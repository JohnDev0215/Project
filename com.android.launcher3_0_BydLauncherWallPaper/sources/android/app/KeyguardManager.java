package android.app;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.INotificationManager;
import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.app.trust.ITrustManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.service.persistentdata.IPersistentDataBlockService;
import android.util.Log;
import android.view.IOnKeyguardExitResult;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class KeyguardManager {
    public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL = "android.app.action.CONFIRM_DEVICE_CREDENTIAL";
    public static final String ACTION_CONFIRM_DEVICE_CREDENTIAL_WITH_USER = "android.app.action.CONFIRM_DEVICE_CREDENTIAL_WITH_USER";
    public static final String ACTION_CONFIRM_FRP_CREDENTIAL = "android.app.action.CONFIRM_FRP_CREDENTIAL";
    public static final String EXTRA_ALTERNATE_BUTTON_LABEL = "android.app.extra.ALTERNATE_BUTTON_LABEL";
    public static final String EXTRA_DESCRIPTION = "android.app.extra.DESCRIPTION";
    public static final String EXTRA_DISALLOW_BIOMETRICS_IF_POLICY_EXISTS = "check_dpm";
    public static final String EXTRA_TITLE = "android.app.extra.TITLE";
    public static final int RESULT_ALTERNATE = 1;
    private static final String TAG = "KeyguardManager";
    private final Context mContext;
    private final IWindowManager mWM = WindowManagerGlobal.getWindowManagerService();
    private final IActivityManager mAm = ActivityManager.getService();
    private final ITrustManager mTrustManager = ITrustManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.TRUST_SERVICE));
    private final INotificationManager mNotificationManager = INotificationManager.Stub.asInterface(ServiceManager.getServiceOrThrow("notification"));

    public static abstract class KeyguardDismissCallback {
        public void onDismissCancelled() {
        }

        public void onDismissError() {
        }

        public void onDismissSucceeded() {
        }
    }

    @interface LockTypes {
        public static final int PASSWORD = 0;
        public static final int PATTERN = 2;
        public static final int PIN = 1;
    }

    @Deprecated
    public interface OnKeyguardExitResult {
        void onKeyguardExitResult(boolean z);
    }

    @Deprecated
    public Intent createConfirmDeviceCredentialIntent(CharSequence charSequence, CharSequence charSequence2) {
        if (!isDeviceSecure()) {
            return null;
        }
        Intent intent = new Intent(ACTION_CONFIRM_DEVICE_CREDENTIAL);
        intent.putExtra(EXTRA_TITLE, charSequence);
        intent.putExtra(EXTRA_DESCRIPTION, charSequence2);
        intent.setPackage(getSettingsPackageForIntent(intent));
        return intent;
    }

    public Intent createConfirmDeviceCredentialIntent(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (!isDeviceSecure(i)) {
            return null;
        }
        Intent intent = new Intent(ACTION_CONFIRM_DEVICE_CREDENTIAL_WITH_USER);
        intent.putExtra(EXTRA_TITLE, charSequence);
        intent.putExtra(EXTRA_DESCRIPTION, charSequence2);
        intent.putExtra(Intent.EXTRA_USER_ID, i);
        intent.setPackage(getSettingsPackageForIntent(intent));
        return intent;
    }

    public Intent createConfirmDeviceCredentialIntent(CharSequence charSequence, CharSequence charSequence2, int i, boolean z) {
        Intent intentCreateConfirmDeviceCredentialIntent = createConfirmDeviceCredentialIntent(charSequence, charSequence2, i);
        intentCreateConfirmDeviceCredentialIntent.putExtra("check_dpm", z);
        return intentCreateConfirmDeviceCredentialIntent;
    }

    @SystemApi
    public Intent createConfirmFactoryResetCredentialIntent(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        if (!LockPatternUtils.frpCredentialEnabled(this.mContext)) {
            Log.w(TAG, "Factory reset credentials not supported.");
            throw new UnsupportedOperationException("not supported on this device");
        }
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0) != 0) {
            Log.e(TAG, "Factory reset credential cannot be verified after provisioning.");
            throw new IllegalStateException("must not be provisioned yet");
        }
        try {
            IPersistentDataBlockService iPersistentDataBlockServiceAsInterface = IPersistentDataBlockService.Stub.asInterface(ServiceManager.getService(Context.PERSISTENT_DATA_BLOCK_SERVICE));
            if (iPersistentDataBlockServiceAsInterface == null) {
                Log.e(TAG, "No persistent data block service");
                throw new UnsupportedOperationException("not supported on this device");
            }
            if (!iPersistentDataBlockServiceAsInterface.hasFrpCredentialHandle()) {
                Log.i(TAG, "The persistent data block does not have a factory reset credential.");
                return null;
            }
            Intent intent = new Intent(ACTION_CONFIRM_FRP_CREDENTIAL);
            intent.putExtra(EXTRA_TITLE, charSequence);
            intent.putExtra(EXTRA_DESCRIPTION, charSequence2);
            intent.putExtra(EXTRA_ALTERNATE_BUTTON_LABEL, charSequence3);
            intent.setPackage(getSettingsPackageForIntent(intent));
            return intent;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setPrivateNotificationsAllowed(boolean z) {
        try {
            this.mNotificationManager.setPrivateNotificationsAllowed(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean getPrivateNotificationsAllowed() {
        try {
            return this.mNotificationManager.getPrivateNotificationsAllowed();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private String getSettingsPackageForIntent(Intent intent) {
        List<ResolveInfo> listQueryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(intent, 1048576);
        return listQueryIntentActivities.size() > 0 ? listQueryIntentActivities.get(0).activityInfo.packageName : "com.android.settings";
    }

    @Deprecated
    public class KeyguardLock {
        private final String mTag;
        private final IBinder mToken = new Binder();

        KeyguardLock(String str) {
            this.mTag = str;
        }

        public void disableKeyguard() {
            try {
                KeyguardManager.this.mWM.disableKeyguard(this.mToken, this.mTag, KeyguardManager.this.mContext.getUserId());
            } catch (RemoteException unused) {
            }
        }

        public void reenableKeyguard() {
            try {
                KeyguardManager.this.mWM.reenableKeyguard(this.mToken, KeyguardManager.this.mContext.getUserId());
            } catch (RemoteException unused) {
            }
        }
    }

    KeyguardManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this.mContext = context;
    }

    @Deprecated
    public KeyguardLock newKeyguardLock(String str) {
        return new KeyguardLock(str);
    }

    public boolean isKeyguardLocked() {
        try {
            return this.mWM.isKeyguardLocked();
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isKeyguardSecure() {
        try {
            return this.mWM.isKeyguardSecure(this.mContext.getUserId());
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean inKeyguardRestrictedInputMode() {
        return isKeyguardLocked();
    }

    public boolean isDeviceLocked() {
        return isDeviceLocked(this.mContext.getUserId());
    }

    public boolean isDeviceLocked(int i) {
        try {
            return this.mTrustManager.isDeviceLocked(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isDeviceSecure() {
        return isDeviceSecure(this.mContext.getUserId());
    }

    public boolean isDeviceSecure(int i) {
        try {
            return this.mTrustManager.isDeviceSecure(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void requestDismissKeyguard(Activity activity, KeyguardDismissCallback keyguardDismissCallback) {
        requestDismissKeyguard(activity, null, keyguardDismissCallback);
    }

    @SystemApi
    public void requestDismissKeyguard(final Activity activity, CharSequence charSequence, final KeyguardDismissCallback keyguardDismissCallback) {
        try {
            ActivityTaskManager.getService().dismissKeyguard(activity.getActivityToken(), new IKeyguardDismissCallback.Stub() { // from class: android.app.KeyguardManager.1
                @Override // com.android.internal.policy.IKeyguardDismissCallback
                public void onDismissError() throws RemoteException {
                    if (keyguardDismissCallback == null || activity.isDestroyed()) {
                        return;
                    }
                    Handler handler = activity.mHandler;
                    final KeyguardDismissCallback keyguardDismissCallback2 = keyguardDismissCallback;
                    Objects.requireNonNull(keyguardDismissCallback2);
                    handler.post(new Runnable() { // from class: android.app.-$$Lambda$YjHGAHK98Qc4lxjDqgzDyNHJK9c
                        @Override // java.lang.Runnable
                        public final void run() {
                            keyguardDismissCallback2.onDismissError();
                        }
                    });
                }

                @Override // com.android.internal.policy.IKeyguardDismissCallback
                public void onDismissSucceeded() throws RemoteException {
                    if (keyguardDismissCallback == null || activity.isDestroyed()) {
                        return;
                    }
                    Handler handler = activity.mHandler;
                    final KeyguardDismissCallback keyguardDismissCallback2 = keyguardDismissCallback;
                    Objects.requireNonNull(keyguardDismissCallback2);
                    handler.post(new Runnable() { // from class: android.app.-$$Lambda$7tasUTdL-HVt2hQx8Uo3X0Uv3KI
                        @Override // java.lang.Runnable
                        public final void run() {
                            keyguardDismissCallback2.onDismissSucceeded();
                        }
                    });
                }

                @Override // com.android.internal.policy.IKeyguardDismissCallback
                public void onDismissCancelled() throws RemoteException {
                    if (keyguardDismissCallback == null || activity.isDestroyed()) {
                        return;
                    }
                    Handler handler = activity.mHandler;
                    final KeyguardDismissCallback keyguardDismissCallback2 = keyguardDismissCallback;
                    Objects.requireNonNull(keyguardDismissCallback2);
                    handler.post(new Runnable() { // from class: android.app.-$$Lambda$c_8fa1Y4x0ztoIFIbjAFS2jDxrk
                        @Override // java.lang.Runnable
                        public final void run() {
                            keyguardDismissCallback2.onDismissCancelled();
                        }
                    });
                }
            }, charSequence);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void exitKeyguardSecurely(final OnKeyguardExitResult onKeyguardExitResult) {
        try {
            this.mWM.exitKeyguardSecurely(new IOnKeyguardExitResult.Stub() { // from class: android.app.KeyguardManager.2
                @Override // android.view.IOnKeyguardExitResult
                public void onKeyguardExitResult(boolean z) throws RemoteException {
                    OnKeyguardExitResult onKeyguardExitResult2 = onKeyguardExitResult;
                    if (onKeyguardExitResult2 != null) {
                        onKeyguardExitResult2.onKeyguardExitResult(z);
                    }
                }
            });
        } catch (RemoteException unused) {
        }
    }

    private boolean checkInitialLockMethodUsage() {
        if (this.mContext.checkCallingOrSelfPermission(Manifest.permission.SET_INITIAL_LOCK) == 0) {
            return this.mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_AUTOMOTIVE);
        }
        throw new SecurityException("Requires SET_INITIAL_LOCK permission.");
    }

    @SystemApi
    public boolean isValidLockPasswordComplexity(int i, byte[] bArr, int i2) {
        if (!checkInitialLockMethodUsage()) {
            return false;
        }
        return PasswordMetrics.validatePassword(((DevicePolicyManager) this.mContext.getSystemService(Context.DEVICE_POLICY_SERVICE)).getPasswordMinimumMetrics(this.mContext.getUserId()), PasswordMetrics.sanitizeComplexityLevel(i2), i != 0, bArr).size() == 0;
    }

    @SystemApi
    public int getMinLockLength(boolean z, int i) {
        if (!checkInitialLockMethodUsage()) {
            return -1;
        }
        return PasswordMetrics.applyComplexity(((DevicePolicyManager) this.mContext.getSystemService(Context.DEVICE_POLICY_SERVICE)).getPasswordMinimumMetrics(this.mContext.getUserId()), z, PasswordMetrics.sanitizeComplexityLevel(i)).length;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v4 */
    @SystemApi
    public boolean setLock(int i, byte[] bArr, int i2) {
        byte b = 0;
        b = 0;
        if (!checkInitialLockMethodUsage()) {
            return false;
        }
        LockPatternUtils lockPatternUtils = new LockPatternUtils(this.mContext);
        int userId = this.mContext.getUserId();
        if (isDeviceSecure(userId)) {
            Log.e(TAG, "Password already set, rejecting call to setLock");
            return false;
        }
        if (!isValidLockPasswordComplexity(i, bArr, i2)) {
            Log.e(TAG, "Password is not valid, rejecting call to setLock");
            return false;
        }
        boolean z = true;
        try {
            try {
                if (i == 0) {
                    lockPatternUtils.setLockCredential(LockscreenCredential.createPassword(new String(bArr, Charset.forName("UTF-8"))), LockscreenCredential.createNone(), userId);
                } else if (i == 1) {
                    lockPatternUtils.setLockCredential(LockscreenCredential.createPin(new String(bArr)), LockscreenCredential.createNone(), userId);
                } else if (i == 2) {
                    List<LockPatternView.Cell> listByteArrayToPattern = LockPatternUtils.byteArrayToPattern(bArr);
                    lockPatternUtils.setLockCredential(LockscreenCredential.createPattern(listByteArrayToPattern), LockscreenCredential.createNone(), userId);
                    listByteArrayToPattern.clear();
                } else {
                    Log.e(TAG, "Unknown lock type, returning a failure");
                    z = false;
                }
                Arrays.fill(bArr, (byte) 0);
                b = z;
                return b;
            } catch (Exception e) {
                Log.e(TAG, "Save lock exception", e);
                Arrays.fill(bArr, b == true ? (byte) 1 : (byte) 0);
                return b;
            }
        } catch (Throwable th) {
            Arrays.fill(bArr, b);
            throw th;
        }
    }
}
