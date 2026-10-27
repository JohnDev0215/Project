package E2;

import B2.C0073m;
import B2.H;
import B2.r;
import B2.s;
import B2.v;
import h2.t;
import java.io.EOFException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1719a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t f1720b;
    public final H c;

    public a(int i7) {
        this.f1719a = i7;
        switch (i7) {
            case 1:
                this.f1720b = new t(4);
                this.c = new H("image/heif", -1, -1);
                break;
            case 2:
                this.f1720b = new t(4);
                this.c = new H("image/webp", -1, -1);
                break;
            default:
                this.f1720b = new t(4);
                this.c = new H("image/avif", -1, -1);
                break;
        }
    }

    private final void d() {
    }

    private final void h() {
    }

    private final void i() {
    }

    @Override // B2.r
    public final void a() {
        int i7 = this.f1719a;
    }

    @Override // B2.r
    public final void b(B2.t tVar) {
        switch (this.f1719a) {
            case 0:
                this.c.b(tVar);
                break;
            case 1:
                this.c.b(tVar);
                break;
            default:
                this.c.b(tVar);
                break;
        }
    }

    @Override // B2.r
    public final int c(s sVar, v vVar) {
        switch (this.f1719a) {
        }
        return this.c.c(sVar, vVar);
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        switch (this.f1719a) {
            case 0:
                this.c.e(j3, j10);
                break;
            case 1:
                this.c.e(j3, j10);
                break;
            default:
                this.c.e(j3, j10);
                break;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) throws EOFException, InterruptedIOException {
        switch (this.f1719a) {
            case 0:
                C0073m c0073m = (C0073m) sVar;
                c0073m.e(4, false);
                t tVar = this.f1720b;
                tVar.E(4);
                c0073m.k(tVar.f15241a, 0, 4, false);
                if (tVar.x() == 1718909296) {
                    tVar.E(4);
                    c0073m.k(tVar.f15241a, 0, 4, false);
                    if (tVar.x() == 1635150182) {
                    }
                }
                break;
            case 1:
                C0073m c0073m2 = (C0073m) sVar;
                c0073m2.e(4, false);
                t tVar2 = this.f1720b;
                tVar2.E(4);
                c0073m2.k(tVar2.f15241a, 0, 4, false);
                if (tVar2.x() == 1718909296) {
                    tVar2.E(4);
                    c0073m2.k(tVar2.f15241a, 0, 4, false);
                    if (tVar2.x() == 1751476579) {
                    }
                }
                break;
            default:
                t tVar3 = this.f1720b;
                tVar3.E(4);
                C0073m c0073m3 = (C0073m) sVar;
                c0073m3.k(tVar3.f15241a, 0, 4, false);
                if (tVar3.x() == 1380533830) {
                    c0073m3.e(4, false);
                    tVar3.E(4);
                    c0073m3.k(tVar3.f15241a, 0, 4, false);
                    if (tVar3.x() == 1464156752) {
                    }
                }
                break;
        }
        return false;
    }
}
