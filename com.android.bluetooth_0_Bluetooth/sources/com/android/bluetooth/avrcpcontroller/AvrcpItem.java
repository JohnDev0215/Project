package com.android.bluetooth.avrcpcontroller;

import android.bluetooth.BluetoothDevice;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import com.android.bluetooth.util.AesUtil;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpItem {
    public static final String AVRCP_ITEM_KEY_UID = "avrcp-item-key-uid";
    public static final int FOLDER_ALBUMS = 2;
    public static final int FOLDER_ARTISTS = 3;
    public static final int FOLDER_GENRES = 4;
    public static final int FOLDER_MIXED = 0;
    public static final int FOLDER_PLAYLISTS = 5;
    public static final int FOLDER_TITLES = 1;
    public static final int FOLDER_YEARS = 6;
    public static final int MEDIA_AUDIO = 0;
    public static final int MEDIA_VIDEO = 1;
    public static final int TYPE_FOLDER = 2;
    public static final int TYPE_MEDIA = 3;
    public static final int TYPE_PLAYER = 1;
    private String mAlbumName;
    private String mArtistName;
    private boolean mBrowsable;
    private String mCoverArtHandle;
    private BluetoothDevice mDevice;
    private String mDisplayableName;
    private String mGenre;
    private Uri mImageUri;
    private String mImageUuid;
    private int mItemType;
    private boolean mPlayable;
    private long mPlayingTime;
    private String mTitle;
    private long mTotalNumberOfTracks;
    private long mTrackNumber;
    private int mType;
    private long mUid;
    private String mUuid;
    private static final String TAG = "AvrcpItem";
    private static final boolean DBG = Log.isLoggable(TAG, 3);

    private AvrcpItem() {
        this.mPlayable = false;
        this.mBrowsable = false;
        this.mImageUuid = null;
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public long getUid() {
        return this.mUid;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public int getItemType() {
        return this.mItemType;
    }

    public int getType() {
        return this.mType;
    }

    public String getDisplayableName() {
        return this.mDisplayableName;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getArtistName() {
        return this.mArtistName;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public long getTrackNumber() {
        return this.mTrackNumber;
    }

    public long getTotalNumberOfTracks() {
        return this.mTotalNumberOfTracks;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public long getPlayingTime() {
        return this.mPlayingTime;
    }

    public boolean isPlayable() {
        return this.mPlayable;
    }

    public boolean isBrowsable() {
        return this.mBrowsable;
    }

    public String getCoverArtHandle() {
        return this.mCoverArtHandle;
    }

    public String getCoverArtUuid() {
        return this.mImageUuid;
    }

    public void setCoverArtUuid(String uuid) {
        this.mImageUuid = uuid;
    }

    public synchronized Uri getCoverArtLocation() {
        return this.mImageUri;
    }

    public synchronized void setCoverArtLocation(Uri uri) {
        this.mImageUri = uri;
    }

    public MediaMetadataCompat toMediaMetadata() {
        MediaMetadataCompat.Builder metaDataBuilder = new MediaMetadataCompat.Builder();
        Uri coverArtUri = getCoverArtLocation();
        String uriString = coverArtUri != null ? coverArtUri.toString() : null;
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, this.mUuid);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, this.mDisplayableName);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, this.mTitle);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, this.mArtistName);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, this.mAlbumName);
        metaDataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, this.mTrackNumber);
        metaDataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, this.mTotalNumberOfTracks);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_GENRE, this.mGenre);
        metaDataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, this.mPlayingTime);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, uriString);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ART_URI, uriString);
        metaDataBuilder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, uriString);
        if (this.mItemType == 2) {
            metaDataBuilder.putLong(MediaMetadataCompat.METADATA_KEY_BT_FOLDER_TYPE, this.mType);
        }
        return metaDataBuilder.build();
    }

    public MediaBrowserCompat.MediaItem toMediaItem() {
        MediaDescriptionCompat.Builder descriptionBuilder = new MediaDescriptionCompat.Builder();
        descriptionBuilder.setMediaId(this.mUuid);
        String name = null;
        if (this.mDisplayableName != null) {
            name = this.mDisplayableName;
        } else if (this.mTitle != null) {
            name = this.mTitle;
        }
        descriptionBuilder.setTitle(name);
        descriptionBuilder.setIconUri(getCoverArtLocation());
        Bundle extras = new Bundle();
        extras.putLong(AVRCP_ITEM_KEY_UID, this.mUid);
        descriptionBuilder.setExtras(extras);
        int flags = this.mPlayable ? 0 | 2 : 0;
        if (this.mBrowsable) {
            flags |= 1;
        }
        return new MediaBrowserCompat.MediaItem(descriptionBuilder.build(), flags);
    }

    public String toString() {
        return "AvrcpItem{mUuid=" + AesUtil.encrypt(this.mUuid) + ", mUid=" + this.mUid + ", mItemType=" + this.mItemType + ", mType=" + this.mType + ", mDisplayableName=" + this.mDisplayableName + ", mTitle=" + AesUtil.encrypt(this.mTitle) + ", mPlayable=" + this.mPlayable + ", mBrowsable=" + this.mBrowsable + ", mCoverArtHandle=" + getCoverArtHandle() + ", mImageUuid=" + this.mImageUuid + ", mImageUri" + this.mImageUri + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AvrcpItem)) {
            return false;
        }
        AvrcpItem other = (AvrcpItem) o;
        return Objects.equals(this.mUuid, other.getUuid()) && Objects.equals(this.mDevice, other.getDevice()) && Objects.equals(Long.valueOf(this.mUid), Long.valueOf(other.getUid())) && Objects.equals(Integer.valueOf(this.mItemType), Integer.valueOf(other.getItemType())) && Objects.equals(Integer.valueOf(this.mType), Integer.valueOf(other.getType())) && Objects.equals(this.mTitle, other.getTitle()) && Objects.equals(this.mDisplayableName, other.getDisplayableName()) && Objects.equals(this.mArtistName, other.getArtistName()) && Objects.equals(this.mAlbumName, other.getAlbumName()) && Objects.equals(Long.valueOf(this.mTrackNumber), Long.valueOf(other.getTrackNumber())) && Objects.equals(Long.valueOf(this.mTotalNumberOfTracks), Long.valueOf(other.getTotalNumberOfTracks())) && Objects.equals(this.mGenre, other.getGenre()) && Objects.equals(Long.valueOf(this.mPlayingTime), Long.valueOf(other.getPlayingTime())) && Objects.equals(this.mCoverArtHandle, other.getCoverArtHandle()) && Objects.equals(Boolean.valueOf(this.mPlayable), Boolean.valueOf(other.isPlayable())) && Objects.equals(Boolean.valueOf(this.mBrowsable), Boolean.valueOf(other.isBrowsable())) && Objects.equals(this.mImageUri, other.getCoverArtLocation());
    }

    public static class Builder {
        private static final int MEDIA_ATTRIBUTE_ALBUM_NAME = 3;
        private static final int MEDIA_ATTRIBUTE_ARTIST_NAME = 2;
        private static final int MEDIA_ATTRIBUTE_COVER_ART_HANDLE = 8;
        private static final int MEDIA_ATTRIBUTE_GENRE = 6;
        private static final int MEDIA_ATTRIBUTE_PLAYING_TIME = 7;
        private static final int MEDIA_ATTRIBUTE_TITLE = 1;
        private static final int MEDIA_ATTRIBUTE_TOTAL_TRACK_NUMBER = 5;
        private static final int MEDIA_ATTRIBUTE_TRACK_NUMBER = 4;
        private AvrcpItem mAvrcpItem = new AvrcpItem();
        private static final String TAG = "AvrcpItem.Builder";
        private static final boolean DBG = Log.isLoggable(TAG, 3);

        public Builder fromAvrcpAttributeArray(int[] attrIds, String[] attrMap) {
            int attributeCount = Math.max(attrIds.length, attrMap.length);
            for (int i = 0; i < attributeCount; i++) {
                if (DBG) {
                    Log.d(TAG, attrIds[i] + " = " + AesUtil.encrypt(attrMap[i]));
                }
                switch (attrIds[i]) {
                    case 1:
                        this.mAvrcpItem.mTitle = attrMap[i];
                        break;
                    case 2:
                        this.mAvrcpItem.mArtistName = attrMap[i];
                        break;
                    case 3:
                        this.mAvrcpItem.mAlbumName = attrMap[i];
                        break;
                    case 4:
                        try {
                            this.mAvrcpItem.mTrackNumber = Long.valueOf(attrMap[i]).longValue();
                        } catch (NumberFormatException e) {
                        }
                        break;
                    case 5:
                        try {
                            this.mAvrcpItem.mTotalNumberOfTracks = Long.valueOf(attrMap[i]).longValue();
                        } catch (NumberFormatException e2) {
                        }
                        break;
                    case 6:
                        this.mAvrcpItem.mGenre = attrMap[i];
                        break;
                    case 7:
                        try {
                            this.mAvrcpItem.mPlayingTime = Long.valueOf(attrMap[i]).longValue();
                        } catch (NumberFormatException e3) {
                        }
                        break;
                    case 8:
                        this.mAvrcpItem.mCoverArtHandle = attrMap[i];
                        break;
                }
            }
            return this;
        }

        public Builder setItemType(int itemType) {
            this.mAvrcpItem.mItemType = itemType;
            return this;
        }

        public Builder setType(int type) {
            this.mAvrcpItem.mType = type;
            return this;
        }

        public Builder setDevice(BluetoothDevice device) {
            this.mAvrcpItem.mDevice = device;
            return this;
        }

        public Builder setPlayable(boolean playable) {
            this.mAvrcpItem.mPlayable = playable;
            return this;
        }

        public Builder setBrowsable(boolean browsable) {
            this.mAvrcpItem.mBrowsable = browsable;
            return this;
        }

        public Builder setUid(long uid) {
            this.mAvrcpItem.mUid = uid;
            return this;
        }

        public Builder setUuid(String uuid) {
            this.mAvrcpItem.mUuid = uuid;
            return this;
        }

        public Builder setDisplayableName(String displayableName) {
            this.mAvrcpItem.mDisplayableName = displayableName;
            return this;
        }

        public Builder setTitle(String title) {
            this.mAvrcpItem.mTitle = title;
            return this;
        }

        public Builder setArtistName(String artistName) {
            this.mAvrcpItem.mArtistName = artistName;
            return this;
        }

        public Builder setAlbumName(String albumName) {
            this.mAvrcpItem.mAlbumName = albumName;
            return this;
        }

        public Builder setTrackNumber(long trackNumber) {
            this.mAvrcpItem.mTrackNumber = trackNumber;
            return this;
        }

        public Builder setTotalNumberOfTracks(long totalNumberOfTracks) {
            this.mAvrcpItem.mTotalNumberOfTracks = totalNumberOfTracks;
            return this;
        }

        public Builder setGenre(String genre) {
            this.mAvrcpItem.mGenre = genre;
            return this;
        }

        public Builder setPlayingTime(long playingTime) {
            this.mAvrcpItem.mPlayingTime = playingTime;
            return this;
        }

        public Builder setCoverArtHandle(String coverArtHandle) {
            this.mAvrcpItem.mCoverArtHandle = coverArtHandle;
            return this;
        }

        public Builder setCoverArtLocation(Uri uri) {
            this.mAvrcpItem.setCoverArtLocation(uri);
            return this;
        }

        public AvrcpItem build() {
            return this.mAvrcpItem;
        }
    }
}
