package c.a.d.f;

import android.content.Context;
import java.util.Objects;

/* JADX INFO: compiled from: OtaTestBroadcastImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2470a = "k";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2471b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.a.a f2472c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.g.c f2473d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.d.a f2474e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.a.b f2475f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.g.d f2476g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.b.c.a f2477h = c.a.b.c.a.k();

    public k(Context context, c.a.a.a aVar, c.a.g.c cVar, c.a.d.a aVar2, c.a.a.b bVar, c.a.g.d dVar) {
        this.f2471b = context;
        this.f2472c = aVar;
        this.f2473d = cVar;
        this.f2474e = aVar2;
        this.f2475f = bVar;
        this.f2476g = dVar;
    }

    public void a(String str) {
        c.a.b.d.e.c(f2470a, "dealOtaTestBroadcast->methodName = " + str);
        str.hashCode();
        switch (str) {
            case "quitOtaModel":
                this.f2472c.f();
                return;
            case "onlyTest":
                this.f2474e.m();
                return;
            case "reboot":
                this.f2474e.e1();
                return;
            case "deleteOtaDir":
                this.f2476g.f(false);
                this.f2472c.m(false, "OtaTestBroadcastImpl deleteOtaDir");
                c.a.b.d.d.g(c.a.b.b.a.f2320a);
                return;
            case "killApp":
                throw new RuntimeException("Kill myself");
            case "setUpdateFlagFalse":
                this.f2476g.f(false);
                this.f2472c.m(false, "OtaTestBroadcastImpl setUpdateFlagFalse");
                return;
            case "showDialog":
                c.a.b.d.l lVarD = c.a.b.d.l.d();
                c.a.d.a aVar = this.f2474e;
                Objects.requireNonNull(aVar);
                lVarD.b(new a(aVar));
                return;
            case "setUpdateFlagTrue":
                this.f2477h.X(0);
                this.f2477h.W(0);
                this.f2476g.f(true);
                return;
            case "setScreenOn":
                this.f2474e.v();
                return;
            case "setScreenOff":
                this.f2474e.E("OtaTestBroadcastImpl setScreenOff");
                return;
            default:
                return;
        }
    }
}
