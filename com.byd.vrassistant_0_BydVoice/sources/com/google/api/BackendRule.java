package com.google.api;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class BackendRule extends GeneratedMessageV3 implements BackendRuleOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 2;
    public static final int DEADLINE_FIELD_NUMBER = 3;
    public static final int DISABLE_AUTH_FIELD_NUMBER = 8;
    public static final int JWT_AUDIENCE_FIELD_NUMBER = 7;
    public static final int MIN_DEADLINE_FIELD_NUMBER = 4;
    public static final int OPERATION_DEADLINE_FIELD_NUMBER = 5;
    public static final int PATH_TRANSLATION_FIELD_NUMBER = 6;
    public static final int PROTOCOL_FIELD_NUMBER = 9;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private volatile Object address_;
    private int authenticationCase_;
    private Object authentication_;
    private double deadline_;
    private byte memoizedIsInitialized;
    private double minDeadline_;
    private double operationDeadline_;
    private int pathTranslation_;
    private volatile Object protocol_;
    private volatile Object selector_;
    private static final BackendRule DEFAULT_INSTANCE = new BackendRule();
    private static final Parser<BackendRule> PARSER = new AbstractParser<BackendRule>() { // from class: com.google.api.BackendRule.1
        @Override // com.google.protobuf.Parser
        public BackendRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BackendRule(codedInputStream, extensionRegistryLite);
        }
    };

    private BackendRule(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.authenticationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private BackendRule() {
        this.authenticationCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.selector_ = "";
        this.address_ = "";
        this.pathTranslation_ = 0;
        this.protocol_ = "";
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new BackendRule();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BackendRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.selector_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 18) {
                                this.address_ = codedInputStream.readStringRequireUtf8();
                            } else if (tag == 25) {
                                this.deadline_ = codedInputStream.readDouble();
                            } else if (tag == 33) {
                                this.minDeadline_ = codedInputStream.readDouble();
                            } else if (tag == 41) {
                                this.operationDeadline_ = codedInputStream.readDouble();
                            } else if (tag == 48) {
                                this.pathTranslation_ = codedInputStream.readEnum();
                            } else if (tag == 58) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.authenticationCase_ = 7;
                                this.authentication_ = stringRequireUtf8;
                            } else if (tag == 64) {
                                this.authentication_ = Boolean.valueOf(codedInputStream.readBool());
                                this.authenticationCase_ = 8;
                            } else if (tag == 74) {
                                this.protocol_ = codedInputStream.readStringRequireUtf8();
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
        return BackendProto.internal_static_google_api_BackendRule_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
    }

    public enum PathTranslation implements ProtocolMessageEnum {
        PATH_TRANSLATION_UNSPECIFIED(0),
        CONSTANT_ADDRESS(1),
        APPEND_PATH_TO_ADDRESS(2),
        UNRECOGNIZED(-1);

        public static final int APPEND_PATH_TO_ADDRESS_VALUE = 2;
        public static final int CONSTANT_ADDRESS_VALUE = 1;
        public static final int PATH_TRANSLATION_UNSPECIFIED_VALUE = 0;
        private final int value;
        private static final Internal.EnumLiteMap<PathTranslation> internalValueMap = new Internal.EnumLiteMap<PathTranslation>() { // from class: com.google.api.BackendRule.PathTranslation.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PathTranslation findValueByNumber(int i) {
                return PathTranslation.forNumber(i);
            }
        };
        private static final PathTranslation[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public final int getNumber() {
            if (this == UNRECOGNIZED) {
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }
            return this.value;
        }

        @Deprecated
        public static PathTranslation valueOf(int i) {
            return forNumber(i);
        }

        public static PathTranslation forNumber(int i) {
            if (i == 0) {
                return PATH_TRANSLATION_UNSPECIFIED;
            }
            if (i == 1) {
                return CONSTANT_ADDRESS;
            }
            if (i != 2) {
                return null;
            }
            return APPEND_PATH_TO_ADDRESS;
        }

        public static Internal.EnumLiteMap<PathTranslation> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return getDescriptor().getValues().get(ordinal());
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return BackendRule.getDescriptor().getEnumTypes().get(0);
        }

        public static PathTranslation valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            }
            return VALUES[enumValueDescriptor.getIndex()];
        }

        PathTranslation(int i) {
            this.value = i;
        }
    }

    public enum AuthenticationCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        JWT_AUDIENCE(7),
        DISABLE_AUTH(8),
        AUTHENTICATION_NOT_SET(0);

        private final int value;

        AuthenticationCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static AuthenticationCase valueOf(int i) {
            return forNumber(i);
        }

        public static AuthenticationCase forNumber(int i) {
            if (i == 0) {
                return AUTHENTICATION_NOT_SET;
            }
            if (i == 7) {
                return JWT_AUDIENCE;
            }
            if (i != 8) {
                return null;
            }
            return DISABLE_AUTH;
        }

        @Override // com.google.protobuf.Internal.EnumLite, com.google.protobuf.AbstractMessageLite.InternalOneOfEnum
        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public AuthenticationCase getAuthenticationCase() {
        return AuthenticationCase.forNumber(this.authenticationCase_);
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getAddress() {
        Object obj = this.address_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.address_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getAddressBytes() {
        Object obj = this.address_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.address_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getDeadline() {
        return this.deadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getMinDeadline() {
        return this.minDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public double getOperationDeadline() {
        return this.operationDeadline_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public int getPathTranslationValue() {
        return this.pathTranslation_;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public PathTranslation getPathTranslation() {
        PathTranslation pathTranslationValueOf = PathTranslation.valueOf(this.pathTranslation_);
        return pathTranslationValueOf == null ? PathTranslation.UNRECOGNIZED : pathTranslationValueOf;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean hasJwtAudience() {
        return this.authenticationCase_ == 7;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getJwtAudience() {
        String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.authenticationCase_ == 7) {
            this.authentication_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getJwtAudienceBytes() {
        String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.authenticationCase_ == 7) {
                this.authentication_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean hasDisableAuth() {
        return this.authenticationCase_ == 8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public boolean getDisableAuth() {
        if (this.authenticationCase_ == 8) {
            return ((Boolean) this.authentication_).booleanValue();
        }
        return false;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public String getProtocol() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.protocol_ = stringUtf8;
        return stringUtf8;
    }

    @Override // com.google.api.BackendRuleOrBuilder
    public ByteString getProtocolBytes() {
        Object obj = this.protocol_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.protocol_ = byteStringCopyFromUtf8;
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
        if (!GeneratedMessageV3.isStringEmpty(this.selector_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (!GeneratedMessageV3.isStringEmpty(this.address_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.address_);
        }
        if (Double.doubleToRawLongBits(this.deadline_) != 0) {
            codedOutputStream.writeDouble(3, this.deadline_);
        }
        if (Double.doubleToRawLongBits(this.minDeadline_) != 0) {
            codedOutputStream.writeDouble(4, this.minDeadline_);
        }
        if (Double.doubleToRawLongBits(this.operationDeadline_) != 0) {
            codedOutputStream.writeDouble(5, this.operationDeadline_);
        }
        if (this.pathTranslation_ != PathTranslation.PATH_TRANSLATION_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(6, this.pathTranslation_);
        }
        if (this.authenticationCase_ == 7) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.authentication_);
        }
        if (this.authenticationCase_ == 8) {
            codedOutputStream.writeBool(8, ((Boolean) this.authentication_).booleanValue());
        }
        if (!GeneratedMessageV3.isStringEmpty(this.protocol_)) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.protocol_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = GeneratedMessageV3.isStringEmpty(this.selector_) ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.selector_);
        if (!GeneratedMessageV3.isStringEmpty(this.address_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.address_);
        }
        if (Double.doubleToRawLongBits(this.deadline_) != 0) {
            iComputeStringSize += CodedOutputStream.computeDoubleSize(3, this.deadline_);
        }
        if (Double.doubleToRawLongBits(this.minDeadline_) != 0) {
            iComputeStringSize += CodedOutputStream.computeDoubleSize(4, this.minDeadline_);
        }
        if (Double.doubleToRawLongBits(this.operationDeadline_) != 0) {
            iComputeStringSize += CodedOutputStream.computeDoubleSize(5, this.operationDeadline_);
        }
        if (this.pathTranslation_ != PathTranslation.PATH_TRANSLATION_UNSPECIFIED.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(6, this.pathTranslation_);
        }
        if (this.authenticationCase_ == 7) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.authentication_);
        }
        if (this.authenticationCase_ == 8) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(8, ((Boolean) this.authentication_).booleanValue());
        }
        if (!GeneratedMessageV3.isStringEmpty(this.protocol_)) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(9, this.protocol_);
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
        if (!(obj instanceof BackendRule)) {
            return super.equals(obj);
        }
        BackendRule backendRule = (BackendRule) obj;
        if (!getSelector().equals(backendRule.getSelector()) || !getAddress().equals(backendRule.getAddress()) || Double.doubleToLongBits(getDeadline()) != Double.doubleToLongBits(backendRule.getDeadline()) || Double.doubleToLongBits(getMinDeadline()) != Double.doubleToLongBits(backendRule.getMinDeadline()) || Double.doubleToLongBits(getOperationDeadline()) != Double.doubleToLongBits(backendRule.getOperationDeadline()) || this.pathTranslation_ != backendRule.pathTranslation_ || !getProtocol().equals(backendRule.getProtocol()) || !getAuthenticationCase().equals(backendRule.getAuthenticationCase())) {
            return false;
        }
        int i = this.authenticationCase_;
        if (i == 7) {
            if (!getJwtAudience().equals(backendRule.getJwtAudience())) {
                return false;
            }
        } else if (i == 8 && getDisableAuth() != backendRule.getDisableAuth()) {
            return false;
        }
        return this.unknownFields.equals(backendRule.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = ((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode()) * 37) + 2) * 53) + getAddress().hashCode()) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getDeadline()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getMinDeadline()))) * 37) + 5) * 53) + Internal.hashLong(Double.doubleToLongBits(getOperationDeadline()))) * 37) + 6) * 53) + this.pathTranslation_) * 37) + 9) * 53) + getProtocol().hashCode();
        int i2 = this.authenticationCase_;
        if (i2 == 7) {
            i = ((iHashCode2 * 37) + 7) * 53;
            iHashCode = getJwtAudience().hashCode();
        } else {
            if (i2 == 8) {
                i = ((iHashCode2 * 37) + 8) * 53;
                iHashCode = Internal.hashBoolean(getDisableAuth());
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

    public static BackendRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static BackendRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BackendRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static BackendRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BackendRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static BackendRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BackendRule parseFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BackendRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BackendRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BackendRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BackendRule backendRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(backendRule);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements BackendRuleOrBuilder {
        private Object address_;
        private int authenticationCase_;
        private Object authentication_;
        private double deadline_;
        private double minDeadline_;
        private double operationDeadline_;
        private int pathTranslation_;
        private Object protocol_;
        private Object selector_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BackendProto.internal_static_google_api_BackendRule_fieldAccessorTable.ensureFieldAccessorsInitialized(BackendRule.class, Builder.class);
        }

        private Builder() {
            this.authenticationCase_ = 0;
            this.selector_ = "";
            this.address_ = "";
            this.pathTranslation_ = 0;
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.authenticationCase_ = 0;
            this.selector_ = "";
            this.address_ = "";
            this.pathTranslation_ = 0;
            this.protocol_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = BackendRule.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.selector_ = "";
            this.address_ = "";
            this.deadline_ = 0.0d;
            this.minDeadline_ = 0.0d;
            this.operationDeadline_ = 0.0d;
            this.pathTranslation_ = 0;
            this.protocol_ = "";
            this.authenticationCase_ = 0;
            this.authentication_ = null;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return BackendProto.internal_static_google_api_BackendRule_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BackendRule getDefaultInstanceForType() {
            return BackendRule.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BackendRule build() {
            BackendRule backendRuleBuildPartial = buildPartial();
            if (backendRuleBuildPartial.isInitialized()) {
                return backendRuleBuildPartial;
            }
            throw newUninitializedMessageException((Message) backendRuleBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public BackendRule buildPartial() {
            BackendRule backendRule = new BackendRule(this);
            backendRule.selector_ = this.selector_;
            backendRule.address_ = this.address_;
            backendRule.deadline_ = this.deadline_;
            backendRule.minDeadline_ = this.minDeadline_;
            backendRule.operationDeadline_ = this.operationDeadline_;
            backendRule.pathTranslation_ = this.pathTranslation_;
            if (this.authenticationCase_ == 7) {
                backendRule.authentication_ = this.authentication_;
            }
            if (this.authenticationCase_ == 8) {
                backendRule.authentication_ = this.authentication_;
            }
            backendRule.protocol_ = this.protocol_;
            backendRule.authenticationCase_ = this.authenticationCase_;
            onBuilt();
            return backendRule;
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
            if (message instanceof BackendRule) {
                return mergeFrom((BackendRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BackendRule backendRule) {
            if (backendRule == BackendRule.getDefaultInstance()) {
                return this;
            }
            if (!backendRule.getSelector().isEmpty()) {
                this.selector_ = backendRule.selector_;
                onChanged();
            }
            if (!backendRule.getAddress().isEmpty()) {
                this.address_ = backendRule.address_;
                onChanged();
            }
            if (backendRule.getDeadline() != 0.0d) {
                setDeadline(backendRule.getDeadline());
            }
            if (backendRule.getMinDeadline() != 0.0d) {
                setMinDeadline(backendRule.getMinDeadline());
            }
            if (backendRule.getOperationDeadline() != 0.0d) {
                setOperationDeadline(backendRule.getOperationDeadline());
            }
            if (backendRule.pathTranslation_ != 0) {
                setPathTranslationValue(backendRule.getPathTranslationValue());
            }
            if (!backendRule.getProtocol().isEmpty()) {
                this.protocol_ = backendRule.protocol_;
                onChanged();
            }
            int i = AnonymousClass2.$SwitchMap$com$google$api$BackendRule$AuthenticationCase[backendRule.getAuthenticationCase().ordinal()];
            if (i == 1) {
                this.authenticationCase_ = 7;
                this.authentication_ = backendRule.authentication_;
                onChanged();
            } else if (i == 2) {
                setDisableAuth(backendRule.getDisableAuth());
            }
            mergeUnknownFields(backendRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.api.BackendRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.BackendRule.access$1400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.api.BackendRule r3 = (com.google.api.BackendRule) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.api.BackendRule r4 = (com.google.api.BackendRule) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.BackendRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.BackendRule$Builder");
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public AuthenticationCase getAuthenticationCase() {
            return AuthenticationCase.forNumber(this.authenticationCase_);
        }

        public Builder clearAuthentication() {
            this.authenticationCase_ = 0;
            this.authentication_ = null;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getSelector() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.selector_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.BackendRuleOrBuilder
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
            this.selector_ = BackendRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.selector_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getAddress() {
            Object obj = this.address_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.address_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getAddressBytes() {
            Object obj = this.address_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.address_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setAddress(String str) {
            str.getClass();
            this.address_ = str;
            onChanged();
            return this;
        }

        public Builder clearAddress() {
            this.address_ = BackendRule.getDefaultInstance().getAddress();
            onChanged();
            return this;
        }

        public Builder setAddressBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.address_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getDeadline() {
            return this.deadline_;
        }

        public Builder setDeadline(double d) {
            this.deadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearDeadline() {
            this.deadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getMinDeadline() {
            return this.minDeadline_;
        }

        public Builder setMinDeadline(double d) {
            this.minDeadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearMinDeadline() {
            this.minDeadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public double getOperationDeadline() {
            return this.operationDeadline_;
        }

        public Builder setOperationDeadline(double d) {
            this.operationDeadline_ = d;
            onChanged();
            return this;
        }

        public Builder clearOperationDeadline() {
            this.operationDeadline_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public int getPathTranslationValue() {
            return this.pathTranslation_;
        }

        public Builder setPathTranslationValue(int i) {
            this.pathTranslation_ = i;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public PathTranslation getPathTranslation() {
            PathTranslation pathTranslationValueOf = PathTranslation.valueOf(this.pathTranslation_);
            return pathTranslationValueOf == null ? PathTranslation.UNRECOGNIZED : pathTranslationValueOf;
        }

        public Builder setPathTranslation(PathTranslation pathTranslation) {
            pathTranslation.getClass();
            this.pathTranslation_ = pathTranslation.getNumber();
            onChanged();
            return this;
        }

        public Builder clearPathTranslation() {
            this.pathTranslation_ = 0;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean hasJwtAudience() {
            return this.authenticationCase_ == 7;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getJwtAudience() {
            String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.authenticationCase_ == 7) {
                    this.authentication_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getJwtAudienceBytes() {
            String str = this.authenticationCase_ == 7 ? this.authentication_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.authenticationCase_ == 7) {
                    this.authentication_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setJwtAudience(String str) {
            str.getClass();
            this.authenticationCase_ = 7;
            this.authentication_ = str;
            onChanged();
            return this;
        }

        public Builder clearJwtAudience() {
            if (this.authenticationCase_ == 7) {
                this.authenticationCase_ = 0;
                this.authentication_ = null;
                onChanged();
            }
            return this;
        }

        public Builder setJwtAudienceBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.authenticationCase_ = 7;
            this.authentication_ = byteString;
            onChanged();
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean hasDisableAuth() {
            return this.authenticationCase_ == 8;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public boolean getDisableAuth() {
            if (this.authenticationCase_ == 8) {
                return ((Boolean) this.authentication_).booleanValue();
            }
            return false;
        }

        public Builder setDisableAuth(boolean z) {
            this.authenticationCase_ = 8;
            this.authentication_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearDisableAuth() {
            if (this.authenticationCase_ == 8) {
                this.authenticationCase_ = 0;
                this.authentication_ = null;
                onChanged();
            }
            return this;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public String getProtocol() {
            Object obj = this.protocol_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.protocol_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        @Override // com.google.api.BackendRuleOrBuilder
        public ByteString getProtocolBytes() {
            Object obj = this.protocol_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.protocol_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setProtocol(String str) {
            str.getClass();
            this.protocol_ = str;
            onChanged();
            return this;
        }

        public Builder clearProtocol() {
            this.protocol_ = BackendRule.getDefaultInstance().getProtocol();
            onChanged();
            return this;
        }

        public Builder setProtocolBytes(ByteString byteString) {
            byteString.getClass();
            BackendRule.checkByteStringIsUtf8(byteString);
            this.protocol_ = byteString;
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

    /* JADX INFO: renamed from: com.google.api.BackendRule$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$BackendRule$AuthenticationCase;

        static {
            int[] iArr = new int[AuthenticationCase.values().length];
            $SwitchMap$com$google$api$BackendRule$AuthenticationCase = iArr;
            try {
                iArr[AuthenticationCase.JWT_AUDIENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$api$BackendRule$AuthenticationCase[AuthenticationCase.DISABLE_AUTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$api$BackendRule$AuthenticationCase[AuthenticationCase.AUTHENTICATION_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static BackendRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BackendRule> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<BackendRule> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public BackendRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
