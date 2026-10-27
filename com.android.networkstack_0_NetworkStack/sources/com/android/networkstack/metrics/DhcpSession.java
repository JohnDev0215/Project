package com.android.networkstack.metrics;

import android.stats.connectivity.DhcpErrorCode;
import android.stats.connectivity.DhcpFeature;
import android.stats.connectivity.HostnameTransResult;
import com.android.networkstack.protobuf.GeneratedMessageLite;
import com.android.networkstack.protobuf.Internal;
import com.android.networkstack.protobuf.Internal$ListAdapter$Converter;
import com.android.networkstack.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class DhcpSession extends GeneratedMessageLite<DhcpSession, Builder> implements Object {
    public static final int CONFLICT_COUNT_FIELD_NUMBER = 4;
    private static final DhcpSession DEFAULT_INSTANCE;
    public static final int DISCOVER_COUNT_FIELD_NUMBER = 2;
    public static final int ERROR_CODE_FIELD_NUMBER = 5;
    public static final int HT_RESULT_FIELD_NUMBER = 6;
    private static volatile Parser<DhcpSession> PARSER = null;
    public static final int REQUEST_COUNT_FIELD_NUMBER = 3;
    public static final int USED_FEATURES_FIELD_NUMBER = 1;
    private int bitField0_;
    private int conflictCount_;
    private int discoverCount_;
    private int htResult_;
    private int requestCount_;
    private static final Internal$ListAdapter$Converter<Integer, DhcpFeature> usedFeatures_converter_ = new Internal$ListAdapter$Converter<Integer, DhcpFeature>() { // from class: com.android.networkstack.metrics.DhcpSession.1
    };
    private static final Internal$ListAdapter$Converter<Integer, DhcpErrorCode> errorCode_converter_ = new Internal$ListAdapter$Converter<Integer, DhcpErrorCode>() { // from class: com.android.networkstack.metrics.DhcpSession.2
    };
    private Internal.IntList usedFeatures_ = GeneratedMessageLite.emptyIntList();
    private Internal.IntList errorCode_ = GeneratedMessageLite.emptyIntList();

    private DhcpSession() {
    }

    static {
        DhcpSession dhcpSession = new DhcpSession();
        DEFAULT_INSTANCE = dhcpSession;
        GeneratedMessageLite.registerDefaultInstance(DhcpSession.class, dhcpSession);
    }

    private void ensureUsedFeaturesIsMutable() {
        if (this.usedFeatures_.isModifiable()) {
            return;
        }
        this.usedFeatures_ = GeneratedMessageLite.mutableCopy(this.usedFeatures_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addUsedFeatures(DhcpFeature dhcpFeature) {
        if (dhcpFeature == null) {
            throw null;
        }
        ensureUsedFeaturesIsMutable();
        this.usedFeatures_.addInt(dhcpFeature.getNumber());
    }

    public int getDiscoverCount() {
        return this.discoverCount_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDiscoverCount(int i) {
        this.bitField0_ |= 1;
        this.discoverCount_ = i;
    }

    public int getRequestCount() {
        return this.requestCount_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRequestCount(int i) {
        this.bitField0_ |= 2;
        this.requestCount_ = i;
    }

    public int getConflictCount() {
        return this.conflictCount_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setConflictCount(int i) {
        this.bitField0_ |= 4;
        this.conflictCount_ = i;
    }

    public int getErrorCodeCount() {
        return this.errorCode_.size();
    }

    private void ensureErrorCodeIsMutable() {
        if (this.errorCode_.isModifiable()) {
            return;
        }
        this.errorCode_ = GeneratedMessageLite.mutableCopy(this.errorCode_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addErrorCode(DhcpErrorCode dhcpErrorCode) {
        if (dhcpErrorCode == null) {
            throw null;
        }
        ensureErrorCodeIsMutable();
        this.errorCode_.addInt(dhcpErrorCode.getNumber());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHtResult(HostnameTransResult hostnameTransResult) {
        if (hostnameTransResult == null) {
            throw null;
        }
        this.bitField0_ |= 8;
        this.htResult_ = hostnameTransResult.getNumber();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DhcpSession, Builder> implements Object {
        private Builder() {
            super(DhcpSession.DEFAULT_INSTANCE);
        }

        public Builder addUsedFeatures(DhcpFeature dhcpFeature) {
            copyOnWrite();
            ((DhcpSession) this.instance).addUsedFeatures(dhcpFeature);
            return this;
        }

        public int getDiscoverCount() {
            return ((DhcpSession) this.instance).getDiscoverCount();
        }

        public Builder setDiscoverCount(int i) {
            copyOnWrite();
            ((DhcpSession) this.instance).setDiscoverCount(i);
            return this;
        }

        public int getRequestCount() {
            return ((DhcpSession) this.instance).getRequestCount();
        }

        public Builder setRequestCount(int i) {
            copyOnWrite();
            ((DhcpSession) this.instance).setRequestCount(i);
            return this;
        }

        public int getConflictCount() {
            return ((DhcpSession) this.instance).getConflictCount();
        }

        public Builder setConflictCount(int i) {
            copyOnWrite();
            ((DhcpSession) this.instance).setConflictCount(i);
            return this;
        }

        public int getErrorCodeCount() {
            return ((DhcpSession) this.instance).getErrorCodeCount();
        }

        public Builder addErrorCode(DhcpErrorCode dhcpErrorCode) {
            copyOnWrite();
            ((DhcpSession) this.instance).addErrorCode(dhcpErrorCode);
            return this;
        }

        public Builder setHtResult(HostnameTransResult hostnameTransResult) {
            copyOnWrite();
            ((DhcpSession) this.instance).setHtResult(hostnameTransResult);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.networkstack.metrics.DhcpSession$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
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
        switch (AnonymousClass3.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new DhcpSession();
            case 2:
                return new Builder();
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\u001e\u0002\u0004\u0000\u0003\u0004\u0001\u0004\u0004\u0002\u0005\u001e\u0006\f\u0003", new Object[]{"bitField0_", "usedFeatures_", DhcpFeature.internalGetVerifier(), "discoverCount_", "requestCount_", "conflictCount_", "errorCode_", DhcpErrorCode.internalGetVerifier(), "htResult_", HostnameTransResult.internalGetVerifier()});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DhcpSession> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DhcpSession.class) {
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

    public static DhcpSession getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
