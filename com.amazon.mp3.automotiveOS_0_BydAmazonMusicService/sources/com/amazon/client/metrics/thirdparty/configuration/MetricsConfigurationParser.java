package com.amazon.client.metrics.thirdparty.configuration;

import android.os.Build;
import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsConfigurationParser {
    private static final DPLogger log = new DPLogger("MetricsConfigurationParser");

    public MetricsConfiguration parseConfiguration(JSONObject jSONObject, boolean z) throws MetricsConfigurationException {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() != 0) {
                    JSONObject metricsConfigurationForDomain = getMetricsConfigurationForDomain(jSONObject, z);
                    NetworkConfiguration networkConfiguration = getNetworkConfiguration(metricsConfigurationForDomain);
                    BatchQueueConfiguration batchQueueConfiguration = getBatchQueueConfiguration(metricsConfigurationForDomain);
                    CodecConfiguration codecConfiguration = getCodecConfiguration(metricsConfigurationForDomain);
                    HttpConfiguration httpConfiguration = getHttpConfiguration(metricsConfigurationForDomain);
                    Map<PriorityChannelPair, BatchPipelineConfiguration> batchPipelineConfigurationMap = getBatchPipelineConfigurationMap(metricsConfigurationForDomain);
                    if (batchQueueConfiguration != null) {
                        return new MetricsConfiguration(networkConfiguration, batchQueueConfiguration, codecConfiguration, httpConfiguration, batchPipelineConfigurationMap);
                    }
                    return new MetricsConfiguration(networkConfiguration, codecConfiguration, httpConfiguration, batchPipelineConfigurationMap);
                }
            } catch (JSONException e) {
                throw new MetricsConfigurationException("An JSONException was throw was parsing the metrics configuration file", e);
            }
        }
        throw new IllegalArgumentException("Unable to parse metrics configuration. It can not be null or empty");
    }

    public JSONObject getMetricsConfigurationForDomain(JSONObject jSONObject, boolean z) throws JSONException {
        String name = MetricsConfiguration.Domain.DEVO.getName();
        String name2 = MetricsConfiguration.Domain.MASTER.getName();
        String name3 = MetricsConfiguration.Domain.PROD.getName();
        if (jSONObject.has(name)) {
            String str = Build.TYPE;
            if (str.equals("eng") || str.equals("userdebug") || z) {
                log.info("MetricsConfigurationParser", "Picking configuration", "Domain", "devo", "Build", str, "isDebuggable", Boolean.valueOf(z));
                return jSONObject.getJSONObject(name);
            }
        }
        if (jSONObject.has(name2)) {
            String str2 = Build.TYPE;
            if (str2.equals("user") && Build.TAGS.equals("test-keys")) {
                log.info("MetricsConfigurationParser", "Picking configuration", "Domain", "master", "Build", str2, "isDebuggable", Boolean.valueOf(z));
                return jSONObject.getJSONObject(name2);
            }
        }
        log.info("MetricsConfigurationParser", "Picking configuration", "Domain", "prod", "Build", Build.TYPE, "isDebuggable", Boolean.valueOf(z));
        return jSONObject.getJSONObject(name3);
    }

    public NetworkConfiguration getNetworkConfiguration(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        TransportType transportTypeFromString = TransportType.fromString(jSONObject.getString("TransportType"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("NetworkTypes");
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(NetworkType.fromString(jSONArray.getString(i)));
        }
        return new MetricsNetworkConfiguration(transportTypeFromString, hashSet);
    }

    public BatchQueueConfiguration getBatchQueueConfiguration(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("BatchQueueConfiguration");
        if (jSONObjectOptJSONObject != null) {
            return new BatchQueueConfiguration(BatchQueueType.fromString(jSONObjectOptJSONObject.getString("Type")), jSONObjectOptJSONObject.getString("DirectoryPrefix"));
        }
        return null;
    }

    public CodecConfiguration getCodecConfiguration(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("CodecConfiguration");
        return new CodecConfiguration(CodecType.fromString(jSONObject2.getString("Type")), jSONObject2.getString(JsonDocumentFields.VERSION));
    }

    public HttpConfiguration getHttpConfiguration(JSONObject jSONObject) throws MetricsConfigurationException {
        JSONObject jSONObjectOptJSONObject = jSONObject == null ? null : jSONObject.optJSONObject("HttpConfiguration");
        HttpConfiguration defaultHttpConfiguration = getDefaultHttpConfiguration();
        if (jSONObject == null || jSONObjectOptJSONObject == null) {
            return defaultHttpConfiguration;
        }
        return new HttpConfiguration(HttpRequestSignerType.fromString(jSONObjectOptJSONObject.optString("RequestSignerType", defaultHttpConfiguration.getHttpRequestSignerType().getName())), jSONObjectOptJSONObject.optString("UrlEndpoint", defaultHttpConfiguration.getUrlEndpoint()), jSONObjectOptJSONObject.optString("StaticCredentialUrlEndpoint", defaultHttpConfiguration.getStaticCredentialUrlEndpoint()), jSONObjectOptJSONObject.optInt("ConnectTimeout", defaultHttpConfiguration.getConnectTimeout()), jSONObjectOptJSONObject.optInt("ReadTimeout", defaultHttpConfiguration.getReadTimeout()), jSONObjectOptJSONObject.optLong("WakeLockTimeout", defaultHttpConfiguration.getWakeLockTimeout()));
    }

    private HttpConfiguration getDefaultHttpConfiguration() throws MetricsConfigurationException {
        return new HttpConfiguration(HttpRequestSignerType.OAUTH, Build.TYPE.equals("userdebug") ? "https://dp-mont.integ.amazon.com:443" : "https://device-metrics-us.amazon.com:443", "https://device-metrics-us-2.amazon.com:443");
    }

    public Map<PriorityChannelPair, BatchPipelineConfiguration> getBatchPipelineConfigurationMap(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        JSONObject jSONObject2 = jSONObject;
        HashMap map = new HashMap();
        map.put("NormalPriority", Priority.NORMAL);
        map.put("HighPriority", Priority.HIGH);
        map.put("CriticalPriority", Priority.CRITICAL);
        HashMap map2 = new HashMap();
        JSONObject jSONObject3 = jSONObject2.getJSONObject("PipelineConfiguration");
        HashSet hashSet = new HashSet();
        if (jSONObject3.optJSONObject("ReservedForLocationServicePriority") != null) {
            hashSet.add(Channel.LOCATION);
        }
        if (jSONObject3.optJSONObject("ReservedForNonAnonymousMetricsPriority") != null) {
            hashSet.add(Channel.NON_ANONYMOUS);
        }
        for (Map.Entry entry : map.entrySet()) {
            JSONObject jSONObjectOptJSONObject = jSONObject3.optJSONObject((String) entry.getKey());
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("BatchQueueType");
                String strOptString2 = jSONObjectOptJSONObject.optString("BatchQueueDirectoryPrefix");
                JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("BatchQueueConfiguration");
                if (strOptString.isEmpty()) {
                    if (jSONObjectOptJSONObject2 == null) {
                        throw new JSONException("Missing Batch Queue type for priority: " + entry.getValue());
                    }
                    strOptString = jSONObjectOptJSONObject2.getString("Type");
                    strOptString2 = jSONObjectOptJSONObject2.getString("DirectoryPrefix");
                }
                String str = strOptString2;
                JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("Channels");
                if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
                    hashSet.add(Channel.ANONYMOUS);
                } else {
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        hashSet.add(Channel.valueOf(jSONArrayOptJSONArray.getString(i)));
                    }
                }
                MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.fromString(strOptString), str, jSONObjectOptJSONObject.getLong("MaxBatchOpenTimeMillis"), jSONObjectOptJSONObject.getLong("CheckBatchOpenTimeMillis"), jSONObjectOptJSONObject.getInt("MaxBatchEntries"), jSONObjectOptJSONObject.getInt("MaxBatchSizeBytes"), jSONObjectOptJSONObject.getInt("MaxBatchQueueCapacityBytes"), jSONObjectOptJSONObject.getInt("MaxBatchQueueEntries"), jSONObjectOptJSONObject.getLong("ExpiryTimeMillis"), jSONObjectOptJSONObject.getLong("PurgePeriodMillis"), jSONObjectOptJSONObject.getLong("TransmissionPeriodMillis"), BatchTransmitterType.fromString(jSONObjectOptJSONObject.getString("BatchTransmitterType")));
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    map2.put(new PriorityChannelPair((Priority) entry.getValue(), (Channel) it.next()), metricsBatchPipelineConfiguration);
                }
                jSONObject2 = jSONObject;
            }
        }
        return map2;
    }
}
