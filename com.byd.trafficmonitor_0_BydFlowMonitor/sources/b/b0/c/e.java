package b.b0.c;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: ScrollEventAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends RecyclerView.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewPager2.i f1871a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ViewPager2 f1872b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView f1873c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinearLayoutManager f1874d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1875e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1876f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f1877g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1878h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1879i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1880a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1881b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1882c;

        public void a() {
            this.f1880a = -1;
            this.f1881b = 0.0f;
            this.f1882c = 0;
        }
    }

    public e(ViewPager2 viewPager2) {
        this.f1872b = viewPager2;
        RecyclerView recyclerView = viewPager2.l;
        this.f1873c = recyclerView;
        this.f1874d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.f1877g = new a();
        n();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i2) {
        boolean z = true;
        if (!(this.f1875e == 1 && this.f1876f == 1) && i2 == 1) {
            p(false);
            return;
        }
        if (k() && i2 == 2) {
            if (this.k) {
                e(2);
                this.j = true;
                return;
            }
            return;
        }
        if (k() && i2 == 0) {
            q();
            if (this.k) {
                a aVar = this.f1877g;
                if (aVar.f1882c == 0) {
                    int i3 = this.f1878h;
                    int i4 = aVar.f1880a;
                    if (i3 != i4) {
                        d(i4);
                    }
                } else {
                    z = false;
                }
            } else {
                int i5 = this.f1877g.f1880a;
                if (i5 != -1) {
                    c(i5, 0.0f, 0);
                }
            }
            if (z) {
                e(0);
                n();
            }
        }
        if (this.f1875e == 2 && i2 == 0 && this.l) {
            q();
            a aVar2 = this.f1877g;
            if (aVar2.f1882c == 0) {
                int i6 = this.f1879i;
                int i7 = aVar2.f1880a;
                if (i6 != i7) {
                    if (i7 == -1) {
                        i7 = 0;
                    }
                    d(i7);
                }
                e(0);
                n();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002f  */
    @Override // androidx.recyclerview.widget.RecyclerView.t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.k = r4
            r3.q()
            boolean r0 = r3.j
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L3d
            r3.j = r2
            if (r6 > 0) goto L22
            if (r6 != 0) goto L20
            if (r5 >= 0) goto L16
            r5 = r4
            goto L17
        L16:
            r5 = r2
        L17:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f1872b
            boolean r6 = r6.d()
            if (r5 != r6) goto L20
            goto L22
        L20:
            r5 = r2
            goto L23
        L22:
            r5 = r4
        L23:
            if (r5 == 0) goto L2f
            b.b0.c.e$a r5 = r3.f1877g
            int r6 = r5.f1882c
            if (r6 == 0) goto L2f
            int r5 = r5.f1880a
            int r5 = r5 + r4
            goto L33
        L2f:
            b.b0.c.e$a r5 = r3.f1877g
            int r5 = r5.f1880a
        L33:
            r3.f1879i = r5
            int r6 = r3.f1878h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.f1875e
            if (r5 != 0) goto L4b
            b.b0.c.e$a r5 = r3.f1877g
            int r5 = r5.f1880a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            b.b0.c.e$a r5 = r3.f1877g
            int r6 = r5.f1880a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f1881b
            int r5 = r5.f1882c
            r3.c(r6, r0, r5)
            b.b0.c.e$a r5 = r3.f1877g
            int r6 = r5.f1880a
            int r0 = r3.f1879i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f1882c
            if (r5 != 0) goto L71
            int r5 = r3.f1876f
            if (r5 == r4) goto L71
            r3.e(r2)
            r3.n()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.b0.c.e.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i2, float f2, int i3) {
        ViewPager2.i iVar = this.f1871a;
        if (iVar != null) {
            iVar.b(i2, f2, i3);
        }
    }

    public final void d(int i2) {
        ViewPager2.i iVar = this.f1871a;
        if (iVar != null) {
            iVar.c(i2);
        }
    }

    public final void e(int i2) {
        if ((this.f1875e == 3 && this.f1876f == 0) || this.f1876f == i2) {
            return;
        }
        this.f1876f = i2;
        ViewPager2.i iVar = this.f1871a;
        if (iVar != null) {
            iVar.a(i2);
        }
    }

    public final int f() {
        return this.f1874d.Z1();
    }

    public double g() {
        q();
        a aVar = this.f1877g;
        return ((double) aVar.f1880a) + ((double) aVar.f1881b);
    }

    public int h() {
        return this.f1876f;
    }

    public boolean i() {
        return this.m;
    }

    public boolean j() {
        return this.f1876f == 0;
    }

    public final boolean k() {
        int i2 = this.f1875e;
        return i2 == 1 || i2 == 4;
    }

    public void l() {
        this.l = true;
    }

    public void m(int i2, boolean z) {
        this.f1875e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.f1879i != i2;
        this.f1879i = i2;
        e(2);
        if (z2) {
            d(i2);
        }
    }

    public final void n() {
        this.f1875e = 0;
        this.f1876f = 0;
        this.f1877g.a();
        this.f1878h = -1;
        this.f1879i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    public void o(ViewPager2.i iVar) {
        this.f1871a = iVar;
    }

    public final void p(boolean z) {
        this.m = z;
        this.f1875e = z ? 4 : 1;
        int i2 = this.f1879i;
        if (i2 != -1) {
            this.f1878h = i2;
            this.f1879i = -1;
        } else if (this.f1878h == -1) {
            this.f1878h = f();
        }
        e(1);
    }

    public final void q() {
        int top;
        a aVar = this.f1877g;
        int iZ1 = this.f1874d.Z1();
        aVar.f1880a = iZ1;
        if (iZ1 == -1) {
            aVar.a();
            return;
        }
        View viewC = this.f1874d.C(iZ1);
        if (viewC == null) {
            aVar.a();
            return;
        }
        int iA0 = this.f1874d.a0(viewC);
        int iJ0 = this.f1874d.j0(viewC);
        int iM0 = this.f1874d.m0(viewC);
        int iH = this.f1874d.H(viewC);
        ViewGroup.LayoutParams layoutParams = viewC.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            iA0 += marginLayoutParams.leftMargin;
            iJ0 += marginLayoutParams.rightMargin;
            iM0 += marginLayoutParams.topMargin;
            iH += marginLayoutParams.bottomMargin;
        }
        int height = viewC.getHeight() + iM0 + iH;
        int width = viewC.getWidth() + iA0 + iJ0;
        if (this.f1874d.p2() == 0) {
            top = (viewC.getLeft() - iA0) - this.f1873c.getPaddingLeft();
            if (this.f1872b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewC.getTop() - iM0) - this.f1873c.getPaddingTop();
        }
        int i2 = -top;
        aVar.f1882c = i2;
        if (i2 >= 0) {
            aVar.f1881b = height == 0 ? 0.0f : i2 / height;
        } else {
            if (!new b.b0.c.a(this.f1874d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f1882c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }
}
