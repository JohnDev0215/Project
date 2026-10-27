package o1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: PerformanceTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f6863a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set<b> f6864b = new v.b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Map<String, a2.f> f6865c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Comparator<n0.d<String, Float>> f6866d = new a();

    /* JADX INFO: compiled from: PerformanceTracker.java */
    public class a implements Comparator<n0.d<String, Float>> {
        public a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(n0.d<String, Float> dVar, n0.d<String, Float> dVar2) {
            float fFloatValue = dVar.f6507b.floatValue();
            float fFloatValue2 = dVar2.f6507b.floatValue();
            if (fFloatValue2 > fFloatValue) {
                return 1;
            }
            return fFloatValue > fFloatValue2 ? -1 : 0;
        }
    }

    /* JADX INFO: compiled from: PerformanceTracker.java */
    public interface b {
        void a(float f8);
    }

    public void a(String str, float f8) {
        if (this.f6863a) {
            a2.f fVar = this.f6865c.get(str);
            if (fVar == null) {
                fVar = new a2.f();
                this.f6865c.put(str, fVar);
            }
            fVar.a(f8);
            if (str.equals("__container")) {
                Iterator<b> it = this.f6864b.iterator();
                while (it.hasNext()) {
                    it.next().a(f8);
                }
            }
        }
    }

    public void b(boolean z7) {
        this.f6863a = z7;
    }
}
