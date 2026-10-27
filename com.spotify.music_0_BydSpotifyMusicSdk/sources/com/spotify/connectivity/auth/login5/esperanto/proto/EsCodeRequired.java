package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.a;
import com.google.protobuf.i;
import com.spotify.connectivity.auth.login5.esperanto.proto.EsAuthenticateContext;
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
public final class EsCodeRequired {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired$1, reason: invalid class name */
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

    public static final class CodeRequired extends i implements CodeRequiredOrBuilder {
        public static final int CHALLENGECODE_FIELD_NUMBER = 2;
        public static final int CONTEXT_FIELD_NUMBER = 1;
        private static final CodeRequired DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private CodeRequiredCode challengeCode_;
        private EsAuthenticateContext.AuthenticateContext context_;

        public static final class Builder extends h01 implements CodeRequiredOrBuilder {
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

            public Builder clearChallengeCode() {
                copyOnWrite();
                ((CodeRequired) this.instance).clearChallengeCode();
                return this;
            }

            public Builder clearContext() {
                copyOnWrite();
                ((CodeRequired) this.instance).clearContext();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
            public CodeRequiredCode getChallengeCode() {
                return ((CodeRequired) this.instance).getChallengeCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
            public EsAuthenticateContext.AuthenticateContext getContext() {
                return ((CodeRequired) this.instance).getContext();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
            public boolean hasChallengeCode() {
                return ((CodeRequired) this.instance).hasChallengeCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
            public boolean hasContext() {
                return ((CodeRequired) this.instance).hasContext();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeChallengeCode(CodeRequiredCode codeRequiredCode) {
                copyOnWrite();
                ((CodeRequired) this.instance).mergeChallengeCode(codeRequiredCode);
                return this;
            }

            public Builder mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((CodeRequired) this.instance).mergeContext(authenticateContext);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setChallengeCode(CodeRequiredCode codeRequiredCode) {
                copyOnWrite();
                ((CodeRequired) this.instance).setChallengeCode(codeRequiredCode);
                return this;
            }

            public Builder setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((CodeRequired) this.instance).setContext(authenticateContext);
                return this;
            }

            private Builder() {
                super(CodeRequired.DEFAULT_INSTANCE);
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

            public Builder setChallengeCode(CodeRequiredCode.Builder builder) {
                copyOnWrite();
                ((CodeRequired) this.instance).setChallengeCode((CodeRequiredCode) builder.build());
                return this;
            }

            public Builder setContext(EsAuthenticateContext.AuthenticateContext.Builder builder) {
                copyOnWrite();
                ((CodeRequired) this.instance).setContext((EsAuthenticateContext.AuthenticateContext) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m87mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m88mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m89mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            CodeRequired codeRequired = new CodeRequired();
            DEFAULT_INSTANCE = codeRequired;
            i.registerDefaultInstance(CodeRequired.class, codeRequired);
        }

        private CodeRequired() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearChallengeCode() {
            this.challengeCode_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContext() {
            this.context_ = null;
        }

        public static CodeRequired getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeChallengeCode(CodeRequiredCode codeRequiredCode) {
            codeRequiredCode.getClass();
            CodeRequiredCode codeRequiredCode2 = this.challengeCode_;
            if (codeRequiredCode2 == null || codeRequiredCode2 == CodeRequiredCode.getDefaultInstance()) {
                this.challengeCode_ = codeRequiredCode;
            } else {
                this.challengeCode_ = (CodeRequiredCode) ((CodeRequiredCode.Builder) CodeRequiredCode.newBuilder(this.challengeCode_).mergeFrom((i) codeRequiredCode)).buildPartial();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            EsAuthenticateContext.AuthenticateContext authenticateContext2 = this.context_;
            if (authenticateContext2 == null || authenticateContext2 == EsAuthenticateContext.AuthenticateContext.getDefaultInstance()) {
                this.context_ = authenticateContext;
            } else {
                this.context_ = (EsAuthenticateContext.AuthenticateContext) ((EsAuthenticateContext.AuthenticateContext.Builder) EsAuthenticateContext.AuthenticateContext.newBuilder(this.context_).mergeFrom((i) authenticateContext)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequired parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequired) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequired parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChallengeCode(CodeRequiredCode codeRequiredCode) {
            codeRequiredCode.getClass();
            this.challengeCode_ = codeRequiredCode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            this.context_ = authenticateContext;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"context_", "challengeCode_"});
                case 3:
                    return new CodeRequired();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequired.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
        public CodeRequiredCode getChallengeCode() {
            CodeRequiredCode codeRequiredCode = this.challengeCode_;
            return codeRequiredCode == null ? CodeRequiredCode.getDefaultInstance() : codeRequiredCode;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
        public EsAuthenticateContext.AuthenticateContext getContext() {
            EsAuthenticateContext.AuthenticateContext authenticateContext = this.context_;
            return authenticateContext == null ? EsAuthenticateContext.AuthenticateContext.getDefaultInstance() : authenticateContext;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
        public boolean hasChallengeCode() {
            return this.challengeCode_ != null;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredOrBuilder
        public boolean hasContext() {
            return this.context_ != null;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequired codeRequired) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequired);
        }

        public static CodeRequired parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequired) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequired parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequired parseFrom(ByteString byteString) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequired parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequired parseFrom(byte[] bArr) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequired parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequired parseFrom(InputStream inputStream) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequired parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequired parseFrom(au auVar) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequired parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequired) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public static final class CodeRequiredCode extends i implements CodeRequiredCodeOrBuilder {
        public static final int CANONICALPHONENUMBER_FIELD_NUMBER = 3;
        public static final int CODELENGTH_FIELD_NUMBER = 2;
        private static final CodeRequiredCode DEFAULT_INSTANCE;
        public static final int EXPIRES_AT_FIELD_NUMBER = 4;
        public static final int EXPIRES_IN_FIELD_NUMBER = 5;
        private static volatile ds1 PARSER = null;
        public static final int RETRY_NUMBER_FIELD_NUMBER = 6;
        public static final int TYPE_FIELD_NUMBER = 1;
        private String canonicalPhoneNumber_ = "";
        private int codeLength_;
        private long expiresAt_;
        private long expiresIn_;
        private int retryNumber_;
        private int type_;

        public static final class Builder extends h01 implements CodeRequiredCodeOrBuilder {
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

            public Builder clearCanonicalPhoneNumber() {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).clearCanonicalPhoneNumber();
                return this;
            }

            public Builder clearCodeLength() {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).clearCodeLength();
                return this;
            }

            public Builder clearExpiresAt() {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).clearExpiresAt();
                return this;
            }

            public Builder clearExpiresIn() {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).clearExpiresIn();
                return this;
            }

            public Builder clearRetryNumber() {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).clearRetryNumber();
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).clearType();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public String getCanonicalPhoneNumber() {
                return ((CodeRequiredCode) this.instance).getCanonicalPhoneNumber();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public ByteString getCanonicalPhoneNumberBytes() {
                return ((CodeRequiredCode) this.instance).getCanonicalPhoneNumberBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public int getCodeLength() {
                return ((CodeRequiredCode) this.instance).getCodeLength();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public long getExpiresAt() {
                return ((CodeRequiredCode) this.instance).getExpiresAt();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public long getExpiresIn() {
                return ((CodeRequiredCode) this.instance).getExpiresIn();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public int getRetryNumber() {
                return ((CodeRequiredCode) this.instance).getRetryNumber();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public Type getType() {
                return ((CodeRequiredCode) this.instance).getType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
            public int getTypeValue() {
                return ((CodeRequiredCode) this.instance).getTypeValue();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setCanonicalPhoneNumber(String str) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setCanonicalPhoneNumber(str);
                return this;
            }

            public Builder setCanonicalPhoneNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setCanonicalPhoneNumberBytes(byteString);
                return this;
            }

            public Builder setCodeLength(int i) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setCodeLength(i);
                return this;
            }

            public Builder setExpiresAt(long j) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setExpiresAt(j);
                return this;
            }

            public Builder setExpiresIn(long j) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setExpiresIn(j);
                return this;
            }

            public Builder setRetryNumber(int i) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setRetryNumber(i);
                return this;
            }

            public Builder setType(Type type) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setType(type);
                return this;
            }

            public Builder setTypeValue(int i) {
                copyOnWrite();
                ((CodeRequiredCode) this.instance).setTypeValue(i);
                return this;
            }

            private Builder() {
                super(CodeRequiredCode.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m90mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m91mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m92mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum Type implements n51 {
            unknown(0),
            smsCode(1),
            UNRECOGNIZED(-1);

            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCode.Type.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public Type m93findValueByNumber(int i) {
                    return Type.forNumber(i);
                }
            };
            public static final int smsCode_VALUE = 1;
            public static final int unknown_VALUE = 0;
            private final int value;

            public static final class TypeVerifier implements p51 {
                static final p51 INSTANCE = new TypeVerifier();

                private TypeVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return Type.forNumber(i) != null;
                }
            }

            Type(int i) {
                this.value = i;
            }

            public static Type forNumber(int i) {
                if (i == 0) {
                    return unknown;
                }
                if (i != 1) {
                    return null;
                }
                return smsCode;
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return TypeVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Type valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            CodeRequiredCode codeRequiredCode = new CodeRequiredCode();
            DEFAULT_INSTANCE = codeRequiredCode;
            i.registerDefaultInstance(CodeRequiredCode.class, codeRequiredCode);
        }

        private CodeRequiredCode() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCanonicalPhoneNumber() {
            this.canonicalPhoneNumber_ = getDefaultInstance().getCanonicalPhoneNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCodeLength() {
            this.codeLength_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExpiresAt() {
            this.expiresAt_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearExpiresIn() {
            this.expiresIn_ = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearRetryNumber() {
            this.retryNumber_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearType() {
            this.type_ = 0;
        }

        public static CodeRequiredCode getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredCode parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredCode) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredCode parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCanonicalPhoneNumber(String str) {
            str.getClass();
            this.canonicalPhoneNumber_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCanonicalPhoneNumberBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.canonicalPhoneNumber_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCodeLength(int i) {
            this.codeLength_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExpiresAt(long j) {
            this.expiresAt_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setExpiresIn(long j) {
            this.expiresIn_ = j;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRetryNumber(int i) {
            this.retryNumber_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setType(Type type) {
            this.type_ = type.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setTypeValue(int i) {
            this.type_ = i;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001\f\u0002\u0004\u0003Ȉ\u0004\u0002\u0005\u0002\u0006\u0004", new Object[]{"type_", "codeLength_", "canonicalPhoneNumber_", "expiresAt_", "expiresIn_", "retryNumber_"});
                case 3:
                    return new CodeRequiredCode();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredCode.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public String getCanonicalPhoneNumber() {
            return this.canonicalPhoneNumber_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public ByteString getCanonicalPhoneNumberBytes() {
            return ByteString.d(this.canonicalPhoneNumber_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public int getCodeLength() {
            return this.codeLength_;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public long getExpiresAt() {
            return this.expiresAt_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public long getExpiresIn() {
            return this.expiresIn_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public int getRetryNumber() {
            return this.retryNumber_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public Type getType() {
            Type typeForNumber = Type.forNumber(this.type_);
            return typeForNumber == null ? Type.UNRECOGNIZED : typeForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredCodeOrBuilder
        public int getTypeValue() {
            return this.type_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredCode codeRequiredCode) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredCode);
        }

        public static CodeRequiredCode parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredCode) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredCode parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredCode parseFrom(ByteString byteString) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredCode parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredCode parseFrom(byte[] bArr) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredCode parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredCode parseFrom(InputStream inputStream) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredCode parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredCode parseFrom(au auVar) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredCode parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredCode) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredCodeOrBuilder extends yj1 {
        String getCanonicalPhoneNumber();

        ByteString getCanonicalPhoneNumberBytes();

        int getCodeLength();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        long getExpiresAt();

        long getExpiresIn();

        int getRetryNumber();

        CodeRequiredCode.Type getType();

        int getTypeValue();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public interface CodeRequiredOrBuilder extends yj1 {
        CodeRequiredCode getChallengeCode();

        EsAuthenticateContext.AuthenticateContext getContext();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        boolean hasChallengeCode();

        boolean hasContext();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredProceedError extends i implements CodeRequiredProceedErrorOrBuilder {
        private static final CodeRequiredProceedError DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private int error_;

        public static final class Builder extends h01 implements CodeRequiredProceedErrorOrBuilder {
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
                ((CodeRequiredProceedError) this.instance).clearError();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedErrorOrBuilder
            public Error getError() {
                return ((CodeRequiredProceedError) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedErrorOrBuilder
            public int getErrorValue() {
                return ((CodeRequiredProceedError) this.instance).getErrorValue();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setError(Error error) {
                copyOnWrite();
                ((CodeRequiredProceedError) this.instance).setError(error);
                return this;
            }

            public Builder setErrorValue(int i) {
                copyOnWrite();
                ((CodeRequiredProceedError) this.instance).setErrorValue(i);
                return this;
            }

            private Builder() {
                super(CodeRequiredProceedError.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m94mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m95mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m96mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum Error implements n51 {
            unknownContext(0),
            UNRECOGNIZED(-1);

            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedError.Error.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public Error m97findValueByNumber(int i) {
                    return Error.forNumber(i);
                }
            };
            public static final int unknownContext_VALUE = 0;
            private final int value;

            public static final class ErrorVerifier implements p51 {
                static final p51 INSTANCE = new ErrorVerifier();

                private ErrorVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return Error.forNumber(i) != null;
                }
            }

            Error(int i) {
                this.value = i;
            }

            public static Error forNumber(int i) {
                if (i != 0) {
                    return null;
                }
                return unknownContext;
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return ErrorVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Error valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            CodeRequiredProceedError codeRequiredProceedError = new CodeRequiredProceedError();
            DEFAULT_INSTANCE = codeRequiredProceedError;
            i.registerDefaultInstance(CodeRequiredProceedError.class, codeRequiredProceedError);
        }

        private CodeRequiredProceedError() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            this.error_ = 0;
        }

        public static CodeRequiredProceedError getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredProceedError parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredProceedError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedError parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(Error error) {
            this.error_ = error.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorValue(int i) {
            this.error_ = i;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"error_"});
                case 3:
                    return new CodeRequiredProceedError();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredProceedError.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedErrorOrBuilder
        public Error getError() {
            Error errorForNumber = Error.forNumber(this.error_);
            return errorForNumber == null ? Error.UNRECOGNIZED : errorForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedErrorOrBuilder
        public int getErrorValue() {
            return this.error_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredProceedError codeRequiredProceedError) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredProceedError);
        }

        public static CodeRequiredProceedError parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedError parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredProceedError parseFrom(ByteString byteString) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredProceedError parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredProceedError parseFrom(byte[] bArr) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredProceedError parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredProceedError parseFrom(InputStream inputStream) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedError parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedError parseFrom(au auVar) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredProceedError parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredProceedErrorOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        CodeRequiredProceedError.Error getError();

        int getErrorValue();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredProceedRequest extends i implements CodeRequiredProceedRequestOrBuilder {
        public static final int CODE_FIELD_NUMBER = 2;
        public static final int CONTEXT_FIELD_NUMBER = 1;
        private static final CodeRequiredProceedRequest DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private String code_ = "";
        private EsAuthenticateContext.AuthenticateContext context_;

        public static final class Builder extends h01 implements CodeRequiredProceedRequestOrBuilder {
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
                ((CodeRequiredProceedRequest) this.instance).clearCode();
                return this;
            }

            public Builder clearContext() {
                copyOnWrite();
                ((CodeRequiredProceedRequest) this.instance).clearContext();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
            public String getCode() {
                return ((CodeRequiredProceedRequest) this.instance).getCode();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
            public ByteString getCodeBytes() {
                return ((CodeRequiredProceedRequest) this.instance).getCodeBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
            public EsAuthenticateContext.AuthenticateContext getContext() {
                return ((CodeRequiredProceedRequest) this.instance).getContext();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
            public boolean hasContext() {
                return ((CodeRequiredProceedRequest) this.instance).hasContext();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((CodeRequiredProceedRequest) this.instance).mergeContext(authenticateContext);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setCode(String str) {
                copyOnWrite();
                ((CodeRequiredProceedRequest) this.instance).setCode(str);
                return this;
            }

            public Builder setCodeBytes(ByteString byteString) {
                copyOnWrite();
                ((CodeRequiredProceedRequest) this.instance).setCodeBytes(byteString);
                return this;
            }

            public Builder setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((CodeRequiredProceedRequest) this.instance).setContext(authenticateContext);
                return this;
            }

            private Builder() {
                super(CodeRequiredProceedRequest.DEFAULT_INSTANCE);
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

            public Builder setContext(EsAuthenticateContext.AuthenticateContext.Builder builder) {
                copyOnWrite();
                ((CodeRequiredProceedRequest) this.instance).setContext((EsAuthenticateContext.AuthenticateContext) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m98mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m99mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m100mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            CodeRequiredProceedRequest codeRequiredProceedRequest = new CodeRequiredProceedRequest();
            DEFAULT_INSTANCE = codeRequiredProceedRequest;
            i.registerDefaultInstance(CodeRequiredProceedRequest.class, codeRequiredProceedRequest);
        }

        private CodeRequiredProceedRequest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCode() {
            this.code_ = getDefaultInstance().getCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContext() {
            this.context_ = null;
        }

        public static CodeRequiredProceedRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            EsAuthenticateContext.AuthenticateContext authenticateContext2 = this.context_;
            if (authenticateContext2 == null || authenticateContext2 == EsAuthenticateContext.AuthenticateContext.getDefaultInstance()) {
                this.context_ = authenticateContext;
            } else {
                this.context_ = (EsAuthenticateContext.AuthenticateContext) ((EsAuthenticateContext.AuthenticateContext.Builder) EsAuthenticateContext.AuthenticateContext.newBuilder(this.context_).mergeFrom((i) authenticateContext)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredProceedRequest parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredProceedRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedRequest parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCode(String str) {
            str.getClass();
            this.code_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCodeBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.code_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            this.context_ = authenticateContext;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002Ȉ", new Object[]{"context_", "code_"});
                case 3:
                    return new CodeRequiredProceedRequest();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredProceedRequest.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
        public String getCode() {
            return this.code_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
        public ByteString getCodeBytes() {
            return ByteString.d(this.code_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
        public EsAuthenticateContext.AuthenticateContext getContext() {
            EsAuthenticateContext.AuthenticateContext authenticateContext = this.context_;
            return authenticateContext == null ? EsAuthenticateContext.AuthenticateContext.getDefaultInstance() : authenticateContext;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedRequestOrBuilder
        public boolean hasContext() {
            return this.context_ != null;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredProceedRequest codeRequiredProceedRequest) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredProceedRequest);
        }

        public static CodeRequiredProceedRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredProceedRequest parseFrom(ByteString byteString) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredProceedRequest parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredProceedRequest parseFrom(byte[] bArr) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredProceedRequest parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredProceedRequest parseFrom(InputStream inputStream) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedRequest parseFrom(au auVar) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredProceedRequest parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredProceedRequestOrBuilder extends yj1 {
        String getCode();

        ByteString getCodeBytes();

        EsAuthenticateContext.AuthenticateContext getContext();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        boolean hasContext();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredProceedResult extends i implements CodeRequiredProceedResultOrBuilder {
        private static final CodeRequiredProceedResult DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int SUCCESS_FIELD_NUMBER = 1;
        private int resultCase_ = 0;
        private Object result_;

        public static final class Builder extends h01 implements CodeRequiredProceedResultOrBuilder {
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
                ((CodeRequiredProceedResult) this.instance).clearError();
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).clearResult();
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).clearSuccess();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
            public CodeRequiredProceedError getError() {
                return ((CodeRequiredProceedResult) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
            public ResultCase getResultCase() {
                return ((CodeRequiredProceedResult) this.instance).getResultCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
            public CodeRequiredProceedSuccess getSuccess() {
                return ((CodeRequiredProceedResult) this.instance).getSuccess();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
            public boolean hasError() {
                return ((CodeRequiredProceedResult) this.instance).hasError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
            public boolean hasSuccess() {
                return ((CodeRequiredProceedResult) this.instance).hasSuccess();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeError(CodeRequiredProceedError codeRequiredProceedError) {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).mergeError(codeRequiredProceedError);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeSuccess(CodeRequiredProceedSuccess codeRequiredProceedSuccess) {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).mergeSuccess(codeRequiredProceedSuccess);
                return this;
            }

            public Builder setError(CodeRequiredProceedError codeRequiredProceedError) {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).setError(codeRequiredProceedError);
                return this;
            }

            public Builder setSuccess(CodeRequiredProceedSuccess codeRequiredProceedSuccess) {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).setSuccess(codeRequiredProceedSuccess);
                return this;
            }

            private Builder() {
                super(CodeRequiredProceedResult.DEFAULT_INSTANCE);
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

            public Builder setError(CodeRequiredProceedError.Builder builder) {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).setError((CodeRequiredProceedError) builder.build());
                return this;
            }

            public Builder setSuccess(CodeRequiredProceedSuccess.Builder builder) {
                copyOnWrite();
                ((CodeRequiredProceedResult) this.instance).setSuccess((CodeRequiredProceedSuccess) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m101mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m102mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m103mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ResultCase {
            SUCCESS(1),
            ERROR(2),
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
                if (i != 2) {
                    return null;
                }
                return ERROR;
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
            CodeRequiredProceedResult codeRequiredProceedResult = new CodeRequiredProceedResult();
            DEFAULT_INSTANCE = codeRequiredProceedResult;
            i.registerDefaultInstance(CodeRequiredProceedResult.class, codeRequiredProceedResult);
        }

        private CodeRequiredProceedResult() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            if (this.resultCase_ == 2) {
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
        public void clearSuccess() {
            if (this.resultCase_ == 1) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        public static CodeRequiredProceedResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeError(CodeRequiredProceedError codeRequiredProceedError) {
            codeRequiredProceedError.getClass();
            if (this.resultCase_ != 2 || this.result_ == CodeRequiredProceedError.getDefaultInstance()) {
                this.result_ = codeRequiredProceedError;
            } else {
                this.result_ = ((CodeRequiredProceedError.Builder) CodeRequiredProceedError.newBuilder((CodeRequiredProceedError) this.result_).mergeFrom((i) codeRequiredProceedError)).buildPartial();
            }
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSuccess(CodeRequiredProceedSuccess codeRequiredProceedSuccess) {
            codeRequiredProceedSuccess.getClass();
            if (this.resultCase_ != 1 || this.result_ == CodeRequiredProceedSuccess.getDefaultInstance()) {
                this.result_ = codeRequiredProceedSuccess;
            } else {
                this.result_ = ((CodeRequiredProceedSuccess.Builder) CodeRequiredProceedSuccess.newBuilder((CodeRequiredProceedSuccess) this.result_).mergeFrom((i) codeRequiredProceedSuccess)).buildPartial();
            }
            this.resultCase_ = 1;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredProceedResult parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredProceedResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedResult parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(CodeRequiredProceedError codeRequiredProceedError) {
            codeRequiredProceedError.getClass();
            this.result_ = codeRequiredProceedError;
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuccess(CodeRequiredProceedSuccess codeRequiredProceedSuccess) {
            codeRequiredProceedSuccess.getClass();
            this.result_ = codeRequiredProceedSuccess;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"result_", "resultCase_", CodeRequiredProceedSuccess.class, CodeRequiredProceedError.class});
                case 3:
                    return new CodeRequiredProceedResult();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredProceedResult.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
        public CodeRequiredProceedError getError() {
            return this.resultCase_ == 2 ? (CodeRequiredProceedError) this.result_ : CodeRequiredProceedError.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
        public CodeRequiredProceedSuccess getSuccess() {
            return this.resultCase_ == 1 ? (CodeRequiredProceedSuccess) this.result_ : CodeRequiredProceedSuccess.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
        public boolean hasError() {
            return this.resultCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredProceedResultOrBuilder
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

        public static Builder newBuilder(CodeRequiredProceedResult codeRequiredProceedResult) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredProceedResult);
        }

        public static CodeRequiredProceedResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredProceedResult parseFrom(ByteString byteString) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredProceedResult parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredProceedResult parseFrom(byte[] bArr) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredProceedResult parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredProceedResult parseFrom(InputStream inputStream) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedResult parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedResult parseFrom(au auVar) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredProceedResult parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredProceedResultOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        CodeRequiredProceedError getError();

        CodeRequiredProceedResult.ResultCase getResultCase();

        CodeRequiredProceedSuccess getSuccess();

        boolean hasError();

        boolean hasSuccess();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredProceedSuccess extends i implements CodeRequiredProceedSuccessOrBuilder {
        private static final CodeRequiredProceedSuccess DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;

        public static final class Builder extends h01 implements CodeRequiredProceedSuccessOrBuilder {
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

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            private Builder() {
                super(CodeRequiredProceedSuccess.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m104mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m105mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m106mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            CodeRequiredProceedSuccess codeRequiredProceedSuccess = new CodeRequiredProceedSuccess();
            DEFAULT_INSTANCE = codeRequiredProceedSuccess;
            i.registerDefaultInstance(CodeRequiredProceedSuccess.class, codeRequiredProceedSuccess);
        }

        private CodeRequiredProceedSuccess() {
        }

        public static CodeRequiredProceedSuccess getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredProceedSuccess parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredProceedSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedSuccess parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case 3:
                    return new CodeRequiredProceedSuccess();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredProceedSuccess.class) {
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

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredProceedSuccess codeRequiredProceedSuccess) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredProceedSuccess);
        }

        public static CodeRequiredProceedSuccess parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedSuccess parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredProceedSuccess parseFrom(ByteString byteString) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredProceedSuccess parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredProceedSuccess parseFrom(byte[] bArr) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredProceedSuccess parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredProceedSuccess parseFrom(InputStream inputStream) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredProceedSuccess parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredProceedSuccess parseFrom(au auVar) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredProceedSuccess parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredProceedSuccessOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredResendError extends i implements CodeRequiredResendErrorOrBuilder {
        private static final CodeRequiredResendError DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private int error_;

        public static final class Builder extends h01 implements CodeRequiredResendErrorOrBuilder {
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
                ((CodeRequiredResendError) this.instance).clearError();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendErrorOrBuilder
            public Error getError() {
                return ((CodeRequiredResendError) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendErrorOrBuilder
            public int getErrorValue() {
                return ((CodeRequiredResendError) this.instance).getErrorValue();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setError(Error error) {
                copyOnWrite();
                ((CodeRequiredResendError) this.instance).setError(error);
                return this;
            }

            public Builder setErrorValue(int i) {
                copyOnWrite();
                ((CodeRequiredResendError) this.instance).setErrorValue(i);
                return this;
            }

            private Builder() {
                super(CodeRequiredResendError.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m107mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m108mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m109mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum Error implements n51 {
            unknownContext(0),
            UNRECOGNIZED(-1);

            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendError.Error.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public Error m110findValueByNumber(int i) {
                    return Error.forNumber(i);
                }
            };
            public static final int unknownContext_VALUE = 0;
            private final int value;

            public static final class ErrorVerifier implements p51 {
                static final p51 INSTANCE = new ErrorVerifier();

                private ErrorVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return Error.forNumber(i) != null;
                }
            }

            Error(int i) {
                this.value = i;
            }

            public static Error forNumber(int i) {
                if (i != 0) {
                    return null;
                }
                return unknownContext;
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return ErrorVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static Error valueOf(int i) {
                return forNumber(i);
            }
        }

        static {
            CodeRequiredResendError codeRequiredResendError = new CodeRequiredResendError();
            DEFAULT_INSTANCE = codeRequiredResendError;
            i.registerDefaultInstance(CodeRequiredResendError.class, codeRequiredResendError);
        }

        private CodeRequiredResendError() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            this.error_ = 0;
        }

        public static CodeRequiredResendError getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredResendError parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredResendError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendError parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(Error error) {
            this.error_ = error.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorValue(int i) {
            this.error_ = i;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"error_"});
                case 3:
                    return new CodeRequiredResendError();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredResendError.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendErrorOrBuilder
        public Error getError() {
            Error errorForNumber = Error.forNumber(this.error_);
            return errorForNumber == null ? Error.UNRECOGNIZED : errorForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendErrorOrBuilder
        public int getErrorValue() {
            return this.error_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredResendError codeRequiredResendError) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredResendError);
        }

        public static CodeRequiredResendError parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendError parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredResendError parseFrom(ByteString byteString) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredResendError parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredResendError parseFrom(byte[] bArr) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredResendError parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredResendError parseFrom(InputStream inputStream) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendError parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendError parseFrom(au auVar) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredResendError parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredResendError) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredResendErrorOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        CodeRequiredResendError.Error getError();

        int getErrorValue();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredResendRequest extends i implements CodeRequiredResendRequestOrBuilder {
        public static final int CONTEXT_FIELD_NUMBER = 1;
        private static final CodeRequiredResendRequest DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private EsAuthenticateContext.AuthenticateContext context_;

        public static final class Builder extends h01 implements CodeRequiredResendRequestOrBuilder {
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

            public Builder clearContext() {
                copyOnWrite();
                ((CodeRequiredResendRequest) this.instance).clearContext();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendRequestOrBuilder
            public EsAuthenticateContext.AuthenticateContext getContext() {
                return ((CodeRequiredResendRequest) this.instance).getContext();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendRequestOrBuilder
            public boolean hasContext() {
                return ((CodeRequiredResendRequest) this.instance).hasContext();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((CodeRequiredResendRequest) this.instance).mergeContext(authenticateContext);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((CodeRequiredResendRequest) this.instance).setContext(authenticateContext);
                return this;
            }

            private Builder() {
                super(CodeRequiredResendRequest.DEFAULT_INSTANCE);
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

            public Builder setContext(EsAuthenticateContext.AuthenticateContext.Builder builder) {
                copyOnWrite();
                ((CodeRequiredResendRequest) this.instance).setContext((EsAuthenticateContext.AuthenticateContext) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m111mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m112mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m113mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            CodeRequiredResendRequest codeRequiredResendRequest = new CodeRequiredResendRequest();
            DEFAULT_INSTANCE = codeRequiredResendRequest;
            i.registerDefaultInstance(CodeRequiredResendRequest.class, codeRequiredResendRequest);
        }

        private CodeRequiredResendRequest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContext() {
            this.context_ = null;
        }

        public static CodeRequiredResendRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            EsAuthenticateContext.AuthenticateContext authenticateContext2 = this.context_;
            if (authenticateContext2 == null || authenticateContext2 == EsAuthenticateContext.AuthenticateContext.getDefaultInstance()) {
                this.context_ = authenticateContext;
            } else {
                this.context_ = (EsAuthenticateContext.AuthenticateContext) ((EsAuthenticateContext.AuthenticateContext.Builder) EsAuthenticateContext.AuthenticateContext.newBuilder(this.context_).mergeFrom((i) authenticateContext)).buildPartial();
            }
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredResendRequest parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredResendRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendRequest parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            this.context_ = authenticateContext;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"context_"});
                case 3:
                    return new CodeRequiredResendRequest();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredResendRequest.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendRequestOrBuilder
        public EsAuthenticateContext.AuthenticateContext getContext() {
            EsAuthenticateContext.AuthenticateContext authenticateContext = this.context_;
            return authenticateContext == null ? EsAuthenticateContext.AuthenticateContext.getDefaultInstance() : authenticateContext;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendRequestOrBuilder
        public boolean hasContext() {
            return this.context_ != null;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredResendRequest codeRequiredResendRequest) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredResendRequest);
        }

        public static CodeRequiredResendRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredResendRequest parseFrom(ByteString byteString) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredResendRequest parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredResendRequest parseFrom(byte[] bArr) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredResendRequest parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredResendRequest parseFrom(InputStream inputStream) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendRequest parseFrom(au auVar) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredResendRequest parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredResendRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredResendRequestOrBuilder extends yj1 {
        EsAuthenticateContext.AuthenticateContext getContext();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        boolean hasContext();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredResendResult extends i implements CodeRequiredResendResultOrBuilder {
        private static final CodeRequiredResendResult DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int SUCCESS_FIELD_NUMBER = 1;
        private int resultCase_ = 0;
        private Object result_;

        public static final class Builder extends h01 implements CodeRequiredResendResultOrBuilder {
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
                ((CodeRequiredResendResult) this.instance).clearError();
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).clearResult();
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).clearSuccess();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
            public CodeRequiredResendError getError() {
                return ((CodeRequiredResendResult) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
            public ResultCase getResultCase() {
                return ((CodeRequiredResendResult) this.instance).getResultCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
            public CodeRequiredResendSuccess getSuccess() {
                return ((CodeRequiredResendResult) this.instance).getSuccess();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
            public boolean hasError() {
                return ((CodeRequiredResendResult) this.instance).hasError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
            public boolean hasSuccess() {
                return ((CodeRequiredResendResult) this.instance).hasSuccess();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeError(CodeRequiredResendError codeRequiredResendError) {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).mergeError(codeRequiredResendError);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeSuccess(CodeRequiredResendSuccess codeRequiredResendSuccess) {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).mergeSuccess(codeRequiredResendSuccess);
                return this;
            }

            public Builder setError(CodeRequiredResendError codeRequiredResendError) {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).setError(codeRequiredResendError);
                return this;
            }

            public Builder setSuccess(CodeRequiredResendSuccess codeRequiredResendSuccess) {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).setSuccess(codeRequiredResendSuccess);
                return this;
            }

            private Builder() {
                super(CodeRequiredResendResult.DEFAULT_INSTANCE);
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

            public Builder setError(CodeRequiredResendError.Builder builder) {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).setError((CodeRequiredResendError) builder.build());
                return this;
            }

            public Builder setSuccess(CodeRequiredResendSuccess.Builder builder) {
                copyOnWrite();
                ((CodeRequiredResendResult) this.instance).setSuccess((CodeRequiredResendSuccess) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m114mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m115mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m116mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ResultCase {
            SUCCESS(1),
            ERROR(2),
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
                if (i != 2) {
                    return null;
                }
                return ERROR;
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
            CodeRequiredResendResult codeRequiredResendResult = new CodeRequiredResendResult();
            DEFAULT_INSTANCE = codeRequiredResendResult;
            i.registerDefaultInstance(CodeRequiredResendResult.class, codeRequiredResendResult);
        }

        private CodeRequiredResendResult() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            if (this.resultCase_ == 2) {
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
        public void clearSuccess() {
            if (this.resultCase_ == 1) {
                this.resultCase_ = 0;
                this.result_ = null;
            }
        }

        public static CodeRequiredResendResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeError(CodeRequiredResendError codeRequiredResendError) {
            codeRequiredResendError.getClass();
            if (this.resultCase_ != 2 || this.result_ == CodeRequiredResendError.getDefaultInstance()) {
                this.result_ = codeRequiredResendError;
            } else {
                this.result_ = ((CodeRequiredResendError.Builder) CodeRequiredResendError.newBuilder((CodeRequiredResendError) this.result_).mergeFrom((i) codeRequiredResendError)).buildPartial();
            }
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSuccess(CodeRequiredResendSuccess codeRequiredResendSuccess) {
            codeRequiredResendSuccess.getClass();
            if (this.resultCase_ != 1 || this.result_ == CodeRequiredResendSuccess.getDefaultInstance()) {
                this.result_ = codeRequiredResendSuccess;
            } else {
                this.result_ = ((CodeRequiredResendSuccess.Builder) CodeRequiredResendSuccess.newBuilder((CodeRequiredResendSuccess) this.result_).mergeFrom((i) codeRequiredResendSuccess)).buildPartial();
            }
            this.resultCase_ = 1;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredResendResult parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredResendResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendResult parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(CodeRequiredResendError codeRequiredResendError) {
            codeRequiredResendError.getClass();
            this.result_ = codeRequiredResendError;
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuccess(CodeRequiredResendSuccess codeRequiredResendSuccess) {
            codeRequiredResendSuccess.getClass();
            this.result_ = codeRequiredResendSuccess;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"result_", "resultCase_", CodeRequiredResendSuccess.class, CodeRequiredResendError.class});
                case 3:
                    return new CodeRequiredResendResult();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredResendResult.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
        public CodeRequiredResendError getError() {
            return this.resultCase_ == 2 ? (CodeRequiredResendError) this.result_ : CodeRequiredResendError.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
        public CodeRequiredResendSuccess getSuccess() {
            return this.resultCase_ == 1 ? (CodeRequiredResendSuccess) this.result_ : CodeRequiredResendSuccess.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
        public boolean hasError() {
            return this.resultCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsCodeRequired.CodeRequiredResendResultOrBuilder
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

        public static Builder newBuilder(CodeRequiredResendResult codeRequiredResendResult) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredResendResult);
        }

        public static CodeRequiredResendResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredResendResult parseFrom(ByteString byteString) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredResendResult parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredResendResult parseFrom(byte[] bArr) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredResendResult parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredResendResult parseFrom(InputStream inputStream) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendResult parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendResult parseFrom(au auVar) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredResendResult parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredResendResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredResendResultOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        CodeRequiredResendError getError();

        CodeRequiredResendResult.ResultCase getResultCase();

        CodeRequiredResendSuccess getSuccess();

        boolean hasError();

        boolean hasSuccess();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class CodeRequiredResendSuccess extends i implements CodeRequiredResendSuccessOrBuilder {
        private static final CodeRequiredResendSuccess DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;

        public static final class Builder extends h01 implements CodeRequiredResendSuccessOrBuilder {
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

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            private Builder() {
                super(CodeRequiredResendSuccess.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m117mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m118mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m119mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            CodeRequiredResendSuccess codeRequiredResendSuccess = new CodeRequiredResendSuccess();
            DEFAULT_INSTANCE = codeRequiredResendSuccess;
            i.registerDefaultInstance(CodeRequiredResendSuccess.class, codeRequiredResendSuccess);
        }

        private CodeRequiredResendSuccess() {
        }

        public static CodeRequiredResendSuccess getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static CodeRequiredResendSuccess parseDelimitedFrom(InputStream inputStream) {
            return (CodeRequiredResendSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendSuccess parseFrom(ByteBuffer byteBuffer) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case 3:
                    return new CodeRequiredResendSuccess();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (CodeRequiredResendSuccess.class) {
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

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(CodeRequiredResendSuccess codeRequiredResendSuccess) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(codeRequiredResendSuccess);
        }

        public static CodeRequiredResendSuccess parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendSuccess parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static CodeRequiredResendSuccess parseFrom(ByteString byteString) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CodeRequiredResendSuccess parseFrom(ByteString byteString, rl0 rl0Var) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static CodeRequiredResendSuccess parseFrom(byte[] bArr) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CodeRequiredResendSuccess parseFrom(byte[] bArr, rl0 rl0Var) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static CodeRequiredResendSuccess parseFrom(InputStream inputStream) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CodeRequiredResendSuccess parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static CodeRequiredResendSuccess parseFrom(au auVar) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static CodeRequiredResendSuccess parseFrom(au auVar, rl0 rl0Var) {
            return (CodeRequiredResendSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface CodeRequiredResendSuccessOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsCodeRequired() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
