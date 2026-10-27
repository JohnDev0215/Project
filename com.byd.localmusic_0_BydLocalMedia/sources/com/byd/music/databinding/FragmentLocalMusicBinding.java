package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.ui.LocalMusicViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.HeaderExpandListview;
import com.byd.music.view.LoadingView;
import com.byd.music.view.SlideBar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLocalMusicBinding extends ViewDataBinding {
    public final ConstraintLayout clMusicInfo;
    public final ConstraintLayout clMusicInfoContainer;
    public final ConstraintLayout clMusicList;
    public final ConstraintLayout clMusicMain;
    public final ConstraintLayout clOperate;
    public final ConstraintLayout clToolsContainer;
    public final CollectLottieView collectLottiesView;
    public final HeaderExpandListview elArtistAlbumList;
    public final ImageView ivCancelSelect;
    public final ImageView ivCollect;
    public final ImageView ivDelete;
    public final ImageView ivLastMusic;
    public final ShapeableImageView ivMusicPic;
    public final ImageView ivNextMusic;
    public final ImageView ivNoContent;
    public final ImageView ivPlayMode;
    public final ImageView ivPlayMusic;
    public final ImageView ivSearch;
    public final ImageView ivSelectAll;
    public final ImageView ivVoice;
    public final LoadingView loadingView;

    @Bindable
    protected LocalMusicViewModel mViewModel;
    public final NestedScrollView nestedScrollView;
    public final ImageView noSdUsbIv;
    public final TextView noSdUsbTipsTv;
    public final ConstraintLayout rootContainer;
    public final RecyclerView rvLocalMusicList;
    public final RecyclerView rvLocalMusicListCollect;
    public final SeekBar seekBar;
    public final SlideBar slideBarLetter;
    public final TextView slideLetter;
    public final HorizontalScrollView sllView;
    public final TabItem tabAlbumMusic;
    public final TabItem tabAllMusic;
    public final TabItem tabCollectMusic;
    public final TabLayout tabLayout;
    public final TabItem tabSingerMusic;
    public final TextView tvCancelSelect;
    public final TextView tvDelete;
    public final TextView tvDeleteAllTips;
    public final TextView tvMusicName;
    public final TextView tvMusicTotaltime;
    public final TextView tvMusicZerotime;
    public final TextView tvNoContent;
    public final TextView tvSelectAll;
    public final TextView tvSingerName;

    public abstract void setViewModel(LocalMusicViewModel localMusicViewModel);

    protected FragmentLocalMusicBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, CollectLottieView collectLottieView, HeaderExpandListview headerExpandListview, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ShapeableImageView shapeableImageView, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, ImageView imageView9, ImageView imageView10, ImageView imageView11, LoadingView loadingView, NestedScrollView nestedScrollView, ImageView imageView12, TextView textView, ConstraintLayout constraintLayout7, RecyclerView recyclerView, RecyclerView recyclerView2, SeekBar seekBar, SlideBar slideBar, TextView textView2, HorizontalScrollView horizontalScrollView, TabItem tabItem, TabItem tabItem2, TabItem tabItem3, TabLayout tabLayout, TabItem tabItem4, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        super(obj, view, i);
        this.clMusicInfo = constraintLayout;
        this.clMusicInfoContainer = constraintLayout2;
        this.clMusicList = constraintLayout3;
        this.clMusicMain = constraintLayout4;
        this.clOperate = constraintLayout5;
        this.clToolsContainer = constraintLayout6;
        this.collectLottiesView = collectLottieView;
        this.elArtistAlbumList = headerExpandListview;
        this.ivCancelSelect = imageView;
        this.ivCollect = imageView2;
        this.ivDelete = imageView3;
        this.ivLastMusic = imageView4;
        this.ivMusicPic = shapeableImageView;
        this.ivNextMusic = imageView5;
        this.ivNoContent = imageView6;
        this.ivPlayMode = imageView7;
        this.ivPlayMusic = imageView8;
        this.ivSearch = imageView9;
        this.ivSelectAll = imageView10;
        this.ivVoice = imageView11;
        this.loadingView = loadingView;
        this.nestedScrollView = nestedScrollView;
        this.noSdUsbIv = imageView12;
        this.noSdUsbTipsTv = textView;
        this.rootContainer = constraintLayout7;
        this.rvLocalMusicList = recyclerView;
        this.rvLocalMusicListCollect = recyclerView2;
        this.seekBar = seekBar;
        this.slideBarLetter = slideBar;
        this.slideLetter = textView2;
        this.sllView = horizontalScrollView;
        this.tabAlbumMusic = tabItem;
        this.tabAllMusic = tabItem2;
        this.tabCollectMusic = tabItem3;
        this.tabLayout = tabLayout;
        this.tabSingerMusic = tabItem4;
        this.tvCancelSelect = textView3;
        this.tvDelete = textView4;
        this.tvDeleteAllTips = textView5;
        this.tvMusicName = textView6;
        this.tvMusicTotaltime = textView7;
        this.tvMusicZerotime = textView8;
        this.tvNoContent = textView9;
        this.tvSelectAll = textView10;
        this.tvSingerName = textView11;
    }

    public LocalMusicViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentLocalMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalMusicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLocalMusicBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_music, viewGroup, z, obj);
    }

    public static FragmentLocalMusicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalMusicBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLocalMusicBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_music, null, false, obj);
    }

    public static FragmentLocalMusicBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalMusicBinding bind(View view, Object obj) {
        return (FragmentLocalMusicBinding) bind(obj, view, R.layout.fragment_local_music);
    }
}
