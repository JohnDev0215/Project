package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* JADX INFO: compiled from: DateFormatTextWatcher.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c implements TextWatcher {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3958b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final DateFormat f3959c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final TextInputLayout f3960d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final CalendarConstraints f3961e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f3962f;

    c(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.f3958b = str;
        this.f3959c = dateFormat;
        this.f3960d = textInputLayout;
        this.f3961e = calendarConstraints;
        this.f3962f = textInputLayout.getContext().getString(d.b.a.a.j.mtrl_picker_out_of_range);
    }

    abstract void a();

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    abstract void b(Long l);

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (TextUtils.isEmpty(charSequence)) {
            this.f3960d.setError(null);
            b(null);
            return;
        }
        try {
            Date date = this.f3959c.parse(charSequence.toString());
            this.f3960d.setError(null);
            long time = date.getTime();
            if (this.f3961e.l().i(time) && this.f3961e.u(time)) {
                b(Long.valueOf(date.getTime()));
            } else {
                this.f3960d.setError(String.format(this.f3962f, d.c(time)));
                a();
            }
        } catch (ParseException unused) {
            String string = this.f3960d.getContext().getString(d.b.a.a.j.mtrl_picker_invalid_format);
            String str = String.format(this.f3960d.getContext().getString(d.b.a.a.j.mtrl_picker_invalid_format_use), this.f3958b);
            String str2 = String.format(this.f3960d.getContext().getString(d.b.a.a.j.mtrl_picker_invalid_format_example), this.f3959c.format(new Date(p.p().getTimeInMillis())));
            this.f3960d.setError(string + "\n" + str + "\n" + str2);
            a();
        }
    }
}
