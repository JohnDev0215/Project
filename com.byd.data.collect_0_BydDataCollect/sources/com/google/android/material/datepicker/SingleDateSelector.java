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
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class SingleDateSelector implements DateSelector<Long> {
    public static final Parcelable.Creator<SingleDateSelector> CREATOR = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Long f3941b;

    class a extends c {

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ l f3942g;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, l lVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f3942g = lVar;
        }

        @Override // com.google.android.material.datepicker.c
        void a() {
            this.f3942g.a();
        }

        @Override // com.google.android.material.datepicker.c
        void b(Long l) {
            if (l == null) {
                SingleDateSelector.this.d();
            } else {
                SingleDateSelector.this.m(l.longValue());
            }
            this.f3942g.b(SingleDateSelector.this.b());
        }
    }

    static class b implements Parcelable.Creator<SingleDateSelector> {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector createFromParcel(Parcel parcel) {
            SingleDateSelector singleDateSelector = new SingleDateSelector();
            singleDateSelector.f3941b = (Long) parcel.readValue(Long.class.getClassLoader());
            return singleDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector[] newArray(int i2) {
            return new SingleDateSelector[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.f3941b = null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String e(Context context) {
        Resources resources = context.getResources();
        Long l = this.f3941b;
        if (l == null) {
            return resources.getString(d.b.a.a.j.mtrl_picker_date_header_unselected);
        }
        return resources.getString(d.b.a.a.j.mtrl_picker_date_header_selected, d.i(l.longValue()));
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public Long b() {
        return this.f3941b;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int g(Context context) {
        return d.b.a.a.u.b.c(context, d.b.a.a.b.materialCalendarTheme, g.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<b.g.j.d<Long, Long>> h() {
        return new ArrayList();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean k() {
        return this.f3941b != null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void m(long j) {
        this.f3941b = Long.valueOf(j);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View n(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, l<Long> lVar) {
        View viewInflate = layoutInflater.inflate(d.b.a.a.h.mtrl_picker_text_input_date, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(d.b.a.a.f.mtrl_picker_text_input_date);
        EditText editText = textInputLayout.getEditText();
        if (com.google.android.material.internal.d.a()) {
            editText.setInputType(17);
        }
        SimpleDateFormat simpleDateFormatL = p.l();
        String strM = p.m(viewInflate.getResources(), simpleDateFormatL);
        Long l = this.f3941b;
        if (l != null) {
            editText.setText(simpleDateFormatL.format(l));
        }
        editText.addTextChangedListener(new a(strM, simpleDateFormatL, textInputLayout, calendarConstraints, lVar));
        com.google.android.material.internal.q.k(editText);
        return viewInflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> o() {
        ArrayList arrayList = new ArrayList();
        Long l = this.f3941b;
        if (l != null) {
            arrayList.add(l);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.f3941b);
    }
}
