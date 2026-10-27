package c.a.j;

import android.content.Context;
import android.content.res.Configuration;

/* JADX INFO: compiled from: UsbUpdateManager.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public d f2843a;

    public void a(Context context) {
        d dVar = new d(context);
        this.f2843a = dVar;
        dVar.K();
    }

    public void b(Configuration configuration) {
        d dVar = this.f2843a;
        if (dVar != null) {
            dVar.L(configuration);
        }
    }
}
