package com.byd.systemsettings.hotspot;

import android.content.Context;
import android.net.wifi.WifiClient;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.hotspot.HotspotManager;
import com.byd.systemsettings.hotspot.callback.ApEnableStateCallback;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import java.lang.ref.WeakReference;
import java.util.List;
import x3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class PerHotspotPresenter extends f<PerHotspotPager> {
    public static final int ARG1_AP_CLIENTS_CHANGED = 16;
    public static final int ARG1_AP_ENABLE_STATE = 1;
    private static final Class<?> CLASS = PerHotspotPresenter.class;
    private static final String TAG = "PerHotspotPresenter";
    private int handlerWhat = 0;
    private final HotspotManager hm = HotspotManager.Singleton.get();
    private Context mContext;

    public class HotspotSoftApCallback implements HotspotManager.BydSoftApCallback {
        private final WeakReference<PerHotspotPresenter> ref;

        public HotspotSoftApCallback(PerHotspotPresenter perHotspotPresenter) {
            this.ref = new WeakReference<>(perHotspotPresenter);
        }

        @Override // com.byd.systemsettings.hotspot.HotspotManager.BydSoftApCallback
        public void onBlockedClientConnecting(WifiClient wifiClient, int i8) {
            a.a(PerHotspotPresenter.TAG, "---onBlockedClientConnecting---client = " + wifiClient + ", blockedReason: " + i8);
        }

        @Override // com.byd.systemsettings.hotspot.HotspotManager.BydSoftApCallback
        public void onConnectedClientsChanged(List<WifiClient> list) {
            a.a(PerHotspotPresenter.TAG, "---onConnectedClientsChanged---clients = " + list);
            WeakReference<PerHotspotPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            this.ref.get().sendMessage(16, list);
        }

        @Override // com.byd.systemsettings.hotspot.HotspotManager.BydSoftApCallback
        public void onStateChanged(int i8, int i9) {
            a.a(PerHotspotPresenter.TAG, "---onStateChanged---state = " + i8);
            WeakReference<PerHotspotPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
            }
        }
    }

    public class HotspotStateChangedCallback implements ApEnableStateCallback {
        private final WeakReference<PerHotspotPresenter> ref;

        public HotspotStateChangedCallback(PerHotspotPresenter perHotspotPresenter) {
            this.ref = new WeakReference<>(perHotspotPresenter);
        }

        @Override // com.byd.systemsettings.hotspot.callback.ApEnableStateCallback
        public void onEnableStateChanged(EnableState enableState) {
            a.a(PerHotspotPresenter.TAG, "---onEnableStateChanged---state = " + enableState);
            WeakReference<PerHotspotPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            this.ref.get().sendMessage(1, enableState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int i8, Object obj) {
        if (getV() != null) {
            getV().sendMessage(this.handlerWhat, i8, obj);
        }
    }

    public void createHotspot() {
        this.hm.setSoftApConfiguration();
    }

    public int getApSecurityType() {
        return this.hm.getApSecurityType();
    }

    public HotspotParams getDefHotspotParams() {
        return this.hm.getDefHotspotParams();
    }

    public int getHotspotApBand() {
        return this.hm.getHotspotApBand();
    }

    public int getHotspotConnectedCount() {
        return this.hm.getConnectedCount();
    }

    public int getHotspotFlowLimit() {
        return this.hm.getHotspotFlowLimit();
    }

    public String getHotspotName() {
        return this.hm.getHotspotName();
    }

    public String getHotspotPwd() {
        return this.hm.getHotspotPwd();
    }

    public void init(Context context, int i8) {
        a.a(TAG, "---init---");
        this.handlerWhat = i8;
        this.mContext = context;
        this.hm.init(context);
        HotspotManager hotspotManager = this.hm;
        Class<?> cls = CLASS;
        hotspotManager.registerHotspotStateCallback(cls.getName(), new HotspotStateChangedCallback(this));
        this.hm.registerSoftApCallback(cls.getName(), new HotspotSoftApCallback(this));
    }

    public boolean isHotspotEnable() {
        return this.hm.isHotspotEnable();
    }

    public void restartHotspot() {
        this.hm.restartHotspot(this.mContext);
    }

    public void setApSecurityType(int i8) {
        this.hm.setApSecurityType(i8);
    }

    public void setHotspotApBand(int i8) {
        this.hm.setHotspotApBand(i8);
    }

    public void setHotspotEnable(boolean z7) {
        this.hm.setHotspotEnable(this.mContext, z7);
    }

    public void setHotspotFlowLimit(int i8) {
        this.hm.setHotspotFlowLimit(i8);
    }

    public void setHotspotName(String str) {
        this.hm.setHotspotName(str);
    }

    public void setHotspotPwd(String str) {
        this.hm.setHotspotPwd(str);
    }

    @Override // z3.f
    public void unbindV() {
        super.unbindV();
        HotspotManager hotspotManager = this.hm;
        if (hotspotManager != null) {
            Class<?> cls = CLASS;
            hotspotManager.unregisterHotspotStateCallback(cls.getName());
            this.hm.unregisterSoftApCallback(cls.getName());
        }
    }
}
