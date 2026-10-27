package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import java.util.List;
import kotlin.Metadata;
import u3.C2523a;
import u3.InterfaceC2524b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleInitializer;", "Lu3/b;", "Landroidx/lifecycle/A;", "<init>", "()V", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProcessLifecycleInitializer implements InterfaceC2524b {
    @Override // u3.InterfaceC2524b
    public final List a() {
        return n7.u.l;
    }

    @Override // u3.InterfaceC2524b
    public final Object b(Context context) {
        A7.m.f(context, "context");
        C2523a c2523aC = C2523a.c(context);
        A7.m.e(c2523aC, "getInstance(context)");
        if (!c2523aC.f22921b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml");
        }
        if (!AbstractC0985x.f12011a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            A7.m.d(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new C0984w());
        }
        ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.f11939s;
        processLifecycleOwner.getClass();
        processLifecycleOwner.f11942p = new Handler();
        processLifecycleOwner.f11943q.c1(EnumC0980s.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        A7.m.d(applicationContext2, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new N(processLifecycleOwner));
        return processLifecycleOwner;
    }
}
