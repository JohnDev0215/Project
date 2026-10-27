package p;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Handler;
import java.nio.MappedByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class rv0 implements ic0 {
    public final Context a;
    public final pv0 b;
    public final u11 c;
    public final Object d;
    public Handler e;
    public Executor f;
    public ThreadPoolExecutor g;
    public vr2 h;
    public r40 i;

    public rv0(Context context, pv0 pv0Var) {
        u11 u11Var = sv0.d;
        this.d = new Object();
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        this.a = context.getApplicationContext();
        this.b = pv0Var;
        this.c = u11Var;
    }

    @Override // p.ic0
    public final void a(vr2 vr2Var) {
        synchronized (this.d) {
            this.h = vr2Var;
        }
        c();
    }

    public final void b() {
        synchronized (this.d) {
            try {
                this.h = null;
                r40 r40Var = this.i;
                if (r40Var != null) {
                    u11 u11Var = this.c;
                    Context context = this.a;
                    u11Var.getClass();
                    context.getContentResolver().unregisterContentObserver(r40Var);
                    this.i = null;
                }
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
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c() {
        synchronized (this.d) {
            try {
                if (this.h == null) {
                    return;
                }
                if (this.f == null) {
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new kw("emojiCompat"));
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                    this.g = threadPoolExecutor;
                    this.f = threadPoolExecutor;
                }
                final int i = 0;
                this.f.execute(new Runnable(this) { // from class: p.qv0
                    public final /* synthetic */ rv0 e;

                    {
                        this.e = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        switch (i) {
                            case 0:
                                rv0 rv0Var = this.e;
                                synchronized (rv0Var.d) {
                                    try {
                                        if (rv0Var.h == null) {
                                            return;
                                        }
                                        try {
                                            cw0 cw0VarD = rv0Var.d();
                                            int i2 = cw0VarD.e;
                                            if (i2 == 2) {
                                                synchronized (rv0Var.d) {
                                                }
                                            }
                                            if (i2 != 0) {
                                                throw new RuntimeException("fetchFonts result is not OK. (" + i2 + ")");
                                            }
                                            try {
                                                int i3 = lm2.a;
                                                km2.a("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                                                u11 u11Var = rv0Var.c;
                                                Context context = rv0Var.a;
                                                u11Var.getClass();
                                                Typeface typefaceP = ro2.a.p(context, new cw0[]{cw0VarD}, 0);
                                                MappedByteBuffer mappedByteBufferY = f60.y(rv0Var.a, cw0VarD.a);
                                                if (mappedByteBufferY == null || typefaceP == null) {
                                                    throw new RuntimeException("Unable to open file.");
                                                }
                                                try {
                                                    km2.a("EmojiCompat.MetadataRepo.create");
                                                    gh2 gh2Var = new gh2(typefaceP, ln1.v(mappedByteBufferY));
                                                    km2.b();
                                                    km2.b();
                                                    synchronized (rv0Var.d) {
                                                        try {
                                                            vr2 vr2Var = rv0Var.h;
                                                            if (vr2Var != null) {
                                                                vr2Var.o(gh2Var);
                                                            }
                                                        } finally {
                                                        }
                                                        break;
                                                    }
                                                    rv0Var.b();
                                                    return;
                                                } finally {
                                                    int i4 = lm2.a;
                                                    km2.b();
                                                }
                                            } catch (Throwable th) {
                                                throw th;
                                            }
                                            break;
                                        } catch (Throwable th2) {
                                            synchronized (rv0Var.d) {
                                                try {
                                                    vr2 vr2Var2 = rv0Var.h;
                                                    if (vr2Var2 != null) {
                                                        vr2Var2.n(th2);
                                                    }
                                                    rv0Var.b();
                                                    return;
                                                } finally {
                                                }
                                            }
                                        }
                                    } finally {
                                    }
                                }
                            default:
                                this.e.c();
                                return;
                        }
                    }
                });
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final cw0 d() throws Throwable {
        try {
            u11 u11Var = this.c;
            Context context = this.a;
            pv0 pv0Var = this.b;
            u11Var.getClass();
            j6 j6VarL = ux2.l(context, pv0Var);
            if (j6VarL.c != 0) {
                throw new RuntimeException(u4.l(new StringBuilder("fetchFonts failed ("), j6VarL.c, ")"));
            }
            cw0[] cw0VarArr = (cw0[]) j6VarL.e;
            if (cw0VarArr == null || cw0VarArr.length == 0) {
                throw new RuntimeException("fetchFonts failed (empty result)");
            }
            return cw0VarArr[0];
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("provider not found", e);
        }
    }
}
