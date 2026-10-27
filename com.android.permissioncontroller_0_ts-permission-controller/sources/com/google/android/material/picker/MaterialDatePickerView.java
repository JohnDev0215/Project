package com.google.android.material.picker;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialDatePickerView extends MaterialCalendarView<Calendar> {
    private static final int DEF_STYLE_ATTR = R.attr.materialDatePickerStyle;
    private static final ColorDrawable emptyColor = new ColorDrawable(0);
    private static final ColorDrawable selectedColor = new ColorDrawable(SupportMenu.CATEGORY_MASK);
    private final AdapterView.OnItemClickListener onItemClickListener;
    private int selectedPosition;

    public MaterialDatePickerView(Context context) {
        this(context, null);
    }

    public MaterialDatePickerView(Context context, AttributeSet attrs) {
        this(context, attrs, DEF_STYLE_ATTR);
    }

    public MaterialDatePickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.selectedPosition = -1;
        this.onItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.google.android.material.picker.MaterialDatePickerView.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MaterialDatePickerView.this.getMonthInYearAdapter().withinMonth(position)) {
                    MaterialDatePickerView.this.selectedPosition = position;
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
        int i = 0;
        while (i < parent.getCount()) {
            ViewCompat.setBackground(parent.getChildAt(i), i == this.selectedPosition ? selectedColor : emptyColor);
            i++;
        }
    }

    @Override // com.google.android.material.picker.MaterialCalendarView
    public Calendar getSelection() {
        return getMonthInYearAdapter().getItem(this.selectedPosition);
    }
}
