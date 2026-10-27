package com.byd.uereport.dialog.screencast;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import g5.c;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes2.dex */
public class CarPlayStartDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3368d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3369e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3370f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3371g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public BroadcastReceiver f3372h;

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            g5.a.i("mWirelessDialogReceiver action:" + intent.getAction());
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String string = extras.getString("device");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                if (string.equals(CarPlayStartDialog.this.f3370f) || string.contains(CarPlayStartDialog.this.f3370f)) {
                    CarPlayStartDialog.this.dismiss();
                }
            }
        }
    }

    public CarPlayStartDialog(Context context) {
        super(context);
        this.f3368d = null;
        this.f3369e = null;
        this.f3370f = null;
        this.f3372h = new a();
    }

    public final void d() {
        if (this.f3368d == null) {
            return;
        }
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setDeviceId(this.f3368d);
        connectRequest.setConnectType(2);
        connectRequest.setUserRequest(true);
        CarPlayManager.getInstance(this.mContext).connectCarPlay(connectRequest);
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a1.a.b(this.mContext).e(this.f3372h);
    }

    public String e() {
        return this.f3368d;
    }

    public String f() {
        return this.f3370f;
    }

    public boolean g() {
        return this.f3371g;
    }

    public String getDeviceName() {
        return this.f3369e;
    }

    public void h(String str) {
        this.f3368d = str;
    }

    public void i(boolean z7) {
        this.f3371g = z7;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    @SuppressLint({"StringFormatInvalid"})
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_confirm);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_cancel);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_content);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_button);
        if (c.e() == 1) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        String string = this.mContext.getString(R.string.cp_enable_content_tip);
        if (!TextUtils.isEmpty(this.f3369e)) {
            if (this.f3369e.length() > 10) {
                string = String.format(this.mContext.getString(R.string.cp_enable_content_tip), this.f3369e.substring(0, 10) + "...");
            } else {
                string = String.format(this.mContext.getString(R.string.cp_enable_content_tip), this.f3369e);
            }
        }
        textView3.setText(string + BydAdbHelper.COMMAND_LINE_END + this.mContext.getString(R.string.cp_data_protection_notice_content));
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
        IntentFilter intentFilter = new IntentFilter("cpaa_usb_device_detach");
        intentFilter.addAction("action_cp_connecting");
        a1.a.b(this.mContext).c(this.f3372h, intentFilter);
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_car_play_start;
    }

    public void j(String str) {
        this.f3370f = str;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_confirm) {
            if (!this.f3371g) {
                d();
            } else if (!CarPlayManager.getInstance(this.mContext).isUSBNeedConnectCarPlay()) {
                CarPlayManager.getInstance(this.mContext).setUSBNeedConnectCarPlay(true);
            } else if (!TextUtils.isEmpty(this.f3368d)) {
                if (CarPlayManager.getInstance(this.mContext).isCarPlayConnected() || AndroidAutoManager.getInstance(this.mContext).isAndroidAutoConnected()) {
                    g5.a.i("USB show switch dialog.");
                    CastSwitchDeviceDialog castSwitchDeviceDialog = new CastSwitchDeviceDialog(this.mContext);
                    castSwitchDeviceDialog.l(0);
                    castSwitchDeviceDialog.k(this.f3368d);
                    setStartNewUI(true);
                    castSwitchDeviceDialog.show();
                } else {
                    g5.a.i("USB connect CarPlay directly.");
                    d();
                    CarPlayManager.getInstance(this.mContext).setUSBNeedConnectCarPlay(false);
                }
            }
        }
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    public void setDeviceName(String str) {
        this.f3369e = str;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
