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
import android.net.TransportInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.security.NetworkSecurityPolicy;
import android.telephony.TelephonyManager;
import android.util.Log;
import defpackage.a93;
import defpackage.c6;
import defpackage.cu3;
import defpackage.fa1;
import defpackage.uv;
import defpackage.xz;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketImpl;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.annotations.CalledByNativeUnchecked;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidNetworkLibrary {
    public static Boolean a;
    public static Boolean b;

    public static class a extends Socket {

        /* JADX INFO: renamed from: org.chromium.net.AndroidNetworkLibrary$a$a, reason: collision with other inner class name */
        public static class C0155a extends SocketImpl {
            public C0155a(FileDescriptor fileDescriptor) {
                ((SocketImpl) this).fd = fileDescriptor;
            }

            @Override // java.net.SocketImpl
            public final void accept(SocketImpl socketImpl) {
                throw new uv("accept not implemented");
            }

            @Override // java.net.SocketImpl
            public final int available() {
                throw new uv("accept not implemented");
            }

            @Override // java.net.SocketImpl
            public final void bind(InetAddress inetAddress, int i) {
                throw new uv("accept not implemented");
            }

            @Override // java.net.SocketImpl
            public final void connect(InetAddress inetAddress, int i) {
                throw new uv("connect not implemented");
            }

            @Override // java.net.SocketImpl
            public final InputStream getInputStream() {
                throw new uv("getInputStream not implemented");
            }

            @Override // java.net.SocketOptions
            public final Object getOption(int i) {
                throw new uv("getOption not implemented");
            }

            @Override // java.net.SocketImpl
            public final OutputStream getOutputStream() {
                throw new uv("getOutputStream not implemented");
            }

            @Override // java.net.SocketImpl
            public final void listen(int i) {
                throw new uv("listen not implemented");
            }

            @Override // java.net.SocketImpl
            public final void sendUrgentData(int i) {
                throw new uv("sendUrgentData not implemented");
            }

            @Override // java.net.SocketOptions
            public final void setOption(int i, Object obj) {
                throw new uv("setOption not implemented");
            }

            @Override // java.net.SocketImpl
            public final void connect(SocketAddress socketAddress, int i) {
                throw new uv("connect not implemented");
            }

            @Override // java.net.SocketImpl
            public final void connect(String str, int i) {
                throw new uv("connect not implemented");
            }

            @Override // java.net.SocketImpl
            public final void close() {
            }

            @Override // java.net.SocketImpl
            public final void create(boolean z) {
            }
        }

        public a(FileDescriptor fileDescriptor) {
            super(new C0155a(fileDescriptor));
        }
    }

    public static DnsStatus a(Network network) {
        ConnectivityManager connectivityManager;
        if (a == null) {
            a = Boolean.valueOf(c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if (!a.booleanValue() || (connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity")) == null) {
            return null;
        }
        if (network == null) {
            network = connectivityManager.getActiveNetwork();
        }
        if (network == null) {
            return null;
        }
        try {
            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
            if (linkProperties == null) {
                return null;
            }
            return new DnsStatus(linkProperties.getDnsServers(), linkProperties.isPrivateDnsActive(), linkProperties.getPrivateDnsServerName(), linkProperties.getDomains());
        } catch (RuntimeException unused) {
            return null;
        }
    }

    @CalledByNativeUnchecked
    public static void addTestRootCertificate(byte[] bArr) {
        X509Certificate x509CertificateB = cu3.b(bArr);
        synchronized (cu3.j) {
            cu3.e();
            cu3.e.setCertificateEntry("root_cert_" + Integer.toString(cu3.e.size()), x509CertificateB);
            cu3.e();
            cu3.d = cu3.c(cu3.e);
        }
    }

    public static WifiInfo b() {
        TransportInfo transportInfo;
        if (!c()) {
            Intent intentG = xz.g(xz.a, null, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            if (intentG != null) {
                return (WifiInfo) intentG.getParcelableExtra("wifiInfo");
            }
            return null;
        }
        if (Build.VERSION.SDK_INT < 31) {
            return ((WifiManager) xz.a.getSystemService("wifi")).getConnectionInfo();
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities != null && networkCapabilities.hasTransport(1) && (transportInfo = networkCapabilities.getTransportInfo()) != null && (transportInfo instanceof WifiInfo)) {
                return (WifiInfo) transportInfo;
            }
        }
        return null;
    }

    public static boolean c() {
        if (b == null) {
            b = Boolean.valueOf(c6.a(Process.myPid(), Process.myUid(), xz.a, "android.permission.ACCESS_WIFI_STATE") == 0);
        }
        return b.booleanValue();
    }

    @CalledByNativeUnchecked
    public static void clearTestRootCertificates() {
        synchronized (cu3.j) {
            cu3.e();
            try {
                cu3.e.load(null);
                cu3.e();
                cu3.d = cu3.c(cu3.e);
            } catch (IOException unused) {
            }
        }
    }

    @CalledByNative
    public static DnsStatus getCurrentDnsStatus() {
        return a(null);
    }

    @CalledByNative
    public static DnsStatus getDnsStatusForNetwork(long j) {
        try {
            return a(Network.fromNetworkHandle(j));
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    @CalledByNative
    public static boolean getIsCaptivePortal() {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
        return (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(17)) ? false : true;
    }

    @CalledByNative
    public static boolean getIsRoaming() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) xz.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isRoaming();
    }

    @CalledByNative
    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo." + str);
    }

    @CalledByNative
    public static String getNetworkOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) xz.a.getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperator();
    }

    @CalledByNative
    public static byte[][] getUserAddedRoots() {
        CertificateFactory certificateFactory = cu3.a;
        ArrayList arrayList = new ArrayList();
        Object obj = cu3.j;
        synchronized (obj) {
            try {
                synchronized (obj) {
                    cu3.d();
                }
            } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
                return new byte[0][];
            }
        }
        KeyStore keyStore = cu3.f;
        if (keyStore == null) {
            return new byte[0][];
        }
        try {
            Enumeration<String> enumerationAliases = keyStore.aliases();
            while (enumerationAliases.hasMoreElements()) {
                String strNextElement = enumerationAliases.nextElement();
                if (strNextElement.startsWith("user:")) {
                    try {
                        Certificate certificate = cu3.f.getCertificate(strNextElement);
                        if (certificate instanceof X509Certificate) {
                            arrayList.add(((X509Certificate) certificate).getEncoded());
                        } else {
                            Log.w("cr_X509Util", "alias: " + strNextElement + " is not a X509 Cert, skipping");
                        }
                    } catch (KeyStoreException e) {
                        fa1.c("X509Util", "Error reading cert with alias %s, error: %s", strNextElement, e);
                    } catch (CertificateEncodingException e2) {
                        fa1.c("X509Util", "Error encoding cert with alias %s, error: %s", strNextElement, e2);
                    }
                }
            }
            return (byte[][]) arrayList.toArray(new byte[0][]);
        } catch (KeyStoreException e3) {
            Log.e("cr_X509Util", "Error reading cert aliases: %s", e3);
            return new byte[0][];
        }
    }

    @CalledByNative
    public static String getWifiSSID() {
        String ssid;
        WifiInfo wifiInfoB = b();
        return (wifiInfoB == null || (ssid = wifiInfoB.getSSID()) == null || ssid.equals("<unknown ssid>")) ? "" : ssid;
    }

    @CalledByNative
    public static int getWifiSignalLevel(int i) {
        int intExtra;
        int iCalculateSignalLevel;
        Context context = xz.a;
        if (context == null || context.getContentResolver() == null) {
            return -1;
        }
        if (c()) {
            WifiInfo wifiInfoB = b();
            if (wifiInfoB == null) {
                return -1;
            }
            intExtra = wifiInfoB.getRssi();
        } else {
            try {
                Intent intentG = xz.g(xz.a, null, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
                if (intentG == null) {
                    return -1;
                }
                intExtra = intentG.getIntExtra("newRssi", Integer.MIN_VALUE);
            } catch (IllegalArgumentException unused) {
            }
        }
        if (intExtra != Integer.MIN_VALUE && (iCalculateSignalLevel = WifiManager.calculateSignalLevel(intExtra, i)) >= 0 && iCalculateSignalLevel < i) {
            return iCalculateSignalLevel;
        }
        return -1;
    }

    @CalledByNative
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

    @CalledByNative
    public static boolean isCleartextPermitted(String str) {
        try {
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
        } catch (IllegalArgumentException unused) {
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }
    }

    @CalledByNative
    public static boolean reportBadDefaultNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) xz.a.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        connectivityManager.reportNetworkConnectivity(null, false);
        return true;
    }

    @CalledByNative
    public static void setWifiEnabled(boolean z) {
        ((WifiManager) xz.a.getSystemService("wifi")).setWifiEnabled(z);
    }

    @CalledByNative
    public static void tagSocket(int i, int i2, int i3) throws IOException {
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(i3);
        }
        if (i2 != -1) {
            try {
                a93.a.invoke(null, Integer.valueOf(i2));
            } catch (IllegalAccessException e) {
                throw new uv("TrafficStats.setThreadStatsUid failed", e);
            } catch (InvocationTargetException e2) {
                throw new uv("TrafficStats.setThreadStatsUid failed", e2);
            }
        }
        ParcelFileDescriptor parcelFileDescriptorAdoptFd = ParcelFileDescriptor.adoptFd(i);
        a aVar = new a(parcelFileDescriptorAdoptFd.getFileDescriptor());
        TrafficStats.tagSocket(aVar);
        aVar.close();
        parcelFileDescriptorAdoptFd.detachFd();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(threadStatsTag);
        }
        if (i2 != -1) {
            try {
                a93.b.invoke(null, new Object[0]);
            } catch (IllegalAccessException e3) {
                throw new uv("TrafficStats.clearThreadStatsUid failed", e3);
            } catch (InvocationTargetException e4) {
                throw new uv("TrafficStats.clearThreadStatsUid failed", e4);
            }
        }
    }

    @CalledByNative
    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return cu3.h(bArr, str, str2);
        } catch (IllegalArgumentException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (KeyStoreException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
