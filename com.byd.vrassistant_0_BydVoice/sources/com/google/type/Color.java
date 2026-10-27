package com.google.type;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FloatValue;
import com.google.protobuf.FloatValueOrBuilder;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class Color extends GeneratedMessageV3 implements ColorOrBuilder {
    public static final int ALPHA_FIELD_NUMBER = 4;
    public static final int BLUE_FIELD_NUMBER = 3;
    public static final int GREEN_FIELD_NUMBER = 2;
    public static final int RED_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private FloatValue alpha_;
    private float blue_;
    private float green_;
    private byte memoizedIsInitialized;
    private float red_;
    private static final Color DEFAULT_INSTANCE = new Color();
    private static final Parser<Color> PARSER = new AbstractParser<Color>() { // from class: com.google.type.Color.1
        @Override // com.google.protobuf.Parser
        public Color parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Color(codedInputStream, extensionRegistryLite);
        }
    };

    private Color(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Color() {
        this.memoizedIsInitialized = (byte) -1;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new Color();
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageOrBuilder
    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Color(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (tag == 13) {
                                this.red_ = codedInputStream.readFloat();
                            } else if (tag == 21) {
                                this.green_ = codedInputStream.readFloat();
                            } else if (tag == 29) {
                                this.blue_ = codedInputStream.readFloat();
                            } else if (tag == 34) {
                                FloatValue floatValue = this.alpha_;
                                FloatValue.Builder builder = floatValue != null ? floatValue.toBuilder() : null;
                                FloatValue floatValue2 = (FloatValue) codedInputStream.readMessage(FloatValue.parser(), extensionRegistryLite);
                                this.alpha_ = floatValue2;
                                if (builder != null) {
                                    builder.mergeFrom(floatValue2);
                                    this.alpha_ = builder.buildPartial();
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
        return ColorProto.internal_static_google_type_Color_descriptor;
    }

    @Override // com.google.protobuf.GeneratedMessageV3
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
    }

    @Override // com.google.type.ColorOrBuilder
    public float getRed() {
        return this.red_;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getGreen() {
        return this.green_;
    }

    @Override // com.google.type.ColorOrBuilder
    public float getBlue() {
        return this.blue_;
    }

    @Override // com.google.type.ColorOrBuilder
    public boolean hasAlpha() {
        return this.alpha_ != null;
    }

    @Override // com.google.type.ColorOrBuilder
    public FloatValue getAlpha() {
        FloatValue floatValue = this.alpha_;
        return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
    }

    @Override // com.google.type.ColorOrBuilder
    public FloatValueOrBuilder getAlphaOrBuilder() {
        return getAlpha();
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
        if (Float.floatToRawIntBits(this.red_) != 0) {
            codedOutputStream.writeFloat(1, this.red_);
        }
        if (Float.floatToRawIntBits(this.green_) != 0) {
            codedOutputStream.writeFloat(2, this.green_);
        }
        if (Float.floatToRawIntBits(this.blue_) != 0) {
            codedOutputStream.writeFloat(3, this.blue_);
        }
        if (this.alpha_ != null) {
            codedOutputStream.writeMessage(4, getAlpha());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeFloatSize = Float.floatToRawIntBits(this.red_) != 0 ? 0 + CodedOutputStream.computeFloatSize(1, this.red_) : 0;
        if (Float.floatToRawIntBits(this.green_) != 0) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(2, this.green_);
        }
        if (Float.floatToRawIntBits(this.blue_) != 0) {
            iComputeFloatSize += CodedOutputStream.computeFloatSize(3, this.blue_);
        }
        if (this.alpha_ != null) {
            iComputeFloatSize += CodedOutputStream.computeMessageSize(4, getAlpha());
        }
        int serializedSize = iComputeFloatSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Color)) {
            return super.equals(obj);
        }
        Color color = (Color) obj;
        if (Float.floatToIntBits(getRed()) == Float.floatToIntBits(color.getRed()) && Float.floatToIntBits(getGreen()) == Float.floatToIntBits(color.getGreen()) && Float.floatToIntBits(getBlue()) == Float.floatToIntBits(color.getBlue()) && hasAlpha() == color.hasAlpha()) {
            return (!hasAlpha() || getAlpha().equals(color.getAlpha())) && this.unknownFields.equals(color.unknownFields);
        }
        return false;
    }

    @Override // com.google.protobuf.AbstractMessage, com.google.protobuf.Message
    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Float.floatToIntBits(getRed())) * 37) + 2) * 53) + Float.floatToIntBits(getGreen())) * 37) + 3) * 53) + Float.floatToIntBits(getBlue());
        if (hasAlpha()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getAlpha().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    public static Color parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer);
    }

    public static Color parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Color parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString);
    }

    public static Color parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Color parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr);
    }

    public static Color parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Color parseFrom(InputStream inputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Color parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Color parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Color parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Color parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Color parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Color) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Color color) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(color);
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

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ColorOrBuilder {
        private SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> alphaBuilder_;
        private FloatValue alpha_;
        private float blue_;
        private float green_;
        private float red_;

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder
        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ColorProto.internal_static_google_type_Color_fieldAccessorTable.ensureFieldAccessorsInitialized(Color.class, Builder.class);
        }

        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = Color.alwaysUseFieldBuilders;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Builder clear() {
            super.clear();
            this.red_ = 0.0f;
            this.green_ = 0.0f;
            this.blue_ = 0.0f;
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        @Override // com.google.protobuf.GeneratedMessageV3.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
        public Descriptors.Descriptor getDescriptorForType() {
            return ColorProto.internal_static_google_type_Color_descriptor;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Color getDefaultInstanceForType() {
            return Color.getDefaultInstance();
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Color build() {
            Color colorBuildPartial = buildPartial();
            if (colorBuildPartial.isInitialized()) {
                return colorBuildPartial;
            }
            throw newUninitializedMessageException((Message) colorBuildPartial);
        }

        @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        public Color buildPartial() {
            Color color = new Color(this);
            color.red_ = this.red_;
            color.green_ = this.green_;
            color.blue_ = this.blue_;
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                color.alpha_ = this.alpha_;
            } else {
                color.alpha_ = (FloatValue) singleFieldBuilderV3.build();
            }
            onBuilt();
            return color;
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
            if (message instanceof Color) {
                return mergeFrom((Color) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Color color) {
            if (color == Color.getDefaultInstance()) {
                return this;
            }
            if (color.getRed() != 0.0f) {
                setRed(color.getRed());
            }
            if (color.getGreen() != 0.0f) {
                setGreen(color.getGreen());
            }
            if (color.getBlue() != 0.0f) {
                setBlue(color.getBlue());
            }
            if (color.hasAlpha()) {
                mergeAlpha(color.getAlpha());
            }
            mergeUnknownFields(color.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public com.google.type.Color.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.type.Color.access$900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                com.google.type.Color r3 = (com.google.type.Color) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                com.google.type.Color r4 = (com.google.type.Color) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.type.Color.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.type.Color$Builder");
        }

        @Override // com.google.type.ColorOrBuilder
        public float getRed() {
            return this.red_;
        }

        public Builder setRed(float f) {
            this.red_ = f;
            onChanged();
            return this;
        }

        public Builder clearRed() {
            this.red_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public float getGreen() {
            return this.green_;
        }

        public Builder setGreen(float f) {
            this.green_ = f;
            onChanged();
            return this;
        }

        public Builder clearGreen() {
            this.green_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public float getBlue() {
            return this.blue_;
        }

        public Builder setBlue(float f) {
            this.blue_ = f;
            onChanged();
            return this;
        }

        public Builder clearBlue() {
            this.blue_ = 0.0f;
            onChanged();
            return this;
        }

        @Override // com.google.type.ColorOrBuilder
        public boolean hasAlpha() {
            return (this.alphaBuilder_ == null && this.alpha_ == null) ? false : true;
        }

        @Override // com.google.type.ColorOrBuilder
        public FloatValue getAlpha() {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                FloatValue floatValue = this.alpha_;
                return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
            }
            return (FloatValue) singleFieldBuilderV3.getMessage();
        }

        public Builder setAlpha(FloatValue floatValue) {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                floatValue.getClass();
                this.alpha_ = floatValue;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(floatValue);
            }
            return this;
        }

        public Builder setAlpha(FloatValue.Builder builder) {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.alpha_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeAlpha(FloatValue floatValue) {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 == null) {
                FloatValue floatValue2 = this.alpha_;
                if (floatValue2 != null) {
                    this.alpha_ = FloatValue.newBuilder(floatValue2).mergeFrom(floatValue).buildPartial();
                } else {
                    this.alpha_ = floatValue;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(floatValue);
            }
            return this;
        }

        public Builder clearAlpha() {
            if (this.alphaBuilder_ == null) {
                this.alpha_ = null;
                onChanged();
            } else {
                this.alpha_ = null;
                this.alphaBuilder_ = null;
            }
            return this;
        }

        public FloatValue.Builder getAlphaBuilder() {
            onChanged();
            return (FloatValue.Builder) getAlphaFieldBuilder().getBuilder();
        }

        @Override // com.google.type.ColorOrBuilder
        public FloatValueOrBuilder getAlphaOrBuilder() {
            SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> singleFieldBuilderV3 = this.alphaBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FloatValueOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FloatValue floatValue = this.alpha_;
            return floatValue == null ? FloatValue.getDefaultInstance() : floatValue;
        }

        private SingleFieldBuilderV3<FloatValue, FloatValue.Builder, FloatValueOrBuilder> getAlphaFieldBuilder() {
            if (this.alphaBuilder_ == null) {
                this.alphaBuilder_ = new SingleFieldBuilderV3<>(getAlpha(), getParentForChildren(), isClean());
                this.alpha_ = null;
            }
            return this.alphaBuilder_;
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

    public static Color getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Color> parser() {
        return PARSER;
    }

    @Override // com.google.protobuf.GeneratedMessageV3, com.google.protobuf.MessageLite, com.google.protobuf.Message
    public Parser<Color> getParserForType() {
        return PARSER;
    }

    @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
    public Color getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
