package com.byd.music.fragment.localMusic.ui;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableMap;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicViewModel extends BaseLocalViewModel {
    public ObservableBoolean ifSeekbarDragging;

    @Override // com.byd.music.fragment.localMusic.ui.BaseLocalViewModel, com.byd.music.base.BaseViewModel
    protected void onCreate() {
        this.ifSeekbarDragging = new ObservableBoolean(false);
        super.onCreate();
        this.localSdUsbMusicBeanItemBinding = ItemBinding.of(BR.localSdUsbMusicBean, R.layout.item_musiclist_local_music);
        this.localSdUsbMusicBeanItemBinding.bindExtra(BR.viewModel, this);
        this.collectLocalSdUsbMusicBeanItemBinding = ItemBinding.of(BR.collectLocalSdUsbMusicBean, R.layout.item_collect_musiclist_local_music);
        this.collectLocalSdUsbMusicBeanItemBinding.bindExtra(BR.viewModel, this);
    }

    @Override // com.byd.music.fragment.localMusic.ui.BaseLocalViewModel
    public void initData() {
        boolean z = false;
        LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp(0);
        MusicPlayHelper.getInstance().currentLocalMusicMaps.putIfAbsent(0, currentMusicFromSp);
        MusicPlayHelper.getInstance().getPlayModeMaps().putIfAbsent(0, Integer.valueOf(MusicPlayHelper.getInstance().getPlayModeByStorageTypeFromSp(0)));
        if (MusicPlayHelper.getInstance().currentPlayMusicStateMaps.get(0) == null) {
            if (currentMusicFromSp != null) {
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(MusicPlayHelper.getInstance().getLocalProgressByStorageType(currentMusicFromSp.getStorageType()), currentMusicFromSp.getDuration(), 4, currentMusicFromSp.getStorageType()));
            } else {
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(0, 0, 4, 0));
            }
        }
        ObservableMap<Integer, Boolean> isCollectMusicMaps = MusicPlayHelper.getInstance().getIsCollectMusicMaps();
        if (currentMusicFromSp != null && currentMusicFromSp.isCollect()) {
            z = true;
        }
        isCollectMusicMaps.putIfAbsent(0, Boolean.valueOf(z));
        super.initData();
    }
}
