package spi;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import androidx.fragment.app.Fragment;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class oc implements xc<Object> {
    public volatile d5 a;
    public final Object b = new Object();
    public final Fragment c;

    public interface a {
        nc c();
    }

    public oc(Fragment fragment) {
        this.c = fragment;
    }

    public static final Context c(Context context) {
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }

    @Override // spi.xc
    public final Object a() {
        if (this.a == null) {
            synchronized (this.b) {
                if (this.a == null) {
                    this.a = (d5) b();
                }
            }
        }
        return this.a;
    }

    public final Object b() {
        Objects.requireNonNull(this.c.getHost(), "Hilt Fragments must be attached before creating the component.");
        bl.a(this.c.getHost() instanceof xc, "Hilt Fragments must be attached to an @AndroidEntryPoint Activity. Found: %s", this.c.getHost().getClass());
        nc ncVarC = ((a) t8.a(this.c.getHost(), a.class)).c();
        Fragment fragment = this.c;
        c5 c5Var = (c5) ncVarC;
        Objects.requireNonNull(c5Var);
        Objects.requireNonNull(fragment);
        c5Var.d = fragment;
        return new d5(c5Var.a, c5Var.b, c5Var.c, fragment, null);
    }
}
