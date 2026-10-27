package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class d7 {
    public static final String e = "d7";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f279a;
    public String b;
    public String c;
    public String d;

    /* JADX INFO: compiled from: DCP */
    public interface a {
        d7 a();

        String b();

        int c();
    }

    public static d7 a(Bundle bundle) {
        d7 d7VarA = a().a(bundle.getString("com.amazon.dcp.sso.property.account.acctId"));
        d7VarA.b = bundle.getString("key_recover_context_reason");
        d7VarA.c = bundle.getString("key_recover_context_url");
        d7VarA.d = bundle.getString("key_recover_context_action");
        return d7VarA;
    }

    public Bundle b() {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", this.f279a);
        bundle.putString("key_recover_context_reason", this.b);
        bundle.putString("key_recover_context_url", this.c);
        bundle.putString("key_recover_context_action", this.d);
        return bundle;
    }

    public Bundle c() {
        Bundle bundle = new Bundle();
        bundle.putBundle("com.amazon.identity.mobi.account.recover.context", b());
        return bundle;
    }

    public d7 b(String str) {
        yd.a("BuildAccountRecoverContext:" + str, new String[0]);
        this.b = str;
        return this;
    }

    public static d7 a() {
        ga.a(e);
        d7 d7Var = new d7();
        d7Var.d = "action_confirm_credential";
        return d7Var;
    }

    public d7 a(String str) {
        Log.i(ga.a(e), "Building an account recover context bundle for: ***" + str.substring(str.length() - Math.min(2, str.length())));
        this.f279a = str;
        return this;
    }

    public static d7 a(MAPCallbackErrorException mAPCallbackErrorException) {
        Bundle bundle;
        Bundle errorBundle = mAPCallbackErrorException.getErrorBundle();
        if (errorBundle == null || (bundle = errorBundle.getBundle("com.amazon.identity.mobi.account.recover.context")) == null) {
            return null;
        }
        return a(bundle);
    }
}
