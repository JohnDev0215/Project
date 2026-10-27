package com.android.bluetooth.a2dp;

import android.bluetooth.BluetoothCodecConfig;
import android.bluetooth.BluetoothCodecStatus;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.vcard.VCardConfig;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
final class A2dpStateMachine extends StateMachine {
    static final int CONNECT = 1;
    private static final int CONNECT_TIMEOUT = 201;
    private static final boolean DBG = true;
    static final int DISCONNECT = 2;
    static final int STACK_EVENT = 101;
    private static final String TAG = "A2dpStateMachine";
    static int sConnectTimeoutMs = 30000;
    private A2dpNativeInterface mA2dpNativeInterface;
    boolean mA2dpOffloadEnabled;
    private A2dpService mA2dpService;
    private BluetoothCodecStatus mCodecStatus;
    private Connected mConnected;
    private Connecting mConnecting;
    private int mConnectionState;
    private final BluetoothDevice mDevice;
    private Disconnected mDisconnected;
    private Disconnecting mDisconnecting;
    private boolean mIsPlaying;
    private int mLastConnectionState;

    A2dpStateMachine(BluetoothDevice device, A2dpService a2dpService, A2dpNativeInterface a2dpNativeInterface, Looper looper) {
        super(TAG, looper);
        this.mConnectionState = 0;
        this.mLastConnectionState = -1;
        this.mA2dpOffloadEnabled = false;
        this.mIsPlaying = false;
        setDbg(true);
        this.mDevice = device;
        this.mA2dpService = a2dpService;
        this.mA2dpNativeInterface = a2dpNativeInterface;
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mDisconnecting = new Disconnecting();
        this.mConnected = new Connected();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mDisconnecting);
        addState(this.mConnected);
        this.mA2dpOffloadEnabled = this.mA2dpService.mA2dpOffloadEnabled;
        setInitialState(this.mDisconnected);
    }

    static A2dpStateMachine make(BluetoothDevice device, A2dpService a2dpService, A2dpNativeInterface a2dpNativeInterface, Looper looper) {
        Log.i(TAG, "make for device " + device.getAddressOnlyforLog());
        A2dpStateMachine a2dpSm = new A2dpStateMachine(device, a2dpService, a2dpNativeInterface, looper);
        a2dpSm.start();
        return a2dpSm;
    }

    public void doQuit() {
        log("doQuit for device " + this.mDevice);
        if (this.mIsPlaying) {
            log("doQuit: stopped playing " + this.mDevice);
            this.mIsPlaying = false;
            broadcastAudioState(11, 10);
        }
        quitNow();
    }

    public void cleanup() {
        log("cleanup for device " + this.mDevice);
    }

    class Disconnected extends State {
        Disconnected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            StringBuilder sb = new StringBuilder();
            sb.append("Enter Disconnected(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            Log.i(A2dpStateMachine.TAG, sb.toString());
            A2dpStateMachine.this.mConnectionState = 0;
            A2dpStateMachine.this.removeDeferredMessages(2);
            if (A2dpStateMachine.this.mLastConnectionState != -1) {
                A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                a2dpStateMachine.broadcastConnectionState(a2dpStateMachine.mConnectionState, A2dpStateMachine.this.mLastConnectionState);
                if (A2dpStateMachine.this.mIsPlaying) {
                    Log.i(A2dpStateMachine.TAG, "Disconnected: stopped playing: " + A2dpStateMachine.this.mDevice);
                    A2dpStateMachine.this.mIsPlaying = false;
                    A2dpStateMachine.this.broadcastAudioState(11, 10);
                }
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            StringBuilder sb = new StringBuilder();
            sb.append("Exit Disconnected(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            a2dpStateMachine.log(sb.toString());
            A2dpStateMachine.this.mLastConnectionState = 0;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            A2dpStateMachine.this.log("Disconnected process message(" + A2dpStateMachine.this.mDevice + "): " + A2dpStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1) {
                Log.i(A2dpStateMachine.TAG, "Connecting to " + A2dpStateMachine.this.mDevice);
                if (A2dpStateMachine.this.mA2dpNativeInterface.connectA2dp(A2dpStateMachine.this.mDevice)) {
                    if (A2dpStateMachine.this.mA2dpService.okToConnect(A2dpStateMachine.this.mDevice, true)) {
                        A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                        a2dpStateMachine.transitionTo(a2dpStateMachine.mConnecting);
                    } else {
                        Log.w(A2dpStateMachine.TAG, "Outgoing A2DP Connecting request rejected: " + A2dpStateMachine.this.mDevice);
                    }
                } else {
                    Log.e(A2dpStateMachine.TAG, "Disconnected: error connecting to " + A2dpStateMachine.this.mDevice);
                }
            } else if (i == 2) {
                Log.w(A2dpStateMachine.TAG, "Disconnected: DISCONNECT ignored: " + A2dpStateMachine.this.mDevice);
            } else if (i == A2dpStateMachine.STACK_EVENT) {
                A2dpStackEvent event = (A2dpStackEvent) message.obj;
                A2dpStateMachine.this.log("Disconnected: stack event: " + event);
                if (!A2dpStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(A2dpStateMachine.TAG, "Device(" + A2dpStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                int i2 = event.type;
                if (i2 == 1) {
                    processConnectionEvent(event.valueInt);
                } else if (i2 == 3) {
                    A2dpStateMachine.this.processCodecConfigEvent(event.codecStatus);
                } else {
                    Log.e(A2dpStateMachine.TAG, "Disconnected: ignoring stack event: " + event);
                }
            } else {
                return false;
            }
            return true;
        }

        private void processConnectionEvent(int event) {
            if (event == 0) {
                Log.w(A2dpStateMachine.TAG, "Ignore A2DP DISCONNECTED event: " + A2dpStateMachine.this.mDevice);
                return;
            }
            if (event == 1) {
                if (A2dpStateMachine.this.mA2dpService.okToConnect(A2dpStateMachine.this.mDevice, false)) {
                    Log.i(A2dpStateMachine.TAG, "Incoming A2DP Connecting request accepted: " + A2dpStateMachine.this.mDevice);
                    A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                    a2dpStateMachine.transitionTo(a2dpStateMachine.mConnecting);
                    return;
                }
                Log.w(A2dpStateMachine.TAG, "Incoming A2DP Connecting request rejected: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
                return;
            }
            if (event != 2) {
                if (event == 3) {
                    Log.w(A2dpStateMachine.TAG, "Ignore A2DP DISCONNECTING event: " + A2dpStateMachine.this.mDevice);
                    return;
                }
                Log.e(A2dpStateMachine.TAG, "Incorrect event: " + event + " device: " + A2dpStateMachine.this.mDevice);
                return;
            }
            Log.w(A2dpStateMachine.TAG, "A2DP Connected from Disconnected state: " + A2dpStateMachine.this.mDevice);
            if (A2dpStateMachine.this.mA2dpService.okToConnect(A2dpStateMachine.this.mDevice, false)) {
                Log.i(A2dpStateMachine.TAG, "Incoming A2DP Connected request accepted: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine2 = A2dpStateMachine.this;
                a2dpStateMachine2.transitionTo(a2dpStateMachine2.mConnected);
                return;
            }
            Log.w(A2dpStateMachine.TAG, "Incoming A2DP Connected request rejected: " + A2dpStateMachine.this.mDevice);
            A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
        }
    }

    class Connecting extends State {
        Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            StringBuilder sb = new StringBuilder();
            sb.append("Enter Connecting(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            Log.i(A2dpStateMachine.TAG, sb.toString());
            A2dpStateMachine.this.sendMessageDelayed(A2dpStateMachine.CONNECT_TIMEOUT, A2dpStateMachine.sConnectTimeoutMs);
            A2dpStateMachine.this.mConnectionState = 1;
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            a2dpStateMachine.broadcastConnectionState(a2dpStateMachine.mConnectionState, A2dpStateMachine.this.mLastConnectionState);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            StringBuilder sb = new StringBuilder();
            sb.append("Exit Connecting(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            a2dpStateMachine.log(sb.toString());
            A2dpStateMachine.this.mLastConnectionState = 1;
            A2dpStateMachine.this.removeMessages(A2dpStateMachine.CONNECT_TIMEOUT);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            A2dpStateMachine.this.log("Connecting process message(" + A2dpStateMachine.this.mDevice + "): " + A2dpStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1) {
                A2dpStateMachine.this.deferMessage(message);
            } else if (i == 2) {
                Log.i(A2dpStateMachine.TAG, "Connecting: connection canceled to " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                a2dpStateMachine.transitionTo(a2dpStateMachine.mDisconnected);
            } else if (i == A2dpStateMachine.STACK_EVENT) {
                A2dpStackEvent event = (A2dpStackEvent) message.obj;
                A2dpStateMachine.this.log("Connecting: stack event: " + event);
                if (!A2dpStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(A2dpStateMachine.TAG, "Device(" + A2dpStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                int i2 = event.type;
                if (i2 == 1) {
                    processConnectionEvent(event.valueInt);
                } else if (i2 != 2) {
                    if (i2 == 3) {
                        A2dpStateMachine.this.processCodecConfigEvent(event.codecStatus);
                    } else {
                        Log.e(A2dpStateMachine.TAG, "Connecting: ignoring stack event: " + event);
                    }
                }
            } else {
                if (i != A2dpStateMachine.CONNECT_TIMEOUT) {
                    return false;
                }
                Log.w(A2dpStateMachine.TAG, "Connecting connection timeout: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
                A2dpStackEvent event2 = new A2dpStackEvent(1);
                event2.device = A2dpStateMachine.this.mDevice;
                event2.valueInt = 0;
                A2dpStateMachine.this.sendMessage(A2dpStateMachine.STACK_EVENT, event2);
            }
            return true;
        }

        private void processConnectionEvent(int event) {
            if (event == 0) {
                Log.w(A2dpStateMachine.TAG, "Connecting device disconnected: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                a2dpStateMachine.transitionTo(a2dpStateMachine.mDisconnected);
                return;
            }
            if (event != 1) {
                if (event == 2) {
                    A2dpStateMachine a2dpStateMachine2 = A2dpStateMachine.this;
                    a2dpStateMachine2.transitionTo(a2dpStateMachine2.mConnected);
                    return;
                }
                if (event == 3) {
                    Log.w(A2dpStateMachine.TAG, "Connecting interrupted: device is disconnecting: " + A2dpStateMachine.this.mDevice);
                    A2dpStateMachine a2dpStateMachine3 = A2dpStateMachine.this;
                    a2dpStateMachine3.transitionTo(a2dpStateMachine3.mDisconnecting);
                    return;
                }
                Log.e(A2dpStateMachine.TAG, "Incorrect event: " + event);
            }
        }
    }

    class Disconnecting extends State {
        Disconnecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            StringBuilder sb = new StringBuilder();
            sb.append("Enter Disconnecting(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            Log.i(A2dpStateMachine.TAG, sb.toString());
            A2dpStateMachine.this.sendMessageDelayed(A2dpStateMachine.CONNECT_TIMEOUT, A2dpStateMachine.sConnectTimeoutMs);
            A2dpStateMachine.this.mConnectionState = 3;
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            a2dpStateMachine.broadcastConnectionState(a2dpStateMachine.mConnectionState, A2dpStateMachine.this.mLastConnectionState);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            StringBuilder sb = new StringBuilder();
            sb.append("Exit Disconnecting(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            a2dpStateMachine.log(sb.toString());
            A2dpStateMachine.this.mLastConnectionState = 3;
            A2dpStateMachine.this.removeMessages(A2dpStateMachine.CONNECT_TIMEOUT);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            A2dpStateMachine.this.log("Disconnecting process message(" + A2dpStateMachine.this.mDevice + "): " + A2dpStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1 || i == 2) {
                A2dpStateMachine.this.deferMessage(message);
            } else if (i == A2dpStateMachine.STACK_EVENT) {
                A2dpStackEvent event = (A2dpStackEvent) message.obj;
                A2dpStateMachine.this.log("Disconnecting: stack event: " + event);
                if (!A2dpStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(A2dpStateMachine.TAG, "Device(" + A2dpStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                int i2 = event.type;
                if (i2 == 1) {
                    processConnectionEvent(event.valueInt);
                } else if (i2 == 3) {
                    A2dpStateMachine.this.processCodecConfigEvent(event.codecStatus);
                } else {
                    Log.e(A2dpStateMachine.TAG, "Disconnecting: ignoring stack event: " + event);
                }
            } else {
                if (i != A2dpStateMachine.CONNECT_TIMEOUT) {
                    return false;
                }
                Log.w(A2dpStateMachine.TAG, "Disconnecting connection timeout: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
                A2dpStackEvent event2 = new A2dpStackEvent(1);
                event2.device = A2dpStateMachine.this.mDevice;
                event2.valueInt = 0;
                A2dpStateMachine.this.sendMessage(A2dpStateMachine.STACK_EVENT, event2);
            }
            return true;
        }

        private void processConnectionEvent(int event) {
            if (event == 0) {
                Log.i(A2dpStateMachine.TAG, "Disconnected: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                a2dpStateMachine.transitionTo(a2dpStateMachine.mDisconnected);
                return;
            }
            if (event == 1) {
                if (A2dpStateMachine.this.mA2dpService.okToConnect(A2dpStateMachine.this.mDevice, false)) {
                    Log.i(A2dpStateMachine.TAG, "Disconnecting interrupted: try to reconnect: " + A2dpStateMachine.this.mDevice);
                    A2dpStateMachine a2dpStateMachine2 = A2dpStateMachine.this;
                    a2dpStateMachine2.transitionTo(a2dpStateMachine2.mConnecting);
                    return;
                }
                Log.w(A2dpStateMachine.TAG, "Incoming A2DP Connecting request rejected: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
                return;
            }
            if (event != 2) {
                if (event != 3) {
                    Log.e(A2dpStateMachine.TAG, "Incorrect event: " + event);
                    return;
                }
                return;
            }
            if (A2dpStateMachine.this.mA2dpService.okToConnect(A2dpStateMachine.this.mDevice, false)) {
                Log.w(A2dpStateMachine.TAG, "Disconnecting interrupted: device is connected: " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine3 = A2dpStateMachine.this;
                a2dpStateMachine3.transitionTo(a2dpStateMachine3.mConnected);
                return;
            }
            Log.w(A2dpStateMachine.TAG, "Incoming A2DP Connected request rejected: " + A2dpStateMachine.this.mDevice);
            A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice);
        }
    }

    class Connected extends State {
        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            StringBuilder sb = new StringBuilder();
            sb.append("Enter Connected(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            Log.i(A2dpStateMachine.TAG, sb.toString());
            A2dpStateMachine.this.mConnectionState = 2;
            A2dpStateMachine.this.removeDeferredMessages(1);
            A2dpStateMachine.this.mA2dpService.updateOptionalCodecsSupport(A2dpStateMachine.this.mDevice);
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            a2dpStateMachine.broadcastConnectionState(a2dpStateMachine.mConnectionState, A2dpStateMachine.this.mLastConnectionState);
            A2dpStateMachine.this.broadcastAudioState(11, 10);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            Message currentMessage = A2dpStateMachine.this.getCurrentMessage();
            A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
            StringBuilder sb = new StringBuilder();
            sb.append("Exit Connected(");
            sb.append(A2dpStateMachine.this.mDevice);
            sb.append("): ");
            sb.append(currentMessage == null ? "null" : A2dpStateMachine.messageWhatToString(currentMessage.what));
            a2dpStateMachine.log(sb.toString());
            A2dpStateMachine.this.mLastConnectionState = 2;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            A2dpStateMachine.this.log("Connected process message(" + A2dpStateMachine.this.mDevice + "): " + A2dpStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1) {
                Log.w(A2dpStateMachine.TAG, "Connected: CONNECT ignored: " + A2dpStateMachine.this.mDevice);
            } else if (i == 2) {
                Log.i(A2dpStateMachine.TAG, "Disconnecting from " + A2dpStateMachine.this.mDevice);
                if (!A2dpStateMachine.this.mA2dpNativeInterface.disconnectA2dp(A2dpStateMachine.this.mDevice)) {
                    Log.e(A2dpStateMachine.TAG, "Connected: error disconnecting from " + A2dpStateMachine.this.mDevice);
                    A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                    a2dpStateMachine.transitionTo(a2dpStateMachine.mDisconnected);
                } else {
                    A2dpStateMachine a2dpStateMachine2 = A2dpStateMachine.this;
                    a2dpStateMachine2.transitionTo(a2dpStateMachine2.mDisconnecting);
                }
            } else if (i == A2dpStateMachine.STACK_EVENT) {
                A2dpStackEvent event = (A2dpStackEvent) message.obj;
                A2dpStateMachine.this.log("Connected: stack event: " + event);
                if (!A2dpStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(A2dpStateMachine.TAG, "Device(" + A2dpStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                int i2 = event.type;
                if (i2 == 1) {
                    processConnectionEvent(event.valueInt);
                } else if (i2 == 2) {
                    processAudioStateEvent(event.valueInt);
                } else if (i2 == 3) {
                    A2dpStateMachine.this.processCodecConfigEvent(event.codecStatus);
                } else {
                    Log.e(A2dpStateMachine.TAG, "Connected: ignoring stack event: " + event);
                }
            } else {
                return false;
            }
            return true;
        }

        private void processConnectionEvent(int event) {
            if (event == 0) {
                Log.i(A2dpStateMachine.TAG, "Disconnected from " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine = A2dpStateMachine.this;
                a2dpStateMachine.transitionTo(a2dpStateMachine.mDisconnected);
                return;
            }
            if (event == 1) {
                Log.w(A2dpStateMachine.TAG, "Ignore A2DP CONNECTING event: " + A2dpStateMachine.this.mDevice);
                return;
            }
            if (event == 2) {
                Log.w(A2dpStateMachine.TAG, "Ignore A2DP CONNECTED event: " + A2dpStateMachine.this.mDevice);
                return;
            }
            if (event == 3) {
                Log.i(A2dpStateMachine.TAG, "Disconnecting from " + A2dpStateMachine.this.mDevice);
                A2dpStateMachine a2dpStateMachine2 = A2dpStateMachine.this;
                a2dpStateMachine2.transitionTo(a2dpStateMachine2.mDisconnecting);
                return;
            }
            Log.e(A2dpStateMachine.TAG, "Connection State Device: " + A2dpStateMachine.this.mDevice + " bad event: " + event);
        }

        private void processAudioStateEvent(int state) {
            if (state == 0 || state == 1) {
                synchronized (this) {
                    if (A2dpStateMachine.this.mIsPlaying) {
                        Log.i(A2dpStateMachine.TAG, "Connected: stopped playing: " + A2dpStateMachine.this.mDevice);
                        A2dpStateMachine.this.mIsPlaying = false;
                        A2dpStateMachine.this.broadcastAudioState(11, 10);
                    }
                }
                return;
            }
            if (state == 2) {
                synchronized (this) {
                    if (!A2dpStateMachine.this.mIsPlaying) {
                        Log.i(A2dpStateMachine.TAG, "Connected: started playing: " + A2dpStateMachine.this.mDevice);
                        A2dpStateMachine.this.mIsPlaying = true;
                        A2dpStateMachine.this.broadcastAudioState(10, 11);
                    }
                }
                return;
            }
            Log.e(A2dpStateMachine.TAG, "Audio State Device: " + A2dpStateMachine.this.mDevice + " bad state: " + state);
        }
    }

    int getConnectionState() {
        return this.mConnectionState;
    }

    BluetoothDevice getDevice() {
        return this.mDevice;
    }

    boolean isConnected() {
        boolean z;
        synchronized (this) {
            z = getConnectionState() == 2;
        }
        return z;
    }

    boolean isPlaying() {
        boolean z;
        synchronized (this) {
            z = this.mIsPlaying;
        }
        return z;
    }

    BluetoothCodecStatus getCodecStatus() {
        BluetoothCodecStatus bluetoothCodecStatus;
        synchronized (this) {
            bluetoothCodecStatus = this.mCodecStatus;
        }
        return bluetoothCodecStatus;
    }

    void processCodecConfigEvent(BluetoothCodecStatus newCodecStatus) {
        BluetoothCodecConfig prevCodecConfig = null;
        BluetoothCodecStatus prevCodecStatus = this.mCodecStatus;
        synchronized (this) {
            if (this.mCodecStatus != null) {
                prevCodecConfig = this.mCodecStatus.getCodecConfig();
            }
            this.mCodecStatus = newCodecStatus;
        }
        Log.d(TAG, "A2DP Codec Config: " + prevCodecConfig + "->" + newCodecStatus.getCodecConfig());
        BluetoothCodecConfig[] codecsLocalCapabilities = newCodecStatus.getCodecsLocalCapabilities();
        int length = codecsLocalCapabilities.length;
        for (int i = 0; i < length; i++) {
            BluetoothCodecConfig codecConfig = codecsLocalCapabilities[i];
            Log.d(TAG, "A2DP Codec Local Capability: " + codecConfig);
        }
        for (BluetoothCodecConfig codecConfig2 : newCodecStatus.getCodecsSelectableCapabilities()) {
            Log.d(TAG, "A2DP Codec Selectable Capability: " + codecConfig2);
        }
        if (isConnected() && !sameSelectableCodec(prevCodecStatus, this.mCodecStatus)) {
            this.mA2dpService.updateOptionalCodecsSupport(this.mDevice);
        }
        if (this.mA2dpOffloadEnabled) {
            boolean update = false;
            BluetoothCodecConfig newCodecConfig = this.mCodecStatus.getCodecConfig();
            if ((prevCodecConfig != null && prevCodecConfig.getCodecType() != newCodecConfig.getCodecType()) || !newCodecConfig.sameAudioFeedingParameters(prevCodecConfig)) {
                update = true;
            } else if (newCodecConfig.getCodecType() == 4 && prevCodecConfig != null && prevCodecConfig.getCodecSpecific1() != newCodecConfig.getCodecSpecific1()) {
                update = true;
            }
            if (update) {
                this.mA2dpService.codecConfigUpdated(this.mDevice, this.mCodecStatus, false);
                return;
            }
            return;
        }
        boolean sameAudioFeedingParameters = newCodecStatus.getCodecConfig().sameAudioFeedingParameters(prevCodecConfig);
        this.mA2dpService.codecConfigUpdated(this.mDevice, this.mCodecStatus, sameAudioFeedingParameters);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConnectionState(int newState, int prevState) {
        log("Connection state " + this.mDevice + ": " + profileStateToString(prevState) + "->" + profileStateToString(newState));
        Intent intent = new Intent("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
        intent.addFlags(83886080);
        this.mA2dpService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastAudioState(int newState, int prevState) {
        log("A2DP Playing state : device: " + this.mDevice + " State:" + audioStateToString(prevState) + "->" + audioStateToString(newState));
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_A2DP_PLAYBACK_STATE_CHANGED, newState);
        Intent intent = new Intent("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        this.mA2dpService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected String getLogRecString(Message msg) {
        return messageWhatToString(msg.what) + ": arg1=" + msg.arg1 + ", arg2=" + msg.arg2 + ", obj=" + msg.obj;
    }

    private static boolean sameSelectableCodec(BluetoothCodecStatus prevCodecStatus, BluetoothCodecStatus newCodecStatus) {
        if (prevCodecStatus == null) {
            return false;
        }
        return BluetoothCodecStatus.sameCapabilities(prevCodecStatus.getCodecsSelectableCapabilities(), newCodecStatus.getCodecsSelectableCapabilities());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String messageWhatToString(int what) {
        if (what == 1) {
            return "CONNECT";
        }
        if (what == 2) {
            return "DISCONNECT";
        }
        if (what == STACK_EVENT) {
            return "STACK_EVENT";
        }
        if (what == CONNECT_TIMEOUT) {
            return "CONNECT_TIMEOUT";
        }
        return Integer.toString(what);
    }

    private static String profileStateToString(int state) {
        if (state == 0) {
            return "DISCONNECTED";
        }
        if (state == 1) {
            return "CONNECTING";
        }
        if (state == 2) {
            return "CONNECTED";
        }
        if (state == 3) {
            return "DISCONNECTING";
        }
        return Integer.toString(state);
    }

    private static String audioStateToString(int state) {
        if (state == 10) {
            return "PLAYING";
        }
        if (state == 11) {
            return "NOT_PLAYING";
        }
        return Integer.toString(state);
    }

    public void dump(StringBuilder sb) {
        boolean isActive = Objects.equals(this.mDevice, this.mA2dpService.getActiveDevice());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("=== A2dpStateMachine for ");
        sb2.append(this.mDevice);
        sb2.append(isActive ? " (Active) ===" : " ===");
        ProfileService.println(sb, sb2.toString());
        ProfileService.println(sb, "  getConnectionPolicy: " + this.mA2dpService.getConnectionPolicy(this.mDevice));
        ProfileService.println(sb, "  mConnectionState: " + profileStateToString(this.mConnectionState) + ", mLastConnectionState: " + profileStateToString(this.mLastConnectionState));
        StringBuilder sb3 = new StringBuilder();
        sb3.append("  mIsPlaying: ");
        sb3.append(this.mIsPlaying);
        ProfileService.println(sb, sb3.toString());
        ProfileService.println(sb, "  getSupportsOptionalCodecs: " + this.mA2dpService.getSupportsOptionalCodecs(this.mDevice) + ", getOptionalCodecsEnabled: " + this.mA2dpService.getOptionalCodecsEnabled(this.mDevice));
        synchronized (this) {
            if (this.mCodecStatus != null) {
                ProfileService.println(sb, "  mCodecConfig: " + this.mCodecStatus.getCodecConfig());
                ProfileService.println(sb, "  mCodecsSelectableCapabilities:");
                for (BluetoothCodecConfig config : this.mCodecStatus.getCodecsSelectableCapabilities()) {
                    ProfileService.println(sb, "    " + config);
                }
            }
        }
        ProfileService.println(sb, "  StateMachine: " + toString());
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        super.dump(new FileDescriptor(), printWriter, new String[0]);
        printWriter.flush();
        stringWriter.flush();
        ProfileService.println(sb, "  StateMachineLog:");
        Scanner scanner = new Scanner(stringWriter.toString());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ProfileService.println(sb, "    " + line);
        }
        scanner.close();
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void log(String msg) {
        super.log(msg);
    }
}
