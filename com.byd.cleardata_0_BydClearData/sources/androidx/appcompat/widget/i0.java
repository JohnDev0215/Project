package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class i0 implements androidx.appcompat.view.menu.p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f959d;
    final g A;
    private final f B;
    private final e C;
    private final c D;
    private Runnable E;
    final Handler F;
    private final Rect G;
    private Rect H;
    private boolean I;
    PopupWindow J;
    private Context e;
    private ListAdapter f;
    e0 g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private boolean q;
    private boolean r;
    int s;
    private View t;
    private int u;
    private DataSetObserver v;
    private View w;
    private Drawable x;
    private AdapterView.OnItemClickListener y;
    private AdapterView.OnItemSelectedListener z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewT = i0.this.t();
            if (viewT == null || viewT.getWindowToken() == null) {
                return;
            }
            i0.this.g();
        }
    }

    class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            e0 e0Var;
            if (i == -1 || (e0Var = i0.this.g) == null) {
                return;
            }
            e0Var.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    private class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i0.this.r();
        }
    }

    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (i0.this.b()) {
                i0.this.g();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            i0.this.dismiss();
        }
    }

    private class e implements AbsListView.OnScrollListener {
        e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || i0.this.A() || i0.this.J.getContentView() == null) {
                return;
            }
            i0 i0Var = i0.this;
            i0Var.F.removeCallbacks(i0Var.A);
            i0.this.A.run();
        }
    }

    private class f implements View.OnTouchListener {
        f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = i0.this.J) != null && popupWindow.isShowing() && x >= 0 && x < i0.this.J.getWidth() && y >= 0 && y < i0.this.J.getHeight()) {
                i0 i0Var = i0.this;
                i0Var.F.postDelayed(i0Var.A, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            i0 i0Var2 = i0.this;
            i0Var2.F.removeCallbacks(i0Var2.A);
            return false;
        }
    }

    private class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e0 e0Var = i0.this.g;
            if (e0Var == null || !a.g.k.t.M(e0Var) || i0.this.g.getCount() <= i0.this.g.getChildCount()) {
                return;
            }
            int childCount = i0.this.g.getChildCount();
            i0 i0Var = i0.this;
            if (childCount <= i0Var.s) {
                i0Var.J.setInputMethodMode(2);
                i0.this.g();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f957b = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f959d = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                f958c = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public i0(Context context) {
        this(context, null, a.a.a.D);
    }

    public i0(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public i0(Context context, AttributeSet attributeSet, int i, int i2) {
        this.h = -2;
        this.i = -2;
        this.l = 1002;
        this.p = 0;
        this.q = false;
        this.r = false;
        this.s = Integer.MAX_VALUE;
        this.u = 0;
        this.A = new g();
        this.B = new f();
        this.C = new e();
        this.D = new c();
        this.G = new Rect();
        this.e = context;
        this.F = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.a.j.o1, i, i2);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(a.a.j.p1, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(a.a.j.q1, 0);
        this.k = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.m = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        p pVar = new p(context, attributeSet, i, i2);
        this.J = pVar;
        pVar.setInputMethodMode(1);
    }

    private void C() {
        View view = this.t;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.t);
            }
        }
    }

    private void N(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.J.setIsClippedToScreen(z);
            return;
        }
        Method method = f957b;
        if (method != null) {
            try {
                method.invoke(this.J, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x012a A[PHI: r1
  0x012a: PHI (r1v1 int) = (r1v0 int), (r1v6 int) binds: [B:45:0x011f, B:47:0x0123] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int q() {
        /*
            Method dump skipped, instruction units count: 355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.i0.q():int");
    }

    private int u(View view, int i, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.J.getMaxAvailableHeight(view, i, z);
        }
        Method method = f958c;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.J, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.J.getMaxAvailableHeight(view, i);
    }

    public boolean A() {
        return this.J.getInputMethodMode() == 2;
    }

    public boolean B() {
        return this.I;
    }

    public void D(View view) {
        this.w = view;
    }

    public void E(int i) {
        this.J.setAnimationStyle(i);
    }

    public void F(int i) {
        Drawable background = this.J.getBackground();
        if (background == null) {
            Q(i);
            return;
        }
        background.getPadding(this.G);
        Rect rect = this.G;
        this.i = rect.left + rect.right + i;
    }

    public void G(int i) {
        this.p = i;
    }

    public void H(Rect rect) {
        this.H = rect != null ? new Rect(rect) : null;
    }

    public void I(int i) {
        this.J.setInputMethodMode(i);
    }

    public void J(boolean z) {
        this.I = z;
        this.J.setFocusable(z);
    }

    public void K(PopupWindow.OnDismissListener onDismissListener) {
        this.J.setOnDismissListener(onDismissListener);
    }

    public void L(AdapterView.OnItemClickListener onItemClickListener) {
        this.y = onItemClickListener;
    }

    public void M(boolean z) {
        this.o = true;
        this.n = z;
    }

    public void O(int i) {
        this.u = i;
    }

    public void P(int i) {
        e0 e0Var = this.g;
        if (!b() || e0Var == null) {
            return;
        }
        e0Var.setListSelectionHidden(false);
        e0Var.setSelection(i);
        if (e0Var.getChoiceMode() != 0) {
            e0Var.setItemChecked(i, true);
        }
    }

    public void Q(int i) {
        this.i = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean b() {
        return this.J.isShowing();
    }

    public void c(Drawable drawable) {
        this.J.setBackgroundDrawable(drawable);
    }

    public void d(int i) {
        this.j = i;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        this.J.dismiss();
        C();
        this.J.setContentView(null);
        this.g = null;
        this.F.removeCallbacks(this.A);
    }

    public int e() {
        return this.j;
    }

    @Override // androidx.appcompat.view.menu.p
    public void g() {
        int iQ = q();
        boolean zA = A();
        androidx.core.widget.h.b(this.J, this.l);
        if (this.J.isShowing()) {
            if (a.g.k.t.M(t())) {
                int width = this.i;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i = this.h;
                if (i == -1) {
                    if (!zA) {
                        iQ = -1;
                    }
                    if (zA) {
                        this.J.setWidth(this.i == -1 ? -1 : 0);
                        this.J.setHeight(0);
                    } else {
                        this.J.setWidth(this.i == -1 ? -1 : 0);
                        this.J.setHeight(-1);
                    }
                } else if (i != -2) {
                    iQ = i;
                }
                this.J.setOutsideTouchable((this.r || this.q) ? false : true);
                this.J.update(t(), this.j, this.k, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.i;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i2 = this.h;
        if (i2 == -1) {
            iQ = -1;
        } else if (i2 != -2) {
            iQ = i2;
        }
        this.J.setWidth(width2);
        this.J.setHeight(iQ);
        N(true);
        this.J.setOutsideTouchable((this.r || this.q) ? false : true);
        this.J.setTouchInterceptor(this.B);
        if (this.o) {
            androidx.core.widget.h.a(this.J, this.n);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = f959d;
            if (method != null) {
                try {
                    method.invoke(this.J, this.H);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.J.setEpicenterBounds(this.H);
        }
        androidx.core.widget.h.c(this.J, t(), this.j, this.k, this.p);
        this.g.setSelection(-1);
        if (!this.I || this.g.isInTouchMode()) {
            r();
        }
        if (this.I) {
            return;
        }
        this.F.post(this.D);
    }

    public int h() {
        if (this.m) {
            return this.k;
        }
        return 0;
    }

    public Drawable j() {
        return this.J.getBackground();
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView l() {
        return this.g;
    }

    public void n(int i) {
        this.k = i;
        this.m = true;
    }

    public void o(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.v;
        if (dataSetObserver == null) {
            this.v = new d();
        } else {
            ListAdapter listAdapter2 = this.f;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.v);
        }
        e0 e0Var = this.g;
        if (e0Var != null) {
            e0Var.setAdapter(this.f);
        }
    }

    public void r() {
        e0 e0Var = this.g;
        if (e0Var != null) {
            e0Var.setListSelectionHidden(true);
            e0Var.requestLayout();
        }
    }

    e0 s(Context context, boolean z) {
        return new e0(context, z);
    }

    public View t() {
        return this.w;
    }

    public Object v() {
        if (b()) {
            return this.g.getSelectedItem();
        }
        return null;
    }

    public long w() {
        if (b()) {
            return this.g.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int x() {
        if (b()) {
            return this.g.getSelectedItemPosition();
        }
        return -1;
    }

    public View y() {
        if (b()) {
            return this.g.getSelectedView();
        }
        return null;
    }

    public int z() {
        return this.i;
    }
}
