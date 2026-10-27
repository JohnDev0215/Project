package Y;

import java.util.Iterator;
import java.util.Map;
import n7.AbstractC1993h;

/* JADX INFO: loaded from: classes.dex */
public final class j extends AbstractC1993h implements W.b {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final c f10599m;

    public /* synthetic */ j(c cVar, int i7) {
        this.l = i7;
        this.f10599m = cVar;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        Map.Entry entry;
        switch (this.l) {
            case 0:
                if (!(obj instanceof Map.Entry) || (entry = (Map.Entry) obj) == null) {
                    return false;
                }
                Object key = entry.getKey();
                c cVar = this.f10599m;
                Object obj2 = cVar.get(key);
                return obj2 != null ? obj2.equals(entry.getValue()) : entry.getValue() == null && cVar.containsKey(entry.getKey());
            default:
                return this.f10599m.containsKey(obj);
        }
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        switch (this.l) {
            case 0:
                c cVar = this.f10599m;
                cVar.getClass();
                return cVar.f10587m;
            default:
                c cVar2 = this.f10599m;
                cVar2.getClass();
                return cVar2.f10587m;
        }
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        switch (this.l) {
            case 0:
                c cVar = this.f10599m;
                n[] nVarArr = new n[8];
                for (int i7 = 0; i7 < 8; i7++) {
                    nVarArr[i7] = new o(0);
                }
                return new k(cVar.l, nVarArr);
            default:
                c cVar2 = this.f10599m;
                n[] nVarArr2 = new n[8];
                for (int i10 = 0; i10 < 8; i10++) {
                    nVarArr2[i10] = new o(1);
                }
                return new k(cVar2.l, nVarArr2);
        }
    }
}
