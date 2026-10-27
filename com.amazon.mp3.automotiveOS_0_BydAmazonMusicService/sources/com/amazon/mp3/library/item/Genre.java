package com.amazon.mp3.library.item;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;

/* JADX INFO: loaded from: classes3.dex */
public class Genre extends AbstractItem {
    private long mAlbumCount;
    private boolean mHaveCorrectAlbumCounts;
    private boolean mHaveCorrectCounts;
    private String mName;
    private long mTrackCount;

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    public Genre() {
        this.mHaveCorrectCounts = false;
        this.mHaveCorrectAlbumCounts = false;
    }

    public Genre(LibraryItemFactory libItemFactory, Uri contentUri) {
        super(libItemFactory, contentUri);
        this.mHaveCorrectCounts = false;
        this.mHaveCorrectAlbumCounts = false;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public long getTrackCount() {
        LibraryItemFactory libraryItemFactory;
        if (!this.mHaveCorrectCounts && (libraryItemFactory = this.mLibItemFactory) != null) {
            libraryItemFactory.updateCounts(this);
            this.mHaveCorrectCounts = true;
        }
        return this.mTrackCount;
    }

    public void setTrackCount(long trackCount) {
        this.mTrackCount = trackCount;
        this.mHaveCorrectCounts = true;
    }

    public long getAlbumCount() {
        LibraryItemFactory libraryItemFactory;
        if (!this.mHaveCorrectAlbumCounts && (libraryItemFactory = this.mLibItemFactory) != null) {
            libraryItemFactory.updateCounts(this);
            this.mHaveCorrectAlbumCounts = true;
        }
        return this.mAlbumCount;
    }

    public void setAlbumCount(long albumCount) {
        this.mAlbumCount = albumCount;
        this.mHaveCorrectAlbumCounts = true;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_genre);
    }
}
