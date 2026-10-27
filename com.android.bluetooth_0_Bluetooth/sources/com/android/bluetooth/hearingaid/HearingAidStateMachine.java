package com.android.bluetooth.hearingaid;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
final class HearingAidStateMachine extends StateMachine {
    static final int CONNECT = 1;
    private static final int CONNECT_TIMEOUT = 201;
    private static final boolean DBG = false;
    static final int DISCONNECT = 2;
    static final int STACK_EVENT = 101;
    private static final String TAG = "HearingAidStateMachine";
    static int sConnectTimeoutMs = 30000;
    private Connected mConnected;
    private Connecting mConnecting;
    private int mConnectionState;
    private final BluetoothDevice mDevice;
    private Disconnected mDisconnected;
    private Disconnecting mDisconnecting;
    private int mLastConnectionState;
    private HearingAidNativeInterface mNativeInterface;
    private HearingAidService mService;

    HearingAidStateMachine(BluetoothDevice device, HearingAidService svc, HearingAidNativeInterface nativeInterface, Looper looper) {
        super(TAG, looper);
        this.mConnectionState = 0;
        this.mLastConnectionState = -1;
        this.mDevice = device;
        this.mService = svc;
        this.mNativeInterface = nativeInterface;
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mDisconnecting = new Disconnecting();
        this.mConnected = new Connected();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mDisconnecting);
        addState(this.mConnected);
        setInitialState(this.mDisconnected);
    }

    static HearingAidStateMachine make(BluetoothDevice device, HearingAidService svc, HearingAidNativeInterface nativeInterface, Looper looper) {
        Log.i(TAG, "make for device " + device);
        HearingAidStateMachine HearingAidSm = new HearingAidStateMachine(device, svc, nativeInterface, looper);
        HearingAidSm.start();
        return HearingAidSm;
    }

    public void doQuit() {
        log("doQuit for device " + this.mDevice);
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
            Log.i(HearingAidStateMachine.TAG, "Enter Disconnected(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.mConnectionState = 0;
            HearingAidStateMachine.this.removeDeferredMessages(2);
            if (HearingAidStateMachine.this.mLastConnectionState != -1) {
                HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                hearingAidStateMachine.broadcastConnectionState(0, hearingAidStateMachine.mLastConnectionState);
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HearingAidStateMachine.this.log("Exit Disconnected(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.mLastConnectionState = 0;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            HearingAidStateMachine.this.log("Disconnected process message(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1) {
                HearingAidStateMachine.this.log("Connecting to " + HearingAidStateMachine.this.mDevice);
                if (HearingAidStateMachine.this.mNativeInterface.connectHearingAid(HearingAidStateMachine.this.mDevice)) {
                    if (HearingAidStateMachine.this.mService.okToConnect(HearingAidStateMachine.this.mDevice)) {
                        HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                        hearingAidStateMachine.transitionTo(hearingAidStateMachine.mConnecting);
                    } else {
                        Log.w(HearingAidStateMachine.TAG, "Outgoing HearingAid Connecting request rejected: " + HearingAidStateMachine.this.mDevice);
                    }
                } else {
                    Log.e(HearingAidStateMachine.TAG, "Disconnected: error connecting to " + HearingAidStateMachine.this.mDevice);
                }
            } else if (i == 2) {
                Log.d(HearingAidStateMachine.TAG, "Disconnected: DISCONNECT: call native disconnect for " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
            } else if (i == HearingAidStateMachine.STACK_EVENT) {
                HearingAidStackEvent event = (HearingAidStackEvent) message.obj;
                if (!HearingAidStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(HearingAidStateMachine.TAG, "Device(" + HearingAidStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                if (event.type == 1) {
                    processConnectionEvent(event.valueInt1);
                } else {
                    Log.e(HearingAidStateMachine.TAG, "Disconnected: ignoring stack event: " + event);
                }
            } else {
                return false;
            }
            return true;
        }

        private void processConnectionEvent(int state) {
            if (state == 0) {
                Log.w(HearingAidStateMachine.TAG, "Ignore HearingAid DISCONNECTED event: " + HearingAidStateMachine.this.mDevice);
                return;
            }
            if (state == 1) {
                if (HearingAidStateMachine.this.mService.okToConnect(HearingAidStateMachine.this.mDevice)) {
                    Log.i(HearingAidStateMachine.TAG, "Incoming HearingAid Connecting request accepted: " + HearingAidStateMachine.this.mDevice);
                    HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                    hearingAidStateMachine.transitionTo(hearingAidStateMachine.mConnecting);
                    return;
                }
                Log.w(HearingAidStateMachine.TAG, "Incoming HearingAid Connecting request rejected: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
                return;
            }
            if (state != 2) {
                if (state == 3) {
                    Log.w(HearingAidStateMachine.TAG, "Ignore HearingAid DISCONNECTING event: " + HearingAidStateMachine.this.mDevice);
                    return;
                }
                Log.e(HearingAidStateMachine.TAG, "Incorrect state: " + state + " device: " + HearingAidStateMachine.this.mDevice);
                return;
            }
            Log.w(HearingAidStateMachine.TAG, "HearingAid Connected from Disconnected state: " + HearingAidStateMachine.this.mDevice);
            if (HearingAidStateMachine.this.mService.okToConnect(HearingAidStateMachine.this.mDevice)) {
                Log.i(HearingAidStateMachine.TAG, "Incoming HearingAid Connected request accepted: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine hearingAidStateMachine2 = HearingAidStateMachine.this;
                hearingAidStateMachine2.transitionTo(hearingAidStateMachine2.mConnected);
                return;
            }
            Log.w(HearingAidStateMachine.TAG, "Incoming HearingAid Connected request rejected: " + HearingAidStateMachine.this.mDevice);
            HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
        }
    }

    class Connecting extends State {
        Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.i(HearingAidStateMachine.TAG, "Enter Connecting(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.sendMessageDelayed(HearingAidStateMachine.CONNECT_TIMEOUT, (long) HearingAidStateMachine.sConnectTimeoutMs);
            HearingAidStateMachine.this.mConnectionState = 1;
            HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
            hearingAidStateMachine.broadcastConnectionState(1, hearingAidStateMachine.mLastConnectionState);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HearingAidStateMachine.this.log("Exit Connecting(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.mLastConnectionState = 1;
            HearingAidStateMachine.this.removeMessages(HearingAidStateMachine.CONNECT_TIMEOUT);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            HearingAidStateMachine.this.log("Connecting process message(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1) {
                HearingAidStateMachine.this.deferMessage(message);
            } else if (i == 2) {
                HearingAidStateMachine.this.log("Connecting: connection canceled to " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                hearingAidStateMachine.transitionTo(hearingAidStateMachine.mDisconnected);
            } else if (i == HearingAidStateMachine.STACK_EVENT) {
                HearingAidStackEvent event = (HearingAidStackEvent) message.obj;
                HearingAidStateMachine.this.log("Connecting: stack event: " + event);
                if (!HearingAidStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(HearingAidStateMachine.TAG, "Device(" + HearingAidStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                if (event.type == 1) {
                    processConnectionEvent(event.valueInt1);
                } else {
                    Log.e(HearingAidStateMachine.TAG, "Connecting: ignoring stack event: " + event);
                }
            } else {
                if (i != HearingAidStateMachine.CONNECT_TIMEOUT) {
                    return false;
                }
                Log.w(HearingAidStateMachine.TAG, "Connecting connection timeout: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
                if (HearingAidStateMachine.this.mService.isConnectedPeerDevices(HearingAidStateMachine.this.mDevice)) {
                    Log.w(HearingAidStateMachine.TAG, "One side connection timeout: " + HearingAidStateMachine.this.mDevice + ". Try whitelist");
                    HearingAidStateMachine.this.mNativeInterface.addToWhiteList(HearingAidStateMachine.this.mDevice);
                }
                HearingAidStackEvent disconnectEvent = new HearingAidStackEvent(1);
                disconnectEvent.device = HearingAidStateMachine.this.mDevice;
                disconnectEvent.valueInt1 = 0;
                HearingAidStateMachine.this.sendMessage(HearingAidStateMachine.STACK_EVENT, disconnectEvent);
            }
            return true;
        }

        private void processConnectionEvent(int state) {
            if (state == 0) {
                Log.w(HearingAidStateMachine.TAG, "Connecting device disconnected: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                hearingAidStateMachine.transitionTo(hearingAidStateMachine.mDisconnected);
                return;
            }
            if (state != 1) {
                if (state == 2) {
                    HearingAidStateMachine hearingAidStateMachine2 = HearingAidStateMachine.this;
                    hearingAidStateMachine2.transitionTo(hearingAidStateMachine2.mConnected);
                    return;
                }
                if (state == 3) {
                    Log.w(HearingAidStateMachine.TAG, "Connecting interrupted: device is disconnecting: " + HearingAidStateMachine.this.mDevice);
                    HearingAidStateMachine hearingAidStateMachine3 = HearingAidStateMachine.this;
                    hearingAidStateMachine3.transitionTo(hearingAidStateMachine3.mDisconnecting);
                    return;
                }
                Log.e(HearingAidStateMachine.TAG, "Incorrect state: " + state);
            }
        }
    }

    class Disconnecting extends State {
        Disconnecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.i(HearingAidStateMachine.TAG, "Enter Disconnecting(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.sendMessageDelayed(HearingAidStateMachine.CONNECT_TIMEOUT, (long) HearingAidStateMachine.sConnectTimeoutMs);
            HearingAidStateMachine.this.mConnectionState = 3;
            HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
            hearingAidStateMachine.broadcastConnectionState(3, hearingAidStateMachine.mLastConnectionState);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HearingAidStateMachine.this.log("Exit Disconnecting(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.mLastConnectionState = 3;
            HearingAidStateMachine.this.removeMessages(HearingAidStateMachine.CONNECT_TIMEOUT);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            HearingAidStateMachine.this.log("Disconnecting process message(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1 || i == 2) {
                HearingAidStateMachine.this.deferMessage(message);
            } else if (i == HearingAidStateMachine.STACK_EVENT) {
                HearingAidStackEvent event = (HearingAidStackEvent) message.obj;
                HearingAidStateMachine.this.log("Disconnecting: stack event: " + event);
                if (!HearingAidStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(HearingAidStateMachine.TAG, "Device(" + HearingAidStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                if (event.type == 1) {
                    processConnectionEvent(event.valueInt1);
                } else {
                    Log.e(HearingAidStateMachine.TAG, "Disconnecting: ignoring stack event: " + event);
                }
            } else {
                if (i != HearingAidStateMachine.CONNECT_TIMEOUT) {
                    return false;
                }
                Log.w(HearingAidStateMachine.TAG, "Disconnecting connection timeout: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
                HearingAidStackEvent disconnectEvent = new HearingAidStackEvent(1);
                disconnectEvent.device = HearingAidStateMachine.this.mDevice;
                disconnectEvent.valueInt1 = 0;
                HearingAidStateMachine.this.sendMessage(HearingAidStateMachine.STACK_EVENT, disconnectEvent);
            }
            return true;
        }

        private void processConnectionEvent(int state) {
            if (state == 0) {
                Log.i(HearingAidStateMachine.TAG, "Disconnected: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                hearingAidStateMachine.transitionTo(hearingAidStateMachine.mDisconnected);
                return;
            }
            if (state == 1) {
                if (HearingAidStateMachine.this.mService.okToConnect(HearingAidStateMachine.this.mDevice)) {
                    Log.i(HearingAidStateMachine.TAG, "Disconnecting interrupted: try to reconnect: " + HearingAidStateMachine.this.mDevice);
                    HearingAidStateMachine hearingAidStateMachine2 = HearingAidStateMachine.this;
                    hearingAidStateMachine2.transitionTo(hearingAidStateMachine2.mConnecting);
                    return;
                }
                Log.w(HearingAidStateMachine.TAG, "Incoming HearingAid Connecting request rejected: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
                return;
            }
            if (state != 2) {
                if (state != 3) {
                    Log.e(HearingAidStateMachine.TAG, "Incorrect state: " + state);
                    return;
                }
                return;
            }
            if (HearingAidStateMachine.this.mService.okToConnect(HearingAidStateMachine.this.mDevice)) {
                Log.w(HearingAidStateMachine.TAG, "Disconnecting interrupted: device is connected: " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine hearingAidStateMachine3 = HearingAidStateMachine.this;
                hearingAidStateMachine3.transitionTo(hearingAidStateMachine3.mConnected);
                return;
            }
            Log.w(HearingAidStateMachine.TAG, "Incoming HearingAid Connected request rejected: " + HearingAidStateMachine.this.mDevice);
            HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice);
        }
    }

    class Connected extends State {
        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.i(HearingAidStateMachine.TAG, "Enter Connected(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.mConnectionState = 2;
            HearingAidStateMachine.this.removeDeferredMessages(1);
            HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
            hearingAidStateMachine.broadcastConnectionState(2, hearingAidStateMachine.mLastConnectionState);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HearingAidStateMachine.this.log("Exit Connected(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(HearingAidStateMachine.this.getCurrentMessage().what));
            HearingAidStateMachine.this.mLastConnectionState = 2;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            HearingAidStateMachine.this.log("Connected process message(" + HearingAidStateMachine.this.mDevice + "): " + HearingAidStateMachine.messageWhatToString(message.what));
            int i = message.what;
            if (i == 1) {
                Log.w(HearingAidStateMachine.TAG, "Connected: CONNECT ignored: " + HearingAidStateMachine.this.mDevice);
            } else if (i == 2) {
                HearingAidStateMachine.this.log("Disconnecting from " + HearingAidStateMachine.this.mDevice);
                if (!HearingAidStateMachine.this.mNativeInterface.disconnectHearingAid(HearingAidStateMachine.this.mDevice)) {
                    Log.e(HearingAidStateMachine.TAG, "Connected: error disconnecting from " + HearingAidStateMachine.this.mDevice);
                    HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                    hearingAidStateMachine.transitionTo(hearingAidStateMachine.mDisconnected);
                } else {
                    HearingAidStateMachine hearingAidStateMachine2 = HearingAidStateMachine.this;
                    hearingAidStateMachine2.transitionTo(hearingAidStateMachine2.mDisconnecting);
                }
            } else if (i == HearingAidStateMachine.STACK_EVENT) {
                HearingAidStackEvent event = (HearingAidStackEvent) message.obj;
                HearingAidStateMachine.this.log("Connected: stack event: " + event);
                if (!HearingAidStateMachine.this.mDevice.equals(event.device)) {
                    Log.wtf(HearingAidStateMachine.TAG, "Device(" + HearingAidStateMachine.this.mDevice + "): event mismatch: " + event);
                }
                if (event.type == 1) {
                    processConnectionEvent(event.valueInt1);
                } else {
                    Log.e(HearingAidStateMachine.TAG, "Connected: ignoring stack event: " + event);
                }
            } else {
                return false;
            }
            return true;
        }

        private void processConnectionEvent(int state) {
            if (state == 0) {
                Log.i(HearingAidStateMachine.TAG, "Disconnected from " + HearingAidStateMachine.this.mDevice + " but still in Whitelist");
                HearingAidStateMachine hearingAidStateMachine = HearingAidStateMachine.this;
                hearingAidStateMachine.transitionTo(hearingAidStateMachine.mDisconnected);
                return;
            }
            if (state == 3) {
                Log.i(HearingAidStateMachine.TAG, "Disconnecting from " + HearingAidStateMachine.this.mDevice);
                HearingAidStateMachine hearingAidStateMachine2 = HearingAidStateMachine.this;
                hearingAidStateMachine2.transitionTo(hearingAidStateMachine2.mDisconnecting);
                return;
            }
            Log.e(HearingAidStateMachine.TAG, "Connection State Device: " + HearingAidStateMachine.this.mDevice + " bad state: " + state);
        }
    }

    int getConnectionState() {
        return this.mConnectionState;
    }

    BluetoothDevice getDevice() {
        return this.mDevice;
    }

    synchronized boolean isConnected() {
        return getConnectionState() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConnectionState(int newState, int prevState) {
        log("Connection state " + this.mDevice + ": " + profileStateToString(prevState) + "->" + profileStateToString(newState));
        Intent intent = new Intent("android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", this.mDevice);
        intent.addFlags(83886080);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
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

    public void dump(StringBuilder sb) {
        ProfileService.println(sb, "mDevice: " + this.mDevice);
        ProfileService.println(sb, "  StateMachine: " + this);
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
    }
}
