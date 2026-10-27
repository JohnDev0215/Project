package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.OooO00o;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0o extends OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f556b = d.b(OooO0OO.f559OooO00o);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Timer f557c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f558d;

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<OooO00o> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f559OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final OooO00o invoke() {
            return OooO00o.f542a.a();
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public final class b extends TimerTask {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ OooO0o f560b;

        public b(OooO0o oooO0o) {
            h.d(oooO0o, "this$0");
            this.f560b = oooO0o;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (h.a(OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0.f531a.g(), "1")) {
                if ((this.f560b.f558d > 0 ? this : null) == null) {
                    return;
                }
                OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a.t();
                return;
            }
            if (this.f560b.f557c == null) {
                return;
            }
            OooO0o oooO0o = this.f560b;
            int i = oooO0o.f558d;
            oooO0o.f558d = i + 1;
            if (i < 10) {
                oooO0o.n();
                return;
            }
            OooO00o.OooO00o.OooO0O0.b.f41a.d("SoNormalMode", "start times = " + oooO0o.f558d + ", retry too many times, stopCheck");
            oooO0o.o();
        }
    }

    static {
        new a(null);
    }

    public OooO0o() {
        c(new a.a.d.a.a.a.b());
    }

    @Override // a.a.d.c.b
    public void a(int i) {
    }

    @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a
    public void d() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("SoNormalMode", " ------> START_STOP, start");
        super.d();
        k().g(h(1));
        m();
    }

    @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0o0.a
    public void e() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("SoNormalMode", " ------> START_STOP, stop");
        super.e();
        o();
        k().g(h(2));
        this.f558d = 0;
    }

    public final OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0 h(int i) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("SoNormalMode", h.i(" ------> createCommand, START_STOP, status = ", Integer.valueOf(i)));
        return OooO0O0.a.c(OooO0O0.a.d(new OooO0O0.a(), null, 1).a(5).j(0).f(OooO0O0.OooO0o.WRITE).e(OooO0O0.OooO0OO.NONE), i, 0, 2).i();
    }

    public final OooO00o k() {
        return (OooO00o) this.f556b.getValue();
    }

    public final void m() {
        if (this.f557c != null) {
            o();
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.d("SoNormalMode", "startCheck");
        Timer timer = new Timer();
        this.f557c = timer;
        timer.schedule(new b(this), 500L, 30000L);
    }

    public final void n() {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("SoNormalMode", "retry start so");
        k().g(h(1));
    }

    public final void o() {
        OooO00o.OooO00o.OooO0O0.b.f41a.d("SoNormalMode", "stopCheck");
        Timer timer = this.f557c;
        if (timer != null) {
            timer.purge();
        }
        Timer timer2 = this.f557c;
        if (timer2 != null) {
            timer2.cancel();
        }
        this.f557c = null;
    }
}
