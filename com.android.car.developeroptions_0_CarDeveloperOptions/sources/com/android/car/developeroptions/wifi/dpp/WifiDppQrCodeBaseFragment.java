package com.android.car.developeroptions.wifi.dpp;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.car.developeroptions.core.InstrumentedFragment;

/* JADX INFO: loaded from: classes.dex */
public abstract class WifiDppQrCodeBaseFragment extends InstrumentedFragment {
    private ImageView mDevicesCheckCircleGreenHeaderIcon;
    private ImageView mHeaderIcon;
    protected TextView mSummary;
    protected TextView mTitle;
    protected View mTitleSummaryContainer;

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mHeaderIcon = (ImageView) view.findViewById(R.id.icon);
        this.mDevicesCheckCircleGreenHeaderIcon = (ImageView) view.findViewById(com.android.car.developeroptions.R.id.devices_check_circle_green_icon);
        this.mTitle = (TextView) view.findViewById(R.id.title);
        this.mSummary = (TextView) view.findViewById(R.id.summary);
        this.mTitleSummaryContainer = view.findViewById(com.android.car.developeroptions.R.id.title_summary_container);
    }

    protected void setHeaderIconImageResource(int i) {
        if (i == com.android.car.developeroptions.R.drawable.ic_devices_check_circle_green) {
            this.mHeaderIcon.setVisibility(8);
            this.mDevicesCheckCircleGreenHeaderIcon.setVisibility(0);
        } else {
            this.mDevicesCheckCircleGreenHeaderIcon.setVisibility(8);
            this.mHeaderIcon.setImageResource(i);
            this.mHeaderIcon.setVisibility(0);
        }
    }
}
