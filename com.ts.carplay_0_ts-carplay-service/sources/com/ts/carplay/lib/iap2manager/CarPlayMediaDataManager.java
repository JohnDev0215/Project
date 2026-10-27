package com.ts.carplay.lib.iap2manager;

import android.content.Context;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.linkadapter.iap.data.MediaLibraryUpdate;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayMediaDataManager {
    public static final int CARPLAY_PAUSE = 1;
    public static final int CARPLAY_PLAY = 2;
    public static final int IAP_PAUSE = 2;
    public static final int IAP_PLAY = 1;
    public static final int IPOD_MEDIALIB_NEW_ITEM = 1;
    public static final int PLAY_BACK_SECONDS = 180;
    public static final int SONG_DURATION_SECONDS = 180;
    public static final int SONG_RATINGS = 20;
    private static final String TAG = "CarPlayMediaDataManager";
    private static Context mContext;
    private int mCarPlayMusicStatus;
    private CarPlayManager mManager;
    private MediaLibraryUpdate mMediaLib;
    private String mArtworkPath = "";
    private String mTitle = "";
    private String mArtist = "";
    private boolean mPlaybackStatus = false;
    private boolean mPlaybackStatusBak = false;
    private long mPlaybackDuration = -1;
    private long mPlaybackElapsedTime = -1;
    private boolean isDataViald = false;
    private boolean isDataGet = false;
    private boolean isDataGetBak = false;
    private boolean isArcWorkGet = false;
    private boolean isArcWorkGetBak = false;
    private final List<String> mPlayList = new ArrayList();

    public CarPlayMediaDataManager(CarPlayManager manager) {
        this.mMediaLib = null;
        LogUtil.info(TAG, "CarPlayMediaDataManager Construct method!");
        this.mMediaLib = new MediaLibraryUpdate();
        this.mManager = manager;
        this.mCarPlayMusicStatus = 1;
    }

    public void changeMediaStatus(int playStatus) {
    }

    public void changeMediaLibraryUpdate(MediaLibraryUpdate medialib) {
        LogUtil.info(TAG, "changeMediaLibraryUpdate : " + medialib);
        if (medialib != null) {
            this.mMediaLib = medialib;
        }
    }

    public void changePlayList(String plPid, String[] pids) {
        LogUtil.info(TAG, "changePlayList : " + plPid);
    }

    public boolean getPlaybackStatus() {
        LogUtil.info(TAG, "getPlaybackStatus" + this.mPlaybackStatus);
        return this.mPlaybackStatus;
    }

    public String getTitle() {
        LogUtil.info(TAG, "getTitle" + this.mTitle);
        return this.mTitle;
    }

    public String getMusicImagePath() {
        LogUtil.info(TAG, "getMusicImagePath, mArtworkPath" + this.mArtworkPath);
        return this.mArtworkPath;
    }

    public long getPlaybackDurationMs() {
        LogUtil.info(TAG, "getplaybackDuration" + this.mPlaybackDuration);
        return this.mPlaybackDuration;
    }

    public long getPlaybackElapsedTimeMs() {
        LogUtil.info(TAG, "getPlaybackElapsedTimeMs" + this.mPlaybackElapsedTime);
        return this.mPlaybackElapsedTime;
    }

    public void changePlaybackList(boolean isAvailable, String[] pids) {
        LogUtil.info(TAG, "changePlaybackListUpdate, isAvailable=" + isAvailable + ", pids=" + pids);
    }

    public List<String> getPlaybackList() {
        LogUtil.info(TAG, "getPlaybackList");
        return this.mPlayList;
    }

    public void changeArtwork(String artworkFilePath) {
        LogUtil.info(TAG, "changeArtwork, artworkFilePath = " + artworkFilePath);
        this.mArtworkPath = artworkFilePath;
    }

    public String getArtworkPath() {
        LogUtil.info(TAG, "getArtworkPath, mArtworkPath = " + this.mArtworkPath);
        return this.mArtworkPath;
    }

    public String getSingerName() {
        LogUtil.info(TAG, "getSingerName" + this.mArtist);
        return this.mArtist;
    }

    public void changeMediaProgress(int progress) {
        LogUtil.info(TAG, "changeMediaProgress progress : " + progress);
    }

    public boolean isCarPlayMusicPlaying() {
        LogUtil.info(TAG, "isCarPlayMusicPlaying : " + this.mCarPlayMusicStatus);
        if (this.mCarPlayMusicStatus == 2) {
            return true;
        }
        return false;
    }

    public void clearMusicInfo() {
        LogUtil.info(TAG, "clearMusicInfo");
        this.mTitle = "";
        this.mArtist = "";
        this.mArtworkPath = "";
        this.mPlaybackStatus = false;
        this.mPlaybackStatusBak = false;
        this.mPlaybackDuration = -1L;
        this.mPlaybackElapsedTime = -1L;
        this.mPlaybackDuration = -1L;
        this.isDataViald = false;
        this.isDataGet = false;
        this.isDataGetBak = false;
    }
}
