package a.b.o.i;

import a.b.o.i.m;
import a.b.p.l0;
import a.b.p.m0;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {
    public static final int B = a.b.g.abc_cascading_menu_item_layout;
    public boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f147b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f149d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f150e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f151f;
    public final Handler g;
    public View o;
    public View p;
    public int q;
    public boolean r;
    public boolean s;
    public int t;
    public int u;
    public boolean w;
    public m.a x;
    public ViewTreeObserver y;
    public PopupWindow.OnDismissListener z;
    public final List<g> h = new ArrayList();
    public final List<C0002d> i = new ArrayList();
    public final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    public final View.OnAttachStateChangeListener k = new b();
    public final l0 l = new c();
    public int m = 0;
    public int n = 0;
    public boolean v = false;

    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.a() || d.this.i.size() <= 0 || d.this.i.get(0).f159a.A) {
                return;
            }
            View view = d.this.p;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0002d> it = d.this.i.iterator();
            while (it.hasNext()) {
                it.next().f159a.i();
            }
        }
    }

    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = d.this.y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.y = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.y.removeGlobalOnLayoutListener(dVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public class c implements l0 {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ C0002d f155a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ MenuItem f156b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ g f157c;

            public a(C0002d c0002d, MenuItem menuItem, g gVar) {
                this.f155a = c0002d;
                this.f156b = menuItem;
                this.f157c = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0002d c0002d = this.f155a;
                if (c0002d != null) {
                    d.this.A = true;
                    c0002d.f160b.c(false);
                    d.this.A = false;
                }
                if (this.f156b.isEnabled() && this.f156b.hasSubMenu()) {
                    this.f157c.r(this.f156b, 4);
                }
            }
        }

        public c() {
        }

        @Override // a.b.p.l0
        public void g(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(null);
            int size = d.this.i.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (gVar == d.this.i.get(i).f160b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            d.this.g.postAtTime(new a(i2 < d.this.i.size() ? d.this.i.get(i2) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // a.b.p.l0
        public void k(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: a.b.o.i.d$d, reason: collision with other inner class name */
    public static class C0002d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m0 f159a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f160b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f161c;

        public C0002d(m0 m0Var, g gVar, int i) {
            this.f159a = m0Var;
            this.f160b = gVar;
            this.f161c = i;
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.f147b = context;
        this.o = view;
        this.f149d = i;
        this.f150e = i2;
        this.f151f = z;
        this.q = a.h.k.q.p(view) != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f148c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(a.b.d.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    @Override // a.b.o.i.p
    public boolean a() {
        return this.i.size() > 0 && this.i.get(0).f159a.a();
    }

    @Override // a.b.o.i.m
    public void b(g gVar, boolean z) {
        int size = this.i.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (gVar == this.i.get(i).f160b) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            return;
        }
        int i2 = i + 1;
        if (i2 < this.i.size()) {
            this.i.get(i2).f160b.c(false);
        }
        C0002d c0002dRemove = this.i.remove(i);
        c0002dRemove.f160b.u(this);
        if (this.A) {
            c0002dRemove.f159a.B.setExitTransition(null);
            c0002dRemove.f159a.B.setAnimationStyle(0);
        }
        c0002dRemove.f159a.dismiss();
        int size2 = this.i.size();
        this.q = size2 > 0 ? this.i.get(size2 - 1).f161c : a.h.k.q.p(this.o) == 1 ? 0 : 1;
        if (size2 != 0) {
            if (z) {
                this.i.get(0).f160b.c(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.b(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.y.removeGlobalOnLayoutListener(this.j);
            }
            this.y = null;
        }
        this.p.removeOnAttachStateChangeListener(this.k);
        this.z.onDismiss();
    }

    @Override // a.b.o.i.m
    public void d(m.a aVar) {
        this.x = aVar;
    }

    @Override // a.b.o.i.p
    public void dismiss() {
        int size = this.i.size();
        if (size > 0) {
            C0002d[] c0002dArr = (C0002d[]) this.i.toArray(new C0002d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0002d c0002d = c0002dArr[i];
                if (c0002d.f159a.a()) {
                    c0002d.f159a.dismiss();
                }
            }
        }
    }

    @Override // a.b.o.i.p
    public ListView e() {
        if (this.i.isEmpty()) {
            return null;
        }
        return this.i.get(r1.size() - 1).f159a.f305c;
    }

    @Override // a.b.o.i.m
    public boolean f(r rVar) {
        for (C0002d c0002d : this.i) {
            if (rVar == c0002d.f160b) {
                c0002d.f159a.f305c.requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        rVar.b(this, this.f147b);
        if (a()) {
            w(rVar);
        } else {
            this.h.add(rVar);
        }
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.c(rVar);
        }
        return true;
    }

    @Override // a.b.o.i.m
    public boolean g() {
        return false;
    }

    @Override // a.b.o.i.m
    public void h(boolean z) {
        Iterator<C0002d> it = this.i.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = it.next().f159a.f305c.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((f) adapter).notifyDataSetChanged();
        }
    }

    @Override // a.b.o.i.p
    public void i() {
        if (a()) {
            return;
        }
        Iterator<g> it = this.h.iterator();
        while (it.hasNext()) {
            w(it.next());
        }
        this.h.clear();
        View view = this.o;
        this.p = view;
        if (view != null) {
            boolean z = this.y == null;
            ViewTreeObserver viewTreeObserver = this.p.getViewTreeObserver();
            this.y = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    @Override // a.b.o.i.k
    public void l(g gVar) {
        gVar.b(this, this.f147b);
        if (a()) {
            w(gVar);
        } else {
            this.h.add(gVar);
        }
    }

    @Override // a.b.o.i.k
    public boolean m() {
        return false;
    }

    @Override // a.b.o.i.k
    public void o(View view) {
        if (this.o != view) {
            this.o = view;
            this.n = Gravity.getAbsoluteGravity(this.m, a.h.k.q.p(view));
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0002d c0002d;
        int size = this.i.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                c0002d = null;
                break;
            }
            c0002d = this.i.get(i);
            if (!c0002d.f159a.a()) {
                break;
            } else {
                i++;
            }
        }
        if (c0002d != null) {
            c0002d.f160b.c(false);
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

    @Override // a.b.o.i.k
    public void p(boolean z) {
        this.v = z;
    }

    @Override // a.b.o.i.k
    public void q(int i) {
        if (this.m != i) {
            this.m = i;
            this.n = Gravity.getAbsoluteGravity(i, a.h.k.q.p(this.o));
        }
    }

    @Override // a.b.o.i.k
    public void r(int i) {
        this.r = true;
        this.t = i;
    }

    @Override // a.b.o.i.k
    public void s(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }

    @Override // a.b.o.i.k
    public void t(boolean z) {
        this.w = z;
    }

    @Override // a.b.o.i.k
    public void u(int i) {
        this.s = true;
        this.u = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w(a.b.o.i.g r17) {
        /*
            Method dump skipped, instruction units count: 443
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.o.i.d.w(a.b.o.i.g):void");
    }
}
