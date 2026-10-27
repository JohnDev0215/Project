package com.ts.carplay.lib.connect;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.android.carplay.CarPlayClient;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.lib.ts.CarPlayStack;
import com.ts.carplay.linkadapter.device.data.LinkDevice;
import com.ts.carplay.linkadapter.iap.data.WiredAttributes;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayUsbConnect {
    private static final int CARLAY_MESSAGE_DELAY_100 = 50;
    private static final int CARLAY_STACK_CONNECT_TIME_OUT = 10000;
    private static final int CARLAY_STACK_WAIT_TIME = 1000;
    private static final int CARLAY_STACK_WAIT_TIME_3000 = 400;
    private static final String TAG = "CarPlayUsbConnect";
    private CarPlayClient mCarPlayClient;
    private HandlerThread mConnectThread;
    private IState mCurState;
    private LinkDevice mLinkDevice;
    private CarPlayManager mManager;
    private ConnectStateMachine mStateMachine;

    public CarPlayUsbConnect(CarPlayManager manager) {
        this.mManager = manager;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mConnectThread = handlerThread;
        handlerThread.start();
        ConnectStateMachine connectStateMachine = new ConnectStateMachine(TAG, this.mConnectThread.getLooper());
        this.mStateMachine = connectStateMachine;
        connectStateMachine.start();
    }

    private class ConnectStateMachine extends StateMachine {
        private Handler delayHandler;
        private DisconnectState mDisconnectState;
        private IapAuthingState mIapAuthingState;
        private IdleState mIdleState;
        private RoleSwitchState mRoleSwitchState;
        private boolean mScreenNeedShow;
        private SessionConnectingState mSessionConnectingState;
        private SessionEstablishedState mSessionEstablishedState;

        ConnectStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mRoleSwitchState = new RoleSwitchState();
            this.mIapAuthingState = new IapAuthingState();
            this.mSessionConnectingState = new SessionConnectingState();
            this.mSessionEstablishedState = new SessionEstablishedState();
            this.mDisconnectState = new DisconnectState();
            this.mScreenNeedShow = false;
            this.delayHandler = new Handler() { // from class: com.ts.carplay.lib.connect.CarPlayUsbConnect.ConnectStateMachine.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    if (msg.what == 0) {
                        LogUtil.info(CarPlayUsbConnect.TAG, "delay send message exitcarplay!");
                        CarPlayUsbConnect.this.mStateMachine.sendMessage(13);
                    } else if (msg.what == 1) {
                        LogUtil.info(CarPlayUsbConnect.TAG, "delay send message exitcarplay!");
                        CarPlayUsbConnect.this.mStateMachine.sendMessage(2);
                    }
                }
            };
            addState(this.mIdleState);
            addState(this.mRoleSwitchState);
            addState(this.mIapAuthingState);
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
                LogUtil.info(CarPlayUsbConnect.TAG, "IdleState enter");
                CarPlayUsbConnect.this.mCurState = ConnectStateMachine.this.mIdleState;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mRoleSwitchState);
                    return true;
                }
                if (i == 7) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mSessionConnectingState);
                    return true;
                }
                return false;
            }
        }

        class RoleSwitchState extends State {
            RoleSwitchState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayUsbConnect.TAG, "RoleSwitchState enter");
                CarPlayUsbConnect.this.mCurState = ConnectStateMachine.this.mRoleSwitchState;
                boolean ret = CarPlayUsbConnect.this.mManager.doUsbRoleSwitch(CarPlayUsbConnect.this.mLinkDevice);
                if (!ret) {
                    ConnectStateMachine.this.notifyCarPlaySessionDisconnected();
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIdleState);
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine.this.notifyCarPlaySessionDisconnected();
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIdleState);
                    return true;
                }
                if (i == 3) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mIapAuthingState);
                    return true;
                }
                if (i == 4) {
                    ConnectStateMachine.this.notifyCarPlaySessionDisconnected();
                    ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                    connectStateMachine3.transitionTo(connectStateMachine3.mIdleState);
                    return true;
                }
                return false;
            }
        }

        class IapAuthingState extends State {
            IapAuthingState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayUsbConnect.TAG, "IapAuthingState enter");
                CarPlayUsbConnect.this.mCurState = ConnectStateMachine.this.mIapAuthingState;
                ConnectStateMachine.this.startIapAuthing();
                CarPlayStack.getInstance().startCarPlayService();
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mDisconnectState);
                    return true;
                }
                if (i == 11) {
                    LogUtil.info(CarPlayUsbConnect.TAG, "SESSION_CLOSED goto disconnect");
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mDisconnectState);
                    return true;
                }
                if (i == 5) {
                    ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                    connectStateMachine3.transitionTo(connectStateMachine3.mSessionConnectingState);
                    return true;
                }
                if (i == 6) {
                    ConnectStateMachine connectStateMachine4 = ConnectStateMachine.this;
                    connectStateMachine4.transitionTo(connectStateMachine4.mDisconnectState);
                    return true;
                }
                if (i == 7) {
                    ConnectStateMachine connectStateMachine5 = ConnectStateMachine.this;
                    connectStateMachine5.transitionTo(connectStateMachine5.mSessionConnectingState);
                    return true;
                }
                return false;
            }
        }

        class SessionConnectingState extends State {
            SessionConnectingState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayUsbConnect.TAG, "SessionConnectingState enter");
                CarPlayUsbConnect.this.mCurState = ConnectStateMachine.this.mSessionConnectingState;
                ConnectStateMachine.this.startOldCarPlaySession();
                ConnectStateMachine.this.delayHandler.sendEmptyMessageDelayed(1, 10000L);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mDisconnectState);
                    return true;
                }
                if (i == 21) {
                    boolean available = ((Boolean) message.obj).booleanValue();
                    LogUtil.debug(CarPlayUsbConnect.TAG, "WIRED_ATTRIBUTES_CHANGE available:" + available);
                    if (available) {
                        CarPlayUsbConnect.this.mManager.startNewWiredCarPlaySession();
                    } else {
                        ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                        connectStateMachine2.transitionTo(connectStateMachine2.mDisconnectState);
                    }
                    return true;
                }
                if (i == 6) {
                    ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                    connectStateMachine3.transitionTo(connectStateMachine3.mDisconnectState);
                    return true;
                }
                if (i == 7) {
                    ConnectStateMachine.this.startOldCarPlaySession();
                    return true;
                }
                switch (i) {
                    case 9:
                        ConnectStateMachine.this.mScreenNeedShow = ((Boolean) message.obj).booleanValue();
                        ConnectStateMachine connectStateMachine4 = ConnectStateMachine.this;
                        connectStateMachine4.transitionTo(connectStateMachine4.mSessionEstablishedState);
                        break;
                    case 10:
                        ConnectStateMachine.this.delayHandler.removeMessages(1);
                        ConnectStateMachine.this.notifyCarPlaySessionConnectFailed();
                        ConnectStateMachine connectStateMachine5 = ConnectStateMachine.this;
                        connectStateMachine5.transitionTo(connectStateMachine5.mIdleState);
                        break;
                    case 11:
                        LogUtil.info(CarPlayUsbConnect.TAG, "SESSION_CLOSED goto disconnect");
                        ConnectStateMachine connectStateMachine6 = ConnectStateMachine.this;
                        connectStateMachine6.transitionTo(connectStateMachine6.mDisconnectState);
                        break;
                }
                return true;
            }
        }

        class SessionEstablishedState extends State {
            SessionEstablishedState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayUsbConnect.TAG, "SessionEstablishedState enter, mScreenNeedShow=" + ConnectStateMachine.this.mScreenNeedShow);
                CarPlayUsbConnect.this.mCurState = ConnectStateMachine.this.mSessionEstablishedState;
                ConnectStateMachine.this.delayHandler.removeMessages(1);
                ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                connectStateMachine.notifyCarPlaySessionConnected(connectStateMachine.mScreenNeedShow);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 2) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mDisconnectState);
                    return true;
                }
                if (i == 11) {
                    LogUtil.info(CarPlayUsbConnect.TAG, "SESSION_CLOSED goto disconnect");
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mDisconnectState);
                    return true;
                }
                if (i == 6) {
                    ConnectStateMachine connectStateMachine3 = ConnectStateMachine.this;
                    connectStateMachine3.transitionTo(connectStateMachine3.mDisconnectState);
                    return true;
                }
                if (i == 7 || i == 8) {
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
                LogUtil.info(CarPlayUsbConnect.TAG, "DisconnectState enter");
                CarPlayUsbConnect.this.mCurState = ConnectStateMachine.this.mDisconnectState;
                ConnectStateMachine.this.notifyCarPlaySessionDisconnecting();
                ConnectStateMachine.this.delayHandler.removeMessages(1);
                CarPlayUsbConnect.this.mManager.getInnerEventHandler().onSessionDestoryed(CarPlayUsbConnect.this.mLinkDevice);
                if (CarPlayStack.getInstance().getCarPlayClient() == null) {
                    ConnectStateMachine.this.delayHandler.sendEmptyMessageDelayed(0, 50L);
                } else {
                    CarPlayStack.getInstance().stopCarplayClient();
                }
                CarPlayIapSessionManager.getInstance().destoryIapSession(1);
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 11) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIdleState);
                    return true;
                }
                if (i == 13) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mIdleState);
                    return true;
                }
                return false;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                LogUtil.info(CarPlayUsbConnect.TAG, "exit disconnect ");
                ConnectStateMachine.this.notifyCarPlaySessionDisconnected();
            }
        }

        public void carPlayStop() {
            CarPlayStack.getInstance().carPlayStop();
        }

        public void notifyCarPlaySessionConnectFailed() {
            try {
                CarPlayUsbConnect.this.mManager.onSessionEstablishFailed(CarPlayUsbConnect.this.mLinkDevice);
                CarPlayUsbConnect.this.iap2AuthFailToIdle(CarPlayUsbConnect.this.mLinkDevice);
            } catch (Exception e) {
                LogUtil.info(CarPlayUsbConnect.TAG, "notifyCarPlaySessionConnectFailed Exception");
            }
        }

        public void notifyCarPlaySessionConnected(boolean needShow) {
            LogUtil.info(CarPlayUsbConnect.TAG, "notifyCarPlaySessionConnected needShow is " + needShow);
            CarPlayUsbConnect.this.mManager.onSessionEstablished(CarPlayUsbConnect.this.mLinkDevice);
            if (needShow) {
                CarPlayUsbConnect.this.mManager.requestVideoFocus();
            } else if (true == CarPlayUsbConnect.this.mManager.getIsFromUserSts()) {
                CarPlayUsbConnect.this.mManager.requestVideoFocus();
                needShow = true;
            }
            CarPlayUsbConnect.this.mManager.getInnerEventHandler().onSessionEstablished(CarPlayUsbConnect.this.mLinkDevice);
            if (!needShow && CarPlayUsbConnect.this.mManager.getInBackcameraFlag()) {
                CarPlayUsbConnect.this.mManager.setNeedShowCpIconSts(true);
            } else if (!needShow) {
                CarPlayUsbConnect.this.mManager.setCpShowPopup();
            }
        }

        public void notifyCarPlaySessionDisconnecting() {
            CarPlayUsbConnect.this.mManager.onCarPlayDeviceStatusChanged(CarPlayUsbConnect.this.mLinkDevice, 3);
        }

        public void notifyCarPlaySessionDisconnected() {
            CarPlayUsbConnect.this.mManager.notifyCarPlaySessionDisconnected(CarPlayUsbConnect.this.mLinkDevice);
        }

        public void startIapAuthing() {
            LogUtil.info(CarPlayUsbConnect.TAG, "startIapAuthing!");
            CarPlayIapSessionManager.getInstance().destoryIapSession(1);
            CarPlayIapSessionManager.getInstance().startIapSession(CarPlayUsbConnect.this.mLinkDevice, 1);
        }

        public void startOldCarPlaySession() {
            LogUtil.info(CarPlayUsbConnect.TAG, "startOldCarPlaySession!");
            CarPlayClient tmpclient = CarPlayStack.getInstance().getCarPlayClientByBtAddr(null);
            if (tmpclient != null) {
                LogUtil.info(CarPlayUsbConnect.TAG, "carplayclient has been connected !");
                CarPlayStack.getInstance().startCarPlaySession(tmpclient);
            }
        }
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

    public int connect(LinkDevice device) {
        LogUtil.info(TAG, "connect, currentState=" + this.mCurState.getName());
        this.mLinkDevice = device;
        CarPlayClient tmpclient = CarPlayStack.getInstance().getCarPlayClientByBtAddr(null);
        if (tmpclient != null && CarPlayConnect.getInstance().getIapGetNameFlg()) {
            LogUtil.info(TAG, "carplayclient has been connected !");
            this.mStateMachine.sendMessage(7);
            return 0;
        }
        this.mStateMachine.sendMessage(1);
        return 0;
    }

    public int iap2AuthFailToIdle(LinkDevice device) {
        LogUtil.info(TAG, "iap2AuthFailToIdle, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return -1;
        }
        this.mStateMachine.sendMessage(2);
        return 0;
    }

    public int disconnect(LinkDevice device) {
        LogUtil.info(TAG, "disconnect, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return -1;
        }
        this.mLinkDevice = device;
        this.mStateMachine.sendMessage(2);
        return 0;
    }

    public LinkDevice getLinkDevice() {
        if (this.mLinkDevice != null) {
            LogUtil.info(TAG, "getLinkDevice");
            return this.mLinkDevice;
        }
        return null;
    }

    public void iap2AuthResult(int resultCode) {
        LogUtil.info(TAG, "iap2AuthResult resultCode=" + resultCode);
        if (resultCode == 0) {
            this.mStateMachine.sendMessage(5);
        } else {
            this.mStateMachine.sendMessage(6);
        }
    }

    public void onRoleSwitchFinished(LinkDevice device, boolean success) {
        LogUtil.info(TAG, "onRoleSwitchFinished success=" + success);
        this.mLinkDevice = device;
        if (success) {
            this.mStateMachine.sendMessage(3);
        } else {
            this.mStateMachine.sendMessage(4);
        }
    }

    public void onSessionEstablished(boolean needShow) {
        LogUtil.info(TAG, "onSessionEstablished needShow " + needShow);
        this.mStateMachine.sendMessage(9, Boolean.valueOf(needShow));
    }

    public void onSessionEstablishFailed() {
        LogUtil.info(TAG, "onSessionEstablishFailed");
        this.mStateMachine.sendMessage(10);
    }

    public void onSessionClosed() {
        LogUtil.info(TAG, "onSessionClosed");
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return;
        }
        this.mStateMachine.sendMessage(11);
    }

    public void onUsbClientConnected(CarPlayClient client) {
        LogUtil.info(TAG, "onUsbClientConnected" + client);
        this.mCarPlayClient = client;
        LogUtil.info(TAG, "onUsbClientConnected" + client.mType);
        if (client.mType == 1) {
            this.mStateMachine.sendMessage(7);
        }
    }

    public void onWiredAttributesChanged(WiredAttributes wired) {
        if (wired != null && wired.isWiredValid()) {
            this.mStateMachine.sendMessage(21, Boolean.valueOf(wired.isWiredAvailable()));
        }
    }
}
