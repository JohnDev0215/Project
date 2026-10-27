package com.amazon.mp3.account.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.crypto.Crypto;
import com.amazon.music.crypto.SharedPrefCrypto;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes3.dex */
public class AccountDetailStorage {
    private static final String TAG = "AccountDetailStorage";
    private static AccountDetailStorage sInstance;
    private final Context mContext;
    private SharedPreferences mDetailPrefs;

    public static synchronized void createInstance(final Context context) {
        if (sInstance == null && context != null) {
            sInstance = new AccountDetailStorage(context);
        }
    }

    public static AccountDetailStorage get(final Context context) {
        if (context == null) {
            return get();
        }
        if (sInstance == null) {
            createInstance(context);
        }
        return sInstance;
    }

    @Deprecated
    public static AccountDetailStorage get() {
        if (sInstance == null) {
            createInstance(AmazonApplication.getContext());
        }
        return sInstance;
    }

    private AccountDetailStorage(final Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDetailPrefs = applicationContext.getSharedPreferences("com.amazon.mp3_AccountDetails", 0);
        try {
            this.mDetailPrefs = SharedPrefCrypto.encrypt(new Crypto("com.amazon.mp3_AccountDetails", Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("use_gcm", false))), this.mDetailPrefs);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            Log.error(TAG, "Failed to create encrypted SharedPreferences", e);
        }
    }

    public void setAccountDetails(AccountDetails details) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putString("cirrus_account_status", details.getAccountStatus());
        AccountRequestUtil.setPrimeUserStatus(this.mContext, details.isPrimeMusicAccessible());
        editorEdit.putBoolean("cirrus_account_verified", details.getAccountVerified());
        editorEdit.apply();
    }

    public void setPrimeMusicMarket(boolean isPrimeMusicAvailable) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putBoolean("account_prime_benefit_available", isPrimeMusicAvailable);
        editorEdit.apply();
    }

    boolean isPrimeMusicMarket() {
        return this.mDetailPrefs.getBoolean("account_prime_benefit_available", false);
    }

    public void setPrimeMusicAccessible(boolean accessible) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putBoolean("cirrus_account_prime_music_accessible", accessible);
        editorEdit.apply();
    }

    public boolean isPrimeMusicAccessible() {
        return this.mDetailPrefs.getBoolean("cirrus_account_prime_music_accessible", false);
    }

    public boolean isAccountVerified() {
        try {
            return AccountManagerSingleton.get().getUser().isAccountVerified();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return this.mDetailPrefs.getBoolean("cirrus_account_verified", false);
        }
    }

    public boolean isFreeTrialEligible() {
        return this.mDetailPrefs.getBoolean("is_free_trial_eligible", false);
    }

    public void setFreeTrialEligible(boolean isFreeTrialEligible) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putBoolean("is_free_trial_eligible", isFreeTrialEligible);
        editorEdit.apply();
    }

    public void setAccountStatus(final User.CloudAccountStatus status) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putString("cirrus_account_status", status.toString());
        editorEdit.apply();
    }

    public User.CloudAccountStatus getAccountStatus() {
        String string = this.mDetailPrefs.getString("cirrus_account_status", null);
        if (string == null) {
            return User.CloudAccountStatus.UNKNOWN;
        }
        return User.CloudAccountStatus.fromString(string);
    }

    public void setLibraryHomeMarketplace(String cloudPlayerMarketplaceId) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putString("library_home_marketplace", cloudPlayerMarketplaceId);
        editorEdit.apply();
    }

    public String getLibraryHomeMarketplace(String defValue) {
        return this.mDetailPrefs.getString("library_home_marketplace", defValue);
    }

    public void setMigrationCandidateCompletedAccountFlow() {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putBoolean("account_webview_shown", true);
        editorEdit.apply();
    }

    public boolean migrationCandidateCompletedAccountFlow() {
        return this.mDetailPrefs.getBoolean("account_webview_shown", false);
    }

    public void setCloudPlayerAccountExist(boolean cloudPlayerAccountExist) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putBoolean("cirrus_account_exist", cloudPlayerAccountExist);
        editorEdit.apply();
    }

    public boolean getCloudPlayerAccountExist() {
        return this.mDetailPrefs.getBoolean("cirrus_account_exist", false) || userHasCloudPlayerAccount();
    }

    private boolean userHasCloudPlayerAccount() {
        try {
            return AccountManagerSingleton.get().getUser().hasCloudPlayerAccount();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    public boolean hasAcceptedTermsOfUse() {
        try {
            return AccountManagerSingleton.get().getUser().hasAcceptedTermsOfUse();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    public void setHasTracksCirrus(boolean hasTracks) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putBoolean("have_tracks_cirrus", hasTracks);
        editorEdit.apply();
    }

    public void setLastFetchedCirrusTrackCountTime(long timestamp) {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.putLong("last_fetched_cirrus_track_count", timestamp);
        editorEdit.apply();
    }

    public void removeAll() {
        SharedPreferences.Editor editorEdit = this.mDetailPrefs.edit();
        editorEdit.clear();
        editorEdit.apply();
    }
}
