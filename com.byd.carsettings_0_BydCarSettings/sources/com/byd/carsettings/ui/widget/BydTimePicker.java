package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.g.o.f;
import b.b.g.r.g;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydDate;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class BydTimePicker extends FrameLayout implements NumberPicker.OnValueChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3370a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3371b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3372c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3373d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3374e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3375f;
    public float g;
    public volatile BydDate h;
    public int i;
    public NumberPicker j;
    public NumberPicker k;
    public NumberPicker l;
    public NumberPicker m;
    public View n;
    public a o;

    public interface a {
        void onDateSelected(BydDate bydDate);
    }

    public BydTimePicker(@NonNull Context context) {
        super(context);
        this.f3370a = 0;
        this.f3371b = 0;
        this.f3372c = 0.0f;
        this.f3373d = 0.0f;
        this.f3374e = 0;
        this.f3375f = 0;
        this.g = 0.0f;
        this.h = null;
        this.i = 0;
        c(context);
    }

    private int getHourMax() {
        return d() ? 23 : 12;
    }

    public final void a(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.byd_time_picker);
        this.f3370a = typedArrayObtainStyledAttributes.getResourceId(5, this.f3370a);
        this.f3371b = typedArrayObtainStyledAttributes.getResourceId(3, this.f3371b);
        this.f3372c = typedArrayObtainStyledAttributes.getDimension(6, this.f3372c);
        this.f3373d = typedArrayObtainStyledAttributes.getDimension(4, this.f3373d);
        this.f3374e = typedArrayObtainStyledAttributes.getResourceId(0, this.f3374e);
        this.f3375f = typedArrayObtainStyledAttributes.getResourceId(1, this.f3375f);
        this.g = typedArrayObtainStyledAttributes.getDimension(2, this.g);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(NumberPicker numberPicker, int i, int i2, int i3) {
        b.b.m.c.a.a("BydTimePicker", "---initNumberPicker---");
        numberPicker.setMinValue(i);
        numberPicker.setMaxValue(i2);
        numberPicker.setValue(i3);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setDescendantFocusability(393216);
        numberPicker.setOnValueChangedListener(this);
        if (Build.VERSION.SDK_INT >= 29) {
            float f2 = this.g;
            if (f2 != 0.0f) {
                numberPicker.setSelectionDividerHeight((int) f2);
            }
            float f3 = this.f3373d;
            if (f3 != 0.0f) {
                numberPicker.setTextSize(f3);
            }
            if (this.f3371b != 0) {
                numberPicker.setTextColor(getContext().getColor(this.f3371b));
            }
        }
    }

    public final void c(Context context) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_time_picker, (ViewGroup) null);
        this.n = viewInflate.findViewById(R.id.tv_ampm_view_time_picker);
        NumberPicker numberPicker = (NumberPicker) viewInflate.findViewById(R.id.np_year_view_time_picker);
        this.m = (NumberPicker) viewInflate.findViewById(R.id.np_month_view_time_picker);
        this.j = (NumberPicker) viewInflate.findViewById(R.id.np_day_view_time_picker);
        this.k = (NumberPicker) viewInflate.findViewById(R.id.np_ampm_view_time_picker);
        this.l = (NumberPicker) viewInflate.findViewById(R.id.np_hour_view_time_picker);
        NumberPicker numberPicker2 = (NumberPicker) viewInflate.findViewById(R.id.np_minute_view_time_picker);
        BydDate bydDateC = g.c(context);
        this.h = bydDateC;
        b.b.m.c.a.a("BydTimePicker", "---initViews---curDateSelected = " + this.h);
        b(numberPicker, 2001, 2037, bydDateC.getYear());
        numberPicker.setWrapSelectorWheel(false);
        b(this.m, 1, 12, bydDateC.getMonth());
        if (!e()) {
            this.m.setDisplayedValues(getContext().getResources().getStringArray(R.array.month_en_array));
        }
        b(this.j, 1, g.f(bydDateC.getYear(), bydDateC.getMonth()), bydDateC.getDay());
        this.k.setDisplayedValues(getContext().getResources().getStringArray(R.array.time_ampm_array));
        int i = !this.h.isAm() ? 1 : 0;
        this.i = i;
        b(this.k, 0, 1, i);
        b(this.l, 1 ^ (d() ? 1 : 0), getHourMax(), bydDateC.getHour());
        b(numberPicker2, 0, 59, bydDateC.getMinute());
        this.l.setMaxValue(getHourMax());
        f(this.i);
        addView(viewInflate);
    }

    public boolean d() {
        return DateFormat.is24HourFormat(getContext());
    }

    public final boolean e() {
        return Locale.CHINA.equals(getContext().getResources().getConfiguration().getLocales().get(0));
    }

    public final void f(int i) {
        this.i = i;
        if (d()) {
            View view = this.n;
            if (view != null) {
                view.setVisibility(8);
            }
            this.k.setVisibility(8);
        } else {
            View view2 = this.n;
            if (view2 != null) {
                view2.setVisibility(0);
            }
            this.k.setVisibility(0);
        }
        this.l.setMaxValue(getHourMax());
        this.l.setMinValue(!d() ? 1 : 0);
    }

    @Override // android.widget.NumberPicker.OnValueChangeListener
    public void onValueChange(NumberPicker numberPicker, int i, int i2) {
        f.e(numberPicker);
        switch (numberPicker.getId()) {
            case R.id.np_ampm_view_time_picker /* 2131297690 */:
                this.h.setAm(i2 == 0);
                f(i2);
                break;
            case R.id.np_day_view_time_picker /* 2131297691 */:
                this.h.setDay(i2);
                break;
            case R.id.np_hour_view_time_picker /* 2131297693 */:
                this.h.setHour(i2);
                break;
            case R.id.np_minute_view_time_picker /* 2131297694 */:
                this.h.setMinute(i2);
                break;
            case R.id.np_month_view_time_picker /* 2131297695 */:
                this.h.setMonth(i2);
                this.j.setMaxValue(g.f(this.h.getYear(), i2));
                break;
            case R.id.np_year_view_time_picker /* 2131297696 */:
                this.h.setYear(i2);
                this.j.setMaxValue(g.f(this.h.getYear(), this.m.getValue()));
                break;
        }
        a aVar = this.o;
        if (aVar != null) {
            aVar.onDateSelected(this.h);
        }
    }

    public void setOnDateSelectedCallback(a aVar) {
        this.o = aVar;
    }

    public BydTimePicker(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3370a = 0;
        this.f3371b = 0;
        this.f3372c = 0.0f;
        this.f3373d = 0.0f;
        this.f3374e = 0;
        this.f3375f = 0;
        this.g = 0.0f;
        this.h = null;
        this.i = 0;
        a(context, attributeSet);
        c(context);
    }

    public BydTimePicker(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3370a = 0;
        this.f3371b = 0;
        this.f3372c = 0.0f;
        this.f3373d = 0.0f;
        this.f3374e = 0;
        this.f3375f = 0;
        this.g = 0.0f;
        this.h = null;
        this.i = 0;
        a(context, attributeSet);
        c(context);
    }
}
