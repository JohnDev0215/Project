package F2;

import B2.H;
import B2.r;
import B2.s;
import B2.t;
import B2.v;

/* JADX INFO: loaded from: classes.dex */
public final class a implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2354a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f2355b;

    public a(int i7) {
        this.f2354a = i7;
        switch (i7) {
            case 1:
                this.f2355b = new H("image/png", 35152, 2);
                break;
            default:
                this.f2355b = new H("image/bmp", 16973, 2);
                break;
        }
    }

    private final void d() {
    }

    private final void h() {
    }

    @Override // B2.r
    public final void a() {
        int i7 = this.f2354a;
    }

    @Override // B2.r
    public final void b(t tVar) {
        switch (this.f2354a) {
            case 0:
                this.f2355b.b(tVar);
                break;
            default:
                this.f2355b.b(tVar);
                break;
        }
    }

    @Override // B2.r
    public final int c(s sVar, v vVar) {
        switch (this.f2354a) {
        }
        return this.f2355b.c(sVar, vVar);
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        switch (this.f2354a) {
            case 0:
                this.f2355b.e(j3, j10);
                break;
            default:
                this.f2355b.e(j3, j10);
                break;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        switch (this.f2354a) {
        }
        return this.f2355b.f(sVar);
    }
}
