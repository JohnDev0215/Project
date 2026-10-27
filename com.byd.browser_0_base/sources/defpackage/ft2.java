package defpackage;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.base.Callback;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: StatusIndicatorCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ft2 {
    public final qt2 a;
    public final st2 b;
    public boolean c;
    public l91 d;
    public int e;
    public pn3 f;
    public final ResourceManager g;
    public boolean h;
    public final Activity i;
    public final Callback<Runnable> j;
    public boolean k;

    public ft2(AppCompatActivity appCompatActivity, ResourceManager resourceManager, fj fjVar, l23 l23Var, yq2 yq2Var, nw2 nw2Var, jb1 jb1Var) {
        this.i = appCompatActivity;
        this.g = resourceManager;
        this.j = jb1Var;
        this.b = new st2(fjVar);
        this.a = new qt2(fjVar, l23Var, yq2Var, nw2Var);
    }

    /* JADX INFO: compiled from: StatusIndicatorCoordinator.java */
    public interface a {
        default void a() {
        }

        default void d() {
        }

        default void e(int i) {
        }
    }
}
