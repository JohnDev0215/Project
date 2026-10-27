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
import b.f.m.s;
import b.f.n.h;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class ListPopupWindow implements p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f232c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f233d;
    public static Method s;
    public r E3;
    public int F3;
    public int G3;
    public int H3;
    public int I3;
    public int J3;
    public boolean K3;
    public boolean L3;
    public boolean M3;
    public int N3;
    public boolean O3;
    public boolean P3;
    public int Q3;
    public View R3;
    public int S3;
    public DataSetObserver T3;
    public View U3;
    public Drawable V3;
    public AdapterView.OnItemClickListener W3;
    public AdapterView.OnItemSelectedListener X3;
    public final g Y3;
    public final f Z3;
    public final e a4;
    public final c b4;
    public Runnable c4;
    public final Handler d4;
    public final Rect e4;
    public Rect f4;
    public boolean g4;
    public PopupWindow h4;
    public Context x;
    public ListAdapter y;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            View viewT = ListPopupWindow.this.t();
            if (viewT == null || viewT.getWindowToken() == null) {
                return;
            }
            ListPopupWindow.this.f();
        }
    }

    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
            r rVar;
            if (i2 == -1 || (rVar = ListPopupWindow.this.E3) == null) {
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
                ListPopupWindow.this.f();
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
            if (i2 != 1 || ListPopupWindow.this.w() || ListPopupWindow.this.h4.getContentView() == null) {
                return;
            }
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            listPopupWindow.d4.removeCallbacks(listPopupWindow.Y3);
            ListPopupWindow.this.Y3.run();
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
            if (action == 0 && (popupWindow = ListPopupWindow.this.h4) != null && popupWindow.isShowing() && x >= 0 && x < ListPopupWindow.this.h4.getWidth() && y >= 0 && y < ListPopupWindow.this.h4.getHeight()) {
                ListPopupWindow listPopupWindow = ListPopupWindow.this;
                listPopupWindow.d4.postDelayed(listPopupWindow.Y3, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            ListPopupWindow listPopupWindow2 = ListPopupWindow.this;
            listPopupWindow2.d4.removeCallbacks(listPopupWindow2.Y3);
            return false;
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r rVar = ListPopupWindow.this.E3;
            if (rVar == null || !s.F(rVar) || ListPopupWindow.this.E3.getCount() <= ListPopupWindow.this.E3.getChildCount()) {
                return;
            }
            int childCount = ListPopupWindow.this.E3.getChildCount();
            ListPopupWindow listPopupWindow = ListPopupWindow.this;
            if (childCount <= listPopupWindow.Q3) {
                listPopupWindow.h4.setInputMethodMode(2);
                ListPopupWindow.this.f();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                f232c = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                s = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                f233d = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, Boolean.TYPE);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public ListPopupWindow(Context context) {
        this(context, null, b.b.a.E);
    }

    public void A(int i2) {
        this.h4.setAnimationStyle(i2);
    }

    public void B(int i2) {
        Drawable background = this.h4.getBackground();
        if (background == null) {
            M(i2);
            return;
        }
        background.getPadding(this.e4);
        Rect rect = this.e4;
        this.G3 = rect.left + rect.right + i2;
    }

    public void C(int i2) {
        this.N3 = i2;
    }

    public void D(Rect rect) {
        this.f4 = rect != null ? new Rect(rect) : null;
    }

    public void E(int i2) {
        this.h4.setInputMethodMode(i2);
    }

    public void F(boolean z) {
        this.g4 = z;
        this.h4.setFocusable(z);
    }

    public void G(PopupWindow.OnDismissListener onDismissListener) {
        this.h4.setOnDismissListener(onDismissListener);
    }

    public void H(AdapterView.OnItemClickListener onItemClickListener) {
        this.W3 = onItemClickListener;
    }

    public void I(boolean z) {
        this.M3 = true;
        this.L3 = z;
    }

    public final void J(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.h4.setIsClippedToScreen(z);
            return;
        }
        Method method = f232c;
        if (method != null) {
            try {
                method.invoke(this.h4, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    public void K(int i2) {
        this.S3 = i2;
    }

    public void L(int i2) {
        r rVar = this.E3;
        if (!b() || rVar == null) {
            return;
        }
        rVar.setListSelectionHidden(false);
        rVar.setSelection(i2);
        if (rVar.getChoiceMode() != 0) {
            rVar.setItemChecked(i2, true);
        }
    }

    public void M(int i2) {
        this.G3 = i2;
    }

    public void a(int i2) {
        this.H3 = i2;
    }

    @Override // b.b.p.j.p
    public boolean b() {
        return this.h4.isShowing();
    }

    public int c() {
        return this.H3;
    }

    @Override // b.b.p.j.p
    public void dismiss() {
        this.h4.dismiss();
        y();
        this.h4.setContentView(null);
        this.E3 = null;
        this.d4.removeCallbacks(this.Y3);
    }

    @Override // b.b.p.j.p
    public void f() {
        int iQ = q();
        boolean zW = w();
        h.b(this.h4, this.J3);
        if (this.h4.isShowing()) {
            if (s.F(t())) {
                int width = this.G3;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i2 = this.F3;
                if (i2 == -1) {
                    if (!zW) {
                        iQ = -1;
                    }
                    if (zW) {
                        this.h4.setWidth(this.G3 == -1 ? -1 : 0);
                        this.h4.setHeight(0);
                    } else {
                        this.h4.setWidth(this.G3 == -1 ? -1 : 0);
                        this.h4.setHeight(-1);
                    }
                } else if (i2 != -2) {
                    iQ = i2;
                }
                this.h4.setOutsideTouchable((this.P3 || this.O3) ? false : true);
                this.h4.update(t(), this.H3, this.I3, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.G3;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i3 = this.F3;
        if (i3 == -1) {
            iQ = -1;
        } else if (i3 != -2) {
            iQ = i3;
        }
        this.h4.setWidth(width2);
        this.h4.setHeight(iQ);
        J(true);
        this.h4.setOutsideTouchable((this.P3 || this.O3) ? false : true);
        this.h4.setTouchInterceptor(this.Z3);
        if (this.M3) {
            h.a(this.h4, this.L3);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = s;
            if (method != null) {
                try {
                    method.invoke(this.h4, this.f4);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.h4.setEpicenterBounds(this.f4);
        }
        h.c(this.h4, t(), this.H3, this.I3, this.N3);
        this.E3.setSelection(-1);
        if (!this.g4 || this.E3.isInTouchMode()) {
            r();
        }
        if (this.g4) {
            return;
        }
        this.d4.post(this.b4);
    }

    public int g() {
        if (this.K3) {
            return this.I3;
        }
        return 0;
    }

    public Drawable i() {
        return this.h4.getBackground();
    }

    @Override // b.b.p.j.p
    public ListView k() {
        return this.E3;
    }

    public void m(Drawable drawable) {
        this.h4.setBackgroundDrawable(drawable);
    }

    public void n(int i2) {
        this.I3 = i2;
        this.K3 = true;
    }

    public void o(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.T3;
        if (dataSetObserver == null) {
            this.T3 = new d();
        } else {
            ListAdapter listAdapter2 = this.y;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.y = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.T3);
        }
        r rVar = this.E3;
        if (rVar != null) {
            rVar.setAdapter(this.y);
        }
    }

    public final int q() {
        int measuredHeight;
        int i2;
        int iMakeMeasureSpec;
        View view;
        int i3;
        if (this.E3 == null) {
            Context context = this.x;
            this.c4 = new a();
            r rVarS = s(context, !this.g4);
            this.E3 = rVarS;
            Drawable drawable = this.V3;
            if (drawable != null) {
                rVarS.setSelector(drawable);
            }
            this.E3.setAdapter(this.y);
            this.E3.setOnItemClickListener(this.W3);
            this.E3.setFocusable(true);
            this.E3.setFocusableInTouchMode(true);
            this.E3.setOnItemSelectedListener(new b());
            this.E3.setOnScrollListener(this.a4);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.X3;
            if (onItemSelectedListener != null) {
                this.E3.setOnItemSelectedListener(onItemSelectedListener);
            }
            r rVar = this.E3;
            View view2 = this.R3;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i4 = this.S3;
                if (i4 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(rVar, layoutParams);
                } else if (i4 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.S3);
                } else {
                    linearLayout.addView(rVar, layoutParams);
                    linearLayout.addView(view2);
                }
                int i5 = this.G3;
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
            this.h4.setContentView(view);
        } else {
            View view3 = this.R3;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.h4.getBackground();
        if (background != null) {
            background.getPadding(this.e4);
            Rect rect = this.e4;
            int i6 = rect.top;
            i2 = rect.bottom + i6;
            if (!this.K3) {
                this.I3 = -i6;
            }
        } else {
            this.e4.setEmpty();
            i2 = 0;
        }
        int iU = u(t(), this.I3, this.h4.getInputMethodMode() == 2);
        if (this.O3 || this.F3 == -1) {
            return iU + i2;
        }
        int i7 = this.G3;
        if (i7 == -2) {
            int i8 = this.x.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.e4;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i7 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        } else {
            int i9 = this.x.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.e4;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i9 - (rect3.left + rect3.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        int iD = this.E3.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i2 + this.E3.getPaddingTop() + this.E3.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    public void r() {
        r rVar = this.E3;
        if (rVar != null) {
            rVar.setListSelectionHidden(true);
            rVar.requestLayout();
        }
    }

    public r s(Context context, boolean z) {
        return new r(context, z);
    }

    public View t() {
        return this.U3;
    }

    public final int u(View view, int i2, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.h4.getMaxAvailableHeight(view, i2, z);
        }
        Method method = f233d;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.h4, view, Integer.valueOf(i2), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.h4.getMaxAvailableHeight(view, i2);
    }

    public int v() {
        return this.G3;
    }

    public boolean w() {
        return this.h4.getInputMethodMode() == 2;
    }

    public boolean x() {
        return this.g4;
    }

    public final void y() {
        View view = this.R3;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.R3);
            }
        }
    }

    public void z(View view) {
        this.U3 = view;
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.E);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeSet, int i2, int i3) {
        this.F3 = -2;
        this.G3 = -2;
        this.J3 = 1002;
        this.N3 = 0;
        this.O3 = false;
        this.P3 = false;
        this.Q3 = Integer.MAX_VALUE;
        this.S3 = 0;
        this.Y3 = new g();
        this.Z3 = new f();
        this.a4 = new e();
        this.b4 = new c();
        this.e4 = new Rect();
        this.x = context;
        this.d4 = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.q1, i2, i3);
        this.H3 = typedArrayObtainStyledAttributes.getDimensionPixelOffset(j.r1, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(j.s1, 0);
        this.I3 = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.K3 = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        i iVar = new i(context, attributeSet, i2, i3);
        this.h4 = iVar;
        iVar.setInputMethodMode(1);
    }
}
