package OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import com.android.volley.BuildConfig;
import com.loostone.libserver.version2.bean.UpgradeParams;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {
    public b(OooO00o oooO00o, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        h.d(oooO00o, "cachedDongleDevice");
        UpgradeParams upgradeParams = new UpgradeParams();
        upgradeParams.setUpgradeType("receiver");
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        String strA = OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c.a(aVar.a(), "effect_period_ota");
        upgradeParams.setPeriod(strA == null ? 240 : Integer.parseInt(strA));
        String strA2 = oooO00o.a();
        upgradeParams.setDeviceId(strA2 == null ? BuildConfig.FLAVOR : strA2);
        String strA3 = OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c.a(aVar.a(), h.i(upgradeParams.getUpgradeType(), "net_config_version"));
        String strA4 = OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c.a(aVar.a(), "effect_period_ota");
        Integer numValueOf = strA3 == null ? null : Integer.valueOf(Integer.parseInt(strA3));
        upgradeParams.setVersion(numValueOf == null ? strA4 == null ? 1 : Integer.parseInt(strA4) : numValueOf.intValue());
        upgradeParams.setFileType(3);
        super(upgradeParams, bVar);
    }
}
