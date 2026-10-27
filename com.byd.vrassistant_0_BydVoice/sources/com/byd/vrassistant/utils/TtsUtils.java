package com.byd.vrassistant.utils;

import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.core.JsonCompliant;
import com.byd.vrassistant.Constant;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class TtsUtils {
    public static <T extends BaseResult> void tts(String str, String str2, IResponseSender<T> iResponseSender, String str3) {
        tts(str, str2, iResponseSender, str3, null);
    }

    public static <T extends BaseResult> void tts(String str, String str2, IResponseSender<T> iResponseSender, String str3, T t) {
        Log.i(Constant.TAG_FORTEST, "request domain【" + getDomian(str) + "】,api【" + str2 + "】,result【" + str3 + "】");
        if (t == null) {
            Log.i(str, str2 + ": " + str3);
            iResponseSender.send(new Response(str3));
        } else {
            try {
                Log.i(str, str2 + ": " + str3 + " result: " + t.toJsonString());
            } catch (JSONException e) {
                Log.i(str, "JSONException : ", e);
            }
            iResponseSender.send(new Response(str3, t));
        }
    }

    public static void requestLog(String str, String str2) throws JSONException {
        requestLog(str, str2, null);
    }

    public static void requestLog(String str, String str2, JsonCompliant jsonCompliant) throws JSONException {
        String domian = getDomian(str);
        if (jsonCompliant == null) {
            Log.i(str, str2 + ": json = ");
        } else {
            Log.i(str, str2 + ": json = " + jsonCompliant.toJsonString());
        }
        Log.i(Constant.TAG_FORTEST, "request domain【" + domian + "】,api【" + str2 + "】");
    }

    private static String getDomian(String str) {
        return str.startsWith("NEU_Vehicle") ? "CustomVehicleControlDomain" : str.startsWith("NEU_Hvac") ? "CustomHvacDomain" : "CustomPhoneDomain";
    }
}
