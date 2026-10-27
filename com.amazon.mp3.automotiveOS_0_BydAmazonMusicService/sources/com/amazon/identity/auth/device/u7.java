package com.amazon.identity.auth.device;

import android.accounts.Account;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class u7 {

    /* JADX INFO: compiled from: DCP */
    public interface a {
    }

    public abstract Set<String> a();

    public abstract void a(o7 o7Var);

    public abstract void a(String str, String str2);

    public boolean a(String str) {
        return b().contains(str);
    }

    public abstract boolean a(String str, o7 o7Var, a aVar);

    public abstract boolean a(String str, o7 o7Var, a aVar, List<String> list);

    public abstract Account b(String str);

    public abstract String b(String str, String str2);

    public abstract Set<String> b();

    public abstract void b(String str, String str2, String str3);

    public void b(String str, Map<String, String> map) {
        a(new o7(str, null, map, null));
    }

    public abstract String c();

    public abstract String c(String str, String str2);

    public abstract Set<String> c(String str);

    @Deprecated
    public abstract void c(String str, String str2, String str3);

    public abstract String d(String str, String str2);

    public abstract Set<String> d(String str);

    public abstract void d();

    public abstract void d(String str, String str2, String str3);

    public abstract void e();

    public abstract void e(String str);

    public abstract void f();

    public void a(String str, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            b(str, entry.getKey(), entry.getValue());
        }
    }

    public Map<String, String> a(String str, List<String> list) {
        HashMap map = new HashMap();
        for (String str2 : list) {
            map.put(str2, b(str, str2));
        }
        return map;
    }

    public String a(String str, String str2, String str3) {
        return d(str, "actor_data/" + str2 + str3);
    }
}
