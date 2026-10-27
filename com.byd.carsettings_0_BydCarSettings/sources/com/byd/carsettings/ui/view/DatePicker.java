package com.byd.carsettings.ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import androidx.core.view.ViewCompat;
import com.byd.carsettings.R;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class DatePicker extends NumberPicker {

    public class a implements NumberPicker.Formatter {
        public a() {
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i) {
            if (i >= 10) {
                return String.valueOf(i);
            }
            return "0" + i;
        }
    }

    public DatePicker(Context context) {
        super(context);
    }

    private void setNumberPickerDividerColor(int i) {
        for (Field field : NumberPicker.class.getDeclaredFields()) {
            if (field.getName().equals("mSelectionDivider")) {
                field.setAccessible(true);
                try {
                    field.set(this, new ColorDrawable(i));
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public Field a(String str) {
        try {
            Field declaredField = NumberPicker.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        super.addView(view);
        c(view);
    }

    public final void b() {
        setFormatter(new a());
    }

    public final void c(View view) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            editText.setTextColor(getResources().getColor(R.color.text_color_main_gray));
            editText.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen.sp_24));
        }
    }

    public void setMInputStyle(Float f2) {
        try {
            ((EditText) a("mInputText").get(this)).setTextSize(f2.floatValue());
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public void setSelector() {
        setMInputStyle(getResources().getColor(R.color.text_blue));
    }

    public DatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setNumberPickerDividerColor(ViewCompat.MEASURED_SIZE_MASK);
        b();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        c(view);
    }

    public void setMInputStyle(int i) {
        try {
            ((EditText) a("mInputText").get(this)).setTextColor(i);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }

    public DatePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setNumberPickerDividerColor(ViewCompat.MEASURED_SIZE_MASK);
        b();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, layoutParams);
        c(view);
    }
}
