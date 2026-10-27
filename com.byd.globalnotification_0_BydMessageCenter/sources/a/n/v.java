package a.n;

import a.q.a;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f1112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.b f1113b;

    public class a implements a.b {
        public a() {
        }

        @Override // a.q.a.b
        public Bundle a() {
            Set<String> setKeySet = v.this.f1112a.keySet();
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(setKeySet.size());
            ArrayList<? extends Parcelable> arrayList2 = new ArrayList<>(arrayList.size());
            for (String str : setKeySet) {
                arrayList.add(str);
                arrayList2.add(v.this.f1112a.get(str));
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("keys", arrayList);
            bundle.putParcelableArrayList("values", arrayList2);
            return bundle;
        }
    }

    static {
        Class cls = Double.TYPE;
        Class cls2 = Integer.TYPE;
        Class cls3 = Long.TYPE;
        Class cls4 = Byte.TYPE;
        Class cls5 = Character.TYPE;
        Class cls6 = Float.TYPE;
        Class cls7 = Short.TYPE;
    }

    public v() {
        new HashMap();
        this.f1113b = new a();
        this.f1112a = new HashMap();
    }

    public v(Map<String, Object> map) {
        new HashMap();
        this.f1113b = new a();
        this.f1112a = new HashMap(map);
    }

    public static v a(Bundle bundle, Bundle bundle2) {
        if (bundle == null && bundle2 == null) {
            return new v();
        }
        HashMap map = new HashMap();
        if (bundle2 != null) {
            for (String str : bundle2.keySet()) {
                map.put(str, bundle2.get(str));
            }
        }
        if (bundle == null) {
            return new v(map);
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            map.put((String) parcelableArrayList.get(i), parcelableArrayList2.get(i));
        }
        return new v(map);
    }
}
