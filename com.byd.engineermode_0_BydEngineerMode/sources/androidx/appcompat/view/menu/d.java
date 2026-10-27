package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
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
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.appcompat.widget.u;
import b.g.k.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f198c = b.a.g.g;
    ViewTreeObserver A;
    private PopupWindow.OnDismissListener B;
    boolean C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f199d;
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
    final List<C0005d> k = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    private final View.OnAttachStateChangeListener m = new b();
    private final u n = new c();
    private int o = 0;
    private int p = 0;
    private boolean x = false;
    private int s = G();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.b() || d.this.k.size() <= 0 || d.this.k.get(0).f206a.B()) {
                return;
            }
            View view = d.this.r;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0005d> it = d.this.k.iterator();
            while (it.hasNext()) {
                it.next().f206a.g();
            }
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
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

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class c implements u {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ C0005d f203b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ MenuItem f204c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ g f205d;

            a(C0005d c0005d, MenuItem menuItem, g gVar) {
                this.f203b = c0005d;
                this.f204c = menuItem;
                this.f205d = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0005d c0005d = this.f203b;
                if (c0005d != null) {
                    d.this.C = true;
                    c0005d.f207b.e(false);
                    d.this.C = false;
                }
                if (this.f204c.isEnabled() && this.f204c.hasSubMenu()) {
                    this.f205d.N(this.f204c, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.u
        public void a(g gVar, MenuItem menuItem) {
            d.this.i.removeCallbacksAndMessages(null);
            int size = d.this.k.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (gVar == d.this.k.get(i).f207b) {
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

        @Override // androidx.appcompat.widget.u
        public void i(g gVar, MenuItem menuItem) {
            d.this.i.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.view.menu.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    private static class C0005d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f206a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f207b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f208c;

        public C0005d(MenuPopupWindow menuPopupWindow, g gVar, int i) {
            this.f206a = menuPopupWindow;
            this.f207b = gVar;
            this.f208c = i;
        }

        public ListView a() {
            return this.f206a.l();
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.f199d = context;
        this.q = view;
        this.f = i;
        this.g = i2;
        this.h = z;
        Resources resources = context.getResources();
        this.e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.a.d.f1490d));
        this.i = new Handler();
    }

    private MenuPopupWindow C() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f199d, null, this.f, this.g);
        menuPopupWindow.T(this.n);
        menuPopupWindow.L(this);
        menuPopupWindow.K(this);
        menuPopupWindow.D(this.q);
        menuPopupWindow.G(this.p);
        menuPopupWindow.J(true);
        menuPopupWindow.I(2);
        return menuPopupWindow;
    }

    private int D(g gVar) {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (gVar == this.k.get(i).f207b) {
                return i;
            }
        }
        return -1;
    }

    private MenuItem E(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View F(C0005d c0005d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemE = E(c0005d.f207b, gVar);
        if (menuItemE == null) {
            return null;
        }
        ListView listViewA = c0005d.a();
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
            if (menuItemE == fVar.getItem(i)) {
                break;
            }
            i++;
        }
        if (i != -1 && (firstVisiblePosition = (i + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int G() {
        return t.z(this.q) == 1 ? 0 : 1;
    }

    private int H(int i) {
        List<C0005d> list = this.k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.r.getWindowVisibleDisplayFrame(rect);
        return this.s == 1 ? (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    private void I(g gVar) {
        C0005d c0005d;
        View viewF;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f199d);
        f fVar = new f(gVar, layoutInflaterFrom, this.h, f198c);
        if (!b() && this.x) {
            fVar.d(true);
        } else if (b()) {
            fVar.d(k.A(gVar));
        }
        int iR = k.r(fVar, null, this.f199d, this.e);
        MenuPopupWindow menuPopupWindowC = C();
        menuPopupWindowC.o(fVar);
        menuPopupWindowC.F(iR);
        menuPopupWindowC.G(this.p);
        if (this.k.size() > 0) {
            List<C0005d> list = this.k;
            c0005d = list.get(list.size() - 1);
            viewF = F(c0005d, gVar);
        } else {
            c0005d = null;
            viewF = null;
        }
        if (viewF != null) {
            menuPopupWindowC.U(false);
            menuPopupWindowC.R(null);
            int iH = H(iR);
            boolean z = iH == 1;
            this.s = iH;
            if (Build.VERSION.SDK_INT >= 26) {
                menuPopupWindowC.D(viewF);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.q.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewF.getLocationOnScreen(iArr2);
                if ((this.p & 7) == 5) {
                    iArr[0] = iArr[0] + this.q.getWidth();
                    iArr2[0] = iArr2[0] + viewF.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.p & 5) == 5) {
                if (!z) {
                    iR = viewF.getWidth();
                    i3 = i - iR;
                }
                i3 = i + iR;
            } else {
                if (z) {
                    iR = viewF.getWidth();
                    i3 = i + iR;
                }
                i3 = i - iR;
            }
            menuPopupWindowC.d(i3);
            menuPopupWindowC.M(true);
            menuPopupWindowC.n(i2);
        } else {
            if (this.t) {
                menuPopupWindowC.d(this.v);
            }
            if (this.u) {
                menuPopupWindowC.n(this.w);
            }
            menuPopupWindowC.H(q());
        }
        this.k.add(new C0005d(menuPopupWindowC, gVar, this.s));
        menuPopupWindowC.g();
        ListView listViewL = menuPopupWindowC.l();
        listViewL.setOnKeyListener(this);
        if (c0005d == null && this.y && gVar.z() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(b.a.g.n, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.z());
            listViewL.addHeaderView(frameLayout, null, false);
            menuPopupWindowC.g();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        int iD = D(gVar);
        if (iD < 0) {
            return;
        }
        int i = iD + 1;
        if (i < this.k.size()) {
            this.k.get(i).f207b.e(false);
        }
        C0005d c0005dRemove = this.k.remove(iD);
        c0005dRemove.f207b.Q(this);
        if (this.C) {
            c0005dRemove.f206a.S(null);
            c0005dRemove.f206a.E(0);
        }
        c0005dRemove.f206a.dismiss();
        int size = this.k.size();
        if (size > 0) {
            this.s = this.k.get(size - 1).f208c;
        } else {
            this.s = G();
        }
        if (size != 0) {
            if (z) {
                this.k.get(0).f207b.e(false);
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
        return this.k.size() > 0 && this.k.get(0).f206a.b();
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.k.size();
        if (size > 0) {
            C0005d[] c0005dArr = (C0005d[]) this.k.toArray(new C0005d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0005d c0005d = c0005dArr[i];
                if (c0005d.f206a.b()) {
                    c0005d.f206a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable e() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.p
    public void g() {
        if (b()) {
            return;
        }
        Iterator<g> it = this.j.iterator();
        while (it.hasNext()) {
            I(it.next());
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
    public void h(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.m
    public void k(m.a aVar) {
        this.z = aVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView l() {
        if (this.k.isEmpty()) {
            return null;
        }
        return this.k.get(r0.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean m(r rVar) {
        for (C0005d c0005d : this.k) {
            if (rVar == c0005d.f207b) {
                c0005d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        o(rVar);
        m.a aVar = this.z;
        if (aVar != null) {
            aVar.b(rVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(boolean z) {
        Iterator<C0005d> it = this.k.iterator();
        while (it.hasNext()) {
            k.B(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void o(g gVar) {
        gVar.c(this, this.f199d);
        if (b()) {
            I(gVar);
        } else {
            this.j.add(gVar);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0005d c0005d;
        int size = this.k.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                c0005d = null;
                break;
            }
            c0005d = this.k.get(i);
            if (!c0005d.f206a.b()) {
                break;
            } else {
                i++;
            }
        }
        if (c0005d != null) {
            c0005d.f207b.e(false);
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
    protected boolean p() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void s(View view) {
        if (this.q != view) {
            this.q = view;
            this.p = b.g.k.d.b(this.o, t.z(view));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void u(boolean z) {
        this.x = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void v(int i) {
        if (this.o != i) {
            this.o = i;
            this.p = b.g.k.d.b(i, t.z(this.q));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void w(int i) {
        this.t = true;
        this.v = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.B = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void y(boolean z) {
        this.y = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void z(int i) {
        this.u = true;
        this.w = i;
    }
}
