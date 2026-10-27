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
import o0.t;
import q.p;
import r.i;
import r.r;
import r0.h;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f629d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Method f630e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Method f631f;
    public AdapterView.OnItemClickListener A;
    public AdapterView.OnItemSelectedListener B;
    public final g C;
    public final f D;
    public final e E;
    public final c F;
    public Runnable G;
    public final Handler H;
    public final Rect I;
    public Rect J;
    public boolean K;
    public PopupWindow L;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Context f632g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ListAdapter f633h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public r f634i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f635j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f636k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f637l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f638m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f639n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f640o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f641p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f642q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f643r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f644s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f645t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f646u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public View f647v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f648w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public DataSetObserver f649x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f650y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Drawable f651z;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewT = ListPopupWindow.this.t();
            if (viewT == null || viewT.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.e();
        }
    }

    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i8, long j8) {
            r rVar;
            if (i8 == -1 || (rVar = ListPopupWindow.this.f634i) == null) {
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
            if (ListPopupWindow.this.c()) {
                ListPopupWindow.this.e();
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
        public void onScroll(AbsListView absListView, int i8, int i9, int i10) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i8) {
            if (i8 != 1 || ListPopupWindow.this.A() || ListPopupWindow.this.L.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.H.removeCallbacks(listPopupWindow.C);
            ListPopupWindow.this.C.run();
        }
    }

    public class f implements View.OnTouchListener {
        public f() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x7 = (int) motionEvent.getX();
            int y7 = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = ListPopupWindow.this.L) != null && popupWindow.isShowing() && x7 >= 0 && x7 < ListPopupWindow.this.L.getWidth() && y7 >= 0 && y7 < ListPopupWindow.this.L.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.H.postDelayed(listPopupWindow.C, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.H.removeCallbacks(listPopupWindow2.C);
            return false;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = ListPopupWindow.this.f634i;
            if (rVar == null || !t.P(rVar) || ListPopupWindow.this.f634i.getCount() <= ListPopupWindow.this.f634i.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.f634i.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.f646u) {
                listPopupWindow.L.setInputMethodMode(2);
                ListPopupWindow.this.e();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f629d = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                f631f = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                f630e = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, R$attr.listPopupWindowStyle);
    }

    public boolean A() {
        return this.L.getInputMethodMode() == 2;
    }

    public boolean B() {
        return this.K;
    }

    public final void C() {
        View view = this.f647v;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.f647v);
            }
        }
    }

    public void D(View view) {
        this.f650y = view;
    }

    public void E(int i8) {
        this.L.setAnimationStyle(i8);
    }

    public void F(int i8) {
        Drawable background = this.L.getBackground();
        if (background == null) {
            Q(i8);
            return;
        }
        background.getPadding(this.I);
        Rect rect = this.I;
        this.f636k = rect.left + rect.right + i8;
    }

    public void G(int i8) {
        this.f643r = i8;
    }

    public void H(Rect rect) {
        this.J = rect != null ? new Rect(rect) : null;
    }

    public void I(int i8) {
        this.L.setInputMethodMode(i8);
    }

    public void J(boolean z7) {
        this.K = z7;
        this.L.setFocusable(z7);
    }

    public void K(PopupWindow.OnDismissListener onDismissListener) {
        this.L.setOnDismissListener(onDismissListener);
    }

    public void L(AdapterView.OnItemClickListener onItemClickListener) {
        this.A = onItemClickListener;
    }

    public void M(boolean z7) {
        this.f642q = true;
        this.f641p = z7;
    }

    public final void N(boolean z7) {
        if (Build.VERSION.SDK_INT > 28) {
            this.L.setIsClippedToScreen(z7);
            return;
        }
        Method method = f629d;
        if (method != null) {
            try {
                method.invoke(this.L, Boolean.valueOf(z7));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void O(int i8) {
        this.f648w = i8;
    }

    public void P(int i8) {
        r rVar = this.f634i;
        if (!c() || rVar == null) {
            return;
        }
        rVar.setListSelectionHidden(false);
        rVar.setSelection(i8);
        if (rVar.getChoiceMode() != 0) {
            rVar.setItemChecked(i8, true);
        }
    }

    public void Q(int i8) {
        this.f636k = i8;
    }

    public void b(Drawable drawable) {
        this.L.setBackgroundDrawable(drawable);
    }

    @Override // q.p
    public boolean c() {
        return this.L.isShowing();
    }

    public int d() {
        return this.f637l;
    }

    @Override // q.p
    public void dismiss() {
        this.L.dismiss();
        C();
        this.L.setContentView(null);
        this.f634i = null;
        this.H.removeCallbacks(this.C);
    }

    @Override // q.p
    public void e() {
        int iQ = q();
        boolean zA = A();
        h.b(this.L, this.f639n);
        if (this.L.isShowing()) {
            if (t.P(t())) {
                int width = this.f636k;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i8 = this.f635j;
                if (i8 == -1) {
                    if (!zA) {
                        iQ = -1;
                    }
                    if (zA) {
                        this.L.setWidth(this.f636k == -1 ? -1 : 0);
                        this.L.setHeight(0);
                    } else {
                        this.L.setWidth(this.f636k == -1 ? -1 : 0);
                        this.L.setHeight(-1);
                    }
                } else if (i8 != -2) {
                    iQ = i8;
                }
                this.L.setOutsideTouchable((this.f645t || this.f644s) ? false : true);
                this.L.update(t(), this.f637l, this.f638m, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.f636k;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i9 = this.f635j;
        if (i9 == -1) {
            iQ = -1;
        } else if (i9 != -2) {
            iQ = i9;
        }
        this.L.setWidth(width2);
        this.L.setHeight(iQ);
        N(true);
        this.L.setOutsideTouchable((this.f645t || this.f644s) ? false : true);
        this.L.setTouchInterceptor(this.D);
        if (this.f642q) {
            h.a(this.L, this.f641p);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = f631f;
            if (method != null) {
                try {
                    method.invoke(this.L, this.J);
                } catch (Exception e8) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e8);
                }
            }
        } else {
            this.L.setEpicenterBounds(this.J);
        }
        h.c(this.L, t(), this.f637l, this.f638m, this.f643r);
        this.f634i.setSelection(-1);
        if (!this.K || this.f634i.isInTouchMode()) {
            r();
        }
        if (this.K) {
            return;
        }
        this.H.post(this.F);
    }

    public Drawable g() {
        return this.L.getBackground();
    }

    @Override // q.p
    public ListView h() {
        return this.f634i;
    }

    public void j(int i8) {
        this.f638m = i8;
        this.f640o = true;
    }

    public void l(int i8) {
        this.f637l = i8;
    }

    public int n() {
        if (this.f640o) {
            return this.f638m;
        }
        return 0;
    }

    public void p(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.f649x;
        if (dataSetObserver == null) {
            this.f649x = new d();
        } else {
            ListAdapter listAdapter2 = this.f633h;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f633h = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f649x);
        }
        r rVar = this.f634i;
        if (rVar != null) {
            rVar.setAdapter(this.f633h);
        }
    }

    public final int q() {
        int measuredHeight;
        int i8;
        int iMakeMeasureSpec;
        View view;
        int i9;
        if (this.f634i == null) {
            Context context = this.f632g;
            this.G = new a();
            r rVarS = s(context, !this.K);
            this.f634i = rVarS;
            Drawable drawable = this.f651z;
            if (drawable != null) {
                rVarS.setSelector(drawable);
            }
            this.f634i.setAdapter(this.f633h);
            this.f634i.setOnItemClickListener(this.A);
            this.f634i.setFocusable(true);
            this.f634i.setFocusableInTouchMode(true);
            this.f634i.setOnItemSelectedListener(new b());
            this.f634i.setOnScrollListener(this.E);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.B;
            if (onItemSelectedListener != null) {
                this.f634i.setOnItemSelectedListener(onItemSelectedListener);
            }
            r rVar = this.f634i;
            View view2 = this.f647v;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i10 = this.f648w;
                if (i10 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(rVar, layoutParams);
                } else if (i10 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.f648w);
                } else {
                    linearLayout.addView(rVar, layoutParams);
                    linearLayout.addView(view2);
                }
                int i11 = this.f636k;
                if (i11 >= 0) {
                    i9 = Integer.MIN_VALUE;
                } else {
                    i11 = 0;
                    i9 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i11, i9), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = rVar;
            }
            this.L.setContentView(view);
        } else {
            View view3 = this.f647v;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.L.getBackground();
        if (background != null) {
            background.getPadding(this.I);
            Rect rect = this.I;
            int i12 = rect.top;
            i8 = rect.bottom + i12;
            if (!this.f640o) {
                this.f638m = -i12;
            }
        } else {
            this.I.setEmpty();
            i8 = 0;
        }
        int iU = u(t(), this.f638m, this.L.getInputMethodMode() == 2);
        if (this.f644s || this.f635j == -1) {
            return iU + i8;
        }
        int i13 = this.f636k;
        if (i13 == -2) {
            int i14 = this.f632g.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.I;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i14 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i13 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i13, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else {
            int i15 = this.f632g.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.I;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i15 - (rect3.left + rect3.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        int iD = this.f634i.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i8 + this.f634i.getPaddingTop() + this.f634i.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    public void r() {
        r rVar = this.f634i;
        if (rVar != null) {
            rVar.setListSelectionHidden(true);
            rVar.requestLayout();
        }
    }

    public r s(Context context, boolean z7) {
        return new r(context, z7);
    }

    public View t() {
        return this.f650y;
    }

    public final int u(View view, int i8, boolean z7) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.L.getMaxAvailableHeight(view, i8, z7);
        }
        Method method = f630e;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.L, view, Integer.valueOf(i8), Boolean.valueOf(z7))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.L.getMaxAvailableHeight(view, i8);
    }

    public Object v() {
        if (c()) {
            return this.f634i.getSelectedItem();
        }
        return null;
    }

    public long w() {
        if (c()) {
            return this.f634i.getSelectedItemId();
        }
        return Long.MIN_VALUE;
    }

    public int x() {
        if (c()) {
            return this.f634i.getSelectedItemPosition();
        }
        return -1;
    }

    public View y() {
        if (c()) {
            return this.f634i.getSelectedView();
        }
        return null;
    }

    public int z() {
        return this.f636k;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i8) {
        this(context, attributeSet, i8, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i8, int i9) {
        this.f635j = -2;
        this.f636k = -2;
        this.f639n = 1002;
        this.f643r = 0;
        this.f644s = false;
        this.f645t = false;
        this.f646u = Integer.MAX_VALUE;
        this.f648w = 0;
        this.C = new g();
        this.D = new f();
        this.E = new e();
        this.F = new c();
        this.I = new Rect();
        this.f632g = context;
        this.H = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i8, i9);
        this.f637l = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.f638m = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f640o = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        i iVar = new i(context, attributeSet, i8, i9);
        this.L = iVar;
        iVar.setInputMethodMode(1);
    }
}
