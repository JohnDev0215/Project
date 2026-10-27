package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsAuthenticateRequest {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest$1, reason: invalid class name */
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

    public static final class AuthenticateRequest extends i implements AuthenticateRequestOrBuilder {
        public static final int CREDENTIALS_FIELD_NUMBER = 1;
        private static final AuthenticateRequest DEFAULT_INSTANCE;
        public static final int INTERACTION_CALLBACK_URI_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int UI_LOCALE_FIELD_NUMBER = 3;
        private int bitField0_;
        private EsAuthenticateCredentials.AuthenticateCredentials credentials_;
        private String interactionCallbackUri_ = "";
        private String uiLocale_ = "";

        public static final class Builder extends h01 implements AuthenticateRequestOrBuilder {
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

            public Builder clearCredentials() {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).clearCredentials();
                return this;
            }

            public Builder clearInteractionCallbackUri() {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).clearInteractionCallbackUri();
                return this;
            }

            public Builder clearUiLocale() {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).clearUiLocale();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public EsAuthenticateCredentials.AuthenticateCredentials getCredentials() {
                return ((AuthenticateRequest) this.instance).getCredentials();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public String getInteractionCallbackUri() {
                return ((AuthenticateRequest) this.instance).getInteractionCallbackUri();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public ByteString getInteractionCallbackUriBytes() {
                return ((AuthenticateRequest) this.instance).getInteractionCallbackUriBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public String getUiLocale() {
                return ((AuthenticateRequest) this.instance).getUiLocale();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public ByteString getUiLocaleBytes() {
                return ((AuthenticateRequest) this.instance).getUiLocaleBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public boolean hasCredentials() {
                return ((AuthenticateRequest) this.instance).hasCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
            public boolean hasUiLocale() {
                return ((AuthenticateRequest) this.instance).hasUiLocale();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeCredentials(EsAuthenticateCredentials.AuthenticateCredentials authenticateCredentials) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).mergeCredentials(authenticateCredentials);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setCredentials(EsAuthenticateCredentials.AuthenticateCredentials authenticateCredentials) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).setCredentials(authenticateCredentials);
                return this;
            }

            public Builder setInteractionCallbackUri(String str) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).setInteractionCallbackUri(str);
                return this;
            }

            public Builder setInteractionCallbackUriBytes(ByteString byteString) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).setInteractionCallbackUriBytes(byteString);
                return this;
            }

            public Builder setUiLocale(String str) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).setUiLocale(str);
                return this;
            }

            public Builder setUiLocaleBytes(ByteString byteString) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).setUiLocaleBytes(byteString);
                return this;
            }

            private Builder() {
                super(AuthenticateRequest.DEFAULT_INSTANCE);
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

            public Builder setCredentials(EsAuthenticateCredentials.AuthenticateCredentials.Builder builder) {
                copyOnWrite();
                ((AuthenticateRequest) this.instance).setCredentials((EsAuthenticateCredentials.AuthenticateCredentials) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m71mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m72mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m73mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            AuthenticateRequest authenticateRequest = new AuthenticateRequest();
            DEFAULT_INSTANCE = authenticateRequest;
            i.registerDefaultInstance(AuthenticateRequest.class, authenticateRequest);
        }

        private AuthenticateRequest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCredentials() {
            this.credentials_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInteractionCallbackUri() {
            this.interactionCallbackUri_ = getDefaultInstance().getInteractionCallbackUri();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUiLocale() {
            this.bitField0_ &= -2;
            this.uiLocale_ = getDefaultInstance().getUiLocale();
        }

        public static AuthenticateRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeCredentials(EsAuthenticateCredentials.AuthenticateCredentials authenticateCredentials) {
            authenticateCredentials.getClass();
            EsAuthenticateCredentials.AuthenticateCredentials authenticateCredentials2 = this.credentials_;
            if (authenticateCredentials2 == null || authenticateCredentials2 == EsAuthenticateCredentials.AuthenticateCredentials.getDefaultInstance()) {
                this.credentials_ = authenticateCredentials;
            } else {
                this.credentials_ = (EsAuthenticateCredentials.AuthenticateCredentials) ((EsAuthenticateCredentials.AuthenticateCredentials.Builder) EsAuthenticateCredentials.AuthenticateCredentials.newBuilder(this.credentials_).mergeFrom((i) authenticateCredentials)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AuthenticateRequest parseDelimitedFrom(InputStream inputStream) {
            return (AuthenticateRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateRequest parseFrom(ByteBuffer byteBuffer) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCredentials(EsAuthenticateCredentials.AuthenticateCredentials authenticateCredentials) {
            authenticateCredentials.getClass();
            this.credentials_ = authenticateCredentials;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionCallbackUri(String str) {
            str.getClass();
            this.interactionCallbackUri_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionCallbackUriBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.interactionCallbackUri_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUiLocale(String str) {
            str.getClass();
            this.bitField0_ |= 1;
            this.uiLocale_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUiLocaleBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.uiLocale_ = byteString.s();
            this.bitField0_ |= 1;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002Ȉ\u0003ለ\u0000", new Object[]{"bitField0_", "credentials_", "interactionCallbackUri_", "uiLocale_"});
                case 3:
                    return new AuthenticateRequest();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AuthenticateRequest.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public EsAuthenticateCredentials.AuthenticateCredentials getCredentials() {
            EsAuthenticateCredentials.AuthenticateCredentials authenticateCredentials = this.credentials_;
            return authenticateCredentials == null ? EsAuthenticateCredentials.AuthenticateCredentials.getDefaultInstance() : authenticateCredentials;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public String getInteractionCallbackUri() {
            return this.interactionCallbackUri_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public ByteString getInteractionCallbackUriBytes() {
            return ByteString.d(this.interactionCallbackUri_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public String getUiLocale() {
            return this.uiLocale_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public ByteString getUiLocaleBytes() {
            return ByteString.d(this.uiLocale_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public boolean hasCredentials() {
            return this.credentials_ != null;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateRequest.AuthenticateRequestOrBuilder
        public boolean hasUiLocale() {
            return (this.bitField0_ & 1) != 0;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AuthenticateRequest authenticateRequest) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(authenticateRequest);
        }

        public static AuthenticateRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AuthenticateRequest parseFrom(ByteString byteString) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthenticateRequest parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AuthenticateRequest parseFrom(byte[] bArr) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthenticateRequest parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AuthenticateRequest parseFrom(InputStream inputStream) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateRequest parseFrom(au auVar) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AuthenticateRequest parseFrom(au auVar, rl0 rl0Var) {
            return (AuthenticateRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AuthenticateRequestOrBuilder extends yj1 {
        EsAuthenticateCredentials.AuthenticateCredentials getCredentials();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getInteractionCallbackUri();

        ByteString getInteractionCallbackUriBytes();

        String getUiLocale();

        ByteString getUiLocaleBytes();

        boolean hasCredentials();

        boolean hasUiLocale();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsAuthenticateRequest() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
