package com.amazon.music.media.auto.provider;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: OnMusicLoadedListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\"\u0010\b\u001a\u00020\u00072\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002H&J*\u0010\u000b\u001a\u00020\u00072\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\u0006\u0010\n\u001a\u00020\u0003H&¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/auto/provider/OnMusicLoadedListener;", "", "", "", "", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "widgetsMap", "", "onBrushHomePageFetched", "widgetMap", "widgetTitle", "onBrushHomeSeeMorePageFetched", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public interface OnMusicLoadedListener {
    void onBrushHomePageFetched(Map<String, ? extends List<AutoMediaItem>> widgetsMap);

    void onBrushHomeSeeMorePageFetched(Map<String, ? extends List<AutoMediaItem>> widgetMap, String widgetTitle);
}
