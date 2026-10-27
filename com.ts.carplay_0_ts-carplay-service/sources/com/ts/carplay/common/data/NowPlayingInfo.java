package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class NowPlayingInfo implements Parcelable {
    public static final Parcelable.Creator<NowPlayingInfo> CREATOR = new Parcelable.Creator<NowPlayingInfo>() { // from class: com.ts.carplay.common.data.NowPlayingInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NowPlayingInfo createFromParcel(Parcel source) {
            NowPlayingInfo info = new NowPlayingInfo(source);
            return info;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NowPlayingInfo[] newArray(int size) {
            return new NowPlayingInfo[size];
        }
    };
    private String mAlbumTitle;
    private byte[] mArtworkData;
    private String mArtworkPath;
    private int mArtworkSize;
    private String mMediaItemArtist;
    private long mMediaItemPlaybackDurationMs;
    private String mMediaItemTitle;
    private String mPid;
    private long mPlaybackElapsedTimeMs;
    private int mPlaybackRepeatMode;
    private int mPlaybackShuffleMode;
    private int mPlaybackStatus;

    public static final class PlaybackRepeatMode {
        public static final int PLAYBACK_REPEAT_MODE_ALL = 2;
        public static final int PLAYBACK_REPEAT_MODE_NOT_PRESENT = -1;
        public static final int PLAYBACK_REPEAT_MODE_OFF = 0;
        public static final int PLAYBACK_REPEAT_MODE_ONE = 1;
    }

    public static final class PlaybackShuffleMode {
        public static final int PLAYBACK_SHUFFLE_MODE_ALBUMS = 2;
        public static final int PLAYBACK_SHUFFLE_MODE_NOT_PRESENT = -1;
        public static final int PLAYBACK_SHUFFLE_MODE_OFF = 0;
        public static final int PLAYBACK_SHUFFLE_MODE_SONGS = 1;
    }

    public static final class PlaybackStatus {
        public static final int PLAYBACK_STATE_PAUSED = 2;
        public static final int PLAYBACK_STATE_PLAYING = 1;
        public static final int PLAYBACK_STATE_SEEK_BACKWARD = 4;
        public static final int PLAYBACK_STATE_SEEK_FORWARD = 3;
        public static final int PLAYBACK_STATE_STOPPED = 0;
    }

    public NowPlayingInfo() {
        this.mMediaItemTitle = null;
        this.mMediaItemArtist = null;
        this.mMediaItemPlaybackDurationMs = -1L;
        this.mPlaybackStatus = 0;
        this.mPlaybackElapsedTimeMs = -1L;
        this.mPlaybackShuffleMode = -1;
        this.mPlaybackRepeatMode = -1;
        this.mArtworkSize = -1;
        this.mArtworkPath = null;
        this.mArtworkData = null;
        this.mAlbumTitle = null;
        this.mPid = null;
    }

    public void clear() {
        this.mMediaItemTitle = null;
        this.mMediaItemArtist = null;
        this.mMediaItemPlaybackDurationMs = -1L;
        this.mPlaybackStatus = 0;
        this.mPlaybackElapsedTimeMs = -1L;
        this.mPlaybackShuffleMode = -1;
        this.mPlaybackRepeatMode = -1;
        this.mArtworkSize = -1;
        this.mArtworkPath = null;
        this.mArtworkData = null;
        this.mAlbumTitle = null;
        this.mPid = null;
    }

    public NowPlayingInfo(Parcel in) {
        this.mMediaItemTitle = in.readString();
        this.mMediaItemArtist = in.readString();
        this.mMediaItemPlaybackDurationMs = in.readLong();
        this.mPlaybackStatus = in.readInt();
        this.mPlaybackElapsedTimeMs = in.readLong();
        this.mPlaybackShuffleMode = in.readInt();
        this.mPlaybackRepeatMode = in.readInt();
        this.mArtworkSize = in.readInt();
        this.mArtworkPath = in.readString();
        this.mArtworkData = in.createByteArray();
        this.mAlbumTitle = in.readString();
        this.mPid = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMediaItemTitle);
        dest.writeString(this.mMediaItemArtist);
        dest.writeLong(this.mMediaItemPlaybackDurationMs);
        dest.writeInt(this.mPlaybackStatus);
        dest.writeLong(this.mPlaybackElapsedTimeMs);
        dest.writeInt(this.mPlaybackShuffleMode);
        dest.writeInt(this.mPlaybackRepeatMode);
        dest.writeInt(this.mArtworkSize);
        dest.writeString(this.mArtworkPath);
        dest.writeByteArray(this.mArtworkData);
        dest.writeString(this.mAlbumTitle);
        dest.writeString(this.mPid);
    }

    public void copy(NowPlayingInfo info) {
        if (info != null) {
            this.mMediaItemTitle = info.getMediaItemTitle();
            this.mMediaItemArtist = info.getMediaItemArtist();
            this.mMediaItemPlaybackDurationMs = info.getMediaItemPlaybackDurationMs();
            this.mPlaybackStatus = info.getPlaybackStatus();
            this.mPlaybackElapsedTimeMs = info.getPlaybackElapsedTimeMs();
            this.mPlaybackShuffleMode = info.getPlaybackShuffleMode();
            this.mPlaybackRepeatMode = info.getPlaybackRepeatMode();
            this.mArtworkSize = info.getArtworkSize();
            this.mArtworkPath = info.getArtworkPath();
            int i = this.mArtworkSize;
            if (i <= 0) {
                this.mArtworkData = null;
            } else {
                this.mArtworkData = new byte[i];
                if (info.getArtworkData() != null) {
                    System.arraycopy(info.getArtworkData(), 0, this.mArtworkData, 0, this.mArtworkSize);
                }
            }
            this.mAlbumTitle = info.getAlbumTitle();
            this.mPid = info.getPid();
        }
    }

    public boolean isDefaultValue() {
        if (this.mMediaItemTitle != null || this.mMediaItemArtist != null || this.mMediaItemPlaybackDurationMs > 0 || this.mPlaybackStatus != 0 || this.mPlaybackElapsedTimeMs > 0 || this.mPlaybackShuffleMode > 0 || this.mPid != null || this.mPlaybackRepeatMode > 0 || this.mArtworkSize > 0 || this.mArtworkPath != null || this.mArtworkData != null || this.mAlbumTitle != null) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("NowPlayingInfo{");
        sb.append("mMediaItemTitle = ");
        sb.append(this.mMediaItemTitle);
        sb.append(" ");
        sb.append("mMediaItemArtist = ");
        sb.append(this.mMediaItemArtist);
        sb.append(" ");
        sb.append("mMediaItemPlaybackDurationMs = ");
        sb.append(this.mMediaItemPlaybackDurationMs);
        sb.append(" ");
        sb.append("mPlaybackStatus = ");
        sb.append(this.mPlaybackStatus);
        sb.append(" ");
        sb.append("mPlaybackElapsedTimeMs = ");
        sb.append(this.mPlaybackElapsedTimeMs);
        sb.append(" ");
        sb.append("mPlaybackShuffleMode = ");
        sb.append(this.mPlaybackShuffleMode);
        sb.append(" ");
        sb.append("mPlaybackRepeatMode = ");
        sb.append(this.mPlaybackRepeatMode);
        sb.append(" ");
        sb.append("mArtworkSize = ");
        sb.append(this.mArtworkSize);
        sb.append(" ");
        sb.append("mArtworkPath = ");
        sb.append(this.mArtworkPath);
        sb.append(" ");
        sb.append("mAlbumTitle = ");
        sb.append(this.mAlbumTitle);
        sb.append(" ");
        sb.append("mPid = ");
        sb.append(this.mPid);
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }

    public String getMediaItemTitle() {
        return this.mMediaItemTitle;
    }

    public void setMediaItemTitle(String mediaItemTitle) {
        this.mMediaItemTitle = mediaItemTitle;
    }

    public String getMediaItemArtist() {
        return this.mMediaItemArtist;
    }

    public void setMediaItemArtist(String mediaItemArtist) {
        this.mMediaItemArtist = mediaItemArtist;
    }

    public long getMediaItemPlaybackDurationMs() {
        return this.mMediaItemPlaybackDurationMs;
    }

    public void setMediaItemPlaybackDurationMs(long mediaItemPlaybackDurationMs) {
        this.mMediaItemPlaybackDurationMs = mediaItemPlaybackDurationMs;
    }

    public int getPlaybackStatus() {
        return this.mPlaybackStatus;
    }

    public void setPlaybackStatus(int playbackStatus) {
        this.mPlaybackStatus = playbackStatus;
    }

    public long getPlaybackElapsedTimeMs() {
        return this.mPlaybackElapsedTimeMs;
    }

    public void setPlaybackElapsedTimeMs(long playbackElapsedTimeMs) {
        this.mPlaybackElapsedTimeMs = playbackElapsedTimeMs;
    }

    public int getPlaybackShuffleMode() {
        return this.mPlaybackShuffleMode;
    }

    public void setPlaybackShuffleMode(int playbackShuffleMode) {
        this.mPlaybackShuffleMode = playbackShuffleMode;
    }

    public int getPlaybackRepeatMode() {
        return this.mPlaybackRepeatMode;
    }

    public void setPlaybackRepeatMode(int playbackRepeatMode) {
        this.mPlaybackRepeatMode = playbackRepeatMode;
    }

    public int getArtworkSize() {
        return this.mArtworkSize;
    }

    public void setArtworkSize(int artworkSize) {
        this.mArtworkSize = artworkSize;
    }

    public String getArtworkPath() {
        return this.mArtworkPath;
    }

    public void setArtworkPath(String artworkPath) {
        this.mArtworkPath = artworkPath;
    }

    public byte[] getArtworkData() {
        return this.mArtworkData;
    }

    public void setArtworkData(byte[] artworkData) {
        if (getArtworkSize() > 0 && artworkData != null) {
            byte[] bArr = new byte[getArtworkSize()];
            this.mArtworkData = bArr;
            System.arraycopy(artworkData, 0, bArr, 0, getArtworkSize());
            return;
        }
        this.mArtworkData = null;
    }

    public String getAlbumTitle() {
        return this.mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.mAlbumTitle = albumTitle;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
    }
}
