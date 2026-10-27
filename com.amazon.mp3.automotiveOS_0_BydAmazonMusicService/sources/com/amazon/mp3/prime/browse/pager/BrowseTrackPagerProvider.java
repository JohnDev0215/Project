package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.music.pager.Pager;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseTrackPagerProvider extends AbstractPagerProvider<TrackItem> {
    public BrowseTrackPagerProvider(PaginatedRankType rankType, String refinementID) {
        super(rankType, PaginatedDataType.TRACK, refinementID);
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public Pager<List<TrackItem>> createPager(Context context) {
        return BrowseFactory.createRxBrowse(context).getTracks(createRequest(context));
    }
}
