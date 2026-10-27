package com.amazon.music.share.menu.destinations;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Telephony;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$string;
import com.amazon.music.share.menu.ShareableContent;
import java.io.ByteArrayOutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessagesShareDestination.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/share/menu/destinations/MessagesShareDestination;", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "iconResId", "", "getIconResId", "()I", "nameResId", "getNameResId", "getUri", "Landroid/net/Uri;", "bitmap", "Landroid/graphics/Bitmap;", "isAvailable", "", "share", "content", "Lcom/amazon/music/share/menu/ShareableContent;", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MessagesShareDestination extends ShareDestination {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessagesShareDestination(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public ActionType getActionType() {
        return ActionType.SHARE_ON_MESSAGES;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getIconResId() {
        return R$drawable.ic_share_messages;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public int getNameResId() {
        return R$string.dmusic_sharing_destination_messages;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean isAvailable() {
        return Telephony.Sms.getDefaultSmsPackage(getContext()) != null;
    }

    @Override // com.amazon.music.share.menu.destinations.ShareDestination
    public boolean share(ShareableContent content) {
        String defaultSmsPackage;
        Intrinsics.checkNotNullParameter(content, "content");
        String strDefaultShareMessage = defaultShareMessage(content);
        if (strDefaultShareMessage == null || (defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(getContext())) == null) {
            return false;
        }
        Context context = getContext();
        Intent intent = new Intent("android.intent.action.SEND");
        if (content.getImageBitmap() == null) {
            intent.setType("text/plain");
        } else {
            intent.setType("image/png");
            intent.putExtra("android.intent.extra.STREAM", getUri(content.getImageBitmap()));
        }
        intent.setPackage(defaultSmsPackage);
        intent.putExtra("android.intent.extra.TEXT", strDefaultShareMessage);
        context.startActivity(intent);
        emitMetrics(content);
        return true;
    }

    private final Uri getUri(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        }
        try {
            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContext().getContentResolver(), bitmap, (String) null, (String) null).toString());
            Intrinsics.checkNotNullExpressionValue(uri, "{\n            parse(path.toString())\n        }");
            return uri;
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }
    }
}
