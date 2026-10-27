package b.b.p.j;

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
import androidx.appcompat.widget.MenuPopupWindow;
import b.b.p.j.m;
import b.b.q.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f922d = b.b.g.f755g;
    public final int E3;
    public final boolean F3;
    public final Handler G3;
    public View O3;
    public View P3;
    public boolean R3;
    public boolean S3;
    public int T3;
    public int U3;
    public boolean W3;
    public m.a X3;
    public ViewTreeObserver Y3;
    public PopupWindow.OnDismissListener Z3;
    public boolean a4;
    public final Context s;
    public final int x;
    public final int y;
    public final List<g> H3 = new ArrayList();
    public final List<C0009d> I3 = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener J3 = new a();
    public final View.OnAttachStateChangeListener K3 = new b();
    public final u L3 = new c();
    public int M3 = 0;
    public int N3 = 0;
    public boolean V3 = false;
    public int Q3 = D();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.b() || d.this.I3.size() <= 0 || d.this.I3.get(0).f928a.x()) {
                return;
            }
            View view = d.this.P3;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0009d> it = d.this.I3.iterator();
            while (it.hasNext()) {
                it.next().f928a.f();
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
            ViewTreeObserver viewTreeObserver = d.this.Y3;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.Y3 = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.Y3.removeGlobalOnLayoutListener(dVar.J3);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class c implements u {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ C0009d f926c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ MenuItem f927d;
            public final /* synthetic */ g s;

            public a(C0009d c0009d, MenuItem menuItem, g gVar) {
                this.f926c = c0009d;
                this.f927d = menuItem;
                this.s = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0009d c0009d = this.f926c;
                if (c0009d != null) {
                    d.this.a4 = true;
                    c0009d.f929b.e(false);
                    d.this.a4 = false;
                }
                if (this.f927d.isEnabled() && this.f927d.hasSubMenu()) {
                    this.s.L(this.f927d, 4);
                }
            }
        }

        public c() {
        }

        @Override // b.b.q.u
        public void e(g gVar, MenuItem menuItem) {
            d.this.G3.removeCallbacksAndMessages(null);
            int size = d.this.I3.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i2 = -1;
                    break;
                } else if (gVar == d.this.I3.get(i2).f929b) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                return;
            }
            int i3 = i2 + 1;
            d.this.G3.postAtTime(new a(i3 < d.this.I3.size() ? d.this.I3.get(i3) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // b.b.q.u
        public void h(g gVar, MenuItem menuItem) {
            d.this.G3.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: b.b.p.j.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public static class C0009d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f928a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f929b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f930c;

        public C0009d(MenuPopupWindow menuPopupWindow, g gVar, int i2) {
            this.f928a = menuPopupWindow;
            this.f929b = gVar;
            this.f930c = i2;
        }

        public ListView a() {
            return this.f928a.k();
        }
    }

    public d(Context context, View view, int i2, int i3, boolean z) {
        this.s = context;
        this.O3 = view;
        this.y = i2;
        this.E3 = i3;
        this.F3 = z;
        Resources resources = context.getResources();
        this.x = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.b.d.f725d));
        this.G3 = new Handler();
    }

    public final int A(g gVar) {
        int size = this.I3.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (gVar == this.I3.get(i2).f929b) {
                return i2;
            }
        }
        return -1;
    }

    public final MenuItem B(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = gVar.getItem(i2);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View C(C0009d c0009d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemB = B(c0009d.f929b, gVar);
        if (menuItemB == null) {
            return null;
        }
        ListView listViewA = c0009d.a();
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
            if (menuItemB == fVar.getItem(i2)) {
                break;
            }
            i2++;
        }
        if (i2 != -1 && (firstVisiblePosition = (i2 + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final int D() {
        return b.f.m.s.v(this.O3) == 1 ? 0 : 1;
    }

    public final int E(int i2) {
        List<C0009d> list = this.I3;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.P3.getWindowVisibleDisplayFrame(rect);
        return this.Q3 == 1 ? (iArr[0] + listViewA.getWidth()) + i2 > rect.right ? 0 : 1 : iArr[0] - i2 < 0 ? 1 : 0;
    }

    public final void F(g gVar) {
        C0009d c0009d;
        View viewC;
        int i2;
        int i3;
        int i4;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.s);
        f fVar = new f(gVar, layoutInflaterFrom, this.F3, f922d);
        if (!b() && this.V3) {
            fVar.d(true);
        } else if (b()) {
            fVar.d(k.x(gVar));
        }
        int iO = k.o(fVar, null, this.s, this.x);
        MenuPopupWindow menuPopupWindowZ = z();
        menuPopupWindowZ.o(fVar);
        menuPopupWindowZ.B(iO);
        menuPopupWindowZ.C(this.N3);
        if (this.I3.size() > 0) {
            List<C0009d> list = this.I3;
            c0009d = list.get(list.size() - 1);
            viewC = C(c0009d, gVar);
        } else {
            c0009d = null;
            viewC = null;
        }
        if (viewC != null) {
            menuPopupWindowZ.Q(false);
            menuPopupWindowZ.N(null);
            int iE = E(iO);
            boolean z = iE == 1;
            this.Q3 = iE;
            if (Build.VERSION.SDK_INT >= 26) {
                menuPopupWindowZ.z(viewC);
                i3 = 0;
                i2 = 0;
            } else {
                int[] iArr = new int[2];
                this.O3.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewC.getLocationOnScreen(iArr2);
                if ((this.N3 & 7) == 5) {
                    iArr[0] = iArr[0] + this.O3.getWidth();
                    iArr2[0] = iArr2[0] + viewC.getWidth();
                }
                i2 = iArr2[0] - iArr[0];
                i3 = iArr2[1] - iArr[1];
            }
            if ((this.N3 & 5) == 5) {
                if (!z) {
                    iO = viewC.getWidth();
                    i4 = i2 - iO;
                }
                i4 = i2 + iO;
            } else {
                if (z) {
                    iO = viewC.getWidth();
                    i4 = i2 + iO;
                }
                i4 = i2 - iO;
            }
            menuPopupWindowZ.a(i4);
            menuPopupWindowZ.I(true);
            menuPopupWindowZ.n(i3);
        } else {
            if (this.R3) {
                menuPopupWindowZ.a(this.T3);
            }
            if (this.S3) {
                menuPopupWindowZ.n(this.U3);
            }
            menuPopupWindowZ.D(n());
        }
        this.I3.add(new C0009d(menuPopupWindowZ, gVar, this.Q3));
        menuPopupWindowZ.f();
        ListView listViewK = menuPopupWindowZ.k();
        listViewK.setOnKeyListener(this);
        if (c0009d == null && this.W3 && gVar.x() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(b.b.g.n, (ViewGroup) listViewK, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.x());
            listViewK.addHeaderView(frameLayout, null, false);
            menuPopupWindowZ.f();
        }
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        int iA = A(gVar);
        if (iA < 0) {
            return;
        }
        int i2 = iA + 1;
        if (i2 < this.I3.size()) {
            this.I3.get(i2).f929b.e(false);
        }
        C0009d c0009dRemove = this.I3.remove(iA);
        c0009dRemove.f929b.O(this);
        if (this.a4) {
            c0009dRemove.f928a.O(null);
            c0009dRemove.f928a.A(0);
        }
        c0009dRemove.f928a.dismiss();
        int size = this.I3.size();
        if (size > 0) {
            this.Q3 = this.I3.get(size - 1).f930c;
        } else {
            this.Q3 = D();
        }
        if (size != 0) {
            if (z) {
                this.I3.get(0).f929b.e(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.X3;
        if (aVar != null) {
            aVar.a(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.Y3;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.Y3.removeGlobalOnLayoutListener(this.J3);
            }
            this.Y3 = null;
        }
        this.P3.removeOnAttachStateChangeListener(this.K3);
        this.Z3.onDismiss();
    }

    @Override // b.b.p.j.p
    public boolean b() {
        return this.I3.size() > 0 && this.I3.get(0).f928a.b();
    }

    @Override // b.b.p.j.m
    public boolean c() {
        return false;
    }

    @Override // b.b.p.j.p
    public void dismiss() {
        int size = this.I3.size();
        if (size > 0) {
            C0009d[] c0009dArr = (C0009d[]) this.I3.toArray(new C0009d[size]);
            for (int i2 = size - 1; i2 >= 0; i2--) {
                C0009d c0009d = c0009dArr[i2];
                if (c0009d.f928a.b()) {
                    c0009d.f928a.dismiss();
                }
            }
        }
    }

    @Override // b.b.p.j.p
    public void f() {
        if (b()) {
            return;
        }
        Iterator<g> it = this.H3.iterator();
        while (it.hasNext()) {
            F(it.next());
        }
        this.H3.clear();
        View view = this.O3;
        this.P3 = view;
        if (view != null) {
            boolean z = this.Y3 == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.Y3 = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.J3);
            }
            this.P3.addOnAttachStateChangeListener(this.K3);
        }
    }

    @Override // b.b.p.j.m
    public void h(m.a aVar) {
        this.X3 = aVar;
    }

    @Override // b.b.p.j.m
    public boolean i(r rVar) {
        for (C0009d c0009d : this.I3) {
            if (rVar == c0009d.f929b) {
                c0009d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        l(rVar);
        m.a aVar = this.X3;
        if (aVar != null) {
            aVar.b(rVar);
        }
        return true;
    }

    @Override // b.b.p.j.m
    public void j(boolean z) {
        Iterator<C0009d> it = this.I3.iterator();
        while (it.hasNext()) {
            k.y(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // b.b.p.j.p
    public ListView k() {
        if (this.I3.isEmpty()) {
            return null;
        }
        return this.I3.get(r0.size() - 1).a();
    }

    @Override // b.b.p.j.k
    public void l(g gVar) {
        gVar.c(this, this.s);
        if (b()) {
            F(gVar);
        } else {
            this.H3.add(gVar);
        }
    }

    @Override // b.b.p.j.k
    public boolean m() {
        return false;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0009d c0009d;
        int size = this.I3.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                c0009d = null;
                break;
            }
            c0009d = this.I3.get(i2);
            if (!c0009d.f928a.b()) {
                break;
            } else {
                i2++;
            }
        }
        if (c0009d != null) {
            c0009d.f929b.e(false);
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
    public void p(View view) {
        if (this.O3 != view) {
            this.O3 = view;
            this.N3 = b.f.m.d.a(this.M3, b.f.m.s.v(view));
        }
    }

    @Override // b.b.p.j.k
    public void r(boolean z) {
        this.V3 = z;
    }

    @Override // b.b.p.j.k
    public void s(int i2) {
        if (this.M3 != i2) {
            this.M3 = i2;
            this.N3 = b.f.m.d.a(i2, b.f.m.s.v(this.O3));
        }
    }

    @Override // b.b.p.j.k
    public void t(int i2) {
        this.R3 = true;
        this.T3 = i2;
    }

    @Override // b.b.p.j.k
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.Z3 = onDismissListener;
    }

    @Override // b.b.p.j.k
    public void v(boolean z) {
        this.W3 = z;
    }

    @Override // b.b.p.j.k
    public void w(int i2) {
        this.S3 = true;
        this.U3 = i2;
    }

    public final MenuPopupWindow z() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.s, null, this.y, this.E3);
        menuPopupWindow.P(this.L3);
        menuPopupWindow.H(this);
        menuPopupWindow.G(this);
        menuPopupWindow.z(this.O3);
        menuPopupWindow.C(this.N3);
        menuPopupWindow.F(true);
        menuPopupWindow.E(2);
        return menuPopupWindow;
    }
}
