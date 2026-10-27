package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import com.amazon.identity.frc.FrcCookiesManager;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class a7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f173a = "com.amazon.identity.auth.device.a7";

    public static JSONObject a(String str, String str2, y5 y5Var) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            if (y5Var != null) {
                y5Var.b("DeviceMetadata:RequiredParameterNull:DeviceTypeAndDSN");
            }
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            if (y5Var != null) {
                y5Var.b("DeviceMetadata:RequiredParameterNull:DeviceType");
            }
            return null;
        }
        if (TextUtils.isEmpty(str2)) {
            if (y5Var != null) {
                y5Var.b("DeviceMetadata:RequiredParameterNull:DSN");
            }
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device_os_family", "android");
            jSONObject.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, str);
            jSONObject.put("device_serial", str2);
            jSONObject.putOpt("manufacturer", Build.MANUFACTURER);
            jSONObject.putOpt("model", Build.MODEL);
            jSONObject.putOpt("os_version", Integer.toString(Build.VERSION.SDK_INT));
            jSONObject.putOpt("product", Build.PRODUCT);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(ga.a(f173a), "JSONException happened when trying to build device metadata", e);
            return null;
        } catch (Exception e2) {
            Log.e(ga.a(f173a), "An unexpected error occurred while building the device metadata JSONObject");
            yd.a("MetadataCollection:UnexpectedException", "ExceptionType:" + e2.getClass().getName());
            return null;
        }
    }

    @SuppressLint({"ServiceCast"})
    public static String b(t5 t5Var, y5 y5Var) {
        try {
            String networkCountryIso = ((x5) t5Var.getSystemService("sso_telephony_service")).f657a.getNetworkCountryIso();
            if (TextUtils.isEmpty(networkCountryIso)) {
                return null;
            }
            return networkCountryIso;
        } catch (Exception e) {
            ga.a(f173a, y5Var, "Unknown exception happened why try to read country ISO", "SimCountryISOCollection:Exception:" + e.getClass().getName(), e);
            return null;
        }
    }

    public static void a(String str, JSONObject jSONObject) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("frc", str);
        jSONObject.put("user_context_map", jSONObject2);
    }

    public static String a(Context context, String str) {
        return new FrcCookiesManager(context).getFrcCookies(str);
    }

    public static String a(t5 t5Var, String str, y5 y5Var, boolean z) {
        String strEncodeToString = null;
        UserDictionaryHelper userDictionaryHelperA = z ? null : UserDictionaryHelper.a(t5Var);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("device_data", null);
            String strB = s9.b();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.putOpt("software_version", strB);
            } catch (JSONException unused) {
                Log.e(ga.a(f173a), "JSONException when building device registration JSON for map-md cookie");
                jSONObject2 = null;
            }
            jSONObject.putOpt("device_registration_data", jSONObject2);
            jSONObject.putOpt("app_identifier", a(t5Var, str));
            jSONObject.putOpt("app_info", a(t5Var, y5Var));
            if (userDictionaryHelperA != null) {
                List<String> listA = userDictionaryHelperA.a();
                JSONArray jSONArray = e.a(listA) ? null : new JSONArray((Collection<?>) listA);
                if (jSONArray != null && jSONArray.length() > 0) {
                    jSONObject.putOpt("device_user_dictionary", jSONArray);
                }
            }
        } catch (JSONException unused2) {
            Log.e(ga.a(f173a), "JSONException when adding data to map-md cookie");
        }
        String string = jSONObject.toString();
        ga.a(f173a);
        if (!TextUtils.isEmpty(string)) {
            try {
                strEncodeToString = Base64.encodeToString(string.getBytes(ParserUtil.UTF_8), 2);
            } catch (UnsupportedEncodingException e) {
                Log.e(ga.a(f173a), "This platform doesn't support UTF-8, this should never happen.", e);
            }
        }
        ga.a(f173a);
        return strEncodeToString;
    }

    public static JSONObject a(t5 t5Var, String str) {
        String str2;
        String string;
        String strA;
        JSONArray jSONArray;
        String str3 = s9.a().d;
        try {
            PackageInfo packageInfoA = z5.a(str, 64, t5Var.getPackageManager());
            if (packageInfoA != null) {
                str2 = packageInfoA.versionName;
                try {
                    string = Integer.toString(packageInfoA.versionCode);
                    try {
                        Signature[] signatureArr = packageInfoA.signatures;
                        jSONArray = new JSONArray();
                        for (Signature signature : signatureArr) {
                            try {
                                jSONArray.put(e.a("SHA-256", signature));
                            } catch (PackageManager.NameNotFoundException unused) {
                                strA = null;
                            } catch (SecurityException unused2) {
                                strA = null;
                            } catch (GeneralSecurityException unused3) {
                                strA = null;
                            }
                        }
                        strA = e.a(packageInfoA);
                        try {
                            ga.a(f173a);
                        } catch (PackageManager.NameNotFoundException unused4) {
                            Log.e(ga.a(f173a), "NameNotFoundException when building app identifier JSON for map-md cookie");
                        } catch (SecurityException unused5) {
                            Log.e(ga.a(f173a), "SecurityException when building app identifier JSON for map-md cookie");
                        } catch (GeneralSecurityException unused6) {
                            Log.e(ga.a(f173a), "GeneralSecurityException when building app identifier JSON for map-md cookie");
                        }
                    } catch (PackageManager.NameNotFoundException unused7) {
                        strA = null;
                        jSONArray = null;
                        Log.e(ga.a(f173a), "NameNotFoundException when building app identifier JSON for map-md cookie");
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.putOpt("package", str);
                        jSONObject.putOpt("SHA-256", jSONArray);
                        jSONObject.putOpt("app_version", string);
                        jSONObject.putOpt("app_version_name", str2);
                        jSONObject.putOpt("app_sms_hash", strA);
                        jSONObject.putOpt("map_version", str3);
                        return jSONObject;
                    } catch (SecurityException unused8) {
                        strA = null;
                        jSONArray = null;
                        Log.e(ga.a(f173a), "SecurityException when building app identifier JSON for map-md cookie");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.putOpt("package", str);
                        jSONObject2.putOpt("SHA-256", jSONArray);
                        jSONObject2.putOpt("app_version", string);
                        jSONObject2.putOpt("app_version_name", str2);
                        jSONObject2.putOpt("app_sms_hash", strA);
                        jSONObject2.putOpt("map_version", str3);
                        return jSONObject2;
                    } catch (GeneralSecurityException unused9) {
                        strA = null;
                        jSONArray = null;
                        Log.e(ga.a(f173a), "GeneralSecurityException when building app identifier JSON for map-md cookie");
                        JSONObject jSONObject22 = new JSONObject();
                        jSONObject22.putOpt("package", str);
                        jSONObject22.putOpt("SHA-256", jSONArray);
                        jSONObject22.putOpt("app_version", string);
                        jSONObject22.putOpt("app_version_name", str2);
                        jSONObject22.putOpt("app_sms_hash", strA);
                        jSONObject22.putOpt("map_version", str3);
                        return jSONObject22;
                    }
                } catch (PackageManager.NameNotFoundException unused10) {
                    string = null;
                } catch (SecurityException unused11) {
                    string = null;
                } catch (GeneralSecurityException unused12) {
                    string = null;
                }
            } else {
                strA = null;
                str2 = null;
                string = null;
                jSONArray = null;
            }
        } catch (PackageManager.NameNotFoundException unused13) {
            str2 = null;
            string = null;
        } catch (SecurityException unused14) {
            str2 = null;
            string = null;
        } catch (GeneralSecurityException unused15) {
            str2 = null;
            string = null;
        }
        JSONObject jSONObject222 = new JSONObject();
        try {
            jSONObject222.putOpt("package", str);
            jSONObject222.putOpt("SHA-256", jSONArray);
            jSONObject222.putOpt("app_version", string);
            jSONObject222.putOpt("app_version_name", str2);
            jSONObject222.putOpt("app_sms_hash", strA);
            jSONObject222.putOpt("map_version", str3);
            return jSONObject222;
        } catch (JSONException unused16) {
            Log.e(ga.a(f173a), "JSONException when building app identifier JSON for map-md cookie");
            return null;
        }
    }

    public static JSONObject a(t5 t5Var, y5 y5Var) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("auto_pv", 0);
            if (MAPRuntimePermissionHandler.b(t5Var)) {
                jSONObject.putOpt("auto_pv_with_smsretriever", 1);
            } else {
                jSONObject.putOpt("auto_pv_with_smsretriever", 0);
            }
            if (MAPRuntimePermissionHandler.a(t5Var)) {
                jSONObject.putOpt("smartlock_supported", 1);
            } else {
                jSONObject.putOpt("smartlock_supported", 0);
            }
            if (MAPRuntimePermissionHandler.a(t5Var, y5Var)) {
                jSONObject.put("permission_runtime_grant", 2);
            } else {
                jSONObject.put("permission_runtime_grant", 0);
            }
            return jSONObject;
        } catch (JSONException unused) {
            Log.e(ga.a(f173a), "JSONException when building app info JSON for map-md cookie");
            return null;
        }
    }
}
