package com.spotify.connectivity.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.n51;
import p.o51;
import p.p51;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public final class ForceOfflineResponse extends i implements ForceOfflineResponseOrBuilder {
    private static final ForceOfflineResponse DEFAULT_INSTANCE;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int STATUS_CODE_FIELD_NUMBER = 1;
    private int bitField0_;
    private String errorMessage_ = "";
    private int statusCode_;

    /* JADX INFO: renamed from: com.spotify.connectivity.esperanto.proto.ForceOfflineResponse$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite$MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[3] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[4] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[5] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[6] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[0] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[1] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static final class Builder extends h01 implements ForceOfflineResponseOrBuilder {
        public /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        public /* bridge */ /* synthetic */ wj1 build() {
            return super.build();
        }

        @Override // p.h01, p.vj1
        public /* bridge */ /* synthetic */ wj1 buildPartial() {
            return super.buildPartial();
        }

        public /* bridge */ /* synthetic */ vj1 clear() {
            return super.clear();
        }

        public Builder clearErrorMessage() {
            copyOnWrite();
            ((ForceOfflineResponse) this.instance).clearErrorMessage();
            return this;
        }

        public Builder clearStatusCode() {
            copyOnWrite();
            ((ForceOfflineResponse) this.instance).clearStatusCode();
            return this;
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ Object clone() {
            return super.clone();
        }

        @Override // p.h01, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
        public String getErrorMessage() {
            return ((ForceOfflineResponse) this.instance).getErrorMessage();
        }

        @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
        public ByteString getErrorMessageBytes() {
            return ((ForceOfflineResponse) this.instance).getErrorMessageBytes();
        }

        @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
        public StatusCode getStatusCode() {
            return ((ForceOfflineResponse) this.instance).getStatusCode();
        }

        @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
        public int getStatusCodeValue() {
            return ((ForceOfflineResponse) this.instance).getStatusCodeValue();
        }

        @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
        public boolean hasErrorMessage() {
            return ((ForceOfflineResponse) this.instance).hasErrorMessage();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
            return super.internalMergeFrom((i) aVar);
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public Builder setErrorMessage(String str) {
            copyOnWrite();
            ((ForceOfflineResponse) this.instance).setErrorMessage(str);
            return this;
        }

        public Builder setErrorMessageBytes(ByteString byteString) {
            copyOnWrite();
            ((ForceOfflineResponse) this.instance).setErrorMessageBytes(byteString);
            return this;
        }

        public Builder setStatusCode(StatusCode statusCode) {
            copyOnWrite();
            ((ForceOfflineResponse) this.instance).setStatusCode(statusCode);
            return this;
        }

        public Builder setStatusCodeValue(int i) {
            copyOnWrite();
            ((ForceOfflineResponse) this.instance).setStatusCodeValue(i);
            return this;
        }

        private Builder() {
            super(ForceOfflineResponse.DEFAULT_INSTANCE);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ r1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        @Override // p.h01
        public /* bridge */ /* synthetic */ vj1 clone() {
            return super.clone();
        }

        @Override // p.h01, p.r1
        public /* bridge */ /* synthetic */ r1 mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString) {
            return super.mergeFrom(byteString);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(ByteString byteString, rl0 rl0Var) {
            return super.mergeFrom(byteString, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream) {
            return super.mergeFrom(inputStream);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(InputStream inputStream, rl0 rl0Var) {
            return super.mergeFrom(inputStream, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(au auVar) {
            return super.mergeFrom(auVar);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m146mergeFrom(au auVar, rl0 rl0Var) {
            return super.mergeFrom(auVar, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
            return super.mergeFrom(wj1Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
            return super.mergeFrom(bArr);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m147mergeFrom(byte[] bArr, int i, int i2) {
            return super.mergeFrom(bArr, i, i2);
        }

        /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ vj1 m148mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
            return super.mergeFrom(bArr, i, i2, rl0Var);
        }

        public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
            return super.mergeFrom(bArr, rl0Var);
        }
    }

    public enum StatusCode implements n51 {
        SUCCESS(0),
        NOT_FOUND(2),
        UNRECOGNIZED(-1);

        public static final int NOT_FOUND_VALUE = 2;
        public static final int SUCCESS_VALUE = 0;
        private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.esperanto.proto.ForceOfflineResponse.StatusCode.1
            /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
            public StatusCode m149findValueByNumber(int i) {
                return StatusCode.forNumber(i);
            }
        };
        private final int value;

        public static final class StatusCodeVerifier implements p51 {
            static final p51 INSTANCE = new StatusCodeVerifier();

            private StatusCodeVerifier() {
            }

            @Override // p.p51
            public boolean isInRange(int i) {
                return StatusCode.forNumber(i) != null;
            }
        }

        StatusCode(int i) {
            this.value = i;
        }

        public static StatusCode forNumber(int i) {
            if (i == 0) {
                return SUCCESS;
            }
            if (i != 2) {
                return null;
            }
            return NOT_FOUND;
        }

        public static o51 internalGetValueMap() {
            return internalValueMap;
        }

        public static p51 internalGetVerifier() {
            return StatusCodeVerifier.INSTANCE;
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static StatusCode valueOf(int i) {
            return forNumber(i);
        }
    }

    static {
        ForceOfflineResponse forceOfflineResponse = new ForceOfflineResponse();
        DEFAULT_INSTANCE = forceOfflineResponse;
        i.registerDefaultInstance(ForceOfflineResponse.class, forceOfflineResponse);
    }

    private ForceOfflineResponse() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearErrorMessage() {
        this.bitField0_ &= -2;
        this.errorMessage_ = getDefaultInstance().getErrorMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStatusCode() {
        this.statusCode_ = 0;
    }

    public static ForceOfflineResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.createBuilder();
    }

    public static ForceOfflineResponse parseDelimitedFrom(InputStream inputStream) {
        return (ForceOfflineResponse) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ForceOfflineResponse parseFrom(ByteBuffer byteBuffer) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorMessage(String str) {
        str.getClass();
        this.bitField0_ |= 1;
        this.errorMessage_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setErrorMessageBytes(ByteString byteString) {
        a.checkByteStringIsUtf8(byteString);
        this.errorMessage_ = byteString.s();
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode_ = statusCode.getNumber();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStatusCodeValue(int i) {
        this.statusCode_ = i;
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002ለ\u0000", new Object[]{"bitField0_", "statusCode_", "errorMessage_"});
            case 3:
                return new ForceOfflineResponse();
            case 4:
                return new Builder(anonymousClass1);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ForceOfflineResponse.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
    public String getErrorMessage() {
        return this.errorMessage_;
    }

    @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
    public ByteString getErrorMessageBytes() {
        return ByteString.d(this.errorMessage_);
    }

    @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
    public StatusCode getStatusCode() {
        StatusCode statusCodeForNumber = StatusCode.forNumber(this.statusCode_);
        return statusCodeForNumber == null ? StatusCode.UNRECOGNIZED : statusCodeForNumber;
    }

    @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
    public int getStatusCodeValue() {
        return this.statusCode_;
    }

    @Override // com.spotify.connectivity.esperanto.proto.ForceOfflineResponseOrBuilder
    public boolean hasErrorMessage() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.protobuf.i, p.wj1
    public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }

    public /* bridge */ /* synthetic */ vj1 toBuilder() {
        return super.toBuilder();
    }

    public static Builder newBuilder(ForceOfflineResponse forceOfflineResponse) {
        return (Builder) DEFAULT_INSTANCE.createBuilder(forceOfflineResponse);
    }

    public static ForceOfflineResponse parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
        return (ForceOfflineResponse) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ForceOfflineResponse parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
    }

    public static ForceOfflineResponse parseFrom(ByteString byteString) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ForceOfflineResponse parseFrom(ByteString byteString, rl0 rl0Var) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
    }

    public static ForceOfflineResponse parseFrom(byte[] bArr) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ForceOfflineResponse parseFrom(byte[] bArr, rl0 rl0Var) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
    }

    public static ForceOfflineResponse parseFrom(InputStream inputStream) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ForceOfflineResponse parseFrom(InputStream inputStream, rl0 rl0Var) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
    }

    public static ForceOfflineResponse parseFrom(au auVar) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, auVar);
    }

    public static ForceOfflineResponse parseFrom(au auVar, rl0 rl0Var) {
        return (ForceOfflineResponse) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
    }
}
