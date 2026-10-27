package a.f.a.i;

import a.b.k.l;
import a.f.a.g;
import a.f.a.i.l.n;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f544b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f545c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f546d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f547e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f548f;
    public a.f.a.g i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HashSet<c> f543a = null;
    public int g = 0;
    public int h = Integer.MIN_VALUE;

    public enum a {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public c(d dVar, a aVar) {
        this.f546d = dVar;
        this.f547e = aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0079 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(a.f.a.i.c r9, int r10, int r11, boolean r12) {
        /*
            r8 = this;
            r0 = 1
            if (r9 != 0) goto L7
            r8.h()
            return r0
        L7:
            if (r12 != 0) goto L7a
            a.f.a.i.c$a r12 = a.f.a.i.c.a.CENTER_Y
            a.f.a.i.c$a r1 = a.f.a.i.c.a.RIGHT
            a.f.a.i.c$a r2 = a.f.a.i.c.a.CENTER_X
            a.f.a.i.c$a r3 = a.f.a.i.c.a.LEFT
            a.f.a.i.c$a r4 = a.f.a.i.c.a.BASELINE
            a.f.a.i.c$a r5 = r9.f547e
            a.f.a.i.c$a r6 = r8.f547e
            r7 = 0
            if (r5 != r6) goto L29
            if (r6 != r4) goto L42
            a.f.a.i.d r12 = r9.f546d
            boolean r12 = r12.G
            if (r12 == 0) goto L44
            a.f.a.i.d r12 = r8.f546d
            boolean r12 = r12.G
            if (r12 != 0) goto L42
            goto L44
        L29:
            int r6 = r6.ordinal()
            switch(r6) {
                case 0: goto L44;
                case 1: goto L64;
                case 2: goto L4b;
                case 3: goto L64;
                case 4: goto L4b;
                case 5: goto L46;
                case 6: goto L3c;
                case 7: goto L44;
                case 8: goto L44;
                default: goto L30;
            }
        L30:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            a.f.a.i.c$a r8 = r8.f547e
            java.lang.String r8 = r8.name()
            r9.<init>(r8)
            throw r9
        L3c:
            if (r5 == r4) goto L44
            if (r5 == r2) goto L44
            if (r5 == r12) goto L44
        L42:
            r12 = r0
            goto L77
        L44:
            r12 = r7
            goto L77
        L46:
            if (r5 == r3) goto L44
            if (r5 != r1) goto L42
            goto L44
        L4b:
            a.f.a.i.c$a r1 = a.f.a.i.c.a.TOP
            if (r5 == r1) goto L56
            a.f.a.i.c$a r1 = a.f.a.i.c.a.BOTTOM
            if (r5 != r1) goto L54
            goto L56
        L54:
            r1 = r7
            goto L57
        L56:
            r1 = r0
        L57:
            a.f.a.i.d r2 = r9.f546d
            boolean r2 = r2 instanceof a.f.a.i.f
            if (r2 == 0) goto L62
            if (r1 != 0) goto L42
            if (r5 != r12) goto L44
            goto L42
        L62:
            r12 = r1
            goto L77
        L64:
            if (r5 == r3) goto L6b
            if (r5 != r1) goto L69
            goto L6b
        L69:
            r12 = r7
            goto L6c
        L6b:
            r12 = r0
        L6c:
            a.f.a.i.d r1 = r9.f546d
            boolean r1 = r1 instanceof a.f.a.i.f
            if (r1 == 0) goto L77
            if (r12 != 0) goto L42
            if (r5 != r2) goto L44
            goto L42
        L77:
            if (r12 != 0) goto L7a
            return r7
        L7a:
            r8.f548f = r9
            java.util.HashSet<a.f.a.i.c> r12 = r9.f543a
            if (r12 != 0) goto L87
            java.util.HashSet r12 = new java.util.HashSet
            r12.<init>()
            r9.f543a = r12
        L87:
            a.f.a.i.c r9 = r8.f548f
            java.util.HashSet<a.f.a.i.c> r9 = r9.f543a
            if (r9 == 0) goto L90
            r9.add(r8)
        L90:
            r8.g = r10
            r8.h = r11
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.c.a(a.f.a.i.c, int, int, boolean):boolean");
    }

    public void b(int i, ArrayList<n> arrayList, n nVar) {
        HashSet<c> hashSet = this.f543a;
        if (hashSet != null) {
            Iterator<c> it = hashSet.iterator();
            while (it.hasNext()) {
                l.i.t(it.next().f546d, i, arrayList, nVar);
            }
        }
    }

    public int c() {
        if (this.f545c) {
            return this.f544b;
        }
        return 0;
    }

    public int d() {
        c cVar;
        if (this.f546d.k0 == 8) {
            return 0;
        }
        int i = this.h;
        return (i == Integer.MIN_VALUE || (cVar = this.f548f) == null || cVar.f546d.k0 != 8) ? this.g : i;
    }

    public boolean e() {
        c cVar;
        HashSet<c> hashSet = this.f543a;
        if (hashSet == null) {
            return false;
        }
        for (c cVar2 : hashSet) {
            switch (cVar2.f547e) {
                case NONE:
                case BASELINE:
                case CENTER:
                case CENTER_X:
                case CENTER_Y:
                    cVar = null;
                    break;
                case LEFT:
                    cVar = cVar2.f546d.N;
                    break;
                case TOP:
                    cVar = cVar2.f546d.O;
                    break;
                case RIGHT:
                    cVar = cVar2.f546d.L;
                    break;
                case BOTTOM:
                    cVar = cVar2.f546d.M;
                    break;
                default:
                    throw new AssertionError(cVar2.f547e.name());
            }
            if (cVar.g()) {
                return true;
            }
        }
        return false;
    }

    public boolean f() {
        HashSet<c> hashSet = this.f543a;
        return hashSet != null && hashSet.size() > 0;
    }

    public boolean g() {
        return this.f548f != null;
    }

    public void h() {
        HashSet<c> hashSet;
        c cVar = this.f548f;
        if (cVar != null && (hashSet = cVar.f543a) != null) {
            hashSet.remove(this);
            if (this.f548f.f543a.size() == 0) {
                this.f548f.f543a = null;
            }
        }
        this.f543a = null;
        this.f548f = null;
        this.g = 0;
        this.h = Integer.MIN_VALUE;
        this.f545c = false;
        this.f544b = 0;
    }

    public void i() {
        a.f.a.g gVar = this.i;
        if (gVar == null) {
            this.i = new a.f.a.g(g.a.UNRESTRICTED);
        } else {
            gVar.c();
        }
    }

    public void j(int i) {
        this.f544b = i;
        this.f545c = true;
    }

    public String toString() {
        return this.f546d.m0 + ":" + this.f547e.toString();
    }
}
