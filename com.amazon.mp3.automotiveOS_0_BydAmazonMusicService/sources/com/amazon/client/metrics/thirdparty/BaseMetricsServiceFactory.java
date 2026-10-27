package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.batch.creator.BatchCreator;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.NonVolatileBoundedByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.SemiVolatileBoundedByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.VolatileBoundedByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter;
import com.amazon.client.metrics.thirdparty.batch.transmitter.PeriodicBatchTransmitter;
import com.amazon.client.metrics.thirdparty.batch.transmitter.UploadResultBroadcaster;
import com.amazon.client.metrics.thirdparty.batch.transmitter.UrgentBatchTransmitter;
import com.amazon.client.metrics.thirdparty.codec.MetricBatchProtocolBuffersCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricBatchToStringCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricEntryProtocolBuffersCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricEntryToStringCodec;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.BatchQueueType;
import com.amazon.client.metrics.thirdparty.configuration.BatchTransmitterType;
import com.amazon.client.metrics.thirdparty.configuration.CodecType;
import com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationParser;
import com.amazon.client.metrics.thirdparty.configuration.TransportType;
import com.amazon.client.metrics.thirdparty.transport.MAPMetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;
import com.amazon.client.metrics.thirdparty.transport.OutputStreamMetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.ProvidedOAuthMetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.StaticCredentialMetricsTransport;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class BaseMetricsServiceFactory {
    protected static final DPLogger log = new DPLogger("BaseMetricsServiceFactory");
    protected Context mContext;
    protected DelegatingOAuthHelper mDelegatingOAuthHelper;
    protected final DeviceUtil mDeviceUtil;
    protected MetricsConfiguration mMetricsConfiguration;
    protected MetricsFactory mMetricsFactory;
    protected MetricsTransport mMetricsTransport;
    protected PeriodicMetricReporter mPeriodicMetricReporter;
    protected long mTransmissionOffsetMillis;

    public BaseMetricsServiceFactory(Context context, DeviceUtil deviceUtil, MetricsConfiguration metricsConfiguration) throws MetricsConfigurationException {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.mContext = context;
        this.mDeviceUtil = deviceUtil;
        this.mMetricsConfiguration = metricsConfiguration == null ? initializeMetricsConfiguration(context) : metricsConfiguration;
        this.mDelegatingOAuthHelper = new DelegatingOAuthHelper();
        if (shouldUsePassThroughMode(context)) {
            this.mMetricsConfiguration.setPassThroughMode();
        }
        log.verbose("createMetricsService", "Getting MetricsFactory via AndroidMetricsFactoryImpl.getInstance(Context).", new Object[0]);
        MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(context);
        this.mMetricsFactory = androidMetricsFactoryImpl;
        PeriodicMetricReporterImpl periodicMetricReporterImpl = new PeriodicMetricReporterImpl(androidMetricsFactoryImpl, "MetricsService", "RecordMetric");
        this.mPeriodicMetricReporter = periodicMetricReporterImpl;
        periodicMetricReporterImpl.startRecordingPeriodically(5L, TimeUnit.MINUTES);
        this.mTransmissionOffsetMillis = ((long) ((Math.random() * 9.223372036854776E18d) / 1000.0d)) * 1000;
    }

    public void setOAuthHelper(OAuthHelper oAuthHelper) {
        this.mDelegatingOAuthHelper.setInnerHelper(oAuthHelper);
    }

    protected boolean shouldUsePassThroughMode(Context context) {
        DPLogger dPLogger = log;
        dPLogger.verbose("shouldUsePassThroughMode", "Looking up transport preferences at transport-preferences", new Object[0]);
        boolean z = context.getSharedPreferences("transport-preferences", 0).getBoolean("PREF_USE_PASS_THROUGH_MODE", false);
        dPLogger.verbose("shouldUsePassThroughMode", "usePassThroughMode", Boolean.valueOf(z));
        return z;
    }

    protected MetricsService createMetricsService() {
        int i;
        int i2;
        try {
            UploadResultBroadcaster uploadResultBroadcaster = new UploadResultBroadcaster(this.mContext);
            HashMap map = new HashMap();
            ArrayList arrayList = new ArrayList(Priority.values().length);
            this.mMetricsTransport = createMetricsTransport();
            HashSet<Priority> hashSet = new HashSet(3);
            hashSet.add(Priority.HIGH);
            hashSet.add(Priority.NORMAL);
            hashSet.add(Priority.CRITICAL);
            for (Priority priority : hashSet) {
                Channel[] channelArrValues = Channel.values();
                int length = channelArrValues.length;
                int i3 = 0;
                while (i3 < length) {
                    Channel channel = channelArrValues[i3];
                    BatchPipelineConfiguration batchPipelineConfiguration = getBatchPipelineConfiguration(new PriorityChannelPair(priority, channel));
                    if (batchPipelineConfiguration == null) {
                        log.warn("createMetricsService", String.format("Skipping batch pipeline setup for Priority %s and Channel %s because no configuration is provided.", priority, channel), new Object[0]);
                        i = i3;
                        i2 = length;
                    } else {
                        ByteArrayQueue byteArrayQueueCreateBatchQueue = createBatchQueue(new PriorityChannelPair(priority, channel));
                        byteArrayQueueCreateBatchQueue.setQueueName(priority.name() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + channel.name());
                        BatchCreator batchCreatorCreateBatchCreator = createBatchCreator(byteArrayQueueCreateBatchQueue, new PriorityChannelPair(priority, channel), createDeviceInfoManager(priority, channel));
                        PriorityChannelPair priorityChannelPair = new PriorityChannelPair(priority, channel);
                        map.put(priorityChannelPair, batchCreatorCreateBatchCreator);
                        i = i3;
                        i2 = length;
                        arrayList.add(createBatchTransmitter(byteArrayQueueCreateBatchQueue, this.mMetricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, priorityChannelPair));
                    }
                    i3 = i + 1;
                    length = i2;
                }
            }
            map.putAll(createExtraBatchCreators());
            log.verbose("createMetricsService", "Triggering intial push for stored metrics on service startup", new Object[0]);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((BatchTransmitter) it.next()).transmitBatches(false);
            }
            return new MetricsService(this.mDeviceUtil, map, arrayList);
        } catch (CodecException e) {
            log.error("createMetricsService", "could not serialize device info", e);
            throw new RuntimeException(e);
        } catch (IOException e2) {
            log.error("createMetricsService", "'could not create batch queue", e2);
            throw new RuntimeException(e2);
        }
    }

    protected DeviceInfoManager createDeviceInfoManager(Priority priority, Channel channel) {
        DeviceInfoManager overridingDeviceInfoManager;
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$Channel[channel.ordinal()];
        if (i == 1) {
            overridingDeviceInfoManager = new OverridingDeviceInfoManager(this.mContext, this.mDeviceUtil, "anonymousDeviceSerialNumberUUID", "anonymousSessionIDKey", "anonymousCustomerIDKey", false);
        } else if (i == 2) {
            overridingDeviceInfoManager = new OverridingDeviceInfoManager(this.mContext, this.mDeviceUtil, "nonAnonymousDeviceSerialNumberUUID", "nonAnonymousSessionIDKey", "nonAnonymousCustomerIDKey", true);
        } else if (i == 3) {
            overridingDeviceInfoManager = new RotatingDeviceInfoManager(this.mContext, this.mDeviceUtil, "locationDeviceSerialNumberUUID", "locationSessionIDKey", "locationCustomerIDKey");
        } else {
            overridingDeviceInfoManager = new AndroidDeviceInfoManager(this.mDeviceUtil);
        }
        if (Priority.CRITICAL.equals(priority)) {
            overridingDeviceInfoManager.addToMetricsDeviceInfo("metricBatchType", "critical");
            overridingDeviceInfoManager.addToMetricsDeviceInfo("criticalMetricBatchSource", "app");
        }
        return overridingDeviceInfoManager;
    }

    protected MetricsTransport createMetricsTransport() {
        StaticCredentialMetricsTransport staticCredentialMetricsTransport = new StaticCredentialMetricsTransport(this.mContext, this.mMetricsConfiguration, this.mDeviceUtil, null);
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType[this.mMetricsConfiguration.getNetworkConfiguration().getTransportType().ordinal()];
        if (i != 1) {
            if (i == 2) {
                try {
                    return createOutputStreamMetricsTransport();
                } catch (IOException e) {
                    log.error("createMetricsTransport", "postInitialize failed", e);
                    throw new RuntimeException(e);
                }
            }
            throw new IllegalArgumentException("Unsupported TransportType: " + this.mMetricsConfiguration.getNetworkConfiguration().getTransportType());
        }
        int i2 = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[this.mMetricsConfiguration.getHttpConfiguration().getHttpRequestSignerType().ordinal()];
        if (i2 == 1) {
            return createProvidedOAuthTransport(this.mDelegatingOAuthHelper, staticCredentialMetricsTransport);
        }
        if (i2 == 2 || i2 == 3) {
            return createMAPMetricsTransport(staticCredentialMetricsTransport);
        }
        throw new IllegalArgumentException("Unknown request signer type: " + this.mMetricsConfiguration.getHttpConfiguration().getHttpRequestSignerType());
    }

    protected MetricsTransport createProvidedOAuthTransport(OAuthHelper oAuthHelper, MetricsTransport metricsTransport) {
        return new ProvidedOAuthMetricsTransport(this.mContext, this.mMetricsConfiguration, this.mDeviceUtil, oAuthHelper, metricsTransport);
    }

    protected MetricsTransport createMAPMetricsTransport(MetricsTransport metricsTransport) {
        return new MAPMetricsTransport(this.mContext, this.mMetricsConfiguration, this.mDeviceUtil, metricsTransport);
    }

    protected MetricsTransport createOutputStreamMetricsTransport() throws IOException {
        File file = new File(this.mContext.getCacheDir(), "metric-log");
        file.createNewFile();
        log.info("createOutputStreamMetricsService", "Metric log file: " + file.getAbsolutePath(), new Object[0]);
        return new OutputStreamMetricsTransport(new BufferedOutputStream(new FileOutputStream(file)));
    }

    protected BatchCreator createBatchCreator(ByteArrayQueue byteArrayQueue, PriorityChannelPair priorityChannelPair, DeviceInfoManager deviceInfoManager) throws CodecException {
        BatchPipelineConfiguration batchPipelineConfiguration = getBatchPipelineConfiguration(priorityChannelPair);
        UserAgentHelper userAgentHelper = new UserAgentHelper(this.mContext, deviceInfoManager);
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType[this.mMetricsConfiguration.getCodecConfiguration().getCodecType().ordinal()];
        if (i == 1) {
            return new BatchCreator(byteArrayQueue, new MetricBatchProtocolBuffersCodec(), new MetricEntryProtocolBuffersCodec(), batchPipelineConfiguration, this.mPeriodicMetricReporter, deviceInfoManager, userAgentHelper);
        }
        if (i == 2) {
            return new BatchCreator(byteArrayQueue, new MetricBatchToStringCodec(), new MetricEntryToStringCodec(), batchPipelineConfiguration, this.mPeriodicMetricReporter, deviceInfoManager, userAgentHelper);
        }
        throw new IllegalArgumentException("Unsupported CodecType: " + this.mMetricsConfiguration.getCodecConfiguration().getCodecType());
    }

    protected Map<PriorityChannelPair, BatchCreator> createExtraBatchCreators() {
        return Collections.emptyMap();
    }

    protected ByteArrayQueue createBatchQueue(PriorityChannelPair priorityChannelPair) throws IOException {
        BatchPipelineConfiguration batchPipelineConfiguration = getBatchPipelineConfiguration(priorityChannelPair);
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType[this.mMetricsConfiguration.getPipelineConfiguration(priorityChannelPair).getBatchQueueType().ordinal()];
        if (i == 1) {
            return new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter);
        }
        if (i == 2) {
            File dir = this.mContext.getDir(this.mMetricsConfiguration.getBatchQueueDirectoryName(priorityChannelPair), 0);
            if (dir != null && dir.isDirectory()) {
                return new NonVolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter, dir);
            }
            String str = String.format("Failed to create batch directory for non-volatile queue. No metrics will be recorded for Channel: %s and Priority: %s in the NonVolatile queue. Falling back to VolatileQueue", priorityChannelPair.getPriority().name(), priorityChannelPair.getChannel().name());
            log.error("createBatchQueue", str, new Object[0]);
            Log.wtf("createBatchQueue", str);
            return new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter);
        }
        if (i == 3) {
            File dir2 = this.mContext.getDir(this.mMetricsConfiguration.getBatchQueueDirectoryName(priorityChannelPair), 0);
            if (dir2 != null && dir2.isDirectory()) {
                return new SemiVolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter, dir2);
            }
            String str2 = String.format("Failed to create batch directory for semi-volatile queue. No metrics will be recorded for Channel: %s and Priority: %s in the SemiVolatile queue. Falling back to VolatileQueue", priorityChannelPair.getPriority().name(), priorityChannelPair.getChannel().name());
            log.error("createBatchQueue", str2, new Object[0]);
            Log.wtf("createBatchQueue", str2);
            return new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter);
        }
        throw new IllegalArgumentException("Unsupported BatchQueueType: " + this.mMetricsConfiguration.getPipelineConfiguration(priorityChannelPair).getBatchQueueType());
    }

    /* JADX INFO: renamed from: com.amazon.client.metrics.thirdparty.BaseMetricsServiceFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$Channel;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType;

        static {
            int[] iArr = new int[BatchTransmitterType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType = iArr;
            try {
                iArr[BatchTransmitterType.PERIODIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType[BatchTransmitterType.URGENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BatchQueueType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType = iArr2;
            try {
                iArr2[BatchQueueType.VOLATILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType[BatchQueueType.NON_VOLATILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType[BatchQueueType.SEMI_VOLATILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[CodecType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType = iArr3;
            try {
                iArr3[CodecType.PROTOCOL_BUFFERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType[CodecType.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr4 = new int[TransportType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType = iArr4;
            try {
                iArr4[TransportType.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType[TransportType.OUTPUT_STREAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr5 = new int[HttpRequestSignerType.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType = iArr5;
            try {
                iArr5[HttpRequestSignerType.OAUTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP_OAUTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr6 = new int[Channel.values().length];
            $SwitchMap$com$amazon$client$metrics$thirdparty$Channel = iArr6;
            try {
                iArr6[Channel.ANONYMOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Channel[Channel.NON_ANONYMOUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Channel[Channel.LOCATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    protected BatchTransmitter createBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, BatchPipelineConfiguration batchPipelineConfiguration, PriorityChannelPair priorityChannelPair) {
        long jAddOffset = addOffset(batchPipelineConfiguration.getTransmissionPeriodMillis(), this.mTransmissionOffsetMillis);
        int i = AnonymousClass1.$SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType[batchPipelineConfiguration.getBatchTransmitterType().ordinal()];
        if (i == 1) {
            return new PeriodicBatchTransmitter(byteArrayQueue, metricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, this.mPeriodicMetricReporter, jAddOffset, this.mContext);
        }
        if (i == 2) {
            return new UrgentBatchTransmitter(byteArrayQueue, metricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, this.mPeriodicMetricReporter, jAddOffset, this.mContext);
        }
        throw new IllegalArgumentException("Unsupported PeriodicBatchTransmitterType " + batchPipelineConfiguration.getBatchTransmitterType());
    }

    protected BatchPipelineConfiguration getBatchPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return this.mMetricsConfiguration.getPipelineConfiguration(priorityChannelPair);
    }

    public void shutdown() {
        PeriodicMetricReporter periodicMetricReporter = this.mPeriodicMetricReporter;
        if (periodicMetricReporter != null) {
            periodicMetricReporter.shutdown();
        }
    }

    protected MetricsConfiguration initializeMetricsConfiguration(Context context) throws MetricsConfigurationException {
        return new MetricsConfigurationHelper(context).initializeMetricsConfiguration(createMetricsConfigurationParser());
    }

    protected MetricsConfigurationParser createMetricsConfigurationParser() {
        return new MetricsConfigurationParser();
    }

    protected long addOffset(long j, long j2) {
        long j3 = j2 % j;
        return j3 < j / 2 ? j3 + j : j3;
    }

    protected static class DelegatingOAuthHelper implements OAuthHelper {
        public OAuthHelper mInnerHelper;

        protected DelegatingOAuthHelper() {
        }

        public void setInnerHelper(OAuthHelper oAuthHelper) {
            this.mInnerHelper = oAuthHelper;
        }

        @Override // com.amazon.client.metrics.thirdparty.transport.OAuthHelper
        public String getAccessToken() throws Exception {
            OAuthHelper oAuthHelper = this.mInnerHelper;
            if (oAuthHelper == null) {
                return null;
            }
            return oAuthHelper.getAccessToken();
        }
    }
}
