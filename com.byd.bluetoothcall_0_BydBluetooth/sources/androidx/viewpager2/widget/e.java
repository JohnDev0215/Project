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
    private ViewPager2.i f1955a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ViewPager2 f1956b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f1957c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayoutManager f1958d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1959e;
    private int f;
    private a g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1960a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1961b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1962c;

        a() {
        }

        void a() {
            this.f1960a = -1;
            this.f1961b = 0.0f;
            this.f1962c = 0;
        }
    }

    e(ViewPager2 viewPager2) {
        this.f1956b = viewPager2;
        RecyclerView recyclerView = viewPager2.l;
        this.f1957c = recyclerView;
        this.f1958d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.g = new a();
        n();
    }

    private void c(int i, float f, int i2) {
        ViewPager2.i iVar = this.f1955a;
        if (iVar != null) {
            iVar.b(i, f, i2);
        }
    }

    private void d(int i) {
        ViewPager2.i iVar = this.f1955a;
        if (iVar != null) {
            iVar.c(i);
        }
    }

    private void e(int i) {
        if ((this.f1959e == 3 && this.f == 0) || this.f == i) {
            return;
        }
        this.f = i;
        ViewPager2.i iVar = this.f1955a;
        if (iVar != null) {
            iVar.a(i);
        }
    }

    private int f() {
        return this.f1958d.a2();
    }

    private boolean k() {
        int i = this.f1959e;
        return i == 1 || i == 4;
    }

    private void n() {
        this.f1959e = 0;
        this.f = 0;
        this.g.a();
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    private void p(boolean z) {
        this.m = z;
        this.f1959e = z ? 4 : 1;
        int i = this.i;
        if (i != -1) {
            this.h = i;
            this.i = -1;
        } else if (this.h == -1) {
            this.h = f();
        }
        e(1);
    }

    private void q() {
        int top;
        a aVar = this.g;
        int iA2 = this.f1958d.a2();
        aVar.f1960a = iA2;
        if (iA2 == -1) {
            aVar.a();
            return;
        }
        View viewC = this.f1958d.C(iA2);
        if (viewC == null) {
            aVar.a();
            return;
        }
        int iA0 = this.f1958d.a0(viewC);
        int iJ0 = this.f1958d.j0(viewC);
        int iM0 = this.f1958d.m0(viewC);
        int iH = this.f1958d.H(viewC);
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
        if (this.f1958d.r2() == 0) {
            top = (viewC.getLeft() - iA0) - this.f1957c.getPaddingLeft();
            if (this.f1956b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewC.getTop() - iM0) - this.f1957c.getPaddingTop();
        }
        int i = -top;
        aVar.f1962c = i;
        if (i >= 0) {
            aVar.f1961b = height == 0 ? 0.0f : i / height;
        } else {
            if (!new androidx.viewpager2.widget.a(this.f1958d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f1962c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i) {
        boolean z = true;
        if (!(this.f1959e == 1 && this.f == 1) && i == 1) {
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
                if (aVar.f1962c == 0) {
                    int i2 = this.h;
                    int i3 = aVar.f1960a;
                    if (i2 != i3) {
                        d(i3);
                    }
                } else {
                    z = false;
                }
            } else {
                int i4 = this.g.f1960a;
                if (i4 != -1) {
                    c(i4, 0.0f, 0);
                }
            }
            if (z) {
                e(0);
                n();
            }
        }
        if (this.f1959e == 2 && i == 0 && this.l) {
            q();
            a aVar2 = this.g;
            if (aVar2.f1962c == 0) {
                int i5 = this.i;
                int i6 = aVar2.f1960a;
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
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f1956b
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
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1962c
            if (r6 == 0) goto L2f
            int r5 = r5.f1960a
            int r5 = r5 + r4
            goto L33
        L2f:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.f1960a
        L33:
            r3.i = r5
            int r6 = r3.h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.f1959e
            if (r5 != 0) goto L4b
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.f1960a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1960a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f1961b
            int r5 = r5.f1962c
            r3.c(r6, r0, r5)
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1960a
            int r0 = r3.i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f1962c
            if (r5 != 0) goto L71
            int r5 = r3.f
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
        a aVar = this.g;
        return ((double) aVar.f1960a) + ((double) aVar.f1961b);
    }

    int h() {
        return this.f;
    }

    boolean i() {
        return this.m;
    }

    boolean j() {
        return this.f == 0;
    }

    void l() {
        this.l = true;
    }

    void m(int i, boolean z) {
        this.f1959e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.i != i;
        this.i = i;
        e(2);
        if (z2) {
            d(i);
        }
    }

    void o(ViewPager2.i iVar) {
        this.f1955a = iVar;
    }
}
