package OooO00o.OooO00o.OooO0Oo.OooO0OO.b.d;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.b.b;
import android.content.Context;
import com.loostone.libserver.version1.entity.NetUrlConfig;
import com.loostone.libserver.version1.entity.request.config.RequestMicLimitInfo;
import com.loostone.libserver.version1.entity.response.config.MicLimitInfo;

/* JADX INFO: loaded from: classes.dex */
public class a extends b {
    public a(Context context, String str) {
        super(context, str);
        e(Boolean.TRUE, 604800000L);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.b.b
    public OooO00o.OooO00o.OooO0Oo.OooO0OO.c.b i() {
        return new OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a(MicLimitInfo.class);
    }

    public void j(int i, OooO00o oooO00o, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a<MicLimitInfo> aVar) {
        RequestMicLimitInfo requestMicLimitInfo = new RequestMicLimitInfo();
        requestMicLimitInfo.setHidResult(i);
        requestMicLimitInfo.setHidSn(oooO00o.r());
        requestMicLimitInfo.setHidVersion(oooO00o.q());
        requestMicLimitInfo.setSerial(oooO00o.a());
        requestMicLimitInfo.setVid(oooO00o.x());
        requestMicLimitInfo.setPid(oooO00o.s());
        requestMicLimitInfo.setVendor(oooO00o.y());
        requestMicLimitInfo.setDeviceName(oooO00o.t());
        g(NetUrlConfig.getAddress(2), requestMicLimitInfo, aVar);
    }
}
