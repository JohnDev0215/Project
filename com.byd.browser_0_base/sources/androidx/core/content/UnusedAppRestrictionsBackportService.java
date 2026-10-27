package androidx.core.content;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.ow0;

/* JADX INFO: loaded from: classes.dex */
public abstract class UnusedAppRestrictionsBackportService extends Service {
    public final a e = new a();

    public class a extends ow0.a {
        public a() {
        }
    }

    public abstract void a();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.e;
    }
}
