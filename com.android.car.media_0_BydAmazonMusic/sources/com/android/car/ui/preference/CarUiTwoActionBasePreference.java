package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.preference.Preference;
import com.android.car.ui.R$styleable;
import java.util.function.Consumer;
import spi.c;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public abstract class CarUiTwoActionBasePreference extends CarUiPreference {
    public boolean mSecondaryActionEnabled;
    public boolean mSecondaryActionVisible;

    public CarUiTwoActionBasePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(attributeSet);
    }

    private static void disallowResourceIds(@NonNull TypedArray typedArray, @StyleableRes int... iArr) {
        for (int i : iArr) {
            if (typedArray.hasValue(i)) {
                StringBuilder sbK = c.k("Setting this attribute is not allowed: ");
                sbK.append(typedArray.getResources().getResourceName(i));
                throw new AssertionError(sbK.toString());
            }
        }
    }

    @CallSuper
    public void init(@Nullable AttributeSet attributeSet) {
        setShowChevron(false);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiTwoActionBasePreference);
        try {
            disallowResourceIds(typedArrayObtainStyledAttributes, R$styleable.CarUiTwoActionBasePreference_carUiLayout, R$styleable.CarUiTwoActionBasePreference_android_layout, R$styleable.CarUiTwoActionBasePreference_widgetLayout, R$styleable.CarUiTwoActionBasePreference_android_widgetLayout);
            typedArrayObtainStyledAttributes.recycle();
            typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiTwoActionPreference);
            try {
                this.mSecondaryActionVisible = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiTwoActionPreference_actionShown, true);
                this.mSecondaryActionEnabled = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiTwoActionPreference_actionEnabled, true);
            } finally {
            }
        } finally {
        }
    }

    public boolean isSecondaryActionEnabled() {
        return this.mSecondaryActionEnabled && isEnabled();
    }

    public boolean isSecondaryActionVisible() {
        return this.mSecondaryActionVisible;
    }

    public void performSecondaryActionClick() {
        if (!isSecondaryActionEnabled()) {
            if (!isClickableWhileDisabled() || getDisabledClickListener() == null) {
                return;
            }
            getDisabledClickListener().accept(this);
            return;
        }
        if (!isUxRestricted()) {
            performSecondaryActionClickInternal();
            return;
        }
        Consumer<Preference> onClickWhileRestrictedListener = getOnClickWhileRestrictedListener();
        if (onClickWhileRestrictedListener != null) {
            onClickWhileRestrictedListener.accept(this);
        }
    }

    public abstract void performSecondaryActionClickInternal();

    @Override // androidx.preference.Preference
    public void setLayoutResource(@LayoutRes int i) {
        throw new UnsupportedOperationException();
    }

    public void setLayoutResourceInternal(@LayoutRes int i) {
        super.setLayoutResource(i);
    }

    @Override // com.android.car.ui.preference.CarUiPreference, spi.j3
    @Deprecated
    public /* bridge */ /* synthetic */ void setMessageToShowWhenDisabledPreferenceClicked(@NonNull String str) {
        super.setMessageToShowWhenDisabledPreferenceClicked(str);
    }

    public void setSecondaryActionEnabled(boolean z) {
        this.mSecondaryActionEnabled = z;
        notifyChanged();
    }

    public void setSecondaryActionVisible(boolean z) {
        this.mSecondaryActionVisible = z;
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public void setWidgetLayoutResource(@LayoutRes int i) {
        throw new UnsupportedOperationException();
    }

    public CarUiTwoActionBasePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(attributeSet);
    }

    public CarUiTwoActionBasePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(attributeSet);
    }

    public CarUiTwoActionBasePreference(Context context) {
        super(context);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(null);
    }
}
