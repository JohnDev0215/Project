package com.byd.amazon.music;

import com.byd.amazon.music.bean.BooleanResult;
import com.byd.amazon.music.bean.MusicItemResult;
import com.byd.amazon.music.bean.PlayModeResult;
import com.byd.amazon.music.bean.PlayStateResult;
import com.byd.amazon.music.bean.Result;
import com.byd.amazon.music.param.PlayMode;
import com.byd.amazon.music.param.VodType;

/* JADX INFO: loaded from: classes.dex */
public interface IMusicVoiceControl {
    Result closePlayListPage();

    Result exitMusic();

    PlayModeResult getCurPlayMode();

    MusicItemResult getNowPlayingSong();

    PlayStateResult getPlayState();

    BooleanResult isLogIn();

    BooleanResult isPlayListPageOpen();

    Result openPlayListPage();

    Result pause();

    Result play();

    Result playByFavorite();

    Result playById(String str, IPlayStateCallBack iPlayStateCallBack);

    Result playByVod(String str, VodType vodType, IMusicItemsCallBack iMusicItemsCallBack);

    Result playNext();

    Result playPre();

    Result replay();

    Result setFavorite();

    Result setPlayMode(PlayMode playMode);

    Result stop();

    Result unSetFavorite();
}
