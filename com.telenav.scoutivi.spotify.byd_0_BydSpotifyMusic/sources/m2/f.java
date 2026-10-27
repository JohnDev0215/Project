package m2;

import m2.b;
import m2.g;

/* JADX INFO: compiled from: RenderOptions.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    b.r f8128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    e f8129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    String f8130c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    g.b f8131d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f8132e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    g.b f8133f;

    public f() {
        this.f8128a = null;
        this.f8129b = null;
        this.f8130c = null;
        this.f8131d = null;
        this.f8132e = null;
        this.f8133f = null;
    }

    public boolean a() {
        b.r rVar = this.f8128a;
        return rVar != null && rVar.f() > 0;
    }

    public boolean b() {
        return this.f8129b != null;
    }

    public boolean c() {
        return this.f8130c != null;
    }

    public boolean d() {
        return this.f8132e != null;
    }

    public boolean e() {
        return this.f8131d != null;
    }

    public boolean f() {
        return this.f8133f != null;
    }

    public f g(float f7, float f8, float f9, float f10) {
        this.f8133f = new g.b(f7, f8, f9, f10);
        return this;
    }

    public f(f fVar) {
        this.f8128a = null;
        this.f8129b = null;
        this.f8130c = null;
        this.f8131d = null;
        this.f8132e = null;
        this.f8133f = null;
        if (fVar == null) {
            return;
        }
        this.f8128a = fVar.f8128a;
        this.f8129b = fVar.f8129b;
        this.f8131d = fVar.f8131d;
        this.f8132e = fVar.f8132e;
        this.f8133f = fVar.f8133f;
    }
}
