package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;

/* JADX INFO: compiled from: MaterialTextInputPicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class i<S> extends m<S> {

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private int f4474h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private DateSelector<S> f4475i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private CalendarConstraints f4476j0;

    /* JADX INFO: compiled from: MaterialTextInputPicker.java */
    class a extends l<S> {
        a() {
        }

        @Override // com.google.android.material.datepicker.l
        public void a() {
            Iterator<l<S>> it = i.this.f4493g0.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }

        @Override // com.google.android.material.datepicker.l
        public void b(S s6) {
            Iterator<l<S>> it = i.this.f4493g0.iterator();
            while (it.hasNext()) {
                it.next().b(s6);
            }
        }
    }

    static <T> i<T> P1(DateSelector<T> dateSelector, int i7, CalendarConstraints calendarConstraints) {
        i<T> iVar = new i<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i7);
        bundle.putParcelable("DATE_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        iVar.C1(bundle);
        return iVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void Q0(Bundle bundle) {
        super.Q0(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.f4474h0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.f4475i0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f4476j0);
    }

    @Override // androidx.fragment.app.Fragment
    public void u0(Bundle bundle) {
        super.u0(bundle);
        if (bundle == null) {
            bundle = q();
        }
        this.f4474h0 = bundle.getInt("THEME_RES_ID_KEY");
        this.f4475i0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.f4476j0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View y0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f4475i0.n(layoutInflater.cloneInContext(new ContextThemeWrapper(s(), this.f4474h0)), viewGroup, bundle, this.f4476j0, new a());
    }
}
