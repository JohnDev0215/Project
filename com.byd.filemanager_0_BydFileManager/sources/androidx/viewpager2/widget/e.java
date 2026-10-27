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
    private ViewPager2.i f1573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ViewPager2 f1574b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f1575c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayoutManager f1576d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1577e;
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
        int f1578a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1579b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1580c;

        a() {
        }

        void a() {
            this.f1578a = -1;
            this.f1579b = 0.0f;
            this.f1580c = 0;
        }
    }

    e(ViewPager2 viewPager2) {
        this.f1574b = viewPager2;
        RecyclerView recyclerView = viewPager2.l;
        this.f1575c = recyclerView;
        this.f1576d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.g = new a();
        n();
    }

    private void c(int i, float f, int i2) {
        ViewPager2.i iVar = this.f1573a;
        if (iVar != null) {
            iVar.b(i, f, i2);
        }
    }

    private void d(int i) {
        ViewPager2.i iVar = this.f1573a;
        if (iVar != null) {
            iVar.c(i);
        }
    }

    private void e(int i) {
        if ((this.f1577e == 3 && this.f == 0) || this.f == i) {
            return;
        }
        this.f = i;
        ViewPager2.i iVar = this.f1573a;
        if (iVar != null) {
            iVar.a(i);
        }
    }

    private int f() {
        return this.f1576d.k2();
    }

    private boolean k() {
        int i = this.f1577e;
        return i == 1 || i == 4;
    }

    private void n() {
        this.f1577e = 0;
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
        this.f1577e = z ? 4 : 1;
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
        int iK2 = this.f1576d.k2();
        aVar.f1578a = iK2;
        if (iK2 == -1) {
            aVar.a();
            return;
        }
        View viewM = this.f1576d.M(iK2);
        if (viewM == null) {
            aVar.a();
            return;
        }
        int iK0 = this.f1576d.k0(viewM);
        int iP0 = this.f1576d.p0(viewM);
        int iS0 = this.f1576d.s0(viewM);
        int iR = this.f1576d.R(viewM);
        ViewGroup.LayoutParams layoutParams = viewM.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            iK0 += marginLayoutParams.leftMargin;
            iP0 += marginLayoutParams.rightMargin;
            iS0 += marginLayoutParams.topMargin;
            iR += marginLayoutParams.bottomMargin;
        }
        int height = viewM.getHeight() + iS0 + iR;
        int width = viewM.getWidth() + iK0 + iP0;
        if (this.f1576d.B2() == 0) {
            top = (viewM.getLeft() - iK0) - this.f1575c.getPaddingLeft();
            if (this.f1574b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewM.getTop() - iS0) - this.f1575c.getPaddingTop();
        }
        int i = -top;
        aVar.f1580c = i;
        if (i >= 0) {
            aVar.f1579b = height == 0 ? 0.0f : i / height;
        } else {
            if (!new androidx.viewpager2.widget.a(this.f1576d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f1580c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i) {
        boolean z = true;
        if (!(this.f1577e == 1 && this.f == 1) && i == 1) {
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
                if (aVar.f1580c == 0) {
                    int i2 = this.h;
                    int i3 = aVar.f1578a;
                    if (i2 != i3) {
                        d(i3);
                    }
                } else {
                    z = false;
                }
            } else {
                int i4 = this.g.f1578a;
                if (i4 != -1) {
                    c(i4, 0.0f, 0);
                }
            }
            if (z) {
                e(0);
                n();
            }
        }
        if (this.f1577e == 2 && i == 0 && this.l) {
            q();
            a aVar2 = this.g;
            if (aVar2.f1580c == 0) {
                int i5 = this.i;
                int i6 = aVar2.f1578a;
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
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f1574b
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
            int r6 = r5.f1580c
            if (r6 == 0) goto L2f
            int r5 = r5.f1578a
            int r5 = r5 + r4
            goto L33
        L2f:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.f1578a
        L33:
            r3.i = r5
            int r6 = r3.h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.f1577e
            if (r5 != 0) goto L4b
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r5 = r5.f1578a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1578a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f1579b
            int r5 = r5.f1580c
            r3.c(r6, r0, r5)
            androidx.viewpager2.widget.e$a r5 = r3.g
            int r6 = r5.f1578a
            int r0 = r3.i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f1580c
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
        return ((double) aVar.f1578a) + ((double) aVar.f1579b);
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
        this.f1577e = z ? 2 : 3;
        this.m = false;
        boolean z2 = this.i != i;
        this.i = i;
        e(2);
        if (z2) {
            d(i);
        }
    }

    void o(ViewPager2.i iVar) {
        this.f1573a = iVar;
    }
}
