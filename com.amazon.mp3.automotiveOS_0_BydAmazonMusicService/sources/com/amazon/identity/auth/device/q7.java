package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.u7;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.y8;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q7 extends u7 {
    public static final String g = "com.amazon.identity.auth.device.q7";
    public static q7 h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f511a = new Object[0];
    public final Context b;
    public final i5 c;
    public final n8 d;
    public final p9 e;
    public volatile ConcurrentHashMap<String, a> f;

    /* JADX INFO: compiled from: DCP */
    public static class a implements pa<a> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f512a;
        public final Account b;
        public final Map<String, c6<String>> c;
        public final Map<String, c6<String>> d;
        public final Object[] e = new Object[0];
        public y8 f;

        public a(String str, Account account, Map<String, c6<String>> map, Map<String, c6<String>> map2) {
            this.f512a = str;
            this.b = account;
            this.c = map;
            this.d = map2;
        }

        @Override // com.amazon.identity.auth.device.pa
        public pa a() {
            return new a(this.f512a, this.b, aa.a((Map) this.c), aa.a((Map) this.d));
        }

        public y8 a(n8 n8Var) {
            y8 y8Var;
            synchronized (this.e) {
                if (this.f == null) {
                    this.f = n8Var.b(this.b);
                }
                y8Var = this.f;
            }
            return y8Var;
        }
    }

    public q7(Context context) {
        t5 t5VarA = t5.a(context);
        this.b = t5VarA;
        this.c = (i5) t5VarA.getSystemService("sso_platform");
        this.d = (n8) t5VarA.getSystemService("dcp_token_cache_holder");
        this.e = (p9) t5VarA.getSystemService("dcp_account_manager");
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar) {
        String str2 = o7Var.f476a;
        Bundle bundle = new Bundle();
        for (Map.Entry<String, String> entry : o7Var.b.entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        Map<String, String> map = o7Var.c;
        synchronized (this.f511a) {
            if (a(str2)) {
                return false;
            }
            Account account = new Account(str, AccountConstants.AMAZON_ACCOUNT_TYPE);
            bundle.putString("com.amazon.dcp.sso.property.account.acctId", str2);
            boolean zA = this.e.a(account, null, bundle, true);
            i();
            if (zA && map != null) {
                b(str2, map);
            }
            if (zA && aVar != null) {
                ((n) aVar).a();
            }
            return zA;
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> b() {
        return ((HashMap) g()).keySet();
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> c(String str) {
        Log.e(ga.a(g), "Actor is not supported in this platform!");
        throw new UnsupportedOperationException("Actor is not supported in this platform.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d(String str, String str2, String str3) {
        synchronized (this.f511a) {
            a aVarC = c(str, h());
            if (aVarC == null) {
                Log.w(ga.a(g), "Cannot set the requested user data for the given directed ID because it is not registered on the device");
            } else {
                aVarC.c.remove(str2);
                this.e.a(aVarC.b, str2, str3);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e(String str) {
        synchronized (this.f511a) {
            a aVarC = c(str, g());
            Account account = aVarC == null ? null : aVarC.b;
            if (account == null) {
                Log.w(ga.a(g), "Cannot remove the requested user because it is not registered on the device");
                return;
            }
            this.f.remove(str);
            AccountManagerFuture<Boolean> accountManagerFutureA = this.e.a(account, (AccountManagerCallback<Boolean>) null, true);
            boolean zBooleanValue = false;
            try {
                try {
                    try {
                        zBooleanValue = accountManagerFutureA.getResult().booleanValue();
                    } catch (AuthenticatorException e) {
                        Log.e(ga.a(g), "Could not locally removed account because their was an Authenticator Exception. Error: " + e.getMessage());
                    } catch (OperationCanceledException e2) {
                        Log.e(ga.a(g), "Could not locally removed account because the operation was canceled. Error: " + e2.getMessage());
                    }
                } catch (IOException e3) {
                    Log.e(ga.a(g), "Could not locally removed account because their was an IO Exception. Error: " + e3.getMessage());
                }
                if (!zBooleanValue) {
                    Log.e(ga.a(g), "Locally removing the account from the central store was not succesful");
                }
            } finally {
                i();
            }
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void f() {
    }

    public final Map<String, a> g() {
        Map<String, a> mapA;
        ConcurrentHashMap<String, a> concurrentHashMap = this.f;
        if (concurrentHashMap != null && t9.f(this.c.f382a)) {
            return aa.a((Map) concurrentHashMap);
        }
        synchronized (this.f511a) {
            mapA = aa.a((Map) h());
        }
        return mapA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<String, a> h() {
        if (!(this.f != null && t9.f(this.c.f382a))) {
            p9 p9Var = this.e;
            Account[] accountArrA = p9Var.a(AccountConstants.AMAZON_ACCOUNT_TYPE);
            HashMap map = new HashMap();
            for (Account account : accountArrA) {
                String strB = p9Var.b(account, "com.amazon.dcp.sso.property.account.acctId");
                if (strB == null) {
                    strB = UUID.randomUUID().toString();
                    p9Var.a(account, "com.amazon.dcp.sso.property.account.acctId", strB);
                }
                map.put(strB, account);
            }
            ConcurrentHashMap<String, a> concurrentHashMap = new ConcurrentHashMap<>();
            for (Map.Entry entry : map.entrySet()) {
                concurrentHashMap.put(entry.getKey(), new a((String) entry.getKey(), (Account) entry.getValue(), new ConcurrentHashMap(), new ConcurrentHashMap()));
            }
            this.f = concurrentHashMap;
        }
        return this.f;
    }

    public final void i() {
        synchronized (this.f511a) {
            this.f = null;
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public String b(String str, String str2) {
        if (t9.f(this.c.f382a)) {
            return new f8(this.b, str, 0).f313a.getString(str2, null);
        }
        throw new UnsupportedOperationException("getDeviceData should only be called via central apk");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void c(String str, String str2, String str3) {
        synchronized (this.f511a) {
            a aVarC = c(str, h());
            if (aVarC == null) {
                Log.e(ga.a(g), "Cannot set the requested token for the given directed ID because we couldn't construct a TokenCache");
                return;
            }
            y8 y8VarA = aVarC.a(this.d);
            aVarC.d.remove(str2);
            y8VarA.a(str2, str3);
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void b(String str, String str2, String str3) {
        if (t9.f(this.c.f382a)) {
            new f8(this.b, str, 0).a(str2, str3);
            return;
        }
        throw new UnsupportedOperationException("setDeviceData should only be called via central apk");
    }

    @Override // com.amazon.identity.auth.device.u7
    public String d(String str, String str2) {
        a aVarC = c(str, g());
        if (aVarC == null) {
            Log.w(ga.a(g), "Cannot get the requested user data for the given directed ID because it is not registered on the device");
            return null;
        }
        c6<String> c6Var = aVarC.c.get(str2);
        if (c6Var != null) {
            return c6Var.f261a;
        }
        synchronized (this.f511a) {
            a aVarC2 = c(str, h());
            if (aVarC2 == null) {
                Log.w(ga.a(g), "Cannot get the requested user data for the given directed ID because it is not registered on the device");
                return null;
            }
            c6<String> c6Var2 = aVarC2.c.get(str2);
            if (c6Var2 != null) {
                return c6Var2.f261a;
            }
            String strB = this.e.b(aVarC2.b, str2);
            aVarC2.c.put(str2, new c6<>(strB));
            return strB;
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        a aVarC = c(str, g());
        if (aVarC == null) {
            return null;
        }
        return aVarC.b;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c(String str, String str2) {
        a aVarC = c(str, g());
        if (aVarC == null) {
            Log.e(ga.a(g), "Cannot get the requested token for the given directed ID because we couldn't construct a TokenCache");
            return null;
        }
        c6<String> c6Var = aVarC.d.get(str2);
        if (c6Var != null) {
            return c6Var.f261a;
        }
        synchronized (this.f511a) {
            a aVarC2 = c(str, h());
            if (aVarC2 == null) {
                Log.e(ga.a(g), "Cannot get the requested token for the given directed ID because we couldn't construct a TokenCache");
                return null;
            }
            c6<String> c6Var2 = aVarC2.d.get(str2);
            if (c6Var2 != null) {
                return c6Var2.f261a;
            }
            y8 y8VarA = aVarC2.a(this.d);
            y8.c cVarB = y8VarA.b(str2, y8VarA.d(str2));
            String str3 = cVarB != null ? cVarB.b : null;
            aVarC2.d.put(str2, new c6<>(str3));
            return str3;
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        for (Map.Entry<String, String> entry : o7Var.b.entrySet()) {
            d(o7Var.f476a, entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry2 : o7Var.c.entrySet()) {
            c(o7Var.f476a, entry2.getKey(), entry2.getValue());
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> d(String str) {
        synchronized (this.f511a) {
            a aVarC = c(str, h());
            if (aVarC == null) {
                Log.e(ga.a(g), "Cannot get all token keys for the directedId because we couldn't get the account info");
                return new HashSet();
            }
            return aVarC.d.keySet();
        }
    }

    public static synchronized q7 a(Context context) {
        if (h == null || xa.a()) {
            h = new q7(context.getApplicationContext());
        }
        return h;
    }

    public final a c(String str, Map<String, a> map) {
        if (str == null) {
            Log.e(ga.a(g), "Cannot find account for null directedId");
            return null;
        }
        a aVar = map.get(str);
        if (aVar == null) {
            ga.a(g, str, map.keySet());
        }
        return aVar;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        HashSet hashSet = new HashSet();
        Iterator it = ((HashMap) g()).values().iterator();
        while (it.hasNext()) {
            hashSet.add(((a) it.next()).b.name);
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, String str2) {
        synchronized (this.f511a) {
            a aVarC = c(str, h());
            if (aVarC == null) {
                Log.e(ga.a(g), "Cannot expire the requested token for the given directed ID because we couldn't construct a TokenCache");
                return;
            }
            y8 y8VarA = aVarC.a(this.d);
            aVarC.d.remove(str2);
            Log.i(ga.a(y8.g), y8VarA.f + ": invalidateAuthTokenByType: " + str2);
            y8VarA.a(y8VarA.c, y8VarA.d(str2));
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        Log.i(ga.a(g), "getDeviceSnapshot API is only supported on 3P devices.");
        return "";
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        return ((HashMap) g()).containsKey(str);
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        Log.e(ga.a(g), "Replace accounts not supported");
        return false;
    }
}
