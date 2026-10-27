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
public class RxSignInWithNativeScreen {
    private static final Logger LOG = LoggerFactory.getLogger(RxSignInWithNativeScreen.class.getSimpleName());
    private final Context context;
    private final MAPErrorBundleInterpreter mapErrorBundleInterpreter;
    private final RxSignInTaskIdStream rxSignInTaskIdStream;

    protected RxSignInWithNativeScreen(@NonNull RxSignInTaskIdStream rxSignInTaskIdStream, @NonNull Context context) {
        Validate.notNull(rxSignInTaskIdStream, "RxSignInTaskIdStream cannot be null", new Object[0]);
        Validate.notNull(context, "Context cannot be null", new Object[0]);
        this.rxSignInTaskIdStream = rxSignInTaskIdStream;
        this.context = context;
        this.mapErrorBundleInterpreter = new MAPErrorBundleInterpreter();
    }

    protected static final class MAPSignInWithNativeScreenCallback implements Callback {
        private String MAPAccountId;
        private final Set<MAPAccountManager.RegistrationError> acceptableMAPErrors = new HashSet<MAPAccountManager.RegistrationError>() { // from class: com.amazon.music.signin.RxSignInWithNativeScreen.MAPSignInWithNativeScreenCallback.1
            {
                add(MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS);
                add(MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED);
            }
        };
        private final MAPErrorBundleInterpreter mapErrorBundleInterpreter;
        private final Subscriber<? super MAPAccountIdResponse> signInWithNativeScreenSubscriber;

        protected MAPSignInWithNativeScreenCallback(@NonNull Subscriber<? super MAPAccountIdResponse> subscriber, @NonNull MAPErrorBundleInterpreter mAPErrorBundleInterpreter) {
            Validate.notNull(subscriber, "SignInWithNativeScreenSubscriber cannot be null", new Object[0]);
            this.signInWithNativeScreenSubscriber = subscriber;
            this.mapErrorBundleInterpreter = mAPErrorBundleInterpreter;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(@NonNull Bundle bundle) {
            Validate.notNull(bundle, "result cannot be null", new Object[0]);
            int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
            String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
            String mapAccountManagerDetailedErrorType = this.mapErrorBundleInterpreter.getMapAccountManagerDetailedErrorType(bundle);
            MAPAccountManager.RegistrationError registrationErrorFromValue = MAPAccountManager.RegistrationError.fromValue(i);
            if (!this.acceptableMAPErrors.contains(registrationErrorFromValue)) {
                RxSignInWithNativeScreen.LOG.error("Sign in with MAP registerAccount() but error returned {} with error message {}", registrationErrorFromValue, string);
                this.signInWithNativeScreenSubscriber.onError(new MAPRegistrationException(i, string, mapAccountManagerDetailedErrorType, MAPApiName.REGISTER_ACCOUNT_WITH_NATIVE_SCREEN));
            } else {
                String string2 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
                this.MAPAccountId = string2;
                this.signInWithNativeScreenSubscriber.onNext(new MAPAccountIdResponse(string2));
            }
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(@NonNull Bundle bundle) {
            String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
            this.MAPAccountId = string;
            this.signInWithNativeScreenSubscriber.onNext(new MAPAccountIdResponse(string));
        }
    }

    protected Func1<MAPAccountIdResponse, MAPAccountIdResponse> emitTaskId() {
        return new Func1<MAPAccountIdResponse, MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithNativeScreen.1
            @Override // rx.functions.Func1
            public MAPAccountIdResponse call(MAPAccountIdResponse mAPAccountIdResponse) {
                RxSignInWithNativeScreen.this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_WITH_NATIVE_SCREEN_COMPLETE);
                return mAPAccountIdResponse;
            }
        };
    }

    protected Observable<MAPAccountIdResponse> signInWithNativeScreen(@NonNull final Activity activity, @NonNull final Bundle bundle) {
        return Observable.create(new Observable.OnSubscribe<MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithNativeScreen.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super MAPAccountIdResponse> subscriber) {
                SignInRegistrationParameters.setUpRegistrationParametersForNativeSignIn(bundle, activity.getApplicationContext().getResources().getConfiguration().locale);
                new MAPAccountManager(activity.getApplicationContext()).registerAccount(activity, bundle, null, new MAPSignInWithNativeScreenCallback(subscriber, RxSignInWithNativeScreen.this.mapErrorBundleInterpreter));
            }
        });
    }
}
