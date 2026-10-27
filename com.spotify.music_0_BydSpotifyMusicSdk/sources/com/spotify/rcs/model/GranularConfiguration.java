package com.spotify.rcs.model;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import p.a2;
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
    public static final int ETAG_FIELD_NUMBER = 10;
    private static volatile ds1 PARSER = null;
    public static final int POLICY_SNAPSHOT_ID_FIELD_NUMBER = 4;
    public static final int PROPERTIES_FIELD_NUMBER = 1;
    public static final int RCS_FETCH_TIME_FIELD_NUMBER = 2;
    private int bitField0_;
    private long policySnapshotId_;
    private long rcsFetchTime_;
    private v51 properties_ = i.emptyProtobufList();
    private String configurationAssignmentId_ = "";
    private String etag_ = "";

    /* JADX INFO: renamed from: com.spotify.rcs.model.GranularConfiguration$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite$MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[1] = 7;
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
                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                public /* bridge */ /* synthetic */ wj1 build() {
                    return super.build();
                }

                @Override // p.h01, p.vj1
                public /* bridge */ /* synthetic */ wj1 buildPartial() {
                    return super.buildPartial();
                }

                public /* bridge */ /* synthetic */ vj1 clear() {
                    return super.clear();
                }

                public Builder clearValue() {
                    copyOnWrite();
                    ((BoolValue) this.instance).clearValue();
                    return this;
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // p.h01, p.yj1
                public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.BoolValueOrBuilder
                public boolean getValue() {
                    return ((BoolValue) this.instance).getValue();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public Builder setValue(boolean z) {
                    copyOnWrite();
                    ((BoolValue) this.instance).setValue(z);
                    return this;
                }

                private Builder() {
                    super(BoolValue.DEFAULT_INSTANCE);
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ r1 clone() {
                    return super.clone();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ vj1 clone() {
                    return super.clone();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
                    return super.mergeFrom(byteString);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
                    return super.mergeFrom(byteString, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
                    return super.mergeFrom(inputStream);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
                    return super.mergeFrom(inputStream, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
                    return super.mergeFrom(auVar);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m331mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                    return super.mergeFrom(wj1Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                    return super.mergeFrom(bArr);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m332mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m333mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                    return super.mergeFrom(bArr, rl0Var);
                }
            }

            static {
                BoolValue boolValue = new BoolValue();
                DEFAULT_INSTANCE = boolValue;
                i.registerDefaultInstance(BoolValue.class, boolValue);
            }

            private BoolValue() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearValue() {
                this.value_ = false;
            }

            public static BoolValue getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static BoolValue parseDelimitedFrom(InputStream inputStream) {
                return (BoolValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BoolValue parseFrom(ByteBuffer byteBuffer) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setValue(boolean z) {
                this.value_ = z;
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = null;
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
                        return new Builder(anonymousClass1);
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
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.BoolValueOrBuilder
            public boolean getValue() {
                return this.value_;
            }

            @Override // com.google.protobuf.i, p.wj1
            public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }

            public /* bridge */ /* synthetic */ vj1 toBuilder() {
                return super.toBuilder();
            }

            public static Builder newBuilder(BoolValue boolValue) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(boolValue);
            }

            public static BoolValue parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
                return (BoolValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static BoolValue parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
            }

            public static BoolValue parseFrom(ByteString byteString) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static BoolValue parseFrom(ByteString byteString, rl0 rl0Var) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
            }

            public static BoolValue parseFrom(byte[] bArr) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static BoolValue parseFrom(byte[] bArr, rl0 rl0Var) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
            }

            public static BoolValue parseFrom(InputStream inputStream) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static BoolValue parseFrom(InputStream inputStream, rl0 rl0Var) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static BoolValue parseFrom(au auVar) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, auVar);
            }

            public static BoolValue parseFrom(au auVar, rl0 rl0Var) {
                return (BoolValue) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
            }
        }

        public interface BoolValueOrBuilder extends yj1 {
            @Override // p.yj1
            /* synthetic */ wj1 getDefaultInstanceForType();

            boolean getValue();

            @Override // p.yj1
            /* synthetic */ boolean isInitialized();
        }

        public static final class Builder extends h01 implements AssignedPropertyValueOrBuilder {
            public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            public /* bridge */ /* synthetic */ wj1 build() {
                return super.build();
            }

            @Override // p.h01, p.vj1
            public /* bridge */ /* synthetic */ wj1 buildPartial() {
                return super.buildPartial();
            }

            public /* bridge */ /* synthetic */ vj1 clear() {
                return super.clear();
            }

            public Builder clearBoolValue() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearBoolValue();
                return this;
            }

            public Builder clearClientId() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearClientId();
                return this;
            }

            public Builder clearComponentId() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearComponentId();
                return this;
            }

            public Builder clearEnumValue() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearEnumValue();
                return this;
            }

            public Builder clearGroupId() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearGroupId();
                return this;
            }

            public Builder clearIntValue() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearIntValue();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearName();
                return this;
            }

            public Builder clearPlatform() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearPlatform();
                return this;
            }

            public Builder clearPolicyId() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearPolicyId();
                return this;
            }

            public Builder clearStructuredValue() {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).clearStructuredValue();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public BoolValue getBoolValue() {
                return ((AssignedPropertyValue) this.instance).getBoolValue();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public String getClientId() {
                return ((AssignedPropertyValue) this.instance).getClientId();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public ByteString getClientIdBytes() {
                return ((AssignedPropertyValue) this.instance).getClientIdBytes();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public String getComponentId() {
                return ((AssignedPropertyValue) this.instance).getComponentId();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public ByteString getComponentIdBytes() {
                return ((AssignedPropertyValue) this.instance).getComponentIdBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public EnumValue getEnumValue() {
                return ((AssignedPropertyValue) this.instance).getEnumValue();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public long getGroupId() {
                return ((AssignedPropertyValue) this.instance).getGroupId();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public IntValue getIntValue() {
                return ((AssignedPropertyValue) this.instance).getIntValue();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public String getName() {
                return ((AssignedPropertyValue) this.instance).getName();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public ByteString getNameBytes() {
                return ((AssignedPropertyValue) this.instance).getNameBytes();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public Platform getPlatform() {
                return ((AssignedPropertyValue) this.instance).getPlatform();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public int getPlatformValue() {
                return ((AssignedPropertyValue) this.instance).getPlatformValue();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public long getPolicyId() {
                return ((AssignedPropertyValue) this.instance).getPolicyId();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public StructuredValueCase getStructuredValueCase() {
                return ((AssignedPropertyValue) this.instance).getStructuredValueCase();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public boolean hasBoolValue() {
                return ((AssignedPropertyValue) this.instance).hasBoolValue();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public boolean hasEnumValue() {
                return ((AssignedPropertyValue) this.instance).hasEnumValue();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
            public boolean hasIntValue() {
                return ((AssignedPropertyValue) this.instance).hasIntValue();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeBoolValue(BoolValue boolValue) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).mergeBoolValue(boolValue);
                return this;
            }

            public Builder mergeEnumValue(EnumValue enumValue) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).mergeEnumValue(enumValue);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeIntValue(IntValue intValue) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).mergeIntValue(intValue);
                return this;
            }

            public Builder setBoolValue(BoolValue boolValue) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setBoolValue(boolValue);
                return this;
            }

            public Builder setClientId(String str) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setClientId(str);
                return this;
            }

            public Builder setClientIdBytes(ByteString byteString) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setClientIdBytes(byteString);
                return this;
            }

            public Builder setComponentId(String str) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setComponentId(str);
                return this;
            }

            public Builder setComponentIdBytes(ByteString byteString) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setComponentIdBytes(byteString);
                return this;
            }

            public Builder setEnumValue(EnumValue enumValue) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setEnumValue(enumValue);
                return this;
            }

            public Builder setGroupId(long j) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setGroupId(j);
                return this;
            }

            public Builder setIntValue(IntValue intValue) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setIntValue(intValue);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setPlatform(Platform platform) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setPlatform(platform);
                return this;
            }

            public Builder setPlatformValue(int i) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setPlatformValue(i);
                return this;
            }

            public Builder setPolicyId(long j) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setPolicyId(j);
                return this;
            }

            private Builder() {
                super(AssignedPropertyValue.DEFAULT_INSTANCE);
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ r1 clone() {
                return super.clone();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ vj1 clone() {
                return super.clone();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public Builder setBoolValue(BoolValue.Builder builder) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setBoolValue((BoolValue) builder.build());
                return this;
            }

            public Builder setEnumValue(EnumValue.Builder builder) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setEnumValue((EnumValue) builder.build());
                return this;
            }

            public Builder setIntValue(IntValue.Builder builder) {
                copyOnWrite();
                ((AssignedPropertyValue) this.instance).setIntValue((IntValue) builder.build());
                return this;
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
                return super.mergeFrom(byteString);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
                return super.mergeFrom(byteString, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
                return super.mergeFrom(inputStream);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
                return super.mergeFrom(inputStream, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
                return super.mergeFrom(auVar);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m334mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m335mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m336mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public static final class EnumValue extends i implements EnumValueOrBuilder {
            private static final EnumValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private String value_ = "";

            public static final class Builder extends h01 implements EnumValueOrBuilder {
                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                public /* bridge */ /* synthetic */ wj1 build() {
                    return super.build();
                }

                @Override // p.h01, p.vj1
                public /* bridge */ /* synthetic */ wj1 buildPartial() {
                    return super.buildPartial();
                }

                public /* bridge */ /* synthetic */ vj1 clear() {
                    return super.clear();
                }

                public Builder clearValue() {
                    copyOnWrite();
                    ((EnumValue) this.instance).clearValue();
                    return this;
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // p.h01, p.yj1
                public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.EnumValueOrBuilder
                public String getValue() {
                    return ((EnumValue) this.instance).getValue();
                }

                @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.EnumValueOrBuilder
                public ByteString getValueBytes() {
                    return ((EnumValue) this.instance).getValueBytes();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public Builder setValue(String str) {
                    copyOnWrite();
                    ((EnumValue) this.instance).setValue(str);
                    return this;
                }

                public Builder setValueBytes(ByteString byteString) {
                    copyOnWrite();
                    ((EnumValue) this.instance).setValueBytes(byteString);
                    return this;
                }

                private Builder() {
                    super(EnumValue.DEFAULT_INSTANCE);
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ r1 clone() {
                    return super.clone();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ vj1 clone() {
                    return super.clone();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
                    return super.mergeFrom(byteString);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
                    return super.mergeFrom(byteString, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
                    return super.mergeFrom(inputStream);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
                    return super.mergeFrom(inputStream, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
                    return super.mergeFrom(auVar);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m337mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                    return super.mergeFrom(wj1Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                    return super.mergeFrom(bArr);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m338mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m339mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                    return super.mergeFrom(bArr, rl0Var);
                }
            }

            static {
                EnumValue enumValue = new EnumValue();
                DEFAULT_INSTANCE = enumValue;
                i.registerDefaultInstance(EnumValue.class, enumValue);
            }

            private EnumValue() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearValue() {
                this.value_ = getDefaultInstance().getValue();
            }

            public static EnumValue getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static EnumValue parseDelimitedFrom(InputStream inputStream) {
                return (EnumValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static EnumValue parseFrom(ByteBuffer byteBuffer) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setValue(String str) {
                str.getClass();
                this.value_ = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setValueBytes(ByteString byteString) {
                a.checkByteStringIsUtf8(byteString);
                this.value_ = byteString.s();
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = null;
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
                        return new Builder(anonymousClass1);
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
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.EnumValueOrBuilder
            public String getValue() {
                return this.value_;
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.EnumValueOrBuilder
            public ByteString getValueBytes() {
                return ByteString.d(this.value_);
            }

            @Override // com.google.protobuf.i, p.wj1
            public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }

            public /* bridge */ /* synthetic */ vj1 toBuilder() {
                return super.toBuilder();
            }

            public static Builder newBuilder(EnumValue enumValue) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(enumValue);
            }

            public static EnumValue parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
                return (EnumValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static EnumValue parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
            }

            public static EnumValue parseFrom(ByteString byteString) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static EnumValue parseFrom(ByteString byteString, rl0 rl0Var) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
            }

            public static EnumValue parseFrom(byte[] bArr) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static EnumValue parseFrom(byte[] bArr, rl0 rl0Var) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
            }

            public static EnumValue parseFrom(InputStream inputStream) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static EnumValue parseFrom(InputStream inputStream, rl0 rl0Var) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static EnumValue parseFrom(au auVar) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, auVar);
            }

            public static EnumValue parseFrom(au auVar, rl0 rl0Var) {
                return (EnumValue) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
            }
        }

        public interface EnumValueOrBuilder extends yj1 {
            @Override // p.yj1
            /* synthetic */ wj1 getDefaultInstanceForType();

            String getValue();

            ByteString getValueBytes();

            @Override // p.yj1
            /* synthetic */ boolean isInitialized();
        }

        public static final class IntValue extends i implements IntValueOrBuilder {
            private static final IntValue DEFAULT_INSTANCE;
            private static volatile ds1 PARSER = null;
            public static final int VALUE_FIELD_NUMBER = 1;
            private int value_;

            public static final class Builder extends h01 implements IntValueOrBuilder {
                public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                    this();
                }

                public /* bridge */ /* synthetic */ wj1 build() {
                    return super.build();
                }

                @Override // p.h01, p.vj1
                public /* bridge */ /* synthetic */ wj1 buildPartial() {
                    return super.buildPartial();
                }

                public /* bridge */ /* synthetic */ vj1 clear() {
                    return super.clear();
                }

                public Builder clearValue() {
                    copyOnWrite();
                    ((IntValue) this.instance).clearValue();
                    return this;
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // p.h01, p.yj1
                public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.IntValueOrBuilder
                public int getValue() {
                    return ((IntValue) this.instance).getValue();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public Builder setValue(int i) {
                    copyOnWrite();
                    ((IntValue) this.instance).setValue(i);
                    return this;
                }

                private Builder() {
                    super(IntValue.DEFAULT_INSTANCE);
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ r1 clone() {
                    return super.clone();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ vj1 clone() {
                    return super.clone();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
                    return super.mergeFrom(byteString);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
                    return super.mergeFrom(byteString, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
                    return super.mergeFrom(inputStream);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
                    return super.mergeFrom(inputStream, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
                    return super.mergeFrom(auVar);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m340mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                    return super.mergeFrom(wj1Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                    return super.mergeFrom(bArr);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m341mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m342mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                    return super.mergeFrom(bArr, rl0Var);
                }
            }

            static {
                IntValue intValue = new IntValue();
                DEFAULT_INSTANCE = intValue;
                i.registerDefaultInstance(IntValue.class, intValue);
            }

            private IntValue() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearValue() {
                this.value_ = 0;
            }

            public static IntValue getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static IntValue parseDelimitedFrom(InputStream inputStream) {
                return (IntValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static IntValue parseFrom(ByteBuffer byteBuffer) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setValue(int i) {
                this.value_ = i;
            }

            @Override // com.google.protobuf.i
            public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
                AnonymousClass1 anonymousClass1 = null;
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
                        return new Builder(anonymousClass1);
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
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValue.IntValueOrBuilder
            public int getValue() {
                return this.value_;
            }

            @Override // com.google.protobuf.i, p.wj1
            public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }

            public /* bridge */ /* synthetic */ vj1 toBuilder() {
                return super.toBuilder();
            }

            public static Builder newBuilder(IntValue intValue) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(intValue);
            }

            public static IntValue parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
                return (IntValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static IntValue parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
            }

            public static IntValue parseFrom(ByteString byteString) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static IntValue parseFrom(ByteString byteString, rl0 rl0Var) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
            }

            public static IntValue parseFrom(byte[] bArr) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static IntValue parseFrom(byte[] bArr, rl0 rl0Var) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
            }

            public static IntValue parseFrom(InputStream inputStream) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static IntValue parseFrom(InputStream inputStream, rl0 rl0Var) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static IntValue parseFrom(au auVar) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, auVar);
            }

            public static IntValue parseFrom(au auVar, rl0 rl0Var) {
                return (IntValue) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
            }
        }

        public interface IntValueOrBuilder extends yj1 {
            @Override // p.yj1
            /* synthetic */ wj1 getDefaultInstanceForType();

            int getValue();

            @Override // p.yj1
            /* synthetic */ boolean isInitialized();
        }

        public enum StructuredValueCase {
            BOOL_VALUE(1),
            INT_VALUE(2),
            ENUM_VALUE(3),
            STRUCTUREDVALUE_NOT_SET(0);

            private final int value;

            StructuredValueCase(int i) {
                this.value = i;
            }

            public static StructuredValueCase forNumber(int i) {
                if (i == 0) {
                    return STRUCTUREDVALUE_NOT_SET;
                }
                if (i == 1) {
                    return BOOL_VALUE;
                }
                if (i == 2) {
                    return INT_VALUE;
                }
                if (i != 3) {
                    return null;
                }
                return ENUM_VALUE;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static StructuredValueCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            AssignedPropertyValue assignedPropertyValue = new AssignedPropertyValue();
            DEFAULT_INSTANCE = assignedPropertyValue;
            i.registerDefaultInstance(AssignedPropertyValue.class, assignedPropertyValue);
        }

        private AssignedPropertyValue() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBoolValue() {
            if (this.structuredValueCase_ == 1) {
                this.structuredValueCase_ = 0;
                this.structuredValue_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearClientId() {
            this.clientId_ = getDefaultInstance().getClientId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearComponentId() {
            this.componentId_ = getDefaultInstance().getComponentId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEnumValue() {
            if (this.structuredValueCase_ == 3) {
                this.structuredValueCase_ = 0;
                this.structuredValue_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGroupId() {
            this.groupId_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIntValue() {
            if (this.structuredValueCase_ == 2) {
                this.structuredValueCase_ = 0;
                this.structuredValue_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPlatform() {
            this.platform_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPolicyId() {
            this.policyId_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStructuredValue() {
            this.structuredValueCase_ = 0;
            this.structuredValue_ = null;
        }

        public static AssignedPropertyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeBoolValue(BoolValue boolValue) {
            boolValue.getClass();
            if (this.structuredValueCase_ != 1 || this.structuredValue_ == BoolValue.getDefaultInstance()) {
                this.structuredValue_ = boolValue;
            } else {
                this.structuredValue_ = ((BoolValue.Builder) BoolValue.newBuilder((BoolValue) this.structuredValue_).mergeFrom((i) boolValue)).buildPartial();
            }
            this.structuredValueCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeEnumValue(EnumValue enumValue) {
            enumValue.getClass();
            if (this.structuredValueCase_ != 3 || this.structuredValue_ == EnumValue.getDefaultInstance()) {
                this.structuredValue_ = enumValue;
            } else {
                this.structuredValue_ = ((EnumValue.Builder) EnumValue.newBuilder((EnumValue) this.structuredValue_).mergeFrom((i) enumValue)).buildPartial();
            }
            this.structuredValueCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeIntValue(IntValue intValue) {
            intValue.getClass();
            if (this.structuredValueCase_ != 2 || this.structuredValue_ == IntValue.getDefaultInstance()) {
                this.structuredValue_ = intValue;
            } else {
                this.structuredValue_ = ((IntValue.Builder) IntValue.newBuilder((IntValue) this.structuredValue_).mergeFrom((i) intValue)).buildPartial();
            }
            this.structuredValueCase_ = 2;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AssignedPropertyValue parseDelimitedFrom(InputStream inputStream) {
            return (AssignedPropertyValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AssignedPropertyValue parseFrom(ByteBuffer byteBuffer) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBoolValue(BoolValue boolValue) {
            boolValue.getClass();
            this.structuredValue_ = boolValue;
            this.structuredValueCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setClientId(String str) {
            str.getClass();
            this.clientId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setClientIdBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.clientId_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponentId(String str) {
            str.getClass();
            this.componentId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setComponentIdBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.componentId_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEnumValue(EnumValue enumValue) {
            enumValue.getClass();
            this.structuredValue_ = enumValue;
            this.structuredValueCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGroupId(long j) {
            this.groupId_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIntValue(IntValue intValue) {
            intValue.getClass();
            this.structuredValue_ = intValue;
            this.structuredValueCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.name_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPlatform(Platform platform) {
            this.platform_ = platform.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPlatformValue(int i) {
            this.platform_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPolicyId(long j) {
            this.policyId_ = j;
        }

        @Override // com.google.protobuf.i
        public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = null;
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
                    return new Builder(anonymousClass1);
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

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public BoolValue getBoolValue() {
            return this.structuredValueCase_ == 1 ? (BoolValue) this.structuredValue_ : BoolValue.getDefaultInstance();
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public String getClientId() {
            return this.clientId_;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public ByteString getClientIdBytes() {
            return ByteString.d(this.clientId_);
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public String getComponentId() {
            return this.componentId_;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public ByteString getComponentIdBytes() {
            return ByteString.d(this.componentId_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public EnumValue getEnumValue() {
            return this.structuredValueCase_ == 3 ? (EnumValue) this.structuredValue_ : EnumValue.getDefaultInstance();
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public long getGroupId() {
            return this.groupId_;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public IntValue getIntValue() {
            return this.structuredValueCase_ == 2 ? (IntValue) this.structuredValue_ : IntValue.getDefaultInstance();
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public ByteString getNameBytes() {
            return ByteString.d(this.name_);
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public Platform getPlatform() {
            Platform platformA = Platform.a(this.platform_);
            return platformA == null ? Platform.UNRECOGNIZED : platformA;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public int getPlatformValue() {
            return this.platform_;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public long getPolicyId() {
            return this.policyId_;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public StructuredValueCase getStructuredValueCase() {
            return StructuredValueCase.forNumber(this.structuredValueCase_);
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public boolean hasBoolValue() {
            return this.structuredValueCase_ == 1;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public boolean hasEnumValue() {
            return this.structuredValueCase_ == 3;
        }

        @Override // com.spotify.rcs.model.GranularConfiguration.AssignedPropertyValueOrBuilder
        public boolean hasIntValue() {
            return this.structuredValueCase_ == 2;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AssignedPropertyValue assignedPropertyValue) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(assignedPropertyValue);
        }

        public static AssignedPropertyValue parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AssignedPropertyValue) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AssignedPropertyValue parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AssignedPropertyValue parseFrom(ByteString byteString) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AssignedPropertyValue parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AssignedPropertyValue parseFrom(byte[] bArr) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AssignedPropertyValue parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AssignedPropertyValue parseFrom(InputStream inputStream) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AssignedPropertyValue parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AssignedPropertyValue parseFrom(au auVar) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AssignedPropertyValue parseFrom(au auVar, rl0 rl0Var) {
            return (AssignedPropertyValue) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AssignedPropertyValueOrBuilder extends yj1 {
        AssignedPropertyValue.BoolValue getBoolValue();

        String getClientId();

        ByteString getClientIdBytes();

        String getComponentId();

        ByteString getComponentIdBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        AssignedPropertyValue.EnumValue getEnumValue();

        long getGroupId();

        AssignedPropertyValue.IntValue getIntValue();

        String getName();

        ByteString getNameBytes();

        Platform getPlatform();

        int getPlatformValue();

        long getPolicyId();

        AssignedPropertyValue.StructuredValueCase getStructuredValueCase();

        boolean hasBoolValue();

        boolean hasEnumValue();

        boolean hasIntValue();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class Builder extends h01 implements GranularConfigurationOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public Builder addAllProperties(Iterable<? extends AssignedPropertyValue> iterable) {
            copyOnWrite();
            GranularConfiguration.i((GranularConfiguration) this.instance, iterable);
            return this;
        }

        public Builder addProperties(AssignedPropertyValue assignedPropertyValue) {
            copyOnWrite();
            GranularConfiguration.g((GranularConfiguration) this.instance, assignedPropertyValue);
            return this;
        }

        public /* bridge */ /* synthetic */ wj1 build() {
            return super.build();
        }

        @Override // p.h01, p.vj1
        public /* bridge */ /* synthetic */ wj1 buildPartial() {
            return super.buildPartial();
        }

        public /* bridge */ /* synthetic */ vj1 clear() {
            return super.clear();
        }

        public Builder clearConfigurationAssignmentId() {
            copyOnWrite();
            GranularConfiguration.o((GranularConfiguration) this.instance);
            return this;
        }

        public Builder clearEtag() {
            copyOnWrite();
            GranularConfiguration.r((GranularConfiguration) this.instance);
            return this;
        }

        public Builder clearPolicySnapshotId() {
            copyOnWrite();
            GranularConfiguration.u((GranularConfiguration) this.instance);
            return this;
        }

        public Builder clearProperties() {
            copyOnWrite();
            GranularConfiguration.j((GranularConfiguration) this.instance);
            return this;
        }

        public Builder clearRcsFetchTime() {
            copyOnWrite();
            GranularConfiguration.m((GranularConfiguration) this.instance);
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        public String getConfigurationAssignmentId() {
            return ((GranularConfiguration) this.instance).w();
        }

        public ByteString getConfigurationAssignmentIdBytes() {
            return ((GranularConfiguration) this.instance).x();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        public String getEtag() {
            return ((GranularConfiguration) this.instance).y();
        }

        public ByteString getEtagBytes() {
            return ((GranularConfiguration) this.instance).z();
        }

        public long getPolicySnapshotId() {
            return ((GranularConfiguration) this.instance).A();
        }

        public AssignedPropertyValue getProperties(int i) {
            return ((GranularConfiguration) this.instance).B(i);
        }

        public int getPropertiesCount() {
            return ((GranularConfiguration) this.instance).C();
        }

        public List<AssignedPropertyValue> getPropertiesList() {
            return Collections.unmodifiableList(((GranularConfiguration) this.instance).D());
        }

        public long getRcsFetchTime() {
            return ((GranularConfiguration) this.instance).E();
        }

        public boolean hasEtag() {
            return ((GranularConfiguration) this.instance).F();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder removeProperties(int i) {
            copyOnWrite();
            GranularConfiguration.k((GranularConfiguration) this.instance, i);
            return this;
        }

        public Builder setConfigurationAssignmentId(String str) {
            copyOnWrite();
            GranularConfiguration.n((GranularConfiguration) this.instance, str);
            return this;
        }

        public Builder setConfigurationAssignmentIdBytes(ByteString byteString) {
            copyOnWrite();
            GranularConfiguration.p((GranularConfiguration) this.instance, byteString);
            return this;
        }

        public Builder setEtag(String str) {
            copyOnWrite();
            GranularConfiguration.q((GranularConfiguration) this.instance, str);
            return this;
        }

        public Builder setEtagBytes(ByteString byteString) {
            copyOnWrite();
            GranularConfiguration.s((GranularConfiguration) this.instance, byteString);
            return this;
        }

        public Builder setPolicySnapshotId(long j) {
            copyOnWrite();
            GranularConfiguration.t((GranularConfiguration) this.instance, j);
            return this;
        }

        public Builder setProperties(int i, AssignedPropertyValue assignedPropertyValue) {
            copyOnWrite();
            GranularConfiguration.f((GranularConfiguration) this.instance, i, assignedPropertyValue);
            return this;
        }

        public Builder setRcsFetchTime(long j) {
            copyOnWrite();
            GranularConfiguration.l((GranularConfiguration) this.instance, j);
            return this;
        }

        private Builder() {
            super(GranularConfiguration.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        public Builder addProperties(int i, AssignedPropertyValue assignedPropertyValue) {
            copyOnWrite();
            GranularConfiguration.h((GranularConfiguration) this.instance, i, assignedPropertyValue);
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ vj1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public Builder setProperties(int i, AssignedPropertyValue.Builder builder) {
            copyOnWrite();
            GranularConfiguration.f((GranularConfiguration) this.instance, i, (AssignedPropertyValue) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public Builder addProperties(AssignedPropertyValue.Builder builder) {
            copyOnWrite();
            GranularConfiguration.g((GranularConfiguration) this.instance, (AssignedPropertyValue) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public Builder addProperties(int i, AssignedPropertyValue.Builder builder) {
            copyOnWrite();
            GranularConfiguration.h((GranularConfiguration) this.instance, i, (AssignedPropertyValue) builder.build());
            return this;
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m343mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m344mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m345mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    static {
        GranularConfiguration granularConfiguration = new GranularConfiguration();
        DEFAULT_INSTANCE = granularConfiguration;
        i.registerDefaultInstance(GranularConfiguration.class, granularConfiguration);
    }

    private GranularConfiguration() {
    }

    public static Builder G() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static GranularConfiguration H(byte[] bArr) {
        return (GranularConfiguration) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static void f(GranularConfiguration granularConfiguration, int i, AssignedPropertyValue assignedPropertyValue) {
        granularConfiguration.getClass();
        assignedPropertyValue.getClass();
        granularConfiguration.v();
        granularConfiguration.properties_.set(i, assignedPropertyValue);
    }

    public static void g(GranularConfiguration granularConfiguration, AssignedPropertyValue assignedPropertyValue) {
        granularConfiguration.getClass();
        assignedPropertyValue.getClass();
        granularConfiguration.v();
        granularConfiguration.properties_.add(assignedPropertyValue);
    }

    public static void h(GranularConfiguration granularConfiguration, int i, AssignedPropertyValue assignedPropertyValue) {
        granularConfiguration.getClass();
        assignedPropertyValue.getClass();
        granularConfiguration.v();
        granularConfiguration.properties_.add(i, assignedPropertyValue);
    }

    public static void i(GranularConfiguration granularConfiguration, Iterable iterable) {
        granularConfiguration.v();
        a.addAll(iterable, (List) granularConfiguration.properties_);
    }

    public static void j(GranularConfiguration granularConfiguration) {
        granularConfiguration.getClass();
        granularConfiguration.properties_ = i.emptyProtobufList();
    }

    public static void k(GranularConfiguration granularConfiguration, int i) {
        granularConfiguration.v();
        granularConfiguration.properties_.remove(i);
    }

    public static void l(GranularConfiguration granularConfiguration, long j) {
        granularConfiguration.rcsFetchTime_ = j;
    }

    public static void m(GranularConfiguration granularConfiguration) {
        granularConfiguration.rcsFetchTime_ = 0L;
    }

    public static void n(GranularConfiguration granularConfiguration, String str) {
        granularConfiguration.getClass();
        str.getClass();
        granularConfiguration.configurationAssignmentId_ = str;
    }

    public static void o(GranularConfiguration granularConfiguration) {
        granularConfiguration.getClass();
        granularConfiguration.configurationAssignmentId_ = DEFAULT_INSTANCE.configurationAssignmentId_;
    }

    public static void p(GranularConfiguration granularConfiguration, ByteString byteString) {
        granularConfiguration.getClass();
        a.checkByteStringIsUtf8(byteString);
        granularConfiguration.configurationAssignmentId_ = byteString.s();
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(GranularConfiguration granularConfiguration, String str) {
        granularConfiguration.getClass();
        str.getClass();
        granularConfiguration.bitField0_ |= 1;
        granularConfiguration.etag_ = str;
    }

    public static void r(GranularConfiguration granularConfiguration) {
        granularConfiguration.bitField0_ &= -2;
        granularConfiguration.etag_ = DEFAULT_INSTANCE.etag_;
    }

    public static void s(GranularConfiguration granularConfiguration, ByteString byteString) {
        granularConfiguration.getClass();
        a.checkByteStringIsUtf8(byteString);
        granularConfiguration.etag_ = byteString.s();
        granularConfiguration.bitField0_ |= 1;
    }

    public static void t(GranularConfiguration granularConfiguration, long j) {
        granularConfiguration.policySnapshotId_ = j;
    }

    public static void u(GranularConfiguration granularConfiguration) {
        granularConfiguration.policySnapshotId_ = 0L;
    }

    public final long A() {
        return this.policySnapshotId_;
    }

    public final AssignedPropertyValue B(int i) {
        return (AssignedPropertyValue) this.properties_.get(i);
    }

    public final int C() {
        return this.properties_.size();
    }

    public final List D() {
        return this.properties_;
    }

    public final long E() {
        return this.rcsFetchTime_;
    }

    public final boolean F() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0000\u0001\u0001\n\u0005\u0000\u0001\u0000\u0001\u001b\u0002\u0002\u0003Ȉ\u0004\u0002\nለ\u0000", new Object[]{"bitField0_", "properties_", AssignedPropertyValue.class, "rcsFetchTime_", "configurationAssignmentId_", "policySnapshotId_", "etag_"});
            case 3:
                return new GranularConfiguration();
            case 4:
                return new Builder(anonymousClass1);
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

    public final void v() {
        v51 v51Var = this.properties_;
        if (((a2) v51Var).c) {
            return;
        }
        this.properties_ = i.mutableCopy(v51Var);
    }

    public final String w() {
        return this.configurationAssignmentId_;
    }

    public final ByteString x() {
        return ByteString.d(this.configurationAssignmentId_);
    }

    public final String y() {
        return this.etag_;
    }

    public final ByteString z() {
        return ByteString.d(this.etag_);
    }
}
