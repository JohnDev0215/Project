package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import java.util.EnumMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class h2 {
    public static final String b = "com.amazon.identity.auth.device.h2";
    public static final h2 c = new h2();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final EnumMap<DeviceAttribute, Object> f358a = new EnumMap<>(DeviceAttribute.class);

    public synchronized Object a(Context context, DeviceAttribute deviceAttribute) {
        Object objFetchValue;
        Object obj = this.f358a.get(deviceAttribute);
        if (obj != null) {
            return obj;
        }
        synchronized (this) {
            objFetchValue = deviceAttribute.fetchValue(context);
            if (!(objFetchValue instanceof String) || !TextUtils.isEmpty((String) objFetchValue)) {
                Log.i(ga.a(b), String.format("Setting device attribute %s to %s", deviceAttribute.toString(), objFetchValue));
                this.f358a.put(deviceAttribute, objFetchValue);
            }
        }
        return objFetchValue;
    }
}
