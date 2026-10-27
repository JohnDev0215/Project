package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$attr;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$style;
import com.android.car.ui.R$styleable;
import java.util.function.Consumer;
import spi.b2;
import spi.j3;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiPreference extends Preference implements j3 {
    private Consumer<Preference> mDisabledClickListener;
    private boolean mIsClickableWhileDisabled;
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mShowChevron;
    private boolean mUxRestricted;

    public CarUiPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mUxRestricted = false;
        this.mIsClickableWhileDisabled = false;
        init(attributeSet, i, i2);
    }

    private void init(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiPreference, i, i2);
        this.mShowChevron = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiPreference_carUiShowChevron, true);
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

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        if (getContext().getResources().getBoolean(R$bool.car_ui_preference_show_chevron) && this.mShowChevron) {
            if (getOnPreferenceClickListener() == null && getIntent() == null && getFragment() == null) {
                return;
            }
            setWidgetLayoutResource(R$layout.car_ui_preference_chevron);
        }
    }

    @Override // androidx.preference.Preference
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

    public void performClickUnrestricted(View view) {
        performClick();
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
        throw new UnsupportedOperationException("android:shouldDisableView isunsupported on CarUiPreferences");
    }

    public void setShowChevron(boolean z) {
        this.mShowChevron = z;
    }

    public void setUxRestricted(boolean z) {
        this.mUxRestricted = z;
        notifyChanged();
    }

    public CarUiPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R$style.Preference_CarUi_Preference);
    }

    public CarUiPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.carUiPreferenceStyle);
    }

    public CarUiPreference(Context context) {
        this(context, null);
    }
}
