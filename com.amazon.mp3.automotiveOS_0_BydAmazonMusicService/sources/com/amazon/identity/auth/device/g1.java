package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.kcpsdk.common.ParseError;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class g1 implements g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f344a;
    public final l4 b;

    public g1(Context context) {
        t5 t5VarA = t5.a(context);
        this.f344a = t5VarA;
        this.b = (l4) t5VarA.getSystemService("sso_webservice_caller_creator");
    }

    @Override // com.amazon.identity.auth.device.g0
    public void a(String str, ld ldVar, h0 h0Var, y5 y5Var) {
        d3 d3VarA;
        a aVar = new a(this, h0Var);
        mc mcVar = new mc();
        Context context = this.b.f418a;
        synchronized (e3.class) {
            d3VarA = e3.a(context, str, null);
        }
        tc tcVar = new tc(d3VarA);
        xc xcVar = new xc(mcVar);
        if (d3VarA != null && d3VarA.a(context)) {
            d3 d3VarB = e3.b(context, null, null);
            boolean z = tcVar.c;
            tcVar = new tc(d3VarB);
            tcVar.c = z;
            if (z) {
                Log.i(ga.a(tc.d), "Try to set useLegacyAuthentication to be true when algorithm is: " + tcVar.f585a);
                if (tcVar.b != null) {
                    tcVar.a();
                    if (!tcVar.f585a.equalsIgnoreCase("SHA256WithRSA")) {
                        throw new IllegalStateException("LegacyAuthentication is not compatible with algorithm:" + tcVar.f585a);
                    }
                }
            }
        }
        new o4(context, ldVar, new uc(xcVar, aVar, ldVar.j), tcVar, y5Var).a();
    }

    /* JADX INFO: compiled from: DCP */
    public class a extends tb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h0 f345a;

        public a(g1 g1Var, h0 h0Var) {
            this.f345a = h0Var;
        }

        @Override // com.amazon.identity.auth.device.tb
        public void a(Object obj) {
            ((j0) this.f345a).a((kc) obj);
        }

        @Override // com.amazon.identity.auth.device.tb
        public void b() {
            ((j0) this.f345a).c();
        }

        @Override // com.amazon.identity.auth.device.tb
        public void a() {
            ((j0) this.f345a).a();
        }

        @Override // com.amazon.identity.auth.device.tb
        public void a(ParseError parseError) {
            ((j0) this.f345a).b();
        }
    }
}
