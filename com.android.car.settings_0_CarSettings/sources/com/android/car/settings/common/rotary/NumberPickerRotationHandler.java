package com.android.car.settings.common.rotary;

import android.view.MotionEvent;
import android.view.View;
import android.widget.NumberPicker;

/* JADX INFO: loaded from: classes5.dex */
public class NumberPickerRotationHandler implements View.OnGenericMotionListener {
    @Override // android.view.View.OnGenericMotionListener
    public boolean onGenericMotion(View v, MotionEvent event) {
        View focusedView = v.findFocus();
        if (focusedView instanceof NumberPicker) {
            NumberPicker numberPicker = (NumberPicker) focusedView;
            float scroll = event.getAxisValue(26);
            numberPicker.setValue(numberPicker.getValue() + Math.round(scroll));
            return true;
        }
        return false;
    }
}
