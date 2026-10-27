package com.byd.music.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableMap;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.fragment.localMusic.ui.LocalUsbViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.HeaderExpandListview;
import com.byd.music.view.LoadingView;
import com.byd.music.view.SlideBar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentLocalUsbBindingImpl extends FragmentLocalUsbBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.nestedScrollView, 33);
        sViewsWithIds.put(R.id.rootContainer, 34);
        sViewsWithIds.put(R.id.collectLottiesView, 35);
        sViewsWithIds.put(R.id.cl_music_info, 36);
        sViewsWithIds.put(R.id.cl_music_list, 37);
        sViewsWithIds.put(R.id.tv_select_all, 38);
        sViewsWithIds.put(R.id.tab_all_music, 39);
        sViewsWithIds.put(R.id.tab_collect_music, 40);
        sViewsWithIds.put(R.id.tab_singer_music, 41);
        sViewsWithIds.put(R.id.tab_album_music, 42);
        sViewsWithIds.put(R.id.el_artist_album_list, 43);
        sViewsWithIds.put(R.id.slide_letter, 44);
        sViewsWithIds.put(R.id.loading_view, 45);
    }

    public FragmentLocalUsbBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 46, sIncludes, sViewsWithIds));
    }

    private FragmentLocalUsbBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 17, (ConstraintLayout) objArr[36], (ConstraintLayout) objArr[4], (ConstraintLayout) objArr[37], (ConstraintLayout) objArr[16], (ConstraintLayout) objArr[18], (ConstraintLayout) objArr[1], (CollectLottieView) objArr[35], (HeaderExpandListview) objArr[43], (ImageView) objArr[23], (ImageView) objArr[5], (ImageView) objArr[21], (ImageView) objArr[6], (ShapeableImageView) objArr[15], (ImageView) objArr[8], (ImageView) objArr[28], (ImageView) objArr[9], (ImageView) objArr[7], (ImageView) objArr[2], (ImageView) objArr[20], (ImageView) objArr[3], (LoadingView) objArr[45], (NestedScrollView) objArr[33], (ImageView) objArr[31], (TextView) objArr[32], (ConstraintLayout) objArr[34], (RecyclerView) objArr[26], (RecyclerView) objArr[27], (SeekBar) objArr[10], (SlideBar) objArr[30], (TextView) objArr[44], (HorizontalScrollView) objArr[17], (TabItem) objArr[42], (TabItem) objArr[39], (TabItem) objArr[40], (TabLayout) objArr[25], (TabItem) objArr[41], (TextView) objArr[24], (TextView) objArr[22], (TextView) objArr[19], (TextView) objArr[13], (TextView) objArr[12], (TextView) objArr[11], (TextView) objArr[29], (TextView) objArr[38], (TextView) objArr[14]);
        this.mDirtyFlags = -1L;
        this.mDirtyFlags_1 = -1L;
        this.clMusicInfoContainer.setTag(null);
        this.clMusicMain.setTag(null);
        this.clOperate.setTag(null);
        this.clToolsContainer.setTag(null);
        this.ivCancelSelect.setTag(null);
        this.ivCollect.setTag(null);
        this.ivDelete.setTag(null);
        this.ivLastMusic.setTag(null);
        this.ivMusicPic.setTag(null);
        this.ivNextMusic.setTag(null);
        this.ivNoContent.setTag(null);
        this.ivPlayMode.setTag(null);
        this.ivPlayMusic.setTag(null);
        this.ivSearch.setTag(null);
        this.ivSelectAll.setTag(null);
        this.ivVoice.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.noSdUsbIv.setTag(null);
        this.noSdUsbTipsTv.setTag(null);
        this.rvLocalMusicList.setTag(null);
        this.rvLocalMusicListCollect.setTag(null);
        this.seekBar.setTag(null);
        this.slideBarLetter.setTag(null);
        this.sllView.setTag(null);
        this.tabLayout.setTag(null);
        this.tvCancelSelect.setTag(null);
        this.tvDelete.setTag(null);
        this.tvDeleteAllTips.setTag(null);
        this.tvMusicName.setTag(null);
        this.tvMusicTotaltime.setTag(null);
        this.tvMusicZerotime.setTag(null);
        this.tvNoContent.setTag(null);
        this.tvSingerName.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 262144L;
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
        setViewModel((LocalUsbViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentLocalUsbBinding
    public void setViewModel(LocalUsbViewModel localUsbViewModel) {
        this.mViewModel = localUsbViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 131072;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelIfSeekbarDragging((ObservableBoolean) obj, i2);
            case 1:
                return onChangeViewModelLocalSdUsbMusicBeansList((ObservableList) obj, i2);
            case 2:
                return onChangeMusicPlayHelperGetInstanceCurrentPlayMusicStateMaps((ObservableMap) obj, i2);
            case 3:
                return onChangeMusicPlayHelperGetInstancePlayModeMaps((ObservableMap) obj, i2);
            case 4:
                return onChangeViewModelIsShowTabUI((ObservableBoolean) obj, i2);
            case 5:
                return onChangeViewModelIsSDTag((ObservableBoolean) obj, i2);
            case 6:
                return onChangeMusicPlayHelperGetInstanceIsPlayingMaps((ObservableMap) obj, i2);
            case 7:
                return onChangeViewModelIsShowOperateUi((ObservableBoolean) obj, i2);
            case 8:
                return onChangeViewModelLocalMusicListCount((ObservableInt) obj, i2);
            case 9:
                return onChangeViewModelIsRight((ObservableBoolean) obj, i2);
            case 10:
                return onChangeMusicPlayHelperGetInstanceIsCollectMusicMaps((ObservableMap) obj, i2);
            case 11:
                return onChangeMusicPlayHelperGetInstanceCurrentLocalMusicMaps((ObservableMap) obj, i2);
            case 12:
                return onChangeViewModelIsShowPlayUI((ObservableBoolean) obj, i2);
            case 13:
                return onChangeViewModelCollectLocalSdUsbMusicBeansList((ObservableList) obj, i2);
            case 14:
                return onChangeViewModelIsSelectAll((ObservableBoolean) obj, i2);
            case 15:
                return onChangeViewModelIsLoading((ObservableBoolean) obj, i2);
            case 16:
                return onChangeViewModelLocalSdUsbMusicDelList((ObservableList) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelIfSeekbarDragging(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSdUsbMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceCurrentPlayMusicStateMaps(ObservableMap<Integer, LocalMusicBean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstancePlayModeMaps(ObservableMap<Integer, Integer> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelIsShowTabUI(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelIsSDTag(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceIsPlayingMaps(ObservableMap<Integer, Boolean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelIsShowOperateUi(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelLocalMusicListCount(ObservableInt observableInt, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelIsRight(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceIsCollectMusicMaps(ObservableMap<Integer, Boolean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1024;
        }
        return true;
    }

    private boolean onChangeMusicPlayHelperGetInstanceCurrentLocalMusicMaps(ObservableMap<Integer, LocalMusicBean> observableMap, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2048;
        }
        return true;
    }

    private boolean onChangeViewModelIsShowPlayUI(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4096;
        }
        return true;
    }

    private boolean onChangeViewModelCollectLocalSdUsbMusicBeansList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        return true;
    }

    private boolean onChangeViewModelIsSelectAll(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        return true;
    }

    private boolean onChangeViewModelIsLoading(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32768;
        }
        return true;
    }

    private boolean onChangeViewModelLocalSdUsbMusicDelList(ObservableList<LocalMusicBean> observableList, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 65536;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0657  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x065e  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0668  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x0670  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0672  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x068a  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x069b  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x06a9  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x06bc  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x06c6  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x06d7  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x06e4  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x070f  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0718  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0724  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0727  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0756  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0767  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x077a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x078c  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x07ad  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x07d4  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x07e4  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x07f7  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0802  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x0815  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x082a  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0833  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x083a  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0844  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x0850  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0859  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0861  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x0868  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x0885  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x0894  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x08d6  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x08eb  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x08f8  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x0948  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x0954  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x095b  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x099f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x09b4  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x09c5  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x09dc  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x09e5  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x09ee  */
    /* JADX WARN: Removed duplicated region for block: B:542:0x09fd  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0a24  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x0a4a  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x0a53  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x0a5e  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x0a67  */
    /* JADX WARN: Removed duplicated region for block: B:561:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b5  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 2681
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalUsbBindingImpl.executeBindings():void");
    }
}
