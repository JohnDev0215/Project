package b.a.a.n;

/* JADX INFO: compiled from: IdentityHashMap.java */
/* JADX INFO: loaded from: classes.dex */
public class f<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a<K, V>[] f442a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f443b;

    /* JADX INFO: compiled from: IdentityHashMap.java */
    public static final class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f444a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final K f445b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public V f446c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a<K, V> f447d;

        public a(K k, V v, int i, a<K, V> aVar) {
            this.f445b = k;
            this.f446c = v;
            this.f447d = aVar;
            this.f444a = i;
        }
    }

    public f() {
        this(8192);
    }

    public Class a(String str) {
        int i = 0;
        while (true) {
            a<K, V>[] aVarArr = this.f442a;
            if (i >= aVarArr.length) {
                return null;
            }
            a<K, V> aVar = aVarArr[i];
            if (aVar != null) {
                for (a<K, V> aVar2 = aVar; aVar2 != null; aVar2 = aVar2.f447d) {
                    K k = aVar.f445b;
                    if (k instanceof Class) {
                        Class cls = (Class) k;
                        if (cls.getName().equals(str)) {
                            return cls;
                        }
                    }
                }
            }
            i++;
        }
    }

    public final V b(K k) {
        for (a<K, V> aVar = this.f442a[System.identityHashCode(k) & this.f443b]; aVar != null; aVar = aVar.f447d) {
            if (k == aVar.f445b) {
                return aVar.f446c;
            }
        }
        return null;
    }

    public boolean c(K k, V v) {
        int iIdentityHashCode = System.identityHashCode(k);
        int i = this.f443b & iIdentityHashCode;
        for (a<K, V> aVar = this.f442a[i]; aVar != null; aVar = aVar.f447d) {
            if (k == aVar.f445b) {
                aVar.f446c = v;
                return true;
            }
        }
        this.f442a[i] = new a<>(k, v, iIdentityHashCode, this.f442a[i]);
        return false;
    }

    public f(int i) {
        this.f443b = i - 1;
        this.f442a = new a[i];
    }
}
