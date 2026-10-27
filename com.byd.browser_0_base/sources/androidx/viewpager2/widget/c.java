package androidx.viewpager2.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* JADX INFO: compiled from: ScrollEventAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends RecyclerView.q {
    public ViewPager2.g e;
    public final ViewPager2 f;
    public final RecyclerView g;
    public final LinearLayoutManager h;
    public int i;
    public int j;
    public final a k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    public static final class a {
        public int a;
        public float b;
        public int c;
    }

    public c(ViewPager2 viewPager2) {
        this.f = viewPager2;
        ViewPager2.k kVar = viewPager2.n;
        this.g = kVar;
        this.h = (LinearLayoutManager) kVar.getLayoutManager();
        this.k = new a();
        e();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.q
    public final void a(int i, RecyclerView recyclerView) {
        ViewPager2.g gVar;
        ViewPager2.g gVar2;
        int i2 = this.i;
        boolean z = true;
        if (!(i2 == 1 && this.j == 1) && i == 1) {
            this.q = false;
            this.i = 1;
            int i3 = this.m;
            if (i3 != -1) {
                this.l = i3;
                this.m = -1;
            } else if (this.l == -1) {
                this.l = this.h.b();
            }
            c(1);
            return;
        }
        if ((i2 == 1 || i2 == 4) && i == 2) {
            if (this.o) {
                c(2);
                this.n = true;
                return;
            }
            return;
        }
        boolean z2 = i2 == 1 || i2 == 4;
        a aVar = this.k;
        if (z2 && i == 0) {
            f();
            if (!this.o) {
                int i4 = aVar.a;
                if (i4 != -1 && (gVar2 = this.e) != null) {
                    gVar2.b(i4, 0, 0.0f);
                }
            } else if (aVar.c == 0) {
                int i5 = this.l;
                int i6 = aVar.a;
                if (i5 != i6 && (gVar = this.e) != null) {
                    gVar.c(i6);
                }
            } else {
                z = false;
            }
            if (z) {
                c(0);
                e();
            }
        }
        if (this.i == 2 && i == 0 && this.p) {
            f();
            if (aVar.c == 0) {
                int i7 = this.m;
                int i8 = aVar.a;
                if (i7 != i8) {
                    if (i8 == -1) {
                        i8 = 0;
                    }
                    ViewPager2.g gVar3 = this.e;
                    if (gVar3 != null) {
                        gVar3.c(i8);
                    }
                }
                c(0);
                e();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036  */
    @Override // androidx.recyclerview.widget.RecyclerView.q
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(androidx.recyclerview.widget.RecyclerView r6, int r7, int r8) {
        /*
            r5 = this;
            r6 = 1
            r5.o = r6
            r5.f()
            boolean r0 = r5.n
            r1 = -1
            androidx.viewpager2.widget.c$a r2 = r5.k
            r3 = 0
            if (r0 == 0) goto L46
            r5.n = r3
            if (r8 > 0) goto L2b
            if (r8 != 0) goto L29
            if (r7 >= 0) goto L18
            r7 = r6
            goto L19
        L18:
            r7 = r3
        L19:
            androidx.viewpager2.widget.ViewPager2 r8 = r5.f
            androidx.viewpager2.widget.ViewPager2$f r8 = r8.k
            int r8 = r8.E()
            if (r8 != r6) goto L25
            r8 = r6
            goto L26
        L25:
            r8 = r3
        L26:
            if (r7 != r8) goto L29
            goto L2b
        L29:
            r7 = r3
            goto L2c
        L2b:
            r7 = r6
        L2c:
            if (r7 == 0) goto L36
            int r7 = r2.c
            if (r7 == 0) goto L36
            int r7 = r2.a
            int r7 = r7 + r6
            goto L38
        L36:
            int r7 = r2.a
        L38:
            r5.m = r7
            int r8 = r5.l
            if (r8 == r7) goto L56
            androidx.viewpager2.widget.ViewPager2$g r8 = r5.e
            if (r8 == 0) goto L56
            r8.c(r7)
            goto L56
        L46:
            int r7 = r5.i
            if (r7 != 0) goto L56
            int r7 = r2.a
            if (r7 != r1) goto L4f
            r7 = r3
        L4f:
            androidx.viewpager2.widget.ViewPager2$g r8 = r5.e
            if (r8 == 0) goto L56
            r8.c(r7)
        L56:
            int r7 = r2.a
            if (r7 != r1) goto L5b
            r7 = r3
        L5b:
            float r8 = r2.b
            int r0 = r2.c
            androidx.viewpager2.widget.ViewPager2$g r4 = r5.e
            if (r4 == 0) goto L66
            r4.b(r7, r0, r8)
        L66:
            int r7 = r2.a
            int r8 = r5.m
            if (r7 == r8) goto L6e
            if (r8 != r1) goto L7c
        L6e:
            int r7 = r2.c
            if (r7 != 0) goto L7c
            int r7 = r5.j
            if (r7 == r6) goto L7c
            r5.c(r3)
            r5.e()
        L7c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.c.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i) {
        if ((this.i == 3 && this.j == 0) || this.j == i) {
            return;
        }
        this.j = i;
        ViewPager2.g gVar = this.e;
        if (gVar != null) {
            gVar.a(i);
        }
    }

    public final void e() {
        this.i = 0;
        this.j = 0;
        a aVar = this.k;
        aVar.a = -1;
        aVar.b = 0.0f;
        aVar.c = 0;
        this.l = -1;
        this.m = -1;
        this.n = false;
        this.o = false;
        this.q = false;
        this.p = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f() {
        /*
            Method dump skipped, instruction units count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.c.f():void");
    }
}
