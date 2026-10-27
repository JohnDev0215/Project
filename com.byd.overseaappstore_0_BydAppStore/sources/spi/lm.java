package spi;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class lm extends mm {
    public final /* synthetic */ ByteString a;
    public final /* synthetic */ mi b;

    public lm(ByteString byteString, mi miVar) {
        this.a = byteString;
        this.b = miVar;
    }

    @Override // spi.mm
    public final long contentLength() {
        return this.a.size();
    }

    @Override // spi.mm
    @Nullable
    public final mi contentType() {
        return this.b;
    }

    @Override // spi.mm
    public final void writeTo(@NotNull k2 sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        sink.u(this.a);
    }
}
