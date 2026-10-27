package ts.car.diag.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SoftApCapability;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.SoftApInfo;
import android.net.wifi.WifiClient;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerExecutor;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class p extends o {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private WifiManager f1620b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ConnectivityManager f1621c;
    private b d;
    private Handler e;
    private List<WifiClient> f = new ArrayList();

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends ConnectivityManager.OnStartTetheringCallback {
        a(p pVar) {
        }

        public void onTetheringFailed() {
            super.onTetheringFailed();
            q.h("DIAG-WiFiImpl", "stratWifiAp:fail");
        }

        public void onTetheringStarted() {
            super.onTetheringStarted();
            q.h("DIAG-WiFiImpl", "stratWifiAp:ok");
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class b implements WifiManager.SoftApCallback {
        private b() {
        }

        public void onBlockedClientConnecting(WifiClient wifiClient, int i) {
        }

        public void onCapabilityChanged(SoftApCapability softApCapability) {
        }

        public void onConnectedClientsChanged(List<WifiClient> list) {
            p.this.f = list;
        }

        public void onInfoChanged(SoftApInfo softApInfo) {
        }

        public void onStateChanged(int i, int i2) {
        }

        /* synthetic */ b(p pVar, a aVar) {
            this();
        }
    }

    public p(Context context) {
    }

    private String q() {
        String macAddress;
        WifiInfo connectionInfo = this.f1620b.getConnectionInfo();
        if (connectionInfo != null) {
            macAddress = connectionInfo.getMacAddress();
        } else {
            q.h("DIAG-WiFiImpl", "mWifiManager.getConnectionInfo == null");
            macAddress = "";
        }
        q.i("DIAG-WiFiImpl", "macAddress " + macAddress);
        return macAddress;
    }

    private WifiConfiguration r(String str, String str2, boolean z, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        if (!z) {
            q.h("DIAG-WiFiImpl", "get not pwd config ");
            wifiConfiguration.allowedKeyManagement.set(0);
            return wifiConfiguration;
        }
        if (i == 2) {
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.wepKeys[0] = "\"" + str2 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        } else if (i == 3) {
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.hiddenSSID = true;
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.status = 2;
        } else {
            wifiConfiguration.allowedKeyManagement.set(0);
        }
        return wifiConfiguration;
    }

    private boolean t() {
        x();
        return this.f1620b.setWifiEnabled(false);
    }

    private boolean u() {
        o();
        if (this.f1620b.getWifiState() != 1) {
            q.h("DIAG-WiFiImpl", "Wifi state keep on.");
            return true;
        }
        boolean wifiEnabled = this.f1620b.setWifiEnabled(true);
        q.h("DIAG-WiFiImpl", "setClientPiontOn.");
        return wifiEnabled;
    }

    private boolean v() {
        x();
        if (this.f1620b.getWifiState() != 1) {
            q.h("DIAG-WiFiImpl", "Wifi state keep on.");
            return true;
        }
        boolean wifiEnabled = this.f1620b.setWifiEnabled(true);
        q.h("DIAG-WiFiImpl", "setDualModeOn.");
        return wifiEnabled;
    }

    private boolean w() {
        q.h("DIAG-WiFiImpl", "setWifiSwitchOn");
        byte bD = d();
        q.h("DIAG-WiFiImpl", "mode = " + ((int) bD));
        if (bD == 1) {
            return t();
        }
        if (bD == 2) {
            return u();
        }
        if (bD == 3) {
            return v();
        }
        q.h("DIAG-WiFiImpl", "current is close ，default setWifiSwitchOn setClientPiontOn ");
        return u();
    }

    private boolean x() {
        q.h("DIAG-WiFiImpl", "stratWifiAp");
        if (s()) {
            q.h("DIAG-WiFiImpl", "stratWifiAp -- Already opened");
            return true;
        }
        this.f1621c.startTethering(0, true, new a(this));
        return true;
    }

    @Override // ts.car.diag.manager.o
    public boolean b() {
        return this.f.size() > 0;
    }

    @Override // ts.car.diag.manager.o
    public String c() {
        String ssid;
        SoftApConfiguration softApConfiguration = this.f1620b.getSoftApConfiguration();
        if (softApConfiguration != null) {
            ssid = softApConfiguration.getSsid();
        } else {
            q.h("DIAG-WiFiImpl", "mWifiManager.getSoftApConfiguration == null");
            ssid = "";
        }
        q.i("DIAG-WiFiImpl", "readWifiApSsid: result " + ssid);
        return ssid;
    }

    @Override // ts.car.diag.manager.o
    public byte d() {
        q.h("DIAG-WiFiImpl", "readWifiMode");
        if (s() && this.f1620b.getWifiState() == 1) {
            q.h("DIAG-WiFiImpl", "WiFiMode is: acces point");
            return (byte) 1;
        }
        if (!s() && this.f1620b.getWifiState() == 3) {
            q.h("DIAG-WiFiImpl", "WiFiMode is: client point");
            return (byte) 2;
        }
        if (s() && this.f1620b.getWifiState() == 3) {
            q.h("DIAG-WiFiImpl", "WiFiMode is: dual mode");
            return (byte) 3;
        }
        q.b("DIAG-WiFiImpl", "WiFiMode is: INVALID");
        return (byte) -1;
    }

    @Override // ts.car.diag.manager.o
    public byte e() {
        q.h("DIAG-WiFiImpl", "readWifiOnOff");
        if (s()) {
            q.h("DIAG-WiFiImpl", "AP is ON");
            return (byte) 1;
        }
        int wifiState = this.f1620b.getWifiState();
        if (wifiState == 0 || wifiState == 1) {
            q.h("DIAG-WiFiImpl", "Wifi_SwitchModulePower : off");
            return (byte) 0;
        }
        if (wifiState == 2 || wifiState == 3) {
            q.h("DIAG-WiFiImpl", "Wifi_SwitchModulePower : on");
            return (byte) 1;
        }
        q.h("DIAG-WiFiImpl", "Wifi_SwitchModulePower : invalid");
        return (byte) -1;
    }

    @Override // ts.car.diag.manager.o
    public String f() {
        return null;
    }

    @Override // ts.car.diag.manager.o
    public String g() {
        String strQ = q();
        q.i("DIAG-WiFiImpl", "localWifiMac " + strQ);
        return strQ;
    }

    @Override // ts.car.diag.manager.o
    public byte h(String str) {
        q.i("DIAG-WiFiImpl", "setWifiApSsid: ssid " + str);
        boolean z = !this.f1620b.setSoftApConfiguration(new SoftApConfiguration.Builder(this.f1620b.getSoftApConfiguration()).setSsid(str).build());
        q.g("setWifiApSsid " + (z ? 1 : 0));
        return z ? (byte) 1 : (byte) 0;
    }

    @Override // ts.car.diag.manager.o
    public byte i(byte b2) {
        q.h("DIAG-WiFiImpl", "SetWiFiMode");
        int i = 0;
        if (b2 == -1) {
            o();
            this.f1620b.setWifiEnabled(false);
            q.h("DIAG-WiFiImpl", "SetWiFiMode :INVALID,result is:true");
        } else if (b2 == 1) {
            x();
            this.f1620b.setWifiEnabled(false);
            q.h("DIAG-WiFiImpl", "SetWiFiMode :ACCESPOINT,result is:true");
        } else {
            if (b2 != 2) {
                if (b2 == 3) {
                    x();
                    this.f1620b.setWifiEnabled(true);
                    q.h("DIAG-WiFiImpl", "SetWiFiMode :DUALMODE,result is:true");
                }
                return (i ^ 1) == true ? (byte) 1 : (byte) 0;
            }
            o();
            this.f1620b.setWifiEnabled(true);
            q.h("DIAG-WiFiImpl", "SetWiFiMode :CLIENTPOINT,result is:true");
        }
        i = 1;
        return (i ^ 1) == true ? (byte) 1 : (byte) 0;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // ts.car.diag.manager.o
    public byte j() {
        byte b2;
        q.h("DIAG-WiFiImpl", "wifiConnectionStatus start");
        ConnectivityManager connectivityManager = this.f1621c;
        if (connectivityManager == null) {
            q.b("DIAG-WiFiImpl", "wifiConnectionStatus mConnManager == null");
            return (byte) -1;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null) {
            q.b("DIAG-WiFiImpl", "wifiConnectionStatus info == null");
            return (byte) -1;
        }
        NetworkInfo.State state = networkInfo.getState();
        if (this.f1620b.getWifiState() != 3) {
            q.b("DIAG-WiFiImpl", "wifiConnectionStatus : invalid");
            return (byte) -1;
        }
        String string = state.toString();
        switch (string.hashCode()) {
            case -2087582999:
                b2 = !string.equals("CONNECTED") ? (byte) -1 : (byte) 1;
                break;
            case -1052098138:
                b2 = !string.equals("DISCONNECTING") ? (byte) -1 : (byte) 2;
                break;
            case -290559304:
                b2 = !string.equals("CONNECTING") ? (byte) -1 : (byte) 0;
                break;
            case 433141802:
                b2 = !string.equals("UNKNOWN") ? (byte) -1 : (byte) 4;
                break;
            case 935892539:
                b2 = !string.equals("DISCONNECTED") ? (byte) -1 : (byte) 3;
                break;
            case 1124965819:
                b2 = !string.equals("SUSPENDED") ? (byte) -1 : (byte) 5;
                break;
            default:
                b2 = -1;
                break;
        }
        if (b2 == 0) {
            q.h("DIAG-WiFiImpl", "wifiConnectionStatus : connecting");
            return (byte) 3;
        }
        if (b2 == 1) {
            q.h("DIAG-WiFiImpl", "wifiConnectionStatus : connect");
            return (byte) 1;
        }
        if (b2 == 2 || b2 == 3) {
            q.h("DIAG-WiFiImpl", "wifiConnectionStatus : disconnect");
            return (byte) 2;
        }
        q.h("DIAG-WiFiImpl", "wifiConnectionStatus : invalid");
        return (byte) -1;
    }

    @Override // ts.car.diag.manager.o
    public void k(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        this.f1620b = wifiManager;
        wifiManager.getConnectionInfo();
        this.f1621c = (ConnectivityManager) context.getSystemService("connectivity");
        this.d = new b(this, null);
        this.e = new Handler();
        this.f1620b.registerSoftApCallback(new HandlerExecutor(this.e), this.d);
    }

    @Override // ts.car.diag.manager.o
    public byte l(String str, String str2, String str3, int i) {
        q.i("DIAG-WiFiImpl", "WiFIStationAutoConenct : bssid =" + str + "ssid = " + str2 + " pwd " + str3 + " pwdType " + i);
        if (this.f1620b.getWifiState() != 1) {
            return !p(str2, str3, i) ? (byte) 1 : (byte) 0;
        }
        q.b("DIAG-WiFiImpl", "WiFIStationAutoConenct :  current wifi is disable ");
        return (byte) 1;
    }

    @Override // ts.car.diag.manager.o
    public byte m(Byte b2) {
        q.h("DIAG-WiFiImpl", "Wifi_SwitchModulePower");
        boolean wifiEnabled = false;
        if (b2.equals((byte) 1)) {
            q.h("DIAG-WiFiImpl", "open wifi");
            wifiEnabled = w();
            q.h("DIAG-WiFiImpl", "" + wifiEnabled);
        } else if (b2.equals((byte) 0)) {
            q.h("DIAG-WiFiImpl", "close wifi");
            boolean zO = o();
            if (this.f1620b.getWifiState() == 3) {
                wifiEnabled = this.f1620b.setWifiEnabled(false);
            } else {
                q.h("DIAG-WiFiImpl", "Wifi state keep off.");
                wifiEnabled = zO;
            }
            q.h("DIAG-WiFiImpl", "" + wifiEnabled);
        }
        return wifiEnabled ^ true ? (byte) 1 : (byte) 0;
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    public boolean o() {
        q.h("DIAG-WiFiImpl", "closeWifiAp");
        if (s()) {
            this.f1621c.stopTethering(0);
        }
        return true;
    }

    public boolean p(String str, String str2, int i) {
        String strTrim = str.trim();
        String strTrim2 = str2.trim();
        int iIndexOf = strTrim.indexOf("\u0000");
        if (iIndexOf >= 0) {
            strTrim = strTrim.substring(0, iIndexOf);
        }
        q.i("DIAG-WiFiImpl", "connectWifiPws curSSID = " + strTrim);
        WifiManager wifiManager = this.f1620b;
        wifiManager.disableNetwork(wifiManager.getConnectionInfo().getNetworkId());
        boolean zEnableNetwork = this.f1620b.enableNetwork(this.f1620b.addNetwork(r(strTrim, strTrim2, true, i)), true);
        q.h("DIAG-WiFiImpl", " connectWifiPws result " + zEnableNetwork);
        return zEnableNetwork;
    }

    public boolean s() {
        boolean zIsWifiApEnabled = this.f1620b.isWifiApEnabled();
        q.h("DIAG-WiFiImpl", "isWifiApEnabled :" + zIsWifiApEnabled);
        return zIsWifiApEnabled;
    }
}
