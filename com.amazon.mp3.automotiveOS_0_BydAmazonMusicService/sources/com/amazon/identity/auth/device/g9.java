package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.b9;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class g9 implements b9.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f350a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ String d;
    public final /* synthetic */ String e;
    public final /* synthetic */ Bundle f;
    public final /* synthetic */ y5 g;
    public final /* synthetic */ h9 h;

    public g9(h9 h9Var, Context context, String str, String str2, String str3, String str4, Bundle bundle, y5 y5Var) {
        this.h = h9Var;
        this.f350a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = bundle;
        this.g = y5Var;
    }

    @Override // com.amazon.identity.auth.device.b9.d
    public void a(Callback callback) {
        h9 h9Var = this.h;
        Context context = this.f350a;
        String str = this.b;
        String str2 = this.c;
        String str3 = this.d;
        String str4 = this.e;
        Bundle bundle = this.f;
        y5 y5Var = this.g;
        h9Var.i.b();
        fa faVarA = fa.a(str3);
        MAPApplicationInformationQueryer.a(h9Var.f364a).e(faVarA.b);
        try {
            ga.a(h9.k);
            if ("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token".equals(faVarA.c)) {
                h9Var.a(context, str, str2, faVarA, str4, bundle, callback, y5Var);
            } else {
                MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
                a9.a(callback, commonError, commonError.getErrorMessage(), 7, "Key for getting actor token is not recognized");
            }
        } finally {
            MAPApplicationInformationQueryer.a(h9Var.f364a).g(faVarA.b);
        }
    }

    @Override // com.amazon.identity.auth.device.b9.d
    public boolean b() {
        return false;
    }

    @Override // com.amazon.identity.auth.device.b9.d
    public String a() {
        return "GetActorToken:" + this.d;
    }
}
