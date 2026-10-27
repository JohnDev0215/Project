package com.ts.carplay.lib.connect;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.common.CpConst;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.lib.interfaces.ReconnectionDependence;
import com.ts.carplay.lib.interfaces.ReconnectionFactor;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayReconnect implements ReconnectionFactor {
    private static final String KEY_DEVICE_UUID = "DeviceUuid";
    private static final String KEY_SCENARIOS = "Scenarios";
    private static final String LAST_CARPLAY_DEVICE = "LastCarPlayDevice";
    private static final int MAX_RECONNECT_COUNT = 5;
    private static final int MSG_TYPE_CONNECT_PROJECTION_FAIL = 109;
    private static final int MSG_TYPE_CONNECT_PROJECTION_SUCCESS = 108;
    private static final int MSG_TYPE_CONNECT_RFCOMM = 103;
    private static final int MSG_TYPE_CONNECT_RFCOMM_FAIL = 105;
    private static final int MSG_TYPE_CONNECT_RFCOMM_SUCCESS = 104;
    private static final int MSG_TYPE_LAST_DEVICE_CHANGE = 0;
    private static final int MSG_TYPE_MEET_RECONNECT_CONDITIONS = 100;
    private static final int MSG_TYPE_PRE_RECONNECT_FAIL = 102;
    private static final int MSG_TYPE_PRE_RECONNECT_SUCCESS = 101;
    private static final int MSG_TYPE_RECONNECT_CARPLAY_AGAIN = 111;
    private static final int MSG_TYPE_RECONNECT_CARPLAY_FAIL = 110;
    private static final int MSG_TYPE_WIRELESS_CARPLAY_AVAILABLE = 106;
    private static final int MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE = 107;
    private static final int RECONNECT_INTERVAL = 5000;
    private static final String TAG = "CarPlayReconnect";
    public static final int WAIT_FOR_APOPEN_TIMEOUT = 10000;
    private Context mContext;
    private ReconnectionDependence mDependence;
    private ReconnectStateMachine mStateMachine;
    private WifiManager mWifiManager;
    private SharedPreferences mSharedPreferences = null;
    private SharedPreferences.Editor mEditor = null;
    private HandlerThread mReconnectThread = null;
    private boolean mDelayCheck = false;
    private LastDevice mLastDevice = new LastDevice();
    private boolean mIsSystemReady = false;
    private int mReconnectScenario = -1;
    private int mReconnectCount = 0;

    public CarPlayReconnect(ReconnectionDependence dependence) {
        this.mDependence = dependence;
    }

    public void init(Context context) {
        this.mContext = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(LAST_CARPLAY_DEVICE, 0);
        this.mSharedPreferences = sharedPreferences;
        if (sharedPreferences == null) {
            LogUtil.warning(TAG, "init SharedPreferences is null.");
        } else {
            this.mEditor = sharedPreferences.edit();
        }
        this.mWifiManager = (WifiManager) this.mContext.getSystemService(WifiManager.class);
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mReconnectThread = handlerThread;
        handlerThread.start();
        ReconnectStateMachine reconnectStateMachine = new ReconnectStateMachine(TAG, this.mReconnectThread.getLooper());
        this.mStateMachine = reconnectStateMachine;
        reconnectStateMachine.start();
        this.mReconnectCount = 0;
    }

    public int getReconnectCount() {
        return this.mReconnectCount;
    }

    private static class LastDevice {
        private String mDeviceId;
        private int mScenario;

        private LastDevice() {
        }

        public void setDeviceInfo(String deviceId, int scenario) {
            this.mDeviceId = deviceId;
            this.mScenario = scenario;
        }

        public String getDeviceId() {
            return this.mDeviceId;
        }

        public int getScenario() {
            return this.mScenario;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("LastDevice {");
            sb.append(" mDeviceId:");
            sb.append(LogEncrypt.MacAddressEncrypt(this.mDeviceId));
            sb.append(" mScenario:");
            sb.append(CpConst.DisconnectScenario.toString(this.mScenario));
            sb.append(" }");
            return sb.toString();
        }
    }

    private class ReconnectStateMachine extends StateMachine {
        private ConfirmAvailableState mConfirmAvailableState;
        private ConnectProjectionState mConnectProjectionState;
        private ConnectRfCommState mConnectRfCommState;
        private State mCurState;
        private IdleState mIdleState;
        private PreReconnectState mPreReconnectState;
        private WaitReconnectResultState mWaitReconnectResultState;

        ReconnectStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mPreReconnectState = new PreReconnectState();
            this.mConnectRfCommState = new ConnectRfCommState();
            this.mConfirmAvailableState = new ConfirmAvailableState();
            this.mConnectProjectionState = new ConnectProjectionState();
            this.mWaitReconnectResultState = new WaitReconnectResultState();
            IdleState idleState = this.mIdleState;
            this.mCurState = idleState;
            addState(idleState);
            addState(this.mPreReconnectState);
            addState(this.mConnectRfCommState);
            addState(this.mConfirmAvailableState);
            addState(this.mConnectProjectionState);
            addState(this.mWaitReconnectResultState);
            setInitialState(this.mIdleState);
        }

        @Override // com.ts.carplay.lib.common.utils.StateMachine
        protected String getWhatToString(int what) {
            if (what == 0) {
                return "MSG_TYPE_LAST_DEVICE_CHANGE";
            }
            switch (what) {
                case 100:
                    return "MSG_TYPE_MEET_RECONNECT_CONDITIONS";
                case CarPlayReconnect.MSG_TYPE_PRE_RECONNECT_SUCCESS /* 101 */:
                    return "MSG_TYPE_PRE_RECONNECT_SUCCESS";
                case CarPlayReconnect.MSG_TYPE_PRE_RECONNECT_FAIL /* 102 */:
                    return "MSG_TYPE_PRE_RECONNECT_FAIL";
                case CarPlayReconnect.MSG_TYPE_CONNECT_RFCOMM /* 103 */:
                    return "MSG_TYPE_CONNECT_RFCOMM";
                case 104:
                    return "MSG_TYPE_CONNECT_RFCOMM_SUCCESS";
                case CarPlayReconnect.MSG_TYPE_CONNECT_RFCOMM_FAIL /* 105 */:
                    return "MSG_TYPE_CONNECT_RFCOMM_FAIL";
                case CarPlayReconnect.MSG_TYPE_WIRELESS_CARPLAY_AVAILABLE /* 106 */:
                    return "MSG_TYPE_WIRELESS_CARPLAY_AVAILABLE";
                case CarPlayReconnect.MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE /* 107 */:
                    return "MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE";
                case 108:
                    return "MSG_TYPE_CONNECT_PROJECTION_SUCCESS";
                case CarPlayReconnect.MSG_TYPE_CONNECT_PROJECTION_FAIL /* 109 */:
                    return "MSG_TYPE_CONNECT_PROJECTION_FAIL";
                case CarPlayReconnect.MSG_TYPE_RECONNECT_CARPLAY_FAIL /* 110 */:
                    return "MSG_TYPE_RECONNECT_CARPLAY_FAIL";
                case CarPlayReconnect.MSG_TYPE_RECONNECT_CARPLAY_AGAIN /* 111 */:
                    return "MSG_TYPE_RECONNECT_CARPLAY_AGAIN";
                default:
                    return null;
            }
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                reconnectStateMachine.mCurState = reconnectStateMachine.mIdleState;
                CarPlayReconnect.this.mReconnectScenario = -1;
                LogUtil.debug(CarPlayReconnect.TAG, "IdleState enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                if (message.what == 100) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    CarPlayReconnect.this.startSoftAp();
                    ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                    reconnectStateMachine.transitionTo(reconnectStateMachine.mPreReconnectState);
                    return true;
                }
                return false;
            }
        }

        class PreReconnectState extends State {
            PreReconnectState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                reconnectStateMachine.mCurState = reconnectStateMachine.mPreReconnectState;
                LogUtil.debug(CarPlayReconnect.TAG, "PreReconnectState enter");
                CarPlayReconnect.this.preReconnect();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                    reconnectStateMachine.transitionTo(reconnectStateMachine.mIdleState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_PRE_RECONNECT_SUCCESS) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine2 = ReconnectStateMachine.this;
                    reconnectStateMachine2.transitionTo(reconnectStateMachine2.mConfirmAvailableState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_PRE_RECONNECT_FAIL) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    CarPlayReconnect.this.setBluetoothAutoConnectEnabled(true, null, false);
                    ReconnectStateMachine reconnectStateMachine3 = ReconnectStateMachine.this;
                    reconnectStateMachine3.transitionTo(reconnectStateMachine3.mConnectRfCommState);
                    return true;
                }
                return false;
            }
        }

        class ConnectRfCommState extends State {
            ConnectRfCommState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                reconnectStateMachine.mCurState = reconnectStateMachine.mConnectRfCommState;
                LogUtil.debug(CarPlayReconnect.TAG, "ConnectRfCommState enter");
                CarPlayReconnect.this.mStateMachine.sendMessageDelayed(CarPlayReconnect.MSG_TYPE_CONNECT_RFCOMM, 5000L);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i != 0) {
                    switch (i) {
                        case CarPlayReconnect.MSG_TYPE_CONNECT_RFCOMM /* 103 */:
                            LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                            if (CarPlayReconnect.this.isPaired(CarPlayReconnect.this.getLastDeviceUuid())) {
                                CarPlayReconnect.this.connectRfComm();
                            } else {
                                LogUtil.warning(CarPlayReconnect.TAG, "not paired, transfer to idle");
                                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                                reconnectStateMachine.transitionTo(reconnectStateMachine.mIdleState);
                            }
                            break;
                        case 104:
                            LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                            ReconnectStateMachine reconnectStateMachine2 = ReconnectStateMachine.this;
                            reconnectStateMachine2.transitionTo(reconnectStateMachine2.mConfirmAvailableState);
                            break;
                        case CarPlayReconnect.MSG_TYPE_CONNECT_RFCOMM_FAIL /* 105 */:
                            LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                            CarPlayReconnect.this.mStateMachine.sendMessageDelayed(CarPlayReconnect.MSG_TYPE_CONNECT_RFCOMM, 5000L);
                            break;
                    }
                    return true;
                }
                LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                ReconnectStateMachine reconnectStateMachine3 = ReconnectStateMachine.this;
                reconnectStateMachine3.transitionTo(reconnectStateMachine3.mIdleState);
                return true;
            }
        }

        class ConfirmAvailableState extends State {
            ConfirmAvailableState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                reconnectStateMachine.mCurState = reconnectStateMachine.mConfirmAvailableState;
                LogUtil.debug(CarPlayReconnect.TAG, "ConfirmAvailableState enter");
                CarPlayReconnect.this.confirmWirelessCarPlayAvailable();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                    reconnectStateMachine.transitionTo(reconnectStateMachine.mIdleState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_WIRELESS_CARPLAY_AVAILABLE) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine2 = ReconnectStateMachine.this;
                    reconnectStateMachine2.transitionTo(reconnectStateMachine2.mConnectProjectionState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    if (48 == CarPlayReconnect.this.getLastDeviceScenario() || 32 == CarPlayReconnect.this.getLastDeviceScenario()) {
                        CarPlayReconnect.this.setLastDeviceInfo(CarPlayReconnect.this.getLastDeviceUuid(), 96);
                    }
                    if (96 != CarPlayReconnect.this.getLastDeviceScenario() && CarPlayReconnect.this.mDependence != null) {
                        CarPlayReconnect.this.mDependence.connectBtClassicProfile(CarPlayReconnect.this.getLastDeviceBtAddr());
                    }
                    CarPlayReconnect.this.setBluetoothAutoConnectEnabled(true, null, false);
                    ReconnectStateMachine reconnectStateMachine3 = ReconnectStateMachine.this;
                    reconnectStateMachine3.transitionTo(reconnectStateMachine3.mIdleState);
                    return true;
                }
                return false;
            }
        }

        class ConnectProjectionState extends State {
            ConnectProjectionState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                reconnectStateMachine.mCurState = reconnectStateMachine.mConnectProjectionState;
                LogUtil.debug(CarPlayReconnect.TAG, "ConnectProjectionState enter");
                CarPlayReconnect.this.connectProjection();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                    reconnectStateMachine.transitionTo(reconnectStateMachine.mIdleState);
                    return true;
                }
                if (i == 108) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine2 = ReconnectStateMachine.this;
                    reconnectStateMachine2.transitionTo(reconnectStateMachine2.mWaitReconnectResultState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_CONNECT_PROJECTION_FAIL) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    CarPlayReconnect.this.setBluetoothAutoConnectEnabled(true, null, false);
                    ReconnectStateMachine reconnectStateMachine3 = ReconnectStateMachine.this;
                    reconnectStateMachine3.transitionTo(reconnectStateMachine3.mIdleState);
                    return true;
                }
                return false;
            }
        }

        class WaitReconnectResultState extends State {
            WaitReconnectResultState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                reconnectStateMachine.mCurState = reconnectStateMachine.mWaitReconnectResultState;
                LogUtil.debug(CarPlayReconnect.TAG, toString() + " enter");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                LogUtil.debug(CarPlayReconnect.TAG, toString() + " exit");
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    CarPlayReconnect.this.setBluetoothAutoConnectEnabled(true, CarPlayReconnect.this.getLastDeviceBtAddr(), true);
                    ReconnectStateMachine reconnectStateMachine = ReconnectStateMachine.this;
                    reconnectStateMachine.transitionTo(reconnectStateMachine.mIdleState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_RECONNECT_CARPLAY_FAIL) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    CarPlayReconnect.this.setBluetoothAutoConnectEnabled(true, null, false);
                    ReconnectStateMachine reconnectStateMachine2 = ReconnectStateMachine.this;
                    reconnectStateMachine2.transitionTo(reconnectStateMachine2.mIdleState);
                    return true;
                }
                if (i == CarPlayReconnect.MSG_TYPE_RECONNECT_CARPLAY_AGAIN) {
                    LogUtil.debug(CarPlayReconnect.TAG, ReconnectStateMachine.this.getWhatToString(message.what));
                    ReconnectStateMachine reconnectStateMachine3 = ReconnectStateMachine.this;
                    reconnectStateMachine3.transitionTo(reconnectStateMachine3.mIdleState);
                    CarPlayReconnect.this.checkReconnectionConditions(3);
                    return true;
                }
                return false;
            }
        }

        public State getCurState() {
            return this.mCurState;
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onReconnectionReady() {
        readLastDevice();
        LogUtil.debug(TAG, "onReconnectionReady, mLastDevice:" + this.mLastDevice);
        this.mIsSystemReady = true;
        ReconnectionDependence reconnectionDependence = this.mDependence;
        if (reconnectionDependence != null && reconnectionDependence.isBluetoothEnabled()) {
            checkReconnectionConditions(1);
            return;
        }
        LogUtil.warning(TAG, "onReconnectionReady mDependence is:" + this.mDependence);
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onProjectionConnectionStateChanged(String devId, int projectionType, int status, int reason) {
        if (devId == null) {
            LogUtil.warning(TAG, "onProjectionServiceStateChanged devId is null.");
            return;
        }
        LogUtil.debug(TAG, "onProjectionServiceStateChanged lastDevice:" + this.mLastDevice + " devId:" + LogEncrypt.MacAddressEncrypt(devId) + " type:" + projectionType + " status:" + status + " reason:" + reason);
        boolean isSameWithLastDevice = false;
        if (devId.equals(getLastDeviceUuid())) {
            isSameWithLastDevice = true;
        }
        if (1 == status) {
            this.mDelayCheck = false;
            boolean isUserConnect = 17 == reason;
            if (!isSameWithLastDevice || isUserConnect) {
                clearLastDevice();
                return;
            }
            return;
        }
        if (2 == status) {
            this.mReconnectCount = 0;
            setLastDeviceInfo(devId, 0);
            return;
        }
        if (3 == status) {
            if (projectionType == 1 && isSameWithLastDevice) {
                LogUtil.debug(TAG, "Wireless carplay is disconnecting,need to reconnect. devId:" + devId);
                this.mDelayCheck = true;
                return;
            }
            return;
        }
        if (4 == status) {
            if (isSameWithLastDevice) {
                if (projectionType == 1) {
                    if (this.mStateMachine.getCurState() == this.mStateMachine.mWaitReconnectResultState) {
                        if (this.mReconnectCount < 5) {
                            if (this.mDelayCheck) {
                                LogUtil.debug(TAG, "Reconnect count:" + this.mReconnectCount + ",try to reconnect again. devId:" + devId + ",curState: " + this.mStateMachine.getCurState());
                                int scenario = getLastDeviceScenario();
                                if (scenario == 0) {
                                    setLastDeviceInfo(devId, 48);
                                }
                                this.mStateMachine.sendMessage(MSG_TYPE_RECONNECT_CARPLAY_AGAIN);
                            }
                            int scenario2 = this.mReconnectCount;
                            this.mReconnectCount = scenario2 + 1;
                        } else {
                            this.mReconnectCount = 0;
                            this.mStateMachine.sendMessage(MSG_TYPE_RECONNECT_CARPLAY_FAIL);
                            LogUtil.info(TAG, "reconnect carplay fail 6 times,not to reconnect again");
                        }
                    } else if (this.mDelayCheck) {
                        LogUtil.debug(TAG, "Wireless carplay has disconnected,try to reconnect. devId:" + devId + ",curState: " + this.mStateMachine.getCurState());
                        int scenario3 = getLastDeviceScenario();
                        if (scenario3 == 0) {
                            setLastDeviceInfo(devId, 48);
                        }
                        checkReconnectionConditions(3);
                    }
                } else if (projectionType == 0) {
                    setLastDeviceInfo(devId, 64);
                }
            }
            this.mDelayCheck = false;
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onBtProfileStateChanged(String address, int profile, int state) {
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onBtPairStateChanged(String address, boolean isPair) {
        if (isPair) {
            String devId = getLastDeviceUuid();
            if (!TextUtils.isEmpty(devId) && !TextUtils.isEmpty(address) && !address.equals(getLastDeviceBtAddr())) {
                LogUtil.debug(TAG, "onBtPairStateChanged stopReconnect address:" + address);
                clearLastDevice();
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onBtStateChanged(boolean open) {
        LogUtil.debug(TAG, "onBtStateChanged open:" + open);
        if (open && this.mIsSystemReady) {
            checkReconnectionConditions(1);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onWifiApStateChanged(boolean open) {
        LogUtil.debug(TAG, "onWifiApStateChanged open:" + open);
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onWifiDeviceAttach(String mac) {
        LogUtil.debug(TAG, "onWifiDeviceAttach, mac:" + mac);
        TextUtils.isEmpty(mac);
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onWirelessCarPlayUpdate(String devId, boolean available) {
        LogUtil.warning(TAG, "onWirelessCarPlayUpdate, devId:" + devId + "lastDevice:" + this.mLastDevice);
        if (devId != null && devId.equals(getLastDeviceUuid())) {
            if (available) {
                this.mStateMachine.sendMessage(MSG_TYPE_WIRELESS_CARPLAY_AVAILABLE);
            } else {
                this.mStateMachine.sendMessage(MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE);
            }
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onDisconnect(String devId, int scenario) {
        if (TextUtils.isEmpty(devId)) {
            LogUtil.warning(TAG, "onDisconnect, devId is null.");
            return;
        }
        String lastDeviceId = getLastDeviceUuid();
        LogUtil.debug(TAG, "onDisconnect, devId:" + devId + " scenario:" + CpConst.DisconnectScenario.toString(scenario) + " lastDeviceId:" + lastDeviceId);
        if (devId.equals(getLastDeviceUuid())) {
            setLastDeviceInfo(devId, scenario);
        }
    }

    @Override // com.ts.carplay.lib.interfaces.ReconnectionFactor
    public void onAccessoryTurnOff() {
        String devId = getLastDeviceUuid();
        int scenario = getLastDeviceScenario();
        boolean isPaired = isPaired(devId);
        LogUtil.debug(TAG, "onAccessoryTurnOff, devId:" + devId + " scenario:" + CpConst.DisconnectScenario.toString(scenario) + " isPaired:" + isPaired);
        if (64 == scenario && isPaired) {
            setLastDeviceInfo(devId, 65);
        }
        int lastDeviceScenario = getLastDeviceScenario();
        LogUtil.debug(TAG, "onAccessoryTurnOff, lastDeviceScenario:" + CpConst.DisconnectScenario.toString(lastDeviceScenario));
        if (lastDeviceScenario != 0 && lastDeviceScenario != 16 && lastDeviceScenario != 64 && !TextUtils.isEmpty(devId) && isPaired) {
            setBluetoothAutoConnectEnabled(false, getLastDeviceBtAddr(), false);
        }
    }

    public void onCarplayAvialability(String devId, boolean available) {
        if (devId == null) {
            return;
        }
        int disconnectScenario = getLastDeviceScenario();
        if (available && devId.equals(getLastDeviceUuid()) && 96 == disconnectScenario) {
            LogUtil.debug(TAG, "onCarplayAvialability connectProjection");
            checkReconnectionConditions(4);
        }
    }

    private synchronized void checkOtherDeviceProfileConnection() {
        Set<String> bondedDevices;
        String lastDeviceBtAddr = getLastDeviceBtAddr();
        if (TextUtils.isEmpty(lastDeviceBtAddr)) {
            return;
        }
        boolean hasProfileConnection = false;
        if (this.mDependence != null && (bondedDevices = this.mDependence.getBondedDevices()) != null && bondedDevices.size() > 0) {
            Iterator<String> it = bondedDevices.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String mac = it.next();
                if (!TextUtils.isEmpty(mac) && !mac.equals(lastDeviceBtAddr) && this.mDependence.isConnected(mac)) {
                    LogUtil.debug(TAG, "other device mac:" + LogEncrypt.MacAddressEncrypt(mac) + " has connected classic profile.");
                    hasProfileConnection = true;
                    break;
                }
            }
        }
        if (hasProfileConnection) {
            clearLastDevice();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkReconnectionConditions(int reconnectScenario) {
        if (meetReconnectionConditions(reconnectScenario)) {
            this.mReconnectScenario = reconnectScenario;
            this.mStateMachine.sendMessage(100);
        } else if (this.mDependence != null && this.mDependence.isWifiActived()) {
            LogUtil.warning(TAG, "not enable btautoconnect when carplay connecting");
        } else {
            LogUtil.warning(TAG, "Enable Bluetooth auto reconnect");
            setBluetoothAutoConnectEnabled(true, null, false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean meetReconnectionConditions(int r9) {
        /*
            r8 = this;
            r0 = 0
            com.ts.carplay.lib.interfaces.ReconnectionDependence r1 = r8.mDependence
            java.lang.String r2 = "CarPlayReconnect"
            if (r1 != 0) goto Ld
            java.lang.String r1 = "meetReconnectionConditions, mDependence is null."
            com.ts.carplay.common.util.LogUtil.warning(r2, r1)
            return r0
        Ld:
            boolean r1 = r1.isBluetoothEnabled()
            java.lang.String r3 = r8.getLastDeviceUuid()
            int r4 = r8.getLastDeviceScenario()
            boolean r5 = r8.isPaired(r3)
            if (r1 == 0) goto L48
            boolean r6 = android.text.TextUtils.isEmpty(r3)
            if (r6 != 0) goto L48
            boolean r5 = r8.isPaired(r3)
            if (r5 == 0) goto L48
            r6 = 1
            r7 = 48
            if (r6 != r9) goto L44
            if (r4 == r7) goto L42
            r6 = 65
            if (r4 == r6) goto L42
            r6 = 80
            if (r4 == r6) goto L42
            r6 = 96
            if (r4 == r6) goto L42
            switch(r4) {
                case 32: goto L42;
                case 33: goto L42;
                case 34: goto L42;
                default: goto L41;
            }
        L41:
            goto L48
        L42:
            r0 = 1
            goto L48
        L44:
            if (r4 == r7) goto L47
            goto L48
        L47:
            r0 = 1
        L48:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "meetReconnectionConditions ret:"
            r6.append(r7)
            r6.append(r0)
            java.lang.String r7 = " btState:"
            r6.append(r7)
            r6.append(r1)
            java.lang.String r7 = " lastCarPlayDevId:"
            r6.append(r7)
            r6.append(r3)
            java.lang.String r7 = " reconnectScenario:"
            r6.append(r7)
            java.lang.String r7 = com.ts.carplay.lib.common.CpConst.ReconnectScenario.toString(r9)
            r6.append(r7)
            java.lang.String r7 = " disconnectScenario:"
            r6.append(r7)
            java.lang.String r7 = com.ts.carplay.lib.common.CpConst.DisconnectScenario.toString(r4)
            r6.append(r7)
            java.lang.String r7 = " isPaired:"
            r6.append(r7)
            r6.append(r5)
            java.lang.String r6 = r6.toString()
            com.ts.carplay.common.util.LogUtil.debug(r2, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.carplay.lib.connect.CarPlayReconnect.meetReconnectionConditions(int):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSoftAp() {
        boolean isWifiApEnabled = this.mDependence.isWifiApEnabled();
        LogUtil.warning(TAG, "startSoftAp, wifiApState:" + isWifiApEnabled);
        if (this.mDependence != null && !isWifiApEnabled) {
            LogUtil.warning(TAG, "open wifiap");
            this.mDependence.startSoftAp();
            LogUtil.warning(TAG, "open wifiap done");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPaired(String id) {
        if (TextUtils.isEmpty(id)) {
            LogUtil.warning(TAG, "isPaired, id is null.");
            return false;
        }
        ReconnectionDependence reconnectionDependence = this.mDependence;
        if (reconnectionDependence == null) {
            LogUtil.warning(TAG, "isPaired, mDependence is null.");
            return false;
        }
        LinkDevice device = reconnectionDependence.getCarPlayDevice(id);
        if (device == null) {
            LogUtil.warning(TAG, "isPaired, device is null.");
            return false;
        }
        LogUtil.debug(TAG, "isPaired, device:" + device);
        Set<String> devices = this.mDependence.getBondedDevices();
        if (devices == null) {
            return false;
        }
        for (String mac : devices) {
            if (!TextUtils.isEmpty(mac) && mac.equals(device.getBtAddr())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preReconnect() {
        LogUtil.debug(TAG, "preReconnect mLastDevice:" + this.mLastDevice);
        int msgType = MSG_TYPE_PRE_RECONNECT_FAIL;
        if (this.mDependence != null) {
            String btMac = getLastDeviceBtAddr();
            if (btMac != null) {
                if (this.mDependence.connectRfcommSocket(btMac)) {
                    msgType = MSG_TYPE_PRE_RECONNECT_SUCCESS;
                }
            } else {
                LogUtil.warning(TAG, "preReconnect btMac is null.");
            }
        } else {
            LogUtil.warning(TAG, "preReconnect mDevicesManager is null.");
        }
        this.mStateMachine.sendMessage(msgType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectRfComm() {
        LogUtil.debug(TAG, "connectRfComm mLastDevice:" + this.mLastDevice);
        int msgType = MSG_TYPE_CONNECT_RFCOMM_FAIL;
        if (this.mDependence != null) {
            String btMac = getLastDeviceBtAddr();
            if (btMac != null) {
                if (this.mDependence.connectRfcommSocket(btMac)) {
                    msgType = 104;
                }
            } else {
                LogUtil.warning(TAG, "connectRfComm btMac is null.");
            }
        } else {
            LogUtil.warning(TAG, "connectRfComm mDevicesManager is null.");
        }
        this.mStateMachine.sendMessage(msgType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void confirmWirelessCarPlayAvailable() {
        ReconnectionDependence reconnectionDependence = this.mDependence;
        if (reconnectionDependence != null) {
            if (!reconnectionDependence.confirmWirelessCarPlayAvailable(getLastDeviceUuid())) {
                this.mStateMachine.sendMessage(MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE);
                return;
            }
            return;
        }
        LogUtil.warning(TAG, "confirmWirelessCarPlayAvailable null mLastDevice:" + this.mLastDevice + " mDependence:" + this.mDependence);
        this.mStateMachine.sendMessage(MSG_TYPE_WIRELESS_CARPLAY_UNAVAILABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectProjection() {
        LogUtil.debug(TAG, "connectProjection mLastDevice:" + this.mLastDevice);
        boolean ret = false;
        ReconnectionDependence reconnectionDependence = this.mDependence;
        if (reconnectionDependence != null) {
            ret = reconnectionDependence.reconnectWirelessCarPlay(getLastDeviceUuid());
        }
        this.mStateMachine.sendMessage(ret ? 108 : MSG_TYPE_CONNECT_PROJECTION_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setBluetoothAutoConnectEnabled(boolean enabled, String address, boolean reconnState) {
        ReconnectionDependence reconnectionDependence = this.mDependence;
        if (reconnectionDependence == null) {
            return false;
        }
        boolean ret = reconnectionDependence.setBluetoothAutoConnectEnabled(enabled, address, reconnState);
        return ret;
    }

    private synchronized void clearLastDevice() {
        setLastDeviceInfo("", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void setLastDeviceInfo(String deviceId, int scenario) {
        LogUtil.debug(TAG, "setLastDeviceInfo deviceId:" + LogEncrypt.MacAddressEncrypt(deviceId) + " scenario:" + CpConst.DisconnectScenario.toString(scenario));
        String lastDeviceUuid = getLastDeviceUuid();
        this.mLastDevice.setDeviceInfo(deviceId, scenario);
        writeLastDevice(deviceId, scenario);
        boolean change = true;
        if (!TextUtils.isEmpty(lastDeviceUuid) && lastDeviceUuid.equals(deviceId) && (scenario == 33 || scenario == 34 || scenario == 65)) {
            change = false;
        }
        if (change) {
            this.mStateMachine.sendMessage(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized String getLastDeviceUuid() {
        return this.mLastDevice != null ? this.mLastDevice.getDeviceId() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int getLastDeviceScenario() {
        if (TextUtils.isEmpty(getLastDeviceUuid())) {
            return 0;
        }
        if (this.mLastDevice == null) {
            return 0;
        }
        return this.mLastDevice.getScenario();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized String getLastDeviceBtAddr() {
        String mac;
        LinkDevice lastDevice;
        mac = null;
        if (this.mDependence != null && (lastDevice = this.mDependence.getCarPlayDevice(getLastDeviceUuid())) != null) {
            mac = lastDevice.getBtAddr();
        }
        return mac;
    }

    private synchronized String getLastDeviceWifiAddr() {
        String mac;
        LinkDevice lastDevice;
        mac = null;
        if (this.mDependence != null && (lastDevice = this.mDependence.getCarPlayDevice(getLastDeviceUuid())) != null) {
            mac = lastDevice.getWifiAddr();
        }
        return mac;
    }

    private synchronized void writeLastDevice(String deviceId, int scenario) {
        if (this.mEditor != null) {
            this.mEditor.clear();
            this.mEditor.putString(KEY_DEVICE_UUID, deviceId);
            this.mEditor.putInt(KEY_SCENARIOS, scenario);
            this.mEditor.commit();
        } else {
            LogUtil.warning(TAG, "writeLastDevice editor is null.");
        }
    }

    private synchronized void readLastDevice() {
        if (this.mSharedPreferences != null) {
            String deviceId = this.mSharedPreferences.getString(KEY_DEVICE_UUID, "");
            int scenario = this.mSharedPreferences.getInt(KEY_SCENARIOS, 0);
            setLastDeviceInfo(deviceId, scenario);
        } else {
            LogUtil.warning(TAG, "readLastDevice mSharedPreferences is null.");
        }
    }
}
