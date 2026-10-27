package com.google.api;

import com.google.api.RoutingParameter;
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
public final class RoutingRule extends GeneratedMessageV3 implements RoutingRuleOrBuilder {
    private static final RoutingRule DEFAULT_INSTANCE = new RoutingRule();
    private static final Parser<RoutingRule> PARSER = new AbstractParser<RoutingRule>() { // from class: com.google.api.RoutingRule.1
        @Override // com.google.protobuf.Parser
        public RoutingRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RoutingRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int ROUTING_PARAMETERS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<RoutingParameter> routingParameters_;

    private RoutingRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RoutingRule() {
        this.memoizedIsInitialized = (byte) -1;
        this.routingParameters_ = Collections.emptyList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new RoutingRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RoutingRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 18) {
                            if (!(z2 & true)) {
                                this.routingParameters_ = new ArrayList();
                                z2 |= true;
                            }
                            this.routingParameters_.add((RoutingParameter) codedInputStream.readMessage(RoutingParameter.parser(), extensionRegistryLite));
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (UninitializedMessageException e2) {
                    throw e2.asInvalidProtocolBufferException().setUnfinishedMessage(this);
                } catch (IOException e3) {
                    throw new InvalidProtocolBufferException(e3).setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.routingParameters_ = Collections.unmodifiableList(this.routingParameters_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RoutingProto.internal_static_google_api_RoutingRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RoutingProto.internal_static_google_api_RoutingRule_fieldAccessorTable.ensureFieldAccessorsInitialized(RoutingRule.class, Builder.class);
    }

    @Override // com.google.api.RoutingRuleOrBuilder
    public List<RoutingParameter> getRoutingParametersList() {
        return this.routingParameters_;
    }

    @Override // com.google.api.RoutingRuleOrBuilder
    public List<? extends RoutingParameterOrBuilder> getRoutingParametersOrBuilderList() {
        return this.routingParameters_;
    }

    @Override // com.google.api.RoutingRuleOrBuilder
    public int getRoutingParametersCount() {
        return this.routingParameters_.size();
    }

    @Override // com.google.api.RoutingRuleOrBuilder
    public RoutingParameter getRoutingParameters(int i) {
        return this.routingParameters_.get(i);
    }

    @Override // com.google.api.RoutingRuleOrBuilder
    public RoutingParameterOrBuilder getRoutingParametersOrBuilder(int i) {
        return this.routingParameters_.get(i);
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
        for (int i = 0; i < this.routingParameters_.size(); i++) {
            codedOutputStream.writeMessage(2, this.routingParameters_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.routingParameters_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, this.routingParameters_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RoutingRule)) {
            return super.equals(obj);
        }
        RoutingRule routingRule = (RoutingRule) obj;
        return getRoutingParametersList().equals(routingRule.getRoutingParametersList()) && this.unknownFields.equals(routingRule.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getRoutingParametersCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getRoutingParametersList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static RoutingRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static RoutingRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RoutingRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static RoutingRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RoutingRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static RoutingRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RoutingRule parseFrom(InputStream inputStream) throws IOException {
        return (RoutingRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RoutingRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RoutingRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RoutingRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RoutingRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RoutingRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RoutingRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RoutingRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RoutingRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RoutingRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RoutingRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RoutingRule routingRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(routingRule);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoutingRuleOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> routingParametersBuilder_;
        private List<RoutingParameter> routingParameters_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RoutingProto.internal_static_google_api_RoutingRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RoutingProto.internal_static_google_api_RoutingRule_fieldAccessorTable.ensureFieldAccessorsInitialized(RoutingRule.class, Builder.class);
        }

        private Builder() {
            this.routingParameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.routingParameters_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (RoutingRule.alwaysUseFieldBuilders) {
                getRoutingParametersFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.routingParameters_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return RoutingProto.internal_static_google_api_RoutingRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RoutingRule getDefaultInstanceForType() {
            return RoutingRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RoutingRule build() {
            RoutingRule routingRuleBuildPartial = buildPartial();
            if (routingRuleBuildPartial.isInitialized()) {
                return routingRuleBuildPartial;
            }
            throw newUninitializedMessageException((Message) routingRuleBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RoutingRule buildPartial() {
            RoutingRule routingRule = new RoutingRule(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.routingParameters_ = Collections.unmodifiableList(this.routingParameters_);
                    this.bitField0_ &= -2;
                }
                routingRule.routingParameters_ = this.routingParameters_;
            } else {
                routingRule.routingParameters_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return routingRule;
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
            if (message instanceof RoutingRule) {
                return mergeFrom((RoutingRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RoutingRule routingRule) {
            if (routingRule == RoutingRule.getDefaultInstance()) {
                return this;
            }
            if (this.routingParametersBuilder_ == null) {
                if (!routingRule.routingParameters_.isEmpty()) {
                    if (this.routingParameters_.isEmpty()) {
                        this.routingParameters_ = routingRule.routingParameters_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRoutingParametersIsMutable();
                        this.routingParameters_.addAll(routingRule.routingParameters_);
                    }
                    onChanged();
                }
            } else if (!routingRule.routingParameters_.isEmpty()) {
                if (!this.routingParametersBuilder_.isEmpty()) {
                    this.routingParametersBuilder_.addAllMessages(routingRule.routingParameters_);
                } else {
                    this.routingParametersBuilder_.dispose();
                    this.routingParametersBuilder_ = null;
                    this.routingParameters_ = routingRule.routingParameters_;
                    this.bitField0_ &= -2;
                    this.routingParametersBuilder_ = RoutingRule.alwaysUseFieldBuilders ? getRoutingParametersFieldBuilder() : null;
                }
            }
            mergeUnknownFields(routingRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.RoutingRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.RoutingRule.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.RoutingRule r3 = (com.google.api.RoutingRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.RoutingRule r4 = (com.google.api.RoutingRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.RoutingRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.RoutingRule$Builder");
        }

        private void ensureRoutingParametersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.routingParameters_ = new ArrayList(this.routingParameters_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.RoutingRuleOrBuilder
        public List<RoutingParameter> getRoutingParametersList() {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.routingParameters_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.RoutingRuleOrBuilder
        public int getRoutingParametersCount() {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.routingParameters_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.RoutingRuleOrBuilder
        public RoutingParameter getRoutingParameters(int i) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.routingParameters_.get(i);
            }
            return (RoutingParameter) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRoutingParameters(int i, RoutingParameter routingParameter) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                routingParameter.getClass();
                ensureRoutingParametersIsMutable();
                this.routingParameters_.set(i, routingParameter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, routingParameter);
            }
            return this;
        }

        public Builder setRoutingParameters(int i, RoutingParameter.Builder builder) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutingParametersIsMutable();
                this.routingParameters_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRoutingParameters(RoutingParameter routingParameter) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                routingParameter.getClass();
                ensureRoutingParametersIsMutable();
                this.routingParameters_.add(routingParameter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(routingParameter);
            }
            return this;
        }

        public Builder addRoutingParameters(int i, RoutingParameter routingParameter) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                routingParameter.getClass();
                ensureRoutingParametersIsMutable();
                this.routingParameters_.add(i, routingParameter);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, routingParameter);
            }
            return this;
        }

        public Builder addRoutingParameters(RoutingParameter.Builder builder) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutingParametersIsMutable();
                this.routingParameters_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRoutingParameters(int i, RoutingParameter.Builder builder) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutingParametersIsMutable();
                this.routingParameters_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRoutingParameters(Iterable<? extends RoutingParameter> iterable) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutingParametersIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.routingParameters_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRoutingParameters() {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.routingParameters_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRoutingParameters(int i) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRoutingParametersIsMutable();
                this.routingParameters_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public RoutingParameter.Builder getRoutingParametersBuilder(int i) {
            return (RoutingParameter.Builder) getRoutingParametersFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.RoutingRuleOrBuilder
        public RoutingParameterOrBuilder getRoutingParametersOrBuilder(int i) {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.routingParameters_.get(i);
            }
            return (RoutingParameterOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.RoutingRuleOrBuilder
        public List<? extends RoutingParameterOrBuilder> getRoutingParametersOrBuilderList() {
            RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> repeatedFieldBuilderV3 = this.routingParametersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.routingParameters_);
        }

        public RoutingParameter.Builder addRoutingParametersBuilder() {
            return (RoutingParameter.Builder) getRoutingParametersFieldBuilder().addBuilder(RoutingParameter.getDefaultInstance());
        }

        public RoutingParameter.Builder addRoutingParametersBuilder(int i) {
            return (RoutingParameter.Builder) getRoutingParametersFieldBuilder().addBuilder(i, RoutingParameter.getDefaultInstance());
        }

        public List<RoutingParameter.Builder> getRoutingParametersBuilderList() {
            return getRoutingParametersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<RoutingParameter, RoutingParameter.Builder, RoutingParameterOrBuilder> getRoutingParametersFieldBuilder() {
            if (this.routingParametersBuilder_ == null) {
                this.routingParametersBuilder_ = new RepeatedFieldBuilderV3<>(this.routingParameters_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.routingParameters_ = null;
            }
            return this.routingParametersBuilder_;
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

    public static RoutingRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RoutingRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<RoutingRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public RoutingRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
