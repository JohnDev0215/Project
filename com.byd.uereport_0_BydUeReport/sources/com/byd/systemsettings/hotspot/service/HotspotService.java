package com.byd.systemsettings.hotspot.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.UserHandle;
import com.byd.bt.model.EnableState;
import com.byd.mvp.service.BydIntentService;
import com.byd.systemsettings.hotspot.cache.ApCache;
import com.byd.systemsettings.hotspot.callback.ApEnableStateCallback;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.common.WifiAP;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class HotspotService extends BydIntentService {
    private static final String TAG = "HotspotService";

    public HotspotService() {
        super(TAG);
    }

    private void onHotspotStateChanged(Intent intent) {
        ApEnableStateCallback apEnableStateCallback = (ApEnableStateCallback) ApCache.get(ApCache.HOTSPOT_STATE);
        if (apEnableStateCallback == null) {
        }
        int intExtra = intent.getIntExtra(WifiCache.WIFI_STATE, -1);
        a.a(TAG, "onHotspotStateChanged", ", state = " + intExtra);
        switch (intExtra) {
            case 10:
                apEnableStateCallback.onEnableStateChanged(EnableState.DISABLING);
                break;
            case 11:
                apEnableStateCallback.onEnableStateChanged(EnableState.DISABLE);
                break;
            case 12:
                apEnableStateCallback.onEnableStateChanged(EnableState.ENABLING);
                break;
            case 13:
                apEnableStateCallback.onEnableStateChanged(EnableState.ENABLE);
                break;
            case 14:
                apEnableStateCallback.onEnableStateChanged(EnableState.ERROR);
                break;
        }
    }

    public static void startHotspotAction(final Context context, final Intent intent) {
        a.a(TAG, "--startHotspotAction--" + intent.getAction());
        g.a().g(new Runnable() { // from class: com.byd.systemsettings.hotspot.service.HotspotService.1
            @Override // java.lang.Runnable
            public void run() {
                Parcel parcelObtain = Parcel.obtain();
                intent.setComponent(new ComponentName(context, (Class<?>) HotspotService.class));
                m4.a.d(context, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, new Object[]{intent, UserHandle.readFromParcel(parcelObtain)});
                parcelObtain.recycle();
                a.e(HotspotService.TAG, "---startHotspotAction--start service completed. threadName: " + Thread.currentThread().getName() + ", action: " + intent.getAction());
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
        if (action.equals(WifiAP.WIFI_AP_STATE_CHANGED_ACTION)) {
            onHotspotStateChanged(intent);
        }
    }
}
