package com.nick.eventbusdemo;

/**
 * Created by Administrator on 2016/6/12.
 * User: Nick
 * Date: 2016/6/12
 * Email: 305812387@qq.com
 * Project: EventBusDemo
 */
public class FirstEvent {
    private String msg;

    public FirstEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
