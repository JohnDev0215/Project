package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.g0;
import androidx.appcompat.widget.h0;
import i0.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends h implements View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int G = e.g.abc_cascading_menu_item_layout;
    private boolean B;
    private j.a C;
    ViewTreeObserver D;
    private PopupWindow.OnDismissListener E;
    boolean F;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Context f615g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f616h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f617i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final int f618j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final boolean f619k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    final Handler f620l;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private View f628t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    View f629u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f631w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private boolean f632x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f633y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f634z;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final List<e> f621m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    final List<d> f622n = new ArrayList();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f623o = new a();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f624p = new ViewOnAttachStateChangeListenerC0014b();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final g0 f625q = new c();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private int f626r = 0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f627s = 0;
    private boolean A = false;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f630v = D();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!b.this.b() || b.this.f622n.size() <= 0 || b.this.f622n.get(0).f642a.B()) {
                return;
            }
            View view = b.this.f629u;
            if (view == null || !view.isShown()) {
                b.this.dismiss();
                return;
            }
            Iterator<d> it = b.this.f622n.iterator();
            while (it.hasNext()) {
                it.next().f642a.g();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.view.menu.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class ViewOnAttachStateChangeListenerC0014b implements View.OnAttachStateChangeListener {
        ViewOnAttachStateChangeListenerC0014b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = b.this.D;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    b.this.D = view.getViewTreeObserver();
                }
                b bVar = b.this;
                bVar.D.removeGlobalOnLayoutListener(bVar.f623o);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class c implements g0 {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            final /* synthetic */ d f638e;

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            final /* synthetic */ MenuItem f639g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            final /* synthetic */ e f640h;

            a(d dVar, MenuItem menuItem, e eVar) {
                this.f638e = dVar;
                this.f639g = menuItem;
                this.f640h = eVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.f638e;
                if (dVar != null) {
                    b.this.F = true;
                    dVar.f643b.e(false);
                    b.this.F = false;
                }
                if (this.f639g.isEnabled() && this.f639g.hasSubMenu()) {
                    this.f640h.L(this.f639g, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.g0
        public void a(e eVar, MenuItem menuItem) {
            b.this.f620l.removeCallbacksAndMessages(null);
            int size = b.this.f622n.size();
            int i7 = 0;
            while (true) {
                if (i7 >= size) {
                    i7 = -1;
                    break;
                } else if (eVar == b.this.f622n.get(i7).f643b) {
                    break;
                } else {
                    i7++;
                }
            }
            if (i7 == -1) {
                return;
            }
            int i8 = i7 + 1;
            b.this.f620l.postAtTime(new a(i8 < b.this.f622n.size() ? b.this.f622n.get(i8) : null, menuItem, eVar), eVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.g0
        public void i(e eVar, MenuItem menuItem) {
            b.this.f620l.removeCallbacksAndMessages(eVar);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final h0 f642a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f643b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f644c;

        public d(h0 h0Var, e eVar, int i7) {
            this.f642a = h0Var;
            this.f643b = eVar;
            this.f644c = i7;
        }

        public ListView a() {
            return this.f642a.l();
        }
    }

    public b(Context context, View view, int i7, int i8, boolean z6) {
        this.f615g = context;
        this.f628t = view;
        this.f617i = i7;
        this.f618j = i8;
        this.f619k = z6;
        Resources resources = context.getResources();
        this.f616h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(e.d.abc_config_prefDialogWidth));
        this.f620l = new Handler();
    }

    private int A(e eVar) {
        int size = this.f622n.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (eVar == this.f622n.get(i7).f643b) {
                return i7;
            }
        }
        return -1;
    }

    private MenuItem B(e eVar, e eVar2) {
        int size = eVar.size();
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = eVar.getItem(i7);
            if (item.hasSubMenu() && eVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View C(d dVar, e eVar) {
        androidx.appcompat.view.menu.d dVar2;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemB = B(dVar.f643b, eVar);
        if (menuItemB == null) {
            return null;
        }
        ListView listViewA = dVar.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i7 = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            dVar2 = (androidx.appcompat.view.menu.d) headerViewListAdapter.getWrappedAdapter();
        } else {
            dVar2 = (androidx.appcompat.view.menu.d) adapter;
            headersCount = 0;
        }
        int count = dVar2.getCount();
        while (true) {
            if (i7 >= count) {
                i7 = -1;
                break;
            }
            if (menuItemB == dVar2.getItem(i7)) {
                break;
            }
            i7++;
        }
        if (i7 != -1 && (firstVisiblePosition = (i7 + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int D() {
        return u.y(this.f628t) == 1 ? 0 : 1;
    }

    private int E(int i7) {
        List<d> list = this.f622n;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f629u.getWindowVisibleDisplayFrame(rect);
        return this.f630v == 1 ? (iArr[0] + listViewA.getWidth()) + i7 > rect.right ? 0 : 1 : iArr[0] - i7 < 0 ? 1 : 0;
    }

    private void F(e eVar) {
        d dVar;
        View viewC;
        int i7;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f615g);
        androidx.appcompat.view.menu.d dVar2 = new androidx.appcompat.view.menu.d(eVar, layoutInflaterFrom, this.f619k, G);
        if (!b() && this.A) {
            dVar2.d(true);
        } else if (b()) {
            dVar2.d(h.x(eVar));
        }
        int iO = h.o(dVar2, null, this.f615g, this.f616h);
        h0 h0VarZ = z();
        h0VarZ.o(dVar2);
        h0VarZ.F(iO);
        h0VarZ.G(this.f627s);
        if (this.f622n.size() > 0) {
            List<d> list = this.f622n;
            dVar = list.get(list.size() - 1);
            viewC = C(dVar, eVar);
        } else {
            dVar = null;
            viewC = null;
        }
        if (viewC != null) {
            h0VarZ.U(false);
            h0VarZ.R(null);
            int iE = E(iO);
            boolean z6 = iE == 1;
            this.f630v = iE;
            h0VarZ.D(viewC);
            if ((this.f627s & 5) == 5) {
                if (!z6) {
                    iO = viewC.getWidth();
                    i7 = 0 - iO;
                }
                i7 = iO + 0;
            } else {
                if (z6) {
                    iO = viewC.getWidth();
                    i7 = iO + 0;
                }
                i7 = 0 - iO;
            }
            h0VarZ.d(i7);
            h0VarZ.M(true);
            h0VarZ.n(0);
        } else {
            if (this.f631w) {
                h0VarZ.d(this.f633y);
            }
            if (this.f632x) {
                h0VarZ.n(this.f634z);
            }
            h0VarZ.H(n());
        }
        this.f622n.add(new d(h0VarZ, eVar, this.f630v));
        h0VarZ.g();
        ListView listViewL = h0VarZ.l();
        listViewL.setOnKeyListener(this);
        if (dVar == null && this.B && eVar.x() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(e.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(eVar.x());
            listViewL.addHeaderView(frameLayout, null, false);
            h0VarZ.g();
        }
    }

    private h0 z() {
        h0 h0Var = new h0(this.f615g, null, this.f617i, this.f618j);
        h0Var.T(this.f625q);
        h0Var.L(this);
        h0Var.K(this);
        h0Var.D(this.f628t);
        h0Var.G(this.f627s);
        h0Var.J(true);
        h0Var.I(2);
        return h0Var;
    }

    @Override // androidx.appcompat.view.menu.j
    public void a(e eVar, boolean z6) {
        int iA = A(eVar);
        if (iA < 0) {
            return;
        }
        int i7 = iA + 1;
        if (i7 < this.f622n.size()) {
            this.f622n.get(i7).f643b.e(false);
        }
        d dVarRemove = this.f622n.remove(iA);
        dVarRemove.f643b.O(this);
        if (this.F) {
            dVarRemove.f642a.S(null);
            dVarRemove.f642a.E(0);
        }
        dVarRemove.f642a.dismiss();
        int size = this.f622n.size();
        if (size > 0) {
            this.f630v = this.f622n.get(size - 1).f644c;
        } else {
            this.f630v = D();
        }
        if (size != 0) {
            if (z6) {
                this.f622n.get(0).f643b.e(false);
                return;
            }
            return;
        }
        dismiss();
        j.a aVar = this.C;
        if (aVar != null) {
            aVar.a(eVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.D;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.D.removeGlobalOnLayoutListener(this.f623o);
            }
            this.D = null;
        }
        this.f629u.removeOnAttachStateChangeListener(this.f624p);
        this.E.onDismiss();
    }

    @Override // j.e
    public boolean b() {
        return this.f622n.size() > 0 && this.f622n.get(0).f642a.b();
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean c() {
        return false;
    }

    @Override // j.e
    public void dismiss() {
        int size = this.f622n.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.f622n.toArray(new d[size]);
            for (int i7 = size - 1; i7 >= 0; i7--) {
                d dVar = dVarArr[i7];
                if (dVar.f642a.b()) {
                    dVar.f642a.dismiss();
                }
            }
        }
    }

    @Override // j.e
    public void g() {
        if (b()) {
            return;
        }
        Iterator<e> it = this.f621m.iterator();
        while (it.hasNext()) {
            F(it.next());
        }
        this.f621m.clear();
        View view = this.f628t;
        this.f629u = view;
        if (view != null) {
            boolean z6 = this.D == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.D = viewTreeObserver;
            if (z6) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f623o);
            }
            this.f629u.addOnAttachStateChangeListener(this.f624p);
        }
    }

    @Override // androidx.appcompat.view.menu.j
    public void h(j.a aVar) {
        this.C = aVar;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean i(m mVar) {
        for (d dVar : this.f622n) {
            if (mVar == dVar.f643b) {
                dVar.a().requestFocus();
                return true;
            }
        }
        if (!mVar.hasVisibleItems()) {
            return false;
        }
        k(mVar);
        j.a aVar = this.C;
        if (aVar != null) {
            aVar.b(mVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.j
    public void j(boolean z6) {
        Iterator<d> it = this.f622n.iterator();
        while (it.hasNext()) {
            h.y(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.h
    public void k(e eVar) {
        eVar.c(this, this.f615g);
        if (b()) {
            F(eVar);
        } else {
            this.f621m.add(eVar);
        }
    }

    @Override // j.e
    public ListView l() {
        if (this.f622n.isEmpty()) {
            return null;
        }
        return this.f622n.get(r0.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.h
    protected boolean m() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        d dVar;
        int size = this.f622n.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                dVar = null;
                break;
            }
            dVar = this.f622n.get(i7);
            if (!dVar.f642a.b()) {
                break;
            } else {
                i7++;
            }
        }
        if (dVar != null) {
            dVar.f643b.e(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i7, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i7 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.h
    public void p(View view) {
        if (this.f628t != view) {
            this.f628t = view;
            this.f627s = i0.e.b(this.f626r, u.y(view));
        }
    }

    @Override // androidx.appcompat.view.menu.h
    public void r(boolean z6) {
        this.A = z6;
    }

    @Override // androidx.appcompat.view.menu.h
    public void s(int i7) {
        if (this.f626r != i7) {
            this.f626r = i7;
            this.f627s = i0.e.b(i7, u.y(this.f628t));
        }
    }

    @Override // androidx.appcompat.view.menu.h
    public void t(int i7) {
        this.f631w = true;
        this.f633y = i7;
    }

    @Override // androidx.appcompat.view.menu.h
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.E = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.h
    public void v(boolean z6) {
        this.B = z6;
    }

    @Override // androidx.appcompat.view.menu.h
    public void w(int i7) {
        this.f632x = true;
        this.f634z = i7;
    }
}
