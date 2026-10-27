package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.connectivity.auth.common.esperanto.proto.EsAuthBlob;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired;
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
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsAuthenticateResult {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult$1, reason: invalid class name */
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

    public static final class AuthenticateFailure extends i implements AuthenticateFailureOrBuilder {
        private static final AuthenticateFailure DEFAULT_INSTANCE;
        public static final int FAILURE_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER = null;
        public static final int UNKNOWNFAILURE_FIELD_NUMBER = 2;
        private int reasonCase_ = 0;
        private Object reason_;

        public static final class Builder extends h01 implements AuthenticateFailureOrBuilder {
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

            public Builder clearFailure() {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).clearFailure();
                return this;
            }

            public Builder clearReason() {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).clearReason();
                return this;
            }

            public Builder clearUnknownFailure() {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).clearUnknownFailure();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
            public Reason getFailure() {
                return ((AuthenticateFailure) this.instance).getFailure();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
            public int getFailureValue() {
                return ((AuthenticateFailure) this.instance).getFailureValue();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
            public ReasonCase getReasonCase() {
                return ((AuthenticateFailure) this.instance).getReasonCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
            public UnknownReason getUnknownFailure() {
                return ((AuthenticateFailure) this.instance).getUnknownFailure();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
            public boolean hasFailure() {
                return ((AuthenticateFailure) this.instance).hasFailure();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
            public boolean hasUnknownFailure() {
                return ((AuthenticateFailure) this.instance).hasUnknownFailure();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeUnknownFailure(UnknownReason unknownReason) {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).mergeUnknownFailure(unknownReason);
                return this;
            }

            public Builder setFailure(Reason reason) {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).setFailure(reason);
                return this;
            }

            public Builder setFailureValue(int i) {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).setFailureValue(i);
                return this;
            }

            public Builder setUnknownFailure(UnknownReason unknownReason) {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).setUnknownFailure(unknownReason);
                return this;
            }

            private Builder() {
                super(AuthenticateFailure.DEFAULT_INSTANCE);
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

            public Builder setUnknownFailure(UnknownReason.Builder builder) {
                copyOnWrite();
                ((AuthenticateFailure) this.instance).setUnknownFailure((UnknownReason) builder.build());
                return this;
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
            public /* bridge */ /* synthetic */ vj1 m74mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m75mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m76mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum Reason implements n51 {
            success(0),
            aborted(1),
            unsupportedResponse(2),
            nonSuccessfulResponse(3),
            httpTransportError(4),
            unsupportedAuthenticationMethod(5),
            invalidArguments(6),
            unknownBackendError(7),
            invalidCredentials(8),
            badRequest(9),
            unsupportedLoginProtocol(10),
            timeout(11),
            unknownIdentifier(12),
            tooManyAttempts(13),
            invalidPhoneNumber(14),
            tryAgainLater(15),
            hashMismatch(16),
            UNRECOGNIZED(-1);

            public static final int aborted_VALUE = 1;
            public static final int badRequest_VALUE = 9;
            public static final int hashMismatch_VALUE = 16;
            public static final int httpTransportError_VALUE = 4;
            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.Reason.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public Reason m77findValueByNumber(int i) {
                    return Reason.forNumber(i);
                }
            };
            public static final int invalidArguments_VALUE = 6;
            public static final int invalidCredentials_VALUE = 8;
            public static final int invalidPhoneNumber_VALUE = 14;
            public static final int nonSuccessfulResponse_VALUE = 3;
            public static final int success_VALUE = 0;
            public static final int timeout_VALUE = 11;
            public static final int tooManyAttempts_VALUE = 13;
            public static final int tryAgainLater_VALUE = 15;
            public static final int unknownBackendError_VALUE = 7;
            public static final int unknownIdentifier_VALUE = 12;
            public static final int unsupportedAuthenticationMethod_VALUE = 5;
            public static final int unsupportedLoginProtocol_VALUE = 10;
            public static final int unsupportedResponse_VALUE = 2;
            private final int value;

            public static final class ReasonVerifier implements p51 {
                static final p51 INSTANCE = new ReasonVerifier();

                private ReasonVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return Reason.forNumber(i) != null;
                }
            }

            Reason(int i) {
                this.value = i;
            }

            public static Reason forNumber(int i) {
                switch (i) {
                    case 0:
                        return success;
                    case 1:
                        return aborted;
                    case 2:
                        return unsupportedResponse;
                    case 3:
                        return nonSuccessfulResponse;
                    case 4:
                        return httpTransportError;
                    case 5:
                        return unsupportedAuthenticationMethod;
                    case 6:
                        return invalidArguments;
                    case 7:
                        return unknownBackendError;
                    case 8:
                        return invalidCredentials;
                    case 9:
                        return badRequest;
                    case 10:
                        return unsupportedLoginProtocol;
                    case 11:
                        return timeout;
                    case 12:
                        return unknownIdentifier;
                    case 13:
                        return tooManyAttempts;
                    case 14:
                        return invalidPhoneNumber;
                    case 15:
                        return tryAgainLater;
                    case 16:
                        return hashMismatch;
                    default:
                        return null;
                }
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return ReasonVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Reason valueOf(int i) {
                return forNumber(i);
            }
        }

        public enum ReasonCase {
            FAILURE(1),
            UNKNOWNFAILURE(2),
            REASON_NOT_SET(0);

            private final int value;

            ReasonCase(int i) {
                this.value = i;
            }

            public static ReasonCase forNumber(int i) {
                if (i == 0) {
                    return REASON_NOT_SET;
                }
                if (i == 1) {
                    return FAILURE;
                }
                if (i != 2) {
                    return null;
                }
                return UNKNOWNFAILURE;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ReasonCase valueOf(int i) {
                return forNumber(i);
            }
        }

        public static final class UnknownReason extends i implements UnknownReasonOrBuilder {
            public static final int CODE_FIELD_NUMBER = 1;
            private static final UnknownReason DEFAULT_INSTANCE;
            public static final int MESSAGE_FIELD_NUMBER = 2;
            private static volatile ds1 PARSER;
            private int code_;
            private String message_ = "";

            public static final class Builder extends h01 implements UnknownReasonOrBuilder {
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

                public Builder clearCode() {
                    copyOnWrite();
                    ((UnknownReason) this.instance).clearCode();
                    return this;
                }

                public Builder clearMessage() {
                    copyOnWrite();
                    ((UnknownReason) this.instance).clearMessage();
                    return this;
                }

                @Override // p.h01
                public /* bridge */ /* synthetic */ Object clone() {
                    return super.clone();
                }

                @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.UnknownReasonOrBuilder
                public int getCode() {
                    return ((UnknownReason) this.instance).getCode();
                }

                @Override // p.h01, p.yj1
                public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                    return super.getDefaultInstanceForType();
                }

                @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.UnknownReasonOrBuilder
                public String getMessage() {
                    return ((UnknownReason) this.instance).getMessage();
                }

                @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.UnknownReasonOrBuilder
                public ByteString getMessageBytes() {
                    return ((UnknownReason) this.instance).getMessageBytes();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public Builder setCode(int i) {
                    copyOnWrite();
                    ((UnknownReason) this.instance).setCode(i);
                    return this;
                }

                public Builder setMessage(String str) {
                    copyOnWrite();
                    ((UnknownReason) this.instance).setMessage(str);
                    return this;
                }

                public Builder setMessageBytes(ByteString byteString) {
                    copyOnWrite();
                    ((UnknownReason) this.instance).setMessageBytes(byteString);
                    return this;
                }

                private Builder() {
                    super(UnknownReason.DEFAULT_INSTANCE);
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
                public /* bridge */ /* synthetic */ vj1 m78mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                    return super.mergeFrom(wj1Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                    return super.mergeFrom(bArr);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m79mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m80mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                    return super.mergeFrom(bArr, rl0Var);
                }
            }

            static {
                UnknownReason unknownReason = new UnknownReason();
                DEFAULT_INSTANCE = unknownReason;
                i.registerDefaultInstance(UnknownReason.class, unknownReason);
            }

            private UnknownReason() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearCode() {
                this.code_ = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearMessage() {
                this.message_ = getDefaultInstance().getMessage();
            }

            public static UnknownReason getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static UnknownReason parseDelimitedFrom(InputStream inputStream) {
                return (UnknownReason) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static UnknownReason parseFrom(ByteBuffer byteBuffer) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setCode(int i) {
                this.code_ = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setMessage(String str) {
                str.getClass();
                this.message_ = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setMessageBytes(ByteString byteString) {
                a.checkByteStringIsUtf8(byteString);
                this.message_ = byteString.s();
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
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002Ȉ", new Object[]{"code_", "message_"});
                    case 3:
                        return new UnknownReason();
                    case 4:
                        return new Builder(anonymousClass1);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (UnknownReason.class) {
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.UnknownReasonOrBuilder
            public int getCode() {
                return this.code_;
            }

            @Override // com.google.protobuf.i, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.UnknownReasonOrBuilder
            public String getMessage() {
                return this.message_;
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailure.UnknownReasonOrBuilder
            public ByteString getMessageBytes() {
                return ByteString.d(this.message_);
            }

            @Override // com.google.protobuf.i, p.wj1
            public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }

            public /* bridge */ /* synthetic */ vj1 toBuilder() {
                return super.toBuilder();
            }

            public static Builder newBuilder(UnknownReason unknownReason) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(unknownReason);
            }

            public static UnknownReason parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
                return (UnknownReason) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static UnknownReason parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
            }

            public static UnknownReason parseFrom(ByteString byteString) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static UnknownReason parseFrom(ByteString byteString, rl0 rl0Var) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
            }

            public static UnknownReason parseFrom(byte[] bArr) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static UnknownReason parseFrom(byte[] bArr, rl0 rl0Var) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
            }

            public static UnknownReason parseFrom(InputStream inputStream) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static UnknownReason parseFrom(InputStream inputStream, rl0 rl0Var) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static UnknownReason parseFrom(au auVar) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, auVar);
            }

            public static UnknownReason parseFrom(au auVar, rl0 rl0Var) {
                return (UnknownReason) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
            }
        }

        public interface UnknownReasonOrBuilder extends yj1 {
            int getCode();

            @Override // p.yj1
            /* synthetic */ wj1 getDefaultInstanceForType();

            String getMessage();

            ByteString getMessageBytes();

            @Override // p.yj1
            /* synthetic */ boolean isInitialized();
        }

        static {
            AuthenticateFailure authenticateFailure = new AuthenticateFailure();
            DEFAULT_INSTANCE = authenticateFailure;
            i.registerDefaultInstance(AuthenticateFailure.class, authenticateFailure);
        }

        private AuthenticateFailure() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFailure() {
            if (this.reasonCase_ == 1) {
                this.reasonCase_ = 0;
                this.reason_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearReason() {
            this.reasonCase_ = 0;
            this.reason_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnknownFailure() {
            if (this.reasonCase_ == 2) {
                this.reasonCase_ = 0;
                this.reason_ = null;
            }
        }

        public static AuthenticateFailure getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnknownFailure(UnknownReason unknownReason) {
            unknownReason.getClass();
            if (this.reasonCase_ != 2 || this.reason_ == UnknownReason.getDefaultInstance()) {
                this.reason_ = unknownReason;
            } else {
                this.reason_ = ((UnknownReason.Builder) UnknownReason.newBuilder((UnknownReason) this.reason_).mergeFrom((i) unknownReason)).buildPartial();
            }
            this.reasonCase_ = 2;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AuthenticateFailure parseDelimitedFrom(InputStream inputStream) {
            return (AuthenticateFailure) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateFailure parseFrom(ByteBuffer byteBuffer) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFailure(Reason reason) {
            this.reason_ = Integer.valueOf(reason.getNumber());
            this.reasonCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFailureValue(int i) {
            this.reasonCase_ = 1;
            this.reason_ = Integer.valueOf(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnknownFailure(UnknownReason unknownReason) {
            unknownReason.getClass();
            this.reason_ = unknownReason;
            this.reasonCase_ = 2;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001?\u0000\u0002<\u0000", new Object[]{"reason_", "reasonCase_", UnknownReason.class});
                case 3:
                    return new AuthenticateFailure();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AuthenticateFailure.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
        public Reason getFailure() {
            if (this.reasonCase_ != 1) {
                return Reason.success;
            }
            Reason reasonForNumber = Reason.forNumber(((Integer) this.reason_).intValue());
            return reasonForNumber == null ? Reason.UNRECOGNIZED : reasonForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
        public int getFailureValue() {
            if (this.reasonCase_ == 1) {
                return ((Integer) this.reason_).intValue();
            }
            return 0;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
        public ReasonCase getReasonCase() {
            return ReasonCase.forNumber(this.reasonCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
        public UnknownReason getUnknownFailure() {
            return this.reasonCase_ == 2 ? (UnknownReason) this.reason_ : UnknownReason.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
        public boolean hasFailure() {
            return this.reasonCase_ == 1;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateFailureOrBuilder
        public boolean hasUnknownFailure() {
            return this.reasonCase_ == 2;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AuthenticateFailure authenticateFailure) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(authenticateFailure);
        }

        public static AuthenticateFailure parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateFailure) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateFailure parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AuthenticateFailure parseFrom(ByteString byteString) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthenticateFailure parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AuthenticateFailure parseFrom(byte[] bArr) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthenticateFailure parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AuthenticateFailure parseFrom(InputStream inputStream) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateFailure parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateFailure parseFrom(au auVar) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AuthenticateFailure parseFrom(au auVar, rl0 rl0Var) {
            return (AuthenticateFailure) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AuthenticateFailureOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        AuthenticateFailure.Reason getFailure();

        int getFailureValue();

        AuthenticateFailure.ReasonCase getReasonCase();

        AuthenticateFailure.UnknownReason getUnknownFailure();

        boolean hasFailure();

        boolean hasUnknownFailure();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class AuthenticateResult extends i implements AuthenticateResultOrBuilder {
        public static final int CODEREQUIRED_FIELD_NUMBER = 3;
        private static final AuthenticateResult DEFAULT_INSTANCE;
        public static final int FAILURE_FIELD_NUMBER = 2;
        public static final int INTERACTIONREQUIRED_FIELD_NUMBER = 5;
        private static volatile ds1 PARSER = null;
        public static final int SIGNUPREQUIRED_FIELD_NUMBER = 4;
        public static final int SUCCESS_FIELD_NUMBER = 1;
        private int resultCase_ = 0;
        private Object result_;

        public static final class Builder extends h01 implements AuthenticateResultOrBuilder {
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

            public Builder clearCodeRequired() {
                copyOnWrite();
                ((AuthenticateResult) this.instance).clearCodeRequired();
                return this;
            }

            public Builder clearFailure() {
                copyOnWrite();
                ((AuthenticateResult) this.instance).clearFailure();
                return this;
            }

            public Builder clearInteractionRequired() {
                copyOnWrite();
                ((AuthenticateResult) this.instance).clearInteractionRequired();
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((AuthenticateResult) this.instance).clearResult();
                return this;
            }

            public Builder clearSignupRequired() {
                copyOnWrite();
                ((AuthenticateResult) this.instance).clearSignupRequired();
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((AuthenticateResult) this.instance).clearSuccess();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public EsCodeRequired.CodeRequired getCodeRequired() {
                return ((AuthenticateResult) this.instance).getCodeRequired();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public AuthenticateFailure getFailure() {
                return ((AuthenticateResult) this.instance).getFailure();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public EsInteractionRequired.InteractionRequired getInteractionRequired() {
                return ((AuthenticateResult) this.instance).getInteractionRequired();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public ResultCase getResultCase() {
                return ((AuthenticateResult) this.instance).getResultCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public EsSignupRequired.SignupRequired getSignupRequired() {
                return ((AuthenticateResult) this.instance).getSignupRequired();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public AuthenticateSuccess getSuccess() {
                return ((AuthenticateResult) this.instance).getSuccess();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public boolean hasCodeRequired() {
                return ((AuthenticateResult) this.instance).hasCodeRequired();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public boolean hasFailure() {
                return ((AuthenticateResult) this.instance).hasFailure();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public boolean hasInteractionRequired() {
                return ((AuthenticateResult) this.instance).hasInteractionRequired();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public boolean hasSignupRequired() {
                return ((AuthenticateResult) this.instance).hasSignupRequired();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
            public boolean hasSuccess() {
                return ((AuthenticateResult) this.instance).hasSuccess();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeCodeRequired(EsCodeRequired.CodeRequired codeRequired) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).mergeCodeRequired(codeRequired);
                return this;
            }

            public Builder mergeFailure(AuthenticateFailure authenticateFailure) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).mergeFailure(authenticateFailure);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeInteractionRequired(EsInteractionRequired.InteractionRequired interactionRequired) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).mergeInteractionRequired(interactionRequired);
                return this;
            }

            public Builder mergeSignupRequired(EsSignupRequired.SignupRequired signupRequired) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).mergeSignupRequired(signupRequired);
                return this;
            }

            public Builder mergeSuccess(AuthenticateSuccess authenticateSuccess) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).mergeSuccess(authenticateSuccess);
                return this;
            }

            public Builder setCodeRequired(EsCodeRequired.CodeRequired codeRequired) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setCodeRequired(codeRequired);
                return this;
            }

            public Builder setFailure(AuthenticateFailure authenticateFailure) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setFailure(authenticateFailure);
                return this;
            }

            public Builder setInteractionRequired(EsInteractionRequired.InteractionRequired interactionRequired) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setInteractionRequired(interactionRequired);
                return this;
            }

            public Builder setSignupRequired(EsSignupRequired.SignupRequired signupRequired) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setSignupRequired(signupRequired);
                return this;
            }

            public Builder setSuccess(AuthenticateSuccess authenticateSuccess) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setSuccess(authenticateSuccess);
                return this;
            }

            private Builder() {
                super(AuthenticateResult.DEFAULT_INSTANCE);
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

            public Builder setCodeRequired(EsCodeRequired.CodeRequired.Builder builder) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setCodeRequired((EsCodeRequired.CodeRequired) builder.build());
                return this;
            }

            public Builder setFailure(AuthenticateFailure.Builder builder) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setFailure((AuthenticateFailure) builder.build());
                return this;
            }

            public Builder setInteractionRequired(EsInteractionRequired.InteractionRequired.Builder builder) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setInteractionRequired((EsInteractionRequired.InteractionRequired) builder.build());
                return this;
            }

            public Builder setSignupRequired(EsSignupRequired.SignupRequired.Builder builder) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setSignupRequired((EsSignupRequired.SignupRequired) builder.build());
                return this;
            }

            public Builder setSuccess(AuthenticateSuccess.Builder builder) {
                copyOnWrite();
                ((AuthenticateResult) this.instance).setSuccess((AuthenticateSuccess) builder.build());
                return this;
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
            public /* bridge */ /* synthetic */ vj1 m81mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m82mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m83mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ResultCase {
            SUCCESS(1),
            FAILURE(2),
            CODEREQUIRED(3),
            SIGNUPREQUIRED(4),
            INTERACTIONREQUIRED(5),
            RESULT_NOT_SET(0);

            private final int value;

            ResultCase(int i) {
                this.value = i;
            }

            public static ResultCase forNumber(int i) {
                if (i == 0) {
                    return RESULT_NOT_SET;
                }
                if (i == 1) {
                    return SUCCESS;
                }
                if (i == 2) {
                    return FAILURE;
                }
                if (i == 3) {
                    return CODEREQUIRED;
                }
                if (i == 4) {
                    return SIGNUPREQUIRED;
                }
                if (i != 5) {
                    return null;
                }
                return INTERACTIONREQUIRED;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ResultCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            AuthenticateResult authenticateResult = new AuthenticateResult();
            DEFAULT_INSTANCE = authenticateResult;
            i.registerDefaultInstance(AuthenticateResult.class, authenticateResult);
        }

        private AuthenticateResult() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCodeRequired() {
            if (this.resultCase_ == 3) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFailure() {
            if (this.resultCase_ == 2) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInteractionRequired() {
            if (this.resultCase_ == 5) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResult() {
            this.resultCase_ = 0;
            this.result_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSignupRequired() {
            if (this.resultCase_ == 4) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSuccess() {
            if (this.resultCase_ == 1) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        public static AuthenticateResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCodeRequired(EsCodeRequired.CodeRequired codeRequired) {
            codeRequired.getClass();
            if (this.resultCase_ != 3 || this.result_ == EsCodeRequired.CodeRequired.getDefaultInstance()) {
                this.result_ = codeRequired;
            } else {
                this.result_ = ((EsCodeRequired.CodeRequired.Builder) EsCodeRequired.CodeRequired.newBuilder((EsCodeRequired.CodeRequired) this.result_).mergeFrom((i) codeRequired)).buildPartial();
            }
            this.resultCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFailure(AuthenticateFailure authenticateFailure) {
            authenticateFailure.getClass();
            if (this.resultCase_ != 2 || this.result_ == AuthenticateFailure.getDefaultInstance()) {
                this.result_ = authenticateFailure;
            } else {
                this.result_ = ((AuthenticateFailure.Builder) AuthenticateFailure.newBuilder((AuthenticateFailure) this.result_).mergeFrom((i) authenticateFailure)).buildPartial();
            }
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeInteractionRequired(EsInteractionRequired.InteractionRequired interactionRequired) {
            interactionRequired.getClass();
            if (this.resultCase_ != 5 || this.result_ == EsInteractionRequired.InteractionRequired.getDefaultInstance()) {
                this.result_ = interactionRequired;
            } else {
                this.result_ = ((EsInteractionRequired.InteractionRequired.Builder) EsInteractionRequired.InteractionRequired.newBuilder((EsInteractionRequired.InteractionRequired) this.result_).mergeFrom((i) interactionRequired)).buildPartial();
            }
            this.resultCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSignupRequired(EsSignupRequired.SignupRequired signupRequired) {
            signupRequired.getClass();
            if (this.resultCase_ != 4 || this.result_ == EsSignupRequired.SignupRequired.getDefaultInstance()) {
                this.result_ = signupRequired;
            } else {
                this.result_ = ((EsSignupRequired.SignupRequired.Builder) EsSignupRequired.SignupRequired.newBuilder((EsSignupRequired.SignupRequired) this.result_).mergeFrom((i) signupRequired)).buildPartial();
            }
            this.resultCase_ = 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSuccess(AuthenticateSuccess authenticateSuccess) {
            authenticateSuccess.getClass();
            if (this.resultCase_ != 1 || this.result_ == AuthenticateSuccess.getDefaultInstance()) {
                this.result_ = authenticateSuccess;
            } else {
                this.result_ = ((AuthenticateSuccess.Builder) AuthenticateSuccess.newBuilder((AuthenticateSuccess) this.result_).mergeFrom((i) authenticateSuccess)).buildPartial();
            }
            this.resultCase_ = 1;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AuthenticateResult parseDelimitedFrom(InputStream inputStream) {
            return (AuthenticateResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateResult parseFrom(ByteBuffer byteBuffer) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCodeRequired(EsCodeRequired.CodeRequired codeRequired) {
            codeRequired.getClass();
            this.result_ = codeRequired;
            this.resultCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFailure(AuthenticateFailure authenticateFailure) {
            authenticateFailure.getClass();
            this.result_ = authenticateFailure;
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionRequired(EsInteractionRequired.InteractionRequired interactionRequired) {
            interactionRequired.getClass();
            this.result_ = interactionRequired;
            this.resultCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSignupRequired(EsSignupRequired.SignupRequired signupRequired) {
            signupRequired.getClass();
            this.result_ = signupRequired;
            this.resultCase_ = 4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuccess(AuthenticateSuccess authenticateSuccess) {
            authenticateSuccess.getClass();
            this.result_ = authenticateSuccess;
            this.resultCase_ = 1;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0001\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000", new Object[]{"result_", "resultCase_", AuthenticateSuccess.class, AuthenticateFailure.class, EsCodeRequired.CodeRequired.class, EsSignupRequired.SignupRequired.class, EsInteractionRequired.InteractionRequired.class});
                case 3:
                    return new AuthenticateResult();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AuthenticateResult.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public EsCodeRequired.CodeRequired getCodeRequired() {
            return this.resultCase_ == 3 ? (EsCodeRequired.CodeRequired) this.result_ : EsCodeRequired.CodeRequired.getDefaultInstance();
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public AuthenticateFailure getFailure() {
            return this.resultCase_ == 2 ? (AuthenticateFailure) this.result_ : AuthenticateFailure.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public EsInteractionRequired.InteractionRequired getInteractionRequired() {
            return this.resultCase_ == 5 ? (EsInteractionRequired.InteractionRequired) this.result_ : EsInteractionRequired.InteractionRequired.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public EsSignupRequired.SignupRequired getSignupRequired() {
            return this.resultCase_ == 4 ? (EsSignupRequired.SignupRequired) this.result_ : EsSignupRequired.SignupRequired.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public AuthenticateSuccess getSuccess() {
            return this.resultCase_ == 1 ? (AuthenticateSuccess) this.result_ : AuthenticateSuccess.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public boolean hasCodeRequired() {
            return this.resultCase_ == 3;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public boolean hasFailure() {
            return this.resultCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public boolean hasInteractionRequired() {
            return this.resultCase_ == 5;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public boolean hasSignupRequired() {
            return this.resultCase_ == 4;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateResultOrBuilder
        public boolean hasSuccess() {
            return this.resultCase_ == 1;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AuthenticateResult authenticateResult) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(authenticateResult);
        }

        public static AuthenticateResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AuthenticateResult parseFrom(ByteString byteString) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthenticateResult parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AuthenticateResult parseFrom(byte[] bArr) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthenticateResult parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AuthenticateResult parseFrom(InputStream inputStream) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateResult parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateResult parseFrom(au auVar) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AuthenticateResult parseFrom(au auVar, rl0 rl0Var) {
            return (AuthenticateResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AuthenticateResultOrBuilder extends yj1 {
        EsCodeRequired.CodeRequired getCodeRequired();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        AuthenticateFailure getFailure();

        EsInteractionRequired.InteractionRequired getInteractionRequired();

        AuthenticateResult.ResultCase getResultCase();

        EsSignupRequired.SignupRequired getSignupRequired();

        AuthenticateSuccess getSuccess();

        boolean hasCodeRequired();

        boolean hasFailure();

        boolean hasInteractionRequired();

        boolean hasSignupRequired();

        boolean hasSuccess();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class AuthenticateSuccess extends i implements AuthenticateSuccessOrBuilder {
        public static final int ACCESSTOKEN_FIELD_NUMBER = 2;
        public static final int AUTHBLOB_FIELD_NUMBER = 3;
        private static final AuthenticateSuccess DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int USERNAME_FIELD_NUMBER = 1;
        private EsAuthBlob.AuthBlob authBlob_;
        private Object optionalAccessToken_;
        private int optionalAccessTokenCase_ = 0;
        private String username_ = "";

        public static final class Builder extends h01 implements AuthenticateSuccessOrBuilder {
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

            public Builder clearAccessToken() {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).clearAccessToken();
                return this;
            }

            public Builder clearAuthBlob() {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).clearAuthBlob();
                return this;
            }

            public Builder clearOptionalAccessToken() {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).clearOptionalAccessToken();
                return this;
            }

            public Builder clearUsername() {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).clearUsername();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public EsAccessToken.AccessToken getAccessToken() {
                return ((AuthenticateSuccess) this.instance).getAccessToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public EsAuthBlob.AuthBlob getAuthBlob() {
                return ((AuthenticateSuccess) this.instance).getAuthBlob();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public OptionalAccessTokenCase getOptionalAccessTokenCase() {
                return ((AuthenticateSuccess) this.instance).getOptionalAccessTokenCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public String getUsername() {
                return ((AuthenticateSuccess) this.instance).getUsername();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public ByteString getUsernameBytes() {
                return ((AuthenticateSuccess) this.instance).getUsernameBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public boolean hasAccessToken() {
                return ((AuthenticateSuccess) this.instance).hasAccessToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
            public boolean hasAuthBlob() {
                return ((AuthenticateSuccess) this.instance).hasAuthBlob();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeAccessToken(EsAccessToken.AccessToken accessToken) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).mergeAccessToken(accessToken);
                return this;
            }

            public Builder mergeAuthBlob(EsAuthBlob.AuthBlob authBlob) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).mergeAuthBlob(authBlob);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAccessToken(EsAccessToken.AccessToken accessToken) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).setAccessToken(accessToken);
                return this;
            }

            public Builder setAuthBlob(EsAuthBlob.AuthBlob authBlob) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).setAuthBlob(authBlob);
                return this;
            }

            public Builder setUsername(String str) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).setUsername(str);
                return this;
            }

            public Builder setUsernameBytes(ByteString byteString) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).setUsernameBytes(byteString);
                return this;
            }

            private Builder() {
                super(AuthenticateSuccess.DEFAULT_INSTANCE);
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

            public Builder setAccessToken(EsAccessToken.AccessToken.Builder builder) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).setAccessToken((EsAccessToken.AccessToken) builder.build());
                return this;
            }

            public Builder setAuthBlob(EsAuthBlob.AuthBlob.Builder builder) {
                copyOnWrite();
                ((AuthenticateSuccess) this.instance).setAuthBlob((EsAuthBlob.AuthBlob) builder.build());
                return this;
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
            public /* bridge */ /* synthetic */ vj1 m84mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m85mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m86mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum OptionalAccessTokenCase {
            ACCESSTOKEN(2),
            OPTIONALACCESSTOKEN_NOT_SET(0);

            private final int value;

            OptionalAccessTokenCase(int i) {
                this.value = i;
            }

            public static OptionalAccessTokenCase forNumber(int i) {
                if (i == 0) {
                    return OPTIONALACCESSTOKEN_NOT_SET;
                }
                if (i != 2) {
                    return null;
                }
                return ACCESSTOKEN;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OptionalAccessTokenCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            AuthenticateSuccess authenticateSuccess = new AuthenticateSuccess();
            DEFAULT_INSTANCE = authenticateSuccess;
            i.registerDefaultInstance(AuthenticateSuccess.class, authenticateSuccess);
        }

        private AuthenticateSuccess() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccessToken() {
            if (this.optionalAccessTokenCase_ == 2) {
                this.optionalAccessTokenCase_ = 0;
                this.optionalAccessToken_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAuthBlob() {
            this.authBlob_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOptionalAccessToken() {
            this.optionalAccessTokenCase_ = 0;
            this.optionalAccessToken_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsername() {
            this.username_ = getDefaultInstance().getUsername();
        }

        public static AuthenticateSuccess getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAccessToken(EsAccessToken.AccessToken accessToken) {
            accessToken.getClass();
            if (this.optionalAccessTokenCase_ != 2 || this.optionalAccessToken_ == EsAccessToken.AccessToken.getDefaultInstance()) {
                this.optionalAccessToken_ = accessToken;
            } else {
                this.optionalAccessToken_ = ((EsAccessToken.AccessToken.Builder) EsAccessToken.AccessToken.newBuilder((EsAccessToken.AccessToken) this.optionalAccessToken_).mergeFrom((i) accessToken)).buildPartial();
            }
            this.optionalAccessTokenCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAuthBlob(EsAuthBlob.AuthBlob authBlob) {
            authBlob.getClass();
            EsAuthBlob.AuthBlob authBlob2 = this.authBlob_;
            if (authBlob2 == null || authBlob2 == EsAuthBlob.AuthBlob.getDefaultInstance()) {
                this.authBlob_ = authBlob;
            } else {
                this.authBlob_ = (EsAuthBlob.AuthBlob) ((EsAuthBlob.AuthBlob.Builder) EsAuthBlob.AuthBlob.newBuilder(this.authBlob_).mergeFrom((i) authBlob)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AuthenticateSuccess parseDelimitedFrom(InputStream inputStream) {
            return (AuthenticateSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateSuccess parseFrom(ByteBuffer byteBuffer) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessToken(EsAccessToken.AccessToken accessToken) {
            accessToken.getClass();
            this.optionalAccessToken_ = accessToken;
            this.optionalAccessTokenCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthBlob(EsAuthBlob.AuthBlob authBlob) {
            authBlob.getClass();
            this.authBlob_ = authBlob;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsername(String str) {
            str.getClass();
            this.username_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUsernameBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.username_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0001\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0003\t", new Object[]{"optionalAccessToken_", "optionalAccessTokenCase_", "username_", EsAccessToken.AccessToken.class, "authBlob_"});
                case 3:
                    return new AuthenticateSuccess();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AuthenticateSuccess.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public EsAccessToken.AccessToken getAccessToken() {
            return this.optionalAccessTokenCase_ == 2 ? (EsAccessToken.AccessToken) this.optionalAccessToken_ : EsAccessToken.AccessToken.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public EsAuthBlob.AuthBlob getAuthBlob() {
            EsAuthBlob.AuthBlob authBlob = this.authBlob_;
            return authBlob == null ? EsAuthBlob.AuthBlob.getDefaultInstance() : authBlob;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public OptionalAccessTokenCase getOptionalAccessTokenCase() {
            return OptionalAccessTokenCase.forNumber(this.optionalAccessTokenCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public String getUsername() {
            return this.username_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public ByteString getUsernameBytes() {
            return ByteString.d(this.username_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public boolean hasAccessToken() {
            return this.optionalAccessTokenCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateResult.AuthenticateSuccessOrBuilder
        public boolean hasAuthBlob() {
            return this.authBlob_ != null;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AuthenticateSuccess authenticateSuccess) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(authenticateSuccess);
        }

        public static AuthenticateSuccess parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateSuccess parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AuthenticateSuccess parseFrom(ByteString byteString) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthenticateSuccess parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AuthenticateSuccess parseFrom(byte[] bArr) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthenticateSuccess parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AuthenticateSuccess parseFrom(InputStream inputStream) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateSuccess parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateSuccess parseFrom(au auVar) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AuthenticateSuccess parseFrom(au auVar, rl0 rl0Var) {
            return (AuthenticateSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AuthenticateSuccessOrBuilder extends yj1 {
        EsAccessToken.AccessToken getAccessToken();

        EsAuthBlob.AuthBlob getAuthBlob();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        AuthenticateSuccess.OptionalAccessTokenCase getOptionalAccessTokenCase();

        String getUsername();

        ByteString getUsernameBytes();

        boolean hasAccessToken();

        boolean hasAuthBlob();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsAuthenticateResult() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
