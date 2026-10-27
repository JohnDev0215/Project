package j9;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class l extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m f17535e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f17536f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ long f17537g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(String str, m mVar, int i7, long j3) {
        super(str, true);
        this.f17535e = mVar;
        this.f17536f = i7;
        this.f17537g = j3;
    }

    @Override // f9.a
    public final long a() {
        m mVar = this.f17535e;
        try {
            mVar.f17545H.q(this.f17537g, this.f17536f);
            return -1L;
        } catch (IOException e10) {
            mVar.b(2, 2, e10);
            return -1L;
        }
    }
}
