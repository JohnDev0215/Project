package com.spotify.connectivity.auth.esperanto.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.WireFormat$FieldType;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;
import p.au;
import p.ds1;
import p.h01;
import p.i01;
import p.n51;
import p.o51;
import p.oe1;
import p.p51;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSession {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.esperanto.proto.EsSession$1, reason: invalid class name */
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

    public static final class APPermanentErrorResult extends i implements APPermanentErrorResultOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        private static final APPermanentErrorResult DEFAULT_INSTANCE;
        public static final int GENERIC_ERROR_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER;
        private int errorCase_ = 0;
        private Object error_;

        public enum APPermanentError implements n51 {
            BAD_CREDENTIALS(0),
            REGION_MISMATCH(1),
            PASSWORD_CHANGED(2),
            UNRECOGNIZED(-1);

            public static final int BAD_CREDENTIALS_VALUE = 0;
            public static final int PASSWORD_CHANGED_VALUE = 2;
            public static final int REGION_MISMATCH_VALUE = 1;
            private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.APPermanentError.1
                /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
                public APPermanentError m9findValueByNumber(int i) {
                    return APPermanentError.forNumber(i);
                }
            };
            private final int value;

            public static final class APPermanentErrorVerifier implements p51 {
                static final p51 INSTANCE = new APPermanentErrorVerifier();

                private APPermanentErrorVerifier() {
                }

                @Override // p.p51
                public boolean isInRange(int i) {
                    return APPermanentError.forNumber(i) != null;
                }
            }

            APPermanentError(int i) {
                this.value = i;
            }

            public static APPermanentError forNumber(int i) {
                if (i == 0) {
                    return BAD_CREDENTIALS;
                }
                if (i == 1) {
                    return REGION_MISMATCH;
                }
                if (i != 2) {
                    return null;
                }
                return PASSWORD_CHANGED;
            }

            public static o51 internalGetValueMap() {
                return internalValueMap;
            }

            public static p51 internalGetVerifier() {
                return APPermanentErrorVerifier.INSTANCE;
            }

            @Override // p.n51
            public final int getNumber() {
                if (this != UNRECOGNIZED) {
                    return this.value;
                }
                throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
            }

            @Deprecated
            public static APPermanentError valueOf(int i) {
                return forNumber(i);
            }
        }

        public static final class Builder extends h01 implements APPermanentErrorResultOrBuilder {
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
                ((APPermanentErrorResult) this.instance).clearCode();
                return this;
            }

            public Builder clearError() {
                copyOnWrite();
                ((APPermanentErrorResult) this.instance).clearError();
                return this;
            }

            public Builder clearGenericError() {
                copyOnWrite();
                ((APPermanentErrorResult) this.instance).clearGenericError();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
            public APPermanentError getCode() {
                return ((APPermanentErrorResult) this.instance).getCode();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
            public int getCodeValue() {
                return ((APPermanentErrorResult) this.instance).getCodeValue();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
            public ErrorCase getErrorCase() {
                return ((APPermanentErrorResult) this.instance).getErrorCase();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
            public GenericError getGenericError() {
                return ((APPermanentErrorResult) this.instance).getGenericError();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
            public boolean hasCode() {
                return ((APPermanentErrorResult) this.instance).hasCode();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
            public boolean hasGenericError() {
                return ((APPermanentErrorResult) this.instance).hasGenericError();
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
                ((APPermanentErrorResult) this.instance).mergeGenericError(genericError);
                return this;
            }

            public Builder setCode(APPermanentError aPPermanentError) {
                copyOnWrite();
                ((APPermanentErrorResult) this.instance).setCode(aPPermanentError);
                return this;
            }

            public Builder setCodeValue(int i) {
                copyOnWrite();
                ((APPermanentErrorResult) this.instance).setCodeValue(i);
                return this;
            }

            public Builder setGenericError(GenericError genericError) {
                copyOnWrite();
                ((APPermanentErrorResult) this.instance).setGenericError(genericError);
                return this;
            }

            private Builder() {
                super(APPermanentErrorResult.DEFAULT_INSTANCE);
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
                ((APPermanentErrorResult) this.instance).setGenericError((GenericError) builder.build());
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
            public /* bridge */ /* synthetic */ vj1 m10mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m11mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m12mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public enum ErrorCase {
            CODE(1),
            GENERIC_ERROR(2),
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
                    return CODE;
                }
                if (i != 2) {
                    return null;
                }
                return GENERIC_ERROR;
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

                @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.GenericErrorOrBuilder
                public int getErrorCode() {
                    return ((GenericError) this.instance).getErrorCode();
                }

                @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.GenericErrorOrBuilder
                public String getErrorMessage() {
                    return ((GenericError) this.instance).getErrorMessage();
                }

                @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.GenericErrorOrBuilder
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
                public /* bridge */ /* synthetic */ vj1 m13mergeFrom(au auVar, rl0 rl0Var) {
                    return super.mergeFrom(auVar, rl0Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                    return super.mergeFrom(wj1Var);
                }

                public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                    return super.mergeFrom(bArr);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m14mergeFrom(byte[] bArr, int i, int i2) {
                    return super.mergeFrom(bArr, i, i2);
                }

                /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
                public /* bridge */ /* synthetic */ vj1 m15mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
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

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.GenericErrorOrBuilder
            public int getErrorCode() {
                return this.errorCode_;
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.GenericErrorOrBuilder
            public String getErrorMessage() {
                return this.errorMessage_;
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResult.GenericErrorOrBuilder
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

        static {
            APPermanentErrorResult aPPermanentErrorResult = new APPermanentErrorResult();
            DEFAULT_INSTANCE = aPPermanentErrorResult;
            i.registerDefaultInstance(APPermanentErrorResult.class, aPPermanentErrorResult);
        }

        private APPermanentErrorResult() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearCode() {
            if (this.errorCase_ == 1) {
                this.errorCase_ = 0;
                this.error_ = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearError() {
            this.errorCase_ = 0;
            this.error_ = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGenericError() {
            if (this.errorCase_ == 2) {
                this.errorCase_ = 0;
                this.error_ = null;
            }
        }

        public static APPermanentErrorResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void mergeGenericError(GenericError genericError) {
            genericError.getClass();
            if (this.errorCase_ != 2 || this.error_ == GenericError.getDefaultInstance()) {
                this.error_ = genericError;
            } else {
                this.error_ = ((GenericError.Builder) GenericError.newBuilder((GenericError) this.error_).mergeFrom((i) genericError)).buildPartial();
            }
            this.errorCase_ = 2;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static APPermanentErrorResult parseDelimitedFrom(InputStream inputStream) {
            return (APPermanentErrorResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static APPermanentErrorResult parseFrom(ByteBuffer byteBuffer) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCode(APPermanentError aPPermanentError) {
            this.error_ = Integer.valueOf(aPPermanentError.getNumber());
            this.errorCase_ = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setCodeValue(int i) {
            this.errorCase_ = 1;
            this.error_ = Integer.valueOf(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGenericError(GenericError genericError) {
            genericError.getClass();
            this.error_ = genericError;
            this.errorCase_ = 2;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001?\u0000\u0002<\u0000", new Object[]{"error_", "errorCase_", GenericError.class});
                case 3:
                    return new APPermanentErrorResult();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (APPermanentErrorResult.class) {
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

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
        public APPermanentError getCode() {
            if (this.errorCase_ != 1) {
                return APPermanentError.BAD_CREDENTIALS;
            }
            APPermanentError aPPermanentErrorForNumber = APPermanentError.forNumber(((Integer) this.error_).intValue());
            return aPPermanentErrorForNumber == null ? APPermanentError.UNRECOGNIZED : aPPermanentErrorForNumber;
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
        public int getCodeValue() {
            if (this.errorCase_ == 1) {
                return ((Integer) this.error_).intValue();
            }
            return 0;
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
        public ErrorCase getErrorCase() {
            return ErrorCase.forNumber(this.errorCase_);
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
        public GenericError getGenericError() {
            return this.errorCase_ == 2 ? (GenericError) this.error_ : GenericError.getDefaultInstance();
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
        public boolean hasCode() {
            return this.errorCase_ == 1;
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.APPermanentErrorResultOrBuilder
        public boolean hasGenericError() {
            return this.errorCase_ == 2;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(APPermanentErrorResult aPPermanentErrorResult) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(aPPermanentErrorResult);
        }

        public static APPermanentErrorResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (APPermanentErrorResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static APPermanentErrorResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static APPermanentErrorResult parseFrom(ByteString byteString) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static APPermanentErrorResult parseFrom(ByteString byteString, rl0 rl0Var) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static APPermanentErrorResult parseFrom(byte[] bArr) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static APPermanentErrorResult parseFrom(byte[] bArr, rl0 rl0Var) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static APPermanentErrorResult parseFrom(InputStream inputStream) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static APPermanentErrorResult parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static APPermanentErrorResult parseFrom(au auVar) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static APPermanentErrorResult parseFrom(au auVar, rl0 rl0Var) {
            return (APPermanentErrorResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface APPermanentErrorResultOrBuilder extends yj1 {
        APPermanentErrorResult.APPermanentError getCode();

        int getCodeValue();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        APPermanentErrorResult.ErrorCase getErrorCase();

        APPermanentErrorResult.GenericError getGenericError();

        boolean hasCode();

        boolean hasGenericError();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class ProductStateMap extends i implements ProductStateMapOrBuilder {
        private static final ProductStateMap DEFAULT_INSTANCE;
        private static volatile ds1 PARSER = null;
        public static final int PRODUCT_STATE_MAP_FIELD_NUMBER = 1;
        private MapFieldLite<String, String> productStateMap_ = MapFieldLite.c;

        public static final class Builder extends h01 implements ProductStateMapOrBuilder {
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

            public Builder clearProductStateMap() {
                copyOnWrite();
                ((ProductStateMap) this.instance).getMutableProductStateMapMap().clear();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
            public boolean containsProductStateMap(String str) {
                str.getClass();
                return ((ProductStateMap) this.instance).getProductStateMapMap().containsKey(str);
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
            @Deprecated
            public Map<String, String> getProductStateMap() {
                return getProductStateMapMap();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
            public int getProductStateMapCount() {
                return ((ProductStateMap) this.instance).getProductStateMapMap().size();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
            public Map<String, String> getProductStateMapMap() {
                return Collections.unmodifiableMap(((ProductStateMap) this.instance).getProductStateMapMap());
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
            public String getProductStateMapOrDefault(String str, String str2) {
                str.getClass();
                Map<String, String> productStateMapMap = ((ProductStateMap) this.instance).getProductStateMapMap();
                return productStateMapMap.containsKey(str) ? productStateMapMap.get(str) : str2;
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
            public String getProductStateMapOrThrow(String str) {
                str.getClass();
                Map<String, String> productStateMapMap = ((ProductStateMap) this.instance).getProductStateMapMap();
                if (productStateMapMap.containsKey(str)) {
                    return productStateMapMap.get(str);
                }
                throw new IllegalArgumentException();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder putAllProductStateMap(Map<String, String> map) {
                copyOnWrite();
                ((ProductStateMap) this.instance).getMutableProductStateMapMap().putAll(map);
                return this;
            }

            public Builder putProductStateMap(String str, String str2) {
                str.getClass();
                str2.getClass();
                copyOnWrite();
                ((ProductStateMap) this.instance).getMutableProductStateMapMap().put(str, str2);
                return this;
            }

            public Builder removeProductStateMap(String str) {
                str.getClass();
                copyOnWrite();
                ((ProductStateMap) this.instance).getMutableProductStateMapMap().remove(str);
                return this;
            }

            private Builder() {
                super(ProductStateMap.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m16mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m17mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m18mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        public static final class ProductStateMapDefaultEntryHolder {
            static final oe1 defaultEntry;

            static {
                WireFormat$FieldType wireFormat$FieldType = WireFormat$FieldType.e;
                defaultEntry = new oe1(wireFormat$FieldType, wireFormat$FieldType, "");
            }

            private ProductStateMapDefaultEntryHolder() {
            }
        }

        static {
            ProductStateMap productStateMap = new ProductStateMap();
            DEFAULT_INSTANCE = productStateMap;
            i.registerDefaultInstance(ProductStateMap.class, productStateMap);
        }

        private ProductStateMap() {
        }

        public static ProductStateMap getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Map<String, String> getMutableProductStateMapMap() {
            return internalGetMutableProductStateMap();
        }

        private MapFieldLite<String, String> internalGetMutableProductStateMap() {
            if (!this.productStateMap_.e()) {
                this.productStateMap_ = this.productStateMap_.g();
            }
            return this.productStateMap_;
        }

        private MapFieldLite<String, String> internalGetProductStateMap() {
            return this.productStateMap_;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static ProductStateMap parseDelimitedFrom(InputStream inputStream) {
            return (ProductStateMap) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ProductStateMap parseFrom(ByteBuffer byteBuffer) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
        public boolean containsProductStateMap(String str) {
            str.getClass();
            return internalGetProductStateMap().containsKey(str);
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"productStateMap_", ProductStateMapDefaultEntryHolder.defaultEntry});
                case 3:
                    return new ProductStateMap();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (ProductStateMap.class) {
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

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
        @Deprecated
        public Map<String, String> getProductStateMap() {
            return getProductStateMapMap();
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
        public int getProductStateMapCount() {
            return internalGetProductStateMap().size();
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
        public Map<String, String> getProductStateMapMap() {
            return Collections.unmodifiableMap(internalGetProductStateMap());
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
        public String getProductStateMapOrDefault(String str, String str2) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLiteInternalGetProductStateMap = internalGetProductStateMap();
            return mapFieldLiteInternalGetProductStateMap.containsKey(str) ? mapFieldLiteInternalGetProductStateMap.get(str) : str2;
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.ProductStateMapOrBuilder
        public String getProductStateMapOrThrow(String str) {
            str.getClass();
            MapFieldLite<String, String> mapFieldLiteInternalGetProductStateMap = internalGetProductStateMap();
            if (mapFieldLiteInternalGetProductStateMap.containsKey(str)) {
                return mapFieldLiteInternalGetProductStateMap.get(str);
            }
            throw new IllegalArgumentException();
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(ProductStateMap productStateMap) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(productStateMap);
        }

        public static ProductStateMap parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (ProductStateMap) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static ProductStateMap parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static ProductStateMap parseFrom(ByteString byteString) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ProductStateMap parseFrom(ByteString byteString, rl0 rl0Var) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static ProductStateMap parseFrom(byte[] bArr) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ProductStateMap parseFrom(byte[] bArr, rl0 rl0Var) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static ProductStateMap parseFrom(InputStream inputStream) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ProductStateMap parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static ProductStateMap parseFrom(au auVar) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static ProductStateMap parseFrom(au auVar, rl0 rl0Var) {
            return (ProductStateMap) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface ProductStateMapOrBuilder extends yj1 {
        boolean containsProductStateMap(String str);

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        @Deprecated
        Map<String, String> getProductStateMap();

        int getProductStateMapCount();

        Map<String, String> getProductStateMapMap();

        String getProductStateMapOrDefault(String str, String str2);

        String getProductStateMapOrThrow(String str);

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    public static final class SendEndSongsResult extends i implements SendEndSongsResultOrBuilder {
        private static final SendEndSongsResult DEFAULT_INSTANCE;
        public static final int ERROR_CODE_FIELD_NUMBER = 2;
        public static final int ERROR_DESCRIPTION_FIELD_NUMBER = 3;
        private static volatile ds1 PARSER = null;
        public static final int SUCCESS_FIELD_NUMBER = 1;
        private int errorCode_;
        private String errorDescription_ = "";
        private boolean success_;

        public static final class Builder extends h01 implements SendEndSongsResultOrBuilder {
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
                ((SendEndSongsResult) this.instance).clearErrorCode();
                return this;
            }

            public Builder clearErrorDescription() {
                copyOnWrite();
                ((SendEndSongsResult) this.instance).clearErrorDescription();
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((SendEndSongsResult) this.instance).clearSuccess();
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

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
            public int getErrorCode() {
                return ((SendEndSongsResult) this.instance).getErrorCode();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
            public String getErrorDescription() {
                return ((SendEndSongsResult) this.instance).getErrorDescription();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
            public ByteString getErrorDescriptionBytes() {
                return ((SendEndSongsResult) this.instance).getErrorDescriptionBytes();
            }

            @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
            public boolean getSuccess() {
                return ((SendEndSongsResult) this.instance).getSuccess();
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
                ((SendEndSongsResult) this.instance).setErrorCode(i);
                return this;
            }

            public Builder setErrorDescription(String str) {
                copyOnWrite();
                ((SendEndSongsResult) this.instance).setErrorDescription(str);
                return this;
            }

            public Builder setErrorDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((SendEndSongsResult) this.instance).setErrorDescriptionBytes(byteString);
                return this;
            }

            public Builder setSuccess(boolean z) {
                copyOnWrite();
                ((SendEndSongsResult) this.instance).setSuccess(z);
                return this;
            }

            private Builder() {
                super(SendEndSongsResult.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m19mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m20mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m21mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            SendEndSongsResult sendEndSongsResult = new SendEndSongsResult();
            DEFAULT_INSTANCE = sendEndSongsResult;
            i.registerDefaultInstance(SendEndSongsResult.class, sendEndSongsResult);
        }

        private SendEndSongsResult() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorCode() {
            this.errorCode_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearErrorDescription() {
            this.errorDescription_ = getDefaultInstance().getErrorDescription();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearSuccess() {
            this.success_ = false;
        }

        public static SendEndSongsResult getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static SendEndSongsResult parseDelimitedFrom(InputStream inputStream) {
            return (SendEndSongsResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SendEndSongsResult parseFrom(ByteBuffer byteBuffer) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorCode(int i) {
            this.errorCode_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorDescription(String str) {
            str.getClass();
            this.errorDescription_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setErrorDescriptionBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.errorDescription_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setSuccess(boolean z) {
            this.success_ = z;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0002\u0004\u0003Ȉ", new Object[]{"success_", "errorCode_", "errorDescription_"});
                case 3:
                    return new SendEndSongsResult();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (SendEndSongsResult.class) {
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

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
        public int getErrorCode() {
            return this.errorCode_;
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
        public String getErrorDescription() {
            return this.errorDescription_;
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
        public ByteString getErrorDescriptionBytes() {
            return ByteString.d(this.errorDescription_);
        }

        @Override // com.spotify.connectivity.auth.esperanto.proto.EsSession.SendEndSongsResultOrBuilder
        public boolean getSuccess() {
            return this.success_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(SendEndSongsResult sendEndSongsResult) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(sendEndSongsResult);
        }

        public static SendEndSongsResult parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (SendEndSongsResult) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static SendEndSongsResult parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static SendEndSongsResult parseFrom(ByteString byteString) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SendEndSongsResult parseFrom(ByteString byteString, rl0 rl0Var) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static SendEndSongsResult parseFrom(byte[] bArr) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SendEndSongsResult parseFrom(byte[] bArr, rl0 rl0Var) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static SendEndSongsResult parseFrom(InputStream inputStream) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SendEndSongsResult parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static SendEndSongsResult parseFrom(au auVar) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static SendEndSongsResult parseFrom(au auVar, rl0 rl0Var) {
            return (SendEndSongsResult) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface SendEndSongsResultOrBuilder extends yj1 {
        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        int getErrorCode();

        String getErrorDescription();

        ByteString getErrorDescriptionBytes();

        boolean getSuccess();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsSession() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
