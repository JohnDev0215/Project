package b.c.a.h.e;

import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class a extends TimerTask {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b f1791a;

    public a(b bVar) {
        this.f1791a = bVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        StringBuilder sbD = b.a.a.a.a.d("timerTask cancel msgId=");
        sbD.append(this.f1791a.h);
        b.c.a.h.b.b(sbD.toString());
        b bVar = this.f1791a;
        bVar.f1794c.cancel(bVar.h);
    }
}
