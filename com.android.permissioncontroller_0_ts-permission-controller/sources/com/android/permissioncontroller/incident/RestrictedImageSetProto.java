package com.android.permissioncontroller.incident;

import com.android.permissioncontroller.incident.RestrictedImageProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class RestrictedImageSetProto extends GeneratedMessageLite<RestrictedImageSetProto, Builder> implements RestrictedImageSetProtoOrBuilder {
    public static final int CATEGORY_FIELD_NUMBER = 1;
    private static final RestrictedImageSetProto DEFAULT_INSTANCE;
    public static final int IMAGES_FIELD_NUMBER = 2;
    public static final int METADATA_FIELD_NUMBER = 3;
    private static volatile Parser<RestrictedImageSetProto> PARSER;
    private int bitField0_;
    private String category_ = "";
    private Internal.ProtobufList<RestrictedImageProto> images_ = emptyProtobufList();
    private ByteString metadata_ = ByteString.EMPTY;

    private RestrictedImageSetProto() {
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public boolean hasCategory() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public String getCategory() {
        return this.category_;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public ByteString getCategoryBytes() {
        return ByteString.copyFromUtf8(this.category_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCategory(String value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.category_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCategory() {
        this.bitField0_ &= -2;
        this.category_ = getDefaultInstance().getCategory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCategoryBytes(ByteString value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.category_ = value.toStringUtf8();
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public List<RestrictedImageProto> getImagesList() {
        return this.images_;
    }

    public List<? extends RestrictedImageProtoOrBuilder> getImagesOrBuilderList() {
        return this.images_;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public int getImagesCount() {
        return this.images_.size();
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public RestrictedImageProto getImages(int index) {
        return this.images_.get(index);
    }

    public RestrictedImageProtoOrBuilder getImagesOrBuilder(int index) {
        return this.images_.get(index);
    }

    private void ensureImagesIsMutable() {
        if (!this.images_.isModifiable()) {
            this.images_ = GeneratedMessageLite.mutableCopy(this.images_);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImages(int index, RestrictedImageProto value) {
        if (value == null) {
            throw null;
        }
        ensureImagesIsMutable();
        this.images_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImages(int index, RestrictedImageProto.Builder builderForValue) {
        ensureImagesIsMutable();
        this.images_.set(index, builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImages(RestrictedImageProto value) {
        if (value == null) {
            throw null;
        }
        ensureImagesIsMutable();
        this.images_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImages(int index, RestrictedImageProto value) {
        if (value == null) {
            throw null;
        }
        ensureImagesIsMutable();
        this.images_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImages(RestrictedImageProto.Builder builderForValue) {
        ensureImagesIsMutable();
        this.images_.add(builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImages(int index, RestrictedImageProto.Builder builderForValue) {
        ensureImagesIsMutable();
        this.images_.add(index, builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllImages(Iterable<? extends RestrictedImageProto> values) {
        ensureImagesIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.images_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearImages() {
        this.images_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeImages(int index) {
        ensureImagesIsMutable();
        this.images_.remove(index);
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public boolean hasMetadata() {
        return (this.bitField0_ & 2) != 0;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
    public ByteString getMetadata() {
        return this.metadata_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(ByteString value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 2;
        this.metadata_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        this.bitField0_ &= -3;
        this.metadata_ = getDefaultInstance().getMetadata();
    }

    public static RestrictedImageSetProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageSetProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageSetProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImageSetProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(InputStream input) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageSetProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageSetProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RestrictedImageSetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageSetProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageSetProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImageSetProto parseFrom(CodedInputStream input) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImageSetProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImageSetProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RestrictedImageSetProto prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictedImageSetProto, Builder> implements RestrictedImageSetProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(RestrictedImageSetProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public boolean hasCategory() {
            return ((RestrictedImageSetProto) this.instance).hasCategory();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public String getCategory() {
            return ((RestrictedImageSetProto) this.instance).getCategory();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public ByteString getCategoryBytes() {
            return ((RestrictedImageSetProto) this.instance).getCategoryBytes();
        }

        public Builder setCategory(String value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setCategory(value);
            return this;
        }

        public Builder clearCategory() {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).clearCategory();
            return this;
        }

        public Builder setCategoryBytes(ByteString value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setCategoryBytes(value);
            return this;
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public List<RestrictedImageProto> getImagesList() {
            return Collections.unmodifiableList(((RestrictedImageSetProto) this.instance).getImagesList());
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public int getImagesCount() {
            return ((RestrictedImageSetProto) this.instance).getImagesCount();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public RestrictedImageProto getImages(int index) {
            return ((RestrictedImageSetProto) this.instance).getImages(index);
        }

        public Builder setImages(int index, RestrictedImageProto value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setImages(index, value);
            return this;
        }

        public Builder setImages(int index, RestrictedImageProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setImages(index, builderForValue);
            return this;
        }

        public Builder addImages(RestrictedImageProto value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages(value);
            return this;
        }

        public Builder addImages(int index, RestrictedImageProto value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages(index, value);
            return this;
        }

        public Builder addImages(RestrictedImageProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages(builderForValue);
            return this;
        }

        public Builder addImages(int index, RestrictedImageProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addImages(index, builderForValue);
            return this;
        }

        public Builder addAllImages(Iterable<? extends RestrictedImageProto> values) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).addAllImages(values);
            return this;
        }

        public Builder clearImages() {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).clearImages();
            return this;
        }

        public Builder removeImages(int index) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).removeImages(index);
            return this;
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public boolean hasMetadata() {
            return ((RestrictedImageSetProto) this.instance).hasMetadata();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImageSetProtoOrBuilder
        public ByteString getMetadata() {
            return ((RestrictedImageSetProto) this.instance).getMetadata();
        }

        public Builder setMetadata(ByteString value) {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).setMetadata(value);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((RestrictedImageSetProto) this.instance).clearMetadata();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.incident.RestrictedImageSetProto$1, reason: invalid class name */
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
                return new RestrictedImageSetProto();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"bitField0_", "category_", "images_", RestrictedImageProto.class, "metadata_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\b\u0000\u0002\u001b\u0003\n\u0001", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RestrictedImageSetProto> parser = PARSER;
                if (parser == null) {
                    synchronized (RestrictedImageSetProto.class) {
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
        RestrictedImageSetProto defaultInstance = new RestrictedImageSetProto();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(RestrictedImageSetProto.class, defaultInstance);
    }

    public static RestrictedImageSetProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestrictedImageSetProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
