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
        final /* synthetic */ int f4158b;

        a(int i) {
            this.f4158b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.l0.r1(this.f4158b);
        }
    }

    class b extends b.g.k.a {
        b() {
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.d0(null);
        }
    }

    class c extends com.google.android.material.datepicker.j {
        final /* synthetic */ int J;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.J = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void W1(RecyclerView.z zVar, int[] iArr) {
            if (this.J == 0) {
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
            if (MaterialCalendar.this.g0.v().j(j)) {
                MaterialCalendar.this.f0.p(j);
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
        private final Calendar f4162a = l.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f4163b = l.l();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof m) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                m mVar = (m) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (b.g.j.d<Long, Long> dVar : MaterialCalendar.this.f0.i()) {
                    Long l = dVar.f1936a;
                    if (l != null && dVar.f1937b != null) {
                        this.f4162a.setTimeInMillis(l.longValue());
                        this.f4163b.setTimeInMillis(dVar.f1937b.longValue());
                        int iY = mVar.y(this.f4162a.get(1));
                        int iY2 = mVar.y(this.f4163b.get(1));
                        View viewM = gridLayoutManager.M(iY);
                        View viewM2 = gridLayoutManager.M(iY2);
                        int iJ3 = iY / gridLayoutManager.j3();
                        int iJ32 = iY2 / gridLayoutManager.j3();
                        int i = iJ3;
                        while (i <= iJ32) {
                            if (gridLayoutManager.M(gridLayoutManager.j3() * i) != null) {
                                canvas.drawRect(i == iJ3 ? viewM.getLeft() + (viewM.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.j0.f4193d.c(), i == iJ32 ? viewM2.getLeft() + (viewM2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.j0.f4193d.b(), MaterialCalendar.this.j0.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    class f extends b.g.k.a {
        f() {
        }

        @Override // b.g.k.a
        public void g(View view, b.g.k.c0.c cVar) {
            super.g(view, cVar);
            cVar.l0(MaterialCalendar.this.n0.getVisibility() == 0 ? MaterialCalendar.this.Q(c.a.a.a.j.q) : MaterialCalendar.this.Q(c.a.a.a.j.p));
        }
    }

    class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f4166a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f4167b;

        g(com.google.android.material.datepicker.g gVar, MaterialButton materialButton) {
            this.f4166a = gVar;
            this.f4167b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.f4167b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            int iK2 = i < 0 ? MaterialCalendar.this.N1().k2() : MaterialCalendar.this.N1().o2();
            MaterialCalendar.this.h0 = this.f4166a.x(iK2);
            this.f4167b.setText(this.f4166a.y(iK2));
        }
    }

    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.R1();
        }
    }

    class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f4170b;

        i(com.google.android.material.datepicker.g gVar) {
            this.f4170b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iK2 = MaterialCalendar.this.N1().k2() + 1;
            if (iK2 < MaterialCalendar.this.l0.getAdapter().c()) {
                MaterialCalendar.this.P1(this.f4170b.x(iK2));
            }
        }
    }

    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f4172b;

        j(com.google.android.material.datepicker.g gVar) {
            this.f4172b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iO2 = MaterialCalendar.this.N1().o2() - 1;
            if (iO2 >= 0) {
                MaterialCalendar.this.P1(this.f4172b.x(iO2));
            }
        }
    }

    interface k {
        void a(long j);
    }

    private void G1(View view, com.google.android.material.datepicker.g gVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(c.a.a.a.f.h);
        materialButton.setTag(d0);
        t.j0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(c.a.a.a.f.j);
        materialButton2.setTag(b0);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(c.a.a.a.f.i);
        materialButton3.setTag(c0);
        this.m0 = view.findViewById(c.a.a.a.f.o);
        this.n0 = view.findViewById(c.a.a.a.f.l);
        Q1(CalendarSelector.DAY);
        materialButton.setText(this.h0.z());
        this.l0.l(new g(gVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(gVar));
        materialButton2.setOnClickListener(new j(gVar));
    }

    private RecyclerView.n H1() {
        return new e();
    }

    static int M1(Context context) {
        return context.getResources().getDimensionPixelSize(c.a.a.a.d.A);
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

    CalendarConstraints I1() {
        return this.g0;
    }

    com.google.android.material.datepicker.b J1() {
        return this.j0;
    }

    Month K1() {
        return this.h0;
    }

    public DateSelector<S> L1() {
        return this.f0;
    }

    LinearLayoutManager N1() {
        return (LinearLayoutManager) this.l0.getLayoutManager();
    }

    void P1(Month month) {
        com.google.android.material.datepicker.g gVar = (com.google.android.material.datepicker.g) this.l0.getAdapter();
        int iZ = gVar.z(month);
        int iZ2 = iZ - gVar.z(this.h0);
        boolean z = Math.abs(iZ2) > 3;
        boolean z2 = iZ2 > 0;
        this.h0 = month;
        if (z && z2) {
            this.l0.j1(iZ - 3);
            O1(iZ);
        } else if (!z) {
            O1(iZ);
        } else {
            this.l0.j1(iZ + 3);
            O1(iZ);
        }
    }

    void Q1(CalendarSelector calendarSelector) {
        this.i0 = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.k0.getLayoutManager().G1(((m) this.k0.getAdapter()).y(this.h0.f4179e));
            this.m0.setVisibility(0);
            this.n0.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.m0.setVisibility(8);
            this.n0.setVisibility(0);
            P1(this.h0);
        }
    }

    void R1() {
        CalendarSelector calendarSelector = this.i0;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            Q1(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            Q1(calendarSelector2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void k0(Bundle bundle) {
        super.k0(bundle);
        if (bundle == null) {
            bundle = v();
        }
        this.e0 = bundle.getInt("THEME_RES_ID_KEY");
        this.f0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.g0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.h0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View o0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(x(), this.e0);
        this.j0 = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthZ = this.g0.z();
        if (com.google.android.material.datepicker.e.z1(contextThemeWrapper)) {
            i2 = c.a.a.a.h.p;
            i3 = 1;
        } else {
            i2 = c.a.a.a.h.n;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(c.a.a.a.f.m);
        t.j0(gridView, new b());
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.d());
        gridView.setNumColumns(monthZ.f);
        gridView.setEnabled(false);
        this.l0 = (RecyclerView) viewInflate.findViewById(c.a.a.a.f.n);
        this.l0.setLayoutManager(new c(x(), i3, false, i3));
        this.l0.setTag(a0);
        com.google.android.material.datepicker.g gVar = new com.google.android.material.datepicker.g(contextThemeWrapper, this.f0, this.g0, new d());
        this.l0.setAdapter(gVar);
        int integer = contextThemeWrapper.getResources().getInteger(c.a.a.a.g.f2283b);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(c.a.a.a.f.o);
        this.k0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.k0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.k0.setAdapter(new m(this));
            this.k0.h(H1());
        }
        if (viewInflate.findViewById(c.a.a.a.f.h) != null) {
            G1(viewInflate, gVar);
        }
        if (!com.google.android.material.datepicker.e.z1(contextThemeWrapper)) {
            new androidx.recyclerview.widget.i().b(this.l0);
        }
        this.l0.j1(gVar.z(this.h0));
        return viewInflate;
    }
}
