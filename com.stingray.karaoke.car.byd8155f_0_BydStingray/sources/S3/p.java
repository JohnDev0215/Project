package S3;

import S8.C0712i;
import h8.AbstractC1386c;

/* JADX INFO: loaded from: classes.dex */
public final class p implements O3.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0712i f7959b;

    public /* synthetic */ p(C0712i c0712i, int i7) {
        this.f7958a = i7;
        this.f7959b = c0712i;
    }

    @Override // O3.n
    public final void a(Object obj) {
        switch (this.f7958a) {
            case 0:
                C0712i c0712i = this.f7959b;
                if (!c0712i.w()) {
                    c0712i.resumeWith(obj);
                }
                break;
            default:
                Throwable th = (Throwable) obj;
                C0712i c0712i2 = this.f7959b;
                if (!c0712i2.w()) {
                    A7.m.c(th);
                    c0712i2.resumeWith(AbstractC1386c.f(th));
                }
                break;
        }
    }
}
