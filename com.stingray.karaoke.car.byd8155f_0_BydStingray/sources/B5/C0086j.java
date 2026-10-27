package B5;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.lifecycle.DefaultLifecycleObserver;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import x.AbstractC2641j;

/* JADX INFO: renamed from: B5.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0086j implements Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C0085i f714w;
    public C0083g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ExecutorService f715m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Boolean f716n;
    public Boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Boolean f717p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PackageInfo f718q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public AtomicBoolean f719r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public AtomicInteger f720s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AtomicBoolean f721t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public AtomicBoolean f722u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Boolean f723v;

    static {
        C0085i c0085i = new C0085i();
        c0085i.l = new C0084h(4);
        f714w = c0085i;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        Intent intent;
        r rVar = new r(0);
        C0083g c0083g = this.l;
        c0083g.e(rVar);
        if (!this.f723v.booleanValue()) {
            onCreate(f714w);
        }
        if (!this.o.booleanValue() || (intent = activity.getIntent()) == null || intent.getData() == null) {
            return;
        }
        z zVar = new z();
        Uri referrer = activity.getReferrer();
        if (referrer != null) {
            zVar.j(referrer.toString());
        }
        Uri data = intent.getData();
        try {
            for (String str : data.getQueryParameterNames()) {
                String queryParameter = data.getQueryParameter(str);
                if (queryParameter != null && !queryParameter.trim().isEmpty()) {
                    zVar.f(queryParameter, str);
                }
            }
        } catch (Exception e10) {
            C1.j jVar = c0083g.f699i;
            jVar.getClass();
            String string = data.toString();
            if (AbstractC2641j.c(jVar.l) >= 1) {
                h6.n.i("Analytics-LifecycleCallbacks", "failed to get uri params for " + string, e10);
            }
        }
        zVar.f(data.toString(), "url");
        c0083g.g("Deep Link Opened", zVar);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.l.e(new r(6));
        this.f723v.getClass();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.l.e(new r(3));
        this.f723v.getClass();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        this.l.e(new r(2));
        if (this.f723v.booleanValue()) {
            return;
        }
        onStart(f714w);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.l.e(new r(5));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        boolean zBooleanValue = this.f717p.booleanValue();
        C0083g c0083g = this.l;
        if (zBooleanValue) {
            c0083g.getClass();
            PackageManager packageManager = activity.getPackageManager();
            try {
                c0083g.f(packageManager.getActivityInfo(activity.getComponentName(), 128).loadLabel(packageManager).toString(), null);
            } catch (PackageManager.NameNotFoundException e10) {
                throw new AssertionError("Activity Not Found: " + e10.toString());
            } catch (Exception e11) {
                c0083g.f699i.l(e11, "Unable to track screen view for %s", activity.toString());
            }
        }
        c0083g.e(new r(1));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        this.l.e(new r(4));
        if (this.f723v.booleanValue()) {
            return;
        }
        onStop(f714w);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onCreate(androidx.lifecycle.A a10) {
        if (this.f719r.getAndSet(true) || !this.f716n.booleanValue()) {
            return;
        }
        this.f720s.set(0);
        this.f721t.set(true);
        C0083g c0083g = this.l;
        Application application = c0083g.f693a;
        PackageInfo packageInfoC = C0083g.c(application);
        String str = packageInfoC.versionName;
        int i7 = packageInfoC.versionCode;
        SharedPreferences sharedPreferencesD = D5.g.d(application, c0083g.f700j);
        String string = sharedPreferencesD.getString("version", null);
        int i10 = sharedPreferencesD.getInt("build", -1);
        if (i10 == -1) {
            z zVar = new z();
            zVar.k(str, "version");
            zVar.k(String.valueOf(i7), "build");
            c0083g.g("Application Installed", zVar);
        } else if (i7 != i10) {
            z zVar2 = new z();
            zVar2.k(str, "version");
            zVar2.k(String.valueOf(i7), "build");
            zVar2.k(string, "previous_version");
            zVar2.k(String.valueOf(i10), "previous_build");
            c0083g.g("Application Updated", zVar2);
        }
        SharedPreferences.Editor editorEdit = sharedPreferencesD.edit();
        editorEdit.putString("version", str);
        editorEdit.putInt("build", i7);
        editorEdit.apply();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onDestroy(androidx.lifecycle.A a10) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onPause(androidx.lifecycle.A a10) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume(androidx.lifecycle.A a10) {
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart(androidx.lifecycle.A a10) {
        if (this.f716n.booleanValue() && this.f720s.incrementAndGet() == 1 && !this.f722u.get()) {
            z zVar = new z();
            AtomicBoolean atomicBoolean = this.f721t;
            if (atomicBoolean.get()) {
                PackageInfo packageInfo = this.f718q;
                zVar.k(packageInfo.versionName, "version");
                zVar.k(String.valueOf(packageInfo.versionCode), "build");
            }
            zVar.k(Boolean.valueOf(true ^ atomicBoolean.getAndSet(false)), "from_background");
            this.l.g("Application Opened", zVar);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStop(androidx.lifecycle.A a10) {
        if (this.f716n.booleanValue() && this.f720s.decrementAndGet() == 0 && !this.f722u.get()) {
            this.l.g("Application Backgrounded", null);
        }
    }
}
