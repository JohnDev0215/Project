package defpackage;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import java.util.Iterator;

/* JADX INFO: compiled from: MaterialTextInputPicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class tc1<S> extends f12<S> {
    public int a0;
    public DateSelector<S> b0;
    public CalendarConstraints c0;

    /* JADX INFO: compiled from: MaterialTextInputPicker.java */
    public class a extends ks1<S> {
        public a() {
        }

        @Override // defpackage.ks1
        public final void a(S s) {
            Iterator<ks1<S>> it = tc1.this.Z.iterator();
            while (it.hasNext()) {
                it.next().a(s);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        if (bundle == null) {
            bundle = this.j;
        }
        this.a0 = bundle.getInt("THEME_RES_ID_KEY");
        this.b0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.c0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater.cloneInContext(new ContextThemeWrapper(K(), this.a0));
        DateSelector<S> dateSelector = this.b0;
        new a();
        return dateSelector.g0();
    }

    @Override // androidx.fragment.app.Fragment
    public final void n0(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.a0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.b0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.c0);
    }
}
