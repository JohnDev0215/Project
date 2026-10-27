package com.spotify.connectivity.auth.common.esperanto.proto;

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
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsUnencryptedStoredCredentials {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials$1, reason: invalid class name */
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

    public static final class UnencryptedStoredCredentials extends i implements UnencryptedStoredCredentialsOrBuilder {
        private static final UnencryptedStoredCredentials DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int STOREDCREDENTIALS_FIELD_NUMBER = 2;
        public static final int USERNAME_FIELD_NUMBER = 1;
        private String username_ = "";
        private ByteString storedCredentials_ = ByteString.c;

        public static final class Builder extends h01 implements UnencryptedStoredCredentialsOrBuilder {
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

            public Builder clearStoredCredentials() {
                copyOnWrite();
                ((UnencryptedStoredCredentials) this.instance).clearStoredCredentials();
                return this;
            }

            public Builder clearUsername() {
                copyOnWrite();
                ((UnencryptedStoredCredentials) this.instance).clearUsername();
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

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials.UnencryptedStoredCredentialsOrBuilder
            public ByteString getStoredCredentials() {
                return ((UnencryptedStoredCredentials) this.instance).getStoredCredentials();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials.UnencryptedStoredCredentialsOrBuilder
            public String getUsername() {
                return ((UnencryptedStoredCredentials) this.instance).getUsername();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials.UnencryptedStoredCredentialsOrBuilder
            public ByteString getUsernameBytes() {
                return ((UnencryptedStoredCredentials) this.instance).getUsernameBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setStoredCredentials(ByteString byteString) {
                copyOnWrite();
                ((UnencryptedStoredCredentials) this.instance).setStoredCredentials(byteString);
                return this;
            }

            public Builder setUsername(String str) {
                copyOnWrite();
                ((UnencryptedStoredCredentials) this.instance).setUsername(str);
                return this;
            }

            public Builder setUsernameBytes(ByteString byteString) {
                copyOnWrite();
                ((UnencryptedStoredCredentials) this.instance).setUsernameBytes(byteString);
                return this;
            }

            private Builder() {
                super(UnencryptedStoredCredentials.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m6mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m7mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m8mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            UnencryptedStoredCredentials unencryptedStoredCredentials = new UnencryptedStoredCredentials();
            DEFAULT_INSTANCE = unencryptedStoredCredentials;
            i.registerDefaultInstance(UnencryptedStoredCredentials.class, unencryptedStoredCredentials);
        }

        private UnencryptedStoredCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearStoredCredentials() {
            this.storedCredentials_ = getDefaultInstance().getStoredCredentials();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsername() {
            this.username_ = getDefaultInstance().getUsername();
        }

        public static UnencryptedStoredCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static UnencryptedStoredCredentials parseDelimitedFrom(InputStream inputStream) {
            return (UnencryptedStoredCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnencryptedStoredCredentials parseFrom(ByteBuffer byteBuffer) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setStoredCredentials(ByteString byteString) {
            byteString.getClass();
            this.storedCredentials_ = byteString;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"username_", "storedCredentials_"});
                case 3:
                    return new UnencryptedStoredCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (UnencryptedStoredCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials.UnencryptedStoredCredentialsOrBuilder
        public ByteString getStoredCredentials() {
            return this.storedCredentials_;
        }

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials.UnencryptedStoredCredentialsOrBuilder
        public String getUsername() {
            return this.username_;
        }

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsUnencryptedStoredCredentials.UnencryptedStoredCredentialsOrBuilder
        public ByteString getUsernameBytes() {
            return ByteString.d(this.username_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(UnencryptedStoredCredentials unencryptedStoredCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(unencryptedStoredCredentials);
        }

        public static UnencryptedStoredCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (UnencryptedStoredCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static UnencryptedStoredCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static UnencryptedStoredCredentials parseFrom(ByteString byteString) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UnencryptedStoredCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static UnencryptedStoredCredentials parseFrom(byte[] bArr) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UnencryptedStoredCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static UnencryptedStoredCredentials parseFrom(InputStream inputStream) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UnencryptedStoredCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static UnencryptedStoredCredentials parseFrom(au auVar) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static UnencryptedStoredCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (UnencryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface UnencryptedStoredCredentialsOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        ByteString getStoredCredentials();

        String getUsername();

        ByteString getUsernameBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsUnencryptedStoredCredentials() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
