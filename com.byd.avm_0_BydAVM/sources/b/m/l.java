package b.m;

import android.view.ViewGroup;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f1884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Runnable f1885b;

    public static l b(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.f1868c);
    }

    static void c(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.f1868c, lVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f1884a) != this || (runnable = this.f1885b) == null) {
            return;
        }
        runnable.run();
    }
}
