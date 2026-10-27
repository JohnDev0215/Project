package com.amazon.mp3.recentlyplayed;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.music.contentEncoding.ContentEncoding;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecentTrack implements RecentItem, Parcelable {
    public static final Parcelable.Creator<RecentTrack> CREATOR = new Parcelable.Creator<RecentTrack>() { // from class: com.amazon.mp3.recentlyplayed.RecentTrack.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentTrack createFromParcel(Parcel source) {
            RecentTrack recentTrack = new RecentTrack(source.readString(), source.readString(), source.readString());
            recentTrack.mArtworkImageUrl = source.readString();
            recentTrack.mAsin = source.readString();
            recentTrack.mLuid = source.readString();
            recentTrack.mAlbumAsin = source.readString();
            recentTrack.mLastPlayedTime = Long.valueOf(source.readLong());
            recentTrack.artworkId = Long.valueOf(source.readLong());
            recentTrack.hasLyrics = source.readInt() == 1;
            recentTrack.isExplicit = source.readInt() == 1;
            recentTrack.mIsRemote = source.readInt() == 1;
            recentTrack.mCatalogStatus = (ContentCatalogStatus) source.readSerializable();
            recentTrack.mOwnershipStatus = (ContentOwnershipStatus) source.readSerializable();
            recentTrack.mContentUnavailableReason = (ContentUnavailableReason) source.readSerializable();
            recentTrack.mCatalogStatusTiers = (CatalogStatusTiers) source.readParcelable(CatalogStatusTiers.class.getClassLoader());
            recentTrack.mContentEncodings = ContentEncodingDecoder.loadFrom((List<String>) Arrays.asList((String[]) source.readArray(String.class.getClassLoader())));
            return recentTrack;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecentTrack[] newArray(final int size) {
            return new RecentTrack[size];
        }
    };

    @Nullable
    private Long artworkId;

    @Nullable
    private String mAlbumAsin;

    @Nullable
    private String mAlbumName;

    @Nullable
    private String mArtistAsin;
    private final String mArtistName;

    @Nullable
    private String mArtworkImageUrl;

    @Nullable
    private String mAsin;
    private CatalogStatusTiers mCatalogStatusTiers;
    private List<ContentEncoding> mContentEncodings;

    @Nullable
    private ContentUnavailableReason mContentUnavailableReason;
    private int mDownloadState;

    @Nullable
    private Long mLastPlayedTime;

    @Nullable
    private String mLocalUri;

    @Nullable
    private String mLuid;
    private final String mSource;
    private final String mTitle;
    private boolean hasLyrics = false;
    private boolean isExplicit = false;
    private boolean mIsRemote = false;
    private ContentCatalogStatus mCatalogStatus = ContentCatalogStatus.UNKNOWN;
    private ContentOwnershipStatus mOwnershipStatus = ContentOwnershipStatus.UNKNOWN;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecentTrack(final String title, final String artistName, final String source) {
        this.mTitle = title;
        this.mArtistName = artistName;
        this.mSource = source;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getArtistName() {
        return this.mArtistName;
    }

    @Nullable
    public String getArtworkImageUrl() {
        return this.mArtworkImageUrl;
    }

    public void setArtworkImageUrl(@Nullable final String artworkImageUrl) {
        this.mArtworkImageUrl = artworkImageUrl;
    }

    @Nullable
    public String getAsin() {
        return this.mAsin;
    }

    public void setAsin(@Nullable final String asin) {
        this.mAsin = asin;
    }

    @Nullable
    public String getLuid() {
        return this.mLuid;
    }

    public void setLuid(@Nullable final String luid) {
        this.mLuid = luid;
    }

    @Nullable
    public String getAlbumAsin() {
        return this.mAlbumAsin;
    }

    public void setAlbumAsin(@Nullable final String albumAsin) {
        this.mAlbumAsin = albumAsin;
    }

    @Nullable
    public Long getLastPlayedTime() {
        return this.mLastPlayedTime;
    }

    public void setLastPlayedTime(@Nullable final Long lastPlayedTime) {
        this.mLastPlayedTime = lastPlayedTime;
    }

    public boolean isRemote() {
        return this.mIsRemote;
    }

    public void setRemote(final boolean remote) {
        this.mIsRemote = remote;
    }

    public void setCatalogStatus(final ContentCatalogStatus catalogStatus) {
        this.mCatalogStatus = catalogStatus;
    }

    public void setOwnershipStatus(final ContentOwnershipStatus ownershipStatus) {
        this.mOwnershipStatus = ownershipStatus;
    }

    @Override // com.amazon.mp3.recentlyplayed.RecentItem
    public void setContentUnavailableReason(@Nullable final ContentUnavailableReason contentUnavailableReason) {
        this.mContentUnavailableReason = contentUnavailableReason;
    }

    public boolean isHasLyrics() {
        return this.hasLyrics;
    }

    public void setHasLyrics(final boolean hasLyrics) {
        this.hasLyrics = hasLyrics;
    }

    public void setArtworkId(final long artworkId) {
        this.artworkId = Long.valueOf(artworkId);
    }

    public boolean isInLibrary() {
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        return contentOwnershipStatus != ContentOwnershipStatus.UNKNOWN && contentOwnershipStatus.getValue() < 300;
    }

    public void setArtistAsin(@Nullable final String artistAsin) {
        this.mArtistAsin = artistAsin;
    }

    public void setAlbumName(@Nullable final String albumName) {
        this.mAlbumName = albumName;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return this.mCatalogStatus.isHawkfire();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return this.isExplicit;
    }

    public void setExplicit(final boolean explicit) {
        this.isExplicit = explicit;
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
        ContentOwnershipStatus contentOwnershipStatus = this.mOwnershipStatus;
        return contentOwnershipStatus != null && contentOwnershipStatus.isOwned();
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public String getSource() {
        return this.mSource;
    }

    @Nullable
    public String getLocalUri() {
        return this.mLocalUri;
    }

    public void setLocalUri(@Nullable final String localUri) {
        this.mLocalUri = localUri;
    }

    public int getDownloadState() {
        return this.mDownloadState;
    }

    public void setDownloadState(final int downloadState) {
        this.mDownloadState = downloadState;
    }

    public boolean isDownloaded() {
        return this.mDownloadState == 0;
    }

    public boolean isDownloading() {
        int i = this.mDownloadState;
        return i == 1 || i == 4 || i == 3 || i == -2;
    }

    public CatalogStatusTiers getCatalogStatusTiers() {
        return this.mCatalogStatusTiers;
    }

    public void setCatalogStatusTiers(final CatalogStatusTiers catalogStatusTiers) {
        this.mCatalogStatusTiers = catalogStatusTiers;
    }

    public List<ContentEncoding> getContentEncodings() {
        return this.mContentEncodings;
    }

    public void setContentEncodings(final List<ContentEncoding> contentEncodings) {
        this.mContentEncodings = contentEncodings;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mArtistName);
        parcel.writeString(this.mSource);
        parcel.writeString(this.mArtworkImageUrl);
        parcel.writeString(this.mAsin);
        parcel.writeString(this.mLuid);
        parcel.writeString(this.mAlbumAsin);
        if (this.mLastPlayedTime == null) {
            this.mLastPlayedTime = 0L;
        }
        parcel.writeLong(this.mLastPlayedTime.longValue());
        if (this.artworkId == null) {
            this.artworkId = 0L;
        }
        parcel.writeLong(this.artworkId.longValue());
        parcel.writeInt(this.hasLyrics ? 1 : 0);
        parcel.writeInt(this.isExplicit ? 1 : 0);
        parcel.writeInt(this.mIsRemote ? 1 : 0);
        parcel.writeSerializable(this.mCatalogStatus);
        parcel.writeSerializable(this.mOwnershipStatus);
        parcel.writeSerializable(this.mContentUnavailableReason);
        parcel.writeParcelable(this.mCatalogStatusTiers, i);
        parcel.writeStringArray((String[]) ContentEncodingDecoder.parseFrom(this.mContentEncodings).toArray(new String[0]));
    }
}
