package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u3 implements s3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s3 f602a;
    public p3 b;
    public p3 c;

    public u3(s3 s3Var, Context context) {
        this.b = null;
        this.c = null;
        this.f602a = s3Var;
        String strD = z9.d(context);
        if (strD != null) {
            this.b = new p3(strD, true);
        }
        String strA = ra.a(context);
        if (strA != null) {
            this.c = new p3(strA, true);
        }
    }

    @Override // com.amazon.identity.auth.device.s3
    public p3 a(String str) throws DeviceDataStoreException {
        return (this.b == null || !"DeviceType".equals(str)) ? (this.c == null || !DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER.equals(str)) ? this.f602a.a(str) : this.c : this.b;
    }
}
