package p;

import android.content.Context;
import androidx.work.WorkerParameters;

/* JADX INFO: loaded from: classes.dex */
public final class ty2 {
    public static final String a = vc1.d("WorkerFactory");

    public static qb1 a(Context context, String str, WorkerParameters workerParameters) {
        Class clsAsSubclass;
        String str2 = a;
        qb1 qb1Var = null;
        try {
            clsAsSubclass = Class.forName(str).asSubclass(qb1.class);
        } catch (Throwable th) {
            vc1.c().b(str2, "Invalid class: " + str, th);
            clsAsSubclass = null;
        }
        if (clsAsSubclass != null) {
            try {
                qb1Var = (qb1) clsAsSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context, workerParameters);
            } catch (Throwable th2) {
                vc1.c().b(str2, "Could not instantiate " + str, th2);
            }
        }
        if (qb1Var == null || !qb1Var.l) {
            return qb1Var;
        }
        throw new IllegalStateException("WorkerFactory (" + ty2.class.getName() + ") returned an instance of a ListenableWorker (" + str + ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
    }
}
