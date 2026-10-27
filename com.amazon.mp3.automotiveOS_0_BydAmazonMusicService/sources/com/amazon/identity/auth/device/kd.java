package com.amazon.identity.auth.device;

import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class kd {
    public static final String b = "com.amazon.identity.auth.device.kd";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f410a;

    public kd(String str) {
        if (a(str)) {
            this.f410a = str;
        } else {
            ga.b(b, "SoftwareVersion: constructed with invalid software version. Was not set.");
            this.f410a = null;
        }
    }

    public static boolean a(String str) {
        if (str == null) {
            Log.w(ga.a(b), "SoftwareVersion: isValidVersion: returning false because a null version was given.");
            return false;
        }
        if (str.length() < 1 || str.length() > 37) {
            Log.w(ga.a(b), "SoftwareVersion: isValidVersion: returning false because an invalid length was given (must be between 1 and 37 characters).");
            return false;
        }
        if (str.matches("[0-9]*")) {
            return true;
        }
        Log.w(ga.a(b), "SoftwareVersion: isValidVersion: returning false because version contains invalid characters (can only contain digits)");
        return false;
    }

    public boolean a() {
        if (this.f410a != null) {
            return true;
        }
        Log.w(ga.a(b), "SoftwareVersion: isValid: returning false because a valid software version has not been set.");
        return false;
    }
}
