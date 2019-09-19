package com.androidpro.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidpro.R;
import com.androidpro.tool.AppUtils;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/3/8 上午11:29
 * @description
 */
public class TextViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView showTV;
    private Button spanBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_text);
        showTV = findViewById(R.id.tv_show);
        spanBtn = findViewById(R.id.btn_tv_1);
        spanBtn.setOnClickListener(this);
        findViewById(R.id.btn_tv_2).setOnClickListener(this);
        Log.d(this.getClass().getName(), "====onCreate====");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getClass().getName(), "====onStart====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.getClass().getName(), "====onResume====");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getClass().getName(), "====onPause====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.getClass().getName(), "====onStop====");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.getClass().getName(), "====onDestroy====");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_tv_1:
                handleSpanText();
                break;
            case R.id.btn_tv_2:
                Intent intent = new Intent(this, LifeActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void handleSpanText() {
        SpannableString str = new SpannableString("菩提本无树，明镜亦非台，本来无一物，何处染尘埃。");
        //      Spanned.SPAN_EXCLUSIVE_EXCLUSIVE —— (a,b)
        //      Spanned.SPAN_EXCLUSIVE_INCLUSIVE —— (a,b]
        //      Spanned.SPAN_INCLUSIVE_EXCLUSIVE —— [a,b)
        //      Spanned.SPAN_INCLUSIVE_INCLUSIVE —— [a,b]
        // URLSpan 点击打开Url
        str.setSpan(
                new URLSpan("https://baike.baidu.com/item/%E8%8F%A9%E6%8F%90/934?fr=aladdin"),
                0,
                2,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        // TypefaceSpan 设置字体 monospace, serif, sans-serif
        str.setSpan(new TypefaceSpan("sans-serif"), 3, 4, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        // TextAppearanceSpan设置文字样式
        // Typeface.NORMAL
        // Typeface.BOLD
        // Typeface.ITALIC
        // Typeface.BOLD_ITALIC
        str.setSpan(
                new TextAppearanceSpan(
                        "serif", Typeface.BOLD_ITALIC, AppUtils.dpToPx(TextViewActivity.this, 14), null, null),
                5,
                10,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        showTV.setText(str);
        // 在单击链接时凡是有要执行的动作，都必须设置MovementMethod对象
        showTV.setMovementMethod(LinkMovementMethod.getInstance());
        // 设置点击后的颜色，这里涉及到ClickableSpan的点击背景
        showTV.setHighlightColor(0xff8FABCC);
    }
}
