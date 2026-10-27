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
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$integer;
import com.google.android.material.R$layout;
import com.google.android.material.R$string;
import com.google.android.material.button.MaterialButton;
import d2.l;
import d2.m;
import java.util.Calendar;
import java.util.Iterator;
import l0.t;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialCalendar<S> extends d2.i<S> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f2233c = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f2234d = "NAVIGATION_PREV_TAG";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f2235e = "NAVIGATION_NEXT_TAG";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f2236f = "SELECTOR_TOGGLE_TAG";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2237g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public DateSelector<S> f2238h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public CalendarConstraints f2239i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Month f2240j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CalendarSelector f2241k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d2.b f2242l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public RecyclerView f2243m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public RecyclerView f2244n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f2245o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f2246p;

    public enum CalendarSelector {
        DAY,
        YEAR
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2247b;

        public a(int i3) {
            this.f2247b = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.f2244n.p1(this.f2247b);
        }
    }

    public class b extends l0.a {
        public b() {
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            cVar.d0(null);
        }
    }

    public class c extends d2.j {
        public final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, int i3, boolean z3, int i4) {
            super(context, i3, z3);
            this.I = i4;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void M1(RecyclerView.z zVar, int[] iArr) {
            if (this.I == 0) {
                iArr[0] = MaterialCalendar.this.f2244n.getWidth();
                iArr[1] = MaterialCalendar.this.f2244n.getWidth();
            } else {
                iArr[0] = MaterialCalendar.this.f2244n.getHeight();
                iArr[1] = MaterialCalendar.this.f2244n.getHeight();
            }
        }
    }

    public class d implements k {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.k
        public void a(long j3) {
            if (MaterialCalendar.this.f2239i.g().c(j3)) {
                MaterialCalendar.this.f2238h.d(j3);
                Iterator<d2.h<S>> it = MaterialCalendar.this.f2736b.iterator();
                while (it.hasNext()) {
                    it.next().a(MaterialCalendar.this.f2238h.a());
                }
                MaterialCalendar.this.f2244n.getAdapter().h();
                if (MaterialCalendar.this.f2243m != null) {
                    MaterialCalendar.this.f2243m.getAdapter().h();
                }
            }
        }
    }

    public class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Calendar f2251a = l.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Calendar f2252b = l.l();

        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof m) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                m mVar = (m) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (k0.d<Long, Long> dVar : MaterialCalendar.this.f2238h.b()) {
                    Long l3 = dVar.f3579a;
                    if (l3 != null && dVar.f3580b != null) {
                        this.f2251a.setTimeInMillis(l3.longValue());
                        this.f2252b.setTimeInMillis(dVar.f3580b.longValue());
                        int iW = mVar.w(this.f2251a.get(1));
                        int iW2 = mVar.w(this.f2252b.get(1));
                        View viewC = gridLayoutManager.C(iW);
                        View viewC2 = gridLayoutManager.C(iW2);
                        int iX2 = iW / gridLayoutManager.X2();
                        int iX22 = iW2 / gridLayoutManager.X2();
                        int i3 = iX2;
                        while (i3 <= iX22) {
                            if (gridLayoutManager.C(gridLayoutManager.X2() * i3) != null) {
                                canvas.drawRect(i3 == iX2 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.f2242l.f2711d.c(), i3 == iX22 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.f2242l.f2711d.b(), MaterialCalendar.this.f2242l.f2715h);
                            }
                            i3++;
                        }
                    }
                }
            }
        }
    }

    public class f extends l0.a {
        public f() {
        }

        @Override // l0.a
        public void g(View view, m0.c cVar) {
            super.g(view, cVar);
            cVar.l0(MaterialCalendar.this.f2246p.getVisibility() == 0 ? MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_year_selection) : MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_day_selection));
        }
    }

    public class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d2.g f2255a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MaterialButton f2256b;

        public g(d2.g gVar, MaterialButton materialButton) {
            this.f2255a = gVar;
            this.f2256b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i3) {
            if (i3 == 0) {
                CharSequence text = this.f2256b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i3, int i4) {
            int iZ1 = i3 < 0 ? MaterialCalendar.this.o().Z1() : MaterialCalendar.this.o().c2();
            MaterialCalendar.this.f2240j = this.f2255a.v(iZ1);
            this.f2256b.setText(this.f2255a.w(iZ1));
        }
    }

    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.s();
        }
    }

    public class i implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d2.g f2259b;

        public i(d2.g gVar) {
            this.f2259b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = MaterialCalendar.this.o().Z1() + 1;
            if (iZ1 < MaterialCalendar.this.f2244n.getAdapter().c()) {
                MaterialCalendar.this.q(this.f2259b.v(iZ1));
            }
        }
    }

    public class j implements View.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ d2.g f2261b;

        public j(d2.g gVar) {
            this.f2261b = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = MaterialCalendar.this.o().c2() - 1;
            if (iC2 >= 0) {
                MaterialCalendar.this.q(this.f2261b.v(iC2));
            }
        }
    }

    public interface k {
        void a(long j3);
    }

    public static int n(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.mtrl_calendar_day_height);
    }

    public final void h(View view, d2.g gVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(R$id.month_navigation_fragment_toggle);
        materialButton.setTag(f2236f);
        t.j0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(R$id.month_navigation_previous);
        materialButton2.setTag(f2234d);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(R$id.month_navigation_next);
        materialButton3.setTag(f2235e);
        this.f2245o = view.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.f2246p = view.findViewById(R$id.mtrl_calendar_day_selector_frame);
        r(CalendarSelector.DAY);
        materialButton.setText(this.f2240j.k());
        this.f2244n.l(new g(gVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(gVar));
        materialButton2.setOnClickListener(new j(gVar));
    }

    public final RecyclerView.n i() {
        return new e();
    }

    public CalendarConstraints j() {
        return this.f2239i;
    }

    public d2.b k() {
        return this.f2242l;
    }

    public Month l() {
        return this.f2240j;
    }

    public DateSelector<S> m() {
        return this.f2238h;
    }

    public LinearLayoutManager o() {
        return (LinearLayoutManager) this.f2244n.getLayoutManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f2237g = bundle.getInt("THEME_RES_ID_KEY");
        this.f2238h = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f2239i = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f2240j = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i3;
        int i4;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f2237g);
        this.f2242l = new d2.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthK = this.f2239i.k();
        if (d2.e.a(contextThemeWrapper)) {
            i3 = R$layout.mtrl_calendar_vertical;
            i4 = 1;
        } else {
            i3 = R$layout.mtrl_calendar_horizontal;
            i4 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i3, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(R$id.mtrl_calendar_days_of_week);
        t.j0(gridView, new b());
        gridView.setAdapter((ListAdapter) new d2.d());
        gridView.setNumColumns(monthK.f2269f);
        gridView.setEnabled(false);
        this.f2244n = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_months);
        this.f2244n.setLayoutManager(new c(getContext(), i4, false, i4));
        this.f2244n.setTag(f2233c);
        d2.g gVar = new d2.g(contextThemeWrapper, this.f2238h, this.f2239i, new d());
        this.f2244n.setAdapter(gVar);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.f2243m = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f2243m.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.f2243m.setAdapter(new m(this));
            this.f2243m.h(i());
        }
        if (viewInflate.findViewById(R$id.month_navigation_fragment_toggle) != null) {
            h(viewInflate, gVar);
        }
        if (!d2.e.a(contextThemeWrapper)) {
            new w0.h().b(this.f2244n);
        }
        this.f2244n.h1(gVar.x(this.f2240j));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f2237g);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f2238h);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f2239i);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f2240j);
    }

    public final void p(int i3) {
        this.f2244n.post(new a(i3));
    }

    public void q(Month month) {
        d2.g gVar = (d2.g) this.f2244n.getAdapter();
        int iX = gVar.x(month);
        int iX2 = iX - gVar.x(this.f2240j);
        boolean z3 = Math.abs(iX2) > 3;
        boolean z4 = iX2 > 0;
        this.f2240j = month;
        if (z3 && z4) {
            this.f2244n.h1(iX - 3);
            p(iX);
        } else if (!z3) {
            p(iX);
        } else {
            this.f2244n.h1(iX + 3);
            p(iX);
        }
    }

    public void r(CalendarSelector calendarSelector) {
        this.f2241k = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.f2243m.getLayoutManager().x1(((m) this.f2243m.getAdapter()).w(this.f2240j.f2268e));
            this.f2245o.setVisibility(0);
            this.f2246p.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.f2245o.setVisibility(8);
            this.f2246p.setVisibility(0);
            q(this.f2240j);
        }
    }

    public void s() {
        CalendarSelector calendarSelector = this.f2241k;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            r(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            r(calendarSelector2);
        }
    }
}
