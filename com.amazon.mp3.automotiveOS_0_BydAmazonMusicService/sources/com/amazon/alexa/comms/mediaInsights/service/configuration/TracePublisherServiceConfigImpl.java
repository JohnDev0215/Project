package com.amazon.alexa.comms.mediaInsights.service.configuration;

import com.amazon.mp3.skiplimit.impl.HardCodedRefreshStrategy;
import kotlin.time.DurationKt;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class TracePublisherServiceConfigImpl implements TracePublisherServiceConfiguration {
    public static final TracePublisherServiceConfiguration DEFAULT = builder().build();

    @NonNull
    private Integer deviceDetailsJobIntervalInMillis;

    @NonNull
    private Integer exponentForHttpRetries;

    @NonNull
    private Long flushJobIntervalInMillis;

    @NonNull
    private Integer flushThreadPoolSize;

    @NonNull
    private Integer httpConnectTimeout;

    @NonNull
    private Integer httpReadTimeout;

    @NonNull
    private Integer maxNumberOfHttpRetries;

    @NonNull
    private Integer maxTraceTTLInMillis;

    @NonNull
    private Integer minimumDelayForHttpRetries;

    @NonNull
    private Integer totalTracesCapacityInBytes;

    @NonNull
    private String traceServiceHttpsEndpoint;

    @NonNull
    private Long wakeLockTimeout;

    /* JADX INFO: Access modifiers changed from: private */
    public static String $default$traceServiceHttpsEndpoint() {
        return "https://prod.insights.comms.alexa.a2z.com/traces";
    }

    public static class TracePublisherServiceConfigImplBuilder {
        private Integer deviceDetailsJobIntervalInMillis;
        private boolean deviceDetailsJobIntervalInMillis$set;
        private Integer exponentForHttpRetries;
        private boolean exponentForHttpRetries$set;
        private Long flushJobIntervalInMillis;
        private boolean flushJobIntervalInMillis$set;
        private Integer flushThreadPoolSize;
        private boolean flushThreadPoolSize$set;
        private Integer httpConnectTimeout;
        private boolean httpConnectTimeout$set;
        private Integer httpReadTimeout;
        private boolean httpReadTimeout$set;
        private Integer maxNumberOfHttpRetries;
        private boolean maxNumberOfHttpRetries$set;
        private Integer maxTraceTTLInMillis;
        private boolean maxTraceTTLInMillis$set;
        private Integer minimumDelayForHttpRetries;
        private boolean minimumDelayForHttpRetries$set;
        private Integer totalTracesCapacityInBytes;
        private boolean totalTracesCapacityInBytes$set;
        private String traceServiceHttpsEndpoint;
        private boolean traceServiceHttpsEndpoint$set;
        private Long wakeLockTimeout;
        private boolean wakeLockTimeout$set;

        TracePublisherServiceConfigImplBuilder() {
        }

        public TracePublisherServiceConfigImpl build() {
            return new TracePublisherServiceConfigImpl(this.totalTracesCapacityInBytes$set ? this.totalTracesCapacityInBytes : TracePublisherServiceConfigImpl.$default$totalTracesCapacityInBytes(), this.maxTraceTTLInMillis$set ? this.maxTraceTTLInMillis : TracePublisherServiceConfigImpl.$default$maxTraceTTLInMillis(), this.flushJobIntervalInMillis$set ? this.flushJobIntervalInMillis : TracePublisherServiceConfigImpl.$default$flushJobIntervalInMillis(), this.deviceDetailsJobIntervalInMillis$set ? this.deviceDetailsJobIntervalInMillis : TracePublisherServiceConfigImpl.$default$deviceDetailsJobIntervalInMillis(), this.flushThreadPoolSize$set ? this.flushThreadPoolSize : TracePublisherServiceConfigImpl.$default$flushThreadPoolSize(), this.traceServiceHttpsEndpoint$set ? this.traceServiceHttpsEndpoint : TracePublisherServiceConfigImpl.$default$traceServiceHttpsEndpoint(), this.httpConnectTimeout$set ? this.httpConnectTimeout : TracePublisherServiceConfigImpl.$default$httpConnectTimeout(), this.httpReadTimeout$set ? this.httpReadTimeout : TracePublisherServiceConfigImpl.$default$httpReadTimeout(), this.wakeLockTimeout$set ? this.wakeLockTimeout : TracePublisherServiceConfigImpl.$default$wakeLockTimeout(), this.maxNumberOfHttpRetries$set ? this.maxNumberOfHttpRetries : TracePublisherServiceConfigImpl.$default$maxNumberOfHttpRetries(), this.minimumDelayForHttpRetries$set ? this.minimumDelayForHttpRetries : TracePublisherServiceConfigImpl.$default$minimumDelayForHttpRetries(), this.exponentForHttpRetries$set ? this.exponentForHttpRetries : TracePublisherServiceConfigImpl.$default$exponentForHttpRetries());
        }

        public String toString() {
            return "TracePublisherServiceConfigImpl.TracePublisherServiceConfigImplBuilder(totalTracesCapacityInBytes=" + this.totalTracesCapacityInBytes + ", maxTraceTTLInMillis=" + this.maxTraceTTLInMillis + ", flushJobIntervalInMillis=" + this.flushJobIntervalInMillis + ", deviceDetailsJobIntervalInMillis=" + this.deviceDetailsJobIntervalInMillis + ", flushThreadPoolSize=" + this.flushThreadPoolSize + ", traceServiceHttpsEndpoint=" + this.traceServiceHttpsEndpoint + ", httpConnectTimeout=" + this.httpConnectTimeout + ", httpReadTimeout=" + this.httpReadTimeout + ", wakeLockTimeout=" + this.wakeLockTimeout + ", maxNumberOfHttpRetries=" + this.maxNumberOfHttpRetries + ", minimumDelayForHttpRetries=" + this.minimumDelayForHttpRetries + ", exponentForHttpRetries=" + this.exponentForHttpRetries + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$deviceDetailsJobIntervalInMillis() {
        return 120000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$exponentForHttpRetries() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long $default$flushJobIntervalInMillis() {
        return 60000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$flushThreadPoolSize() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$httpConnectTimeout() {
        return 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$httpReadTimeout() {
        return 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$maxNumberOfHttpRetries() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$maxTraceTTLInMillis() {
        return Integer.valueOf(HardCodedRefreshStrategy.MS_IN_MINUTE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$minimumDelayForHttpRetries() {
        return 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$totalTracesCapacityInBytes() {
        return Integer.valueOf(DurationKt.NANOS_IN_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long $default$wakeLockTimeout() {
        return 11000L;
    }

    TracePublisherServiceConfigImpl(@NonNull Integer num, @NonNull Integer num2, @NonNull Long l, @NonNull Integer num3, @NonNull Integer num4, @NonNull String str, @NonNull Integer num5, @NonNull Integer num6, @NonNull Long l2, @NonNull Integer num7, @NonNull Integer num8, @NonNull Integer num9) {
        if (num == null) {
            throw new IllegalArgumentException("totalTracesCapacityInBytes is null");
        }
        if (num2 == null) {
            throw new IllegalArgumentException("maxTraceTTLInMillis is null");
        }
        if (l == null) {
            throw new IllegalArgumentException("flushJobIntervalInMillis is null");
        }
        if (num3 == null) {
            throw new IllegalArgumentException("deviceDetailsJobIntervalInMillis is null");
        }
        if (num4 == null) {
            throw new IllegalArgumentException("flushThreadPoolSize is null");
        }
        if (str == null) {
            throw new IllegalArgumentException("traceServiceHttpsEndpoint is null");
        }
        if (num5 == null) {
            throw new IllegalArgumentException("httpConnectTimeout is null");
        }
        if (num6 == null) {
            throw new IllegalArgumentException("httpReadTimeout is null");
        }
        if (l2 == null) {
            throw new IllegalArgumentException("wakeLockTimeout is null");
        }
        if (num7 == null) {
            throw new IllegalArgumentException("maxNumberOfHttpRetries is null");
        }
        if (num8 == null) {
            throw new IllegalArgumentException("minimumDelayForHttpRetries is null");
        }
        if (num9 == null) {
            throw new IllegalArgumentException("exponentForHttpRetries is null");
        }
        this.totalTracesCapacityInBytes = num;
        this.maxTraceTTLInMillis = num2;
        this.flushJobIntervalInMillis = l;
        this.deviceDetailsJobIntervalInMillis = num3;
        this.flushThreadPoolSize = num4;
        this.traceServiceHttpsEndpoint = str;
        this.httpConnectTimeout = num5;
        this.httpReadTimeout = num6;
        this.wakeLockTimeout = l2;
        this.maxNumberOfHttpRetries = num7;
        this.minimumDelayForHttpRetries = num8;
        this.exponentForHttpRetries = num9;
    }

    public static TracePublisherServiceConfigImplBuilder builder() {
        return new TracePublisherServiceConfigImplBuilder();
    }

    public String toString() {
        return "TracePublisherServiceConfigImpl(totalTracesCapacityInBytes=" + getTotalTracesCapacityInBytes() + ", maxTraceTTLInMillis=" + getMaxTraceTTLInMillis() + ", flushJobIntervalInMillis=" + getFlushJobIntervalInMillis() + ", deviceDetailsJobIntervalInMillis=" + getDeviceDetailsJobIntervalInMillis() + ", flushThreadPoolSize=" + getFlushThreadPoolSize() + ", traceServiceHttpsEndpoint=" + getTraceServiceHttpsEndpoint() + ", httpConnectTimeout=" + getHttpConnectTimeout() + ", httpReadTimeout=" + getHttpReadTimeout() + ", wakeLockTimeout=" + getWakeLockTimeout() + ", maxNumberOfHttpRetries=" + getMaxNumberOfHttpRetries() + ", minimumDelayForHttpRetries=" + getMinimumDelayForHttpRetries() + ", exponentForHttpRetries=" + getExponentForHttpRetries() + ")";
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getTotalTracesCapacityInBytes() {
        return this.totalTracesCapacityInBytes;
    }

    @NonNull
    public Integer getMaxTraceTTLInMillis() {
        return this.maxTraceTTLInMillis;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Long getFlushJobIntervalInMillis() {
        return this.flushJobIntervalInMillis;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getDeviceDetailsJobIntervalInMillis() {
        return this.deviceDetailsJobIntervalInMillis;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getFlushThreadPoolSize() {
        return this.flushThreadPoolSize;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public String getTraceServiceHttpsEndpoint() {
        return this.traceServiceHttpsEndpoint;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getHttpConnectTimeout() {
        return this.httpConnectTimeout;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getHttpReadTimeout() {
        return this.httpReadTimeout;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Long getWakeLockTimeout() {
        return this.wakeLockTimeout;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getMaxNumberOfHttpRetries() {
        return this.maxNumberOfHttpRetries;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getMinimumDelayForHttpRetries() {
        return this.minimumDelayForHttpRetries;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getExponentForHttpRetries() {
        return this.exponentForHttpRetries;
    }
}
