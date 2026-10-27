package com.byd.systemsettings.service;

import android.bluetooth.BluetoothDevice;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.UserHandle;
import com.byd.mvp.service.BydIntentService;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import m4.d;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class ReceiverActionService extends BydIntentService {
    private static final String TAG = "ReceiverActionService";

    public ReceiverActionService() {
        super(TAG);
    }

    private void onReceivedBtPairRequest(final Intent intent) {
        a.a(TAG, "onReceivedBtPairRequest", "---device: " + d.c((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")));
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.service.ReceiverActionService.2
            @Override // java.lang.Runnable
            public void run() {
                SystemDialog.showBtPairCode(ReceiverActionService.this.getApplicationContext(), intent, false);
            }
        });
    }

    public static void startBtAction(final Context context, final Intent intent) {
        a.c(TAG, "--startBtAction--action: " + intent.getAction());
        g.a().g(new Runnable() { // from class: com.byd.systemsettings.service.ReceiverActionService.1
            @Override // java.lang.Runnable
            public void run() {
                Parcel parcelObtain = Parcel.obtain();
                intent.setComponent(new ComponentName(context, (Class<?>) ReceiverActionService.class));
                m4.a.d(context, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, new Object[]{intent, UserHandle.readFromParcel(parcelObtain)});
                parcelObtain.recycle();
                a.e(ReceiverActionService.TAG, "---startWifiAction--start service completed. threadName: " + Thread.currentThread().getName() + ", action: " + intent.getAction());
            }
        });
    }

    @Override // com.byd.mvp.service.BydIntentService
    public void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        action.hashCode();
        if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) {
            onReceivedBtPairRequest(intent);
        }
    }
}
