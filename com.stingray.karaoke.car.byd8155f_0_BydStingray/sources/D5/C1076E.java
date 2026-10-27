package d5;

import java.io.Serializable;

/* JADX INFO: renamed from: d5.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1076E extends AbstractC1091m implements Serializable {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f13379m;

    public C1076E(Object obj, Object obj2) {
        this.l = obj;
        this.f13379m = obj2;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.l;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f13379m;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
