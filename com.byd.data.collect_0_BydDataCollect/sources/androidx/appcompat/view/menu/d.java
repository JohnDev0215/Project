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
import androidx.appcompat.widget.v;
import b.g.k.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f273c = b.a.g.abc_cascading_menu_item_layout;
    ViewTreeObserver A;
    private PopupWindow.OnDismissListener B;
    boolean C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f274d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f275e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f276f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f277g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f278h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final Handler f279i;
    private View q;
    View r;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private boolean y;
    private m.a z;
    private final List<g> j = new ArrayList();
    final List<C0006d> k = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    private final View.OnAttachStateChangeListener m = new b();
    private final v n = new c();
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
            if (!d.this.b() || d.this.k.size() <= 0 || d.this.k.get(0).f287a.B()) {
                return;
            }
            View view = d.this.r;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0006d> it = d.this.k.iterator();
            while (it.hasNext()) {
                it.next().f287a.g();
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
    class c implements v {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ C0006d f283b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ MenuItem f284c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            final /* synthetic */ g f285d;

            a(C0006d c0006d, MenuItem menuItem, g gVar) {
                this.f283b = c0006d;
                this.f284c = menuItem;
                this.f285d = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0006d c0006d = this.f283b;
                if (c0006d != null) {
                    d.this.C = true;
                    c0006d.f288b.e(false);
                    d.this.C = false;
                }
                if (this.f284c.isEnabled() && this.f284c.hasSubMenu()) {
                    this.f285d.N(this.f284c, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.v
        public void a(g gVar, MenuItem menuItem) {
            d.this.f279i.removeCallbacksAndMessages(null);
            int size = d.this.k.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (gVar == d.this.k.get(i2).f288b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            d.this.f279i.postAtTime(new a(i3 < d.this.k.size() ? d.this.k.get(i3) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.v
        public void i(g gVar, MenuItem menuItem) {
            d.this.f279i.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.view.menu.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    private static class C0006d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f287a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f288b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f289c;

        public C0006d(MenuPopupWindow menuPopupWindow, g gVar, int i2) {
            this.f287a = menuPopupWindow;
            this.f288b = gVar;
            this.f289c = i2;
        }

        public ListView a() {
            return this.f287a.l();
        }
    }

    public d(Context context, View view, int i2, int i3, boolean z) {
        this.f274d = context;
        this.q = view;
        this.f276f = i2;
        this.f277g = i3;
        this.f278h = z;
        Resources resources = context.getResources();
        this.f275e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.a.d.abc_config_prefDialogWidth));
        this.f279i = new Handler();
    }

    private MenuPopupWindow C() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f274d, null, this.f276f, this.f277g);
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
        for (int i2 = 0; i2 < size; i2++) {
            if (gVar == this.k.get(i2).f288b) {
                return i2;
            }
        }
        return -1;
    }

    private MenuItem E(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = gVar.getItem(i2);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    private View F(C0006d c0006d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemE = E(c0006d.f288b, gVar);
        if (menuItemE == null) {
            return null;
        }
        ListView listViewA = c0006d.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i2 = 0;
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
            if (i2 >= count) {
                i2 = -1;
                break;
            }
            if (menuItemE == fVar.getItem(i2)) {
                break;
            }
            i2++;
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private int G() {
        return t.z(this.q) == 1 ? 0 : 1;
    }

    private int H(int i2) {
        List<C0006d> list = this.k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.r.getWindowVisibleDisplayFrame(rect);
        return this.s == 1 ? (iArr[0] + listViewA.getWidth()) + i2 > rect.right ? 0 : 1 : iArr[0] - i2 < 0 ? 1 : 0;
    }

    private void I(g gVar) {
        C0006d c0006d;
        View viewF;
        int i2;
        int i3;
        int i4;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f274d);
        f fVar = new f(gVar, layoutInflaterFrom, this.f278h, f273c);
        if (!b() && this.x) {
            fVar.d(true);
        } else if (b()) {
            fVar.d(k.A(gVar));
        }
        int iR = k.r(fVar, null, this.f274d, this.f275e);
        MenuPopupWindow menuPopupWindowC = C();
        menuPopupWindowC.o(fVar);
        menuPopupWindowC.F(iR);
        menuPopupWindowC.G(this.p);
        if (this.k.size() > 0) {
            List<C0006d> list = this.k;
            c0006d = list.get(list.size() - 1);
            viewF = F(c0006d, gVar);
        } else {
            c0006d = null;
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
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr = new int[2];
                this.q.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewF.getLocationOnScreen(iArr2);
                if ((this.p & 7) == 5) {
                    iArr[0] = iArr[0] + this.q.getWidth();
                    iArr2[0] = iArr2[0] + viewF.getWidth();
                }
                i2 = iArr2[0] - iArr[0];
                i3 = iArr2[1] - iArr[1];
            }
            if ((this.p & 5) == 5) {
                if (!z) {
                    iR = viewF.getWidth();
                    i4 = i2 - iR;
                }
                i4 = i2 + iR;
            } else {
                if (z) {
                    iR = viewF.getWidth();
                    i4 = i2 + iR;
                }
                i4 = i2 - iR;
            }
            menuPopupWindowC.d(i4);
            menuPopupWindowC.M(true);
            menuPopupWindowC.n(i3);
        } else {
            if (this.t) {
                menuPopupWindowC.d(this.v);
            }
            if (this.u) {
                menuPopupWindowC.n(this.w);
            }
            menuPopupWindowC.H(q());
        }
        this.k.add(new C0006d(menuPopupWindowC, gVar, this.s));
        menuPopupWindowC.g();
        ListView listViewL = menuPopupWindowC.l();
        listViewL.setOnKeyListener(this);
        if (c0006d == null && this.y && gVar.z() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(b.a.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
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
        int i2 = iD + 1;
        if (i2 < this.k.size()) {
            this.k.get(i2).f288b.e(false);
        }
        C0006d c0006dRemove = this.k.remove(iD);
        c0006dRemove.f288b.Q(this);
        if (this.C) {
            c0006dRemove.f287a.S(null);
            c0006dRemove.f287a.E(0);
        }
        c0006dRemove.f287a.dismiss();
        int size = this.k.size();
        if (size > 0) {
            this.s = this.k.get(size - 1).f289c;
        } else {
            this.s = G();
        }
        if (size != 0) {
            if (z) {
                this.k.get(0).f288b.e(false);
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
        return this.k.size() > 0 && this.k.get(0).f287a.b();
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.k.size();
        if (size > 0) {
            C0006d[] c0006dArr = (C0006d[]) this.k.toArray(new C0006d[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                C0006d c0006d = c0006dArr[i2];
                if (c0006d.f287a.b()) {
                    c0006d.f287a.dismiss();
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
        for (C0006d c0006d : this.k) {
            if (rVar == c0006d.f288b) {
                c0006d.a().requestFocus();
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
        Iterator<C0006d> it = this.k.iterator();
        while (it.hasNext()) {
            k.B(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void o(g gVar) {
        gVar.c(this, this.f274d);
        if (b()) {
            I(gVar);
        } else {
            this.j.add(gVar);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0006d c0006d;
        int size = this.k.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                c0006d = null;
                break;
            }
            c0006d = this.k.get(i2);
            if (!c0006d.f287a.b()) {
                break;
            } else {
                i2++;
            }
        }
        if (c0006d != null) {
            c0006d.f288b.e(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
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
    public void v(int i2) {
        if (this.o != i2) {
            this.o = i2;
            this.p = b.g.k.d.b(i2, t.z(this.q));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void w(int i2) {
        this.t = true;
        this.v = i2;
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
    public void z(int i2) {
        this.u = true;
        this.w = i2;
    }
}
