package spi;

import android.app.Application;
import android.app.Service;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class yo implements xc<Object> {
    public final Service a;
    public f5 b;

    public interface a {
        xo a();
    }

    public yo(Service service) {
        this.a = service;
    }

    @Override // spi.xc
    public final Object a() {
        if (this.b == null) {
            Application application = this.a.getApplication();
            bl.a(application instanceof xc, "Hilt service must be attached to an @AndroidEntryPoint Application. Found: %s", application.getClass());
            xo xoVarA = ((a) t8.a(application, a.class)).a();
            Service service = this.a;
            e5 e5Var = (e5) xoVarA;
            Objects.requireNonNull(e5Var);
            Objects.requireNonNull(service);
            this.b = new f5(e5Var.a, service, null);
        }
        return this.b;
    }
}
