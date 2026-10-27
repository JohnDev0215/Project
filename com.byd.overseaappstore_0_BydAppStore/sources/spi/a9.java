package spi;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import spi.tn;

/* JADX INFO: loaded from: classes2.dex */
public final class a9 {
    public tn.b a;
    public tn b;
    public int c;
    public int d;
    public int e;
    public rn f;
    public final am g;

    @NotNull
    public final t h;
    public final vl i;
    public final u8 j;

    public a9(@NotNull am connectionPool, @NotNull t address, @NotNull vl call, @NotNull u8 eventListener) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.g = connectionPool;
        this.h = address;
        this.i = call;
        this.j = eventListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02a0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0074  */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.util.ArrayList, java.util.List<spi.rn>] */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.util.ArrayList, java.util.List<spi.rn>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.wl a(int r16, int r17, int r18, int r19, boolean r20, boolean r21) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 850
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.a9.a(int, int, int, int, boolean, boolean):spi.wl");
    }

    public final boolean b(@NotNull hf url) {
        Intrinsics.checkNotNullParameter(url, "url");
        hf hfVar = this.h.a;
        return url.f == hfVar.f && Intrinsics.areEqual(url.e, hfVar.e);
    }

    public final void c(@NotNull IOException e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.f = null;
        if ((e instanceof StreamResetException) && ((StreamResetException) e).errorCode == ErrorCode.REFUSED_STREAM) {
            this.c++;
        } else if (e instanceof ConnectionShutdownException) {
            this.d++;
        } else {
            this.e++;
        }
    }
}
