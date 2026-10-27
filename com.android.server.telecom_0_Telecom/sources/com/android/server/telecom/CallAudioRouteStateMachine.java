package com.android.server.telecom;

import android.app.ActivityManager;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.IAudioService;
import android.os.Binder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.telecom.CallAudioState;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.os.SomeArgs;
import com.android.internal.util.IState;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.State;
import com.android.internal.util.StateMachine;
import com.android.server.telecom.CallAudioManager;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.bluetooth.BluetoothRouteManager;
import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class CallAudioRouteStateMachine extends StateMachine {

    @VisibleForTesting
    public static final SparseArray<String> AUDIO_ROUTE_TO_LOG_EVENT = new SparseArray<String>() { // from class: com.android.server.telecom.CallAudioRouteStateMachine.1
        {
            put(2, "AUDIO_ROUTE_BT");
            put(1, "AUDIO_ROUTE_EARPIECE");
            put(8, "AUDIO_ROUTE_SPEAKER");
            put(4, "AUDIO_ROUTE_HEADSET");
        }
    };
    private static final SparseArray<String> MESSAGE_CODE_TO_NAME = new SparseArray<String>() { // from class: com.android.server.telecom.CallAudioRouteStateMachine.2
        {
            put(1, "CONNECT_WIRED_HEADSET");
            put(2, "DISCONNECT_WIRED_HEADSET");
            put(5, "CONNECT_DOCK");
            put(6, "DISCONNECT_DOCK");
            put(7, "BLUETOOTH_DEVICE_LIST_CHANGED");
            put(8, "BT_ACTIVE_DEVICE_PRESENT");
            put(9, "BT_ACTIVE_DEVICE_GONE");
            put(1001, "SWITCH_EARPIECE");
            put(1002, "SWITCH_BLUETOOTH");
            put(1003, "SWITCH_HEADSET");
            put(1004, "SWITCH_SPEAKER");
            put(1005, "SWITCH_BASELINE_ROUTE");
            put(1006, "SPEAKER_ON");
            put(1007, "SPEAKER_OFF");
            put(1101, "USER_SWITCH_EARPIECE");
            put(1102, "USER_SWITCH_BLUETOOTH");
            put(1103, "USER_SWITCH_HEADSET");
            put(1104, "USER_SWITCH_SPEAKER");
            put(1105, "USER_SWITCH_BASELINE_ROUTE");
            put(1201, "UPDATE_SYSTEM_AUDIO_ROUTE");
            put(1301, "BT_AUDIO_DISCONNECTED");
            put(1302, "BT_AUDIO_CONNECTED");
            put(1303, "BT_AUDIO_PENDING");
            put(3001, "MUTE_ON");
            put(3002, "MUTE_OFF");
            put(3003, "TOGGLE_MUTE");
            put(3004, "MUTE_EXTERNALLY_CHANGED");
            put(4001, "SWITCH_FOCUS");
            put(9001, "RUN_RUNNABLE");
        }
    };
    public static final String NAME = CallAudioRouteStateMachine.class.getName();
    private final ActiveBluetoothRoute mActiveBluetoothRoute;
    private final ActiveEarpieceRoute mActiveEarpieceRoute;
    private final ActiveHeadsetRoute mActiveHeadsetRoute;
    private final ActiveSpeakerRoute mActiveSpeakerRoute;
    private int mAudioFocusType;
    private final AudioManager mAudioManager;
    private final CallAudioManager.AudioServiceFactory mAudioServiceFactory;
    private int mAvailableRoutes;
    private final BluetoothRouteManager mBluetoothRouteManager;
    private CallAudioManager mCallAudioManager;
    private final CallsManager mCallsManager;
    private final Context mContext;
    private CallAudioState mCurrentCallAudioState;
    private int mDeviceSupportedRoutes;
    private boolean mDoesDeviceSupportEarpieceRoute;
    private boolean mHasUserExplicitlyLeftBluetooth;
    private boolean mIsMuted;
    private CallAudioState mLastKnownCallAudioState;
    private final TelecomSystem.SyncRoot mLock;
    private final BroadcastReceiver mMuteChangeReceiver;
    private final QuiescentBluetoothRoute mQuiescentBluetoothRoute;
    private final QuiescentEarpieceRoute mQuiescentEarpieceRoute;
    private final QuiescentHeadsetRoute mQuiescentHeadsetRoute;
    private final QuiescentSpeakerRoute mQuiescentSpeakerRoute;
    private final RingingBluetoothRoute mRingingBluetoothRoute;
    private HashMap<Integer, AudioState> mRouteCodeToQuiescentState;
    private final BroadcastReceiver mSpeakerPhoneChangeReceiver;
    private HashMap<String, Integer> mStateNameToRouteCode;
    private final StatusBarNotifier mStatusBarNotifier;
    private boolean mWasOnSpeaker;
    private final WiredHeadsetManager mWiredHeadsetManager;

    public static class Factory {
        public CallAudioRouteStateMachine create(Context context, CallsManager callsManager, BluetoothRouteManager bluetoothRouteManager, WiredHeadsetManager wiredHeadsetManager, StatusBarNotifier statusBarNotifier, CallAudioManager.AudioServiceFactory audioServiceFactory, int i) {
            return new CallAudioRouteStateMachine(context, callsManager, bluetoothRouteManager, wiredHeadsetManager, statusBarNotifier, audioServiceFactory, i);
        }
    }

    protected void onPreHandleMessage(Message message) {
        Object obj = message.obj;
        if (obj == null || !(obj instanceof SomeArgs)) {
            return;
        }
        Session session = (Session) ((SomeArgs) obj).arg1;
        String str = MESSAGE_CODE_TO_NAME.get(message.what, "unknown");
        Log.continueSession(session, "CARSM.pM_" + str);
        Log.i(this, "Message received: %s=%d, arg1=%d", new Object[]{str, Integer.valueOf(message.what), Integer.valueOf(message.arg1)});
    }

    protected void onPostHandleMessage(Message message) {
        Log.endSession();
        Object obj = message.obj;
        if (obj == null || !(obj instanceof SomeArgs)) {
            return;
        }
        ((SomeArgs) obj).recycle();
    }

    abstract class AudioState extends State {
        public abstract int getRouteCode();

        public abstract boolean isActive();

        public abstract void updateSystemAudioState();

        AudioState() {
        }

        public void enter() {
            super.enter();
            Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Entering state " + getName());
            if (isActive()) {
                Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), CallAudioRouteStateMachine.AUDIO_ROUTE_TO_LOG_EVENT.get(getRouteCode(), "AUDIO_ROUTE"));
            }
        }

        public void exit() {
            Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Leaving state " + getName());
            super.exit();
        }

        public boolean processMessage(Message message) {
            int i;
            boolean z;
            SparseArray sparseArray = CallAudioRouteStateMachine.MESSAGE_CODE_TO_NAME;
            int i2 = message.what;
            Log.i(this, "Processing message %s", new Object[]{sparseArray.get(i2, Integer.toString(i2))});
            int i3 = message.what;
            int i4 = 4;
            if (i3 != 1) {
                i = 2;
                if (i3 == 2) {
                    Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Wired headset disconnected");
                    i = 4;
                    if (CallAudioRouteStateMachine.this.mDoesDeviceSupportEarpieceRoute) {
                        z = false;
                        i4 = 1;
                    } else {
                        z = false;
                        i4 = 0;
                    }
                } else if (i3 != 7) {
                    if (i3 == 8) {
                        Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Bluetooth active device present");
                    } else {
                        if (i3 != 9) {
                            if (i3 == 1005) {
                                CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
                                callAudioRouteStateMachine.sendInternalMessage(callAudioRouteStateMachine.calculateBaselineRouteMessage(false, message.arg1 == 1));
                                return true;
                            }
                            if (i3 == 1102) {
                                CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth = false;
                                return false;
                            }
                            if (i3 == 1105) {
                                CallAudioRouteStateMachine callAudioRouteStateMachine2 = CallAudioRouteStateMachine.this;
                                callAudioRouteStateMachine2.sendInternalMessage(callAudioRouteStateMachine2.calculateBaselineRouteMessage(true, message.arg1 == 1));
                                return true;
                            }
                            if (i3 != 4001) {
                                return false;
                            }
                            if (CallAudioRouteStateMachine.this.mAudioFocusType == 1 || message.arg1 != 1) {
                                if (CallAudioRouteStateMachine.this.mAudioFocusType == 1 && message.arg1 != 1) {
                                    CallAudioRouteStateMachine.this.mBluetoothRouteManager.cacheHearingAidDevice();
                                }
                            } else {
                                CallAudioRouteStateMachine.this.mBluetoothRouteManager.restoreHearingAidDevice();
                            }
                            CallAudioRouteStateMachine.this.mAudioFocusType = message.arg1;
                            return false;
                        }
                        Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Bluetooth active device gone");
                    }
                    z = false;
                    i4 = 0;
                    i = 0;
                } else {
                    Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Bluetooth device list changed");
                    if (CallAudioRouteStateMachine.this.mBluetoothRouteManager.getConnectedDevices().size() > 0) {
                        i4 = 2;
                        i = 0;
                    } else {
                        i4 = 0;
                    }
                    z = true;
                }
            } else {
                Log.addEvent(CallAudioRouteStateMachine.this.mCallsManager.getForegroundCall(), "AUDIO_ROUTE", "Wired headset connected");
                i = 1;
                z = false;
            }
            if (i4 != 0 || i != 0 || message.what == 7) {
                CallAudioRouteStateMachine callAudioRouteStateMachine3 = CallAudioRouteStateMachine.this;
                callAudioRouteStateMachine3.mAvailableRoutes = callAudioRouteStateMachine3.modifyRoutes(callAudioRouteStateMachine3.mAvailableRoutes, i, i4, true);
                CallAudioRouteStateMachine callAudioRouteStateMachine4 = CallAudioRouteStateMachine.this;
                callAudioRouteStateMachine4.mDeviceSupportedRoutes = callAudioRouteStateMachine4.modifyRoutes(callAudioRouteStateMachine4.mDeviceSupportedRoutes, i, i4, false);
                updateSystemAudioState();
            }
            return z;
        }
    }

    class ActiveEarpieceRoute extends EarpieceRoute {
        public String getName() {
            return "ActiveEarpieceRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return true;
        }

        ActiveEarpieceRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.setSpeakerphoneOn(false);
            CallAudioRouteStateMachine.this.setSystemAudioState(new CallAudioState(CallAudioRouteStateMachine.this.mIsMuted, 1, CallAudioRouteStateMachine.this.mAvailableRoutes, null, CallAudioRouteStateMachine.this.mBluetoothRouteManager.getConnectedDevices()), true);
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
            CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
            callAudioRouteStateMachine.setSystemAudioState(callAudioRouteStateMachine.mCurrentCallAudioState);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0022  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x003e  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.EarpieceRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r5) {
            /*
                r4 = this;
                boolean r0 = super.processMessage(r5)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r5.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto La0
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L9f
                r2 = 1302(0x516, float:1.824E-42)
                if (r0 == r2) goto L96
                r2 = 4001(0xfa1, float:5.607E-42)
                if (r0 == r2) goto L83
                r2 = 0
                switch(r0) {
                    case 1001: goto L9f;
                    case 1002: goto L3e;
                    case 1003: goto L22;
                    case 1004: goto La0;
                    default: goto L1e;
                }
            L1e:
                switch(r0) {
                    case 1101: goto L9f;
                    case 1102: goto L3e;
                    case 1103: goto L22;
                    case 1104: goto La0;
                    default: goto L21;
                }
            L21:
                return r2
            L22:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r5)
                r5 = r5 & 4
                if (r5 == 0) goto L36
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveHeadsetRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$2000(r4)
                r4.transitionTo(r5)
                goto L3d
            L36:
                java.lang.Object[] r5 = new java.lang.Object[r2]
                java.lang.String r0 = "Ignoring switch to headset command. Not available."
                android.telecom.Log.w(r4, r0, r5)
            L3d:
                return r1
            L3e:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r0)
                r3 = 2
                r0 = r0 & r3
                if (r0 == 0) goto L7b
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$700(r0)
                if (r0 == r3) goto L67
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.bluetooth.BluetoothRouteManager r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$300(r0)
                boolean r0 = r0.isInbandRingingEnabled()
                if (r0 == 0) goto L5d
                goto L67
            L5d:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$RingingBluetoothRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$1900(r4)
                r4.transitionTo(r5)
                goto L82
            L67:
                java.lang.Object r5 = r5.obj
                boolean r0 = r5 instanceof com.android.internal.os.SomeArgs
                if (r0 == 0) goto L74
                com.android.internal.os.SomeArgs r5 = (com.android.internal.os.SomeArgs) r5
                java.lang.Object r5 = r5.arg2
                java.lang.String r5 = (java.lang.String) r5
                goto L75
            L74:
                r5 = 0
            L75:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$1800(r4, r5)
                goto L82
            L7b:
                java.lang.Object[] r5 = new java.lang.Object[r2]
                java.lang.String r0 = "Ignoring switch to bluetooth command. Not available."
                android.telecom.Log.w(r4, r0, r5)
            L82:
                return r1
            L83:
                int r5 = r5.arg1
                if (r5 != r1) goto L95
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$2200(r5)
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioManager r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2300(r4)
                r4.notifyAudioOperationsComplete()
            L95:
                return r1
            L96:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r4)
                r4.transitionTo(r5)
            L9f:
                return r1
            La0:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveSpeakerRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$2100(r4)
                r4.transitionTo(r5)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.ActiveEarpieceRoute.processMessage(android.os.Message):boolean");
        }
    }

    class QuiescentEarpieceRoute extends EarpieceRoute {
        public String getName() {
            return "QuiescentEarpieceRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return false;
        }

        QuiescentEarpieceRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth = false;
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0049  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.EarpieceRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r6) {
            /*
                r5 = this;
                boolean r0 = super.processMessage(r6)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r6.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto L85
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L85
                r2 = 1302(0x516, float:1.824E-42)
                r3 = 0
                if (r0 == r2) goto L75
                r2 = 4001(0xfa1, float:5.607E-42)
                r4 = 2
                if (r0 == r2) goto L64
                switch(r0) {
                    case 1001: goto L85;
                    case 1002: goto L49;
                    case 1003: goto L2d;
                    case 1004: goto L23;
                    default: goto L1f;
                }
            L1f:
                switch(r0) {
                    case 1101: goto L85;
                    case 1102: goto L49;
                    case 1103: goto L2d;
                    case 1104: goto L23;
                    default: goto L22;
                }
            L22:
                return r3
            L23:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentSpeakerRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2600(r5)
                r5.transitionTo(r6)
                return r1
            L2d:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & 4
                if (r6 == 0) goto L41
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentHeadsetRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2500(r5)
                r5.transitionTo(r6)
                goto L48
            L41:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to headset command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L48:
                return r1
            L49:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & r4
                if (r6 == 0) goto L5c
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentBluetoothRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2400(r5)
                r5.transitionTo(r6)
                goto L63
            L5c:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to bluetooth command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L63:
                return r1
            L64:
                int r6 = r6.arg1
                if (r6 == r4) goto L6b
                r0 = 3
                if (r6 != r0) goto L74
            L6b:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveEarpieceRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2700(r5)
                r5.transitionTo(r6)
            L74:
                return r1
            L75:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "BT Audio came on in quiescent earpiece route."
                android.telecom.Log.w(r5, r0, r6)
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r5)
                r5.transitionTo(r6)
            L85:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.QuiescentEarpieceRoute.processMessage(android.os.Message):boolean");
        }
    }

    abstract class EarpieceRoute extends AudioState {
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public int getRouteCode() {
            return 1;
        }

        EarpieceRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            int i = message.what;
            if (i == 1) {
                CallAudioRouteStateMachine.this.sendInternalMessage(1003);
                return true;
            }
            if (i == 2) {
                Log.e(this, new IllegalStateException(), "Wired headset should not go from connected to not when on earpiece", new Object[0]);
                return true;
            }
            if (i == 5) {
                CallAudioRouteStateMachine.this.sendInternalMessage(1004);
                return true;
            }
            if (i != 6) {
                if (i != 8) {
                    return i == 9 || i == 1301;
                }
                if (!CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth) {
                    CallAudioRouteStateMachine.this.sendInternalMessage(1002);
                } else {
                    Log.i(this, "Not switching to BT route from earpiece because user has explicitly disconnected.", new Object[0]);
                }
            }
            return true;
        }
    }

    class ActiveHeadsetRoute extends HeadsetRoute {
        public String getName() {
            return "ActiveHeadsetRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return true;
        }

        ActiveHeadsetRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.setSpeakerphoneOn(false);
            CallAudioRouteStateMachine.this.setSystemAudioState(new CallAudioState(CallAudioRouteStateMachine.this.mIsMuted, 4, CallAudioRouteStateMachine.this.mAvailableRoutes, null, CallAudioRouteStateMachine.this.mBluetoothRouteManager.getConnectedDevices()), true);
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
            CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
            callAudioRouteStateMachine.setSystemAudioState(callAudioRouteStateMachine.mCurrentCallAudioState);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0022  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0067  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.HeadsetRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r5) {
            /*
                r4 = this;
                boolean r0 = super.processMessage(r5)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r5.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto L9f
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L9e
                r2 = 1302(0x516, float:1.824E-42)
                if (r0 == r2) goto L95
                r2 = 4001(0xfa1, float:5.607E-42)
                if (r0 == r2) goto L82
                r2 = 0
                switch(r0) {
                    case 1001: goto L67;
                    case 1002: goto L22;
                    case 1003: goto L9e;
                    case 1004: goto L9f;
                    default: goto L1e;
                }
            L1e:
                switch(r0) {
                    case 1101: goto L67;
                    case 1102: goto L22;
                    case 1103: goto L9e;
                    case 1104: goto L9f;
                    default: goto L21;
                }
            L21:
                return r2
            L22:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r0)
                r3 = 2
                r0 = r0 & r3
                if (r0 == 0) goto L5f
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$700(r0)
                if (r0 == r3) goto L4b
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.bluetooth.BluetoothRouteManager r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$300(r0)
                boolean r0 = r0.isInbandRingingEnabled()
                if (r0 == 0) goto L41
                goto L4b
            L41:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$RingingBluetoothRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$1900(r4)
                r4.transitionTo(r5)
                goto L66
            L4b:
                java.lang.Object r5 = r5.obj
                boolean r0 = r5 instanceof com.android.internal.os.SomeArgs
                if (r0 == 0) goto L58
                com.android.internal.os.SomeArgs r5 = (com.android.internal.os.SomeArgs) r5
                java.lang.Object r5 = r5.arg2
                java.lang.String r5 = (java.lang.String) r5
                goto L59
            L58:
                r5 = 0
            L59:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$1800(r4, r5)
                goto L66
            L5f:
                java.lang.Object[] r5 = new java.lang.Object[r2]
                java.lang.String r0 = "Ignoring switch to bluetooth command. Not available."
                android.telecom.Log.w(r4, r0, r5)
            L66:
                return r1
            L67:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r5)
                r5 = r5 & r1
                if (r5 == 0) goto L7a
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveEarpieceRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$2700(r4)
                r4.transitionTo(r5)
                goto L81
            L7a:
                java.lang.Object[] r5 = new java.lang.Object[r2]
                java.lang.String r0 = "Ignoring switch to earpiece command. Not available."
                android.telecom.Log.w(r4, r0, r5)
            L81:
                return r1
            L82:
                int r5 = r5.arg1
                if (r5 != r1) goto L94
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$2200(r5)
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioManager r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2300(r4)
                r4.notifyAudioOperationsComplete()
            L94:
                return r1
            L95:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r4)
                r4.transitionTo(r5)
            L9e:
                return r1
            L9f:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveSpeakerRoute r5 = com.android.server.telecom.CallAudioRouteStateMachine.access$2100(r4)
                r4.transitionTo(r5)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.ActiveHeadsetRoute.processMessage(android.os.Message):boolean");
        }
    }

    class QuiescentHeadsetRoute extends HeadsetRoute {
        public String getName() {
            return "QuiescentHeadsetRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return false;
        }

        QuiescentHeadsetRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth = false;
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x002d  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.HeadsetRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r6) {
            /*
                r5 = this;
                boolean r0 = super.processMessage(r6)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r6.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto L84
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L84
                r2 = 1302(0x516, float:1.824E-42)
                r3 = 0
                if (r0 == r2) goto L74
                r2 = 4001(0xfa1, float:5.607E-42)
                r4 = 2
                if (r0 == r2) goto L63
                switch(r0) {
                    case 1001: goto L48;
                    case 1002: goto L2d;
                    case 1003: goto L84;
                    case 1004: goto L23;
                    default: goto L1f;
                }
            L1f:
                switch(r0) {
                    case 1101: goto L48;
                    case 1102: goto L2d;
                    case 1103: goto L84;
                    case 1104: goto L23;
                    default: goto L22;
                }
            L22:
                return r3
            L23:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentSpeakerRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2600(r5)
                r5.transitionTo(r6)
                return r1
            L2d:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & r4
                if (r6 == 0) goto L40
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentBluetoothRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2400(r5)
                r5.transitionTo(r6)
                goto L47
            L40:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to bluetooth command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L47:
                return r1
            L48:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & r1
                if (r6 == 0) goto L5b
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentEarpieceRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2800(r5)
                r5.transitionTo(r6)
                goto L62
            L5b:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to earpiece command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L62:
                return r1
            L63:
                int r6 = r6.arg1
                if (r6 == r4) goto L6a
                r0 = 3
                if (r6 != r0) goto L73
            L6a:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveHeadsetRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2000(r5)
                r5.transitionTo(r6)
            L73:
                return r1
            L74:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r6)
                r6.transitionTo(r0)
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "BT Audio came on in quiescent headset route."
                android.telecom.Log.w(r5, r0, r6)
            L84:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.QuiescentHeadsetRoute.processMessage(android.os.Message):boolean");
        }
    }

    abstract class HeadsetRoute extends AudioState {
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public int getRouteCode() {
            return 4;
        }

        HeadsetRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            int i = message.what;
            if (i == 1) {
                Log.e(this, new IllegalStateException(), "Wired headset should already be connected.", new Object[0]);
                return true;
            }
            if (i == 2) {
                if (CallAudioRouteStateMachine.this.mWasOnSpeaker) {
                    CallAudioRouteStateMachine.this.sendInternalMessage(1004);
                } else {
                    CallAudioRouteStateMachine.this.sendInternalMessage(1005, 1);
                }
                return true;
            }
            if (i != 5 && i != 6) {
                if (i != 8) {
                    return i == 9 || i == 1301;
                }
                if (!CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth) {
                    CallAudioRouteStateMachine.this.sendInternalMessage(1002);
                } else {
                    Log.i(this, "Not switching to BT route from headset because user has explicitly disconnected.", new Object[0]);
                }
            }
            return true;
        }
    }

    class ActiveBluetoothRoute extends BluetoothRoute {
        public String getName() {
            return "ActiveBluetoothRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return true;
        }

        ActiveBluetoothRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.setSpeakerphoneOn(false);
            CallAudioRouteStateMachine.this.setSystemAudioState(new CallAudioState(CallAudioRouteStateMachine.this.mIsMuted, 2, CallAudioRouteStateMachine.this.mAvailableRoutes, CallAudioRouteStateMachine.this.mBluetoothRouteManager.getBluetoothAudioConnectedDevice(), CallAudioRouteStateMachine.this.mBluetoothRouteManager.getConnectedDevices()), true);
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
            if (CallAudioRouteStateMachine.this.mBluetoothRouteManager.getBluetoothAudioConnectedDevice() != null) {
                CallAudioRouteStateMachine.this.mCallAudioManager.onRingerModeChange();
            }
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
            CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
            callAudioRouteStateMachine.setSystemAudioState(callAudioRouteStateMachine.mCurrentCallAudioState);
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.BluetoothRoute
        public void handleBtInitiatedDisconnect() {
            int iCalculateBaselineRouteMessage = CallAudioRouteStateMachine.this.calculateBaselineRouteMessage(false, false);
            if (iCalculateBaselineRouteMessage == 1001) {
                CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
                callAudioRouteStateMachine.transitionTo(callAudioRouteStateMachine.mActiveEarpieceRoute);
                return;
            }
            if (iCalculateBaselineRouteMessage == 1003) {
                CallAudioRouteStateMachine callAudioRouteStateMachine2 = CallAudioRouteStateMachine.this;
                callAudioRouteStateMachine2.transitionTo(callAudioRouteStateMachine2.mActiveHeadsetRoute);
            } else {
                if (iCalculateBaselineRouteMessage == 1004) {
                    CallAudioRouteStateMachine callAudioRouteStateMachine3 = CallAudioRouteStateMachine.this;
                    callAudioRouteStateMachine3.transitionTo(callAudioRouteStateMachine3.mActiveSpeakerRoute);
                    return;
                }
                Log.w(this, "Got unexpected code " + iCalculateBaselineRouteMessage + " when processing a BT-initiated audio disconnect", new Object[0]);
                super.handleBtInitiatedDisconnect();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0043  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0077  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0086  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.BluetoothRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r4) {
            /*
                Method dump skipped, instruction units count: 256
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.ActiveBluetoothRoute.processMessage(android.os.Message):boolean");
        }
    }

    class RingingBluetoothRoute extends BluetoothRoute {
        public String getName() {
            return "RingingBluetoothRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return false;
        }

        RingingBluetoothRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.setSpeakerphoneOn(false);
            CallAudioRouteStateMachine.this.setSystemAudioState(new CallAudioState(CallAudioRouteStateMachine.this.mIsMuted, 2, CallAudioRouteStateMachine.this.mAvailableRoutes, CallAudioRouteStateMachine.this.mBluetoothRouteManager.getBluetoothAudioConnectedDevice(), CallAudioRouteStateMachine.this.mBluetoothRouteManager.getConnectedDevices()));
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
            CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
            callAudioRouteStateMachine.setSystemAudioState(callAudioRouteStateMachine.mCurrentCallAudioState);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0053 A[FALL_THROUGH, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005d  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0067  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.BluetoothRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r4) {
            /*
                r3 = this;
                boolean r0 = super.processMessage(r4)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r4.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto L96
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L95
                r2 = 1301(0x515, float:1.823E-42)
                if (r0 == r2) goto L95
                r2 = 1302(0x516, float:1.824E-42)
                if (r0 == r2) goto L8c
                r2 = 4001(0xfa1, float:5.607E-42)
                if (r0 == r2) goto L6f
                r4 = 0
                switch(r0) {
                    case 1001: goto L54;
                    case 1002: goto L53;
                    case 1003: goto L38;
                    case 1004: goto L96;
                    default: goto L22;
                }
            L22:
                switch(r0) {
                    case 1101: goto L32;
                    case 1102: goto L53;
                    case 1103: goto L2c;
                    case 1104: goto L26;
                    default: goto L25;
                }
            L25:
                return r4
            L26:
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$602(r4, r1)
                goto L96
            L2c:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$602(r0, r1)
                goto L38
            L32:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$602(r0, r1)
                goto L54
            L38:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r0)
                r0 = r0 & 4
                if (r0 == 0) goto L4c
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveHeadsetRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2000(r3)
                r3.transitionTo(r4)
                goto L53
            L4c:
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.String r0 = "Ignoring switch to headset command. Not available."
                android.telecom.Log.w(r3, r0, r4)
            L53:
                return r1
            L54:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r0)
                r0 = r0 & r1
                if (r0 == 0) goto L67
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveEarpieceRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2700(r3)
                r3.transitionTo(r4)
                goto L6e
            L67:
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.String r0 = "Ignoring switch to earpiece command. Not available."
                android.telecom.Log.w(r3, r0, r4)
            L6e:
                return r1
            L6f:
                int r4 = r4.arg1
                if (r4 != r1) goto L82
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$2200(r4)
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioManager r3 = com.android.server.telecom.CallAudioRouteStateMachine.access$2300(r3)
                r3.notifyAudioOperationsComplete()
                goto L8b
            L82:
                r0 = 2
                if (r4 != r0) goto L8b
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                r4 = 0
                com.android.server.telecom.CallAudioRouteStateMachine.access$1800(r3, r4)
            L8b:
                return r1
            L8c:
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r3)
                r3.transitionTo(r4)
            L95:
                return r1
            L96:
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveSpeakerRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2100(r3)
                r3.transitionTo(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.RingingBluetoothRoute.processMessage(android.os.Message):boolean");
        }
    }

    class QuiescentBluetoothRoute extends BluetoothRoute {
        public String getName() {
            return "QuiescentBluetoothRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return false;
        }

        QuiescentBluetoothRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth = false;
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0026  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0030  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.BluetoothRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r4) {
            /*
                r3 = this;
                boolean r0 = super.processMessage(r4)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r4.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto L9b
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L9b
                r2 = 1301(0x515, float:1.823E-42)
                if (r0 == r2) goto L9b
                r2 = 1302(0x516, float:1.824E-42)
                if (r0 == r2) goto L92
                r2 = 4001(0xfa1, float:5.607E-42)
                if (r0 == r2) goto L67
                r4 = 0
                switch(r0) {
                    case 1001: goto L4c;
                    case 1002: goto L9b;
                    case 1003: goto L30;
                    case 1004: goto L26;
                    default: goto L22;
                }
            L22:
                switch(r0) {
                    case 1101: goto L4c;
                    case 1102: goto L9b;
                    case 1103: goto L30;
                    case 1104: goto L26;
                    default: goto L25;
                }
            L25:
                return r4
            L26:
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentSpeakerRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2600(r3)
                r3.transitionTo(r4)
                return r1
            L30:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r0)
                r0 = r0 & 4
                if (r0 == 0) goto L44
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentHeadsetRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2500(r3)
                r3.transitionTo(r4)
                goto L4b
            L44:
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.String r0 = "Ignoring switch to headset command. Not available."
                android.telecom.Log.w(r3, r0, r4)
            L4b:
                return r1
            L4c:
                com.android.server.telecom.CallAudioRouteStateMachine r0 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r0)
                r0 = r0 & r1
                if (r0 == 0) goto L5f
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentEarpieceRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$2800(r3)
                r3.transitionTo(r4)
                goto L66
            L5f:
                java.lang.Object[] r4 = new java.lang.Object[r4]
                java.lang.String r0 = "Ignoring switch to earpiece command. Not available."
                android.telecom.Log.w(r3, r0, r4)
            L66:
                return r1
            L67:
                int r4 = r4.arg1
                r0 = 2
                r2 = 0
                if (r4 != r0) goto L73
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$1800(r3, r2)
                goto L91
            L73:
                r0 = 3
                if (r4 != r0) goto L91
                com.android.server.telecom.CallAudioRouteStateMachine r4 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.bluetooth.BluetoothRouteManager r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$300(r4)
                boolean r4 = r4.isInbandRingingEnabled()
                if (r4 == 0) goto L88
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine.access$1800(r3, r2)
                goto L91
            L88:
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$RingingBluetoothRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$1900(r3)
                r3.transitionTo(r4)
            L91:
                return r1
            L92:
                com.android.server.telecom.CallAudioRouteStateMachine r3 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r4 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r3)
                r3.transitionTo(r4)
            L9b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.QuiescentBluetoothRoute.processMessage(android.os.Message):boolean");
        }
    }

    abstract class BluetoothRoute extends AudioState {
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public int getRouteCode() {
            return 2;
        }

        BluetoothRoute() {
            super();
        }

        public void handleBtInitiatedDisconnect() {
            CallAudioRouteStateMachine.this.sendInternalMessage(1005, 0);
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            int i = message.what;
            if (i == 1) {
                CallAudioRouteStateMachine.this.sendInternalMessage(1003);
                return true;
            }
            if (i != 2 && i != 5 && i != 6) {
                if (i != 8) {
                    if (i != 9) {
                        return false;
                    }
                    handleBtInitiatedDisconnect();
                    CallAudioRouteStateMachine.this.mWasOnSpeaker = false;
                    return true;
                }
                Log.w(this, "Bluetooth active device should not have been null while we were in BT route.", new Object[0]);
            }
            return true;
        }
    }

    class ActiveSpeakerRoute extends SpeakerRoute {
        public String getName() {
            return "ActiveSpeakerRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return true;
        }

        ActiveSpeakerRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.mWasOnSpeaker = true;
            CallAudioRouteStateMachine.this.setSpeakerphoneOn(true);
            CallAudioRouteStateMachine.this.setSystemAudioState(new CallAudioState(CallAudioRouteStateMachine.this.mIsMuted, 8, CallAudioRouteStateMachine.this.mAvailableRoutes, null, CallAudioRouteStateMachine.this.mBluetoothRouteManager.getConnectedDevices()), true);
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
            CallAudioRouteStateMachine callAudioRouteStateMachine = CallAudioRouteStateMachine.this;
            callAudioRouteStateMachine.setSystemAudioState(callAudioRouteStateMachine.mCurrentCallAudioState);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0034 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x008e  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x009f  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00a9  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.SpeakerRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r5) {
            /*
                Method dump skipped, instruction units count: 238
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.ActiveSpeakerRoute.processMessage(android.os.Message):boolean");
        }
    }

    class QuiescentSpeakerRoute extends SpeakerRoute {
        public String getName() {
            return "QuiescentSpeakerRoute";
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean isActive() {
            return false;
        }

        QuiescentSpeakerRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void enter() {
            super.enter();
            CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth = false;
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public void updateSystemAudioState() {
            CallAudioRouteStateMachine.this.updateInternalCallAudioState();
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.SpeakerRoute, com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r6) {
            /*
                r5 = this;
                boolean r0 = super.processMessage(r6)
                r1 = 1
                if (r0 != r1) goto L8
                return r1
            L8:
                int r0 = r6.what
                r2 = 1006(0x3ee, float:1.41E-42)
                if (r0 == r2) goto L9e
                r2 = 1007(0x3ef, float:1.411E-42)
                if (r0 == r2) goto L97
                r2 = 1302(0x516, float:1.824E-42)
                r3 = 0
                if (r0 == r2) goto L86
                r2 = 4001(0xfa1, float:5.607E-42)
                r4 = 2
                if (r0 == r2) goto L75
                switch(r0) {
                    case 1001: goto L5a;
                    case 1002: goto L3f;
                    case 1003: goto L23;
                    case 1004: goto L9e;
                    default: goto L1f;
                }
            L1f:
                switch(r0) {
                    case 1101: goto L5a;
                    case 1102: goto L3f;
                    case 1103: goto L23;
                    case 1104: goto L9e;
                    default: goto L22;
                }
            L22:
                return r3
            L23:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & 4
                if (r6 == 0) goto L37
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentHeadsetRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2500(r5)
                r5.transitionTo(r6)
                goto L3e
            L37:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to headset command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L3e:
                return r1
            L3f:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & r4
                if (r6 == 0) goto L52
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentBluetoothRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2400(r5)
                r5.transitionTo(r6)
                goto L59
            L52:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to bluetooth command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L59:
                return r1
            L5a:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                int r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$800(r6)
                r6 = r6 & r1
                if (r6 == 0) goto L6d
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$QuiescentEarpieceRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2800(r5)
                r5.transitionTo(r6)
                goto L74
            L6d:
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "Ignoring switch to earpiece command. Not available."
                android.telecom.Log.w(r5, r0, r6)
            L74:
                return r1
            L75:
                int r6 = r6.arg1
                if (r6 == r4) goto L7c
                r0 = 3
                if (r6 != r0) goto L85
            L7c:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveSpeakerRoute r6 = com.android.server.telecom.CallAudioRouteStateMachine.access$2100(r5)
                r5.transitionTo(r6)
            L85:
                return r1
            L86:
                com.android.server.telecom.CallAudioRouteStateMachine r6 = com.android.server.telecom.CallAudioRouteStateMachine.this
                com.android.server.telecom.CallAudioRouteStateMachine$ActiveBluetoothRoute r0 = com.android.server.telecom.CallAudioRouteStateMachine.access$1700(r6)
                r6.transitionTo(r0)
                java.lang.Object[] r6 = new java.lang.Object[r3]
                java.lang.String r0 = "BT audio reported as connected while in quiescent speaker"
                android.telecom.Log.w(r5, r0, r6)
                return r1
            L97:
                com.android.server.telecom.CallAudioRouteStateMachine r5 = com.android.server.telecom.CallAudioRouteStateMachine.this
                r6 = 1005(0x3ed, float:1.408E-42)
                com.android.server.telecom.CallAudioRouteStateMachine.access$3000(r5, r6, r1)
            L9e:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.CallAudioRouteStateMachine.QuiescentSpeakerRoute.processMessage(android.os.Message):boolean");
        }
    }

    abstract class SpeakerRoute extends AudioState {
        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public int getRouteCode() {
            return 8;
        }

        SpeakerRoute() {
            super();
        }

        @Override // com.android.server.telecom.CallAudioRouteStateMachine.AudioState
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            int i = message.what;
            if (i == 1) {
                CallAudioRouteStateMachine.this.sendInternalMessage(1003);
                return true;
            }
            if (i != 2 && i != 5) {
                if (i != 6) {
                    if (i != 8) {
                        return i == 9 || i == 1301;
                    }
                    if (!CallAudioRouteStateMachine.this.mHasUserExplicitlyLeftBluetooth) {
                        CallAudioRouteStateMachine.this.sendInternalMessage(1002);
                    } else {
                        Log.i(this, "Not switching to BT route from speaker because user has explicitly disconnected.", new Object[0]);
                    }
                    return true;
                }
                CallAudioRouteStateMachine.this.sendInternalMessage(1005, 1);
            }
            return true;
        }
    }

    public CallAudioRouteStateMachine(Context context, CallsManager callsManager, BluetoothRouteManager bluetoothRouteManager, WiredHeadsetManager wiredHeadsetManager, StatusBarNotifier statusBarNotifier, CallAudioManager.AudioServiceFactory audioServiceFactory, int i) {
        super(NAME);
        this.mMuteChangeReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.CallAudioRouteStateMachine.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.startSession("CARSM.mCR");
                try {
                    if ("android.media.action.MICROPHONE_MUTE_CHANGED".equals(intent.getAction())) {
                        if (!CallAudioRouteStateMachine.this.mCallsManager.isInEmergencyCall()) {
                            CallAudioRouteStateMachine.this.sendInternalMessage(3004);
                        } else {
                            Log.i(this, "Mute was externally changed when there's an emergency call. Forcing mute back off.", new Object[0]);
                            CallAudioRouteStateMachine.this.sendInternalMessage(3002);
                        }
                    } else {
                        Log.w(this, "Received non-mute-change intent", new Object[0]);
                    }
                } finally {
                    Log.endSession();
                }
            }
        };
        this.mSpeakerPhoneChangeReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.CallAudioRouteStateMachine.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                Log.startSession("CARSM.mSPCR");
                try {
                    if ("android.media.action.SPEAKERPHONE_STATE_CHANGED".equals(intent.getAction())) {
                        if (CallAudioRouteStateMachine.this.mAudioManager != null) {
                            if (CallAudioRouteStateMachine.this.mAudioManager.isSpeakerphoneOn()) {
                                CallAudioRouteStateMachine.this.sendInternalMessage(1006);
                            } else {
                                CallAudioRouteStateMachine.this.sendInternalMessage(1007);
                            }
                        }
                    } else {
                        Log.w(this, "Received non-speakerphone-change intent", new Object[0]);
                    }
                } finally {
                    Log.endSession();
                }
            }
        };
        this.mActiveEarpieceRoute = new ActiveEarpieceRoute();
        this.mActiveHeadsetRoute = new ActiveHeadsetRoute();
        this.mActiveBluetoothRoute = new ActiveBluetoothRoute();
        this.mActiveSpeakerRoute = new ActiveSpeakerRoute();
        this.mRingingBluetoothRoute = new RingingBluetoothRoute();
        this.mQuiescentEarpieceRoute = new QuiescentEarpieceRoute();
        this.mQuiescentHeadsetRoute = new QuiescentHeadsetRoute();
        this.mQuiescentBluetoothRoute = new QuiescentBluetoothRoute();
        this.mQuiescentSpeakerRoute = new QuiescentSpeakerRoute();
        this.mAudioFocusType = 1;
        this.mHasUserExplicitlyLeftBluetooth = false;
        this.mContext = context;
        this.mCallsManager = callsManager;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mBluetoothRouteManager = bluetoothRouteManager;
        this.mWiredHeadsetManager = wiredHeadsetManager;
        this.mStatusBarNotifier = statusBarNotifier;
        this.mAudioServiceFactory = audioServiceFactory;
        this.mLock = callsManager.getLock();
        createStates(i);
    }

    private void createStates(int i) {
        if (i == 0) {
            this.mDoesDeviceSupportEarpieceRoute = false;
        } else if (i == 1) {
            this.mDoesDeviceSupportEarpieceRoute = true;
        } else {
            this.mDoesDeviceSupportEarpieceRoute = checkForEarpieceSupport();
        }
        addState(this.mActiveEarpieceRoute);
        addState(this.mActiveHeadsetRoute);
        addState(this.mActiveBluetoothRoute);
        addState(this.mActiveSpeakerRoute);
        addState(this.mRingingBluetoothRoute);
        addState(this.mQuiescentEarpieceRoute);
        addState(this.mQuiescentHeadsetRoute);
        addState(this.mQuiescentBluetoothRoute);
        addState(this.mQuiescentSpeakerRoute);
        HashMap<String, Integer> map = new HashMap<>(8);
        this.mStateNameToRouteCode = map;
        map.put(this.mQuiescentEarpieceRoute.getName(), 1);
        this.mStateNameToRouteCode.put(this.mQuiescentBluetoothRoute.getName(), 2);
        this.mStateNameToRouteCode.put(this.mQuiescentHeadsetRoute.getName(), 4);
        this.mStateNameToRouteCode.put(this.mQuiescentSpeakerRoute.getName(), 8);
        this.mStateNameToRouteCode.put(this.mRingingBluetoothRoute.getName(), 2);
        this.mStateNameToRouteCode.put(this.mActiveEarpieceRoute.getName(), 1);
        this.mStateNameToRouteCode.put(this.mActiveBluetoothRoute.getName(), 2);
        this.mStateNameToRouteCode.put(this.mActiveHeadsetRoute.getName(), 4);
        this.mStateNameToRouteCode.put(this.mActiveSpeakerRoute.getName(), 8);
        HashMap<Integer, AudioState> map2 = new HashMap<>(4);
        this.mRouteCodeToQuiescentState = map2;
        map2.put(1, this.mQuiescentEarpieceRoute);
        this.mRouteCodeToQuiescentState.put(2, this.mQuiescentBluetoothRoute);
        this.mRouteCodeToQuiescentState.put(8, this.mQuiescentSpeakerRoute);
        this.mRouteCodeToQuiescentState.put(4, this.mQuiescentHeadsetRoute);
    }

    public void setCallAudioManager(CallAudioManager callAudioManager) {
        this.mCallAudioManager = callAudioManager;
    }

    public void initialize() {
        initialize(getInitialAudioState());
    }

    public void initialize(CallAudioState callAudioState) {
        if ((callAudioState.getRoute() & getCurrentCallSupportedRoutes()) == 0) {
            Log.e(this, new IllegalArgumentException(), "Route %d specified when supported call routes are: %d", new Object[]{Integer.valueOf(callAudioState.getRoute()), Integer.valueOf(getCurrentCallSupportedRoutes())});
        }
        this.mCurrentCallAudioState = callAudioState;
        this.mLastKnownCallAudioState = callAudioState;
        int supportedRouteMask = callAudioState.getSupportedRouteMask();
        this.mDeviceSupportedRoutes = supportedRouteMask;
        this.mAvailableRoutes = supportedRouteMask & getCurrentCallSupportedRoutes();
        this.mIsMuted = callAudioState.isMuted();
        this.mWasOnSpeaker = false;
        this.mContext.registerReceiver(this.mMuteChangeReceiver, new IntentFilter("android.media.action.MICROPHONE_MUTE_CHANGED"));
        this.mContext.registerReceiver(this.mSpeakerPhoneChangeReceiver, new IntentFilter("android.media.action.SPEAKERPHONE_STATE_CHANGED"));
        this.mStatusBarNotifier.notifyMute(callAudioState.isMuted());
        this.mStatusBarNotifier.notifySpeakerphone(callAudioState.getRoute() == 8);
        setInitialState(this.mRouteCodeToQuiescentState.get(Integer.valueOf(callAudioState.getRoute())));
        start();
    }

    public CallAudioState getCurrentCallAudioState() {
        return this.mCurrentCallAudioState;
    }

    public void sendMessageWithSessionInfo(int i, int i2) {
        sendMessageWithSessionInfo(i, i2, null);
    }

    public void sendMessageWithSessionInfo(int i) {
        sendMessageWithSessionInfo(i, 0, null);
    }

    public void sendMessageWithSessionInfo(int i, int i2, String str) {
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = Log.createSubsession();
        someArgsObtain.arg2 = str;
        sendMessage(i, i2, 0, someArgsObtain);
    }

    protected void unhandledMessage(Message message) {
        int i = message.what;
        if (i == 1201) {
            updateInternalCallAudioState();
            updateRouteForForegroundCall();
            resendSystemAudioState();
            return;
        }
        if (i != 9001) {
            switch (i) {
                case 3001:
                    setMuteOn(true);
                    updateSystemMuteState();
                    break;
                case 3002:
                    setMuteOn(false);
                    updateSystemMuteState();
                    break;
                case 3003:
                    if (this.mIsMuted) {
                        sendInternalMessage(3002);
                    } else {
                        sendInternalMessage(3001);
                    }
                    break;
                case 3004:
                    this.mIsMuted = this.mAudioManager.isMicrophoneMute();
                    if (isInActiveState()) {
                        updateSystemMuteState();
                    }
                    break;
                default:
                    Log.e(this, new IllegalStateException(), "Unexpected message code %d", new Object[]{Integer.valueOf(message.what)});
                    break;
            }
            return;
        }
        ((Runnable) message.obj).run();
    }

    public void dumpPendingMessages(IndentingPrintWriter indentingPrintWriter) {
        Looper looper = getHandler().getLooper();
        Objects.requireNonNull(indentingPrintWriter);
        looper.dump(new $$Lambda$xzMjTXhTm1dp77OfnTdXjK_uqY(indentingPrintWriter), "");
    }

    public boolean isHfpDeviceAvailable() {
        return this.mBluetoothRouteManager.isBluetoothAvailable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpeakerphoneOn(boolean z) {
        if (this.mAudioManager.isSpeakerphoneOn() != z) {
            Log.i(this, "turning speaker phone %s", new Object[]{Boolean.valueOf(z)});
            this.mAudioManager.setSpeakerphoneOn(z);
        } else {
            Log.i(this, "Ignoring speakerphone request -- already %s", new Object[]{Boolean.valueOf(z)});
        }
        this.mStatusBarNotifier.notifySpeakerphone(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothOn(String str) {
        if (this.mBluetoothRouteManager.isBluetoothAvailable()) {
            BluetoothDevice bluetoothAudioConnectedDevice = this.mBluetoothRouteManager.getBluetoothAudioConnectedDevice();
            if (str == null && bluetoothAudioConnectedDevice != null) {
                Log.i(this, "Bluetooth audio already on.", new Object[0]);
                sendInternalMessage(1302);
                this.mBluetoothRouteManager.connectBluetoothAudio(bluetoothAudioConnectedDevice.getAddress());
            } else if (bluetoothAudioConnectedDevice == null || !Objects.equals(str, bluetoothAudioConnectedDevice.getAddress())) {
                Log.i(this, "connecting bluetooth audio: %s", new Object[]{str});
                this.mBluetoothRouteManager.connectBluetoothAudio(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBluetoothOff() {
        if (this.mBluetoothRouteManager.isBluetoothAvailable() && this.mBluetoothRouteManager.isBluetoothAudioConnectedOrPending()) {
            Log.i(this, "disconnecting bluetooth audio", new Object[0]);
            this.mBluetoothRouteManager.disconnectBluetoothAudio();
        }
    }

    private void setMuteOn(boolean z) {
        this.mIsMuted = z;
        Log.addEvent(this.mCallsManager.getForegroundCall(), z ? "MUTE" : "UNMUTE");
        if (z == this.mAudioManager.isMicrophoneMute() || !isInActiveState()) {
            return;
        }
        IAudioService audioService = this.mAudioServiceFactory.getAudioService();
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(z);
        objArr[1] = Boolean.valueOf(audioService == null);
        Log.i(this, "changing microphone mute state to: %b [serviceIsNull=%b]", objArr);
        if (audioService != null) {
            try {
                audioService.setMicrophoneMute(z, this.mContext.getOpPackageName(), getCurrentUserId());
            } catch (RemoteException e) {
                Log.e(this, e, "Remote exception while toggling mute.", new Object[0]);
            }
        }
    }

    private void updateSystemMuteState() {
        setSystemAudioState(new CallAudioState(this.mIsMuted, this.mCurrentCallAudioState.getRoute(), this.mAvailableRoutes, this.mCurrentCallAudioState.getActiveBluetoothDevice(), this.mBluetoothRouteManager.getConnectedDevices()));
        updateInternalCallAudioState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInternalCallAudioState() {
        IState currentState = getCurrentState();
        if (currentState == null) {
            Log.e(this, new IllegalStateException(), "Current state should never be null when updateInternalCallAudioState is called.", new Object[0]);
            this.mCurrentCallAudioState = new CallAudioState(this.mIsMuted, this.mCurrentCallAudioState.getRoute(), this.mAvailableRoutes, this.mBluetoothRouteManager.getBluetoothAudioConnectedDevice(), this.mBluetoothRouteManager.getConnectedDevices());
        } else {
            this.mCurrentCallAudioState = new CallAudioState(this.mIsMuted, this.mStateNameToRouteCode.get(currentState.getName()).intValue(), this.mAvailableRoutes, this.mBluetoothRouteManager.getBluetoothAudioConnectedDevice(), this.mBluetoothRouteManager.getConnectedDevices());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemAudioState(CallAudioState callAudioState) {
        setSystemAudioState(callAudioState, false);
    }

    private void resendSystemAudioState() {
        setSystemAudioState(this.mLastKnownCallAudioState, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSystemAudioState(CallAudioState callAudioState, boolean z) {
        synchronized (this.mLock) {
            Log.i(this, "setSystemAudioState: changing from %s to %s", new Object[]{this.mLastKnownCallAudioState, callAudioState});
            if (z || !callAudioState.equals(this.mLastKnownCallAudioState)) {
                this.mStatusBarNotifier.notifyMute(callAudioState.isMuted());
                this.mCallsManager.onCallAudioStateChanged(this.mLastKnownCallAudioState, callAudioState);
                updateAudioForForegroundCall(callAudioState);
                this.mLastKnownCallAudioState = callAudioState;
            }
        }
    }

    private void updateAudioForForegroundCall(CallAudioState callAudioState) {
        Call foregroundCall = this.mCallsManager.getForegroundCall();
        if (foregroundCall == null || foregroundCall.getConnectionService() == null) {
            return;
        }
        foregroundCall.getConnectionService().onCallAudioStateChanged(foregroundCall, callAudioState);
    }

    private int calculateSupportedRoutes() {
        int i;
        if (this.mWiredHeadsetManager.isPluggedIn()) {
            i = 12;
        } else {
            i = this.mDoesDeviceSupportEarpieceRoute ? 9 : 8;
        }
        return this.mBluetoothRouteManager.isBluetoothAvailable() ? i | 2 : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendInternalMessage(int i) {
        sendInternalMessage(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendInternalMessage(int i, int i2) {
        Session sessionCreateSubsession = Log.createSubsession();
        if (sessionCreateSubsession != null) {
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = sessionCreateSubsession;
            sendMessageAtFrontOfQueue(i, i2, 0, someArgsObtain);
            return;
        }
        sendMessageAtFrontOfQueue(i, i2);
    }

    private CallAudioState getInitialAudioState() {
        int iCalculateSupportedRoutes = calculateSupportedRoutes() & getCurrentCallSupportedRoutes();
        return new CallAudioState(false, ((iCalculateSupportedRoutes & 2) == 0 || !this.mBluetoothRouteManager.hasBtActiveDevice()) ? (iCalculateSupportedRoutes & 4) != 0 ? 4 : (iCalculateSupportedRoutes & 1) != 0 ? 1 : 8 : 2, iCalculateSupportedRoutes, null, this.mBluetoothRouteManager.getConnectedDevices());
    }

    private int getCurrentUserId() {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int i = ActivityManager.getService().getCurrentUser().id;
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return i;
        } catch (RemoteException unused) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return 0;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    public boolean isInActiveState() {
        AudioState currentState = getCurrentState();
        if (currentState == null) {
            Log.w(this, "Current state is null, assuming inactive state", new Object[0]);
            return false;
        }
        return currentState.isActive();
    }

    private boolean checkForEarpieceSupport() {
        for (AudioDeviceInfo audioDeviceInfo : this.mAudioManager.getDevices(2)) {
            if (audioDeviceInfo.getType() == 1) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateBaselineRouteMessage(boolean z, boolean z2) {
        boolean zHasVideoCall;
        if (z) {
            zHasVideoCall = false;
        } else {
            synchronized (this.mLock) {
                zHasVideoCall = this.mCallsManager.hasVideoCall();
            }
        }
        return ((this.mAvailableRoutes & 2) == 0 || this.mHasUserExplicitlyLeftBluetooth || !z2) ? ((this.mAvailableRoutes & 1) == 0 || zHasVideoCall) ? (this.mAvailableRoutes & 4) != 0 ? z ? 1103 : 1003 : z ? 1104 : 1004 : z ? 1101 : 1001 : z ? 1102 : 1002;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reinitialize() {
        CallAudioState initialAudioState = getInitialAudioState();
        int supportedRouteMask = initialAudioState.getSupportedRouteMask();
        this.mDeviceSupportedRoutes = supportedRouteMask;
        this.mAvailableRoutes = supportedRouteMask & getCurrentCallSupportedRoutes();
        this.mIsMuted = initialAudioState.isMuted();
        setSpeakerphoneOn(initialAudioState.getRoute() == 8);
        setMuteOn(this.mIsMuted);
        this.mWasOnSpeaker = false;
        this.mHasUserExplicitlyLeftBluetooth = false;
        this.mLastKnownCallAudioState = initialAudioState;
        transitionTo(this.mRouteCodeToQuiescentState.get(Integer.valueOf(initialAudioState.getRoute())));
    }

    private void updateRouteForForegroundCall() {
        this.mAvailableRoutes = this.mDeviceSupportedRoutes & getCurrentCallSupportedRoutes();
        CallAudioState currentCallAudioState = getCurrentCallAudioState();
        if ((currentCallAudioState.getRoute() & this.mAvailableRoutes) == 0) {
            sendInternalMessage(calculateBaselineRouteMessage(false, true));
        }
    }

    private int getCurrentCallSupportedRoutes() {
        if (this.mCallsManager.getForegroundCall() != null) {
            return 15 & this.mCallsManager.getForegroundCall().getSupportedAudioRoutes();
        }
        return 15;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int modifyRoutes(int i, int i2, int i3, boolean z) {
        int i4 = i & (~i2);
        if (z) {
            i3 &= getCurrentCallSupportedRoutes();
        }
        return i4 | i3;
    }
}
