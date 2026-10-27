package com.byd.uereport.dialog.screencast;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.systemsettings.cpaa.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import g5.c;
import g5.k;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CastProtectionNoticeDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3381d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3382e;

    public CastProtectionNoticeDialog(Context context) {
        super(context);
        this.f3381d = true;
    }

    public final void c() {
        if (this.f3382e == null) {
            Log.d(CarPlayManager.TAG, "mac is null , cannot connect carplay.");
            return;
        }
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setDeviceId(this.f3382e);
        connectRequest.setConnectType(2);
        connectRequest.setUserRequest(true);
        com.byd.cpaa.carplay.CarPlayManager.getInstance(this.mContext).directConnectCarPlay(connectRequest);
    }

    public String d() {
        return this.f3382e;
    }

    public boolean e() {
        return this.f3381d;
    }

    public void f(String str) {
        this.f3382e = str;
    }

    public void g(boolean z7) {
        this.f3381d = z7;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_confirm);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_cancel);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_check);
        imageView.setImageResource(this.f3381d ? R.mipmap.check_pitch_regular : R.mipmap.check_normal_regular);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_cast_data_protection_notice_right_rudder : R.layout.dialog_cast_data_protection_notice;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.iv_check) {
            if (this.f3381d) {
                ((ImageView) view).setImageResource(R.mipmap.check_normal_regular);
            } else {
                ((ImageView) view).setImageResource(R.mipmap.check_pitch_regular);
            }
            this.f3381d = !this.f3381d;
            return;
        }
        if (id == R.id.tv_cancel) {
            dismiss();
            return;
        }
        if (id != R.id.tv_confirm) {
            return;
        }
        if (this.f3381d) {
            String strF = k.f(this.mContext, "cp_not_protection_device", "");
            k.k(this.mContext, "cp_not_protection_device", strF + this.f3382e + "-");
        }
        c();
        dismiss();
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
