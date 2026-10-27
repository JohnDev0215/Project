package com.google.android.material.picker;

import android.os.Bundle;
import com.google.android.material.R;
import java.util.Calendar;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialDatePickerDialogFragment extends MaterialPickerDialogFragment<Calendar> {
    public static MaterialDatePickerDialogFragment newInstance() {
        return newInstance(0);
    }

    public static MaterialDatePickerDialogFragment newInstance(int themeResId) {
        MaterialDatePickerDialogFragment materialDatePickerDialogFragment = new MaterialDatePickerDialogFragment();
        Bundle args = new Bundle();
        addThemeToBundle(args, themeResId);
        materialDatePickerDialogFragment.setArguments(args);
        return materialDatePickerDialogFragment;
    }

    @Override // com.google.android.material.picker.MaterialPickerDialogFragment
    protected int getDefaultThemeAttr() {
        return R.attr.materialDatePickerDialogTheme;
    }

    @Override // com.google.android.material.picker.MaterialPickerDialogFragment
    protected MaterialCalendarView<? extends Calendar> createMaterialCalendarView() {
        return new MaterialDatePickerView(getContext());
    }

    @Override // com.google.android.material.picker.MaterialPickerDialogFragment
    protected String getHeaderText() {
        Calendar selectedDate = getMaterialCalendarView().getSelection();
        if (selectedDate == null) {
            return getContext().getResources().getString(R.string.mtrl_picker_header_prompt);
        }
        String startString = getSimpleDateFormat().format(selectedDate.getTime());
        return getContext().getResources().getString(R.string.mtrl_picker_header_selected, startString);
    }
}
