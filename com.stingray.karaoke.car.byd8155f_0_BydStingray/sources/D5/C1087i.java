package d5;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: renamed from: d5.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1087i extends C1082d implements SortedMap {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public SortedSet f13426p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Y f13427q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1087i(Y y10, SortedMap sortedMap) {
        super(y10, sortedMap);
        this.f13427q = y10;
    }

    public SortedSet c() {
        return new C1088j(this.f13427q, e());
    }

    @Override // java.util.SortedMap
    public final Comparator comparator() {
        return e().comparator();
    }

    @Override // d5.C1082d, java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public SortedSet keySet() {
        SortedSet sortedSet = this.f13426p;
        if (sortedSet != null) {
            return sortedSet;
        }
        SortedSet sortedSetC = c();
        this.f13426p = sortedSetC;
        return sortedSetC;
    }

    public SortedMap e() {
        return (SortedMap) this.f13414n;
    }

    @Override // java.util.SortedMap
    public final Object firstKey() {
        return e().firstKey();
    }

    public SortedMap headMap(Object obj) {
        return new C1087i(this.f13427q, e().headMap(obj));
    }

    @Override // java.util.SortedMap
    public final Object lastKey() {
        return e().lastKey();
    }

    public SortedMap subMap(Object obj, Object obj2) {
        return new C1087i(this.f13427q, e().subMap(obj, obj2));
    }

    public SortedMap tailMap(Object obj) {
        return new C1087i(this.f13427q, e().tailMap(obj));
    }
}
