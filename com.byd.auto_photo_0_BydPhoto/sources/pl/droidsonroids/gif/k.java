package pl.droidsonroids.gif;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
class k extends l {
    k(c cVar) {
        super(cVar);
    }

    @Override // pl.droidsonroids.gif.l
    public void a() {
        c cVar = this.f3203a;
        long jR = cVar.g.r(cVar.f);
        if (jR >= 0) {
            this.f3203a.f3188c = SystemClock.uptimeMillis() + jR;
            if (this.f3203a.isVisible() && this.f3203a.f3187b) {
                c cVar2 = this.f3203a;
                if (!cVar2.l) {
                    cVar2.f3186a.remove(this);
                    c cVar3 = this.f3203a;
                    cVar3.p = cVar3.f3186a.schedule(this, jR, TimeUnit.MILLISECONDS);
                }
            }
            if (!this.f3203a.h.isEmpty() && this.f3203a.b() == this.f3203a.g.i() - 1) {
                c cVar4 = this.f3203a;
                cVar4.m.sendEmptyMessageAtTime(cVar4.c(), this.f3203a.f3188c);
            }
        } else {
            c cVar5 = this.f3203a;
            cVar5.f3188c = Long.MIN_VALUE;
            cVar5.f3187b = false;
        }
        if (!this.f3203a.isVisible() || this.f3203a.m.hasMessages(-1)) {
            return;
        }
        this.f3203a.m.sendEmptyMessageAtTime(-1, 0L);
    }
}
