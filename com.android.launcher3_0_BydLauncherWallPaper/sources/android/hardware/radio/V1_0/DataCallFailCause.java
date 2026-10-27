package android.hardware.radio.V1_0;

import android.security.keystore.KeyProperties;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class DataCallFailCause {
    public static final int ACTIVATION_REJECT_GGSN = 30;
    public static final int ACTIVATION_REJECT_UNSPECIFIED = 31;
    public static final int APN_TYPE_CONFLICT = 112;
    public static final int AUTH_FAILURE_ON_EMERGENCY_CALL = 122;
    public static final int COMPANION_IFACE_IN_USE = 118;
    public static final int CONDITIONAL_IE_ERROR = 100;
    public static final int DATA_REGISTRATION_FAIL = -2;
    public static final int EMERGENCY_IFACE_ONLY = 116;
    public static final int EMM_ACCESS_BARRED = 115;
    public static final int EMM_ACCESS_BARRED_INFINITE_RETRY = 121;
    public static final int ERROR_UNSPECIFIED = 65535;
    public static final int ESM_INFO_NOT_RECEIVED = 53;
    public static final int FEATURE_NOT_SUPP = 40;
    public static final int FILTER_SEMANTIC_ERROR = 44;
    public static final int FILTER_SYTAX_ERROR = 45;
    public static final int IFACE_AND_POL_FAMILY_MISMATCH = 120;
    public static final int IFACE_MISMATCH = 117;
    public static final int INSUFFICIENT_RESOURCES = 26;
    public static final int INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN = 114;
    public static final int INVALID_MANDATORY_INFO = 96;
    public static final int INVALID_PCSCF_ADDR = 113;
    public static final int INVALID_TRANSACTION_ID = 81;
    public static final int IP_ADDRESS_MISMATCH = 119;
    public static final int MAX_ACTIVE_PDP_CONTEXT_REACHED = 65;
    public static final int MESSAGE_INCORRECT_SEMANTIC = 95;
    public static final int MESSAGE_TYPE_UNSUPPORTED = 97;
    public static final int MISSING_UKNOWN_APN = 27;
    public static final int MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE = 101;
    public static final int MSG_TYPE_NONCOMPATIBLE_STATE = 98;
    public static final int MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED = 55;
    public static final int NAS_SIGNALLING = 14;
    public static final int NETWORK_FAILURE = 38;
    public static final int NONE = 0;
    public static final int NSAPI_IN_USE = 35;
    public static final int OEM_DCFAILCAUSE_1 = 4097;
    public static final int OEM_DCFAILCAUSE_10 = 4106;
    public static final int OEM_DCFAILCAUSE_11 = 4107;
    public static final int OEM_DCFAILCAUSE_12 = 4108;
    public static final int OEM_DCFAILCAUSE_13 = 4109;
    public static final int OEM_DCFAILCAUSE_14 = 4110;
    public static final int OEM_DCFAILCAUSE_15 = 4111;
    public static final int OEM_DCFAILCAUSE_2 = 4098;
    public static final int OEM_DCFAILCAUSE_3 = 4099;
    public static final int OEM_DCFAILCAUSE_4 = 4100;
    public static final int OEM_DCFAILCAUSE_5 = 4101;
    public static final int OEM_DCFAILCAUSE_6 = 4102;
    public static final int OEM_DCFAILCAUSE_7 = 4103;
    public static final int OEM_DCFAILCAUSE_8 = 4104;
    public static final int OEM_DCFAILCAUSE_9 = 4105;
    public static final int ONLY_IPV4_ALLOWED = 50;
    public static final int ONLY_IPV6_ALLOWED = 51;
    public static final int ONLY_SINGLE_BEARER_ALLOWED = 52;
    public static final int OPERATOR_BARRED = 8;
    public static final int PDN_CONN_DOES_NOT_EXIST = 54;
    public static final int PDP_WITHOUT_ACTIVE_TFT = 46;
    public static final int PREF_RADIO_TECH_CHANGED = -4;
    public static final int PROTOCOL_ERRORS = 111;
    public static final int QOS_NOT_ACCEPTED = 37;
    public static final int RADIO_POWER_OFF = -5;
    public static final int REGULAR_DEACTIVATION = 36;
    public static final int SERVICE_OPTION_NOT_SUBSCRIBED = 33;
    public static final int SERVICE_OPTION_NOT_SUPPORTED = 32;
    public static final int SERVICE_OPTION_OUT_OF_ORDER = 34;
    public static final int SIGNAL_LOST = -3;
    public static final int TETHERED_CALL_ACTIVE = -6;
    public static final int TFT_SEMANTIC_ERROR = 41;
    public static final int TFT_SYTAX_ERROR = 42;
    public static final int UMTS_REACTIVATION_REQ = 39;
    public static final int UNKNOWN_INFO_ELEMENT = 99;
    public static final int UNKNOWN_PDP_ADDRESS_TYPE = 28;
    public static final int UNKNOWN_PDP_CONTEXT = 43;
    public static final int UNSUPPORTED_APN_IN_CURRENT_PLMN = 66;
    public static final int USER_AUTHENTICATION = 29;
    public static final int VOICE_REGISTRATION_FAIL = -1;

    public static final String toString(int i) {
        return i == 0 ? KeyProperties.DIGEST_NONE : i == 8 ? "OPERATOR_BARRED" : i == 14 ? "NAS_SIGNALLING" : i == 26 ? "INSUFFICIENT_RESOURCES" : i == 27 ? "MISSING_UKNOWN_APN" : i == 28 ? "UNKNOWN_PDP_ADDRESS_TYPE" : i == 29 ? "USER_AUTHENTICATION" : i == 30 ? "ACTIVATION_REJECT_GGSN" : i == 31 ? "ACTIVATION_REJECT_UNSPECIFIED" : i == 32 ? "SERVICE_OPTION_NOT_SUPPORTED" : i == 33 ? "SERVICE_OPTION_NOT_SUBSCRIBED" : i == 34 ? "SERVICE_OPTION_OUT_OF_ORDER" : i == 35 ? "NSAPI_IN_USE" : i == 36 ? "REGULAR_DEACTIVATION" : i == 37 ? "QOS_NOT_ACCEPTED" : i == 38 ? "NETWORK_FAILURE" : i == 39 ? "UMTS_REACTIVATION_REQ" : i == 40 ? "FEATURE_NOT_SUPP" : i == 41 ? "TFT_SEMANTIC_ERROR" : i == 42 ? "TFT_SYTAX_ERROR" : i == 43 ? "UNKNOWN_PDP_CONTEXT" : i == 44 ? "FILTER_SEMANTIC_ERROR" : i == 45 ? "FILTER_SYTAX_ERROR" : i == 46 ? "PDP_WITHOUT_ACTIVE_TFT" : i == 50 ? "ONLY_IPV4_ALLOWED" : i == 51 ? "ONLY_IPV6_ALLOWED" : i == 52 ? "ONLY_SINGLE_BEARER_ALLOWED" : i == 53 ? "ESM_INFO_NOT_RECEIVED" : i == 54 ? "PDN_CONN_DOES_NOT_EXIST" : i == 55 ? "MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED" : i == 65 ? "MAX_ACTIVE_PDP_CONTEXT_REACHED" : i == 66 ? "UNSUPPORTED_APN_IN_CURRENT_PLMN" : i == 81 ? "INVALID_TRANSACTION_ID" : i == 95 ? "MESSAGE_INCORRECT_SEMANTIC" : i == 96 ? "INVALID_MANDATORY_INFO" : i == 97 ? "MESSAGE_TYPE_UNSUPPORTED" : i == 98 ? "MSG_TYPE_NONCOMPATIBLE_STATE" : i == 99 ? "UNKNOWN_INFO_ELEMENT" : i == 100 ? "CONDITIONAL_IE_ERROR" : i == 101 ? "MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE" : i == 111 ? "PROTOCOL_ERRORS" : i == 112 ? "APN_TYPE_CONFLICT" : i == 113 ? "INVALID_PCSCF_ADDR" : i == 114 ? "INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN" : i == 115 ? "EMM_ACCESS_BARRED" : i == 116 ? "EMERGENCY_IFACE_ONLY" : i == 117 ? "IFACE_MISMATCH" : i == 118 ? "COMPANION_IFACE_IN_USE" : i == 119 ? "IP_ADDRESS_MISMATCH" : i == 120 ? "IFACE_AND_POL_FAMILY_MISMATCH" : i == 121 ? "EMM_ACCESS_BARRED_INFINITE_RETRY" : i == 122 ? "AUTH_FAILURE_ON_EMERGENCY_CALL" : i == 4097 ? "OEM_DCFAILCAUSE_1" : i == 4098 ? "OEM_DCFAILCAUSE_2" : i == 4099 ? "OEM_DCFAILCAUSE_3" : i == 4100 ? "OEM_DCFAILCAUSE_4" : i == 4101 ? "OEM_DCFAILCAUSE_5" : i == 4102 ? "OEM_DCFAILCAUSE_6" : i == 4103 ? "OEM_DCFAILCAUSE_7" : i == 4104 ? "OEM_DCFAILCAUSE_8" : i == 4105 ? "OEM_DCFAILCAUSE_9" : i == 4106 ? "OEM_DCFAILCAUSE_10" : i == 4107 ? "OEM_DCFAILCAUSE_11" : i == 4108 ? "OEM_DCFAILCAUSE_12" : i == 4109 ? "OEM_DCFAILCAUSE_13" : i == 4110 ? "OEM_DCFAILCAUSE_14" : i == 4111 ? "OEM_DCFAILCAUSE_15" : i == -1 ? "VOICE_REGISTRATION_FAIL" : i == -2 ? "DATA_REGISTRATION_FAIL" : i == -3 ? "SIGNAL_LOST" : i == -4 ? "PREF_RADIO_TECH_CHANGED" : i == -5 ? "RADIO_POWER_OFF" : i == -6 ? "TETHERED_CALL_ACTIVE" : i == 65535 ? "ERROR_UNSPECIFIED" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(KeyProperties.DIGEST_NONE);
        int i2 = 8;
        if ((i & 8) == 8) {
            arrayList.add("OPERATOR_BARRED");
        } else {
            i2 = 0;
        }
        if ((i & 14) == 14) {
            arrayList.add("NAS_SIGNALLING");
            i2 |= 14;
        }
        if ((i & 26) == 26) {
            arrayList.add("INSUFFICIENT_RESOURCES");
            i2 |= 26;
        }
        if ((i & 27) == 27) {
            arrayList.add("MISSING_UKNOWN_APN");
            i2 |= 27;
        }
        if ((i & 28) == 28) {
            arrayList.add("UNKNOWN_PDP_ADDRESS_TYPE");
            i2 |= 28;
        }
        if ((i & 29) == 29) {
            arrayList.add("USER_AUTHENTICATION");
            i2 |= 29;
        }
        if ((i & 30) == 30) {
            arrayList.add("ACTIVATION_REJECT_GGSN");
            i2 |= 30;
        }
        if ((i & 31) == 31) {
            arrayList.add("ACTIVATION_REJECT_UNSPECIFIED");
            i2 |= 31;
        }
        if ((i & 32) == 32) {
            arrayList.add("SERVICE_OPTION_NOT_SUPPORTED");
            i2 |= 32;
        }
        if ((i & 33) == 33) {
            arrayList.add("SERVICE_OPTION_NOT_SUBSCRIBED");
            i2 |= 33;
        }
        if ((i & 34) == 34) {
            arrayList.add("SERVICE_OPTION_OUT_OF_ORDER");
            i2 |= 34;
        }
        if ((i & 35) == 35) {
            arrayList.add("NSAPI_IN_USE");
            i2 |= 35;
        }
        if ((i & 36) == 36) {
            arrayList.add("REGULAR_DEACTIVATION");
            i2 |= 36;
        }
        if ((i & 37) == 37) {
            arrayList.add("QOS_NOT_ACCEPTED");
            i2 |= 37;
        }
        if ((i & 38) == 38) {
            arrayList.add("NETWORK_FAILURE");
            i2 |= 38;
        }
        if ((i & 39) == 39) {
            arrayList.add("UMTS_REACTIVATION_REQ");
            i2 |= 39;
        }
        if ((i & 40) == 40) {
            arrayList.add("FEATURE_NOT_SUPP");
            i2 |= 40;
        }
        if ((i & 41) == 41) {
            arrayList.add("TFT_SEMANTIC_ERROR");
            i2 |= 41;
        }
        if ((i & 42) == 42) {
            arrayList.add("TFT_SYTAX_ERROR");
            i2 |= 42;
        }
        if ((i & 43) == 43) {
            arrayList.add("UNKNOWN_PDP_CONTEXT");
            i2 |= 43;
        }
        if ((i & 44) == 44) {
            arrayList.add("FILTER_SEMANTIC_ERROR");
            i2 |= 44;
        }
        if ((i & 45) == 45) {
            arrayList.add("FILTER_SYTAX_ERROR");
            i2 |= 45;
        }
        if ((i & 46) == 46) {
            arrayList.add("PDP_WITHOUT_ACTIVE_TFT");
            i2 |= 46;
        }
        if ((i & 50) == 50) {
            arrayList.add("ONLY_IPV4_ALLOWED");
            i2 |= 50;
        }
        if ((i & 51) == 51) {
            arrayList.add("ONLY_IPV6_ALLOWED");
            i2 |= 51;
        }
        if ((i & 52) == 52) {
            arrayList.add("ONLY_SINGLE_BEARER_ALLOWED");
            i2 |= 52;
        }
        if ((i & 53) == 53) {
            arrayList.add("ESM_INFO_NOT_RECEIVED");
            i2 |= 53;
        }
        if ((i & 54) == 54) {
            arrayList.add("PDN_CONN_DOES_NOT_EXIST");
            i2 |= 54;
        }
        if ((i & 55) == 55) {
            arrayList.add("MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED");
            i2 |= 55;
        }
        if ((i & 65) == 65) {
            arrayList.add("MAX_ACTIVE_PDP_CONTEXT_REACHED");
            i2 |= 65;
        }
        if ((i & 66) == 66) {
            arrayList.add("UNSUPPORTED_APN_IN_CURRENT_PLMN");
            i2 |= 66;
        }
        if ((i & 81) == 81) {
            arrayList.add("INVALID_TRANSACTION_ID");
            i2 |= 81;
        }
        if ((i & 95) == 95) {
            arrayList.add("MESSAGE_INCORRECT_SEMANTIC");
            i2 |= 95;
        }
        if ((i & 96) == 96) {
            arrayList.add("INVALID_MANDATORY_INFO");
            i2 |= 96;
        }
        if ((i & 97) == 97) {
            arrayList.add("MESSAGE_TYPE_UNSUPPORTED");
            i2 |= 97;
        }
        if ((i & 98) == 98) {
            arrayList.add("MSG_TYPE_NONCOMPATIBLE_STATE");
            i2 |= 98;
        }
        if ((i & 99) == 99) {
            arrayList.add("UNKNOWN_INFO_ELEMENT");
            i2 |= 99;
        }
        if ((i & 100) == 100) {
            arrayList.add("CONDITIONAL_IE_ERROR");
            i2 |= 100;
        }
        if ((i & 101) == 101) {
            arrayList.add("MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE");
            i2 |= 101;
        }
        if ((i & 111) == 111) {
            arrayList.add("PROTOCOL_ERRORS");
            i2 |= 111;
        }
        if ((i & 112) == 112) {
            arrayList.add("APN_TYPE_CONFLICT");
            i2 |= 112;
        }
        if ((i & 113) == 113) {
            arrayList.add("INVALID_PCSCF_ADDR");
            i2 |= 113;
        }
        if ((i & 114) == 114) {
            arrayList.add("INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN");
            i2 |= 114;
        }
        if ((i & 115) == 115) {
            arrayList.add("EMM_ACCESS_BARRED");
            i2 |= 115;
        }
        if ((i & 116) == 116) {
            arrayList.add("EMERGENCY_IFACE_ONLY");
            i2 |= 116;
        }
        if ((i & 117) == 117) {
            arrayList.add("IFACE_MISMATCH");
            i2 |= 117;
        }
        if ((i & 118) == 118) {
            arrayList.add("COMPANION_IFACE_IN_USE");
            i2 |= 118;
        }
        if ((i & 119) == 119) {
            arrayList.add("IP_ADDRESS_MISMATCH");
            i2 |= 119;
        }
        if ((i & 120) == 120) {
            arrayList.add("IFACE_AND_POL_FAMILY_MISMATCH");
            i2 |= 120;
        }
        if ((i & 121) == 121) {
            arrayList.add("EMM_ACCESS_BARRED_INFINITE_RETRY");
            i2 |= 121;
        }
        if ((i & 122) == 122) {
            arrayList.add("AUTH_FAILURE_ON_EMERGENCY_CALL");
            i2 |= 122;
        }
        if ((i & 4097) == 4097) {
            arrayList.add("OEM_DCFAILCAUSE_1");
            i2 |= 4097;
        }
        if ((i & 4098) == 4098) {
            arrayList.add("OEM_DCFAILCAUSE_2");
            i2 |= 4098;
        }
        if ((i & 4099) == 4099) {
            arrayList.add("OEM_DCFAILCAUSE_3");
            i2 |= 4099;
        }
        if ((i & 4100) == 4100) {
            arrayList.add("OEM_DCFAILCAUSE_4");
            i2 |= 4100;
        }
        if ((i & 4101) == 4101) {
            arrayList.add("OEM_DCFAILCAUSE_5");
            i2 |= 4101;
        }
        if ((i & 4102) == 4102) {
            arrayList.add("OEM_DCFAILCAUSE_6");
            i2 |= 4102;
        }
        if ((i & 4103) == 4103) {
            arrayList.add("OEM_DCFAILCAUSE_7");
            i2 |= 4103;
        }
        if ((i & 4104) == 4104) {
            arrayList.add("OEM_DCFAILCAUSE_8");
            i2 |= 4104;
        }
        if ((i & 4105) == 4105) {
            arrayList.add("OEM_DCFAILCAUSE_9");
            i2 |= 4105;
        }
        if ((i & 4106) == 4106) {
            arrayList.add("OEM_DCFAILCAUSE_10");
            i2 |= 4106;
        }
        if ((i & 4107) == 4107) {
            arrayList.add("OEM_DCFAILCAUSE_11");
            i2 |= 4107;
        }
        if ((i & 4108) == 4108) {
            arrayList.add("OEM_DCFAILCAUSE_12");
            i2 |= 4108;
        }
        if ((i & 4109) == 4109) {
            arrayList.add("OEM_DCFAILCAUSE_13");
            i2 |= 4109;
        }
        if ((i & 4110) == 4110) {
            arrayList.add("OEM_DCFAILCAUSE_14");
            i2 |= 4110;
        }
        if ((i & 4111) == 4111) {
            arrayList.add("OEM_DCFAILCAUSE_15");
            i2 |= 4111;
        }
        if ((i & (-1)) == -1) {
            arrayList.add("VOICE_REGISTRATION_FAIL");
            i2 |= -1;
        }
        if ((i & (-2)) == -2) {
            arrayList.add("DATA_REGISTRATION_FAIL");
            i2 |= -2;
        }
        if ((i & (-3)) == -3) {
            arrayList.add("SIGNAL_LOST");
            i2 |= -3;
        }
        if ((i & (-4)) == -4) {
            arrayList.add("PREF_RADIO_TECH_CHANGED");
            i2 |= -4;
        }
        if ((i & (-5)) == -5) {
            arrayList.add("RADIO_POWER_OFF");
            i2 |= -5;
        }
        if ((i & (-6)) == -6) {
            arrayList.add("TETHERED_CALL_ACTIVE");
            i2 |= -6;
        }
        if ((65535 & i) == 65535) {
            arrayList.add("ERROR_UNSPECIFIED");
            i2 |= 65535;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
