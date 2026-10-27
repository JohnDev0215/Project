package android.hardware.radio.V1_5;

import android.security.keystore.KeyProperties;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class RegistrationFailCause {
    public static final int CALL_CANNOT_BE_IDENTIFIED = 38;
    public static final int CONDITIONAL_IE_ERROR = 100;
    public static final int CONGESTION = 22;
    public static final int GPRS_AND_NON_GPRS_SERVICES_NOT_ALLOWED = 8;
    public static final int GPRS_SERVICES_NOT_ALLOWED = 7;
    public static final int GPRS_SERVICES_NOT_ALLOWED_IN_PLMN = 14;
    public static final int GSM_AUTHENTICATION_UNACCEPTABLE = 23;
    public static final int ILLEGAL_ME = 6;
    public static final int ILLEGAL_MS = 3;
    public static final int IMEI_NOT_ACCEPTED = 5;
    public static final int IMPLICITLY_DETACHED = 10;
    public static final int IMSI_UNKNOWN_IN_HLR = 2;
    public static final int IMSI_UNKNOWN_IN_VLR = 4;
    public static final int INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED = 99;
    public static final int INVALID_MANDATORY_INFORMATION = 96;
    public static final int LOCATION_AREA_NOT_ALLOWED = 12;
    public static final int MAC_FAILURE = 20;
    public static final int MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = 101;
    public static final int MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED = 97;
    public static final int MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE = 98;
    public static final int MSC_TEMPORARILY_NOT_REACHABLE = 15;
    public static final int MS_IDENTITY_CANNOT_BE_DERIVED_BY_NETWORK = 9;
    public static final int NETWORK_FAILURE = 17;
    public static final int NONE = 0;
    public static final int NOT_AUTHORIZED_FOR_THIS_CSG = 25;
    public static final int NO_PDP_CONTEXT_ACTIVATED = 40;
    public static final int NO_SUITABLE_CELLS = 15;
    public static final int PLMN_NOT_ALLOWED = 11;
    public static final int PROTOCOL_ERROR_UNSPECIFIED = 111;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_1 = 48;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_10 = 57;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_11 = 58;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_12 = 59;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_13 = 60;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_14 = 61;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_15 = 62;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_16 = 63;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_2 = 49;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_3 = 50;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_4 = 51;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_5 = 52;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_6 = 53;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_7 = 54;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_8 = 55;
    public static final int RETRY_UPON_ENTRY_INTO_NEW_CELL_9 = 56;
    public static final int ROAMING_NOT_ALLOWED = 13;
    public static final int SEMANTICALLY_INCORRECT_MESSAGE = 95;
    public static final int SERVICE_OPTION_NOT_SUBSCRIBED = 33;
    public static final int SERVICE_OPTION_NOT_SUPPORTED = 32;
    public static final int SERVICE_OPTION_TEMPORARILY_OUT_OF_ORDER = 34;
    public static final int SMS_PROVIDED_BY_GPRS_IN_ROUTING_AREA = 26;
    public static final int SYNC_FAILURE = 21;

    public static final String toString(int i) {
        return i == 0 ? KeyProperties.DIGEST_NONE : i == 2 ? "IMSI_UNKNOWN_IN_HLR" : i == 3 ? "ILLEGAL_MS" : i == 4 ? "IMSI_UNKNOWN_IN_VLR" : i == 5 ? "IMEI_NOT_ACCEPTED" : i == 6 ? "ILLEGAL_ME" : i == 7 ? "GPRS_SERVICES_NOT_ALLOWED" : i == 8 ? "GPRS_AND_NON_GPRS_SERVICES_NOT_ALLOWED" : i == 9 ? "MS_IDENTITY_CANNOT_BE_DERIVED_BY_NETWORK" : i == 10 ? "IMPLICITLY_DETACHED" : i == 11 ? "PLMN_NOT_ALLOWED" : i == 12 ? "LOCATION_AREA_NOT_ALLOWED" : i == 13 ? "ROAMING_NOT_ALLOWED" : i == 14 ? "GPRS_SERVICES_NOT_ALLOWED_IN_PLMN" : i == 15 ? "NO_SUITABLE_CELLS" : i == 15 ? "MSC_TEMPORARILY_NOT_REACHABLE" : i == 17 ? "NETWORK_FAILURE" : i == 20 ? "MAC_FAILURE" : i == 21 ? "SYNC_FAILURE" : i == 22 ? "CONGESTION" : i == 23 ? "GSM_AUTHENTICATION_UNACCEPTABLE" : i == 25 ? "NOT_AUTHORIZED_FOR_THIS_CSG" : i == 26 ? "SMS_PROVIDED_BY_GPRS_IN_ROUTING_AREA" : i == 32 ? "SERVICE_OPTION_NOT_SUPPORTED" : i == 33 ? "SERVICE_OPTION_NOT_SUBSCRIBED" : i == 34 ? "SERVICE_OPTION_TEMPORARILY_OUT_OF_ORDER" : i == 38 ? "CALL_CANNOT_BE_IDENTIFIED" : i == 40 ? "NO_PDP_CONTEXT_ACTIVATED" : i == 48 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_1" : i == 49 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_2" : i == 50 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_3" : i == 51 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_4" : i == 52 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_5" : i == 53 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_6" : i == 54 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_7" : i == 55 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_8" : i == 56 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_9" : i == 57 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_10" : i == 58 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_11" : i == 59 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_12" : i == 60 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_13" : i == 61 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_14" : i == 62 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_15" : i == 63 ? "RETRY_UPON_ENTRY_INTO_NEW_CELL_16" : i == 95 ? "SEMANTICALLY_INCORRECT_MESSAGE" : i == 96 ? "INVALID_MANDATORY_INFORMATION" : i == 97 ? "MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED" : i == 98 ? "MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE" : i == 99 ? "INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED" : i == 100 ? "CONDITIONAL_IE_ERROR" : i == 101 ? "MESSAGE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE" : i == 111 ? "PROTOCOL_ERROR_UNSPECIFIED" : "0x" + Integer.toHexString(i);
    }

    public static final String dumpBitfield(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(KeyProperties.DIGEST_NONE);
        int i2 = 2;
        if ((i & 2) == 2) {
            arrayList.add("IMSI_UNKNOWN_IN_HLR");
        } else {
            i2 = 0;
        }
        if ((i & 3) == 3) {
            arrayList.add("ILLEGAL_MS");
            i2 |= 3;
        }
        if ((i & 4) == 4) {
            arrayList.add("IMSI_UNKNOWN_IN_VLR");
            i2 |= 4;
        }
        if ((i & 5) == 5) {
            arrayList.add("IMEI_NOT_ACCEPTED");
            i2 |= 5;
        }
        if ((i & 6) == 6) {
            arrayList.add("ILLEGAL_ME");
            i2 |= 6;
        }
        if ((i & 7) == 7) {
            arrayList.add("GPRS_SERVICES_NOT_ALLOWED");
            i2 |= 7;
        }
        if ((i & 8) == 8) {
            arrayList.add("GPRS_AND_NON_GPRS_SERVICES_NOT_ALLOWED");
            i2 |= 8;
        }
        if ((i & 9) == 9) {
            arrayList.add("MS_IDENTITY_CANNOT_BE_DERIVED_BY_NETWORK");
            i2 |= 9;
        }
        if ((i & 10) == 10) {
            arrayList.add("IMPLICITLY_DETACHED");
            i2 |= 10;
        }
        if ((i & 11) == 11) {
            arrayList.add("PLMN_NOT_ALLOWED");
            i2 |= 11;
        }
        if ((i & 12) == 12) {
            arrayList.add("LOCATION_AREA_NOT_ALLOWED");
            i2 |= 12;
        }
        if ((i & 13) == 13) {
            arrayList.add("ROAMING_NOT_ALLOWED");
            i2 |= 13;
        }
        if ((i & 14) == 14) {
            arrayList.add("GPRS_SERVICES_NOT_ALLOWED_IN_PLMN");
            i2 |= 14;
        }
        int i3 = i & 15;
        if (i3 == 15) {
            arrayList.add("NO_SUITABLE_CELLS");
            i2 |= 15;
        }
        if (i3 == 15) {
            arrayList.add("MSC_TEMPORARILY_NOT_REACHABLE");
            i2 |= 15;
        }
        if ((i & 17) == 17) {
            arrayList.add("NETWORK_FAILURE");
            i2 |= 17;
        }
        if ((i & 20) == 20) {
            arrayList.add("MAC_FAILURE");
            i2 |= 20;
        }
        if ((i & 21) == 21) {
            arrayList.add("SYNC_FAILURE");
            i2 |= 21;
        }
        if ((i & 22) == 22) {
            arrayList.add("CONGESTION");
            i2 |= 22;
        }
        if ((i & 23) == 23) {
            arrayList.add("GSM_AUTHENTICATION_UNACCEPTABLE");
            i2 |= 23;
        }
        if ((i & 25) == 25) {
            arrayList.add("NOT_AUTHORIZED_FOR_THIS_CSG");
            i2 |= 25;
        }
        if ((i & 26) == 26) {
            arrayList.add("SMS_PROVIDED_BY_GPRS_IN_ROUTING_AREA");
            i2 |= 26;
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
            arrayList.add("SERVICE_OPTION_TEMPORARILY_OUT_OF_ORDER");
            i2 |= 34;
        }
        if ((i & 38) == 38) {
            arrayList.add("CALL_CANNOT_BE_IDENTIFIED");
            i2 |= 38;
        }
        if ((i & 40) == 40) {
            arrayList.add("NO_PDP_CONTEXT_ACTIVATED");
            i2 |= 40;
        }
        if ((i & 48) == 48) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_1");
            i2 |= 48;
        }
        if ((i & 49) == 49) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_2");
            i2 |= 49;
        }
        if ((i & 50) == 50) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_3");
            i2 |= 50;
        }
        if ((i & 51) == 51) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_4");
            i2 |= 51;
        }
        if ((i & 52) == 52) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_5");
            i2 |= 52;
        }
        if ((i & 53) == 53) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_6");
            i2 |= 53;
        }
        if ((i & 54) == 54) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_7");
            i2 |= 54;
        }
        if ((i & 55) == 55) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_8");
            i2 |= 55;
        }
        if ((i & 56) == 56) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_9");
            i2 |= 56;
        }
        if ((i & 57) == 57) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_10");
            i2 |= 57;
        }
        if ((i & 58) == 58) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_11");
            i2 |= 58;
        }
        if ((i & 59) == 59) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_12");
            i2 |= 59;
        }
        if ((i & 60) == 60) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_13");
            i2 |= 60;
        }
        if ((i & 61) == 61) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_14");
            i2 |= 61;
        }
        if ((i & 62) == 62) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_15");
            i2 |= 62;
        }
        if ((i & 63) == 63) {
            arrayList.add("RETRY_UPON_ENTRY_INTO_NEW_CELL_16");
            i2 |= 63;
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
            arrayList.add("MESSAGE_TYPE_NON_EXISTENT_OR_NOT_IMPLEMENTED");
            i2 |= 97;
        }
        if ((i & 98) == 98) {
            arrayList.add("MESSAGE_TYPE_NOT_COMPATIBLE_WITH_PROTOCOL_STATE");
            i2 |= 98;
        }
        if ((i & 99) == 99) {
            arrayList.add("INFORMATION_ELEMENT_NON_EXISTENT_OR_NOT_IMPLEMENTED");
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
        if ((i & 111) == 111) {
            arrayList.add("PROTOCOL_ERROR_UNSPECIFIED");
            i2 |= 111;
        }
        if (i != i2) {
            arrayList.add("0x" + Integer.toHexString(i & (~i2)));
        }
        return String.join(" | ", arrayList);
    }
}
