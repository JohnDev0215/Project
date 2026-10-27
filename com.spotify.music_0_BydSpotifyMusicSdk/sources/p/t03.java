package p;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class t03 {
    public final tz2 a;
    public final IntentFilter b;
    public final Context c;
    public final HashSet d = new HashSet();
    public o8 e = null;
    public volatile boolean f = false;

    public t03(tz2 tz2Var, IntentFilter intentFilter, Context context) {
        this.a = tz2Var;
        this.b = intentFilter;
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext != null ? applicationContext : context;
    }

    public abstract void a(Intent intent);

    public final void b() {
        o8 o8Var;
        if ((this.f || !this.d.isEmpty()) && this.e == null) {
            o8 o8Var2 = new o8(this);
            this.e = o8Var2;
            this.c.registerReceiver(o8Var2, this.b);
        }
        if (this.f || !this.d.isEmpty() || (o8Var = this.e) == null) {
            return;
        }
        this.c.unregisterReceiver(o8Var);
        this.e = null;
    }
}
