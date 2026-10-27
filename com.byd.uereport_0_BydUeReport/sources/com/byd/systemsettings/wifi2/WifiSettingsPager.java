package com.byd.systemsettings.wifi2;

import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Message;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.base.thread.Priority;
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.R;
import com.byd.systemsettings.system.SystemSettingsModule;
import com.byd.systemsettings.ui.cache.DialogStatesCache;
import com.byd.systemsettings.ui.cache.model.DialogState;
import com.byd.systemsettings.ui.cache.model.WifiDialogState;
import com.byd.systemsettings.ui.dialog.ServiceDialogHolder;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import com.byd.systemsettings.wifi2.PagerTimerManager;
import com.byd.systemsettings.wifi2.adapter.WifiListAdapter;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.callback.TimerTimeoutable;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.ConnectedWifiInfo;
import com.byd.systemsettings.wifi2.model.NetworkParams;
import com.byd.systemsettings.wifi2.model.SupplicantParams;
import com.byd.systemsettings.wifi2.model.WifiConnectParams;
import com.byd.systemsettings.wifi2.model.WifiPagerType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.util.ResUtils;
import com.byd.systemsettings.wifi2.util.SharedPrefUtils;
import com.byd.systemsettings.wifi2.view.pager.ConnectedWifiInfoPager;
import com.byd.systemsettings.wifi2.view.pager.NoWifiConnectionPager;
import com.byd.systemsettings.wifi2.view.pager.WifiAddNetworkPager;
import com.byd.systemsettings.wifi2.view.pager.WifiConnectedEmptyPager;
import com.byd.systemsettings.wifi2.view.pager.WifiConnectingPager;
import com.byd.systemsettings.wifi2.view.pager.WifiInfoPager;
import com.byd.systemsettings.wifi2.view.pager.WifiInputConnectPager;
import com.bydux.api.common.views.window.DampingScrollView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import k4.c;
import k4.e;
import p2.g;
import q2.b;
import q2.d;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class WifiSettingsPager extends a<WifiSettingsPresenter> implements e, c, TimerTimeoutable {
    public static final int ACTION_CONNECT = 1;
    public static final int ACTION_CONNECT_ADD_NET = 2;
    public static final String CUR_CONNECT_WIFI_SECURITY = "cur_connect_wifi_security";
    private static final long DELAY_ENABLE_AUTO_CONNECT = 10000;
    private static final int REQUEST_CODE_PERMISSION = 33;
    private static final String TAG_W = "WifiSettingsPager";
    private static final long TIMEOUT_CONNECT = 15000;
    private static final long TIMEOUT_CONNECT_ADD_NET = 10000;
    private static final int WHAT_ENABLE_AUTO_CONNECT = 817;
    private volatile WifiScanResult addNetInfo;
    private NetworkParams addNetworkParams;
    private BydSwitch bydSwitch;
    private boolean cancelConnect;
    private volatile int checkPosition;
    private volatile CipherType connectedCipherType;
    private volatile int connectedRssiLevel;
    private volatile WifiInfo connectingWifiInfo;
    private volatile BydData curBydData;
    private ConnectedWifiInfo curConnectWifiInfo;
    private volatile WifiScanResult curConnectedScanInfo;
    private volatile z3.e curPager;
    private volatile WifiPagerType curPagerType;
    private volatile WifiScanResult curScanInfo;
    private ConnectState currentConnectState;
    private volatile boolean doingConnect;
    private volatile boolean enableOperation;
    private FrameLayout flRight;
    private volatile boolean focusing;
    private volatile boolean isNetworkNotFound;
    private volatile boolean isScanning;
    private ImageView ivIconConnected;
    private ImageView ivRefresh;
    private View llClose;
    private View llConnect;
    private View llConnected;
    private View llOpen;
    private View llWifiInfo;
    private WifiListAdapter mAdapter;
    private OnPageBtnClickedCallback mBtnCallback;
    private View mLeftView;
    private volatile List<WifiScanResult> mScanList;
    private ConcurrentHashMap<WifiPagerType, a> pagerMap;
    private volatile BydData preBydData;
    private volatile SupplicantState preConnectState;
    private volatile ConnectState preConnectStatus;
    private volatile WifiPagerType prePagerType;
    private RecyclerView rcvWifi;
    private volatile boolean receiveAutoConnectResult;
    private View rlConnected;
    private ConcurrentHashMap<String, WifiScanResult> scanMap;
    private FrameLayout titleParent;
    private TextView tvConnectedName;
    private View vItemChecked;

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.WifiSettingsPager$11, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass11 {
        public static final /* synthetic */ int[] $SwitchMap$android$net$wifi$SupplicantState;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$ConnectState;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$bt$model$EnableState;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$mvp$callback$PagerBtnType;
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType;

        static {
            int[] iArr = new int[PagerBtnType.values().length];
            $SwitchMap$com$byd$mvp$callback$PagerBtnType = iArr;
            try {
                iArr[PagerBtnType.BOTTOM_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$mvp$callback$PagerBtnType[PagerBtnType.BOTTOM_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$mvp$callback$PagerBtnType[PagerBtnType.BOTTOM_MIDDLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SupplicantState.values().length];
            $SwitchMap$android$net$wifi$SupplicantState = iArr2;
            try {
                iArr2[SupplicantState.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.AUTHENTICATING.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.ASSOCIATING.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.ASSOCIATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.FOUR_WAY_HANDSHAKE.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.GROUP_HANDSHAKE.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[ConnectState.values().length];
            $SwitchMap$com$byd$bt$model$ConnectState = iArr3;
            try {
                iArr3[ConnectState.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$byd$bt$model$ConnectState[ConnectState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$byd$bt$model$ConnectState[ConnectState.DISCONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$byd$bt$model$ConnectState[ConnectState.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            int[] iArr4 = new int[WifiPagerType.values().length];
            $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType = iArr4;
            try {
                iArr4[WifiPagerType.NO_CONNECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[WifiPagerType.WIFI_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[WifiPagerType.INPUT_PWD.ordinal()] = 3;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[WifiPagerType.CONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[WifiPagerType.CONNECTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[WifiPagerType.CONNECTED_EMPTY.ordinal()] = 6;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[WifiPagerType.ADD_NETWORK.ordinal()] = 7;
            } catch (NoSuchFieldError unused20) {
            }
            int[] iArr5 = new int[EnableState.values().length];
            $SwitchMap$com$byd$bt$model$EnableState = iArr5;
            try {
                iArr5[EnableState.ENABLING.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$byd$bt$model$EnableState[EnableState.ENABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$byd$bt$model$EnableState[EnableState.DISABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$byd$bt$model$EnableState[EnableState.DISABLING.ordinal()] = 4;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    public class OnPageBtnClickedCallback implements w3.a {
        private final WeakReference<WifiSettingsPager> ref;

        public OnPageBtnClickedCallback(WifiSettingsPager wifiSettingsPager) {
            this.ref = new WeakReference<>(wifiSettingsPager);
        }

        @Override // w3.a
        public /* bridge */ /* synthetic */ void onBtnClicked(PagerBtnType pagerBtnType) {
            super.onBtnClicked(pagerBtnType);
        }

        @Override // w3.a
        public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
            super.onBtnClicked(cls, pagerBtnType);
        }

        @Override // w3.a
        public void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
            WeakReference<WifiSettingsPager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onPagerBtnClicked(cls, pagerBtnType, obj, obj2);
        }
    }

    public WifiSettingsPager(Context context) {
        super(context);
        this.isScanning = false;
        this.doingConnect = false;
        this.isNetworkNotFound = false;
        this.focusing = false;
        this.connectingWifiInfo = null;
        this.checkPosition = -1;
        this.receiveAutoConnectResult = false;
    }

    private void cancelAddNetConnectTimer() {
        x3.a.a(this.TAG, "---cancelAddNetConnectTimer---");
        PagerTimerManager.Singleton.get().removeTimer(getClass(), 2);
    }

    private void cancelConnectTimer() {
        x3.a.a(this.TAG, "---cancelConnectTimer---");
        PagerTimerManager.Singleton.get().removeTimer(getClass(), 1);
    }

    private void checkAddNetwork() {
        if (this.doingConnect) {
            d.b(getContext(), R.string.hint_muti_connect_operation);
            return;
        }
        showChecked(null);
        WifiPagerType wifiPagerType = this.curPagerType;
        WifiPagerType wifiPagerType2 = WifiPagerType.ADD_NETWORK;
        if (wifiPagerType == wifiPagerType2) {
            x3.a.a(TAG_W, "checkAddNetwork", "AddNetwork page is showing.");
        } else {
            showPageOnRight(wifiPagerType2, new NetworkParams());
        }
    }

    private void checkConnectedWifi() {
        showChecked(null);
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        if (connectedWifiInfo != null) {
            showPageOnRight(WifiPagerType.CONNECTED, connectedWifiInfo);
        }
    }

    private void checkItem(WifiScanResult wifiScanResult) {
        x3.a.a(TAG_W, "---checkItem---result: " + wifiScanResult);
        this.vItemChecked.setVisibility(wifiScanResult != null ? 8 : 0);
        for (WifiScanResult wifiScanResult2 : this.mScanList) {
            wifiScanResult2.setChecked(wifiScanResult2.equals(wifiScanResult));
        }
        updateRecyclerViewHeight();
    }

    private void doHintWpa2(int i8, boolean z7) {
        x3.a.a(TAG_W, ", ---doHintWpa2---errCode: " + i8 + ", notFound: " + z7);
        if (!z7) {
            if (i8 == 2 && this.doingConnect) {
                ((WifiSettingsPresenter) getP()).forgetNetwork(getApName(this.connectingWifiInfo));
            }
            onErrorReason(false, i8);
            return;
        }
        if (this.doingConnect) {
            d.b(getContext(), R.string.network_not_found_on_connecting);
            if (i8 == 2) {
                ((WifiSettingsPresenter) getP()).forgetNetwork(getApName(this.connectingWifiInfo));
            }
        }
        this.isNetworkNotFound = true;
    }

    private void doHintWpa3(int i8) {
        x3.a.a(TAG_W, ", ---doHintWpa3---errCode: " + i8);
        onErrorReason(true, i8);
    }

    private void doOnAutoConnecting(int i8, boolean z7) {
        x3.a.a(TAG_W, ", ---doOnAutoConnecting---errCode: " + i8 + ", notFound: " + z7);
    }

    private void doOnUserConnectCompleted() {
        x3.a.a(TAG_W, ", ---doOnUserConnectCompleted--Delay to reset autoConnectState, SSID: " + getWifiInfoText(this.connectingWifiInfo));
        postDelayed(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.9
            @Override // java.lang.Runnable
            public void run() {
                ((WifiSettingsPresenter) WifiSettingsPager.this.getP()).onUserConnectCompleted();
            }
        }, 817, 10000L);
    }

    private NetworkParams findAddNetworkParams(WifiPagerType wifiPagerType) {
        a aVar;
        BydData curData;
        if (wifiPagerType == WifiPagerType.ADD_NETWORK && (aVar = this.pagerMap.get(wifiPagerType)) != null && (curData = aVar.getCurData()) != null && (curData instanceof NetworkParams)) {
            return (NetworkParams) curData;
        }
        return null;
    }

    private String getApName(WifiInfo wifiInfo) {
        x3.a.a(TAG_W, ", ---getApName---info: " + wifiInfo);
        if (wifiInfo == null) {
            return "";
        }
        String ssid = wifiInfo.getSSID();
        return TextUtils.isEmpty(ssid) ? "" : (ssid.startsWith("\"") && ssid.endsWith("\"")) ? ssid.substring(1, ssid.length() - 1) : ssid;
    }

    private ConnectedWifiInfo getConnectedWifiInfo() {
        WifiInfo connectedWifiInfo = ((WifiSettingsPresenter) getP()).getConnectedWifiInfo();
        x3.a.a(TAG_W, "getConnectedWifiInfo", "info: " + getWifiInfoText(connectedWifiInfo));
        if (connectedWifiInfo == null) {
            return null;
        }
        if (this.curConnectWifiInfo == null) {
            this.curConnectWifiInfo = new ConnectedWifiInfo();
        }
        String connectedName = ((WifiSettingsPresenter) getP()).getConnectedName();
        this.curConnectWifiInfo.setName(connectedName);
        this.curConnectWifiInfo.setInfo(connectedWifiInfo);
        this.curConnectWifiInfo.setIpAddr(connectedWifiInfo.getIpAddress());
        if (this.curScanInfo != null && this.curScanInfo.getResult() != null && this.curScanInfo.getResult().BSSID.equals(connectedWifiInfo.getBSSID())) {
            this.curConnectedScanInfo = this.curScanInfo;
            ScanResult result = this.curScanInfo.getResult();
            x3.a.a(TAG_W, "getConnectedWifiInfo", "scanResult.SSI: " + m4.d.f(result.SSID));
            updateCurConnectWifiInfo(this.curConnectWifiInfo, result.capabilities);
        } else if (this.addNetInfo != null && this.addNetInfo.getName().equals(m4.d.i(connectedWifiInfo.getSSID()))) {
            x3.a.a(TAG_W, "getConnectedWifiInfo", "addNetInfo.SSID: " + m4.d.f(this.addNetInfo.getName()));
            WifiScanResult wifiScanResult = this.scanMap.get(this.addNetInfo.getName());
            x3.a.a(TAG_W, "getConnectedWifiInfo", "--2--target: " + wifiScanResult);
            if (wifiScanResult != null) {
                updateCurConnectWifiInfo(this.curConnectWifiInfo, wifiScanResult.getCapabilities());
            } else if (this.curConnectWifiInfo.isSecurityInvalid()) {
                this.curConnectWifiInfo.setCipherTypeAndSecurity(this.addNetInfo.getCipherType());
            } else {
                x3.a.a(TAG_W, "getConnectedWifiInfo", "curConnectWifiInfo is not invalid.");
            }
        } else if (connectedName != null && this.curScanInfo == null && this.addNetInfo == null) {
            WifiScanResult wifiScanResult2 = this.scanMap.get(connectedName);
            x3.a.a(TAG_W, "getConnectedWifiInfo", "--3--target: " + wifiScanResult2);
            if (wifiScanResult2 != null) {
                updateCurConnectWifiInfo(this.curConnectWifiInfo, wifiScanResult2.getCapabilities());
            }
        }
        x3.a.a(TAG_W, "getConnectedWifiInfo", "curConnectWifiInfo " + this.curConnectWifiInfo);
        return this.curConnectWifiInfo;
    }

    private a getPager(WifiPagerType wifiPagerType) {
        if (wifiPagerType == null) {
            return null;
        }
        a noWifiConnectionPager = this.pagerMap.get(wifiPagerType);
        if (noWifiConnectionPager != null) {
            return noWifiConnectionPager;
        }
        if (this.mBtnCallback == null) {
            this.mBtnCallback = new OnPageBtnClickedCallback(this);
        }
        switch (AnonymousClass11.$SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[wifiPagerType.ordinal()]) {
            case 1:
                noWifiConnectionPager = new NoWifiConnectionPager(getContext());
                break;
            case 2:
                noWifiConnectionPager = new WifiInfoPager(getContext());
                noWifiConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 3:
                noWifiConnectionPager = new WifiInputConnectPager(getContext());
                noWifiConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 4:
                noWifiConnectionPager = new WifiConnectingPager(getContext());
                noWifiConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 5:
                noWifiConnectionPager = new ConnectedWifiInfoPager(getContext());
                noWifiConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
            case 6:
                noWifiConnectionPager = new WifiConnectedEmptyPager(getContext());
                break;
            case 7:
                noWifiConnectionPager = new WifiAddNetworkPager(getContext());
                noWifiConnectionPager.setBtnClickedCallback(this.mBtnCallback);
                break;
        }
        if (noWifiConnectionPager != null) {
            this.pagerMap.put(wifiPagerType, noWifiConnectionPager);
        }
        return noWifiConnectionPager;
    }

    private String getWifiInfoText(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return "null";
        }
        return m4.d.f(wifiInfo.getSSID()) + ", mac: " + m4.d.d(wifiInfo.getBSSID());
    }

    private boolean isAutoConnecting() {
        if (this.curScanInfo == null && this.addNetInfo == null) {
            return true;
        }
        if (this.connectingWifiInfo == null) {
            return false;
        }
        x3.a.a(TAG_W, "isAutoConnecting", "curScanInfo: " + this.curScanInfo);
        x3.a.a(TAG_W, "isAutoConnecting", "connectingWifiInfo: " + getWifiInfoText(this.connectingWifiInfo));
        String mac = this.curScanInfo != null ? this.curScanInfo.getMac() : null;
        x3.a.a(TAG_W, "isAutoConnecting", "bssid: " + m4.d.f(mac));
        if (mac != null && mac.equals(this.connectingWifiInfo.getBSSID())) {
            x3.a.a(TAG_W, "isAutoConnecting", "false.");
            return false;
        }
        if (this.addNetInfo != null) {
            return !m4.d.i(this.connectingWifiInfo.getSSID()).equals(this.addNetInfo.getName());
        }
        return true;
    }

    private boolean isConnectingScanInfoWpa3() {
        if (this.curScanInfo == null) {
            return false;
        }
        CipherType cipherType = this.curScanInfo.getCipherType();
        return cipherType == CipherType.WPA3_PERSONAL || cipherType == CipherType.WPA3;
    }

    private boolean isTheSameInputPage(WifiScanResult wifiScanResult) {
        a pager;
        WifiScanResult wifiScanResult2;
        WifiPagerType wifiPagerType = this.curPagerType;
        WifiPagerType wifiPagerType2 = WifiPagerType.INPUT_PWD;
        if (wifiPagerType != wifiPagerType2 || wifiScanResult == null || wifiScanResult.getName() == null || (pager = getPager(wifiPagerType2)) == null || pager.getCurData() == null) {
            return false;
        }
        BydData curData = pager.getCurData();
        return (curData instanceof WifiScanResult) && (wifiScanResult2 = (WifiScanResult) curData) != null && wifiScanResult.getName().equals(wifiScanResult2.getName());
    }

    private void loadTitleOnLayoutLocked() {
        this.bydSwitch = (BydSwitch) this.mRootView.findViewById(R.id.switch_title_wireless);
        this.mRootView.findViewById(R.id.iv_back_wireless).setOnClickListener(this);
        this.bydSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z7) {
                WifiSettingsPager.this.openWireless(z7);
            }
        });
    }

    private void onAddNetPagerClicked(PagerBtnType pagerBtnType, NetworkParams networkParams) {
        x3.a.a(TAG_W, "onAddNetPagerClicked", ", type: " + pagerBtnType + ", params: " + networkParams);
        this.addNetworkParams = networkParams;
        int i8 = AnonymousClass11.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                return;
            }
            showPageOnCancelOperation();
            return;
        }
        this.doingConnect = true;
        this.curScanInfo = null;
        WifiScanResult wifiScanResult = new WifiScanResult();
        wifiScanResult.setName(networkParams.getName());
        wifiScanResult.setCipherType(networkParams.getType());
        this.addNetInfo = wifiScanResult;
        showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
        removeMessages(817);
        this.preBydData = networkParams;
        ((WifiSettingsPresenter) getP()).addNetwork(networkParams.getName(), networkParams.getPwd(), networkParams.getType());
    }

    private void onCipherTypeErr() {
        x3.a.a(TAG_W, "---onCipherTypeErr---");
        onErrorReason(false, 2);
        if (this.prePagerType == WifiPagerType.ADD_NETWORK) {
            showAddNetworkPager();
        }
        this.doingConnect = false;
    }

    private void onConnectFailed(int i8) {
        x3.a.a(TAG_W, "onConnectFailed", ", errCode: " + i8);
        if (i8 == -1) {
        }
    }

    private void onConnectedWifiInfoPagerClicked(PagerBtnType pagerBtnType, ConnectedWifiInfo connectedWifiInfo) {
        int i8 = AnonymousClass11.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                return;
            }
            showPageOnCancelOperation();
        } else {
            if (connectedWifiInfo == null || connectedWifiInfo.getInfo() == null) {
                return;
            }
            ((WifiSettingsPresenter) getP()).forgetNetwork(connectedWifiInfo.getInfo().getNetworkId());
            updateOnNoConnected();
            SharedPrefUtils.putSP(getContext(), CUR_CONNECT_WIFI_SECURITY, "");
        }
    }

    private void onConnectingPagerClicked(PagerBtnType pagerBtnType, WifiScanResult wifiScanResult) {
        if (AnonymousClass11.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()] != 3) {
            return;
        }
        this.cancelConnect = true;
        this.doingConnect = false;
        x3.a.a(TAG_W, "---onConnectingPagerClicked---type: " + pagerBtnType + ", prePagerType: " + this.prePagerType + ", result: " + wifiScanResult);
        if (this.prePagerType == WifiPagerType.ADD_NETWORK || wifiScanResult == null || !wifiScanResult.isSaved()) {
            showPageOnRight(this.prePagerType, this.preBydData);
        } else {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
        }
        cancelConnectTimer();
    }

    private void onErrorReason(boolean z7, int i8) {
        Context context;
        x3.a.a(TAG_W, "---onErrorReason---isWpa3: " + z7 + ", errCode: " + i8);
        if (this.doingConnect && (context = getContext()) != null) {
            x3.a.a(TAG_W, "---onErrorReason---do toast, errCode: " + i8);
            if (i8 != 0 && i8 != 1) {
                if (i8 == 2) {
                    if (z7) {
                        d.b(context.getApplicationContext(), R.string.connect_failed_on_connecting);
                        return;
                    } else {
                        d.b(context.getApplicationContext(), R.string.pwd_err_on_connecting);
                        return;
                    }
                }
                if (i8 != 3) {
                    return;
                }
            }
            d.b(context.getApplicationContext(), R.string.connect_failed_on_connecting);
        }
    }

    private void onInputConnectPagerClicked(PagerBtnType pagerBtnType, WifiScanResult wifiScanResult, String str) {
        int i8 = AnonymousClass11.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 1) {
            if (i8 != 2) {
                return;
            }
            showPageOnCancelOperation();
        } else {
            this.doingConnect = true;
            this.curScanInfo = wifiScanResult;
            this.addNetInfo = null;
            showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
            ((WifiSettingsPresenter) getP()).connect(wifiScanResult, str);
            this.enableOperation = false;
        }
    }

    private void onSupplicantStateChanged(SupplicantParams supplicantParams) {
        x3.a.a(TAG_W, "onSupplicantStateChanged_page", ", params: " + supplicantParams);
        if (supplicantParams == null || !SupplicantState.isValidState(supplicantParams.state)) {
            return;
        }
        SupplicantState supplicantState = supplicantParams.state;
        if (supplicantState != SupplicantState.DISCONNECTED && supplicantState != SupplicantState.COMPLETED) {
            this.connectingWifiInfo = ((WifiSettingsPresenter) getP()).getConnectingWifiInfo();
            x3.a.a(TAG_W, "onSupplicantStateChanged_page", ", state: " + supplicantParams.state + ", connectingWifiInfo: " + getWifiInfoText(this.connectingWifiInfo));
        }
        SupplicantState supplicantState2 = supplicantParams.state;
        updateUIOnSupplicantDisconnected(supplicantState2, supplicantParams.errorReason);
        this.preConnectState = supplicantState2;
    }

    private void onWifiInfoPagerClicked(PagerBtnType pagerBtnType, WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            x3.a.b(TAG_W, "---onWifiInfoPagerClicked result is null return ");
            return;
        }
        x3.a.a(TAG_W, "---onWifiInfoPagerClicked---name: " + m4.d.f(wifiScanResult.getName()));
        boolean zIsWifiSaved = ((WifiSettingsPresenter) getP()).isWifiSaved(wifiScanResult);
        int i8 = AnonymousClass11.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i8 != 1) {
            if (i8 == 2 && wifiScanResult.getResult() != null) {
                ((WifiSettingsPresenter) getP()).forgetNetwork(wifiScanResult.getName());
                wifiScanResult.setSaved(false);
                getPager(WifiPagerType.WIFI_INFO).updateData(wifiScanResult);
                return;
            }
            return;
        }
        if (CipherType.OPEN.equals(wifiScanResult.getCipherType())) {
            this.doingConnect = true;
            this.curScanInfo = wifiScanResult;
            showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
            ((WifiSettingsPresenter) getP()).connectOpenType(wifiScanResult);
            return;
        }
        if (!zIsWifiSaved) {
            showPageOnRight(WifiPagerType.INPUT_PWD, wifiScanResult);
            return;
        }
        this.doingConnect = true;
        this.curScanInfo = wifiScanResult;
        showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
        ((WifiSettingsPresenter) getP()).connectByConfig(((WifiSettingsPresenter) getP()).getSavedConfig(wifiScanResult.getName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openWireless(boolean z7) {
        x3.a.a(TAG_W, ", openWireless, open: " + z7);
        this.enableOperation = z7;
        ((WifiSettingsPresenter) getP()).enabled(z7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetConnectState() {
        x3.a.a(TAG_W, "---resetConnectState---preConnectStatus: " + this.preConnectStatus + ", preConnectState: " + this.preConnectState);
        if (this.doingConnect && this.preConnectState == SupplicantState.DISCONNECTED) {
            this.doingConnect = false;
        }
        if (this.connectingWifiInfo != null) {
            this.connectingWifiInfo = null;
        }
    }

    private void resetCurScanInfo() {
        postDelayed(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.6
            @Override // java.lang.Runnable
            public void run() {
                WifiSettingsPager.this.curScanInfo = null;
                x3.a.a(WifiSettingsPager.TAG_W, "---resetCurScanInfo.");
            }
        }, 500L);
    }

    private void showAddNetworkPager() {
        x3.a.a(TAG_W, "---showAddNetworkPager--prePagerType = " + this.prePagerType);
        removeMessage(278);
        cancelAddNetConnectTimer();
        WifiPagerType wifiPagerType = this.prePagerType;
        WifiPagerType wifiPagerType2 = WifiPagerType.ADD_NETWORK;
        if (wifiPagerType != wifiPagerType2) {
            return;
        }
        if (this.addNetInfo != null) {
            ((WifiSettingsPresenter) getP()).forgetNetwork(this.addNetInfo.getName());
        }
        showPageOnRight(wifiPagerType2, this.addNetworkParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChecked(WifiScanResult wifiScanResult) {
        x3.a.a(TAG_W, "---showChecked---result: " + wifiScanResult);
        checkItem(wifiScanResult);
        if (wifiScanResult != null) {
            wifiScanResult.setSaved(((WifiSettingsPresenter) getP()).isWifiSaved(wifiScanResult));
            showPageOnRight(WifiPagerType.WIFI_INFO, wifiScanResult);
        }
    }

    private void showConnectFailed(int i8, boolean z7, boolean z8) {
        boolean zIsAutoConnecting = isAutoConnecting();
        boolean z9 = false;
        x3.a.a(TAG_W, "showConnectFailed", "errCode: " + i8, "notFound: " + z7, ", isWpa3: " + z8, "autoConnect: " + zIsAutoConnecting);
        if (zIsAutoConnecting) {
            x3.a.a(TAG_W, "showConnectFailed", "autoConnect, SSID: " + getWifiInfoText(this.connectingWifiInfo));
            doOnAutoConnecting(i8, z7);
            return;
        }
        doOnUserConnectCompleted();
        if (this.curScanInfo != null) {
            this.curScanInfo.setSaved(false);
            if (z8) {
                showWpa3(i8);
            } else {
                showWpa2(i8, z7);
            }
        } else if (this.addNetInfo != null) {
            showAddNetworkPager();
            z9 = true;
        }
        this.curScanInfo = null;
        if (!z8 || z9) {
            doHintWpa2(i8, z7);
        } else {
            doHintWpa3(i8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCurScanInfo() {
        WifiScanResult wifiScanResult = this.curScanInfo;
        if (wifiScanResult == null) {
            return;
        }
        ((WifiSettingsPresenter) getP()).forgetNetwork(wifiScanResult.getName());
        if (wifiScanResult.getCipherType() == CipherType.OPEN) {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
        } else {
            showPageOnRight(WifiPagerType.INPUT_PWD, wifiScanResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInputOrConnect(WifiScanResult wifiScanResult) {
        WifiInfo connectingWifiInfo;
        boolean zIsWifiSaved = ((WifiSettingsPresenter) getP()).isWifiSaved(wifiScanResult);
        x3.a.a(TAG_W, "showInputOrConnect", "saved: " + zIsWifiSaved + ", result: " + wifiScanResult);
        wifiScanResult.setSaved(zIsWifiSaved);
        checkItem(wifiScanResult);
        this.receiveAutoConnectResult = false;
        if (wifiScanResult.getCipherType() == CipherType.OPEN) {
            this.curScanInfo = wifiScanResult;
            this.doingConnect = true;
            showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
            ((WifiSettingsPresenter) getP()).connectOpenType(wifiScanResult);
            this.enableOperation = false;
            return;
        }
        if (!zIsWifiSaved) {
            x3.a.a(TAG_W, "showInputOrConnect", "curPagerType: " + this.curPagerType);
            if (isTheSameInputPage(wifiScanResult)) {
                x3.a.a(TAG_W, "showInputOrConnect", "Page is showing.");
                return;
            } else {
                showPageOnRight(WifiPagerType.INPUT_PWD, wifiScanResult);
                return;
            }
        }
        this.curScanInfo = wifiScanResult;
        this.doingConnect = true;
        showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
        WifiConfiguration savedConfig = ((WifiSettingsPresenter) getP()).getSavedConfig(wifiScanResult.getName());
        this.enableOperation = false;
        if (!((WifiSettingsPresenter) getP()).isAutoConnecting() || !((WifiSettingsPresenter) getP()).isWifiStateConnecting() || (connectingWifiInfo = ((WifiSettingsPresenter) getP()).getConnectingWifiInfo()) == null || !wifiScanResult.getName().equals(m4.d.i(connectingWifiInfo.getSSID()))) {
            ((WifiSettingsPresenter) getP()).connectByConfig(savedConfig);
            return;
        }
        x3.a.a(TAG_W, "showInputOrConnect", "doing autoConnect, and wifi state is connecting, connect duplicated, just update UI, and return.", "SSID: " + m4.d.f(wifiScanResult.getName()));
        this.receiveAutoConnectResult = true;
    }

    private void showPageOnCancelOperation() {
        WifiPagerType wifiPagerType = ((WifiSettingsPresenter) getP()).isConnected() ? WifiPagerType.CONNECTED_EMPTY : WifiPagerType.NO_CONNECTION;
        x3.a.a(TAG_W, "---showPageOnCancelOperation--pagerType = " + wifiPagerType);
        showPageOnRight(wifiPagerType, null);
    }

    private void showPageOnRight(WifiPagerType wifiPagerType, BydData bydData) {
        x3.a.a(TAG_W, "showPageOnRight", "type = " + wifiPagerType);
        if (wifiPagerType == null) {
            wifiPagerType = WifiPagerType.NO_CONNECTION;
        } else if (wifiPagerType == WifiPagerType.CONNECTING) {
            startConnectTimer();
            this.isNetworkNotFound = false;
        }
        this.flRight.setVisibility(0);
        a pager = getPager(wifiPagerType);
        if (pager == null) {
            return;
        }
        this.curPager = pager;
        if (pager.getRootView() != null) {
            pager.updateData(bydData);
        }
        if (this.curPagerType != wifiPagerType) {
            this.prePagerType = this.curPagerType;
            this.preBydData = this.curBydData;
            if (this.prePagerType != null) {
                this.pagerMap.get(this.prePagerType).onHide();
            }
            this.curPagerType = wifiPagerType;
            this.curBydData = bydData;
            this.flRight.removeAllViews();
            pager.loadData();
            this.flRight.addView(pager.getRootView());
        }
    }

    private void showWpa2(int i8, boolean z7) {
        x3.a.a(TAG_W, ", ---showWpa2---errCode: " + i8 + ", notFound: " + z7);
        if (z7) {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
        } else if (i8 == 2) {
            showCurScanInfo();
        } else {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
        }
    }

    private void showWpa3(int i8) {
        x3.a.a(TAG_W, ", ---showWpa3---errCode: " + i8);
        showCurScanInfo();
    }

    private void startScan() {
        x3.a.a(TAG_W, "---startScan---isScanning： " + this.isScanning);
        if (this.isScanning) {
            return;
        }
        x3.a.a(TAG_W, "---startScan---do real");
        this.mScanList.clear();
        this.scanMap.clear();
        this.isScanning = true;
        ((WifiSettingsPresenter) getP()).startScan();
        startRefreshAnimation();
        updateRecyclerViewHeight();
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        if (connectedWifiInfo == null) {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
            return;
        }
        this.llConnected.setVisibility(0);
        this.vItemChecked.setVisibility(0);
        String name = connectedWifiInfo.getName();
        TextView textView = this.tvConnectedName;
        if (name == null) {
            name = "";
        }
        textView.setText(name);
        showPageOnRight(WifiPagerType.CONNECTED, connectedWifiInfo);
    }

    private void updateConnected() {
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        x3.a.a(TAG_W, "----updateConnected---info: " + connectedWifiInfo);
        if (connectedWifiInfo == null) {
            return;
        }
        updateConnected(connectedWifiInfo.getCipherType(), ((WifiSettingsPresenter) getP()).getRssiLevel(connectedWifiInfo.getInfo().getRssi()));
    }

    private void updateCurConnectWifiInfo(ConnectedWifiInfo connectedWifiInfo, String str) {
        if (connectedWifiInfo == null || str == null) {
            return;
        }
        connectedWifiInfo.setSecurity(str);
        b.b(getContext(), CUR_CONNECT_WIFI_SECURITY, str);
    }

    private void updateOnConnected(NetworkInfo networkInfo) {
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        int i8 = 1;
        x3.a.a(TAG_W, "----updateOnConnected---info: " + connectedWifiInfo);
        if (connectedWifiInfo == null) {
            return;
        }
        this.llConnected.setVisibility(0);
        this.vItemChecked.setVisibility(0);
        CipherType cipherType = connectedWifiInfo.getCipherType();
        x3.a.a(TAG_W, "----updateOnConnected---type: " + cipherType);
        int rssiLevel = ((WifiSettingsPresenter) getP()).getRssiLevel(connectedWifiInfo.getInfo().getRssi());
        updateConnected(cipherType, rssiLevel);
        if (networkInfo == null) {
            String sp = SharedPrefUtils.getSP(getContext(), CUR_CONNECT_WIFI_SECURITY);
            if (!TextUtils.isEmpty(sp)) {
                connectedWifiInfo.setSecurity(sp);
                updateConnected(connectedWifiInfo.getCipherType(), rssiLevel);
            }
        }
        String connectedName = ((WifiSettingsPresenter) getP()).getConnectedName();
        WifiInfo connectedWifiInfo2 = ((WifiSettingsPresenter) getP()).getConnectedWifiInfo();
        x3.a.a(TAG_W, "----updateOnConnected---ssid: " + m4.d.f(connectedName));
        x3.a.a(TAG_W, "----updateOnConnected---connectedInfo: " + getWifiInfoText(connectedWifiInfo2));
        if (connectedName != null) {
            String bssid = connectedWifiInfo2 != null ? connectedWifiInfo2.getBSSID() : null;
            this.tvConnectedName.setText(connectedName);
            ArrayList<WifiScanResult> arrayList = new ArrayList(this.mScanList);
            this.mScanList.clear();
            for (WifiScanResult wifiScanResult : arrayList) {
                if (wifiScanResult.getMac().equals(bssid)) {
                    String[] strArr = new String[i8];
                    strArr[0] = "----updateOnConnected---This scan result is connected, info: " + getWifiInfoText(connectedWifiInfo2);
                    x3.a.a(TAG_W, strArr);
                    cipherType = wifiScanResult.getCipherType();
                    int rssiLevel2 = ((WifiSettingsPresenter) getP()).getRssiLevel(wifiScanResult.getRssi());
                    String[] strArr2 = new String[i8];
                    strArr2[0] = "----updateOnConnected---type: " + cipherType + ", level: " + rssiLevel2;
                    x3.a.a(TAG_W, strArr2);
                    updateConnected(cipherType, rssiLevel2);
                    connectedWifiInfo.setSecurity(wifiScanResult.getSecurity());
                } else {
                    this.mScanList.add(wifiScanResult);
                }
                i8 = 1;
            }
            String[] strArr3 = new String[i8];
            strArr3[0] = "----updateOnConnected---info: " + connectedWifiInfo;
            x3.a.a(TAG_W, strArr3);
            showPageOnRight(WifiPagerType.CONNECTED, connectedWifiInfo);
            ((WifiSettingsPresenter) getP()).saveNetwork(connectedName, bssid, cipherType);
            try {
                Collections.sort(this.mScanList);
            } catch (Exception e8) {
                x3.a.b(TAG_W, "updateOnConnected sort Exception:" + e8);
            }
            this.mAdapter.checkItem(-1);
            this.mAdapter.notifyDataSetChanged();
            updateRecyclerViewHeight();
        }
    }

    private void updateOnDisconnected(boolean z7) {
        WifiInfo connectedWifiInfo = ((WifiSettingsPresenter) getP()).getConnectedWifiInfo();
        x3.a.a(TAG_W, "updateOnDisconnected", "doingConnect: " + this.doingConnect, "remoteDisconnected: " + z7, "info: " + getWifiInfoText(connectedWifiInfo));
        this.llConnected.setVisibility(8);
        if (connectedWifiInfo == null && !this.doingConnect && this.addNetInfo == null) {
            if (z7 || !isAutoConnecting()) {
                showPageOnRight(WifiPagerType.NO_CONNECTION, null);
            }
        }
    }

    private void updateOnNoConnected() {
        x3.a.a(TAG_W, "----updateOnNoConnected---");
        this.llConnected.setVisibility(8);
        showPageOnRight(WifiPagerType.NO_CONNECTION, null);
    }

    private void updateOnShowWifiInfo(boolean z7) {
        x3.a.a(TAG_W, ", updateOnShowWifiInfo, enable = " + z7);
        if (!z7) {
            this.flRight.setVisibility(8);
        } else {
            this.flRight.setVisibility(0);
            this.tvConnectedName.setText(((WifiSettingsPresenter) getP()).getConnectedName());
        }
    }

    private void updateOnWifiStateChanged(boolean z7, boolean z8) {
        x3.a.a(TAG_W, ", updateOnWifiStateChanged, open = " + z7);
        this.bydSwitch.b(z7, false);
        this.curScanInfo = null;
        this.addNetInfo = null;
        if (!z7) {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
            this.llOpen.setVisibility(8);
            this.llClose.setVisibility(0);
        } else {
            this.llOpen.setVisibility(0);
            this.llClose.setVisibility(8);
            if (!ServiceDialogHolder.isDoingonConfigurationChanged()) {
                startScan();
            }
            updateRecyclerViewHeight();
        }
    }

    private void updateRecyclerViewHeight() {
        if (isPageInvalid()) {
            return;
        }
        Resources resources = getContext().getResources();
        int size = this.mScanList.size();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.rcvWifi.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = ((int) resources.getDimension(R.dimen.dp_60)) * size;
        this.rcvWifi.setLayoutParams(layoutParams);
        this.mAdapter.notifyDataSetChanged();
    }

    private void updateUIOnSupplicantDisconnected(SupplicantState supplicantState, int i8) {
        if (supplicantState != SupplicantState.DISCONNECTED || this.preConnectState == SupplicantState.COMPLETED) {
            return;
        }
        if (this.preConnectState != null) {
            cancelConnectTimer();
        }
        x3.a.a(TAG_W, "updateUIOnSupplicantDisconnected", "preConnectState: " + this.preConnectState, ", errCode: " + i8, "doingConnect: " + this.doingConnect);
        StringBuilder sb = new StringBuilder();
        sb.append("curScanInfo: ");
        sb.append(this.curScanInfo);
        x3.a.a(TAG_W, "updateUIOnSupplicantDisconnected", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("connectingWifiInfo: ");
        sb2.append(getWifiInfoText(this.connectingWifiInfo));
        x3.a.a(TAG_W, "updateUIOnSupplicantDisconnected", sb2.toString());
        if (this.preConnectState == null) {
            if (this.doingConnect) {
                return;
            }
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
            return;
        }
        boolean zIsConnectingScanInfoWpa3 = isConnectingScanInfoWpa3();
        switch (AnonymousClass11.$SwitchMap$android$net$wifi$SupplicantState[this.preConnectState.ordinal()]) {
            case 1:
                showConnectFailed(i8, true, zIsConnectingScanInfoWpa3);
                break;
            case 2:
            case 3:
                showConnectFailed(i8, false, zIsConnectingScanInfoWpa3);
                break;
            case 4:
            case 5:
            case 6:
                showConnectFailed(i8, false, zIsConnectingScanInfoWpa3);
                break;
        }
        g.a().a(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.10
            @Override // java.lang.Runnable
            public void run() {
                WifiSettingsPager.this.resetConnectState();
            }
        }, 120L, Priority.HIGH);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // z3.a
    public String getDialogTag() {
        return TAG_W;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.activity_wifi;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
        this.scanMap = new ConcurrentHashMap<>();
        this.mScanList = new ArrayList();
        WifiListAdapter wifiListAdapter = new WifiListAdapter(this.mScanList);
        this.mAdapter = wifiListAdapter;
        this.rcvWifi.setAdapter(wifiListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context) { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.3
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(1);
        this.rcvWifi.setLayoutManager(linearLayoutManager);
        this.rcvWifi.setItemAnimator(new c1.c());
        this.mAdapter.setOnItemClickedListener(new d4.d() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.4
            @Override // d4.d
            public void onItemClicked(int i8) {
                x3.a.a(WifiSettingsPager.TAG_W, "--onItemClicked--position = " + i8);
                if (i8 < 0 || i8 >= WifiSettingsPager.this.mScanList.size()) {
                    return;
                }
                if (WifiSettingsPager.this.doingConnect) {
                    d.b(WifiSettingsPager.this.getContext(), R.string.hint_muti_connect_operation);
                    return;
                }
                WifiSettingsPager.this.checkPosition = i8;
                WifiSettingsPager wifiSettingsPager = WifiSettingsPager.this;
                wifiSettingsPager.showInputOrConnect((WifiScanResult) wifiSettingsPager.mScanList.get(i8));
            }
        });
        this.mAdapter.setOnItemBtnClickedCallback(new d4.c() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.5
            @Override // d4.c
            public void onItemBtnClicked(int i8, BtnType btnType) {
                x3.a.a(WifiSettingsPager.TAG_W, "--onItemBtnClicked--position = " + i8);
                if (i8 < 0 || i8 >= WifiSettingsPager.this.mScanList.size()) {
                    return;
                }
                if (WifiSettingsPager.this.doingConnect) {
                    d.b(WifiSettingsPager.this.getContext(), R.string.hint_muti_connect_operation);
                } else {
                    WifiSettingsPager wifiSettingsPager = WifiSettingsPager.this;
                    wifiSettingsPager.showChecked((WifiScanResult) wifiSettingsPager.mScanList.get(i8));
                }
            }
        });
        WifiCache.setUIListening(true);
        PagerTimerManager.Singleton.get().registerTimeoutable(getClass(), this);
    }

    @Override // z3.e
    public boolean initHandler() {
        return true;
    }

    @Override // z3.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // z3.e
    public void initViews(Context context) {
        if (this.pagerMap == null) {
            this.pagerMap = new ConcurrentHashMap<>();
        }
        ((WifiSettingsPresenter) getP()).init(context, 33);
        this.titleParent = (FrameLayout) this.mRootView.findViewById(R.id.fl_title_wireless);
        this.mLeftView = this.mRootView.findViewById(R.id.rl_left_wifi);
        this.llOpen = this.mRootView.findViewById(R.id.ll_wifi_state_open_wireless);
        this.llClose = this.mRootView.findViewById(R.id.ll_wifi_state_close_wireless);
        this.llConnected = this.llOpen.findViewById(R.id.ll_connected_device_wifi_left);
        this.flRight = (FrameLayout) this.llOpen.findViewById(R.id.fl_wifi_right);
        View viewFindViewById = this.llOpen.findViewById(R.id.rl_connected_device_wifi_left);
        this.rlConnected = viewFindViewById;
        this.vItemChecked = viewFindViewById.findViewById(R.id.v_item_bg_item_connected_device_wifi_left);
        this.ivIconConnected = (ImageView) this.rlConnected.findViewById(R.id.iv_icon_connected_device_wifi_left);
        this.tvConnectedName = (TextView) this.rlConnected.findViewById(R.id.tv_name_connected_device_wifi_left);
        this.ivRefresh = (ImageView) this.llOpen.findViewById(R.id.iv_refresh_wifi_left);
        this.rcvWifi = (RecyclerView) this.llOpen.findViewById(R.id.rcv_discovered_device_list_wifi_left);
        DampingScrollView dampingScrollView = (DampingScrollView) this.llOpen.findViewById(R.id.wifi_left_scroll_view);
        dampingScrollView.setScroll(false);
        this.llOpen.findViewById(R.id.tv_add_network_wifi_left).setOnClickListener(this);
        this.ivRefresh.setOnClickListener(this);
        this.rlConnected.setOnClickListener(this);
        this.rlConnected.findViewById(R.id.iv_detail_connected_device_wifi_left).setOnClickListener(this);
        if (AppConfig.a() == CarModel$Brand.DENZA) {
            this.mLeftView.setBackgroundResource(R.drawable.bg_dialog_bt_left_tenz_land);
        }
        dampingScrollView.findViewById(R.id.ll_discovered_device_list_wifi_left).setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                x3.a.a(WifiSettingsPager.TAG_W, "--onClick--scrollView.");
                m4.e.a(WifiSettingsPager.this.flRight);
            }
        });
    }

    @Override // z3.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    public boolean isDoingConnect() {
        return this.doingConnect;
    }

    public boolean isReceiveAutoConnectResult() {
        return this.receiveAutoConnectResult;
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onActionModeFinished(ActionMode actionMode) {
        super.onActionModeFinished(actionMode);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back_wireless) {
            z3.d dVar = this.mCallback;
            if (dVar != null) {
                dVar.onBackPress();
            }
            finish();
            return;
        }
        if (id == R.id.rl_connected_device_wifi_left) {
            updateConnected();
            checkConnectedWifi();
            return;
        }
        if (id == R.id.iv_refresh_wifi_left) {
            startScan();
            return;
        }
        if (id != R.id.iv_detail_connected_device_wifi_left) {
            if (id == R.id.tv_add_network_wifi_left) {
                checkAddNetwork();
            }
        } else {
            ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
            if (connectedWifiInfo != null) {
                showPageOnRight(WifiPagerType.CONNECTED, connectedWifiInfo);
            }
        }
    }

    public void onConnectStateChanged(WifiConnectParams wifiConnectParams) {
        x3.a.a(TAG_W, "onConnectStateChanged_page", ", params: " + wifiConnectParams);
        ConnectState connectState = wifiConnectParams.state;
        this.currentConnectState = connectState;
        int i8 = AnonymousClass11.$SwitchMap$com$byd$bt$model$ConnectState[connectState.ordinal()];
        if (i8 == 2) {
            this.doingConnect = false;
            this.connectingWifiInfo = null;
            x3.a.a(TAG_W, "onConnectStateChanged_page", "---onConnectStateChanged---CONNECTED--");
            cancelConnectTimer();
            updateOnConnected(wifiConnectParams.info);
            NetworkParams networkParams = this.addNetworkParams;
            if (networkParams != null && this.curConnectWifiInfo != null && networkParams.getName().equals(this.curConnectWifiInfo.getName())) {
                cancelAddNetConnectTimer();
            }
            doOnUserConnectCompleted();
            resetCurScanInfo();
            x3.a.a(TAG_W, "onConnectStateChanged_page", "---onConnectStateChanged---CONNECTED--delay to reset curScanInfo.");
        } else if (i8 == 4) {
            x3.a.a(TAG_W, "onConnectStateChanged_page", "--DISCONNECTED--preConnectStatus: " + this.preConnectStatus);
            if (this.preConnectStatus == null || this.preConnectStatus == ConnectState.CONNECTING || this.preConnectStatus == ConnectState.CONNECTED) {
                updateOnDisconnected(this.preConnectStatus == ConnectState.CONNECTED);
            }
        }
        this.preConnectStatus = wifiConnectParams.state;
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onContentChanged() {
        super.onContentChanged();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onCreatePanelMenu(int i8, Menu menu) {
        return super.onCreatePanelMenu(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ View onCreatePanelView(int i8) {
        return super.onCreatePanelView(i8);
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        WifiCache.setUIListening(false);
        super.onDestroy();
        SystemDialog.sendBroadcastWifiDialogShow(SystemSettingsModule.getAppContext(), false);
        ImageView imageView = this.ivRefresh;
        if (imageView != null) {
            imageView.clearAnimation();
        }
        this.isScanning = false;
        if (ServiceDialogHolder.isDoingonConfigurationChanged()) {
            PagerTimerManager.Singleton.get().unregisterTimeoutable(getClass());
        } else {
            cancelConnectTimer();
            cancelAddNetConnectTimer();
        }
        if (this.mScanList != null) {
            this.mScanList.clear();
        }
        ConcurrentHashMap<String, WifiScanResult> concurrentHashMap = this.scanMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<WifiPagerType, a> concurrentHashMap2 = this.pagerMap;
        if (concurrentHashMap2 != null) {
            Iterator<a> it = concurrentHashMap2.values().iterator();
            while (it.hasNext()) {
                it.next().onDestroy();
            }
            this.pagerMap.clear();
        }
        ServiceDialogHolder.dismissAll(getClass().getName());
        this.curPager = null;
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // z3.e
    public void onHandlerOther(Message message) {
        super.onHandlerOther(message);
        if (message.what != 278) {
            return;
        }
        d.b(getContext(), R.string.network_not_found_on_connecting);
        doOnUserConnectCompleted();
        x3.a.a(TAG_W, "---onHandlerOther--prePagerType = " + this.prePagerType);
        showPageOnRight(this.prePagerType, this.preBydData);
        ((WifiSettingsPresenter) getP()).cancelConnect();
        this.doingConnect = false;
    }

    @Override // z3.e
    public void onHandlerUpdateUI(Message message) {
        int i8 = message.arg1;
        if (i8 == 257) {
            onScanResult((List) message.obj);
        }
        if (i8 == 277) {
            x3.a.a(TAG_W, "----onHandlerUpdateUI--ARG1_WIFI_RSSI_CHANGED--msg: " + message);
            onWifiRssiChanged();
            return;
        }
        if (i8 == 279) {
            x3.a.a(TAG_W, "----onHandlerUpdateUI--ADD_NET_CIPHER_TYPE_ERR--msg: " + message);
            onCipherTypeErr();
            return;
        }
        switch (i8) {
            case 272:
                x3.a.a(TAG_W, "----onHandlerUpdateUI--CONNECT_CHANGED--msg: " + message);
                onConnectStateChanged((WifiConnectParams) message.obj);
                break;
            case 273:
                x3.a.a(TAG_W, "----onHandlerUpdateUI--CONNECT_FAILED--msg: " + message);
                onConnectFailed(((Integer) message.obj).intValue());
                break;
            case 274:
                x3.a.a(TAG_W, "----onHandlerUpdateUI--ENABLE_CHANGED--msg: " + message);
                onWifiStateChanged((EnableState) message.obj);
                break;
            case 275:
                x3.a.a(TAG_W, "----onHandlerUpdateUI--ARG1_WIFI_SUPPLICANT_STATE_CHANGED--msg: " + message);
                onSupplicantStateChanged((SupplicantParams) message.obj);
                break;
        }
    }

    @Override // k4.c
    public void onKeyboardStateChanged(boolean z7) {
        x3.a.a(TAG_W, "---onKeyboardStateChanged---showing: " + z7);
        View.OnClickListener pager = getPager(this.curPagerType);
        if (pager instanceof c) {
            ((c) pager).onKeyboardStateChanged(z7);
        }
    }

    @Override // z3.e
    public void onLtrRudderL(boolean z7) {
        super.onLtrRudderL(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_wireless_settings, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        super.onLtrRudderR(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_wireless_settings_r, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        return super.onMenuItemSelected(i8, menuItem);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onMenuOpened(int i8, Menu menu) {
        return super.onMenuOpened(i8, menu);
    }

    public void onPagerBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
        x3.a.a(TAG_W, "clazz = " + cls.getName() + ", type = " + pagerBtnType);
        String name = cls.getName();
        this.addNetInfo = null;
        if (WifiInfoPager.class.getName().equals(name)) {
            onWifiInfoPagerClicked(pagerBtnType, (WifiScanResult) obj);
            return;
        }
        if (WifiInputConnectPager.class.getName().equals(name)) {
            onInputConnectPagerClicked(pagerBtnType, (WifiScanResult) obj, (String) obj2);
            return;
        }
        if (WifiConnectingPager.class.getName().equals(name)) {
            onConnectingPagerClicked(pagerBtnType, (WifiScanResult) obj);
            cancelAddNetConnectTimer();
            ((WifiSettingsPresenter) getP()).cancelConnect();
        } else if (ConnectedWifiInfoPager.class.getName().equals(name)) {
            onConnectedWifiInfoPagerClicked(pagerBtnType, (ConnectedWifiInfo) obj);
        } else if (WifiAddNetworkPager.class.getName().equals(name)) {
            onAddNetPagerClicked(pagerBtnType, (NetworkParams) obj);
        }
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onPanelClosed(int i8, Menu menu) {
        super.onPanelClosed(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onPreparePanel(int i8, View view, Menu menu) {
        return super.onPreparePanel(i8, view, menu);
    }

    @Override // z3.e
    public void onRestoreInstanceState() {
        super.onRestoreInstanceState();
        WifiDialogState wifiDialogState = (WifiDialogState) DialogStatesCache.get(DialogState.WIFI_DIALOG_STATE);
        x3.a.a(TAG_W, "onRestoreInstanceState", "state: " + wifiDialogState);
        if (wifiDialogState == null) {
            return;
        }
        WifiPagerType curPagerType = wifiDialogState.getCurPagerType();
        BydData curBydData = wifiDialogState.getCurBydData();
        this.curBydData = curBydData;
        this.doingConnect = wifiDialogState.isDoingConnect();
        if (curPagerType != null) {
            x3.a.a(TAG_W, "onRestoreInstanceState", "curData: " + curBydData);
            showPageOnRight(curPagerType, curBydData);
        }
        this.curPagerType = curPagerType;
        WifiPagerType wifiPagerType = WifiPagerType.ADD_NETWORK;
        if (curPagerType == wifiPagerType) {
            if (curBydData instanceof NetworkParams) {
                this.addNetworkParams = (NetworkParams) curBydData;
            }
        } else if (curBydData instanceof WifiScanResult) {
            this.curScanInfo = (WifiScanResult) curBydData;
        }
        x3.a.a(TAG_W, "onRestoreInstanceState", "curType: " + curPagerType + ", curScanInfo: " + this.curScanInfo + ", addNetworkParams: " + this.addNetworkParams);
        WifiPagerType prePagerType = wifiDialogState.getPrePagerType();
        BydData preBydData = wifiDialogState.getPreBydData();
        this.prePagerType = prePagerType;
        this.preBydData = preBydData;
        if (prePagerType == wifiPagerType) {
            if (this.addNetworkParams == null && (preBydData instanceof NetworkParams)) {
                this.addNetworkParams = (NetworkParams) preBydData;
            }
        } else if (this.curScanInfo == null && (preBydData instanceof WifiScanResult)) {
            this.curScanInfo = (WifiScanResult) preBydData;
        }
        this.preConnectState = wifiDialogState.getPreConnectState();
        x3.a.a(TAG_W, "onRestoreInstanceState", "preType: " + prePagerType + ", curScanInfo: " + this.curScanInfo + ", addNetworkParams: " + this.addNetworkParams);
        this.connectingWifiInfo = ((WifiSettingsPresenter) getP()).getConnectingWifiInfo();
        this.checkPosition = wifiDialogState.getCheckItemIndex();
        if (this.mScanList != null) {
            this.mScanList.clear();
            this.mScanList.addAll(wifiDialogState.getScanList());
            try {
                Collections.sort(this.mScanList);
            } catch (Exception e8) {
                x3.a.b(TAG_W, "updateOnConnected sort Exception:" + e8);
            }
            this.mAdapter.checkItem(this.checkPosition);
            this.mAdapter.notifyDataSetChanged();
        }
        DialogStatesCache.remove(DialogState.WIFI_DIALOG_STATE);
    }

    @Override // z3.a
    public void onResume() {
        super.onResume();
        boolean zIsWifiEnabled = ((WifiSettingsPresenter) getP()).isWifiEnabled();
        x3.a.a(TAG_W, "---onResume---isOpen：" + zIsWifiEnabled);
        this.bydSwitch.b(zIsWifiEnabled, false);
        if (!ServiceDialogHolder.isDoingonConfigurationChanged()) {
            updateOnNoConnected();
        }
        updateOnWifiStateChanged(zIsWifiEnabled, true);
        if (!zIsWifiEnabled && !ServiceDialogHolder.isDoingonConfigurationChanged()) {
            this.enableOperation = true;
            ((WifiSettingsPresenter) getP()).enabled(true);
        } else if (!((WifiSettingsPresenter) getP()).isConnected()) {
            x3.a.a(TAG_W, ", --onResume---isConnected: false.");
            updateOnDisconnected(false);
        } else {
            x3.a.a(TAG_W, ", --onResume---isConnected: true.");
            checkConnectedWifi();
            updateOnConnected(null);
        }
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        this.titleParent.addView(this.lf.inflate(R.layout.title_wireless_settings_rtl, (ViewGroup) null));
        loadTitleOnLayoutLocked();
        setViewGroupRtl(this.titleParent, false);
    }

    @Override // z3.e
    public void onSaveInstanceState() {
        super.onSaveInstanceState();
        WifiPagerType wifiPagerType = this.curPagerType;
        WifiPagerType wifiPagerType2 = this.prePagerType;
        x3.a.a(TAG_W, "onSaveInstanceState", "curType: " + wifiPagerType + ", preType: " + wifiPagerType2);
        WifiPagerType wifiPagerType3 = WifiPagerType.ADD_NETWORK;
        if (wifiPagerType == wifiPagerType3) {
            this.curBydData = findAddNetworkParams(wifiPagerType);
        }
        if (wifiPagerType2 == wifiPagerType3) {
            this.preBydData = findAddNetworkParams(wifiPagerType2);
        }
        x3.a.a(TAG_W, "onSaveInstanceState", "curBydData: " + this.curBydData);
        x3.a.a(TAG_W, "onSaveInstanceState", "preBydData: " + this.preBydData);
        WifiDialogState wifiDialogState = new WifiDialogState(wifiPagerType, this.curBydData);
        wifiDialogState.setScanList(new ArrayList(this.mScanList));
        wifiDialogState.setFirstItemIndex(0);
        wifiDialogState.setPrePagerType(wifiPagerType2);
        wifiDialogState.setPreBydData(this.preBydData);
        wifiDialogState.setPreConnectState(this.preConnectState);
        wifiDialogState.setDoingConnect(this.doingConnect);
        wifiDialogState.setCheckItemIndex(this.checkPosition);
        x3.a.a(TAG_W, "onSaveInstanceState", "state: " + wifiDialogState);
        DialogStatesCache.put(DialogState.WIFI_DIALOG_STATE, wifiDialogState);
    }

    public void onScanFinished() {
        x3.a.a(TAG_W, "---onScanFinished---");
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.7
            @Override // java.lang.Runnable
            public void run() {
                WifiSettingsPager.this.ivRefresh.clearAnimation();
            }
        });
        this.isScanning = false;
        ((WifiSettingsPresenter) getP()).autoScan();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0167  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onScanResult(java.util.List<android.net.wifi.ScanResult> r17) {
        /*
            Method dump skipped, instruction units count: 569
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.wifi2.WifiSettingsPager.onScanResult(java.util.List):void");
    }

    public void onScanTimeout() {
        x3.a.a(TAG_W, "---onScanTimeout---");
        this.ivRefresh.clearAnimation();
        this.isScanning = false;
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onSearchRequested(SearchEvent searchEvent) {
        return super.onSearchRequested(searchEvent);
    }

    @Override // com.byd.systemsettings.wifi2.callback.TimerTimeoutable
    public void onTimerTimeout(int i8) {
        x3.a.a(this.TAG, "onTimerTimeout", "actionCode: " + i8, "currentConnectState: " + this.currentConnectState + ", preConnectState: " + this.preConnectState + ", isNetworkNotFound: " + this.isNetworkNotFound);
        if (i8 == 2) {
            sendMessageDelay(278, 1L);
            return;
        }
        this.doingConnect = false;
        if (this.currentConnectState == ConnectState.CONNECTED || this.isNetworkNotFound) {
            return;
        }
        g.a().b(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.8
            @Override // java.lang.Runnable
            public void run() {
                if (WifiSettingsPager.this.mRootView != null) {
                    d.b(WifiSettingsPager.this.mRootView.getContext(), R.string.connect_failed_on_connecting);
                }
                WifiSettingsPager.this.showCurScanInfo();
            }
        });
    }

    public void onWifiRssiChanged() {
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        x3.a.a(TAG_W, "----onWifiRssiChanged---info: " + connectedWifiInfo);
        if (connectedWifiInfo == null || isPageInvalid()) {
            return;
        }
        CipherType cipherType = connectedWifiInfo.getCipherType();
        int rssiLevel = ((WifiSettingsPresenter) getP()).getRssiLevel(connectedWifiInfo.getInfo().getRssi());
        x3.a.a(TAG_W, "----onWifiRssiChanged---level: " + rssiLevel);
        updateConnected(cipherType, rssiLevel);
        ConcurrentHashMap<WifiPagerType, a> concurrentHashMap = this.pagerMap;
        WifiPagerType wifiPagerType = WifiPagerType.CONNECTED;
        a aVar = concurrentHashMap.get(wifiPagerType);
        if (aVar == null || this.curPagerType != wifiPagerType) {
            return;
        }
        aVar.updateData(connectedWifiInfo);
    }

    public void onWifiStateChanged(EnableState enableState) {
        x3.a.a(TAG_W, ", onWifiStateChanged, state = " + enableState);
        int i8 = AnonymousClass11.$SwitchMap$com$byd$bt$model$EnableState[enableState.ordinal()];
        if (i8 == 2) {
            this.doingConnect = false;
            updateOnWifiStateChanged(true);
        } else {
            if (i8 != 3) {
                return;
            }
            updateOnWifiStateChanged(false);
        }
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
    }

    @Override // k4.e, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z7) {
        this.focusing = z7;
        x3.a.a(TAG_W, "---onWindowFocusChanged---hasFocus: " + z7);
        ((WifiSettingsPresenter) getP()).onWindowFocusChanged(z7);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
        return super.onWindowStartingActionMode(callback, i8);
    }

    public void startAddNetConnectTimer() {
        x3.a.a(this.TAG, "---startAddNetConnectTimer---");
        removeMessage(278);
        PagerTimerManager.Singleton.get().startTimer(getClass(), 2, this, 10000L);
    }

    public void startConnectTimer() {
        x3.a.a(this.TAG, "---startConnectTimer---");
        removeMessages(817);
        PagerTimerManager.Singleton.get().startTimer(getClass(), 1, this, TIMEOUT_CONNECT);
    }

    public void startRefreshAnimation() {
        x3.a.a(TAG_W, "---startRefreshAnimation---");
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setStartOffset(0L);
        this.ivRefresh.startAnimation(rotateAnimation);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.g
    public WifiSettingsPresenter newP() {
        return new WifiSettingsPresenter();
    }

    private void updateConnected(CipherType cipherType, int i8) {
        this.connectedCipherType = cipherType;
        this.connectedRssiLevel = i8;
        this.ivIconConnected.setImageResource(ResUtils.getRssiImgId(cipherType, i8));
    }

    private void updateOnWifiStateChanged(boolean z7) {
        updateOnWifiStateChanged(z7, false);
    }
}
