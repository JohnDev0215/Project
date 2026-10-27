package com.android.internal.widget;

import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.app.trust.IStrongAuthTracker;
import android.app.trust.TrustManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.UserInfo;
import android.media.AudioSystem;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.IStorageManager;
import android.os.storage.StorageManager;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import com.android.internal.R;
import com.android.internal.widget.ICheckCredentialProgressCallback;
import com.android.internal.widget.ILockSettings;
import com.android.internal.widget.LockPatternView;
import com.android.server.LocalServices;
import com.google.android.collect.Lists;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;
import libcore.util.HexEncoding;

/* JADX INFO: loaded from: classes4.dex */
public class LockPatternUtils {

    @Deprecated
    public static final String BIOMETRIC_WEAK_EVER_CHOSEN_KEY = "lockscreen.biometricweakeverchosen";
    public static final int CREDENTIAL_TYPE_NONE = -1;
    public static final int CREDENTIAL_TYPE_PASSWORD = 4;
    public static final int CREDENTIAL_TYPE_PASSWORD_OR_PIN = 2;
    public static final int CREDENTIAL_TYPE_PATTERN = 1;
    public static final int CREDENTIAL_TYPE_PIN = 3;
    public static final String DISABLE_LOCKSCREEN_KEY = "lockscreen.disabled";
    private static final String ENABLED_TRUST_AGENTS = "lockscreen.enabledtrustagents";
    public static final int FAILED_ATTEMPTS_BEFORE_WIPE_GRACE = 5;
    public static final long FAILED_ATTEMPT_COUNTDOWN_INTERVAL_MS = 1000;
    private static final boolean FRP_CREDENTIAL_ENABLED = true;
    private static final String HISTORY_DELIMITER = ",";
    private static final String IS_TRUST_USUALLY_MANAGED = "lockscreen.istrustusuallymanaged";
    public static final String LEGACY_LOCK_PATTERN_ENABLED = "legacy_lock_pattern_enabled";

    @Deprecated
    public static final String LOCKOUT_PERMANENT_KEY = "lockscreen.lockedoutpermanently";

    @Deprecated
    public static final String LOCKSCREEN_BIOMETRIC_WEAK_FALLBACK = "lockscreen.biometric_weak_fallback";
    public static final String LOCKSCREEN_OPTIONS = "lockscreen.options";
    public static final String LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS = "lockscreen.power_button_instantly_locks";

    @Deprecated
    public static final String LOCKSCREEN_WIDGETS_ENABLED = "lockscreen.widgets_enabled";
    public static final String LOCK_PASSWORD_SALT_KEY = "lockscreen.password_salt";
    private static final String LOCK_SCREEN_DEVICE_OWNER_INFO = "lockscreen.device_owner_info";
    private static final String LOCK_SCREEN_OWNER_INFO = "lock_screen_owner_info";
    private static final String LOCK_SCREEN_OWNER_INFO_ENABLED = "lock_screen_owner_info_enabled";
    public static final int MIN_LOCK_PASSWORD_SIZE = 4;
    public static final int MIN_LOCK_PATTERN_SIZE = 4;
    public static final int MIN_PATTERN_REGISTER_FAIL = 4;
    public static final String PASSWORD_HISTORY_KEY = "lockscreen.passwordhistory";

    @Deprecated
    public static final String PASSWORD_TYPE_ALTERNATE_KEY = "lockscreen.password_type_alternate";
    public static final String PASSWORD_TYPE_KEY = "lockscreen.password_type";
    public static final String PATTERN_EVER_CHOSEN_KEY = "lockscreen.patterneverchosen";
    public static final String PROFILE_KEY_NAME_DECRYPT = "profile_key_name_decrypt_";
    public static final String PROFILE_KEY_NAME_ENCRYPT = "profile_key_name_encrypt_";
    public static final int SYNTHETIC_PASSWORD_ENABLED_BY_DEFAULT = 1;
    public static final String SYNTHETIC_PASSWORD_ENABLED_KEY = "enable-sp";
    public static final String SYNTHETIC_PASSWORD_HANDLE_KEY = "sp-handle";
    public static final String SYNTHETIC_PASSWORD_KEY_PREFIX = "synthetic_password_";
    private static final String TAG = "LockPatternUtils";
    public static final int USER_FRP = -9999;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private DevicePolicyManager mDevicePolicyManager;
    private final Handler mHandler;
    private Boolean mHasSecureLockScreen;
    private ILockSettings mLockSettingsService;
    private final SparseLongArray mLockoutDeadlines = new SparseLongArray();
    private UserManager mUserManager;

    public interface CheckCredentialProgressCallback {
        void onEarlyMatched();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CredentialType {
    }

    public interface EscrowTokenStateChangeCallback {
        void onEscrowTokenActivated(long j, int i);
    }

    public static boolean isQualityAlphabeticPassword(int i) {
        return i >= 262144;
    }

    public static boolean isQualityNumericPin(int i) {
        return i == 131072 || i == 196608;
    }

    public boolean isTrustUsuallyManaged(int i) {
        if (!(this.mLockSettingsService instanceof ILockSettings.Stub)) {
            throw new IllegalStateException("May only be called by TrustManagerService. Use TrustManager.isTrustUsuallyManaged()");
        }
        try {
            return getLockSettings().getBoolean(IS_TRUST_USUALLY_MANAGED, false, i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void setTrustUsuallyManaged(boolean z, int i) {
        try {
            getLockSettings().setBoolean(IS_TRUST_USUALLY_MANAGED, z, i);
        } catch (RemoteException unused) {
        }
    }

    public void userPresent(int i) {
        try {
            getLockSettings().userPresent(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static final class RequestThrottledException extends Exception {
        private int mTimeoutMs;

        public RequestThrottledException(int i) {
            this.mTimeoutMs = i;
        }

        public int getTimeoutMs() {
            return this.mTimeoutMs;
        }
    }

    public DevicePolicyManager getDevicePolicyManager() {
        if (this.mDevicePolicyManager == null) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
            this.mDevicePolicyManager = devicePolicyManager;
            if (devicePolicyManager == null) {
                Log.e(TAG, "Can't get DevicePolicyManagerService: is it running?", new IllegalStateException("Stack trace:"));
            }
        }
        return this.mDevicePolicyManager;
    }

    private UserManager getUserManager() {
        if (this.mUserManager == null) {
            this.mUserManager = UserManager.get(this.mContext);
        }
        return this.mUserManager;
    }

    private TrustManager getTrustManager() {
        TrustManager trustManager = (TrustManager) this.mContext.getSystemService(Context.TRUST_SERVICE);
        if (trustManager == null) {
            Log.e(TAG, "Can't get TrustManagerService: is it running?", new IllegalStateException("Stack trace:"));
        }
        return trustManager;
    }

    public LockPatternUtils(Context context) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        Looper looperMyLooper = Looper.myLooper();
        this.mHandler = looperMyLooper != null ? new Handler(looperMyLooper) : null;
    }

    public ILockSettings getLockSettings() {
        if (this.mLockSettingsService == null) {
            this.mLockSettingsService = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));
        }
        return this.mLockSettingsService;
    }

    public int getRequestedMinimumPasswordLength(int i) {
        return getDevicePolicyManager().getPasswordMinimumLength(null, i);
    }

    public int getMaximumPasswordLength(int i) {
        return getDevicePolicyManager().getPasswordMaximumLength(i);
    }

    public PasswordMetrics getRequestedPasswordMetrics(int i) {
        return getDevicePolicyManager().getPasswordMinimumMetrics(i);
    }

    public int getRequestedPasswordQuality(int i) {
        return getDevicePolicyManager().getPasswordQuality(null, i);
    }

    private int getRequestedPasswordHistoryLength(int i) {
        return getDevicePolicyManager().getPasswordHistoryLength(null, i);
    }

    public int getRequestedPasswordMinimumLetters(int i) {
        return getDevicePolicyManager().getPasswordMinimumLetters(null, i);
    }

    public int getRequestedPasswordMinimumUpperCase(int i) {
        return getDevicePolicyManager().getPasswordMinimumUpperCase(null, i);
    }

    public int getRequestedPasswordMinimumLowerCase(int i) {
        return getDevicePolicyManager().getPasswordMinimumLowerCase(null, i);
    }

    public int getRequestedPasswordMinimumNumeric(int i) {
        return getDevicePolicyManager().getPasswordMinimumNumeric(null, i);
    }

    public int getRequestedPasswordMinimumSymbols(int i) {
        return getDevicePolicyManager().getPasswordMinimumSymbols(null, i);
    }

    public int getRequestedPasswordMinimumNonLetter(int i) {
        return getDevicePolicyManager().getPasswordMinimumNonLetter(null, i);
    }

    public void reportFailedPasswordAttempt(int i) {
        if (i == -9999 && frpCredentialEnabled(this.mContext)) {
            return;
        }
        getDevicePolicyManager().reportFailedPasswordAttempt(i);
        getTrustManager().reportUnlockAttempt(false, i);
    }

    public void reportSuccessfulPasswordAttempt(int i) {
        if (i == -9999 && frpCredentialEnabled(this.mContext)) {
            return;
        }
        getDevicePolicyManager().reportSuccessfulPasswordAttempt(i);
        getTrustManager().reportUnlockAttempt(true, i);
    }

    public void reportPasswordLockout(int i, int i2) {
        if (i2 == -9999 && frpCredentialEnabled(this.mContext)) {
            return;
        }
        getTrustManager().reportUnlockLockout(i, i2);
    }

    public int getCurrentFailedPasswordAttempts(int i) {
        if (i == -9999 && frpCredentialEnabled(this.mContext)) {
            return 0;
        }
        return getDevicePolicyManager().getCurrentFailedPasswordAttempts(i);
    }

    public int getMaximumFailedPasswordsForWipe(int i) {
        if (i == -9999 && frpCredentialEnabled(this.mContext)) {
            return 0;
        }
        return getDevicePolicyManager().getMaximumFailedPasswordsForWipe(null, i);
    }

    public byte[] verifyCredential(LockscreenCredential lockscreenCredential, long j, int i) throws RequestThrottledException {
        throwIfCalledOnMainThread();
        try {
            VerifyCredentialResponse verifyCredentialResponseVerifyCredential = getLockSettings().verifyCredential(lockscreenCredential, j, i);
            if (verifyCredentialResponseVerifyCredential.getResponseCode() == 0) {
                return verifyCredentialResponseVerifyCredential.getPayload();
            }
            if (verifyCredentialResponseVerifyCredential.getResponseCode() != 1) {
                return null;
            }
            throw new RequestThrottledException(verifyCredentialResponseVerifyCredential.getTimeout());
        } catch (RemoteException e) {
            Log.e(TAG, "failed to verify credential", e);
            return null;
        }
    }

    public boolean checkCredential(LockscreenCredential lockscreenCredential, int i, CheckCredentialProgressCallback checkCredentialProgressCallback) throws RequestThrottledException {
        throwIfCalledOnMainThread();
        try {
            VerifyCredentialResponse verifyCredentialResponseCheckCredential = getLockSettings().checkCredential(lockscreenCredential, i, wrapCallback(checkCredentialProgressCallback));
            if (verifyCredentialResponseCheckCredential.getResponseCode() == 0) {
                return true;
            }
            if (verifyCredentialResponseCheckCredential.getResponseCode() != 1) {
                return false;
            }
            throw new RequestThrottledException(verifyCredentialResponseCheckCredential.getTimeout());
        } catch (RemoteException e) {
            Log.e(TAG, "failed to check credential", e);
            return false;
        }
    }

    public byte[] verifyTiedProfileChallenge(LockscreenCredential lockscreenCredential, long j, int i) throws RequestThrottledException {
        throwIfCalledOnMainThread();
        try {
            VerifyCredentialResponse verifyCredentialResponseVerifyTiedProfileChallenge = getLockSettings().verifyTiedProfileChallenge(lockscreenCredential, j, i);
            if (verifyCredentialResponseVerifyTiedProfileChallenge.getResponseCode() == 0) {
                return verifyCredentialResponseVerifyTiedProfileChallenge.getPayload();
            }
            if (verifyCredentialResponseVerifyTiedProfileChallenge.getResponseCode() != 1) {
                return null;
            }
            throw new RequestThrottledException(verifyCredentialResponseVerifyTiedProfileChallenge.getTimeout());
        } catch (RemoteException e) {
            Log.e(TAG, "failed to verify tied profile credential", e);
            return null;
        }
    }

    public boolean checkVoldPassword(int i) {
        try {
            return getLockSettings().checkVoldPassword(i);
        } catch (RemoteException e) {
            Log.e(TAG, "failed to check vold password", e);
            return false;
        }
    }

    public byte[] getPasswordHistoryHashFactor(LockscreenCredential lockscreenCredential, int i) {
        try {
            return getLockSettings().getHashFactor(lockscreenCredential, i);
        } catch (RemoteException e) {
            Log.e(TAG, "failed to get hash factor", e);
            return null;
        }
    }

    public boolean checkPasswordHistory(byte[] bArr, byte[] bArr2, int i) {
        int requestedPasswordHistoryLength;
        if (bArr == null || bArr.length == 0) {
            Log.e(TAG, "checkPasswordHistory: empty password");
            return false;
        }
        String string = getString(PASSWORD_HISTORY_KEY, i);
        if (TextUtils.isEmpty(string) || (requestedPasswordHistoryLength = getRequestedPasswordHistoryLength(i)) == 0) {
            return false;
        }
        String strLegacyPasswordToHash = legacyPasswordToHash(bArr, i);
        String strPasswordToHistoryHash = passwordToHistoryHash(bArr, bArr2, i);
        String[] strArrSplit = string.split(",");
        for (int i2 = 0; i2 < Math.min(requestedPasswordHistoryLength, strArrSplit.length); i2++) {
            if (strArrSplit[i2].equals(strLegacyPasswordToHash) || strArrSplit[i2].equals(strPasswordToHistoryHash)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPatternEverChosen(int i) {
        return getBoolean(PATTERN_EVER_CHOSEN_KEY, false, i);
    }

    public void reportPatternWasChosen(int i) {
        setBoolean(PATTERN_EVER_CHOSEN_KEY, true, i);
    }

    public int getActivePasswordQuality(int i) {
        return getKeyguardStoredPasswordQuality(i);
    }

    public void resetKeyStore(int i) {
        try {
            getLockSettings().resetKeyStore(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't reset keystore " + e);
        }
    }

    public void setLockScreenDisabled(boolean z, int i) {
        setBoolean("lockscreen.disabled", z, i);
    }

    public boolean isLockScreenDisabled(int i) {
        if (isSecure(i)) {
            return false;
        }
        boolean z = this.mContext.getResources().getBoolean(R.bool.config_disableLockscreenByDefault);
        boolean z2 = UserManager.isSplitSystemUser() && i == 0;
        UserInfo userInfo = getUserManager().getUserInfo(i);
        return getBoolean("lockscreen.disabled", false, i) || (z && !z2) || (UserManager.isDeviceInDemoMode(this.mContext) && userInfo != null && userInfo.isDemo());
    }

    public static int credentialTypeToPasswordQuality(int i) {
        if (i == -1) {
            return 0;
        }
        if (i == 1) {
            return 65536;
        }
        if (i == 3) {
            return 131072;
        }
        if (i == 4) {
            return 262144;
        }
        throw new IllegalStateException("Unknown type: " + i);
    }

    public boolean setLockCredential(LockscreenCredential lockscreenCredential, LockscreenCredential lockscreenCredential2, int i) {
        if (!hasSecureLockScreen()) {
            throw new UnsupportedOperationException("This operation requires the lock screen feature.");
        }
        lockscreenCredential.checkLength();
        try {
            if (!getLockSettings().setLockCredential(lockscreenCredential, lockscreenCredential2, i)) {
                return false;
            }
            onPostPasswordChanged(lockscreenCredential, i);
            return true;
        } catch (RemoteException e) {
            throw new RuntimeException("Unable to save lock password", e);
        }
    }

    private void onPostPasswordChanged(LockscreenCredential lockscreenCredential, int i) {
        updateEncryptionPasswordIfNeeded(lockscreenCredential, i);
        if (lockscreenCredential.isPattern()) {
            reportPatternWasChosen(i);
        }
        updatePasswordHistory(lockscreenCredential, i);
        reportEnabledTrustAgentsChanged(i);
    }

    private void updateCryptoUserInfo(int i) {
        if (i != 0) {
            return;
        }
        String ownerInfo = isOwnerInfoEnabled(i) ? getOwnerInfo(i) : "";
        IBinder service = ServiceManager.getService("mount");
        if (service == null) {
            Log.e(TAG, "Could not find the mount service to update the user info");
            return;
        }
        IStorageManager iStorageManagerAsInterface = IStorageManager.Stub.asInterface(service);
        try {
            Log.d(TAG, "Setting owner info");
            iStorageManagerAsInterface.setField(StorageManager.OWNER_INFO_KEY, ownerInfo);
        } catch (RemoteException e) {
            Log.e(TAG, "Error changing user info", e);
        }
    }

    public void setOwnerInfo(String str, int i) {
        setString("lock_screen_owner_info", str, i);
        updateCryptoUserInfo(i);
    }

    public void setOwnerInfoEnabled(boolean z, int i) {
        setBoolean("lock_screen_owner_info_enabled", z, i);
        updateCryptoUserInfo(i);
    }

    public String getOwnerInfo(int i) {
        return getString("lock_screen_owner_info", i);
    }

    public boolean isOwnerInfoEnabled(int i) {
        return getBoolean("lock_screen_owner_info_enabled", false, i);
    }

    public void setDeviceOwnerInfo(String str) {
        if (str != null && str.isEmpty()) {
            str = null;
        }
        setString(LOCK_SCREEN_DEVICE_OWNER_INFO, str, 0);
    }

    public String getDeviceOwnerInfo() {
        return getString(LOCK_SCREEN_DEVICE_OWNER_INFO, 0);
    }

    public boolean isDeviceOwnerInfoEnabled() {
        return getDeviceOwnerInfo() != null;
    }

    private void updateEncryptionPassword(final int i, byte[] bArr) {
        if (!hasSecureLockScreen()) {
            throw new UnsupportedOperationException("This operation requires the lock screen feature.");
        }
        if (isDeviceEncryptionEnabled()) {
            final IBinder service = ServiceManager.getService("mount");
            if (service == null) {
                Log.e(TAG, "Could not find the mount service to update the encryption password");
            } else {
                final String str = bArr != null ? new String(bArr) : null;
                new AsyncTask<Void, Void, Void>() { // from class: com.android.internal.widget.LockPatternUtils.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public Void doInBackground(Void... voidArr) {
                        try {
                            IStorageManager.Stub.asInterface(service).changeEncryptionPassword(i, str);
                            return null;
                        } catch (RemoteException e) {
                            Log.e(LockPatternUtils.TAG, "Error changing encryption password", e);
                            return null;
                        }
                    }
                }.execute(new Void[0]);
            }
        }
    }

    private void updateEncryptionPasswordIfNeeded(LockscreenCredential lockscreenCredential, int i) {
        if (i == 0 && isDeviceEncryptionEnabled()) {
            if (!shouldEncryptWithCredentials(true)) {
                updateEncryptionPassword(1, null);
                return;
            }
            if (lockscreenCredential.isNone()) {
                setCredentialRequiredToDecrypt(false);
            }
            updateEncryptionPassword(lockscreenCredential.getStorageCryptType(), lockscreenCredential.getCredential());
        }
    }

    private void updatePasswordHistory(LockscreenCredential lockscreenCredential, int i) {
        if (lockscreenCredential.isNone() || lockscreenCredential.isPattern()) {
            return;
        }
        String string = getString(PASSWORD_HISTORY_KEY, i);
        String strPasswordToHistoryHash = "";
        if (string == null) {
            string = "";
        }
        int requestedPasswordHistoryLength = getRequestedPasswordHistoryLength(i);
        if (requestedPasswordHistoryLength != 0) {
            strPasswordToHistoryHash = passwordToHistoryHash(lockscreenCredential.getCredential(), getPasswordHistoryHashFactor(lockscreenCredential, i), i);
            if (strPasswordToHistoryHash == null) {
                Log.e(TAG, "Compute new style password hash failed, fallback to legacy style");
                strPasswordToHistoryHash = legacyPasswordToHash(lockscreenCredential.getCredential(), i);
            }
            if (!TextUtils.isEmpty(string)) {
                String[] strArrSplit = string.split(",");
                StringJoiner stringJoiner = new StringJoiner(",");
                stringJoiner.add(strPasswordToHistoryHash);
                for (int i2 = 0; i2 < requestedPasswordHistoryLength - 1 && i2 < strArrSplit.length; i2++) {
                    stringJoiner.add(strArrSplit[i2]);
                }
                strPasswordToHistoryHash = stringJoiner.toString();
            }
        }
        setString(PASSWORD_HISTORY_KEY, strPasswordToHistoryHash, i);
    }

    public static boolean isDeviceEncryptionEnabled() {
        return StorageManager.isEncrypted();
    }

    public static boolean isFileEncryptionEnabled() {
        return StorageManager.isFileEncryptedNativeOrEmulated();
    }

    public void clearEncryptionPassword() {
        updateEncryptionPassword(1, null);
    }

    @Deprecated
    public int getKeyguardStoredPasswordQuality(int i) {
        return credentialTypeToPasswordQuality(getCredentialTypeForUser(i));
    }

    public void setSeparateProfileChallengeEnabled(int i, boolean z, LockscreenCredential lockscreenCredential) {
        if (isManagedProfile(i)) {
            try {
                getLockSettings().setSeparateProfileChallengeEnabled(i, z, lockscreenCredential);
                reportEnabledTrustAgentsChanged(i);
            } catch (RemoteException unused) {
                Log.e(TAG, "Couldn't update work profile challenge enabled");
            }
        }
    }

    public boolean isSeparateProfileChallengeEnabled(int i) {
        return isManagedProfile(i) && hasSeparateChallenge(i);
    }

    public boolean isManagedProfileWithUnifiedChallenge(int i) {
        return isManagedProfile(i) && !hasSeparateChallenge(i);
    }

    public boolean isSeparateProfileChallengeAllowed(int i) {
        return isManagedProfile(i) && getDevicePolicyManager().isSeparateProfileChallengeAllowed(i);
    }

    public boolean isSeparateProfileChallengeAllowedToUnify(int i) {
        return getDevicePolicyManager().isProfileActivePasswordSufficientForParent(i) && !getUserManager().hasUserRestriction(UserManager.DISALLOW_UNIFIED_PASSWORD, UserHandle.of(i));
    }

    private boolean hasSeparateChallenge(int i) {
        try {
            return getLockSettings().getSeparateProfileChallengeEnabled(i);
        } catch (RemoteException unused) {
            Log.e(TAG, "Couldn't get separate profile challenge enabled");
            return false;
        }
    }

    private boolean isManagedProfile(int i) {
        UserInfo userInfo = getUserManager().getUserInfo(i);
        return userInfo != null && userInfo.isManagedProfile();
    }

    public static List<LockPatternView.Cell> byteArrayToPattern(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        for (byte b : bArr) {
            byte b2 = (byte) (b - 49);
            arrayListNewArrayList.add(LockPatternView.Cell.of(b2 / 3, b2 % 3));
        }
        return arrayListNewArrayList;
    }

    public static byte[] patternToByteArray(List<LockPatternView.Cell> list) {
        if (list == null) {
            return new byte[0];
        }
        int size = list.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            LockPatternView.Cell cell = list.get(i);
            bArr[i] = (byte) ((cell.getRow() * 3) + cell.getColumn() + 49);
        }
        return bArr;
    }

    private String getSalt(int i) {
        long jNextLong = getLong(LOCK_PASSWORD_SALT_KEY, 0L, i);
        if (jNextLong == 0) {
            try {
                jNextLong = SecureRandom.getInstance("SHA1PRNG").nextLong();
                setLong(LOCK_PASSWORD_SALT_KEY, jNextLong, i);
                Log.v(TAG, "Initialized lock password salt for user: " + i);
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException("Couldn't get SecureRandom number", e);
            }
        }
        return Long.toHexString(jNextLong);
    }

    public String legacyPasswordToHash(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            byte[] bytes = getSalt(i).getBytes();
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length + bytes.length);
            System.arraycopy(bytes, 0, bArrCopyOf, bArr.length, bytes.length);
            byte[] bArrDigest = MessageDigest.getInstance(KeyProperties.DIGEST_SHA1).digest(bArrCopyOf);
            byte[] bArrDigest2 = MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(bArrCopyOf);
            byte[] bArr2 = new byte[bArrDigest.length + bArrDigest2.length];
            System.arraycopy(bArrDigest, 0, bArr2, 0, bArrDigest.length);
            System.arraycopy(bArrDigest2, 0, bArr2, bArrDigest.length, bArrDigest2.length);
            char[] cArrEncode = HexEncoding.encode(bArr2);
            Arrays.fill(bArrCopyOf, (byte) 0);
            return new String(cArrEncode);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Missing digest algorithm: ", e);
        }
    }

    private String passwordToHistoryHash(byte[] bArr, byte[] bArr2, int i) {
        if (bArr == null || bArr.length == 0 || bArr2 == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr2);
            byte[] bytes = getSalt(i).getBytes();
            byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length + bytes.length);
            System.arraycopy(bytes, 0, bArrCopyOf, bArr.length, bytes.length);
            messageDigest.update(bArrCopyOf);
            Arrays.fill(bArrCopyOf, (byte) 0);
            return new String(HexEncoding.encode(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Missing digest algorithm: ", e);
        }
    }

    public int getCredentialTypeForUser(int i) {
        try {
            return getLockSettings().getCredentialType(i);
        } catch (RemoteException e) {
            Log.e(TAG, "failed to get credential type", e);
            return -1;
        }
    }

    public boolean isSecure(int i) {
        return getCredentialTypeForUser(i) != -1;
    }

    public boolean isLockPasswordEnabled(int i) {
        int credentialTypeForUser = getCredentialTypeForUser(i);
        return credentialTypeForUser == 4 || credentialTypeForUser == 3;
    }

    public boolean isLockPatternEnabled(int i) {
        return getCredentialTypeForUser(i) == 1;
    }

    @Deprecated
    public boolean isLegacyLockPatternEnabled(int i) {
        return getBoolean(LEGACY_LOCK_PATTERN_ENABLED, true, i);
    }

    @Deprecated
    public void setLegacyLockPatternEnabled(int i) {
        setBoolean("lock_pattern_autolock", true, i);
    }

    public boolean isVisiblePatternEnabled(int i) {
        return getBoolean("lock_pattern_visible_pattern", false, i);
    }

    public void setVisiblePatternEnabled(boolean z, int i) {
        setBoolean("lock_pattern_visible_pattern", z, i);
        if (i != 0) {
            return;
        }
        IBinder service = ServiceManager.getService("mount");
        if (service == null) {
            Log.e(TAG, "Could not find the mount service to update the user info");
            return;
        }
        try {
            IStorageManager.Stub.asInterface(service).setField(StorageManager.PATTERN_VISIBLE_KEY, z ? "1" : AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
        } catch (RemoteException e) {
            Log.e(TAG, "Error changing pattern visible state", e);
        }
    }

    public boolean isVisiblePatternEverChosen(int i) {
        return getString("lock_pattern_visible_pattern", i) != null;
    }

    public void setVisiblePasswordEnabled(boolean z, int i) {
        if (i != 0) {
            return;
        }
        IBinder service = ServiceManager.getService("mount");
        if (service == null) {
            Log.e(TAG, "Could not find the mount service to update the user info");
            return;
        }
        try {
            IStorageManager.Stub.asInterface(service).setField(StorageManager.PASSWORD_VISIBLE_KEY, z ? "1" : AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
        } catch (RemoteException e) {
            Log.e(TAG, "Error changing password visible state", e);
        }
    }

    public boolean isTactileFeedbackEnabled() {
        return Settings.System.getIntForUser(this.mContentResolver, Settings.System.HAPTIC_FEEDBACK_ENABLED, 1, -2) != 0;
    }

    public long setLockoutAttemptDeadline(int i, int i2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() + ((long) i2);
        if (i == -9999) {
            return jElapsedRealtime;
        }
        this.mLockoutDeadlines.put(i, jElapsedRealtime);
        return jElapsedRealtime;
    }

    public long getLockoutAttemptDeadline(int i) {
        long j = this.mLockoutDeadlines.get(i, 0L);
        if (j >= SystemClock.elapsedRealtime() || j == 0) {
            return j;
        }
        this.mLockoutDeadlines.put(i, 0L);
        return 0L;
    }

    private boolean getBoolean(String str, boolean z, int i) {
        try {
            return getLockSettings().getBoolean(str, z, i);
        } catch (RemoteException unused) {
            return z;
        }
    }

    private void setBoolean(String str, boolean z, int i) {
        try {
            getLockSettings().setBoolean(str, z, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't write boolean " + str + e);
        }
    }

    private long getLong(String str, long j, int i) {
        try {
            return getLockSettings().getLong(str, j, i);
        } catch (RemoteException unused) {
            return j;
        }
    }

    private void setLong(String str, long j, int i) {
        try {
            getLockSettings().setLong(str, j, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't write long " + str + e);
        }
    }

    private String getString(String str, int i) {
        try {
            return getLockSettings().getString(str, null, i);
        } catch (RemoteException unused) {
            return null;
        }
    }

    private void setString(String str, String str2, int i) {
        try {
            getLockSettings().setString(str, str2, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Couldn't write string " + str + e);
        }
    }

    public void setPowerButtonInstantlyLocks(boolean z, int i) {
        setBoolean(LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS, z, i);
    }

    public boolean getPowerButtonInstantlyLocks(int i) {
        return getBoolean(LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS, true, i);
    }

    public boolean isPowerButtonInstantlyLocksEverChosen(int i) {
        return getString(LOCKSCREEN_POWER_BUTTON_INSTANTLY_LOCKS, i) != null;
    }

    public void setEnabledTrustAgents(Collection<ComponentName> collection, int i) {
        StringBuilder sb = new StringBuilder();
        for (ComponentName componentName : collection) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(componentName.flattenToShortString());
        }
        setString(ENABLED_TRUST_AGENTS, sb.toString(), i);
        getTrustManager().reportEnabledTrustAgentsChanged(i);
    }

    public List<ComponentName> getEnabledTrustAgents(int i) {
        String string = getString(ENABLED_TRUST_AGENTS, i);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String[] strArrSplit = string.split(",");
        ArrayList arrayList = new ArrayList(strArrSplit.length);
        for (String str : strArrSplit) {
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(ComponentName.unflattenFromString(str));
            }
        }
        return arrayList;
    }

    public void requireCredentialEntry(int i) {
        requireStrongAuth(4, i);
    }

    public void requireStrongAuth(int i, int i2) {
        try {
            getLockSettings().requireStrongAuth(i, i2);
        } catch (RemoteException e) {
            Log.e(TAG, "Error while requesting strong auth: " + e);
        }
    }

    private void reportEnabledTrustAgentsChanged(int i) {
        getTrustManager().reportEnabledTrustAgentsChanged(i);
    }

    public boolean isCredentialRequiredToDecrypt(boolean z) {
        int i = Settings.Global.getInt(this.mContentResolver, Settings.Global.REQUIRE_PASSWORD_TO_DECRYPT, -1);
        return i == -1 ? z : i != 0;
    }

    public void setCredentialRequiredToDecrypt(boolean z) {
        if (!getUserManager().isSystemUser() && !getUserManager().isPrimaryUser()) {
            throw new IllegalStateException("Only the system or primary user may call setCredentialRequiredForDecrypt()");
        }
        if (isDeviceEncryptionEnabled()) {
            Settings.Global.putInt(this.mContext.getContentResolver(), Settings.Global.REQUIRE_PASSWORD_TO_DECRYPT, z ? 1 : 0);
        }
    }

    private boolean isDoNotAskCredentialsOnBootSet() {
        return getDevicePolicyManager().getDoNotAskCredentialsOnBoot();
    }

    private boolean shouldEncryptWithCredentials(boolean z) {
        return isCredentialRequiredToDecrypt(z) && !isDoNotAskCredentialsOnBootSet();
    }

    private void throwIfCalledOnMainThread() {
        if (Looper.getMainLooper().isCurrentThread()) {
            throw new IllegalStateException("should not be called from the main thread.");
        }
    }

    public void registerStrongAuthTracker(StrongAuthTracker strongAuthTracker) {
        try {
            getLockSettings().registerStrongAuthTracker(strongAuthTracker.getStub());
        } catch (RemoteException unused) {
            throw new RuntimeException("Could not register StrongAuthTracker");
        }
    }

    public void unregisterStrongAuthTracker(StrongAuthTracker strongAuthTracker) {
        try {
            getLockSettings().unregisterStrongAuthTracker(strongAuthTracker.getStub());
        } catch (RemoteException e) {
            Log.e(TAG, "Could not unregister StrongAuthTracker", e);
        }
    }

    public void reportSuccessfulBiometricUnlock(boolean z, int i) {
        try {
            getLockSettings().reportSuccessfulBiometricUnlock(z, i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not report successful biometric unlock", e);
        }
    }

    public void scheduleNonStrongBiometricIdleTimeout(int i) {
        try {
            getLockSettings().scheduleNonStrongBiometricIdleTimeout(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not schedule non-strong biometric idle timeout", e);
        }
    }

    public int getStrongAuthForUser(int i) {
        try {
            return getLockSettings().getStrongAuthForUser(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Could not get StrongAuth", e);
            return StrongAuthTracker.getDefaultFlags(this.mContext);
        }
    }

    public boolean isTrustAllowedForUser(int i) {
        return getStrongAuthForUser(i) == 0;
    }

    public boolean isBiometricAllowedForUser(int i) {
        return (getStrongAuthForUser(i) & (-5)) == 0;
    }

    public boolean isUserInLockdown(int i) {
        return getStrongAuthForUser(i) == 32;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class WrappedCallback extends ICheckCredentialProgressCallback.Stub {
        private CheckCredentialProgressCallback mCallback;
        private Handler mHandler;

        WrappedCallback(Handler handler, CheckCredentialProgressCallback checkCredentialProgressCallback) {
            this.mHandler = handler;
            this.mCallback = checkCredentialProgressCallback;
        }

        @Override // com.android.internal.widget.ICheckCredentialProgressCallback
        public void onCredentialVerified() throws RemoteException {
            if (this.mHandler == null) {
                Log.e(LockPatternUtils.TAG, "Handler is null during callback");
            }
            this.mHandler.post(new Runnable() { // from class: com.android.internal.widget.-$$Lambda$LockPatternUtils$WrappedCallback$rBO7YmQAA9ZowrWHy1I9ASOWiCI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCredentialVerified$0$LockPatternUtils$WrappedCallback();
                }
            });
            this.mHandler = null;
        }

        public /* synthetic */ void lambda$onCredentialVerified$0$LockPatternUtils$WrappedCallback() {
            this.mCallback.onEarlyMatched();
            this.mCallback = null;
        }
    }

    private ICheckCredentialProgressCallback wrapCallback(CheckCredentialProgressCallback checkCredentialProgressCallback) {
        if (checkCredentialProgressCallback == null) {
            return null;
        }
        if (this.mHandler == null) {
            throw new IllegalStateException("Must construct LockPatternUtils on a looper thread to use progress callbacks.");
        }
        return new WrappedCallback(this.mHandler, checkCredentialProgressCallback);
    }

    private LockSettingsInternal getLockSettingsInternal() {
        LockSettingsInternal lockSettingsInternal = (LockSettingsInternal) LocalServices.getService(LockSettingsInternal.class);
        if (lockSettingsInternal != null) {
            return lockSettingsInternal;
        }
        throw new SecurityException("Only available to system server itself");
    }

    public long addEscrowToken(byte[] bArr, int i, EscrowTokenStateChangeCallback escrowTokenStateChangeCallback) {
        return getLockSettingsInternal().addEscrowToken(bArr, i, escrowTokenStateChangeCallback);
    }

    public boolean removeEscrowToken(long j, int i) {
        return getLockSettingsInternal().removeEscrowToken(j, i);
    }

    public boolean isEscrowTokenActive(long j, int i) {
        return getLockSettingsInternal().isEscrowTokenActive(j, i);
    }

    public boolean setLockCredentialWithToken(LockscreenCredential lockscreenCredential, long j, byte[] bArr, int i) {
        if (!hasSecureLockScreen()) {
            throw new UnsupportedOperationException("This operation requires the lock screen feature.");
        }
        lockscreenCredential.checkLength();
        if (!getLockSettingsInternal().setLockCredentialWithToken(lockscreenCredential, j, bArr, i)) {
            return false;
        }
        onPostPasswordChanged(lockscreenCredential, i);
        return true;
    }

    public boolean unlockUserWithToken(long j, byte[] bArr, int i) {
        return getLockSettingsInternal().unlockUserWithToken(j, bArr, i);
    }

    public static class StrongAuthTracker {
        private static final int ALLOWING_BIOMETRIC = 4;
        public static final int SOME_AUTH_REQUIRED_AFTER_USER_REQUEST = 4;
        public static final int STRONG_AUTH_NOT_REQUIRED = 0;
        public static final int STRONG_AUTH_REQUIRED_AFTER_BOOT = 1;
        public static final int STRONG_AUTH_REQUIRED_AFTER_DPM_LOCK_NOW = 2;
        public static final int STRONG_AUTH_REQUIRED_AFTER_LOCKOUT = 8;
        public static final int STRONG_AUTH_REQUIRED_AFTER_NON_STRONG_BIOMETRICS_TIMEOUT = 128;
        public static final int STRONG_AUTH_REQUIRED_AFTER_TIMEOUT = 16;
        public static final int STRONG_AUTH_REQUIRED_AFTER_USER_LOCKDOWN = 32;
        public static final int STRONG_AUTH_REQUIRED_FOR_UNATTENDED_UPDATE = 64;
        private final boolean mDefaultIsNonStrongBiometricAllowed;
        private final int mDefaultStrongAuthFlags;
        private final H mHandler;
        private final SparseBooleanArray mIsNonStrongBiometricAllowedForUser;
        private final SparseIntArray mStrongAuthRequiredForUser;
        private final IStrongAuthTracker.Stub mStub;

        @Retention(RetentionPolicy.SOURCE)
        public @interface StrongAuthFlags {
        }

        public void onIsNonStrongBiometricAllowedChanged(int i) {
        }

        public void onStrongAuthRequiredChanged(int i) {
        }

        public StrongAuthTracker(Context context) {
            this(context, Looper.myLooper());
        }

        public StrongAuthTracker(Context context, Looper looper) {
            this.mStrongAuthRequiredForUser = new SparseIntArray();
            this.mIsNonStrongBiometricAllowedForUser = new SparseBooleanArray();
            this.mDefaultIsNonStrongBiometricAllowed = true;
            this.mStub = new IStrongAuthTracker.Stub() { // from class: com.android.internal.widget.LockPatternUtils.StrongAuthTracker.1
                @Override // android.app.trust.IStrongAuthTracker
                public void onStrongAuthRequiredChanged(int i, int i2) {
                    StrongAuthTracker.this.mHandler.obtainMessage(1, i, i2).sendToTarget();
                }

                @Override // android.app.trust.IStrongAuthTracker
                public void onIsNonStrongBiometricAllowedChanged(boolean z, int i) {
                    StrongAuthTracker.this.mHandler.obtainMessage(2, z ? 1 : 0, i).sendToTarget();
                }
            };
            this.mHandler = new H(looper);
            this.mDefaultStrongAuthFlags = getDefaultFlags(context);
        }

        public static int getDefaultFlags(Context context) {
            return context.getResources().getBoolean(R.bool.config_strongAuthRequiredOnBoot) ? 1 : 0;
        }

        public int getStrongAuthForUser(int i) {
            return this.mStrongAuthRequiredForUser.get(i, this.mDefaultStrongAuthFlags);
        }

        public boolean isTrustAllowedForUser(int i) {
            return getStrongAuthForUser(i) == 0;
        }

        public boolean isBiometricAllowedForUser(boolean z, int i) {
            boolean z2 = (getStrongAuthForUser(i) & (-5)) == 0;
            return !z ? z2 & isNonStrongBiometricAllowedAfterIdleTimeout(i) : z2;
        }

        public boolean isNonStrongBiometricAllowedAfterIdleTimeout(int i) {
            return this.mIsNonStrongBiometricAllowedForUser.get(i, true);
        }

        protected void handleStrongAuthRequiredChanged(int i, int i2) {
            if (i != getStrongAuthForUser(i2)) {
                if (i == this.mDefaultStrongAuthFlags) {
                    this.mStrongAuthRequiredForUser.delete(i2);
                } else {
                    this.mStrongAuthRequiredForUser.put(i2, i);
                }
                onStrongAuthRequiredChanged(i2);
            }
        }

        protected void handleIsNonStrongBiometricAllowedChanged(boolean z, int i) {
            if (z != isNonStrongBiometricAllowedAfterIdleTimeout(i)) {
                if (z) {
                    this.mIsNonStrongBiometricAllowedForUser.delete(i);
                } else {
                    this.mIsNonStrongBiometricAllowedForUser.put(i, z);
                }
                onIsNonStrongBiometricAllowedChanged(i);
            }
        }

        public IStrongAuthTracker.Stub getStub() {
            return this.mStub;
        }

        private class H extends Handler {
            static final int MSG_ON_IS_NON_STRONG_BIOMETRIC_ALLOWED_CHANGED = 2;
            static final int MSG_ON_STRONG_AUTH_REQUIRED_CHANGED = 1;

            public H(Looper looper) {
                super(looper);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                if (i == 1) {
                    StrongAuthTracker.this.handleStrongAuthRequiredChanged(message.arg1, message.arg2);
                } else {
                    if (i != 2) {
                        return;
                    }
                    StrongAuthTracker.this.handleIsNonStrongBiometricAllowedChanged(message.arg1 == 1, message.arg2);
                }
            }
        }
    }

    public void enableSyntheticPassword() {
        setLong(SYNTHETIC_PASSWORD_ENABLED_KEY, 1L, 0);
    }

    public void disableSyntheticPassword() {
        setLong(SYNTHETIC_PASSWORD_ENABLED_KEY, 0L, 0);
    }

    public boolean isSyntheticPasswordEnabled() {
        return getLong(SYNTHETIC_PASSWORD_ENABLED_KEY, 1L, 0) != 0;
    }

    public boolean hasPendingEscrowToken(int i) {
        try {
            return getLockSettings().hasPendingEscrowToken(i);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return false;
        }
    }

    public boolean hasSecureLockScreen() {
        if (this.mHasSecureLockScreen == null) {
            try {
                this.mHasSecureLockScreen = Boolean.valueOf(getLockSettings().hasSecureLockScreen());
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
        return this.mHasSecureLockScreen.booleanValue();
    }

    public static boolean userOwnsFrpCredential(Context context, UserInfo userInfo) {
        return userInfo != null && userInfo.isPrimary() && userInfo.isAdmin() && frpCredentialEnabled(context);
    }

    public static boolean frpCredentialEnabled(Context context) {
        return context.getResources().getBoolean(R.bool.config_enableCredentialFactoryResetProtection);
    }

    public boolean tryUnlockWithCachedUnifiedChallenge(int i) {
        try {
            return getLockSettings().tryUnlockWithCachedUnifiedChallenge(i);
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void removeCachedUnifiedChallenge(int i) {
        try {
            getLockSettings().removeCachedUnifiedChallenge(i);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }
}
