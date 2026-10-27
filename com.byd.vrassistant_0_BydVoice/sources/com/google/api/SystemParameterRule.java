package com.google.api;

import com.google.api.SystemParameter;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
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
public final class SystemParameterRule extends GeneratedMessageV3 implements SystemParameterRuleOrBuilder {
    public static final int PARAMETERS_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<SystemParameter> parameters_;
    private volatile Object selector_;
    private static final SystemParameterRule DEFAULT_INSTANCE = new SystemParameterRule();
    private static final Parser<SystemParameterRule> PARSER = new AbstractParser<SystemParameterRule>() { // from class: com.google.api.SystemParameterRule.1
        @Override // com.google.protobuf.Parser
        public SystemParameterRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new SystemParameterRule(codedInputStream, extensionRegistryLite);
        }
    };

    private SystemParameterRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private SystemParameterRule() {
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.parameters_ = Collections.emptyList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new SystemParameterRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private SystemParameterRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.parameters_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.parameters_.add((SystemParameter) codedInputStream.readMessage(SystemParameter.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.parameters_ = Collections.unmodifiableList(this.parameters_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SystemParameterProto.internal_static_google_api_SystemParameterRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SystemParameterProto.internal_static_google_api_SystemParameterRule_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameterRule.class, Builder.class);
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public List<SystemParameter> getParametersList() {
        return this.parameters_;
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public List<? extends SystemParameterOrBuilder> getParametersOrBuilderList() {
        return this.parameters_;
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public int getParametersCount() {
        return this.parameters_.size();
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public SystemParameter getParameters(int i) {
        return this.parameters_.get(i);
    }

    @Override // com.google.api.SystemParameterRuleOrBuilder
    public SystemParameterOrBuilder getParametersOrBuilder(int i) {
        return this.parameters_.get(i);
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
        for (int i = 0; i < this.parameters_.size(); i++) {
            codedOutputStream.writeMessage(2, this.parameters_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !GeneratedMessageV3.isStringEmpty(this.selector_) ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        for (int i2 = 0; i2 < this.parameters_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.parameters_.get(i2));
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
        if (!(obj instanceof SystemParameterRule)) {
            return super.equals(obj);
        }
        SystemParameterRule systemParameterRule = (SystemParameterRule) obj;
        return getSelector().equals(systemParameterRule.getSelector()) && getParametersList().equals(systemParameterRule.getParametersList()) && this.unknownFields.equals(systemParameterRule.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (getParametersCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getParametersList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static SystemParameterRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static SystemParameterRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static SystemParameterRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static SystemParameterRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(InputStream inputStream) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static SystemParameterRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static SystemParameterRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static SystemParameterRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static SystemParameterRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SystemParameterRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SystemParameterRule systemParameterRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(systemParameterRule);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SystemParameterRuleOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> parametersBuilder_;
        private List<SystemParameter> parameters_;
        private Object selector_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return SystemParameterProto.internal_static_google_api_SystemParameterRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SystemParameterProto.internal_static_google_api_SystemParameterRule_fieldAccessorTable.ensureFieldAccessorsInitialized(SystemParameterRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.parameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.parameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (SystemParameterRule.alwaysUseFieldBuilders) {
                getParametersFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.parameters_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return SystemParameterProto.internal_static_google_api_SystemParameterRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public SystemParameterRule getDefaultInstanceForType() {
            return SystemParameterRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SystemParameterRule build() {
            SystemParameterRule systemParameterRuleBuildPartial = buildPartial();
            if (systemParameterRuleBuildPartial.isInitialized()) {
                return systemParameterRuleBuildPartial;
            }
            throw newUninitializedMessageException((Message) systemParameterRuleBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public SystemParameterRule buildPartial() {
            SystemParameterRule systemParameterRule = new SystemParameterRule(this);
            systemParameterRule.selector_ = this.selector_;
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                systemParameterRule.parameters_ = repeatedFieldBuilderV3.build();
            } else {
                if ((this.bitField0_ & 1) != 0) {
                    this.parameters_ = Collections.unmodifiableList(this.parameters_);
                    this.bitField0_ &= -2;
                }
                systemParameterRule.parameters_ = this.parameters_;
            }
            onBuilt();
            return systemParameterRule;
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
            if (message instanceof SystemParameterRule) {
                return mergeFrom((SystemParameterRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(SystemParameterRule systemParameterRule) {
            if (systemParameterRule == SystemParameterRule.getDefaultInstance()) {
                return this;
            }
            if (!systemParameterRule.getSelector().isEmpty()) {
                this.selector_ = systemParameterRule.selector_;
                onChanged();
            }
            if (this.parametersBuilder_ == null) {
                if (!systemParameterRule.parameters_.isEmpty()) {
                    if (this.parameters_.isEmpty()) {
                        this.parameters_ = systemParameterRule.parameters_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureParametersIsMutable();
                        this.parameters_.addAll(systemParameterRule.parameters_);
                    }
                    onChanged();
                }
            } else if (!systemParameterRule.parameters_.isEmpty()) {
                if (!this.parametersBuilder_.isEmpty()) {
                    this.parametersBuilder_.addAllMessages(systemParameterRule.parameters_);
                } else {
                    this.parametersBuilder_.dispose();
                    this.parametersBuilder_ = null;
                    this.parameters_ = systemParameterRule.parameters_;
                    this.bitField0_ &= -2;
                    this.parametersBuilder_ = SystemParameterRule.alwaysUseFieldBuilders ? getParametersFieldBuilder() : null;
                }
            }
            mergeUnknownFields(systemParameterRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.SystemParameterRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.SystemParameterRule.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.SystemParameterRule r3 = (com.google.api.SystemParameterRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.SystemParameterRule r4 = (com.google.api.SystemParameterRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.SystemParameterRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.SystemParameterRule$Builder");
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
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
            this.selector_ = SystemParameterRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            byteString.getClass();
            SystemParameterRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        private void ensureParametersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.parameters_ = new ArrayList(this.parameters_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public List<SystemParameter> getParametersList() {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.parameters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public int getParametersCount() {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public SystemParameter getParameters(int i) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.get(i);
            }
            return (SystemParameter) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setParameters(int i, SystemParameter systemParameter) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                systemParameter.getClass();
                ensureParametersIsMutable();
                this.parameters_.set(i, systemParameter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, systemParameter);
            }
            return this;
        }

        public Builder setParameters(int i, SystemParameter.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addParameters(SystemParameter systemParameter) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                systemParameter.getClass();
                ensureParametersIsMutable();
                this.parameters_.add(systemParameter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(systemParameter);
            }
            return this;
        }

        public Builder addParameters(int i, SystemParameter systemParameter) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                systemParameter.getClass();
                ensureParametersIsMutable();
                this.parameters_.add(i, systemParameter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, systemParameter);
            }
            return this;
        }

        public Builder addParameters(SystemParameter.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addParameters(int i, SystemParameter.Builder builder) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllParameters(Iterable<? extends SystemParameter> iterable) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.parameters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearParameters() {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.parameters_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeParameters(int i) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureParametersIsMutable();
                this.parameters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public SystemParameter.Builder getParametersBuilder(int i) {
            return (SystemParameter.Builder) getParametersFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public SystemParameterOrBuilder getParametersOrBuilder(int i) {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.parameters_.get(i);
            }
            return (SystemParameterOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.SystemParameterRuleOrBuilder
        public List<? extends SystemParameterOrBuilder> getParametersOrBuilderList() {
            RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> repeatedFieldBuilderV3 = this.parametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.parameters_);
        }

        public SystemParameter.Builder addParametersBuilder() {
            return (SystemParameter.Builder) getParametersFieldBuilder().addBuilder(SystemParameter.getDefaultInstance());
        }

        public SystemParameter.Builder addParametersBuilder(int i) {
            return (SystemParameter.Builder) getParametersFieldBuilder().addBuilder(i, SystemParameter.getDefaultInstance());
        }

        public List<SystemParameter.Builder> getParametersBuilderList() {
            return getParametersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SystemParameter, SystemParameter.Builder, SystemParameterOrBuilder> getParametersFieldBuilder() {
            if (this.parametersBuilder_ == null) {
                this.parametersBuilder_ = new RepeatedFieldBuilderV3<>(this.parameters_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.parameters_ = null;
            }
            return this.parametersBuilder_;
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

    public static SystemParameterRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SystemParameterRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<SystemParameterRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public SystemParameterRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
