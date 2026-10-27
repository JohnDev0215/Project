package b0;

import T.AbstractC0750k0;
import T.T0;
import Y.m;
import a0.C0906b;

/* JADX INFO: loaded from: classes.dex */
public final class d extends Y.e {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public e f12194r;

    @Override // Y.e, java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof AbstractC0750k0) {
            return super.containsKey((AbstractC0750k0) obj);
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof T0) {
            return super.containsValue((T0) obj);
        }
        return false;
    }

    @Override // Y.e
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final e a() {
        m mVar = this.f10591n;
        e eVar = this.f12194r;
        if (mVar != eVar.l) {
            this.f10590m = new C0906b();
            eVar = new e(this.f10591n, this.f10593q);
        }
        this.f12194r = eVar;
        return eVar;
    }

    @Override // Y.e, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object get(Object obj) {
        if (obj instanceof AbstractC0750k0) {
            return (T0) super.get((AbstractC0750k0) obj);
        }
        return null;
    }

    @Override // java.util.Map
    public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
        return !(obj instanceof AbstractC0750k0) ? obj2 : (T0) super.getOrDefault((AbstractC0750k0) obj, (T0) obj2);
    }

    @Override // Y.e, java.util.AbstractMap, java.util.Map
    public final /* bridge */ Object remove(Object obj) {
        if (obj instanceof AbstractC0750k0) {
            return (T0) super.remove((AbstractC0750k0) obj);
        }
        return null;
    }
}
