package com.amazon.mp3.service.metrics;

import android.content.Context;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsRecorderMarket extends BaseMetricsRecorder {
    @Override // com.amazon.mp3.service.metrics.IMetricsRecorder
    public void tabSelectedEvent(int titleId) {
    }

    protected static class MetricEventWrapper implements BaseMetricsRecorder.MetricEventWrapper {
        protected final MetricEvent mEvent;

        public MetricEventWrapper(MetricEvent event) {
            this.mEvent = event;
        }

        public MetricEvent getEvent() {
            return this.mEvent;
        }

        @Override // com.amazon.mp3.service.metrics.BaseMetricsRecorder.MetricEventWrapper
        public void incrementCounter(String name, int value) {
            this.mEvent.incrementCounter(name, value);
        }
    }

    protected static class MetricsFactoryWrapper implements BaseMetricsRecorder.MetricsFactoryWrapper {
        protected final MetricsFactory mFactory;

        public MetricsFactoryWrapper(MetricsFactory factory) {
            this.mFactory = factory;
        }

        @Override // com.amazon.mp3.service.metrics.BaseMetricsRecorder.MetricsFactoryWrapper
        public MetricEventWrapper createMetricEvent(String programName, String sourceName) {
            return new MetricEventWrapper(this.mFactory.createMetricEvent(programName, sourceName));
        }

        @Override // com.amazon.mp3.service.metrics.BaseMetricsRecorder.MetricsFactoryWrapper
        public void record(BaseMetricsRecorder.MetricEventWrapper metric) {
            this.mFactory.record(((MetricEventWrapper) metric).getEvent());
        }
    }

    public MetricsRecorderMarket(final Context context) {
        AndroidMetricsFactoryImpl.setOAuthHelper(context, new MetricsMarketOAuthHelper());
        AndroidMetricsFactoryImpl.setDeviceType(context, AccountCredentialStorage.get(context).getDeviceType());
        AndroidMetricsFactoryImpl.setDeviceId(context, AccountCredentialStorage.get(context).getDeviceId());
        setExternalStorageMetrics(new ExternalStorageMetricsMarket(this));
        setFirstTimeUseMetrics(new FirstTimeUseMetricsMarket(context, this));
        setRecurrentUseMetrics(new RecurrentUseMetricsMarket(this));
        setLoginMetrics(new LoginMetricsMarket(this));
    }

    @Override // com.amazon.mp3.service.metrics.BaseMetricsRecorder
    protected BaseMetricsRecorder.MetricsFactoryWrapper getMetricsFactory() {
        return new MetricsFactoryWrapper(AndroidMetricsFactoryImpl.getInstance(AmazonApplication.getContext()));
    }
}
