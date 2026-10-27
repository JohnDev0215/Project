package spi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import spi.ol;

/* JADX INFO: loaded from: classes2.dex */
public final class we extends ts {
    public final /* synthetic */ re e;
    public final /* synthetic */ int f;
    public final /* synthetic */ List g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public we(String str, boolean z, String str2, boolean z2, re reVar, int i, List list, boolean z3) {
        super(str2, z2);
        this.e = reVar;
        this.f = i;
        this.g = list;
    }

    @Override // spi.ts
    public final long a() {
        ol.a.C0137a c0137a = this.e.l;
        List responseHeaders = this.g;
        Objects.requireNonNull(c0137a);
        Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
        try {
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
