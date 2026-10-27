package s;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DependencyNode.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    p f9410d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f9412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9413g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f9407a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f9408b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f9409c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    a f9411e = a.UNKNOWN;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f9414h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    g f9415i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f9416j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    List<d> f9417k = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    List<f> f9418l = new ArrayList();

    /* JADX INFO: compiled from: DependencyNode.java */
    enum a {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public f(p pVar) {
        this.f9410d = pVar;
    }

    @Override // s.d
    public void a(d dVar) {
        Iterator<f> it = this.f9418l.iterator();
        while (it.hasNext()) {
            if (!it.next().f9416j) {
                return;
            }
        }
        this.f9409c = true;
        d dVar2 = this.f9407a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f9408b) {
            this.f9410d.a(this);
            return;
        }
        f fVar = null;
        int i7 = 0;
        for (f fVar2 : this.f9418l) {
            if (!(fVar2 instanceof g)) {
                i7++;
                fVar = fVar2;
            }
        }
        if (fVar != null && i7 == 1 && fVar.f9416j) {
            g gVar = this.f9415i;
            if (gVar != null) {
                if (!gVar.f9416j) {
                    return;
                } else {
                    this.f9412f = this.f9414h * gVar.f9413g;
                }
            }
            d(fVar.f9413g + this.f9412f);
        }
        d dVar3 = this.f9407a;
        if (dVar3 != null) {
            dVar3.a(this);
        }
    }

    public void b(d dVar) {
        this.f9417k.add(dVar);
        if (this.f9416j) {
            dVar.a(dVar);
        }
    }

    public void c() {
        this.f9418l.clear();
        this.f9417k.clear();
        this.f9416j = false;
        this.f9413g = 0;
        this.f9409c = false;
        this.f9408b = false;
    }

    public void d(int i7) {
        if (this.f9416j) {
            return;
        }
        this.f9416j = true;
        this.f9413g = i7;
        for (d dVar : this.f9417k) {
            dVar.a(dVar);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9410d.f9453b.v());
        sb.append(":");
        sb.append(this.f9411e);
        sb.append("(");
        sb.append(this.f9416j ? Integer.valueOf(this.f9413g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.f9418l.size());
        sb.append(":d=");
        sb.append(this.f9417k.size());
        sb.append(">");
        return sb.toString();
    }
}
