package androidx.emoji2.text;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import androidx.emoji2.text.d;
import androidx.lifecycle.ProcessLifecycleInitializer;
import defpackage.a01;
import defpackage.b71;
import defpackage.m91;
import defpackage.n6;
import defpackage.t70;
import defpackage.zw;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements a01<Boolean> {

    public static class a extends d.c {
        public a(Context context) {
            super(new b(context));
            this.b = 1;
        }
    }

    public static class b implements d.h {
        public final Context a;

        public b(Context context) {
            this.a = context.getApplicationContext();
        }

        @Override // androidx.emoji2.text.d.h
        public final void a(d.i iVar) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new zw("EmojiCompatInitializer"));
            threadPoolExecutor.allowCoreThreadTimeOut(true);
            threadPoolExecutor.execute(new m91(this, 1, iVar, threadPoolExecutor));
        }
    }

    public static class c implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            try {
                Trace.beginSection("EmojiCompat.EmojiCompatInitializer.run");
                if (d.k != null) {
                    d.a().c();
                }
            } finally {
                Trace.endSection();
            }
        }
    }

    @Override // defpackage.a01
    public final List<Class<? extends a01<?>>> a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // defpackage.a01
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Boolean b(Context context) {
        Object objB;
        a aVar = new a(context);
        if (d.k == null) {
            synchronized (d.j) {
                if (d.k == null) {
                    d.k = new d(aVar);
                }
            }
        }
        n6 n6VarC = n6.c(context);
        n6VarC.getClass();
        synchronized (n6.e) {
            try {
                objB = n6VarC.a.get(ProcessLifecycleInitializer.class);
                if (objB == null) {
                    objB = n6VarC.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        final androidx.lifecycle.g gVarJ1 = ((b71) objB).j1();
        gVarJ1.a(new t70() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // defpackage.t70
            public final void g(b71 b71Var) {
                EmojiCompatInitializer.this.getClass();
                Handler.createAsync(Looper.getMainLooper()).postDelayed(new c(), 500L);
                gVarJ1.b(this);
            }
        });
        return Boolean.TRUE;
    }
}
