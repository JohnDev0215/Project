package com.cerence.cloudservices.api.services.session.v1;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class DeleteBySessionId extends GeneratedMessageLite<DeleteBySessionId, Builder> implements DeleteBySessionIdOrBuilder {
    private static final DeleteBySessionId DEFAULT_INSTANCE;
    public static final int DEVICE_ID_FIELD_NUMBER = 2;
    private static volatile Parser<DeleteBySessionId> PARSER = null;
    public static final int SESSION_ID_TO_DELETE_FIELD_NUMBER = 3;
    public static final int USER_ID_FIELD_NUMBER = 1;
    private String userId_ = "";
    private String deviceId_ = "";
    private String sessionIdToDelete_ = "";

    private DeleteBySessionId() {
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
    public String getUserId() {
        return this.userId_;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
    public ByteString getUserIdBytes() {
        return ByteString.copyFromUtf8(this.userId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserId(String str) {
        str.getClass();
        this.userId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUserId() {
        this.userId_ = getDefaultInstance().getUserId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.userId_ = byteString.toStringUtf8();
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
    public String getDeviceId() {
        return this.deviceId_;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
    public ByteString getDeviceIdBytes() {
        return ByteString.copyFromUtf8(this.deviceId_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceId(String str) {
        str.getClass();
        this.deviceId_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDeviceId() {
        this.deviceId_ = getDefaultInstance().getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceIdBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.deviceId_ = byteString.toStringUtf8();
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
    public String getSessionIdToDelete() {
        return this.sessionIdToDelete_;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
    public ByteString getSessionIdToDeleteBytes() {
        return ByteString.copyFromUtf8(this.sessionIdToDelete_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSessionIdToDelete(String str) {
        str.getClass();
        this.sessionIdToDelete_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSessionIdToDelete() {
        this.sessionIdToDelete_ = getDefaultInstance().getSessionIdToDelete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSessionIdToDeleteBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.sessionIdToDelete_ = byteString.toStringUtf8();
    }

    public static DeleteBySessionId parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DeleteBySessionId parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DeleteBySessionId parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DeleteBySessionId parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DeleteBySessionId parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DeleteBySessionId parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DeleteBySessionId parseFrom(InputStream inputStream) throws IOException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteBySessionId parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteBySessionId parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DeleteBySessionId) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteBySessionId parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteBySessionId) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteBySessionId parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DeleteBySessionId parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteBySessionId) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DeleteBySessionId deleteBySessionId) {
        return DEFAULT_INSTANCE.createBuilder(deleteBySessionId);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeleteBySessionId, Builder> implements DeleteBySessionIdOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DeleteBySessionId.DEFAULT_INSTANCE);
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
        public String getUserId() {
            return ((DeleteBySessionId) this.instance).getUserId();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
        public ByteString getUserIdBytes() {
            return ((DeleteBySessionId) this.instance).getUserIdBytes();
        }

        public Builder setUserId(String str) {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).setUserId(str);
            return this;
        }

        public Builder clearUserId() {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).clearUserId();
            return this;
        }

        public Builder setUserIdBytes(ByteString byteString) {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).setUserIdBytes(byteString);
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
        public String getDeviceId() {
            return ((DeleteBySessionId) this.instance).getDeviceId();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
        public ByteString getDeviceIdBytes() {
            return ((DeleteBySessionId) this.instance).getDeviceIdBytes();
        }

        public Builder setDeviceId(String str) {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).setDeviceId(str);
            return this;
        }

        public Builder clearDeviceId() {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).clearDeviceId();
            return this;
        }

        public Builder setDeviceIdBytes(ByteString byteString) {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).setDeviceIdBytes(byteString);
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
        public String getSessionIdToDelete() {
            return ((DeleteBySessionId) this.instance).getSessionIdToDelete();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteBySessionIdOrBuilder
        public ByteString getSessionIdToDeleteBytes() {
            return ((DeleteBySessionId) this.instance).getSessionIdToDeleteBytes();
        }

        public Builder setSessionIdToDelete(String str) {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).setSessionIdToDelete(str);
            return this;
        }

        public Builder clearSessionIdToDelete() {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).clearSessionIdToDelete();
            return this;
        }

        public Builder setSessionIdToDeleteBytes(ByteString byteString) {
            copyOnWrite();
            ((DeleteBySessionId) this.instance).setSessionIdToDeleteBytes(byteString);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.cerence.cloudservices.api.services.session.v1.DeleteBySessionId$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new DeleteBySessionId();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"userId_", "deviceId_", "sessionIdToDelete_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DeleteBySessionId> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DeleteBySessionId.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                        break;
                    }
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        DeleteBySessionId deleteBySessionId = new DeleteBySessionId();
        DEFAULT_INSTANCE = deleteBySessionId;
        GeneratedMessageLite.registerDefaultInstance(DeleteBySessionId.class, deleteBySessionId);
    }

    public static DeleteBySessionId getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeleteBySessionId> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
