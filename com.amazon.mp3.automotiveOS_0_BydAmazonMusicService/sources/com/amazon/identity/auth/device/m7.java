package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m7 implements pa<m7> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f431a;
    public final String b;
    public final Map<String, x7<String>> c;
    public final Map<String, x7<String>> d;

    public m7(String str, String str2, Map<String, x7<String>> map) {
        this(str, str2, map, new HashMap());
    }

    @Override // com.amazon.identity.auth.device.pa
    public pa a() {
        return new m7(this.f431a, this.b, aa.a((Map) this.c), aa.a((Map) this.d));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m7.class != obj.getClass()) {
            return false;
        }
        m7 m7Var = (m7) obj;
        return TextUtils.equals(this.f431a, m7Var.f431a) && TextUtils.equals(this.b, m7Var.b) && aa.a(this.c, m7Var.c) && aa.a(this.d, m7Var.d);
    }

    public int hashCode() {
        String str = this.f431a;
        int iHashCode = ((str == null ? 0 : str.hashCode()) + 31) * 31;
        String str2 = this.b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Map<String, x7<String>> map = this.d;
        int iHashCode3 = (iHashCode2 + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, x7<String>> map2 = this.c;
        return iHashCode3 + (map2 != null ? map2.hashCode() : 0);
    }

    public String toString() {
        return String.format("DirectedId: %s, Display Name: %s, userdata: %s, tokens: %s", this.f431a, this.b, this.c.toString(), this.d.toString());
    }

    public m7(String str, String str2, Map<String, x7<String>> map, Map<String, x7<String>> map2) {
        this.f431a = str;
        this.b = str2;
        this.c = map;
        this.d = map2;
    }
}
