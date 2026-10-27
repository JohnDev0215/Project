package d1;

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
    public ViewPager2.i f2686a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ViewPager2 f2687b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView f2688c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinearLayoutManager f2689d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2690e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2691f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f2692g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2693h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2694i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f2695j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f2696k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2697l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2698m;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2699a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2700b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2701c;

        public void a() {
            this.f2699a = -1;
            this.f2700b = 0.0f;
            this.f2701c = 0;
        }
    }

    public e(ViewPager2 viewPager2) {
        this.f2687b = viewPager2;
        RecyclerView recyclerView = viewPager2.f1726l;
        this.f2688c = recyclerView;
        this.f2689d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.f2692g = new a();
        n();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i3) {
        boolean z3 = true;
        if (!(this.f2690e == 1 && this.f2691f == 1) && i3 == 1) {
            p(false);
            return;
        }
        if (k() && i3 == 2) {
            if (this.f2696k) {
                e(2);
                this.f2695j = true;
                return;
            }
            return;
        }
        if (k() && i3 == 0) {
            q();
            if (this.f2696k) {
                a aVar = this.f2692g;
                if (aVar.f2701c == 0) {
                    int i4 = this.f2693h;
                    int i5 = aVar.f2699a;
                    if (i4 != i5) {
                        d(i5);
                    }
                } else {
                    z3 = false;
                }
            } else {
                int i6 = this.f2692g.f2699a;
                if (i6 != -1) {
                    c(i6, 0.0f, 0);
                }
            }
            if (z3) {
                e(0);
                n();
            }
        }
        if (this.f2690e == 2 && i3 == 0 && this.f2697l) {
            q();
            a aVar2 = this.f2692g;
            if (aVar2.f2701c == 0) {
                int i7 = this.f2694i;
                int i8 = aVar2.f2699a;
                if (i7 != i8) {
                    if (i8 == -1) {
                        i8 = 0;
                    }
                    d(i8);
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
            r3.f2696k = r4
            r3.q()
            boolean r0 = r3.f2695j
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L3d
            r3.f2695j = r2
            if (r6 > 0) goto L22
            if (r6 != 0) goto L20
            if (r5 >= 0) goto L16
            r5 = r4
            goto L17
        L16:
            r5 = r2
        L17:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f2687b
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
            d1.e$a r5 = r3.f2692g
            int r6 = r5.f2701c
            if (r6 == 0) goto L2f
            int r5 = r5.f2699a
            int r5 = r5 + r4
            goto L33
        L2f:
            d1.e$a r5 = r3.f2692g
            int r5 = r5.f2699a
        L33:
            r3.f2694i = r5
            int r6 = r3.f2693h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.f2690e
            if (r5 != 0) goto L4b
            d1.e$a r5 = r3.f2692g
            int r5 = r5.f2699a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            d1.e$a r5 = r3.f2692g
            int r6 = r5.f2699a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f2700b
            int r5 = r5.f2701c
            r3.c(r6, r0, r5)
            d1.e$a r5 = r3.f2692g
            int r6 = r5.f2699a
            int r0 = r3.f2694i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f2701c
            if (r5 != 0) goto L71
            int r5 = r3.f2691f
            if (r5 == r4) goto L71
            r3.e(r2)
            r3.n()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: d1.e.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i3, float f3, int i4) {
        ViewPager2.i iVar = this.f2686a;
        if (iVar != null) {
            iVar.b(i3, f3, i4);
        }
    }

    public final void d(int i3) {
        ViewPager2.i iVar = this.f2686a;
        if (iVar != null) {
            iVar.c(i3);
        }
    }

    public final void e(int i3) {
        if ((this.f2690e == 3 && this.f2691f == 0) || this.f2691f == i3) {
            return;
        }
        this.f2691f = i3;
        ViewPager2.i iVar = this.f2686a;
        if (iVar != null) {
            iVar.a(i3);
        }
    }

    public final int f() {
        return this.f2689d.Z1();
    }

    public double g() {
        q();
        a aVar = this.f2692g;
        return ((double) aVar.f2699a) + ((double) aVar.f2700b);
    }

    public int h() {
        return this.f2691f;
    }

    public boolean i() {
        return this.f2698m;
    }

    public boolean j() {
        return this.f2691f == 0;
    }

    public final boolean k() {
        int i3 = this.f2690e;
        return i3 == 1 || i3 == 4;
    }

    public void l() {
        this.f2697l = true;
    }

    public void m(int i3, boolean z3) {
        this.f2690e = z3 ? 2 : 3;
        this.f2698m = false;
        boolean z4 = this.f2694i != i3;
        this.f2694i = i3;
        e(2);
        if (z4) {
            d(i3);
        }
    }

    public final void n() {
        this.f2690e = 0;
        this.f2691f = 0;
        this.f2692g.a();
        this.f2693h = -1;
        this.f2694i = -1;
        this.f2695j = false;
        this.f2696k = false;
        this.f2698m = false;
        this.f2697l = false;
    }

    public void o(ViewPager2.i iVar) {
        this.f2686a = iVar;
    }

    public final void p(boolean z3) {
        this.f2698m = z3;
        this.f2690e = z3 ? 4 : 1;
        int i3 = this.f2694i;
        if (i3 != -1) {
            this.f2693h = i3;
            this.f2694i = -1;
        } else if (this.f2693h == -1) {
            this.f2693h = f();
        }
        e(1);
    }

    public final void q() {
        int top;
        a aVar = this.f2692g;
        int iZ1 = this.f2689d.Z1();
        aVar.f2699a = iZ1;
        if (iZ1 == -1) {
            aVar.a();
            return;
        }
        View viewC = this.f2689d.C(iZ1);
        if (viewC == null) {
            aVar.a();
            return;
        }
        int iA0 = this.f2689d.a0(viewC);
        int iJ0 = this.f2689d.j0(viewC);
        int iM0 = this.f2689d.m0(viewC);
        int iH = this.f2689d.H(viewC);
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
        if (this.f2689d.p2() == 0) {
            top = (viewC.getLeft() - iA0) - this.f2688c.getPaddingLeft();
            if (this.f2687b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewC.getTop() - iM0) - this.f2688c.getPaddingTop();
        }
        int i3 = -top;
        aVar.f2701c = i3;
        if (i3 >= 0) {
            aVar.f2700b = height == 0 ? 0.0f : i3 / height;
        } else {
            if (!new d1.a(this.f2689d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f2701c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }
}
