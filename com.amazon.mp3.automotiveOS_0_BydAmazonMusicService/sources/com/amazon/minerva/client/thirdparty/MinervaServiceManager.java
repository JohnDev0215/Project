package com.amazon.minerva.client.thirdparty;

import android.content.Context;
import android.util.Log;
import com.amazon.ion.IonStruct;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.client.thirdparty.configuration.ConfigurationManager;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.storage.BatchCreatorManager;
import com.amazon.minerva.client.thirdparty.storage.KPIBatchCreator;
import com.amazon.minerva.client.thirdparty.storage.StorageManager;
import com.amazon.minerva.client.thirdparty.transport.MetricsTransmissionManager;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import com.amazon.minerva.client.thirdparty.utils.PredefinedKeyUtil;
import com.amazon.minerva.identifiers.schemaid.SchemaId;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.AttributeEnumV2;
import com.amazon.minerva.identifiers.schemaid.attribute.attributes.VersionedAttributes;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
public class MinervaServiceManager {
    private static final String TAG = "MinervaServiceManager";
    private AtomicBoolean initialized = new AtomicBoolean(false);
    private BatchCreatorManager mBatchCreatorManager;
    private ConfigurationManager mConfigurationManager;
    private Context mContext;
    private CustomDeviceUtil mCustomDeviceUtil;
    private KPIBatchCreator mKPIBatchCreator;
    private MetricsTransmissionManager mMetricsTransmissionManager;
    private PredefinedKeyUtil mPredefinedKeyUtil;
    private ServiceKPIReporter mServiceKPIReporter;
    private StorageManager mStorageManager;
    private static final MinervaServiceManager INSTANCE = new MinervaServiceManager();
    private static final ValueFactory VALUE_FACTORY = IonSystemBuilder.standard().build();

    private MinervaServiceManager() {
    }

    public static MinervaServiceManager getInstance() {
        return INSTANCE;
    }

    public boolean isInitialized() {
        return this.initialized.get();
    }

    public synchronized void initialize(Context context, PredefinedKeyUtil predefinedKeyUtil, boolean z) {
        if (!this.initialized.get()) {
            String str = TAG;
            Log.i(str, "Initializing MinervaServiceManager");
            if (context == null) {
                Log.e(str, "Context cannot be null.");
                return;
            }
            if (predefinedKeyUtil == null) {
                Log.e(str, "PredefinedKeyUtil cannot be null.");
                return;
            }
            this.mContext = context;
            CustomDeviceUtil customDeviceUtil = CustomDeviceUtil.getInstance();
            this.mCustomDeviceUtil = customDeviceUtil;
            if (!customDeviceUtil.isInitialized()) {
                this.mCustomDeviceUtil.initialize(context);
            }
            this.mPredefinedKeyUtil = predefinedKeyUtil;
            this.mConfigurationManager = new ConfigurationManager(context);
            this.mServiceKPIReporter = new ServiceKPIReporter(this.mConfigurationManager.getMetricsConfigurationHelper());
            File dir = context.getDir("MINERVA", 0);
            Log.i(str, "Batch DIR:" + dir.getAbsolutePath());
            this.mStorageManager = new StorageManager(this.mConfigurationManager.getMetricsConfigurationHelper(), this.mServiceKPIReporter, dir);
            this.mKPIBatchCreator = new KPIBatchCreator(context, this.mServiceKPIReporter, this.mConfigurationManager.getMetricsConfigurationHelper(), dir, this.mCustomDeviceUtil);
            this.mBatchCreatorManager = new BatchCreatorManager(this.mConfigurationManager.getMetricsConfigurationHelper(), this.mStorageManager);
            MetricsTransmissionManager metricsTransmissionManager = new MetricsTransmissionManager(context, this.mConfigurationManager.getMetricsConfigurationHelper(), this.mServiceKPIReporter, this.mStorageManager, dir, this.mKPIBatchCreator);
            this.mMetricsTransmissionManager = metricsTransmissionManager;
            if (z) {
                metricsTransmissionManager.transmitMetricBatches();
            }
            Log.i(str, "Initializing Arcus periodical sync-up.");
            this.mConfigurationManager.initializeWithArcus();
            this.initialized.set(true);
            Log.i(str, "Finished initializing MinervaServiceManager");
        } else {
            Log.w(TAG, "Tried to initialize MinervaServiceManager after it has been initialized");
        }
    }

    public ConfigurationManager getConfigurationManager() {
        return this.mConfigurationManager;
    }

    public void record(IonMetricEvent ionMetricEvent) {
        String str = TAG;
        Log.d(str, ionMetricEvent.toString());
        this.mServiceKPIReporter.report(KPIMetric.IPC.getMetricName(), "aminerva", 1L);
        try {
            if (isKPIMetric(ionMetricEvent)) {
                this.mKPIBatchCreator.recordClientKPIMetricEvent(ionMetricEvent);
                this.mServiceKPIReporter.report(KPIMetric.RECORD_ON_DISK.getMetricName(), "aminerva", 1L);
                return;
            }
            VersionedAttributes versionedAttributes = new SchemaId(ionMetricEvent.getMetricSchemaId().stringValue()).getVersionedAttributes();
            if (!versionedAttributes.getBooleanValue(AttributeEnumV2.ALLOW_CHILD_PROFILE).booleanValue() && CustomDeviceUtil.getInstance().getChildProfileVerifier().isChildProfile()) {
                Log.w(str, String.format("Metric event was dropped due to Child profile compliance check. metricGroupId: %s, schemaId: %s", ionMetricEvent.getMetricGroupId(), ionMetricEvent.getMetricSchemaId()));
                this.mServiceKPIReporter.report(KPIMetric.CHILD_PROFILE.getMetricName(), ionMetricEvent.getMetricGroupId().stringValue(), 1L);
            } else if (!versionedAttributes.getBooleanValue(AttributeEnumV2.EXEMPT_FROM_OPT_OUT).booleanValue() && !CustomDeviceUtil.getInstance().getUserControlVerifier().isUsageCollectionEnabled()) {
                Log.w(str, String.format("Metric event was dropped due to user control compliance check. metricGroupId: %s, schemaId: %s", ionMetricEvent.getMetricGroupId(), ionMetricEvent.getMetricSchemaId()));
                this.mServiceKPIReporter.report(KPIMetric.USER_CONTROL.getMetricName(), ionMetricEvent.getMetricGroupId().stringValue(), 1L);
            } else {
                addPredefinedValues(ionMetricEvent);
                this.mBatchCreatorManager.addMetricEvent(ionMetricEvent);
                this.mServiceKPIReporter.report(KPIMetric.RECORD_ON_DISK.getMetricName(), "aminerva", 1L);
            }
        } catch (Exception e) {
            Log.e(TAG, String.format("Can't record metric event. metricGroupId: %s, schemaId: %s", ionMetricEvent.getMetricGroupId(), ionMetricEvent.getMetricSchemaId()) + " due to Exception: " + e);
        }
    }

    public void shutdown() {
        this.mMetricsTransmissionManager.shutdown();
        this.mStorageManager.shutdown();
        this.mBatchCreatorManager.shutdown();
    }

    private boolean isKPIMetric(IonMetricEvent ionMetricEvent) {
        if (!ionMetricEvent.getMetricGroupId().stringValue().equals("aminerva")) {
            return false;
        }
        return new SchemaId("ckpi/2/00f30233").getIdentifier().equals(new SchemaId(ionMetricEvent.getMetricSchemaId().stringValue()).getIdentifier());
    }

    private void addPredefinedValues(IonMetricEvent ionMetricEvent) {
        IonStruct keyValuePairs = ionMetricEvent.getKeyValuePairs();
        for (Predefined predefined : Predefined.values()) {
            if (keyValuePairs.containsKey(predefined.getKey())) {
                String predefinedValue = this.mPredefinedKeyUtil.getPredefinedValue(predefined, ionMetricEvent.getMetricSchemaId().stringValue());
                Log.d(TAG, "Predefined key: " + predefined.getKey() + " Predefined value: " + predefinedValue);
                keyValuePairs.put(predefined.getKey(), VALUE_FACTORY.newSymbol(predefinedValue));
            }
        }
    }
}
