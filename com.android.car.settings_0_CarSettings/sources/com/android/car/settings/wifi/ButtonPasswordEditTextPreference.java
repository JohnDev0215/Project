package com.android.car.settings.wifi;

import android.content.Context;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.common.PasswordEditTextPreference;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes5.dex */
public class ButtonPasswordEditTextPreference extends PasswordEditTextPreference {
    private boolean mIsButtonShown;
    private OnButtonClickListener mOnButtonClickListener;

    public interface OnButtonClickListener {
        void onButtonClick(ButtonPasswordEditTextPreference buttonPasswordEditTextPreference);
    }

    public ButtonPasswordEditTextPreference(Context context) {
        super(context);
        this.mIsButtonShown = true;
        init();
    }

    private void init() {
        setTwoActionLayout();
    }

    public void showButton(boolean isShown) {
        this.mIsButtonShown = isShown;
        notifyChanged();
    }

    public boolean isButtonShown() {
        return this.mIsButtonShown;
    }

    @Override // com.android.car.settings.common.ValidatedEditTextPreference, com.android.car.ui.preference.CarUiEditTextPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View containerWithoutWidget = CarUiUtils.findViewByRefId(holder.itemView, R.id.car_ui_preference_container_without_widget);
        View actionContainer = getWidgetActionContainer(holder);
        View widgetFrame = holder.findViewById(android.R.id.widget_frame);
        holder.itemView.setFocusable(!this.mIsButtonShown);
        containerWithoutWidget.setOnClickListener(this.mIsButtonShown ? new View.OnClickListener() { // from class: com.android.car.settings.wifi.-$$Lambda$ButtonPasswordEditTextPreference$tlnqP2kW2ggdiBZpAHjx4_AH10Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.performClick(view);
            }
        } : null);
        containerWithoutWidget.setClickable(this.mIsButtonShown);
        containerWithoutWidget.setFocusable(this.mIsButtonShown);
        actionContainer.setVisibility(this.mIsButtonShown ? 0 : 8);
        widgetFrame.setOnClickListener(this.mIsButtonShown ? new View.OnClickListener() { // from class: com.android.car.settings.wifi.-$$Lambda$ButtonPasswordEditTextPreference$TEYq8dHrHmjUuf5tNDIpFFqQfL4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$ButtonPasswordEditTextPreference(view);
            }
        } : null);
        widgetFrame.setFocusable(this.mIsButtonShown);
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$ButtonPasswordEditTextPreference(View v) {
        performButtonClick();
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.mOnButtonClickListener = listener;
    }

    public void performButtonClick() {
        OnButtonClickListener onButtonClickListener;
        if (isButtonShown() && (onButtonClickListener = this.mOnButtonClickListener) != null) {
            onButtonClickListener.onButtonClick(this);
        }
    }
}
