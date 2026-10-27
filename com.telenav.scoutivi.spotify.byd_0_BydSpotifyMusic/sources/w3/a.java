package w3;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.r;
import h3.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private j f9711c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BaseRecyclerView f9712d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f9709a = "ScrollHelper";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f9710b = 15;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f9713e = 500;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f9714f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<Integer> f9715g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f9716h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f9717i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final ViewTreeObserver.OnGlobalLayoutListener f9718j = new ViewTreeObserverOnGlobalLayoutListenerC0170a();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final RecyclerView.i f9719k = new b();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final RecyclerView.t f9720l = new c();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final RecyclerView.r f9721m = new d();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f9722n = new e();

    /* JADX INFO: renamed from: w3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ScrollHelper.java */
    class ViewTreeObserverOnGlobalLayoutListenerC0170a implements ViewTreeObserver.OnGlobalLayoutListener {
        ViewTreeObserverOnGlobalLayoutListenerC0170a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (a.this.f9712d.getChildCount() > 1) {
                a.this.f9712d.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                a.this.n();
            }
        }
    }

    /* JADX INFO: compiled from: ScrollHelper.java */
    class b extends RecyclerView.i {
        b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void a() {
            super.a();
            a.this.C();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void d(int i7, int i8) {
            super.d(i7, i8);
            a.this.C();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public void f(int i7, int i8) {
            super.f(i7, i8);
            a.this.C();
        }
    }

    /* JADX INFO: compiled from: ScrollHelper.java */
    class c extends RecyclerView.t {
        c() {
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x00ae  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00b0  */
        @Override // androidx.recyclerview.widget.RecyclerView.t
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(androidx.recyclerview.widget.RecyclerView r8, int r9) {
            /*
                Method dump skipped, instruction units count: 255
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: w3.a.c.a(androidx.recyclerview.widget.RecyclerView, int):void");
        }
    }

    /* JADX INFO: compiled from: ScrollHelper.java */
    class d extends RecyclerView.r {
        d() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.r
        public boolean a(int i7, int i8) {
            RecyclerView.o layoutManager;
            int iZ1;
            if (a.this.f9717i) {
                i7 = -i7;
            }
            if ((Math.abs(i8) < 500 && Math.abs(i7) < 500) || (layoutManager = a.this.f9712d.getLayoutManager()) == null || !(layoutManager instanceof LinearLayoutManager) || (iZ1 = ((LinearLayoutManager) layoutManager).Z1()) < 0) {
                return false;
            }
            a.this.D((i8 > 0 || i7 > 0) ? a.this.r(iZ1) : a.this.t(iZ1));
            a.this.f9714f = false;
            return true;
        }
    }

    /* JADX INFO: compiled from: ScrollHelper.java */
    class e implements View.OnAttachStateChangeListener {
        e() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (a.this.f9716h) {
                return;
            }
            i.b("ScrollHelper").b("Retry initialization", new Object[0]);
            a.this.C();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            a.this.f9712d.getViewTreeObserver().removeOnGlobalLayoutListener(a.this.f9718j);
        }
    }

    /* JADX INFO: compiled from: ScrollHelper.java */
    class f extends j {
        f(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.j
        protected int B() {
            return -1;
        }

        @Override // androidx.recyclerview.widget.j
        public int s(int i7, int i8, int i9, int i10, int i11) {
            return a.this.f9717i ? i10 - i8 : i9 - i7;
        }

        @Override // androidx.recyclerview.widget.j
        protected float v(DisplayMetrics displayMetrics) {
            return 50.0f / displayMetrics.densityDpi;
        }
    }

    public a(BaseRecyclerView baseRecyclerView) {
        m(baseRecyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean A(LinearLayoutManager linearLayoutManager, int i7) {
        int iIntValue;
        int iIntValue2;
        int i8 = 1;
        while (true) {
            if (i8 >= this.f9715g.size()) {
                iIntValue = 0;
                iIntValue2 = 0;
                break;
            }
            if (i7 < this.f9715g.get(i8).intValue()) {
                iIntValue2 = this.f9715g.get(i8 - 1).intValue();
                iIntValue = this.f9715g.get(i8).intValue();
                break;
            }
            i8++;
        }
        if (iIntValue - iIntValue2 == 1) {
            View viewC = linearLayoutManager.C(i7);
            return linearLayoutManager.p2() == 1 ? viewC.getY() + ((float) (viewC.getHeight() / 2)) <= 0.0f : this.f9717i ? ((float) this.f9712d.getWidth()) - viewC.getX() <= ((float) (viewC.getWidth() / 2)) : viewC.getX() + ((float) (viewC.getWidth() / 2)) <= 0.0f;
        }
        int iP = iIntValue - i7;
        int iP2 = (i7 - iIntValue2) + 1;
        if (linearLayoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) linearLayoutManager;
            iP2 = p(iIntValue2, i7, gridLayoutManager) + 1;
            iP = p(i7, iIntValue, gridLayoutManager);
        }
        if (iP < iP2) {
            return true;
        }
        if (iP > iP2) {
            return false;
        }
        View viewC2 = linearLayoutManager.C(i7);
        return linearLayoutManager.p2() == 1 ? viewC2.getY() + ((float) (viewC2.getHeight() / 2)) <= 0.0f : this.f9717i ? ((float) this.f9712d.getWidth()) - viewC2.getX() <= ((float) (viewC2.getWidth() / 2)) : viewC2.getX() + ((float) (viewC2.getWidth() / 2)) <= 0.0f;
    }

    private boolean B(int i7) {
        return this.f9712d.getAdapter().g(i7) == 12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(int i7) {
        this.f9711c.p(i7);
        this.f9712d.getLayoutManager().J1(this.f9711c);
    }

    private void m(BaseRecyclerView baseRecyclerView) {
        this.f9716h = false;
        this.f9712d = baseRecyclerView;
        C();
        if (this.f9711c == null) {
            this.f9711c = new f(baseRecyclerView.getContext());
        }
        y();
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        RecyclerView.o layoutManager = this.f9712d.getLayoutManager();
        int iO = 0;
        if (layoutManager == null || !(layoutManager instanceof LinearLayoutManager)) {
            i.b("ScrollHelper").d("layoutmanager error " + layoutManager, new Object[0]);
        } else {
            this.f9717i = com.vivid.spotify.common.base.i.a() && ((LinearLayoutManager) layoutManager).p2() == 0;
            View childAt = this.f9712d.getChildAt(0);
            if (((LinearLayoutManager) layoutManager).p2() == 0) {
                int width = (this.f9712d.getWidth() - this.f9712d.getPaddingStart()) - this.f9712d.getPaddingEnd();
                int iQ = q();
                int iX = (width + iQ) / (x(childAt) + iQ);
                while (iO < layoutManager.Y()) {
                    if (iO != 0) {
                        this.f9715g.add(Integer.valueOf(iO));
                    }
                    i.b("ScrollHelper").g("add  title index  : " + iO);
                    iO += iX;
                }
            } else {
                int dimensionPixelSize = this.f9712d.getResources().getDimensionPixelSize(r.media_title_height);
                if (this.f9712d.getAdapter() instanceof com.vivid.spotify.common.base.d) {
                    dimensionPixelSize = ((com.vivid.spotify.common.base.d) this.f9712d.getAdapter()).B();
                }
                int iS = s();
                int iU = u();
                int iQ2 = q();
                int iY = layoutManager.Y();
                int i7 = 0;
                while (iO < iY) {
                    int i8 = B(iO) ? dimensionPixelSize : iS;
                    int i9 = i7 + i8;
                    if (i9 > iU) {
                        this.f9715g.add(Integer.valueOf(iO));
                        i.b("ScrollHelper").g("add  title index  : " + iO);
                    } else {
                        i8 = i9;
                    }
                    i7 = i8 + iQ2;
                    iO = o(iO, layoutManager);
                    if (iO == -1) {
                        break;
                    }
                }
            }
        }
        this.f9716h = true;
    }

    private int o(int i7, RecyclerView.o oVar) {
        if (!(oVar instanceof GridLayoutManager)) {
            int i8 = i7 + 1;
            if (i8 >= oVar.Y()) {
                return -1;
            }
            return i8;
        }
        int iF = 0;
        int iX2 = ((GridLayoutManager) oVar).X2();
        int iY = oVar.Y();
        while (i7 < iY) {
            iF += ((GridLayoutManager) oVar).b3().f(i7);
            if (iF > iX2) {
                return i7;
            }
            if (iF == iX2) {
                return i7 + 1;
            }
            i7++;
        }
        return -1;
    }

    private int p(int i7, int i8, GridLayoutManager gridLayoutManager) {
        int i9 = 0;
        if (i7 == i8) {
            return 0;
        }
        int iO = i7 > i8 ? i8 : i7;
        if (i7 < i8) {
            i7 = i8;
        }
        while (iO < i7) {
            i9++;
            iO = o(iO, gridLayoutManager);
            if (iO < 0) {
                break;
            }
        }
        return i9;
    }

    private int q() {
        int itemDecorationCount = this.f9712d.getItemDecorationCount();
        if (itemDecorationCount <= 0) {
            return 0;
        }
        int iJ = 0;
        for (int i7 = 0; i7 < itemDecorationCount; i7++) {
            RecyclerView.n nVarL0 = this.f9712d.l0(i7);
            if (nVarL0 instanceof com.vivid.spotify.common.ui.c) {
                iJ += ((com.vivid.spotify.common.ui.c) nVarL0).j();
            }
        }
        return iJ;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int r(int i7) {
        for (int i8 = 0; i8 < this.f9715g.size(); i8++) {
            if (i7 < this.f9715g.get(i8).intValue()) {
                return this.f9715g.get(i8).intValue();
            }
        }
        return this.f9715g.get(r3.size() - 1).intValue();
    }

    private int s() {
        return !B(0) ? v(this.f9712d.getChildAt(0)) : v(this.f9712d.getChildAt(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int t(int r7) {
        /*
            r6 = this;
            com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView r0 = r6.f9712d
            androidx.recyclerview.widget.RecyclerView$o r0 = r0.getLayoutManager()
            android.view.View r1 = r0.C(r7)
            boolean r2 = r0 instanceof androidx.recyclerview.widget.LinearLayoutManager
            r3 = 0
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L48
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0
            int r0 = r0.p2()
            if (r0 != r5) goto L25
            float r0 = r1.getY()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L23
        L21:
            r0 = r5
            goto L51
        L23:
            r0 = r4
            goto L51
        L25:
            boolean r0 = r6.f9717i
            if (r0 == 0) goto L3f
            com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView r0 = r6.f9712d
            int r0 = r0.getWidth()
            float r0 = (float) r0
            float r2 = r1.getX()
            float r0 = r0 - r2
            int r1 = r1.getWidth()
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 < 0) goto L23
            goto L21
        L3f:
            float r0 = r1.getX()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L23
            goto L21
        L48:
            float r0 = r1.getY()
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L23
            goto L21
        L51:
            java.util.List<java.lang.Integer> r1 = r6.f9715g
            int r1 = r1.size()
            if (r5 > r1) goto L98
            if (r0 == 0) goto L6e
            java.util.List<java.lang.Integer> r1 = r6.f9715g
            int r2 = r1.size()
            int r2 = r2 - r5
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r7 > r1) goto L83
        L6e:
            if (r0 != 0) goto L95
            java.util.List<java.lang.Integer> r1 = r6.f9715g
            int r2 = r1.size()
            int r2 = r2 - r5
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            if (r7 < r1) goto L95
        L83:
            java.util.List<java.lang.Integer> r7 = r6.f9715g
            int r0 = r7.size()
            int r0 = r0 - r5
            java.lang.Object r7 = r7.get(r0)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            return r7
        L95:
            int r5 = r5 + 1
            goto L51
        L98:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: w3.a.t(int):int");
    }

    private int u() {
        return (((this.f9712d.getHeight() - this.f9712d.getPaddingTop()) - this.f9712d.getPaddingBottom()) - this.f9712d.getOccludedHeight()) + w(this.f9712d.getChildAt(1)) + this.f9712d.getItemBlankAreaSize();
    }

    private int v(View view) {
        int height = view.getHeight();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return height;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return height + marginLayoutParams.bottomMargin + marginLayoutParams.topMargin;
    }

    private int w(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return 0 + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return 0;
    }

    private int x(View view) {
        int width = view.getWidth();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return width;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return width + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
    }

    private void y() {
        this.f9712d.getAdapter().y(this.f9719k);
    }

    private void z() {
        this.f9712d.setOnFlingListener(this.f9721m);
        this.f9712d.l(this.f9720l);
        this.f9712d.addOnAttachStateChangeListener(this.f9722n);
        if (this.f9716h || !this.f9712d.isAttachedToWindow()) {
            return;
        }
        C();
    }

    public void C() {
        this.f9715g.clear();
        this.f9715g.add(0);
        this.f9714f = false;
        if (this.f9712d.getAdapter() == null) {
            i.b("ScrollHelper").g("adapter is null");
            return;
        }
        if (this.f9712d.getAdapter().e() <= 1) {
            i.b("ScrollHelper").g("too little data to calculate");
            return;
        }
        if (this.f9712d.getChildCount() > 1) {
            n();
        } else if (this.f9712d.isAttachedToWindow()) {
            this.f9712d.getViewTreeObserver().removeOnGlobalLayoutListener(this.f9718j);
            this.f9712d.getViewTreeObserver().addOnGlobalLayoutListener(this.f9718j);
        }
    }
}
