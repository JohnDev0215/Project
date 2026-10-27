package u;

/* JADX INFO: renamed from: u.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2460H extends n7.y {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2459G f22590m;

    public C2460H(C2459G c2459g) {
        this.f22590m = c2459g;
    }

    @Override // n7.y
    public final int b() {
        int i7 = this.l;
        this.l = i7 + 1;
        return this.f22590m.d(i7);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.l < this.f22590m.f();
    }
}
