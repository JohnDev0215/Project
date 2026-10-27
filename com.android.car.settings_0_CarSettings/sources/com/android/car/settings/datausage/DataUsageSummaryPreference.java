package com.android.car.settings.datausage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;
import com.android.car.settings.common.ProgressBarPreference;

/* JADX INFO: loaded from: classes5.dex */
public class DataUsageSummaryPreference extends ProgressBarPreference {
    private CharSequence mCarrierInfoText;
    private int mCarrierInfoTextStyle;
    private CharSequence mDataLimitText;
    private Intent mManageSubscriptionIntent;
    private CharSequence mRemainingBillingCycleText;

    public DataUsageSummaryPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mCarrierInfoTextStyle = R.style.DataUsageSummaryCarrierInfoTextAppearance;
        init();
    }

    public DataUsageSummaryPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCarrierInfoTextStyle = R.style.DataUsageSummaryCarrierInfoTextAppearance;
        init();
    }

    public DataUsageSummaryPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCarrierInfoTextStyle = R.style.DataUsageSummaryCarrierInfoTextAppearance;
        init();
    }

    public DataUsageSummaryPreference(Context context) {
        super(context);
        this.mCarrierInfoTextStyle = R.style.DataUsageSummaryCarrierInfoTextAppearance;
        init();
    }

    private void init() {
        setLayoutResource(R.layout.data_usage_summary_preference);
    }

    public void setDataLimitText(CharSequence text) {
        if (!TextUtils.equals(this.mDataLimitText, text)) {
            this.mDataLimitText = text;
            notifyChanged();
        }
    }

    public CharSequence getDataLimitText() {
        return this.mDataLimitText;
    }

    public void setRemainingBillingCycleText(CharSequence text) {
        if (!TextUtils.equals(this.mRemainingBillingCycleText, text)) {
            this.mRemainingBillingCycleText = text;
            notifyChanged();
        }
    }

    public CharSequence getRemainingBillingCycleText() {
        return this.mRemainingBillingCycleText;
    }

    public void setCarrierInfoText(CharSequence text) {
        if (!TextUtils.equals(this.mCarrierInfoText, text)) {
            this.mCarrierInfoText = text;
            notifyChanged();
        }
    }

    public CharSequence getCarrierInfoText() {
        return this.mCarrierInfoText;
    }

    public void setCarrierInfoTextStyle(int styleId) {
        if (this.mCarrierInfoTextStyle != styleId) {
            this.mCarrierInfoTextStyle = styleId;
            notifyChanged();
        }
    }

    public int getCarrierInfoTextStyle() {
        return this.mCarrierInfoTextStyle;
    }

    public void setManageSubscriptionIntent(Intent intent) {
        this.mManageSubscriptionIntent = intent;
        notifyChanged();
    }

    public Intent getManageSubscriptionIntent() {
        return this.mManageSubscriptionIntent;
    }

    @Override // com.android.car.settings.common.ProgressBarPreference, com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        setTextAndVisibility((TextView) view.findViewById(R.id.data_limit_text), this.mDataLimitText);
        setTextAndVisibility((TextView) view.findViewById(R.id.remaining_billing_cycle_time_text), this.mRemainingBillingCycleText);
        TextView carrierInfo = (TextView) view.findViewById(R.id.carrier_info_text);
        setTextAndVisibility(carrierInfo, this.mCarrierInfoText);
        carrierInfo.setTextAppearance(this.mCarrierInfoTextStyle);
        Button button = (Button) view.findViewById(R.id.manage_subscription_button);
        if (this.mManageSubscriptionIntent != null) {
            button.setVisibility(0);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.settings.datausage.-$$Lambda$DataUsageSummaryPreference$epuEekXu7XjLHUxsDYed1CHV53o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$onBindViewHolder$0$DataUsageSummaryPreference(view2);
                }
            });
        } else {
            button.setVisibility(8);
        }
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$DataUsageSummaryPreference(View v) {
        getContext().startActivity(this.mManageSubscriptionIntent);
    }

    private void setTextAndVisibility(TextView textView, CharSequence value) {
        if (!TextUtils.isEmpty(value)) {
            textView.setText(value);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }
}
