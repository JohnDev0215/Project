package y3;

import java.lang.ref.WeakReference;
import z3.e;

/* JADX INFO: compiled from: PagerTag.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public WeakReference<e> f9282b;

    public a(e eVar) {
        this.f9282b = new WeakReference<>(eVar);
    }

    public String toString() {
        return "PagerTag{index=" + this.f9281a + ", pagerRef=" + this.f9282b + '}';
    }
}
