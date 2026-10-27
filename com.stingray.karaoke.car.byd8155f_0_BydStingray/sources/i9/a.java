package i9;

import A7.m;
import i8.C1417b;
import java.io.IOException;
import r9.C2319i;
import r9.D;
import r9.J;
import r9.L;
import r9.r;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements J {
    public final r l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15800m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1417b f15801n;

    public a(C1417b c1417b) {
        this.f15801n = c1417b;
        this.l = new r(((D) c1417b.f15785e).l.e());
    }

    public final void b() {
        C1417b c1417b = this.f15801n;
        int i7 = c1417b.f15784b;
        if (i7 == 6) {
            return;
        }
        if (i7 != 5) {
            throw new IllegalStateException("state: " + c1417b.f15784b);
        }
        r rVar = this.l;
        L l = rVar.f21940e;
        rVar.f21940e = L.d;
        l.a();
        l.b();
        c1417b.f15784b = 6;
    }

    @Override // r9.J
    public final L e() {
        return this.l;
    }

    @Override // r9.J
    public long p(C2319i c2319i, long j3) throws IOException {
        C1417b c1417b = this.f15801n;
        m.f(c2319i, "sink");
        try {
            return ((D) c1417b.f15785e).p(c2319i, j3);
        } catch (IOException e10) {
            ((g9.m) c1417b.d).k();
            this.b();
            throw e10;
        }
    }
}
