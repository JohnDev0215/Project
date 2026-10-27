package m1;

import g1.q;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class c extends l1.g {

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public float f18896n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final HashMap f18897o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final HashMap f18898p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final HashMap f18899q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public HashMap f18900r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public HashMap f18901s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public l1.i f18902t0;

    public c(q qVar, int i7) {
        super(qVar, i7);
        this.f18896n0 = 0.5f;
        this.f18897o0 = new HashMap();
        this.f18898p0 = new HashMap();
        this.f18899q0 = new HashMap();
        this.f18902t0 = l1.i.l;
    }

    public final float u(String str) {
        HashMap map = this.f18901s0;
        if (map == null || !map.containsKey(str)) {
            return 0.0f;
        }
        return ((Float) this.f18901s0.get(str)).floatValue();
    }

    public final float v(String str) {
        HashMap map = this.f18899q0;
        if (map.containsKey(str)) {
            return ((Float) map.get(str)).floatValue();
        }
        return 0.0f;
    }

    public final float w(String str) {
        HashMap map = this.f18900r0;
        if (map == null || !map.containsKey(str)) {
            return 0.0f;
        }
        return ((Float) this.f18900r0.get(str)).floatValue();
    }

    public final float x(String str) {
        HashMap map = this.f18898p0;
        if (map.containsKey(str)) {
            return ((Float) map.get(str)).floatValue();
        }
        return 0.0f;
    }
}
