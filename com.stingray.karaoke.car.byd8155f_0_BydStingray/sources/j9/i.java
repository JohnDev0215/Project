package j9;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class i extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f17524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ m f17525f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f17526g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f17527h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(String str, m mVar, int i7, int i10, int i11) {
        super(str, true);
        this.f17524e = i11;
        this.f17525f = mVar;
        this.f17526g = i7;
        this.f17527h = i10;
    }

    @Override // f9.a
    public final long a() {
        switch (this.f17524e) {
            case 0:
                int i7 = this.f17526g;
                int i10 = this.f17527h;
                m mVar = this.f17525f;
                mVar.getClass();
                try {
                    mVar.f17545H.m(i7, i10, true);
                    return -1L;
                } catch (IOException e10) {
                    mVar.b(2, 2, e10);
                    return -1L;
                }
            case 1:
                x xVar = this.f17525f.f17556v;
                int i11 = this.f17527h;
                xVar.getClass();
                A7.k.p(i11, "errorCode");
                synchronized (this.f17525f) {
                    this.f17525f.f17547J.remove(Integer.valueOf(this.f17526g));
                }
                return -1L;
            default:
                m mVar2 = this.f17525f;
                try {
                    int i12 = this.f17526g;
                    int i13 = this.f17527h;
                    mVar2.getClass();
                    A7.k.p(i13, "statusCode");
                    mVar2.f17545H.o(i12, i13);
                    return -1L;
                } catch (IOException e11) {
                    mVar2.b(2, 2, e11);
                    return -1L;
                }
        }
    }
}
