package com.android.phone.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.telephony.MmiCode;
import com.android.internal.telephony.Phone;
import com.android.phone.CarrierXmlParser;
import com.android.phone.PhoneGlobals;
import com.android.phone.PhoneUtils;
import com.android.phone.R;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class SuppServicesUiUtil {
    public static Dialog showBlockingSuppServicesDialog(final Context context, Phone phone, String str) {
        if (context == null || phone == null) {
            return null;
        }
        String strMakeMessage = makeMessage(context, str, phone);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder.setMessage(strMakeMessage).setNeutralButton(context.getResources().getString(R.string.settings_label), new DialogInterface.OnClickListener() { // from class: com.android.phone.settings.SuppServicesUiUtil.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setComponent(new ComponentName(context.getString(R.string.mobile_network_settings_package), context.getString(R.string.mobile_network_settings_class)));
                context.startActivity(intent);
            }
        }).setPositiveButton(context.getResources().getString(R.string.supp_service_over_ut_precautions_dialog_dismiss), (DialogInterface.OnClickListener) null).create();
    }

    private static String makeMessage(Context context, String str, Phone phone) {
        int i = phone.getPhoneId() == 0 ? 1 : 2;
        String suppServiceName = getSuppServiceName(context, str);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        boolean zIsNetworkRoaming = telephonyManager.isNetworkRoaming(phone.getSubId());
        if (!(telephonyManager.getSimCount() > 1)) {
            return zIsNetworkRoaming ? context.getResources().getString(R.string.supp_service_over_ut_precautions_roaming, suppServiceName) : context.getResources().getString(R.string.supp_service_over_ut_precautions, suppServiceName);
        }
        if (zIsNetworkRoaming) {
            return context.getResources().getString(R.string.supp_service_over_ut_precautions_roaming_dual_sim, suppServiceName, Integer.valueOf(i));
        }
        return context.getResources().getString(R.string.supp_service_over_ut_precautions_dual_sim, suppServiceName, Integer.valueOf(i));
    }

    private static String getSuppServiceName(Context context, String str) {
        if (str.equals("call_forwarding_key")) {
            return context.getResources().getString(R.string.labelCF);
        }
        if (str.equals("call_barring_key")) {
            return context.getResources().getString(R.string.labelCallBarring);
        }
        if (str.equals("button_clir_key")) {
            return context.getResources().getString(R.string.labelCallerId);
        }
        return str.equals("button_cw_key") ? context.getResources().getString(R.string.labelCW) : "";
    }

    public static boolean isSsOverUtPrecautions(Context context, Phone phone) {
        if (phone == null || context == null) {
            return false;
        }
        return isMobileDataOff(context, phone) || isDataRoamingOffUnderRoaming(context, phone);
    }

    private static boolean isMobileDataOff(Context context, Phone phone) {
        return !((TelephonyManager) context.getSystemService("phone")).getDataEnabled(phone.getSubId());
    }

    private static boolean isDataRoamingOffUnderRoaming(Context context, Phone phone) {
        return ((TelephonyManager) context.getSystemService("phone")).isNetworkRoaming(phone.getSubId()) && !phone.getDataRoamingEnabled();
    }

    public static CharSequence handleCallerIdUssdResponse(final PhoneGlobals phoneGlobals, final Context context, final Phone phone, MmiCode mmiCode) {
        if (TextUtils.isEmpty(mmiCode.getDialString())) {
            return mmiCode.getMessage();
        }
        int simCarrierId = new TelephonyManager(context, phone.getSubId()).getSimCarrierId();
        if (simCarrierId == -1) {
            return mmiCode.getMessage();
        }
        CarrierXmlParser carrierXmlParser = new CarrierXmlParser(context, simCarrierId);
        final CarrierXmlParser.SsEntry.SSAction callerIdUssdCommandAction = carrierXmlParser.getCallerIdUssdCommandAction(mmiCode.getDialString());
        Log.d("SuppServicesUiUtil", "handleCallerIdUssdResponse: ssAction =" + callerIdUssdCommandAction);
        if (callerIdUssdCommandAction == CarrierXmlParser.SsEntry.SSAction.UNKNOWN) {
            return mmiCode.getMessage();
        }
        HashMap<String, String> responseSet = carrierXmlParser.getFeature("callerid").getResponseSet(callerIdUssdCommandAction, mmiCode.getMessage().toString());
        Log.d("SuppServicesUiUtil", "handleCallerIdUssdResponse: analysisResult =" + responseSet);
        if (responseSet.get("status_code").equals("ok")) {
            new Thread(new Runnable() { // from class: com.android.phone.settings.SuppServicesUiUtil.2
                @Override // java.lang.Runnable
                public void run() {
                    ((TelephonyManager) context.getSystemService("phone")).sendUssdRequest(callerIdUssdCommandAction == CarrierXmlParser.SsEntry.SSAction.UPDATE_ACTIVATE ? "#31#" : "*31#", new TelephonyManager.UssdResponseCallback() { // from class: com.android.phone.settings.SuppServicesUiUtil.2.1
                        @Override // android.telephony.TelephonyManager.UssdResponseCallback
                        public void onReceiveUssdResponse(TelephonyManager telephonyManager, String str, CharSequence charSequence) {
                            Log.d("SuppServicesUiUtil", "handleCallerIdUssdResponse: response =" + charSequence.toString());
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            PhoneUtils.createUssdDialog(phoneGlobals, context, charSequence.toString(), phone, 2003);
                        }

                        @Override // android.telephony.TelephonyManager.UssdResponseCallback
                        public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String str, int i) {
                            Log.d("SuppServicesUiUtil", "handleCallerIdUssdResponse: failureCode =" + i);
                            AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                            PhoneGlobals phoneGlobals2 = phoneGlobals;
                            Context context2 = context;
                            PhoneUtils.createUssdDialog(phoneGlobals2, context2, context2.getText(R.string.response_error), phone, 2003);
                        }
                    }, null);
                }
            }).start();
            return "";
        }
        return context.getText(android.R.string.indeterminate_progress_43);
    }
}
