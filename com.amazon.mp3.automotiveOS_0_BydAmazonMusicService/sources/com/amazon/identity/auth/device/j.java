package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.c;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class j implements c.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Set f388a;
    public final /* synthetic */ y5 b;
    public final /* synthetic */ Bundle c;
    public final /* synthetic */ g d;

    public j(g gVar, Set set, y5 y5Var, Bundle bundle) {
        this.d = gVar;
        this.f388a = set;
        this.b = y5Var;
        this.c = bundle;
    }

    @Override // com.amazon.identity.auth.device.c.b
    public Bundle a(Callback callback) {
        g gVar = this.d;
        Set<String> set = this.f388a;
        y5 y5Var = this.b;
        Bundle bundle = this.c;
        gVar.getClass();
        for (String str : set) {
            if (gVar.k.a(str)) {
                if (!gVar.e.a(str)) {
                    gVar.a(callback, true);
                    return null;
                }
                Log.i(ga.a(g.r), "deregisterAllAccountsInner - deregister the primary first");
                gVar.b(str, new k(gVar, y5Var, bundle, callback), y5Var, bundle);
                return null;
            }
        }
        gVar.a(set, callback, y5Var, bundle);
        return null;
    }
}
