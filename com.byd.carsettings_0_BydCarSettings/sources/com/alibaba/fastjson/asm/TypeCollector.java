package com.alibaba.fastjson.asm;

import androidx.exifinterface.media.ExifInterface;
import b.a.a.h.d;
import b.a.a.i.g;
import b.a.a.i.j;
import b.a.a.n.b;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class TypeCollector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2416a = b.b(d.class);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map<String, String> f2417b = new HashMap<String, String>() { // from class: com.alibaba.fastjson.asm.TypeCollector.1
        {
            put("int", "I");
            put("boolean", "Z");
            put("byte", "B");
            put("char", "C");
            put("short", ExifInterface.LATITUDE_SOUTH);
            put("float", "F");
            put("long", "J");
            put("double", "D");
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f2418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Class<?>[] f2419d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public g f2420e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2421f;

    public TypeCollector(String str, Class<?>[] clsArr) {
        this.f2418c = str;
        this.f2419d = clsArr;
    }

    public final boolean a(j jVar, String str) {
        String strC = jVar.c();
        StringBuilder sb = new StringBuilder();
        while (strC.endsWith("[]")) {
            sb.append('[');
            strC = strC.substring(0, strC.length() - 2);
        }
        if (sb.length() != 0) {
            Map<String, String> map = f2417b;
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
        g gVar = this.f2420e;
        return (gVar == null || !gVar.f216e) ? new String[0] : gVar.a().split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public boolean c() {
        return this.f2421f;
    }

    public void d(String str) {
        if (f2416a.equals(str)) {
            this.f2421f = true;
        }
    }

    public g e(int i, String str, String str2) {
        if (this.f2420e != null || !str.equals(this.f2418c)) {
            return null;
        }
        j[] jVarArrA = j.a(str2);
        int i2 = 0;
        for (j jVar : jVarArrA) {
            String strC = jVar.c();
            if (strC.equals("long") || strC.equals("double")) {
                i2++;
            }
        }
        if (jVarArrA.length != this.f2419d.length) {
            return null;
        }
        for (int i3 = 0; i3 < jVarArrA.length; i3++) {
            if (!a(jVarArrA[i3], this.f2419d[i3].getName())) {
                return null;
            }
        }
        g gVar = new g(!Modifier.isStatic(i) ? 1 : 0, jVarArrA.length + i2);
        this.f2420e = gVar;
        return gVar;
    }
}
