package com.amazon.mp3.intents;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.util.PlatformUtil;
import com.visualon.OSMPUtils.voOSType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PendingIntentUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\rJ0\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\rJ\u001a\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0002J0\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\r¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/intents/PendingIntentUtil;", "", "()V", "getActivity", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "requestCode", "", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "flags", "requiresMutable", "", "getBroadcast", "getFlags", "getService", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PendingIntentUtil {
    public static final PendingIntentUtil INSTANCE = new PendingIntentUtil();

    private PendingIntentUtil() {
    }

    private final int getFlags(int flags, boolean requiresMutable) {
        if (PlatformUtil.isVersionOrGreater(23)) {
            return flags | ((PlatformUtil.isVersionOrGreater(31) && requiresMutable) ? voOSType.VOOSMP_SRC_FFAUDIO_WMA : voOSType.VOOSMP_SRC_FFAUDIO_MIDI);
        }
        return flags;
    }

    public static /* synthetic */ PendingIntent getActivity$default(PendingIntentUtil pendingIntentUtil, Context context, int i, Intent intent, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z = false;
        }
        return pendingIntentUtil.getActivity(context, i, intent, i2, z);
    }

    public final PendingIntent getActivity(Context context, int requestCode, Intent intent, int flags, boolean requiresMutable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PendingIntent activity = PendingIntent.getActivity(context, requestCode, intent, getFlags(flags, requiresMutable));
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(context, req…(flags, requiresMutable))");
        return activity;
    }

    public final PendingIntent getBroadcast(Context context, int requestCode, Intent intent, int flags, boolean requiresMutable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, requestCode, intent, getFlags(flags, requiresMutable));
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(context, re…(flags, requiresMutable))");
        return broadcast;
    }

    public final PendingIntent getService(Context context, int requestCode, Intent intent, int flags, boolean requiresMutable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PendingIntent service = PendingIntent.getService(context, requestCode, intent, getFlags(flags, requiresMutable));
        Intrinsics.checkNotNullExpressionValue(service, "getService(context, requ…(flags, requiresMutable))");
        return service;
    }
}
