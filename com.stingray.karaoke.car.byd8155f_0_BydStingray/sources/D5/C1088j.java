package d5;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.SortedSet;

/* JADX INFO: renamed from: d5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C1088j extends C1083e implements SortedSet {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Y f13431n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1088j(Y y10, SortedMap sortedMap) {
        super(y10, sortedMap);
        this.f13431n = y10;
    }

    public SortedMap a() {
        return (SortedMap) this.l;
    }

    @Override // java.util.SortedSet
    public final Comparator comparator() {
        return a().comparator();
    }

    @Override // java.util.SortedSet
    public final Object first() {
        return a().firstKey();
    }

    public SortedSet headSet(Object obj) {
        return new C1088j(this.f13431n, a().headMap(obj));
    }

    @Override // java.util.SortedSet
    public final Object last() {
        return a().lastKey();
    }

    public SortedSet subSet(Object obj, Object obj2) {
        return new C1088j(this.f13431n, a().subMap(obj, obj2));
    }

    public SortedSet tailSet(Object obj) {
        return new C1088j(this.f13431n, a().tailMap(obj));
    }
}
