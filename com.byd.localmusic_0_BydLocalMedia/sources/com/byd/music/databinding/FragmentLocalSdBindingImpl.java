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
import com.byd.music.fragment.localMusic.ui.LocalSDViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.HeaderExpandListview;
import com.byd.music.view.LoadingView;
import com.byd.music.view.SlideBar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentLocalSdBindingImpl extends FragmentLocalSdBinding {
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

    public FragmentLocalSdBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 46, sIncludes, sViewsWithIds));
    }

    private FragmentLocalSdBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
        setViewModel((LocalSDViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentLocalSdBinding
    public void setViewModel(LocalSDViewModel localSDViewModel) {
        this.mViewModel = localSDViewModel;
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0699  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x069b  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x06a2  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x06b4  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x06b6  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x06bf  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x06ce  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x06de  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x06eb  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x06f4  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x06f7  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0710  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x071a  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x072d  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0738  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0757  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x0766  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0778  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0782  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0789  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0791  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0798  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x07b9  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x07c9  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x07df  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x07f2  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0800  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x080c  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0824  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x082f  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x0858  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x086a  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0888  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x08ca  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x08dc  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x08e7  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x092e  */
    /* JADX WARN: Removed duplicated region for block: B:527:0x0937  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x097d  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x0992  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x09a5  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x09bc  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x09c5  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x09ce  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x09dd  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0a04  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x0a2a  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0a31  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0a3a  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x0a43  */
    /* JADX WARN: Removed duplicated region for block: B:562:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01b1  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 2643
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalSdBindingImpl.executeBindings():void");
    }
}
