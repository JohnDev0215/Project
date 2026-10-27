package com.byd.music.fragment.localMusic;

import android.os.Environment;
import android.util.Log;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import com.blankj.utilcode.util.Utils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicListViewModel extends BaseViewModel {
    public BindingCommand<LocalMusicBean> listMusicLongClick = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.LocalMusicListViewModel.2
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            Log.d(LocalMusicListViewModel.this.TAG, "Alvin---long-call: ");
            LocalMusicListViewModel.this.longClickEvent.call();
        }
    });
    public ItemBinding<LocalMusicBean> localMusicBeanItemBinding;
    public ObservableList<LocalMusicBean> localMusicBeansList;
    public SingleLiveEvent longClickEvent;

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        ItemBinding<LocalMusicBean> itemBindingOf = ItemBinding.of(BR.localMusicBean, R.layout.item_localmusiclist);
        this.localMusicBeanItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        this.localMusicBeansList = new ObservableArrayList();
        getLoadingEvent().postValue(true);
        scanMusic(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath());
        this.longClickEvent = new SingleLiveEvent();
    }

    private void scanMusic(final String str) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.LocalMusicListViewModel.1
            @Override // java.lang.Runnable
            public void run() {
                LocalMusicDB.delete();
                LocalMusicDB.insertAll(LocalMusicUtils.queryMusic(Utils.getApp().getApplicationContext(), str));
                LocalMusicListViewModel.this.getLoadingEvent().postValue(false);
            }
        });
    }

    public void addListMusic() {
        this.localMusicBeansList.addAll(LocalMusicDB.getSongIdList());
    }
}
