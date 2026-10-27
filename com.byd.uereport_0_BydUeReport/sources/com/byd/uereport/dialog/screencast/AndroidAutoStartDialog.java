package com.byd.uereport.dialog.screencast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import g5.c;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoStartDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final BroadcastReceiver f3363f;

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.i("mUSBDeviceReceiver");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String string = extras.getString("device");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                if (string.equals(AndroidAutoStartDialog.this.f3361d) || string.contains(AndroidAutoStartDialog.this.f3361d)) {
                    AndroidAutoStartDialog.this.dismiss();
                }
            }
        }
    }

    public AndroidAutoStartDialog(Context context) {
        super(context);
        this.f3363f = new a();
    }

    public final void d() {
        if (TextUtils.isEmpty(this.f3361d)) {
            return;
        }
        AndroidAutoManager.getInstance(this.mContext).connectAndroidAuto(this.f3361d, this.f3362e);
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a1.a.b(this.mContext).e(this.f3363f);
    }

    public int e() {
        return this.f3362e;
    }

    public void f(int i8) {
        this.f3362e = i8;
    }

    public String getDeviceName() {
        return this.f3361d;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_yes);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_no);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
        a1.a.b(this.mContext).c(this.f3363f, new IntentFilter("cpaa_usb_device_detach"));
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_android_auto_start_right_rudder : R.layout.dialog_android_auto_start;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_yes) {
            d();
        }
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    public void setDeviceName(String str) {
        this.f3361d = str;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
