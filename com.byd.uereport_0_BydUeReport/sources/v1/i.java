package v1;

import android.graphics.Path;

/* JADX INFO: compiled from: ShapeFill.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f8555a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path.FillType f8556b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f8557c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u1.a f8558d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final u1.d f8559e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f8560f;

    public i(String str, boolean z7, Path.FillType fillType, u1.a aVar, u1.d dVar, boolean z8) {
        this.f8557c = str;
        this.f8555a = z7;
        this.f8556b = fillType;
        this.f8558d = aVar;
        this.f8559e = dVar;
        this.f8560f = z8;
    }

    @Override // v1.b
    public q1.c a(o1.g gVar, w1.a aVar) {
        return new q1.g(gVar, aVar, this);
    }

    public u1.a b() {
        return this.f8558d;
    }

    public Path.FillType c() {
        return this.f8556b;
    }

    public String d() {
        return this.f8557c;
    }

    public u1.d e() {
        return this.f8559e;
    }

    public boolean f() {
        return this.f8560f;
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f8555a + '}';
    }
}
