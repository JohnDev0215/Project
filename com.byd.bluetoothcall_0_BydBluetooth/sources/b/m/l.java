package b.m;

import android.view.ViewGroup;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f2494a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Runnable f2495b;

    public static l b(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.f2476c);
    }

    static void c(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.f2476c, lVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f2494a) != this || (runnable = this.f2495b) == null) {
            return;
        }
        runnable.run();
    }
}
