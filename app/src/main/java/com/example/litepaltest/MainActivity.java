package com.example.litepaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCreateDatabase;
    private Button mAddData;
    private Button mUpdateData;
    private Button mDeleteData;
    private Button mQueryData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        LitePal.initialize(this);
    }

    private void initView() {
        mCreateDatabase = findViewById(R.id.create_database);
        mCreateDatabase.setOnClickListener(this);
        mAddData = findViewById(R.id.add_data);
        mAddData.setOnClickListener(this);
        mUpdateData = findViewById(R.id.update_data);
        mUpdateData.setOnClickListener(this);
        mDeleteData = findViewById(R.id.delete_data);
        mDeleteData.setOnClickListener(this);
        mQueryData = findViewById(R.id.query_data);
        mQueryData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.create_database:
                LitePal.getDatabase();
                break;
            case R.id.add_data:
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("UNKNOWN");
                book.save();
                break;
            case R.id.update_data:
                Book book1 = new Book();
                book1.setPrice(14.95);
                book1.setPress("Anchor");
                book1.updateAll("name = ? and author = ?", "The Da Vinci Code", "Dan Brown");
                break;
            case R.id.delete_data:
                LitePal.deleteAll(Book.class, "price < ?", "15");
                break;
            case R.id.query_data:
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book2 : books) {
                    Log.i("MainActivity"," book id is " + book2.getId());
                    Log.i("MainActivity"," book name is " + book2.getName());
                    Log.i("MainActivity"," book author is " + book2.getAuthor());
                    Log.i("MainActivity"," book pages is " + book2.getPress());
                    Log.i("MainActivity"," book price is " + book2.getPrice());
                    Log.i("MainActivity"," book press is " + book2.getPress());
                }
                break;
        }
    }
}
