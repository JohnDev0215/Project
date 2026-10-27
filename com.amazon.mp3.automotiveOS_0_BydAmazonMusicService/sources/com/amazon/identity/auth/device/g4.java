package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.util.Base64;
import android.util.Log;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class g4 extends l7 {
    public static final String d = "com.amazon.identity.auth.device.g4";
    public final u7 b;
    public final String c;

    public g4(t5 t5Var, Account account) {
        this(t5Var.a(), r9.a(t5Var, account));
    }

    @Override // com.amazon.identity.auth.device.l7
    public byte[] b() {
        u7 u7Var = this.b;
        if (u7Var == null) {
            return null;
        }
        String strD = u7Var.d(this.c, AccountConstants.KEY_TOKEN_ENCRYPT_KEY);
        if (strD != null) {
            return Base64.decode(strD, 0);
        }
        Log.e(ga.a(d), "The current account does not have an encryption key. This is probably because it is not registered.");
        return null;
    }

    public g4(u7 u7Var, String str) {
        this.b = u7Var;
        this.c = str;
    }
}
