package h;

import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f15131a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f15132b = new CopyOnWriteArrayList();
    public A7.j c;

    public p(boolean z6) {
        this.f15131a = z6;
    }

    public void a() {
    }

    public abstract void b();

    public void c(C1330b c1330b) {
        A7.m.f(c1330b, "backEvent");
    }

    public void d(C1330b c1330b) {
        A7.m.f(c1330b, "backEvent");
    }
}
