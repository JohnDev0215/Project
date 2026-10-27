package com.amazon.music.share.menu.destinations;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.share.menu.KtExtensionsKt;
import com.amazon.music.share.menu.ShareableContent;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: ShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0004J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0013\u001a\u00020\u0014H\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014H\u0004J\b\u0010\u0019\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0016H\u0004J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H&R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/share/menu/destinations/ShareDestination;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getContext", "()Landroid/content/Context;", "iconResId", "", "getIconResId", "()I", "nameResId", "getNameResId", "createMetricsIntent", "Landroid/content/Intent;", "content", "Lcom/amazon/music/share/menu/ShareableContent;", "defaultShareMessage", "", "emitMetrics", "", "isAvailable", "", "isPackageAvailable", "name", "share", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class ShareDestination {
    private final Context context;

    public abstract ActionType getActionType();

    public abstract int getIconResId();

    public abstract int getNameResId();

    public abstract boolean isAvailable();

    public abstract boolean share(ShareableContent content);

    public ShareDestination(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    protected final boolean isPackageAvailable(String name) {
        ApplicationInfo applicationInfo;
        Intrinsics.checkNotNullParameter(name, "name");
        try {
            applicationInfo = this.context.getPackageManager().getApplicationInfo(name, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return applicationInfo != null;
    }

    protected final String defaultShareMessage(ShareableContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        String strUrlWithRefMarker = KtExtensionsKt.urlWithRefMarker(content);
        if (strUrlWithRefMarker == null) {
            return null;
        }
        String message = content.getMessage();
        boolean z = false;
        if (message != null && new Regex("\\bhttps?://\\S+").containsMatchIn(message)) {
            z = true;
        }
        if (z) {
            return content.getMessage();
        }
        StringBuilder sb = new StringBuilder();
        String message2 = content.getMessage();
        if (message2 == null) {
            message2 = "";
        }
        sb.append(message2);
        sb.append(' ');
        sb.append(strUrlWithRefMarker);
        return sb.toString();
    }

    protected final Intent createMetricsIntent(ShareableContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage(getContext().getApplicationContext().getPackageName());
        intent.setComponent(new ComponentName(getContext().getApplicationContext().getPackageName(), "com.amazon.music.share.ShareIntentReceiver"));
        intent.putExtra("ACTION_TYPE", getActionType().name());
        EntityIdType entityIdType = content.getEntityIdType();
        intent.putExtra(Contexts.OpenShareLink.entityIdType, entityIdType == null ? null : entityIdType.name());
        EntityType entityType = content.getEntityType();
        intent.putExtra(Contexts.OpenShareLink.entityType, entityType != null ? entityType.name() : null);
        intent.putExtra(Contexts.OpenShareLink.entityId, content.getEntityId());
        intent.putExtra("REF_MARKER", content.getRef());
        return intent;
    }

    protected final void emitMetrics(ShareableContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.context.sendBroadcast(createMetricsIntent(content));
    }
}
