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
public final class EsEncryptedStoredCredentials {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials$1, reason: invalid class name */
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

    public static final class EncryptedStoredCredentials extends i implements EncryptedStoredCredentialsOrBuilder {
        public static final int AUTH_BLOB_FIELD_NUMBER = 2;
        private static final EncryptedStoredCredentials DEFAULT_INSTANCE;
        public static final int DEVICE_ID_FIELD_NUMBER = 3;
        private static volatile ds1 PARSER = null;
        public static final int USERNAME_FIELD_NUMBER = 1;
        private String username_ = "";
        private ByteString authBlob_ = ByteString.c;
        private String deviceId_ = "";

        public static final class Builder extends h01 implements EncryptedStoredCredentialsOrBuilder {
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

            public Builder clearAuthBlob() {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).clearAuthBlob();
                return this;
            }

            public Builder clearDeviceId() {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).clearDeviceId();
                return this;
            }

            public Builder clearUsername() {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).clearUsername();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
            public ByteString getAuthBlob() {
                return ((EncryptedStoredCredentials) this.instance).getAuthBlob();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
            public String getDeviceId() {
                return ((EncryptedStoredCredentials) this.instance).getDeviceId();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
            public ByteString getDeviceIdBytes() {
                return ((EncryptedStoredCredentials) this.instance).getDeviceIdBytes();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
            public String getUsername() {
                return ((EncryptedStoredCredentials) this.instance).getUsername();
            }

            @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
            public ByteString getUsernameBytes() {
                return ((EncryptedStoredCredentials) this.instance).getUsernameBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setAuthBlob(ByteString byteString) {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).setAuthBlob(byteString);
                return this;
            }

            public Builder setDeviceId(String str) {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).setDeviceId(str);
                return this;
            }

            public Builder setDeviceIdBytes(ByteString byteString) {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).setDeviceIdBytes(byteString);
                return this;
            }

            public Builder setUsername(String str) {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).setUsername(str);
                return this;
            }

            public Builder setUsernameBytes(ByteString byteString) {
                copyOnWrite();
                ((EncryptedStoredCredentials) this.instance).setUsernameBytes(byteString);
                return this;
            }

            private Builder() {
                super(EncryptedStoredCredentials.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m3mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m4mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m5mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            EncryptedStoredCredentials encryptedStoredCredentials = new EncryptedStoredCredentials();
            DEFAULT_INSTANCE = encryptedStoredCredentials;
            i.registerDefaultInstance(EncryptedStoredCredentials.class, encryptedStoredCredentials);
        }

        private EncryptedStoredCredentials() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearAuthBlob() {
            this.authBlob_ = getDefaultInstance().getAuthBlob();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearDeviceId() {
            this.deviceId_ = getDefaultInstance().getDeviceId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearUsername() {
            this.username_ = getDefaultInstance().getUsername();
        }

        public static EncryptedStoredCredentials getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static EncryptedStoredCredentials parseDelimitedFrom(InputStream inputStream) {
            return (EncryptedStoredCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static EncryptedStoredCredentials parseFrom(ByteBuffer byteBuffer) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAuthBlob(ByteString byteString) {
            byteString.getClass();
            this.authBlob_ = byteString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceId(String str) {
            str.getClass();
            this.deviceId_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDeviceIdBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.deviceId_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003Ȉ", new Object[]{"username_", "authBlob_", "deviceId_"});
                case 3:
                    return new EncryptedStoredCredentials();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (EncryptedStoredCredentials.class) {
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

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
        public ByteString getAuthBlob() {
            return this.authBlob_;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
        public String getDeviceId() {
            return this.deviceId_;
        }

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
        public ByteString getDeviceIdBytes() {
            return ByteString.d(this.deviceId_);
        }

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
        public String getUsername() {
            return this.username_;
        }

        @Override // com.spotify.connectivity.auth.common.esperanto.proto.EsEncryptedStoredCredentials.EncryptedStoredCredentialsOrBuilder
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

        public static Builder newBuilder(EncryptedStoredCredentials encryptedStoredCredentials) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(encryptedStoredCredentials);
        }

        public static EncryptedStoredCredentials parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (EncryptedStoredCredentials) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static EncryptedStoredCredentials parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static EncryptedStoredCredentials parseFrom(ByteString byteString) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static EncryptedStoredCredentials parseFrom(ByteString byteString, rl0 rl0Var) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static EncryptedStoredCredentials parseFrom(byte[] bArr) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static EncryptedStoredCredentials parseFrom(byte[] bArr, rl0 rl0Var) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static EncryptedStoredCredentials parseFrom(InputStream inputStream) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static EncryptedStoredCredentials parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static EncryptedStoredCredentials parseFrom(au auVar) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static EncryptedStoredCredentials parseFrom(au auVar, rl0 rl0Var) {
            return (EncryptedStoredCredentials) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface EncryptedStoredCredentialsOrBuilder extends yj1 {
        ByteString getAuthBlob();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getDeviceId();

        ByteString getDeviceIdBytes();

        String getUsername();

        ByteString getUsernameBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsEncryptedStoredCredentials() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
