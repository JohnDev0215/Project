package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import java.util.List;
import kotlin.collections.EmptyList;
import p.jx1;
import p.lx1;
import p.n41;
import p.n91;
import p.o91;
import p.qa;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements n41 {
    @Override // p.n41
    public final List a() {
        return EmptyList.c;
    }

    @Override // p.n41
    public final Object b(Context context) {
        v03.j(context, "context");
        qa qaVarC = qa.c(context);
        v03.i(qaVarC, "getInstance(context)");
        if (!qaVarC.b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml".toString());
        }
        if (!o91.a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            v03.g(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new n91());
        }
        lx1 lx1Var = lx1.q;
        lx1Var.getClass();
        lx1Var.m = new Handler();
        lx1Var.n.f(Lifecycle$Event.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        v03.g(applicationContext2, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new jx1(lx1Var));
        return lx1Var;
    }
}
