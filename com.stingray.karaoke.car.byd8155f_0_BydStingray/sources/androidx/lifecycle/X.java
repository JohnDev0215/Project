package androidx.lifecycle;

import F0.C0233v0;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class X {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class[] f11958f = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final LinkedHashMap f11959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f11960b;
    public final LinkedHashMap c;
    public final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t3.d f11961e;

    public X(HashMap map) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.f11959a = linkedHashMap;
        this.f11960b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.f11961e = new C0233v0(this, 1);
        linkedHashMap.putAll(map);
    }

    public static Bundle a(X x8) {
        A7.m.f(x8, "this$0");
        for (Map.Entry entry : n7.z.y(x8.f11960b).entrySet()) {
            x8.b(((t3.d) entry.getValue()).a(), (String) entry.getKey());
        }
        LinkedHashMap linkedHashMap = x8.f11959a;
        Set<String> setKeySet = linkedHashMap.keySet();
        ArrayList arrayList = new ArrayList(setKeySet.size());
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (String str : setKeySet) {
            arrayList.add(str);
            arrayList2.add(linkedHashMap.get(str));
        }
        return w0.c.F(new m7.i("keys", arrayList), new m7.i("values", arrayList2));
    }

    public final void b(Object obj, String str) {
        A7.m.f(str, "key");
        if (obj != null) {
            Class[] clsArr = f11958f;
            for (int i7 = 0; i7 < 29; i7++) {
                Class cls = clsArr[i7];
                A7.m.c(cls);
                if (!cls.isInstance(obj)) {
                }
            }
            throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
        }
        Object obj2 = this.c.get(str);
        J j3 = obj2 instanceof J ? (J) obj2 : null;
        if (j3 != null) {
            j3.i(obj);
        } else {
            this.f11959a.put(str, obj);
        }
        V8.L l = (V8.L) this.d.get(str);
        if (l == null) {
            return;
        }
        ((V8.f0) l).l(obj);
    }

    public X() {
        this.f11959a = new LinkedHashMap();
        this.f11960b = new LinkedHashMap();
        this.c = new LinkedHashMap();
        this.d = new LinkedHashMap();
        this.f11961e = new C0233v0(this, 1);
    }
}
