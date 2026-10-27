package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.kcpsdk.common.ParseError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q extends tb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ p.b f499a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ u c;
    public final /* synthetic */ y5 d;
    public final /* synthetic */ String e;
    public final /* synthetic */ p f;

    public q(p pVar, p.b bVar, boolean z, u uVar, y5 y5Var, String str) {
        this.f = pVar;
        this.f499a = bVar;
        this.b = z;
        this.c = uVar;
        this.d = y5Var;
        this.e = str;
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a(Object obj) {
        String str = p.m;
        Log.i(ga.a(str), "Successfully completed the deregistration request");
        wb wbVar = (wb) obj;
        if (wbVar == null) {
            this.f499a.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during deregistration. Received a null response", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Deregistration Error: Null response", null);
            return;
        }
        ub ubVar = wbVar.f650a;
        if (ubVar != null) {
            if (ubVar.f614a.ordinal() != 0) {
                this.f499a.a(MAPError.CommonError.INVALID_RESPONSE, "Error occurred during deregistration. Received an unrecognizable response", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Deregistration Error: Unrecognized response", null);
                return;
            } else {
                this.f499a.a(MAPError.AccountError.DEREGISTER_FAILED, "Error occurred during deregistration", MAPAccountManager.RegistrationError.DEREGISTER_FAILED, "Deregistration Error: Failed", null);
                return;
            }
        }
        if (!this.b) {
            String str2 = this.e;
            synchronized (e3.class) {
                d3 d3Var = e3.f294a;
                ArrayList arrayList = new ArrayList();
                for (String str3 : e3.c.keySet()) {
                    if (str3.contains(str2)) {
                        arrayList.add(str3);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    e3.c.remove((String) it.next());
                }
            }
            this.f499a.a(null, null, null);
            return;
        }
        synchronized (e3.class) {
            e3.c.clear();
        }
        p pVar = this.f;
        u uVar = this.c;
        p.b bVar = this.f499a;
        y5 y5Var = this.d;
        if (!(!e.g(pVar.b.c()))) {
            ga.a(str);
            bVar.a(null, null, null);
            return;
        }
        Log.i(ga.a(str), "Attempting to get anonymous credentials");
        String strD = pVar.b.d();
        String strF = pVar.b.f();
        nc ncVar = new nc();
        ncVar.c(pVar.b.c());
        ncVar.d(pVar.b.a(y5Var));
        ncVar.b(strF);
        ncVar.a(strD);
        ncVar.a(pVar.b.g());
        ncVar.l = pVar.a(strF);
        ncVar.k = e.a(Locale.getDefault());
        s sVar = new s(pVar, bVar, uVar);
        ncVar.a(y5Var);
        if (ncVar.c() != null) {
            pVar.a(ncVar, sVar, y5Var);
        } else {
            bVar.a(MAPError.CommonError.INTERNAL_ERROR, "Could not construct a valid pre-registration request to get anonymous credentials", MAPAccountManager.RegistrationError.UNRECOGNIZED, "Could not construct a valid pre-registration request to get anonymous credentials", null);
        }
    }

    @Override // com.amazon.identity.auth.device.tb
    public void b() {
        yd.b("NetworkError6:AccountRegistrar", new String[0]);
        this.f499a.a(MAPError.CommonError.NETWORK_ERROR, "Network failure occurred while performing deregistration request", MAPAccountManager.RegistrationError.NETWORK_FAILURE, "Network failure performing deregistration request", null);
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a() {
        this.f499a.a(MAPError.AccountError.AUTHENTICATION_FAILED, "Authentication failure occurred while performing deregistration request", MAPAccountManager.RegistrationError.AUTHENTICATION_FAILED, "Authentication failure performing deregistration request", null);
    }

    @Override // com.amazon.identity.auth.device.tb
    public void a(ParseError parseError) {
        this.f499a.a(MAPError.CommonError.PARSE_ERROR, "Parsing failure occurred while performing deregistration request", MAPAccountManager.RegistrationError.PARSE_ERROR, "Parsing failure performing deregistration request", null);
    }
}
