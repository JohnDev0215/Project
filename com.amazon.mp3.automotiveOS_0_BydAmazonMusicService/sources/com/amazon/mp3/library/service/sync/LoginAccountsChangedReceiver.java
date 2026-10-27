package com.amazon.mp3.library.service.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.map.PostAccountDeregister;
import com.amazon.mp3.account.signintasks.SignInFlowTasks;
import com.amazon.mp3.account.signintasks.SignInTaskStream;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.signin.RxSignInFlow;
import com.amazon.music.signin.SignInTaskId;
import rx.functions.Action0;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class LoginAccountsChangedReceiver extends BroadcastReceiver {
    private static final String TAG = LoginAccountsChangedReceiver.class.getSimpleName();
    private Action1<SignInTaskId> onNext = new Action1<SignInTaskId>() { // from class: com.amazon.mp3.library.service.sync.LoginAccountsChangedReceiver.1
        @Override // rx.functions.Action1
        public void call(SignInTaskId signInTaskId) {
        }
    };
    private Action1<Throwable> onError = new Action1<Throwable>() { // from class: com.amazon.mp3.library.service.sync.LoginAccountsChangedReceiver.2
        @Override // rx.functions.Action1
        public void call(Throwable throwable) {
            Log.debug(LoginAccountsChangedReceiver.TAG, "Error in executeSignInTasksWithoutDialog");
        }
    };
    private Action0 onCompleted = new Action0() { // from class: com.amazon.mp3.library.service.sync.LoginAccountsChangedReceiver.3
        @Override // rx.functions.Action0
        public void call() {
            Log.debug(LoginAccountsChangedReceiver.TAG, "executeSignInTasksWithoutDialog success");
            LoginAccountsChangedReceiver.kickSync(AmazonApplication.getContext());
        }
    };

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, final Intent intent) {
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.library.service.sync.LoginAccountsChangedReceiver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onReceive$0(context, intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onReceiveAsync, reason: merged with bridge method [inline-methods] */
    public void lambda$onReceive$0(final Context context, final Intent intent) {
        boolean zEquals;
        String action = intent.getAction();
        boolean zEquals2 = action.equals("com.amazon.dcp.sso.action.account.added");
        if (action.equals("com.amazon.dcp.sso.action.account.removed")) {
            String stringExtra = intent.getStringExtra(MAPAccountManager.KEY_EXTRA_DIRECTED_ID);
            String accountId = AccountCredentialStorage.get(context).getAccountId();
            if (TextUtils.isEmpty(accountId)) {
                accountId = AccountCredentialStorage.get(context).getLastAccountId();
            }
            zEquals = accountId.equals(stringExtra);
        } else {
            zEquals = false;
        }
        if (zEquals) {
            handleAccountRemoved(context);
        }
        if (zEquals2 && AccountCredentialUtil.get(context).isSignedOut()) {
            handleAccountAdded(context);
        }
    }

    private void handleAccountRemoved(final Context context) {
        Log.verbose(TAG, "account removed");
        new PostAccountDeregister().onSignOut(context);
    }

    private void handleAccountAdded(final Context context) {
        if (SignInTaskStream.get().noSignInFlowIsInProgress()) {
            new RxSignInFlow(context, AccountCredentialStorage.get(context).getCredentialPrefs(), new SignInFlowTasks(context).getSignInTasks(), Boolean.valueOf(PlatformUtil.isFireOS())).executeSignInTasksWithoutDialog().toObservable().subscribe(this.onNext, this.onError, this.onCompleted);
        }
        AmazonApplication.getAuthInfoMAPR5().resetSessionId();
    }

    public static void kickSync(Context context) {
        if (AccountDetailUtil.get(context).canSyncRemoteContent()) {
            SyncService.startSync(context, 13995);
        }
    }
}
