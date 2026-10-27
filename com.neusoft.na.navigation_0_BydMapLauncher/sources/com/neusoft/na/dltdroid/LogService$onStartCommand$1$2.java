package com.neusoft.na.dltdroid;

import com.neusoft.na.dltdroid.service.DaemonState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: LogService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/neusoft/na/dltdroid/service/DaemonState;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class LogService$onStartCommand$1$2 extends Lambda implements Function1<DaemonState, Unit> {
    final /* synthetic */ LogService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LogService$onStartCommand$1$2(LogService logService) {
        super(1);
        this.this$0 = logService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0() {
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(DaemonState daemonState) {
        invoke2(daemonState);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(DaemonState daemonState) {
        this.this$0.mError = Error.LogSinkError;
        this.this$0.getMHandler().post(new Runnable() { // from class: com.neusoft.na.dltdroid.LogService$onStartCommand$1$2$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LogService$onStartCommand$1$2.invoke$lambda$0();
            }
        });
    }
}
