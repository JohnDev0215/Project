package c.b.a.a.x;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/* JADX INFO: compiled from: DateFormatTextWatcher.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements TextWatcher {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4114b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DateFormat f4115c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final TextInputLayout f4116d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CalendarConstraints f4117e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f4118f;

    public c(String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.f4114b = str;
        this.f4115c = dateFormat;
        this.f4116d = textInputLayout;
        this.f4117e = calendarConstraints;
        this.f4118f = textInputLayout.getContext().getString(c.b.a.a.j.mtrl_picker_out_of_range);
    }

    public abstract void a();

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    public abstract void b(Long l);

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        if (TextUtils.isEmpty(charSequence)) {
            this.f4116d.setError(null);
            b(null);
            return;
        }
        try {
            Date date = this.f4115c.parse(charSequence.toString());
            this.f4116d.setError(null);
            long time = date.getTime();
            if (this.f4117e.o().e(time) && this.f4117e.u(time)) {
                b(Long.valueOf(date.getTime()));
            } else {
                this.f4116d.setError(String.format(this.f4118f, d.c(time)));
                a();
            }
        } catch (ParseException unused) {
            String string = this.f4116d.getContext().getString(c.b.a.a.j.mtrl_picker_invalid_format);
            String str = String.format(this.f4116d.getContext().getString(c.b.a.a.j.mtrl_picker_invalid_format_use), this.f4114b);
            String str2 = String.format(this.f4116d.getContext().getString(c.b.a.a.j.mtrl_picker_invalid_format_example), this.f4115c.format(new Date(o.p().getTimeInMillis())));
            this.f4116d.setError(string + BydAdbHelper.COMMAND_LINE_END + str + BydAdbHelper.COMMAND_LINE_END + str2);
            a();
        }
    }
}
