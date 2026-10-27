package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p9 {
    public static final String d = "com.amazon.identity.auth.device.p9";
    public static final Object e = new Object[0];

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f492a;
    public final AccountManager b;
    public final v c;

    /* JADX INFO: compiled from: DCP */
    public static class a<T> implements AccountManagerCallback<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AccountManagerCallback<T> f493a;
        public final de b;

        public a(AccountManagerCallback<T> accountManagerCallback, de deVar) {
            this.f493a = accountManagerCallback;
            this.b = deVar;
        }

        @Override // android.accounts.AccountManagerCallback
        public void run(AccountManagerFuture<T> accountManagerFuture) {
            this.b.c();
            AccountManagerCallback<T> accountManagerCallback = this.f493a;
            if (accountManagerCallback != null) {
                accountManagerCallback.run(accountManagerFuture);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface b {
    }

    /* JADX INFO: compiled from: DCP */
    public static class c implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CountDownLatch f494a = new CountDownLatch(1);
        public boolean b = false;
    }

    public p9(Context context, AccountManager accountManager) {
        this.f492a = context;
        this.b = accountManager;
        this.c = new v(context);
    }

    public boolean a(Account account, String str, Bundle bundle, boolean z) {
        c cVar = new c();
        y9.a("addAccountExplicitly");
        if (z || !a(account)) {
            a(account, (AccountManagerCallback<Boolean>) new o9(this, account, null, bundle, cVar), true);
        } else {
            cVar.b = false;
            cVar.f494a.countDown();
        }
        try {
            cVar.f494a.await();
        } catch (InterruptedException unused) {
            Log.e(ga.a(d), "Interrupted waiting for defensive remove account.");
        }
        return cVar.b;
    }

    public String b(Account account, String str) {
        y9.a("unprotectedGetUserData");
        if (account == null) {
            throw new IllegalStateException("Account cannot be null");
        }
        if (this.b == null) {
            return null;
        }
        de deVarB = yd.b("AccountManagerWrapper", "getUserData");
        try {
            return this.b.getUserData(account, str);
        } finally {
            deVarB.c();
        }
    }

    public p9() {
        this.f492a = null;
        this.b = null;
        this.c = null;
    }

    public void a(Account account, String str, String str2) {
        y9.a("setUserData");
        if (this.b == null) {
            return;
        }
        de deVarB = yd.b("AccountManagerWrapper", "setUserData");
        try {
            this.b.setUserData(account, str, str2);
        } finally {
            deVarB.c();
        }
    }

    public String a(Account account, String str) {
        y9.a("getUserData");
        if (this.b == null || !a(account)) {
            return null;
        }
        de deVarB = yd.b("AccountManagerWrapper", "getUserData");
        try {
            return this.b.getUserData(account, str);
        } finally {
            deVarB.c();
        }
    }

    public Account[] a(String str) {
        y9.a("getAccountsByType");
        if (this.b == null) {
            return new Account[0];
        }
        de deVarB = yd.b("AccountManagerWrapper", "getAccountsByType");
        try {
            return this.b.getAccountsByType(str);
        } finally {
            deVarB.c();
        }
    }

    public AccountManagerFuture<Boolean> a(Account account, AccountManagerCallback<Boolean> accountManagerCallback, boolean z) {
        Handler handler;
        v vVar;
        y9.a("removeAccount");
        if (this.b == null) {
            return null;
        }
        if (z && (vVar = this.c) != null) {
            synchronized (v.d) {
                if (AccountConstants.AMAZON_ACCOUNT_TYPE.equals(account.type)) {
                    if (!vVar.b.a("Account_To_Remove_" + account.name, Boolean.TRUE, 2, 15)) {
                        Log.e(ga.a(v.c), "Could not write account removed flag to disk");
                    }
                } else {
                    throw new IllegalStateException("Not valid for accounts of type: " + account.type);
                }
            }
        }
        de deVarB = yd.b("AccountManagerWrapper", "removeAccount");
        AccountManager accountManager = this.b;
        a aVar = new a(accountManagerCallback, deVarB);
        synchronized (va.b) {
            handler = va.d;
            if (handler == null) {
                ua uaVar = new ua();
                va.c = uaVar;
                uaVar.start();
                handler = va.d;
            }
        }
        return accountManager.removeAccount(account, aVar, handler);
    }

    public boolean a(Account account) {
        if (account == null) {
            return false;
        }
        for (Account account2 : a(account.type)) {
            if (account.equals(account2)) {
                return true;
            }
        }
        return false;
    }
}
