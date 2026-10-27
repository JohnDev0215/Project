package b.b.p.j;

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
import androidx.appcompat.widget.MenuPopupWindow;
import b.b.p.j.m;
import b.b.q.u;
import b.h.m.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f1599c = b.b.g.abc_cascading_menu_item_layout;
    public ViewTreeObserver A;
    public PopupWindow.OnDismissListener B;
    public boolean C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f1600d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1601e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1602f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1603g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f1604h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Handler f1605i;
    public View q;
    public View r;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public boolean y;
    public m.a z;
    public final List<g> j = new ArrayList();
    public final List<C0018d> k = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    public final View.OnAttachStateChangeListener m = new b();
    public final u n = new c();
    public int o = 0;
    public int p = 0;
    public boolean x = false;
    public int s = G();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.b() || d.this.k.size() <= 0 || d.this.k.get(0).f1613a.B()) {
                return;
            }
            View view = d.this.r;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0018d> it = d.this.k.iterator();
            while (it.hasNext()) {
                it.next().f1613a.g();
            }
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
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
    public class c implements u {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ C0018d f1609b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ MenuItem f1610c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ g f1611d;

            public a(C0018d c0018d, MenuItem menuItem, g gVar) {
                this.f1609b = c0018d;
                this.f1610c = menuItem;
                this.f1611d = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0018d c0018d = this.f1609b;
                if (c0018d != null) {
                    d.this.C = true;
                    c0018d.f1614b.e(false);
                    d.this.C = false;
                }
                if (this.f1610c.isEnabled() && this.f1610c.hasSubMenu()) {
                    this.f1611d.N(this.f1610c, 4);
                }
            }
        }

        public c() {
        }

        @Override // b.b.q.u
        public void a(g gVar, MenuItem menuItem) {
            d.this.f1605i.removeCallbacksAndMessages(null);
            int size = d.this.k.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (gVar == d.this.k.get(i2).f1614b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            d.this.f1605i.postAtTime(new a(i3 < d.this.k.size() ? d.this.k.get(i3) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // b.b.q.u
        public void i(g gVar, MenuItem menuItem) {
            d.this.f1605i.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: b.b.p.j.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public static class C0018d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f1613a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f1614b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1615c;

        public C0018d(MenuPopupWindow menuPopupWindow, g gVar, int i2) {
            this.f1613a = menuPopupWindow;
            this.f1614b = gVar;
            this.f1615c = i2;
        }

        public ListView a() {
            return this.f1613a.l();
        }
    }

    public d(Context context, View view, int i2, int i3, boolean z) {
        this.f1600d = context;
        this.q = view;
        this.f1602f = i2;
        this.f1603g = i3;
        this.f1604h = z;
        Resources resources = context.getResources();
        this.f1601e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.b.d.abc_config_prefDialogWidth));
        this.f1605i = new Handler();
    }

    public final MenuPopupWindow C() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f1600d, null, this.f1602f, this.f1603g);
        menuPopupWindow.T(this.n);
        menuPopupWindow.L(this);
        menuPopupWindow.K(this);
        menuPopupWindow.D(this.q);
        menuPopupWindow.G(this.p);
        menuPopupWindow.J(true);
        menuPopupWindow.I(2);
        return menuPopupWindow;
    }

    public final int D(g gVar) {
        int size = this.k.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (gVar == this.k.get(i2).f1614b) {
                return i2;
            }
        }
        return -1;
    }

    public final MenuItem E(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = gVar.getItem(i2);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View F(C0018d c0018d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemE = E(c0018d.f1614b, gVar);
        if (menuItemE == null) {
            return null;
        }
        ListView listViewA = c0018d.a();
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

    public final int G() {
        return t.z(this.q) == 1 ? 0 : 1;
    }

    public final int H(int i2) {
        List<C0018d> list = this.k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.r.getWindowVisibleDisplayFrame(rect);
        return this.s == 1 ? (iArr[0] + listViewA.getWidth()) + i2 > rect.right ? 0 : 1 : iArr[0] - i2 < 0 ? 1 : 0;
    }

    public final void I(g gVar) {
        C0018d c0018d;
        View viewF;
        int i2;
        int i3;
        int i4;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1600d);
        f fVar = new f(gVar, layoutInflaterFrom, this.f1604h, f1599c);
        if (!b() && this.x) {
            fVar.d(true);
        } else if (b()) {
            fVar.d(k.A(gVar));
        }
        int iR = k.r(fVar, null, this.f1600d, this.f1601e);
        MenuPopupWindow menuPopupWindowC = C();
        menuPopupWindowC.o(fVar);
        menuPopupWindowC.F(iR);
        menuPopupWindowC.G(this.p);
        if (this.k.size() > 0) {
            List<C0018d> list = this.k;
            c0018d = list.get(list.size() - 1);
            viewF = F(c0018d, gVar);
        } else {
            c0018d = null;
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
        this.k.add(new C0018d(menuPopupWindowC, gVar, this.s));
        menuPopupWindowC.g();
        ListView listViewL = menuPopupWindowC.l();
        listViewL.setOnKeyListener(this);
        if (c0018d == null && this.y && gVar.z() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(b.b.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.z());
            listViewL.addHeaderView(frameLayout, null, false);
            menuPopupWindowC.g();
        }
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        int iD = D(gVar);
        if (iD < 0) {
            return;
        }
        int i2 = iD + 1;
        if (i2 < this.k.size()) {
            this.k.get(i2).f1614b.e(false);
        }
        C0018d c0018dRemove = this.k.remove(iD);
        c0018dRemove.f1614b.Q(this);
        if (this.C) {
            c0018dRemove.f1613a.S(null);
            c0018dRemove.f1613a.E(0);
        }
        c0018dRemove.f1613a.dismiss();
        int size = this.k.size();
        if (size > 0) {
            this.s = this.k.get(size - 1).f1615c;
        } else {
            this.s = G();
        }
        if (size != 0) {
            if (z) {
                this.k.get(0).f1614b.e(false);
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

    @Override // b.b.p.j.p
    public boolean b() {
        return this.k.size() > 0 && this.k.get(0).f1613a.b();
    }

    @Override // b.b.p.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.p
    public void dismiss() {
        int size = this.k.size();
        if (size > 0) {
            C0018d[] c0018dArr = (C0018d[]) this.k.toArray(new C0018d[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                C0018d c0018d = c0018dArr[i2];
                if (c0018d.f1613a.b()) {
                    c0018d.f1613a.dismiss();
                }
            }
        }
    }

    @Override // b.b.p.j.m
    public Parcelable e() {
        return null;
    }

    @Override // b.b.p.j.p
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

    @Override // b.b.p.j.m
    public void h(Parcelable parcelable) {
    }

    @Override // b.b.p.j.m
    public void k(m.a aVar) {
        this.z = aVar;
    }

    @Override // b.b.p.j.p
    public ListView l() {
        if (this.k.isEmpty()) {
            return null;
        }
        return this.k.get(r0.size() - 1).a();
    }

    @Override // b.b.p.j.m
    public boolean m(r rVar) {
        for (C0018d c0018d : this.k) {
            if (rVar == c0018d.f1614b) {
                c0018d.a().requestFocus();
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

    @Override // b.b.p.j.m
    public void n(boolean z) {
        Iterator<C0018d> it = this.k.iterator();
        while (it.hasNext()) {
            k.B(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // b.b.p.j.k
    public void o(g gVar) {
        gVar.c(this, this.f1600d);
        if (b()) {
            I(gVar);
        } else {
            this.j.add(gVar);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0018d c0018d;
        int size = this.k.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                c0018d = null;
                break;
            }
            c0018d = this.k.get(i2);
            if (!c0018d.f1613a.b()) {
                break;
            } else {
                i2++;
            }
        }
        if (c0018d != null) {
            c0018d.f1614b.e(false);
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

    @Override // b.b.p.j.k
    public boolean p() {
        return false;
    }

    @Override // b.b.p.j.k
    public void s(View view) {
        if (this.q != view) {
            this.q = view;
            this.p = b.h.m.d.b(this.o, t.z(view));
        }
    }

    @Override // b.b.p.j.k
    public void u(boolean z) {
        this.x = z;
    }

    @Override // b.b.p.j.k
    public void v(int i2) {
        if (this.o != i2) {
            this.o = i2;
            this.p = b.h.m.d.b(i2, t.z(this.q));
        }
    }

    @Override // b.b.p.j.k
    public void w(int i2) {
        this.t = true;
        this.v = i2;
    }

    @Override // b.b.p.j.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.B = onDismissListener;
    }

    @Override // b.b.p.j.k
    public void y(boolean z) {
        this.y = z;
    }

    @Override // b.b.p.j.k
    public void z(int i2) {
        this.u = true;
        this.w = i2;
    }
}
