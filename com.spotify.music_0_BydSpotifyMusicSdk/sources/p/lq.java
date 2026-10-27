package p;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import com.spotify.music.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class lq extends ej1 implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public boolean A;
    public int B;
    public int C;
    public boolean E;
    public nj1 F;
    public ViewTreeObserver G;
    public PopupWindow.OnDismissListener H;
    public boolean I;
    public final Context e;
    public final int k;
    public final int l;
    public final int m;
    public final boolean n;
    public final Handler o;
    public final hq r;
    public final iq s;
    public View w;
    public View x;
    public int y;
    public boolean z;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f149p = new ArrayList();
    public final ArrayList q = new ArrayList();
    public final hk0 t = new hk0(4, this);
    public int u = 0;
    public int v = 0;
    public boolean D = false;

    public lq(Context context, View view, int i, int i2, boolean z) {
        this.r = new hq(i, this);
        this.s = new iq(this, i);
        this.e = context;
        this.w = view;
        this.l = i;
        this.m = i2;
        this.n = z;
        WeakHashMap weakHashMap = zt2.a;
        this.y = jt2.d(view) != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.k = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.o = new Handler();
    }

    @Override // p.jd2
    public final boolean a() {
        ArrayList arrayList = this.q;
        return arrayList.size() > 0 && ((kq) arrayList.get(0)).a.G.isShowing();
    }

    @Override // p.oj1
    public final void b(si1 si1Var, boolean z) {
        ArrayList arrayList = this.q;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (si1Var == ((kq) arrayList.get(i)).b) {
                break;
            } else {
                i++;
            }
        }
        if (i < 0) {
            return;
        }
        int i2 = i + 1;
        if (i2 < arrayList.size()) {
            ((kq) arrayList.get(i2)).b.c(false);
        }
        kq kqVar = (kq) arrayList.remove(i);
        kqVar.b.r(this);
        boolean z2 = this.I;
        mj1 mj1Var = kqVar.a;
        if (z2) {
            ij1.b(mj1Var.G, null);
            mj1Var.G.setAnimationStyle(0);
        }
        mj1Var.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.y = ((kq) arrayList.get(size2 - 1)).c;
        } else {
            View view = this.w;
            WeakHashMap weakHashMap = zt2.a;
            this.y = jt2.d(view) == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z) {
                ((kq) arrayList.get(0)).b.c(false);
                return;
            }
            return;
        }
        dismiss();
        nj1 nj1Var = this.F;
        if (nj1Var != null) {
            nj1Var.b(si1Var, true);
        }
        ViewTreeObserver viewTreeObserver = this.G;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.G.removeGlobalOnLayoutListener(this.r);
            }
            this.G = null;
        }
        this.x.removeOnAttachStateChangeListener(this.s);
        this.H.onDismiss();
    }

    @Override // p.jd2
    public final void c() {
        if (a()) {
            return;
        }
        ArrayList arrayList = this.f149p;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            v((si1) it.next());
        }
        arrayList.clear();
        View view = this.w;
        this.x = view;
        if (view != null) {
            boolean z = this.G == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.G = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.r);
            }
            this.x.addOnAttachStateChangeListener(this.s);
        }
    }

    @Override // p.oj1
    public final void d() {
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((kq) it.next()).a.k.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((pi1) adapter).notifyDataSetChanged();
        }
    }

    @Override // p.jd2
    public final void dismiss() {
        ArrayList arrayList = this.q;
        int size = arrayList.size();
        if (size > 0) {
            kq[] kqVarArr = (kq[]) arrayList.toArray(new kq[size]);
            for (int i = size - 1; i >= 0; i--) {
                kq kqVar = kqVarArr[i];
                if (kqVar.a.G.isShowing()) {
                    kqVar.a.dismiss();
                }
            }
        }
    }

    @Override // p.jd2
    public final ib0 e() {
        ArrayList arrayList = this.q;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((kq) arrayList.get(arrayList.size() - 1)).a.k;
    }

    @Override // p.oj1
    public final boolean f(qg2 qg2Var) {
        for (kq kqVar : this.q) {
            if (qg2Var == kqVar.b) {
                kqVar.a.k.requestFocus();
                return true;
            }
        }
        if (!qg2Var.hasVisibleItems()) {
            return false;
        }
        l(qg2Var);
        nj1 nj1Var = this.F;
        if (nj1Var != null) {
            nj1Var.s(qg2Var);
        }
        return true;
    }

    @Override // p.oj1
    public final boolean i() {
        return false;
    }

    @Override // p.oj1
    public final void j(nj1 nj1Var) {
        this.F = nj1Var;
    }

    @Override // p.ej1
    public final void l(si1 si1Var) {
        si1Var.b(this, this.e);
        if (a()) {
            v(si1Var);
        } else {
            this.f149p.add(si1Var);
        }
    }

    @Override // p.ej1
    public final void n(View view) {
        if (this.w != view) {
            this.w = view;
            int i = this.u;
            WeakHashMap weakHashMap = zt2.a;
            this.v = Gravity.getAbsoluteGravity(i, jt2.d(view));
        }
    }

    @Override // p.ej1
    public final void o(boolean z) {
        this.D = z;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        kq kqVar;
        ArrayList arrayList = this.q;
        int size = arrayList.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                kqVar = null;
                break;
            }
            kqVar = (kq) arrayList.get(i);
            if (!kqVar.a.G.isShowing()) {
                break;
            } else {
                i++;
            }
        }
        if (kqVar != null) {
            kqVar.b.c(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // p.ej1
    public final void p(int i) {
        if (this.u != i) {
            this.u = i;
            View view = this.w;
            WeakHashMap weakHashMap = zt2.a;
            this.v = Gravity.getAbsoluteGravity(i, jt2.d(view));
        }
    }

    @Override // p.ej1
    public final void q(int i) {
        this.z = true;
        this.B = i;
    }

    @Override // p.ej1
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.H = onDismissListener;
    }

    @Override // p.ej1
    public final void s(boolean z) {
        this.E = z;
    }

    @Override // p.ej1
    public final void t(int i) {
        this.A = true;
        this.C = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v(p.si1 r18) {
        /*
            Method dump skipped, instruction units count: 469
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.lq.v(p.si1):void");
    }
}
