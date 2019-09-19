package com.androidpro.service;

import android.os.RemoteException;

import com.androidpro.IVzerAidl;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/5/15 下午4:33
 * @description
 */
public class VzerManager extends IVzerAidl.Stub{
    private static final VzerManager ourInstance = new VzerManager();

    private String json = "还没有数据";
    static VzerManager getInstance() {
        return ourInstance;
    }

    private VzerManager() {
    }

    @Override
    public String getJsonString() throws RemoteException {
        return json;
    }

    @Override
    public void setMessage(String msg) throws RemoteException {
        VzerProxy.getInstance().handleTxt(msg);
    }

    void setJson(String json){
        this.json = json;
    }
}
