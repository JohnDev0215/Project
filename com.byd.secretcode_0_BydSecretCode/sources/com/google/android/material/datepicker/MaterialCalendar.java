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
import defpackage.a9;
import defpackage.bd;
import defpackage.di;
import defpackage.ea;
import defpackage.fi;
import defpackage.gi;
import defpackage.ii;
import defpackage.ji;
import defpackage.ki;
import defpackage.li;
import defpackage.ni;
import defpackage.oi;
import defpackage.t9;
import defpackage.v8;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialCalendar<S> extends ki<S> {
    public static final Object c = "MONTHS_VIEW_GROUP_TAG";
    public static final Object d = "NAVIGATION_PREV_TAG";
    public static final Object e = "NAVIGATION_NEXT_TAG";
    public static final Object f = "SELECTOR_TOGGLE_TAG";
    public int g;
    public DateSelector<S> h;
    public CalendarConstraints i;
    public Month j;
    public CalendarSelector k;
    public di l;
    public RecyclerView m;
    public RecyclerView n;
    public View o;
    public View p;

    public enum CalendarSelector {
        DAY,
        YEAR
    }

    public class a implements Runnable {
        public final /* synthetic */ int b;

        public a(int i) {
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.n.q1(this.b);
        }
    }

    public class b extends a9 {
        public b() {
        }

        @Override // defpackage.a9
        public void g(View view, ea eaVar) {
            super.g(view, eaVar);
            eaVar.d0(null);
        }
    }

    public class c extends li {
        public final /* synthetic */ int I;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.I = i2;
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
            if (MaterialCalendar.this.i.g().c(j)) {
                MaterialCalendar.this.h.d(j);
                Iterator<ji<S>> it = MaterialCalendar.this.b.iterator();
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

    public class e extends RecyclerView.n {
        public final Calendar a = ni.l();
        public final Calendar b = ni.l();

        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.n
        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
            if ((recyclerView.getAdapter() instanceof oi) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                oi oiVar = (oi) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (v8<Long, Long> v8Var : MaterialCalendar.this.h.b()) {
                    Long l = v8Var.a;
                    if (l != null && v8Var.b != null) {
                        this.a.setTimeInMillis(l.longValue());
                        this.b.setTimeInMillis(v8Var.b.longValue());
                        int iW = oiVar.w(this.a.get(1));
                        int iW2 = oiVar.w(this.b.get(1));
                        View viewC = gridLayoutManager.C(iW);
                        View viewC2 = gridLayoutManager.C(iW2);
                        int iY2 = iW / gridLayoutManager.Y2();
                        int iY22 = iW2 / gridLayoutManager.Y2();
                        int i = iY2;
                        while (i <= iY22) {
                            if (gridLayoutManager.C(gridLayoutManager.Y2() * i) != null) {
                                canvas.drawRect(i == iY2 ? viewC.getLeft() + (viewC.getWidth() / 2) : 0, r9.getTop() + MaterialCalendar.this.l.d.c(), i == iY22 ? viewC2.getLeft() + (viewC2.getWidth() / 2) : recyclerView.getWidth(), r9.getBottom() - MaterialCalendar.this.l.d.b(), MaterialCalendar.this.l.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    public class f extends a9 {
        public f() {
        }

        @Override // defpackage.a9
        public void g(View view, ea eaVar) {
            super.g(view, eaVar);
            eaVar.l0(MaterialCalendar.this.p.getVisibility() == 0 ? MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_year_selection) : MaterialCalendar.this.getString(R$string.mtrl_picker_toggle_to_day_selection));
        }
    }

    public class g extends RecyclerView.t {
        public final /* synthetic */ ii a;
        public final /* synthetic */ MaterialButton b;

        public g(ii iiVar, MaterialButton materialButton) {
            this.a = iiVar;
            this.b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.b.getText();
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
            MaterialCalendar.this.j = this.a.v(iZ1);
            this.b.setText(this.a.w(iZ1));
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
        public final /* synthetic */ ii b;

        public i(ii iiVar) {
            this.b = iiVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iZ1 = MaterialCalendar.this.o().Z1() + 1;
            if (iZ1 < MaterialCalendar.this.n.getAdapter().c()) {
                MaterialCalendar.this.q(this.b.v(iZ1));
            }
        }
    }

    public class j implements View.OnClickListener {
        public final /* synthetic */ ii b;

        public j(ii iiVar) {
            this.b = iiVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iC2 = MaterialCalendar.this.o().c2() - 1;
            if (iC2 >= 0) {
                MaterialCalendar.this.q(this.b.v(iC2));
            }
        }
    }

    public interface k {
        void a(long j);
    }

    public static int n(Context context) {
        return context.getResources().getDimensionPixelSize(R$dimen.mtrl_calendar_day_height);
    }

    public final void h(View view, ii iiVar) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(R$id.month_navigation_fragment_toggle);
        materialButton.setTag(f);
        t9.j0(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(R$id.month_navigation_previous);
        materialButton2.setTag(d);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(R$id.month_navigation_next);
        materialButton3.setTag(e);
        this.o = view.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.p = view.findViewById(R$id.mtrl_calendar_day_selector_frame);
        r(CalendarSelector.DAY);
        materialButton.setText(this.j.k());
        this.n.l(new g(iiVar, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(iiVar));
        materialButton2.setOnClickListener(new j(iiVar));
    }

    public final RecyclerView.n i() {
        return new e();
    }

    public CalendarConstraints j() {
        return this.i;
    }

    public di k() {
        return this.l;
    }

    public Month l() {
        return this.j;
    }

    public DateSelector<S> m() {
        return this.h;
    }

    public LinearLayoutManager o() {
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
        this.l = new di(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month monthK = this.i.k();
        if (gi.a(contextThemeWrapper)) {
            i2 = R$layout.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = R$layout.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(R$id.mtrl_calendar_days_of_week);
        t9.j0(gridView, new b());
        gridView.setAdapter((ListAdapter) new fi());
        gridView.setNumColumns(monthK.f);
        gridView.setEnabled(false);
        this.n = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_months);
        this.n.setLayoutManager(new c(getContext(), i3, false, i3));
        this.n.setTag(c);
        ii iiVar = new ii(contextThemeWrapper, this.h, this.i, new d());
        this.n.setAdapter(iiVar);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_year_selector_frame);
        this.m = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.m.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.m.setAdapter(new oi(this));
            this.m.h(i());
        }
        if (viewInflate.findViewById(R$id.month_navigation_fragment_toggle) != null) {
            h(viewInflate, iiVar);
        }
        if (!gi.a(contextThemeWrapper)) {
            new bd().b(this.n);
        }
        this.n.i1(iiVar.x(this.j));
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

    public final void p(int i2) {
        this.n.post(new a(i2));
    }

    public void q(Month month) {
        ii iiVar = (ii) this.n.getAdapter();
        int iX = iiVar.x(month);
        int iX2 = iX - iiVar.x(this.j);
        boolean z = Math.abs(iX2) > 3;
        boolean z2 = iX2 > 0;
        this.j = month;
        if (z && z2) {
            this.n.i1(iX - 3);
            p(iX);
        } else if (!z) {
            p(iX);
        } else {
            this.n.i1(iX + 3);
            p(iX);
        }
    }

    public void r(CalendarSelector calendarSelector) {
        this.k = calendarSelector;
        if (calendarSelector == CalendarSelector.YEAR) {
            this.m.getLayoutManager().x1(((oi) this.m.getAdapter()).w(this.j.e));
            this.o.setVisibility(0);
            this.p.setVisibility(8);
        } else if (calendarSelector == CalendarSelector.DAY) {
            this.o.setVisibility(8);
            this.p.setVisibility(0);
            q(this.j);
        }
    }

    public void s() {
        CalendarSelector calendarSelector = this.k;
        CalendarSelector calendarSelector2 = CalendarSelector.YEAR;
        if (calendarSelector == calendarSelector2) {
            r(CalendarSelector.DAY);
        } else if (calendarSelector == CalendarSelector.DAY) {
            r(calendarSelector2);
        }
    }
}
