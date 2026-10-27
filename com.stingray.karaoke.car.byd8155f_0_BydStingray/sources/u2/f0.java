package u2;

import F0.E0;
import android.net.Uri;
import e2.C1192A;
import e2.C1207o;
import e2.C1208p;
import e2.C1212u;
import e2.C1214w;
import e2.C1215x;
import e2.C1216y;
import e2.C1217z;
import h2.AbstractC1346c;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class f0 extends AbstractC2498a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C1208p f22822j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final e2.F f22823k;
    public static final byte[] l;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f22824h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e2.F f22825i;

    static {
        C1192A c1192a;
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l("audio/raw");
        c1207o.B = 2;
        c1207o.f14228C = 44100;
        c1207o.f14229D = 2;
        C1208p c1208p = new C1208p(c1207o);
        f22822j = c1208p;
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d5.d0 d0Var = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        e2.B b10 = e2.B.d;
        Uri uri = Uri.EMPTY;
        AbstractC1346c.j(((Uri) e02.f2016e) == null || ((UUID) e02.d) != null);
        if (uri != null) {
            c1192a = new C1192A(uri, c1208p.f14316n, ((UUID) e02.d) != null ? new C1215x(e02) : null, null, listEmptyList, null, d0Var, -9223372036854775807L);
        } else {
            c1192a = null;
        }
        f22823k = new e2.F("SilenceMediaSource", new C1214w(c1212u), c1192a, new C1217z(c1216y), e2.I.f13914K, b10);
        l = new byte[4096];
    }

    public f0(long j3) {
        AbstractC1346c.c(j3 >= 0);
        this.f22824h = j3;
        this.f22825i = f22823k;
    }

    @Override // u2.AbstractC2498a
    public final boolean a(e2.F f10) {
        return true;
    }

    @Override // u2.AbstractC2498a
    public final InterfaceC2490A b(C2492C c2492c, x2.e eVar, long j3) {
        return new d0(this.f22824h);
    }

    @Override // u2.AbstractC2498a
    public final synchronized e2.F h() {
        return this.f22825i;
    }

    @Override // u2.AbstractC2498a
    public final void j() {
    }

    @Override // u2.AbstractC2498a
    public final void l(k2.E e10) {
        m(new g0(this.f22824h, true, false, h()));
    }

    @Override // u2.AbstractC2498a
    public final void n(InterfaceC2490A interfaceC2490A) {
    }

    @Override // u2.AbstractC2498a
    public final void p() {
    }

    @Override // u2.AbstractC2498a
    public final synchronized void s(e2.F f10) {
        this.f22825i = f10;
    }
}
