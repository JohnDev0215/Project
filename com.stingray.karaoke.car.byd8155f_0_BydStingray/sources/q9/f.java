package q9;

import A7.m;
import java.io.IOException;
import java.net.SocketTimeoutException;
import r9.C2322l;

/* JADX INFO: loaded from: classes.dex */
public final class f extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ g f21362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ long f21363f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(String str, g gVar, long j3) {
        super(str, true);
        this.f21362e = gVar;
        this.f21363f = j3;
    }

    @Override // f9.a
    public final long a() {
        j jVar;
        g gVar = this.f21362e;
        synchronized (gVar) {
            try {
                if (!gVar.f21380t && (jVar = gVar.f21372j) != null) {
                    int i7 = gVar.f21382v ? gVar.f21381u : -1;
                    gVar.f21381u++;
                    gVar.f21382v = true;
                    if (i7 != -1) {
                        StringBuilder sb = new StringBuilder("sent ping but didn't receive pong within ");
                        sb.append(gVar.c);
                        sb.append("ms (after ");
                        gVar.c(new SocketTimeoutException(M5.b.h(sb, i7 - 1, " successful ping/pongs)")));
                    } else {
                        try {
                            C2322l c2322l = C2322l.o;
                            m.f(c2322l, "payload");
                            jVar.b(9, c2322l);
                        } catch (IOException e10) {
                            gVar.c(e10);
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return this.f21363f;
    }
}
