package defpackage;

import android.view.ViewGroup;
import androidx.transition.R$id;

/* JADX INFO: compiled from: Scene.java */
/* JADX INFO: loaded from: classes.dex */
public class xd {
    public ViewGroup a;
    public Runnable b;

    public static xd b(ViewGroup viewGroup) {
        return (xd) viewGroup.getTag(R$id.transition_current_scene);
    }

    public static void c(ViewGroup viewGroup, xd xdVar) {
        viewGroup.setTag(R$id.transition_current_scene, xdVar);
    }

    public void a() {
        Runnable runnable;
        if (b(this.a) != this || (runnable = this.b) == null) {
            return;
        }
        runnable.run();
    }
}
