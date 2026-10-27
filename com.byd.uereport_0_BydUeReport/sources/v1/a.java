package v1;

import android.graphics.PointF;
import u1.m;

/* JADX INFO: compiled from: CircleShape.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f8512a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m<PointF, PointF> f8513b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u1.f f8514c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f8515d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f8516e;

    public a(String str, m<PointF, PointF> mVar, u1.f fVar, boolean z7, boolean z8) {
        this.f8512a = str;
        this.f8513b = mVar;
        this.f8514c = fVar;
        this.f8515d = z7;
        this.f8516e = z8;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new q1.f(gVar, aVar, this);
    }

    public String b() {
        return this.f8512a;
    }

    public m<PointF, PointF> c() {
        return this.f8513b;
    }

    public u1.f d() {
        return this.f8514c;
    }

    public boolean e() {
        return this.f8516e;
    }

    public boolean f() {
        return this.f8515d;
    }
}
