package com.amazon.music.skyfire.ui.providers;

import android.content.Context;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: SharingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001JV\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\r\u001a\u00020\u000eH&J&\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "", "share", "", "context", "Landroid/content/Context;", "title", "", "subject", "message", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "refMarker", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "args", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SharingProvider {
    void share(Context context, String title, String subject, String message, String entityId, String entityIdType, String refMarker, EntityType entityType);

    void share(Context context, Map<String, String> args);

    /* JADX INFO: compiled from: SharingProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void share$default(SharingProvider sharingProvider, Context context, String str, String str2, String str3, String str4, String str5, String str6, EntityType entityType, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: share");
            }
            sharingProvider.share(context, str, str2, str3, str4, str5, str6, (i & 128) != 0 ? EntityType.UNKNOWN : entityType);
        }
    }
}
