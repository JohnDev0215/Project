package com.qtxa.monitor.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class WifiStateReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ScheduledExecutorService f86a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<String, String> f87b;
    private ArrayList<String> d;
    private String e;
    private ArrayList<String> c = new ArrayList<>();
    private long f = 0;
    private boolean g = false;
    private HashMap<String, Long> h = new HashMap<>();
    private HashMap<String, Boolean> i = new HashMap<>();

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f88a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f89b;
        final /* synthetic */ String c;

        a(String str, long j, String str2) {
            this.f88a = str;
            this.f89b = j;
            this.c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int iWaitFor = Runtime.getRuntime().exec("ping -c 1 -w 10 " + this.f88a).waitFor();
                com.qtxa.monitor.c.a.a("WifiStateReceiver", "pingIP " + this.f88a + " res: " + iWaitFor);
                if (iWaitFor == 0) {
                    com.qtxa.monitor.wifi.a aVar = new com.qtxa.monitor.wifi.a(this.f89b, this.c, this.f88a, true, "Connected", 0L);
                    WifiStateReceiver.this.i.put(this.c, Boolean.FALSE);
                    com.qtxa.monitor.wifi.b.c().a(aVar);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    class b implements Runnable {
        b(WifiStateReceiver wifiStateReceiver) {
            new WeakReference(wifiStateReceiver);
        }

        @Override // java.lang.Runnable
        public void run() {
            com.qtxa.monitor.c.a.a("WifiStateReceiver", "ScanScheduledExecutor loop start.");
            try {
                WifiStateReceiver.this.d = WifiStateReceiver.this.m();
            } catch (Exception e) {
                e.printStackTrace();
                com.qtxa.monitor.c.a.a("WifiStateReceiver", "Exception: " + e.getMessage());
            }
            WifiStateReceiver wifiStateReceiver = WifiStateReceiver.this;
            wifiStateReceiver.o(wifiStateReceiver.c, "mOriginalMacList");
            WifiStateReceiver wifiStateReceiver2 = WifiStateReceiver.this;
            wifiStateReceiver2.o(wifiStateReceiver2.d, "newConnectMacList");
            ArrayList arrayList = new ArrayList(WifiStateReceiver.this.d);
            arrayList.removeAll(WifiStateReceiver.this.c);
            WifiStateReceiver.this.o(arrayList, "newAddMacList");
            try {
                if (arrayList.size() > 0) {
                    for (int i = 0; i < arrayList.size(); i++) {
                        String str = (String) arrayList.get(i);
                        String str2 = (String) WifiStateReceiver.this.f87b.get(str);
                        try {
                            int iWaitFor = Runtime.getRuntime().exec("ping -c 1 -w 10 " + str2).waitFor();
                            com.qtxa.monitor.c.a.a("WifiStateReceiver", "pingIP " + str2 + " res: " + iWaitFor);
                            if (iWaitFor == 0) {
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                WifiStateReceiver.this.h.put(str, Long.valueOf(jCurrentTimeMillis));
                                com.qtxa.monitor.wifi.a aVar = new com.qtxa.monitor.wifi.a(jCurrentTimeMillis, str, str2, true, "Connected", 0L);
                                WifiStateReceiver.this.i.put(str, Boolean.FALSE);
                                com.qtxa.monitor.wifi.b.c().a(aVar);
                            } else {
                                Iterator it = WifiStateReceiver.this.d.iterator();
                                while (it.hasNext()) {
                                    if (((String) it.next()).equals(str)) {
                                        it.remove();
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                } else {
                    Iterator it2 = WifiStateReceiver.this.d.iterator();
                    while (it2.hasNext()) {
                        String str3 = (String) it2.next();
                        String str4 = (String) WifiStateReceiver.this.f87b.get(str3);
                        try {
                            if (Runtime.getRuntime().exec("ping -c 1 -w 10 " + str4).waitFor() != 0) {
                                it2.remove();
                                com.qtxa.monitor.c.a.a("WifiStateReceiver", " ping ip " + str4 + " error. newConnectMacList remove :" + str3);
                            }
                        } catch (Exception e3) {
                            com.qtxa.monitor.c.a.a("WifiStateReceiver", "Exception 1: " + e3.getMessage());
                            e3.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                WifiStateReceiver.this.o(WifiStateReceiver.this.c, "mOriginalMacList remove");
                WifiStateReceiver.this.o(WifiStateReceiver.this.d, "newConnectMacList remove");
                ArrayList arrayList2 = new ArrayList(WifiStateReceiver.this.c);
                arrayList2.removeAll(WifiStateReceiver.this.d);
                WifiStateReceiver.this.o(arrayList2, "lostMacList remove");
                if (arrayList2.size() > 0) {
                    for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                        String str5 = (String) arrayList2.get(i2);
                        if (WifiStateReceiver.this.i.get(str5) == null || !((Boolean) WifiStateReceiver.this.i.get(str5)).booleanValue()) {
                            long jCurrentTimeMillis2 = System.currentTimeMillis();
                            long jLongValue = ((Long) WifiStateReceiver.this.h.get(str5)).longValue();
                            WifiStateReceiver.this.h.remove(str5);
                            com.qtxa.monitor.wifi.b.c().a(new com.qtxa.monitor.wifi.a(System.currentTimeMillis(), str5, null, true, "Disconnected", jCurrentTimeMillis2 > jLongValue ? jCurrentTimeMillis2 - jLongValue : 0L));
                            WifiStateReceiver.this.i.put(str5, Boolean.TRUE);
                        }
                    }
                }
                WifiStateReceiver.this.o(WifiStateReceiver.this.d, "new mOriginalMacList");
                WifiStateReceiver.this.c = new ArrayList(WifiStateReceiver.this.d);
            } catch (Exception e4) {
                e4.printStackTrace();
                com.qtxa.monitor.c.a.a("WifiStateReceiver", "ScanScheduledExecutor " + e4.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void j() {
        l();
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.f86a = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        if (scheduledExecutorServiceNewSingleThreadScheduledExecutor != null) {
            com.qtxa.monitor.c.a.a("WifiStateReceiver", "ScheduledmService beginExecutorScan");
            this.f86a.scheduleAtFixedRate(new b(this), 5L, 10L, TimeUnit.SECONDS);
        }
    }

    public static boolean k(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
            String ssid = connectionInfo.getSSID();
            if (ssid != null && ssid.length() > 2) {
                if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                    ssid = ssid.substring(1, ssid.length() - 1);
                }
                if (configuredNetworks != null && configuredNetworks.size() > 0) {
                    for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                        if (wifiConfiguration != null && wifiConfiguration.status == 0) {
                            String strSubstring = null;
                            if (wifiConfiguration.SSID != null && !"".equals(wifiConfiguration.SSID)) {
                                strSubstring = wifiConfiguration.SSID;
                                if (wifiConfiguration.SSID.startsWith("\"") && wifiConfiguration.SSID.endsWith("\"")) {
                                    strSubstring = wifiConfiguration.SSID.substring(1, wifiConfiguration.SSID.length() - 1);
                                }
                            }
                            if (ssid.equalsIgnoreCase(strSubstring)) {
                                return !wifiConfiguration.allowedKeyManagement.get(0);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void l() {
        if (this.f86a != null) {
            com.qtxa.monitor.c.a.a("WifiStateReceiver", "ScheduledmService endExecutorScan");
            this.f86a.shutdownNow();
        }
        this.f86a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> m() {
        BufferedReader bufferedReader;
        String[] strArrSplit;
        ArrayList<String> arrayList = new ArrayList<>();
        BufferedReader bufferedReader2 = null;
        try {
            this.f87b = new HashMap<>();
            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
        } catch (Throwable unused) {
        }
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (!line.contains("HW address") && (strArrSplit = line.split(" +")) != null && strArrSplit.length >= 6) {
                    String str = strArrSplit[5];
                    if (str.equals(this.e)) {
                        String str2 = strArrSplit[3];
                        String str3 = strArrSplit[0];
                        if (!str2.contains("00:00:00:00:00:00")) {
                            this.f87b.put(str2, str3);
                            com.qtxa.monitor.c.a.a("WifiStateReceiver", " get iface: " + str + " mac: " + str2);
                            arrayList.add(str2);
                        }
                    }
                }
            } catch (Throwable unused2) {
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return arrayList;
        }
        bufferedReader.close();
        return arrayList;
    }

    private void n(long j, String str, String str2) {
        Executors.newSingleThreadScheduledExecutor().submit(new a(str2, j, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(ArrayList<String> arrayList, String str) {
        if (arrayList == null || arrayList.size() <= 0) {
            com.qtxa.monitor.c.a.a("WifiStateReceiver", "showIpMacList " + str + " : is empty.");
            return;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            com.qtxa.monitor.c.a.a("WifiStateReceiver", "showIpMacList " + str + " : " + it.next());
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("wifi_state", 1);
            if (intExtra == 1) {
                System.currentTimeMillis();
                return;
            }
            if (intExtra == 3) {
                com.qtxa.monitor.c.a.a("WifiStateReceiver", "NotifyWifiStateChange: " + new d(0, false, false, null, System.currentTimeMillis(), null, false));
                return;
            }
            return;
        }
        if (intent.getAction().equals("android.net.wifi.STATE_CHANGE")) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            if (networkInfo.getState() != NetworkInfo.State.DISCONNECTED) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    String ssid = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
                    this.f = System.currentTimeMillis();
                    this.g = true;
                    com.qtxa.monitor.wifi.b.c().b(new c(ssid, k(context), "Connected", this.f, 0L));
                    return;
                }
                return;
            }
            if (this.g) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = this.f;
                long j2 = jCurrentTimeMillis > j ? jCurrentTimeMillis - j : 0L;
                this.g = false;
                com.qtxa.monitor.wifi.b.c().b(new c(null, false, "Disconnected", jCurrentTimeMillis, j2));
                return;
            }
            return;
        }
        if (intent.getAction().equals("android.net.wifi.WIFI_AP_STATE_CHANGED")) {
            try {
                int intExtra2 = intent.getIntExtra("wifi_state", 0);
                this.e = intent.getStringExtra("wifi_ap_interface_name");
                com.qtxa.monitor.c.a.a("WifiStateReceiver", "WIFI_AP_STATE_CHANGED " + intExtra2);
                if (intExtra2 == 13) {
                    ArrayList<String> arrayListM = m();
                    this.c = arrayListM;
                    o(arrayListM, "mOriginalMacList first");
                    if (this.c.size() > 0) {
                        for (int i = 0; i < this.c.size(); i++) {
                            String str = this.c.get(i);
                            String str2 = this.f87b.get(str);
                            long jCurrentTimeMillis2 = System.currentTimeMillis();
                            this.h.put(str, Long.valueOf(jCurrentTimeMillis2));
                            n(jCurrentTimeMillis2, str, str2);
                        }
                    }
                    j();
                    return;
                }
                if (intExtra2 == 11) {
                    l();
                    this.i.clear();
                    if (this.h.size() > 0) {
                        for (String str3 : this.h.keySet()) {
                            long jLongValue = this.h.get(str3).longValue();
                            long jCurrentTimeMillis3 = System.currentTimeMillis();
                            long j3 = jCurrentTimeMillis3 > jLongValue ? jCurrentTimeMillis3 - jLongValue : 0L;
                            this.h.remove(str3);
                            com.qtxa.monitor.wifi.b.c().a(new com.qtxa.monitor.wifi.a(System.currentTimeMillis(), str3, null, false, "Disconnected", j3));
                        }
                    }
                }
            } catch (Throwable th) {
                com.qtxa.monitor.c.a.a("WifiStateReceiver", "WIFI_AP_STATE_CHANGED 13 first error: " + th.getMessage());
                th.printStackTrace();
            }
        }
    }
}
