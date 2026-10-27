package com.amazon.music.alps.util;

import android.os.CountDownTimer;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CountDownTimerHelper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0002J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H&J\b\u0010\t\u001a\u00020\u0002H&R\"\u0010\n\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000bR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R$\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00148\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0016\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/alps/util/CountDownTimerHelper;", "", "", "recreateTimer", AsmString.METHOD_FIREBASE_PERF_TRACE_START, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "", "msRemaining", "onTicked", "onFinished", "msLeftUntilFinished", "J", "getMsLeftUntilFinished", "()J", "setMsLeftUntilFinished", "(J)V", "tickIntervalMs", "Landroid/os/CountDownTimer;", "timer", "Landroid/os/CountDownTimer;", "", "<set-?>", "isRunning", "Z", "()Z", "<init>", "(JJ)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public abstract class CountDownTimerHelper {
    private boolean isRunning;
    private long msLeftUntilFinished;
    private final long tickIntervalMs;
    private CountDownTimer timer;

    public abstract void onFinished();

    public abstract void onTicked(long msRemaining);

    public CountDownTimerHelper(long j, long j2) {
        this.msLeftUntilFinished = j;
        this.tickIntervalMs = j2;
    }

    public final void setMsLeftUntilFinished(long j) {
        this.msLeftUntilFinished = j;
    }

    public final void start() {
        recreateTimer();
        CountDownTimer countDownTimer = this.timer;
        Intrinsics.checkNotNull(countDownTimer);
        countDownTimer.start();
        this.isRunning = true;
    }

    public final void stop() {
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            Intrinsics.checkNotNull(countDownTimer);
            countDownTimer.cancel();
            this.timer = null;
        }
        this.isRunning = false;
    }

    private final void recreateTimer() {
        stop();
        this.timer = new CountDownTimer(this.msLeftUntilFinished, this.tickIntervalMs) { // from class: com.amazon.music.alps.util.CountDownTimerHelper.recreateTimer.1
            @Override // android.os.CountDownTimer
            public void onTick(long msRemaining) {
                CountDownTimerHelper.this.setMsLeftUntilFinished(msRemaining);
                CountDownTimerHelper.this.onTicked(msRemaining);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                CountDownTimerHelper.this.isRunning = false;
                CountDownTimerHelper.this.setMsLeftUntilFinished(0L);
                CountDownTimerHelper.this.onFinished();
            }
        };
    }
}
