package com.google.rpc;

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
import com.google.protobuf.MessageOrBuilder;
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

/* JADX INFO: loaded from: classes3.dex */
public final class BadRequest extends GeneratedMessageV3 implements BadRequestOrBuilder {
    public static final int FIELD_VIOLATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<FieldViolation> fieldViolations_;
    private byte memoizedIsInitialized;
    private static final BadRequest DEFAULT_INSTANCE = new BadRequest();
    private static final Parser<BadRequest> PARSER = new AbstractParser<BadRequest>() { // from class: com.google.rpc.BadRequest.1
        @Override // com.google.protobuf.Parser
        public BadRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BadRequest(codedInputStream, extensionRegistryLite);
        }
    };

    public interface FieldViolationOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getField();

        ByteString getFieldBytes();
    }

    private BadRequest(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private BadRequest() {
        this.memoizedIsInitialized = (byte) -1;
        this.fieldViolations_ = Collections.emptyList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BadRequest();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BadRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.fieldViolations_ = new ArrayList();
                                z2 |= true;
                            }
                            this.fieldViolations_.add((FieldViolation) codedInputStream.readMessage(FieldViolation.parser(), extensionRegistryLite));
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
                    this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_BadRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(BadRequest.class, Builder.class);
    }

    public static final class FieldViolation extends GeneratedMessageV3 implements FieldViolationOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object description_;
        private volatile Object field_;
        private byte memoizedIsInitialized;
        private static final FieldViolation DEFAULT_INSTANCE = new FieldViolation();
        private static final Parser<FieldViolation> PARSER = new AbstractParser<FieldViolation>() { // from class: com.google.rpc.BadRequest.FieldViolation.1
            @Override // com.google.protobuf.Parser
            public FieldViolation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FieldViolation(codedInputStream, extensionRegistryLite);
            }
        };

        private FieldViolation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FieldViolation() {
            this.memoizedIsInitialized = (byte) -1;
            this.field_ = "";
            this.description_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new FieldViolation();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FieldViolation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.description_ = codedInputStream.readStringRequireUtf8();
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
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldViolation.class, Builder.class);
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public String getField() {
            Object obj = this.field_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.field_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public ByteString getFieldBytes() {
            Object obj = this.field_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.field_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.description_ = byteStringCopyFromUtf8;
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
            if (!GeneratedMessageV3.isStringEmpty(this.description_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
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
            if (!GeneratedMessageV3.isStringEmpty(this.description_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.description_);
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
            if (!(obj instanceof FieldViolation)) {
                return super.equals(obj);
            }
            FieldViolation fieldViolation = (FieldViolation) obj;
            return getField().equals(fieldViolation.getField()) && getDescription().equals(fieldViolation.getDescription()) && this.unknownFields.equals(fieldViolation.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getField().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static FieldViolation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static FieldViolation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static FieldViolation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static FieldViolation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(InputStream inputStream) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FieldViolation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldViolation fieldViolation) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(fieldViolation);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FieldViolationOrBuilder {
            private Object description_;
            private Object field_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldViolation.class, Builder.class);
            }

            private Builder() {
                this.field_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.field_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FieldViolation.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.field_ = "";
                this.description_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public FieldViolation getDefaultInstanceForType() {
                return FieldViolation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldViolation build() {
                FieldViolation fieldViolationBuildPartial = buildPartial();
                if (fieldViolationBuildPartial.isInitialized()) {
                    return fieldViolationBuildPartial;
                }
                throw newUninitializedMessageException((Message) fieldViolationBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FieldViolation buildPartial() {
                FieldViolation fieldViolation = new FieldViolation(this);
                fieldViolation.field_ = this.field_;
                fieldViolation.description_ = this.description_;
                onBuilt();
                return fieldViolation;
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
                if (message instanceof FieldViolation) {
                    return mergeFrom((FieldViolation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FieldViolation fieldViolation) {
                if (fieldViolation == FieldViolation.getDefaultInstance()) {
                    return this;
                }
                if (!fieldViolation.getField().isEmpty()) {
                    this.field_ = fieldViolation.field_;
                    onChanged();
                }
                if (!fieldViolation.getDescription().isEmpty()) {
                    this.description_ = fieldViolation.description_;
                    onChanged();
                }
                mergeUnknownFields(fieldViolation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.rpc.BadRequest.FieldViolation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.BadRequest.FieldViolation.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.rpc.BadRequest$FieldViolation r3 = (com.google.rpc.BadRequest.FieldViolation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.rpc.BadRequest$FieldViolation r4 = (com.google.rpc.BadRequest.FieldViolation) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.BadRequest.FieldViolation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.BadRequest$FieldViolation$Builder");
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public String getField() {
                Object obj = this.field_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.field_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
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
                this.field_ = FieldViolation.getDefaultInstance().getField();
                onChanged();
                return this;
            }

            public Builder setFieldBytes(ByteString byteString) {
                byteString.getClass();
                FieldViolation.checkByteStringIsUtf8(byteString);
                this.field_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public String getDescription() {
                Object obj = this.description_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.description_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.BadRequest.FieldViolationOrBuilder
            public ByteString getDescriptionBytes() {
                Object obj = this.description_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.description_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setDescription(String str) {
                str.getClass();
                this.description_ = str;
                onChanged();
                return this;
            }

            public Builder clearDescription() {
                this.description_ = FieldViolation.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                byteString.getClass();
                FieldViolation.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
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

        public static FieldViolation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldViolation> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<FieldViolation> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public FieldViolation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public List<FieldViolation> getFieldViolationsList() {
        return this.fieldViolations_;
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
        return this.fieldViolations_;
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public int getFieldViolationsCount() {
        return this.fieldViolations_.size();
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public FieldViolation getFieldViolations(int i) {
        return this.fieldViolations_.get(i);
    }

    @Override // com.google.rpc.BadRequestOrBuilder
    public FieldViolationOrBuilder getFieldViolationsOrBuilder(int i) {
        return this.fieldViolations_.get(i);
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
        for (int i = 0; i < this.fieldViolations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.fieldViolations_.get(i));
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
        for (int i2 = 0; i2 < this.fieldViolations_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.fieldViolations_.get(i2));
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
        if (!(obj instanceof BadRequest)) {
            return super.equals(obj);
        }
        BadRequest badRequest = (BadRequest) obj;
        return getFieldViolationsList().equals(badRequest.getFieldViolationsList()) && this.unknownFields.equals(badRequest.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getFieldViolationsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getFieldViolationsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static BadRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BadRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BadRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BadRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BadRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BadRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BadRequest parseFrom(InputStream inputStream) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BadRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BadRequest badRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(badRequest);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BadRequestOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> fieldViolationsBuilder_;
        private List<FieldViolation> fieldViolations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(BadRequest.class, Builder.class);
        }

        private Builder() {
            this.fieldViolations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.fieldViolations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (BadRequest.alwaysUseFieldBuilders) {
                getFieldViolationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fieldViolations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BadRequest getDefaultInstanceForType() {
            return BadRequest.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BadRequest build() {
            BadRequest badRequestBuildPartial = buildPartial();
            if (badRequestBuildPartial.isInitialized()) {
                return badRequestBuildPartial;
            }
            throw newUninitializedMessageException((Message) badRequestBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BadRequest buildPartial() {
            BadRequest badRequest = new BadRequest(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
                    this.bitField0_ &= -2;
                }
                badRequest.fieldViolations_ = this.fieldViolations_;
            } else {
                badRequest.fieldViolations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return badRequest;
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
            if (message instanceof BadRequest) {
                return mergeFrom((BadRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BadRequest badRequest) {
            if (badRequest == BadRequest.getDefaultInstance()) {
                return this;
            }
            if (this.fieldViolationsBuilder_ == null) {
                if (!badRequest.fieldViolations_.isEmpty()) {
                    if (this.fieldViolations_.isEmpty()) {
                        this.fieldViolations_ = badRequest.fieldViolations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFieldViolationsIsMutable();
                        this.fieldViolations_.addAll(badRequest.fieldViolations_);
                    }
                    onChanged();
                }
            } else if (!badRequest.fieldViolations_.isEmpty()) {
                if (!this.fieldViolationsBuilder_.isEmpty()) {
                    this.fieldViolationsBuilder_.addAllMessages(badRequest.fieldViolations_);
                } else {
                    this.fieldViolationsBuilder_.dispose();
                    this.fieldViolationsBuilder_ = null;
                    this.fieldViolations_ = badRequest.fieldViolations_;
                    this.bitField0_ &= -2;
                    this.fieldViolationsBuilder_ = BadRequest.alwaysUseFieldBuilders ? getFieldViolationsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(badRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.rpc.BadRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.BadRequest.access$1800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.BadRequest r3 = (com.google.rpc.BadRequest) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.BadRequest r4 = (com.google.rpc.BadRequest) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.BadRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.BadRequest$Builder");
        }

        private void ensureFieldViolationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.fieldViolations_ = new ArrayList(this.fieldViolations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public List<FieldViolation> getFieldViolationsList() {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.fieldViolations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public int getFieldViolationsCount() {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fieldViolations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public FieldViolation getFieldViolations(int i) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fieldViolations_.get(i);
            }
            return (FieldViolation) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFieldViolations(int i, FieldViolation fieldViolation) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                fieldViolation.getClass();
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.set(i, fieldViolation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, fieldViolation);
            }
            return this;
        }

        public Builder setFieldViolations(int i, FieldViolation.Builder builder) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addFieldViolations(FieldViolation fieldViolation) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                fieldViolation.getClass();
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(fieldViolation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(fieldViolation);
            }
            return this;
        }

        public Builder addFieldViolations(int i, FieldViolation fieldViolation) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                fieldViolation.getClass();
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(i, fieldViolation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, fieldViolation);
            }
            return this;
        }

        public Builder addFieldViolations(FieldViolation.Builder builder) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addFieldViolations(int i, FieldViolation.Builder builder) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllFieldViolations(Iterable<? extends FieldViolation> iterable) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.fieldViolations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFieldViolations() {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fieldViolations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFieldViolations(int i) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public FieldViolation.Builder getFieldViolationsBuilder(int i) {
            return (FieldViolation.Builder) getFieldViolationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public FieldViolationOrBuilder getFieldViolationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fieldViolations_.get(i);
            }
            return (FieldViolationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.rpc.BadRequestOrBuilder
        public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
            RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fieldViolations_);
        }

        public FieldViolation.Builder addFieldViolationsBuilder() {
            return (FieldViolation.Builder) getFieldViolationsFieldBuilder().addBuilder(FieldViolation.getDefaultInstance());
        }

        public FieldViolation.Builder addFieldViolationsBuilder(int i) {
            return (FieldViolation.Builder) getFieldViolationsFieldBuilder().addBuilder(i, FieldViolation.getDefaultInstance());
        }

        public List<FieldViolation.Builder> getFieldViolationsBuilderList() {
            return getFieldViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<FieldViolation, FieldViolation.Builder, FieldViolationOrBuilder> getFieldViolationsFieldBuilder() {
            if (this.fieldViolationsBuilder_ == null) {
                this.fieldViolationsBuilder_ = new RepeatedFieldBuilderV3<>(this.fieldViolations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.fieldViolations_ = null;
            }
            return this.fieldViolationsBuilder_;
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

    public static BadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BadRequest> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BadRequest> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BadRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
