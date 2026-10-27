package p;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public abstract class u91 extends Service implements s91 {
    public final kk0 c = new kk0(this);

    @Override // p.s91
    public final l91 l() {
        return (androidx.lifecycle.a) this.c.e;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        v03.j(intent, "intent");
        this.c.y(Lifecycle$Event.ON_START);
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.c.y(Lifecycle$Event.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        Lifecycle$Event lifecycle$Event = Lifecycle$Event.ON_STOP;
        kk0 kk0Var = this.c;
        kk0Var.y(lifecycle$Event);
        kk0Var.y(Lifecycle$Event.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onStart(Intent intent, int i) {
        this.c.y(Lifecycle$Event.ON_START);
        super.onStart(intent, i);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
