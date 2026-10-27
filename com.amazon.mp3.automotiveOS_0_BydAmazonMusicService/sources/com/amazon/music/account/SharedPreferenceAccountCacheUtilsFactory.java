package com.amazon.music.account;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.crypto.Crypto;
import com.amazon.music.crypto.SharedPrefCrypto;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
class SharedPreferenceAccountCacheUtilsFactory {
    private final Context context;

    public SharedPreferenceAccountCacheUtilsFactory(Context context) {
        this.context = ((Context) Validate.notNull(context)).getApplicationContext();
    }

    SharedPreferences getSharedPrefs(String str) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(str, 0);
        try {
            return SharedPrefCrypto.encrypt(new Crypto(str, Boolean.TRUE), sharedPreferences);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException unused) {
            return sharedPreferences;
        }
    }

    UserSerializer buildUserSerializer() {
        return new UserSerializer();
    }

    DeviceSerializer buildDeviceSerializer() {
        return new DeviceSerializer();
    }

    DeviceAuthorizationsSerializer buildDeviceAuthorizationsSerializer() {
        return new DeviceAuthorizationsSerializer();
    }

    DeviceCapabilitiesSerializer buildDeviceCapabilitiesSerializer() {
        return new DeviceCapabilitiesSerializer();
    }

    ReentrantReadWriteLock buildReentrantReadWriteLock() {
        return new ReentrantReadWriteLock();
    }
}
