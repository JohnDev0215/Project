package com.android.networkstack.metrics;

import android.stats.connectivity.ProbeResult;
import android.stats.connectivity.ProbeType;
import com.android.networkstack.metrics.CapportApiData;
import com.android.networkstack.protobuf.GeneratedMessageLite;
import com.android.networkstack.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class ProbeEvent extends GeneratedMessageLite<ProbeEvent, Builder> implements Object {
    public static final int CAPPORT_API_DATA_FIELD_NUMBER = 4;
    private static final ProbeEvent DEFAULT_INSTANCE;
    public static final int LATENCY_MICROS_FIELD_NUMBER = 2;
    private static volatile Parser<ProbeEvent> PARSER = null;
    public static final int PROBE_RESULT_FIELD_NUMBER = 3;
    public static final int PROBE_TYPE_FIELD_NUMBER = 1;
    private int bitField0_;
    private CapportApiData capportApiData_;
    private int latencyMicros_;
    private int probeResult_;
    private int probeType_;

    private ProbeEvent() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProbeType(ProbeType probeType) {
        if (probeType == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.probeType_ = probeType.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatencyMicros(int i) {
        this.bitField0_ |= 2;
        this.latencyMicros_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProbeResult(ProbeResult probeResult) {
        if (probeResult == null) {
            throw null;
        }
        this.bitField0_ |= 4;
        this.probeResult_ = probeResult.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCapportApiData(CapportApiData.Builder builder) {
        this.capportApiData_ = builder.build();
        this.bitField0_ |= 8;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProbeEvent, Builder> implements Object {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ProbeEvent.DEFAULT_INSTANCE);
        }

        public Builder setProbeType(ProbeType probeType) {
            copyOnWrite();
            ((ProbeEvent) this.instance).setProbeType(probeType);
            return this;
        }

        public Builder setLatencyMicros(int i) {
            copyOnWrite();
            ((ProbeEvent) this.instance).setLatencyMicros(i);
            return this;
        }

        public Builder setProbeResult(ProbeResult probeResult) {
            copyOnWrite();
            ((ProbeEvent) this.instance).setProbeResult(probeResult);
            return this;
        }

        public Builder setCapportApiData(CapportApiData.Builder builder) {
            copyOnWrite();
            ((ProbeEvent) this.instance).setCapportApiData(builder);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.networkstack.metrics.ProbeEvent$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.android.networkstack.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ProbeEvent();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001\u0003\f\u0002\u0004\t\u0003", new Object[]{"bitField0_", "probeType_", ProbeType.internalGetVerifier(), "latencyMicros_", "probeResult_", ProbeResult.internalGetVerifier(), "capportApiData_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ProbeEvent> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ProbeEvent.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        ProbeEvent probeEvent = new ProbeEvent();
        DEFAULT_INSTANCE = probeEvent;
        GeneratedMessageLite.registerDefaultInstance(ProbeEvent.class, probeEvent);
    }
}
