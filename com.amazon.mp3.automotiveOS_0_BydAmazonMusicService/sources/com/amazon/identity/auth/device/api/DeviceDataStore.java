package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.p3;
import com.amazon.identity.auth.device.r3;
import com.amazon.identity.auth.device.s3;
import com.amazon.identity.auth.device.t3;
import com.amazon.identity.auth.device.y5;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class DeviceDataStore {
    public static DeviceDataStore c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r3 f204a;
    public final s3 b;

    public DeviceDataStore(Context context) {
        MAPInit.getInstance(context).initialize();
        this.f204a = r3.a();
        this.b = t3.a(context);
    }

    @FireOsSdk
    public static void generateNewInstance(Context context) {
        c = new DeviceDataStore(context.getApplicationContext());
    }

    @FireOsSdk
    public static synchronized DeviceDataStore getInstance(Context context) {
        if (c == null) {
            generateNewInstance(context);
        }
        return c;
    }

    public void clearCache() {
        this.f204a.b();
    }

    @FireOsSdk
    public String getValue(String str) throws DeviceDataStoreException {
        if (str == null) {
            String str2 = String.format("Key passed in is null", new Object[0]);
            Log.w(ga.a("com.amazon.identity.auth.device.api.DeviceDataStore"), str2);
            throw new DeviceDataStoreException(str2);
        }
        if (this.f204a.f523a.containsKey(str)) {
            return this.f204a.f523a.get(str);
        }
        y5 y5Var = new y5("DeviceDataStore:getValue");
        try {
            p3 p3VarA = this.b.a(str);
            if (p3VarA == null) {
                String str3 = String.format("Key %s was not found in the device data store", str);
                Log.w(ga.a("com.amazon.identity.auth.device.api.DeviceDataStore"), str3);
                throw new DeviceDataStoreException(str3);
            }
            String str4 = p3VarA.f488a;
            if (str4 == null) {
                y5Var.b(str + ":Null");
                ga.c("com.amazon.identity.auth.device.api.DeviceDataStore", "Getting null value for key %s ", str);
            } else if (p3VarA.b) {
                this.f204a.f523a.put(str, str4);
            }
            return str4;
        } finally {
            y5Var.e.a();
        }
    }
}
