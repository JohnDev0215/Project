package com.spotify.rcs.model.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.r1;
import p.rl0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class GranularConfiguration extends i implements GranularConfigurationOrBuilder {
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 3;
    private static final GranularConfiguration DEFAULT_INSTANCE;
    private static volatile ds1 PARSER = null;
    public static final int POLICY_SNAPSHOT_ID_FIELD_NUMBER = 4;
    public static final int PROPERTIES_FIELD_NUMBER = 1;
    public static final int RCS_FETCH_TIME_FIELD_NUMBER = 2;
    private long policySnapshotId_;
    private long rcsFetchTime_;
    private v51 properties_ = i.emptyProtobufList();
    private String configurationAssignmentId_ = "";

    /* JADX INFO: renamed from: com.spotify.rcs.model.proto.GranularConfiguration$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[GeneratedMessageLite$MethodToInvoke.values().length];
            a = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[1] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class AssignedPropertyValue extends i implements AssignedPropertyValueOrBuilder {
        public static final int BOOL_VALUE_FIELD_NUMBER = 1;
        public static final int CLIENT_ID_FIELD_NUMBER = 4;
        public static final int COMPONENT_ID_FIELD_NUMBER = 5;
        private static final AssignedPropertyValue DEFAULT_INSTANCE;
        public static final int ENUM_VALUE_FIELD_NUMBER = 3;
        public static final int GROUPID_FIELD_NUMBER = 8;
        public static final int INT_VALUE_FIELD_NUMBER = 2;
        public static final int NAME_FIELD_NUMBER = 6;
        private static volatile ds1 PARSER = null;
        public static final int PLATFORM_FIELD_NUMBER = 7;
        public static final int POLICYID_FIELD_NUMBER = 9;
        private long groupId_;
        private int platform_;
        private long policyId_;
        private Object structuredValue_;
        private int structuredValueCase_ = 0;
        private String clientId_ = "";
        private String componentId_ = "";
        private String name_ = "";

        public static final class BoolValue extends i implements BoolValueOrBuilder {
            private static final BoolValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private boolean value_;

            public static final class Builder extends h01 implements BoolValueOrBuilder {
                public /* synthetic */ Builder(int i) {
                    this();
                }

                @Override // p.h01, p.vj1
                public final /* bridge */ /* synthetic */ wj1 buildPartial() {
                    return super.buildPartial();
                }

                @Override // p.h01
                public final /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // p.h01, p.yj1
                public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                private Builder() {
                    super(BoolValue.DEFAULT_INSTANCE);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, 0, i2);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, 0, i2, rl0Var);
                }
            }

            static {
                BoolValue boolValue = new BoolValue();
                DEFAULT_INSTANCE = boolValue;
                i.registerDefaultInstance(BoolValue.class, boolValue);
            }

            private BoolValue() {
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                    case 0:
                        return (byte) 1;
                    case 1:
                        return null;
                    case 2:
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"value_"});
                    case 3:
                        return new BoolValue();
                    case 4:
                        return new Builder(0);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (BoolValue.class) {
                                try {
                                    i01Var = PARSER;
                                    if (i01Var == null) {
                                        i01Var = new i01(DEFAULT_INSTANCE);
                                        PARSER = i01Var;
                                    }
                                } finally {
                                }
                                break;
                            }
                        }
                        return i01Var;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            @Override // com.google.protobuf.i, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public interface BoolValueOrBuilder extends yj1 {
        }

        public static final class Builder extends h01 implements AssignedPropertyValueOrBuilder {
            public /* synthetic */ Builder(int i) {
                this();
            }

            @Override // p.h01, p.vj1
            public final /* bridge */ /* synthetic */ wj1 buildPartial() {
                return super.buildPartial();
            }

            @Override // p.h01
            public final /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // p.h01, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // p.h01, p.r1
            public final /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            private Builder() {
                super(AssignedPropertyValue.DEFAULT_INSTANCE);
            }

            @Override // p.h01, p.r1
            public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, 0, i2);
            }

            @Override // p.h01, p.r1
            public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, 0, i2, rl0Var);
            }
        }

        public static final class EnumValue extends i implements EnumValueOrBuilder {
            private static final EnumValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private String value_ = "";

            public static final class Builder extends h01 implements EnumValueOrBuilder {
                public /* synthetic */ Builder(int i) {
                    this();
                }

                @Override // p.h01, p.vj1
                public final /* bridge */ /* synthetic */ wj1 buildPartial() {
                    return super.buildPartial();
                }

                @Override // p.h01
                public final /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // p.h01, p.yj1
                public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                private Builder() {
                    super(EnumValue.DEFAULT_INSTANCE);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, 0, i2);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, 0, i2, rl0Var);
                }
            }

            static {
                EnumValue enumValue = new EnumValue();
                DEFAULT_INSTANCE = enumValue;
                i.registerDefaultInstance(EnumValue.class, enumValue);
            }

            private EnumValue() {
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                    case 0:
                        return (byte) 1;
                    case 1:
                        return null;
                    case 2:
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"value_"});
                    case 3:
                        return new EnumValue();
                    case 4:
                        return new Builder(0);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (EnumValue.class) {
                                try {
                                    i01Var = PARSER;
                                    if (i01Var == null) {
                                        i01Var = new i01(DEFAULT_INSTANCE);
                                        PARSER = i01Var;
                                    }
                                } finally {
                                }
                                break;
                            }
                        }
                        return i01Var;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            @Override // com.google.protobuf.i, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public interface EnumValueOrBuilder extends yj1 {
        }

        public static final class IntValue extends i implements IntValueOrBuilder {
            private static final IntValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private int value_;

            public static final class Builder extends h01 implements IntValueOrBuilder {
                public /* synthetic */ Builder(int i) {
                    this();
                }

                @Override // p.h01, p.vj1
                public final /* bridge */ /* synthetic */ wj1 buildPartial() {
                    return super.buildPartial();
                }

                @Override // p.h01
                public final /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // p.h01, p.yj1
                public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                private Builder() {
                    super(IntValue.DEFAULT_INSTANCE);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, 0, i2);
                }

                @Override // p.h01, p.r1
                public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, 0, i2, rl0Var);
                }
            }

            static {
                IntValue intValue = new IntValue();
                DEFAULT_INSTANCE = intValue;
                i.registerDefaultInstance(IntValue.class, intValue);
            }

            private IntValue() {
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                    case 0:
                        return (byte) 1;
                    case 1:
                        return null;
                    case 2:
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"value_"});
                    case 3:
                        return new IntValue();
                    case 4:
                        return new Builder(0);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (IntValue.class) {
                                try {
                                    i01Var = PARSER;
                                    if (i01Var == null) {
                                        i01Var = new i01(DEFAULT_INSTANCE);
                                        PARSER = i01Var;
                                    }
                                } finally {
                                }
                                break;
                            }
                        }
                        return i01Var;
                    default:
                        throw new UnsupportedOperationException();
                }
            }

            @Override // com.google.protobuf.i, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public interface IntValueOrBuilder extends yj1 {
        }

        public enum StructuredValueCase {
            /* JADX INFO: Fake field, exist only in values array */
            BOOL_VALUE(1),
            /* JADX INFO: Fake field, exist only in values array */
            INT_VALUE(2),
            /* JADX INFO: Fake field, exist only in values array */
            ENUM_VALUE(3),
            /* JADX INFO: Fake field, exist only in values array */
            STRUCTUREDVALUE_NOT_SET(0);

            private final int value;

            StructuredValueCase(int i) {
                this.value = i;
            }
        }

        static {
            AssignedPropertyValue assignedPropertyValue = new AssignedPropertyValue();
            DEFAULT_INSTANCE = assignedPropertyValue;
            i.registerDefaultInstance(AssignedPropertyValue.class, assignedPropertyValue);
        }

        private AssignedPropertyValue() {
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        @Override // com.google.protobuf.i
        public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
            switch (generatedMessageLite$MethodToInvoke.ordinal()) {
                case 0:
                    return (byte) 1;
                case 1:
                    return null;
                case 2:
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0001\u0000\u0001\t\t\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007\f\b\u0002\t\u0002", new Object[]{"structuredValue_", "structuredValueCase_", BoolValue.class, IntValue.class, EnumValue.class, "clientId_", "componentId_", "name_", "platform_", "groupId_", "policyId_"});
                case 3:
                    return new AssignedPropertyValue();
                case 4:
                    return new Builder(0);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AssignedPropertyValue.class) {
                            try {
                                i01Var = PARSER;
                                if (i01Var == null) {
                                    i01Var = new i01(DEFAULT_INSTANCE);
                                    PARSER = i01Var;
                                }
                            } finally {
                            }
                            break;
                        }
                    }
                    return i01Var;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        @Override // com.google.protobuf.i, p.yj1
        public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    public interface AssignedPropertyValueOrBuilder extends yj1 {
    }

    public static final class Builder extends h01 implements GranularConfigurationOrBuilder {
        public /* synthetic */ Builder(int i) {
            this();
        }

        @Override // p.h01, p.vj1
        public final /* bridge */ /* synthetic */ wj1 buildPartial() {
            return super.buildPartial();
        }

        @Override // p.h01
        public final /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // p.h01, p.yj1
        public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // p.h01, p.r1
        public final /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public final /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        private Builder() {
            super(GranularConfiguration.DEFAULT_INSTANCE);
        }

        @Override // p.h01, p.r1
        public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, 0, i2);
        }

        @Override // p.h01, p.r1
        public final /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, 0, i2, rl0Var);
        }
    }

    static {
        GranularConfiguration granularConfiguration = new GranularConfiguration();
        DEFAULT_INSTANCE = granularConfiguration;
        i.registerDefaultInstance(GranularConfiguration.class, granularConfiguration);
    }

    private GranularConfiguration() {
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001b\u0002\u0002\u0003Ȉ\u0004\u0002", new Object[]{"properties_", AssignedPropertyValue.class, "rcsFetchTime_", "configurationAssignmentId_", "policySnapshotId_"});
            case 3:
                return new GranularConfiguration();
            case 4:
                return new Builder(0);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (GranularConfiguration.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
