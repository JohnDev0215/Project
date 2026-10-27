package b.n;

import android.os.Binder;
import android.os.Build;
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
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class[] f2957a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map<String, Object> f2958b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, SavedStateRegistry.b> f2959c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Map<String, ?> f2960d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final SavedStateRegistry.b f2961e;

    /* JADX INFO: compiled from: SavedStateHandle.java */
    public class a implements SavedStateRegistry.b {
        public a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.b
        public Bundle a() {
            for (Map.Entry entry : new HashMap(o.this.f2959c).entrySet()) {
                o.this.c((String) entry.getKey(), ((SavedStateRegistry.b) entry.getValue()).a());
            }
            Set<String> setKeySet = o.this.f2958b.keySet();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(setKeySet.size());
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(arrayList.size());
            for (String str : setKeySet) {
                arrayList.add(str);
                arrayList2.add(o.this.f2958b.get(str));
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("keys", arrayList);
            bundle.putParcelableArrayList("values", arrayList2);
            return bundle;
        }
    }

    static {
        Class[] clsArr = new Class[29];
        clsArr[0] = Boolean.TYPE;
        clsArr[1] = boolean[].class;
        clsArr[2] = Double.TYPE;
        clsArr[3] = double[].class;
        Class<SizeF> cls = Integer.TYPE;
        clsArr[4] = cls;
        clsArr[5] = int[].class;
        clsArr[6] = Long.TYPE;
        clsArr[7] = long[].class;
        clsArr[8] = String.class;
        clsArr[9] = String[].class;
        clsArr[10] = Binder.class;
        clsArr[11] = Bundle.class;
        clsArr[12] = Byte.TYPE;
        clsArr[13] = byte[].class;
        clsArr[14] = Character.TYPE;
        clsArr[15] = char[].class;
        clsArr[16] = CharSequence.class;
        clsArr[17] = CharSequence[].class;
        clsArr[18] = ArrayList.class;
        clsArr[19] = Float.TYPE;
        clsArr[20] = float[].class;
        clsArr[21] = Parcelable.class;
        clsArr[22] = Parcelable[].class;
        clsArr[23] = Serializable.class;
        clsArr[24] = Short.TYPE;
        clsArr[25] = short[].class;
        clsArr[26] = SparseArray.class;
        int i2 = Build.VERSION.SDK_INT;
        clsArr[27] = i2 >= 21 ? Size.class : cls;
        if (i2 >= 21) {
            cls = SizeF.class;
        }
        clsArr[28] = cls;
        f2957a = clsArr;
    }

    public o(Map<String, Object> map) {
        this.f2959c = new HashMap();
        this.f2960d = new HashMap();
        this.f2961e = new a();
        this.f2958b = new HashMap(map);
    }

    public static o a(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return new o();
        }
        HashMap map = new HashMap();
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                map.put(str, bundle2.get(str));
            }
        }
        if (bundle == null) {
            return new o(map);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        for (int i2 = 0; i2 < parcelableArrayList.size(); i2++) {
            map.put((String) parcelableArrayList.get(i2), parcelableArrayList2.get(i2));
        }
        return new o(map);
    }

    public static void d(Object obj) {
        if (obj == null) {
            return;
        }
        for (Class cls : f2957a) {
            if (cls.isInstance(obj)) {
                return;
            }
        }
        throw new IllegalArgumentException("Can't put value with type " + obj.getClass() + " into saved state");
    }

    public SavedStateRegistry.b b() {
        return this.f2961e;
    }

    public <T> void c(String str, T t) {
        d(t);
        l lVar = (l) this.f2960d.get(str);
        if (lVar != null) {
            lVar.j(t);
        } else {
            this.f2958b.put(str, t);
        }
    }

    public o() {
        this.f2959c = new HashMap();
        this.f2960d = new HashMap();
        this.f2961e = new a();
        this.f2958b = new HashMap();
    }
}
