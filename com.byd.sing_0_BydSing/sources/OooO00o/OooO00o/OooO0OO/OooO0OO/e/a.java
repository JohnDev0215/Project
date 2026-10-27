package OooO00o.OooO00o.OooO0Oo.OooO0Oo.e;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.g;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.i;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.l;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f139a = 240;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f140b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g f141c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.a f142d;
    public Timer e;
    public boolean f;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.a$a, reason: collision with other inner class name */
    public static final class C0010a {
        public C0010a() {
        }

        public /* synthetic */ C0010a(f fVar) {
            this();
        }
    }

    public final class b extends TimerTask {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ a f143b;

        public b(a aVar) {
            h.d(aVar, "this$0");
            this.f143b = aVar;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (this.f143b.e == null) {
                return;
            }
            this.f143b.l();
        }
    }

    static {
        new C0010a(null);
    }

    public final void b() {
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.a aVar = this.f142d;
        Object obj = null;
        if (aVar != null) {
            g gVarG = g();
            if (gVarG != null) {
                gVarG.a(aVar);
                obj = gVarG;
            }
            if (obj == null) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("BaseModel", "proxy is null");
                obj = l.f4084a;
            }
        }
        if (obj == null) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("BaseModel", "device is null");
        }
    }

    public final void c(int i) {
        this.f139a = i;
    }

    public final void d(g gVar) {
        this.f141c = gVar;
    }

    public final void e(i iVar) {
        h.d(iVar, "callback");
        this.f140b = iVar;
    }

    public final void f(OooO00o.OooO00o.OooO0Oo.OooO0Oo.e.c.a aVar) {
        this.f142d = aVar;
    }

    public final g g() {
        return this.f141c;
    }

    public final int h() {
        return this.f139a;
    }

    public final i i() {
        return this.f140b;
    }

    public void j() {
        Timer timer = this.e;
        if (timer != null) {
            timer.purge();
        }
        Timer timer2 = this.e;
        if (timer2 != null) {
            timer2.cancel();
        }
        this.e = null;
        this.f = false;
    }

    public void k() {
        if (this.f) {
            return;
        }
        Timer timer = new Timer();
        this.e = timer;
        b bVar = new b(this);
        long j = 3600000 * ((long) this.f139a);
        timer.schedule(bVar, 300000 + j, j);
        this.f = true;
    }

    public void l() {
        throw null;
    }
}
