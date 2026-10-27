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
public final class PreconditionFailure extends GeneratedMessageV3 implements PreconditionFailureOrBuilder {
    private static final PreconditionFailure DEFAULT_INSTANCE = new PreconditionFailure();
    private static final Parser<PreconditionFailure> PARSER = new AbstractParser<PreconditionFailure>() { // from class: com.google.rpc.PreconditionFailure.1
        @Override // com.google.protobuf.Parser
        public PreconditionFailure parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new PreconditionFailure(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VIOLATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<Violation> violations_;

    public interface ViolationOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getSubject();

        ByteString getSubjectBytes();

        String getType();

        ByteString getTypeBytes();
    }

    private PreconditionFailure(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private PreconditionFailure() {
        this.memoizedIsInitialized = (byte) -1;
        this.violations_ = Collections.emptyList();
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new PreconditionFailure();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private PreconditionFailure(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.violations_ = new ArrayList();
                                z2 |= true;
                            }
                            this.violations_.add((Violation) codedInputStream.readMessage(Violation.parser(), extensionRegistryLite));
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
                    this.violations_ = Collections.unmodifiableList(this.violations_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_fieldAccessorTable.ensureFieldAccessorsInitialized(PreconditionFailure.class, Builder.class);
    }

    public static final class Violation extends GeneratedMessageV3 implements ViolationOrBuilder {
        public static final int DESCRIPTION_FIELD_NUMBER = 3;
        public static final int SUBJECT_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private volatile Object description_;
        private byte memoizedIsInitialized;
        private volatile Object subject_;
        private volatile Object type_;
        private static final Violation DEFAULT_INSTANCE = new Violation();
        private static final Parser<Violation> PARSER = new AbstractParser<Violation>() { // from class: com.google.rpc.PreconditionFailure.Violation.1
            @Override // com.google.protobuf.Parser
            public Violation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Violation(codedInputStream, extensionRegistryLite);
            }
        };

        private Violation(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Violation() {
            this.memoizedIsInitialized = (byte) -1;
            this.type_ = "";
            this.subject_ = "";
            this.description_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new Violation();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Violation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.type_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.subject_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 26) {
                                this.description_ = codedInputStream.readStringRequireUtf8();
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
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public String getType() {
            Object obj = this.type_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.type_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.type_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public String getSubject() {
            Object obj = this.subject_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.subject_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public ByteString getSubjectBytes() {
            Object obj = this.subject_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.subject_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
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
            if (!GeneratedMessageV3.isStringEmpty(this.type_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.subject_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.subject_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.description_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.type_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.type_);
            if (!GeneratedMessageV3.isStringEmpty(this.subject_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.subject_);
            }
            if (!GeneratedMessageV3.isStringEmpty(this.description_)) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.description_);
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
            if (!(obj instanceof Violation)) {
                return super.equals(obj);
            }
            Violation violation = (Violation) obj;
            return getType().equals(violation.getType()) && getSubject().equals(violation.getSubject()) && getDescription().equals(violation.getDescription()) && this.unknownFields.equals(violation.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode()) * 37) + 2) * 53) + getSubject().hashCode()) * 37) + 3) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        public static Violation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static Violation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Violation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static Violation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Violation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static Violation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Violation parseFrom(InputStream inputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Violation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Violation violation) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(violation);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ViolationOrBuilder {
            private Object description_;
            private Object subject_;
            private Object type_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
            }

            private Builder() {
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Violation.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Violation getDefaultInstanceForType() {
                return Violation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Violation build() {
                Violation violationBuildPartial = buildPartial();
                if (violationBuildPartial.isInitialized()) {
                    return violationBuildPartial;
                }
                throw newUninitializedMessageException((Message) violationBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Violation buildPartial() {
                Violation violation = new Violation(this);
                violation.type_ = this.type_;
                violation.subject_ = this.subject_;
                violation.description_ = this.description_;
                onBuilt();
                return violation;
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
                if (message instanceof Violation) {
                    return mergeFrom((Violation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Violation violation) {
                if (violation == Violation.getDefaultInstance()) {
                    return this;
                }
                if (!violation.getType().isEmpty()) {
                    this.type_ = violation.type_;
                    onChanged();
                }
                if (!violation.getSubject().isEmpty()) {
                    this.subject_ = violation.subject_;
                    onChanged();
                }
                if (!violation.getDescription().isEmpty()) {
                    this.description_ = violation.description_;
                    onChanged();
                }
                mergeUnknownFields(violation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.rpc.PreconditionFailure.Violation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.PreconditionFailure.Violation.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.rpc.PreconditionFailure$Violation r3 = (com.google.rpc.PreconditionFailure.Violation) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.rpc.PreconditionFailure$Violation r4 = (com.google.rpc.PreconditionFailure.Violation) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.PreconditionFailure.Violation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.PreconditionFailure$Violation$Builder");
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public String getType() {
                Object obj = this.type_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.type_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public ByteString getTypeBytes() {
                Object obj = this.type_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.type_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setType(String str) {
                str.getClass();
                this.type_ = str;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.type_ = Violation.getDefaultInstance().getType();
                onChanged();
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                byteString.getClass();
                Violation.checkByteStringIsUtf8(byteString);
                this.type_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public String getSubject() {
                Object obj = this.subject_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.subject_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public ByteString getSubjectBytes() {
                Object obj = this.subject_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.subject_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setSubject(String str) {
                str.getClass();
                this.subject_ = str;
                onChanged();
                return this;
            }

            public Builder clearSubject() {
                this.subject_ = Violation.getDefaultInstance().getSubject();
                onChanged();
                return this;
            }

            public Builder setSubjectBytes(ByteString byteString) {
                byteString.getClass();
                Violation.checkByteStringIsUtf8(byteString);
                this.subject_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
            public String getDescription() {
                Object obj = this.description_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.description_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.rpc.PreconditionFailure.ViolationOrBuilder
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
                this.description_ = Violation.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                byteString.getClass();
                Violation.checkByteStringIsUtf8(byteString);
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

        public static Violation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Violation> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Violation> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Violation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public List<Violation> getViolationsList() {
        return this.violations_;
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
        return this.violations_;
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public int getViolationsCount() {
        return this.violations_.size();
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public Violation getViolations(int i) {
        return this.violations_.get(i);
    }

    @Override // com.google.rpc.PreconditionFailureOrBuilder
    public ViolationOrBuilder getViolationsOrBuilder(int i) {
        return this.violations_.get(i);
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
        for (int i = 0; i < this.violations_.size(); i++) {
            codedOutputStream.writeMessage(1, this.violations_.get(i));
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
        for (int i2 = 0; i2 < this.violations_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.violations_.get(i2));
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
        if (!(obj instanceof PreconditionFailure)) {
            return super.equals(obj);
        }
        PreconditionFailure preconditionFailure = (PreconditionFailure) obj;
        return getViolationsList().equals(preconditionFailure.getViolationsList()) && this.unknownFields.equals(preconditionFailure.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getViolationsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getViolationsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static PreconditionFailure parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static PreconditionFailure parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static PreconditionFailure parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static PreconditionFailure parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(InputStream inputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PreconditionFailure parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PreconditionFailure parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PreconditionFailure parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PreconditionFailure parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PreconditionFailure preconditionFailure) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(preconditionFailure);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PreconditionFailureOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> violationsBuilder_;
        private List<Violation> violations_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_fieldAccessorTable.ensureFieldAccessorsInitialized(PreconditionFailure.class, Builder.class);
        }

        private Builder() {
            this.violations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.violations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (PreconditionFailure.alwaysUseFieldBuilders) {
                getViolationsFieldBuilder();
            }
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public PreconditionFailure getDefaultInstanceForType() {
            return PreconditionFailure.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public PreconditionFailure build() {
            PreconditionFailure preconditionFailureBuildPartial = buildPartial();
            if (preconditionFailureBuildPartial.isInitialized()) {
                return preconditionFailureBuildPartial;
            }
            throw newUninitializedMessageException((Message) preconditionFailureBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public PreconditionFailure buildPartial() {
            PreconditionFailure preconditionFailure = new PreconditionFailure(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.violations_ = Collections.unmodifiableList(this.violations_);
                    this.bitField0_ &= -2;
                }
                preconditionFailure.violations_ = this.violations_;
            } else {
                preconditionFailure.violations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return preconditionFailure;
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
            if (message instanceof PreconditionFailure) {
                return mergeFrom((PreconditionFailure) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(PreconditionFailure preconditionFailure) {
            if (preconditionFailure == PreconditionFailure.getDefaultInstance()) {
                return this;
            }
            if (this.violationsBuilder_ == null) {
                if (!preconditionFailure.violations_.isEmpty()) {
                    if (this.violations_.isEmpty()) {
                        this.violations_ = preconditionFailure.violations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureViolationsIsMutable();
                        this.violations_.addAll(preconditionFailure.violations_);
                    }
                    onChanged();
                }
            } else if (!preconditionFailure.violations_.isEmpty()) {
                if (!this.violationsBuilder_.isEmpty()) {
                    this.violationsBuilder_.addAllMessages(preconditionFailure.violations_);
                } else {
                    this.violationsBuilder_.dispose();
                    this.violationsBuilder_ = null;
                    this.violations_ = preconditionFailure.violations_;
                    this.bitField0_ &= -2;
                    this.violationsBuilder_ = PreconditionFailure.alwaysUseFieldBuilders ? getViolationsFieldBuilder() : null;
                }
            }
            mergeUnknownFields(preconditionFailure.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.rpc.PreconditionFailure.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.PreconditionFailure.access$2000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.rpc.PreconditionFailure r3 = (com.google.rpc.PreconditionFailure) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.rpc.PreconditionFailure r4 = (com.google.rpc.PreconditionFailure) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.PreconditionFailure.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.PreconditionFailure$Builder");
        }

        private void ensureViolationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.violations_ = new ArrayList(this.violations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public List<Violation> getViolationsList() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.violations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public int getViolationsCount() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public Violation getViolations(int i) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.get(i);
            }
            return (Violation) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setViolations(int i, Violation violation) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                violation.getClass();
                ensureViolationsIsMutable();
                this.violations_.set(i, violation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, violation);
            }
            return this;
        }

        public Builder setViolations(int i, Violation.Builder builder) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addViolations(Violation violation) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                violation.getClass();
                ensureViolationsIsMutable();
                this.violations_.add(violation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(violation);
            }
            return this;
        }

        public Builder addViolations(int i, Violation violation) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                violation.getClass();
                ensureViolationsIsMutable();
                this.violations_.add(i, violation);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, violation);
            }
            return this;
        }

        public Builder addViolations(Violation.Builder builder) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addViolations(int i, Violation.Builder builder) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllViolations(Iterable<? extends Violation> iterable) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                AbstractMessageLite.Builder.addAll((Iterable) iterable, (List) this.violations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearViolations() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeViolations(int i) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Violation.Builder getViolationsBuilder(int i) {
            return (Violation.Builder) getViolationsFieldBuilder().getBuilder(i);
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public ViolationOrBuilder getViolationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.get(i);
            }
            return (ViolationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // com.google.rpc.PreconditionFailureOrBuilder
        public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
            RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.violations_);
        }

        public Violation.Builder addViolationsBuilder() {
            return (Violation.Builder) getViolationsFieldBuilder().addBuilder(Violation.getDefaultInstance());
        }

        public Violation.Builder addViolationsBuilder(int i) {
            return (Violation.Builder) getViolationsFieldBuilder().addBuilder(i, Violation.getDefaultInstance());
        }

        public List<Violation.Builder> getViolationsBuilderList() {
            return getViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Violation, Violation.Builder, ViolationOrBuilder> getViolationsFieldBuilder() {
            if (this.violationsBuilder_ == null) {
                this.violationsBuilder_ = new RepeatedFieldBuilderV3<>(this.violations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.violations_ = null;
            }
            return this.violationsBuilder_;
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

    public static PreconditionFailure getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PreconditionFailure> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<PreconditionFailure> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public PreconditionFailure getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
