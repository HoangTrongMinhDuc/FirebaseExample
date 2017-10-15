package com.hoangtrongminhduc.html5.dev.firebaseexample;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private DatabaseReference nDatabase;
    private ListView lv;
    private EditText edtcongviec, edtnoidung;
    private Button btnDate;
    String ngay;
    Calendar cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        edtcongviec = (EditText) findViewById(R.id.edtcongviec);
        edtnoidung = (EditText) findViewById(R.id.edtnoidung);
        btnDate = (Button) findViewById(R.id.btnDate);
        final ArrayList<JournalEntry> arrJ = new ArrayList<>();
        mDatabase =  FirebaseDatabase.getInstance().getReference();
        nDatabase = mDatabase.child("journalentris");
        final JEArrayAdapter mAdapter = new JEArrayAdapter(this,R.layout.journal, arrJ);
        lv.setAdapter(mAdapter);
        nDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                JournalEntry je = dataSnapshot.getValue(JournalEntry.class);
                arrJ.add(je);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                JournalEntry current = mAdapter.getItem(position);
                nDatabase.child(current.getKey()).removeValue() ;
                Toast.makeText(MainActivity.this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                mAdapter.remove(current);
                mAdapter.notifyDataSetChanged();
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JournalEntry current = mAdapter.getItem(position);
                Toast.makeText(MainActivity.this, current.getContent(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addData(View view){
        JournalEntry journalEntry1 = new JournalEntry();
        journalEntry1.setTitle(edtcongviec.getText().toString());
        journalEntry1.setContent(edtnoidung.getText().toString());
        journalEntry1.setDate(ngay);
        String khoa = mDatabase.child("journalentris").push().getKey();
        journalEntry1.setKey(khoa);
        nDatabase.child(khoa).setValue(journalEntry1);
    }

    public void showDate(View view){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DATE);
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ngay = (year+"/"+(dayOfMonth+1)+"/"+month);
            }
        };
        DatePickerDialog dialog=new DatePickerDialog(this, onDateSetListener, year, month, day);
        dialog.setTitle("Chọn giờ hoàn thành");
        dialog.show();
    }
}
