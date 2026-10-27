package com.amazon.minerva.client.thirdparty;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.kpi.PeriodicKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.DataPointEnvelope;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEventBuilder;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazon.minerva.client.thirdparty.utils.PredefinedKeyUtil;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MinervaServiceAndroidAdapter {
    private static final String TAG = "MinervaServiceAndroidAdapter";
    private static volatile MinervaServiceAndroidAdapter sMinervaServiceAndroidAdapter;
    private final Context mContext;
    private CustomDeviceUtil mCustomDeviceUtil;
    private final Handler mHandler;
    private final HandlerThread mMainThread;
    private MinervaServiceManager mMinervaServiceManager;
    private PeriodicKPIReporter mPeriodicKPIReporter;

    @TargetApi(3)
    private MinervaServiceAndroidAdapter(Context context, boolean z) {
        Log.i(TAG, "Initialize(context) -- MinervaServiceAndroidAdapter");
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("MetricsServiceAndroid3rdParty");
        this.mMainThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                return MinervaServiceAndroidAdapter.this.handleMessageForService(message);
            }
        });
        CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
        this.mCustomDeviceUtil = customDeviceUtil;
        if (!customDeviceUtil.isInitialized()) {
            this.mCustomDeviceUtil.initialize(context);
        }
        MinervaServiceManager minervaServiceManager = MinervaServiceManager.getInstance();
        this.mMinervaServiceManager = minervaServiceManager;
        if (!minervaServiceManager.isInitialized()) {
            this.mMinervaServiceManager.initialize(context, new PredefinedKeyUtil(context, this.mCustomDeviceUtil), z);
        }
        this.mPeriodicKPIReporter = new PeriodicKPIReporter();
    }

    public static MinervaServiceAndroidAdapter getInstance(Context context, boolean z) {
        if (sMinervaServiceAndroidAdapter == null) {
            synchronized (MinervaServiceAndroidAdapter.class) {
                if (sMinervaServiceAndroidAdapter == null) {
                    sMinervaServiceAndroidAdapter = new MinervaServiceAndroidAdapter(context, z);
                    sMinervaServiceAndroidAdapter.mPeriodicKPIReporter.setMinervaServiceAndroidAdapter(sMinervaServiceAndroidAdapter);
                }
            }
        }
        return sMinervaServiceAndroidAdapter;
    }

    public MinervaServiceManager getMinervaServiceManager() {
        return this.mMinervaServiceManager;
    }

    public PeriodicKPIReporter getPeriodicKPIReporter() {
        return this.mPeriodicKPIReporter;
    }

    public void record(String str, String str2, String str3, String str4, long j, int i, List<DataPointEnvelope> list) {
        IonMetricEvent ionMetricEventBuild;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("*****record, region:");
        stringBuffer.append(str);
        stringBuffer.append(", metricGroupId:");
        stringBuffer.append(str2);
        stringBuffer.append(", metricSchemaId:");
        stringBuffer.append(str3);
        stringBuffer.append(", metricEventId:");
        stringBuffer.append(str4);
        stringBuffer.append(", utcTimestamp:");
        stringBuffer.append(j);
        stringBuffer.append(", localOffsetMinutes:");
        stringBuffer.append(i);
        stringBuffer.append(", listOfDataPointEnvelopes size:");
        stringBuffer.append(list.size());
        Log.d(TAG, stringBuffer.toString());
        try {
            ionMetricEventBuild = new IonMetricEventBuilder().withRegion(str).withMetricGroupId(str2).withSchemaId(str3).withMetricEventId(str4).withUtcTimestamp(j).withLocalOffsetMinutes(i).withDataPoints(DataPointEnvelope.convertFromEnvelopes(list)).build();
        } catch (Exception e) {
            Log.e(TAG, "record: failed to create IonMetricEvent from IPC parameters", e);
            ionMetricEventBuild = null;
        }
        if (ionMetricEventBuild == null) {
            return;
        }
        Log.d(TAG, "metricEvent:" + ionMetricEventBuild.toString());
        Handler handler = getHandler();
        Message messageObtainMessage = handler.obtainMessage();
        messageObtainMessage.obj = ionMetricEventBuild;
        handler.sendMessage(messageObtainMessage);
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    protected boolean handleMessageForService(Message message) {
        MinervaServiceManager minervaServiceManager;
        String str = TAG;
        Log.d(str, "handleMessageForService.");
        Object obj = message.obj;
        if (obj != null && (obj instanceof IonMetricEvent) && (minervaServiceManager = this.mMinervaServiceManager) != null) {
            minervaServiceManager.record((IonMetricEvent) obj);
            return true;
        }
        Log.e(str, "handleMessageForService received unknown android.os.Message " + message);
        return false;
    }

    public void shutdown() {
        MinervaServiceManager minervaServiceManager = this.mMinervaServiceManager;
        if (minervaServiceManager != null) {
            minervaServiceManager.shutdown();
        }
        HandlerThread handlerThread = this.mMainThread;
        if (handlerThread == null) {
            Log.w(TAG, "Shutdown: HandlerThread is null - nothing to do in shutdown.");
            return;
        }
        handlerThread.quit();
        try {
            this.mMainThread.join(120000L);
            Log.w(TAG, "Shutdown: (super) Shutting down...");
        } catch (InterruptedException e) {
            throw new RuntimeException("System service shutdown failed", e);
        }
    }
}
