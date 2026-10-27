package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.ViewUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiPreference extends Preference implements DisabledPreferenceCallback {
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mShowChevron;
    private boolean mUxRestricted;

    public CarUiPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mUxRestricted = false;
        init(attrs, defStyleAttr, defStyleRes);
    }

    public CarUiPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, R.style.Preference_CarUi_Preference);
    }

    public CarUiPreference(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.carUiPreferenceStyle);
    }

    public CarUiPreference(Context context) {
        this(context, null);
    }

    private void init(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiPreference, defStyleAttr, defStyleRes);
        this.mShowChevron = a.getBoolean(R.styleable.CarUiPreference_showChevron, true);
        this.mUxRestricted = a.getBoolean(R.styleable.CarUiPreference_car_ui_ux_restricted, false);
        a.recycle();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        ViewUtils.makeAllViewsUxRestricted(holder.itemView, isUxRestricted());
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        boolean allowChevron = getContext().getResources().getBoolean(R.bool.car_ui_preference_show_chevron);
        if (!allowChevron || !this.mShowChevron) {
            return;
        }
        if (getOnPreferenceClickListener() != null || getIntent() != null || getFragment() != null) {
            setWidgetLayoutResource(R.layout.car_ui_preference_chevron);
        }
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

    public void setShowChevron(boolean showChevron) {
        this.mShowChevron = showChevron;
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

    @Override // com.android.car.ui.preference.UxRestrictablePreference
    public void setUxRestricted(boolean restricted) {
        this.mUxRestricted = restricted;
        notifyChanged();
    }
}
