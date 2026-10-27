package v1;

import android.graphics.PointF;
import q1.o;
import u1.m;

/* JADX INFO: compiled from: RectangleShape.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8542a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m<PointF, PointF> f8543b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.f f8544c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.b f8545d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f8546e;

    public f(String str, m<PointF, PointF> mVar, u1.f fVar, u1.b bVar, boolean z7) {
        this.f8542a = str;
        this.f8543b = mVar;
        this.f8544c = fVar;
        this.f8545d = bVar;
        this.f8546e = z7;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new o(gVar, aVar, this);
    }

    public u1.b b() {
        return this.f8545d;
    }

    public String c() {
        return this.f8542a;
    }

    public m<PointF, PointF> d() {
        return this.f8543b;
    }

    public u1.f e() {
        return this.f8544c;
    }

    public boolean f() {
        return this.f8546e;
    }

    public String toString() {
        return "RectangleShape{position=" + this.f8543b + ", size=" + this.f8544c + '}';
    }
}
