package com.google.android.material.datepicker;

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
import c.g.k.t;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialCalendar<S> extends com.google.android.material.datepicker.i<S> {
    static final Object a0 = "MONTHS_VIEW_GROUP_TAG";
    static final Object b0 = "NAVIGATION_PREV_TAG";
    static final Object c0 = "NAVIGATION_NEXT_TAG";
    static final Object d0 = "SELECTOR_TOGGLE_TAG";
    private int e0;
    private DateSelector<S> f0;
    private CalendarConstraints g0;
    private Month h0;
    private CalendarSelector i0;
    private com.google.android.material.datepicker.b j0;
    private RecyclerView k0;
    private RecyclerView l0;
    private View m0;
    private View n0;

    enum CalendarSelector {
        DAY,
        YEAR
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3493b;

        a(int i) {
            this.f3493b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.l0.p1(this.f3493b);
        }
    }

    class b extends c.g.k.a {
        b() {
        }

        @Override // c.g.k.a
        public void g(View view, c.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.d0(null);
        }
    }

    class c extends com.google.android.material.datepicker.j {
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.I = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void M1(RecyclerView.z zVar, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = MaterialCalendar.this.l0.getWidth();
                iArr[1] = MaterialCalendar.this.l0.getWidth();
            } else {
                iArr[0] = MaterialCalendar.this.l0.getHeight();
                iArr[1] = MaterialCalendar.this.l0.getHeight();
            }
        }
    }

    class d implements k {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.k
        public void a(long j) {
            if (MaterialCalendar.this.g0.g().b(j)) {
                MaterialCalendar.this.f0.e(j);
                Iterator<com.google.android.material.datepicker.h<S>> it = MaterialCalendar.this.Z.iterator();
                while (it.hasNext()) {
                    it.next().a(MaterialCalendar.this.f0.d());
                }
                MaterialCalendar.this.l0.getAdapter().h();
                if (MaterialCalendar.this.k0 != null) {
                    MaterialCalendar.this.k0.getAdapter().h();
                }
            }
        }
    }

    class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Calendar f3497a = l.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f3498b = l.l();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof m) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                m mVar = (m) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (c.g.j.d<Long, Long> dVar : MaterialCalendar.this.f0.a()) {
                    Long l = dVar.f2269a;
                    if (l != null && dVar.f2270b != null) {
                        this.f3497a.setTimeInMillis(l.longValue());
                        this.f3498b.setTimeInMillis(dVar.f2270b.longValue());
                        int iX = mVar.x(this.f3497a.get(1));
                        int iX2 = mVar.x(this.f3498b.get(1));
                        View viewC = gridLayoutManager.C(iX);
                        View viewC2 = gridLayoutManager.C(iX2);
                        int iX22 = iX / gridLayoutManager.X2();
                        int iX23 = iX2 / gridLayoutManager.X2();
                        int i = iX22;
                        while (i <= iX23) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i) != null) {
                                canvas.drawRect(i == iX22 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.j0.f3525d.c(), i == iX23 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.j0.f3525d.b(), MaterialCalendar.this.j0.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    class f extends c.g.k.a {
        f() {
        }

        @Override // c.g.k.a
        public void g(View view, c.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.l0(MaterialCalendar.this.n0.getVisibility() == 0 ? MaterialCalendar.this.I(d.b.a.a.j.q) : MaterialCalendar.this.I(d.b.a.a.j.p));
        }
    }

    class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f3501a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f3502b;

        g(com.google.android.material.datepicker.g gVar, MaterialButton materialButton) {
            this.f3501a = gVar;
            this.f3502b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.f3502b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            int iZ1 = i < 0 ? MaterialCalendar.this.A1().Z1() : MaterialCalendar.this.A1().c2();
            MaterialCalendar.this.h0 = this.f3501a.w(iZ1);
            this.f3502b.setText(this.f3501a.x(iZ1));
        }
    }

    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.E1();
        }
    }

    class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f3505b;

        i(com.google.android.material.datepicker.g gVar) {
            this.f3505b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = MaterialCalendar.this.A1().Z1() + 1;
            if (iZ1 < MaterialCalendar.this.l0.getAdapter().c()) {
                MaterialCalendar.this.C1(this.f3505b.w(iZ1));
            }
        }
    }

    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f3507b;

        j(com.google.android.material.datepicker.g gVar) {
            this.f3507b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = MaterialCalendar.this.A1().c2() - 1;
            if (iC2 >= 0) {
                MaterialCalendar.this.C1(this.f3507b.w(iC2));
            }
        }
    }

    interface k {
        void a(long j);
    }

    private void B1(int i2) {
        this.l0.post(new a(i2));
    }

    private void t1(View view, com.google.android.material.datepicker.g gVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(d.b.a.a.f.h);
        materialButton.setTag(d0);
        t.j0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(d.b.a.a.f.j);
        materialButton2.setTag(b0);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(d.b.a.a.f.i);
        materialButton3.setTag(c0);
        this.m0 = view.findViewById(d.b.a.a.f.o);
        this.n0 = view.findViewById(d.b.a.a.f.l);
        D1(CalendarSelector.DAY);
        materialButton.setText(this.h0.k());
        this.l0.l(new g(gVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(gVar));
        materialButton2.setOnClickListener(new j(gVar));
    }

    private RecyclerView.n u1() {
        return new e();
    }

    static int z1(Context context) {
        return context.getResources().getDimensionPixelSize(d.b.a.a.d.A);
    }

    LinearLayoutManager A1() {
        return (LinearLayoutManager) this.l0.getLayoutManager();
    }

    void C1(Month month) {
        com.google.android.material.datepicker.g gVar = (com.google.android.material.datepicker.g) this.l0.getAdapter();
        int iY = gVar.y(month);
        int iY2 = iY - gVar.y(this.h0);
        boolean z = Math.abs(iY2) > 3;
        boolean z2 = iY2 > 0;
        this.h0 = month;
        if (z && z2) {
            this.l0.h1(iY - 3);
            B1(iY);
        } else if (!z) {
            B1(iY);
        } else {
            this.l0.h1(iY + 3);
            B1(iY);
        }
    }

    void D1(CalendarSelector calendarSelector) {
        this.i0 = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.k0.getLayoutManager().x1(((m) this.k0.getAdapter()).x(this.h0.e));
            this.m0.setVisibility(0);
            this.n0.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.m0.setVisibility(8);
            this.n0.setVisibility(0);
            C1(this.h0);
        }
    }

    void E1() {
        CalendarSelector calendarSelector = this.i0;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            D1(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            D1(calendarSelector2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void a0(Bundle bundle) {
        super.a0(bundle);
        if (bundle == null) {
            bundle = n();
        }
        this.e0 = bundle.getInt("THEME_RES_ID_KEY");
        this.f0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.g0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.h0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(p(), this.e0);
        this.j0 = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthK = this.g0.k();
        if (com.google.android.material.datepicker.e.m1(contextThemeWrapper)) {
            i2 = d.b.a.a.h.p;
            i3 = 1;
        } else {
            i2 = d.b.a.a.h.n;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(d.b.a.a.f.m);
        t.j0(gridView, new b());
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.d());
        gridView.setNumColumns(monthK.f);
        gridView.setEnabled(false);
        this.l0 = (RecyclerView) viewInflate.findViewById(d.b.a.a.f.n);
        this.l0.setLayoutManager(new c(p(), i3, false, i3));
        this.l0.setTag(a0);
        com.google.android.material.datepicker.g gVar = new com.google.android.material.datepicker.g(contextThemeWrapper, this.f0, this.g0, new d());
        this.l0.setAdapter(gVar);
        int integer = contextThemeWrapper.getResources().getInteger(d.b.a.a.g.f3926b);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(d.b.a.a.f.o);
        this.k0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.k0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.k0.setAdapter(new m(this));
            this.k0.h(u1());
        }
        if (viewInflate.findViewById(d.b.a.a.f.h) != null) {
            t1(viewInflate, gVar);
        }
        if (!com.google.android.material.datepicker.e.m1(contextThemeWrapper)) {
            new androidx.recyclerview.widget.h().b(this.l0);
        }
        this.l0.h1(gVar.y(this.h0));
        return viewInflate;
    }

    CalendarConstraints v1() {
        return this.g0;
    }

    @Override // androidx.fragment.app.Fragment
    public void w0(Bundle bundle) {
        super.w0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.e0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.g0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.h0);
    }

    com.google.android.material.datepicker.b w1() {
        return this.j0;
    }

    Month x1() {
        return this.h0;
    }

    public DateSelector<S> y1() {
        return this.f0;
    }
}
