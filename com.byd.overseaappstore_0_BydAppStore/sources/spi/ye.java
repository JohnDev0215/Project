package spi;

import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import spi.ol;

/* JADX INFO: loaded from: classes2.dex */
public final class ye extends ts {
    public final /* synthetic */ re e;
    public final /* synthetic */ int f;
    public final /* synthetic */ ErrorCode g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ye(String str, boolean z, String str2, boolean z2, re reVar, int i, ErrorCode errorCode) {
        super(str2, z2);
        this.e = reVar;
        this.f = i;
        this.g = errorCode;
    }

    @Override // spi.ts
    public final long a() {
        ol.a.C0137a c0137a = this.e.l;
        ErrorCode errorCode = this.g;
        Objects.requireNonNull(c0137a);
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        synchronized (this.e) {
            this.e.A.remove(Integer.valueOf(this.f));
            Unit unit = Unit.INSTANCE;
        }
        return -1L;
    }
}
