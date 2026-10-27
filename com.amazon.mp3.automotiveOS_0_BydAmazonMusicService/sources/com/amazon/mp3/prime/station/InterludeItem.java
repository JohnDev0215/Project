package com.amazon.mp3.prime.station;

import android.net.Uri;
import com.amazon.mp3.library.item.MarketplaceDependentItem;
import com.amazon.music.find.converter.BrushV2Converter;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InterludeItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0002H\u0016R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/prime/station/InterludeItem;", "Lcom/amazon/mp3/library/item/MarketplaceDependentItem;", "", "getItemTypeName", "", BrushV2Converter.KEY_IS_EXPLICIT, PageUriUtils.SOURCE_QUERY_PARAM, "Landroid/net/Uri;", "getContentUri", "Lcom/amazon/music/media/playback/MediaItem;", "mMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "<init>", "(Lcom/amazon/music/media/playback/MediaItem;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class InterludeItem extends MarketplaceDependentItem {
    private final MediaItem mMediaItem;

    public InterludeItem(MediaItem mMediaItem) {
        Intrinsics.checkNotNullParameter(mMediaItem, "mMediaItem");
        this.mMediaItem = mMediaItem;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        MediaItem.Type type = this.mMediaItem.getType();
        if (type == null) {
            return null;
        }
        return type.toString();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        MediaAccessInfo mediaAccessInfo = this.mMediaItem.getMediaAccessInfo();
        return mediaAccessInfo != null && mediaAccessInfo.isExplicit();
    }

    @Override // com.amazon.mp3.library.item.MarketplaceDependentItem
    public Uri getContentUri(String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        MediaItemId mediaItemId = this.mMediaItem.getMediaItemId(MediaItemId.Type.URL);
        if (mediaItemId == null) {
            return null;
        }
        return mediaItemId.getUri();
    }
}
