package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidMetricsFactoryImpl extends AbstractMetricsFactoryImpl {
    private final Context mContext;

    @Override // com.amazon.client.metrics.thirdparty.BaseMetricsFactoryImpl
    protected boolean shouldRecordMetrics() {
        return true;
    }

    public static synchronized void setDeviceType(Context context, String str) {
        GenericMetricsServiceAdapter.getInstance(context).setDeviceType(str);
    }

    public static synchronized void setDeviceId(Context context, String str) {
        GenericMetricsServiceAdapter.getInstance(context).setDeviceSerialNumber(str);
    }

    public static synchronized void setOAuthHelper(Context context, OAuthHelper oAuthHelper) {
        GenericMetricsServiceAdapter.getInstance(context).setOAuthHelper(oAuthHelper);
    }

    public static synchronized MetricsFactory getInstance(Context context) {
        if (AbstractMetricsFactoryImpl.sMetricsFactory == null) {
            AbstractMetricsFactoryImpl.sMetricsFactory = new AndroidMetricsFactoryImpl(context);
        }
        return AbstractMetricsFactoryImpl.sMetricsFactory;
    }

    @Override // com.amazon.client.metrics.thirdparty.AbstractMetricsFactoryImpl
    protected IMetricsService getService() {
        if (AbstractMetricsFactoryImpl.sMetricsService == null) {
            AbstractMetricsFactoryImpl.sMetricsService = GenericMetricsServiceAdapter.getInstance(this.mContext).getMetricsService();
        }
        return AbstractMetricsFactoryImpl.sMetricsService;
    }

    private AndroidMetricsFactoryImpl(Context context) {
        this.mContext = context;
    }
}
