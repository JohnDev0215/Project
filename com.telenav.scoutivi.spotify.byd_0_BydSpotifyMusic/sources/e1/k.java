package e1;

import android.view.ViewGroup;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ViewGroup f6116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Runnable f6117b;

    public static k b(ViewGroup viewGroup) {
        return (k) viewGroup.getTag(i.transition_current_scene);
    }

    static void c(ViewGroup viewGroup, k kVar) {
        viewGroup.setTag(i.transition_current_scene, kVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f6116a) != this || (runnable = this.f6117b) == null) {
            return;
        }
        runnable.run();
    }
}
