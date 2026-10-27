package org.chromium.net;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.util.Log;
import defpackage.AbstractC0545bL;
import defpackage.AbstractC0824gL;
import defpackage.AbstractC1542tK;
import defpackage.AbstractC1623us;
import defpackage.C1185mt;
import defpackage.C1240nt;
import defpackage.C1405qt;
import defpackage.C1652vK;
import defpackage.C1872zK;
import defpackage.Is;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLConnection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AndroidNetworkLibrary {
    public static Boolean a;
    public static Boolean b;

    public static boolean a() {
        if (b == null) {
            b = Boolean.valueOf(AbstractC1623us.a(Is.a, "android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0);
        }
        return b.booleanValue();
    }

    public static void addTestRootCertificate(byte[] bArr) {
        AbstractC0824gL.d();
        X509Certificate x509CertificateB = AbstractC0824gL.b(bArr);
        synchronized (AbstractC0824gL.j) {
            AbstractC0824gL.e.setCertificateEntry("root_cert_" + Integer.toString(AbstractC0824gL.e.size()), x509CertificateB);
            AbstractC0824gL.d = AbstractC0824gL.c(AbstractC0824gL.e);
        }
    }

    public static void clearTestRootCertificates() {
        AbstractC0824gL.d();
        synchronized (AbstractC0824gL.j) {
            try {
                AbstractC0824gL.e.load(null);
                AbstractC0824gL.d = AbstractC0824gL.c(AbstractC0824gL.e);
            } catch (IOException unused) {
            }
        }
    }

    public static DnsStatus getDnsStatus(Network network) {
        ConnectivityManager connectivityManager;
        LinkProperties linkProperties;
        if (a == null) {
            a = Boolean.valueOf(AbstractC1623us.a(Is.a, "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0);
        }
        if (!a.booleanValue() || (connectivityManager = (ConnectivityManager) Is.a.getSystemService("connectivity")) == null) {
            return null;
        }
        if (network == null) {
            network = C1185mt.b(connectivityManager);
        }
        if (network == null || (linkProperties = connectivityManager.getLinkProperties(network)) == null) {
            return null;
        }
        List<InetAddress> dnsServers = linkProperties.getDnsServers();
        return Build.VERSION.SDK_INT >= 28 ? new DnsStatus(dnsServers, C1405qt.d(linkProperties), C1405qt.b(linkProperties)) : new DnsStatus(dnsServers, false, "");
    }

    public static boolean getIsCaptivePortal() {
        ConnectivityManager connectivityManager;
        Network networkB;
        NetworkCapabilities networkCapabilities;
        return (Build.VERSION.SDK_INT < 23 || (connectivityManager = (ConnectivityManager) Is.a.getSystemService("connectivity")) == null || (networkB = C1185mt.b(connectivityManager)) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(networkB)) == null || !networkCapabilities.hasCapability(17)) ? false : true;
    }

    public static boolean getIsRoaming() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) Is.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isRoaming();
    }

    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo." + str);
    }

    public static String getNetworkOperator() {
        C1872zK c1872zKA = C1872zK.a();
        if (c1872zKA.a == null) {
            TelephonyManager telephonyManagerB = C1872zK.b();
            if (telephonyManagerB == null) {
                return "";
            }
            c1872zKA.a = telephonyManagerB.getNetworkOperator();
        }
        return c1872zKA.a;
    }

    public static String getSimOperator() {
        C1872zK c1872zKA = C1872zK.a();
        if (c1872zKA.b == null) {
            TelephonyManager telephonyManagerB = C1872zK.b();
            if (telephonyManagerB == null) {
                return "";
            }
            c1872zKA.b = telephonyManagerB.getSimOperator();
        }
        return c1872zKA.b;
    }

    public static String getWifiSSID() {
        String ssid;
        WifiInfo connectionInfo = null;
        if (a()) {
            connectionInfo = ((WifiManager) Is.a.getSystemService("wifi")).getConnectionInfo();
        } else {
            Intent intentRegisterReceiver = Is.a.registerReceiver(null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (intentRegisterReceiver != null) {
                connectionInfo = (WifiInfo) intentRegisterReceiver.getParcelableExtra("wifiInfo");
            }
        }
        return (connectionInfo == null || (ssid = connectionInfo.getSSID()) == null || ssid.equals("<unknown ssid>")) ? "" : ssid;
    }

    public static int getWifiSignalLevel(int i) {
        int intExtra;
        int iCalculateSignalLevel;
        Context context = Is.a;
        if (context == null || context.getContentResolver() == null) {
            return -1;
        }
        if (a()) {
            WifiInfo connectionInfo = ((WifiManager) Is.a.getSystemService("wifi")).getConnectionInfo();
            if (connectionInfo == null) {
                return -1;
            }
            intExtra = connectionInfo.getRssi();
        } else {
            try {
                Intent intentRegisterReceiver = Is.a.registerReceiver(null, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
                if (intentRegisterReceiver == null) {
                    return -1;
                }
                intExtra = intentRegisterReceiver.getIntExtra("newRssi", Integer.MIN_VALUE);
            } catch (IllegalArgumentException unused) {
            }
        }
        if (intExtra != Integer.MIN_VALUE && (iCalculateSignalLevel = WifiManager.calculateSignalLevel(intExtra, i)) >= 0 && iCalculateSignalLevel < i) {
            return iCalculateSignalLevel;
        }
        return -1;
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
            Log.w("AndroidNetworkLibrary", "could not get network interfaces: " + e);
            return false;
        }
    }

    public static boolean isCleartextPermitted(String str) {
        int i = Build.VERSION.SDK_INT;
        boolean zC = true;
        try {
            if (i < 24) {
                if (i >= 23) {
                    zC = C1185mt.i();
                }
                return zC;
            }
            zC = C1240nt.c(str);
            return zC;
        } catch (IllegalArgumentException unused) {
            return i < 23 ? zC : C1185mt.i();
        }
    }

    public static void tagSocket(int i, int i2, int i3) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorAdoptFd;
        FileDescriptor fileDescriptor;
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(i3);
        }
        if (i2 != -1) {
            AbstractC0545bL.b(i2);
        }
        if (Build.VERSION.SDK_INT < 23) {
            parcelFileDescriptorAdoptFd = null;
            fileDescriptor = AbstractC1542tK.a(i);
        } else {
            parcelFileDescriptorAdoptFd = ParcelFileDescriptor.adoptFd(i);
            fileDescriptor = parcelFileDescriptorAdoptFd.getFileDescriptor();
        }
        C1652vK c1652vK = new C1652vK(fileDescriptor);
        TrafficStats.tagSocket(c1652vK);
        c1652vK.close();
        if (parcelFileDescriptorAdoptFd != null) {
            parcelFileDescriptorAdoptFd.detachFd();
        }
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(threadStatsTag);
        }
        if (i2 != -1) {
            AbstractC0545bL.a();
        }
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return AbstractC0824gL.h(bArr, str, str2);
        } catch (IllegalArgumentException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (KeyStoreException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
