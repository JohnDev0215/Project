package com.ts.carplay.lib.connect;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.ts.android.carplay.CarPlayClient;
import com.ts.carplay.common.util.LogEncrypt;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.lib.transport.CarPlayTransport;
import com.ts.carplay.lib.ts.CarPlayStack;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import com.ts.carplay.linkadapter.iap.data.WirelessAttributes;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayWiFiConnect {
    private static final int CARLAY_MESSAGE_DELAY_100 = 150;
    private static final int CARLAY_STACK_CONNECT_TIME_OUT = 65000;
    private static final int MESSAGEDELAY_100 = 100;
    private static final int RFCOMM_SOCKET_SLEEP_1000 = 1000;
    private static final String TAG = "CarPlayWiFiConnect";
    private static final int TRANSPORT_OPEN_FAIL = -1;
    private CarPlayClient mCarPlayClient;
    private CarPlayWiFiCapUpdate mCarPlayWiFiCapUpdate;
    private HandlerThread mConnectThread;
    private IState mCurState;
    private LinkDevice mLinkDevice;
    private CarPlayManager mManager;
    private String mSerialNumber;
    private ConnectStateMachine mStateMachine;
    private int mBtSessionHandle = -1;
    private CarPlayTransport mCarPlayTransport = CarPlayTransport.getInstance();

    public CarPlayWiFiConnect(CarPlayManager manager, CarPlayWiFiCapUpdate update) {
        this.mManager = manager;
        this.mCarPlayWiFiCapUpdate = update;
        HandlerThread handlerThread = new HandlerThread("CarPlayWifiConnect");
        this.mConnectThread = handlerThread;
        handlerThread.start();
        ConnectStateMachine connectStateMachine = new ConnectStateMachine(TAG, this.mConnectThread.getLooper());
        this.mStateMachine = connectStateMachine;
        connectStateMachine.start();
    }

    public LinkDevice getLinkDevice() {
        if (this.mLinkDevice != null) {
            LogUtil.info(TAG, "getLinkDevice");
            return this.mLinkDevice;
        }
        return null;
    }

    private class ConnectStateMachine extends StateMachine {
        private Handler delayHandler;
        private DisconnectState mDisconnectState;
        private IdleState mIdleState;
        private boolean mScreenNeedShow;
        private SessionConnectingState mSessionConnectingState;
        private SessionEstablishedState mSessionEstablishedState;

        ConnectStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mSessionConnectingState = new SessionConnectingState();
            this.mSessionEstablishedState = new SessionEstablishedState();
            this.mDisconnectState = new DisconnectState();
            this.mScreenNeedShow = false;
            this.delayHandler = new Handler() { // from class: com.ts.carplay.lib.connect.CarPlayWiFiConnect.ConnectStateMachine.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    if (msg.what == 0) {
                        LogUtil.info(CarPlayWiFiConnect.TAG, "delay send message exitcarplay!");
                        CarPlayWiFiConnect.this.mStateMachine.sendMessage(13);
                    } else if (msg.what == 1) {
                        LogUtil.info(CarPlayWiFiConnect.TAG, "delay send message exitcarplay!");
                        CarPlayWiFiConnect.this.mStateMachine.sendMessage(2);
                    }
                }
            };
            addState(this.mIdleState);
            addState(this.mSessionConnectingState);
            addState(this.mSessionEstablishedState);
            addState(this.mDisconnectState);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiConnect.TAG, "IdleState enter");
                CarPlayWiFiConnect.this.mCurState = ConnectStateMachine.this.mIdleState;
                if (CarPlayStack.getInstance().getCarPlayClient() != null) {
                    CarPlayStack.getInstance().stopCarplayClient();
                }
                if (CarPlayWiFiConnect.this.mManager != null) {
                    CarPlayWiFiConnect.this.mManager.setIsConnectByUser(false);
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mDisconnectState);
                    return true;
                }
                if (i == 20) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mSessionConnectingState);
                    return true;
                }
                if (i == 23) {
                    ConnectStateMachine.this.deferMessage(message);
                    return true;
                }
                return false;
            }
        }

        class SessionConnectingState extends State {
            private boolean mIsCarPlayStartSessionSucc = false;

            SessionConnectingState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiConnect.TAG, "SessionConnectingState enter");
                CarPlayWiFiConnect.this.mCurState = ConnectStateMachine.this.mSessionConnectingState;
                ConnectStateMachine.this.delayHandler.sendEmptyMessageDelayed(1, 65000L);
                CarPlayWiFiConnect.this.mManager.setIsConnectByUser(true);
                if (CarPlayWiFiConnect.this.mManager.getIsCarPlayConnection()) {
                    this.mIsCarPlayStartSessionSucc = CarPlayWiFiConnect.this.mManager.startNewWirelessCarPlaySession();
                    return;
                }
                CarPlayStack.getInstance().startCarPlayService();
                CarPlayClient tmpclient = CarPlayStack.getInstance().getCarPlayClientByBtAddr(CarPlayWiFiConnect.this.mLinkDevice.getBtAddr());
                if (tmpclient != null) {
                    LogUtil.info(CarPlayWiFiConnect.TAG, "carplayclient has been connected !");
                    CarPlayStack.getInstance().startCarPlaySession(tmpclient);
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mDisconnectState);
                    return true;
                }
                if (i == 7) {
                    ConnectStateMachine.this.startCarPlaySession();
                    return true;
                }
                switch (i) {
                    case 9:
                        ConnectStateMachine.this.mScreenNeedShow = ((Boolean) message.obj).booleanValue();
                        ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                        connectStateMachine2.transitionTo(connectStateMachine2.mSessionEstablishedState);
                        break;
                    case 10:
                        ConnectStateMachine.this.notifyCarPlaySessionConnectFailed();
                        ConnectStateMachine.this.delayHandler.removeMessages(1);
                        ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                        connectStateMachine3.transitionTo(connectStateMachine3.mIdleState);
                        break;
                    case 11:
                        ConnectStateMachine connectStateMachine4 = ConnectStateMachine.this;
                        connectStateMachine4.transitionTo(connectStateMachine4.mDisconnectState);
                        break;
                    default:
                        switch (i) {
                            case 22:
                                WirelessAttributes attributes = (WirelessAttributes) message.obj;
                                if (!TextUtils.equals(attributes.getBluetoothTransportIdentifier(), CarPlayWiFiConnect.this.mLinkDevice.getDeviceId())) {
                                    LogUtil.debug(CarPlayWiFiConnect.TAG, "different device linkDevice:" + CarPlayWiFiConnect.this.mLinkDevice.getDeviceId() + ", new device:" + attributes.getBluetoothTransportIdentifier());
                                } else {
                                    boolean available = attributes.isWirelessAvailable();
                                    LogUtil.debug(CarPlayWiFiConnect.TAG, "WIRELESS_ATTRIBUTES_CHANGE available:" + available);
                                    if (available) {
                                        this.mIsCarPlayStartSessionSucc = CarPlayWiFiConnect.this.mManager.startNewWirelessCarPlaySession();
                                    } else {
                                        ConnectStateMachine connectStateMachine5 = ConnectStateMachine.this;
                                        connectStateMachine5.transitionTo(connectStateMachine5.mDisconnectState);
                                    }
                                }
                                break;
                            case 23:
                                CarPlayIapSessionManager.getInstance().iapReqWifiInfo();
                                break;
                            case 24:
                                boolean retVal = this.mIsCarPlayStartSessionSucc;
                                if (!retVal) {
                                    LogUtil.error(CarPlayWiFiConnect.TAG, "CHECK_CARPLAY_START_SESSION");
                                    this.mIsCarPlayStartSessionSucc = CarPlayWiFiConnect.this.mManager.startNewWirelessCarPlaySession();
                                }
                                break;
                        }
                        break;
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                this.mIsCarPlayStartSessionSucc = false;
            }
        }

        class SessionEstablishedState extends State {
            SessionEstablishedState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiConnect.TAG, "SessionEstablishedState enter");
                CarPlayWiFiConnect.this.mCurState = ConnectStateMachine.this.mSessionEstablishedState;
                ConnectStateMachine.this.delayHandler.removeMessages(1);
                if (CarPlayWiFiConnect.this.mManager.getIsCarPlayConnection()) {
                    ConnectStateMachine.this.startCarPlaySession();
                }
                ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                connectStateMachine.notifyCarPlaySessionConnected(connectStateMachine.mScreenNeedShow);
                ConnectStateMachine.this.createIapSession(4);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mDisconnectState);
                    return true;
                }
                if (i == 5) {
                    return false;
                }
                if (i == 6) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mDisconnectState);
                    return false;
                }
                if (i == 7 || i == 8) {
                    return true;
                }
                if (i == 11) {
                    ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                    connectStateMachine3.transitionTo(connectStateMachine3.mDisconnectState);
                    return true;
                }
                if (i == 12) {
                    if (CarPlayWiFiConnect.this.mLinkDevice != null) {
                        CarPlayIapSessionManager.getInstance().destoryIapSession(2);
                        if (CarPlayWiFiConnect.this.mCarPlayWiFiCapUpdate != null) {
                            CarPlayWiFiConnect.this.mCarPlayWiFiCapUpdate.closeRfCommConnection();
                        }
                        CarPlayWiFiConnect.this.mCarPlayTransport.close(1);
                    }
                    return true;
                }
                return false;
            }
        }

        class DisconnectState extends State {
            DisconnectState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayWiFiConnect.TAG, "DisconnectState enter");
                CarPlayWiFiConnect.this.mCurState = ConnectStateMachine.this.mDisconnectState;
                ConnectStateMachine.this.notifyCarPlaySessionDisconnecting();
                ConnectStateMachine.this.delayHandler.removeMessages(1);
                CarPlayWiFiConnect.this.mManager.getInnerEventHandler().onSessionDestoryed(CarPlayWiFiConnect.this.mLinkDevice);
                if (CarPlayStack.getInstance().getCarPlayClient() == null) {
                    ConnectStateMachine.this.delayHandler.sendEmptyMessageDelayed(0, 150L);
                } else {
                    CarPlayStack.getInstance().stopCarplayClient();
                }
                CarPlayIapSessionManager.getInstance().destoryIapSession(2);
                CarPlayIapSessionManager.getInstance().destoryIapSession(4);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIdleState);
                    return true;
                }
                if (i == 11) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mIdleState);
                    return true;
                }
                if (i == 13) {
                    ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                    connectStateMachine3.transitionTo(connectStateMachine3.mIdleState);
                    return true;
                }
                return true;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                ConnectStateMachine.this.notifyCarPlaySessionDisconnected();
            }
        }

        public void carPlayStop() {
            CarPlayStack.getInstance().carPlayStop();
        }

        public void notifyCarPlaySessionConnectFailed() {
            CarPlayWiFiConnect.this.mManager.onSessionEstablishFailed(CarPlayWiFiConnect.this.mLinkDevice);
        }

        public void notifyCarPlaySessionConnected(boolean needShow) {
            LogUtil.info(CarPlayWiFiConnect.TAG, "notifyCarPlaySessionConnected, needShow is " + needShow);
            CarPlayWiFiConnect.this.mManager.onSessionEstablished(CarPlayWiFiConnect.this.mLinkDevice);
            if (needShow) {
                CarPlayWiFiConnect.this.mManager.requestVideoFocus();
            } else if (true == CarPlayWiFiConnect.this.mManager.getIsFromUserSts()) {
                CarPlayWiFiConnect.this.mManager.requestVideoFocus();
                needShow = true;
            }
            CarPlayWiFiConnect.this.mManager.getInnerEventHandler().onSessionEstablished(CarPlayWiFiConnect.this.mLinkDevice);
            if (!needShow && CarPlayWiFiConnect.this.mManager.getInBackcameraFlag()) {
                CarPlayWiFiConnect.this.mManager.setNeedShowCpIconSts(true);
            } else if (!needShow) {
                CarPlayWiFiConnect.this.mManager.setCpShowPopup();
            }
        }

        public void notifyCarPlaySessionDisconnecting() {
            CarPlayWiFiConnect.this.mManager.onCarPlayDeviceStatusChanged(CarPlayWiFiConnect.this.mLinkDevice, 3);
        }

        public void notifyCarPlaySessionDisconnected() {
            CarPlayWiFiConnect.this.mManager.notifyCarPlaySessionDisconnected(CarPlayWiFiConnect.this.mLinkDevice);
        }

        public void createIapSession(int type) {
            LogUtil.info(CarPlayWiFiConnect.TAG, "createIapSession, iapsession  create begin!");
            CarPlayIapSessionManager.getInstance().startIapSession(CarPlayWiFiConnect.this.mLinkDevice, type);
        }

        public void startCarPlaySession() {
            LogUtil.info(CarPlayWiFiConnect.TAG, "startCarPlaySession!");
            CarPlayClient tmpclient = CarPlayStack.getInstance().getCarPlayClientByBtAddr(CarPlayWiFiConnect.this.mLinkDevice.getBtAddr());
            LogUtil.debug(CarPlayWiFiConnect.TAG, "startCarPlaySession!addr=====" + LogEncrypt.MacAddressEncrypt(CarPlayWiFiConnect.this.mLinkDevice.getBtAddr()));
            if (tmpclient != null) {
                LogUtil.info(CarPlayWiFiConnect.TAG, "carplayclient has been connected !");
                CarPlayStack.getInstance().startCarPlaySession(tmpclient);
            }
        }

        public void cancelSendWifiConfigMessage() {
            removeDeferredMessages(23);
            removeMessages(23);
        }
    }

    public void checkCarPlayStartSession() {
        this.mStateMachine.sendMessage(24);
    }

    public boolean isHasWirelessConn() {
        if (this.mCurState == this.mStateMachine.mSessionConnectingState || this.mCurState == this.mStateMachine.mSessionEstablishedState) {
            return true;
        }
        return false;
    }

    public boolean isActived() {
        if (this.mCurState != this.mStateMachine.mIdleState) {
            return true;
        }
        return false;
    }

    public boolean isConnected() {
        LogUtil.verbose(TAG, "isConnected, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mSessionEstablishedState) {
            return true;
        }
        return false;
    }

    public boolean isWirelessHandle(int handle) {
        LogUtil.info(TAG, "isWirelessHandle, handle=" + handle + "mBtSessionHandle" + this.mBtSessionHandle);
        if (handle == this.mBtSessionHandle) {
            return true;
        }
        return false;
    }

    public int connect_with_no_iap(LinkDevice device) {
        LogUtil.info(TAG, "connect_with_no_iap, trigger device =" + device);
        this.mLinkDevice = device;
        this.mStateMachine.sendMessage(20);
        return 0;
    }

    public int disconnect(LinkDevice device) {
        LogUtil.info(TAG, "disconnect, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return -1;
        }
        this.mStateMachine.sendMessage(2);
        return 0;
    }

    public void updateSerialNumber(String serialNumber) {
        this.mSerialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public void iap2AuthResult(int resultCode) {
        LogUtil.info(TAG, "iap2AuthResult resultCode=" + resultCode);
        if (resultCode == 0) {
            this.mStateMachine.sendMessage(5);
        } else {
            this.mStateMachine.sendMessage(6);
        }
    }

    public void onSessionEstablished(boolean needShow) {
        LogUtil.info(TAG, "onSessionEstablished");
        this.mStateMachine.sendMessage(9, Boolean.valueOf(needShow));
        this.mCarPlayTransport.open(2);
    }

    public void onSessionEstablishFailed() {
        LogUtil.info(TAG, "onSessionEstablishFailed");
        this.mCarPlayTransport.close(1);
        CarPlayWiFiCapUpdate carPlayWiFiCapUpdate = this.mCarPlayWiFiCapUpdate;
        if (carPlayWiFiCapUpdate != null) {
            carPlayWiFiCapUpdate.closeRfCommConnection();
        }
        this.mStateMachine.sendMessage(10);
    }

    public void onSessionClosed() {
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return;
        }
        LogUtil.info(TAG, "onSessionClosed ");
        this.mCarPlayTransport.close(2);
        this.mCarPlayTransport.close(1);
        CarPlayWiFiCapUpdate carPlayWiFiCapUpdate = this.mCarPlayWiFiCapUpdate;
        if (carPlayWiFiCapUpdate != null) {
            carPlayWiFiCapUpdate.closeRfCommConnection();
        }
        this.mStateMachine.sendMessage(11);
        LogUtil.info(TAG, "onSessionClosed end ");
    }

    public void onWifiClientConnected(CarPlayClient client) {
        LogUtil.info(TAG, "onWifiClientConnected" + client);
        this.mCarPlayClient = client;
        this.mStateMachine.sendMessage(7);
    }

    public void onCarPlayCapbilityUpdate(boolean flag) {
        LogUtil.info(TAG, "onCarPlayCapbilityUpdate, flag = " + flag);
        if (!flag) {
            this.mStateMachine.sendMessage(19);
        } else {
            this.mStateMachine.sendMessage(18);
        }
    }

    public void disableBluetooth() {
        LogUtil.info(TAG, "disableBluetooth");
        this.mStateMachine.sendMessage(12);
    }

    public void onWirelessAttributesChanged(WirelessAttributes wireless) {
        if (wireless != null && wireless.isWirelessValid()) {
            this.mStateMachine.sendMessage(22, wireless);
        }
    }

    public void onRequestAccessoryWiFiConfigurationInformation() {
        this.mStateMachine.sendMessage(23);
    }

    public void onSessionDestroy() {
        this.mStateMachine.cancelSendWifiConfigMessage();
    }
}
