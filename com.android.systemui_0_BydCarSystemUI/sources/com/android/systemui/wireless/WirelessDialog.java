package com.android.systemui.wireless;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.systemui.R;
import com.android.systemui.base.BaseDialog3;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class WirelessDialog extends BaseDialog3 implements DialogInterface.OnDismissListener {
    private final int delayTime;
    private Handler handler;
    private ImageView img;
    private boolean powerLevel;
    private TextView tv_title;
    protected final int what_dismiss;

    @Override // com.android.systemui.base.BaseDialog3
    protected int getLayoutId() {
        return R.layout.dialog_wireless_charging;
    }

    public WirelessDialog(Context context) {
        super(context);
        this.delayTime = 3000;
        this.what_dismiss = 1;
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void initSize() {
        this.mWidth = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_560);
        this.mHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_356);
    }

    @Override // com.android.systemui.base.BaseDialog3
    protected void initData() {
        setCanceledOnTouchOutside(true);
        setOnDismissListener(this);
        BydLogHelper.logD("start");
        this.img = (ImageView) findViewById(R.id.img);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.img.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.wireless_charging_anim, null));
        this.handler = new H(Looper.getMainLooper());
    }

    public void startModeAnimation() {
        Drawable drawable;
        ImageView imageView = this.img;
        if (imageView == null || (drawable = imageView.getDrawable()) == null || !(drawable instanceof AnimationDrawable)) {
            return;
        }
        ((AnimationDrawable) drawable).start();
    }

    public void stopModeAnimation() {
        Drawable drawable;
        ImageView imageView = this.img;
        if (imageView == null || (drawable = imageView.getDrawable()) == null || !(drawable instanceof AnimationDrawable)) {
            return;
        }
        ((AnimationDrawable) drawable).stop();
    }

    @Override // android.app.Dialog
    public void show() {
        TextView textView = this.tv_title;
        if (textView != null) {
            if (this.powerLevel) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(4);
            }
        }
        super.show();
        BydLogHelper.logD("show");
        startModeAnimation();
        this.handler.removeMessages(1);
        this.handler.sendEmptyMessageDelayed(1, 3000L);
    }

    private int[] getRes(int resArray) {
        TypedArray typedArrayObtainTypedArray = this.mContext.getResources().obtainTypedArray(resArray);
        int length = typedArrayObtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = typedArrayObtainTypedArray.getResourceId(i, -1);
        }
        typedArrayObtainTypedArray.recycle();
        return iArr;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        stopModeAnimation();
        this.handler.removeMessages(1);
        BydLogHelper.logD("onDismiss");
    }

    public void setPowerLevel(boolean level) {
        this.powerLevel = level;
    }

    private class H extends Handler {
        public H(Looper mainLooper) {
            super(mainLooper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            BydLogHelper.logD("what:" + i);
            if (i != 1) {
                return;
            }
            WirelessDialog.this.dismiss();
        }
    }
}
