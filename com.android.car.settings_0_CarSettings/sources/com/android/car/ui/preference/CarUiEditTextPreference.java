package com.android.car.ui.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;
import com.android.car.ui.utils.ViewUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes6.dex */
public class CarUiEditTextPreference extends EditTextPreference implements UxRestrictablePreference {
    private Consumer<Preference> mRestrictedClickListener;
    private boolean mShowChevron;
    private boolean mUxRestricted;

    public CarUiEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mUxRestricted = false;
        this.mShowChevron = true;
    }

    public CarUiEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mUxRestricted = false;
        this.mShowChevron = true;
    }

    public CarUiEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mUxRestricted = false;
        this.mShowChevron = true;
    }

    public CarUiEditTextPreference(Context context) {
        super(context);
        this.mUxRestricted = false;
        this.mShowChevron = true;
    }

    protected void setTwoActionLayout() {
        setLayoutResource(R.layout.car_ui_two_action_preference);
    }

    public View getWidgetActionContainer(PreferenceViewHolder holder) {
        return CarUiUtils.findViewByRefId(holder.itemView, R.id.action_widget_container);
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        boolean allowChevron = getContext().getResources().getBoolean(R.bool.car_ui_preference_show_chevron);
        if (!allowChevron || !this.mShowChevron) {
            return;
        }
        setWidgetLayoutResource(R.layout.car_ui_preference_chevron);
    }

    public void setShowChevron(boolean showChevron) {
        this.mShowChevron = showChevron;
    }

    @Override // androidx.preference.Preference
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
