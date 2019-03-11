package com.androidpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidpro.adapter.MainAdapter;
import com.androidpro.view.TextViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  private ListView mListView;
  private MainAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mListView = findViewById(R.id.main_lv);
    mAdapter = new MainAdapter(this);
    initData();
  }

  private void initData() {
    ArrayList<String> list = new ArrayList<>();
    list.add("TextView");
    mAdapter.bindData(list);
    mListView.setAdapter(mAdapter);
    mAdapter.notifyDataSetChanged();
    mListView.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
              case 0:
                  Intent intent = new Intent(MainActivity.this, TextViewActivity.class);
                  startActivity(intent);
                break;
              default:
                break;
            }
          }
        });
  }
}
