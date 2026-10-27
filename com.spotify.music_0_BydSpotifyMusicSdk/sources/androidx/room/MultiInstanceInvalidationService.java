package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.LinkedHashMap;
import p.rl1;
import p.sl1;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class MultiInstanceInvalidationService extends Service {
    public int c;
    public final LinkedHashMap e = new LinkedHashMap();
    public final sl1 k = new sl1(this);
    public final rl1 l = new rl1(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        v03.j(intent, "intent");
        return this.l;
    }
}
