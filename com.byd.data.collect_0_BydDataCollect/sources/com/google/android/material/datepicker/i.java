package com.google.android.material.datepicker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;

/* JADX INFO: compiled from: MaterialTextInputPicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class i<S> extends m<S> {
    private DateSelector<S> Z;
    private CalendarConstraints a0;

    /* JADX INFO: compiled from: MaterialTextInputPicker.java */
    class a extends l<S> {
        a() {
        }

        @Override // com.google.android.material.datepicker.l
        void a() {
            Iterator<l<S>> it = i.this.Y.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }

        @Override // com.google.android.material.datepicker.l
        public void b(S s) {
            Iterator<l<S>> it = i.this.Y.iterator();
            while (it.hasNext()) {
                it.next().b(s);
            }
        }
    }

    static <T> i<T> r1(DateSelector<T> dateSelector, CalendarConstraints calendarConstraints) {
        i<T> iVar = new i<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("DATE_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        iVar.i1(bundle);
        return iVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void d0(Bundle bundle) {
        super.d0(bundle);
        if (bundle == null) {
            bundle = n();
        }
        this.Z = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.a0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View h0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.Z.n(layoutInflater, viewGroup, bundle, this.a0, new a());
    }

    @Override // androidx.fragment.app.Fragment
    public void z0(Bundle bundle) {
        super.z0(bundle);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.Z);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.a0);
    }
}
