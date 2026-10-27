package com.amazon.mp3.profile;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import com.amazon.mp3.service.metrics.CampaignMetrics;
import com.amazon.mp3.util.ConnectivityUtil;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceProfileProvider {
    private final CampaignMetrics mCampaignMetrics;
    private final Configuration mConfiguration;

    public DeviceProfileProvider(Context context) {
        this.mConfiguration = context.getResources().getConfiguration();
        this.mCampaignMetrics = CampaignMetrics.loadCampaignReferrer(context);
    }

    public String getDeviceModel() {
        return Build.MODEL;
    }

    public String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getCarrierName() {
        return ConnectivityUtil.getCarrierName();
    }

    public String getSimMobileCountryCode() {
        return String.valueOf(this.mConfiguration.mcc);
    }

    public String getSimMobileNetworkCode() {
        return String.valueOf(this.mConfiguration.mnc);
    }

    public String getInstallReferrer() {
        return this.mCampaignMetrics.getSource();
    }
}
