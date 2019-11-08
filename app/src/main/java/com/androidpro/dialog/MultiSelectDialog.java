package com.androidpro.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.androidpro.R;
import com.androidpro.view.SelectView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019-11-08 19:54
 * @description 多选Dialog
 */
public class MultiSelectDialog {
    private Activity activity;
    private Map<String, String> feedbackReasons;//可以换成ArrayList

    public MultiSelectDialog(Activity activity) {
        this.activity = activity;
        initDialogLayout();
        initListener();
    }

    private AlertDialog funScreenDialog;
    private LinearLayout mItemContainer;
    private View root;
    private ImageView closeIV;
    private SelectView mSubmitBtn;
    private TextView mTitleTxt;

    private View.OnClickListener closeListener;
    private View.OnClickListener itemListener;
    private View.OnClickListener submitListener;

    private ArrayList<String> mSelectSet;//选中理由
    private int mReasonCount = 0;//选中理由数量
    private boolean isSingleType = false;//单行显示

    private final String titleTxt = "选择不喜欢的理由";

    private void initDialogLayout() {
        root = LayoutInflater.from(activity).inflate(R.layout.layout_multi_select_dialog, null);
        mItemContainer = root.findViewById(R.id.ll_select_container);
        mTitleTxt = root.findViewById(R.id.tv_select_title);
        mSubmitBtn = root.findViewById(R.id.btn_select_submit);
        closeIV = root.findViewById(R.id.iv_select_close);

        mSubmitBtn.setSelectTextColor(R.color.m_100);
        funScreenDialog = new AlertDialog.Builder(activity).create();
        funScreenDialog.setContentView(root);
        root.findViewById(R.id.content_layout).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //do nothing,覆盖点击事件，不点击消失
            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                funScreenDialog.hide();
            }
        });
    }

    /*点击监听器初始化*/
    private void initListener() {
        mSelectSet = new ArrayList<>();
        closeListener = v -> funScreenDialog.hide();
        submitListener = v -> {
            funScreenDialog.hide();
        };
        itemListener = v -> {
            if (v instanceof SelectView) {
                SelectView textView = (SelectView) v;
                updateReasonsView(textView.isSelect(), textView.getTag());
            }
        };
        mSubmitBtn.setOnClickListener(null);
        mSubmitBtn.autoUpdate(false);
        closeIV.setOnClickListener(closeListener);
    }

    private void updateReasonsView(boolean select, String text) {
        if (select) {
            if (!mSelectSet.contains(text)) {
                mSelectSet.add(text);
            }
        } else {
            mSelectSet.remove(text);
        }
        mReasonCount = mSelectSet.size();

        if (mReasonCount > 0) {
            mSubmitBtn.setSelect(true);
            mSubmitBtn.setOnClickListener(submitListener);
            String showTitle = "已选择" + mReasonCount + "个理由";
            int size = (mReasonCount + "").length();
            SpannableString spannableString = new SpannableString(showTitle);
            ForegroundColorSpan colorSpan =
                new ForegroundColorSpan(activity.getResources().getColor(R.color.m_100));
            spannableString.setSpan(colorSpan, 3, 3 + size, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            mTitleTxt.setText(spannableString);
        } else {
            mSubmitBtn.setSelect(false);
            mSubmitBtn.setOnClickListener(null);
            mTitleTxt.setText(titleTxt);
        }
    }

    public void show() {
        funScreenDialog.show();
    }

    /*帖子反馈*/
    public void setDataFeed(Map<String, String> feedbackReasons) {
        //反馈理由,
        isSingleType = false;
        this.feedbackReasons = feedbackReasons;
        fillFirstLayout(feedbackReasons);
    }

    /*展示反馈理由*/
    private void fillFirstLayout(Map<String, String> feedbackReasons) {
        if (feedbackReasons != null && !feedbackReasons.isEmpty()) {
            Iterator iterable = feedbackReasons.keySet().iterator();
            //单行类型dialog
            if (isSingleType) {
                Item preItem;
                while (iterable.hasNext()) {
                    String key = (String) iterable.next();
                    String value = feedbackReasons.get(key);
                    if (!TextUtils.isEmpty(value)) {
                        value = value.replace(":", " ");
                        preItem = new Item(1);
                        preItem.fillData(value, key);
                        mItemContainer.addView(preItem.getView());
                    }
                }
            }
        }
    }

    /*反馈理由单行Item*/
    class Item {
        private View root;
        private SelectView item1;
        private SelectView item2;
        private SelectView item3;
        private int space = 1;//剩余空间
        private int index = 0;//当前下标

        public Item() {
            this(1);
        }

        Item(int space) {
            this.space = space;
            root = LayoutInflater.from(activity)
                .inflate(R.layout.layout_select_item, mItemContainer, false);
            item1 = root.findViewById(R.id.tv_feedback_item_1);
            item2 = root.findViewById(R.id.tv_feedback_item_2);
            item3 = root.findViewById(R.id.tv_feedback_item_3);
            item1.setOnClickListener(itemListener);
            item2.setOnClickListener(itemListener);
            item3.setOnClickListener(itemListener);
            item1.setSelectTextColor(R.color.m_100);
            item2.setSelectTextColor(R.color.m_100);
            item3.setSelectTextColor(R.color.m_100);

            if (space == 2) {
                item2.setVisibility(INVISIBLE);
            } else if (space > 2) {
                item2.setVisibility(INVISIBLE);
                item3.setVisibility(INVISIBLE);
            }
        }

        void fillData(String text, String tag) {
            if (space < 1) return;
            if (index == 0) {
                item1.setText(text, tag);
            } else if (index == 1) {
                item2.setText(text, tag);
                item2.setVisibility(VISIBLE);
            } else {
                item3.setText(text, tag);
                item3.setVisibility(VISIBLE);
            }
            index++;
            space--;
        }

        /*是否能填充数据*/
        private boolean canFill() {
            return space > 0;
        }

        public View getView() {
            return root;
        }
    }
}

