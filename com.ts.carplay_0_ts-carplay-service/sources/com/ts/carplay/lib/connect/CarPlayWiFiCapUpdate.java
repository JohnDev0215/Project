package com.ts.carplay.lib.connect;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.lib.transport.CarPlayTransport;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayWiFiCapUpdate {
    private static final int CARLAY_GET_CAPABILITY_TIME_OUT = 30000;
    private static final int DISCONNECT_TIME_OUT = 3;
    public static final int IAP2_SESSION_DESTORY = 7;
    public static final int MSG_CARPLAY_CAPBILITY_UPDATE_FALSE = 5;
    public static final int MSG_CARPLAY_CAPBILITY_UPDATE_TRUE = 4;
    public static final int MSG_GET_CAPBILITY = 2;
    public static final int MSG_GET_CAPBILITY_TIMEOUT = 6;
    public static final int MSG_IAP2_AUTH_FAILED = 3;
    public static final int MSG_IAP2_AUTH_SUCCESS = 8;
    public static final int MSG_RECONNECT_RFCOMM = 0;
    public static final int MSG_RELEASE_RESOURCE = 1;
    public static final int MSG_RFCOMM_DISCONNECT = 9;
    private static final long RECONNECT_RFCOMM_TIME_OUT_SECOND = 30;
    private static final String TAG = "CarPlayWiFiCapUpdate";
    private static final int TRANSPORT_OPEN_FAIL = -1;
    private static final UUID UUID_IAP = UUID.fromString("00000000-deca-fade-deca-deafdecacafe");
    private CarPlayTransport mCarPlayTransport;
    private final Condition mCondition;
    private IState mCurState;
    private final Condition mDisconnectCondition;
    private final Lock mDisconnectLock;
    private boolean mIsWaitingCheckResult = false;
    private LinkDevice mLinkDevice;
    private final ReentrantLock mLock;
    private CarPlayManager mManager;
    private int mRfcommHandle;
    private String mRfcommMac;
    private WiFiCapUpdateStateMachine mStateMachine;
    private HandlerThread mWifiCapUpdateThread;

    public CarPlayWiFiCapUpdate(CarPlayManager manager) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mLock = reentrantLock;
        this.mCondition = reentrantLock.newCondition();
        ReentrantLock reentrantLock2 = new ReentrantLock();
        this.mDisconnectLock = reentrantLock2;
        this.mDisconnectCondition = reentrantLock2.newCondition();
        this.mRfcommHandle = -1;
        this.mRfcommMac = null;
        this.mManager = manager;
        this.mCarPlayTransport = CarPlayTransport.getInstance();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mWifiCapUpdateThread = handlerThread;
        handlerThread.start();
        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = new WiFiCapUpdateStateMachine(TAG, this.mWifiCapUpdateThread.getLooper());
        this.mStateMachine = wiFiCapUpdateStateMachine;
        wiFiCapUpdateStateMachine.start();
    }

    private class WiFiCapUpdateStateMachine extends StateMachine {
        private BuildSocketState mBuildSocketState;
        private ConnectRfcommState mConnectRfcommState;
        private IapAuthForWirelessCapabilityState mIapAuthForWirelessCapabilityState;
        private IdleState mIdleState;
        private ReleaseRousourceState mReleaseRousourceState;

        WiFiCapUpdateStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mConnectRfcommState = new ConnectRfcommState();
            this.mBuildSocketState = new BuildSocketState();
            this.mIapAuthForWirelessCapabilityState = new IapAuthForWirelessCapabilityState();
            this.mReleaseRousourceState = new ReleaseRousourceState();
            addState(this.mIdleState);
            addState(this.mConnectRfcommState);
            addState(this.mBuildSocketState);
            addState(this.mIapAuthForWirelessCapabilityState);
            addState(this.mReleaseRousourceState);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IdleState enter");
                CarPlayWiFiCapUpdate.this.mCurState = WiFiCapUpdateStateMachine.this.mIdleState;
                CarPlayWiFiCapUpdate.this.notifyDisconnectComplete();
                WiFiCapUpdateStateMachine.this.removeMessages(1);
                WiFiCapUpdateStateMachine.this.removeMessages(7);
                WiFiCapUpdateStateMachine.this.removeMessages(9);
                if (CarPlayWiFiCapUpdate.this.mRfcommHandle != -1) {
                    CarPlayWiFiCapUpdate.this.mManager.closeConnection(CarPlayWiFiCapUpdate.this.mRfcommHandle);
                    CarPlayWiFiCapUpdate.this.mRfcommHandle = -1;
                    CarPlayWiFiCapUpdate.this.mRfcommMac = null;
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IdleState processMessage: " + message.what);
                int i = message.what;
                if (i == 0) {
                    WiFiCapUpdateStateMachine.this.deferMessage(message);
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine.transitionTo(wiFiCapUpdateStateMachine.mConnectRfcommState);
                    return true;
                }
                if (i == 1) {
                    CarPlayWiFiCapUpdate.this.notifyDisconnectComplete();
                    return true;
                }
                if (i == 2) {
                    WiFiCapUpdateStateMachine.this.deferMessage(message);
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine2 = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine2.transitionTo(wiFiCapUpdateStateMachine2.mConnectRfcommState);
                    return true;
                }
                return false;
            }
        }

        class ConnectRfcommState extends State {
            ConnectRfcommState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "ConnectRfcommState enter");
                CarPlayWiFiCapUpdate.this.mCurState = WiFiCapUpdateStateMachine.this.mConnectRfcommState;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "ConnectRfcommState processMessage: " + message.what);
                int i = message.what;
                if (i == 0) {
                    String rfcommMac = (String) message.obj;
                    if (CarPlayWiFiCapUpdate.this.mRfcommMac == null) {
                        int handle = CarPlayWiFiCapUpdate.this.mManager.connectToServer(rfcommMac, CarPlayWiFiCapUpdate.UUID_IAP.toString());
                        if (-1 != handle) {
                            CarPlayWiFiCapUpdate.this.mRfcommMac = rfcommMac;
                            CarPlayWiFiCapUpdate.this.mRfcommHandle = handle;
                        } else {
                            WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = WiFiCapUpdateStateMachine.this;
                            wiFiCapUpdateStateMachine.transitionTo(wiFiCapUpdateStateMachine.mIdleState);
                        }
                        CarPlayWiFiCapUpdate.this.notifyRfcommComplete();
                    } else if (CarPlayWiFiCapUpdate.this.mRfcommMac.equals(rfcommMac) && -1 != CarPlayWiFiCapUpdate.this.mRfcommHandle) {
                        CarPlayWiFiCapUpdate.this.notifyRfcommComplete();
                    } else {
                        WiFiCapUpdateStateMachine.this.deferMessage(message);
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine2 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine2.transitionTo(wiFiCapUpdateStateMachine2.mIdleState);
                    }
                    return true;
                }
                if (i == 1) {
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine3 = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine3.transitionTo(wiFiCapUpdateStateMachine3.mIdleState);
                    return true;
                }
                if (i != 2) {
                    if (i == 3) {
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine4 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine4.transitionTo(wiFiCapUpdateStateMachine4.mIdleState);
                        return true;
                    }
                    return false;
                }
                WiFiCapUpdateStateMachine.this.removeMessages(6);
                WiFiCapUpdateStateMachine.this.sendMessageDelayed(6, 30000L);
                String rfcommMac2 = (String) message.obj;
                if (CarPlayWiFiCapUpdate.this.mRfcommMac == null) {
                    int handle2 = CarPlayWiFiCapUpdate.this.mManager.connectToServer(rfcommMac2, CarPlayWiFiCapUpdate.UUID_IAP.toString());
                    if (-1 != handle2) {
                        LogUtil.info(CarPlayWiFiCapUpdate.TAG, "MSG_GET_CAPBILITY, last Rfcomm is null");
                        CarPlayWiFiCapUpdate.this.mRfcommMac = rfcommMac2;
                        CarPlayWiFiCapUpdate.this.mRfcommHandle = handle2;
                        WiFiCapUpdateStateMachine.this.deferMessage(message);
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine5 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine5.transitionTo(wiFiCapUpdateStateMachine5.mBuildSocketState);
                    } else {
                        LogUtil.info(CarPlayWiFiCapUpdate.TAG, "MSG_GET_CAPBILITY, connectToServer failed");
                        WiFiCapUpdateStateMachine.this.notifyCarPlayCapbility(rfcommMac2, false, 1);
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine6 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine6.transitionTo(wiFiCapUpdateStateMachine6.mIdleState);
                    }
                } else if (CarPlayWiFiCapUpdate.this.mRfcommMac.equals(rfcommMac2) && -1 != CarPlayWiFiCapUpdate.this.mRfcommHandle) {
                    LogUtil.info(CarPlayWiFiCapUpdate.TAG, "MSG_GET_CAPBILITY, Rfcomm already connected");
                    WiFiCapUpdateStateMachine.this.deferMessage(message);
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine7 = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine7.transitionTo(wiFiCapUpdateStateMachine7.mBuildSocketState);
                } else {
                    WiFiCapUpdateStateMachine.this.deferMessage(message);
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine8 = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine8.transitionTo(wiFiCapUpdateStateMachine8.mIdleState);
                }
                return true;
            }
        }

        class BuildSocketState extends State {
            BuildSocketState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "BuildSocketState enter");
                CarPlayWiFiCapUpdate.this.mCurState = WiFiCapUpdateStateMachine.this.mBuildSocketState;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "BuildSocketState processMessage: " + message.what);
                int i = message.what;
                if (i == 1) {
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine.transitionTo(wiFiCapUpdateStateMachine.mReleaseRousourceState);
                    return true;
                }
                if (i == 2) {
                    WiFiCapUpdateStateMachine.this.removeMessages(9);
                    String rfcommMac = (String) message.obj;
                    if (updateWirelessSocket(rfcommMac, CarPlayWiFiCapUpdate.this.mManager.getInputStream(CarPlayWiFiCapUpdate.this.mRfcommHandle), CarPlayWiFiCapUpdate.this.mManager.getOutputStream(CarPlayWiFiCapUpdate.this.mRfcommHandle), message.arg1)) {
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine2 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine2.transitionTo(wiFiCapUpdateStateMachine2.mIapAuthForWirelessCapabilityState);
                    } else {
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine3 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine3.transitionTo(wiFiCapUpdateStateMachine3.mReleaseRousourceState);
                    }
                    return true;
                }
                return false;
            }

            private boolean updateWirelessSocket(String mac, InputStream input, OutputStream output, int reason) {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "updateWirelessSocket, InputStream =" + input + ", OutputStream = " + output);
                if (input != null && output != null) {
                    CarPlayWiFiCapUpdate.this.mCarPlayTransport.updateWirelessSocket(input, output, mac);
                    int status = CarPlayWiFiCapUpdate.this.mCarPlayTransport.open(1);
                    if (status != -1) {
                        return true;
                    }
                    LogUtil.warning(CarPlayWiFiCapUpdate.TAG, "TRANSPORT_OPEN_FAIL");
                    WiFiCapUpdateStateMachine.this.notifyCarPlayCapbility(mac, false, 1);
                    return false;
                }
                WiFiCapUpdateStateMachine.this.notifyCarPlayCapbility(mac, false, 1);
                return false;
            }
        }

        class IapAuthForWirelessCapabilityState extends State {
            private boolean mIsWifiCap = false;

            IapAuthForWirelessCapabilityState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState enter " + CarPlayWiFiCapUpdate.this.mLinkDevice);
                CarPlayWiFiCapUpdate.this.mCurState = WiFiCapUpdateStateMachine.this.mIapAuthForWirelessCapabilityState;
                this.mIsWifiCap = false;
                CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = true;
                CarPlayIapSessionManager.getInstance().startIapSession(CarPlayWiFiCapUpdate.this.mLinkDevice, 2);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState processMessage: " + message.what);
                switch (message.what) {
                    case 0:
                        String reconnectRfcommMac = (String) message.obj;
                        if (CarPlayWiFiCapUpdate.this.mRfcommMac == null || !CarPlayWiFiCapUpdate.this.mRfcommMac.equals(reconnectRfcommMac)) {
                            if (CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult && CarPlayWiFiCapUpdate.this.mRfcommMac != null) {
                                WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = WiFiCapUpdateStateMachine.this;
                                wiFiCapUpdateStateMachine.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mRfcommMac, false, 1);
                            }
                            WiFiCapUpdateStateMachine.this.deferMessage(message);
                            WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine2 = WiFiCapUpdateStateMachine.this;
                            wiFiCapUpdateStateMachine2.transitionTo(wiFiCapUpdateStateMachine2.mReleaseRousourceState);
                            LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState MSG_RECONNECT_RFCOMM other device check cap done, next reconnect cycle");
                        } else {
                            LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState MSG_RECONNECT_RFCOMM already");
                            CarPlayWiFiCapUpdate.this.notifyRfcommComplete();
                        }
                        return true;
                    case 1:
                        break;
                    case 2:
                        CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = false;
                        String rfcommMac = (String) message.obj;
                        LogUtil.debug(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState MSG_GET_CAPBILITY, rfcommMac:" + LogEncrypt.MacAddressEncrypt(rfcommMac) + ", mRfcommMac:" + LogEncrypt.MacAddressEncrypt(CarPlayWiFiCapUpdate.this.mRfcommMac));
                        if (rfcommMac != null && CarPlayWiFiCapUpdate.this.mRfcommMac != null) {
                            if (rfcommMac.equals(CarPlayWiFiCapUpdate.this.mRfcommMac)) {
                                if (!CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult) {
                                    if (CarPlayWiFiCapUpdate.this.mManager != null) {
                                        LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState MSG_GET_CAPBILITY, notify last check result:" + this.mIsWifiCap);
                                        WiFiCapUpdateStateMachine.this.removeMessages(6);
                                        CarPlayWiFiCapUpdate.this.mManager.notifyCarPlayCapbility(rfcommMac, this.mIsWifiCap, 0, message.arg1);
                                    } else {
                                        LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState MSG_GET_CAPBILITY,  carplay manager is null!!");
                                    }
                                } else {
                                    LogUtil.info(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState, is waiting for check result");
                                    return true;
                                }
                            } else {
                                WiFiCapUpdateStateMachine.this.removeMessages(6);
                                if (CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult) {
                                    CarPlayWiFiCapUpdate.this.mManager.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mRfcommMac, this.mIsWifiCap, 0, message.arg1);
                                }
                                WiFiCapUpdateStateMachine.this.deferMessage(message);
                                WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine3 = WiFiCapUpdateStateMachine.this;
                                wiFiCapUpdateStateMachine3.transitionTo(wiFiCapUpdateStateMachine3.mReleaseRousourceState);
                            }
                        }
                        return true;
                    case 3:
                    case 6:
                        this.mIsWifiCap = false;
                        CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = false;
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine4 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine4.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mLinkDevice.getBtAddr(), false, 2);
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine5 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine5.transitionTo(wiFiCapUpdateStateMachine5.mReleaseRousourceState);
                        return true;
                    case 4:
                        if (CarPlayWiFiCapUpdate.this.mManager != null) {
                            CarPlayWiFiCapUpdate.this.mManager.onKnownWirelessAttributesChanged(CarPlayWiFiCapUpdate.this.mRfcommMac, 1);
                        }
                        this.mIsWifiCap = true;
                        CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = false;
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine6 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine6.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mLinkDevice.getBtAddr(), true, 0);
                        return true;
                    case 5:
                        if (CarPlayWiFiCapUpdate.this.mManager != null) {
                            CarPlayWiFiCapUpdate.this.mManager.onKnownWirelessAttributesChanged(CarPlayWiFiCapUpdate.this.mRfcommMac, 2);
                        }
                        this.mIsWifiCap = false;
                        CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = false;
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine7 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine7.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mLinkDevice.getBtAddr(), false, 0);
                        return true;
                    case 7:
                        this.mIsWifiCap = false;
                        if (CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult && CarPlayWiFiCapUpdate.this.mRfcommMac != null) {
                            WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine8 = WiFiCapUpdateStateMachine.this;
                            wiFiCapUpdateStateMachine8.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mRfcommMac, false, 1);
                        }
                        CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = false;
                        WiFiCapUpdateStateMachine.this.deferMessage(message);
                        WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine9 = WiFiCapUpdateStateMachine.this;
                        wiFiCapUpdateStateMachine9.transitionTo(wiFiCapUpdateStateMachine9.mReleaseRousourceState);
                        return true;
                    case 8:
                        return true;
                    case 9:
                        String mac = (String) message.obj;
                        LogUtil.debug(CarPlayWiFiCapUpdate.TAG, "IapAuthForWirelessCapabilityState MSG_RFCOMM_DISCONNECT, mac:" + LogEncrypt.MacAddressEncrypt(mac) + ", mRfcommMac:" + LogEncrypt.MacAddressEncrypt(CarPlayWiFiCapUpdate.this.mRfcommMac));
                        if (mac == null || CarPlayWiFiCapUpdate.this.mRfcommMac == null || !mac.equals(CarPlayWiFiCapUpdate.this.mRfcommMac)) {
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                this.mIsWifiCap = false;
                if (CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult && CarPlayWiFiCapUpdate.this.mRfcommMac != null) {
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine10 = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine10.notifyCarPlayCapbility(CarPlayWiFiCapUpdate.this.mRfcommMac, false, 1);
                }
                CarPlayWiFiCapUpdate.this.mIsWaitingCheckResult = false;
                WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine11 = WiFiCapUpdateStateMachine.this;
                wiFiCapUpdateStateMachine11.transitionTo(wiFiCapUpdateStateMachine11.mReleaseRousourceState);
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                if (CarPlayWiFiCapUpdate.this.mManager != null) {
                    CarPlayWiFiCapUpdate.this.mManager.onKnownWirelessAttributesChanged(CarPlayWiFiCapUpdate.this.mRfcommMac, 0);
                }
            }
        }

        class ReleaseRousourceState extends State {
            ReleaseRousourceState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "ReleaseRousourceState enter");
                CarPlayWiFiCapUpdate.this.mCurState = WiFiCapUpdateStateMachine.this.mReleaseRousourceState;
                if (CarPlayIapSessionManager.getInstance().destoryIapSession(2) == -1) {
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine.transitionTo(wiFiCapUpdateStateMachine.mIdleState);
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                LogUtil.info(CarPlayWiFiCapUpdate.TAG, "ReleaseRousourceState processMessage: " + message.what);
                int i = message.what;
                if (i == 0) {
                    WiFiCapUpdateStateMachine.this.deferMessage(message);
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine.transitionTo(wiFiCapUpdateStateMachine.mIdleState);
                    return true;
                }
                if (i == 2) {
                    WiFiCapUpdateStateMachine.this.deferMessage(message);
                    return true;
                }
                if (i == 7) {
                    WiFiCapUpdateStateMachine wiFiCapUpdateStateMachine2 = WiFiCapUpdateStateMachine.this;
                    wiFiCapUpdateStateMachine2.transitionTo(wiFiCapUpdateStateMachine2.mIdleState);
                    return true;
                }
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifyCarPlayCapbility(String rfcommMac, boolean supportCarPlay, int reason) {
            removeMessages(6);
            CarPlayWiFiCapUpdate.this.mManager.notifyCarPlayCapbility(rfcommMac, supportCarPlay, reason);
        }
    }

    public LinkDevice getLinkDevice() {
        if (this.mLinkDevice != null) {
            LogUtil.info(TAG, "getLinkDevice");
            return this.mLinkDevice;
        }
        return null;
    }

    public boolean isActived() {
        if (this.mCurState != this.mStateMachine.mIdleState) {
            return true;
        }
        return false;
    }

    public boolean onSppRetryConnected(String btAddrMac) {
        LogUtil.debug(TAG, "onSppRetryConnected btAddrMac: " + LogEncrypt.MacAddressEncrypt(btAddrMac));
        if (btAddrMac == null) {
            LogUtil.warning(TAG, "onSppRetryConnected btAddrMac is null");
            return false;
        }
        try {
            try {
                this.mLock.lock();
                this.mStateMachine.sendMessage(0, btAddrMac);
                this.mCondition.awaitNanos(TimeUnit.SECONDS.toNanos(RECONNECT_RFCOMM_TIME_OUT_SECOND));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.mLock.unlock();
            if (!btAddrMac.equals(this.mRfcommMac)) {
                LogUtil.warning(TAG, "onSppRetryConnected invalid state");
                return false;
            }
            boolean ret = -1 != this.mRfcommHandle;
            return ret;
        } catch (Throwable th) {
            this.mLock.unlock();
            throw th;
        }
    }

    public boolean closeRfSocketConnected() {
        LogUtil.debug(TAG, "closeRfSocketConnected ");
        try {
            if (this.mCurState == this.mStateMachine.mIdleState) {
                return true;
            }
            try {
                this.mDisconnectLock.lock();
                if (this.mCurState == this.mStateMachine.mIdleState) {
                    return true;
                }
                LogUtil.debug(TAG, "closeRfSocketConnected, send message");
                this.mStateMachine.sendMessage(1);
                this.mDisconnectCondition.awaitNanos(TimeUnit.SECONDS.toNanos(3L));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return true;
        } finally {
            this.mDisconnectLock.unlock();
        }
    }

    public void closeRfCommConnection() {
        LogUtil.debug(TAG, "closeRfCommConnection ");
        closeRfSocketConnected();
    }

    public boolean closeRfCommConnectionByMac(String mac) {
        String str;
        LogUtil.debug(TAG, "closeRfCommConnectionByMac, mac:" + LogEncrypt.MacAddressEncrypt(mac));
        if (mac != null && (str = this.mRfcommMac) != null && mac.equals(str)) {
            closeRfCommConnection();
            return true;
        }
        return false;
    }

    public void onRfCommDisconnected(String rfcommMac) {
        LogUtil.debug(TAG, "onRfCommDisconnected, rfcommMac:" + LogEncrypt.MacAddressEncrypt(rfcommMac));
        this.mStateMachine.sendMessage(9, rfcommMac);
    }

    public int disconnect(LinkDevice device) {
        LogUtil.debug(TAG, "disconnect device:" + device);
        closeRfSocketConnected();
        return 0;
    }

    public boolean getCarplayCapbility(LinkDevice device, int reason) {
        this.mDisconnectLock.lock();
        LogUtil.debug(TAG, "getCarplayCapbility, device:" + device + " reason:" + reason);
        if (device == null || device.getBtAddr() == null) {
            LogUtil.warning(TAG, "getCarplayCapbility device is invalid");
            this.mDisconnectLock.unlock();
            return false;
        }
        this.mLinkDevice = device;
        this.mStateMachine.sendMessage(2, reason, -1, device.getBtAddr());
        this.mDisconnectLock.unlock();
        return true;
    }

    public void iap2AuthResult(int resultCode) {
        LogUtil.info(TAG, "iap2AuthResult resultCode=" + resultCode);
        if (resultCode != 0) {
            this.mStateMachine.sendMessage(3);
        }
    }

    public void onSessionDestroy() {
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return;
        }
        try {
            try {
                this.mDisconnectLock.lock();
                if (this.mCurState == this.mStateMachine.mIdleState) {
                    return;
                }
                LogUtil.debug(TAG, "onSessionDestroy, send message");
                this.mStateMachine.sendMessage(7);
                this.mDisconnectCondition.awaitNanos(TimeUnit.SECONDS.toNanos(3L));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } finally {
            this.mDisconnectLock.unlock();
        }
    }

    public void onCarPlayCapbilityUpdate(boolean flag) {
        LogUtil.debug(TAG, "onCarPlayCapbilityUpdate, flag = " + flag);
        this.mStateMachine.sendMessage(flag ? 4 : 5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRfcommComplete() {
        try {
            this.mLock.lock();
            LogUtil.debug(TAG, "notifyRfcommComplete operation signal ");
            this.mCondition.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDisconnectComplete() {
        try {
            this.mDisconnectLock.lock();
            LogUtil.debug(TAG, "notifyDisconnectComplete operation signal ");
            this.mDisconnectCondition.signalAll();
        } finally {
            this.mDisconnectLock.unlock();
        }
    }
}
