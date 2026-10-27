package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import c.b.a.a.d0.p;
import c.b.a.a.h;
import c.b.a.a.j;
import c.b.a.a.x.c;
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
public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Long f4714b;

    public class a extends c {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ k f4715g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, k kVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f4715g = kVar;
        }

        @Override // c.b.a.a.x.c
        public void a() {
            this.f4715g.a();
        }

        @Override // c.b.a.a.x.c
        public void b(Long l) {
            if (l == null) {
                SingleDateSelector.this.l();
            } else {
                SingleDateSelector.this.g(l.longValue());
            }
            this.f4715g.b(SingleDateSelector.this.a());
        }
    }

    public static class b implements Parcelable.Creator<SingleDateSelector> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector createFromParcel(Parcel parcel) {
            SingleDateSelector singleDateSelector = new SingleDateSelector();
            singleDateSelector.f4714b = (Long) parcel.readValue(Long.class.getClassLoader());
            return singleDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector[] newArray(int i2) {
            return new SingleDateSelector[i2];
        }
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String b(Context context) {
        Resources resources = context.getResources();
        Long l = this.f4714b;
        if (l == null) {
            return resources.getString(j.mtrl_picker_date_header_unselected);
        }
        return resources.getString(j.mtrl_picker_date_header_selected, d.i(l.longValue()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int c(Context context) {
        return c.b.a.a.f0.b.c(context, c.b.a.a.b.materialCalendarTheme, f.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<b.h.l.c<Long, Long>> d() {
        return new ArrayList();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean f() {
        return this.f4714b != null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void g(long j) {
        this.f4714b = Long.valueOf(j);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View h(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, k<Long> kVar) {
        View viewInflate = layoutInflater.inflate(h.mtrl_picker_text_input_date, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(c.b.a.a.f.mtrl_picker_text_input_date);
        EditText editText = textInputLayout.getEditText();
        if (c.b.a.a.d0.d.a()) {
            editText.setInputType(17);
        }
        SimpleDateFormat simpleDateFormatL = o.l();
        String strM = o.m(viewInflate.getResources(), simpleDateFormatL);
        Long l = this.f4714b;
        if (l != null) {
            editText.setText(simpleDateFormatL.format(l));
        }
        editText.addTextChangedListener(new a(strM, simpleDateFormatL, textInputLayout, calendarConstraints, kVar));
        p.k(editText);
        return viewInflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> i() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f4714b;
        if (l != null) {
            arrayList.add(l);
        }
        return arrayList;
    }

    public final void l() {
        this.f4714b = null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public Long a() {
        return this.f4714b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.f4714b);
    }
}
