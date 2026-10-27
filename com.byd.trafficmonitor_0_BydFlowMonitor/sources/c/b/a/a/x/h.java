package c.b.a.a.x;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import java.util.Iterator;

/* JADX INFO: compiled from: MaterialTextInputPicker.java */
/* JADX INFO: loaded from: classes.dex */
public final class h<S> extends l<S> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public DateSelector<S> f4127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public CalendarConstraints f4128d;

    /* JADX INFO: compiled from: MaterialTextInputPicker.java */
    public class a extends k<S> {
        public a() {
        }

        @Override // c.b.a.a.x.k
        public void a() {
            Iterator<k<S>> it = h.this.f4141b.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }

        @Override // c.b.a.a.x.k
        public void b(S s) {
            Iterator<k<S>> it = h.this.f4141b.iterator();
            while (it.hasNext()) {
                it.next().b(s);
            }
        }
    }

    public static <T> h<T> d(DateSelector<T> dateSelector, CalendarConstraints calendarConstraints) {
        h<T> hVar = new h<>();
        Bundle bundle = new Bundle();
        bundle.putParcelable("DATE_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        hVar.setArguments(bundle);
        return hVar;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.f4127c = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.f4128d = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f4127c.h(layoutInflater, viewGroup, bundle, this.f4128d, new a());
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.f4127c);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.f4128d);
    }
}
