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
public class FragmentLocalSearchBindingSw720dpImpl extends FragmentLocalSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final ConstraintLayout mboundView0;
    private final ShapeableImageView mboundView11;
    private final LinearLayout mboundView12;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.iv_local_music, 13);
        sViewsWithIds.put(R.id.ll_search_bar, 14);
        sViewsWithIds.put(R.id.et_local_search, 15);
        sViewsWithIds.put(R.id.fl_tab_container, 16);
        sViewsWithIds.put(R.id.tab_music_search, 17);
        sViewsWithIds.put(R.id.tab_artist_search, 18);
        sViewsWithIds.put(R.id.tab_album_search, 19);
        sViewsWithIds.put(R.id.rg_local_search, 20);
        sViewsWithIds.put(R.id.container_list_views, 21);
        sViewsWithIds.put(R.id.flHolderMask, 22);
        sViewsWithIds.put(R.id.history_group, 23);
    }

    public FragmentLocalSearchBindingSw720dpImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private FragmentLocalSearchBindingSw720dpImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 16, null, (ImageView) objArr[10], (FrameLayout) objArr[21], (ExpandableListView) objArr[7], (LocalSearchView) objArr[15], (FrameLayout) objArr[22], (FrameLayout) objArr[16], (FlowLayout) objArr[23], (ImageView) objArr[1], (ImageView) objArr[13], (LinearLayout) objArr[14], (RadioGroup) objArr[20], (RadioButton) objArr[3], (RadioButton) objArr[5], (RadioButton) objArr[4], (RecyclerView) objArr[8], (RecyclerView) objArr[6], (TabItem) objArr[19], (TabItem) objArr[18], (TabLayout) objArr[2], (TabItem) objArr[17]);
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.btnClearHistory.setTag(null);
        this.elArtistList.setTag(null);
        this.iconSearchBack.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        ShapeableImageView shapeableImageView = (ShapeableImageView) objArr[11];
        this.mboundView11 = shapeableImageView;
        shapeableImageView.setTag(null);
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
    /* JADX WARN: Removed duplicated region for block: B:189:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x059c  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x05bf  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x072e  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x073c  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0753  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x075d  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x079b  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x07a6  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x07d0  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x07e0  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x0806  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x0819  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0848  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x086d  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x0886  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x088e  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x08a7  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:580:0x08ce  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x08ef  */
    /* JADX WARN: Removed duplicated region for block: B:629:0x0978  */
    /* JADX WARN: Removed duplicated region for block: B:634:0x098a  */
    /* JADX WARN: Removed duplicated region for block: B:639:0x0996  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x099d  */
    /* JADX WARN: Removed duplicated region for block: B:649:0x09b2  */
    /* JADX WARN: Removed duplicated region for block: B:656:0x09cd  */
    /* JADX WARN: Removed duplicated region for block: B:658:0x09d8  */
    /* JADX WARN: Removed duplicated region for block: B:670:0x0a03  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x0a14  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0a38  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x0a44  */
    /* JADX WARN: Removed duplicated region for block: B:694:0x0a5f  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x0a69  */
    /* JADX WARN: Removed duplicated region for block: B:705:0x0a7b  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0a8c  */
    /* JADX WARN: Removed duplicated region for block: B:713:0x0a93  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x0aac  */
    /* JADX WARN: Removed duplicated region for block: B:724:0x0ab6  */
    /* JADX WARN: Removed duplicated region for block: B:726:0x0aba  */
    /* JADX WARN: Removed duplicated region for block: B:731:0x0ac7  */
    /* JADX WARN: Removed duplicated region for block: B:734:0x0ad3  */
    /* JADX WARN: Removed duplicated region for block: B:777:0x0b5e  */
    /* JADX WARN: Removed duplicated region for block: B:780:0x0b65  */
    /* JADX WARN: Removed duplicated region for block: B:788:0x0b78  */
    /* JADX WARN: Removed duplicated region for block: B:793:0x0b87  */
    /* JADX WARN: Removed duplicated region for block: B:796:0x0b8e  */
    /* JADX WARN: Removed duplicated region for block: B:804:0x0ba3  */
    /* JADX WARN: Removed duplicated region for block: B:811:0x0bc1  */
    /* JADX WARN: Removed duplicated region for block: B:813:0x0bc7  */
    /* JADX WARN: Removed duplicated region for block: B:823:0x0be9 A[PHI: r47
  0x0be9: PHI (r47v13 androidx.databinding.ObservableList) = (r47v12 androidx.databinding.ObservableList), (r47v15 androidx.databinding.ObservableList) binds: [B:812:0x0bc5, B:822:0x0be7] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:826:0x0bf2  */
    /* JADX WARN: Removed duplicated region for block: B:838:0x0c15  */
    /* JADX WARN: Removed duplicated region for block: B:841:0x0c24  */
    /* JADX WARN: Removed duplicated region for block: B:843:0x0c2c  */
    /* JADX WARN: Removed duplicated region for block: B:844:0x0c2e  */
    /* JADX WARN: Removed duplicated region for block: B:845:0x0c31  */
    /* JADX WARN: Removed duplicated region for block: B:848:0x0c3a  */
    /* JADX WARN: Removed duplicated region for block: B:854:0x0c4c  */
    /* JADX WARN: Removed duplicated region for block: B:857:0x0c53  */
    /* JADX WARN: Removed duplicated region for block: B:861:0x0c61  */
    /* JADX WARN: Removed duplicated region for block: B:864:0x0c6c  */
    /* JADX WARN: Removed duplicated region for block: B:870:0x0c84  */
    /* JADX WARN: Removed duplicated region for block: B:875:0x0c90  */
    /* JADX WARN: Removed duplicated region for block: B:878:0x0c97  */
    /* JADX WARN: Removed duplicated region for block: B:880:0x0c9c  */
    /* JADX WARN: Removed duplicated region for block: B:883:0x0ca5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:885:0x0ca8  */
    /* JADX WARN: Removed duplicated region for block: B:888:0x0cb2  */
    /* JADX WARN: Removed duplicated region for block: B:889:0x0cc1  */
    /* JADX WARN: Removed duplicated region for block: B:891:0x0cc5  */
    /* JADX WARN: Removed duplicated region for block: B:893:0x0ccc  */
    /* JADX WARN: Removed duplicated region for block: B:896:0x0cd9  */
    /* JADX WARN: Removed duplicated region for block: B:899:0x0ce6  */
    /* JADX WARN: Removed duplicated region for block: B:902:0x0cf3  */
    /* JADX WARN: Removed duplicated region for block: B:905:0x0d00  */
    /* JADX WARN: Removed duplicated region for block: B:908:0x0d0d  */
    /* JADX WARN: Removed duplicated region for block: B:911:0x0d1a  */
    /* JADX WARN: Removed duplicated region for block: B:914:0x0d27  */
    /* JADX WARN: Removed duplicated region for block: B:917:0x0d36  */
    /* JADX WARN: Removed duplicated region for block: B:919:0x0d52  */
    /* JADX WARN: Removed duplicated region for block: B:922:0x0d63  */
    /* JADX WARN: Removed duplicated region for block: B:925:0x0d83  */
    /* JADX WARN: Removed duplicated region for block: B:932:? A[RETURN, SYNTHETIC] */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 3470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalSearchBindingSw720dpImpl.executeBindings():void");
    }
}
