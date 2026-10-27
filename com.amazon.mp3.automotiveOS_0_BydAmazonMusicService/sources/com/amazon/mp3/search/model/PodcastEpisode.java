package com.amazon.mp3.search.model;

import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.music.find.model.search.PodcastEpisodeSearchItem;

/* JADX INFO: loaded from: classes4.dex */
public final class PodcastEpisode extends PodcastEpisodeSearchItem implements CatalogContent {
    @Override // com.amazon.music.find.model.search.SearchItem
    public String getItemTypeName() {
        return null;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        return null;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        return false;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return false;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return false;
    }
}
