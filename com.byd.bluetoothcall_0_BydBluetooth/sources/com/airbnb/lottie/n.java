package com.airbnb.lottie;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: PerformanceTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3028a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<b> f3029b = new b.d.b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, com.airbnb.lottie.u.f> f3030c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Comparator<b.g.j.d<String, Float>> f3031d = new a();

    /* JADX INFO: compiled from: PerformanceTracker.java */
    class a implements Comparator<b.g.j.d<String, Float>> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b.g.j.d<String, Float> dVar, b.g.j.d<String, Float> dVar2) {
            float fFloatValue = dVar.f2293b.floatValue();
            float fFloatValue2 = dVar2.f2293b.floatValue();
            if (fFloatValue2 > fFloatValue) {
                return 1;
            }
            return fFloatValue > fFloatValue2 ? -1 : 0;
        }
    }

    /* JADX INFO: compiled from: PerformanceTracker.java */
    public interface b {
        void a(float f);
    }

    public void a(String str, float f) {
        if (this.f3028a) {
            com.airbnb.lottie.u.f fVar = this.f3030c.get(str);
            if (fVar == null) {
                fVar = new com.airbnb.lottie.u.f();
                this.f3030c.put(str, fVar);
            }
            fVar.a(f);
            if (str.equals("__container")) {
                Iterator<b> it = this.f3029b.iterator();
                while (it.hasNext()) {
                    it.next().a(f);
                }
            }
        }
    }

    void b(boolean z) {
        this.f3028a = z;
    }
}
