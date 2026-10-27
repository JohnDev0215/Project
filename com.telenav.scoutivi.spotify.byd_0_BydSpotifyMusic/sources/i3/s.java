package i3;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: MediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f6972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Drawable f6973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f6974c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f6975d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f6976e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Context f6977f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f6978g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private MediaBrowserCompat f6979h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private MediaControllerCompat f6980i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Resources f6981j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f6982k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private MediaControllerCompat.a f6983l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final Handler f6985n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Timer f6986o;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final CopyOnWriteArrayList<i3.a> f6984m = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final MediaBrowserCompat.b f6987p = new b();

    /* JADX INFO: compiled from: MediaSource.java */
    class a implements q4.h<MediaBrowserCompat> {

        /* JADX INFO: renamed from: i3.s$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: MediaSource.java */
        class C0086a implements i3.a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ q4.f f6989a;

            C0086a(q4.f fVar) {
                this.f6989a = fVar;
            }

            @Override // i3.a
            public void a(s sVar) {
                h3.i.b("MediaSource").b("onConnectionSuspended", new Object[0]);
            }

            @Override // i3.a
            public void b(s sVar) {
                sVar.K(this);
                this.f6989a.a(new RuntimeException("onConnectionFailed " + sVar.w()));
            }

            @Override // i3.a
            public void c(s sVar) {
                sVar.K(this);
                this.f6989a.b(s.this.f6979h);
            }
        }

        a() {
        }

        @Override // q4.h
        public void a(q4.f<MediaBrowserCompat> fVar) {
            s.this.r(new C0086a(fVar));
            s.this.t();
        }
    }

    /* JADX INFO: compiled from: MediaSource.java */
    class b extends MediaBrowserCompat.b {
        b() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void a() {
            s.this.s();
            super.a();
            h3.i.b("MediaSource").b("onConnected: mediaAppId=" + s.this.v() + ",threadId=" + Thread.currentThread().getId() + ",name:" + Thread.currentThread().getName(), new Object[0]);
            try {
                s sVar = s.this;
                sVar.f6980i = new MediaControllerCompat(sVar.f6977f, s.this.f6979h.d());
                if (s.this.f6983l != null) {
                    s.this.f6980i.n(s.this.f6983l);
                    s.this.f6980i.k(s.this.f6983l);
                }
                s sVar2 = s.this;
                sVar2.f6981j = sVar2.f6977f.getPackageManager().getResourcesForApplication(s.this.f6980i.d());
            } catch (Exception e7) {
                h3.i.b("MediaSource").e(e7, e7.toString(), new Object[0]);
            }
            s.this.f6982k = false;
            s.this.I();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void b() {
            s.this.s();
            super.b();
            h3.i.b("MediaSource").d("onConnectionFailed: mediaAppId=" + s.this.v() + ",threadId=" + Thread.currentThread().getId(), new Object[0]);
            s.this.f6982k = false;
            s.this.J();
            if (s.this.f6983l != null && s.this.f6980i != null) {
                s.this.f6980i.n(s.this.f6983l);
            }
            s.this.f6980i = null;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void c() {
            s.this.s();
            super.c();
            h3.i.b("MediaSource").b("onConnectionSuspended: mediaAppId=" + s.this.v() + ",threadId=" + Thread.currentThread().getId(), new Object[0]);
            s.this.f6982k = false;
            if (s.this.f6983l != null && s.this.f6980i != null) {
                s.this.f6980i.n(s.this.f6983l);
            }
            s.this.f6980i = null;
            s.this.u();
            s.this.t();
            s.this.H();
        }
    }

    /* JADX INFO: compiled from: MediaSource.java */
    class c extends TimerTask {
        c() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            h3.i.b("MediaSource").b("reconnect  connecting = " + s.this.f6982k, new Object[0]);
            s.this.f6982k = false;
            s.this.u();
            s.this.t();
        }
    }

    public s(final Context context, String str, final String str2, Handler handler) {
        h3.i.b("MediaSource").i("packageName:%s, className:%s", str, str2);
        if (Looper.getMainLooper() == handler.getLooper()) {
            throw new IllegalArgumentException("don't use main looper handler");
        }
        this.f6977f = context;
        this.f6975d = str2;
        this.f6974c = str;
        B(str, str2);
        this.f6978g = str + "/" + str2;
        final ComponentName componentName = new ComponentName(str, str2);
        this.f6985n = handler;
        handler.post(new Runnable() { // from class: i3.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f6966e.G(str2, context, componentName);
            }
        });
    }

    private void B(String str, String str2) {
        int i7;
        h3.i.b("MediaSource").g("loadAllMediaSources start");
        PackageManager packageManager = this.f6977f.getPackageManager();
        Intent intent = new Intent("android.media.browse.MediaBrowserService");
        intent.setClassName(str, str2);
        ResolveInfo resolveInfoResolveService = packageManager.resolveService(intent, 128);
        if (resolveInfoResolveService == null) {
            h3.i.b("MediaSource").d("%s not found", str2);
            throw new RuntimeException(str2 + " not found");
        }
        ServiceInfo serviceInfo = resolveInfoResolveService.serviceInfo;
        Resources resourcesForApplication = null;
        ApplicationInfo applicationInfo = serviceInfo != null ? serviceInfo.applicationInfo : null;
        if (serviceInfo == null || applicationInfo == null) {
            h3.i.b("MediaSource").h("the serviceInfo or appInfo is null, ignore", new Object[0]);
            StringBuilder sb = new StringBuilder();
            sb.append("serviceInfo is null:");
            sb.append(serviceInfo == null);
            sb.append(", appInfo == null:");
            sb.append(applicationInfo == null);
            throw new RuntimeException(sb.toString());
        }
        try {
            resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
            this.f6981j = resourcesForApplication;
        } catch (PackageManager.NameNotFoundException e7) {
            h3.i.b("MediaSource").e(e7, "getResourcesForApplication", new Object[0]);
        }
        Bundle bundle = serviceInfo.metaData;
        if (bundle != null && (i7 = bundle.getInt("com.google.android.gms.car.notification.SmallIcon")) != 0 && resourcesForApplication != null) {
            this.f6976e = resourcesForApplication.getDrawable(i7);
        }
        if (str.equals(this.f6977f.getPackageName())) {
            this.f6972a = (String) serviceInfo.loadLabel(packageManager);
            this.f6973b = serviceInfo.loadIcon(packageManager);
        } else {
            this.f6972a = (String) packageManager.getApplicationLabel(applicationInfo);
            this.f6973b = packageManager.getApplicationIcon(applicationInfo);
        }
        h3.i.b("MediaSource").b("find system media source:" + this, new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C() {
        if (this.f6979h.e()) {
            I();
        } else {
            if (this.f6982k) {
                return;
            }
            this.f6982k = true;
            this.f6979h.a();
            M();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void D() {
        this.f6979h.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(Runnable runnable) {
        this.f6985n.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(Runnable runnable) {
        this.f6985n.post(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G(String str, Context context, ComponentName componentName) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f6979h = new MediaBrowserCompat(context, componentName, this.f6987p, null);
        t();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        Iterator<i3.a> it = this.f6984m.iterator();
        while (it.hasNext()) {
            it.next().a(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I() {
        Iterator<i3.a> it = this.f6984m.iterator();
        while (it.hasNext()) {
            it.next().c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J() {
        Iterator<i3.a> it = this.f6984m.iterator();
        while (it.hasNext()) {
            it.next().b(this);
        }
    }

    private synchronized void M() {
        h3.i.b("MediaSource").b("startTimer  connecting = " + this.f6982k, new Object[0]);
        Timer timer = new Timer();
        this.f6986o = timer;
        timer.schedule(new c(), 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void s() {
        h3.i.b("MediaSource").b("cancelTimer  connecting = " + this.f6982k, new Object[0]);
        try {
            Timer timer = this.f6986o;
            if (timer != null) {
                timer.cancel();
                this.f6986o.purge();
                this.f6986o = null;
            } else {
                h3.i.b("MediaSource").d("timer is null", new Object[0]);
            }
        } catch (Exception e7) {
            h3.i.b("MediaSource").e(e7, "cancelTimer", new Object[0]);
        }
        h3.i.b("MediaSource").b("cancelTimer success", new Object[0]);
    }

    public Resources A() {
        return this.f6981j;
    }

    void K(i3.a aVar) {
        this.f6984m.remove(aVar);
    }

    public void L(MediaControllerCompat.a aVar) {
        h3.l lVarB = h3.i.b("MediaSource");
        StringBuilder sb = new StringBuilder();
        sb.append("set MediaControllerCompat.Callback isConnected:");
        MediaBrowserCompat mediaBrowserCompat = this.f6979h;
        sb.append(mediaBrowserCompat == null ? "mMediaBrowser is null" : Boolean.valueOf(mediaBrowserCompat.e()));
        lVarB.b(sb.toString(), new Object[0]);
        this.f6983l = aVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f6974c.equals(sVar.f6974c) && this.f6975d.equals(sVar.f6975d);
    }

    public int hashCode() {
        return Objects.hash(this.f6974c, this.f6975d);
    }

    void r(i3.a aVar) {
        this.f6984m.addIfAbsent(aVar);
    }

    public void t() {
        this.f6985n.post(new Runnable() { // from class: i3.o
            @Override // java.lang.Runnable
            public final void run() {
                this.f6965e.C();
            }
        });
    }

    public String toString() {
        return "MediaSource{name='" + this.f6972a + "', mediaAppId='" + this.f6978g + "'}";
    }

    public void u() {
        this.f6985n.post(new Runnable() { // from class: i3.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f6964e.D();
            }
        });
    }

    public String v() {
        return this.f6978g;
    }

    public String w() {
        return this.f6975d;
    }

    public q4.e<MediaBrowserCompat> x() {
        MediaBrowserCompat mediaBrowserCompat = this.f6979h;
        return (mediaBrowserCompat == null || !mediaBrowserCompat.e()) ? q4.e.b(new a()).f(e5.a.a(new Executor() { // from class: i3.r
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f6971e.F(runnable);
            }
        })) : q4.e.d(this.f6979h).f(e5.a.a(new Executor() { // from class: i3.q
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f6970e.E(runnable);
            }
        }));
    }

    public MediaControllerCompat y() {
        return this.f6980i;
    }

    public String z() {
        return this.f6974c;
    }
}
