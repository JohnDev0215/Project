package a.b.p;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public class k0 implements a.b.o.i.p {
    public boolean A;
    public PopupWindow B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ListAdapter f304b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public f0 f305c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f308f;
    public int g;
    public boolean i;
    public boolean j;
    public boolean k;
    public DataSetObserver q;
    public View r;
    public AdapterView.OnItemClickListener s;
    public final Handler x;
    public Rect z;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f306d = -2;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f307e = -2;
    public int h = 1002;
    public int l = 0;
    public boolean m = false;
    public boolean n = false;
    public int o = Integer.MAX_VALUE;
    public int p = 0;
    public final e t = new e();
    public final d u = new d();
    public final c v = new c();
    public final a w = new a();
    public final Rect y = new Rect();

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f0 f0Var = k0.this.f305c;
            if (f0Var != null) {
                f0Var.setListSelectionHidden(true);
                f0Var.requestLayout();
            }
        }
    }

    public class b extends DataSetObserver {
        public b() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            if (k0.this.a()) {
                k0.this.i();
            }
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            k0.this.dismiss();
        }
    }

    public class c implements AbsListView.OnScrollListener {
        public c() {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (i == 1) {
                if ((k0.this.B.getInputMethodMode() == 2) || k0.this.B.getContentView() == null) {
                    return;
                }
                k0 k0Var = k0.this;
                k0Var.x.removeCallbacks(k0Var.t);
                k0.this.t.run();
            }
        }
    }

    public class d implements View.OnTouchListener {
        public d() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PopupWindow popupWindow;
            int action = motionEvent.getAction();
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (action == 0 && (popupWindow = k0.this.B) != null && popupWindow.isShowing() && x >= 0 && x < k0.this.B.getWidth() && y >= 0 && y < k0.this.B.getHeight()) {
                k0 k0Var = k0.this;
                k0Var.x.postDelayed(k0Var.t, 250L);
                return false;
            }
            if (action != 1) {
                return false;
            }
            k0 k0Var2 = k0.this;
            k0Var2.x.removeCallbacks(k0Var2.t);
            return false;
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f0 f0Var = k0.this.f305c;
            if (f0Var == null || !a.h.k.q.B(f0Var) || k0.this.f305c.getCount() <= k0.this.f305c.getChildCount()) {
                return;
            }
            int childCount = k0.this.f305c.getChildCount();
            k0 k0Var = k0.this;
            if (childCount <= k0Var.o) {
                k0Var.B.setInputMethodMode(2);
                k0.this.i();
            }
        }
    }

    public k0(Context context, AttributeSet attributeSet, int i, int i2) {
        this.f303a = context;
        this.x = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b.j.ListPopupWindow, i, i2);
        this.f308f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(a.b.j.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(a.b.j.ListPopupWindow_android_dropDownVerticalOffset, 0);
        this.g = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.i = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        p pVar = new p(context, attributeSet, i, i2);
        this.B = pVar;
        pVar.setInputMethodMode(1);
    }

    @Override // a.b.o.i.p
    public boolean a() {
        return this.B.isShowing();
    }

    public void c(int i) {
        this.f308f = i;
    }

    public int d() {
        return this.f308f;
    }

    @Override // a.b.o.i.p
    public void dismiss() {
        this.B.dismiss();
        this.B.setContentView(null);
        this.f305c = null;
        this.x.removeCallbacks(this.t);
    }

    @Override // a.b.o.i.p
    public ListView e() {
        return this.f305c;
    }

    @Override // a.b.o.i.p
    public void i() {
        int i;
        int paddingBottom;
        f0 f0Var;
        int i2;
        if (this.f305c == null) {
            f0 f0VarQ = q(this.f303a, !this.A);
            this.f305c = f0VarQ;
            f0VarQ.setAdapter(this.f304b);
            this.f305c.setOnItemClickListener(this.s);
            this.f305c.setFocusable(true);
            this.f305c.setFocusableInTouchMode(true);
            this.f305c.setOnItemSelectedListener(new j0(this));
            this.f305c.setOnScrollListener(this.v);
            this.B.setContentView(this.f305c);
        }
        Drawable background = this.B.getBackground();
        boolean z = false;
        if (background != null) {
            background.getPadding(this.y);
            Rect rect = this.y;
            int i3 = rect.top;
            i = rect.bottom + i3;
            if (!this.i) {
                this.g = -i3;
            }
        } else {
            this.y.setEmpty();
            i = 0;
        }
        int maxAvailableHeight = this.B.getMaxAvailableHeight(this.r, this.g, this.B.getInputMethodMode() == 2);
        if (this.m || this.f306d == -1) {
            paddingBottom = maxAvailableHeight + i;
        } else {
            int i4 = this.f307e;
            if (i4 != -2) {
                i2 = 1073741824;
                if (i4 == -1) {
                    int i5 = this.f303a.getResources().getDisplayMetrics().widthPixels;
                    Rect rect2 = this.y;
                    i4 = i5 - (rect2.left + rect2.right);
                }
            } else {
                int i6 = this.f303a.getResources().getDisplayMetrics().widthPixels;
                Rect rect3 = this.y;
                i4 = i6 - (rect3.left + rect3.right);
                i2 = Integer.MIN_VALUE;
            }
            int iA = this.f305c.a(View.MeasureSpec.makeMeasureSpec(i4, i2), maxAvailableHeight - 0, -1);
            paddingBottom = iA + (iA > 0 ? this.f305c.getPaddingBottom() + this.f305c.getPaddingTop() + i + 0 : 0);
        }
        boolean z2 = this.B.getInputMethodMode() == 2;
        this.B.setWindowLayoutType(this.h);
        if (this.B.isShowing()) {
            if (a.h.k.q.B(this.r)) {
                int width = this.f307e;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.r.getWidth();
                }
                int i7 = this.f306d;
                if (i7 == -1) {
                    if (!z2) {
                        paddingBottom = -1;
                    }
                    if (z2) {
                        this.B.setWidth(this.f307e == -1 ? -1 : 0);
                        this.B.setHeight(0);
                    } else {
                        this.B.setWidth(this.f307e == -1 ? -1 : 0);
                        this.B.setHeight(-1);
                    }
                } else if (i7 != -2) {
                    paddingBottom = i7;
                }
                this.B.setOutsideTouchable((this.n || this.m) ? false : true);
                this.B.update(this.r, this.f308f, this.g, width < 0 ? -1 : width, paddingBottom < 0 ? -1 : paddingBottom);
                return;
            }
            return;
        }
        int width2 = this.f307e;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.r.getWidth();
        }
        int i8 = this.f306d;
        if (i8 == -1) {
            paddingBottom = -1;
        } else if (i8 != -2) {
            paddingBottom = i8;
        }
        this.B.setWidth(width2);
        this.B.setHeight(paddingBottom);
        this.B.setIsClippedToScreen(true);
        PopupWindow popupWindow = this.B;
        if (!this.n && !this.m) {
            z = true;
        }
        popupWindow.setOutsideTouchable(z);
        this.B.setTouchInterceptor(this.u);
        if (this.k) {
            this.B.setOverlapAnchor(this.j);
        }
        this.B.setEpicenterBounds(this.z);
        this.B.showAsDropDown(this.r, this.f308f, this.g, this.l);
        this.f305c.setSelection(-1);
        if ((!this.A || this.f305c.isInTouchMode()) && (f0Var = this.f305c) != null) {
            f0Var.setListSelectionHidden(true);
            f0Var.requestLayout();
        }
        if (this.A) {
            return;
        }
        this.x.post(this.w);
    }

    public int j() {
        if (this.i) {
            return this.g;
        }
        return 0;
    }

    public void l(Drawable drawable) {
        this.B.setBackgroundDrawable(drawable);
    }

    public void m(int i) {
        this.g = i;
        this.i = true;
    }

    public Drawable n() {
        return this.B.getBackground();
    }

    public void o(ListAdapter listAdapter) {
        DataSetObserver dataSetObserver = this.q;
        if (dataSetObserver == null) {
            this.q = new b();
        } else {
            ListAdapter listAdapter2 = this.f304b;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.f304b = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.q);
        }
        f0 f0Var = this.f305c;
        if (f0Var != null) {
            f0Var.setAdapter(this.f304b);
        }
    }

    public f0 q(Context context, boolean z) {
        return new f0(context, z);
    }

    public void r(int i) {
        Drawable background = this.B.getBackground();
        if (background == null) {
            this.f307e = i;
            return;
        }
        background.getPadding(this.y);
        Rect rect = this.y;
        this.f307e = rect.left + rect.right + i;
    }

    public void s(boolean z) {
        this.A = z;
        this.B.setFocusable(z);
    }
}
