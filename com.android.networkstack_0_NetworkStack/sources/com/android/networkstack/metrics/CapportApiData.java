package com.android.networkstack.metrics;

import com.android.networkstack.protobuf.GeneratedMessageLite;
import com.android.networkstack.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class CapportApiData extends GeneratedMessageLite<CapportApiData, Builder> implements Object {
    private static final CapportApiData DEFAULT_INSTANCE;
    public static final int HAS_PORTAL_URL_FIELD_NUMBER = 3;
    public static final int HAS_VENUE_INFO_FIELD_NUMBER = 4;
    private static volatile Parser<CapportApiData> PARSER = null;
    public static final int REMAINING_BYTES_FIELD_NUMBER = 2;
    public static final int REMAINING_TTL_SECS_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean hasPortalUrl_;
    private boolean hasVenueInfo_;
    private int remainingBytes_;
    private int remainingTtlSecs_;

    private CapportApiData() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemainingTtlSecs(int i) {
        this.bitField0_ |= 1;
        this.remainingTtlSecs_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemainingBytes(int i) {
        this.bitField0_ |= 2;
        this.remainingBytes_ = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHasPortalUrl(boolean z) {
        this.bitField0_ |= 4;
        this.hasPortalUrl_ = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHasVenueInfo(boolean z) {
        this.bitField0_ |= 8;
        this.hasVenueInfo_ = z;
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<CapportApiData, Builder> implements Object {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(CapportApiData.DEFAULT_INSTANCE);
        }

        public Builder setRemainingTtlSecs(int i) {
            copyOnWrite();
            ((CapportApiData) this.instance).setRemainingTtlSecs(i);
            return this;
        }

        public Builder setRemainingBytes(int i) {
            copyOnWrite();
            ((CapportApiData) this.instance).setRemainingBytes(i);
            return this;
        }

        public Builder setHasPortalUrl(boolean z) {
            copyOnWrite();
            ((CapportApiData) this.instance).setHasPortalUrl(z);
            return this;
        }

        public Builder setHasVenueInfo(boolean z) {
            copyOnWrite();
            ((CapportApiData) this.instance).setHasVenueInfo(z);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.networkstack.metrics.CapportApiData$1, reason: invalid class name */
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
                return new CapportApiData();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001\u0004\u0000\u0002\u0004\u0001\u0003\u0007\u0002\u0004\u0007\u0003", new Object[]{"bitField0_", "remainingTtlSecs_", "remainingBytes_", "hasPortalUrl_", "hasVenueInfo_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<CapportApiData> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (CapportApiData.class) {
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
        CapportApiData capportApiData = new CapportApiData();
        DEFAULT_INSTANCE = capportApiData;
        GeneratedMessageLite.registerDefaultInstance(CapportApiData.class, capportApiData);
    }
}
