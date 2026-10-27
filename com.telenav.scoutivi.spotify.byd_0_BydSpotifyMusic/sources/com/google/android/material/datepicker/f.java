package com.google.android.material.datepicker;

import android.content.Context;
import android.graphics.Canvas;
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
import i0.u;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<S> extends m<S> {

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    static final Object f4435r0 = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    static final Object f4436s0 = "NAVIGATION_PREV_TAG";

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    static final Object f4437t0 = "NAVIGATION_NEXT_TAG";

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    static final Object f4438u0 = "SELECTOR_TOGGLE_TAG";

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private int f4439h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private DateSelector<S> f4440i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private CalendarConstraints f4441j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private Month f4442k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private k f4443l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private com.google.android.material.datepicker.b f4444m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private RecyclerView f4445n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private RecyclerView f4446o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private View f4447p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private View f4448q0;

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f4449e;

        a(int i7) {
            this.f4449e = i7;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.f4446o0.r1(this.f4449e);
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class b extends i0.a {
        b() {
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            cVar.R(null);
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class c extends n {
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i7, boolean z6, int i8) {
            super(context, i7, z6);
            this.I = i8;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void M1(RecyclerView.z zVar, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = f.this.f4446o0.getWidth();
                iArr[1] = f.this.f4446o0.getWidth();
            } else {
                iArr[0] = f.this.f4446o0.getHeight();
                iArr[1] = f.this.f4446o0.getHeight();
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class d implements l {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.f.l
        public void a(long j7) {
            if (f.this.f4441j0.m().g(j7)) {
                f.this.f4440i0.l(j7);
                Iterator<com.google.android.material.datepicker.l<S>> it = f.this.f4493g0.iterator();
                while (it.hasNext()) {
                    it.next().b(f.this.f4440i0.a());
                }
                f.this.f4446o0.getAdapter().j();
                if (f.this.f4445n0 != null) {
                    f.this.f4445n0.getAdapter().j();
                }
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Calendar f4453a = p.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f4454b = p.l();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof q) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                q qVar = (q) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (h0.d<Long, Long> dVar : f.this.f4440i0.f()) {
                    Long l7 = dVar.f6589a;
                    if (l7 != null && dVar.f6590b != null) {
                        this.f4453a.setTimeInMillis(l7.longValue());
                        this.f4454b.setTimeInMillis(dVar.f6590b.longValue());
                        int iD = qVar.D(this.f4453a.get(1));
                        int iD2 = qVar.D(this.f4454b.get(1));
                        View viewC = gridLayoutManager.C(iD);
                        View viewC2 = gridLayoutManager.C(iD2);
                        int iX2 = iD / gridLayoutManager.X2();
                        int iX22 = iD2 / gridLayoutManager.X2();
                        int i7 = iX2;
                        while (i7 <= iX22) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i7) != null) {
                                canvas.drawRect(i7 == iX2 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + f.this.f4444m0.f4416d.c(), i7 == iX22 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - f.this.f4444m0.f4416d.b(), f.this.f4444m0.f4420h);
                            }
                            i7++;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.datepicker.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCalendar.java */
    class C0048f extends i0.a {
        C0048f() {
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            super.g(view, cVar);
            cVar.Z(f.this.f4448q0.getVisibility() == 0 ? f.this.T(o2.j.mtrl_picker_toggle_to_year_selection) : f.this.T(o2.j.mtrl_picker_toggle_to_day_selection));
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f4457a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f4458b;

        g(com.google.android.material.datepicker.k kVar, MaterialButton materialButton) {
            this.f4457a = kVar;
            this.f4458b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i7) {
            if (i7 == 0) {
                recyclerView.announceForAccessibility(this.f4458b.getText());
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i7, int i8) {
            int iZ1 = i7 < 0 ? f.this.d2().Z1() : f.this.d2().c2();
            f.this.f4442k0 = this.f4457a.C(iZ1);
            this.f4458b.setText(this.f4457a.D(iZ1));
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.i2();
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class i implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f4461e;

        i(com.google.android.material.datepicker.k kVar) {
            this.f4461e = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = f.this.d2().Z1() + 1;
            if (iZ1 < f.this.f4446o0.getAdapter().e()) {
                f.this.g2(this.f4461e.C(iZ1));
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f4463e;

        j(com.google.android.material.datepicker.k kVar) {
            this.f4463e = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = f.this.d2().c2() - 1;
            if (iC2 >= 0) {
                f.this.g2(this.f4463e.C(iC2));
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    enum k {
        DAY,
        YEAR
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    interface l {
        void a(long j7);
    }

    private void W1(View view, com.google.android.material.datepicker.k kVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(o2.f.month_navigation_fragment_toggle);
        materialButton.setTag(f4438u0);
        u.k0(materialButton, new C0048f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(o2.f.month_navigation_previous);
        materialButton2.setTag(f4436s0);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(o2.f.month_navigation_next);
        materialButton3.setTag(f4437t0);
        this.f4447p0 = view.findViewById(o2.f.mtrl_calendar_year_selector_frame);
        this.f4448q0 = view.findViewById(o2.f.mtrl_calendar_day_selector_frame);
        h2(k.DAY);
        materialButton.setText(this.f4442k0.q(view.getContext()));
        this.f4446o0.l(new g(kVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(kVar));
        materialButton2.setOnClickListener(new j(kVar));
    }

    private RecyclerView.n X1() {
        return new e();
    }

    static int c2(Context context) {
        return context.getResources().getDimensionPixelSize(o2.d.mtrl_calendar_day_height);
    }

    public static <T> f<T> e2(DateSelector<T> dateSelector, int i7, CalendarConstraints calendarConstraints) {
        f<T> fVar = new f<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i7);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.r());
        fVar.C1(bundle);
        return fVar;
    }

    private void f2(int i7) {
        this.f4446o0.post(new a(i7));
    }

    @Override // com.google.android.material.datepicker.m
    public boolean N1(com.google.android.material.datepicker.l<S> lVar) {
        return super.N1(lVar);
    }

    @Override // androidx.fragment.app.Fragment
    public void Q0(Bundle bundle) {
        super.Q0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f4439h0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f4440i0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f4441j0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f4442k0);
    }

    CalendarConstraints Y1() {
        return this.f4441j0;
    }

    com.google.android.material.datepicker.b Z1() {
        return this.f4444m0;
    }

    Month a2() {
        return this.f4442k0;
    }

    public DateSelector<S> b2() {
        return this.f4440i0;
    }

    LinearLayoutManager d2() {
        return (LinearLayoutManager) this.f4446o0.getLayoutManager();
    }

    void g2(Month month) {
        com.google.android.material.datepicker.k kVar = (com.google.android.material.datepicker.k) this.f4446o0.getAdapter();
        int iE = kVar.E(month);
        int iE2 = iE - kVar.E(this.f4442k0);
        boolean z6 = Math.abs(iE2) > 3;
        boolean z7 = iE2 > 0;
        this.f4442k0 = month;
        if (z6 && z7) {
            this.f4446o0.j1(iE - 3);
            f2(iE);
        } else if (!z6) {
            f2(iE);
        } else {
            this.f4446o0.j1(iE + 3);
            f2(iE);
        }
    }

    void h2(k kVar) {
        this.f4443l0 = kVar;
        if (kVar == k.YEAR) {
            this.f4445n0.getLayoutManager().x1(((q) this.f4445n0.getAdapter()).D(this.f4442k0.f4385h));
            this.f4447p0.setVisibility(0);
            this.f4448q0.setVisibility(8);
        } else if (kVar == k.DAY) {
            this.f4447p0.setVisibility(8);
            this.f4448q0.setVisibility(0);
            g2(this.f4442k0);
        }
    }

    void i2() {
        k kVar = this.f4443l0;
        k kVar2 = k.YEAR;
        if (kVar == kVar2) {
            h2(k.DAY);
        } else if (kVar == k.DAY) {
            h2(kVar2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void u0(Bundle bundle) {
        super.u0(bundle);
        if (bundle == null) {
            bundle = q();
        }
        this.f4439h0 = bundle.getInt("THEME_RES_ID_KEY");
        this.f4440i0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f4441j0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f4442k0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i7;
        int i8;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(s(), this.f4439h0);
        this.f4444m0 = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthS = this.f4441j0.s();
        if (com.google.android.material.datepicker.g.q2(contextThemeWrapper)) {
            i7 = o2.h.mtrl_calendar_vertical;
            i8 = 1;
        } else {
            i7 = o2.h.mtrl_calendar_horizontal;
            i8 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i7, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(o2.f.mtrl_calendar_days_of_week);
        u.k0(gridView, new b());
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.e());
        gridView.setNumColumns(monthS.f4386i);
        gridView.setEnabled(false);
        this.f4446o0 = (RecyclerView) viewInflate.findViewById(o2.f.mtrl_calendar_months);
        this.f4446o0.setLayoutManager(new c(s(), i8, false, i8));
        this.f4446o0.setTag(f4435r0);
        com.google.android.material.datepicker.k kVar = new com.google.android.material.datepicker.k(contextThemeWrapper, this.f4440i0, this.f4441j0, new d());
        this.f4446o0.setAdapter(kVar);
        int integer = contextThemeWrapper.getResources().getInteger(o2.g.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(o2.f.mtrl_calendar_year_selector_frame);
        this.f4445n0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f4445n0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.f4445n0.setAdapter(new q(this));
            this.f4445n0.h(X1());
        }
        if (viewInflate.findViewById(o2.f.month_navigation_fragment_toggle) != null) {
            W1(viewInflate, kVar);
        }
        if (!com.google.android.material.datepicker.g.q2(contextThemeWrapper)) {
            new androidx.recyclerview.widget.n().b(this.f4446o0);
        }
        this.f4446o0.j1(kVar.E(this.f4442k0));
        return viewInflate;
    }
}
