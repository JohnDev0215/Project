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
import b.b.j;
import b.b.p.j.p;
import b.b.q.i;
import b.b.q.r;
import b.h.m.t;
import b.h.n.h;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f473c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f474d;
    public final g A;
    public final f B;
    public final e C;
    public final c D;
    public Runnable E;
    public final Handler F;
    public final Rect G;
    public Rect H;
    public boolean I;
    public PopupWindow J;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Context f475e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ListAdapter f476f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public r f477g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f478h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f479i;
    public int j;
    public int k;
    public int l;
    public boolean m;
    public boolean n;
    public boolean o;
    public int p;
    public boolean q;
    public boolean r;
    public int s;
    public View t;
    public int u;
    public DataSetObserver v;
    public View w;
    public Drawable x;
    public AdapterView.OnItemClickListener y;
    public AdapterView.OnItemSelectedListener z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewT = ListPopupWindow.this.t();
            if (viewT == null || viewT.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.g();
        }
    }

    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            r rVar;
            if (i2 == -1 || (rVar = ListPopupWindow.this.f477g) == null) {
                return;
            }
            rVar.setListSelectionHidden(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ListPopupWindow.this.r();
        }
    }

    public class d extends DataSetObserver {
        public d() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (ListPopupWindow.this.b()) {
                ListPopupWindow.this.g();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            ListPopupWindow.this.dismiss();
        }
    }

    public class e implements AbsListView.OnScrollListener {
        public e() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            if (i2 != 1 || ListPopupWindow.this.A() || ListPopupWindow.this.J.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.F.removeCallbacks(listPopupWindow.A);
            ListPopupWindow.this.A.run();
        }
    }

    public class f implements View.OnTouchListener {
        public f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.J) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.J.getWidth() && y >= 0 && y < ListPopupWindow.this.J.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.F.postDelayed(listPopupWindow.A, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.F.removeCallbacks(listPopupWindow2.A);
            return false;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = ListPopupWindow.this.f477g;
            if (rVar == null || !t.O(rVar) || ListPopupWindow.this.f477g.getCount() <= ListPopupWindow.this.f477g.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.f477g.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.s) {
                listPopupWindow.J.setInputMethodMode(2);
                ListPopupWindow.this.g();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f472b = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f474d = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                f473c = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, b.b.a.listPopupWindowStyle);
    }

    public boolean A() {
        return this.J.getInputMethodMode() == 2;
    }

    public boolean B() {
        return this.I;
    }

    public final void C() {
        View view = this.t;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.t);
            }
        }
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
        this.f479i = rect.left + rect.right + i2;
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

    public final void N(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.J.setIsClippedToScreen(z);
            return;
        }
        Method method = f472b;
        if (method != null) {
            try {
                method.invoke(this.J, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void O(int i2) {
        this.u = i2;
    }

    public void P(int i2) {
        r rVar = this.f477g;
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
        this.f479i = i2;
    }

    @Override // b.b.p.j.p
    public boolean b() {
        return this.J.isShowing();
    }

    public void c(Drawable drawable) {
        this.J.setBackgroundDrawable(drawable);
    }

    public void d(int i2) {
        this.j = i2;
    }

    @Override // b.b.p.j.p
    public void dismiss() {
        this.J.dismiss();
        C();
        this.J.setContentView(null);
        this.f477g = null;
        this.F.removeCallbacks(this.A);
    }

    public int e() {
        return this.j;
    }

    @Override // b.b.p.j.p
    public void g() {
        int iQ = q();
        boolean zA = A();
        h.b(this.J, this.l);
        if (this.J.isShowing()) {
            if (t.O(t())) {
                int width = this.f479i;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i2 = this.f478h;
                if (i2 == -1) {
                    if (!zA) {
                        iQ = -1;
                    }
                    if (zA) {
                        this.J.setWidth(this.f479i == -1 ? -1 : 0);
                        this.J.setHeight(0);
                    } else {
                        this.J.setWidth(this.f479i == -1 ? -1 : 0);
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
        int width2 = this.f479i;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i3 = this.f478h;
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
            h.a(this.J, this.n);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = f474d;
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
        h.c(this.J, t(), this.j, this.k, this.p);
        this.f477g.setSelection(-1);
        if (!this.I || this.f477g.isInTouchMode()) {
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

    @Override // b.b.p.j.p
    public ListView l() {
        return this.f477g;
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
            ListAdapter listAdapter2 = this.f476f;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f476f = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.v);
        }
        r rVar = this.f477g;
        if (rVar != null) {
            rVar.setAdapter(this.f476f);
        }
    }

    public final int q() {
        int measuredHeight;
        int i2;
        int iMakeMeasureSpec;
        View view;
        int i3;
        if (this.f477g == null) {
            Context context = this.f475e;
            this.E = new a();
            r rVarS = s(context, !this.I);
            this.f477g = rVarS;
            Drawable drawable = this.x;
            if (drawable != null) {
                rVarS.setSelector(drawable);
            }
            this.f477g.setAdapter(this.f476f);
            this.f477g.setOnItemClickListener(this.y);
            this.f477g.setFocusable(true);
            this.f477g.setFocusableInTouchMode(true);
            this.f477g.setOnItemSelectedListener(new b());
            this.f477g.setOnScrollListener(this.C);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.z;
            if (onItemSelectedListener != null) {
                this.f477g.setOnItemSelectedListener(onItemSelectedListener);
            }
            r rVar = this.f477g;
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
                int i5 = this.f479i;
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
        if (this.q || this.f478h == -1) {
            return iU + i2;
        }
        int i7 = this.f479i;
        if (i7 == -2) {
            int i8 = this.f475e.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.G;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i7 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else {
            int i9 = this.f475e.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.G;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect3.left + rect3.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        int iD = this.f477g.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i2 + this.f477g.getPaddingTop() + this.f477g.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    public void r() {
        r rVar = this.f477g;
        if (rVar != null) {
            rVar.setListSelectionHidden(true);
            rVar.requestLayout();
        }
    }

    public r s(Context context, boolean z) {
        return new r(context, z);
    }

    public View t() {
        return this.w;
    }

    public final int u(View view, int i2, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.J.getMaxAvailableHeight(view, i2, z);
        }
        Method method = f473c;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.J, view, Integer.valueOf(i2), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.J.getMaxAvailableHeight(view, i2);
    }

    public Object v() {
        if (b()) {
            return this.f477g.getSelectedItem();
        }
        return null;
    }

    public long w() {
        if (b()) {
            return this.f477g.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int x() {
        if (b()) {
            return this.f477g.getSelectedItemPosition();
        }
        return -1;
    }

    public View y() {
        if (b()) {
            return this.f477g.getSelectedView();
        }
        return null;
    }

    public int z() {
        return this.f479i;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.f478h = -2;
        this.f479i = -2;
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
        this.f475e = context;
        this.F = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.ListPopupWindow, i2, i3);
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(j.ListPopupWindow_android_dropDownVerticalOffset, 0);
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
