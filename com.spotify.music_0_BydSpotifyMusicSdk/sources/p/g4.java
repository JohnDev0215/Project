package p;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class g4 implements oj1 {
    public c4 A;
    public c4 B;
    public yf1 C;
    public d4 D;
    public final Context c;
    public Context e;
    public si1 k;
    public final LayoutInflater l;
    public nj1 m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public qj1 f122p;
    public f4 q;
    public Drawable r;
    public boolean s;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public int x;
    public boolean y;
    public final int n = R.layout.abc_action_menu_layout;
    public final int o = R.layout.abc_action_menu_item_layout;
    public final SparseBooleanArray z = new SparseBooleanArray();
    public final p01 E = new p01(2, this);

    public g4(Context context) {
        this.c = context;
        this.l = LayoutInflater.from(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View a(xi1 xi1Var, View view, ViewGroup viewGroup) {
        View actionView = xi1Var.getActionView();
        if (actionView == null || xi1Var.e()) {
            pj1 pj1Var = view instanceof pj1 ? (pj1) view : (pj1) this.l.inflate(this.o, viewGroup, false);
            pj1Var.c(xi1Var);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) pj1Var;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.f122p);
            if (this.D == null) {
                this.D = new d4(this);
            }
            actionMenuItemView.setPopupCallback(this.D);
            actionView = (View) pj1Var;
        }
        actionView.setVisibility(xi1Var.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!((ActionMenuView) viewGroup).checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(ActionMenuView.m(layoutParams));
        }
        return actionView;
    }

    @Override // p.oj1
    public final void b(si1 si1Var, boolean z) {
        c();
        c4 c4Var = this.B;
        if (c4Var != null && c4Var.b()) {
            c4Var.j.dismiss();
        }
        nj1 nj1Var = this.m;
        if (nj1Var != null) {
            nj1Var.b(si1Var, z);
        }
    }

    public final boolean c() {
        Object obj;
        yf1 yf1Var = this.C;
        if (yf1Var != null && (obj = this.f122p) != null) {
            ((View) obj).removeCallbacks(yf1Var);
            this.C = null;
            return true;
        }
        c4 c4Var = this.A;
        if (c4Var == null) {
            return false;
        }
        if (c4Var.b()) {
            c4Var.j.dismiss();
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.oj1
    public final void d() {
        int size;
        int i;
        ViewGroup viewGroup = (ViewGroup) this.f122p;
        ArrayList arrayList = null;
        if (viewGroup != null) {
            si1 si1Var = this.k;
            if (si1Var != null) {
                si1Var.i();
                ArrayList arrayListL = this.k.l();
                int size2 = arrayListL.size();
                i = 0;
                for (int i2 = 0; i2 < size2; i2++) {
                    xi1 xi1Var = (xi1) arrayListL.get(i2);
                    if (xi1Var.f()) {
                        View childAt = viewGroup.getChildAt(i);
                        xi1 itemData = childAt instanceof pj1 ? ((pj1) childAt).getItemData() : null;
                        View viewA = a(xi1Var, childAt, viewGroup);
                        if (xi1Var != itemData) {
                            viewA.setPressed(false);
                            viewA.jumpDrawablesToCurrentState();
                        }
                        if (viewA != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) viewA.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(viewA);
                            }
                            ((ViewGroup) this.f122p).addView(viewA, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i) == this.q) {
                    i++;
                } else {
                    viewGroup.removeViewAt(i);
                }
            }
        }
        ((View) this.f122p).requestLayout();
        si1 si1Var2 = this.k;
        if (si1Var2 != null) {
            si1Var2.i();
            ArrayList arrayList2 = si1Var2.i;
            int size3 = arrayList2.size();
            for (int i3 = 0; i3 < size3; i3++) {
                yi1 yi1Var = ((xi1) arrayList2.get(i3)).A;
            }
        }
        si1 si1Var3 = this.k;
        if (si1Var3 != null) {
            si1Var3.i();
            arrayList = si1Var3.j;
        }
        if (!this.t || arrayList == null || ((size = arrayList.size()) != 1 ? size <= 0 : !(!((xi1) arrayList.get(0)).C))) {
            f4 f4Var = this.q;
            if (f4Var != null) {
                Object parent = f4Var.getParent();
                Object obj = this.f122p;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.q);
                }
            }
        } else {
            if (this.q == null) {
                this.q = new f4(this, this.c);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.q.getParent();
            if (viewGroup3 != this.f122p) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.q);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f122p;
                f4 f4Var2 = this.q;
                actionMenuView.getClass();
                i4 i4VarL = ActionMenuView.l();
                i4VarL.a = true;
                actionMenuView.addView(f4Var2, i4VarL);
            }
        }
        ((ActionMenuView) this.f122p).setOverflowReserved(this.t);
    }

    public final boolean e() {
        c4 c4Var = this.A;
        return c4Var != null && c4Var.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p.oj1
    public final boolean f(qg2 qg2Var) {
        boolean z;
        if (!qg2Var.hasVisibleItems()) {
            return false;
        }
        qg2 qg2Var2 = qg2Var;
        while (true) {
            si1 si1Var = qg2Var2.z;
            if (si1Var == this.k) {
                break;
            }
            qg2Var2 = (qg2) si1Var;
        }
        ViewGroup viewGroup = (ViewGroup) this.f122p;
        View view = null;
        view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof pj1) && ((pj1) childAt).getItemData() == qg2Var2.A) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        qg2Var.A.getClass();
        int size = qg2Var.f.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                z = false;
                break;
            }
            MenuItem item = qg2Var.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        c4 c4Var = new c4(this, this.e, qg2Var, view);
        this.B = c4Var;
        c4Var.h = z;
        ej1 ej1Var = c4Var.j;
        if (ej1Var != null) {
            ej1Var.o(z);
        }
        c4 c4Var2 = this.B;
        if (!c4Var2.b()) {
            if (c4Var2.f == null) {
                throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
            }
            c4Var2.d(0, 0, false, false);
        }
        nj1 nj1Var = this.m;
        if (nj1Var != null) {
            nj1Var.s(qg2Var);
        }
        return true;
    }

    @Override // p.oj1
    public final void g(Context context, si1 si1Var) {
        this.e = context;
        LayoutInflater.from(context);
        this.k = si1Var;
        Resources resources = context.getResources();
        if (!this.u) {
            this.t = true;
        }
        int i = 2;
        this.v = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i3 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i2 > 600 || ((i2 > 960 && i3 > 720) || (i2 > 720 && i3 > 960))) {
            i = 5;
        } else if (i2 >= 500 || ((i2 > 640 && i3 > 480) || (i2 > 480 && i3 > 640))) {
            i = 4;
        } else if (i2 >= 360) {
            i = 3;
        }
        this.x = i;
        int measuredWidth = this.v;
        if (this.t) {
            if (this.q == null) {
                f4 f4Var = new f4(this, this.c);
                this.q = f4Var;
                if (this.s) {
                    f4Var.setImageDrawable(this.r);
                    this.r = null;
                    this.s = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.q.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.q.getMeasuredWidth();
        } else {
            this.q = null;
        }
        this.w = measuredWidth;
        float f = resources.getDisplayMetrics().density;
    }

    @Override // p.oj1
    public final /* bridge */ /* synthetic */ boolean h(xi1 xi1Var) {
        return false;
    }

    @Override // p.oj1
    public final boolean i() {
        int size;
        ArrayList arrayListL;
        int i;
        boolean z;
        si1 si1Var = this.k;
        if (si1Var != null) {
            arrayListL = si1Var.l();
            size = arrayListL.size();
        } else {
            size = 0;
            arrayListL = null;
        }
        int i2 = this.x;
        int i3 = this.w;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.f122p;
        int i4 = 0;
        boolean z2 = false;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            i = 2;
            z = true;
            if (i4 >= size) {
                break;
            }
            xi1 xi1Var = (xi1) arrayListL.get(i4);
            int i7 = xi1Var.y;
            if ((i7 & 2) == 2) {
                i5++;
            } else if ((i7 & 1) == 1) {
                i6++;
            } else {
                z2 = true;
            }
            if (this.y && xi1Var.C) {
                i2 = 0;
            }
            i4++;
        }
        if (this.t && (z2 || i6 + i5 > i2)) {
            i2--;
        }
        int i8 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = this.z;
        sparseBooleanArray.clear();
        int i9 = 0;
        int i10 = 0;
        while (i9 < size) {
            xi1 xi1Var2 = (xi1) arrayListL.get(i9);
            int i11 = xi1Var2.y;
            boolean z3 = (i11 & 2) == i ? z : false;
            int i12 = xi1Var2.b;
            if (z3) {
                View viewA = a(xi1Var2, null, viewGroup);
                viewA.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewA.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i10 == 0) {
                    i10 = measuredWidth;
                }
                if (i12 != 0) {
                    sparseBooleanArray.put(i12, z);
                }
                xi1Var2.g(z);
            } else if ((i11 & 1) == z) {
                boolean z4 = sparseBooleanArray.get(i12);
                boolean z5 = ((i8 > 0 || z4) && i3 > 0) ? z : false;
                if (z5) {
                    View viewA2 = a(xi1Var2, null, viewGroup);
                    viewA2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewA2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i10 == 0) {
                        i10 = measuredWidth2;
                    }
                    z5 &= i3 + i10 > 0;
                }
                if (z5 && i12 != 0) {
                    sparseBooleanArray.put(i12, true);
                } else if (z4) {
                    sparseBooleanArray.put(i12, false);
                    for (int i13 = 0; i13 < i9; i13++) {
                        xi1 xi1Var3 = (xi1) arrayListL.get(i13);
                        if (xi1Var3.b == i12) {
                            if (xi1Var3.f()) {
                                i8++;
                            }
                            xi1Var3.g(false);
                        }
                    }
                }
                if (z5) {
                    i8--;
                }
                xi1Var2.g(z5);
            } else {
                xi1Var2.g(false);
                i9++;
                i = 2;
                z = true;
            }
            i9++;
            i = 2;
            z = true;
        }
        return z;
    }

    @Override // p.oj1
    public final void j(nj1 nj1Var) {
        this.m = nj1Var;
    }

    @Override // p.oj1
    public final /* bridge */ /* synthetic */ boolean k(xi1 xi1Var) {
        return false;
    }

    public final boolean l() {
        si1 si1Var;
        if (!this.t || e() || (si1Var = this.k) == null || this.f122p == null || this.C != null) {
            return false;
        }
        si1Var.i();
        if (si1Var.j.isEmpty()) {
            return false;
        }
        yf1 yf1Var = new yf1(this, 1, new c4(this, this.e, this.k, this.q));
        this.C = yf1Var;
        ((View) this.f122p).post(yf1Var);
        return true;
    }
}
