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
public class CarPlayUsbDeviceConnect {
    private static final int CARLAY_MESSAGE_DELAY_100 = 150;
    private static final String TAG = "CarPlayUsbDeviceConnect";
    private HandlerThread mConnectThread;
    private IState mCurState;
    private int mIapSessionType = 0;
    private LinkDevice mLinkDevice;
    private CarPlayManager mManager;
    private ConnectStateMachine mStateMachine;

    public CarPlayUsbDeviceConnect(CarPlayManager manager) {
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

        ConnectStateMachine(String name, Looper looper) {
            super(name, looper);
            this.mIdleState = new IdleState();
            this.mIapAuthingState = new IapAuthingState();
            this.mDisconnectState = new DisconnectState();
            this.delayHandler = new Handler() { // from class: com.ts.carplay.lib.connect.CarPlayUsbDeviceConnect.ConnectStateMachine.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    if (msg.what == 0) {
                        LogUtil.info(CarPlayUsbDeviceConnect.TAG, "delay send message exitcarplay!");
                        CarPlayUsbDeviceConnect.this.mStateMachine.sendMessage(13);
                    }
                }
            };
            addState(this.mIdleState);
            addState(this.mIapAuthingState);
            addState(this.mDisconnectState);
            setInitialState(this.mIdleState);
        }

        class IdleState extends State {
            IdleState() {
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public void enter() {
                LogUtil.info(CarPlayUsbDeviceConnect.TAG, "IdleState enter");
                CarPlayUsbDeviceConnect.this.mCurState = ConnectStateMachine.this.mIdleState;
            }

            @Override // com.ts.carplay.lib.common.utils.State, com.ts.carplay.lib.common.utils.IState
            public boolean processMessage(Message message) {
                if (message.what == 1) {
                    ConnectStateMachine connectStateMachine = ConnectStateMachine.this;
                    connectStateMachine.transitionTo(connectStateMachine.mIapAuthingState);
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
                LogUtil.info(CarPlayUsbDeviceConnect.TAG, "IapAuthingState enter");
                CarPlayUsbDeviceConnect.this.mCurState = ConnectStateMachine.this.mIapAuthingState;
                CarPlayUsbDeviceConnect.this.startIapAuthing();
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
                LogUtil.info(CarPlayUsbDeviceConnect.TAG, "DisconnectState enter");
                CarPlayUsbDeviceConnect.this.mCurState = ConnectStateMachine.this.mDisconnectState;
                CarPlayIapSessionManager.getInstance().destoryIapSession(64);
                if (CarPlayUsbDeviceConnect.this.mManager != null) {
                    CarPlayUsbDeviceConnect.this.mManager.setInterfaceName("usb0");
                    CarPlayUsbDeviceConnect.this.mManager.setInterfaceRoute(CarPlayManager.INTERFACE_ACTION_DOWN);
                }
                ConnectStateMachine.this.delayHandler.sendEmptyMessageDelayed(0, 150L);
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
                LogUtil.info(CarPlayUsbDeviceConnect.TAG, "exit disconnect ");
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
        this.mIapSessionType = 64;
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

    public int disconnectIapUsbDevice() {
        LogUtil.info(TAG, "disconnectIapUsbDevice, currentState=" + this.mCurState.getName());
        if (this.mCurState == this.mStateMachine.mIdleState) {
            return -1;
        }
        if (this.mIapSessionType == 64) {
            LogUtil.info(TAG, "disconnectIapUsbDevice--");
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

    public void startIapAuthing() {
        LogUtil.info(TAG, "startIapAuthing!");
        CarPlayIapSessionManager.getInstance().destoryIapSession(64);
        CarPlayIapSessionManager.getInstance().startIapSession(this.mLinkDevice, this.mIapSessionType);
    }

    public void iap2AuthResult(int resultCode) {
        LogUtil.info(TAG, "iap2AuthResult resultCode=" + resultCode);
        if (resultCode == 0) {
            this.mStateMachine.sendMessage(5);
        } else {
            this.mStateMachine.sendMessage(6);
        }
    }
}
