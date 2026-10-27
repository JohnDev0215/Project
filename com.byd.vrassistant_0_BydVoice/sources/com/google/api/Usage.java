package com.google.api;

import com.google.api.UsageRule;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class Usage extends GeneratedMessageV3 implements UsageOrBuilder {
    private static final Usage DEFAULT_INSTANCE = new Usage();
    private static final Parser<Usage> PARSER = new AbstractParser<Usage>() { // from class: com.google.api.Usage.1
        @Override // com.google.protobuf.Parser
        public Usage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Usage(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_NOTIFICATION_CHANNEL_FIELD_NUMBER = 7;
    public static final int REQUIREMENTS_FIELD_NUMBER = 1;
    public static final int RULES_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private volatile Object producerNotificationChannel_;
    private LazyStringList requirements_;
    private List<UsageRule> rules_;

    private Usage(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Usage() {
        this.memoizedIsInitialized = (byte) -1;
        this.requirements_ = LazyStringArrayList.EMPTY;
        this.rules_ = Collections.emptyList();
        this.producerNotificationChannel_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Usage();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Usage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if ((i & 1) == 0) {
                                    this.requirements_ = new LazyStringArrayList();
                                    i |= 1;
                                }
                                this.requirements_.add(stringRequireUtf8);
                            } else if (tag == 50) {
                                if ((i & 2) == 0) {
                                    this.rules_ = new ArrayList();
                                    i |= 2;
                                }
                                this.rules_.add((UsageRule) codedInputStream.readMessage(UsageRule.parser(), extensionRegistryLite));
                            } else if (tag == 58) {
                                this.producerNotificationChannel_ = codedInputStream.readStringRequireUtf8();
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (UninitializedMessageException e) {
                        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e3) {
                    throw e3.setUnfinishedMessage(this);
                }
            } finally {
                if ((i & 1) != 0) {
                    this.requirements_ = this.requirements_.getUnmodifiableView();
                }
                if ((i & 2) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return UsageProto.internal_static_google_api_Usage_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
    }

    @Override // com.google.api.UsageOrBuilder
    public ProtocolStringList getRequirementsList() {
        return this.requirements_;
    }

    @Override // com.google.api.UsageOrBuilder
    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    @Override // com.google.api.UsageOrBuilder
    public String getRequirements(int i) {
        return (String) this.requirements_.get(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public ByteString getRequirementsBytes(int i) {
        return this.requirements_.getByteString(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public List<UsageRule> getRulesList() {
        return this.rules_;
    }

    @Override // com.google.api.UsageOrBuilder
    public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override // com.google.api.UsageOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.UsageOrBuilder
    public UsageRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public UsageRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.UsageOrBuilder
    public String getProducerNotificationChannel() {
        Object obj = this.producerNotificationChannel_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.producerNotificationChannel_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.UsageOrBuilder
    public ByteString getProducerNotificationChannelBytes() {
        Object obj = this.producerNotificationChannel_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.producerNotificationChannel_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
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
        for (int i = 0; i < this.requirements_.size(); i++) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.requirements_.getRaw(i));
        }
        for (int i2 = 0; i2 < this.rules_.size(); i2++) {
            codedOutputStream.writeMessage(6, this.rules_.get(i2));
        }
        if (!GeneratedMessageV3.isStringEmpty(this.producerNotificationChannel_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.producerNotificationChannel_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSizeNoTag = 0;
        for (int i2 = 0; i2 < this.requirements_.size(); i2++) {
            iComputeStringSizeNoTag += computeStringSizeNoTag(this.requirements_.getRaw(i2));
        }
        int size = iComputeStringSizeNoTag + 0 + (getRequirementsList().size() * 1);
        for (int i3 = 0; i3 < this.rules_.size(); i3++) {
            size += CodedOutputStream.computeMessageSize(6, this.rules_.get(i3));
        }
        if (!GeneratedMessageV3.isStringEmpty(this.producerNotificationChannel_)) {
            size += GeneratedMessageV3.computeStringSize(7, this.producerNotificationChannel_);
        }
        int serializedSize = size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Usage)) {
            return super.equals(obj);
        }
        Usage usage = (Usage) obj;
        return getRequirementsList().equals(usage.getRequirementsList()) && getRulesList().equals(usage.getRulesList()) && getProducerNotificationChannel().equals(usage.getProducerNotificationChannel()) && this.unknownFields.equals(usage.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getRequirementsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getRequirementsList().hashCode();
        }
        if (getRulesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getRulesList().hashCode();
        }
        int iHashCode2 = (((((iHashCode * 37) + 7) * 53) + getProducerNotificationChannel().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static Usage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Usage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Usage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Usage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Usage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Usage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Usage parseFrom(InputStream inputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Usage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Usage parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Usage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Usage parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Usage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Usage) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Usage usage) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(usage);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements UsageOrBuilder {
        private int bitField0_;
        private Object producerNotificationChannel_;
        private LazyStringList requirements_;
        private RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> rulesBuilder_;
        private List<UsageRule> rules_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return UsageProto.internal_static_google_api_Usage_fieldAccessorTable.ensureFieldAccessorsInitialized(Usage.class, Builder.class);
        }

        private Builder() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.rules_ = Collections.emptyList();
            this.producerNotificationChannel_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.rules_ = Collections.emptyList();
            this.producerNotificationChannel_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Usage.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.producerNotificationChannel_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return UsageProto.internal_static_google_api_Usage_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Usage getDefaultInstanceForType() {
            return Usage.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Usage build() {
            Usage usageBuildPartial = buildPartial();
            if (usageBuildPartial.isInitialized()) {
                return usageBuildPartial;
            }
            throw newUninitializedMessageException((Message) usageBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Usage buildPartial() {
            Usage usage = new Usage(this);
            if ((this.bitField0_ & 1) != 0) {
                this.requirements_ = this.requirements_.getUnmodifiableView();
                this.bitField0_ &= -2;
            }
            usage.requirements_ = this.requirements_;
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                usage.rules_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 2) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -3;
                }
                usage.rules_ = this.rules_;
            }
            usage.producerNotificationChannel_ = this.producerNotificationChannel_;
            onBuilt();
            return usage;
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
            if (message instanceof Usage) {
                return mergeFrom((Usage) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Usage usage) {
            if (usage == Usage.getDefaultInstance()) {
                return this;
            }
            if (!usage.requirements_.isEmpty()) {
                if (this.requirements_.isEmpty()) {
                    this.requirements_ = usage.requirements_;
                    this.bitField0_ &= -2;
                } else {
                    ensureRequirementsIsMutable();
                    this.requirements_.addAll(usage.requirements_);
                }
                onChanged();
            }
            if (this.rulesBuilder_ == null) {
                if (!usage.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = usage.rules_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(usage.rules_);
                    }
                    onChanged();
                }
            } else if (!usage.rules_.isEmpty()) {
                if (!this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.addAllMessages(usage.rules_);
                } else {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = usage.rules_;
                    this.bitField0_ &= -3;
                    this.rulesBuilder_ = Usage.alwaysUseFieldBuilders ? getRulesFieldBuilder() : null;
                }
            }
            if (!usage.getProducerNotificationChannel().isEmpty()) {
                this.producerNotificationChannel_ = usage.producerNotificationChannel_;
                onChanged();
            }
            mergeUnknownFields(usage.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.Usage.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Usage.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.Usage r3 = (com.google.api.Usage) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.Usage r4 = (com.google.api.Usage) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Usage.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Usage$Builder");
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.requirements_ = new LazyStringArrayList(this.requirements_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.UsageOrBuilder
        public ProtocolStringList getRequirementsList() {
            return this.requirements_.getUnmodifiableView();
        }

        @Override // com.google.api.UsageOrBuilder
        public int getRequirementsCount() {
            return this.requirements_.size();
        }

        @Override // com.google.api.UsageOrBuilder
        public String getRequirements(int i) {
            return (String) this.requirements_.get(i);
        }

        @Override // com.google.api.UsageOrBuilder
        public ByteString getRequirementsBytes(int i) {
            return this.requirements_.getByteString(i);
        }

        public Builder setRequirements(int i, String str) {
            str.getClass();
            ensureRequirementsIsMutable();
            this.requirements_.set(i, str);
            onChanged();
            return this;
        }

        public Builder addRequirements(String str) {
            str.getClass();
            ensureRequirementsIsMutable();
            this.requirements_.add(str);
            onChanged();
            return this;
        }

        public Builder addAllRequirements(Iterable<String> iterable) {
            ensureRequirementsIsMutable();
            AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.requirements_);
            onChanged();
            return this;
        }

        public Builder clearRequirements() {
            this.requirements_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -2;
            onChanged();
            return this;
        }

        public Builder addRequirementsBytes(ByteString byteString) {
            byteString.getClass();
            Usage.checkByteStringIsUtf8(byteString);
            ensureRequirementsIsMutable();
            this.requirements_.add(byteString);
            onChanged();
            return this;
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.google.api.UsageOrBuilder
        public List<UsageRule> getRulesList() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.UsageOrBuilder
        public int getRulesCount() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.UsageOrBuilder
        public UsageRule getRules(int i) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return (UsageRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                usageRule.getClass();
                ensureRulesIsMutable();
                this.rules_.set(i, usageRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, usageRule);
            }
            return this;
        }

        public Builder setRules(int i, UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                usageRule.getClass();
                ensureRulesIsMutable();
                this.rules_.add(usageRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(usageRule);
            }
            return this;
        }

        public Builder addRules(int i, UsageRule usageRule) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                usageRule.getClass();
                ensureRulesIsMutable();
                this.rules_.add(i, usageRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, usageRule);
            }
            return this;
        }

        public Builder addRules(UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, UsageRule.Builder builder) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends UsageRule> iterable) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public UsageRule.Builder getRulesBuilder(int i) {
            return (UsageRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.UsageOrBuilder
        public UsageRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return (UsageRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.UsageOrBuilder
        public List<? extends UsageRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public UsageRule.Builder addRulesBuilder() {
            return (UsageRule.Builder) getRulesFieldBuilder().addBuilder(UsageRule.getDefaultInstance());
        }

        public UsageRule.Builder addRulesBuilder(int i) {
            return (UsageRule.Builder) getRulesFieldBuilder().addBuilder(i, UsageRule.getDefaultInstance());
        }

        public List<UsageRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<UsageRule, UsageRule.Builder, UsageRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        @Override // com.google.api.UsageOrBuilder
        public String getProducerNotificationChannel() {
            Object obj = this.producerNotificationChannel_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.producerNotificationChannel_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.UsageOrBuilder
        public ByteString getProducerNotificationChannelBytes() {
            Object obj = this.producerNotificationChannel_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.producerNotificationChannel_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setProducerNotificationChannel(String str) {
            str.getClass();
            this.producerNotificationChannel_ = str;
            onChanged();
            return this;
        }

        public Builder clearProducerNotificationChannel() {
            this.producerNotificationChannel_ = Usage.getDefaultInstance().getProducerNotificationChannel();
            onChanged();
            return this;
        }

        public Builder setProducerNotificationChannelBytes(ByteString byteString) {
            byteString.getClass();
            Usage.checkByteStringIsUtf8(byteString);
            this.producerNotificationChannel_ = byteString;
            onChanged();
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

    public static Usage getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Usage> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Usage> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Usage getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
