package com.byd.profile.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.profile.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ToastWindow extends FloatingWindow {
    private static final int TIME_SHOW = 3000;
    private static final int TYPE_ONLY_TEXT = 0;
    private static final int TYPE_WITH_ICON = 1;
    private int iconRes;
    private final Handler mHandler;
    private View mRoot;
    private String mText;

    @Override // com.byd.profile.dialog.FloatingWindow
    protected boolean isFullScreen() {
        return false;
    }

    public ToastWindow(Context context, String text) {
        super(context, 0);
        this.mHandler = new Handler(new Handler.Callback() { // from class: com.byd.profile.dialog.ToastWindow.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                ToastWindow.this.dismiss();
                return true;
            }
        });
        this.mText = text;
    }

    public ToastWindow(Context context, int textRes) {
        super(context, 0);
        this.mHandler = new Handler(new Handler.Callback() { // from class: com.byd.profile.dialog.ToastWindow.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                ToastWindow.this.dismiss();
                return true;
            }
        });
        this.mText = context.getString(textRes);
    }

    public ToastWindow(Context context, int iconRes, int textRes) {
        super(context, 1);
        this.mHandler = new Handler(new Handler.Callback() { // from class: com.byd.profile.dialog.ToastWindow.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                ToastWindow.this.dismiss();
                return true;
            }
        });
        this.iconRes = iconRes;
        this.mText = context.getString(textRes);
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    protected void setWidthAndHeight(int[] size) {
        BydLogHelper.logD("mType = " + this.mType);
        size[0] = this.mType == 0 ? 0 : R.dimen.dp_232;
        size[1] = this.mType == 0 ? R.dimen.dp_56 : R.dimen.dp_208;
    }

    @Override // com.byd.profile.dialog.FloatingWindow
    protected void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(this.mType == 0 ? R.layout.layout_toast_normal : R.layout.layout_toast_icon, (ViewGroup) null);
        this.mRoot = viewInflate;
        ((TextView) viewInflate.findViewById(R.id.tv_message)).setText(this.mText);
        if (this.mType == 1) {
            ((ImageView) this.mRoot.findViewById(R.id.iv_icon)).setImageResource(this.iconRes);
        }
        this.mHandler.sendEmptyMessageDelayed(0, 3000L);
    }

    public void show() {
        initView();
        if (this.mRoot != null) {
            this.mLayoutParams.gravity = 17;
            if (this.mType == 0) {
                this.mLayoutParams.gravity = 49;
                this.mLayoutParams.y = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_128);
            }
            show(this.mRoot);
        }
    }

    public void show(int x, int y, int gravity) {
        initView();
        if (this.mRoot != null) {
            this.mLayoutParams.gravity = gravity;
            if (this.mType == 0) {
                this.mLayoutParams.y = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_128);
            }
            show(this.mRoot, x, y);
        }
    }

    public void dismiss() {
        this.mHandler.removeMessages(0);
        super.dismiss(this.mRoot);
        this.mRoot = null;
    }
}
