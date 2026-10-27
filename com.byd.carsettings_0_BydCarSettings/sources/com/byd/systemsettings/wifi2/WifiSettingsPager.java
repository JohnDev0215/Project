package com.byd.systemsettings.wifi2;

import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.f.h;
import b.b.a.h.i;
import b.b.g.r.z;
import b.b.i.c;
import b.b.m.e.a;
import b.b.m.e.f;
import b.b.m.e.g;
import b.b.m.f.c.a.d;
import b.b.m.f.c.a.e;
import com.byd.base.thread.Priority;
import com.byd.base.view.BydSwitch;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.systemsettings.wifi2.adapter.WifiListAdapter;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.ConnectedWifiInfo;
import com.byd.systemsettings.wifi2.model.NetworkParams;
import com.byd.systemsettings.wifi2.model.SupplicantParams;
import com.byd.systemsettings.wifi2.model.WifiConnectParams;
import com.byd.systemsettings.wifi2.model.WifiPagerType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.util.ResUtils;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class WifiSettingsPager extends a<WifiSettingsPresenter> {
    public static final String CUR_CONNECT_WIFI_SECURITY = "cur_connect_wifi_security";
    private static final int REQUEST_CODE_PERMISSION = 33;
    private static final String TAG_W = "WifiSettingsPager";
    private static final long TIMEOUT_CONNECT = 15000;
    private volatile WifiScanResult addNetInfo;
    private NetworkParams addNetworkParams;
    private BydSwitch bydSwitch;
    private boolean cancelConnect;
    private volatile Timer connectTimer;
    private volatile WifiInfo connectingWifiInfo;
    private volatile BydData curBydData;
    private ConnectedWifiInfo curConnectWifiInfo;
    private volatile f curPager;
    private volatile WifiPagerType curPagerType;
    private volatile WifiScanResult curScanInfo;
    private ConnectState currentConnectState;
    private volatile boolean doingConnect;
    private volatile boolean enableOperation;
    private FrameLayout flRight;
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
    private List<WifiScanResult> mScanList;
    private ConcurrentHashMap<WifiPagerType, a> pagerMap;
    private volatile BydData preBydData;
    private volatile SupplicantState preConnectState;
    private volatile ConnectState preConnectStatus;
    private volatile WifiPagerType prePagerType;
    private RecyclerView rcvWifi;
    private View rlConnected;
    private FrameLayout titleParent;
    private TextView tvConnectedName;
    private View vItemChecked;

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.WifiSettingsPager$8, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass8 {
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

    public class OnPageBtnClickedCallback implements b.b.m.b.a {
        private final WeakReference<WifiSettingsPager> ref;

        public OnPageBtnClickedCallback(WifiSettingsPager wifiSettingsPager) {
            this.ref = new WeakReference<>(wifiSettingsPager);
        }

        @Override // b.b.m.b.a
        public /* bridge */ /* synthetic */ void onBtnClicked(PagerBtnType pagerBtnType) {
            super.onBtnClicked(pagerBtnType);
        }

        @Override // b.b.m.b.a
        public /* bridge */ /* synthetic */ void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType) {
            super.onBtnClicked(cls, pagerBtnType);
        }

        @Override // b.b.m.b.a
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
        this.connectTimer = null;
        this.connectingWifiInfo = null;
    }

    private void cancelConnectTimer() {
        b.b.m.c.a.a(this.TAG, "---cancelConnectTimer---");
        if (this.connectTimer != null) {
            this.connectTimer.cancel();
            this.connectTimer = null;
        }
    }

    private void checkAddNetwork() {
        if (this.doingConnect) {
            z.b(getContext(), R.string.hint_muti_connect_operation);
        } else {
            showChecked(null);
            showPageOnRight(WifiPagerType.ADD_NETWORK, null);
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
        b.b.m.c.a.a(TAG_W, "---checkItem---result: " + wifiScanResult);
        this.vItemChecked.setVisibility(wifiScanResult != null ? 8 : 0);
        for (WifiScanResult wifiScanResult2 : this.mScanList) {
            wifiScanResult2.setChecked(wifiScanResult2.equals(wifiScanResult));
        }
        updateRecyclerViewHeight();
    }

    private String getApName(WifiInfo wifiInfo) {
        b.b.m.c.a.a(TAG_W, ", ---getApName---info: " + wifiInfo);
        if (wifiInfo == null) {
            return "";
        }
        String ssid = wifiInfo.getSSID();
        return TextUtils.isEmpty(ssid) ? "" : (ssid.startsWith("\"") && ssid.endsWith("\"")) ? ssid.substring(1, ssid.length() - 1) : ssid;
    }

    private ConnectedWifiInfo getConnectedWifiInfo() {
        WifiInfo connectedWifiInfo = ((WifiSettingsPresenter) mo7getP()).getConnectedWifiInfo();
        b.b.m.c.a.a(TAG_W, "---getConnectedWifiInfo---info = " + getWifiInfoText(connectedWifiInfo));
        if (connectedWifiInfo == null) {
            return null;
        }
        if (this.curConnectWifiInfo == null) {
            this.curConnectWifiInfo = new ConnectedWifiInfo();
        }
        this.curConnectWifiInfo.setName(((WifiSettingsPresenter) mo7getP()).getConnectedName());
        this.curConnectWifiInfo.setInfo(connectedWifiInfo);
        this.curConnectWifiInfo.setIpAddr(connectedWifiInfo.getIpAddress());
        if (this.curScanInfo != null && this.curScanInfo.getResult() != null && this.curScanInfo.getResult().BSSID.equals(connectedWifiInfo.getBSSID())) {
            ScanResult result = this.curScanInfo.getResult();
            b.b.m.c.a.a(TAG_W, "---getConnectedWifiInfo---scanResult.SSID = " + b.b.m.g.f.g(result.SSID));
            this.curConnectWifiInfo.setSecurity(result.capabilities);
            i.b(getContext(), CUR_CONNECT_WIFI_SECURITY, result.capabilities);
        }
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
        switch (AnonymousClass8.$SwitchMap$com$byd$systemsettings$wifi2$model$WifiPagerType[wifiPagerType.ordinal()]) {
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
        return b.b.m.g.f.g(wifiInfo.getSSID()) + ", mac: " + b.b.m.g.f.e(wifiInfo.getBSSID());
    }

    private void loadTitleOnLayoutLocked() {
        this.bydSwitch = (BydSwitch) this.mRootView.findViewById(R.id.switch_title_wireless);
        this.mRootView.findViewById(R.id.iv_back_wireless).setOnClickListener(this);
        this.bydSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                WifiSettingsPager.this.openWireless(z);
            }
        });
    }

    private void onAddNetPagerClicked(PagerBtnType pagerBtnType, NetworkParams networkParams) {
        b.b.m.c.a.a(TAG_W, "onAddNetPagerClicked", ", type: " + pagerBtnType + ", params: " + networkParams);
        this.addNetworkParams = networkParams;
        int i = AnonymousClass8.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            showPageOnCancelOperation();
            this.pagerMap.remove(WifiPagerType.ADD_NETWORK);
            return;
        }
        this.doingConnect = true;
        this.curScanInfo = null;
        WifiScanResult wifiScanResult = new WifiScanResult();
        wifiScanResult.setName(networkParams.getName());
        wifiScanResult.setCipherType(networkParams.getType());
        this.addNetInfo = wifiScanResult;
        showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
        ((WifiSettingsPresenter) mo7getP()).addNetwork(networkParams.getName(), networkParams.getPwd(), networkParams.getType());
        removeMessage(278);
        sendMessageDelay(278, 10000L);
    }

    private void onCipherTypeErr() {
        b.b.m.c.a.a(TAG_W, "---onCipherTypeErr---");
        onErrorReason(2);
        if (this.prePagerType == WifiPagerType.ADD_NETWORK) {
            showAddNetworkPager();
        }
        this.doingConnect = false;
    }

    private void onConnectFailed(int i) {
        b.b.m.c.a.a(TAG_W, "onConnectFailed", ", errCode: " + i);
        if (i == -1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectTimeout() {
        b.b.m.c.a.a(this.TAG, "---onConnectTimeout---currentConnectState: " + this.currentConnectState + ", preConnectState: " + this.preConnectState + ", isNetworkNotFound: " + this.isNetworkNotFound);
        if (this.currentConnectState == ConnectState.CONNECTED || this.isNetworkNotFound) {
            return;
        }
        h.a().d(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.6
            @Override // java.lang.Runnable
            public void run() {
                WifiSettingsPager.this.showCurScanInfo();
            }
        });
    }

    private void onConnectedWifiInfoPagerClicked(PagerBtnType pagerBtnType, ConnectedWifiInfo connectedWifiInfo) {
        int i = AnonymousClass8.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            showPageOnCancelOperation();
        } else {
            if (connectedWifiInfo == null || connectedWifiInfo.getInfo() == null) {
                return;
            }
            ((WifiSettingsPresenter) mo7getP()).forgetNetwork(connectedWifiInfo.getInfo().getNetworkId());
            updateOnNoConnected();
            i.b(getContext(), CUR_CONNECT_WIFI_SECURITY, "");
        }
    }

    private void onConnectingPagerClicked(PagerBtnType pagerBtnType, WifiScanResult wifiScanResult) {
        if (AnonymousClass8.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()] != 3) {
            return;
        }
        this.cancelConnect = true;
        showPageOnRight(this.prePagerType, this.preBydData);
        cancelConnectTimer();
    }

    private void onErrorReason(int i) {
        Context context;
        b.b.m.c.a.a(TAG_W, "---onErrorReason---errCode: " + i);
        if (this.doingConnect && (context = getContext()) != null) {
            if (i != 1) {
                if (i == 2) {
                    z.b(context.getApplicationContext(), R.string.pwd_err_on_connecting);
                    return;
                } else if (i != 3) {
                    return;
                }
            }
            z.b(context.getApplicationContext(), R.string.connect_failed_on_connecting);
        }
    }

    private void onInputConnectPagerClicked(PagerBtnType pagerBtnType, WifiScanResult wifiScanResult, String str) {
        int i = AnonymousClass8.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            showPageOnCancelOperation();
        } else {
            this.doingConnect = true;
            this.curScanInfo = wifiScanResult;
            this.addNetInfo = null;
            showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
            ((WifiSettingsPresenter) mo7getP()).connect(wifiScanResult, str);
            this.enableOperation = false;
        }
    }

    private void onSupplicantStateChanged(SupplicantParams supplicantParams) {
        b.b.m.c.a.a(TAG_W, "--onSupplicantStateChanged--", ", params: " + supplicantParams);
        if (supplicantParams == null || !SupplicantState.isValidState(supplicantParams.state)) {
            return;
        }
        SupplicantState supplicantState = supplicantParams.state;
        updateUIOnSupplicantDisconnected(supplicantState, supplicantParams.errorReason);
        this.preConnectState = supplicantState;
    }

    private void onWifiInfoPagerClicked(PagerBtnType pagerBtnType, WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            b.b.m.c.a.c(TAG_W, "---onWifiInfoPagerClicked result is null return ");
            return;
        }
        b.b.m.c.a.a(TAG_W, "---onWifiInfoPagerClicked---name: " + b.b.m.g.f.g(wifiScanResult.getName()));
        int i = AnonymousClass8.$SwitchMap$com$byd$mvp$callback$PagerBtnType[pagerBtnType.ordinal()];
        if (i != 1) {
            if (i == 2 && wifiScanResult.getResult() != null) {
                ((WifiSettingsPresenter) mo7getP()).forgetNetwork(wifiScanResult.getName());
                wifiScanResult.setSaved(((WifiSettingsPresenter) mo7getP()).isWifiSaved(wifiScanResult));
                getPager(WifiPagerType.WIFI_INFO).updateData(wifiScanResult);
                return;
            }
            return;
        }
        if (CipherType.OPEN.equals(wifiScanResult.getCipherType())) {
            this.doingConnect = true;
            this.curScanInfo = wifiScanResult;
            showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
            ((WifiSettingsPresenter) mo7getP()).connectOpenType(wifiScanResult);
            return;
        }
        if (!((WifiSettingsPresenter) mo7getP()).isWifiSaved(wifiScanResult)) {
            showPageOnRight(WifiPagerType.INPUT_PWD, wifiScanResult);
            return;
        }
        this.doingConnect = true;
        this.curScanInfo = wifiScanResult;
        showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
        ((WifiSettingsPresenter) mo7getP()).connectByConfig(((WifiSettingsPresenter) mo7getP()).getSavedConfig(wifiScanResult.getName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openWireless(boolean z) {
        b.b.m.c.a.a(TAG_W, ", openWireless, open: " + z);
        this.enableOperation = z;
        ((WifiSettingsPresenter) mo7getP()).enabled(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetConnectState() {
        b.b.m.c.a.a(TAG_W, "---resetConnectState---preConnectStatus: " + this.preConnectStatus);
        if (this.doingConnect) {
            this.doingConnect = false;
        }
        if (this.connectingWifiInfo != null) {
            this.connectingWifiInfo = null;
        }
    }

    private void showAddNetworkPager() {
        b.b.m.c.a.a(TAG_W, "---showAddNetworkPager--prePagerType = " + this.prePagerType);
        WifiPagerType wifiPagerType = this.prePagerType;
        WifiPagerType wifiPagerType2 = WifiPagerType.ADD_NETWORK;
        if (wifiPagerType != wifiPagerType2) {
            return;
        }
        if (this.addNetInfo != null) {
            ((WifiSettingsPresenter) mo7getP()).forgetNetwork(this.addNetInfo.getName());
        }
        removeMessage(278);
        showPageOnRight(wifiPagerType2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChecked(WifiScanResult wifiScanResult) {
        b.b.m.c.a.a(TAG_W, "---showChecked---result: " + wifiScanResult);
        checkItem(wifiScanResult);
        showPageOnRight(WifiPagerType.WIFI_INFO, wifiScanResult);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void showConnectFailed(int r10, boolean r11) {
        /*
            r9 = this;
            r0 = 1
            java.lang.String[] r1 = new java.lang.String[r0]
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ", ---showConnectFailed---errCode: "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r3 = ", notFound: "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r3 = 0
            r1[r3] = r2
            java.lang.String r2 = "WifiSettingsPager"
            b.b.m.c.a.a(r2, r1)
            com.byd.systemsettings.wifi2.model.WifiScanResult r1 = r9.curScanInfo
            r4 = 0
            r5 = 2
            if (r1 == 0) goto L43
            com.byd.systemsettings.wifi2.model.WifiScanResult r1 = r9.curScanInfo
            r1.setSaved(r3)
            if (r11 == 0) goto L37
            com.byd.systemsettings.wifi2.model.WifiPagerType r1 = com.byd.systemsettings.wifi2.model.WifiPagerType.NO_CONNECTION
            r9.showPageOnRight(r1, r4)
            goto L4a
        L37:
            if (r10 != r5) goto L3d
            r9.showCurScanInfo()
            goto L4a
        L3d:
            com.byd.systemsettings.wifi2.model.WifiPagerType r1 = com.byd.systemsettings.wifi2.model.WifiPagerType.NO_CONNECTION
            r9.showPageOnRight(r1, r4)
            goto L4a
        L43:
            com.byd.systemsettings.wifi2.model.WifiScanResult r1 = r9.addNetInfo
            if (r1 == 0) goto L4c
            r9.showAddNetworkPager()
        L4a:
            r1 = r3
            goto L4d
        L4c:
            r1 = r0
        L4d:
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = ", ---showConnectFailed---doingConnect: "
            r7.append(r8)
            boolean r8 = r9.doingConnect
            r7.append(r8)
            java.lang.String r8 = ", autoConnect: "
            r7.append(r8)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            r6[r3] = r7
            b.b.m.c.a.a(r2, r6)
            if (r11 == 0) goto L93
            if (r1 != 0) goto L8e
            android.content.Context r11 = r9.getContext()
            r1 = 2131755800(0x7f100318, float:1.914249E38)
            b.b.g.r.z.b(r11, r1)
            if (r10 != r5) goto L8e
            b.b.m.e.g r10 = r9.mo7getP()
            com.byd.systemsettings.wifi2.WifiSettingsPresenter r10 = (com.byd.systemsettings.wifi2.WifiSettingsPresenter) r10
            android.net.wifi.WifiInfo r11 = r9.connectingWifiInfo
            java.lang.String r11 = r9.getApName(r11)
            r10.forgetNetwork(r11)
        L8e:
            r9.isNetworkNotFound = r0
            r9.curScanInfo = r4
            goto La9
        L93:
            if (r10 != r5) goto La4
            b.b.m.e.g r11 = r9.mo7getP()
            com.byd.systemsettings.wifi2.WifiSettingsPresenter r11 = (com.byd.systemsettings.wifi2.WifiSettingsPresenter) r11
            android.net.wifi.WifiInfo r0 = r9.connectingWifiInfo
            java.lang.String r0 = r9.getApName(r0)
            r11.forgetNetwork(r0)
        La4:
            if (r1 != 0) goto La9
            r9.onErrorReason(r10)
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.wifi2.WifiSettingsPager.showConnectFailed(int, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCurScanInfo() {
        WifiScanResult wifiScanResult = this.curScanInfo;
        if (wifiScanResult == null) {
            return;
        }
        ((WifiSettingsPresenter) mo7getP()).forgetNetwork(wifiScanResult.getName());
        if (wifiScanResult.getCipherType() == CipherType.OPEN) {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
        } else {
            showPageOnRight(WifiPagerType.INPUT_PWD, wifiScanResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInputOrConnect(WifiScanResult wifiScanResult) {
        b.b.m.c.a.a(TAG_W, "--showInputOrConnect--result: " + wifiScanResult);
        checkItem(wifiScanResult);
        if (wifiScanResult.getCipherType() == CipherType.OPEN) {
            this.curScanInfo = wifiScanResult;
            this.doingConnect = true;
            showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
            ((WifiSettingsPresenter) mo7getP()).connectOpenType(wifiScanResult);
            this.enableOperation = false;
            return;
        }
        if (!((WifiSettingsPresenter) mo7getP()).isWifiSaved(wifiScanResult)) {
            showPageOnRight(WifiPagerType.INPUT_PWD, wifiScanResult);
            return;
        }
        this.curScanInfo = wifiScanResult;
        this.doingConnect = true;
        showPageOnRight(WifiPagerType.CONNECTING, wifiScanResult);
        ((WifiSettingsPresenter) mo7getP()).connectByConfig(((WifiSettingsPresenter) mo7getP()).getSavedConfig(wifiScanResult.getName()));
        this.enableOperation = false;
    }

    private void showPageOnCancelOperation() {
        WifiPagerType wifiPagerType = ((WifiSettingsPresenter) mo7getP()).isConnected() ? WifiPagerType.CONNECTED_EMPTY : WifiPagerType.NO_CONNECTION;
        b.b.m.c.a.a(TAG_W, "---showPageOnCancelOperation--pagerType = " + wifiPagerType);
        showPageOnRight(wifiPagerType, null);
    }

    private void showPageOnRight(WifiPagerType wifiPagerType, BydData bydData) {
        b.b.m.c.a.a(TAG_W, "showPageOnRight", ", type = " + wifiPagerType);
        if (wifiPagerType == null) {
            wifiPagerType = WifiPagerType.NO_CONNECTION;
        } else if (wifiPagerType == WifiPagerType.CONNECTING) {
            this.isNetworkNotFound = false;
        }
        this.curBydData = bydData;
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

    private void startScan() {
        b.b.m.c.a.a(TAG_W, "---startScan---isScanning： " + this.isScanning);
        if (this.isScanning) {
            return;
        }
        b.b.m.c.a.a(TAG_W, "---startScan---do real");
        this.mScanList.clear();
        this.isScanning = true;
        ((WifiSettingsPresenter) mo7getP()).startScan();
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
        b.b.m.c.a.a(TAG_W, "----updateConnected---info: " + connectedWifiInfo);
        if (connectedWifiInfo == null) {
            return;
        }
        updateConnected(connectedWifiInfo.getCipherType(), ((WifiSettingsPresenter) mo7getP()).getRssiLevel(connectedWifiInfo.getInfo().getRssi()));
    }

    private void updateOnConnected(NetworkInfo networkInfo) {
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        b.b.m.c.a.a(TAG_W, "----updateOnConnected---info: " + connectedWifiInfo);
        if (connectedWifiInfo == null) {
            return;
        }
        this.llConnected.setVisibility(0);
        this.vItemChecked.setVisibility(0);
        CipherType cipherType = connectedWifiInfo.getCipherType();
        b.b.m.c.a.a(TAG_W, "----updateOnConnected---type: " + cipherType);
        int rssiLevel = ((WifiSettingsPresenter) mo7getP()).getRssiLevel(connectedWifiInfo.getInfo().getRssi());
        updateConnected(cipherType, rssiLevel);
        if (networkInfo == null) {
            String strA = i.a(getContext(), CUR_CONNECT_WIFI_SECURITY);
            if (!TextUtils.isEmpty(strA)) {
                connectedWifiInfo.setSecurity(strA);
                updateConnected(connectedWifiInfo.getCipherType(), rssiLevel);
            }
        }
        String connectedName = ((WifiSettingsPresenter) mo7getP()).getConnectedName();
        WifiInfo connectedWifiInfo2 = ((WifiSettingsPresenter) mo7getP()).getConnectedWifiInfo();
        b.b.m.c.a.a(TAG_W, "----updateOnConnected---ssid: " + b.b.m.g.f.g(connectedName));
        b.b.m.c.a.a(TAG_W, "----updateOnConnected---connectedInfo: " + getWifiInfoText(connectedWifiInfo2));
        if (connectedName != null) {
            this.tvConnectedName.setText(connectedName);
            ArrayList<WifiScanResult> arrayList = new ArrayList(this.mScanList);
            this.mScanList.clear();
            for (WifiScanResult wifiScanResult : arrayList) {
                if (connectedWifiInfo2 == null || !wifiScanResult.getMac().equals(connectedWifiInfo2.getBSSID())) {
                    this.mScanList.add(wifiScanResult);
                } else {
                    b.b.m.c.a.a(TAG_W, "----updateOnConnected---This scan result is connected, info: " + getWifiInfoText(connectedWifiInfo2));
                    cipherType = wifiScanResult.getCipherType();
                    int rssiLevel2 = ((WifiSettingsPresenter) mo7getP()).getRssiLevel(wifiScanResult.getRssi());
                    b.b.m.c.a.a(TAG_W, "----updateOnConnected---type: " + cipherType + ", level: " + rssiLevel2);
                    updateConnected(cipherType, rssiLevel2);
                    connectedWifiInfo.setSecurity(wifiScanResult.getSecurity());
                }
            }
            b.b.m.c.a.a(TAG_W, "----updateOnConnected---info: " + connectedWifiInfo);
            showPageOnRight(WifiPagerType.CONNECTED, connectedWifiInfo);
            ((WifiSettingsPresenter) mo7getP()).saveNetwork(connectedName, cipherType);
            try {
                Collections.sort(this.mScanList);
            } catch (Exception e2) {
                b.b.m.c.a.c(TAG_W, "updateOnConnected sort Exception:" + e2);
            }
            this.mAdapter.checkItem(-1);
            this.mAdapter.notifyDataSetChanged();
            updateRecyclerViewHeight();
        }
    }

    private void updateOnDisconnected() {
        WifiInfo connectedWifiInfo = ((WifiSettingsPresenter) mo7getP()).getConnectedWifiInfo();
        b.b.m.c.a.a(TAG_W, "----updateOnDisconnected---doingConnect： " + this.doingConnect + "， info: " + getWifiInfoText(connectedWifiInfo));
        this.llConnected.setVisibility(8);
        if (connectedWifiInfo != null || this.doingConnect) {
            return;
        }
        showPageOnRight(WifiPagerType.NO_CONNECTION, null);
    }

    private void updateOnNoConnected() {
        b.b.m.c.a.a(TAG_W, "----updateOnNoConnected---");
        this.llConnected.setVisibility(8);
        showPageOnRight(WifiPagerType.NO_CONNECTION, null);
    }

    private void updateOnShowWifiInfo(boolean z) {
        b.b.m.c.a.a(TAG_W, ", updateOnShowWifiInfo, enable = " + z);
        if (!z) {
            this.flRight.setVisibility(8);
        } else {
            this.flRight.setVisibility(0);
            this.tvConnectedName.setText(((WifiSettingsPresenter) mo7getP()).getConnectedName());
        }
    }

    private void updateOnWifiStateChanged(boolean z) {
        b.b.m.c.a.a(TAG_W, ", updateOnWifiStateChanged, open = " + z);
        this.bydSwitch.setChecked(z, false);
        this.curScanInfo = null;
        this.addNetInfo = null;
        if (!z) {
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
            this.llOpen.setVisibility(8);
            this.llClose.setVisibility(0);
        } else {
            this.llOpen.setVisibility(0);
            this.llClose.setVisibility(8);
            startScan();
            updateRecyclerViewHeight();
        }
    }

    private void updateRecyclerViewHeight() {
        Resources resources = getContext().getResources();
        int size = this.mScanList.size();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.rcvWifi.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = ((int) resources.getDimension(R.dimen.dp_60)) * size;
        this.rcvWifi.setLayoutParams(layoutParams);
        this.mAdapter.notifyDataSetChanged();
    }

    private void updateUIOnSupplicantDisconnected(SupplicantState supplicantState, int i) {
        if (supplicantState != SupplicantState.DISCONNECTED) {
            return;
        }
        cancelConnectTimer();
        b.b.m.c.a.a(TAG_W, "--updateUIOnSupplicantDisconnected--", ", ---DISCONNECTED---preConnectState: " + this.preConnectState + ", errCode: " + i + ", doingConnect: " + this.doingConnect);
        if (this.preConnectState == null) {
            if (this.doingConnect) {
                return;
            }
            showPageOnRight(WifiPagerType.NO_CONNECTION, null);
            return;
        }
        switch (AnonymousClass8.$SwitchMap$android$net$wifi$SupplicantState[this.preConnectState.ordinal()]) {
            case 1:
                showConnectFailed(i, true);
                break;
            case 2:
            case 3:
                if (i == 0) {
                    showConnectFailed(i, true);
                } else {
                    showConnectFailed(i, false);
                }
                break;
            case 4:
            case 5:
            case 6:
                showConnectFailed(i, false);
                break;
        }
        h.a().a(new Runnable() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.7
            @Override // java.lang.Runnable
            public void run() {
                WifiSettingsPager.this.resetConnectState();
            }
        }, 120L, Priority.HIGH);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.a
    public String getDialogTag() {
        return TAG_W;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.activity_wifi;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ g mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ Object mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
        ArrayList arrayList = new ArrayList();
        this.mScanList = arrayList;
        WifiListAdapter wifiListAdapter = new WifiListAdapter(arrayList);
        this.mAdapter = wifiListAdapter;
        this.rcvWifi.setAdapter(wifiListAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context) { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.2
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(1);
        this.rcvWifi.setLayoutManager(linearLayoutManager);
        this.rcvWifi.setItemAnimator(new DefaultItemAnimator());
        this.mAdapter.setOnItemClickedListener(new e() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.3
            @Override // b.b.m.f.c.a.e
            public void onItemClicked(int i) {
                b.b.m.c.a.a(WifiSettingsPager.TAG_W, "--onItemClicked--position = " + i);
                if (i < 0 || i >= WifiSettingsPager.this.mScanList.size()) {
                    return;
                }
                if (WifiSettingsPager.this.doingConnect) {
                    z.b(WifiSettingsPager.this.getContext(), R.string.hint_muti_connect_operation);
                } else {
                    WifiSettingsPager wifiSettingsPager = WifiSettingsPager.this;
                    wifiSettingsPager.showInputOrConnect((WifiScanResult) wifiSettingsPager.mScanList.get(i));
                }
            }
        });
        this.mAdapter.setOnItemBtnClickedCallback(new d() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.4
            @Override // b.b.m.f.c.a.d
            public void onItemBtnClicked(int i, BtnType btnType) {
                b.b.m.c.a.a(WifiSettingsPager.TAG_W, "--onItemBtnClicked--position = " + i);
                if (i < 0 || i >= WifiSettingsPager.this.mScanList.size()) {
                    return;
                }
                if (WifiSettingsPager.this.doingConnect) {
                    z.b(WifiSettingsPager.this.getContext(), R.string.hint_muti_connect_operation);
                } else {
                    WifiSettingsPager wifiSettingsPager = WifiSettingsPager.this;
                    wifiSettingsPager.showChecked((WifiScanResult) wifiSettingsPager.mScanList.get(i));
                }
            }
        });
        WifiCache.setListeningScanResult(true);
    }

    @Override // b.b.m.e.f
    public boolean initHandler() {
        return true;
    }

    @Override // b.b.m.e.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        if (this.pagerMap == null) {
            this.pagerMap = new ConcurrentHashMap<>();
        }
        ((WifiSettingsPresenter) mo7getP()).init(context, 33);
        this.titleParent = (FrameLayout) this.mRootView.findViewById(R.id.fl_title_wireless);
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
        ((DampingScrollView) this.llOpen.findViewById(R.id.wifi_left_scroll_view)).setScroll(false);
        this.llOpen.findViewById(R.id.tv_add_network_wifi_left).setOnClickListener(this);
        this.ivRefresh.setOnClickListener(this);
        this.rlConnected.setOnClickListener(this);
        this.rlConnected.findViewById(R.id.iv_detail_connected_device_wifi_left).setOnClickListener(this);
    }

    @Override // b.b.m.e.a
    public int initWidth(Context context) {
        Resources resources = context.getResources();
        return (int) (resources.getConfiguration().orientation != 2 ? resources.getDimension(R.dimen.dp_612) : context.getResources().getDimension(R.dimen.dp_800));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_wireless /* 2131297203 */:
                b.b.m.e.d dVar = this.mCallback;
                if (dVar != null) {
                    dVar.onBackPress();
                }
                finish();
                break;
            case R.id.iv_detail_connected_device_wifi_left /* 2131297234 */:
                ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
                if (connectedWifiInfo != null) {
                    showPageOnRight(WifiPagerType.CONNECTED, connectedWifiInfo);
                }
                break;
            case R.id.iv_refresh_wifi_left /* 2131297337 */:
                startScan();
                break;
            case R.id.rl_connected_device_wifi_left /* 2131297950 */:
                updateConnected();
                checkConnectedWifi();
                break;
            case R.id.tv_add_network_wifi_left /* 2131298362 */:
                checkAddNetwork();
                break;
        }
    }

    public void onConnectStateChanged(WifiConnectParams wifiConnectParams) {
        b.b.m.c.a.a(TAG_W, "onConnectStateChanged", ", params: " + wifiConnectParams);
        ConnectState connectState = wifiConnectParams.state;
        this.currentConnectState = connectState;
        int i = AnonymousClass8.$SwitchMap$com$byd$bt$model$ConnectState[connectState.ordinal()];
        if (i == 1) {
            this.connectingWifiInfo = ((WifiSettingsPresenter) mo7getP()).getConnectingWifiInfo();
        } else if (i == 2) {
            this.doingConnect = false;
            this.connectingWifiInfo = null;
            cancelConnectTimer();
            updateOnConnected(wifiConnectParams.info);
            NetworkParams networkParams = this.addNetworkParams;
            if (networkParams != null && this.curConnectWifiInfo != null && networkParams.getName().equals(this.curConnectWifiInfo.getName())) {
                removeMessage(278);
            }
        } else if (i == 4) {
            b.b.m.c.a.a(TAG_W, "onConnectStateChanged", "--DISCONNECTED--preConnectStatus: " + this.preConnectStatus);
            if (this.preConnectStatus == ConnectState.CONNECTING || this.preConnectStatus == ConnectState.CONNECTED) {
                updateOnDisconnected();
            }
        }
        this.preConnectStatus = wifiConnectParams.state;
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        WifiCache.setListeningScanResult(false);
        super.onDestroy();
        b.b.i.d.w(BydApp.getContext(), false);
        ImageView imageView = this.ivRefresh;
        if (imageView != null) {
            imageView.clearAnimation();
        }
        this.isScanning = false;
        cancelConnectTimer();
        List<WifiScanResult> list = this.mScanList;
        if (list != null) {
            list.clear();
        }
        ConcurrentHashMap<WifiPagerType, a> concurrentHashMap = this.pagerMap;
        if (concurrentHashMap != null) {
            Iterator<a> it = concurrentHashMap.values().iterator();
            while (it.hasNext()) {
                it.next().onDestroy();
            }
            this.pagerMap.clear();
        }
        c.e(getClass().getName());
        this.curPager = null;
    }

    @Override // b.b.m.e.f
    public void onHandlerOther(Message message) {
        super.onHandlerOther(message);
        if (message.what != 278) {
            return;
        }
        z.b(getContext(), R.string.network_not_found_on_connecting);
        b.b.m.c.a.a(TAG_W, "---onHandlerOther--prePagerType = " + this.prePagerType);
        showPageOnRight(this.prePagerType, this.preBydData);
        ((WifiSettingsPresenter) mo7getP()).cancelConnect();
        this.doingConnect = false;
    }

    @Override // b.b.m.e.f
    public void onHandlerUpdateUI(Message message) {
        int i = message.arg1;
        if (i == 257) {
            onScanResult((List) message.obj);
        }
        if (i == 277) {
            b.b.m.c.a.a(TAG_W, "----onHandlerUpdateUI--ARG1_WIFI_RSSI_CHANGED--msg: " + message);
            onWifiRssiChanged();
            return;
        }
        if (i == 279) {
            b.b.m.c.a.a(TAG_W, "----onHandlerUpdateUI--ADD_NET_CIPHER_TYPE_ERR--msg: " + message);
            onCipherTypeErr();
            return;
        }
        switch (i) {
            case 272:
                b.b.m.c.a.a(TAG_W, "----onHandlerUpdateUI--CONNECT_CHANGED--msg: " + message);
                onConnectStateChanged((WifiConnectParams) message.obj);
                break;
            case 273:
                b.b.m.c.a.a(TAG_W, "----onHandlerUpdateUI--CONNECT_FAILED--msg: " + message);
                onConnectFailed(((Integer) message.obj).intValue());
                break;
            case 274:
                b.b.m.c.a.a(TAG_W, "----onHandlerUpdateUI--ENABLE_CHANGED--msg: " + message);
                onWifiStateChanged((EnableState) message.obj);
                break;
            case 275:
                b.b.m.c.a.a(TAG_W, "----onHandlerUpdateUI--ARG1_WIFI_SUPPLICANT_STATE_CHANGED--msg: " + message);
                onSupplicantStateChanged((SupplicantParams) message.obj);
                break;
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderL(boolean z) {
        super.onLtrRudderL(z);
        this.titleParent.addView(this.lf.inflate(R.layout.title_wireless_settings, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        super.onLtrRudderR(z);
        this.titleParent.addView(this.lf.inflate(R.layout.title_wireless_settings_r, (ViewGroup) null));
        loadTitleOnLayoutLocked();
    }

    public void onPagerBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
        b.b.m.c.a.a(TAG_W, "clazz = " + cls.getName() + ", type = " + pagerBtnType);
        String name = cls.getName();
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
            removeMessage(278);
            ((WifiSettingsPresenter) mo7getP()).cancelConnect();
        } else if (ConnectedWifiInfoPager.class.getName().equals(name)) {
            onConnectedWifiInfoPagerClicked(pagerBtnType, (ConnectedWifiInfo) obj);
        } else if (WifiAddNetworkPager.class.getName().equals(name)) {
            onAddNetPagerClicked(pagerBtnType, (NetworkParams) obj);
        }
    }

    @Override // b.b.m.e.a
    public void onResume() {
        super.onResume();
        boolean zIsWifiEnabled = ((WifiSettingsPresenter) mo7getP()).isWifiEnabled();
        b.b.m.c.a.a(TAG_W, "---onResume---isOpen：" + zIsWifiEnabled);
        this.bydSwitch.setChecked(zIsWifiEnabled, false);
        updateOnNoConnected();
        updateOnWifiStateChanged(zIsWifiEnabled);
        if (!zIsWifiEnabled) {
            this.enableOperation = true;
            ((WifiSettingsPresenter) mo7getP()).enabled(true);
        } else if (!((WifiSettingsPresenter) mo7getP()).isConnected()) {
            b.b.m.c.a.a(TAG_W, ", --onResume---isConnected: false.");
            updateOnDisconnected();
        } else {
            b.b.m.c.a.a(TAG_W, ", --onResume---isConnected: true.");
            checkConnectedWifi();
            updateOnConnected(null);
        }
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        super.onRtlRudderL(z);
        this.titleParent.addView(this.lf.inflate(R.layout.title_wireless_settings_rtl, (ViewGroup) null));
        loadTitleOnLayoutLocked();
        setViewGroupRtl(this.titleParent, false);
    }

    public void onScanFinished() {
        b.b.m.c.a.a(TAG_W, "---onScanFinished---");
        this.ivRefresh.clearAnimation();
        this.isScanning = false;
        ((WifiSettingsPresenter) mo7getP()).autoScan();
    }

    public void onScanResult(List<ScanResult> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        b.b.m.c.a.a(TAG_W, "--onScanResult--scanList.size: " + list.size());
        List<WifiScanResult> list2 = this.mScanList;
        if (list2 != null) {
            list2.clear();
        }
        WifiInfo connectedWifiInfo = ((WifiSettingsPresenter) mo7getP()).getConnectedWifiInfo();
        b.b.m.c.a.a(TAG_W, "--onScanResult--connected info: " + getWifiInfoText(connectedWifiInfo));
        for (ScanResult scanResult : list) {
            if (scanResult.SSID != null) {
                b.b.m.c.a.a(TAG_W, "--onScanResult--r.SSID : " + b.b.m.g.f.g(scanResult.SSID) + ", r.BSSID: " + b.b.m.g.f.e(scanResult.BSSID) + ", " + scanResult.capabilities);
                if (connectedWifiInfo == null || !scanResult.BSSID.equals(connectedWifiInfo.getBSSID())) {
                    WifiScanResult wifiScanResult = new WifiScanResult(scanResult, scanResult.level, scanResult.capabilities);
                    wifiScanResult.setSaved(((WifiSettingsPresenter) mo7getP()).isWifiSaved(wifiScanResult));
                    wifiScanResult.setRssiLevel(((WifiSettingsPresenter) mo7getP()).getRssiLevel(scanResult.level));
                    this.mScanList.add(wifiScanResult);
                } else {
                    b.b.m.c.a.a(TAG_W, "--onScanResult--connectedSSID: " + b.b.m.g.f.g(connectedWifiInfo.getSSID()) + ", r.SSID: " + b.b.m.g.f.g(scanResult.SSID) + ", " + scanResult.capabilities);
                    ConnectedWifiInfo connectedWifiInfo2 = this.curConnectWifiInfo;
                    if (connectedWifiInfo2 != null) {
                        connectedWifiInfo2.setSecurity(scanResult.capabilities);
                        updateConnected(CipherType.get(scanResult.capabilities), ((WifiSettingsPresenter) mo7getP()).getRssiLevel(scanResult.level));
                    }
                    a aVar = this.pagerMap.get(WifiPagerType.CONNECTED);
                    if (aVar != null) {
                        aVar.updateData(this.curConnectWifiInfo);
                    }
                    i.b(getContext(), CUR_CONNECT_WIFI_SECURITY, scanResult.capabilities);
                }
            }
        }
        b.b.m.c.a.a(TAG_W, "--onScanResult--mScanList.size: " + this.mScanList.size());
        try {
            Collections.sort(this.mScanList);
        } catch (Exception e2) {
            b.b.m.c.a.c(TAG_W, "onScanResult sort Exception:" + e2);
        }
        updateRecyclerViewHeight();
    }

    public void onScanTimeout() {
        b.b.m.c.a.a(TAG_W, "---onScanTimeout---");
        this.ivRefresh.clearAnimation();
        this.isScanning = false;
    }

    public void onWifiRssiChanged() {
        ConnectedWifiInfo connectedWifiInfo = getConnectedWifiInfo();
        b.b.m.c.a.a(TAG_W, "----onWifiRssiChanged---info: " + connectedWifiInfo);
        if (connectedWifiInfo == null) {
            return;
        }
        CipherType cipherType = connectedWifiInfo.getCipherType();
        int rssiLevel = ((WifiSettingsPresenter) mo7getP()).getRssiLevel(connectedWifiInfo.getInfo().getRssi());
        b.b.m.c.a.a(TAG_W, "----onWifiRssiChanged---level: " + rssiLevel);
        updateConnected(cipherType, rssiLevel);
        a aVar = this.pagerMap.get(WifiPagerType.CONNECTED);
        if (aVar != null) {
            aVar.updateData(connectedWifiInfo);
        }
    }

    public void onWifiStateChanged(EnableState enableState) {
        b.b.m.c.a.a(TAG_W, ", onWifiStateChanged, state = " + enableState);
        int i = AnonymousClass8.$SwitchMap$com$byd$bt$model$EnableState[enableState.ordinal()];
        if (i == 2) {
            updateOnWifiStateChanged(true);
        } else {
            if (i != 3) {
                return;
            }
            updateOnWifiStateChanged(false);
        }
    }

    public void startConnectTimer() {
        b.b.m.c.a.a(this.TAG, "---startConnectTimer---");
        if (this.connectTimer == null) {
            this.connectTimer = new Timer();
        }
        this.connectTimer.schedule(new TimerTask() { // from class: com.byd.systemsettings.wifi2.WifiSettingsPager.5
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                WifiSettingsPager.this.onConnectTimeout();
            }
        }, TIMEOUT_CONNECT);
    }

    public void startRefreshAnimation() {
        b.b.m.c.a.a(TAG_W, "---startRefreshAnimation---");
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setStartOffset(0L);
        this.ivRefresh.startAnimation(rotateAnimation);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.h
    public WifiSettingsPresenter newP() {
        return new WifiSettingsPresenter();
    }

    private void updateConnected(CipherType cipherType, int i) {
        this.ivIconConnected.setImageResource(ResUtils.getRssiImgId(cipherType, i));
    }
}
