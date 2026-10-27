package com.amazon.music.signin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MAPAccount {
    private static final Logger LOG = LoggerFactory.getLogger(MAPAccount.class.getSimpleName());
    private static final String TAG = "MAPAccount";
    private final Context context;
    private SharedPreferences credentialPrefs;

    public MAPAccount(@NonNull Context context) {
        this.context = context;
        this.credentialPrefs = context.getSharedPreferences("com.amazon.mp3_AccountCredentials", 0);
        try {
            this.credentialPrefs = SharedPrefCrypto.encrypt(new Crypto("com.amazon.mp3_AccountCredentials", Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean("use_gcm", false))), this.credentialPrefs);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOG.error(TAG, "Failed to create encrypted SharedPreferences", e);
        }
    }

    @NonNull
    public AdpInfo getADPToken() {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.music.signin.MAPAccount.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                MAPAccount.this.updateADPInfoCache();
                return null;
            }
        }.execute(new Void[0]);
        return new AdpInfo(getDeviceToken(), getPrivateKeyString());
    }

    @NonNull
    AdpProvider getAdpInfoProvider() {
        return new AdpProviderMAPR5(this.context);
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
            LOG.debug(TAG, "Successfully updated ADP token and Private Key from Map");
        } catch (AuthenticationException e) {
            LOG.error(TAG, "Failed to updated ADP token and Private Key from Map", e);
            e.getCause();
        } catch (Exception e2) {
            LOG.error(TAG, "Failed to updated ADP token and Private Key from Map", e2);
        }
    }
}
