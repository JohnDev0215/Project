package com.engine.tbox.item;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.engine.tbox.auth.AuthStateMachine;
import com.engine.tbox.core.conn.AbstractConnection;
import com.engine.tbox.core.conn.ConnectionState;
import com.engine.tbox.core.conn.ConnectionStateChangeCallback;
import com.engine.tbox.core.conn.IResponse;
import com.engine.tbox.core.data.CountSyncMessage;
import com.engine.tbox.core.net.IEventHandler;
import com.engine.tbox.core.net.SocketStateMachine;
import com.engine.tbox.core.utils.LogUtils;
import com.engine.tbox.core.utils.TypeUtils;
import com.engine.tbox.data.ActiveCode;
import com.engine.tbox.data.ActiveCodeV2;
import com.engine.tbox.data.Apn1Info;
import com.engine.tbox.data.ApnArgs;
import com.engine.tbox.data.ApnInfo;
import com.engine.tbox.data.DigitalKeyAccountInfo;
import com.engine.tbox.data.DigitalKeyBean;
import com.engine.tbox.data.FtpInfo;
import com.engine.tbox.data.NetworkFlow;
import com.engine.tbox.data.NetworkOperator;
import com.engine.tbox.data.NetworkQueryResult;
import com.engine.tbox.data.OtaNotification;
import com.engine.tbox.data.ReservationCharging;
import com.engine.tbox.data.TboxStaticInfo;
import com.engine.tbox.iface.ITboxIface;
import com.engine.tbox.iface.ITboxListener;
import com.engine.tbox.item.LogExport;
import com.engine.tbox.item.SendMessageHandler;
import com.engine.tbox.netd.NetdClient;
import com.engine.tbox.ota.TboxOtaMachine;
import com.engine.tbox.utils.AESUtils;
import com.engine.tbox.utils.BroadCastUtils;
import com.engine.tbox.utils.FileUtils;
import com.engine.tbox.utils.NitzUtils;
import com.engine.tbox.utils.SharePreferenceUtils;
import com.ts.lib.power.data.CarPowerData;
import com.ts.lib.power.data.CarPowerSignalStatus;
import com.ts.lib.power.data.CarPowerWorkModeStatus;
import com.ts.lib.power.listener.CarPowerEventListener;
import com.ts.lib.power.manager.CarPowerManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes.dex */
public class TBoxConnection extends AbstractConnection {
    private static final int ACTIVE_TIMEOUT = 60000;
    private static final int CHANGE_ADB_SWITCH_CLOSE = 1;
    private static final int CHANGE_ADB_SWITCH_FAILED = 1;
    private static final int CHANGE_ADB_SWITCH_OPEN = 0;
    private static final int CHANGE_ADB_SWITCH_SUCCESS = 0;
    private static final long CLOUD_SERVICE_TIMEOUT = 1500;
    private static final String CLOUD_TCP_STATUS = "sys.tcp_connect_status";
    private static final long FTP_UPGRAD_REQ_TIMEOUT = 1500;
    private static final String HELLO = "Hello";
    private static final int INVALID_APP_VERSION = 0;
    private static final String LOCAL_IP = "127.0.0.1";
    private static final int MAX_APP_VERSION = 3;
    private static final int MAX_PASS_ID_LENGTH = 24;
    private static final int MAX_PORT = 65535;
    private static final int MIN_NITZ_DATA_LEN = 9;
    private static final int MIN_PORT = 0;
    private static final long NITZ_REQ_TIMEOUT = 1500;
    private static final int PORT = 28889;
    private static final int PRIVATE_POLICY_RESULT_FAILED = 2;
    private static final int PRIVATE_POLICY_RESULT_INVALID = 0;
    private static final int PRIVATE_POLICY_RESULT_SUCCESS = 1;
    public static final int PRIVATE_POLICY_STATUS_AGREE = 1;
    public static final int PRIVATE_POLICY_STATUS_DISAGREE = 2;
    public static final String PROP_PERSIST_NET_LIMIT = "persist.net.limit";
    private static final long REACHABLE_DELAY = 10000;
    public static final String REMOTE_IP = "172.16.5.101";
    private static final int TIMEOUT = 600;
    private static final int V1G_MODE = 3;
    private static final int V2G_MODE = 4;
    private static final int V2G_MODE_2 = 225;
    private static final String V2G_MODE_PROP = "persist.sys.v2g_model_status";
    private static final String VIRTUAL_VIN_BEGIN = "BYD";
    private static final int VIRTUAL_VIN_LENGTH = 19;
    private static String cloudVer = "";
    private MessageCache cache;
    private Handler connectionHandler;
    private HandlerThread connectionHandlerThread;
    private Apn1Info mApn1Info;
    private ApnInfo mApnInfo;
    private Auth mAuth;
    private AuthStateMachine mAuthStateMachine;
    private BroadCastUtils mBroadCastUtils;
    private SocketChannel mChannel;
    private CountryCodeConfigManager mCodeConfigManager;
    private Context mContext;
    private ITboxListener mListener;
    private LogExport mLogExport;
    private FtpInfo mLogExportFtpInfo;
    private SocketStateMachine mMachine;
    private String mNfcDeleteSigleKey;
    private String mNfcIphoneKey;
    private String mNitzData;
    private NitzUtils mNitzUtils;
    private CarPowerManager mPowerManger;
    private RealNameChange mRealNameChange;
    private Semaphore mRestartTBoxSemaphore;
    private SharePreferenceUtils mSharedPreferenceUtils;
    private TboxOtaMachine mTBoxOtaMachine;
    private FtpInfo mUpgradeFtpInfo;
    private RecvMessageHandler recvHandler;
    private HandlerThread recvHandlerThread;
    private SendMessageHandler sendHandler;
    private HandlerThread sendHandlerThread;
    private final Map<ActiveCode.ProviderType, Semaphore> mActiveSemaphore = new HashMap();
    private final Map<Integer, Semaphore> mActiveCodeV2Semaphore = new HashMap();
    private final TBoxCache mCache = new TBoxCache();
    private final NetdClient mNetdClient = new NetdClient();
    private boolean mApnState = false;
    private boolean mAuthVinChanged = false;
    private final Object mApnMutex = new Object();
    private boolean enableAuth = true;
    private Boolean mPrivatePolicyStatus = false;
    private boolean mTboxReachable = false;
    private CarPowerEventListener mCarPowerListener = new CarPowerEventListener() { // from class: com.engine.tbox.item.TBoxConnection.2
        public void onCarPowerWorkModeChangeEvent(CarPowerWorkModeStatus carPowerWorkModeStatus) {
            LogUtils.i(AbstractConnection.TAG, "onCarPowerWorkModeChangeEvent:" + carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal(), new Object[0]);
            if (carPowerWorkModeStatus.getCarPowerWorkModeStatusEnum().getVal() == CarPowerWorkModeStatus.CarPowerWorkModeStatusEnum.STR_SUSPEND_EXIT.getVal()) {
                TBoxConnection.this.socketReconnect();
            }
        }

        public void onCarPowerSignalChangeEvent(CarPowerSignalStatus carPowerSignalStatus) {
            LogUtils.i(AbstractConnection.TAG, "onCarPowerSignalChangeEvent:" + carPowerSignalStatus.getCarPowerSignalStatusEnum().getVal(), new Object[0]);
        }

        public void onCarPowerServiceConnected() {
            LogUtils.i(AbstractConnection.TAG, "onCarPowerServiceConnected", new Object[0]);
        }

        public void onCarPowerServiceDisconnected() {
            LogUtils.i(AbstractConnection.TAG, "onCarPowerServiceDisconnected", new Object[0]);
        }

        public void onCarPowerEvent(int i, int i1, CarPowerData carPowerData) {
            LogUtils.i(AbstractConnection.TAG, "onCarPowerEvent i:" + i + " i1:" + i1 + " carPowerData:" + carPowerData, new Object[0]);
        }

        public void onCarPowerDisplayEvent(int type, int displayPanelId, int value) {
            LogUtils.i(AbstractConnection.TAG, "onCarPowerDisplayEvent: type:" + type + " displayPanelId:" + displayPanelId + " value:" + value, new Object[0]);
        }
    };

    public TBoxConnection(Context context) {
        this.mContext = context;
        Auth auth = new Auth(context);
        this.mAuth = auth;
        auth.enableAuth(this.enableAuth);
        this.mActiveSemaphore.put(ActiveCode.ProviderType.MAP, new Semaphore(1));
        this.mActiveSemaphore.put(ActiveCode.ProviderType.VR, new Semaphore(1));
        this.mActiveSemaphore.put(ActiveCode.ProviderType.MUSIC, new Semaphore(1));
        this.mRestartTBoxSemaphore = new Semaphore(1);
        this.connectionHandlerThread = new HandlerThread("connection Handler");
        this.sendHandlerThread = new HandlerThread("ConnectionSendThread");
        this.recvHandlerThread = new HandlerThread("ConnectionRecvThread");
        this.connectionHandlerThread.start();
        this.sendHandlerThread.start();
        this.recvHandlerThread.start();
        Handler handler = new Handler(this.connectionHandlerThread.getLooper());
        this.connectionHandler = handler;
        LogUtils.init(context, handler);
        this.sendHandler = new SendMessageHandler(this.sendHandlerThread.getLooper());
        this.recvHandler = new RecvMessageHandler(this.recvHandlerThread.getLooper());
        this.sendHandler.post(new Runnable() { // from class: com.engine.tbox.item.-$$Lambda$TBoxConnection$41eV0-RS08rLBoAMjXg7WggR_rA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$TBoxConnection();
            }
        });
        addHandler(this.connectionHandler);
        this.cache = new MessageCache();
        this.sendHandler.addConnection(this);
        this.sendHandler.addTBoxConnection(this);
        this.recvHandler.addConnection(this);
        this.recvHandler.addTBoxConnection(this);
        this.recvHandler.addMessageCache(this.cache);
        authStateMachineInit();
        otaMachineInit();
        exportLogInit();
        initSocketConnection();
        initBroadcast();
        initSharedPreference();
        initConfigManager();
        initNitz(context, this.connectionHandler);
        registerConnectionStateCallback(new ConnectionStateChangeCallback() { // from class: com.engine.tbox.item.TBoxConnection.1
            @Override // com.engine.tbox.core.conn.ConnectionStateChangeCallback
            public void onConnectionState(ConnectionState state) {
                LogUtils.i(AbstractConnection.TAG, "onConnectionState:" + state + " enable Auth:" + TBoxConnection.this.enableAuth, new Object[0]);
                int currState = 1;
                int i = AnonymousClass30.$SwitchMap$com$engine$tbox$core$conn$ConnectionState[state.ordinal()];
                if (i == 1) {
                    currState = 0;
                    TBoxConnection.this.onTBoxConnectedInternal();
                } else if (i == 2) {
                    TBoxConnection.this.onTBoxDisconnectedInternal();
                    TBoxConnection.this.mAuth.setAuth(false);
                    TBoxConnection.this.mBroadCastUtils.sendAuthState(false);
                } else {
                    LogUtils.w(AbstractConnection.TAG, "onConnectionState:" + state, new Object[0]);
                }
                if (TBoxConnection.this.mListener != null) {
                    TBoxConnection.this.mListener.tboxConnectState(currState);
                }
            }
        });
        initHandler();
        initPrivatePolicyStatus();
        initCarPowerManager();
        initRealName();
    }

    /* JADX INFO: renamed from: com.engine.tbox.item.TBoxConnection$30, reason: invalid class name */
    static /* synthetic */ class AnonymousClass30 {
        static final /* synthetic */ int[] $SwitchMap$com$engine$tbox$core$conn$ConnectionState;

        static {
            int[] iArr = new int[ConnectionState.values().length];
            $SwitchMap$com$engine$tbox$core$conn$ConnectionState = iArr;
            try {
                iArr[ConnectionState.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$engine$tbox$core$conn$ConnectionState[ConnectionState.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private void initRealName() {
        RealNameChangeCallback callback = new RealNameChangeCallback() { // from class: com.engine.tbox.item.-$$Lambda$TBoxConnection$EGTC2BoThzN51375BwgevY7y0_M
            @Override // com.engine.tbox.item.RealNameChangeCallback
            public final void realNameStatusChange(int i) {
                this.f$0.setRealNameChange(i);
            }
        };
        this.mRealNameChange = new RealNameChange(callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateTboxReachable, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public void lambda$updateTboxReachable$1$TBoxConnection() {
        try {
            this.mTboxReachable = InetAddress.getByName(REMOTE_IP).isReachable(TIMEOUT);
        } catch (Exception exp) {
            LogUtils.e(AbstractConnection.TAG, "updateTboxReachable:" + exp.getMessage(), new Object[0]);
        }
        this.sendHandler.postDelayed(new Runnable() { // from class: com.engine.tbox.item.-$$Lambda$TBoxConnection$T_o5cLF8du00F36aHevtekxsYL0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateTboxReachable$1$TBoxConnection();
            }
        }, REACHABLE_DELAY);
    }

    private void initCarPowerManager() {
        LogUtils.i(AbstractConnection.TAG, "initCarPowerManager", new Object[0]);
        CarPowerManager carPowerManager = CarPowerManager.getInstance(this.mContext, this.mCarPowerListener);
        this.mPowerManger = carPowerManager;
        carPowerManager.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidVirtualVin(String vin) {
        if (vin != null && vin.length() == 19 && vin.contains(VIRTUAL_VIN_BEGIN)) {
            return true;
        }
        return false;
    }

    private void initNitz(Context context, Handler handler) {
        this.mNitzUtils = new NitzUtils(context, handler, new NitzUtils.NitzCallback() { // from class: com.engine.tbox.item.TBoxConnection.3
            @Override // com.engine.tbox.utils.NitzUtils.NitzCallback
            public void onDelete() {
                LogUtils.i(AbstractConnection.TAG, "NitzUtils delete", new Object[0]);
                TBoxConnection.this.mNitzUtils.setNitzData(TBoxConnection.this.mNitzData);
            }
        });
    }

    private void initPrivatePolicyStatus() {
        this.mPrivatePolicyStatus = this.mSharedPreferenceUtils.getPrivatePolicyStatus();
        LogUtils.i(AbstractConnection.TAG, "initPrivatePolicyStatus mPrivatePolicyStatus:" + this.mPrivatePolicyStatus, new Object[0]);
    }

    private void initSharedPreference() {
        this.mSharedPreferenceUtils = new SharePreferenceUtils(this.mContext);
    }

    private void initConfigManager() {
        this.mCodeConfigManager = new CountryCodeConfigManager(this.mContext);
    }

    public int getCountryCode() {
        String code = this.mCodeConfigManager.getCountryCode();
        try {
            return Integer.parseInt(code);
        } catch (Exception exp) {
            exp.printStackTrace();
            return 47;
        }
    }

    public boolean isAuthEnable() {
        return this.mAuth.isAuthEnable();
    }

    private void initBroadcast() {
        BroadCastUtils broadCastUtils = new BroadCastUtils();
        this.mBroadCastUtils = broadCastUtils;
        broadCastUtils.registerBroadCast(this.mContext, new BroadCastUtils.BroadcastInterface() { // from class: com.engine.tbox.item.TBoxConnection.4
            private static final int AUTH_DATA_FAILED_CASE = 2;
            private static final int DEFAULT_TEST_CASE = 1;
            private static final int STR_TEST_CASE = 1;

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onPlaneRes(int res) {
                LogUtils.i(AbstractConnection.TAG, "onPlaneRes:" + res, new Object[0]);
                TBoxConnection.this.mSharedPreferenceUtils.deleteReservationCharging();
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onPlaneChargeRes(int res) {
                LogUtils.i(AbstractConnection.TAG, "onPlaneChargeRes:" + res, new Object[0]);
                TBoxConnection.this.sendPlaneChargeRes(res);
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onPlaneUpload(ReservationCharging rc) {
                LogUtils.i(AbstractConnection.TAG, "onPlaneUpload:" + rc, new Object[0]);
                TBoxConnection.this.sendPlaneUpload(rc);
                TBoxConnection.this.mSharedPreferenceUtils.storeReservationCharging(rc);
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onChargeUpload(int charge) {
                LogUtils.i(AbstractConnection.TAG, "onChargeUpload:" + charge, new Object[0]);
                TBoxConnection.this.sendChargeControl(charge);
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onGetPlane() {
                LogUtils.i(AbstractConnection.TAG, "onGetPlane", new Object[0]);
                Intent intent = TBoxConnection.this.mSharedPreferenceUtils.getStoreReservationCharging();
                if (intent != null) {
                    TBoxConnection.this.mBroadCastUtils.sendPlaneBroadCast(intent);
                } else {
                    LogUtils.i(AbstractConnection.TAG, "no plan store in ivi", new Object[0]);
                }
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onAppVersionCode(int code) {
                LogUtils.i(AbstractConnection.TAG, "onAppVersionCode:" + code, new Object[0]);
                if (TBoxConnection.this.isValidVersionCode(code)) {
                    TBoxConnection.this.sendAppVersionCode(code);
                }
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onPrivatePolicyStatusChanged(int status, int op, String policyVersion, String date) {
                boolean result = TBoxConnection.this.mSharedPreferenceUtils.storePrivatePolicy(status, op, date, policyVersion);
                LogUtils.i(AbstractConnection.TAG, "onPrivatePolicyStatusChanged status:" + status + " op:" + op + " policyVersion:" + policyVersion + " date:" + date + " store reulst:" + result, new Object[0]);
                TBoxConnection.this.mPrivatePolicyStatus = Boolean.valueOf(status == 1);
                TBoxConnection.this.mSharedPreferenceUtils.setPrivatePolicyStatus(TBoxConnection.this.mPrivatePolicyStatus);
                if (TBoxConnection.this.mState == ConnectionState.CONNECTED) {
                    TBoxConnection.this.setPrivatePolicy(status, op, date, policyVersion);
                }
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onUsbRemove() {
                LogUtils.i(AbstractConnection.TAG, "onUsbRemove", new Object[0]);
                TBoxConnection.this.mLogExport.stopExport();
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onTest(int testcase) {
                LogUtils.i(AbstractConnection.TAG, "onTest:" + testcase, new Object[0]);
                if (testcase != 2) {
                    TBoxConnection.this.mMachine.reconnectImmediately();
                } else {
                    TBoxConnection.this.recvMessageDescryptedFailed();
                }
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onBindCloudServiceReq() {
                LogUtils.i(AbstractConnection.TAG, "bindCloudServiceReq", new Object[0]);
                TBoxConnection.this.sendBindCloudReq();
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onBindInfoAck(int ack) {
                LogUtils.i(AbstractConnection.TAG, "bindInfoAck ack:" + ack, new Object[0]);
                TBoxConnection.this.sendBindCloudRes(ack);
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onV2G539(String json) {
                LogUtils.i(AbstractConnection.TAG, "onV2G539 json:" + json, new Object[0]);
                if (!TextUtils.isEmpty(json)) {
                    TBoxConnection.this.send539or540(true, json);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "onV2G539 with error json:" + json, new Object[0]);
            }

            @Override // com.engine.tbox.utils.BroadCastUtils.BroadcastInterface
            public void onV2G540(String json) {
                LogUtils.i(AbstractConnection.TAG, "onV2G540 json:" + json, new Object[0]);
                if (!TextUtils.isEmpty(json)) {
                    TBoxConnection.this.send539or540(false, json);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "onV2G540 with error json:" + json, new Object[0]);
            }
        });
    }

    private int getV2GMode() {
        int anInt = SystemProperties.getInt(V2G_MODE_PROP, 0);
        if (anInt == 1) {
            return 3;
        }
        return anInt == 2 ? 4 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send539or540(boolean is539, String json) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "send539or540 when TBox Connection failed", new Object[0]);
        } else {
            TboxMessage tboxMessage = MessageUtils.build539Or540Message(is539, is539 ? getV2GMode() : 225, json);
            sendAsyncMessage(tboxMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidVersionCode(int code) {
        return code <= 3 && code >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAppVersionCode(int code) {
        LogUtils.i(AbstractConnection.TAG, "sendAppVersionCode:" + code, new Object[0]);
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendAppVersionCode privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return;
        }
        byte[] data = {(byte) code};
        sendAsyncMessage(MessageUtils.buildAppVersionCode(data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTBoxConnectedInternal() {
        this.mCache.clear();
        if (this.enableAuth) {
            this.mAuthStateMachine.resetAuth();
            this.mAuthStateMachine.startAuth();
        }
        synchronized (this.mApnMutex) {
            this.mApnState = false;
        }
        this.mRestartTBoxSemaphore.release();
        this.mNitzData = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTBoxDisconnectedInternal() {
        LogUtils.i(AbstractConnection.TAG, "onTBoxDisconnectedInternal", new Object[0]);
        notifyTboxCloudConnected(2);
    }

    private void authStateMachineInit() {
        LogUtils.i(AbstractConnection.TAG, "authStateMachineInit", new Object[0]);
        AuthStateMachine authStateMachine = new AuthStateMachine("AuthStateMachine");
        this.mAuthStateMachine = authStateMachine;
        authStateMachine.addTboxConnection(this);
        this.mAuthStateMachine.registerAuthResultCallback(new AuthStateMachine.AuthResultCallback() { // from class: com.engine.tbox.item.TBoxConnection.5
            @Override // com.engine.tbox.auth.AuthStateMachine.AuthResultCallback
            public void authFailed() {
                LogUtils.i(AbstractConnection.TAG, "authFailed", new Object[0]);
                TBoxConnection.this.mAuth.setAuth(false);
                TBoxConnection.this.mBroadCastUtils.sendAuthState(false);
                TBoxConnection.this.mAuth.resetAesKey();
                TBoxConnection.this.mAuthVinChanged = true;
            }

            @Override // com.engine.tbox.auth.AuthStateMachine.AuthResultCallback
            public void authSuccess() {
                LogUtils.i(AbstractConnection.TAG, "authSuccess mAuthVinChanged:" + TBoxConnection.this.mAuthVinChanged, new Object[0]);
                TBoxConnection.this.getNitzData();
                if (TBoxConnection.this.mAuthVinChanged) {
                    TBoxConnection.this.getVirtualVinInternal();
                } else {
                    TBoxConnection.this.getVirtualVin();
                }
                TBoxConnection.this.mAuthVinChanged = false;
                TBoxConnection.this.mSharedPreferenceUtils.readPrivatePolicy(new SharePreferenceUtils.OnPrivatePolicyReady() { // from class: com.engine.tbox.item.TBoxConnection.5.1
                    @Override // com.engine.tbox.utils.SharePreferenceUtils.OnPrivatePolicyReady
                    public void onPirvatePolicyReady(int auth, int action, String time, String version) {
                        LogUtils.i(AbstractConnection.TAG, "onPirvatePolicyReady auth:" + auth + " action:" + action + " time:" + time + " version:" + version, new Object[0]);
                        if (action == 0) {
                            TBoxConnection.this.setPrivatePolicy(auth, 0, null, null);
                        } else if (action != -1) {
                            TBoxConnection.this.setPrivatePolicy(auth, action, time, version);
                        }
                    }
                });
                TBoxConnection.this.cloudConnStateInitAfterTboxConnected();
                TBoxConnection tBoxConnection = TBoxConnection.this;
                tBoxConnection.setRealNameChange(tBoxConnection.mRealNameChange.getRealNameStatus());
            }
        });
        if (this.enableAuth) {
            this.mAuthStateMachine.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cloudConnStateInitAfterTboxConnected() {
        int cloudConnState = requestCloudServiceConnectState();
        LogUtils.i(AbstractConnection.TAG, "cloudConnStateInitAfterTboxConnected cloudConnState:" + cloudConnState, new Object[0]);
        Message msg = this.recvHandler.obtainMessage();
        msg.what = 14;
        msg.arg1 = cloudConnState;
        this.recvHandler.sendMessage(msg);
    }

    private Auth getAuth() {
        return this.mAuth;
    }

    private void exportLogInit() {
        LogUtils.i(AbstractConnection.TAG, "exportLogInit", new Object[0]);
        LogExport logExport = LogExport.getInstance(this);
        this.mLogExport = logExport;
        logExport.registerLogExportCallback(new LogExport.LogExportCallback() { // from class: com.engine.tbox.item.TBoxConnection.6
            @Override // com.engine.tbox.item.LogExport.LogExportCallback
            public void exportLogProgress(int progress) {
                super.exportLogProgress(progress);
                if (TBoxConnection.this.mListener != null) {
                    TBoxConnection.this.mListener.exportLogProgress(progress);
                }
            }

            @Override // com.engine.tbox.item.LogExport.LogExportCallback
            public void exportLogResult(int result) {
                super.exportLogResult(result);
                if (TBoxConnection.this.mListener != null) {
                    TBoxConnection.this.mListener.exportLogResult(result);
                }
                if (result == 0) {
                    TBoxConnection.this.sendExportResult(true);
                } else if (5 != result) {
                    TBoxConnection.this.sendExportResult(false);
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.engine.tbox.item.TBoxConnection$7, reason: invalid class name */
    class AnonymousClass7 implements SendMessageHandler.HeartBeatCallback {
        AnonymousClass7() {
        }

        @Override // com.engine.tbox.item.SendMessageHandler.HeartBeatCallback
        public void heartBeatFailed() {
            LogUtils.e(AbstractConnection.TAG, "heartbeat failed", new Object[0]);
            TBoxConnection.this.resetHeartBeatCount();
            if (TBoxConnection.this.sendHandler.hasMessages(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ)) {
                LogUtils.d(AbstractConnection.TAG, "remove the heartbeat message when heartBeatFailed", new Object[0]);
                TBoxConnection.this.sendHandler.removeMessages(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ);
            }
            TBoxConnection.this.sendHandler.post(new Runnable() { // from class: com.engine.tbox.item.-$$Lambda$TBoxConnection$7$qIFexD2DrJ3j_5gDrOduRvxOlao
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$heartBeatFailed$0$TBoxConnection$7();
                }
            });
        }

        public /* synthetic */ void lambda$heartBeatFailed$0$TBoxConnection$7() {
            LogUtils.i(AbstractConnection.TAG, "heartbeat stop the thread", new Object[0]);
            TBoxConnection.this.stop();
            TBoxConnection.this.start();
        }
    }

    private void initHandler() {
        LogUtils.i(AbstractConnection.TAG, "initHandler", new Object[0]);
        this.sendHandler.registerHeartBeatCallback(new AnonymousClass7());
        this.recvHandler.start();
    }

    private void initSocketConnection() {
        LogUtils.i(AbstractConnection.TAG, "initSocketConnection", new Object[0]);
        SocketStateMachine socketStateMachine = new SocketStateMachine("TBoxClientSocket", new SocketStateMachine.SocketInterface() { // from class: com.engine.tbox.item.TBoxConnection.8
            @Override // com.engine.tbox.core.net.SocketStateMachine.SocketInterface
            public boolean createSocket(Selector selector) {
                LogUtils.d(AbstractConnection.TAG, "create socket:" + selector.hashCode(), new Object[0]);
                try {
                    TBoxConnection.this.mChannel = SocketChannel.open();
                    TBoxConnection.this.mChannel.setOption((SocketOption<Boolean>) StandardSocketOptions.TCP_NODELAY, Boolean.TRUE);
                    TBoxConnection.this.mChannel.configureBlocking(false);
                    TBoxConnection.this.mChannel.register(selector, 9);
                    TBoxConnection.this.mChannel.connect(new InetSocketAddress(TBoxConnection.REMOTE_IP, TBoxConnection.PORT));
                    return true;
                } catch (Exception exp) {
                    exp.printStackTrace();
                    return false;
                }
            }

            @Override // com.engine.tbox.core.net.SocketStateMachine.SocketInterface
            public void destroySocket(Selector selector) {
                LogUtils.d(AbstractConnection.TAG, "destroy socket:" + selector.hashCode(), new Object[0]);
            }
        });
        this.mMachine = socketStateMachine;
        socketStateMachine.registerHandler(new IEventHandler() { // from class: com.engine.tbox.item.TBoxConnection.9
            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onRead(SocketChannel channel, ByteBuffer buffer) {
                LogUtils.d(AbstractConnection.TAG, "client on Read:" + buffer, new Object[0]);
                Message msg = TBoxConnection.this.recvHandler.obtainMessage();
                msg.what = 1;
                msg.obj = buffer;
                TBoxConnection.this.recvHandler.sendMessage(msg);
                return true;
            }

            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onConnectFailed(SocketChannel channel) {
                StringBuilder sb = new StringBuilder();
                sb.append("onConnectFailed:");
                sb.append(channel == null ? "" : Integer.valueOf(channel.hashCode()));
                LogUtils.d(AbstractConnection.TAG, sb.toString(), new Object[0]);
                TBoxConnection.this.onConnectionStateChanged(ConnectionState.DISCONNECTED);
                TBoxConnection.this.resetHeartBeatCount();
                TBoxConnection.this.sendHandler.removeCallbacksAndMessages(null);
                if (TBoxConnection.this.sendHandler.hasMessages(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ)) {
                    LogUtils.d(AbstractConnection.TAG, "remove the heartbeat message when onConnectFailed", new Object[0]);
                    TBoxConnection.this.sendHandler.removeMessages(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ);
                    return true;
                }
                return true;
            }

            @Override // com.engine.tbox.core.net.IEventHandler
            public boolean onConnectionSuccess(SocketChannel channel) {
                LogUtils.d(AbstractConnection.TAG, "onConnectionSuccess:" + channel.hashCode(), new Object[0]);
                TBoxConnection.this.resetHeartBeatCount();
                TBoxConnection.this.onConnectionStateChanged(ConnectionState.CONNECTED);
                if (TBoxConnection.this.sendHandler.hasMessages(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ)) {
                    LogUtils.d(AbstractConnection.TAG, "remove the heartbeat message when onConnectionSuccess", new Object[0]);
                    TBoxConnection.this.sendHandler.removeMessages(MessageConstants.IVI_TBOX_KEEP_ALIVE_REQ);
                }
                TboxMessage message = MessageUtils.buildLClientHeartbeatMessage();
                TBoxConnection.this.sendAsyncMessage(message);
                return true;
            }
        });
        this.mMachine.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void socketReconnect() {
        this.mMachine.reconnectImmediately();
    }

    private void otaMachineInit() {
        LogUtils.w(AbstractConnection.TAG, "otaMachineInit", new Object[0]);
        TboxOtaMachine tboxOtaMachine = new TboxOtaMachine("TboxOtaStateMachine");
        this.mTBoxOtaMachine = tboxOtaMachine;
        tboxOtaMachine.addTboxConnection(this);
        this.mTBoxOtaMachine.addOtaCallback(new TboxOtaMachine.OtaCallback() { // from class: com.engine.tbox.item.TBoxConnection.10
            @Override // com.engine.tbox.ota.TboxOtaMachine.OtaCallback
            public void process(int process) {
                super.process(process);
                if (TBoxConnection.this.mListener != null) {
                    TBoxConnection.this.mListener.upgradeProgress(process);
                }
            }

            @Override // com.engine.tbox.ota.TboxOtaMachine.OtaCallback
            public void upgradeSuccess() {
                super.upgradeSuccess();
                if (TBoxConnection.this.mListener != null) {
                    TBoxConnection.this.mListener.upgradeResult(0);
                }
            }

            @Override // com.engine.tbox.ota.TboxOtaMachine.OtaCallback
            public void upgradeFailed(int reason) {
                super.upgradeFailed(reason);
                int outReason = 3;
                if (reason == 1) {
                    outReason = 4;
                } else if (reason == 2) {
                    outReason = 2;
                } else if (reason == 4) {
                    outReason = 1;
                } else if (reason == 5) {
                    outReason = 5;
                }
                if (TBoxConnection.this.mListener != null) {
                    TBoxConnection.this.mListener.upgradeResult(outReason);
                }
            }
        });
        this.mTBoxOtaMachine.start();
    }

    private Message sendMsgToSendHandlerMessage(CountSyncMessage msg) {
        Message message = this.sendHandler.obtainMessage(msg.identify());
        if (!MessageConstants.isHearBeatMessage(msg.identify())) {
            this.cache.addMessage((TboxMessage) msg);
        }
        message.obj = msg;
        return message;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean sendAsyncMessage(CountSyncMessage msg) {
        Message message = sendMsgToSendHandlerMessage(msg);
        return this.sendHandler.sendMessage(message);
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean sendSyncMessage(CountSyncMessage msg, IResponse response) {
        Message message = sendMsgToSendHandlerMessage(msg);
        this.sendHandler.sendMessage(message);
        boolean ret = msg.waitForSignal();
        if (ret) {
            LogUtils.d(AbstractConnection.TAG, "on response:" + msg, new Object[0]);
            CountSyncMessage respone = msg.getRespone();
            if (isTboxConnected()) {
                response.onResponse(respone);
            } else {
                LogUtils.e(AbstractConnection.TAG, "wait for response but tbox connected failed " + respone, new Object[0]);
            }
        } else {
            LogUtils.d(AbstractConnection.TAG, "on timeout:" + msg, new Object[0]);
            response.onTimeout(msg);
        }
        return ret;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean sendByteBuffer(ByteBuffer buff) {
        if (this.mState == ConnectionState.CONNECTED) {
            try {
                this.mChannel.write(buff);
                return true;
            } catch (IOException | NotYetConnectedException exp) {
                exp.printStackTrace();
                return true;
            }
        }
        LogUtils.e(AbstractConnection.TAG, "send byte buffer failed", new Object[0]);
        return false;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean start() {
        onConnectionStateChanged(ConnectionState.CONNECTING);
        this.mMachine.turnOn();
        return true;
    }

    @Override // com.engine.tbox.core.conn.AbstractConnection, com.engine.tbox.core.conn.IConnection
    public boolean stop() {
        LogUtils.i(AbstractConnection.TAG, "stop machine", new Object[0]);
        this.mLogExport.stopExport();
        this.mMachine.turnOff();
        return true;
    }

    public TboxStaticInfo requestTBoxStaticInfo() {
        LogUtils.i(AbstractConnection.TAG, "requestTBoxStaticInfo", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "requestTBoxStaticInfo when TBox Connection failed", new Object[0]);
            return this.mCache.getDefaultTBoxStaticInfo();
        }
        TboxStaticInfo cachedInfo = this.mCache.getTBoxStaticInfo();
        if (cachedInfo != null) {
            LogUtils.w(AbstractConnection.TAG, "requestTBoxStaticInfo use cached:" + cachedInfo, new Object[0]);
            return cachedInfo;
        }
        sendSyncMessage(MessageUtils.buildClientTBoxStaticInfoRequestMessage(), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.11
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                LogUtils.d(AbstractConnection.TAG, "on Response:" + msg, new Object[0]);
                TboxStaticInfo info = new TboxStaticInfo();
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length == 79) {
                    TBoxConnection.this.parseSoftVersion01(data, 0, 0, info);
                    LogUtils.d(AbstractConnection.TAG, " info:" + info, new Object[0]);
                } else if (data.length == 97) {
                    TBoxConnection.this.parseSoftVersion01(data, 0, 0, info);
                    int pos = 0 + 15;
                    String softCode = MessageUtils.dataArrayToString(data, pos, 18);
                    info.setSoftCode(softCode);
                    LogUtils.d(AbstractConnection.TAG, "softCode:" + softCode, new Object[0]);
                    LogUtils.d(AbstractConnection.TAG, " info:" + info, new Object[0]);
                }
                TBoxConnection.this.mCache.updateTBoxStaticInfo(info);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "request TBox STATIC_INFO timeout", new Object[0]);
            }
        });
        if (this.mCache.getTBoxStaticInfo() == null) {
            return this.mCache.getDefaultTBoxStaticInfo();
        }
        return this.mCache.getTBoxStaticInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseSoftVersion01(byte[] data, int pos, int pos1, TboxStaticInfo info) {
        String softVersion = MessageUtils.dataArrayToString(data, pos, 20);
        info.setTboxSoftVersion(softVersion);
        LogUtils.d(AbstractConnection.TAG, "SoftVersion:" + softVersion, new Object[0]);
        int pos12 = pos1 + 20;
        String hardVersion = MessageUtils.dataArrayToString(data, pos12, 20);
        info.setTboxHardVersion(hardVersion);
        LogUtils.d(AbstractConnection.TAG, "hardVersion:" + hardVersion, new Object[0]);
        int pos13 = pos12 + 20;
        String serial = MessageUtils.dataArrayToString(data, pos13, 24);
        info.setTboxSerial(serial);
        StringBuilder sb = new StringBuilder();
        sb.append("serial length:");
        sb.append(serial != null ? Integer.valueOf(serial.length()) : "null");
        LogUtils.d(AbstractConnection.TAG, sb.toString(), new Object[0]);
        String imsi = MessageUtils.dataArrayToString(data, pos13 + 24, 15);
        info.setImsi(imsi);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("imsi length:");
        sb2.append(imsi != null ? Integer.valueOf(imsi.length()) : "null");
        LogUtils.d(AbstractConnection.TAG, sb2.toString(), new Object[0]);
    }

    public FtpInfo startUpgradeRequest() {
        LogUtils.i(AbstractConnection.TAG, "startUpgradeRequest", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "startUpgradeRequest TBox Connection failed", new Object[0]);
            return null;
        }
        this.mUpgradeFtpInfo = null;
        TboxMessage tboxMessage = MessageUtils.buildClientUpgradeReqMessage();
        tboxMessage.setTimeOut(1500L);
        sendSyncMessage(tboxMessage, new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.12
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                LogUtils.d(AbstractConnection.TAG, "on Upgrade Response:" + msg, new Object[0]);
                TboxMessage message = (TboxMessage) msg;
                TBoxConnection tBoxConnection = TBoxConnection.this;
                tBoxConnection.mUpgradeFtpInfo = tBoxConnection.parseFtpInfo(message);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "request TBox Upgrade timeout", new Object[0]);
            }
        });
        return this.mUpgradeFtpInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FtpInfo parseFtpInfo(TboxMessage message) {
        byte[] data = message.getData();
        LogUtils.d(AbstractConnection.TAG, "ftpInfo: data len:" + data.length + " String:" + new String(data, StandardCharsets.UTF_8).trim(), new Object[0]);
        FtpInfo info = new FtpInfo();
        if (data.length == 170) {
            String url = MessageUtils.dataArrayToString(data, 0, 128);
            info.setUrl(url);
            LogUtils.d(AbstractConnection.TAG, "data[0]:" + ((int) data[0]) + " url:" + url, new Object[0]);
            int pos = 0 + 128;
            int port = TypeUtils.byteArrayPosToShortInt(data, pos);
            info.setPort(port);
            int pos2 = pos + 2;
            String name = MessageUtils.dataArrayToString(data, pos2, 20);
            info.setUsername(name);
            LogUtils.d(AbstractConnection.TAG, "data[" + pos2 + "]:" + ((int) data[pos2]) + " name:" + name, new Object[0]);
            int pos3 = pos2 + 20;
            String pass = MessageUtils.dataArrayToString(data, pos3, 20);
            info.setPassword(pass);
            LogUtils.d(AbstractConnection.TAG, "data[" + pos3 + "]:" + data[pos3] + " pass len:" + pass.length(), new Object[0]);
        }
        LogUtils.d(AbstractConnection.TAG, "parse ftpInfo:" + info, new Object[0]);
        if (info.getPort() <= 0 || info.getPort() > MAX_PORT) {
            return null;
        }
        return info;
    }

    public void sendFtpResult(boolean z) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendFtpResult when TBox Connection failed", new Object[0]);
        } else {
            sendAsyncMessage(MessageUtils.buildLClientSendEndNotifyMessage(new byte[]{(byte) (!z ? 1 : 0)}));
        }
    }

    public void startUpgrade(String path) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "startUpgrade when TBox Connection failed", new Object[0]);
            ITboxListener iTboxListener = this.mListener;
            if (iTboxListener != null) {
                iTboxListener.upgradeResult(6);
                return;
            }
            return;
        }
        this.mTBoxOtaMachine.startUpgrade(path);
    }

    public boolean exportLog(String path) {
        LogUtils.i(AbstractConnection.TAG, "export Log:" + path, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "exportLog when TBox Connection failed", new Object[0]);
            ITboxListener iTboxListener = this.mListener;
            if (iTboxListener != null) {
                iTboxListener.exportLogResult(5);
            }
            return false;
        }
        this.mLogExport.init(path);
        return true;
    }

    public void sendExportResult(boolean z) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendExportResult when TBox Connection failed", new Object[0]);
        } else {
            sendAsyncMessage(MessageUtils.buildClientExportLogNotifyMessage(new byte[]{(byte) (!z ? 1 : 0)}));
        }
    }

    public FtpInfo sendExportLogReq() {
        this.mLogExportFtpInfo = null;
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendExportLogReq when TBox Connection failed", new Object[0]);
            return null;
        }
        sendSyncMessage(MessageUtils.buildClientExportLogReqMessage(), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.13
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                LogUtils.d(AbstractConnection.TAG, "on export log Response:" + msg, new Object[0]);
                TboxMessage message = (TboxMessage) msg;
                TBoxConnection tBoxConnection = TBoxConnection.this;
                tBoxConnection.mLogExportFtpInfo = tBoxConnection.parseFtpInfo(message);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "on sendExportLogReq timeout:" + msg, new Object[0]);
            }
        });
        return this.mLogExportFtpInfo;
    }

    public void upgradeFileCheck(boolean result) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "upgradeFileCheck when TBox Connection failed", new Object[0]);
        } else {
            this.mTBoxOtaMachine.upgradeFileCheck(result);
        }
    }

    public void sendUpgradeProcess(int process) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendUpgradeProcess when TBox Connection failed", new Object[0]);
        } else {
            this.mTBoxOtaMachine.sendUpgradeProcess(process);
        }
    }

    public void sendUpgradeResult(boolean result) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendUpgradeResult when TBox Connection failed", new Object[0]);
        } else {
            this.mTBoxOtaMachine.sendUpgradeResult(result);
        }
    }

    public void sendExchangeReq() {
        LogUtils.w(AbstractConnection.TAG, "sendExchangeReq", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendExchangeReq when TBox Connection failed", new Object[0]);
        } else {
            sendAsyncMessage(MessageUtils.buildTBoxMessageExChangeReq());
        }
    }

    public void sendExchangeResult(final boolean result) {
        LogUtils.w(AbstractConnection.TAG, "sendExchangeResult:" + result, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendExchangeResult when TBox Connection failed", new Object[0]);
        } else {
            sendSyncMessage(MessageUtils.buildTBoxMessageExChangeResult(result), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.14
                @Override // com.engine.tbox.core.conn.IResponse
                public void onResponse(CountSyncMessage msg) {
                    if (!result) {
                        LogUtils.w(AbstractConnection.TAG, "restart start auth", new Object[0]);
                        if (TBoxConnection.this.enableAuth) {
                            TBoxConnection.this.mAuthStateMachine.startAuth();
                        }
                    }
                }

                @Override // com.engine.tbox.core.conn.IResponse
                public void onTimeout(CountSyncMessage msg) {
                    if (!result) {
                        LogUtils.w(AbstractConnection.TAG, "restart start auth when timeout", new Object[0]);
                        if (TBoxConnection.this.enableAuth) {
                            TBoxConnection.this.mAuthStateMachine.startAuth();
                        }
                    }
                }
            });
        }
    }

    public void sendExchangeOffer(byte[] rsaEncryptAesKeyData) {
        LogUtils.v(AbstractConnection.TAG, "sendExchangeOffer:" + Arrays.toString(rsaEncryptAesKeyData), new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendExchangeOffer when TBox Connection failed", new Object[0]);
        } else {
            sendAsyncMessage(MessageUtils.buildTBoxMessageExChangeOffer(rsaEncryptAesKeyData));
        }
    }

    public void registerListener(ITboxListener listener) {
        this.mListener = listener;
    }

    public void unregisterListener() {
        this.mListener = null;
    }

    private static String getHexByteArrayString(byte[] array) {
        StringBuffer sb = new StringBuffer();
        for (byte bit : array) {
            sb.append(String.format("%02x", Byte.valueOf(bit)));
        }
        return sb.toString();
    }

    public void checkIsHello(byte[] data) {
        LogUtils.w(AbstractConnection.TAG, "is Hello:" + AESUtils.dataToHexString(data), new Object[0]);
        byte[] data2 = this.mAuth.forceDecrypt(data);
        boolean result = false;
        if (data2 != null) {
            String hello = new String(data2, StandardCharsets.UTF_8).trim();
            result = HELLO.equals(hello);
        }
        if (!result) {
            LogUtils.e(AbstractConnection.TAG, "hello encrypted expected:" + AESUtils.dataToHexString(this.mAuth.foreceEncrypt(HELLO)), new Object[0]);
            LogUtils.e(AbstractConnection.TAG, "hello check error with " + getHexByteArrayString(this.mAuth.getKey().getEncoded()), new Object[0]);
        } else {
            LogUtils.i(AbstractConnection.TAG, "hello check success update local key", new Object[0]);
            this.mAuth.setAuth(true);
            this.mBroadCastUtils.sendAuthState(true);
        }
        LogUtils.w(AbstractConnection.TAG, "send ExchangeResult:" + result, new Object[0]);
        sendExchangeResult(result);
        this.mAuthStateMachine.helloAuthResult(result);
    }

    public void sendRsaPublicKey(byte[] rsaPublicKey) {
        LogUtils.w(AbstractConnection.TAG, "sendRsaPublicKey", new Object[0]);
        this.mAuthStateMachine.sendRsaPublicKey(rsaPublicKey);
    }

    public void sendPublicKeyFailed() {
        LogUtils.w(AbstractConnection.TAG, "sendPublicKeyFailed", new Object[0]);
        this.mAuthStateMachine.sendRsaPublicKeyError();
    }

    public void sendHelloFailed() {
        LogUtils.w(AbstractConnection.TAG, "sendHelloFailed", new Object[0]);
        this.mAuthStateMachine.sendHelloError();
    }

    public void recvAuthAck() {
        LogUtils.w(AbstractConnection.TAG, "recvAuthAck", new Object[0]);
        this.mAuthStateMachine.sendAuth();
    }

    public boolean sendAesKey(byte[] rsaPublicKey) {
        LogUtils.i(AbstractConnection.TAG, "sendAesKey:" + Arrays.toString(rsaPublicKey), new Object[0]);
        this.mAuth.generatorAesPublicKey();
        try {
            byte[] rsaEncryptPublicKey = this.mAuth.getRsaEncryptAesPublicKey(rsaPublicKey);
            sendExchangeOffer(rsaEncryptPublicKey);
            return true;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public boolean authEncrypt(TboxMessage msg) {
        return this.mAuth.encryptTBoxMessage(msg);
    }

    public boolean authDecrypt(TboxMessage msg) {
        if (msg.getData().length == 0) {
            return true;
        }
        return this.mAuth.decryptTBoxMessage(msg);
    }

    public boolean sendNetworkTrafficQueryResult(NetworkQueryResult result) {
        LogUtils.i(AbstractConnection.TAG, "sendNetworkTrafficQueryResult", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendNetworkTrafficQueryResult when TBox Connection failed", new Object[0]);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendNetworkTrafficQueryResult privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        sendAsyncMessage(MessageUtils.buildNetwrokTrafficeRsp(result.toByteArray()));
        return true;
    }

    public boolean sendTrafficeQueryResult(int cmd, int result) {
        LogUtils.i(AbstractConnection.TAG, "sendTrafficeQueryResult cmd:" + cmd + " result:" + result, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendTrafficeQueryResult when TBox Connection failed", new Object[0]);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendTrafficeQueryResult privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        this.recvHandler.removeMessages(7);
        byte[] data = {(byte) cmd, (byte) result};
        return sendAsyncMessage(MessageUtils.buildNetwrokTrafficeRsp(data));
    }

    public boolean sendTrafficeQueryResult(int length, String result) {
        byte[] data;
        int i;
        LogUtils.i(AbstractConnection.TAG, "sendTrafficeQueryResult result:" + result, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendTrafficeQueryResult when TBox Connection failed", new Object[0]);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendTrafficeQueryResult privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        this.recvHandler.removeMessages(6);
        boolean ok = length != 0;
        if (ok) {
            data = new byte[2 + 1 + length];
        } else {
            data = new byte[2];
        }
        int pos = 0 + 1;
        if (ok) {
            i = 0;
        } else {
            i = 1;
        }
        data[0] = (byte) i;
        int pos2 = pos + 1;
        data[pos] = 3;
        if (ok) {
            data[pos2] = (byte) length;
            System.arraycopy(result.getBytes(StandardCharsets.UTF_8), 0, data, pos2 + 1, length);
        }
        return sendAsyncMessage(MessageUtils.buildNetwrokTrafficeRsp(data));
    }

    public boolean sendActiveCode(ActiveCode code) {
        LogUtils.i(AbstractConnection.TAG, "sendActiveCode:" + code, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendActiveCode when TBox Connection failed", new Object[0]);
            Message msg = this.recvHandler.obtainMessage();
            msg.what = 4;
            msg.obj = code;
            this.recvHandler.sendMessage(msg);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendActiveCode privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        if (!this.mActiveSemaphore.get(code.getProviderType()).tryAcquire()) {
            LogUtils.e(AbstractConnection.TAG, code.getProviderType() + " before active request is not timeout", new Object[0]);
            return false;
        }
        sendAsyncMessage(MessageUtils.buildActiveCode(code.toByteArray()));
        Message msg2 = this.recvHandler.obtainMessage();
        msg2.what = 4;
        msg2.obj = code;
        this.recvHandler.sendMessageDelayed(msg2, 60000L);
        return true;
    }

    public boolean tryLockCode(ActiveCodeV2 code) {
        boolean zTryAcquire;
        int type = code.getProviderType();
        LogUtils.d(AbstractConnection.TAG, "tryLockCode code:" + type, new Object[0]);
        synchronized (this.mActiveCodeV2Semaphore) {
            Semaphore semp = this.mActiveCodeV2Semaphore.get(Integer.valueOf(type));
            if (semp == null) {
                semp = new Semaphore(1);
                this.mActiveCodeV2Semaphore.put(Integer.valueOf(type), semp);
                LogUtils.d(AbstractConnection.TAG, "new lock type for:" + type, new Object[0]);
            }
            zTryAcquire = semp.tryAcquire();
        }
        return zTryAcquire;
    }

    public boolean unlockCode(int type) {
        LogUtils.d(AbstractConnection.TAG, "unlockCode type:" + type, new Object[0]);
        synchronized (this.mActiveCodeV2Semaphore) {
            Semaphore semp = this.mActiveCodeV2Semaphore.get(Integer.valueOf(type));
            if (semp == null) {
                return false;
            }
            semp.release();
            LogUtils.d(AbstractConnection.TAG, "unlockCode release type:" + type, new Object[0]);
            return true;
        }
    }

    public boolean sendActiveCodeV2(ActiveCodeV2 code) {
        LogUtils.i(AbstractConnection.TAG, "sendActiveCode2:" + code, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendActiveCode2 when TBox Connection failed", new Object[0]);
            Message msg = this.recvHandler.obtainMessage();
            msg.what = 8;
            msg.obj = code;
            this.recvHandler.sendMessage(msg);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendActiveCode2 privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        if (!tryLockCode(code)) {
            LogUtils.e(AbstractConnection.TAG, "sendActiveCode2 type:" + code.getProviderType() + " before active2 request is not timeout", new Object[0]);
            return false;
        }
        sendAsyncMessage(MessageUtils.buildActiveCode(code.toByteArray()));
        Message msg2 = this.recvHandler.obtainMessage();
        msg2.what = 8;
        msg2.obj = code;
        this.recvHandler.sendMessageDelayed(msg2, 60000L);
        return true;
    }

    public void networkTrafficReqCallback(byte result) {
        LogUtils.d(AbstractConnection.TAG, "networkTrafficReqCallback:" + ((int) result), new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onTBoxCmd2Ivi(result);
        }
    }

    public int requestCloudServiceEnv() {
        LogUtils.i(AbstractConnection.TAG, "requestCloudServiceEnv", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "requestCloudServiceEnv TBox Connection failed", new Object[0]);
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        sendSyncMessage(MessageUtils.buildGetCloudServiceEnvReqMessage(), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.15
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "requestCloudServiceEnv response length:" + data.length, new Object[0]);
                }
                byte cloudEnv = data[0];
                atomicInteger.set(cloudEnv);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "requestCloudServiceEnv timeout", new Object[0]);
            }
        });
        return atomicInteger.get();
    }

    public int requestCloudServiceConnectState() {
        LogUtils.i(AbstractConnection.TAG, "requestCloudServiceConnectState", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "requestCloudServiceConnectState TBox Connection failed", new Object[0]);
            return 0;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        TboxMessage tboxMessage = MessageUtils.buildGetCloudServiceConnectState();
        tboxMessage.setTimeOut(1500L);
        sendSyncMessage(tboxMessage, new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.16
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "requestCloudServiceConnectState error length:" + data.length, new Object[0]);
                }
                byte cloudConnState = data[0];
                atomicInteger.set(cloudConnState);
                TBoxConnection.this.checkAndSendCloudTcpState(cloudConnState);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "requestCloudServiceConnectState timeout", new Object[0]);
                TBoxConnection.this.checkAndSendCloudTcpState(2);
            }
        });
        return atomicInteger.get();
    }

    public boolean requestSwitchCloudServiceEnv(final int env) {
        LogUtils.i(AbstractConnection.TAG, "requestSwitchCloudServiceEnv:" + env, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "requestSwitchCloudServiceEnv TBox Connection failed", new Object[0]);
            return false;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        sendSyncMessage(MessageUtils.buildSwitchCloudServiceEnv(MessageUtils.buildSwitchCloudServiceEnvData(env)), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.17
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "requestSwitchCloudServiceEnv response len:" + data.length, new Object[0]);
                }
                byte cloudEnv = data[0];
                atomicBoolean.set(cloudEnv == env);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "requestSwitchCloudServiceEnv timeout", new Object[0]);
            }
        });
        return atomicBoolean.get();
    }

    public String requestCloudServiceVersion() {
        LogUtils.i(AbstractConnection.TAG, "requestCloudServiceVersion", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "requestCloudServiceVersion TBox Connection failed", new Object[0]);
            return null;
        }
        sendSyncMessage(MessageUtils.buildCloudServiceVersion(), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.18
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length == 0) {
                    LogUtils.e(AbstractConnection.TAG, "requestCloudServiceVersion empty data", new Object[0]);
                }
                String unused = TBoxConnection.cloudVer = MessageUtils.dataArrayToString(data, 0, data.length);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "requestCloudServiceVersion timeout", new Object[0]);
            }
        });
        return cloudVer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkEnableApnResultValid(byte data) {
        if (data == 0 || data == 1) {
            return true;
        }
        return false;
    }

    public boolean enableApn2(final boolean z) {
        LogUtils.i(AbstractConnection.TAG, "enableApn2 arg:" + z, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "enableApn2 TBox Connection failed", new Object[0]);
            return false;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        sendSyncMessage(MessageUtils.buildEnableApn2State(new byte[]{(byte) (!z ? 1 : 0)}), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.19
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "enableApn2 error length:" + data.length, new Object[0]);
                    return;
                }
                byte state = data[0];
                if (TBoxConnection.this.checkEnableApnResultValid(state)) {
                    synchronized (TBoxConnection.this.mApnMutex) {
                        if (state == 0) {
                            TBoxConnection.this.mApnState = z;
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("enableApn2 result:");
                    sb.append(state == 0);
                    LogUtils.i(AbstractConnection.TAG, sb.toString(), new Object[0]);
                    atomicInteger.set(state);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "enable apn2 result error value:" + ((int) state), new Object[0]);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "enableApn2 timeout", new Object[0]);
            }
        });
        return atomicInteger.get() == 0;
    }

    public boolean enableApn3(final boolean z) {
        LogUtils.i(AbstractConnection.TAG, "enableApn3 arg:" + z, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "enableApn3 TBox Connection failed", new Object[0]);
            return false;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        sendSyncMessage(MessageUtils.buildEnableApn3State(new byte[]{(byte) (!z ? 1 : 0)}), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.20
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "enableApn3 error length:" + data.length, new Object[0]);
                    return;
                }
                byte state = data[0];
                if (TBoxConnection.this.checkEnableApnResultValid(state)) {
                    synchronized (TBoxConnection.this.mApnMutex) {
                        if (state == 0) {
                            TBoxConnection.this.mApnState = z;
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("enableApn3 result:");
                    sb.append(state == 0);
                    LogUtils.i(AbstractConnection.TAG, sb.toString(), new Object[0]);
                    atomicInteger.set(state);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "enable apn3 result error value:" + ((int) state), new Object[0]);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "enableApn3 timeout", new Object[0]);
            }
        });
        return atomicInteger.get() == 0;
    }

    public void apn2StateChanged(boolean state) {
        LogUtils.i(AbstractConnection.TAG, "apn2StateChanged state:" + state, new Object[0]);
        synchronized (this.mApnMutex) {
            if (this.mApnState != state) {
                this.mApnState = state;
                if (this.mListener != null) {
                    this.mListener.apn2StateChanged(state);
                }
            }
        }
    }

    public boolean getApn2State() {
        boolean z;
        synchronized (this.mApnMutex) {
            z = this.mApnState;
        }
        return z;
    }

    public boolean sendOtaState(int state) {
        LogUtils.i(AbstractConnection.TAG, "sendOtaState:" + state, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendOtaState TBox Connection failed", new Object[0]);
            return false;
        }
        byte[] bytes = {(byte) state};
        return sendAsyncMessage(MessageUtils.buildSendOtaState(bytes));
    }

    public boolean sendOtsRes(int res) {
        LogUtils.w(AbstractConnection.TAG, "sendOtsRes:" + res, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendOtsRes TBox Connection failed", new Object[0]);
            return false;
        }
        byte[] bytes = {(byte) res};
        return sendAsyncMessage(MessageUtils.buildOtaRes(bytes));
    }

    public boolean sendOtaResWithUuid(byte[] uuid, int res) {
        LogUtils.w(AbstractConnection.TAG, "sendOtaResWithUuid:" + Arrays.toString(uuid) + " res:" + res, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendOtaResWithUuid TBox Connection failed", new Object[0]);
            return false;
        }
        if (uuid == null) {
            if (res != 2) {
                LogUtils.d(AbstractConnection.TAG, "sendOtaResWithUuid uuid is null,res:" + res, new Object[0]);
                return false;
            }
            uuid = new byte[16];
        }
        byte[] bytes = new byte[17];
        System.arraycopy(uuid, 0, bytes, 0, 16);
        bytes[16] = (byte) res;
        return sendAsyncMessage(MessageUtils.buildOtaRes(bytes));
    }

    public boolean sendPlaneRes(int result) {
        LogUtils.i(AbstractConnection.TAG, "sendChargePlaneResult:" + result, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendPlaneRes TBox Connection failed", new Object[0]);
            return false;
        }
        byte[] bytes = {(byte) result};
        return sendAsyncMessage(MessageUtils.buildChargePlaneRes(bytes));
    }

    public boolean sendPlaneChargeRes(int result) {
        LogUtils.i(AbstractConnection.TAG, "sendUserChargePlaneResult:" + result, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendPlaneChargeRes TBox Connection failed", new Object[0]);
            return false;
        }
        byte[] bytes = {(byte) result};
        return sendAsyncMessage(MessageUtils.buildUserChargePlaneRes(bytes));
    }

    public boolean sendPlaneUpload(ReservationCharging rc) {
        LogUtils.i(AbstractConnection.TAG, "sendUserUpdateChargePlane:" + rc, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendPlaneUpload TBox Connection failed", new Object[0]);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendPlaneUpload privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        byte[] bytes = ReservationCharging.toByte(rc);
        return sendAsyncMessage(MessageUtils.buildChargePlaneUp(bytes));
    }

    public boolean sendChargeControl(int charge) {
        LogUtils.i(AbstractConnection.TAG, "sendChargeControl:" + charge, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendChargeControl TBox Connection failed", new Object[0]);
            return false;
        }
        if (!this.mPrivatePolicyStatus.booleanValue()) {
            LogUtils.e(AbstractConnection.TAG, "sendChargeControl privatePolicyStatus: " + this.mPrivatePolicyStatus, new Object[0]);
            return false;
        }
        byte[] bytes = {(byte) charge};
        return sendAsyncMessage(MessageUtils.buildChargeControl(bytes));
    }

    public void otaNotificationCallback(OtaNotification nty) {
        LogUtils.w(AbstractConnection.TAG, "otaNotificationCallback:" + nty, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.otaNotifcaticationCallback(nty);
        }
    }

    public void tboxChargePlaneCallback(ReservationCharging rsv) {
        LogUtils.i(AbstractConnection.TAG, "tboxChargePlaneCallback:" + rsv, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.tboxChargePlaneCallback(rsv);
        }
        this.mBroadCastUtils.sendPlaneBroadCast(rsv);
    }

    public void userChangeChargePlane(int userPlane) {
        LogUtils.i(AbstractConnection.TAG, "userChangeChargePlane:" + userPlane, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.userChangeChargePlane(userPlane);
        }
        this.mBroadCastUtils.sendPlaneCharge(userPlane);
    }

    public void releaseActiveSemaphoreAndNotify(ActiveCode.ProviderType type, int activeResult) {
        LogUtils.i(AbstractConnection.TAG, "releaseActiveSemaphore type:" + type + " activeResult:" + activeResult, new Object[0]);
        Semaphore semaphore = this.mActiveSemaphore.get(type);
        int key = semaphore.availablePermits();
        LogUtils.d(AbstractConnection.TAG, "releaseActiveSemaphoreAndNotify key:" + key, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null && key == 0) {
            iTboxListener.appActivityResult(ActiveCode.providerTypeToByte(type), activeResult);
        }
        semaphore.release();
    }

    public void releaseActiveCodeV2SemaphoreAndNotify(int type, int activeResult) {
        ITboxListener iTboxListener;
        LogUtils.i(AbstractConnection.TAG, "releaseActiveCodeV2SemaphoreAndNotify type:" + type + " activeResult:" + activeResult, new Object[0]);
        if (unlockCode(type) && (iTboxListener = this.mListener) != null) {
            iTboxListener.appActivityResult(type, activeResult);
        }
    }

    public void notifyTheActiveResult(TboxMessage msg) {
        LogUtils.d(AbstractConnection.TAG, "notifyTheActiveResult msg:" + msg, new Object[0]);
        byte[] data = msg.getData();
        if (data.length < 2) {
            LogUtils.e(AbstractConnection.TAG, "notifyTheActiveResult date len less than 2 msg:" + msg, new Object[0]);
            return;
        }
        if (data[0] > 3 || data[0] < 1) {
            LogUtils.e(AbstractConnection.TAG, "error active code provider type:" + ((int) data[0]), new Object[0]);
            return;
        }
        ActiveCode.ProviderType type = ActiveCode.byteToProviderType(data[0]);
        releaseActiveSemaphoreAndNotify(type, data[1]);
        releaseActiveCodeV2SemaphoreAndNotify(data[0], data[1]);
    }

    public boolean setRestartTbox(int state) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "setRestartTbox TBox Connection failed", new Object[0]);
            return false;
        }
        if (!this.mRestartTBoxSemaphore.tryAcquire()) {
            LogUtils.e(AbstractConnection.TAG, "still in restart tbox state now", new Object[0]);
            return false;
        }
        return sendRestartTbox(state, 0);
    }

    public boolean sendRestartTbox(int state, int count) {
        byte[] data = MessageUtils.buildRestartTBoxMessageData(state);
        boolean flag = sendAsyncMessage(MessageUtils.buildRestartTBoxMessage(data));
        LogUtils.d(AbstractConnection.TAG, "sendRestartTbox: result:" + flag + " state" + state + " count:" + count, new Object[0]);
        Message msg = this.recvHandler.obtainMessage();
        msg.what = 5;
        msg.arg1 = state;
        msg.arg2 = count;
        this.recvHandler.sendMessageDelayed(msg, 3000L);
        return flag;
    }

    private boolean isValidTboxRestartCallbackState(int state) {
        return state == 0 || state == 1 || state == -1;
    }

    public void onTboxRestartChanged(int state) {
        ITboxListener iTboxListener;
        LogUtils.d(AbstractConnection.TAG, "onTboxRestartChanged:" + state, new Object[0]);
        if (isValidTboxRestartCallbackState(state) && (iTboxListener = this.mListener) != null) {
            iTboxListener.onTboxRestartChanged(state);
        }
        LogUtils.d(AbstractConnection.TAG, "release the restart tbox state", new Object[0]);
        this.mRestartTBoxSemaphore.release();
    }

    public int getNetworkOperatorList() {
        LogUtils.i(AbstractConnection.TAG, "getNetworkOperatorList", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "getNetworkOperatorList TBox Connection failed", new Object[0]);
            return -1;
        }
        boolean result = sendAsyncMessage(MessageUtils.buildTBoxGetOperationList());
        return result ? 0 : -1;
    }

    public void notifyOperatorList(ArrayList<String> opList) {
        LogUtils.i(AbstractConnection.TAG, "notifyOperatorList length:" + opList.size() + " array:" + opList, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onNetworkOperatorList(opList);
        }
    }

    public void notifyNetworkOperationList(ArrayList<NetworkOperator> opList) {
        LogUtils.i(AbstractConnection.TAG, "notifyOperatorList length:" + opList.size() + " array:" + opList, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onOperatorInfoChange(opList);
        }
    }

    public void notifyTboxCloudConnected(int state) {
        LogUtils.i(AbstractConnection.TAG, "notifyTboxCloudConnected:" + state, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.switchCloudEnvCallback(state);
        }
        checkAndSendCloudTcpState(state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAndSendCloudTcpState(int state) {
        int result = state == 1 ? 1 : 0;
        int pre = SystemProperties.getInt(CLOUD_TCP_STATUS, 0);
        if (pre != result) {
            SystemProperties.set(CLOUD_TCP_STATUS, "" + result);
            this.mBroadCastUtils.sendCloudTcpState(state == 1);
        }
        LogUtils.d(AbstractConnection.TAG, "checkAndSendCloudTcpState result:" + result + " pre:" + pre, new Object[0]);
    }

    public int setNetworkOperator(String code) {
        byte[] data = code.getBytes(StandardCharsets.UTF_8);
        boolean result = sendAsyncMessage(MessageUtils.buildTBoxSelectOperator(data));
        return result ? 0 : -1;
    }

    public boolean storeCharge(ReservationCharging rc) {
        return this.mSharedPreferenceUtils.storeReservationCharging(rc);
    }

    public boolean isTboxConnected() {
        return this.mState == ConnectionState.CONNECTED && this.mAuth.isAuthed();
    }

    public synchronized boolean isTboxReachable() {
        return this.mTboxReachable;
    }

    public boolean queryKeyListReq(DigitalKeyAccountInfo info) {
        LogUtils.i(AbstractConnection.TAG, "queryKeyListReq:" + info, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "queryKeyListReq TBox Connection failed", new Object[0]);
            return false;
        }
        byte[] data = MessageUtils.arrayToStringArray(info.toByteArray(), 0);
        if (data == null) {
            LogUtils.e(AbstractConnection.TAG, "queryKeyListReq info:" + info + " data is null", new Object[0]);
            return false;
        }
        return sendAsyncMessage(MessageUtils.buildQueryAllNfc(data));
    }

    @Deprecated
    public boolean uploadNfcOpenInfo() {
        LogUtils.i(AbstractConnection.TAG, "uploadNfcOpenInfo", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "uploadNfcOpenInfo TBox Connection failed", new Object[0]);
            return false;
        }
        return sendAsyncMessage(MessageUtils.buildRegisterNfc());
    }

    public boolean startRegisterNfcKey() {
        LogUtils.i(AbstractConnection.TAG, "startRegisterNfcKey", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "startRegisterNfcKey TBox Connection failed", new Object[0]);
            return false;
        }
        return sendAsyncMessage(MessageUtils.buildRegisterNfc());
    }

    public boolean deleteNfcKey(int op, String keyNo) {
        LogUtils.i(AbstractConnection.TAG, "deleteNfcKey:" + op + " keyNo:" + keyNo, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "deleteNfcKey TBox Connection failed", new Object[0]);
            return false;
        }
        if (op == 1 || op == 4) {
            return sendAsyncMessage(MessageUtils.buildNfcDelete(op, keyNo.getBytes(StandardCharsets.UTF_8)));
        }
        if (op == 6 || op == 2) {
            return sendAsyncMessage(MessageUtils.buildNfcAllDelete(op));
        }
        return false;
    }

    public void onDeleteNfcCallback(int op, int result) {
        LogUtils.i(AbstractConnection.TAG, "onDeleteNfcCallback:" + op + " result:" + result, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            if (op == 1) {
                iTboxListener.onDeleteNfcKeyNoByKeyNoResult(this.mNfcDeleteSigleKey, result);
                return;
            }
            if (op == 2) {
                iTboxListener.onDeleteAllNfcKeyResult(op, result);
            } else if (op == 4) {
                iTboxListener.onDeleteOwnIphoneKeyResult(this.mNfcIphoneKey, result);
            } else if (op == 6) {
                iTboxListener.onDeleteAllNfcKeyResult(op, result);
            }
        }
    }

    public void onNfcKeyList(ArrayList<DigitalKeyBean> beans) {
        LogUtils.i(AbstractConnection.TAG, "onNfcKeyList:" + Arrays.toString(beans.toArray()), new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onKeyListResponse(beans);
        }
    }

    public void onNfcKeyList(int total, ArrayList<DigitalKeyBean> beans) {
        LogUtils.i(AbstractConnection.TAG, "onNfcKeyList:" + Arrays.toString(beans.toArray()), new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onKeyListResponse(total, beans);
        }
    }

    public void onKeyListPageRefresh() {
        LogUtils.i(AbstractConnection.TAG, "onKeyListPageRefresh", new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onKeyListPageRefresh();
        }
    }

    public boolean updateTBoxAdb(boolean z) {
        LogUtils.i(AbstractConnection.TAG, "updateTBoxAdb", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "updateTBoxAdb TBox Connection failed", new Object[0]);
            return false;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(1);
        sendSyncMessage(MessageUtils.buildAdbRsp(new byte[]{(byte) (!z ? 1 : 0)}), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.21
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "change adb error length:" + data.length, new Object[0]);
                    return;
                }
                byte result = data[0];
                if (!TBoxConnection.this.checkChangeTboxAdbResultValid(result)) {
                    LogUtils.e(AbstractConnection.TAG, "change adb result error value:" + ((int) result), new Object[0]);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("change adb result:");
                sb.append(result == 0);
                LogUtils.i(AbstractConnection.TAG, sb.toString(), new Object[0]);
                atomicInteger.set(result);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "updateTBoxAdb timeout", new Object[0]);
            }
        });
        return atomicInteger.get() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkChangeTboxAdbResultValid(byte result) {
        if (result == 0 || result == 1) {
            return true;
        }
        return false;
    }

    public boolean setPrivatePolicy(final int auth, int action, final String time, final String version) {
        byte[] data;
        LogUtils.i(AbstractConnection.TAG, "setPrivatePolicy auth:" + auth + " action:" + action + " time:" + time + " version:" + version, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "setPrivatePolicy TBox Connection failed", new Object[0]);
            return false;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(2);
        if (action != 0) {
            data = new byte[time.length() + 4 + version.length()];
            int pos = 0 + 1;
            data[0] = (byte) auth;
            int pos2 = pos + 1;
            data[pos] = (byte) action;
            int pos3 = pos2 + 1;
            data[pos2] = (byte) time.length();
            System.arraycopy(time.getBytes(StandardCharsets.UTF_8), 0, data, pos3, time.length());
            int pos4 = pos3 + time.length();
            data[pos4] = (byte) version.length();
            System.arraycopy(version.getBytes(StandardCharsets.UTF_8), 0, data, pos4 + 1, version.length());
            LogUtils.i(AbstractConnection.TAG, "send sync auth state to cloud data:" + Arrays.toString(data), new Object[0]);
        } else {
            data = new byte[2];
            data[0] = (byte) auth;
            data[0 + 1] = (byte) action;
            LogUtils.i(AbstractConnection.TAG, "send sync auth state to tbox. authstate:" + auth, new Object[0]);
        }
        sendSyncMessage(MessageUtils.buildPrivatePolicy(data), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.22
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data2 = message.getData();
                if (data2.length != 1) {
                    LogUtils.e(AbstractConnection.TAG, "setPrivatePolicy error length:" + data2.length, new Object[0]);
                    return;
                }
                byte result = data2[0];
                if (!TBoxConnection.this.checkSetPrivatePolicyResultValid(result)) {
                    LogUtils.e(AbstractConnection.TAG, "setPrivatePolicy result error value:" + ((int) result), new Object[0]);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("setPrivatePolicy result:");
                sb.append(result == 1);
                LogUtils.i(AbstractConnection.TAG, sb.toString(), new Object[0]);
                if (result == 1) {
                    TBoxConnection.this.mSharedPreferenceUtils.storePrivatePolicy(auth, 0, time, version);
                    LogUtils.i(AbstractConnection.TAG, "storePrivatePolicy result success", new Object[0]);
                }
                atomicInteger.set(result);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "setPrivatePolicy timeout", new Object[0]);
            }
        });
        return atomicInteger.get() == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkSetPrivatePolicyResultValid(byte result) {
        if (result == 0 || result == 1 || result == 2) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getVirtualVinInternal() {
        LogUtils.i(AbstractConnection.TAG, "getVirtualVinInternal", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "getVirtualVinInternal TBox Connection failed", new Object[0]);
            return ITboxIface.DEFAULT_VIRTUAL_VIN;
        }
        sendSyncMessage(MessageUtils.buildGetVirtualVinReq(), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.23
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length < 19) {
                    LogUtils.w(AbstractConnection.TAG, "virtual response length error:" + data.length + " data:" + Arrays.toString(data), new Object[0]);
                    return;
                }
                String vin = new String(data, 0, 19, StandardCharsets.UTF_8).trim();
                if (TBoxConnection.this.isValidVirtualVin(vin)) {
                    TBoxConnection.this.mCache.updateVirtualVin(vin);
                    FileUtils.writeVirtualVin(vin);
                    LogUtils.w(AbstractConnection.TAG, "update the virtual vin length:" + vin.length(), new Object[0]);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "virtual vin error message:" + message, new Object[0]);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "getVirtualVin timeout", new Object[0]);
            }
        });
        String virtualVin = this.mCache.getVirtualVin();
        return virtualVin == null ? ITboxIface.DEFAULT_VIRTUAL_VIN : virtualVin;
    }

    public String getVirtualVin() {
        LogUtils.i(AbstractConnection.TAG, "getVirtualVin", new Object[0]);
        String cacheVirtualVin = this.mCache.getVirtualVin();
        if (cacheVirtualVin != null && !ITboxIface.DEFAULT_VIRTUAL_VIN.equals(cacheVirtualVin)) {
            LogUtils.w(AbstractConnection.TAG, "getVirtualVin use cached:" + cacheVirtualVin.length(), new Object[0]);
            return cacheVirtualVin;
        }
        String cacheVirtualVin2 = FileUtils.readVirtualVin();
        if (!TextUtils.isEmpty(cacheVirtualVin2) && !ITboxIface.DEFAULT_VIRTUAL_VIN.equals(cacheVirtualVin2)) {
            LogUtils.w(AbstractConnection.TAG, "getVirtualVin from file", new Object[0]);
            this.mCache.updateVirtualVin(cacheVirtualVin2);
            return cacheVirtualVin2;
        }
        return getVirtualVinInternal();
    }

    void getNitzData() {
        LogUtils.i(AbstractConnection.TAG, "getNitzData", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "getNitzData TBox Connection failed", new Object[0]);
            return;
        }
        TboxMessage tboxMessage = MessageUtils.buildNitzReq();
        tboxMessage.setTimeOut(1500L);
        sendSyncMessage(tboxMessage, new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.24
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage message = (TboxMessage) msg;
                byte[] data = message.getData();
                if (data.length < 9) {
                    LogUtils.e(AbstractConnection.TAG, "getNitzData error message length:" + msg, new Object[0]);
                    return;
                }
                String nitz = new String(data, 0, data.length - 9).trim();
                LogUtils.i(AbstractConnection.TAG, "getNitzResult:" + nitz, new Object[0]);
                TBoxConnection.this.updateNitz(nitz);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.w(AbstractConnection.TAG, "getNitzData timeout", new Object[0]);
                Message delayMsg = TBoxConnection.this.recvHandler.obtainMessage();
                delayMsg.what = 9;
                TBoxConnection.this.recvHandler.sendMessageDelayed(delayMsg, 5000L);
                TBoxConnection.this.firstNitzUpdateFromFile();
            }
        });
    }

    public void updateNitz(String nitz) {
        this.mNitzData = nitz;
        this.mNitzUtils.setNitzData(nitz);
        FileUtils.writeNitz(this.mNitzData);
    }

    public void firstNitzUpdateFromFile() {
        if (TextUtils.isEmpty(this.mNitzUtils.getNitz())) {
            String nitz = FileUtils.readNitz();
            if (!TextUtils.isEmpty(nitz)) {
                this.mNitzUtils.setNitzData(nitz);
                return;
            } else {
                LogUtils.e(AbstractConnection.TAG, "firstNitzUpdateFromFile value is empty", new Object[0]);
                return;
            }
        }
        LogUtils.d(AbstractConnection.TAG, "firstNitzUpdateFromFile the nitz data have been set", new Object[0]);
    }

    public boolean deleteNfcKeyByType(int type) {
        LogUtils.i(AbstractConnection.TAG, "deleteNfcKeyByType type:" + type, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "deleteNfcKeyByType TBox Connection failed", new Object[0]);
            return false;
        }
        boolean result = sendAsyncMessage(MessageUtils.buildNfcAllDelete(type));
        return result;
    }

    public boolean deleteNfcKeyByKeyNo(int type, String keyNo) {
        LogUtils.i(AbstractConnection.TAG, "deleteNfcKeyByKeyNo type:" + type + " keyNo:" + keyNo, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "deleteNfcKeyByKeyNo TBox Connection failed", new Object[0]);
            return false;
        }
        this.mNfcDeleteSigleKey = keyNo;
        boolean result = sendAsyncMessage(MessageUtils.buildNfcDelete(1, keyNo.getBytes(StandardCharsets.UTF_8)));
        return result;
    }

    public boolean deleteOwnIphoneKey(int type, String keyNo) {
        LogUtils.i(AbstractConnection.TAG, "deleteOwnIphoneKey type:" + type + " keyNo:" + keyNo, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "deleteOwnIphoneKey TBox Connection failed", new Object[0]);
            return false;
        }
        this.mNfcIphoneKey = keyNo;
        boolean result = sendAsyncMessage(MessageUtils.buildNfcDelete(4, keyNo.getBytes(StandardCharsets.UTF_8)));
        return result;
    }

    private void recvDelayMsg(int what, long delay) {
        Message msg = this.recvHandler.obtainMessage();
        msg.what = what;
        if (this.recvHandler.hasMessages(what)) {
            this.recvHandler.removeMessages(what);
        }
        this.recvHandler.sendMessageDelayed(msg, delay);
    }

    public int deleteTboxLog(int req) {
        LogUtils.i(AbstractConnection.TAG, "deleteTboxLog req:" + req, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "deleteTboxLog TBox Connection failed", new Object[0]);
            return -1;
        }
        final AtomicInteger ret = new AtomicInteger(-1);
        sendSyncMessage(MessageUtils.buildDeleteTboxLogMessage(req), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.25
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage tboxMessage = (TboxMessage) msg;
                byte[] data = tboxMessage.getData();
                if (data.length < 1) {
                    LogUtils.e(AbstractConnection.TAG, "deleteTboxLog response len error:" + data.length, new Object[0]);
                    return;
                }
                ret.set(data[0]);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.e(AbstractConnection.TAG, "deleteTboxLog timeout", new Object[0]);
            }
        });
        return ret.get();
    }

    public boolean tcpdumpReq(int req) {
        LogUtils.i(AbstractConnection.TAG, "tcpdumpReq req:" + req, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "tcpdumpReq TBox Connection failed", new Object[0]);
            return false;
        }
        final AtomicBoolean ret = new AtomicBoolean(false);
        sendSyncMessage(MessageUtils.buildTcpdumpMessage(req), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.26
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TboxMessage tboxMessage = (TboxMessage) msg;
                byte[] data = tboxMessage.getData();
                if (data.length < 1) {
                    LogUtils.e(AbstractConnection.TAG, "tcpdumpReq response len error:" + data, new Object[0]);
                    return;
                }
                if (data[0] == 0) {
                    ret.set(true);
                    return;
                }
                if (data[0] == 1) {
                    ret.set(false);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "tcpdumpReq response ret error:" + data, new Object[0]);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.e(AbstractConnection.TAG, "tcpdumpReq timeout", new Object[0]);
            }
        });
        return ret.get();
    }

    public void recvMessageDescryptedFailed() {
        this.mAuthStateMachine.sendMessageDescryptedFailed();
    }

    private void compatWithOldApnInfo(TboxMessage msg) {
        byte[] data = msg.getData();
        if (data.length < 5) {
            LogUtils.e(AbstractConnection.TAG, "error message data length:" + data.length + " data:" + Arrays.toString(data), new Object[0]);
            return;
        }
        ByteBuffer buf = ByteBuffer.wrap(data);
        int state = buf.get();
        int ip = buf.getInt();
        Apn1Info apn1Info = new Apn1Info();
        this.mApn1Info = apn1Info;
        apn1Info.setApConnectState(state);
        this.mApn1Info.setIp(ip);
        String uuid = new String(data, 5, data.length - 5).trim();
        this.mApn1Info.setUuid(uuid);
        LogUtils.i(AbstractConnection.TAG, "getApn1Info:" + this.mApn1Info, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compateWithApnInfo(TboxMessage msg) {
        byte[] data = msg.getData();
        if (data.length < 17) {
            LogUtils.w(AbstractConnection.TAG, "error message data length:" + data.length + " data:" + Arrays.toString(data), new Object[0]);
            compatWithOldApnInfo(msg);
            return;
        }
        ByteBuffer buf = ByteBuffer.wrap(data);
        ApnInfo apnInfo = new ApnInfo();
        int apn = buf.get();
        apnInfo.setApn(apn);
        int result = buf.get();
        apnInfo.setResult(result);
        int state = buf.get();
        apnInfo.setApConnectState(state);
        int ip = buf.getInt();
        apnInfo.setIp(ip);
        buf.getInt();
        buf.getInt();
        int code = buf.get();
        apnInfo.setErrorCode(code);
        String uuid = new String(data, 17, data.length - 17, StandardCharsets.UTF_8).trim();
        apnInfo.setUuid(uuid);
        LogUtils.i(AbstractConnection.TAG, "getApn1Info:" + apnInfo, new Object[0]);
        this.mApnInfo = apnInfo;
        Apn1Info apn1Info = new Apn1Info();
        this.mApn1Info = apn1Info;
        apn1Info.setApConnectState(apnInfo.getApConnectState());
        this.mApn1Info.setIp(apnInfo.getIp());
        this.mApn1Info.setUuid(uuid);
        this.recvHandler.post(new Runnable() { // from class: com.engine.tbox.item.-$$Lambda$TBoxConnection$YaWYU0CY_KIHMpbUHDnIngn77a8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$compateWithApnInfo$2$TBoxConnection();
            }
        });
    }

    public /* synthetic */ void lambda$compateWithApnInfo$2$TBoxConnection() {
        onApnState(this.mApnInfo);
    }

    public Apn1Info getApn1Info() {
        LogUtils.i(AbstractConnection.TAG, "getApn1Info start", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "getApn1Info TBox Connection failed", new Object[0]);
            return null;
        }
        this.mApn1Info = null;
        sendSyncMessage(MessageUtils.buildApn1Req(), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.27
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TBoxConnection.this.compateWithApnInfo((TboxMessage) msg);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.e(AbstractConnection.TAG, "getApn1Info timeout", new Object[0]);
            }
        });
        return this.mApn1Info;
    }

    public boolean sendNfcCanQuery(short id, byte subid, byte pos, byte[] data) {
        LogUtils.i(AbstractConnection.TAG, "sendNfcQuery id:" + ((int) id) + " subid:" + ((int) subid) + " pos:" + ((int) pos) + " data:" + Arrays.toString(data), new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendNfcCanQuery TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildNfcServerCanMessage(id, subid, pos, data);
        if (msg == null) {
            LogUtils.e(AbstractConnection.TAG, "build nfc can query failed", new Object[0]);
            return false;
        }
        return sendAsyncMessage(msg);
    }

    public boolean sendNfcCanQueryV2(short id, byte[] data) {
        LogUtils.i(AbstractConnection.TAG, "sendNfcCanQueryV2 id:" + ((int) id) + " data:" + data, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendNfcCanQueryV2 TBox Connection failed", new Object[0]);
            return false;
        }
        return sendAsyncMessage(MessageUtils.buildNfcTboxMessage(id, data));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendBindCloudReq() {
        LogUtils.i(AbstractConnection.TAG, "sendBindCloudReq", new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendBindCloudReq TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildBindCloudReq();
        return sendAsyncMessage(msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean sendBindCloudRes(int ack) {
        LogUtils.i(AbstractConnection.TAG, "sendBindCloudRes ack:" + ack, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendBindCloudRes TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildBindCloudRes(ack);
        return sendAsyncMessage(msg);
    }

    public void sendBindCloudRsp(int status, String url) {
        this.mBroadCastUtils.sendBindCloudRsp(status, url);
    }

    public void sendBindCloudInfo(int status) {
        this.mBroadCastUtils.sendBindInfo(status);
    }

    public boolean sendOtaSupport(boolean supported) {
        LogUtils.d(AbstractConnection.TAG, "sendOtaSupport:" + supported, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendOtaSupport TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildOtaSupoortMessage(supported);
        return sendAsyncMessage(msg);
    }

    public boolean remoteLockAndUnlock(int op, int status) {
        LogUtils.i(AbstractConnection.TAG, "remoteLockAndUnlock op:" + op + " status:" + status, new Object[0]);
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "remoteLockAndUnlock TBox Connection failed", new Object[0]);
            return false;
        }
        if (op != 7 && op != 8) {
            return false;
        }
        TboxMessage msg = MessageUtils.buildNfcLockStatus(op, status);
        return sendAsyncMessage(msg);
    }

    public boolean reqNetworkOperatorMode() {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "reqNetworkOperatorMode TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildNetworkModeReq();
        return sendAsyncMessage(msg);
    }

    public boolean reqOperatorInfo() {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "reqOperatorInfo TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildGetManualNetworkListReq();
        return sendAsyncMessage(msg);
    }

    public boolean setManuallyStationedNetwork(String network, int sigLevel) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "setManuallyStationedNetwork TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildSelectManualNetworkReq(network, sigLevel);
        return sendAsyncMessage(msg);
    }

    public boolean setAutomaticNetwork() {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "setAutomaticNetwork TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildAutoNetSetReq();
        return sendAsyncMessage(msg);
    }

    public boolean reqSimCardState() {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "reqSimCardState TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildSimDiagReq();
        return sendAsyncMessage(msg);
    }

    public boolean operaQxdmLog(int start, int logCategory) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "reqSimCardState TBox Connection failed", new Object[0]);
            return false;
        }
        TboxMessage msg = MessageUtils.buildExportQxdmLogReq(start, logCategory);
        final AtomicBoolean ret = new AtomicBoolean(false);
        sendSyncMessage(msg, new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.28
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg2) {
                TboxMessage tboxMessage = (TboxMessage) msg2;
                byte[] bytes = tboxMessage.getData();
                if (bytes != null && bytes.length == 1) {
                    ret.set(bytes[0] == 0);
                    return;
                }
                LogUtils.e(AbstractConnection.TAG, "error operaQxdmLog rsp data:" + bytes, new Object[0]);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg2) {
                LogUtils.e(AbstractConnection.TAG, "error operaQxdmLog onTimeout", new Object[0]);
            }
        });
        return ret.get();
    }

    public void notifyAutoModeChanged(int mode) {
        LogUtils.i(AbstractConnection.TAG, "notifyAutoModeChanged:" + mode, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onNetworkOperatorModeChange(mode);
        }
    }

    public void onManuallyStationedNetworkChange(String mccMnc, int curNetType, int state, String lastMccMnc, int lastNetType) {
        LogUtils.d(AbstractConnection.TAG, "onNetworkOperatorModeChange mccMnc:" + mccMnc + " curNetType:" + curNetType + " state:" + state + " lastMccMnc:" + lastMccMnc + " lastNetType:" + lastNetType, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onManuallyStationedNetworkChange(mccMnc, curNetType, state, lastMccMnc, lastNetType);
        }
    }

    public void onAutomaticNetworkChange(int state) {
        LogUtils.d(AbstractConnection.TAG, "onAutomaticNetworkChange state:" + state, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onAutomaticNetworkChange(state);
        }
    }

    public void onSimCardStateChange(int status) {
        LogUtils.d(AbstractConnection.TAG, "onSimCardStateChange status:" + status, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onSimCardStateChange(status);
        }
    }

    private void delaySendApnResultNty(long delayTime) {
        Message msg = this.recvHandler.obtainMessage();
        msg.what = 15;
        msg.arg1 = 1;
        if (delayTime > 0) {
            this.recvHandler.sendMessageDelayed(msg, delayTime);
        } else {
            this.recvHandler.sendMessage(msg);
        }
    }

    public boolean sendApnArgs(ApnArgs apnArgs) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendApnArgs TBox Connection failed", new Object[0]);
            delaySendApnResultNty(0L);
            return false;
        }
        TboxMessage msg = MessageUtils.buildApnArgSetReq(apnArgs);
        sendAsyncMessage(msg);
        delaySendApnResultNty(1000L);
        return true;
    }

    public boolean sendSimCardType(int type) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendSimCardType TBox Connection failed", new Object[0]);
            return false;
        }
        if (type == 0) {
            this.mNetdClient.setSignalApn();
        } else {
            this.mNetdClient.setOtherCard();
        }
        return sendAsyncMessage(MessageUtils.buildSyncSimReq(type));
    }

    public boolean queryNetworkFlow(int apn, String iccid) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "queryNetworkFlow TBox Connection failed", new Object[0]);
            return false;
        }
        return sendAsyncMessage(MessageUtils.buildNetworkFlowReq(apn, iccid));
    }

    public boolean queryApnArgs() {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "queryApnArgs TBox Connection failed", new Object[0]);
            return false;
        }
        return sendAsyncMessage(MessageUtils.buildApnArgReq());
    }

    public boolean getTboxNetworkState(int apn) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "queryApnArgs TBox Connection failed", new Object[0]);
            return false;
        }
        return sendSyncMessage(MessageUtils.buildApnStateQuery(apn), new IResponse() { // from class: com.engine.tbox.item.TBoxConnection.29
            @Override // com.engine.tbox.core.conn.IResponse
            public void onResponse(CountSyncMessage msg) {
                TBoxConnection.this.compateWithApnInfo((TboxMessage) msg);
            }

            @Override // com.engine.tbox.core.conn.IResponse
            public void onTimeout(CountSyncMessage msg) {
                LogUtils.e(AbstractConnection.TAG, "getTboxNetworkState timeout", new Object[0]);
            }
        });
    }

    public void onApnArgsNty(ApnArgs args) {
        LogUtils.d(AbstractConnection.TAG, "onApnArgsNty:" + args, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onApnArgsNty(args);
        }
    }

    public void onSendApnArgsResult(int result) {
        LogUtils.d(AbstractConnection.TAG, "onSendApnArgsResult:" + result, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.sendApnArgsResult(result);
        }
    }

    public void onNetworkFlowNty(NetworkFlow flow) {
        LogUtils.d(AbstractConnection.TAG, "onNetworkFlowNty:" + flow, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onNetworkFlowNty(flow);
        }
    }

    public boolean setNetworkLimit(boolean enable) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "queryApnArgs TBox Connection failed", new Object[0]);
            return false;
        }
        LogUtils.d(AbstractConnection.TAG, "setNetworkLimit:" + enable, new Object[0]);
        SystemProperties.set(PROP_PERSIST_NET_LIMIT, enable ? "1" : "0");
        this.mNetdClient.netLimit(enable);
        return true;
    }

    public boolean send539Resp(String json) {
        return this.mBroadCastUtils.send539(json);
    }

    public void onApnState(ApnInfo info) {
        LogUtils.d(AbstractConnection.TAG, "onApnState:" + info, new Object[0]);
        ITboxListener iTboxListener = this.mListener;
        if (iTboxListener != null) {
            iTboxListener.onTboxNetworkState(info.getApn(), info.getApConnectState());
            this.mListener.onTboxNetworkStateResult(info);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRealNameChange(int realNameChange) {
        LogUtils.d(AbstractConnection.TAG, "setRealNameChange:" + realNameChange, new Object[0]);
        if (realNameChange == 1) {
            this.mNetdClient.setDoubleApn();
        }
        sendRealNameSetReqInternal(realNameChange);
    }

    private void sendRealNameSetReqInternal(int realNameChange) {
        if (this.mState != ConnectionState.CONNECTED) {
            LogUtils.e(AbstractConnection.TAG, "sendRealNameSetReqInternal TBox Connection failed", new Object[0]);
        } else {
            sendAsyncMessage(MessageUtils.buildRealNameSetReq(realNameChange));
        }
    }
}
