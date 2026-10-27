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
    static final Object f3519c = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final Object f3520d = "NAVIGATION_PREV_TAG";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final Object f3521e = "NAVIGATION_NEXT_TAG";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    static final Object f3522f = "SELECTOR_TOGGLE_TAG";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3523g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private DateSelector<S> f3524h;
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
        final /* synthetic */ int f3526b;

        a(int i) {
            this.f3526b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.n.p1(this.f3526b);
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
                MaterialCalendar.this.f3524h.d(j);
                Iterator<com.google.android.material.datepicker.h<S>> it = MaterialCalendar.this.f3589b.iterator();
                while (it.hasNext()) {
                    it.next().a(MaterialCalendar.this.f3524h.a());
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
        private final Calendar f3530a = l.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Calendar f3531b = l.l();

        e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof m) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                m mVar = (m) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (b.g.j.d<Long, Long> dVar : MaterialCalendar.this.f3524h.b()) {
                    Long l = dVar.f2504a;
                    if (l != null && dVar.f2505b != null) {
                        this.f3530a.setTimeInMillis(l.longValue());
                        this.f3531b.setTimeInMillis(dVar.f2505b.longValue());
                        int iX = mVar.x(this.f3530a.get(1));
                        int iX2 = mVar.x(this.f3531b.get(1));
                        View viewC = gridLayoutManager.C(iX);
                        View viewC2 = gridLayoutManager.C(iX2);
                        int iX22 = iX / gridLayoutManager.X2();
                        int iX23 = iX2 / gridLayoutManager.X2();
                        int i = iX22;
                        while (i <= iX23) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i) != null) {
                                canvas.drawRect(i == iX22 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.l.f3566d.c(), i == iX23 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.l.f3566d.b(), MaterialCalendar.this.l.f3570h);
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
            cVar.l0(MaterialCalendar.this.p.getVisibility() == 0 ? MaterialCalendar.this.getString(d.b.a.a.j.q) : MaterialCalendar.this.getString(d.b.a.a.j.p));
        }
    }

    class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f3534a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ MaterialButton f3535b;

        g(com.google.android.material.datepicker.g gVar, MaterialButton materialButton) {
            this.f3534a = gVar;
            this.f3535b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.f3535b.getText();
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
            MaterialCalendar.this.j = this.f3534a.w(iZ1);
            this.f3535b.setText(this.f3534a.x(iZ1));
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
        final /* synthetic */ com.google.android.material.datepicker.g f3538b;

        i(com.google.android.material.datepicker.g gVar) {
            this.f3538b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = MaterialCalendar.this.o().Z1() + 1;
            if (iZ1 < MaterialCalendar.this.n.getAdapter().c()) {
                MaterialCalendar.this.q(this.f3538b.w(iZ1));
            }
        }
    }

    class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.datepicker.g f3540b;

        j(com.google.android.material.datepicker.g gVar) {
            this.f3540b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = MaterialCalendar.this.o().c2() - 1;
            if (iC2 >= 0) {
                MaterialCalendar.this.q(this.f3540b.w(iC2));
            }
        }
    }

    interface k {
        void a(long j);
    }

    private void h(View view, com.google.android.material.datepicker.g gVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(d.b.a.a.f.f4724h);
        materialButton.setTag(f3522f);
        t.j0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(d.b.a.a.f.j);
        materialButton2.setTag(f3520d);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(d.b.a.a.f.i);
        materialButton3.setTag(f3521e);
        this.o = view.findViewById(d.b.a.a.f.o);
        this.p = view.findViewById(d.b.a.a.f.l);
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
        return context.getResources().getDimensionPixelSize(d.b.a.a.d.A);
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
        return this.f3524h;
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
        this.f3523g = bundle.getInt("THEME_RES_ID_KEY");
        this.f3524h = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.i = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.j = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f3523g);
        this.l = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthK = this.i.k();
        if (com.google.android.material.datepicker.e.a(contextThemeWrapper)) {
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
        gridView.setNumColumns(monthK.f3548f);
        gridView.setEnabled(false);
        this.n = (RecyclerView) viewInflate.findViewById(d.b.a.a.f.n);
        this.n.setLayoutManager(new c(getContext(), i3, false, i3));
        this.n.setTag(f3519c);
        com.google.android.material.datepicker.g gVar = new com.google.android.material.datepicker.g(contextThemeWrapper, this.f3524h, this.i, new d());
        this.n.setAdapter(gVar);
        int integer = contextThemeWrapper.getResources().getInteger(d.b.a.a.g.f4726b);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(d.b.a.a.f.o);
        this.m = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.m.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.m.setAdapter(new m(this));
            this.m.h(i());
        }
        if (viewInflate.findViewById(d.b.a.a.f.f4724h) != null) {
            h(viewInflate, gVar);
        }
        if (!com.google.android.material.datepicker.e.a(contextThemeWrapper)) {
            new androidx.recyclerview.widget.h().b(this.n);
        }
        this.n.h1(gVar.y(this.j));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f3523g);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f3524h);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.i);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.j);
    }

    void q(Month month) {
        com.google.android.material.datepicker.g gVar = (com.google.android.material.datepicker.g) this.n.getAdapter();
        int iY = gVar.y(month);
        int iY2 = iY - gVar.y(this.j);
        boolean z = Math.abs(iY2) > 3;
        boolean z2 = iY2 > 0;
        this.j = month;
        if (z && z2) {
            this.n.h1(iY - 3);
            p(iY);
        } else if (!z) {
            p(iY);
        } else {
            this.n.h1(iY + 3);
            p(iY);
        }
    }

    void r(CalendarSelector calendarSelector) {
        this.k = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.m.getLayoutManager().x1(((m) this.m.getAdapter()).x(this.j.f3547e));
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
