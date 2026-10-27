package com.amazon.identity.auth.device.token;

import android.text.TextUtils;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.v9;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPCookie implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f590a = MAPCookie.class.getName();
    private static final long serialVersionUID = 551200964665L;
    private final Map<String, String> mCookieData;
    private int[] mPorts;

    public MAPCookie(String str, String str2, String str3, String str4, boolean z) {
        HashMap map = new HashMap();
        this.mCookieData = map;
        map.put("Name", str);
        map.put("Value", str2);
        map.put("DirectedId", str4);
        map.put("Domain", str3);
        b(z);
        f();
    }

    public void a(boolean z) {
        this.mCookieData.put("HttpOnly", Boolean.toString(z));
    }

    public void b(boolean z) {
        this.mCookieData.put("Secure", Boolean.toString(z));
    }

    public String c() {
        return this.mCookieData.get("Name");
    }

    public String d() {
        StringBuilder sb = new StringBuilder(c().trim());
        sb.append("=");
        sb.append(e().trim());
        sb.append("; path=");
        String str = this.mCookieData.get("Path");
        if (TextUtils.isEmpty(str)) {
            sb.append("/");
        } else {
            sb.append(str);
        }
        sb.append("; domain=" + a().trim());
        if (Boolean.parseBoolean(this.mCookieData.get("Secure"))) {
            sb.append("; secure");
        }
        String str2 = this.mCookieData.get("HttpOnly");
        if (TextUtils.isEmpty(str2) ? false : Boolean.parseBoolean(str2)) {
            sb.append("; httponly");
        }
        Date dateB = b();
        if (dateB != null) {
            sb.append("; expires=");
            if (dateB.before(Calendar.getInstance().getTime())) {
                ga.a(f590a, "Cookie %s expired : ", c(), dateB.toGMTString());
            }
            sb.append(v9.a().format(dateB));
        }
        return sb.toString();
    }

    public String e() {
        return this.mCookieData.get("Value");
    }

    public final void f() {
        ga.a(f590a, "Creating Cookie: %s , domain: %s , for id: %s from cookie data %s", c(), a(), this.mCookieData.get("DirectedId"), e());
    }

    public String a() {
        return this.mCookieData.get("Domain");
    }

    public void c(String str) {
        this.mCookieData.put("Path", str);
    }

    public void a(String str) {
        this.mCookieData.put("Domain", str);
    }

    public Date b() {
        String str = this.mCookieData.get("Expires");
        if (str != null) {
            try {
                return v9.a().parse(str);
            } catch (ParseException unused) {
                ga.a(f590a);
            }
        }
        return null;
    }

    public void b(String str) {
        this.mCookieData.put("Expires", str);
    }

    public MAPCookie(String str, String str2, String str3, String str4, String str5, String str6, boolean z, boolean z2) {
        HashMap map = new HashMap();
        this.mCookieData = map;
        map.put("Name", str);
        map.put("Value", str2);
        map.put("Domain", str3);
        map.put("Expires", str4);
        map.put("Path", str5);
        map.put("DirectedId", str6);
        b(z);
        a(z2);
        f();
    }
}
