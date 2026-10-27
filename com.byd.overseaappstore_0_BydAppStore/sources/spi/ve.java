package spi;

import java.io.IOException;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import spi.ol;

/* JADX INFO: loaded from: classes2.dex */
public final class ve extends ts {
    public final /* synthetic */ re e;
    public final /* synthetic */ int f;
    public final /* synthetic */ g2 g;
    public final /* synthetic */ int h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ve(String str, boolean z, String str2, boolean z2, re reVar, int i, g2 g2Var, int i2, boolean z3) {
        super(str2, z2);
        this.e = reVar;
        this.f = i;
        this.g = g2Var;
        this.h = i2;
    }

    @Override // spi.ts
    public final long a() {
        try {
            ol.a.C0137a c0137a = this.e.l;
            g2 source = this.g;
            int i = this.h;
            Objects.requireNonNull(c0137a);
            Intrinsics.checkNotNullParameter(source, "source");
            source.skip(i);
            this.e.y.G(this.f, ErrorCode.CANCEL);
            synchronized (this.e) {
                this.e.A.remove(Integer.valueOf(this.f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}
