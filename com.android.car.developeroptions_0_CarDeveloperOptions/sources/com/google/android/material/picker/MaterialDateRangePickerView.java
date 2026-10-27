package com.google.android.material.picker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$attr;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDateRangePickerView extends MaterialCalendarView<Pair<Calendar, Calendar>> {
    private final AdapterView.OnItemClickListener onItemClickListener;
    private int selectedEndPosition;
    private int selectedStartPosition;
    private static final int DEF_STYLE_ATTR = R$attr.materialDateRangePickerStyle;
    private static final ColorDrawable emptyColor = new ColorDrawable(0);
    private static final ColorDrawable startColor = new ColorDrawable(-65536);
    private static final ColorDrawable endColor = new ColorDrawable(-16711936);
    private static final ColorDrawable rangeColor = new ColorDrawable(-256);

    public MaterialDateRangePickerView(Context context) {
        this(context, null);
    }

    public MaterialDateRangePickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, DEF_STYLE_ATTR);
    }

    public MaterialDateRangePickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectedStartPosition = -1;
        this.selectedEndPosition = -1;
        this.onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.google.android.material.picker.MaterialDateRangePickerView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                if (MaterialDateRangePickerView.this.getMonthInYearAdapter().withinMonth(i2)) {
                    if (MaterialDateRangePickerView.this.selectedStartPosition < 0) {
                        MaterialDateRangePickerView.this.selectedStartPosition = i2;
                    } else if (MaterialDateRangePickerView.this.selectedEndPosition >= 0 || i2 <= MaterialDateRangePickerView.this.selectedStartPosition) {
                        MaterialDateRangePickerView.this.selectedEndPosition = -1;
                        MaterialDateRangePickerView.this.selectedStartPosition = i2;
                    } else {
                        MaterialDateRangePickerView.this.selectedEndPosition = i2;
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
    protected void drawSelection(AdapterView<?> adapterView) {
        for (int i = 0; i < adapterView.getCount(); i++) {
            ColorDrawable colorDrawable = emptyColor;
            int i2 = this.selectedStartPosition;
            if (i == i2) {
                colorDrawable = startColor;
            } else {
                int i3 = this.selectedEndPosition;
                if (i == i3) {
                    colorDrawable = endColor;
                } else if (i > i2 && i < i3) {
                    colorDrawable = rangeColor;
                }
            }
            ViewCompat.setBackground(adapterView.getChildAt(i), colorDrawable);
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
