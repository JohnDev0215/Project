package com.byd.ota.view.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.byd.ota.R;
import com.byd.ota.view.widget.MyNumberPicker;

/* JADX INFO: loaded from: classes.dex */
public class DatePicker extends MyNumberPicker {

    public class a implements MyNumberPicker.f {
        public a() {
        }

        @Override // com.byd.ota.view.widget.MyNumberPicker.f
        public String format(int i2) {
            if (i2 >= 10) {
                return String.valueOf(i2);
            }
            return "0" + i2;
        }
    }

    public DatePicker(Context context) {
        this(context, null);
    }

    public final void a0() {
        setFormatter(new a());
    }

    public DatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a0();
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.update_info_time_color, typedValue, true);
        setSelectColor(resources.getColor(typedValue.resourceId, null));
        TypedValue typedValue2 = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.time_ticker_text_color, typedValue2, true);
        setNormalTextColor(resources.getColor(typedValue2.resourceId, null));
    }
}
