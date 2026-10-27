package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.utils.DeviceCategoryUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class UserAgentHelper {
    private Context mContext;
    private String mDeviceCategory;
    private DeviceInfoManager mDeviceInfoManager;
    private String mUserAgent;

    private String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    public UserAgentHelper(Context context, DeviceInfoManager deviceInfoManager) {
        this.mContext = context;
        this.mDeviceInfoManager = deviceInfoManager;
    }

    public void addUserAgentIfNotSet(MetricEntry metricEntry) {
        if (hasUserAgent(metricEntry)) {
            return;
        }
        if (TextUtils.isEmpty(this.mUserAgent)) {
            this.mUserAgent = generateUserAgent();
        }
        metricEntry.getDatapoints().add(new DataPoint(ClickStreamData.USER_AGENT.getName(), this.mUserAgent, 1, DataPointType.DV));
    }

    private String generateUserAgent() {
        if (TextUtils.isEmpty(this.mDeviceCategory)) {
            this.mDeviceCategory = DeviceCategoryUtil.getDeviceCategory(this.mContext);
        }
        return String.format(Locale.US, "AMZN(%s/%s/%s,%s/%s,//,DCM)", emptyIfNull(this.mDeviceCategory), Build.PRODUCT, this.mDeviceInfoManager.getDeviceInfo().getDeviceInfo(Splash.PARAMS_DEVICE_TYPE), "Android", Build.VERSION.RELEASE);
    }

    private boolean hasUserAgent(MetricEntry metricEntry) {
        Iterator<DataPoint> it = metricEntry.getDatapoints().iterator();
        while (it.hasNext()) {
            if (ClickStreamData.USER_AGENT.getName().equals(it.next().getName())) {
                return true;
            }
        }
        return false;
    }
}
