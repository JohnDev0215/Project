package com.byd.mvp.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.autofill.AutofillManager;
import android.widget.RadioGroup;
import java.lang.reflect.Field;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class UserRadioGroup extends RadioGroup {
    public UserRadioGroup(Context context) {
        super(context);
    }

    private int getSuperCheckedId() {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mCheckedId");
            a.a("UserRadioGroup", ", ---getSuperCheckedId---field: " + declaredField);
            declaredField.setAccessible(true);
            return ((Integer) declaredField.get(this)).intValue();
        } catch (IllegalAccessException | NoSuchFieldException e8) {
            a.b("UserRadioGroup", ", ---getSuperCheckedId---e: " + e8.toString());
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    private void setCheckedIdWithoutCallback(int i8) {
        boolean z7 = i8 != getSuperCheckedId();
        setSuperCheckedId(i8);
        if (z7) {
            AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
            a.a("UserRadioGroup", ", ---setCheckedIdWithoutCallback---afm: " + autofillManager);
            if (autofillManager != null) {
                autofillManager.notifyValueChanged(this);
            }
        }
    }

    private void setSuperCheckedId(int i8) {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mCheckedId");
            a.a("UserRadioGroup", ", ---setSuperCheckedId---field: " + declaredField);
            declaredField.setAccessible(true);
            declaredField.set(this, Integer.valueOf(i8));
        } catch (IllegalAccessException | NoSuchFieldException e8) {
            a.b("UserRadioGroup", ", ---setSuperCheckedId---e: " + e8.toString());
        }
    }

    public UserRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
