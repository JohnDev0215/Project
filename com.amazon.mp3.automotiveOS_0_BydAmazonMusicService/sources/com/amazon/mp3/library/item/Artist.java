package com.amazon.mp3.library.item;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemType;

/* JADX INFO: loaded from: classes3.dex */
public class Artist extends MarketplaceDependentItem implements Refinable {
    private static final String TAG = "Artist";
    private long mAlbumCount;
    private long mDateFollowed;
    private int mFollowStatus;
    private boolean mHaveCorrectAlbumCounts;
    private boolean mHaveCorrectCounts;
    private String mName;
    private String mSortName;
    private long mTrackCount;

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    public Artist() {
        this.mHaveCorrectCounts = false;
        this.mHaveCorrectAlbumCounts = false;
    }

    public Artist(LibraryItemFactory libItemFactory, Uri contentUri) {
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

    public void setSortName(String sortName) {
        this.mSortName = sortName;
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

    @Override // com.amazon.mp3.library.item.MarketplaceDependentItem
    public Uri getContentUri(String source) {
        try {
            return MediaProvider.Artists.Tracks.getContentUri(source, Long.parseLong(getId()));
        } catch (Exception e) {
            Log.error(TAG, "Cannot getContentUri for source: " + source + " Id: " + getId(), e);
            return null;
        }
    }

    public int getFollowStatus() {
        return this.mFollowStatus;
    }

    public void setFollowStatus(int followStatus) {
        this.mFollowStatus = followStatus;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_artist);
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected EntityItemType getEntityItemType() {
        return CollectionType.Artist.INSTANCE;
    }

    public long getDateFollowed() {
        return this.mDateFollowed;
    }

    public void setDateFollowed(long mDateFollowed) {
        this.mDateFollowed = mDateFollowed;
    }
}
