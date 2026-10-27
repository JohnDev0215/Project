package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class u5 extends r2 {
    public static final Executor e;
    public final r4 d;

    /* JADX INFO: compiled from: DCP */
    public class a extends r4 {

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.u5$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class RunnableC0061a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ ComponentName f605a;
            public final /* synthetic */ IBinder b;

            public RunnableC0061a(ComponentName componentName, IBinder iBinder) {
                this.f605a = componentName;
                this.b = iBinder;
            }

            @Override // java.lang.Runnable
            public void run() {
                u5.this.useService(this.f605a, this.b);
            }
        }

        public a(Context context, Intent intent, int i) {
            super(context, intent, i);
        }

        @Override // com.amazon.identity.auth.device.r4
        public void useService(ComponentName componentName, IBinder iBinder) throws RemoteException {
            u5.e.execute(new RunnableC0061a(componentName, iBinder));
        }

        @Override // com.amazon.identity.auth.device.r4
        public void useService(IBinder iBinder) throws RemoteException {
        }
    }

    static {
        Executor executor = va.f637a;
        e = Executors.newFixedThreadPool(4, new ka("MAP-SyncBoundServiceCallerThreadPool"));
    }

    public u5(Context context, Intent intent, int i) {
        this.d = new a(context, intent, i);
    }

    public void doneUsingService() {
        this.d.unbind();
        asyncOperationComplete();
    }

    @Override // com.amazon.identity.auth.device.r2
    public void startAsyncOperation() {
        if (this.d.call()) {
            return;
        }
        doneUsingService();
    }

    public void useService(ComponentName componentName, IBinder iBinder) {
        useService(iBinder);
    }

    public abstract void useService(IBinder iBinder);
}
