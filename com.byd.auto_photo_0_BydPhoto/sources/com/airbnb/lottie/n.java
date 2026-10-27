package com.airbnb.lottie;

import androidx.collection.ArraySet;
import androidx.core.util.Pair;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f719a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<b> f720b = new ArraySet();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, com.airbnb.lottie.w.f> f721c = new HashMap();
    private final Comparator<Pair<String, Float>> d = new a();

    class a implements Comparator<Pair<String, Float>> {
        a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Pair<String, Float> pair, Pair<String, Float> pair2) {
            float fFloatValue = pair.second.floatValue();
            float fFloatValue2 = pair2.second.floatValue();
            if (fFloatValue2 > fFloatValue) {
                return 1;
            }
            return fFloatValue > fFloatValue2 ? -1 : 0;
        }
    }

    public interface b {
        void a(float f);
    }

    public void a(String str, float f) {
        if (this.f719a) {
            com.airbnb.lottie.w.f fVar = this.f721c.get(str);
            if (fVar == null) {
                fVar = new com.airbnb.lottie.w.f();
                this.f721c.put(str, fVar);
            }
            fVar.a(f);
            if (str.equals("__container")) {
                Iterator<b> it = this.f720b.iterator();
                while (it.hasNext()) {
                    it.next().a(f);
                }
            }
        }
    }

    void b(boolean z) {
        this.f719a = z;
    }
}
