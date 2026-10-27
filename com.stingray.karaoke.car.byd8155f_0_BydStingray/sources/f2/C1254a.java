package f2;

import d5.d0;
import h2.AbstractC1346c;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: renamed from: f2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1254a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f14545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f14546b = new ArrayList();
    public ByteBuffer[] c = new ByteBuffer[0];
    public boolean d;

    public C1254a(d0 d0Var) {
        this.f14545a = d0Var;
        b bVar = b.f14547e;
        this.d = false;
    }

    public final b a(b bVar) {
        if (bVar.equals(b.f14547e)) {
            throw new c(bVar);
        }
        int i7 = 0;
        while (true) {
            d0 d0Var = this.f14545a;
            if (i7 >= d0Var.size()) {
                return bVar;
            }
            d dVar = (d) d0Var.get(i7);
            b bVarC = dVar.c(bVar);
            if (dVar.b()) {
                AbstractC1346c.j(!bVarC.equals(b.f14547e));
                bVar = bVarC;
            }
            i7++;
        }
    }

    public final void b() {
        ArrayList arrayList = this.f14546b;
        arrayList.clear();
        this.d = false;
        int i7 = 0;
        while (true) {
            d0 d0Var = this.f14545a;
            if (i7 >= d0Var.size()) {
                break;
            }
            d dVar = (d) d0Var.get(i7);
            dVar.flush();
            if (dVar.b()) {
                arrayList.add(dVar);
            }
            i7++;
        }
        this.c = new ByteBuffer[arrayList.size()];
        for (int i10 = 0; i10 <= c(); i10++) {
            this.c[i10] = ((d) arrayList.get(i10)).a();
        }
    }

    public final int c() {
        return this.c.length - 1;
    }

    public final boolean d() {
        return this.d && ((d) this.f14546b.get(c())).f() && !this.c[c()].hasRemaining();
    }

    public final boolean e() {
        return !this.f14546b.isEmpty();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1254a)) {
            return false;
        }
        C1254a c1254a = (C1254a) obj;
        d0 d0Var = this.f14545a;
        if (d0Var.size() != c1254a.f14545a.size()) {
            return false;
        }
        for (int i7 = 0; i7 < d0Var.size(); i7++) {
            if (d0Var.get(i7) != c1254a.f14545a.get(i7)) {
                return false;
            }
        }
        return true;
    }

    public final void f(ByteBuffer byteBuffer) {
        boolean z6;
        for (boolean z10 = true; z10; z10 = z6) {
            z6 = false;
            int i7 = 0;
            while (i7 <= c()) {
                if (!this.c[i7].hasRemaining()) {
                    ArrayList arrayList = this.f14546b;
                    d dVar = (d) arrayList.get(i7);
                    if (!dVar.f()) {
                        ByteBuffer byteBuffer2 = i7 > 0 ? this.c[i7 - 1] : byteBuffer.hasRemaining() ? byteBuffer : d.f14550a;
                        long jRemaining = byteBuffer2.remaining();
                        dVar.g(byteBuffer2);
                        this.c[i7] = dVar.a();
                        z6 |= jRemaining - ((long) byteBuffer2.remaining()) > 0 || this.c[i7].hasRemaining();
                    } else if (!this.c[i7].hasRemaining() && i7 < c()) {
                        ((d) arrayList.get(i7 + 1)).e();
                    }
                }
                i7++;
            }
        }
    }

    public final int hashCode() {
        return this.f14545a.hashCode();
    }
}
