package spi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class b6 {

    public interface a {
        c a();
    }

    public interface b {
        c a();
    }

    public static final class c {
        public final Set<String> a;
        public final gv b;

        public c(Set<String> set, gv gvVar) {
            this.a = set;
            this.b = gvVar;
        }
    }

    private b6() {
    }

    public static ViewModelProvider.Factory a(Fragment fragment, ViewModelProvider.Factory factory) {
        c cVarA = ((b) t8.a(fragment, b.class)).a();
        Objects.requireNonNull(cVarA);
        Bundle arguments = fragment.getArguments();
        Set<String> set = cVarA.a;
        Objects.requireNonNull(factory);
        return new HiltViewModelFactory(fragment, arguments, set, factory, cVarA.b);
    }
}
