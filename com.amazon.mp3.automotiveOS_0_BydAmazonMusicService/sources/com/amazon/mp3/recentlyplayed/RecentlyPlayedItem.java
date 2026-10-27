package com.amazon.mp3.recentlyplayed;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.music.contentEncoding.ContentEncoding;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedItem implements Parcelable, RecentItem {
    public static final Parcelable.Creator<RecentlyPlayedItem> CREATOR = new Parcelable.Creator<RecentlyPlayedItem>() { // from class: com.amazon.mp3.recentlyplayed.RecentlyPlayedItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentlyPlayedItem createFromParcel(Parcel source) {
            RecentlyPlayedItem recentlyPlayedItem = new RecentlyPlayedItem((Uri) source.readParcelable(Uri.class.getClassLoader()));
            recentlyPlayedItem.mLastPlayedTime = source.readLong();
            recentlyPlayedItem.mTitle = source.readString();
            recentlyPlayedItem.mCollectionId = source.readString();
            recentlyPlayedItem.mTrackLuid = source.readString();
            recentlyPlayedItem.mTrackAsin = source.readString();
            recentlyPlayedItem.mTrackProviderState = source.readString();
            recentlyPlayedItem.mArtworkImageUrl = source.readString();
            recentlyPlayedItem.mShuffleOn = source.readInt() == 1;
            recentlyPlayedItem.mSynced = source.readInt() == 1;
            recentlyPlayedItem.mCatalogStatus = (ContentCatalogStatus) source.readSerializable();
            recentlyPlayedItem.mOwnershipStatus = (ContentOwnershipStatus) source.readSerializable();
            recentlyPlayedItem.mContentUnavailableReason = (ContentUnavailableReason) source.readSerializable();
            recentlyPlayedItem.mIsExplicit = source.readInt() == 1;
            recentlyPlayedItem.mIsRemote = source.readInt() == 1;
            recentlyPlayedItem.mCatalogStatusTiers = (CatalogStatusTiers) source.readParcelable(CatalogStatusTiers.class.getClassLoader());
            recentlyPlayedItem.mContentEncodings = ContentEncodingDecoder.loadFrom((List<String>) Arrays.asList((String[]) source.readArray(String.class.getClassLoader())));
            return recentlyPlayedItem;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentlyPlayedItem[] newArray(int size) {
            return new RecentlyPlayedItem[size];
        }
    };
    private String mArtworkImageUrl;
    private CatalogStatusTiers mCatalogStatusTiers;
    private String mCollectionAsin;
    private String mCollectionId;
    private List<ContentEncoding> mContentEncodings;
    private ContentUnavailableReason mContentUnavailableReason;
    private Uri mContentUri;
    private boolean mIsExplicit;
    private boolean mIsRemote;
    private long mLastPlayedTime;
    private String mLocalUri;
    private boolean mShuffleOn;
    private String mTitle;
    private String mTrackAsin;
    private String mTrackLuid;
    private String mTrackProviderState;
    private boolean mSynced = true;
    private ContentCatalogStatus mCatalogStatus = ContentCatalogStatus.UNKNOWN;
    private ContentOwnershipStatus mOwnershipStatus = ContentOwnershipStatus.UNKNOWN;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public enum Type {
        ARTIST("Artist"),
        ALBUM("Album"),
        GENRE("Genre"),
        PLAYLIST("Playlist"),
        SMART_PLAYLIST("SmartPlaylist"),
        PRIME_PLAYLIST("PrimePlaylist"),
        PRIME_ARTIST("PrimeArtist"),
        PRIME_BROWSE_SONGS("PrimeBrowseSongs"),
        ALL_SONGS("AllSongs"),
        STATION("Station"),
        ALEXA_RECENTS("AlexaRecents"),
        CASTING_RECENTS("CastingRecents");

        private final String value;

        Type(final String value) {
            this.value = value;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    public RecentlyPlayedItem(Uri contentUri) {
        this.mContentUri = contentUri;
    }

    public Uri getContentUri() {
        return this.mContentUri;
    }

    public void setContentUri(final Uri contentUri) {
        this.mContentUri = contentUri;
    }

    public void setLocalUri(String localUri) {
        this.mLocalUri = localUri;
    }

    public String getLocalUri() {
        return this.mLocalUri;
    }

    public Type getItemType() {
        if (MediaProvider.Albums.isAlbum(this.mContentUri)) {
            return Type.ALBUM;
        }
        if (MediaProvider.PrimeArtists.isPrimeArtist(this.mContentUri)) {
            return Type.PRIME_ARTIST;
        }
        if (MediaProvider.Artists.isArtist(this.mContentUri)) {
            return Type.ARTIST;
        }
        if (MediaProvider.Genres.isGenre(this.mContentUri)) {
            return Type.GENRE;
        }
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(this.mContentUri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(this.mContentUri)) {
            return Type.PRIME_PLAYLIST;
        }
        if (MediaProvider.SmartPlaylists.isSmartPlaylist(this.mContentUri)) {
            return Type.SMART_PLAYLIST;
        }
        if (MediaProvider.Playlists.isPlaylist(this.mContentUri)) {
            return Type.PLAYLIST;
        }
        if (MediaProvider.PrimeBrowseTracksCollection.isCollection(this.mContentUri.buildUpon().appendPath("tracks").build())) {
            return Type.PRIME_BROWSE_SONGS;
        }
        if (MediaProvider.Tracks.isTrackCollection(this.mContentUri, new String[0])) {
            return Type.ALL_SONGS;
        }
        if (MediaProvider.Tracks.isTrack(this.mContentUri)) {
            return Type.ALL_SONGS;
        }
        if (MediaProvider.Station.isStation(this.mContentUri)) {
            return Type.STATION;
        }
        if (MediaProvider.AlexaRecentlyPlayed.isAlexaRecentlyPlayedCollection(this.mContentUri)) {
            return Type.ALEXA_RECENTS;
        }
        if (MediaProvider.CastingRecentlyPlayed.isCastingRecentlyPlayedCollection(this.mContentUri)) {
            return Type.CASTING_RECENTS;
        }
        throw new IllegalStateException("Unable to determine item type from content uri: " + this.mContentUri.toString());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentlyPlayedItem$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[Type.PRIME_PLAYLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.ALEXA_RECENTS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.ALBUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.PRIME_ARTIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.ALL_SONGS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.PRIME_BROWSE_SONGS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.ARTIST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.SMART_PLAYLIST.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.GENRE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.STATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[Type.CASTING_RECENTS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public CatalogContent convertToCatalogContent() {
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[getItemType().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return convertToPrimePlaylist();
        }
        if (i == 4) {
            return convertToPrimeAlbum();
        }
        if (i != 5) {
            return null;
        }
        return convertToPrimeArtist();
    }

    private PrimeAlbum convertToPrimeAlbum() {
        return new PrimeAlbum(getTitle(), getCollectionAsin(), getArtworkImageUrl(), null, null, 0, this.mOwnershipStatus, this.mCatalogStatusTiers);
    }

    private PrimePlaylist convertToPrimePlaylist() {
        return new PrimePlaylist(getTitle(), getCollectionAsin(), getArtworkImageUrl(), null, 0.0f, 0, 0, null, null, this.mCatalogStatusTiers);
    }

    public PrimeArtist convertToPrimeArtist() {
        return new PrimeArtist(getTitle(), getCollectionAsin(), getCollectionAsin(), 0, 0, getArtworkImageUrl(), ContentOwnershipStatus.ADDED, this.mCatalogStatusTiers);
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public String getSource() {
        return MediaProvider.getSource(this.mContentUri);
    }

    public long getLastPlayedTime() {
        return this.mLastPlayedTime;
    }

    public void setLastPlayedTime(long lastPlayedTime) {
        this.mLastPlayedTime = lastPlayedTime;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getCollectionId() {
        return this.mCollectionId;
    }

    public void setCollectionId(String collectionId) {
        this.mCollectionId = collectionId;
    }

    public String getTrackLuid() {
        return this.mTrackLuid;
    }

    public String getTrackAsin() {
        return this.mTrackAsin;
    }

    public void setTrackLuid(String trackLuid) {
        this.mTrackLuid = trackLuid;
    }

    public void setTrackAsin(String trackAsin) {
        this.mTrackAsin = trackAsin;
    }

    public String getTrackProviderState() {
        return this.mTrackProviderState;
    }

    public void setTrackProviderState(String providerStateJson) {
        this.mTrackProviderState = providerStateJson;
    }

    public boolean isShuffleOn() {
        return this.mShuffleOn;
    }

    public void setShuffleOn(boolean shuffleOn) {
        this.mShuffleOn = shuffleOn;
    }

    public String getArtworkImageUrl() {
        return this.mArtworkImageUrl;
    }

    public void setArtworkImageUrl(String artworkImageUrl) {
        this.mArtworkImageUrl = artworkImageUrl;
    }

    public void setSynced(boolean synced) {
        this.mSynced = synced;
    }

    public boolean isSynced() {
        return this.mSynced;
    }

    public boolean isPrime() {
        return this.mCatalogStatus.isPrime() && (this.mOwnershipStatus.isOwned() ^ true);
    }

    public void setOwnershipStatus(ContentOwnershipStatus ownershipStatus) {
        this.mOwnershipStatus = ownershipStatus;
    }

    public ContentOwnershipStatus getOwnershipStatus() {
        return this.mOwnershipStatus;
    }

    public void setContentCatalogStatus(ContentCatalogStatus contentCatalogStatus) {
        this.mCatalogStatus = contentCatalogStatus;
    }

    public ContentCatalogStatus getCatalogStatus() {
        return this.mCatalogStatus;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return this.mCatalogStatus.isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        if (this.mCatalogStatus.isPreviouslyCatalog()) {
            return ContentCatalogStatus.UNKNOWN;
        }
        return this.mCatalogStatus;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllPreviouslyCatalog() {
        return this.mCatalogStatus.isPreviouslyCatalog();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        return this.mOwnershipStatus.isOwned();
    }

    @Override // com.amazon.mp3.recentlyplayed.RecentItem
    public void setContentUnavailableReason(ContentUnavailableReason contentUnavailableReason) {
        this.mContentUnavailableReason = contentUnavailableReason;
    }

    public ContentUnavailableReason getContentUnavailableReason() {
        return this.mContentUnavailableReason;
    }

    public void setCollectionAsin(String asin) {
        this.mCollectionAsin = asin;
    }

    public String getCollectionAsin() {
        return this.mCollectionAsin;
    }

    public void setIsExplicit(final boolean isExplicit) {
        this.mIsExplicit = isExplicit;
    }

    public void setIsRemote(final boolean isRemote) {
        this.mIsRemote = isRemote;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return this.mIsExplicit;
    }

    public boolean isRemote() {
        return this.mIsRemote;
    }

    public CatalogStatusTiers getCatalogStatusTiers() {
        return this.mCatalogStatusTiers;
    }

    public void setCatalogStatusTiers(final CatalogStatusTiers catalogStatusTiers) {
        if (catalogStatusTiers == null) {
            catalogStatusTiers = CatalogStatusTiers.fallbackValues();
        }
        this.mCatalogStatusTiers = catalogStatusTiers;
        this.mCatalogStatus = ContentCatalogStatus.fromValues(catalogStatusTiers, isAllOwned(), false);
    }

    public List<ContentEncoding> getContentEncoding() {
        return this.mContentEncodings;
    }

    public void setContentEncoding(final List<ContentEncoding> contentEncodings) {
        this.mContentEncodings = contentEncodings;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mContentUri, i);
        parcel.writeLong(this.mLastPlayedTime);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mCollectionId);
        parcel.writeString(this.mTrackLuid);
        parcel.writeString(this.mTrackAsin);
        parcel.writeString(this.mTrackProviderState);
        parcel.writeString(this.mArtworkImageUrl);
        parcel.writeInt(this.mShuffleOn ? 1 : 0);
        parcel.writeInt(this.mSynced ? 1 : 0);
        parcel.writeSerializable(this.mCatalogStatus);
        parcel.writeSerializable(this.mOwnershipStatus);
        parcel.writeSerializable(this.mContentUnavailableReason);
        parcel.writeInt(this.mIsExplicit ? 1 : 0);
        parcel.writeInt(this.mIsRemote ? 1 : 0);
        parcel.writeParcelable(this.mCatalogStatusTiers, i);
        parcel.writeStringArray((String[]) ContentEncodingDecoder.parseFrom(this.mContentEncodings).toArray(new String[0]));
    }

    public String getLibraryItemName(Resources resources) {
        switch (AnonymousClass2.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[getItemType().ordinal()]) {
            case 1:
            case 2:
            case 9:
                return resources.getString(R.string.dmusic_library_item_name_playlist);
            case 3:
            case 12:
                return resources.getString(R.string.dmusic_recently_played_alexa);
            case 4:
                return resources.getString(R.string.dmusic_library_item_name_album);
            case 5:
            case 8:
                return resources.getString(R.string.dmusic_library_item_name_artist);
            case 6:
            case 7:
                return resources.getString(R.string.dmusic_library_item_name_track);
            case 10:
                return resources.getString(R.string.dmusic_library_item_name_genre);
            case 11:
                return resources.getString(R.string.dmusic_library_item_name_station);
            default:
                return "";
        }
    }

    public void updateShareMenuItemTitle(final ContextMenu menu) {
        MenuItem menuItemFindItem = menu.findItem(R.id.MenuShare);
        int i = AnonymousClass2.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[getItemType().ordinal()];
        if (i == 1 || i == 2) {
            menuItemFindItem.setTitle(R.string.dmusic_context_share_playlist);
            return;
        }
        if (i == 4) {
            menuItemFindItem.setTitle(R.string.dmusic_context_share_album);
            return;
        }
        if (i == 5 || i == 8) {
            menuItemFindItem.setTitle(R.string.dmusic_context_share_artist);
        } else {
            if (i != 11) {
                return;
            }
            menuItemFindItem.setTitle(R.string.dmusic_context_share_station);
        }
    }

    public boolean shouldShowShareMenuItem() {
        return (getItemType() == Type.ALL_SONGS || getItemType() == Type.SMART_PLAYLIST || getItemType() == Type.GENRE || (TextUtils.isEmpty(getCollectionAsin()) && getItemType() != Type.STATION) || !AmazonApplication.getCapabilities().isSharingSupported() || !canShareUserPlaylist()) ? false : true;
    }

    private boolean canShareUserPlaylist() {
        if (getItemType() != Type.PLAYLIST) {
            return true;
        }
        return "cirrus".equals(getSource());
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
