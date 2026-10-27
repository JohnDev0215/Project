package d2;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RequestManagerRetriever.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements Handler.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final b f5633i = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile com.bumptech.glide.k f5634a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f5637d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b f5638e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Map<FragmentManager, k> f5635b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Map<androidx.fragment.app.FragmentManager, o> f5636c = new HashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final n.a<View, Fragment> f5639f = new n.a<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final n.a<View, android.app.Fragment> f5640g = new n.a<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Bundle f5641h = new Bundle();

    /* JADX INFO: compiled from: RequestManagerRetriever.java */
    class a implements b {
        a() {
        }

        @Override // d2.l.b
        public com.bumptech.glide.k a(com.bumptech.glide.c cVar, h hVar, m mVar, Context context) {
            return new com.bumptech.glide.k(cVar, hVar, mVar, context);
        }
    }

    /* JADX INFO: compiled from: RequestManagerRetriever.java */
    public interface b {
        com.bumptech.glide.k a(com.bumptech.glide.c cVar, h hVar, m mVar, Context context);
    }

    public l(b bVar) {
        this.f5638e = bVar == null ? f5633i : bVar;
        this.f5637d = new Handler(Looper.getMainLooper(), this);
    }

    @TargetApi(17)
    private static void a(Activity activity) {
        if (activity.isDestroyed()) {
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

    @TargetApi(26)
    @Deprecated
    private void c(FragmentManager fragmentManager, n.a<View, android.app.Fragment> aVar) {
        for (android.app.Fragment fragment : fragmentManager.getFragments()) {
            if (fragment.getView() != null) {
                aVar.put(fragment.getView(), fragment);
                c(fragment.getChildFragmentManager(), aVar);
            }
        }
    }

    private static void d(Collection<Fragment> collection, Map<View, Fragment> map) {
        if (collection == null) {
            return;
        }
        for (Fragment fragment : collection) {
            if (fragment != null && fragment.W() != null) {
                map.put(fragment.W(), fragment);
                d(fragment.r().s0(), map);
            }
        }
    }

    @Deprecated
    private android.app.Fragment e(View view, Activity activity) {
        this.f5640g.clear();
        c(activity.getFragmentManager(), this.f5640g);
        View viewFindViewById = activity.findViewById(R.id.content);
        android.app.Fragment fragment = null;
        while (!view.equals(viewFindViewById) && (fragment = this.f5640g.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.f5640g.clear();
        return fragment;
    }

    private Fragment f(View view, androidx.fragment.app.d dVar) {
        this.f5639f.clear();
        d(dVar.v().s0(), this.f5639f);
        View viewFindViewById = dVar.findViewById(R.id.content);
        Fragment fragment = null;
        while (!view.equals(viewFindViewById) && (fragment = this.f5639f.get(view)) == null && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        this.f5639f.clear();
        return fragment;
    }

    @Deprecated
    private com.bumptech.glide.k g(Context context, FragmentManager fragmentManager, android.app.Fragment fragment, boolean z6) {
        k kVarP = p(fragmentManager, fragment, z6);
        com.bumptech.glide.k kVarE = kVarP.e();
        if (kVarE != null) {
            return kVarE;
        }
        com.bumptech.glide.k kVarA = this.f5638e.a(com.bumptech.glide.c.c(context), kVarP.c(), kVarP.f(), context);
        kVarP.k(kVarA);
        return kVarA;
    }

    private com.bumptech.glide.k n(Context context) {
        if (this.f5634a == null) {
            synchronized (this) {
                if (this.f5634a == null) {
                    this.f5634a = this.f5638e.a(com.bumptech.glide.c.c(context.getApplicationContext()), new d2.b(), new g(), context.getApplicationContext());
                }
            }
        }
        return this.f5634a;
    }

    private k p(FragmentManager fragmentManager, android.app.Fragment fragment, boolean z6) {
        k kVar = (k) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (kVar == null && (kVar = this.f5635b.get(fragmentManager)) == null) {
            kVar = new k();
            kVar.j(fragment);
            if (z6) {
                kVar.c().d();
            }
            this.f5635b.put(fragmentManager, kVar);
            fragmentManager.beginTransaction().add(kVar, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.f5637d.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return kVar;
    }

    private o r(androidx.fragment.app.FragmentManager fragmentManager, Fragment fragment, boolean z6) {
        o oVar = (o) fragmentManager.h0("com.bumptech.glide.manager");
        if (oVar == null && (oVar = this.f5636c.get(fragmentManager)) == null) {
            oVar = new o();
            oVar.X1(fragment);
            if (z6) {
                oVar.P1().d();
            }
            this.f5636c.put(fragmentManager, oVar);
            fragmentManager.l().e(oVar, "com.bumptech.glide.manager").j();
            this.f5637d.obtainMessage(2, fragmentManager).sendToTarget();
        }
        return oVar;
    }

    private static boolean s(Context context) {
        Activity activityB = b(context);
        return activityB == null || !activityB.isFinishing();
    }

    private com.bumptech.glide.k t(Context context, androidx.fragment.app.FragmentManager fragmentManager, Fragment fragment, boolean z6) {
        o oVarR = r(fragmentManager, fragment, z6);
        com.bumptech.glide.k kVarR1 = oVarR.R1();
        if (kVarR1 != null) {
            return kVarR1;
        }
        com.bumptech.glide.k kVarA = this.f5638e.a(com.bumptech.glide.c.c(context), oVarR.P1(), oVarR.S1(), context);
        oVarR.Y1(kVarA);
        return kVarA;
    }

    public com.bumptech.glide.k h(Activity activity) {
        if (k2.k.p()) {
            return j(activity.getApplicationContext());
        }
        a(activity);
        return g(activity, activity.getFragmentManager(), null, s(activity));
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        Object obj;
        Object objRemove;
        Object obj2;
        int i7 = message.what;
        Object obj3 = null;
        boolean z6 = true;
        if (i7 == 1) {
            obj = (FragmentManager) message.obj;
            objRemove = this.f5635b.remove(obj);
        } else {
            if (i7 != 2) {
                z6 = false;
                obj2 = null;
                if (z6 && obj3 == null && Log.isLoggable("RMRetriever", 5)) {
                    Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
                }
                return z6;
            }
            obj = (androidx.fragment.app.FragmentManager) message.obj;
            objRemove = this.f5636c.remove(obj);
        }
        Object obj4 = obj;
        obj3 = objRemove;
        obj2 = obj4;
        if (z6) {
            Log.w("RMRetriever", "Failed to remove expected request manager fragment, manager: " + obj2);
        }
        return z6;
    }

    @TargetApi(17)
    @Deprecated
    public com.bumptech.glide.k i(android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        }
        if (k2.k.p()) {
            return j(fragment.getActivity().getApplicationContext());
        }
        return g(fragment.getActivity(), fragment.getChildFragmentManager(), fragment, fragment.isVisible());
    }

    public com.bumptech.glide.k j(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (k2.k.q() && !(context instanceof Application)) {
            if (context instanceof androidx.fragment.app.d) {
                return m((androidx.fragment.app.d) context);
            }
            if (context instanceof Activity) {
                return h((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                ContextWrapper contextWrapper = (ContextWrapper) context;
                if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                    return j(contextWrapper.getBaseContext());
                }
            }
        }
        return n(context);
    }

    public com.bumptech.glide.k k(View view) {
        if (k2.k.p()) {
            return j(view.getContext().getApplicationContext());
        }
        k2.j.d(view);
        k2.j.e(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity activityB = b(view.getContext());
        if (activityB == null) {
            return j(view.getContext().getApplicationContext());
        }
        if (!(activityB instanceof androidx.fragment.app.d)) {
            android.app.Fragment fragmentE = e(view, activityB);
            return fragmentE == null ? h(activityB) : i(fragmentE);
        }
        androidx.fragment.app.d dVar = (androidx.fragment.app.d) activityB;
        Fragment fragmentF = f(view, dVar);
        return fragmentF != null ? l(fragmentF) : m(dVar);
    }

    public com.bumptech.glide.k l(Fragment fragment) {
        k2.j.e(fragment.s(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (k2.k.p()) {
            return j(fragment.s().getApplicationContext());
        }
        return t(fragment.s(), fragment.r(), fragment, fragment.m0());
    }

    public com.bumptech.glide.k m(androidx.fragment.app.d dVar) {
        if (k2.k.p()) {
            return j(dVar.getApplicationContext());
        }
        a(dVar);
        return t(dVar, dVar.v(), null, s(dVar));
    }

    @Deprecated
    k o(Activity activity) {
        return p(activity.getFragmentManager(), null, s(activity));
    }

    o q(Context context, androidx.fragment.app.FragmentManager fragmentManager) {
        return r(fragmentManager, null, s(context));
    }
}
