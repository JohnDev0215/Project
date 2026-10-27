package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.Timestamp;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.r1;
import p.rl0;
import p.vj1;
import p.vl2;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsAccessToken {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken$1, reason: invalid class name */
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

    public static final class AccessToken extends i implements AccessTokenOrBuilder {
        private static final AccessToken DEFAULT_INSTANCE;
        public static final int EXPIRESAT_FIELD_NUMBER = 3;
        private static volatile ds1 PARSER = null;
        public static final int TOKEN_FIELD_NUMBER = 1;
        public static final int TYPE_FIELD_NUMBER = 2;
        private Timestamp expiresAt_;
        private String token_ = "";
        private String type_ = "";

        public static final class Builder extends h01 implements AccessTokenOrBuilder {
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

            public Builder clearExpiresAt() {
                copyOnWrite();
                ((AccessToken) this.instance).clearExpiresAt();
                return this;
            }

            public Builder clearToken() {
                copyOnWrite();
                ((AccessToken) this.instance).clearToken();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((AccessToken) this.instance).clearType();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
            public Timestamp getExpiresAt() {
                return ((AccessToken) this.instance).getExpiresAt();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
            public String getToken() {
                return ((AccessToken) this.instance).getToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
            public ByteString getTokenBytes() {
                return ((AccessToken) this.instance).getTokenBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
            public String getType() {
                return ((AccessToken) this.instance).getType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
            public ByteString getTypeBytes() {
                return ((AccessToken) this.instance).getTypeBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
            public boolean hasExpiresAt() {
                return ((AccessToken) this.instance).hasExpiresAt();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeExpiresAt(Timestamp timestamp) {
                copyOnWrite();
                ((AccessToken) this.instance).mergeExpiresAt(timestamp);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setExpiresAt(Timestamp timestamp) {
                copyOnWrite();
                ((AccessToken) this.instance).setExpiresAt(timestamp);
                return this;
            }

            public Builder setToken(String str) {
                copyOnWrite();
                ((AccessToken) this.instance).setToken(str);
                return this;
            }

            public Builder setTokenBytes(ByteString byteString) {
                copyOnWrite();
                ((AccessToken) this.instance).setTokenBytes(byteString);
                return this;
            }

            public Builder setType(String str) {
                copyOnWrite();
                ((AccessToken) this.instance).setType(str);
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                copyOnWrite();
                ((AccessToken) this.instance).setTypeBytes(byteString);
                return this;
            }

            private Builder() {
                super(AccessToken.DEFAULT_INSTANCE);
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

            public Builder setExpiresAt(vl2 vl2Var) {
                copyOnWrite();
                ((AccessToken) this.instance).setExpiresAt((Timestamp) vl2Var.build());
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
            public /* bridge */ /* synthetic */ vj1 m22mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m23mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m24mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            AccessToken accessToken = new AccessToken();
            DEFAULT_INSTANCE = accessToken;
            i.registerDefaultInstance(AccessToken.class, accessToken);
        }

        private AccessToken() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExpiresAt() {
            this.expiresAt_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearToken() {
            this.token_ = getDefaultInstance().getToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = getDefaultInstance().getType();
        }

        public static AccessToken getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeExpiresAt(Timestamp timestamp) {
            timestamp.getClass();
            Timestamp timestamp2 = this.expiresAt_;
            if (timestamp2 == null || timestamp2 == Timestamp.e()) {
                this.expiresAt_ = timestamp;
            } else {
                this.expiresAt_ = (Timestamp) ((vl2) Timestamp.f(this.expiresAt_).mergeFrom((i) timestamp)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static AccessToken parseDelimitedFrom(InputStream inputStream) {
            return (AccessToken) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AccessToken parseFrom(ByteBuffer byteBuffer) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExpiresAt(Timestamp timestamp) {
            timestamp.getClass();
            this.expiresAt_ = timestamp;
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

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(String str) {
            str.getClass();
            this.type_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTypeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.type_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\t", new Object[]{"token_", "type_", "expiresAt_"});
                case 3:
                    return new AccessToken();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (AccessToken.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
        public Timestamp getExpiresAt() {
            Timestamp timestamp = this.expiresAt_;
            return timestamp == null ? Timestamp.e() : timestamp;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
        public String getToken() {
            return this.token_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
        public ByteString getTokenBytes() {
            return ByteString.d(this.token_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
        public String getType() {
            return this.type_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
        public ByteString getTypeBytes() {
            return ByteString.d(this.type_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsAccessToken.AccessTokenOrBuilder
        public boolean hasExpiresAt() {
            return this.expiresAt_ != null;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(AccessToken accessToken) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(accessToken);
        }

        public static AccessToken parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (AccessToken) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AccessToken parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static AccessToken parseFrom(ByteString byteString) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AccessToken parseFrom(ByteString byteString, rl0 rl0Var) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static AccessToken parseFrom(byte[] bArr) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AccessToken parseFrom(byte[] bArr, rl0 rl0Var) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static AccessToken parseFrom(InputStream inputStream) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AccessToken parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static AccessToken parseFrom(au auVar) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static AccessToken parseFrom(au auVar, rl0 rl0Var) {
            return (AccessToken) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface AccessTokenOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        Timestamp getExpiresAt();

        String getToken();

        ByteString getTokenBytes();

        String getType();

        ByteString getTypeBytes();

        boolean hasExpiresAt();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsAccessToken() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
