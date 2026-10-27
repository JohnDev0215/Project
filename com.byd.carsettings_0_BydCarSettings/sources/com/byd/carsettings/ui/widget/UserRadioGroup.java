package com.byd.carsettings.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.autofill.AutofillManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.IdRes;
import b.b.m.c.a;
import java.lang.reflect.Field;

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
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            a.c("UserRadioGroup", ", ---getSuperCheckedId---e: " + e2.toString());
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    private void setCheckedIdWithoutCallback(@IdRes int i) {
        boolean z = i != getSuperCheckedId();
        setSuperCheckedId(i);
        if (z) {
            AutofillManager autofillManager = (AutofillManager) getContext().getSystemService(AutofillManager.class);
            a.a("UserRadioGroup", ", ---setCheckedIdWithoutCallback---afm: " + autofillManager);
            if (autofillManager != null) {
                autofillManager.notifyValueChanged(this);
            }
        }
    }

    private void setSuperCheckedId(int i) {
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mCheckedId");
            a.a("UserRadioGroup", ", ---setSuperCheckedId---field: " + declaredField);
            declaredField.setAccessible(true);
            declaredField.set(this, Integer.valueOf(i));
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            a.c("UserRadioGroup", ", ---setSuperCheckedId---e: " + e2.toString());
        }
    }

    public void a(@IdRes int i, boolean z) {
        if (z) {
            check(i);
            return;
        }
        int superCheckedId = getSuperCheckedId();
        if (i == superCheckedId) {
            return;
        }
        a.a("UserRadioGroup", ", ---check---id: " + i + ", preCheckedId: " + superCheckedId);
        if (superCheckedId != -1) {
            b(superCheckedId, false);
        }
        if (i != -1) {
            b(i, true);
        }
        setCheckedIdWithoutCallback(i);
    }

    public final void b(int i, boolean z) {
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null || !(viewFindViewById instanceof RadioButton)) {
            return;
        }
        ((RadioButton) viewFindViewById).setChecked(z);
    }

    public UserRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
