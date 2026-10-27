package Q3;

import a4.C0920a;
import a4.C0926g;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d implements e, l, R3.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1.j f6136a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RectF f6137b;
    public final C0926g c;
    public final Matrix d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Path f6138e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f6139f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f6140g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f6141h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final O3.j f6142i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f6143j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final R3.n f6144k;

    public d(O3.j jVar, X3.b bVar, W3.m mVar, O3.a aVar) {
        V3.d dVar;
        String str = mVar.f9797a;
        List list = mVar.f9798b;
        ArrayList arrayList = new ArrayList(list.size());
        int i7 = 0;
        for (int i10 = 0; i10 < list.size(); i10++) {
            c cVarA = ((W3.b) list.get(i10)).a(jVar, aVar, bVar);
            if (cVarA != null) {
                arrayList.add(cVarA);
            }
        }
        while (true) {
            if (i7 >= list.size()) {
                dVar = null;
                break;
            }
            W3.b bVar2 = (W3.b) list.get(i7);
            if (bVar2 instanceof V3.d) {
                dVar = (V3.d) bVar2;
                break;
            }
            i7++;
        }
        this(jVar, bVar, mVar.c, arrayList, dVar);
    }

    @Override // Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        Matrix matrix2 = this.d;
        matrix2.set(matrix);
        R3.n nVar = this.f6144k;
        if (nVar != null) {
            matrix2.preConcat(nVar.d());
        }
        RectF rectF2 = this.f6139f;
        rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
        ArrayList arrayList = this.f6141h;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c cVar = (c) arrayList.get(size);
            if (cVar instanceof e) {
                ((e) cVar).a(rectF2, matrix2, z6);
                rectF.union(rectF2);
            }
        }
    }

    @Override // R3.a
    public final void b() {
        this.f6142i.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
        int size = list.size();
        ArrayList arrayList = this.f6141h;
        ArrayList arrayList2 = new ArrayList(arrayList.size() + size);
        arrayList2.addAll(list);
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            c cVar = (c) arrayList.get(size2);
            cVar.c(arrayList2, arrayList.subList(0, size2));
            arrayList2.add(cVar);
        }
    }

    public final List d() {
        if (this.f6143j == null) {
            this.f6143j = new ArrayList();
            int i7 = 0;
            while (true) {
                ArrayList arrayList = this.f6141h;
                if (i7 >= arrayList.size()) {
                    break;
                }
                c cVar = (c) arrayList.get(i7);
                if (cVar instanceof l) {
                    this.f6143j.add((l) cVar);
                }
                i7++;
            }
        }
        return this.f6143j;
    }

    @Override // Q3.l
    public final Path e() {
        Matrix matrix = this.d;
        matrix.reset();
        R3.n nVar = this.f6144k;
        if (nVar != null) {
            matrix.set(nVar.d());
        }
        Path path = this.f6138e;
        path.reset();
        if (this.f6140g) {
            return path;
        }
        ArrayList arrayList = this.f6141h;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c cVar = (c) arrayList.get(size);
            if (cVar instanceof l) {
                path.addPath(((l) cVar).e(), matrix);
            }
        }
        return path;
    }

    @Override // Q3.e
    public final void f(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        if (this.f6140g) {
            return;
        }
        Matrix matrix2 = this.d;
        matrix2.set(matrix);
        R3.n nVar = this.f6144k;
        if (nVar != null) {
            matrix2.preConcat(nVar.d());
            i7 = (int) (((((nVar.f7422j == null ? 100 : ((Integer) r1.d()).intValue()) / 100.0f) * i7) / 255.0f) * 255.0f);
        }
        O3.j jVar = this.f6142i;
        boolean z6 = (jVar.f5130z && g() && i7 != 255) || (c0920a != null && jVar.f5096A && g());
        int i10 = z6 ? 255 : i7;
        C0926g c0926g = this.c;
        if (z6) {
            RectF rectF = this.f6137b;
            rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            a(rectF, matrix, true);
            C1.j jVar2 = this.f6136a;
            jVar2.l = i7;
            if (c0920a != null) {
                if (Color.alpha(c0920a.d) > 0) {
                    jVar2.f944m = c0920a;
                } else {
                    jVar2.f944m = null;
                }
                c0920a = null;
            } else {
                jVar2.f944m = null;
            }
            canvas = c0926g.e(canvas, rectF, jVar2);
        } else if (c0920a != null) {
            C0920a c0920a2 = new C0920a(c0920a);
            c0920a2.b(i10);
            c0920a = c0920a2;
        }
        ArrayList arrayList = this.f6141h;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Object obj = arrayList.get(size);
            if (obj instanceof e) {
                ((e) obj).f(canvas, matrix2, i10, c0920a);
            }
        }
        if (z6) {
            c0926g.c();
        }
    }

    public final boolean g() {
        int i7 = 0;
        int i10 = 0;
        while (true) {
            ArrayList arrayList = this.f6141h;
            if (i7 >= arrayList.size()) {
                return false;
            }
            if ((arrayList.get(i7) instanceof e) && (i10 = i10 + 1) >= 2) {
                return true;
            }
            i7++;
        }
    }

    public d(O3.j jVar, X3.b bVar, boolean z6, ArrayList arrayList, V3.d dVar) {
        this.f6136a = new C1.j(7, false);
        this.f6137b = new RectF();
        this.c = new C0926g();
        this.d = new Matrix();
        this.f6138e = new Path();
        this.f6139f = new RectF();
        this.f6142i = jVar;
        this.f6140g = z6;
        this.f6141h = arrayList;
        if (dVar != null) {
            R3.n nVar = new R3.n(dVar);
            this.f6144k = nVar;
            nVar.a(bVar);
            nVar.b(this);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            c cVar = (c) arrayList.get(size);
            if (cVar instanceof j) {
                arrayList2.add((j) cVar);
            }
        }
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ((j) arrayList2.get(size2)).d(arrayList.listIterator(arrayList.size()));
        }
    }
}
