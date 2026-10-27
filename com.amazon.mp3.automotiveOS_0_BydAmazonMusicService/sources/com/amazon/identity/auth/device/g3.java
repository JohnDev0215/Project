package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class g3 extends d3 {
    public static final String n = "com.amazon.identity.auth.device.g3";
    public final t5 e;
    public final AmazonAccountManager f;
    public final BackwardsCompatiableDataStorage g;
    public a h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;

    public g3(Context context, String str, String str2) {
        t5 t5VarA = t5.a(context.getApplicationContext());
        this.e = t5VarA;
        this.m = str2;
        this.f = (AmazonAccountManager) t5VarA.getSystemService("dcp_amazon_account_man");
        this.g = new BackwardsCompatiableDataStorage(t5VarA);
        this.j = str;
        this.i = e();
        this.k = c();
        this.l = d();
    }

    @Override // com.amazon.identity.auth.device.ac
    public a a() {
        if (this.h == null) {
            this.h = new a(b(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN), b(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
        }
        return this.h;
    }

    public final String b(String str) {
        String strB = b();
        if (strB == null) {
            Log.e(ga.a(n), "Failed to locate an amazon account to retrieve amazon credentials from.");
            return null;
        }
        if (!TextUtils.isEmpty(this.m)) {
            str = e.a(this.e, this.m, str);
        }
        BackwardsCompatiableDataStorage backwardsCompatiableDataStorage = this.g;
        return backwardsCompatiableDataStorage.f(str) ? backwardsCompatiableDataStorage.d(strB, str) : backwardsCompatiableDataStorage.f548a.c(strB, str);
    }

    public final String c() {
        return a(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN);
    }

    public final String d() {
        return a(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY);
    }

    public String e() {
        String strB = b();
        if (strB != null) {
            return this.g.d(strB, AccountConstants.KEY_ACCOUNT_UUID);
        }
        ga.a(n);
        return null;
    }

    @Override // com.amazon.identity.auth.device.d3
    public boolean a(Context context) {
        String strD;
        String strB = b();
        if (strB == null) {
            ga.a(n);
            strD = null;
        } else {
            strD = this.g.d(strB, AccountConstants.KEY_ACCOUNT_UUID);
        }
        return (strD != null && strD.equals(this.i) && TextUtils.equals(this.k, a(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN)) && TextUtils.equals(this.l, a(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY))) ? false : true;
    }

    public final String b() {
        if (this.j == null) {
            this.j = this.f.b();
        }
        return this.j;
    }

    public final String a(String str) {
        String strB = b();
        if (strB == null) {
            Log.e(ga.a(n), "Failed to locate an amazon account to retrieve amazon credentials from.");
            return null;
        }
        return this.g.f548a.d(strB, str);
    }
}
