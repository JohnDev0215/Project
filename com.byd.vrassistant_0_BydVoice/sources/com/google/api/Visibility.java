package com.google.api;

import com.google.api.VisibilityRule;
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
public final class Visibility extends GeneratedMessageV3 implements VisibilityOrBuilder {
    private static final Visibility DEFAULT_INSTANCE = new Visibility();
    private static final Parser<Visibility> PARSER = new AbstractParser<Visibility>() { // from class: com.google.api.Visibility.1
        @Override // com.google.protobuf.Parser
        public Visibility parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Visibility(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int RULES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<VisibilityRule> rules_;

    private Visibility(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Visibility() {
        this.memoizedIsInitialized = (byte) -1;
        this.rules_ = Collections.emptyList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Visibility();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Visibility(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 10) {
                            if (!(z2 & true)) {
                                this.rules_ = new ArrayList();
                                z2 |= true;
                            }
                            this.rules_.add((VisibilityRule) codedInputStream.readMessage(VisibilityRule.parser(), extensionRegistryLite));
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
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return VisibilityProto.internal_static_google_api_Visibility_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return VisibilityProto.internal_static_google_api_Visibility_fieldAccessorTable.ensureFieldAccessorsInitialized(Visibility.class, Builder.class);
    }

    @Override // com.google.api.VisibilityOrBuilder
    public List<VisibilityRule> getRulesList() {
        return this.rules_;
    }

    @Override // com.google.api.VisibilityOrBuilder
    public List<? extends VisibilityRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    @Override // com.google.api.VisibilityOrBuilder
    public int getRulesCount() {
        return this.rules_.size();
    }

    @Override // com.google.api.VisibilityOrBuilder
    public VisibilityRule getRules(int i) {
        return this.rules_.get(i);
    }

    @Override // com.google.api.VisibilityOrBuilder
    public VisibilityRuleOrBuilder getRulesOrBuilder(int i) {
        return this.rules_.get(i);
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
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(1, this.rules_.get(i));
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
        for (int i2 = 0; i2 < this.rules_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.rules_.get(i2));
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
        if (!(obj instanceof Visibility)) {
            return super.equals(obj);
        }
        Visibility visibility = (Visibility) obj;
        return getRulesList().equals(visibility.getRulesList()) && this.unknownFields.equals(visibility.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getRulesCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getRulesList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static Visibility parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Visibility parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Visibility parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Visibility parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Visibility parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Visibility parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Visibility parseFrom(InputStream inputStream) throws IOException {
        return (Visibility) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Visibility parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Visibility) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Visibility parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Visibility) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Visibility parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Visibility) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Visibility parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Visibility) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Visibility parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Visibility) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Visibility visibility) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(visibility);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements VisibilityOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> rulesBuilder_;
        private List<VisibilityRule> rules_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return VisibilityProto.internal_static_google_api_Visibility_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return VisibilityProto.internal_static_google_api_Visibility_fieldAccessorTable.ensureFieldAccessorsInitialized(Visibility.class, Builder.class);
        }

        private Builder() {
            this.rules_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.rules_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Visibility.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return VisibilityProto.internal_static_google_api_Visibility_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Visibility getDefaultInstanceForType() {
            return Visibility.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Visibility build() {
            Visibility visibilityBuildPartial = buildPartial();
            if (visibilityBuildPartial.isInitialized()) {
                return visibilityBuildPartial;
            }
            throw newUninitializedMessageException((Message) visibilityBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Visibility buildPartial() {
            Visibility visibility = new Visibility(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -2;
                }
                visibility.rules_ = this.rules_;
            } else {
                visibility.rules_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return visibility;
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
            if (message instanceof Visibility) {
                return mergeFrom((Visibility) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Visibility visibility) {
            if (visibility == Visibility.getDefaultInstance()) {
                return this;
            }
            if (this.rulesBuilder_ == null) {
                if (!visibility.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = visibility.rules_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(visibility.rules_);
                    }
                    onChanged();
                }
            } else if (!visibility.rules_.isEmpty()) {
                if (!this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.addAllMessages(visibility.rules_);
                } else {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = visibility.rules_;
                    this.bitField0_ &= -2;
                    this.rulesBuilder_ = Visibility.alwaysUseFieldBuilders ? getRulesFieldBuilder() : null;
                }
            }
            mergeUnknownFields(visibility.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.Visibility.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Visibility.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.Visibility r3 = (com.google.api.Visibility) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.Visibility r4 = (com.google.api.Visibility) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Visibility.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Visibility$Builder");
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.api.VisibilityOrBuilder
        public List<VisibilityRule> getRulesList() {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.api.VisibilityOrBuilder
        public int getRulesCount() {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.api.VisibilityOrBuilder
        public VisibilityRule getRules(int i) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return (VisibilityRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, VisibilityRule visibilityRule) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                visibilityRule.getClass();
                ensureRulesIsMutable();
                this.rules_.set(i, visibilityRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, visibilityRule);
            }
            return this;
        }

        public Builder setRules(int i, VisibilityRule.Builder builder) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(VisibilityRule visibilityRule) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                visibilityRule.getClass();
                ensureRulesIsMutable();
                this.rules_.add(visibilityRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(visibilityRule);
            }
            return this;
        }

        public Builder addRules(int i, VisibilityRule visibilityRule) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                visibilityRule.getClass();
                ensureRulesIsMutable();
                this.rules_.add(i, visibilityRule);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, visibilityRule);
            }
            return this;
        }

        public Builder addRules(VisibilityRule.Builder builder) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, VisibilityRule.Builder builder) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends VisibilityRule> iterable) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
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
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public VisibilityRule.Builder getRulesBuilder(int i) {
            return (VisibilityRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        @Override // com.google.api.VisibilityOrBuilder
        public VisibilityRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.get(i);
            }
            return (VisibilityRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.api.VisibilityOrBuilder
        public List<? extends VisibilityRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public VisibilityRule.Builder addRulesBuilder() {
            return (VisibilityRule.Builder) getRulesFieldBuilder().addBuilder(VisibilityRule.getDefaultInstance());
        }

        public VisibilityRule.Builder addRulesBuilder(int i) {
            return (VisibilityRule.Builder) getRulesFieldBuilder().addBuilder(i, VisibilityRule.getDefaultInstance());
        }

        public List<VisibilityRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<VisibilityRule, VisibilityRule.Builder, VisibilityRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(this.rules_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
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

    public static Visibility getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Visibility> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Visibility> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Visibility getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
