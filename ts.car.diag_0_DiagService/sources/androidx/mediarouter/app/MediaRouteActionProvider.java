package androidx.mediarouter.app;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.mediarouter.media.t;
import androidx.mediarouter.media.u;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class MediaRouteActionProvider extends androidx.core.view.b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final u f843c;
    private t d;
    private f e;
    private androidx.mediarouter.app.a f;
    private boolean g;
    private boolean h;

    /* JADX INFO: compiled from: caradapter-bug */
    private static final class a extends u.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<MediaRouteActionProvider> f844a;

        public a(MediaRouteActionProvider mediaRouteActionProvider) {
            this.f844a = new WeakReference<>(mediaRouteActionProvider);
        }

        private void m(u uVar) {
            MediaRouteActionProvider mediaRouteActionProvider = this.f844a.get();
            if (mediaRouteActionProvider != null) {
                mediaRouteActionProvider.n();
            } else {
                uVar.m(this);
            }
        }

        @Override // androidx.mediarouter.media.u.b
        public void a(u uVar, u.f fVar) {
            m(uVar);
        }

        @Override // androidx.mediarouter.media.u.b
        public void b(u uVar, u.f fVar) {
            m(uVar);
        }

        @Override // androidx.mediarouter.media.u.b
        public void c(u uVar, u.f fVar) {
            m(uVar);
        }

        @Override // androidx.mediarouter.media.u.b
        public void d(u uVar, u.g gVar) {
            m(uVar);
        }

        @Override // androidx.mediarouter.media.u.b
        public void e(u uVar, u.g gVar) {
            m(uVar);
        }

        @Override // androidx.mediarouter.media.u.b
        public void g(u uVar, u.g gVar) {
            m(uVar);
        }
    }

    public MediaRouteActionProvider(Context context) {
        super(context);
        this.d = t.f996c;
        this.e = f.a();
        this.f843c = u.g(context);
        new a(this);
    }

    @Override // androidx.core.view.b
    public boolean c() {
        return this.h || this.f843c.k(this.d, 1);
    }

    @Override // androidx.core.view.b
    public View d() {
        if (this.f != null) {
            Log.e("MRActionProvider", "onCreateActionView: this ActionProvider is already associated with a menu item. Don't reuse MediaRouteActionProvider instances! Abandoning the old menu item...");
        }
        androidx.mediarouter.app.a aVarM = m();
        this.f = aVarM;
        aVarM.h(true);
        this.f.l(this.d);
        if (this.g) {
            this.f.a();
        }
        this.f.g(this.h);
        this.f.i(this.e);
        this.f.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
        return this.f;
    }

    @Override // androidx.core.view.b
    public boolean f() {
        androidx.mediarouter.app.a aVar = this.f;
        if (aVar != null) {
            return aVar.m();
        }
        return false;
    }

    @Override // androidx.core.view.b
    public boolean h() {
        return true;
    }

    public androidx.mediarouter.app.a m() {
        return new androidx.mediarouter.app.a(a());
    }

    void n() {
        i();
    }
}
