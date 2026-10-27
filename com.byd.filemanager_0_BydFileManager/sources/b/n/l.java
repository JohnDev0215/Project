package b.n;

import android.view.ViewGroup;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f2156a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Runnable f2157b;

    public static l b(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.f2138c);
    }

    static void c(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.f2138c, lVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f2156a) != this || (runnable = this.f2157b) == null) {
            return;
        }
        runnable.run();
    }
}
