package b5;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.uereport.R;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: BaseWindowDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class g extends Dialog implements View.OnClickListener {
    public static final int HEIGHT = 1;
    public static final int WIDTH = 0;
    private int flag;
    private boolean isStartNewUI;
    public Context mContext;
    public int mHeight;
    public WindowManager.LayoutParams mLayoutParams;
    public View mRoot;
    public int mWidth;
    public WindowManager mWindowManager;

    public g(Context context) {
        super(context, R.style.dialog_style);
        this.flag = 0;
        this.mContext = context;
    }

    private void addFlag() {
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().clearFlags(67108864);
        getWindow().getDecorView().setSystemUiVisibility(5889);
        if (AndroidAutoManager.getInstance(this.mContext).isAndroidAutoCasting()) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.flags = 787752;
            getWindow().setAttributes(attributes);
        }
    }

    private void initSize() {
        int[] iArr = new int[2];
        setWidthAndHeight(iArr);
        this.mWidth = iArr[0] != 0 ? this.mContext.getResources().getDimensionPixelSize(iArr[0]) : 0;
        this.mHeight = iArr[1] != 0 ? this.mContext.getResources().getDimensionPixelSize(iArr[1]) : 0;
    }

    private void sendDialogDismissBroadcast() {
        Intent intent = new Intent("com.byd.system.dialog.ACTION_DISMISS");
        intent.putExtra("dialog_name", getClass().getSimpleName());
        g5.a.i("isStartActivity:" + this.isStartNewUI);
        intent.putExtra("isStartActivity", this.isStartNewUI);
        g5.a.i("send broadcast dismiss dialog,dialog_name:" + getClass().getSimpleName());
        this.mContext.sendBroadcast(intent);
    }

    private void sendDialogShowBroadcast() {
        Intent intent = new Intent("com.byd.system.dialog.ACTION_SHOW");
        intent.putExtra("dialog_name", getClass().getSimpleName());
        g5.a.i("send broadcast show dialog,dialog_name:" + getClass().getSimpleName());
        this.mContext.sendBroadcast(intent);
    }

    private void setAttributes() {
        int i8;
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        this.mLayoutParams = attributes;
        int i9 = this.mWidth;
        if (i9 != 0 && (i8 = this.mHeight) != 0) {
            attributes.width = i9;
            attributes.height = i8;
        } else if (isFullScreen()) {
            WindowManager.LayoutParams layoutParams = this.mLayoutParams;
            layoutParams.width = -1;
            layoutParams.height = -1;
        } else {
            WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
            layoutParams2.width = -2;
            layoutParams2.height = -2;
        }
        WindowManager.LayoutParams layoutParams3 = this.mLayoutParams;
        layoutParams3.gravity = 17;
        layoutParams3.format = -3;
        if (isFullScreen()) {
            this.mLayoutParams.windowAnimations = R.style.byd_dialog_common_anim;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.mLayoutParams.type = 2001;
        } else {
            this.mLayoutParams.type = 2003;
        }
        this.mLayoutParams.packageName = this.mContext.getPackageName();
        if (AndroidAutoManager.getInstance(this.mContext).isAndroidAutoCasting()) {
            this.mLayoutParams.dimAmount = 0.0f;
        } else {
            this.mLayoutParams.dimAmount = isFullScreen() ? 0.0f : 0.6f;
        }
        getWindow().setAttributes(this.mLayoutParams);
        getWindow().setFlags(32, 32);
        getWindow().setFlags(CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES, CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (isShowing() && h.e(this.mContext).g(this)) {
            sendDialogDismissBroadcast();
            super.dismiss();
        }
    }

    public int getFlag() {
        return this.flag;
    }

    public WindowManager.LayoutParams getLayoutParams() {
        return this.mLayoutParams;
    }

    public abstract void initContent(View view);

    public abstract void initData();

    public abstract int initLayoutRes();

    public boolean isCanceledOnTouchOutside() {
        return true;
    }

    public abstract boolean isFullScreen();

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(initLayoutRes());
        initSize();
        setAttributes();
        addFlag();
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        initData();
        View decorView = getWindow().getDecorView();
        this.mRoot = decorView;
        initContent(decorView);
    }

    public boolean onKeyEvent(int i8, KeyEvent keyEvent) {
        if (i8 != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        dismiss();
        return true;
    }

    public void setFlag(int i8) {
        this.flag = i8;
    }

    public void setStartNewUI(boolean z7) {
        this.isStartNewUI = z7;
    }

    public abstract void setWidthAndHeight(int[] iArr);

    @Override // android.app.Dialog
    public void show() {
        if (h.e(this.mContext).b(this)) {
            g5.d.b(this.mContext);
            sendDialogShowBroadcast();
            super.show();
        }
    }
}
