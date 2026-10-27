package com.amazon.identity.auth.device;

import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class id {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f387a = "com.amazon.identity.auth.device.id";

    public static boolean a(String str) {
        return str != null && str.matches("[a-zA-Z0-9]*");
    }

    public static boolean b(String str) {
        return str == null || str.equals("");
    }

    public static boolean c(String str) {
        if (b(str)) {
            Log.i(ga.a(f387a), "isValidDeviceSerialNumber: returning false because a null or empty device serial number was given.");
            return false;
        }
        if (!a(str)) {
            Log.i(ga.a(f387a), "isValidDeviceSerialNumber: returning false because a non alpha numeric serial number was given.");
            return false;
        }
        if (str.length() <= 51) {
            return true;
        }
        Log.w(ga.a(f387a), "isValidDeviceSerialNumber: returning false because a serial number that is too long (more than 51 characters) was given.");
        return false;
    }

    public static boolean d(String str) {
        if (b(str)) {
            Log.i(ga.a(f387a), "isValidDeviceType: returning false because a null or empty device type was given.");
            return false;
        }
        if (a(str)) {
            return true;
        }
        Log.i(ga.a(f387a), "isValidDeviceType: returning false because a non alpha numeric device type was given.");
        return false;
    }
}
