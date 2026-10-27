package com.amazon.music.signin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.util.List;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public final class RxSignInFlow {
    private static final Logger LOG = LoggerFactory.getLogger(RxSignInFlow.class.getSimpleName());
    private Context context;
    private MAPAccountManager mapAccountManager;
    private RxMAPAccountDataLookup rxMAPAccountDataLookup;
    private RxMAPAccountDataSaver rxMAPAccountDataSaver;
    private RxSignInTaskExecutor rxSignInTaskExecutor;
    private RxSignInTaskIdStream rxSignInTaskIdStream;
    private RxSignInWithDialog rxSignInWithDialog;
    private SharedPreferences sharedPreferences;
    private Func1<Void, Void> rxSetHasShownSSOWelcome = new Func1<Void, Void>() { // from class: com.amazon.music.signin.RxSignInFlow.2
        @Override // rx.functions.Func1
        public Void call(Void r1) {
            RxSignInFlow.this.setHasShownSSOWelcome();
            return null;
        }
    };
    private Func1<Void, Void> finishFlow = new Func1<Void, Void>() { // from class: com.amazon.music.signin.RxSignInFlow.3
        @Override // rx.functions.Func1
        public Void call(Void r1) {
            RxSignInFlow.this.rxSignInTaskIdStream.onCompleted();
            return null;
        }
    };
    private Action1<Void> internalOnNext = new Action1<Void>() { // from class: com.amazon.music.signin.RxSignInFlow.4
        @Override // rx.functions.Action1
        public void call(Void r1) {
        }
    };
    private Action1<Throwable> internalOnError = new Action1<Throwable>() { // from class: com.amazon.music.signin.RxSignInFlow.5
        @Override // rx.functions.Action1
        public void call(Throwable th) {
            RxSignInFlow.this.rxSignInTaskIdStream.onError(th);
        }
    };
    private Action0 internalOnCompleted = new Action0() { // from class: com.amazon.music.signin.RxSignInFlow.6
        @Override // rx.functions.Action0
        public void call() {
        }
    };

    public RxSignInFlow(@NonNull Context context, @NonNull SharedPreferences sharedPreferences, @NonNull List<SignInTask> list, @NonNull Boolean bool) {
        Validate.notNull(context, "Context cannot be null", new Object[0]);
        Validate.notNull(sharedPreferences, "SharedPreferences cannot be null", new Object[0]);
        Validate.notNull(list, "SignInTasks cannot be null", new Object[0]);
        this.context = context;
        this.sharedPreferences = sharedPreferences;
        this.rxSignInTaskIdStream = new RxSignInTaskIdStream();
        this.rxSignInWithDialog = new RxSignInWithDialog(new SignInWithDialog(), this.rxSignInTaskIdStream, context);
        this.rxMAPAccountDataLookup = new RxMAPAccountDataLookup(new MAPAccountDataLookup(bool), this.rxSignInTaskIdStream, context);
        this.rxMAPAccountDataSaver = new RxMAPAccountDataSaver(new MAPAccountDataSaver(sharedPreferences), this.rxSignInTaskIdStream);
        this.rxSignInTaskExecutor = new RxSignInTaskExecutor(list, this.rxSignInTaskIdStream, new SignInTaskCache(context));
        this.mapAccountManager = new MAPAccountManager(context);
    }

    public boolean isRecognized() {
        return isSSO();
    }

    public RxSignInTaskIdStream signIn(@NonNull Activity activity) {
        Validate.notNull(activity, "activity cannot be null", new Object[0]);
        if (isSSO()) {
            LOG.info("User is SSO. Proceeding with sign in tasks.");
            this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SSO_SIGN_IN_START);
            return executeSignInTasksWithoutDialog();
        }
        return executeSignInTasksWithDialog(activity);
    }

    public int getCountOfRemainingTasksToExecute() {
        return this.rxSignInTaskExecutor.getCountOfRemainingTasksToExecute();
    }

    public RxSignInTaskIdStream executeSignInTasksWithoutDialog() {
        LOG.info("Starting sign in tasks without dialog.");
        rxGetSSOAccountId().map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSignInTaskExecutor.executeTasks()).map(this.finishFlow).subscribeOn(Schedulers.newThread()).subscribe(this.internalOnNext, this.internalOnError, this.internalOnCompleted);
        return this.rxSignInTaskIdStream;
    }

    private RxSignInTaskIdStream executeSignInTasksWithDialog(Activity activity) {
        this.rxSignInWithDialog.signInWithDialog(activity).map(this.rxSignInWithDialog.emitTaskId()).map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSetHasShownSSOWelcome).map(this.rxSignInTaskExecutor.executeTasks()).map(this.finishFlow).subscribeOn(Schedulers.newThread()).subscribe(this.internalOnNext, this.internalOnError, this.internalOnCompleted);
        return this.rxSignInTaskIdStream;
    }

    public RxSignInTaskIdStream signInWithDifferentAccountThanSSOAccount(Activity activity) {
        LOG.info("Starting user sign in with a secondary account.");
        this.rxSignInWithDialog.signInWithDialog(activity).map(this.rxSignInWithDialog.emitTaskId()).map(this.rxSignInWithDialog.setAccountAsDefaultForThisApp()).map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSetHasShownSSOWelcome).map(this.rxSignInTaskExecutor.executeTasks()).map(this.finishFlow).subscribeOn(Schedulers.newThread()).subscribe(this.internalOnNext, this.internalOnError, this.internalOnCompleted);
        return this.rxSignInTaskIdStream;
    }

    public RxSignInTaskIdStream signInWithRecoverAccountFlow(@NonNull Activity activity) {
        LOG.info("Starting user sign in with a secondary account.");
        this.rxSignInTaskIdStream.sendTaskId(SignInTaskId.SIGN_IN_WITH_RECOVER_ACCOUNT_START);
        this.rxSignInWithDialog.signInWithRecoverAccount(activity).map(this.rxSignInWithDialog.emitRecoverAccountTaskId()).map(this.rxSignInWithDialog.setAccountAsDefaultForThisApp()).map(this.rxMAPAccountDataLookup.getAccountDataFromMAP()).map(this.rxMAPAccountDataSaver.saveAccountDataResponse()).map(this.rxSetHasShownSSOWelcome).map(this.rxSignInTaskExecutor.executeTasks()).map(this.finishFlow).subscribeOn(Schedulers.newThread()).subscribe(this.internalOnNext, this.internalOnError, this.internalOnCompleted);
        return this.rxSignInTaskIdStream;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAccountId() {
        return this.mapAccountManager.getAccount();
    }

    private boolean isSSO() {
        return !TextUtils.isEmpty(getAccountId());
    }

    private Observable<MAPAccountIdResponse> rxGetSSOAccountId() {
        return Observable.create(new Observable.OnSubscribe<MAPAccountIdResponse>() { // from class: com.amazon.music.signin.RxSignInFlow.1
            @Override // rx.functions.Action1
            public void call(Subscriber<? super MAPAccountIdResponse> subscriber) {
                subscriber.onNext(new MAPAccountIdResponse(RxSignInFlow.this.getAccountId()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHasShownSSOWelcome() {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        editorEdit.putBoolean("sso_acceptance_required", true);
        editorEdit.apply();
    }
}
