package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.DefaultCallback;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class s2 implements MAPFuture<Bundle>, Callback {
    public static final String e = "com.amazon.identity.auth.device.s2";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CountDownLatch f537a;
    public Callback b;
    public int c;
    public Bundle d;

    public s2() {
        this(null);
    }

    public static s2 a(Callback callback) {
        return callback instanceof s2 ? (s2) callback : new s2(callback);
    }

    public final synchronized Bundle b() throws MAPCallbackErrorException {
        int i = this.c;
        if (i != 1) {
            if (i != 2) {
                throw new IllegalStateException();
            }
            throw new MAPCallbackErrorException(this.d);
        }
        return this.d;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onError(Bundle bundle) {
        a(2, bundle);
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    public void onSuccess(Bundle bundle) {
        a(1, bundle);
    }

    public s2(Callback callback) {
        this.b = DefaultCallback.nullToDefault(callback);
        this.f537a = new CountDownLatch(1);
        this.c = 0;
    }

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    public Bundle get(long j, TimeUnit timeUnit) throws ExecutionException, MAPCallbackErrorException, InterruptedException, TimeoutException {
        if (va.a()) {
            throw new IllegalStateException("Cannot call get on the main thread, unless you want ANRs");
        }
        if (this.f537a.await(j, timeUnit)) {
            return b();
        }
        Log.e(ga.a(e), "Timed out waiting for result!");
        throw new TimeoutException("Timed out waiting for result!");
    }

    public final void a(int i, Bundle bundle) {
        synchronized (this) {
            if (this.c != 0) {
                String str = e;
                Log.w(ga.a(str), "Callback was called twice", new IllegalStateException());
                return;
            }
            this.d = bundle;
            this.c = i;
            Callback callback = this.b;
            this.b = null;
            this.f537a.countDown();
            if (callback == null) {
                return;
            }
            if (i == 1) {
                callback.onSuccess(bundle);
            } else {
                if (i == 2) {
                    callback.onError(bundle);
                    return;
                }
                throw new IllegalStateException();
            }
        }
    }

    @Override // com.amazon.identity.auth.device.api.MAPFuture
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Bundle get() throws ExecutionException, MAPCallbackErrorException, InterruptedException {
        if (!va.a()) {
            this.f537a.await();
            return b();
        }
        throw new IllegalStateException("Cannot call get on the main thread, unless you want ANRs");
    }
}
