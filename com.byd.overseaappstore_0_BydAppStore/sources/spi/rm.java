package spi;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.b;
import com.bumptech.glide.manager.RequestManagerFragment;
import com.bumptech.glide.manager.SupportRequestManagerFragment;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class rm implements Handler.Callback {
    public static final a g = new a();
    public volatile qm a;

    @VisibleForTesting
    public final Map<FragmentManager, RequestManagerFragment> b = new HashMap();

    @VisibleForTesting
    public final Map<androidx.fragment.app.FragmentManager, SupportRequestManagerFragment> c = new HashMap();
    public final Handler d;
    public final b e;
    public final tc f;

    public class a implements b {
        @Override // spi.rm.b
        @NonNull
        public final qm a(@NonNull com.bumptech.glide.a aVar, @NonNull sg sgVar, @NonNull sm smVar, @NonNull Context context) {
            return new qm(aVar, sgVar, smVar, context);
        }
    }

    public interface b {
        @NonNull
        qm a(@NonNull com.bumptech.glide.a aVar, @NonNull sg sgVar, @NonNull sm smVar, @NonNull Context context);
    }

    public rm(@Nullable b bVar, com.bumptech.glide.d dVar) {
        new ArrayMap();
        new ArrayMap();
        new Bundle();
        this.e = bVar == null ? g : bVar;
        this.d = new Handler(Looper.getMainLooper(), this);
        this.f = (he.g && he.f) ? dVar.a(b.e.class) ? new ac() : new bc() : new t6();
    }

    @Nullable
    public static Activity a(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return a(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @NonNull
    public final qm b(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (tu.i() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return c((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                Activity activity = (Activity) context;
                if (tu.h()) {
                    return b(activity.getApplicationContext());
                }
                if (activity instanceof FragmentActivity) {
                    return c((FragmentActivity) activity);
                }
                if (activity.isDestroyed()) {
                    throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
                }
                this.f.a();
                FragmentManager fragmentManager = activity.getFragmentManager();
                Activity activityA = a(activity);
                boolean z = activityA == null || !activityA.isFinishing();
                RequestManagerFragment requestManagerFragmentD = d(fragmentManager);
                qm qmVar = requestManagerFragmentD.d;
                if (qmVar != null) {
                    return qmVar;
                }
                qm qmVarA = this.e.a(com.bumptech.glide.a.b(activity), requestManagerFragmentD.a, requestManagerFragmentD.b, activity);
                if (z) {
                    qmVarA.onStart();
                }
                requestManagerFragmentD.d = qmVarA;
                return qmVarA;
            }
            if (context instanceof ContextWrapper) {
                ContextWrapper contextWrapper = (ContextWrapper) context;
                if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                    return b(contextWrapper.getBaseContext());
                }
            }
        }
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    this.a = this.e.a(com.bumptech.glide.a.b(context.getApplicationContext()), new j0(), new d8(), context.getApplicationContext());
                }
            }
        }
        return this.a;
    }

    @NonNull
    public final qm c(@NonNull FragmentActivity fragmentActivity) {
        if (tu.h()) {
            return b(fragmentActivity.getApplicationContext());
        }
        if (fragmentActivity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
        this.f.a();
        androidx.fragment.app.FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        Activity activityA = a(fragmentActivity);
        boolean z = activityA == null || !activityA.isFinishing();
        SupportRequestManagerFragment supportRequestManagerFragmentE = e(supportFragmentManager);
        qm qmVarA = supportRequestManagerFragmentE.e;
        if (qmVarA == null) {
            qmVarA = this.e.a(com.bumptech.glide.a.b(fragmentActivity), supportRequestManagerFragmentE.a, supportRequestManagerFragmentE.b, fragmentActivity);
            if (z) {
                qmVarA.onStart();
            }
            supportRequestManagerFragmentE.e = qmVarA;
        }
        return qmVarA;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<android.app.FragmentManager, com.bumptech.glide.manager.RequestManagerFragment>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashMap, java.util.Map<android.app.FragmentManager, com.bumptech.glide.manager.RequestManagerFragment>] */
    @NonNull
    public final RequestManagerFragment d(@NonNull FragmentManager fragmentManager) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) this.b.get(fragmentManager);
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        RequestManagerFragment requestManagerFragment2 = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (requestManagerFragment2 == null) {
            requestManagerFragment2 = new RequestManagerFragment();
            requestManagerFragment2.f = null;
            this.b.put(fragmentManager, requestManagerFragment2);
            fragmentManager.beginTransaction().add(requestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.d.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return requestManagerFragment2;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.Map<androidx.fragment.app.FragmentManager, com.bumptech.glide.manager.SupportRequestManagerFragment>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.HashMap, java.util.Map<androidx.fragment.app.FragmentManager, com.bumptech.glide.manager.SupportRequestManagerFragment>] */
    @NonNull
    public final SupportRequestManagerFragment e(@NonNull androidx.fragment.app.FragmentManager fragmentManager) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) this.c.get(fragmentManager);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        SupportRequestManagerFragment supportRequestManagerFragment2 = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment2 == null) {
            supportRequestManagerFragment2 = new SupportRequestManagerFragment();
            supportRequestManagerFragment2.f = null;
            this.c.put(fragmentManager, supportRequestManagerFragment2);
            fragmentManager.beginTransaction().add(supportRequestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
            this.d.obtainMessage(2, fragmentManager).sendToTarget();
        }
        return supportRequestManagerFragment2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0132  */
    /* JADX WARN: Type inference failed for: r2v21, types: [java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.HashMap, java.util.Map<android.app.FragmentManager, com.bumptech.glide.manager.RequestManagerFragment>] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.util.HashMap, java.util.Map<androidx.fragment.app.FragmentManager, com.bumptech.glide.manager.SupportRequestManagerFragment>] */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean handleMessage(android.os.Message r18) {
        /*
            Method dump skipped, instruction units count: 353
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.rm.handleMessage(android.os.Message):boolean");
    }
}
