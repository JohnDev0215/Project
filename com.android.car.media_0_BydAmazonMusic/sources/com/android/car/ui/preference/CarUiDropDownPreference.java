package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$layout;
import java.util.function.Consumer;
import spi.b2;
import spi.fc;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiDropDownPreference extends DropDownPreference implements fc {
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mUxRestricted;

    public CarUiDropDownPreference(Context context) {
        super(context);
        this.mUxRestricted = false;
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

    @Override // androidx.preference.DropDownPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        b2.l(preferenceViewHolder.itemView, isUxRestricted());
    }

    @Override // androidx.preference.DropDownPreference, androidx.preference.DialogPreference, androidx.preference.Preference
    public void onClick() {
        getPreferenceManager().showDialog(this);
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

    public CarUiDropDownPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUxRestricted = false;
    }

    public CarUiDropDownPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUxRestricted = false;
    }

    public CarUiDropDownPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUxRestricted = false;
    }
}
