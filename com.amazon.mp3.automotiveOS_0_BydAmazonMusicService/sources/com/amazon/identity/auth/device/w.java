package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class w implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t5 f641a;
    public final /* synthetic */ String b;
    public final /* synthetic */ z0 c;
    public final /* synthetic */ x0 d;
    public final /* synthetic */ boolean e;
    public final /* synthetic */ Bundle f;
    public final /* synthetic */ String g;

    public w(t5 t5Var, String str, z0 z0Var, x0 x0Var, boolean z, Bundle bundle, String str2) {
        this.f641a = t5Var;
        this.b = str;
        this.c = z0Var;
        this.d = x0Var;
        this.e = z;
        this.f = bundle;
        this.g = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Account accountA = r9.a(this.f641a, this.b);
        t5 t5Var = this.f641a;
        z0 z0Var = this.c;
        x0 x0Var = this.d;
        String str = this.b;
        boolean z = this.e;
        Bundle bundle = this.f;
        Set<Integer> setA = z0Var.a(t5Var, str);
        Intent intentA = x.a(str, null, "com.amazon.identity.auth.account.added.on.device", null, z, bundle);
        intentA.putIntegerArrayListExtra(MAPAccountManager.KEY_EXTRA_PROFILES_ACCOUNT_ADDED_OR_REMOVED_BELONG_TO, new ArrayList<>(setA));
        x0Var.b(str, intentA, AccountConstants.PERMISSION_AMAZON_MULTIPLE_PROFILE_AWARE);
        if (this.c.a(this.b)) {
            String str2 = x.f652a;
            String.format("%s sends primary account add broadcast", this.f641a.getPackageName());
            ga.a(str2);
            x0 x0Var2 = this.d;
            String str3 = this.b;
            x0Var2.a(str3, x.a(str3, accountA, "com.amazon.dcp.sso.action.account.added", this.g, this.e, this.f), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
            return;
        }
        String str4 = x.f652a;
        String.format("%s sends secondary account add broadcast", this.f641a.getPackageName());
        ga.a(str4);
        x0 x0Var3 = this.d;
        String str5 = this.b;
        x0Var3.a(str5, x.a(str5, accountA, "com.amazon.dcp.sso.action.secondary.account.added", this.g, this.e, this.f), AccountConstants.PERMISSION_AMAZON_ACCOUNT_CHANGED);
    }
}
