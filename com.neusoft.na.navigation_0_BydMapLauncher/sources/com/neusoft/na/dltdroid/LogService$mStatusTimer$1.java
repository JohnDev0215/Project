package com.neusoft.na.dltdroid;

import android.os.Handler;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: LogService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\n\u001a\u00020\u000bH\u0016J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tR\u001b\u0010\u0002\u001a\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"com/neusoft/na/dltdroid/LogService$mStatusTimer$1", "Ljava/lang/Runnable;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "intervalMs", "", "run", "", "setInterval", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LogService$mStatusTimer$1 implements Runnable {

    /* JADX INFO: renamed from: handler$delegate, reason: from kotlin metadata */
    private final Lazy handler;
    private long intervalMs;
    final /* synthetic */ LogService this$0;

    LogService$mStatusTimer$1(final LogService logService) {
        this.this$0 = logService;
        this.handler = LazyKt.lazy(new Function0<Handler>() { // from class: com.neusoft.na.dltdroid.LogService$mStatusTimer$1$handler$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Handler invoke() {
                return logService.getMHandler();
            }
        });
    }

    private final Handler getHandler() {
        return (Handler) this.handler.getValue();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.this$0.sendLogState();
        getHandler().postDelayed(this, this.intervalMs);
    }

    public final void setInterval(long intervalMs) {
        if (this.intervalMs != 0) {
            if (intervalMs == 0) {
                getHandler().removeCallbacks(this);
            }
            this.intervalMs = intervalMs;
        } else if (intervalMs > 0) {
            getHandler().post(this);
            this.intervalMs = intervalMs;
        }
    }
}
