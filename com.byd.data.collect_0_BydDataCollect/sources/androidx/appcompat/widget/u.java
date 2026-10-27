package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: ListPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class u implements androidx.appcompat.view.menu.p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Method f717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Method f718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Method f719d;
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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f720e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ListAdapter f721f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    r f722g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f723h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f724i;
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

    /* JADX INFO: compiled from: ListPopupWindow.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewT = u.this.t();
            if (viewT == null || viewT.getWindowToken() == null) {
                return;
            }
            u.this.g();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            r rVar;
            if (i2 == -1 || (rVar = u.this.f722g) == null) {
                return;
            }
            rVar.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            u.this.r();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (u.this.b()) {
                u.this.g();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            u.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class e implements AbsListView.OnScrollListener {
        e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || u.this.A() || u.this.J.getContentView() == null) {
                return;
            }
            u uVar = u.this;
            uVar.F.removeCallbacks(uVar.A);
            u.this.A.run();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class f implements View.OnTouchListener {
        f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = u.this.J) != null && popupWindow.isShowing() && x >= 0 && x < u.this.J.getWidth() && y >= 0 && y < u.this.J.getHeight()) {
                u uVar = u.this;
                uVar.F.postDelayed(uVar.A, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            u uVar2 = u.this;
            uVar2.F.removeCallbacks(uVar2.A);
            return false;
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = u.this.f722g;
            if (rVar == null || !b.g.k.t.O(rVar) || u.this.f722g.getCount() <= u.this.f722g.getChildCount()) {
                return;
            }
            int childCount = u.this.f722g.getChildCount();
            u uVar = u.this;
            if (childCount <= uVar.s) {
                uVar.J.setInputMethodMode(2);
                u.this.g();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f717b = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f719d = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                f718c = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public u(Context context) {
        this(context, null, b.a.a.listPopupWindowStyle);
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
        Method method = f717b;
        if (method != null) {
            try {
                method.invoke(this.J, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int q() {
        int measuredHeight;
        int i2;
        int iMakeMeasureSpec;
        View view;
        int i3;
        if (this.f722g == null) {
            Context context = this.f720e;
            this.E = new a();
            r rVarS = s(context, !this.I);
            this.f722g = rVarS;
            Drawable drawable = this.x;
            if (drawable != null) {
                rVarS.setSelector(drawable);
            }
            this.f722g.setAdapter(this.f721f);
            this.f722g.setOnItemClickListener(this.y);
            this.f722g.setFocusable(true);
            this.f722g.setFocusableInTouchMode(true);
            this.f722g.setOnItemSelectedListener(new b());
            this.f722g.setOnScrollListener(this.C);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.z;
            if (onItemSelectedListener != null) {
                this.f722g.setOnItemSelectedListener(onItemSelectedListener);
            }
            r rVar = this.f722g;
            View view2 = this.t;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i4 = this.u;
                if (i4 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(rVar, layoutParams);
                } else if (i4 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.u);
                } else {
                    linearLayout.addView(rVar, layoutParams);
                    linearLayout.addView(view2);
                }
                int i5 = this.f724i;
                if (i5 >= 0) {
                    i3 = Integer.MIN_VALUE;
                } else {
                    i5 = 0;
                    i3 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i5, i3), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = rVar;
            }
            this.J.setContentView(view);
        } else {
            View view3 = this.t;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.J.getBackground();
        if (background != null) {
            background.getPadding(this.G);
            Rect rect = this.G;
            int i6 = rect.top;
            i2 = rect.bottom + i6;
            if (!this.m) {
                this.k = -i6;
            }
        } else {
            this.G.setEmpty();
            i2 = 0;
        }
        int iU = u(t(), this.k, this.J.getInputMethodMode() == 2);
        if (this.q || this.f723h == -1) {
            return iU + i2;
        }
        int i7 = this.f724i;
        if (i7 == -2) {
            int i8 = this.f720e.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.G;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i7 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else {
            int i9 = this.f720e.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.G;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect3.left + rect3.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        int iD = this.f722g.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i2 + this.f722g.getPaddingTop() + this.f722g.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    private int u(View view, int i2, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.J.getMaxAvailableHeight(view, i2, z);
        }
        Method method = f718c;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.J, view, Integer.valueOf(i2), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.J.getMaxAvailableHeight(view, i2);
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

    public void E(int i2) {
        this.J.setAnimationStyle(i2);
    }

    public void F(int i2) {
        Drawable background = this.J.getBackground();
        if (background == null) {
            Q(i2);
            return;
        }
        background.getPadding(this.G);
        Rect rect = this.G;
        this.f724i = rect.left + rect.right + i2;
    }

    public void G(int i2) {
        this.p = i2;
    }

    public void H(Rect rect) {
        this.H = rect != null ? new Rect(rect) : null;
    }

    public void I(int i2) {
        this.J.setInputMethodMode(i2);
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

    public void O(int i2) {
        this.u = i2;
    }

    public void P(int i2) {
        r rVar = this.f722g;
        if (!b() || rVar == null) {
            return;
        }
        rVar.setListSelectionHidden(false);
        rVar.setSelection(i2);
        if (rVar.getChoiceMode() != 0) {
            rVar.setItemChecked(i2, true);
        }
    }

    public void Q(int i2) {
        this.f724i = i2;
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean b() {
        return this.J.isShowing();
    }

    public void c(Drawable drawable) {
        this.J.setBackgroundDrawable(drawable);
    }

    public void d(int i2) {
        this.j = i2;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        this.J.dismiss();
        C();
        this.J.setContentView(null);
        this.f722g = null;
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
            if (b.g.k.t.O(t())) {
                int width = this.f724i;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i2 = this.f723h;
                if (i2 == -1) {
                    if (!zA) {
                        iQ = -1;
                    }
                    if (zA) {
                        this.J.setWidth(this.f724i == -1 ? -1 : 0);
                        this.J.setHeight(0);
                    } else {
                        this.J.setWidth(this.f724i == -1 ? -1 : 0);
                        this.J.setHeight(-1);
                    }
                } else if (i2 != -2) {
                    iQ = i2;
                }
                this.J.setOutsideTouchable((this.r || this.q) ? false : true);
                this.J.update(t(), this.j, this.k, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.f724i;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i3 = this.f723h;
        if (i3 == -1) {
            iQ = -1;
        } else if (i3 != -2) {
            iQ = i3;
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
            Method method = f719d;
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
        this.f722g.setSelection(-1);
        if (!this.I || this.f722g.isInTouchMode()) {
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
        return this.f722g;
    }

    public void n(int i2) {
        this.k = i2;
        this.m = true;
    }

    public void o(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.v;
        if (dataSetObserver == null) {
            this.v = new d();
        } else {
            ListAdapter listAdapter2 = this.f721f;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f721f = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.v);
        }
        r rVar = this.f722g;
        if (rVar != null) {
            rVar.setAdapter(this.f721f);
        }
    }

    public void r() {
        r rVar = this.f722g;
        if (rVar != null) {
            rVar.setListSelectionHidden(true);
            rVar.requestLayout();
        }
    }

    r s(Context context, boolean z) {
        return new r(context, z);
    }

    public View t() {
        return this.w;
    }

    public Object v() {
        if (b()) {
            return this.f722g.getSelectedItem();
        }
        return null;
    }

    public long w() {
        if (b()) {
            return this.f722g.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int x() {
        if (b()) {
            return this.f722g.getSelectedItemPosition();
        }
        return -1;
    }

    public View y() {
        if (b()) {
            return this.f722g.getSelectedView();
        }
        return null;
    }

    public int z() {
        return this.f724i;
    }

    public u(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public u(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f723h = -2;
        this.f724i = -2;
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
        this.f720e = context;
        this.F = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.a.j.ListPopupWindow, i2, i3);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.a.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.a.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.k = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.m = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        i iVar = new i(context, attributeSet, i2, i3);
        this.J = iVar;
        iVar.setInputMethodMode(1);
    }
}
