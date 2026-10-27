package android.hardware.radio.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class LastCallFailCause {
    public static final int ACCESS_CLASS_BLOCKED = 260;
    public static final int ACCESS_INFORMATION_DISCARDED = 43;
    public static final int ACM_LIMIT_EXCEEDED = 68;
    public static final int BEARER_CAPABILITY_NOT_AUTHORIZED = 57;
    public static final int BEARER_CAPABILITY_UNAVAILABLE = 58;
    public static final int BEARER_SERVICE_NOT_IMPLEMENTED = 65;
    public static final int BUSY = 17;
    public static final int CALL_BARRED = 240;
    public static final int CALL_REJECTED = 21;
    public static final int CDMA_ACCESS_BLOCKED = 1009;
    public static final int CDMA_ACCESS_FAILURE = 1006;
    public static final int CDMA_DROP = 1001;
    public static final int CDMA_INTERCEPT = 1002;
    public static final int CDMA_LOCKED_UNTIL_POWER_CYCLE = 1000;
    public static final int CDMA_NOT_EMERGENCY = 1008;
    public static final int CDMA_PREEMPTED = 1007;
    public static final int CDMA_REORDER = 1003;
    public static final int CDMA_RETRY_ORDER = 1005;
    public static final int CDMA_SO_REJECT = 1004;
    public static final int CHANNEL_UNACCEPTABLE = 6;
    public static final int CONDITIONAL_IE_ERROR = 100;
    public static final int CONGESTION = 34;
    public static final int DESTINATION_OUT_OF_ORDER = 27;
    public static final int DIAL_MODIFIED_TO_DIAL = 246;
    public static final int DIAL_MODIFIED_TO_SS = 245;
    public static final int DIAL_MODIFIED_TO_USSD = 244;
    public static final int ERROR_UNSPECIFIED = 65535;
    public static final int FACILITY_REJECTED = 29;
    public static final int FDN_BLOCKED = 241;
    public static final int IMEI_NOT_ACCEPTED = 243;
    public static final int IMSI_UNKNOWN_IN_VLR = 242;
    public static final int INCOMING_CALLS_BARRED_WITHIN_CUG = 55;
    public static final int INCOMPATIBLE_DESTINATION = 88;
    public static final int INFORMATION_ELEMENT_NON_EXISTENT = 99;
    public static final int INTERWORKING_UNSPECIFIED = 127;
    public static final int INVALID_MANDATORY_INFORMATION = 96;
    public static final int INVALID_NUMBER_FORMAT = 28;
    public static final int INVALID_TRANSACTION_IDENTIFIER = 81;
    public static final int INVALID_TRANSIT_NW_SELECTION = 91;
    public static final int MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = 101;
    public static final int MESSAGE_TYPE_NON_IMPLEMENTED = 97;
    public static final int MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = 98;
    public static final int NETWORK_DETACH = 261;
    public static final int NETWORK_OUT_OF_ORDER = 38;
    public static final int NETWORK_REJECT = 252;
    public static final int NETWORK_RESP_TIMEOUT = 251;
    public static final int NORMAL = 16;
    public static final int NORMAL_UNSPECIFIED = 31;
    public static final int NO_ANSWER_FROM_USER = 19;
    public static final int NO_ROUTE_TO_DESTINATION = 3;
    public static final int NO_USER_RESPONDING = 18;
    public static final int NO_VALID_SIM = 249;
    public static final int NUMBER_CHANGED = 22;
    public static final int OEM_CAUSE_1 = 61441;
    public static final int OEM_CAUSE_10 = 61450;
    public static final int OEM_CAUSE_11 = 61451;
    public static final int OEM_CAUSE_12 = 61452;
    public static final int OEM_CAUSE_13 = 61453;
    public static final int OEM_CAUSE_14 = 61454;
    public static final int OEM_CAUSE_15 = 61455;
    public static final int OEM_CAUSE_2 = 61442;
    public static final int OEM_CAUSE_3 = 61443;
    public static final int OEM_CAUSE_4 = 61444;
    public static final int OEM_CAUSE_5 = 61445;
    public static final int OEM_CAUSE_6 = 61446;
    public static final int OEM_CAUSE_7 = 61447;
    public static final int OEM_CAUSE_8 = 61448;
    public static final int OEM_CAUSE_9 = 61449;
    public static final int ONLY_DIGITAL_INFORMATION_BEARER_AVAILABLE = 70;
    public static final int OPERATOR_DETERMINED_BARRING = 8;
    public static final int OUT_OF_SERVICE = 248;
    public static final int PREEMPTION = 25;
    public static final int PROTOCOL_ERROR_UNSPECIFIED = 111;
    public static final int QOS_UNAVAILABLE = 49;
    public static final int RADIO_ACCESS_FAILURE = 253;
    public static final int RADIO_INTERNAL_ERROR = 250;
    public static final int RADIO_LINK_FAILURE = 254;
    public static final int RADIO_LINK_LOST = 255;
    public static final int RADIO_OFF = 247;
    public static final int RADIO_RELEASE_ABNORMAL = 259;
    public static final int RADIO_RELEASE_NORMAL = 258;
    public static final int RADIO_SETUP_FAILURE = 257;
    public static final int RADIO_UPLINK_FAILURE = 256;
    public static final int RECOVERY_ON_TIMER_EXPIRED = 102;
    public static final int REQUESTED_CIRCUIT_OR_CHANNEL_NOT_AVAILABLE = 44;
    public static final int REQUESTED_FACILITY_NOT_IMPLEMENTED = 69;
    public static final int REQUESTED_FACILITY_NOT_SUBSCRIBED = 50;
    public static final int RESOURCES_UNAVAILABLE_OR_UNSPECIFIED = 47;
    public static final int RESP_TO_STATUS_ENQUIRY = 30;
    public static final int SEMANTICALLY_INCORRECT_MESSAGE = 95;
    public static final int SERVICE_OPTION_NOT_AVAILABLE = 63;
    public static final int SERVICE_OR_OPTION_NOT_IMPLEMENTED = 79;
    public static final int SWITCHING_EQUIPMENT_CONGESTION = 42;
    public static final int TEMPORARY_FAILURE = 41;
    public static final int UNOBTAINABLE_NUMBER = 1;
    public static final int USER_NOT_MEMBER_OF_CUG = 87;

    public static final String toString(int i) {
        return i == 1 ? "UNOBTAINABLE_NUMBER" : i == 3 ? "NO_ROUTE_TO_DESTINATION" : i == 6 ? "CHANNEL_UNACCEPTABLE" : i == 8 ? "OPERATOR_DETERMINED_BARRING" : i == 16 ? "NORMAL" : i == 17 ? "BUSY" : i == 18 ? "NO_USER_RESPONDING" : i == 19 ? "NO_ANSWER_FROM_USER" : i == 21 ? "CALL_REJECTED" : i == 22 ? "NUMBER_CHANGED" : i == 25 ? "PREEMPTION" : i == 27 ? "DESTINATION_OUT_OF_ORDER" : i == 28 ? "INVALID_NUMBER_FORMAT" : i == 29 ? "FACILITY_REJECTED" : i == 30 ? "RESP_TO_STATUS_ENQUIRY" : i == 31 ? "NORMAL_UNSPECIFIED" : i == 34 ? "CONGESTION" : i == 38 ? "NETWORK_OUT_OF_ORDER" : i == 41 ? "TEMPORARY_FAILURE" : i == 42 ? "SWITCHING_EQUIPMENT_CONGESTION" : i == 43 ? "ACCESS_INFORMATION_DISCARDED" : i == 44 ? "REQUESTED_CIRCUIT_OR_CHANNEL_NOT_AVAILABLE" : i == 47 ? "RESOURCES_UNAVAILABLE_OR_UNSPECIFIED" : i == 49 ? "QOS_UNAVAILABLE" : i == 50 ? "REQUESTED_FACILITY_NOT_SUBSCRIBED" : i == 55 ? "INCOMING_CALLS_BARRED_WITHIN_CUG" : i == 57 ? "BEARER_CAPABILITY_NOT_AUTHORIZED" : i == 58 ? "BEARER_CAPABILITY_UNAVAILABLE" : i == 63 ? "SERVICE_OPTION_NOT_AVAILABLE" : i == 65 ? "BEARER_SERVICE_NOT_IMPLEMENTED" : i == 68 ? "ACM_LIMIT_EXCEEDED" : i == 69 ? "REQUESTED_FACILITY_NOT_IMPLEMENTED" : i == 70 ? "ONLY_DIGITAL_INFORMATION_BEARER_AVAILABLE" : i == 79 ? "SERVICE_OR_OPTION_NOT_IMPLEMENTED" : i == 81 ? "INVALID_TRANSACTION_IDENTIFIER" : i == 87 ? "USER_NOT_MEMBER_OF_CUG" : i == 88 ? "INCOMPATIBLE_DESTINATION" : i == 91 ? "INVALID_TRANSIT_NW_SELECTION" : i == 95 ? "SEMANTICALLY_INCORRECT_MESSAGE" : i == 96 ? "INVALID_MANDATORY_INFORMATION" : i == 97 ? "MESSAGE_TYPE_NON_IMPLEMENTED" : i == 98 ? "MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE" : i == 99 ? "INFORMATION_ELEMENT_NON_EXISTENT" : i == 100 ? "CONDITIONAL_IE_ERROR" : i == 101 ? "MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE" : i == 102 ? "RECOVERY_ON_TIMER_EXPIRED" : i == 111 ? "PROTOCOL_ERROR_UNSPECIFIED" : i == 127 ? "INTERWORKING_UNSPECIFIED" : i == 240 ? "CALL_BARRED" : i == 241 ? "FDN_BLOCKED" : i == 242 ? "IMSI_UNKNOWN_IN_VLR" : i == 243 ? "IMEI_NOT_ACCEPTED" : i == 244 ? "DIAL_MODIFIED_TO_USSD" : i == 245 ? "DIAL_MODIFIED_TO_SS" : i == 246 ? "DIAL_MODIFIED_TO_DIAL" : i == 247 ? "RADIO_OFF" : i == 248 ? "OUT_OF_SERVICE" : i == 249 ? "NO_VALID_SIM" : i == 250 ? "RADIO_INTERNAL_ERROR" : i == 251 ? "NETWORK_RESP_TIMEOUT" : i == 252 ? "NETWORK_REJECT" : i == 253 ? "RADIO_ACCESS_FAILURE" : i == 254 ? "RADIO_LINK_FAILURE" : i == 255 ? "RADIO_LINK_LOST" : i == 256 ? "RADIO_UPLINK_FAILURE" : i == 257 ? "RADIO_SETUP_FAILURE" : i == 258 ? "RADIO_RELEASE_NORMAL" : i == 259 ? "RADIO_RELEASE_ABNORMAL" : i == 260 ? "ACCESS_CLASS_BLOCKED" : i == 261 ? "NETWORK_DETACH" : i == 1000 ? "CDMA_LOCKED_UNTIL_POWER_CYCLE" : i == 1001 ? "CDMA_DROP" : i == 1002 ? "CDMA_INTERCEPT" : i == 1003 ? "CDMA_REORDER" : i == 1004 ? "CDMA_SO_REJECT" : i == 1005 ? "CDMA_RETRY_ORDER" : i == 1006 ? "CDMA_ACCESS_FAILURE" : i == 1007 ? "CDMA_PREEMPTED" : i == 1008 ? "CDMA_NOT_EMERGENCY" : i == 1009 ? "CDMA_ACCESS_BLOCKED" : i == 61441 ? "OEM_CAUSE_1" : i == 61442 ? "OEM_CAUSE_2" : i == 61443 ? "OEM_CAUSE_3" : i == 61444 ? "OEM_CAUSE_4" : i == 61445 ? "OEM_CAUSE_5" : i == 61446 ? "OEM_CAUSE_6" : i == 61447 ? "OEM_CAUSE_7" : i == 61448 ? "OEM_CAUSE_8" : i == 61449 ? "OEM_CAUSE_9" : i == 61450 ? "OEM_CAUSE_10" : i == 61451 ? "OEM_CAUSE_11" : i == 61452 ? "OEM_CAUSE_12" : i == 61453 ? "OEM_CAUSE_13" : i == 61454 ? "OEM_CAUSE_14" : i == 61455 ? "OEM_CAUSE_15" : i == 65535 ? "ERROR_UNSPECIFIED" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if ((i & 1) == 1) {
            arrayList.add("UNOBTAINABLE_NUMBER");
        } else {
            i2 = 0;
        }
        if ((i & 3) == 3) {
            arrayList.add("NO_ROUTE_TO_DESTINATION");
            i2 |= 3;
        }
        if ((i & 6) == 6) {
            arrayList.add("CHANNEL_UNACCEPTABLE");
            i2 |= 6;
        }
        if ((i & 8) == 8) {
            arrayList.add("OPERATOR_DETERMINED_BARRING");
            i2 |= 8;
        }
        if ((i & 16) == 16) {
            arrayList.add("NORMAL");
            i2 |= 16;
        }
        if ((i & 17) == 17) {
            arrayList.add("BUSY");
            i2 |= 17;
        }
        if ((i & 18) == 18) {
            arrayList.add("NO_USER_RESPONDING");
            i2 |= 18;
        }
        if ((i & 19) == 19) {
            arrayList.add("NO_ANSWER_FROM_USER");
            i2 |= 19;
        }
        if ((i & 21) == 21) {
            arrayList.add("CALL_REJECTED");
            i2 |= 21;
        }
        if ((i & 22) == 22) {
            arrayList.add("NUMBER_CHANGED");
            i2 |= 22;
        }
        if ((i & 25) == 25) {
            arrayList.add("PREEMPTION");
            i2 |= 25;
        }
        if ((i & 27) == 27) {
            arrayList.add("DESTINATION_OUT_OF_ORDER");
            i2 |= 27;
        }
        if ((i & 28) == 28) {
            arrayList.add("INVALID_NUMBER_FORMAT");
            i2 |= 28;
        }
        if ((i & 29) == 29) {
            arrayList.add("FACILITY_REJECTED");
            i2 |= 29;
        }
        if ((i & 30) == 30) {
            arrayList.add("RESP_TO_STATUS_ENQUIRY");
            i2 |= 30;
        }
        if ((i & 31) == 31) {
            arrayList.add("NORMAL_UNSPECIFIED");
            i2 |= 31;
        }
        if ((i & 34) == 34) {
            arrayList.add("CONGESTION");
            i2 |= 34;
        }
        if ((i & 38) == 38) {
            arrayList.add("NETWORK_OUT_OF_ORDER");
            i2 |= 38;
        }
        if ((i & 41) == 41) {
            arrayList.add("TEMPORARY_FAILURE");
            i2 |= 41;
        }
        if ((i & 42) == 42) {
            arrayList.add("SWITCHING_EQUIPMENT_CONGESTION");
            i2 |= 42;
        }
        if ((i & 43) == 43) {
            arrayList.add("ACCESS_INFORMATION_DISCARDED");
            i2 |= 43;
        }
        if ((i & 44) == 44) {
            arrayList.add("REQUESTED_CIRCUIT_OR_CHANNEL_NOT_AVAILABLE");
            i2 |= 44;
        }
        if ((i & 47) == 47) {
            arrayList.add("RESOURCES_UNAVAILABLE_OR_UNSPECIFIED");
            i2 |= 47;
        }
        if ((i & 49) == 49) {
            arrayList.add("QOS_UNAVAILABLE");
            i2 |= 49;
        }
        if ((i & 50) == 50) {
            arrayList.add("REQUESTED_FACILITY_NOT_SUBSCRIBED");
            i2 |= 50;
        }
        if ((i & 55) == 55) {
            arrayList.add("INCOMING_CALLS_BARRED_WITHIN_CUG");
            i2 |= 55;
        }
        if ((i & 57) == 57) {
            arrayList.add("BEARER_CAPABILITY_NOT_AUTHORIZED");
            i2 |= 57;
        }
        if ((i & 58) == 58) {
            arrayList.add("BEARER_CAPABILITY_UNAVAILABLE");
            i2 |= 58;
        }
        if ((i & 63) == 63) {
            arrayList.add("SERVICE_OPTION_NOT_AVAILABLE");
            i2 |= 63;
        }
        if ((i & 65) == 65) {
            arrayList.add("BEARER_SERVICE_NOT_IMPLEMENTED");
            i2 |= 65;
        }
        if ((i & 68) == 68) {
            arrayList.add("ACM_LIMIT_EXCEEDED");
            i2 |= 68;
        }
        if ((i & 69) == 69) {
            arrayList.add("REQUESTED_FACILITY_NOT_IMPLEMENTED");
            i2 |= 69;
        }
        if ((i & 70) == 70) {
            arrayList.add("ONLY_DIGITAL_INFORMATION_BEARER_AVAILABLE");
            i2 |= 70;
        }
        if ((i & 79) == 79) {
            arrayList.add("SERVICE_OR_OPTION_NOT_IMPLEMENTED");
            i2 |= 79;
        }
        if ((i & 81) == 81) {
            arrayList.add("INVALID_TRANSACTION_IDENTIFIER");
            i2 |= 81;
        }
        if ((i & 87) == 87) {
            arrayList.add("USER_NOT_MEMBER_OF_CUG");
            i2 |= 87;
        }
        if ((i & 88) == 88) {
            arrayList.add("INCOMPATIBLE_DESTINATION");
            i2 |= 88;
        }
        if ((i & 91) == 91) {
            arrayList.add("INVALID_TRANSIT_NW_SELECTION");
            i2 |= 91;
        }
        if ((i & 95) == 95) {
            arrayList.add("SEMANTICALLY_INCORRECT_MESSAGE");
            i2 |= 95;
        }
        if ((i & 96) == 96) {
            arrayList.add("INVALID_MANDATORY_INFORMATION");
            i2 |= 96;
        }
        if ((i & 97) == 97) {
            arrayList.add("MESSAGE_TYPE_NON_IMPLEMENTED");
            i2 |= 97;
        }
        if ((i & 98) == 98) {
            arrayList.add("MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE");
            i2 |= 98;
        }
        if ((i & 99) == 99) {
            arrayList.add("INFORMATION_ELEMENT_NON_EXISTENT");
            i2 |= 99;
        }
        if ((i & 100) == 100) {
            arrayList.add("CONDITIONAL_IE_ERROR");
            i2 |= 100;
        }
        if ((i & 101) == 101) {
            arrayList.add("MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE");
            i2 |= 101;
        }
        if ((i & 102) == 102) {
            arrayList.add("RECOVERY_ON_TIMER_EXPIRED");
            i2 |= 102;
        }
        if ((i & 111) == 111) {
            arrayList.add("PROTOCOL_ERROR_UNSPECIFIED");
            i2 |= 111;
        }
        if ((i & 127) == 127) {
            arrayList.add("INTERWORKING_UNSPECIFIED");
            i2 |= 127;
        }
        if ((i & 240) == 240) {
            arrayList.add("CALL_BARRED");
            i2 |= 240;
        }
        if ((i & 241) == 241) {
            arrayList.add("FDN_BLOCKED");
            i2 |= 241;
        }
        if ((i & 242) == 242) {
            arrayList.add("IMSI_UNKNOWN_IN_VLR");
            i2 |= 242;
        }
        if ((i & 243) == 243) {
            arrayList.add("IMEI_NOT_ACCEPTED");
            i2 |= 243;
        }
        if ((i & 244) == 244) {
            arrayList.add("DIAL_MODIFIED_TO_USSD");
            i2 |= 244;
        }
        if ((i & 245) == 245) {
            arrayList.add("DIAL_MODIFIED_TO_SS");
            i2 |= 245;
        }
        if ((i & 246) == 246) {
            arrayList.add("DIAL_MODIFIED_TO_DIAL");
            i2 |= 246;
        }
        if ((i & 247) == 247) {
            arrayList.add("RADIO_OFF");
            i2 |= 247;
        }
        if ((i & 248) == 248) {
            arrayList.add("OUT_OF_SERVICE");
            i2 |= 248;
        }
        if ((i & 249) == 249) {
            arrayList.add("NO_VALID_SIM");
            i2 |= 249;
        }
        if ((i & 250) == 250) {
            arrayList.add("RADIO_INTERNAL_ERROR");
            i2 |= 250;
        }
        if ((i & 251) == 251) {
            arrayList.add("NETWORK_RESP_TIMEOUT");
            i2 |= 251;
        }
        if ((i & 252) == 252) {
            arrayList.add("NETWORK_REJECT");
            i2 |= 252;
        }
        if ((i & 253) == 253) {
            arrayList.add("RADIO_ACCESS_FAILURE");
            i2 |= 253;
        }
        if ((i & 254) == 254) {
            arrayList.add("RADIO_LINK_FAILURE");
            i2 |= 254;
        }
        if ((i & 255) == 255) {
            arrayList.add("RADIO_LINK_LOST");
            i2 |= 255;
        }
        if ((i & 256) == 256) {
            arrayList.add("RADIO_UPLINK_FAILURE");
            i2 |= 256;
        }
        if ((i & 257) == 257) {
            arrayList.add("RADIO_SETUP_FAILURE");
            i2 |= 257;
        }
        if ((i & 258) == 258) {
            arrayList.add("RADIO_RELEASE_NORMAL");
            i2 |= 258;
        }
        if ((i & 259) == 259) {
            arrayList.add("RADIO_RELEASE_ABNORMAL");
            i2 |= 259;
        }
        if ((i & 260) == 260) {
            arrayList.add("ACCESS_CLASS_BLOCKED");
            i2 |= 260;
        }
        if ((i & 261) == 261) {
            arrayList.add("NETWORK_DETACH");
            i2 |= 261;
        }
        if ((i & 1000) == 1000) {
            arrayList.add("CDMA_LOCKED_UNTIL_POWER_CYCLE");
            i2 |= 1000;
        }
        if ((i & 1001) == 1001) {
            arrayList.add("CDMA_DROP");
            i2 |= 1001;
        }
        if ((i & 1002) == 1002) {
            arrayList.add("CDMA_INTERCEPT");
            i2 |= 1002;
        }
        if ((i & 1003) == 1003) {
            arrayList.add("CDMA_REORDER");
            i2 |= 1003;
        }
        if ((i & 1004) == 1004) {
            arrayList.add("CDMA_SO_REJECT");
            i2 |= 1004;
        }
        if ((i & 1005) == 1005) {
            arrayList.add("CDMA_RETRY_ORDER");
            i2 |= 1005;
        }
        if ((i & 1006) == 1006) {
            arrayList.add("CDMA_ACCESS_FAILURE");
            i2 |= 1006;
        }
        if ((i & 1007) == 1007) {
            arrayList.add("CDMA_PREEMPTED");
            i2 |= 1007;
        }
        if ((i & 1008) == 1008) {
            arrayList.add("CDMA_NOT_EMERGENCY");
            i2 |= 1008;
        }
        if ((i & 1009) == 1009) {
            arrayList.add("CDMA_ACCESS_BLOCKED");
            i2 |= 1009;
        }
        if ((61441 & i) == 61441) {
            arrayList.add("OEM_CAUSE_1");
            i2 |= 61441;
        }
        if ((61442 & i) == 61442) {
            arrayList.add("OEM_CAUSE_2");
            i2 |= 61442;
        }
        if ((61443 & i) == 61443) {
            arrayList.add("OEM_CAUSE_3");
            i2 |= 61443;
        }
        if ((61444 & i) == 61444) {
            arrayList.add("OEM_CAUSE_4");
            i2 |= 61444;
        }
        if ((61445 & i) == 61445) {
            arrayList.add("OEM_CAUSE_5");
            i2 |= 61445;
        }
        if ((61446 & i) == 61446) {
            arrayList.add("OEM_CAUSE_6");
            i2 |= 61446;
        }
        if ((61447 & i) == 61447) {
            arrayList.add("OEM_CAUSE_7");
            i2 |= 61447;
        }
        if ((61448 & i) == 61448) {
            arrayList.add("OEM_CAUSE_8");
            i2 |= 61448;
        }
        if ((61449 & i) == 61449) {
            arrayList.add("OEM_CAUSE_9");
            i2 |= 61449;
        }
        if ((61450 & i) == 61450) {
            arrayList.add("OEM_CAUSE_10");
            i2 |= 61450;
        }
        if ((61451 & i) == 61451) {
            arrayList.add("OEM_CAUSE_11");
            i2 |= 61451;
        }
        if ((61452 & i) == 61452) {
            arrayList.add("OEM_CAUSE_12");
            i2 |= 61452;
        }
        if ((61453 & i) == 61453) {
            arrayList.add("OEM_CAUSE_13");
            i2 |= 61453;
        }
        if ((61454 & i) == 61454) {
            arrayList.add("OEM_CAUSE_14");
            i2 |= 61454;
        }
        if ((61455 & i) == 61455) {
            arrayList.add("OEM_CAUSE_15");
            i2 |= 61455;
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
