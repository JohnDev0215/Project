package com.android.permissioncontroller.incident;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes8.dex */
public final class RestrictedImageProto extends GeneratedMessageLite<RestrictedImageProto, Builder> implements RestrictedImageProtoOrBuilder {
    private static final RestrictedImageProto DEFAULT_INSTANCE;
    public static final int IMAGE_DATA_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    public static final int MIME_TYPE_FIELD_NUMBER = 1;
    private static volatile Parser<RestrictedImageProto> PARSER;
    private int bitField0_;
    private String mimeType_ = "";
    private ByteString imageData_ = ByteString.EMPTY;
    private ByteString metadata_ = ByteString.EMPTY;

    private RestrictedImageProto() {
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public boolean hasMimeType() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public String getMimeType() {
        return this.mimeType_;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public ByteString getMimeTypeBytes() {
        return ByteString.copyFromUtf8(this.mimeType_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMimeType(String value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.mimeType_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMimeType() {
        this.bitField0_ &= -2;
        this.mimeType_ = getDefaultInstance().getMimeType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMimeTypeBytes(ByteString value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.mimeType_ = value.toStringUtf8();
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public boolean hasImageData() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public ByteString getImageData() {
        return this.imageData_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageData(ByteString value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 2;
        this.imageData_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearImageData() {
        this.bitField0_ &= -3;
        this.imageData_ = getDefaultInstance().getImageData();
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public boolean hasMetadata() {
        return (this.bitField0_ & 4) != 0;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
    public ByteString getMetadata() {
        return this.metadata_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(ByteString value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 4;
        this.metadata_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        this.bitField0_ &= -5;
        this.metadata_ = getDefaultInstance().getMetadata();
    }

    public static RestrictedImageProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(InputStream input) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RestrictedImageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageProto parseFrom(CodedInputStream input) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RestrictedImageProto prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictedImageProto, Builder> implements RestrictedImageProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(RestrictedImageProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public boolean hasMimeType() {
            return ((RestrictedImageProto) this.instance).hasMimeType();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public String getMimeType() {
            return ((RestrictedImageProto) this.instance).getMimeType();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public ByteString getMimeTypeBytes() {
            return ((RestrictedImageProto) this.instance).getMimeTypeBytes();
        }

        public Builder setMimeType(String value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setMimeType(value);
            return this;
        }

        public Builder clearMimeType() {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).clearMimeType();
            return this;
        }

        public Builder setMimeTypeBytes(ByteString value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setMimeTypeBytes(value);
            return this;
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public boolean hasImageData() {
            return ((RestrictedImageProto) this.instance).hasImageData();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public ByteString getImageData() {
            return ((RestrictedImageProto) this.instance).getImageData();
        }

        public Builder setImageData(ByteString value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setImageData(value);
            return this;
        }

        public Builder clearImageData() {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).clearImageData();
            return this;
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public boolean hasMetadata() {
            return ((RestrictedImageProto) this.instance).hasMetadata();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageProtoOrBuilder
        public ByteString getMetadata() {
            return ((RestrictedImageProto) this.instance).getMetadata();
        }

        public Builder setMetadata(ByteString value) {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).setMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((RestrictedImageProto) this.instance).clearMetadata();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.incident.RestrictedImageProto$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke method, Object arg0, Object arg1) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[method.ordinal()]) {
            case 1:
                return new RestrictedImageProto();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"bitField0_", "mimeType_", "imageData_", "metadata_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\b\u0000\u0002\n\u0001\u0003\n\u0002", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RestrictedImageProto> parser = PARSER;
                if (parser == null) {
                    synchronized (RestrictedImageProto.class) {
                        parser = PARSER;
                        if (parser == null) {
                            parser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            PARSER = parser;
                        }
                        break;
                    }
                }
                return parser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        RestrictedImageProto defaultInstance = new RestrictedImageProto();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(RestrictedImageProto.class, defaultInstance);
    }

    public static RestrictedImageProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestrictedImageProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
