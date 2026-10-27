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

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final Object f2293c = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Object f2294d = "NAVIGATION_PREV_TAG";
    static final Object e = "NAVIGATION_NEXT_TAG";
    static final Object f = "SELECTOR_TOGGLE_TAG";
    private int g;
    private DateSelector<S> h;
    private CalendarConstraints i;
    private Month j;
    private CalendarSelector k;
    private com.google.android.material.datepicker.b l;
    private RecyclerView m;
    private RecyclerView n;
    private View o;
    private View p;

    enum CalendarSelector {
        DAY,
        YEAR
    }

    class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f2296b;

        a(int i) {
            this.f2296b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.n.p1(this.f2296b);
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
        final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.I = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        protected void M1(RecyclerView.z zVar, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = MaterialCalendar.this.n.getWidth();
                iArr[1] = MaterialCalendar.this.n.getWidth();
            } else {
                iArr[0] = MaterialCalendar.this.n.getHeight();
                iArr[1] = MaterialCalendar.this.n.getHeight();
            }
        }
    }

    class d implements k {
        d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.k
        public void a(long j) {
            if (MaterialCalendar.this.i.g().c(j)) {
                MaterialCalendar.this.h.d(j);
                Iterator<com.google.android.material.datepicker.h<S>> it = MaterialCalendar.this.f2342b.iterator();
                while (it.hasNext()) {
                    it.next().a(MaterialCalendar.this.h.a());
                }
                MaterialCalendar.this.n.getAdapter().h();
                if (MaterialCalendar.this.m != null) {
                    MaterialCalendar.this.m.getAdapter().h();
                }
            }
        }
    }

    class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Calendar f2300a = l.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f2301b = l.l();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof m) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                m mVar = (m) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (b.g.j.d<Long, Long> dVar : MaterialCalendar.this.h.b()) {
                    Long l = dVar.f1702a;
                    if (l != null && dVar.f1703b != null) {
                        this.f2300a.setTimeInMillis(l.longValue());
                        this.f2301b.setTimeInMillis(dVar.f1703b.longValue());
                        int iW = mVar.w(this.f2300a.get(1));
                        int iW2 = mVar.w(this.f2301b.get(1));
                        View viewC = gridLayoutManager.C(iW);
                        View viewC2 = gridLayoutManager.C(iW2);
                        int iX2 = iW / gridLayoutManager.X2();
                        int iX22 = iW2 / gridLayoutManager.X2();
                        int i = iX2;
                        while (i <= iX22) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i) != null) {
                                canvas.drawRect(i == iX2 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.l.f2328d.c(), i == iX22 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.l.f2328d.b(), MaterialCalendar.this.l.h);
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
            cVar.l0(MaterialCalendar.this.p.getVisibility() == 0 ? MaterialCalendar.this.getString(c.a.a.a.j.q) : MaterialCalendar.this.getString(c.a.a.a.j.p));
        }
    }

    class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f2304a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f2305b;

        g(com.google.android.material.datepicker.g gVar, MaterialButton materialButton) {
            this.f2304a = gVar;
            this.f2305b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.f2305b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            int iZ1 = i < 0 ? MaterialCalendar.this.o().Z1() : MaterialCalendar.this.o().c2();
            MaterialCalendar.this.j = this.f2304a.v(iZ1);
            this.f2305b.setText(this.f2304a.w(iZ1));
        }
    }

    class h implements View.OnClickListener {
        h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.s();
        }
    }

    class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f2308b;

        i(com.google.android.material.datepicker.g gVar) {
            this.f2308b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = MaterialCalendar.this.o().Z1() + 1;
            if (iZ1 < MaterialCalendar.this.n.getAdapter().c()) {
                MaterialCalendar.this.q(this.f2308b.v(iZ1));
            }
        }
    }

    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f2310b;

        j(com.google.android.material.datepicker.g gVar) {
            this.f2310b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = MaterialCalendar.this.o().c2() - 1;
            if (iC2 >= 0) {
                MaterialCalendar.this.q(this.f2310b.v(iC2));
            }
        }
    }

    interface k {
        void a(long j);
    }

    private void h(View view, com.google.android.material.datepicker.g gVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(c.a.a.a.f.h);
        materialButton.setTag(f);
        t.j0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(c.a.a.a.f.j);
        materialButton2.setTag(f2294d);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(c.a.a.a.f.i);
        materialButton3.setTag(e);
        this.o = view.findViewById(c.a.a.a.f.o);
        this.p = view.findViewById(c.a.a.a.f.l);
        r(CalendarSelector.DAY);
        materialButton.setText(this.j.k());
        this.n.l(new g(gVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(gVar));
        materialButton2.setOnClickListener(new j(gVar));
    }

    private RecyclerView.n i() {
        return new e();
    }

    static int n(Context context) {
        return context.getResources().getDimensionPixelSize(c.a.a.a.d.A);
    }

    private void p(int i2) {
        this.n.post(new a(i2));
    }

    CalendarConstraints j() {
        return this.i;
    }

    com.google.android.material.datepicker.b k() {
        return this.l;
    }

    Month l() {
        return this.j;
    }

    public DateSelector<S> m() {
        return this.h;
    }

    LinearLayoutManager o() {
        return (LinearLayoutManager) this.n.getLayoutManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.g = bundle.getInt("THEME_RES_ID_KEY");
        this.h = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.i = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.j = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.g);
        this.l = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthK = this.i.k();
        if (com.google.android.material.datepicker.e.a(contextThemeWrapper)) {
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
        gridView.setNumColumns(monthK.f);
        gridView.setEnabled(false);
        this.n = (RecyclerView) viewInflate.findViewById(c.a.a.a.f.n);
        this.n.setLayoutManager(new c(getContext(), i3, false, i3));
        this.n.setTag(f2293c);
        com.google.android.material.datepicker.g gVar = new com.google.android.material.datepicker.g(contextThemeWrapper, this.h, this.i, new d());
        this.n.setAdapter(gVar);
        int integer = contextThemeWrapper.getResources().getInteger(c.a.a.a.g.f1992b);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(c.a.a.a.f.o);
        this.m = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.m.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.m.setAdapter(new m(this));
            this.m.h(i());
        }
        if (viewInflate.findViewById(c.a.a.a.f.h) != null) {
            h(viewInflate, gVar);
        }
        if (!com.google.android.material.datepicker.e.a(contextThemeWrapper)) {
            new androidx.recyclerview.widget.h().b(this.n);
        }
        this.n.h1(gVar.x(this.j));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.g);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.h);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.i);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.j);
    }

    void q(Month month) {
        com.google.android.material.datepicker.g gVar = (com.google.android.material.datepicker.g) this.n.getAdapter();
        int iX = gVar.x(month);
        int iX2 = iX - gVar.x(this.j);
        boolean z = Math.abs(iX2) > 3;
        boolean z2 = iX2 > 0;
        this.j = month;
        if (z && z2) {
            this.n.h1(iX - 3);
            p(iX);
        } else if (!z) {
            p(iX);
        } else {
            this.n.h1(iX + 3);
            p(iX);
        }
    }

    void r(CalendarSelector calendarSelector) {
        this.k = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.m.getLayoutManager().x1(((m) this.m.getAdapter()).w(this.j.e));
            this.o.setVisibility(0);
            this.p.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.o.setVisibility(8);
            this.p.setVisibility(0);
            q(this.j);
        }
    }

    void s() {
        CalendarSelector calendarSelector = this.k;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            r(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            r(calendarSelector2);
        }
    }
}
