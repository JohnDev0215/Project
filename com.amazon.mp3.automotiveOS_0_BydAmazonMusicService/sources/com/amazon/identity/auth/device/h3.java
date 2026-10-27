package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class h3 extends d3 {
    public final Context e;
    public final AmazonAccountManager f;
    public final u7 g;
    public a h;

    public h3(Context context) {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        t5 t5VarA = t5.a(context.getApplicationContext());
        this.e = t5VarA;
        this.f = (AmazonAccountManager) t5VarA.getSystemService("dcp_amazon_account_man");
        this.g = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
    }

    @Override // com.amazon.identity.auth.device.d3
    public boolean a(Context context) {
        if (this.f.e()) {
            return true;
        }
        a aVarA = a(this.g);
        return TextUtils.isEmpty(a().f165a) || TextUtils.isEmpty(a().b) || !a().f165a.equals(aVarA.f165a) || !a().b.equals(aVarA.b);
    }

    @Override // com.amazon.identity.auth.device.ac
    public a a() {
        a aVar = this.h;
        if (aVar == null || TextUtils.isEmpty(aVar.b) || TextUtils.isEmpty(this.h.f165a)) {
            this.h = a(this.g);
        }
        return this.h;
    }

    public static a a(u7 u7Var) {
        a aVar;
        List<String> list = AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN_AND_PRIVATE_KEY_LIST;
        Map<String, String> mapA = u7Var.a("com.amazon.identity.auth.device.b.e.SHARED_PREFS", list);
        String str = mapA.get(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN);
        String str2 = mapA.get(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Log.i(ga.a("AnonymousAccountCredentials"), "Migrating anonymous credentials from legacy name space to new one.");
            yd.b("obfuscated_ANONYMOUS_CREDENTIALS_NAMESPACE", new String[0]);
            u7Var.a("com.amazon.identity.auth.device.credentials.AnonymousAccountCredentials.SHARED_PREFS", mapA);
            HashMap map = new HashMap();
            map.put(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, "");
            map.put(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, "");
            u7Var.a("com.amazon.identity.auth.device.b.e.SHARED_PREFS", map);
            aVar = new a(str, str2);
        } else {
            Map<String, String> mapA2 = u7Var.a("com.amazon.identity.auth.device.credentials.AnonymousAccountCredentials.SHARED_PREFS", list);
            aVar = new a(mapA2.get(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN), mapA2.get(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
        }
        Log.i(ga.a("AnonymousAccountCredentials"), String.format("Returning adp token: %s, private key: %s", ga.a(aVar.f165a, 5), ga.a(aVar.b, 5)));
        return aVar;
    }
}
