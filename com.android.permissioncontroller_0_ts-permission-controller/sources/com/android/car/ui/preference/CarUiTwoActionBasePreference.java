package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.android.car.ui.R;

/* JADX INFO: loaded from: classes7.dex */
public abstract class CarUiTwoActionBasePreference extends CarUiPreference {
    protected boolean mSecondaryActionEnabled;
    protected boolean mSecondaryActionVisible;

    protected abstract void performSecondaryActionClickInternal();

    public CarUiTwoActionBasePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(attrs);
    }

    public CarUiTwoActionBasePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(attrs);
    }

    public CarUiTwoActionBasePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(attrs);
    }

    public CarUiTwoActionBasePreference(Context context) {
        super(context);
        this.mSecondaryActionEnabled = true;
        this.mSecondaryActionVisible = true;
        init(null);
    }

    protected void init(AttributeSet attrs) {
        setShowChevron(false);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiTwoActionBasePreference);
        try {
            disallowResourceIds(a, R.styleable.CarUiTwoActionBasePreference_layout, R.styleable.CarUiTwoActionBasePreference_android_layout, R.styleable.CarUiTwoActionBasePreference_widgetLayout, R.styleable.CarUiTwoActionBasePreference_android_widgetLayout);
            a.recycle();
            a = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiTwoActionPreference);
            try {
                this.mSecondaryActionVisible = a.getBoolean(R.styleable.CarUiTwoActionPreference_actionShown, true);
                this.mSecondaryActionEnabled = a.getBoolean(R.styleable.CarUiTwoActionPreference_actionEnabled, true);
            } finally {
            }
        } finally {
        }
    }

    public boolean isSecondaryActionEnabled() {
        return this.mSecondaryActionEnabled && isEnabled();
    }

    public void setSecondaryActionEnabled(boolean enabled) {
        this.mSecondaryActionEnabled = enabled;
        notifyChanged();
    }

    public boolean isSecondaryActionVisible() {
        return this.mSecondaryActionVisible;
    }

    public void setSecondaryActionVisible(boolean visible) {
        this.mSecondaryActionVisible = visible;
        notifyChanged();
    }

    public void performSecondaryActionClick() {
        if (this.mSecondaryActionEnabled && this.mSecondaryActionVisible) {
            performSecondaryActionClickInternal();
        }
    }

    protected void setLayoutResourceInternal(int layoutResId) {
        super.setLayoutResource(layoutResId);
    }

    @Override // androidx.preference.Preference
    public void setLayoutResource(int layoutResId) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.preference.Preference
    public void setWidgetLayoutResource(int widgetLayoutResId) {
        throw new UnsupportedOperationException();
    }

    private static void disallowResourceIds(TypedArray a, int... indices) {
        for (int index : indices) {
            if (a.hasValue(index)) {
                throw new AssertionError("Setting this attribute is not allowed: " + a.getResources().getResourceName(index));
            }
        }
    }
}
