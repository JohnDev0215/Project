package com.amazon.identity.auth.device;

import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.util.SparseIntArray;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.callback.IGenericIPC;
import com.amazon.identity.auth.device.framework.IPCCommand;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.s4;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z4 {
    public static final Uri h = Uri.parse("content://com.amazon.map.generic_ipc");
    public static final Uri i = Uri.parse("content://com.amazon.map.generic_ipc.directboot");
    public static final String[] j = {"bundle_value"};
    public static final String k = z4.class.getName();
    public static s4<IGenericIPC> l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s5 f697a;
    public final s4<IGenericIPC> b;
    public final i5 c;
    public final String d;
    public final String e;
    public final SparseIntArray f;
    public final Context g;

    /* JADX INFO: compiled from: DCP */
    public class a implements v4<Bundle> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Uri f698a;
        public final /* synthetic */ String b;

        public a(z4 z4Var, Uri uri, String str) {
            this.f698a = uri;
            this.b = str;
        }

        @Override // com.amazon.identity.auth.device.v4
        public Bundle a(ContentProviderClient contentProviderClient) throws Exception {
            Cursor cursorQuery = contentProviderClient.query(this.f698a, z4.j, this.b, null, null);
            try {
                if (cursorQuery == null) {
                    throw new RuntimeException("Got a null cursor calling Generic IPC central store.");
                }
                if (!cursorQuery.moveToFirst()) {
                    throw new RuntimeException("Got an empty cursor calling Generic IPC central store.");
                }
                int columnIndex = cursorQuery.getColumnIndex("bundle_value");
                Bundle bundleB = ma.b(columnIndex == -1 ? null : cursorQuery.getString(columnIndex));
                if (bundleB == null) {
                    throw new RuntimeException("Corrupted value returned.");
                }
                cursorQuery.close();
                return bundleB;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class b extends s4<IGenericIPC> {
        public b(Context context, String str, Executor executor) {
            super(context, str, executor);
        }

        @Override // com.amazon.identity.auth.device.s4
        public IGenericIPC a(IBinder iBinder) {
            return IGenericIPC.Stub.asInterface(iBinder);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public final class c implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Callback f699a;
        public String b;

        public c(Callback callback) {
            this.f699a = callback;
        }

        public final synchronized boolean a(String str) {
            if (this.b == null) {
                this.b = str;
                return true;
            }
            Log.w(ga.a(z4.k), "Duplicate callback detected: " + str + " called after " + this.b);
            return false;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            Callback callback;
            if (a("onError") && (callback = this.f699a) != null) {
                callback.onError(z4.this.a(bundle));
            }
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            Callback callback;
            if (a("onSuccess") && (callback = this.f699a) != null) {
                callback.onSuccess(bundle);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class e implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final CountDownLatch f700a = new CountDownLatch(1);
        public Bundle b;

        public Bundle a(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
            if (this.f700a.await(j, timeUnit)) {
                return this.b;
            }
            throw new TimeoutException();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            this.b = bundle;
            this.f700a.countDown();
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            this.b = bundle;
            this.f700a.countDown();
        }
    }

    public z4(Context context, String str, String str2, Integer num) {
        this(context, str, str2, num, new s5(context), a(context), new i5(context));
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00f8 A[Catch: all -> 0x0107, TryCatch #2 {, blocks: (B:4:0x0026, B:29:0x00aa, B:39:0x00f8, B:40:0x00fc, B:23:0x0089, B:36:0x00f4, B:47:0x0105, B:48:0x0106, B:5:0x0027, B:7:0x002d, B:8:0x0039, B:10:0x003d, B:11:0x0048, B:14:0x004d, B:25:0x008c, B:26:0x0094, B:28:0x009e, B:32:0x00ae, B:35:0x00d1, B:21:0x0086, B:44:0x0102, B:45:0x0103, B:12:0x0049, B:16:0x004f, B:18:0x005d, B:20:0x0084, B:19:0x007a), top: B:55:0x0026, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fc A[Catch: all -> 0x0107, TRY_LEAVE, TryCatch #2 {, blocks: (B:4:0x0026, B:29:0x00aa, B:39:0x00f8, B:40:0x00fc, B:23:0x0089, B:36:0x00f4, B:47:0x0105, B:48:0x0106, B:5:0x0027, B:7:0x002d, B:8:0x0039, B:10:0x003d, B:11:0x0048, B:14:0x004d, B:25:0x008c, B:26:0x0094, B:28:0x009e, B:32:0x00ae, B:35:0x00d1, B:21:0x0086, B:44:0x0102, B:45:0x0103, B:12:0x0049, B:16:0x004f, B:18:0x005d, B:20:0x0084, B:19:0x007a), top: B:55:0x0026, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.Class<? extends com.amazon.identity.auth.device.framework.IPCCommand> r5, android.os.Bundle r6, com.amazon.identity.auth.device.api.Callback r7) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.z4.a(java.lang.Class, android.os.Bundle, com.amazon.identity.auth.device.api.Callback):void");
    }

    /* JADX INFO: compiled from: DCP */
    public static final class d extends s4.b<IGenericIPC> implements Callback {
        public final Callback b;
        public final Bundle c;
        public final Class<? extends IPCCommand> d;
        public boolean e;

        public d(Callback callback, Bundle bundle, Class<? extends IPCCommand> cls, s4<IGenericIPC> s4Var) {
            super(s4Var);
            this.b = callback;
            this.c = bundle;
            this.d = cls;
            this.e = false;
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.amazon.identity.auth.device.z4$d for r3v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // com.amazon.identity.auth.device.s4.b
        public void a(com.amazon.identity.auth.device.callback.IGenericIPC r4) throws android.os.RemoteException {
            /*
                r3 = this;
                com.amazon.identity.auth.device.callback.IGenericIPC r4 = (com.amazon.identity.auth.device.callback.IGenericIPC) r4
                com.amazon.identity.auth.device.callback.IRemoteCallback r0 = com.amazon.identity.auth.device.callback.RemoteCallbackWrapper.toRemoteCallback(r3)
                com.amazon.identity.auth.device.s4<T> r1 = r3.f540a
                monitor-enter(r1)
                java.util.List<com.amazon.identity.auth.device.s4$b<T>> r2 = r1.e     // Catch: java.lang.Throwable -> L1b
                r2.add(r3)     // Catch: java.lang.Throwable -> L1b
                monitor-exit(r1)
                java.lang.Class<? extends com.amazon.identity.auth.device.framework.IPCCommand> r1 = r3.d
                java.lang.String r1 = r1.getName()
                android.os.Bundle r2 = r3.c
                r4.call(r1, r2, r0)
                return
            L1b:
                r4 = move-exception
                monitor-exit(r1)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.z4.d.a(java.lang.Object):void");
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            synchronized (this) {
                if (this.e) {
                    return;
                }
                this.e = true;
                s4<T> s4Var = this.f540a;
                synchronized (s4Var) {
                    s4Var.e.remove(this);
                }
                this.b.onError(bundle);
            }
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            synchronized (this) {
                if (this.e) {
                    return;
                }
                this.e = true;
                s4<T> s4Var = this.f540a;
                synchronized (s4Var) {
                    s4Var.e.remove(this);
                }
                this.b.onSuccess(bundle);
            }
        }

        @Override // com.amazon.identity.auth.device.s4.b
        public void a() {
            Bundle bundle = new Bundle();
            bundle.putInt("ipc_error_code_key", 500);
            bundle.putString("ipc_error_code_message", "Got an error while calling Generic IPC central store.");
            onError(bundle);
        }
    }

    public z4(Context context, String str, String str2, Integer num, s5 s5Var, s4<IGenericIPC> s4Var, i5 i5Var) {
        this.f697a = s5Var;
        this.b = s4Var;
        this.d = str;
        this.e = str2;
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.f = sparseIntArray;
        if (num != null) {
            sparseIntArray.put(500, num.intValue());
        }
        this.c = i5Var;
        this.g = context;
    }

    public Bundle a(Class<? extends IPCCommand> cls, Bundle bundle) {
        boolean z;
        Bundle bundleA;
        com.amazon.identity.auth.device.e.a(cls, "ipcCommandClass");
        de deVarB = yd.b("GenericIPCSender", cls.getSimpleName());
        try {
            e eVar = new e();
            s4<IGenericIPC> s4Var = this.b;
            d dVar = new d(eVar, bundle, cls, s4Var);
            synchronized (s4Var) {
                if (s4Var.g == null) {
                    z = false;
                } else {
                    s4Var.a(dVar);
                    z = true;
                }
            }
            Bundle bundleA2 = null;
            if (z) {
                try {
                    bundleA2 = eVar.a(3000L, TimeUnit.MILLISECONDS);
                    if (this.c.e()) {
                        Log.i(ga.a(k), String.format("%s try get ipc service in direct mode for %s", this.g.getPackageName(), cls.getSimpleName()));
                    } else {
                        String str = k;
                        String.format("%s try get ipc service out of direct mode for %s", this.g.getPackageName(), cls.getSimpleName());
                        ga.a(str);
                    }
                } catch (InterruptedException e2) {
                    Log.e(ga.a(k), String.format("MAP api call: %s get InterruptedException, probably due to caller is canceling the call.", cls.getSimpleName()), e2);
                    Thread.currentThread().interrupt();
                } catch (TimeoutException e3) {
                    Log.e(ga.a(k), "TimeoutException while waiting for " + cls.getSimpleName() + "; will retry over content provider", e3);
                }
            }
            if (bundleA2 == null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("command", cls.getName());
                bundle2.putBundle("parameters", bundle);
                if (this.c.e()) {
                    Log.i(ga.a(k), String.format("%s try get ipc provider in direct mode for %s", this.g.getPackageName(), cls.getSimpleName()));
                    bundleA = a(bundle2, i);
                } else {
                    String str2 = k;
                    String.format("%s try get ipc provider out of direct mode for %s", this.g.getPackageName(), cls.getSimpleName());
                    ga.a(str2);
                    bundleA = a(bundle2, h);
                }
                bundleA2 = bundleA;
            }
            Bundle bundleA3 = a(bundleA2);
            if (bundleA3.containsKey(this.e)) {
                Log.e(ga.a(k), cls.getSimpleName() + " returned error " + bundleA3.getInt(this.d, -1) + " : " + bundleA3.getString(this.e));
            }
            return bundleA3;
        } finally {
            deVarB.c();
        }
    }

    public final Bundle a(Bundle bundle, Uri uri) {
        try {
            return (Bundle) this.f697a.a(uri, new a(this, uri, ma.a(bundle)));
        } catch (RemoteMAPException e2) {
            Log.e(ga.a(k), "Got an error while calling Generic IPC central store. This can happen in certain edge cases around a crash.", e2);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("ipc_error_code_key", 500);
            bundle2.putString("ipc_error_code_message", "Got an error while calling Generic IPC central store.");
            return bundle2;
        }
    }

    public final Bundle a(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (this.d != null && bundle.containsKey("ipc_error_code_key")) {
            int i2 = bundle.getInt("ipc_error_code_key");
            bundle.putInt(this.d, this.f.get(i2, i2));
            bundle.remove("ipc_error_code_key");
            bundle.putString(this.e, bundle.getString("ipc_error_code_message"));
            bundle.remove("ipc_error_code_message");
        }
        return bundle;
    }

    public static synchronized s4<IGenericIPC> a(Context context) {
        s4<IGenericIPC> s4Var = l;
        if (s4Var != null) {
            return s4Var;
        }
        b bVar = new b(context, "com.amazon.identity.framework.GenericIPCService", va.f637a);
        if (!xa.a()) {
            l = bVar;
        }
        return bVar;
    }
}
