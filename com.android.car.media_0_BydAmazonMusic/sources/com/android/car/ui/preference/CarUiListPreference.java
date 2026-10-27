package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$attr;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$style;
import com.android.car.ui.R$styleable;
import java.util.function.Consumer;
import spi.b2;
import spi.fc;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiListPreference extends ListPreference implements fc {
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mUxRestricted;

    public CarUiListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUxRestricted = false;
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiPreference, i, i2);
        this.mUxRestricted = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiPreference_car_ui_ux_restricted, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Nullable
    public Consumer<Preference> getOnClickWhileRestrictedListener() {
        return this.mRestrictedClickListener;
    }

    public boolean isUxRestricted() {
        return this.mUxRestricted;
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        if (getContext().getResources().getBoolean(R$bool.car_ui_preference_show_chevron)) {
            setWidgetLayoutResource(R$layout.car_ui_preference_chevron);
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        b2.l(preferenceViewHolder.itemView, isUxRestricted());
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        if ((!isEnabled() && !isSelectable()) || !isUxRestricted()) {
            super.performClick();
            return;
        }
        Consumer<Preference> consumer = this.mRestrictedClickListener;
        if (consumer != null) {
            consumer.accept(this);
        }
    }

    public void setOnClickWhileRestrictedListener(@Nullable Consumer<Preference> consumer) {
        this.mRestrictedClickListener = consumer;
    }

    public void setUxRestricted(boolean z) {
        if (z != this.mUxRestricted) {
            this.mUxRestricted = z;
            notifyChanged();
        }
    }

    public CarUiListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R$style.Preference_CarUi_Preference);
    }

    public CarUiListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.carUiPreferenceStyle);
    }

    public CarUiListPreference(Context context) {
        this(context, null);
    }
}
