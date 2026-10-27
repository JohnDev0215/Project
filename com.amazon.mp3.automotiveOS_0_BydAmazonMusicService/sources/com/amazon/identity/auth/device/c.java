package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c {
    public static final String b = "com.amazon.identity.auth.device.c";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a6 f252a = new a6(Executors.newSingleThreadExecutor(va.a("MAP-AccountAuthenticatorQueueThread")));

    /* JADX INFO: compiled from: DCP */
    public static final class a extends r2 {
        public static final long g = wa.c(5, TimeUnit.SECONDS);
        public final Callback d;
        public final b e;
        public final String f;

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class C0054a implements Callback {
            public C0054a() {
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                ga.c(c.b, "onError Popping task %s off AccountAuthenticatorQueue.", a.this.f);
                a.this.asyncOperationComplete();
                a.this.d.onError(bundle);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                ga.c(c.b, "onSuccess Popping task %s off AccountAuthenticatorQueue.", a.this.f);
                a.this.asyncOperationComplete();
                a.this.d.onSuccess(bundle);
            }
        }

        public a(b bVar, Callback callback, String str) {
            this.d = callback;
            this.e = bVar;
            this.f = str;
        }

        @Override // com.amazon.identity.auth.device.r2
        public void onTimeout() {
            throw new RuntimeException("Possible AccountManager Deadlock Detected!");
        }

        @Override // com.amazon.identity.auth.device.r2, java.lang.Runnable
        public synchronized void run() {
            super.run(Long.valueOf(g), TimeUnit.SECONDS, this.f);
        }

        @Override // com.amazon.identity.auth.device.r2
        public void startAsyncOperation() {
            C0054a c0054a = new C0054a();
            ga.c(c.b, "Pushing task %s on AccountAuthenticatorQueue.", this.f);
            Bundle bundleA = this.e.a(c0054a);
            if (bundleA != null) {
                c0054a.onSuccess(bundleA);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public interface b {
        Bundle a(Callback callback);
    }
}
