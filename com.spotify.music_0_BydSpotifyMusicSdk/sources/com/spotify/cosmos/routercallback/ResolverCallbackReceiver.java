package com.spotify.cosmos.routercallback;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.util.c;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class ResolverCallbackReceiver<T> {
    public static final Companion Companion = new Companion(null);
    private static final AtomicInteger ID_GEN = new AtomicInteger(0);
    private final Handler handler;
    private final int id = Companion.createId();

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int createId() {
            return ResolverCallbackReceiver.ID_GEN.getAndIncrement();
        }

        public final <T> ResolverCallbackReceiver<T> forAny(final Handler handler, final g gVar, final g gVar2) {
            v03.j(gVar, "onResolved");
            v03.j(gVar2, "onError");
            return new ResolverCallbackReceiver<T>(handler) { // from class: com.spotify.cosmos.routercallback.ResolverCallbackReceiver$Companion$forAny$1
                @Override // com.spotify.cosmos.routercallback.ResolverCallbackReceiver
                public void onError(Throwable th) {
                    v03.j(th, "error");
                    try {
                        gVar2.accept(th);
                    } catch (Throwable th2) {
                        RuntimeException runtimeExceptionF = c.f(th2);
                        v03.i(runtimeExceptionF, "wrapOrThrow(e)");
                        throw runtimeExceptionF;
                    }
                }

                @Override // com.spotify.cosmos.routercallback.ResolverCallbackReceiver
                public void onResolved(T t) {
                    v03.j(t, "response");
                    try {
                        gVar.accept(t);
                    } catch (Throwable th) {
                        RuntimeException runtimeExceptionF = c.f(th);
                        v03.i(runtimeExceptionF, "wrapOrThrow(e)");
                        throw runtimeExceptionF;
                    }
                }
            };
        }

        public final <T> ResolverCallbackReceiver<T> forUI(g gVar, g gVar2) {
            v03.j(gVar, "onResolved");
            v03.j(gVar2, "onError");
            return forAny(new Handler(Looper.getMainLooper()), gVar, gVar2);
        }
    }

    public final class LocalRunnable implements Runnable {
        private final T response;

        public LocalRunnable(T t) {
            this.response = t;
        }

        @Override // java.lang.Runnable
        public void run() {
            ResolverCallbackReceiver.this.onResolved(this.response);
        }
    }

    public ResolverCallbackReceiver(Handler handler) {
        this.handler = handler;
    }

    public static final <T> ResolverCallbackReceiver<T> forAny(Handler handler, g gVar, g gVar2) {
        return Companion.forAny(handler, gVar, gVar2);
    }

    public static final <T> ResolverCallbackReceiver<T> forUI(g gVar, g gVar2) {
        return Companion.forUI(gVar, gVar2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ResolverCallbackReceiver) && ((ResolverCallbackReceiver) obj).id == this.id;
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public final int getId() {
        return this.id;
    }

    public int hashCode() {
        return this.id;
    }

    public abstract void onError(Throwable th);

    public abstract void onResolved(T t);

    public final void sendOnError(final Throwable th) {
        v03.j(th, "error");
        Handler handler = this.handler;
        if (handler != null) {
            handler.post(new Runnable(this) { // from class: com.spotify.cosmos.routercallback.ResolverCallbackReceiver.sendOnError.1
                final /* synthetic */ ResolverCallbackReceiver<T> this$0;

                {
                    this.this$0 = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.this$0.onError(th);
                }
            });
        } else {
            onError(th);
        }
    }

    public void sendOnResolved(T t) {
        Handler handler = this.handler;
        if (handler != null) {
            handler.post(new LocalRunnable(t));
        } else {
            onResolved(t);
        }
    }
}
