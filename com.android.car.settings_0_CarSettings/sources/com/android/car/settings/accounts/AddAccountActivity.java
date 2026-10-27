package com.android.car.settings.accounts;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import com.android.car.settings.common.Logger;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class AddAccountActivity extends Activity {
    private static final int ADD_ACCOUNT_REQUEST = 2001;
    private static final String ALLOW_SKIP = "allowSkip";
    static final String EXTRA_HAS_MULTIPLE_USERS = "hasMultipleUsers";
    static final String EXTRA_SELECTED_ACCOUNT = "selected_account";
    private static final String KEY_ADD_CALLED = "AddAccountCalled";
    private static final String KEY_CALLER_IDENTITY = "pendingIntent";
    private static final Logger LOG = new Logger((Class<?>) AddAccountActivity.class);
    private static final String SHOULD_NOT_RESOLVE = "SHOULDN'T RESOLVE!";
    private boolean mAddAccountCalled;
    private final AccountManagerCallback<Bundle> mCallback = new AccountManagerCallback<Bundle>() { // from class: com.android.car.settings.accounts.AddAccountActivity.1
        @Override // android.accounts.AccountManagerCallback
        public void run(AccountManagerFuture<Bundle> future) {
            if (!future.isDone()) {
                AddAccountActivity.LOG.v("Account manager future is not done.");
                AddAccountActivity.this.finish();
            }
            boolean done = true;
            try {
                try {
                    Bundle result = future.getResult();
                    Intent intent = (Intent) result.getParcelable("intent");
                    if (intent != null) {
                        done = false;
                        Bundle addAccountOptions = new Bundle();
                        addAccountOptions.putBoolean(AddAccountActivity.EXTRA_HAS_MULTIPLE_USERS, AddAccountActivity.this.hasMultipleUsers(AddAccountActivity.this));
                        addAccountOptions.putParcelable("android.intent.extra.USER", AddAccountActivity.this.mUserHandle);
                        intent.putExtras(addAccountOptions);
                        intent.addFlags(268435456);
                        AddAccountActivity.this.startActivityForResultAsUser(intent, AddAccountActivity.ADD_ACCOUNT_REQUEST, AddAccountActivity.this.mUserHandle);
                    } else {
                        AddAccountActivity.this.setResult(-1);
                        if (AddAccountActivity.this.mPendingIntent != null) {
                            AddAccountActivity.this.mPendingIntent.cancel();
                            AddAccountActivity.this.mPendingIntent = null;
                        }
                    }
                    AddAccountActivity.LOG.v("account added: " + result);
                    if (!done) {
                        return;
                    }
                } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                    AddAccountActivity.LOG.v("addAccount error: " + e);
                    if (!done) {
                        return;
                    }
                }
                AddAccountActivity.this.finish();
            } catch (Throwable th) {
                if (done) {
                    AddAccountActivity.this.finish();
                }
                throw th;
            }
        }
    };
    private PendingIntent mPendingIntent;
    private UserHandle mUserHandle;
    private UserManager mUserManager;

    public static Intent createAddAccountActivityIntent(Context context, String accountType) {
        Intent intent = new Intent(context, (Class<?>) AddAccountActivity.class);
        intent.putExtra(EXTRA_SELECTED_ACCOUNT, accountType);
        return intent;
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ADD_CALLED, this.mAddAccountCalled);
        LOG.v("saved");
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.mAddAccountCalled = savedInstanceState.getBoolean(KEY_ADD_CALLED);
            LOG.v("Restored from previous add account call: " + this.mAddAccountCalled);
        }
        this.mUserManager = UserManager.get(getApplicationContext());
        this.mUserHandle = UserHandle.of(UserHandle.myUserId());
        if (this.mUserManager.hasUserRestriction("no_modify_accounts")) {
            finish();
        } else if (this.mAddAccountCalled) {
            finish();
        } else {
            addAccount(getIntent().getStringExtra(EXTRA_SELECTED_ACCOUNT));
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        setResult(resultCode);
        PendingIntent pendingIntent = this.mPendingIntent;
        if (pendingIntent != null) {
            pendingIntent.cancel();
            this.mPendingIntent = null;
        }
        finish();
    }

    private void addAccount(String accountType) {
        Bundle addAccountOptions = new Bundle();
        addAccountOptions.putBoolean(EXTRA_HAS_MULTIPLE_USERS, hasMultipleUsers(this));
        addAccountOptions.putBoolean(ALLOW_SKIP, true);
        Intent identityIntent = new Intent();
        identityIntent.setComponent(new ComponentName(SHOULD_NOT_RESOLVE, SHOULD_NOT_RESOLVE));
        identityIntent.setAction(SHOULD_NOT_RESOLVE);
        identityIntent.addCategory(SHOULD_NOT_RESOLVE);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, identityIntent, 0);
        this.mPendingIntent = broadcast;
        addAccountOptions.putParcelable(KEY_CALLER_IDENTITY, broadcast);
        AccountManager.get(this).addAccountAsUser(accountType, null, null, addAccountOptions, null, this.mCallback, null, this.mUserHandle);
        this.mAddAccountCalled = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasMultipleUsers(Context context) {
        return ((UserManager) context.getSystemService("user")).getUsers().size() > 1;
    }
}
