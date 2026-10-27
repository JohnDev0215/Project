package X3;

import N1.C0420a;
import R3.n;
import a4.C0920a;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Q3.e, R3.a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f9995A;
    public BlurMaskFilter B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public P3.a f9996C;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f9997a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix f9998b = new Matrix();
    public final Matrix c = new Matrix();
    public final P3.a d = new P3.a(1, 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final P3.a f9999e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final P3.a f10000f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final P3.a f10001g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final P3.a f10002h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f10003i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final RectF f10004j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final RectF f10005k;
    public final RectF l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final RectF f10006m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Matrix f10007n;
    public final O3.j o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final e f10008p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final P f10009q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final R3.f f10010r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public b f10011s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public b f10012t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public List f10013u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ArrayList f10014v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final n f10015w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f10016x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f10017y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public P3.a f10018z;

    public b(O3.j jVar, e eVar) {
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.f9999e = new P3.a(mode);
        PorterDuff.Mode mode2 = PorterDuff.Mode.DST_OUT;
        this.f10000f = new P3.a(mode2);
        P3.a aVar = new P3.a(1, 0);
        this.f10001g = aVar;
        PorterDuff.Mode mode3 = PorterDuff.Mode.CLEAR;
        P3.a aVar2 = new P3.a();
        aVar2.setXfermode(new PorterDuffXfermode(mode3));
        this.f10002h = aVar2;
        this.f10003i = new RectF();
        this.f10004j = new RectF();
        this.f10005k = new RectF();
        this.l = new RectF();
        this.f10006m = new RectF();
        this.f10007n = new Matrix();
        this.f10014v = new ArrayList();
        this.f10016x = true;
        this.f9995A = 0.0f;
        this.o = jVar;
        this.f10008p = eVar;
        if (eVar.f10053u == 3) {
            aVar.setXfermode(new PorterDuffXfermode(mode2));
        } else {
            aVar.setXfermode(new PorterDuffXfermode(mode));
        }
        V3.d dVar = eVar.f10043i;
        dVar.getClass();
        n nVar = new n(dVar);
        this.f10015w = nVar;
        nVar.b(this);
        List list = eVar.f10042h;
        if (list != null && !list.isEmpty()) {
            P p10 = new P(list);
            this.f10009q = p10;
            Iterator it = ((ArrayList) p10.o).iterator();
            while (it.hasNext()) {
                ((R3.e) it.next()).a(this);
            }
            for (R3.e eVar2 : (ArrayList) this.f10009q.f18537m) {
                d(eVar2);
                eVar2.a(this);
            }
        }
        e eVar3 = this.f10008p;
        if (eVar3.f10052t.isEmpty()) {
            if (true != this.f10016x) {
                this.f10016x = true;
                this.o.invalidateSelf();
                return;
            }
            return;
        }
        R3.f fVar = new R3.f(1, eVar3.f10052t);
        this.f10010r = fVar;
        fVar.f7389b = true;
        fVar.a(new R3.a() { // from class: X3.a
            @Override // R3.a
            public final void b() {
                b bVar = this.f9994a;
                boolean z6 = bVar.f10010r.i() == 1.0f;
                if (z6 != bVar.f10016x) {
                    bVar.f10016x = z6;
                    bVar.o.invalidateSelf();
                }
            }
        });
        boolean z6 = ((Float) this.f10010r.d()).floatValue() == 1.0f;
        if (z6 != this.f10016x) {
            this.f10016x = z6;
            this.o.invalidateSelf();
        }
        d(this.f10010r);
    }

    @Override // Q3.e
    public void a(RectF rectF, Matrix matrix, boolean z6) {
        this.f10003i.set(0.0f, 0.0f, 0.0f, 0.0f);
        g();
        Matrix matrix2 = this.f10007n;
        matrix2.set(matrix);
        if (z6) {
            List list = this.f10013u;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    matrix2.preConcat(((b) this.f10013u.get(size)).f10015w.d());
                }
            } else {
                b bVar = this.f10012t;
                if (bVar != null) {
                    matrix2.preConcat(bVar.f10015w.d());
                }
            }
        }
        matrix2.preConcat(this.f10015w.d());
    }

    @Override // R3.a
    public final void b() {
        this.o.invalidateSelf();
    }

    @Override // Q3.c
    public final void c(List list, List list2) {
    }

    public final void d(R3.e eVar) {
        if (eVar == null) {
            return;
        }
        this.f10014v.add(eVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01c9  */
    @Override // Q3.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(android.graphics.Canvas r25, android.graphics.Matrix r26, int r27, a4.C0920a r28) {
        /*
            Method dump skipped, instruction units count: 1238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: X3.b.f(android.graphics.Canvas, android.graphics.Matrix, int, a4.a):void");
    }

    public final void g() {
        if (this.f10013u != null) {
            return;
        }
        if (this.f10012t == null) {
            this.f10013u = Collections.emptyList();
            return;
        }
        this.f10013u = new ArrayList();
        for (b bVar = this.f10012t; bVar != null; bVar = bVar.f10012t) {
            this.f10013u.add(bVar);
        }
    }

    public abstract void h(Canvas canvas, Matrix matrix, int i7, C0920a c0920a);

    public C0420a i() {
        return this.f10008p.f10055w;
    }

    public final boolean j() {
        P p10 = this.f10009q;
        return (p10 == null || ((ArrayList) p10.o).isEmpty()) ? false : true;
    }

    public final void k() {
        Y4.e eVar = this.o.l.f5069a;
        String str = this.f10008p.c;
        eVar.getClass();
    }

    public void l(boolean z6) {
        if (z6 && this.f10018z == null) {
            this.f10018z = new P3.a();
        }
        this.f10017y = z6;
    }

    public void m(float f10) {
        n nVar = this.f10015w;
        R3.f fVar = nVar.f7422j;
        if (fVar != null) {
            fVar.g(f10);
        }
        R3.f fVar2 = nVar.f7424m;
        if (fVar2 != null) {
            fVar2.g(f10);
        }
        R3.f fVar3 = nVar.f7425n;
        if (fVar3 != null) {
            fVar3.g(f10);
        }
        R3.i iVar = nVar.f7418f;
        if (iVar != null) {
            iVar.g(f10);
        }
        R3.e eVar = nVar.f7419g;
        if (eVar != null) {
            eVar.g(f10);
        }
        R3.h hVar = nVar.f7420h;
        if (hVar != null) {
            hVar.g(f10);
        }
        R3.f fVar4 = nVar.f7421i;
        if (fVar4 != null) {
            fVar4.g(f10);
        }
        R3.f fVar5 = nVar.f7423k;
        if (fVar5 != null) {
            fVar5.g(f10);
        }
        R3.f fVar6 = nVar.l;
        if (fVar6 != null) {
            fVar6.g(f10);
        }
        P p10 = this.f10009q;
        int i7 = 0;
        if (p10 != null) {
            int i10 = 0;
            while (true) {
                ArrayList arrayList = (ArrayList) p10.o;
                if (i10 >= arrayList.size()) {
                    break;
                }
                ((R3.e) arrayList.get(i10)).g(f10);
                i10++;
            }
        }
        R3.f fVar7 = this.f10010r;
        if (fVar7 != null) {
            fVar7.g(f10);
        }
        b bVar = this.f10011s;
        if (bVar != null) {
            bVar.m(f10);
        }
        while (true) {
            ArrayList arrayList2 = this.f10014v;
            if (i7 >= arrayList2.size()) {
                return;
            }
            ((R3.e) arrayList2.get(i7)).g(f10);
            i7++;
        }
    }
}
