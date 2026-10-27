package c.a.g.l;

import android.text.TextUtils;
import com.byd.repository.bean.RepairUpgradeInfo;
import java.io.File;

/* JADX INFO: compiled from: RepairUpgradeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2771a = "m";

    public static RepairUpgradeInfo a() throws Throwable {
        String strQ = c.a.b.d.d.q(c.a.b.b.a.l + "repairUpgrade.json");
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (RepairUpgradeInfo) h.d(strQ, RepairUpgradeInfo.class);
    }

    public static boolean b() {
        return new File(c.a.b.b.a.l + "repairUpgrade.json").exists();
    }

    public static boolean c(RepairUpgradeInfo repairUpgradeInfo) {
        if (repairUpgradeInfo == null) {
            return false;
        }
        h.e(repairUpgradeInfo);
        try {
            return c.a.b.d.d.t(c.a.b.b.a.l, "repairUpgrade.json", h.f(repairUpgradeInfo));
        } catch (Exception e2) {
            c.a.b.d.e.b(f2771a, "writeDataWithProcessBuilder error：" + e2.getMessage());
            return false;
        }
    }
}
