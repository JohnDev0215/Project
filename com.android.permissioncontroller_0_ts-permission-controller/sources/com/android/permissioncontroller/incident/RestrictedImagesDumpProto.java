package com.android.permissioncontroller.incident;

import com.android.permissioncontroller.incident.RestrictedImageSetProto;
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
public final class RestrictedImagesDumpProto extends GeneratedMessageLite<RestrictedImagesDumpProto, Builder> implements RestrictedImagesDumpProtoOrBuilder {
    private static final RestrictedImagesDumpProto DEFAULT_INSTANCE;
    private static volatile Parser<RestrictedImagesDumpProto> PARSER = null;
    public static final int SETS_FIELD_NUMBER = 1;
    private Internal.ProtobufList<RestrictedImageSetProto> sets_ = emptyProtobufList();

    private RestrictedImagesDumpProto() {
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImagesDumpProtoOrBuilder
    public List<RestrictedImageSetProto> getSetsList() {
        return this.sets_;
    }

    public List<? extends RestrictedImageSetProtoOrBuilder> getSetsOrBuilderList() {
        return this.sets_;
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImagesDumpProtoOrBuilder
    public int getSetsCount() {
        return this.sets_.size();
    }

    @Override // com.android.permissioncontroller.incident.RestrictedImagesDumpProtoOrBuilder
    public RestrictedImageSetProto getSets(int index) {
        return this.sets_.get(index);
    }

    public RestrictedImageSetProtoOrBuilder getSetsOrBuilder(int index) {
        return this.sets_.get(index);
    }

    private void ensureSetsIsMutable() {
        if (!this.sets_.isModifiable()) {
            this.sets_ = GeneratedMessageLite.mutableCopy(this.sets_);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSets(int index, RestrictedImageSetProto value) {
        if (value == null) {
            throw null;
        }
        ensureSetsIsMutable();
        this.sets_.set(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSets(int index, RestrictedImageSetProto.Builder builderForValue) {
        ensureSetsIsMutable();
        this.sets_.set(index, builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSets(RestrictedImageSetProto value) {
        if (value == null) {
            throw null;
        }
        ensureSetsIsMutable();
        this.sets_.add(value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSets(int index, RestrictedImageSetProto value) {
        if (value == null) {
            throw null;
        }
        ensureSetsIsMutable();
        this.sets_.add(index, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSets(RestrictedImageSetProto.Builder builderForValue) {
        ensureSetsIsMutable();
        this.sets_.add(builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addSets(int index, RestrictedImageSetProto.Builder builderForValue) {
        ensureSetsIsMutable();
        this.sets_.add(index, builderForValue.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllSets(Iterable<? extends RestrictedImageSetProto> values) {
        ensureSetsIsMutable();
        AbstractMessageLite.addAll((Iterable) values, (List) this.sets_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSets() {
        this.sets_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSets(int index) {
        ensureSetsIsMutable();
        this.sets_.remove(index);
    }

    public static RestrictedImagesDumpProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImagesDumpProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImagesDumpProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static RestrictedImagesDumpProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(InputStream input) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImagesDumpProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseDelimitedFrom(InputStream input) throws IOException {
        return (RestrictedImagesDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImagesDumpProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImagesDumpProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static RestrictedImagesDumpProto parseFrom(CodedInputStream input) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static RestrictedImagesDumpProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (RestrictedImagesDumpProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(RestrictedImagesDumpProto prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictedImagesDumpProto, Builder> implements RestrictedImagesDumpProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(RestrictedImagesDumpProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImagesDumpProtoOrBuilder
        public List<RestrictedImageSetProto> getSetsList() {
            return Collections.unmodifiableList(((RestrictedImagesDumpProto) this.instance).getSetsList());
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImagesDumpProtoOrBuilder
        public int getSetsCount() {
            return ((RestrictedImagesDumpProto) this.instance).getSetsCount();
        }

        @Override // com.android.permissioncontroller.incident.RestrictedImagesDumpProtoOrBuilder
        public RestrictedImageSetProto getSets(int index) {
            return ((RestrictedImagesDumpProto) this.instance).getSets(index);
        }

        public Builder setSets(int index, RestrictedImageSetProto value) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).setSets(index, value);
            return this;
        }

        public Builder setSets(int index, RestrictedImageSetProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).setSets(index, builderForValue);
            return this;
        }

        public Builder addSets(RestrictedImageSetProto value) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets(value);
            return this;
        }

        public Builder addSets(int index, RestrictedImageSetProto value) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets(index, value);
            return this;
        }

        public Builder addSets(RestrictedImageSetProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets(builderForValue);
            return this;
        }

        public Builder addSets(int index, RestrictedImageSetProto.Builder builderForValue) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addSets(index, builderForValue);
            return this;
        }

        public Builder addAllSets(Iterable<? extends RestrictedImageSetProto> values) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).addAllSets(values);
            return this;
        }

        public Builder clearSets() {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).clearSets();
            return this;
        }

        public Builder removeSets(int index) {
            copyOnWrite();
            ((RestrictedImagesDumpProto) this.instance).removeSets(index);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.incident.RestrictedImagesDumpProto$1, reason: invalid class name */
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
                return new RestrictedImagesDumpProto();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"sets_", RestrictedImageSetProto.class};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<RestrictedImagesDumpProto> parser = PARSER;
                if (parser == null) {
                    synchronized (RestrictedImagesDumpProto.class) {
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
        RestrictedImagesDumpProto defaultInstance = new RestrictedImagesDumpProto();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(RestrictedImagesDumpProto.class, defaultInstance);
    }

    public static RestrictedImagesDumpProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RestrictedImagesDumpProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
