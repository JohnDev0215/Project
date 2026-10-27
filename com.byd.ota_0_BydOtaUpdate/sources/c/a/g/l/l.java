package c.a.g.l;

import android.text.TextUtils;
import com.byd.repository.bean.OtaCycleTestInfo;

/* JADX INFO: compiled from: OtaCycleTestUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2770a = "l";

    public static int a() throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            return 0;
        }
        return otaCycleTestInfoB.cycleTestCount;
    }

    public static OtaCycleTestInfo b() throws Throwable {
        String strQ = c.a.b.d.d.q("/data/vendor/nfs/nfs_ota/otaCycleTestInfo.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        try {
            return (OtaCycleTestInfo) h.d(strQ, OtaCycleTestInfo.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c() throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            return null;
        }
        return otaCycleTestInfoB.updateFailMsg;
    }

    public static boolean d() {
        return g() || e();
    }

    public static boolean e() throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            return false;
        }
        return otaCycleTestInfoB.isCycleTestBench;
    }

    public static boolean f() throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            return false;
        }
        h.e(otaCycleTestInfoB);
        return otaCycleTestInfoB.isNoNetTest;
    }

    public static boolean g() throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            return false;
        }
        return otaCycleTestInfoB.isCycleTestVehicle;
    }

    public static boolean h() throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            return false;
        }
        return otaCycleTestInfoB.isNetTestEnv;
    }

    public static boolean i(OtaCycleTestInfo otaCycleTestInfo) {
        if (otaCycleTestInfo == null) {
            return false;
        }
        h.e(otaCycleTestInfo);
        try {
            return c.a.b.d.d.t("/data/vendor/nfs/nfs_ota/", "otaCycleTestInfo.json", h.f(otaCycleTestInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2770a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }

    public static void j(int i2) throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            otaCycleTestInfoB = new OtaCycleTestInfo();
        }
        otaCycleTestInfoB.cycleTestCount = i2;
        i(otaCycleTestInfoB);
    }

    public static void k(boolean z) throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            otaCycleTestInfoB = new OtaCycleTestInfo();
        }
        otaCycleTestInfoB.isCycleTestBench = z;
        i(otaCycleTestInfoB);
    }

    public static void l(boolean z) throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            otaCycleTestInfoB = new OtaCycleTestInfo();
        }
        otaCycleTestInfoB.isNoNetTest = z;
        i(otaCycleTestInfoB);
    }

    public static void m(boolean z) throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            otaCycleTestInfoB = new OtaCycleTestInfo();
        }
        otaCycleTestInfoB.isCycleTestVehicle = z;
        i(otaCycleTestInfoB);
    }

    public static void n(boolean z) throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            otaCycleTestInfoB = new OtaCycleTestInfo();
        }
        otaCycleTestInfoB.isNetTestEnv = z;
        i(otaCycleTestInfoB);
    }

    public static void o(String str) throws Throwable {
        OtaCycleTestInfo otaCycleTestInfoB = b();
        if (otaCycleTestInfoB == null) {
            otaCycleTestInfoB = new OtaCycleTestInfo();
        }
        otaCycleTestInfoB.updateFailMsg = str;
        i(otaCycleTestInfoB);
    }
}
