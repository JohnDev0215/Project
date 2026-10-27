package com.android.networkstack.metrics;

import android.stats.connectivity.DisconnectCode;
import android.stats.connectivity.TransportType;
import com.android.networkstack.metrics.DhcpSession;
import com.android.networkstack.protobuf.GeneratedMessageLite;
import com.android.networkstack.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkIpProvisioningReported extends GeneratedMessageLite<NetworkIpProvisioningReported, Builder> implements Object {
    private static final NetworkIpProvisioningReported DEFAULT_INSTANCE;
    public static final int DHCP_SESSION_FIELD_NUMBER = 6;
    public static final int DISCONNECT_CODE_FIELD_NUMBER = 5;
    public static final int IPV4_LATENCY_MICROS_FIELD_NUMBER = 2;
    public static final int IPV6_LATENCY_MICROS_FIELD_NUMBER = 3;
    private static volatile Parser<NetworkIpProvisioningReported> PARSER = null;
    public static final int PROVISIONING_DURATION_MICROS_FIELD_NUMBER = 4;
    public static final int RANDOM_NUMBER_FIELD_NUMBER = 7;
    public static final int TRANSPORT_TYPE_FIELD_NUMBER = 1;
    private int bitField0_;
    private DhcpSession dhcpSession_;
    private int disconnectCode_;
    private int ipv4LatencyMicros_;
    private int ipv6LatencyMicros_;
    private long provisioningDurationMicros_;
    private int randomNumber_;
    private int transportType_;

    private NetworkIpProvisioningReported() {
    }

    public TransportType getTransportType() {
        TransportType transportTypeForNumber = TransportType.forNumber(this.transportType_);
        return transportTypeForNumber == null ? TransportType.TT_UNKNOWN : transportTypeForNumber;
    }

    public boolean hasIpv4LatencyMicros() {
        return (this.bitField0_ & 2) != 0;
    }

    public int getIpv4LatencyMicros() {
        return this.ipv4LatencyMicros_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIpv4LatencyMicros(int i) {
        this.bitField0_ |= 2;
        this.ipv4LatencyMicros_ = i;
    }

    public boolean hasIpv6LatencyMicros() {
        return (this.bitField0_ & 4) != 0;
    }

    public int getIpv6LatencyMicros() {
        return this.ipv6LatencyMicros_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIpv6LatencyMicros(int i) {
        this.bitField0_ |= 4;
        this.ipv6LatencyMicros_ = i;
    }

    public long getProvisioningDurationMicros() {
        return this.provisioningDurationMicros_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setProvisioningDurationMicros(long j) {
        this.bitField0_ |= 8;
        this.provisioningDurationMicros_ = j;
    }

    public boolean hasDisconnectCode() {
        return (this.bitField0_ & 16) != 0;
    }

    public DisconnectCode getDisconnectCode() {
        DisconnectCode disconnectCodeForNumber = DisconnectCode.forNumber(this.disconnectCode_);
        return disconnectCodeForNumber == null ? DisconnectCode.DC_NONE : disconnectCodeForNumber;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDisconnectCode(DisconnectCode disconnectCode) {
        if (disconnectCode == null) {
            throw null;
        }
        this.bitField0_ |= 16;
        this.disconnectCode_ = disconnectCode.getNumber();
    }

    public DhcpSession getDhcpSession() {
        DhcpSession dhcpSession = this.dhcpSession_;
        return dhcpSession == null ? DhcpSession.getDefaultInstance() : dhcpSession;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDhcpSession(DhcpSession.Builder builder) {
        this.dhcpSession_ = builder.build();
        this.bitField0_ |= 32;
    }

    public int getRandomNumber() {
        return this.randomNumber_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRandomNumber(int i) {
        this.bitField0_ |= 64;
        this.randomNumber_ = i;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkIpProvisioningReported, Builder> implements Object {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(NetworkIpProvisioningReported.DEFAULT_INSTANCE);
        }

        public boolean hasIpv4LatencyMicros() {
            return ((NetworkIpProvisioningReported) this.instance).hasIpv4LatencyMicros();
        }

        public Builder setIpv4LatencyMicros(int i) {
            copyOnWrite();
            ((NetworkIpProvisioningReported) this.instance).setIpv4LatencyMicros(i);
            return this;
        }

        public boolean hasIpv6LatencyMicros() {
            return ((NetworkIpProvisioningReported) this.instance).hasIpv6LatencyMicros();
        }

        public Builder setIpv6LatencyMicros(int i) {
            copyOnWrite();
            ((NetworkIpProvisioningReported) this.instance).setIpv6LatencyMicros(i);
            return this;
        }

        public Builder setProvisioningDurationMicros(long j) {
            copyOnWrite();
            ((NetworkIpProvisioningReported) this.instance).setProvisioningDurationMicros(j);
            return this;
        }

        public boolean hasDisconnectCode() {
            return ((NetworkIpProvisioningReported) this.instance).hasDisconnectCode();
        }

        public Builder setDisconnectCode(DisconnectCode disconnectCode) {
            copyOnWrite();
            ((NetworkIpProvisioningReported) this.instance).setDisconnectCode(disconnectCode);
            return this;
        }

        public Builder setDhcpSession(DhcpSession.Builder builder) {
            copyOnWrite();
            ((NetworkIpProvisioningReported) this.instance).setDhcpSession(builder);
            return this;
        }

        public Builder setRandomNumber(int i) {
            copyOnWrite();
            ((NetworkIpProvisioningReported) this.instance).setRandomNumber(i);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.networkstack.metrics.NetworkIpProvisioningReported$1, reason: invalid class name */
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
                return new NetworkIpProvisioningReported();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001\f\u0000\u0002\u0004\u0001\u0003\u0004\u0002\u0004\u0002\u0003\u0005\f\u0004\u0006\t\u0005\u0007\u0004\u0006", new Object[]{"bitField0_", "transportType_", TransportType.internalGetVerifier(), "ipv4LatencyMicros_", "ipv6LatencyMicros_", "provisioningDurationMicros_", "disconnectCode_", DisconnectCode.internalGetVerifier(), "dhcpSession_", "randomNumber_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<NetworkIpProvisioningReported> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (NetworkIpProvisioningReported.class) {
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
            case RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        NetworkIpProvisioningReported networkIpProvisioningReported = new NetworkIpProvisioningReported();
        DEFAULT_INSTANCE = networkIpProvisioningReported;
        GeneratedMessageLite.registerDefaultInstance(NetworkIpProvisioningReported.class, networkIpProvisioningReported);
    }
}
