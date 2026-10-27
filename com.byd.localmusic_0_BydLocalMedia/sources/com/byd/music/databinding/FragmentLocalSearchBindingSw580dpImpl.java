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
public class FragmentLocalSearchBindingSw580dpImpl extends FragmentLocalSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final LinearLayout mboundView11;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_local_music, 12);
        sViewsWithIds.put(R.id.ll_search_bar, 13);
        sViewsWithIds.put(R.id.et_local_search, 14);
        sViewsWithIds.put(R.id.flHolderMask, 15);
        sViewsWithIds.put(R.id.fl_tab_container, 16);
        sViewsWithIds.put(R.id.tab_music_search, 17);
        sViewsWithIds.put(R.id.tab_artist_search, 18);
        sViewsWithIds.put(R.id.tab_album_search, 19);
        sViewsWithIds.put(R.id.rg_local_search, 20);
        sViewsWithIds.put(R.id.history_group, 21);
    }

    public FragmentLocalSearchBindingSw580dpImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 22, sIncludes, sViewsWithIds));
    }

    private FragmentLocalSearchBindingSw580dpImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 14, null, (ImageView) objArr[10], null, (ExpandableListView) objArr[8], (LocalSearchView) objArr[14], (FrameLayout) objArr[15], (FrameLayout) objArr[16], (FlowLayout) objArr[21], (ImageView) objArr[1], (ImageView) objArr[12], (LinearLayout) objArr[13], (RadioGroup) objArr[20], (RadioButton) objArr[3], (RadioButton) objArr[5], (RadioButton) objArr[4], (RecyclerView) objArr[7], (RecyclerView) objArr[6], (TabItem) objArr[19], (TabItem) objArr[18], (TabLayout) objArr[2], (TabItem) objArr[17]);
        this.mDirtyFlags = -1L;
        this.btnClearHistory.setTag(null);
        this.elArtistList.setTag(null);
        this.iconSearchBack.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[11];
        this.mboundView11 = linearLayout;
        linearLayout.setTag(null);
        LinearLayout linearLayout2 = (LinearLayout) objArr[9];
        this.mboundView9 = linearLayout2;
        linearLayout2.setTag(null);
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
            this.mDirtyFlags = 32768L;
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
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelLocalSearchMusicBeansList((ObservableList) obj, i2);
            case 1:
                return onChangeViewModelMatchArtistNum((ObservableInt) obj, i2);
            case 2:
                return onChangeViewModelIsMusicListChecked((ObservableBoolean) obj, i2);
            case 3:
                return onChangeViewModelMatchAlbumNum((ObservableInt) obj, i2);
            case 4:
                return onChangeViewModelIsArtistListChecked((ObservableBoolean) obj, i2);
            case 5:
                return onChangeViewModelIsExpandableListVisible((ObservableBoolean) obj, i2);
            case 6:
                return onChangeViewModelMatchMusicNum((ObservableInt) obj, i2);
            case 7:
                return onChangeViewModelIsNoData((ObservableBoolean) obj, i2);
            case 8:
                return onChangeViewModelIsAlbumDetailShow((ObservableBoolean) obj, i2);
            case 9:
                return onChangeViewModelIsAlbumListVisible((ObservableBoolean) obj, i2);
            case 10:
                return onChangeViewModelIsSearchHistoryVisible((ObservableBoolean) obj, i2);
            case 11:
                return onChangeViewModelLocalSearchAlbumBeansList((ObservableList) obj, i2);
            case 12:
                return onChangeViewModelIsAlbumListChecked((ObservableBoolean) obj, i2);
            case 13:
                return onChangeViewModelIsRadioGroupVisible((ObservableBoolean) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelLocalSearchMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelMatchArtistNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelIsMusicListChecked(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelMatchAlbumNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelIsArtistListChecked(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelIsExpandableListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelMatchMusicNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelIsNoData(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumDetailShow(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelIsSearchHistoryVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSearchAlbumBeansList(ObservableList<AlbumBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumListChecked(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        return true;
    }

    private boolean onChangeViewModelIsRadioGroupVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0681  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0692  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x069c  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x06aa  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x06b8  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0741 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x074c  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x077b  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0788  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x0803  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0810 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:528:0x0815  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x081c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:533:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0828  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0839  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0847  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0861  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x087d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0881  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0888 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:568:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:571:0x0893  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x089a  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x08a4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:579:0x08a7  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:583:0x08c2  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x08cc  */
    /* JADX WARN: Removed duplicated region for block: B:588:0x08d3  */
    /* JADX WARN: Removed duplicated region for block: B:591:0x08e2  */
    /* JADX WARN: Removed duplicated region for block: B:594:0x08ef  */
    /* JADX WARN: Removed duplicated region for block: B:597:0x08fa  */
    /* JADX WARN: Removed duplicated region for block: B:600:0x0905  */
    /* JADX WARN: Removed duplicated region for block: B:603:0x0912  */
    /* JADX WARN: Removed duplicated region for block: B:606:0x091f  */
    /* JADX WARN: Removed duplicated region for block: B:608:0x093b  */
    /* JADX WARN: Removed duplicated region for block: B:611:0x094c  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x096c  */
    /* JADX WARN: Removed duplicated region for block: B:621:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 2423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalSearchBindingSw580dpImpl.executeBindings():void");
    }
}
