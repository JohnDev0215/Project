package com.bydux.api.module.test;

import android.graphics.Bitmap;
import com.byd.shareddata.DataUpdateInterface;

/* JADX INFO: loaded from: classes.dex */
public class MusicInfo {
    private String mAlbum;
    private String mAlbumId;
    private Bitmap mCover;
    private boolean mDownloadFree;
    private long mDuration;
    private boolean mFavorite;
    private long mId;
    private String mImageBigUrl;
    private String mImageSmallUrl;
    private String mImageUrl;
    private boolean mIsOnlineMusic;
    private String mLyric;
    private long mMusicId;
    private String mName;
    private String mPath;
    private boolean mPlayFree;
    private String mSinger;
    private String mSingerId;

    public interface MusicInfoInterface extends DataUpdateInterface {
        MusicInfo getMusicInfo();

        void setMusicInfo(MusicInfo musicInfo);
    }

    public long getmId() {
        return this.mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public long getmMusicId() {
        return this.mMusicId;
    }

    public void setmMusicId(long mMusicId) {
        this.mMusicId = mMusicId;
    }

    public String getmName() {
        return this.mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSinger() {
        return this.mSinger;
    }

    public void setmSinger(String mSinger) {
        this.mSinger = mSinger;
    }

    public String getmSingerId() {
        return this.mSingerId;
    }

    public void setmSingerId(String mSingerId) {
        this.mSingerId = mSingerId;
    }

    public String getmAlbum() {
        return this.mAlbum;
    }

    public void setmAlbum(String mAlbum) {
        this.mAlbum = mAlbum;
    }

    public String getmAlbumId() {
        return this.mAlbumId;
    }

    public void setmAlbumId(String mAlbumId) {
        this.mAlbumId = mAlbumId;
    }

    public long getmDuration() {
        return this.mDuration;
    }

    public void setmDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public String getmImageUrl() {
        return this.mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmImageBigUrl() {
        return this.mImageBigUrl;
    }

    public void setmImageBigUrl(String mImageBigUrl) {
        this.mImageBigUrl = mImageBigUrl;
    }

    public String getmImageSmallUrl() {
        return this.mImageSmallUrl;
    }

    public void setmImageSmallUrl(String mImageSmallUrl) {
        this.mImageSmallUrl = mImageSmallUrl;
    }

    public String getmPath() {
        return this.mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public String getmLyric() {
        return this.mLyric;
    }

    public void setmLyric(String mLyric) {
        this.mLyric = mLyric;
    }

    public Bitmap getmCover() {
        return this.mCover;
    }

    public void setmCover(Bitmap mCover) {
        this.mCover = mCover;
    }

    public boolean ismIsOnlineMusic() {
        return this.mIsOnlineMusic;
    }

    public void setmIsOnlineMusic(boolean mIsOnlineMusic) {
        this.mIsOnlineMusic = mIsOnlineMusic;
    }

    public boolean ismFavorite() {
        return this.mFavorite;
    }

    public void setmFavorite(boolean mFavorite) {
        this.mFavorite = mFavorite;
    }

    public boolean ismPlayFree() {
        return this.mPlayFree;
    }

    public void setmPlayFree(boolean mPlayFree) {
        this.mPlayFree = mPlayFree;
    }

    public boolean ismDownloadFree() {
        return this.mDownloadFree;
    }

    public void setmDownloadFree(boolean mDownloadFree) {
        this.mDownloadFree = mDownloadFree;
    }

    public MusicInfo(long mId, long mMusicId, String mName, String mSinger, String mSingerId, String mAlbum, String mAlbumId, long mDuration, String mImageUrl, String mImageBigUrl, String mImageSmallUrl, String mPath, String mLyric, Bitmap mCover, boolean mIsOnlineMusic, boolean mFavorite, boolean mPlayFree, boolean mDownloadFree) {
        this.mId = 0L;
        this.mMusicId = -1L;
        this.mName = "";
        this.mSinger = "";
        this.mSingerId = "";
        this.mAlbum = "";
        this.mAlbumId = "";
        this.mDuration = 0L;
        this.mImageUrl = "";
        this.mImageBigUrl = "";
        this.mImageSmallUrl = "";
        this.mPath = "";
        this.mLyric = "";
        this.mCover = null;
        this.mIsOnlineMusic = false;
        this.mFavorite = false;
        this.mPlayFree = false;
        this.mDownloadFree = true;
        this.mId = mId;
        this.mMusicId = mMusicId;
        this.mName = mName;
        this.mSinger = mSinger;
        this.mSingerId = mSingerId;
        this.mAlbum = mAlbum;
        this.mAlbumId = mAlbumId;
        this.mDuration = mDuration;
        this.mImageUrl = mImageUrl;
        this.mImageBigUrl = mImageBigUrl;
        this.mImageSmallUrl = mImageSmallUrl;
        this.mPath = mPath;
        this.mLyric = mLyric;
        this.mCover = mCover;
        this.mIsOnlineMusic = mIsOnlineMusic;
        this.mFavorite = mFavorite;
        this.mPlayFree = mPlayFree;
        this.mDownloadFree = mDownloadFree;
    }
}
