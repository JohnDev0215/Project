package androidx.lifecycle;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: SavedStateHandle.java */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Class[] f2574e = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Map<String, Object> f2575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Map<String, SavedStateRegistry.b> f2576b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, b<?>> f2577c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SavedStateRegistry.b f2578d;

    /* JADX INFO: compiled from: SavedStateHandle.java */
    class a implements SavedStateRegistry.b {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.b
        public Bundle a() {
            for (Map.Entry entry : new HashMap(y.this.f2576b).entrySet()) {
                y.this.f((String) entry.getKey(), ((SavedStateRegistry.b) entry.getValue()).a());
            }
            Set<String> setKeySet = y.this.f2575a.keySet();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(setKeySet.size());
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(arrayList.size());
            for (String str : setKeySet) {
                arrayList.add(str);
                arrayList2.add(y.this.f2575a.get(str));
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("keys", arrayList);
            bundle.putParcelableArrayList("values", arrayList2);
            return bundle;
        }
    }

    public y(Map<String, Object> map) {
        this.f2576b = new HashMap();
        this.f2577c = new HashMap();
        this.f2578d = new a();
        this.f2575a = new HashMap(map);
    }

    static y a(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return new y();
        }
        HashMap map = new HashMap();
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                map.put(str, bundle2.get(str));
            }
        }
        if (bundle == null) {
            return new y(map);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        for (int i7 = 0; i7 < parcelableArrayList.size(); i7++) {
            map.put((String) parcelableArrayList.get(i7), parcelableArrayList2.get(i7));
        }
        return new y(map);
    }

    private <T> t<T> d(String str, boolean z6, T t6) {
        b<?> bVar = this.f2577c.get(str);
        if (bVar != null) {
            return bVar;
        }
        b<?> bVar2 = this.f2575a.containsKey(str) ? new b<>(this, str, this.f2575a.get(str)) : z6 ? new b<>(this, str, t6) : new b<>(this, str);
        this.f2577c.put(str, bVar2);
        return bVar2;
    }

    private static void g(Object obj) {
        if (obj == null) {
            return;
        }
        for (Class cls : f2574e) {
            if (cls.isInstance(obj)) {
                return;
            }
        }
        throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
    }

    public <T> t<T> b(String str) {
        return d(str, false, null);
    }

    public <T> t<T> c(String str, @SuppressLint({"UnknownNullness"}) T t6) {
        return d(str, true, t6);
    }

    SavedStateRegistry.b e() {
        return this.f2578d;
    }

    public <T> void f(String str, T t6) {
        g(t6);
        b<?> bVar = this.f2577c.get(str);
        if (bVar != null) {
            bVar.m(t6);
        } else {
            this.f2575a.put(str, t6);
        }
    }

    /* JADX INFO: compiled from: SavedStateHandle.java */
    static class b<T> extends t<T> {

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private String f2580l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private y f2581m;

        b(y yVar, String str, T t6) {
            super(t6);
            this.f2580l = str;
            this.f2581m = yVar;
        }

        @Override // androidx.lifecycle.t, androidx.lifecycle.LiveData
        public void m(T t6) {
            y yVar = this.f2581m;
            if (yVar != null) {
                yVar.f2575a.put(this.f2580l, t6);
            }
            super.m(t6);
        }

        b(y yVar, String str) {
            this.f2580l = str;
            this.f2581m = yVar;
        }
    }

    public y() {
        this.f2576b = new HashMap();
        this.f2577c = new HashMap();
        this.f2578d = new a();
        this.f2575a = new HashMap();
    }
}
