package e7;

import B5.A;
import B5.RunnableC0079c;
import M.t;
import b7.l;
import d7.h;
import d7.o;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

/* JADX INFO: renamed from: e7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1232b extends o {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final Logger f14470A = Logger.getLogger(AbstractC1232b.class.getName());

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f14471z;

    @Override // d7.o
    public final void a1() {
        h hVar = new h(this, 1);
        int i7 = this.f13553w;
        Logger logger = f14470A;
        if (i7 == 2) {
            logger.fine("transport open - closing");
            hVar.q(new Object[0]);
        } else {
            logger.fine("transport not open - deferring close");
            U0("open", hVar);
        }
    }

    @Override // d7.o
    public final void b1() {
        d1();
    }

    @Override // d7.o
    public final void c1(f7.b[] bVarArr) {
        this.f13545n = false;
        t tVar = new t(this, new RunnableC0079c(this, 13));
        l lVar = f7.d.f14643a;
        if (bVarArr.length == 0) {
            tVar.b("0:");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int length = bVarArr.length;
        int i7 = 0;
        while (i7 < length) {
            boolean z6 = i7 == length + (-1);
            f7.b bVar = bVarArr[i7];
            A a10 = new A(sb, z6);
            Object obj = bVar.f14642b;
            if (obj instanceof byte[]) {
                try {
                    a10.b("b".concat(new String(io.sentry.config.a.Z((byte[]) obj), "US-ASCII")));
                } catch (UnsupportedEncodingException e10) {
                    throw new AssertionError(e10);
                }
            } else {
                f7.d.b(bVar, a10);
            }
            i7++;
        }
        tVar.b(sb.toString());
    }

    public final void d1() {
        f14470A.fine("polling");
        this.f14471z = true;
        C1235e c1235e = (C1235e) this;
        C1235e.B.fine("xhr poll");
        C1234d c1234dE1 = c1235e.e1(null);
        c1234dE1.T0("data", new C1233c(c1235e, 3));
        c1234dE1.T0("error", new C1233c(c1235e, 4));
        c1234dE1.a1();
        N0("poll", new Object[0]);
    }
}
