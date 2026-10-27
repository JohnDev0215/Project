package m4;

import android.app.Application;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.b0;
import androidx.lifecycle.z;
import java.util.Set;
import l4.e;

/* JADX INFO: compiled from: DefaultViewModelFactories.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: m4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DefaultViewModelFactories.java */
    public interface InterfaceC0127a {
        c a();
    }

    /* JADX INFO: compiled from: DefaultViewModelFactories.java */
    public interface b {
        c a();
    }

    /* JADX INFO: compiled from: DefaultViewModelFactories.java */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Application f8394a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Set<String> f8395b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final e f8396c;

        c(Application application, Set<String> set, e eVar) {
            this.f8394a = application;
            this.f8395b = set;
            this.f8396c = eVar;
        }

        private b0.b c(androidx.savedstate.c cVar, Bundle bundle, b0.b bVar) {
            if (bVar == null) {
                bVar = new z(this.f8394a, cVar, bundle);
            }
            return new m4.c(cVar, bundle, this.f8395b, bVar, this.f8396c);
        }

        b0.b a(ComponentActivity componentActivity, b0.b bVar) {
            return c(componentActivity, componentActivity.getIntent() != null ? componentActivity.getIntent().getExtras() : null, bVar);
        }

        b0.b b(Fragment fragment, b0.b bVar) {
            return c(fragment, fragment.q(), bVar);
        }
    }

    public static b0.b a(ComponentActivity componentActivity, b0.b bVar) {
        return ((InterfaceC0127a) h4.a.a(componentActivity, InterfaceC0127a.class)).a().a(componentActivity, bVar);
    }

    public static b0.b b(Fragment fragment, b0.b bVar) {
        return ((b) h4.a.a(fragment, b.class)).a().b(fragment, bVar);
    }
}
