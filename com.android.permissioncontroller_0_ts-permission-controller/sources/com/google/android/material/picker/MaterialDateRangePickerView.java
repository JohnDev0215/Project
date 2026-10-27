package com.google.android.material.picker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialDateRangePickerView extends MaterialCalendarView<Pair<Calendar, Calendar>> {
    private final AdapterView.OnItemClickListener onItemClickListener;
    private int selectedEndPosition;
    private int selectedStartPosition;
    private static final int DEF_STYLE_ATTR = R.attr.materialDateRangePickerStyle;
    private static final ColorDrawable emptyColor = new ColorDrawable(0);
    private static final ColorDrawable startColor = new ColorDrawable(SupportMenu.CATEGORY_MASK);
    private static final ColorDrawable endColor = new ColorDrawable(-16711936);
    private static final ColorDrawable rangeColor = new ColorDrawable(InputDeviceCompat.SOURCE_ANY);

    public MaterialDateRangePickerView(Context context) {
        this(context, null);
    }

    public MaterialDateRangePickerView(Context context, AttributeSet attrs) {
        this(context, attrs, DEF_STYLE_ATTR);
    }

    public MaterialDateRangePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.selectedStartPosition = -1;
        this.selectedEndPosition = -1;
        this.onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.google.android.material.picker.MaterialDateRangePickerView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MaterialDateRangePickerView.this.getMonthInYearAdapter().withinMonth(position)) {
                    if (MaterialDateRangePickerView.this.selectedStartPosition < 0) {
                        MaterialDateRangePickerView.this.selectedStartPosition = position;
                    } else if (MaterialDateRangePickerView.this.selectedEndPosition >= 0 || position <= MaterialDateRangePickerView.this.selectedStartPosition) {
                        MaterialDateRangePickerView.this.selectedEndPosition = -1;
                        MaterialDateRangePickerView.this.selectedStartPosition = position;
                    } else {
                        MaterialDateRangePickerView.this.selectedEndPosition = position;
                    }
                }
            }
        };
    }

    @Override // com.google.android.material.picker.MaterialCalendarView
    protected AdapterView.OnItemClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Override // com.google.android.material.picker.MaterialCalendarView
    protected void drawSelection(AdapterView<?> parent) {
        for (int i = 0; i < parent.getCount(); i++) {
            ColorDrawable setColor = emptyColor;
            int i2 = this.selectedStartPosition;
            if (i == i2) {
                setColor = startColor;
            } else {
                int i3 = this.selectedEndPosition;
                if (i == i3) {
                    setColor = endColor;
                } else if (i > i2 && i < i3) {
                    setColor = rangeColor;
                }
            }
            ViewCompat.setBackground(parent.getChildAt(i), setColor);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.material.picker.MaterialCalendarView
    public Pair<Calendar, Calendar> getSelection() {
        Calendar start = getStart();
        Calendar end = getEnd();
        if (start == null || end == null) {
            return null;
        }
        return new Pair<>(getStart(), getEnd());
    }

    public Calendar getStart() {
        return getMonthInYearAdapter().getItem(this.selectedStartPosition);
    }

    public Calendar getEnd() {
        return getMonthInYearAdapter().getItem(this.selectedEndPosition);
    }
}
