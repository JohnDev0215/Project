package dagger.hilt.android.internal.managers;

import android.app.Application;
import android.app.Service;

/* JADX INFO: compiled from: ServiceComponentManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements o4.b<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Service f5995e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Object f5996g;

    /* JADX INFO: compiled from: ServiceComponentManager.java */
    public interface a {
        l4.d c();
    }

    public h(Service service) {
        this.f5995e = service;
    }

    private Object a() {
        Application application = this.f5995e.getApplication();
        o4.d.d(application instanceof o4.b, "Hilt service must be attached to an @AndroidEntryPoint Application. Found: %s", application.getClass());
        return ((a) h4.a.a(application, a.class)).c().b(this.f5995e).a();
    }

    @Override // o4.b
    public Object f() {
        if (this.f5996g == null) {
            this.f5996g = a();
        }
        return this.f5996g;
    }
}
