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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import java.lang.reflect.Method;
import l0.t;
import n.p;
import n0.h;
import o.i;
import o.r;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Method f528b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f529c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f530d;
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
    public Context f531e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ListAdapter f532f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public r f533g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f534h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f535i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f536j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f537k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f538l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f539m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f540n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f541o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f542p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f543q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f544r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f545s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f546t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f547u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public DataSetObserver f548v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public View f549w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Drawable f550x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public AdapterView.OnItemClickListener f551y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public AdapterView.OnItemSelectedListener f552z;

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
        public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j3) {
            r rVar;
            if (i3 == -1 || (rVar = ListPopupWindow.this.f533g) == null) {
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
        public void onScroll(AbsListView absListView, int i3, int i4, int i5) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i3) {
            if (i3 != 1 || ListPopupWindow.this.A() || ListPopupWindow.this.J.getContentView() == null) {
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
            int x3 = (int) motionEvent.getX();
            int y3 = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.J) != null && popupWindow.isShowing() && x3 >= 0 && x3 < ListPopupWindow.this.J.getWidth() && y3 >= 0 && y3 < ListPopupWindow.this.J.getHeight()) {
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
            r rVar = ListPopupWindow.this.f533g;
            if (rVar == null || !t.O(rVar) || ListPopupWindow.this.f533g.getCount() <= ListPopupWindow.this.f533g.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.f533g.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.f545s) {
                listPopupWindow.J.setInputMethodMode(2);
                ListPopupWindow.this.g();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f528b = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f530d = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                f529c = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    public boolean A() {
        return this.J.getInputMethodMode() == 2;
    }

    public boolean B() {
        return this.I;
    }

    public final void C() {
        View view = this.f546t;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f546t);
            }
        }
    }

    public void D(View view) {
        this.f549w = view;
    }

    public void E(int i3) {
        this.J.setAnimationStyle(i3);
    }

    public void F(int i3) {
        Drawable background = this.J.getBackground();
        if (background == null) {
            Q(i3);
            return;
        }
        background.getPadding(this.G);
        Rect rect = this.G;
        this.f535i = rect.left + rect.right + i3;
    }

    public void G(int i3) {
        this.f542p = i3;
    }

    public void H(Rect rect) {
        this.H = rect != null ? new Rect(rect) : null;
    }

    public void I(int i3) {
        this.J.setInputMethodMode(i3);
    }

    public void J(boolean z3) {
        this.I = z3;
        this.J.setFocusable(z3);
    }

    public void K(PopupWindow.OnDismissListener onDismissListener) {
        this.J.setOnDismissListener(onDismissListener);
    }

    public void L(AdapterView.OnItemClickListener onItemClickListener) {
        this.f551y = onItemClickListener;
    }

    public void M(boolean z3) {
        this.f541o = true;
        this.f540n = z3;
    }

    public final void N(boolean z3) {
        if (Build.VERSION.SDK_INT > 28) {
            this.J.setIsClippedToScreen(z3);
            return;
        }
        Method method = f528b;
        if (method != null) {
            try {
                method.invoke(this.J, Boolean.valueOf(z3));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void O(int i3) {
        this.f547u = i3;
    }

    public void P(int i3) {
        r rVar = this.f533g;
        if (!b() || rVar == null) {
            return;
        }
        rVar.setListSelectionHidden(false);
        rVar.setSelection(i3);
        if (rVar.getChoiceMode() != 0) {
            rVar.setItemChecked(i3, true);
        }
    }

    public void Q(int i3) {
        this.f535i = i3;
    }

    @Override // n.p
    public boolean b() {
        return this.J.isShowing();
    }

    public void c(Drawable drawable) {
        this.J.setBackgroundDrawable(drawable);
    }

    public void d(int i3) {
        this.f536j = i3;
    }

    @Override // n.p
    public void dismiss() {
        this.J.dismiss();
        C();
        this.J.setContentView(null);
        this.f533g = null;
        this.F.removeCallbacks(this.A);
    }

    public int e() {
        return this.f536j;
    }

    @Override // n.p
    public void g() {
        int iQ = q();
        boolean zA = A();
        h.b(this.J, this.f538l);
        if (this.J.isShowing()) {
            if (t.O(t())) {
                int width = this.f535i;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i3 = this.f534h;
                if (i3 == -1) {
                    if (!zA) {
                        iQ = -1;
                    }
                    if (zA) {
                        this.J.setWidth(this.f535i == -1 ? -1 : 0);
                        this.J.setHeight(0);
                    } else {
                        this.J.setWidth(this.f535i == -1 ? -1 : 0);
                        this.J.setHeight(-1);
                    }
                } else if (i3 != -2) {
                    iQ = i3;
                }
                this.J.setOutsideTouchable((this.f544r || this.f543q) ? false : true);
                this.J.update(t(), this.f536j, this.f537k, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.f535i;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i4 = this.f534h;
        if (i4 == -1) {
            iQ = -1;
        } else if (i4 != -2) {
            iQ = i4;
        }
        this.J.setWidth(width2);
        this.J.setHeight(iQ);
        N(true);
        this.J.setOutsideTouchable((this.f544r || this.f543q) ? false : true);
        this.J.setTouchInterceptor(this.B);
        if (this.f541o) {
            h.a(this.J, this.f540n);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = f530d;
            if (method != null) {
                try {
                    method.invoke(this.J, this.H);
                } catch (Exception e3) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e3);
                }
            }
        } else {
            this.J.setEpicenterBounds(this.H);
        }
        h.c(this.J, t(), this.f536j, this.f537k, this.f542p);
        this.f533g.setSelection(-1);
        if (!this.I || this.f533g.isInTouchMode()) {
            r();
        }
        if (this.I) {
            return;
        }
        this.F.post(this.D);
    }

    public int h() {
        if (this.f539m) {
            return this.f537k;
        }
        return 0;
    }

    public Drawable j() {
        return this.J.getBackground();
    }

    @Override // n.p
    public ListView l() {
        return this.f533g;
    }

    public void n(int i3) {
        this.f537k = i3;
        this.f539m = true;
    }

    public void o(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f548v;
        if (dataSetObserver == null) {
            this.f548v = new d();
        } else {
            ListAdapter listAdapter2 = this.f532f;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f532f = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f548v);
        }
        r rVar = this.f533g;
        if (rVar != null) {
            rVar.setAdapter(this.f532f);
        }
    }

    public final int q() {
        int measuredHeight;
        int i3;
        int iMakeMeasureSpec;
        View view;
        int i4;
        if (this.f533g == null) {
            Context context = this.f531e;
            this.E = new a();
            r rVarS = s(context, !this.I);
            this.f533g = rVarS;
            Drawable drawable = this.f550x;
            if (drawable != null) {
                rVarS.setSelector(drawable);
            }
            this.f533g.setAdapter(this.f532f);
            this.f533g.setOnItemClickListener(this.f551y);
            this.f533g.setFocusable(true);
            this.f533g.setFocusableInTouchMode(true);
            this.f533g.setOnItemSelectedListener(new b());
            this.f533g.setOnScrollListener(this.C);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.f552z;
            if (onItemSelectedListener != null) {
                this.f533g.setOnItemSelectedListener(onItemSelectedListener);
            }
            r rVar = this.f533g;
            View view2 = this.f546t;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i5 = this.f547u;
                if (i5 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(rVar, layoutParams);
                } else if (i5 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.f547u);
                } else {
                    linearLayout.addView(rVar, layoutParams);
                    linearLayout.addView(view2);
                }
                int i6 = this.f535i;
                if (i6 >= 0) {
                    i4 = Integer.MIN_VALUE;
                } else {
                    i6 = 0;
                    i4 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i6, i4), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = rVar;
            }
            this.J.setContentView(view);
        } else {
            View view3 = this.f546t;
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
            int i7 = rect.top;
            i3 = rect.bottom + i7;
            if (!this.f539m) {
                this.f537k = -i7;
            }
        } else {
            this.G.setEmpty();
            i3 = 0;
        }
        int iU = u(t(), this.f537k, this.J.getInputMethodMode() == 2);
        if (this.f543q || this.f534h == -1) {
            return iU + i3;
        }
        int i8 = this.f535i;
        if (i8 == -2) {
            int i9 = this.f531e.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.G;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i8 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else {
            int i10 = this.f531e.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.G;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i10 - (rect3.left + rect3.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        int iD = this.f533g.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i3 + this.f533g.getPaddingTop() + this.f533g.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    public void r() {
        r rVar = this.f533g;
        if (rVar != null) {
            rVar.setListSelectionHidden(true);
            rVar.requestLayout();
        }
    }

    public r s(Context context, boolean z3) {
        return new r(context, z3);
    }

    public View t() {
        return this.f549w;
    }

    public final int u(View view, int i3, boolean z3) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.J.getMaxAvailableHeight(view, i3, z3);
        }
        Method method = f529c;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.J, view, Integer.valueOf(i3), Boolean.valueOf(z3))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.J.getMaxAvailableHeight(view, i3);
    }

    public Object v() {
        if (b()) {
            return this.f533g.getSelectedItem();
        }
        return null;
    }

    public long w() {
        if (b()) {
            return this.f533g.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int x() {
        if (b()) {
            return this.f533g.getSelectedItemPosition();
        }
        return -1;
    }

    public View y() {
        if (b()) {
            return this.f533g.getSelectedView();
        }
        return null;
    }

    public int z() {
        return this.f535i;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i3) {
        this(context, attributeSet, i3, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i3, int i4) {
        this.f534h = -2;
        this.f535i = -2;
        this.f538l = 1002;
        this.f542p = 0;
        this.f543q = false;
        this.f544r = false;
        this.f545s = Integer.MAX_VALUE;
        this.f547u = 0;
        this.A = new g();
        this.B = new f();
        this.C = new e();
        this.D = new c();
        this.G = new Rect();
        this.f531e = context;
        this.F = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i3, i4);
        this.f536j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.f537k = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f539m = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        i iVar = new i(context, attributeSet, i3, i4);
        this.J = iVar;
        iVar.setInputMethodMode(1);
    }
}
