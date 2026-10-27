package android.telecom;

import android.annotation.SystemApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.telecom.Call;
import com.android.internal.os.SomeArgs;
import com.android.internal.telecom.ICallScreeningAdapter;
import com.android.internal.telecom.ICallScreeningService;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CallScreeningService extends Service {
    private static final int MSG_SCREEN_CALL = 1;
    public static final String SERVICE_INTERFACE = "android.telecom.CallScreeningService";
    private ICallScreeningAdapter mCallScreeningAdapter;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: android.telecom.CallScreeningService.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            SomeArgs someArgs = (SomeArgs) message.obj;
            try {
                try {
                    CallScreeningService.this.mCallScreeningAdapter = (ICallScreeningAdapter) someArgs.arg1;
                    Call.Details detailsCreateFromParcelableCall = Call.Details.createFromParcelableCall((ParcelableCall) someArgs.arg2);
                    CallScreeningService.this.onScreenCall(detailsCreateFromParcelableCall);
                    if (detailsCreateFromParcelableCall.getCallDirection() == 1) {
                        CallScreeningService.this.mCallScreeningAdapter.allowCall(detailsCreateFromParcelableCall.getTelecomCallId());
                    }
                } catch (RemoteException e) {
                    Log.w(this, "Exception when screening call: " + e, new Object[0]);
                }
            } finally {
                someArgs.recycle();
            }
        }
    };

    public abstract void onScreenCall(Call.Details details);

    private final class CallScreeningBinder extends ICallScreeningService.Stub {
        private CallScreeningBinder() {
        }

        @Override // com.android.internal.telecom.ICallScreeningService
        public void screenCall(ICallScreeningAdapter iCallScreeningAdapter, ParcelableCall parcelableCall) {
            Log.v(this, "screenCall", new Object[0]);
            SomeArgs someArgsObtain = SomeArgs.obtain();
            someArgsObtain.arg1 = iCallScreeningAdapter;
            someArgsObtain.arg2 = parcelableCall;
            CallScreeningService.this.mHandler.obtainMessage(1, someArgsObtain).sendToTarget();
        }
    }

    public static class CallResponse {
        private final boolean mShouldDisallowCall;
        private final boolean mShouldRejectCall;
        private final boolean mShouldScreenCallViaAudioProcessing;
        private final boolean mShouldSilenceCall;
        private final boolean mShouldSkipCallLog;
        private final boolean mShouldSkipNotification;

        private CallResponse(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
            if (!z && (z2 || z4 || z5)) {
                throw new IllegalStateException("Invalid response state for allowed call.");
            }
            if (z && z6) {
                throw new IllegalStateException("Invalid response state for allowed call.");
            }
            this.mShouldDisallowCall = z;
            this.mShouldRejectCall = z2;
            this.mShouldSkipCallLog = z4;
            this.mShouldSkipNotification = z5;
            this.mShouldSilenceCall = z3;
            this.mShouldScreenCallViaAudioProcessing = z6;
        }

        public boolean getDisallowCall() {
            return this.mShouldDisallowCall;
        }

        public boolean getRejectCall() {
            return this.mShouldRejectCall;
        }

        public boolean getSilenceCall() {
            return this.mShouldSilenceCall;
        }

        public boolean getSkipCallLog() {
            return this.mShouldSkipCallLog;
        }

        public boolean getSkipNotification() {
            return this.mShouldSkipNotification;
        }

        public boolean getShouldScreenCallViaAudioProcessing() {
            return this.mShouldScreenCallViaAudioProcessing;
        }

        public static class Builder {
            private boolean mShouldDisallowCall;
            private boolean mShouldRejectCall;
            private boolean mShouldScreenCallViaAudioProcessing;
            private boolean mShouldSilenceCall;
            private boolean mShouldSkipCallLog;
            private boolean mShouldSkipNotification;

            public Builder setDisallowCall(boolean z) {
                this.mShouldDisallowCall = z;
                return this;
            }

            public Builder setRejectCall(boolean z) {
                this.mShouldRejectCall = z;
                return this;
            }

            public Builder setSilenceCall(boolean z) {
                this.mShouldSilenceCall = z;
                return this;
            }

            public Builder setSkipCallLog(boolean z) {
                this.mShouldSkipCallLog = z;
                return this;
            }

            public Builder setSkipNotification(boolean z) {
                this.mShouldSkipNotification = z;
                return this;
            }

            @SystemApi
            public Builder setShouldScreenCallViaAudioProcessing(boolean z) {
                this.mShouldScreenCallViaAudioProcessing = z;
                return this;
            }

            public CallResponse build() {
                return new CallResponse(this.mShouldDisallowCall, this.mShouldRejectCall, this.mShouldSilenceCall, this.mShouldSkipCallLog, this.mShouldSkipNotification, this.mShouldScreenCallViaAudioProcessing);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.v(this, "onBind", new Object[0]);
        return new CallScreeningBinder();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        Log.v(this, "onUnbind", new Object[0]);
        return false;
    }

    public final void respondToCall(Call.Details details, CallResponse callResponse) {
        try {
            if (callResponse.getDisallowCall()) {
                this.mCallScreeningAdapter.disallowCall(details.getTelecomCallId(), callResponse.getRejectCall(), !callResponse.getSkipCallLog(), !callResponse.getSkipNotification(), new ComponentName(getPackageName(), getClass().getName()));
            } else if (callResponse.getSilenceCall()) {
                this.mCallScreeningAdapter.silenceCall(details.getTelecomCallId());
            } else if (callResponse.getShouldScreenCallViaAudioProcessing()) {
                this.mCallScreeningAdapter.screenCallFurther(details.getTelecomCallId());
            } else {
                this.mCallScreeningAdapter.allowCall(details.getTelecomCallId());
            }
        } catch (RemoteException unused) {
        }
    }
}
