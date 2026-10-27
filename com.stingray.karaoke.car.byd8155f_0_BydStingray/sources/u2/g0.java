package u2;

import F0.E0;
import android.net.Uri;
import e2.C1192A;
import e2.C1194b;
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
public final class g0 extends e2.f0 {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Object f22828j = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f22829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f22830f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f22831g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e2.F f22832h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C1217z f22833i;

    static {
        C1212u c1212u = new C1212u();
        E0 e02 = new E0();
        List listEmptyList = Collections.emptyList();
        d5.d0 d0Var = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        e2.B b10 = e2.B.d;
        Uri uri = Uri.EMPTY;
        AbstractC1346c.j(((Uri) e02.f2016e) == null || ((UUID) e02.d) != null);
        if (uri != null) {
            new C1192A(uri, null, ((UUID) e02.d) != null ? new C1215x(e02) : null, null, listEmptyList, null, d0Var, -9223372036854775807L);
        }
        new C1214w(c1212u);
        new C1217z(c1216y);
        e2.I i7 = e2.I.f13914K;
    }

    public g0(long j3, boolean z6, boolean z10, e2.F f10) {
        C1217z c1217z = z10 ? f10.c : null;
        this.f22829e = j3;
        this.f22830f = j3;
        this.f22831g = z6;
        f10.getClass();
        this.f22832h = f10;
        this.f22833i = c1217z;
    }

    @Override // e2.f0
    public final int b(Object obj) {
        return f22828j.equals(obj) ? 0 : -1;
    }

    @Override // e2.f0
    public final e2.c0 f(int i7, e2.c0 c0Var, boolean z6) {
        AbstractC1346c.f(i7, 1);
        Object obj = z6 ? f22828j : null;
        c0Var.getClass();
        c0Var.i(null, obj, 0, this.f22829e, 0L, C1194b.f14037f, false);
        return c0Var;
    }

    @Override // e2.f0
    public final int h() {
        return 1;
    }

    @Override // e2.f0
    public final Object l(int i7) {
        AbstractC1346c.f(i7, 1);
        return f22828j;
    }

    @Override // e2.f0
    public final e2.e0 m(int i7, e2.e0 e0Var, long j3) {
        AbstractC1346c.f(i7, 1);
        e0Var.b(e2.e0.f14075q, this.f22832h, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, this.f22831g, false, this.f22833i, 0L, this.f22830f, 0, 0, 0L);
        return e0Var;
    }

    @Override // e2.f0
    public final int o() {
        return 1;
    }
}
