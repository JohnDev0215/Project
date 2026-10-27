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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class G implements androidx.appcompat.view.menu.o {
    private static Method H;
    private static Method I;
    private static Method J;
    private final d A;
    private final b B;
    final Handler C;
    private final Rect D;
    private Rect E;
    private boolean F;
    PopupWindow G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f275c;
    private ListAdapter d;
    D e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    int q;
    private View r;
    private int s;
    private DataSetObserver t;
    private View u;
    private Drawable v;
    private AdapterView.OnItemClickListener w;
    private AdapterView.OnItemSelectedListener x;
    final f y;
    private final e z;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements AdapterView.OnItemSelectedListener {
        a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            D d;
            if (i == -1 || (d = G.this.e) == null) {
                return;
            }
            d.i(false);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            G.this.r();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class c extends DataSetObserver {
        c() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (G.this.b()) {
                G.this.f();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            G.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class d implements AbsListView.OnScrollListener {
        d() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i != 1 || G.this.w() || G.this.G.getContentView() == null) {
                return;
            }
            G g = G.this;
            g.C.removeCallbacks(g.y);
            G.this.y.run();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class e implements View.OnTouchListener {
        e() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = G.this.G) != null && popupWindow.isShowing() && x >= 0 && x < G.this.G.getWidth() && y >= 0 && y < G.this.G.getHeight()) {
                G g = G.this;
                g.C.postDelayed(g.y, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            G g2 = G.this;
            g2.C.removeCallbacks(g2.y);
            return false;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            D d = G.this.e;
            if (d == null || !ViewCompat.A(d) || G.this.e.getCount() <= G.this.e.getChildCount()) {
                return;
            }
            int childCount = G.this.e.getChildCount();
            G g = G.this;
            if (childCount <= g.q) {
                g.G.setInputMethodMode(2);
                G.this.f();
            }
        }
    }

    static {
        Class cls = Boolean.TYPE;
        int i = Build.VERSION.SDK_INT;
        if (i <= 28) {
            try {
                H = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", cls);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                J = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (i <= 23) {
            try {
                I = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, cls);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public G(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        this(context, attributeSet, i, 0);
    }

    private void J(boolean z) {
        if (Build.VERSION.SDK_INT > 28) {
            this.G.setIsClippedToScreen(z);
            return;
        }
        Method method = H;
        if (method != null) {
            try {
                method.invoke(this.G, Boolean.valueOf(z));
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
        }
    }

    private int q() {
        int measuredHeight;
        int i;
        int iMakeMeasureSpec;
        View view;
        int i2;
        if (this.e == null) {
            Context context = this.f275c;
            D dS = s(context, !this.F);
            this.e = dS;
            Drawable drawable = this.v;
            if (drawable != null) {
                dS.setSelector(drawable);
            }
            this.e.setAdapter(this.d);
            this.e.setOnItemClickListener(this.w);
            this.e.setFocusable(true);
            this.e.setFocusableInTouchMode(true);
            this.e.setOnItemSelectedListener(new a());
            this.e.setOnScrollListener(this.A);
            AdapterView.OnItemSelectedListener onItemSelectedListener = this.x;
            if (onItemSelectedListener != null) {
                this.e.setOnItemSelectedListener(onItemSelectedListener);
            }
            D d2 = this.e;
            View view2 = this.r;
            if (view2 != null) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0, 1.0f);
                int i3 = this.s;
                if (i3 == 0) {
                    linearLayout.addView(view2);
                    linearLayout.addView(d2, layoutParams);
                } else if (i3 != 1) {
                    Log.e("ListPopupWindow", "Invalid hint position " + this.s);
                } else {
                    linearLayout.addView(d2, layoutParams);
                    linearLayout.addView(view2);
                }
                int i4 = this.g;
                if (i4 >= 0) {
                    i2 = Integer.MIN_VALUE;
                } else {
                    i4 = 0;
                    i2 = 0;
                }
                view2.measure(View.MeasureSpec.makeMeasureSpec(i4, i2), 0);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                measuredHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                view = linearLayout;
            } else {
                measuredHeight = 0;
                view = d2;
            }
            this.G.setContentView(view);
        } else {
            View view3 = this.r;
            if (view3 != null) {
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view3.getLayoutParams();
                measuredHeight = view3.getMeasuredHeight() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            } else {
                measuredHeight = 0;
            }
        }
        Drawable background = this.G.getBackground();
        if (background != null) {
            background.getPadding(this.D);
            Rect rect = this.D;
            int i5 = rect.top;
            i = rect.bottom + i5;
            if (!this.k) {
                this.i = -i5;
            }
        } else {
            this.D.setEmpty();
            i = 0;
        }
        int iU = u(t(), this.i, this.G.getInputMethodMode() == 2);
        if (this.o || this.f == -1) {
            return iU + i;
        }
        int i6 = this.g;
        if (i6 == -2) {
            int i7 = this.f275c.getResources().getDisplayMetrics().widthPixels;
            Rect rect2 = this.D;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 - (rect2.left + rect2.right), Integer.MIN_VALUE);
        } else if (i6 != -1) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, 1073741824);
        } else {
            int i8 = this.f275c.getResources().getDisplayMetrics().widthPixels;
            Rect rect3 = this.D;
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i8 - (rect3.left + rect3.right), 1073741824);
        }
        int iD = this.e.d(iMakeMeasureSpec, 0, -1, iU - measuredHeight, -1);
        if (iD > 0) {
            measuredHeight += i + this.e.getPaddingTop() + this.e.getPaddingBottom();
        }
        return iD + measuredHeight;
    }

    private int u(View view, int i, boolean z) {
        if (Build.VERSION.SDK_INT > 23) {
            return this.G.getMaxAvailableHeight(view, i, z);
        }
        Method method = I;
        if (method != null) {
            try {
                return ((Integer) method.invoke(this.G, view, Integer.valueOf(i), Boolean.valueOf(z))).intValue();
            } catch (Exception unused) {
                Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
            }
        }
        return this.G.getMaxAvailableHeight(view, i);
    }

    private void y() {
        View view = this.r;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.r);
            }
        }
    }

    public void A(@StyleRes int i) {
        this.G.setAnimationStyle(i);
    }

    public void B(int i) {
        Drawable background = this.G.getBackground();
        if (background == null) {
            M(i);
            return;
        }
        background.getPadding(this.D);
        Rect rect = this.D;
        this.g = rect.left + rect.right + i;
    }

    public void C(int i) {
        this.n = i;
    }

    public void D(@Nullable Rect rect) {
        this.E = rect != null ? new Rect(rect) : null;
    }

    public void E(int i) {
        this.G.setInputMethodMode(i);
    }

    public void F(boolean z) {
        this.F = z;
        this.G.setFocusable(z);
    }

    public void G(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.G.setOnDismissListener(onDismissListener);
    }

    public void H(@Nullable AdapterView.OnItemClickListener onItemClickListener) {
        this.w = onItemClickListener;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void I(boolean z) {
        this.m = true;
        this.l = z;
    }

    public void K(int i) {
        this.s = i;
    }

    public void L(int i) {
        D d2 = this.e;
        if (!b() || d2 == null) {
            return;
        }
        d2.i(false);
        d2.setSelection(i);
        if (d2.getChoiceMode() != 0) {
            d2.setItemChecked(i, true);
        }
    }

    public void M(int i) {
        this.g = i;
    }

    public void a(int i) {
        this.h = i;
    }

    @Override // androidx.appcompat.view.menu.o
    public boolean b() {
        return this.G.isShowing();
    }

    public int c() {
        return this.h;
    }

    @Override // androidx.appcompat.view.menu.o
    public void dismiss() {
        this.G.dismiss();
        y();
        this.G.setContentView(null);
        this.e = null;
        this.C.removeCallbacks(this.y);
    }

    @Override // androidx.appcompat.view.menu.o
    public void f() {
        int iQ = q();
        boolean zW = w();
        androidx.core.widget.h.b(this.G, this.j);
        if (this.G.isShowing()) {
            if (ViewCompat.A(t())) {
                int width = this.g;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = t().getWidth();
                }
                int i = this.f;
                if (i == -1) {
                    if (!zW) {
                        iQ = -1;
                    }
                    if (zW) {
                        this.G.setWidth(this.g == -1 ? -1 : 0);
                        this.G.setHeight(0);
                    } else {
                        this.G.setWidth(this.g == -1 ? -1 : 0);
                        this.G.setHeight(-1);
                    }
                } else if (i != -2) {
                    iQ = i;
                }
                this.G.setOutsideTouchable((this.p || this.o) ? false : true);
                this.G.update(t(), this.h, this.i, width < 0 ? -1 : width, iQ < 0 ? -1 : iQ);
                return;
            }
            return;
        }
        int width2 = this.g;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = t().getWidth();
        }
        int i2 = this.f;
        if (i2 == -1) {
            iQ = -1;
        } else if (i2 != -2) {
            iQ = i2;
        }
        this.G.setWidth(width2);
        this.G.setHeight(iQ);
        J(true);
        this.G.setOutsideTouchable((this.p || this.o) ? false : true);
        this.G.setTouchInterceptor(this.z);
        if (this.m) {
            androidx.core.widget.h.a(this.G, this.l);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = J;
            if (method != null) {
                try {
                    method.invoke(this.G, this.E);
                } catch (Exception e2) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e2);
                }
            }
        } else {
            this.G.setEpicenterBounds(this.E);
        }
        androidx.core.widget.h.c(this.G, t(), this.h, this.i, this.n);
        this.e.setSelection(-1);
        if (!this.F || this.e.isInTouchMode()) {
            r();
        }
        if (this.F) {
            return;
        }
        this.C.post(this.B);
    }

    public int g() {
        if (this.k) {
            return this.i;
        }
        return 0;
    }

    @Nullable
    public Drawable i() {
        return this.G.getBackground();
    }

    @Override // androidx.appcompat.view.menu.o
    @Nullable
    public ListView k() {
        return this.e;
    }

    public void m(@Nullable Drawable drawable) {
        this.G.setBackgroundDrawable(drawable);
    }

    public void n(int i) {
        this.i = i;
        this.k = true;
    }

    public void o(@Nullable ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.t;
        if (dataSetObserver == null) {
            this.t = new c();
        } else {
            ListAdapter listAdapter2 = this.d;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.d = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.t);
        }
        D d2 = this.e;
        if (d2 != null) {
            d2.setAdapter(this.d);
        }
    }

    public void r() {
        D d2 = this.e;
        if (d2 != null) {
            d2.i(true);
            d2.requestLayout();
        }
    }

    @NonNull
    D s(Context context, boolean z) {
        return new D(context, z);
    }

    @Nullable
    public View t() {
        return this.u;
    }

    public int v() {
        return this.g;
    }

    public boolean w() {
        return this.G.getInputMethodMode() == 2;
    }

    public boolean x() {
        return this.F;
    }

    public void z(@Nullable View view) {
        this.u = view;
    }

    public G(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        this.f = -2;
        this.g = -2;
        this.j = 1002;
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = Integer.MAX_VALUE;
        this.s = 0;
        this.y = new f();
        this.z = new e();
        this.A = new d();
        this.B = new b();
        this.D = new Rect();
        this.f275c = context;
        this.C = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPopupWindow, i, i2);
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.i = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.k = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        C0070p c0070p = new C0070p(context, attributeSet, i, i2);
        this.G = c0070p;
        c0070p.setInputMethodMode(1);
    }
}
