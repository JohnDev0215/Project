package p;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class mv {
    public final String a;
    public final Set b;
    public final Set c;
    public final int d;
    public final int e;
    public final aw f;
    public final Set g;

    public mv(String str, Set set, Set set2, int i, int i2, aw awVar, Set set3) {
        this.a = str;
        this.b = Collections.unmodifiableSet(set);
        this.c = Collections.unmodifiableSet(set2);
        this.d = i;
        this.e = i2;
        this.f = awVar;
        this.g = Collections.unmodifiableSet(set3);
    }

    public static mv a(Object obj, Class cls, Class... clsArr) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        hashSet.add(dz1.a(cls));
        for (Class cls2 : clsArr) {
            if (cls2 == null) {
                throw new NullPointerException("Null interface");
            }
            hashSet.add(dz1.a(cls2));
        }
        return new mv(null, new HashSet(hashSet), new HashSet(hashSet2), 0, 0, new kv(0, obj), hashSet3);
    }

    public final String toString() {
        return "Component<" + Arrays.toString(this.b.toArray()) + ">{" + this.d + ", type=" + this.e + ", deps=" + Arrays.toString(this.c.toArray()) + "}";
    }
}
