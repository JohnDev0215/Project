package com.byd.music.utils;

import android.os.SystemProperties;
import android.text.TextUtils;
import com.blankj.utilcode.util.AppUtils;
import com.byd.music.api.ApiMethod;
import com.byd.music.beans.EventString;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.style.BydUiStyleConfig;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public class ShowCarModeUtil {
    private static final String TAG = "ShowCarModeUtil";
    private static final String KEY_CAR_MODE = "persist.sys.mediacenter.showcar";
    private static boolean showCarMode = Boolean.parseBoolean(SystemProperties.get(KEY_CAR_MODE, "false"));

    public static boolean getShowCarMode() {
        return showCarMode;
    }

    public static void setCarMode(boolean z) {
        if (!BydUiStyleConfig.isDenza()) {
            LogUtil.e(TAG, "setCarMode enter with isDenza false, just return.");
            return;
        }
        LogUtil.e(TAG, "setCarMode enter with showCarMode = " + showCarMode + ", outShowCarMode = " + z);
        if (showCarMode != z) {
            new Throwable().printStackTrace();
            setCarModeProperty(z);
        }
    }

    public static boolean isShowCarModeStyle() {
        LogUtil.e(TAG, "isShowCarModeStyle :" + BydUiStyleConfig.getUiStyle());
        return getShowCarMode() && BydUiStyleConfig.isDenza();
    }

    public static boolean isShowCarFileExist(String str) {
        return isShowCarModeStyle() && TextUtils.equals(str, LocalMusicUtils.BYD_SHOW_CAR_PATH_WITH_CAR_STYLE);
    }

    private static void setCarModeProperty(boolean z) {
        SystemProperties.set(KEY_CAR_MODE, z ? "true" : "false");
        showCarMode = z;
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.utils.ShowCarModeUtil.1
            @Override // java.lang.Runnable
            public void run() {
                final LocalMusicBean localMusicBeanAddShowCarMusic = LocalMusicUtils.addShowCarMusic(0);
                if (ShowCarModeUtil.showCarMode) {
                    LocalMusicDB.insert(localMusicBeanAddShowCarMusic);
                } else {
                    LocalMusicDB.deleteLocalMusicByIdAndStorageType(localMusicBeanAddShowCarMusic.getMusicId(), localMusicBeanAddShowCarMusic.getStorageType());
                }
                if (AppUtils.isAppForeground()) {
                    EventBus.getDefault().postSticky(new EventString("show_car_mode_status", ShowCarModeUtil.showCarMode ? "true" : "false"));
                }
                AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.utils.ShowCarModeUtil.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StringBuilder sb = new StringBuilder("setCarModeProperty");
                        sb.append(" showCarMode=");
                        sb.append(ShowCarModeUtil.showCarMode);
                        if (ShowCarModeUtil.showCarMode) {
                            List<LocalMusicBean> localMusicByStorageType = LocalMusicDB.getLocalMusicByStorageType(0);
                            LocalMusicUtils.sortShowCarMusicList(localMusicByStorageType);
                            MusicPlayHelper.getInstance().setLocalMusicBeanList(localMusicByStorageType, localMusicBeanAddShowCarMusic);
                            MusicPlayHelper.getInstance().autoPlayShowCar();
                            sb.append(" autoPlayShowCar");
                        } else {
                            boolean zIsMatchLocalMusicEngine = MusicPlayHelper.getInstance().isMatchLocalMusicEngine();
                            sb.append(" isLocalEngine=");
                            sb.append(zIsMatchLocalMusicEngine);
                            if (!zIsMatchLocalMusicEngine) {
                                List<LocalMusicBean> localMusicByStorageType2 = LocalMusicDB.getLocalMusicByStorageType(0);
                                LocalMusicUtils.sortShowCarMusicList(localMusicByStorageType2);
                                MusicPlayHelper.getInstance().setLocalMusicBeanList(localMusicByStorageType2);
                                if (!localMusicByStorageType2.isEmpty()) {
                                    LocalMusicBean localMusicBean = localMusicByStorageType2.get(0);
                                    MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(0, localMusicBean.getDuration(), 4, localMusicBean.getStorageType()));
                                }
                                LogUtil.e(ShowCarModeUtil.TAG, sb.toString());
                                return;
                            }
                            boolean zIsShowCarMusicPlaying = MusicPlayHelper.getInstance().isShowCarMusicPlaying();
                            sb.append(" isShowCarMusicPlaying=");
                            sb.append(zIsShowCarMusicPlaying);
                            if (zIsShowCarMusicPlaying) {
                                CurrentMediaInfoUtils.getInstance().curProgress.set(0L);
                                CurrentMediaInfoUtils.getInstance().expectPlayingStatusShouldNotBeOverride = true;
                                ApiMethod.playOrStop(false);
                                CurrentMediaInfoUtils.getInstance().setPlayingState(false);
                                CurrentMediaInfoUtils.getInstance().notifyWidgetUpdate(true);
                                MusicPlayHelper.getInstance().setCurrentMusic(null);
                            }
                            List<LocalMusicBean> localMusicByStorageType3 = LocalMusicDB.getLocalMusicByStorageType(0);
                            LocalMusicUtils.sortShowCarMusicList(localMusicByStorageType3);
                            if (!localMusicByStorageType3.isEmpty()) {
                                LocalMusicBean localMusicBean2 = localMusicByStorageType3.get(0);
                                MusicPlayHelper.getInstance().setLocalMusicBeanList(localMusicByStorageType3, localMusicBean2);
                                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(0, localMusicBean2.getDuration(), 4, localMusicBean2.getStorageType()));
                                MusicPlayHelper.getInstance().playMusicFromApi();
                            } else {
                                MusicPlayHelper.getInstance().setLocalMusicBeanList(localMusicByStorageType3);
                            }
                        }
                        LogUtil.e(ShowCarModeUtil.TAG, sb.toString());
                    }
                }, 1000L);
            }
        });
    }
}
