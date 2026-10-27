package com.android.bluetooth.hfp;

import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetPhoneState {
    private static final String TAG = "HeadsetPhoneState";
    private int mCindBatteryCharge;
    private int mCindSignal;
    private final Handler mHandler;
    private final HeadsetService mHeadsetService;
    private int mNumActive;
    private int mNumHeld;
    private final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangedListener;
    private PhoneStateListener mPhoneStateListener;
    private ServiceState mServiceState;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;
    private int mCindService = 0;
    private int mCallState = 6;
    private int mCindRoam = 0;
    private final HashMap<BluetoothDevice, Integer> mDeviceEventMap = new HashMap<>();

    HeadsetPhoneState(HeadsetService headsetService) {
        Objects.requireNonNull(headsetService, "headsetService is null");
        this.mHeadsetService = headsetService;
        TelephonyManager telephonyManager = (TelephonyManager) headsetService.getSystemService("phone");
        this.mTelephonyManager = telephonyManager;
        Objects.requireNonNull(telephonyManager, "TELEPHONY_SERVICE is null");
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(this.mHeadsetService);
        this.mSubscriptionManager = subscriptionManagerFrom;
        Objects.requireNonNull(subscriptionManagerFrom, "TELEPHONY_SUBSCRIPTION_SERVICE is null");
        this.mHandler = new Handler(headsetService.getStateMachinesThreadLooper());
        this.mOnSubscriptionsChangedListener = new HeadsetPhoneStateOnSubscriptionChangedListener();
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(new Executor() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetPhoneState$Z8lmIgD-VtJ_7DQAyWsxE2Vm2w4
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.lambda$new$0$HeadsetPhoneState(runnable);
            }
        }, this.mOnSubscriptionsChangedListener);
    }

    public /* synthetic */ void lambda$new$0$HeadsetPhoneState(Runnable command) {
        this.mHandler.post(command);
    }

    public void cleanup() {
        synchronized (this.mDeviceEventMap) {
            this.mDeviceEventMap.clear();
            stopListenForPhoneState();
        }
        this.mSubscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangedListener);
    }

    public String toString() {
        return "HeadsetPhoneState [mTelephonyServiceAvailability=" + this.mCindService + ", mNumActive=" + this.mNumActive + ", mCallState=" + this.mCallState + ", mNumHeld=" + this.mNumHeld + ", mSignal=" + this.mCindSignal + ", mRoam=" + this.mCindRoam + ", mBatteryCharge=" + this.mCindBatteryCharge + ", TelephonyEvents=" + getTelephonyEventsToListen() + "]";
    }

    private int getTelephonyEventsToListen() {
        int iIntValue;
        synchronized (this.mDeviceEventMap) {
            iIntValue = this.mDeviceEventMap.values().stream().reduce(0, new BinaryOperator() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetPhoneState$ThejWnzApDfy8--sAHEQTtxtxJc
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return Integer.valueOf(((Integer) obj).intValue() | ((Integer) obj2).intValue());
                }
            }).intValue();
        }
        return iIntValue;
    }

    public void listenForPhoneState(BluetoothDevice device, int events) {
        synchronized (this.mDeviceEventMap) {
            int prevEvents = getTelephonyEventsToListen();
            if (events == 0) {
                this.mDeviceEventMap.remove(device);
            } else {
                this.mDeviceEventMap.put(device, Integer.valueOf(events));
            }
            int updatedEvents = getTelephonyEventsToListen();
            if (prevEvents != updatedEvents) {
                stopListenForPhoneState();
                startListenForPhoneState();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startListenForPhoneState() {
        if (this.mPhoneStateListener != null) {
            Log.w(TAG, "startListenForPhoneState, already listening");
            return;
        }
        int events = getTelephonyEventsToListen();
        if (events == 0) {
            Log.w(TAG, "startListenForPhoneState, no event to listen");
            return;
        }
        int subId = SubscriptionManager.getDefaultSubscriptionId();
        if (!SubscriptionManager.isValidSubscriptionId(subId)) {
            Log.w(TAG, "startListenForPhoneState, invalid subscription ID " + subId);
            return;
        }
        Log.i(TAG, "startListenForPhoneState(), subId=" + subId + ", enabled_events=" + events);
        HeadsetPhoneStateListener headsetPhoneStateListener = new HeadsetPhoneStateListener(new Executor() { // from class: com.android.bluetooth.hfp.-$$Lambda$HeadsetPhoneState$sW6qcxDGWxQjg6vRrEE-AnFJFpc
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.lambda$startListenForPhoneState$2$HeadsetPhoneState(runnable);
            }
        });
        this.mPhoneStateListener = headsetPhoneStateListener;
        this.mTelephonyManager.listen(headsetPhoneStateListener, events);
    }

    public /* synthetic */ void lambda$startListenForPhoneState$2$HeadsetPhoneState(Runnable command) {
        this.mHandler.post(command);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopListenForPhoneState() {
        if (this.mPhoneStateListener == null) {
            Log.i(TAG, "stopListenForPhoneState(), no listener indicates nothing is listening");
            return;
        }
        Log.i(TAG, "stopListenForPhoneState(), stopping listener, enabled_events=" + getTelephonyEventsToListen());
        this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
        this.mPhoneStateListener = null;
    }

    int getCindService() {
        return this.mCindService;
    }

    int getNumActiveCall() {
        return this.mNumActive;
    }

    public void setNumActiveCall(int numActive) {
        this.mNumActive = numActive;
    }

    int getCallState() {
        return this.mCallState;
    }

    public void setCallState(int callState) {
        this.mCallState = callState;
    }

    int getNumHeldCall() {
        return this.mNumHeld;
    }

    public void setNumHeldCall(int numHeldCall) {
        this.mNumHeld = numHeldCall;
    }

    ServiceState getServiceState() {
        return this.mServiceState;
    }

    int getCindSignal() {
        return this.mCindSignal;
    }

    int getCindRoam() {
        return this.mCindRoam;
    }

    public void setCindBatteryCharge(int batteryLevel) {
        if (this.mCindBatteryCharge != batteryLevel) {
            this.mCindBatteryCharge = batteryLevel;
            sendDeviceStateChanged();
        }
    }

    int getCindBatteryCharge() {
        return this.mCindBatteryCharge;
    }

    boolean isInCall() {
        return this.mNumActive >= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void sendDeviceStateChanged() {
        int signal = this.mCindService == 1 ? this.mCindSignal : 0;
        Log.d(TAG, "sendDeviceStateChanged. mService=" + this.mCindService + " mSignal=" + this.mCindSignal + " mRoam=" + this.mCindRoam + " mBatteryCharge=" + this.mCindBatteryCharge);
        this.mHeadsetService.onDeviceStateChanged(new HeadsetDeviceState(this.mCindService, this.mCindRoam, signal, this.mCindBatteryCharge));
    }

    private class HeadsetPhoneStateOnSubscriptionChangedListener extends SubscriptionManager.OnSubscriptionsChangedListener {
        HeadsetPhoneStateOnSubscriptionChangedListener() {
        }

        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            synchronized (HeadsetPhoneState.this.mDeviceEventMap) {
                int simState = HeadsetPhoneState.this.mTelephonyManager.getSimState();
                if (simState != 5) {
                    HeadsetPhoneState.this.mServiceState = null;
                    HeadsetPhoneState.this.mCindSignal = 0;
                    HeadsetPhoneState.this.mCindService = 0;
                    HeadsetPhoneState.this.sendDeviceStateChanged();
                }
                HeadsetPhoneState.this.stopListenForPhoneState();
                HeadsetPhoneState.this.startListenForPhoneState();
            }
        }
    }

    private class HeadsetPhoneStateListener extends PhoneStateListener {
        HeadsetPhoneStateListener(Executor executor) {
            super(executor);
        }

        @Override // android.telephony.PhoneStateListener
        public synchronized void onServiceStateChanged(ServiceState serviceState) {
            int cindService;
            HeadsetPhoneState.this.mServiceState = serviceState;
            int newRoam = 1;
            if (serviceState.getState() == 0) {
                cindService = 1;
            } else {
                cindService = 0;
            }
            if (!serviceState.getRoaming()) {
                newRoam = 0;
            }
            if (cindService == HeadsetPhoneState.this.mCindService && newRoam == HeadsetPhoneState.this.mCindRoam) {
                return;
            }
            HeadsetPhoneState.this.mCindService = cindService;
            HeadsetPhoneState.this.mCindRoam = newRoam;
            HeadsetPhoneState.this.sendDeviceStateChanged();
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            int prevSignal = HeadsetPhoneState.this.mCindSignal;
            if (HeadsetPhoneState.this.mCindService == 0) {
                HeadsetPhoneState.this.mCindSignal = 0;
            } else {
                HeadsetPhoneState.this.mCindSignal = signalStrength.getLevel() + 1;
            }
            HeadsetPhoneState headsetPhoneState = HeadsetPhoneState.this;
            headsetPhoneState.mCindSignal = Integer.max(Integer.min(headsetPhoneState.mCindSignal, 5), 0);
            if (prevSignal != HeadsetPhoneState.this.mCindSignal) {
                HeadsetPhoneState.this.sendDeviceStateChanged();
            }
        }
    }
}
