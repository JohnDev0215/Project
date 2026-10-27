package defpackage;

import android.R;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: SkinActivityLifecycle.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bo2 implements Application.ActivityLifecycleCallbacks {
    public static volatile bo2 d;
    public WeakHashMap<Context, jo2> a;
    public WeakHashMap<Context, a> b;
    public WeakReference<Activity> c;

    /* JADX INFO: compiled from: SkinActivityLifecycle.java */
    public class a implements ep2 {
        public final Context a;
        public boolean b = false;

        public a(Context context) {
            this.a = context;
        }

        @Override // defpackage.ep2
        public final void a() {
            WeakReference<Activity> weakReference = bo2.this.c;
            if (weakReference != null) {
                Activity activity = weakReference.get();
                Context context = this.a;
                if (context != activity && (context instanceof Activity)) {
                    this.b = true;
                    return;
                }
            }
            b();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public final void b() {
            Context context = this.a;
            if (context == 0) {
                return;
            }
            boolean z = context instanceof Activity;
            bo2 bo2Var = bo2.this;
            if (z) {
                bo2Var.getClass();
                if (bo2.c(context)) {
                    bo2.d((Activity) context);
                }
            }
            CopyOnWriteArrayList<WeakReference> copyOnWriteArrayList = bo2Var.b(context).g;
            if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                for (WeakReference weakReference : copyOnWriteArrayList) {
                    if (weakReference != null && weakReference.get() != null) {
                        ((ro2) weakReference.get()).e();
                    }
                }
            }
            if (context instanceof ro2) {
                ((ro2) context).e();
            }
            this.b = false;
        }
    }

    public bo2(Application application) {
        application.registerActivityLifecycleCallbacks(this);
        try {
            LayoutInflater.from(application).setFactory2(b(application));
        } catch (Throwable unused) {
        }
        no2.o.a(a(application));
    }

    public static boolean c(Context context) {
        return no2.o.m || context.getClass().getAnnotation(mp2.class) != null || (context instanceof ro2);
    }

    public static void d(Activity activity) {
        Drawable drawableC;
        if (no2.o.n) {
            int iA = uo2.a(activity, new int[]{R.attr.windowBackground});
            if (vd1.b(iA) == 0 || (drawableC = po2.c(activity, iA)) == null) {
                return;
            }
            activity.getWindow().setBackgroundDrawable(drawableC);
        }
    }

    public final a a(Context context) {
        if (this.b == null) {
            this.b = new WeakHashMap<>();
        }
        a aVar = this.b.get(context);
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a(context);
        this.b.put(context, aVar2);
        return aVar2;
    }

    public final jo2 b(Context context) {
        if (this.a == null) {
            this.a = new WeakHashMap<>();
        }
        jo2 jo2Var = this.a.get(context);
        if (jo2Var != null) {
            return jo2Var;
        }
        jo2 jo2Var2 = new jo2(context);
        this.a.put(context, jo2Var2);
        return jo2Var2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (c(activity)) {
            try {
                LayoutInflater.from(activity).setFactory2(b(activity));
            } catch (Throwable unused) {
            }
            d(activity);
            if (activity instanceof ro2) {
                ((ro2) activity).e();
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (c(activity)) {
            no2 no2Var = no2.o;
            a aVarA = a(activity);
            synchronized (no2Var) {
                ((ArrayList) no2Var.e).remove(aVarA);
            }
            this.b.remove(activity);
            this.a.remove(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        this.c = new WeakReference<>(activity);
        if (c(activity)) {
            a aVarA = a(activity);
            no2.o.a(aVarA);
            if (aVarA.b) {
                aVarA.b();
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
