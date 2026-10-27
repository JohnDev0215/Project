package spi;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public class dt {

    @JvmField
    @NotNull
    public static final a d;
    public boolean a;
    public long b;
    public long c;

    public static final class a extends dt {
        @Override // spi.dt
        @NotNull
        public final dt d(long j) {
            return this;
        }

        @Override // spi.dt
        public final void f() {
        }

        @Override // spi.dt
        @NotNull
        public final dt g(long j) {
            TimeUnit unit = TimeUnit.MILLISECONDS;
            Intrinsics.checkNotNullParameter(unit, "unit");
            return this;
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new b(null);
        d = new a();
    }

    @NotNull
    public dt a() {
        this.a = false;
        return this;
    }

    @NotNull
    public dt b() {
        this.c = 0L;
        return this;
    }

    public long c() {
        if (this.a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline".toString());
    }

    @NotNull
    public dt d(long j) {
        this.a = true;
        this.b = j;
        return this;
    }

    public boolean e() {
        return this.a;
    }

    public void f() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    @NotNull
    public dt g(long j) {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (!(j >= 0)) {
            throw new IllegalArgumentException(d.d("timeout < 0: ", j).toString());
        }
        this.c = unit.toNanos(j);
        return this;
    }
}
