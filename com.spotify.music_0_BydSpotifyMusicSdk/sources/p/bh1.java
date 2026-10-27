package p;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import com.spotify.automotive.base.service.AAMMediaBrowserService;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class bh1 extends Service {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ int f107p = 0;
    public sg1 c;
    public final wg1 e = new wg1(this);
    public final jg1 k = new jg1(this, "android.media.session.MediaController", -1, -1, null);
    public final ArrayList l = new ArrayList();
    public final nb m = new nb(0);
    public final rh1 n = new rh1(this);
    public MediaSessionCompat$Token o;

    static {
        Log.isLoggable("MBServiceCompat", 3);
    }

    public abstract hg1 a(String str, int i);

    public abstract void b(String str, vg1 vg1Var);

    public abstract void c(Bundle bundle, gg1 gg1Var, String str);

    public final void d(String str, jg1 jg1Var, Bundle bundle, Bundle bundle2) {
        fg1 fg1Var = new fg1(this, str, jg1Var, str, bundle, bundle2);
        if (bundle == null) {
            ((AAMMediaBrowserService) this).q.d(Bundle.EMPTY, fg1Var, str);
        } else {
            ((AAMMediaBrowserService) this).q.d(bundle, fg1Var, str);
        }
        if (fg1Var.b()) {
            return;
        }
        throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + jg1Var.a + " id=" + str);
    }

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.c.b.onBind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 28) {
            this.c = new tg1(this);
        } else {
            this.c = new sg1(this);
        }
        this.c.b();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.n.b = null;
    }
}
