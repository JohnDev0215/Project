package com.amazon.music.station.view;

import com.amazon.music.station.StationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.model.RefinementItem;
import com.amazon.music.station.model.SectionItem;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface StationListView {
    void onNetworkError(StationException stationException);

    void onProcessingError(StationException stationException);

    void onRefinementItemLoaded(Map<SectionItem, List<RefinementItem>> map);

    void onStationItemsLoaded(RefinementItem refinementItem, List<StationItem> list);
}
