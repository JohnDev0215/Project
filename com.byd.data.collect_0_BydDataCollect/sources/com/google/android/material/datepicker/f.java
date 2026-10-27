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
import b.g.k.t;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<S> extends m<S> {
    static final Object Z = "MONTHS_VIEW_GROUP_TAG";
    static final Object a0 = "NAVIGATION_PREV_TAG";
    static final Object b0 = "NAVIGATION_NEXT_TAG";
    static final Object c0 = "SELECTOR_TOGGLE_TAG";
    private int d0;
    private DateSelector<S> e0;
    private CalendarConstraints f0;
    private Month g0;
    private k h0;
    private com.google.android.material.datepicker.b i0;
    private RecyclerView j0;
    private RecyclerView k0;
    private View l0;
    private View m0;

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f3967b;

        a(int i2) {
            this.f3967b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.k0.p1(this.f3967b);
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class b extends b.g.k.a {
        b() {
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.d0(null);
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class c extends n {
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i2, boolean z, int i3) {
            super(context, i2, z);
            this.I = i3;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void M1(RecyclerView.z zVar, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = f.this.k0.getWidth();
                iArr[1] = f.this.k0.getWidth();
            } else {
                iArr[0] = f.this.k0.getHeight();
                iArr[1] = f.this.k0.getHeight();
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class d implements l {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.f.l
        public void a(long j) {
            if (f.this.f0.l().i(j)) {
                f.this.e0.m(j);
                Iterator<com.google.android.material.datepicker.l<S>> it = f.this.Y.iterator();
                while (it.hasNext()) {
                    it.next().b(f.this.e0.b());
                }
                f.this.k0.getAdapter().h();
                if (f.this.j0 != null) {
                    f.this.j0.getAdapter().h();
                }
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Calendar f3971a = p.r();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f3972b = p.r();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof q) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                q qVar = (q) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (b.g.j.d<Long, Long> dVar : f.this.e0.h()) {
                    Long l = dVar.f2659a;
                    if (l != null && dVar.f2660b != null) {
                        this.f3971a.setTimeInMillis(l.longValue());
                        this.f3972b.setTimeInMillis(dVar.f2660b.longValue());
                        int iW = qVar.w(this.f3971a.get(1));
                        int iW2 = qVar.w(this.f3972b.get(1));
                        View viewC = gridLayoutManager.C(iW);
                        View viewC2 = gridLayoutManager.C(iW2);
                        int iX2 = iW / gridLayoutManager.X2();
                        int iX22 = iW2 / gridLayoutManager.X2();
                        int i2 = iX2;
                        while (i2 <= iX22) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i2) != null) {
                                canvas.drawRect(i2 == iX2 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + f.this.i0.f3953d.c(), i2 == iX22 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - f.this.i0.f3953d.b(), f.this.i0.f3957h);
                            }
                            i2++;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.google.android.material.datepicker.f$f, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCalendar.java */
    class C0075f extends b.g.k.a {
        C0075f() {
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.l0(f.this.m0.getVisibility() == 0 ? f.this.I(d.b.a.a.j.mtrl_picker_toggle_to_year_selection) : f.this.I(d.b.a.a.j.mtrl_picker_toggle_to_day_selection));
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f3975a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f3976b;

        g(com.google.android.material.datepicker.k kVar, MaterialButton materialButton) {
            this.f3975a = kVar;
            this.f3976b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                CharSequence text = this.f3976b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i2, int i3) {
            int iZ1 = i2 < 0 ? f.this.F1().Z1() : f.this.F1().c2();
            f.this.g0 = this.f3975a.v(iZ1);
            this.f3976b.setText(this.f3975a.w(iZ1));
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.K1();
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f3979b;

        i(com.google.android.material.datepicker.k kVar) {
            this.f3979b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = f.this.F1().Z1() + 1;
            if (iZ1 < f.this.k0.getAdapter().c()) {
                f.this.I1(this.f3979b.v(iZ1));
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.k f3981b;

        j(com.google.android.material.datepicker.k kVar) {
            this.f3981b = kVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = f.this.F1().c2() - 1;
            if (iC2 >= 0) {
                f.this.I1(this.f3981b.v(iC2));
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
        void a(long j);
    }

    static int E1(Context context) {
        return context.getResources().getDimensionPixelSize(d.b.a.a.d.mtrl_calendar_day_height);
    }

    static <T> f<T> G1(DateSelector<T> dateSelector, int i2, CalendarConstraints calendarConstraints) {
        f<T> fVar = new f<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.r());
        fVar.i1(bundle);
        return fVar;
    }

    private void H1(int i2) {
        this.k0.post(new a(i2));
    }

    private void y1(View view, com.google.android.material.datepicker.k kVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(d.b.a.a.f.month_navigation_fragment_toggle);
        materialButton.setTag(c0);
        t.j0(materialButton, new C0075f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(d.b.a.a.f.month_navigation_previous);
        materialButton2.setTag(a0);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(d.b.a.a.f.month_navigation_next);
        materialButton3.setTag(b0);
        this.l0 = view.findViewById(d.b.a.a.f.mtrl_calendar_year_selector_frame);
        this.m0 = view.findViewById(d.b.a.a.f.mtrl_calendar_day_selector_frame);
        J1(k.DAY);
        materialButton.setText(this.g0.p());
        this.k0.l(new g(kVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(kVar));
        materialButton2.setOnClickListener(new j(kVar));
    }

    private RecyclerView.n z1() {
        return new e();
    }

    CalendarConstraints A1() {
        return this.f0;
    }

    com.google.android.material.datepicker.b B1() {
        return this.i0;
    }

    Month C1() {
        return this.g0;
    }

    public DateSelector<S> D1() {
        return this.e0;
    }

    LinearLayoutManager F1() {
        return (LinearLayoutManager) this.k0.getLayoutManager();
    }

    void I1(Month month) {
        com.google.android.material.datepicker.k kVar = (com.google.android.material.datepicker.k) this.k0.getAdapter();
        int iX = kVar.x(month);
        int iX2 = iX - kVar.x(this.g0);
        boolean z = Math.abs(iX2) > 3;
        boolean z2 = iX2 > 0;
        this.g0 = month;
        if (z && z2) {
            this.k0.h1(iX - 3);
            H1(iX);
        } else if (!z) {
            H1(iX);
        } else {
            this.k0.h1(iX + 3);
            H1(iX);
        }
    }

    void J1(k kVar) {
        this.h0 = kVar;
        if (kVar == k.YEAR) {
            this.j0.getLayoutManager().x1(((q) this.j0.getAdapter()).w(this.g0.f3925e));
            this.l0.setVisibility(0);
            this.m0.setVisibility(8);
        } else if (kVar == k.DAY) {
            this.l0.setVisibility(8);
            this.m0.setVisibility(0);
            I1(this.g0);
        }
    }

    void K1() {
        k kVar = this.h0;
        k kVar2 = k.YEAR;
        if (kVar == kVar2) {
            J1(k.DAY);
        } else if (kVar == k.DAY) {
            J1(kVar2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void d0(Bundle bundle) {
        super.d0(bundle);
        if (bundle == null) {
            bundle = n();
        }
        this.d0 = bundle.getInt("THEME_RES_ID_KEY");
        this.e0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.g0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View h0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(p(), this.d0);
        this.i0 = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthS = this.f0.s();
        if (com.google.android.material.datepicker.g.O1(contextThemeWrapper)) {
            i2 = d.b.a.a.h.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = d.b.a.a.h.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(d.b.a.a.f.mtrl_calendar_days_of_week);
        t.j0(gridView, new b());
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.e());
        gridView.setNumColumns(monthS.f3926f);
        gridView.setEnabled(false);
        this.k0 = (RecyclerView) viewInflate.findViewById(d.b.a.a.f.mtrl_calendar_months);
        this.k0.setLayoutManager(new c(p(), i3, false, i3));
        this.k0.setTag(Z);
        com.google.android.material.datepicker.k kVar = new com.google.android.material.datepicker.k(contextThemeWrapper, this.e0, this.f0, new d());
        this.k0.setAdapter(kVar);
        int integer = contextThemeWrapper.getResources().getInteger(d.b.a.a.g.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(d.b.a.a.f.mtrl_calendar_year_selector_frame);
        this.j0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.j0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.j0.setAdapter(new q(this));
            this.j0.h(z1());
        }
        if (viewInflate.findViewById(d.b.a.a.f.month_navigation_fragment_toggle) != null) {
            y1(viewInflate, kVar);
        }
        if (!com.google.android.material.datepicker.g.O1(contextThemeWrapper)) {
            new androidx.recyclerview.widget.h().b(this.k0);
        }
        this.k0.h1(kVar.x(this.g0));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void z0(Bundle bundle) {
        super.z0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.d0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.e0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.g0);
    }
}
