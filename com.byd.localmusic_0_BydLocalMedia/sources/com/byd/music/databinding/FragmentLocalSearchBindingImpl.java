package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel;
import com.byd.music.view.FlowLayout;
import com.byd.music.view.LocalSearchView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentLocalSearchBindingImpl extends FragmentLocalSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final LinearLayout mboundView10;
    private final ImageView mboundView12;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ll_search_bar, 13);
        sViewsWithIds.put(R.id.et_local_search, 14);
        sViewsWithIds.put(R.id.flHolderMask, 15);
        sViewsWithIds.put(R.id.tab_music_search, 16);
        sViewsWithIds.put(R.id.tab_artist_search, 17);
        sViewsWithIds.put(R.id.tab_album_search, 18);
        sViewsWithIds.put(R.id.history_group, 19);
    }

    public FragmentLocalSearchBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private FragmentLocalSearchBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 13, null, (ImageView) objArr[11], null, (ExpandableListView) objArr[9], (LocalSearchView) objArr[14], (FrameLayout) objArr[15], null, (FlowLayout) objArr[19], (ImageView) objArr[1], null, (LinearLayout) objArr[13], (RadioGroup) objArr[3], (RadioButton) objArr[4], (RadioButton) objArr[6], (RadioButton) objArr[5], (RecyclerView) objArr[8], (RecyclerView) objArr[7], (TabItem) objArr[18], (TabItem) objArr[17], (TabLayout) objArr[2], (TabItem) objArr[16]);
        this.mDirtyFlags = -1L;
        this.btnClearHistory.setTag(null);
        this.elArtistList.setTag(null);
        this.iconSearchBack.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[10];
        this.mboundView10 = linearLayout;
        linearLayout.setTag(null);
        ImageView imageView = (ImageView) objArr[12];
        this.mboundView12 = imageView;
        imageView.setTag(null);
        this.rgLocalSearch.setTag(null);
        this.rtnMusiclist.setTag(null);
        this.rtnMusiclistAlbum.setTag(null);
        this.rtnMusiclistSinger.setTag(null);
        this.rvAlbumList.setTag(null);
        this.rvLocalMusicList.setTag(null);
        this.tabLayoutLocalSearch.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16384L;
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
        if (BR.viewModel != i) {
            return false;
        }
        setViewModel((LocalMusicSearchViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentLocalSearchBinding
    public void setViewModel(LocalMusicSearchViewModel localMusicSearchViewModel) {
        this.mViewModel = localMusicSearchViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelIsExpandableListVisible((ObservableBoolean) obj, i2);
            case 1:
                return onChangeViewModelMatchMusicNum((ObservableInt) obj, i2);
            case 2:
                return onChangeViewModelAlbumSpanHDistance((ObservableInt) obj, i2);
            case 3:
                return onChangeViewModelLocalSearchMusicBeansList((ObservableList) obj, i2);
            case 4:
                return onChangeViewModelIsAlbumListVisible((ObservableBoolean) obj, i2);
            case 5:
                return onChangeViewModelIsMusicListVisible((ObservableBoolean) obj, i2);
            case 6:
                return onChangeViewModelMatchArtistNum((ObservableInt) obj, i2);
            case 7:
                return onChangeViewModelAlbumSpanSize((ObservableInt) obj, i2);
            case 8:
                return onChangeViewModelIsSearchHistoryVisible((ObservableBoolean) obj, i2);
            case 9:
                return onChangeViewModelLocalSearchAlbumBeansList((ObservableList) obj, i2);
            case 10:
                return onChangeViewModelIsRadioGroupVisible((ObservableBoolean) obj, i2);
            case 11:
                return onChangeViewModelAlbumSpanVDistance((ObservableInt) obj, i2);
            case 12:
                return onChangeViewModelMatchAlbumNum((ObservableInt) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIsExpandableListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMatchMusicNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelAlbumSpanHDistance(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSearchMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelIsMusicListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelMatchArtistNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelAlbumSpanSize(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelIsSearchHistoryVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSearchAlbumBeansList(ObservableList<AlbumBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelIsRadioGroupVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeViewModelAlbumSpanVDistance(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        return true;
    }

    private boolean onChangeViewModelMatchAlbumNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01d2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x012d  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 1208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalSearchBindingImpl.executeBindings():void");
    }
}
