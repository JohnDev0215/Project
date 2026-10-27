package OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO;

import com.blankj.utilcode.util.r;
import com.loostone.libserver.version2.bean.UpgradeParams;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    public c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        UpgradeParams upgradeParams = new UpgradeParams();
        r rVarB = r.b("PuremicConfigOther");
        upgradeParams.setUpgradeType("car");
        upgradeParams.setPeriod(rVarB.d("effect_period_ota", 240));
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        String strD = OooO00o.OooO00o.OooO0Oo.OooO0OO.d.e.d(aVar.a());
        upgradeParams.setDeviceId(strD == null ? "null" : strD);
        String strA = OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c.a(aVar.a(), h.i(upgradeParams.getUpgradeType(), "net_config_version"));
        upgradeParams.setVersion(strA != null ? Integer.parseInt(strA) : rVarB.d("config_version", 1));
        upgradeParams.setFileType(3);
        super(upgradeParams, bVar);
    }
}
