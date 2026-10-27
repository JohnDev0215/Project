package com.android.permissioncontroller.incident;

import com.android.permissioncontroller.incident.IncidentHeaderProto;
import com.android.permissioncontroller.incident.RestrictedImagesDumpProto;
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
public final class IncidentMinimal extends GeneratedMessageLite<IncidentMinimal, Builder> implements IncidentMinimalOrBuilder {
    private static final IncidentMinimal DEFAULT_INSTANCE;
    public static final int HEADER_FIELD_NUMBER = 1;
    private static volatile Parser<IncidentMinimal> PARSER = null;
    public static final int RESTRICTED_IMAGES_SECTION_FIELD_NUMBER = 3025;
    private int bitField0_;
    private Internal.ProtobufList<IncidentHeaderProto> header_ = emptyProtobufList();
    private RestrictedImagesDumpProto restrictedImagesSection_;

    private IncidentMinimal() {
    }

    @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
    public List<IncidentHeaderProto> getHeaderList() {
        return this.header_;
    }

    public List<? extends IncidentHeaderProtoOrBuilder> getHeaderOrBuilderList() {
        return this.header_;
    }

    @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
    public int getHeaderCount() {
        return this.header_.size();
    }

    @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
    public IncidentHeaderProto getHeader(int index) {
        return this.header_.get(index);
    }

    public IncidentHeaderProtoOrBuilder getHeaderOrBuilder(int index) {
        return this.header_.get(index);
    }

    private void ensureHeaderIsMutable() {
        if (!this.header_.isModifiable()) {
            this.header_ = GeneratedMessageLite.mutableCopy(this.header_);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeader(int index, IncidentHeaderProto value) {
        if (value == null) {
            throw null;
        }
        ensureHeaderIsMutable();
        this.header_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHeader(int index, IncidentHeaderProto.Builder builderForValue) {
        ensureHeaderIsMutable();
        this.header_.set(index, builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHeader(IncidentHeaderProto value) {
        if (value == null) {
            throw null;
        }
        ensureHeaderIsMutable();
        this.header_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHeader(int index, IncidentHeaderProto value) {
        if (value == null) {
            throw null;
        }
        ensureHeaderIsMutable();
        this.header_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHeader(IncidentHeaderProto.Builder builderForValue) {
        ensureHeaderIsMutable();
        this.header_.add(builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHeader(int index, IncidentHeaderProto.Builder builderForValue) {
        ensureHeaderIsMutable();
        this.header_.add(index, builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllHeader(Iterable<? extends IncidentHeaderProto> values) {
        ensureHeaderIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.header_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHeader() {
        this.header_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeHeader(int index) {
        ensureHeaderIsMutable();
        this.header_.remove(index);
    }

    @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
    public boolean hasRestrictedImagesSection() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
    public RestrictedImagesDumpProto getRestrictedImagesSection() {
        RestrictedImagesDumpProto restrictedImagesDumpProto = this.restrictedImagesSection_;
        return restrictedImagesDumpProto == null ? RestrictedImagesDumpProto.getDefaultInstance() : restrictedImagesDumpProto;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRestrictedImagesSection(RestrictedImagesDumpProto value) {
        if (value == null) {
            throw null;
        }
        this.restrictedImagesSection_ = value;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRestrictedImagesSection(RestrictedImagesDumpProto.Builder builderForValue) {
        this.restrictedImagesSection_ = builderForValue.build();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeRestrictedImagesSection(RestrictedImagesDumpProto value) {
        if (value == null) {
            throw null;
        }
        RestrictedImagesDumpProto restrictedImagesDumpProto = this.restrictedImagesSection_;
        if (restrictedImagesDumpProto != null && restrictedImagesDumpProto != RestrictedImagesDumpProto.getDefaultInstance()) {
            this.restrictedImagesSection_ = RestrictedImagesDumpProto.newBuilder(this.restrictedImagesSection_).mergeFrom(value).buildPartial();
        } else {
            this.restrictedImagesSection_ = value;
        }
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearRestrictedImagesSection() {
        this.restrictedImagesSection_ = null;
        this.bitField0_ &= -2;
    }

    public static IncidentMinimal parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentMinimal parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentMinimal parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentMinimal parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentMinimal parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentMinimal parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentMinimal parseFrom(InputStream input) throws IOException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentMinimal parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentMinimal parseDelimitedFrom(InputStream input) throws IOException {
        return (IncidentMinimal) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentMinimal parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentMinimal) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentMinimal parseFrom(CodedInputStream input) throws IOException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentMinimal parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentMinimal) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(IncidentMinimal prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IncidentMinimal, Builder> implements IncidentMinimalOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(IncidentMinimal.DEFAULT_INSTANCE);
        }

        @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
        public List<IncidentHeaderProto> getHeaderList() {
            return Collections.unmodifiableList(((IncidentMinimal) this.instance).getHeaderList());
        }

        @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
        public int getHeaderCount() {
            return ((IncidentMinimal) this.instance).getHeaderCount();
        }

        @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
        public IncidentHeaderProto getHeader(int index) {
            return ((IncidentMinimal) this.instance).getHeader(index);
        }

        public Builder setHeader(int index, IncidentHeaderProto value) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).setHeader(index, value);
            return this;
        }

        public Builder setHeader(int index, IncidentHeaderProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).setHeader(index, builderForValue);
            return this;
        }

        public Builder addHeader(IncidentHeaderProto value) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).addHeader(value);
            return this;
        }

        public Builder addHeader(int index, IncidentHeaderProto value) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).addHeader(index, value);
            return this;
        }

        public Builder addHeader(IncidentHeaderProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).addHeader(builderForValue);
            return this;
        }

        public Builder addHeader(int index, IncidentHeaderProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).addHeader(index, builderForValue);
            return this;
        }

        public Builder addAllHeader(Iterable<? extends IncidentHeaderProto> values) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).addAllHeader(values);
            return this;
        }

        public Builder clearHeader() {
            copyOnWrite();
            ((IncidentMinimal) this.instance).clearHeader();
            return this;
        }

        public Builder removeHeader(int index) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).removeHeader(index);
            return this;
        }

        @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
        public boolean hasRestrictedImagesSection() {
            return ((IncidentMinimal) this.instance).hasRestrictedImagesSection();
        }

        @Override // com.android.permissioncontroller.incident.IncidentMinimalOrBuilder
        public RestrictedImagesDumpProto getRestrictedImagesSection() {
            return ((IncidentMinimal) this.instance).getRestrictedImagesSection();
        }

        public Builder setRestrictedImagesSection(RestrictedImagesDumpProto value) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).setRestrictedImagesSection(value);
            return this;
        }

        public Builder setRestrictedImagesSection(RestrictedImagesDumpProto.Builder builderForValue) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).setRestrictedImagesSection(builderForValue);
            return this;
        }

        public Builder mergeRestrictedImagesSection(RestrictedImagesDumpProto value) {
            copyOnWrite();
            ((IncidentMinimal) this.instance).mergeRestrictedImagesSection(value);
            return this;
        }

        public Builder clearRestrictedImagesSection() {
            copyOnWrite();
            ((IncidentMinimal) this.instance).clearRestrictedImagesSection();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.incident.IncidentMinimal$1, reason: invalid class name */
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
                return new IncidentMinimal();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"bitField0_", "header_", IncidentHeaderProto.class, "restrictedImagesSection_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0bd1\u0002\u0000\u0001\u0000\u0001\u001b\u0bd1\t\u0000", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IncidentMinimal> parser = PARSER;
                if (parser == null) {
                    synchronized (IncidentMinimal.class) {
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
        IncidentMinimal defaultInstance = new IncidentMinimal();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(IncidentMinimal.class, defaultInstance);
    }

    public static IncidentMinimal getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IncidentMinimal> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
