package com.bumptech.glide.manager;

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
import androidx.fragment.app.FragmentActivity;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RequestManagerRetriever.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements Handler.Callback {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b f3434b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile com.bumptech.glide.h f3435c;
    private final Handler f;
    private final b g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Map<FragmentManager, RequestManagerFragment> f3436d = new HashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Map<androidx.fragment.app.g, n> f3437e = new HashMap();
    private final b.d.a<View, Fragment> h = new b.d.a<>();
    private final b.d.a<View, android.app.Fragment> i = new b.d.a<>();
    private final Bundle j = new Bundle();

    /* JADX INFO: compiled from: RequestManagerRetriever.java */
    class a implements b {
        a() {
        }

        @Override // com.bumptech.glide.manager.k.b
        public com.bumptech.glide.h a(com.bumptech.glide.c cVar, h hVar, l lVar, Context context) {
            return new com.bumptech.glide.h(cVar, hVar, lVar, context);
        }
    }

    /* JADX INFO: compiled from: RequestManagerRetriever.java */
    public interface b {
        com.bumptech.glide.h a(com.bumptech.glide.c cVar, h hVar, l lVar, Context context);
    }

    public k(b bVar) {
        this.g = bVar == null ? f3434b : bVar;
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
    private com.bumptech.glide.h c(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        RequestManagerFragment requestManagerFragmentI = i(fragmentManager, fragment, z);
        com.bumptech.glide.h hVarE = requestManagerFragmentI.e();
        if (hVarE != null) {
            return hVarE;
        }
        com.bumptech.glide.h hVarA = this.g.a(com.bumptech.glide.c.c(context), requestManagerFragmentI.c(), requestManagerFragmentI.f(), context);
        requestManagerFragmentI.k(hVarA);
        return hVarA;
    }

    private com.bumptech.glide.h g(Context context) {
        if (this.f3435c == null) {
            synchronized (this) {
                if (this.f3435c == null) {
                    this.f3435c = this.g.a(com.bumptech.glide.c.c(context.getApplicationContext()), new com.bumptech.glide.manager.b(), new g(), context.getApplicationContext());
                }
            }
        }
        return this.f3435c;
    }

    private RequestManagerFragment i(FragmentManager fragmentManager, android.app.Fragment fragment, boolean z) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (requestManagerFragment == null && (requestManagerFragment = this.f3436d.get(fragmentManager)) == null) {
            requestManagerFragment = new RequestManagerFragment();
            requestManagerFragment.j(fragment);
            if (z) {
                requestManagerFragment.c().d();
            }
            this.f3436d.put(fragmentManager, requestManagerFragment);
            fragmentManager.beginTransaction().add(requestManagerFragment, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return requestManagerFragment;
    }

    private n k(androidx.fragment.app.g gVar, Fragment fragment, boolean z) {
        n nVar = (n) gVar.d("com.bumptech.glide.manager");
        if (nVar == null && (nVar = this.f3437e.get(gVar)) == null) {
            nVar = new n();
            nVar.J1(fragment);
            if (z) {
                nVar.B1().d();
            }
            this.f3437e.put(gVar, nVar);
            gVar.a().c(nVar, "com.bumptech.glide.manager").g();
            this.f.obtainMessage(2, gVar).sendToTarget();
        }
        return nVar;
    }

    private static boolean l(Context context) {
        Activity activityB = b(context);
        return activityB == null || !activityB.isFinishing();
    }

    private com.bumptech.glide.h m(Context context, androidx.fragment.app.g gVar, Fragment fragment, boolean z) {
        n nVarK = k(gVar, fragment, z);
        com.bumptech.glide.h hVarD1 = nVarK.D1();
        if (hVarD1 != null) {
            return hVarD1;
        }
        com.bumptech.glide.h hVarA = this.g.a(com.bumptech.glide.c.c(context), nVarK.B1(), nVarK.E1(), context);
        nVarK.K1(hVarA);
        return hVarA;
    }

    public com.bumptech.glide.h d(Activity activity) {
        if (com.bumptech.glide.o.k.o()) {
            return e(activity.getApplicationContext());
        }
        a(activity);
        return c(activity, activity.getFragmentManager(), null, l(activity));
    }

    public com.bumptech.glide.h e(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (com.bumptech.glide.o.k.p() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return f((FragmentActivity) context);
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

    public com.bumptech.glide.h f(FragmentActivity fragmentActivity) {
        if (com.bumptech.glide.o.k.o()) {
            return e(fragmentActivity.getApplicationContext());
        }
        a(fragmentActivity);
        return m(fragmentActivity, fragmentActivity.x(), null, l(fragmentActivity));
    }

    @Deprecated
    RequestManagerFragment h(Activity activity) {
        return i(activity.getFragmentManager(), null, l(activity));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object obj;
        Object objRemove;
        Object obj2;
        int i = message.what;
        Object obj3 = null;
        boolean z = true;
        if (i == 1) {
            obj = (FragmentManager) message.obj;
            objRemove = this.f3436d.remove(obj);
        } else {
            if (i != 2) {
                z = false;
                obj2 = null;
                if (z && obj3 == null && Log.isLoggable("RMRetriever", 5)) {
                    Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
                }
                return z;
            }
            obj = (androidx.fragment.app.g) message.obj;
            objRemove = this.f3437e.remove(obj);
        }
        Object obj4 = obj;
        obj3 = objRemove;
        obj2 = obj4;
        if (z) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
        }
        return z;
    }

    n j(Context context, androidx.fragment.app.g gVar) {
        return k(gVar, null, l(context));
    }
}
