package com.android.services.telephony;

import android.os.AsyncResult;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.ServiceState;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.SomeArgs;

/* JADX INFO: loaded from: classes.dex */
public class RadioOnStateListener {
    private static int MAX_NUM_RETRIES = 5;

    @VisibleForTesting
    public static final int MSG_RADIO_ON = 4;

    @VisibleForTesting
    public static final int MSG_SERVICE_STATE_CHANGED = 2;
    private static long TIME_BETWEEN_RETRIES_MILLIS = 5000;
    private Callback mCallback;
    private boolean mForEmergencyCall;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.services.telephony.RadioOnStateListener.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                SomeArgs someArgs = (SomeArgs) message.obj;
                try {
                    RadioOnStateListener.this.startSequenceInternal((Phone) someArgs.arg1, (Callback) someArgs.arg2, ((Boolean) someArgs.arg3).booleanValue(), ((Boolean) someArgs.arg4).booleanValue());
                    return;
                } finally {
                    someArgs.recycle();
                }
            }
            if (i == 2) {
                RadioOnStateListener.this.onServiceStateChanged((ServiceState) ((AsyncResult) message.obj).result);
                return;
            }
            if (i == 3) {
                RadioOnStateListener.this.onRetryTimeout();
                return;
            }
            if (i == 4) {
                RadioOnStateListener.this.onRadioOn();
            } else if (i != 5) {
                Log.wtf(this, "handleMessage: unexpected message: %d.", Integer.valueOf(i));
            } else {
                RadioOnStateListener.this.registerForRadioOn();
            }
        }
    };
    private int mNumRetriesSoFar;
    private Phone mPhone;
    private boolean mSelectedPhoneForEmergencyCall;

    interface Callback {
        boolean isOkToCall(Phone phone, int i);

        void onComplete(RadioOnStateListener radioOnStateListener, boolean z);
    }

    public void waitForRadioOn(Phone phone, Callback callback, boolean z, boolean z2) {
        Log.d(this, "waitForRadioOn: Phone " + phone.getPhoneId(), new Object[0]);
        if (this.mPhone != null) {
            return;
        }
        SomeArgs someArgsObtain = SomeArgs.obtain();
        someArgsObtain.arg1 = phone;
        someArgsObtain.arg2 = callback;
        someArgsObtain.arg3 = Boolean.valueOf(z);
        someArgsObtain.arg4 = Boolean.valueOf(z2);
        this.mHandler.obtainMessage(1, someArgsObtain).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSequenceInternal(Phone phone, Callback callback, boolean z, boolean z2) {
        Log.d(this, "startSequenceInternal: Phone " + phone.getPhoneId(), new Object[0]);
        cleanup();
        this.mPhone = phone;
        this.mCallback = callback;
        this.mForEmergencyCall = z;
        this.mSelectedPhoneForEmergencyCall = z2;
        registerForServiceStateChanged();
        registerForRadioOff();
        startRetryTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onServiceStateChanged(ServiceState serviceState) {
        Phone phone = this.mPhone;
        if (phone == null) {
            return;
        }
        Log.d(this, "onServiceStateChanged(), new state = %s, Phone = %s", serviceState, Integer.valueOf(phone.getPhoneId()));
        if (isOkToCall(serviceState.getState())) {
            Log.d(this, "onServiceStateChanged: ok to call!", new Object[0]);
            onComplete(true);
            cleanup();
            return;
        }
        Log.d(this, "onServiceStateChanged: not ready to call yet, keep waiting.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRadioOn() {
        Phone phone = this.mPhone;
        if (phone == null) {
            return;
        }
        ServiceState serviceState = phone.getServiceState();
        Log.d(this, "onRadioOn, state = %s, Phone = %s", serviceState, Integer.valueOf(this.mPhone.getPhoneId()));
        if (isOkToCall(serviceState.getState())) {
            onComplete(true);
            cleanup();
        } else {
            Log.d(this, "onRadioOn: not ready to call yet, keep waiting.", new Object[0]);
        }
    }

    private boolean isOkToCall(int i) {
        Callback callback = this.mCallback;
        if (callback == null) {
            return false;
        }
        return callback.isOkToCall(this.mPhone, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRetryTimeout() {
        Phone phone = this.mPhone;
        if (phone == null) {
            return;
        }
        int state = phone.getServiceState().getState();
        Log.d(this, "onRetryTimeout():  phone state = %s, service state = %d, retries = %d.", this.mPhone.getState(), Integer.valueOf(state), Integer.valueOf(this.mNumRetriesSoFar));
        if (isOkToCall(state)) {
            Log.d(this, "onRetryTimeout: Radio is on. Cleaning up.", new Object[0]);
            onComplete(true);
            cleanup();
            return;
        }
        this.mNumRetriesSoFar++;
        Log.d(this, "mNumRetriesSoFar is now " + this.mNumRetriesSoFar, new Object[0]);
        if (this.mNumRetriesSoFar > MAX_NUM_RETRIES) {
            Log.w(this, "Hit MAX_NUM_RETRIES; giving up.", new Object[0]);
            cleanup();
        } else {
            Log.d(this, "Trying (again) to turn on the radio.", new Object[0]);
            this.mPhone.setRadioPower(true, this.mForEmergencyCall, this.mSelectedPhoneForEmergencyCall, false);
            startRetryTimer();
        }
    }

    public void cleanup() {
        Log.d(this, "cleanup()", new Object[0]);
        onComplete(false);
        unregisterForServiceStateChanged();
        unregisterForRadioOff();
        unregisterForRadioOn();
        cancelRetryTimer();
        this.mPhone = null;
        this.mNumRetriesSoFar = 0;
    }

    private void startRetryTimer() {
        cancelRetryTimer();
        this.mHandler.sendEmptyMessageDelayed(3, TIME_BETWEEN_RETRIES_MILLIS);
    }

    private void cancelRetryTimer() {
        this.mHandler.removeMessages(3);
    }

    private void registerForServiceStateChanged() {
        unregisterForServiceStateChanged();
        this.mPhone.registerForServiceStateChanged(this.mHandler, 2, (Object) null);
    }

    private void unregisterForServiceStateChanged() {
        Phone phone = this.mPhone;
        if (phone != null) {
            phone.unregisterForServiceStateChanged(this.mHandler);
        }
        this.mHandler.removeMessages(2);
    }

    private void registerForRadioOff() {
        this.mPhone.mCi.registerForOffOrNotAvailable(this.mHandler, 5, (Object) null);
    }

    private void unregisterForRadioOff() {
        Phone phone = this.mPhone;
        if (phone != null) {
            phone.mCi.unregisterForOffOrNotAvailable(this.mHandler);
        }
        this.mHandler.removeMessages(5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registerForRadioOn() {
        unregisterForRadioOff();
        this.mPhone.mCi.registerForOn(this.mHandler, 4, (Object) null);
    }

    private void unregisterForRadioOn() {
        Phone phone = this.mPhone;
        if (phone != null) {
            phone.mCi.unregisterForOn(this.mHandler);
        }
        this.mHandler.removeMessages(4);
    }

    private void onComplete(boolean z) {
        Callback callback = this.mCallback;
        if (callback != null) {
            this.mCallback = null;
            callback.onComplete(this, z);
        }
    }

    @VisibleForTesting
    public Handler getHandler() {
        return this.mHandler;
    }

    @VisibleForTesting
    public void setMaxNumRetries(int i) {
        MAX_NUM_RETRIES = i;
    }

    @VisibleForTesting
    public void setTimeBetweenRetriesMillis(long j) {
        TIME_BETWEEN_RETRIES_MILLIS = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !RadioOnStateListener.class.equals(obj.getClass())) {
            return false;
        }
        RadioOnStateListener radioOnStateListener = (RadioOnStateListener) obj;
        if (this.mNumRetriesSoFar != radioOnStateListener.mNumRetriesSoFar) {
            return false;
        }
        Callback callback = this.mCallback;
        if (callback == null ? radioOnStateListener.mCallback != null : !callback.equals(radioOnStateListener.mCallback)) {
            return false;
        }
        Phone phone = this.mPhone;
        return phone != null ? phone.equals(radioOnStateListener.mPhone) : radioOnStateListener.mPhone == null;
    }
}
