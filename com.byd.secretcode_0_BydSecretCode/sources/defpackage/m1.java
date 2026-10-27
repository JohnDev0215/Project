package defpackage;

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
import defpackage.v1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class m1 extends t1 implements v1, View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int c = R$layout.abc_cascading_menu_item_layout;
    public ViewTreeObserver A;
    public PopupWindow.OnDismissListener B;
    public boolean C;
    public final Context d;
    public final int e;
    public final int f;
    public final int g;
    public final boolean h;
    public final Handler i;
    public View q;
    public View r;
    public boolean t;
    public boolean u;
    public int v;
    public int w;
    public boolean y;
    public v1.a z;
    public final List<p1> j = new ArrayList();
    public final List<d> k = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    public final View.OnAttachStateChangeListener m = new b();
    public final w2 n = new c();
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
            if (!m1.this.b() || m1.this.k.size() <= 0 || m1.this.k.get(0).a.B()) {
                return;
            }
            View view = m1.this.r;
            if (view == null || !view.isShown()) {
                m1.this.dismiss();
                return;
            }
            Iterator<d> it = m1.this.k.iterator();
            while (it.hasNext()) {
                it.next().a.g();
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
            ViewTreeObserver viewTreeObserver = m1.this.A;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    m1.this.A = view.getViewTreeObserver();
                }
                m1 m1Var = m1.this;
                m1Var.A.removeGlobalOnLayoutListener(m1Var.l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class c implements w2 {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        public class a implements Runnable {
            public final /* synthetic */ d b;
            public final /* synthetic */ MenuItem c;
            public final /* synthetic */ p1 d;

            public a(d dVar, MenuItem menuItem, p1 p1Var) {
                this.b = dVar;
                this.c = menuItem;
                this.d = p1Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.b;
                if (dVar != null) {
                    m1.this.C = true;
                    dVar.b.e(false);
                    m1.this.C = false;
                }
                if (this.c.isEnabled() && this.c.hasSubMenu()) {
                    this.d.N(this.c, 4);
                }
            }
        }

        public c() {
        }

        @Override // defpackage.w2
        public void a(p1 p1Var, MenuItem menuItem) {
            m1.this.i.removeCallbacksAndMessages(null);
            int size = m1.this.k.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (p1Var == m1.this.k.get(i).b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            m1.this.i.postAtTime(new a(i2 < m1.this.k.size() ? m1.this.k.get(i2) : null, menuItem, p1Var), p1Var, SystemClock.uptimeMillis() + 200);
        }

        @Override // defpackage.w2
        public void i(p1 p1Var, MenuItem menuItem) {
            m1.this.i.removeCallbacksAndMessages(p1Var);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public static class d {
        public final MenuPopupWindow a;
        public final p1 b;
        public final int c;

        public d(MenuPopupWindow menuPopupWindow, p1 p1Var, int i) {
            this.a = menuPopupWindow;
            this.b = p1Var;
            this.c = i;
        }

        public ListView a() {
            return this.a.l();
        }
    }

    public m1(Context context, View view, int i, int i2, boolean z) {
        this.d = context;
        this.q = view;
        this.f = i;
        this.g = i2;
        this.h = z;
        Resources resources = context.getResources();
        this.e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.i = new Handler();
    }

    public final MenuPopupWindow C() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.d, null, this.f, this.g);
        menuPopupWindow.T(this.n);
        menuPopupWindow.L(this);
        menuPopupWindow.K(this);
        menuPopupWindow.D(this.q);
        menuPopupWindow.G(this.p);
        menuPopupWindow.J(true);
        menuPopupWindow.I(2);
        return menuPopupWindow;
    }

    public final int D(p1 p1Var) {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (p1Var == this.k.get(i).b) {
                return i;
            }
        }
        return -1;
    }

    public final MenuItem E(p1 p1Var, p1 p1Var2) {
        int size = p1Var.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = p1Var.getItem(i);
            if (item.hasSubMenu() && p1Var2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View F(d dVar, p1 p1Var) {
        o1 o1Var;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemE = E(dVar.b, p1Var);
        if (menuItemE == null) {
            return null;
        }
        ListView listViewA = dVar.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            o1Var = (o1) headerViewListAdapter.getWrappedAdapter();
        } else {
            o1Var = (o1) adapter;
            headersCount = 0;
        }
        int count = o1Var.getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            }
            if (menuItemE == o1Var.getItem(i)) {
                break;
            }
            i++;
        }
        if (i != -1 && (firstVisiblePosition = (i + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final int G() {
        return t9.z(this.q) == 1 ? 0 : 1;
    }

    public final int H(int i) {
        List<d> list = this.k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.r.getWindowVisibleDisplayFrame(rect);
        return this.s == 1 ? (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    public final void I(p1 p1Var) {
        d dVar;
        View viewF;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.d);
        o1 o1Var = new o1(p1Var, layoutInflaterFrom, this.h, c);
        if (!b() && this.x) {
            o1Var.d(true);
        } else if (b()) {
            o1Var.d(t1.A(p1Var));
        }
        int iR = t1.r(o1Var, null, this.d, this.e);
        MenuPopupWindow menuPopupWindowC = C();
        menuPopupWindowC.o(o1Var);
        menuPopupWindowC.F(iR);
        menuPopupWindowC.G(this.p);
        if (this.k.size() > 0) {
            List<d> list = this.k;
            dVar = list.get(list.size() - 1);
            viewF = F(dVar, p1Var);
        } else {
            dVar = null;
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
        this.k.add(new d(menuPopupWindowC, p1Var, this.s));
        menuPopupWindowC.g();
        ListView listViewL = menuPopupWindowC.l();
        listViewL.setOnKeyListener(this);
        if (dVar == null && this.y && p1Var.z() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(p1Var.z());
            listViewL.addHeaderView(frameLayout, null, false);
            menuPopupWindowC.g();
        }
    }

    @Override // defpackage.v1
    public void a(p1 p1Var, boolean z) {
        int iD = D(p1Var);
        if (iD < 0) {
            return;
        }
        int i = iD + 1;
        if (i < this.k.size()) {
            this.k.get(i).b.e(false);
        }
        d dVarRemove = this.k.remove(iD);
        dVarRemove.b.Q(this);
        if (this.C) {
            dVarRemove.a.S(null);
            dVarRemove.a.E(0);
        }
        dVarRemove.a.dismiss();
        int size = this.k.size();
        if (size > 0) {
            this.s = this.k.get(size - 1).c;
        } else {
            this.s = G();
        }
        if (size != 0) {
            if (z) {
                this.k.get(0).b.e(false);
                return;
            }
            return;
        }
        dismiss();
        v1.a aVar = this.z;
        if (aVar != null) {
            aVar.a(p1Var, true);
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

    @Override // defpackage.y1
    public boolean b() {
        return this.k.size() > 0 && this.k.get(0).a.b();
    }

    @Override // defpackage.v1
    public boolean d() {
        return false;
    }

    @Override // defpackage.y1
    public void dismiss() {
        int size = this.k.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.k.toArray(new d[size]);
            for (int i = size - 1; i >= 0; i--) {
                d dVar = dVarArr[i];
                if (dVar.a.b()) {
                    dVar.a.dismiss();
                }
            }
        }
    }

    @Override // defpackage.v1
    public Parcelable e() {
        return null;
    }

    @Override // defpackage.y1
    public void g() {
        if (b()) {
            return;
        }
        Iterator<p1> it = this.j.iterator();
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

    @Override // defpackage.v1
    public void h(Parcelable parcelable) {
    }

    @Override // defpackage.v1
    public void k(v1.a aVar) {
        this.z = aVar;
    }

    @Override // defpackage.y1
    public ListView l() {
        if (this.k.isEmpty()) {
            return null;
        }
        return this.k.get(r0.size() - 1).a();
    }

    @Override // defpackage.v1
    public boolean m(a2 a2Var) {
        for (d dVar : this.k) {
            if (a2Var == dVar.b) {
                dVar.a().requestFocus();
                return true;
            }
        }
        if (!a2Var.hasVisibleItems()) {
            return false;
        }
        o(a2Var);
        v1.a aVar = this.z;
        if (aVar != null) {
            aVar.b(a2Var);
        }
        return true;
    }

    @Override // defpackage.v1
    public void n(boolean z) {
        Iterator<d> it = this.k.iterator();
        while (it.hasNext()) {
            t1.B(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // defpackage.t1
    public void o(p1 p1Var) {
        p1Var.c(this, this.d);
        if (b()) {
            I(p1Var);
        } else {
            this.j.add(p1Var);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        d dVar;
        int size = this.k.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                dVar = null;
                break;
            }
            dVar = this.k.get(i);
            if (!dVar.a.b()) {
                break;
            } else {
                i++;
            }
        }
        if (dVar != null) {
            dVar.b.e(false);
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

    @Override // defpackage.t1
    public boolean p() {
        return false;
    }

    @Override // defpackage.t1
    public void s(View view) {
        if (this.q != view) {
            this.q = view;
            this.p = d9.b(this.o, t9.z(view));
        }
    }

    @Override // defpackage.t1
    public void u(boolean z) {
        this.x = z;
    }

    @Override // defpackage.t1
    public void v(int i) {
        if (this.o != i) {
            this.o = i;
            this.p = d9.b(i, t9.z(this.q));
        }
    }

    @Override // defpackage.t1
    public void w(int i) {
        this.t = true;
        this.v = i;
    }

    @Override // defpackage.t1
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.B = onDismissListener;
    }

    @Override // defpackage.t1
    public void y(boolean z) {
        this.y = z;
    }

    @Override // defpackage.t1
    public void z(int i) {
        this.u = true;
        this.w = i;
    }
}
