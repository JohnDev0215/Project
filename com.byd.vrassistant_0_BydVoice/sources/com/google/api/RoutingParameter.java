package com.google.api;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class RoutingParameter extends GeneratedMessageV3 implements RoutingParameterOrBuilder {
    public static final int FIELD_FIELD_NUMBER = 1;
    public static final int PATH_TEMPLATE_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private volatile Object field_;
    private byte memoizedIsInitialized;
    private volatile Object pathTemplate_;
    private static final RoutingParameter DEFAULT_INSTANCE = new RoutingParameter();
    private static final Parser<RoutingParameter> PARSER = new AbstractParser<RoutingParameter>() { // from class: com.google.api.RoutingParameter.1
        @Override // com.google.protobuf.Parser
        public RoutingParameter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RoutingParameter(codedInputStream, extensionRegistryLite);
        }
    };

    private RoutingParameter(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RoutingParameter() {
        this.memoizedIsInitialized = (byte) -1;
        this.field_ = "";
        this.pathTemplate_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new RoutingParameter();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private RoutingParameter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        while (!z) {
            try {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                this.field_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.pathTemplate_ = codedInputStream.readStringRequireUtf8();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RoutingProto.internal_static_google_api_RoutingParameter_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RoutingProto.internal_static_google_api_RoutingParameter_fieldAccessorTable.ensureFieldAccessorsInitialized(RoutingParameter.class, Builder.class);
    }

    @Override // com.google.api.RoutingParameterOrBuilder
    public String getField() {
        Object obj = this.field_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.field_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.RoutingParameterOrBuilder
    public ByteString getFieldBytes() {
        Object obj = this.field_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.field_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.RoutingParameterOrBuilder
    public String getPathTemplate() {
        Object obj = this.pathTemplate_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.pathTemplate_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.RoutingParameterOrBuilder
    public ByteString getPathTemplateBytes() {
        Object obj = this.pathTemplate_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.pathTemplate_ = byteStringCopyFromUtf8;
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
        if (!GeneratedMessageV3.isStringEmpty(this.field_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.field_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.pathTemplate_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pathTemplate_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.field_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.field_);
        if (!GeneratedMessageV3.isStringEmpty(this.pathTemplate_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.pathTemplate_);
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
        if (!(obj instanceof RoutingParameter)) {
            return super.equals(obj);
        }
        RoutingParameter routingParameter = (RoutingParameter) obj;
        return getField().equals(routingParameter.getField()) && getPathTemplate().equals(routingParameter.getPathTemplate()) && this.unknownFields.equals(routingParameter.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getField().hashCode()) * 37) + 2) * 53) + getPathTemplate().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    public static RoutingParameter parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static RoutingParameter parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RoutingParameter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static RoutingParameter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RoutingParameter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static RoutingParameter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RoutingParameter parseFrom(InputStream inputStream) throws IOException {
        return (RoutingParameter) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RoutingParameter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RoutingParameter) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RoutingParameter parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RoutingParameter) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RoutingParameter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RoutingParameter) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RoutingParameter parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RoutingParameter) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RoutingParameter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RoutingParameter) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RoutingParameter routingParameter) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(routingParameter);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RoutingParameterOrBuilder {
        private Object field_;
        private Object pathTemplate_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RoutingProto.internal_static_google_api_RoutingParameter_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RoutingProto.internal_static_google_api_RoutingParameter_fieldAccessorTable.ensureFieldAccessorsInitialized(RoutingParameter.class, Builder.class);
        }

        private Builder() {
            this.field_ = "";
            this.pathTemplate_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.field_ = "";
            this.pathTemplate_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RoutingParameter.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.field_ = "";
            this.pathTemplate_ = "";
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return RoutingProto.internal_static_google_api_RoutingParameter_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public RoutingParameter getDefaultInstanceForType() {
            return RoutingParameter.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RoutingParameter build() {
            RoutingParameter routingParameterBuildPartial = buildPartial();
            if (routingParameterBuildPartial.isInitialized()) {
                return routingParameterBuildPartial;
            }
            throw newUninitializedMessageException((Message) routingParameterBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public RoutingParameter buildPartial() {
            RoutingParameter routingParameter = new RoutingParameter(this);
            routingParameter.field_ = this.field_;
            routingParameter.pathTemplate_ = this.pathTemplate_;
            onBuilt();
            return routingParameter;
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
            if (message instanceof RoutingParameter) {
                return mergeFrom((RoutingParameter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RoutingParameter routingParameter) {
            if (routingParameter == RoutingParameter.getDefaultInstance()) {
                return this;
            }
            if (!routingParameter.getField().isEmpty()) {
                this.field_ = routingParameter.field_;
                onChanged();
            }
            if (!routingParameter.getPathTemplate().isEmpty()) {
                this.pathTemplate_ = routingParameter.pathTemplate_;
                onChanged();
            }
            mergeUnknownFields(routingParameter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.RoutingParameter.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.RoutingParameter.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.RoutingParameter r3 = (com.google.api.RoutingParameter) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.RoutingParameter r4 = (com.google.api.RoutingParameter) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.RoutingParameter.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.RoutingParameter$Builder");
        }

        @Override // com.google.api.RoutingParameterOrBuilder
        public String getField() {
            Object obj = this.field_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.field_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.RoutingParameterOrBuilder
        public ByteString getFieldBytes() {
            Object obj = this.field_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.field_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setField(String str) {
            str.getClass();
            this.field_ = str;
            onChanged();
            return this;
        }

        public Builder clearField() {
            this.field_ = RoutingParameter.getDefaultInstance().getField();
            onChanged();
            return this;
        }

        public Builder setFieldBytes(ByteString byteString) {
            byteString.getClass();
            RoutingParameter.checkByteStringIsUtf8(byteString);
            this.field_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.api.RoutingParameterOrBuilder
        public String getPathTemplate() {
            Object obj = this.pathTemplate_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.pathTemplate_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.RoutingParameterOrBuilder
        public ByteString getPathTemplateBytes() {
            Object obj = this.pathTemplate_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.pathTemplate_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPathTemplate(String str) {
            str.getClass();
            this.pathTemplate_ = str;
            onChanged();
            return this;
        }

        public Builder clearPathTemplate() {
            this.pathTemplate_ = RoutingParameter.getDefaultInstance().getPathTemplate();
            onChanged();
            return this;
        }

        public Builder setPathTemplateBytes(ByteString byteString) {
            byteString.getClass();
            RoutingParameter.checkByteStringIsUtf8(byteString);
            this.pathTemplate_ = byteString;
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

    public static RoutingParameter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RoutingParameter> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<RoutingParameter> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public RoutingParameter getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
