package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.me;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class xd implements Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ de f664a;
    public final /* synthetic */ y5 b;
    public final /* synthetic */ boolean c;
    public final /* synthetic */ Callback d;
    public final /* synthetic */ r8 e;
    public final /* synthetic */ t5 f;
    public final /* synthetic */ String g;
    public final /* synthetic */ MAPAccountManager.RegistrationError h;

    public xd(de deVar, y5 y5Var, boolean z, Callback callback, r8 r8Var, t5 t5Var, String str, MAPAccountManager.RegistrationError registrationError) {
        this.f664a = deVar;
        this.b = y5Var;
        this.c = z;
        this.d = callback;
        this.e = r8Var;
        this.f = t5Var;
        this.g = str;
        this.h = registrationError;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        if (this.e != null) {
            ga.a(yd.c);
            this.e.onFinish(new Bundle());
        }
        de deVar = this.f664a;
        double dC = deVar != null ? deVar.c() : -1.0d;
        if (this.b != null) {
            String string = bundle.getString(MAPError.KEY_ERROR_TYPE);
            if (string != null) {
                y5 y5Var = this.b;
                String str = "MAPError:" + string;
                String[] strArr = new String[1];
                t5 t5Var = this.f;
                strArr[0] = t5Var == null ? null : ((o5) t5Var.getSystemService("dcp_device_info")).f();
                y5Var.a(str, strArr);
            }
            String name = MAPAccountManager.RegistrationError.fromValue(bundle.getInt(this.g, this.h.value()), this.h).getName();
            y5 y5Var2 = this.b;
            String str2 = "Error:" + name;
            String[] strArr2 = new String[1];
            t5 t5Var2 = this.f;
            strArr2[0] = t5Var2 != null ? ((o5) t5Var2.getSystemService("dcp_device_info")).f() : null;
            y5Var2.a(str2, strArr2);
            if (this.b.c) {
                me.a aVar = (me.a) yd.a();
                aVar.f438a = this.b.f667a;
                aVar.g = Boolean.FALSE;
                aVar.c = string;
                if (dC != -1.0d) {
                    aVar.f = Double.valueOf(dC);
                }
                aVar.a().a();
            }
            this.b.e.a();
        }
        Callback callback = this.d;
        if (callback != null) {
            callback.onError(bundle);
        }
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        de deVar = this.f664a;
        double dC = deVar != null ? deVar.c() : -1.0d;
        y5 y5Var = this.b;
        if (y5Var != null) {
            if (this.c) {
                y5Var.b("Success");
            }
            if (this.b.c) {
                me.a aVar = (me.a) yd.a();
                aVar.f438a = this.b.f667a;
                aVar.g = Boolean.TRUE;
                if (dC != -1.0d) {
                    aVar.f = Double.valueOf(dC);
                }
                aVar.a().a();
            }
            this.b.e.a();
        }
        Callback callback = this.d;
        if (callback != null) {
            callback.onSuccess(bundle);
        }
    }
}
