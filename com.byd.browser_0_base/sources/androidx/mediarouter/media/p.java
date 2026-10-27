package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.mediarouter.media.h;
import java.util.ArrayList;

/* JADX INFO: compiled from: RegisteredMediaRouteProviderWatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class p {
    public final Context a;
    public final c b;
    public final PackageManager d;
    public boolean f;
    public final ArrayList<o> e = new ArrayList<>();
    public final a g = new a();
    public final b h = new b();
    public final Handler c = new Handler();

    /* JADX INFO: compiled from: RegisteredMediaRouteProviderWatcher.java */
    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            p.this.a();
        }
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProviderWatcher.java */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            p.this.a();
        }
    }

    /* JADX INFO: compiled from: RegisteredMediaRouteProviderWatcher.java */
    public interface c {
    }

    public p(Context context, h.d dVar) {
        this.a = context;
        this.b = dVar;
        this.d = context.getPackageManager();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x004a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x009f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            Method dump skipped, instruction units count: 403
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.p.a():void");
    }
}
