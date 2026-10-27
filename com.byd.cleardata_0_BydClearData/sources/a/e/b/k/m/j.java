package a.e.b.k.m;

import a.e.b.k.e;
import a.e.b.k.m.f;
import a.e.b.k.m.m;

/* JADX INFO: loaded from: classes.dex */
public class j extends m {
    private static int[] k = new int[2];

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f241a;

        static {
            int[] iArr = new int[m.b.values().length];
            f241a = iArr;
            try {
                iArr[m.b.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f241a[m.b.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f241a[m.b.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public j(a.e.b.k.e eVar) {
        super(eVar);
        this.h.e = f.a.LEFT;
        this.i.e = f.a.RIGHT;
        this.f = 0;
    }

    private void q(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 != -1) {
            if (i5 == 0) {
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                return;
            } else {
                if (i5 != 1) {
                    return;
                }
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                return;
            }
        }
        int i8 = (int) ((i7 * f) + 0.5f);
        int i9 = (int) ((i6 / f) + 0.5f);
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

    /* JADX WARN: Code restructure failed: missing block: B:116:0x0292, code lost:
    
        if (r14 != 1) goto L134;
     */
    @Override // a.e.b.k.m.m, a.e.b.k.m.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(a.e.b.k.m.d r17) {
        /*
            Method dump skipped, instruction units count: 1032
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.j.a(a.e.b.k.m.d):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x01c2  */
    @Override // a.e.b.k.m.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void d() {
        /*
            Method dump skipped, instruction units count: 932
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.j.d():void");
    }

    @Override // a.e.b.k.m.m
    public void e() {
        f fVar = this.h;
        if (fVar.j) {
            this.f248b.C0(fVar.g);
        }
    }

    @Override // a.e.b.k.m.m
    void f() {
        this.f249c = null;
        this.h.c();
        this.i.c();
        this.e.c();
        this.g = false;
    }

    @Override // a.e.b.k.m.m
    boolean m() {
        return this.f250d != e.b.MATCH_CONSTRAINT || this.f248b.m == 0;
    }

    void r() {
        this.g = false;
        this.h.c();
        this.h.j = false;
        this.i.c();
        this.i.j = false;
        this.e.j = false;
    }

    public String toString() {
        return "HorizontalRun " + this.f248b.p();
    }
}
