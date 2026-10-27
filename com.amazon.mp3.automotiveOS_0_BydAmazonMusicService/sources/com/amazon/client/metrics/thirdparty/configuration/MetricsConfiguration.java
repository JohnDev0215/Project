package com.amazon.client.metrics.thirdparty.configuration;

import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class MetricsConfiguration {
    private final BatchQueueConfiguration mBatchQueueConfiguration;
    private CodecConfiguration mCodecConfiguration;
    private final HttpConfiguration mHttpConfiguration;
    private NetworkConfiguration mNetworkConfiguration;
    private Map<PriorityChannelPair, BatchPipelineConfiguration> mPipelineConfigurationMap;
    protected static MetricsBatchPipelineConfiguration sPassThroughNormalPriorityPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, "", 10000, 500, 10, 65536, 5242880, 65536, 604800000, 86400000, 600000, BatchTransmitterType.PERIODIC);
    protected static MetricsBatchPipelineConfiguration sPassThroughHighPriorityPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.SEMI_VOLATILE, "", 1000, 500, 1, 65536, 1048576, 65536, 604800000, 86400000, 600000, BatchTransmitterType.URGENT);

    public enum Domain {
        DEVO("devo"),
        MASTER("master"),
        PROD("prod");

        private String mName;

        Domain(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    @Deprecated
    public MetricsConfiguration(NetworkConfiguration networkConfiguration, BatchQueueConfiguration batchQueueConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map map) throws MetricsConfigurationException {
        if (networkConfiguration == null) {
            throw new MetricsConfigurationException("NetworkConfiguration is null");
        }
        if (batchQueueConfiguration == null) {
            throw new MetricsConfigurationException("BatchQueueConfiguration is null");
        }
        if (codecConfiguration == null) {
            throw new MetricsConfigurationException("CodecConfiguration is null");
        }
        if (httpConfiguration == null) {
            throw new MetricsConfigurationException("HttpConfiguration is null");
        }
        if (map == null || map.isEmpty()) {
            throw new MetricsConfigurationException("PipelineConfiguration map is null or empty");
        }
        this.mNetworkConfiguration = networkConfiguration;
        this.mBatchQueueConfiguration = batchQueueConfiguration;
        this.mCodecConfiguration = codecConfiguration;
        this.mHttpConfiguration = httpConfiguration;
        Map<PriorityChannelPair, BatchPipelineConfiguration> mapSanitizeBatchPipelineConfigurationMap = sanitizeBatchPipelineConfigurationMap(map);
        this.mPipelineConfigurationMap = mapSanitizeBatchPipelineConfigurationMap;
        for (PriorityChannelPair priorityChannelPair : mapSanitizeBatchPipelineConfigurationMap.keySet()) {
            if (this.mPipelineConfigurationMap.get(priorityChannelPair).getBatchQueueType() == null) {
                this.mPipelineConfigurationMap.get(priorityChannelPair).setBatchQueueType(this.mBatchQueueConfiguration.getBatchQueueType());
                this.mPipelineConfigurationMap.get(priorityChannelPair).setDirectoryPrefix(this.mBatchQueueConfiguration.getDirectoryPrefix());
            }
        }
    }

    public MetricsConfiguration(NetworkConfiguration networkConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map map) throws MetricsConfigurationException {
        if (networkConfiguration == null) {
            throw new MetricsConfigurationException("NetworkConfiguration is null");
        }
        if (codecConfiguration == null) {
            throw new MetricsConfigurationException("CodecConfiguration is null");
        }
        if (httpConfiguration == null) {
            throw new MetricsConfigurationException("HttpConfiguration is null");
        }
        if (map == null || map.isEmpty()) {
            throw new MetricsConfigurationException("PipelineConfiguration map is null or empty");
        }
        this.mNetworkConfiguration = networkConfiguration;
        this.mCodecConfiguration = codecConfiguration;
        this.mHttpConfiguration = httpConfiguration;
        Map<PriorityChannelPair, BatchPipelineConfiguration> mapSanitizeBatchPipelineConfigurationMap = sanitizeBatchPipelineConfigurationMap(map);
        this.mPipelineConfigurationMap = mapSanitizeBatchPipelineConfigurationMap;
        BatchPipelineConfiguration batchPipelineConfiguration = mapSanitizeBatchPipelineConfigurationMap.get(new PriorityChannelPair(Priority.NORMAL, Channel.ANONYMOUS));
        if (batchPipelineConfiguration != null) {
            this.mBatchQueueConfiguration = new BatchQueueConfiguration(batchPipelineConfiguration.getBatchQueueType(), batchPipelineConfiguration.getDirectoryPrefix());
        } else {
            BatchPipelineConfiguration next = this.mPipelineConfigurationMap.values().iterator().next();
            this.mBatchQueueConfiguration = new BatchQueueConfiguration(next.getBatchQueueType(), next.getDirectoryPrefix());
        }
    }

    public CodecConfiguration getCodecConfiguration() {
        return this.mCodecConfiguration;
    }

    public NetworkConfiguration getNetworkConfiguration() {
        return this.mNetworkConfiguration;
    }

    public HttpConfiguration getHttpConfiguration() {
        return this.mHttpConfiguration;
    }

    public BatchPipelineConfiguration getPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return this.mPipelineConfigurationMap.get(priorityChannelPair);
    }

    public void setPassThroughMode() throws MetricsConfigurationException {
        this.mNetworkConfiguration = new MetricsNetworkConfiguration(TransportType.OUTPUT_STREAM, this.mNetworkConfiguration.getNetworkTypes());
        this.mCodecConfiguration = new CodecConfiguration(CodecType.STRING, "1.0");
        removeNormalAndHightPriorityConfigurations(this.mPipelineConfigurationMap);
        for (Channel channel : Channel.values()) {
            this.mPipelineConfigurationMap.put(new PriorityChannelPair(Priority.NORMAL, channel), sPassThroughNormalPriorityPipelineConfiguration);
            this.mPipelineConfigurationMap.put(new PriorityChannelPair(Priority.HIGH, channel), sPassThroughHighPriorityPipelineConfiguration);
        }
    }

    private static void removeNormalAndHightPriorityConfigurations(Map<PriorityChannelPair, BatchPipelineConfiguration> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<PriorityChannelPair, BatchPipelineConfiguration>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<PriorityChannelPair, BatchPipelineConfiguration> next = it.next();
            if (Priority.NORMAL.equals(next.getKey().getPriority()) || Priority.HIGH.equals(next.getKey().getPriority())) {
                it.remove();
            }
        }
    }

    public String getBatchQueueDirectoryName(PriorityChannelPair priorityChannelPair) {
        String str = this.mPipelineConfigurationMap.get(priorityChannelPair).getDirectoryPrefix() + priorityChannelPair.getPriority() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + priorityChannelPair.getChannel();
        if (!getNetworkConfiguration().getTransportType().equals(TransportType.OUTPUT_STREAM)) {
            return str;
        }
        return "PASSTHROUGH_" + str + "_NonTComm";
    }

    private Map<PriorityChannelPair, BatchPipelineConfiguration> sanitizeBatchPipelineConfigurationMap(Map map) throws MetricsConfigurationException {
        PriorityChannelPair priorityChannelPair;
        if (isPipelineConfigurationMapPriorityChannelPairBased(map)) {
            return map;
        }
        if (!isPipelineConfigurationMapPriorityBased(map)) {
            throw new MetricsConfigurationException("Invalid Batch Pipeline Configuration");
        }
        HashMap map2 = new HashMap();
        for (Object obj : map.keySet()) {
            Priority priority = (Priority) obj;
            int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$Priority[priority.ordinal()];
            if (i == 1) {
                priorityChannelPair = new PriorityChannelPair(Priority.NORMAL, Channel.NON_ANONYMOUS);
            } else if (i == 2) {
                priorityChannelPair = new PriorityChannelPair(Priority.NORMAL, Channel.LOCATION);
            } else {
                priorityChannelPair = new PriorityChannelPair(priority, Channel.ANONYMOUS);
            }
            map2.put(priorityChannelPair, (BatchPipelineConfiguration) map.get(obj));
        }
        return map2;
    }

    /* JADX INFO: renamed from: com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$Priority;

        static {
            int[] iArr = new int[Priority.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$Priority = iArr;
            try {
                iArr[Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Priority[Priority.RESERVED_FOR_LOCATION_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean isPipelineConfigurationMapPriorityBased(Map map) {
        for (Object obj : map.keySet()) {
            if (!(obj instanceof Priority) || !(map.get(obj) instanceof BatchPipelineConfiguration)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPipelineConfigurationMapPriorityChannelPairBased(Map map) {
        for (Object obj : map.keySet()) {
            if (!(obj instanceof PriorityChannelPair) || !(map.get(obj) instanceof BatchPipelineConfiguration)) {
                return false;
            }
        }
        return true;
    }
}
