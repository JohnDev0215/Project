package com.android.bluetooth.pbapclient;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.HandlerThread;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.UserManager;
import android.util.Log;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.pbapclient.PbapClientConnectionHandler;
import com.android.bluetooth.statemachine.IState;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class PbapClientStateMachine extends StateMachine {
    static final int CONNECT_TIMEOUT = 10000;
    private static final boolean DBG = true;
    static final int DISCONNECT_TIMEOUT = 3000;
    static final int MSG_CONNECTION_CLOSED = 7;
    static final int MSG_CONNECTION_COMPLETE = 5;
    static final int MSG_CONNECTION_FAILED = 6;
    private static final int MSG_CONNECT_TIMEOUT = 3;
    private static final int MSG_DISCONNECT = 2;
    private static final int MSG_DISCONNECT_TIMEOUT = 4;
    static final int MSG_ONCE_MORE_BOOK_DOWNLOAD = 9;
    static final int MSG_ONCE_MORE_CALLLOG_DOWNLOAD = 10;
    static final int MSG_RESUME_DOWNLOAD = 8;
    private static final int MSG_SDP_COMPLETE = 9;
    private static final String TAG = "PbapClientStateMachine";
    private State mConnected;
    private State mConnecting;
    private PbapClientConnectionHandler mConnectionHandler;
    private final BluetoothDevice mCurrentDevice;
    private State mDisconnected;
    private State mDisconnecting;
    private HandlerThread mHandlerThread;
    private final Object mLock;
    private int mMostRecentState;
    private int mScene;
    private PbapClientService mService;
    private UserManager mUserManager;

    PbapClientStateMachine(PbapClientService svc, BluetoothDevice device) {
        super(TAG);
        this.mHandlerThread = null;
        this.mUserManager = null;
        this.mMostRecentState = 0;
        this.mService = svc;
        this.mCurrentDevice = device;
        this.mLock = new Object();
        this.mUserManager = UserManager.get(this.mService);
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mDisconnecting = new Disconnecting();
        this.mConnected = new Connected();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mDisconnecting);
        addState(this.mConnected);
        setInitialState(this.mConnecting);
    }

    public void setScene(int scene) {
        Log.w(TAG, "setScene scene: " + scene);
        this.mScene = scene;
    }

    class Disconnected extends State {
        Disconnected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.d(PbapClientStateMachine.TAG, "Enter Disconnected: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine pbapClientStateMachine = PbapClientStateMachine.this;
            pbapClientStateMachine.onConnectionStateChanged(pbapClientStateMachine.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 0);
            PbapClientStateMachine.this.mMostRecentState = 0;
            PbapClientStateMachine.this.mScene = 0;
            PbapClientStateMachine.this.quit();
        }
    }

    class Connecting extends State {
        private SDPBroadcastReceiver mSdpReceiver;

        Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.d(PbapClientStateMachine.TAG, "Enter Connecting: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine pbapClientStateMachine = PbapClientStateMachine.this;
            pbapClientStateMachine.onConnectionStateChanged(pbapClientStateMachine.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 1);
            SDPBroadcastReceiver sDPBroadcastReceiver = new SDPBroadcastReceiver();
            this.mSdpReceiver = sDPBroadcastReceiver;
            sDPBroadcastReceiver.register();
            PbapClientStateMachine.this.mCurrentDevice.sdpSearch(BluetoothUuid.PBAP_PSE);
            PbapClientStateMachine.this.mMostRecentState = 1;
            PbapClientStateMachine.this.mHandlerThread = new HandlerThread("PBAP PCE handler", 10);
            PbapClientStateMachine.this.mHandlerThread.start();
            PbapClientStateMachine.this.mConnectionHandler = new PbapClientConnectionHandler.Builder().setLooper(PbapClientStateMachine.this.mHandlerThread.getLooper()).setContext(PbapClientStateMachine.this.mService).setClientSM(PbapClientStateMachine.this).setRemoteDevice(PbapClientStateMachine.this.mCurrentDevice).build();
            PbapClientStateMachine.this.sendMessageDelayed(3, 10000L);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x005d  */
        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r6) {
            /*
                r5 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Processing MSG "
                r0.append(r1)
                int r1 = r6.what
                r0.append(r1)
                java.lang.String r1 = " from "
                r0.append(r1)
                java.lang.String r1 = r5.getName()
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                java.lang.String r1 = "PbapClientStateMachine"
                android.util.Log.d(r1, r0)
                int r0 = r6.what
                r2 = 2
                r3 = 1
                r4 = 3
                if (r0 == r2) goto L6c
                if (r0 == r4) goto L5d
                r2 = 5
                if (r0 == r2) goto L4e
                r2 = 6
                if (r0 == r2) goto L5d
                r2 = 9
                if (r0 == r2) goto L3e
                java.lang.String r0 = "Received unexpected message while Connecting"
                android.util.Log.w(r1, r0)
                r0 = 0
                return r0
            L3e:
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.pbapclient.PbapClientConnectionHandler r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.access$600(r0)
                java.lang.Object r1 = r6.obj
                android.os.Message r0 = r0.obtainMessage(r3, r1)
                r0.sendToTarget()
                goto L8e
            L4e:
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.pbapclient.PbapClientStateMachine.access$1000(r0, r4)
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.statemachine.State r1 = com.android.bluetooth.pbapclient.PbapClientStateMachine.access$1100(r0)
                r0.transitionTo(r1)
                goto L8e
            L5d:
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.pbapclient.PbapClientStateMachine.access$1200(r0, r4)
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.statemachine.State r1 = com.android.bluetooth.pbapclient.PbapClientStateMachine.access$900(r0)
                r0.transitionTo(r1)
                goto L8e
            L6c:
                java.lang.Object r0 = r6.obj
                boolean r0 = r0 instanceof android.bluetooth.BluetoothDevice
                if (r0 == 0) goto L8e
                java.lang.Object r0 = r6.obj
                com.android.bluetooth.pbapclient.PbapClientStateMachine r1 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                android.bluetooth.BluetoothDevice r1 = com.android.bluetooth.pbapclient.PbapClientStateMachine.access$000(r1)
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L8e
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.pbapclient.PbapClientStateMachine.access$800(r0, r4)
                com.android.bluetooth.pbapclient.PbapClientStateMachine r0 = com.android.bluetooth.pbapclient.PbapClientStateMachine.this
                com.android.bluetooth.statemachine.State r1 = com.android.bluetooth.pbapclient.PbapClientStateMachine.access$900(r0)
                r0.transitionTo(r1)
            L8e:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.pbapclient.PbapClientStateMachine.Connecting.processMessage(android.os.Message):boolean");
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            this.mSdpReceiver.unregister();
            this.mSdpReceiver = null;
        }

        private class SDPBroadcastReceiver extends BroadcastReceiver {
            private SDPBroadcastReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.v(PbapClientStateMachine.TAG, "onReceive" + action);
                if (action.equals("android.bluetooth.device.action.SDP_RECORD")) {
                    BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                    if (!device.equals(PbapClientStateMachine.this.getDevice())) {
                        Log.w(PbapClientStateMachine.TAG, "SDP Record fetched for different device - Ignore");
                        return;
                    }
                    ParcelUuid uuid = (ParcelUuid) intent.getParcelableExtra("android.bluetooth.device.extra.UUID");
                    Log.v(PbapClientStateMachine.TAG, "Received UUID: " + uuid.toString());
                    Log.v(PbapClientStateMachine.TAG, "expected UUID: " + BluetoothUuid.PBAP_PSE.toString());
                    if (uuid.equals(BluetoothUuid.PBAP_PSE)) {
                        PbapClientStateMachine.this.sendMessage(9, intent.getParcelableExtra("android.bluetooth.device.extra.SDP_RECORD"));
                    }
                }
            }

            public void register() {
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.bluetooth.device.action.SDP_RECORD");
                PbapClientStateMachine.this.mService.registerReceiver(this, filter);
            }

            public void unregister() {
                PbapClientStateMachine.this.mService.unregisterReceiver(this);
            }
        }
    }

    class Disconnecting extends State {
        Disconnecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.d(PbapClientStateMachine.TAG, "Enter Disconnecting: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine pbapClientStateMachine = PbapClientStateMachine.this;
            pbapClientStateMachine.onConnectionStateChanged(pbapClientStateMachine.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 3);
            PbapClientStateMachine.this.mMostRecentState = 3;
            PbapClientStateMachine.this.mConnectionHandler.obtainMessage(2).sendToTarget();
            PbapClientStateMachine.this.sendMessageDelayed(4, 3000L);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            Log.d(PbapClientStateMachine.TAG, "Processing MSG " + message.what + " from " + getName());
            int i = message.what;
            if (i == 2) {
                PbapClientStateMachine.this.deferMessage(message);
                return true;
            }
            if (i == 4) {
                Log.w(PbapClientStateMachine.TAG, "Disconnect Timeout, Forcing");
                PbapClientStateMachine.this.mConnectionHandler.abort();
                PbapClientStateMachine pbapClientStateMachine = PbapClientStateMachine.this;
                pbapClientStateMachine.transitionTo(pbapClientStateMachine.mDisconnected);
                return true;
            }
            if (i != 7) {
                if (i != 8) {
                    Log.w(PbapClientStateMachine.TAG, "Received unexpected message while Disconnecting");
                    return false;
                }
                return true;
            }
            PbapClientStateMachine.this.removeMessages(4);
            PbapClientStateMachine.this.mHandlerThread.quitSafely();
            PbapClientStateMachine pbapClientStateMachine2 = PbapClientStateMachine.this;
            pbapClientStateMachine2.transitionTo(pbapClientStateMachine2.mDisconnected);
            return true;
        }
    }

    class Connected extends State {
        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            Log.d(PbapClientStateMachine.TAG, "Enter Connected: " + PbapClientStateMachine.this.getCurrentMessage().what);
            PbapClientStateMachine pbapClientStateMachine = PbapClientStateMachine.this;
            pbapClientStateMachine.onConnectionStateChanged(pbapClientStateMachine.mCurrentDevice, PbapClientStateMachine.this.mMostRecentState, 2);
            PbapClientStateMachine.this.mMostRecentState = 2;
            if (PbapClientStateMachine.this.mUserManager.isUserUnlocked()) {
                Message msg = PbapClientStateMachine.this.mConnectionHandler.obtainMessage(3);
                Log.d(PbapClientStateMachine.TAG, "Enter Connected mScene: " + PbapClientStateMachine.this.mScene);
                msg.arg1 = PbapClientStateMachine.this.mScene;
                msg.sendToTarget();
            }
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            Log.d(PbapClientStateMachine.TAG, "Processing MSG " + message.what + " from " + getName());
            int i = message.what;
            if (i != 2) {
                switch (i) {
                    case 8:
                        Message msg = PbapClientStateMachine.this.mConnectionHandler.obtainMessage(3);
                        Log.w(PbapClientStateMachine.TAG, "MSG_RESUME_DOWNLOAD mScene: " + PbapClientStateMachine.this.mScene);
                        msg.arg1 = PbapClientStateMachine.this.mScene;
                        msg.sendToTarget();
                        break;
                    case 9:
                        Message bookMsg = PbapClientStateMachine.this.mConnectionHandler.obtainMessage(4);
                        bookMsg.obj = PbapClientStateMachine.this.mCurrentDevice;
                        bookMsg.arg1 = 1;
                        bookMsg.sendToTarget();
                        break;
                    case 10:
                        Message callLogMsg = PbapClientStateMachine.this.mConnectionHandler.obtainMessage(4);
                        callLogMsg.obj = PbapClientStateMachine.this.mCurrentDevice;
                        callLogMsg.arg1 = 2;
                        callLogMsg.sendToTarget();
                        break;
                    default:
                        Log.w(PbapClientStateMachine.TAG, "Received unexpected message while Connected");
                        return false;
                }
            } else if ((message.obj instanceof BluetoothDevice) && ((BluetoothDevice) message.obj).equals(PbapClientStateMachine.this.mCurrentDevice)) {
                PbapClientStateMachine pbapClientStateMachine = PbapClientStateMachine.this;
                pbapClientStateMachine.transitionTo(pbapClientStateMachine.mDisconnecting);
            }
            return true;
        }
    }

    public void pullPhoneBook() {
        transitionTo(this.mConnected);
        sendMessage(9);
    }

    public void pullPhoneCallLog() {
        transitionTo(this.mConnected);
        sendMessage(10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectionStateChanged(BluetoothDevice device, int prevState, int state) {
        if (device == null) {
            Log.w(TAG, "onConnectionStateChanged with invalid device");
            return;
        }
        if (prevState != state && state == 2) {
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.PBAP_CLIENT);
        }
        Log.d(TAG, "Connection state " + device.getAddressOnlyforLog() + ": " + prevState + "->" + state);
        Intent intent = new Intent("android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", state);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addFlags(VCardConfig.FLAG_APPEND_TYPE_PARAM);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    public void disconnect(BluetoothDevice device) {
        Log.d(TAG, "Disconnect Request " + device.getAddressOnlyforLog());
        this.mScene = 0;
        sendMessage(2, device);
    }

    public void resumeDownload() {
        sendMessage(8);
    }

    void doQuit() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        quitNow();
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void onQuitting() {
        this.mService.cleanupDevice(this.mCurrentDevice);
    }

    public int getConnectionState() {
        IState currentState = getCurrentState();
        if (currentState instanceof Disconnected) {
            return 0;
        }
        if (currentState instanceof Connecting) {
            return 1;
        }
        if (currentState instanceof Connected) {
            return 2;
        }
        if (currentState instanceof Disconnecting) {
            return 3;
        }
        Log.w(TAG, "Unknown State");
        return 0;
    }

    public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        int clientState;
        BluetoothDevice currentDevice;
        synchronized (this.mLock) {
            clientState = getConnectionState();
            currentDevice = getDevice();
        }
        List<BluetoothDevice> deviceList = new ArrayList<>();
        for (int state : states) {
            if (clientState == state && currentDevice != null) {
                deviceList.add(currentDevice);
            }
        }
        return deviceList;
    }

    public int getConnectionState(BluetoothDevice device) {
        if (device == null) {
            return 0;
        }
        synchronized (this.mLock) {
            if (!device.equals(this.mCurrentDevice)) {
                return 0;
            }
            return getConnectionState();
        }
    }

    public BluetoothDevice getDevice() {
        if (getCurrentState() instanceof Disconnected) {
            return null;
        }
        return this.mCurrentDevice;
    }

    Context getContext() {
        return this.mService;
    }

    public void dump(StringBuilder sb) {
        ProfileService.println(sb, "mCurrentDevice: " + this.mCurrentDevice.getAddress() + "(" + this.mCurrentDevice.getName() + ") " + toString());
    }
}
