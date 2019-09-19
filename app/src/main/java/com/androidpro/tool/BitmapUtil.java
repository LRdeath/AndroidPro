package com.androidpro.tool;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/3/28 上午11:56
 * @description
 */
public class BitmapUtil {

    /**
     * 本地文件加载图片
     *
     * @param filePath 文件路径
     * @param width    宽
     * @param high     高
     * @return
     */
    public static Bitmap getBitmapByFile(String filePath, int width, int high) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只读图片宽高，不加载图片，防止图片过大加载到内存溢出
        BitmapFactory.decodeFile(filePath, options);
        int inSampleSize = 1;
        if (options.outWidth > width || options.outHeight > high) {
            if (options.outHeight > options.outWidth) {
                inSampleSize = options.outHeight / high;
            } else {
                inSampleSize = options.outWidth / width;
            }
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 本地文件加载图片（效率高于上一种)
     *
     * @param filePath 文件路径
     * @param width    宽
     * @param high     高
     * @return
     */
    public static Bitmap getBitmapByFileI(String filePath, int width, int high) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//只读图片宽高，不加载图片，防止图片过大加载到内存溢出
            BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);//以文件描述符号的形式加载图片
            int inSampleSize = 1;
            if (options.outWidth > width || options.outHeight > high) {
                if (options.outHeight > options.outWidth) {
                    inSampleSize = options.outHeight / high;
                } else {
                    inSampleSize = options.outWidth / width;
                }
            }
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 本地获取缩放后的bitmap
     *
     * @param context
     * @param filepath 文件路径，即文件名称
     * @return
     */
    public static Bitmap readBitmapFromAssets(Context context, String filepath) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(filepath);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 从输入流获取图片（网络加载)
     *
     * @param ins    输入流
     * @param width  宽
     * @param height 高
     * @return
     */
    public static Bitmap readBitmapFromInputStream(InputStream ins, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(ins, null, options);
        int inSampleSize = 1;
        if (options.outWidth > width || options.outHeight > height) {
            if (options.outHeight > options.outWidth) {
                inSampleSize = options.outHeight / height;
            } else {
                inSampleSize = options.outWidth / width;
            }
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(ins, null, options);
    }

    /**
     * 从Res获取图片
     *
     * @param resources   res
     * @param resourcesId 图片id
     * @param width       宽
     * @param height      高
     * @return
     */
    public static Bitmap readBitmapFromResource(Resources resources, int resourcesId, int width, int height) {
        InputStream ins = resources.openRawResource(resourcesId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        /*
          效率较低，加载图片会进行缩放，该缩放在java层进行的，
         */
//        BitmapFactory.decodeResource(resources,resourcesId,options);//
        BitmapFactory.decodeStream(ins, null, options);
        int inSampleSize = 1;
        if (options.outWidth > width || options.outHeight > height) {
            if (options.outHeight > options.outWidth) {
                inSampleSize = options.outHeight / height;
            } else {
                inSampleSize = options.outWidth / width;
            }
        }
        options.inSampleSize = inSampleSize;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(ins, null, options);
    }

    /**
     * 从二进制读取图片
     *
     * @param data   图片二进制数组
     * @param width  宽
     * @param height 高
     * @return
     */
    public static Bitmap readBitmapFromByteArray(byte[] data, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int inSampleSize = 1;
        if (options.outWidth > width || options.outHeight > height) {
            if (options.outWidth > options.outHeight) {
                inSampleSize = (int) ((options.outWidth / width )+ 0.5);
            } else {
                inSampleSize = options.outHeight / height;
            }
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = inSampleSize;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /*转换部分*/

    /**
     * Drawable 转化 Bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] bitmapToBytes(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        return baos.toByteArray();
    }
}
