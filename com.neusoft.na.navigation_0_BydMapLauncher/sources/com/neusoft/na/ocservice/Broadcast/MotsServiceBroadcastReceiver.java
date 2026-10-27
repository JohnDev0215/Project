package com.neusoft.na.ocservice.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.neusoft.na.mots.MainActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MotsServiceBroadcastReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/neusoft/na/ocservice/Broadcast/MotsServiceBroadcastReceiver;", "Landroid/content/BroadcastReceiver;", "motsBroadcastCallBack", "Lcom/neusoft/na/ocservice/Broadcast/MotsServiceBroadcastCallBack;", "(Lcom/neusoft/na/ocservice/Broadcast/MotsServiceBroadcastCallBack;)V", "callback", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsServiceBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String START_ACTIVITY = MainActivity.START_ACTIVITY;
    private MotsServiceBroadcastCallBack callback;

    /* JADX INFO: compiled from: MotsServiceBroadcastReceiver.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/neusoft/na/ocservice/Broadcast/MotsServiceBroadcastReceiver$Companion;", "", "()V", "START_ACTIVITY", "", "getSTART_ACTIVITY", "()Ljava/lang/String;", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getSTART_ACTIVITY() {
            return MotsServiceBroadcastReceiver.START_ACTIVITY;
        }
    }

    public MotsServiceBroadcastReceiver(MotsServiceBroadcastCallBack motsBroadcastCallBack) {
        Intrinsics.checkNotNullParameter(motsBroadcastCallBack, "motsBroadcastCallBack");
        this.callback = motsBroadcastCallBack;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, START_ACTIVITY, false, 2, null)) {
            this.callback.getMaskStatus();
        }
    }
}
