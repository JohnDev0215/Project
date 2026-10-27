package com.amazon.mp3.library.item;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.EligibilityConverterUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.platform.providers.NestedEligibilitiesProvider;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class MusicTrack extends MarketplaceDependentItem implements Parcelable, Refinable, NestedEligibilitiesProvider {
    private Album mAlbum;
    private long mAlbumArtId;
    private String mAlbumArtUrl;
    private String mAlbumArtistName;
    private String mAlbumAsin;
    private long mAlbumId;
    private String mAlbumName;
    private PlaymodeEligibility mAlbumPlaymodeEligibility;
    private String mArtistAsin;
    private long mArtistId;
    private String mArtistName;
    private List<AssetQuality> mAssetQualities;
    private PrimePlaylistTracksTable.AssetType mAssetType;
    protected ContentCatalogStatus mCatalogStatus;
    private Uri mCollectionUri;
    private List<ContentEncoding> mContentEncodings;
    private long mDateCreated;
    private long mDateUpdated;
    private int mDiscNumber;
    private long mDuration;
    private String mExtension;
    private boolean mHasLyrics;
    private boolean mIsExclusionTagged;
    private boolean mIsExplicit;
    private boolean mIsLiked;
    private boolean mIsOwnedAndOnlyDownloadedInPrimePlaylist;
    private String mLocalUri;
    private String mLuid;
    private long mMatchHash;
    private ContentOwnershipStatus mOwnershipStatus;
    private EntityEligibilitiesProvider mParentEligibilities;
    private int mPlaylistTrackStatus;
    private double mPopularity;
    private String mSortTitle;
    private String mTitle;
    private long mTrackId;
    private long mTrackNum;
    private TrackType mTrackType;
    private static final String TAG = MusicTrack.class.getSimpleName();
    public static final Parcelable.Creator<MusicTrack> CREATOR = new Parcelable.Creator<MusicTrack>() { // from class: com.amazon.mp3.library.item.MusicTrack.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicTrack createFromParcel(Parcel source) {
            MusicTrack musicTrack = new MusicTrack(null, Uri.parse(source.readString()));
            musicTrack.mLuid = source.readString();
            musicTrack.mAsin = source.readString();
            musicTrack.mTitle = source.readString();
            musicTrack.mAlbumName = source.readString();
            musicTrack.mAlbumId = source.readLong();
            musicTrack.mAlbumAsin = source.readString();
            musicTrack.mAlbumArtId = source.readLong();
            musicTrack.mArtistId = source.readLong();
            musicTrack.mArtistAsin = source.readString();
            musicTrack.mArtistName = source.readString();
            musicTrack.mAlbumArtistName = source.readString();
            musicTrack.mAlbumArtUrl = source.readString();
            musicTrack.mTrackType = TrackType.fromTypeId(source.readInt());
            musicTrack.mTrackNum = source.readLong();
            musicTrack.mLocalUri = source.readString();
            musicTrack.mMatchHash = source.readLong();
            musicTrack.mDuration = source.readLong();
            musicTrack.mOwnershipStatus = ContentOwnershipStatus.fromValue(source.readInt());
            musicTrack.mCatalogStatus = ContentCatalogStatus.fromValue(source.readInt());
            musicTrack.mHasLyrics = LyricsStatus.fromString(source.readString()) == LyricsStatus.AVAILABLE;
            musicTrack.setDownloadState(source.readInt());
            musicTrack.setIsExplicit(source.readInt() == 1);
            musicTrack.setAssetQualities(CatalogPlaylistTrack.getAssetQualityFromJson(source.readString()));
            PrimePlaylistTracksTable.AssetType assetType = PrimePlaylistTracksTable.AssetType.VIDEO;
            if (!assetType.toString().equals(source.readString())) {
                assetType = PrimePlaylistTracksTable.AssetType.AUDIO;
            }
            musicTrack.setAssetType(assetType);
            musicTrack.setIsExclusionTagged(source.readInt() == 1);
            musicTrack.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(source.readString()));
            musicTrack.setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.deserialize(source.readInt()));
            return musicTrack;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicTrack[] newArray(int size) {
            return new MusicTrack[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public MusicTrack() {
        this.mIsLiked = false;
    }

    public MusicTrack(LibraryItemFactory libItemFactory, Uri contentUri) {
        super(libItemFactory, contentUri);
        this.mIsLiked = false;
    }

    public MusicTrack(MusicTrack track) {
        this.mIsLiked = false;
        setContentUri(track.getContentUri());
        setLuid(track.getLuid());
        setAsin(track.getAsin());
        setTrackId(track.getTrackId());
        setTitle(track.getTitle());
        setAlbumId(track.getAlbumId());
        setAlbumAsin(track.getAlbumAsin());
        setArtistId(track.getArtistId());
        setArtistAsin(track.getArtistAsin());
        setTrackNum(track.getTrackNum());
        setLocalUri(track.getLocalUri());
        setMatchHash(track.getMatchHash());
        setExtension(track.getExtension());
        setDuration(track.getDuration());
        setArtistName(track.getArtistName());
        setAlbumName(track.getAlbumName());
        setAlbumArtistName(track.getAlbumArtistName());
        setCollectionUri(track.getCollectionUri());
        setAlbumArtId(track.getAlbumArtId());
        setAlbumArtUrl(track.getAlbumArtUrl());
        setTrackType(track.getType());
        setLyricsState(track.hasLyrics());
        setOwnershipStatus(track.getOwnershipStatus());
        setContentCatalogStatus(track.getContentCatalogStatus());
        setOwnedAndOnlyDownloadedInPrimePlaylist(track.isOwnedAndOnlyDownloadedInPrimePlaylist());
        setDownloadState(track.getDownloadState());
        setIsExplicit(track.isExplicit());
        setPopularity(track.getPopularity());
        setContentEncodings(track.getContentEncodings());
        setPlaylistTrackStatus(track.getPlaylistTrackStatus());
        setAssetQualities(track.getAssetQualities());
        setAssetType(track.getAssetType());
        setIsExclusionTagged(track.isExclusionTagged());
        setPlaymodeEligibility(track.getPlaymodeEligibility());
        setAlbumPlaymodeEligibility(track.getAlbumPlaymodeEligibility());
        setCatalogStatusTiers(track.getCatalogStatusTiers());
    }

    public List<ContentEncoding> getContentEncodings() {
        return this.mContentEncodings;
    }

    public void setContentEncodings(List<ContentEncoding> contentEncodings) {
        this.mContentEncodings = contentEncodings;
    }

    public double getPopularity() {
        return this.mPopularity;
    }

    public void setPopularity(final double popularity) {
        this.mPopularity = popularity;
    }

    public String getLuid() {
        return this.mLuid;
    }

    public void setLuid(String luid) {
        this.mLuid = luid;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getAsin() {
        return this.mAsin;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setAsin(String asin) {
        this.mAsin = asin;
    }

    public long getTrackId() {
        return this.mTrackId;
    }

    public void setTrackId(long trackId) {
        this.mTrackId = trackId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getSortTitle() {
        return this.mSortTitle;
    }

    public void setSortTitle(String sortTitle) {
        this.mSortTitle = sortTitle;
    }

    public Album getAlbum() {
        if (this.mAlbum == null) {
            this.mAlbum = AmazonApplication.getLibraryItemFactory().getAlbumForTrack(this);
        }
        return this.mAlbum;
    }

    public long getAlbumId() {
        return this.mAlbumId;
    }

    public void setAlbumId(long albumId) {
        this.mAlbumId = albumId;
    }

    public String getAlbumAsin() {
        return this.mAlbumAsin;
    }

    public void setAlbumAsin(String albumAsin) {
        this.mAlbumAsin = albumAsin;
    }

    public long getArtistId() {
        return this.mArtistId;
    }

    public void setArtistId(long artistId) {
        this.mArtistId = artistId;
    }

    public String getArtistAsin() {
        return this.mArtistAsin;
    }

    public void setArtistAsin(String artistAsin) {
        this.mArtistAsin = artistAsin;
    }

    public long getTrackNum() {
        return this.mTrackNum;
    }

    public void setTrackNum(long trackNum) {
        this.mTrackNum = trackNum;
    }

    public String getLocalUri() {
        return this.mLocalUri;
    }

    public void setLocalUri(String localUri) {
        this.mLocalUri = localUri;
    }

    @Deprecated
    public long getMatchHash() {
        return this.mMatchHash;
    }

    public void setMatchHash(long matchHash) {
        this.mMatchHash = matchHash;
    }

    public String getExtension() {
        return this.mExtension;
    }

    public void setExtension(String mExtension) {
        this.mExtension = mExtension;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public void setDuration(long duration) {
        this.mDuration = duration;
    }

    public long getDateUpdated() {
        return this.mDateUpdated;
    }

    public void setDateUpdated(long dateUpdated) {
        this.mDateUpdated = dateUpdated;
    }

    public long getDateCreated() {
        return this.mDateCreated;
    }

    public void setDateCreated(long mDateCreated) {
        this.mDateCreated = mDateCreated;
    }

    public String getArtistName() {
        return this.mArtistName;
    }

    public void setArtistName(String artistName) {
        this.mArtistName = artistName;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public void setAlbumName(String albumName) {
        this.mAlbumName = albumName;
    }

    public String getAlbumArtistName() {
        return this.mAlbumArtistName;
    }

    public void setAlbumArtistName(String albumArtistName) {
        this.mAlbumArtistName = albumArtistName;
    }

    @Override // com.amazon.mp3.library.item.MarketplaceDependentItem
    public Uri getContentUri(String source) {
        return MediaProvider.Tracks.getContentUri(source, getId());
    }

    public void setCollectionUri(Uri collectionUri) {
        this.mCollectionUri = collectionUri;
    }

    public Uri getCollectionUri() {
        return this.mCollectionUri;
    }

    public long getAlbumArtId() {
        return this.mAlbumArtId;
    }

    public void setAlbumArtId(long albumArtId) {
        this.mAlbumArtId = albumArtId;
    }

    public String getAlbumArtUrl() {
        return this.mAlbumArtUrl;
    }

    public void setAlbumArtUrl(String albumArtUrl) {
        this.mAlbumArtUrl = albumArtUrl;
    }

    public TrackType getType() {
        return this.mTrackType;
    }

    public void setTrackType(TrackType type) {
        this.mTrackType = type;
    }

    public boolean hasLyrics() {
        return this.mHasLyrics;
    }

    public void setLyricsState(final boolean hasLyrics) {
        this.mHasLyrics = hasLyrics;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.mAssetQualities;
    }

    public void setAssetQualities(final List<AssetQuality> assetQualities) {
        this.mAssetQualities = assetQualities;
    }

    public void setIsExclusionTagged(boolean isExclusionTagged) {
        this.mIsExclusionTagged = isExclusionTagged;
    }

    public boolean isExclusionTagged() {
        return this.mIsExclusionTagged;
    }

    public PrimePlaylistTracksTable.AssetType getAssetType() {
        return this.mAssetType;
    }

    public void setAssetType(final PrimePlaylistTracksTable.AssetType assetType) {
        this.mAssetType = assetType;
    }

    public boolean isVideo() {
        return PrimePlaylistTracksTable.AssetType.VIDEO.equals(this.mAssetType);
    }

    public void setOwnershipStatus(ContentOwnershipStatus ownershipStatus) {
        this.mOwnershipStatus = ownershipStatus;
        setMinContentOwnershipStatusValue(ownershipStatus.getValue());
    }

    public ContentOwnershipStatus getOwnershipStatus() {
        return this.mOwnershipStatus;
    }

    public void setContentCatalogStatus(ContentCatalogStatus catalogStatus) {
        if (shouldUpdateContentCatalogStatus()) {
            this.mCatalogStatus = catalogStatus;
            setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(catalogStatus.getSupportedTiers()));
        } else {
            this.mCatalogStatus = getMaxContentCatalogStatus();
        }
    }

    public ContentCatalogStatus getContentCatalogStatus() {
        if (this.mCatalogStatus == null) {
            this.mCatalogStatus = getMaxContentCatalogStatus();
        }
        return this.mCatalogStatus;
    }

    public void setAlbumPlaymodeEligibility(PlaymodeEligibility albumPlaymodeEligibility) {
        this.mAlbumPlaymodeEligibility = albumPlaymodeEligibility;
    }

    public PlaymodeEligibility getAlbumPlaymodeEligibility() {
        return this.mAlbumPlaymodeEligibility;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setCatalogStatusTiers(final CatalogStatusTiers catalogStatusTiers) {
        if (this.mMaxContentCatalogStatusValue == 0) {
            if (catalogStatusTiers == null) {
                catalogStatusTiers = CatalogStatusTiers.fallbackValues();
            }
            this.mCatalogStatusTiers = catalogStatusTiers;
            refreshEligibilities();
        } else {
            super.setCatalogStatusTiers(catalogStatusTiers);
        }
        if (this.mCatalogStatus != null) {
            this.mCatalogStatus = getMaxContentCatalogStatus();
        }
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected ContentCatalogStatus getMinCatalogStatusFromTiers() {
        return ContentCatalogStatus.PrimeContentCatalogStatus.getMinContentCatalogStatus(this.mCatalogStatusTiers, isVideo());
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected ContentCatalogStatus getMaxCatalogStatusFromTiers() {
        return ContentCatalogStatus.PrimeContentCatalogStatus.getMaxContentCatalogStatus(this.mCatalogStatusTiers, isVideo());
    }

    public void setIsExplicit(boolean isExplicit) {
        this.mIsExplicit = isExplicit;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return this.mIsExplicit;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected EntityItemType getEntityItemType() {
        return SingleType.Track.INSTANCE;
    }

    public enum PlaylistTrackStatus {
        NOT_CATALOG_TRACK(0),
        CATALOG_TRACK_NOT_IN_LIBRARY(1),
        CATALOG_TRACK_IN_LIBRARY(2),
        LOCAL_TRACK_IN_PLAYLIST_NOT_IN_OFFLINE_LIBRARY(3);

        private int mValue;

        PlaylistTrackStatus(int value) {
            this.mValue = value;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public void setPlaylistTrackStatus(int playlistTrackStatus) {
        this.mPlaylistTrackStatus = playlistTrackStatus;
    }

    public int getPlaylistTrackStatus() {
        return this.mPlaylistTrackStatus;
    }

    public Boolean isAddableToLibrary() {
        return Boolean.valueOf(this.mPlaylistTrackStatus < PlaylistTrackStatus.CATALOG_TRACK_IN_LIBRARY.getValue());
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public int getMinContentOwnershipStatusValue() {
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        if (contentOwnershipStatus != null) {
            return contentOwnershipStatus.getValue();
        }
        Log.error(TAG, "Attempted to call Track.getMinContentOwnershipStatusValue(), but mOwnershipStatus has not been set");
        return ContentOwnershipStatus.UNKNOWN.getValue();
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public int getMaxContentOwnershipStatusValue() {
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        if (contentOwnershipStatus != null) {
            return contentOwnershipStatus.getValue();
        }
        Log.error(TAG, "Attempted to call Track.getMaxContentOwnershipStatusValue(), but mOwnershipStatus has not been set");
        return ContentOwnershipStatus.UNKNOWN.getValue();
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public int getMinContentCatalogStatusValue() {
        return getContentCatalogStatus().getValue();
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setMinContentOwnershipStatusValue(int minOwnershipStatusValue) {
        super.setMinContentOwnershipStatusValue(minOwnershipStatusValue);
        this.mOwnershipStatus = ContentOwnershipStatus.fromValue(minOwnershipStatusValue);
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setMaxContentOwnershipStatusValue(int maxOwnershipStatusValue) {
        super.setMinContentOwnershipStatusValue(maxOwnershipStatusValue);
        this.mOwnershipStatus = ContentOwnershipStatus.fromValue(maxOwnershipStatusValue);
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setMinContentCatalogStatusValue(int minPrimeStatusValue) {
        if (shouldUpdateContentCatalogStatus()) {
            super.setMinContentCatalogStatusValue(minPrimeStatusValue);
            this.mCatalogStatus = ContentCatalogStatus.fromValue(minPrimeStatusValue);
        }
    }

    @Deprecated
    public boolean isPrime() {
        return getContentCatalogStatus() == ContentCatalogStatus.PRIME;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setMaxNonPreviousContentCatalogStatusValue(int maxPrimeStatusValue) {
        setContentCatalogStatus(ContentCatalogStatus.fromValue(maxPrimeStatusValue));
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public int getMaxNonPreviousContentCatalogStatusValue() {
        return (getContentCatalogStatus().isPreviouslyCatalog() ? ContentCatalogStatus.UNKNOWN : this.mCatalogStatus).getValue();
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setMaxContentCatalogStatusValue(int maxPrimeStatusValue) {
        setContentCatalogStatus(ContentCatalogStatus.fromValue(maxPrimeStatusValue));
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public int getMaxContentCatalogStatusValue() {
        return getContentCatalogStatus().getValue();
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return AmazonApplication.getContext().getString(R.string.dmusic_library_item_name_track);
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public boolean isAvailable() {
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && isExplicit()) {
            return false;
        }
        return super.isAvailable();
    }

    public boolean isShareable() {
        if (!isPureCatalog() || isCatalogAccessible()) {
            return (isAllPreviouslyCatalog() && isNotOwned()) ? false : true;
        }
        return false;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public boolean isAvailableOffline() {
        ContentUnavailableReason catalogContentUnavailableReason = ContentAccessUtil.getCatalogContentUnavailableReason(this, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE);
        if (catalogContentUnavailableReason == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK || catalogContentUnavailableReason == ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE || catalogContentUnavailableReason == ContentUnavailableReason.NOT_AVAILABLE_OFFLINE) {
            return false;
        }
        if (isAllCatalog() && catalogContentUnavailableReason != null) {
            return false;
        }
        if (isOwnedAndOnlyDownloadedInPrimePlaylist()) {
            return catalogContentUnavailableReason == null;
        }
        return ContentAccessUtil.isAvailableOnDisk(this);
    }

    public ContentUnavailableReason getOfflinePlaybackUnavailabilityReason() {
        int downloadState = getDownloadState();
        if (downloadState == 0) {
            return ContentAccessUtil.getCatalogContentUnavailableReason(this, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE);
        }
        if (downloadState == 6) {
            return ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE;
        }
        if (downloadState != 7) {
            return null;
        }
        return ContentUnavailableReason.DOWNLOAD_CORRUPTED;
    }

    public void setOwnedAndOnlyDownloadedInPrimePlaylist(boolean value) {
        this.mIsOwnedAndOnlyDownloadedInPrimePlaylist = value;
    }

    public boolean isOwnedAndOnlyDownloadedInPrimePlaylist() {
        return this.mIsOwnedAndOnlyDownloadedInPrimePlaylist;
    }

    public void setDiscNumber(int discNumber) {
        this.mDiscNumber = discNumber;
    }

    public int getDiscNumber() {
        return this.mDiscNumber;
    }

    public void setParentEligibilities(final EntityEligibilitiesProvider parentEligibilities) {
        this.mParentEligibilities = parentEligibilities;
    }

    @Override // com.amazon.music.platform.providers.NestedEligibilitiesProvider
    public EntityEligibilitiesProvider getParentEligibilities() {
        return this.mParentEligibilities;
    }

    public void setIsLiked(boolean isLiked) {
        this.mIsLiked = isLiked;
    }

    public boolean isLiked() {
        return this.mIsLiked;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getContentUri().toString());
        parcel.writeString(this.mLuid);
        parcel.writeString(this.mAsin);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mAlbumName);
        parcel.writeLong(this.mAlbumId);
        parcel.writeString(this.mAlbumAsin);
        parcel.writeLong(this.mAlbumArtId);
        parcel.writeLong(this.mArtistId);
        parcel.writeString(this.mArtistAsin);
        parcel.writeString(this.mArtistName);
        parcel.writeString(this.mAlbumArtistName);
        parcel.writeString(this.mAlbumArtUrl);
        TrackType trackType = this.mTrackType;
        if (trackType == null) {
            trackType = TrackType.LIBRARY;
        }
        parcel.writeInt(trackType.getTypeId());
        parcel.writeLong(this.mTrackNum);
        parcel.writeString(this.mLocalUri);
        parcel.writeLong(this.mMatchHash);
        parcel.writeLong(this.mDuration);
        parcel.writeInt(getMinContentOwnershipStatusValue());
        parcel.writeInt(getMinContentCatalogStatusValue());
        parcel.writeString((this.mHasLyrics ? LyricsStatus.AVAILABLE : LyricsStatus.NO_LYRICS).toString());
        parcel.writeInt(getDownloadState());
        parcel.writeInt(this.mIsExplicit ? 1 : 0);
        parcel.writeString(CatalogPlaylistTrack.getAssetQualitiesString(this.mAssetQualities));
        PrimePlaylistTracksTable.AssetType assetType = this.mAssetType;
        parcel.writeString(assetType != null ? assetType.toString() : null);
        parcel.writeInt(this.mIsExclusionTagged ? 1 : 0);
        parcel.writeString(PlaymodeEligibility.INSTANCE.getPlaymodeEligibilityString(getPlaymodeEligibility()));
        parcel.writeInt(getCatalogStatusTiers().serialize());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("uri[");
        sb.append(getContentUri());
        sb.append(']');
        sb.append(" luid[");
        sb.append(this.mLuid);
        sb.append(']');
        sb.append(" asin[");
        sb.append(this.mAsin);
        sb.append(']');
        sb.append(" title[");
        sb.append(this.mTitle);
        sb.append(']');
        sb.append(" albumName[");
        sb.append(this.mAlbumName);
        sb.append(']');
        sb.append(" albumId[");
        sb.append(this.mAlbumId);
        sb.append(']');
        sb.append(" albumAsin[");
        sb.append(this.mAlbumAsin);
        sb.append(']');
        sb.append(" albumArtId[");
        sb.append(this.mAlbumArtId);
        sb.append(']');
        sb.append(" artistId[");
        sb.append(this.mArtistId);
        sb.append(']');
        sb.append(" artistAsin[");
        sb.append(this.mArtistAsin);
        sb.append(']');
        sb.append(" artistName[");
        sb.append(this.mArtistName);
        sb.append(']');
        sb.append(" albumArtistName[");
        sb.append(this.mAlbumArtistName);
        sb.append(']');
        sb.append(" albumArtUrl[");
        sb.append(this.mAlbumArtUrl);
        sb.append(']');
        sb.append(" trackType[");
        TrackType trackType = this.mTrackType;
        sb.append(trackType == null ? "" : Integer.valueOf(trackType.getTypeId()));
        sb.append(']');
        sb.append(" trackNum[");
        sb.append(this.mTrackNum);
        sb.append(']');
        sb.append(" localUri[");
        sb.append(this.mLocalUri);
        sb.append(']');
        sb.append(" matchHash[");
        sb.append(this.mMatchHash);
        sb.append(']');
        sb.append(" duration[");
        sb.append(this.mDuration);
        sb.append(']');
        sb.append(" ownershipStatus[");
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        sb.append(contentOwnershipStatus == null ? "" : Integer.valueOf(contentOwnershipStatus.getValue()));
        sb.append(']');
        sb.append(" mCatalogStatus[");
        ContentCatalogStatus contentCatalogStatus = this.mCatalogStatus;
        sb.append(contentCatalogStatus != null ? Integer.valueOf(contentCatalogStatus.getValue()) : "");
        sb.append(']');
        sb.append(" hasLyrics[");
        sb.append((this.mHasLyrics ? LyricsStatus.AVAILABLE : LyricsStatus.NO_LYRICS).toString());
        sb.append(']');
        return sb.toString();
    }
}
