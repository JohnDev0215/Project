package c.a.d.s;

import android.annotation.TargetApi;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.byd.trafficmonitor.model.SerialDrawable;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: TrafficUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f3690a;

    static {
        ArrayList arrayList = new ArrayList();
        f3690a = arrayList;
        arrayList.add("com.byd.ota");
        arrayList.add("com.byd.vrassistant");
        arrayList.add("com.byd.trafficmonitor");
    }

    public static String a(float f2) {
        return new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ENGLISH)).format(f2);
    }

    public static String b(long j) {
        float f2 = (j * 1.0f) / 1024.0f;
        if (f2 < 1024.0f) {
            if (f2 <= 1.0f || f2 >= 1024.0f) {
                return j + " B";
            }
            return a(f2) + " KB";
        }
        float f3 = (f2 * 1.0f) / 1024.0f;
        if (f3 >= 1024.0f) {
            return a((f3 * 1.0f) / 1024.0f) + " GB";
        }
        return a(f3) + " MB";
    }

    @TargetApi(29)
    public static ArrayList<TrafficInfo> c(Context context, long j) {
        String[] strArr;
        String str;
        int i2;
        i iVar;
        int i3;
        PackageManager packageManager;
        ArrayList<TrafficInfo> arrayList;
        PackageInfo packageInfo;
        long rxBytes;
        long txBytes;
        long j2 = j;
        Log.i("TrafficUtils", "---------------- getTrafficInfo start -------------------");
        Log.i("TrafficUtils", "---------------- queryDetailsForUid -------------------");
        PackageManager packageManager2 = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager2.getInstalledPackages(4096);
        NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getSystemService("netstats");
        i iVarC = i.c(context);
        ArrayList<TrafficInfo> arrayList2 = new ArrayList<>();
        for (PackageInfo packageInfo2 : installedPackages) {
            int i4 = packageInfo2.applicationInfo.uid;
            String str2 = packageInfo2.packageName;
            if (f3690a.contains(str2)) {
                j2 = j;
            } else if (i4 != 1000) {
                int i5 = 0;
                boolean z = (packageInfo2.applicationInfo.flags & 1) > 0;
                String[] strArr2 = packageInfo2.requestedPermissions;
                if (strArr2 != null && strArr2.length > 0) {
                    ArrayList<TrafficInfo> arrayList3 = arrayList2;
                    int length = strArr2.length;
                    while (i5 < length) {
                        int i6 = i5;
                        if ("android.permission.INTERNET".equals(strArr2[i5])) {
                            TrafficInfo trafficInfo = new TrafficInfo();
                            StringBuilder sb = new StringBuilder();
                            strArr = strArr2;
                            sb.append("uid:");
                            sb.append(i4);
                            BydLogHelper.logV("TrafficUtils", sb.toString());
                            trafficInfo.w(packageInfo2.applicationInfo.uid);
                            trafficInfo.q(packageInfo2.applicationInfo.loadLabel(packageManager2).toString());
                            new SerialDrawable(packageInfo2.applicationInfo.loadIcon(packageManager2));
                            boolean z2 = !iVarC.d(i4);
                            trafficInfo.r(z2 ? TrafficInfo.f4414b : TrafficInfo.f4415c);
                            try {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("packageName:");
                                sb2.append(str2);
                                sb2.append(";uid : ");
                                sb2.append(i4);
                                sb2.append(";start time:");
                                sb2.append(j2);
                                sb2.append(";system time :");
                                iVar = iVarC;
                                i3 = length;
                                try {
                                    sb2.append(System.currentTimeMillis());
                                    BydLogHelper.logV("TrafficUtils", sb2.toString());
                                    Log.i("TrafficUtils", "packageName:" + str2 + ";uid : " + i4 + ";start time:" + j2 + ";system time :" + System.currentTimeMillis());
                                    str = str2;
                                    PackageInfo packageInfo3 = packageInfo2;
                                    i2 = i4;
                                    ArrayList<TrafficInfo> arrayList4 = arrayList3;
                                    packageManager = packageManager2;
                                    try {
                                        NetworkStats networkStatsQueryDetailsForUid = networkStatsManager.queryDetailsForUid(0, null, j, System.currentTimeMillis(), i2);
                                        rxBytes = 0;
                                        txBytes = 0;
                                        do {
                                            NetworkStats.Bucket bucket = new NetworkStats.Bucket();
                                            networkStatsQueryDetailsForUid.getNextBucket(bucket);
                                            rxBytes += bucket.getRxBytes();
                                            txBytes += bucket.getTxBytes();
                                        } while (networkStatsQueryDetailsForUid.hasNextBucket());
                                        trafficInfo.u(txBytes);
                                        trafficInfo.t(rxBytes);
                                        trafficInfo.s(z ? TrafficInfo.f4416d : TrafficInfo.f4417e);
                                        packageInfo = packageInfo3;
                                        try {
                                            trafficInfo.v(packageInfo.packageName);
                                            arrayList = arrayList4;
                                        } catch (SecurityException e2) {
                                            e = e2;
                                            arrayList = arrayList4;
                                        }
                                    } catch (SecurityException e3) {
                                        e = e3;
                                        arrayList = arrayList4;
                                        packageInfo = packageInfo3;
                                    }
                                    try {
                                        arrayList.add(trafficInfo);
                                        BydLogHelper.logV("TrafficUtils", "rx:" + rxBytes + " , tx =" + txBytes + " isMobileTraffic =" + z2);
                                    } catch (SecurityException e4) {
                                        e = e4;
                                        e.printStackTrace();
                                    }
                                } catch (SecurityException e5) {
                                    e = e5;
                                    arrayList = arrayList3;
                                    str = str2;
                                    packageInfo = packageInfo2;
                                    i2 = i4;
                                    packageManager = packageManager2;
                                }
                            } catch (SecurityException e6) {
                                e = e6;
                                str = str2;
                                i2 = i4;
                                iVar = iVarC;
                                i3 = length;
                                packageManager = packageManager2;
                                arrayList = arrayList3;
                                packageInfo = packageInfo2;
                            }
                        } else {
                            strArr = strArr2;
                            str = str2;
                            i2 = i4;
                            iVar = iVarC;
                            i3 = length;
                            packageManager = packageManager2;
                            arrayList = arrayList3;
                            packageInfo = packageInfo2;
                        }
                        i5 = i6 + 1;
                        packageInfo2 = packageInfo;
                        arrayList3 = arrayList;
                        str2 = str;
                        strArr2 = strArr;
                        iVarC = iVar;
                        length = i3;
                        packageManager2 = packageManager;
                        i4 = i2;
                        j2 = j;
                    }
                    arrayList2 = arrayList3;
                }
                j2 = j;
                iVarC = iVarC;
                packageManager2 = packageManager2;
            }
        }
        return arrayList2;
    }

    public static String d(String str) {
        float f2 = (Integer.parseInt(str) * 1.0f) / 1024.0f;
        if (f2 >= 1024.0f) {
            return a((f2 * 1.0f) / 1024.0f) + " GB";
        }
        return a(f2) + " MB";
    }
}
