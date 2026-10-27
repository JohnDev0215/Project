package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.c;
import com.loostone.libserver.version2.bean.PlatformParams;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class d extends OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a {

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public d() {
        p();
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a
    public void j() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("PlatformModel", "stop the upgrade timer");
        super.j();
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a
    public void k() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("PlatformModel", "start the upgrade timer, period: " + h() + 'h');
        super.k();
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a
    public void l() {
        i iVarI = i();
        if (iVarI == null) {
            return;
        }
        iVarI.onPlatformTimeEnd();
    }

    public final int m(String str, int i) {
        return OooO00o.OooO00o.OooO0O0.a.f39a.a().getSharedPreferences("PuremicConfigOther", 0).getInt(str, i);
    }

    public final void n(PlatformParams platformParams) {
        h.d(platformParams, "params");
        f(new c(platformParams));
        d(new OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e.c(platformParams));
        b();
    }

    public final void o(PlatformParams platformParams) {
        h.d(platformParams, "params");
        f(new c(platformParams));
        d(new OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e.d(platformParams));
        b();
    }

    public void p() {
        c(m("effect_period_ota", 240));
    }
}
