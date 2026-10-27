package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q3 implements s3 {
    public static final String d = "com.amazon.identity.auth.device.q3";
    public static q3 e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f507a;
    public final q1 b;
    public final u7 c;

    public q3(Context context) {
        t5 t5VarA = t5.a(context);
        this.f507a = t5VarA;
        this.b = new q1(t5VarA);
        this.c = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
    }

    public static synchronized q3 a(Context context) {
        if (e == null || xa.a()) {
            e = new q3(context.getApplicationContext());
        }
        return e;
    }

    public p3 a() throws DeviceDataStoreException {
        try {
            return new p3(t3.b(this.f507a).f562a.d(), true);
        } catch (UnsupportedOperationException unused) {
            throw new DeviceDataStoreException("Fetching DSN on this device is not supported while unregistered.");
        }
    }

    @Override // com.amazon.identity.auth.device.s3
    public p3 a(String str) throws DeviceDataStoreException {
        fa faVarA = fa.a(str);
        if (faVarA.c.equals(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER)) {
            return a();
        }
        if (faVarA.c.equals("DeviceType")) {
            String strC = z9.c(this.f507a, faVarA.b);
            if (!TextUtils.isEmpty(strC)) {
                return new p3(strC, true);
            }
            throw new DeviceDataStoreException("Value of device type is null.  This is expected on Grover V1 for the central device Type when the device is not registered.");
        }
        if (faVarA.c.equals("Default COR")) {
            return new p3(this.b.a(), false);
        }
        if (faVarA.c.equals("Default PFM")) {
            return new p3(this.b.b(), false);
        }
        if (faVarA.c.equals("Client Id")) {
            return new p3(OpenIdRequest.a(a().f488a, oa.a(this.f507a, DeviceAttribute.CentralDeviceType)), true);
        }
        if (qe.j(this.f507a)) {
            String strB = this.c.b("device.metadata", str);
            if (strB == null) {
                Log.i(ga.a(d), "device attribute " + str + " not found in datastore");
                return null;
            }
            return new p3(strB, true);
        }
        ga.a(d);
        return null;
    }
}
