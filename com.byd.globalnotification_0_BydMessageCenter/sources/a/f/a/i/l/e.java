package a.f.a.i.l;

import a.f.a.i.d;
import a.f.a.i.l.b;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a.f.a.i.e f576a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a.f.a.i.e f579d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.InterfaceC0007b f581f;
    public b.a g;
    public ArrayList<l> h;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f577b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f578c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<o> f580e = new ArrayList<>();

    public e(a.f.a.i.e eVar) {
        new ArrayList();
        this.f581f = null;
        this.g = new b.a();
        this.h = new ArrayList<>();
        this.f576a = eVar;
        this.f579d = eVar;
    }

    public final void a(f fVar, int i, int i2, f fVar2, ArrayList<l> arrayList, l lVar) {
        o oVar = fVar.f585d;
        if (oVar.f608c == null) {
            a.f.a.i.e eVar = this.f576a;
            if (oVar == eVar.f558d || oVar == eVar.f559e) {
                return;
            }
            if (lVar == null) {
                lVar = new l(oVar, i2);
                arrayList.add(lVar);
            }
            oVar.f608c = lVar;
            lVar.f599b.add(oVar);
            for (d dVar : oVar.h.k) {
                if (dVar instanceof f) {
                    a((f) dVar, i, 0, fVar2, arrayList, lVar);
                }
            }
            for (d dVar2 : oVar.i.k) {
                if (dVar2 instanceof f) {
                    a((f) dVar2, i, 1, fVar2, arrayList, lVar);
                }
            }
            if (i == 1 && (oVar instanceof m)) {
                for (d dVar3 : ((m) oVar).k.k) {
                    if (dVar3 instanceof f) {
                        a((f) dVar3, i, 2, fVar2, arrayList, lVar);
                    }
                }
            }
            Iterator<f> it = oVar.h.l.iterator();
            while (it.hasNext()) {
                a(it.next(), i, 0, fVar2, arrayList, lVar);
            }
            Iterator<f> it2 = oVar.i.l.iterator();
            while (it2.hasNext()) {
                a(it2.next(), i, 1, fVar2, arrayList, lVar);
            }
            if (i == 1 && (oVar instanceof m)) {
                Iterator<f> it3 = ((m) oVar).k.l.iterator();
                while (it3.hasNext()) {
                    a(it3.next(), i, 2, fVar2, arrayList, lVar);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0010 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(a.f.a.i.e r19) {
        /*
            Method dump skipped, instruction units count: 588
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.e.b(a.f.a.i.e):boolean");
    }

    public void c() {
        o iVar;
        ArrayList<o> arrayList = this.f580e;
        arrayList.clear();
        this.f579d.f558d.f();
        this.f579d.f559e.f();
        arrayList.add(this.f579d.f558d);
        arrayList.add(this.f579d.f559e);
        HashSet hashSet = null;
        for (a.f.a.i.d dVar : this.f579d.v0) {
            if (dVar instanceof a.f.a.i.f) {
                iVar = new i(dVar);
            } else {
                if (dVar.A()) {
                    if (dVar.f556b == null) {
                        dVar.f556b = new c(dVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(dVar.f556b);
                } else {
                    arrayList.add(dVar.f558d);
                }
                if (dVar.B()) {
                    if (dVar.f557c == null) {
                        dVar.f557c = new c(dVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(dVar.f557c);
                } else {
                    arrayList.add(dVar.f559e);
                }
                if (dVar instanceof a.f.a.i.h) {
                    iVar = new j(dVar);
                }
            }
            arrayList.add(iVar);
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<o> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        for (o oVar : arrayList) {
            if (oVar.f607b != this.f579d) {
                oVar.d();
            }
        }
        this.h.clear();
        l.f597c = 0;
        e(this.f576a.f558d, 0, this.h);
        e(this.f576a.f559e, 1, this.h);
        this.f577b = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int d(a.f.a.i.e r17, int r18) {
        /*
            Method dump skipped, instruction units count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.e.d(a.f.a.i.e, int):int");
    }

    public final void e(o oVar, int i, ArrayList<l> arrayList) {
        for (d dVar : oVar.h.k) {
            if (dVar instanceof f) {
                a((f) dVar, i, 0, oVar.i, arrayList, null);
            } else if (dVar instanceof o) {
                a(((o) dVar).h, i, 0, oVar.i, arrayList, null);
            }
        }
        for (d dVar2 : oVar.i.k) {
            if (dVar2 instanceof f) {
                a((f) dVar2, i, 1, oVar.h, arrayList, null);
            } else if (dVar2 instanceof o) {
                a(((o) dVar2).i, i, 1, oVar.h, arrayList, null);
            }
        }
        if (i == 1) {
            for (d dVar3 : ((m) oVar).k.k) {
                if (dVar3 instanceof f) {
                    a((f) dVar3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    public final void f(a.f.a.i.d dVar, d.a aVar, int i, d.a aVar2, int i2) {
        b.a aVar3 = this.g;
        aVar3.f570a = aVar;
        aVar3.f571b = aVar2;
        aVar3.f572c = i;
        aVar3.f573d = i2;
        ((ConstraintLayout.b) this.f581f).b(dVar, aVar3);
        dVar.S(this.g.f574e);
        dVar.N(this.g.f575f);
        b.a aVar4 = this.g;
        dVar.G = aVar4.h;
        dVar.K(aVar4.g);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007a, code lost:
    
        if (r13 == r7) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007c, code lost:
    
        r0.m = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007f, code lost:
    
        r0.c(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a2, code lost:
    
        if (r11 == r7) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g() {
        /*
            r14 = this;
            a.f.a.i.d$a r6 = a.f.a.i.d.a.FIXED
            a.f.a.i.d$a r7 = a.f.a.i.d.a.MATCH_CONSTRAINT
            a.f.a.i.d$a r8 = a.f.a.i.d.a.WRAP_CONTENT
            a.f.a.i.e r0 = r14.f576a
            java.util.ArrayList<a.f.a.i.d> r0 = r0.v0
            java.util.Iterator r9 = r0.iterator()
        Le:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto Lb6
            java.lang.Object r0 = r9.next()
            r10 = r0
            a.f.a.i.d r10 = (a.f.a.i.d) r10
            boolean r0 = r10.f555a
            if (r0 == 0) goto L20
            goto Le
        L20:
            a.f.a.i.d$a[] r0 = r10.W
            r1 = 0
            r11 = r0[r1]
            r12 = 1
            r13 = r0[r12]
            int r0 = r10.t
            int r2 = r10.u
            if (r11 == r8) goto L35
            if (r11 != r7) goto L33
            if (r0 != r12) goto L33
            goto L35
        L33:
            r0 = r1
            goto L36
        L35:
            r0 = r12
        L36:
            if (r13 == r8) goto L3c
            if (r13 != r7) goto L3d
            if (r2 != r12) goto L3d
        L3c:
            r1 = r12
        L3d:
            a.f.a.i.l.k r2 = r10.f558d
            a.f.a.i.l.g r2 = r2.f610e
            boolean r3 = r2.j
            a.f.a.i.l.m r4 = r10.f559e
            a.f.a.i.l.g r4 = r4.f610e
            boolean r5 = r4.j
            if (r3 == 0) goto L5b
            if (r5 == 0) goto L5b
            int r3 = r2.g
            int r5 = r4.g
            r0 = r14
            r1 = r10
            r2 = r6
            r4 = r6
            r0.f(r1, r2, r3, r4, r5)
        L58:
            r10.f555a = r12
            goto La5
        L5b:
            if (r3 == 0) goto L83
            if (r1 == 0) goto L83
            a.f.a.i.l.k r0 = r10.f558d
            a.f.a.i.l.g r0 = r0.f610e
            int r3 = r0.g
            a.f.a.i.l.m r0 = r10.f559e
            a.f.a.i.l.g r0 = r0.f610e
            int r5 = r0.g
            r0 = r14
            r1 = r10
            r2 = r6
            r4 = r8
            r0.f(r1, r2, r3, r4, r5)
            a.f.a.i.l.m r0 = r10.f559e
            a.f.a.i.l.g r0 = r0.f610e
            int r1 = r10.l()
            if (r13 != r7) goto L7f
        L7c:
            r0.m = r1
            goto La5
        L7f:
            r0.c(r1)
            goto L58
        L83:
            if (r5 == 0) goto La5
            if (r0 == 0) goto La5
            a.f.a.i.l.k r0 = r10.f558d
            a.f.a.i.l.g r0 = r0.f610e
            int r3 = r0.g
            a.f.a.i.l.m r0 = r10.f559e
            a.f.a.i.l.g r0 = r0.f610e
            int r5 = r0.g
            r0 = r14
            r1 = r10
            r2 = r8
            r4 = r6
            r0.f(r1, r2, r3, r4, r5)
            a.f.a.i.l.k r0 = r10.f558d
            a.f.a.i.l.g r0 = r0.f610e
            int r1 = r10.u()
            if (r11 != r7) goto L7f
            goto L7c
        La5:
            boolean r0 = r10.f555a
            if (r0 == 0) goto Le
            a.f.a.i.l.m r0 = r10.f559e
            a.f.a.i.l.g r0 = r0.l
            if (r0 == 0) goto Le
            int r1 = r10.e0
            r0.c(r1)
            goto Le
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.e.g():void");
    }
}
