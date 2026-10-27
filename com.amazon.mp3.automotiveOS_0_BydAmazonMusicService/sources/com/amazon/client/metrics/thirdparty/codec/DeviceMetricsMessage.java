package com.amazon.client.metrics.thirdparty.codec;

import com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator.RemoteConfigurationAndroidClientContextDecorator;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class DeviceMetricsMessage {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_metrics_DataPointMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_DataPointMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_metrics_KeyValue_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_KeyValue_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_metrics_MetricBatchMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_MetricBatchMessage_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_metrics_MetricEntryMessage_descriptor;
    private static final GeneratedMessageV3.FieldAccessorTable internal_static_metrics_MetricEntryMessage_fieldAccessorTable;

    public static final class DataPointMessage extends GeneratedMessageV3 implements DataPointMessageOrBuilder {
        private static final DataPointMessage DEFAULT_INSTANCE = new DataPointMessage();

        @Deprecated
        public static final Parser<DataPointMessage> PARSER = new AbstractParser<DataPointMessage>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.1
            @Override // com.google.protobuf.Parser
            public DataPointMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new DataPointMessage(codedInputStream, extensionRegistryLite);
            }
        };
        private int bitField0_;
        private byte memoizedIsInitialized;
        private volatile Object name_;
        private int sampleSize_;
        private int type_;
        private volatile Object value_;

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements DataPointMessageOrBuilder {
            private int bitField0_;
            private Object name_;
            private int sampleSize_;
            private int type_;
            private Object value_;

            private Builder() {
                this.name_ = "";
                this.value_ = "";
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.name_ = "";
                this.value_ = "";
                this.type_ = 0;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DataPointMessage build() {
                DataPointMessage dataPointMessageBuildPartial = buildPartial();
                if (dataPointMessageBuildPartial.isInitialized()) {
                    return dataPointMessageBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) dataPointMessageBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public DataPointMessage buildPartial() {
                DataPointMessage dataPointMessage = new DataPointMessage(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                dataPointMessage.name_ = this.name_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                dataPointMessage.value_ = this.value_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                dataPointMessage.sampleSize_ = this.sampleSize_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                dataPointMessage.type_ = this.type_;
                dataPointMessage.bitField0_ = i2;
                onBuilt();
                return dataPointMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo76clone() {
                return (Builder) super.mo76clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public DataPointMessage getDefaultInstanceForType() {
                return DataPointMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_descriptor;
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
            public int getSampleSize() {
                return this.sampleSize_;
            }

            public boolean hasName() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasSampleSize() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasType() {
                return (this.bitField0_ & 8) == 8;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(DataPointMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasName() && hasValue() && hasSampleSize() && hasType();
            }

            public Builder mergeFrom(DataPointMessage dataPointMessage) {
                if (dataPointMessage == DataPointMessage.getDefaultInstance()) {
                    return this;
                }
                if (dataPointMessage.hasName()) {
                    this.bitField0_ |= 1;
                    this.name_ = dataPointMessage.name_;
                    onChanged();
                }
                if (dataPointMessage.hasValue()) {
                    this.bitField0_ |= 2;
                    this.value_ = dataPointMessage.value_;
                    onChanged();
                }
                if (dataPointMessage.hasSampleSize()) {
                    setSampleSize(dataPointMessage.getSampleSize());
                }
                if (dataPointMessage.hasType()) {
                    setType(dataPointMessage.getType());
                }
                mergeUnknownFields(((GeneratedMessageV3) dataPointMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$DataPointMessage$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof DataPointMessage) {
                    return mergeFrom((DataPointMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            public Builder setName(String str) {
                str.getClass();
                this.bitField0_ |= 1;
                this.name_ = str;
                onChanged();
                return this;
            }

            public Builder setSampleSize(int i) {
                this.bitField0_ |= 4;
                this.sampleSize_ = i;
                onChanged();
                return this;
            }

            public Builder setType(DataType dataType) {
                dataType.getClass();
                this.bitField0_ |= 8;
                this.type_ = dataType.getNumber();
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public Builder setValue(String str) {
                str.getClass();
                this.bitField0_ |= 2;
                this.value_ = str;
                onChanged();
                return this;
            }
        }

        public enum DataType implements ProtocolMessageEnum {
            COUNTER(0),
            TIMER(1),
            DISCRETE(2),
            CLICKSTREAM(3);

            public static final int CLICKSTREAM_VALUE = 3;
            public static final int COUNTER_VALUE = 0;
            public static final int DISCRETE_VALUE = 2;
            public static final int TIMER_VALUE = 1;
            private final int value;
            private static final Internal.EnumLiteMap<DataType> internalValueMap = new Internal.EnumLiteMap<DataType>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessage.DataType.1
                @Override // com.google.protobuf.Internal.EnumLiteMap
                public DataType findValueByNumber(int i) {
                    return DataType.forNumber(i);
                }
            };
            private static final DataType[] VALUES = values();

            DataType(int i) {
                this.value = i;
            }

            public static DataType forNumber(int i) {
                if (i == 0) {
                    return COUNTER;
                }
                if (i == 1) {
                    return TIMER;
                }
                if (i == 2) {
                    return DISCRETE;
                }
                if (i != 3) {
                    return null;
                }
                return CLICKSTREAM;
            }

            public static final Descriptors.EnumDescriptor getDescriptor() {
                return DataPointMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static Internal.EnumLiteMap<DataType> internalGetValueMap() {
                return internalValueMap;
            }

            @Deprecated
            public static DataType valueOf(int i) {
                return forNumber(i);
            }

            public static DataType valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            public final Descriptors.EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
            public final int getNumber() {
                return this.value;
            }

            public final Descriptors.EnumValueDescriptor getValueDescriptor() {
                return getDescriptor().getValues().get(ordinal());
            }
        }

        private DataPointMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.name_ = "";
            this.value_ = "";
            this.sampleSize_ = 0;
            this.type_ = 0;
        }

        private DataPointMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                ByteString bytes = codedInputStream.readBytes();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.name_ = bytes;
                            } else if (tag == 18) {
                                ByteString bytes2 = codedInputStream.readBytes();
                                this.bitField0_ |= 2;
                                this.value_ = bytes2;
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.sampleSize_ = codedInputStream.readInt32();
                            } else if (tag == 32) {
                                int i = codedInputStream.readEnum();
                                if (DataType.valueOf(i) == null) {
                                    builderNewBuilder.mergeVarintField(4, i);
                                } else {
                                    this.bitField0_ |= 8;
                                    this.type_ = i;
                                }
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        private DataPointMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static DataPointMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof DataPointMessage)) {
                return super.equals(obj);
            }
            DataPointMessage dataPointMessage = (DataPointMessage) obj;
            boolean z = hasName() == dataPointMessage.hasName();
            if (hasName()) {
                z = z && getName().equals(dataPointMessage.getName());
            }
            boolean z2 = z && hasValue() == dataPointMessage.hasValue();
            if (hasValue()) {
                z2 = z2 && getValue().equals(dataPointMessage.getValue());
            }
            boolean z3 = z2 && hasSampleSize() == dataPointMessage.hasSampleSize();
            if (hasSampleSize()) {
                z3 = z3 && getSampleSize() == dataPointMessage.getSampleSize();
            }
            boolean z4 = z3 && hasType() == dataPointMessage.hasType();
            if (hasType()) {
                z4 = z4 && this.type_ == dataPointMessage.type_;
            }
            return z4 && this.unknownFields.equals(dataPointMessage.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public DataPointMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.name_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<DataPointMessage> getParserForType() {
            return PARSER;
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.DataPointMessageOrBuilder
        public int getSampleSize() {
            return this.sampleSize_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.value_);
            }
            if ((this.bitField0_ & 4) == 4) {
                iComputeStringSize += CodedOutputStream.computeInt32Size(3, this.sampleSize_);
            }
            if ((this.bitField0_ & 8) == 8) {
                iComputeStringSize += CodedOutputStream.computeEnumSize(4, this.type_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public DataType getType() {
            DataType dataTypeValueOf = DataType.valueOf(this.type_);
            return dataTypeValueOf == null ? DataType.COUNTER : dataTypeValueOf;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.value_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasSampleSize() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasType() {
            return (this.bitField0_ & 8) == 8;
        }

        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasName()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getName().hashCode();
            }
            if (hasValue()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getValue().hashCode();
            }
            if (hasSampleSize()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getSampleSize();
            }
            if (hasType()) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + this.type_;
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_DataPointMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(DataPointMessage.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasName()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasValue()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasSampleSize()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasType()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.sampleSize_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeEnum(4, this.type_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface DataPointMessageOrBuilder extends MessageOrBuilder {
        int getSampleSize();
    }

    public static final class KeyValue extends GeneratedMessageV3 implements MessageOrBuilder {
        private static final KeyValue DEFAULT_INSTANCE = new KeyValue();

        @Deprecated
        public static final Parser<KeyValue> PARSER = new AbstractParser<KeyValue>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.1
            @Override // com.google.protobuf.Parser
            public KeyValue parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new KeyValue(codedInputStream, extensionRegistryLite);
            }
        };
        private int bitField0_;
        private volatile Object key_;
        private byte memoizedIsInitialized;
        private volatile Object value_;

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MessageOrBuilder {
            private int bitField0_;
            private Object key_;
            private Object value_;

            private Builder() {
                this.key_ = "";
                this.value_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.key_ = "";
                this.value_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = GeneratedMessageV3.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public KeyValue build() {
                KeyValue keyValueBuildPartial = buildPartial();
                if (keyValueBuildPartial.isInitialized()) {
                    return keyValueBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) keyValueBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public KeyValue buildPartial() {
                KeyValue keyValue = new KeyValue(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                keyValue.key_ = this.key_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                keyValue.value_ = this.value_;
                keyValue.bitField0_ = i2;
                onBuilt();
                return keyValue;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo76clone() {
                return (Builder) super.mo76clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public KeyValue getDefaultInstanceForType() {
                return KeyValue.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_KeyValue_descriptor;
            }

            public boolean hasKey() {
                return (this.bitField0_ & 1) == 1;
            }

            public boolean hasValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_KeyValue_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyValue.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return hasKey() && hasValue();
            }

            public Builder mergeFrom(KeyValue keyValue) {
                if (keyValue == KeyValue.getDefaultInstance()) {
                    return this;
                }
                if (keyValue.hasKey()) {
                    this.bitField0_ |= 1;
                    this.key_ = keyValue.key_;
                    onChanged();
                }
                if (keyValue.hasValue()) {
                    this.bitField0_ |= 2;
                    this.value_ = keyValue.value_;
                    onChanged();
                }
                mergeUnknownFields(((GeneratedMessageV3) keyValue).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.KeyValue.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$KeyValue$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof KeyValue) {
                    return mergeFrom((KeyValue) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            public Builder setKey(String str) {
                str.getClass();
                this.bitField0_ |= 1;
                this.key_ = str;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            public Builder setValue(String str) {
                str.getClass();
                this.bitField0_ |= 2;
                this.value_ = str;
                onChanged();
                return this;
            }
        }

        private KeyValue() {
            this.memoizedIsInitialized = (byte) -1;
            this.key_ = "";
            this.value_ = "";
        }

        private KeyValue(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                ByteString bytes = codedInputStream.readBytes();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.key_ = bytes;
                            } else if (tag == 18) {
                                ByteString bytes2 = codedInputStream.readBytes();
                                this.bitField0_ |= 2;
                                this.value_ = bytes2;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        private KeyValue(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static KeyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_KeyValue_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof KeyValue)) {
                return super.equals(obj);
            }
            KeyValue keyValue = (KeyValue) obj;
            boolean z = hasKey() == keyValue.hasKey();
            if (hasKey()) {
                z = z && getKey().equals(keyValue.getKey());
            }
            boolean z2 = z && hasValue() == keyValue.hasValue();
            if (hasValue()) {
                z2 = z2 && getValue().equals(keyValue.getValue());
            }
            return z2 && this.unknownFields.equals(keyValue.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public KeyValue getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public String getKey() {
            Object obj = this.key_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.key_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<KeyValue> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) == 1 ? 0 + GeneratedMessageV3.computeStringSize(1, this.key_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.value_);
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public String getValue() {
            Object obj = this.value_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.value_ = stringUtf8;
            }
            return stringUtf8;
        }

        public boolean hasKey() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasKey()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getKey().hashCode();
            }
            if (hasValue()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getValue().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_KeyValue_fieldAccessorTable.ensureFieldAccessorsInitialized(KeyValue.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasKey()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (hasValue()) {
                this.memoizedIsInitialized = (byte) 1;
                return true;
            }
            this.memoizedIsInitialized = (byte) 0;
            return false;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.key_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.value_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public static final class MetricBatchMessage extends GeneratedMessageV3 implements MetricBatchMessageOrBuilder {
        private static final MetricBatchMessage DEFAULT_INSTANCE = new MetricBatchMessage();

        @Deprecated
        public static final Parser<MetricBatchMessage> PARSER = new AbstractParser<MetricBatchMessage>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.1
            @Override // com.google.protobuf.Parser
            public MetricBatchMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MetricBatchMessage(codedInputStream, extensionRegistryLite);
            }
        };
        private int bitField0_;
        private volatile Object deviceSerialNumber_;
        private volatile Object deviceType_;
        private byte memoizedIsInitialized;
        private List<KeyValue> metadata_;
        private List<MetricEntryMessage> metricEntry_;
        private volatile Object tag_;

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricBatchMessageOrBuilder {
            private int bitField0_;
            private Object deviceSerialNumber_;
            private Object deviceType_;
            private RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, Object> metadataBuilder_;
            private List<KeyValue> metadata_;
            private RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> metricEntryBuilder_;
            private List<MetricEntryMessage> metricEntry_;
            private Object tag_;

            private Builder() {
                this.deviceSerialNumber_ = "";
                this.deviceType_ = "";
                this.tag_ = "";
                this.metadata_ = Collections.emptyList();
                this.metricEntry_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.deviceSerialNumber_ = "";
                this.deviceType_ = "";
                this.tag_ = "";
                this.metadata_ = Collections.emptyList();
                this.metricEntry_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void ensureMetadataIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.metadata_ = new ArrayList(this.metadata_);
                    this.bitField0_ |= 8;
                }
            }

            private void ensureMetricEntryIsMutable() {
                if ((this.bitField0_ & 16) != 16) {
                    this.metricEntry_ = new ArrayList(this.metricEntry_);
                    this.bitField0_ |= 16;
                }
            }

            private RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, Object> getMetadataFieldBuilder() {
                if (this.metadataBuilder_ == null) {
                    this.metadataBuilder_ = new RepeatedFieldBuilderV3<>(this.metadata_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.metadata_ = null;
                }
                return this.metadataBuilder_;
            }

            private RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> getMetricEntryFieldBuilder() {
                if (this.metricEntryBuilder_ == null) {
                    this.metricEntryBuilder_ = new RepeatedFieldBuilderV3<>(this.metricEntry_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                    this.metricEntry_ = null;
                }
                return this.metricEntryBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getMetadataFieldBuilder();
                    getMetricEntryFieldBuilder();
                }
            }

            public Builder addMetadata(KeyValue keyValue) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, Object> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    keyValue.getClass();
                    ensureMetadataIsMutable();
                    this.metadata_.add(keyValue);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(keyValue);
                }
                return this;
            }

            public Builder addMetricEntry(MetricEntryMessage metricEntryMessage) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    metricEntryMessage.getClass();
                    ensureMetricEntryIsMutable();
                    this.metricEntry_.add(metricEntryMessage);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(metricEntryMessage);
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MetricBatchMessage build() {
                MetricBatchMessage metricBatchMessageBuildPartial = buildPartial();
                if (metricBatchMessageBuildPartial.isInitialized()) {
                    return metricBatchMessageBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) metricBatchMessageBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MetricBatchMessage buildPartial() {
                List listBuild;
                List listBuild2;
                MetricBatchMessage metricBatchMessage = new MetricBatchMessage(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                metricBatchMessage.deviceSerialNumber_ = this.deviceSerialNumber_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                metricBatchMessage.deviceType_ = this.deviceType_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                metricBatchMessage.tag_ = this.tag_;
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, Object> repeatedFieldBuilderV3 = this.metadataBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.metadata_ = Collections.unmodifiableList(this.metadata_);
                        this.bitField0_ &= -9;
                    }
                    listBuild = this.metadata_;
                } else {
                    listBuild = repeatedFieldBuilderV3.build();
                }
                metricBatchMessage.metadata_ = listBuild;
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV32 = this.metricEntryBuilder_;
                if (repeatedFieldBuilderV32 == null) {
                    if ((this.bitField0_ & 16) == 16) {
                        this.metricEntry_ = Collections.unmodifiableList(this.metricEntry_);
                        this.bitField0_ &= -17;
                    }
                    listBuild2 = this.metricEntry_;
                } else {
                    listBuild2 = repeatedFieldBuilderV32.build();
                }
                metricBatchMessage.metricEntry_ = listBuild2;
                metricBatchMessage.bitField0_ = i2;
                onBuilt();
                return metricBatchMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo76clone() {
                return (Builder) super.mo76clone();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MetricBatchMessage getDefaultInstanceForType() {
                return MetricBatchMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_descriptor;
            }

            public KeyValue getMetadata(int i) {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, Object> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return (KeyValue) (repeatedFieldBuilderV3 == null ? this.metadata_.get(i) : repeatedFieldBuilderV3.getMessage(i));
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public int getMetadataCount() {
                RepeatedFieldBuilderV3<KeyValue, KeyValue.Builder, Object> repeatedFieldBuilderV3 = this.metadataBuilder_;
                return repeatedFieldBuilderV3 == null ? this.metadata_.size() : repeatedFieldBuilderV3.getCount();
            }

            public MetricEntryMessage getMetricEntry(int i) {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return (MetricEntryMessage) (repeatedFieldBuilderV3 == null ? this.metricEntry_.get(i) : repeatedFieldBuilderV3.getMessage(i));
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
            public int getMetricEntryCount() {
                RepeatedFieldBuilderV3<MetricEntryMessage, MetricEntryMessage.Builder, MetricEntryMessageOrBuilder> repeatedFieldBuilderV3 = this.metricEntryBuilder_;
                return repeatedFieldBuilderV3 == null ? this.metricEntry_.size() : repeatedFieldBuilderV3.getCount();
            }

            public boolean hasDeviceSerialNumber() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricBatchMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasDeviceSerialNumber()) {
                    return false;
                }
                for (int i = 0; i < getMetadataCount(); i++) {
                    if (!getMetadata(i).isInitialized()) {
                        return false;
                    }
                }
                for (int i2 = 0; i2 < getMetricEntryCount(); i2++) {
                    if (!getMetricEntry(i2).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(MetricBatchMessage metricBatchMessage) {
                if (metricBatchMessage == MetricBatchMessage.getDefaultInstance()) {
                    return this;
                }
                if (metricBatchMessage.hasDeviceSerialNumber()) {
                    this.bitField0_ |= 1;
                    this.deviceSerialNumber_ = metricBatchMessage.deviceSerialNumber_;
                    onChanged();
                }
                if (metricBatchMessage.hasDeviceType()) {
                    this.bitField0_ |= 2;
                    this.deviceType_ = metricBatchMessage.deviceType_;
                    onChanged();
                }
                if (metricBatchMessage.hasTag()) {
                    this.bitField0_ |= 4;
                    this.tag_ = metricBatchMessage.tag_;
                    onChanged();
                }
                if (this.metadataBuilder_ == null) {
                    if (!metricBatchMessage.metadata_.isEmpty()) {
                        if (this.metadata_.isEmpty()) {
                            this.metadata_ = metricBatchMessage.metadata_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureMetadataIsMutable();
                            this.metadata_.addAll(metricBatchMessage.metadata_);
                        }
                        onChanged();
                    }
                } else if (!metricBatchMessage.metadata_.isEmpty()) {
                    if (this.metadataBuilder_.isEmpty()) {
                        this.metadataBuilder_.dispose();
                        this.metadataBuilder_ = null;
                        this.metadata_ = metricBatchMessage.metadata_;
                        this.bitField0_ &= -9;
                        this.metadataBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMetadataFieldBuilder() : null;
                    } else {
                        this.metadataBuilder_.addAllMessages(metricBatchMessage.metadata_);
                    }
                }
                if (this.metricEntryBuilder_ == null) {
                    if (!metricBatchMessage.metricEntry_.isEmpty()) {
                        if (this.metricEntry_.isEmpty()) {
                            this.metricEntry_ = metricBatchMessage.metricEntry_;
                            this.bitField0_ &= -17;
                        } else {
                            ensureMetricEntryIsMutable();
                            this.metricEntry_.addAll(metricBatchMessage.metricEntry_);
                        }
                        onChanged();
                    }
                } else if (!metricBatchMessage.metricEntry_.isEmpty()) {
                    if (this.metricEntryBuilder_.isEmpty()) {
                        this.metricEntryBuilder_.dispose();
                        this.metricEntryBuilder_ = null;
                        this.metricEntry_ = metricBatchMessage.metricEntry_;
                        this.bitField0_ &= -17;
                        this.metricEntryBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getMetricEntryFieldBuilder() : null;
                    } else {
                        this.metricEntryBuilder_.addAllMessages(metricBatchMessage.metricEntry_);
                    }
                }
                mergeUnknownFields(((GeneratedMessageV3) metricBatchMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricBatchMessage$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MetricBatchMessage) {
                    return mergeFrom((MetricBatchMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }

            public Builder setDeviceSerialNumber(String str) {
                str.getClass();
                this.bitField0_ |= 1;
                this.deviceSerialNumber_ = str;
                onChanged();
                return this;
            }

            public Builder setDeviceType(String str) {
                str.getClass();
                this.bitField0_ |= 2;
                this.deviceType_ = str;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }
        }

        private MetricBatchMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.deviceSerialNumber_ = "";
            this.deviceType_ = "";
            this.tag_ = "";
            this.metadata_ = Collections.emptyList();
            this.metricEntry_ = Collections.emptyList();
        }

        private MetricBatchMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            List list;
            MessageLite message;
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (true) {
                if (z) {
                    break;
                }
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                ByteString bytes = codedInputStream.readBytes();
                                this.bitField0_ = 1 | this.bitField0_;
                                this.deviceSerialNumber_ = bytes;
                            } else if (tag == 18) {
                                ByteString bytes2 = codedInputStream.readBytes();
                                this.bitField0_ |= 2;
                                this.deviceType_ = bytes2;
                            } else if (tag != 26) {
                                if (tag == 34) {
                                    if ((i & 8) != 8) {
                                        this.metadata_ = new ArrayList();
                                        i |= 8;
                                    }
                                    list = this.metadata_;
                                    message = codedInputStream.readMessage(KeyValue.PARSER, extensionRegistryLite);
                                } else if (tag == 42) {
                                    if ((i & 16) != 16) {
                                        this.metricEntry_ = new ArrayList();
                                        i |= 16;
                                    }
                                    list = this.metricEntry_;
                                    message = codedInputStream.readMessage(MetricEntryMessage.PARSER, extensionRegistryLite);
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                                list.add(message);
                            } else {
                                ByteString bytes3 = codedInputStream.readBytes();
                                this.bitField0_ |= 4;
                                this.tag_ = bytes3;
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 8) == 8) {
                        this.metadata_ = Collections.unmodifiableList(this.metadata_);
                    }
                    if ((i & 16) == 16) {
                        this.metricEntry_ = Collections.unmodifiableList(this.metricEntry_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        private MetricBatchMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static MetricBatchMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricBatchMessage)) {
                return super.equals(obj);
            }
            MetricBatchMessage metricBatchMessage = (MetricBatchMessage) obj;
            boolean z = hasDeviceSerialNumber() == metricBatchMessage.hasDeviceSerialNumber();
            if (hasDeviceSerialNumber()) {
                z = z && getDeviceSerialNumber().equals(metricBatchMessage.getDeviceSerialNumber());
            }
            boolean z2 = z && hasDeviceType() == metricBatchMessage.hasDeviceType();
            if (hasDeviceType()) {
                z2 = z2 && getDeviceType().equals(metricBatchMessage.getDeviceType());
            }
            boolean z3 = z2 && hasTag() == metricBatchMessage.hasTag();
            if (hasTag()) {
                z3 = z3 && getTag().equals(metricBatchMessage.getTag());
            }
            return ((z3 && getMetadataList().equals(metricBatchMessage.getMetadataList())) && getMetricEntryList().equals(metricBatchMessage.getMetricEntryList())) && this.unknownFields.equals(metricBatchMessage.unknownFields);
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MetricBatchMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public String getDeviceSerialNumber() {
            Object obj = this.deviceSerialNumber_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceSerialNumber_ = stringUtf8;
            }
            return stringUtf8;
        }

        public String getDeviceType() {
            Object obj = this.deviceType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.deviceType_ = stringUtf8;
            }
            return stringUtf8;
        }

        public KeyValue getMetadata(int i) {
            return this.metadata_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public int getMetadataCount() {
            return this.metadata_.size();
        }

        public List<KeyValue> getMetadataList() {
            return this.metadata_;
        }

        public MetricEntryMessage getMetricEntry(int i) {
            return this.metricEntry_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricBatchMessageOrBuilder
        public int getMetricEntryCount() {
            return this.metricEntry_.size();
        }

        public List<MetricEntryMessage> getMetricEntryList() {
            return this.metricEntry_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<MetricBatchMessage> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.bitField0_ & 1) == 1 ? GeneratedMessageV3.computeStringSize(1, this.deviceSerialNumber_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.deviceType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.tag_);
            }
            for (int i2 = 0; i2 < this.metadata_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(4, this.metadata_.get(i2));
            }
            for (int i3 = 0; i3 < this.metricEntry_.size(); i3++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(5, this.metricEntry_.get(i3));
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public String getTag() {
            Object obj = this.tag_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.tag_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasDeviceSerialNumber() {
            return (this.bitField0_ & 1) == 1;
        }

        public boolean hasDeviceType() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasTag() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasDeviceSerialNumber()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getDeviceSerialNumber().hashCode();
            }
            if (hasDeviceType()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getDeviceType().hashCode();
            }
            if (hasTag()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getTag().hashCode();
            }
            if (getMetadataCount() > 0) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getMetadataList().hashCode();
            }
            if (getMetricEntryCount() > 0) {
                iHashCode = (((iHashCode * 37) + 5) * 53) + getMetricEntryList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_MetricBatchMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricBatchMessage.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasDeviceSerialNumber()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getMetadataCount(); i++) {
                if (!getMetadata(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            for (int i2 = 0; i2 < getMetricEntryCount(); i2++) {
                if (!getMetricEntry(i2).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.deviceSerialNumber_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.deviceType_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.tag_);
            }
            for (int i = 0; i < this.metadata_.size(); i++) {
                codedOutputStream.writeMessage(4, this.metadata_.get(i));
            }
            for (int i2 = 0; i2 < this.metricEntry_.size(); i2++) {
                codedOutputStream.writeMessage(5, this.metricEntry_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface MetricBatchMessageOrBuilder extends MessageOrBuilder {
        int getMetadataCount();

        int getMetricEntryCount();
    }

    public static final class MetricEntryMessage extends GeneratedMessageV3 implements MetricEntryMessageOrBuilder {
        private static final MetricEntryMessage DEFAULT_INSTANCE = new MetricEntryMessage();

        @Deprecated
        public static final Parser<MetricEntryMessage> PARSER = new AbstractParser<MetricEntryMessage>() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.1
            @Override // com.google.protobuf.Parser
            public MetricEntryMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MetricEntryMessage(codedInputStream, extensionRegistryLite);
            }
        };
        private int bitField0_;
        private List<DataPointMessage> dataPoint_;
        private byte memoizedIsInitialized;
        private volatile Object program_;
        private volatile Object source_;
        private long timestamp_;

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricEntryMessageOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> dataPointBuilder_;
            private List<DataPointMessage> dataPoint_;
            private Object program_;
            private Object source_;
            private long timestamp_;

            private Builder() {
                this.program_ = "";
                this.source_ = "";
                this.dataPoint_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.program_ = "";
                this.source_ = "";
                this.dataPoint_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void ensureDataPointIsMutable() {
                if ((this.bitField0_ & 8) != 8) {
                    this.dataPoint_ = new ArrayList(this.dataPoint_);
                    this.bitField0_ |= 8;
                }
            }

            private RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> getDataPointFieldBuilder() {
                if (this.dataPointBuilder_ == null) {
                    this.dataPointBuilder_ = new RepeatedFieldBuilderV3<>(this.dataPoint_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                    this.dataPoint_ = null;
                }
                return this.dataPointBuilder_;
            }

            private void maybeForceBuilderInitialization() {
                if (GeneratedMessageV3.alwaysUseFieldBuilders) {
                    getDataPointFieldBuilder();
                }
            }

            public Builder addDataPoint(DataPointMessage dataPointMessage) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    dataPointMessage.getClass();
                    ensureDataPointIsMutable();
                    this.dataPoint_.add(dataPointMessage);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(dataPointMessage);
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MetricEntryMessage build() {
                MetricEntryMessage metricEntryMessageBuildPartial = buildPartial();
                if (metricEntryMessageBuildPartial.isInitialized()) {
                    return metricEntryMessageBuildPartial;
                }
                throw AbstractMessage.Builder.newUninitializedMessageException((Message) metricEntryMessageBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MetricEntryMessage buildPartial() {
                List listBuild;
                MetricEntryMessage metricEntryMessage = new MetricEntryMessage(this);
                int i = this.bitField0_;
                int i2 = (i & 1) != 1 ? 0 : 1;
                metricEntryMessage.timestamp_ = this.timestamp_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                metricEntryMessage.program_ = this.program_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                metricEntryMessage.source_ = this.source_;
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 8) == 8) {
                        this.dataPoint_ = Collections.unmodifiableList(this.dataPoint_);
                        this.bitField0_ &= -9;
                    }
                    listBuild = this.dataPoint_;
                } else {
                    listBuild = repeatedFieldBuilderV3.build();
                }
                metricEntryMessage.dataPoint_ = listBuild;
                metricEntryMessage.bitField0_ = i2;
                onBuilt();
                return metricEntryMessage;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo76clone() {
                return (Builder) super.mo76clone();
            }

            public DataPointMessage getDataPoint(int i) {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return (DataPointMessage) (repeatedFieldBuilderV3 == null ? this.dataPoint_.get(i) : repeatedFieldBuilderV3.getMessage(i));
            }

            @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
            public int getDataPointCount() {
                RepeatedFieldBuilderV3<DataPointMessage, DataPointMessage.Builder, DataPointMessageOrBuilder> repeatedFieldBuilderV3 = this.dataPointBuilder_;
                return repeatedFieldBuilderV3 == null ? this.dataPoint_.size() : repeatedFieldBuilderV3.getCount();
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MetricEntryMessage getDefaultInstanceForType() {
                return MetricEntryMessage.getDefaultInstance();
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_descriptor;
            }

            public boolean hasProgram() {
                return (this.bitField0_ & 2) == 2;
            }

            public boolean hasSource() {
                return (this.bitField0_ & 4) == 4;
            }

            public boolean hasTimestamp() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricEntryMessage.class, Builder.class);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasTimestamp() || !hasProgram() || !hasSource()) {
                    return false;
                }
                for (int i = 0; i < getDataPointCount(); i++) {
                    if (!getDataPoint(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(MetricEntryMessage metricEntryMessage) {
                if (metricEntryMessage == MetricEntryMessage.getDefaultInstance()) {
                    return this;
                }
                if (metricEntryMessage.hasTimestamp()) {
                    setTimestamp(metricEntryMessage.getTimestamp());
                }
                if (metricEntryMessage.hasProgram()) {
                    this.bitField0_ |= 2;
                    this.program_ = metricEntryMessage.program_;
                    onChanged();
                }
                if (metricEntryMessage.hasSource()) {
                    this.bitField0_ |= 4;
                    this.source_ = metricEntryMessage.source_;
                    onChanged();
                }
                if (this.dataPointBuilder_ == null) {
                    if (!metricEntryMessage.dataPoint_.isEmpty()) {
                        if (this.dataPoint_.isEmpty()) {
                            this.dataPoint_ = metricEntryMessage.dataPoint_;
                            this.bitField0_ &= -9;
                        } else {
                            ensureDataPointIsMutable();
                            this.dataPoint_.addAll(metricEntryMessage.dataPoint_);
                        }
                        onChanged();
                    }
                } else if (!metricEntryMessage.dataPoint_.isEmpty()) {
                    if (this.dataPointBuilder_.isEmpty()) {
                        this.dataPointBuilder_.dispose();
                        this.dataPointBuilder_ = null;
                        this.dataPoint_ = metricEntryMessage.dataPoint_;
                        this.bitField0_ &= -9;
                        this.dataPointBuilder_ = GeneratedMessageV3.alwaysUseFieldBuilders ? getDataPointFieldBuilder() : null;
                    } else {
                        this.dataPointBuilder_.addAllMessages(metricEntryMessage.dataPoint_);
                    }
                }
                mergeUnknownFields(((GeneratedMessageV3) metricEntryMessage).unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser<com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage> r1 = com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.PARSER     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage r3 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage) r3     // Catch: java.lang.Throwable -> Lf com.google.protobuf.InvalidProtocolBufferException -> L11
                    if (r3 == 0) goto Le
                    r2.mergeFrom(r3)
                Le:
                    return r2
                Lf:
                    r3 = move-exception
                    goto L1f
                L11:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> Lf
                    com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage r4 = (com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage) r4     // Catch: java.lang.Throwable -> Lf
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1d
                    throw r3     // Catch: java.lang.Throwable -> L1d
                L1d:
                    r3 = move-exception
                    r0 = r4
                L1f:
                    if (r0 == 0) goto L24
                    r2.mergeFrom(r0)
                L24:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage$MetricEntryMessage$Builder");
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message message) {
                if (message instanceof MetricEntryMessage) {
                    return mergeFrom((MetricEntryMessage) message);
                }
                super.mergeFrom(message);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder
            public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            public Builder setProgram(String str) {
                str.getClass();
                this.bitField0_ |= 2;
                this.program_ = str;
                onChanged();
                return this;
            }

            public Builder setSource(String str) {
                str.getClass();
                this.bitField0_ |= 4;
                this.source_ = str;
                onChanged();
                return this;
            }

            public Builder setTimestamp(long j) {
                this.bitField0_ |= 1;
                this.timestamp_ = j;
                onChanged();
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
            public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }
        }

        private MetricEntryMessage() {
            this.memoizedIsInitialized = (byte) -1;
            this.timestamp_ = 0L;
            this.program_ = "";
            this.source_ = "";
            this.dataPoint_ = Collections.emptyList();
        }

        private MetricEntryMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            int i = 0;
            while (true) {
                if (z) {
                    break;
                }
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.timestamp_ = codedInputStream.readInt64();
                            } else if (tag == 18) {
                                ByteString bytes = codedInputStream.readBytes();
                                this.bitField0_ |= 2;
                                this.program_ = bytes;
                            } else if (tag == 26) {
                                ByteString bytes2 = codedInputStream.readBytes();
                                this.bitField0_ |= 4;
                                this.source_ = bytes2;
                            } else if (tag == 34) {
                                if ((i & 8) != 8) {
                                    this.dataPoint_ = new ArrayList();
                                    i |= 8;
                                }
                                this.dataPoint_.add((DataPointMessage) codedInputStream.readMessage(DataPointMessage.PARSER, extensionRegistryLite));
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if ((i & 8) == 8) {
                        this.dataPoint_ = Collections.unmodifiableList(this.dataPoint_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        private MetricEntryMessage(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        public static MetricEntryMessage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_descriptor;
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        @Override // com.google.protobuf.AbstractMessage
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricEntryMessage)) {
                return super.equals(obj);
            }
            MetricEntryMessage metricEntryMessage = (MetricEntryMessage) obj;
            boolean z = hasTimestamp() == metricEntryMessage.hasTimestamp();
            if (hasTimestamp()) {
                z = z && getTimestamp() == metricEntryMessage.getTimestamp();
            }
            boolean z2 = z && hasProgram() == metricEntryMessage.hasProgram();
            if (hasProgram()) {
                z2 = z2 && getProgram().equals(metricEntryMessage.getProgram());
            }
            boolean z3 = z2 && hasSource() == metricEntryMessage.hasSource();
            if (hasSource()) {
                z3 = z3 && getSource().equals(metricEntryMessage.getSource());
            }
            return (z3 && getDataPointList().equals(metricEntryMessage.getDataPointList())) && this.unknownFields.equals(metricEntryMessage.unknownFields);
        }

        public DataPointMessage getDataPoint(int i) {
            return this.dataPoint_.get(i);
        }

        @Override // com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.MetricEntryMessageOrBuilder
        public int getDataPointCount() {
            return this.dataPoint_.size();
        }

        public List<DataPointMessage> getDataPointList() {
            return this.dataPoint_;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MetricEntryMessage getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite
        public Parser<MetricEntryMessage> getParserForType() {
            return PARSER;
        }

        public String getProgram() {
            Object obj = this.program_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.program_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.AbstractMessageLite, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeInt64Size = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeInt64Size(1, this.timestamp_) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                iComputeInt64Size += GeneratedMessageV3.computeStringSize(2, this.program_);
            }
            if ((this.bitField0_ & 4) == 4) {
                iComputeInt64Size += GeneratedMessageV3.computeStringSize(3, this.source_);
            }
            for (int i2 = 0; i2 < this.dataPoint_.size(); i2++) {
                iComputeInt64Size += CodedOutputStream.computeMessageSize(4, this.dataPoint_.get(i2));
            }
            int serializedSize = iComputeInt64Size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public String getSource() {
            Object obj = this.source_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.source_ = stringUtf8;
            }
            return stringUtf8;
        }

        public long getTimestamp() {
            return this.timestamp_;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        public boolean hasProgram() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean hasSource() {
            return (this.bitField0_ & 4) == 4;
        }

        public boolean hasTimestamp() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.google.protobuf.AbstractMessage
        public int hashCode() {
            int i = this.memoizedHashCode;
            if (i != 0) {
                return i;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasTimestamp()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + Internal.hashLong(getTimestamp());
            }
            if (hasProgram()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getProgram().hashCode();
            }
            if (hasSource()) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getSource().hashCode();
            }
            if (getDataPointCount() > 0) {
                iHashCode = (((iHashCode * 37) + 4) * 53) + getDataPointList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DeviceMetricsMessage.internal_static_metrics_MetricEntryMessage_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricEntryMessage.class, Builder.class);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasTimestamp()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasProgram()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            if (!hasSource()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            for (int i = 0; i < getDataPointCount(); i++) {
                if (!getDataPoint(i).isInitialized()) {
                    this.memoizedIsInitialized = (byte) 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessageV3
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt64(1, this.timestamp_);
            }
            if ((this.bitField0_ & 2) == 2) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.program_);
            }
            if ((this.bitField0_ & 4) == 4) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.source_);
            }
            for (int i = 0; i < this.dataPoint_.size(); i++) {
                codedOutputStream.writeMessage(4, this.dataPoint_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }
    }

    public interface MetricEntryMessageOrBuilder extends MessageOrBuilder {
        int getDataPointCount();
    }

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\nRcom/amazon/client/metrics/thirdparty/codec/protocol/DeviceMetricsMessage.0.2.proto\u0012\u0007metrics\"¨\u0001\n\u0012MetricBatchMessage\u0012\u001a\n\u0012deviceSerialNumber\u0018\u0001 \u0002(\t\u0012\u0012\n\ndeviceType\u0018\u0002 \u0001(\t\u0012\u000b\n\u0003tag\u0018\u0003 \u0001(\t\u0012#\n\bmetadata\u0018\u0004 \u0003(\u000b2\u0011.metrics.KeyValue\u00120\n\u000bmetricEntry\u0018\u0005 \u0003(\u000b2\u001b.metrics.MetricEntryMessage\"v\n\u0012MetricEntryMessage\u0012\u0011\n\ttimestamp\u0018\u0001 \u0002(\u0003\u0012\u000f\n\u0007program\u0018\u0002 \u0002(\t\u0012\u000e\n\u0006source\u0018\u0003 \u0002(\t\u0012,\n\tdataPoint\u0018\u0004 \u0003(\u000b2\u0019.metrics.DataPointMessage\"¸\u0001\n\u0010DataPointMessage\u0012\f\n\u0004name\u0018\u0001 \u0002(\t\u0012\r\n\u0005value\u0018\u0002 \u0002(\t\u0012\u0012\n\nSampleSize\u0018\u0003 \u0002(\u0005\u00120\n\u0004type\u0018\u0004 \u0002(\u000e2\".metrics.DataPointMessage.DataType\"A\n\bDataType\u0012\u000b\n\u0007COUNTER\u0010\u0000\u0012\t\n\u0005TIMER\u0010\u0001\u0012\f\n\bDISCRETE\u0010\u0002\u0012\u000f\n\u000bCLICKSTREAM\u0010\u0003\"&\n\bKeyValue\u0012\u000b\n\u0003key\u0018\u0001 \u0002(\t\u0012\r\n\u0005value\u0018\u0002 \u0002(\tBB\n*com.amazon.client.metrics.thirdparty.codecB\u0014DeviceMetricsMessage"}, new Descriptors.FileDescriptor[0], new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.amazon.client.metrics.thirdparty.codec.DeviceMetricsMessage.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = DeviceMetricsMessage.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_metrics_MetricBatchMessage_descriptor = descriptor2;
        internal_static_metrics_MetricBatchMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{RemoteConfigurationAndroidClientContextDecorator.DEVICE_SERIAL_NUMBER, "DeviceType", "Tag", "Metadata", "MetricEntry"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_metrics_MetricEntryMessage_descriptor = descriptor3;
        internal_static_metrics_MetricEntryMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Timestamp", "Program", "Source", "DataPoint"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_metrics_DataPointMessage_descriptor = descriptor4;
        internal_static_metrics_DataPointMessage_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Value", "SampleSize", "Type"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_metrics_KeyValue_descriptor = descriptor5;
        internal_static_metrics_KeyValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Key", "Value"});
    }

    private DeviceMetricsMessage() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }
}
