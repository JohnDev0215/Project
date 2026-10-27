package q;

import F0.C;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.stingray.drivemodedetector.R;
import h2.AbstractC1347d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import l3.C1764u;
import r.g0;
import r.j0;

/* JADX INFO: renamed from: q.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnKeyListenerC2148e extends AbstractC2153j implements View.OnKeyListener, PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f21021A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f21022C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f21023D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f21024E;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public boolean f21026G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public InterfaceC2156m f21027H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public ViewTreeObserver f21028I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public C2154k f21029J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f21030K;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f21031m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f21032n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f21033p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Handler f21034q;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f21042y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f21043z;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f21035r = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final ArrayList f21036s = new ArrayList();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ViewTreeObserverOnGlobalLayoutListenerC2146c f21037t = new ViewTreeObserverOnGlobalLayoutListenerC2146c(this, 0);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C f21038u = new C(this, 3);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C1764u f21039v = new C1764u(this, 15);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f21040w = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f21041x = 0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f21025F = false;

    public ViewOnKeyListenerC2148e(Context context, View view, int i7, boolean z6) {
        this.f21031m = context;
        this.f21042y = view;
        this.o = i7;
        this.f21033p = z6;
        this.f21021A = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f21032n = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f21034q = new Handler();
    }

    @Override // q.InterfaceC2157n
    public final void a(MenuC2151h menuC2151h, boolean z6) {
        ArrayList arrayList = this.f21036s;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                i7 = -1;
                break;
            } else if (menuC2151h == ((C2147d) arrayList.get(i7)).f21020b) {
                break;
            } else {
                i7++;
            }
        }
        if (i7 < 0) {
            return;
        }
        int i10 = i7 + 1;
        if (i10 < arrayList.size()) {
            ((C2147d) arrayList.get(i10)).f21020b.c(false);
        }
        C2147d c2147d = (C2147d) arrayList.remove(i7);
        CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = c2147d.f21020b.f21063s;
        for (WeakReference weakReference : copyOnWriteArrayList) {
            InterfaceC2157n interfaceC2157n = (InterfaceC2157n) weakReference.get();
            if (interfaceC2157n == null || interfaceC2157n == this) {
                copyOnWriteArrayList.remove(weakReference);
            }
        }
        boolean z10 = this.f21030K;
        j0 j0Var = c2147d.f21019a;
        if (z10) {
            g0.b(j0Var.f21505G, null);
            j0Var.f21505G.setAnimationStyle(0);
        }
        j0Var.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.f21021A = ((C2147d) arrayList.get(size2 - 1)).c;
        } else {
            this.f21021A = this.f21042y.getLayoutDirection() == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z6) {
                ((C2147d) arrayList.get(0)).f21020b.c(false);
                return;
            }
            return;
        }
        dismiss();
        InterfaceC2156m interfaceC2156m = this.f21027H;
        if (interfaceC2156m != null) {
            interfaceC2156m.a(menuC2151h, true);
        }
        ViewTreeObserver viewTreeObserver = this.f21028I;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.f21028I.removeGlobalOnLayoutListener(this.f21037t);
            }
            this.f21028I = null;
        }
        this.f21043z.removeOnAttachStateChangeListener(this.f21038u);
        this.f21029J.onDismiss();
    }

    @Override // q.InterfaceC2157n
    public final boolean b(SubMenuC2161r subMenuC2161r) {
        for (C2147d c2147d : this.f21036s) {
            if (subMenuC2161r == c2147d.f21020b) {
                c2147d.f21019a.f21508n.requestFocus();
                return true;
            }
        }
        if (!subMenuC2161r.hasVisibleItems()) {
            return false;
        }
        l(subMenuC2161r);
        InterfaceC2156m interfaceC2156m = this.f21027H;
        if (interfaceC2156m != null) {
            interfaceC2156m.v(subMenuC2161r);
        }
        return true;
    }

    @Override // q.InterfaceC2159p
    public final void c() {
        if (j()) {
            return;
        }
        ArrayList arrayList = this.f21035r;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            v((MenuC2151h) it.next());
        }
        arrayList.clear();
        View view = this.f21042y;
        this.f21043z = view;
        if (view != null) {
            boolean z6 = this.f21028I == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.f21028I = viewTreeObserver;
            if (z6) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f21037t);
            }
            this.f21043z.addOnAttachStateChangeListener(this.f21038u);
        }
    }

    @Override // q.InterfaceC2157n
    public final void d() {
        Iterator it = this.f21036s.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((C2147d) it.next()).f21019a.f21508n.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((C2149f) adapter).notifyDataSetChanged();
        }
    }

    @Override // q.InterfaceC2159p
    public final void dismiss() {
        ArrayList arrayList = this.f21036s;
        int size = arrayList.size();
        if (size > 0) {
            C2147d[] c2147dArr = (C2147d[]) arrayList.toArray(new C2147d[size]);
            for (int i7 = size - 1; i7 >= 0; i7--) {
                C2147d c2147d = c2147dArr[i7];
                if (c2147d.f21019a.f21505G.isShowing()) {
                    c2147d.f21019a.dismiss();
                }
            }
        }
    }

    @Override // q.InterfaceC2159p
    public final ListView e() {
        ArrayList arrayList = this.f21036s;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((C2147d) AbstractC1347d.e(1, arrayList)).f21019a.f21508n;
    }

    @Override // q.InterfaceC2157n
    public final boolean g() {
        return false;
    }

    @Override // q.InterfaceC2157n
    public final void i(InterfaceC2156m interfaceC2156m) {
        this.f21027H = interfaceC2156m;
    }

    @Override // q.InterfaceC2159p
    public final boolean j() {
        ArrayList arrayList = this.f21036s;
        return arrayList.size() > 0 && ((C2147d) arrayList.get(0)).f21019a.f21505G.isShowing();
    }

    @Override // q.AbstractC2153j
    public final void l(MenuC2151h menuC2151h) {
        menuC2151h.b(this, this.f21031m);
        if (j()) {
            v(menuC2151h);
        } else {
            this.f21035r.add(menuC2151h);
        }
    }

    @Override // q.AbstractC2153j
    public final void n(View view) {
        if (this.f21042y != view) {
            this.f21042y = view;
            this.f21041x = Gravity.getAbsoluteGravity(this.f21040w, view.getLayoutDirection());
        }
    }

    @Override // q.AbstractC2153j
    public final void o(boolean z6) {
        this.f21025F = z6;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        C2147d c2147d;
        ArrayList arrayList = this.f21036s;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                c2147d = null;
                break;
            }
            c2147d = (C2147d) arrayList.get(i7);
            if (!c2147d.f21019a.f21505G.isShowing()) {
                break;
            } else {
                i7++;
            }
        }
        if (c2147d != null) {
            c2147d.f21020b.c(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i7, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i7 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // q.AbstractC2153j
    public final void p(int i7) {
        if (this.f21040w != i7) {
            this.f21040w = i7;
            this.f21041x = Gravity.getAbsoluteGravity(i7, this.f21042y.getLayoutDirection());
        }
    }

    @Override // q.AbstractC2153j
    public final void q(int i7) {
        this.B = true;
        this.f21023D = i7;
    }

    @Override // q.AbstractC2153j
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.f21029J = (C2154k) onDismissListener;
    }

    @Override // q.AbstractC2153j
    public final void s(boolean z6) {
        this.f21026G = z6;
    }

    @Override // q.AbstractC2153j
    public final void t(int i7) {
        this.f21022C = true;
        this.f21024E = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(q.MenuC2151h r17) {
        /*
            Method dump skipped, instruction units count: 457
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q.ViewOnKeyListenerC2148e.v(q.h):void");
    }
}
