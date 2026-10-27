package spi;

import java.io.File;
import java.io.FileNotFoundException;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class km extends mm {
    public final /* synthetic */ File a;
    public final /* synthetic */ mi b;

    public km(File file, mi miVar) {
        this.a = file;
        this.b = miVar;
    }

    @Override // spi.mm
    public final long contentLength() {
        return this.a.length();
    }

    @Override // spi.mm
    @Nullable
    public final mi contentType() {
        return this.b;
    }

    @Override // spi.mm
    public final void writeTo(@NotNull k2 sink) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        vr vrVarL = b.l(this.a);
        try {
            sink.x(vrVarL);
            CloseableKt.closeFinally(vrVarL, null);
        } finally {
        }
    }
}
