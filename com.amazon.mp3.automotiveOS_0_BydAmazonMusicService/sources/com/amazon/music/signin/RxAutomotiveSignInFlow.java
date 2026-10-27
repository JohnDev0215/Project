package com.amazon.music.signin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class RxAutomotiveSignInFlow {
    private static final Logger LOG = LoggerFactory.getLogger(RxAutomotiveSignInFlow.class.getSimpleName());
    private Func1<Void, Void> completeSignInFlow = new Func1<Void, Void>() { // from class: com.amazon.music.signin.RxAutomotiveSignInFlow.4
        @Override // rx.functions.Func1
        public Void call(Void r1) {
            RxAutomotiveSignInFlow.this.rxSignInTaskIdStream.onCompleted();
            return null;
        }
    };
    private Context context;
    private MAPAccountManager mapAccountManager;
    private RxMAPAccountDataLookup rxMAPAccountDataLookup;
    private RxMAPAccountDataSaver rxMAPAccountDataSaver;
    private RxSignInTaskExecutor rxSignInTaskExecutor;
    private RxSignInTaskIdStream rxSignInTaskIdStream;
    private RxSignInWithLinkCode rxSignInWithLinkCode;
    private RxSignInWithNativeScreen rxSignInWithNativeScreen;

    public RxAutomotiveSignInFlow(@NonNull Context context, @NonNull SharedPreferences sharedPreferences, @NonNull List<SignInTask> list) {
        Validate.notNull(context, "Context cannot be null", new Object[0]);
        Validate.notNull(sharedPreferences, "SharedPreferences cannot be null", new Object[0]);
        Validate.notNull(list, "SignInTasks cannot be null", new Object[0]);
        this.context = context;
        this.rxSignInTaskIdStream = new RxSignInTaskIdStream();
        this.rxSignInWithNativeScreen = new RxSignInWithNativeScreen(this.rxSignInTaskIdStream, context);
        this.rxSignInWithLinkCode = new RxSignInWithLinkCode(this.rxSignInTaskIdStream, context);
        this.rxMAPAccountDataLookup = new RxMAPAccountDataLookup(new MAPAccountDataLookup(Boolean.FALSE), this.rxSignInTaskIdStream, context);
        this.rxMAPAccountDataSaver = new RxMAPAccountDataSaver(new MAPAccountDataSaver(sharedPreferences), this.rxSignInTaskIdStream);
        this.rxSignInTaskExecutor = new RxSignInTaskExecutor(list, this.rxSignInTaskIdStream, new SignInTaskCache(context));
        this.mapAccountManager = new MAPAccountManager(context);
    }

    public RxSignInTaskIdStream signIn(@NonNull Activity activity, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (!TextUtils.isEmpty(this.mapAccountManager.getAccount())) {
            this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SSO_SIGN_IN_START);
            return executeSignInWithoutScreen();
        }
        if (bundle.containsKey(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL)) {
            bundle2.putString(MAPAccountManager.KEY_LINK_CODE, bundle.getString(MAPAccountManager.KEY_LINK_CODE));
            return executeSignInWithLinkCode(bundle2, bundle.getLong(MAPAccountManager.KEY_LINK_CODE_POLLING_INTERVAL));
        }
        bundle2.putString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME, bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME));
        bundle2.putString("password", bundle.getString("password"));
        return executeSignInWithNativeScreen(activity, bundle2);
    }

    public RxSignInTaskIdStream executeSignInWithoutScreen() {
        this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_TASKS_WITHOUT_DIALOG_START);
        getSSOAccountId().map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSignInTaskExecutor.executeTasks()).map(this.completeSignInFlow).subscribeOn(Schedulers.newThread()).subscribe((Subscriber) new Subscriber<Void>() { // from class: com.amazon.music.signin.RxAutomotiveSignInFlow.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onNext(Void r1) {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                RxAutomotiveSignInFlow.LOG.error("Fail to sign in via SSO with error {}", th.getMessage());
                RxAutomotiveSignInFlow.this.rxSignInTaskIdStream.onError(th);
            }
        });
        return this.rxSignInTaskIdStream;
    }

    public RxSignInTaskIdStream executeSignInWithNativeScreen(@NonNull Activity activity, Bundle bundle) {
        this.rxSignInWithNativeScreen.signInWithNativeScreen(activity, bundle).map(this.rxSignInWithNativeScreen.emitTaskId()).map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSignInTaskExecutor.executeTasks()).map(this.completeSignInFlow).subscribeOn(Schedulers.newThread()).subscribe((Subscriber) new Subscriber<Void>() { // from class: com.amazon.music.signin.RxAutomotiveSignInFlow.2
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onNext(Void r1) {
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                RxAutomotiveSignInFlow.LOG.error("Fail to sign in through MAP registerAccount() with error {}", th.getMessage());
                RxAutomotiveSignInFlow.this.rxSignInTaskIdStream.onError(th);
            }
        });
        return this.rxSignInTaskIdStream;
    }

    public Observable<Bundle> executeRetrieveSignInLinkCode(Bundle bundle) {
        return this.rxSignInWithLinkCode.retrieveLinkCode(bundle);
    }

    public RxSignInTaskIdStream executeSignInWithLinkCode(Bundle bundle, long j) {
        Subscriber<Void> subscriber = new Subscriber<Void>() { // from class: com.amazon.music.signin.RxAutomotiveSignInFlow.3
            @Override // rx.Observer
            public void onNext(Void r1) {
            }

            @Override // rx.Observer
            public void onCompleted() {
                unsubscribe();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                RxAutomotiveSignInFlow.LOG.error("Fail to sign in through MAP registerAccount() with error {}", th.getMessage());
                RxAutomotiveSignInFlow.this.rxSignInTaskIdStream.onError(th);
                unsubscribe();
            }
        };
        this.rxSignInWithLinkCode.signInWithLinkCode(bundle, j, subscriber).map(this.rxSignInWithLinkCode.emitTaskId()).map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSignInTaskExecutor.executeTasks()).map(this.completeSignInFlow).subscribeOn(Schedulers.newThread()).subscribe((Subscriber) subscriber);
        return this.rxSignInTaskIdStream;
    }

    public void cancelLinkCodePollingSubscriber() {
        this.rxSignInWithLinkCode.cancelLinkCodePollingSubscriber();
    }

    public int getCountOfRemainingTasksToExecute() {
        return this.rxSignInTaskExecutor.getCountOfRemainingTasksToExecute();
    }

    public static String getRegistrationEndpoint(@Nullable Locale locale) {
        return SignInRegistrationParameters.getRegistrationEndpoint(locale);
    }

    private Observable<MAPAccountIdResponse> getSSOAccountId() {
        return Observable.create(new Observable.OnSubscribe<MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxAutomotiveSignInFlow.5
            @Override // rx.functions.Action1
            public void call(Subscriber<? super MAPAccountIdResponse> subscriber) {
                subscriber.onNext(new MAPAccountIdResponse(RxAutomotiveSignInFlow.this.mapAccountManager.getAccount()));
            }
        });
    }
}
