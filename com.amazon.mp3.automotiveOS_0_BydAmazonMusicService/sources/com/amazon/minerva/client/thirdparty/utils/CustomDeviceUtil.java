package com.amazon.minerva.client.thirdparty.utils;

import android.content.Context;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.compliance.ChildProfileVerifier;
import com.amazon.minerva.client.thirdparty.compliance.NonAnonymousCustomerIdProvider;
import com.amazon.minerva.client.thirdparty.compliance.UserControlVerifier;
import com.amazon.minerva.client.thirdparty.transport.OAuthProvider;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class CustomDeviceUtil {
    private static final CustomDeviceUtil INSTANCE = new CustomDeviceUtil();
    private static final String TAG = "CustomDeviceUtil";
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private String mAppConfigId;
    private ChildProfileVerifier mChildProfileVerifier;
    private Context mContext;
    private String mDeviceType;
    private String mHashedDeviceType;
    private NonAnonymousCustomerIdProvider mNonAnonymousCustomerIdProvider;
    private String mNonAnonymousDeviceId;
    private OAuthProvider mOAuthProvider;
    private UserControlVerifier mUserControlVerifier;

    private CustomDeviceUtil() {
    }

    public static CustomDeviceUtil getInstance() {
        return INSTANCE;
    }

    public boolean isInitialized() {
        return this.initialized.get();
    }

    public synchronized void initialize(Context context) {
        if (!this.initialized.get()) {
            String str = TAG;
            Log.i(str, "Initializing CustomDeviceUtil");
            if (context == null) {
                Log.e(str, "Context cannot be null.");
                return;
            }
            this.mContext = context;
        }
    }

    public void setDeviceType(String str) {
        this.mDeviceType = str;
        this.mHashedDeviceType = calculateHashedDeviceType(str);
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getHashedDeviceType() {
        return this.mHashedDeviceType;
    }

    public void setOAuthProvider(OAuthProvider oAuthProvider) {
        this.mOAuthProvider = oAuthProvider;
    }

    public OAuthProvider getOAuthProvider() {
        return this.mOAuthProvider;
    }

    public void setChildProfileVerifier(ChildProfileVerifier childProfileVerifier) {
        this.mChildProfileVerifier = childProfileVerifier;
    }

    public ChildProfileVerifier getChildProfileVerifier() {
        return this.mChildProfileVerifier;
    }

    public void setUserControlVerifier(UserControlVerifier userControlVerifier) {
        this.mUserControlVerifier = userControlVerifier;
    }

    public UserControlVerifier getUserControlVerifier() {
        return this.mUserControlVerifier;
    }

    public void setCustomerIdProvider(NonAnonymousCustomerIdProvider nonAnonymousCustomerIdProvider) {
        this.mNonAnonymousCustomerIdProvider = nonAnonymousCustomerIdProvider;
    }

    public NonAnonymousCustomerIdProvider getNonAnonymousCustomerIdProvider() {
        return this.mNonAnonymousCustomerIdProvider;
    }

    public void setNonAnonymousDeviceId(String str) {
        this.mNonAnonymousDeviceId = str;
    }

    public String getNonAnonymousDeviceId() {
        return this.mNonAnonymousDeviceId;
    }

    public void setAppConfigId(String str) {
        this.mAppConfigId = str;
    }

    public String getAppConfigId() {
        return this.mAppConfigId;
    }

    private String calculateHashedDeviceType(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            String string = new BigInteger(1, messageDigest.digest()).toString(16);
            return string.length() > 63 ? string.substring(1) : string;
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "Failed to generate hash value of device type", e);
            return "UNKNOWN";
        }
    }
}
