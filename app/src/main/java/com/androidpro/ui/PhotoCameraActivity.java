package com.androidpro.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.androidpro.R;

import java.io.File;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/3/11 下午6:31 打开系统相机Activity
 * @description
 */
public class PhotoCameraActivity extends AppCompatActivity {
  private ImageView showIv;
  private Uri imageUri;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_camera);
    showIv = findViewById(R.id.iv_show);
    findViewById(R.id.btn_open_camera)
        .setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                  File newFile = new File(getFilesDir(),"images");
                  if (!newFile.exists()){
                      newFile.mkdirs();
                  }
                  File imagesFile = new File(newFile,"case.jpg");
                  imageUri = Uri.parse(imagesFile.toString());
                  intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                  //添加运行时权限
                  intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                          |Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                  startActivityForResult(intent,0);
              }
            });
  }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode == Activity.RESULT_OK){
            if (data == null ||data.getExtras()==null){
                return;
            }

            //获取拍照后的图片
//            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            if (imageUri!=null) {
                showIv.setImageURI(imageUri);
            }
//            if (bmp!=null){
//                showIv.setImageBitmap(bmp);
//            }
        }
    }
}
