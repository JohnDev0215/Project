package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.u7;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class c8 extends u7 {
    public static final String d = "com.amazon.identity.auth.device.c8";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f264a;
    public final u7 b;
    public f c;

    public c8(Context context) {
        t5 t5VarA = t5.a(context);
        this.f264a = t5VarA;
        this.b = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        return this.b.a();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> b() {
        return this.b.b();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> c(String str) {
        return this.b.c(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d() {
        this.b.d();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e() {
        this.b.e();
    }

    @Override // com.amazon.identity.auth.device.u7
    public void f() {
        this.b.f();
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar) {
        return this.b.a(str, o7Var, aVar);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String b(String str, String str2) {
        return this.b.b(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c(String str, String str2) {
        String strA = a(str, str2, true);
        String str3 = d;
        String.format("Token key: %s. ContextualKey: %s", str2, strA);
        ga.a(str3);
        if (strA != null) {
            return this.b.c(str, strA);
        }
        Log.w(ga.a(str3), "getToken failed because key does not make sense on the platform");
        return null;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String d(String str, String str2) {
        String strA = a(str, str2, true);
        if (strA != null) {
            return this.b.d(str, strA);
        }
        Log.w(ga.a(d), "getUserData failed because key does not make sense on the platform");
        return null;
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e(String str) {
        this.b.e(str);
    }

    public final boolean f(String str, String str2, String str3) {
        f fVar;
        y5 y5Var = new y5("RegisterChildApplicationFromDBLayer");
        Bundle bundle = new Bundle();
        bundle.putString("override_dsn", str3);
        synchronized (this) {
            if (this.c == null) {
                this.c = e.b(this.f264a);
            }
            fVar = this.c;
        }
        try {
            try {
                try {
                    return fVar.a(str, str2, bundle, (Callback) null, y5Var).get() != null;
                } catch (MAPCallbackErrorException e) {
                    Log.e(ga.a(d), "Error registeringChildAccount. Bundle Error: " + e.c(e.getErrorBundle()), e);
                    return false;
                } catch (ExecutionException e2) {
                    Log.e(ga.a(d), "Execution exception while registeringChildAccount", e2);
                    return false;
                }
            } catch (InterruptedException e3) {
                Log.e(ga.a(d), "Interrupted exception while registeringChildAccount", e3);
                return false;
            }
        } finally {
            y5Var.e.a();
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        String str = o7Var.f476a;
        this.b.a(new o7(o7Var.f476a, c(str, o7Var.b), c(str, o7Var.c), null));
    }

    @Override // com.amazon.identity.auth.device.u7
    public void b(String str, String str2, String str3) {
        this.b.b(str, str2, str3);
    }

    public String e(String str, String str2, String str3) {
        String strA = a(str, str2, str3, true);
        String str4 = d;
        String.format("Token key: %s, ActorContextualKey: %s", str3, strA);
        ga.a(str4);
        return this.b.c(str, strA);
    }

    public void b(String str, String str2, Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map2.put(e.a(this.f264a, str2, fa.a(entry.getKey()).c), entry.getValue());
        }
        this.b.b(str, map2);
    }

    public c8(Context context, u7 u7Var) {
        this.f264a = t5.a(context);
        this.b = u7Var;
    }

    @Override // com.amazon.identity.auth.device.u7
    @Deprecated
    public void c(String str, String str2, String str3) {
        String strA = a(str, str2, true);
        if (strA == null) {
            Log.w(ga.a(d), "setToken failed because key does not make sense on the platform");
        } else {
            this.b.c(str, strA, str3);
        }
    }

    public String e(String str, String str2) throws BackwardsCompatiableDataStorage.BackwardsCompatibleDataStorageException {
        String strA = a(str, str2, true);
        String str3 = d;
        String.format("Token key: %s. ContextualKey: %s", str2, strA);
        ga.a(str3);
        if (strA == null) {
            Log.w(ga.a(str3), "getToken failed because key does not make sense on the platform");
            return null;
        }
        u7 u7Var = this.b;
        if (u7Var instanceof BackwardsCompatiableDataStorage) {
            BackwardsCompatiableDataStorage backwardsCompatiableDataStorage = (BackwardsCompatiableDataStorage) u7Var;
            if (backwardsCompatiableDataStorage.f(strA)) {
                return backwardsCompatiableDataStorage.e(str, strA);
            }
            return backwardsCompatiableDataStorage.f548a.c(str, strA);
        }
        return u7Var.c(str, strA);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d(String str, String str2, String str3) {
        String strA = a(str, str2, true);
        if (strA == null) {
            Log.w(ga.a(d), "setUserData failed because key does not make sense on the platform");
        } else {
            this.b.d(str, strA, str3);
        }
    }

    public void a(String str, String str2, String str3, String str4) {
        this.b.d(str, "actor_data/" + str2 + str3, str4);
    }

    public final Map<String, String> c(String str, Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strA = a(str, entry.getKey(), true);
            if (strA == null) {
                Log.w(ga.a(d), "Not setting " + entry.getKey() + " because the child device type could not be registered.");
            } else {
                map2.put(strA, entry.getValue());
            }
        }
        return map2;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> d(String str) {
        return this.b.d(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, String str2) {
        String strA = a(str, str2, true);
        if (strA == null) {
            Log.w(ga.a(d), "expireToken failed because key does not make sense on the platform");
        } else {
            this.b.a(str, strA);
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        return this.b.b(str);
    }

    public void a(String str, String str2, String str3, Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            map2.put(e.a(this.f264a, str3, fa.a(entry.getKey()).c) + "/" + str2, entry.getValue());
        }
        this.b.b(str, map2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        return this.b.c();
    }

    public String f(String str, String str2) {
        String strA = a(str, str2, false);
        if (strA == null) {
            Log.w(ga.a(d), "peekToken failed because key does not make sense on the platform");
            return null;
        }
        return this.b.c(str, strA);
    }

    public void a(String str, String str2, Map<String, String> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String strA = a(str, str2, entry.getKey(), false);
            if (strA == null) {
                Log.w(ga.a(d), "Not setting actor key " + entry.getKey());
            } else {
                map2.put(strA, entry.getValue());
            }
        }
        this.b.b(str, map2);
    }

    public final String a(String str, String str2, boolean z) {
        if (z && va.a()) {
            throw new IllegalStateException("Cannot get local data on the main thread");
        }
        fa faVarA = fa.a(str2);
        String str3 = faVarA.b;
        if (str3 == null) {
            return faVarA.c;
        }
        j5 j5VarA = MAPApplicationInformationQueryer.a(this.f264a).a(str3);
        if (j5VarA == null) {
            return faVarA.c;
        }
        try {
            String strC = j5VarA.c();
            String strD = j5VarA.d();
            if (z9.a(this.f264a, strC)) {
                ga.a(d);
                return faVarA.c;
            }
            if (z) {
                boolean zA = f0.a(this.f264a, this.b, str, strC);
                String str4 = d;
                String.format("Child application device type %s has already been registered", strC);
                ga.a(str4);
                if (!zA) {
                    if (f(str, strC, strD)) {
                        return e.a(this.f264a, strC, faVarA.c);
                    }
                    Log.e(ga.a(str4), String.format("Could not register application with the device type %s", strC));
                    return null;
                }
            }
            ga.a(d);
            return e.a(this.f264a, strC, faVarA.c);
        } catch (RemoteMAPException unused) {
            Log.w(ga.a(d), "Couldn't determine override device type/DSN for the package ");
            return null;
        }
    }

    public final String a(String str, String str2, String str3, boolean z) {
        String strA = a(str, str3, z);
        if (strA == null) {
            Log.w(ga.a(d), "getActorToken failed because key does not make sense on the platform");
            return null;
        }
        return strA + "/" + str2;
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        return this.b.a(str, o7Var, aVar, list);
    }

    public final void a(m8 m8Var, String str, Map<String, String> map) {
        if (m8Var != null) {
            String strC = this.b.c(str, m8Var.b);
            if (TextUtils.isEmpty(strC)) {
                return;
            }
            String str2 = m8Var.f432a;
            if (str2 == null) {
                map.put(z9.b(this.f264a), strC);
            } else {
                map.put(str2, strC);
            }
        }
    }
}
