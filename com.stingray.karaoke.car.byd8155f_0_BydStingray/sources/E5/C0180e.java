package E5;

import h2.AbstractC1347d;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: renamed from: E5.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0180e implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1804a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Serializable f1805b;
    public final Object c;

    public /* synthetic */ C0180e(int i7, Serializable serializable, Object obj) {
        this.f1804a = i7;
        this.f1805b = serializable;
        this.c = obj;
    }

    public static AbstractC0179d b(ArrayList arrayList, Type type, Set set) {
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            AbstractC0179d abstractC0179d = (AbstractC0179d) arrayList.get(i7);
            if (M.c(abstractC0179d.f1799a, type) && abstractC0179d.f1800b.equals(set)) {
                return abstractC0179d;
            }
        }
        return null;
    }

    @Override // E5.p
    public final q a(Type type, Set set, H h10) {
        q qVarD = null;
        Object obj = this.c;
        Serializable serializable = this.f1805b;
        switch (this.f1804a) {
            case 0:
                AbstractC0179d abstractC0179dB = b((ArrayList) serializable, type, set);
                AbstractC0179d abstractC0179dB2 = b((ArrayList) obj, type, set);
                if (abstractC0179dB == null && abstractC0179dB2 == null) {
                    return null;
                }
                if (abstractC0179dB == null || abstractC0179dB2 == null) {
                    try {
                        qVarD = h10.d(this, type, set);
                    } catch (IllegalArgumentException e10) {
                        StringBuilder sbJ = AbstractC1347d.j("No ", abstractC0179dB == null ? "@ToJson" : "@FromJson", " adapter for ");
                        sbJ.append(G5.f.j(type, set));
                        throw new IllegalArgumentException(sbJ.toString(), e10);
                    }
                    break;
                }
                q qVar = qVarD;
                if (abstractC0179dB != null) {
                    abstractC0179dB.a(h10, this);
                }
                if (abstractC0179dB2 != null) {
                    abstractC0179dB2.a(h10, this);
                }
                return new C0176a(abstractC0179dB, qVar, h10, abstractC0179dB2, set, type);
            default:
                if (!set.isEmpty()) {
                    return null;
                }
                Set set2 = G5.f.f2685a;
                if (M.c((Class) serializable, type)) {
                    return (F5.a) obj;
                }
                return null;
        }
    }
}
