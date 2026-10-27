package r;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import l3.C1764u;
import q.AbstractC2153j;
import q.InterfaceC2156m;
import q.InterfaceC2157n;
import q.InterfaceC2158o;
import q.MenuC2151h;
import q.MenuItemC2152i;
import q.SubMenuC2161r;

/* JADX INFO: renamed from: r.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2250i implements InterfaceC2157n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f21477A;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C2247f f21478C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public C2247f f21479D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public io.sentry.android.core.M f21480E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public C2248g f21481F;
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Context f21483m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public MenuC2151h f21484n;
    public final LayoutInflater o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public InterfaceC2156m f21485p;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ActionMenuView f21487r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C2249h f21488s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Drawable f21489t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f21490u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f21491v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f21492w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f21493x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f21494y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f21495z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f21486q = R.layout.abc_action_menu_item_layout;
    public final SparseBooleanArray B = new SparseBooleanArray();

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final C1764u f21482G = new C1764u(this, 18);

    public C2250i(Context context) {
        this.l = context;
        this.o = LayoutInflater.from(context);
    }

    @Override // q.InterfaceC2157n
    public final void a(MenuC2151h menuC2151h, boolean z6) {
        e();
        C2247f c2247f = this.f21479D;
        if (c2247f != null && c2247f.b()) {
            c2247f.f21095i.dismiss();
        }
        InterfaceC2156m interfaceC2156m = this.f21485p;
        if (interfaceC2156m != null) {
            interfaceC2156m.a(menuC2151h, z6);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // q.InterfaceC2157n
    public final boolean b(SubMenuC2161r subMenuC2161r) {
        boolean z6;
        if (!subMenuC2161r.hasVisibleItems()) {
            return false;
        }
        SubMenuC2161r subMenuC2161r2 = subMenuC2161r;
        while (true) {
            MenuC2151h menuC2151h = subMenuC2161r2.f21115w;
            if (menuC2151h == this.f21484n) {
                break;
            }
            subMenuC2161r2 = (SubMenuC2161r) menuC2151h;
        }
        ActionMenuView actionMenuView = this.f21487r;
        View view = null;
        view = null;
        if (actionMenuView != null) {
            int childCount = actionMenuView.getChildCount();
            int i7 = 0;
            while (true) {
                if (i7 >= childCount) {
                    break;
                }
                View childAt = actionMenuView.getChildAt(i7);
                if ((childAt instanceof InterfaceC2158o) && ((InterfaceC2158o) childAt).getItemData() == subMenuC2161r2.f21116x) {
                    view = childAt;
                    break;
                }
                i7++;
            }
        }
        if (view == null) {
            return false;
        }
        subMenuC2161r.f21116x.getClass();
        int size = subMenuC2161r.f21052f.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                z6 = false;
                break;
            }
            MenuItem item = subMenuC2161r.getItem(i10);
            if (item.isVisible() && item.getIcon() != null) {
                z6 = true;
                break;
            }
            i10++;
        }
        C2247f c2247f = new C2247f(this, this.f21483m, subMenuC2161r, view);
        this.f21479D = c2247f;
        c2247f.f21093g = z6;
        AbstractC2153j abstractC2153j = c2247f.f21095i;
        if (abstractC2153j != null) {
            abstractC2153j.o(z6);
        }
        C2247f c2247f2 = this.f21479D;
        if (!c2247f2.b()) {
            if (c2247f2.f21091e == null) {
                throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
            }
            c2247f2.d(0, 0, false, false);
        }
        InterfaceC2156m interfaceC2156m = this.f21485p;
        if (interfaceC2156m != null) {
            interfaceC2156m.v(subMenuC2161r);
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View c(MenuItemC2152i menuItemC2152i, View view, ActionMenuView actionMenuView) {
        View view2 = menuItemC2152i.f21088z;
        if (view2 == null) {
            view2 = null;
        }
        if (view2 == null || menuItemC2152i.c()) {
            InterfaceC2158o interfaceC2158o = view instanceof InterfaceC2158o ? (InterfaceC2158o) view : (InterfaceC2158o) this.o.inflate(this.f21486q, (ViewGroup) actionMenuView, false);
            interfaceC2158o.b(menuItemC2152i);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) interfaceC2158o;
            actionMenuItemView.setItemInvoker(this.f21487r);
            if (this.f21481F == null) {
                this.f21481F = new C2248g(this);
            }
            actionMenuItemView.setPopupCallback(this.f21481F);
            view2 = (View) interfaceC2158o;
        }
        view2.setVisibility(menuItemC2152i.B ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
        actionMenuView.getClass();
        if (!(layoutParams instanceof C2252k)) {
            view2.setLayoutParams(ActionMenuView.j(layoutParams));
        }
        return view2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // q.InterfaceC2157n
    public final void d() {
        int i7;
        ActionMenuView actionMenuView = this.f21487r;
        ArrayList arrayList = null;
        boolean z6 = false;
        if (actionMenuView != null) {
            MenuC2151h menuC2151h = this.f21484n;
            if (menuC2151h != null) {
                menuC2151h.i();
                ArrayList arrayListK = this.f21484n.k();
                int size = arrayListK.size();
                i7 = 0;
                for (int i10 = 0; i10 < size; i10++) {
                    MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayListK.get(i10);
                    if (menuItemC2152i.d()) {
                        View childAt = actionMenuView.getChildAt(i7);
                        MenuItemC2152i itemData = childAt instanceof InterfaceC2158o ? ((InterfaceC2158o) childAt).getItemData() : null;
                        View viewC = c(menuItemC2152i, childAt, actionMenuView);
                        if (menuItemC2152i != itemData) {
                            viewC.setPressed(false);
                            viewC.jumpDrawablesToCurrentState();
                        }
                        if (viewC != childAt) {
                            ViewGroup viewGroup = (ViewGroup) viewC.getParent();
                            if (viewGroup != null) {
                                viewGroup.removeView(viewC);
                            }
                            this.f21487r.addView(viewC, i7);
                        }
                        i7++;
                    }
                }
            } else {
                i7 = 0;
            }
            while (i7 < actionMenuView.getChildCount()) {
                if (actionMenuView.getChildAt(i7) == this.f21488s) {
                    i7++;
                } else {
                    actionMenuView.removeViewAt(i7);
                }
            }
        }
        this.f21487r.requestLayout();
        MenuC2151h menuC2151h2 = this.f21484n;
        if (menuC2151h2 != null) {
            menuC2151h2.i();
            ArrayList arrayList2 = menuC2151h2.f21055i;
            int size2 = arrayList2.size();
            for (int i11 = 0; i11 < size2; i11++) {
                ((MenuItemC2152i) arrayList2.get(i11)).getClass();
            }
        }
        MenuC2151h menuC2151h3 = this.f21484n;
        if (menuC2151h3 != null) {
            menuC2151h3.i();
            arrayList = menuC2151h3.f21056j;
        }
        if (this.f21491v && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z6 = !((MenuItemC2152i) arrayList.get(0)).B;
            } else if (size3 > 0) {
                z6 = true;
            }
        }
        if (z6) {
            if (this.f21488s == null) {
                this.f21488s = new C2249h(this, this.l);
            }
            ViewGroup viewGroup2 = (ViewGroup) this.f21488s.getParent();
            if (viewGroup2 != this.f21487r) {
                if (viewGroup2 != null) {
                    viewGroup2.removeView(this.f21488s);
                }
                ActionMenuView actionMenuView2 = this.f21487r;
                C2249h c2249h = this.f21488s;
                actionMenuView2.getClass();
                C2252k c2252kI = ActionMenuView.i();
                c2252kI.f21520a = true;
                actionMenuView2.addView(c2249h, c2252kI);
            }
        } else {
            C2249h c2249h2 = this.f21488s;
            if (c2249h2 != null) {
                ViewParent parent = c2249h2.getParent();
                ActionMenuView actionMenuView3 = this.f21487r;
                if (parent == actionMenuView3) {
                    actionMenuView3.removeView(this.f21488s);
                }
            }
        }
        this.f21487r.setOverflowReserved(this.f21491v);
    }

    public final boolean e() {
        ActionMenuView actionMenuView;
        io.sentry.android.core.M m10 = this.f21480E;
        if (m10 != null && (actionMenuView = this.f21487r) != null) {
            actionMenuView.removeCallbacks(m10);
            this.f21480E = null;
            return true;
        }
        C2247f c2247f = this.f21478C;
        if (c2247f == null) {
            return false;
        }
        if (c2247f.b()) {
            c2247f.f21095i.dismiss();
        }
        return true;
    }

    @Override // q.InterfaceC2157n
    public final boolean f(MenuItemC2152i menuItemC2152i) {
        return false;
    }

    @Override // q.InterfaceC2157n
    public final boolean g() {
        int size;
        ArrayList arrayListK;
        int i7;
        boolean z6;
        MenuC2151h menuC2151h = this.f21484n;
        if (menuC2151h != null) {
            arrayListK = menuC2151h.k();
            size = arrayListK.size();
        } else {
            size = 0;
            arrayListK = null;
        }
        int i10 = this.f21495z;
        int i11 = this.f21494y;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ActionMenuView actionMenuView = this.f21487r;
        int i12 = 0;
        boolean z10 = false;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            i7 = 2;
            z6 = true;
            if (i12 >= size) {
                break;
            }
            MenuItemC2152i menuItemC2152i = (MenuItemC2152i) arrayListK.get(i12);
            int i15 = menuItemC2152i.f21087y;
            if ((i15 & 2) == 2) {
                i13++;
            } else if ((i15 & 1) == 1) {
                i14++;
            } else {
                z10 = true;
            }
            if (this.f21477A && menuItemC2152i.B) {
                i10 = 0;
            }
            i12++;
        }
        if (this.f21491v && (z10 || i14 + i13 > i10)) {
            i10--;
        }
        int i16 = i10 - i13;
        SparseBooleanArray sparseBooleanArray = this.B;
        sparseBooleanArray.clear();
        int i17 = 0;
        int i18 = 0;
        while (i17 < size) {
            MenuItemC2152i menuItemC2152i2 = (MenuItemC2152i) arrayListK.get(i17);
            int i19 = menuItemC2152i2.f21087y;
            boolean z11 = (i19 & 2) == i7 ? z6 : false;
            int i20 = menuItemC2152i2.f21068b;
            if (z11) {
                View viewC = c(menuItemC2152i2, null, actionMenuView);
                viewC.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewC.getMeasuredWidth();
                i11 -= measuredWidth;
                if (i18 == 0) {
                    i18 = measuredWidth;
                }
                if (i20 != 0) {
                    sparseBooleanArray.put(i20, z6);
                }
                menuItemC2152i2.f(z6);
            } else if ((i19 & 1) == z6) {
                boolean z12 = sparseBooleanArray.get(i20);
                boolean z13 = ((i16 > 0 || z12) && i11 > 0) ? z6 : false;
                if (z13) {
                    View viewC2 = c(menuItemC2152i2, null, actionMenuView);
                    viewC2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewC2.getMeasuredWidth();
                    i11 -= measuredWidth2;
                    if (i18 == 0) {
                        i18 = measuredWidth2;
                    }
                    z13 &= i11 + i18 > 0;
                }
                if (z13 && i20 != 0) {
                    sparseBooleanArray.put(i20, true);
                } else if (z12) {
                    sparseBooleanArray.put(i20, false);
                    for (int i21 = 0; i21 < i17; i21++) {
                        MenuItemC2152i menuItemC2152i3 = (MenuItemC2152i) arrayListK.get(i21);
                        if (menuItemC2152i3.f21068b == i20) {
                            if (menuItemC2152i3.d()) {
                                i16++;
                            }
                            menuItemC2152i3.f(false);
                        }
                    }
                }
                if (z13) {
                    i16--;
                }
                menuItemC2152i2.f(z13);
            } else {
                menuItemC2152i2.f(false);
                i17++;
                i7 = 2;
                z6 = true;
            }
            i17++;
            i7 = 2;
            z6 = true;
        }
        return z6;
    }

    @Override // q.InterfaceC2157n
    public final void h(Context context, MenuC2151h menuC2151h) {
        this.f21483m = context;
        LayoutInflater.from(context);
        this.f21484n = menuC2151h;
        Resources resources = context.getResources();
        if (!this.f21492w) {
            this.f21491v = true;
        }
        int i7 = 2;
        this.f21493x = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i10 = configuration.screenWidthDp;
        int i11 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i10 > 600 || ((i10 > 960 && i11 > 720) || (i10 > 720 && i11 > 960))) {
            i7 = 5;
        } else if (i10 >= 500 || ((i10 > 640 && i11 > 480) || (i10 > 480 && i11 > 640))) {
            i7 = 4;
        } else if (i10 >= 360) {
            i7 = 3;
        }
        this.f21495z = i7;
        int measuredWidth = this.f21493x;
        if (this.f21491v) {
            if (this.f21488s == null) {
                C2249h c2249h = new C2249h(this, this.l);
                this.f21488s = c2249h;
                if (this.f21490u) {
                    c2249h.setImageDrawable(this.f21489t);
                    this.f21489t = null;
                    this.f21490u = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f21488s.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f21488s.getMeasuredWidth();
        } else {
            this.f21488s = null;
        }
        this.f21494y = measuredWidth;
        float f10 = resources.getDisplayMetrics().density;
    }

    @Override // q.InterfaceC2157n
    public final void i(InterfaceC2156m interfaceC2156m) {
        throw null;
    }

    public final boolean j() {
        MenuC2151h menuC2151h;
        if (!this.f21491v) {
            return false;
        }
        C2247f c2247f = this.f21478C;
        if ((c2247f != null && c2247f.b()) || (menuC2151h = this.f21484n) == null || this.f21487r == null || this.f21480E != null) {
            return false;
        }
        menuC2151h.i();
        if (menuC2151h.f21056j.isEmpty()) {
            return false;
        }
        io.sentry.android.core.M m10 = new io.sentry.android.core.M(this, 3, new C2247f(this, this.f21483m, this.f21484n, this.f21488s));
        this.f21480E = m10;
        this.f21487r.post(m10);
        return true;
    }

    @Override // q.InterfaceC2157n
    public final boolean k(MenuItemC2152i menuItemC2152i) {
        return false;
    }
}
