package com.amazon.music.platform.data.entity;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: EntityItem.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "Lcom/amazon/music/platform/data/entity/EntityItem;", ContextMappingConstants.ITEMS, "", "getItems", "()Ljava/util/List;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface EntityItemCollection extends EntityItem {
    List<EntityItem> getItems();
}
