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
import java.util.Calendar;
import java.util.Iterator;
import o0.t;
import u5.l;
import u5.m;

/* JADX INFO: loaded from: classes2.dex */
public final class MaterialCalendar<S> extends u5.i<S> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Object f3816e = "MONTHS_VIEW_GROUP_TAG";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Object f3817f = "NAVIGATION_PREV_TAG";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Object f3818g = "NAVIGATION_NEXT_TAG";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Object f3819h = "SELECTOR_TOGGLE_TAG";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3820i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public DateSelector<S> f3821j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public CalendarConstraints f3822k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Month f3823l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CalendarSelector f3824m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public u5.b f3825n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public RecyclerView f3826o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public RecyclerView f3827p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f3828q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f3829r;

    public enum CalendarSelector {
        DAY,
        YEAR
    }

    public class a implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3830d;

        public a(int i8) {
            this.f3830d = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.f3827p.smoothScrollToPosition(this.f3830d);
        }
    }

    public class b extends o0.a {
        public b() {
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            cVar.e0(null);
        }
    }

    public class c extends u5.j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3833a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, int i8, boolean z7, int i9) {
            super(context, i8, z7);
            this.f3833a = i9;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(RecyclerView.z zVar, int[] iArr) {
            if (this.f3833a == 0) {
                iArr[0] = MaterialCalendar.this.f3827p.getWidth();
                iArr[1] = MaterialCalendar.this.f3827p.getWidth();
            } else {
                iArr[0] = MaterialCalendar.this.f3827p.getHeight();
                iArr[1] = MaterialCalendar.this.f3827p.getHeight();
            }
        }
    }

    public class d implements k {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.datepicker.MaterialCalendar.k
        public void a(long j8) {
            if (MaterialCalendar.this.f3822k.g().b(j8)) {
                MaterialCalendar.this.f3821j.e(j8);
                Iterator<u5.h<S>> it = MaterialCalendar.this.f8278d.iterator();
                while (it.hasNext()) {
                    it.next().a(MaterialCalendar.this.f3821j.d());
                }
                MaterialCalendar.this.f3827p.getAdapter().notifyDataSetChanged();
                if (MaterialCalendar.this.f3826o != null) {
                    MaterialCalendar.this.f3826o.getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    public class e extends RecyclerView.n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Calendar f3836a = l.l();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Calendar f3837b = l.l();

        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof m) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                m mVar = (m) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (n0.d<Long, Long> dVar : MaterialCalendar.this.f3821j.a()) {
                    Long l8 = dVar.f6506a;
                    if (l8 != null && dVar.f6507b != null) {
                        this.f3836a.setTimeInMillis(l8.longValue());
                        this.f3837b.setTimeInMillis(dVar.f6507b.longValue());
                        int iC = mVar.c(this.f3836a.get(1));
                        int iC2 = mVar.c(this.f3837b.get(1));
                        View viewFindViewByPosition = gridLayoutManager.findViewByPosition(iC);
                        View viewFindViewByPosition2 = gridLayoutManager.findViewByPosition(iC2);
                        int iK = iC / gridLayoutManager.k();
                        int iK2 = iC2 / gridLayoutManager.k();
                        int i8 = iK;
                        while (i8 <= iK2) {
                            if (gridLayoutManager.findViewByPosition(gridLayoutManager.k() * i8) != null) {
                                canvas.drawRect(i8 == iK ? viewFindViewByPosition.getLeft() + (viewFindViewByPosition.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.f3825n.f8253d.c(), i8 == iK2 ? viewFindViewByPosition2.getLeft() + (viewFindViewByPosition2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.f3825n.f8253d.b(), MaterialCalendar.this.f3825n.f8257h);
                            }
                            i8++;
                        }
                    }
                }
            }
        }
    }

    public class f extends o0.a {
        public f() {
        }

        @Override // o0.a
        public void g(View view, p0.c cVar) {
            super.g(view, cVar);
            cVar.m0(MaterialCalendar.this.f3829r.getVisibility() == 0 ? MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_year_selection) : MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_day_selection));
        }
    }

    public class g extends RecyclerView.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ u5.g f3840a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ MaterialButton f3841b;

        public g(u5.g gVar, MaterialButton materialButton) {
            this.f3840a = gVar;
            this.f3841b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrollStateChanged(RecyclerView recyclerView, int i8) {
            if (i8 == 0) {
                CharSequence text = this.f3841b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void onScrolled(RecyclerView recyclerView, int i8, int i9) {
            int iFindFirstVisibleItemPosition = i8 < 0 ? MaterialCalendar.this.o().findFirstVisibleItemPosition() : MaterialCalendar.this.o().findLastVisibleItemPosition();
            MaterialCalendar.this.f3823l = this.f3840a.b(iFindFirstVisibleItemPosition);
            this.f3841b.setText(this.f3840a.c(iFindFirstVisibleItemPosition));
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ u5.g f3844d;

        public i(u5.g gVar) {
            this.f3844d = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iFindFirstVisibleItemPosition = MaterialCalendar.this.o().findFirstVisibleItemPosition() + 1;
            if (iFindFirstVisibleItemPosition < MaterialCalendar.this.f3827p.getAdapter().getItemCount()) {
                MaterialCalendar.this.q(this.f3844d.b(iFindFirstVisibleItemPosition));
            }
        }
    }

    public class j implements View.OnClickListener {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ u5.g f3846d;

        public j(u5.g gVar) {
            this.f3846d = gVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iFindLastVisibleItemPosition = MaterialCalendar.this.o().findLastVisibleItemPosition() - 1;
            if (iFindLastVisibleItemPosition >= 0) {
                MaterialCalendar.this.q(this.f3846d.b(iFindLastVisibleItemPosition));
            }
        }
    }

    public interface k {
        void a(long j8);
    }

    public static int n(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.mtrl_calendar_day_height);
    }

    public final void h(View view, u5.g gVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(R$id.month_navigation_fragment_toggle);
        materialButton.setTag(f3819h);
        t.l0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(R$id.month_navigation_previous);
        materialButton2.setTag(f3817f);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(R$id.month_navigation_next);
        materialButton3.setTag(f3818g);
        this.f3828q = view.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.f3829r = view.findViewById(R$id.mtrl_calendar_day_selector_frame);
        r(CalendarSelector.DAY);
        materialButton.setText(this.f3823l.k());
        this.f3827p.addOnScrollListener(new g(gVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(gVar));
        materialButton2.setOnClickListener(new j(gVar));
    }

    public final RecyclerView.n i() {
        return new e();
    }

    public CalendarConstraints j() {
        return this.f3822k;
    }

    public u5.b k() {
        return this.f3825n;
    }

    public Month l() {
        return this.f3823l;
    }

    public DateSelector<S> m() {
        return this.f3821j;
    }

    public LinearLayoutManager o() {
        return (LinearLayoutManager) this.f3827p.getLayoutManager();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f3820i = bundle.getInt("THEME_RES_ID_KEY");
        this.f3821j = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.f3822k = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.f3823l = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i8;
        int i9;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.f3820i);
        this.f3825n = new u5.b(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthK = this.f3822k.k();
        if (u5.e.a(contextThemeWrapper)) {
            i8 = R$layout.mtrl_calendar_vertical;
            i9 = 1;
        } else {
            i8 = R$layout.mtrl_calendar_horizontal;
            i9 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i8, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(R$id.mtrl_calendar_days_of_week);
        t.l0(gridView, new b());
        gridView.setAdapter((ListAdapter) new u5.d());
        gridView.setNumColumns(monthK.f3854h);
        gridView.setEnabled(false);
        this.f3827p = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_months);
        this.f3827p.setLayoutManager(new c(getContext(), i9, false, i9));
        this.f3827p.setTag(f3816e);
        u5.g gVar = new u5.g(contextThemeWrapper, this.f3821j, this.f3822k, new d());
        this.f3827p.setAdapter(gVar);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.f3826o = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.f3826o.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.f3826o.setAdapter(new m(this));
            this.f3826o.addItemDecoration(i());
        }
        if (viewInflate.findViewById(R$id.month_navigation_fragment_toggle) != null) {
            h(viewInflate, gVar);
        }
        if (!u5.e.a(contextThemeWrapper)) {
            new c1.h().attachToRecyclerView(this.f3827p);
        }
        this.f3827p.scrollToPosition(gVar.d(this.f3823l));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f3820i);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.f3821j);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f3822k);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.f3823l);
    }

    public final void p(int i8) {
        this.f3827p.post(new a(i8));
    }

    public void q(Month month) {
        u5.g gVar = (u5.g) this.f3827p.getAdapter();
        int iD = gVar.d(month);
        int iD2 = iD - gVar.d(this.f3823l);
        boolean z7 = Math.abs(iD2) > 3;
        boolean z8 = iD2 > 0;
        this.f3823l = month;
        if (z7 && z8) {
            this.f3827p.scrollToPosition(iD - 3);
            p(iD);
        } else if (!z7) {
            p(iD);
        } else {
            this.f3827p.scrollToPosition(iD + 3);
            p(iD);
        }
    }

    public void r(CalendarSelector calendarSelector) {
        this.f3824m = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.f3826o.getLayoutManager().scrollToPosition(((m) this.f3826o.getAdapter()).c(this.f3823l.f3853g));
            this.f3828q.setVisibility(0);
            this.f3829r.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.f3828q.setVisibility(8);
            this.f3829r.setVisibility(0);
            q(this.f3823l);
        }
    }

    public void s() {
        CalendarSelector calendarSelector = this.f3824m;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            r(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            r(calendarSelector2);
        }
    }
}
