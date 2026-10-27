package com.android.services.telephony;

import android.content.Context;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.telecom.DisconnectCause;
import android.telephony.SubscriptionManager;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.phone.ImsUtil;
import com.android.phone.PhoneGlobals;
import com.android.phone.R;

/* JADX INFO: loaded from: classes.dex */
public class DisconnectCauseUtil {
    public static DisconnectCause toTelecomDisconnectCause(int i) {
        return toTelecomDisconnectCause(i, -1, (String) null);
    }

    public static DisconnectCause toTelecomDisconnectCause(int i, String str) {
        return toTelecomDisconnectCause(i, -1, str);
    }

    public static DisconnectCause toTelecomDisconnectCause(int i, int i2, String str) {
        return toTelecomDisconnectCause(i, i2, str, SubscriptionManager.getDefaultVoicePhoneId());
    }

    public static DisconnectCause toTelecomDisconnectCause(int i, String str, int i2) {
        return toTelecomDisconnectCause(i, -1, str, i2);
    }

    public static DisconnectCause toTelecomDisconnectCause(int i, int i2, String str, int i3) {
        PhoneGlobals phoneGlobals = PhoneGlobals.getInstance();
        return new DisconnectCause(toTelecomDisconnectCauseCode(i), toTelecomDisconnectCauseLabel(phoneGlobals, i, i2), toTelecomDisconnectCauseDescription(phoneGlobals, i, i3), toTelecomDisconnectReason(phoneGlobals, i, str, i3), toTelecomDisconnectCauseTone(i, i3));
    }

    private static int toTelecomDisconnectCauseCode(int i) {
        switch (i) {
            case -1:
            case 0:
                break;
            case 1:
                break;
            case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
            case 65:
                break;
            case 3:
            case 80:
                break;
            case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                break;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 17:
            case 18:
            case 19:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 36:
            case 38:
            case 40:
            case 41:
            case 43:
            case 46:
            case 47:
            case 48:
            case 49:
            case 53:
            case 54:
            case 55:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
                break;
            case 6:
            case 39:
            case 42:
            case 45:
                break;
            case 15:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 34:
            case 35:
            case 37:
            case 50:
                break;
            case 16:
                break;
            case 44:
                break;
            case 51:
                break;
            case 52:
                break;
            case 56:
            case 63:
            case 64:
            default:
                Log.w("DisconnectCauseUtil.toTelecomDisconnectCauseCode", "Unrecognized Telephony DisconnectCause " + i, new Object[0]);
                break;
        }
        return 0;
    }

    private static CharSequence toTelecomDisconnectCauseLabel(Context context, int i, int i2) {
        if (i2 != -1) {
            return getLabelFromPreciseDisconnectCause(context, i2, i);
        }
        return getLabelFromDisconnectCause(context, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.CharSequence getLabelFromDisconnectCause(android.content.Context r3, int r4) {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.services.telephony.DisconnectCauseUtil.getLabelFromDisconnectCause(android.content.Context, int):java.lang.CharSequence");
    }

    /* JADX WARN: Removed duplicated region for block: B:84:0x01fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.CharSequence getLabelFromPreciseDisconnectCause(android.content.Context r1, int r2, int r3) {
        /*
            Method dump skipped, instruction units count: 680
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.services.telephony.DisconnectCauseUtil.getLabelFromPreciseDisconnectCause(android.content.Context, int, int):java.lang.CharSequence");
    }

    private static CharSequence toTelecomDisconnectCauseDescription(Context context, int i, int i2) {
        if (context == null) {
            return "";
        }
        Integer numValueOf = null;
        switch (i) {
            case 17:
                if (ImsUtil.shouldPromoteWfc(context, i2)) {
                    numValueOf = Integer.valueOf(R.string.incall_error_promote_wfc);
                } else if (ImsUtil.isWfcModeWifiOnly(context, i2)) {
                    numValueOf = Integer.valueOf(R.string.incall_error_wfc_only_no_wireless_network);
                } else if (ImsUtil.isWfcEnabled(context, i2)) {
                    numValueOf = Integer.valueOf(R.string.incall_error_power_off_wfc);
                } else {
                    numValueOf = Integer.valueOf(R.string.incall_error_power_off);
                }
                break;
            case 18:
                if (ImsUtil.shouldPromoteWfc(context, i2)) {
                    numValueOf = Integer.valueOf(R.string.incall_error_promote_wfc);
                } else if (ImsUtil.isWfcModeWifiOnly(context, i2)) {
                    numValueOf = Integer.valueOf(R.string.incall_error_wfc_only_no_wireless_network);
                } else if (ImsUtil.isWfcEnabled(context, i2)) {
                    numValueOf = Integer.valueOf(R.string.incall_error_out_of_service_wfc);
                } else {
                    numValueOf = Integer.valueOf(R.string.incall_error_out_of_service);
                }
                break;
            case 20:
                numValueOf = Integer.valueOf(R.string.callFailed_cb_enabled);
                break;
            case 21:
                numValueOf = Integer.valueOf(R.string.callFailed_fdn_only);
                break;
            case 22:
                numValueOf = Integer.valueOf(R.string.callFailed_dsac_restricted);
                break;
            case 23:
                numValueOf = Integer.valueOf(R.string.callFailed_dsac_restricted_normal);
                break;
            case 24:
                numValueOf = Integer.valueOf(R.string.callFailed_dsac_restricted_emergency);
                break;
            case 29:
                numValueOf = Integer.valueOf(R.string.callFailed_NetworkBusy);
                break;
            case 34:
                numValueOf = Integer.valueOf(R.string.incall_error_ecm_emergency_only);
                break;
            case 37:
                numValueOf = Integer.valueOf(R.string.incall_error_emergency_only);
                break;
            case 38:
                numValueOf = Integer.valueOf(R.string.incall_error_no_phone_number_supplied);
                break;
            case 40:
                numValueOf = Integer.valueOf(R.string.incall_error_missing_voicemail_number);
                break;
            case 43:
                numValueOf = Integer.valueOf(R.string.incall_error_call_failed);
                break;
            case 46:
                numValueOf = Integer.valueOf(R.string.callFailed_dialToUssd);
                break;
            case 47:
                numValueOf = Integer.valueOf(R.string.callFailed_dialToSs);
                break;
            case 48:
                numValueOf = Integer.valueOf(R.string.callFailed_dialToDial);
                break;
            case 49:
                numValueOf = Integer.valueOf(R.string.callFailed_cdma_activation);
                break;
            case 50:
                numValueOf = Integer.valueOf(R.string.callFailed_video_call_tty_enabled);
                break;
            case 51:
                numValueOf = Integer.valueOf(R.string.callEnded_pulled);
                break;
            case 53:
                numValueOf = Integer.valueOf(R.string.callFailed_maximum_reached);
                break;
            case 54:
                numValueOf = Integer.valueOf(R.string.callFailed_data_disabled);
                break;
            case 55:
                numValueOf = Integer.valueOf(R.string.callFailed_data_limit_reached_description);
                break;
            case 57:
                numValueOf = Integer.valueOf(android.R.string.indeterminate_progress_44);
                break;
            case 58:
                numValueOf = Integer.valueOf(R.string.callFailed_imei_not_accepted);
                break;
            case 59:
                numValueOf = Integer.valueOf(R.string.callFailed_wifi_lost);
                break;
            case 60:
                numValueOf = Integer.valueOf(R.string.callFailed_NetworkCongested);
                break;
            case 61:
                numValueOf = Integer.valueOf(R.string.callFailed_low_battery);
                break;
            case 62:
                numValueOf = Integer.valueOf(R.string.dialFailed_low_battery);
                break;
            case 66:
                numValueOf = Integer.valueOf(R.string.callFailed_dialToDialVideo);
                break;
            case 67:
                numValueOf = Integer.valueOf(R.string.callFailed_dialVideoToSs);
                break;
            case 68:
                numValueOf = Integer.valueOf(R.string.callFailed_dialVideoToUssd);
                break;
            case 69:
                numValueOf = Integer.valueOf(R.string.callFailed_dialVideoToDial);
                break;
            case 70:
                numValueOf = Integer.valueOf(R.string.callFailed_dialVideoToDialVideo);
                break;
            case 71:
                int i3 = Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0);
                numValueOf = Integer.valueOf(R.string.incall_error_call_failed);
                if (i3 != 0) {
                    numValueOf = Integer.valueOf(R.string.incall_error_power_off);
                }
                break;
            case 72:
                numValueOf = Integer.valueOf(R.string.callFailed_already_dialing);
                break;
            case 73:
                numValueOf = Integer.valueOf(R.string.callFailed_already_ringing);
                break;
            case 74:
                numValueOf = Integer.valueOf(R.string.callFailed_calling_disabled);
                break;
            case 75:
                numValueOf = Integer.valueOf(R.string.callFailed_too_many_calls);
                break;
            case 76:
                numValueOf = Integer.valueOf(R.string.callFailed_otasp_provisioning_in_process);
                break;
            case 78:
                numValueOf = Integer.valueOf(R.string.callFailed_emergency_call_over_wfc_not_available);
                break;
            case 79:
                numValueOf = Integer.valueOf(R.string.callFailed_wfc_service_not_available_in_this_location);
                break;
        }
        return numValueOf == null ? "" : context.getResources().getString(numValueOf.intValue());
    }

    private static String toTelecomDisconnectReason(Context context, int i, String str, int i2) {
        if (context == null) {
            return "";
        }
        if (i == 17 || i == 18) {
            if (ImsUtil.shouldPromoteWfc(context, i2)) {
                return "REASON_WIFI_ON_BUT_WFC_OFF";
            }
        } else {
            if (i == 60) {
                return "REASON_IMS_ACCESS_BLOCKED";
            }
            if (i == 80) {
                return "REASON_EMERGENCY_CALL_PLACED";
            }
        }
        String string = android.telephony.DisconnectCause.toString(i);
        if (str == null) {
            return string;
        }
        return str + ", " + string;
    }

    private static int toTelecomDisconnectCauseTone(int i, int i2) {
        PersistableBundle carrierConfig;
        Phone phone = PhoneFactory.getPhone(i2);
        if (phone != null) {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfigForSubId(phone.getSubId());
        } else {
            carrierConfig = PhoneGlobals.getInstance().getCarrierConfig();
        }
        for (int i3 : carrierConfig.getIntArray("disconnect_cause_play_busytone_int_array")) {
            if (i3 == i) {
                return 17;
            }
        }
        if (i == 5) {
            return 18;
        }
        if (i == 18) {
            return 95;
        }
        if (i == 25) {
            return 21;
        }
        if (i == 45) {
            return -1;
        }
        switch (i) {
            case 27:
                return 95;
            case 28:
                return 37;
            case 29:
                return 38;
            default:
                return 27;
        }
    }
}
