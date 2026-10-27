package com.google.android.material.datepicker;

import a.g.k.t;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g<S> extends m<S> {
    static final Object a0 = "MONTHS_VIEW_GROUP_TAG";
    static final Object b0 = "NAVIGATION_PREV_TAG";
    static final Object c0 = "NAVIGATION_NEXT_TAG";
    static final Object d0 = "SELECTOR_TOGGLE_TAG";
    private int e0;
    private com.google.android.material.datepicker.d<S> f0;
    private com.google.android.material.datepicker.a g0;
    private com.google.android.material.datepicker.i h0;
    private k i0;
    private com.google.android.material.datepicker.c j0;
    private RecyclerView k0;
    private RecyclerView l0;
    private View m0;
    private View n0;

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3049b;

        a(int i) {
            this.f3049b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            g.this.l0.q1(this.f3049b);
        }
    }

    class b extends a.g.k.a {
        b() {
        }

        @Override // a.g.k.a
        public void g(View view, a.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.Z(null);
        }
    }

    class c extends n {
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.I = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void i2(RecyclerView.a0 a0Var, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = g.this.l0.getWidth();
                iArr[1] = g.this.l0.getWidth();
            } else {
                iArr[0] = g.this.l0.getHeight();
                iArr[1] = g.this.l0.getHeight();
            }
        }
    }

    class d implements l {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.g.l
        public void a(long j) {
            if (g.this.g0.v().j(j)) {
                g.this.f0.p(j);
                Iterator<com.google.android.material.datepicker.l<S>> it = g.this.Z.iterator();
                while (it.hasNext()) {
                    it.next().a(g.this.f0.b());
                }
                g.this.l0.getAdapter().h();
                if (g.this.k0 != null) {
                    g.this.k0.getAdapter().h();
                }
            }
        }
    }

    class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Calendar f3053a = p.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f3054b = p.l();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            if ((recyclerView.getAdapter() instanceof q) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                q qVar = (q) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (a.g.j.d<Long, Long> dVar : g.this.f0.i()) {
                    Long l = dVar.f366a;
                    if (l != null && dVar.f367b != null) {
                        this.f3053a.setTimeInMillis(l.longValue());
                        this.f3054b.setTimeInMillis(dVar.f367b.longValue());
                        int iZ = qVar.z(this.f3053a.get(1));
                        int iZ2 = qVar.z(this.f3054b.get(1));
                        View viewY = gridLayoutManager.Y(iZ);
                        View viewY2 = gridLayoutManager.Y(iZ2);
                        int iT3 = iZ / gridLayoutManager.t3();
                        int iT32 = iZ2 / gridLayoutManager.t3();
                        int i = iT3;
                        while (i <= iT32) {
                            if (gridLayoutManager.Y(gridLayoutManager.t3() * i) != null) {
                                canvas.drawRect(i == iT3 ? viewY.getLeft() + (viewY.getWidth() / 2) : 0, r9.getTop() + g.this.j0.f3045d.c(), i == iT32 ? viewY2.getLeft() + (viewY2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - g.this.j0.f3045d.b(), g.this.j0.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    class f extends a.g.k.a {
        f() {
        }

        @Override // a.g.k.a
        public void g(View view, a.g.k.c0.c cVar) {
            g gVar;
            int i;
            super.g(view, cVar);
            if (g.this.n0.getVisibility() == 0) {
                gVar = g.this;
                i = b.a.a.a.j.n;
            } else {
                gVar = g.this;
                i = b.a.a.a.j.m;
            }
            cVar.h0(gVar.R(i));
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.datepicker.g$g, reason: collision with other inner class name */
    class C0111g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f3057a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f3058b;

        C0111g(com.google.android.material.datepicker.k kVar, MaterialButton materialButton) {
            this.f3057a = kVar;
            this.f3058b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.f3058b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            LinearLayoutManager linearLayoutManagerN1 = g.this.N1();
            int iV2 = i < 0 ? linearLayoutManagerN1.v2() : linearLayoutManagerN1.y2();
            g.this.h0 = this.f3057a.y(iV2);
            this.f3058b.setText(this.f3057a.z(iV2));
        }
    }

    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.R1();
        }
    }

    class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f3061b;

        i(com.google.android.material.datepicker.k kVar) {
            this.f3061b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iV2 = g.this.N1().v2() + 1;
            if (iV2 < g.this.l0.getAdapter().c()) {
                g.this.P1(this.f3061b.y(iV2));
            }
        }
    }

    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f3063b;

        j(com.google.android.material.datepicker.k kVar) {
            this.f3063b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iY2 = g.this.N1().y2() - 1;
            if (iY2 >= 0) {
                g.this.P1(this.f3063b.y(iY2));
            }
        }
    }

    enum k {
        DAY,
        YEAR
    }

    interface l {
        void a(long j);
    }

    private void G1(View view, com.google.android.material.datepicker.k kVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(b.a.a.a.f.f);
        materialButton.setTag(d0);
        t.h0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(b.a.a.a.f.h);
        materialButton2.setTag(b0);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(b.a.a.a.f.g);
        materialButton3.setTag(c0);
        this.m0 = view.findViewById(b.a.a.a.f.m);
        this.n0 = view.findViewById(b.a.a.a.f.j);
        Q1(k.DAY);
        materialButton.setText(this.h0.z());
        this.l0.k(new C0111g(kVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(kVar));
        materialButton2.setOnClickListener(new j(kVar));
    }

    private RecyclerView.n H1() {
        return new e();
    }

    static int M1(Context context) {
        return context.getResources().getDimensionPixelSize(b.a.a.a.d.q);
    }

    private void O1(int i2) {
        this.l0.post(new a(i2));
    }

    @Override // androidx.fragment.app.Fragment
    public void G0(Bundle bundle) {
        super.G0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.e0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.g0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.h0);
    }

    com.google.android.material.datepicker.a I1() {
        return this.g0;
    }

    com.google.android.material.datepicker.c J1() {
        return this.j0;
    }

    com.google.android.material.datepicker.i K1() {
        return this.h0;
    }

    public com.google.android.material.datepicker.d<S> L1() {
        return this.f0;
    }

    LinearLayoutManager N1() {
        return (LinearLayoutManager) this.l0.getLayoutManager();
    }

    void P1(com.google.android.material.datepicker.i iVar) {
        RecyclerView recyclerView;
        int i2;
        com.google.android.material.datepicker.k kVar = (com.google.android.material.datepicker.k) this.l0.getAdapter();
        int iA = kVar.A(iVar);
        int iA2 = iA - kVar.A(this.h0);
        boolean z = Math.abs(iA2) > 3;
        boolean z2 = iA2 > 0;
        this.h0 = iVar;
        if (!z || !z2) {
            if (z) {
                recyclerView = this.l0;
                i2 = iA + 3;
            }
            O1(iA);
        }
        recyclerView = this.l0;
        i2 = iA - 3;
        recyclerView.i1(i2);
        O1(iA);
    }

    void Q1(k kVar) {
        this.i0 = kVar;
        if (kVar == k.YEAR) {
            this.k0.getLayoutManager().S1(((q) this.k0.getAdapter()).z(this.h0.e));
            this.m0.setVisibility(0);
            this.n0.setVisibility(8);
        } else if (kVar == k.DAY) {
            this.m0.setVisibility(8);
            this.n0.setVisibility(0);
            P1(this.h0);
        }
    }

    void R1() {
        k kVar = this.i0;
        k kVar2 = k.YEAR;
        if (kVar == kVar2) {
            Q1(k.DAY);
        } else if (kVar == k.DAY) {
            Q1(kVar2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        if (bundle == null) {
            bundle = w();
        }
        this.e0 = bundle.getInt("THEME_RES_ID_KEY");
        this.f0 = (com.google.android.material.datepicker.d) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.g0 = (com.google.android.material.datepicker.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.h0 = (com.google.android.material.datepicker.i) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(y(), this.e0);
        this.j0 = new com.google.android.material.datepicker.c(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        com.google.android.material.datepicker.i iVarZ = this.g0.z();
        if (com.google.android.material.datepicker.h.z1(contextThemeWrapper)) {
            i2 = b.a.a.a.h.j;
            i3 = 1;
        } else {
            i2 = b.a.a.a.h.h;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(b.a.a.a.f.k);
        t.h0(gridView, new b());
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.f());
        gridView.setNumColumns(iVarZ.f);
        gridView.setEnabled(false);
        this.l0 = (RecyclerView) viewInflate.findViewById(b.a.a.a.f.l);
        this.l0.setLayoutManager(new c(y(), i3, false, i3));
        this.l0.setTag(a0);
        com.google.android.material.datepicker.k kVar = new com.google.android.material.datepicker.k(contextThemeWrapper, this.f0, this.g0, new d());
        this.l0.setAdapter(kVar);
        int integer = contextThemeWrapper.getResources().getInteger(b.a.a.a.g.f1714b);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(b.a.a.a.f.m);
        this.k0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.k0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.k0.setAdapter(new q(this));
            this.k0.h(H1());
        }
        if (viewInflate.findViewById(b.a.a.a.f.f) != null) {
            G1(viewInflate, kVar);
        }
        if (!com.google.android.material.datepicker.h.z1(contextThemeWrapper)) {
            new androidx.recyclerview.widget.i().b(this.l0);
        }
        this.l0.i1(kVar.A(this.h0));
        return viewInflate;
    }
}
