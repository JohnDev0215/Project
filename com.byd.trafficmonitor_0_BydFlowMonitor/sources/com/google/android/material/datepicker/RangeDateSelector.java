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
import c.b.a.a.d0.p;
import c.b.a.a.h;
import c.b.a.a.j;
import c.b.a.a.x.d;
import c.b.a.a.x.f;
import c.b.a.a.x.k;
import c.b.a.a.x.o;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class RangeDateSelector implements DateSelector<b.h.l.c<Long, Long>> {
    public static final Parcelable.Creator<RangeDateSelector> CREATOR = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4702b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4703c = " ";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Long f4704d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Long f4705e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Long f4706f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Long f4707g = null;

    public class a extends c.b.a.a.x.c {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ TextInputLayout f4708g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ TextInputLayout f4709h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ k f4710i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, k kVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f4708g = textInputLayout2;
            this.f4709h = textInputLayout3;
            this.f4710i = kVar;
        }

        @Override // c.b.a.a.x.c
        public void a() {
            RangeDateSelector.this.f4706f = null;
            RangeDateSelector.this.s(this.f4708g, this.f4709h, this.f4710i);
        }

        @Override // c.b.a.a.x.c
        public void b(Long l) {
            RangeDateSelector.this.f4706f = l;
            RangeDateSelector.this.s(this.f4708g, this.f4709h, this.f4710i);
        }
    }

    public class b extends c.b.a.a.x.c {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ TextInputLayout f4711g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ TextInputLayout f4712h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ k f4713i;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, TextInputLayout textInputLayout2, TextInputLayout textInputLayout3, k kVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f4711g = textInputLayout2;
            this.f4712h = textInputLayout3;
            this.f4713i = kVar;
        }

        @Override // c.b.a.a.x.c
        public void a() {
            RangeDateSelector.this.f4707g = null;
            RangeDateSelector.this.s(this.f4711g, this.f4712h, this.f4713i);
        }

        @Override // c.b.a.a.x.c
        public void b(Long l) {
            RangeDateSelector.this.f4707g = l;
            RangeDateSelector.this.s(this.f4711g, this.f4712h, this.f4713i);
        }
    }

    public static class c implements Parcelable.Creator<RangeDateSelector> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector createFromParcel(Parcel parcel) {
            RangeDateSelector rangeDateSelector = new RangeDateSelector();
            rangeDateSelector.f4704d = (Long) parcel.readValue(Long.class.getClassLoader());
            rangeDateSelector.f4705e = (Long) parcel.readValue(Long.class.getClassLoader());
            return rangeDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RangeDateSelector[] newArray(int i2) {
            return new RangeDateSelector[i2];
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String b(Context context) {
        Resources resources = context.getResources();
        Long l = this.f4704d;
        if (l == null && this.f4705e == null) {
            return resources.getString(j.mtrl_picker_range_header_unselected);
        }
        Long l2 = this.f4705e;
        if (l2 == null) {
            return resources.getString(j.mtrl_picker_range_header_only_start_selected, d.c(l.longValue()));
        }
        if (l == null) {
            return resources.getString(j.mtrl_picker_range_header_only_end_selected, d.c(l2.longValue()));
        }
        b.h.l.c<String, String> cVarA = d.a(l, l2);
        return resources.getString(j.mtrl_picker_range_header_selected, cVarA.f2575a, cVarA.f2576b);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int c(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return c.b.a.a.f0.b.c(context, Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) > resources.getDimensionPixelSize(c.b.a.a.d.mtrl_calendar_maximum_default_fullscreen_minor_axis) ? c.b.a.a.b.materialCalendarTheme : c.b.a.a.b.materialCalendarFullscreenTheme, f.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<b.h.l.c<Long, Long>> d() {
        if (this.f4704d == null || this.f4705e == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new b.h.l.c(this.f4704d, this.f4705e));
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean f() {
        Long l = this.f4704d;
        return (l == null || this.f4705e == null || !q(l.longValue(), this.f4705e.longValue())) ? false : true;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void g(long j) {
        Long l = this.f4704d;
        if (l == null) {
            this.f4704d = Long.valueOf(j);
        } else if (this.f4705e == null && q(l.longValue(), j)) {
            this.f4705e = Long.valueOf(j);
        } else {
            this.f4705e = null;
            this.f4704d = Long.valueOf(j);
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View h(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, k<b.h.l.c<Long, Long>> kVar) {
        View viewInflate = layoutInflater.inflate(h.mtrl_picker_text_input_date_range, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(c.b.a.a.f.mtrl_picker_text_input_range_start);
        TextInputLayout textInputLayout2 = (TextInputLayout) viewInflate.findViewById(c.b.a.a.f.mtrl_picker_text_input_range_end);
        EditText editText = textInputLayout.getEditText();
        EditText editText2 = textInputLayout2.getEditText();
        if (c.b.a.a.d0.d.a()) {
            editText.setInputType(17);
            editText2.setInputType(17);
        }
        this.f4702b = viewInflate.getResources().getString(j.mtrl_picker_invalid_range);
        SimpleDateFormat simpleDateFormatL = o.l();
        Long l = this.f4704d;
        if (l != null) {
            editText.setText(simpleDateFormatL.format(l));
            this.f4706f = this.f4704d;
        }
        Long l2 = this.f4705e;
        if (l2 != null) {
            editText2.setText(simpleDateFormatL.format(l2));
            this.f4707g = this.f4705e;
        }
        String strM = o.m(viewInflate.getResources(), simpleDateFormatL);
        editText.addTextChangedListener(new a(strM, simpleDateFormatL, textInputLayout, calendarConstraints, textInputLayout, textInputLayout2, kVar));
        editText2.addTextChangedListener(new b(strM, simpleDateFormatL, textInputLayout2, calendarConstraints, textInputLayout, textInputLayout2, kVar));
        p.k(editText);
        return viewInflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> i() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f4704d;
        if (l != null) {
            arrayList.add(l);
        }
        Long l2 = this.f4705e;
        if (l2 != null) {
            arrayList.add(l2);
        }
        return arrayList;
    }

    public final void o(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        if (textInputLayout.getError() != null && this.f4702b.contentEquals(textInputLayout.getError())) {
            textInputLayout.setError(null);
        }
        if (textInputLayout2.getError() == null || !" ".contentEquals(textInputLayout2.getError())) {
            return;
        }
        textInputLayout2.setError(null);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public b.h.l.c<Long, Long> a() {
        return new b.h.l.c<>(this.f4704d, this.f4705e);
    }

    public final boolean q(long j, long j2) {
        return j <= j2;
    }

    public final void r(TextInputLayout textInputLayout, TextInputLayout textInputLayout2) {
        textInputLayout.setError(this.f4702b);
        textInputLayout2.setError(" ");
    }

    public final void s(TextInputLayout textInputLayout, TextInputLayout textInputLayout2, k<b.h.l.c<Long, Long>> kVar) {
        Long l = this.f4706f;
        if (l == null || this.f4707g == null) {
            o(textInputLayout, textInputLayout2);
            kVar.a();
        } else if (!q(l.longValue(), this.f4707g.longValue())) {
            r(textInputLayout, textInputLayout2);
            kVar.a();
        } else {
            this.f4704d = this.f4706f;
            this.f4705e = this.f4707g;
            kVar.b(a());
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.f4704d);
        parcel.writeValue(this.f4705e);
    }
}
