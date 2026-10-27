package b.x;

import android.view.ViewGroup;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f3283a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f3284b;

    public static l b(ViewGroup viewGroup) {
        return (l) viewGroup.getTag(j.transition_current_scene);
    }

    public static void c(ViewGroup viewGroup, l lVar) {
        viewGroup.setTag(j.transition_current_scene, lVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f3283a) != this || (runnable = this.f3284b) == null) {
            return;
        }
        runnable.run();
    }
}
