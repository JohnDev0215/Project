package com.google.android.material.datepicker;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.u;
import com.google.android.material.R$dimen;
import com.google.android.material.R$id;
import com.google.android.material.R$integer;
import com.google.android.material.R$layout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.f;
import defpackage.C0214v;
import defpackage.ec1;
import defpackage.f12;
import defpackage.fc1;
import defpackage.gc1;
import defpackage.il;
import defpackage.om3;
import defpackage.sp2;
import defpackage.z60;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<S> extends f12<S> {
    public static final /* synthetic */ int k0 = 0;
    public int a0;
    public DateSelector<S> b0;
    public CalendarConstraints c0;
    public Month d0;
    public int e0;
    public il f0;
    public RecyclerView g0;
    public RecyclerView h0;
    public View i0;
    public View j0;

    /* JADX INFO: renamed from: com.google.android.material.datepicker.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaterialCalendar.java */
    public class C0035a extends C0214v {
        @Override // defpackage.C0214v
        public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
            accessibilityNodeInfoCompat.p(null);
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    public class b extends sp2 {
        public final /* synthetic */ int E;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(int i, int i2) {
            super(i);
            this.E = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public final void I0(RecyclerView.w wVar, int[] iArr) {
            int i = this.E;
            a aVar = a.this;
            if (i == 0) {
                iArr[0] = aVar.h0.getWidth();
                iArr[1] = aVar.h0.getWidth();
            } else {
                iArr[0] = aVar.h0.getHeight();
                iArr[1] = aVar.h0.getHeight();
            }
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    public class c implements d {
        public c() {
        }
    }

    /* JADX INFO: compiled from: MaterialCalendar.java */
    public interface d {
    }

    @Override // defpackage.f12
    public final boolean H0(f.c cVar) {
        return super.H0(cVar);
    }

    public final void I0(Month month) {
        Month month2 = ((i) this.h0.getAdapter()).h.e;
        Calendar calendar = month2.e;
        if (!(calendar instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        int i = month.g;
        int i2 = month2.g;
        int i3 = month.f;
        int i4 = month2.f;
        int i5 = (i3 - i4) + ((i - i2) * 12);
        Month month3 = this.d0;
        if (!(calendar instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        int i6 = i5 - ((month3.f - i4) + ((month3.g - i2) * 12));
        boolean z = Math.abs(i6) > 3;
        boolean z2 = i6 > 0;
        this.d0 = month;
        if (z && z2) {
            this.h0.g0(i5 - 3);
            this.h0.post(new ec1(this, i5));
        } else if (!z) {
            this.h0.post(new ec1(this, i5));
        } else {
            this.h0.g0(i5 + 3);
            this.h0.post(new ec1(this, i5));
        }
    }

    public final void J0(int i) {
        this.e0 = i;
        if (i == 2) {
            this.g0.getLayoutManager().v0(this.d0.g - ((k) this.g0.getAdapter()).h.c0.e.g);
            this.i0.setVisibility(0);
            this.j0.setVisibility(8);
            return;
        }
        if (i == 1) {
            this.i0.setVisibility(8);
            this.j0.setVisibility(0);
            I0(this.d0);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle == null) {
            bundle = this.j;
        }
        this.a0 = bundle.getInt("THEME_RES_ID_KEY");
        this.b0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.c0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.d0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(K(), this.a0);
        this.f0 = new il(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.c0.e;
        if (f.P0(contextThemeWrapper)) {
            i = R$layout.mtrl_calendar_vertical;
            i2 = 1;
        } else {
            i = R$layout.mtrl_calendar_horizontal;
            i2 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i, viewGroup, false);
        Resources resources = x0().getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_navigation_bottom_padding) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelSize(R$dimen.mtrl_calendar_navigation_height);
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.mtrl_calendar_days_of_week_height);
        int i3 = g.j;
        viewInflate.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_month_vertical_padding) * (i3 - 1)) + (resources.getDimensionPixelSize(R$dimen.mtrl_calendar_day_height) * i3) + resources.getDimensionPixelOffset(R$dimen.mtrl_calendar_bottom_padding));
        GridView gridView = (GridView) viewInflate.findViewById(R$id.mtrl_calendar_days_of_week);
        om3.k(gridView, new C0035a());
        int i4 = this.c0.i;
        gridView.setAdapter((ListAdapter) (i4 > 0 ? new z60(i4) : new z60()));
        gridView.setNumColumns(month.h);
        gridView.setEnabled(false);
        this.h0 = (RecyclerView) viewInflate.findViewById(R$id.mtrl_calendar_months);
        K();
        this.h0.setLayoutManager(new b(i2, i2));
        this.h0.setTag("MONTHS_VIEW_GROUP_TAG");
        i iVar = new i(contextThemeWrapper, this.b0, this.c0, new c());
        this.h0.setAdapter(iVar);
        int integer = contextThemeWrapper.getResources().getInteger(R$integer.mtrl_calendar_year_selector_span);
        int i5 = R$id.mtrl_calendar_year_selector_frame;
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(i5);
        this.g0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.g0.setLayoutManager(new GridLayoutManager(integer));
            this.g0.setAdapter(new k(this));
            this.g0.g(new com.google.android.material.datepicker.b(this));
        }
        int i6 = R$id.month_navigation_fragment_toggle;
        if (viewInflate.findViewById(i6) != null) {
            MaterialButton materialButton = (MaterialButton) viewInflate.findViewById(i6);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            om3.k(materialButton, new fc1(this));
            MaterialButton materialButton2 = (MaterialButton) viewInflate.findViewById(R$id.month_navigation_previous);
            materialButton2.setTag("NAVIGATION_PREV_TAG");
            MaterialButton materialButton3 = (MaterialButton) viewInflate.findViewById(R$id.month_navigation_next);
            materialButton3.setTag("NAVIGATION_NEXT_TAG");
            this.i0 = viewInflate.findViewById(i5);
            this.j0 = viewInflate.findViewById(R$id.mtrl_calendar_day_selector_frame);
            J0(1);
            materialButton.setText(this.d0.L());
            this.h0.addOnScrollListener(new com.google.android.material.datepicker.c(this, iVar, materialButton));
            materialButton.setOnClickListener(new gc1(this));
            materialButton3.setOnClickListener(new com.google.android.material.datepicker.d(this, iVar));
            materialButton2.setOnClickListener(new e(this, iVar));
        }
        if (!f.P0(contextThemeWrapper)) {
            new u().a(this.h0);
        }
        RecyclerView recyclerView2 = this.h0;
        Month month2 = this.d0;
        Month month3 = iVar.h.e;
        if (!(month3.e instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        recyclerView2.g0((month2.f - month3.f) + ((month2.g - month3.g) * 12));
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.a0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.b0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.c0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.d0);
    }
}
