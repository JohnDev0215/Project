package com.byd.profile.view;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import androidx.core.view.ViewCompat;
import com.byd.card.bean.Constant;
import com.byd.profile.R;
import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DatePicker extends NumberPicker {
    public DatePicker(Context context) {
        super(context);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setNumberPickerDividerColor(ViewCompat.MEASURED_SIZE_MASK);
        setFormatter();
    }

    public DatePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setNumberPickerDividerColor(ViewCompat.MEASURED_SIZE_MASK);
        setFormatter();
    }

    @Override // android.view.ViewGroup
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View view) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            editText.setTextColor(getResources().getColor(R.color.white));
            editText.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen.sp_24));
        }
    }

    public final void setNumberPickerDividerColor(int color) {
        for (Field field : NumberPicker.class.getDeclaredFields()) {
            if (field.getName().equals("mSelectionDivider")) {
                field.setAccessible(true);
                try {
                    field.set(this, new ColorDrawable(color));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void updateViews(View view) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            editText.setTextColor(getResources().getColor(R.color.highlight));
            editText.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen.sp_24));
        }
        try {
            getField("mSelectionDivider").set(this, new ColorDrawable(ViewCompat.MEASURED_SIZE_MASK));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFormatter() {
        setFormatter(new NumberPicker.Formatter() { // from class: com.byd.profile.view.DatePicker.1
            @Override // android.widget.NumberPicker.Formatter
            public String format(int value) {
                if (value < 10) {
                    return Constant.CarType.CAR_TYPE_FUEL + value;
                }
                return String.valueOf(value);
            }
        });
    }

    public void updateColor(int color) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof EditText) {
                try {
                    Field declaredField = ((Class) Objects.requireNonNull(getClass().getSuperclass())).getDeclaredField("mSelectorWheelPaint");
                    declaredField.setAccessible(true);
                    ((Paint) declaredField.get(this)).setColor(getResources().getColor(color));
                    ((EditText) childAt).setTextColor(getResources().getColor(color));
                    invalidate();
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Field getField(String fieldName) {
        try {
            Field declaredField = NumberPicker.class.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setMInputStyle(Float size) {
        try {
            ((EditText) getField("mInputText").get(this)).setTextSize(size.floatValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setMInputStyle(int color) {
        try {
            ((EditText) getField("mInputText").get(this)).setTextColor(color);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setSelector() {
        setMInputStyle(getResources().getColor(R.color.highlight));
    }
}
