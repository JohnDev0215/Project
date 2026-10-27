package l2;

/* JADX INFO: compiled from: IdentityHashMap.java */
/* JADX INFO: loaded from: classes.dex */
public class f<K, V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a<K, V>[] f6209a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f6210b;

    /* JADX INFO: compiled from: IdentityHashMap.java */
    public static final class a<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f6211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final K f6212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public V f6213c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a<K, V> f6214d;

        public a(K k8, V v7, int i8, a<K, V> aVar) {
            this.f6212b = k8;
            this.f6213c = v7;
            this.f6214d = aVar;
            this.f6211a = i8;
        }
    }

    public f() {
        this(8192);
    }

    public Class a(String str) {
        int i8 = 0;
        while (true) {
            a<K, V>[] aVarArr = this.f6209a;
            if (i8 >= aVarArr.length) {
                return null;
            }
            a<K, V> aVar = aVarArr[i8];
            if (aVar != null) {
                for (a<K, V> aVar2 = aVar; aVar2 != null; aVar2 = aVar2.f6214d) {
                    K k8 = aVar.f6212b;
                    if (k8 instanceof Class) {
                        Class cls = (Class) k8;
                        if (cls.getName().equals(str)) {
                            return cls;
                        }
                    }
                }
            }
            i8++;
        }
    }

    public final V b(K k8) {
        for (a<K, V> aVar = this.f6209a[System.identityHashCode(k8) & this.f6210b]; aVar != null; aVar = aVar.f6214d) {
            if (k8 == aVar.f6212b) {
                return aVar.f6213c;
            }
        }
        return null;
    }

    public boolean c(K k8, V v7) {
        int iIdentityHashCode = System.identityHashCode(k8);
        int i8 = this.f6210b & iIdentityHashCode;
        for (a<K, V> aVar = this.f6209a[i8]; aVar != null; aVar = aVar.f6214d) {
            if (k8 == aVar.f6212b) {
                aVar.f6213c = v7;
                return true;
            }
        }
        this.f6209a[i8] = new a<>(k8, v7, iIdentityHashCode, this.f6209a[i8]);
        return false;
    }

    public f(int i8) {
        this.f6210b = i8 - 1;
        this.f6209a = new a[i8];
    }
}
