package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class v9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f636a = "com.amazon.identity.auth.device.v9";

    public static List<MAPCookie> a(@NonNull Bundle bundle, String str) {
        ArrayList arrayList = new ArrayList();
        if (bundle.containsKey(CookieKeys.KEY_COOKIES)) {
            try {
                String[] stringArray = bundle.getStringArray(CookieKeys.KEY_COOKIES);
                if (stringArray == null) {
                    return arrayList;
                }
                for (String str2 : stringArray) {
                    arrayList.add(c(str2, str));
                }
            } catch (Exception e) {
                Log.w(ga.a(f636a), "Failed to deserialize parcel", e);
            }
        }
        return arrayList;
    }

    @Deprecated
    public static List<MAPCookie> b(String str, String str2) {
        String[] stringArray;
        ArrayList arrayList = new ArrayList();
        Bundle bundleB = ma.b(str);
        if (bundleB == null) {
            return arrayList;
        }
        try {
            stringArray = bundleB.getStringArray(CookieKeys.KEY_COOKIES);
        } catch (Exception e) {
            Log.w(ga.a(f636a), "Failed to deserialize parcel", e);
            stringArray = null;
        }
        if (stringArray == null) {
            return arrayList;
        }
        for (String str3 : stringArray) {
            arrayList.add(c(str3, str2));
        }
        return arrayList;
    }

    public static MAPCookie c(String str, String str2) {
        String strSubstring;
        StringTokenizer stringTokenizer = new StringTokenizer(str.trim(), ";");
        boolean z = false;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        while (stringTokenizer.hasMoreTokens()) {
            String strTrim = stringTokenizer.nextToken().trim();
            int iIndexOf = strTrim.indexOf("=");
            if (iIndexOf == -1) {
                strSubstring = null;
            } else {
                String strSubstring2 = strTrim.substring(0, iIndexOf);
                strSubstring = strTrim.substring(iIndexOf + 1);
                strTrim = strSubstring2;
            }
            if (strTrim.equalsIgnoreCase("domain")) {
                str5 = strSubstring;
            } else if (strTrim.equalsIgnoreCase("Expires")) {
                str6 = strSubstring;
            } else if (strTrim.equalsIgnoreCase("Path")) {
                str7 = strSubstring;
            } else if (strTrim.equalsIgnoreCase("Secure")) {
                z = true;
            } else if (strTrim.equalsIgnoreCase("HttpOnly")) {
                z2 = true;
            } else {
                str3 = strTrim;
                str4 = strSubstring;
            }
        }
        return new MAPCookie(str3, str4, str5, str6, str7, str2, z, z2);
    }

    public static String b(List<MAPCookie> list) {
        JSONArray jSONArray = new JSONArray();
        String[] strArrA = a(list);
        if (strArrA != null) {
            for (String str : strArrA) {
                jSONArray.put(str);
            }
        }
        return jSONArray.toString();
    }

    public static List<MAPCookie> a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(c(jSONArray.getString(i), str2));
            }
            return arrayList;
        } catch (JSONException e) {
            Log.w(ga.a(f636a), "Failed to parse cookies", e);
            return arrayList;
        }
    }

    public static String b() {
        Calendar calendar = Calendar.getInstance();
        Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(1, 30);
        return a().format(calendar.getTime());
    }

    public static String[] a(List<MAPCookie> list) {
        if (list == null) {
            return new String[0];
        }
        ArrayList arrayList = new ArrayList();
        Iterator<MAPCookie> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().d());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static SimpleDateFormat a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy kk:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
