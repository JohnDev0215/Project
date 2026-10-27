package com.android.networkstack.metrics;

import com.android.networkstack.metrics.ProbeEvent;
import com.android.networkstack.protobuf.GeneratedMessageLite;
import com.android.networkstack.protobuf.Internal;
import com.android.networkstack.protobuf.Parser;

/* JADX INFO: loaded from: classes.dex */
public final class ProbeEvents extends GeneratedMessageLite<ProbeEvents, Builder> implements Object {
    private static final ProbeEvents DEFAULT_INSTANCE;
    private static volatile Parser<ProbeEvents> PARSER = null;
    public static final int PROBE_EVENT_FIELD_NUMBER = 1;
    private Internal.ProtobufList<ProbeEvent> probeEvent_ = GeneratedMessageLite.emptyProtobufList();

    private ProbeEvents() {
    }

    public int getProbeEventCount() {
        return this.probeEvent_.size();
    }

    private void ensureProbeEventIsMutable() {
        if (this.probeEvent_.isModifiable()) {
            return;
        }
        this.probeEvent_ = GeneratedMessageLite.mutableCopy(this.probeEvent_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addProbeEvent(ProbeEvent.Builder builder) {
        ensureProbeEventIsMutable();
        this.probeEvent_.add(builder.build());
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProbeEvents, Builder> implements Object {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ProbeEvents.DEFAULT_INSTANCE);
        }

        public int getProbeEventCount() {
            return ((ProbeEvents) this.instance).getProbeEventCount();
        }

        public Builder addProbeEvent(ProbeEvent.Builder builder) {
            copyOnWrite();
            ((ProbeEvents) this.instance).addProbeEvent(builder);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.networkstack.metrics.ProbeEvents$1, reason: invalid class name */
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
                return new ProbeEvents();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return GeneratedMessageLite.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"probeEvent_", ProbeEvent.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ProbeEvents> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ProbeEvents.class) {
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
        ProbeEvents probeEvents = new ProbeEvents();
        DEFAULT_INSTANCE = probeEvents;
        GeneratedMessageLite.registerDefaultInstance(ProbeEvents.class, probeEvents);
    }

    public static ProbeEvents getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
