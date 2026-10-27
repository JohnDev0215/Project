package com.amazon.client.metrics.common;

import android.content.Context;
import com.amazon.client.metrics.common.internal.android.AndroidMetricsFactory;
import com.amazon.client.metrics.common.internal.fireos.FireOSMetricsFactory;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import com.amazon.client.metrics.common.transport.OAuthHelper;
import com.amazon.client.metrics.common.transport.internal.android.AndroidOAuthHelperImpl;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidMetricsFactoryImpl {
    private static AndroidMetricsFactoryImpl sAndroidMetricsFactoryImpl;
    private static MetricsFactory sMetricsFactory;

    private AndroidMetricsFactoryImpl() {
    }

    public static synchronized MetricsFactory getInstance(Context context) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        if (sAndroidMetricsFactoryImpl == null) {
            sAndroidMetricsFactoryImpl = new AndroidMetricsFactoryImpl();
            sMetricsFactory = getMetricsFactory(context);
        }
        return sMetricsFactory;
    }

    private static MetricsFactory getMetricsFactory(Context context) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            return FireOSMetricsFactory.getInstance(context);
        }
        return AndroidMetricsFactory.getInstance(context);
    }

    public static synchronized void setDeviceType(Context context, String str) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setDeviceType(context, str);
        }
    }

    public static synchronized void setDeviceId(Context context, String str) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setDeviceId(context, str);
        }
    }

    public static synchronized void setOAuthHelper(Context context, OAuthHelper oAuthHelper) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl.setOAuthHelper(context, new AndroidOAuthHelperImpl(oAuthHelper));
        }
    }
}
