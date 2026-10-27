package j9;

import java.io.IOException;
import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public final class j extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ m f17528e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f17529f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ C2319i f17530g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f17531h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(String str, m mVar, int i7, C2319i c2319i, int i10, boolean z6) {
        super(str, true);
        this.f17528e = mVar;
        this.f17529f = i7;
        this.f17530g = c2319i;
        this.f17531h = i10;
    }

    @Override // f9.a
    public final long a() {
        try {
            x xVar = this.f17528e.f17556v;
            C2319i c2319i = this.f17530g;
            int i7 = this.f17531h;
            xVar.getClass();
            c2319i.R(i7);
            this.f17528e.f17545H.o(this.f17529f, 9);
            synchronized (this.f17528e) {
                this.f17528e.f17547J.remove(Integer.valueOf(this.f17529f));
            }
            return -1L;
        } catch (IOException unused) {
            return -1L;
        }
    }
}
