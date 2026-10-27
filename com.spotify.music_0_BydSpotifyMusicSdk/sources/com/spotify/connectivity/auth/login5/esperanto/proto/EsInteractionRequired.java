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
public final class EsInteractionRequired {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired$1, reason: invalid class name */
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

    public static final class InteractionRequired extends i implements InteractionRequiredOrBuilder {
        public static final int CONTEXT_FIELD_NUMBER = 1;
        private static final InteractionRequired DEFAULT_INSTANCE;
        public static final int FINISH_URL_FIELD_NUMBER = 3;
        public static final int INTERACTION_URL_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER;
        private EsAuthenticateContext.AuthenticateContext context_;
        private String interactionUrl_ = "";
        private String finishUrl_ = "";

        public static final class Builder extends h01 implements InteractionRequiredOrBuilder {
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
                ((InteractionRequired) this.instance).clearContext();
                return this;
            }

            public Builder clearFinishUrl() {
                copyOnWrite();
                ((InteractionRequired) this.instance).clearFinishUrl();
                return this;
            }

            public Builder clearInteractionUrl() {
                copyOnWrite();
                ((InteractionRequired) this.instance).clearInteractionUrl();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
            public EsAuthenticateContext.AuthenticateContext getContext() {
                return ((InteractionRequired) this.instance).getContext();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
            public String getFinishUrl() {
                return ((InteractionRequired) this.instance).getFinishUrl();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
            public ByteString getFinishUrlBytes() {
                return ((InteractionRequired) this.instance).getFinishUrlBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
            public String getInteractionUrl() {
                return ((InteractionRequired) this.instance).getInteractionUrl();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
            public ByteString getInteractionUrlBytes() {
                return ((InteractionRequired) this.instance).getInteractionUrlBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
            public boolean hasContext() {
                return ((InteractionRequired) this.instance).hasContext();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((InteractionRequired) this.instance).mergeContext(authenticateContext);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((InteractionRequired) this.instance).setContext(authenticateContext);
                return this;
            }

            public Builder setFinishUrl(String str) {
                copyOnWrite();
                ((InteractionRequired) this.instance).setFinishUrl(str);
                return this;
            }

            public Builder setFinishUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((InteractionRequired) this.instance).setFinishUrlBytes(byteString);
                return this;
            }

            public Builder setInteractionUrl(String str) {
                copyOnWrite();
                ((InteractionRequired) this.instance).setInteractionUrl(str);
                return this;
            }

            public Builder setInteractionUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((InteractionRequired) this.instance).setInteractionUrlBytes(byteString);
                return this;
            }

            private Builder() {
                super(InteractionRequired.DEFAULT_INSTANCE);
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
                ((InteractionRequired) this.instance).setContext((EsAuthenticateContext.AuthenticateContext) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m120mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m121mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m122mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            InteractionRequired interactionRequired = new InteractionRequired();
            DEFAULT_INSTANCE = interactionRequired;
            i.registerDefaultInstance(InteractionRequired.class, interactionRequired);
        }

        private InteractionRequired() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContext() {
            this.context_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearFinishUrl() {
            this.finishUrl_ = getDefaultInstance().getFinishUrl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInteractionUrl() {
            this.interactionUrl_ = getDefaultInstance().getInteractionUrl();
        }

        public static InteractionRequired getDefaultInstance() {
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

        public static InteractionRequired parseDelimitedFrom(InputStream inputStream) {
            return (InteractionRequired) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequired parseFrom(ByteBuffer byteBuffer) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            this.context_ = authenticateContext;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFinishUrl(String str) {
            str.getClass();
            this.finishUrl_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFinishUrlBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.finishUrl_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionUrl(String str) {
            str.getClass();
            this.interactionUrl_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionUrlBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.interactionUrl_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002Ȉ\u0003Ȉ", new Object[]{"context_", "interactionUrl_", "finishUrl_"});
                case 3:
                    return new InteractionRequired();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (InteractionRequired.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
        public EsAuthenticateContext.AuthenticateContext getContext() {
            EsAuthenticateContext.AuthenticateContext authenticateContext = this.context_;
            return authenticateContext == null ? EsAuthenticateContext.AuthenticateContext.getDefaultInstance() : authenticateContext;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
        public String getFinishUrl() {
            return this.finishUrl_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
        public ByteString getFinishUrlBytes() {
            return ByteString.d(this.finishUrl_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
        public String getInteractionUrl() {
            return this.interactionUrl_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
        public ByteString getInteractionUrlBytes() {
            return ByteString.d(this.interactionUrl_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredOrBuilder
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

        public static Builder newBuilder(InteractionRequired interactionRequired) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(interactionRequired);
        }

        public static InteractionRequired parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequired) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequired parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static InteractionRequired parseFrom(ByteString byteString) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InteractionRequired parseFrom(ByteString byteString, rl0 rl0Var) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static InteractionRequired parseFrom(byte[] bArr) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InteractionRequired parseFrom(byte[] bArr, rl0 rl0Var) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static InteractionRequired parseFrom(InputStream inputStream) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequired parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequired parseFrom(au auVar) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static InteractionRequired parseFrom(au auVar, rl0 rl0Var) {
            return (InteractionRequired) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface InteractionRequiredOrBuilder extends yj1 {
        EsAuthenticateContext.AuthenticateContext getContext();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getFinishUrl();

        ByteString getFinishUrlBytes();

        String getInteractionUrl();

        ByteString getInteractionUrlBytes();

        boolean hasContext();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class InteractionRequiredProceedError extends i implements InteractionRequiredProceedErrorOrBuilder {
        private static final InteractionRequiredProceedError DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER;
        private int error_;

        public static final class Builder extends h01 implements InteractionRequiredProceedErrorOrBuilder {
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
                ((InteractionRequiredProceedError) this.instance).clearError();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedErrorOrBuilder
            public Error getError() {
                return ((InteractionRequiredProceedError) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedErrorOrBuilder
            public int getErrorValue() {
                return ((InteractionRequiredProceedError) this.instance).getErrorValue();
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
                ((InteractionRequiredProceedError) this.instance).setError(error);
                return this;
            }

            public Builder setErrorValue(int i) {
                copyOnWrite();
                ((InteractionRequiredProceedError) this.instance).setErrorValue(i);
                return this;
            }

            private Builder() {
                super(InteractionRequiredProceedError.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m123mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m124mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m125mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum Error implements n51 {
            unknownContext(0),
            UNRECOGNIZED(-1);

            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedError.Error.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public Error m126findValueByNumber(int i) {
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
            InteractionRequiredProceedError interactionRequiredProceedError = new InteractionRequiredProceedError();
            DEFAULT_INSTANCE = interactionRequiredProceedError;
            i.registerDefaultInstance(InteractionRequiredProceedError.class, interactionRequiredProceedError);
        }

        private InteractionRequiredProceedError() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            this.error_ = 0;
        }

        public static InteractionRequiredProceedError getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static InteractionRequiredProceedError parseDelimitedFrom(InputStream inputStream) {
            return (InteractionRequiredProceedError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedError parseFrom(ByteBuffer byteBuffer) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
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
                    return new InteractionRequiredProceedError();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (InteractionRequiredProceedError.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedErrorOrBuilder
        public Error getError() {
            Error errorForNumber = Error.forNumber(this.error_);
            return errorForNumber == null ? Error.UNRECOGNIZED : errorForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedErrorOrBuilder
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

        public static Builder newBuilder(InteractionRequiredProceedError interactionRequiredProceedError) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(interactionRequiredProceedError);
        }

        public static InteractionRequiredProceedError parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedError) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedError parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static InteractionRequiredProceedError parseFrom(ByteString byteString) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InteractionRequiredProceedError parseFrom(ByteString byteString, rl0 rl0Var) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static InteractionRequiredProceedError parseFrom(byte[] bArr) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InteractionRequiredProceedError parseFrom(byte[] bArr, rl0 rl0Var) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static InteractionRequiredProceedError parseFrom(InputStream inputStream) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedError parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedError parseFrom(au auVar) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static InteractionRequiredProceedError parseFrom(au auVar, rl0 rl0Var) {
            return (InteractionRequiredProceedError) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface InteractionRequiredProceedErrorOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        InteractionRequiredProceedError.Error getError();

        int getErrorValue();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class InteractionRequiredProceedRequest extends i implements InteractionRequiredProceedRequestOrBuilder {
        public static final int CONTEXT_FIELD_NUMBER = 1;
        private static final InteractionRequiredProceedRequest DEFAULT_INSTANCE;
        public static final int INTERACTION_REF_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int SERVER_HASH_FIELD_NUMBER = 3;
        private EsAuthenticateContext.AuthenticateContext context_;
        private String interactionRef_ = "";
        private String serverHash_ = "";

        public static final class Builder extends h01 implements InteractionRequiredProceedRequestOrBuilder {
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
                ((InteractionRequiredProceedRequest) this.instance).clearContext();
                return this;
            }

            public Builder clearInteractionRef() {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).clearInteractionRef();
                return this;
            }

            public Builder clearServerHash() {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).clearServerHash();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
            public EsAuthenticateContext.AuthenticateContext getContext() {
                return ((InteractionRequiredProceedRequest) this.instance).getContext();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
            public String getInteractionRef() {
                return ((InteractionRequiredProceedRequest) this.instance).getInteractionRef();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
            public ByteString getInteractionRefBytes() {
                return ((InteractionRequiredProceedRequest) this.instance).getInteractionRefBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
            public String getServerHash() {
                return ((InteractionRequiredProceedRequest) this.instance).getServerHash();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
            public ByteString getServerHashBytes() {
                return ((InteractionRequiredProceedRequest) this.instance).getServerHashBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
            public boolean hasContext() {
                return ((InteractionRequiredProceedRequest) this.instance).hasContext();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).mergeContext(authenticateContext);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).setContext(authenticateContext);
                return this;
            }

            public Builder setInteractionRef(String str) {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).setInteractionRef(str);
                return this;
            }

            public Builder setInteractionRefBytes(ByteString byteString) {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).setInteractionRefBytes(byteString);
                return this;
            }

            public Builder setServerHash(String str) {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).setServerHash(str);
                return this;
            }

            public Builder setServerHashBytes(ByteString byteString) {
                copyOnWrite();
                ((InteractionRequiredProceedRequest) this.instance).setServerHashBytes(byteString);
                return this;
            }

            private Builder() {
                super(InteractionRequiredProceedRequest.DEFAULT_INSTANCE);
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
                ((InteractionRequiredProceedRequest) this.instance).setContext((EsAuthenticateContext.AuthenticateContext) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m127mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m128mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m129mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            InteractionRequiredProceedRequest interactionRequiredProceedRequest = new InteractionRequiredProceedRequest();
            DEFAULT_INSTANCE = interactionRequiredProceedRequest;
            i.registerDefaultInstance(InteractionRequiredProceedRequest.class, interactionRequiredProceedRequest);
        }

        private InteractionRequiredProceedRequest() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearContext() {
            this.context_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearInteractionRef() {
            this.interactionRef_ = getDefaultInstance().getInteractionRef();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearServerHash() {
            this.serverHash_ = getDefaultInstance().getServerHash();
        }

        public static InteractionRequiredProceedRequest getDefaultInstance() {
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

        public static InteractionRequiredProceedRequest parseDelimitedFrom(InputStream inputStream) {
            return (InteractionRequiredProceedRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedRequest parseFrom(ByteBuffer byteBuffer) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setContext(EsAuthenticateContext.AuthenticateContext authenticateContext) {
            authenticateContext.getClass();
            this.context_ = authenticateContext;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionRef(String str) {
            str.getClass();
            this.interactionRef_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setInteractionRefBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.interactionRef_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setServerHash(String str) {
            str.getClass();
            this.serverHash_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setServerHashBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.serverHash_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002Ȉ\u0003Ȉ", new Object[]{"context_", "interactionRef_", "serverHash_"});
                case 3:
                    return new InteractionRequiredProceedRequest();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (InteractionRequiredProceedRequest.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
        public EsAuthenticateContext.AuthenticateContext getContext() {
            EsAuthenticateContext.AuthenticateContext authenticateContext = this.context_;
            return authenticateContext == null ? EsAuthenticateContext.AuthenticateContext.getDefaultInstance() : authenticateContext;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
        public String getInteractionRef() {
            return this.interactionRef_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
        public ByteString getInteractionRefBytes() {
            return ByteString.d(this.interactionRef_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
        public String getServerHash() {
            return this.serverHash_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
        public ByteString getServerHashBytes() {
            return ByteString.d(this.serverHash_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedRequestOrBuilder
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

        public static Builder newBuilder(InteractionRequiredProceedRequest interactionRequiredProceedRequest) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(interactionRequiredProceedRequest);
        }

        public static InteractionRequiredProceedRequest parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedRequest) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedRequest parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static InteractionRequiredProceedRequest parseFrom(ByteString byteString) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InteractionRequiredProceedRequest parseFrom(ByteString byteString, rl0 rl0Var) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static InteractionRequiredProceedRequest parseFrom(byte[] bArr) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InteractionRequiredProceedRequest parseFrom(byte[] bArr, rl0 rl0Var) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static InteractionRequiredProceedRequest parseFrom(InputStream inputStream) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedRequest parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedRequest parseFrom(au auVar) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static InteractionRequiredProceedRequest parseFrom(au auVar, rl0 rl0Var) {
            return (InteractionRequiredProceedRequest) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface InteractionRequiredProceedRequestOrBuilder extends yj1 {
        EsAuthenticateContext.AuthenticateContext getContext();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getInteractionRef();

        ByteString getInteractionRefBytes();

        String getServerHash();

        ByteString getServerHashBytes();

        boolean hasContext();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class InteractionRequiredProceedResult extends i implements InteractionRequiredProceedResultOrBuilder {
        private static final InteractionRequiredProceedResult DEFAULT_INSTANCE;
        public static final int ERROR_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER = null;
        public static final int SUCCESS_FIELD_NUMBER = 1;
        private int resultCase_ = 0;
        private Object result_;

        public static final class Builder extends h01 implements InteractionRequiredProceedResultOrBuilder {
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
                ((InteractionRequiredProceedResult) this.instance).clearError();
                return this;
            }

            public Builder clearResult() {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).clearResult();
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).clearSuccess();
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

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
            public InteractionRequiredProceedError getError() {
                return ((InteractionRequiredProceedResult) this.instance).getError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
            public ResultCase getResultCase() {
                return ((InteractionRequiredProceedResult) this.instance).getResultCase();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
            public InteractionRequiredProceedSuccess getSuccess() {
                return ((InteractionRequiredProceedResult) this.instance).getSuccess();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
            public boolean hasError() {
                return ((InteractionRequiredProceedResult) this.instance).hasError();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
            public boolean hasSuccess() {
                return ((InteractionRequiredProceedResult) this.instance).hasSuccess();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            public Builder mergeError(InteractionRequiredProceedError interactionRequiredProceedError) {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).mergeError(interactionRequiredProceedError);
                return this;
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder mergeSuccess(InteractionRequiredProceedSuccess interactionRequiredProceedSuccess) {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).mergeSuccess(interactionRequiredProceedSuccess);
                return this;
            }

            public Builder setError(InteractionRequiredProceedError interactionRequiredProceedError) {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).setError(interactionRequiredProceedError);
                return this;
            }

            public Builder setSuccess(InteractionRequiredProceedSuccess interactionRequiredProceedSuccess) {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).setSuccess(interactionRequiredProceedSuccess);
                return this;
            }

            private Builder() {
                super(InteractionRequiredProceedResult.DEFAULT_INSTANCE);
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

            public Builder setError(InteractionRequiredProceedError.Builder builder) {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).setError((InteractionRequiredProceedError) builder.build());
                return this;
            }

            public Builder setSuccess(InteractionRequiredProceedSuccess.Builder builder) {
                copyOnWrite();
                ((InteractionRequiredProceedResult) this.instance).setSuccess((InteractionRequiredProceedSuccess) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m130mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m131mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m132mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
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
            InteractionRequiredProceedResult interactionRequiredProceedResult = new InteractionRequiredProceedResult();
            DEFAULT_INSTANCE = interactionRequiredProceedResult;
            i.registerDefaultInstance(InteractionRequiredProceedResult.class, interactionRequiredProceedResult);
        }

        private InteractionRequiredProceedResult() {
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

        public static InteractionRequiredProceedResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeError(InteractionRequiredProceedError interactionRequiredProceedError) {
            interactionRequiredProceedError.getClass();
            if (this.resultCase_ != 2 || this.result_ == InteractionRequiredProceedError.getDefaultInstance()) {
                this.result_ = interactionRequiredProceedError;
            } else {
                this.result_ = ((InteractionRequiredProceedError.Builder) InteractionRequiredProceedError.newBuilder((InteractionRequiredProceedError) this.result_).mergeFrom((i) interactionRequiredProceedError)).buildPartial();
            }
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeSuccess(InteractionRequiredProceedSuccess interactionRequiredProceedSuccess) {
            interactionRequiredProceedSuccess.getClass();
            if (this.resultCase_ != 1 || this.result_ == InteractionRequiredProceedSuccess.getDefaultInstance()) {
                this.result_ = interactionRequiredProceedSuccess;
            } else {
                this.result_ = ((InteractionRequiredProceedSuccess.Builder) InteractionRequiredProceedSuccess.newBuilder((InteractionRequiredProceedSuccess) this.result_).mergeFrom((i) interactionRequiredProceedSuccess)).buildPartial();
            }
            this.resultCase_ = 1;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static InteractionRequiredProceedResult parseDelimitedFrom(InputStream inputStream) {
            return (InteractionRequiredProceedResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedResult parseFrom(ByteBuffer byteBuffer) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setError(InteractionRequiredProceedError interactionRequiredProceedError) {
            interactionRequiredProceedError.getClass();
            this.result_ = interactionRequiredProceedError;
            this.resultCase_ = 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuccess(InteractionRequiredProceedSuccess interactionRequiredProceedSuccess) {
            interactionRequiredProceedSuccess.getClass();
            this.result_ = interactionRequiredProceedSuccess;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"result_", "resultCase_", InteractionRequiredProceedSuccess.class, InteractionRequiredProceedError.class});
                case 3:
                    return new InteractionRequiredProceedResult();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (InteractionRequiredProceedResult.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
        public InteractionRequiredProceedError getError() {
            return this.resultCase_ == 2 ? (InteractionRequiredProceedError) this.result_ : InteractionRequiredProceedError.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
        public ResultCase getResultCase() {
            return ResultCase.forNumber(this.resultCase_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
        public InteractionRequiredProceedSuccess getSuccess() {
            return this.resultCase_ == 1 ? (InteractionRequiredProceedSuccess) this.result_ : InteractionRequiredProceedSuccess.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
        public boolean hasError() {
            return this.resultCase_ == 2;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsInteractionRequired.InteractionRequiredProceedResultOrBuilder
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

        public static Builder newBuilder(InteractionRequiredProceedResult interactionRequiredProceedResult) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(interactionRequiredProceedResult);
        }

        public static InteractionRequiredProceedResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static InteractionRequiredProceedResult parseFrom(ByteString byteString) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InteractionRequiredProceedResult parseFrom(ByteString byteString, rl0 rl0Var) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static InteractionRequiredProceedResult parseFrom(byte[] bArr) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InteractionRequiredProceedResult parseFrom(byte[] bArr, rl0 rl0Var) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static InteractionRequiredProceedResult parseFrom(InputStream inputStream) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedResult parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedResult parseFrom(au auVar) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static InteractionRequiredProceedResult parseFrom(au auVar, rl0 rl0Var) {
            return (InteractionRequiredProceedResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface InteractionRequiredProceedResultOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        InteractionRequiredProceedError getError();

        InteractionRequiredProceedResult.ResultCase getResultCase();

        InteractionRequiredProceedSuccess getSuccess();

        boolean hasError();

        boolean hasSuccess();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class InteractionRequiredProceedSuccess extends i implements InteractionRequiredProceedSuccessOrBuilder {
        private static final InteractionRequiredProceedSuccess DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;

        public static final class Builder extends h01 implements InteractionRequiredProceedSuccessOrBuilder {
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
                super(InteractionRequiredProceedSuccess.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m133mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m134mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m135mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            InteractionRequiredProceedSuccess interactionRequiredProceedSuccess = new InteractionRequiredProceedSuccess();
            DEFAULT_INSTANCE = interactionRequiredProceedSuccess;
            i.registerDefaultInstance(InteractionRequiredProceedSuccess.class, interactionRequiredProceedSuccess);
        }

        private InteractionRequiredProceedSuccess() {
        }

        public static InteractionRequiredProceedSuccess getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static InteractionRequiredProceedSuccess parseDelimitedFrom(InputStream inputStream) {
            return (InteractionRequiredProceedSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedSuccess parseFrom(ByteBuffer byteBuffer) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
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
                    return new InteractionRequiredProceedSuccess();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (InteractionRequiredProceedSuccess.class) {
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

        public static Builder newBuilder(InteractionRequiredProceedSuccess interactionRequiredProceedSuccess) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(interactionRequiredProceedSuccess);
        }

        public static InteractionRequiredProceedSuccess parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedSuccess) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedSuccess parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static InteractionRequiredProceedSuccess parseFrom(ByteString byteString) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static InteractionRequiredProceedSuccess parseFrom(ByteString byteString, rl0 rl0Var) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static InteractionRequiredProceedSuccess parseFrom(byte[] bArr) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static InteractionRequiredProceedSuccess parseFrom(byte[] bArr, rl0 rl0Var) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static InteractionRequiredProceedSuccess parseFrom(InputStream inputStream) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static InteractionRequiredProceedSuccess parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static InteractionRequiredProceedSuccess parseFrom(au auVar) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static InteractionRequiredProceedSuccess parseFrom(au auVar, rl0 rl0Var) {
            return (InteractionRequiredProceedSuccess) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface InteractionRequiredProceedSuccessOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsInteractionRequired() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
