package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.u7;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class r7 extends u7 {
    public static final String b = "com.amazon.identity.auth.device.r7";
    public static r7 c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e8 f529a;

    public r7(Context context, e8 e8Var) {
        ga.c(b, "Constructing CentralLocalDataStorage");
        t5.a(context);
        this.f529a = e8Var;
    }

    public static synchronized r7 a(Context context) {
        e8 e8Var;
        if (c == null || xa.a()) {
            t5 t5VarA = t5.a(context.getApplicationContext());
            synchronized (e8.class) {
                if (e8.h == null) {
                    e8.h = new e8(t5VarA.getApplicationContext(), "map_data_storage_v2", 2);
                }
                e8Var = e8.h;
            }
            c = new r7(t5VarA, e8Var);
        }
        return c;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Account b(String str) {
        return null;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> b() {
        Set<String> setUnmodifiableSet;
        synchronized (this) {
        }
        e8 e8Var = this.f529a;
        synchronized (e8Var) {
            Map<String, n7> mapC = e8Var.c();
            HashSet hashSet = new HashSet();
            Iterator<Map.Entry<String, n7>> it = mapC.entrySet().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().getKey());
            }
            setUnmodifiableSet = Collections.unmodifiableSet(hashSet);
        }
        return setUnmodifiableSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> c(String str) {
        HashSet hashSet;
        n7 n7Var;
        synchronized (this) {
        }
        e8 e8Var = this.f529a;
        synchronized (e8Var) {
            hashSet = new HashSet();
            if (str != null && (n7Var = e8Var.c().get(str)) != null) {
                for (Map.Entry<String, String> entry : n7Var.b.entrySet()) {
                    if (entry.getKey().startsWith("actor/")) {
                        hashSet.add(entry.getValue());
                    }
                }
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String d(String str, String str2) {
        synchronized (this) {
        }
        return this.f529a.a(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public synchronized void d() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void e(String str) {
        synchronized (this) {
        }
        if (this.f529a.b(str)) {
            return;
        }
        Log.e(ga.a(b), "Removing the account was not successful.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void f() {
    }

    @Override // com.amazon.identity.auth.device.u7
    public void d(String str, String str2, String str3) {
        synchronized (this) {
        }
        if (this.f529a.a(str, str2, str3)) {
            return;
        }
        Log.e(ga.a(b), "Setting the userdata was not successful.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> d(String str) {
        n7 n7Var;
        e8 e8Var = this.f529a;
        e8Var.getClass();
        HashSet hashSet = new HashSet();
        if (!TextUtils.isEmpty(str) && (n7Var = e8Var.c().get(str)) != null) {
            for (String str2 : n7Var.b.keySet()) {
                if (str2.contains("token") || str2.contains("cookie")) {
                    hashSet.add(str2);
                }
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String b(String str, String str2) {
        String str3;
        synchronized (this) {
        }
        e8 e8Var = this.f529a;
        synchronized (e8Var) {
            if (str != null && str2 != null) {
                Map<String, String> map = e8Var.d().get(str);
                str3 = map == null ? null : map.get(str2);
            }
        }
        return str3;
    }

    @Override // com.amazon.identity.auth.device.u7
    public Set<String> a() {
        HashSet hashSet;
        synchronized (this) {
        }
        e8 e8Var = this.f529a;
        synchronized (e8Var) {
            Map<String, n7> mapC = e8Var.c();
            hashSet = new HashSet();
            Iterator<n7> it = mapC.values().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().f459a);
            }
        }
        return hashSet;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c(String str, String str2) {
        synchronized (this) {
        }
        return this.f529a.a(str, str2);
    }

    @Override // com.amazon.identity.auth.device.u7
    public void c(String str, String str2, String str3) {
        synchronized (this) {
        }
        if (this.f529a.a(str, str2, str3)) {
            return;
        }
        Log.e(ga.a(b), "Setting the token was not successful.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public void b(String str, String str2, String str3) {
        synchronized (this) {
        }
        if (this.f529a.b(str, str2, str3)) {
            return;
        }
        Log.e(ga.a(b), "Setting device token was not successful. ");
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar) {
        synchronized (this) {
        }
        HashMap map = new HashMap();
        map.putAll(o7Var.b);
        map.putAll(o7Var.c);
        boolean zA = this.f529a.a(str, o7Var.f476a, map);
        if (zA && aVar != null) {
            ((n) aVar).a();
        }
        return zA;
    }

    @Override // com.amazon.identity.auth.device.u7
    public String c() {
        Log.i(ga.a(b), "getDeviceSnapshot API is only supported on 3P devices.");
        return "";
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, String str2) {
        synchronized (this) {
        }
        if (this.f529a.b(str, str2)) {
            return;
        }
        Log.e(ga.a(b), "Expiring the token was not successful. ");
    }

    @Override // com.amazon.identity.auth.device.u7
    public Map<String, String> a(String str, List<String> list) {
        HashMap map;
        Map<String, String> map2;
        synchronized (this) {
        }
        e8 e8Var = this.f529a;
        synchronized (e8Var) {
            map = new HashMap();
            if (list != null && !list.isEmpty() && (map2 = e8Var.d().get(str)) != null) {
                for (String str2 : list) {
                    map.put(str2, map2.get(str2));
                }
            }
        }
        return map;
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(String str, Map<String, String> map) {
        synchronized (this) {
        }
        e8 e8Var = this.f529a;
        synchronized (e8Var) {
            boolean z = false;
            if (!TextUtils.isEmpty(str) && !map.isEmpty()) {
                try {
                    try {
                        SQLiteDatabase sQLiteDatabaseA = d8.a(e8Var.b);
                        sQLiteDatabaseA.beginTransaction();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            if (!e8Var.c(sQLiteDatabaseA, str, entry.getKey(), entry.getValue())) {
                                throw new SQLiteException("Failed to update db!");
                            }
                        }
                        sQLiteDatabaseA.setTransactionSuccessful();
                        for (Map.Entry<String, String> entry2 : map.entrySet()) {
                            e8Var.c(str, entry2.getKey(), entry2.getValue());
                        }
                        e8Var.a(sQLiteDatabaseA);
                        z = true;
                    } catch (SQLiteException e) {
                        Log.e(ga.a(e8.g), "Cannot set device data!", e);
                    }
                } finally {
                    e8Var.a((SQLiteDatabase) null);
                }
            }
            if (z) {
                return;
            }
            Log.e(ga.a(b), "Setting device tokens was not successful. ");
        }
    }

    @Override // com.amazon.identity.auth.device.u7
    public void a(o7 o7Var) {
        synchronized (this) {
        }
        HashMap map = new HashMap();
        map.putAll(o7Var.b);
        map.putAll(o7Var.c);
        if (this.f529a.a(o7Var.f476a, map)) {
            return;
        }
        Log.e(ga.a(b), "Setting the data was not successful.");
    }

    @Override // com.amazon.identity.auth.device.u7
    public boolean a(String str, o7 o7Var, u7.a aVar, List<String> list) {
        synchronized (this) {
        }
        HashMap map = new HashMap();
        map.putAll(o7Var.b);
        map.putAll(o7Var.c);
        boolean zA = this.f529a.a(str, o7Var.f476a, map, list);
        if (zA && aVar != null) {
            ((n) aVar).a();
        }
        return zA;
    }
}
