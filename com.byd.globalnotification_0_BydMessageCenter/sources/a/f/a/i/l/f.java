package a.f.a.i.l;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f implements d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f585d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f587f;
    public int g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f582a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f583b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f584c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public a f586e = a.UNKNOWN;
    public int h = 1;
    public g i = null;
    public boolean j = false;
    public List<d> k = new ArrayList();
    public List<f> l = new ArrayList();

    public enum a {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public f(o oVar) {
        this.f585d = oVar;
    }

    @Override // a.f.a.i.l.d
    public void a(d dVar) {
        Iterator<f> it = this.l.iterator();
        while (it.hasNext()) {
            if (!it.next().j) {
                return;
            }
        }
        this.f584c = true;
        d dVar2 = this.f582a;
        if (dVar2 != null) {
            dVar2.a(this);
        }
        if (this.f583b) {
            this.f585d.a(this);
            return;
        }
        f fVar = null;
        int i = 0;
        for (f fVar2 : this.l) {
            if (!(fVar2 instanceof g)) {
                i++;
                fVar = fVar2;
            }
        }
        if (fVar != null && i == 1 && fVar.j) {
            g gVar = this.i;
            if (gVar != null) {
                if (!gVar.j) {
                    return;
                } else {
                    this.f587f = this.h * gVar.g;
                }
            }
            c(fVar.g + this.f587f);
        }
        d dVar3 = this.f582a;
        if (dVar3 != null) {
            dVar3.a(this);
        }
    }

    public void b() {
        this.l.clear();
        this.k.clear();
        this.j = false;
        this.g = 0;
        this.f584c = false;
        this.f583b = false;
    }

    public void c(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (d dVar : this.k) {
            dVar.a(dVar);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f585d.f607b.m0);
        sb.append(":");
        sb.append(this.f586e);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(">");
        return sb.toString();
    }
}
