package q;

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
import o0.t;
import q.m;
import r.u;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f7080e = R$layout.abc_cascading_menu_item_layout;
    public boolean A;
    public m.a B;
    public ViewTreeObserver C;
    public PopupWindow.OnDismissListener D;
    public boolean E;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f7081f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f7082g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f7083h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f7084i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f7085j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Handler f7086k;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public View f7094s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public View f7095t;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f7097v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f7098w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f7099x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7100y;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List<g> f7087l = new ArrayList();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List<C0105d> f7088m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f7089n = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final View.OnAttachStateChangeListener f7090o = new b();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final u f7091p = new c();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f7092q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f7093r = 0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f7101z = false;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f7096u = G();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.c() || d.this.f7088m.size() <= 0 || d.this.f7088m.get(0).f7109a.B()) {
                return;
            }
            View view = d.this.f7095t;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0105d> it = d.this.f7088m.iterator();
            while (it.hasNext()) {
                it.next().f7109a.e();
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
            ViewTreeObserver viewTreeObserver = d.this.C;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.C = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.C.removeGlobalOnLayoutListener(dVar.f7089n);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public class c implements u {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ C0105d f7105d;

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            public final /* synthetic */ MenuItem f7106e;

            /* JADX INFO: renamed from: f, reason: collision with root package name */
            public final /* synthetic */ g f7107f;

            public a(C0105d c0105d, MenuItem menuItem, g gVar) {
                this.f7105d = c0105d;
                this.f7106e = menuItem;
                this.f7107f = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0105d c0105d = this.f7105d;
                if (c0105d != null) {
                    d.this.E = true;
                    c0105d.f7110b.e(false);
                    d.this.E = false;
                }
                if (this.f7106e.isEnabled() && this.f7106e.hasSubMenu()) {
                    this.f7107f.N(this.f7106e, 4);
                }
            }
        }

        public c() {
        }

        @Override // r.u
        public void a(g gVar, MenuItem menuItem) {
            d.this.f7086k.removeCallbacksAndMessages(null);
            int size = d.this.f7088m.size();
            int i8 = 0;
            while (true) {
                if (i8 >= size) {
                    i8 = -1;
                    break;
                } else if (gVar == d.this.f7088m.get(i8).f7110b) {
                    break;
                } else {
                    i8++;
                }
            }
            if (i8 == -1) {
                return;
            }
            int i9 = i8 + 1;
            d.this.f7086k.postAtTime(new a(i9 < d.this.f7088m.size() ? d.this.f7088m.get(i9) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // r.u
        public void f(g gVar, MenuItem menuItem) {
            d.this.f7086k.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: q.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    public static class C0105d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f7109a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f7110b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f7111c;

        public C0105d(MenuPopupWindow menuPopupWindow, g gVar, int i8) {
            this.f7109a = menuPopupWindow;
            this.f7110b = gVar;
            this.f7111c = i8;
        }

        public ListView a() {
            return this.f7109a.h();
        }
    }

    public d(Context context, View view, int i8, int i9, boolean z7) {
        this.f7081f = context;
        this.f7094s = view;
        this.f7083h = i8;
        this.f7084i = i9;
        this.f7085j = z7;
        Resources resources = context.getResources();
        this.f7082g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.f7086k = new Handler();
    }

    public final MenuPopupWindow C() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f7081f, null, this.f7083h, this.f7084i);
        menuPopupWindow.T(this.f7091p);
        menuPopupWindow.L(this);
        menuPopupWindow.K(this);
        menuPopupWindow.D(this.f7094s);
        menuPopupWindow.G(this.f7093r);
        menuPopupWindow.J(true);
        menuPopupWindow.I(2);
        return menuPopupWindow;
    }

    public final int D(g gVar) {
        int size = this.f7088m.size();
        for (int i8 = 0; i8 < size; i8++) {
            if (gVar == this.f7088m.get(i8).f7110b) {
                return i8;
            }
        }
        return -1;
    }

    public final MenuItem E(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i8 = 0; i8 < size; i8++) {
            MenuItem item = gVar.getItem(i8);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    public final View F(C0105d c0105d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemE = E(c0105d.f7110b, gVar);
        if (menuItemE == null) {
            return null;
        }
        ListView listViewA = c0105d.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i8 = 0;
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
            if (i8 >= count) {
                i8 = -1;
                break;
            }
            if (menuItemE == fVar.getItem(i8)) {
                break;
            }
            i8++;
        }
        if (i8 != -1 && (firstVisiblePosition = (i8 + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    public final int G() {
        return t.A(this.f7094s) == 1 ? 0 : 1;
    }

    public final int H(int i8) {
        List<C0105d> list = this.f7088m;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.f7095t.getWindowVisibleDisplayFrame(rect);
        return this.f7096u == 1 ? (iArr[0] + listViewA.getWidth()) + i8 > rect.right ? 0 : 1 : iArr[0] - i8 < 0 ? 1 : 0;
    }

    public final void I(g gVar) {
        C0105d c0105d;
        View viewF;
        int i8;
        int i9;
        int i10;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f7081f);
        f fVar = new f(gVar, layoutInflaterFrom, this.f7085j, f7080e);
        if (!c() && this.f7101z) {
            fVar.d(true);
        } else if (c()) {
            fVar.d(k.A(gVar));
        }
        int iR = k.r(fVar, null, this.f7081f, this.f7082g);
        MenuPopupWindow menuPopupWindowC = C();
        menuPopupWindowC.p(fVar);
        menuPopupWindowC.F(iR);
        menuPopupWindowC.G(this.f7093r);
        if (this.f7088m.size() > 0) {
            List<C0105d> list = this.f7088m;
            c0105d = list.get(list.size() - 1);
            viewF = F(c0105d, gVar);
        } else {
            c0105d = null;
            viewF = null;
        }
        if (viewF != null) {
            menuPopupWindowC.U(false);
            menuPopupWindowC.R(null);
            int iH = H(iR);
            boolean z7 = iH == 1;
            this.f7096u = iH;
            if (Build.VERSION.SDK_INT >= 26) {
                menuPopupWindowC.D(viewF);
                i9 = 0;
                i8 = 0;
            } else {
                int[] iArr = new int[2];
                this.f7094s.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewF.getLocationOnScreen(iArr2);
                if ((this.f7093r & 7) == 5) {
                    iArr[0] = iArr[0] + this.f7094s.getWidth();
                    iArr2[0] = iArr2[0] + viewF.getWidth();
                }
                i8 = iArr2[0] - iArr[0];
                i9 = iArr2[1] - iArr[1];
            }
            if ((this.f7093r & 5) == 5) {
                if (!z7) {
                    iR = viewF.getWidth();
                    i10 = i8 - iR;
                }
                i10 = i8 + iR;
            } else {
                if (z7) {
                    iR = viewF.getWidth();
                    i10 = i8 + iR;
                }
                i10 = i8 - iR;
            }
            menuPopupWindowC.l(i10);
            menuPopupWindowC.M(true);
            menuPopupWindowC.j(i9);
        } else {
            if (this.f7097v) {
                menuPopupWindowC.l(this.f7099x);
            }
            if (this.f7098w) {
                menuPopupWindowC.j(this.f7100y);
            }
            menuPopupWindowC.H(q());
        }
        this.f7088m.add(new C0105d(menuPopupWindowC, gVar, this.f7096u));
        menuPopupWindowC.e();
        ListView listViewH = menuPopupWindowC.h();
        listViewH.setOnKeyListener(this);
        if (c0105d == null && this.A && gVar.z() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewH, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.z());
            listViewH.addHeaderView(frameLayout, null, false);
            menuPopupWindowC.e();
        }
    }

    @Override // q.m
    public void a(g gVar, boolean z7) {
        int iD = D(gVar);
        if (iD < 0) {
            return;
        }
        int i8 = iD + 1;
        if (i8 < this.f7088m.size()) {
            this.f7088m.get(i8).f7110b.e(false);
        }
        C0105d c0105dRemove = this.f7088m.remove(iD);
        c0105dRemove.f7110b.Q(this);
        if (this.E) {
            c0105dRemove.f7109a.S(null);
            c0105dRemove.f7109a.E(0);
        }
        c0105dRemove.f7109a.dismiss();
        int size = this.f7088m.size();
        if (size > 0) {
            this.f7096u = this.f7088m.get(size - 1).f7111c;
        } else {
            this.f7096u = G();
        }
        if (size != 0) {
            if (z7) {
                this.f7088m.get(0).f7110b.e(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.B;
        if (aVar != null) {
            aVar.a(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.C;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.C.removeGlobalOnLayoutListener(this.f7089n);
            }
            this.C = null;
        }
        this.f7095t.removeOnAttachStateChangeListener(this.f7090o);
        this.D.onDismiss();
    }

    @Override // q.p
    public boolean c() {
        return this.f7088m.size() > 0 && this.f7088m.get(0).f7109a.c();
    }

    @Override // q.m
    public void d(Parcelable parcelable) {
    }

    @Override // q.p
    public void dismiss() {
        int size = this.f7088m.size();
        if (size > 0) {
            C0105d[] c0105dArr = (C0105d[]) this.f7088m.toArray(new C0105d[size]);
            for (int i8 = size - 1; i8 >= 0; i8--) {
                C0105d c0105d = c0105dArr[i8];
                if (c0105d.f7109a.c()) {
                    c0105d.f7109a.dismiss();
                }
            }
        }
    }

    @Override // q.p
    public void e() {
        if (c()) {
            return;
        }
        Iterator<g> it = this.f7087l.iterator();
        while (it.hasNext()) {
            I(it.next());
        }
        this.f7087l.clear();
        View view = this.f7094s;
        this.f7095t = view;
        if (view != null) {
            boolean z7 = this.C == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.C = viewTreeObserver;
            if (z7) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f7089n);
            }
            this.f7095t.addOnAttachStateChangeListener(this.f7090o);
        }
    }

    @Override // q.m
    public boolean f(r rVar) {
        for (C0105d c0105d : this.f7088m) {
            if (rVar == c0105d.f7110b) {
                c0105d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        o(rVar);
        m.a aVar = this.B;
        if (aVar != null) {
            aVar.b(rVar);
        }
        return true;
    }

    @Override // q.m
    public void g(boolean z7) {
        Iterator<C0105d> it = this.f7088m.iterator();
        while (it.hasNext()) {
            k.B(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // q.p
    public ListView h() {
        if (this.f7088m.isEmpty()) {
            return null;
        }
        return this.f7088m.get(r0.size() - 1).a();
    }

    @Override // q.m
    public boolean j() {
        return false;
    }

    @Override // q.m
    public Parcelable k() {
        return null;
    }

    @Override // q.m
    public void n(m.a aVar) {
        this.B = aVar;
    }

    @Override // q.k
    public void o(g gVar) {
        gVar.c(this, this.f7081f);
        if (c()) {
            I(gVar);
        } else {
            this.f7087l.add(gVar);
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0105d c0105d;
        int size = this.f7088m.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size) {
                c0105d = null;
                break;
            }
            c0105d = this.f7088m.get(i8);
            if (!c0105d.f7109a.c()) {
                break;
            } else {
                i8++;
            }
        }
        if (c0105d != null) {
            c0105d.f7110b.e(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i8, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i8 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // q.k
    public boolean p() {
        return false;
    }

    @Override // q.k
    public void s(View view) {
        if (this.f7094s != view) {
            this.f7094s = view;
            this.f7093r = o0.d.b(this.f7092q, t.A(view));
        }
    }

    @Override // q.k
    public void u(boolean z7) {
        this.f7101z = z7;
    }

    @Override // q.k
    public void v(int i8) {
        if (this.f7092q != i8) {
            this.f7092q = i8;
            this.f7093r = o0.d.b(i8, t.A(this.f7094s));
        }
    }

    @Override // q.k
    public void w(int i8) {
        this.f7097v = true;
        this.f7099x = i8;
    }

    @Override // q.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.D = onDismissListener;
    }

    @Override // q.k
    public void y(boolean z7) {
        this.A = z7;
    }

    @Override // q.k
    public void z(int i8) {
        this.f7098w = true;
        this.f7100y = i8;
    }
}
