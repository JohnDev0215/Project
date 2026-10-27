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
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import g5.c;
import g5.d;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CarPlayWirelessDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3375e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3376f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public BroadcastReceiver f3377g;

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
                if (string.equals(CarPlayWirelessDialog.this.f3375e) || string.contains(CarPlayWirelessDialog.this.f3375e)) {
                    CarPlayWirelessDialog.this.dismiss();
                }
            }
        }
    }

    public CarPlayWirelessDialog(Context context) {
        super(context);
        this.f3374d = null;
        this.f3375e = null;
        this.f3376f = null;
        this.f3377g = new a();
        this.mContext = context;
    }

    public String d() {
        return this.f3374d;
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        a1.a.b(this.mContext).e(this.f3377g);
    }

    public void e(String str) {
        this.f3376f = str;
    }

    public void f(String str) {
        this.f3374d = str;
    }

    public void g(String str) {
        this.f3375e = str;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_enable);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_charge);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_title);
        String str = this.f3374d;
        if (str != null) {
            if (str.length() > 10) {
                textView3.setText(String.format(this.mContext.getString(R.string.cp_wireless_title), this.f3374d.substring(0, 10) + "..."));
            } else {
                textView3.setText(String.format(this.mContext.getString(R.string.cp_wireless_title), this.f3374d));
            }
        }
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
        IntentFilter intentFilter = new IntentFilter("cpaa_usb_device_detach");
        intentFilter.addAction("action_cp_connecting");
        a1.a.b(this.mContext).c(this.f3377g, intentFilter);
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_car_play_wireless_right_rudder : R.layout.dialog_car_play_wireless;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_enable) {
            if (this.f3376f == null) {
                d.c(this.mContext);
                setStartNewUI(true);
            } else if (!CarPlayManager.getInstance(this.mContext).isUSBNeedConnectCarPlay()) {
                CarPlayManager.getInstance(this.mContext).setUSBNeedConnectCarPlay(true);
            } else if (!TextUtils.isEmpty(this.f3376f)) {
                if (CarPlayManager.getInstance(this.mContext).isCarPlayConnected() || AndroidAutoManager.getInstance(this.mContext).isAndroidAutoConnected()) {
                    g5.a.i("USB show switch dialog.");
                    CastSwitchDeviceDialog castSwitchDeviceDialog = new CastSwitchDeviceDialog(this.mContext);
                    castSwitchDeviceDialog.l(0);
                    castSwitchDeviceDialog.k(this.f3376f);
                    castSwitchDeviceDialog.show();
                } else {
                    g5.a.i("USB connect CarPlay directly.");
                    ConnectRequest connectRequest = new ConnectRequest();
                    connectRequest.setDeviceId(this.f3376f);
                    connectRequest.setConnectType(2);
                    connectRequest.setUserRequest(true);
                    CarPlayManager.getInstance(this.mContext).connectCarPlay(connectRequest);
                    CarPlayManager.getInstance(this.mContext).setUSBNeedConnectCarPlay(false);
                }
            }
        }
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
