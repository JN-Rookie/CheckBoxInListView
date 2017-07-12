package com.sjsm.checkboxinlistview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView projectCode;
    private Button btnConfirm;
    private List<Map<String,String>> mList;
    private ProjectCodeAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        projectCode = (ListView) findViewById(R.id.lv_ProjectCode);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);
        initData();
    }

    private void initData() {
        mList=new ArrayList<>();
        Map<String,String> map=null;
        for (int i = 0; i < 20; i++) {
            map=new HashMap<>();
            map.put("ProCode",20170000+i+"");
            mList.add(map);
        }
        Message message=new Message();
        message.what=1;
        mHandler.sendMessage(message);
    }

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 1:
                    mAdapter=new ProjectCodeAdapter(mList,mContext);
                    projectCode.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                    mAdapter.setOnCBClickListener(new ProjectCodeAdapter.OnCBClickListener() {
                        @Override
                        public void onCBClick(String name) {
                            Toast.makeText(mContext,name,Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    };
}
