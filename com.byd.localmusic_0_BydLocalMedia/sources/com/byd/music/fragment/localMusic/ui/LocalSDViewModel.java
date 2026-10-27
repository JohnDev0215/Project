package com.byd.music.fragment.localMusic.ui;

import androidx.databinding.ObservableBoolean;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class LocalSDViewModel extends BaseLocalViewModel {
    public ObservableBoolean ifSeekbarDragging;

    @Override // com.byd.music.fragment.localMusic.ui.BaseLocalViewModel, com.byd.music.base.BaseViewModel
    protected void onCreate() {
        this.ifSeekbarDragging = new ObservableBoolean(false);
        super.onCreate();
        this.localSdUsbMusicBeanItemBinding = ItemBinding.of(BR.localSdUsbMusicBean, R.layout.item_musiclist_local_sd);
        this.localSdUsbMusicBeanItemBinding.bindExtra(BR.viewModel, this);
        this.collectLocalSdUsbMusicBeanItemBinding = ItemBinding.of(BR.collectLocalSdUsbMusicBean, R.layout.item_collect_musiclist_local_sd);
        this.collectLocalSdUsbMusicBeanItemBinding.bindExtra(BR.viewModel, this);
    }

    @Override // com.byd.music.fragment.localMusic.ui.BaseLocalViewModel
    public void initData() {
        LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp(1);
        MusicPlayHelper.getInstance().currentLocalMusicMaps.putIfAbsent(1, currentMusicFromSp);
        MusicPlayHelper.getInstance().getPlayModeMaps().putIfAbsent(1, Integer.valueOf(MusicPlayHelper.getInstance().getPlayModeByStorageTypeFromSp(1)));
        if (MusicPlayHelper.getInstance().currentPlayMusicStateMaps.get(1) == null) {
            if (currentMusicFromSp != null) {
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(MusicPlayHelper.getInstance().getLocalProgressByStorageType(currentMusicFromSp.getStorageType()), currentMusicFromSp.getDuration(), 4, currentMusicFromSp.getStorageType()));
            } else {
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(0, 0, 4, 1));
            }
        }
        MusicPlayHelper.getInstance().getIsCollectMusicMaps().putIfAbsent(1, Boolean.valueOf(currentMusicFromSp != null && currentMusicFromSp.isCollect()));
        super.initData();
    }
}
