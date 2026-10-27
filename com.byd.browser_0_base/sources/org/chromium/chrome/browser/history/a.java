package org.chromium.chrome.browser.history;

import J.N;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.aq1;
import defpackage.b60;
import defpackage.c3;
import defpackage.dv0;
import defpackage.ev0;
import defpackage.kv0;
import defpackage.vh3;
import defpackage.xz;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.history.b;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.browser_ui.widget.MoreProgressButton;
import org.chromium.components.browser_ui.widget.R$layout;

/* JADX INFO: compiled from: HistoryAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends b60 implements b.a {
    public static final /* synthetic */ int A = 0;
    public final dv0 j;
    public final ArrayList<HistoryItemView> k;
    public final FaviconHelper.a l;
    public b m;
    public MoreProgressButton n;
    public b60.e o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public String x = "";
    public String y;
    public final zp1<Boolean> z;

    public a(dv0 dv0Var, BrowsingHistoryBridge browsingHistoryBridge, aq1 aq1Var) {
        B(true);
        this.m = browsingHistoryBridge;
        browsingHistoryBridge.a = this;
        this.j = dv0Var;
        this.z = aq1Var;
        aq1Var.p(new c3(28, this));
        this.l = new FaviconHelper.a();
        this.k = new ArrayList<>();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // defpackage.b60
    public final void D(RecyclerView.a0 a0Var, b60.k kVar) {
        ev0 ev0Var = (ev0) kVar;
        dv0 dv0Var = this.j;
        dv0Var.getClass();
        ev0Var.i = dv0Var;
        ((kv0) a0Var).y.setItem((E) ev0Var);
    }

    @Override // defpackage.b60
    public final b60.d F(RecyclerView recyclerView) {
        b60.d dVarF = super.F(recyclerView);
        if (xz.a(recyclerView.getContext()) instanceof ChromeTabbedActivity) {
            View view = dVarF.e;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMarginStart(vh3.a(20));
            marginLayoutParams.setMarginEnd(vh3.a(20));
            view.setLayoutParams(marginLayoutParams);
        }
        return dVarF;
    }

    @Override // defpackage.b60
    public final b60.b G(RecyclerView recyclerView) {
        return new b60.b(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.date_divided_adapter_header_view_holder, (ViewGroup) recyclerView, false));
    }

    @Override // defpackage.b60
    public final kv0 H(RecyclerView recyclerView) {
        View viewInflate = LayoutInflater.from(recyclerView.getContext()).inflate(org.chromium.chrome.R$layout.history_item_view, (ViewGroup) recyclerView, false);
        dv0 dv0Var = this.j;
        dv0Var.getClass();
        kv0 kv0Var = new kv0(viewInflate, dv0Var.r);
        HistoryItemView historyItemView = (HistoryItemView) viewInflate;
        historyItemView.setRemoveButtonVisible(!r0.e());
        historyItemView.setFaviconHelper(this.l);
        this.k.add(historyItemView);
        return kv0Var;
    }

    @Override // defpackage.b60
    public final int K() {
        return org.chromium.chrome.R$layout.date_view;
    }

    public final void P() {
        if (!this.s && this.u) {
            this.s = true;
            V();
            o();
            BrowsingHistoryBridge browsingHistoryBridge = (BrowsingHistoryBridge) this.m;
            N.MuGq8Vn6(browsingHistoryBridge.b, browsingHistoryBridge, new ArrayList());
        }
    }

    public final void Q(ev0 ev0Var) {
        Pair<b60.i, Integer> pairI = I(ev0Var.a);
        if (pairI == null) {
            Log.e("cr_DateDividedAdapter", "Failed to find group for item during remove. Item position: " + ev0Var.a + ", total size: " + this.h);
        } else {
            b60.i iVar = (b60.i) pairI.first;
            iVar.b.remove(ev0Var);
            int size = iVar.b.size();
            TreeSet treeSet = this.i;
            if (size == 1) {
                treeSet.remove(iVar);
            }
            if (M() && treeSet.size() == 1) {
                N();
            }
            O();
            o();
        }
        BrowsingHistoryBridge browsingHistoryBridge = (BrowsingHistoryBridge) this.m;
        long j = browsingHistoryBridge.b;
        long[] jArr = ev0Var.g;
        N.Mya3ANHw(j, browsingHistoryBridge, ev0Var.c, Arrays.copyOf(jArr, jArr.length));
    }

    public final void R() {
        Iterator<HistoryItemView> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().getClass();
        }
        U();
    }

    public final void S() {
        ArrayList arrayList = new ArrayList();
        if (this.w) {
            arrayList.add(null);
        }
        zp1<Boolean> zp1Var = this.z;
        if (zp1Var.get() != null) {
            zp1Var.get().booleanValue();
        }
        b60.g[] gVarArr = (b60.g[]) arrayList.toArray(new b60.g[arrayList.size()]);
        if (gVarArr == null || gVarArr.length == 0) {
            N();
            return;
        }
        boolean zM = M();
        TreeSet treeSet = this.i;
        if (zM) {
            treeSet.remove(treeSet.first());
        }
        b60.h hVar = new b60.h();
        for (b60.g gVar : gVarArr) {
            hVar.a(gVar);
        }
        treeSet.add(hVar);
        O();
        o();
    }

    public final void T() {
        dv0 dv0Var = this.j;
        boolean z = (!dv0Var.k && this.p) && dv0Var.s;
        if (this.w == z) {
            return;
        }
        this.w = z;
        if (this.r) {
            S();
        }
    }

    public final void U() {
        this.r = false;
        this.s = true;
        this.v = true;
        String str = this.y;
        if (str != null) {
            BrowsingHistoryBridge browsingHistoryBridge = (BrowsingHistoryBridge) this.m;
            N.ML$TCyGp(browsingHistoryBridge.b, browsingHistoryBridge, new ArrayList(), str, true);
        } else {
            b bVar = this.m;
            BrowsingHistoryBridge browsingHistoryBridge2 = (BrowsingHistoryBridge) bVar;
            N.ML$TCyGp(browsingHistoryBridge2.b, browsingHistoryBridge2, new ArrayList(), this.x, false);
        }
    }

    public final void V() {
        boolean z = false;
        dv0 dv0Var = this.j;
        if (((dv0Var != null && dv0Var.l) || this.s) && !L()) {
            b60.f fVar = new b60.f();
            fVar.a(this.o);
            if (dv0Var != null && dv0Var.l) {
                z = true;
            }
            if (z) {
                this.n.setState(1);
            } else {
                this.n.setState(2);
            }
            this.i.add(fVar);
            O();
            o();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void u(@NonNull RecyclerView.a0 a0Var, int i, @NonNull List<Object> list) {
        if (list == null || list.isEmpty()) {
            t(a0Var, i);
            return;
        }
        Object obj = list.get(0);
        if ((obj instanceof String) && obj.equals("refresh_collect ") && (a0Var instanceof kv0)) {
            ((HistoryItemView) ((kv0) a0Var).e).s();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void s(RecyclerView recyclerView) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void w(RecyclerView recyclerView) {
    }
}
