package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.LocalMusicListViewModel;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.musicPlayingSlideView.MusicPlayingSlideView;

/* JADX INFO: loaded from: classes5.dex */
public class ItemLocalmusiclistBindingImpl extends ItemLocalmusiclistBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final MusicPlayingSlideView mboundView4;

    public ItemLocalmusiclistBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private ItemLocalmusiclistBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (ImageView) objArr[7], (ImageView) objArr[6], (ImageView) objArr[5], (TextView) objArr[3], (TextView) objArr[1], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.ivAdd.setTag(null);
        this.ivDownload.setTag(null);
        this.ivLike.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        MusicPlayingSlideView musicPlayingSlideView = (MusicPlayingSlideView) objArr[4];
        this.mboundView4 = musicPlayingSlideView;
        musicPlayingSlideView.setTag(null);
        this.tvAnnouncer.setTag(null);
        this.tvIndex.setTag(null);
        this.tvSongName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (BR.localMusicBean == i) {
            setLocalMusicBean((LocalMusicBean) obj);
        } else {
            if (BR.viewModel != i) {
                return false;
            }
            setViewModel((LocalMusicListViewModel) obj);
        }
        return true;
    }

    @Override // com.byd.music.databinding.ItemLocalmusiclistBinding
    public void setLocalMusicBean(LocalMusicBean localMusicBean) {
        this.mLocalMusicBean = localMusicBean;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.localMusicBean);
        super.requestRebind();
    }

    @Override // com.byd.music.databinding.ItemLocalmusiclistBinding
    public void setViewModel(LocalMusicListViewModel localMusicListViewModel) {
        this.mViewModel = localMusicListViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewModelLocalMusicBeansList((ObservableList) obj, i2);
    }

    private boolean onChangeViewModelLocalMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String strValueOf;
        BindingCommand<LocalMusicBean> bindingCommand;
        String str;
        String title;
        String artist;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        LocalMusicBean localMusicBean = this.mLocalMusicBean;
        LocalMusicListViewModel localMusicListViewModel = this.mViewModel;
        long j2 = 15 & j;
        if (j2 != 0) {
            if ((j & 10) == 0 || localMusicBean == null) {
                title = null;
                artist = null;
            } else {
                title = localMusicBean.getTitle();
                artist = localMusicBean.getArtist();
            }
            bindingCommand = ((j & 12) == 0 || localMusicListViewModel == null) ? null : localMusicListViewModel.listMusicLongClick;
            ObservableList observableList = localMusicListViewModel != null ? localMusicListViewModel.localMusicBeansList : null;
            updateRegistration(0, observableList);
            strValueOf = String.valueOf((observableList != null ? observableList.indexOf(localMusicBean) : 0) + 1);
            str = artist;
        } else {
            strValueOf = null;
            bindingCommand = null;
            str = null;
            title = null;
        }
        long j3 = j & 8;
        int i = j3 != 0 ? R.drawable.icon_dark_media_list_download_48 : 0;
        if ((10 & j) != 0) {
            BindingCommand bindingCommand2 = (BindingCommand) null;
            BindingUtils.onClickCommand(this.ivAdd, bindingCommand2, false, localMusicBean, false);
            BindingUtils.onClickCommand(this.ivDownload, bindingCommand2, false, localMusicBean, false);
            BindingUtils.onClickCommand(this.ivLike, bindingCommand2, false, localMusicBean, false);
            BindingUtils.onClickCommand(this.mboundView0, bindingCommand2, false, localMusicBean, false);
            String str2 = (String) null;
            BindingUtils.setKeyWordColor(this.tvAnnouncer, str, str2);
            BindingUtils.setKeyWordColor(this.tvSongName, title, str2);
        }
        if (j3 != 0) {
            BindingUtils.setImageRes(this.ivDownload, i);
            BindingUtils.setMusicSlidePlaying(this.mboundView4, true);
            BindingUtils.setVisiable(this.mboundView4, true, false, 0, 0);
        }
        if ((j & 12) != 0) {
            BindingUtils.onLongClickCommand(this.mboundView0, bindingCommand, null);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.tvIndex, strValueOf);
        }
    }
}
