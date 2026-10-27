package com.amazon.mp3.library.item;

import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityContext;
import com.amazon.music.platform.data.eligibility.factory.ContentEligibilityFactory;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.PlaylistEntityItem;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.identifiers.UserPlaylistId;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public class Playlist extends AbstractItem implements Refinable, PlaylistEntityItem {
    private double createdDate;
    private double lastModifiedDate;
    private double lastTouchedDate;
    private String mLuid;
    private String mName;
    private long mTrackCount;
    private String mType;
    private String mVersion;
    private MediaProvider.Playlists.VisibilityState mVisibility;
    private int numberOfFollowers;
    private String sharedId;

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    public Playlist() {
        this.mVisibility = MediaProvider.Playlists.VisibilityState.NO_STATE;
    }

    public Playlist(LibraryItemFactory libItemFactory, Uri contentUri) {
        super(libItemFactory, contentUri);
        this.mVisibility = MediaProvider.Playlists.VisibilityState.NO_STATE;
    }

    public String getLuid() {
        return this.mLuid;
    }

    public void setLuid(String luid) {
        this.mLuid = luid;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Collection<MusicTrack> getTracks() {
        return this.mLibItemFactory.getTracksForPlaylist(this);
    }

    public long getTrackCount() {
        if (this.mTrackCount == -1) {
            this.mTrackCount = PlaylistUtil.getPlaylistCount(AmazonApplication.getContext(), getContentUri());
        }
        return this.mTrackCount;
    }

    public void setTrackCount(long trackCount) {
        this.mTrackCount = trackCount;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public double getLastTouchedDate() {
        return this.lastTouchedDate;
    }

    public void setLastTouchedDate(double lastTouchedDate) {
        this.lastTouchedDate = lastTouchedDate;
    }

    public double getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(double createdDate) {
        this.createdDate = createdDate;
    }

    public double getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(double lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getSharedId() {
        return this.sharedId;
    }

    public void setSharedId(final String sharedId) {
        this.sharedId = sharedId;
    }

    public boolean isCatalog() {
        return "vnd.android.cursor.item/vnd.amazonmp3.primeplaylist".equals(getType());
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_playlist);
    }

    public MediaProvider.Playlists.VisibilityState getVisibility() {
        return this.mVisibility;
    }

    public void setVisibility(MediaProvider.Playlists.VisibilityState visibility) {
        this.mVisibility = visibility;
    }

    public String getVisibilityText() {
        return isCatalog() ? "" : getVisibility().getInitialSubtitleTextWithSeparator();
    }

    public void setVisibilityFromCursor(@NonNull Cursor playlistCursor) {
        int columnIndex = playlistCursor.getColumnIndex("visibility");
        if (columnIndex >= 0) {
            setVisibility(MediaProvider.Playlists.VisibilityState.fromInt(playlistCursor.getInt(columnIndex)));
        } else {
            setVisibility(MediaProvider.Playlists.VisibilityState.NO_STATE);
        }
    }

    public int getNumberOfFollowers() {
        return this.numberOfFollowers;
    }

    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    public String toString() {
        return "Playlist{mLuid='" + this.mLuid + "', mId='" + getId() + "', mName='" + this.mName + "', mTrackCount=" + this.mTrackCount + ", mType='" + this.mType + "', mAsin'" + getAsin() + "', mVersion='" + this.mVersion + "', lastTouchedDate='" + this.lastTouchedDate + "', createdDate='" + this.createdDate + "', lastModifiedDate='" + this.lastModifiedDate + "', sharedPlaylistId='" + this.sharedId + "'}";
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected EntityItemType getEntityItemType() {
        return CollectionType.Playlist.INSTANCE;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public ContentEligibility getContentEligibility() {
        if (Feature.user_playlists_eligibility_override.isEnabled() && !isFree() && !this.mCatalogStatusTiers.getIsPrime() && !isSonicRush() && !this.mCatalogStatusTiers.getIsMusicSubscription()) {
            Log.error(Playlist.class.getSimpleName(), "No Catalog Tiers found for playlist. Hence overriding..", getEntityItem());
            return ContentEligibilityFactory.INSTANCE.createFrom(new ContentEligibilityContext(getEntityItemIdentifier(), false, isAllOwned(), isFree(), this.mCatalogStatusTiers.getIsPrime(), isSonicRush(), this.mCatalogStatusTiers.getIsMusicSubscription(), isFreeOnDemand(), isSonicRushOnDemand(), isDownloaded(), "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist".equals(getType())));
        }
        return super.getContentEligibility();
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected void initEntityItemIdentifier() {
        String str;
        EntityItemType entityItemType = getEntityItemType();
        if (entityItemType == null) {
            return;
        }
        if (getAsin() != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(getAsin()), entityItemType);
            return;
        }
        if (Feature.should_create_entity_item_identifier_by_luid.isEnabled(true) && getLuid() != null && (str = this.mType) != null && "vnd.android.cursor.item/vnd.amazonmp3.udoplaylist".equals(str)) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new UserPlaylistId(getLuid()), entityItemType);
        } else if (getId() != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(getId()), entityItemType);
        }
    }

    @Override // com.amazon.music.platform.data.entity.PlaylistEntityItem
    @Nullable
    public Integer getNumberOfTracksInPlaylist() {
        return Integer.valueOf((int) this.mTrackCount);
    }
}
