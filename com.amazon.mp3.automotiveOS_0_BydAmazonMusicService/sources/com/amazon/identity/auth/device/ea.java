package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ea {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f299a = {"-----BEGIN RSA PRIVATE KEY-----", "-----END RSA PRIVATE KEY-----", "-----BEGIN EC PRIVATE KEY-----", "-----END EC PRIVATE KEY-----", "-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----"};
    public static final String b = "com.amazon.identity.auth.device.ea";

    public static KeyFactory a(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.e(ga.a(b), "The algorithm cannot be null");
            throw new IllegalArgumentException("The algorithm cannot be null");
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            Log.i(ga.a(b), "KeyFactory at Android version " + i);
            try {
                return KeyFactory.getInstance(str);
            } catch (Exception e) {
                Log.e(ga.a(b), "getKeyFactory: Could not get private key because there was no " + str + " algorithm", e);
                yd.a("MAPKeyFactoryGenerationError:DefaultProviderNotSupportAlgorithm:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
            }
        } else {
            try {
                return KeyFactory.getInstance(str, "BC");
            } catch (NoSuchAlgorithmException e2) {
                Log.e(ga.a(b), "getKeyFactory: Could not get private key because there was no " + str + " algorithm", e2);
                yd.a("MAPKeyFactoryGenerationError:BouncyCastleMissing:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                try {
                    return KeyFactory.getInstance(str);
                } catch (NoSuchAlgorithmException e3) {
                    Log.e(ga.a(b), "getKeyFactory: Could not get private key because there was no RSA algorithm", e3);
                    yd.a("MAPKeyFactoryGenerationError:DefaultProviderNotSupportAlgorithm:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                }
            } catch (NoSuchProviderException e4) {
                Log.e(ga.a(b), "The device doesn't contain BouncyCastle Provider, try using the default.", e4);
                yd.a("MAPKeyFactoryGenerationError:MissingBouncyCastle:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                try {
                    return KeyFactory.getInstance(str);
                } catch (NoSuchAlgorithmException e5) {
                    Log.e(ga.a(b), "getKeyFactory: Could not get private key because there was no RSA algorithm", e5);
                    yd.a("MAPKeyFactoryGenerationError:DefaultProviderNotSupportAlgorithm:Algorithm:" + str + ":SystemVersion:" + Build.VERSION.SDK_INT, new String[0]);
                }
            }
        }
        return null;
    }

    public static PrivateKey b(String str) throws InvalidKeySpecException {
        String strTrim = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        KeyFactory keyFactoryA = a("RSA");
        if (keyFactoryA == null) {
            Log.w(ga.a(b), "Failed to create keyFactory for the input key");
            return null;
        }
        String str2 = b;
        keyFactoryA.getAlgorithm();
        ga.a(str2);
        if (str != null) {
            for (String str3 : f299a) {
                str = str.replace(str3, "");
            }
            strTrim = str.trim();
        }
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(strTrim.getBytes(), 0));
        try {
            return keyFactoryA.generatePrivate(pKCS8EncodedKeySpec);
        } catch (InvalidKeySpecException e) {
            ga.a(b);
            try {
                return a("EC").generatePrivate(pKCS8EncodedKeySpec);
            } catch (InvalidKeySpecException e2) {
                String str4 = b;
                Log.e(ga.a(str4), "Failed to create private key using the original algo: " + keyFactoryA.getAlgorithm(), e);
                Log.e(ga.a(str4), "Failed to create private key when retried using algo: EC", e2);
                throw e;
            }
        }
    }

    public static void a(Context context, String str) {
        if (!TextUtils.equals(str, "SSO Currently does not have credentials") || qe.f(context)) {
            return;
        }
        String strA = oa.a(context, DeviceAttribute.CentralDeviceType);
        Log.e(ga.a(b), "Central DMS token or DMS private key get corrupted, MAP is going to deregister device to clean the state");
        yd.a("DMSCredentialCorrupted:DeviceType:" + strA + ":SYSTEM_VERSION:" + Build.VERSION.SDK_INT, new String[0]);
        try {
            new MAPAccountManager(context).deregisterDevice(null).get();
        } catch (MAPCallbackErrorException e) {
            Bundle errorBundle = e.getErrorBundle();
            Log.e(ga.a(b), "Error deregister the device when DMS private-key/DMS token got corrupted or missing. Error code:" + errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") + " Error message is:" + errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
        } catch (InterruptedException e2) {
            Log.e(ga.a(b), "InterruptedException! Error deregister the device when DMS private-key/DMS token got corrupted or missing. ", e2);
        } catch (ExecutionException e3) {
            Log.e(ga.a(b), "ExecutionException! Error deregister the device when DMS private-key/DMS token got corrupted or missing.", e3);
        }
        Log.i(ga.a(b), "Successfully deregister the device when DMS private-key/DMS token got corrupted or missing.");
    }
}
