package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

/* JADX INFO: compiled from: ScrollEventAdapter.java */
/* JADX INFO: loaded from: classes.dex */
final class e extends RecyclerView.t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewPager2.i f2047a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ViewPager2 f2048b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f2049c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayoutManager f2050d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2051e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2052f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f2053g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2054h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2055a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f2056b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2057c;

        a() {
        }

        void a() {
            this.f2055a = -1;
            this.f2056b = 0.0f;
            this.f2057c = 0;
        }
    }

    e(ViewPager2 viewPager2) {
        this.f2048b = viewPager2;
        RecyclerView recyclerView = viewPager2.l;
        this.f2049c = recyclerView;
        this.f2050d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.f2053g = new a();
        n();
    }

    private void c(int i, float f2, int i2) {
        ViewPager2.i iVar = this.f2047a;
        if (iVar != null) {
            iVar.b(i, f2, i2);
        }
    }

    private void d(int i) {
        ViewPager2.i iVar = this.f2047a;
        if (iVar != null) {
            iVar.c(i);
        }
    }

    private void e(int i) {
        if ((this.f2051e == 3 && this.f2052f == 0) || this.f2052f == i) {
            return;
        }
        this.f2052f = i;
        ViewPager2.i iVar = this.f2047a;
        if (iVar != null) {
            iVar.a(i);
        }
    }

    private int f() {
        return this.f2050d.Z1();
    }

    private boolean k() {
        int i = this.f2051e;
        return i == 1 || i == 4;
    }

    private void n() {
        this.f2051e = 0;
        this.f2052f = 0;
        this.f2053g.a();
        this.f2054h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    private void p(boolean z) {
        this.m = z;
        this.f2051e = z ? 4 : 1;
        int i = this.i;
        if (i != -1) {
            this.f2054h = i;
            this.i = -1;
        } else if (this.f2054h == -1) {
            this.f2054h = f();
        }
        e(1);
    }

    private void q() {
        int top;
        a aVar = this.f2053g;
        int iZ1 = this.f2050d.Z1();
        aVar.f2055a = iZ1;
        if (iZ1 == -1) {
            aVar.a();
            return;
        }
        View viewC = this.f2050d.C(iZ1);
        if (viewC == null) {
            aVar.a();
            return;
        }
        int iA0 = this.f2050d.a0(viewC);
        int iJ0 = this.f2050d.j0(viewC);
        int iM0 = this.f2050d.m0(viewC);
        int iH = this.f2050d.H(viewC);
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
        if (this.f2050d.p2() == 0) {
            top = (viewC.getLeft() - iA0) - this.f2049c.getPaddingLeft();
            if (this.f2048b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewC.getTop() - iM0) - this.f2049c.getPaddingTop();
        }
        int i = -top;
        aVar.f2057c = i;
        if (i >= 0) {
            aVar.f2056b = height == 0 ? 0.0f : i / height;
        } else {
            if (!new androidx.viewpager2.widget.a(this.f2050d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f2057c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i) {
        boolean z = true;
        if (!(this.f2051e == 1 && this.f2052f == 1) && i == 1) {
            p(false);
            return;
        }
        if (k() && i == 2) {
            if (this.k) {
                e(2);
                this.j = true;
                return;
            }
            return;
        }
        if (k() && i == 0) {
            q();
            if (this.k) {
                a aVar = this.f2053g;
                if (aVar.f2057c == 0) {
                    int i2 = this.f2054h;
                    int i3 = aVar.f2055a;
                    if (i2 != i3) {
                        d(i3);
                    }
                } else {
                    z = false;
                }
            } else {
                int i4 = this.f2053g.f2055a;
                if (i4 != -1) {
                    c(i4, 0.0f, 0);
                }
            }
            if (z) {
                e(0);
                n();
            }
        }
        if (this.f2051e == 2 && i == 0 && this.l) {
            q();
            a aVar2 = this.f2053g;
            if (aVar2.f2057c == 0) {
                int i5 = this.i;
                int i6 = aVar2.f2055a;
                if (i5 != i6) {
                    if (i6 == -1) {
                        i6 = 0;
                    }
                    d(i6);
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
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f2048b
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
            androidx.viewpager2.widget.e$a r5 = r3.f2053g
            int r6 = r5.f2057c
            if (r6 == 0) goto L2f
            int r5 = r5.f2055a
            int r5 = r5 + r4
            goto L33
        L2f:
            androidx.viewpager2.widget.e$a r5 = r3.f2053g
            int r5 = r5.f2055a
        L33:
            r3.i = r5
            int r6 = r3.f2054h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.f2051e
            if (r5 != 0) goto L4b
            androidx.viewpager2.widget.e$a r5 = r3.f2053g
            int r5 = r5.f2055a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            androidx.viewpager2.widget.e$a r5 = r3.f2053g
            int r6 = r5.f2055a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f2056b
            int r5 = r5.f2057c
            r3.c(r6, r0, r5)
            androidx.viewpager2.widget.e$a r5 = r3.f2053g
            int r6 = r5.f2055a
            int r0 = r3.i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f2057c
            if (r5 != 0) goto L71
            int r5 = r3.f2052f
            if (r5 == r4) goto L71
            r3.e(r2)
            r3.n()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.e.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    double g() {
        q();
        a aVar = this.f2053g;
        return ((double) aVar.f2055a) + ((double) aVar.f2056b);
    }

    int h() {
        return this.f2052f;
    }

    boolean i() {
        return this.m;
    }

    boolean j() {
        return this.f2052f == 0;
    }

    void l() {
        this.l = true;
    }

    void m(int i, boolean z) {
        this.f2051e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.i != i;
        this.i = i;
        e(2);
        if (z2) {
            d(i);
        }
    }

    void o(ViewPager2.i iVar) {
        this.f2047a = iVar;
    }
}
