package spi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class jc extends dt {

    @NotNull
    public dt e;

    public jc(@NotNull dt delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.e = delegate;
    }

    @Override // spi.dt
    @NotNull
    public final dt a() {
        return this.e.a();
    }

    @Override // spi.dt
    @NotNull
    public final dt b() {
        return this.e.b();
    }

    @Override // spi.dt
    public final long c() {
        return this.e.c();
    }

    @Override // spi.dt
    @NotNull
    public final dt d(long j) {
        return this.e.d(j);
    }

    @Override // spi.dt
    public final boolean e() {
        return this.e.e();
    }

    @Override // spi.dt
    public final void f() throws IOException {
        this.e.f();
    }

    @Override // spi.dt
    @NotNull
    public final dt g(long j) {
        TimeUnit unit = TimeUnit.MILLISECONDS;
        Intrinsics.checkNotNullParameter(unit, "unit");
        return this.e.g(j);
    }
}
