package l3;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: DriveModeManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SharedPreferences f7956b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected Context f7958d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f7955a = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final CopyOnWriteArraySet<a> f7957c = new CopyOnWriteArraySet<>();

    /* JADX INFO: compiled from: DriveModeManager.java */
    public interface a {
        void a(boolean z6);
    }

    public d(Application application) {
        this.f7958d = application.getApplicationContext();
        this.f7956b = application.getSharedPreferences("drive_mode", 0);
    }

    public boolean a() {
        return this.f7956b.getBoolean("enableDriveMode", true);
    }

    protected void b(boolean z6) {
        this.f7955a = z6;
        Iterator<a> it = this.f7957c.iterator();
        while (it.hasNext()) {
            it.next().a(z6);
        }
    }

    public void c(a aVar) {
        this.f7957c.add(aVar);
        aVar.a(this.f7955a);
    }
}
