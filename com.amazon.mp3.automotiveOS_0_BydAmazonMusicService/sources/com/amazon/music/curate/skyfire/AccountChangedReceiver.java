package com.amazon.music.curate.skyfire;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: AccountChangedReceiver.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/curate/skyfire/AccountChangedReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AccountChangedReceiver extends BroadcastReceiver {
    private static final String ACCOUNT_ADDED_ACTION;
    private static final String ACCOUNT_REMOVED_ACTION;
    private static final Logger LOG;

    static {
        Logger logger = LoggerFactory.getLogger(AccountChangedReceiver.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(AccountChanged…r::class.java.simpleName)");
        LOG = logger;
        ACCOUNT_ADDED_ACTION = "com.amazon.dcp.sso.action.account.added";
        ACCOUNT_REMOVED_ACTION = "com.amazon.dcp.sso.action.account.removed";
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (ACCOUNT_ADDED_ACTION.equals(intent == null ? null : intent.getAction())) {
            CurateMethods.onUserSignedIn(context);
            return;
        }
        if (ACCOUNT_REMOVED_ACTION.equals(intent != null ? intent.getAction() : null)) {
            CurateMethods.onUserSignedOut(context);
        }
    }
}
