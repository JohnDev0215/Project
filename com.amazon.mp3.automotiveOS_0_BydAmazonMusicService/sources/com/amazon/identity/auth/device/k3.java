package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.identity.auth.device.api.MultipleAccountManager;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class k3 extends q3 {
    public static k3 j;
    public final Context f;
    public final boolean g;
    public final MultipleAccountManager h;
    public o3 i;

    public k3(Context context, boolean z) {
        super(context);
        this.g = z;
        this.f = context;
        this.h = new MultipleAccountManager(context);
    }

    public static synchronized k3 a(Context context, boolean z) {
        if (j == null || xa.a()) {
            Boolean boolValueOf = Boolean.valueOf(z);
            j = new k3(context.getApplicationContext(), boolValueOf != null ? boolValueOf.booleanValue() : qe.a(context, o3.d));
        }
        return j;
    }

    public o5 b() {
        return new d4(this.f, this.h);
    }

    @Override // com.amazon.identity.auth.device.q3, com.amazon.identity.auth.device.s3
    public p3 a(String str) throws DeviceDataStoreException {
        o3 o3Var;
        fa faVarA = fa.a(str);
        if (this.g && ("Default COR".equals(faVarA.c) || "Default PFM".equals(faVarA.c))) {
            synchronized (this) {
                if (this.i == null) {
                    this.i = new o3(t5.a(this.f));
                }
                o3Var = this.i;
            }
            return o3Var.a(str);
        }
        return super.a(str);
    }
}
