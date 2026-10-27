package androidx.datastore.preferences.protobuf;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import u.C2463c;
import u.C2465e;

/* JADX INFO: loaded from: classes.dex */
public class g0 extends AbstractSet {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Map f11846m;

    public /* synthetic */ g0(int i7, Map map) {
        this.l = i7;
        this.f11846m = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(Object obj) {
        switch (this.l) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                if (contains(entry)) {
                    return false;
                }
                ((C0938a0) this.f11846m).put((Comparable) entry.getKey(), entry.getValue());
                return true;
            case 1:
                Map.Entry entry2 = (Map.Entry) obj;
                if (contains(entry2)) {
                    return false;
                }
                ((p8.z) this.f11846m).put((Comparable) entry2.getKey(), entry2.getValue());
                return true;
            default:
                return super.add(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        switch (this.l) {
            case 0:
                ((C0938a0) this.f11846m).clear();
                break;
            case 1:
                ((p8.z) this.f11846m).clear();
                break;
            default:
                super.clear();
                break;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        switch (this.l) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                Object obj2 = ((C0938a0) this.f11846m).get(entry.getKey());
                Object value = entry.getValue();
                return obj2 == value || (obj2 != null && obj2.equals(value));
            case 1:
                Map.Entry entry2 = (Map.Entry) obj;
                Object obj3 = ((p8.z) this.f11846m).get(entry2.getKey());
                Object value2 = entry2.getValue();
                return obj3 == value2 || (obj3 != null && obj3.equals(value2));
            default:
                return super.contains(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        switch (this.l) {
            case 0:
                return new f0((C0938a0) this.f11846m, 0);
            case 1:
                return new f0((p8.z) this.f11846m, 1);
            default:
                return new C2463c((C2465e) this.f11846m);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        switch (this.l) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                if (!contains(entry)) {
                    return false;
                }
                ((C0938a0) this.f11846m).remove(entry.getKey());
                return true;
            case 1:
                Map.Entry entry2 = (Map.Entry) obj;
                if (!contains(entry2)) {
                    return false;
                }
                ((p8.z) this.f11846m).remove(entry2.getKey());
                return true;
            default:
                return super.remove(obj);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        switch (this.l) {
            case 0:
                return ((C0938a0) this.f11846m).size();
            case 1:
                return ((p8.z) this.f11846m).size();
            default:
                return ((C2465e) this.f11846m).f22587n;
        }
    }
}
