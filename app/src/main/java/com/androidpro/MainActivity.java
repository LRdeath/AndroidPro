package com.androidpro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidpro.adapter.MainAdapter;
import com.androidpro.service.VzerProxy;
import com.androidpro.ui.PhotoCameraActivity;
import com.androidpro.ui.TextViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private MainAdapter mAdapter;
    private EditText aidlEdt;
    private TextView showTxt;
    private ImageView showImg;
    private Button bitmapBtn;
    public static String number = "16601102256";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null && msg.obj instanceof BitmapBean){
                BitmapBean bean = (BitmapBean) msg.obj;
                showTxt.setText(bean.showtime);
                showImg.setImageBitmap(bean.bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.main_lv);
        mAdapter = new MainAdapter(this);
        aidlEdt = findViewById(R.id.edt_aidl);
        showTxt = findViewById(R.id.tv_show);
        showImg = findViewById(R.id.iv_show);
        bitmapBtn = findViewById(R.id.btn_bitmap);
        bitmapBtn.setOnClickListener(this);
        initData();
        findViewById(R.id.btn_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VzerProxy.getInstance().setJson(aidlEdt.getText().toString());
            }
        });
        VzerProxy.getInstance().setContext(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityCompat.requestPermissions(this,new String[]{"android.permission.PROCESS_OUTGOING_CALLS"},1);
//        Log.d(this.getClass().getName(),"====onResume====");
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(this.getClass().getName(),"====onPause====");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(this.getClass().getName(),"====onStop====");
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d(this.getClass().getName(),"====onStart====");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(this.getClass().getName(),"====onDestroy====");
//    }

    private void initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("TextView");
        list.add("相机");
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
                            case 1:
                                Intent intent1 = new Intent(MainActivity.this, PhotoCameraActivity.class);
                                startActivity(intent1);
                                break;
                            default:
                                break;
                        }
                    }
                });
    }

    public void updateShow(final String txt) {
        aidlEdt.post(new Runnable() {
            @Override
            public void run() {
                aidlEdt.setText(txt);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bitmap:
                handleBitmap();
                break;
            default:
                break;
        }
    }

    private void handleBitmap() {
        String res = aidlEdt.getText().toString();
        if (!TextUtils.isEmpty(res) && TextUtils.isDigitsOnly(res)){
            number =  res;
        }
    }
}
