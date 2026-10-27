package X3;

import N0.q;
import a4.AbstractC0922c;
import a4.C0920a;
import a4.C0926g;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import u.C2472l;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class c extends b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final R3.f f10019D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final ArrayList f10020E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final RectF f10021F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final RectF f10022G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final RectF f10023H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final C0926g f10024I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C1.j f10025J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public float f10026K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f10027L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final R3.g f10028M;

    public c(O3.j jVar, e eVar, List list, O3.a aVar) {
        b bVar;
        b cVar;
        String str;
        super(jVar, eVar);
        this.f10020E = new ArrayList();
        this.f10021F = new RectF();
        this.f10022G = new RectF();
        this.f10023H = new RectF();
        this.f10024I = new C0926g();
        this.f10025J = new C1.j(7, false);
        this.f10027L = true;
        V3.b bVar2 = eVar.f10051s;
        if (bVar2 != null) {
            R3.f fVarT0 = bVar2.t0();
            this.f10019D = fVarT0;
            d(fVarT0);
            fVarT0.a(this);
        } else {
            this.f10019D = null;
        }
        C2472l c2472l = new C2472l(aVar.f5076j.size());
        int size = list.size() - 1;
        b bVar3 = null;
        while (true) {
            if (size < 0) {
                for (int i7 = 0; i7 < c2472l.g(); i7++) {
                    b bVar4 = (b) c2472l.b(c2472l.d(i7));
                    if (bVar4 != null && (bVar = (b) c2472l.b(bVar4.f10008p.f10040f)) != null) {
                        bVar4.f10012t = bVar;
                    }
                }
                q qVar = this.f10008p.f10056x;
                if (qVar != null) {
                    this.f10028M = new R3.g(this, this, qVar);
                    return;
                }
                return;
            }
            e eVar2 = (e) list.get(size);
            int iC = AbstractC2641j.c(eVar2.f10039e);
            if (iC == 0) {
                cVar = new c(jVar, eVar2, (List) aVar.c.get(eVar2.f10041g), aVar);
            } else if (iC == 1) {
                cVar = new h(jVar, eVar2);
            } else if (iC == 2) {
                cVar = new d(jVar, eVar2);
            } else if (iC == 3) {
                cVar = new f(jVar, eVar2);
            } else if (iC == 4) {
                cVar = new g(jVar, eVar2, this, aVar);
            } else if (iC != 5) {
                switch (eVar2.f10039e) {
                    case 1:
                        str = "PRE_COMP";
                        break;
                    case 2:
                        str = "SOLID";
                        break;
                    case 3:
                        str = "IMAGE";
                        break;
                    case 4:
                        str = "NULL";
                        break;
                    case 5:
                        str = "SHAPE";
                        break;
                    case 6:
                        str = "TEXT";
                        break;
                    case 7:
                        str = "UNKNOWN";
                        break;
                    default:
                        str = "null";
                        break;
                }
                AbstractC0922c.a("Unknown layer type ".concat(str));
                cVar = null;
            } else {
                cVar = new j(jVar, eVar2);
            }
            if (cVar != null) {
                c2472l.e(cVar.f10008p.d, cVar);
                if (bVar3 != null) {
                    bVar3.f10011s = cVar;
                    bVar3 = null;
                } else {
                    this.f10020E.add(0, cVar);
                    int iC2 = AbstractC2641j.c(eVar2.f10053u);
                    if (iC2 == 1 || iC2 == 2) {
                        bVar3 = cVar;
                    }
                }
            }
            size--;
        }
    }

    @Override // X3.b, Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        super.a(rectF, matrix, z6);
        ArrayList arrayList = this.f10020E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            RectF rectF2 = this.f10021F;
            rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
            ((b) arrayList.get(size)).a(rectF2, this.f10007n, true);
            rectF.union(rectF2);
        }
    }

    @Override // X3.b
    public final void h(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        Canvas canvasE;
        R3.g gVar = this.f10028M;
        boolean z6 = false;
        boolean z10 = (c0920a == null && gVar == null) ? false : true;
        O3.j jVar = this.o;
        boolean z11 = jVar.f5130z;
        ArrayList<b> arrayList = this.f10020E;
        if ((z11 && arrayList.size() > 1 && i7 != 255) || (z10 && jVar.f5096A)) {
            z6 = true;
        }
        int i10 = z6 ? 255 : i7;
        if (gVar != null) {
            c0920a = gVar.a(matrix, i10);
        }
        boolean z12 = this.f10027L;
        RectF rectF = this.f10022G;
        e eVar = this.f10008p;
        if (z12 || !"__container".equals(eVar.c)) {
            rectF.set(0.0f, 0.0f, eVar.o, eVar.f10048p);
            matrix.mapRect(rectF);
        } else {
            rectF.setEmpty();
            for (b bVar : arrayList) {
                RectF rectF2 = this.f10023H;
                bVar.a(rectF2, matrix, true);
                rectF.union(rectF2);
            }
        }
        C0926g c0926g = this.f10024I;
        if (z6) {
            C1.j jVar2 = this.f10025J;
            jVar2.f944m = null;
            jVar2.l = i7;
            if (c0920a != null) {
                if (Color.alpha(c0920a.d) > 0) {
                    jVar2.f944m = c0920a;
                } else {
                    jVar2.f944m = null;
                }
                c0920a = null;
            }
            canvasE = c0926g.e(canvas, rectF, jVar2);
        } else {
            canvasE = canvas;
        }
        canvas.save();
        if (canvas.clipRect(rectF)) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((b) arrayList.get(size)).f(canvasE, matrix, i10, c0920a);
            }
        }
        if (z6) {
            c0926g.c();
        }
        canvas.restore();
    }

    @Override // X3.b
    public final void l(boolean z6) {
        super.l(z6);
        Iterator it = this.f10020E.iterator();
        while (it.hasNext()) {
            ((b) it.next()).l(z6);
        }
    }

    @Override // X3.b
    public final void m(float f10) {
        this.f10026K = f10;
        super.m(f10);
        R3.f fVar = this.f10019D;
        e eVar = this.f10008p;
        if (fVar != null) {
            O3.a aVar = this.o.l;
            f10 = ((((Float) fVar.d()).floatValue() * eVar.f10038b.f5079n) - eVar.f10038b.l) / ((aVar.f5078m - aVar.l) + 0.01f);
        }
        if (fVar == null) {
            O3.a aVar2 = eVar.f10038b;
            f10 -= eVar.f10047n / (aVar2.f5078m - aVar2.l);
        }
        if (eVar.f10046m != 0.0f && !"__container".equals(eVar.c)) {
            f10 /= eVar.f10046m;
        }
        ArrayList arrayList = this.f10020E;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((b) arrayList.get(size)).m(f10);
        }
    }
}
