package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class MetricRule extends GeneratedMessageV3 implements MetricRuleOrBuilder {
    public static final int METRIC_COSTS_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private MapField<String, Long> metricCosts_;
    private volatile Object selector_;
    private static final MetricRule DEFAULT_INSTANCE = new MetricRule();
    private static final Parser<MetricRule> PARSER = new AbstractParser<MetricRule>() { // from class: com.google.api.MetricRule.1
        @Override // com.google.protobuf.Parser
        public MetricRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetricRule(codedInputStream, extensionRegistryLite);
        }
    };

    private MetricRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetricRule() {
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new MetricRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MetricRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.selector_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                if (!(z2 & true)) {
                                    this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
                                    z2 |= true;
                                }
                                MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(MetricCostsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                                this.metricCosts_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (UninitializedMessageException e2) {
                        throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                    }
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return QuotaProto.internal_static_google_api_MetricRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetMetricCosts();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    private static final class MetricCostsDefaultEntryHolder {
        static final MapEntry<String, Long> defaultEntry = MapEntry.newDefaultInstance(QuotaProto.internal_static_google_api_MetricRule_MetricCostsEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);

        private MetricCostsDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Long> internalGetMetricCosts() {
        MapField<String, Long> mapField = this.metricCosts_;
        return mapField == null ? MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public int getMetricCostsCount() {
        return internalGetMetricCosts().getMap().size();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public boolean containsMetricCosts(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        return internalGetMetricCosts().getMap().containsKey(str);
    }

    @Override // com.google.api.MetricRuleOrBuilder
    @Deprecated
    public Map<String, Long> getMetricCosts() {
        return getMetricCostsMap();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public Map<String, Long> getMetricCostsMap() {
        return internalGetMetricCosts().getMap();
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public long getMetricCostsOrDefault(String str, long j) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, Long> map = internalGetMetricCosts().getMap();
        return map.containsKey(str) ? map.get(str).longValue() : j;
    }

    @Override // com.google.api.MetricRuleOrBuilder
    public long getMetricCostsOrThrow(String str) {
        if (str == null) {
            throw new NullPointerException("map key");
        }
        Map<String, Long> map = internalGetMetricCosts().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return map.get(str).longValue();
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
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!GeneratedMessageV3.isStringEmpty(this.selector_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetMetricCosts(), MetricCostsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.selector_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.selector_);
        for (Map.Entry<String, Long> entry : internalGetMetricCosts().getMap().entrySet()) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, MetricCostsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricRule)) {
            return super.equals(obj);
        }
        MetricRule metricRule = (MetricRule) obj;
        return getSelector().equals(metricRule.getSelector()) && internalGetMetricCosts().equals(metricRule.internalGetMetricCosts()) && this.unknownFields.equals(metricRule.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (!internalGetMetricCosts().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetMetricCosts().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetricRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static MetricRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetricRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static MetricRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetricRule parseFrom(InputStream inputStream) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetricRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MetricRule metricRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(metricRule);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.protobuf.GeneratedMessageV3
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetricRuleOrBuilder {
        private int bitField0_;
        private MapField<String, Long> metricCosts_;
        private Object selector_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetMetricCosts();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableMetricCosts();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = MetricRule.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            internalGetMutableMetricCosts().clear();
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MetricRule getDefaultInstanceForType() {
            return MetricRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MetricRule build() {
            MetricRule metricRuleBuildPartial = buildPartial();
            if (metricRuleBuildPartial.isInitialized()) {
                return metricRuleBuildPartial;
            }
            throw newUninitializedMessageException((Message) metricRuleBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public MetricRule buildPartial() {
            MetricRule metricRule = new MetricRule(this);
            metricRule.selector_ = this.selector_;
            metricRule.metricCosts_ = internalGetMetricCosts();
            metricRule.metricCosts_.makeImmutable();
            onBuilt();
            return metricRule;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
        /* JADX INFO: renamed from: clone */
        public Builder mo240clone() {
            return (Builder) super.mo240clone();
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public Builder addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public Builder mergeFrom(Message message) {
            if (message instanceof MetricRule) {
                return mergeFrom((MetricRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetricRule metricRule) {
            if (metricRule == MetricRule.getDefaultInstance()) {
                return this;
            }
            if (!metricRule.getSelector().isEmpty()) {
                this.selector_ = metricRule.selector_;
                onChanged();
            }
            internalGetMutableMetricCosts().mergeFrom(metricRule.internalGetMetricCosts());
            mergeUnknownFields(metricRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.MetricRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MetricRule.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.MetricRule r3 = (com.google.api.MetricRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                com.google.api.MetricRule r4 = (com.google.api.MetricRule) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MetricRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MetricRule$Builder");
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.selector_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setSelector(String str) {
            str.getClass();
            this.selector_ = str;
            onChanged();
            return this;
        }

        public Builder clearSelector() {
            this.selector_ = MetricRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            byteString.getClass();
            MetricRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        private MapField<String, Long> internalGetMetricCosts() {
            MapField<String, Long> mapField = this.metricCosts_;
            return mapField == null ? MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Long> internalGetMutableMetricCosts() {
            onChanged();
            if (this.metricCosts_ == null) {
                this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
            }
            if (!this.metricCosts_.isMutable()) {
                this.metricCosts_ = this.metricCosts_.copy();
            }
            return this.metricCosts_;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public int getMetricCostsCount() {
            return internalGetMetricCosts().getMap().size();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public boolean containsMetricCosts(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            return internalGetMetricCosts().getMap().containsKey(str);
        }

        @Override // com.google.api.MetricRuleOrBuilder
        @Deprecated
        public Map<String, Long> getMetricCosts() {
            return getMetricCostsMap();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public Map<String, Long> getMetricCostsMap() {
            return internalGetMetricCosts().getMap();
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public long getMetricCostsOrDefault(String str, long j) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, Long> map = internalGetMetricCosts().getMap();
            return map.containsKey(str) ? map.get(str).longValue() : j;
        }

        @Override // com.google.api.MetricRuleOrBuilder
        public long getMetricCostsOrThrow(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            Map<String, Long> map = internalGetMetricCosts().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return map.get(str).longValue();
        }

        public Builder clearMetricCosts() {
            internalGetMutableMetricCosts().getMutableMap().clear();
            return this;
        }

        public Builder removeMetricCosts(String str) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableMetricCosts().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Long> getMutableMetricCosts() {
            return internalGetMutableMetricCosts().getMutableMap();
        }

        public Builder putMetricCosts(String str, long j) {
            if (str == null) {
                throw new NullPointerException("map key");
            }
            internalGetMutableMetricCosts().getMutableMap().put(str, Long.valueOf(j));
            return this;
        }

        public Builder putAllMetricCosts(Map<String, Long> map) {
            internalGetMutableMetricCosts().getMutableMap().putAll(map);
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder
        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static MetricRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<MetricRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public MetricRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
