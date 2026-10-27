package com.amazon.music.downloads;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemDownloadableItemConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001a\u0010\u0007\u001a\u00020\u00068\u0006X\u0086D¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u00068\u0006X\u0086D¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/downloads/ItemDownloadableItemConverter;", "Lcom/amazon/music/downloads/DownloadableItemConverter;", "Lcom/amazon/music/downloads/Item;", "item", "Lcom/amazon/music/downloads/DownloadableItem;", "convert", "", "PURCHASED_TRACK_PREFIX", "Ljava/lang/String;", "getPURCHASED_TRACK_PREFIX", "()Ljava/lang/String;", "DRM_TRACK_PREFIX", "getDRM_TRACK_PREFIX", "<init>", "()V", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public final class ItemDownloadableItemConverter implements DownloadableItemConverter<Item> {
    private final String PURCHASED_TRACK_PREFIX = "coid://";
    private final String DRM_TRACK_PREFIX = "asin://";

    @Override // com.amazon.music.downloads.DownloadableItemConverter
    public DownloadableItem convert(Item item) {
        String strStringPlus;
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.isOwned()) {
            strStringPlus = Intrinsics.stringPlus(this.PURCHASED_TRACK_PREFIX, item.getLuid());
        } else {
            strStringPlus = Intrinsics.stringPlus(this.DRM_TRACK_PREFIX, item.getAsin());
        }
        PriorityInfo priorityInfo = item.getPriorityInfo();
        Intrinsics.checkNotNullExpressionValue(priorityInfo, "item.priorityInfo");
        return new DMMDownloadableItem(new DownloadItemInfo(strStringPlus, "AUTO", priorityInfo));
    }
}
