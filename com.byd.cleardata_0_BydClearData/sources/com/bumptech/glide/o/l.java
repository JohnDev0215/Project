package com.bumptech.glide.o;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class l implements Handler.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b f2469b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile com.bumptech.glide.k f2470c;
    private final Handler f;
    private final b g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Map<FragmentManager, k> f2471d = new HashMap();
    final Map<androidx.fragment.app.i, o> e = new HashMap();
    private final a.d.a<View, Fragment> h = new a.d.a<>();
    private final a.d.a<View, android.app.Fragment> i = new a.d.a<>();
    private final Bundle j = new Bundle();

    class a implements b {
        a() {
        }

        @Override // com.bumptech.glide.o.l.b
        public com.bumptech.glide.k a(com.bumptech.glide.c cVar, h hVar, m mVar, Context context) {
            return new com.bumptech.glide.k(cVar, hVar, mVar, context);
        }
    }

    public interface b {
        com.bumptech.glide.k a(com.bumptech.glide.c cVar, h hVar, m mVar, Context context);
    }

    public l(b bVar) {
        this.g = bVar == null ? f2469b : bVar;
        this.f = new Handler(Looper.getMainLooper(), this);
    }

    @TargetApi(17)
    private static void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    private static Activity b(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return b(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @Deprecated
    private com.bumptech.glide.k c(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        k kVarI = i(fragmentManager, fragment, z);
        com.bumptech.glide.k kVarE = kVarI.e();
        if (kVarE != null) {
            return kVarE;
        }
        com.bumptech.glide.k kVarA = this.g.a(com.bumptech.glide.c.c(context), kVarI.c(), kVarI.f(), context);
        kVarI.k(kVarA);
        return kVarA;
    }

    private com.bumptech.glide.k g(Context context) {
        if (this.f2470c == null) {
            synchronized (this) {
                if (this.f2470c == null) {
                    this.f2470c = this.g.a(com.bumptech.glide.c.c(context.getApplicationContext()), new com.bumptech.glide.o.b(), new g(), context.getApplicationContext());
                }
            }
        }
        return this.f2470c;
    }

    private k i(FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        k kVar = (k) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (kVar == null && (kVar = this.f2471d.get(fragmentManager)) == null) {
            kVar = new k();
            kVar.j(fragment);
            if (z) {
                kVar.c().d();
            }
            this.f2471d.put(fragmentManager, kVar);
            fragmentManager.beginTransaction().add(kVar, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return kVar;
    }

    private o k(androidx.fragment.app.i iVar, Fragment fragment, boolean z) {
        o oVar = (o) iVar.c("com.bumptech.glide.manager");
        if (oVar == null && (oVar = this.e.get(iVar)) == null) {
            oVar = new o();
            oVar.J1(fragment);
            if (z) {
                oVar.B1().d();
            }
            this.e.put(iVar, oVar);
            iVar.a().b(oVar, "com.bumptech.glide.manager").e();
            this.f.obtainMessage(2, iVar).sendToTarget();
        }
        return oVar;
    }

    private static boolean l(Context context) {
        Activity activityB = b(context);
        return activityB == null || !activityB.isFinishing();
    }

    private com.bumptech.glide.k m(Context context, androidx.fragment.app.i iVar, Fragment fragment, boolean z) {
        o oVarK = k(iVar, fragment, z);
        com.bumptech.glide.k kVarD1 = oVarK.D1();
        if (kVarD1 != null) {
            return kVarD1;
        }
        com.bumptech.glide.k kVarA = this.g.a(com.bumptech.glide.c.c(context), oVarK.B1(), oVarK.E1(), context);
        oVarK.K1(kVarA);
        return kVarA;
    }

    public com.bumptech.glide.k d(Activity activity) {
        if (com.bumptech.glide.t.k.o()) {
            return e(activity.getApplicationContext());
        }
        a(activity);
        return c(activity, activity.getFragmentManager(), null, l(activity));
    }

    public com.bumptech.glide.k e(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (com.bumptech.glide.t.k.p() && !(context instanceof Application)) {
            if (context instanceof androidx.fragment.app.d) {
                return f((androidx.fragment.app.d) context);
            }
            if (context instanceof Activity) {
                return d((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                ContextWrapper contextWrapper = (ContextWrapper) context;
                if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                    return e(contextWrapper.getBaseContext());
                }
            }
        }
        return g(context);
    }

    public com.bumptech.glide.k f(androidx.fragment.app.d dVar) {
        if (com.bumptech.glide.t.k.o()) {
            return e(dVar.getApplicationContext());
        }
        a(dVar);
        return m(dVar, dVar.y(), null, l(dVar));
    }

    @Deprecated
    k h(Activity activity) {
        return i(activity.getFragmentManager(), null, l(activity));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object obj;
        Map map;
        Object obj2;
        int i = message.what;
        Object objRemove = null;
        boolean z = true;
        if (i == 1) {
            obj = (FragmentManager) message.obj;
            map = this.f2471d;
        } else {
            if (i != 2) {
                z = false;
                obj2 = null;
                if (z && objRemove == null && Log.isLoggable("RMRetriever", 5)) {
                    Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
                }
                return z;
            }
            obj = (androidx.fragment.app.i) message.obj;
            map = this.e;
        }
        Object obj3 = obj;
        objRemove = map.remove(obj);
        obj2 = obj3;
        if (z) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
        }
        return z;
    }

    o j(Context context, androidx.fragment.app.i iVar) {
        return k(iVar, null, l(context));
    }
}
