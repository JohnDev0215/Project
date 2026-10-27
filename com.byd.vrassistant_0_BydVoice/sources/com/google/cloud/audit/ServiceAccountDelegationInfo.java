package com.google.cloud.audit;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class ServiceAccountDelegationInfo extends GeneratedMessageV3 implements ServiceAccountDelegationInfoOrBuilder {
    public static final int FIRST_PARTY_PRINCIPAL_FIELD_NUMBER = 1;
    public static final int PRINCIPAL_SUBJECT_FIELD_NUMBER = 3;
    public static final int THIRD_PARTY_PRINCIPAL_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private int authorityCase_;
    private Object authority_;
    private byte memoizedIsInitialized;
    private volatile Object principalSubject_;
    private static final ServiceAccountDelegationInfo DEFAULT_INSTANCE = new ServiceAccountDelegationInfo();
    private static final Parser<ServiceAccountDelegationInfo> PARSER = new AbstractParser<ServiceAccountDelegationInfo>() { // from class: com.google.cloud.audit.ServiceAccountDelegationInfo.1
        @Override // com.google.protobuf.Parser
        public ServiceAccountDelegationInfo parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ServiceAccountDelegationInfo(codedInputStream, extensionRegistryLite);
        }
    };

    public interface FirstPartyPrincipalOrBuilder extends MessageOrBuilder {
        String getPrincipalEmail();

        ByteString getPrincipalEmailBytes();

        Struct getServiceMetadata();

        StructOrBuilder getServiceMetadataOrBuilder();

        boolean hasServiceMetadata();
    }

    public interface ThirdPartyPrincipalOrBuilder extends MessageOrBuilder {
        Struct getThirdPartyClaims();

        StructOrBuilder getThirdPartyClaimsOrBuilder();

        boolean hasThirdPartyClaims();
    }

    private ServiceAccountDelegationInfo(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.authorityCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private ServiceAccountDelegationInfo() {
        this.authorityCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.principalSubject_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new ServiceAccountDelegationInfo();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ServiceAccountDelegationInfo(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            FirstPartyPrincipal.Builder builder = this.authorityCase_ == 1 ? ((FirstPartyPrincipal) this.authority_).toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(FirstPartyPrincipal.parser(), extensionRegistryLite);
                            this.authority_ = message;
                            if (builder != null) {
                                builder.mergeFrom((FirstPartyPrincipal) message);
                                this.authority_ = builder.buildPartial();
                            }
                            this.authorityCase_ = 1;
                        } else if (tag == 18) {
                            ThirdPartyPrincipal.Builder builder2 = this.authorityCase_ == 2 ? ((ThirdPartyPrincipal) this.authority_).toBuilder() : null;
                            MessageLite message2 = codedInputStream.readMessage(ThirdPartyPrincipal.parser(), extensionRegistryLite);
                            this.authority_ = message2;
                            if (builder2 != null) {
                                builder2.mergeFrom((ThirdPartyPrincipal) message2);
                                this.authority_ = builder2.buildPartial();
                            }
                            this.authorityCase_ = 2;
                        } else if (tag == 26) {
                            this.principalSubject_ = codedInputStream.readStringRequireUtf8();
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
        return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceAccountDelegationInfo.class, Builder.class);
    }

    public static final class FirstPartyPrincipal extends GeneratedMessageV3 implements FirstPartyPrincipalOrBuilder {
        private static final FirstPartyPrincipal DEFAULT_INSTANCE = new FirstPartyPrincipal();
        private static final Parser<FirstPartyPrincipal> PARSER = new AbstractParser<FirstPartyPrincipal>() { // from class: com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipal.1
            @Override // com.google.protobuf.Parser
            public FirstPartyPrincipal parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FirstPartyPrincipal(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PRINCIPAL_EMAIL_FIELD_NUMBER = 1;
        public static final int SERVICE_METADATA_FIELD_NUMBER = 2;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private volatile Object principalEmail_;
        private Struct serviceMetadata_;

        private FirstPartyPrincipal(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private FirstPartyPrincipal() {
            this.memoizedIsInitialized = (byte) -1;
            this.principalEmail_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new FirstPartyPrincipal();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FirstPartyPrincipal(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.principalEmail_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                Struct struct = this.serviceMetadata_;
                                Struct.Builder builder = struct != null ? struct.toBuilder() : null;
                                Struct struct2 = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                this.serviceMetadata_ = struct2;
                                if (builder != null) {
                                    builder.mergeFrom(struct2);
                                    this.serviceMetadata_ = builder.buildPartial();
                                }
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
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_fieldAccessorTable.ensureFieldAccessorsInitialized(FirstPartyPrincipal.class, Builder.class);
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
        public String getPrincipalEmail() {
            Object obj = this.principalEmail_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.principalEmail_ = stringUtf8;
            return stringUtf8;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
        public ByteString getPrincipalEmailBytes() {
            Object obj = this.principalEmail_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.principalEmail_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
        public boolean hasServiceMetadata() {
            return this.serviceMetadata_ != null;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
        public Struct getServiceMetadata() {
            Struct struct = this.serviceMetadata_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
        public StructOrBuilder getServiceMetadataOrBuilder() {
            return getServiceMetadata();
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
            if (!GeneratedMessageV3.isStringEmpty(this.principalEmail_)) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.principalEmail_);
            }
            if (this.serviceMetadata_ != null) {
                codedOutputStream.writeMessage(2, getServiceMetadata());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.principalEmail_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.principalEmail_);
            if (this.serviceMetadata_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, getServiceMetadata());
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
            if (!(obj instanceof FirstPartyPrincipal)) {
                return super.equals(obj);
            }
            FirstPartyPrincipal firstPartyPrincipal = (FirstPartyPrincipal) obj;
            if (getPrincipalEmail().equals(firstPartyPrincipal.getPrincipalEmail()) && hasServiceMetadata() == firstPartyPrincipal.hasServiceMetadata()) {
                return (!hasServiceMetadata() || getServiceMetadata().equals(firstPartyPrincipal.getServiceMetadata())) && this.unknownFields.equals(firstPartyPrincipal.unknownFields);
            }
            return false;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getPrincipalEmail().hashCode();
            if (hasServiceMetadata()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getServiceMetadata().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public static FirstPartyPrincipal parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static FirstPartyPrincipal parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FirstPartyPrincipal parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static FirstPartyPrincipal parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FirstPartyPrincipal parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static FirstPartyPrincipal parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FirstPartyPrincipal parseFrom(InputStream inputStream) throws IOException {
            return (FirstPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FirstPartyPrincipal parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirstPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FirstPartyPrincipal parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FirstPartyPrincipal) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FirstPartyPrincipal parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirstPartyPrincipal) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FirstPartyPrincipal parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FirstPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FirstPartyPrincipal parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FirstPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FirstPartyPrincipal firstPartyPrincipal) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(firstPartyPrincipal);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FirstPartyPrincipalOrBuilder {
            private Object principalEmail_;
            private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> serviceMetadataBuilder_;
            private Struct serviceMetadata_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_fieldAccessorTable.ensureFieldAccessorsInitialized(FirstPartyPrincipal.class, Builder.class);
            }

            private Builder() {
                this.principalEmail_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.principalEmail_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = FirstPartyPrincipal.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.principalEmail_ = "";
                if (this.serviceMetadataBuilder_ == null) {
                    this.serviceMetadata_ = null;
                } else {
                    this.serviceMetadata_ = null;
                    this.serviceMetadataBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_FirstPartyPrincipal_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public FirstPartyPrincipal getDefaultInstanceForType() {
                return FirstPartyPrincipal.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FirstPartyPrincipal build() {
                FirstPartyPrincipal firstPartyPrincipalBuildPartial = buildPartial();
                if (firstPartyPrincipalBuildPartial.isInitialized()) {
                    return firstPartyPrincipalBuildPartial;
                }
                throw newUninitializedMessageException((Message) firstPartyPrincipalBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public FirstPartyPrincipal buildPartial() {
                FirstPartyPrincipal firstPartyPrincipal = new FirstPartyPrincipal(this);
                firstPartyPrincipal.principalEmail_ = this.principalEmail_;
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.serviceMetadataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    firstPartyPrincipal.serviceMetadata_ = this.serviceMetadata_;
                } else {
                    firstPartyPrincipal.serviceMetadata_ = (Struct) singleFieldBuilderV3.build();
                }
                onBuilt();
                return firstPartyPrincipal;
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
                if (message instanceof FirstPartyPrincipal) {
                    return mergeFrom((FirstPartyPrincipal) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FirstPartyPrincipal firstPartyPrincipal) {
                if (firstPartyPrincipal == FirstPartyPrincipal.getDefaultInstance()) {
                    return this;
                }
                if (!firstPartyPrincipal.getPrincipalEmail().isEmpty()) {
                    this.principalEmail_ = firstPartyPrincipal.principalEmail_;
                    onChanged();
                }
                if (firstPartyPrincipal.hasServiceMetadata()) {
                    mergeServiceMetadata(firstPartyPrincipal.getServiceMetadata());
                }
                mergeUnknownFields(firstPartyPrincipal.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipal.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipal.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.cloud.audit.ServiceAccountDelegationInfo$FirstPartyPrincipal r3 = (com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipal) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.cloud.audit.ServiceAccountDelegationInfo$FirstPartyPrincipal r4 = (com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipal) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipal.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.ServiceAccountDelegationInfo$FirstPartyPrincipal$Builder");
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
            public String getPrincipalEmail() {
                Object obj = this.principalEmail_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.principalEmail_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
            public ByteString getPrincipalEmailBytes() {
                Object obj = this.principalEmail_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.principalEmail_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setPrincipalEmail(String str) {
                str.getClass();
                this.principalEmail_ = str;
                onChanged();
                return this;
            }

            public Builder clearPrincipalEmail() {
                this.principalEmail_ = FirstPartyPrincipal.getDefaultInstance().getPrincipalEmail();
                onChanged();
                return this;
            }

            public Builder setPrincipalEmailBytes(ByteString byteString) {
                byteString.getClass();
                FirstPartyPrincipal.checkByteStringIsUtf8(byteString);
                this.principalEmail_ = byteString;
                onChanged();
                return this;
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
            public boolean hasServiceMetadata() {
                return (this.serviceMetadataBuilder_ == null && this.serviceMetadata_ == null) ? false : true;
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
            public Struct getServiceMetadata() {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.serviceMetadataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Struct struct = this.serviceMetadata_;
                    return struct == null ? Struct.getDefaultInstance() : struct;
                }
                return (Struct) singleFieldBuilderV3.getMessage();
            }

            public Builder setServiceMetadata(Struct struct) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.serviceMetadataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    struct.getClass();
                    this.serviceMetadata_ = struct;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(struct);
                }
                return this;
            }

            public Builder setServiceMetadata(Struct.Builder builder) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.serviceMetadataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.serviceMetadata_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeServiceMetadata(Struct struct) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.serviceMetadataBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Struct struct2 = this.serviceMetadata_;
                    if (struct2 != null) {
                        this.serviceMetadata_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                    } else {
                        this.serviceMetadata_ = struct;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                return this;
            }

            public Builder clearServiceMetadata() {
                if (this.serviceMetadataBuilder_ == null) {
                    this.serviceMetadata_ = null;
                    onChanged();
                } else {
                    this.serviceMetadata_ = null;
                    this.serviceMetadataBuilder_ = null;
                }
                return this;
            }

            public Struct.Builder getServiceMetadataBuilder() {
                onChanged();
                return (Struct.Builder) getServiceMetadataFieldBuilder().getBuilder();
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.FirstPartyPrincipalOrBuilder
            public StructOrBuilder getServiceMetadataOrBuilder() {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.serviceMetadataBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (StructOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Struct struct = this.serviceMetadata_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }

            private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getServiceMetadataFieldBuilder() {
                if (this.serviceMetadataBuilder_ == null) {
                    this.serviceMetadataBuilder_ = new SingleFieldBuilderV3<>(getServiceMetadata(), getParentForChildren(), isClean());
                    this.serviceMetadata_ = null;
                }
                return this.serviceMetadataBuilder_;
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

        public static FirstPartyPrincipal getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FirstPartyPrincipal> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<FirstPartyPrincipal> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public FirstPartyPrincipal getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public static final class ThirdPartyPrincipal extends GeneratedMessageV3 implements ThirdPartyPrincipalOrBuilder {
        private static final ThirdPartyPrincipal DEFAULT_INSTANCE = new ThirdPartyPrincipal();
        private static final Parser<ThirdPartyPrincipal> PARSER = new AbstractParser<ThirdPartyPrincipal>() { // from class: com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipal.1
            @Override // com.google.protobuf.Parser
            public ThirdPartyPrincipal parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ThirdPartyPrincipal(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int THIRD_PARTY_CLAIMS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized;
        private Struct thirdPartyClaims_;

        private ThirdPartyPrincipal(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private ThirdPartyPrincipal() {
            this.memoizedIsInitialized = (byte) -1;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new ThirdPartyPrincipal();
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ThirdPartyPrincipal(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    Struct struct = this.thirdPartyClaims_;
                                    Struct.Builder builder = struct != null ? struct.toBuilder() : null;
                                    Struct struct2 = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                    this.thirdPartyClaims_ = struct2;
                                    if (builder != null) {
                                        builder.mergeFrom(struct2);
                                        this.thirdPartyClaims_ = builder.buildPartial();
                                    }
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
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_fieldAccessorTable.ensureFieldAccessorsInitialized(ThirdPartyPrincipal.class, Builder.class);
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder
        public boolean hasThirdPartyClaims() {
            return this.thirdPartyClaims_ != null;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder
        public Struct getThirdPartyClaims() {
            Struct struct = this.thirdPartyClaims_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder
        public StructOrBuilder getThirdPartyClaimsOrBuilder() {
            return getThirdPartyClaims();
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
            if (this.thirdPartyClaims_ != null) {
                codedOutputStream.writeMessage(1, getThirdPartyClaims());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = (this.thirdPartyClaims_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getThirdPartyClaims()) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeMessageSize;
            return iComputeMessageSize;
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof ThirdPartyPrincipal)) {
                return super.equals(obj);
            }
            ThirdPartyPrincipal thirdPartyPrincipal = (ThirdPartyPrincipal) obj;
            if (hasThirdPartyClaims() != thirdPartyPrincipal.hasThirdPartyClaims()) {
                return false;
            }
            return (!hasThirdPartyClaims() || getThirdPartyClaims().equals(thirdPartyPrincipal.getThirdPartyClaims())) && this.unknownFields.equals(thirdPartyPrincipal.unknownFields);
        }

        @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (hasThirdPartyClaims()) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getThirdPartyClaims().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        public static ThirdPartyPrincipal parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer);
        }

        public static ThirdPartyPrincipal parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static ThirdPartyPrincipal parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString);
        }

        public static ThirdPartyPrincipal parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ThirdPartyPrincipal parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr);
        }

        public static ThirdPartyPrincipal parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ThirdPartyPrincipal parseFrom(InputStream inputStream) throws IOException {
            return (ThirdPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static ThirdPartyPrincipal parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThirdPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ThirdPartyPrincipal parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ThirdPartyPrincipal) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static ThirdPartyPrincipal parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThirdPartyPrincipal) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static ThirdPartyPrincipal parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ThirdPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static ThirdPartyPrincipal parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThirdPartyPrincipal) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ThirdPartyPrincipal thirdPartyPrincipal) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(thirdPartyPrincipal);
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

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ThirdPartyPrincipalOrBuilder {
            private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> thirdPartyClaimsBuilder_;
            private Struct thirdPartyClaims_;

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder
            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_fieldAccessorTable.ensureFieldAccessorsInitialized(ThirdPartyPrincipal.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ThirdPartyPrincipal.alwaysUseFieldBuilders;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                if (this.thirdPartyClaimsBuilder_ == null) {
                    this.thirdPartyClaims_ = null;
                } else {
                    this.thirdPartyClaims_ = null;
                    this.thirdPartyClaimsBuilder_ = null;
                }
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_ThirdPartyPrincipal_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ThirdPartyPrincipal getDefaultInstanceForType() {
                return ThirdPartyPrincipal.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ThirdPartyPrincipal build() {
                ThirdPartyPrincipal thirdPartyPrincipalBuildPartial = buildPartial();
                if (thirdPartyPrincipalBuildPartial.isInitialized()) {
                    return thirdPartyPrincipalBuildPartial;
                }
                throw newUninitializedMessageException((Message) thirdPartyPrincipalBuildPartial);
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ThirdPartyPrincipal buildPartial() {
                ThirdPartyPrincipal thirdPartyPrincipal = new ThirdPartyPrincipal(this);
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.thirdPartyClaimsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    thirdPartyPrincipal.thirdPartyClaims_ = this.thirdPartyClaims_;
                } else {
                    thirdPartyPrincipal.thirdPartyClaims_ = (Struct) singleFieldBuilderV3.build();
                }
                onBuilt();
                return thirdPartyPrincipal;
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
                if (message instanceof ThirdPartyPrincipal) {
                    return mergeFrom((ThirdPartyPrincipal) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ThirdPartyPrincipal thirdPartyPrincipal) {
                if (thirdPartyPrincipal == ThirdPartyPrincipal.getDefaultInstance()) {
                    return this;
                }
                if (thirdPartyPrincipal.hasThirdPartyClaims()) {
                    mergeThirdPartyClaims(thirdPartyPrincipal.getThirdPartyClaims());
                }
                mergeUnknownFields(thirdPartyPrincipal.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipal.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipal.access$1600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    com.google.cloud.audit.ServiceAccountDelegationInfo$ThirdPartyPrincipal r3 = (com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipal) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    com.google.cloud.audit.ServiceAccountDelegationInfo$ThirdPartyPrincipal r4 = (com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipal) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipal.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.ServiceAccountDelegationInfo$ThirdPartyPrincipal$Builder");
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder
            public boolean hasThirdPartyClaims() {
                return (this.thirdPartyClaimsBuilder_ == null && this.thirdPartyClaims_ == null) ? false : true;
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder
            public Struct getThirdPartyClaims() {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.thirdPartyClaimsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Struct struct = this.thirdPartyClaims_;
                    return struct == null ? Struct.getDefaultInstance() : struct;
                }
                return (Struct) singleFieldBuilderV3.getMessage();
            }

            public Builder setThirdPartyClaims(Struct struct) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.thirdPartyClaimsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    struct.getClass();
                    this.thirdPartyClaims_ = struct;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(struct);
                }
                return this;
            }

            public Builder setThirdPartyClaims(Struct.Builder builder) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.thirdPartyClaimsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.thirdPartyClaims_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeThirdPartyClaims(Struct struct) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.thirdPartyClaimsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Struct struct2 = this.thirdPartyClaims_;
                    if (struct2 != null) {
                        this.thirdPartyClaims_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                    } else {
                        this.thirdPartyClaims_ = struct;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                return this;
            }

            public Builder clearThirdPartyClaims() {
                if (this.thirdPartyClaimsBuilder_ == null) {
                    this.thirdPartyClaims_ = null;
                    onChanged();
                } else {
                    this.thirdPartyClaims_ = null;
                    this.thirdPartyClaimsBuilder_ = null;
                }
                return this;
            }

            public Struct.Builder getThirdPartyClaimsBuilder() {
                onChanged();
                return (Struct.Builder) getThirdPartyClaimsFieldBuilder().getBuilder();
            }

            @Override // com.google.cloud.audit.ServiceAccountDelegationInfo.ThirdPartyPrincipalOrBuilder
            public StructOrBuilder getThirdPartyClaimsOrBuilder() {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.thirdPartyClaimsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (StructOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                Struct struct = this.thirdPartyClaims_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }

            private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getThirdPartyClaimsFieldBuilder() {
                if (this.thirdPartyClaimsBuilder_ == null) {
                    this.thirdPartyClaimsBuilder_ = new SingleFieldBuilderV3<>(getThirdPartyClaims(), getParentForChildren(), isClean());
                    this.thirdPartyClaims_ = null;
                }
                return this.thirdPartyClaimsBuilder_;
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

        public static ThirdPartyPrincipal getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ThirdPartyPrincipal> parser() {
            return PARSER;
        }

        @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ThirdPartyPrincipal> getParserForType() {
            return PARSER;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ThirdPartyPrincipal getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public enum AuthorityCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        FIRST_PARTY_PRINCIPAL(1),
        THIRD_PARTY_PRINCIPAL(2),
        AUTHORITY_NOT_SET(0);

        private final int value;

        AuthorityCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static AuthorityCase valueOf(int i) {
            return forNumber(i);
        }

        public static AuthorityCase forNumber(int i) {
            if (i == 0) {
                return AUTHORITY_NOT_SET;
            }
            if (i == 1) {
                return FIRST_PARTY_PRINCIPAL;
            }
            if (i != 2) {
                return null;
            }
            return THIRD_PARTY_PRINCIPAL;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public AuthorityCase getAuthorityCase() {
        return AuthorityCase.forNumber(this.authorityCase_);
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public String getPrincipalSubject() {
        Object obj = this.principalSubject_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.principalSubject_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public ByteString getPrincipalSubjectBytes() {
        Object obj = this.principalSubject_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.principalSubject_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public boolean hasFirstPartyPrincipal() {
        return this.authorityCase_ == 1;
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public FirstPartyPrincipal getFirstPartyPrincipal() {
        if (this.authorityCase_ == 1) {
            return (FirstPartyPrincipal) this.authority_;
        }
        return FirstPartyPrincipal.getDefaultInstance();
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public FirstPartyPrincipalOrBuilder getFirstPartyPrincipalOrBuilder() {
        if (this.authorityCase_ == 1) {
            return (FirstPartyPrincipal) this.authority_;
        }
        return FirstPartyPrincipal.getDefaultInstance();
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public boolean hasThirdPartyPrincipal() {
        return this.authorityCase_ == 2;
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public ThirdPartyPrincipal getThirdPartyPrincipal() {
        if (this.authorityCase_ == 2) {
            return (ThirdPartyPrincipal) this.authority_;
        }
        return ThirdPartyPrincipal.getDefaultInstance();
    }

    @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
    public ThirdPartyPrincipalOrBuilder getThirdPartyPrincipalOrBuilder() {
        if (this.authorityCase_ == 2) {
            return (ThirdPartyPrincipal) this.authority_;
        }
        return ThirdPartyPrincipal.getDefaultInstance();
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
        if (this.authorityCase_ == 1) {
            codedOutputStream.writeMessage(1, (FirstPartyPrincipal) this.authority_);
        }
        if (this.authorityCase_ == 2) {
            codedOutputStream.writeMessage(2, (ThirdPartyPrincipal) this.authority_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.principalSubject_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.principalSubject_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.authorityCase_ == 1 ? 0 + CodedOutputStream.computeMessageSize(1, (FirstPartyPrincipal) this.authority_) : 0;
        if (this.authorityCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (ThirdPartyPrincipal) this.authority_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.principalSubject_)) {
            iComputeMessageSize += GeneratedMessageV3.computeStringSize(3, this.principalSubject_);
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
        if (!(obj instanceof ServiceAccountDelegationInfo)) {
            return super.equals(obj);
        }
        ServiceAccountDelegationInfo serviceAccountDelegationInfo = (ServiceAccountDelegationInfo) obj;
        if (!getPrincipalSubject().equals(serviceAccountDelegationInfo.getPrincipalSubject()) || !getAuthorityCase().equals(serviceAccountDelegationInfo.getAuthorityCase())) {
            return false;
        }
        int i = this.authorityCase_;
        if (i == 1) {
            if (!getFirstPartyPrincipal().equals(serviceAccountDelegationInfo.getFirstPartyPrincipal())) {
                return false;
            }
        } else if (i == 2 && !getThirdPartyPrincipal().equals(serviceAccountDelegationInfo.getThirdPartyPrincipal())) {
            return false;
        }
        return this.unknownFields.equals(serviceAccountDelegationInfo.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 3) * 53) + getPrincipalSubject().hashCode();
        int i2 = this.authorityCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getFirstPartyPrincipal().hashCode();
        } else {
            if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getThirdPartyPrincipal().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    public static ServiceAccountDelegationInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static ServiceAccountDelegationInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ServiceAccountDelegationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static ServiceAccountDelegationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ServiceAccountDelegationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static ServiceAccountDelegationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ServiceAccountDelegationInfo parseFrom(InputStream inputStream) throws IOException {
        return (ServiceAccountDelegationInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ServiceAccountDelegationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServiceAccountDelegationInfo) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServiceAccountDelegationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ServiceAccountDelegationInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ServiceAccountDelegationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServiceAccountDelegationInfo) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ServiceAccountDelegationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ServiceAccountDelegationInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ServiceAccountDelegationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ServiceAccountDelegationInfo) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ServiceAccountDelegationInfo serviceAccountDelegationInfo) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(serviceAccountDelegationInfo);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServiceAccountDelegationInfoOrBuilder {
        private int authorityCase_;
        private Object authority_;
        private SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> firstPartyPrincipalBuilder_;
        private Object principalSubject_;
        private SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> thirdPartyPrincipalBuilder_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceAccountDelegationInfo.class, Builder.class);
        }

        private Builder() {
            this.authorityCase_ = 0;
            this.principalSubject_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.authorityCase_ = 0;
            this.principalSubject_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ServiceAccountDelegationInfo.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.principalSubject_ = "";
            this.authorityCase_ = 0;
            this.authority_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return AuditLogProto.internal_static_google_cloud_audit_ServiceAccountDelegationInfo_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ServiceAccountDelegationInfo getDefaultInstanceForType() {
            return ServiceAccountDelegationInfo.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ServiceAccountDelegationInfo build() {
            ServiceAccountDelegationInfo serviceAccountDelegationInfoBuildPartial = buildPartial();
            if (serviceAccountDelegationInfoBuildPartial.isInitialized()) {
                return serviceAccountDelegationInfoBuildPartial;
            }
            throw newUninitializedMessageException((Message) serviceAccountDelegationInfoBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public ServiceAccountDelegationInfo buildPartial() {
            ServiceAccountDelegationInfo serviceAccountDelegationInfo = new ServiceAccountDelegationInfo(this);
            serviceAccountDelegationInfo.principalSubject_ = this.principalSubject_;
            if (this.authorityCase_ == 1) {
                SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_;
                if (singleFieldBuilderV3 == null) {
                    serviceAccountDelegationInfo.authority_ = this.authority_;
                } else {
                    serviceAccountDelegationInfo.authority_ = singleFieldBuilderV3.build();
                }
            }
            if (this.authorityCase_ == 2) {
                SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV32 = this.thirdPartyPrincipalBuilder_;
                if (singleFieldBuilderV32 == null) {
                    serviceAccountDelegationInfo.authority_ = this.authority_;
                } else {
                    serviceAccountDelegationInfo.authority_ = singleFieldBuilderV32.build();
                }
            }
            serviceAccountDelegationInfo.authorityCase_ = this.authorityCase_;
            onBuilt();
            return serviceAccountDelegationInfo;
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
            if (message instanceof ServiceAccountDelegationInfo) {
                return mergeFrom((ServiceAccountDelegationInfo) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ServiceAccountDelegationInfo serviceAccountDelegationInfo) {
            if (serviceAccountDelegationInfo == ServiceAccountDelegationInfo.getDefaultInstance()) {
                return this;
            }
            if (!serviceAccountDelegationInfo.getPrincipalSubject().isEmpty()) {
                this.principalSubject_ = serviceAccountDelegationInfo.principalSubject_;
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$com$google$cloud$audit$ServiceAccountDelegationInfo$AuthorityCase[serviceAccountDelegationInfo.getAuthorityCase().ordinal()];
            if (i == 1) {
                mergeFirstPartyPrincipal(serviceAccountDelegationInfo.getFirstPartyPrincipal());
            } else if (i == 2) {
                mergeThirdPartyPrincipal(serviceAccountDelegationInfo.getThirdPartyPrincipal());
            }
            mergeUnknownFields(serviceAccountDelegationInfo.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.cloud.audit.ServiceAccountDelegationInfo.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.cloud.audit.ServiceAccountDelegationInfo.access$2600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.cloud.audit.ServiceAccountDelegationInfo r3 = (com.google.cloud.audit.ServiceAccountDelegationInfo) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.cloud.audit.ServiceAccountDelegationInfo r4 = (com.google.cloud.audit.ServiceAccountDelegationInfo) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.cloud.audit.ServiceAccountDelegationInfo.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.cloud.audit.ServiceAccountDelegationInfo$Builder");
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public AuthorityCase getAuthorityCase() {
            return AuthorityCase.forNumber(this.authorityCase_);
        }

        public Builder clearAuthority() {
            this.authorityCase_ = 0;
            this.authority_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public String getPrincipalSubject() {
            Object obj = this.principalSubject_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.principalSubject_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public ByteString getPrincipalSubjectBytes() {
            Object obj = this.principalSubject_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.principalSubject_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setPrincipalSubject(String str) {
            str.getClass();
            this.principalSubject_ = str;
            onChanged();
            return this;
        }

        public Builder clearPrincipalSubject() {
            this.principalSubject_ = ServiceAccountDelegationInfo.getDefaultInstance().getPrincipalSubject();
            onChanged();
            return this;
        }

        public Builder setPrincipalSubjectBytes(ByteString byteString) {
            byteString.getClass();
            ServiceAccountDelegationInfo.checkByteStringIsUtf8(byteString);
            this.principalSubject_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public boolean hasFirstPartyPrincipal() {
            return this.authorityCase_ == 1;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public FirstPartyPrincipal getFirstPartyPrincipal() {
            SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.authorityCase_ == 1) {
                    return (FirstPartyPrincipal) this.authority_;
                }
                return FirstPartyPrincipal.getDefaultInstance();
            }
            if (this.authorityCase_ == 1) {
                return (FirstPartyPrincipal) singleFieldBuilderV3.getMessage();
            }
            return FirstPartyPrincipal.getDefaultInstance();
        }

        public Builder setFirstPartyPrincipal(FirstPartyPrincipal firstPartyPrincipal) {
            SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                firstPartyPrincipal.getClass();
                this.authority_ = firstPartyPrincipal;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(firstPartyPrincipal);
            }
            this.authorityCase_ = 1;
            return this;
        }

        public Builder setFirstPartyPrincipal(FirstPartyPrincipal.Builder builder) {
            SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authority_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.authorityCase_ = 1;
            return this;
        }

        public Builder mergeFirstPartyPrincipal(FirstPartyPrincipal firstPartyPrincipal) {
            SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.authorityCase_ == 1 && this.authority_ != FirstPartyPrincipal.getDefaultInstance()) {
                    this.authority_ = FirstPartyPrincipal.newBuilder((FirstPartyPrincipal) this.authority_).mergeFrom(firstPartyPrincipal).buildPartial();
                } else {
                    this.authority_ = firstPartyPrincipal;
                }
                onChanged();
            } else if (this.authorityCase_ == 1) {
                singleFieldBuilderV3.mergeFrom(firstPartyPrincipal);
            } else {
                singleFieldBuilderV3.setMessage(firstPartyPrincipal);
            }
            this.authorityCase_ = 1;
            return this;
        }

        public Builder clearFirstPartyPrincipal() {
            SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.authorityCase_ == 1) {
                    this.authorityCase_ = 0;
                    this.authority_ = null;
                    onChanged();
                }
            } else {
                if (this.authorityCase_ == 1) {
                    this.authorityCase_ = 0;
                    this.authority_ = null;
                }
                singleFieldBuilderV3.clear();
            }
            return this;
        }

        public FirstPartyPrincipal.Builder getFirstPartyPrincipalBuilder() {
            return (FirstPartyPrincipal.Builder) getFirstPartyPrincipalFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public FirstPartyPrincipalOrBuilder getFirstPartyPrincipalOrBuilder() {
            SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> singleFieldBuilderV3;
            int i = this.authorityCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.firstPartyPrincipalBuilder_) != null) {
                return (FirstPartyPrincipalOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (FirstPartyPrincipal) this.authority_;
            }
            return FirstPartyPrincipal.getDefaultInstance();
        }

        private SingleFieldBuilderV3<FirstPartyPrincipal, FirstPartyPrincipal.Builder, FirstPartyPrincipalOrBuilder> getFirstPartyPrincipalFieldBuilder() {
            if (this.firstPartyPrincipalBuilder_ == null) {
                if (this.authorityCase_ != 1) {
                    this.authority_ = FirstPartyPrincipal.getDefaultInstance();
                }
                this.firstPartyPrincipalBuilder_ = new SingleFieldBuilderV3<>((FirstPartyPrincipal) this.authority_, getParentForChildren(), isClean());
                this.authority_ = null;
            }
            this.authorityCase_ = 1;
            onChanged();
            return this.firstPartyPrincipalBuilder_;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public boolean hasThirdPartyPrincipal() {
            return this.authorityCase_ == 2;
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public ThirdPartyPrincipal getThirdPartyPrincipal() {
            SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.thirdPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.authorityCase_ == 2) {
                    return (ThirdPartyPrincipal) this.authority_;
                }
                return ThirdPartyPrincipal.getDefaultInstance();
            }
            if (this.authorityCase_ == 2) {
                return (ThirdPartyPrincipal) singleFieldBuilderV3.getMessage();
            }
            return ThirdPartyPrincipal.getDefaultInstance();
        }

        public Builder setThirdPartyPrincipal(ThirdPartyPrincipal thirdPartyPrincipal) {
            SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.thirdPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                thirdPartyPrincipal.getClass();
                this.authority_ = thirdPartyPrincipal;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(thirdPartyPrincipal);
            }
            this.authorityCase_ = 2;
            return this;
        }

        public Builder setThirdPartyPrincipal(ThirdPartyPrincipal.Builder builder) {
            SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.thirdPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.authority_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            this.authorityCase_ = 2;
            return this;
        }

        public Builder mergeThirdPartyPrincipal(ThirdPartyPrincipal thirdPartyPrincipal) {
            SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.thirdPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.authorityCase_ == 2 && this.authority_ != ThirdPartyPrincipal.getDefaultInstance()) {
                    this.authority_ = ThirdPartyPrincipal.newBuilder((ThirdPartyPrincipal) this.authority_).mergeFrom(thirdPartyPrincipal).buildPartial();
                } else {
                    this.authority_ = thirdPartyPrincipal;
                }
                onChanged();
            } else if (this.authorityCase_ == 2) {
                singleFieldBuilderV3.mergeFrom(thirdPartyPrincipal);
            } else {
                singleFieldBuilderV3.setMessage(thirdPartyPrincipal);
            }
            this.authorityCase_ = 2;
            return this;
        }

        public Builder clearThirdPartyPrincipal() {
            SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV3 = this.thirdPartyPrincipalBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.authorityCase_ == 2) {
                    this.authorityCase_ = 0;
                    this.authority_ = null;
                    onChanged();
                }
            } else {
                if (this.authorityCase_ == 2) {
                    this.authorityCase_ = 0;
                    this.authority_ = null;
                }
                singleFieldBuilderV3.clear();
            }
            return this;
        }

        public ThirdPartyPrincipal.Builder getThirdPartyPrincipalBuilder() {
            return (ThirdPartyPrincipal.Builder) getThirdPartyPrincipalFieldBuilder().getBuilder();
        }

        @Override // com.google.cloud.audit.ServiceAccountDelegationInfoOrBuilder
        public ThirdPartyPrincipalOrBuilder getThirdPartyPrincipalOrBuilder() {
            SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> singleFieldBuilderV3;
            int i = this.authorityCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.thirdPartyPrincipalBuilder_) != null) {
                return (ThirdPartyPrincipalOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (ThirdPartyPrincipal) this.authority_;
            }
            return ThirdPartyPrincipal.getDefaultInstance();
        }

        private SingleFieldBuilderV3<ThirdPartyPrincipal, ThirdPartyPrincipal.Builder, ThirdPartyPrincipalOrBuilder> getThirdPartyPrincipalFieldBuilder() {
            if (this.thirdPartyPrincipalBuilder_ == null) {
                if (this.authorityCase_ != 2) {
                    this.authority_ = ThirdPartyPrincipal.getDefaultInstance();
                }
                this.thirdPartyPrincipalBuilder_ = new SingleFieldBuilderV3<>((ThirdPartyPrincipal) this.authority_, getParentForChildren(), isClean());
                this.authority_ = null;
            }
            this.authorityCase_ = 2;
            onChanged();
            return this.thirdPartyPrincipalBuilder_;
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

    /* JADX INFO: renamed from: com.google.cloud.audit.ServiceAccountDelegationInfo$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$cloud$audit$ServiceAccountDelegationInfo$AuthorityCase;

        static {
            int[] iArr = new int[AuthorityCase.values().length];
            $SwitchMap$com$google$cloud$audit$ServiceAccountDelegationInfo$AuthorityCase = iArr;
            try {
                iArr[AuthorityCase.FIRST_PARTY_PRINCIPAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$cloud$audit$ServiceAccountDelegationInfo$AuthorityCase[AuthorityCase.THIRD_PARTY_PRINCIPAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$cloud$audit$ServiceAccountDelegationInfo$AuthorityCase[AuthorityCase.AUTHORITY_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static ServiceAccountDelegationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ServiceAccountDelegationInfo> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<ServiceAccountDelegationInfo> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public ServiceAccountDelegationInfo getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
