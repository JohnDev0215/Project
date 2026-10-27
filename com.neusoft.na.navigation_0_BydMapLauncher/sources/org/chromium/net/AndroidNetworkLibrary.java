package org.chromium.net;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.security.KeyChain;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.net.NetworkInterface;
import java.net.URLConnection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes3.dex */
class AndroidNetworkLibrary {
    private static final String TAG = "AndroidNetworkLibrary";

    AndroidNetworkLibrary() {
    }

    public static boolean storeKeyPair(Context context, byte[] bArr, byte[] bArr2) {
        try {
            Intent intentCreateInstallIntent = KeyChain.createInstallIntent();
            intentCreateInstallIntent.putExtra("PKEY", bArr2);
            intentCreateInstallIntent.putExtra("KEY", bArr);
            intentCreateInstallIntent.addFlags(268435456);
            context.startActivity(intentCreateInstallIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "could not store key pair: " + e);
            return false;
        }
    }

    public static boolean storeCertificate(Context context, int i, byte[] bArr) {
        try {
            Intent intentCreateInstallIntent = KeyChain.createInstallIntent();
            intentCreateInstallIntent.addFlags(268435456);
            if (i == 1 || i == 2) {
                intentCreateInstallIntent.putExtra("CERT", bArr);
            } else if (i == 3) {
                intentCreateInstallIntent.putExtra("PKCS12", bArr);
            } else {
                Log.w(TAG, "invalid certificate type: " + i);
                return false;
            }
            context.startActivity(intentCreateInstallIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            Log.w(TAG, "could not store crypto file: " + e);
            return false;
        }
    }

    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo." + str);
    }

    public static boolean haveOnlyLoopbackAddresses() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterfaceNextElement = networkInterfaces.nextElement();
                if (networkInterfaceNextElement.isUp() && !networkInterfaceNextElement.isLoopback()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Log.w(TAG, "could not get network interfaces: " + e);
            return false;
        }
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return X509Util.verifyServerCertificates(bArr, str, str2);
        } catch (IllegalArgumentException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (KeyStoreException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }

    public static void addTestRootCertificate(byte[] bArr) throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        X509Util.addTestRootCertificate(bArr);
    }

    public static void clearTestRootCertificates() throws NoSuchAlgorithmException, CertificateException, KeyStoreException {
        X509Util.clearTestRootCertificates();
    }

    private static String getNetworkCountryIso(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkCountryIso();
    }

    private static String getNetworkOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperator();
    }

    private static String getSimOperator(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getSimOperator();
    }

    private static boolean getIsRoaming(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isRoaming();
    }
}
