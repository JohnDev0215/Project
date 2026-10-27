package com.android.networkstack.metrics;

import android.stats.connectivity.TransportType;
import android.stats.connectivity.ValidationResult;
import com.android.networkstack.metrics.ProbeEvents;
import com.android.networkstack.protobuf.GeneratedMessageLite;
import com.android.networkstack.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkValidationReported extends GeneratedMessageLite<NetworkValidationReported, Builder> implements Object {
    private static final NetworkValidationReported DEFAULT_INSTANCE;
    public static final int LATENCY_MICROS_FIELD_NUMBER = 4;
    private static volatile Parser<NetworkValidationReported> PARSER = null;
    public static final int PROBE_EVENTS_FIELD_NUMBER = 2;
    public static final int RANDOM_NUMBER_FIELD_NUMBER = 6;
    public static final int TRANSPORT_TYPE_FIELD_NUMBER = 1;
    public static final int VALIDATION_INDEX_FIELD_NUMBER = 5;
    public static final int VALIDATION_RESULT_FIELD_NUMBER = 3;
    private int bitField0_;
    private int latencyMicros_;
    private ProbeEvents probeEvents_;
    private int randomNumber_;
    private int transportType_;
    private int validationIndex_;
    private int validationResult_;

    private NetworkValidationReported() {
    }

    public TransportType getTransportType() {
        TransportType transportTypeForNumber = TransportType.forNumber(this.transportType_);
        return transportTypeForNumber == null ? TransportType.TT_UNKNOWN : transportTypeForNumber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransportType(TransportType transportType) {
        if (transportType == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.transportType_ = transportType.getNumber();
    }

    public ProbeEvents getProbeEvents() {
        ProbeEvents probeEvents = this.probeEvents_;
        return probeEvents == null ? ProbeEvents.getDefaultInstance() : probeEvents;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProbeEvents(ProbeEvents.Builder builder) {
        this.probeEvents_ = builder.build();
        this.bitField0_ |= 2;
    }

    public ValidationResult getValidationResult() {
        ValidationResult validationResultForNumber = ValidationResult.forNumber(this.validationResult_);
        return validationResultForNumber == null ? ValidationResult.VR_UNKNOWN : validationResultForNumber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValidationResult(ValidationResult validationResult) {
        if (validationResult == null) {
            throw null;
        }
        this.bitField0_ |= 4;
        this.validationResult_ = validationResult.getNumber();
    }

    public int getLatencyMicros() {
        return this.latencyMicros_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLatencyMicros(int i) {
        this.bitField0_ |= 8;
        this.latencyMicros_ = i;
    }

    public int getValidationIndex() {
        return this.validationIndex_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setValidationIndex(int i) {
        this.bitField0_ |= 16;
        this.validationIndex_ = i;
    }

    public int getRandomNumber() {
        return this.randomNumber_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRandomNumber(int i) {
        this.bitField0_ |= 32;
        this.randomNumber_ = i;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkValidationReported, Builder> implements Object {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(NetworkValidationReported.DEFAULT_INSTANCE);
        }

        public Builder setTransportType(TransportType transportType) {
            copyOnWrite();
            ((NetworkValidationReported) this.instance).setTransportType(transportType);
            return this;
        }

        public Builder setProbeEvents(ProbeEvents.Builder builder) {
            copyOnWrite();
            ((NetworkValidationReported) this.instance).setProbeEvents(builder);
            return this;
        }

        public Builder setValidationResult(ValidationResult validationResult) {
            copyOnWrite();
            ((NetworkValidationReported) this.instance).setValidationResult(validationResult);
            return this;
        }

        public Builder setLatencyMicros(int i) {
            copyOnWrite();
            ((NetworkValidationReported) this.instance).setLatencyMicros(i);
            return this;
        }

        public Builder setValidationIndex(int i) {
            copyOnWrite();
            ((NetworkValidationReported) this.instance).setValidationIndex(i);
            return this;
        }

        public Builder setRandomNumber(int i) {
            copyOnWrite();
            ((NetworkValidationReported) this.instance).setRandomNumber(i);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.networkstack.metrics.NetworkValidationReported$1, reason: invalid class name */
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
                return new NetworkValidationReported();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0000\u0002\t\u0001\u0003\f\u0002\u0004\u0004\u0003\u0005\u0004\u0004\u0006\u0004\u0005", new Object[]{"bitField0_", "transportType_", TransportType.internalGetVerifier(), "probeEvents_", "validationResult_", ValidationResult.internalGetVerifier(), "latencyMicros_", "validationIndex_", "randomNumber_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<NetworkValidationReported> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (NetworkValidationReported.class) {
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
        NetworkValidationReported networkValidationReported = new NetworkValidationReported();
        DEFAULT_INSTANCE = networkValidationReported;
        GeneratedMessageLite.registerDefaultInstance(NetworkValidationReported.class, networkValidationReported);
    }
}
