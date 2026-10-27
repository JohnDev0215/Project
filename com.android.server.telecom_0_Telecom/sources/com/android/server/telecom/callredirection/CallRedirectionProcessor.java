package com.android.server.telecom.callredirection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telecom.GatewayInfo;
import android.telecom.Log;
import android.telecom.Logging.Runnable;
import android.telecom.PhoneAccountHandle;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telecom.ICallRedirectionAdapter;
import com.android.internal.telecom.ICallRedirectionService;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.PhoneAccountRegistrar;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;

/* JADX INFO: loaded from: classes.dex */
public class CallRedirectionProcessor {
    private final boolean mAllowInteractiveResponse;
    private CallRedirectionAttempt mAttempt;
    private final Call mCall;
    private CallRedirectionProcessorHelper mCallRedirectionProcessorHelper;
    private final CallsManager mCallsManager;
    private final Context mContext;
    private Uri mDestinationUri;
    private PhoneAccountHandle mPhoneAccountHandle;
    private String mPostDialDigits;
    private Uri mProcessedDestinationUri;
    private GatewayInfo mRedirectionGatewayInfo;
    private final boolean mSpeakerphoneOn;
    private final TelecomSystem.SyncRoot mTelecomLock;
    private final Timeouts.Adapter mTimeoutsAdapter;
    private final int mVideoState;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mShouldCancelCall = false;
    private String mUiAction = "no_action";
    private boolean mIsUserDefinedRedirectionPending = false;
    private boolean mIsCarrierRedirectionPending = false;

    private class CallRedirectionAttempt {
        private final ComponentName mComponentName;
        private ServiceConnection mConnection;
        private ICallRedirectionService mService;
        private final String mServiceType;

        private CallRedirectionAttempt(ComponentName componentName, String str) {
            this.mComponentName = componentName;
            this.mServiceType = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void process() {
            Intent component = new Intent("android.telecom.CallRedirectionService").setComponent(this.mComponentName);
            CallRedirectionServiceConnection callRedirectionServiceConnection = new CallRedirectionServiceConnection();
            if (CallRedirectionProcessor.this.mContext.bindServiceAsUser(component, callRedirectionServiceConnection, 68157441, UserHandle.CURRENT)) {
                Log.d(this, "bindService, found " + this.mServiceType + " call redirection service, waiting for it to connect", new Object[0]);
                this.mConnection = callRedirectionServiceConnection;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onServiceBound(ICallRedirectionService iCallRedirectionService) {
            this.mService = iCallRedirectionService;
            try {
                iCallRedirectionService.placeCall(new CallRedirectionAdapter(), CallRedirectionProcessor.this.mProcessedDestinationUri, CallRedirectionProcessor.this.mPhoneAccountHandle, CallRedirectionProcessor.this.mAllowInteractiveResponse && this.mServiceType.equals("user_defined"));
                Log.addEvent(CallRedirectionProcessor.this.mCall, this.mServiceType.equals("user_defined") ? "REDIRECTION_SENT_USER" : "REDIRECTION_SENT_CARRIER", this.mComponentName);
                Log.d(this, "Requested placeCall with [Destination Uri] " + Log.pii(CallRedirectionProcessor.this.mProcessedDestinationUri) + " [phoneAccountHandle]" + CallRedirectionProcessor.this.mPhoneAccountHandle, new Object[0]);
            } catch (RemoteException e) {
                Log.e(this, e, "Failed to request with the found " + this.mServiceType + " call redirection service", new Object[0]);
                finishCallRedirection();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finishCallRedirection() {
            if ((this.mServiceType.equals("carrier") && CallRedirectionProcessor.this.mIsCarrierRedirectionPending) || (this.mServiceType.equals("user_defined") && CallRedirectionProcessor.this.mIsUserDefinedRedirectionPending)) {
                if (this.mConnection != null) {
                    CallRedirectionProcessor.this.mContext.unbindService(this.mConnection);
                    this.mConnection = null;
                }
                this.mService = null;
                CallRedirectionProcessor callRedirectionProcessor = CallRedirectionProcessor.this;
                callRedirectionProcessor.onCallRedirectionComplete(callRedirectionProcessor.mCall);
            }
        }

        private class CallRedirectionServiceConnection implements ServiceConnection {
            private CallRedirectionServiceConnection() {
            }

            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.startSession("CRSC.oSC");
                try {
                    synchronized (CallRedirectionProcessor.this.mTelecomLock) {
                        Log.addEvent(CallRedirectionProcessor.this.mCall, CallRedirectionAttempt.this.mServiceType.equals("user_defined") ? "REDIRECTION_BOUND_USER" : "REDIRECTION_BOUND_CARRIER", componentName);
                        CallRedirectionAttempt.this.onServiceBound(ICallRedirectionService.Stub.asInterface(iBinder));
                    }
                } finally {
                    Log.endSession();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.startSession("CRSC.oSD");
                try {
                    synchronized (CallRedirectionProcessor.this.mTelecomLock) {
                        CallRedirectionAttempt.this.finishCallRedirection();
                    }
                } finally {
                    Log.endSession();
                }
            }
        }

        private class CallRedirectionAdapter extends ICallRedirectionAdapter.Stub {
            private CallRedirectionAdapter() {
            }

            public void cancelCall() {
                Log.startSession("CRA.cC");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    synchronized (CallRedirectionProcessor.this.mTelecomLock) {
                        Log.d(this, "Received cancelCall from " + CallRedirectionAttempt.this.mServiceType + " call redirection service", new Object[0]);
                        CallRedirectionProcessor.this.mShouldCancelCall = true;
                        CallRedirectionAttempt.this.finishCallRedirection();
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }

            public void placeCallUnmodified() {
                Log.startSession("CRA.pCU");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    synchronized (CallRedirectionProcessor.this.mTelecomLock) {
                        Log.d(this, "Received placeCallUnmodified from " + CallRedirectionAttempt.this.mServiceType + " call redirection service", new Object[0]);
                        CallRedirectionAttempt.this.finishCallRedirection();
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }

            public void redirectCall(Uri uri, PhoneAccountHandle phoneAccountHandle, boolean z) {
                Log.startSession("CRA.rC");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    synchronized (CallRedirectionProcessor.this.mTelecomLock) {
                        CallRedirectionProcessor callRedirectionProcessor = CallRedirectionProcessor.this;
                        CallRedirectionProcessorHelper unused = CallRedirectionProcessor.this.mCallRedirectionProcessorHelper;
                        callRedirectionProcessor.mRedirectionGatewayInfo = CallRedirectionProcessorHelper.getGatewayInfoFromGatewayUri(CallRedirectionAttempt.this.mComponentName.getPackageName(), uri, CallRedirectionProcessor.this.mDestinationUri, CallRedirectionProcessor.this.mPostDialDigits);
                        CallRedirectionProcessor.this.mPhoneAccountHandle = phoneAccountHandle;
                        CallRedirectionProcessor.this.mUiAction = (z && CallRedirectionAttempt.this.mServiceType.equals("user_defined") && CallRedirectionProcessor.this.mAllowInteractiveResponse) ? "user_defined_ask_for_confirm" : "no_action";
                        Log.d(this, "Received redirectCall with [gatewayUri]" + Log.pii(uri) + " [phoneAccountHandle]" + CallRedirectionProcessor.this.mPhoneAccountHandle + "[confirmFirst]" + z + " from " + CallRedirectionAttempt.this.mServiceType + " call redirection service", new Object[0]);
                        CallRedirectionAttempt.this.finishCallRedirection();
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
        }
    }

    public CallRedirectionProcessor(Context context, CallsManager callsManager, Call call, Uri uri, PhoneAccountRegistrar phoneAccountRegistrar, GatewayInfo gatewayInfo, boolean z, int i) {
        this.mContext = context;
        this.mCallsManager = callsManager;
        this.mCall = call;
        this.mDestinationUri = uri;
        this.mPhoneAccountHandle = call.getTargetPhoneAccount();
        this.mRedirectionGatewayInfo = gatewayInfo;
        this.mSpeakerphoneOn = z;
        this.mVideoState = i;
        this.mTimeoutsAdapter = callsManager.getTimeoutsAdapter();
        this.mTelecomLock = callsManager.getLock();
        this.mAllowInteractiveResponse = !callsManager.getSystemStateHelper().isCarMode();
        CallRedirectionProcessorHelper callRedirectionProcessorHelper = new CallRedirectionProcessorHelper(context, callsManager, phoneAccountRegistrar);
        this.mCallRedirectionProcessorHelper = callRedirectionProcessorHelper;
        this.mProcessedDestinationUri = callRedirectionProcessorHelper.formatNumberForRedirection(this.mDestinationUri);
        this.mPostDialDigits = this.mCallRedirectionProcessorHelper.getPostDialDigits(this.mDestinationUri);
    }

    public void onCallRedirectionComplete(Call call) {
        this.mHandler.post(new Runnable("CRP.oCRC", this.mTelecomLock) { // from class: com.android.server.telecom.callredirection.CallRedirectionProcessor.1
            public void loggedRun() {
                if (CallRedirectionProcessor.this.mIsUserDefinedRedirectionPending) {
                    Log.addEvent(CallRedirectionProcessor.this.mCall, "REDIRECTION_COMPLETED_USER");
                    CallRedirectionProcessor.this.mIsUserDefinedRedirectionPending = false;
                    if (!CallRedirectionProcessor.this.mShouldCancelCall) {
                        CallRedirectionProcessor.this.performCarrierCallRedirection();
                    } else {
                        CallRedirectionProcessor.this.mCallsManager.onCallRedirectionComplete(CallRedirectionProcessor.this.mCall, CallRedirectionProcessor.this.mDestinationUri, CallRedirectionProcessor.this.mPhoneAccountHandle, CallRedirectionProcessor.this.mRedirectionGatewayInfo, CallRedirectionProcessor.this.mSpeakerphoneOn, CallRedirectionProcessor.this.mVideoState, CallRedirectionProcessor.this.mShouldCancelCall, CallRedirectionProcessor.this.mUiAction);
                    }
                }
                if (CallRedirectionProcessor.this.mIsCarrierRedirectionPending) {
                    Log.addEvent(CallRedirectionProcessor.this.mCall, "REDIRECTION_COMPLETED_CARRIER");
                    CallRedirectionProcessor.this.mIsCarrierRedirectionPending = false;
                    CallRedirectionProcessor.this.mCallsManager.onCallRedirectionComplete(CallRedirectionProcessor.this.mCall, CallRedirectionProcessor.this.mDestinationUri, CallRedirectionProcessor.this.mPhoneAccountHandle, CallRedirectionProcessor.this.mRedirectionGatewayInfo, CallRedirectionProcessor.this.mSpeakerphoneOn, CallRedirectionProcessor.this.mVideoState, CallRedirectionProcessor.this.mShouldCancelCall, CallRedirectionProcessor.this.mUiAction);
                }
            }
        }.prepare());
    }

    public void performCallRedirection() {
        if (this.mRedirectionGatewayInfo != null) {
            performCarrierCallRedirection();
        } else {
            performUserDefinedCallRedirection();
        }
    }

    private void performUserDefinedCallRedirection() {
        Log.d(this, "performUserDefinedCallRedirection", new Object[0]);
        ComponentName userDefinedCallRedirectionService = this.mCallRedirectionProcessorHelper.getUserDefinedCallRedirectionService();
        if (userDefinedCallRedirectionService != null) {
            CallRedirectionAttempt callRedirectionAttempt = new CallRedirectionAttempt(userDefinedCallRedirectionService, "user_defined");
            this.mAttempt = callRedirectionAttempt;
            callRedirectionAttempt.process();
            this.mIsUserDefinedRedirectionPending = true;
            processTimeoutForCallRedirection("user_defined");
            return;
        }
        Log.i(this, "There are no user-defined call redirection services installed on this device.", new Object[0]);
        performCarrierCallRedirection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performCarrierCallRedirection() {
        Log.d(this, "performCarrierCallRedirection", new Object[0]);
        ComponentName carrierCallRedirectionService = this.mCallRedirectionProcessorHelper.getCarrierCallRedirectionService(this.mPhoneAccountHandle);
        if (carrierCallRedirectionService != null) {
            CallRedirectionAttempt callRedirectionAttempt = new CallRedirectionAttempt(carrierCallRedirectionService, "carrier");
            this.mAttempt = callRedirectionAttempt;
            callRedirectionAttempt.process();
            this.mIsCarrierRedirectionPending = true;
            processTimeoutForCallRedirection("carrier");
            return;
        }
        Log.i(this, "There are no carrier call redirection services installed on this device.", new Object[0]);
        this.mCallsManager.onCallRedirectionComplete(this.mCall, this.mDestinationUri, this.mPhoneAccountHandle, this.mRedirectionGatewayInfo, this.mSpeakerphoneOn, this.mVideoState, this.mShouldCancelCall, this.mUiAction);
    }

    private void processTimeoutForCallRedirection(final String str) {
        long carrierCallRedirectionTimeoutMillis;
        if (str.equals("user_defined")) {
            carrierCallRedirectionTimeoutMillis = this.mTimeoutsAdapter.getUserDefinedCallRedirectionTimeoutMillis(this.mContext.getContentResolver());
        } else {
            carrierCallRedirectionTimeoutMillis = this.mTimeoutsAdapter.getCarrierCallRedirectionTimeoutMillis(this.mContext.getContentResolver());
        }
        this.mHandler.postDelayed(new Runnable("CRP.pTFCR", null) { // from class: com.android.server.telecom.callredirection.CallRedirectionProcessor.2
            public void loggedRun() {
                if (str.equals("user_defined") ? CallRedirectionProcessor.this.mIsUserDefinedRedirectionPending : CallRedirectionProcessor.this.mIsCarrierRedirectionPending) {
                    Log.i(this, str + " call redirection has timed out.", new Object[0]);
                    Log.addEvent(CallRedirectionProcessor.this.mCall, str.equals("user_defined") ? "REDIRECTION_TIMED_OUT_USER" : "REDIRECTION_TIMED_OUT_CARRIER");
                    if (str.equals("user_defined")) {
                        CallRedirectionProcessor.this.mUiAction = "user_defined_timeout";
                        CallRedirectionProcessor.this.mShouldCancelCall = true;
                    }
                    CallRedirectionProcessor callRedirectionProcessor = CallRedirectionProcessor.this;
                    callRedirectionProcessor.onCallRedirectionComplete(callRedirectionProcessor.mCall);
                }
            }
        }.prepare(), carrierCallRedirectionTimeoutMillis);
    }

    public boolean canMakeCallRedirectionWithService() {
        boolean z = (this.mCallRedirectionProcessorHelper.getUserDefinedCallRedirectionService() == null && this.mCallRedirectionProcessorHelper.getCarrierCallRedirectionService(this.mPhoneAccountHandle) == null) ? false : true;
        Log.i(this, "Can make call redirection with any available service: " + z, new Object[0]);
        return z;
    }

    @VisibleForTesting
    public Handler getHandler() {
        return this.mHandler;
    }

    @VisibleForTesting
    public void setCallRedirectionServiceHelper(CallRedirectionProcessorHelper callRedirectionProcessorHelper) {
        this.mCallRedirectionProcessorHelper = callRedirectionProcessorHelper;
    }
}
