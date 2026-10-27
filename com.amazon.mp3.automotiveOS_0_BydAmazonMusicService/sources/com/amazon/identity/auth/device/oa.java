package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class oa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f479a = "com.amazon.identity.auth.device.oa";

    public static String a(Context context, DeviceAttribute deviceAttribute) {
        Object objA = h2.c.a(context, deviceAttribute);
        if (objA instanceof String) {
            return (String) objA;
        }
        ga.b(f479a, "Device Attribute %s is not of type String.", deviceAttribute.toString());
        throw new RuntimeException("Incorrectly called getDeviceAttribute in Platform.  Try calling hasDeviceAttribute.");
    }
}
