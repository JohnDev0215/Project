package com.amazon.mp3.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.config.ConfigurationLoader;
import com.amazon.music.crypto.Crypto;
import com.amazon.music.crypto.DecryptionException;
import com.amazon.music.crypto.EncryptionException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;
import org.json.JSONObject;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public final class RemoteConfigLoader implements ConfigurationLoader {
    private static final String TAG = "RemoteConfigLoader";
    private static RemoteConfigLoader sInstance;
    private final SharedPreferences defaultSharedPref;
    private final Context mContext;

    private RemoteConfigLoader(Context context) {
        this.mContext = context;
        this.defaultSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static RemoteConfigLoader getInstance(final Context context) {
        RemoteConfigLoader remoteConfigLoader = sInstance;
        if (remoteConfigLoader == null) {
            synchronized (RemoteConfigLoader.class) {
                remoteConfigLoader = sInstance;
                if (remoteConfigLoader == null) {
                    remoteConfigLoader = new RemoteConfigLoader(context);
                    sInstance = remoteConfigLoader;
                }
            }
        }
        return remoteConfigLoader;
    }

    @Override // com.amazon.music.config.ConfigurationLoader
    public JSONObject loadFromRemoteService(final String url) {
        if (AmazonApplication.getDevModeCapabilities().isHackToCustomCirrusEnabled()) {
            return null;
        }
        String str = TAG;
        Log.verbose(str, "Loading config profile");
        Log.debugBuildOnly(str, "%s", url);
        return JsonHttpClient.getJson(Uri.parse(url));
    }

    @Override // com.amazon.music.config.ConfigurationLoader
    public String getEncryptedConfigFile(final JSONObject json) {
        if (json == null) {
            return null;
        }
        try {
            return new Crypto(TAG, Boolean.valueOf(this.defaultSharedPref.getBoolean("use_gcm", false))).encrypt(json.toString());
        } catch (EncryptionException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            Log.warning(TAG, "could not encrypt config", e);
            return null;
        }
    }

    @Override // com.amazon.music.config.ConfigurationLoader
    public JSONObject loadFromDiskCache(final File cachedConfig) throws Throwable {
        String strDecrypt;
        if (cachedConfig.exists()) {
            String strLoadFromFile = FileUtil.loadFromFile(cachedConfig);
            if (!StringUtil.isNullOrWhiteSpaces(strLoadFromFile)) {
                try {
                    strDecrypt = new Crypto(TAG, Boolean.valueOf(this.defaultSharedPref.getBoolean("use_gcm", false))).decrypt(strLoadFromFile);
                } catch (DecryptionException | UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
                    Log.error(TAG, "Failed to decrypt file contents", e);
                    strDecrypt = null;
                }
            } else {
                Log.error(TAG, "Cannot decrypt an empty file! cachedConfig = " + cachedConfig);
            }
            strDecrypt = null;
        } else {
            strDecrypt = null;
        }
        if (strDecrypt != null) {
            try {
                JSONObject jSONObject = new JSONObject(strDecrypt);
                jSONObject.getJSONObject("config");
                Log.verbose(TAG, "Loaded cached remote config");
                return jSONObject;
            } catch (JSONException e2) {
                FileUtil.deleteFile(cachedConfig);
                Log.warning(TAG, "Cached remote config may be corrupt", e2);
            }
        } else {
            FileUtil.deleteFile(cachedConfig);
            Log.warning(TAG, "Cached config file couldn't be loaded, deleting it");
        }
        return null;
    }

    @Override // com.amazon.music.config.ConfigurationLoader
    public void loadPreloadedAssociatedTag(final Context context) {
        BootstrapSingletonTask.get(context).registerUserObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.config.RemoteConfigLoader.1
            @Override // rx.functions.Action1
            public void call(final Object o) {
                AssociateTag.getInstance(context).getAssociateTagFromPreloadedFile();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.config.RemoteConfigLoader.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.error(RemoteConfigLoader.TAG, "BootstrapSingletonTask error: ", throwable.getMessage());
            }
        });
    }
}
