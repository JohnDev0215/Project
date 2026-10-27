package ts.car.diag;

import android.app.Application;
import android.content.Intent;
import ts.car.diag.manager.q;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class DiagApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        q.h("DIAG-APP", "onCreate");
        super.onCreate();
        startService(new Intent(this, (Class<?>) DiagServices.class));
    }
}
