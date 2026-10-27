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
import com.byd.music.fragment.localMusic.ui.LocalMusicViewModel;
import com.byd.music.view.CollectLottieView;
import com.byd.music.view.HeaderExpandListview;
import com.byd.music.view.LoadingView;
import com.byd.music.view.SlideBar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class FragmentLocalMusicBindingImpl extends FragmentLocalMusicBinding {
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

    public FragmentLocalMusicBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 46, sIncludes, sViewsWithIds));
    }

    private FragmentLocalMusicBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
        setViewModel((LocalMusicViewModel) obj);
        return true;
    }

    @Override // com.byd.music.databinding.FragmentLocalMusicBinding
    public void setViewModel(LocalMusicViewModel localMusicViewModel) {
        this.mViewModel = localMusicViewModel;
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

    /* JADX WARN: Removed duplicated region for block: B:101:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x05a6  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x05c3  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x05d0  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x05db  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x05eb  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x06aa  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x06bb  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x06d4  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x06d7  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x06e4  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x070f  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0720  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0740  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0765  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0776  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x077d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x078d  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0797  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x079e  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x07ab  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x07bd  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x07cd  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x07f4  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0804  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0816  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x081f  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0827  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0831  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0849  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0854  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0873  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x08b5  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x08c9  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x08d6  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x0924  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x092e  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0935  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x097b  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0990  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x09a3  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x09ba  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x09c1  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x09ca  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x09d9  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x0a00  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0a26  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0a2f  */
    /* JADX WARN: Removed duplicated region for block: B:547:0x0a3a  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x0a43  */
    /* JADX WARN: Removed duplicated region for block: B:556:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01b7  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instruction units count: 2645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.databinding.FragmentLocalMusicBindingImpl.executeBindings():void");
    }
}
