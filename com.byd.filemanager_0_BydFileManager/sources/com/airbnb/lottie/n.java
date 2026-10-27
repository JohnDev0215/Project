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
    private boolean f2643a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<b> f2644b = new b.d.b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, com.airbnb.lottie.u.f> f2645c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Comparator<b.g.j.d<String, Float>> f2646d = new a();

    /* JADX INFO: compiled from: PerformanceTracker.java */
    class a implements Comparator<b.g.j.d<String, Float>> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(b.g.j.d<String, Float> dVar, b.g.j.d<String, Float> dVar2) {
            float fFloatValue = dVar.f1937b.floatValue();
            float fFloatValue2 = dVar2.f1937b.floatValue();
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
        if (this.f2643a) {
            com.airbnb.lottie.u.f fVar = this.f2645c.get(str);
            if (fVar == null) {
                fVar = new com.airbnb.lottie.u.f();
                this.f2645c.put(str, fVar);
            }
            fVar.a(f);
            if (str.equals("__container")) {
                Iterator<b> it = this.f2644b.iterator();
                while (it.hasNext()) {
                    it.next().a(f);
                }
            }
        }
    }

    void b(boolean z) {
        this.f2643a = z;
    }
}
