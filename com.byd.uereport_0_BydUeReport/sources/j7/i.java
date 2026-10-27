package j7;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: PendingPost.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List<i> f5656a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f5657b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public p f5658c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i f5659d;

    public i(Object obj, p pVar) {
        this.f5657b = obj;
        this.f5658c = pVar;
    }

    public static i a(p pVar, Object obj) {
        List<i> list = f5656a;
        synchronized (list) {
            int size = list.size();
            if (size <= 0) {
                return new i(obj, pVar);
            }
            i iVarRemove = list.remove(size - 1);
            iVarRemove.f5657b = obj;
            iVarRemove.f5658c = pVar;
            iVarRemove.f5659d = null;
            return iVarRemove;
        }
    }

    public static void b(i iVar) {
        iVar.f5657b = null;
        iVar.f5658c = null;
        iVar.f5659d = null;
        List<i> list = f5656a;
        synchronized (list) {
            if (list.size() < 10000) {
                list.add(iVar);
            }
        }
    }
}
