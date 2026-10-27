package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;
import com.amazon.identity.auth.device.p9;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o9 implements AccountManagerCallback<Boolean> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Account f478a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Bundle c;
    public final /* synthetic */ p9.b d;
    public final /* synthetic */ p9 e;

    public o9(p9 p9Var, Account account, String str, Bundle bundle, p9.b bVar) {
        this.e = p9Var;
        this.f478a = account;
        this.b = str;
        this.c = bundle;
        this.d = bVar;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture<Boolean> accountManagerFuture) {
        synchronized (p9.e) {
            de deVarB = yd.b("AccountManagerWrapper", "addAccountExplicitly");
            boolean zAddAccountExplicitly = this.e.b.addAccountExplicitly(this.f478a, this.b, this.c);
            deVarB.c();
            if (zAddAccountExplicitly) {
                p9.c cVar = (p9.c) this.d;
                cVar.b = true;
                cVar.f494a.countDown();
            } else {
                p9.c cVar2 = (p9.c) this.d;
                cVar2.b = false;
                cVar2.f494a.countDown();
            }
        }
    }
}
