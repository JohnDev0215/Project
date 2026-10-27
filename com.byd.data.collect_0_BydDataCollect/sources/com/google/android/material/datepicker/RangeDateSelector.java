package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<b.g.j.d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3929b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3930c = " ";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Long f3931d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Long f3932e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Long f3933f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Long f3934g = null;

    class a extends com.google.android.material.datepicker.c {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f3935g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f3936h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ l f3937i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, l lVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f3935g = textInputLayout2;
            this.f3936h = textInputLayout3;
            this.f3937i = lVar;
        }

        @Override // com.google.android.material.datepicker.c
        void a() {
            RangeDateSelector.this.f3933f = null;
            RangeDateSelector.this.s(this.f3935g, this.f3936h, this.f3937i);
        }

        @Override // com.google.android.material.datepicker.c
        void b(Long l) {
            RangeDateSelector.this.f3933f = l;
            RangeDateSelector.this.s(this.f3935g, this.f3936h, this.f3937i);
        }
    }

    class b extends com.google.android.material.datepicker.c {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f3938g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f3939h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        final /* synthetic */ l f3940i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, l lVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f3938g = textInputLayout2;
            this.f3939h = textInputLayout3;
            this.f3940i = lVar;
        }

        @Override // com.google.android.material.datepicker.c
        void a() {
            RangeDateSelector.this.f3934g = null;
            RangeDateSelector.this.s(this.f3938g, this.f3939h, this.f3940i);
        }

        @Override // com.google.android.material.datepicker.c
        void b(Long l) {
            RangeDateSelector.this.f3934g = l;
            RangeDateSelector.this.s(this.f3938g, this.f3939h, this.f3940i);
        }
    }

    static class c implements Parcelable.Creator<RangeDateSelector> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f3931d = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f3932e = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i2) {
            return new RangeDateSelector[i2];
        }
    }

    private void l(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.f3929b.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !" ".contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    private boolean q(long j, long j2) {
        return j <= j2;
    }

    private void r(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.f3929b);
        textInputLayout2.setError(" ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, l<b.g.j.d<Long, Long>> lVar) {
        Long l = this.f3933f;
        if (l == null || this.f3934g == null) {
            l(textInputLayout, textInputLayout2);
            lVar.a();
        } else if (!q(l.longValue(), this.f3934g.longValue())) {
            r(textInputLayout, textInputLayout2);
            lVar.a();
        } else {
            this.f3931d = this.f3933f;
            this.f3932e = this.f3934g;
            lVar.b(b());
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String e(Context context) {
        Resources resources = context.getResources();
        Long l = this.f3931d;
        if (l == null && this.f3932e == null) {
            return resources.getString(d.b.a.a.j.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.f3932e;
        if (l2 == null) {
            return resources.getString(d.b.a.a.j.mtrl_picker_range_header_only_start_selected, d.c(l.longValue()));
        }
        if (l == null) {
            return resources.getString(d.b.a.a.j.mtrl_picker_range_header_only_end_selected, d.c(l2.longValue()));
        }
        b.g.j.d<String, String> dVarA = d.a(l, l2);
        return resources.getString(d.b.a.a.j.mtrl_picker_range_header_selected, dVarA.f2659a, dVarA.f2660b);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int g(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return d.b.a.a.u.b.c(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(d.b.a.a.d.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? d.b.a.a.b.materialCalendarTheme : d.b.a.a.b.materialCalendarFullscreenTheme, g.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<b.g.j.d<Long, Long>> h() {
        if (this.f3931d == null || this.f3932e == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new b.g.j.d(this.f3931d, this.f3932e));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean k() {
        Long l = this.f3931d;
        return (l == null || this.f3932e == null || !q(l.longValue(), this.f3932e.longValue())) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void m(long j) {
        Long l = this.f3931d;
        if (l == null) {
            this.f3931d = Long.valueOf(j);
        } else if (this.f3932e == null && q(l.longValue(), j)) {
            this.f3932e = Long.valueOf(j);
        } else {
            this.f3932e = null;
            this.f3931d = Long.valueOf(j);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View n(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, l<b.g.j.d<Long, Long>> lVar) {
        View viewInflate = layoutInflater.inflate(d.b.a.a.h.mtrl_picker_text_input_date_range, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(d.b.a.a.f.mtrl_picker_text_input_range_start);
        TextInputLayout textInputLayout2 = (TextInputLayout) viewInflate.findViewById(d.b.a.a.f.mtrl_picker_text_input_range_end);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        if (com.google.android.material.internal.d.a()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.f3929b = viewInflate.getResources().getString(d.b.a.a.j.mtrl_picker_invalid_range);
        SimpleDateFormat simpleDateFormatL = p.l();
        Long l = this.f3931d;
        if (l != null) {
            editText.setText(simpleDateFormatL.format(l));
            this.f3933f = this.f3931d;
        }
        Long l2 = this.f3932e;
        if (l2 != null) {
            editText2.setText(simpleDateFormatL.format(l2));
            this.f3934g = this.f3932e;
        }
        String strM = p.m(viewInflate.getResources(), simpleDateFormatL);
        editText.addTextChangedListener(new a(strM, simpleDateFormatL, textInputLayout, calendarConstraints, textInputLayout, textInputLayout2, lVar));
        editText2.addTextChangedListener(new b(strM, simpleDateFormatL, textInputLayout2, calendarConstraints, textInputLayout, textInputLayout2, lVar));
        com.google.android.material.internal.q.k(editText);
        return viewInflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> o() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f3931d;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f3932e;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public b.g.j.d<Long, Long> b() {
        return new b.g.j.d<>(this.f3931d, this.f3932e);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.f3931d);
        parcel.writeValue(this.f3932e);
    }
}
