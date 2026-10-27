package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class e3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d3 f294a = new f3(null, null);
    public static d3 b = null;
    public static Map<String, d3> c = new HashMap();

    public static d3 a(Context context, String str, String str2) {
        g3 g3Var;
        AmazonAccountManager amazonAccountManager = new AmazonAccountManager(context);
        if (str == null || amazonAccountManager.d(str)) {
            return b(context, str, str2);
        }
        String strA = a(str, str2);
        d3 d3Var = c.get(strA);
        Context applicationContext = context.getApplicationContext();
        if ((d3Var == null || d3Var.a(applicationContext)) ? false : true) {
            return d3Var;
        }
        if (new AmazonAccountManager(applicationContext).a(str)) {
            g3Var = new g3(applicationContext, str, str2);
        } else {
            Log.e(ga.a(g3.n), "Error, the account given does not exist. Cannot construct account credentials");
            g3Var = null;
        }
        if (g3Var == null) {
            return f294a;
        }
        c.put(strA, g3Var);
        return g3Var;
    }

    public static synchronized d3 b(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        d3 d3Var = !TextUtils.isEmpty(str) ? c.get(a(str, str2)) : b;
        if ((d3Var == null || d3Var.a(applicationContext)) ? false : true) {
            return d3Var;
        }
        d3 g3Var = !new AmazonAccountManager(applicationContext).e() ? null : new g3(applicationContext, str, str2);
        if (g3Var == null) {
            t5 t5VarA = t5.a(applicationContext.getApplicationContext());
            ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
            g3Var = new h3(t5VarA);
        }
        if (TextUtils.isEmpty(str)) {
            b = g3Var;
        } else {
            c.put(a(str, str2), g3Var);
        }
        return g3Var;
    }

    public static String a(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : String.format("%s/%s", str2, str);
    }
}
