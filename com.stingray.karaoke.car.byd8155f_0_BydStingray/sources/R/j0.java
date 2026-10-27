package r;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Handler;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import l3.C1764u;
import n.AbstractC1850a;
import q.AbstractC2153j;
import q.InterfaceC2159p;
import q.MenuC2151h;
import q.MenuItemC2152i;

/* JADX INFO: loaded from: classes.dex */
public final class j0 implements f0, InterfaceC2159p {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final Handler f21501C;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Rect f21503E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f21504F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final C2264w f21505G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public C1764u f21506H;
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ListAdapter f21507m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public i0 f21508n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f21509p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f21510q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f21511r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f21512s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f21513t;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public C2243c0 f21515v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public View f21516w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public AbstractC2153j f21517x;
    public int o = -2;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f21514u = 0;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final RunnableC2241b0 f21518y = new RunnableC2241b0(this, 1);

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final e0 f21519z = new e0(this);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C2245d0 f21500A = new C2245d0(this);
    public final RunnableC2241b0 B = new RunnableC2241b0(this, 0);

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final Rect f21502D = new Rect();

    public j0(Context context, int i7) {
        int resourceId;
        this.l = context;
        this.f21501C = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, AbstractC1850a.l, i7, 0);
        this.f21509p = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.f21510q = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f21511r = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        C2264w c2264w = new C2264w(context, null, i7, 0);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(null, AbstractC1850a.f19363p, i7, 0);
        if (typedArrayObtainStyledAttributes2.hasValue(2)) {
            c2264w.setOverlapAnchor(typedArrayObtainStyledAttributes2.getBoolean(2, false));
        }
        c2264w.setBackgroundDrawable((!typedArrayObtainStyledAttributes2.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes2.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes2.getDrawable(0) : h6.f.k(context, resourceId));
        typedArrayObtainStyledAttributes2.recycle();
        this.f21505G = c2264w;
        c2264w.setInputMethodMode(1);
    }

    public final void a(ListAdapter listAdapter) {
        C2243c0 c2243c0 = this.f21515v;
        if (c2243c0 == null) {
            this.f21515v = new C2243c0(this);
        } else {
            ListAdapter listAdapter2 = this.f21507m;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(c2243c0);
            }
        }
        this.f21507m = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.f21515v);
        }
        i0 i0Var = this.f21508n;
        if (i0Var != null) {
            i0Var.setAdapter(this.f21507m);
        }
    }

    @Override // q.InterfaceC2159p
    public final void c() {
        int i7;
        i0 i0Var;
        i0 i0Var2 = this.f21508n;
        C2264w c2264w = this.f21505G;
        Context context = this.l;
        if (i0Var2 == null) {
            i0 i0Var3 = new i0(context, !this.f21504F);
            i0Var3.setHoverListener(this);
            this.f21508n = i0Var3;
            i0Var3.setAdapter(this.f21507m);
            this.f21508n.setOnItemClickListener(this.f21517x);
            this.f21508n.setFocusable(true);
            this.f21508n.setFocusableInTouchMode(true);
            this.f21508n.setOnItemSelectedListener(new C2236Y(this));
            this.f21508n.setOnScrollListener(this.f21500A);
            c2264w.setContentView(this.f21508n);
        }
        Drawable background = c2264w.getBackground();
        Rect rect = this.f21502D;
        if (background != null) {
            background.getPadding(rect);
            int i10 = rect.top;
            i7 = rect.bottom + i10;
            if (!this.f21511r) {
                this.f21510q = -i10;
            }
        } else {
            rect.setEmpty();
            i7 = 0;
        }
        int iA = AbstractC2237Z.a(c2264w, this.f21516w, this.f21510q, c2264w.getInputMethodMode() == 2);
        int i11 = this.o;
        int iA2 = this.f21508n.a(i11 != -2 ? i11 != -1 ? View.MeasureSpec.makeMeasureSpec(i11, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iA);
        int paddingBottom = iA2 + (iA2 > 0 ? this.f21508n.getPaddingBottom() + this.f21508n.getPaddingTop() + i7 : 0);
        this.f21505G.getInputMethodMode();
        c2264w.setWindowLayoutType(1002);
        if (c2264w.isShowing()) {
            if (this.f21516w.isAttachedToWindow()) {
                int width = this.o;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.f21516w.getWidth();
                }
                c2264w.setOutsideTouchable(true);
                c2264w.update(this.f21516w, this.f21509p, this.f21510q, width < 0 ? -1 : width, paddingBottom < 0 ? -1 : paddingBottom);
                return;
            }
            return;
        }
        int width2 = this.o;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.f21516w.getWidth();
        }
        c2264w.setWidth(width2);
        c2264w.setHeight(paddingBottom);
        AbstractC2239a0.b(c2264w, true);
        c2264w.setOutsideTouchable(true);
        c2264w.setTouchInterceptor(this.f21519z);
        if (this.f21513t) {
            c2264w.setOverlapAnchor(this.f21512s);
        }
        AbstractC2239a0.a(c2264w, this.f21503E);
        c2264w.showAsDropDown(this.f21516w, this.f21509p, this.f21510q, this.f21514u);
        this.f21508n.setSelection(-1);
        if ((!this.f21504F || this.f21508n.isInTouchMode()) && (i0Var = this.f21508n) != null) {
            i0Var.setListSelectionHidden(true);
            i0Var.requestLayout();
        }
        if (this.f21504F) {
            return;
        }
        this.f21501C.post(this.B);
    }

    @Override // q.InterfaceC2159p
    public final void dismiss() {
        C2264w c2264w = this.f21505G;
        c2264w.dismiss();
        c2264w.setContentView(null);
        this.f21508n = null;
        this.f21501C.removeCallbacks(this.f21518y);
    }

    @Override // q.InterfaceC2159p
    public final ListView e() {
        return this.f21508n;
    }

    @Override // q.InterfaceC2159p
    public final boolean j() {
        return this.f21505G.isShowing();
    }

    @Override // r.f0
    public final void r(MenuC2151h menuC2151h, MenuItemC2152i menuItemC2152i) {
        C1764u c1764u = this.f21506H;
        if (c1764u != null) {
            c1764u.r(menuC2151h, menuItemC2152i);
        }
    }

    @Override // r.f0
    public final void y(MenuC2151h menuC2151h, MenuItemC2152i menuItemC2152i) {
        C1764u c1764u = this.f21506H;
        if (c1764u != null) {
            c1764u.y(menuC2151h, menuItemC2152i);
        }
    }
}
