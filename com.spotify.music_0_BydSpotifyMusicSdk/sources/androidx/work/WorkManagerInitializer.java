package androidx.work;

import android.content.Context;
import java.util.Collections;
import java.util.List;
import p.n41;
import p.qo;
import p.sw;
import p.vc1;
import p.wx2;

/* JADX INFO: loaded from: classes.dex */
public final class WorkManagerInitializer implements n41 {
    static {
        vc1.d("WrkMgrInitializer");
    }

    @Override // p.n41
    public final List a() {
        return Collections.emptyList();
    }

    @Override // p.n41
    public final Object b(Context context) {
        vc1.c().getClass();
        sw swVar = new sw(new qo());
        synchronized (wx2.s) {
            try {
                wx2 wx2Var = wx2.q;
                if (wx2Var != null && wx2.r != null) {
                    throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");
                }
                if (wx2Var == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (wx2.r == null) {
                        wx2.r = androidx.work.impl.a.a(applicationContext, swVar);
                    }
                    wx2.q = wx2.r;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return wx2.E(context);
    }
}
