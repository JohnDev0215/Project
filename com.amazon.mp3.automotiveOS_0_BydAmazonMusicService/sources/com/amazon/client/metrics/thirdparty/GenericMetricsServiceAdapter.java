package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import com.amazon.client.metrics.thirdparty.IMetricsService;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;
import com.amazon.device.utils.thirdparty.CustomDeviceUtil;
import com.amazon.dp.logger.DPLogger;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GenericMetricsServiceAdapter {
    private static final DPLogger log = new DPLogger("MetricsServiceAdapter");
    private static GenericMetricsServiceAdapter sGenericMetricsServiceAdapter;
    private static MetricsConfiguration sMetricsConfiguration;
    private final IMetricsService mBinder = new IMetricsService.Stub() { // from class: com.amazon.client.metrics.thirdparty.GenericMetricsServiceAdapter.2
        @Override // com.amazon.client.metrics.thirdparty.IMetricsService
        public boolean getRecordMetricsSetting() {
            return true;
        }

        @Override // com.amazon.client.metrics.thirdparty.IMetricsService
        public void record(int i, int i2, String str, String str2, long j, List<DataPointEnvelope> list) throws RemoteException {
            GenericMetricsServiceAdapter.log.verbose("record", "[ " + str + " , " + str2 + " ]", new Object[0]);
            Handler handler = GenericMetricsServiceAdapter.this.getHandler();
            Message messageObtainMessage = handler.obtainMessage();
            messageObtainMessage.arg1 = i;
            messageObtainMessage.arg2 = i2;
            messageObtainMessage.obj = new MetricEntry(j, str, str2, DataPointEnvelope.convertFromEnvelopes(list));
            handler.sendMessage(messageObtainMessage);
        }
    };
    private final Context mContext;
    private final CustomDeviceUtil mDeviceUtil;
    private final Handler mHandler;
    private final HandlerThread mMainThread;
    private final MetricsService mMetricsService;
    private final BaseMetricsServiceFactory mMetricsServiceFactory;

    private GenericMetricsServiceAdapter(Context context) {
        log.debug("initialize", "initialize(context) - Metrics service", new Object[0]);
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("MetricsService");
        this.mMainThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.amazon.client.metrics.thirdparty.GenericMetricsServiceAdapter.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                return GenericMetricsServiceAdapter.this.handleMessageForService(message);
            }
        });
        try {
            CustomDeviceUtil customDeviceUtil = new CustomDeviceUtil(context);
            this.mDeviceUtil = customDeviceUtil;
            BaseMetricsServiceFactory baseMetricsServiceFactory = new BaseMetricsServiceFactory(context, customDeviceUtil, sMetricsConfiguration);
            this.mMetricsServiceFactory = baseMetricsServiceFactory;
            this.mMetricsService = baseMetricsServiceFactory.createMetricsService();
        } catch (MetricsConfigurationException e) {
            shutdown();
            throw new RuntimeException(e);
        }
    }

    private void shutdown() {
        BaseMetricsServiceFactory baseMetricsServiceFactory = this.mMetricsServiceFactory;
        if (baseMetricsServiceFactory != null) {
            baseMetricsServiceFactory.shutdown();
        }
        MetricsService metricsService = this.mMetricsService;
        if (metricsService != null) {
            metricsService.shutdown();
        }
        HandlerThread handlerThread = this.mMainThread;
        if (handlerThread == null) {
            log.debug("shutdown", "HandlerThread is null - nothing to do in shutdown.", new Object[0]);
            return;
        }
        handlerThread.quit();
        try {
            this.mMainThread.join(120000L);
            log.debug("shutdown", "(super) Shutting down...", new Object[0]);
        } catch (InterruptedException e) {
            throw new RuntimeException("System service shutdown failed", e);
        }
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    protected boolean handleMessageForService(Message message) {
        Object obj = message.obj;
        if (obj != null && (obj instanceof MetricEntry) && this.mMetricsService != null) {
            this.mMetricsService.record((MetricEntry) message.obj, Priority.fromInt(message.arg1), Channel.fromInt(message.arg2));
            return true;
        }
        log.error("handleMessageForService", "Received unknown android.os.Message " + message, new Object[0]);
        return false;
    }

    public static GenericMetricsServiceAdapter getInstance(Context context) {
        if (sGenericMetricsServiceAdapter == null) {
            sGenericMetricsServiceAdapter = new GenericMetricsServiceAdapter(context);
        }
        return sGenericMetricsServiceAdapter;
    }

    public void setOAuthHelper(OAuthHelper oAuthHelper) {
        this.mMetricsServiceFactory.setOAuthHelper(oAuthHelper);
    }

    public void setDeviceType(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("deviceType must not be null or empty");
        }
        this.mDeviceUtil.setDeviceType(str);
    }

    public void setDeviceSerialNumber(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("deviceId must not be null or empty");
        }
        this.mDeviceUtil.setDeviceSerialNumber(str);
    }

    public IMetricsService getMetricsService() {
        return sGenericMetricsServiceAdapter.mBinder;
    }
}
