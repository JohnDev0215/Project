package com.amazon.identity.frc;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.frc.FrcCookiesManager.a;
import com.amazon.identity.frc.FrcCookiesManager.b;
import com.amazon.identity.frc.FrcCookiesManager.c;
import com.amazon.identity.frc.FrcCookiesManager.d;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class FrcCookiesManager {
    private static final String TAG;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Set<String> f709a;
    private Context mContext;

    static {
        HashSet hashSet = new HashSet();
        f709a = hashSet;
        TAG = FrcCookiesManager.class.getName();
        hashSet.add(DataRecordKey.APP_NAME);
        hashSet.add(DataRecordKey.APP_VERSION);
        hashSet.add(DataRecordKey.MODEL);
        hashSet.add("DeviceOSVersion");
        hashSet.add(DataRecordKey.DEVICE_WIDTH);
        hashSet.add(DataRecordKey.DEVICE_HEIGHT);
        hashSet.add(DataRecordKey.DEVICE_LANGUAGE);
        hashSet.add("TimeZone");
    }

    public FrcCookiesManager(Context context) {
        this.mContext = context;
    }

    public String getFrcCookies(String str) {
        String str2 = TAG;
        Log.i(str2, "Start generating frc cookies");
        JSONObject jSONObjectA = a();
        if (jSONObjectA == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return jSONObjectA.toString();
        }
        String strA = a.a(jSONObjectA.toString(), str);
        Log.i(str2, "Finish generating frc cookies.");
        return strA;
    }

    String b() {
        return this.mContext.getPackageName();
    }

    String c() {
        Integer numA = b.a(this.mContext, this.mContext.getPackageName());
        if (numA != null) {
            return Integer.toString(numA.intValue());
        }
        return null;
    }

    String d() {
        return Build.FINGERPRINT;
    }

    String getDeviceName() {
        return String.format("%s/%s/%s", Build.HARDWARE, Build.MANUFACTURER, Build.MODEL);
    }

    String a(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver == null) {
                Log.i(TAG, "Resolver is null, the 3P id will be null");
                return null;
            }
            return Settings.Secure.getString(contentResolver, "android_id");
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown error happens when collecting android id: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String b(Context context) {
        try {
            return d.f(context);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get screen width %s", e.getClass().getName()), e);
            return null;
        }
    }

    String c(Context context) {
        try {
            return d.g(context);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get screen height: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String e() {
        return Locale.getDefault().toString().replace(Attributes.PREDEFINED_ATTRIBUTE_PREFIX, "-");
    }

    String getTimeZone() {
        try {
            String str = new SimpleDateFormat("Z", Locale.getDefault()).format(Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.getDefault()).getTime());
            return str.substring(0, 3) + ":" + str.substring(3, 5);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get time zone: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String d(Context context) {
        try {
            return c.d(context);
        } catch (Exception e) {
            Log.e(TAG, String.format("Unknown exception happened when try to get Carrier: %s", e.getClass().getName()), e);
            return null;
        }
    }

    String f() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                Log.e(TAG, "Network interfaces is null, this should be a bug!");
                return null;
            }
            Iterator it = Collections.list(networkInterfaces).iterator();
            String upperCase = null;
            while (it.hasNext()) {
                for (InetAddress inetAddress : Collections.list(((NetworkInterface) it.next()).getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress();
                    }
                    if (inetAddress.isLoopbackAddress()) {
                        upperCase = inetAddress.getHostAddress().toUpperCase(Locale.US);
                    }
                }
            }
            return upperCase;
        } catch (Exception e) {
            Log.e(TAG, String.format("Exception happened when tring to get ip address: %s", e.getClass().getName()), e);
            return null;
        }
    }

    JSONObject a() {
        JSONObject jSONObject = new JSONObject() { // from class: com.amazon.identity.frc.FrcCookiesManager.1
            @Override // org.json.JSONObject
            public JSONObject put(String str, Object obj) throws JSONException {
                if ((obj == null || TextUtils.isEmpty(obj.toString().trim())) && FrcCookiesManager.f709a.contains(str)) {
                    Log.e(FrcCookiesManager.TAG, "MissingMandatoryFrcCookie:" + str);
                }
                return ((obj instanceof String) && TextUtils.isEmpty((String) obj)) ? this : super.put(str, obj);
            }
        };
        try {
            jSONObject.put(DataRecordKey.APP_NAME, b());
            jSONObject.put(DataRecordKey.APP_VERSION, c());
            jSONObject.put("DeviceOSVersion", d());
            jSONObject.put(DataRecordKey.MODEL, getDeviceName());
            jSONObject.put(DataRecordKey.THIRD_PARTY_DEVICE_AGENT, a(this.mContext));
            jSONObject.put(DataRecordKey.DEVICE_WIDTH, b(this.mContext));
            jSONObject.put(DataRecordKey.DEVICE_HEIGHT, c(this.mContext));
            jSONObject.put(DataRecordKey.DEVICE_LANGUAGE, e());
            jSONObject.put("TimeZone", getTimeZone());
            jSONObject.put(DataRecordKey.NETWORK_OPERATOR, d(this.mContext));
            jSONObject.put("IpAddress", f());
            jSONObject.toString();
            return jSONObject;
        } catch (Exception e) {
            Log.e(TAG, "FRC Cookies Generation Failed", e);
            return null;
        }
    }
}
