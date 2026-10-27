package n6;

import N1.S;
import S8.AbstractC0727y;
import S8.k0;
import V8.AbstractC0828q;
import V8.f0;
import android.content.Context;
import com.stingray.karaoke.scoring.MicrophoneCommand;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import l3.C1764u;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f20127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1764u f20128b;
    public final f0 c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedList f20129e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f20130f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f20131g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C1963D f20132h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f20133i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final B2.P f20134j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final X8.c f20135k;
    public k0 l;

    /* JADX WARN: Multi-variable type inference failed */
    public u(Context context, z zVar) {
        A7.m.f(context, "context");
        A7.m.f(zVar, "midiManager");
        this.f20127a = zVar;
        C1764u c1764u = new C1764u(9, false);
        ArrayList arrayList = new ArrayList();
        c1764u.f18608m = new S(context);
        this.f20128b = c1764u;
        this.c = AbstractC0828q.c(Boolean.TRUE);
        this.d = -1.0f;
        this.f20129e = new LinkedList();
        this.f20130f = -1.0f;
        Z8.e eVar = S8.E.f8084a;
        X8.c cVarA = AbstractC0727y.a(X8.m.f10573a);
        this.f20135k = cVarA;
        this.f20132h = new C1963D(c1764u, new q(this, 0 == true ? 1 : 0), new q(this, 1));
        AbstractC0727y.w(cVarA, null, null, new r(this, null), 3);
        arrayList.add(new s());
        this.f20134j = new B2.P();
    }

    public final float a() {
        if (((Boolean) ((S) this.f20128b.f18608m).f4497a.getValue()).booleanValue() && !h6.n.f15593k) {
            return this.d;
        }
        if (this.f20134j != null) {
            return 0;
        }
        return 0.0f;
    }

    public final void b() {
        C1963D c1963d = this.f20132h;
        if (c1963d.f19992e) {
            return;
        }
        c1963d.f19990a.U(MicrophoneCommand.StartPitchCorrection);
        c1963d.d = new H5.f(c1963d, 5);
        Timer timer = new Timer();
        timer.schedule(c1963d.d, 0L, 100L);
        c1963d.c = timer;
        c1963d.f19992e = true;
    }

    public final void c() {
        C1963D c1963d = this.f20132h;
        if (c1963d.f19992e) {
            c1963d.f19990a.U(MicrophoneCommand.StopPitchCorrection);
            H5.f fVar = c1963d.d;
            if (fVar != null) {
                fVar.cancel();
            }
            Timer timer = c1963d.c;
            if (timer != null) {
                timer.cancel();
            }
            c1963d.d = null;
            c1963d.c = null;
            c1963d.f19992e = false;
        }
    }
}
