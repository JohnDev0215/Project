package com.android.car.ui.preference;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.widget.RadioButton;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.TwoStatePreference;
import com.android.car.ui.R$attr;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import spi.b2;
import spi.fc;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiRadioButtonPreference extends TwoStatePreference implements fc {
    public CarUiRadioButtonPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setLayoutResource(R$layout.car_ui_preference);
        setWidgetLayoutResource(R$layout.car_ui_radio_button_preference_widget);
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        ((RadioButton) b2.d(preferenceViewHolder.itemView, R$id.radio_button)).setChecked(isChecked());
        b2.l(preferenceViewHolder.itemView, false);
    }

    @Override // androidx.preference.Preference
    public final void performClick() {
        if (!isEnabled()) {
            isSelectable();
        }
        super.performClick();
    }

    public CarUiRadioButtonPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CarUiRadioButtonPreference(Context context, AttributeSet attributeSet) {
        int i = R$attr.preferenceStyle;
        SparseArray<String> sparseArray = b2.a;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i, typedValue, true);
        this(context, attributeSet, typedValue.resourceId == 0 ? R.attr.preferenceStyle : i);
    }

    public CarUiRadioButtonPreference(Context context) {
        this(context, null);
    }
}
