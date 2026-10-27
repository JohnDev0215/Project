package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken;
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
public final class EsAccessTokenClient {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient$1, reason: invalid class name */
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

    public static final class AccessTokenResponse extends i implements AccessTokenResponseOrBuilder {
        private static final AccessTokenResponse DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int TOKEN_FIELD_NUMBER = 1;
        private int responseCase_ = 0;
        private Object response_;

        public static final class Builder extends h01 implements AccessTokenResponseOrBuilder {
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

            public Builder clearError() {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).clearError();
                return this;
            }

            public Builder clearResponse() {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).clearResponse();
                return this;
            }

            public Builder clearToken() {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).clearToken();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
            public ErrorResponse getError() {
                return ((AccessTokenResponse) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
            public ResponseCase getResponseCase() {
                return ((AccessTokenResponse) this.instance).getResponseCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
            public EsAccessToken.AccessToken getToken() {
                return ((AccessTokenResponse) this.instance).getToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
            public boolean hasError() {
                return ((AccessTokenResponse) this.instance).hasError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
            public boolean hasToken() {
                return ((AccessTokenResponse) this.instance).hasToken();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeError(ErrorResponse errorResponse) {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).mergeError(errorResponse);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeToken(EsAccessToken.AccessToken accessToken) {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).mergeToken(accessToken);
                return this;
            }

            public Builder setError(ErrorResponse errorResponse) {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).setError(errorResponse);
                return this;
            }

            public Builder setToken(EsAccessToken.AccessToken accessToken) {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).setToken(accessToken);
                return this;
            }

            private Builder() {
                super(AccessTokenResponse.DEFAULT_INSTANCE);
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

            public Builder setError(ErrorResponse.Builder builder) {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).setError((ErrorResponse) builder.build());
                return this;
            }

            public Builder setToken(EsAccessToken.AccessToken.Builder builder) {
                copyOnWrite();
                ((AccessTokenResponse) this.instance).setToken((EsAccessToken.AccessToken) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m25mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m26mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m27mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ResponseCase {
            TOKEN(1),
            ERROR(2),
            RESPONSE_NOT_SET(0);

            private final int value;

            ResponseCase(int i) {
                this.value = i;
            }

            public static ResponseCase forNumber(int i) {
                if (i == 0) {
                    return RESPONSE_NOT_SET;
                }
                if (i == 1) {
                    return TOKEN;
                }
                if (i != 2) {
                    return null;
                }
                return ERROR;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ResponseCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
            DEFAULT_INSTANCE = accessTokenResponse;
            i.registerDefaultInstance(AccessTokenResponse.class, accessTokenResponse);
        }

        private AccessTokenResponse() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            if (this.responseCase_ == 2) {
                this.responseCase_ = 0;
                this.response_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearResponse() {
            this.responseCase_ = 0;
            this.response_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearToken() {
            if (this.responseCase_ == 1) {
                this.responseCase_ = 0;
                this.response_ = null;
            }
        }

        public static AccessTokenResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeError(ErrorResponse errorResponse) {
            errorResponse.getClass();
            if (this.responseCase_ != 2 || this.response_ == ErrorResponse.getDefaultInstance()) {
                this.response_ = errorResponse;
            } else {
                this.response_ = ((ErrorResponse.Builder) ErrorResponse.newBuilder((ErrorResponse) this.response_).mergeFrom((i) errorResponse)).buildPartial();
            }
            this.responseCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeToken(EsAccessToken.AccessToken accessToken) {
            accessToken.getClass();
            if (this.responseCase_ != 1 || this.response_ == EsAccessToken.AccessToken.getDefaultInstance()) {
                this.response_ = accessToken;
            } else {
                this.response_ = ((EsAccessToken.AccessToken.Builder) EsAccessToken.AccessToken.newBuilder((EsAccessToken.AccessToken) this.response_).mergeFrom((i) accessToken)).buildPartial();
            }
            this.responseCase_ = 1;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AccessTokenResponse parseDelimitedFrom(InputStream inputStream) {
            return (AccessTokenResponse) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AccessTokenResponse parseFrom(ByteBuffer byteBuffer) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(ErrorResponse errorResponse) {
            errorResponse.getClass();
            this.response_ = errorResponse;
            this.responseCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setToken(EsAccessToken.AccessToken accessToken) {
            accessToken.getClass();
            this.response_ = accessToken;
            this.responseCase_ = 1;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"response_", "responseCase_", EsAccessToken.AccessToken.class, ErrorResponse.class});
                case 3:
                    return new AccessTokenResponse();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AccessTokenResponse.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
        public ErrorResponse getError() {
            return this.responseCase_ == 2 ? (ErrorResponse) this.response_ : ErrorResponse.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
        public ResponseCase getResponseCase() {
            return ResponseCase.forNumber(this.responseCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
        public EsAccessToken.AccessToken getToken() {
            return this.responseCase_ == 1 ? (EsAccessToken.AccessToken) this.response_ : EsAccessToken.AccessToken.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
        public boolean hasError() {
            return this.responseCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.AccessTokenResponseOrBuilder
        public boolean hasToken() {
            return this.responseCase_ == 1;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AccessTokenResponse accessTokenResponse) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(accessTokenResponse);
        }

        public static AccessTokenResponse parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AccessTokenResponse) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AccessTokenResponse parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AccessTokenResponse parseFrom(ByteString byteString) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AccessTokenResponse parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AccessTokenResponse parseFrom(byte[] bArr) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AccessTokenResponse parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AccessTokenResponse parseFrom(InputStream inputStream) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AccessTokenResponse parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AccessTokenResponse parseFrom(au auVar) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AccessTokenResponse parseFrom(au auVar, rl0 rl0Var) {
            return (AccessTokenResponse) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AccessTokenResponseOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        ErrorResponse getError();

        AccessTokenResponse.ResponseCase getResponseCase();

        EsAccessToken.AccessToken getToken();

        boolean hasError();

        boolean hasToken();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class ErrorResponse extends i implements ErrorResponseOrBuilder {
        private static final ErrorResponse DEFAULT_INSTANCE;
        public static final int GENERIC_ERROR_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER = null;
        public static final int SPECIFIC_ERROR_FIELD_NUMBER = 2;
        private int errorCase_ = 0;
        private Object error_;

        public static final class Builder extends h01 implements ErrorResponseOrBuilder {
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

            public Builder clearError() {
                copyOnWrite();
                ((ErrorResponse) this.instance).clearError();
                return this;
            }

            public Builder clearGenericError() {
                copyOnWrite();
                ((ErrorResponse) this.instance).clearGenericError();
                return this;
            }

            public Builder clearSpecificError() {
                copyOnWrite();
                ((ErrorResponse) this.instance).clearSpecificError();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
            public ErrorCase getErrorCase() {
                return ((ErrorResponse) this.instance).getErrorCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
            public GenericError getGenericError() {
                return ((ErrorResponse) this.instance).getGenericError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
            public SpecificError getSpecificError() {
                return ((ErrorResponse) this.instance).getSpecificError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
            public int getSpecificErrorValue() {
                return ((ErrorResponse) this.instance).getSpecificErrorValue();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
            public boolean hasGenericError() {
                return ((ErrorResponse) this.instance).hasGenericError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
            public boolean hasSpecificError() {
                return ((ErrorResponse) this.instance).hasSpecificError();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeGenericError(GenericError genericError) {
                copyOnWrite();
                ((ErrorResponse) this.instance).mergeGenericError(genericError);
                return this;
            }

            public Builder setGenericError(GenericError genericError) {
                copyOnWrite();
                ((ErrorResponse) this.instance).setGenericError(genericError);
                return this;
            }

            public Builder setSpecificError(SpecificError specificError) {
                copyOnWrite();
                ((ErrorResponse) this.instance).setSpecificError(specificError);
                return this;
            }

            public Builder setSpecificErrorValue(int i) {
                copyOnWrite();
                ((ErrorResponse) this.instance).setSpecificErrorValue(i);
                return this;
            }

            private Builder() {
                super(ErrorResponse.DEFAULT_INSTANCE);
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

            public Builder setGenericError(GenericError.Builder builder) {
                copyOnWrite();
                ((ErrorResponse) this.instance).setGenericError((GenericError) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m28mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m29mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m30mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ErrorCase {
            GENERIC_ERROR(1),
            SPECIFIC_ERROR(2),
            ERROR_NOT_SET(0);

            private final int value;

            ErrorCase(int i) {
                this.value = i;
            }

            public static ErrorCase forNumber(int i) {
                if (i == 0) {
                    return ERROR_NOT_SET;
                }
                if (i == 1) {
                    return GENERIC_ERROR;
                }
                if (i != 2) {
                    return null;
                }
                return SPECIFIC_ERROR;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ErrorCase valueOf(int i) {
                return forNumber(i);
            }
        }

        public static final class GenericError extends i implements GenericErrorOrBuilder {
            private static final GenericError DEFAULT_INSTANCE;
            public static final int ERROR_CODE_FIELD_NUMBER = 1;
            public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
            private static volatile ds1 PARSER;
            private int errorCode_;
            private String errorMessage_ = "";

            public static final class Builder extends h01 implements GenericErrorOrBuilder {
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

                public Builder clearErrorCode() {
                    copyOnWrite();
                    ((GenericError) this.instance).clearErrorCode();
                    return this;
                }

                public Builder clearErrorMessage() {
                    copyOnWrite();
                    ((GenericError) this.instance).clearErrorMessage();
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

                @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.GenericErrorOrBuilder
                public int getErrorCode() {
                    return ((GenericError) this.instance).getErrorCode();
                }

                @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.GenericErrorOrBuilder
                public String getErrorMessage() {
                    return ((GenericError) this.instance).getErrorMessage();
                }

                @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.GenericErrorOrBuilder
                public ByteString getErrorMessageBytes() {
                    return ((GenericError) this.instance).getErrorMessageBytes();
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                    return super.internalMergeFrom((i) aVar);
                }

                @Override // p.h01, p.r1
                public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public Builder setErrorCode(int i) {
                    copyOnWrite();
                    ((GenericError) this.instance).setErrorCode(i);
                    return this;
                }

                public Builder setErrorMessage(String str) {
                    copyOnWrite();
                    ((GenericError) this.instance).setErrorMessage(str);
                    return this;
                }

                public Builder setErrorMessageBytes(ByteString byteString) {
                    copyOnWrite();
                    ((GenericError) this.instance).setErrorMessageBytes(byteString);
                    return this;
                }

                private Builder() {
                    super(GenericError.DEFAULT_INSTANCE);
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
                public /* bridge */ /* synthetic */ vj1 m31mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                    return super.mergeFrom(wj1Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                    return super.mergeFrom(bArr);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m32mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m33mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                    return super.mergeFrom(bArr, i, i2, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                    return super.mergeFrom(bArr, rl0Var);
                }
            }

            static {
                GenericError genericError = new GenericError();
                DEFAULT_INSTANCE = genericError;
                i.registerDefaultInstance(GenericError.class, genericError);
            }

            private GenericError() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearErrorCode() {
                this.errorCode_ = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clearErrorMessage() {
                this.errorMessage_ = getDefaultInstance().getErrorMessage();
            }

            public static GenericError getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Builder newBuilder() {
                return (Builder) DEFAULT_INSTANCE.createBuilder();
            }

            public static GenericError parseDelimitedFrom(InputStream inputStream) {
                return (GenericError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static GenericError parseFrom(ByteBuffer byteBuffer) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
            }

            public static ds1 parser() {
                return DEFAULT_INSTANCE.getParserForType();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setErrorCode(int i) {
                this.errorCode_ = i;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setErrorMessage(String str) {
                str.getClass();
                this.errorMessage_ = str;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void setErrorMessageBytes(ByteString byteString) {
                a.checkByteStringIsUtf8(byteString);
                this.errorMessage_ = byteString.s();
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
                        return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0004\u0002Ȉ", new Object[]{"errorCode_", "errorMessage_"});
                    case 3:
                        return new GenericError();
                    case 4:
                        return new Builder(anonymousClass1);
                    case 5:
                        return DEFAULT_INSTANCE;
                    case 6:
                        ds1 i01Var = PARSER;
                        if (i01Var == null) {
                            synchronized (GenericError.class) {
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.GenericErrorOrBuilder
            public int getErrorCode() {
                return this.errorCode_;
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.GenericErrorOrBuilder
            public String getErrorMessage() {
                return this.errorMessage_;
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.GenericErrorOrBuilder
            public ByteString getErrorMessageBytes() {
                return ByteString.d(this.errorMessage_);
            }

            @Override // com.google.protobuf.i, p.wj1
            public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
                return super.newBuilderForType();
            }

            public /* bridge */ /* synthetic */ vj1 toBuilder() {
                return super.toBuilder();
            }

            public static Builder newBuilder(GenericError genericError) {
                return (Builder) DEFAULT_INSTANCE.createBuilder(genericError);
            }

            public static GenericError parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
                return (GenericError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static GenericError parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
            }

            public static GenericError parseFrom(ByteString byteString) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, byteString);
            }

            public static GenericError parseFrom(ByteString byteString, rl0 rl0Var) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
            }

            public static GenericError parseFrom(byte[] bArr) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, bArr);
            }

            public static GenericError parseFrom(byte[] bArr, rl0 rl0Var) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
            }

            public static GenericError parseFrom(InputStream inputStream) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, inputStream);
            }

            public static GenericError parseFrom(InputStream inputStream, rl0 rl0Var) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
            }

            public static GenericError parseFrom(au auVar) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, auVar);
            }

            public static GenericError parseFrom(au auVar, rl0 rl0Var) {
                return (GenericError) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
            }
        }

        public interface GenericErrorOrBuilder extends yj1 {
            @Override // p.yj1
            /* synthetic */ wj1 getDefaultInstanceForType();

            int getErrorCode();

            String getErrorMessage();

            ByteString getErrorMessageBytes();

            @Override // p.yj1
            /* synthetic */ boolean isInitialized();
        }

        public enum SpecificError implements n51 {
            INVALID_CREDENTIALS(0),
            HTTP_ERROR(1),
            OFFLINE(2),
            UNRECOGNIZED(-1);

            public static final int HTTP_ERROR_VALUE = 1;
            public static final int INVALID_CREDENTIALS_VALUE = 0;
            public static final int OFFLINE_VALUE = 2;
            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponse.SpecificError.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public SpecificError m34findValueByNumber(int i) {
                    return SpecificError.forNumber(i);
                }
            };
            private final int value;

            public static final class SpecificErrorVerifier implements p51 {
                static final p51 INSTANCE = new SpecificErrorVerifier();

                private SpecificErrorVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return SpecificError.forNumber(i) != null;
                }
            }

            SpecificError(int i) {
                this.value = i;
            }

            public static SpecificError forNumber(int i) {
                if (i == 0) {
                    return INVALID_CREDENTIALS;
                }
                if (i == 1) {
                    return HTTP_ERROR;
                }
                if (i != 2) {
                    return null;
                }
                return OFFLINE;
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return SpecificErrorVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static SpecificError valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            ErrorResponse errorResponse = new ErrorResponse();
            DEFAULT_INSTANCE = errorResponse;
            i.registerDefaultInstance(ErrorResponse.class, errorResponse);
        }

        private ErrorResponse() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            this.errorCase_ = 0;
            this.error_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGenericError() {
            if (this.errorCase_ == 1) {
                this.errorCase_ = 0;
                this.error_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSpecificError() {
            if (this.errorCase_ == 2) {
                this.errorCase_ = 0;
                this.error_ = null;
            }
        }

        public static ErrorResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGenericError(GenericError genericError) {
            genericError.getClass();
            if (this.errorCase_ != 1 || this.error_ == GenericError.getDefaultInstance()) {
                this.error_ = genericError;
            } else {
                this.error_ = ((GenericError.Builder) GenericError.newBuilder((GenericError) this.error_).mergeFrom((i) genericError)).buildPartial();
            }
            this.errorCase_ = 1;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static ErrorResponse parseDelimitedFrom(InputStream inputStream) {
            return (ErrorResponse) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ErrorResponse parseFrom(ByteBuffer byteBuffer) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGenericError(GenericError genericError) {
            genericError.getClass();
            this.error_ = genericError;
            this.errorCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpecificError(SpecificError specificError) {
            this.error_ = Integer.valueOf(specificError.getNumber());
            this.errorCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSpecificErrorValue(int i) {
            this.errorCase_ = 2;
            this.error_ = Integer.valueOf(i);
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002?\u0000", new Object[]{"error_", "errorCase_", GenericError.class});
                case 3:
                    return new ErrorResponse();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (ErrorResponse.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
        public ErrorCase getErrorCase() {
            return ErrorCase.forNumber(this.errorCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
        public GenericError getGenericError() {
            return this.errorCase_ == 1 ? (GenericError) this.error_ : GenericError.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
        public SpecificError getSpecificError() {
            if (this.errorCase_ != 2) {
                return SpecificError.INVALID_CREDENTIALS;
            }
            SpecificError specificErrorForNumber = SpecificError.forNumber(((Integer) this.error_).intValue());
            return specificErrorForNumber == null ? SpecificError.UNRECOGNIZED : specificErrorForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
        public int getSpecificErrorValue() {
            if (this.errorCase_ == 2) {
                return ((Integer) this.error_).intValue();
            }
            return 0;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
        public boolean hasGenericError() {
            return this.errorCase_ == 1;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.ErrorResponseOrBuilder
        public boolean hasSpecificError() {
            return this.errorCase_ == 2;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(ErrorResponse errorResponse) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(errorResponse);
        }

        public static ErrorResponse parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (ErrorResponse) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static ErrorResponse parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static ErrorResponse parseFrom(ByteString byteString) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ErrorResponse parseFrom(ByteString byteString, rl0 rl0Var) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static ErrorResponse parseFrom(byte[] bArr) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ErrorResponse parseFrom(byte[] bArr, rl0 rl0Var) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static ErrorResponse parseFrom(InputStream inputStream) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ErrorResponse parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static ErrorResponse parseFrom(au auVar) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static ErrorResponse parseFrom(au auVar, rl0 rl0Var) {
            return (ErrorResponse) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface ErrorResponseOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        ErrorResponse.ErrorCase getErrorCase();

        ErrorResponse.GenericError getGenericError();

        ErrorResponse.SpecificError getSpecificError();

        int getSpecificErrorValue();

        boolean hasGenericError();

        boolean hasSpecificError();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class GetTokenRequest extends i implements GetTokenRequestOrBuilder {
        private static final GetTokenRequest DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int RENEW_FIELD_NUMBER = 1;
        private boolean renew_;

        public static final class Builder extends h01 implements GetTokenRequestOrBuilder {
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

            public Builder clearRenew() {
                copyOnWrite();
                ((GetTokenRequest) this.instance).clearRenew();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.GetTokenRequestOrBuilder
            public boolean getRenew() {
                return ((GetTokenRequest) this.instance).getRenew();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setRenew(boolean z) {
                copyOnWrite();
                ((GetTokenRequest) this.instance).setRenew(z);
                return this;
            }

            private Builder() {
                super(GetTokenRequest.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m35mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m36mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m37mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            GetTokenRequest getTokenRequest = new GetTokenRequest();
            DEFAULT_INSTANCE = getTokenRequest;
            i.registerDefaultInstance(GetTokenRequest.class, getTokenRequest);
        }

        private GetTokenRequest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRenew() {
            this.renew_ = false;
        }

        public static GetTokenRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static GetTokenRequest parseDelimitedFrom(InputStream inputStream) {
            return (GetTokenRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetTokenRequest parseFrom(ByteBuffer byteBuffer) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRenew(boolean z) {
            this.renew_ = z;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"renew_"});
                case 3:
                    return new GetTokenRequest();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (GetTokenRequest.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessTokenClient.GetTokenRequestOrBuilder
        public boolean getRenew() {
            return this.renew_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(GetTokenRequest getTokenRequest) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(getTokenRequest);
        }

        public static GetTokenRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (GetTokenRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static GetTokenRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static GetTokenRequest parseFrom(ByteString byteString) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GetTokenRequest parseFrom(ByteString byteString, rl0 rl0Var) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static GetTokenRequest parseFrom(byte[] bArr) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GetTokenRequest parseFrom(byte[] bArr, rl0 rl0Var) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static GetTokenRequest parseFrom(InputStream inputStream) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GetTokenRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static GetTokenRequest parseFrom(au auVar) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static GetTokenRequest parseFrom(au auVar, rl0 rl0Var) {
            return (GetTokenRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface GetTokenRequestOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        boolean getRenew();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsAccessTokenClient() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
