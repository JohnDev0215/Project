package dagger.hilt.android.internal.managers;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;

/* JADX INFO: compiled from: FragmentComponentManager.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements o4.b<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f5992e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object f5993g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Fragment f5994h;

    /* JADX INFO: compiled from: FragmentComponentManager.java */
    public interface a {
        l4.c d();
    }

    public g(Fragment fragment) {
        this.f5994h = fragment;
    }

    private Object a() {
        o4.d.c(this.f5994h.B(), "Hilt Fragments must be attached before creating the component.");
        o4.d.d(this.f5994h.B() instanceof o4.b, "Hilt Fragments must be attached to an @AndroidEntryPoint Activity. Found: %s", this.f5994h.B().getClass());
        e(this.f5994h);
        return ((a) h4.a.a(this.f5994h.B(), a.class)).d().b(this.f5994h).a();
    }

    public static ContextWrapper b(Context context, Fragment fragment) {
        return new ViewComponentManager$FragmentContextWrapper(context, fragment);
    }

    public static ContextWrapper c(LayoutInflater layoutInflater, Fragment fragment) {
        return new ViewComponentManager$FragmentContextWrapper(layoutInflater, fragment);
    }

    public static final Context d(Context context) {
        while ((context instanceof ContextWrapper) && !(context instanceof Activity)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return context;
    }

    protected void e(Fragment fragment) {
    }

    @Override // o4.b
    public Object f() {
        if (this.f5992e == null) {
            synchronized (this.f5993g) {
                if (this.f5992e == null) {
                    this.f5992e = a();
                }
            }
        }
        return this.f5992e;
    }
}
