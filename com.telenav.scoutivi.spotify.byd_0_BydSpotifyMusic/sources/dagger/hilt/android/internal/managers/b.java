package dagger.hilt.android.internal.managers;

import android.content.Context;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.e0;
import i4.a;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: ActivityRetainedComponentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements o4.b<j4.b> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b0 f5981e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private volatile j4.b f5982g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Object f5983h = new Object();

    /* JADX INFO: compiled from: ActivityRetainedComponentManager.java */
    class a implements b0.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f5984a;

        a(Context context) {
            this.f5984a = context;
        }

        @Override // androidx.lifecycle.b0.b
        public <T extends a0> T a(Class<T> cls) {
            return new c(((InterfaceC0064b) i4.b.a(this.f5984a, InterfaceC0064b.class)).d().a());
        }
    }

    /* JADX INFO: renamed from: dagger.hilt.android.internal.managers.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRetainedComponentManager.java */
    public interface InterfaceC0064b {
        l4.b d();
    }

    /* JADX INFO: compiled from: ActivityRetainedComponentManager.java */
    static final class c extends a0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final j4.b f5986a;

        c(j4.b bVar) {
            this.f5986a = bVar;
        }

        j4.b h() {
            return this.f5986a;
        }

        @Override // androidx.lifecycle.a0
        protected void onCleared() {
            super.onCleared();
            ((e) ((d) h4.a.a(this.f5986a, d.class)).b()).a();
        }
    }

    /* JADX INFO: compiled from: ActivityRetainedComponentManager.java */
    public interface d {
        i4.a b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: compiled from: ActivityRetainedComponentManager.java */
    public static final class e implements i4.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Set<a.InterfaceC0087a> f5987a = new HashSet();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f5988b = false;

        e() {
        }

        void a() {
            k4.b.a();
            this.f5988b = true;
            Iterator<a.InterfaceC0087a> it = this.f5987a.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }
    }

    b(ComponentActivity componentActivity) {
        this.f5981e = c(componentActivity, componentActivity);
    }

    private j4.b a() {
        return ((c) this.f5981e.a(c.class)).h();
    }

    private b0 c(e0 e0Var, Context context) {
        return new b0(e0Var, new a(context));
    }

    @Override // o4.b
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public j4.b f() {
        if (this.f5982g == null) {
            synchronized (this.f5983h) {
                if (this.f5982g == null) {
                    this.f5982g = a();
                }
            }
        }
        return this.f5982g;
    }
}
