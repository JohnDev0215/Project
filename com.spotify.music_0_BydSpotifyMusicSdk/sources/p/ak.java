package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.spotify.automotive.base.service.AutomotiveService;

/* JADX INFO: loaded from: classes.dex */
public final class ak implements ServiceConnection {
    public final zj a;
    public final Context b;
    public boolean c;

    public ak(Context context, zj zjVar) {
        this.a = zjVar;
        this.b = context;
    }

    public final void a() {
        if (this.c) {
            return;
        }
        Context context = this.b;
        context.getApplicationContext().bindService(new Intent(context, (Class<?>) AutomotiveService.class), this, 1);
    }

    public final void b() {
        if (this.c) {
            this.b.getApplicationContext().unbindService(this);
            this.a.c();
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.c = true;
        this.a.m((yj) iBinder);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.c = false;
    }
}
