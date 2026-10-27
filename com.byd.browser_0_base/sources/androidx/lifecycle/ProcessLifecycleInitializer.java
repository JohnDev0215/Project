package androidx.lifecycle;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import defpackage.a01;
import defpackage.b71;
import defpackage.gh0;
import defpackage.i21;
import defpackage.n6;
import java.util.List;

/* JADX INFO: compiled from: ProcessLifecycleInitializer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ProcessLifecycleInitializer implements a01<b71> {
    @Override // defpackage.a01
    public final List<Class<? extends a01<?>>> a() {
        return gh0.e;
    }

    @Override // defpackage.a01
    public final b71 b(Context context) {
        i21.e(context, "context");
        n6 n6VarC = n6.c(context);
        i21.d(n6VarC, "getInstance(context)");
        if (!n6VarC.b.contains(ProcessLifecycleInitializer.class)) {
            throw new IllegalStateException("ProcessLifecycleInitializer cannot be initialized lazily.\n               Please ensure that you have:\n               <meta-data\n                   android:name='androidx.lifecycle.ProcessLifecycleInitializer'\n                   android:value='androidx.startup' />\n               under InitializationProvider in your AndroidManifest.xml".toString());
        }
        if (!e.a.getAndSet(true)) {
            Context applicationContext = context.getApplicationContext();
            i21.c(applicationContext, "null cannot be cast to non-null type android.app.Application");
            ((Application) applicationContext).registerActivityLifecycleCallbacks(new e.a());
        }
        j jVar = j.l;
        jVar.getClass();
        jVar.i = new Handler();
        jVar.j.e(d.a.ON_CREATE);
        Context applicationContext2 = context.getApplicationContext();
        i21.c(applicationContext2, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext2).registerActivityLifecycleCallbacks(new i(jVar));
        return jVar;
    }
}
