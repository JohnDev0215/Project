package b.u.b;

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
    public ViewPager2.i f1581a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ViewPager2 f1582b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final RecyclerView f1583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinearLayoutManager f1584d;
    public int e;
    public int f;
    public a g;
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1585a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1586b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1587c;

        public void a() {
            this.f1585a = -1;
            this.f1586b = 0.0f;
            this.f1587c = 0;
        }
    }

    public e(ViewPager2 viewPager2) {
        this.f1582b = viewPager2;
        RecyclerView recyclerView = viewPager2.l;
        this.f1583c = recyclerView;
        this.f1584d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.g = new a();
        n();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i) {
        boolean z = true;
        if (!(this.e == 1 && this.f == 1) && i == 1) {
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
                a aVar = this.g;
                if (aVar.f1587c == 0) {
                    int i2 = this.h;
                    int i3 = aVar.f1585a;
                    if (i2 != i3) {
                        d(i3);
                    }
                } else {
                    z = false;
                }
            } else {
                int i4 = this.g.f1585a;
                if (i4 != -1) {
                    c(i4, 0.0f, 0);
                }
            }
            if (z) {
                e(0);
                n();
            }
        }
        if (this.e == 2 && i == 0 && this.l) {
            q();
            a aVar2 = this.g;
            if (aVar2.f1587c == 0) {
                int i5 = this.i;
                int i6 = aVar2.f1585a;
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
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f1582b
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
            b.u.b.e$a r5 = r3.g
            int r6 = r5.f1587c
            if (r6 == 0) goto L2f
            int r5 = r5.f1585a
            int r5 = r5 + r4
            goto L33
        L2f:
            b.u.b.e$a r5 = r3.g
            int r5 = r5.f1585a
        L33:
            r3.i = r5
            int r6 = r3.h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.e
            if (r5 != 0) goto L4b
            b.u.b.e$a r5 = r3.g
            int r5 = r5.f1585a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            b.u.b.e$a r5 = r3.g
            int r6 = r5.f1585a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f1586b
            int r5 = r5.f1587c
            r3.c(r6, r0, r5)
            b.u.b.e$a r5 = r3.g
            int r6 = r5.f1585a
            int r0 = r3.i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f1587c
            if (r5 != 0) goto L71
            int r5 = r3.f
            if (r5 == r4) goto L71
            r3.e(r2)
            r3.n()
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: b.u.b.e.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i, float f, int i2) {
        ViewPager2.i iVar = this.f1581a;
        if (iVar != null) {
            iVar.b(i, f, i2);
        }
    }

    public final void d(int i) {
        ViewPager2.i iVar = this.f1581a;
        if (iVar != null) {
            iVar.c(i);
        }
    }

    public final void e(int i) {
        if ((this.e == 3 && this.f == 0) || this.f == i) {
            return;
        }
        this.f = i;
        ViewPager2.i iVar = this.f1581a;
        if (iVar != null) {
            iVar.a(i);
        }
    }

    public final int f() {
        return this.f1584d.Z1();
    }

    public double g() {
        q();
        a aVar = this.g;
        return ((double) aVar.f1585a) + ((double) aVar.f1586b);
    }

    public int h() {
        return this.f;
    }

    public boolean i() {
        return this.m;
    }

    public boolean j() {
        return this.f == 0;
    }

    public final boolean k() {
        int i = this.e;
        return i == 1 || i == 4;
    }

    public void l() {
        this.l = true;
    }

    public void m(int i, boolean z) {
        this.e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.i != i;
        this.i = i;
        e(2);
        if (z2) {
            d(i);
        }
    }

    public final void n() {
        this.e = 0;
        this.f = 0;
        this.g.a();
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    public void o(ViewPager2.i iVar) {
        this.f1581a = iVar;
    }

    public final void p(boolean z) {
        this.m = z;
        this.e = z ? 4 : 1;
        int i = this.i;
        if (i != -1) {
            this.h = i;
            this.i = -1;
        } else if (this.h == -1) {
            this.h = f();
        }
        e(1);
    }

    public final void q() {
        int top;
        a aVar = this.g;
        int iZ1 = this.f1584d.Z1();
        aVar.f1585a = iZ1;
        if (iZ1 == -1) {
            aVar.a();
            return;
        }
        View viewC = this.f1584d.C(iZ1);
        if (viewC == null) {
            aVar.a();
            return;
        }
        int iA0 = this.f1584d.a0(viewC);
        int iJ0 = this.f1584d.j0(viewC);
        int iM0 = this.f1584d.m0(viewC);
        int iH = this.f1584d.H(viewC);
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
        if (this.f1584d.p2() == 0) {
            top = (viewC.getLeft() - iA0) - this.f1583c.getPaddingLeft();
            if (this.f1582b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewC.getTop() - iM0) - this.f1583c.getPaddingTop();
        }
        int i = -top;
        aVar.f1587c = i;
        if (i >= 0) {
            aVar.f1586b = height == 0 ? 0.0f : i / height;
        } else {
            if (!new b.u.b.a(this.f1584d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f1587c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }
}
