package b.b.g.d;

import android.content.pm.PackageManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: ModelStudyUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f751a = "com.neusoft.na.navigation";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f752b = "com.huawei.maps.auto.app";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f753c = "com.byd.automap";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f754d = "net.zmap.znav.byd8155";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f755e = "com.ts.carplay.app";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f756f = "com.ts.androidauto.app";
    public static String g = "ModelStudyUtils";
    public static String h = "82";

    public static boolean a(String str) {
        BydLogHelper.logD(g, "packageName : " + str);
        try {
            if (b.b.a.a.a.b().a().getPackageManager().getPackageInfo(str, 0) != null) {
                return true;
            }
            BydLogHelper.logE(g, "packageName : " + str + " packageInfo == null");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            BydLogHelper.logE(g, "packageName : " + str + " , NameNotFoundException");
            return false;
        }
    }

    public static boolean b() {
        String strB = b.b.g.r.l.b();
        int iB = b.b.a.i.a.b();
        BydLogHelper.logD(g, "isKoreaUke  countryCode : " + strB + " , caryType : " + iB);
        return h.equals(strB) && (iB == 182 || iB == 208);
    }

    public static boolean c() {
        if (b()) {
            return false;
        }
        String[] strArr = {f751a, f752b, f753c, f755e, f756f, f754d};
        for (int i = 0; i < 6; i++) {
            if (a(strArr[i])) {
                return true;
            }
        }
        return false;
    }
}
