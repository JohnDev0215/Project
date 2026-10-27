package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.car.ui.R$styleable;
import java.util.function.Consumer;
import spi.b2;
import spi.j3;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiSwitchPreference extends SwitchPreference implements j3 {
    private Consumer<Preference> mDisabledClickListener;
    private boolean mIsClickableWhileDisabled;
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mUxRestricted;

    public CarUiSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUxRestricted = false;
        this.mIsClickableWhileDisabled = false;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiPreference);
        this.mUxRestricted = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiPreference_car_ui_ux_restricted, false);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiPreference_carUiClickableWhileDisabled, false);
        this.mIsClickableWhileDisabled = z;
        if (z) {
            super.setShouldDisableView(false);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public Consumer<Preference> getDisabledClickListener() {
        return this.mDisabledClickListener;
    }

    @Nullable
    public Consumer<Preference> getOnClickWhileRestrictedListener() {
        return this.mRestrictedClickListener;
    }

    public boolean isClickableWhileDisabled() {
        return this.mIsClickableWhileDisabled;
    }

    public boolean isUxRestricted() {
        return this.mUxRestricted;
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        b2.k(preferenceViewHolder.itemView, isEnabled(), isUxRestricted());
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        if (!isEnabled() && this.mIsClickableWhileDisabled) {
            Consumer<Preference> consumer = this.mDisabledClickListener;
            if (consumer != null) {
                consumer.accept(this);
                return;
            }
            return;
        }
        if ((!isEnabled() && !isSelectable()) || !isUxRestricted()) {
            super.performClick();
            return;
        }
        Consumer<Preference> consumer2 = this.mRestrictedClickListener;
        if (consumer2 != null) {
            consumer2.accept(this);
        }
    }

    public void setClickableWhileDisabled(boolean z) {
        if (this.mIsClickableWhileDisabled != z) {
            super.setShouldDisableView(!z);
            this.mIsClickableWhileDisabled = z;
            notifyChanged();
        }
    }

    public void setDisabledClickListener(Consumer<Preference> consumer) {
        this.mDisabledClickListener = consumer;
    }

    @Override // spi.j3
    @Deprecated
    public /* bridge */ /* synthetic */ void setMessageToShowWhenDisabledPreferenceClicked(@NonNull String str) {
        super.setMessageToShowWhenDisabledPreferenceClicked(str);
    }

    public void setOnClickWhileRestrictedListener(@Nullable Consumer<Preference> consumer) {
        this.mRestrictedClickListener = consumer;
    }

    @Override // androidx.preference.Preference
    public void setShouldDisableView(boolean z) {
        throw new UnsupportedOperationException("Dynamically setting shouldDisableView isunsupported in CarUiPreferences");
    }

    public void setUxRestricted(boolean z) {
        if (this.mUxRestricted != z) {
            this.mUxRestricted = z;
            notifyChanged();
        }
    }

    public CarUiSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mUxRestricted = false;
        this.mIsClickableWhileDisabled = false;
        init(attributeSet);
    }

    public CarUiSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUxRestricted = false;
        this.mIsClickableWhileDisabled = false;
        init(attributeSet);
    }

    public CarUiSwitchPreference(Context context) {
        super(context);
        this.mUxRestricted = false;
        this.mIsClickableWhileDisabled = false;
        init(null);
    }
}
