package com.android.car.settings.security;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.security.KeyChain;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.Logger;
import com.android.car.ui.utils.RotaryConstants;
import com.android.internal.widget.LockPatternUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public class CredentialStorageActivity extends FragmentActivity {
    private static final String ACTION_INSTALL = "com.android.credentials.INSTALL";
    private static final String ACTION_RESET = "com.android.credentials.RESET";
    private static final String CERT_INSTALLER_PKG = "com.android.certinstaller";
    private static final int CONFIRM_CLEAR_SYSTEM_CREDENTIAL_REQUEST = 1;
    private static final String DIALOG_TAG = "com.android.car.settings.security.RESET_CREDENTIALS";
    private static final Logger LOG = new Logger((Class<?>) CredentialStorageActivity.class);
    private UserManager mUserManager;
    private LockPatternUtils mUtils;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mUserManager = UserManager.get(getApplicationContext());
        this.mUtils = new LockPatternUtils(this);
        if (this.mUserManager.hasUserRestriction("no_config_credentials")) {
            finish();
            return;
        }
        Intent intent = getIntent();
        String action = intent.getAction();
        if (ACTION_RESET.equals(action) && checkCallerIsSelf()) {
            showResetConfirmationDialog();
            return;
        }
        if (ACTION_INSTALL.equals(action) && checkCallerIsCertInstallerOrSelfInProfile()) {
            Bundle installBundle = intent.getExtras();
            boolean allTasksComplete = installIfAvailable(installBundle);
            if (allTasksComplete) {
                finish();
            }
        }
    }

    private void showResetConfirmationDialog() {
        ConfirmationDialogFragment dialog = new ConfirmationDialogFragment.Builder(this).setTitle(R.string.credentials_reset).setMessage(R.string.credentials_reset_hint).setPositiveButton(android.R.string.ok, new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.security.-$$Lambda$CredentialStorageActivity$WzFKej3MRRa959QbTtBMB5OOBqk
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$showResetConfirmationDialog$0$CredentialStorageActivity(bundle);
            }
        }).setNegativeButton(android.R.string.cancel, new ConfirmationDialogFragment.RejectListener() { // from class: com.android.car.settings.security.-$$Lambda$CredentialStorageActivity$hTNIwby7VCCurJt6-QKegBxjcs8
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.RejectListener
            public final void onReject(Bundle bundle) {
                this.f$0.lambda$showResetConfirmationDialog$1$CredentialStorageActivity(bundle);
            }
        }).build();
        dialog.show(getSupportFragmentManager(), DIALOG_TAG);
    }

    public /* synthetic */ void lambda$showResetConfirmationDialog$0$CredentialStorageActivity(Bundle arguments) {
        onResetConfirmed();
    }

    public /* synthetic */ void lambda$showResetConfirmationDialog$1$CredentialStorageActivity(Bundle arguments) {
        finish();
    }

    private void onResetConfirmed() {
        if (!this.mUtils.isSecure(UserHandle.myUserId())) {
            new ResetKeyStoreAndKeyChain(this).execute(new Void[0]);
        } else {
            startActivityForResult(new Intent(this, (Class<?>) CheckLockActivity.class), 1);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == -1) {
                new ResetKeyStoreAndKeyChain(this).execute(new Void[0]);
            } else {
                finish();
            }
        }
    }

    private boolean checkCallerIsSelf() {
        try {
            return Process.myUid() == ActivityManager.getService().getLaunchedFromUid(getActivityToken());
        } catch (RemoteException e) {
            return false;
        }
    }

    private boolean checkCallerIsCertInstallerOrSelfInProfile() {
        if (TextUtils.equals(CERT_INSTALLER_PKG, getCallingPackage())) {
            return getPackageManager().checkSignatures(getCallingPackage(), getPackageName()) == 0;
        }
        try {
            int launchedFromUid = ActivityManager.getService().getLaunchedFromUid(getActivityToken());
            if (launchedFromUid == -1) {
                LOG.e("com.android.credentials.INSTALL must be started with startActivityForResult");
                return false;
            }
            if (!UserHandle.isSameApp(launchedFromUid, Process.myUid())) {
                return false;
            }
            int launchedFromUserId = UserHandle.getUserId(launchedFromUid);
            UserManager userManager = (UserManager) getSystemService("user");
            UserInfo parentInfo = userManager.getProfileParent(launchedFromUserId);
            return parentInfo != null && parentInfo.id == UserHandle.myUserId();
        } catch (RemoteException e) {
            LOG.w("Unable to verify calling identity. ActivityManager is down.", e);
            return false;
        }
    }

    private boolean installIfAvailable(Bundle installBundle) {
        if (installBundle == null || installBundle.isEmpty()) {
            return true;
        }
        int uid = installBundle.getInt("install_as_uid", -1);
        if (uid != -1 && !UserHandle.isSameUser(uid, Process.myUid()) && (!UserManager.isHeadlessSystemUserMode() || UserHandle.getUserId(uid) != 0)) {
            int dstUserId = UserHandle.getUserId(uid);
            if (uid != 1010) {
                LOG.e("Failed to install credentials as uid " + uid + ": cross-user installs may only target wifi uids");
                return true;
            }
            Intent installIntent = new Intent(ACTION_INSTALL).setFlags(RotaryConstants.ACTION_NUDGE_TO_ANOTHER_FOCUS_AREA).putExtras(installBundle);
            startActivityAsUser(installIntent, new UserHandle(dstUserId));
            return true;
        }
        String alias = installBundle.getString("user_key_pair_name", null);
        if (TextUtils.isEmpty(alias)) {
            LOG.e("Cannot install key without an alias");
            return true;
        }
        byte[] privateKeyData = installBundle.getByteArray("user_private_key_data");
        byte[] certData = installBundle.getByteArray("user_certificate_data");
        byte[] caListData = installBundle.getByteArray("ca_certificates_data");
        new InstallKeyInKeyChain(alias, privateKeyData, certData, caListData, uid).execute(new Void[0]);
        return false;
    }

    private static class ResetKeyStoreAndKeyChain extends AsyncTask<Void, Void, Boolean> {
        private final WeakReference<CredentialStorageActivity> mCredentialStorage;

        ResetKeyStoreAndKeyChain(CredentialStorageActivity credentialStorage) {
            this.mCredentialStorage = new WeakReference<>(credentialStorage);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... unused) {
            CredentialStorageActivity credentialStorage = this.mCredentialStorage.get();
            if (credentialStorage == null || credentialStorage.isFinishing() || credentialStorage.isDestroyed()) {
                return false;
            }
            UserHandle user = CredentialStorageActivity.getUserHandleToUse(this.mCredentialStorage.get().mUserManager);
            credentialStorage.mUtils.resetKeyStore(user.getIdentifier());
            try {
                KeyChain.KeyChainConnection keyChainConnection = KeyChain.bindAsUser(credentialStorage, user);
                try {
                    try {
                        return Boolean.valueOf(keyChainConnection.getService().reset());
                    } finally {
                        keyChainConnection.close();
                    }
                } catch (RemoteException e) {
                    CredentialStorageActivity.LOG.w("Failed to reset KeyChain", e);
                    return false;
                }
            } catch (InterruptedException e2) {
                CredentialStorageActivity.LOG.w("Failed to reset KeyChain", e2);
                Thread.currentThread().interrupt();
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean success) {
            CredentialStorageActivity credentialStorage = this.mCredentialStorage.get();
            if (credentialStorage == null || credentialStorage.isFinishing() || credentialStorage.isDestroyed()) {
                return;
            }
            credentialStorage.finish();
        }
    }

    private class InstallKeyInKeyChain extends AsyncTask<Void, Void, Boolean> {
        final String mAlias;
        private final byte[] mCaListData;
        private final byte[] mCertData;
        private final byte[] mKeyData;
        private final int mUid;

        InstallKeyInKeyChain(String alias, byte[] keyData, byte[] certData, byte[] caListData, int uid) {
            this.mAlias = alias;
            this.mKeyData = keyData;
            this.mCertData = certData;
            this.mCaListData = caListData;
            this.mUid = uid;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... unused) {
            try {
                KeyChain.KeyChainConnection keyChainConnection = KeyChain.bindAsUser(CredentialStorageActivity.this, CredentialStorageActivity.getUserHandleToUse(CredentialStorageActivity.this.mUserManager));
                try {
                    Boolean boolValueOf = Boolean.valueOf(keyChainConnection.getService().installKeyPair(this.mKeyData, this.mCertData, this.mCaListData, this.mAlias, this.mUid));
                    if (keyChainConnection != null) {
                        keyChainConnection.close();
                    }
                    return boolValueOf;
                } catch (Throwable th) {
                    if (keyChainConnection != null) {
                        try {
                            keyChainConnection.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (RemoteException e) {
                CredentialStorageActivity.LOG.w(String.format("Failed to install key %s to uid %d", this.mAlias, Integer.valueOf(this.mUid)), e);
                return false;
            } catch (InterruptedException e2) {
                CredentialStorageActivity.LOG.w(String.format("Interrupted while installing key %s", this.mAlias), e2);
                Thread.currentThread().interrupt();
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean result) {
            CredentialStorageActivity.LOG.i(String.format("Marked alias %s as selectable, success? %s", this.mAlias, result));
            CredentialStorageActivity.this.onKeyInstalled(this.mAlias, this.mUid, result.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onKeyInstalled(String alias, int uid, boolean result) {
        if (!result) {
            LOG.w(String.format("Error installing alias %s for uid %d", alias, Integer.valueOf(uid)));
            finish();
            return;
        }
        Intent broadcast = new Intent("android.security.action.KEYCHAIN_CHANGED");
        sendBroadcast(broadcast);
        setResult(-1);
        if (uid == 1000 || uid == -1) {
            new MarkKeyAsUserSelectable(this, alias).execute(new Void[0]);
        } else {
            finish();
        }
    }

    private static class MarkKeyAsUserSelectable extends AsyncTask<Void, Void, Boolean> {
        private final String mAlias;
        private final WeakReference<CredentialStorageActivity> mCredentialStorage;

        MarkKeyAsUserSelectable(CredentialStorageActivity credentialStorage, String alias) {
            this.mCredentialStorage = new WeakReference<>(credentialStorage);
            this.mAlias = alias;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... unused) {
            CredentialStorageActivity credentialStorage = this.mCredentialStorage.get();
            if (credentialStorage == null || credentialStorage.isFinishing() || credentialStorage.isDestroyed()) {
                return false;
            }
            try {
                KeyChain.KeyChainConnection keyChainConnection = KeyChain.bindAsUser(credentialStorage, CredentialStorageActivity.getUserHandleToUse(credentialStorage.mUserManager));
                try {
                    keyChainConnection.getService().setUserSelectable(this.mAlias, true);
                    if (keyChainConnection != null) {
                        keyChainConnection.close();
                    }
                    return true;
                } catch (Throwable th) {
                    if (keyChainConnection != null) {
                        try {
                            keyChainConnection.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (RemoteException e) {
                CredentialStorageActivity.LOG.w("Failed to mark key " + this.mAlias + " as user-selectable.", e);
                return false;
            } catch (InterruptedException e2) {
                CredentialStorageActivity.LOG.w("Failed to mark key " + this.mAlias + " as user-selectable.", e2);
                Thread.currentThread().interrupt();
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean result) {
            CredentialStorageActivity.LOG.i(String.format("Marked alias %s as selectable, success? %s", this.mAlias, result));
            CredentialStorageActivity credentialStorage = this.mCredentialStorage.get();
            if (credentialStorage == null || credentialStorage.isFinishing() || credentialStorage.isDestroyed()) {
                return;
            }
            credentialStorage.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static UserHandle getUserHandleToUse(UserManager userManager) {
        return UserManager.isHeadlessSystemUserMode() ? UserHandle.SYSTEM : UserHandle.of(ActivityManager.getCurrentUser());
    }
}
