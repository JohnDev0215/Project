package com.android.car.settings.common.rotary;

import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class NumberPickerUtils {
    static final Set<Class> POSSIBLE_PARENT_TYPES = new HashSet(Arrays.asList(TimePicker.class, DatePicker.class));

    public static boolean focusChildNumberPicker(View view) {
        ViewGroup numberPickerParent = (ViewGroup) view;
        List<NumberPicker> numberPickers = new ArrayList<>();
        getNumberPickerDescendants(numberPickers, numberPickerParent);
        if (numberPickers.isEmpty()) {
            return false;
        }
        numberPickerParent.setDescendantFocusability(262144);
        numberPickers.get(0).requestFocus();
        return true;
    }

    public static void getNumberPickerDescendants(List<NumberPicker> numberPickers, ViewGroup view) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            if (child instanceof NumberPicker) {
                numberPickers.add((NumberPicker) child);
            } else if (child instanceof ViewGroup) {
                getNumberPickerDescendants(numberPickers, (ViewGroup) child);
            }
        }
    }

    static boolean hasCommonNumberPickerParent(View view1, View view2) {
        if (view1 == null || view2 == null) {
            return false;
        }
        View view1Ancestor = getNumberPickerParent(view1);
        View view2Ancestor = getNumberPickerParent(view2);
        return (view1Ancestor == null || view2Ancestor == null || view1Ancestor != view2Ancestor) ? false : true;
    }

    static View getNumberPickerParent(View view) {
        if (view == null) {
            return null;
        }
        if (POSSIBLE_PARENT_TYPES.contains(view.getClass())) {
            return view;
        }
        Object parent = view.getParent();
        if (!(parent instanceof View)) {
            return null;
        }
        return getNumberPickerParent((View) parent);
    }
}
