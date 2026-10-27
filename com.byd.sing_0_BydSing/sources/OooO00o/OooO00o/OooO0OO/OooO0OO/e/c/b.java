package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import com.android.volley.BuildConfig;
import com.loostone.libserver.version2.bean.ActivateParams;
import java.util.List;
import kotlin.jvm.internal.h;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {
    public b(OooO00o oooO00o) {
        List listJ;
        String str;
        h.d(oooO00o, "cachedDevice");
        ActivateParams activateParams = new ActivateParams();
        activateParams.setModelTypeName("receiver");
        String strT = oooO00o.t();
        activateParams.setModelName(String.valueOf((strT == null || (listJ = StringsKt__StringsKt.J(strT, new String[]{"-"}, false, 0, 6, null)) == null || (str = (String) listJ.get(0)) == null) ? null : o.i(str, "Loostone PureMic ", BuildConfig.FLAVOR, true)));
        activateParams.setManufacturerName(String.valueOf(oooO00o.y()));
        activateParams.setPid(String.valueOf(oooO00o.s()));
        activateParams.setVid(String.valueOf(oooO00o.x()));
        activateParams.setMac("null");
        activateParams.setCustomer(OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.d(OooO00o.OooO00o.OooO0O0.a.f39a.a()));
        activateParams.setDeviceId(String.valueOf(oooO00o.a()));
        activateParams.setHardwareName("default");
        activateParams.setDeviceOriginId(String.valueOf(oooO00o.a()));
        activateParams.setVersion(String.valueOf(oooO00o.q()));
        activateParams.setVersionName(String.valueOf(oooO00o.k()));
        activateParams.setDeviceName("null");
        super(activateParams);
    }
}
