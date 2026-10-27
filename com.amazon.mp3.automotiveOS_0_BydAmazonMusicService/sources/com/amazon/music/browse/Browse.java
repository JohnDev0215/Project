package com.amazon.music.browse;

import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.pager.Pager;
import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class Browse {
    private final BrowseService browseService;
    private final Marketplace marketplace;
    private final String musicTerritory;

    public Browse(BrowseService browseService, String str) {
        this(browseService, null, str);
    }

    public Browse(BrowseService browseService, Marketplace marketplace, String str) {
        Validate.notNull(browseService, "browseService can't be null", new Object[0]);
        if (marketplace == null && StringUtils.isEmpty(str)) {
            throw new NullPointerException("Either marketplace or musicTerritory can't be null");
        }
        this.browseService = browseService;
        this.marketplace = marketplace;
        this.musicTerritory = str;
    }

    public Pager<List<TrackItem>> getTracks(BrowseItemRequest browseItemRequest) {
        Validate.notNull(browseItemRequest, "request can't be null", new Object[0]);
        return Pager.create(new BrowsePagerIterator(this.browseService, this.marketplace, this.musicTerritory, browseItemRequest, DataType.TRACK)).replay().build();
    }

    public Pager<List<AlbumItem>> getAlbums(BrowseItemRequest browseItemRequest) {
        Validate.notNull(browseItemRequest, "request can't be null", new Object[0]);
        return Pager.create(new BrowsePagerIterator(this.browseService, this.marketplace, this.musicTerritory, browseItemRequest, DataType.ALBUM)).replay().build();
    }

    public Pager<List<PlaylistItem>> getPlaylists(BrowseItemRequest browseItemRequest) {
        Validate.notNull(browseItemRequest, "request can't be null", new Object[0]);
        return Pager.create(new BrowsePagerIterator(this.browseService, this.marketplace, this.musicTerritory, browseItemRequest, DataType.PLAYLIST)).replay().build();
    }
}
