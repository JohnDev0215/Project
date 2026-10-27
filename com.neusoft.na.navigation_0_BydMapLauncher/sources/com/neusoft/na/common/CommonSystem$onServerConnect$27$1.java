package com.neusoft.na.common;

import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import api.notifications.com.neusoft.common.commonsysapiinverse.NotifyShowToastMessage;
import api.structs.com.neusoft.common.DelayPeriod;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: CommonSystem.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lapi/notifications/com/neusoft/common/commonsysapiinverse/NotifyShowToastMessage;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
final class CommonSystem$onServerConnect$27$1 extends Lambda implements Function1<NotifyShowToastMessage, Unit> {
    final /* synthetic */ CommonSystem this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonSystem$onServerConnect$27$1(CommonSystem commonSystem) {
        super(1);
        this.this$0 = commonSystem;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(NotifyShowToastMessage notifyShowToastMessage) {
        invoke2(notifyShowToastMessage);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final NotifyShowToastMessage notifyShowToastMessage) {
        Log.i("XJCommonSystem", "NotifyShowToastMessage");
        if (notifyShowToastMessage.getShowToastMessage().getDelay() == DelayPeriod.LONG_DELAY) {
            Handler handler = this.this$0.mHandler;
            final CommonSystem commonSystem = this.this$0;
            handler.post(new Runnable() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$27$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CommonSystem$onServerConnect$27$1.invoke$lambda$0(commonSystem, notifyShowToastMessage);
                }
            });
        } else if (notifyShowToastMessage.getShowToastMessage().getDelay() == DelayPeriod.SHORT_DELAY) {
            Handler handler2 = this.this$0.mHandler;
            final CommonSystem commonSystem2 = this.this$0;
            handler2.post(new Runnable() { // from class: com.neusoft.na.common.CommonSystem$onServerConnect$27$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    CommonSystem$onServerConnect$27$1.invoke$lambda$1(commonSystem2, notifyShowToastMessage);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(CommonSystem this$0, NotifyShowToastMessage notifyShowToastMessage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.getContext(), notifyShowToastMessage.getShowToastMessage().getMessage(), 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(CommonSystem this$0, NotifyShowToastMessage notifyShowToastMessage) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0.getContext(), notifyShowToastMessage.getShowToastMessage().getMessage(), 0).show();
    }
}
