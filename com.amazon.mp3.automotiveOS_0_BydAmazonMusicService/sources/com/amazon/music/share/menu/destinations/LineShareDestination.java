package com.amazon.music.share.menu.destinations;

import android.content.Context;
import android.content.Intent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$string;
import com.amazon.music.share.menu.ShareableContent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LineShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/share/menu/destinations/LineShareDestination;", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "iconResId", "", "getIconResId", "()I", "nameResId", "getNameResId", "isAvailable", "", "share", "content", "Lcom/amazon/music/share/menu/ShareableContent;", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LineShareDestination extends ShareDestination {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LineShareDestination(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public ActionType getActionType() {
        return ActionType.SHARE_ON_LINE;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getIconResId() {
        return R$drawable.ic_share_line;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getNameResId() {
        return R$string.dmusic_sharing_destination_line;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean isAvailable() {
        return isPackageAvailable("jp.naver.line.android");
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean share(ShareableContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        String strDefaultShareMessage = defaultShareMessage(content);
        if (strDefaultShareMessage == null) {
            return false;
        }
        Context context = getContext();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.setPackage("jp.naver.line.android");
        intent.putExtra("android.intent.extra.TEXT", strDefaultShareMessage);
        context.startActivity(intent);
        emitMetrics(content);
        return true;
    }
}
