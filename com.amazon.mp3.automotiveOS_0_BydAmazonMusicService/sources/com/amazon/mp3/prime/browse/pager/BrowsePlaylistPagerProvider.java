package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.music.pager.Pager;
import com.amazon.music.storeservice.model.PlaylistItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BrowsePlaylistPagerProvider extends AbstractPagerProvider<PlaylistItem> {
    public BrowsePlaylistPagerProvider(PaginatedRankType rankType, String refinementID) {
        super(rankType, PaginatedDataType.PLAYLIST, refinementID);
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public Pager<List<PlaylistItem>> createPager(Context context) {
        return BrowseFactory.createRxBrowse(context).getPlaylists(createRequest(context));
    }
}
