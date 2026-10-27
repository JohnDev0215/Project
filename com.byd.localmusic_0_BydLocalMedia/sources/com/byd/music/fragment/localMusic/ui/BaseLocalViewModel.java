package com.byd.music.fragment.localMusic.ui;

import android.text.TextUtils;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import com.blankj.utilcode.util.SPUtils;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.api.ApiMethod;
import com.byd.music.base.BaseViewModel;
import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.ArtistBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.constant.SPConstants;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.pinyin.PinyinComparator;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.byd.music.utils.binding.BindingAction;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class BaseLocalViewModel extends BaseViewModel {
    public List<AlbumBean> albumList;
    public List<ArtistBean> artistList;
    public SingleLiveEvent cancelSingleEvent;
    public ObservableBoolean clickCollectFromPlayer;
    public SingleLiveEvent<Boolean> collectClickEvent;
    public SingleLiveEvent collectDataChangeEvent;
    public List<LocalMusicBean> collectLocalMusicList;
    public ItemBinding<LocalMusicBean> collectLocalSdUsbMusicBeanItemBinding;
    public ObservableList<LocalMusicBean> collectLocalSdUsbMusicBeansList;
    public ObservableField<LocalMusicBean> currentPlayLocalMusicBean;
    public SingleLiveEvent delSingleEvent;
    private SingleLiveEvent deleteMusicSingleLiveEvent;
    public SingleLiveEvent disableShowUI;
    public Boolean ifSeekbarDraging;
    public ObservableBoolean isLoading;
    public ObservableBoolean isPlaying;
    public ObservableBoolean isSD;
    public ObservableBoolean isSDTag;
    public ObservableBoolean isSelectAll;
    public SingleLiveEvent<Boolean> isSelectAllEvent;
    public ObservableBoolean isSelectItem;
    public ObservableBoolean isShowOperateUi;
    public ObservableBoolean isShowPlayUI;
    public ObservableBoolean isShowRg;
    public ObservableBoolean isShowTabUI;
    public ObservableInt localMusicAlbumCount;
    public ObservableInt localMusicArtistCount;
    public SingleLiveEvent<LocalMusicBean> localMusicBeanSingleLiveEvent;
    public ObservableField<LocalMusicBean> localMusicBeanState;
    public ObservableInt localMusicCollectCount;
    public ObservableInt localMusicCount;
    public List<LocalMusicBean> localMusicList;
    public ObservableInt localMusicListCount;
    public ItemBinding<LocalMusicBean> localSdUsbMusicBeanItemBinding;
    public ObservableList<LocalMusicBean> localSdUsbMusicBeansList;
    public ObservableList<LocalMusicBean> localSdUsbMusicDelList;
    HashMap<LocalMusicBean, ObservableBoolean> mAnimationMap;
    private Map<Character, Integer> music_initials_index_map;
    public ObservableInt playMode;
    public ObservableBoolean plugUsbSDTips;
    public SimpleDateFormat sdfHms;
    public SimpleDateFormat sdfMs;
    public List<LocalMusicBean> templist;
    public SingleLiveEvent updateDataEvent;
    public int musicListType = 1;
    public SimpleDateFormat sdf = new SimpleDateFormat("mm:ss", Locale.CHINESE);
    private int playListTag = 0;
    protected int mMusicTag = 0;
    private int mSourceType = 0;
    List<AlbumBean> deleteAlbumList = new ArrayList();
    List<ArtistBean> deleteArtistList = new ArrayList();
    public int delAlbumIndex = -1;
    public int delArtistIndex = -1;
    PinyinComparator pinyinComparator = new PinyinComparator();
    public boolean isFirstPlugUsb = false;
    public boolean isFirstPlugSD = false;
    public boolean isFirstScan = true;
    private int clickPlayListTag = -1;
    protected Set<LocalMusicBean> mMusicSelectedSet = new HashSet();
    protected Set<AlbumBean> mAlbumBeanSelectedSet = new HashSet();
    protected Set<ArtistBean> mArtistBeanSelectedSet = new HashSet();
    public BindingCommand<LocalMusicBean> listMusicLocalSdUsbLongClick = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.1
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            BaseLocalViewModel.this.setIsShowOperateUi(true);
            BaseLocalViewModel.this.setSelectLocalMusic(localMusicBean);
        }
    });
    public BindingCommand<LocalMusicBean> listMusicLocalSdUsbClick = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.2
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            if (BaseLocalViewModel.this.isShowOperateUi.get()) {
                BaseLocalViewModel.this.setSelectLocalMusic(localMusicBean);
                return;
            }
            MusicPlayHelper.getInstance().setMusicEngineByStorageType(BaseLocalViewModel.this.mMusicTag);
            if (BaseLocalViewModel.this.musicListType == 1) {
                MusicPlayHelper.getInstance().playListItemByManual(BaseLocalViewModel.this.localSdUsbMusicBeansList, localMusicBean);
                MusicPlayHelper.getInstance().setCollectList(false);
            } else {
                MusicPlayHelper.getInstance().playListItemByManual(BaseLocalViewModel.this.collectLocalMusicList, localMusicBean);
                MusicPlayHelper.getInstance().setCollectList(true);
            }
        }
    });
    public BindingCommand multiDeleteBtnClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.3
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            if (BaseLocalViewModel.this.localSdUsbMusicDelList.size() > 0) {
                BaseLocalViewModel.this.deleteMusicSingleLiveEvent.call();
            }
        }
    });
    public BindingCommand cancelBtnClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.4
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            BaseLocalViewModel.this.disableOperation();
            if (BaseLocalViewModel.this.musicListType == 3 || BaseLocalViewModel.this.musicListType == 4) {
                BaseLocalViewModel.this.cancelSingleEvent.call();
            }
        }
    });
    public BindingCommand selectAllCmd = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.5
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            BaseLocalViewModel.this.isSelectAll.set(!BaseLocalViewModel.this.isSelectAll.get());
            if (BaseLocalViewModel.this.isSelectAll.get()) {
                BaseLocalViewModel.this.localSdUsbMusicDelList.clear();
                if (BaseLocalViewModel.this.musicListType == 1) {
                    BaseLocalViewModel.this.localSdUsbMusicDelList.addAll(BaseLocalViewModel.this.localSdUsbMusicBeansList);
                } else if (BaseLocalViewModel.this.musicListType == 2) {
                    BaseLocalViewModel.this.localSdUsbMusicDelList.addAll(BaseLocalViewModel.this.collectLocalSdUsbMusicBeansList);
                }
            } else if (BaseLocalViewModel.this.localSdUsbMusicDelList.size() != 0) {
                BaseLocalViewModel.this.localSdUsbMusicDelList.clear();
            }
            LogUtil.e(BaseLocalViewModel.this.TAG, "call: selectAllCmd");
            BaseLocalViewModel.this.isSelectAllEvent.setValue(Boolean.valueOf(BaseLocalViewModel.this.isSelectAll.get()));
        }
    });
    public BindingCommand<LocalMusicBean> itemSelectDelCmd = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.6
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            LogUtil.e(BaseLocalViewModel.this.TAG, "itemSelectDelCmd-call: Alvin");
            BaseLocalViewModel.this.setSelectLocalMusic(localMusicBean);
        }
    });
    public BindingCommand<LocalMusicBean> itemDelCmd = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.7
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            if (MusicPlayHelper.getInstance().isShowCarMusicBean(localMusicBean)) {
                LogUtil.d(BaseLocalViewModel.this.TAG, "itemDelCmd is showCarMusic bean");
            } else {
                BaseLocalViewModel.this.addDeleteMusicList(localMusicBean);
                BaseLocalViewModel.this.deleteMusicSingleLiveEvent.call();
            }
        }
    });
    public BindingCommand<LocalMusicBean> collectCliclCmd = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.8
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            LogUtil.d(BaseLocalViewModel.this.TAG, "collectCliclCmd");
            if (MusicPlayHelper.getInstance().isShowCarMusicBean(localMusicBean)) {
                LogUtil.d(BaseLocalViewModel.this.TAG, "collectClickCmd is showCarMusic bean");
                return;
            }
            BaseLocalViewModel.this.collectMusicList(localMusicBean);
            ObservableBoolean observableBoolean = BaseLocalViewModel.this.mAnimationMap.get(localMusicBean);
            if (observableBoolean != null) {
                observableBoolean.set(localMusicBean.isCollect());
            }
            BaseLocalViewModel.this.collectDataChangeEvent.call();
        }
    });
    public BindingCommand playItemClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$BaseLocalViewModel$lt997tPuoVcrIFundVEvOvMgsWs
        @Override // com.byd.music.utils.binding.BindingAction
        public final void call() {
            this.f$0.lambda$new$2$BaseLocalViewModel();
        }
    });
    public BindingCommand playPreClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$BaseLocalViewModel$zd_vZCGSb7OzK2mNyQeYhugw-qQ
        @Override // com.byd.music.utils.binding.BindingAction
        public final void call() {
            this.f$0.lambda$new$3$BaseLocalViewModel();
        }
    });
    public BindingCommand playNextClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$BaseLocalViewModel$8j9yf2wOlMF1NGh8iIILX9CSEj4
        @Override // com.byd.music.utils.binding.BindingAction
        public final void call() {
            this.f$0.lambda$new$4$BaseLocalViewModel();
        }
    });
    public BindingCommand playModeItemClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.9
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            if (BaseLocalViewModel.this.isExistCurrentMusic()) {
                int i = 2;
                int iIntValue = MusicPlayHelper.getInstance().getPlayModeMaps().getOrDefault(Integer.valueOf(BaseLocalViewModel.this.mMusicTag), 2).intValue();
                if (iIntValue == 0) {
                    i = 1;
                } else if (iIntValue != 1) {
                    i = 0;
                }
                MusicPlayHelper.getInstance().setPlayMode(BaseLocalViewModel.this.mMusicTag, i);
            }
        }
    });
    public BindingCommand collectClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.ui.BaseLocalViewModel.10
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            StringBuilder sb = new StringBuilder();
            sb.append("collectClick from player: ");
            sb.append(" storageType=");
            sb.append(BaseLocalViewModel.this.mMusicTag);
            LocalMusicBean currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(BaseLocalViewModel.this.mMusicTag);
            if (currentPlayLocalMusic == null) {
                sb.append(" no exit playing music and return");
                LogUtil.e(BaseLocalViewModel.this.TAG, sb.toString());
                return;
            }
            if (MusicPlayHelper.getInstance().isShowCarMusicBean(currentPlayLocalMusic)) {
                sb.append(" is showCarMusic bean and return");
                LogUtil.e(BaseLocalViewModel.this.TAG, sb.toString());
                return;
            }
            Boolean boolValueOf = Boolean.valueOf(currentPlayLocalMusic.isCollect());
            sb.append(" isCollect= ");
            sb.append(boolValueOf);
            LogUtil.d(BaseLocalViewModel.this.TAG, sb.toString());
            BaseLocalViewModel.this.collectClickEvent.setValue(Boolean.valueOf(!boolValueOf.booleanValue()));
            LocalMusicDB.update(currentPlayLocalMusic);
            currentPlayLocalMusic.setCollect(!boolValueOf.booleanValue());
            MusicPlayHelper.getInstance().saveCurrentMusicFromCollectBtn(currentPlayLocalMusic);
            MusicPlayHelper.getInstance().setCurrentLocalMusic(currentPlayLocalMusic);
            MusicPlayHelper.getInstance().setIsCollectMusic(currentPlayLocalMusic.getStorageType(), !boolValueOf.booleanValue());
            if (BaseLocalViewModel.this.mMusicTag == 0) {
                MusicPlayHelper.getInstance().clickCollectFromPlayerLocal.set(!MusicPlayHelper.getInstance().clickCollectFromPlayerLocal.get());
            } else if (BaseLocalViewModel.this.mMusicTag == 1) {
                MusicPlayHelper.getInstance().clickCollectFromPlayerSD.set(!MusicPlayHelper.getInstance().clickCollectFromPlayerSD.get());
            } else if (BaseLocalViewModel.this.mMusicTag == 2) {
                MusicPlayHelper.getInstance().clickCollectFromPlayerUSB.set(!MusicPlayHelper.getInstance().clickCollectFromPlayerUSB.get());
            }
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        ItemBinding<LocalMusicBean> itemBindingOf = ItemBinding.of(BR.localSdUsbMusicBean, R.layout.item_musiclist_local_usb);
        this.localSdUsbMusicBeanItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        ItemBinding<LocalMusicBean> itemBindingOf2 = ItemBinding.of(BR.collectLocalSdUsbMusicBean, R.layout.item_collect_musiclist_local_usb);
        this.collectLocalSdUsbMusicBeanItemBinding = itemBindingOf2;
        itemBindingOf2.bindExtra(BR.viewModel, this);
        this.collectLocalSdUsbMusicBeansList = new ObservableArrayList();
        this.localSdUsbMusicBeansList = new ObservableArrayList();
        this.localSdUsbMusicDelList = new ObservableArrayList();
        this.isSelectAll = new ObservableBoolean(false);
        this.isSelectItem = new ObservableBoolean(false);
        this.localMusicBeanSingleLiveEvent = new SingleLiveEvent<>();
        getLoadingEvent().postValue(true);
        this.isLoading = new ObservableBoolean(true);
        this.isShowOperateUi = new ObservableBoolean(false);
        this.localMusicList = new ArrayList();
        this.collectLocalMusicList = new ArrayList();
        this.localMusicCount = new ObservableInt(0);
        this.localMusicCollectCount = new ObservableInt(0);
        this.localMusicAlbumCount = new ObservableInt(0);
        this.localMusicArtistCount = new ObservableInt(0);
        this.isShowRg = new ObservableBoolean();
        this.localMusicListCount = new ObservableInt(0);
        this.playMode = new ObservableInt(SPUtils.getInstance().getInt(SPConstants.SP_PLAY_MODE_LOCAL_MUSIC, 0));
        this.currentPlayLocalMusicBean = new ObservableField<>();
        this.isPlaying = new ObservableBoolean(false);
        this.isSDTag = new ObservableBoolean();
        this.albumList = new ArrayList();
        this.artistList = new ArrayList();
        this.updateDataEvent = new SingleLiveEvent();
        this.collectDataChangeEvent = new SingleLiveEvent();
        this.deleteMusicSingleLiveEvent = new SingleLiveEvent();
        this.delSingleEvent = new SingleLiveEvent();
        this.cancelSingleEvent = new SingleLiveEvent();
        this.disableShowUI = new SingleLiveEvent();
        this.isSelectAllEvent = new SingleLiveEvent<>();
        this.collectClickEvent = new SingleLiveEvent<>();
        initMusicPlay();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        this.sdfMs = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
        this.sdfHms = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        this.isShowPlayUI = new ObservableBoolean(false);
        this.clickCollectFromPlayer = new ObservableBoolean(false);
        this.isShowTabUI = new ObservableBoolean(false);
    }

    public void setIsShowPlayUI(boolean z) {
        if (this.isShowPlayUI.get() != z) {
            this.isShowPlayUI.set(z);
        }
    }

    public void setIsShowTabUI() {
        this.isShowTabUI.set(true);
    }

    public String toHhMmSs(long j) {
        LogUtil.d(this.TAG, "toHhMmSs : " + j);
        if (j >= 3600000) {
            return this.sdfHms.format(Long.valueOf(j));
        }
        return this.sdfMs.format(Long.valueOf(j));
    }

    public String toHhMmSs(long j, long j2) {
        LogUtil.d(this.TAG, "toHhMmSs duration2: " + j2);
        if (j2 >= 3600000) {
            return this.sdfHms.format(Long.valueOf(j));
        }
        return this.sdfMs.format(Long.valueOf(j));
    }

    public void initMusicPlay() {
        this.currentPlayLocalMusicBean.set(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic());
    }

    public void initData() {
        LogUtil.e(this.TAG, "initData:" + this.mMusicTag);
        scanMusic(false, false);
    }

    public void scanMusic(final boolean z, final boolean z2) {
        LogUtil.d(this.TAG, String.format("scanMusic, isUpdateData = %1$s, isOnResume = %2$s, getIsUsbPlug= %3$s, getIsSDPlug= %4$s, mMusicTag= %5$s", Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(MusicPlayHelper.getInstance().getIsUsbPlug().get()), Boolean.valueOf(MusicPlayHelper.getInstance().getIsSDPlug().get()), Integer.valueOf(this.mMusicTag)));
        AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$BaseLocalViewModel$1mYr5OAlmBHS1bJZ24FidKj_Si0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$scanMusic$0$BaseLocalViewModel(z, z2);
            }
        });
    }

    public /* synthetic */ void lambda$scanMusic$0$BaseLocalViewModel(boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        int i = this.mMusicTag;
        if (i == 0 || ((i == 1 && MusicPlayHelper.getInstance().getIsSDPlug().get()) || (this.mMusicTag == 2 && MusicPlayHelper.getInstance().getIsUsbPlug().get()))) {
            arrayList3.addAll(LocalMusicDB.getLocalMusicByStorageType(this.mMusicTag));
        }
        Map<Long, AlbumBean> mapLoadAllAlbumsByPath = LocalMusicUtils.loadAllAlbumsByPath(arrayList3, this.mMusicTag);
        Map<Long, ArtistBean> mapLoadAllArtist = LocalMusicUtils.loadAllArtist(arrayList3, this.mMusicTag);
        Iterator<Long> it = mapLoadAllAlbumsByPath.keySet().iterator();
        while (it.hasNext()) {
            AlbumBean albumBean = mapLoadAllAlbumsByPath.get(it.next());
            if (albumBean != null) {
                albumBean.addAllMusic(LocalMusicDB.getAlbumMusicWithIdAndStorageType(albumBean.getId(), this.mMusicTag));
                arrayList.add(albumBean);
            }
        }
        Iterator<Long> it2 = mapLoadAllArtist.keySet().iterator();
        while (it2.hasNext()) {
            ArtistBean artistBean = mapLoadAllArtist.get(it2.next());
            if (artistBean != null) {
                artistBean.addAllMusic(LocalMusicDB.getArtistMusicIdAndStorageType(artistBean.getId(), this.mMusicTag));
                arrayList2.add(artistBean);
            }
        }
        arrayList3.sort(new PinyinComparator());
        if (this.mMusicTag == 0) {
            LocalMusicUtils.sortShowCarMusicList(arrayList3);
        }
        arrayList.sort(new PinyinComparator());
        arrayList2.sort(new PinyinComparator());
        LogUtil.d(this.TAG, String.format("scanMusic finish MUSIC_TAG=%1$s querySize=%2$s in threadName =%3$s", Integer.valueOf(this.mMusicTag), Integer.valueOf(arrayList3.size()), Thread.currentThread().getName()));
        initData(arrayList3, arrayList, arrayList2, z, z2);
        this.isShowRg.set(true);
    }

    public void clearData() {
        LogUtil.d(this.TAG, "clearData after MEDIA_EJECT MusicTag=" + this.mMusicTag);
        this.localMusicList.clear();
        this.collectLocalMusicList.clear();
        this.localMusicCount.set(0);
        this.localMusicCollectCount.set(0);
        this.localMusicAlbumCount.set(0);
        this.localMusicArtistCount.set(0);
        this.localSdUsbMusicBeansList.clear();
        this.collectLocalSdUsbMusicBeansList.clear();
        this.albumList.clear();
        this.artistList.clear();
    }

    private void initData(final List<LocalMusicBean> list, final List<AlbumBean> list2, final List<ArtistBean> list3, final boolean z, final boolean z2) {
        LogUtil.d(this.TAG, "initData after scanning music!");
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$BaseLocalViewModel$X4RJE_7FocTcc6xrgV9EUZ5r1dI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initData$1$BaseLocalViewModel(list, list2, list3, z, z2);
            }
        });
    }

    public /* synthetic */ void lambda$initData$1$BaseLocalViewModel(List list, List list2, List list3, boolean z, boolean z2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.localMusicList.clear();
        this.localSdUsbMusicBeansList.clear();
        this.collectLocalSdUsbMusicBeansList.clear();
        this.collectLocalMusicList.clear();
        this.localMusicList.addAll(list);
        int size = this.localMusicList.size();
        this.localMusicCount.set(size);
        setLocalMusicListCount(this.localMusicCount.get());
        List<LocalMusicBean> collectLocalBeanList = LocalMusicDB.getCollectLocalBeanList(true, this.mMusicTag);
        this.collectLocalMusicList = collectLocalBeanList;
        collectLocalBeanList.sort(this.pinyinComparator);
        this.localSdUsbMusicBeansList.addAll(this.localMusicList);
        this.collectLocalSdUsbMusicBeansList.addAll(this.collectLocalMusicList);
        this.localMusicCollectCount.set(this.collectLocalMusicList.size());
        getLoadingEvent().postValue(false);
        this.artistList.clear();
        this.albumList.clear();
        this.albumList.addAll(list2);
        this.artistList.addAll(list3);
        this.updateDataEvent.call();
        StringBuilder sb = new StringBuilder();
        sb.append("initData size=");
        sb.append(size);
        sb.append(" isUpdateData=");
        sb.append(z);
        sb.append(" isOnResume=");
        sb.append(z2);
        sb.append(" NEED_RESTORE=");
        sb.append(OnLineMusic.NEED_RESTORE);
        sb.append(" MUSIC_TAG=");
        sb.append(this.mMusicTag);
        sb.append(" musicEngine=");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" tName=");
        sb.append(Thread.currentThread().getName());
        if (!z) {
            LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp(this.mMusicTag);
            if (size > 0 && (!MusicPlayHelper.getInstance().isShowCarMusicBean(currentMusicFromSp) || this.mMusicTag == 0)) {
                LocalMusicBean curEnginePlayLocalMusic = MusicPlayHelper.getInstance().getCurEnginePlayLocalMusic();
                if ((currentMusicFromSp == null || !LocalMusicUtils.checkMusicFileLength(currentMusicFromSp.getUri()) || (curEnginePlayLocalMusic != null && curEnginePlayLocalMusic.getStorageType() == this.mMusicTag)) && MusicPlayHelper.getInstance().isMatchMusicEngineByStorageType(this.mMusicTag)) {
                    MusicPlayHelper.getInstance().setLocalMusicBeanList(this.localMusicList);
                }
            }
            if (this.isFirstPlugUsb && !list.isEmpty()) {
                if (MusicPlayHelper.getInstance().isMatchMusicEngineByStorageType(this.mMusicTag) && 2 == this.mMusicTag) {
                    playMusicAfterPlugSDUSBV2(list, true, z2);
                }
                this.isFirstPlugUsb = false;
            }
            if (this.isFirstPlugSD && !list.isEmpty()) {
                if (MusicPlayHelper.getInstance().isMatchMusicEngineByStorageType(this.mMusicTag) && 1 == this.mMusicTag) {
                    playMusicAfterPlugSDUSBV2(list, false, z2);
                }
                this.isFirstPlugSD = false;
            }
            if (MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(this.mMusicTag) == null) {
                if (currentMusicFromSp == null) {
                    currentMusicFromSp = !list.isEmpty() ? (LocalMusicBean) list.get(0) : null;
                }
                MusicPlayHelper.getInstance().currentLocalMusicMaps.put(Integer.valueOf(this.mMusicTag), (currentMusicFromSp == null || currentMusicFromSp.getStorageType() == this.mMusicTag) ? currentMusicFromSp : null);
            }
        } else {
            LocalMusicBean curEnginePlayLocalMusic2 = MusicPlayHelper.getInstance().getCurEnginePlayLocalMusic();
            if (curEnginePlayLocalMusic2 == null) {
                LocalMusicBean curEngineMusicFromSp = MusicPlayHelper.getInstance().getCurEngineMusicFromSp();
                if (this.localSdUsbMusicBeansList.size() > 0 && curEngineMusicFromSp != null && curEngineMusicFromSp.getStorageType() == this.mMusicTag) {
                    MusicPlayHelper.getInstance().setLocalMusicBeanList(this.localSdUsbMusicBeansList);
                }
            } else if (curEnginePlayLocalMusic2.getStorageType() == this.mMusicTag) {
                MusicPlayHelper.getInstance().setLocalMusicBeanList(this.localSdUsbMusicBeansList);
            }
        }
        if (MusicPlayHelper.getInstance().getCurEnginePlayLocalMusic() == null) {
            MusicPlayHelper.getInstance().stopPlayLocalMusic();
        }
        sb.append(" initData costTime ");
        sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
        LogUtil.d(this.TAG, sb.toString());
    }

    private void playMusicAfterPlugSDUSBV2(List<LocalMusicBean> list, boolean z, boolean z2) {
        StringBuilder sb = new StringBuilder("playMusicAfterPlugSDUSBV2");
        int i = OnLineMusic.musicEngine;
        sb.append(" musicEngine=");
        sb.append(i);
        if (i == 5 || i == 4 || i == 3) {
            if (list == null || list.isEmpty()) {
                sb.append(" queryMusicList isEmpty and return!!!");
                LogUtil.e(this.TAG, sb.toString());
                return;
            }
            int storageType = list.get(0).getStorageType();
            int iConvertStorageTypeToEngine = MusicPlayHelper.getInstance().convertStorageTypeToEngine(storageType);
            sb.append(" storageType=");
            sb.append(storageType);
            sb.append(" convertEngine=");
            sb.append(iConvertStorageTypeToEngine);
            sb.append(" isEngineSame=");
            sb.append(iConvertStorageTypeToEngine == i);
            LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp(storageType);
            boolean zEquals = Boolean.TRUE.equals(MusicPlayHelper.getInstance().getIsPlayingMaps().getOrDefault(Integer.valueOf(storageType), false));
            int localProgressByStorageType = MusicPlayHelper.getInstance().getLocalProgressByStorageType(storageType);
            sb.append(" isPlaying=");
            sb.append(zEquals);
            sb.append(" spProgress=");
            sb.append(localProgressByStorageType);
            if (zEquals) {
                LocalMusicBean localMusicBean = MusicPlayHelper.getInstance().currentPlayMusicStateMaps.get(Integer.valueOf(storageType));
                if (localMusicBean != null) {
                    localProgressByStorageType = localMusicBean.getProgress();
                }
                sb.append(" setProgress=");
                sb.append(localProgressByStorageType);
            }
            if (iConvertStorageTypeToEngine != i) {
                if (currentMusicFromSp != null && LocalMusicUtils.checkMusicFileLength(currentMusicFromSp.getUri())) {
                    sb.append(" spMusicTitle=");
                    sb.append(StringSubUtil.subString(currentMusicFromSp.getTitle()));
                    sb.append(" setProgress=");
                    sb.append(localProgressByStorageType);
                    MusicPlayHelper.getInstance().currentLocalMusicMaps.put(Integer.valueOf(storageType), currentMusicFromSp);
                    MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, currentMusicFromSp.getDuration(), 4, currentMusicFromSp.getStorageType()));
                } else {
                    sb.append(" spLocalMusicIsExist false");
                    MusicPlayHelper.getInstance().currentLocalMusicMaps.put(Integer.valueOf(storageType), null);
                    MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(0, 0, 4, storageType));
                }
                sb.append(" update and return!!");
                LogUtil.e(this.TAG, sb.toString());
                return;
            }
            sb.append(" isUsb=");
            sb.append(z);
            sb.append(" isOnResume=");
            sb.append(z2);
            sb.append(" queryMusicList size=");
            sb.append(list.size());
            if (currentMusicFromSp == null || !LocalMusicUtils.checkMusicFileLength(currentMusicFromSp.getUri())) {
                sb.append(" localMusicSp is inValid path");
                LogUtil.e(this.TAG, sb.toString());
                if (z2) {
                    MusicPlayHelper.getInstance().playListByItem(list, list.get(0));
                    return;
                } else {
                    MusicPlayHelper.getInstance().setLocalMusicBeanList(list, null);
                    return;
                }
            }
            if (LocalMusicUtils.checkMusicFileLength(currentMusicFromSp.getUri())) {
                boolean z3 = zEquals && MusicPlayHelper.getInstance().checkLocalIsEqualsMusicInfoWithCMIU(currentMusicFromSp.getStorageType());
                sb.append(" title=");
                sb.append(StringSubUtil.subString(currentMusicFromSp.getTitle()));
                sb.append(" isMusicPlaying=");
                sb.append(z3);
                if (z3) {
                    LogUtil.e(this.TAG, sb.toString());
                    return;
                }
                sb.append(" setProgress=");
                sb.append(localProgressByStorageType);
                LogUtil.e(this.TAG, sb.toString());
                if (z2) {
                    if (MusicPlayHelper.getInstance().initPlayMusicList(list)) {
                        MusicPlayHelper.getInstance().play(currentMusicFromSp, localProgressByStorageType);
                        return;
                    }
                    return;
                } else {
                    MusicPlayHelper.getInstance().setLocalMusicBeanList(list, currentMusicFromSp);
                    MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(localProgressByStorageType, currentMusicFromSp.getDuration(), 4, currentMusicFromSp.getStorageType()));
                    MusicPlayHelper.getInstance().getMusicPlayer().setState(0);
                    return;
                }
            }
            LogUtil.e(this.TAG, sb.toString());
        }
    }

    private void playMusicAfterPlugSDUSB(List<LocalMusicBean> list, boolean z, boolean z2) {
        if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
            StringBuilder sb = new StringBuilder();
            sb.append("playMusicAfterPlugSDUSB queryMusicList:");
            sb.append(list.size());
            sb.append(" isUsb=");
            sb.append(z);
            sb.append(" isOnResume=");
            sb.append(z2);
            LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp();
            sb.append(" spLocalMusic isExist=");
            sb.append(currentMusicFromSp != null);
            if (currentMusicFromSp == null || !LocalMusicUtils.checkMusicFileLength(currentMusicFromSp.getUri())) {
                LogUtil.d(this.TAG, sb.toString());
                if (z2) {
                    MusicPlayHelper.getInstance().playListByItem(list, list.get(0));
                    return;
                } else {
                    MusicPlayHelper.getInstance().setLocalMusicBeanList(list, currentMusicFromSp);
                    return;
                }
            }
            int storageType = currentMusicFromSp.getStorageType();
            sb.append(" storageType=");
            sb.append(storageType);
            LocalMusicBean currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentPlayLocalMusic();
            boolean z3 = MusicPlayHelper.getInstance().getIsPlaying().get();
            sb.append(" curPlayLocalMusic isExist=");
            sb.append(currentPlayLocalMusic != null);
            sb.append(" isPlaying=");
            sb.append(z3);
            LogUtil.d(this.TAG, sb.toString());
            if ((z && storageType == 2) || (!z && storageType == 1)) {
                if (currentPlayLocalMusic != null && currentPlayLocalMusic.equals(currentMusicFromSp) && z3) {
                    sb.append(" curLocalMusic is playing and return 01 !!");
                    LogUtil.d(this.TAG, sb.toString());
                    return;
                }
                MusicPlayHelper.getInstance().setLocalMusicBeanList(list, currentMusicFromSp);
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(MusicPlayHelper.getInstance().getLocalProgressByStorageType(currentMusicFromSp.getStorageType()), currentMusicFromSp.getDuration(), 4, currentMusicFromSp.getStorageType()));
                MusicPlayHelper.getInstance().getMusicPlayer().setState(0);
                if (!z2 || MusicPlayHelper.getInstance().isPlaying.get()) {
                    return;
                }
                ApiMethod.playOrStop(true);
                return;
            }
            if ((!z || storageType != 2) && (z || storageType != 1)) {
                currentMusicFromSp = list.get(0);
            }
            if (z2) {
                MusicPlayHelper.getInstance().playListByItem(list, currentMusicFromSp);
                return;
            }
            if (currentPlayLocalMusic != null && currentPlayLocalMusic.equals(currentMusicFromSp) && z3) {
                sb.append(" curLocalMusic is playing and return 02 !!");
                LogUtil.d(this.TAG, sb.toString());
            } else {
                MusicPlayHelper.getInstance().setLocalMusicBeanList(list, currentMusicFromSp);
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(MusicPlayHelper.getInstance().getLocalProgressByStorageType(currentMusicFromSp.getStorageType()), currentMusicFromSp.getDuration(), 4, currentMusicFromSp.getStorageType()));
                MusicPlayHelper.getInstance().getMusicPlayer().setState(0);
            }
        }
    }

    public void getPinyinIndexList(List<LocalMusicBean> list) {
        if (this.music_initials_index_map == null) {
            this.music_initials_index_map = new HashMap();
        }
        if (!this.music_initials_index_map.isEmpty()) {
            this.music_initials_index_map.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getItemPinyin().isEmpty()) {
                this.music_initials_index_map.putIfAbsent(Character.valueOf(list.get(i).getItemPinyin().charAt(0)), Integer.valueOf(i));
            }
        }
    }

    public void getPinyinIndexArtistList(List<ArtistBean> list) {
        if (this.music_initials_index_map == null) {
            this.music_initials_index_map = new HashMap();
        }
        if (!this.music_initials_index_map.isEmpty()) {
            this.music_initials_index_map.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getItemPinyin().isEmpty()) {
                this.music_initials_index_map.putIfAbsent(Character.valueOf(list.get(i).getItemPinyin().charAt(0)), Integer.valueOf(i));
            }
        }
        for (Character ch : this.music_initials_index_map.keySet()) {
            LogUtil.d(getClass().toString(), "The Index For initial " + ch + " is: " + this.music_initials_index_map.get(ch));
        }
    }

    public void getPinyinIndexAlbumList(List<AlbumBean> list) {
        if (this.music_initials_index_map == null) {
            this.music_initials_index_map = new HashMap();
        }
        if (!this.music_initials_index_map.isEmpty()) {
            this.music_initials_index_map.clear();
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getItemPinyin().isEmpty()) {
                this.music_initials_index_map.putIfAbsent(Character.valueOf(list.get(i).getItemPinyin().charAt(0)), Integer.valueOf(i));
            }
        }
        for (Character ch : this.music_initials_index_map.keySet()) {
            LogUtil.d(getClass().toString(), "The Index For initial " + ch + " is: " + this.music_initials_index_map.get(ch));
        }
    }

    public void updatePinyinIndexList() {
        LogUtil.d(this.TAG, "updatePinyinIndexList musicListType:" + this.musicListType + " mMusicTag=" + this.mMusicTag);
        int i = this.musicListType;
        if (1 == i) {
            getPinyinIndexList(this.localMusicList);
            return;
        }
        if (2 == i) {
            getPinyinIndexList(this.collectLocalMusicList);
        } else if (3 == i) {
            getPinyinIndexArtistList(this.artistList);
        } else if (4 == i) {
            getPinyinIndexAlbumList(this.albumList);
        }
    }

    public void updateLocalListByShowCarMode(boolean z) {
        StringBuilder sb = new StringBuilder("updateLocalListByShowCarMode");
        if (this.mMusicTag != 0) {
            sb.append(" is not LOCAL_MUSIC_TAG and return");
            LogUtil.e(this.TAG, sb.toString());
            return;
        }
        sb.append(" isEnter=");
        sb.append(z);
        LocalMusicBean localMusicBeanAddShowCarMusic = LocalMusicUtils.addShowCarMusic(0);
        if (!TextUtils.isEmpty(localMusicBeanAddShowCarMusic.getTitle())) {
            if (z) {
                List<LocalMusicBean> list = this.localMusicList;
                if (list != null && !list.contains(localMusicBeanAddShowCarMusic)) {
                    this.localMusicList.sort(new PinyinComparator());
                    this.localMusicList.add(0, localMusicBeanAddShowCarMusic);
                    this.localSdUsbMusicBeansList.clear();
                    this.localSdUsbMusicBeansList.addAll(this.localMusicList);
                    updateArtistListByShowCarMode(localMusicBeanAddShowCarMusic, true);
                    this.localMusicCount.set(this.localMusicList.size());
                    if (this.musicListType == 2) {
                        setLocalMusicListCount(this.collectLocalMusicList.size());
                    } else {
                        setLocalMusicListCount(this.localMusicCount.get());
                    }
                    this.localMusicCollectCount.set(this.collectLocalMusicList.size());
                }
            } else {
                List<LocalMusicBean> list2 = this.localMusicList;
                if (list2 != null && list2.contains(localMusicBeanAddShowCarMusic)) {
                    this.localMusicList.remove(localMusicBeanAddShowCarMusic);
                    this.localSdUsbMusicBeansList.remove(localMusicBeanAddShowCarMusic);
                    this.collectLocalMusicList.remove(localMusicBeanAddShowCarMusic);
                    this.collectLocalSdUsbMusicBeansList.remove(localMusicBeanAddShowCarMusic);
                    if (this.musicListType == 2) {
                        setLocalMusicListCount(this.collectLocalMusicList.size());
                    } else {
                        setLocalMusicListCount(this.localMusicCount.get());
                    }
                    this.localMusicCollectCount.set(this.collectLocalMusicList.size());
                    updateArtistListByShowCarMode(localMusicBeanAddShowCarMusic, false);
                    this.localMusicCount.set(this.localMusicList.size());
                }
            }
        }
        LogUtil.d(this.TAG, sb.toString());
    }

    public Map<Character, Integer> getMusic_initials_index_map() {
        return this.music_initials_index_map;
    }

    public void addListMusic() {
        this.localSdUsbMusicBeansList.clear();
        this.localSdUsbMusicBeansList.addAll(LocalMusicDB.getSongIdList());
        LogUtil.d(this.TAG, "addListMusic: " + this.localSdUsbMusicBeansList.size());
    }

    public void disableOperation() {
        this.localSdUsbMusicDelList.clear();
        if (this.isShowOperateUi.get()) {
            setIsShowOperateUi(false);
            this.disableShowUI.call();
            if (this.isSelectAll.get()) {
                this.isSelectAll.set(false);
            }
        }
    }

    public void setIsShowOperateUi(Boolean bool) {
        this.isShowOperateUi.set(bool.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSelectLocalMusic(LocalMusicBean localMusicBean) {
        int size;
        if (this.localSdUsbMusicDelList.contains(localMusicBean)) {
            this.localSdUsbMusicDelList.remove(localMusicBean);
        } else {
            this.localSdUsbMusicDelList.add(localMusicBean);
        }
        int size2 = this.localSdUsbMusicDelList.size();
        int i = this.musicListType;
        if (i == 1) {
            size = this.localSdUsbMusicBeansList.size();
        } else {
            size = i == 2 ? this.collectLocalSdUsbMusicBeansList.size() : 0;
        }
        if (size2 == size) {
            this.isSelectAll.set(true);
        } else if (this.isSelectAll.get()) {
            this.isSelectAll.set(false);
        }
    }

    public SingleLiveEvent<LocalMusicBean> getLocalMusicBeanSingleLiveEvent() {
        return this.localMusicBeanSingleLiveEvent;
    }

    public void removeItem(LocalMusicBean localMusicBean) {
        this.localSdUsbMusicBeansList.remove(localMusicBean);
        LocalMusicDB.deleteLocalMusicById(localMusicBean.getMusicId());
    }

    public void collectMusicList(LocalMusicBean localMusicBean) {
        StringBuilder sb = new StringBuilder();
        sb.append("collectMusicList localMusicTitle:");
        sb.append(localMusicBean.getUri());
        LocalMusicDB.update(localMusicBean);
        int iIndexOf = this.localMusicList.indexOf(localMusicBean);
        boolean zIsCollect = localMusicBean.isCollect();
        sb.append(" index:");
        sb.append(iIndexOf);
        sb.append(" collect=");
        sb.append(zIsCollect);
        if (iIndexOf < 0) {
            LogUtil.e(this.TAG, sb.toString());
            return;
        }
        localMusicBean.setCollect(!zIsCollect);
        if (MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(this.mMusicTag) != null && MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(this.mMusicTag).equals(localMusicBean)) {
            MusicPlayHelper.getInstance().setCurrentLocalMusic(localMusicBean);
            MusicPlayHelper.getInstance().setIsCollectMusic(localMusicBean.getStorageType(), localMusicBean.isCollect());
            MusicPlayHelper.getInstance().saveCurrentMusic(localMusicBean);
        }
        this.localSdUsbMusicBeansList.set(iIndexOf, localMusicBean);
        if (zIsCollect) {
            this.collectLocalSdUsbMusicBeansList.remove(localMusicBean);
            this.collectLocalMusicList.remove(localMusicBean);
        } else {
            this.collectLocalSdUsbMusicBeansList.add(localMusicBean);
            this.collectLocalMusicList.add(localMusicBean);
            Collections.sort(this.collectLocalMusicList, this.pinyinComparator);
            this.collectLocalSdUsbMusicBeansList.clear();
            this.collectLocalSdUsbMusicBeansList.addAll(this.collectLocalMusicList);
        }
        if (this.musicListType == 2) {
            setLocalMusicListCount(this.collectLocalMusicList.size());
        }
        this.localMusicCollectCount.set(this.collectLocalMusicList.size());
        this.localMusicList.set(iIndexOf, localMusicBean);
        if (OnLineMusic.musicEngine == MusicPlayHelper.getInstance().convertStorageTypeToEngine(localMusicBean.getStorageType())) {
            MusicPlayHelper.getInstance().initCollectPlayMusicListAndPlay(localMusicBean, zIsCollect, this.localMusicList);
        }
        LogUtil.e(this.TAG, sb.toString());
        updateCollectAblum(localMusicBean);
    }

    public void collectMusicListFromPlayer(LocalMusicBean localMusicBean) {
        int iIndexOf = this.localMusicList.indexOf(localMusicBean);
        LogUtil.e(this.TAG, "collectMusicList: " + iIndexOf);
        boolean zIsCollect = localMusicBean.isCollect();
        if (iIndexOf >= 0) {
            this.localSdUsbMusicBeansList.set(iIndexOf, localMusicBean);
            if (zIsCollect) {
                if (!this.collectLocalMusicList.contains(localMusicBean)) {
                    this.collectLocalMusicList.add(localMusicBean);
                    this.collectLocalMusicList.sort(this.pinyinComparator);
                    this.collectLocalSdUsbMusicBeansList.clear();
                    this.collectLocalSdUsbMusicBeansList.addAll(this.collectLocalMusicList);
                }
            } else {
                this.collectLocalSdUsbMusicBeansList.remove(localMusicBean);
                this.collectLocalMusicList.remove(localMusicBean);
            }
            this.localMusicCollectCount.set(this.collectLocalMusicList.size());
            this.localMusicList.set(iIndexOf, localMusicBean);
            if (OnLineMusic.musicEngine == MusicPlayHelper.getInstance().convertStorageTypeToEngine(localMusicBean.getStorageType())) {
                MusicPlayHelper.getInstance().initCollectPlayMusicListAndPlay(localMusicBean, !zIsCollect, this.localMusicList);
            }
            if (this.musicListType == 2) {
                setLocalMusicListCount(this.collectLocalSdUsbMusicBeansList.size());
                if (this.isShowOperateUi.get()) {
                    this.localSdUsbMusicDelList.remove(localMusicBean);
                }
            }
            updateCollectAblum(localMusicBean);
        }
    }

    private void updateCollectAblum(LocalMusicBean localMusicBean) {
        Iterator<AlbumBean> it = this.albumList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AlbumBean next = it.next();
            if (next.getName().equals(localMusicBean.getAlbum())) {
                int iIndexOf = next.getmAllMusics().indexOf(localMusicBean);
                if (iIndexOf != -1) {
                    next.getmAllMusics().set(iIndexOf, localMusicBean);
                }
            }
        }
        Iterator<ArtistBean> it2 = this.artistList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            ArtistBean next2 = it2.next();
            if (next2.getId() == localMusicBean.getArtistId()) {
                int iIndexOf2 = next2.getmAllMusics().indexOf(localMusicBean);
                if (iIndexOf2 != -1) {
                    next2.getmAllMusics().set(iIndexOf2, localMusicBean);
                }
            }
        }
        this.collectDataChangeEvent.call();
    }

    private void deleteAlbumAndArtist(LocalMusicBean localMusicBean) {
        for (AlbumBean albumBean : this.albumList) {
            if (albumBean.getId() == localMusicBean.getAlbumId()) {
                int iIndexOf = albumBean.getmAllMusics().indexOf(localMusicBean);
                if (iIndexOf != -1) {
                    albumBean.getmAllMusics().remove(iIndexOf);
                }
                if (albumBean.allMusicSize() == 0) {
                    this.delAlbumIndex = this.albumList.indexOf(albumBean);
                    this.deleteAlbumList.add(albumBean);
                }
            }
        }
        this.albumList.removeAll(this.deleteAlbumList);
        getPinyinIndexAlbumList(this.albumList);
        this.deleteAlbumList.clear();
        for (ArtistBean artistBean : this.artistList) {
            if (artistBean.getId() == localMusicBean.getArtistId()) {
                int iIndexOf2 = artistBean.getmAllMusics().indexOf(localMusicBean);
                if (iIndexOf2 != -1) {
                    artistBean.getmAllMusics().remove(iIndexOf2);
                }
                if (artistBean.allMusicSize() == 0) {
                    this.delArtistIndex = this.artistList.indexOf(artistBean);
                    this.deleteArtistList.add(artistBean);
                }
            }
        }
        this.artistList.removeAll(this.deleteArtistList);
        this.deleteArtistList.clear();
        getPinyinIndexArtistList(this.artistList);
    }

    private void updateArtistListByShowCarMode(LocalMusicBean localMusicBean, boolean z) {
        ArtistBean artistBeanFrom = ArtistBean.from(localMusicBean, localMusicBean.getStorageType());
        if (artistBeanFrom == null) {
            return;
        }
        if (z) {
            boolean z2 = false;
            Iterator<ArtistBean> it = this.artistList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ArtistBean next = it.next();
                if (next.getId() == localMusicBean.getArtistId()) {
                    if (next.getmAllMusics().indexOf(localMusicBean) == -1) {
                        next.getmAllMusics().add(localMusicBean);
                    }
                    z2 = true;
                }
            }
            if (z2) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(localMusicBean);
            artistBeanFrom.addAllMusic(arrayList);
            artistBeanFrom.setMusicCount(arrayList.size());
            this.artistList.add(artistBeanFrom);
            return;
        }
        for (ArtistBean artistBean : this.artistList) {
            if (artistBean.getId() == localMusicBean.getArtistId()) {
                int iIndexOf = artistBean.getmAllMusics().indexOf(localMusicBean);
                if (iIndexOf != -1) {
                    artistBean.getmAllMusics().remove(iIndexOf);
                }
                if (artistBean.allMusicSize() == 0) {
                    this.artistList.remove(artistBean);
                    return;
                }
                return;
            }
        }
    }

    public void switchMusicListData() {
        StringBuilder sb = new StringBuilder();
        sb.append("switchMusicListData collectLocalMusicList:");
        sb.append(this.collectLocalMusicList.size());
        sb.append(" musicListType=");
        sb.append(this.musicListType);
        sb.append(" tag:");
        sb.append(this.mMusicTag);
        int i = this.musicListType;
        if (1 == i) {
            getPinyinIndexList(this.localMusicList);
        } else if (2 == i) {
            getPinyinIndexList(this.collectLocalMusicList);
        }
        sb.append(" localSdUsbMusicBeansList size:");
        sb.append(this.localSdUsbMusicBeansList.size());
        LogUtil.d(this.TAG, sb.toString());
    }

    public void setMusicListType(int i) {
        this.musicListType = i;
    }

    public int getMusicListType() {
        return this.musicListType;
    }

    public void playMusicList(List<LocalMusicBean> list, LocalMusicBean localMusicBean) {
        MusicPlayHelper.getInstance().playListItemByManual(list, localMusicBean);
    }

    public void setmMusicTag(int i, int i2) {
        this.mMusicTag = i;
        this.mSourceType = i2;
        if (this.isFirstScan) {
            initData();
            this.isFirstScan = false;
        }
        if (i == 1) {
            this.isSDTag.set(true);
        } else if (i == 2) {
            this.isSDTag.set(false);
        }
    }

    public SingleLiveEvent getDeleteMusicSingleLiveEvent() {
        return this.deleteMusicSingleLiveEvent;
    }

    public void addDeleteMusicList(LocalMusicBean localMusicBean) {
        this.localSdUsbMusicDelList.clear();
        this.localSdUsbMusicDelList.add(localMusicBean);
    }

    public void deleteMusicList() {
        StringBuilder sb = new StringBuilder();
        this.localSdUsbMusicDelList.removeAll(MusicPlayHelper.getInstance().deleteMusicList);
        int size = this.localSdUsbMusicDelList.size();
        sb.append(" deleteMusicList size=");
        sb.append(size);
        if (size > 0) {
            if (size == this.localMusicCount.get()) {
                this.localSdUsbMusicBeansList.clear();
                this.collectLocalSdUsbMusicBeansList.clear();
                this.localMusicList.clear();
                this.artistList.clear();
                this.albumList.clear();
                this.collectLocalMusicList.clear();
            } else {
                for (LocalMusicBean localMusicBean : this.localSdUsbMusicDelList) {
                    this.localSdUsbMusicBeansList.remove(localMusicBean);
                    this.localMusicList.remove(localMusicBean);
                    this.collectLocalSdUsbMusicBeansList.remove(localMusicBean);
                    this.collectLocalMusicList.remove(localMusicBean);
                    deletePlayMusic(localMusicBean);
                    deleteAlbumAndArtist(localMusicBean);
                }
            }
            int size2 = this.localMusicList.size();
            if (1 == this.musicListType && size2 == 0) {
                setLocalMusicListCount(size2);
            }
            this.localMusicCount.set(size2);
            int size3 = this.collectLocalMusicList.size();
            if (2 == this.musicListType && size3 == 0) {
                setLocalMusicListCount(size3);
            }
            this.localMusicCollectCount.set(size3);
            this.localMusicAlbumCount.set(this.albumList.size());
            this.localMusicArtistCount.set(this.artistList.size());
            disableOperation();
            this.delSingleEvent.call();
            this.collectDataChangeEvent.call();
            sb.append(" mMusicTag=");
            sb.append(this.mMusicTag);
            if (this.localMusicCount.get() == 0 && MusicPlayHelper.getInstance().getCurrentPlayLocalMusic() != null && MusicPlayHelper.getInstance().getCurrentPlayLocalMusic().getStorageType() == this.mMusicTag) {
                MusicPlayHelper.getInstance().setCurrentMusic(null);
            }
            LocalMusicBean localMusicBean2 = MusicPlayHelper.getInstance().currentLocalMusicMaps.get(Integer.valueOf(this.mMusicTag));
            if (localMusicBean2 != null && !LocalMusicUtils.checkMusicFileExist(localMusicBean2.getUri())) {
                sb.append(" setMusicInfo is Null");
                MusicPlayHelper.getInstance().currentLocalMusicMaps.put(Integer.valueOf(this.mMusicTag), null);
                MusicPlayHelper.getInstance().setCurrentPlayMusicState(new LocalMusicBean(0, 0, 4, this.mMusicTag));
            }
            LogUtil.d(this.TAG, sb.toString());
        }
    }

    public void deletePlayMusic(LocalMusicBean localMusicBean) {
        MusicPlayHelper.getInstance().deletePlayMusic(localMusicBean);
    }

    public boolean isCurrentMusic(LocalMusicBean localMusicBean) {
        return MusicPlayHelper.getInstance().getCurrentPlayLocalMusic() != null && MusicPlayHelper.getInstance().getCurrentPlayLocalMusic().equals(localMusicBean);
    }

    public void setPlugUsbSDTips(boolean z) {
        if (this.plugUsbSDTips == null) {
            this.plugUsbSDTips = new ObservableBoolean(this.mMusicTag == 0);
        }
        if (this.plugUsbSDTips.get() != z) {
            this.plugUsbSDTips.set(z);
        }
    }

    public void setLocalMusicListCount(int i) {
        this.localMusicListCount.set(i);
    }

    public boolean isPlayIngMusic(LocalMusicBean localMusicBean, LocalMusicBean localMusicBean2) {
        if (localMusicBean2 == null) {
            return false;
        }
        return localMusicBean2.equals(localMusicBean);
    }

    public ObservableBoolean isStartAnimation(LocalMusicBean localMusicBean) {
        if (MusicPlayHelper.getInstance().isShowCarMusicBean(localMusicBean)) {
            LogUtil.d(this.TAG, "isStartAnimation is showCarMusic bean");
            return new ObservableBoolean(false);
        }
        if (this.mAnimationMap == null) {
            this.mAnimationMap = new HashMap<>();
        }
        if (!this.mAnimationMap.containsKey(localMusicBean) || this.mAnimationMap.get(localMusicBean) == null) {
            this.mAnimationMap.put(localMusicBean, new ObservableBoolean(false));
        }
        return this.mAnimationMap.get(localMusicBean);
    }

    public int getClickPlayListTag() {
        return this.clickPlayListTag;
    }

    public void setClickPlayListTag(int i) {
        this.clickPlayListTag = i;
    }

    public void updateSelectMusicSet(Set<LocalMusicBean> set, Set<AlbumBean> set2, Set<ArtistBean> set3) {
        this.mMusicSelectedSet.clear();
        this.mMusicSelectedSet.addAll(set);
        int i = this.musicListType;
        if (i == 4) {
            this.mAlbumBeanSelectedSet.clear();
            this.mAlbumBeanSelectedSet.addAll(set2);
        } else if (i == 3) {
            this.mArtistBeanSelectedSet.clear();
            this.mArtistBeanSelectedSet.addAll(set3);
        }
    }

    public /* synthetic */ void lambda$new$2$BaseLocalViewModel() {
        LogUtil.d(this.TAG, "playItemClick!");
        if (!isExistCurrentMusic()) {
            LogUtil.d(this.TAG, "playItemClick failed");
        } else if (checkoutPlayListIfNeed()) {
            MusicPlayHelper.getInstance().setMusicEngineByStorageType(this.mMusicTag);
            MusicPlayHelper.getInstance().setPlayErrorTimes(0);
            MusicPlayHelper.getInstance().playOrPauseFromPlayer();
        }
    }

    public /* synthetic */ void lambda$new$3$BaseLocalViewModel() {
        LogUtil.d(this.TAG, "playPreClick!");
        if (!isExistCurrentMusic()) {
            LogUtil.d(this.TAG, "play pre failed! current music is null!");
            return;
        }
        if (!MusicPlayHelper.getInstance().playAudioFocus()) {
            LogUtil.d(this.TAG, "play pre failed! Request audio focus failed!");
        } else if (checkoutPlayListIfNeed()) {
            MusicPlayHelper.getInstance().setMusicEngineByStorageType(this.mMusicTag);
            MusicPlayHelper.getInstance().setPlayErrorTimes(0);
            MusicPlayHelper.getInstance().playPre();
        }
    }

    public /* synthetic */ void lambda$new$4$BaseLocalViewModel() {
        LogUtil.d(this.TAG, "playNextClick!");
        if (!isExistCurrentMusic()) {
            LogUtil.d(this.TAG, "play next failed! current music is null!");
            return;
        }
        if (!MusicPlayHelper.getInstance().playAudioFocus()) {
            LogUtil.d(this.TAG, "play next failed! Request audio focus failed!");
        } else if (checkoutPlayListIfNeed()) {
            MusicPlayHelper.getInstance().setMusicEngineByStorageType(this.mMusicTag);
            MusicPlayHelper.getInstance().setPlayErrorTimes(0);
            MusicPlayHelper.getInstance().playNext();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isExistCurrentMusic() {
        return MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(this.mMusicTag) != null;
    }

    private boolean checkoutPlayListIfNeed() {
        StringBuilder sb = new StringBuilder("checkoutPlayListIfNeed");
        LocalMusicBean currentPlayLocalMusic = MusicPlayHelper.getInstance().getCurrentPlayLocalMusic();
        if (currentPlayLocalMusic != null) {
            sb.append(" before storageType=");
            sb.append(currentPlayLocalMusic.getStorageType());
        }
        if (currentPlayLocalMusic == null || currentPlayLocalMusic.getStorageType() != this.mMusicTag) {
            sb.append(" and checkout engine storageType=");
            sb.append(this.mMusicTag);
            MusicPlayHelper.getInstance().saveCurLocalMusicEngineProgress();
            MusicPlayHelper.getInstance().setMusicEngineByStorageType(this.mMusicTag);
            LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp(this.mMusicTag);
            LocalMusicBean localMusicBean = MusicPlayHelper.getInstance().currentLocalMusicMaps.get(Integer.valueOf(this.mMusicTag));
            sb.append(" curMusicSpIsNull=");
            sb.append(currentMusicFromSp == null);
            if (currentMusicFromSp != null) {
                MusicPlayHelper.getInstance().setCurrentMusic(currentMusicFromSp);
            } else if (localMusicBean != null && this.localSdUsbMusicBeansList.contains(localMusicBean)) {
                MusicPlayHelper.getInstance().setCurrentMusic(localMusicBean);
            } else {
                MusicPlayHelper.getInstance().setCurrentMusic(null);
            }
            zInitPlayMusicList = MusicPlayHelper.getInstance().initPlayMusicList(this.localSdUsbMusicBeansList);
        }
        sb.append(" result=");
        sb.append(zInitPlayMusicList);
        LogUtil.d(this.TAG, sb.toString());
        return zInitPlayMusicList;
    }
}
