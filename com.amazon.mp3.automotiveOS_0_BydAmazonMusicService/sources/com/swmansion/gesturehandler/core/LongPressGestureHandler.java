package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LongPressGestureHandler.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 *2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001*B\u000f\u0012\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b(\u0010)J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0014J\u0018\u0010\u000e\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0014J\u0018\u0010\u0010\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0016\u0010\u001d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001aR\u0016\u0010\u001e\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u0014R\u0016\u0010\u001f\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010\u0014R\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0011\u0010%\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b#\u0010$¨\u0006+"}, d2 = {"Lcom/swmansion/gesturehandler/core/LongPressGestureHandler;", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "", "resetConfig", "", "maxDist", "setMaxDist", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "sourceEvent", "onHandle", "", "newState", "previousState", "onStateChange", "prevState", "dispatchStateChange", "dispatchHandlerUpdate", "", "minDurationMs", "J", "getMinDurationMs", "()J", "setMinDurationMs", "(J)V", "defaultMaxDistSq", "F", "maxDistSq", "startX", "startY", "startTime", "previousTime", "Landroid/os/Handler;", "handler", "Landroid/os/Handler;", "getDuration", "()I", "duration", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "react-native-gesture-handler_release"}, k = 1, mv = {1, 6, 0})
public final class LongPressGestureHandler extends GestureHandler<LongPressGestureHandler> {
    private final float defaultMaxDistSq;
    private Handler handler;
    private float maxDistSq;
    private long minDurationMs;
    private long previousTime;
    private long startTime;
    private float startX;
    private float startY;

    public LongPressGestureHandler(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.minDurationMs = 500L;
        setShouldCancelWhenOutside(true);
        float f = context.getResources().getDisplayMetrics().density * 10.0f;
        float f2 = f * f;
        this.defaultMaxDistSq = f2;
        this.maxDistSq = f2;
    }

    public final void setMinDurationMs(long j) {
        this.minDurationMs = j;
    }

    public final int getDuration() {
        return (int) (this.previousTime - this.startTime);
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    public void resetConfig() {
        super.resetConfig();
        this.minDurationMs = 500L;
        this.maxDistSq = this.defaultMaxDistSq;
    }

    public final LongPressGestureHandler setMaxDist(float maxDist) {
        this.maxDistSq = maxDist * maxDist;
        return this;
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    protected void onHandle(MotionEvent event, MotionEvent sourceEvent) {
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(sourceEvent, "sourceEvent");
        if (getState() == 0) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            this.previousTime = jUptimeMillis;
            this.startTime = jUptimeMillis;
            begin();
            this.startX = sourceEvent.getRawX();
            this.startY = sourceEvent.getRawY();
            Handler handler = new Handler(Looper.getMainLooper());
            this.handler = handler;
            long j = this.minDurationMs;
            if (j > 0) {
                Intrinsics.checkNotNull(handler);
                handler.postDelayed(new Runnable() { // from class: com.swmansion.gesturehandler.core.LongPressGestureHandler$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LongPressGestureHandler.m2750onHandle$lambda0(this.f$0);
                    }
                }, this.minDurationMs);
            } else if (j == 0) {
                activate();
            }
        }
        if (sourceEvent.getActionMasked() == 1) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
                this.handler = null;
            }
            if (getState() == 4) {
                end();
                return;
            } else {
                fail();
                return;
            }
        }
        float rawX = sourceEvent.getRawX() - this.startX;
        float rawY = sourceEvent.getRawY() - this.startY;
        if ((rawX * rawX) + (rawY * rawY) > this.maxDistSq) {
            if (getState() == 4) {
                cancel();
            } else {
                fail();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onHandle$lambda-0, reason: not valid java name */
    public static final void m2750onHandle$lambda0(LongPressGestureHandler this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.activate();
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    protected void onStateChange(int newState, int previousState) {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        handler.removeCallbacksAndMessages(null);
        this.handler = null;
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    public void dispatchStateChange(int newState, int prevState) {
        this.previousTime = SystemClock.uptimeMillis();
        super.dispatchStateChange(newState, prevState);
    }

    @Override // com.swmansion.gesturehandler.core.GestureHandler
    public void dispatchHandlerUpdate(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        this.previousTime = SystemClock.uptimeMillis();
        super.dispatchHandlerUpdate(event);
    }
}
