package com.amazon.identity.auth.device;

import android.util.Log;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class pc {
    public static final String h = "com.amazon.identity.auth.device.pc";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f496a;
    public String b;
    public String c;
    public String d;
    public ld e;
    public String f;
    public cc g;

    public final boolean a(String str) {
        if (!id.c(str)) {
            Log.e(ga.a(h), "setDeviceSerialNumber: device serial number was invalid. Cannot be set.");
            return false;
        }
        this.b = str;
        String strSubstring = null;
        if (str != null) {
            try {
                strSubstring = e.f(str).substring(23, 31);
            } catch (NoSuchAlgorithmException e) {
                Log.e(ga.a(dc.f283a), "SHA-256 algorithm does not exist.  PANICK!", e);
            }
        }
        this.c = strSubstring;
        return true;
    }

    public final boolean b(String str) {
        if (id.d(str)) {
            this.f496a = str;
            return true;
        }
        Log.e(ga.a(h), "setDeviceType: deviceType was invalid. Cannot be set.");
        return false;
    }

    public abstract ld c();

    public final boolean c(String str) {
        if (id.b(str)) {
            ga.a(h);
            return false;
        }
        this.d = str;
        return true;
    }

    public String b() {
        if (this.g == null) {
            return null;
        }
        try {
            return this.g.a("drvV1", gb.a(gb.a(this.f496a, this.b, this.d), a(), this.f));
        } catch (Exception e) {
            Log.e(ga.a(h), "Failed to sign JWT", e);
            return null;
        }
    }

    public void a(y5 y5Var) {
        cc.a();
        Log.i(ga.a(h), "TrustZone signer is not available on this device.");
    }

    public JSONObject a() throws JSONException {
        return gb.a();
    }
}
