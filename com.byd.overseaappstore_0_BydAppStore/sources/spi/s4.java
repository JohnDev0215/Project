package spi;

import java.util.Timer;
import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class s4 {
    public Function1<? super Long, Unit> a;

    @Nullable
    public Timer b;

    @Nullable
    public TimerTask c;
    public final long d;
    public long e;

    public s4(long j, long j2) {
        this.d = j;
        this.e = j2;
    }

    public final void a() {
        Timer timer = this.b;
        if (timer != null) {
            timer.cancel();
        }
        this.b = null;
        TimerTask timerTask = this.c;
        if (timerTask != null) {
            timerTask.cancel();
        }
        this.c = null;
        ct.a("CountDownTimerTwoParams cancel", new Object[0]);
    }
}
