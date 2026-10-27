package b.n;

import android.view.ViewGroup;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f1988a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Runnable f1989b;

    public static l b(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.f1972c);
    }

    static void c(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.f1972c, lVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f1988a) != this || (runnable = this.f1989b) == null) {
            return;
        }
        runnable.run();
    }
}
