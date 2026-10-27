package spi;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import spi.vl;

/* JADX INFO: loaded from: classes2.dex */
public final class p6 {
    public int a;
    public int b;
    public ExecutorService c;
    public final ArrayDeque<vl.a> d;
    public final ArrayDeque<vl.a> e;
    public final ArrayDeque<vl> f;

    public p6() {
        this.a = 64;
        this.b = 5;
        this.d = new ArrayDeque<>();
        this.e = new ArrayDeque<>();
        this.f = new ArrayDeque<>();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public p6(@NotNull ExecutorService executorService) {
        this();
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        this.c = executorService;
    }

    public final <T> void a(Deque<T> deque, T t) {
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            Unit unit = Unit.INSTANCE;
        }
        c();
    }

    public final void b(@NotNull vl.a call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.a.decrementAndGet();
        a(this.e, call);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean c() {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.p6.c():boolean");
    }
}
