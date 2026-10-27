package com.spotify.connectivity.auth.login5.esperanto.proto;

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
import p.n51;
import p.o51;
import p.p51;
import p.r1;
import p.rl0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSignupRequired {

    /* JADX INFO: renamed from: com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired$1, reason: invalid class name */
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

    public enum AuthUserInfoGender implements n51 {
        UNKNOWN(0),
        MALE(1),
        FEMALE(2),
        NEUTRAL(3),
        UNRECOGNIZED(-1);

        public static final int FEMALE_VALUE = 2;
        public static final int MALE_VALUE = 1;
        public static final int NEUTRAL_VALUE = 3;
        public static final int UNKNOWN_VALUE = 0;
        private static final o51 internalValueMap = new o51() { // from class: com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.AuthUserInfoGender.1
            /* JADX INFO: renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
            public AuthUserInfoGender m136findValueByNumber(int i) {
                return AuthUserInfoGender.forNumber(i);
            }
        };
        private final int value;

        public static final class AuthUserInfoGenderVerifier implements p51 {
            static final p51 INSTANCE = new AuthUserInfoGenderVerifier();

            private AuthUserInfoGenderVerifier() {
            }

            @Override // p.p51
            public boolean isInRange(int i) {
                return AuthUserInfoGender.forNumber(i) != null;
            }
        }

        AuthUserInfoGender(int i) {
            this.value = i;
        }

        public static AuthUserInfoGender forNumber(int i) {
            if (i == 0) {
                return UNKNOWN;
            }
            if (i == 1) {
                return MALE;
            }
            if (i == 2) {
                return FEMALE;
            }
            if (i != 3) {
                return null;
            }
            return NEUTRAL;
        }

        public static o51 internalGetValueMap() {
            return internalValueMap;
        }

        public static p51 internalGetVerifier() {
            return AuthUserInfoGenderVerifier.INSTANCE;
        }

        @Override // p.n51
        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static AuthUserInfoGender valueOf(int i) {
            return forNumber(i);
        }
    }

    public static final class SignupRequired extends i implements SignupRequiredOrBuilder {
        public static final int BIRTHDATE_FIELD_NUMBER = 4;
        private static final SignupRequired DEFAULT_INSTANCE;
        public static final int EMAILALREADYREGISTERED_FIELD_NUMBER = 8;
        public static final int EMAIL_FIELD_NUMBER = 2;
        public static final int GENDER_FIELD_NUMBER = 5;
        public static final int IDENTIFIERTOKEN_FIELD_NUMBER = 9;
        public static final int ISEMAILVERIFIED_FIELD_NUMBER = 3;
        public static final int ISPHONENUMBERVERIFIED_FIELD_NUMBER = 7;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile ds1 PARSER = null;
        public static final int PHONENUMBER_FIELD_NUMBER = 6;
        private boolean emailAlreadyRegistered_;
        private int gender_;
        private boolean isEmailVerified_;
        private boolean isPhoneNumberVerified_;
        private String name_ = "";
        private String email_ = "";
        private String birthdate_ = "";
        private String phoneNumber_ = "";
        private String identifierToken_ = "";

        public static final class Builder extends h01 implements SignupRequiredOrBuilder {
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

            public Builder clearBirthdate() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearBirthdate();
                return this;
            }

            public Builder clearEmail() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearEmail();
                return this;
            }

            public Builder clearEmailAlreadyRegistered() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearEmailAlreadyRegistered();
                return this;
            }

            public Builder clearGender() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearGender();
                return this;
            }

            public Builder clearIdentifierToken() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearIdentifierToken();
                return this;
            }

            public Builder clearIsEmailVerified() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearIsEmailVerified();
                return this;
            }

            public Builder clearIsPhoneNumberVerified() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearIsPhoneNumberVerified();
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearName();
                return this;
            }

            public Builder clearPhoneNumber() {
                copyOnWrite();
                ((SignupRequired) this.instance).clearPhoneNumber();
                return this;
            }

            @Override // p.h01
            public /* bridge */ /* synthetic */ Object clone() {
                return super.clone();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public String getBirthdate() {
                return ((SignupRequired) this.instance).getBirthdate();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public ByteString getBirthdateBytes() {
                return ((SignupRequired) this.instance).getBirthdateBytes();
            }

            @Override // p.h01, p.yj1
            public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
                return super.getDefaultInstanceForType();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public String getEmail() {
                return ((SignupRequired) this.instance).getEmail();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public boolean getEmailAlreadyRegistered() {
                return ((SignupRequired) this.instance).getEmailAlreadyRegistered();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public ByteString getEmailBytes() {
                return ((SignupRequired) this.instance).getEmailBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public AuthUserInfoGender getGender() {
                return ((SignupRequired) this.instance).getGender();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public int getGenderValue() {
                return ((SignupRequired) this.instance).getGenderValue();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public String getIdentifierToken() {
                return ((SignupRequired) this.instance).getIdentifierToken();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public ByteString getIdentifierTokenBytes() {
                return ((SignupRequired) this.instance).getIdentifierTokenBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public boolean getIsEmailVerified() {
                return ((SignupRequired) this.instance).getIsEmailVerified();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public boolean getIsPhoneNumberVerified() {
                return ((SignupRequired) this.instance).getIsPhoneNumberVerified();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public String getName() {
                return ((SignupRequired) this.instance).getName();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public ByteString getNameBytes() {
                return ((SignupRequired) this.instance).getNameBytes();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public String getPhoneNumber() {
                return ((SignupRequired) this.instance).getPhoneNumber();
            }

            @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
            public ByteString getPhoneNumberBytes() {
                return ((SignupRequired) this.instance).getPhoneNumberBytes();
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 internalMergeFrom(a aVar) {
                return super.internalMergeFrom((i) aVar);
            }

            @Override // p.h01, p.r1
            public /* bridge */ /* synthetic */ r1 mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public Builder setBirthdate(String str) {
                copyOnWrite();
                ((SignupRequired) this.instance).setBirthdate(str);
                return this;
            }

            public Builder setBirthdateBytes(ByteString byteString) {
                copyOnWrite();
                ((SignupRequired) this.instance).setBirthdateBytes(byteString);
                return this;
            }

            public Builder setEmail(String str) {
                copyOnWrite();
                ((SignupRequired) this.instance).setEmail(str);
                return this;
            }

            public Builder setEmailAlreadyRegistered(boolean z) {
                copyOnWrite();
                ((SignupRequired) this.instance).setEmailAlreadyRegistered(z);
                return this;
            }

            public Builder setEmailBytes(ByteString byteString) {
                copyOnWrite();
                ((SignupRequired) this.instance).setEmailBytes(byteString);
                return this;
            }

            public Builder setGender(AuthUserInfoGender authUserInfoGender) {
                copyOnWrite();
                ((SignupRequired) this.instance).setGender(authUserInfoGender);
                return this;
            }

            public Builder setGenderValue(int i) {
                copyOnWrite();
                ((SignupRequired) this.instance).setGenderValue(i);
                return this;
            }

            public Builder setIdentifierToken(String str) {
                copyOnWrite();
                ((SignupRequired) this.instance).setIdentifierToken(str);
                return this;
            }

            public Builder setIdentifierTokenBytes(ByteString byteString) {
                copyOnWrite();
                ((SignupRequired) this.instance).setIdentifierTokenBytes(byteString);
                return this;
            }

            public Builder setIsEmailVerified(boolean z) {
                copyOnWrite();
                ((SignupRequired) this.instance).setIsEmailVerified(z);
                return this;
            }

            public Builder setIsPhoneNumberVerified(boolean z) {
                copyOnWrite();
                ((SignupRequired) this.instance).setIsPhoneNumberVerified(z);
                return this;
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((SignupRequired) this.instance).setName(str);
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((SignupRequired) this.instance).setNameBytes(byteString);
                return this;
            }

            public Builder setPhoneNumber(String str) {
                copyOnWrite();
                ((SignupRequired) this.instance).setPhoneNumber(str);
                return this;
            }

            public Builder setPhoneNumberBytes(ByteString byteString) {
                copyOnWrite();
                ((SignupRequired) this.instance).setPhoneNumberBytes(byteString);
                return this;
            }

            private Builder() {
                super(SignupRequired.DEFAULT_INSTANCE);
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
            public /* bridge */ /* synthetic */ vj1 m137mergeFrom(au auVar, rl0 rl0Var) {
                return super.mergeFrom(auVar, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(wj1 wj1Var) {
                return super.mergeFrom(wj1Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr) {
                return super.mergeFrom(bArr);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m138mergeFrom(byte[] bArr, int i, int i2) {
                return super.mergeFrom(bArr, i, i2);
            }

            /* JADX INFO: renamed from: mergeFrom, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ vj1 m139mergeFrom(byte[] bArr, int i, int i2, rl0 rl0Var) {
                return super.mergeFrom(bArr, i, i2, rl0Var);
            }

            public /* bridge */ /* synthetic */ vj1 mergeFrom(byte[] bArr, rl0 rl0Var) {
                return super.mergeFrom(bArr, rl0Var);
            }
        }

        static {
            SignupRequired signupRequired = new SignupRequired();
            DEFAULT_INSTANCE = signupRequired;
            i.registerDefaultInstance(SignupRequired.class, signupRequired);
        }

        private SignupRequired() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearBirthdate() {
            this.birthdate_ = getDefaultInstance().getBirthdate();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEmail() {
            this.email_ = getDefaultInstance().getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearEmailAlreadyRegistered() {
            this.emailAlreadyRegistered_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearGender() {
            this.gender_ = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIdentifierToken() {
            this.identifierToken_ = getDefaultInstance().getIdentifierToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsEmailVerified() {
            this.isEmailVerified_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearIsPhoneNumberVerified() {
            this.isPhoneNumberVerified_ = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearPhoneNumber() {
            this.phoneNumber_ = getDefaultInstance().getPhoneNumber();
        }

        public static SignupRequired getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.createBuilder();
        }

        public static SignupRequired parseDelimitedFrom(InputStream inputStream) {
            return (SignupRequired) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SignupRequired parseFrom(ByteBuffer byteBuffer) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static ds1 parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBirthdate(String str) {
            str.getClass();
            this.birthdate_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBirthdateBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.birthdate_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmail(String str) {
            str.getClass();
            this.email_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmailAlreadyRegistered(boolean z) {
            this.emailAlreadyRegistered_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setEmailBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.email_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGender(AuthUserInfoGender authUserInfoGender) {
            this.gender_ = authUserInfoGender.getNumber();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setGenderValue(int i) {
            this.gender_ = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifierToken(String str) {
            str.getClass();
            this.identifierToken_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIdentifierTokenBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.identifierToken_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsEmailVerified(boolean z) {
            this.isEmailVerified_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setIsPhoneNumberVerified(boolean z) {
            this.isPhoneNumberVerified_ = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.name_ = byteString.s();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhoneNumber(String str) {
            str.getClass();
            this.phoneNumber_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setPhoneNumberBytes(ByteString byteString) {
            a.checkByteStringIsUtf8(byteString);
            this.phoneNumber_ = byteString.s();
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\t\u0000\u0000\u0001\t\t\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u0007\u0004Ȉ\u0005\f\u0006Ȉ\u0007\u0007\b\u0007\tȈ", new Object[]{"name_", "email_", "isEmailVerified_", "birthdate_", "gender_", "phoneNumber_", "isPhoneNumberVerified_", "emailAlreadyRegistered_", "identifierToken_"});
                case 3:
                    return new SignupRequired();
                case 4:
                    return new Builder(anonymousClass1);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (SignupRequired.class) {
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

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public String getBirthdate() {
            return this.birthdate_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public ByteString getBirthdateBytes() {
            return ByteString.d(this.birthdate_);
        }

        @Override // com.google.protobuf.i, p.yj1
        public /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
            return super.getDefaultInstanceForType();
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public String getEmail() {
            return this.email_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public boolean getEmailAlreadyRegistered() {
            return this.emailAlreadyRegistered_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public ByteString getEmailBytes() {
            return ByteString.d(this.email_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public AuthUserInfoGender getGender() {
            AuthUserInfoGender authUserInfoGenderForNumber = AuthUserInfoGender.forNumber(this.gender_);
            return authUserInfoGenderForNumber == null ? AuthUserInfoGender.UNRECOGNIZED : authUserInfoGenderForNumber;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public int getGenderValue() {
            return this.gender_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public String getIdentifierToken() {
            return this.identifierToken_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public ByteString getIdentifierTokenBytes() {
            return ByteString.d(this.identifierToken_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public boolean getIsEmailVerified() {
            return this.isEmailVerified_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public boolean getIsPhoneNumberVerified() {
            return this.isPhoneNumberVerified_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public ByteString getNameBytes() {
            return ByteString.d(this.name_);
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public String getPhoneNumber() {
            return this.phoneNumber_;
        }

        @Override // com.spotify.connectivity.auth.login5.esperanto.proto.EsSignupRequired.SignupRequiredOrBuilder
        public ByteString getPhoneNumberBytes() {
            return ByteString.d(this.phoneNumber_);
        }

        @Override // com.google.protobuf.i, p.wj1
        public /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }

        public /* bridge */ /* synthetic */ vj1 toBuilder() {
            return super.toBuilder();
        }

        public static Builder newBuilder(SignupRequired signupRequired) {
            return (Builder) DEFAULT_INSTANCE.createBuilder(signupRequired);
        }

        public static SignupRequired parseDelimitedFrom(InputStream inputStream, rl0 rl0Var) {
            return (SignupRequired) i.parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static SignupRequired parseFrom(ByteBuffer byteBuffer, rl0 rl0Var) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, byteBuffer, rl0Var);
        }

        public static SignupRequired parseFrom(ByteString byteString) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static SignupRequired parseFrom(ByteString byteString, rl0 rl0Var) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, byteString, rl0Var);
        }

        public static SignupRequired parseFrom(byte[] bArr) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static SignupRequired parseFrom(byte[] bArr, rl0 rl0Var) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, bArr, rl0Var);
        }

        public static SignupRequired parseFrom(InputStream inputStream) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static SignupRequired parseFrom(InputStream inputStream, rl0 rl0Var) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, inputStream, rl0Var);
        }

        public static SignupRequired parseFrom(au auVar) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, auVar);
        }

        public static SignupRequired parseFrom(au auVar, rl0 rl0Var) {
            return (SignupRequired) i.parseFrom(DEFAULT_INSTANCE, auVar, rl0Var);
        }
    }

    public interface SignupRequiredOrBuilder extends yj1 {
        String getBirthdate();

        ByteString getBirthdateBytes();

        @Override // p.yj1
        /* synthetic */ wj1 getDefaultInstanceForType();

        String getEmail();

        boolean getEmailAlreadyRegistered();

        ByteString getEmailBytes();

        AuthUserInfoGender getGender();

        int getGenderValue();

        String getIdentifierToken();

        ByteString getIdentifierTokenBytes();

        boolean getIsEmailVerified();

        boolean getIsPhoneNumberVerified();

        String getName();

        ByteString getNameBytes();

        String getPhoneNumber();

        ByteString getPhoneNumberBytes();

        @Override // p.yj1
        /* synthetic */ boolean isInitialized();
    }

    private EsSignupRequired() {
    }

    public static void registerAllExtensions(rl0 rl0Var) {
    }
}
