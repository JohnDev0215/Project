package com.byd.uereport.dialog.screencast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.systemsettings.btsetting.BtSingleConnection;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import h3.c;
import java.util.Set;
import ts.car.service.androidauto.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes2.dex */
public class CastSwitchDeviceDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3383d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3384e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3385f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final BroadcastReceiver f3386g;

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
                if (string.equals(CastSwitchDeviceDialog.this.f3383d) || string.contains(CastSwitchDeviceDialog.this.f3383d)) {
                    CastSwitchDeviceDialog.this.dismiss();
                }
            }
        }
    }

    public CastSwitchDeviceDialog(Context context) {
        super(context);
        this.f3385f = -1;
        this.f3386g = new a();
    }

    public final void d() {
        if (this.f3383d == null) {
            return;
        }
        c.a.a().a(this.mContext);
        int i8 = this.f3384e;
        if (i8 == 0) {
            j();
        } else if (i8 == 1) {
            i();
        }
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        h();
        a1.a.b(this.mContext).e(this.f3386g);
    }

    public final void e() {
        AndroidAutoDevice connectedAndroidAutoDevice = AndroidAutoManager.getInstance(getContext()).getConnectedAndroidAutoDevice();
        if (connectedAndroidAutoDevice == null || q2.c.b(connectedAndroidAutoDevice.getBtAddr())) {
            return;
        }
        Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        String btAddr = connectedAndroidAutoDevice.getBtAddr();
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (btAddr.equals(bluetoothDevice.getAddress())) {
                BtSingleConnection.enableAndroidAutoHfp(bluetoothDevice, true);
                return;
            }
        }
    }

    public String f() {
        return this.f3383d;
    }

    public int g() {
        return this.f3384e;
    }

    public final void h() {
        Intent intent = new Intent("android_auto_new_device_pair");
        intent.putExtra("op", this.f3385f);
        this.mContext.sendBroadcast(intent);
    }

    public final void i() {
        Intent intent = new Intent("carsettings_device_connect_androidauto_action");
        intent.setPackage(this.mContext.getPackageName());
        intent.putExtra("deviceId", this.f3383d);
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_yes);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_no);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_switch_tip);
        int i8 = this.f3384e;
        if (i8 == 1) {
            textView3.setText(this.mContext.getString(R.string.aa_switch_device_tip));
        } else if (i8 == 0) {
            textView3.setText(this.mContext.getString(R.string.cp_switch_device_tip));
        }
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
        a1.a.b(this.mContext).c(this.f3386g, new IntentFilter("cpaa_usb_device_detach"));
    }

    @Override // b5.g
    public int initLayoutRes() {
        return g5.c.e() == 1 ? R.layout.dialog_cast_swtich_device_tip_right_rudder : R.layout.dialog_cast_swtich_device_tip;
    }

    public final void j() {
        Intent intent = new Intent("carsettings_device_connect_carplay_action");
        intent.setPackage(this.mContext.getPackageName());
        intent.putExtra("deviceId", this.f3383d);
        this.mContext.sendBroadcast(intent);
    }

    public void k(String str) {
        this.f3383d = str;
    }

    public void l(int i8) {
        this.f3384e = i8;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.ll_dialog_masks) {
            this.f3385f = -1;
            e();
        } else if (id == R.id.tv_no) {
            this.f3385f = 0;
            e();
        } else if (id == R.id.tv_yes) {
            this.f3385f = 1;
            d();
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
