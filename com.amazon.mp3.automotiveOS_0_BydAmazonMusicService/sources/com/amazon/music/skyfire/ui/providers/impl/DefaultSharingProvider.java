package com.amazon.music.skyfire.ui.providers.impl;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.share.ShareIntentReceiver;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultSharingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002JT\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultSharingProvider;", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "()V", "share", "", "context", "Landroid/content/Context;", "title", "", "subject", "message", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "refMarker", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "args", "", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultSharingProvider implements SharingProvider {
    private static final String ENTITY_TYPE = "ENTITY_TYPE";
    private static final String INTENT_TEXT_PLAIN = "text/plain";
    private static final String KEY_ENTITY_ID = "ENTITY_ID";
    private static final String KEY_ENTITY_ID_TYPE = "ENTITY_ID_TYPE";
    private static final String KEY_REF_MARKER = "REF_MARKER";

    @Override // com.amazon.music.skyfire.ui.providers.SharingProvider
    public void share(Context context, String title, String subject, String message, String entityId, String entityIdType, String refMarker, EntityType entityType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(INTENT_TEXT_PLAIN);
        intent.putExtra("android.intent.extra.SUBJECT", subject);
        intent.putExtra("android.intent.extra.TEXT", message);
        Intent intent2 = new Intent(context, (Class<?>) ShareIntentReceiver.class);
        intent2.putExtra("ENTITY_ID_TYPE", entityIdType);
        intent2.putExtra("ENTITY_ID", entityId);
        intent2.putExtra(KEY_REF_MARKER, refMarker);
        intent2.putExtra("ENTITY_TYPE", entityType.toString());
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent2, 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(context,\n  …ingIntent.FLAG_IMMUTABLE)");
        context.startActivity(Intent.createChooser(intent, title, broadcast.getIntentSender()));
    }

    @Override // com.amazon.music.skyfire.ui.providers.SharingProvider
    public void share(Context context, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(args, "args");
        SharingProvider.DefaultImpls.share$default(this, context, args.get("TITLE"), args.get(Contexts.OpenShareLink.subject), args.get("MESSAGE"), args.get("ENTITY_ID"), args.get("ENTITY_ID_TYPE"), args.get(Contexts.OpenShareLink.refMarker), null, 128, null);
    }
}
