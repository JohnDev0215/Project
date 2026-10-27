package com.amazon.mp3.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LauncherLinkStatusHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J$\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/navigation/LauncherLinkStatusHandler;", "", "Landroid/content/Context;", "context", "", "message", "Landroid/net/Uri;", "uri", "", "broadcastSuccessfullyHandledLauncherLink", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface LauncherLinkStatusHandler {
    void broadcastSuccessfullyHandledLauncherLink(Context context, String message, Uri uri);

    /* JADX INFO: compiled from: LauncherLinkStatusHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void broadcastFailedToHandleLauncherLink(LauncherLinkStatusHandler launcherLinkStatusHandler, Context context, String str, Uri uri) {
            Intrinsics.checkNotNullParameter(launcherLinkStatusHandler, "this");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intent intent = new Intent("APPLINK_DEEPLINKS");
            intent.putExtra("STATUS", ContextAwareDeeplinkManager.Companion.Status.FAILURE);
            intent.putExtra("MESSAGE", str);
            intent.putExtra(Contexts.OpenSeeMore.uri, uri.toString());
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }

        public static /* synthetic */ void broadcastSuccessfullyHandledLauncherLink$default(LauncherLinkStatusHandler launcherLinkStatusHandler, Context context, String str, Uri uri, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: broadcastSuccessfullyHandledLauncherLink");
            }
            if ((i & 2) != 0) {
                str = null;
            }
            launcherLinkStatusHandler.broadcastSuccessfullyHandledLauncherLink(context, str, uri);
        }

        public static void broadcastSuccessfullyHandledLauncherLink(LauncherLinkStatusHandler launcherLinkStatusHandler, Context context, String str, Uri uri) {
            Intrinsics.checkNotNullParameter(launcherLinkStatusHandler, "this");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, "uri");
            Intent intent = new Intent("APPLINK_DEEPLINKS");
            intent.putExtra("STATUS", ContextAwareDeeplinkManager.Companion.Status.SUCCESS);
            intent.putExtra("MESSAGE", str);
            intent.putExtra(Contexts.OpenSeeMore.uri, uri.toString());
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
