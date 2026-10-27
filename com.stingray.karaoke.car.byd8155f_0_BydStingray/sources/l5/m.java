package l5;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class m implements Map.Entry {
    public m l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public m f18649m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f18650n;
    public m o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public m f18651p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f18652q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f18653r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Object f18654s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f18655t;

    public m(boolean z6) {
        this.f18652q = null;
        this.f18653r = z6;
        this.f18651p = this;
        this.o = this;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.f18652q;
        if (obj2 == null) {
            if (entry.getKey() != null) {
                return false;
            }
        } else if (!obj2.equals(entry.getKey())) {
            return false;
        }
        Object obj3 = this.f18654s;
        if (obj3 == null) {
            if (entry.getValue() != null) {
                return false;
            }
        } else if (!obj3.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f18652q;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f18654s;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.f18652q;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.f18654s;
        return iHashCode ^ (obj2 != null ? obj2.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (obj == null && !this.f18653r) {
            throw new NullPointerException("value == null");
        }
        Object obj2 = this.f18654s;
        this.f18654s = obj;
        return obj2;
    }

    public final String toString() {
        return this.f18652q + "=" + this.f18654s;
    }

    public m(boolean z6, m mVar, Object obj, m mVar2, m mVar3) {
        this.l = mVar;
        this.f18652q = obj;
        this.f18653r = z6;
        this.f18655t = 1;
        this.o = mVar2;
        this.f18651p = mVar3;
        mVar3.o = this;
        mVar2.f18651p = this;
    }
}
