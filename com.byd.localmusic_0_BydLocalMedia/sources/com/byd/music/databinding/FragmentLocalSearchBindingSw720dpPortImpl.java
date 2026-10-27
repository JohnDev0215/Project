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
import androidx.databinding.ObservableField;
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
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentLocalSearchBindingSw720dpPortImpl extends FragmentLocalSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final ConstraintLayout mboundView0;
    private final LinearLayout mboundView12;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_local_music, 13);
        sViewsWithIds.put(R.id.ll_search_bar, 14);
        sViewsWithIds.put(R.id.et_local_search, 15);
        sViewsWithIds.put(R.id.flHolderMask, 16);
        sViewsWithIds.put(R.id.fl_tab_container, 17);
        sViewsWithIds.put(R.id.tab_music_search, 18);
        sViewsWithIds.put(R.id.tab_artist_search, 19);
        sViewsWithIds.put(R.id.tab_album_search, 20);
        sViewsWithIds.put(R.id.rg_local_search, 21);
        sViewsWithIds.put(R.id.history_group, 22);
    }

    public FragmentLocalSearchBindingSw720dpPortImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 23, sIncludes, sViewsWithIds));
    }

    private FragmentLocalSearchBindingSw720dpPortImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 16, (ShapeableImageView) objArr[11], (ImageView) objArr[10], null, (ExpandableListView) objArr[8], (LocalSearchView) objArr[15], (FrameLayout) objArr[16], (FrameLayout) objArr[17], (FlowLayout) objArr[22], (ImageView) objArr[1], (ImageView) objArr[13], (LinearLayout) objArr[14], (RadioGroup) objArr[21], (RadioButton) objArr[3], (RadioButton) objArr[5], (RadioButton) objArr[4], (RecyclerView) objArr[7], (RecyclerView) objArr[6], (TabItem) objArr[20], (TabItem) objArr[19], (TabLayout) objArr[2], (TabItem) objArr[18]);
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.albumCoverSearch.setTag(null);
        this.btnClearHistory.setTag(null);
        this.elArtistList.setTag(null);
        this.iconSearchBack.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[12];
        this.mboundView12 = linearLayout;
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
            this.mDirtyFlags = 131072L;
            this.mDirtyFlags_1 = 0L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0 && this.mDirtyFlags_1 == 0) {
                return false;
            }
            return true;
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
            this.mDirtyFlags |= 65536;
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
                return onChangeViewModelIsMusicListVisible((ObservableBoolean) obj, i2);
            case 2:
                return onChangeViewModelMatchArtistNum((ObservableInt) obj, i2);
            case 3:
                return onChangeViewModelIsMusicListChecked((ObservableBoolean) obj, i2);
            case 4:
                return onChangeViewModelMatchAlbumNum((ObservableInt) obj, i2);
            case 5:
                return onChangeViewModelIsArtistListChecked((ObservableBoolean) obj, i2);
            case 6:
                return onChangeViewModelIsExpandableListVisible((ObservableBoolean) obj, i2);
            case 7:
                return onChangeViewModelMatchMusicNum((ObservableInt) obj, i2);
            case 8:
                return onChangeViewModelIsNoData((ObservableBoolean) obj, i2);
            case 9:
                return onChangeViewModelIsAlbumDetailShow((ObservableBoolean) obj, i2);
            case 10:
                return onChangeViewModelObservableUri((ObservableField) obj, i2);
            case 11:
                return onChangeViewModelIsAlbumListVisible((ObservableBoolean) obj, i2);
            case 12:
                return onChangeViewModelIsSearchHistoryVisible((ObservableBoolean) obj, i2);
            case 13:
                return onChangeViewModelLocalSearchAlbumBeansList((ObservableList) obj, i2);
            case 14:
                return onChangeViewModelIsAlbumListChecked((ObservableBoolean) obj, i2);
            case 15:
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

    private boolean onChangeViewModelIsMusicListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelMatchArtistNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelIsMusicListChecked(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelMatchAlbumNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelIsArtistListChecked(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelIsExpandableListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelMatchMusicNum(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelIsNoData(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumDetailShow(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelObservableUri(ObservableField<String> observableField, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumListVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        return true;
    }

    private boolean onChangeViewModelIsSearchHistoryVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSearchAlbumBeansList(ObservableList<AlbumBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        return true;
    }

    private boolean onChangeViewModelIsAlbumListChecked(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        return true;
    }

    private boolean onChangeViewModelIsRadioGroupVisible(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32768;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x059d  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x072f  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x073d  */
    /* JADX WARN: Removed duplicated region for block: B:471:0x0754  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0771  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x07a7  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x07d1  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x07e1  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x0807  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x081a  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x083c  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0849  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x086e  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x0887  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x088f  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x08a8  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x08b2  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x08c5  */
    /* JADX WARN: Removed duplicated region for block: B:579:0x08cf  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x08e1  */
    /* JADX WARN: Removed duplicated region for block: B:589:0x08f0  */
    /* JADX WARN: Removed duplicated region for block: B:628:0x0979  */
    /* JADX WARN: Removed duplicated region for block: B:633:0x098b  */
    /* JADX WARN: Removed duplicated region for block: B:638:0x0997  */
    /* JADX WARN: Removed duplicated region for block: B:641:0x099e  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x09b3  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x09ce  */
    /* JADX WARN: Removed duplicated region for block: B:657:0x09d9  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x0a04  */
    /* JADX WARN: Removed duplicated region for block: B:672:0x0a15  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x0a39  */
    /* JADX WARN: Removed duplicated region for block: B:686:0x0a45  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x0a60  */
    /* JADX WARN: Removed duplicated region for block: B:696:0x0a6a  */
    /* JADX WARN: Removed duplicated region for block: B:704:0x0a7c  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x0a8d  */
    /* JADX WARN: Removed duplicated region for block: B:712:0x0a94  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x0aad  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x0ab7  */
    /* JADX WARN: Removed duplicated region for block: B:725:0x0abb  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x0ac8  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x0ad4  */
    /* JADX WARN: Removed duplicated region for block: B:776:0x0b5f  */
    /* JADX WARN: Removed duplicated region for block: B:779:0x0b66  */
    /* JADX WARN: Removed duplicated region for block: B:787:0x0b79  */
    /* JADX WARN: Removed duplicated region for block: B:792:0x0b88  */
    /* JADX WARN: Removed duplicated region for block: B:795:0x0b8f  */
    /* JADX WARN: Removed duplicated region for block: B:803:0x0ba4  */
    /* JADX WARN: Removed duplicated region for block: B:810:0x0bc2  */
    /* JADX WARN: Removed duplicated region for block: B:812:0x0bc8  */
    /* JADX WARN: Removed duplicated region for block: B:822:0x0bea A[PHI: r47
  0x0bea: PHI (r47v13 androidx.databinding.ObservableList) = (r47v12 androidx.databinding.ObservableList), (r47v15 androidx.databinding.ObservableList) binds: [B:811:0x0bc6, B:821:0x0be8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:825:0x0bf3  */
    /* JADX WARN: Removed duplicated region for block: B:837:0x0c16  */
    /* JADX WARN: Removed duplicated region for block: B:840:0x0c25  */
    /* JADX WARN: Removed duplicated region for block: B:842:0x0c2d  */
    /* JADX WARN: Removed duplicated region for block: B:843:0x0c2f  */
    /* JADX WARN: Removed duplicated region for block: B:844:0x0c32  */
    /* JADX WARN: Removed duplicated region for block: B:847:0x0c3b  */
    /* JADX WARN: Removed duplicated region for block: B:853:0x0c4d  */
    /* JADX WARN: Removed duplicated region for block: B:856:0x0c54  */
    /* JADX WARN: Removed duplicated region for block: B:860:0x0c62  */
    /* JADX WARN: Removed duplicated region for block: B:863:0x0c6d  */
    /* JADX WARN: Removed duplicated region for block: B:869:0x0c85  */
    /* JADX WARN: Removed duplicated region for block: B:874:0x0c91  */
    /* JADX WARN: Removed duplicated region for block: B:877:0x0c98  */
    /* JADX WARN: Removed duplicated region for block: B:879:0x0c9d  */
    /* JADX WARN: Removed duplicated region for block: B:882:0x0ca6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:884:0x0ca9  */
    /* JADX WARN: Removed duplicated region for block: B:886:0x0cac  */
    /* JADX WARN: Removed duplicated region for block: B:889:0x0cb9  */
    /* JADX WARN: Removed duplicated region for block: B:892:0x0cc6  */
    /* JADX WARN: Removed duplicated region for block: B:894:0x0cd4  */
    /* JADX WARN: Removed duplicated region for block: B:897:0x0ce3  */
    /* JADX WARN: Removed duplicated region for block: B:900:0x0cf0  */
    /* JADX WARN: Removed duplicated region for block: B:903:0x0cfd  */
    /* JADX WARN: Removed duplicated region for block: B:906:0x0d0a  */
    /* JADX WARN: Removed duplicated region for block: B:909:0x0d17  */
    /* JADX WARN: Removed duplicated region for block: B:912:0x0d24  */
    /* JADX WARN: Removed duplicated region for block: B:915:0x0d33  */
    /* JADX WARN: Removed duplicated region for block: B:917:0x0d4f  */
    /* JADX WARN: Removed duplicated region for block: B:920:0x0d60  */
    /* JADX WARN: Removed duplicated region for block: B:923:0x0d80  */
    /* JADX WARN: Removed duplicated region for block: B:930:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 3467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalSearchBindingSw720dpPortImpl.executeBindings():void");
    }
}
