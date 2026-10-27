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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Long f4404e;

    class a extends c {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        final /* synthetic */ l f4405l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints, l lVar) {
            super(str, dateFormat, textInputLayout, calendarConstraints);
            this.f4405l = lVar;
        }

        @Override // com.google.android.material.datepicker.c
        void e() {
            this.f4405l.a();
        }

        @Override // com.google.android.material.datepicker.c
        void f(Long l7) {
            if (l7 == null) {
                SingleDateSelector.this.h();
            } else {
                SingleDateSelector.this.l(l7.longValue());
            }
            this.f4405l.b(SingleDateSelector.this.a());
        }
    }

    static class b implements Parcelable.Creator<SingleDateSelector> {
        b() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector createFromParcel(Parcel parcel) {
            SingleDateSelector singleDateSelector = new SingleDateSelector();
            singleDateSelector.f4404e = (Long) parcel.readValue(Long.class.getClassLoader());
            return singleDateSelector;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public SingleDateSelector[] newArray(int i7) {
            return new SingleDateSelector[i7];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        this.f4404e = null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public String d(Context context) {
        Resources resources = context.getResources();
        Long l7 = this.f4404e;
        if (l7 == null) {
            return resources.getString(o2.j.mtrl_picker_date_header_unselected);
        }
        return resources.getString(o2.j.mtrl_picker_date_header_selected, d.j(l7.longValue()));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public int e(Context context) {
        return b3.b.c(context, o2.b.materialCalendarTheme, g.class.getCanonicalName());
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<h0.d<Long, Long>> f() {
        return new ArrayList();
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public boolean i() {
        return this.f4404e != null;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public Long a() {
        return this.f4404e;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public void l(long j7) {
        this.f4404e = Long.valueOf(j7);
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public View n(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, l<Long> lVar) {
        View viewInflate = layoutInflater.inflate(o2.h.mtrl_picker_text_input_date, viewGroup, false);
        TextInputLayout textInputLayout = (TextInputLayout) viewInflate.findViewById(o2.f.mtrl_picker_text_input_date);
        EditText editText = textInputLayout.getEditText();
        if (com.google.android.material.internal.e.a()) {
            editText.setInputType(17);
        }
        SimpleDateFormat simpleDateFormatF = p.f();
        String strG = p.g(viewInflate.getResources(), simpleDateFormatF);
        textInputLayout.setPlaceholderText(strG);
        Long l7 = this.f4404e;
        if (l7 != null) {
            editText.setText(simpleDateFormatF.format(l7));
        }
        editText.addTextChangedListener(new a(strG, simpleDateFormatF, textInputLayout, calendarConstraints, lVar));
        com.google.android.material.internal.l.g(editText);
        return viewInflate;
    }

    @Override // com.google.android.material.datepicker.DateSelector
    public Collection<Long> o() {
        ArrayList arrayList = new ArrayList();
        Long l7 = this.f4404e;
        if (l7 != null) {
            arrayList.add(l7);
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeValue(this.f4404e);
    }
}
