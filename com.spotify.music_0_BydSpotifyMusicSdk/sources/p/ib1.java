package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ib1 implements jd2 {
    public static final Method H;
    public static final Method I;
    public final Handler C;
    public Rect E;
    public boolean F;
    public final d9 G;
    public final Context c;
    public ListAdapter e;
    public ib0 k;
    public int n;
    public int o;
    public boolean q;
    public boolean r;
    public boolean s;
    public fb1 v;
    public View w;
    public AdapterView.OnItemClickListener x;
    public final int l = -2;
    public int m = -2;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f133p = 1002;
    public int t = 0;
    public final int u = Integer.MAX_VALUE;
    public final bb1 y = new bb1(this, 2);
    public final hb1 z = new hb1(this);
    public final gb1 A = new gb1(this);
    public final bb1 B = new bb1(this, 1);
    public final Rect D = new Rect();

    static {
        if (Build.VERSION.SDK_INT <= 28) {
            try {
                H = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", Boolean.TYPE);
            } catch (NoSuchMethodException unused) {
            }
            try {
                I = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
            }
        }
    }

    public ib1(Context context, AttributeSet attributeSet, int i, int i2) {
        this.c = context;
        this.C = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yz1.o, i, i2);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.o = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.q = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        d9 d9Var = new d9(context, attributeSet, i, i2);
        v4 v4Var = new v4(context, context.obtainStyledAttributes(attributeSet, yz1.s, i, i2));
        if (v4Var.G(2)) {
            qw1.c(d9Var, v4Var.p(2, false));
        }
        d9Var.setBackgroundDrawable(v4Var.t(0));
        v4Var.L();
        this.G = d9Var;
        d9Var.setInputMethodMode(1);
    }

    @Override // p.jd2
    public final boolean a() {
        return this.G.isShowing();
    }

    public final int b() {
        return this.n;
    }

    @Override // p.jd2
    public final void c() {
        int i;
        int paddingBottom;
        ib0 ib0Var;
        ib0 ib0Var2 = this.k;
        d9 d9Var = this.G;
        Context context = this.c;
        if (ib0Var2 == null) {
            ib0 ib0VarQ = q(context, !this.F);
            this.k = ib0VarQ;
            ib0VarQ.setAdapter(this.e);
            this.k.setOnItemClickListener(this.x);
            this.k.setFocusable(true);
            this.k.setFocusableInTouchMode(true);
            this.k.setOnItemSelectedListener(new cb1(0, this));
            this.k.setOnScrollListener(this.A);
            d9Var.setContentView(this.k);
        }
        Drawable background = d9Var.getBackground();
        Rect rect = this.D;
        if (background != null) {
            background.getPadding(rect);
            int i2 = rect.top;
            i = rect.bottom + i2;
            if (!this.q) {
                this.o = -i2;
            }
        } else {
            rect.setEmpty();
            i = 0;
        }
        int iA = db1.a(d9Var, this.w, this.o, d9Var.getInputMethodMode() == 2);
        int i3 = this.l;
        if (i3 == -1) {
            paddingBottom = iA + i;
        } else {
            int i4 = this.m;
            int iA2 = this.k.a(i4 != -2 ? i4 != -1 ? View.MeasureSpec.makeMeasureSpec(i4, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iA);
            paddingBottom = iA2 + (iA2 > 0 ? this.k.getPaddingBottom() + this.k.getPaddingTop() + i : 0);
        }
        boolean z = this.G.getInputMethodMode() == 2;
        qw1.d(d9Var, this.f133p);
        if (d9Var.isShowing()) {
            View view = this.w;
            WeakHashMap weakHashMap = zt2.a;
            if (lt2.b(view)) {
                int width = this.m;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.w.getWidth();
                }
                if (i3 == -1) {
                    i3 = z ? paddingBottom : -1;
                    if (z) {
                        d9Var.setWidth(this.m == -1 ? -1 : 0);
                        d9Var.setHeight(0);
                    } else {
                        d9Var.setWidth(this.m == -1 ? -1 : 0);
                        d9Var.setHeight(-1);
                    }
                } else if (i3 == -2) {
                    i3 = paddingBottom;
                }
                d9Var.setOutsideTouchable(true);
                View view2 = this.w;
                int i5 = this.n;
                int i6 = this.o;
                if (width < 0) {
                    width = -1;
                }
                d9Var.update(view2, i5, i6, width, i3 < 0 ? -1 : i3);
                return;
            }
            return;
        }
        int width2 = this.m;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.w.getWidth();
        }
        if (i3 == -1) {
            i3 = -1;
        } else if (i3 == -2) {
            i3 = paddingBottom;
        }
        d9Var.setWidth(width2);
        d9Var.setHeight(i3);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = H;
            if (method != null) {
                try {
                    method.invoke(d9Var, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        } else {
            eb1.b(d9Var, true);
        }
        d9Var.setOutsideTouchable(true);
        d9Var.setTouchInterceptor(this.z);
        if (this.s) {
            qw1.c(d9Var, this.r);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = I;
            if (method2 != null) {
                try {
                    method2.invoke(d9Var, this.E);
                } catch (Exception e) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e);
                }
            }
        } else {
            eb1.a(d9Var, this.E);
        }
        pw1.a(d9Var, this.w, this.n, this.o, this.t);
        this.k.setSelection(-1);
        if ((!this.F || this.k.isInTouchMode()) && (ib0Var = this.k) != null) {
            ib0Var.setListSelectionHidden(true);
            ib0Var.requestLayout();
        }
        if (this.F) {
            return;
        }
        this.C.post(this.B);
    }

    public final Drawable d() {
        return this.G.getBackground();
    }

    @Override // p.jd2
    public final void dismiss() {
        d9 d9Var = this.G;
        d9Var.dismiss();
        d9Var.setContentView(null);
        this.k = null;
        this.C.removeCallbacks(this.y);
    }

    @Override // p.jd2
    public final ib0 e() {
        return this.k;
    }

    public final void g(Drawable drawable) {
        this.G.setBackgroundDrawable(drawable);
    }

    public final void h(int i) {
        this.o = i;
        this.q = true;
    }

    public final void j(int i) {
        this.n = i;
    }

    public final int l() {
        if (this.q) {
            return this.o;
        }
        return 0;
    }

    public void p(ListAdapter listAdapter) {
        fb1 fb1Var = this.v;
        if (fb1Var == null) {
            this.v = new fb1(0, this);
        } else {
            ListAdapter listAdapter2 = this.e;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(fb1Var);
            }
        }
        this.e = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.v);
        }
        ib0 ib0Var = this.k;
        if (ib0Var != null) {
            ib0Var.setAdapter(this.e);
        }
    }

    public ib0 q(Context context, boolean z) {
        return new ib0(context, z);
    }

    public final void r(int i) {
        Drawable background = this.G.getBackground();
        if (background == null) {
            this.m = i;
            return;
        }
        Rect rect = this.D;
        background.getPadding(rect);
        this.m = rect.left + rect.right + i;
    }
}
