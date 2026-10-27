package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.e;
import android.os.Build;
import com.android.volley.BuildConfig;
import com.loostone.libserver.version2.bean.ActivateParams;
import com.loostone.libserver.version2.bean.PlatformParams;
import kotlin.jvm.internal.h;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    public c(PlatformParams platformParams) {
        h.d(platformParams, "params");
        ActivateParams activateParams = new ActivateParams();
        OooO00o oooO00o = OooO00o.f18a;
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        String strD = oooO00o.d(aVar.a());
        String string = e.d(aVar.a()).toString();
        activateParams.setModelTypeName("car");
        activateParams.setModelName(platformParams.getModelName());
        activateParams.setManufacturerName(strD);
        activateParams.setPid("null");
        activateParams.setVid("null");
        activateParams.setMac(string);
        activateParams.setCustomer(strD);
        activateParams.setDeviceId(string);
        activateParams.setHardwareName(Build.HARDWARE.toString());
        activateParams.setDeviceOriginId(string);
        activateParams.setVersion(o.j(platformParams.getSdkVersion(), ".", BuildConfig.FLAVOR, false, 4, null));
        activateParams.setVersionName(platformParams.getSdkVersion());
        activateParams.setDeviceName(Build.DEVICE.toString());
        super(activateParams);
    }
}
