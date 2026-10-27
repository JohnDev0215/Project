package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
@TargetApi(17)
public class b1 implements x0 {
    public static final String e = "com.amazon.identity.auth.device.b1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f235a;
    public final z0 b;
    public final b6 c;
    public final AmazonAccountManager d;

    public b1(t5 t5Var, z0 z0Var, b6 b6Var, AmazonAccountManager amazonAccountManager) {
        this.f235a = t5Var;
        this.b = z0Var;
        this.c = b6Var;
        this.d = amazonAccountManager;
    }

    @Override // com.amazon.identity.auth.device.x0
    public void a(String str, Intent intent, String str2) {
        m4 m4VarB = this.c.b();
        if (a(str, m4VarB)) {
            a(intent, str2, m4VarB);
        } else {
            ga.a(e, "Not Sending broadcast %s to user %s since it is not visible", intent.getAction(), Integer.toString(m4VarB.b));
        }
    }

    @Override // com.amazon.identity.auth.device.x0
    public void b(String str, Intent intent, String str2) {
        b6 b6Var = this.c;
        b6Var.a();
        Integer num = m4.e;
        a(intent, str2, b6Var.a(num == null ? 0 : num.intValue()));
    }

    @Override // com.amazon.identity.auth.device.x0
    public void c(String str, Intent intent, String str2) {
        a(intent);
        b6 b6Var = this.c;
        b6Var.a();
        Integer num = m4.e;
        m4 m4VarA = b6Var.a(num == null ? 0 : num.intValue());
        m4 m4VarB = this.c.b();
        String str3 = e;
        ga.a(str3, "Sending broadcast %s to current user %s and main user %s", intent.getAction(), Integer.toString(m4VarB.b), Integer.toString(m4VarA.b));
        if (a(str, m4VarB)) {
            e.a(this.f235a, intent, str2, m4VarB);
        }
        Log.i(ga.a(str3), "Sending broadcast to User 0 quick setting bar.");
        Intent intent2 = new Intent(intent);
        intent2.setPackage("com.android.systemui");
        e.a(this.f235a, intent2, (String) null, m4VarA);
    }

    public final void a(Intent intent, String str, m4 m4Var) {
        ga.a(e, "Sending broadcast %s to user %s", intent.getAction(), Integer.toString(m4Var.b));
        a(intent);
        e.a(this.f235a, intent, str, m4Var);
    }

    public final void a(Intent intent) {
        String action = intent.getAction();
        if ("com.amazon.dcp.sso.action.account.added".equals(action) || "com.amazon.dcp.sso.action.secondary.account.added".equals(action) || "com.amazon.dcp.sso.action.account.removed".equals(action) || "com.amazon.dcp.sso.action.secondary.account.removed".equals(action) || "com.amazon.identity.auth.account.added.on.device".equals(action) || "com.amazon.identity.auth.account.removed.on.device".equals(action) || "com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed".equals(action) || CustomerAttributeStore.COR_PFM_CHANGED_INTENT_ACTION.equals(action)) {
            ga.a(e);
            intent.addFlags(268435456);
        }
    }

    @Override // com.amazon.identity.auth.device.x0
    public MultipleAccountManager.AccountMappingType[] a(String str, int i) {
        return new MultipleAccountManager.AccountMappingType[]{new MultipleAccountManager.PackageMappingType(str), MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(i)};
    }

    @Override // com.amazon.identity.auth.device.x0
    public String a() {
        m4 m4VarB = this.c.b();
        String strA = this.b.a(MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(m4VarB.b));
        ga.a(e, "Detected visible user %s associated to account %s", Integer.toString(m4VarB.b), strA);
        return strA;
    }

    @Override // com.amazon.identity.auth.device.x0
    public boolean a(String str) {
        boolean zD = this.d.d(str);
        Log.i(ga.a(e), "deregisterAllAccountsOnAccountRemoval returns: " + zD);
        return zD;
    }

    public final boolean a(String str, m4 m4Var) {
        String str2 = e;
        String.format("Directed-id to fire broadcast to : %s; User-id : %s", str, Integer.valueOf(m4Var.b));
        ga.a(str2);
        if (str == null) {
            ga.a(str2);
            return true;
        }
        if (this.b.a(str, MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(m4Var.b))) {
            ga.a(str2);
            return true;
        }
        return !this.b.a(str);
    }

    @Override // com.amazon.identity.auth.device.x0
    public void a(String str, Bundle bundle, Bundle bundle2) {
        int iIntValue;
        if (bundle.containsKey(MAPAccountManager.KEY_PROFILE_MAPPING)) {
            iIntValue = bundle.getInt(MAPAccountManager.KEY_PROFILE_MAPPING);
        } else if (bundle.containsKey("calling_profile")) {
            iIntValue = bundle.getInt("calling_profile");
        } else {
            Log.w(ga.a(e), "No calling profile or mapping profile given. Defaulting to main profile");
            Integer num = m4.e;
            iIntValue = num == null ? 0 : num.intValue();
        }
        Integer numValueOf = Integer.valueOf(iIntValue);
        if (numValueOf != null) {
            if ((this.b.a(MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(numValueOf.intValue())) == null) || bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false)) {
                bundle2.putString("com.amazon.dcp.sso.property.account.extratokens.account_profiles", Integer.toString(numValueOf.intValue()));
            }
        }
    }

    @Override // com.amazon.identity.auth.device.x0
    public void a(String str, Set<Integer> set, Intent intent, String str2) {
        m4 m4VarB = this.c.b();
        if (m4VarB == null) {
            Log.e(ga.a(e), "Can not send broadcast if current AndroidUser is null");
            return;
        }
        HashSet hashSet = new HashSet(set);
        Integer numValueOf = Integer.valueOf(m4VarB.b);
        if (hashSet.contains(numValueOf)) {
            a(intent, str2, m4VarB);
            hashSet.remove(numValueOf);
        }
    }
}
