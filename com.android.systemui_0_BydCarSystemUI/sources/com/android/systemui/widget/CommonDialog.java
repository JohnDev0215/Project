package com.android.systemui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.systemui.R;
import com.android.systemui.base.BaseDialog;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class CommonDialog extends BaseDialog implements DialogInterface.OnDismissListener {
    private static final int DEFAULT_DURATION = 1000;
    public static final int DOUBLE_BUTTON_LEFT_CANCEL = 1;
    public static final int DOUBLE_BUTTON_RIGHT_CANCEL = 2;
    public static final int STYLE_COUNT_DOWN = 3;
    public static final int STYLE_COUNT_DOWN_DOUBLE_BUTTON = 4;
    public static final int STYLE_DOUBLE_BUTTON = 2;
    public static final int STYLE_SINGLE_BUTTON = 1;
    public static final int SWITCH_COUNT_DOWN_LEFT_BTN = 1;
    public static final int SWITCH_COUNT_DOWN_RIGHT_BTN = 2;
    private int bottomStyle;
    private TextView btnLeft;
    private TextView btnRight;
    private int countDownNum;
    private String mBtnLeftString;
    private String mBtnRightString;
    private int mCountTime;
    private int mDoubleButtonStyle;
    private DialogHandler mHandler;
    private LinearLayout mLlDialogBackground;
    private LinearLayout mLlDialogContainer;
    private String mMessage;
    private int mSwitchCountDownBtn;
    private String mTitle;
    private TextView messageTv;
    public OnClickBottomListener onClickBottomListener;
    private View titleLine;
    private TextView titleTv;

    public interface OnClickBottomListener {
        void onNegativeClick();

        void onPositiveClick();
    }

    static /* synthetic */ void lambda$initView$5(View view) {
    }

    protected void onInitView() {
    }

    protected void onRefreshView() {
    }

    public CommonDialog(Context context) {
        super(context, R.style.dialog);
        this.bottomStyle = 2;
        this.mDoubleButtonStyle = 1;
        this.mSwitchCountDownBtn = 1;
        this.mCountTime = 8;
        this.countDownNum = 8;
        BydLogHelper.logD("new CommonDialog");
        this.mHandler = new DialogHandler(this);
        addFlag();
        setCanceledOnTouchOutside(true);
        initView();
        refreshView();
    }

    @Override // com.android.systemui.base.BaseDialog
    protected int getLayoutId() {
        return AppUtils.isRightRudder() ? R.layout.common_dialog_layout_right_rudder : R.layout.common_dialog_layout;
    }

    private void addFlag() {
        getWindow().addFlags(403571968);
    }

    private void initEvent() {
        BydLogHelper.logD("initEvent mDoubleButtonStyle : " + this.mDoubleButtonStyle);
        int i = this.mDoubleButtonStyle;
        if (i == 1) {
            this.btnRight.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.-$$Lambda$CommonDialog$MTY8uLpmJxMHsJ0FO4JTBeLG8C4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initEvent$0$CommonDialog(view);
                }
            });
            this.btnLeft.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.-$$Lambda$CommonDialog$BjSV1zTWypVJPvRlIxtkgu4Mjpc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initEvent$1$CommonDialog(view);
                }
            });
        } else if (i == 2) {
            this.btnLeft.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.-$$Lambda$CommonDialog$YJpuPVwPhMbckx00l-OXE-M0D9E
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initEvent$2$CommonDialog(view);
                }
            });
            this.btnRight.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.-$$Lambda$CommonDialog$vQXNQDKjWWEJn8HwPlXQnxxqvHo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initEvent$3$CommonDialog(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$initEvent$0$CommonDialog(View view) {
        OnClickBottomListener onClickBottomListener = this.onClickBottomListener;
        if (onClickBottomListener != null) {
            onClickBottomListener.onPositiveClick();
        }
    }

    public /* synthetic */ void lambda$initEvent$1$CommonDialog(View view) {
        OnClickBottomListener onClickBottomListener = this.onClickBottomListener;
        if (onClickBottomListener != null) {
            onClickBottomListener.onNegativeClick();
        }
    }

    public /* synthetic */ void lambda$initEvent$2$CommonDialog(View view) {
        OnClickBottomListener onClickBottomListener = this.onClickBottomListener;
        if (onClickBottomListener != null) {
            onClickBottomListener.onPositiveClick();
        }
    }

    public /* synthetic */ void lambda$initEvent$3$CommonDialog(View view) {
        OnClickBottomListener onClickBottomListener = this.onClickBottomListener;
        if (onClickBottomListener != null) {
            onClickBottomListener.onNegativeClick();
        }
    }

    private void refreshView() {
        if (!TextUtils.isEmpty(this.mTitle)) {
            this.titleTv.setText(this.mTitle);
            this.titleTv.setVisibility(0);
            this.titleLine.setVisibility(0);
        } else {
            this.titleTv.setVisibility(8);
            this.titleLine.setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.mMessage)) {
            this.messageTv.setText(this.mMessage);
        }
        if (!TextUtils.isEmpty(this.mBtnLeftString)) {
            this.btnLeft.setText(this.mBtnLeftString);
        } else {
            this.btnLeft.setText(getContext().getString(R.string.confirm));
        }
        if (!TextUtils.isEmpty(this.mBtnRightString)) {
            this.btnRight.setText(this.mBtnRightString);
        } else {
            this.btnRight.setText(getContext().getString(R.string.cancel));
        }
        updateBottomStyle();
        onRefreshView();
    }

    private void updateBottomStyle() {
        BydLogHelper.logD("bottomStyle : " + this.bottomStyle);
        int i = this.bottomStyle;
        if (i == 1) {
            this.btnRight.setVisibility(8);
            return;
        }
        if (i == 2) {
            this.btnRight.setVisibility(0);
            return;
        }
        if (i == 3) {
            this.btnRight.setVisibility(8);
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessage(1);
        } else {
            if (i != 4) {
                return;
            }
            this.btnRight.setVisibility(0);
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessage(1);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        this.countDownNum = this.mCountTime;
        if (this.mHandler == null) {
            this.mHandler = new DialogHandler(this);
        }
        refreshView();
        initEvent();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        BydLogHelper.logD("CommonDialog dismiss");
        DialogHandler dialogHandler = this.mHandler;
        if (dialogHandler != null) {
            dialogHandler.removeMessages(1);
            this.mHandler = null;
        }
    }

    private void initView() {
        this.mLlDialogContainer = (LinearLayout) findViewById(R.id.ll_dialog_container);
        this.mLlDialogBackground = (LinearLayout) findViewById(R.id.ll_dialog_background);
        this.btnLeft = (TextView) findViewById(R.id.btnLeft);
        this.btnRight = (TextView) findViewById(R.id.btnRight);
        this.titleTv = (TextView) findViewById(R.id.title);
        this.messageTv = (TextView) findViewById(R.id.message);
        this.titleLine = findViewById(R.id.title_line);
        LinearLayout linearLayout = this.mLlDialogContainer;
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.-$$Lambda$CommonDialog$T8YjZI0dwB04LZYgN2hddjBAXt4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$4$CommonDialog(view);
                }
            });
        }
        LinearLayout linearLayout2 = this.mLlDialogBackground;
        if (linearLayout2 != null) {
            linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.-$$Lambda$CommonDialog$L-MP8QZlypT4lDg3vzNYKVnJ8zE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CommonDialog.lambda$initView$5(view);
                }
            });
        }
        onInitView();
    }

    public /* synthetic */ void lambda$initView$4$CommonDialog(View view) {
        dismiss();
    }

    public CommonDialog setOnClickBottomListener(OnClickBottomListener onClickBottomListener) {
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        BydLogHelper.logD("onDismiss");
    }

    public int getBottomStyle() {
        return this.bottomStyle;
    }

    public CommonDialog setBottomStyle(int bottomStyle) {
        this.bottomStyle = bottomStyle;
        return this;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public CommonDialog setMessage(String message) {
        this.mMessage = message;
        return this;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public CommonDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public String getBtnLeft() {
        return this.mBtnLeftString;
    }

    public CommonDialog setBtnLeft(String s) {
        this.mBtnLeftString = s;
        return this;
    }

    public String getBtnRight() {
        return this.mBtnRightString;
    }

    public CommonDialog setBtnRight(String s) {
        this.mBtnRightString = s;
        return this;
    }

    public CommonDialog setCountTime(int countTime) {
        this.mCountTime = countTime;
        return this;
    }

    public int getDoubleButtonStyle() {
        return this.mDoubleButtonStyle;
    }

    public CommonDialog setDoubleButtonStyle(int mDoubleButtonStyle) {
        this.mDoubleButtonStyle = mDoubleButtonStyle;
        return this;
    }

    public int getSwitchCountDownBtn() {
        return this.mSwitchCountDownBtn;
    }

    public CommonDialog setSwitchCountDownBtn(int mCountDownBtn) {
        this.mSwitchCountDownBtn = mCountDownBtn;
        return this;
    }

    public CommonDialog setBtnLeftBgColor(boolean color) {
        if (color) {
            this.btnLeft.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_hvac_system_button_blue_bg, null));
        }
        return this;
    }

    public CommonDialog setBtnRightBgColor(boolean color) {
        if (color) {
            this.btnRight.setBackground(this.mContext.getResources().getDrawable(R.drawable.shape_hvac_system_button_blue_bg, null));
        }
        return this;
    }

    public CommonDialog setBtnLeftTextColor(int color) {
        this.btnLeft.setTextColor(color);
        return this;
    }

    public CommonDialog setBtnRightTextColor(int color) {
        this.btnRight.setTextColor(color);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCountDownNum() {
        if (this.countDownNum > 0) {
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 1000L);
            BydLogHelper.logD("updateCountDownNum mSwitchCountDownBtn : " + this.mSwitchCountDownBtn);
            int i = this.mSwitchCountDownBtn;
            if (i == 1) {
                this.btnLeft.setText(String.format(this.mBtnLeftString, Integer.valueOf(this.countDownNum)));
            } else if (i == 2) {
                this.btnRight.setText(String.format(this.mBtnRightString, Integer.valueOf(this.countDownNum)));
            }
        } else {
            BydLogHelper.logD("count down over");
            dismiss();
        }
        this.countDownNum--;
    }

    private static final class DialogHandler extends Handler {
        public static final int START_COUNT_DOWN_MSG = 1;
        private final CommonDialog dialog;

        public DialogHandler(CommonDialog dialog) {
            this.dialog = (CommonDialog) new WeakReference(dialog).get();
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BydLogHelper.logD("handleMessage", "msg.what = " + msg.what);
            if (msg.what == 1) {
                this.dialog.updateCountDownNum();
            }
        }
    }
}
