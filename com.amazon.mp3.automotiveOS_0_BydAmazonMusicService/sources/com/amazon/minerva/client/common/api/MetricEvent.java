package com.amazon.minerva.client.common.api;

import com.amazon.minerva.client.common.internal.MetricEventAdapter;
import com.amazon.minerva.client.common.internal.android.AndroidMetricEventAdapter;
import com.amazon.minerva.client.common.internal.fireos.FireOSMetricEventAdapter;
import com.amazon.minerva.client.common.internal.util.DevicePlatformIdentifierUtil;

/* JADX INFO: loaded from: classes3.dex */
public class MetricEvent {
    private DevicePlatformIdentifierUtil mDevicePlatformIdentifierUtil;
    private MetricEventAdapter mMetricEventDelegate;

    public MetricEvent(String str, String str2) {
        DevicePlatformIdentifierUtil devicePlatformIdentifierUtil = DevicePlatformIdentifierUtil.getInstance();
        this.mDevicePlatformIdentifierUtil = devicePlatformIdentifierUtil;
        if (devicePlatformIdentifierUtil.isDevicePlatformFireOS()) {
            this.mMetricEventDelegate = new FireOSMetricEventAdapter(str, str2);
        } else {
            this.mMetricEventDelegate = new AndroidMetricEventAdapter(str, str2);
        }
    }

    protected MetricEvent() {
        this.mDevicePlatformIdentifierUtil = DevicePlatformIdentifierUtil.getInstance();
    }

    public void addDouble(String str, double d) {
        this.mMetricEventDelegate.addDouble(str, d);
    }

    public void addString(String str, String str2) {
        this.mMetricEventDelegate.addString(str, str2);
    }

    public MetricEventAdapter getMetricEventDelegate() {
        return this.mMetricEventDelegate;
    }
}
