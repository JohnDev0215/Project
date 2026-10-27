package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m implements p.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RegistrationType f424a;
    public final /* synthetic */ Bundle b;
    public final /* synthetic */ Callback c;
    public final /* synthetic */ y5 d;
    public final /* synthetic */ g e;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f425a;
        public final /* synthetic */ Bundle b;

        public a(String str, Bundle bundle) {
            this.f425a = str;
            this.b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            y5 y5Var;
            BackwardsCompatiableDataStorage backwardsCompatiableDataStorage;
            CORPFMResponse cORPFMResponse;
            Bundle bundle;
            String str;
            Map<String, String> mapA;
            String str2;
            u7 u7Var;
            String str3;
            Bundle bundle2;
            m mVar = m.this;
            g gVar = mVar.e;
            RegistrationType registrationType = mVar.f424a;
            Bundle bundle3 = mVar.b;
            Callback callback = mVar.c;
            String str4 = this.f425a;
            Bundle bundle4 = this.b;
            y5 y5Var2 = mVar.d;
            c cVar = g.q;
            gVar.getClass();
            if (bundle4 == null) {
                Log.e(ga.a(g.r), "No userdata returned. The account cannot be created.");
                y.a(callback, MAPError.CommonError.BAD_REQUEST, "No userdata given. Cannot construct an account", 7, "No userdata given. Cannot construct an account");
                return;
            }
            String string = bundle4.getString("com.amazon.dcp.sso.property.account.acctId");
            boolean z = bundle3.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT);
            Bundle bundle5 = new Bundle();
            if (((HashSet) gVar.e.d()).isEmpty()) {
                bundle4.putString(AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
            }
            Bundle bundle6 = bundle3.getBundle(MAPAccountManager.KEY_CLIENT_EVENT_CONTEXT);
            bundle4.putString(CustomerAttributeKeys.KEY_IS_ANONYMOUS, Boolean.valueOf(registrationType == RegistrationType.ANONYMOUS).toString());
            boolean z2 = !string.equals(bundle3.getString("com.amazon.dcp.sso.property.account.acctId"));
            if (z) {
                bundle5.putBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT, true);
                if (z2) {
                    Set<String> setA = gVar.e.a();
                    if (setA != null) {
                        for (Iterator<String> it = setA.iterator(); it.hasNext(); it = it) {
                            gVar.e.f144a.e(it.next());
                            gVar.a(gVar.a(setA), bundle6);
                        }
                        gVar.j.b();
                    }
                    bundle5.putString(MAPAccountManager.KEY_ACCOUNT_RECOVERY_BY_USING_NEW_ACCOUNT, string);
                }
            }
            if (Boolean.parseBoolean(bundle4.getString(MAPAccountManager.KEY_IS_AMAZON_BUSINESS_ACCOUNT))) {
                bundle5.putBoolean(MAPAccountManager.KEY_IS_AMAZON_BUSINESS_ACCOUNT, true);
            }
            gVar.k.a(string, bundle3, bundle4);
            if (gVar.e.e() && gVar.d(bundle3) && !TextUtils.equals(gVar.e.b(), string) && (!z || (!bundle3.containsKey(MAPAccountManager.KEY_LINK_CODE) && !bundle3.containsKey(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE)))) {
                bundle4.putString(AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT, "true");
                if (bundle3.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary")) {
                    bundle4.putString(AccountConstants.KEY_DEVICE_ACCOUNT_ROLE, RegisterDeviceRequest.DeviceAccountRole.PRIMARY.name());
                }
            }
            if (gVar.e.b(string)) {
                gVar.a(string, (String) null, bundle4);
                if (!z) {
                    Log.w(ga.a(g.r), "An account has been registered multiple times and this is not a recovery.");
                    y.a(callback, string);
                    return;
                }
            }
            BackwardsCompatiableDataStorage backwardsCompatiableDataStorage2 = new BackwardsCompatiableDataStorage(gVar.f335a, gVar.i);
            bundle4.putString(AccountConstants.KEY_ACCOUNT_UUID, UUID.randomUUID().toString());
            Iterator<String> it2 = bundle3.keySet().iterator();
            while (it2.hasNext()) {
                String next = it2.next();
                Iterator<String> it3 = it2;
                if (next.startsWith("com.amazon.dcp.sso.property.account.extratokens")) {
                    bundle4.putString(next, bundle3.getString(next));
                }
                it2 = it3;
            }
            bundle4.putString("force_refresh_dms_to_oauth_done_once", "true");
            if (registrationType == RegistrationType.REGISTER_DELEGATED_ACCOUNT) {
                bundle4.putString("com.amazon.dcp.sso.property.account.delegateeaccount", bundle3.getString("com.amazon.dcp.sso.property.account.delegateeaccount"));
                String strA = q9.a(bundle3);
                if (!TextUtils.isEmpty(strA)) {
                    bundle4.putString(AccountConstants.KEY_DELEGATION_DOMAIN, strA);
                }
            }
            gVar.a(bundle3, bundle4);
            String strA2 = r9.a(gVar.i, str4);
            CORPFMResponse cORPFMResponseA = gVar.a(bundle4);
            Map<String, Map<String, String>> mapB = gVar.b(bundle4);
            List<MAPCookie> listA = gVar.a(string, bundle4);
            Bundle bundleC = gVar.c(bundle4);
            if (bundleC.isEmpty()) {
                y5Var = y5Var2;
                backwardsCompatiableDataStorage = backwardsCompatiableDataStorage2;
                cORPFMResponse = cORPFMResponseA;
                bundle = bundle5;
                str = strA2;
                mapA = null;
                str2 = null;
            } else {
                String strA3 = EnvironmentUtils.c.a(q9.a(bundle3));
                backwardsCompatiableDataStorage = backwardsCompatiableDataStorage2;
                y5Var = y5Var2;
                cORPFMResponse = cORPFMResponseA;
                bundle = bundle5;
                str = strA2;
                mapA = gVar.a(string, bundleC, listA, strA3, (String) null);
                str2 = strA3;
            }
            Map<String, String> mapD = e.d(bundle4);
            s1 s1Var = gVar.l;
            s1Var.getClass();
            if (cORPFMResponse == null) {
                Log.w(ga.a(s1.i), "Cor/PFM response given to set is null. Not setting.");
                u7Var = null;
            } else {
                u7Var = null;
                s1Var.a(null, cORPFMResponse, mapD);
            }
            o7 o7Var = new o7(string, mapD, mapA, u7Var);
            for (Map.Entry<String, Map<String, String>> entry : mapB.entrySet()) {
                n0.a(gVar.f335a, o7Var, entry.getKey(), entry.getValue());
            }
            boolean z3 = bundle3.getBoolean(MAPAccountManager.KEY_IS_NEW_ACCOUNT, false);
            if (bundle3.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false)) {
                ArrayList arrayList = new ArrayList(gVar.e.a());
                bundle2 = bundle3;
                str3 = str2;
                n nVar = new n(gVar, z, z2, string, bundle4, gVar.a(gVar.e.a()), bundle6, z3);
                gVar.j.b();
                if (!backwardsCompatiableDataStorage.f548a.a(str, o7Var, nVar, arrayList)) {
                    y.a(callback, MAPError.AccountError.REPLACE_ACCOUNTS_FAILED, "Failed to replace accounts on device", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Failed to replace accounts on device", null);
                    return;
                }
            } else {
                str3 = str2;
                bundle2 = bundle3;
                if (!backwardsCompatiableDataStorage.a(str, o7Var, new n(gVar, z, z2, string, bundle4, Collections.emptyList(), bundle6, z3))) {
                    y.a(callback, string);
                    return;
                }
            }
            gVar.j.b();
            Bundle bundle7 = bundle;
            r9.a(str, string, bundle7);
            if (str3 != null) {
                String str5 = str3;
                if (!e.a(gVar.n.a(null, str5, null))) {
                    gVar.n.a((String) null, str5, (String) null, (List<MAPCookie>) null);
                }
            }
            String str6 = g.r;
            Log.i(ga.a(str6), "MAP finished adding account locally and will do the other necessary work asynchronously in the data propogation callback");
            Bundle bundle8 = bundle2;
            if (!bundle8.getBoolean(MAPAccountManager.KEY_DISABLE_USERNAME_AUTO_SUGGESTION)) {
                String strA4 = o7Var.a("com.amazon.dcp.sso.token.oauth.amazon.access_token");
                if (TextUtils.isEmpty(strA4)) {
                    Log.e(ga.a(str6), "access token is null after sign in!");
                } else {
                    va.f637a.execute(new h(gVar, strA4, bundle8, y5Var));
                }
            }
            callback.onSuccess(bundle7);
        }
    }

    public m(g gVar, RegistrationType registrationType, Bundle bundle, Callback callback, y5 y5Var) {
        this.e = gVar;
        this.f424a = registrationType;
        this.b = bundle;
        this.c = callback;
        this.d = y5Var;
    }

    @Override // com.amazon.identity.auth.device.p.b
    public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
        ga.a(g.r);
        this.e.a(mAPError, str, registrationError, this.c, bundle, str2);
    }

    @Override // com.amazon.identity.auth.device.p.b
    public void a(String str, String str2, Bundle bundle) {
        va.f637a.execute(new a(str2, bundle));
    }

    public void a(String str) {
        y.a(this.c, str);
    }
}
