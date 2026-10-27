package com.amazon.music.media.auto.tab.home.mesk.converters;

import Touch.SyncInterface.v1_0.WidgetItemSyncElement;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.media.auto.util.ContentPlaybackUtil;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UserPlaylistConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/media/auto/tab/home/mesk/converters/UserPlaylistConverter;", "Lcom/amazon/music/media/auto/tab/home/mesk/converters/EntityConverter;", "()V", "convertEntity", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetItemSyncElement", "LTouch/SyncInterface/v1_0/WidgetItemSyncElement;", "widgetSyncElementPrimaryHeader", "", "contentPlaybackUtil", "Lcom/amazon/music/media/auto/util/ContentPlaybackUtil;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class UserPlaylistConverter implements EntityConverter {
    @Override // com.amazon.music.media.auto.tab.home.mesk.converters.EntityConverter
    public AutoMediaItem convertEntity(WidgetItemSyncElement widgetItemSyncElement, String widgetSyncElementPrimaryHeader, ContentPlaybackUtil contentPlaybackUtil) {
        Intrinsics.checkNotNullParameter(widgetItemSyncElement, "widgetItemSyncElement");
        Intrinsics.checkNotNullParameter(contentPlaybackUtil, "contentPlaybackUtil");
        String strPrimaryText = widgetItemSyncElement.primaryText();
        if (strPrimaryText == null) {
            strPrimaryText = widgetSyncElementPrimaryHeader == null ? "" : widgetSyncElementPrimaryHeader;
        }
        String strEntityId = widgetItemSyncElement.entityId();
        if (strEntityId == null) {
            return null;
        }
        return new AutoMediaItem(strPrimaryText, widgetItemSyncElement.secondaryText(), null, new UserPlaylistMetadata(widgetItemSyncElement.metricsToken(), contentPlaybackUtil.getUserPlaylistUri(), null, strPrimaryText, widgetItemSyncElement.image(), null, null, null, true, strEntityId, null, null, null, false, 15588, null), widgetItemSyncElement.metricsToken(), 1, null, 0, widgetSyncElementPrimaryHeader, Boolean.FALSE, 196, null);
    }
}
