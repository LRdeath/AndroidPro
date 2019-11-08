package com.androidpro.view;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import com.androidpro.R;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019-11-07 10:27
 * @description
 */
public class SelectView extends AppCompatTextView {
    private View.OnClickListener mClickListener;
    private boolean isSelect = false;
    private boolean autoUpdate = true;

    private @ColorInt int selectTextColor; //字体选中颜色
    private @ColorInt int mTextColor; //字体颜色
    private int defaultColor;//字体默认颜色

    private View.OnClickListener curListener;
    private String mTag ="";

    public SelectView(Context context) {
        this(context, null);
    }

    public SelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        defaultColor = getContext().getResources().getColor(R.color.t_1);
        curListener = v -> {
            if (autoUpdate){
                setSelect(!isSelect);
            }
            if (mClickListener != null) {
                mClickListener.onClick(v);
            }
        };
        super.setOnClickListener(curListener);
        mTextColor = getCurrentTextColor();
    }

    private void updateView() {
        int textColor = isSelect ? selectTextColor : mTextColor;
        try {
            setTextColor(textColor);
        } catch (Exception e) {
            setTextColor(defaultColor);
        }
        setSelected(isSelect);
    }

    /*当前View的选中状态*/
    public boolean isSelect() {
        return isSelect;
    }
    /*是否自动更新选中状态*/
    public void autoUpdate(boolean auto) {
        autoUpdate = auto;
    }
    /*更新状态*/
    public void setSelect(boolean select) {
       isSelect = select;
       updateView();
    }

    @Override public void setOnClickListener(@Nullable View.OnClickListener l) {
        this.mClickListener = l;
    }

    public void setText(String text,@NonNull String tag){
        setText(text);
        this.mTag = tag;
    }

    /*获取当前tag*/
    public String getTag(){
        return mTag;
    }

    /*TextColor 选中颜色*/
    public void setSelectTextColor(@ColorRes int colorResId) {
        this.selectTextColor = getContext().getResources().getColor(colorResId);
    }

    /*TextColor 选中和未选中颜色*/
    public void setTextColor(@ColorRes int textColor, @ColorRes int selectColor) {
        this.selectTextColor = getContext().getResources().getColor(selectColor);
        this.mTextColor = getContext().getResources().getColor(textColor);
    }
}
