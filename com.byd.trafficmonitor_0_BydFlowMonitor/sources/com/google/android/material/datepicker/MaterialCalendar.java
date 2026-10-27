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
import b.h.m.t;
import c.b.a.a.x.l;
import c.b.a.a.x.m;
import c.b.a.a.x.o;
import c.b.a.a.x.p;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialCalendar<S> extends l<S> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f4669c = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f4670d = "NAVIGATION_PREV_TAG";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f4671e = "NAVIGATION_NEXT_TAG";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f4672f = "SELECTOR_TOGGLE_TAG";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4673g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DateSelector<S> f4674h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CalendarConstraints f4675i;
    public Month j;
    public CalendarSelector k;
    public c.b.a.a.x.b l;
    public RecyclerView m;
    public RecyclerView n;
    public View o;
    public View p;

    public enum CalendarSelector {
        DAY,
        YEAR
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f4677b;

        public a(int i2) {
            this.f4677b = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.n.q1(this.f4677b);
        }
    }

    public class b extends b.h.m.a {
        public b() {
        }

        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            super.g(view, cVar);
            cVar.e0(null);
        }
    }

    public class c extends m {
        public final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, int i2, boolean z, int i3) {
            super(context, i2, z);
            this.I = i3;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void M1(RecyclerView.z zVar, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = MaterialCalendar.this.n.getWidth();
                iArr[1] = MaterialCalendar.this.n.getWidth();
            } else {
                iArr[0] = MaterialCalendar.this.n.getHeight();
                iArr[1] = MaterialCalendar.this.n.getHeight();
            }
        }
    }

    public class d implements k {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.k
        public void a(long j) {
            if (MaterialCalendar.this.f4675i.o().e(j)) {
                MaterialCalendar.this.f4674h.g(j);
                Iterator<c.b.a.a.x.k<S>> it = MaterialCalendar.this.f4141b.iterator();
                while (it.hasNext()) {
                    it.next().b(MaterialCalendar.this.f4674h.a());
                }
                MaterialCalendar.this.n.getAdapter().h();
                if (MaterialCalendar.this.m != null) {
                    MaterialCalendar.this.m.getAdapter().h();
                }
            }
        }
    }

    public class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Calendar f4681a = o.r();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Calendar f4682b = o.r();

        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof p) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                p pVar = (p) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (b.h.l.c<Long, Long> cVar : MaterialCalendar.this.f4674h.d()) {
                    Long l = cVar.f2575a;
                    if (l != null && cVar.f2576b != null) {
                        this.f4681a.setTimeInMillis(l.longValue());
                        this.f4682b.setTimeInMillis(cVar.f2576b.longValue());
                        int iY = pVar.y(this.f4681a.get(1));
                        int iY2 = pVar.y(this.f4682b.get(1));
                        View viewC = gridLayoutManager.C(iY);
                        View viewC2 = gridLayoutManager.C(iY2);
                        int iX2 = iY / gridLayoutManager.X2();
                        int iX22 = iY2 / gridLayoutManager.X2();
                        int i2 = iX2;
                        while (i2 <= iX22) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i2) != null) {
                                canvas.drawRect(i2 == iX2 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.l.f4109d.c(), i2 == iX22 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.l.f4109d.b(), MaterialCalendar.this.l.f4113h);
                            }
                            i2++;
                        }
                    }
                }
            }
        }
    }

    public class f extends b.h.m.a {
        public f() {
        }

        @Override // b.h.m.a
        public void g(View view, b.h.m.c0.c cVar) {
            super.g(view, cVar);
            cVar.m0(MaterialCalendar.this.p.getVisibility() == 0 ? MaterialCalendar.this.getString(c.b.a.a.j.mtrl_picker_toggle_to_year_selection) : MaterialCalendar.this.getString(c.b.a.a.j.mtrl_picker_toggle_to_day_selection));
        }
    }

    public class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ c.b.a.a.x.j f4685a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MaterialButton f4686b;

        public g(c.b.a.a.x.j jVar, MaterialButton materialButton) {
            this.f4685a = jVar;
            this.f4686b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i2) {
            if (i2 == 0) {
                CharSequence text = this.f4686b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i2, int i3) {
            int iZ1 = i2 < 0 ? MaterialCalendar.this.r().Z1() : MaterialCalendar.this.r().c2();
            MaterialCalendar.this.j = this.f4685a.x(iZ1);
            this.f4686b.setText(this.f4685a.y(iZ1));
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.w();
        }
    }

    public class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c.b.a.a.x.j f4689b;

        public i(c.b.a.a.x.j jVar) {
            this.f4689b = jVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = MaterialCalendar.this.r().Z1() + 1;
            if (iZ1 < MaterialCalendar.this.n.getAdapter().c()) {
                MaterialCalendar.this.u(this.f4689b.x(iZ1));
            }
        }
    }

    public class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ c.b.a.a.x.j f4691b;

        public j(c.b.a.a.x.j jVar) {
            this.f4691b = jVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = MaterialCalendar.this.r().c2() - 1;
            if (iC2 >= 0) {
                MaterialCalendar.this.u(this.f4691b.x(iC2));
            }
        }
    }

    public interface k {
        void a(long j);
    }

    public static int q(Context context) {
        return context.getResources().getDimensionPixelSize(c.b.a.a.d.mtrl_calendar_day_height);
    }

    public static <T> MaterialCalendar<T> s(DateSelector<T> dateSelector, int i2, CalendarConstraints calendarConstraints) {
        MaterialCalendar<T> materialCalendar = new MaterialCalendar<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.r());
        materialCalendar.setArguments(bundle);
        return materialCalendar;
    }

    public final void k(View view, c.b.a.a.x.j jVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(c.b.a.a.f.month_navigation_fragment_toggle);
        materialButton.setTag(f4672f);
        t.k0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(c.b.a.a.f.month_navigation_previous);
        materialButton2.setTag(f4670d);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(c.b.a.a.f.month_navigation_next);
        materialButton3.setTag(f4671e);
        this.o = view.findViewById(c.b.a.a.f.mtrl_calendar_year_selector_frame);
        this.p = view.findViewById(c.b.a.a.f.mtrl_calendar_day_selector_frame);
        v(CalendarSelector.DAY);
        materialButton.setText(this.j.p());
        this.n.l(new g(jVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(jVar));
        materialButton2.setOnClickListener(new j(jVar));
    }

    public final RecyclerView.n l() {
        return new e();
    }

    public CalendarConstraints m() {
        return this.f4675i;
    }

    public c.b.a.a.x.b n() {
        return this.l;
    }

    public Month o() {
        return this.j;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f4673g = bundle.getInt("THEME_RES_ID_KEY");
        this.f4674h = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f4675i = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.j = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f4673g);
        this.l = new c.b.a.a.x.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthS = this.f4675i.s();
        if (c.b.a.a.x.f.F(contextThemeWrapper)) {
            i2 = c.b.a.a.h.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = c.b.a.a.h.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(c.b.a.a.f.mtrl_calendar_days_of_week);
        t.k0(gridView, new b());
        gridView.setAdapter((ListAdapter) new c.b.a.a.x.e());
        gridView.setNumColumns(monthS.f4699f);
        gridView.setEnabled(false);
        this.n = (RecyclerView) viewInflate.findViewById(c.b.a.a.f.mtrl_calendar_months);
        this.n.setLayoutManager(new c(getContext(), i3, false, i3));
        this.n.setTag(f4669c);
        c.b.a.a.x.j jVar = new c.b.a.a.x.j(contextThemeWrapper, this.f4674h, this.f4675i, new d());
        this.n.setAdapter(jVar);
        int integer = contextThemeWrapper.getResources().getInteger(c.b.a.a.g.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(c.b.a.a.f.mtrl_calendar_year_selector_frame);
        this.m = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.m.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.m.setAdapter(new p(this));
            this.m.h(l());
        }
        if (viewInflate.findViewById(c.b.a.a.f.month_navigation_fragment_toggle) != null) {
            k(viewInflate, jVar);
        }
        if (!c.b.a.a.x.f.F(contextThemeWrapper)) {
            new b.r.d.h().b(this.n);
        }
        this.n.i1(jVar.z(this.j));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f4673g);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f4674h);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f4675i);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.j);
    }

    public DateSelector<S> p() {
        return this.f4674h;
    }

    public LinearLayoutManager r() {
        return (LinearLayoutManager) this.n.getLayoutManager();
    }

    public final void t(int i2) {
        this.n.post(new a(i2));
    }

    public void u(Month month) {
        c.b.a.a.x.j jVar = (c.b.a.a.x.j) this.n.getAdapter();
        int iZ = jVar.z(month);
        int iZ2 = iZ - jVar.z(this.j);
        boolean z = Math.abs(iZ2) > 3;
        boolean z2 = iZ2 > 0;
        this.j = month;
        if (z && z2) {
            this.n.i1(iZ - 3);
            t(iZ);
        } else if (!z) {
            t(iZ);
        } else {
            this.n.i1(iZ + 3);
            t(iZ);
        }
    }

    public void v(CalendarSelector calendarSelector) {
        this.k = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.m.getLayoutManager().x1(((p) this.m.getAdapter()).y(this.j.f4698e));
            this.o.setVisibility(0);
            this.p.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.o.setVisibility(8);
            this.p.setVisibility(0);
            u(this.j);
        }
    }

    public void w() {
        CalendarSelector calendarSelector = this.k;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            v(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            v(calendarSelector2);
        }
    }
}
