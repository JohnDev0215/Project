package com.amazon.identity.auth.device;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n implements u7.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ boolean f440a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ String c;
    public final /* synthetic */ Bundle d;
    public final /* synthetic */ List e;
    public final /* synthetic */ Bundle f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ g h;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            n nVar = n.this;
            String str = nVar.c;
            x0 x0Var = nVar.h.k;
            ga.a(s1.i);
            Intent intentA = e.a(CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION);
            intentA.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, str);
            x0Var.a(str, intentA, AccountConstants.PERMISSION_MANAGE_COR_PFM);
            String string = n.this.d.getString("com.amazon.dcp.sso.property.devicename");
            n nVar2 = n.this;
            t5 t5Var = nVar2.h.f335a;
            String str2 = nVar2.c;
            Bundle bundle = new Bundle();
            bundle.putString("com.amazon.dcp.sso.property.devicename", string);
            w1.a(t5Var, str2, bundle);
            boolean zBooleanValue = Boolean.valueOf(n.this.d.getString(CustomerAttributeKeys.KEY_IS_ANONYMOUS)).booleanValue();
            n nVar3 = n.this;
            if (nVar3.h.e.e(nVar3.c) || zBooleanValue) {
                return;
            }
            String string2 = n.this.d.getString("com.amazon.dcp.sso.property.deviceemail");
            String string3 = n.this.d.getString("com.amazon.dcp.sso.token.devicedevicetype");
            n nVar4 = n.this;
            g gVar = nVar4.h;
            String str3 = nVar4.c;
            gVar.getClass();
            if (TextUtils.isEmpty(string2)) {
                Log.e(ga.a(g.r), "The central device email is missing. Please check that the capability EMAIL_ALIAS_SUPPORTED is defined for the following device type in DMS: " + string3);
                yd.a("CentralDeviceEmailIsMissing", string3);
            }
            w1.a(gVar.f335a, str3, string2);
        }
    }

    public n(g gVar, boolean z, boolean z2, String str, Bundle bundle, List list, Bundle bundle2, boolean z3) {
        this.h = gVar;
        this.f440a = z;
        this.b = z2;
        this.c = str;
        this.d = bundle;
        this.e = list;
        this.f = bundle2;
        this.g = z3;
    }

    public void a() {
        ga.a(g.r);
        this.h.j.b();
        boolean z = this.f440a;
        if (!z || (z && this.b)) {
            a aVar = new a();
            Executor executor = va.f637a;
            executor.execute(aVar);
            this.h.a(this.e, this.f);
            g gVar = this.h;
            t5 t5Var = gVar.f335a;
            z0 z0Var = gVar.j;
            x0 x0Var = gVar.k;
            String str = this.c;
            boolean z2 = this.g;
            Bundle bundle = this.f;
            c7.a(t5Var, new MAPAccountManager(t5Var).getAccount());
            executor.execute(new w(t5Var, str, z0Var, x0Var, z2, bundle, null));
        }
    }
}
