package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class d4 extends j4 {
    public static final String h = "com.amazon.identity.auth.device.d4";
    public final MultipleAccountManager f;
    public final MAPAccountManager g;

    public d4(Context context, MultipleAccountManager multipleAccountManager) {
        super(context);
        this.f = multipleAccountManager;
        this.g = new MAPAccountManager(this.c);
    }

    @Override // com.amazon.identity.auth.device.j4, com.amazon.identity.auth.device.o5
    public String d() {
        String accountForMapping = this.f.getAccountForMapping(new MultipleAccountManager.PrimaryUserMappingType());
        if (accountForMapping == null) {
            accountForMapping = this.g.getAccount();
        }
        if (accountForMapping != null) {
            t5 t5Var = this.c;
            return new BackwardsCompatiableDataStorage(t5Var, t5Var.a()).d(accountForMapping, "com.amazon.dcp.sso.token.device.deviceserialname");
        }
        Log.e(ga.a(h), "Cannot return DSN on this platform (Grover, Canary V1). We can only return it while the device is registered. Please use MAPAccountManager.getAccount() to ensure an account is existed.");
        throw new UnsupportedOperationException("Cannot return DSN on this platform (Grover, Canary V1). We can only return it while the device is registered. Please use MAPAccountManager.getAccount() to ensure an account is existed.");
    }
}
