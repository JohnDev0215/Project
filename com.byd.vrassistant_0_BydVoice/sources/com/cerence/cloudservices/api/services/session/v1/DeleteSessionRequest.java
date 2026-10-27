package com.cerence.cloudservices.api.services.session.v1;

import com.cerence.cloudservices.api.services.session.v1.DeleteAll;
import com.cerence.cloudservices.api.services.session.v1.DeleteBySessionId;
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
public final class DeleteSessionRequest extends GeneratedMessageLite<DeleteSessionRequest, Builder> implements DeleteSessionRequestOrBuilder {
    public static final int ALL_FIELD_NUMBER = 2;
    public static final int BY_SESSION_ID_FIELD_NUMBER = 1;
    private static final DeleteSessionRequest DEFAULT_INSTANCE;
    private static volatile Parser<DeleteSessionRequest> PARSER;
    private int payloadCase_ = 0;
    private Object payload_;

    private DeleteSessionRequest() {
    }

    public enum PayloadCase {
        BY_SESSION_ID(1),
        ALL(2),
        PAYLOAD_NOT_SET(0);

        private final int value;

        PayloadCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static PayloadCase valueOf(int i) {
            return forNumber(i);
        }

        public static PayloadCase forNumber(int i) {
            if (i == 0) {
                return PAYLOAD_NOT_SET;
            }
            if (i == 1) {
                return BY_SESSION_ID;
            }
            if (i != 2) {
                return null;
            }
            return ALL;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
    public PayloadCase getPayloadCase() {
        return PayloadCase.forNumber(this.payloadCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPayload() {
        this.payloadCase_ = 0;
        this.payload_ = null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
    public boolean hasBySessionId() {
        return this.payloadCase_ == 1;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
    public DeleteBySessionId getBySessionId() {
        if (this.payloadCase_ == 1) {
            return (DeleteBySessionId) this.payload_;
        }
        return DeleteBySessionId.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBySessionId(DeleteBySessionId deleteBySessionId) {
        deleteBySessionId.getClass();
        this.payload_ = deleteBySessionId;
        this.payloadCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeBySessionId(DeleteBySessionId deleteBySessionId) {
        deleteBySessionId.getClass();
        if (this.payloadCase_ == 1 && this.payload_ != DeleteBySessionId.getDefaultInstance()) {
            this.payload_ = DeleteBySessionId.newBuilder((DeleteBySessionId) this.payload_).mergeFrom(deleteBySessionId).buildPartial();
        } else {
            this.payload_ = deleteBySessionId;
        }
        this.payloadCase_ = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearBySessionId() {
        if (this.payloadCase_ == 1) {
            this.payloadCase_ = 0;
            this.payload_ = null;
        }
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
    public boolean hasAll() {
        return this.payloadCase_ == 2;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
    public DeleteAll getAll() {
        if (this.payloadCase_ == 2) {
            return (DeleteAll) this.payload_;
        }
        return DeleteAll.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAll(DeleteAll deleteAll) {
        deleteAll.getClass();
        this.payload_ = deleteAll;
        this.payloadCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeAll(DeleteAll deleteAll) {
        deleteAll.getClass();
        if (this.payloadCase_ == 2 && this.payload_ != DeleteAll.getDefaultInstance()) {
            this.payload_ = DeleteAll.newBuilder((DeleteAll) this.payload_).mergeFrom(deleteAll).buildPartial();
        } else {
            this.payload_ = deleteAll;
        }
        this.payloadCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAll() {
        if (this.payloadCase_ == 2) {
            this.payloadCase_ = 0;
            this.payload_ = null;
        }
    }

    public static DeleteSessionRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DeleteSessionRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DeleteSessionRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DeleteSessionRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DeleteSessionRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DeleteSessionRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DeleteSessionRequest parseFrom(InputStream inputStream) throws IOException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteSessionRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteSessionRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DeleteSessionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteSessionRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteSessionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteSessionRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DeleteSessionRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteSessionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DeleteSessionRequest deleteSessionRequest) {
        return DEFAULT_INSTANCE.createBuilder(deleteSessionRequest);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeleteSessionRequest, Builder> implements DeleteSessionRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DeleteSessionRequest.DEFAULT_INSTANCE);
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
        public PayloadCase getPayloadCase() {
            return ((DeleteSessionRequest) this.instance).getPayloadCase();
        }

        public Builder clearPayload() {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).clearPayload();
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
        public boolean hasBySessionId() {
            return ((DeleteSessionRequest) this.instance).hasBySessionId();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
        public DeleteBySessionId getBySessionId() {
            return ((DeleteSessionRequest) this.instance).getBySessionId();
        }

        public Builder setBySessionId(DeleteBySessionId deleteBySessionId) {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).setBySessionId(deleteBySessionId);
            return this;
        }

        public Builder setBySessionId(DeleteBySessionId.Builder builder) {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).setBySessionId(builder.build());
            return this;
        }

        public Builder mergeBySessionId(DeleteBySessionId deleteBySessionId) {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).mergeBySessionId(deleteBySessionId);
            return this;
        }

        public Builder clearBySessionId() {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).clearBySessionId();
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
        public boolean hasAll() {
            return ((DeleteSessionRequest) this.instance).hasAll();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequestOrBuilder
        public DeleteAll getAll() {
            return ((DeleteSessionRequest) this.instance).getAll();
        }

        public Builder setAll(DeleteAll deleteAll) {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).setAll(deleteAll);
            return this;
        }

        public Builder setAll(DeleteAll.Builder builder) {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).setAll(builder.build());
            return this;
        }

        public Builder mergeAll(DeleteAll deleteAll) {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).mergeAll(deleteAll);
            return this;
        }

        public Builder clearAll() {
            copyOnWrite();
            ((DeleteSessionRequest) this.instance).clearAll();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.cerence.cloudservices.api.services.session.v1.DeleteSessionRequest$1, reason: invalid class name */
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
                return new DeleteSessionRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"payload_", "payloadCase_", DeleteBySessionId.class, DeleteAll.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DeleteSessionRequest> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DeleteSessionRequest.class) {
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
        DeleteSessionRequest deleteSessionRequest = new DeleteSessionRequest();
        DEFAULT_INSTANCE = deleteSessionRequest;
        GeneratedMessageLite.registerDefaultInstance(DeleteSessionRequest.class, deleteSessionRequest);
    }

    public static DeleteSessionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeleteSessionRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
