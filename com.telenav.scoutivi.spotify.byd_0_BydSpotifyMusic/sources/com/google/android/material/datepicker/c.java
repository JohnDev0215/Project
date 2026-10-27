package com.google.android.material.datepicker;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* JADX INFO: compiled from: DateFormatTextWatcher.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c extends com.google.android.material.internal.i {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final TextInputLayout f4421e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final DateFormat f4422g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final CalendarConstraints f4423h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f4424i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Runnable f4425j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Runnable f4426k;

    /* JADX INFO: compiled from: DateFormatTextWatcher.java */
    class a implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f4427e;

        a(String str) {
            this.f4427e = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout textInputLayout = c.this.f4421e;
            DateFormat dateFormat = c.this.f4422g;
            Context context = textInputLayout.getContext();
            textInputLayout.setError(context.getString(o2.j.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(o2.j.mtrl_picker_invalid_format_use), this.f4427e) + "\n" + String.format(context.getString(o2.j.mtrl_picker_invalid_format_example), dateFormat.format(new Date(p.j().getTimeInMillis()))));
            c.this.e();
        }
    }

    /* JADX INFO: compiled from: DateFormatTextWatcher.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ long f4429e;

        b(long j7) {
            this.f4429e = j7;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.f4421e.setError(String.format(c.this.f4424i, d.c(this.f4429e)));
            c.this.e();
        }
    }

    c(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.f4422g = dateFormat;
        this.f4421e = textInputLayout;
        this.f4423h = calendarConstraints;
        this.f4424i = textInputLayout.getContext().getString(o2.j.mtrl_picker_out_of_range);
        this.f4425j = new a(str);
    }

    private Runnable d(long j7) {
        return new b(j7);
    }

    abstract void e();

    abstract void f(Long l7);

    public void g(View view, Runnable runnable) {
        view.postDelayed(runnable, 1000L);
    }

    @Override // com.google.android.material.internal.i, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i7, int i8, int i9) {
        this.f4421e.removeCallbacks(this.f4425j);
        this.f4421e.removeCallbacks(this.f4426k);
        this.f4421e.setError(null);
        f(null);
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        try {
            Date date = this.f4422g.parse(charSequence.toString());
            this.f4421e.setError(null);
            long time = date.getTime();
            if (this.f4423h.m().g(time) && this.f4423h.u(time)) {
                f(Long.valueOf(date.getTime()));
                return;
            }
            Runnable runnableD = d(time);
            this.f4426k = runnableD;
            g(this.f4421e, runnableD);
        } catch (ParseException unused) {
            g(this.f4421e, this.f4425j);
        }
    }
}
