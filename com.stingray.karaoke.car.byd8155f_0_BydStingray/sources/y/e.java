package Y;

import a0.C0905a;
import a0.C0906b;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class e extends AbstractMap implements W.c, Map, B7.e {
    public c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0906b f10590m = new C0906b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f10591n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f10592p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f10593q;

    public e(c cVar) {
        this.l = cVar;
        this.f10591n = cVar.l;
        cVar.getClass();
        this.f10593q = cVar.f10587m;
    }

    @Override // W.c
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public c a() {
        m mVar = this.f10591n;
        c cVar = this.l;
        if (mVar != cVar.l) {
            this.f10590m = new C0906b();
            cVar = new c(this.f10591n, this.f10593q);
        }
        this.l = cVar;
        return cVar;
    }

    public final void c(int i7) {
        this.f10593q = i7;
        this.f10592p++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.f10591n = m.f10600e;
        c(0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return this.f10591n.d(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new g(0, this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        return this.f10591n.g(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new g(1, this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.o = null;
        this.f10591n = this.f10591n.l(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        return this.o;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        c cVarA = null;
        c cVar = map instanceof c ? (c) map : null;
        if (cVar == null) {
            e eVar = map instanceof e ? (e) map : null;
            if (eVar != null) {
                cVarA = eVar.a();
            }
        } else {
            cVarA = cVar;
        }
        if (cVarA == null) {
            super.putAll(map);
            return;
        }
        C0905a c0905a = new C0905a();
        c0905a.f11025a = 0;
        int i7 = this.f10593q;
        m mVar = this.f10591n;
        m mVar2 = cVarA.l;
        A7.m.d(mVar2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder>");
        this.f10591n = mVar.m(mVar2, 0, c0905a, this);
        int i10 = (cVarA.f10587m + i7) - c0905a.f11025a;
        if (i7 != i10) {
            c(i10);
        }
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        int i7 = this.f10593q;
        m mVarO = this.f10591n.o(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        if (mVarO == null) {
            mVarO = m.f10600e;
        }
        this.f10591n = mVarO;
        return i7 != this.f10593q;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f10593q;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return new i(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        this.o = null;
        m mVarN = this.f10591n.n(obj != null ? obj.hashCode() : 0, obj, 0, this);
        if (mVarN == null) {
            mVarN = m.f10600e;
        }
        this.f10591n = mVarN;
        return this.o;
    }
}
