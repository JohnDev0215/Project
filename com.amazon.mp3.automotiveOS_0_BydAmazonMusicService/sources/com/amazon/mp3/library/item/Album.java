package com.amazon.mp3.library.item;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.util.Log;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemType;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class Album extends MarketplaceDependentItem implements Refinable {
    private static final String TAG = "Album";
    private long mAlbumArtId;
    private String mArtistAsin;
    private long mArtistId;
    private String mArtistName;
    private List<ContentEncoding> mContentEncodings;
    private boolean mHaveCorrectCounts;
    private String mSortArtistName;
    private String mSortTitle;
    private String mTitle;
    private long mTrackCount;

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    public Album() {
        this.mHaveCorrectCounts = false;
    }

    public Album(LibraryItemFactory libItemFactory, Uri contentUri) {
        super(libItemFactory, contentUri);
        this.mHaveCorrectCounts = false;
    }

    public Collection<MusicTrack> getTracks() {
        return this.mLibItemFactory.getTracksForAlbum(this);
    }

    public Collection<MusicTrack> getTracksWithDiscNumberSortOrder() {
        return this.mLibItemFactory.getTracksForAlbum(this, CirrusMediaSource.getAlbumTrackSortOrder());
    }

    public Artist getArtist() {
        return this.mLibItemFactory.getArtistForAlbum(this);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setSortTitle(String sortTitle) {
        this.mSortTitle = sortTitle;
    }

    public long getTrackCount() {
        if (!this.mHaveCorrectCounts) {
            this.mLibItemFactory.updateCounts(this);
            this.mHaveCorrectCounts = true;
        }
        return this.mTrackCount;
    }

    public void setTrackCount(long trackCount) {
        this.mTrackCount = trackCount;
        this.mHaveCorrectCounts = true;
    }

    public String getArtistName() {
        return this.mArtistName;
    }

    public void setArtistName(String artistName) {
        this.mArtistName = artistName;
    }

    public void setSortArtistName(String sortArtistName) {
        this.mSortArtistName = sortArtistName;
    }

    public long getArtistId() {
        return this.mArtistId;
    }

    public void setArtistId(long artistId) {
        this.mArtistId = artistId;
    }

    public String getArtistAsin() {
        if (TextUtils.isEmpty(this.mArtistAsin)) {
            demandOptionalInfo();
        }
        return this.mArtistAsin;
    }

    public void setAlbumArtId(long albumArtId) {
        this.mAlbumArtId = albumArtId;
    }

    public void setArtistAsin(String artistAsin) {
        this.mArtistAsin = artistAsin;
    }

    @Override // com.amazon.mp3.library.item.MarketplaceDependentItem
    public Uri getContentUri(String source) {
        try {
            return MediaProvider.Albums.Tracks.getContentUri(source, Long.parseLong(getId()));
        } catch (Exception e) {
            Log.error(TAG, "Cannot getContentUri for source: " + source + " Id: " + getId(), e);
            return null;
        }
    }

    public List<ContentEncoding> getContentEncodings() {
        return this.mContentEncodings;
    }

    public void setContentEncodings(final List<ContentEncoding> contentEncodings) {
        this.mContentEncodings = contentEncodings;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        Album album = (Album) obj;
        return album.getId() != null && album.getSource() != null && album.getId().equals(getId()) && album.getSource().equals(getSource());
    }

    public int hashCode() {
        return ((getId() != null ? getId().hashCode() : 0) * 31) + (getSource() != null ? getSource().hashCode() : 0);
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_album);
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected EntityItemType getEntityItemType() {
        return CollectionType.Album.INSTANCE;
    }
}
