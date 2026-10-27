package com.amazon.minerva.client.thirdparty.storage;

import android.content.Context;
import android.util.Log;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.system.IonSystemBuilder;
import com.amazon.minerva.client.thirdparty.api.Predefined;
import com.amazon.minerva.client.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.minerva.client.thirdparty.kpi.ServiceKPIReporter;
import com.amazon.minerva.client.thirdparty.metric.IonMetricEvent;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.serializer.MetricBatchSerializer;
import com.amazon.minerva.client.thirdparty.utils.CustomDeviceUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class KPIBatchCreator extends AbstractBatchCreatorScheduler {
    private final Context mContext;
    private final CustomDeviceUtil mCustomDeviceUtil;
    private final File mDirOfKPI;
    Map<String, HashMap<String, Long>> mKpiMap;
    private final MetricBatchSerializer mMetricBatchSerializer;
    private final MetricsConfigurationHelper mMetricsConfigurationHelper;
    private final ServiceKPIReporter mServiceKPIReporter;
    Object uploadDoneSignal;
    static final IonSystem ION_SYSTEM = IonSystemBuilder.standard().build();
    static final ValueFactory valueFactory = IonSystemBuilder.standard().build();
    private static final String TAG = KPIBatchCreator.class.getSimpleName();
    public static volatile boolean isUploading = false;

    public KPIBatchCreator(Context context, ServiceKPIReporter serviceKPIReporter, MetricsConfigurationHelper metricsConfigurationHelper, File file, CustomDeviceUtil customDeviceUtil) {
        super(metricsConfigurationHelper, "KPI");
        this.mKpiMap = new ConcurrentHashMap();
        this.uploadDoneSignal = new Object();
        Objects.requireNonNull(serviceKPIReporter, "serviceKPIReporter cannot be null.");
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("dirOfMinerva must be a valid directory.");
        }
        File file2 = new File(file.getAbsolutePath() + File.separator + "KPI");
        this.mDirOfKPI = file2;
        if (!file2.isDirectory()) {
            file2.mkdir();
        }
        this.mContext = context;
        this.mCustomDeviceUtil = customDeviceUtil;
        this.mServiceKPIReporter = serviceKPIReporter;
        this.mMetricsConfigurationHelper = metricsConfigurationHelper;
        this.mMetricBatchSerializer = metricsConfigurationHelper.getUploadConfiguration().createMetricBatchSerializer();
        scheduleBatchOpenTimeWatcher();
    }

    static void mergeMapForAllMetricGroups(Map<String, HashMap<String, Long>> map, Map<String, HashMap<String, Long>> map2) {
        for (Map.Entry<String, HashMap<String, Long>> entry : map.entrySet()) {
            String key = entry.getKey();
            HashMap<String, Long> value = entry.getValue();
            HashMap<String, Long> map3 = map2.get(key);
            if (map3 == null) {
                map3 = new HashMap<>();
                map2.put(key, map3);
            }
            mergeMapForSingleMetricGroup(value, map3);
        }
    }

    static void mergeMapForSingleMetricGroup(HashMap<String, Long> map, HashMap<String, Long> map2) {
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            String key = entry.getKey();
            long jLongValue = entry.getValue().longValue();
            if (map2.containsKey(key)) {
                jLongValue += map2.get(key).longValue();
            }
            map2.put(key, Long.valueOf(jLongValue));
        }
    }

    static List<IonMetricEvent> convertKpiMapToIonMetricEvents(Map<String, HashMap<String, Long>> map, String str) {
        ArrayList arrayList = new ArrayList();
        long epochMillis = Timestamp.now().getEpochMillis();
        int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(r1.getTimeZone().getOffset(epochMillis));
        for (Map.Entry<String, HashMap<String, Long>> entry : map.entrySet()) {
            IonSystem ionSystem = ION_SYSTEM;
            IonSymbol ionSymbolNewSymbol = ionSystem.newSymbol("aminerva");
            IonSymbol ionSymbolNewSymbol2 = ionSystem.newSymbol("ckpi/2/00f30233");
            com.amazon.ion.Timestamp timestampForMillis = com.amazon.ion.Timestamp.forMillis(epochMillis, Integer.valueOf(minutes));
            String key = entry.getKey();
            HashMap<String, Long> value = entry.getValue();
            IonStruct ionStructNewEmptyStruct = ionSystem.newEmptyStruct();
            ValueFactory valueFactory2 = valueFactory;
            ionStructNewEmptyStruct.add("customerMetricGroupId", valueFactory2.newSymbol(key));
            ionStructNewEmptyStruct.add(Predefined.DEVICE_TYPE.getKey(), valueFactory2.newSymbol(str));
            for (Map.Entry<String, Long> entry2 : value.entrySet()) {
                ionStructNewEmptyStruct.add(entry2.getKey(), valueFactory.newInt(entry2.getValue()));
            }
            IonSystem ionSystem2 = ION_SYSTEM;
            arrayList.add(new IonMetricEvent(ionSymbolNewSymbol, ionSymbolNewSymbol2, ionSystem2.newTimestamp(timestampForMillis), ionSystem2.newString(UUID.randomUUID().toString()), ionStructNewEmptyStruct));
        }
        return arrayList;
    }

    static Map<String, HashMap<String, Long>> convertIonMetricEventsToMapMetricGroupIdToKpiValues(List<IonMetricEvent> list) {
        HashMap map = new HashMap();
        Iterator<IonMetricEvent> it = list.iterator();
        while (it.hasNext()) {
            IonStruct keyValuePairs = it.next().getKeyValuePairs();
            String strStringValue = ((IonSymbol) keyValuePairs.get("customerMetricGroupId")).stringValue();
            HashMap map2 = (HashMap) map.get(strStringValue);
            if (map2 == null) {
                map2 = new HashMap();
                map.put(strStringValue, map2);
            }
            fillKpiValueMapWithKeyValuePair(keyValuePairs, map2);
        }
        return map;
    }

    static void fillKpiValueMapWithKeyValuePair(IonStruct ionStruct, HashMap<String, Long> map) {
        for (IonValue ionValue : ionStruct) {
            if (ionValue instanceof IonInt) {
                String fieldName = ionValue.getFieldName();
                long jLongValue = ((IonInt) ionValue).longValue();
                if (map.containsKey(fieldName)) {
                    jLongValue += map.get(fieldName).longValue();
                }
                map.put(fieldName, Long.valueOf(jLongValue));
            }
        }
    }

    public synchronized void recordClientKPIMetricEvent(IonMetricEvent ionMetricEvent) {
        IonStruct keyValuePairs = ionMetricEvent.getKeyValuePairs();
        String strStringValue = ((IonSymbol) keyValuePairs.get("customerMetricGroupId")).stringValue();
        HashMap<String, Long> map = this.mKpiMap.get(strStringValue);
        if (map == null) {
            map = new HashMap<>();
            this.mKpiMap.put(strStringValue, map);
        }
        fillKpiValueMapWithKeyValuePair(keyValuePairs, map);
    }

    synchronized void pullServiceKPIMap() {
        Map<String, HashMap<String, Long>> kpiMap = this.mServiceKPIReporter.getKpiMap();
        mergeMapForAllMetricGroups(kpiMap, this.mKpiMap);
        kpiMap.clear();
    }

    @Override // com.amazon.minerva.client.thirdparty.storage.AbstractBatchCreatorScheduler
    protected void checkBatchOpenTimeAndEnqueueIfReady() {
        if (maxBatchOpenTimeReached()) {
            if (this.mKpiMap.size() > 0 || this.mServiceKPIReporter.getKpiMap().size() > 0) {
                synchronized (this.uploadDoneSignal) {
                    while (isUploading) {
                        try {
                            this.uploadDoneSignal.wait();
                        } catch (InterruptedException e) {
                            Log.e(TAG, "Exception in purgeExpiredBatches.", e);
                            return;
                        }
                    }
                }
                enqueueBatchForTransmission();
            }
        }
    }

    synchronized void enqueueBatchForTransmission() {
        FileOutputStream fileOutputStream;
        Log.i(TAG, "enqueueKPIBatchForTransmission");
        pullServiceKPIMap();
        Map<String, HashMap<String, Long>> kPIBatchFile = readKPIBatchFile();
        if (kPIBatchFile != null) {
            mergeMapForAllMetricGroups(kPIBatchFile, this.mKpiMap);
        }
        byte[] bArrSerialize = null;
        try {
            bArrSerialize = this.mMetricBatchSerializer.serialize(convertKpiMapToIonMetricEvents(this.mKpiMap, this.mCustomDeviceUtil.getDeviceType() == null ? "UNKNOWN" : this.mCustomDeviceUtil.getDeviceType()));
        } catch (IOException e) {
            Log.e(TAG, "An error occurs when converting KPI metric events to Ion Binary.", e);
        }
        if (bArrSerialize == null) {
            return;
        }
        try {
            fileOutputStream = new FileOutputStream(getKpiFilePath(), false);
        } catch (IOException e2) {
            Log.e(TAG, "An error occurs when writing KPI metrics to disk.", e2);
        }
        try {
            fileOutputStream.write(bArrSerialize);
            fileOutputStream.close();
            this.mKpiMap.clear();
            this.mTimeSinceLastPublish.set(System.currentTimeMillis());
        } finally {
        }
    }

    Map<String, HashMap<String, Long>> readKPIBatchFile() {
        File file = new File(getKpiFilePath());
        Map<String, HashMap<String, Long>> mapConvertIonMetricEventsToMapMetricGroupIdToKpiValues = null;
        if (file.exists() && file.length() > 0) {
            byte[] bArr = new byte[(int) file.length()];
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    fileInputStream.read(bArr);
                    mapConvertIonMetricEventsToMapMetricGroupIdToKpiValues = convertIonMetricEventsToMapMetricGroupIdToKpiValues(this.mMetricBatchSerializer.deserialize(bArr));
                    fileInputStream.close();
                } finally {
                }
            } catch (IOException e) {
                Log.e(TAG, "An error occurs when reading KPI file.", e);
            }
        }
        return mapConvertIonMetricEventsToMapMetricGroupIdToKpiValues;
    }

    private String getKpiFilePath() {
        return this.mDirOfKPI.getAbsolutePath() + File.separator + getKpiFileName();
    }

    public String getKpiFileName() {
        return "KPIBATCH_{region}_0001".replace("{region}", this.mMetricsConfigurationHelper.getUploadConfiguration().getKPIRegion());
    }

    public void sendUploadDoneSignal() {
        synchronized (this.uploadDoneSignal) {
            this.uploadDoneSignal.notifyAll();
        }
    }
}
