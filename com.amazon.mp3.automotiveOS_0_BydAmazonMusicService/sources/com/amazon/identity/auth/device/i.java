package com.amazon.identity.auth.device;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i implements p.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Callback f374a;
    public final /* synthetic */ g b;

    public i(g gVar, Callback callback) {
        this.b = gVar;
        this.f374a = callback;
    }

    @Override // com.amazon.identity.auth.device.p.b
    public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
        Log.i(ga.a(g.r), "Failed to transfer account. Error : " + registrationError.getName());
        g gVar = this.b;
        Callback callback = this.f374a;
        String name = registrationError.getName();
        gVar.getClass();
        y.a(callback, MAPError.CommonError.BAD_REQUEST, name, 7, name);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [com.amazon.identity.auth.device.u7, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.amazon.identity.auth.device.p.b
    public void a(String str, String str2, Bundle bundle) {
        String str3;
        ?? r4;
        String str4;
        Map<String, String> mapA;
        String str5 = g.r;
        Log.i(ga.a(str5), "Remote transfer of device successfully completed. Proceeding with local operations...");
        g gVar = this.b;
        Callback callback = this.f374a;
        gVar.getClass();
        if (bundle == null) {
            Log.e(ga.a(str5), "No userdata returned. The account cannot be created.");
            y.a(callback, MAPError.CommonError.BAD_REQUEST, "No userdata given. Cannot construct an account", 7, "No userdata given. Cannot construct an account");
            return;
        }
        String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        Bundle bundle2 = new Bundle();
        Set<String> setA = gVar.e.a();
        if (setA != null) {
            Iterator<String> it = setA.iterator();
            while (it.hasNext()) {
                gVar.e.f144a.e(it.next());
            }
            gVar.j.b();
        }
        gVar.k.a(string, new Bundle(), bundle);
        gVar.a(new Bundle(), bundle);
        String strA = r9.a(gVar.i, str2);
        CORPFMResponse cORPFMResponseA = gVar.a(bundle);
        Map<String, Map<String, String>> mapB = gVar.b(bundle);
        List<MAPCookie> listA = gVar.a(string, bundle);
        Bundle bundleC = gVar.c(bundle);
        if (bundleC.isEmpty()) {
            str3 = strA;
            r4 = 0;
            str4 = null;
            mapA = null;
        } else {
            String strA2 = EnvironmentUtils.c.a(q9.a((Bundle) null));
            str3 = strA;
            r4 = 0;
            mapA = gVar.a(string, bundleC, listA, strA2, (String) null);
            str4 = strA2;
        }
        Map<String, String> mapD = e.d(bundle);
        s1 s1Var = gVar.l;
        s1Var.getClass();
        if (cORPFMResponseA == null) {
            Log.w(ga.a(s1.i), "Cor/PFM response given to set is null. Not setting.");
        } else {
            s1Var.a(r4, cORPFMResponseA, mapD);
        }
        o7 o7Var = new o7(string, mapD, mapA, r4);
        for (Map.Entry<String, Map<String, String>> entry : mapB.entrySet()) {
            n0.a(gVar.f335a, o7Var, entry.getKey(), entry.getValue());
        }
        String str6 = str3;
        if (!new BackwardsCompatiableDataStorage(gVar.f335a, gVar.i).a(str6, o7Var, new n(gVar, false, true, string, bundle, Collections.emptyList(), null, true))) {
            y.a(callback, string);
            return;
        }
        r9.a(str6, string, bundle2);
        if (str4 != null && !e.a(gVar.n.a(null, str4, null))) {
            gVar.n.a((String) null, str4, (String) null, (List<MAPCookie>) null);
        }
        String str7 = g.r;
        Log.i(ga.a(str7), "Emitting broadcast indicating an account transfer.");
        x0 x0Var = gVar.k;
        Intent intentA = e.a(MAPAccountManager.ACCOUNT_CHANGED_ON_DEVICE_INTENT_ACTION);
        intentA.putExtra(MAPAccountManager.KEY_DIRECTED_ID_POST_ACCOUNT_CHANGE, string);
        x0Var.a(string, intentA, AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
        Log.i(ga.a(str7), "MAP finished adding account locally and will do the other necessary work asynchronously in the data propogation callback");
        callback.onSuccess(bundle2);
    }
}
