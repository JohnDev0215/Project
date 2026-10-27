package com.amazon.mp3.account.credentials;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.map.DeviceAttributesQuery;
import com.amazon.mp3.util.DeviceID;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.music.crypto.Crypto;
import com.amazon.music.crypto.SharedPrefCrypto;
import com.amazon.music.signin.MAPAccountDeviceTypeLookup;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import rx.Observer;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class AccountCredentialStorage {
    private static final String TAG = "AccountCredentialStorage";
    private static AccountCredentialStorage sInstance;
    private final Context mContext;
    private SharedPreferences mCredentialPrefs;
    private final DeviceAttributesQuery mDeviceAttributesQuery;

    public SharedPreferences getCredentialPrefs() {
        return this.mCredentialPrefs;
    }

    public static synchronized void createInstance(final Context context) {
        if (sInstance == null && context != null) {
            sInstance = new AccountCredentialStorage(context);
        }
    }

    public static AccountCredentialStorage get(final Context context) {
        if (sInstance == null) {
            createInstance(context);
        }
        return sInstance;
    }

    @Deprecated
    public static AccountCredentialStorage get() {
        if (sInstance == null) {
            createInstance(AmazonApplication.getContext());
        }
        return sInstance;
    }

    private AccountCredentialStorage(final Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDeviceAttributesQuery = new DeviceAttributesQuery(applicationContext);
        this.mCredentialPrefs = applicationContext.getSharedPreferences("com.amazon.mp3_AccountCredentials", 0);
        try {
            this.mCredentialPrefs = SharedPrefCrypto.encrypt(new Crypto("com.amazon.mp3_AccountCredentials", Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("use_gcm", false))), this.mCredentialPrefs);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            Log.error(TAG, "Failed to create encrypted SharedPreferences", e);
        }
        boolean z = this.mCredentialPrefs.getBoolean("fos_device_Type_has_been_corrected", false);
        if (PlatformUtil.isFireOS() && !z && this.mCredentialPrefs.getString("account_device_type", "A1DL2DVDQVK3Q").equals("A1DL2DVDQVK3Q")) {
            setCorrectFOSDeviceType();
        }
    }

    private void setCorrectFOSDeviceType() {
        new MAPAccountDeviceTypeLookup(Boolean.TRUE).getAccountDeviceTypeObservable(this.mContext, MapAccount.getManager(this.mContext).getAccount()).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() { // from class: com.amazon.mp3.account.credentials.AccountCredentialStorage.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                Log.error(AccountCredentialStorage.TAG, "Error getting the deviceType from MAP", throwable);
            }

            @Override // rx.Observer
            public void onNext(String deviceType) {
                SharedPreferences.Editor editorEdit = AccountCredentialStorage.this.mCredentialPrefs.edit();
                editorEdit.putString("account_device_type", deviceType);
                editorEdit.putBoolean("fos_device_Type_has_been_corrected", true);
                editorEdit.apply();
            }
        });
    }

    public void setAtMainCookieExpirationDate(final long time) {
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.putLong("account_at_main_cookie_expiration_date", time);
        editorEdit.apply();
    }

    public long getAtMainCookieExpirationDate() {
        return this.mCredentialPrefs.getLong("account_at_main_cookie_expiration_date", 0L);
    }

    public void setCirrusNonce(final String nonce) {
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.putString("account_cirrus_nonce", nonce);
        editorEdit.apply();
    }

    public String getCirrusNonce() {
        return this.mCredentialPrefs.getString("account_cirrus_nonce", null);
    }

    public String getAccountId() {
        return this.mCredentialPrefs.getString("account_id", "");
    }

    public String getLastAccountId() {
        return this.mCredentialPrefs.getString("last_account_id", "");
    }

    public String getUsername() {
        return this.mCredentialPrefs.getString("account_username", "");
    }

    public boolean hasShownSsoWelcome() {
        return this.mCredentialPrefs.getBoolean("sso_acceptance_required", false);
    }

    public void setShowSsoWelcome(boolean ssoRequired) {
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.putBoolean("sso_acceptance_required", ssoRequired);
        editorEdit.apply();
    }

    public boolean shouldPerformSignInTasks() {
        return this.mCredentialPrefs.getBoolean("should_perform_sign_in_tasks", true);
    }

    public void setShouldPerformSignInTasks(boolean shouldPerformSignInTasks) {
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.putBoolean("should_perform_sign_in_tasks", shouldPerformSignInTasks);
        editorEdit.apply();
    }

    public String getDeviceType() {
        return this.mCredentialPrefs.getString("account_device_type", "A1DL2DVDQVK3Q");
    }

    public String getCentralDeviceType() {
        String string = this.mCredentialPrefs.getString("central_device_type", null);
        if (string == null) {
            try {
                string = this.mDeviceAttributesQuery.fetchCentralDeviceType();
                if (string != null) {
                    setCentralDeviceType(string);
                }
            } catch (DeviceDataStoreException e) {
                Log.error(TAG, "Couldn't retrieve central device type: ", e);
            }
        }
        return string;
    }

    public void setCentralDeviceType(String centralDeviceType) {
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.putString("central_device_type", centralDeviceType);
        editorEdit.apply();
    }

    public String getDeviceId() {
        String strFetchDeviceSerialNumber = null;
        if (PlatformUtil.isFireOS()) {
            try {
                strFetchDeviceSerialNumber = this.mDeviceAttributesQuery.fetchDeviceSerialNumber();
            } catch (RuntimeException e) {
                Log.error(TAG, "Couldn't retrieve device serial number, fallback to use DeviceID.getId", e);
            }
        } else {
            strFetchDeviceSerialNumber = this.mCredentialPrefs.getString("account_token_device_id", null);
        }
        return strFetchDeviceSerialNumber != null ? strFetchDeviceSerialNumber : DeviceID.getId(this.mContext);
    }

    public void setAccountCreationDate(double creationDate) {
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.putLong("account_creation_date", Double.doubleToRawLongBits(creationDate));
        editorEdit.apply();
    }

    public double getAccountCreationDate() {
        return Double.longBitsToDouble(this.mCredentialPrefs.getLong("account_creation_date", 0L));
    }

    public void removeAll() {
        String accountId = getAccountId();
        SharedPreferences.Editor editorEdit = this.mCredentialPrefs.edit();
        editorEdit.clear();
        editorEdit.putString("last_account_id", accountId);
        editorEdit.apply();
    }
}
