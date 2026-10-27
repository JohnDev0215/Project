package com.amazon.identity.auth.device;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.os.Bundle;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z8 implements AccountManagerCallback<Bundle> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AccountManagerCallback f703a;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ AccountManagerFuture f704a;

        public a(AccountManagerFuture accountManagerFuture) {
            this.f704a = accountManagerFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            z8.this.f703a.run(this.f704a);
        }
    }

    public z8(y8 y8Var, AccountManagerCallback accountManagerCallback) {
        this.f703a = accountManagerCallback;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
        y8.h.execute(new a(accountManagerFuture));
    }
}
