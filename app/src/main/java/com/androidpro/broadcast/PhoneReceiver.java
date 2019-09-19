package com.androidpro.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.androidpro.MainActivity;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/5/29 下午5:27
 * @description
 */
public class PhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String number = getResultData();

        if (number.equals(MainActivity.number)){
            setResultData("13639253032");
        }else {
            setResultData(number);
        }
    }
}
