package androidx.appcompat.view.menu;

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
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.widget.H;
import androidx.appcompat.widget.I;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class CascadingMenuPopup extends j implements l, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int D = R$layout.abc_cascading_menu_item_layout;
    ViewTreeObserver A;
    private PopupWindow.OnDismissListener B;
    boolean C;
    private final Context d;
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
    private l.a z;
    private final List<f> j = new ArrayList();
    final List<d> k = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    private final View.OnAttachStateChangeListener m = new b();
    private final H n = new c();
    private int o = 0;
    private int p = 0;
    private boolean x = false;
    private int s = D();

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizPosition {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!CascadingMenuPopup.this.b() || CascadingMenuPopup.this.k.size() <= 0 || CascadingMenuPopup.this.k.get(0).f207a.x()) {
                return;
            }
            View view = CascadingMenuPopup.this.r;
            if (view == null || !view.isShown()) {
                CascadingMenuPopup.this.dismiss();
                return;
            }
            Iterator<d> it = CascadingMenuPopup.this.k.iterator();
            while (it.hasNext()) {
                it.next().f207a.f();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = CascadingMenuPopup.this.A;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    CascadingMenuPopup.this.A = view.getViewTreeObserver();
                }
                CascadingMenuPopup cascadingMenuPopup = CascadingMenuPopup.this;
                cascadingMenuPopup.A.removeGlobalOnLayoutListener(cascadingMenuPopup.l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements H {

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ d f206c;
            final /* synthetic */ MenuItem d;
            final /* synthetic */ f e;

            a(d dVar, MenuItem menuItem, f fVar) {
                this.f206c = dVar;
                this.d = menuItem;
                this.e = fVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                d dVar = this.f206c;
                if (dVar != null) {
                    CascadingMenuPopup.this.C = true;
                    dVar.f208b.e(false);
                    CascadingMenuPopup.this.C = false;
                }
                if (this.d.isEnabled() && this.d.hasSubMenu()) {
                    this.e.L(this.d, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.H
        public void e(@NonNull f fVar, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.i.removeCallbacksAndMessages(null);
            int size = CascadingMenuPopup.this.k.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (fVar == CascadingMenuPopup.this.k.get(i).f208b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            CascadingMenuPopup.this.i.postAtTime(new a(i2 < CascadingMenuPopup.this.k.size() ? CascadingMenuPopup.this.k.get(i2) : null, menuItem, fVar), fVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.H
        public void h(@NonNull f fVar, @NonNull MenuItem menuItem) {
            CascadingMenuPopup.this.i.removeCallbacksAndMessages(fVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final I f207a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f f208b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f209c;

        public d(@NonNull I i, @NonNull f fVar, int i2) {
            this.f207a = i;
            this.f208b = fVar;
            this.f209c = i2;
        }

        public ListView a() {
            return this.f207a.k();
        }
    }

    public CascadingMenuPopup(@NonNull Context context, @NonNull View view, @AttrRes int i, @StyleRes int i2, boolean z) {
        this.d = context;
        this.q = view;
        this.f = i;
        this.g = i2;
        this.h = z;
        Resources resources = context.getResources();
        this.e = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.i = new Handler();
    }

    private int A(@NonNull f fVar) {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (fVar == this.k.get(i).f208b) {
                return i;
            }
        }
        return -1;
    }

    private MenuItem B(@NonNull f fVar, @NonNull f fVar2) {
        int size = fVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = fVar.getItem(i);
            if (item.hasSubMenu() && fVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Nullable
    private View C(@NonNull d dVar, @NonNull f fVar) {
        e eVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemB = B(dVar.f208b, fVar);
        if (menuItemB == null) {
            return null;
        }
        ListView listViewA = dVar.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            eVar = (e) headerViewListAdapter.getWrappedAdapter();
        } else {
            eVar = (e) adapter;
            headersCount = 0;
        }
        int count = eVar.getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            }
            if (menuItemB == eVar.getItem(i)) {
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
        return ViewCompat.q(this.q) == 1 ? 0 : 1;
    }

    private int E(int i) {
        List<d> list = this.k;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.r.getWindowVisibleDisplayFrame(rect);
        return this.s == 1 ? (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1 : iArr[0] - i < 0 ? 1 : 0;
    }

    private void F(@NonNull f fVar) {
        d dVar;
        View viewC;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.d);
        e eVar = new e(fVar, layoutInflaterFrom, this.h, D);
        if (!b() && this.x) {
            eVar.d(true);
        } else if (b()) {
            eVar.d(j.x(fVar));
        }
        int iO = j.o(eVar, null, this.d, this.e);
        I iZ = z();
        iZ.o(eVar);
        iZ.B(iO);
        iZ.C(this.p);
        if (this.k.size() > 0) {
            List<d> list = this.k;
            dVar = list.get(list.size() - 1);
            viewC = C(dVar, fVar);
        } else {
            dVar = null;
            viewC = null;
        }
        if (viewC != null) {
            iZ.Q(false);
            iZ.N(null);
            int iE = E(iO);
            boolean z = iE == 1;
            this.s = iE;
            if (Build.VERSION.SDK_INT >= 26) {
                iZ.z(viewC);
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
            iZ.a(i3);
            iZ.I(true);
            iZ.n(i2);
        } else {
            if (this.t) {
                iZ.a(this.v);
            }
            if (this.u) {
                iZ.n(this.w);
            }
            iZ.D(n());
        }
        this.k.add(new d(iZ, fVar, this.s));
        iZ.f();
        ListView listViewK = iZ.k();
        listViewK.setOnKeyListener(this);
        if (dVar == null && this.y && fVar.x() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewK, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(fVar.x());
            listViewK.addHeaderView(frameLayout, null, false);
            iZ.f();
        }
    }

    private I z() {
        I i = new I(this.d, null, this.f, this.g);
        i.P(this.n);
        i.H(this);
        i.G(this);
        i.z(this.q);
        i.C(this.p);
        i.F(true);
        i.E(2);
        return i;
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(f fVar, boolean z) {
        int iA = A(fVar);
        if (iA < 0) {
            return;
        }
        int i = iA + 1;
        if (i < this.k.size()) {
            this.k.get(i).f208b.e(false);
        }
        d dVarRemove = this.k.remove(iA);
        dVarRemove.f208b.O(this);
        if (this.C) {
            dVarRemove.f207a.O(null);
            dVarRemove.f207a.A(0);
        }
        dVarRemove.f207a.dismiss();
        int size = this.k.size();
        if (size > 0) {
            this.s = this.k.get(size - 1).f209c;
        } else {
            this.s = D();
        }
        if (size != 0) {
            if (z) {
                this.k.get(0).f208b.e(false);
                return;
            }
            return;
        }
        dismiss();
        l.a aVar = this.z;
        if (aVar != null) {
            aVar.a(fVar, true);
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

    @Override // androidx.appcompat.view.menu.o
    public boolean b() {
        return this.k.size() > 0 && this.k.get(0).f207a.b();
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.o
    public void dismiss() {
        int size = this.k.size();
        if (size > 0) {
            d[] dVarArr = (d[]) this.k.toArray(new d[size]);
            for (int i = size - 1; i >= 0; i--) {
                d dVar = dVarArr[i];
                if (dVar.f207a.b()) {
                    dVar.f207a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public void f() {
        if (b()) {
            return;
        }
        Iterator<f> it = this.j.iterator();
        while (it.hasNext()) {
            F(it.next());
        }
        this.j.clear();
        View view = this.q;
        this.r = view;
        if (view != null) {
            boolean z = this.A == null;
            ViewTreeObserver viewTreeObserver = this.r.getViewTreeObserver();
            this.A = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.l);
            }
            this.r.addOnAttachStateChangeListener(this.m);
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void h(l.a aVar) {
        this.z = aVar;
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean i(q qVar) {
        for (d dVar : this.k) {
            if (qVar == dVar.f208b) {
                dVar.a().requestFocus();
                return true;
            }
        }
        if (!qVar.hasVisibleItems()) {
            return false;
        }
        l(qVar);
        l.a aVar = this.z;
        if (aVar != null) {
            aVar.b(qVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.l
    public void j(boolean z) {
        Iterator<d> it = this.k.iterator();
        while (it.hasNext()) {
            j.y(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public ListView k() {
        if (this.k.isEmpty()) {
            return null;
        }
        return this.k.get(r1.size() - 1).a();
    }

    @Override // androidx.appcompat.view.menu.j
    public void l(f fVar) {
        fVar.c(this, this.d);
        if (b()) {
            F(fVar);
        } else {
            this.j.add(fVar);
        }
    }

    @Override // androidx.appcompat.view.menu.j
    protected boolean m() {
        return false;
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
            if (!dVar.f207a.b()) {
                break;
            } else {
                i++;
            }
        }
        if (dVar != null) {
            dVar.f208b.e(false);
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

    @Override // androidx.appcompat.view.menu.j
    public void p(@NonNull View view) {
        if (this.q != view) {
            this.q = view;
            this.p = androidx.core.view.d.a(this.o, ViewCompat.q(view));
        }
    }

    @Override // androidx.appcompat.view.menu.j
    public void r(boolean z) {
        this.x = z;
    }

    @Override // androidx.appcompat.view.menu.j
    public void s(int i) {
        if (this.o != i) {
            this.o = i;
            this.p = androidx.core.view.d.a(i, ViewCompat.q(this.q));
        }
    }

    @Override // androidx.appcompat.view.menu.j
    public void t(int i) {
        this.t = true;
        this.v = i;
    }

    @Override // androidx.appcompat.view.menu.j
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.B = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.j
    public void v(boolean z) {
        this.y = z;
    }

    @Override // androidx.appcompat.view.menu.j
    public void w(int i) {
        this.u = true;
        this.w = i;
    }
}
