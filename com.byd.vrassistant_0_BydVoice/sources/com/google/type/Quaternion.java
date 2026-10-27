package com.google.type;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class Quaternion extends GeneratedMessageV3 implements QuaternionOrBuilder {
    private static final Quaternion DEFAULT_INSTANCE = new Quaternion();
    private static final Parser<Quaternion> PARSER = new AbstractParser<Quaternion>() { // from class: com.google.type.Quaternion.1
        @Override // com.google.protobuf.Parser
        public Quaternion parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Quaternion(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int W_FIELD_NUMBER = 4;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 2;
    public static final int Z_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private double w_;
    private double x_;
    private double y_;
    private double z_;

    private Quaternion(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Quaternion() {
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Quaternion();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Quaternion(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 9) {
                                this.x_ = codedInputStream.readDouble();
                            } else if (tag == 17) {
                                this.y_ = codedInputStream.readDouble();
                            } else if (tag == 25) {
                                this.z_ = codedInputStream.readDouble();
                            } else if (tag == 33) {
                                this.w_ = codedInputStream.readDouble();
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
        return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return QuaternionProto.internal_static_google_type_Quaternion_fieldAccessorTable.ensureFieldAccessorsInitialized(Quaternion.class, Builder.class);
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getX() {
        return this.x_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getY() {
        return this.y_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getZ() {
        return this.z_;
    }

    @Override // com.google.type.QuaternionOrBuilder
    public double getW() {
        return this.w_;
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
        if (Double.doubleToRawLongBits(this.x_) != 0) {
            codedOutputStream.writeDouble(1, this.x_);
        }
        if (Double.doubleToRawLongBits(this.y_) != 0) {
            codedOutputStream.writeDouble(2, this.y_);
        }
        if (Double.doubleToRawLongBits(this.z_) != 0) {
            codedOutputStream.writeDouble(3, this.z_);
        }
        if (Double.doubleToRawLongBits(this.w_) != 0) {
            codedOutputStream.writeDouble(4, this.w_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeDoubleSize = Double.doubleToRawLongBits(this.x_) != 0 ? 0 + CodedOutputStream.computeDoubleSize(1, this.x_) : 0;
        if (Double.doubleToRawLongBits(this.y_) != 0) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(2, this.y_);
        }
        if (Double.doubleToRawLongBits(this.z_) != 0) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(3, this.z_);
        }
        if (Double.doubleToRawLongBits(this.w_) != 0) {
            iComputeDoubleSize += CodedOutputStream.computeDoubleSize(4, this.w_);
        }
        int serializedSize = iComputeDoubleSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Quaternion)) {
            return super.equals(obj);
        }
        Quaternion quaternion = (Quaternion) obj;
        return Double.doubleToLongBits(getX()) == Double.doubleToLongBits(quaternion.getX()) && Double.doubleToLongBits(getY()) == Double.doubleToLongBits(quaternion.getY()) && Double.doubleToLongBits(getZ()) == Double.doubleToLongBits(quaternion.getZ()) && Double.doubleToLongBits(getW()) == Double.doubleToLongBits(quaternion.getW()) && this.unknownFields.equals(quaternion.unknownFields);
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(Double.doubleToLongBits(getX()))) * 37) + 2) * 53) + Internal.hashLong(Double.doubleToLongBits(getY()))) * 37) + 3) * 53) + Internal.hashLong(Double.doubleToLongBits(getZ()))) * 37) + 4) * 53) + Internal.hashLong(Double.doubleToLongBits(getW()))) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode;
        return iHashCode;
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Quaternion parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Quaternion parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Quaternion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Quaternion parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Quaternion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Quaternion parseFrom(InputStream inputStream) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Quaternion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Quaternion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Quaternion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quaternion) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Quaternion quaternion) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(quaternion);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements QuaternionOrBuilder {
        private double w_;
        private double x_;
        private double y_;
        private double z_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return QuaternionProto.internal_static_google_type_Quaternion_fieldAccessorTable.ensureFieldAccessorsInitialized(Quaternion.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Quaternion.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.x_ = 0.0d;
            this.y_ = 0.0d;
            this.z_ = 0.0d;
            this.w_ = 0.0d;
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return QuaternionProto.internal_static_google_type_Quaternion_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Quaternion getDefaultInstanceForType() {
            return Quaternion.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Quaternion build() {
            Quaternion quaternionBuildPartial = buildPartial();
            if (quaternionBuildPartial.isInitialized()) {
                return quaternionBuildPartial;
            }
            throw newUninitializedMessageException((Message) quaternionBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Quaternion buildPartial() {
            Quaternion quaternion = new Quaternion(this);
            quaternion.x_ = this.x_;
            quaternion.y_ = this.y_;
            quaternion.z_ = this.z_;
            quaternion.w_ = this.w_;
            onBuilt();
            return quaternion;
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
            if (message instanceof Quaternion) {
                return mergeFrom((Quaternion) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Quaternion quaternion) {
            if (quaternion == Quaternion.getDefaultInstance()) {
                return this;
            }
            if (quaternion.getX() != 0.0d) {
                setX(quaternion.getX());
            }
            if (quaternion.getY() != 0.0d) {
                setY(quaternion.getY());
            }
            if (quaternion.getZ() != 0.0d) {
                setZ(quaternion.getZ());
            }
            if (quaternion.getW() != 0.0d) {
                setW(quaternion.getW());
            }
            mergeUnknownFields(quaternion.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.type.Quaternion.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Quaternion.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.type.Quaternion r3 = (com.google.type.Quaternion) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.type.Quaternion r4 = (com.google.type.Quaternion) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Quaternion.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Quaternion$Builder");
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getX() {
            return this.x_;
        }

        public Builder setX(double d) {
            this.x_ = d;
            onChanged();
            return this;
        }

        public Builder clearX() {
            this.x_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getY() {
            return this.y_;
        }

        public Builder setY(double d) {
            this.y_ = d;
            onChanged();
            return this;
        }

        public Builder clearY() {
            this.y_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getZ() {
            return this.z_;
        }

        public Builder setZ(double d) {
            this.z_ = d;
            onChanged();
            return this;
        }

        public Builder clearZ() {
            this.z_ = 0.0d;
            onChanged();
            return this;
        }

        @Override // com.google.type.QuaternionOrBuilder
        public double getW() {
            return this.w_;
        }

        public Builder setW(double d) {
            this.w_ = d;
            onChanged();
            return this;
        }

        public Builder clearW() {
            this.w_ = 0.0d;
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

    public static Quaternion getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Quaternion> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Quaternion> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Quaternion getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
