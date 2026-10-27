package com.android.bluetooth.hfpclient;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadsetClientCall;
import android.bluetooth.BluetoothUuid;
import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.media.CarAudioManager;
import android.car.media.CarAudioPatchHandle;
import android.car.media.CarEnhancedAudioManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.media.AudioDevicePort;
import android.media.AudioFocusRequest;
import android.media.AudioGainConfig;
import android.media.AudioManager;
import android.media.AudioPatch;
import android.media.AudioPort;
import android.media.AudioPortConfig;
import android.net.INetd;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.util.Log;
import android.util.Pair;
import com.android.bluetooth.BluetoothMetricsProto;
import com.android.bluetooth.BluetoothStatsLog;
import com.android.bluetooth.R;
import com.android.bluetooth.Utils;
import com.android.bluetooth.btservice.AdapterService;
import com.android.bluetooth.btservice.MetricsLogger;
import com.android.bluetooth.btservice.ProfileService;
import com.android.bluetooth.statemachine.IState;
import com.android.bluetooth.statemachine.State;
import com.android.bluetooth.statemachine.StateMachine;
import com.android.bluetooth.util.AesUtil;
import com.android.internal.util.ArrayUtils;
import com.android.vcard.VCardConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetClientStateMachine extends StateMachine {
    public static final int ACCEPT_CALL = 12;
    public static final int AT_OK = 0;
    private static final String AUDIO_NEED_ON = "ts.vendor.audioon";
    private static final String BATTERY_AND_MAC = "persist.vendor.battery.mac";
    private static final String BUS_CALL = "bus7_bt_dnlink_in";
    private static final String BUS_RING = "bus7_bt_dnlink_in";
    public static final int CONNECT = 1;
    private static final int CONNECTING_TIMEOUT = 53;
    static final int CONNECTING_TIMEOUT_MS = 10000;
    public static final int CONNECT_AUDIO = 3;
    public static final int DIAL_NUMBER = 10;
    public static final int DISABLE_NREC = 20;
    public static final int DISCONNECT = 2;
    public static final int DISCONNECT_AUDIO = 4;
    public static final int ENTER_PRIVATE_MODE = 16;
    public static final int EXPLICIT_CALL_TRANSFER = 18;
    static final int HF_ORIGINATED_CALL_ID = -1;
    public static final int HOLD_CALL = 14;
    static final int IN_BAND_RING_ENABLED = 1;
    private static final int MAX_HFP_SCO_VOICE_CALL_VOLUME = 15;
    private static final int MIN_HFP_SCO_VOICE_CALL_VOLUME = 0;
    private static final String NEW_BATTERY_AND_MAC = "vendor.ts.battery.mac";
    static final int NO_ACTION = 0;
    private static final long OUTGOING_TIMEOUT_MILLI = 10000;
    private static final int QUERY_CURRENT_CALLS = 50;
    private static final long QUERY_CURRENT_CALLS_WAIT_MILLIS = 2000;
    private static final int QUERY_OPERATOR_NAME = 51;
    public static final int REJECT_CALL = 13;
    public static final int RELEASE_CALL = 22;
    private static final int ROUTING_DELAY_MS = 250;
    private static final int SAMPLING_RATE_NARROW = 8000;
    private static final int SAMPLING_RATE_WIDE = 16000;
    public static final int SEND_DTMF = 17;
    public static final int SEND_VENDOR_AT_COMMAND = 21;
    public static final int SET_MIC_VOLUME = 7;
    public static final int SET_SPEAKER_VOLUME = 8;
    private static final int SUBSCRIBER_INFO = 52;
    public static final int TERMINATE_CALL = 15;
    static final int TERMINATE_SPECIFIC_CALL = 53;
    private static final int USAGE_NOTIFICATION_RINGTONE = 6;
    private static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int VOICE_RECOGNITION_START = 5;
    public static final int VOICE_RECOGNITION_STOP = 6;
    private static int mVolumeGroupId;
    private static int sMaxAmVcVol;
    private static int sMinAmVcVol;
    private final AudioManager.OnAudioFocusChangeListener focusListener2;
    private final AudioManager.OnAudioFocusChangeListener focusListener6;
    private final BluetoothAdapter mAdapter;
    private AudioFocusRequest mAudioFocusRequest;
    private final AudioManager mAudioManager;
    private final AudioOn mAudioOn;
    private AudioPatch mAudioPatch;
    private int mAudioState;
    private boolean mAudioWbs;
    private final Hashtable<Integer, BluetoothHeadsetClientCall> mCalls;
    private final Hashtable<Integer, BluetoothHeadsetClientCall> mCallsUpdate;
    private Car mCar;
    private CarAudioManager mCarAudioManager;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private int mChldFeatures;
    private long mClccTimer;
    private final Connected mConnected;
    private final Connecting mConnecting;
    private final ServiceConnection mConnection;
    private BluetoothDevice mCurrentDevice;
    private final Disconnected mDisconnected;
    private boolean mInBandRing;
    private int mIndicatorBatteryLevel;
    private int mIndicatorNetworkSignal;
    private int mIndicatorNetworkState;
    private int mIndicatorNetworkType;
    private int mLastCallState;
    private final NativeInterface mNativeInterface;
    private String mOperatorName;
    private CarAudioPatchHandle mPatch;
    private int mPeerFeatures;
    private Pair<Integer, Object> mPendingAction;
    private State mPrevState;
    private Queue<Pair<Integer, Object>> mQueuedActions;
    private int mSamplingRate;
    private final HeadsetClientService mService;
    private String mSubscriberInfo;
    private final VendorCommandResponseProcessor mVendorProcessor;
    private int mVoiceRecognitionActive;
    private final CarAudioManager.CarVolumeCallback mVolumeChangeCallback;
    private int usage;
    private static final String TAG = "HeadsetClientStateMachine";
    private static final boolean DBG = Log.isLoggable(TAG, 3);
    private static boolean sAudioIsRouted = false;

    public IState getDisconnectedState() {
        return this.mDisconnected;
    }

    public boolean getInBandRing() {
        return this.mInBandRing;
    }

    public void dump(StringBuilder sb) {
        if (this.mCurrentDevice == null) {
            return;
        }
        ProfileService.println(sb, "mCurrentDevice: " + this.mCurrentDevice.getAddress() + "(" + this.mCurrentDevice.getName() + ") " + toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("mAudioState: ");
        sb2.append(this.mAudioState);
        ProfileService.println(sb, sb2.toString());
        ProfileService.println(sb, "mAudioWbs: " + this.mAudioWbs);
        ProfileService.println(sb, "mIndicatorNetworkState: " + this.mIndicatorNetworkState);
        ProfileService.println(sb, "mIndicatorNetworkType: " + this.mIndicatorNetworkType);
        ProfileService.println(sb, "mIndicatorNetworkSignal: " + this.mIndicatorNetworkSignal);
        ProfileService.println(sb, "mIndicatorBatteryLevel: " + this.mIndicatorBatteryLevel);
        ProfileService.println(sb, "mOperatorName: " + this.mOperatorName);
        ProfileService.println(sb, "mSubscriberInfo: " + this.mSubscriberInfo);
        ProfileService.println(sb, "mCalls:");
        Hashtable<Integer, BluetoothHeadsetClientCall> hashtable = this.mCalls;
        if (hashtable != null) {
            for (BluetoothHeadsetClientCall call : hashtable.values()) {
                ProfileService.println(sb, "  " + call);
            }
        }
        ProfileService.println(sb, "mCallsUpdate:");
        Hashtable<Integer, BluetoothHeadsetClientCall> hashtable2 = this.mCallsUpdate;
        if (hashtable2 != null) {
            for (BluetoothHeadsetClientCall call2 : hashtable2.values()) {
                ProfileService.println(sb, "  " + call2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPendingAction() {
        this.mPendingAction = new Pair<>(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addQueuedAction(int action) {
        addQueuedAction(action, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addQueuedAction(int action, Object data) {
        this.mQueuedActions.add(new Pair<>(Integer.valueOf(action), data));
    }

    private void addQueuedAction(int action, int data) {
        this.mQueuedActions.add(new Pair<>(Integer.valueOf(action), Integer.valueOf(data)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BluetoothHeadsetClientCall getCall(int... states) {
        logD("getFromCallsWithStates states:" + Arrays.toString(states));
        for (BluetoothHeadsetClientCall c : this.mCalls.values()) {
            for (int s : states) {
                if (c.getState() == s) {
                    return c;
                }
            }
        }
        return null;
    }

    private int callsInState(int state) {
        int i = 0;
        for (BluetoothHeadsetClientCall c : this.mCalls.values()) {
            if (c.getState() == state) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendCallChangedIntent(BluetoothHeadsetClientCall call) {
        int state = call.getState();
        logD("sendCallChangedIntent currentState: " + state);
        Intent intent = new Intent("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        intent.addFlags(VCardConfig.FLAG_REFRAIN_QP_TO_NAME_PROPERTIES);
        intent.putExtra("android.bluetooth.headsetclient.extra.CALL", (Parcelable) call);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        if (state == 7) {
            logD("sendCallChangedIntent  mAudioFocusRequest: " + this.mAudioFocusRequest);
            logD("sendCallChangedIntent CALL_STATE_TERMINATED");
            returnAudioFocusIfNecessary();
            return;
        }
        logD("sendCallChangedIntent mLastCallState: " + this.mLastCallState);
        if (this.mLastCallState == state) {
            logD("sendCallChangedIntent same sate, return");
            return;
        }
        this.mLastCallState = state;
        if (state == 4 && this.mAudioFocusRequest == null) {
            logD("sendCallChangedIntent mAudioFocusRequest is null, requestAudioFocus");
            requestFromCallState();
            return;
        }
        logD("sendCallChangedIntent currentMachineState: " + getCurrentState());
        boolean isConnected = isConnected();
        logD("sendCallChangedIntent isConnected: " + isConnected);
        if (isConnected) {
            int audioUsage = getAudioUsageFromCallState(state);
            logD("sendCallChangedIntent audioUsage: " + audioUsage + "  beforeUsage: " + this.usage);
            int i = this.usage;
            if (i != 0 && audioUsage > i) {
                logD("sendCallChangedIntent Disordered order, return");
            } else if (audioUsage != this.usage) {
                releaseAudioPatch();
                if (state != 7) {
                    requestFromCallState();
                }
            }
        }
    }

    private void requestFromCallState() {
        logD("requestFromCallState");
        if (this.mService.isScoRouted()) {
            this.mAudioFocusRequest = requestAudioFocus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean queryCallsStart() {
        logD("queryCallsStart");
        clearPendingAction();
        this.mNativeInterface.queryCurrentCalls(getByteAddress(this.mCurrentDevice));
        addQueuedAction(50, 0);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryCallsDone() {
        logD("queryCallsDone");
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.mCalls.keySet());
        BluetoothHeadsetClientCall cUnassigned = this.mCalls.get(-1);
        if (cUnassigned != null && cUnassigned.getNumber() != null) {
            boolean needAssociate = false;
            logD("Unassigned call exsists, number: " + AesUtil.encrypt(cUnassigned.getNumber()));
            for (Integer idx : this.mCallsUpdate.keySet()) {
                BluetoothHeadsetClientCall cUpdated = this.mCallsUpdate.get(idx);
                if (cUpdated == null || cUpdated.getNumber() == null) {
                    logD("Updated call invalid, idx:" + idx);
                } else {
                    logD("Updated call idx: " + idx + " number: " + AesUtil.encrypt(cUpdated.getNumber()));
                    if (cUnassigned.getNumber().equals(cUpdated.getNumber())) {
                        needAssociate = true;
                    }
                }
            }
            if (!needAssociate) {
                cUnassigned.setState(7);
                logD("Cannot find matched call in CLCC, terminate original out going call.");
                sendCallChangedIntent(cUnassigned);
                this.mCalls.remove(-1);
            }
        }
        hashSet.remove(-1);
        HashSet hashSet2 = new HashSet();
        hashSet2.addAll(this.mCallsUpdate.keySet());
        Set<Integer> callAddedIds = new HashSet<>();
        callAddedIds.addAll(hashSet2);
        callAddedIds.removeAll(hashSet);
        Set<Integer> callRemovedIds = new HashSet<>();
        callRemovedIds.addAll(hashSet);
        callRemovedIds.removeAll(hashSet2);
        Set<Integer> callRetainedIds = new HashSet<>();
        callRetainedIds.addAll(hashSet);
        callRetainedIds.retainAll(hashSet2);
        logD("currCallIdSet " + this.mCalls.keySet() + " newCallIdSet " + hashSet2 + " callAddedIds " + callAddedIds + " callRemovedIds " + callRemovedIds + " callRetainedIds " + callRetainedIds);
        Integer.valueOf(-1);
        if (this.mCalls.containsKey(-1)) {
            long cCreationElapsed = this.mCalls.get(-1).getCreationElapsedMilli();
            if (callAddedIds.size() > 0) {
                logD("Associating the first call with HF originated call");
                Integer hfOriginatedAssoc = (Integer) callAddedIds.toArray()[0];
                Hashtable<Integer, BluetoothHeadsetClientCall> hashtable = this.mCalls;
                hashtable.put(hfOriginatedAssoc, hashtable.get(-1));
                this.mCalls.remove(-1);
                callAddedIds.remove(hfOriginatedAssoc);
                callRetainedIds.add(hfOriginatedAssoc);
            } else if (SystemClock.elapsedRealtime() - cCreationElapsed > OUTGOING_TIMEOUT_MILLI) {
                Log.w(TAG, "Outgoing call did not see a response, clear the calls and send CHUP");
                terminateCall();
                Iterator<Integer> it = this.mCalls.keySet().iterator();
                while (it.hasNext()) {
                    BluetoothHeadsetClientCall c1 = this.mCalls.get(it.next());
                    c1.setState(7);
                    sendCallChangedIntent(c1);
                }
                this.mCalls.clear();
                return;
            }
        }
        logD("ADJUST: currCallIdSet " + this.mCalls.keySet() + " newCallIdSet " + hashSet2 + " callAddedIds " + callAddedIds + " callRemovedIds " + callRemovedIds + " callRetainedIds " + callRetainedIds);
        Iterator<Integer> it2 = callRemovedIds.iterator();
        while (it2.hasNext()) {
            BluetoothHeadsetClientCall c = this.mCalls.remove(it2.next());
            c.setState(7);
            sendCallChangedIntent(c);
        }
        for (Integer idx2 : callAddedIds) {
            BluetoothHeadsetClientCall c2 = this.mCallsUpdate.get(idx2);
            this.mCalls.put(idx2, c2);
            sendCallChangedIntent(c2);
        }
        for (Integer idx3 : callRetainedIds) {
            BluetoothHeadsetClientCall cOrig = this.mCalls.get(idx3);
            BluetoothHeadsetClientCall cUpdate = this.mCallsUpdate.get(idx3);
            cOrig.setNumber(cUpdate.getNumber());
            cOrig.setState(cUpdate.getState());
            cOrig.setMultiParty(cUpdate.isMultiParty());
            sendCallChangedIntent(cOrig);
        }
        if (this.mCalls.size() > 0) {
            if (!this.mService.getResources().getBoolean(R.bool.hfp_clcc_poll_during_call)) {
                if (getCall(4) != null) {
                    logD("Still have incoming call; polling");
                    sendMessageDelayed(50, QUERY_CURRENT_CALLS_WAIT_MILLIS);
                } else {
                    removeMessages(50);
                }
            } else {
                sendMessageDelayed(50, QUERY_CURRENT_CALLS_WAIT_MILLIS);
            }
        }
        this.mCallsUpdate.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryCallsUpdate(int id, int state, String number, boolean multiParty, boolean outgoing) {
        logD("queryCallsUpdate: " + id);
        this.mCallsUpdate.put(Integer.valueOf(id), new BluetoothHeadsetClientCall(this.mCurrentDevice, id, state, number, multiParty, outgoing, this.mInBandRing));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptCall(int flag) {
        int action;
        logD("acceptCall: (" + flag + ")");
        BluetoothHeadsetClientCall c = getCall(4, 5);
        if (c == null && (c = getCall(6, 1)) == null) {
            return;
        }
        logD("Call to accept: " + c);
        int state = c.getState();
        if (state != 1) {
            if (state != 4) {
                if (state != 5) {
                    if (state == 6) {
                        action = 10;
                    } else {
                        return;
                    }
                } else if (callsInState(0) == 0) {
                    if (flag != 0) {
                        return;
                    } else {
                        action = 2;
                    }
                } else if (flag == 1 || flag == 0) {
                    logD("Accepting call with accept and hold");
                    action = 2;
                } else if (flag == 2) {
                    logD("Accepting call with accept and reject");
                    action = 1;
                } else {
                    Log.e(TAG, "Aceept call with invalid flag: " + flag);
                    return;
                }
            } else if (flag != 0) {
                return;
            } else {
                action = 7;
            }
        } else if (flag == 1) {
            action = 2;
        } else if (flag == 2) {
            action = 1;
        } else if (getCall(0) != null) {
            action = 3;
        } else if (flag == 0) {
            action = 2;
        } else {
            action = 2;
        }
        if (flag == 1) {
            routeHfpAudio(true);
        }
        if (this.mNativeInterface.handleCallAction(getByteAddress(this.mCurrentDevice), action, 0)) {
            addQueuedAction(12, action);
            return;
        }
        Log.e(TAG, "ERROR: Couldn't accept a call, action:" + action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void rejectCall() {
        /*
            r5 = this;
            java.lang.String r0 = "rejectCall"
            logD(r0)
            r0 = 4
            int[] r1 = new int[r0]
            r1 = {x0070: FILL_ARRAY_DATA , data: [4, 5, 6, 1} // fill-array
            android.bluetooth.BluetoothHeadsetClientCall r1 = r5.getCall(r1)
            if (r1 != 0) goto L17
            java.lang.String r0 = "No call to reject, returning."
            logD(r0)
            return
        L17:
            int r2 = r1.getState()
            r3 = 1
            if (r2 == r3) goto L2d
            if (r2 == r0) goto L2a
            r0 = 5
            if (r2 == r0) goto L2d
            r0 = 6
            if (r2 == r0) goto L27
            return
        L27:
            r0 = 11
            goto L2f
        L2a:
            r0 = 8
            goto L2f
        L2d:
            r0 = 0
        L2f:
            com.android.bluetooth.hfpclient.NativeInterface r2 = r5.mNativeInterface
            android.bluetooth.BluetoothDevice r3 = r5.mCurrentDevice
            byte[] r3 = r5.getByteAddress(r3)
            r4 = 0
            boolean r2 = r2.handleCallAction(r3, r0, r4)
            if (r2 == 0) goto L58
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Reject call action "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            logD(r2)
            r2 = 13
            r5.addQueuedAction(r2, r0)
            goto L6e
        L58:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "ERROR: Couldn't reject a call, action:"
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "HeadsetClientStateMachine"
            android.util.Log.e(r3, r2)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hfpclient.HeadsetClientStateMachine.rejectCall():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void holdCall() {
        int action;
        logD("holdCall");
        BluetoothHeadsetClientCall c = getCall(4);
        if (c != null) {
            action = 9;
        } else {
            BluetoothHeadsetClientCall c2 = getCall(0);
            if (c2 == null) {
                return;
            } else {
                action = 2;
            }
        }
        if (this.mNativeInterface.handleCallAction(getByteAddress(this.mCurrentDevice), action, 0)) {
            addQueuedAction(14, action);
            return;
        }
        Log.e(TAG, "ERROR: Couldn't hold a call, action:" + action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void terminateCall() {
        logD("terminateCall");
        int action = 8;
        BluetoothHeadsetClientCall c = getCall(2, 3, 0);
        if (c == null) {
            c = getCall(1);
            action = 0;
        }
        if (c != null) {
            if (this.mNativeInterface.handleCallAction(getByteAddress(this.mCurrentDevice), action, 0)) {
                addQueuedAction(15, action);
            } else {
                Log.e(TAG, "ERROR: Couldn't terminate outgoing call");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enterPrivateMode(int idx) {
        logD("enterPrivateMode: " + idx);
        BluetoothHeadsetClientCall c = this.mCalls.get(Integer.valueOf(idx));
        if (c == null || c.getState() != 0 || !c.isMultiParty()) {
            return;
        }
        if (this.mNativeInterface.handleCallAction(getByteAddress(this.mCurrentDevice), 6, idx)) {
            addQueuedAction(16, c);
            return;
        }
        Log.e(TAG, "ERROR: Couldn't enter private  id:" + idx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseCall(int idx) {
        if (DBG) {
            Log.d(TAG, "releaseCall: " + idx);
        }
        BluetoothHeadsetClientCall c = this.mCalls.get(Integer.valueOf(idx));
        if (c == null || c.getState() != 0) {
            return;
        }
        if (this.mNativeInterface.handleCallAction(getByteAddress(this.mCurrentDevice), 5, idx)) {
            addQueuedAction(22, c);
            return;
        }
        Log.e(TAG, "ERROR: Couldn't release call  id:" + idx);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void explicitCallTransfer() {
        logD("explicitCallTransfer");
        if (this.mCalls.size() < 2) {
            return;
        }
        if (this.mNativeInterface.handleCallAction(getByteAddress(this.mCurrentDevice), 4, -1)) {
            addQueuedAction(18);
        } else {
            Log.e(TAG, "ERROR: Couldn't transfer call");
        }
    }

    public Bundle getCurrentAgFeatures() {
        Bundle b = new Bundle();
        if ((this.mPeerFeatures & 1) == 1) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_3WAY_CALLING", true);
        }
        if ((this.mPeerFeatures & 4) == 4) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_VOICE_RECOGNITION", true);
        }
        if ((this.mPeerFeatures & 32) == 32) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_REJECT_CALL", true);
        }
        if ((this.mPeerFeatures & 128) == 128) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ECC", true);
        }
        if ((this.mChldFeatures & 8) == 8) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL", true);
        }
        if ((this.mChldFeatures & 1) == 1) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL", true);
        }
        if ((this.mChldFeatures & 2) == 2) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT", true);
        }
        if ((this.mChldFeatures & 32) == 32) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE", true);
        }
        if ((this.mChldFeatures & 64) == 64) {
            b.putBoolean("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE_AND_DETACH", true);
        }
        return b;
    }

    HeadsetClientStateMachine(HeadsetClientService context, Looper looper, NativeInterface nativeInterface) {
        super(TAG, looper);
        this.mClccTimer = 0L;
        this.mLastCallState = -1;
        this.mCalls = new Hashtable<>();
        this.mCallsUpdate = new Hashtable<>();
        this.mCurrentDevice = null;
        this.mAudioPatch = null;
        this.mVolumeChangeCallback = new CarAudioManager.CarVolumeCallback() { // from class: com.android.bluetooth.hfpclient.HeadsetClientStateMachine.1
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
                HeadsetClientStateMachine.logD("zoneId:" + zoneId + ", groupId:" + groupId);
                if (zoneId == 0 && groupId == HeadsetClientStateMachine.mVolumeGroupId) {
                    int streamValue = 0;
                    try {
                        streamValue = HeadsetClientStateMachine.this.mCarAudioManager.getGroupVolume(zoneId, groupId);
                    } catch (CarNotConnectedException exception) {
                        Log.e(HeadsetClientStateMachine.TAG, "Car is not connected", exception);
                    } catch (NullPointerException exception2) {
                        Log.e(HeadsetClientStateMachine.TAG, "mCarAudioManager is NULL!", exception2);
                    }
                    int hfVol = HeadsetClientStateMachine.amToHfVol(streamValue);
                    HeadsetClientStateMachine.logD("Setting volume to audio manager: " + streamValue + " hands free: " + hfVol);
                    HeadsetClientStateMachine.this.sendMessage(8, streamValue);
                }
            }
        };
        this.mConnection = new ServiceConnection() { // from class: com.android.bluetooth.hfpclient.HeadsetClientStateMachine.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    HeadsetClientStateMachine.this.mCarAudioManager = (CarAudioManager) HeadsetClientStateMachine.this.mCar.getCarManager("audio");
                    int unused = HeadsetClientStateMachine.mVolumeGroupId = HeadsetClientStateMachine.this.mCarAudioManager.getVolumeGroupIdForUsage(2);
                    HeadsetClientStateMachine.logD("mVolumeGroupId:" + HeadsetClientStateMachine.mVolumeGroupId);
                    HeadsetClientStateMachine.this.mCarEnhancedAudioManager = (CarEnhancedAudioManager) HeadsetClientStateMachine.this.mCar.getCarManager("enhanced_audio");
                    int unused2 = HeadsetClientStateMachine.sMaxAmVcVol = HeadsetClientStateMachine.this.mCarAudioManager.getGroupMaxVolume(HeadsetClientStateMachine.mVolumeGroupId);
                    int unused3 = HeadsetClientStateMachine.sMinAmVcVol = HeadsetClientStateMachine.this.mCarAudioManager.getGroupMinVolume(HeadsetClientStateMachine.mVolumeGroupId);
                    HeadsetClientStateMachine.this.mCarAudioManager.registerCarVolumeCallback(HeadsetClientStateMachine.this.mVolumeChangeCallback);
                } catch (CarNotConnectedException exception) {
                    Log.e(HeadsetClientStateMachine.TAG, "Car is not connected!", exception);
                } catch (NullPointerException exception2) {
                    Log.e(HeadsetClientStateMachine.TAG, "mCarAudioManager is NULL!", exception2);
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                Log.e(HeadsetClientStateMachine.TAG, "Car service is disconnected");
            }
        };
        this.focusListener2 = new AudioManager.OnAudioFocusChangeListener() { // from class: com.android.bluetooth.hfpclient.HeadsetClientStateMachine.3
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int action) {
                HeadsetClientStateMachine.logD("focusListener2 onAudioFocusChange action: " + action);
                HeadsetClientStateMachine.this.audioFocusLossHandle(action, 2);
            }
        };
        this.focusListener6 = new AudioManager.OnAudioFocusChangeListener() { // from class: com.android.bluetooth.hfpclient.HeadsetClientStateMachine.4
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int action) {
                HeadsetClientStateMachine.logD("focusListener6 onAudioFocusChange action: " + action);
                HeadsetClientStateMachine.this.audioFocusLossHandle(action, 6);
            }
        };
        this.mService = context;
        this.mNativeInterface = nativeInterface;
        this.mAudioManager = context.getAudioManager();
        Car carCreateCar = Car.createCar(context, this.mConnection);
        this.mCar = carCreateCar;
        carCreateCar.connect();
        this.mVendorProcessor = new VendorCommandResponseProcessor(this.mService, this.mNativeInterface);
        this.mAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mAudioState = 0;
        this.mAudioWbs = false;
        this.mVoiceRecognitionActive = 0;
        this.mIndicatorNetworkState = 0;
        this.mIndicatorNetworkType = 0;
        this.mIndicatorNetworkSignal = 0;
        this.mIndicatorBatteryLevel = 0;
        this.mOperatorName = null;
        this.mSubscriberInfo = null;
        this.mQueuedActions = new LinkedList();
        clearPendingAction();
        this.mCalls.clear();
        this.mCallsUpdate.clear();
        this.mDisconnected = new Disconnected();
        this.mConnecting = new Connecting();
        this.mConnected = new Connected();
        this.mAudioOn = new AudioOn();
        addState(this.mDisconnected);
        addState(this.mConnecting);
        addState(this.mConnected);
        addState(this.mAudioOn, this.mConnected);
        setInitialState(this.mDisconnected);
    }

    static HeadsetClientStateMachine make(HeadsetClientService context, Looper looper, NativeInterface nativeInterface) {
        logD("make");
        HeadsetClientStateMachine hfcsm = new HeadsetClientStateMachine(context, looper, nativeInterface);
        hfcsm.start();
        return hfcsm;
    }

    synchronized void routeHfpAudio(boolean enable) {
        if (this.mAudioManager == null) {
            Log.e(TAG, "AudioManager is null!");
            return;
        }
        logD("hfp_enable=" + enable);
        if (enable && !sAudioIsRouted) {
            this.mAudioManager.setParameters("hfp_enable=true");
        } else if (!enable) {
            this.mAudioManager.setParameters("hfp_enable=false");
        }
        sAudioIsRouted = enable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized AudioFocusRequest requestAudioFocus() {
        int[] usageArray = getAttributeUsage();
        if (usageArray == null) {
            logD("requestAudioFocus usageArray == null");
            return this.mAudioFocusRequest;
        }
        if (usageArray[0] == 6) {
            logD("requestAudioFocus success,currentUsage is ringtone,setUnMute");
            setUnMute();
        }
        AudioAttributes streamAttributes = new AudioAttributes.Builder().setUsage(usageArray[0]).setContentType(1).build();
        AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(2).setAudioAttributes(streamAttributes).setOnAudioFocusChangeListener(usageArray[0] == 6 ? this.focusListener6 : this.focusListener2).build();
        int focusRequestStatus = this.mAudioManager.requestAudioFocus(focusRequest);
        String s = focusRequestStatus == 1 ? "AudioFocus granted" : "AudioFocus NOT granted";
        logD("AudioManager requestAudioFocus returned: " + s);
        if (focusRequestStatus != 1) {
            this.usage = 0;
            this.mLastCallState = -1;
            logD("AudioManager requestAudioFocus NOT granted , return mAudioFocusRequest");
            if (this.mService.isScoRouted() && isAudioOn() && this.mNativeInterface.disconnectAudio(getByteAddress(this.mCurrentDevice))) {
                logD("requestAudioFocus NOT granted, disconnectAudio");
            }
        }
        if (focusRequestStatus == 1) {
            createAudioPatch(usageArray[1]);
        }
        return focusRequest;
    }

    private int[] getAttributeUsage() {
        int currentUsage = getAudioUsage();
        logD("getAttributeUsage currentUsage: " + currentUsage + "; oldusage: " + this.usage);
        if (this.usage == currentUsage && this.mAudioFocusRequest != null) {
            logD("getAttributeUsage usage == currentUsage mAudioFocusRequest: " + this.mAudioFocusRequest);
            return null;
        }
        this.usage = currentUsage;
        logD("getAttributeUsage returned: " + this.usage);
        int[] usageArray = new int[2];
        int i = this.usage;
        if (i == 6) {
            usageArray[0] = 6;
            usageArray[1] = 6;
        } else if (i == 2) {
            usageArray[0] = 2;
            usageArray[1] = 2;
        }
        logD("getAttributeUsage returned: " + usageArray[0] + "   " + usageArray[1]);
        return usageArray;
    }

    public void doQuit() {
        logD("doQuit");
        BluetoothDevice bluetoothDevice = this.mCurrentDevice;
        if (bluetoothDevice != null) {
            this.mNativeInterface.disconnect(getByteAddress(bluetoothDevice));
        }
        routeHfpAudio(false);
        returnAudioFocusIfNecessary();
        try {
            this.mCarAudioManager.unregisterCarVolumeCallback(this.mVolumeChangeCallback);
        } catch (CarNotConnectedException exception) {
            Log.e(TAG, "Car is not connected", exception);
        } catch (NullPointerException exception2) {
            Log.e(TAG, "mCarAudioManager is NULL!", exception2);
        }
        Car car = this.mCar;
        if (car != null && car.isConnected()) {
            this.mCar.disconnect();
            this.mCar = null;
        }
        quitNow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void returnAudioFocusIfNecessary() {
        AudioFocusRequest audioFocusRequest = this.mAudioFocusRequest;
        if (audioFocusRequest == null) {
            logD("returnAudioFocusIfNecessary  mAudioFocusRequest is null");
        } else {
            int result = this.mAudioManager.abandonAudioFocusRequest(audioFocusRequest);
            logD("returnAudioFocusIfNecessary  abandonAudioFocusRequest :" + result);
        }
        logD("returnAudioFocusIfNecessary  abandonAudioFocusRequest :  mAudioPatch:" + this.mAudioPatch);
        releaseAudioPatch();
        this.usage = 0;
        this.mLastCallState = -1;
        this.mAudioFocusRequest = null;
    }

    private void returnAudioFocusIfNecessaryWithCallState(AudioFocusRequest audioFocusRequest, CarAudioPatchHandle patch) {
        if (audioFocusRequest == null) {
            logD("returnAudioFocusIfNecessary  audioFocusRequest is null");
            return;
        }
        int result = this.mAudioManager.abandonAudioFocusRequest(audioFocusRequest);
        logD("returnAudioFocusIfNecessary  abandonAudioFocusRequest :" + result + "  mPatch:" + patch);
        if (patch != null) {
            logD("releaseAudioPatch" + patch.toString());
            try {
                this.mCarAudioManager.releaseAudioPatch(patch);
            } catch (CarNotConnectedException exception) {
                Log.e(TAG, "Car is not connected", exception);
            } catch (NullPointerException exception2) {
                Log.e(TAG, "mCarAudioManager is NULL!", exception2);
            }
        }
    }

    static int hfToAmVol(int hfVol) {
        int i = sMaxAmVcVol;
        int i2 = sMinAmVcVol;
        int amRange = i - i2;
        int amOffset = ((hfVol + 0) * amRange) / 15;
        int amVol = i2 + amOffset;
        logD("HF -> AM " + hfVol + " " + amVol);
        return amVol;
    }

    static int amToHfVol(int amVol) {
        int hfOffset;
        int i = sMaxAmVcVol;
        int i2 = sMinAmVcVol;
        int amRange = i > i2 ? i - i2 : 1;
        if (amRange != 0) {
            hfOffset = ((amVol - sMinAmVcVol) * 15) / amRange;
        } else {
            hfOffset = 7;
        }
        int hfOffset2 = hfOffset + 0;
        logD("AM -> HF " + amVol + " " + hfOffset2);
        return hfOffset2;
    }

    private int getAudioUsage() {
        logD("getAudioUsage mCalls: " + this.mCalls.size());
        for (BluetoothHeadsetClientCall value : this.mCalls.values()) {
            logD("getAudioUsage  value: " + value.getState());
        }
        if (getCall(0, 2, 3) != null) {
            return 2;
        }
        return getCall(4) != null ? 6 : 6;
    }

    private int getAudioUsageFromCallState(int state) {
        logD("getAudioUsageFromCallState state: " + state);
        if (state == 0 || state == 2 || state == 3) {
            return 2;
        }
        return state == 4 ? 6 : 6;
    }

    private CarAudioPatchHandle createAudioPatchHandle(int usage) {
        if (usage == 6) {
            logD("createAudioPatchHandle: BUS: bus7_bt_dnlink_in USAGE: " + usage);
            try {
                return this.mCarAudioManager.createAudioPatch("bus7_bt_dnlink_in", usage, 0);
            } catch (CarNotConnectedException exception) {
                Log.e(TAG, "Car is not connected", exception);
            } catch (NullPointerException exception2) {
                Log.e(TAG, "mCarAudioManager is NULL!", exception2);
            }
        }
        logD("createAudioPatchHandle: BUS: bus7_bt_dnlink_in USAGE: " + usage);
        try {
            return this.mCarAudioManager.createAudioPatch("bus7_bt_dnlink_in", usage, 0);
        } catch (CarNotConnectedException exception3) {
            Log.e(TAG, "Car is not connected", exception3);
            return null;
        } catch (NullPointerException exception4) {
            Log.e(TAG, "mCarAudioManager is NULL!", exception4);
            return null;
        }
    }

    private void createAudioPatch(int type) {
        logD("createAudioPatch enter");
        String audioAddress = null;
        ArrayList<AudioPort> audioPorts = new ArrayList<>();
        AudioManager.listAudioPorts(audioPorts);
        AudioPortConfig srcPortConfig = null;
        AudioPortConfig sinkPortConfig = null;
        logD("createAudioPatch mSamplingRate: " + this.mSamplingRate);
        if (this.mSamplingRate == 0) {
            this.mSamplingRate = SAMPLING_RATE_WIDE;
            logD("change mSamplingRate from 0 to " + this.mSamplingRate);
        }
        for (AudioPort audioPort : audioPorts) {
            if (srcPortConfig == null && audioPort.role() == 1 && (audioPort instanceof AudioDevicePort)) {
                AudioDevicePort audioDevicePort = (AudioDevicePort) audioPort;
                audioAddress = "bus7_bt_dnlink_in";
                if (audioDevicePort.address().equals("bus7_bt_dnlink_in")) {
                    srcPortConfig = audioPort.buildConfig(this.mSamplingRate, 1, 1, (AudioGainConfig) null);
                    Log.d(TAG, "Found srcPortConfig : " + srcPortConfig);
                }
            } else if (sinkPortConfig == null && audioPort.role() == 2 && (audioPort instanceof AudioDevicePort)) {
                AudioDevicePort sinkDevicePort = (AudioDevicePort) audioPort;
                if (type == 2) {
                    audioAddress = "bus4_call_out";
                } else if (type == 6) {
                    audioAddress = "bus3_call_ring_out";
                }
                if (sinkDevicePort.address().equals(audioAddress)) {
                    sinkPortConfig = audioPort.buildConfig(this.mSamplingRate, 1, 1, (AudioGainConfig) null);
                    logD("Found sinkPortConfig : " + sinkPortConfig);
                }
            }
        }
        if (srcPortConfig != null && sinkPortConfig != null) {
            AudioPatch[] audioPatchArray = {this.mAudioPatch};
            int status = AudioManager.createAudioPatch(audioPatchArray, new AudioPortConfig[]{srcPortConfig}, new AudioPortConfig[]{sinkPortConfig});
            this.mAudioPatch = audioPatchArray[0];
            logD("createAudioPatch result: " + status + "  audioPath: " + this.mAudioPatch.toString());
        }
    }

    private void releaseAudioPatch() {
        if (this.mAudioPatch != null) {
            logD("releaseAudioPatch" + this.mAudioPatch.toString());
            int result = AudioManager.releaseAudioPatch(this.mAudioPatch);
            logD("releaseAudioPatch result: " + result);
            this.mAudioPatch = null;
        }
    }

    class Disconnected extends State {
        Disconnected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            HeadsetClientStateMachine.logD("Enter Disconnected: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.mIndicatorNetworkState = 0;
            HeadsetClientStateMachine.this.mIndicatorNetworkType = 0;
            HeadsetClientStateMachine.this.mIndicatorNetworkSignal = 0;
            HeadsetClientStateMachine.this.mIndicatorBatteryLevel = 0;
            HeadsetClientStateMachine.this.mInBandRing = false;
            HeadsetClientStateMachine.this.mAudioWbs = false;
            HeadsetClientStateMachine.this.mOperatorName = null;
            HeadsetClientStateMachine.this.mSubscriberInfo = null;
            HeadsetClientStateMachine.this.mQueuedActions = new LinkedList();
            HeadsetClientStateMachine.this.clearPendingAction();
            HeadsetClientStateMachine.this.mCalls.clear();
            HeadsetClientStateMachine.this.mCallsUpdate.clear();
            HeadsetClientStateMachine.this.mPeerFeatures = 0;
            HeadsetClientStateMachine.this.mChldFeatures = 0;
            HeadsetClientStateMachine.this.removeMessages(50);
            if (HeadsetClientStateMachine.this.mPrevState != HeadsetClientStateMachine.this.mConnecting) {
                if (HeadsetClientStateMachine.this.mPrevState != HeadsetClientStateMachine.this.mConnected && HeadsetClientStateMachine.this.mPrevState != HeadsetClientStateMachine.this.mAudioOn) {
                    if (HeadsetClientStateMachine.this.mPrevState != null) {
                        Log.e(HeadsetClientStateMachine.TAG, "Connected: Illegal state transition from " + HeadsetClientStateMachine.this.mPrevState.getName() + " to Connecting, mCurrentDevice=" + HeadsetClientStateMachine.this.mCurrentDevice);
                    }
                } else {
                    HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                    headsetClientStateMachine.broadcastConnectionState(headsetClientStateMachine.mCurrentDevice, 0, 2);
                    List<BluetoothDevice> hfpDevices = HeadsetClientStateMachine.this.mService.getConnectedDevices();
                    Log.e(HeadsetClientStateMachine.TAG, "Disconnected: HFPConnectedDevices num: " + hfpDevices.size());
                    HeadsetClientStateMachine.this.returnAudioFocusIfNecessary();
                }
            } else {
                HeadsetClientStateMachine headsetClientStateMachine2 = HeadsetClientStateMachine.this;
                headsetClientStateMachine2.broadcastConnectionState(headsetClientStateMachine2.mCurrentDevice, 0, 1);
            }
            HeadsetClientStateMachine.this.mCurrentDevice = null;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public synchronized boolean processMessage(Message message) {
            HeadsetClientStateMachine.logD("Disconnected process message: " + message.what);
            if (HeadsetClientStateMachine.this.mCurrentDevice != null) {
                Log.e(HeadsetClientStateMachine.TAG, "ERROR: current device not null in Disconnected");
                return false;
            }
            int i = message.what;
            if (i == 1) {
                BluetoothDevice device = (BluetoothDevice) message.obj;
                if (!HeadsetClientStateMachine.this.mNativeInterface.connect(HeadsetClientStateMachine.this.getByteAddress(device))) {
                    HeadsetClientStateMachine.this.broadcastConnectionState(device, 0, 0);
                } else {
                    HeadsetClientStateMachine.this.mCurrentDevice = device;
                    HeadsetClientStateMachine.this.transitionTo(HeadsetClientStateMachine.this.mConnecting);
                }
            } else if (i != 2) {
                if (i != 100) {
                    return false;
                }
                StackEvent event = (StackEvent) message.obj;
                HeadsetClientStateMachine.logD("Stack event type: " + event.type);
                if (event.type == 1) {
                    HeadsetClientStateMachine.logD("Disconnected: Connection " + event.device + " state changed:" + event.valueInt);
                    processConnectionEvent(event.valueInt, event.device);
                } else {
                    Log.e(HeadsetClientStateMachine.TAG, "Disconnected: Unexpected stack event: " + event.type);
                }
            }
            return true;
        }

        private void processConnectionEvent(int state, BluetoothDevice device) {
            if (state == 2) {
                Log.w(HeadsetClientStateMachine.TAG, "HFPClient Connecting from Disconnected state");
                if (HeadsetClientStateMachine.this.okToConnect(device)) {
                    Log.i(HeadsetClientStateMachine.TAG, "Incoming AG accepted");
                    HeadsetClientStateMachine.this.mCurrentDevice = device;
                    HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                    headsetClientStateMachine.transitionTo(headsetClientStateMachine.mConnecting);
                    return;
                }
                Log.i(HeadsetClientStateMachine.TAG, "Incoming AG rejected. connectionPolicy=" + HeadsetClientStateMachine.this.mService.getConnectionPolicy(device) + " bondState=" + device.getBondState());
                HeadsetClientStateMachine.this.mNativeInterface.disconnect(HeadsetClientStateMachine.this.getByteAddress(device));
                AdapterService.getAdapterService();
                HeadsetClientStateMachine.this.broadcastConnectionState(device, 0, 0);
                return;
            }
            Log.i(HeadsetClientStateMachine.TAG, "ignoring state: " + state);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetClientStateMachine.logD("Exit Disconnected: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.mPrevState = this;
        }
    }

    class Connecting extends State {
        Connecting() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            HeadsetClientStateMachine.logD("Enter Connecting: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.sendMessageDelayed(53, HeadsetClientStateMachine.OUTGOING_TIMEOUT_MILLI);
            if (HeadsetClientStateMachine.this.mPrevState != HeadsetClientStateMachine.this.mDisconnected) {
                String prevStateName = HeadsetClientStateMachine.this.mPrevState == null ? "null" : HeadsetClientStateMachine.this.mPrevState.getName();
                Log.e(HeadsetClientStateMachine.TAG, "Connected: Illegal state transition from " + prevStateName + " to Connecting, mCurrentDevice=" + HeadsetClientStateMachine.this.mCurrentDevice);
                return;
            }
            HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
            headsetClientStateMachine.broadcastConnectionState(headsetClientStateMachine.mCurrentDevice, 1, 0);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public synchronized boolean processMessage(Message message) {
            HeadsetClientStateMachine.logD("Connecting process message: " + message.what);
            int i = message.what;
            if (i == 1 || i == 2 || i == 3) {
                HeadsetClientStateMachine.this.deferMessage(message);
            } else if (i == 53) {
                Log.w(HeadsetClientStateMachine.TAG, "Connection timeout for " + HeadsetClientStateMachine.this.mCurrentDevice);
                HeadsetClientStateMachine.this.transitionTo(HeadsetClientStateMachine.this.mDisconnected);
            } else if (i == 100) {
                StackEvent event = (StackEvent) message.obj;
                HeadsetClientStateMachine.logD("Connecting: event type: " + event.type);
                switch (event.type) {
                    case 1:
                        HeadsetClientStateMachine.logD("Connecting: Connection " + event.device + " state changed:" + event.valueInt);
                        processConnectionEvent(event.valueInt, event.valueInt2, event.valueInt3, event.device);
                        break;
                    case 2:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 16:
                        HeadsetClientStateMachine.this.deferMessage(message);
                        break;
                    case 3:
                    case 8:
                    case 15:
                    default:
                        Log.e(HeadsetClientStateMachine.TAG, "Connecting: ignoring stack event: " + event.type);
                        break;
                }
            } else {
                Log.w(HeadsetClientStateMachine.TAG, "Message not handled " + message);
                return false;
            }
            return true;
        }

        private void processConnectionEvent(int state, int peerFeat, int chldFeat, BluetoothDevice device) {
            if (state == 0) {
                HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                headsetClientStateMachine.transitionTo(headsetClientStateMachine.mDisconnected);
                return;
            }
            if (state == 1) {
                HeadsetClientStateMachine.logD("outgoing connection started, ignore");
                return;
            }
            if (state == 2) {
                if (!HeadsetClientStateMachine.this.mCurrentDevice.equals(device)) {
                    Log.w(HeadsetClientStateMachine.TAG, "incoming connection event, device: " + device);
                    HeadsetClientStateMachine headsetClientStateMachine2 = HeadsetClientStateMachine.this;
                    headsetClientStateMachine2.broadcastConnectionState(headsetClientStateMachine2.mCurrentDevice, 0, 1);
                    HeadsetClientStateMachine.this.broadcastConnectionState(device, 1, 0);
                    HeadsetClientStateMachine.this.mCurrentDevice = device;
                    return;
                }
                return;
            }
            if (state == 3) {
                HeadsetClientStateMachine.logD("HFPClient Connected from Connecting state");
                HeadsetClientStateMachine.this.mPeerFeatures = peerFeat;
                HeadsetClientStateMachine.this.mChldFeatures = chldFeat;
                if ((HeadsetClientStateMachine.this.mPeerFeatures & 64) == 0) {
                    HeadsetClientStateMachine.this.mNativeInterface.disconnect(HeadsetClientStateMachine.this.getByteAddress(device));
                    return;
                }
                if ((HeadsetClientStateMachine.this.mPeerFeatures & 2) == 2) {
                    NativeInterface nativeInterface = HeadsetClientStateMachine.this.mNativeInterface;
                    HeadsetClientStateMachine headsetClientStateMachine3 = HeadsetClientStateMachine.this;
                    if (nativeInterface.sendATCmd(headsetClientStateMachine3.getByteAddress(headsetClientStateMachine3.mCurrentDevice), 15, 1, 0, null)) {
                        HeadsetClientStateMachine.this.addQueuedAction(20);
                    } else {
                        Log.e(HeadsetClientStateMachine.TAG, "Failed to send NREC");
                    }
                }
                int amVol = 0;
                try {
                    amVol = HeadsetClientStateMachine.this.mCarAudioManager.getGroupVolume(HeadsetClientStateMachine.mVolumeGroupId);
                } catch (CarNotConnectedException exception) {
                    Log.e(HeadsetClientStateMachine.TAG, "Car is not connected", exception);
                } catch (NullPointerException exception2) {
                    Log.e(HeadsetClientStateMachine.TAG, "mCarAudioManager is NULL!", exception2);
                }
                HeadsetClientStateMachine headsetClientStateMachine4 = HeadsetClientStateMachine.this;
                headsetClientStateMachine4.deferMessage(headsetClientStateMachine4.obtainMessage(8, amVol, 0));
                HeadsetClientStateMachine headsetClientStateMachine5 = HeadsetClientStateMachine.this;
                headsetClientStateMachine5.deferMessage(headsetClientStateMachine5.obtainMessage(7, headsetClientStateMachine5.mAudioManager.isMicrophoneMute() ? 0 : 15, 0));
                HeadsetClientStateMachine headsetClientStateMachine6 = HeadsetClientStateMachine.this;
                headsetClientStateMachine6.deferMessage(headsetClientStateMachine6.obtainMessage(52));
                HeadsetClientStateMachine headsetClientStateMachine7 = HeadsetClientStateMachine.this;
                headsetClientStateMachine7.transitionTo(headsetClientStateMachine7.mConnected);
                return;
            }
            Log.e(HeadsetClientStateMachine.TAG, "Incorrect state: " + state);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetClientStateMachine.logD("Exit Connecting: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.removeMessages(53);
            HeadsetClientStateMachine.this.mPrevState = this;
        }
    }

    class Connected extends State {
        int mCommandedSpeakerVolume = -1;

        Connected() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            HeadsetClientStateMachine.logD("Enter Connected: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.mAudioWbs = false;
            this.mCommandedSpeakerVolume = -1;
            if (HeadsetClientStateMachine.this.mPrevState != HeadsetClientStateMachine.this.mConnecting) {
                if (HeadsetClientStateMachine.this.mPrevState != HeadsetClientStateMachine.this.mAudioOn) {
                    String prevStateName = HeadsetClientStateMachine.this.mPrevState == null ? "null" : HeadsetClientStateMachine.this.mPrevState.getName();
                    Log.e(HeadsetClientStateMachine.TAG, "Connected: Illegal state transition from " + prevStateName + " to Connecting, mCurrentDevice=" + HeadsetClientStateMachine.this.mCurrentDevice);
                    return;
                }
                return;
            }
            HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
            headsetClientStateMachine.broadcastConnectionState(headsetClientStateMachine.mCurrentDevice, 2, 1);
            MetricsLogger.logProfileConnectionEvent(BluetoothMetricsProto.ProfileId.HEADSET_CLIENT);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public synchronized boolean processMessage(Message message) {
            HeadsetClientStateMachine.logD("Connected process message: " + message.what);
            if (HeadsetClientStateMachine.DBG && HeadsetClientStateMachine.this.mCurrentDevice == null) {
                Log.e(HeadsetClientStateMachine.TAG, "ERROR: mCurrentDevice is null in Connected");
                return false;
            }
            int i = message.what;
            if (i == 10) {
                BluetoothHeadsetClientCall c = (BluetoothHeadsetClientCall) message.obj;
                HeadsetClientStateMachine.this.mCalls.put(-1, c);
                if (HeadsetClientStateMachine.this.mNativeInterface.dial(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice), c.getNumber())) {
                    HeadsetClientStateMachine.this.addQueuedAction(10, c.getNumber());
                    HeadsetClientStateMachine.this.sendMessage(50);
                } else {
                    Log.e(HeadsetClientStateMachine.TAG, "ERROR: Cannot dial with a given number:" + AesUtil.encrypt((String) message.obj));
                    c.setState(7);
                    HeadsetClientStateMachine.this.sendCallChangedIntent(c);
                    HeadsetClientStateMachine.this.mCalls.remove(-1);
                }
            } else if (i == 50) {
                HeadsetClientStateMachine.this.removeMessages(50);
                if (HeadsetClientStateMachine.this.mCalls.size() > 0) {
                    HeadsetClientStateMachine.this.sendMessageDelayed(50, HeadsetClientStateMachine.QUERY_CURRENT_CALLS_WAIT_MILLIS);
                }
                HeadsetClientStateMachine.this.queryCallsStart();
            } else if (i != 52) {
                if (i != 100) {
                    if (i != 21) {
                        if (i == 22) {
                            HeadsetClientStateMachine.this.releaseCall(message.arg1);
                        } else {
                            switch (i) {
                                case 1:
                                    BluetoothDevice device = (BluetoothDevice) message.obj;
                                    if (!HeadsetClientStateMachine.this.mCurrentDevice.equals(device)) {
                                        HeadsetClientStateMachine.this.mNativeInterface.connect(HeadsetClientStateMachine.this.getByteAddress(device));
                                    }
                                    break;
                                case 2:
                                    BluetoothDevice dev = (BluetoothDevice) message.obj;
                                    if (HeadsetClientStateMachine.this.mCurrentDevice.equals(dev) && !HeadsetClientStateMachine.this.mNativeInterface.disconnect(HeadsetClientStateMachine.this.getByteAddress(dev))) {
                                        Log.e(HeadsetClientStateMachine.TAG, "disconnectNative failed for " + dev);
                                    }
                                    break;
                                case 3:
                                    if (HeadsetClientStateMachine.this.mNativeInterface.connectAudio(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice))) {
                                        HeadsetClientStateMachine.this.mAudioState = 1;
                                    } else {
                                        Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't connect Audio for device " + HeadsetClientStateMachine.this.mCurrentDevice);
                                        HeadsetClientStateMachine.this.broadcastAudioState(HeadsetClientStateMachine.this.mCurrentDevice, 0, 0);
                                    }
                                    break;
                                case 4:
                                    if (!HeadsetClientStateMachine.this.mNativeInterface.disconnectAudio(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice))) {
                                        Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't disconnect Audio for device " + HeadsetClientStateMachine.this.mCurrentDevice);
                                    }
                                    break;
                                case 5:
                                    if (HeadsetClientStateMachine.this.mVoiceRecognitionActive == 0) {
                                        if (HeadsetClientStateMachine.this.mNativeInterface.startVoiceRecognition(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice))) {
                                            HeadsetClientStateMachine.this.addQueuedAction(5);
                                        } else {
                                            Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't start voice recognition");
                                        }
                                    }
                                    break;
                                case 6:
                                    if (HeadsetClientStateMachine.this.mVoiceRecognitionActive == 1) {
                                        if (HeadsetClientStateMachine.this.mNativeInterface.stopVoiceRecognition(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice))) {
                                            HeadsetClientStateMachine.this.addQueuedAction(6);
                                        } else {
                                            Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't stop voice recognition");
                                        }
                                    }
                                    break;
                                case 7:
                                    break;
                                case 8:
                                    int amVol = message.arg1;
                                    int hfVol = HeadsetClientStateMachine.amToHfVol(amVol);
                                    HeadsetClientStateMachine.this.mAudioManager.setParameters("hfp_volume=" + hfVol);
                                    if (amVol != this.mCommandedSpeakerVolume) {
                                        HeadsetClientStateMachine.logD("Volume" + amVol + ":" + this.mCommandedSpeakerVolume);
                                        this.mCommandedSpeakerVolume = -1;
                                        if (HeadsetClientStateMachine.this.mNativeInterface.setVolume(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice), 0, hfVol)) {
                                            HeadsetClientStateMachine.this.addQueuedAction(8);
                                        }
                                    }
                                    break;
                                default:
                                    switch (i) {
                                        case 12:
                                            HeadsetClientStateMachine.this.acceptCall(message.arg1);
                                            break;
                                        case 13:
                                            HeadsetClientStateMachine.this.rejectCall();
                                            break;
                                        case 14:
                                            HeadsetClientStateMachine.this.holdCall();
                                            break;
                                        case 15:
                                            HeadsetClientStateMachine.this.terminateCall();
                                            break;
                                        case 16:
                                            HeadsetClientStateMachine.this.enterPrivateMode(message.arg1);
                                            break;
                                        case 17:
                                            if (HeadsetClientStateMachine.this.mNativeInterface.sendDtmf(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice), (byte) message.arg1)) {
                                                HeadsetClientStateMachine.this.addQueuedAction(17);
                                            } else {
                                                Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't send DTMF");
                                            }
                                            break;
                                        case 18:
                                            HeadsetClientStateMachine.this.explicitCallTransfer();
                                            break;
                                        default:
                                            return false;
                                    }
                                    break;
                            }
                        }
                    } else {
                        int vendorId = message.arg1;
                        String atCommand = (String) message.obj;
                        HeadsetClientStateMachine.this.mVendorProcessor.sendCommand(vendorId, atCommand, HeadsetClientStateMachine.this.mCurrentDevice);
                    }
                } else {
                    StackEvent event = (StackEvent) message.obj;
                    HeadsetClientStateMachine.logD("Connected: event type: " + event.type);
                    switch (event.type) {
                        case 1:
                            HeadsetClientStateMachine.logD("Connected: Connection state changed: " + event.device + ": " + event.valueInt);
                            processConnectionEvent(event.valueInt, event.device);
                            break;
                        case 2:
                            HeadsetClientStateMachine.logD("Connected: Audio state changed: " + event.device + ": " + event.valueInt);
                            processAudioEvent(event.valueInt, event.device);
                            break;
                        case 3:
                            int oldState = HeadsetClientStateMachine.this.mVoiceRecognitionActive;
                            HeadsetClientStateMachine.this.mVoiceRecognitionActive = event.valueInt;
                            broadcastVoiceRecognitionStateChanged(event.device, oldState, HeadsetClientStateMachine.this.mVoiceRecognitionActive);
                            break;
                        case 4:
                            HeadsetClientStateMachine.logD("Connected: Network state: " + event.valueInt);
                            HeadsetClientStateMachine.this.mIndicatorNetworkState = event.valueInt;
                            Intent intent = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            intent.putExtra("android.bluetooth.headsetclient.extra.NETWORK_STATUS", event.valueInt);
                            if (HeadsetClientStateMachine.this.mIndicatorNetworkState == 0) {
                                HeadsetClientStateMachine.this.mOperatorName = null;
                                intent.putExtra("android.bluetooth.headsetclient.extra.OPERATOR_NAME", HeadsetClientStateMachine.this.mOperatorName);
                            }
                            intent.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
                            if (HeadsetClientStateMachine.this.mIndicatorNetworkState == 1) {
                                if (HeadsetClientStateMachine.this.mNativeInterface.queryCurrentOperatorName(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice))) {
                                    HeadsetClientStateMachine.this.addQueuedAction(51);
                                } else {
                                    Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't querry operator name");
                                }
                            }
                            break;
                        case 5:
                            HeadsetClientStateMachine.this.mIndicatorNetworkType = event.valueInt;
                            Intent intent2 = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            intent2.putExtra("android.bluetooth.headsetclient.extra.NETWORK_ROAMING", event.valueInt);
                            intent2.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent2, ProfileService.BLUETOOTH_PERM);
                            break;
                        case 6:
                            HeadsetClientStateMachine.this.mIndicatorNetworkSignal = event.valueInt;
                            Intent intent3 = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            intent3.putExtra("android.bluetooth.headsetclient.extra.NETWORK_SIGNAL_STRENGTH", event.valueInt);
                            intent3.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent3, ProfileService.BLUETOOTH_PERM);
                            break;
                        case 7:
                            HeadsetClientStateMachine.this.mIndicatorBatteryLevel = event.valueInt;
                            Intent intent4 = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            intent4.putExtra("android.bluetooth.headsetclient.extra.BATTERY_LEVEL", event.valueInt);
                            intent4.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent4, ProfileService.BLUETOOTH_PERM);
                            SystemProperties.set(HeadsetClientStateMachine.BATTERY_AND_MAC, HeadsetClientStateMachine.this.mIndicatorBatteryLevel + " " + event.device.getAddress());
                            HeadsetClientStateMachine.this.updateBatteryList(event.device.getAddress(), HeadsetClientStateMachine.this.mIndicatorBatteryLevel);
                            break;
                        case 8:
                            HeadsetClientStateMachine.this.mOperatorName = event.valueString;
                            Intent intent5 = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            intent5.putExtra("android.bluetooth.headsetclient.extra.OPERATOR_NAME", event.valueString);
                            intent5.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent5, ProfileService.BLUETOOTH_PERM);
                            break;
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                            HeadsetClientStateMachine.this.sendMessage(50);
                            break;
                        case 15:
                            HeadsetClientStateMachine.this.queryCallsUpdate(event.valueInt, event.valueInt3, event.valueString, event.valueInt4 == 1, event.valueInt2 == 0);
                            break;
                        case 16:
                            HeadsetClientStateMachine.logD("HeadsetClientStateMachine EVENT_TYPE_VOLUME_CHANGED  event.valueInt==" + event.valueInt);
                            if (event.valueInt == 0) {
                                this.mCommandedSpeakerVolume = HeadsetClientStateMachine.hfToAmVol(event.valueInt2);
                                HeadsetClientStateMachine.logD("AM volume set to " + this.mCommandedSpeakerVolume);
                            } else if (event.valueInt == 1) {
                                HeadsetClientStateMachine.this.mAudioManager.setMicrophoneMute(event.valueInt2 == 0);
                            }
                            break;
                        case 17:
                            Pair<Integer, Object> queuedAction = (Pair) HeadsetClientStateMachine.this.mQueuedActions.poll();
                            if (queuedAction == null || ((Integer) queuedAction.first).intValue() == 0) {
                                HeadsetClientStateMachine.this.clearPendingAction();
                            } else {
                                HeadsetClientStateMachine.logD("Connected: command result: " + event.valueInt + " queuedAction: " + queuedAction.first);
                                int iIntValue = ((Integer) queuedAction.first).intValue();
                                if (iIntValue == 5) {
                                    if (event.valueInt == 0) {
                                        int oldState2 = HeadsetClientStateMachine.this.mVoiceRecognitionActive;
                                        HeadsetClientStateMachine.this.mVoiceRecognitionActive = 1;
                                        broadcastVoiceRecognitionStateChanged(event.device, oldState2, HeadsetClientStateMachine.this.mVoiceRecognitionActive);
                                    }
                                } else if (iIntValue == 6) {
                                    if (event.valueInt == 0) {
                                        int oldState3 = HeadsetClientStateMachine.this.mVoiceRecognitionActive;
                                        HeadsetClientStateMachine.this.mVoiceRecognitionActive = 0;
                                        broadcastVoiceRecognitionStateChanged(event.device, oldState3, HeadsetClientStateMachine.this.mVoiceRecognitionActive);
                                    }
                                } else if (iIntValue == 50) {
                                    HeadsetClientStateMachine.this.queryCallsDone();
                                } else {
                                    Log.w(HeadsetClientStateMachine.TAG, "Unhandled AT OK " + event);
                                }
                            }
                            break;
                        case 18:
                            HeadsetClientStateMachine.this.mSubscriberInfo = event.valueString;
                            Intent intent6 = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            intent6.putExtra("android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO", HeadsetClientStateMachine.this.mSubscriberInfo);
                            intent6.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent6, ProfileService.BLUETOOTH_PERM);
                            break;
                        case 19:
                            Intent intent7 = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
                            HeadsetClientStateMachine.this.mInBandRing = event.valueInt == 1;
                            intent7.putExtra("android.bluetooth.headsetclient.extra.IN_BAND_RING", event.valueInt);
                            intent7.putExtra("android.bluetooth.device.extra.DEVICE", event.device);
                            HeadsetClientStateMachine.this.mService.sendBroadcast(intent7, ProfileService.BLUETOOTH_PERM);
                            HeadsetClientStateMachine.logD(event.device.toString() + "onInBandRing" + event.valueInt);
                            break;
                        case 20:
                        default:
                            Log.e(HeadsetClientStateMachine.TAG, "Unknown stack event: " + event.type);
                            break;
                        case 21:
                            break;
                        case 22:
                            if (!HeadsetClientStateMachine.this.mVendorProcessor.processEvent(event.valueString, event.device)) {
                                Log.e(HeadsetClientStateMachine.TAG, "Unknown event :" + event.valueString + " for device " + event.device);
                            }
                            break;
                    }
                }
            } else if (HeadsetClientStateMachine.this.mNativeInterface.retrieveSubscriberInfo(HeadsetClientStateMachine.this.getByteAddress(HeadsetClientStateMachine.this.mCurrentDevice))) {
                HeadsetClientStateMachine.this.addQueuedAction(52);
            } else {
                Log.e(HeadsetClientStateMachine.TAG, "ERROR: Couldn't retrieve subscriber info");
            }
            return true;
        }

        private void broadcastVoiceRecognitionStateChanged(BluetoothDevice device, int oldState, int newState) {
            if (oldState == newState) {
                return;
            }
            Intent intent = new Intent("android.bluetooth.headsetclient.profile.action.AG_EVENT");
            intent.putExtra("android.bluetooth.headsetclient.extra.VOICE_RECOGNITION", newState);
            intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
            HeadsetClientStateMachine.this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        }

        private void terminateCallForDisconnect() {
            HeadsetClientStateMachine.logD("terminateCallForDisconnect");
            BluetoothHeadsetClientCall tempcall = HeadsetClientStateMachine.this.getCall(0);
            if (tempcall != null) {
                Log.e(HeadsetClientStateMachine.TAG, "terminateCallForDisconnect: force terminate call");
                tempcall.setState(7);
                HeadsetClientStateMachine.this.sendCallChangedIntent(tempcall);
            }
        }

        private void processConnectionEvent(int state, BluetoothDevice device) {
            if (state == 0) {
                HeadsetClientStateMachine.logD("Connected disconnects.");
                if (HeadsetClientStateMachine.this.mCurrentDevice.equals(device)) {
                    terminateCallForDisconnect();
                    HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                    headsetClientStateMachine.transitionTo(headsetClientStateMachine.mDisconnected);
                    return;
                } else {
                    Log.e(HeadsetClientStateMachine.TAG, "Disconnected from unknown device: " + device);
                    return;
                }
            }
            Log.e(HeadsetClientStateMachine.TAG, "Connection State Device: " + device + " bad state: " + state);
        }

        private void processAudioEvent(int state, BluetoothDevice device) {
            if (!HeadsetClientStateMachine.this.mCurrentDevice.equals(device)) {
                Log.e(HeadsetClientStateMachine.TAG, "Audio changed on disconnected device: " + device);
                return;
            }
            if (state == 0) {
                HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                headsetClientStateMachine.broadcastAudioState(device, 0, headsetClientStateMachine.mAudioState);
                HeadsetClientStateMachine.this.mAudioState = 0;
                return;
            }
            if (state != 1) {
                if (state != 2) {
                    if (state == 3) {
                        HeadsetClientStateMachine.this.mAudioWbs = true;
                    } else {
                        Log.e(HeadsetClientStateMachine.TAG, "Audio State Device: " + device + " bad state: " + state);
                        return;
                    }
                }
                if (!HeadsetClientStateMachine.this.mService.isScoRouted()) {
                    HeadsetClientStateMachine.this.mAudioState = 2;
                    int amVol = 0;
                    try {
                        amVol = HeadsetClientStateMachine.this.mCarAudioManager.getGroupVolume(HeadsetClientStateMachine.mVolumeGroupId);
                    } catch (CarNotConnectedException exception) {
                        Log.e(HeadsetClientStateMachine.TAG, "Car is not connected", exception);
                    } catch (NullPointerException exception2) {
                        Log.e(HeadsetClientStateMachine.TAG, "mCarAudioManager is NULL!", exception2);
                    }
                    int hfVol = HeadsetClientStateMachine.amToHfVol(amVol);
                    HeadsetClientStateMachine.logD("hfp_enable=true mAudioWbs is " + HeadsetClientStateMachine.this.mAudioWbs);
                    if (HeadsetClientStateMachine.this.mAudioWbs) {
                        HeadsetClientStateMachine.logD("Setting sampling rate as 16000");
                        HeadsetClientStateMachine.this.mAudioManager.setParameters("hfp_set_sampling_rate=16000");
                        HeadsetClientStateMachine.this.mSamplingRate = HeadsetClientStateMachine.SAMPLING_RATE_WIDE;
                    } else {
                        HeadsetClientStateMachine.logD("Setting sampling rate as 8000");
                        HeadsetClientStateMachine.this.mAudioManager.setParameters("hfp_set_sampling_rate=8000");
                        HeadsetClientStateMachine.this.mSamplingRate = HeadsetClientStateMachine.SAMPLING_RATE_NARROW;
                    }
                    HeadsetClientStateMachine.logD("hf_volume AUDIO_STATE_CONNECTED:" + hfVol);
                    HeadsetClientStateMachine.this.routeHfpAudio(true);
                    for (BluetoothHeadsetClientCall call : HeadsetClientStateMachine.this.mCalls.values()) {
                        HeadsetClientStateMachine.logD("processAudioEvent callState: " + call.getState());
                    }
                    if (HeadsetClientStateMachine.this.getCurrentCalls() != null && !HeadsetClientStateMachine.this.getCurrentCalls().isEmpty()) {
                        HeadsetClientStateMachine headsetClientStateMachine2 = HeadsetClientStateMachine.this;
                        headsetClientStateMachine2.mAudioFocusRequest = headsetClientStateMachine2.requestAudioFocus();
                    }
                    HeadsetClientStateMachine.this.mAudioManager.setParameters("hfp_volume=" + hfVol);
                    HeadsetClientStateMachine headsetClientStateMachine3 = HeadsetClientStateMachine.this;
                    headsetClientStateMachine3.transitionTo(headsetClientStateMachine3.mAudioOn);
                    return;
                }
                Log.w(HeadsetClientStateMachine.TAG, "ScoRouted is linked");
                StackEvent event = new StackEvent(2);
                event.valueInt = state;
                event.device = device;
                HeadsetClientStateMachine.this.sendMessageDelayed(100, event, 250L);
                return;
            }
            HeadsetClientStateMachine headsetClientStateMachine4 = HeadsetClientStateMachine.this;
            headsetClientStateMachine4.broadcastAudioState(device, 1, headsetClientStateMachine4.mAudioState);
            HeadsetClientStateMachine.this.mAudioState = 1;
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetClientStateMachine.logD("Exit Connected: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.mPrevState = this;
        }
    }

    class AudioOn extends State {
        AudioOn() {
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void enter() {
            if (HeadsetClientStateMachine.this.getCurrentMessage() != null) {
                HeadsetClientStateMachine.logD("Enter AudioOn: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            }
            String audioState = SystemProperties.get(HeadsetClientStateMachine.AUDIO_NEED_ON, INetd.NEXTHOP_NONE);
            HeadsetClientStateMachine.logD("Enter AudioOn audioState: " + audioState);
            if (!audioState.isEmpty()) {
                HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                headsetClientStateMachine.broadcastAudioState(headsetClientStateMachine.mCurrentDevice, 2, 1);
            } else {
                HeadsetClientStateMachine.this.sendMessage(4);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:34:0x00e9 A[Catch: all -> 0x011c, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x001e, B:7:0x0026, B:10:0x002f, B:20:0x0042, B:25:0x0064, B:26:0x008d, B:27:0x00b5, B:32:0x00d1, B:34:0x00e9, B:36:0x00fc, B:37:0x0109, B:28:0x00bb, B:31:0x00cc), top: B:43:0x0001 }] */
        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized boolean processMessage(android.os.Message r8) {
            /*
                Method dump skipped, instruction units count: 287
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.hfpclient.HeadsetClientStateMachine.AudioOn.processMessage(android.os.Message):boolean");
        }

        private void processConnectionEvent(int state, BluetoothDevice device) {
            if (state == 0) {
                if (HeadsetClientStateMachine.this.mCurrentDevice.equals(device)) {
                    processAudioEvent(0, device);
                    HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                    headsetClientStateMachine.transitionTo(headsetClientStateMachine.mDisconnected);
                    return;
                } else {
                    Log.e(HeadsetClientStateMachine.TAG, "Disconnected from unknown device: " + device);
                    return;
                }
            }
            Log.e(HeadsetClientStateMachine.TAG, "Connection State Device: " + device.getAddressOnlyforLog() + " bad state: " + state);
        }

        private void processAudioEvent(int state, BluetoothDevice device) {
            if (!HeadsetClientStateMachine.this.mCurrentDevice.equals(device)) {
                Log.e(HeadsetClientStateMachine.TAG, "Audio changed on disconnected device: " + device);
                return;
            }
            if (state == 0) {
                HeadsetClientStateMachine.this.removeMessages(4);
                HeadsetClientStateMachine.this.mAudioState = 0;
                if (HeadsetClientStateMachine.this.getCall(2, 4, 3, 0) != null) {
                    Log.w(HeadsetClientStateMachine.TAG, "HeadsetClientHalConstants.AUDIO_STATE_DISCONNECTED   calling,Refuse release focus");
                    HeadsetClientStateMachine.this.routeHfpAudio(false);
                    HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
                    headsetClientStateMachine.transitionTo(headsetClientStateMachine.mConnected);
                    return;
                }
                Log.w(HeadsetClientStateMachine.TAG, "HeadsetClientHalConstants.AUDIO_STATE_DISCONNECTED   release audioFocus");
                HeadsetClientStateMachine.this.routeHfpAudio(false);
                HeadsetClientStateMachine.this.returnAudioFocusIfNecessary();
                HeadsetClientStateMachine headsetClientStateMachine2 = HeadsetClientStateMachine.this;
                headsetClientStateMachine2.transitionTo(headsetClientStateMachine2.mConnected);
                return;
            }
            Log.e(HeadsetClientStateMachine.TAG, "Audio State Device: " + device + " bad state: " + state);
        }

        @Override // com.android.bluetooth.statemachine.State, com.android.bluetooth.statemachine.IState
        public void exit() {
            HeadsetClientStateMachine.logD("Exit AudioOn: " + HeadsetClientStateMachine.this.getCurrentMessage().what);
            HeadsetClientStateMachine.this.mPrevState = this;
            HeadsetClientStateMachine headsetClientStateMachine = HeadsetClientStateMachine.this;
            headsetClientStateMachine.broadcastAudioState(headsetClientStateMachine.mCurrentDevice, 0, 2);
        }
    }

    public synchronized int getConnectionState(BluetoothDevice device) {
        if (this.mCurrentDevice == null) {
            return 0;
        }
        if (!this.mCurrentDevice.equals(device)) {
            return 0;
        }
        IState currentState = getCurrentState();
        if (currentState == this.mConnecting) {
            return 1;
        }
        if (currentState != this.mConnected && currentState != this.mAudioOn) {
            Log.e(TAG, "Bad currentState: " + currentState);
            return 0;
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastAudioState(BluetoothDevice device, int newState, int prevState) {
        int i;
        byte[] bArrObfuscateAddress = AdapterService.getAdapterService().obfuscateAddress(device);
        int connectionStateFromAudioState = getConnectionStateFromAudioState(newState);
        if (this.mAudioWbs) {
            i = 2;
        } else {
            i = 1;
        }
        BluetoothStatsLog.write(BluetoothStatsLog.BLUETOOTH_SCO_CONNECTION_STATE_CHANGED, bArrObfuscateAddress, connectionStateFromAudioState, i, AdapterService.getAdapterService().getMetricId(device));
        Intent intent = new Intent("android.bluetooth.headsetclient.profile.action.AUDIO_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        if (newState == 2) {
            intent.putExtra("android.bluetooth.headsetclient.extra.AUDIO_WBS", this.mAudioWbs);
        }
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
        logD("Audio state " + device + ": " + prevState + "->" + newState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastConnectionState(BluetoothDevice device, int newState, int prevState) {
        logD("Connection state " + device + ": " + prevState + "->" + newState);
        Intent intent = new Intent("android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        intent.putExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", prevState);
        intent.putExtra("android.bluetooth.profile.extra.STATE", newState);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", device);
        if (newState == 2) {
            if ((this.mPeerFeatures & 1) == 1) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_3WAY_CALLING", true);
            }
            if ((this.mPeerFeatures & 4) == 4) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_VOICE_RECOGNITION", true);
            }
            if ((this.mPeerFeatures & 32) == 32) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_REJECT_CALL", true);
            }
            if ((this.mPeerFeatures & 128) == 128) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ECC", true);
            }
            if ((this.mChldFeatures & 8) == 8) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_ACCEPT_HELD_OR_WAITING_CALL", true);
            }
            if ((this.mChldFeatures & 1) == 1) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_HELD_OR_WAITING_CALL", true);
            }
            if ((this.mChldFeatures & 2) == 2) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_RELEASE_AND_ACCEPT", true);
            }
            if ((this.mChldFeatures & 32) == 32) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE", true);
            }
            if ((this.mChldFeatures & 64) == 64) {
                intent.putExtra("android.bluetooth.headsetclient.extra.EXTRA_AG_FEATURE_MERGE_AND_DETACH", true);
            }
        } else if (newState == 0) {
            deleteBatteryDevice(device.getAddress());
        }
        this.mService.sendBroadcast(intent, ProfileService.BLUETOOTH_PERM);
    }

    boolean isConnected() {
        IState currentState = getCurrentState();
        return currentState == this.mConnected || currentState == this.mAudioOn;
    }

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] states) {
        List<BluetoothDevice> deviceList = new ArrayList<>();
        Set<BluetoothDevice> bondedDevices = this.mAdapter.getBondedDevices();
        synchronized (this) {
            for (BluetoothDevice device : bondedDevices) {
                ParcelUuid[] featureUuids = device.getUuids();
                if (ArrayUtils.contains(featureUuids, BluetoothUuid.HFP_AG)) {
                    int connectionState = getConnectionState(device);
                    for (int state : states) {
                        if (connectionState == state) {
                            deviceList.add(device);
                        }
                    }
                }
            }
        }
        return deviceList;
    }

    boolean okToConnect(BluetoothDevice device) {
        int connectionPolicy = this.mService.getConnectionPolicy(device);
        if (connectionPolicy <= 0 && (-1 != connectionPolicy || device.getBondState() == 10)) {
            return false;
        }
        return true;
    }

    boolean isAudioOn() {
        return getCurrentState() == this.mAudioOn;
    }

    synchronized int getAudioState(BluetoothDevice device) {
        if (this.mCurrentDevice != null && this.mCurrentDevice.equals(device)) {
            Log.e(TAG, " getAudioState mAudioState: " + this.mAudioState);
            return this.mAudioState;
        }
        return 0;
    }

    List<BluetoothDevice> getConnectedDevices() {
        List<BluetoothDevice> devices = new ArrayList<>();
        synchronized (this) {
            if (isConnected()) {
                devices.add(this.mCurrentDevice);
            }
        }
        return devices;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] getByteAddress(BluetoothDevice device) {
        return Utils.getBytesFromAddress(device.getAddress());
    }

    public List<BluetoothHeadsetClientCall> getCurrentCalls() {
        return new ArrayList(this.mCalls.values());
    }

    public Bundle getCurrentAgEvents() {
        Bundle b = new Bundle();
        b.putInt("android.bluetooth.headsetclient.extra.NETWORK_STATUS", this.mIndicatorNetworkState);
        b.putInt("android.bluetooth.headsetclient.extra.NETWORK_SIGNAL_STRENGTH", this.mIndicatorNetworkSignal);
        b.putInt("android.bluetooth.headsetclient.extra.NETWORK_ROAMING", this.mIndicatorNetworkType);
        b.putInt("android.bluetooth.headsetclient.extra.BATTERY_LEVEL", this.mIndicatorBatteryLevel);
        b.putString("android.bluetooth.headsetclient.extra.OPERATOR_NAME", this.mOperatorName);
        b.putString("android.bluetooth.headsetclient.extra.SUBSCRIBER_INFO", this.mSubscriberInfo);
        return b;
    }

    private static int getConnectionStateFromAudioState(int audioState) {
        if (audioState != 1) {
            return audioState != 2 ? 0 : 2;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logD(String message) {
        if (DBG) {
            Log.d(TAG, message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void audioFocusLossHandle(int action, int usage) {
        if (action == -1 && usage == 2) {
            returnAudioFocusIfNecessary();
        }
        if (action == 2) {
            BluetoothHeadsetClientCall tempcall = getCall(2, 4, 3, 0);
            if (tempcall == null) {
                returnAudioFocusIfNecessary();
                return;
            }
            logD("faudioFocusLossHandle   mAudioPatch: " + this.mAudioPatch + "  usage:" + usage);
            if (this.mAudioPatch == null) {
                createAudioPatch(usage);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBatteryList(String mac, int battery) {
        String data;
        String data2;
        try {
            String data3 = SystemProperties.get(NEW_BATTERY_AND_MAC, INetd.NEXTHOP_NONE);
            logD("updateBatteryList mac: " + mac + " battery: " + battery + " data:" + data3);
            StringBuilder sb = new StringBuilder();
            sb.append(mac);
            sb.append(" ");
            sb.append(battery);
            String insert = sb.toString();
            if (data3.isEmpty()) {
                logD("updateBatteryList first data:" + insert);
                SystemProperties.set(NEW_BATTERY_AND_MAC, insert);
                return;
            }
            if (!data3.contains(mac)) {
                String[] splitContent = data3.split("_");
                if (splitContent.length < 2) {
                    data2 = insert + "_" + data3;
                } else {
                    String newData = data3.substring(0, data3.lastIndexOf("_"));
                    data2 = insert + "_" + newData;
                }
                logD("updateBatteryList second data:" + data2);
                SystemProperties.set(NEW_BATTERY_AND_MAC, data2);
                return;
            }
            int index = data3.indexOf(mac);
            if (index > 0) {
                data = insert + "_" + data3.substring(0, index - 1);
            } else if (data3.contains("_")) {
                int lineIndex = data3.indexOf("_");
                data = insert + "_" + data3.substring(lineIndex + 1);
            } else {
                data = insert;
            }
            logD("updateBatteryList third data:" + data);
            SystemProperties.set(NEW_BATTERY_AND_MAC, data);
        } catch (Exception exception) {
            Log.e(TAG, "updateBatteryList error: " + exception.getLocalizedMessage());
        }
    }

    private void deleteBatteryDevice(String mac) {
        String data;
        try {
            String data2 = SystemProperties.get(NEW_BATTERY_AND_MAC, INetd.NEXTHOP_NONE);
            logD("deleteBatteryDevice mac: " + mac + " data:" + data2);
            if (data2.isEmpty() || !data2.contains(mac)) {
                return;
            }
            int index = data2.indexOf(mac);
            if (index > 0) {
                data = data2.substring(0, index - 1);
            } else {
                int lineIndex = data2.indexOf("_");
                data = data2.substring(lineIndex + 1);
            }
            SystemProperties.set(NEW_BATTERY_AND_MAC, data);
        } catch (Exception exception) {
            Log.e(TAG, "deleteBatteryDevice error: " + exception.getLocalizedMessage());
        }
    }

    private void setUnMute() {
        CarAudioManager carAudioManager;
        logD("setUnMute");
        if (this.mCarEnhancedAudioManager != null && (carAudioManager = this.mCarAudioManager) != null) {
            int groupCount = carAudioManager.getVolumeGroupCount();
            logD("setUnMute groupCount: " + groupCount + "  volumeGroupId: " + mVolumeGroupId);
            for (int groupIndex = 0; groupIndex < groupCount; groupIndex++) {
                if (groupIndex != mVolumeGroupId) {
                    logD("HeadsetClient setAllMute groupIndex = " + groupIndex);
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex, false, 0);
                }
            }
        }
    }
}
