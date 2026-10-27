package x4;

import android.annotation.TargetApi;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.provider.Settings;
import com.byd.traffic.R$string;
import com.byd.trafficmonitor.model.TrafficInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TrafficUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<String> f9090a;

    static {
        ArrayList arrayList = new ArrayList();
        f9090a = arrayList;
        arrayList.add("com.byd.ota");
        arrayList.add("com.byd.vrassistant");
        arrayList.add("com.byd.trafficmonitor");
    }

    public static String a(float f8) {
        if (f8 % 1.0f != 0.0f) {
            return new DecimalFormat("#0.0").format(f8);
        }
        return ((int) f8) + "";
    }

    public static String b(PackageManager packageManager, int i8) {
        String[] packagesForUid = packageManager.getPackagesForUid(i8);
        if (packagesForUid == null) {
            return null;
        }
        for (String str : packagesForUid) {
            try {
                packageManager.getPackageInfo(str, 0);
                return str;
            } catch (PackageManager.NameNotFoundException e8) {
                BydLogHelper.logE("TrafficUtils", "getPackageByUid error :" + e8);
            }
        }
        return packageManager.getNameForUid(i8);
    }

    @TargetApi(29)
    public static ArrayList<TrafficInfo> c(Context context, long j8) {
        Iterator<PackageInfo> it;
        e eVar;
        PackageInfo packageInfo;
        BydLogHelper.logD("TrafficUtils", "---------------- getTrafficInfo3 start -------------------startTime:" + j8);
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(4096);
        NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getSystemService("netstats");
        e eVarC = e.c(context);
        ArrayList<TrafficInfo> arrayList = new ArrayList<>();
        HashMap map = new HashMap();
        Iterator<PackageInfo> it2 = installedPackages.iterator();
        while (it2.hasNext()) {
            PackageInfo next = it2.next();
            int i8 = next.applicationInfo.uid;
            String str = next.packageName;
            if (!f9090a.contains(str) && i8 > 1000) {
                int i9 = (next.applicationInfo.flags & 1) > 0 ? 1 : 0;
                String[] strArr = next.requestedPermissions;
                if (strArr == null || strArr.length <= 0) {
                    it = it2;
                } else {
                    int length = strArr.length;
                    it = it2;
                    int i10 = 0;
                    while (i10 < length) {
                        if ("android.permission.INTERNET".equals(strArr[i10])) {
                            TrafficInfo trafficInfo = new TrafficInfo();
                            trafficInfo.t(next.applicationInfo.uid);
                            trafficInfo.n(next.applicationInfo.loadLabel(packageManager).toString());
                            eVar = eVarC;
                            if (eVarC.d(i8)) {
                                trafficInfo.o(0);
                                if (u4.c.f8211b.equals(str)) {
                                    packageInfo = next;
                                    if (!"1".equals(Settings.Global.getString(context.getContentResolver(), "persist.mobile.navigation.switch"))) {
                                        Settings.Global.putInt(context.getContentResolver(), "persist.mobile.navigation.switch", 1);
                                    }
                                } else {
                                    packageInfo = next;
                                }
                            } else {
                                packageInfo = next;
                                trafficInfo.o(1);
                                if (u4.c.f8211b.equals(str) && !"0".equals(Settings.Global.getString(context.getContentResolver(), "persist.mobile.navigation.switch"))) {
                                    Settings.Global.putInt(context.getContentResolver(), "persist.mobile.navigation.switch", 0);
                                }
                            }
                            trafficInfo.s(str);
                            if (u4.c.f8210a.equals(str)) {
                                trafficInfo.n(context.getString(R$string.isa_app_name));
                            }
                            trafficInfo.p(i9);
                            map.put(Integer.valueOf(i8), trafficInfo);
                        } else {
                            eVar = eVarC;
                            packageInfo = next;
                        }
                        i10++;
                        eVarC = eVar;
                        next = packageInfo;
                    }
                }
                it2 = it;
                eVarC = eVarC;
            }
        }
        NetworkStats networkStatsQuerySummary = null;
        try {
            networkStatsQuerySummary = networkStatsManager.querySummary(0, null, j8, System.currentTimeMillis());
            do {
                NetworkStats.Bucket bucket = new NetworkStats.Bucket();
                networkStatsQuerySummary.getNextBucket(bucket);
                int uid = bucket.getUid();
                if (uid > 1000) {
                    TrafficInfo trafficInfo2 = (TrafficInfo) map.get(Integer.valueOf(uid));
                    if (trafficInfo2 == null) {
                        TrafficInfo trafficInfo3 = new TrafficInfo();
                        trafficInfo3.t(uid);
                        String strB = b(packageManager, uid);
                        trafficInfo3.s(strB);
                        try {
                            trafficInfo3.n(packageManager.getPackageInfo(strB, 0).applicationInfo.loadLabel(packageManager).toString());
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                        if (!f9090a.contains(strB)) {
                            trafficInfo3.q(bucket.getRxBytes());
                            trafficInfo3.r(bucket.getTxBytes());
                            map.put(Integer.valueOf(uid), trafficInfo3);
                        }
                    } else {
                        long j9 = trafficInfo2.j();
                        long jK = trafficInfo2.k();
                        trafficInfo2.q(j9 + bucket.getRxBytes());
                        trafficInfo2.r(jK + bucket.getTxBytes());
                    }
                }
            } while (networkStatsQuerySummary.hasNextBucket());
        } catch (RemoteException e8) {
            e8.printStackTrace();
            if (networkStatsQuerySummary != null) {
                networkStatsQuerySummary.close();
            }
        }
        Iterator it3 = map.keySet().iterator();
        while (it3.hasNext()) {
            TrafficInfo trafficInfo4 = (TrafficInfo) map.get((Integer) it3.next());
            BydLogHelper.logD("TrafficUtils", trafficInfo4.toString());
            arrayList.add(trafficInfo4);
        }
        BydLogHelper.logD("TrafficUtils", "---------------- getTrafficInfo3 end -------------------");
        return arrayList;
    }

    public static String d(String str) {
        float f8 = (Integer.parseInt(str) * 1.0f) / 1024.0f;
        if (f8 >= 1024.0f) {
            return a((f8 * 1.0f) / 1024.0f) + "GB";
        }
        return a(f8) + "MB";
    }
}
