package com.google.android.material.picker;

import android.os.Bundle;
import android.util.Pair;
import com.google.android.material.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialDateRangePickerDialogFragment extends MaterialPickerDialogFragment<Pair<Calendar, Calendar>> {
    public static MaterialDateRangePickerDialogFragment newInstance() {
        return newInstance(0);
    }

    public static MaterialDateRangePickerDialogFragment newInstance(int themeResId) {
        MaterialDateRangePickerDialogFragment materialDateRangePickerDialogFragment = new MaterialDateRangePickerDialogFragment();
        Bundle args = new Bundle();
        addThemeToBundle(args, themeResId);
        materialDateRangePickerDialogFragment.setArguments(args);
        return materialDateRangePickerDialogFragment;
    }

    @Override // com.google.android.material.picker.MaterialPickerDialogFragment
    protected int getDefaultThemeAttr() {
        return R.attr.materialDateRangePickerDialogTheme;
    }

    @Override // com.google.android.material.picker.MaterialPickerDialogFragment
    protected MaterialCalendarView<? extends Pair<Calendar, Calendar>> createMaterialCalendarView() {
        return new MaterialDateRangePickerView(getContext());
    }

    @Override // com.google.android.material.picker.MaterialPickerDialogFragment
    protected String getHeaderText() {
        Pair<Calendar, Calendar> startAndEnd = getMaterialCalendarView().getSelection();
        if (startAndEnd == null) {
            return getContext().getResources().getString(R.string.mtrl_picker_range_header_prompt);
        }
        String startString = getSimpleDateFormat().format(((Calendar) startAndEnd.first).getTime());
        String endString = getSimpleDateFormat().format(((Calendar) startAndEnd.second).getTime());
        return getContext().getResources().getString(R.string.mtrl_picker_range_header_selected, startString, endString);
    }
}
