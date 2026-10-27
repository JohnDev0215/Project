package Q3;

import A7.D;
import android.graphics.Path;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class q implements l, R3.a, c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f6213b;
    public final O3.j c;
    public final R3.l d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6214e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6212a = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final D f6215f = new D(1, false);

    public q(O3.j jVar, X3.b bVar, W3.n nVar) {
        nVar.getClass();
        this.f6213b = nVar.d;
        this.c = jVar;
        R3.l lVar = new R3.l((List) nVar.c.f476m);
        this.d = lVar;
        bVar.d(lVar);
        lVar.a(this);
    }

    @Override // R3.a
    public final void b() {
        this.f6214e = false;
        this.c.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    @Override // Q3.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.util.List r6, java.util.List r7) {
        /*
            r5 = this;
            r7 = 0
            r0 = 0
        L2:
            r1 = r6
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r2 = r1.size()
            if (r0 >= r2) goto L40
            java.lang.Object r1 = r1.get(r0)
            Q3.c r1 = (Q3.c) r1
            boolean r2 = r1 instanceof Q3.s
            if (r2 == 0) goto L28
            r2 = r1
            Q3.s r2 = (Q3.s) r2
            int r3 = r2.c
            r4 = 1
            if (r3 != r4) goto L28
            A7.D r1 = r5.f6215f
            java.util.ArrayList r1 = r1.l
            r1.add(r2)
            r2.d(r5)
            goto L3d
        L28:
            boolean r2 = r1 instanceof Q3.p
            if (r2 == 0) goto L3d
            if (r7 != 0) goto L33
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L33:
            Q3.p r1 = (Q3.p) r1
            R3.e r2 = r1.f6211b
            r2.a(r5)
            r7.add(r1)
        L3d:
            int r0 = r0 + 1
            goto L2
        L40:
            R3.l r5 = r5.d
            r5.f7410j = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Q3.q.c(java.util.List, java.util.List):void");
    }

    @Override // Q3.l
    public final Path e() {
        boolean z6 = this.f6214e;
        Path path = this.f6212a;
        R3.l lVar = this.d;
        if (z6) {
            lVar.getClass();
            return path;
        }
        path.reset();
        if (this.f6213b) {
            this.f6214e = true;
            return path;
        }
        Path path2 = (Path) lVar.d();
        if (path2 == null) {
            return path;
        }
        path.set(path2);
        path.setFillType(Path.FillType.EVEN_ODD);
        this.f6215f.l(path);
        this.f6214e = true;
        return path;
    }
}
