package org.chromium.chrome.browser.omnibox.geo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.Process;
import android.os.SystemClock;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import defpackage.c6;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.omnibox.geo.f;

/* JADX INFO: compiled from: PlatformNetworksManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    public static f.b a(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return f.b.e;
        }
        String ssid = wifiInfo.getSSID();
        if (ssid == null || "<unknown ssid>".equals(ssid)) {
            ssid = null;
        } else if (ssid.startsWith("\"") && ssid.endsWith("\"") && ssid.length() > 2) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        return new f.b(ssid, wifiInfo.getBSSID(), null, Long.valueOf(System.currentTimeMillis()));
    }

    public static f.a b(Context context, TelephonyManager telephonyManager) {
        CellInfo cellInfo;
        f.a aVarD;
        int i;
        if (c6.a(Process.myPid(), Process.myUid(), context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo == null) {
                cellInfo = null;
                aVarD = d(cellInfo, SystemClock.elapsedRealtime(), System.currentTimeMillis());
            } else {
                int i2 = 0;
                cellInfo = null;
                for (int i3 = 0; i3 < allCellInfo.size(); i3++) {
                    CellInfo cellInfo2 = allCellInfo.get(i3);
                    if (cellInfo2.isRegistered()) {
                        i2++;
                        if (i2 > 1) {
                            cellInfo = null;
                            break;
                        }
                        cellInfo = cellInfo2;
                    }
                }
                aVarD = d(cellInfo, SystemClock.elapsedRealtime(), System.currentTimeMillis());
            }
        } else {
            aVarD = f.a.k;
        }
        if (aVarD == null || !((i = aVarD.a) == 0 || i == 1)) {
            return aVarD;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.chrome.browser.omnibox.geo.f.b c(android.content.Context r7) {
        /*
            int r0 = android.os.Process.myPid()
            int r1 = android.os.Process.myUid()
            java.lang.String r2 = "android.permission.ACCESS_FINE_LOCATION"
            int r0 = defpackage.c6.a(r0, r1, r7, r2)
            r1 = 0
            r3 = 1
            if (r0 != 0) goto L14
            r0 = r3
            goto L15
        L14:
            r0 = r1
        L15:
            if (r0 == 0) goto L2e
            int r0 = android.os.Process.myPid()
            int r4 = android.os.Process.myUid()
            java.lang.String r5 = "android.permission.ACCESS_WIFI_STATE"
            int r0 = defpackage.c6.a(r0, r4, r7, r5)
            if (r0 != 0) goto L29
            r0 = r3
            goto L2a
        L29:
            r0 = r1
        L2a:
            if (r0 == 0) goto L2e
            r0 = r3
            goto L2f
        L2e:
            r0 = r1
        L2f:
            r4 = 0
            if (r0 == 0) goto L80
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L6b
            android.content.Context r7 = r7.getApplicationContext()
            java.lang.String r0 = "connectivity"
            java.lang.Object r7 = r7.getSystemService(r0)
            android.net.ConnectivityManager r7 = (android.net.ConnectivityManager) r7
            android.net.Network[] r0 = r7.getAllNetworks()
            int r2 = r0.length
        L49:
            if (r1 >= r2) goto L69
            r5 = r0[r1]
            android.net.NetworkCapabilities r5 = r7.getNetworkCapabilities(r5)
            if (r5 == 0) goto L66
            boolean r6 = r5.hasTransport(r3)
            if (r6 == 0) goto L66
            android.net.TransportInfo r5 = r5.getTransportInfo()
            if (r5 == 0) goto L66
            boolean r6 = r5 instanceof android.net.wifi.WifiInfo
            if (r6 == 0) goto L66
            android.net.wifi.WifiInfo r5 = (android.net.wifi.WifiInfo) r5
            goto L7b
        L66:
            int r1 = r1 + 1
            goto L49
        L69:
            r5 = r4
            goto L7b
        L6b:
            android.content.Context r7 = r7.getApplicationContext()
            java.lang.String r0 = "wifi"
            java.lang.Object r7 = r7.getSystemService(r0)
            android.net.wifi.WifiManager r7 = (android.net.wifi.WifiManager) r7
            android.net.wifi.WifiInfo r5 = r7.getConnectionInfo()
        L7b:
            org.chromium.chrome.browser.omnibox.geo.f$b r7 = a(r5)
            goto Lb2
        L80:
            int r0 = android.os.Process.myPid()
            int r5 = android.os.Process.myUid()
            int r0 = defpackage.c6.a(r0, r5, r7, r2)
            if (r0 != 0) goto L8f
            r1 = r3
        L8f:
            org.chromium.chrome.browser.omnibox.geo.f$b r0 = org.chromium.chrome.browser.omnibox.geo.f.b.e
            if (r1 == 0) goto Lb1
            android.content.Context r7 = r7.getApplicationContext()
            android.content.IntentFilter r1 = new android.content.IntentFilter
            java.lang.String r2 = "android.net.wifi.STATE_CHANGE"
            r1.<init>(r2)
            android.content.Intent r7 = defpackage.xz.g(r7, r4, r1)
            if (r7 == 0) goto Lb1
            java.lang.String r0 = "wifiInfo"
            android.os.Parcelable r7 = r7.getParcelableExtra(r0)
            android.net.wifi.WifiInfo r7 = (android.net.wifi.WifiInfo) r7
            org.chromium.chrome.browser.omnibox.geo.f$b r7 = a(r7)
            goto Lb2
        Lb1:
            r7 = r0
        Lb2:
            java.lang.String r0 = r7.b
            if (r0 != 0) goto Lb7
            return r4
        Lb7:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.geo.e.c(android.content.Context):org.chromium.chrome.browser.omnibox.geo.f$b");
    }

    public static f.a d(CellInfo cellInfo, long j, long j2) {
        if (cellInfo == null) {
            return f.a.j;
        }
        long millis = j2 - (j - TimeUnit.NANOSECONDS.toMillis(cellInfo.getTimeStamp()));
        if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity = ((CellInfoCdma) cellInfo).getCellIdentity();
            f.a.C0100a c0100aA = f.a.a(2);
            c0100aA.b = Integer.valueOf(cellIdentity.getBasestationId());
            c0100aA.c = Integer.valueOf(cellIdentity.getNetworkId());
            c0100aA.e = Integer.valueOf(cellIdentity.getSystemId());
            c0100aA.i = Long.valueOf(millis);
            return c0100aA.a();
        }
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity2 = ((CellInfoGsm) cellInfo).getCellIdentity();
            f.a.C0100a c0100aA2 = f.a.a(3);
            c0100aA2.b = Integer.valueOf(cellIdentity2.getCid());
            c0100aA2.c = Integer.valueOf(cellIdentity2.getLac());
            c0100aA2.d = Integer.valueOf(cellIdentity2.getMcc());
            c0100aA2.e = Integer.valueOf(cellIdentity2.getMnc());
            c0100aA2.i = Long.valueOf(millis);
            return c0100aA2.a();
        }
        if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            f.a.C0100a c0100aA3 = f.a.a(4);
            c0100aA3.b = Integer.valueOf(cellIdentity3.getCi());
            c0100aA3.d = Integer.valueOf(cellIdentity3.getMcc());
            c0100aA3.e = Integer.valueOf(cellIdentity3.getMnc());
            c0100aA3.g = Integer.valueOf(cellIdentity3.getPci());
            c0100aA3.h = Integer.valueOf(cellIdentity3.getTac());
            c0100aA3.i = Long.valueOf(millis);
            return c0100aA3.a();
        }
        if (!(cellInfo instanceof CellInfoWcdma)) {
            return f.a.j;
        }
        CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
        f.a.C0100a c0100aA4 = f.a.a(5);
        c0100aA4.b = Integer.valueOf(cellIdentity4.getCid());
        c0100aA4.c = Integer.valueOf(cellIdentity4.getLac());
        c0100aA4.d = Integer.valueOf(cellIdentity4.getMcc());
        c0100aA4.e = Integer.valueOf(cellIdentity4.getMnc());
        c0100aA4.f = Integer.valueOf(cellIdentity4.getPsc());
        c0100aA4.i = Long.valueOf(millis);
        return c0100aA4.a();
    }
}
