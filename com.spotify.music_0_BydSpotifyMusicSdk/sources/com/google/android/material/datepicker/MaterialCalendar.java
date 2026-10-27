package com.google.android.material.datepicker;

import android.R;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Scroller;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import p.ae2;
import p.bl1;
import p.cl1;
import p.d60;
import p.e6;
import p.ei2;
import p.ez2;
import p.ff1;
import p.gf1;
import p.hf1;
import p.if1;
import p.ip;
import p.jf1;
import p.jh1;
import p.mf1;
import p.mr1;
import p.p01;
import p.u4;
import p.ws1;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialCalendar<S> extends ws1 {
    public static final /* synthetic */ int l0 = 0;
    public int c0;
    public ip d0;
    public bl1 e0;
    public CalendarSelector f0;
    public jh1 g0;
    public RecyclerView h0;
    public RecyclerView i0;
    public View j0;
    public View k0;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class CalendarSelector {
        public static final CalendarSelector c;
        public static final CalendarSelector e;
        public static final /* synthetic */ CalendarSelector[] k;

        static {
            CalendarSelector calendarSelector = new CalendarSelector("DAY", 0);
            c = calendarSelector;
            CalendarSelector calendarSelector2 = new CalendarSelector("YEAR", 1);
            e = calendarSelector2;
            k = new CalendarSelector[]{calendarSelector, calendarSelector2};
        }

        public static CalendarSelector valueOf(String str) {
            return (CalendarSelector) Enum.valueOf(CalendarSelector.class, str);
        }

        public static CalendarSelector[] values() {
            return (CalendarSelector[]) k.clone();
        }
    }

    public final void F(bl1 bl1Var) {
        c cVar = (c) this.i0.getAdapter();
        int iE = cVar.d.c.e(bl1Var);
        int iE2 = iE - cVar.d.c.e(this.e0);
        int i = 1;
        boolean z = Math.abs(iE2) > 3;
        boolean z2 = iE2 > 0;
        this.e0 = bl1Var;
        if (z && z2) {
            this.i0.a0(iE - 3);
            this.i0.post(new ei2(iE, i, this));
        } else if (!z) {
            this.i0.post(new ei2(iE, i, this));
        } else {
            this.i0.a0(iE + 3);
            this.i0.post(new ei2(iE, i, this));
        }
    }

    public final void G(CalendarSelector calendarSelector) {
        this.f0 = calendarSelector;
        if (calendarSelector == CalendarSelector.e) {
            this.h0.getLayoutManager().i0(this.e0.k - ((ez2) this.h0.getAdapter()).c.d0.c.k);
            this.j0.setVisibility(0);
            this.k0.setVisibility(8);
            return;
        }
        if (calendarSelector == CalendarSelector.c) {
            this.j0.setVisibility(8);
            this.k0.setVisibility(0);
            F(this.e0);
        }
    }

    public final void H() {
        CalendarSelector calendarSelector = this.f0;
        CalendarSelector calendarSelector2 = CalendarSelector.e;
        CalendarSelector calendarSelector3 = CalendarSelector.c;
        if (calendarSelector == calendarSelector2) {
            G(calendarSelector3);
        } else if (calendarSelector == calendarSelector3) {
            G(calendarSelector2);
        }
    }

    @Override // p.ww0
    public final void r(Bundle bundle) {
        super.r(bundle);
        if (bundle == null) {
            bundle = this.n;
        }
        this.c0 = bundle.getInt("THEME_RES_ID_KEY");
        u4.t(bundle.getParcelable("GRID_SELECTOR_KEY"));
        this.d0 = (ip) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.e0 = (bl1) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // p.ww0
    public final View s(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        int i;
        int i2;
        mr1 mr1Var;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(e(), this.c0);
        this.g0 = new jh1(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        bl1 bl1Var = this.d0.c;
        int i3 = 1;
        int i4 = 0;
        if (mf1.J(contextThemeWrapper, R.attr.windowFullscreen)) {
            i = com.spotify.music.R.layout.mtrl_calendar_vertical;
            i2 = 1;
        } else {
            i = com.spotify.music.R.layout.mtrl_calendar_horizontal;
            i2 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i, viewGroup, false);
        Resources resources = B().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(com.spotify.music.R.dimen.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelOffset(com.spotify.music.R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelSize(com.spotify.music.R.dimen.mtrl_calendar_navigation_height);
        int dimensionPixelSize = resources.getDimensionPixelSize(com.spotify.music.R.dimen.mtrl_calendar_days_of_week_height);
        int i5 = cl1.l;
        viewInflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(com.spotify.music.R.dimen.mtrl_calendar_month_vertical_padding) * (i5 - 1)) + (resources.getDimensionPixelSize(com.spotify.music.R.dimen.mtrl_calendar_day_height) * i5) + resources.getDimensionPixelOffset(com.spotify.music.R.dimen.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) viewInflate.findViewById(com.spotify.music.R.id.mtrl_calendar_days_of_week);
        zt2.k(gridView, new ff1(i4, this));
        gridView.setAdapter((ListAdapter) new d60());
        gridView.setNumColumns(bl1Var.l);
        gridView.setEnabled(false);
        this.i0 = (RecyclerView) viewInflate.findViewById(com.spotify.music.R.id.mtrl_calendar_months);
        e();
        this.i0.setLayoutManager(new gf1(this, i2, i2));
        this.i0.setTag("MONTHS_VIEW_GROUP_TAG");
        c cVar = new c(contextThemeWrapper, this.d0, new p01(16, this));
        this.i0.setAdapter(cVar);
        int integer = contextThemeWrapper.getResources().getInteger(com.spotify.music.R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView3 = (RecyclerView) viewInflate.findViewById(com.spotify.music.R.id.mtrl_calendar_year_selector_frame);
        this.h0 = recyclerView3;
        if (recyclerView3 != null) {
            recyclerView3.setHasFixedSize(true);
            this.h0.setLayoutManager(new GridLayoutManager(integer));
            this.h0.setAdapter(new ez2(this));
            this.h0.g(new hf1(this));
        }
        if (viewInflate.findViewById(com.spotify.music.R.id.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) viewInflate.findViewById(com.spotify.music.R.id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            zt2.k(materialButton, new ff1(i3, this));
            MaterialButton materialButton2 = (MaterialButton) viewInflate.findViewById(com.spotify.music.R.id.month_navigation_previous);
            materialButton2.setTag("NAVIGATION_PREV_TAG");
            MaterialButton materialButton3 = (MaterialButton) viewInflate.findViewById(com.spotify.music.R.id.month_navigation_next);
            materialButton3.setTag("NAVIGATION_NEXT_TAG");
            this.j0 = viewInflate.findViewById(com.spotify.music.R.id.mtrl_calendar_year_selector_frame);
            this.k0 = viewInflate.findViewById(com.spotify.music.R.id.mtrl_calendar_day_selector_frame);
            G(CalendarSelector.c);
            materialButton.setText(this.e0.d(viewInflate.getContext()));
            this.i0.h(new if1(this, cVar, materialButton));
            materialButton.setOnClickListener(new e6(2, this));
            materialButton3.setOnClickListener(new jf1(this, cVar, i4));
            materialButton2.setOnClickListener(new jf1(this, cVar, i3));
        }
        if (!mf1.J(contextThemeWrapper, R.attr.windowFullscreen) && (recyclerView2 = (mr1Var = new mr1()).a) != (recyclerView = this.i0)) {
            ae2 ae2Var = mr1Var.b;
            if (recyclerView2 != null) {
                ArrayList arrayList = recyclerView2.m0;
                if (arrayList != null) {
                    arrayList.remove(ae2Var);
                }
                mr1Var.a.setOnFlingListener(null);
            }
            mr1Var.a = recyclerView;
            if (recyclerView != null) {
                if (recyclerView.getOnFlingListener() != null) {
                    throw new IllegalStateException("An instance of OnFlingListener already set.");
                }
                mr1Var.a.h(ae2Var);
                mr1Var.a.setOnFlingListener(mr1Var);
                new Scroller(mr1Var.a.getContext(), new DecelerateInterpolator());
                mr1Var.f();
            }
        }
        this.i0.a0(cVar.d.c.e(this.e0));
        return viewInflate;
    }

    @Override // p.ww0
    public final void w(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.c0);
        bundle.putParcelable("GRID_SELECTOR_KEY", null);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.d0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.e0);
    }
}
