package com.ts.carplay.lib.connect;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.utils.IState;
import com.ts.carplay.lib.common.utils.State;
import com.ts.carplay.lib.common.utils.StateMachine;
import com.ts.carplay.linkadapter.device.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public class CarPlaySameDevConnect {
    private static final int CARLAY_MESSAGE_DELAY_100 = 150;
    private static final String TAG = "CarPlaySameDevConnect";
    private HandlerThread mConnectThread;
    private IState mCurState;
    private int mIapSessionType = 0;
    private LinkDevice mLinkDevice;
    private CarPlayManager mManager;
    private ConnectStateMachine mStateMachine;

    public CarPlaySameDevConnect(CarPlayManager manager) {
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

        ConnectStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mRoleSwitchState = new RoleSwitchState();
            this.mIapAuthingState = new IapAuthingState();
            this.mDisconnectState = new DisconnectState();
            this.delayHandler = new Handler() { // from class: com.ts.carplay.lib.connect.CarPlaySameDevConnect.ConnectStateMachine.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    if (msg.what == 0) {
                        LogUtil.info(CarPlaySameDevConnect.TAG, "delay send message exitcarplay!");
                        CarPlaySameDevConnect.this.mStateMachine.sendMessage(13);
                    }
                }
            };
            addState(this.mIdleState);
            addState(this.mRoleSwitchState);
            addState(this.mIapAuthingState);
            addState(this.mDisconnectState);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlaySameDevConnect.TAG, "IdleState enter");
                CarPlaySameDevConnect.this.mCurState = ConnectStateMachine.this.mIdleState;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                if (message.what == 1) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mRoleSwitchState);
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
                LogUtil.info(CarPlaySameDevConnect.TAG, "RoleSwitchState enter");
                CarPlaySameDevConnect.this.mCurState = ConnectStateMachine.this.mRoleSwitchState;
                if (CarPlaySameDevConnect.this.mIapSessionType != 32) {
                    boolean ret = CarPlaySameDevConnect.this.mManager.doUsbRoleSwitch(CarPlaySameDevConnect.this.mLinkDevice);
                    if (!ret) {
                        ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                        connectStateMachine.transitionTo(connectStateMachine.mIdleState);
                        return;
                    }
                    return;
                }
                boolean ret2 = CarPlaySameDevConnect.this.mManager.doUsbRoleSwitchForIap(CarPlaySameDevConnect.this.mLinkDevice);
                if (!ret2) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mIdleState);
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                int i = message.what;
                if (i == 3) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIapAuthingState);
                    return true;
                }
                if (i == 4) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mIdleState);
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
                LogUtil.info(CarPlaySameDevConnect.TAG, "IapAuthingState enter");
                CarPlaySameDevConnect.this.mCurState = ConnectStateMachine.this.mIapAuthingState;
                CarPlaySameDevConnect.this.startIapAuthing();
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
                    return true;
                }
                if (i == 6) {
                    ConnectStateMachine connectStateMachine2 = ConnectStateMachine.this;
                    connectStateMachine2.transitionTo(connectStateMachine2.mDisconnectState);
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
                LogUtil.info(CarPlaySameDevConnect.TAG, "DisconnectState enter");
                CarPlaySameDevConnect.this.mCurState = ConnectStateMachine.this.mDisconnectState;
                CarPlayIapSessionManager.getInstance().destoryIapSession(1);
                if (CarPlaySameDevConnect.this.mManager != null) {
                    CarPlaySameDevConnect.this.mManager.setInterfaceName("usb0");
                    CarPlaySameDevConnect.this.mManager.setInterfaceRoute(CarPlayManager.INTERFACE_ACTION_DOWN);
                }
                ConnectStateMachine.this.delayHandler.sendEmptyMessageDelayed(0, 150L);
                if (CarPlaySameDevConnect.this.mIapSessionType == 32) {
                    CarPlaySameDevConnect.this.mManager.onUpdateIapSessionState(CarPlaySameDevConnect.this.mLinkDevice.getUsbSerialNumber(), 2, 0);
                    LogUtil.info(CarPlaySameDevConnect.TAG, "DisconnectState enter :" + CarPlaySameDevConnect.this.mLinkDevice.getUsbSerialNumber());
                    CarPlaySameDevConnect.this.mIapSessionType = 8;
                }
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                if (message.what == 13) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIdleState);
                    return true;
                }
                return false;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void exit() {
                LogUtil.info(CarPlaySameDevConnect.TAG, "exit disconnect ");
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
        LogUtil.info(TAG, "isConnected, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIapAuthingState) {
            return true;
        }
        return false;
    }

    public int connect(LinkDevice device) {
        LogUtil.info(TAG, "connect, currentState=" + this.mCurState.getName());
        if (this.mCurState != this.mStateMachine.mIdleState) {
            return -1;
        }
        this.mIapSessionType = 8;
        this.mLinkDevice = device;
        this.mStateMachine.sendMessage(1);
        return 0;
    }

    public int checkUsbDeviceName(LinkDevice device) {
        LogUtil.info(TAG, "connect, currentState=" + this.mCurState.getName());
        if (this.mCurState != this.mStateMachine.mIdleState) {
            return -1;
        }
        this.mIapSessionType = 32;
        this.mLinkDevice = device;
        this.mStateMachine.sendMessage(1);
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

    public int disconnectIapSameDevice() {
        LogUtil.info(TAG, "disconnectIapSameDevice, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return -1;
        }
        if (this.mIapSessionType == 8) {
            LogUtil.info(TAG, "disconnectIapSameDevice--");
            this.mStateMachine.sendMessage(2);
            return 0;
        }
        return 0;
    }

    public int disconnectIap() {
        LogUtil.info(TAG, "disconnectIap, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return -1;
        }
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

    public void onRoleSwitchFinished(LinkDevice device, boolean success) {
        LogUtil.info(TAG, "onRoleSwitchFinished success=" + success);
        this.mLinkDevice = device;
        if (success) {
            this.mStateMachine.sendMessage(3);
            return;
        }
        this.mStateMachine.sendMessage(4);
        if (this.mIapSessionType == 32) {
            this.mManager.onUpdateIapSessionState(this.mLinkDevice.getUsbSerialNumber(), 1, 2);
        }
    }

    public void startIapAuthing() {
        LogUtil.info(TAG, "startIapAuthing!");
        CarPlayIapSessionManager.getInstance().destoryIapSession(1);
        CarPlayIapSessionManager.getInstance().startIapSession(this.mLinkDevice, this.mIapSessionType);
    }

    public void iap2AuthResult(int resultCode) {
        LogUtil.info(TAG, "iap2AuthResult resultCode=" + resultCode);
        if (resultCode == 0) {
            this.mStateMachine.sendMessage(5);
            if (this.mIapSessionType == 32) {
                this.mManager.onUpdateIapSessionState(this.mLinkDevice.getUsbSerialNumber(), 1, 0);
                return;
            }
            return;
        }
        this.mStateMachine.sendMessage(6);
        if (this.mIapSessionType == 32) {
            this.mManager.onUpdateIapSessionState(this.mLinkDevice.getUsbSerialNumber(), 1, 2);
        }
    }

    public boolean getIapGetNameFlg() {
        return this.mIapSessionType == 32;
    }
}
