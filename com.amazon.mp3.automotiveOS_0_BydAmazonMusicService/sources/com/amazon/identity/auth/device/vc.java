package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazonaws.http.HttpHeader;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class vc {
    public static final String g = "com.amazon.identity.auth.device.vc";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f639a = a();
    public String b;
    public String c;
    public kd d;
    public ld e;
    public Map<String, jd> f;

    public static String a() {
        return "https://" + EnvironmentUtils.c.g() + "/FirsProxy/getDeviceCredentials";
    }

    public boolean b(String str) {
        if (ld.a(str)) {
            this.f639a = str;
            return true;
        }
        Log.e(ga.a(g), "setURL: url is invalid. Cannot be set. Invalidating default URL to prevent it from being used.");
        this.f639a = null;
        return false;
    }

    public boolean a(String str) {
        boolean z;
        if (id.b(str)) {
            Log.i(ga.a(g), "isValidReason: returning false because a null or empty reason was given.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            Log.e(ga.a(g), "setReason: reason was invalid. Cannot be set.");
            return false;
        }
        this.b = str;
        return true;
    }

    public ld b() {
        boolean z;
        if (id.b(this.f639a)) {
            Log.w(ga.a(g), "isValid: returning false because a valid url has not been set.");
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            Log.e(ga.a(g), "getWebRequest: Cannot construct a WebRequest because the UpdateDeviceCredentialsRequest is invalid. (See previous warnings from UpdateDeviceCredentialsRequest::isValid for details.)");
            return null;
        }
        ld ldVar = this.e;
        if (ldVar != null) {
            return ldVar;
        }
        ld ldVar2 = new ld();
        this.e = ldVar2;
        if (!ldVar2.d(this.f639a)) {
            Log.e(ga.a(ld.m), "setUrl: url was malformed. Cannot be set.");
        }
        ld ldVar3 = this.e;
        ldVar3.h = HttpVerb.HttpVerbGet;
        String str = this.b;
        if (str != null) {
            ldVar3.a("reason", str);
        }
        kd kdVar = this.d;
        if (kdVar != null) {
            this.e.a("softwareVersion", kdVar.f410a);
        }
        String str2 = this.c;
        if (str2 != null) {
            this.e.a("softwareComponentId", str2);
        }
        this.e.b(HttpHeader.CONTENT_TYPE, "text/xml");
        Map<String, jd> map = this.f;
        if (map != null && map.size() > 0) {
            qd qdVar = new qd("request", null, new rd[0]);
            qdVar.c.add(new pd(this.f));
            this.e.b(qdVar.a());
            this.e.h = HttpVerb.HttpVerbPost;
        }
        this.e.j = true;
        ga.c(g, "getWebRequest: constructed a web request with:\nReason: %s", this.b);
        return this.e;
    }
}
