package b.b.m.d;

import b.b.m.e.f;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: PagerTag.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2098a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public WeakReference<f> f2099b;

    public a(f fVar) {
        this.f2099b = new WeakReference<>(fVar);
    }

    public void a(int i) {
        this.f2098a = i;
    }

    public String toString() {
        return "PagerTag{index=" + this.f2098a + ", pagerRef=" + this.f2099b + '}';
    }
}
