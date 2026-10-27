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
    private ViewPager2.i f3346a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ViewPager2 f3347b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final RecyclerView f3348c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final LinearLayoutManager f3349d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3350e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3351f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f3352g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3353h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f3354i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f3355j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f3356k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f3357l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f3358m;

    /* JADX INFO: compiled from: ScrollEventAdapter.java */
    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f3360b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f3361c;

        a() {
        }

        void a() {
            this.f3359a = -1;
            this.f3360b = 0.0f;
            this.f3361c = 0;
        }
    }

    e(ViewPager2 viewPager2) {
        this.f3347b = viewPager2;
        RecyclerView recyclerView = viewPager2.f3307o;
        this.f3348c = recyclerView;
        this.f3349d = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.f3352g = new a();
        n();
    }

    private void c(int i7, float f7, int i8) {
        ViewPager2.i iVar = this.f3346a;
        if (iVar != null) {
            iVar.b(i7, f7, i8);
        }
    }

    private void d(int i7) {
        ViewPager2.i iVar = this.f3346a;
        if (iVar != null) {
            iVar.c(i7);
        }
    }

    private void e(int i7) {
        if ((this.f3350e == 3 && this.f3351f == 0) || this.f3351f == i7) {
            return;
        }
        this.f3351f = i7;
        ViewPager2.i iVar = this.f3346a;
        if (iVar != null) {
            iVar.a(i7);
        }
    }

    private int f() {
        return this.f3349d.Z1();
    }

    private boolean k() {
        int i7 = this.f3350e;
        return i7 == 1 || i7 == 4;
    }

    private void n() {
        this.f3350e = 0;
        this.f3351f = 0;
        this.f3352g.a();
        this.f3353h = -1;
        this.f3354i = -1;
        this.f3355j = false;
        this.f3356k = false;
        this.f3358m = false;
        this.f3357l = false;
    }

    private void p(boolean z6) {
        this.f3358m = z6;
        this.f3350e = z6 ? 4 : 1;
        int i7 = this.f3354i;
        if (i7 != -1) {
            this.f3353h = i7;
            this.f3354i = -1;
        } else if (this.f3353h == -1) {
            this.f3353h = f();
        }
        e(1);
    }

    private void q() {
        int top;
        a aVar = this.f3352g;
        int iZ1 = this.f3349d.Z1();
        aVar.f3359a = iZ1;
        if (iZ1 == -1) {
            aVar.a();
            return;
        }
        View viewC = this.f3349d.C(iZ1);
        if (viewC == null) {
            aVar.a();
            return;
        }
        int iA0 = this.f3349d.a0(viewC);
        int iJ0 = this.f3349d.j0(viewC);
        int iM0 = this.f3349d.m0(viewC);
        int iH = this.f3349d.H(viewC);
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
        if (this.f3349d.p2() == 0) {
            top = (viewC.getLeft() - iA0) - this.f3348c.getPaddingLeft();
            if (this.f3347b.d()) {
                top = -top;
            }
            height = width;
        } else {
            top = (viewC.getTop() - iM0) - this.f3348c.getPaddingTop();
        }
        int i7 = -top;
        aVar.f3361c = i7;
        if (i7 >= 0) {
            aVar.f3360b = height == 0 ? 0.0f : i7 / height;
        } else {
            if (!new androidx.viewpager2.widget.a(this.f3349d).d()) {
                throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", Integer.valueOf(aVar.f3361c)));
            }
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.t
    public void a(RecyclerView recyclerView, int i7) {
        boolean z6 = true;
        if (!(this.f3350e == 1 && this.f3351f == 1) && i7 == 1) {
            p(false);
            return;
        }
        if (k() && i7 == 2) {
            if (this.f3356k) {
                e(2);
                this.f3355j = true;
                return;
            }
            return;
        }
        if (k() && i7 == 0) {
            q();
            if (this.f3356k) {
                a aVar = this.f3352g;
                if (aVar.f3361c == 0) {
                    int i8 = this.f3353h;
                    int i9 = aVar.f3359a;
                    if (i8 != i9) {
                        d(i9);
                    }
                } else {
                    z6 = false;
                }
            } else {
                int i10 = this.f3352g.f3359a;
                if (i10 != -1) {
                    c(i10, 0.0f, 0);
                }
            }
            if (z6) {
                e(0);
                n();
            }
        }
        if (this.f3350e == 2 && i7 == 0 && this.f3357l) {
            q();
            a aVar2 = this.f3352g;
            if (aVar2.f3361c == 0) {
                int i11 = this.f3354i;
                int i12 = aVar2.f3359a;
                if (i11 != i12) {
                    if (i12 == -1) {
                        i12 = 0;
                    }
                    d(i12);
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
            r3.f3356k = r4
            r3.q()
            boolean r0 = r3.f3355j
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L3d
            r3.f3355j = r2
            if (r6 > 0) goto L22
            if (r6 != 0) goto L20
            if (r5 >= 0) goto L16
            r5 = r4
            goto L17
        L16:
            r5 = r2
        L17:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.f3347b
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
            androidx.viewpager2.widget.e$a r5 = r3.f3352g
            int r6 = r5.f3361c
            if (r6 == 0) goto L2f
            int r5 = r5.f3359a
            int r5 = r5 + r4
            goto L33
        L2f:
            androidx.viewpager2.widget.e$a r5 = r3.f3352g
            int r5 = r5.f3359a
        L33:
            r3.f3354i = r5
            int r6 = r3.f3353h
            if (r6 == r5) goto L4b
            r3.d(r5)
            goto L4b
        L3d:
            int r5 = r3.f3350e
            if (r5 != 0) goto L4b
            androidx.viewpager2.widget.e$a r5 = r3.f3352g
            int r5 = r5.f3359a
            if (r5 != r1) goto L48
            r5 = r2
        L48:
            r3.d(r5)
        L4b:
            androidx.viewpager2.widget.e$a r5 = r3.f3352g
            int r6 = r5.f3359a
            if (r6 != r1) goto L52
            r6 = r2
        L52:
            float r0 = r5.f3360b
            int r5 = r5.f3361c
            r3.c(r6, r0, r5)
            androidx.viewpager2.widget.e$a r5 = r3.f3352g
            int r6 = r5.f3359a
            int r0 = r3.f3354i
            if (r6 == r0) goto L63
            if (r0 != r1) goto L71
        L63:
            int r5 = r5.f3361c
            if (r5 != 0) goto L71
            int r5 = r3.f3351f
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
        a aVar = this.f3352g;
        return ((double) aVar.f3359a) + ((double) aVar.f3360b);
    }

    int h() {
        return this.f3351f;
    }

    boolean i() {
        return this.f3358m;
    }

    boolean j() {
        return this.f3351f == 0;
    }

    void l() {
        this.f3357l = true;
    }

    void m(int i7, boolean z6) {
        this.f3350e = z6 ? 2 : 3;
        this.f3358m = false;
        boolean z7 = this.f3354i != i7;
        this.f3354i = i7;
        e(2);
        if (z7) {
            d(i7);
        }
    }

    void o(ViewPager2.i iVar) {
        this.f3346a = iVar;
    }
}
