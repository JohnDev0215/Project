package y0;

import android.view.ViewGroup;
import androidx.transition.R$id;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f5931a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Runnable f5932b;

    public static k b(ViewGroup viewGroup) {
        return (k) viewGroup.getTag(R$id.transition_current_scene);
    }

    public static void c(ViewGroup viewGroup, k kVar) {
        viewGroup.setTag(R$id.transition_current_scene, kVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.f5931a) != this || (runnable = this.f5932b) == null) {
            return;
        }
        runnable.run();
    }
}
