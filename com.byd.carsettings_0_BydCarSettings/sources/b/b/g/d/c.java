package b.b.g.d;

import android.os.SystemProperties;
import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: CockpitModeStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return b() || c() || d();
    }

    public boolean b() {
        return e();
    }

    public boolean c() {
        u uVarI = u.I();
        String simpleName = c.class.getSimpleName();
        StringBuilder sb = new StringBuilder();
        sb.append("configService == null  : ");
        sb.append(uVarI == null);
        sb.append(" ,isSupportCpd ");
        sb.append(uVarI.t0());
        BydLogHelper.logD(simpleName, sb.toString());
        return uVarI.t0();
    }

    public boolean d() {
        int iA = b.b.g.n.q.b.c().a();
        return iA == 196 ? u.I().g() == 2 : (iA == 182 || iA == 208) && u.I().q() == 2;
    }

    public final boolean e() {
        String str = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.isp", "-1");
        String str2 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.name", "-1");
        String str3 = SystemProperties.get("persist.vendor.bosch.cfg.supplies.dms.camera.lens", "-1");
        BydLogHelper.logD("CockpitModeStudy", "cameraIsp = " + str + ", cameraName = " + str2 + ", cameraLens = " + str3);
        int iA = b.b.g.n.q.b.c().a();
        StringBuilder sb = new StringBuilder();
        sb.append("isHasDms carType : ");
        sb.append(iA);
        BydLogHelper.logD("CockpitModeStudy", sb.toString());
        return ((iA != 196 && iA != 182 && iA != 208 && iA != 60 && iA != 123 && iA != 143) || "-1".equals(str) || "-1".equals(str2) || "-1".equals(str3)) ? false : true;
    }
}
