package b0;

import T.AbstractC0750k0;
import T.InterfaceC0744h0;
import T.T0;
import Y.m;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Y.c implements InterfaceC0744h0 {
    public static final e o = new e(m.f10600e, 0);

    @Override // Y.c
    /* JADX INFO: renamed from: b */
    public final Y.e m() {
        d dVar = new d(this);
        dVar.f12194r = this;
        return dVar;
    }

    @Override // Y.c, java.util.Map
    public final /* bridge */ boolean containsKey(Object obj) {
        if (obj instanceof AbstractC0750k0) {
            return super.containsKey((AbstractC0750k0) obj);
        }
        return false;
    }

    @Override // n7.AbstractC1990e, java.util.Map
    public final /* bridge */ boolean containsValue(Object obj) {
        if (obj instanceof T0) {
            return super.containsValue((T0) obj);
        }
        return false;
    }

    @Override // Y.c, java.util.Map
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

    @Override // Y.c, W.d
    public final W.c m() {
        d dVar = new d(this);
        dVar.f12194r = this;
        return dVar;
    }
}
