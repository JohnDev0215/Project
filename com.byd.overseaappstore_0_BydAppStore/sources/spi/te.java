package spi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.http2.ErrorCode;
import spi.re;

/* JADX INFO: loaded from: classes2.dex */
public final class te extends ts {
    public final /* synthetic */ cf e;
    public final /* synthetic */ re.e f;
    public final /* synthetic */ List g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public te(String str, boolean z, String str2, boolean z2, cf cfVar, re.e eVar, cf cfVar2, int i, List list, boolean z3) {
        super(str2, z2);
        this.e = cfVar;
        this.f = eVar;
        this.g = list;
    }

    @Override // spi.ts
    public final long a() {
        try {
            this.f.b.b.b(this.e);
            return -1L;
        } catch (IOException e) {
            Objects.requireNonNull(zk.c);
            zk zkVar = zk.a;
            StringBuilder sbK = e.k("Http2Connection.Listener failure for ");
            sbK.append(this.f.b.d);
            zkVar.i(sbK.toString(), 4, e);
            try {
                this.e.c(ErrorCode.PROTOCOL_ERROR, e);
                return -1L;
            } catch (IOException unused) {
                return -1L;
            }
        }
    }
}
