package com.amazon.mp3.prime.browse.pager;

import android.content.Context;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.music.pager.Pager;
import com.amazon.music.storeservice.model.AlbumItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseAlbumPagerProvider extends AbstractPagerProvider<AlbumItem> {
    public BrowseAlbumPagerProvider(PaginatedRankType rankType, String refinementID) {
        super(rankType, PaginatedDataType.ALBUM, refinementID);
    }

    @Override // com.amazon.mp3.prime.browse.pager.PagerProvider
    public Pager<List<AlbumItem>> createPager(Context context) {
        return BrowseFactory.createRxBrowse(context).getAlbums(createRequest(context));
    }
}
