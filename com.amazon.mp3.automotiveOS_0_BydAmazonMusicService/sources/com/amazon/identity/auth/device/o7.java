package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class o7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f476a;
    public final Map<String, String> b;
    public final Map<String, String> c;
    public final u7 d;

    public o7(String str, Map<String, String> map, Map<String, String> map2, u7 u7Var) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("AccountData directedId cannot be null");
        }
        this.f476a = str;
        if (map == null) {
            this.b = new HashMap();
        } else {
            this.b = map;
        }
        if (map2 == null) {
            this.c = new HashMap();
        } else {
            this.c = map2;
        }
        this.d = u7Var;
    }

    public String a(String str) {
        u7 u7Var;
        String str2 = this.c.get(str);
        return (str2 != null || (u7Var = this.d) == null) ? str2 : u7Var.c(this.f476a, str);
    }

    public String b(String str) {
        u7 u7Var;
        String str2 = this.b.get(str);
        return (str2 != null || (u7Var = this.d) == null) ? str2 : u7Var.d(this.f476a, str);
    }
}
