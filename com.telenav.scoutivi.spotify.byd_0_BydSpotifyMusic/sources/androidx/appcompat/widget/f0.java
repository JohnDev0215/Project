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
public class f0 implements j.e {
    private static Method L;
    private static Method M;
    private AdapterView.OnItemSelectedListener A;
    final g B;
    private final f C;
    private final e D;
    private final c E;
    private Runnable F;
    final Handler G;
    private final Rect H;
    private Rect I;
    private boolean J;
    PopupWindow K;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f1040e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ListAdapter f1041g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    c0 f1042h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f1043i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f1044j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1045k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f1046l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f1047m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private boolean f1048n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1049o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f1050p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f1051q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1052r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private boolean f1053s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    int f1054t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private View f1055u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f1056v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private DataSetObserver f1057w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private View f1058x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private Drawable f1059y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private AdapterView.OnItemClickListener f1060z;

    /* JADX INFO: compiled from: ListPopupWindow.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewT = f0.this.t();
            if (viewT == null || viewT.getWindowToken() == null) {
                return;
            }
            f0.this.g();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    class b implements AdapterView.OnItemSelectedListener {
        b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i7, long j7) {
            c0 c0Var;
            if (i7 == -1 || (c0Var = f0.this.f1042h) == null) {
                return;
            }
            c0Var.setListSelectionHidden(false);
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
            f0.this.r();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class d extends DataSetObserver {
        d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (f0.this.b()) {
                f0.this.g();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            f0.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class e implements AbsListView.OnScrollListener {
        e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i7, int i8, int i9) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i7) {
            if (i7 != 1 || f0.this.A() || f0.this.K.getContentView() == null) {
                return;
            }
            f0 f0Var = f0.this;
            f0Var.G.removeCallbacks(f0Var.B);
            f0.this.B.run();
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
            int x6 = (int) motionEvent.getX();
            int y6 = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = f0.this.K) != null && popupWindow.isShowing() && x6 >= 0 && x6 < f0.this.K.getWidth() && y6 >= 0 && y6 < f0.this.K.getHeight()) {
                f0 f0Var = f0.this;
                f0Var.G.postDelayed(f0Var.B, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            f0 f0Var2 = f0.this;
            f0Var2.G.removeCallbacks(f0Var2.B);
            return false;
        }
    }

    /* JADX INFO: compiled from: ListPopupWindow.java */
    private class g implements Runnable {
        g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c0 c0Var = f0.this.f1042h;
            if (c0Var == null || !i0.u.O(c0Var) || f0.this.f1042h.getCount() <= f0.this.f1042h.getChildCount()) {
                return;
            }
            int childCount = f0.this.f1042h.getChildCount();
            f0 f0Var = f0.this;
            if (childCount <= f0Var.f1054t) {
                f0Var.K.setInputMethodMode(2);
                f0.this.g();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                L = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                M = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
    }

    public f0(Context context) {
        this(context, null, e.a.listPopupWindowStyle);
    }

    private void C() {
        View view = this.f1055u;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f1055u);
            }
        }
    }

    private void N(boolean z6) {
        if (Build.VERSION.SDK_INT > 28) {
            this.K.setIsClippedToScreen(z6);
            return;
        }
        Method method = L;
        if (method != null) {
            try {
                method.invoke(this.K, Boolean.valueOf(z6));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int q() {
        int measuredHeight;
        int i7;
        int iMakeMeasureSpec;
        View view;
        int i8;
        if (this.f1042h == null) {
            Context context = this.f1040e;
            this.F = new a();
            c0 c0VarS = s(context, !this.J);
            this.f1042h = c0VarS;
            Drawable drawable = this.f1059y;
            if (drawable != null) {
                c0VarS.setSelector(drawable);
            }
            this.f1042h.setAdapter(this.f1041g);
            this.f1042h.setOnItemClickListener(this.f1060z);
            this.f1042h.setFocusable(true);
            this.f1042h.setFocusableInTouchMode(true);
            this.f1042h.setOnItemSelectedListener(new b());
            this.f1042h.setOnScrollListener(this.D);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.A;
            if (onItemSelectedListener != null) {
                this.f1042h.setOnItemSelectedListener(onItemSelectedListener);
            }
            c0 c0Var = this.f1042h;
            View view2 = this.f1055u;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i9 = this.f1056v;
                if (i9 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(c0Var, layoutParams);
                } else if (i9 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.f1056v);
                } else {
                    linearLayout.addView(c0Var, layoutParams);
                    linearLayout.addView(view2);
                }
                int i10 = this.f1044j;
                if (i10 >= 0) {
                    i8 = Integer.MIN_VALUE;
                } else {
                    i10 = 0;
                    i8 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i10, i8), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = c0Var;
            }
            this.K.setContentView(view);
        } else {
            View view3 = this.f1055u;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.K.getBackground();
        if (background != null) {
            background.getPadding(this.H);
            Rect rect = this.H;
            int i11 = rect.top;
            i7 = rect.bottom + i11;
            if (!this.f1048n) {
                this.f1046l = -i11;
            }
        } else {
            this.H.setEmpty();
            i7 = 0;
        }
        int iU = u(t(), this.f1046l, this.K.getInputMethodMode() == 2);
        if (this.f1052r || this.f1043i == -1) {
            return iU + i7;
        }
        int i12 = this.f1044j;
        if (i12 == -2) {
            int i13 = this.f1040e.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.H;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i13 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i12 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i12, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else {
            int i14 = this.f1040e.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.H;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i14 - (rect3.left + rect3.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        int iD = this.f1042h.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i7 + this.f1042h.getPaddingTop() + this.f1042h.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    private int u(View view, int i7, boolean z6) {
        return this.K.getMaxAvailableHeight(view, i7, z6);
    }

    public boolean A() {
        return this.K.getInputMethodMode() == 2;
    }

    public boolean B() {
        return this.J;
    }

    public void D(View view) {
        this.f1058x = view;
    }

    public void E(int i7) {
        this.K.setAnimationStyle(i7);
    }

    public void F(int i7) {
        Drawable background = this.K.getBackground();
        if (background == null) {
            Q(i7);
            return;
        }
        background.getPadding(this.H);
        Rect rect = this.H;
        this.f1044j = rect.left + rect.right + i7;
    }

    public void G(int i7) {
        this.f1051q = i7;
    }

    public void H(Rect rect) {
        this.I = rect != null ? new Rect(rect) : null;
    }

    public void I(int i7) {
        this.K.setInputMethodMode(i7);
    }

    public void J(boolean z6) {
        this.J = z6;
        this.K.setFocusable(z6);
    }

    public void K(PopupWindow.OnDismissListener onDismissListener) {
        this.K.setOnDismissListener(onDismissListener);
    }

    public void L(AdapterView.OnItemClickListener onItemClickListener) {
        this.f1060z = onItemClickListener;
    }

    public void M(boolean z6) {
        this.f1050p = true;
        this.f1049o = z6;
    }

    public void O(int i7) {
        this.f1056v = i7;
    }

    public void P(int i7) {
        c0 c0Var = this.f1042h;
        if (!b() || c0Var == null) {
            return;
        }
        c0Var.setListSelectionHidden(false);
        c0Var.setSelection(i7);
        if (c0Var.getChoiceMode() != 0) {
            c0Var.setItemChecked(i7, true);
        }
    }

    public void Q(int i7) {
        this.f1044j = i7;
    }

    @Override // j.e
    public boolean b() {
        return this.K.isShowing();
    }

    public void c(Drawable drawable) {
        this.K.setBackgroundDrawable(drawable);
    }

    public void d(int i7) {
        this.f1045k = i7;
    }

    @Override // j.e
    public void dismiss() {
        this.K.dismiss();
        C();
        this.K.setContentView(null);
        this.f1042h = null;
        this.G.removeCallbacks(this.B);
    }

    public int e() {
        return this.f1045k;
    }

    @Override // j.e
    public void g() {
        int iQ = q();
        boolean zA = A();
        androidx.core.widget.h.b(this.K, this.f1047m);
        if (this.K.isShowing()) {
            if (i0.u.O(t())) {
                int width = this.f1044j;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i7 = this.f1043i;
                if (i7 == -1) {
                    if (!zA) {
                        iQ = -1;
                    }
                    if (zA) {
                        this.K.setWidth(this.f1044j == -1 ? -1 : 0);
                        this.K.setHeight(0);
                    } else {
                        this.K.setWidth(this.f1044j == -1 ? -1 : 0);
                        this.K.setHeight(-1);
                    }
                } else if (i7 != -2) {
                    iQ = i7;
                }
                this.K.setOutsideTouchable((this.f1053s || this.f1052r) ? false : true);
                this.K.update(t(), this.f1045k, this.f1046l, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.f1044j;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i8 = this.f1043i;
        if (i8 == -1) {
            iQ = -1;
        } else if (i8 != -2) {
            iQ = i8;
        }
        this.K.setWidth(width2);
        this.K.setHeight(iQ);
        N(true);
        this.K.setOutsideTouchable((this.f1053s || this.f1052r) ? false : true);
        this.K.setTouchInterceptor(this.C);
        if (this.f1050p) {
            androidx.core.widget.h.a(this.K, this.f1049o);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = M;
            if (method != null) {
                try {
                    method.invoke(this.K, this.I);
                } catch (Exception e7) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e7);
                }
            }
        } else {
            this.K.setEpicenterBounds(this.I);
        }
        androidx.core.widget.h.c(this.K, t(), this.f1045k, this.f1046l, this.f1051q);
        this.f1042h.setSelection(-1);
        if (!this.J || this.f1042h.isInTouchMode()) {
            r();
        }
        if (this.J) {
            return;
        }
        this.G.post(this.E);
    }

    public int h() {
        if (this.f1048n) {
            return this.f1046l;
        }
        return 0;
    }

    public Drawable j() {
        return this.K.getBackground();
    }

    @Override // j.e
    public ListView l() {
        return this.f1042h;
    }

    public void n(int i7) {
        this.f1046l = i7;
        this.f1048n = true;
    }

    public void o(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f1057w;
        if (dataSetObserver == null) {
            this.f1057w = new d();
        } else {
            ListAdapter listAdapter2 = this.f1041g;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f1041g = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f1057w);
        }
        c0 c0Var = this.f1042h;
        if (c0Var != null) {
            c0Var.setAdapter(this.f1041g);
        }
    }

    public void r() {
        c0 c0Var = this.f1042h;
        if (c0Var != null) {
            c0Var.setListSelectionHidden(true);
            c0Var.requestLayout();
        }
    }

    c0 s(Context context, boolean z6) {
        return new c0(context, z6);
    }

    public View t() {
        return this.f1058x;
    }

    public Object v() {
        if (b()) {
            return this.f1042h.getSelectedItem();
        }
        return null;
    }

    public long w() {
        if (b()) {
            return this.f1042h.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int x() {
        if (b()) {
            return this.f1042h.getSelectedItemPosition();
        }
        return -1;
    }

    public View y() {
        if (b()) {
            return this.f1042h.getSelectedView();
        }
        return null;
    }

    public int z() {
        return this.f1044j;
    }

    public f0(Context context, AttributeSet attributeSet, int i7) {
        this(context, attributeSet, i7, 0);
    }

    public f0(Context context, AttributeSet attributeSet, int i7, int i8) {
        this.f1043i = -2;
        this.f1044j = -2;
        this.f1047m = 1002;
        this.f1051q = 0;
        this.f1052r = false;
        this.f1053s = false;
        this.f1054t = Integer.MAX_VALUE;
        this.f1056v = 0;
        this.B = new g();
        this.C = new f();
        this.D = new e();
        this.E = new c();
        this.H = new Rect();
        this.f1040e = context;
        this.G = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.j.ListPopupWindow, i7, i8);
        this.f1045k = typedArrayObtainStyledAttributes.getDimensionPixelOffset(e.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(e.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.f1046l = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f1048n = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        o oVar = new o(context, attributeSet, i7, i8);
        this.K = oVar;
        oVar.setInputMethodMode(1);
    }
}
