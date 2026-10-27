package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i;
import com.loostone.libserver.version2.bean.DongleParams;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.l;

/* JADX INFO: loaded from: classes.dex */
public final class b extends OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a {

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

    public b() {
        n();
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a
    public void j() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("MicModel", "stop the upgrade timer");
        super.j();
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a
    public void k() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("MicModel", "start the upgrade timer, period: " + h() + 'h');
        super.k();
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a
    public void l() {
        i iVarI = i();
        if (iVarI == null) {
            return;
        }
        iVarI.onMicTimeEnd();
    }

    public final void m(DongleParams dongleParams) {
        l lVar;
        h.d(dongleParams, "params");
        OooO00o dongle = dongleParams.getDongle();
        if (dongle == null) {
            lVar = null;
        } else {
            f(new OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.b(dongle));
            d(new OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.e.a(dongleParams));
            b();
            lVar = l.f4084a;
        }
        if (lVar == null) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("MicModel", "dongle is null");
        }
    }

    public void n() {
        String strA = c.a(OooO00o.OooO00o.OooO0O0.a.f39a.a(), "mic_period_ota");
        c(strA == null ? 240 : Integer.parseInt(strA));
    }
}
