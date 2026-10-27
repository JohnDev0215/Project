package spi;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/* JADX INFO: loaded from: classes2.dex */
public final class q implements xc<n> {
    public final ViewModelProvider a;

    @Nullable
    public volatile n b;
    public final Object c = new Object();

    public interface a {
        o g();
    }

    public static final class b extends ViewModel {
        public final n a;

        public b(n nVar) {
            this.a = nVar;
        }

        @Override // androidx.lifecycle.ViewModel
        public final void onCleared() {
            super.onCleared();
            ((on) ((c) t8.a(this.a, c.class)).a()).a();
        }
    }

    public interface c {
        r a();
    }

    public q(ComponentActivity componentActivity) {
        this.a = new ViewModelProvider(componentActivity, new p(this, componentActivity));
    }

    @Override // spi.xc
    public final n a() {
        if (this.b == null) {
            synchronized (this.c) {
                if (this.b == null) {
                    this.b = ((b) this.a.get(b.class)).a;
                }
            }
        }
        return this.b;
    }
}
