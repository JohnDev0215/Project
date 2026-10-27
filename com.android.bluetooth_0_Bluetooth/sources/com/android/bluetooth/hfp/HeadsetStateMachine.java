package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.media.AudioManager;
import android.net.INetd;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.UserHandle;
import android.telephony.PhoneNumberUtils;
import android.telephony.ServiceState;
import android.text.TextUtils;
import android.util.Log;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetStateMachine extends StateMachine {
    static final int CALL_STATE_CHANGED = 9;
    private static final int CLCC_RSP_TIMEOUT = 104;
    private static final int CLCC_RSP_TIMEOUT_MS = 5000;
    static final int CONNECT = 1;
    static final int CONNECT_AUDIO = 3;
    private static final int CONNECT_TIMEOUT = 201;
    private static final boolean DBG = false;
    static final int DEVICE_STATE_CHANGED = 10;
    static final int DIALING_OUT_RESULT = 14;
    static final int DISCONNECT = 2;
    static final int DISCONNECT_AUDIO = 4;
    private static final String HEADSET_AUDIO_FEATURE_OFF = "off";
    private static final String HEADSET_AUDIO_FEATURE_ON = "on";
    private static final String HEADSET_NAME = "bt_headset_name";
    private static final String HEADSET_NREC = "bt_headset_nrec";
    private static final String HEADSET_WBS = "bt_wbs";
    static final int INTENT_CONNECTION_ACCESS_REPLY = 8;
    static final int INTENT_SCO_VOLUME_CHANGED = 7;
    static final int SEND_BSIR = 13;
    static final int SEND_CCLC_RESPONSE = 11;
    static final int SEND_VENDOR_SPECIFIC_RESULT_CODE = 12;
    static final int STACK_EVENT = 101;
    private static final String TAG = "HeadsetStateMachine";
    private static final Map<String, Integer> VENDOR_SPECIFIC_AT_COMMAND_COMPANY_ID;
    static final int VOICE_RECOGNITION_RESULT = 15;
    static final int VOICE_RECOGNITION_START = 5;
    static final int VOICE_RECOGNITION_STOP = 6;
    private final AdapterService mAdapterService;
    private HeadsetAgIndicatorEnableState mAgIndicatorEnableState;
    private final AudioConnecting mAudioConnecting;
    private final AudioDisconnecting mAudioDisconnecting;
    private final AudioOn mAudioOn;
    private final HashMap<String, String> mAudioParams;
    MyAudioServerStateCallback mAudioServerStateCallback;
    private final Connected mConnected;
    private final Connecting mConnecting;
    private long mConnectingTimestampMs;
    private HeadsetStateBase mCurrentState;
    private final BluetoothDevice mDevice;
    private boolean mDeviceSilenced;
    private final Disconnected mDisconnected;
    private final Disconnecting mDisconnecting;
    private final HeadsetService mHeadsetService;
    private int mMicVolume;
    private final HeadsetNativeInterface mNativeInterface;
    private boolean mNeedDialingOutReply;
    private final AtPhonebook mPhonebook;
    private HeadsetStateBase mPrevState;
    private int mSpeakerVolume;
    private final HeadsetSystemInterface mSystemInterface;
    static int sConnectTimeoutMs = 30000;
    private static final HeadsetAgIndicatorEnableState DEFAULT_AG_INDICATOR_ENABLE_STATE = new HeadsetAgIndicatorEnableState(true, true, true, true);

    static {
        HashMap map = new HashMap();
        VENDOR_SPECIFIC_AT_COMMAND_COMPANY_ID = map;
        map.put("+XEVENT", 85);
        VENDOR_SPECIFIC_AT_COMMAND_COMPANY_ID.put("+ANDROID", 224);
        VENDOR_SPECIFIC_AT_COMMAND_COMPANY_ID.put("+XAPL", 76);
        VENDOR_SPECIFIC_AT_COMMAND_COMPANY_ID.put("+IPHONEACCEV", 76);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private HeadsetStateMachine(BluetoothDevice device, Looper looper, HeadsetService headsetService, AdapterService adapterService, HeadsetNativeInterface nativeInterface, HeadsetSystemInterface systemInterface) {
        super(TAG, looper);
        Objects.requireNonNull(looper, "looper cannot be null");
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mDisconnecting = new Disconnecting();
        this.mConnected = new Connected();
        this.mAudioOn = new AudioOn();
        this.mAudioConnecting = new AudioConnecting();
        this.mAudioDisconnecting = new AudioDisconnecting();
        this.mConnectingTimestampMs = Long.MIN_VALUE;
        this.mAudioParams = new HashMap<>();
        this.mAudioServerStateCallback = new MyAudioServerStateCallback();
        setDbg(false);
        Objects.requireNonNull(device, "device cannot be null");
        this.mDevice = device;
        Objects.requireNonNull(headsetService, "headsetService cannot be null");
        this.mHeadsetService = headsetService;
        Objects.requireNonNull(nativeInterface, "nativeInterface cannot be null");
        this.mNativeInterface = nativeInterface;
        Objects.requireNonNull(systemInterface, "systemInterface cannot be null");
        this.mSystemInterface = systemInterface;
        Objects.requireNonNull(adapterService, "AdapterService cannot be null");
        this.mAdapterService = adapterService;
        this.mDeviceSilenced = false;
        this.mPhonebook = new AtPhonebook(this.mHeadsetService, this.mNativeInterface);
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mDisconnecting);
        addState(this.mConnected);
        addState(this.mAudioOn);
        addState(this.mAudioConnecting);
        addState(this.mAudioDisconnecting);
        setInitialState(this.mDisconnected);
    }

    static HeadsetStateMachine make(BluetoothDevice device, Looper looper, HeadsetService headsetService, AdapterService adapterService, HeadsetNativeInterface nativeInterface, HeadsetSystemInterface systemInterface) {
        HeadsetStateMachine stateMachine = new HeadsetStateMachine(device, looper, headsetService, adapterService, nativeInterface, systemInterface);
        stateMachine.start();
        Log.i(TAG, "Created state machine " + stateMachine + " for " + device);
        return stateMachine;
    }

    static void destroy(HeadsetStateMachine stateMachine) {
        Log.i(TAG, "destroy");
        if (stateMachine == null) {
            Log.w(TAG, "destroy(), stateMachine is null");
        } else {
            stateMachine.quitNow();
            stateMachine.cleanup();
        }
    }

    public void cleanup() {
        AtPhonebook atPhonebook = this.mPhonebook;
        if (atPhonebook != null) {
            atPhonebook.cleanup();
        }
        this.mAudioParams.clear();
    }

    public void dump(StringBuilder sb) {
        ProfileService.println(sb, "  mCurrentDevice: " + this.mDevice);
        ProfileService.println(sb, "  mCurrentState: " + this.mCurrentState);
        ProfileService.println(sb, "  mPrevState: " + this.mPrevState);
        ProfileService.println(sb, "  mConnectionState: " + getConnectionState());
        ProfileService.println(sb, "  mAudioState: " + getAudioState());
        ProfileService.println(sb, "  mNeedDialingOutReply: " + this.mNeedDialingOutReply);
        ProfileService.println(sb, "  mSpeakerVolume: " + this.mSpeakerVolume);
        ProfileService.println(sb, "  mMicVolume: " + this.mMicVolume);
        ProfileService.println(sb, "  mConnectingTimestampMs(uptimeMillis): " + this.mConnectingTimestampMs);
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

    private abstract class HeadsetStateBase extends State {
        abstract int getAudioStateInt();

        abstract int getConnectionStateInt();

        public abstract void processConnectionEvent(Message message, int i);

        private HeadsetStateBase() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            HeadsetStateMachine.this.mCurrentState = this;
            if (!(this instanceof Disconnected) && HeadsetStateMachine.this.mPrevState == null) {
                throw new IllegalStateException("mPrevState is null on enter()");
            }
            enforceValidConnectionStateTransition();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetStateMachine.this.mPrevState = this;
        }

        public String toString() {
            return getName();
        }

        void broadcastStateTransitions() {
            if (HeadsetStateMachine.this.mPrevState != null) {
                if (getAudioStateInt() != HeadsetStateMachine.this.mPrevState.getAudioStateInt() || ((HeadsetStateMachine.this.mPrevState instanceof AudioDisconnecting) && (this instanceof AudioOn))) {
                    stateLogD("audio state changed: " + HeadsetStateMachine.this.mDevice + ": " + HeadsetStateMachine.this.mPrevState + " -> " + this);
                    broadcastAudioState(HeadsetStateMachine.this.mDevice, HeadsetStateMachine.this.mPrevState.getAudioStateInt(), getAudioStateInt());
                }
                if (getConnectionStateInt() != HeadsetStateMachine.this.mPrevState.getConnectionStateInt()) {
                    stateLogD("connection state changed: " + HeadsetStateMachine.this.mDevice + ": " + HeadsetStateMachine.this.mPrevState + " -> " + this);
                    broadcastConnectionState(HeadsetStateMachine.this.mDevice, HeadsetStateMachine.this.mPrevState.getConnectionStateInt(), getConnectionStateInt());
                }
            }
        }

        void broadcastConnectionState(BluetoothDevice device, int fromState, int toState) {
            stateLogD("broadcastConnectionState " + device + ": " + fromState + "->" + toState);
            HeadsetStateMachine.this.mHeadsetService.onConnectionStateChangedFromStateMachine(device, fromState, toState);
            Intent intent = new Intent("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", fromState);
            intent.putExtra("android.bluetooth.profile.extra.STATE", toState);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
            intent.addFlags(16777216);
            HeadsetStateMachine.this.mHeadsetService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
        }

        void broadcastAudioState(BluetoothDevice device, int fromState, int toState) {
            int i;
            stateLogD("broadcastAudioState: " + device + ": " + fromState + "->" + toState);
            byte[] bArrObfuscateAddress = HeadsetStateMachine.this.mAdapterService.obfuscateAddress(device);
            int connectionStateFromAudioState = HeadsetStateMachine.getConnectionStateFromAudioState(toState);
            if (TextUtils.equals((CharSequence) HeadsetStateMachine.this.mAudioParams.get(HeadsetStateMachine.HEADSET_WBS), HeadsetStateMachine.HEADSET_AUDIO_FEATURE_ON)) {
                i = 2;
            } else {
                i = 1;
            }
            BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_SCO_CONNECTION_STATE_CHANGED, bArrObfuscateAddress, connectionStateFromAudioState, i, HeadsetStateMachine.this.mAdapterService.getMetricId(device));
            HeadsetStateMachine.this.mHeadsetService.onAudioStateChangedFromStateMachine(device, fromState, toState);
            Intent intent = new Intent("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
            intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", fromState);
            intent.putExtra("android.bluetooth.profile.extra.STATE", toState);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
            HeadsetStateMachine.this.mHeadsetService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
        }

        void enforceValidConnectionStateTransition() {
            boolean result = false;
            if (this == HeadsetStateMachine.this.mDisconnected) {
                result = HeadsetStateMachine.this.mPrevState == null || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mDisconnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnected || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioOn || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioConnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioDisconnecting;
            } else if (this == HeadsetStateMachine.this.mConnecting) {
                result = HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mDisconnected;
            } else if (this == HeadsetStateMachine.this.mDisconnecting) {
                result = HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnected || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioConnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioOn || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioDisconnecting;
            } else if (this == HeadsetStateMachine.this.mConnected) {
                result = HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioDisconnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mDisconnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioConnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioOn || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mDisconnected;
            } else if (this == HeadsetStateMachine.this.mAudioConnecting) {
                result = HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnected;
            } else if (this == HeadsetStateMachine.this.mAudioDisconnecting) {
                result = HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioOn;
            } else if (this == HeadsetStateMachine.this.mAudioOn) {
                result = HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioConnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mAudioDisconnecting || HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnected;
            }
            if (!result) {
                throw new IllegalStateException("Invalid state transition from " + HeadsetStateMachine.this.mPrevState + " to " + this + " for device " + HeadsetStateMachine.this.mDevice);
            }
        }

        void stateLogD(String msg) {
            HeadsetStateMachine.this.log(getName() + ": currentDevice=" + HeadsetStateMachine.this.mDevice + ", msg=" + msg);
        }

        void stateLogW(String msg) {
            HeadsetStateMachine.this.logw(getName() + ": currentDevice=" + HeadsetStateMachine.this.mDevice + ", msg=" + msg);
        }

        void stateLogE(String msg) {
            HeadsetStateMachine.this.loge(getName() + ": currentDevice=" + HeadsetStateMachine.this.mDevice + ", msg=" + msg);
        }

        void stateLogV(String msg) {
            HeadsetStateMachine.this.logv(getName() + ": currentDevice=" + HeadsetStateMachine.this.mDevice + ", msg=" + msg);
        }

        void stateLogI(String msg) {
            HeadsetStateMachine.this.logi(getName() + ": currentDevice=" + HeadsetStateMachine.this.mDevice + ", msg=" + msg);
        }

        void stateLogWtf(String msg) {
            Log.wtf(HeadsetStateMachine.TAG, getName() + ": " + msg);
        }
    }

    class Disconnected extends HeadsetStateBase {
        Disconnected() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getConnectionStateInt() {
            return 0;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 10;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            HeadsetStateMachine.this.mConnectingTimestampMs = Long.MIN_VALUE;
            HeadsetStateMachine.this.mPhonebook.resetAtState();
            HeadsetStateMachine.this.updateAgIndicatorEnableState(null);
            HeadsetStateMachine.this.mNeedDialingOutReply = false;
            HeadsetStateMachine.this.mAudioParams.clear();
            broadcastStateTransitions();
            if (HeadsetStateMachine.this.mPrevState != null && HeadsetStateMachine.this.mAdapterService.getBondState(HeadsetStateMachine.this.mDevice) == 10) {
                HeadsetStateMachine.this.getHandler().post(new Runnable() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetStateMachine$Disconnected$-mNaaz94NnZzh1U9fBYMQFwvbrU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$enter$0$HeadsetStateMachine$Disconnected();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$enter$0$HeadsetStateMachine$Disconnected() {
            HeadsetStateMachine.this.mHeadsetService.removeStateMachine(HeadsetStateMachine.this.mDevice);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                stateLogD("Connecting to " + device);
                if (HeadsetStateMachine.this.mDevice.equals(device)) {
                    if (!HeadsetStateMachine.this.mNativeInterface.connectHfp(device)) {
                        stateLogE("CONNECT failed for connectHfp(" + device + ")");
                        broadcastConnectionState(device, 0, 0);
                    } else {
                        HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                        headsetStateMachine.transitionTo(headsetStateMachine.mConnecting);
                    }
                } else {
                    stateLogE("CONNECT failed, device=" + device + ", currentDevice=" + HeadsetStateMachine.this.mDevice);
                }
            } else if (i != 2) {
                if (i == 9) {
                    stateLogD("Ignoring CALL_STATE_CHANGED event");
                } else if (i == 10) {
                    stateLogD("Ignoring DEVICE_STATE_CHANGED event");
                } else if (i == HeadsetStateMachine.STACK_EVENT) {
                    HeadsetStackEvent event = (HeadsetStackEvent) message.obj;
                    stateLogD("STACK_EVENT: " + event);
                    if (!HeadsetStateMachine.this.mDevice.equals(event.device)) {
                        stateLogE("Event device does not match currentDevice[" + HeadsetStateMachine.this.mDevice + "], event: " + event);
                    } else if (event.type == 1) {
                        processConnectionEvent(message, event.valueInt);
                    } else {
                        stateLogE("Unexpected stack event: " + event);
                    }
                } else {
                    stateLogE("Unexpected msg " + HeadsetStateMachine.getMessageName(message.what) + ": " + message);
                    return false;
                }
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        public void processConnectionEvent(Message message, int state) {
            stateLogD("processConnectionEvent, state=" + state);
            if (state == 0) {
                stateLogW("ignore DISCONNECTED event");
                return;
            }
            if (state != 1 && state != 2) {
                if (state == 4) {
                    stateLogW("Ignore DISCONNECTING event");
                    return;
                }
                stateLogE("Incorrect state: " + state);
                return;
            }
            if (HeadsetStateMachine.this.mHeadsetService.okToAcceptConnection(HeadsetStateMachine.this.mDevice)) {
                stateLogI("accept incoming connection");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mConnecting);
                return;
            }
            stateLogI("rejected incoming HF, connectionPolicy=" + HeadsetStateMachine.this.mHeadsetService.getConnectionPolicy(HeadsetStateMachine.this.mDevice) + " bondState=" + HeadsetStateMachine.this.mAdapterService.getBondState(HeadsetStateMachine.this.mDevice));
            if (!HeadsetStateMachine.this.mNativeInterface.disconnectHfp(HeadsetStateMachine.this.mDevice)) {
                stateLogE("failed to disconnect");
            }
            broadcastConnectionState(HeadsetStateMachine.this.mDevice, 0, 0);
        }
    }

    class Connecting extends HeadsetStateBase {
        Connecting() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getConnectionStateInt() {
            return 1;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 10;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            HeadsetStateMachine.this.mConnectingTimestampMs = SystemClock.uptimeMillis();
            HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
            headsetStateMachine.sendMessageDelayed(HeadsetStateMachine.CONNECT_TIMEOUT, headsetStateMachine.mDevice, HeadsetStateMachine.sConnectTimeoutMs);
            broadcastStateTransitions();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1 || i == 2 || i == 3) {
                HeadsetStateMachine.this.deferMessage(message);
            } else if (i == 9) {
                stateLogD("ignoring CALL_STATE_CHANGED event");
            } else if (i == 10) {
                stateLogD("ignoring DEVICE_STATE_CHANGED event");
            } else if (i == HeadsetStateMachine.STACK_EVENT) {
                HeadsetStackEvent event = (HeadsetStackEvent) message.obj;
                stateLogD("STACK_EVENT: " + event);
                if (!HeadsetStateMachine.this.mDevice.equals(event.device)) {
                    stateLogE("Event device does not match currentDevice[" + HeadsetStateMachine.this.mDevice + "], event: " + event);
                } else {
                    int i2 = event.type;
                    if (i2 == 1) {
                        processConnectionEvent(message, event.valueInt);
                    } else if (i2 == 3) {
                        stateLogW("Unexpected VR event, device=" + event.device + ", state=" + event.valueInt);
                        HeadsetStateMachine.this.processVrEvent(event.valueInt);
                    } else if (i2 == 4) {
                        stateLogW("Unexpected answer event for " + event.device);
                        HeadsetStateMachine.this.mSystemInterface.answerCall(event.device);
                    } else if (i2 == 5) {
                        stateLogW("Unexpected hangup event for " + event.device);
                        HeadsetStateMachine.this.mSystemInterface.hangupCall(event.device);
                    } else if (i2 == 6) {
                        stateLogW("Unexpected volume event for " + event.device);
                        HeadsetStateMachine.this.processVolumeEvent(event.valueInt, event.valueInt2);
                    } else if (i2 == 7) {
                        stateLogW("Unexpected dial event, device=" + event.device);
                        HeadsetStateMachine.this.processDialCall(event.valueString);
                    } else {
                        switch (i2) {
                            case 11:
                                stateLogW("Unexpected subscriber number event for" + event.device + ", state=" + event.valueInt);
                                HeadsetStateMachine.this.processSubscriberNumberRequest(event.device);
                                break;
                            case 12:
                                HeadsetStateMachine.this.processAtCind(event.device);
                                break;
                            case 13:
                                stateLogW("Unexpected COPS event for " + event.device);
                                HeadsetStateMachine.this.processAtCops(event.device);
                                break;
                            case 14:
                                Log.w(HeadsetStateMachine.TAG, "Connecting: Unexpected CLCC event for" + event.device);
                                HeadsetStateMachine.this.processAtClcc(event.device);
                                break;
                            case 15:
                                stateLogW("Unexpected unknown AT event for" + event.device + ", cmd=" + event.valueString);
                                HeadsetStateMachine.this.processUnknownAt(event.valueString, event.device);
                                break;
                            case 16:
                                stateLogW("Unexpected key-press event for " + event.device);
                                HeadsetStateMachine.this.processKeyPressed(event.device);
                                break;
                            case 17:
                                HeadsetStateMachine.this.processWBSEvent(event.valueInt);
                                break;
                            case 18:
                                HeadsetStateMachine.this.processAtBind(event.valueString, event.device);
                                break;
                            case 19:
                                stateLogW("Unexpected BIEV event for " + event.device + ", indId=" + event.valueInt + ", indVal=" + event.valueInt2);
                                HeadsetStateMachine.this.processAtBiev(event.valueInt, event.valueInt2, event.device);
                                break;
                            default:
                                stateLogE("Unexpected event: " + event);
                                break;
                        }
                    }
                }
            } else if (i == HeadsetStateMachine.CONNECT_TIMEOUT) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                if (!HeadsetStateMachine.this.mDevice.equals(device)) {
                    stateLogE("Unknown device timeout " + device);
                } else {
                    stateLogW("CONNECT_TIMEOUT");
                    HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                    headsetStateMachine.transitionTo(headsetStateMachine.mDisconnected);
                }
            } else {
                stateLogE("Unexpected msg " + HeadsetStateMachine.getMessageName(message.what) + ": " + message);
                return false;
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        public void processConnectionEvent(Message message, int state) {
            stateLogD("processConnectionEvent, state=" + state);
            if (state == 0) {
                stateLogW("Disconnected");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mDisconnected);
                return;
            }
            if (state != 1) {
                if (state == 2) {
                    stateLogD("RFCOMM connected");
                    return;
                }
                if (state == 3) {
                    stateLogD("SLC connected");
                    HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                    headsetStateMachine2.transitionTo(headsetStateMachine2.mConnected);
                } else {
                    if (state == 4) {
                        stateLogW("Disconnecting");
                        return;
                    }
                    stateLogE("Incorrect state " + state);
                }
            }
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetStateMachine.this.removeMessages(HeadsetStateMachine.CONNECT_TIMEOUT);
            super.exit();
        }
    }

    class Disconnecting extends HeadsetStateBase {
        Disconnecting() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getConnectionStateInt() {
            return 3;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 10;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
            headsetStateMachine.sendMessageDelayed(HeadsetStateMachine.CONNECT_TIMEOUT, headsetStateMachine.mDevice, HeadsetStateMachine.sConnectTimeoutMs);
            broadcastStateTransitions();
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1 || i == 2 || i == 3) {
                HeadsetStateMachine.this.deferMessage(message);
            } else if (i == HeadsetStateMachine.STACK_EVENT) {
                HeadsetStackEvent event = (HeadsetStackEvent) message.obj;
                stateLogD("STACK_EVENT: " + event);
                if (!HeadsetStateMachine.this.mDevice.equals(event.device)) {
                    stateLogE("Event device does not match currentDevice[" + HeadsetStateMachine.this.mDevice + "], event: " + event);
                } else if (event.type == 1) {
                    processConnectionEvent(message, event.valueInt);
                } else {
                    stateLogE("Unexpected event: " + event);
                }
            } else if (i == HeadsetStateMachine.CONNECT_TIMEOUT) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                if (!HeadsetStateMachine.this.mDevice.equals(device)) {
                    stateLogE("Unknown device timeout " + device);
                } else {
                    stateLogE("timeout");
                    HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                    headsetStateMachine.transitionTo(headsetStateMachine.mDisconnected);
                }
            } else {
                stateLogE("Unexpected msg " + HeadsetStateMachine.getMessageName(message.what) + ": " + message);
                return false;
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        public void processConnectionEvent(Message message, int state) {
            if (state == 0) {
                stateLogD("processConnectionEvent: Disconnected");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mDisconnected);
            } else if (state == 3) {
                stateLogD("processConnectionEvent: Connected");
                HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                headsetStateMachine2.transitionTo(headsetStateMachine2.mConnected);
            } else {
                stateLogE("processConnectionEvent: Bad state: " + state);
            }
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetStateMachine.this.removeMessages(HeadsetStateMachine.CONNECT_TIMEOUT);
            super.exit();
        }
    }

    private abstract class ConnectedBase extends HeadsetStateBase {
        public abstract void processAudioEvent(int i);

        private ConnectedBase() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getConnectionStateInt() {
            return 2;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != HeadsetStateMachine.STACK_EVENT) {
                if (i != HeadsetStateMachine.CLCC_RSP_TIMEOUT) {
                    if (i != HeadsetStateMachine.CONNECT_TIMEOUT) {
                        switch (i) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                                break;
                            case 5:
                                BluetoothDevice device = (BluetoothDevice) message.obj;
                                if (!HeadsetStateMachine.this.mDevice.equals(device)) {
                                    stateLogW("VOICE_RECOGNITION_START failed " + device + " is not currentDevice");
                                } else if (!HeadsetStateMachine.this.mNativeInterface.startVoiceRecognition(HeadsetStateMachine.this.mDevice)) {
                                    stateLogW("Failed to start voice recognition");
                                }
                                break;
                            case 6:
                                BluetoothDevice device2 = (BluetoothDevice) message.obj;
                                if (!HeadsetStateMachine.this.mDevice.equals(device2)) {
                                    stateLogW("VOICE_RECOGNITION_STOP failed " + device2 + " is not currentDevice");
                                } else if (!HeadsetStateMachine.this.mNativeInterface.stopVoiceRecognition(HeadsetStateMachine.this.mDevice)) {
                                    stateLogW("Failed to stop voice recognition");
                                }
                                break;
                            default:
                                switch (i) {
                                    case 8:
                                        HeadsetStateMachine.this.handleAccessPermissionResult((Intent) message.obj);
                                        break;
                                    case 9:
                                        HeadsetCallState callState = (HeadsetCallState) message.obj;
                                        if (!HeadsetStateMachine.this.mNativeInterface.phoneStateChange(HeadsetStateMachine.this.mDevice, callState)) {
                                            stateLogW("processCallState: failed to update call state " + callState);
                                        }
                                        break;
                                    case 10:
                                        HeadsetStateMachine.this.mNativeInterface.notifyDeviceStatus(HeadsetStateMachine.this.mDevice, (HeadsetDeviceState) message.obj);
                                        break;
                                    case 11:
                                        HeadsetStateMachine.this.processSendClccResponse((HeadsetClccResponse) message.obj);
                                        break;
                                    case 12:
                                        HeadsetStateMachine.this.processSendVendorSpecificResultCode((HeadsetVendorSpecificResultCode) message.obj);
                                        break;
                                    case 13:
                                        HeadsetStateMachine.this.mNativeInterface.sendBsir(HeadsetStateMachine.this.mDevice, message.arg1 == 1);
                                        break;
                                    case 14:
                                        BluetoothDevice device3 = (BluetoothDevice) message.obj;
                                        if (!HeadsetStateMachine.this.mDevice.equals(device3)) {
                                            stateLogW("DIALING_OUT_RESULT failed " + device3 + " is not currentDevice");
                                        } else if (HeadsetStateMachine.this.mNeedDialingOutReply) {
                                            HeadsetStateMachine.this.mNeedDialingOutReply = false;
                                            HeadsetStateMachine.this.mNativeInterface.atResponseCode(HeadsetStateMachine.this.mDevice, message.arg1 == 1 ? 1 : 0, 0);
                                        }
                                        break;
                                    case 15:
                                        BluetoothDevice device4 = (BluetoothDevice) message.obj;
                                        if (HeadsetStateMachine.this.mDevice.equals(device4)) {
                                            HeadsetStateMachine.this.mNativeInterface.atResponseCode(HeadsetStateMachine.this.mDevice, message.arg1 == 1 ? 1 : 0, 0);
                                        } else {
                                            stateLogW("VOICE_RECOGNITION_RESULT failed " + device4 + " is not currentDevice");
                                        }
                                        break;
                                    default:
                                        stateLogE("Unexpected msg " + HeadsetStateMachine.getMessageName(message.what) + ": " + message);
                                        return false;
                                }
                                break;
                        }
                    }
                    throw new IllegalStateException("Illegal message in generic handler: " + message);
                }
                BluetoothDevice device5 = (BluetoothDevice) message.obj;
                if (HeadsetStateMachine.this.mDevice.equals(device5)) {
                    HeadsetStateMachine.this.mNativeInterface.clccResponse(device5, 0, 0, 0, 0, false, INetd.NEXTHOP_NONE, 0);
                } else {
                    stateLogW("CLCC_RSP_TIMEOUT failed " + device5 + " is not currentDevice");
                }
            } else {
                HeadsetStackEvent event = (HeadsetStackEvent) message.obj;
                stateLogD("STACK_EVENT: " + event);
                if (HeadsetStateMachine.this.mDevice.equals(event.device)) {
                    switch (event.type) {
                        case 1:
                            processConnectionEvent(message, event.valueInt);
                            break;
                        case 2:
                            processAudioEvent(event.valueInt);
                            break;
                        case 3:
                            HeadsetStateMachine.this.processVrEvent(event.valueInt);
                            break;
                        case 4:
                            HeadsetStateMachine.this.mSystemInterface.answerCall(event.device);
                            break;
                        case 5:
                            HeadsetStateMachine.this.mSystemInterface.hangupCall(event.device);
                            break;
                        case 6:
                            HeadsetStateMachine.this.processVolumeEvent(event.valueInt, event.valueInt2);
                            break;
                        case 7:
                            HeadsetStateMachine.this.processDialCall(event.valueString);
                            break;
                        case 8:
                            HeadsetStateMachine.this.mSystemInterface.sendDtmf(event.valueInt, event.device);
                            break;
                        case 9:
                            HeadsetStateMachine.this.processNoiseReductionEvent(event.valueInt == 1);
                            break;
                        case 10:
                            HeadsetStateMachine.this.processAtChld(event.valueInt, event.device);
                            break;
                        case 11:
                            HeadsetStateMachine.this.processSubscriberNumberRequest(event.device);
                            break;
                        case 12:
                            HeadsetStateMachine.this.processAtCind(event.device);
                            break;
                        case 13:
                            HeadsetStateMachine.this.processAtCops(event.device);
                            break;
                        case 14:
                            HeadsetStateMachine.this.processAtClcc(event.device);
                            break;
                        case 15:
                            HeadsetStateMachine.this.processUnknownAt(event.valueString, event.device);
                            break;
                        case 16:
                            HeadsetStateMachine.this.processKeyPressed(event.device);
                            break;
                        case 17:
                            HeadsetStateMachine.this.processWBSEvent(event.valueInt);
                            break;
                        case 18:
                            HeadsetStateMachine.this.processAtBind(event.valueString, event.device);
                            break;
                        case 19:
                            HeadsetStateMachine.this.processAtBiev(event.valueInt, event.valueInt2, event.device);
                            break;
                        case 20:
                            HeadsetStateMachine.this.updateAgIndicatorEnableState((HeadsetAgIndicatorEnableState) event.valueObject);
                            break;
                        default:
                            stateLogE("Unknown stack event: " + event);
                            break;
                    }
                } else {
                    stateLogE("Event device does not match currentDevice[" + HeadsetStateMachine.this.mDevice + "], event: " + event);
                }
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        public void processConnectionEvent(Message message, int state) {
            stateLogD("processConnectionEvent, state=" + state);
            if (state == 0) {
                stateLogI("processConnectionEvent: Disconnected");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mDisconnected);
                return;
            }
            if (state == 2) {
                stateLogE("processConnectionEvent: RFCOMM connected again, shouldn't happen");
                return;
            }
            if (state == 3) {
                stateLogE("processConnectionEvent: SLC connected again, shouldn't happen");
                return;
            }
            if (state == 4) {
                stateLogI("processConnectionEvent: Disconnecting");
                HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                headsetStateMachine2.transitionTo(headsetStateMachine2.mDisconnecting);
            } else {
                stateLogE("processConnectionEvent: bad state: " + state);
            }
        }
    }

    class Connected extends ConnectedBase {
        Connected() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 10;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            if (HeadsetStateMachine.this.mPrevState == HeadsetStateMachine.this.mConnecting) {
                HeadsetStateMachine.this.updateAgIndicatorEnableState(HeadsetStateMachine.DEFAULT_AG_INDICATOR_ENABLE_STATE);
                HeadsetStateMachine.this.processNoiseReductionEvent(true);
                HeadsetStateMachine.this.mSystemInterface.queryPhoneState();
                HeadsetStateMachine.this.removeDeferredMessages(1);
            }
            broadcastStateTransitions();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                stateLogW("CONNECT, ignored, device=" + ((BluetoothDevice) message.obj) + ", currentDevice" + HeadsetStateMachine.this.mDevice);
            } else if (i == 2) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                stateLogD("DISCONNECT from device=" + device);
                if (HeadsetStateMachine.this.mDevice.equals(device)) {
                    if (!HeadsetStateMachine.this.mNativeInterface.disconnectHfp(device)) {
                        stateLogE("DISCONNECT from " + device + " failed");
                        broadcastConnectionState(device, 2, 2);
                    } else {
                        HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                        headsetStateMachine.transitionTo(headsetStateMachine.mDisconnecting);
                    }
                } else {
                    stateLogW("DISCONNECT, device " + device + " not connected");
                }
            } else if (i == 3) {
                stateLogD("CONNECT_AUDIO, device=" + HeadsetStateMachine.this.mDevice);
                HeadsetStateMachine.this.mSystemInterface.getAudioManager().setParameters("A2dpSuspended=true");
                if (!HeadsetStateMachine.this.mNativeInterface.connectAudio(HeadsetStateMachine.this.mDevice)) {
                    HeadsetStateMachine.this.mSystemInterface.getAudioManager().setParameters("A2dpSuspended=false");
                    stateLogE("Failed to connect SCO audio for " + HeadsetStateMachine.this.mDevice);
                    broadcastAudioState(HeadsetStateMachine.this.mDevice, 10, 10);
                } else {
                    HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                    headsetStateMachine2.transitionTo(headsetStateMachine2.mAudioConnecting);
                }
            } else if (i == 4) {
                stateLogD("ignore DISCONNECT_AUDIO, device=" + HeadsetStateMachine.this.mDevice);
            } else {
                return super.processMessage(message);
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase
        public void processAudioEvent(int state) {
            stateLogD("processAudioEvent, state=" + state);
            if (state != 0) {
                if (state == 1) {
                    if (!HeadsetStateMachine.this.mHeadsetService.isScoAcceptable(HeadsetStateMachine.this.mDevice)) {
                        stateLogW("processAudioEvent: reject incoming pending audio connection");
                        if (!HeadsetStateMachine.this.mNativeInterface.disconnectAudio(HeadsetStateMachine.this.mDevice)) {
                            stateLogE("processAudioEvent: failed to disconnect pending audio");
                        }
                        broadcastAudioState(HeadsetStateMachine.this.mDevice, 10, 10);
                        return;
                    }
                    stateLogI("processAudioEvent: audio connecting");
                    HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                    headsetStateMachine.transitionTo(headsetStateMachine.mAudioConnecting);
                    return;
                }
                if (state != 2) {
                    if (state != 3) {
                        stateLogE("processAudioEvent: bad state: " + state);
                        return;
                    }
                    return;
                }
                if (!HeadsetStateMachine.this.mHeadsetService.isScoAcceptable(HeadsetStateMachine.this.mDevice)) {
                    stateLogW("processAudioEvent: reject incoming audio connection");
                    if (!HeadsetStateMachine.this.mNativeInterface.disconnectAudio(HeadsetStateMachine.this.mDevice)) {
                        stateLogE("processAudioEvent: failed to disconnect audio");
                    }
                    broadcastAudioState(HeadsetStateMachine.this.mDevice, 10, 10);
                    return;
                }
                stateLogI("processAudioEvent: audio connected");
                HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                headsetStateMachine2.transitionTo(headsetStateMachine2.mAudioOn);
            }
        }
    }

    class AudioConnecting extends ConnectedBase {
        AudioConnecting() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 11;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
            headsetStateMachine.sendMessageDelayed(HeadsetStateMachine.CONNECT_TIMEOUT, headsetStateMachine.mDevice, HeadsetStateMachine.sConnectTimeoutMs);
            broadcastStateTransitions();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                HeadsetStateMachine.this.deferMessage(message);
            } else if (i == HeadsetStateMachine.CONNECT_TIMEOUT) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                if (!HeadsetStateMachine.this.mDevice.equals(device)) {
                    stateLogW("CONNECT_TIMEOUT for unknown device " + device);
                } else {
                    stateLogW("CONNECT_TIMEOUT");
                    HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                    headsetStateMachine.transitionTo(headsetStateMachine.mConnected);
                }
            } else {
                return super.processMessage(message);
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase
        public void processAudioEvent(int state) {
            if (state == 0) {
                stateLogW("processAudioEvent: audio connection failed");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mConnected);
            } else if (state != 1) {
                if (state == 2) {
                    stateLogI("processAudioEvent: audio connected");
                    HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                    headsetStateMachine2.transitionTo(headsetStateMachine2.mAudioOn);
                } else if (state != 3) {
                    stateLogE("processAudioEvent: bad state: " + state);
                }
            }
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetStateMachine.this.removeMessages(HeadsetStateMachine.CONNECT_TIMEOUT);
            super.exit();
        }
    }

    class MyAudioServerStateCallback extends AudioManager.AudioServerStateCallback {
        MyAudioServerStateCallback() {
        }

        public void onAudioServerDown() {
            HeadsetStateMachine.this.logi("onAudioServerDown");
        }

        public void onAudioServerUp() {
            HeadsetStateMachine.this.logi("onAudioServerUp restoring audio parameters");
            HeadsetStateMachine.this.setAudioParameters();
        }
    }

    class AudioOn extends ConnectedBase {
        AudioOn() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 12;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            HeadsetStateMachine.this.removeDeferredMessages(3);
            if (!HeadsetStateMachine.this.mDevice.equals(HeadsetStateMachine.this.mHeadsetService.getActiveDevice()) && !HeadsetStateMachine.this.hasDeferredMessages(4)) {
                HeadsetStateMachine.this.mHeadsetService.setActiveDevice(HeadsetStateMachine.this.mDevice);
            }
            HeadsetStateMachine.this.setAudioParameters();
            HeadsetStateMachine.this.mSystemInterface.getAudioManager().setAudioServerStateCallback(HeadsetStateMachine.this.mHeadsetService.getMainExecutor(), HeadsetStateMachine.this.mAudioServerStateCallback);
            broadcastStateTransitions();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            super.exit();
            HeadsetStateMachine.this.mSystemInterface.getAudioManager().clearAudioServerStateCallback();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                stateLogW("CONNECT, ignored, device=" + ((BluetoothDevice) message.obj) + ", currentDevice" + HeadsetStateMachine.this.mDevice);
            } else if (i == 2) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                stateLogD("DISCONNECT, device=" + device);
                if (HeadsetStateMachine.this.mDevice.equals(device)) {
                    if (!HeadsetStateMachine.this.mNativeInterface.disconnectAudio(HeadsetStateMachine.this.mDevice)) {
                        stateLogW("DISCONNECT failed, device=" + HeadsetStateMachine.this.mDevice);
                    }
                    HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                    headsetStateMachine.deferMessage(headsetStateMachine.obtainMessage(2, headsetStateMachine.mDevice));
                    HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                    headsetStateMachine2.transitionTo(headsetStateMachine2.mAudioDisconnecting);
                } else {
                    stateLogW("DISCONNECT, device " + device + " not connected");
                }
            } else if (i == 3) {
                BluetoothDevice device2 = (BluetoothDevice) message.obj;
                if (!HeadsetStateMachine.this.mDevice.equals(device2)) {
                    stateLogW("CONNECT_AUDIO device is not connected " + device2);
                } else {
                    stateLogW("CONNECT_AUDIO device auido is already connected " + device2);
                }
            } else if (i == 4) {
                BluetoothDevice device3 = (BluetoothDevice) message.obj;
                if (HeadsetStateMachine.this.mDevice.equals(device3)) {
                    if (HeadsetStateMachine.this.mNativeInterface.disconnectAudio(HeadsetStateMachine.this.mDevice)) {
                        stateLogD("DISCONNECT_AUDIO, device=" + HeadsetStateMachine.this.mDevice);
                        HeadsetStateMachine headsetStateMachine3 = HeadsetStateMachine.this;
                        headsetStateMachine3.transitionTo(headsetStateMachine3.mAudioDisconnecting);
                    } else {
                        stateLogW("DISCONNECT_AUDIO failed, device=" + HeadsetStateMachine.this.mDevice);
                        broadcastAudioState(HeadsetStateMachine.this.mDevice, 12, 12);
                    }
                } else {
                    stateLogW("DISCONNECT_AUDIO, failed, device=" + device3 + ", currentDevice=" + HeadsetStateMachine.this.mDevice);
                }
            } else if (i == 7) {
                processIntentScoVolume((Intent) message.obj, HeadsetStateMachine.this.mDevice);
            } else if (i == HeadsetStateMachine.STACK_EVENT) {
                HeadsetStackEvent event = (HeadsetStackEvent) message.obj;
                stateLogD("STACK_EVENT: " + event);
                if (!HeadsetStateMachine.this.mDevice.equals(event.device)) {
                    stateLogE("Event device does not match currentDevice[" + HeadsetStateMachine.this.mDevice + "], event: " + event);
                } else if (event.type == 17) {
                    stateLogE("Cannot change WBS state when audio is connected: " + event);
                } else {
                    super.processMessage(message);
                }
            } else {
                return super.processMessage(message);
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase
        public void processAudioEvent(int state) {
            if (state == 0) {
                stateLogI("processAudioEvent: audio disconnected by remote");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mConnected);
            } else if (state == 3) {
                stateLogI("processAudioEvent: audio being disconnected by remote");
                HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                headsetStateMachine2.transitionTo(headsetStateMachine2.mAudioDisconnecting);
            } else {
                stateLogE("processAudioEvent: bad state: " + state);
            }
        }

        private void processIntentScoVolume(Intent intent, BluetoothDevice device) {
            int volumeValue = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0);
            if (HeadsetStateMachine.this.mSpeakerVolume != volumeValue) {
                HeadsetStateMachine.this.mSpeakerVolume = volumeValue;
                HeadsetStateMachine.this.mNativeInterface.setVolume(device, 0, HeadsetStateMachine.this.mSpeakerVolume);
            }
        }
    }

    class AudioDisconnecting extends ConnectedBase {
        AudioDisconnecting() {
            super();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase
        int getAudioStateInt() {
            return 12;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            super.enter();
            HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
            headsetStateMachine.sendMessageDelayed(HeadsetStateMachine.CONNECT_TIMEOUT, headsetStateMachine.mDevice, HeadsetStateMachine.sConnectTimeoutMs);
            broadcastStateTransitions();
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1 || i == 2 || i == 3 || i == 4) {
                HeadsetStateMachine.this.deferMessage(message);
            } else if (i == HeadsetStateMachine.CONNECT_TIMEOUT) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                if (!HeadsetStateMachine.this.mDevice.equals(device)) {
                    stateLogW("CONNECT_TIMEOUT for unknown device " + device);
                } else {
                    stateLogW("CONNECT_TIMEOUT");
                    HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                    headsetStateMachine.transitionTo(headsetStateMachine.mConnected);
                }
            } else {
                return super.processMessage(message);
            }
            return true;
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.ConnectedBase
        public void processAudioEvent(int state) {
            if (state == 0) {
                stateLogI("processAudioEvent: audio disconnected");
                HeadsetStateMachine headsetStateMachine = HeadsetStateMachine.this;
                headsetStateMachine.transitionTo(headsetStateMachine.mConnected);
            } else if (state != 1) {
                if (state == 2) {
                    stateLogW("processAudioEvent: audio disconnection failed");
                    HeadsetStateMachine headsetStateMachine2 = HeadsetStateMachine.this;
                    headsetStateMachine2.transitionTo(headsetStateMachine2.mAudioOn);
                } else if (state != 3) {
                    stateLogE("processAudioEvent: bad state: " + state);
                }
            }
        }

        @Override // com.android.bluetooth.hfp.HeadsetStateMachine.HeadsetStateBase, com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetStateMachine.this.removeMessages(HeadsetStateMachine.CONNECT_TIMEOUT);
            super.exit();
        }
    }

    public synchronized BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public synchronized int getConnectionState() {
        if (this.mCurrentState == null) {
            return 0;
        }
        return this.mCurrentState.getConnectionStateInt();
    }

    public synchronized int getAudioState() {
        if (this.mCurrentState == null) {
            return 10;
        }
        return this.mCurrentState.getAudioStateInt();
    }

    public long getConnectingTimestampMs() {
        return this.mConnectingTimestampMs;
    }

    public boolean setSilenceDevice(boolean silence) {
        if (silence == this.mDeviceSilenced) {
            return false;
        }
        if (silence) {
            this.mSystemInterface.getHeadsetPhoneState().listenForPhoneState(this.mDevice, 0);
        } else {
            updateAgIndicatorEnableState(this.mAgIndicatorEnableState);
        }
        this.mDeviceSilenced = silence;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void broadcastVendorSpecificEventIntent(String command, int companyId, int commandType, Object[] objArr, BluetoothDevice device) {
        log("broadcastVendorSpecificEventIntent(" + command + ")");
        Intent intent = new Intent("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
        intent.putExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD", command);
        intent.putExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE", commandType);
        intent.putExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS", (Serializable) objArr);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.addCategory("android.bluetooth.headset.intent.category.companyid." + Integer.toString(companyId));
        this.mHeadsetService.sendBroadcastAsUser(intent, UserHandle.ALL, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAudioParameters() {
        String keyValuePairs = String.join(";", "bt_headset_name=" + getCurrentDeviceName(), "bt_headset_nrec=" + this.mAudioParams.getOrDefault(HEADSET_NREC, HEADSET_AUDIO_FEATURE_OFF), "bt_wbs=" + this.mAudioParams.getOrDefault(HEADSET_WBS, HEADSET_AUDIO_FEATURE_OFF));
        StringBuilder sb = new StringBuilder();
        sb.append("setAudioParameters for ");
        sb.append(this.mDevice);
        sb.append(": ");
        sb.append(keyValuePairs);
        Log.i(TAG, sb.toString());
        this.mSystemInterface.getAudioManager().setParameters(keyValuePairs);
    }

    private String parseUnknownAt(String atString) {
        StringBuilder atCommand = new StringBuilder(atString.length());
        int i = 0;
        while (true) {
            if (i >= atString.length()) {
                break;
            }
            char c = atString.charAt(i);
            if (c == '\"') {
                int j = atString.indexOf(34, i + 1);
                if (j == -1) {
                    atCommand.append(atString.substring(i, atString.length()));
                    atCommand.append('\"');
                    break;
                }
                atCommand.append(atString.substring(i, j + 1));
                i = j;
            } else if (c != ' ') {
                atCommand.append(Character.toUpperCase(c));
            }
            i++;
        }
        return atCommand.toString();
    }

    private int getAtCommandType(String atCommand) {
        String atCommand2 = atCommand.trim();
        if (atCommand2.length() <= 5) {
            return -1;
        }
        String atString = atCommand2.substring(5);
        if (atString.startsWith("?")) {
            return 0;
        }
        if (atString.startsWith("=?")) {
            return 2;
        }
        if (atString.startsWith("=")) {
            return 1;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processDialCall(String number) {
        String dialNumber;
        if (this.mHeadsetService.hasDeviceInitiatedDialingOut()) {
            Log.w(TAG, "processDialCall, already dialling");
            this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
            return;
        }
        if (number == null || number.length() == 0) {
            dialNumber = this.mPhonebook.getLastDialledNumber();
            if (dialNumber == null) {
                Log.w(TAG, "processDialCall, last dial number null");
                this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
                return;
            }
        } else if (number.charAt(0) == '>') {
            if (number.startsWith(">9999")) {
                Log.w(TAG, "Number is too big");
                this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
                return;
            }
            log("processDialCall, memory dial do last dial for now");
            dialNumber = this.mPhonebook.getLastDialledNumber();
            if (dialNumber == null) {
                Log.w(TAG, "processDialCall, last dial number null");
                this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
                return;
            }
        } else {
            if (number.charAt(number.length() - 1) == ';') {
                number = number.substring(0, number.length() - 1);
            }
            dialNumber = Utils.convertPreDial(number);
        }
        if (!this.mHeadsetService.dialOutgoingCall(this.mDevice, dialNumber)) {
            Log.w(TAG, "processDialCall, failed to dial in service");
            this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
        } else {
            this.mNeedDialingOutReply = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processVrEvent(int state) {
        if (state == 1) {
            if (!this.mHeadsetService.startVoiceRecognitionByHeadset(this.mDevice)) {
                this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
            }
        } else {
            if (state == 0) {
                if (this.mHeadsetService.stopVoiceRecognitionByHeadset(this.mDevice)) {
                    this.mNativeInterface.atResponseCode(this.mDevice, 1, 0);
                    return;
                } else {
                    this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
                    return;
                }
            }
            this.mNativeInterface.atResponseCode(this.mDevice, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processVolumeEvent(int volumeType, int volume) {
        if (!this.mDevice.equals(this.mHeadsetService.getActiveDevice())) {
            Log.w(TAG, "processVolumeEvent, ignored because " + this.mDevice + " is not active");
            return;
        }
        if (volumeType != 0) {
            if (volumeType == 1) {
                this.mMicVolume = volume;
                return;
            }
            Log.e(TAG, "Bad volume type: " + volumeType);
            return;
        }
        this.mSpeakerVolume = volume;
        int flag = this.mCurrentState != this.mAudioOn ? 0 : 1;
        this.mSystemInterface.getAudioManager().setStreamVolume(6, volume, flag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processNoiseReductionEvent(boolean enable) {
        HashMap<String, String> map = this.mAudioParams;
        String newNrec = HEADSET_AUDIO_FEATURE_OFF;
        String prevNrec = map.getOrDefault(HEADSET_NREC, HEADSET_AUDIO_FEATURE_OFF);
        if (enable) {
            newNrec = HEADSET_AUDIO_FEATURE_ON;
        }
        this.mAudioParams.put(HEADSET_NREC, newNrec);
        log("processNoiseReductionEvent: bt_headset_nrec change " + prevNrec + " -> " + newNrec);
        if (getAudioState() == 12) {
            setAudioParameters();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processWBSEvent(int wbsConfig) {
        String prevWbs = this.mAudioParams.getOrDefault(HEADSET_WBS, HEADSET_AUDIO_FEATURE_OFF);
        if (wbsConfig == 0 || wbsConfig == 1) {
            this.mAudioParams.put(HEADSET_WBS, HEADSET_AUDIO_FEATURE_OFF);
        } else if (wbsConfig == 2) {
            this.mAudioParams.put(HEADSET_WBS, HEADSET_AUDIO_FEATURE_ON);
        } else {
            Log.e(TAG, "processWBSEvent: unknown wbsConfig " + wbsConfig);
            return;
        }
        log("processWBSEvent: bt_headset_nrec change " + prevWbs + " -> " + this.mAudioParams.get(HEADSET_WBS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAtChld(int chld, BluetoothDevice device) {
        if (this.mSystemInterface.processChld(chld)) {
            this.mNativeInterface.atResponseCode(device, 1, 0);
        } else {
            this.mNativeInterface.atResponseCode(device, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSubscriberNumberRequest(BluetoothDevice device) {
        String number = this.mSystemInterface.getSubscriberNumber();
        if (number != null) {
            this.mNativeInterface.atResponseString(device, "+CNUM: ,\"" + number + "\"," + PhoneNumberUtils.toaFromString(number) + ",,4");
            this.mNativeInterface.atResponseCode(device, 1, 0);
            return;
        }
        Log.e(TAG, "getSubscriberNumber returns null");
        this.mNativeInterface.atResponseCode(device, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAtCind(BluetoothDevice device) {
        int call;
        int callSetup;
        HeadsetPhoneState phoneState = this.mSystemInterface.getHeadsetPhoneState();
        if (this.mHeadsetService.isVirtualCallStarted()) {
            call = 1;
            callSetup = 0;
        } else {
            call = phoneState.getNumActiveCall();
            callSetup = phoneState.getNumHeldCall();
        }
        this.mNativeInterface.cindResponse(device, phoneState.getCindService(), call, callSetup, phoneState.getCallState(), phoneState.getCindSignal(), phoneState.getCindRoam(), phoneState.getCindBatteryCharge());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAtCops(BluetoothDevice device) {
        String operatorName = null;
        ServiceState serviceState = this.mSystemInterface.getHeadsetPhoneState().getServiceState();
        if (serviceState != null) {
            operatorName = serviceState.getOperatorAlpha();
        }
        if (this.mSystemInterface.isInCall() || operatorName == null || operatorName.equals(INetd.NEXTHOP_NONE)) {
            operatorName = this.mSystemInterface.getNetworkOperator();
        }
        if (operatorName == null) {
            operatorName = INetd.NEXTHOP_NONE;
        }
        this.mNativeInterface.copsResponse(device, operatorName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAtClcc(BluetoothDevice device) {
        String phoneNumber;
        if (this.mHeadsetService.isVirtualCallStarted()) {
            String phoneNumber2 = this.mSystemInterface.getSubscriberNumber();
            if (phoneNumber2 != null) {
                phoneNumber = phoneNumber2;
            } else {
                phoneNumber = INetd.NEXTHOP_NONE;
            }
            int type = PhoneNumberUtils.toaFromString(phoneNumber);
            this.mNativeInterface.clccResponse(device, 1, 0, 0, 0, false, phoneNumber, type);
            this.mNativeInterface.clccResponse(device, 0, 0, 0, 0, false, INetd.NEXTHOP_NONE, 0);
            return;
        }
        if (!this.mSystemInterface.listCurrentCalls()) {
            Log.e(TAG, "processAtClcc: failed to list current calls for " + device);
            this.mNativeInterface.clccResponse(device, 0, 0, 0, 0, false, INetd.NEXTHOP_NONE, 0);
            return;
        }
        sendMessageDelayed(CLCC_RSP_TIMEOUT, device, 5000L);
    }

    private void processAtCscs(String atString, int type, BluetoothDevice device) {
        log("processAtCscs - atString = " + atString);
        AtPhonebook atPhonebook = this.mPhonebook;
        if (atPhonebook != null) {
            atPhonebook.handleCscsCommand(atString, type, device);
        } else {
            Log.e(TAG, "Phonebook handle null for At+CSCS");
            this.mNativeInterface.atResponseCode(device, 0, 0);
        }
    }

    private void processAtCpbs(String atString, int type, BluetoothDevice device) {
        log("processAtCpbs - atString = " + atString);
        AtPhonebook atPhonebook = this.mPhonebook;
        if (atPhonebook != null) {
            atPhonebook.handleCpbsCommand(atString, type, device);
        } else {
            Log.e(TAG, "Phonebook handle null for At+CPBS");
            this.mNativeInterface.atResponseCode(device, 0, 0);
        }
    }

    private void processAtCpbr(String atString, int type, BluetoothDevice device) {
        log("processAtCpbr - atString = " + atString);
        AtPhonebook atPhonebook = this.mPhonebook;
        if (atPhonebook != null) {
            atPhonebook.handleCpbrCommand(atString, type, device);
        } else {
            Log.e(TAG, "Phonebook handle null for At+CPBR");
            this.mNativeInterface.atResponseCode(device, 0, 0);
        }
    }

    private static int findChar(char ch, String input, int fromIndex) {
        int i = fromIndex;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '\"') {
                i = input.indexOf(34, i + 1);
                if (i == -1) {
                    return input.length();
                }
            } else if (c == ch) {
                return i;
            }
            i++;
        }
        int i2 = input.length();
        return i2;
    }

    private static Object[] generateArgs(String input) {
        int i = 0;
        ArrayList<Object> out = new ArrayList<>();
        while (i <= input.length()) {
            int j = findChar(Utils.PAUSE, input, i);
            String arg = input.substring(i, j);
            try {
                out.add(new Integer(arg));
            } catch (NumberFormatException e) {
                out.add(arg);
            }
            i = j + 1;
        }
        return out.toArray();
    }

    private void processVendorSpecificAt(String atString, BluetoothDevice device) {
        log("processVendorSpecificAt - atString = " + atString);
        int indexOfEqual = atString.indexOf("=");
        if (indexOfEqual == -1) {
            Log.w(TAG, "processVendorSpecificAt: command type error in " + atString);
            this.mNativeInterface.atResponseCode(device, 0, 0);
            return;
        }
        String command = atString.substring(0, indexOfEqual);
        Integer companyId = VENDOR_SPECIFIC_AT_COMMAND_COMPANY_ID.get(command);
        if (companyId == null) {
            Log.i(TAG, "processVendorSpecificAt: unsupported command: " + atString);
            this.mNativeInterface.atResponseCode(device, 0, 0);
            return;
        }
        String arg = atString.substring(indexOfEqual + 1);
        if (arg.startsWith("?")) {
            Log.w(TAG, "processVendorSpecificAt: command type error in " + atString);
            this.mNativeInterface.atResponseCode(device, 0, 0);
            return;
        }
        Object[] args = generateArgs(arg);
        if (command.equals("+XAPL")) {
            processAtXapl(args, device);
        }
        broadcastVendorSpecificEventIntent(command, companyId.intValue(), 2, args, device);
        this.mNativeInterface.atResponseCode(device, 1, 0);
    }

    private void processAtXapl(Object[] args, BluetoothDevice device) {
        if (args.length != 2) {
            Log.w(TAG, "processAtXapl() args length must be 2: " + String.valueOf(args.length));
            return;
        }
        if (!(args[0] instanceof String) || !(args[1] instanceof Integer)) {
            Log.w(TAG, "processAtXapl() argument types not match");
            return;
        }
        String[] deviceInfo = ((String) args[0]).split("-");
        if (deviceInfo.length != 3) {
            Log.w(TAG, "processAtXapl() deviceInfo length " + deviceInfo.length + " is wrong");
            return;
        }
        String vendorId = deviceInfo[0];
        String productId = deviceInfo[1];
        String version = deviceInfo[2];
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_DEVICE_INFO_REPORTED, this.mAdapterService.obfuscateAddress(device), 1, "+XAPL", vendorId, productId, version, (String) null, this.mAdapterService.getMetricId(device));
        this.mNativeInterface.atResponseString(device, "+XAPL=iPhone," + String.valueOf(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processUnknownAt(String atString, BluetoothDevice device) {
        if (device == null) {
            Log.w(TAG, "processUnknownAt device is null");
            return;
        }
        log("processUnknownAt - atString = " + atString);
        String atCommand = parseUnknownAt(atString);
        int commandType = getAtCommandType(atCommand);
        if (atCommand.startsWith("+CSCS")) {
            processAtCscs(atCommand.substring(5), commandType, device);
            return;
        }
        if (atCommand.startsWith("+CPBS")) {
            processAtCpbs(atCommand.substring(5), commandType, device);
        } else if (atCommand.startsWith("+CPBR")) {
            processAtCpbr(atCommand.substring(5), commandType, device);
        } else {
            processVendorSpecificAt(atCommand, device);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processKeyPressed(BluetoothDevice device) {
        if (this.mSystemInterface.isRinging()) {
            this.mSystemInterface.answerCall(device);
            return;
        }
        if (this.mSystemInterface.isInCall()) {
            if (getAudioState() == 10) {
                if (!this.mHeadsetService.setActiveDevice(this.mDevice)) {
                    Log.w(TAG, "processKeyPressed, failed to set active device to " + this.mDevice);
                    return;
                }
                return;
            }
            this.mSystemInterface.hangupCall(device);
            return;
        }
        if (getAudioState() != 10) {
            if (!this.mNativeInterface.disconnectAudio(this.mDevice)) {
                Log.w(TAG, "processKeyPressed, failed to disconnect audio from " + this.mDevice);
                return;
            }
            return;
        }
        if (this.mHeadsetService.hasDeviceInitiatedDialingOut()) {
            Log.w(TAG, "processKeyPressed, already dialling");
            return;
        }
        String dialNumber = this.mPhonebook.getLastDialledNumber();
        if (dialNumber == null) {
            Log.w(TAG, "processKeyPressed, last dial number null");
        } else if (!this.mHeadsetService.dialOutgoingCall(this.mDevice, dialNumber)) {
            Log.w(TAG, "processKeyPressed, failed to call in service");
        }
    }

    private void sendIndicatorIntent(BluetoothDevice device, int indId, int indValue) {
        Intent intent = new Intent("android.bluetooth.headset.action.HF_INDICATORS_VALUE_CHANGED");
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        intent.putExtra("android.bluetooth.headset.extra.HF_INDICATORS_IND_ID", indId);
        intent.putExtra("android.bluetooth.headset.extra.HF_INDICATORS_IND_VALUE", indValue);
        this.mHeadsetService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAtBind(String atString, BluetoothDevice device) {
        log("processAtBind: " + atString);
        for (String id : atString.split(",")) {
            try {
                int indId = Integer.parseInt(id);
                if (indId == 1) {
                    log("Send Broadcast intent for the Enhanced Driver Safety indicator.");
                    sendIndicatorIntent(device, indId, -1);
                } else if (indId == 2) {
                    log("Send Broadcast intent for the Battery Level indicator.");
                    sendIndicatorIntent(device, indId, -1);
                } else {
                    log("Invalid HF Indicator Received");
                }
            } catch (NumberFormatException e) {
                Log.e(TAG, Log.getStackTraceString(new Throwable()));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAtBiev(int indId, int indValue, BluetoothDevice device) {
        log("processAtBiev: ind_id=" + indId + ", ind_value=" + indValue);
        sendIndicatorIntent(device, indId, indValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSendClccResponse(HeadsetClccResponse clcc) {
        if (!hasMessages(CLCC_RSP_TIMEOUT)) {
            return;
        }
        if (clcc.mIndex == 0) {
            removeMessages(CLCC_RSP_TIMEOUT);
        }
        this.mNativeInterface.clccResponse(this.mDevice, clcc.mIndex, clcc.mDirection, clcc.mStatus, clcc.mMode, clcc.mMpty, clcc.mNumber, clcc.mType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSendVendorSpecificResultCode(HeadsetVendorSpecificResultCode resultCode) {
        String stringToSend = resultCode.mCommand + ": ";
        if (resultCode.mArg != null) {
            stringToSend = stringToSend + resultCode.mArg;
        }
        this.mNativeInterface.atResponseString(resultCode.mDevice, stringToSend);
    }

    private String getCurrentDeviceName() {
        String deviceName = this.mAdapterService.getRemoteName(this.mDevice);
        if (deviceName == null) {
            return "<unknown>";
        }
        return deviceName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAgIndicatorEnableState(HeadsetAgIndicatorEnableState agIndicatorEnableState) {
        if (!this.mDeviceSilenced && Objects.equals(this.mAgIndicatorEnableState, agIndicatorEnableState)) {
            Log.i(TAG, "updateAgIndicatorEnableState, no change in indicator state " + this.mAgIndicatorEnableState);
            return;
        }
        this.mAgIndicatorEnableState = agIndicatorEnableState;
        int events = 0;
        if (agIndicatorEnableState != null && agIndicatorEnableState.service) {
            events = 0 | 1;
        }
        HeadsetAgIndicatorEnableState headsetAgIndicatorEnableState = this.mAgIndicatorEnableState;
        if (headsetAgIndicatorEnableState != null && headsetAgIndicatorEnableState.signal) {
            events |= 512;
        }
        this.mSystemInterface.getHeadsetPhoneState().listenForPhoneState(this.mDevice, events);
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected void log(String msg) {
    }

    @Override // com.android.bluetooth.statemachine.StateMachine
    protected String getLogRecString(Message msg) {
        StringBuilder builder = new StringBuilder();
        builder.append(getMessageName(msg.what));
        builder.append(": ");
        builder.append("arg1=");
        builder.append(msg.arg1);
        builder.append(", arg2=");
        builder.append(msg.arg2);
        builder.append(", obj=");
        if (msg.obj instanceof HeadsetMessageObject) {
            HeadsetMessageObject object = (HeadsetMessageObject) msg.obj;
            object.buildString(builder);
        } else {
            builder.append(msg.obj);
        }
        return builder.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAccessPermissionResult(Intent intent) {
        log("handleAccessPermissionResult");
        BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (!this.mPhonebook.getCheckingAccessPermission()) {
            return;
        }
        int atCommandResult = 0;
        if (intent.getAction().equals("android.bluetooth.device.action.CONNECTION_ACCESS_REPLY")) {
            if (intent.getIntExtra("android.bluetooth.device.extra.CONNECTION_ACCESS_RESULT", 2) == 1) {
                if (intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false)) {
                    this.mDevice.setPhonebookAccessPermission(1);
                }
                atCommandResult = this.mPhonebook.processCpbrCommand(device);
            } else if (intent.getBooleanExtra("android.bluetooth.device.extra.ALWAYS_ALLOWED", false)) {
                this.mDevice.setPhonebookAccessPermission(2);
            }
        }
        this.mPhonebook.setCpbrIndex(-1);
        this.mPhonebook.setCheckingAccessPermission(false);
        if (atCommandResult >= 0) {
            this.mNativeInterface.atResponseCode(device, atCommandResult, 0);
        } else {
            log("handleAccessPermissionResult - RESULT_NONE");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getConnectionStateFromAudioState(int audioState) {
        if (audioState == 11) {
            return 1;
        }
        if (audioState == 12) {
            return 2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getMessageName(int what) {
        if (what == 14) {
            return "DIALING_OUT_RESULT";
        }
        if (what == 15) {
            return "VOICE_RECOGNITION_RESULT";
        }
        if (what == STACK_EVENT) {
            return "STACK_EVENT";
        }
        if (what == CLCC_RSP_TIMEOUT) {
            return "CLCC_RSP_TIMEOUT";
        }
        if (what != CONNECT_TIMEOUT) {
            switch (what) {
                case 1:
                    return "CONNECT";
                case 2:
                    return "DISCONNECT";
                case 3:
                    return "CONNECT_AUDIO";
                case 4:
                    return "DISCONNECT_AUDIO";
                case 5:
                    return "VOICE_RECOGNITION_START";
                case 6:
                    return "VOICE_RECOGNITION_STOP";
                case 7:
                    return "INTENT_SCO_VOLUME_CHANGED";
                case 8:
                    return "INTENT_CONNECTION_ACCESS_REPLY";
                case 9:
                    return "CALL_STATE_CHANGED";
                case 10:
                    return "DEVICE_STATE_CHANGED";
                case 11:
                    return "SEND_CCLC_RESPONSE";
                case 12:
                    return "SEND_VENDOR_SPECIFIC_RESULT_CODE";
                default:
                    return "UNKNOWN(" + what + ")";
            }
        }
        return "CONNECT_TIMEOUT";
    }
}
