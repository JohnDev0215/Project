package a.b.p;

import a.b.o.i.g;
import a.b.o.i.m;
import a.b.o.i.n;
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
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class c extends a.b.o.i.b {
    public d i;
    public Drawable j;
    public boolean k;
    public boolean l;
    public boolean m;
    public int n;
    public int o;
    public int p;
    public boolean q;
    public int r;
    public final SparseBooleanArray s;
    public e t;
    public a u;
    public RunnableC0004c v;
    public b w;
    public final f x;
    public int y;

    public class a extends a.b.o.i.l {
        public a(Context context, a.b.o.i.r rVar, View view) {
            super(context, rVar, view, false, a.b.a.actionOverflowMenuStyle, 0);
            if (!rVar.B.g()) {
                View view2 = c.this.i;
                this.f208f = view2 == null ? (View) c.this.h : view2;
            }
            d(c.this.x);
        }

        @Override // a.b.o.i.l
        public void c() {
            c cVar = c.this;
            cVar.u = null;
            cVar.y = 0;
            super.c();
        }
    }

    public class b extends ActionMenuItemView.b {
        public b() {
        }
    }

    /* JADX INFO: renamed from: a.b.p.c$c, reason: collision with other inner class name */
    public class RunnableC0004c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public e f238a;

        public RunnableC0004c(e eVar) {
            this.f238a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.a aVar;
            a.b.o.i.g gVar = c.this.f140c;
            if (gVar != null && (aVar = gVar.f180e) != null) {
                aVar.a(gVar);
            }
            View view = (View) c.this.h;
            if (view != null && view.getWindowToken() != null && this.f238a.f()) {
                c.this.t = this.f238a;
            }
            c.this.v = null;
        }
    }

    public class d extends n implements ActionMenuView.a {

        public class a extends h0 {
            public a(View view, c cVar) {
                super(view);
            }

            @Override // a.b.p.h0
            public a.b.o.i.p b() {
                e eVar = c.this.t;
                if (eVar == null) {
                    return null;
                }
                return eVar.a();
            }

            @Override // a.b.p.h0
            public boolean c() {
                c.this.n();
                return true;
            }

            @Override // a.b.p.h0
            public boolean d() {
                c cVar = c.this;
                if (cVar.v != null) {
                    return false;
                }
                cVar.i();
                return true;
            }
        }

        public d(Context context) {
            super(context, null, a.b.a.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            setTooltipText(getContentDescription());
            setOnTouchListener(new a(this, c.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            c.this.n();
            return true;
        }

        @Override // android.widget.ImageView
        public boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                background.setHotspotBounds(paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    public class e extends a.b.o.i.l {
        public e(Context context, a.b.o.i.g gVar, View view, boolean z) {
            super(context, gVar, view, z, a.b.a.actionOverflowMenuStyle, 0);
            this.g = 8388613;
            d(c.this.x);
        }

        @Override // a.b.o.i.l
        public void c() {
            a.b.o.i.g gVar = c.this.f140c;
            if (gVar != null) {
                gVar.c(true);
            }
            c.this.t = null;
            super.c();
        }
    }

    public class f implements m.a {
        public f() {
        }

        @Override // a.b.o.i.m.a
        public void b(a.b.o.i.g gVar, boolean z) {
            if (gVar instanceof a.b.o.i.r) {
                gVar.k().c(false);
            }
            m.a aVar = c.this.f142e;
            if (aVar != null) {
                aVar.b(gVar, z);
            }
        }

        @Override // a.b.o.i.m.a
        public boolean c(a.b.o.i.g gVar) {
            c cVar = c.this;
            if (gVar == cVar.f140c) {
                return false;
            }
            int i = ((a.b.o.i.r) gVar).B.f185a;
            m.a aVar = cVar.f142e;
            if (aVar != null) {
                return aVar.c(gVar);
            }
            return false;
        }
    }

    public c(Context context) {
        super(context, a.b.g.abc_action_menu_layout, a.b.g.abc_action_menu_item_layout);
        this.s = new SparseBooleanArray();
        this.x = new f();
    }

    @Override // a.b.o.i.b
    public View a(a.b.o.i.i iVar, View view, ViewGroup viewGroup) {
        View actionView = iVar.getActionView();
        if (actionView == null || iVar.f()) {
            actionView = super.a(iVar, view, viewGroup);
        }
        actionView.setVisibility(iVar.C ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // a.b.o.i.m
    public void b(a.b.o.i.g gVar, boolean z) {
        e();
        m.a aVar = this.f142e;
        if (aVar != null) {
            aVar.b(gVar, z);
        }
    }

    public boolean e() {
        return l() | i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // a.b.o.i.b, a.b.o.i.m
    public boolean f(a.b.o.i.r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        a.b.o.i.r rVar2 = rVar;
        while (true) {
            a.b.o.i.g gVar = rVar2.A;
            if (gVar == this.f140c) {
                break;
            }
            rVar2 = (a.b.o.i.r) gVar;
        }
        a.b.o.i.i iVar = rVar2.B;
        ViewGroup viewGroup = (ViewGroup) this.h;
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
                if ((childAt instanceof n.a) && ((n.a) childAt).getItemData() == iVar) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        int i2 = rVar.B.f185a;
        int size = rVar.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            MenuItem item = rVar.getItem(i3);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i3++;
        }
        a aVar = new a(this.f139b, rVar, view);
        this.u = aVar;
        aVar.h = z;
        a.b.o.i.k kVar = aVar.j;
        if (kVar != null) {
            kVar.p(z);
        }
        if (!this.u.f()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
        super.f(rVar);
        return true;
    }

    @Override // a.b.o.i.m
    public boolean g() {
        ArrayList<a.b.o.i.i> arrayListL;
        int size;
        boolean z;
        boolean z2;
        a.b.o.i.g gVar = this.f140c;
        View view = null;
        boolean z3 = false;
        if (gVar != null) {
            arrayListL = gVar.l();
            size = arrayListL.size();
        } else {
            arrayListL = null;
            size = 0;
        }
        int i = this.p;
        int i2 = this.o;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.h;
        int i3 = 0;
        boolean z4 = false;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            z = true;
            if (i3 >= size) {
                break;
            }
            a.b.o.i.i iVar = arrayListL.get(i3);
            if (iVar.requiresActionButton()) {
                i4++;
            } else if (iVar.i()) {
                i5++;
            } else {
                z4 = true;
            }
            if (this.q && iVar.C) {
                i = 0;
            }
            i3++;
        }
        if (this.l && (z4 || i5 + i4 > i)) {
            i--;
        }
        int i6 = i - i4;
        SparseBooleanArray sparseBooleanArray = this.s;
        sparseBooleanArray.clear();
        int i7 = 0;
        int i8 = 0;
        while (i7 < size) {
            a.b.o.i.i iVar2 = arrayListL.get(i7);
            if (iVar2.requiresActionButton()) {
                View viewA = a(iVar2, view, viewGroup);
                viewA.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewA.getMeasuredWidth();
                i2 -= measuredWidth;
                if (i8 == 0) {
                    i8 = measuredWidth;
                }
                int i9 = iVar2.f186b;
                if (i9 != 0) {
                    sparseBooleanArray.put(i9, z);
                }
                iVar2.l(z);
                z2 = z3;
            } else if (iVar2.i()) {
                int i10 = iVar2.f186b;
                boolean z5 = sparseBooleanArray.get(i10);
                boolean z6 = ((i6 > 0 || z5) && i2 > 0) ? z : z3;
                if (z6) {
                    View viewA2 = a(iVar2, view, viewGroup);
                    viewA2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewA2.getMeasuredWidth();
                    i2 -= measuredWidth2;
                    if (i8 == 0) {
                        i8 = measuredWidth2;
                    }
                    z6 &= i2 + i8 > 0 ? z : false;
                }
                boolean z7 = z6;
                if (z7 && i10 != 0) {
                    sparseBooleanArray.put(i10, z);
                } else if (z5) {
                    sparseBooleanArray.put(i10, false);
                    for (int i11 = 0; i11 < i7; i11++) {
                        a.b.o.i.i iVar3 = arrayListL.get(i11);
                        if (iVar3.f186b == i10) {
                            if (iVar3.g()) {
                                i6++;
                            }
                            iVar3.l(false);
                        }
                    }
                }
                if (z7) {
                    i6--;
                }
                iVar2.l(z7);
                z2 = false;
            } else {
                z2 = z3;
                iVar2.l(z2);
            }
            i7++;
            z3 = z2;
            view = null;
            z = true;
        }
        return z;
    }

    @Override // a.b.o.i.b, a.b.o.i.m
    public void h(boolean z) {
        ArrayList<a.b.o.i.i> arrayList;
        super.h(z);
        ((View) this.h).requestLayout();
        a.b.o.i.g gVar = this.f140c;
        boolean z2 = false;
        if (gVar != null) {
            gVar.i();
            ArrayList<a.b.o.i.i> arrayList2 = gVar.i;
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                a.h.k.b bVar = arrayList2.get(i).A;
            }
        }
        a.b.o.i.g gVar2 = this.f140c;
        if (gVar2 != null) {
            gVar2.i();
            arrayList = gVar2.j;
        } else {
            arrayList = null;
        }
        if (this.l && arrayList != null) {
            int size2 = arrayList.size();
            if (size2 == 1) {
                z2 = !arrayList.get(0).C;
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        d dVar = this.i;
        if (z2) {
            if (dVar == null) {
                this.i = new d(this.f138a);
            }
            ViewGroup viewGroup = (ViewGroup) this.i.getParent();
            if (viewGroup != this.h) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.i);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.h;
                d dVar2 = this.i;
                ActionMenuView.c cVarGenerateDefaultLayoutParams = actionMenuView.generateDefaultLayoutParams();
                cVarGenerateDefaultLayoutParams.f1428c = true;
                actionMenuView.addView(dVar2, cVarGenerateDefaultLayoutParams);
            }
        } else if (dVar != null) {
            Object parent = dVar.getParent();
            Object obj = this.h;
            if (parent == obj) {
                ((ViewGroup) obj).removeView(this.i);
            }
        }
        ((ActionMenuView) this.h).setOverflowReserved(this.l);
    }

    public boolean i() {
        Object obj;
        RunnableC0004c runnableC0004c = this.v;
        if (runnableC0004c != null && (obj = this.h) != null) {
            ((View) obj).removeCallbacks(runnableC0004c);
            this.v = null;
            return true;
        }
        e eVar = this.t;
        if (eVar == null) {
            return false;
        }
        if (eVar.b()) {
            eVar.j.dismiss();
        }
        return true;
    }

    @Override // a.b.o.i.m
    public void j(Context context, a.b.o.i.g gVar) {
        this.f139b = context;
        LayoutInflater.from(context);
        this.f140c = gVar;
        Resources resources = context.getResources();
        if (!this.m) {
            this.l = true;
        }
        int i = 2;
        this.n = context.getResources().getDisplayMetrics().widthPixels / 2;
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
        this.p = i;
        int measuredWidth = this.n;
        if (this.l) {
            if (this.i == null) {
                d dVar = new d(this.f138a);
                this.i = dVar;
                if (this.k) {
                    dVar.setImageDrawable(this.j);
                    this.j = null;
                    this.k = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.o = measuredWidth;
        this.r = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    public boolean l() {
        a aVar = this.u;
        if (aVar == null) {
            return false;
        }
        if (!aVar.b()) {
            return true;
        }
        aVar.j.dismiss();
        return true;
    }

    public boolean m() {
        e eVar = this.t;
        return eVar != null && eVar.b();
    }

    public boolean n() {
        a.b.o.i.g gVar;
        if (!this.l || m() || (gVar = this.f140c) == null || this.h == null || this.v != null) {
            return false;
        }
        gVar.i();
        if (gVar.j.isEmpty()) {
            return false;
        }
        RunnableC0004c runnableC0004c = new RunnableC0004c(new e(this.f139b, this.f140c, this.i, true));
        this.v = runnableC0004c;
        ((View) this.h).post(runnableC0004c);
        return true;
    }
}
