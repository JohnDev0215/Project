package androidx.appcompat.view.menu;

import a.g.k.t;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
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
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.j0;
import androidx.appcompat.widget.k0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f792c = a.a.g.e;
    ViewTreeObserver A;
    private PopupWindow.OnDismissListener B;
    boolean C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f793d;
    private final int e;
    private final int f;
    private final int g;
    private final boolean h;
    final Handler i;
    private View q;
    View r;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean y;
    private m.a z;
    private final List<g> j = new ArrayList();
    final List<C0031d> k = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    private final View.OnAttachStateChangeListener m = new b();
    private final j0 n = new c();
    private int o = 0;
    private int p = 0;
    private boolean x = false;
    private int s = D();

    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.b() || d.this.k.size() <= 0 || d.this.k.get(0).f800a.B()) {
                return;
            }
            View view = d.this.r;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0031d> it = d.this.k.iterator();
            while (it.hasNext()) {
                it.next().f800a.g();
            }
        }
    }

    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = d.this.A;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.A = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.A.removeGlobalOnLayoutListener(dVar.l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    class c implements j0 {

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ C0031d f797b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ MenuItem f798c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ g f799d;

            a(C0031d c0031d, MenuItem menuItem, g gVar) {
                this.f797b = c0031d;
                this.f798c = menuItem;
                this.f799d = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0031d c0031d = this.f797b;
                if (c0031d != null) {
                    d.this.C = true;
                    c0031d.f801b.e(false);
                    d.this.C = false;
                }
                if (this.f798c.isEnabled() && this.f798c.hasSubMenu()) {
                    this.f799d.L(this.f798c, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.j0
        public void a(g gVar, MenuItem menuItem) {
            d.this.i.removeCallbacksAndMessages(null);
            int size = d.this.k.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (gVar == d.this.k.get(i).f801b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            d.this.i.postAtTime(new a(i2 < d.this.k.size() ? d.this.k.get(i2) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.j0
        public void i(g gVar, MenuItem menuItem) {
            d.this.i.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.view.menu.d$d, reason: collision with other inner class name */
    private static class C0031d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k0 f800a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f801b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f802c;

        public C0031d(k0 k0Var, g gVar, int i) {
            this.f800a = k0Var;
            this.f801b = gVar;
            this.f802c = i;
        }

        public ListView a() {
            return this.f800a.l();
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.f793d = context;
        this.q = view;
        this.f = i;
        this.g = i2;
        this.h = z;
        Resources resources = context.getResources();
        this.e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.a.d.f12d));
        this.i = new Handler();
    }

    private int A(g gVar) {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (gVar == this.k.get(i).f801b) {
                return i;
            }
        }
        return -1;
    }

    private MenuItem B(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View C(C0031d c0031d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemB = B(c0031d.f801b, gVar);
        if (menuItemB == null) {
            return null;
        }
        ListView listViewA = c0031d.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            headersCount = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            }
            if (menuItemB == fVar.getItem(i)) {
                break;
            }
            i++;
        }
        if (i != -1 && (firstVisiblePosition = (i + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int D() {
        return t.y(this.q) == 1 ? 0 : 1;
    }

    private int E(int i) {
        List<C0031d> list = this.k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.r.getWindowVisibleDisplayFrame(rect);
        return this.s == 1 ? (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    private void F(g gVar) {
        C0031d c0031d;
        View viewC;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f793d);
        f fVar = new f(gVar, layoutInflaterFrom, this.h, f792c);
        if (!b() && this.x) {
            fVar.d(true);
        } else if (b()) {
            fVar.d(k.x(gVar));
        }
        int iO = k.o(fVar, null, this.f793d, this.e);
        k0 k0VarZ = z();
        k0VarZ.o(fVar);
        k0VarZ.F(iO);
        k0VarZ.G(this.p);
        if (this.k.size() > 0) {
            List<C0031d> list = this.k;
            c0031d = list.get(list.size() - 1);
            viewC = C(c0031d, gVar);
        } else {
            c0031d = null;
            viewC = null;
        }
        if (viewC != null) {
            k0VarZ.U(false);
            k0VarZ.R(null);
            int iE = E(iO);
            boolean z = iE == 1;
            this.s = iE;
            if (Build.VERSION.SDK_INT >= 26) {
                k0VarZ.D(viewC);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.q.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewC.getLocationOnScreen(iArr2);
                if ((this.p & 7) == 5) {
                    iArr[0] = iArr[0] + this.q.getWidth();
                    iArr2[0] = iArr2[0] + viewC.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.p & 5) == 5) {
                if (!z) {
                    iO = viewC.getWidth();
                    i3 = i - iO;
                }
                i3 = i + iO;
            } else {
                if (z) {
                    iO = viewC.getWidth();
                    i3 = i + iO;
                }
                i3 = i - iO;
            }
            k0VarZ.d(i3);
            k0VarZ.M(true);
            k0VarZ.n(i2);
        } else {
            if (this.t) {
                k0VarZ.d(this.v);
            }
            if (this.u) {
                k0VarZ.n(this.w);
            }
            k0VarZ.H(n());
        }
        this.k.add(new C0031d(k0VarZ, gVar, this.s));
        k0VarZ.g();
        ListView listViewL = k0VarZ.l();
        listViewL.setOnKeyListener(this);
        if (c0031d == null && this.y && gVar.x() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(a.a.g.l, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.x());
            listViewL.addHeaderView(frameLayout, null, false);
            k0VarZ.g();
        }
    }

    private k0 z() {
        k0 k0Var = new k0(this.f793d, null, this.f, this.g);
        k0Var.T(this.n);
        k0Var.L(this);
        k0Var.K(this);
        k0Var.D(this.q);
        k0Var.G(this.p);
        k0Var.J(true);
        k0Var.I(2);
        return k0Var;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        int iA = A(gVar);
        if (iA < 0) {
            return;
        }
        int i = iA + 1;
        if (i < this.k.size()) {
            this.k.get(i).f801b.e(false);
        }
        C0031d c0031dRemove = this.k.remove(iA);
        c0031dRemove.f801b.O(this);
        if (this.C) {
            c0031dRemove.f800a.S(null);
            c0031dRemove.f800a.E(0);
        }
        c0031dRemove.f800a.dismiss();
        int size = this.k.size();
        this.s = size > 0 ? this.k.get(size - 1).f802c : D();
        if (size != 0) {
            if (z) {
                this.k.get(0).f801b.e(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.z;
        if (aVar != null) {
            aVar.a(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.A;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.A.removeGlobalOnLayoutListener(this.l);
            }
            this.A = null;
        }
        this.r.removeOnAttachStateChangeListener(this.m);
        this.B.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean b() {
        return this.k.size() > 0 && this.k.get(0).f800a.b();
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.k.size();
        if (size > 0) {
            C0031d[] c0031dArr = (C0031d[]) this.k.toArray(new C0031d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0031d c0031d = c0031dArr[i];
                if (c0031d.f800a.b()) {
                    c0031d.f800a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public void g() {
        if (b()) {
            return;
        }
        Iterator<g> it = this.j.iterator();
        while (it.hasNext()) {
            F(it.next());
        }
        this.j.clear();
        View view = this.q;
        this.r = view;
        if (view != null) {
            boolean z = this.A == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.A = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.l);
            }
            this.r.addOnAttachStateChangeListener(this.m);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void h(m.a aVar) {
        this.z = aVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean i(r rVar) {
        for (C0031d c0031d : this.k) {
            if (rVar == c0031d.f801b) {
                c0031d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        k(rVar);
        m.a aVar = this.z;
        if (aVar != null) {
            aVar.b(rVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void j(boolean z) {
        Iterator<C0031d> it = this.k.iterator();
        while (it.hasNext()) {
            k.y(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void k(g gVar) {
        gVar.c(this, this.f793d);
        if (b()) {
            F(gVar);
        } else {
            this.j.add(gVar);
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView l() {
        if (this.k.isEmpty()) {
            return null;
        }
        return this.k.get(r0.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.k
    protected boolean m() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0031d c0031d;
        int size = this.k.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                c0031d = null;
                break;
            }
            c0031d = this.k.get(i);
            if (!c0031d.f800a.b()) {
                break;
            } else {
                i++;
            }
        }
        if (c0031d != null) {
            c0031d.f801b.e(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.k
    public void p(View view) {
        if (this.q != view) {
            this.q = view;
            this.p = a.g.k.d.b(this.o, t.y(view));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void r(boolean z) {
        this.x = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void s(int i) {
        if (this.o != i) {
            this.o = i;
            this.p = a.g.k.d.b(i, t.y(this.q));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void t(int i) {
        this.t = true;
        this.v = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.B = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void v(boolean z) {
        this.y = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void w(int i) {
        this.u = true;
        this.w = i;
    }
}
