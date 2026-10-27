package F;

import G.M;
import G.N;
import G.O;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k implements O {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f1895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M f1896b;
    public final int c;
    public final /* synthetic */ M d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ y f1897e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ boolean f1898f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f1899g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1900h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f1901i;

    public k(h hVar, M m10, int i7, y yVar, boolean z6, int i10, int i11, long j3) {
        this.d = m10;
        this.f1897e = yVar;
        this.f1898f = z6;
        this.f1899g = i10;
        this.f1900h = i11;
        this.f1901i = j3;
        this.f1895a = hVar;
        this.f1896b = m10;
        this.c = i7;
    }

    @Override // G.O
    public final N a(int i7, int i10, int i11, long j3) {
        return b(i7, j3, i10, i11, this.c);
    }

    public final p b(int i7, long j3, int i10, int i11, int i12) {
        int i13;
        h hVar = this.f1895a;
        Object objC = hVar.c(i7);
        Object objI = hVar.f1882b.i(i7);
        List listA = this.f1896b.a(j3, i7);
        if (a1.a.f(j3)) {
            i13 = a1.a.j(j3);
        } else {
            if (!a1.a.e(j3)) {
                throw new IllegalArgumentException("does not have fixed height");
            }
            i13 = a1.a.i(j3);
        }
        int i14 = i13;
        a1.k layoutDirection = this.d.f2515m.getLayoutDirection();
        androidx.compose.foundation.lazy.layout.a aVar = this.f1897e.f1981k;
        return new p(i7, objC, i14, i12, this.f1898f, layoutDirection, this.f1899g, this.f1900h, listA, this.f1901i, objI, aVar, j3, i10, i11);
    }
}
