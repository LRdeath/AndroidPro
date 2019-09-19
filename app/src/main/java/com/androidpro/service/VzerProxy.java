package com.androidpro.service;

import android.app.Activity;

import com.androidpro.MainActivity;

import java.lang.ref.WeakReference;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/5/15 下午4:43
 * @description
 */
public class VzerProxy {
    private static final VzerProxy ourInstance = new VzerProxy();
    private WeakReference<MainActivity> context;

   public static VzerProxy getInstance() {
        return ourInstance;
    }

    private VzerProxy() {
    }
    public void setJson(String json){
        VzerManager.getInstance().setJson(json);
    }
    public void handleTxt(String txt){
       if (context.get()!=null){
           context.get().updateShow(txt);
       }
    }

    public void setContext(MainActivity activity){
       context = new WeakReference<>(activity);
    }
}
