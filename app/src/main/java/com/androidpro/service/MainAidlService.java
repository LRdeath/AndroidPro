package com.androidpro.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/5/7 下午5:06
 * @description
 */
public class MainAidlService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return VzerManager.getInstance();
    }


}
