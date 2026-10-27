package com.byd.music.fragment.localMusic.search;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import com.blankj.utilcode.util.ScreenUtils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.ArtistBean;
import com.byd.music.beans.EventString;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.SearchHistoryBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.db.SearchHistoryDB;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.pinyin.PinyinComparator;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.OrientationUtil;
import com.byd.music.utils.binding.BindingAction;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import io.objectbox.android.ObjectBoxLiveData;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicSearchViewModel extends BaseViewModel {
    private List<LocalMusicBean> albumMusic;
    public ObservableInt albumSpanHDistance;
    public ObservableInt albumSpanHDistanceMulti;
    public ObservableInt albumSpanSize;
    public ObservableInt albumSpanVDistance;
    public ObservableInt albumSpanVDistanceMulti;
    public SingleLiveEvent artistListChangeEvent;
    public String curKeyWord;
    private ObjectBoxLiveData<SearchHistoryBean> historyBeanList;
    public ObservableBoolean isAlbumDetailShow;
    public ObservableBoolean isAlbumListChecked;
    public ObservableBoolean isAlbumListVisible;
    public ObservableBoolean isArtistListChecked;
    public ObservableBoolean isExpandableListVisible;
    private ObservableBoolean isKeyboardActive;
    public ObservableBoolean isMusicListChecked;
    public ObservableBoolean isMusicListVisible;
    public ObservableBoolean isNoData;
    public ObservableBoolean isRadioGroupVisible;
    public boolean isSdAvailable;
    public SingleLiveEvent<Boolean> isSearchFragmentShow;
    public ObservableBoolean isSearchHistoryVisible;
    public ObservableBoolean isSearching;
    public boolean isUsbAvailable;
    private ObjectBoxLiveData<LocalMusicBean> localMusicLiveData;
    public ItemBinding<AlbumBean> localSearchAlbumBeanItemBinding;
    public ObservableList<AlbumBean> localSearchAlbumBeansList;
    public List<ArtistBean> localSearchArtistBeansList;
    public ItemBinding<LocalMusicBean> localSearchMusicBeanItemBinding;
    public ObservableList<LocalMusicBean> localSearchMusicBeansList;
    public ObservableInt matchAlbumNum;
    public ObservableInt matchArtistNum;
    public ObservableInt matchMusicNum;
    public ArrayList<LocalMusicBean> matchedSongBeanList;
    public ObservableField<String> observableUri;
    public ObservableBoolean expectKeyBoardStatus = new ObservableBoolean(false);
    private final PinyinComparator pinyinComparator = new PinyinComparator();
    public BindingCommand<LocalMusicBean> listMusicLocalSearchClick = new BindingCommand<>(new BindingConsumer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.1
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(LocalMusicBean localMusicBean) {
            LogUtil.d("Music Bean Clicked.");
            if (LocalMusicSearchViewModel.this.isAlbumDetailShow.get()) {
                LogUtil.d(LocalMusicSearchViewModel.this.TAG, "Play By Album List.");
                MusicPlayHelper.getInstance().playListByItem(LocalMusicSearchViewModel.this.localSearchMusicBeansList, localMusicBean);
                LocalMusicSearchViewModel.this.updateSearchHistoryDb(localMusicBean, 3);
                LocalMusicSearchViewModel.this.isAlbumDetailShow.set(false);
            } else {
                MusicPlayHelper.getInstance().initPlayMusicListByItem(localMusicBean);
                MusicPlayHelper.getInstance().play(localMusicBean, 0);
                LocalMusicSearchViewModel.this.updateSearchHistoryDb(localMusicBean, 1);
            }
            LocalMusicSearchViewModel.this.isSearchFragmentShow.postValue(Boolean.valueOf(LocalMusicSearchViewModel.this.isAlbumDetailShow.get()));
            EventBus.getDefault().post(new EventString("switchTab", "listMusicLocalSearchClick"));
        }
    });
    public BindingCommand localSearchBackClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.2
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            LogUtil.d(LocalMusicSearchViewModel.this.TAG, "Search Back Click!");
            LocalMusicSearchViewModel.this.isSearchFragmentShow.postValue(Boolean.valueOf(LocalMusicSearchViewModel.this.isAlbumDetailShow.get()));
        }
    });
    public BindingCommand clearLocalHistoryClick = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.3
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            LogUtil.d(LocalMusicSearchViewModel.this.TAG, "Clear History Click!");
            SearchHistoryDB.clearSearchHistory();
        }
    });
    public BindingCommand<AlbumBean> listAlbumLocalSearchClick = new BindingCommand<>(new BindingConsumer<AlbumBean>() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.4
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(AlbumBean albumBean) {
            LogUtil.d(LocalMusicSearchViewModel.this.TAG, "Album Cover Clicked!");
            LocalMusicSearchViewModel.this.albumMusic = albumBean.getmAllMusics();
            LocalMusicSearchViewModel.this.localSearchMusicBeansList.clear();
            LocalMusicSearchViewModel.this.localSearchMusicBeansList.addAll(LocalMusicSearchViewModel.this.albumMusic);
            LocalMusicSearchViewModel.this.isMusicListVisible.set(true);
            LocalMusicSearchViewModel.this.isAlbumListVisible.set(false);
            LocalMusicSearchViewModel.this.isAlbumDetailShow.set(true);
        }
    });
    private final Runnable mSearchRunnable = new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.5
        @Override // java.lang.Runnable
        public void run() {
            LogUtil.d(LocalMusicSearchViewModel.this.TAG, "Start Runnable, curKeyword is " + LocalMusicSearchViewModel.this.curKeyWord);
            if (LocalMusicSearchViewModel.this.curSearchKeyword.equals("")) {
                LocalMusicSearchViewModel localMusicSearchViewModel = LocalMusicSearchViewModel.this;
                localMusicSearchViewModel.curSearchKeyword = localMusicSearchViewModel.curKeyWord;
            }
            if (LocalMusicSearchViewModel.this.curSearchKeyword.equals(LocalMusicSearchViewModel.this.curKeyWord)) {
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalMusicSearchViewModel.this.isSearchHistoryVisible.set(false);
                        LocalMusicSearchViewModel.this.localSearchMusicBeansList.clear();
                        LocalMusicSearchViewModel.this.localSearchArtistBeansList.clear();
                        LocalMusicSearchViewModel.this.localSearchAlbumBeansList.clear();
                        LocalMusicSearchViewModel.this.isSearching.set(true);
                    }
                });
                List<LocalMusicBean> songIdListWithStorageType = LocalMusicDB.getSongIdListWithStorageType(LocalMusicSearchViewModel.this.isUsbAvailable, LocalMusicSearchViewModel.this.isSdAvailable);
                List<ArtistBean> artistListWithStorageType = LocalMusicDB.getArtistListWithStorageType(LocalMusicSearchViewModel.this.isUsbAvailable, LocalMusicSearchViewModel.this.isSdAvailable);
                List<AlbumBean> albumListByStorageType = LocalMusicDB.getAlbumListByStorageType(LocalMusicSearchViewModel.this.isUsbAvailable, LocalMusicSearchViewModel.this.isSdAvailable);
                LocalMusicSearchViewModel.this.matchedSongBeanList = new ArrayList<>();
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                for (LocalMusicBean localMusicBean : songIdListWithStorageType) {
                    if (localMusicBean.getTitle().toLowerCase().contains(LocalMusicSearchViewModel.this.curKeyWord.toLowerCase())) {
                        LocalMusicSearchViewModel.this.matchedSongBeanList.add(localMusicBean);
                    }
                }
                LocalMusicSearchViewModel.this.matchedSongBeanList.sort(LocalMusicSearchViewModel.this.pinyinComparator);
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalMusicSearchViewModel.this.sortListByStorageType(LocalMusicSearchViewModel.this.matchedSongBeanList);
                        LocalMusicSearchViewModel.this.localSearchMusicBeansList.addAll(LocalMusicSearchViewModel.this.matchedSongBeanList);
                        LocalMusicSearchViewModel.this.isMusicListVisible.set(LocalMusicSearchViewModel.this.localSearchMusicBeansList.size() > 0);
                    }
                });
                for (ArtistBean artistBean : artistListWithStorageType) {
                    if (artistBean.getName().toLowerCase().contains(LocalMusicSearchViewModel.this.curKeyWord.toLowerCase()) && !artistBean.getName().equals(LocalMusicUtils.BYD_SHOW_CAR_ARTIST)) {
                        arrayList.add(artistBean);
                    }
                }
                arrayList.sort(LocalMusicSearchViewModel.this.pinyinComparator);
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalMusicSearchViewModel.this.localSearchArtistBeansList.addAll(arrayList);
                        LocalMusicSearchViewModel.this.artistListChangeEvent.call();
                    }
                });
                for (AlbumBean albumBean : albumListByStorageType) {
                    if (albumBean.getName().toLowerCase().contains(LocalMusicSearchViewModel.this.curKeyWord.toLowerCase())) {
                        arrayList2.add(albumBean);
                    }
                }
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.5.4
                    @Override // java.lang.Runnable
                    public void run() {
                        LocalMusicSearchViewModel.this.localSearchAlbumBeansList.addAll(arrayList2);
                        LocalMusicSearchViewModel.this.localSearchAlbumBeansList.sort(LocalMusicSearchViewModel.this.pinyinComparator);
                        boolean z = true;
                        LocalMusicSearchViewModel.this.isRadioGroupVisible.set((arrayList2.size() > 0 || LocalMusicSearchViewModel.this.matchedSongBeanList.size() > 0 || arrayList.size() > 0) && !LocalMusicSearchViewModel.this.isSearchHistoryVisible.get());
                        LocalMusicSearchViewModel.this.matchMusicNum.set(LocalMusicSearchViewModel.this.matchedSongBeanList.size());
                        LocalMusicSearchViewModel.this.matchArtistNum.set(arrayList.size());
                        LocalMusicSearchViewModel.this.matchAlbumNum.set(arrayList2.size());
                        ObservableBoolean observableBoolean = LocalMusicSearchViewModel.this.isNoData;
                        if (arrayList2.size() != 0 && arrayList.size() != 0 && LocalMusicSearchViewModel.this.matchedSongBeanList.size() != 0) {
                            z = false;
                        }
                        observableBoolean.set(z);
                        LocalMusicSearchViewModel.this.isExpandableListVisible.set(LocalMusicSearchViewModel.this.isArtistListChecked.get());
                        LocalMusicSearchViewModel.this.isSearching.set(false);
                    }
                });
                if (!LocalMusicSearchViewModel.this.curSearchKeyword.equals(LocalMusicSearchViewModel.this.curKeyWord)) {
                    LocalMusicSearchViewModel.this.curSearchKeyword = "";
                    LocalMusicSearchViewModel localMusicSearchViewModel2 = LocalMusicSearchViewModel.this;
                    localMusicSearchViewModel2.startMatch(localMusicSearchViewModel2.curKeyWord);
                    return;
                }
                LocalMusicSearchViewModel.this.curSearchKeyword = "";
            }
        }
    };
    private String curSearchKeyword = "";

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        ObservableBoolean observableBoolean = new ObservableBoolean();
        this.isRadioGroupVisible = observableBoolean;
        observableBoolean.set(false);
        ObservableBoolean observableBoolean2 = new ObservableBoolean();
        this.isMusicListVisible = observableBoolean2;
        observableBoolean2.set(false);
        ObservableBoolean observableBoolean3 = new ObservableBoolean();
        this.isExpandableListVisible = observableBoolean3;
        observableBoolean3.set(false);
        ObservableBoolean observableBoolean4 = new ObservableBoolean();
        this.isAlbumListVisible = observableBoolean4;
        observableBoolean4.set(false);
        ObservableBoolean observableBoolean5 = new ObservableBoolean();
        this.isNoData = observableBoolean5;
        observableBoolean5.set(false);
        ObservableBoolean observableBoolean6 = new ObservableBoolean();
        this.isMusicListChecked = observableBoolean6;
        observableBoolean6.set(true);
        ObservableBoolean observableBoolean7 = new ObservableBoolean();
        this.isArtistListChecked = observableBoolean7;
        observableBoolean7.set(false);
        ObservableBoolean observableBoolean8 = new ObservableBoolean();
        this.isAlbumListChecked = observableBoolean8;
        observableBoolean8.set(false);
        ObservableBoolean observableBoolean9 = new ObservableBoolean();
        this.isAlbumDetailShow = observableBoolean9;
        observableBoolean9.set(false);
        ObservableBoolean observableBoolean10 = new ObservableBoolean();
        this.isSearching = observableBoolean10;
        observableBoolean10.set(false);
        ObservableBoolean observableBoolean11 = new ObservableBoolean();
        this.isKeyboardActive = observableBoolean11;
        observableBoolean11.set(false);
        ObservableField<String> observableField = new ObservableField<>();
        this.observableUri = observableField;
        observableField.set("");
        this.artistListChangeEvent = new SingleLiveEvent();
        this.albumSpanSize = new ObservableInt();
        ObservableInt observableInt = new ObservableInt();
        this.albumSpanHDistance = observableInt;
        observableInt.set((int) (ScreenUtils.getScreenDensity() * 24.0f));
        ObservableInt observableInt2 = new ObservableInt();
        this.albumSpanVDistance = observableInt2;
        observableInt2.set((int) (ScreenUtils.getScreenDensity() * 24.0f));
        ObservableInt observableInt3 = new ObservableInt();
        this.albumSpanHDistanceMulti = observableInt3;
        observableInt3.set((int) (ScreenUtils.getScreenDensity() * 16.0f));
        ObservableInt observableInt4 = new ObservableInt();
        this.albumSpanVDistanceMulti = observableInt4;
        observableInt4.set((int) (ScreenUtils.getScreenDensity() * 16.0f));
        this.isSearchFragmentShow = new SingleLiveEvent<>();
        this.localSearchMusicBeansList = new ObservableArrayList();
        this.localSearchArtistBeansList = new ArrayList();
        this.localSearchAlbumBeansList = new ObservableArrayList();
        this.matchMusicNum = new ObservableInt();
        this.matchArtistNum = new ObservableInt();
        this.matchAlbumNum = new ObservableInt();
        this.isUsbAvailable = MusicPlayHelper.getInstance().getIsUsbPlug().get();
        this.isSdAvailable = MusicPlayHelper.getInstance().getIsSDPlug().get();
        getHistoryFromDb();
        ItemBinding<LocalMusicBean> itemBindingOf = ItemBinding.of(BR.localSearchMusicBean, R.layout.item_music_list_local_search);
        this.localSearchMusicBeanItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        ItemBinding<AlbumBean> itemBindingOf2 = ItemBinding.of(BR.localSearchAlbumBean, R.layout.item_artist_search);
        this.localSearchAlbumBeanItemBinding = itemBindingOf2;
        itemBindingOf2.bindExtra(BR.viewModel, this);
        getAllList();
    }

    private void getAllList() {
        this.localMusicLiveData = LocalMusicDB.getLocalMusicLiveData();
    }

    private void getHistoryFromDb() {
        this.isSearchHistoryVisible = new ObservableBoolean();
        this.historyBeanList = SearchHistoryDB.loadNewHistory();
        this.isSearchHistoryVisible.set(true);
    }

    public void startMatch(String str) {
        if (str.length() == 0) {
            this.curSearchKeyword = "";
            return;
        }
        this.curKeyWord = str;
        if (this.curSearchKeyword.equals("")) {
            AppExecutors.getInstance().diskIO().execute(this.mSearchRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sortListByStorageType(List<LocalMusicBean> list) {
        List<LocalMusicBean> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            LocalMusicBean localMusicBean = list.get(i);
            if (!arrayList2.contains(localMusicBean)) {
                arrayList.add(localMusicBean);
                for (int i2 = i + 1; i2 < list.size(); i2++) {
                    LocalMusicBean localMusicBean2 = list.get(i2);
                    if (arrayList.size() > 0 && arrayList.get(arrayList.size() - 1).getTitle().equals(localMusicBean2.getTitle())) {
                        arrayList.add(localMusicBean2);
                    }
                }
                arrayList.size();
                if (arrayList.size() > 1) {
                    arrayList = sortListWithSameTitle(arrayList);
                }
                arrayList2.addAll(arrayList);
                arrayList.clear();
            }
        }
        list.clear();
        list.addAll(arrayList2);
    }

    private List<LocalMusicBean> sortListWithSameTitle(List<LocalMusicBean> list) {
        list.sort(new Comparator<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.search.LocalMusicSearchViewModel.6
            @Override // java.util.Comparator
            public int compare(LocalMusicBean localMusicBean, LocalMusicBean localMusicBean2) {
                if (localMusicBean.getStorageType() == localMusicBean2.getStorageType()) {
                    return 0;
                }
                if (localMusicBean.getStorageType() == 0) {
                    return -1;
                }
                return (localMusicBean.getStorageType() == 2 && localMusicBean2.getStorageType() == 1) ? -1 : 1;
            }
        });
        return list;
    }

    public void playMusicList(List<LocalMusicBean> list, LocalMusicBean localMusicBean) {
        MusicPlayHelper.getInstance().playListByItem(list, localMusicBean);
    }

    public void showAlbumCoverList() {
        this.localSearchMusicBeansList.clear();
        this.isMusicListVisible.set(false);
        this.isAlbumListVisible.set(true);
        this.isAlbumDetailShow.set(false);
    }

    public ObjectBoxLiveData<SearchHistoryBean> getHistoryBeanList() {
        return this.historyBeanList;
    }

    public boolean hasHistory() {
        return SearchHistoryDB.hasHistory();
    }

    public void updateSearchHistoryDb(LocalMusicBean localMusicBean, int i) {
        if (i == 1) {
            SearchHistoryDB.insert(new SearchHistoryBean(localMusicBean.getTitle(), i));
        } else if (i == 2) {
            SearchHistoryDB.insert(new SearchHistoryBean(localMusicBean.getArtist(), i));
        } else {
            if (i != 3) {
                return;
            }
            SearchHistoryDB.insert(new SearchHistoryBean(localMusicBean.getAlbum(), i));
        }
    }

    public void refreshSpanCount() {
        LogUtil.e(this.TAG, "refreshSpanCount: is Portrait? " + this.isPortraitMode.get());
        this.albumSpanSize.set((this.isMultiMode.get() || OrientationUtil.isPortrait()) ? 4 : 6);
        LogUtil.e(this.TAG, "refreshSpanCount: Count Is " + this.albumSpanSize.get());
    }

    public ObjectBoxLiveData<LocalMusicBean> getLocalMusicLiveData() {
        return this.localMusicLiveData;
    }

    public void setKeyboardActive(boolean z) {
        LogUtil.e(this.TAG, "set vm keyboard active= " + z);
        this.isKeyboardActive.set(z);
    }

    public boolean isKeyboardActive() {
        LogUtil.e(this.TAG, "get vm keyboard active= " + this.isKeyboardActive.get());
        return this.isKeyboardActive.get();
    }

    public void updateExpectKeyboardStatue(boolean z) {
        LogUtil.e(this.TAG, "LocalMusicSearchActivity update expectKeyBoardStatus= " + z);
        this.expectKeyBoardStatus.set(z);
    }

    public boolean isExpectKeyboardStatus() {
        boolean z = this.expectKeyBoardStatus.get();
        LogUtil.e(this.TAG, "LocalMusicSearchActivity expectKeyBoardStatus= " + z);
        return z;
    }
}
