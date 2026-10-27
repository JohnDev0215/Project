package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SwitchPreference;
import com.android.car.ui.R;
import com.android.car.ui.utils.ViewUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes6.dex */
public class CarUiSwitchPreference extends SwitchPreference implements DisabledPreferenceCallback {
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mUxRestricted;

    public CarUiSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mUxRestricted = false;
        init(attrs);
    }

    public CarUiSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mUxRestricted = false;
        init(attrs);
    }

    public CarUiSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUxRestricted = false;
        init(attrs);
    }

    public CarUiSwitchPreference(Context context) {
        super(context);
        this.mUxRestricted = false;
        init(null);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiPreference);
        this.mUxRestricted = a.getBoolean(R.styleable.CarUiPreference_car_ui_ux_restricted, false);
        a.recycle();
    }

    @Override // androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        ViewUtils.makeAllViewsUxRestricted(holder.itemView, isUxRestricted());
    }

    @Override // androidx.preference.Preference
    public void performClick() {
        if ((isEnabled() || isSelectable()) && isUxRestricted()) {
            Consumer<Preference> consumer = this.mRestrictedClickListener;
            if (consumer != null) {
                consumer.accept(this);
                return;
            }
            return;
        }
        super.performClick();
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public void setUxRestricted(boolean restricted) {
        if (this.mUxRestricted != restricted) {
            this.mUxRestricted = restricted;
            notifyChanged();
        }
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public boolean isUxRestricted() {
        return this.mUxRestricted;
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public void setOnClickWhileRestrictedListener(Consumer<Preference> listener) {
        this.mRestrictedClickListener = listener;
    }

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public Consumer<Preference> getOnClickWhileRestrictedListener() {
        return this.mRestrictedClickListener;
    }
}
