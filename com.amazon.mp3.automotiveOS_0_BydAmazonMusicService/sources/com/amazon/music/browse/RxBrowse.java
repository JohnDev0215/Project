package com.amazon.music.browse;

import com.amazon.music.pager.Pager;
import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class RxBrowse {
    private final Browse browse;

    public RxBrowse(Browse browse) {
        this.browse = browse;
    }

    public Pager<List<TrackItem>> getTracks(BrowseItemRequest browseItemRequest) {
        return this.browse.getTracks(browseItemRequest);
    }

    public Pager<List<AlbumItem>> getAlbums(BrowseItemRequest browseItemRequest) {
        return this.browse.getAlbums(browseItemRequest);
    }

    public Pager<List<PlaylistItem>> getPlaylists(BrowseItemRequest browseItemRequest) {
        return this.browse.getPlaylists(browseItemRequest);
    }
}
