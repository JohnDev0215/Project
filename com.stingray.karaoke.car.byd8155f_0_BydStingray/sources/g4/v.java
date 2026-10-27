package g4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P f14956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List f14957b;
    public final String c;

    public v(Class cls, Class cls2, Class cls3, List list, P p10) {
        this.f14956a = p10;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        this.f14957b = list;
        this.c = "Failed LoadPath{" + cls.getSimpleName() + "->" + cls2.getSimpleName() + "->" + cls3.getSimpleName() + "}";
    }

    public final x a(int i7, int i10, C1.j jVar, com.bumptech.glide.load.data.g gVar, e4.h hVar) {
        P p10 = this.f14956a;
        List list = (List) p10.i();
        try {
            List list2 = this.f14957b;
            int size = list2.size();
            x xVarA = null;
            for (int i11 = 0; i11 < size; i11++) {
                try {
                    xVarA = ((j) list2.get(i11)).a(i7, i10, jVar, gVar, hVar);
                } catch (t e10) {
                    list.add(e10);
                }
                if (xVarA != null) {
                    break;
                }
            }
            if (xVarA != null) {
                return xVarA;
            }
            throw new t(this.c, new ArrayList(list));
        } finally {
            p10.B(list);
        }
    }

    public final String toString() {
        return "LoadPath{decodePaths=" + Arrays.toString(this.f14957b.toArray()) + '}';
    }
}
