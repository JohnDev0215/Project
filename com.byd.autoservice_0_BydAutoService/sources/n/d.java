package n;

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
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.MenuPopupWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l0.t;
import n.m;
import o.u;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f3881c = R$layout.abc_cascading_menu_item_layout;
    public ViewTreeObserver A;
    public PopupWindow.OnDismissListener B;
    public boolean C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f3882d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3883e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3884f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3885g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3886h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Handler f3887i;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f3895q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f3896r;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3898t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f3899u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f3900v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f3901w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f3903y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public m.a f3904z;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List<g> f3888j = new ArrayList();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final List<C0040d> f3889k = new ArrayList();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f3890l = new a();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final View.OnAttachStateChangeListener f3891m = new b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u f3892n = new c();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3893o = 0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3894p = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f3902x = false;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3897s = G();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.b() || d.this.f3889k.size() <= 0 || d.this.f3889k.get(0).f3912a.B()) {
                return;
            }
            View view = d.this.f3896r;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0040d> it = d.this.f3889k.iterator();
            while (it.hasNext()) {
                it.next().f3912a.g();
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
                dVar.A.removeGlobalOnLayoutListener(dVar.f3890l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class c implements u {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ C0040d f3908b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ MenuItem f3909c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ g f3910d;

            public a(C0040d c0040d, MenuItem menuItem, g gVar) {
                this.f3908b = c0040d;
                this.f3909c = menuItem;
                this.f3910d = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0040d c0040d = this.f3908b;
                if (c0040d != null) {
                    d.this.C = true;
                    c0040d.f3913b.e(false);
                    d.this.C = false;
                }
                if (this.f3909c.isEnabled() && this.f3909c.hasSubMenu()) {
                    this.f3910d.N(this.f3909c, 4);
                }
            }
        }

        public c() {
        }

        @Override // o.u
        public void a(g gVar, MenuItem menuItem) {
            d.this.f3887i.removeCallbacksAndMessages(null);
            int size = d.this.f3889k.size();
            int i3 = 0;
            while (true) {
                if (i3 >= size) {
                    i3 = -1;
                    break;
                } else if (gVar == d.this.f3889k.get(i3).f3913b) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 == -1) {
                return;
            }
            int i4 = i3 + 1;
            d.this.f3887i.postAtTime(new a(i4 < d.this.f3889k.size() ? d.this.f3889k.get(i4) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // o.u
        public void i(g gVar, MenuItem menuItem) {
            d.this.f3887i.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: n.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public static class C0040d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f3912a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f3913b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f3914c;

        public C0040d(MenuPopupWindow menuPopupWindow, g gVar, int i3) {
            this.f3912a = menuPopupWindow;
            this.f3913b = gVar;
            this.f3914c = i3;
        }

        public ListView a() {
            return this.f3912a.l();
        }
    }

    public d(Context context, View view, int i3, int i4, boolean z3) {
        this.f3882d = context;
        this.f3895q = view;
        this.f3884f = i3;
        this.f3885g = i4;
        this.f3886h = z3;
        Resources resources = context.getResources();
        this.f3883e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.f3887i = new Handler();
    }

    public final MenuPopupWindow C() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f3882d, null, this.f3884f, this.f3885g);
        menuPopupWindow.T(this.f3892n);
        menuPopupWindow.L(this);
        menuPopupWindow.K(this);
        menuPopupWindow.D(this.f3895q);
        menuPopupWindow.G(this.f3894p);
        menuPopupWindow.J(true);
        menuPopupWindow.I(2);
        return menuPopupWindow;
    }

    public final int D(g gVar) {
        int size = this.f3889k.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (gVar == this.f3889k.get(i3).f3913b) {
                return i3;
            }
        }
        return -1;
    }

    public final MenuItem E(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i3 = 0; i3 < size; i3++) {
            MenuItem item = gVar.getItem(i3);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View F(C0040d c0040d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemE = E(c0040d.f3913b, gVar);
        if (menuItemE == null) {
            return null;
        }
        ListView listViewA = c0040d.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i3 = 0;
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
            if (i3 >= count) {
                i3 = -1;
                break;
            }
            if (menuItemE == fVar.getItem(i3)) {
                break;
            }
            i3++;
        }
        if (i3 != -1 && (firstVisiblePosition = (i3 + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final int G() {
        return t.z(this.f3895q) == 1 ? 0 : 1;
    }

    public final int H(int i3) {
        List<C0040d> list = this.f3889k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f3896r.getWindowVisibleDisplayFrame(rect);
        return this.f3897s == 1 ? (iArr[0] + listViewA.getWidth()) + i3 > rect.right ? 0 : 1 : iArr[0] - i3 < 0 ? 1 : 0;
    }

    public final void I(g gVar) {
        C0040d c0040d;
        View viewF;
        int i3;
        int i4;
        int i5;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f3882d);
        f fVar = new f(gVar, layoutInflaterFrom, this.f3886h, f3881c);
        if (!b() && this.f3902x) {
            fVar.d(true);
        } else if (b()) {
            fVar.d(k.A(gVar));
        }
        int iR = k.r(fVar, null, this.f3882d, this.f3883e);
        MenuPopupWindow menuPopupWindowC = C();
        menuPopupWindowC.o(fVar);
        menuPopupWindowC.F(iR);
        menuPopupWindowC.G(this.f3894p);
        if (this.f3889k.size() > 0) {
            List<C0040d> list = this.f3889k;
            c0040d = list.get(list.size() - 1);
            viewF = F(c0040d, gVar);
        } else {
            c0040d = null;
            viewF = null;
        }
        if (viewF != null) {
            menuPopupWindowC.U(false);
            menuPopupWindowC.R(null);
            int iH = H(iR);
            boolean z3 = iH == 1;
            this.f3897s = iH;
            if (Build.VERSION.SDK_INT >= 26) {
                menuPopupWindowC.D(viewF);
                i4 = 0;
                i3 = 0;
            } else {
                int[] iArr = new int[2];
                this.f3895q.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewF.getLocationOnScreen(iArr2);
                if ((this.f3894p & 7) == 5) {
                    iArr[0] = iArr[0] + this.f3895q.getWidth();
                    iArr2[0] = iArr2[0] + viewF.getWidth();
                }
                i3 = iArr2[0] - iArr[0];
                i4 = iArr2[1] - iArr[1];
            }
            if ((this.f3894p & 5) == 5) {
                if (!z3) {
                    iR = viewF.getWidth();
                    i5 = i3 - iR;
                }
                i5 = i3 + iR;
            } else {
                if (z3) {
                    iR = viewF.getWidth();
                    i5 = i3 + iR;
                }
                i5 = i3 - iR;
            }
            menuPopupWindowC.d(i5);
            menuPopupWindowC.M(true);
            menuPopupWindowC.n(i4);
        } else {
            if (this.f3898t) {
                menuPopupWindowC.d(this.f3900v);
            }
            if (this.f3899u) {
                menuPopupWindowC.n(this.f3901w);
            }
            menuPopupWindowC.H(q());
        }
        this.f3889k.add(new C0040d(menuPopupWindowC, gVar, this.f3897s));
        menuPopupWindowC.g();
        ListView listViewL = menuPopupWindowC.l();
        listViewL.setOnKeyListener(this);
        if (c0040d == null && this.f3903y && gVar.z() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.z());
            listViewL.addHeaderView(frameLayout, null, false);
            menuPopupWindowC.g();
        }
    }

    @Override // n.m
    public void a(g gVar, boolean z3) {
        int iD = D(gVar);
        if (iD < 0) {
            return;
        }
        int i3 = iD + 1;
        if (i3 < this.f3889k.size()) {
            this.f3889k.get(i3).f3913b.e(false);
        }
        C0040d c0040dRemove = this.f3889k.remove(iD);
        c0040dRemove.f3913b.Q(this);
        if (this.C) {
            c0040dRemove.f3912a.S(null);
            c0040dRemove.f3912a.E(0);
        }
        c0040dRemove.f3912a.dismiss();
        int size = this.f3889k.size();
        if (size > 0) {
            this.f3897s = this.f3889k.get(size - 1).f3914c;
        } else {
            this.f3897s = G();
        }
        if (size != 0) {
            if (z3) {
                this.f3889k.get(0).f3913b.e(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.f3904z;
        if (aVar != null) {
            aVar.a(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.A;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.A.removeGlobalOnLayoutListener(this.f3890l);
            }
            this.A = null;
        }
        this.f3896r.removeOnAttachStateChangeListener(this.f3891m);
        this.B.onDismiss();
    }

    @Override // n.p
    public boolean b() {
        return this.f3889k.size() > 0 && this.f3889k.get(0).f3912a.b();
    }

    @Override // n.m
    public boolean d() {
        return false;
    }

    @Override // n.p
    public void dismiss() {
        int size = this.f3889k.size();
        if (size > 0) {
            C0040d[] c0040dArr = (C0040d[]) this.f3889k.toArray(new C0040d[size]);
            for (int i3 = size - 1; i3 >= 0; i3--) {
                C0040d c0040d = c0040dArr[i3];
                if (c0040d.f3912a.b()) {
                    c0040d.f3912a.dismiss();
                }
            }
        }
    }

    @Override // n.m
    public Parcelable e() {
        return null;
    }

    @Override // n.p
    public void g() {
        if (b()) {
            return;
        }
        Iterator<g> it = this.f3888j.iterator();
        while (it.hasNext()) {
            I(it.next());
        }
        this.f3888j.clear();
        View view = this.f3895q;
        this.f3896r = view;
        if (view != null) {
            boolean z3 = this.A == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.A = viewTreeObserver;
            if (z3) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f3890l);
            }
            this.f3896r.addOnAttachStateChangeListener(this.f3891m);
        }
    }

    @Override // n.m
    public void h(Parcelable parcelable) {
    }

    @Override // n.m
    public void k(m.a aVar) {
        this.f3904z = aVar;
    }

    @Override // n.p
    public ListView l() {
        if (this.f3889k.isEmpty()) {
            return null;
        }
        return this.f3889k.get(r0.size() - 1).a();
    }

    @Override // n.m
    public boolean m(r rVar) {
        for (C0040d c0040d : this.f3889k) {
            if (rVar == c0040d.f3913b) {
                c0040d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        o(rVar);
        m.a aVar = this.f3904z;
        if (aVar != null) {
            aVar.b(rVar);
        }
        return true;
    }

    @Override // n.m
    public void n(boolean z3) {
        Iterator<C0040d> it = this.f3889k.iterator();
        while (it.hasNext()) {
            k.B(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // n.k
    public void o(g gVar) {
        gVar.c(this, this.f3882d);
        if (b()) {
            I(gVar);
        } else {
            this.f3888j.add(gVar);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0040d c0040d;
        int size = this.f3889k.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                c0040d = null;
                break;
            }
            c0040d = this.f3889k.get(i3);
            if (!c0040d.f3912a.b()) {
                break;
            } else {
                i3++;
            }
        }
        if (c0040d != null) {
            c0040d.f3913b.e(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i3, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i3 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // n.k
    public boolean p() {
        return false;
    }

    @Override // n.k
    public void s(View view) {
        if (this.f3895q != view) {
            this.f3895q = view;
            this.f3894p = l0.d.b(this.f3893o, t.z(view));
        }
    }

    @Override // n.k
    public void u(boolean z3) {
        this.f3902x = z3;
    }

    @Override // n.k
    public void v(int i3) {
        if (this.f3893o != i3) {
            this.f3893o = i3;
            this.f3894p = l0.d.b(i3, t.z(this.f3895q));
        }
    }

    @Override // n.k
    public void w(int i3) {
        this.f3898t = true;
        this.f3900v = i3;
    }

    @Override // n.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.B = onDismissListener;
    }

    @Override // n.k
    public void y(boolean z3) {
        this.f3903y = z3;
    }

    @Override // n.k
    public void z(int i3) {
        this.f3899u = true;
        this.f3901w = i3;
    }
}
