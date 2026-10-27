package com.android.car.ui.preference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.ViewUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes6.dex */
public class CarUiDropDownPreference extends DropDownPreference implements UxRestrictablePreference {
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mUxRestricted;

    public CarUiDropDownPreference(Context context) {
        super(context);
        this.mUxRestricted = false;
    }

    public CarUiDropDownPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUxRestricted = false;
    }

    public CarUiDropDownPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mUxRestricted = false;
    }

    public CarUiDropDownPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mUxRestricted = false;
    }

    @Override // androidx.preference.DropDownPreference, androidx.preference.DialogPreference, androidx.preference.Preference
    protected void onClick() {
        getPreferenceManager().showDialog(this);
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        boolean showChevron = getContext().getResources().getBoolean(R.bool.car_ui_preference_show_chevron);
        if (!showChevron) {
            return;
        }
        setWidgetLayoutResource(R.layout.car_ui_preference_chevron);
    }

    @Override // androidx.preference.DropDownPreference, androidx.preference.Preference
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
        if (restricted != this.mUxRestricted) {
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
