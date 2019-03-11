package com.androidpro.tool;

import android.content.Context;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/3/11 下午2:52
 * @description
 */
public class AppUtils {

    private AppUtils(){
    }

    public static int dpToPx(Context context,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp*scale+0.5f);
    }
}
