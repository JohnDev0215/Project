package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials;
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
public final class EsAuthenticateCredentials {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials$1, reason: invalid class name */
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

    public static final class AppleSignInCredentials extends i implements AppleSignInCredentialsOrBuilder {
        public static final int AUTH_CODE_FIELD_NUMBER = 1;
        public static final int BUNDLE_ID_FIELD_NUMBER = 3;
        private static final AppleSignInCredentials DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int REDIRECT_URI_FIELD_NUMBER = 2;
        private String authCode_ = "";
        private String redirectUri_ = "";
        private String bundleId_ = "";

        public static final class Builder extends h01 implements AppleSignInCredentialsOrBuilder {
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

            public Builder clearAuthCode() {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).clearAuthCode();
                return this;
            }

            public Builder clearBundleId() {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).clearBundleId();
                return this;
            }

            public Builder clearRedirectUri() {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).clearRedirectUri();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
            public String getAuthCode() {
                return ((AppleSignInCredentials) this.instance).getAuthCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
            public ByteString getAuthCodeBytes() {
                return ((AppleSignInCredentials) this.instance).getAuthCodeBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
            public String getBundleId() {
                return ((AppleSignInCredentials) this.instance).getBundleId();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
            public ByteString getBundleIdBytes() {
                return ((AppleSignInCredentials) this.instance).getBundleIdBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
            public String getRedirectUri() {
                return ((AppleSignInCredentials) this.instance).getRedirectUri();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
            public ByteString getRedirectUriBytes() {
                return ((AppleSignInCredentials) this.instance).getRedirectUriBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAuthCode(String str) {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).setAuthCode(str);
                return this;
            }

            public Builder setAuthCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).setAuthCodeBytes(byteString);
                return this;
            }

            public Builder setBundleId(String str) {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).setBundleId(str);
                return this;
            }

            public Builder setBundleIdBytes(ByteString byteString) {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).setBundleIdBytes(byteString);
                return this;
            }

            public Builder setRedirectUri(String str) {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).setRedirectUri(str);
                return this;
            }

            public Builder setRedirectUriBytes(ByteString byteString) {
                copyOnWrite();
                ((AppleSignInCredentials) this.instance).setRedirectUriBytes(byteString);
                return this;
            }

            private Builder() {
                super(AppleSignInCredentials.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m41mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m42mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m43mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            AppleSignInCredentials appleSignInCredentials = new AppleSignInCredentials();
            DEFAULT_INSTANCE = appleSignInCredentials;
            i.registerDefaultInstance(AppleSignInCredentials.class, appleSignInCredentials);
        }

        private AppleSignInCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAuthCode() {
            this.authCode_ = getDefaultInstance().getAuthCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBundleId() {
            this.bundleId_ = getDefaultInstance().getBundleId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRedirectUri() {
            this.redirectUri_ = getDefaultInstance().getRedirectUri();
        }

        public static AppleSignInCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AppleSignInCredentials parseDelimitedFrom(InputStream inputStream) {
            return (AppleSignInCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppleSignInCredentials parseFrom(ByteBuffer byteBuffer) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthCode(String str) {
            str.getClass();
            this.authCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthCodeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.authCode_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBundleId(String str) {
            str.getClass();
            this.bundleId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBundleIdBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.bundleId_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRedirectUri(String str) {
            str.getClass();
            this.redirectUri_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRedirectUriBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.redirectUri_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"authCode_", "redirectUri_", "bundleId_"});
                case 3:
                    return new AppleSignInCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AppleSignInCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
        public String getAuthCode() {
            return this.authCode_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
        public ByteString getAuthCodeBytes() {
            return ByteString.d(this.authCode_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
        public String getBundleId() {
            return this.bundleId_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
        public ByteString getBundleIdBytes() {
            return ByteString.d(this.bundleId_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
        public String getRedirectUri() {
            return this.redirectUri_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AppleSignInCredentialsOrBuilder
        public ByteString getRedirectUriBytes() {
            return ByteString.d(this.redirectUri_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AppleSignInCredentials appleSignInCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(appleSignInCredentials);
        }

        public static AppleSignInCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AppleSignInCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AppleSignInCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AppleSignInCredentials parseFrom(ByteString byteString) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AppleSignInCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AppleSignInCredentials parseFrom(byte[] bArr) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AppleSignInCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AppleSignInCredentials parseFrom(InputStream inputStream) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppleSignInCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AppleSignInCredentials parseFrom(au auVar) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AppleSignInCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (AppleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AppleSignInCredentialsOrBuilder extends yj1 {
        String getAuthCode();

        ByteString getAuthCodeBytes();

        String getBundleId();

        ByteString getBundleIdBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getRedirectUri();

        ByteString getRedirectUriBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class AuthenticateCredentials extends i implements AuthenticateCredentialsOrBuilder {
        public static final int APPLESIGNINCREDENTIALS_FIELD_NUMBER = 8;
        private static final AuthenticateCredentials DEFAULT_INSTANCE;
        public static final int FACEBOOKACCESSTOKEN_FIELD_NUMBER = 9;
        public static final int GOOGLESIGNINCREDENTIALS_FIELD_NUMBER = 7;
        public static final int LEGACYV3PASSWORD_FIELD_NUMBER = 10;
        public static final int ONETIMETOKEN_FIELD_NUMBER = 2;
        public static final int PARENTCHILDCREDENTIALS_FIELD_NUMBER = 6;
        private static volatile ds1 PARSER = null;
        public static final int PASSWORD_FIELD_NUMBER = 1;
        public static final int PHONENUMBER_FIELD_NUMBER = 3;
        public static final int SAMSUNGSIGNINCREDENTIALS_FIELD_NUMBER = 5;
        public static final int UNENCRYPTEDSTOREDCREDENTIALS_FIELD_NUMBER = 4;
        private int credentialsCase_ = 0;
        private Object credentials_;

        public static final class Builder extends h01 implements AuthenticateCredentialsOrBuilder {
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

            public Builder clearAppleSignInCredentials() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearAppleSignInCredentials();
                return this;
            }

            public Builder clearCredentials() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearCredentials();
                return this;
            }

            public Builder clearFacebookAccessToken() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearFacebookAccessToken();
                return this;
            }

            public Builder clearGoogleSignInCredentials() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearGoogleSignInCredentials();
                return this;
            }

            public Builder clearLegacyV3Password() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearLegacyV3Password();
                return this;
            }

            public Builder clearOneTimeToken() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearOneTimeToken();
                return this;
            }

            public Builder clearParentChildCredentials() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearParentChildCredentials();
                return this;
            }

            public Builder clearPassword() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearPassword();
                return this;
            }

            public Builder clearPhoneNumber() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearPhoneNumber();
                return this;
            }

            public Builder clearSamsungSignInCredentials() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearSamsungSignInCredentials();
                return this;
            }

            public Builder clearUnencryptedStoredCredentials() {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).clearUnencryptedStoredCredentials();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public AppleSignInCredentials getAppleSignInCredentials() {
                return ((AuthenticateCredentials) this.instance).getAppleSignInCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public CredentialsCase getCredentialsCase() {
                return ((AuthenticateCredentials) this.instance).getCredentialsCase();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public FacebookAccessToken getFacebookAccessToken() {
                return ((AuthenticateCredentials) this.instance).getFacebookAccessToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public GoogleSignInCredentials getGoogleSignInCredentials() {
                return ((AuthenticateCredentials) this.instance).getGoogleSignInCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public LegacyV3Password getLegacyV3Password() {
                return ((AuthenticateCredentials) this.instance).getLegacyV3Password();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public OneTimeToken getOneTimeToken() {
                return ((AuthenticateCredentials) this.instance).getOneTimeToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public ParentChildCredentials getParentChildCredentials() {
                return ((AuthenticateCredentials) this.instance).getParentChildCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public Password getPassword() {
                return ((AuthenticateCredentials) this.instance).getPassword();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public PhoneNumber getPhoneNumber() {
                return ((AuthenticateCredentials) this.instance).getPhoneNumber();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public SamsungSignInCredentials getSamsungSignInCredentials() {
                return ((AuthenticateCredentials) this.instance).getSamsungSignInCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public EsUnencryptedStoredCredentials.UnencryptedStoredCredentials getUnencryptedStoredCredentials() {
                return ((AuthenticateCredentials) this.instance).getUnencryptedStoredCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasAppleSignInCredentials() {
                return ((AuthenticateCredentials) this.instance).hasAppleSignInCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasFacebookAccessToken() {
                return ((AuthenticateCredentials) this.instance).hasFacebookAccessToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasGoogleSignInCredentials() {
                return ((AuthenticateCredentials) this.instance).hasGoogleSignInCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasLegacyV3Password() {
                return ((AuthenticateCredentials) this.instance).hasLegacyV3Password();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasOneTimeToken() {
                return ((AuthenticateCredentials) this.instance).hasOneTimeToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasParentChildCredentials() {
                return ((AuthenticateCredentials) this.instance).hasParentChildCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasPassword() {
                return ((AuthenticateCredentials) this.instance).hasPassword();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasPhoneNumber() {
                return ((AuthenticateCredentials) this.instance).hasPhoneNumber();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasSamsungSignInCredentials() {
                return ((AuthenticateCredentials) this.instance).hasSamsungSignInCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
            public boolean hasUnencryptedStoredCredentials() {
                return ((AuthenticateCredentials) this.instance).hasUnencryptedStoredCredentials();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeAppleSignInCredentials(AppleSignInCredentials appleSignInCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeAppleSignInCredentials(appleSignInCredentials);
                return this;
            }

            public Builder mergeFacebookAccessToken(FacebookAccessToken facebookAccessToken) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeFacebookAccessToken(facebookAccessToken);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeGoogleSignInCredentials(GoogleSignInCredentials googleSignInCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeGoogleSignInCredentials(googleSignInCredentials);
                return this;
            }

            public Builder mergeLegacyV3Password(LegacyV3Password legacyV3Password) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeLegacyV3Password(legacyV3Password);
                return this;
            }

            public Builder mergeOneTimeToken(OneTimeToken oneTimeToken) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeOneTimeToken(oneTimeToken);
                return this;
            }

            public Builder mergeParentChildCredentials(ParentChildCredentials parentChildCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeParentChildCredentials(parentChildCredentials);
                return this;
            }

            public Builder mergePassword(Password password) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergePassword(password);
                return this;
            }

            public Builder mergePhoneNumber(PhoneNumber phoneNumber) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergePhoneNumber(phoneNumber);
                return this;
            }

            public Builder mergeSamsungSignInCredentials(SamsungSignInCredentials samsungSignInCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeSamsungSignInCredentials(samsungSignInCredentials);
                return this;
            }

            public Builder mergeUnencryptedStoredCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).mergeUnencryptedStoredCredentials(unencryptedStoredCredentials);
                return this;
            }

            public Builder setAppleSignInCredentials(AppleSignInCredentials appleSignInCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setAppleSignInCredentials(appleSignInCredentials);
                return this;
            }

            public Builder setFacebookAccessToken(FacebookAccessToken facebookAccessToken) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setFacebookAccessToken(facebookAccessToken);
                return this;
            }

            public Builder setGoogleSignInCredentials(GoogleSignInCredentials googleSignInCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setGoogleSignInCredentials(googleSignInCredentials);
                return this;
            }

            public Builder setLegacyV3Password(LegacyV3Password legacyV3Password) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setLegacyV3Password(legacyV3Password);
                return this;
            }

            public Builder setOneTimeToken(OneTimeToken oneTimeToken) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setOneTimeToken(oneTimeToken);
                return this;
            }

            public Builder setParentChildCredentials(ParentChildCredentials parentChildCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setParentChildCredentials(parentChildCredentials);
                return this;
            }

            public Builder setPassword(Password password) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setPassword(password);
                return this;
            }

            public Builder setPhoneNumber(PhoneNumber phoneNumber) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setPhoneNumber(phoneNumber);
                return this;
            }

            public Builder setSamsungSignInCredentials(SamsungSignInCredentials samsungSignInCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setSamsungSignInCredentials(samsungSignInCredentials);
                return this;
            }

            public Builder setUnencryptedStoredCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setUnencryptedStoredCredentials(unencryptedStoredCredentials);
                return this;
            }

            private Builder() {
                super(AuthenticateCredentials.DEFAULT_INSTANCE);
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

            public Builder setAppleSignInCredentials(AppleSignInCredentials.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setAppleSignInCredentials((AppleSignInCredentials) builder.build());
                return this;
            }

            public Builder setFacebookAccessToken(FacebookAccessToken.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setFacebookAccessToken((FacebookAccessToken) builder.build());
                return this;
            }

            public Builder setGoogleSignInCredentials(GoogleSignInCredentials.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setGoogleSignInCredentials((GoogleSignInCredentials) builder.build());
                return this;
            }

            public Builder setLegacyV3Password(LegacyV3Password.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setLegacyV3Password((LegacyV3Password) builder.build());
                return this;
            }

            public Builder setOneTimeToken(OneTimeToken.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setOneTimeToken((OneTimeToken) builder.build());
                return this;
            }

            public Builder setParentChildCredentials(ParentChildCredentials.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setParentChildCredentials((ParentChildCredentials) builder.build());
                return this;
            }

            public Builder setPassword(Password.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setPassword((Password) builder.build());
                return this;
            }

            public Builder setPhoneNumber(PhoneNumber.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setPhoneNumber((PhoneNumber) builder.build());
                return this;
            }

            public Builder setSamsungSignInCredentials(SamsungSignInCredentials.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setSamsungSignInCredentials((SamsungSignInCredentials) builder.build());
                return this;
            }

            public Builder setUnencryptedStoredCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.Builder builder) {
                copyOnWrite();
                ((AuthenticateCredentials) this.instance).setUnencryptedStoredCredentials((EsUnencryptedStoredCredentials.UnencryptedStoredCredentials) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m44mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m45mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m46mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum CredentialsCase {
            PASSWORD(1),
            ONETIMETOKEN(2),
            PHONENUMBER(3),
            UNENCRYPTEDSTOREDCREDENTIALS(4),
            SAMSUNGSIGNINCREDENTIALS(5),
            PARENTCHILDCREDENTIALS(6),
            GOOGLESIGNINCREDENTIALS(7),
            APPLESIGNINCREDENTIALS(8),
            FACEBOOKACCESSTOKEN(9),
            LEGACYV3PASSWORD(10),
            CREDENTIALS_NOT_SET(0);

            private final int value;

            CredentialsCase(int i) {
                this.value = i;
            }

            public static CredentialsCase forNumber(int i) {
                switch (i) {
                    case 0:
                        return CREDENTIALS_NOT_SET;
                    case 1:
                        return PASSWORD;
                    case 2:
                        return ONETIMETOKEN;
                    case 3:
                        return PHONENUMBER;
                    case 4:
                        return UNENCRYPTEDSTOREDCREDENTIALS;
                    case 5:
                        return SAMSUNGSIGNINCREDENTIALS;
                    case 6:
                        return PARENTCHILDCREDENTIALS;
                    case 7:
                        return GOOGLESIGNINCREDENTIALS;
                    case 8:
                        return APPLESIGNINCREDENTIALS;
                    case 9:
                        return FACEBOOKACCESSTOKEN;
                    case 10:
                        return LEGACYV3PASSWORD;
                    default:
                        return null;
                }
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static CredentialsCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            AuthenticateCredentials authenticateCredentials = new AuthenticateCredentials();
            DEFAULT_INSTANCE = authenticateCredentials;
            i.registerDefaultInstance(AuthenticateCredentials.class, authenticateCredentials);
        }

        private AuthenticateCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAppleSignInCredentials() {
            if (this.credentialsCase_ == 8) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCredentials() {
            this.credentialsCase_ = 0;
            this.credentials_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFacebookAccessToken() {
            if (this.credentialsCase_ == 9) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGoogleSignInCredentials() {
            if (this.credentialsCase_ == 7) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearLegacyV3Password() {
            if (this.credentialsCase_ == 10) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOneTimeToken() {
            if (this.credentialsCase_ == 2) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearParentChildCredentials() {
            if (this.credentialsCase_ == 6) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPassword() {
            if (this.credentialsCase_ == 1) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPhoneNumber() {
            if (this.credentialsCase_ == 3) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSamsungSignInCredentials() {
            if (this.credentialsCase_ == 5) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnencryptedStoredCredentials() {
            if (this.credentialsCase_ == 4) {
                this.credentialsCase_ = 0;
                this.credentials_ = null;
            }
        }

        public static AuthenticateCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeAppleSignInCredentials(AppleSignInCredentials appleSignInCredentials) {
            appleSignInCredentials.getClass();
            if (this.credentialsCase_ != 8 || this.credentials_ == AppleSignInCredentials.getDefaultInstance()) {
                this.credentials_ = appleSignInCredentials;
            } else {
                this.credentials_ = ((AppleSignInCredentials.Builder) AppleSignInCredentials.newBuilder((AppleSignInCredentials) this.credentials_).mergeFrom((i) appleSignInCredentials)).buildPartial();
            }
            this.credentialsCase_ = 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeFacebookAccessToken(FacebookAccessToken facebookAccessToken) {
            facebookAccessToken.getClass();
            if (this.credentialsCase_ != 9 || this.credentials_ == FacebookAccessToken.getDefaultInstance()) {
                this.credentials_ = facebookAccessToken;
            } else {
                this.credentials_ = ((FacebookAccessToken.Builder) FacebookAccessToken.newBuilder((FacebookAccessToken) this.credentials_).mergeFrom((i) facebookAccessToken)).buildPartial();
            }
            this.credentialsCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGoogleSignInCredentials(GoogleSignInCredentials googleSignInCredentials) {
            googleSignInCredentials.getClass();
            if (this.credentialsCase_ != 7 || this.credentials_ == GoogleSignInCredentials.getDefaultInstance()) {
                this.credentials_ = googleSignInCredentials;
            } else {
                this.credentials_ = ((GoogleSignInCredentials.Builder) GoogleSignInCredentials.newBuilder((GoogleSignInCredentials) this.credentials_).mergeFrom((i) googleSignInCredentials)).buildPartial();
            }
            this.credentialsCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeLegacyV3Password(LegacyV3Password legacyV3Password) {
            legacyV3Password.getClass();
            if (this.credentialsCase_ != 10 || this.credentials_ == LegacyV3Password.getDefaultInstance()) {
                this.credentials_ = legacyV3Password;
            } else {
                this.credentials_ = ((LegacyV3Password.Builder) LegacyV3Password.newBuilder((LegacyV3Password) this.credentials_).mergeFrom((i) legacyV3Password)).buildPartial();
            }
            this.credentialsCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeOneTimeToken(OneTimeToken oneTimeToken) {
            oneTimeToken.getClass();
            if (this.credentialsCase_ != 2 || this.credentials_ == OneTimeToken.getDefaultInstance()) {
                this.credentials_ = oneTimeToken;
            } else {
                this.credentials_ = ((OneTimeToken.Builder) OneTimeToken.newBuilder((OneTimeToken) this.credentials_).mergeFrom((i) oneTimeToken)).buildPartial();
            }
            this.credentialsCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeParentChildCredentials(ParentChildCredentials parentChildCredentials) {
            parentChildCredentials.getClass();
            if (this.credentialsCase_ != 6 || this.credentials_ == ParentChildCredentials.getDefaultInstance()) {
                this.credentials_ = parentChildCredentials;
            } else {
                this.credentials_ = ((ParentChildCredentials.Builder) ParentChildCredentials.newBuilder((ParentChildCredentials) this.credentials_).mergeFrom((i) parentChildCredentials)).buildPartial();
            }
            this.credentialsCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePassword(Password password) {
            password.getClass();
            if (this.credentialsCase_ != 1 || this.credentials_ == Password.getDefaultInstance()) {
                this.credentials_ = password;
            } else {
                this.credentials_ = ((Password.Builder) Password.newBuilder((Password) this.credentials_).mergeFrom((i) password)).buildPartial();
            }
            this.credentialsCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergePhoneNumber(PhoneNumber phoneNumber) {
            phoneNumber.getClass();
            if (this.credentialsCase_ != 3 || this.credentials_ == PhoneNumber.getDefaultInstance()) {
                this.credentials_ = phoneNumber;
            } else {
                this.credentials_ = ((PhoneNumber.Builder) PhoneNumber.newBuilder((PhoneNumber) this.credentials_).mergeFrom((i) phoneNumber)).buildPartial();
            }
            this.credentialsCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSamsungSignInCredentials(SamsungSignInCredentials samsungSignInCredentials) {
            samsungSignInCredentials.getClass();
            if (this.credentialsCase_ != 5 || this.credentials_ == SamsungSignInCredentials.getDefaultInstance()) {
                this.credentials_ = samsungSignInCredentials;
            } else {
                this.credentials_ = ((SamsungSignInCredentials.Builder) SamsungSignInCredentials.newBuilder((SamsungSignInCredentials) this.credentials_).mergeFrom((i) samsungSignInCredentials)).buildPartial();
            }
            this.credentialsCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnencryptedStoredCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
            unencryptedStoredCredentials.getClass();
            if (this.credentialsCase_ != 4 || this.credentials_ == EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.getDefaultInstance()) {
                this.credentials_ = unencryptedStoredCredentials;
            } else {
                this.credentials_ = ((EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.Builder) EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.newBuilder((EsUnencryptedStoredCredentials.UnencryptedStoredCredentials) this.credentials_).mergeFrom((i) unencryptedStoredCredentials)).buildPartial();
            }
            this.credentialsCase_ = 4;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AuthenticateCredentials parseDelimitedFrom(InputStream inputStream) {
            return (AuthenticateCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateCredentials parseFrom(ByteBuffer byteBuffer) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAppleSignInCredentials(AppleSignInCredentials appleSignInCredentials) {
            appleSignInCredentials.getClass();
            this.credentials_ = appleSignInCredentials;
            this.credentialsCase_ = 8;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFacebookAccessToken(FacebookAccessToken facebookAccessToken) {
            facebookAccessToken.getClass();
            this.credentials_ = facebookAccessToken;
            this.credentialsCase_ = 9;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGoogleSignInCredentials(GoogleSignInCredentials googleSignInCredentials) {
            googleSignInCredentials.getClass();
            this.credentials_ = googleSignInCredentials;
            this.credentialsCase_ = 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLegacyV3Password(LegacyV3Password legacyV3Password) {
            legacyV3Password.getClass();
            this.credentials_ = legacyV3Password;
            this.credentialsCase_ = 10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOneTimeToken(OneTimeToken oneTimeToken) {
            oneTimeToken.getClass();
            this.credentials_ = oneTimeToken;
            this.credentialsCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setParentChildCredentials(ParentChildCredentials parentChildCredentials) {
            parentChildCredentials.getClass();
            this.credentials_ = parentChildCredentials;
            this.credentialsCase_ = 6;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPassword(Password password) {
            password.getClass();
            this.credentials_ = password;
            this.credentialsCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhoneNumber(PhoneNumber phoneNumber) {
            phoneNumber.getClass();
            this.credentials_ = phoneNumber;
            this.credentialsCase_ = 3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSamsungSignInCredentials(SamsungSignInCredentials samsungSignInCredentials) {
            samsungSignInCredentials.getClass();
            this.credentials_ = samsungSignInCredentials;
            this.credentialsCase_ = 5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnencryptedStoredCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
            unencryptedStoredCredentials.getClass();
            this.credentials_ = unencryptedStoredCredentials;
            this.credentialsCase_ = 4;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\n\u0001\u0000\u0001\n\n\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000\u0003<\u0000\u0004<\u0000\u0005<\u0000\u0006<\u0000\u0007<\u0000\b<\u0000\t<\u0000\n<\u0000", new Object[]{"credentials_", "credentialsCase_", Password.class, OneTimeToken.class, PhoneNumber.class, EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.class, SamsungSignInCredentials.class, ParentChildCredentials.class, GoogleSignInCredentials.class, AppleSignInCredentials.class, FacebookAccessToken.class, LegacyV3Password.class});
                case 3:
                    return new AuthenticateCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AuthenticateCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public AppleSignInCredentials getAppleSignInCredentials() {
            return this.credentialsCase_ == 8 ? (AppleSignInCredentials) this.credentials_ : AppleSignInCredentials.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public CredentialsCase getCredentialsCase() {
            return CredentialsCase.forNumber(this.credentialsCase_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public FacebookAccessToken getFacebookAccessToken() {
            return this.credentialsCase_ == 9 ? (FacebookAccessToken) this.credentials_ : FacebookAccessToken.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public GoogleSignInCredentials getGoogleSignInCredentials() {
            return this.credentialsCase_ == 7 ? (GoogleSignInCredentials) this.credentials_ : GoogleSignInCredentials.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public LegacyV3Password getLegacyV3Password() {
            return this.credentialsCase_ == 10 ? (LegacyV3Password) this.credentials_ : LegacyV3Password.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public OneTimeToken getOneTimeToken() {
            return this.credentialsCase_ == 2 ? (OneTimeToken) this.credentials_ : OneTimeToken.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public ParentChildCredentials getParentChildCredentials() {
            return this.credentialsCase_ == 6 ? (ParentChildCredentials) this.credentials_ : ParentChildCredentials.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public Password getPassword() {
            return this.credentialsCase_ == 1 ? (Password) this.credentials_ : Password.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public PhoneNumber getPhoneNumber() {
            return this.credentialsCase_ == 3 ? (PhoneNumber) this.credentials_ : PhoneNumber.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public SamsungSignInCredentials getSamsungSignInCredentials() {
            return this.credentialsCase_ == 5 ? (SamsungSignInCredentials) this.credentials_ : SamsungSignInCredentials.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public EsUnencryptedStoredCredentials.UnencryptedStoredCredentials getUnencryptedStoredCredentials() {
            return this.credentialsCase_ == 4 ? (EsUnencryptedStoredCredentials.UnencryptedStoredCredentials) this.credentials_ : EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasAppleSignInCredentials() {
            return this.credentialsCase_ == 8;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasFacebookAccessToken() {
            return this.credentialsCase_ == 9;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasGoogleSignInCredentials() {
            return this.credentialsCase_ == 7;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasLegacyV3Password() {
            return this.credentialsCase_ == 10;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasOneTimeToken() {
            return this.credentialsCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasParentChildCredentials() {
            return this.credentialsCase_ == 6;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasPassword() {
            return this.credentialsCase_ == 1;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasPhoneNumber() {
            return this.credentialsCase_ == 3;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasSamsungSignInCredentials() {
            return this.credentialsCase_ == 5;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.AuthenticateCredentialsOrBuilder
        public boolean hasUnencryptedStoredCredentials() {
            return this.credentialsCase_ == 4;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AuthenticateCredentials authenticateCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(authenticateCredentials);
        }

        public static AuthenticateCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AuthenticateCredentials parseFrom(ByteString byteString) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AuthenticateCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AuthenticateCredentials parseFrom(byte[] bArr) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AuthenticateCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AuthenticateCredentials parseFrom(InputStream inputStream) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AuthenticateCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AuthenticateCredentials parseFrom(au auVar) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AuthenticateCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (AuthenticateCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AuthenticateCredentialsOrBuilder extends yj1 {
        AppleSignInCredentials getAppleSignInCredentials();

        AuthenticateCredentials.CredentialsCase getCredentialsCase();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        FacebookAccessToken getFacebookAccessToken();

        GoogleSignInCredentials getGoogleSignInCredentials();

        LegacyV3Password getLegacyV3Password();

        OneTimeToken getOneTimeToken();

        ParentChildCredentials getParentChildCredentials();

        Password getPassword();

        PhoneNumber getPhoneNumber();

        SamsungSignInCredentials getSamsungSignInCredentials();

        EsUnencryptedStoredCredentials.UnencryptedStoredCredentials getUnencryptedStoredCredentials();

        boolean hasAppleSignInCredentials();

        boolean hasFacebookAccessToken();

        boolean hasGoogleSignInCredentials();

        boolean hasLegacyV3Password();

        boolean hasOneTimeToken();

        boolean hasParentChildCredentials();

        boolean hasPassword();

        boolean hasPhoneNumber();

        boolean hasSamsungSignInCredentials();

        boolean hasUnencryptedStoredCredentials();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class FacebookAccessToken extends i implements FacebookAccessTokenOrBuilder {
        public static final int ACCESSTOKEN_FIELD_NUMBER = 2;
        private static final FacebookAccessToken DEFAULT_INSTANCE;
        public static final int FBUID_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private String fbUID_ = "";
        private String accessToken_ = "";

        public static final class Builder extends h01 implements FacebookAccessTokenOrBuilder {
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
                ((FacebookAccessToken) this.instance).clearAccessToken();
                return this;
            }

            public Builder clearFbUID() {
                copyOnWrite();
                ((FacebookAccessToken) this.instance).clearFbUID();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
            public String getAccessToken() {
                return ((FacebookAccessToken) this.instance).getAccessToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
            public ByteString getAccessTokenBytes() {
                return ((FacebookAccessToken) this.instance).getAccessTokenBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
            public String getFbUID() {
                return ((FacebookAccessToken) this.instance).getFbUID();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
            public ByteString getFbUIDBytes() {
                return ((FacebookAccessToken) this.instance).getFbUIDBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAccessToken(String str) {
                copyOnWrite();
                ((FacebookAccessToken) this.instance).setAccessToken(str);
                return this;
            }

            public Builder setAccessTokenBytes(ByteString byteString) {
                copyOnWrite();
                ((FacebookAccessToken) this.instance).setAccessTokenBytes(byteString);
                return this;
            }

            public Builder setFbUID(String str) {
                copyOnWrite();
                ((FacebookAccessToken) this.instance).setFbUID(str);
                return this;
            }

            public Builder setFbUIDBytes(ByteString byteString) {
                copyOnWrite();
                ((FacebookAccessToken) this.instance).setFbUIDBytes(byteString);
                return this;
            }

            private Builder() {
                super(FacebookAccessToken.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m47mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m48mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m49mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            FacebookAccessToken facebookAccessToken = new FacebookAccessToken();
            DEFAULT_INSTANCE = facebookAccessToken;
            i.registerDefaultInstance(FacebookAccessToken.class, facebookAccessToken);
        }

        private FacebookAccessToken() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAccessToken() {
            this.accessToken_ = getDefaultInstance().getAccessToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFbUID() {
            this.fbUID_ = getDefaultInstance().getFbUID();
        }

        public static FacebookAccessToken getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static FacebookAccessToken parseDelimitedFrom(InputStream inputStream) {
            return (FacebookAccessToken) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FacebookAccessToken parseFrom(ByteBuffer byteBuffer) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessToken(String str) {
            str.getClass();
            this.accessToken_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAccessTokenBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.accessToken_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFbUID(String str) {
            str.getClass();
            this.fbUID_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFbUIDBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.fbUID_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"fbUID_", "accessToken_"});
                case 3:
                    return new FacebookAccessToken();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (FacebookAccessToken.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
        public String getAccessToken() {
            return this.accessToken_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
        public ByteString getAccessTokenBytes() {
            return ByteString.d(this.accessToken_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
        public String getFbUID() {
            return this.fbUID_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.FacebookAccessTokenOrBuilder
        public ByteString getFbUIDBytes() {
            return ByteString.d(this.fbUID_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(FacebookAccessToken facebookAccessToken) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(facebookAccessToken);
        }

        public static FacebookAccessToken parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (FacebookAccessToken) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static FacebookAccessToken parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static FacebookAccessToken parseFrom(ByteString byteString) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FacebookAccessToken parseFrom(ByteString byteString, rl0 rl0Var) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static FacebookAccessToken parseFrom(byte[] bArr) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FacebookAccessToken parseFrom(byte[] bArr, rl0 rl0Var) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static FacebookAccessToken parseFrom(InputStream inputStream) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FacebookAccessToken parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static FacebookAccessToken parseFrom(au auVar) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static FacebookAccessToken parseFrom(au auVar, rl0 rl0Var) {
            return (FacebookAccessToken) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface FacebookAccessTokenOrBuilder extends yj1 {
        String getAccessToken();

        ByteString getAccessTokenBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getFbUID();

        ByteString getFbUIDBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class GoogleSignInCredentials extends i implements GoogleSignInCredentialsOrBuilder {
        public static final int AUTHCODE_FIELD_NUMBER = 1;
        private static final GoogleSignInCredentials DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int REDIRECTURI_FIELD_NUMBER = 2;
        private String authCode_ = "";
        private String redirectUri_ = "";

        public static final class Builder extends h01 implements GoogleSignInCredentialsOrBuilder {
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

            public Builder clearAuthCode() {
                copyOnWrite();
                ((GoogleSignInCredentials) this.instance).clearAuthCode();
                return this;
            }

            public Builder clearRedirectUri() {
                copyOnWrite();
                ((GoogleSignInCredentials) this.instance).clearRedirectUri();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
            public String getAuthCode() {
                return ((GoogleSignInCredentials) this.instance).getAuthCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
            public ByteString getAuthCodeBytes() {
                return ((GoogleSignInCredentials) this.instance).getAuthCodeBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
            public String getRedirectUri() {
                return ((GoogleSignInCredentials) this.instance).getRedirectUri();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
            public ByteString getRedirectUriBytes() {
                return ((GoogleSignInCredentials) this.instance).getRedirectUriBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAuthCode(String str) {
                copyOnWrite();
                ((GoogleSignInCredentials) this.instance).setAuthCode(str);
                return this;
            }

            public Builder setAuthCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((GoogleSignInCredentials) this.instance).setAuthCodeBytes(byteString);
                return this;
            }

            public Builder setRedirectUri(String str) {
                copyOnWrite();
                ((GoogleSignInCredentials) this.instance).setRedirectUri(str);
                return this;
            }

            public Builder setRedirectUriBytes(ByteString byteString) {
                copyOnWrite();
                ((GoogleSignInCredentials) this.instance).setRedirectUriBytes(byteString);
                return this;
            }

            private Builder() {
                super(GoogleSignInCredentials.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m50mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m51mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m52mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            GoogleSignInCredentials googleSignInCredentials = new GoogleSignInCredentials();
            DEFAULT_INSTANCE = googleSignInCredentials;
            i.registerDefaultInstance(GoogleSignInCredentials.class, googleSignInCredentials);
        }

        private GoogleSignInCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAuthCode() {
            this.authCode_ = getDefaultInstance().getAuthCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRedirectUri() {
            this.redirectUri_ = getDefaultInstance().getRedirectUri();
        }

        public static GoogleSignInCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static GoogleSignInCredentials parseDelimitedFrom(InputStream inputStream) {
            return (GoogleSignInCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GoogleSignInCredentials parseFrom(ByteBuffer byteBuffer) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthCode(String str) {
            str.getClass();
            this.authCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthCodeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.authCode_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRedirectUri(String str) {
            str.getClass();
            this.redirectUri_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRedirectUriBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.redirectUri_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"authCode_", "redirectUri_"});
                case 3:
                    return new GoogleSignInCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (GoogleSignInCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
        public String getAuthCode() {
            return this.authCode_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
        public ByteString getAuthCodeBytes() {
            return ByteString.d(this.authCode_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
        public String getRedirectUri() {
            return this.redirectUri_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.GoogleSignInCredentialsOrBuilder
        public ByteString getRedirectUriBytes() {
            return ByteString.d(this.redirectUri_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(GoogleSignInCredentials googleSignInCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(googleSignInCredentials);
        }

        public static GoogleSignInCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (GoogleSignInCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static GoogleSignInCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static GoogleSignInCredentials parseFrom(ByteString byteString) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static GoogleSignInCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static GoogleSignInCredentials parseFrom(byte[] bArr) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static GoogleSignInCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static GoogleSignInCredentials parseFrom(InputStream inputStream) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static GoogleSignInCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static GoogleSignInCredentials parseFrom(au auVar) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static GoogleSignInCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (GoogleSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface GoogleSignInCredentialsOrBuilder extends yj1 {
        String getAuthCode();

        ByteString getAuthCodeBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getRedirectUri();

        ByteString getRedirectUriBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class LegacyV3Password extends i implements LegacyV3PasswordOrBuilder {
        private static final LegacyV3Password DEFAULT_INSTANCE;
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER = null;
        public static final int PASSWORD_FIELD_NUMBER = 2;
        private String id_ = "";
        private String password_ = "";

        public static final class Builder extends h01 implements LegacyV3PasswordOrBuilder {
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

            public Builder clearId() {
                copyOnWrite();
                ((LegacyV3Password) this.instance).clearId();
                return this;
            }

            public Builder clearPassword() {
                copyOnWrite();
                ((LegacyV3Password) this.instance).clearPassword();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
            public String getId() {
                return ((LegacyV3Password) this.instance).getId();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
            public ByteString getIdBytes() {
                return ((LegacyV3Password) this.instance).getIdBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
            public String getPassword() {
                return ((LegacyV3Password) this.instance).getPassword();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
            public ByteString getPasswordBytes() {
                return ((LegacyV3Password) this.instance).getPasswordBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setId(String str) {
                copyOnWrite();
                ((LegacyV3Password) this.instance).setId(str);
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                copyOnWrite();
                ((LegacyV3Password) this.instance).setIdBytes(byteString);
                return this;
            }

            public Builder setPassword(String str) {
                copyOnWrite();
                ((LegacyV3Password) this.instance).setPassword(str);
                return this;
            }

            public Builder setPasswordBytes(ByteString byteString) {
                copyOnWrite();
                ((LegacyV3Password) this.instance).setPasswordBytes(byteString);
                return this;
            }

            private Builder() {
                super(LegacyV3Password.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m53mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m54mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m55mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            LegacyV3Password legacyV3Password = new LegacyV3Password();
            DEFAULT_INSTANCE = legacyV3Password;
            i.registerDefaultInstance(LegacyV3Password.class, legacyV3Password);
        }

        private LegacyV3Password() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearId() {
            this.id_ = getDefaultInstance().getId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPassword() {
            this.password_ = getDefaultInstance().getPassword();
        }

        public static LegacyV3Password getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static LegacyV3Password parseDelimitedFrom(InputStream inputStream) {
            return (LegacyV3Password) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LegacyV3Password parseFrom(ByteBuffer byteBuffer) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setId(String str) {
            str.getClass();
            this.id_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.id_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPassword(String str) {
            str.getClass();
            this.password_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPasswordBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.password_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"id_", "password_"});
                case 3:
                    return new LegacyV3Password();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (LegacyV3Password.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
        public String getId() {
            return this.id_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
        public ByteString getIdBytes() {
            return ByteString.d(this.id_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
        public String getPassword() {
            return this.password_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.LegacyV3PasswordOrBuilder
        public ByteString getPasswordBytes() {
            return ByteString.d(this.password_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(LegacyV3Password legacyV3Password) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(legacyV3Password);
        }

        public static LegacyV3Password parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (LegacyV3Password) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static LegacyV3Password parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static LegacyV3Password parseFrom(ByteString byteString) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static LegacyV3Password parseFrom(ByteString byteString, rl0 rl0Var) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static LegacyV3Password parseFrom(byte[] bArr) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static LegacyV3Password parseFrom(byte[] bArr, rl0 rl0Var) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static LegacyV3Password parseFrom(InputStream inputStream) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static LegacyV3Password parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static LegacyV3Password parseFrom(au auVar) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static LegacyV3Password parseFrom(au auVar, rl0 rl0Var) {
            return (LegacyV3Password) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface LegacyV3PasswordOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getId();

        ByteString getIdBytes();

        String getPassword();

        ByteString getPasswordBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class OneTimeToken extends i implements OneTimeTokenOrBuilder {
        private static final OneTimeToken DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int TOKEN_FIELD_NUMBER = 1;
        private String token_ = "";

        public static final class Builder extends h01 implements OneTimeTokenOrBuilder {
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

            public Builder clearToken() {
                copyOnWrite();
                ((OneTimeToken) this.instance).clearToken();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.OneTimeTokenOrBuilder
            public String getToken() {
                return ((OneTimeToken) this.instance).getToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.OneTimeTokenOrBuilder
            public ByteString getTokenBytes() {
                return ((OneTimeToken) this.instance).getTokenBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setToken(String str) {
                copyOnWrite();
                ((OneTimeToken) this.instance).setToken(str);
                return this;
            }

            public Builder setTokenBytes(ByteString byteString) {
                copyOnWrite();
                ((OneTimeToken) this.instance).setTokenBytes(byteString);
                return this;
            }

            private Builder() {
                super(OneTimeToken.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m56mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m57mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m58mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            OneTimeToken oneTimeToken = new OneTimeToken();
            DEFAULT_INSTANCE = oneTimeToken;
            i.registerDefaultInstance(OneTimeToken.class, oneTimeToken);
        }

        private OneTimeToken() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearToken() {
            this.token_ = getDefaultInstance().getToken();
        }

        public static OneTimeToken getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static OneTimeToken parseDelimitedFrom(InputStream inputStream) {
            return (OneTimeToken) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static OneTimeToken parseFrom(ByteBuffer byteBuffer) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setToken(String str) {
            str.getClass();
            this.token_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTokenBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.token_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȉ", new Object[]{"token_"});
                case 3:
                    return new OneTimeToken();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (OneTimeToken.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.OneTimeTokenOrBuilder
        public String getToken() {
            return this.token_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.OneTimeTokenOrBuilder
        public ByteString getTokenBytes() {
            return ByteString.d(this.token_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(OneTimeToken oneTimeToken) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(oneTimeToken);
        }

        public static OneTimeToken parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (OneTimeToken) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static OneTimeToken parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static OneTimeToken parseFrom(ByteString byteString) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static OneTimeToken parseFrom(ByteString byteString, rl0 rl0Var) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static OneTimeToken parseFrom(byte[] bArr) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static OneTimeToken parseFrom(byte[] bArr, rl0 rl0Var) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static OneTimeToken parseFrom(InputStream inputStream) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static OneTimeToken parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static OneTimeToken parseFrom(au auVar) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static OneTimeToken parseFrom(au auVar, rl0 rl0Var) {
            return (OneTimeToken) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface OneTimeTokenOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getToken();

        ByteString getTokenBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class ParentChildCredentials extends i implements ParentChildCredentialsOrBuilder {
        public static final int CHILDID_FIELD_NUMBER = 1;
        private static final ParentChildCredentials DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int UNENCRYPTEDPARENTCREDENTIALS_FIELD_NUMBER = 2;
        private String childId_ = "";
        private EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedParentCredentials_;

        public static final class Builder extends h01 implements ParentChildCredentialsOrBuilder {
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

            public Builder clearChildId() {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).clearChildId();
                return this;
            }

            public Builder clearUnencryptedParentCredentials() {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).clearUnencryptedParentCredentials();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
            public String getChildId() {
                return ((ParentChildCredentials) this.instance).getChildId();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
            public ByteString getChildIdBytes() {
                return ((ParentChildCredentials) this.instance).getChildIdBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
            public EsUnencryptedStoredCredentials.UnencryptedStoredCredentials getUnencryptedParentCredentials() {
                return ((ParentChildCredentials) this.instance).getUnencryptedParentCredentials();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
            public boolean hasUnencryptedParentCredentials() {
                return ((ParentChildCredentials) this.instance).hasUnencryptedParentCredentials();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeUnencryptedParentCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).mergeUnencryptedParentCredentials(unencryptedStoredCredentials);
                return this;
            }

            public Builder setChildId(String str) {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).setChildId(str);
                return this;
            }

            public Builder setChildIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).setChildIdBytes(byteString);
                return this;
            }

            public Builder setUnencryptedParentCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).setUnencryptedParentCredentials(unencryptedStoredCredentials);
                return this;
            }

            private Builder() {
                super(ParentChildCredentials.DEFAULT_INSTANCE);
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

            public Builder setUnencryptedParentCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.Builder builder) {
                copyOnWrite();
                ((ParentChildCredentials) this.instance).setUnencryptedParentCredentials((EsUnencryptedStoredCredentials.UnencryptedStoredCredentials) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m59mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m60mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m61mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            ParentChildCredentials parentChildCredentials = new ParentChildCredentials();
            DEFAULT_INSTANCE = parentChildCredentials;
            i.registerDefaultInstance(ParentChildCredentials.class, parentChildCredentials);
        }

        private ParentChildCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearChildId() {
            this.childId_ = getDefaultInstance().getChildId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUnencryptedParentCredentials() {
            this.unencryptedParentCredentials_ = null;
        }

        public static ParentChildCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeUnencryptedParentCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
            unencryptedStoredCredentials.getClass();
            EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials2 = this.unencryptedParentCredentials_;
            if (unencryptedStoredCredentials2 == null || unencryptedStoredCredentials2 == EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.getDefaultInstance()) {
                this.unencryptedParentCredentials_ = unencryptedStoredCredentials;
            } else {
                this.unencryptedParentCredentials_ = (EsUnencryptedStoredCredentials.UnencryptedStoredCredentials) ((EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.Builder) EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.newBuilder(this.unencryptedParentCredentials_).mergeFrom((i) unencryptedStoredCredentials)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static ParentChildCredentials parseDelimitedFrom(InputStream inputStream) {
            return (ParentChildCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ParentChildCredentials parseFrom(ByteBuffer byteBuffer) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChildId(String str) {
            str.getClass();
            this.childId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChildIdBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.childId_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setUnencryptedParentCredentials(EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials) {
            unencryptedStoredCredentials.getClass();
            this.unencryptedParentCredentials_ = unencryptedStoredCredentials;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"childId_", "unencryptedParentCredentials_"});
                case 3:
                    return new ParentChildCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (ParentChildCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
        public String getChildId() {
            return this.childId_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
        public ByteString getChildIdBytes() {
            return ByteString.d(this.childId_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
        public EsUnencryptedStoredCredentials.UnencryptedStoredCredentials getUnencryptedParentCredentials() {
            EsUnencryptedStoredCredentials.UnencryptedStoredCredentials unencryptedStoredCredentials = this.unencryptedParentCredentials_;
            return unencryptedStoredCredentials == null ? EsUnencryptedStoredCredentials.UnencryptedStoredCredentials.getDefaultInstance() : unencryptedStoredCredentials;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.ParentChildCredentialsOrBuilder
        public boolean hasUnencryptedParentCredentials() {
            return this.unencryptedParentCredentials_ != null;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(ParentChildCredentials parentChildCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(parentChildCredentials);
        }

        public static ParentChildCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (ParentChildCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static ParentChildCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static ParentChildCredentials parseFrom(ByteString byteString) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ParentChildCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static ParentChildCredentials parseFrom(byte[] bArr) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ParentChildCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static ParentChildCredentials parseFrom(InputStream inputStream) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ParentChildCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static ParentChildCredentials parseFrom(au auVar) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static ParentChildCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (ParentChildCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface ParentChildCredentialsOrBuilder extends yj1 {
        String getChildId();

        ByteString getChildIdBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        EsUnencryptedStoredCredentials.UnencryptedStoredCredentials getUnencryptedParentCredentials();

        boolean hasUnencryptedParentCredentials();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class Password extends i implements PasswordOrBuilder {
        private static final Password DEFAULT_INSTANCE;
        public static final int HINT_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private int optionalHintCase_ = 0;
        private Object optionalHint_;

        public static final class Builder extends h01 implements PasswordOrBuilder {
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

            public Builder clearHint() {
                copyOnWrite();
                ((Password) this.instance).clearHint();
                return this;
            }

            public Builder clearOptionalHint() {
                copyOnWrite();
                ((Password) this.instance).clearOptionalHint();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
            public String getHint() {
                return ((Password) this.instance).getHint();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
            public ByteString getHintBytes() {
                return ((Password) this.instance).getHintBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
            public OptionalHintCase getOptionalHintCase() {
                return ((Password) this.instance).getOptionalHintCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
            public boolean hasHint() {
                return ((Password) this.instance).hasHint();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setHint(String str) {
                copyOnWrite();
                ((Password) this.instance).setHint(str);
                return this;
            }

            public Builder setHintBytes(ByteString byteString) {
                copyOnWrite();
                ((Password) this.instance).setHintBytes(byteString);
                return this;
            }

            private Builder() {
                super(Password.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m62mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m63mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m64mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum OptionalHintCase {
            HINT(1),
            OPTIONALHINT_NOT_SET(0);

            private final int value;

            OptionalHintCase(int i) {
                this.value = i;
            }

            public static OptionalHintCase forNumber(int i) {
                if (i == 0) {
                    return OPTIONALHINT_NOT_SET;
                }
                if (i != 1) {
                    return null;
                }
                return HINT;
            }

            public int getNumber() {
                return this.value;
            }

            @Deprecated
            public static OptionalHintCase valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            Password password = new Password();
            DEFAULT_INSTANCE = password;
            i.registerDefaultInstance(Password.class, password);
        }

        private Password() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHint() {
            if (this.optionalHintCase_ == 1) {
                this.optionalHintCase_ = 0;
                this.optionalHint_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearOptionalHint() {
            this.optionalHintCase_ = 0;
            this.optionalHint_ = null;
        }

        public static Password getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static Password parseDelimitedFrom(InputStream inputStream) {
            return (Password) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Password parseFrom(ByteBuffer byteBuffer) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHint(String str) {
            str.getClass();
            this.optionalHintCase_ = 1;
            this.optionalHint_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setHintBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.optionalHint_ = byteString.s();
            this.optionalHintCase_ = 1;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0001\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001Ȼ\u0000", new Object[]{"optionalHint_", "optionalHintCase_"});
                case 3:
                    return new Password();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (Password.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
        public String getHint() {
            return this.optionalHintCase_ == 1 ? (String) this.optionalHint_ : "";
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
        public ByteString getHintBytes() {
            return ByteString.d(this.optionalHintCase_ == 1 ? (String) this.optionalHint_ : "");
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
        public OptionalHintCase getOptionalHintCase() {
            return OptionalHintCase.forNumber(this.optionalHintCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PasswordOrBuilder
        public boolean hasHint() {
            return this.optionalHintCase_ == 1;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(Password password) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(password);
        }

        public static Password parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (Password) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static Password parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static Password parseFrom(ByteString byteString) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Password parseFrom(ByteString byteString, rl0 rl0Var) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static Password parseFrom(byte[] bArr) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Password parseFrom(byte[] bArr, rl0 rl0Var) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static Password parseFrom(InputStream inputStream) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Password parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static Password parseFrom(au auVar) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static Password parseFrom(au auVar, rl0 rl0Var) {
            return (Password) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface PasswordOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getHint();

        ByteString getHintBytes();

        Password.OptionalHintCase getOptionalHintCase();

        boolean hasHint();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class PhoneNumber extends i implements PhoneNumberOrBuilder {
        public static final int COUNTRYCALLINGCODE_FIELD_NUMBER = 3;
        private static final PhoneNumber DEFAULT_INSTANCE;
        public static final int ISOCOUNTRYCODE_FIELD_NUMBER = 2;
        public static final int NUMBER_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private String number_ = "";
        private String isoCountryCode_ = "";
        private String countryCallingCode_ = "";

        public static final class Builder extends h01 implements PhoneNumberOrBuilder {
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

            public Builder clearCountryCallingCode() {
                copyOnWrite();
                ((PhoneNumber) this.instance).clearCountryCallingCode();
                return this;
            }

            public Builder clearIsoCountryCode() {
                copyOnWrite();
                ((PhoneNumber) this.instance).clearIsoCountryCode();
                return this;
            }

            public Builder clearNumber() {
                copyOnWrite();
                ((PhoneNumber) this.instance).clearNumber();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
            public String getCountryCallingCode() {
                return ((PhoneNumber) this.instance).getCountryCallingCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
            public ByteString getCountryCallingCodeBytes() {
                return ((PhoneNumber) this.instance).getCountryCallingCodeBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
            public String getIsoCountryCode() {
                return ((PhoneNumber) this.instance).getIsoCountryCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
            public ByteString getIsoCountryCodeBytes() {
                return ((PhoneNumber) this.instance).getIsoCountryCodeBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
            public String getNumber() {
                return ((PhoneNumber) this.instance).getNumber();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
            public ByteString getNumberBytes() {
                return ((PhoneNumber) this.instance).getNumberBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setCountryCallingCode(String str) {
                copyOnWrite();
                ((PhoneNumber) this.instance).setCountryCallingCode(str);
                return this;
            }

            public Builder setCountryCallingCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((PhoneNumber) this.instance).setCountryCallingCodeBytes(byteString);
                return this;
            }

            public Builder setIsoCountryCode(String str) {
                copyOnWrite();
                ((PhoneNumber) this.instance).setIsoCountryCode(str);
                return this;
            }

            public Builder setIsoCountryCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((PhoneNumber) this.instance).setIsoCountryCodeBytes(byteString);
                return this;
            }

            public Builder setNumber(String str) {
                copyOnWrite();
                ((PhoneNumber) this.instance).setNumber(str);
                return this;
            }

            public Builder setNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((PhoneNumber) this.instance).setNumberBytes(byteString);
                return this;
            }

            private Builder() {
                super(PhoneNumber.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m65mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m66mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m67mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            PhoneNumber phoneNumber = new PhoneNumber();
            DEFAULT_INSTANCE = phoneNumber;
            i.registerDefaultInstance(PhoneNumber.class, phoneNumber);
        }

        private PhoneNumber() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCountryCallingCode() {
            this.countryCallingCode_ = getDefaultInstance().getCountryCallingCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsoCountryCode() {
            this.isoCountryCode_ = getDefaultInstance().getIsoCountryCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearNumber() {
            this.number_ = getDefaultInstance().getNumber();
        }

        public static PhoneNumber getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static PhoneNumber parseDelimitedFrom(InputStream inputStream) {
            return (PhoneNumber) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PhoneNumber parseFrom(ByteBuffer byteBuffer) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCountryCallingCode(String str) {
            str.getClass();
            this.countryCallingCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCountryCallingCodeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.countryCallingCode_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsoCountryCode(String str) {
            str.getClass();
            this.isoCountryCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsoCountryCodeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.isoCountryCode_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumber(String str) {
            str.getClass();
            this.number_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNumberBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.number_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"number_", "isoCountryCode_", "countryCallingCode_"});
                case 3:
                    return new PhoneNumber();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (PhoneNumber.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
        public String getCountryCallingCode() {
            return this.countryCallingCode_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
        public ByteString getCountryCallingCodeBytes() {
            return ByteString.d(this.countryCallingCode_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
        public String getIsoCountryCode() {
            return this.isoCountryCode_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
        public ByteString getIsoCountryCodeBytes() {
            return ByteString.d(this.isoCountryCode_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
        public String getNumber() {
            return this.number_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.PhoneNumberOrBuilder
        public ByteString getNumberBytes() {
            return ByteString.d(this.number_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(PhoneNumber phoneNumber) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(phoneNumber);
        }

        public static PhoneNumber parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (PhoneNumber) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static PhoneNumber parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static PhoneNumber parseFrom(ByteString byteString) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PhoneNumber parseFrom(ByteString byteString, rl0 rl0Var) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static PhoneNumber parseFrom(byte[] bArr) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PhoneNumber parseFrom(byte[] bArr, rl0 rl0Var) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static PhoneNumber parseFrom(InputStream inputStream) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PhoneNumber parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static PhoneNumber parseFrom(au auVar) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static PhoneNumber parseFrom(au auVar, rl0 rl0Var) {
            return (PhoneNumber) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface PhoneNumberOrBuilder extends yj1 {
        String getCountryCallingCode();

        ByteString getCountryCallingCodeBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getIsoCountryCode();

        ByteString getIsoCountryCodeBytes();

        String getNumber();

        ByteString getNumberBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class SamsungSignInCredentials extends i implements SamsungSignInCredentialsOrBuilder {
        public static final int AUTHCODE_FIELD_NUMBER = 1;
        private static final SamsungSignInCredentials DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int REDIRECTURI_FIELD_NUMBER = 2;
        public static final int TOKENENDPOINTURL_FIELD_NUMBER = 3;
        private String authCode_ = "";
        private String redirectUri_ = "";
        private String tokenEndpointUrl_ = "";

        public static final class Builder extends h01 implements SamsungSignInCredentialsOrBuilder {
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

            public Builder clearAuthCode() {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).clearAuthCode();
                return this;
            }

            public Builder clearRedirectUri() {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).clearRedirectUri();
                return this;
            }

            public Builder clearTokenEndpointUrl() {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).clearTokenEndpointUrl();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
            public String getAuthCode() {
                return ((SamsungSignInCredentials) this.instance).getAuthCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
            public ByteString getAuthCodeBytes() {
                return ((SamsungSignInCredentials) this.instance).getAuthCodeBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
            public String getRedirectUri() {
                return ((SamsungSignInCredentials) this.instance).getRedirectUri();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
            public ByteString getRedirectUriBytes() {
                return ((SamsungSignInCredentials) this.instance).getRedirectUriBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
            public String getTokenEndpointUrl() {
                return ((SamsungSignInCredentials) this.instance).getTokenEndpointUrl();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
            public ByteString getTokenEndpointUrlBytes() {
                return ((SamsungSignInCredentials) this.instance).getTokenEndpointUrlBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAuthCode(String str) {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).setAuthCode(str);
                return this;
            }

            public Builder setAuthCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).setAuthCodeBytes(byteString);
                return this;
            }

            public Builder setRedirectUri(String str) {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).setRedirectUri(str);
                return this;
            }

            public Builder setRedirectUriBytes(ByteString byteString) {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).setRedirectUriBytes(byteString);
                return this;
            }

            public Builder setTokenEndpointUrl(String str) {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).setTokenEndpointUrl(str);
                return this;
            }

            public Builder setTokenEndpointUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((SamsungSignInCredentials) this.instance).setTokenEndpointUrlBytes(byteString);
                return this;
            }

            private Builder() {
                super(SamsungSignInCredentials.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m68mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m69mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m70mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            SamsungSignInCredentials samsungSignInCredentials = new SamsungSignInCredentials();
            DEFAULT_INSTANCE = samsungSignInCredentials;
            i.registerDefaultInstance(SamsungSignInCredentials.class, samsungSignInCredentials);
        }

        private SamsungSignInCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAuthCode() {
            this.authCode_ = getDefaultInstance().getAuthCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRedirectUri() {
            this.redirectUri_ = getDefaultInstance().getRedirectUri();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearTokenEndpointUrl() {
            this.tokenEndpointUrl_ = getDefaultInstance().getTokenEndpointUrl();
        }

        public static SamsungSignInCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static SamsungSignInCredentials parseDelimitedFrom(InputStream inputStream) {
            return (SamsungSignInCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SamsungSignInCredentials parseFrom(ByteBuffer byteBuffer) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthCode(String str) {
            str.getClass();
            this.authCode_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthCodeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.authCode_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRedirectUri(String str) {
            str.getClass();
            this.redirectUri_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRedirectUriBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.redirectUri_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTokenEndpointUrl(String str) {
            str.getClass();
            this.tokenEndpointUrl_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTokenEndpointUrlBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.tokenEndpointUrl_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"authCode_", "redirectUri_", "tokenEndpointUrl_"});
                case 3:
                    return new SamsungSignInCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (SamsungSignInCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
        public String getAuthCode() {
            return this.authCode_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
        public ByteString getAuthCodeBytes() {
            return ByteString.d(this.authCode_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
        public String getRedirectUri() {
            return this.redirectUri_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
        public ByteString getRedirectUriBytes() {
            return ByteString.d(this.redirectUri_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
        public String getTokenEndpointUrl() {
            return this.tokenEndpointUrl_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateCredentials.SamsungSignInCredentialsOrBuilder
        public ByteString getTokenEndpointUrlBytes() {
            return ByteString.d(this.tokenEndpointUrl_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(SamsungSignInCredentials samsungSignInCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(samsungSignInCredentials);
        }

        public static SamsungSignInCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (SamsungSignInCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static SamsungSignInCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static SamsungSignInCredentials parseFrom(ByteString byteString) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SamsungSignInCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static SamsungSignInCredentials parseFrom(byte[] bArr) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SamsungSignInCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static SamsungSignInCredentials parseFrom(InputStream inputStream) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SamsungSignInCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static SamsungSignInCredentials parseFrom(au auVar) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static SamsungSignInCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (SamsungSignInCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface SamsungSignInCredentialsOrBuilder extends yj1 {
        String getAuthCode();

        ByteString getAuthCodeBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getRedirectUri();

        ByteString getRedirectUriBytes();

        String getTokenEndpointUrl();

        ByteString getTokenEndpointUrlBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsAuthenticateCredentials() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
