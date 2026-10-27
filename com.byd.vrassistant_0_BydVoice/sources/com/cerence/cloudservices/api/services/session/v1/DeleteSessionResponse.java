package com.cerence.cloudservices.api.services.session.v1;

import com.cerence.cloudservices.api.services.session.v1.Metadata;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.rpc.Status;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public final class DeleteSessionResponse extends GeneratedMessageLite<DeleteSessionResponse, Builder> implements DeleteSessionResponseOrBuilder {
    private static final DeleteSessionResponse DEFAULT_INSTANCE;
    public static final int METADATA_FIELD_NUMBER = 2;
    private static volatile Parser<DeleteSessionResponse> PARSER = null;
    public static final int STATUS_FIELD_NUMBER = 1;
    private int deleteResultCase_ = 0;
    private Object deleteResult_;
    private Status status_;

    private DeleteSessionResponse() {
    }

    public enum DeleteResultCase {
        METADATA(2),
        DELETERESULT_NOT_SET(0);

        private final int value;

        DeleteResultCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static DeleteResultCase valueOf(int i) {
            return forNumber(i);
        }

        public static DeleteResultCase forNumber(int i) {
            if (i == 0) {
                return DELETERESULT_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return METADATA;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
    public DeleteResultCase getDeleteResultCase() {
        return DeleteResultCase.forNumber(this.deleteResultCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDeleteResult() {
        this.deleteResultCase_ = 0;
        this.deleteResult_ = null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
    public boolean hasStatus() {
        return this.status_ != null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
    public Status getStatus() {
        Status status = this.status_;
        return status == null ? Status.getDefaultInstance() : status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatus(Status status) {
        status.getClass();
        this.status_ = status;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStatus(Status status) {
        status.getClass();
        Status status2 = this.status_;
        if (status2 != null && status2 != Status.getDefaultInstance()) {
            this.status_ = Status.newBuilder(this.status_).mergeFrom(status).buildPartial();
        } else {
            this.status_ = status;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStatus() {
        this.status_ = null;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
    public boolean hasMetadata() {
        return this.deleteResultCase_ == 2;
    }

    @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
    public Metadata getMetadata() {
        if (this.deleteResultCase_ == 2) {
            return (Metadata) this.deleteResult_;
        }
        return Metadata.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMetadata(Metadata metadata) {
        metadata.getClass();
        this.deleteResult_ = metadata;
        this.deleteResultCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeMetadata(Metadata metadata) {
        metadata.getClass();
        if (this.deleteResultCase_ == 2 && this.deleteResult_ != Metadata.getDefaultInstance()) {
            this.deleteResult_ = Metadata.newBuilder((Metadata) this.deleteResult_).mergeFrom(metadata).buildPartial();
        } else {
            this.deleteResult_ = metadata;
        }
        this.deleteResultCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearMetadata() {
        if (this.deleteResultCase_ == 2) {
            this.deleteResultCase_ = 0;
            this.deleteResult_ = null;
        }
    }

    public static DeleteSessionResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static DeleteSessionResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static DeleteSessionResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DeleteSessionResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DeleteSessionResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DeleteSessionResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DeleteSessionResponse parseFrom(InputStream inputStream) throws IOException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteSessionResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteSessionResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DeleteSessionResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DeleteSessionResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteSessionResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DeleteSessionResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DeleteSessionResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DeleteSessionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(DeleteSessionResponse deleteSessionResponse) {
        return DEFAULT_INSTANCE.createBuilder(deleteSessionResponse);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeleteSessionResponse, Builder> implements DeleteSessionResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(DeleteSessionResponse.DEFAULT_INSTANCE);
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
        public DeleteResultCase getDeleteResultCase() {
            return ((DeleteSessionResponse) this.instance).getDeleteResultCase();
        }

        public Builder clearDeleteResult() {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).clearDeleteResult();
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
        public boolean hasStatus() {
            return ((DeleteSessionResponse) this.instance).hasStatus();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
        public Status getStatus() {
            return ((DeleteSessionResponse) this.instance).getStatus();
        }

        public Builder setStatus(Status status) {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).setStatus(status);
            return this;
        }

        public Builder setStatus(Status.Builder builder) {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).setStatus(builder.build());
            return this;
        }

        public Builder mergeStatus(Status status) {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).mergeStatus(status);
            return this;
        }

        public Builder clearStatus() {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).clearStatus();
            return this;
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
        public boolean hasMetadata() {
            return ((DeleteSessionResponse) this.instance).hasMetadata();
        }

        @Override // com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponseOrBuilder
        public Metadata getMetadata() {
            return ((DeleteSessionResponse) this.instance).getMetadata();
        }

        public Builder setMetadata(Metadata metadata) {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).setMetadata(metadata);
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).setMetadata(builder.build());
            return this;
        }

        public Builder mergeMetadata(Metadata metadata) {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).mergeMetadata(metadata);
            return this;
        }

        public Builder clearMetadata() {
            copyOnWrite();
            ((DeleteSessionResponse) this.instance).clearMetadata();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.cerence.cloudservices.api.services.session.v1.DeleteSessionResponse$1, reason: invalid class name */
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
                return new DeleteSessionResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002<\u0000", new Object[]{"deleteResult_", "deleteResultCase_", "status_", Metadata.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<DeleteSessionResponse> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (DeleteSessionResponse.class) {
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
        DeleteSessionResponse deleteSessionResponse = new DeleteSessionResponse();
        DEFAULT_INSTANCE = deleteSessionResponse;
        GeneratedMessageLite.registerDefaultInstance(DeleteSessionResponse.class, deleteSessionResponse);
    }

    public static DeleteSessionResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DeleteSessionResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
