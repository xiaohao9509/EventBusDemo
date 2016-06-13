package com.nick.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.second_text)
    TextView mSecondText;
    @BindView(R.id.second_btn)
    Button mSecondBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.second_btn)
    public void onClick(View view) {
        System.out.println("被点击了");
        EventBus.getDefault().post(new FirstEvent("现在已经被点击啦!~~"));
    }
}
