package com.amazon.music.signin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
class RxSignInWithDialog {
    private static final Logger LOG = LoggerFactory.getLogger(RxSignInWithDialog.class.getSimpleName());
    private final Context context;
    private MAPErrorBundleInterpreter mapErrorBundleInterpreter;
    private final RxSignInTaskIdStream rxSignInTaskIdStream;
    private final SignInWithDialog signInWithDialog;
    private Func1<MAPAccountIdResponse, MAPAccountIdResponse> emitTaskId = new Func1<MAPAccountIdResponse, MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithDialog.2
        @Override // rx.functions.Func1
        public MAPAccountIdResponse call(MAPAccountIdResponse mAPAccountIdResponse) {
            RxSignInWithDialog.this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_WITH_DIALOG_COMPLETE);
            return mAPAccountIdResponse;
        }
    };
    private Func1<MAPAccountIdResponse, MAPAccountIdResponse> setAccountAsDefaultForThisApp = new Func1<MAPAccountIdResponse, MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithDialog.3
        @Override // rx.functions.Func1
        public MAPAccountIdResponse call(MAPAccountIdResponse mAPAccountIdResponse) {
            try {
                RxSignInWithDialog.this.signInWithDialog.setAccountAsDefaultForThisApp(mAPAccountIdResponse.getAccountId(), RxSignInWithDialog.this.context);
            } catch (Exception e) {
                RxSignInWithDialog.this.rxSignInTaskIdStream.onError(e);
            }
            return mAPAccountIdResponse;
        }
    };
    private Func1<MAPAccountIdResponse, MAPAccountIdResponse> emitRecoverAccountTaskId = new Func1<MAPAccountIdResponse, MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithDialog.5
        @Override // rx.functions.Func1
        public MAPAccountIdResponse call(MAPAccountIdResponse mAPAccountIdResponse) {
            RxSignInWithDialog.LOG.error("Sending task id: SIGN_IN_WITH_RECOVER_ACCOUNT_COMPLETE");
            RxSignInWithDialog.this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_WITH_RECOVER_ACCOUNT_COMPLETE);
            return mAPAccountIdResponse;
        }
    };

    protected RxSignInWithDialog(@NonNull SignInWithDialog signInWithDialog, @NonNull RxSignInTaskIdStream rxSignInTaskIdStream, @NonNull Context context) {
        Validate.notNull(signInWithDialog, "SignInWithDialog cannot be null", new Object[0]);
        Validate.notNull(rxSignInTaskIdStream, "RxSignInTaskIdStream cannot be null", new Object[0]);
        Validate.notNull(context, "Context cannot be null", new Object[0]);
        this.signInWithDialog = signInWithDialog;
        this.rxSignInTaskIdStream = rxSignInTaskIdStream;
        this.context = context;
        this.mapErrorBundleInterpreter = new MAPErrorBundleInterpreter();
    }

    protected Observable<MAPAccountIdResponse> signInWithDialog(@NonNull final Activity activity) {
        return Observable.create(new Observable.OnSubscribe<MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithDialog.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super MAPAccountIdResponse> subscriber) {
                RxSignInWithDialog.LOG.info("Starting user sign in with Auth Portal sign in screen.");
                RxSignInWithDialog.this.signInWithDialog.signInWithDialog(activity, new MAPSignInWithDialogCallback(subscriber, RxSignInWithDialog.this.mapErrorBundleInterpreter));
            }
        });
    }

    protected Func1<MAPAccountIdResponse, MAPAccountIdResponse> emitTaskId() {
        return this.emitTaskId;
    }

    protected Func1<MAPAccountIdResponse, MAPAccountIdResponse> setAccountAsDefaultForThisApp() {
        return this.setAccountAsDefaultForThisApp;
    }

    protected static final class MAPSignInWithDialogCallback implements Callback {
        private Set<MAPAccountManager.RegistrationError> acceptableMAPErrors = new HashSet<MAPAccountManager.RegistrationError>() { // from class: com.amazon.music.signin.RxSignInWithDialog.MAPSignInWithDialogCallback.1
            {
                add(MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS);
                add(MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED);
            }
        };
        private final Subscriber<? super MAPAccountIdResponse> mSignInWithDialogSubscriber;
        private final MAPErrorBundleInterpreter mapErrorBundleInterpreter;
        private String returnedAccountId;

        protected MAPSignInWithDialogCallback(@NonNull Subscriber<? super MAPAccountIdResponse> subscriber, @NonNull MAPErrorBundleInterpreter mAPErrorBundleInterpreter) {
            Validate.notNull(subscriber, "signInWithDialogSubscriber cannot be null", new Object[0]);
            this.mSignInWithDialogSubscriber = subscriber;
            this.mapErrorBundleInterpreter = mAPErrorBundleInterpreter;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(@NonNull Bundle bundle) {
            Validate.notNull(bundle, "result cannot be null", new Object[0]);
            int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
            MAPAccountManager.RegistrationError registrationErrorFromValue = MAPAccountManager.RegistrationError.fromValue(i);
            String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
            String mapAccountManagerDetailedErrorType = this.mapErrorBundleInterpreter.getMapAccountManagerDetailedErrorType(bundle);
            if (this.acceptableMAPErrors.contains(registrationErrorFromValue)) {
                RxSignInWithDialog.LOG.info("Sign in with MAP registerAccountWithUI returned an acceptable error {}, so proceed with sign in", registrationErrorFromValue);
                String string2 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
                this.returnedAccountId = string2;
                this.mSignInWithDialogSubscriber.onNext(new MAPAccountIdResponse(string2));
                return;
            }
            RxSignInWithDialog.LOG.error("Sign in with MAP registerAccountWithUI returned error {}with error message {}", registrationErrorFromValue, string);
            this.mSignInWithDialogSubscriber.onError(new MAPRegistrationException(i, string, mapAccountManagerDetailedErrorType, MAPApiName.REGISTER_ACCOUNT_WITH_UI));
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(@NonNull Bundle bundle) {
            RxSignInWithDialog.LOG.info("Sign in with with MAP registerAccountWithUI sign in returned success.");
            String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
            this.returnedAccountId = string;
            this.mSignInWithDialogSubscriber.onNext(new MAPAccountIdResponse(string));
        }
    }

    protected Observable<MAPAccountIdResponse> signInWithRecoverAccount(@NonNull final Activity activity) {
        return Observable.create(new Observable.OnSubscribe<MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithDialog.4
            @Override // rx.functions.Action1
            public void call(Subscriber<? super MAPAccountIdResponse> subscriber) {
                RxSignInWithDialog.LOG.error("Starting user sign in with MAP recoverAccount API.");
                RxSignInWithDialog.this.signInWithDialog.signInWithRecoverAccount(activity, new MAPRecoverAccountCallback(activity.getApplicationContext(), subscriber, RxSignInWithDialog.this.mapErrorBundleInterpreter));
            }
        });
    }

    protected static final class MAPRecoverAccountCallback implements Callback {
        private final Context context;
        private final Subscriber<? super MAPAccountIdResponse> mSignInWithDialogSubscriber;
        private final MAPErrorBundleInterpreter mapErrorBundleInterpreter;

        protected MAPRecoverAccountCallback(@NonNull Context context, @NonNull Subscriber<? super MAPAccountIdResponse> subscriber, @NonNull MAPErrorBundleInterpreter mAPErrorBundleInterpreter) {
            Validate.notNull(subscriber, "signInWithDialogSubscriber cannot be null", new Object[0]);
            this.context = context;
            this.mSignInWithDialogSubscriber = subscriber;
            this.mapErrorBundleInterpreter = mAPErrorBundleInterpreter;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            String account = new MAPAccountManager(this.context).getAccount();
            RxSignInWithDialog.LOG.error("Sign in with with MAP recoverAccount sign in returned success.");
            this.mSignInWithDialogSubscriber.onNext(new MAPAccountIdResponse(account));
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
            String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
            String mapAccountManagerDetailedErrorType = this.mapErrorBundleInterpreter.getMapAccountManagerDetailedErrorType(bundle);
            RxSignInWithDialog.LOG.error("Sign in with MAP recoverAccount returned error code {} with error message {}", Integer.valueOf(i), string);
            this.mSignInWithDialogSubscriber.onError(new MAPRegistrationException(i, string, mapAccountManagerDetailedErrorType, MAPApiName.RECOVER_ACCOUNT));
        }
    }

    protected Func1<MAPAccountIdResponse, MAPAccountIdResponse> emitRecoverAccountTaskId() {
        return this.emitRecoverAccountTaskId;
    }
}
