package com.byd.music.fragment.localMusic.MusicPlayer;

import android.text.TextUtils;
import android.widget.Toast;
import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableMap;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.MultiMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.AudioFocusManager;
import com.byd.music.constant.SPConstants;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.pinyin.PinyinComparator;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.byd.music.utils.ToastUtil;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public class MusicPlayHelper implements IPlayerListener {
    private static volatile MusicPlayHelper mMusicPlayHelperInstance;
    private int duration;
    IPlayerStateSyncListener mIPlayerStateSyncListener;
    MusicPlayer musicPlayer;
    private int playState;
    private String playUrl;
    private int progress;
    private long startTime;
    private String TAG = MusicPlayHelper.class.getSimpleName();
    public boolean isNeedPlay = false;
    public ObservableInt playMode = new ObservableInt(SPUtils.getInstance().getInt(SPConstants.SP_PLAY_MODE_LOCAL_MUSIC, 0));
    public ObservableMap<Integer, Integer> playModeMaps = new ObservableArrayMap();
    public ObservableBoolean isPlaying = new ObservableBoolean(false);
    public ObservableMap<Integer, Boolean> isPlayingMaps = new ObservableArrayMap();
    boolean isCollectList = false;
    private Random random = new Random();
    private int seekProgress = 0;
    private boolean isMemoryPlay = false;
    public ObservableField<LocalMusicBean> currentPlayMusicState = new ObservableField<>();
    public ObservableMap<Integer, LocalMusicBean> currentPlayMusicStateMaps = new ObservableArrayMap();
    public final ObservableField<LocalMusicBean> currentLocalMusic = new ObservableField<>();
    public ObservableMap<Integer, LocalMusicBean> currentLocalMusicMaps = new ObservableArrayMap();
    public ObservableBoolean isCollectMusic = new ObservableBoolean(false);
    public ObservableMap<Integer, Boolean> isCollectMusicMaps = new ObservableArrayMap();
    public volatile ObservableBoolean isUsbPlug = new ObservableBoolean();
    public volatile ObservableBoolean isSDPlug = new ObservableBoolean();
    private List<LocalMusicBean> localMusicBeanList = new ArrayList();
    public ObservableBoolean clickCollectFromPlayer = new ObservableBoolean();
    public ObservableBoolean clickCollectFromPlayerUSB = new ObservableBoolean();
    public ObservableBoolean clickCollectFromPlayerSD = new ObservableBoolean();
    public ObservableBoolean clickCollectFromPlayerLocal = new ObservableBoolean();
    public List<LocalMusicBean> deleteMusicList = new ArrayList();
    public volatile boolean isNeedUpdateData = false;
    public volatile boolean isFormVoice = false;
    public volatile boolean isAccOff = false;
    private boolean hasUsbPlug = SPUtils.getInstance().getBoolean(SPConstants.KEY_USB_PLUG, false);
    private boolean hasSdPlug = SPUtils.getInstance().getBoolean(SPConstants.KEY_SD_PLUG, false);
    PinyinComparator pinyinComparator = new PinyinComparator();
    private final Lock mUpdateDataLock = new ReentrantLock();

    @Override // com.byd.music.fragment.localMusic.MusicPlayer.IPlayerListener
    public void reSet() {
    }

    private MusicPlayHelper() {
    }

    public static MusicPlayHelper getInstance() {
        if (mMusicPlayHelperInstance == null) {
            synchronized (MusicPlayHelper.class) {
                if (mMusicPlayHelperInstance == null) {
                    mMusicPlayHelperInstance = new MusicPlayHelper();
                }
            }
        }
        return mMusicPlayHelperInstance;
    }

    public ObservableInt getPlayMode() {
        return this.playMode;
    }

    public String setPlayMode(int i) {
        this.playMode.set(i);
        SPUtils.getInstance().put(SPConstants.SP_PLAY_MODE_LOCAL_MUSIC, i);
        return "";
    }

    public ObservableMap<Integer, Integer> getPlayModeMaps() {
        return this.playModeMaps;
    }

    public int getPlayModeOfMusicEngine() {
        StringBuilder sb = new StringBuilder("getPlayModeOfMusicEngine");
        sb.append(" musicEngine= ");
        sb.append(OnLineMusic.musicEngine);
        int i = OnLineMusic.musicEngine;
        int i2 = i != 3 ? i != 4 ? i != 5 ? -1 : 0 : 2 : 1;
        int iIntValue = this.playModeMaps.getOrDefault(Integer.valueOf(i2), -1).intValue();
        if (iIntValue == -1) {
            iIntValue = getPlayModeByStorageTypeFromSp(i2);
        }
        sb.append(" pMode=");
        sb.append(iIntValue);
        LogUtil.d(this.TAG, sb.toString());
        return iIntValue;
    }

    public String setPlayMode(int i, int i2) {
        StringBuilder sb = new StringBuilder("setPlayMode");
        sb.append(" storageType=");
        sb.append(i);
        sb.append(" playMode=");
        sb.append(i2);
        String str = i != 0 ? i != 1 ? i != 2 ? "" : SPConstants.SP_PLAY_MODE_USB : SPConstants.SP_PLAY_MODE_SD : SPConstants.SP_PLAY_MODE_LOCAL;
        if (!TextUtils.isEmpty(str)) {
            sb.append(" update!");
            this.playModeMaps.put(Integer.valueOf(i), Integer.valueOf(i2));
            SPUtils.getInstance().put(str, i2);
        }
        LogUtil.e(this.TAG, sb.toString());
        return "";
    }

    public int getPlayModeByStorageTypeFromSp(int i) {
        StringBuilder sb = new StringBuilder("setPlayMode");
        sb.append(" storageType=");
        sb.append(i);
        String str = i != 0 ? i != 1 ? i != 2 ? "" : SPConstants.SP_PLAY_MODE_USB : SPConstants.SP_PLAY_MODE_SD : SPConstants.SP_PLAY_MODE_LOCAL;
        int i2 = SPUtils.getInstance().getInt(str, 0);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(i2);
        return i2;
    }

    @Override // com.byd.music.fragment.localMusic.MusicPlayer.IPlayerListener
    public void onProgress(int i) {
        this.progress = i;
        int i2 = this.duration;
        if (i > i2) {
            this.progress = i2;
        }
        setCurrentPlayMusicState(new LocalMusicBean(this.progress, this.duration, this.playState, getCurrentPlayMusicStorageType()));
        LogUtil.d(this.TAG, "isPlaying = " + this.isPlaying.get() + " onProgress: " + i + " duration: " + this.duration);
        if (i > 0 && getPlayErrorTimes() > 0) {
            this.musicPlayer.setPlayErrorTime(0);
            this.musicPlayer.setPlayPreClick(false);
            this.musicPlayer.setPlayNextClick(false);
        }
        if (this.isPlaying.get()) {
            CurrentMediaInfoUtils.getInstance().setPlayingState(true);
        }
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            long j = i;
            CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(j, j, this.duration);
        }
    }

    @Override // com.byd.music.fragment.localMusic.MusicPlayer.IPlayerListener
    public void onDuration(int i) {
        this.duration = i;
        setCurrentPlayMusicState(new LocalMusicBean(this.progress, i, this.playState, getCurrentPlayMusicStorageType()));
    }

    @Override // com.byd.music.fragment.localMusic.MusicPlayer.IPlayerListener
    public void onChangeState(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("onChangeState state:");
        sb.append(i);
        sb.append(" playMode:");
        sb.append(getPlayModeOfMusicEngine());
        if (getCurrentPlayLocalMusic() == null) {
            stopUpdateProgress();
            this.musicPlayer.stop();
            return;
        }
        if (4 == i) {
            if (!LocalMusicUtils.checkMusicFileExist(getCurrentPlayLocalMusic().getUri())) {
                stopPlayLocalMusic();
                sb.append(" playTitle is not exist:");
                sb.append(getCurrentPlayLocalMusic().getUri());
                LogUtil.d(this.TAG, sb.toString());
                return;
            }
            if (getPlayErrorTimes() > 5) {
                pauseLocalMusic();
                sb.append("getPlayErrorTimes() >5");
                LogUtil.d(this.TAG, sb.toString());
                return;
            }
            if (OnLineMusic.musicEngine != 5 && OnLineMusic.musicEngine != 4 && OnLineMusic.musicEngine != 3) {
                this.musicPlayer.pause();
                sb.append(" musicEngine=");
                sb.append(OnLineMusic.musicEngine);
                LogUtil.d(this.TAG, sb.toString());
                return;
            }
            LogUtil.d(this.TAG, sb.toString());
            if (getPlayModeOfMusicEngine() == 0) {
                playNext();
            } else if (2 == getPlayModeOfMusicEngine()) {
                play(getCurrentPlayLocalMusic(), 0);
            } else if (1 == getPlayModeOfMusicEngine()) {
                List<LocalMusicBean> list = this.localMusicBeanList;
                play(list.get(getRandomInt(list.size(), this.localMusicBeanList.indexOf(getCurrentLocalMusic()))), 0);
            }
        }
        this.playState = i;
        IPlayerStateSyncListener iPlayerStateSyncListener = this.mIPlayerStateSyncListener;
        if (iPlayerStateSyncListener != null) {
            if (i == 1) {
                iPlayerStateSyncListener.onPlay();
            } else {
                if (i != 2) {
                    return;
                }
                iPlayerStateSyncListener.onStop();
            }
        }
    }

    @Override // com.byd.music.fragment.localMusic.MusicPlayer.IPlayerListener
    public void playError(int i) {
        if (i >= 5) {
            pauseMusic();
        }
    }

    @Override // com.byd.music.fragment.localMusic.MusicPlayer.IPlayerListener
    public void playPreNextError(boolean z) {
        LogUtil.d(this.TAG, "playPreNextError:" + z);
        if (z) {
            playPre();
        } else {
            playNext();
        }
    }

    public void setIPlayerStateSyncListener(IPlayerStateSyncListener iPlayerStateSyncListener) {
        this.mIPlayerStateSyncListener = iPlayerStateSyncListener;
    }

    public void playListByItem(List<LocalMusicBean> list, LocalMusicBean localMusicBean) {
        LogUtil.d(this.TAG, "playListByItem:musicTitle=" + StringSubUtil.subString(localMusicBean.getTitle()));
        if (initPlayMusicList(list)) {
            play(localMusicBean, 0);
        }
    }

    public void playListItemByManual(List<LocalMusicBean> list, LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder("playListByItem manual click:");
        sb.append(" musicTitle=");
        sb.append(StringSubUtil.subString(localMusicBean.getTitle()));
        setPlayErrorTimes(0);
        if (initPlayMusicList(list)) {
            LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
            if (currentPlayLocalMusic != null && currentPlayLocalMusic.equals(localMusicBean)) {
                sb.append(" isPlaying= ");
                sb.append(getIsPlaying().get());
                if (!getIsPlaying().get()) {
                    sb.append(" and play");
                    playOrPause();
                }
            } else {
                sb.append(" and play");
                play(localMusicBean, 0);
            }
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public void stop() {
        MusicPlayer musicPlayer = this.musicPlayer;
        if (musicPlayer == null) {
            return;
        }
        musicPlayer.stopBeforePlay();
    }

    public void play(LocalMusicBean localMusicBean, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("play(localMusicBean,progress):");
        if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
            int iRequestAudioFocus = AudioFocusManager.getInstance().requestAudioFocus();
            sb.append(" audioFocus：");
            sb.append(iRequestAudioFocus);
            if (1 != iRequestAudioFocus) {
                sb.append(" play_failed");
                LogUtil.e(this.TAG, sb.toString());
                if (!DeviceInfoUtils.isInCall()) {
                    ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                }
                updateLocalPlayStateUIOnAudioFocusFail();
                return;
            }
        }
        if (localMusicBean == null) {
            sb.append("No Music can play");
            LogUtil.e(this.TAG, sb.toString());
            if (this.musicPlayer != null) {
                setCurrentPlayMusicState(new LocalMusicBean(0, 0, 2, getCurrentPlayMusicStorageType()));
                setIsCollectMusic(convertMusicEngineToStorageType(OnLineMusic.musicEngine), false);
                this.musicPlayer.stop();
            }
            Toast.makeText(Utils.getApp().getApplicationContext(), "暂无歌曲", 0).show();
            return;
        }
        stop();
        initMusicPlayer();
        this.progress = i;
        this.playState = this.musicPlayer.getState();
        sb.append("music title=");
        sb.append(StringSubUtil.subString(localMusicBean.getTitle()));
        sb.append(" playState=");
        sb.append(this.playState);
        sb.append(" progress = ");
        sb.append(i);
        sb.append(" duration= ");
        sb.append(localMusicBean.getDuration());
        LogUtil.d(this.TAG, sb.toString());
        this.musicPlayer.play(localMusicBean, i);
    }

    public void playFromWidget() {
        play(getCurrentMusicFromSp(), 0);
    }

    public String playPre() {
        LogUtil.d(this.TAG, "start play music!");
        StringBuilder sb = new StringBuilder();
        sb.append("playPre:");
        if (!ensureCurrentMusicEngineData()) {
            sb.append(" ensure MusicEngineData return false");
            LogUtil.e(this.TAG, sb.toString());
            return MediaControl.REASON_UNKNOWN;
        }
        if (getCurrentPlayLocalMusic() == null) {
            sb.append("currentLocalMusic null");
            LogUtil.e(this.TAG, sb.toString());
            return MediaControl.REASON_UNKNOWN;
        }
        LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
        int iIndexOf = this.localMusicBeanList.indexOf(currentPlayLocalMusic);
        LogUtil.d(this.TAG, "localMusicBeanList of size is " + this.localMusicBeanList.size());
        LogUtil.d(this.TAG, "index of current music is " + iIndexOf);
        LogUtil.d(this.TAG, "playMode = " + getPlayModeOfMusicEngine());
        sb.append(" playMode:");
        sb.append(getPlayModeOfMusicEngine());
        sb.append(" before index=");
        sb.append(iIndexOf);
        LogUtil.d(this.TAG, sb.toString());
        if (iIndexOf == -1) {
            int size = this.localMusicBeanList.size();
            sb.append(" musicList no play music size:");
            sb.append(size);
            sb.append("current music name=");
            sb.append(StringSubUtil.subString(getCurrentPlayLocalMusic().getTitle()));
            sb.append(" storageType=");
            sb.append(getCurrentPlayLocalMusic().getStorageType());
            if (size == 0) {
                if (currentPlayLocalMusic != null && LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic.getUri()) && reloadPlayList(currentPlayLocalMusic.getStorageType()) > 0) {
                    iIndexOf = this.localMusicBeanList.indexOf(currentPlayLocalMusic);
                    if (iIndexOf == 0) {
                        List<LocalMusicBean> list = this.localMusicBeanList;
                        currentPlayLocalMusic = list.get(list.size() - 1);
                    } else {
                        currentPlayLocalMusic = this.localMusicBeanList.get(iIndexOf - 1);
                    }
                } else {
                    playFailByListEmpty();
                }
                LogUtil.d(this.TAG, sb.toString());
            } else {
                LogUtil.d(this.TAG, sb.toString());
                play(this.localMusicBeanList.get(0), 0);
                LogUtil.d(this.TAG, "playPre: Not in list, and play first music!");
                return "";
            }
        } else if (iIndexOf == 0) {
            int size2 = this.localMusicBeanList.size();
            LogUtil.d(this.TAG, "music list size = " + size2);
            int i = size2 - 1;
            LocalMusicBean localMusicBean = this.localMusicBeanList.get(i);
            if (1 != getPlayModeOfMusicEngine()) {
                iIndexOf = i;
            }
            currentPlayLocalMusic = localMusicBean;
        } else {
            int i2 = iIndexOf - 1;
            currentPlayLocalMusic = this.localMusicBeanList.get(i2);
            if (1 != getPlayModeOfMusicEngine()) {
                iIndexOf = i2;
            }
        }
        if (1 == getPlayModeOfMusicEngine()) {
            iIndexOf = getRandomInt(this.localMusicBeanList.size(), iIndexOf);
            currentPlayLocalMusic = this.localMusicBeanList.get(iIndexOf);
        }
        if (currentPlayLocalMusic == null) {
            return MediaControl.REASON_UNKNOWN;
        }
        sb.append(" after index=");
        sb.append(iIndexOf);
        sb.append(" playMode");
        sb.append(getPlayModeOfMusicEngine());
        sb.append(" musicTitle=");
        sb.append(StringSubUtil.subString(currentPlayLocalMusic.getTitle()));
        LogUtil.d(this.TAG, sb.toString());
        MusicPlayer musicPlayer = this.musicPlayer;
        if (musicPlayer != null) {
            musicPlayer.setPlayPreClick(true);
            this.musicPlayer.setPlayNextClick(false);
        }
        if (LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic.getUri())) {
            play(currentPlayLocalMusic, 0);
        } else {
            setCurrentMusic(currentPlayLocalMusic);
            checkCurrentPlayMusic(true);
        }
        return "";
    }

    public String playNext() {
        LogUtil.d(this.TAG, "start play next music!");
        StringBuilder sb = new StringBuilder();
        sb.append("playNext:");
        if (!ensureCurrentMusicEngineData()) {
            sb.append(" ensure MusicEngineData return false");
            LogUtil.e(this.TAG, sb.toString());
            return MediaControl.REASON_UNKNOWN;
        }
        if (getCurrentPlayLocalMusic() == null) {
            sb.append("currentLocalMusic null");
            LogUtil.e(this.TAG, sb.toString());
            return MediaControl.REASON_UNKNOWN;
        }
        if (getPlayErrorTimes() > 5) {
            sb.append(" error time=" + getPlayErrorTimes());
            LogUtil.e(this.TAG, sb.toString());
            stopPlayLocalMusic();
            return "";
        }
        LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
        int iIndexOf = this.localMusicBeanList.indexOf(currentPlayLocalMusic);
        LogUtil.d(this.TAG, "localMusicBeanList of size is " + this.localMusicBeanList.size());
        LogUtil.d(this.TAG, "index of current music is " + iIndexOf);
        LogUtil.d(this.TAG, "play mode = " + getPlayModeOfMusicEngine());
        sb.append(" playMode:");
        sb.append(getPlayModeOfMusicEngine());
        sb.append(" before index=");
        sb.append(iIndexOf);
        if (-1 == iIndexOf) {
            int size = this.localMusicBeanList.size();
            sb.append(" musicList no play music size:");
            sb.append(size);
            sb.append("current music name=");
            sb.append(StringSubUtil.subString(getCurrentPlayLocalMusic().getTitle()));
            sb.append(" storageType=");
            sb.append(getCurrentPlayLocalMusic().getStorageType());
            if (size == 0) {
                if (currentPlayLocalMusic != null && LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic.getUri()) && reloadPlayList(currentPlayLocalMusic.getStorageType()) > 0) {
                    iIndexOf = this.localMusicBeanList.indexOf(currentPlayLocalMusic);
                    currentPlayLocalMusic = this.localMusicBeanList.size() - 1 == iIndexOf ? this.localMusicBeanList.get(0) : this.localMusicBeanList.get(iIndexOf + 1);
                } else {
                    playFailByListEmpty();
                }
                LogUtil.d(this.TAG, sb.toString());
            } else {
                LogUtil.d(this.TAG, sb.toString());
                play(this.localMusicBeanList.get(0), 0);
                LogUtil.d(this.TAG, "playNext: Not in list, and play first music!");
                return "";
            }
        } else if (this.localMusicBeanList.size() - 1 == iIndexOf) {
            LogUtil.d(this.TAG, "Get first music!");
            currentPlayLocalMusic = this.localMusicBeanList.get(0);
            if (1 != getPlayModeOfMusicEngine()) {
                iIndexOf = 0;
            }
        } else {
            LogUtil.d(this.TAG, "Get next music!");
            int i = iIndexOf + 1;
            currentPlayLocalMusic = this.localMusicBeanList.get(i);
            if (1 != getPlayModeOfMusicEngine()) {
                iIndexOf = i;
            }
        }
        if (1 == getPlayModeOfMusicEngine()) {
            iIndexOf = getRandomInt(this.localMusicBeanList.size(), iIndexOf);
            currentPlayLocalMusic = this.localMusicBeanList.get(iIndexOf);
        }
        MusicPlayer musicPlayer = this.musicPlayer;
        if (musicPlayer != null) {
            musicPlayer.setPlayPreClick(false);
            this.musicPlayer.setPlayNextClick(true);
        }
        if (currentPlayLocalMusic == null) {
            return MediaControl.REASON_UNKNOWN;
        }
        sb.append(" after index=");
        sb.append(iIndexOf);
        sb.append(" playMode=");
        sb.append(getPlayModeOfMusicEngine());
        sb.append(" musicTitle=");
        sb.append(StringSubUtil.subString(currentPlayLocalMusic.getTitle()));
        LogUtil.d(this.TAG, sb.toString());
        if (LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic.getUri())) {
            play(currentPlayLocalMusic, 0);
        } else {
            setCurrentMusic(currentPlayLocalMusic);
            checkCurrentPlayMusic(false);
        }
        return "";
    }

    private void playFailByListEmpty() {
        stopPlayLocalMusic();
        Toast.makeText(Utils.getApp().getApplicationContext(), Utils.getApp().getString(R.string.play_list_empty), 0).show();
    }

    public void seekToProgress(int i, int i2) {
        StringBuilder sb = new StringBuilder("seekToProgress");
        sb.append(" OnLineMusic.musicEngine=");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" storageType=");
        sb.append(i);
        sb.append(" progress=");
        sb.append(i2);
        if (convertStorageTypeToEngine(i) != OnLineMusic.musicEngine) {
            LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic(i);
            if (currentPlayLocalMusic != null) {
                this.currentPlayMusicStateMaps.put(Integer.valueOf(i), new LocalMusicBean(i2, currentPlayLocalMusic.getDuration(), currentPlayLocalMusic.getState(), i));
            } else {
                this.currentPlayMusicStateMaps.put(Integer.valueOf(i), new LocalMusicBean(0, 0, 2, i));
            }
            saveMusicProgressByStorage(i);
            sb.append(" seek to other engine progress!");
            LogUtil.d(this.TAG, sb.toString());
            return;
        }
        setMusicEngineByStorageType(i);
        boolean zEquals = Boolean.TRUE.equals(this.isPlayingMaps.get(Integer.valueOf(getCurrentPlayMusicStorageType())));
        sb.append(" beforePlayState=");
        sb.append(zEquals);
        if (zEquals) {
            pauseMusic();
        }
        initMusicPlayer();
        sb.append(" progress=");
        sb.append(i2);
        LocalMusicBean currentPlayLocalMusic2 = getCurrentPlayLocalMusic(i);
        if (currentPlayLocalMusic2 != null) {
            setCurrentPlayMusicState(new LocalMusicBean(i2, currentPlayLocalMusic2.getDuration(), currentPlayLocalMusic2.getState(), currentPlayLocalMusic2.getStorageType()));
            if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
                sb.append(" update CMIU progress=");
                sb.append(i2);
                long j = i2;
                CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(j, j, currentPlayLocalMusic2.getDuration());
                saveCurLocalMusicEngineProgress();
            }
        }
        sb.append(" afterPlayState=");
        sb.append(this.isPlaying.get());
        LogUtil.d(this.TAG, sb.toString());
        if (zEquals) {
            play(currentPlayLocalMusic2, i2);
            return;
        }
        if (this.duration == 0 && currentPlayLocalMusic2 != null) {
            this.duration = currentPlayLocalMusic2.getDuration();
        }
        this.seekProgress = i2;
        this.musicPlayer.seekPlay(i2);
    }

    public void stopUpdateProgress() {
        MusicPlayer musicPlayer = this.musicPlayer;
        if (musicPlayer != null) {
            musicPlayer.stopUpdate();
        }
    }

    public void setLocalMusicBeanList(List<LocalMusicBean> list) {
        LogUtil.d(this.TAG, "setLocalMusicBeanList1");
        if (initPlayMusicList(list) && getCurrentMusicFromSp() == null) {
            setCurrentMusic(this.localMusicBeanList.get(0));
            resetPlayState(0);
            if (getCurrentPlayLocalMusic() != null) {
                setCurrentPlayMusicState(new LocalMusicBean(0, getCurrentPlayLocalMusic().getDuration(), 4, getCurrentPlayLocalMusic().getStorageType()));
            }
        }
    }

    public void setLocalMusicBeanList(List<LocalMusicBean> list, LocalMusicBean localMusicBean) {
        LogUtil.d(this.TAG, "setLocalMusicBeanList2 ");
        if (initPlayMusicList(list)) {
            if (localMusicBean == null || LocalMusicUtils.checkMusicFileLength(localMusicBean.getUri())) {
                setCurrentMusic(localMusicBean);
            }
        }
    }

    public boolean initPlayMusicList(List<LocalMusicBean> list) {
        StringBuilder sb = new StringBuilder("initPlayMusicList ");
        sb.append(" localMusicBeanList before size  = ");
        sb.append(this.localMusicBeanList.size());
        if (list.size() > 0) {
            this.localMusicBeanList.clear();
            this.localMusicBeanList.addAll(list);
            sb.append(" clear and after size  = ");
            sb.append(this.localMusicBeanList.size());
            LogUtil.d(this.TAG, sb.toString());
            return true;
        }
        LogUtil.d(this.TAG, sb.toString());
        return false;
    }

    public synchronized void initCollectPlayMusicListAndPlay(LocalMusicBean localMusicBean, boolean z, List<LocalMusicBean> list) {
        if (this.isCollectList) {
            StringBuilder sb = new StringBuilder();
            sb.append(" initCollectPlayMusicListAndPlay11");
            sb.append(" removeItem = ");
            sb.append(z);
            sb.append(" localMusicBeanList size = ");
            sb.append(this.localMusicBeanList.size());
            if (z) {
                int iIndexOf = this.localMusicBeanList.indexOf(this.currentLocalMusic.get());
                this.localMusicBeanList.remove(localMusicBean);
                int size = this.localMusicBeanList.size();
                sb.append(" index = ");
                sb.append(iIndexOf);
                sb.append(" removeItem and size = ");
                sb.append(size);
                sb.append(" currentLocalMusic.getTitle() = ");
                sb.append(StringSubUtil.subString(this.currentLocalMusic.get().getTitle()));
                sb.append(" playState = ");
                sb.append(this.isPlaying.get());
                if (size <= 0) {
                    LocalMusicBean localMusicBean2 = list.get(0);
                    if (this.isPlaying.get()) {
                        sb.append(" addAll list");
                        this.localMusicBeanList.addAll(list);
                    } else {
                        setCurrentMusic(localMusicBean2);
                    }
                    setCollectList(false);
                }
            } else if (!this.localMusicBeanList.contains(localMusicBean)) {
                this.localMusicBeanList.add(localMusicBean);
                Collections.sort(this.localMusicBeanList, this.pinyinComparator);
                sb.append("add localMusic title = ");
                sb.append(StringSubUtil.subString(localMusicBean.getTitle()));
            }
            sb.append(" localMusicBeanList after size = ");
            sb.append(this.localMusicBeanList.size());
            LogUtil.d(this.TAG, sb.toString());
        }
    }

    public void initPlayMusicListByItem(LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder("initPlayMusicListByItem ");
        int size = this.localMusicBeanList.size();
        sb.append(" localMusicBeanList before size = ");
        sb.append(size);
        LocalMusicBean localMusicBean2 = this.currentLocalMusic.get();
        sb.append(" currentLocalMusic = ");
        sb.append(localMusicBean2 != null ? StringSubUtil.subString(localMusicBean2.getTitle()) : "null");
        sb.append(" addItemTitle = ");
        sb.append(localMusicBean != null ? StringSubUtil.subString(localMusicBean.getTitle()) : "null");
        if (size > 0 && localMusicBean != null) {
            if (this.currentLocalMusic.get() == null || this.currentLocalMusic.get().getStorageType() != localMusicBean.getStorageType() || !this.localMusicBeanList.contains(localMusicBean)) {
                this.localMusicBeanList.clear();
                this.localMusicBeanList.add(localMusicBean);
                sb.append(" clear and add after size = ");
                sb.append(this.localMusicBeanList.size());
            }
        } else {
            this.localMusicBeanList.add(localMusicBean);
            sb.append(" add after size =  ");
            sb.append(this.localMusicBeanList.size());
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public void init(List<LocalMusicBean> list) {
        LogUtil.d(this.TAG, " init setLocalMusicBeanList");
        Collections.sort(list, new PinyinComparator());
        setLocalMusicBeanList(list);
    }

    public void setCurrentPlayMusicState(LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder(" setCurrentPlayMusicState");
        if (getCurEnginePlayLocalMusic() == null && localMusicBean.getDuration() > 0) {
            sb.append(" progress= 0");
            this.currentPlayMusicStateMaps.put(Integer.valueOf(localMusicBean.getStorageType()), localMusicBean);
            if (isMatchMusicEngineByStorageType(localMusicBean.getStorageType())) {
                sb.append(" matchMusicEngine and update!");
                this.currentPlayMusicState.set(new LocalMusicBean(0, 0, 4, localMusicBean.getStorageType()));
            }
            LogUtil.d(this.TAG, sb.toString());
            return;
        }
        if (localMusicBean != null) {
            sb.append(" progress=");
            sb.append(localMusicBean.getProgress());
            sb.append(" duration=");
            sb.append(localMusicBean.getDuration());
            sb.append(" isPlaying=");
            sb.append(this.isPlayingMaps.get(Integer.valueOf(localMusicBean.getStorageType())));
            sb.append(" storageType=");
            sb.append(localMusicBean.getStorageType());
            this.currentPlayMusicStateMaps.put(Integer.valueOf(localMusicBean.getStorageType()), localMusicBean);
            if (isMatchMusicEngineByStorageType(localMusicBean.getStorageType())) {
                sb.append(" matchMusicEngine and update!");
                this.currentPlayMusicState.set(localMusicBean);
                if (!checkLocalIsEqualsMusicInfoWithCMIU(localMusicBean.getStorageType())) {
                    updateCurrentMusicInfo();
                }
            }
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public ObservableField<LocalMusicBean> getCurrentPlayMusicState() {
        return this.currentPlayMusicState;
    }

    public ObservableMap<Integer, LocalMusicBean> getCurrentPlayMusicStateMaps() {
        return this.currentPlayMusicStateMaps;
    }

    private int getCurrentPlayMusicStorageType() {
        return convertMusicEngineToStorageType(OnLineMusic.musicEngine);
    }

    public void pauseLocalMusic() {
        StringBuilder sb = new StringBuilder();
        setIsPlaying(false);
        initMusicPlayer();
        sb.append("pauseLocalMusic");
        sb.append(" isPlaying=");
        sb.append(getIsPlaying().get());
        sb.append(" state=");
        sb.append(this.musicPlayer.getState());
        LogUtil.e(this.TAG, sb.toString());
        stopUpdateProgress();
        if (this.musicPlayer.getState() != 2) {
            this.musicPlayer.pause();
            this.musicPlayer.setPlayErrorTime(0);
        }
    }

    public void stopPlayLocalMusic() {
        StringBuilder sb = new StringBuilder();
        setIsPlaying(false);
        initMusicPlayer();
        stopUpdateProgress();
        setCurrentPlayMusicState(new LocalMusicBean(0, 0, 4, getCurrentPlayMusicStorageType()));
        sb.append("stopPlayLocalMusic");
        sb.append(" isPlaying=");
        sb.append(getIsPlaying().get());
        sb.append(" stop before state=");
        MusicPlayer musicPlayer = this.musicPlayer;
        sb.append(musicPlayer == null ? "null" : Integer.valueOf(musicPlayer.getState()));
        LogUtil.e(this.TAG, sb.toString());
        MusicPlayer musicPlayer2 = this.musicPlayer;
        if (musicPlayer2 != null && musicPlayer2.getState() != 4) {
            this.musicPlayer.stop();
        }
        sb.append(" stop after state=");
        MusicPlayer musicPlayer3 = this.musicPlayer;
        sb.append(musicPlayer3 != null ? Integer.valueOf(musicPlayer3.getState()) : "null");
        LogUtil.e(this.TAG, sb.toString());
    }

    public String playOrPauseFromPlayer() {
        StringBuilder sb = new StringBuilder("playOrPauseFromPlayer");
        initMusicPlayer();
        sb.append(" isPlaying=");
        sb.append(getIsPlaying().get());
        sb.append(" state=");
        sb.append(this.musicPlayer.getState());
        if (getPlayErrorTimes() > 5) {
            sb.append(" errorTime=");
            sb.append(getPlayErrorTimes());
            LogUtil.e(this.TAG, sb.toString());
            return "";
        }
        int size = this.localMusicBeanList.size();
        sb.append(" size=");
        sb.append(size);
        if (size > 0) {
            int storageType = this.localMusicBeanList.get(0).getStorageType();
            sb.append(" storageType=");
            sb.append(storageType);
            if (this.musicPlayer.getState() == 0) {
                sb.append(" playMusic01");
                LocalMusicBean currentMusicFromSp = getCurrentMusicFromSp(storageType);
                sb.append(" localSpIsExist=");
                sb.append(currentMusicFromSp != null);
                if (currentMusicFromSp != null) {
                    int localProgressByStorageType = getLocalProgressByStorageType(storageType);
                    sb.append(" title=");
                    sb.append(StringSubUtil.subString(currentMusicFromSp.getTitle()));
                    sb.append(" progress=");
                    sb.append(this.progress);
                    sb.append(" seek_p=");
                    sb.append(this.seekProgress);
                    sb.append(" sp_progress=");
                    sb.append(localProgressByStorageType);
                    LocalMusicBean localMusicBean = this.currentPlayMusicStateMaps.get(Integer.valueOf(storageType));
                    int i = this.seekProgress;
                    if (i > 0) {
                        localProgressByStorageType = i;
                    } else if (localMusicBean != null) {
                        localProgressByStorageType = localMusicBean.getProgress();
                    }
                    sb.append(" setProgress=");
                    sb.append(localProgressByStorageType);
                    play(currentMusicFromSp, localProgressByStorageType);
                    this.seekProgress = 0;
                } else {
                    play(this.localMusicBeanList.get(0), 0);
                }
            } else if (this.musicPlayer.getState() != 2) {
                if (Boolean.TRUE.equals(this.isPlayingMaps.getOrDefault(Integer.valueOf(storageType), false))) {
                    sb.append(" pauseMusic");
                    this.musicPlayer.setState(2);
                    this.musicPlayer.pause();
                    LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic(storageType);
                    if (currentPlayLocalMusic != null) {
                        sb.append(" and saveProgress=");
                        sb.append(this.progress);
                        this.currentPlayMusicStateMaps.put(Integer.valueOf(storageType), new LocalMusicBean(this.progress, currentPlayLocalMusic.getDuration(), currentPlayLocalMusic.getState(), storageType));
                        saveCurLocalMusicEngineProgress();
                    }
                } else {
                    sb.append(" playMusic02");
                    if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
                        int iRequestAudioFocus = AudioFocusManager.getInstance().requestAudioFocus();
                        sb.append(" audioFocus=");
                        sb.append(iRequestAudioFocus);
                        if (1 != iRequestAudioFocus) {
                            if (!DeviceInfoUtils.isInCall()) {
                                ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                            }
                            sb.append(" play_failed");
                            LogUtil.e(this.TAG, sb.toString());
                            updateLocalPlayStateUIOnAudioFocusFail();
                            return "";
                        }
                    }
                    int localProgressByStorageType2 = getLocalProgressByStorageType(storageType);
                    sb.append(" progress=");
                    sb.append(this.progress);
                    sb.append(" seek_p=");
                    sb.append(this.seekProgress);
                    sb.append(" sp_progress=");
                    sb.append(localProgressByStorageType2);
                    LocalMusicBean localMusicBean2 = this.currentPlayMusicStateMaps.get(Integer.valueOf(storageType));
                    int i2 = this.seekProgress;
                    if (i2 > 0) {
                        localProgressByStorageType2 = i2;
                    } else if (localMusicBean2 != null) {
                        localProgressByStorageType2 = localMusicBean2.getProgress();
                    }
                    sb.append(" setProgress=");
                    sb.append(localProgressByStorageType2);
                    play(this.currentLocalMusicMaps.get(Integer.valueOf(storageType)), localProgressByStorageType2);
                    this.seekProgress = 0;
                    this.musicPlayer.setState(1);
                }
            } else {
                sb.append(" playMusic03");
                if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
                    int iRequestAudioFocus2 = AudioFocusManager.getInstance().requestAudioFocus();
                    sb.append(" audioFocus=");
                    sb.append(iRequestAudioFocus2);
                    if (1 != iRequestAudioFocus2) {
                        if (!DeviceInfoUtils.isInCall()) {
                            ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                        }
                        sb.append(" play_failed");
                        LogUtil.e(this.TAG, sb.toString());
                        updateLocalPlayStateUIOnAudioFocusFail();
                        return "";
                    }
                }
                int localProgressByStorageType3 = getLocalProgressByStorageType(storageType);
                sb.append(" progress=");
                sb.append(this.progress);
                sb.append(" seek_p=");
                sb.append(this.seekProgress);
                sb.append(" sp_progress=");
                sb.append(localProgressByStorageType3);
                LocalMusicBean localMusicBean3 = this.currentPlayMusicStateMaps.get(Integer.valueOf(storageType));
                int i3 = this.seekProgress;
                if (i3 > 0) {
                    localProgressByStorageType3 = i3;
                } else if (localMusicBean3 != null) {
                    localProgressByStorageType3 = localMusicBean3.getProgress();
                }
                sb.append(" setProgress=");
                sb.append(localProgressByStorageType3);
                play(this.currentLocalMusicMaps.get(Integer.valueOf(storageType)), localProgressByStorageType3);
                this.seekProgress = 0;
                this.musicPlayer.setState(1);
            }
        } else {
            LocalMusicBean currentPlayLocalMusic2 = getCurrentPlayLocalMusic();
            if (currentPlayLocalMusic2 != null && LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic2.getUri())) {
                if (this.musicPlayer.getState() == 1) {
                    sb.append(" pauseMusic");
                    this.musicPlayer.pause();
                } else if (this.musicPlayer.getState() == 2 || this.musicPlayer.getState() == 4) {
                    sb.append(" playMusic");
                    this.musicPlayer.startPlay(currentPlayLocalMusic2);
                }
                int iReloadPlayList = reloadPlayList(currentPlayLocalMusic2.getStorageType());
                sb.append(" reloadList=");
                sb.append(iReloadPlayList);
            } else {
                playFailByListEmpty();
            }
        }
        LogUtil.e(this.TAG, sb.toString());
        return MediaControl.REASON_UNKNOWN;
    }

    public String playOrPause() {
        StringBuilder sb = new StringBuilder();
        initMusicPlayer();
        sb.append("playOrPause");
        sb.append(" isPlaying=");
        sb.append(getIsPlaying().get());
        sb.append(" state=");
        sb.append(this.musicPlayer.getState());
        if (getPlayErrorTimes() > 5) {
            sb.append(" errorTime=");
            sb.append(getPlayErrorTimes());
            LogUtil.e(this.TAG, sb.toString());
            return "";
        }
        int size = this.localMusicBeanList.size();
        if (size > 0) {
            if (this.musicPlayer.getState() == 0) {
                sb.append(" playMusic");
                LocalMusicBean currentMusicFromSp = getCurrentMusicFromSp();
                if (currentMusicFromSp != null) {
                    int progress = this.seekProgress;
                    if (progress <= 0) {
                        progress = this.currentPlayMusicState.get() != null ? this.currentPlayMusicState.get().getProgress() : getLocalProgressByStorageType(currentMusicFromSp.getStorageType());
                    }
                    play(currentMusicFromSp, progress);
                    this.seekProgress = 0;
                } else {
                    play(this.localMusicBeanList.get(0), 0);
                }
            } else if (this.musicPlayer.getState() != 2) {
                sb.append(" pauseMusic");
                this.musicPlayer.setState(2);
                this.musicPlayer.pause();
            } else {
                sb.append(" playMusic");
                if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
                    int iRequestAudioFocus = AudioFocusManager.getInstance().requestAudioFocus();
                    sb.append(" audioFocus=");
                    sb.append(iRequestAudioFocus);
                    if (1 != iRequestAudioFocus) {
                        if (!DeviceInfoUtils.isInCall()) {
                            ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
                        }
                        sb.append(" play_failed");
                        LogUtil.e(this.TAG, sb.toString());
                        updateLocalPlayStateUIOnAudioFocusFail();
                        return "";
                    }
                }
                sb.append(" set progress=");
                sb.append(this.progress);
                if (this.progress == 0) {
                    play(this.currentLocalMusic.get(), 0);
                } else {
                    this.musicPlayer.startPlay(this.currentLocalMusic.get());
                }
                this.musicPlayer.setState(1);
            }
            sb.append(" size=");
            sb.append(size);
            LogUtil.e(this.TAG, sb.toString());
            return "";
        }
        if (LocalMusicUtils.checkMusicFileExist(this.currentLocalMusic.get().getUri())) {
            sb.append(" state=");
            sb.append(this.musicPlayer.getState());
            if (this.musicPlayer.getState() == 1) {
                sb.append(" pauseMusic");
                this.musicPlayer.pause();
            } else if (this.musicPlayer.getState() == 2 || this.musicPlayer.getState() == 4) {
                sb.append(" playMusic");
                this.musicPlayer.startPlay(this.currentLocalMusic.get());
            }
            int iReloadPlayList = reloadPlayList(this.currentLocalMusic.get().getStorageType());
            sb.append(" reloadList=");
            sb.append(iReloadPlayList);
        } else {
            playFailByListEmpty();
        }
        sb.append(" size=");
        sb.append(size);
        LogUtil.e(this.TAG, sb.toString());
        return MediaControl.REASON_UNKNOWN;
    }

    private int getPlayErrorTimes() {
        initMusicPlayer();
        return this.musicPlayer.getPlayErrorTime();
    }

    public void setPlayErrorTimes(int i) {
        MusicPlayer musicPlayer = this.musicPlayer;
        if (musicPlayer != null) {
            musicPlayer.setPlayErrorTime(i);
        }
    }

    public void localMusicPause() {
        LogUtil.d(this.TAG, "localMusicPause:");
        if (getIsPlaying().get()) {
            this.musicPlayer.removeMsg();
            this.musicPlayer.pause();
        }
    }

    public String playMusicFromApi() {
        if (DeviceInfoUtils.isInCall()) {
            LogUtil.d(this.TAG, "playMusicFromApi isInCall and return !");
            return Utils.getApp().getResources().getString(R.string.incall_forbid_operation);
        }
        initMusicPlayer();
        StringBuilder sb = new StringBuilder();
        sb.append("playMusicFromApi");
        if (!ensureCurrentMusicEngineData()) {
            sb.append(" ensure MusicEngineData return false");
            LogUtil.e(this.TAG, sb.toString());
            return MediaControl.REASON_UNKNOWN;
        }
        LocalMusicBean curEnginePlayLocalMusic = getCurEnginePlayLocalMusic();
        if (curEnginePlayLocalMusic == null) {
            LocalMusicBean curEngineMusicFromSp = getCurEngineMusicFromSp();
            if (CurrentMediaInfoUtils.getInstance().currentMusic.get() != null && curEngineMusicFromSp != null) {
                restoreCurrentMusic();
                LocalMusicBean curEnginePlayLocalMusic2 = getCurEnginePlayLocalMusic();
                if (curEnginePlayLocalMusic2 == null) {
                    sb.append(" currentLocalMusic NULL1");
                    LogUtil.e(this.TAG, sb.toString());
                    return MediaControl.REASON_NO_MATCH_FILE;
                }
                setLocalMusicBeanList(LocalMusicDB.getLocalMusicByStorageType(curEngineMusicFromSp.getStorageType()));
                curEnginePlayLocalMusic = curEnginePlayLocalMusic2;
            } else {
                sb.append(" currentLocalMusic NULL2");
                LogUtil.e(this.TAG, sb.toString());
                return MediaControl.REASON_NO_MATCH_FILE;
            }
        }
        sb.append(" state:");
        sb.append(this.musicPlayer.getState());
        int localProgressByStorageType = getLocalProgressByStorageType(curEnginePlayLocalMusic.getStorageType());
        sb.append(" spProgress:");
        sb.append(localProgressByStorageType);
        sb.append(" isFormVoice=");
        sb.append(this.isFormVoice);
        sb.append(" storageType=");
        sb.append(curEnginePlayLocalMusic.getStorageType());
        sb.append(" title=");
        sb.append(StringSubUtil.subString(curEnginePlayLocalMusic.getTitle()));
        if (!LocalMusicUtils.checkMusicFileExist(curEnginePlayLocalMusic.getUri())) {
            sb.append("current music not exist");
            LogUtil.e(this.TAG, sb.toString());
            updateLocalPlayStateWhenMusicIsNotExist();
            return MediaControl.REASON_NO_MATCH_FILE;
        }
        if (!AudioFocusManager.getInstance().isHoldAudioFocus() && 1 != AudioFocusManager.getInstance().requestAudioFocus()) {
            sb.append(" audioFocus failed");
            LogUtil.e(this.TAG, sb.toString());
            if (!DeviceInfoUtils.isInCall()) {
                ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
            }
            updateLocalPlayStateUIOnAudioFocusFail();
            return MediaControl.REASON_UNKNOWN;
        }
        if (checkCurrentPlayMusic(false)) {
            sb.append("checkCurrentPlayMusic =true");
        } else if (this.isFormVoice) {
            play(curEnginePlayLocalMusic, 0);
            this.isFormVoice = false;
        } else {
            if (this.musicPlayer.getState() == 0 || (this.currentPlayMusicState.get() != null && this.currentPlayMusicState.get().getDuration() == 0)) {
                sb.append(" play(playMusic, spProgress) = ");
                sb.append(localProgressByStorageType);
                int duration = curEnginePlayLocalMusic.getDuration();
                if (localProgressByStorageType < 0 || localProgressByStorageType > duration) {
                    sb.append(" spProgress is invalid and correct value = 0 ");
                    localProgressByStorageType = 0;
                }
                sb.append(" duration= ");
                sb.append(duration);
                play(curEnginePlayLocalMusic, localProgressByStorageType);
            } else {
                sb.append(" progress= ");
                sb.append(this.progress);
                LocalMusicBean localMusicBean = getCurrentPlayMusicStateMaps().get(Integer.valueOf(curEnginePlayLocalMusic.getStorageType()));
                sb.append(" musicStateIsExist=");
                sb.append(localMusicBean != null);
                sb.append(" statePb=");
                sb.append(localMusicBean != null ? localMusicBean.getProgress() : -1);
                if (localMusicBean != null) {
                    this.progress = localMusicBean.getProgress();
                }
                if (this.progress == 0) {
                    play(curEnginePlayLocalMusic, 0);
                } else {
                    if (localMusicBean != null) {
                        this.musicPlayer.play(curEnginePlayLocalMusic, localMusicBean.getProgress());
                    } else {
                        this.musicPlayer.startPlay(curEnginePlayLocalMusic);
                    }
                    checkUpdateForCMIULocalMusicInfo(curEnginePlayLocalMusic);
                }
            }
            if (this.musicPlayer.getState() == 1) {
                setIsPlaying(true);
            }
        }
        LogUtil.e(this.TAG, sb.toString());
        return "";
    }

    public void pauseMusic() {
        initMusicPlayer();
        LogUtil.e(this.TAG, "pauseMusic: musicPlayer state = " + this.musicPlayer.getState() + " mediaPlayer isPlaying = " + this.musicPlayer.isPlaying());
        this.musicPlayer.setState(2);
        this.musicPlayer.pause();
        saveCurLocalMusicEngineProgress();
    }

    public void setCurrentMusic(LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder();
        sb.append("setCurrentMusic current name =");
        sb.append(localMusicBean == null ? "null" : StringSubUtil.subString(localMusicBean.getTitle()));
        sb.append(" storageType=");
        sb.append(localMusicBean != null ? localMusicBean.getStorageString() : "null");
        sb.append(" OnLineMusic.musicEngine = ");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" isAccOff = ");
        sb.append(this.isAccOff);
        boolean z = this.currentLocalMusic.get() != null && this.currentLocalMusic.get().equals(localMusicBean);
        boolean z2 = this.currentLocalMusic.get() == null && localMusicBean == null;
        sb.append(" isSameBean = ");
        sb.append(z);
        sb.append(" beanIsNull = ");
        sb.append(z2);
        sb.append(" tName=");
        sb.append(Thread.currentThread().getName());
        if (z || z2) {
            if (z && !this.isAccOff) {
                checkUpdateForCMIULocalMusicInfo(localMusicBean);
            }
            if (localMusicBean == null && !this.isAccOff) {
                updateCurrentMediaInfo(localMusicBean);
            }
            LogUtil.e(this.TAG, sb.toString());
            if (z2) {
                return;
            }
            setCurrentLocalMusic(localMusicBean);
            return;
        }
        LogUtil.d(this.TAG, sb.toString());
        setCurrentLocalMusic(localMusicBean);
        if (localMusicBean != null) {
            LogUtil.d(this.TAG, "音乐不为空才写进sp里面:");
            saveCurrentMusic(localMusicBean);
            setIsCollectMusic(localMusicBean.getStorageType(), localMusicBean.isCollect());
        } else {
            setIsCollectMusic(convertMusicEngineToStorageType(OnLineMusic.musicEngine), false);
            stopPlayLocalMusic();
        }
        if (this.isAccOff) {
            return;
        }
        updateCurrentMediaInfo(localMusicBean);
    }

    private void updateCurrentMediaInfo(LocalMusicBean localMusicBean) {
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 3 || OnLineMusic.musicEngine == 4) {
            if (localMusicBean == null || LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                CurrentMediaInfoUtils.getInstance().currentMusic.set(new MultiMusicBean(localMusicBean));
            }
            if (localMusicBean == null || !LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                return;
            }
            int i = 21;
            int storageType = localMusicBean.getStorageType();
            if (storageType == 1) {
                i = 22;
            } else if (storageType == 2) {
                i = 23;
            }
            LogUtil.d(this.TAG, "setLocalMusicChildType:" + i);
            OnLineMusic.setLocalMusicChildType(i);
        }
    }

    private void checkUpdateForCMIULocalMusicInfo(LocalMusicBean localMusicBean) {
        if (OnLineMusic.musicEngine != 5 && OnLineMusic.musicEngine != 4 && OnLineMusic.musicEngine != 3) {
            LogUtil.d(this.TAG, "checkUpdateForCMIULocalMusicInfo finish with engine = " + OnLineMusic.musicEngine);
            return;
        }
        StringBuilder sb = new StringBuilder("checkUpdateForCMIULocalMusicInfo: ");
        MultiMusicBean multiMusicBean = CurrentMediaInfoUtils.getInstance().currentMusic.get();
        if (multiMusicBean == null) {
            sb.append(" the CMIU Local music is Null and update!");
            updateCurrentMediaInfo(localMusicBean);
        } else {
            Object entity = multiMusicBean.getEntity();
            if (entity instanceof LocalMusicBean) {
                boolean z = localMusicBean != null && localMusicBean.equals(entity);
                sb.append(" the CMIU Local music isSameBean= ");
                sb.append(z);
                if (!z) {
                    sb.append(" and update!");
                    updateCurrentMediaInfo(localMusicBean);
                }
            } else {
                sb.append(" remoteEntity is not localMusicBean Clazz, reset.");
                updateCurrentMediaInfo(localMusicBean);
            }
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public void checkUpdateLocalDataForCMIU() {
        StringBuilder sb = new StringBuilder("checkUpdateLocalDataForCMIU: ");
        LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
        if (currentPlayLocalMusic == null) {
            sb.append(" currentLocalMusic is NULL");
        }
        checkUpdateForCMIULocalMusicInfo(currentPlayLocalMusic);
        LogUtil.e(this.TAG, sb.toString());
    }

    public void updateCurrentMusicInfo() {
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        }
    }

    public boolean checkLocalIsEqualsMusicInfoWithCMIU(int i) {
        StringBuilder sb = new StringBuilder("isEqualsMusicInfoWithCMIU");
        sb.append(" musicEngine=");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" storageType=");
        sb.append(i);
        boolean z = false;
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            MultiMusicBean multiMusicBean = CurrentMediaInfoUtils.getInstance().currentMusic.get();
            if (multiMusicBean == null) {
                sb.append(" the CMIU remoteBean is Null!");
            } else {
                Object entity = multiMusicBean.getEntity();
                if (entity instanceof LocalMusicBean) {
                    LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic(i);
                    if (currentPlayLocalMusic != null && currentPlayLocalMusic.equals(entity)) {
                        z = true;
                    }
                    sb.append(" the CMIU isSameBean=");
                    sb.append(z);
                } else {
                    sb.append(" remoteEntity is not localMusicBean Clazz, reset.");
                }
            }
        }
        sb.append(" and return=");
        sb.append(z);
        LogUtil.d(this.TAG, sb.toString());
        return z;
    }

    public void restoreCurrentMusic() {
        LocalMusicBean curEngineMusicFromSp = getCurEngineMusicFromSp();
        StringBuilder sb = new StringBuilder("restoreCurrentMusic");
        sb.append(" musicEngine=");
        sb.append(OnLineMusic.musicEngine);
        boolean z = getIsPlaying().get();
        sb.append(" isPlaying=");
        sb.append(z);
        if (curEngineMusicFromSp != null && LocalMusicUtils.checkMusicFileExist(curEngineMusicFromSp.getUri())) {
            if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 3 || OnLineMusic.musicEngine == 4) {
                setMusicEngineByStorageType(curEngineMusicFromSp.getStorageType());
                sb.append(" restore musicEngine= ");
                sb.append(OnLineMusic.musicEngine);
            }
            setCurrentLocalMusic(curEngineMusicFromSp);
            setIsCollectMusic(curEngineMusicFromSp.getStorageType(), curEngineMusicFromSp.isCollect());
            sb.append(" spTitle=");
            sb.append(StringSubUtil.subString(curEngineMusicFromSp.getTitle()));
            LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
            sb.append(" curPlayMusicIsExist=");
            sb.append(currentPlayLocalMusic != null);
            if (currentPlayLocalMusic != null && currentPlayLocalMusic.equals(curEngineMusicFromSp) && z) {
                sb.append("  save sp progress");
                saveCurLocalMusicEngineProgress();
            }
            boolean zCheckLocalIsEqualsMusicInfoWithCMIU = checkLocalIsEqualsMusicInfoWithCMIU(curEngineMusicFromSp.getStorageType());
            sb.append(" isEqualsCMIU=");
            sb.append(zCheckLocalIsEqualsMusicInfoWithCMIU);
            if (!zCheckLocalIsEqualsMusicInfoWithCMIU || !z) {
                int duration = curEngineMusicFromSp.getDuration();
                int localProgressByStorageType = getLocalProgressByStorageType(curEngineMusicFromSp.getStorageType());
                sb.append(" before spProgress= ");
                sb.append(localProgressByStorageType);
                sb.append(" duration= ");
                sb.append(duration);
                if (localProgressByStorageType > duration) {
                    localProgressByStorageType = 0;
                }
                sb.append(" after spProgress=");
                sb.append(localProgressByStorageType);
                setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, duration, 0, curEngineMusicFromSp.getStorageType()));
                if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
                    sb.append(" and update CMIU progress");
                    long j = localProgressByStorageType;
                    CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(j, j, duration);
                }
            } else {
                sb.append(" curMusicSp is playing and return!");
            }
        } else {
            sb.append(" curMusicSp is invalid file");
        }
        LogUtil.d(this.TAG, sb.toString());
        if (z) {
            return;
        }
        updateCurrentMediaInfo(curEngineMusicFromSp);
    }

    public void saveCurrentMusic(LocalMusicBean localMusicBean) {
        String json = new Gson().toJson(localMusicBean);
        if (localMusicBean == null) {
            return;
        }
        StringBuilder sb = new StringBuilder("saveCurrentMusic to sp");
        sb.append(" musicUri=");
        sb.append(StringSubUtil.subString(localMusicBean.getUri()));
        sb.append(" curTitle=");
        sb.append(StringSubUtil.subString(localMusicBean.getTitle()));
        sb.append(" storageType=");
        sb.append(localMusicBean.getStorageType());
        sb.append(" isAccOff=");
        sb.append(this.isAccOff);
        if (this.isAccOff) {
            sb.append(" and return!");
            LogUtil.e(this.TAG, sb.toString());
            return;
        }
        LocalMusicBean currentMusicFromSp = getCurrentMusicFromSp();
        if (!localMusicBean.equals(currentMusicFromSp) || localMusicBean.isCollect() != currentMusicFromSp.isCollect()) {
            SPUtils.getInstance().put(SPConstants.CURRENT_MUSIC_SP_KEY, json);
            sb.append(" and update sp");
        }
        if (localMusicBean.getStorageType() == 0) {
            SPUtils.getInstance().put(SPConstants.SP_KEY_CURRENT_MUSIC_LOCAL, json);
        } else if (localMusicBean.getStorageType() == 2) {
            SPUtils.getInstance().put(SPConstants.SP_KEY_CURRENT_MUSIC_USB, json);
        } else if (localMusicBean.getStorageType() == 1) {
            SPUtils.getInstance().put(SPConstants.SP_KEY_CURRENT_MUSIC_SD, json);
        }
        LogUtil.e(this.TAG, sb.toString());
    }

    public void saveCurrentMusicFromCollectBtn(LocalMusicBean localMusicBean) {
        String json = new Gson().toJson(localMusicBean);
        if (localMusicBean != null) {
            if (OnLineMusic.musicEngine == convertStorageTypeToEngine(localMusicBean.getStorageType())) {
                SPUtils.getInstance().put(SPConstants.CURRENT_MUSIC_SP_KEY, json);
            }
            if (localMusicBean.getStorageType() == 0) {
                SPUtils.getInstance().put(SPConstants.SP_KEY_CURRENT_MUSIC_LOCAL, json);
            } else if (localMusicBean.getStorageType() == 2) {
                SPUtils.getInstance().put(SPConstants.SP_KEY_CURRENT_MUSIC_USB, json);
            } else if (localMusicBean.getStorageType() == 1) {
                SPUtils.getInstance().put(SPConstants.SP_KEY_CURRENT_MUSIC_SD, json);
            }
        }
        LogUtil.e(this.TAG, "saveCurrentMusicFromCollectBtn CURRENT_MUSIC_SP_KEY sp_progress = " + localMusicBean.getProgress());
    }

    public LocalMusicBean getCurrentMusicFromSp() {
        LocalMusicBean localMusicBean = (LocalMusicBean) new Gson().fromJson(SPUtils.getInstance().getString(SPConstants.CURRENT_MUSIC_SP_KEY, "null"), LocalMusicBean.class);
        if (localMusicBean != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("getCurrentMusicFromSp path:");
            sb.append(StringSubUtil.subString(localMusicBean.getUri()));
            sb.append(" getMusicNameFromSp: ");
            sb.append(StringSubUtil.subString(localMusicBean.getTitle()));
            sb.append(" storageType:");
            sb.append(localMusicBean.getStorageType());
            if (!LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                localMusicBean = null;
                sb.append(" check MusicFile is not Exist");
            }
            LogUtil.d(this.TAG, sb.toString());
        }
        return localMusicBean;
    }

    public LocalMusicBean getCurrentMusicFromSp(int i) {
        StringBuilder sb = new StringBuilder("getCurrentMusicFromSp ");
        sb.append(" type=");
        sb.append(i);
        String str = SPConstants.SP_KEY_CURRENT_MUSIC_LOCAL;
        if (i != 0) {
            if (i == 1) {
                str = SPConstants.SP_KEY_CURRENT_MUSIC_SD;
            } else if (i == 2) {
                str = SPConstants.SP_KEY_CURRENT_MUSIC_USB;
            }
        }
        LocalMusicBean localMusicBean = (LocalMusicBean) new Gson().fromJson(SPUtils.getInstance().getString(str, "null"), LocalMusicBean.class);
        if (localMusicBean != null) {
            sb.append(" LocalMusicBean path:");
            sb.append(StringSubUtil.subString(localMusicBean.getUri()));
            sb.append(" getMusicNameFromSp: ");
            sb.append(StringSubUtil.subString(localMusicBean.getTitle()));
            sb.append(" storageType:");
            sb.append(localMusicBean.getStorageType());
            if (!LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                localMusicBean = null;
                sb.append(" check MusicFile is not Exist");
            }
            LogUtil.d(this.TAG, sb.toString());
        }
        return localMusicBean;
    }

    public LocalMusicBean getCurrentPlayLocalMusic() {
        return this.currentLocalMusic.get();
    }

    public LocalMusicBean getCurrentPlayLocalMusic(int i) {
        return this.currentLocalMusicMaps.get(Integer.valueOf(i));
    }

    public LocalMusicBean getCurEnginePlayLocalMusic() {
        return this.currentLocalMusicMaps.get(Integer.valueOf(convertMusicEngineToStorageType(OnLineMusic.musicEngine)));
    }

    public LocalMusicBean getCurEngineMusicFromSp() {
        return getCurrentMusicFromSp(convertMusicEngineToStorageType(OnLineMusic.musicEngine));
    }

    public void setCurrentLocalMusic(LocalMusicBean localMusicBean) {
        if (localMusicBean != null) {
            LogUtil.d(this.TAG, "setCurrentLocalMusic:" + StringSubUtil.subString(localMusicBean.getUri()) + " musicName = " + StringSubUtil.subString(localMusicBean.getTitle()) + " isCollect = " + localMusicBean.isCollect() + " getDuration = " + localMusicBean.getDuration() + " storageType= " + localMusicBean.getStorageType() + " tName= " + Thread.currentThread().getName());
            this.currentLocalMusicMaps.put(Integer.valueOf(localMusicBean.getStorageType()), localMusicBean);
            if (OnLineMusic.musicEngine == convertStorageTypeToEngine(localMusicBean.getStorageType())) {
                this.currentLocalMusic.set(localMusicBean);
                return;
            }
            return;
        }
        LogUtil.d(this.TAG, "setCurrentLocalMusic null");
        this.currentLocalMusic.set(null);
        this.currentLocalMusicMaps.put(Integer.valueOf(convertMusicEngineToStorageType(OnLineMusic.musicEngine)), null);
    }

    public ObservableField<LocalMusicBean> getCurrentLocalMusic() {
        return this.currentLocalMusic;
    }

    public ObservableBoolean getIsPlaying() {
        return this.isPlaying;
    }

    public ObservableMap<Integer, Boolean> getIsPlayingMaps() {
        return this.isPlayingMaps;
    }

    public void setIsPlaying(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("setIsPlaying:");
        sb.append(z);
        if (this.isPlaying.get() != z) {
            this.isPlaying.set(z);
            sb.append(" and update");
        }
        LocalMusicBean curEnginePlayLocalMusic = getCurEnginePlayLocalMusic();
        int storageType = curEnginePlayLocalMusic != null ? curEnginePlayLocalMusic.getStorageType() : -1;
        for (int i = 0; i < 3; i++) {
            if (storageType == i) {
                this.isPlayingMaps.put(Integer.valueOf(i), Boolean.valueOf(z));
            } else {
                this.isPlayingMaps.put(Integer.valueOf(i), false);
            }
        }
        LogUtil.e(this.TAG, sb.toString());
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            CurrentMediaInfoUtils.getInstance().setPlayingState(z);
        }
    }

    private void updateLocalPlayStateUIOnAudioFocusFail() {
        StringBuilder sb = new StringBuilder("updateLocalMusicUIOnAudioFocusFail ");
        sb.append(" musicEngine= ");
        sb.append(OnLineMusic.musicEngine);
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            sb.append(" and setIsPlaying false");
            setIsPlaying(false);
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public void updateLocalPlayStateWhenMusicIsNotExist() {
        StringBuilder sb = new StringBuilder("updateLocalPlayStateWhenMusicIsNotExist：");
        sb.append(" musicEngine= ");
        sb.append(OnLineMusic.musicEngine);
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            setCurrentMusic(null);
            sb.append(" set curLocalMusic null due to music url is not exist !!!");
        }
        LogUtil.e(this.TAG, sb.toString());
    }

    public ObservableBoolean getIsUsbPlug() {
        return this.isUsbPlug;
    }

    public void setIsUsbPlug(Boolean bool) {
        LogUtil.d(this.TAG, "MusicPlayHelper setIsUsbPlug = " + bool);
        if (this.isUsbPlug.get() != bool.booleanValue()) {
            this.isUsbPlug.set(bool.booleanValue());
        }
        if (!bool.booleanValue() || isHasUsbPlug()) {
            return;
        }
        setHasUsbPlug(true);
    }

    public ObservableBoolean getIsSDPlug() {
        return this.isSDPlug;
    }

    public void setIsSDPlug(Boolean bool) {
        if (this.isSDPlug.get() != bool.booleanValue()) {
            this.isSDPlug.set(bool.booleanValue());
        }
        if (!bool.booleanValue() || isHasSdPlug()) {
            return;
        }
        setHasSdPlug(true);
    }

    public void deletePlayMusic(LocalMusicBean localMusicBean) {
        LocalMusicBean localMusicBean2;
        StringBuilder sb = new StringBuilder("deletePlayMusic");
        sb.append(" localMusicBeanList before size = ");
        sb.append(this.localMusicBeanList.size());
        LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
        if (this.localMusicBeanList.size() > 0) {
            if (localMusicBean.equals(currentPlayLocalMusic)) {
                int iIndexOf = this.localMusicBeanList.indexOf(currentPlayLocalMusic);
                this.localMusicBeanList.remove(iIndexOf);
                int size = this.localMusicBeanList.size();
                if (size <= 0) {
                    localMusicBean2 = null;
                } else if (size == iIndexOf) {
                    localMusicBean2 = this.localMusicBeanList.get(0);
                } else {
                    localMusicBean2 = this.localMusicBeanList.get(iIndexOf);
                }
                setCurrentMusic(localMusicBean2);
                if (this.isPlaying.get()) {
                    play(localMusicBean2, 0);
                } else if (localMusicBean2 != null) {
                    setCurrentPlayMusicState(new LocalMusicBean(0, localMusicBean2.getDuration(), 2, localMusicBean2.getStorageType()));
                } else {
                    setCurrentPlayMusicState(new LocalMusicBean(0, 0, 2, currentPlayLocalMusic.getStorageType()));
                }
                if (localMusicBean2 != null && Boolean.TRUE.equals(this.isCollectMusicMaps.get(Integer.valueOf(localMusicBean2.getStorageType()))) != localMusicBean2.isCollect()) {
                    setIsCollectMusic(localMusicBean2.getStorageType(), localMusicBean2.isCollect());
                }
            } else {
                this.localMusicBeanList.remove(localMusicBean);
            }
            sb.append(" remove and after size = ");
            sb.append(this.localMusicBeanList.size());
            LogUtil.d(this.TAG, sb.toString());
            if (this.localMusicBeanList.size() == 0) {
                stopPlayLocalMusic();
            }
        }
    }

    public String setCollectMusic(boolean z) {
        StringBuilder sb = new StringBuilder("setCollectMusic");
        LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
        if (currentPlayLocalMusic == null) {
            sb.append(" REASON_NOT_FOUND");
            LogUtil.d(this.TAG, sb.toString());
            return MediaControl.REASON_NOT_FOUND;
        }
        if (z && currentPlayLocalMusic.isCollect()) {
            sb.append(" REASON_ALREADY_COLLECTED");
            LogUtil.d(this.TAG, sb.toString());
            return MediaControl.REASON_ALREADY_COLLECTED;
        }
        if (!z && !currentPlayLocalMusic.isCollect()) {
            sb.append(" REASON_NOT_COLLECTED");
            LogUtil.d(this.TAG, sb.toString());
            return MediaControl.REASON_NOT_COLLECTED;
        }
        sb.append(" isCollectMusic get=");
        sb.append(this.isCollectMusic.get());
        sb.append(" isCollected=");
        sb.append(z);
        if (this.isCollectMusic.get() != z) {
            int storageType = currentPlayLocalMusic.getStorageType();
            sb.append(" storageType=");
            sb.append(storageType);
            LocalMusicDB.update(currentPlayLocalMusic);
            currentPlayLocalMusic.setCollect(z);
            setCurrentLocalMusic(currentPlayLocalMusic);
            setIsCollectMusic(storageType, z);
            if (storageType == 0) {
                ObservableBoolean observableBoolean = this.clickCollectFromPlayerLocal;
                observableBoolean.set(true ^ observableBoolean.get());
            } else if (storageType == 1) {
                ObservableBoolean observableBoolean2 = this.clickCollectFromPlayerSD;
                observableBoolean2.set(true ^ observableBoolean2.get());
            } else if (storageType == 2) {
                ObservableBoolean observableBoolean3 = this.clickCollectFromPlayerUSB;
                observableBoolean3.set(true ^ observableBoolean3.get());
            }
        }
        LogUtil.d(this.TAG, sb.toString());
        return "";
    }

    private void initMusicPlayer() {
        if (this.musicPlayer == null) {
            MusicPlayer musicPlayer = new MusicPlayer();
            this.musicPlayer = musicPlayer;
            musicPlayer.setiPlayerListener(this);
            this.musicPlayer.setErrorListener();
        }
    }

    public MusicPlayer getMusicPlayer() {
        initMusicPlayer();
        return this.musicPlayer;
    }

    public ObservableBoolean getIsCollectMusic() {
        return this.isCollectMusic;
    }

    public void setIsCollectMusic(boolean z) {
        StringBuilder sb = new StringBuilder("setIsCollectMusic: ");
        if (getCurrentPlayLocalMusic() == null) {
            sb.append(" getCurrentPlayLocalMusic is Null  and  isCollectMusic = false");
            this.isCollectMusic.set(false);
            LogUtil.d(this.TAG, sb.toString());
            return;
        }
        sb.append(" curMusic title= ");
        sb.append(StringSubUtil.subString(getCurrentPlayLocalMusic().getTitle()));
        sb.append(" isCollect= ");
        sb.append(getCurrentPlayLocalMusic().isCollect());
        sb.append(" and setIsCollectMusic= ");
        sb.append(z);
        if (this.isCollectMusic.get() != z) {
            this.isCollectMusic.set(z);
            sb.append(" do update!");
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public void setIsCollectMusic(int i, boolean z) {
        StringBuilder sb = new StringBuilder("setIsCollectMusic: ");
        LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic(i);
        int iConvertStorageTypeToEngine = convertStorageTypeToEngine(i);
        sb.append(" storageType=");
        sb.append(i);
        sb.append(" convertEngine=");
        sb.append(iConvertStorageTypeToEngine);
        if (currentPlayLocalMusic == null) {
            sb.append(" getCurrentPlayLocalMusic is Null  and  isCollectMusic = false");
            if (OnLineMusic.musicEngine == iConvertStorageTypeToEngine) {
                this.isCollectMusic.set(false);
            }
            this.isCollectMusicMaps.put(Integer.valueOf(i), false);
            LogUtil.d(this.TAG, sb.toString());
            return;
        }
        sb.append(" curMusic title= ");
        sb.append(StringSubUtil.subString(currentPlayLocalMusic.getTitle()));
        sb.append(" isCollect= ");
        sb.append(currentPlayLocalMusic.isCollect());
        sb.append(" and set IsCollectMusic= ");
        sb.append(z);
        if (Boolean.TRUE.equals(this.isCollectMusicMaps.getOrDefault(Integer.valueOf(i), false)) != z) {
            this.isCollectMusicMaps.put(Integer.valueOf(i), Boolean.valueOf(z));
            sb.append(" isCollectMusicMaps update!");
        }
        if (OnLineMusic.musicEngine == iConvertStorageTypeToEngine && this.isCollectMusic.get() != z) {
            this.isCollectMusic.set(z);
            sb.append(" isCollectMusic update!");
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public ObservableMap<Integer, Boolean> getIsCollectMusicMaps() {
        return this.isCollectMusicMaps;
    }

    public void resetPlayState(int i) {
        MusicPlayer musicPlayer = this.musicPlayer;
        if (musicPlayer != null) {
            musicPlayer.setState(i);
        }
    }

    public boolean initMusicListForVoice(int i, CharSequence charSequence) {
        LocalMusicBean currentMusicFromSp;
        StringBuilder sb = new StringBuilder();
        sb.append("initMusicListForVoice");
        ArrayList arrayList = new ArrayList();
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        if (i == 1 && !this.isSDPlug.get()) {
            sb.append(" sourceType=1 and isSDPlug=false nd return false !");
            LogUtil.e(this.TAG, sb.toString());
            return false;
        }
        if (i == 2 && !this.isUsbPlug.get()) {
            sb.append(" sourceType=2 and isUsbPlug=false nd return false !");
            LogUtil.e(this.TAG, sb.toString());
            return false;
        }
        sb.append(" sourceType=");
        sb.append(i);
        if (zIsEmpty) {
            currentMusicFromSp = null;
        } else {
            sb.append(" charSequence:");
            sb.append(charSequence);
            List<LocalMusicBean> musicWithTitleAndStorageTypeMatch = LocalMusicDB.getMusicWithTitleAndStorageTypeMatch(charSequence.toString(), i);
            if (musicWithTitleAndStorageTypeMatch.size() <= 0) {
                sb.append(" queryList size = 0 and return false !");
                LogUtil.e(this.TAG, sb.toString());
                return false;
            }
            currentMusicFromSp = musicWithTitleAndStorageTypeMatch.get(0);
        }
        arrayList.addAll(LocalMusicDB.getLocalMusicByStorageType(i));
        int size = arrayList.size();
        if (size == 0) {
            sb.append(" no exist song from ");
            sb.append(i);
            LogUtil.e(this.TAG, sb.toString());
            return false;
        }
        LocalMusicBean localMusicBean = this.currentLocalMusicMaps.get(Integer.valueOf(i));
        if ((zIsEmpty && localMusicBean != null && localMusicBean.getStorageType() == i) || (localMusicBean != null && localMusicBean.equals(currentMusicFromSp))) {
            sb.append(" sourceType or curLocalMusic is equals return true ");
            LogUtil.e(this.TAG, sb.toString());
            return true;
        }
        arrayList.sort(new PinyinComparator());
        if (zIsEmpty) {
            if (localMusicBean == null || !LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                currentMusicFromSp = getCurrentMusicFromSp(i);
                if (currentMusicFromSp == null || currentMusicFromSp.getStorageType() != i || !LocalMusicUtils.checkMusicFileExist(currentMusicFromSp.getUri())) {
                    currentMusicFromSp = arrayList.get(0);
                }
            } else {
                currentMusicFromSp = localMusicBean;
            }
        } else {
            this.isFormVoice = true;
        }
        sb.append(" playTitle=");
        sb.append(StringSubUtil.subString(StringSubUtil.subString(currentMusicFromSp.getTitle())));
        sb.append(" playSize=");
        sb.append(size);
        sb.append(" isEmptyChar=");
        sb.append(zIsEmpty);
        setLocalMusicBeanList(arrayList, currentMusicFromSp);
        if (zIsEmpty && isMatchMusicEngineByStorageType(i)) {
            int duration = currentMusicFromSp.getDuration();
            int localProgressByStorageType = getLocalProgressByStorageType(currentMusicFromSp.getStorageType());
            sb.append(" before spProgress= ");
            sb.append(localProgressByStorageType);
            sb.append(" duration= ");
            sb.append(duration);
            if (localProgressByStorageType > duration) {
                localProgressByStorageType = 0;
            }
            sb.append(" after spProgress= ");
            sb.append(localProgressByStorageType);
            setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, duration, 0, currentMusicFromSp.getStorageType()));
            if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
                sb.append(" and update CMIU progress");
                long j = localProgressByStorageType;
                CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(j, j, duration);
            }
        }
        LogUtil.e(this.TAG, sb.toString());
        return true;
    }

    public void playAfterUpdateData() {
        StringBuilder sb = new StringBuilder("playAfterUpdateData");
        this.mUpdateDataLock.lock();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            ArrayList arrayList = new ArrayList();
            for (LocalMusicBean localMusicBean : this.localMusicBeanList) {
                if (!LocalMusicUtils.checkMusicFileExist(localMusicBean.getUri())) {
                    arrayList.add(localMusicBean);
                }
            }
            sb.append(" before size=");
            sb.append(this.localMusicBeanList.size());
            this.localMusicBeanList.removeAll(arrayList);
            sb.append(" after size=");
            sb.append(this.localMusicBeanList.size());
            if (this.localMusicBeanList.size() == 0) {
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.e(MusicPlayHelper.this.TAG, "playAfterUpdateData data size = 0 and setCurrentMusic Null");
                        MusicPlayHelper.this.setCurrentMusic(null);
                    }
                });
            }
        } finally {
            this.mUpdateDataLock.unlock();
            sb.append(" finally unlock and updateData costTime ");
            sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
            sb.append(" tName = ");
            sb.append(Thread.currentThread().getName());
            LogUtil.e(this.TAG, sb.toString());
        }
    }

    public boolean isHasUsbPlug() {
        return this.hasUsbPlug;
    }

    public void setHasUsbPlug(boolean z) {
        SPUtils.getInstance().put(SPConstants.KEY_USB_PLUG, true);
        this.hasUsbPlug = z;
    }

    public boolean isHasSdPlug() {
        return this.hasSdPlug;
    }

    public void setHasSdPlug(boolean z) {
        SPUtils.getInstance().put(SPConstants.KEY_SD_PLUG, true);
        this.hasSdPlug = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean checkCurrentPlayMusic(boolean r7) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper.checkCurrentPlayMusic(boolean):boolean");
    }

    public boolean playArtistMusicFromVoice(String str) {
        ArrayList arrayList = new ArrayList();
        int size = 0;
        for (int i = 0; i < 3; i++) {
            arrayList.addAll(LocalMusicDB.getArtistNameAndStorageType(str, i));
            size = arrayList.size();
            if (size > 0) {
                break;
            }
        }
        if (size <= 0) {
            return false;
        }
        playListByItem(arrayList, arrayList.get(0));
        return true;
    }

    public String playCollectMusicFromVoice() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("playCollectMusicFromVoice:");
        int i = OnLineMusic.musicEngine;
        int iConvertMusicEngineToStorageType = convertMusicEngineToStorageType(i);
        sb.append(" musicEngine= ");
        sb.append(i);
        sb.append(" convert to storageType= ");
        sb.append(iConvertMusicEngineToStorageType);
        ArrayList arrayList = new ArrayList();
        if (iConvertMusicEngineToStorageType == 0) {
            arrayList.addAll(LocalMusicDB.getCollectLocalBeanList(true, iConvertMusicEngineToStorageType));
        } else if (iConvertMusicEngineToStorageType == 1 && getInstance().isSDPlug.get()) {
            arrayList.addAll(LocalMusicDB.getCollectLocalBeanList(true, iConvertMusicEngineToStorageType));
        } else if (iConvertMusicEngineToStorageType == 2 && getInstance().isUsbPlug.get()) {
            arrayList.addAll(LocalMusicDB.getCollectLocalBeanList(true, iConvertMusicEngineToStorageType));
        }
        int size = arrayList.size();
        sb.append(" collectListMusic size=");
        sb.append(arrayList.size());
        if (size > 0) {
            playListByItem(arrayList, arrayList.get(0));
            setCollectList(true);
            str = "";
        } else {
            str = MediaControl.REASON_NOT_FOUND;
        }
        LogUtil.d(this.TAG, sb.toString());
        return str;
    }

    public List<LocalMusicBean> getLocalMusicBeanList() {
        return this.localMusicBeanList;
    }

    public void stopPlayUSBOrSD(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("stopPlayUSBOrSD ");
        if (z) {
            getInstance().setIsUsbPlug(false);
        } else {
            getInstance().setIsSDPlug(false);
        }
        LocalMusicBean localMusicBean = this.currentLocalMusic.get();
        if (localMusicBean != null) {
            if (!(z && localMusicBean.getStorageType() == 2) && (z || localMusicBean.getStorageType() != 1)) {
                return;
            }
            this.localMusicBeanList.clear();
            sb.append(" clear localMusicBeanList");
            sb.append(this.localMusicBeanList.size());
            if (getIsPlaying().get()) {
                getInstance().resetPlayState(0);
                saveCurLocalMusicEngineProgress();
            }
            sb.append(" isUSB=");
            sb.append(z);
            if (CurrentMediaInfoUtils.getInstance().getPlayingState() && getInstance().getIsPlaying().get() && (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3)) {
                sb.append(" expectPlayingStatusShouldNotBeOverride true");
                CurrentMediaInfoUtils.getInstance().expectPlayingStatusShouldNotBeOverride = true;
            }
            LogUtil.d(this.TAG, sb.toString());
            setCurrentMusic(null);
        }
    }

    public int reloadPlayList(int i) {
        StringBuilder sb = new StringBuilder("reloadPlayList ");
        sb.append(" storageType = ");
        sb.append(i);
        sb.append(" localMusicBeanList before size  = ");
        sb.append(this.localMusicBeanList.size());
        this.localMusicBeanList.clear();
        this.localMusicBeanList.addAll(LocalMusicDB.getLocalMusicByStorageType(i));
        Collections.sort(this.localMusicBeanList, new PinyinComparator());
        sb.append(" clear and addAll after size  = ");
        sb.append(this.localMusicBeanList.size());
        LogUtil.d(this.TAG, sb.toString());
        return this.localMusicBeanList.size();
    }

    public int getRandomInt(int i, int i2) {
        if (i <= 0) {
            return 0;
        }
        int iNextInt = this.random.nextInt(i);
        return (i2 != iNextInt || i <= 1) ? iNextInt : iNextInt < i - 1 ? iNextInt + 1 : iNextInt - 1;
    }

    public boolean isCollectList() {
        return this.isCollectList;
    }

    public void setCollectList(boolean z) {
        this.isCollectList = z;
    }

    public void memoryPlay(String[] strArr) {
        boolean z;
        if (OnLineMusic.getNeedRestore()) {
            LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
            if (currentPlayLocalMusic != null) {
                int i = 0;
                while (true) {
                    if (i >= strArr.length) {
                        z = false;
                        break;
                    } else {
                        if (strArr[i] != null && currentPlayLocalMusic.getUri().contains(strArr[i])) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                }
                LogUtil.e(this.TAG, "memoryPlay play File" + currentPlayLocalMusic.getUri() + " need play:" + OnLineMusic.NEED_RESTORE + " isExistPath=" + z + " spLocalMusic=" + currentPlayLocalMusic.equals(getCurrentMusicFromSp()));
                if (z) {
                    if (LocalMusicUtils.checkMusicFileExist(currentPlayLocalMusic.getUri())) {
                        play(currentPlayLocalMusic, getLocalProgressByStorageType(currentPlayLocalMusic.getStorageType()));
                    }
                    OnLineMusic.setNeedRestore(false);
                    return;
                }
                return;
            }
            return;
        }
        LogUtil.e(this.TAG, "memoryPlay false");
    }

    public void memoryPlayAfterScanStart(LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder();
        sb.append("memoryPlay play File ");
        if (localMusicBean != null && LocalMusicUtils.checkMusicFileLength(localMusicBean.getUri())) {
            sb.append(localMusicBean.getUri());
            sb.append(" storage=");
            sb.append(localMusicBean.getStorageType());
            if (!getIsPlaying().get()) {
                int localProgressByStorageType = getLocalProgressByStorageType(localMusicBean.getStorageType());
                setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, localMusicBean.getDuration(), 0, localMusicBean.getStorageType()));
                play(localMusicBean, localProgressByStorageType);
            }
            OnLineMusic.setNeedRestore(false);
        } else {
            sb.append("is no exist");
        }
        LogUtil.e(this.TAG, sb.toString());
    }

    public boolean memoryPlayAccOn() {
        StringBuilder sb = new StringBuilder("memoryPlayAccOn");
        LocalMusicBean curEngineMusicFromSp = getCurEngineMusicFromSp();
        sb.append(" musicEngine=");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" localMusicSp isExist=");
        sb.append(curEngineMusicFromSp != null);
        if (!getIsPlaying().get() && curEngineMusicFromSp != null && LocalMusicUtils.checkMusicFileExist(curEngineMusicFromSp.getUri())) {
            int storageType = curEngineMusicFromSp.getStorageType();
            int localProgressByStorageType = getLocalProgressByStorageType(storageType);
            int duration = curEngineMusicFromSp.getDuration();
            sb.append(" spProgress=");
            sb.append(localProgressByStorageType);
            if (localProgressByStorageType > duration) {
                localProgressByStorageType = 0;
            }
            sb.append(" setProgress=");
            sb.append(localProgressByStorageType);
            sb.append(" duration=");
            sb.append(duration);
            sb.append(" storageType=");
            sb.append(storageType);
            LogUtil.e(this.TAG, sb.toString());
            setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, duration, 0, storageType));
            play(curEngineMusicFromSp, localProgressByStorageType);
            return true;
        }
        sb.append(" and memory play fail");
        LogUtil.e(this.TAG, sb.toString());
        return false;
    }

    public void autoPlayShowCar() {
        StringBuilder sb = new StringBuilder("autoPlayShowCar");
        LocalMusicBean localMusicBeanAddShowCarMusic = LocalMusicUtils.addShowCarMusic(0);
        if (!TextUtils.isEmpty(localMusicBeanAddShowCarMusic.getTitle())) {
            sb.append(" playListByItem size=");
            sb.append(this.localMusicBeanList.size());
            sb.append(" contains showCar=");
            sb.append(this.localMusicBeanList.contains(localMusicBeanAddShowCarMusic));
            playListByItem(new ArrayList(this.localMusicBeanList), localMusicBeanAddShowCarMusic);
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public boolean isShowCarMusicPlaying() {
        return isShowCarMusicBean(getCurrentPlayLocalMusic());
    }

    public boolean isShowCarMusicBean(LocalMusicBean localMusicBean) {
        return LocalMusicUtils.addShowCarMusic(0).equals(localMusicBean);
    }

    public boolean playAudioFocus() {
        StringBuilder sb = new StringBuilder();
        if (AudioFocusManager.getInstance().isHoldAudioFocus()) {
            return true;
        }
        int iRequestAudioFocus = AudioFocusManager.getInstance().requestAudioFocus();
        sb.append(" audioFocus：");
        sb.append(iRequestAudioFocus);
        if (1 == iRequestAudioFocus) {
            return true;
        }
        sb.append(" play_failed");
        LogUtil.e(this.TAG, sb.toString());
        if (!DeviceInfoUtils.isInCall()) {
            ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.play_failed));
        }
        updateLocalPlayStateUIOnAudioFocusFail();
        return false;
    }

    public boolean isMemoryPlay() {
        return this.isMemoryPlay;
    }

    public void setMemoryPlay(boolean z) {
        this.isMemoryPlay = z;
    }

    public void setMusicEngineByStorageType(int i) {
        StringBuilder sb = new StringBuilder("setMusicEngineByStorageType");
        sb.append(" before musicEngine=");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" set storageType=");
        sb.append(i);
        int i2 = i != 0 ? i != 1 ? i != 2 ? -1 : 4 : 3 : 5;
        if (i2 != -1) {
            if (i2 == OnLineMusic.musicEngine) {
                sb.append(" engine already set and return");
            } else {
                sb.append(" convert to musicEngine= ");
                sb.append(i2);
                OnLineMusic.setMusicEngine(i2);
            }
        }
        LogUtil.e(this.TAG, sb.toString());
    }

    public int getLocalProgressByStorageType(int i) {
        StringBuilder sb = new StringBuilder("getLocalProgressByStorageType");
        sb.append(" storageType=");
        sb.append(i);
        String str = i == 0 ? SPConstants.KEY_SAVE_PROGRESS_LOCAL : i == 2 ? SPConstants.KEY_SAVE_PROGRESS_USB : i == 1 ? SPConstants.KEY_SAVE_PROGRESS_SD : SPConstants.KEY_SAVE_PROGRESS;
        int i2 = (int) SPUtils.getInstance().getLong(str, 0L);
        sb.append(" spKey=");
        sb.append(str);
        sb.append(" spProgress=");
        sb.append(i2);
        LogUtil.e(this.TAG, sb.toString());
        return i2;
    }

    public int convertStorageTypeToEngine(int i) {
        StringBuilder sb = new StringBuilder("convertStorageTypeToEngine");
        sb.append(" storageType= ");
        sb.append(i);
        int i2 = i != 0 ? i != 1 ? i != 2 ? -1 : 4 : 3 : 5;
        sb.append(" convert to Engine= ");
        sb.append(i2);
        return i2;
    }

    public int convertMusicEngineToStorageType(int i) {
        StringBuilder sb = new StringBuilder("convertMusicEngineToStorageType");
        sb.append(" musicEngine= ");
        sb.append(i);
        int i2 = i != 3 ? i != 4 ? i != 5 ? -1 : 0 : 2 : 1;
        sb.append(" convert to storageType= ");
        sb.append(i2);
        LogUtil.e(this.TAG, sb.toString());
        return i2;
    }

    public boolean isMatchMusicEngineByStorageType(int i) {
        return OnLineMusic.musicEngine == convertStorageTypeToEngine(i);
    }

    public boolean isMatchLocalMusicEngine() {
        return OnLineMusic.musicEngine == 5;
    }

    public boolean ensureCurrentMusicEngineData() {
        int localProgressByStorageType;
        StringBuilder sb = new StringBuilder("ensureCurrentMusicEngineData");
        int i = OnLineMusic.musicEngine;
        int iConvertMusicEngineToStorageType = convertMusicEngineToStorageType(i);
        sb.append(" musicEngine=");
        sb.append(i);
        sb.append(" convertStorageType=");
        sb.append(iConvertMusicEngineToStorageType);
        if (iConvertMusicEngineToStorageType == -1) {
            sb.append(" invalid local engine and return false!!!");
            LogUtil.e(this.TAG, sb.toString());
            return false;
        }
        int size = this.localMusicBeanList.size();
        sb.append(" data_size= ");
        sb.append(size);
        if (size > 0) {
            int storageType = this.localMusicBeanList.get(0).getStorageType();
            sb.append(" curStorageType= ");
            sb.append(storageType);
            boolean z = storageType == iConvertMusicEngineToStorageType;
            sb.append(" isSameStorageType= ");
            sb.append(z);
            if (z) {
                sb.append(" already init data and return true");
                LogUtil.e(this.TAG, sb.toString());
                return true;
            }
        }
        ArrayList arrayList = new ArrayList(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
        sb.append(" db_size=");
        sb.append(arrayList.size());
        if (!arrayList.isEmpty()) {
            arrayList.sort(new PinyinComparator());
            if (initPlayMusicList(arrayList)) {
                LocalMusicBean currentMusicFromSp = getCurrentMusicFromSp(iConvertMusicEngineToStorageType);
                if (currentMusicFromSp == null) {
                    currentMusicFromSp = arrayList.get(0);
                    localProgressByStorageType = 0;
                } else {
                    localProgressByStorageType = getLocalProgressByStorageType(iConvertMusicEngineToStorageType);
                }
                setCurrentMusic(currentMusicFromSp);
                resetPlayState(0);
                LocalMusicBean currentPlayLocalMusic = getCurrentPlayLocalMusic();
                if (currentPlayLocalMusic != null && currentPlayLocalMusic.getStorageType() == iConvertMusicEngineToStorageType) {
                    sb.append(" musicTitle= ");
                    sb.append(currentPlayLocalMusic.getTitle());
                    setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, currentPlayLocalMusic.getDuration(), 4, currentPlayLocalMusic.getStorageType()));
                }
                sb.append(" and return true");
                LogUtil.e(this.TAG, sb.toString());
                return true;
            }
        } else {
            sb.append(" clear musicList");
            this.localMusicBeanList.clear();
            updateLocalPlayStateWhenMusicIsNotExist();
        }
        sb.append(" and return false");
        LogUtil.e(this.TAG, sb.toString());
        return false;
    }

    public void saveCurLocalMusicEngineProgress() {
        StringBuilder sb = new StringBuilder("saveCurLocalMusicEngineProgress");
        int i = OnLineMusic.musicEngine;
        sb.append(" musicEngine=");
        sb.append(i);
        if (i == 3 || i == 4 || i == 5) {
            int iConvertMusicEngineToStorageType = convertMusicEngineToStorageType(i);
            LocalMusicBean localMusicBean = this.currentPlayMusicStateMaps.get(Integer.valueOf(iConvertMusicEngineToStorageType));
            int progress = localMusicBean != null ? localMusicBean.getProgress() : -1;
            sb.append(" storageType=");
            sb.append(iConvertMusicEngineToStorageType);
            sb.append(" innerPb=");
            sb.append(progress);
            sb.append(" isAccOff=");
            sb.append(this.isAccOff);
            CurrentMediaInfoUtils.getInstance().saveCurrentProgress();
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    private void saveMusicProgressByStorage(int i) {
        StringBuilder sb = new StringBuilder("saveMusicProgressByStorage");
        String str = i == 0 ? SPConstants.KEY_SAVE_PROGRESS_LOCAL : i == 1 ? SPConstants.KEY_SAVE_PROGRESS_SD : i == 2 ? SPConstants.KEY_SAVE_PROGRESS_USB : "";
        sb.append(" spKey=");
        sb.append(str);
        LocalMusicBean localMusicBean = this.currentPlayMusicStateMaps.get(Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            long progress = localMusicBean != null ? localMusicBean.getProgress() : 0L;
            sb.append(" setProgress=");
            sb.append(progress);
            sb.append(" and update!");
            SPUtils.getInstance().put(str, progress);
        }
        LogUtil.d(this.TAG, sb.toString());
    }
}
