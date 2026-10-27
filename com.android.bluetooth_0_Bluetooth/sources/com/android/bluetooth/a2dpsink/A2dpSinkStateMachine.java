package com.android.bluetooth.a2dpsink;

import android.bluetooth.BluetoothAudioConfig;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.vcard.VCardConfig;

/* JADX INFO: loaded from: classes.dex */
public class A2dpSinkStateMachine extends StateMachine {
    protected static final int CLEANUP = 100;
    public static final int CONNECT = 1;
    private static final int CONNECT_TIMEOUT = 101;
    static final int CONNECT_TIMEOUT_MS = 5000;
    public static final int DISCONNECT = 2;
    static final int STACK_EVENT = 200;
    protected BluetoothAudioConfig mAudioConfig;
    protected final Connected mConnected;
    protected final Connecting mConnecting;
    protected final BluetoothDevice mDevice;
    protected final byte[] mDeviceAddress;
    protected final Disconnected mDisconnected;
    protected final Disconnecting mDisconnecting;
    protected int mMostRecentState;
    protected final A2dpSinkService mService;
    static final String TAG = "A2DPSinkStateMachine";
    static final boolean DBG = Log.isLoggable(TAG, 3);

    A2dpSinkStateMachine(BluetoothDevice device, A2dpSinkService service) {
        super(TAG);
        this.mMostRecentState = 0;
        this.mAudioConfig = null;
        this.mDevice = device;
        this.mDeviceAddress = Utils.getByteAddress(device);
        this.mService = service;
        if (DBG) {
            Log.d(TAG, device.toString());
        }
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mConnected = new Connected();
        this.mDisconnecting = new Disconnecting();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mConnected);
        addState(this.mDisconnecting);
        setInitialState(this.mDisconnected);
    }

    protected String getConnectionStateChangedIntent() {
        return "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED";
    }

    public int getState() {
        return this.mMostRecentState;
    }

    BluetoothAudioConfig getAudioConfig() {
        return this.mAudioConfig;
    }

    public synchronized BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public final void connect() {
        sendMessage(1);
    }

    public final void disconnect() {
        sendMessage(2);
    }

    public void dump(StringBuilder sb) {
        ProfileService.println(sb, "mDevice: " + this.mDevice.getAddressOnlyforLog() + "(" + this.mDevice.getName() + ") " + toString());
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void unhandledMessage(Message msg) {
        Log.w(TAG, "unhandledMessage in state " + getCurrentState() + "msg.what=" + msg.what);
    }

    class Disconnected extends State {
        Disconnected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (A2dpSinkStateMachine.DBG) {
                Log.d(A2dpSinkStateMachine.TAG, "Enter Disconnected");
            }
            if (A2dpSinkStateMachine.this.mMostRecentState != 0) {
                A2dpSinkStateMachine.this.sendMessage(100);
            }
            A2dpSinkStateMachine.this.onConnectionStateChanged(0);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                if (A2dpSinkStateMachine.DBG) {
                    Log.d(A2dpSinkStateMachine.TAG, "Connect");
                }
                A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
                a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mConnecting);
                return true;
            }
            if (i == 100) {
                A2dpSinkStateMachine.this.mService.removeStateMachine(A2dpSinkStateMachine.this);
                return true;
            }
            if (i == 200) {
                processStackEvent((StackEvent) message.obj);
                return true;
            }
            return false;
        }

        void processStackEvent(StackEvent event) {
            int i = event.mType;
            if (i != 1) {
                if (i == 3) {
                    A2dpSinkStateMachine.this.mAudioConfig = new BluetoothAudioConfig(event.mSampleRate, event.mChannelCount, 2, event.mCodecIndex);
                    return;
                }
                return;
            }
            int i2 = event.mState;
            if (i2 == 0) {
                A2dpSinkStateMachine.this.sendMessage(100);
                return;
            }
            if (i2 != 1) {
                if (i2 == 2) {
                    A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
                    a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mConnected);
                    return;
                }
                return;
            }
            if (A2dpSinkStateMachine.this.mService.getConnectionPolicy(A2dpSinkStateMachine.this.mDevice) == 0) {
                Log.w(A2dpSinkStateMachine.TAG, "Ignore incoming connection, profile is turned off for " + A2dpSinkStateMachine.this.mDevice.getAddressOnlyforLog());
                A2dpSinkStateMachine.this.mService.disconnectA2dpNative(A2dpSinkStateMachine.this.mDeviceAddress);
                return;
            }
            A2dpSinkStateMachine.this.mConnecting.mIncomingConnection = true;
            A2dpSinkStateMachine a2dpSinkStateMachine2 = A2dpSinkStateMachine.this;
            a2dpSinkStateMachine2.transitionTo(a2dpSinkStateMachine2.mConnecting);
        }
    }

    class Connecting extends State {
        boolean mIncomingConnection = false;

        Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (A2dpSinkStateMachine.DBG) {
                Log.d(A2dpSinkStateMachine.TAG, "Enter Connecting");
            }
            A2dpSinkStateMachine.this.onConnectionStateChanged(1);
            A2dpSinkStateMachine.this.sendMessageDelayed(A2dpSinkStateMachine.CONNECT_TIMEOUT, 5000L);
            if (!this.mIncomingConnection) {
                A2dpSinkStateMachine.this.mService.connectA2dpNative(A2dpSinkStateMachine.this.mDeviceAddress);
            }
            super.enter();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == A2dpSinkStateMachine.CONNECT_TIMEOUT) {
                A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
                a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mDisconnected);
                return true;
            }
            if (i == 200) {
                processStackEvent((StackEvent) message.obj);
                return true;
            }
            return false;
        }

        void processStackEvent(StackEvent event) {
            int i = event.mType;
            if (i != 1) {
                if (i == 3) {
                    A2dpSinkStateMachine.this.mAudioConfig = new BluetoothAudioConfig(event.mSampleRate, event.mChannelCount, 2, event.mCodecIndex);
                    return;
                }
                return;
            }
            int i2 = event.mState;
            if (i2 == 0) {
                A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
                a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mDisconnected);
            } else if (i2 == 2) {
                A2dpSinkStateMachine a2dpSinkStateMachine2 = A2dpSinkStateMachine.this;
                a2dpSinkStateMachine2.transitionTo(a2dpSinkStateMachine2.mConnected);
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            A2dpSinkStateMachine.this.removeMessages(A2dpSinkStateMachine.CONNECT_TIMEOUT);
            this.mIncomingConnection = false;
        }
    }

    class Connected extends State {
        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (A2dpSinkStateMachine.DBG) {
                Log.d(A2dpSinkStateMachine.TAG, "Enter Connected");
            }
            A2dpSinkStateMachine.this.onConnectionStateChanged(2);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != 2) {
                if (i == 200) {
                    processStackEvent((StackEvent) message.obj);
                    return true;
                }
                return false;
            }
            A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
            a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mDisconnecting);
            A2dpSinkStateMachine.this.mService.disconnectA2dpNative(A2dpSinkStateMachine.this.mDeviceAddress);
            return true;
        }

        void processStackEvent(StackEvent event) {
            int i = event.mType;
            if (i != 1) {
                if (i == 3) {
                    A2dpSinkStateMachine.this.mAudioConfig = new BluetoothAudioConfig(event.mSampleRate, event.mChannelCount, 2, event.mCodecIndex);
                    return;
                }
                return;
            }
            int i2 = event.mState;
            if (i2 == 0) {
                A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
                a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mDisconnected);
            } else if (i2 == 3) {
                A2dpSinkStateMachine a2dpSinkStateMachine2 = A2dpSinkStateMachine.this;
                a2dpSinkStateMachine2.transitionTo(a2dpSinkStateMachine2.mDisconnecting);
            }
        }
    }

    protected class Disconnecting extends State {
        protected Disconnecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (A2dpSinkStateMachine.DBG) {
                Log.d(A2dpSinkStateMachine.TAG, "Enter Disconnecting");
            }
            A2dpSinkStateMachine.this.onConnectionStateChanged(3);
            A2dpSinkStateMachine a2dpSinkStateMachine = A2dpSinkStateMachine.this;
            a2dpSinkStateMachine.transitionTo(a2dpSinkStateMachine.mDisconnected);
        }
    }

    protected void onConnectionStateChanged(int currentState) {
        if (this.mMostRecentState == currentState) {
            return;
        }
        if (currentState == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.A2DP_SINK);
        }
        if (DBG) {
            Log.d(TAG, "Connection state " + this.mDevice.getAddressOnlyforLog() + ": " + this.mMostRecentState + "->" + currentState);
        }
        Intent intent = new Intent("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", this.mMostRecentState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", currentState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        this.mMostRecentState = currentState;
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }
}
