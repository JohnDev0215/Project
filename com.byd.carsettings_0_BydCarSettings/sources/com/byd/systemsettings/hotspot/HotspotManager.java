package com.byd.systemsettings.hotspot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.SoftApCapability;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.SoftApInfo;
import android.net.wifi.WifiClient;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import b.b.m.c.a;
import b.b.m.g.f;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.hotspot.cache.ApCache;
import com.byd.systemsettings.hotspot.callback.ApEnableStateCallback;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.hotspot.receiver.HotspotReceiver;
import com.byd.systemsettings.wifi2.common.WifiAP;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class HotspotManager {
    private static final String AP_CONNECTED_COUNT = "ap_connected_count";
    private static final String AP_FLOW_LIMITED = "ap_flow_limited";
    private static final int CHANNEL_153 = 153;
    private static final int DEF_AP_BAND = 2;
    private static final int DEF_AP_FLOW_LIMIT = -1;
    private static final boolean DEF_AP_IS_PWD_HIDE = true;
    private static final int DEF_AP_SECURITY_TYPE = 1;
    private static final String KEY_HOTSPOT_PARAMS = "hotspot_params";
    private static final String TAG = "HotspotManager";
    public static final int WIFI_AP_STATE_DISABLED = 11;
    public static final int WIFI_AP_STATE_DISABLING = 10;
    public static final int WIFI_AP_STATE_ENABLED = 13;
    public static final int WIFI_AP_STATE_ENABLING = 12;
    public static final int WIFI_AP_STATE_FAILED = 14;
    private BydHotspotCallback apCallback;
    private List<WifiClient> apClients;
    private ConcurrentHashMap<String, ApEnableStateCallback> apStateCallbackMap;
    private HotspotParams curParams;
    private Context mContext;
    private Handler mHandler;
    private HotspotReceiver mReceiver;
    private WifiManager mWifiManager;
    private SharedPreferences spf;
    private final ConcurrentHashMap<String, BydSoftApCallback> apCallbackMap = new ConcurrentHashMap<>();
    public int flowLimited = 0;
    private volatile int apConnectedCount = 0;
    private volatile boolean isInited = false;
    private volatile EnableState preState = null;

    public class BydHotspotCallback implements WifiManager.SoftApCallback {
        private final WeakReference<HotspotManager> ref;

        public BydHotspotCallback(HotspotManager hotspotManager) {
            this.ref = new WeakReference<>(hotspotManager);
        }

        public void onBlockedClientConnecting(WifiClient wifiClient, int i) {
            a.a(HotspotManager.TAG, "---onBlockedClientConnecting---client = " + wifiClient + ", blockedReason = " + i);
        }

        public void onCapabilityChanged(SoftApCapability softApCapability) {
            a.a(HotspotManager.TAG, "---onCapabilityChanged---softApCapability = " + softApCapability);
        }

        public void onConnectedClientsChanged(List<WifiClient> list) {
            a.a(HotspotManager.TAG, "---onConnectedClientsChanged---clients = " + list);
            WeakReference<HotspotManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onApConnectedClientsChanged(list);
        }

        public void onInfoChanged(SoftApInfo softApInfo) {
            a.a(HotspotManager.TAG, "---onInfoChanged---softApInfo = " + softApInfo);
        }

        public void onStateChanged(int i, int i2) {
            a.a(HotspotManager.TAG, "---onStateChanged---state = " + i + ", failureReason = " + i2);
            WeakReference<HotspotManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onStateChanged(i, i2);
        }
    }

    public interface BydSoftApCallback {
        void onBlockedClientConnecting(WifiClient wifiClient, int i);

        default void onCapabilityChanged(SoftApCapability softApCapability) {
        }

        void onConnectedClientsChanged(List<WifiClient> list);

        default void onInfoChanged(SoftApInfo softApInfo) {
        }

        void onStateChanged(int i, int i2);
    }

    public class HotspotHandler extends Handler {
        public HotspotHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
        }
    }

    public class HotspotStateCallback implements ApEnableStateCallback {
        private final WeakReference<HotspotManager> ref;

        public HotspotStateCallback(HotspotManager hotspotManager) {
            this.ref = new WeakReference<>(hotspotManager);
        }

        @Override // com.byd.systemsettings.hotspot.callback.ApEnableStateCallback
        public void onEnableStateChanged(EnableState enableState) {
            WeakReference<HotspotManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onAPEnableStateCchanged(enableState);
        }
    }

    public static class Singleton {
        private static final HotspotManager instance = new HotspotManager();

        public static HotspotManager get() {
            return instance;
        }
    }

    private int getCorrectBand(int i) {
        int i2 = 1;
        if (i != 1) {
            i2 = 4;
            if (i != 4) {
                return 2;
            }
        }
        return i2;
    }

    private Class<?> getTetheringManagerCallbackClass() {
        try {
            return Class.forName("android.net.TetheringManager$StartTetheringCallback");
        } catch (ClassNotFoundException e2) {
            a.c(TAG, ", getTetheringManagerCallbackClass error: " + e2.toString());
            return null;
        }
    }

    private void initHotspotParams(Context context) {
        if (context == null) {
            return;
        }
        this.spf = context.getSharedPreferences(KEY_HOTSPOT_PARAMS, 0);
        if (this.curParams == null) {
            this.curParams = new HotspotParams();
        }
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        int i = this.spf.getInt(AP_FLOW_LIMITED, -1);
        this.curParams.setName(softApConfiguration.getSsid());
        this.curParams.setPwd(softApConfiguration.getPassphrase());
        this.curParams.setApSecurityType(softApConfiguration.getSecurityType());
        this.curParams.setApBand(getCorrectBand(softApConfiguration.getBand()));
        this.curParams.setFlowLimited(i);
        this.apConnectedCount = this.spf.getInt(AP_CONNECTED_COUNT, 0);
        a.a(TAG, "initHotspotParams", " flowLimited = " + i);
        a.a(TAG, "initHotspotParams", " curParams = " + this.curParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onApConnectedClientsChanged(List<WifiClient> list) {
        a.a(TAG, "---onApConnectedClientsChanged---clients = " + list);
        this.apClients = list;
        this.apConnectedCount = list.size();
        saveConnectedConut(this.apConnectedCount);
        ConcurrentHashMap<String, BydSoftApCallback> concurrentHashMap = this.apCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<BydSoftApCallback> it = this.apCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectedClientsChanged(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStateChanged(int i, int i2) {
        Iterator<BydSoftApCallback> it = this.apCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(i, i2);
        }
        switch (i) {
            case 10:
                onAPEnableStateCchanged(EnableState.DISABLING);
                break;
            case 11:
                onAPEnableStateCchanged(EnableState.DISABLE);
                break;
            case 12:
                onAPEnableStateCchanged(EnableState.ENABLING);
                break;
            case 13:
                onAPEnableStateCchanged(EnableState.ENABLE);
                break;
        }
    }

    private void registerReceiver(Context context) {
        if (context == null) {
            return;
        }
        a.a(TAG, "---registerReceiver---");
        this.mReceiver = new HotspotReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiAP.WIFI_AP_STATE_CHANGED_ACTION);
        context.registerReceiver(this.mReceiver, intentFilter);
    }

    private void registerSoftApCallback() {
        a.a(TAG, ", registerSoftApCallback------");
        if (this.apCallback == null) {
            this.apCallback = new BydHotspotCallback(this);
        }
        this.mWifiManager.registerSoftApCallback(new Executor() { // from class: com.byd.systemsettings.hotspot.HotspotManager.1
            @Override // java.util.concurrent.Executor
            public void execute(Runnable runnable) {
                runnable.run();
                a.a(HotspotManager.TAG, ", registerSoftApCallback----execute--");
            }
        }, this.apCallback);
    }

    private void saveConnectedConut(int i) {
        a.a(TAG, "---saveConnectedConut---spf = " + this.spf);
        SharedPreferences sharedPreferences = this.spf;
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(AP_CONNECTED_COUNT, i);
        editorEdit.commit();
    }

    private void unregisterReceiver() {
        if (this.mContext == null) {
            return;
        }
        a.a(TAG, "---unregisterReceiver---");
        try {
            this.mContext.unregisterReceiver(this.mReceiver);
        } catch (RuntimeException e2) {
            a.a(TAG, "e = " + e2.toString());
        }
    }

    public List<WifiClient> getApClients() {
        return this.apClients;
    }

    public int getApSecurityType() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        a.a(TAG, "---getApSecurityType---getSecurityType: " + softApConfiguration.getSecurityType());
        return softApConfiguration.getSecurityType();
    }

    public int getConnectedCount() {
        if (isHotspotEnable()) {
            return this.apConnectedCount;
        }
        return 0;
    }

    public HotspotParams getDefHotspotParams() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        this.curParams.setName(softApConfiguration.getSsid());
        this.curParams.setPwd(softApConfiguration.getPassphrase());
        this.curParams.setApSecurityType(softApConfiguration.getSecurityType());
        this.curParams.setApBand(getCorrectBand(softApConfiguration.getBand()));
        return this.curParams;
    }

    public int getHotspotApBand() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        a.a(TAG, "---getHotspotApBand----getBand: " + softApConfiguration.getBand());
        return getCorrectBand(softApConfiguration.getBand());
    }

    public int getHotspotFlowLimit() {
        return this.curParams.getFlowLimited();
    }

    public String getHotspotName() {
        SoftApConfiguration softApConfiguration = this.mWifiManager.getSoftApConfiguration();
        a.a(TAG, "---getHotspotName---getSsid: " + f.g(softApConfiguration.getSsid()));
        return softApConfiguration.getSsid();
    }

    public String getHotspotPwd() {
        return this.mWifiManager.getSoftApConfiguration().getPassphrase();
    }

    public void init(Context context) {
        if (context == null || this.isInited) {
            return;
        }
        this.mContext = context;
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        ApCache.put(ApCache.HOTSPOT_STATE, new HotspotStateCallback(this));
        registerSoftApCallback();
        initHotspotParams(context.getApplicationContext());
        registerReceiver(context);
        if (this.apStateCallbackMap == null) {
            this.apStateCallbackMap = new ConcurrentHashMap<>();
        }
        this.isInited = true;
    }

    public boolean isHotspotEnable() {
        a.a(TAG, "---isHotspotEnable---");
        try {
            Method declaredMethod = this.mWifiManager.getClass().getDeclaredMethod("isWifiApEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(this.mWifiManager, new Object[0])).booleanValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            a.c(TAG, "e = " + e2.toString());
            return false;
        }
    }

    public void onAPEnableStateCchanged(EnableState enableState) {
        a.a(TAG, "--onAPEnableStateCchanged--state = " + enableState);
        if (enableState.equals(this.preState)) {
            return;
        }
        this.preState = enableState;
        ConcurrentHashMap<String, ApEnableStateCallback> concurrentHashMap = this.apStateCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<ApEnableStateCallback> it = this.apStateCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onEnableStateChanged(enableState);
        }
    }

    public void registerHotspotStateCallback(String str, ApEnableStateCallback apEnableStateCallback) {
        ConcurrentHashMap<String, ApEnableStateCallback> concurrentHashMap = this.apStateCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, apEnableStateCallback);
    }

    public void release() {
        a.a(TAG, "---release---");
        saveConnectedConut(this.apConnectedCount);
        ApCache.clear();
        unregisterReceiver();
        unregisterSoftApCallback();
        this.apCallback = null;
        ConcurrentHashMap<String, BydSoftApCallback> concurrentHashMap = this.apCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, ApEnableStateCallback> concurrentHashMap2 = this.apStateCallbackMap;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.mHandler = null;
        this.isInited = false;
    }

    public void resetConfiguration(Context context) {
        if (this.curParams == null) {
            this.curParams = new HotspotParams();
        }
        String str = SystemProperties.get("persist.wifi.apconfiguration.default.type");
        a.a(TAG, "resetConfiguration", ", Rest Hotspot Config: " + str);
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = str.split(" ");
            if (strArrSplit.length >= 4) {
                int i = Integer.parseInt(strArrSplit[0]);
                a.a(TAG, "resetConfiguration", ", band: " + i);
                setHotspotApBand(i);
                setHotspotName(strArrSplit[1]);
                setHotspotPwd(strArrSplit[2]);
                setApSecurityType(Integer.parseInt(strArrSplit[3]));
            }
        }
        setSoftApConfiguration();
    }

    public void restartHotspot(final Context context) {
        a.a(TAG, "---restartHotspot-----do disable---mHandler :" + this.mHandler);
        setHotspotEnable(context, false);
        if (this.mHandler == null) {
            this.mHandler = new Handler();
        }
        this.mHandler.postDelayed(new Runnable() { // from class: com.byd.systemsettings.hotspot.HotspotManager.2
            @Override // java.lang.Runnable
            public void run() {
                a.a(HotspotManager.TAG, "---restartHotspot-----do enable---");
                HotspotManager.this.setHotspotEnable(context, true);
            }
        }, 1000L);
    }

    public void setApSecurityType(int i) {
        a.a(TAG, "---setApSecurityType----type: " + i);
        this.curParams.setApSecurityType(i);
    }

    public void setHotspotApBand(int i) {
        a.a(TAG, "apBand = " + i);
        this.curParams.setApBand(getCorrectBand(i));
    }

    public void setHotspotEnable(Context context, boolean z) {
        HotspotParams hotspotParams;
        a.a(TAG, "---setHotspotEnable---enable = " + z);
        if (!z && (hotspotParams = this.curParams) != null) {
            hotspotParams.setPwdHide(true);
        }
        try {
            Class<?> cls = Class.forName("android.net.TetheringManager");
            Object systemService = context.getApplicationContext().getSystemService(cls);
            if (systemService == null) {
                return;
            }
            if (!z) {
                Method method = cls.getMethod("stopTethering", Integer.TYPE);
                method.setAccessible(true);
                method.invoke(systemService, 0);
            } else {
                setSoftApConfiguration();
                Method method2 = cls.getMethod("startTethering", Integer.TYPE, Executor.class, getTetheringManagerCallbackClass());
                method2.setAccessible(true);
                method2.invoke(systemService, 0, null, null);
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            a.a(TAG, "e: " + e2.toString());
        }
    }

    public void setHotspotFlowLimit(int i) {
        a.a(TAG, "value = " + i);
        this.curParams.setFlowLimited(i);
        SharedPreferences.Editor editorEdit = this.spf.edit();
        editorEdit.putInt(AP_FLOW_LIMITED, i);
        editorEdit.commit();
    }

    public void setHotspotName(String str) {
        a.a(TAG, "name = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.curParams.setName(str);
    }

    public void setHotspotPwd(String str) {
        this.curParams.setPwd(str);
    }

    @SuppressLint({"NewApi"})
    public boolean setSoftApConfiguration() {
        HotspotParams hotspotParams = this.curParams;
        a.a(TAG, "setSoftApConfiguration", ", params = " + this.curParams);
        SoftApConfiguration.Builder builder = new SoftApConfiguration.Builder(this.mWifiManager.getSoftApConfiguration());
        int apSecurityType = hotspotParams.getApSecurityType();
        String pwd = hotspotParams.getPwd();
        if (apSecurityType == 0) {
            pwd = null;
        }
        int correctBand = getCorrectBand(hotspotParams.getApBand());
        a.a(TAG, "setSoftApConfiguration", ", band = " + correctBand);
        builder.setBand(correctBand);
        builder.setSsid(hotspotParams.getName());
        if (!TextUtils.isEmpty(pwd)) {
            builder.setPassphrase(pwd, apSecurityType);
        }
        boolean softApConfiguration = this.mWifiManager.setSoftApConfiguration(builder.build());
        a.a(TAG, "setSoftApConfiguration", ", builder.build: " + builder.build());
        String[] strArr = new String[2];
        strArr[0] = "setSoftApConfiguration";
        StringBuilder sb = new StringBuilder();
        sb.append(", createHotspot: ");
        sb.append(softApConfiguration ? "Success." : "Failed.");
        strArr[1] = sb.toString();
        a.a(TAG, strArr);
        return softApConfiguration;
    }

    public void unregisterHotspotStateCallback(String str) {
        ConcurrentHashMap<String, ApEnableStateCallback> concurrentHashMap = this.apStateCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void unregisterSoftApCallback() {
        WifiManager wifiManager;
        a.a(TAG, ", unregisterSoftApCallback------");
        BydHotspotCallback bydHotspotCallback = this.apCallback;
        if (bydHotspotCallback == null || (wifiManager = this.mWifiManager) == null) {
            return;
        }
        wifiManager.unregisterSoftApCallback(bydHotspotCallback);
    }

    public void unregisterSoftApCallback(String str) {
        if (str == null) {
            return;
        }
        this.apCallbackMap.remove(str);
    }

    public void registerSoftApCallback(String str, BydSoftApCallback bydSoftApCallback) {
        if (str == null || bydSoftApCallback == null) {
            return;
        }
        this.apCallbackMap.put(str, bydSoftApCallback);
    }
}
