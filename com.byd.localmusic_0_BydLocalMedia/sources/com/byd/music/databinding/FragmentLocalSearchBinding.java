package com.byd.music.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.R;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel;
import com.byd.music.view.FlowLayout;
import com.byd.music.view.LocalSearchView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentLocalSearchBinding extends ViewDataBinding {
    public final ShapeableImageView albumCoverSearch;
    public final ImageView btnClearHistory;
    public final FrameLayout containerListViews;
    public final ExpandableListView elArtistList;
    public final LocalSearchView etLocalSearch;
    public final FrameLayout flHolderMask;
    public final FrameLayout flTabContainer;
    public final FlowLayout historyGroup;
    public final ImageView iconSearchBack;
    public final ImageView ivLocalMusic;
    public final LinearLayout llSearchBar;

    @Bindable
    protected LocalMusicSearchViewModel mViewModel;
    public final RadioGroup rgLocalSearch;
    public final RadioButton rtnMusiclist;
    public final RadioButton rtnMusiclistAlbum;
    public final RadioButton rtnMusiclistSinger;
    public final RecyclerView rvAlbumList;
    public final RecyclerView rvLocalMusicList;
    public final TabItem tabAlbumSearch;
    public final TabItem tabArtistSearch;
    public final TabLayout tabLayoutLocalSearch;
    public final TabItem tabMusicSearch;

    public abstract void setViewModel(LocalMusicSearchViewModel localMusicSearchViewModel);

    protected FragmentLocalSearchBinding(Object obj, View view, int i, ShapeableImageView shapeableImageView, ImageView imageView, FrameLayout frameLayout, ExpandableListView expandableListView, LocalSearchView localSearchView, FrameLayout frameLayout2, FrameLayout frameLayout3, FlowLayout flowLayout, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, RadioGroup radioGroup, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RecyclerView recyclerView, RecyclerView recyclerView2, TabItem tabItem, TabItem tabItem2, TabLayout tabLayout, TabItem tabItem3) {
        super(obj, view, i);
        this.albumCoverSearch = shapeableImageView;
        this.btnClearHistory = imageView;
        this.containerListViews = frameLayout;
        this.elArtistList = expandableListView;
        this.etLocalSearch = localSearchView;
        this.flHolderMask = frameLayout2;
        this.flTabContainer = frameLayout3;
        this.historyGroup = flowLayout;
        this.iconSearchBack = imageView2;
        this.ivLocalMusic = imageView3;
        this.llSearchBar = linearLayout;
        this.rgLocalSearch = radioGroup;
        this.rtnMusiclist = radioButton;
        this.rtnMusiclistAlbum = radioButton2;
        this.rtnMusiclistSinger = radioButton3;
        this.rvAlbumList = recyclerView;
        this.rvLocalMusicList = recyclerView2;
        this.tabAlbumSearch = tabItem;
        this.tabArtistSearch = tabItem2;
        this.tabLayoutLocalSearch = tabLayout;
        this.tabMusicSearch = tabItem3;
    }

    public LocalMusicSearchViewModel getViewModel() {
        return this.mViewModel;
    }

    public static FragmentLocalSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLocalSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_search, viewGroup, z, obj);
    }

    public static FragmentLocalSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalSearchBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLocalSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_local_search, null, false, obj);
    }

    public static FragmentLocalSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLocalSearchBinding bind(View view, Object obj) {
        return (FragmentLocalSearchBinding) bind(obj, view, R.layout.fragment_local_search);
    }
}
