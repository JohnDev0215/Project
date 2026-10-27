package v3;

import androidx.recyclerview.widget.f;

/* JADX INFO: compiled from: PositionCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends f.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f.b f9678a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f9679b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f9680c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f9681d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f9682e = -1;

    public d(f.b bVar) {
        this.f9678a = bVar;
    }

    private int g(int i7, int i8) {
        return i7 == -1 ? i8 : Math.max(i7, i8);
    }

    private int h(int i7, int i8) {
        return i7 == -1 ? i8 : Math.min(i7, i8);
    }

    private void i(int i7, int i8) {
        this.f9679b = h(this.f9679b, i8);
        this.f9680c = g(this.f9680c, i8);
        this.f9681d = h(this.f9681d, i7);
        this.f9682e = g(this.f9682e, i7);
    }

    @Override // androidx.recyclerview.widget.f.b
    public boolean a(int i7, int i8) {
        boolean zA = this.f9678a.a(i7, i8);
        if (!zA) {
            i(i7, i8);
        }
        return zA;
    }

    @Override // androidx.recyclerview.widget.f.b
    public boolean b(int i7, int i8) {
        boolean zB = this.f9678a.b(i7, i8);
        if (!zB) {
            i(i7, i8);
        }
        return zB;
    }

    @Override // androidx.recyclerview.widget.f.b
    public int d() {
        return this.f9678a.d();
    }

    @Override // androidx.recyclerview.widget.f.b
    public int e() {
        return this.f9678a.e();
    }

    public int f() {
        return this.f9679b;
    }
}
