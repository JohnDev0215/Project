package i5;

import h5.k;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: MapBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d<K, V> implements Map<K, V>, Serializable, s5.a {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private static final a f7017r = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private K[] f7018e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private V[] f7019g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int[] f7020h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int[] f7021i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private int f7022j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f7023k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f7024l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f7025m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private i5.f<K> f7026n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private g<V> f7027o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private i5.e<K, V> f7028p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f7029q;

    /* JADX INFO: compiled from: MapBuilder.kt */
    private static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int c(int i7) {
            return Integer.highestOneBit(v5.h.b(i7, 1) * 3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int d(int i7) {
            return Integer.numberOfLeadingZeros(i7) + 1;
        }
    }

    /* JADX INFO: compiled from: MapBuilder.kt */
    public static final class b<K, V> extends C0088d<K, V> implements Iterator<Map.Entry<K, V>>, s5.a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(d<K, V> map) {
            super(map);
            l.e(map, "map");
        }

        @Override // java.util.Iterator
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public c<K, V> next() {
            if (a() >= ((d) c()).f7023k) {
                throw new NoSuchElementException();
            }
            int iA = a();
            e(iA + 1);
            f(iA);
            c<K, V> cVar = new c<>(c(), b());
            d();
            return cVar;
        }

        public final void h(StringBuilder sb) {
            l.e(sb, "sb");
            if (a() >= ((d) c()).f7023k) {
                throw new NoSuchElementException();
            }
            int iA = a();
            e(iA + 1);
            f(iA);
            Object obj = ((d) c()).f7018e[b()];
            if (l.a(obj, c())) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = ((d) c()).f7019g;
            l.c(objArr);
            Object obj2 = objArr[b()];
            if (l.a(obj2, c())) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            d();
        }

        public final int i() {
            if (a() >= ((d) c()).f7023k) {
                throw new NoSuchElementException();
            }
            int iA = a();
            e(iA + 1);
            f(iA);
            Object obj = ((d) c()).f7018e[b()];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = ((d) c()).f7019g;
            l.c(objArr);
            Object obj2 = objArr[b()];
            int iHashCode2 = iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
            d();
            return iHashCode2;
        }
    }

    /* JADX INFO: compiled from: MapBuilder.kt */
    public static final class c<K, V> implements Map.Entry<K, V>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final d<K, V> f7030e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final int f7031g;

        public c(d<K, V> map, int i7) {
            l.e(map, "map");
            this.f7030e = map;
            this.f7031g = i7;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (l.a(entry.getKey(), getKey()) && l.a(entry.getValue(), getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return (K) ((d) this.f7030e).f7018e[this.f7031g];
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            Object[] objArr = ((d) this.f7030e).f7019g;
            l.c(objArr);
            return (V) objArr[this.f7031g];
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K key = getKey();
            int iHashCode = key != null ? key.hashCode() : 0;
            V value = getValue();
            return iHashCode ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public V setValue(V v6) {
            this.f7030e.l();
            Object[] objArrJ = this.f7030e.j();
            int i7 = this.f7031g;
            V v7 = (V) objArrJ[i7];
            objArrJ[i7] = v6;
            return v7;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    /* JADX INFO: renamed from: i5.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MapBuilder.kt */
    public static class C0088d<K, V> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final d<K, V> f7032e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f7033g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f7034h;

        public C0088d(d<K, V> map) {
            l.e(map, "map");
            this.f7032e = map;
            this.f7034h = -1;
            d();
        }

        public final int a() {
            return this.f7033g;
        }

        public final int b() {
            return this.f7034h;
        }

        public final d<K, V> c() {
            return this.f7032e;
        }

        public final void d() {
            while (this.f7033g < ((d) this.f7032e).f7023k) {
                int[] iArr = ((d) this.f7032e).f7020h;
                int i7 = this.f7033g;
                if (iArr[i7] >= 0) {
                    return;
                } else {
                    this.f7033g = i7 + 1;
                }
            }
        }

        public final void e(int i7) {
            this.f7033g = i7;
        }

        public final void f(int i7) {
            this.f7034h = i7;
        }

        public final boolean hasNext() {
            return this.f7033g < ((d) this.f7032e).f7023k;
        }

        public final void remove() {
            if (!(this.f7034h != -1)) {
                throw new IllegalStateException("Call next() before removing element from the iterator.".toString());
            }
            this.f7032e.l();
            this.f7032e.L(this.f7034h);
            this.f7034h = -1;
        }
    }

    /* JADX INFO: compiled from: MapBuilder.kt */
    public static final class e<K, V> extends C0088d<K, V> implements Iterator<K>, s5.a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public e(d<K, V> map) {
            super(map);
            l.e(map, "map");
        }

        @Override // java.util.Iterator
        public K next() {
            if (a() >= ((d) c()).f7023k) {
                throw new NoSuchElementException();
            }
            int iA = a();
            e(iA + 1);
            f(iA);
            K k7 = (K) ((d) c()).f7018e[b()];
            d();
            return k7;
        }
    }

    /* JADX INFO: compiled from: MapBuilder.kt */
    public static final class f<K, V> extends C0088d<K, V> implements Iterator<V>, s5.a {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(d<K, V> map) {
            super(map);
            l.e(map, "map");
        }

        @Override // java.util.Iterator
        public V next() {
            if (a() >= ((d) c()).f7023k) {
                throw new NoSuchElementException();
            }
            int iA = a();
            e(iA + 1);
            f(iA);
            Object[] objArr = ((d) c()).f7019g;
            l.c(objArr);
            V v6 = (V) objArr[b()];
            d();
            return v6;
        }
    }

    private d(K[] kArr, V[] vArr, int[] iArr, int[] iArr2, int i7, int i8) {
        this.f7018e = kArr;
        this.f7019g = vArr;
        this.f7020h = iArr;
        this.f7021i = iArr2;
        this.f7022j = i7;
        this.f7023k = i8;
        this.f7024l = f7017r.d(x());
    }

    private final int B(K k7) {
        return ((k7 != null ? k7.hashCode() : 0) * (-1640531527)) >>> this.f7024l;
    }

    private final boolean E(Collection<? extends Map.Entry<? extends K, ? extends V>> collection) {
        boolean z6 = false;
        if (collection.isEmpty()) {
            return false;
        }
        r(collection.size());
        Iterator<? extends Map.Entry<? extends K, ? extends V>> it = collection.iterator();
        while (it.hasNext()) {
            if (F(it.next())) {
                z6 = true;
            }
        }
        return z6;
    }

    private final boolean F(Map.Entry<? extends K, ? extends V> entry) {
        int i7 = i(entry.getKey());
        V[] vArrJ = j();
        if (i7 >= 0) {
            vArrJ[i7] = entry.getValue();
            return true;
        }
        int i8 = (-i7) - 1;
        if (l.a(entry.getValue(), vArrJ[i8])) {
            return false;
        }
        vArrJ[i8] = entry.getValue();
        return true;
    }

    private final boolean G(int i7) {
        int iB = B(this.f7018e[i7]);
        int i8 = this.f7022j;
        while (true) {
            int[] iArr = this.f7021i;
            if (iArr[iB] == 0) {
                iArr[iB] = i7 + 1;
                this.f7020h[i7] = iB;
                return true;
            }
            i8--;
            if (i8 < 0) {
                return false;
            }
            iB = iB == 0 ? x() - 1 : iB - 1;
        }
    }

    private final void H(int i7) {
        if (this.f7023k > size()) {
            m();
        }
        int i8 = 0;
        if (i7 != x()) {
            this.f7021i = new int[i7];
            this.f7024l = f7017r.d(i7);
        } else {
            k.i(this.f7021i, 0, 0, x());
        }
        while (i8 < this.f7023k) {
            int i9 = i8 + 1;
            if (!G(i8)) {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
            i8 = i9;
        }
    }

    private final void J(int i7) {
        int iD = v5.h.d(this.f7022j * 2, x() / 2);
        int i8 = 0;
        int i9 = i7;
        do {
            i7 = i7 == 0 ? x() - 1 : i7 - 1;
            i8++;
            if (i8 > this.f7022j) {
                this.f7021i[i9] = 0;
                return;
            }
            int[] iArr = this.f7021i;
            int i10 = iArr[i7];
            if (i10 == 0) {
                iArr[i9] = 0;
                return;
            }
            if (i10 < 0) {
                iArr[i9] = -1;
            } else {
                int i11 = i10 - 1;
                if (((B(this.f7018e[i11]) - i7) & (x() - 1)) >= i8) {
                    this.f7021i[i9] = i10;
                    this.f7020h[i11] = i9;
                }
                iD--;
            }
            i9 = i7;
            i8 = 0;
            iD--;
        } while (iD >= 0);
        this.f7021i[i9] = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void L(int i7) {
        i5.c.f(this.f7018e, i7);
        J(this.f7020h[i7]);
        this.f7020h[i7] = -1;
        this.f7025m = size() - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V[] j() {
        V[] vArr = this.f7019g;
        if (vArr != null) {
            return vArr;
        }
        V[] vArr2 = (V[]) i5.c.d(v());
        this.f7019g = vArr2;
        return vArr2;
    }

    private final void m() {
        int i7;
        V[] vArr = this.f7019g;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            i7 = this.f7023k;
            if (i8 >= i7) {
                break;
            }
            if (this.f7020h[i8] >= 0) {
                K[] kArr = this.f7018e;
                kArr[i9] = kArr[i8];
                if (vArr != null) {
                    vArr[i9] = vArr[i8];
                }
                i9++;
            }
            i8++;
        }
        i5.c.g(this.f7018e, i9, i7);
        if (vArr != null) {
            i5.c.g(vArr, i9, this.f7023k);
        }
        this.f7023k = i9;
    }

    private final boolean p(Map<?, ?> map) {
        return size() == map.size() && n(map.entrySet());
    }

    private final void q(int i7) {
        if (i7 < 0) {
            throw new OutOfMemoryError();
        }
        if (i7 <= v()) {
            if ((this.f7023k + i7) - size() > v()) {
                H(x());
                return;
            }
            return;
        }
        int iV = (v() * 3) / 2;
        if (i7 <= iV) {
            i7 = iV;
        }
        this.f7018e = (K[]) i5.c.e(this.f7018e, i7);
        V[] vArr = this.f7019g;
        this.f7019g = vArr != null ? (V[]) i5.c.e(vArr, i7) : null;
        int[] iArrCopyOf = Arrays.copyOf(this.f7020h, i7);
        l.d(iArrCopyOf, "copyOf(this, newSize)");
        this.f7020h = iArrCopyOf;
        int iC = f7017r.c(i7);
        if (iC > x()) {
            H(iC);
        }
    }

    private final void r(int i7) {
        q(this.f7023k + i7);
    }

    private final int t(K k7) {
        int iB = B(k7);
        int i7 = this.f7022j;
        while (true) {
            int i8 = this.f7021i[iB];
            if (i8 == 0) {
                return -1;
            }
            if (i8 > 0) {
                int i9 = i8 - 1;
                if (l.a(this.f7018e[i9], k7)) {
                    return i9;
                }
            }
            i7--;
            if (i7 < 0) {
                return -1;
            }
            iB = iB == 0 ? x() - 1 : iB - 1;
        }
    }

    private final int u(V v6) {
        int i7 = this.f7023k;
        while (true) {
            i7--;
            if (i7 < 0) {
                return -1;
            }
            if (this.f7020h[i7] >= 0) {
                V[] vArr = this.f7019g;
                l.c(vArr);
                if (l.a(vArr[i7], v6)) {
                    return i7;
                }
            }
        }
    }

    private final int v() {
        return this.f7018e.length;
    }

    private final Object writeReplace() throws NotSerializableException {
        if (this.f7029q) {
            return new i(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    private final int x() {
        return this.f7021i.length;
    }

    public Collection<V> A() {
        g<V> gVar = this.f7027o;
        if (gVar != null) {
            return gVar;
        }
        g<V> gVar2 = new g<>(this);
        this.f7027o = gVar2;
        return gVar2;
    }

    public final boolean C() {
        return this.f7029q;
    }

    public final e<K, V> D() {
        return new e<>(this);
    }

    public final boolean I(Map.Entry<? extends K, ? extends V> entry) {
        l.e(entry, "entry");
        l();
        int iT = t(entry.getKey());
        if (iT < 0) {
            return false;
        }
        V[] vArr = this.f7019g;
        l.c(vArr);
        if (!l.a(vArr[iT], entry.getValue())) {
            return false;
        }
        L(iT);
        return true;
    }

    public final int K(K k7) {
        l();
        int iT = t(k7);
        if (iT < 0) {
            return -1;
        }
        L(iT);
        return iT;
    }

    public final boolean M(V v6) {
        l();
        int iU = u(v6);
        if (iU < 0) {
            return false;
        }
        L(iU);
        return true;
    }

    public final f<K, V> N() {
        return new f<>(this);
    }

    @Override // java.util.Map
    public void clear() {
        l();
        int i7 = this.f7023k - 1;
        if (i7 >= 0) {
            int i8 = 0;
            while (true) {
                int[] iArr = this.f7020h;
                int i9 = iArr[i8];
                if (i9 >= 0) {
                    this.f7021i[i9] = 0;
                    iArr[i8] = -1;
                }
                if (i8 == i7) {
                    break;
                } else {
                    i8++;
                }
            }
        }
        i5.c.g(this.f7018e, 0, this.f7023k);
        V[] vArr = this.f7019g;
        if (vArr != null) {
            i5.c.g(vArr, 0, this.f7023k);
        }
        this.f7025m = 0;
        this.f7023k = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return t(obj) >= 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return u(obj) >= 0;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return w();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof Map) && p((Map) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object obj) {
        int iT = t(obj);
        if (iT < 0) {
            return null;
        }
        V[] vArr = this.f7019g;
        l.c(vArr);
        return vArr[iT];
    }

    @Override // java.util.Map
    public int hashCode() {
        b<K, V> bVarS = s();
        int i7 = 0;
        while (bVarS.hasNext()) {
            i7 += bVarS.i();
        }
        return i7;
    }

    public final int i(K k7) {
        l();
        while (true) {
            int iB = B(k7);
            int iD = v5.h.d(this.f7022j * 2, x() / 2);
            int i7 = 0;
            while (true) {
                int i8 = this.f7021i[iB];
                if (i8 <= 0) {
                    if (this.f7023k < v()) {
                        int i9 = this.f7023k;
                        int i10 = i9 + 1;
                        this.f7023k = i10;
                        this.f7018e[i9] = k7;
                        this.f7020h[i9] = iB;
                        this.f7021i[iB] = i10;
                        this.f7025m = size() + 1;
                        if (i7 > this.f7022j) {
                            this.f7022j = i7;
                        }
                        return i9;
                    }
                    r(1);
                } else {
                    if (l.a(this.f7018e[i8 - 1], k7)) {
                        return -i8;
                    }
                    i7++;
                    if (i7 > iD) {
                        H(x() * 2);
                        break;
                    }
                    iB = iB == 0 ? x() - 1 : iB - 1;
                }
            }
        }
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public final Map<K, V> k() {
        l();
        this.f7029q = true;
        return this;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return y();
    }

    public final void l() {
        if (this.f7029q) {
            throw new UnsupportedOperationException();
        }
    }

    public final boolean n(Collection<?> m7) {
        l.e(m7, "m");
        for (Object obj : m7) {
            if (obj != null) {
                try {
                    if (!o((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean o(Map.Entry<? extends K, ? extends V> entry) {
        l.e(entry, "entry");
        int iT = t(entry.getKey());
        if (iT < 0) {
            return false;
        }
        V[] vArr = this.f7019g;
        l.c(vArr);
        return l.a(vArr[iT], entry.getValue());
    }

    @Override // java.util.Map
    public V put(K k7, V v6) {
        l();
        int i7 = i(k7);
        V[] vArrJ = j();
        if (i7 >= 0) {
            vArrJ[i7] = v6;
            return null;
        }
        int i8 = (-i7) - 1;
        V v7 = vArrJ[i8];
        vArrJ[i8] = v6;
        return v7;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> from) {
        l.e(from, "from");
        l();
        E(from.entrySet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V remove(Object obj) {
        int iK = K(obj);
        if (iK < 0) {
            return null;
        }
        V[] vArr = this.f7019g;
        l.c(vArr);
        V v6 = vArr[iK];
        i5.c.f(vArr, iK);
        return v6;
    }

    public final b<K, V> s() {
        return new b<>(this);
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return z();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((size() * 3) + 2);
        sb.append("{");
        b<K, V> bVarS = s();
        int i7 = 0;
        while (bVarS.hasNext()) {
            if (i7 > 0) {
                sb.append(", ");
            }
            bVarS.h(sb);
            i7++;
        }
        sb.append("}");
        String string = sb.toString();
        l.d(string, "sb.toString()");
        return string;
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return A();
    }

    public Set<Map.Entry<K, V>> w() {
        i5.e<K, V> eVar = this.f7028p;
        if (eVar != null) {
            return eVar;
        }
        i5.e<K, V> eVar2 = new i5.e<>(this);
        this.f7028p = eVar2;
        return eVar2;
    }

    public Set<K> y() {
        i5.f<K> fVar = this.f7026n;
        if (fVar != null) {
            return fVar;
        }
        i5.f<K> fVar2 = new i5.f<>(this);
        this.f7026n = fVar2;
        return fVar2;
    }

    public int z() {
        return this.f7025m;
    }

    public d() {
        this(8);
    }

    public d(int i7) {
        this(i5.c.d(i7), null, new int[i7], new int[f7017r.c(i7)], 2, 0);
    }
}
