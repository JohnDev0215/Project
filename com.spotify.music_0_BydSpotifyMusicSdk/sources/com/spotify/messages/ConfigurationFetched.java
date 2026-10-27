package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ax;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;
import p.zw;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationFetched extends i implements yj1 {
    public static final int ATTRIBUTES_SET_ID_FIELD_NUMBER = 5;
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 3;
    private static final ConfigurationFetched DEFAULT_INSTANCE;
    public static final int ERROR_CODE_ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 20;
    public static final int ERROR_CODE_CONFIGURATION_RESOLVE_FIELD_NUMBER = 21;
    public static final int ERROR_MESSAGE_ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 19;
    public static final int ERROR_MESSAGE_CONFIGURATION_RESOLVE_FIELD_NUMBER = 17;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 15;
    public static final int ERROR_REASON_ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 18;
    public static final int ERROR_REASON_CONFIGURATION_RESOLVE_FIELD_NUMBER = 16;
    public static final int ERROR_REASON_FIELD_NUMBER = 14;
    public static final int FETCH_TYPE_FIELD_NUMBER = 10;
    public static final int IDENTIFIERS_FIELD_NUMBER = 23;
    public static final int INSTALLATION_ID_FIELD_NUMBER = 2;
    public static final int LAST_RCS_FETCH_TIME_FIELD_NUMBER = 1;
    public static final int LATENCY_FIELD_NUMBER = 11;
    private static volatile ds1 PARSER = null;
    public static final int PAYLOAD_SIZE_FIELD_NUMBER = 12;
    public static final int PLATFORM_FIELD_NUMBER = 9;
    public static final int PRODUCT_STATE_KEY_COUNT_FIELD_NUMBER = 22;
    public static final int PROPERTY_SET_ID_FIELD_NUMBER = 4;
    public static final int RC_CLIENT_ID_FIELD_NUMBER = 6;
    public static final int RC_CLIENT_VERSION_FIELD_NUMBER = 7;
    public static final int RC_SDK_VERSION_FIELD_NUMBER = 8;
    public static final int STATUS_CODE_FIELD_NUMBER = 13;
    private int bitField0_;
    private int errorCodeAccountAttributes_;
    private int errorCodeConfigurationResolve_;
    private long lastRcsFetchTime_;
    private long latency_;
    private long payloadSize_;
    private int productStateKeyCount_;
    private int statusCode_;
    private MapFieldLite<String, String> identifiers_ = MapFieldLite.c;
    private String installationId_ = "";
    private String configurationAssignmentId_ = "";
    private String propertySetId_ = "";
    private String attributesSetId_ = "";
    private String rcClientId_ = "";
    private String rcClientVersion_ = "";
    private String rcSdkVersion_ = "";
    private String platform_ = "";
    private String fetchType_ = "";
    private String errorReason_ = "";
    private String errorMessage_ = "";
    private String errorReasonConfigurationResolve_ = "";
    private String errorMessageConfigurationResolve_ = "";
    private String errorReasonAccountAttributes_ = "";
    private String errorMessageAccountAttributes_ = "";

    static {
        ConfigurationFetched configurationFetched = new ConfigurationFetched();
        DEFAULT_INSTANCE = configurationFetched;
        i.registerDefaultInstance(ConfigurationFetched.class, configurationFetched);
    }

    private ConfigurationFetched() {
    }

    public static void e(ConfigurationFetched configurationFetched, long j) {
        configurationFetched.bitField0_ |= 1;
        configurationFetched.lastRcsFetchTime_ = j;
    }

    public static void f(ConfigurationFetched configurationFetched) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 32;
        configurationFetched.rcClientId_ = "com.spotify.auto";
    }

    public static void g(ConfigurationFetched configurationFetched) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 64;
        configurationFetched.rcClientVersion_ = "4.4.1.0";
    }

    public static void h(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        str.getClass();
        configurationFetched.bitField0_ |= 128;
        configurationFetched.rcSdkVersion_ = str;
    }

    public static void i(ConfigurationFetched configurationFetched) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 256;
        configurationFetched.platform_ = "ANDROID";
    }

    public static void j(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        str.getClass();
        configurationFetched.bitField0_ |= 512;
        configurationFetched.fetchType_ = str;
    }

    public static void k(ConfigurationFetched configurationFetched, long j) {
        configurationFetched.bitField0_ |= 1024;
        configurationFetched.latency_ = j;
    }

    public static void l(ConfigurationFetched configurationFetched, long j) {
        configurationFetched.bitField0_ |= 2048;
        configurationFetched.payloadSize_ = j;
    }

    public static void m(ConfigurationFetched configurationFetched, int i) {
        configurationFetched.bitField0_ |= 4096;
        configurationFetched.statusCode_ = i;
    }

    public static void n(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        str.getClass();
        configurationFetched.bitField0_ |= 8192;
        configurationFetched.errorReason_ = str;
    }

    public static void o(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 16384;
        configurationFetched.errorMessage_ = str;
    }

    public static void p(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        str.getClass();
        configurationFetched.bitField0_ |= 32768;
        configurationFetched.errorReasonConfigurationResolve_ = str;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 65536;
        configurationFetched.errorMessageConfigurationResolve_ = str;
    }

    public static void r(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        str.getClass();
        configurationFetched.bitField0_ |= 131072;
        configurationFetched.errorReasonAccountAttributes_ = str;
    }

    public static void s(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 262144;
        configurationFetched.errorMessageAccountAttributes_ = str;
    }

    public static void t(ConfigurationFetched configurationFetched, int i) {
        configurationFetched.bitField0_ |= 524288;
        configurationFetched.errorCodeAccountAttributes_ = i;
    }

    public static void u(ConfigurationFetched configurationFetched, int i) {
        configurationFetched.bitField0_ |= 1048576;
        configurationFetched.errorCodeConfigurationResolve_ = i;
    }

    public static void v(ConfigurationFetched configurationFetched, int i) {
        configurationFetched.bitField0_ |= 2097152;
        configurationFetched.productStateKeyCount_ = i;
    }

    public static void w(ConfigurationFetched configurationFetched, String str) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 4;
        configurationFetched.configurationAssignmentId_ = str;
    }

    public static MapFieldLite x(ConfigurationFetched configurationFetched) {
        if (!configurationFetched.identifiers_.e()) {
            configurationFetched.identifiers_ = configurationFetched.identifiers_.g();
        }
        return configurationFetched.identifiers_;
    }

    public static void y(ConfigurationFetched configurationFetched) {
        configurationFetched.getClass();
        configurationFetched.bitField0_ |= 8;
        configurationFetched.propertySetId_ = "a296f6a0c8d02612625a2cce0a87cd4903b3a030b8d046935f1922a441cd30df";
    }

    public static zw z() {
        return (zw) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0017\u0000\u0001\u0001\u0017\u0017\u0001\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t\u000bဂ\n\fဂ\u000b\rင\f\u000eဈ\r\u000fဈ\u000e\u0010ဈ\u000f\u0011ဈ\u0010\u0012ဈ\u0011\u0013ဈ\u0012\u0014င\u0013\u0015င\u0014\u0016င\u0015\u00172", new Object[]{"bitField0_", "lastRcsFetchTime_", "installationId_", "configurationAssignmentId_", "propertySetId_", "attributesSetId_", "rcClientId_", "rcClientVersion_", "rcSdkVersion_", "platform_", "fetchType_", "latency_", "payloadSize_", "statusCode_", "errorReason_", "errorMessage_", "errorReasonConfigurationResolve_", "errorMessageConfigurationResolve_", "errorReasonAccountAttributes_", "errorMessageAccountAttributes_", "errorCodeAccountAttributes_", "errorCodeConfigurationResolve_", "productStateKeyCount_", "identifiers_", ax.a});
            case 3:
                return new ConfigurationFetched();
            case 4:
                return new zw(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ConfigurationFetched.class) {
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
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}
