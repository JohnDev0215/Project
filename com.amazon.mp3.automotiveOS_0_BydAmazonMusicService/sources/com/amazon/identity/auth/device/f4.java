package com.amazon.identity.auth.device;

import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class f4<T> implements MAPFuture<Bundle> {
    public static final String b = "com.amazon.identity.auth.device.f4";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AccountManagerFuture<T> f308a;

    public f4(AccountManagerFuture<T> accountManagerFuture) {
        this.f308a = accountManagerFuture;
    }

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bundle get() throws ExecutionException, MAPCallbackErrorException, InterruptedException {
        try {
            return a(this.f308a.getResult());
        } catch (AuthenticatorException e) {
            Log.w(ga.a(b), "AccountManager request failed because of AuthenticatorException: " + e.getMessage());
            throw new ExecutionException(e);
        } catch (OperationCanceledException e2) {
            Log.w(ga.a(b), "AccountManager request failed because of OperationCanceledException: " + e2.getMessage());
            throw new ExecutionException(e2);
        } catch (IOException e3) {
            Log.w(ga.a(b), "AccountManager request failed because of IOException: " + e3.getMessage());
            throw new ExecutionException(e3);
        }
    }

    public abstract Bundle a(T t) throws MAPCallbackErrorException;

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bundle get(long j, TimeUnit timeUnit) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        try {
            return a(this.f308a.getResult(j, timeUnit));
        } catch (AuthenticatorException e) {
            Log.w(ga.a(b), "AccountManager request failed because of AuthenticatorException: " + e.getMessage());
            throw new ExecutionException(e);
        } catch (OperationCanceledException e2) {
            Log.w(ga.a(b), "AccountManager request failed because of OperationCanceledException: " + e2.getMessage());
            throw new ExecutionException(e2);
        } catch (IOException e3) {
            Log.w(ga.a(b), "AccountManager request failed because of IOException: " + e3.getMessage());
            throw new ExecutionException(e3);
        }
    }
}
