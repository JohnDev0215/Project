package com.amazon.music.share.menu.destinations;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$string;
import com.amazon.music.share.menu.ShareableContent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: TwitterShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/share/menu/destinations/TwitterShareDestination;", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "iconResId", "", "getIconResId", "()I", "nameResId", "getNameResId", "findTweetComposerClass", "", "isAvailable", "", "share", "content", "Lcom/amazon/music/share/menu/ShareableContent;", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TwitterShareDestination extends ShareDestination {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TwitterShareDestination(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public ActionType getActionType() {
        return ActionType.SHARE_ON_TWITTER;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getIconResId() {
        return R$drawable.ic_share_twitter;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getNameResId() {
        return R$string.dmusic_sharing_destination_twitter;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean isAvailable() {
        return isPackageAvailable("com.twitter.android");
    }

    private final String findTweetComposerClass() {
        Object obj;
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.setPackage("com.twitter.android");
        List<ResolveInfo> listQueryIntentActivities = getContext().getPackageManager().queryIntentActivities(intent, 0);
        Intrinsics.checkNotNullExpressionValue(listQueryIntentActivities, "context.packageManager\n …tentActivities(intent, 0)");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listQueryIntentActivities, 10));
        Iterator<T> it = listQueryIntentActivities.iterator();
        while (it.hasNext()) {
            arrayList.add(((ResolveInfo) it.next()).activityInfo.name);
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            obj = null;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            String name = (String) next;
            Intrinsics.checkNotNullExpressionValue(name, "name");
            if (StringsKt__StringsKt.contains$default((CharSequence) name, (CharSequence) "com.twitter.composer", false, 2, (Object) null)) {
                obj = next;
                break;
            }
        }
        return (String) obj;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean share(ShareableContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        String strDefaultShareMessage = defaultShareMessage(content);
        if (strDefaultShareMessage == null) {
            return false;
        }
        String strFindTweetComposerClass = findTweetComposerClass();
        Context context = getContext();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        if (strFindTweetComposerClass != null) {
            intent.setClassName("com.twitter.android", strFindTweetComposerClass);
        } else {
            intent.setPackage("com.twitter.android");
        }
        intent.putExtra("android.intent.extra.TEXT", strDefaultShareMessage);
        context.startActivity(intent);
        emitMetrics(content);
        return true;
    }
}
