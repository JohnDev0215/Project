package com.android.car.ui.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiTwoActionSwitchPreference extends CarUiTwoActionBasePreference {
    private boolean mSecondaryActionChecked;
    protected Consumer<Boolean> mSecondaryActionOnClickListener;

    public CarUiTwoActionSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CarUiTwoActionSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CarUiTwoActionSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarUiTwoActionSwitchPreference(Context context) {
        super(context);
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        setLayoutResourceInternal(R.layout.car_ui_preference_two_action_switch);
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    protected void performSecondaryActionClickInternal() {
        if (isSecondaryActionEnabled()) {
            if (isUxRestricted()) {
                Consumer<Preference> restrictedListener = getOnClickWhileRestrictedListener();
                if (restrictedListener != null) {
                    restrictedListener.accept(this);
                    return;
                }
                return;
            }
            this.mSecondaryActionChecked = !this.mSecondaryActionChecked;
            notifyChanged();
            Consumer<Boolean> consumer = this.mSecondaryActionOnClickListener;
            if (consumer != null) {
                consumer.accept(Boolean.valueOf(this.mSecondaryActionChecked));
            }
        }
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View firstActionContainer = CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_first_action_container);
        View secondActionContainer = CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_second_action_container);
        View secondaryAction = CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_secondary_action);
        Switch s = (Switch) CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_secondary_action_concrete);
        holder.itemView.setFocusable(false);
        holder.itemView.setClickable(false);
        firstActionContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionSwitchPreference$f73yIdclmCf2GmoWYFHah0foQHo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.performClick(view);
            }
        });
        firstActionContainer.setEnabled(isEnabled() || isUxRestricted());
        firstActionContainer.setFocusable(isEnabled() || isUxRestricted());
        secondActionContainer.setVisibility(this.mSecondaryActionVisible ? 0 : 8);
        s.setChecked(this.mSecondaryActionChecked);
        s.setEnabled(isSecondaryActionEnabled());
        secondaryAction.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionSwitchPreference$0UrFOx3Y19pWiiRQlHbQfeW0gGA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$CarUiTwoActionSwitchPreference(view);
            }
        });
        secondaryAction.setEnabled(isSecondaryActionEnabled() || isUxRestricted());
        secondaryAction.setFocusable(isSecondaryActionEnabled() || isUxRestricted());
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$CarUiTwoActionSwitchPreference(View v) {
        performSecondaryActionClickInternal();
    }

    public void setSecondaryActionChecked(boolean checked) {
        this.mSecondaryActionChecked = checked;
        notifyChanged();
    }

    public boolean isSecondaryActionChecked() {
        return this.mSecondaryActionChecked;
    }

    public void setOnSecondaryActionClickListener(Consumer<Boolean> onClickListener) {
        this.mSecondaryActionOnClickListener = onClickListener;
        notifyChanged();
    }
}
