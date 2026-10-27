package com.alibaba.fastjson.asm;

import com.bydux.api.common.tools.util.ListUtils;
import d2.d;
import e2.g;
import e2.j;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import l2.b;

/* JADX INFO: loaded from: classes.dex */
public class TypeCollector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2597a = b.b(d.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map<String, String> f2598b = new HashMap<String, String>() { // from class: com.alibaba.fastjson.asm.TypeCollector.1
        {
            put("int", "I");
            put("boolean", "Z");
            put("byte", "B");
            put("char", "C");
            put("short", "S");
            put("float", "F");
            put("long", "J");
            put("double", "D");
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Class<?>[] f2600d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f2601e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2602f;

    public TypeCollector(String str, Class<?>[] clsArr) {
        this.f2599c = str;
        this.f2600d = clsArr;
    }

    public final boolean a(j jVar, String str) {
        String strC = jVar.c();
        StringBuilder sb = new StringBuilder();
        while (strC.endsWith("[]")) {
            sb.append('[');
            strC = strC.substring(0, strC.length() - 2);
        }
        if (sb.length() != 0) {
            Map<String, String> map = f2598b;
            if (map.containsKey(strC)) {
                sb.append(map.get(strC));
                strC = sb.toString();
            } else {
                sb.append('L');
                sb.append(strC);
                sb.append(';');
                strC = sb.toString();
            }
        }
        return strC.equals(str);
    }

    public String[] b() {
        g gVar = this.f2601e;
        return (gVar == null || !gVar.f4562e) ? new String[0] : gVar.a().split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public boolean c() {
        return this.f2602f;
    }

    public void d(String str) {
        if (f2597a.equals(str)) {
            this.f2602f = true;
        }
    }

    public g e(int i8, String str, String str2) {
        if (this.f2601e != null || !str.equals(this.f2599c)) {
            return null;
        }
        j[] jVarArrA = j.a(str2);
        int i9 = 0;
        for (j jVar : jVarArrA) {
            String strC = jVar.c();
            if (strC.equals("long") || strC.equals("double")) {
                i9++;
            }
        }
        if (jVarArrA.length != this.f2600d.length) {
            return null;
        }
        for (int i10 = 0; i10 < jVarArrA.length; i10++) {
            if (!a(jVarArrA[i10], this.f2600d[i10].getName())) {
                return null;
            }
        }
        g gVar = new g(!Modifier.isStatic(i8) ? 1 : 0, jVarArrA.length + i9);
        this.f2601e = gVar;
        return gVar;
    }
}
