package com.amazon.music.signin;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.music.signin.RxSignInRetryStrategyUtility;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/* JADX INFO: loaded from: classes4.dex */
public class RxSignInWithLinkCode {
    private static final Logger LOG = LoggerFactory.getLogger(RxSignInWithLinkCode.class.getSimpleName());
    private final int BACKOFF_EXP_START;
    private final Long MAX_BACKOFF_MILLIS;
    private final int MAX_BACKOFF_TRIES;
    private final Long MAX_TTL_MILLIS;
    private final Long MIN_BACKOFF_MILLIS;
    private final Long MIN_TTL_MILLIS;
    private final Context context;
    private final RxSignInTaskIdStream rxSignInTaskIdStream;
    private Subscriber<Void> signInFlowSubscriber;

    public RxSignInWithLinkCode(@NonNull RxSignInTaskIdStream rxSignInTaskIdStream, @NonNull Context context) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.MIN_TTL_MILLIS = Long.valueOf(timeUnit.toMillis(10L));
        this.MAX_TTL_MILLIS = Long.valueOf(timeUnit.toMillis(60L));
        this.MIN_BACKOFF_MILLIS = 100L;
        this.MAX_BACKOFF_MILLIS = Long.valueOf(TimeUnit.SECONDS.toMillis(8L));
        this.MAX_BACKOFF_TRIES = 8;
        this.BACKOFF_EXP_START = 0;
        Validate.notNull(rxSignInTaskIdStream, "RxSignInTaskIdStream cannot be null", new Object[0]);
        this.rxSignInTaskIdStream = rxSignInTaskIdStream;
        this.context = context;
    }

    public void cancelLinkCodePollingSubscriber() {
        Subscriber<Void> subscriber = this.signInFlowSubscriber;
        if (subscriber != null) {
            subscriber.onCompleted();
        }
    }

    protected static final class MAPRetrieveLinkCodeCallback implements Callback {
        private final Subscriber<Bundle> linkCodeRequestSubscriber;

        protected MAPRetrieveLinkCodeCallback(@NonNull Subscriber<Bundle> subscriber) {
            Validate.notNull(subscriber, "LinkCodeSubscriber cannot be null", new Object[0]);
            this.linkCodeRequestSubscriber = subscriber;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            if (bundle != null) {
                int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
                RxSignInWithLinkCode.LOG.error("Error creating link code with error code " + i + ". Message: " + bundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                if (i == 1) {
                    this.linkCodeRequestSubscriber.onError(new InternalError("An internal error occurred."));
                    return;
                } else {
                    this.linkCodeRequestSubscriber.onError(new Error("An error occurred while requesting link code."));
                    return;
                }
            }
            this.linkCodeRequestSubscriber.onError(new Error("Received a null bundle in onError of LinkCodeSubscriber."));
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            if (bundle != null) {
                RxSignInWithLinkCode.LOG.debug("Successfully received link code with TTL " + bundle.getLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE) + " and polling interval " + bundle.getLong(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL));
                this.linkCodeRequestSubscriber.onNext(bundle);
                this.linkCodeRequestSubscriber.onCompleted();
                return;
            }
            this.linkCodeRequestSubscriber.onError(new Error("Received a null bundle in onSuccess of LinkCodeSubscriber."));
        }
    }

    protected static final class MAPSignInWithLinkCodeCallback implements Callback {
        private String MAPAccountId;
        private final Set<MAPAccountManager.RegistrationError> acceptableMAPErrors = new HashSet<MAPAccountManager.RegistrationError>() { // from class: com.amazon.music.signin.RxSignInWithLinkCode.MAPSignInWithLinkCodeCallback.1
            {
                add(MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS);
                add(MAPAccountManager.RegistrationError.DEVICE_ALREADY_REGISTERED);
            }
        };
        private final Subscriber<? super MAPAccountIdResponse> linkCodeRegistrationSubscriber;
        private final RxSignInTaskIdStream rxSignInTaskIdStream;

        protected MAPSignInWithLinkCodeCallback(@NonNull Subscriber<? super MAPAccountIdResponse> subscriber, @NonNull RxSignInTaskIdStream rxSignInTaskIdStream) {
            Validate.notNull(subscriber, "linkCodeSubscriber cannot be null", new Object[0]);
            this.linkCodeRegistrationSubscriber = subscriber;
            this.rxSignInTaskIdStream = rxSignInTaskIdStream;
        }

        private void emitMAPAccountId(@NonNull Bundle bundle) {
            String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
            this.MAPAccountId = string;
            this.linkCodeRegistrationSubscriber.onNext(new MAPAccountIdResponse(string));
            this.linkCodeRegistrationSubscriber.onCompleted();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(@NonNull Bundle bundle) {
            int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode");
            String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
            MAPAccountManager.RegistrationError registrationErrorFromValue = MAPAccountManager.RegistrationError.fromValue(i);
            if (this.acceptableMAPErrors.contains(registrationErrorFromValue)) {
                emitMAPAccountId(bundle);
                return;
            }
            if (registrationErrorFromValue == MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED) {
                this.linkCodeRegistrationSubscriber.onError(new RxSignInRetryStrategyUtility.AuthenticationFailedError(string));
                return;
            }
            if (registrationErrorFromValue == MAPAccountManager.RegistrationError.BAD_REQUEST) {
                this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_CODE_INVALID);
            } else {
                this.linkCodeRegistrationSubscriber.onError(new Error(string));
                RxSignInWithLinkCode.LOG.error("Sign in with MAP registerAccount() but error returned {} with error message {}", registrationErrorFromValue, string);
            }
            this.linkCodeRegistrationSubscriber.onCompleted();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(@NonNull Bundle bundle) {
            emitMAPAccountId(bundle);
        }
    }

    protected Long getValidLinkCodeTTL(Long l) {
        return (l.longValue() > this.MAX_TTL_MILLIS.longValue() || l.longValue() < this.MIN_TTL_MILLIS.longValue()) ? this.MIN_TTL_MILLIS : l;
    }

    protected Observable<Bundle> retrieveLinkCode(final Bundle bundle) {
        SignInRegistrationParameters.setUpRegistrationParametersForLinkCodeRequest(bundle, this.context.getResources().getConfiguration().locale);
        bundle.putLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE, getValidLinkCodeTTL(Long.valueOf(bundle.getLong(MAPAccountManager.KEY_LINK_CODE_TIME_TO_LIVE, this.MIN_TTL_MILLIS.longValue()))).longValue());
        return Observable.create(new Observable.OnSubscribe<Bundle>() { // from class: com.amazon.music.signin.RxSignInWithLinkCode.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super Bundle> subscriber) {
                new MAPAccountManager(RxSignInWithLinkCode.this.context).generateLinkCode(bundle, new MAPRetrieveLinkCodeCallback(subscriber));
            }
        }).retryWhen(RxSignInRetryStrategyUtility.exponentialBackoffStrategy(0, 8, this.MIN_BACKOFF_MILLIS.longValue(), this.MAX_BACKOFF_MILLIS.longValue()));
    }

    protected Observable<MAPAccountIdResponse> signInWithLinkCode(@NonNull final Bundle bundle, long j, final Subscriber<Void> subscriber) {
        return Observable.create(new Observable.OnSubscribe<MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithLinkCode.2
            @Override // rx.functions.Action1
            public void call(Subscriber<? super MAPAccountIdResponse> subscriber2) {
                RxSignInWithLinkCode.this.signInFlowSubscriber = subscriber;
                SignInRegistrationParameters.setUpRegistrationParametersForNativeSignIn(bundle, RxSignInWithLinkCode.this.context.getResources().getConfiguration().locale);
                new MAPAccountManager(RxSignInWithLinkCode.this.context).registerAccount(RegistrationType.WITH_LINK_CODE, bundle, new MAPSignInWithLinkCodeCallback(subscriber2, RxSignInWithLinkCode.this.rxSignInTaskIdStream));
            }
        }).retryWhen(RxSignInRetryStrategyUtility.iterativePollingStrategy(j));
    }

    protected Func1<MAPAccountIdResponse, MAPAccountIdResponse> emitTaskId() {
        return new Func1<MAPAccountIdResponse, MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInWithLinkCode.3
            @Override // rx.functions.Func1
            public MAPAccountIdResponse call(MAPAccountIdResponse mAPAccountIdResponse) {
                RxSignInWithLinkCode.this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_WITH_LINK_CODE_COMPLETE);
                return mAPAccountIdResponse;
            }
        };
    }
}
