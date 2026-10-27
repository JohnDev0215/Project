package com.bydux.api.module.test;

import android.graphics.Bitmap;
import java.lang.reflect.Method;
import p4.a;

/* JADX INFO: loaded from: classes2.dex */
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

    public interface MusicInfoInterface {
        /* synthetic */ <T> void addDataUpdateListener(String str, a<T> aVar);

        /* synthetic */ <T> void addDataUpdateListener(String str, a<T> aVar, boolean z7);

        /* synthetic */ <T> void addDataUpdateListener(Method method, a<T> aVar, boolean z7);

        MusicInfo getMusicInfo();

        /* synthetic */ void removeDataUpdateListener(String str);

        /* synthetic */ void removeDataUpdateListener(Method method);

        /* synthetic */ void removeDataUpdateListener(a aVar);

        void setMusicInfo(MusicInfo musicInfo);
    }

    public MusicInfo(long j8, long j9, String str, String str2, String str3, String str4, String str5, long j10, String str6, String str7, String str8, String str9, String str10, Bitmap bitmap, boolean z7, boolean z8, boolean z9, boolean z10) {
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
        this.mId = j8;
        this.mMusicId = j9;
        this.mName = str;
        this.mSinger = str2;
        this.mSingerId = str3;
        this.mAlbum = str4;
        this.mAlbumId = str5;
        this.mDuration = j10;
        this.mImageUrl = str6;
        this.mImageBigUrl = str7;
        this.mImageSmallUrl = str8;
        this.mPath = str9;
        this.mLyric = str10;
        this.mCover = bitmap;
        this.mIsOnlineMusic = z7;
        this.mFavorite = z8;
        this.mPlayFree = z9;
        this.mDownloadFree = z10;
    }

    public String getmAlbum() {
        return this.mAlbum;
    }

    public String getmAlbumId() {
        return this.mAlbumId;
    }

    public Bitmap getmCover() {
        return this.mCover;
    }

    public long getmDuration() {
        return this.mDuration;
    }

    public long getmId() {
        return this.mId;
    }

    public String getmImageBigUrl() {
        return this.mImageBigUrl;
    }

    public String getmImageSmallUrl() {
        return this.mImageSmallUrl;
    }

    public String getmImageUrl() {
        return this.mImageUrl;
    }

    public String getmLyric() {
        return this.mLyric;
    }

    public long getmMusicId() {
        return this.mMusicId;
    }

    public String getmName() {
        return this.mName;
    }

    public String getmPath() {
        return this.mPath;
    }

    public String getmSinger() {
        return this.mSinger;
    }

    public String getmSingerId() {
        return this.mSingerId;
    }

    public boolean ismDownloadFree() {
        return this.mDownloadFree;
    }

    public boolean ismFavorite() {
        return this.mFavorite;
    }

    public boolean ismIsOnlineMusic() {
        return this.mIsOnlineMusic;
    }

    public boolean ismPlayFree() {
        return this.mPlayFree;
    }

    public void setmAlbum(String str) {
        this.mAlbum = str;
    }

    public void setmAlbumId(String str) {
        this.mAlbumId = str;
    }

    public void setmCover(Bitmap bitmap) {
        this.mCover = bitmap;
    }

    public void setmDownloadFree(boolean z7) {
        this.mDownloadFree = z7;
    }

    public void setmDuration(long j8) {
        this.mDuration = j8;
    }

    public void setmFavorite(boolean z7) {
        this.mFavorite = z7;
    }

    public void setmId(long j8) {
        this.mId = j8;
    }

    public void setmImageBigUrl(String str) {
        this.mImageBigUrl = str;
    }

    public void setmImageSmallUrl(String str) {
        this.mImageSmallUrl = str;
    }

    public void setmImageUrl(String str) {
        this.mImageUrl = str;
    }

    public void setmIsOnlineMusic(boolean z7) {
        this.mIsOnlineMusic = z7;
    }

    public void setmLyric(String str) {
        this.mLyric = str;
    }

    public void setmMusicId(long j8) {
        this.mMusicId = j8;
    }

    public void setmName(String str) {
        this.mName = str;
    }

    public void setmPath(String str) {
        this.mPath = str;
    }

    public void setmPlayFree(boolean z7) {
        this.mPlayFree = z7;
    }

    public void setmSinger(String str) {
        this.mSinger = str;
    }

    public void setmSingerId(String str) {
        this.mSingerId = str;
    }
}
