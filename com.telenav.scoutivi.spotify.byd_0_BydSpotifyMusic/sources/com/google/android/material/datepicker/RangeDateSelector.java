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
public class RangeDateSelector implements DateSelector<h0.d<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new c();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f4390e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f4391g = " ";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Long f4392h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Long f4393i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Long f4394j = null;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Long f4395k = null;

    class a extends com.google.android.material.datepicker.c {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f4396l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f4397m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        final /* synthetic */ l f4398n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, l lVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f4396l = textInputLayout2;
            this.f4397m = textInputLayout3;
            this.f4398n = lVar;
        }

        @Override // com.google.android.material.datepicker.c
        void e() {
            RangeDateSelector.this.f4394j = null;
            RangeDateSelector.this.s(this.f4396l, this.f4397m, this.f4398n);
        }

        @Override // com.google.android.material.datepicker.c
        void f(Long l7) {
            RangeDateSelector.this.f4394j = l7;
            RangeDateSelector.this.s(this.f4396l, this.f4397m, this.f4398n);
        }
    }

    class b extends com.google.android.material.datepicker.c {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f4400l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        final /* synthetic */ TextInputLayout f4401m;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        final /* synthetic */ l f4402n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, l lVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f4400l = textInputLayout2;
            this.f4401m = textInputLayout3;
            this.f4402n = lVar;
        }

        @Override // com.google.android.material.datepicker.c
        void e() {
            RangeDateSelector.this.f4395k = null;
            RangeDateSelector.this.s(this.f4400l, this.f4401m, this.f4402n);
        }

        @Override // com.google.android.material.datepicker.c
        void f(Long l7) {
            RangeDateSelector.this.f4395k = l7;
            RangeDateSelector.this.s(this.f4400l, this.f4401m, this.f4402n);
        }
    }

    static class c implements Parcelable.Creator<RangeDateSelector> {
        c() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f4392h = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f4393i = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i7) {
            return new RangeDateSelector[i7];
        }
    }

    private void m(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.f4390e.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !" ".contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    private boolean q(long j7, long j8) {
        return j7 <= j8;
    }

    private void r(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.f4390e);
        textInputLayout2.setError(" ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, l<h0.d<Long, Long>> lVar) {
        Long l7 = this.f4394j;
        if (l7 == null || this.f4395k == null) {
            m(textInputLayout, textInputLayout2);
            lVar.a();
        } else if (!q(l7.longValue(), this.f4395k.longValue())) {
            r(textInputLayout, textInputLayout2);
            lVar.a();
        } else {
            this.f4392h = this.f4394j;
            this.f4393i = this.f4395k;
            lVar.b(a());
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String d(Context context) {
        Resources resources = context.getResources();
        Long l7 = this.f4392h;
        if (l7 == null && this.f4393i == null) {
            return resources.getString(o2.j.mtrl_picker_range_header_unselected);
        }
        Long l8 = this.f4393i;
        if (l8 == null) {
            return resources.getString(o2.j.mtrl_picker_range_header_only_start_selected, d.c(l7.longValue()));
        }
        if (l7 == null) {
            return resources.getString(o2.j.mtrl_picker_range_header_only_end_selected, d.c(l8.longValue()));
        }
        h0.d<String, String> dVarA = d.a(l7, l8);
        return resources.getString(o2.j.mtrl_picker_range_header_selected, dVarA.f6589a, dVarA.f6590b);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int e(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return b3.b.c(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(o2.d.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? o2.b.materialCalendarTheme : o2.b.materialCalendarFullscreenTheme, g.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<h0.d<Long, Long>> f() {
        if (this.f4392h == null || this.f4393i == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new h0.d(this.f4392h, this.f4393i));
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean i() {
        Long l7 = this.f4392h;
        return (l7 == null || this.f4393i == null || !q(l7.longValue(), this.f4393i.longValue())) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void l(long j7) {
        Long l7 = this.f4392h;
        if (l7 == null) {
            this.f4392h = Long.valueOf(j7);
        } else if (this.f4393i == null && q(l7.longValue(), j7)) {
            this.f4393i = Long.valueOf(j7);
        } else {
            this.f4393i = null;
            this.f4392h = Long.valueOf(j7);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View n(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, l<h0.d<Long, Long>> lVar) {
        View viewInflate = layoutInflater.inflate(o2.h.mtrl_picker_text_input_date_range, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(o2.f.mtrl_picker_text_input_range_start);
        TextInputLayout textInputLayout2 = (TextInputLayout) viewInflate.findViewById(o2.f.mtrl_picker_text_input_range_end);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        if (com.google.android.material.internal.e.a()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.f4390e = viewInflate.getResources().getString(o2.j.mtrl_picker_invalid_range);
        SimpleDateFormat simpleDateFormatF = p.f();
        Long l7 = this.f4392h;
        if (l7 != null) {
            editText.setText(simpleDateFormatF.format(l7));
            this.f4394j = this.f4392h;
        }
        Long l8 = this.f4393i;
        if (l8 != null) {
            editText2.setText(simpleDateFormatF.format(l8));
            this.f4395k = this.f4393i;
        }
        String strG = p.g(viewInflate.getResources(), simpleDateFormatF);
        textInputLayout.setPlaceholderText(strG);
        textInputLayout2.setPlaceholderText(strG);
        editText.addTextChangedListener(new a(strG, simpleDateFormatF, textInputLayout, calendarConstraints, textInputLayout, textInputLayout2, lVar));
        editText2.addTextChangedListener(new b(strG, simpleDateFormatF, textInputLayout2, calendarConstraints, textInputLayout, textInputLayout2, lVar));
        com.google.android.material.internal.l.g(editText);
        return viewInflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> o() {
        ArrayList arrayList = new ArrayList();
        Long l7 = this.f4392h;
        if (l7 != null) {
            arrayList.add(l7);
        }
        Long l8 = this.f4393i;
        if (l8 != null) {
            arrayList.add(l8);
        }
        return arrayList;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public h0.d<Long, Long> a() {
        return new h0.d<>(this.f4392h, this.f4393i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeValue(this.f4392h);
        parcel.writeValue(this.f4393i);
    }
}
