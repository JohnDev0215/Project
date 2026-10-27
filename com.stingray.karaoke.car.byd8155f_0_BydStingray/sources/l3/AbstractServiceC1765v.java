package l3;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import u.C2465e;

/* JADX INFO: renamed from: l3.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractServiceC1765v extends Service {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final boolean f18609t = Log.isLoggable("MBServiceCompat", 3);
    public C1759o l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1764u f18610m = new C1764u(this, 0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1756l f18611n = new C1756l(this, "android.media.session.MediaController", -1, -1, null);
    public final ArrayList o = new ArrayList();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final C2465e f18612p = new C2465e(0);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C1756l f18613q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final B5.H f18614r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public O f18615s;

    public AbstractServiceC1765v() {
        B5.H h10 = new B5.H();
        h10.f657b = this;
        this.f18614r = h10;
    }

    public abstract C1755k a(Bundle bundle);

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        C1759o c1759o = this.l;
        c1759o.getClass();
        C1758n c1758n = (C1758n) c1759o.f18586b;
        c1758n.getClass();
        return c1758n.onBind(intent);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        C1759o c1759o = new C1759o(this);
        this.l = c1759o;
        C1758n c1758n = new C1758n(c1759o, this);
        c1759o.f18586b = c1758n;
        c1758n.onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        this.f18614r.f657b = null;
    }
}
