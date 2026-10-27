package ts.car.service.ipod.data;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes2.dex */
public class IpodNowPlayingUpdate {
    private String mAlbumTitle;
    private String mArtist;
    private String mComposer;
    private String mGenre;
    private String mPid;
    private String mTitle;
    private boolean mPidValid = false;
    private boolean mTitleValid = false;
    private boolean mAlbumTitleValid = false;
    private boolean mArtistValid = false;
    private boolean mGenreValid = false;
    private boolean mComposerValid = false;
    private long mPlaybackDurationMs = -1;
    private int mChapterCount = -1;
    private int mPlaybackStatus = -1;
    private long mPlaybackElapsedTimeMs = -1;
    private long mPlaybackQueueIndex = -1;
    private long mPlaybackQueueCount = -1;
    private long mPlaybackQueueChapterIndex = -1;
    private int mPlaybackShuffleMode = -1;
    private int mPlaybackRepeatMode = -1;
    private int mIsSetElapsedTimeAvailable = -1;

    public boolean isPidValid() {
        return this.mPidValid;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
        this.mPidValid = true;
    }

    public boolean isTitleValid() {
        return this.mTitleValid;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
        this.mTitleValid = true;
    }

    public boolean isAlbumTitleValid() {
        return this.mAlbumTitleValid;
    }

    public String getAlbumTitle() {
        return this.mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.mAlbumTitle = albumTitle;
        this.mAlbumTitleValid = true;
    }

    public boolean isArtistValid() {
        return this.mArtistValid;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
        this.mArtistValid = true;
    }

    public boolean isGenreValid() {
        return this.mGenreValid;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
        this.mGenreValid = true;
    }

    public boolean isComposerValid() {
        return this.mComposerValid;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String composer) {
        this.mComposer = composer;
        this.mComposerValid = true;
    }

    public long getPlaybackDurationMs() {
        return this.mPlaybackDurationMs;
    }

    public void setPlaybackDurationMs(long durationMs) {
        this.mPlaybackDurationMs = durationMs;
    }

    public int getChapterCount() {
        return this.mChapterCount;
    }

    public void setChapterCount(int count) {
        this.mChapterCount = count;
    }

    public int getPlaybackStatus() {
        return this.mPlaybackStatus;
    }

    public void setPlaybackStatus(int status) {
        this.mPlaybackStatus = status;
    }

    public long getPlaybackElapsedTimeMs() {
        return this.mPlaybackElapsedTimeMs;
    }

    public void setPlaybackElapsedTimeMs(long timeMs) {
        this.mPlaybackElapsedTimeMs = timeMs;
    }

    public long getPlaybackQueueIndex() {
        return this.mPlaybackQueueIndex;
    }

    public void setPlaybackQueueIndex(long index) {
        this.mPlaybackQueueIndex = index;
    }

    public long getPlaybackQueueCount() {
        return this.mPlaybackQueueCount;
    }

    public void setPlaybackQueueCount(long count) {
        this.mPlaybackQueueCount = count;
    }

    public long getPlaybackQueueChapterIndex() {
        return this.mPlaybackQueueChapterIndex;
    }

    public void setPlaybackQueueChapterIndex(long index) {
        this.mPlaybackQueueChapterIndex = index;
    }

    public int getPlaybackShuffleMode() {
        return this.mPlaybackShuffleMode;
    }

    public void setPlaybackShuffleMode(int mode) {
        this.mPlaybackShuffleMode = mode;
    }

    public int getPlaybackRepeatMode() {
        return this.mPlaybackRepeatMode;
    }

    public void setPlaybackRepeatMode(int mode) {
        this.mPlaybackRepeatMode = mode;
    }

    public int getIsSetElapsedTimeAvailable() {
        return this.mIsSetElapsedTimeAvailable;
    }

    public void setIsSetElapsedTimeAvailable(int available) {
        this.mIsSetElapsedTimeAvailable = available;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        StringBuilder sb = new StringBuilder();
        sb.append("IpodNowPlayingUpdate{PidValid=");
        sb.append(this.mPidValid);
        boolean z = this.mPidValid;
        String str6 = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        if (z) {
            str = ", Pid='" + this.mPid + "'";
        } else {
            str = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        sb.append(str);
        sb.append(", TitleValid=");
        sb.append(this.mTitleValid);
        if (this.mTitleValid) {
            str2 = ", Title='" + this.mTitle + "'";
        } else {
            str2 = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        sb.append(str2);
        sb.append(", AlbumTitleValid=");
        sb.append(this.mAlbumTitleValid);
        if (this.mAlbumTitleValid) {
            str3 = ", AlbumTitle='" + this.mAlbumTitle + "'";
        } else {
            str3 = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        sb.append(str3);
        sb.append(", ArtistValid=");
        sb.append(this.mArtistValid);
        if (this.mArtistValid) {
            str4 = ", Artist='" + this.mArtist + "'";
        } else {
            str4 = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        sb.append(str4);
        sb.append(", GenreValid=");
        sb.append(this.mGenreValid);
        if (this.mGenreValid) {
            str5 = ", Genre='" + this.mGenre + "'";
        } else {
            str5 = AndroidAutoConst.DEFAULT_SCREEN_NAME;
        }
        sb.append(str5);
        sb.append(", ComposerValid=");
        sb.append(this.mComposerValid);
        if (this.mComposerValid) {
            str6 = ", Composer='" + this.mComposer + "'";
        }
        sb.append(str6);
        sb.append(", PlaybackDurationMs=");
        sb.append(this.mPlaybackDurationMs);
        sb.append(", ChapterCount=");
        sb.append(this.mChapterCount);
        sb.append(", PlaybackStatus=");
        sb.append(this.mPlaybackStatus);
        sb.append(", PlaybackElapsedTimeMs=");
        sb.append(this.mPlaybackElapsedTimeMs);
        sb.append(", PlaybackQueueIndex=");
        sb.append(this.mPlaybackQueueIndex);
        sb.append(", PlaybackQueueCount=");
        sb.append(this.mPlaybackQueueCount);
        sb.append(", PlaybackQueueChapterIndex=");
        sb.append(this.mPlaybackQueueChapterIndex);
        sb.append(", PlaybackShuffleMode=");
        sb.append(this.mPlaybackShuffleMode);
        sb.append(", PlaybackRepeatMode=");
        sb.append(this.mPlaybackRepeatMode);
        sb.append(", IsSetElapsedTimeAvailable=");
        sb.append(this.mIsSetElapsedTimeAvailable);
        sb.append("}");
        return sb.toString();
    }
}
