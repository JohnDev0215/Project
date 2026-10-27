package androidx.emoji2.text;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import androidx.emoji2.text.d;
import defpackage.oo0;
import defpackage.po0;
import defpackage.wo0;
import defpackage.wq0;
import defpackage.xo0;
import defpackage.zw;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: FontRequestEmojiCompatConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends d.c {
    public static final a d = new a();

    /* JADX INFO: compiled from: FontRequestEmojiCompatConfig.java */
    public static class a {
    }

    /* JADX INFO: compiled from: FontRequestEmojiCompatConfig.java */
    public static class b implements d.h {
        public final Context a;
        public final po0 b;
        public final a c;
        public final Object d;
        public Handler e;
        public Executor f;
        public ThreadPoolExecutor g;
        public d.i h;

        public b(Context context, po0 po0Var) {
            a aVar = g.d;
            this.d = new Object();
            if (context == null) {
                throw new NullPointerException("Context cannot be null");
            }
            this.a = context.getApplicationContext();
            this.b = po0Var;
            this.c = aVar;
        }

        @Override // androidx.emoji2.text.d.h
        public final void a(d.i iVar) {
            synchronized (this.d) {
                this.h = iVar;
            }
            c();
        }

        public final void b() {
            synchronized (this.d) {
                this.h = null;
                Handler handler = this.e;
                if (handler != null) {
                    handler.removeCallbacks(null);
                }
                this.e = null;
                ThreadPoolExecutor threadPoolExecutor = this.g;
                if (threadPoolExecutor != null) {
                    threadPoolExecutor.shutdown();
                }
                this.f = null;
                this.g = null;
            }
        }

        public final void c() {
            synchronized (this.d) {
                if (this.h == null) {
                    return;
                }
                if (this.f == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new zw("emojiCompat"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.g = threadPoolExecutor;
                    this.f = threadPoolExecutor;
                }
                this.f.execute(new wq0(4, this));
            }
        }

        public final xo0 d() {
            try {
                a aVar = this.c;
                Context context = this.a;
                po0 po0Var = this.b;
                aVar.getClass();
                wo0 wo0VarA = oo0.a(context, po0Var);
                int i = wo0VarA.a;
                if (i != 0) {
                    throw new RuntimeException("fetchFonts failed (" + i + ")");
                }
                xo0[] xo0VarArr = wo0VarA.b;
                if (xo0VarArr == null || xo0VarArr.length == 0) {
                    throw new RuntimeException("fetchFonts failed (empty result)");
                }
                return xo0VarArr[0];
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException("provider not found", e);
            }
        }
    }

    public g(Context context, po0 po0Var) {
        super(new b(context, po0Var));
    }
}
