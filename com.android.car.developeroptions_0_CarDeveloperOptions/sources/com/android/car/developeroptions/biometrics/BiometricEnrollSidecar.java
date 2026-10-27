package com.android.car.developeroptions.biometrics;

import android.app.Activity;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import com.android.car.developeroptions.core.InstrumentedFragment;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class BiometricEnrollSidecar extends InstrumentedFragment {
    private boolean mEnrolling;
    protected CancellationSignal mEnrollmentCancel;
    private Listener mListener;
    protected byte[] mToken;
    protected int mUserId;
    private int mEnrollmentSteps = -1;
    private int mEnrollmentRemaining = 0;
    private Handler mHandler = new Handler();
    private final Runnable mTimeoutRunnable = new Runnable() { // from class: com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.1
        @Override // java.lang.Runnable
        public void run() {
            BiometricEnrollSidecar.this.cancelEnrollment();
        }
    };
    private ArrayList<QueuedEvent> mQueuedEvents = new ArrayList<>();

    public interface Listener {
        void onEnrollmentError(int i, CharSequence charSequence);

        void onEnrollmentHelp(int i, CharSequence charSequence);

        void onEnrollmentProgressChange(int i, int i2);
    }

    private abstract class QueuedEvent {
        public abstract void send(Listener listener);

        private QueuedEvent(BiometricEnrollSidecar biometricEnrollSidecar) {
        }
    }

    private class QueuedEnrollmentProgress extends QueuedEvent {
        int enrollmentSteps;
        int remaining;

        public QueuedEnrollmentProgress(BiometricEnrollSidecar biometricEnrollSidecar, int i, int i2) {
            super();
            this.enrollmentSteps = i;
            this.remaining = i2;
        }

        @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.QueuedEvent
        public void send(Listener listener) {
            listener.onEnrollmentProgressChange(this.enrollmentSteps, this.remaining);
        }
    }

    private class QueuedEnrollmentHelp extends QueuedEvent {
        int helpMsgId;
        CharSequence helpString;

        public QueuedEnrollmentHelp(BiometricEnrollSidecar biometricEnrollSidecar, int i, CharSequence charSequence) {
            super();
            this.helpMsgId = i;
            this.helpString = charSequence;
        }

        @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.QueuedEvent
        public void send(Listener listener) {
            listener.onEnrollmentHelp(this.helpMsgId, this.helpString);
        }
    }

    private class QueuedEnrollmentError extends QueuedEvent {
        int errMsgId;
        CharSequence errString;

        public QueuedEnrollmentError(BiometricEnrollSidecar biometricEnrollSidecar, int i, CharSequence charSequence) {
            super();
            this.errMsgId = i;
            this.errString = charSequence;
        }

        @Override // com.android.car.developeroptions.biometrics.BiometricEnrollSidecar.QueuedEvent
        public void send(Listener listener) {
            listener.onEnrollmentError(this.errMsgId, this.errString);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mToken = activity.getIntent().getByteArrayExtra("hw_auth_token");
        this.mUserId = activity.getIntent().getIntExtra("android.intent.extra.USER_ID", -10000);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mEnrolling) {
            return;
        }
        startEnrollment();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservableFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (getActivity().isChangingConfigurations()) {
            return;
        }
        cancelEnrollment();
    }

    protected void startEnrollment() {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mEnrollmentSteps = -1;
        this.mEnrollmentCancel = new CancellationSignal();
        this.mEnrolling = true;
    }

    public boolean cancelEnrollment() {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        if (!this.mEnrolling) {
            return false;
        }
        this.mEnrollmentCancel.cancel();
        this.mEnrolling = false;
        this.mEnrollmentSteps = -1;
        return true;
    }

    protected void onEnrollmentProgress(int i) {
        if (this.mEnrollmentSteps == -1) {
            this.mEnrollmentSteps = i;
        }
        this.mEnrollmentRemaining = i;
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onEnrollmentProgressChange(this.mEnrollmentSteps, i);
        } else {
            this.mQueuedEvents.add(new QueuedEnrollmentProgress(this, this.mEnrollmentSteps, i));
        }
    }

    protected void onEnrollmentHelp(int i, CharSequence charSequence) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onEnrollmentHelp(i, charSequence);
        } else {
            this.mQueuedEvents.add(new QueuedEnrollmentHelp(this, i, charSequence));
        }
    }

    protected void onEnrollmentError(int i, CharSequence charSequence) {
        Listener listener = this.mListener;
        if (listener != null) {
            listener.onEnrollmentError(i, charSequence);
        } else {
            this.mQueuedEvents.add(new QueuedEnrollmentError(this, i, charSequence));
        }
        this.mEnrolling = false;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
        if (listener != null) {
            for (int i = 0; i < this.mQueuedEvents.size(); i++) {
                this.mQueuedEvents.get(i).send(this.mListener);
            }
            this.mQueuedEvents.clear();
        }
    }

    public int getEnrollmentSteps() {
        return this.mEnrollmentSteps;
    }

    public int getEnrollmentRemaining() {
        return this.mEnrollmentRemaining;
    }
}
