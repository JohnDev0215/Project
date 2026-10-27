package a.f.a.i.l;

import a.f.a.i.d;
import a.f.a.i.l.f;

/* JADX INFO: loaded from: classes.dex */
public class k extends o {
    public static int[] k = new int[2];

    public k(a.f.a.i.d dVar) {
        super(dVar);
        this.h.f586e = f.a.LEFT;
        this.i.f586e = f.a.RIGHT;
        this.f611f = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:116:0x027e, code lost:
    
        if (r15 != 1) goto L133;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0167  */
    @Override // a.f.a.i.l.o, a.f.a.i.l.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(a.f.a.i.l.d r18) {
        /*
            Method dump skipped, instruction units count: 997
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.k.a(a.f.a.i.l.d):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x01db  */
    @Override // a.f.a.i.l.o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d() {
        /*
            Method dump skipped, instruction units count: 995
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.l.k.d():void");
    }

    @Override // a.f.a.i.l.o
    public void e() {
        f fVar = this.h;
        if (fVar.j) {
            this.f607b.c0 = fVar.g;
        }
    }

    @Override // a.f.a.i.l.o
    public void f() {
        this.f608c = null;
        this.h.b();
        this.i.b();
        this.f610e.b();
        this.g = false;
    }

    @Override // a.f.a.i.l.o
    public boolean k() {
        return this.f609d != d.a.MATCH_CONSTRAINT || this.f607b.t == 0;
    }

    public final void m(int[] iArr, int i, int i2, int i3, int i4, float f2, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f2) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f2) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f2) + 0.5f);
        int i9 = (int) ((i6 / f2) + 0.5f);
        if (i8 <= i6 && i7 <= i7) {
            iArr[0] = i8;
            iArr[1] = i7;
        } else {
            if (i6 > i6 || i9 > i7) {
                return;
            }
            iArr[0] = i6;
            iArr[1] = i9;
        }
    }

    public void n() {
        this.g = false;
        this.h.b();
        this.h.j = false;
        this.i.b();
        this.i.j = false;
        this.f610e.j = false;
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("HorizontalRun ");
        sbD.append(this.f607b.m0);
        return sbD.toString();
    }
}
