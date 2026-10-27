package com.amazon.music.media.auto.provider;

import com.amazon.music.media.auto.MediaItemNode;
import com.amazon.music.media.auto.provider.data.AutoTabItem;
import kotlin.Metadata;

/* JADX INFO: compiled from: MESKMediaBrowserNodeProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/media/auto/provider/MESKMediaBrowserNodeProvider;", "", "Lcom/amazon/music/media/auto/provider/data/AutoTabItem;", "tabItem", "Lcom/amazon/music/media/auto/MediaItemNode;", "createMeskHomeTabNode", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public interface MESKMediaBrowserNodeProvider {
    MediaItemNode createMeskHomeTabNode(AutoTabItem tabItem);
}
