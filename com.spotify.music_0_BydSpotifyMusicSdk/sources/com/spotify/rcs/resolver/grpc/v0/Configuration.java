package com.spotify.rcs.resolver.grpc.v0;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.spotify.rcs.admin.grpc.v0.PropertyDefinition$Identifier;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Configuration extends com.google.protobuf.i implements yj1 {
    public static final int ASSIGNED_VALUES_FIELD_NUMBER = 3;
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 1;
    private static final Configuration DEFAULT_INSTANCE;
    public static final int FETCH_TIME_MILLIS_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int POLICY_SNAPSHOT_ID_FIELD_NUMBER = 4;
    private long fetchTimeMillis_;
    private long policySnapshotId_;
    private String configurationAssignmentId_ = "";
    private v51 assignedValues_ = com.google.protobuf.i.emptyProtobufList();

    public static final class AssignedValue extends com.google.protobuf.i implements yj1 {
        public static final int BOOL_VALUE_FIELD_NUMBER = 3;
        private static final AssignedValue DEFAULT_INSTANCE;
        public static final int ENUM_VALUE_FIELD_NUMBER = 5;
        public static final int INT_VALUE_FIELD_NUMBER = 4;
        public static final int METADATA_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int PROPERTY_ID_FIELD_NUMBER = 1;
        private Metadata metadata_;
        private PropertyDefinition$Identifier propertyId_;
        private int structuredValueCase_ = 0;
        private Object structuredValue_;

        public static final class BoolValue extends com.google.protobuf.i implements yj1 {
            private static final BoolValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private boolean value_;

            static {
                BoolValue boolValue = new BoolValue();
                DEFAULT_INSTANCE = boolValue;
                com.google.protobuf.i.registerDefaultInstance(BoolValue.class, boolValue);
            }

            private BoolValue() {
            }

            public static BoolValue e() {
                return DEFAULT_INSTANCE;
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
                        return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"value_"});
                    case 3:
                        return new BoolValue();
                    case 4:
                        return new a(DEFAULT_INSTANCE);
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

            public final boolean getValue() {
                return this.value_;
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public static final class EnumValue extends com.google.protobuf.i implements yj1 {
            private static final EnumValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private String value_ = "";

            static {
                EnumValue enumValue = new EnumValue();
                DEFAULT_INSTANCE = enumValue;
                com.google.protobuf.i.registerDefaultInstance(EnumValue.class, enumValue);
            }

            private EnumValue() {
            }

            public static EnumValue e() {
                return DEFAULT_INSTANCE;
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
                        return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"value_"});
                    case 3:
                        return new EnumValue();
                    case 4:
                        return new c(DEFAULT_INSTANCE);
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

            public final String getValue() {
                return this.value_;
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public static final class IntValue extends com.google.protobuf.i implements yj1 {
            private static final IntValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private int value_;

            static {
                IntValue intValue = new IntValue();
                DEFAULT_INSTANCE = intValue;
                com.google.protobuf.i.registerDefaultInstance(IntValue.class, intValue);
            }

            private IntValue() {
            }

            public static IntValue e() {
                return DEFAULT_INSTANCE;
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
                        return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0004", new Object[]{"value_"});
                    case 3:
                        return new IntValue();
                    case 4:
                        return new d(DEFAULT_INSTANCE);
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

            public final int getValue() {
                return this.value_;
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public static final class Metadata extends com.google.protobuf.i implements yj1 {
            private static final Metadata DEFAULT_INSTANCE;
            public static final int EXTERNAL_REALM_FIELD_NUMBER = 2;
            public static final int EXTERNAL_REALM_ID_FIELD_NUMBER = 3;
            private static volatile ds1 PARSER = null;
            public static final int POLICY_ID_FIELD_NUMBER = 1;
            private long externalRealmId_;
            private String externalRealm_ = "";
            private long policyId_;

            static {
                Metadata metadata = new Metadata();
                DEFAULT_INSTANCE = metadata;
                com.google.protobuf.i.registerDefaultInstance(Metadata.class, metadata);
            }

            private Metadata() {
            }

            public static Metadata e() {
                return DEFAULT_INSTANCE;
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
                        return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0002\u0002Ȉ\u0003\u0002", new Object[]{"policyId_", "externalRealm_", "externalRealmId_"});
                    case 3:
                        return new Metadata();
                    case 4:
                        return new e(DEFAULT_INSTANCE);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (Metadata.class) {
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

            public final long f() {
                return this.externalRealmId_;
            }

            @Override // com.google.protobuf.i, p.yj1
            public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            public final long getPolicyId() {
                return this.policyId_;
            }

            @Override // com.google.protobuf.i, p.wj1
            public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }
        }

        public enum StructuredValueCase {
            BOOL_VALUE(3),
            INT_VALUE(4),
            ENUM_VALUE(5),
            STRUCTUREDVALUE_NOT_SET(0);

            private final int value;

            StructuredValueCase(int i) {
                this.value = i;
            }
        }

        static {
            AssignedValue assignedValue = new AssignedValue();
            DEFAULT_INSTANCE = assignedValue;
            com.google.protobuf.i.registerDefaultInstance(AssignedValue.class, assignedValue);
        }

        private AssignedValue() {
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
                    return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001\t\u0002\t\u0003<\u0000\u0004<\u0000\u0005<\u0000", new Object[]{"structuredValue_", "structuredValueCase_", "propertyId_", "metadata_", BoolValue.class, IntValue.class, EnumValue.class});
                case 3:
                    return new AssignedValue();
                case 4:
                    return new b(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AssignedValue.class) {
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

        public final BoolValue e() {
            return this.structuredValueCase_ == 3 ? (BoolValue) this.structuredValue_ : BoolValue.e();
        }

        public final EnumValue f() {
            return this.structuredValueCase_ == 5 ? (EnumValue) this.structuredValue_ : EnumValue.e();
        }

        public final IntValue g() {
            return this.structuredValueCase_ == 4 ? (IntValue) this.structuredValue_ : IntValue.e();
        }

        @Override // com.google.protobuf.i, p.yj1
        public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        public final Metadata h() {
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.e() : metadata;
        }

        public final PropertyDefinition$Identifier i() {
            PropertyDefinition$Identifier propertyDefinition$Identifier = this.propertyId_;
            return propertyDefinition$Identifier == null ? PropertyDefinition$Identifier.f() : propertyDefinition$Identifier;
        }

        public final StructuredValueCase j() {
            int i = this.structuredValueCase_;
            if (i == 0) {
                return StructuredValueCase.STRUCTUREDVALUE_NOT_SET;
            }
            if (i == 3) {
                return StructuredValueCase.BOOL_VALUE;
            }
            if (i == 4) {
                return StructuredValueCase.INT_VALUE;
            }
            if (i != 5) {
                return null;
            }
            return StructuredValueCase.ENUM_VALUE;
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    static {
        Configuration configuration = new Configuration();
        DEFAULT_INSTANCE = configuration;
        com.google.protobuf.i.registerDefaultInstance(Configuration.class, configuration);
    }

    private Configuration() {
    }

    public static Configuration g() {
        return DEFAULT_INSTANCE;
    }

    public static Configuration j(byte[] bArr) {
        return (Configuration) com.google.protobuf.i.parseFrom(DEFAULT_INSTANCE, bArr);
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
                return com.google.protobuf.i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0001\u0000\u0001Ȉ\u0002\u0002\u0003\u001b\u0004\u0002", new Object[]{"configurationAssignmentId_", "fetchTimeMillis_", "assignedValues_", AssignedValue.class, "policySnapshotId_"});
            case 3:
                return new Configuration();
            case 4:
                return new f(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Configuration.class) {
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

    public final v51 e() {
        return this.assignedValues_;
    }

    public final String f() {
        return this.configurationAssignmentId_;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final long h() {
        return this.fetchTimeMillis_;
    }

    public final long i() {
        return this.policySnapshotId_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
