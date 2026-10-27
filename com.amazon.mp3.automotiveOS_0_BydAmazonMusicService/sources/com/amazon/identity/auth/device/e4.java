package com.amazon.identity.auth.device;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPError;
import java.io.IOException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class e4<T> implements AccountManagerCallback<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Callback f295a;

    public e4(Callback callback) {
        this.f295a = callback;
    }

    public abstract void a(Callback callback, T t);

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture<T> accountManagerFuture) {
        String message;
        MAPError.CommonError commonError;
        MAPError.CommonError commonError2 = MAPError.CommonError.NETWORK_ERROR;
        int i = 5;
        try {
            a(this.f295a, accountManagerFuture.getResult());
        } catch (AuthenticatorException e) {
            message = e.getMessage();
            commonError = MAPError.CommonError.INVALID_RESPONSE;
            y.a(this.f295a, commonError, message, i, message);
        } catch (OperationCanceledException e2) {
            message = e2.getMessage();
            i = 4;
            commonError = MAPError.CommonError.OPERATION_CANCELLED;
            y.a(this.f295a, commonError, message, i, message);
        } catch (IOException e3) {
            message = e3.getMessage();
            yd.b("NetworkError12:AccountManagerCallbackAdapter", new String[0]);
            i = 3;
            commonError = MAPError.CommonError.NETWORK_ERROR;
            y.a(this.f295a, commonError, message, i, message);
        } catch (IllegalArgumentException e4) {
            message = e4.getMessage();
            commonError = MAPError.CommonError.INVALID_RESPONSE;
            y.a(this.f295a, commonError, message, i, message);
        }
    }
}
