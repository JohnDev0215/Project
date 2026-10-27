package com.amazon.music.skyfire.ui.widgets.helpers;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.plugin.util.AsmString;
import com.google.firebase.perf.util.Constants;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: RepeatingUiTimer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\fH\u0002J\u0006\u0010\u0018\u001a\u00020\fJ\u0006\u0010\u0019\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0004¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/helpers/RepeatingUiTimer;", "", "timeoutMillis", "", "(I)V", "_timeoutMillis", "handler", "Landroid/os/Handler;", Constants.ENABLE_DISABLE, "Ljava/util/concurrent/atomic/AtomicBoolean;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lkotlin/Function1;", "", "getListener", "()Lkotlin/jvm/functions/Function1;", "setListener", "(Lkotlin/jvm/functions/Function1;)V", "runnable", "Ljava/lang/Runnable;", "value", "getTimeoutMillis", "()I", "setTimeoutMillis", "reschedule", AsmString.METHOD_FIREBASE_PERF_TRACE_START, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RepeatingUiTimer {
    private int _timeoutMillis;
    private final Handler handler;
    private final AtomicBoolean isEnabled;
    private Function1<? super RepeatingUiTimer, Unit> listener;
    private final Runnable runnable;

    public RepeatingUiTimer() {
        this(0, 1, null);
    }

    public RepeatingUiTimer(int i) {
        this.isEnabled = new AtomicBoolean(false);
        this.handler = new Handler(Looper.getMainLooper());
        this.runnable = new Runnable() { // from class: com.amazon.music.skyfire.ui.widgets.helpers.RepeatingUiTimer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RepeatingUiTimer.m2252runnable$lambda0(this.f$0);
            }
        };
        this._timeoutMillis = i;
    }

    public /* synthetic */ RepeatingUiTimer(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: runnable$lambda-0, reason: not valid java name */
    public static final void m2252runnable$lambda0(RepeatingUiTimer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super RepeatingUiTimer, Unit> function1 = this$0.listener;
        if (function1 != null) {
            function1.invoke(this$0);
        }
        if (this$0.isEnabled.get()) {
            this$0.reschedule();
        }
    }

    public final Function1<RepeatingUiTimer, Unit> getListener() {
        return this.listener;
    }

    public final void setListener(Function1<? super RepeatingUiTimer, Unit> function1) {
        this.listener = function1;
    }

    /* JADX INFO: renamed from: getTimeoutMillis, reason: from getter */
    public final int get_timeoutMillis() {
        return this._timeoutMillis;
    }

    public final void setTimeoutMillis(int i) {
        if (this._timeoutMillis != i) {
            this._timeoutMillis = RangesKt___RangesKt.coerceAtLeast(i, 0);
            if (this.isEnabled.get()) {
                reschedule();
            }
        }
    }

    public final void start() {
        if (this._timeoutMillis <= 0 || !this.isEnabled.compareAndSet(false, true)) {
            return;
        }
        reschedule();
    }

    public final void stop() {
        if (this.isEnabled.compareAndSet(true, false)) {
            reschedule();
        }
    }

    private final void reschedule() {
        int i;
        this.handler.removeCallbacks(this.runnable);
        if (!this.isEnabled.get() || (i = this._timeoutMillis) <= 0) {
            return;
        }
        this.handler.postDelayed(this.runnable, i);
    }
}
