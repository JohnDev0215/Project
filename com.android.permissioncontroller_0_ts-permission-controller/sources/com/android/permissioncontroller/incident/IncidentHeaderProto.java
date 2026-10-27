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
public final class IncidentHeaderProto extends GeneratedMessageLite<IncidentHeaderProto, Builder> implements IncidentHeaderProtoOrBuilder {
    private static final IncidentHeaderProto DEFAULT_INSTANCE;
    private static volatile Parser<IncidentHeaderProto> PARSER = null;
    public static final int REASON_FIELD_NUMBER = 2;
    private int bitField0_;
    private String reason_ = "";

    private IncidentHeaderProto() {
    }

    @Override // com.android.permissioncontroller.incident.IncidentHeaderProtoOrBuilder
    public boolean hasReason() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.android.permissioncontroller.incident.IncidentHeaderProtoOrBuilder
    public String getReason() {
        return this.reason_;
    }

    @Override // com.android.permissioncontroller.incident.IncidentHeaderProtoOrBuilder
    public ByteString getReasonBytes() {
        return ByteString.copyFromUtf8(this.reason_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReason(String value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.reason_ = value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReason() {
        this.bitField0_ &= -2;
        this.reason_ = getDefaultInstance().getReason();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReasonBytes(ByteString value) {
        if (value == null) {
            throw null;
        }
        this.bitField0_ |= 1;
        this.reason_ = value.toStringUtf8();
    }

    public static IncidentHeaderProto parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentHeaderProto parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentHeaderProto parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data);
    }

    public static IncidentHeaderProto parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, data, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(InputStream input) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentHeaderProto parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentHeaderProto parseDelimitedFrom(InputStream input) throws IOException {
        return (IncidentHeaderProto) parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentHeaderProto parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentHeaderProto) parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static IncidentHeaderProto parseFrom(CodedInputStream input) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input);
    }

    public static IncidentHeaderProto parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return (IncidentHeaderProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(IncidentHeaderProto prototype) {
        return DEFAULT_INSTANCE.createBuilder(prototype);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<IncidentHeaderProto, Builder> implements IncidentHeaderProtoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 x0) {
            this();
        }

        private Builder() {
            super(IncidentHeaderProto.DEFAULT_INSTANCE);
        }

        @Override // com.android.permissioncontroller.incident.IncidentHeaderProtoOrBuilder
        public boolean hasReason() {
            return ((IncidentHeaderProto) this.instance).hasReason();
        }

        @Override // com.android.permissioncontroller.incident.IncidentHeaderProtoOrBuilder
        public String getReason() {
            return ((IncidentHeaderProto) this.instance).getReason();
        }

        @Override // com.android.permissioncontroller.incident.IncidentHeaderProtoOrBuilder
        public ByteString getReasonBytes() {
            return ((IncidentHeaderProto) this.instance).getReasonBytes();
        }

        public Builder setReason(String value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setReason(value);
            return this;
        }

        public Builder clearReason() {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).clearReason();
            return this;
        }

        public Builder setReasonBytes(ByteString value) {
            copyOnWrite();
            ((IncidentHeaderProto) this.instance).setReasonBytes(value);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.permissioncontroller.incident.IncidentHeaderProto$1, reason: invalid class name */
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
                return new IncidentHeaderProto();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                Object[] objects = {"bitField0_", "reason_"};
                return newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0001\u0002\u0002\u0001\u0000\u0000\u0000\u0002\b\u0000", objects);
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<IncidentHeaderProto> parser = PARSER;
                if (parser == null) {
                    synchronized (IncidentHeaderProto.class) {
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
        IncidentHeaderProto defaultInstance = new IncidentHeaderProto();
        DEFAULT_INSTANCE = defaultInstance;
        GeneratedMessageLite.registerDefaultInstance(IncidentHeaderProto.class, defaultInstance);
    }

    public static IncidentHeaderProto getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<IncidentHeaderProto> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
