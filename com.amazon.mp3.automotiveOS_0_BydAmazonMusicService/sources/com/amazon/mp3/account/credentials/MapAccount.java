package com.amazon.mp3.account.credentials;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import com.amazon.music.authentication.AdpInfo;
import com.amazon.music.authentication.AdpProvider;
import com.amazon.music.authentication.AuthenticationException;
import com.amazon.music.authentication.mapr5.AdpProviderMAPR5;
import com.amazon.music.crypto.Crypto;
import com.amazon.music.crypto.SharedPrefCrypto;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes3.dex */
public class MapAccount {
    private static final String TAG = "MapAccount";
    private static MAPAccountManager sMapAccountManager;
    private SharedPreferences credentialPrefs;

    public MapAccount(final Context context) {
        this.credentialPrefs = context.getSharedPreferences("com.amazon.mp3_AccountCredentials", 0);
        try {
            this.credentialPrefs = SharedPrefCrypto.encrypt(new Crypto("com.amazon.mp3_AccountCredentials", Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("use_gcm", false))), this.credentialPrefs);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            Log.error(TAG, "Failed to create encrypted SharedPreferences", e);
        }
    }

    public static MAPAccountManager getManager(Context context) {
        MAPAccountManager mAPAccountManager = sMapAccountManager;
        if (mAPAccountManager == null) {
            synchronized (MapAccount.class) {
                mAPAccountManager = sMapAccountManager;
                if (mAPAccountManager == null) {
                    mAPAccountManager = new MAPAccountManager(context.getApplicationContext());
                    sMapAccountManager = mAPAccountManager;
                }
            }
        }
        return mAPAccountManager;
    }

    public AdpInfo getADPToken() {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.mp3.account.credentials.MapAccount.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voids) {
                MapAccount.this.updateADPInfoCache();
                return null;
            }
        }.execute(new Void[0]);
        return new AdpInfo(getDeviceToken(), getPrivateKeyString());
    }

    @VisibleForTesting
    AdpProvider getAdpInfoProvider() {
        return new AdpProviderMAPR5(AmazonApplication.getApplication().getApplicationContext());
    }

    private String getPrivateKeyString() {
        return this.credentialPrefs.getString("account_private_key", "");
    }

    private String getDeviceToken() {
        return this.credentialPrefs.getString("account_device_token", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateADPInfoCache() {
        String deviceToken = getDeviceToken();
        try {
            AdpInfo adpInfo = getAdpInfoProvider().getAdpInfo();
            if (deviceToken == null || deviceToken.equals(adpInfo.adpToken)) {
                return;
            }
            SharedPreferences.Editor editorEdit = this.credentialPrefs.edit();
            editorEdit.putString("account_device_token", adpInfo.adpToken);
            editorEdit.putString("account_private_key", adpInfo.privateKeyString);
            editorEdit.apply();
            Log.debug(TAG, "Successfully updated ADP token and Private Key from Map");
        } catch (AuthenticationException e) {
            Log.error(TAG, "Failed to updated ADP token and Private Key from Map", e);
        } catch (Exception e2) {
            Log.error(TAG, "Failed to updated ADP token and Private Key from Map", e2);
        }
    }
}
