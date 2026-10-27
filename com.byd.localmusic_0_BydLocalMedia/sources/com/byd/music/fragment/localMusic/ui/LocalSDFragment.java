package com.byd.music.fragment.localMusic.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.VoicemailContract;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.Observable;
import androidx.databinding.ObservableMap;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.beans.EventString;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.MediaControlNaviBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.databinding.FragmentLocalSdBinding;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.activity.DialogActivity;
import com.byd.music.fragment.localMusic.adpter.AlbumExpandAbleListAdapter;
import com.byd.music.fragment.localMusic.adpter.ArtistExpandAbleListAdapter;
import com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter;
import com.byd.music.fragment.localMusic.search.LocalMusicSearchActivity;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.style.BydUiStyleConfig;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.HeaderExpandListview;
import com.byd.music.view.SlideBar;
import com.google.android.material.tabs.TabLayout;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class LocalSDFragment extends BaseFragment<FragmentLocalSdBinding, LocalSDViewModel> implements ExpandableListAdapter.onItemOperateListener, ExpandableListAdapter.SelectedGroupListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private AlbumExpandAbleListAdapter albumExpandableListAdapter;
    private ArtistExpandAbleListAdapter artistExpandableListAdapter;
    private BroadcastReceiver mUsbSdBroadReceiver;
    private String DATE_TYPE = "date_type";
    private String SOURCE_TYPE = VoicemailContract.Status.SOURCE_TYPE;
    private int mMusicTag = 0;
    private int mSourceType = 0;
    private boolean isFirstOnResume = true;
    private Observable.OnPropertyChangedCallback onPropertyChangedPlayingCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.1
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            if (LocalSDFragment.this.viewModel == 0) {
                return;
            }
            LocalSDFragment.this.expandAbleDataChanged();
        }
    };
    private Observable.OnPropertyChangedCallback onPropertyChangedCurrentMusicCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.3
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            if (LocalSDFragment.this.viewModel != 0) {
                LocalSDFragment.this.expandAbleDataChanged();
                LocalSDFragment.this.scrollToCurrentMusic(false);
            }
        }
    };
    private Observable.OnPropertyChangedCallback onPropertyClickFromPlayerCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.4
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            ((LocalSDViewModel) LocalSDFragment.this.viewModel).collectMusicListFromPlayer(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(LocalSDFragment.this.mMusicTag));
            LocalSDFragment.this.initTabText();
            LocalSDFragment.this.notifyDataSetChanged();
        }
    };
    private final ObservableMap.OnMapChangedCallback<ObservableMap<Integer, Boolean>, Integer, Boolean> isPlayingStateMapChangedCallback = new ObservableMap.OnMapChangedCallback<ObservableMap<Integer, Boolean>, Integer, Boolean>() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.5
        @Override // androidx.databinding.ObservableMap.OnMapChangedCallback
        public /* bridge */ /* synthetic */ void onMapChanged(ObservableMap observableMap, Integer num) {
            onMapChanged2((ObservableMap<Integer, Boolean>) observableMap, num);
        }

        /* JADX INFO: renamed from: onMapChanged, reason: avoid collision after fix types in other method */
        public void onMapChanged2(ObservableMap<Integer, Boolean> observableMap, Integer num) {
            if (LocalSDFragment.this.viewModel == 0) {
                return;
            }
            LocalSDFragment.this.expandAbleDataChanged();
            LocalSDFragment.this.updateMarqueeMusicName();
            LocalSDFragment localSDFragment = LocalSDFragment.this;
            localSDFragment.scrollToCurrentMusic(localSDFragment.isResumed());
        }
    };
    private Observable.OnPropertyChangedCallback musicCountProp = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.22
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            LocalSDFragment.this.initTabText();
        }
    };
    private Observable.OnPropertyChangedCallback collectMusicCountProp = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.23
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            LocalSDFragment.this.initTabText();
        }
    };
    boolean isDeleteItemMusic = false;
    private Observable.OnPropertyChangedCallback onUsbPlugPropertyChangeCallBack = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.26
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            ((LocalSDViewModel) LocalSDFragment.this.viewModel).getLoadingEvent().postValue(Boolean.valueOf(MusicPlayHelper.getInstance().isHasUsbPlug()));
            if (((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCount.get() > 0) {
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).getLoadingEvent().postValue(false);
            }
            LocalSDFragment.this.setShowPlayUI();
        }
    };
    private Observable.OnPropertyChangedCallback onSDPlugPropertyChangeCallBack = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.27
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            ((LocalSDViewModel) LocalSDFragment.this.viewModel).getLoadingEvent().postValue(Boolean.valueOf(MusicPlayHelper.getInstance().isHasSdPlug()));
            if (((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCount.get() > 0) {
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).getLoadingEvent().postValue(false);
            }
            LocalSDFragment.this.setShowPlayUI();
        }
    };

    @Override // com.byd.music.base.BaseFragment
    protected boolean disableBackground() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected boolean isPreLoad() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    public boolean onBack() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onLazyInit() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void expandAbleDataChanged() {
        AlbumExpandAbleListAdapter albumExpandAbleListAdapter;
        if (!ThreadUtils.isMainThread()) {
            AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 3) {
                        if (LocalSDFragment.this.artistExpandableListAdapter != null) {
                            LocalSDFragment.this.artistExpandableListAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() != 4 || LocalSDFragment.this.albumExpandableListAdapter == null) {
                            return;
                        }
                        LocalSDFragment.this.albumExpandableListAdapter.notifyDataSetChanged();
                    }
                }
            });
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 3) {
            ArtistExpandAbleListAdapter artistExpandAbleListAdapter = this.artistExpandableListAdapter;
            if (artistExpandAbleListAdapter != null) {
                artistExpandAbleListAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() != 4 || (albumExpandAbleListAdapter = this.albumExpandableListAdapter) == null) {
            return;
        }
        albumExpandAbleListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMarqueeMusicName() {
        marqueeMusicName(Boolean.TRUE.equals(MusicPlayHelper.getInstance().isPlayingMaps.getOrDefault(1, false)));
    }

    private void marqueeMusicName(final boolean z) {
        LogUtil.d(this.TAG, "marqueeMusicName isMarquee= " + z);
        requireActivity().runOnUiThread(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$HNUFqoM8rDCJbBDAokG6Ew3rY98
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$marqueeMusicName$0$LocalSDFragment(z);
            }
        });
    }

    public /* synthetic */ void lambda$marqueeMusicName$0$LocalSDFragment(boolean z) {
        if (this.dataBinding != 0) {
            if (z) {
                ((FragmentLocalSdBinding) this.dataBinding).tvMusicName.setFocusable(true);
                ((FragmentLocalSdBinding) this.dataBinding).tvMusicName.setSelected(true);
                ((FragmentLocalSdBinding) this.dataBinding).tvMusicName.requestFocus();
            } else {
                ((FragmentLocalSdBinding) this.dataBinding).tvMusicName.clearFocus();
                ((FragmentLocalSdBinding) this.dataBinding).tvMusicName.setSelected(false);
                ((FragmentLocalSdBinding) this.dataBinding).tvMusicName.setFocusable(false);
            }
        }
    }

    public LocalSDFragment() {
        Bundle bundle = new Bundle();
        bundle.putInt(this.DATE_TYPE, 1);
        bundle.putInt(this.SOURCE_TYPE, 1);
        setArguments(bundle);
    }

    public LocalSDFragment(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt(this.DATE_TYPE, i);
        bundle.putInt(this.SOURCE_TYPE, i2);
        setArguments(bundle);
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mMusicTag = getArguments().getInt(this.DATE_TYPE, 0);
        this.mSourceType = getArguments().getInt(this.SOURCE_TYPE, 0);
        addCallBack();
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_local_sd;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        LogUtil.d(this.TAG, "initData: " + this.mMusicTag);
        ((LocalSDViewModel) this.viewModel).setmMusicTag(this.mMusicTag, this.mSourceType);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        setShowPlayUI();
        initToolContainer();
        initMusicInfoView();
        initMusicListContainer();
        applyViewByOrientation(this.lastIsMultiMode, this.lastDeviceOrientation);
        updateMarqueeMusicName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setShowPlayUI() {
        int i = this.mMusicTag;
        if (i == 0) {
            ((LocalSDViewModel) this.viewModel).setIsShowPlayUI(true);
        } else if (i == 1) {
            ((LocalSDViewModel) this.viewModel).setIsShowPlayUI(MusicPlayHelper.getInstance().getIsSDPlug().get());
        } else {
            ((LocalSDViewModel) this.viewModel).setIsShowPlayUI(MusicPlayHelper.getInstance().getIsUsbPlug().get());
        }
        ((LocalSDViewModel) this.viewModel).setIsShowTabUI();
        LogUtil.e(this.TAG, "setShowPlayUI : " + ((LocalSDViewModel) this.viewModel).isShowPlayUI.get());
    }

    private void initMusicListContainer() {
        ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.setItemAnimator(null);
        ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.setItemAnimator(null);
        ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setAnimation(null);
        initLocalListMusicView();
        ((LocalSDViewModel) this.viewModel).updateDataEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LogUtil.d(LocalSDFragment.this.TAG, "music list updated!");
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicAlbumCount.set(((LocalSDViewModel) LocalSDFragment.this.viewModel).albumList.size());
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicArtistCount.set(((LocalSDViewModel) LocalSDFragment.this.viewModel).artistList.size());
                if (((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).tabLayout.getSelectedTabPosition() + 1 == 2) {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setLocalMusicListCount(((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCollectCount.get());
                } else {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setLocalMusicListCount(((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCount.get());
                }
                LocalSDFragment.this.notifyDataSetChanged();
                LocalSDFragment.this.switchExpandAdapterByTag();
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).switchMusicListData();
                LocalSDFragment.this.initTabText();
            }
        });
        ((LocalSDViewModel) this.viewModel).collectDataChangeEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.7
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LocalSDFragment.this.notifyDataSetChanged();
            }
        });
        ((LocalSDViewModel) this.viewModel).getDeleteMusicSingleLiveEvent().observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.8
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LocalSDFragment localSDFragment = LocalSDFragment.this;
                localSDFragment.startConfirmDeleteActivity(((LocalSDViewModel) localSDFragment.viewModel).localSdUsbMusicDelList);
            }
        });
        ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.setOnLetterTouchListener(new SlideBar.OnLetterTouchListener() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.9
            /* JADX WARN: Removed duplicated region for block: B:24:0x010d A[PHI: r0 r7
  0x010d: PHI (r0v13 int) = (r0v12 int), (r0v17 int) binds: [B:23:0x010b, B:19:0x00e3] A[DONT_GENERATE, DONT_INLINE]
  0x010d: PHI (r7v36 int) = (r7v35 int), (r7v48 int) binds: [B:23:0x010b, B:19:0x00e3] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:28:0x0152  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x015e  */
            @Override // com.byd.music.view.SlideBar.OnLetterTouchListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onLetterTouch(java.lang.String r6, float r7) {
                /*
                    Method dump skipped, instruction units count: 394
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.byd.music.fragment.localMusic.ui.LocalSDFragment.AnonymousClass9.onLetterTouch(java.lang.String, float):void");
            }

            @Override // com.byd.music.view.SlideBar.OnLetterTouchListener
            public void onTouchEnd() {
                if (((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).slideLetter != null) {
                    ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).slideLetter.setVisibility(4);
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).slideLetter.getLayoutParams();
                    layoutParams.setMargins(0, 0, layoutParams.getMarginEnd(), 0);
                    ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).slideLetter.setLayoutParams(layoutParams);
                }
            }
        });
        ((LocalSDViewModel) this.viewModel).delSingleEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.10
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                if ((((LocalSDViewModel) LocalSDFragment.this.viewModel).delArtistIndex == -1 && ((LocalSDViewModel) LocalSDFragment.this.viewModel).delAlbumIndex == -1) || LocalSDFragment.this.dataBinding == 0) {
                    return;
                }
                if (LocalSDFragment.this.isResumed()) {
                    ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).elArtistAlbumList.collapseGroup(((LocalSDViewModel) LocalSDFragment.this.viewModel).delArtistIndex);
                }
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).isFirstScan = false;
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).delArtistIndex = -1;
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).delAlbumIndex = -1;
            }
        });
        ((LocalSDViewModel) this.viewModel).cancelSingleEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.11
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LogUtil.d(LocalSDFragment.this.TAG, "cancelSingleEvent getMusicListType=" + ((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType());
                if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 4) {
                    LocalSDFragment.this.albumExpandableListAdapter.setShowOperate(false);
                    LocalSDFragment.this.albumExpandableListAdapter.cancelSelectAll();
                } else if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 3) {
                    LocalSDFragment.this.artistExpandableListAdapter.setShowOperate(false);
                    LocalSDFragment.this.artistExpandableListAdapter.cancelSelectAll();
                }
            }
        });
        ((LocalSDViewModel) this.viewModel).isSelectAllEvent.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.12
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                LogUtil.d(LocalSDFragment.this.TAG, "isSelectAllEvent observe music list type=" + ((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() + " isSelectAll=" + bool);
                if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 4) {
                    if (bool.booleanValue()) {
                        LocalSDFragment.this.albumExpandableListAdapter.selectAll();
                        return;
                    } else {
                        LocalSDFragment.this.albumExpandableListAdapter.cancelSelectAll();
                        return;
                    }
                }
                if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 3) {
                    if (bool.booleanValue()) {
                        LocalSDFragment.this.artistExpandableListAdapter.selectAll();
                    } else {
                        LocalSDFragment.this.artistExpandableListAdapter.cancelSelectAll();
                    }
                }
            }
        });
        ((LocalSDViewModel) this.viewModel).disableShowUI.observe(this, new Observer() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.13
            @Override // androidx.lifecycle.Observer
            public void onChanged(Object obj) {
                LocalSDFragment.this.enableTabItemVisible(0);
            }
        });
        setLoadingView(((LocalSDViewModel) this.viewModel).isLoading.get());
        switchExpandAdapterByTag();
        ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.14
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (recyclerView.getScrollState() == 0) {
                    LocalSDFragment localSDFragment = LocalSDFragment.this;
                    localSDFragment.highLightIndex(((LocalSDViewModel) localSDFragment.viewModel).getMusicListType(), 0);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (recyclerView.getScrollState() == 1 || recyclerView.getScrollState() == 0) {
                    LocalSDFragment localSDFragment = LocalSDFragment.this;
                    localSDFragment.highLightIndex(((LocalSDViewModel) localSDFragment.viewModel).getMusicListType(), 0);
                }
            }
        });
        ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.15
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (recyclerView.getScrollState() == 0) {
                    LocalSDFragment localSDFragment = LocalSDFragment.this;
                    localSDFragment.highLightIndex(((LocalSDViewModel) localSDFragment.viewModel).getMusicListType(), 0);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (recyclerView.getScrollState() == 1 || recyclerView.getScrollState() == 0) {
                    LocalSDFragment localSDFragment = LocalSDFragment.this;
                    localSDFragment.highLightIndex(((LocalSDViewModel) localSDFragment.viewModel).getMusicListType(), 0);
                }
            }
        });
    }

    private void initToolContainer() {
        ((FragmentLocalSdBinding) this.dataBinding).ivSearch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$vKa6xSaGHQS1fxOuFHk6_5aG7i4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initToolContainer$1$LocalSDFragment(view);
            }
        });
        ((FragmentLocalSdBinding) this.dataBinding).ivVoice.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$eKGlJw8xq2uBcmkLUQs-T9GknmI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initToolContainer$2$LocalSDFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$initToolContainer$1$LocalSDFragment(View view) {
        startActivity(new Intent(requireActivity(), (Class<?>) LocalMusicSearchActivity.class));
        EventBus.getDefault().post(new EventString("disable_operation", String.valueOf(0)));
    }

    public /* synthetic */ void lambda$initToolContainer$2$LocalSDFragment(View view) {
        onSoundEffectClick();
        EventBus.getDefault().post(new EventString("disable_operation", String.valueOf(0)));
    }

    private void initMusicInfoView() {
        if (BydUiStyleConfig.isFancy()) {
            ((FragmentLocalSdBinding) this.dataBinding).seekBar.setThumb(ContextCompat.getDrawable(Utils.getApp(), R.drawable.fancy_seekbar_thumb));
        }
        ((FragmentLocalSdBinding) this.dataBinding).seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.16
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    if (OnLineMusic.musicEngine == 3) {
                        MusicPlayHelper.getInstance().stopUpdateProgress();
                    }
                    ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).tvMusicZerotime.setText(((LocalSDViewModel) LocalSDFragment.this.viewModel).toHhMmSs(i, MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(1) == null ? 0L : r4.getDuration()));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                LogUtil.d("seekbar: onStartTrackingTouch  ifSeekbarDraging = true");
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).ifSeekbarDragging.set(true);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                LogUtil.d("seekbar: onStopTrackingTouch  ifSeekbarDraging = false");
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).ifSeekbarDragging.set(false);
                MusicPlayHelper.getInstance().seekToProgress(LocalSDFragment.this.mMusicTag, seekBar.getProgress());
            }
        });
        ((LocalSDViewModel) this.viewModel).collectClickEvent.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.17
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (LocalSDFragment.this.dataBinding != 0) {
                    ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).collectLottiesView.startAnimation(bool.booleanValue());
                }
            }
        });
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        if (((LocalSDViewModel) this.viewModel).isShowOperateUi.get()) {
            if (((LocalSDViewModel) this.viewModel).musicListType == 4) {
                ((LocalSDViewModel) this.viewModel).updateSelectMusicSet(this.albumExpandableListAdapter.getMusicSelectedSet(), this.albumExpandableListAdapter.getGroupSelectedSet(), null);
            } else if (((LocalSDViewModel) this.viewModel).musicListType == 3) {
                ((LocalSDViewModel) this.viewModel).updateSelectMusicSet(this.artistExpandableListAdapter.getMusicSelectedSet(), null, this.artistExpandableListAdapter.getGroupSelectedSet());
            }
        }
        if (this.dataBinding != 0) {
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.addOnScrollListenerExpandListener(null);
        }
        super.onDestroyView();
    }

    public void notifyDataSetChanged() {
        if (ThreadUtils.isMainThread()) {
            AlbumExpandAbleListAdapter albumExpandAbleListAdapter = this.albumExpandableListAdapter;
            if (albumExpandAbleListAdapter != null) {
                albumExpandAbleListAdapter.notifyDataSetChanged();
            }
            ArtistExpandAbleListAdapter artistExpandAbleListAdapter = this.artistExpandableListAdapter;
            if (artistExpandAbleListAdapter != null) {
                artistExpandAbleListAdapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.18
            @Override // java.lang.Runnable
            public void run() {
                if (LocalSDFragment.this.albumExpandableListAdapter != null) {
                    LocalSDFragment.this.albumExpandableListAdapter.notifyDataSetChanged();
                }
                if (LocalSDFragment.this.artistExpandableListAdapter != null) {
                    LocalSDFragment.this.artistExpandableListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initLocalListMusicView() {
        initTabText();
        ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(((LocalSDViewModel) this.viewModel).getMusicListType() - 1).select();
        ((FragmentLocalSdBinding) this.dataBinding).tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.19
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition() + 1;
                if (1 == position) {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setLocalMusicListCount(((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCount.get());
                } else if (2 == position) {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setLocalMusicListCount(((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCollectCount.get());
                } else if (4 == position) {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setLocalMusicListCount(((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCount.get());
                } else {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setLocalMusicListCount(((LocalSDViewModel) LocalSDFragment.this.viewModel).localMusicCount.get());
                }
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).setMusicListType(position);
                LocalSDFragment.this.switchVisibleRvAndEl(position);
                ((LocalSDViewModel) LocalSDFragment.this.viewModel).switchMusicListData();
                LocalSDFragment.this.switchExpandAdapterByTag();
            }
        });
        ((LocalSDViewModel) this.viewModel).getLoadingEvent().observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.20
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                LogUtil.d(LocalSDFragment.this.TAG, "Local music data is loading = " + bool);
                if (LocalSDFragment.this.dataBinding != 0) {
                    if (bool.booleanValue()) {
                        ((LocalSDViewModel) LocalSDFragment.this.viewModel).isLoading.set(true);
                        ((TextView) ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).loadingView.findViewById(R.id.tv_load)).setText(R.string.scanning);
                    }
                    LocalSDFragment.this.setLoadingView(bool.booleanValue());
                    if (bool.booleanValue()) {
                        return;
                    }
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).isLoading.set(false);
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).switchMusicListData();
                    ((FragmentLocalSdBinding) LocalSDFragment.this.dataBinding).tabLayout.setVisibility(0);
                    if (LocalSDFragment.this.isFirstOnResume) {
                        LocalSDFragment.this.scrollToCurrentMusic(true);
                        LocalSDFragment.this.isFirstOnResume = false;
                    }
                }
            }
        });
        ((LocalSDViewModel) this.viewModel).getLocalMusicBeanSingleLiveEvent().observe(this, new Observer<LocalMusicBean>() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.21
            @Override // androidx.lifecycle.Observer
            public void onChanged(LocalMusicBean localMusicBean) {
            }
        });
        int selectedTabPosition = ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getSelectedTabPosition() + 1;
        if (1 != selectedTabPosition) {
        }
        switchVisibleRvAndEl(selectedTabPosition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTabText() {
        if (((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabCount() >= 4) {
            ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(0).setText(R.string.local_songlist_music_count_search);
            ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(1).setText(R.string.radio_collection);
            ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(2).setText(R.string.singer);
            ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(3).setText(R.string.local_songlist_album_count_search);
            enableTabItemVisible(((LocalSDViewModel) this.viewModel).isShowOperateUi.get() ? 8 : 0);
            if (this.dataBinding != 0) {
                int tabCount = ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tabAt = ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(i);
                    if (tabAt != null) {
                        tabAt.view.setLongClickable(false);
                        if (Build.VERSION.SDK_INT >= 26) {
                            tabAt.view.setTooltipText(null);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableTabItemVisible(int i) {
        int tabCount = ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabCount();
        for (int i2 = 0; i2 < tabCount; i2++) {
            ((FragmentLocalSdBinding) this.dataBinding).tabLayout.getTabAt(i2).view.setVisibility(i);
        }
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initTabText();
        checkNavi();
        if (this.dataBinding != 0 && ((FragmentLocalSdBinding) this.dataBinding).slideLetter.getVisibility() == 0) {
            ((FragmentLocalSdBinding) this.dataBinding).slideLetter.setVisibility(4);
        }
        if (this.dataBinding != 0) {
            ((FragmentLocalSdBinding) this.dataBinding).loadingView.setRotate(((FragmentLocalSdBinding) this.dataBinding).loadingView.getVisibility() == 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchVisibleRvAndEl(int i) {
        if (this.dataBinding != 0) {
            if (((LocalSDViewModel) this.viewModel).getMusicListType() == 1) {
                if (((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.getVisibility() != 0) {
                    ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.setVisibility(8);
                    ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.setVisibility(0);
                }
                if (((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.getVisibility() == 0) {
                    ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setVisibility(8);
                    return;
                }
                return;
            }
            if (((LocalSDViewModel) this.viewModel).getMusicListType() == 2) {
                if (((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.getVisibility() != 0) {
                    ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.setVisibility(8);
                    ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.setVisibility(0);
                }
                if (((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.getVisibility() == 0) {
                    ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setVisibility(8);
                    return;
                }
                return;
            }
            ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.setVisibility(8);
            ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.setVisibility(8);
            if (((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.getVisibility() == 8) {
                ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchExpandAdapterByTag() {
        LogUtil.d(this.TAG, "switchExpandAdapterByTag!");
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 3) {
            if (this.artistExpandableListAdapter == null) {
                LogUtil.d(this.TAG, "switchExpandAdapterByTag, init artist list adapter!");
                ArtistExpandAbleListAdapter artistExpandAbleListAdapter = new ArtistExpandAbleListAdapter(this.mMusicTag, ((LocalSDViewModel) this.viewModel).artistList, ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList);
                this.artistExpandableListAdapter = artistExpandAbleListAdapter;
                artistExpandAbleListAdapter.setSelectedGroupListener(this);
                this.artistExpandableListAdapter.setShowOperate(((LocalSDViewModel) this.viewModel).isShowOperateUi.get());
                if (((LocalSDViewModel) this.viewModel).isShowOperateUi.get()) {
                    this.artistExpandableListAdapter.setMusicSelectedSet(((LocalSDViewModel) this.viewModel).mMusicSelectedSet);
                    this.artistExpandableListAdapter.setGroupSelectedSet(((LocalSDViewModel) this.viewModel).mArtistBeanSelectedSet);
                }
            }
            ((LocalSDViewModel) this.viewModel).getPinyinIndexArtistList(((LocalSDViewModel) this.viewModel).artistList);
            this.artistExpandableListAdapter.setOnItemOperateListener(this);
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setAdapter((ExpandableListAdapter) this.artistExpandableListAdapter);
            LogUtil.d(this.TAG, "switchExpandAdapterByTag, setup artist list adapter!");
            int expandGroupPos = this.artistExpandableListAdapter.getExpandGroupPos();
            if (expandGroupPos != -1) {
                ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.expandGroup(expandGroupPos, false);
            }
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setHeaderView(getLayoutInflater().inflate(R.layout.item_aritsit_headview, (ViewGroup) ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList, false));
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 4) {
            if (this.albumExpandableListAdapter == null) {
                LogUtil.d(this.TAG, "switchExpandAdapterByTag, init album list adapter!");
                AlbumExpandAbleListAdapter albumExpandAbleListAdapter = new AlbumExpandAbleListAdapter(this.mMusicTag, ((LocalSDViewModel) this.viewModel).albumList, ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList);
                this.albumExpandableListAdapter = albumExpandAbleListAdapter;
                albumExpandAbleListAdapter.setSelectedGroupListener(this);
                this.albumExpandableListAdapter.setShowOperate(((LocalSDViewModel) this.viewModel).isShowOperateUi.get());
                if (((LocalSDViewModel) this.viewModel).isShowOperateUi.get()) {
                    this.albumExpandableListAdapter.setMusicSelectedSet(((LocalSDViewModel) this.viewModel).mMusicSelectedSet);
                    this.albumExpandableListAdapter.setGroupSelectedSet(((LocalSDViewModel) this.viewModel).mAlbumBeanSelectedSet);
                }
            }
            ((LocalSDViewModel) this.viewModel).getPinyinIndexAlbumList(((LocalSDViewModel) this.viewModel).albumList);
            this.albumExpandableListAdapter.setOnItemOperateListener(this);
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setAdapter((ExpandableListAdapter) this.albumExpandableListAdapter);
            LogUtil.d(this.TAG, "switchExpandAdapterByTag, setup album list adapter!");
            int expandGroupPos2 = this.albumExpandableListAdapter.getExpandGroupPos();
            if (expandGroupPos2 != -1) {
                ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.expandGroup(expandGroupPos2, false);
            }
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setHeaderView(getLayoutInflater().inflate(R.layout.item_ablum_headview, (ViewGroup) ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList, false));
        }
        ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setNestedScrollingEnabled(true);
        ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.24
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtil.e(LocalSDFragment.this.TAG, "OnItemLongClickListener pos=" + i + " musicListType=" + ((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType());
                if (!((LocalSDViewModel) LocalSDFragment.this.viewModel).isShowOperateUi.get()) {
                    ((LocalSDViewModel) LocalSDFragment.this.viewModel).setIsShowOperateUi(true);
                    if (LocalSDFragment.this.albumExpandableListAdapter != null && ((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 4) {
                        LocalSDFragment.this.albumExpandableListAdapter.setShowOperate(true);
                    }
                    if (LocalSDFragment.this.artistExpandableListAdapter != null && ((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 3) {
                        LocalSDFragment.this.artistExpandableListAdapter.setShowOperate(true);
                    }
                }
                if (ExpandableListView.getPackedPositionType(j) == 1) {
                    long expandableListPosition = ((ExpandableListView) adapterView).getExpandableListPosition(i);
                    int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                    int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                    if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 4) {
                        LocalSDFragment.this.albumExpandableListAdapter.selectChildItem(LocalSDFragment.this.albumExpandableListAdapter.getChildList(packedPositionGroup).get(packedPositionChild), true);
                    } else if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 3) {
                        LocalSDFragment.this.artistExpandableListAdapter.selectChildItem(LocalSDFragment.this.artistExpandableListAdapter.getChildList(packedPositionGroup).get(packedPositionChild), true);
                    }
                    return true;
                }
                if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 4) {
                    LocalSDFragment.this.albumExpandableListAdapter.selectAllMusicGroupLongClick(i, true);
                } else if (((LocalSDViewModel) LocalSDFragment.this.viewModel).getMusicListType() == 3) {
                    LocalSDFragment.this.artistExpandableListAdapter.selectAllMusicGroupLongClick(i, true);
                }
                return true;
            }
        });
        ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.addOnScrollListenerExpandListener(new HeaderExpandListview.OnScrollListenerExpand() { // from class: com.byd.music.fragment.localMusic.ui.LocalSDFragment.25
            @Override // com.byd.music.view.HeaderExpandListview.OnScrollListenerExpand
            public void onScrollExpand(int i) {
                LocalSDFragment localSDFragment = LocalSDFragment.this;
                localSDFragment.highLightIndex(((LocalSDViewModel) localSDFragment.viewModel).getMusicListType(), i);
            }
        });
        highLightIndex(((LocalSDViewModel) this.viewModel).getMusicListType(), 0);
    }

    private String getStoragePath(Context context, boolean z) {
        String absolutePath = "test";
        StorageManager storageManager = (StorageManager) context.getSystemService("storage");
        try {
            Class<?> cls = Class.forName("android.os.storage.VolumeInfo");
            Class<?> cls2 = Class.forName("android.os.storage.DiskInfo");
            Method method = Class.forName("android.os.storage.StorageManager").getMethod("getVolumes", new Class[0]);
            Method method2 = cls.getMethod("getDisk", new Class[0]);
            Method method3 = cls.getMethod("getPath", new Class[0]);
            Method method4 = cls2.getMethod("isUsb", new Class[0]);
            Method method5 = cls2.getMethod("isSd", new Class[0]);
            List list = (List) method.invoke(storageManager, new Object[0]);
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                Object objInvoke = method2.invoke(obj, new Object[0]);
                if (objInvoke != null) {
                    boolean zBooleanValue = ((Boolean) method5.invoke(objInvoke, new Object[0])).booleanValue();
                    boolean zBooleanValue2 = ((Boolean) method4.invoke(objInvoke, new Object[0])).booleanValue();
                    File file = (File) method3.invoke(obj, new Object[0]);
                    if (z == zBooleanValue2) {
                        absolutePath = file.getAbsolutePath();
                    } else if ((!z) == zBooleanValue) {
                        absolutePath = file.getAbsolutePath();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absolutePath;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("receiveEvent type=");
        sb.append(this.mMusicTag);
        sb.append(" str=");
        sb.append(str);
        if (this.mMusicTag != 0) {
            if ("MEDIA_EJECT".equals(str) || "TYPE_STUB".equals(str)) {
                sb.append(" TYPE_STUB usbPlug=");
                sb.append(MusicPlayHelper.getInstance().getIsUsbPlug().get());
                sb.append(" sdPlug=");
                sb.append(MusicPlayHelper.getInstance().getIsSDPlug().get());
                sb.append(" tName=");
                sb.append(Thread.currentThread().getName());
                int i = this.mMusicTag;
                if (i == 2) {
                    if (MusicPlayHelper.getInstance().getIsUsbPlug().get()) {
                        return;
                    }
                    ((LocalSDViewModel) this.viewModel).setPlugUsbSDTips(true);
                    if (MusicPlayHelper.getInstance().getCurrentPlayLocalMusic() != null && MusicPlayHelper.getInstance().getCurrentPlayLocalMusic().getStorageType() == 2) {
                        MusicPlayHelper.getInstance().setCurrentMusic(null);
                    }
                    ((LocalSDViewModel) this.viewModel).clearData();
                    notifyDataSetChanged();
                    LogUtil.e(this.TAG, sb.toString());
                    return;
                }
                if (i != 1 || MusicPlayHelper.getInstance().getIsSDPlug().get()) {
                    return;
                }
                ((LocalSDViewModel) this.viewModel).setPlugUsbSDTips(true);
                if (MusicPlayHelper.getInstance().getCurrentPlayLocalMusic() != null && MusicPlayHelper.getInstance().getCurrentPlayLocalMusic().getStorageType() == 1) {
                    MusicPlayHelper.getInstance().setCurrentMusic(null);
                }
                ((LocalSDViewModel) this.viewModel).clearData();
                notifyDataSetChanged();
                LogUtil.e(this.TAG, sb.toString());
                return;
            }
            "MEDIA_MOUNTED".equals(str);
            return;
        }
        LogUtil.e(this.TAG, sb.toString());
    }

    private void sdOrUsbStorageAdded(int i) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("sdOrUsbStorageAdded mMusicTag=");
        sb.append(this.mMusicTag);
        sb.append(" storageType=");
        sb.append(i);
        if (2 == i) {
            str = LocalMusicUtils.getStoragePath(Utils.getApp().getApplicationContext(), true)[1];
            "test".equals(str);
        } else if (1 == i) {
            str = LocalMusicUtils.getStoragePath(Utils.getApp().getApplicationContext(), false)[0];
            "test".equals(str);
        } else {
            str = "test";
        }
        sb.append(" BydStoragePath=");
        sb.append(str);
        LogUtil.e(this.TAG, sb.toString());
        if ("test".equals(str) || i != this.mMusicTag) {
            return;
        }
        ((LocalSDViewModel) this.viewModel).getLoadingEvent().postValue(true);
        ((LocalSDViewModel) this.viewModel).scanMusic(false, isResumed());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToCurrentMusic(boolean z) {
        final int iIndexOf;
        int expandGroupPos;
        if (!Boolean.TRUE.equals(MusicPlayHelper.getInstance().getIsPlayingMaps().get(Integer.valueOf(this.mMusicTag))) || (iIndexOf = ((LocalSDViewModel) this.viewModel).localSdUsbMusicBeansList.indexOf(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic())) < 0 || this.dataBinding == 0) {
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 1) {
            if (z) {
                ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.post(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$IUydGweVoYqu-nlTmDZxJyXTSPQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$scrollToCurrentMusic$3$LocalSDFragment(iIndexOf);
                    }
                });
                return;
            }
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.getLayoutManager();
            int iFindFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            int iFindLastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (iIndexOf < 0) {
                return;
            }
            if (iIndexOf > iFindLastCompletelyVisibleItemPosition || iIndexOf < iFindFirstCompletelyVisibleItemPosition) {
                ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.post(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$pq7y5YuSrnoPmk5mMW2J73-mRBg
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$scrollToCurrentMusic$4$LocalSDFragment(iIndexOf);
                    }
                });
                highLightIndex(((LocalSDViewModel) this.viewModel).getMusicListType(), 0);
                return;
            }
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 2) {
            final int iIndexOf2 = ((LocalSDViewModel) this.viewModel).collectLocalSdUsbMusicBeansList.indexOf(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic());
            if (z) {
                ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.post(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$7fF1TCJ2JW2brNgrpMLVkCPcji8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$scrollToCurrentMusic$5$LocalSDFragment(iIndexOf2);
                    }
                });
                return;
            }
            LinearLayoutManager linearLayoutManager2 = (LinearLayoutManager) ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.getLayoutManager();
            int iFindFirstCompletelyVisibleItemPosition2 = linearLayoutManager2.findFirstCompletelyVisibleItemPosition();
            int iFindLastCompletelyVisibleItemPosition2 = linearLayoutManager2.findLastCompletelyVisibleItemPosition();
            if (iIndexOf2 < 0) {
                return;
            }
            if (iIndexOf2 > iFindLastCompletelyVisibleItemPosition2 || iIndexOf2 < iFindFirstCompletelyVisibleItemPosition2) {
                ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.post(new Runnable() { // from class: com.byd.music.fragment.localMusic.ui.-$$Lambda$LocalSDFragment$EaEn1ZcdqogTqS5RYu-qohVxzxc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$scrollToCurrentMusic$6$LocalSDFragment(iIndexOf2);
                    }
                });
                highLightIndex(((LocalSDViewModel) this.viewModel).getMusicListType(), 0);
                return;
            }
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 4) {
            int expandGroupPos2 = this.albumExpandableListAdapter.getExpandGroupPos();
            if (expandGroupPos2 != -1) {
                int iIndexOf3 = ((LocalSDViewModel) this.viewModel).albumList.get(expandGroupPos2).getmAllMusics().indexOf(MusicPlayHelper.getInstance().currentLocalMusic.get());
                if (iIndexOf3 == -1) {
                    LogUtil.d(this.TAG, "album group index == -1");
                    return;
                }
                int i = iIndexOf3 + expandGroupPos2;
                if (i - 2 > 0) {
                    ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.smoothScrollToPositionFromTop(i - 1, 0, 200);
                    return;
                } else {
                    ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.smoothScrollToPositionFromTop(expandGroupPos2, 0, 200);
                    return;
                }
            }
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() != 3 || (expandGroupPos = this.artistExpandableListAdapter.getExpandGroupPos()) == -1) {
            return;
        }
        int iIndexOf4 = ((LocalSDViewModel) this.viewModel).artistList.get(expandGroupPos).getmAllMusics().indexOf(MusicPlayHelper.getInstance().currentLocalMusic.get());
        if (iIndexOf4 == -1) {
            LogUtil.d(this.TAG, "artist group index == -1");
            return;
        }
        int i2 = iIndexOf4 + expandGroupPos;
        if (i2 - 2 > 0) {
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.smoothScrollToPositionFromTop(i2 - 1, 0, 200);
        } else {
            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.smoothScrollToPositionFromTop(expandGroupPos, 0, 200);
        }
    }

    public /* synthetic */ void lambda$scrollToCurrentMusic$3$LocalSDFragment(int i) {
        if (this.dataBinding != 0) {
            ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.scrollToPosition(i);
        }
    }

    public /* synthetic */ void lambda$scrollToCurrentMusic$4$LocalSDFragment(int i) {
        ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.scrollToPosition(i);
    }

    public /* synthetic */ void lambda$scrollToCurrentMusic$5$LocalSDFragment(int i) {
        if (this.dataBinding != 0) {
            ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.scrollToPosition(i);
        }
    }

    public /* synthetic */ void lambda$scrollToCurrentMusic$6$LocalSDFragment(int i) {
        ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.scrollToPosition(i);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEventType(EventString eventString) {
        AlbumExpandAbleListAdapter albumExpandAbleListAdapter;
        if (eventString != null) {
            if ("delete_item_music".equals(eventString.getClazz()) && this.mMusicTag == Integer.valueOf(eventString.getString()).intValue()) {
                LogUtil.d(this.TAG, "receiveEventType:delete_item_music");
                this.isDeleteItemMusic = true;
                ((LocalSDViewModel) this.viewModel).deleteMusicList();
                if (((LocalSDViewModel) this.viewModel).getMusicListType() == 3) {
                    ArtistExpandAbleListAdapter artistExpandAbleListAdapter = this.artistExpandableListAdapter;
                    if (artistExpandAbleListAdapter != null) {
                        artistExpandAbleListAdapter.setShowOperate(false);
                        this.artistExpandableListAdapter.notifyDataSetChanged();
                        if (((LocalSDViewModel) this.viewModel).localMusicCount.get() == 0) {
                            this.artistExpandableListAdapter.headerViewArrowClick();
                            ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setVisibility(8);
                            ((FragmentLocalSdBinding) this.dataBinding).ivNoContent.setVisibility(0);
                            ((FragmentLocalSdBinding) this.dataBinding).tvNoContent.setVisibility(0);
                        }
                    }
                } else if (((LocalSDViewModel) this.viewModel).getMusicListType() == 4 && (albumExpandAbleListAdapter = this.albumExpandableListAdapter) != null) {
                    albumExpandAbleListAdapter.setShowOperate(false);
                    this.albumExpandableListAdapter.notifyDataSetChanged();
                    if (((LocalSDViewModel) this.viewModel).localMusicCount.get() == 0) {
                        this.albumExpandableListAdapter.headerViewArrowClick();
                        ((FragmentLocalSdBinding) this.dataBinding).elArtistAlbumList.setVisibility(8);
                        ((FragmentLocalSdBinding) this.dataBinding).ivNoContent.setVisibility(0);
                        ((FragmentLocalSdBinding) this.dataBinding).tvNoContent.setVisibility(0);
                    }
                }
                ((LocalSDViewModel) this.viewModel).updatePinyinIndexList();
                disableOperationUI();
                return;
            }
            if ("disable_operation".equals(eventString.getClazz())) {
                disableOperationUI();
                return;
            }
            if ("update_data".equals(eventString.getClazz())) {
                LogUtil.e(this.TAG, "update_data receiveEventType: " + this.mMusicTag);
                if (this.isDeleteItemMusic) {
                    this.isDeleteItemMusic = false;
                    return;
                } else {
                    ((LocalSDViewModel) this.viewModel).scanMusic(true, isResumed());
                    return;
                }
            }
            if ("update_data_storage".equals(eventString.getClazz())) {
                LogUtil.e(this.TAG, "update_data_storage receiveEventType: " + this.mMusicTag + " " + eventString.getString());
                if (this.isDeleteItemMusic) {
                    this.isDeleteItemMusic = false;
                    return;
                } else {
                    if (this.mMusicTag == Integer.valueOf(eventString.getString()).intValue()) {
                        ((LocalSDViewModel) this.viewModel).scanMusic(true, isResumed());
                        return;
                    }
                    return;
                }
            }
            if ("show_car_mode_status".equals(eventString.getClazz()) && this.mMusicTag == 0) {
                boolean z = Boolean.parseBoolean(eventString.getString());
                LogUtil.e(this.TAG, "show_car_mode_status isEnter: " + z);
                ((LocalSDViewModel) this.viewModel).updateLocalListByShowCarMode(z);
                disableOperationUI();
                return;
            }
            if ("delete_item_music_cancel".equals(eventString.getClazz()) && this.mMusicTag == Integer.valueOf(eventString.getString()).intValue()) {
                if (((LocalSDViewModel) this.viewModel).isShowOperateUi.get()) {
                    return;
                }
                ((LocalSDViewModel) this.viewModel).localSdUsbMusicDelList.clear();
            } else if ("MEDIA_MOUNTED".equals(eventString.getClazz())) {
                if (LocalMusicUtils.SD_PATH.equals(eventString.getString())) {
                    ((LocalSDViewModel) this.viewModel).isFirstPlugSD = true;
                    sdOrUsbStorageAdded(1);
                } else if (LocalMusicUtils.USB_PATH.equals(eventString.getString())) {
                    ((LocalSDViewModel) this.viewModel).isFirstPlugUsb = true;
                    sdOrUsbStorageAdded(2);
                } else {
                    ((LocalSDViewModel) this.viewModel).scanMusic(false, isResumed());
                }
            }
        }
    }

    private void disableOperationUI() {
        AlbumExpandAbleListAdapter albumExpandAbleListAdapter;
        enableTabItemVisible(0);
        ((LocalSDViewModel) this.viewModel).disableOperation();
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 3) {
            ArtistExpandAbleListAdapter artistExpandAbleListAdapter = this.artistExpandableListAdapter;
            if (artistExpandAbleListAdapter != null) {
                artistExpandAbleListAdapter.setShowOperate(false);
                this.artistExpandableListAdapter.cancelSelectAll();
                return;
            }
            return;
        }
        if (((LocalSDViewModel) this.viewModel).getMusicListType() != 4 || (albumExpandAbleListAdapter = this.albumExpandableListAdapter) == null) {
            return;
        }
        albumExpandAbleListAdapter.setShowOperate(false);
        this.albumExpandableListAdapter.cancelSelectAll();
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.onItemOperateListener
    public void onChildClick(List<LocalMusicBean> list, LocalMusicBean localMusicBean, int i, int i2) {
        LogUtil.d(this.TAG, "onChildClick: " + ((LocalSDViewModel) this.viewModel).isShowOperateUi.get());
        if (((LocalSDViewModel) this.viewModel).isShowOperateUi.get()) {
            if (((LocalSDViewModel) this.viewModel).getMusicListType() == 4) {
                this.albumExpandableListAdapter.selectChildItem(localMusicBean, !r2.getMusicSelectedSet().contains(localMusicBean));
                return;
            } else {
                if (((LocalSDViewModel) this.viewModel).getMusicListType() == 3) {
                    this.artistExpandableListAdapter.selectChildItem(localMusicBean, !r2.getMusicSelectedSet().contains(localMusicBean));
                    return;
                }
                return;
            }
        }
        ((LocalSDViewModel) this.viewModel).playMusicList(list, localMusicBean);
        int storageType = localMusicBean.getStorageType();
        MusicPlayHelper.getInstance().setIsCollectMusic(storageType, false);
        if (MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(storageType).equals(localMusicBean)) {
            MusicPlayHelper.getInstance().setIsCollectMusic(storageType, localMusicBean.isCollect());
        }
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.onItemOperateListener
    public void onDeleteClick(LocalMusicBean localMusicBean) {
        ((LocalSDViewModel) this.viewModel).addDeleteMusicList(localMusicBean);
        startConfirmDeleteActivity(((LocalSDViewModel) this.viewModel).localSdUsbMusicDelList);
    }

    public void startConfirmDeleteActivity(List<LocalMusicBean> list) {
        Intent intent = new Intent(getContext(), (Class<?>) DialogActivity.class);
        Bundle bundle = new Bundle();
        MusicPlayHelper.getInstance().deleteMusicList.clear();
        MusicPlayHelper.getInstance().deleteMusicList.addAll(list);
        bundle.putSerializable("musicTag", Integer.valueOf(this.mMusicTag));
        intent.putExtra("localmusiclist_bundle", bundle);
        startActivity(intent);
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.onItemOperateListener
    public void onCollectClick(LocalMusicBean localMusicBean) {
        ((LocalSDViewModel) this.viewModel).collectMusicList(localMusicBean);
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.SelectedGroupListener
    public void onAllSelectedStateChanged(boolean z) {
        ((LocalSDViewModel) this.viewModel).isSelectAll.set(z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        removeCallBack();
        super.onDestroy();
    }

    @Override // com.byd.music.fragment.localMusic.adpter.ExpandableListAdapter.SelectedGroupListener
    public void onSelectedCountChanged(int i) {
        if (((LocalSDViewModel) this.viewModel).getMusicListType() == 4) {
            ((LocalSDViewModel) this.viewModel).localSdUsbMusicDelList.clear();
            ((LocalSDViewModel) this.viewModel).localSdUsbMusicDelList.addAll(this.albumExpandableListAdapter.getMusicSelectedSet());
        } else if (((LocalSDViewModel) this.viewModel).getMusicListType() == 3) {
            ((LocalSDViewModel) this.viewModel).localSdUsbMusicDelList.clear();
            ((LocalSDViewModel) this.viewModel).localSdUsbMusicDelList.addAll(this.artistExpandableListAdapter.getMusicSelectedSet());
        }
    }

    private void addCallBack() {
        MusicPlayHelper.getInstance().isPlayingMaps.addOnMapChangedCallback(this.isPlayingStateMapChangedCallback);
        MusicPlayHelper.getInstance().isPlaying.addOnPropertyChangedCallback(this.onPropertyChangedPlayingCallback);
        MusicPlayHelper.getInstance().currentLocalMusic.addOnPropertyChangedCallback(this.onPropertyChangedCurrentMusicCallback);
        MusicPlayHelper.getInstance().clickCollectFromPlayerSD.addOnPropertyChangedCallback(this.onPropertyClickFromPlayerCallback);
        if (this.mMusicTag == 2) {
            MusicPlayHelper.getInstance().getIsUsbPlug().addOnPropertyChangedCallback(this.onUsbPlugPropertyChangeCallBack);
        }
        if (this.mMusicTag == 1) {
            MusicPlayHelper.getInstance().getIsSDPlug().addOnPropertyChangedCallback(this.onSDPlugPropertyChangeCallBack);
        }
    }

    private void removeCallBack() {
        MusicPlayHelper.getInstance().isPlayingMaps.removeOnMapChangedCallback(this.isPlayingStateMapChangedCallback);
        MusicPlayHelper.getInstance().isPlaying.removeOnPropertyChangedCallback(this.onPropertyChangedPlayingCallback);
        MusicPlayHelper.getInstance().currentLocalMusic.removeOnPropertyChangedCallback(this.onPropertyChangedCurrentMusicCallback);
        MusicPlayHelper.getInstance().clickCollectFromPlayerSD.removeOnPropertyChangedCallback(this.onPropertyClickFromPlayerCallback);
        if (this.mMusicTag == 2) {
            MusicPlayHelper.getInstance().getIsUsbPlug().removeOnPropertyChangedCallback(this.onUsbPlugPropertyChangeCallBack);
        }
        if (this.mMusicTag == 1) {
            MusicPlayHelper.getInstance().getIsSDPlug().removeOnPropertyChangedCallback(this.onSDPlugPropertyChangeCallBack);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMediaControlNavi(MediaControlNaviBean mediaControlNaviBean) {
        checkNavi();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkNavi() {
        /*
            r6 = this;
            boolean r0 = r6.isOnResume
            if (r0 == 0) goto L85
            int r0 = com.byd.music.OnLineMusic.NAVI_TYPE
            r1 = 3
            if (r0 != r1) goto L85
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "checkNavi->NAVI_DEST:"
            r0.append(r1)
            java.lang.String r1 = com.byd.music.OnLineMusic.NAVI_DEST
            r0.append(r1)
            java.lang.String r1 = r6.TAG
            java.lang.String r0 = r0.toString()
            com.byd.music.utils.LogUtil.d(r1, r0)
            java.lang.String r0 = com.byd.music.OnLineMusic.NAVI_DEST
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = -1
            if (r0 != 0) goto L7e
            java.lang.String r0 = com.byd.music.OnLineMusic.NAVI_DEST
            int r2 = r0.hashCode()
            r3 = -1785238953(0xffffffff95976a57, float:-6.115619E-26)
            r4 = 0
            r5 = 1
            if (r2 == r3) goto L47
            r3 = 1798032508(0x6b2bcc7c, float:2.0769197E26)
            if (r2 == r3) goto L3d
            goto L51
        L3d:
            java.lang.String r2 = "all_list"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L51
            r0 = r4
            goto L52
        L47:
            java.lang.String r2 = "favorites"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L51
            r0 = r5
            goto L52
        L51:
            r0 = r1
        L52:
            if (r0 == 0) goto L6b
            if (r0 == r5) goto L57
            goto L7e
        L57:
            V extends androidx.databinding.ViewDataBinding r0 = r6.dataBinding
            com.byd.music.databinding.FragmentLocalSdBinding r0 = (com.byd.music.databinding.FragmentLocalSdBinding) r0
            com.google.android.material.tabs.TabLayout r0 = r0.tabLayout
            V extends androidx.databinding.ViewDataBinding r2 = r6.dataBinding
            com.byd.music.databinding.FragmentLocalSdBinding r2 = (com.byd.music.databinding.FragmentLocalSdBinding) r2
            com.google.android.material.tabs.TabLayout r2 = r2.tabLayout
            com.google.android.material.tabs.TabLayout$Tab r2 = r2.getTabAt(r5)
            r0.selectTab(r2)
            goto L7e
        L6b:
            V extends androidx.databinding.ViewDataBinding r0 = r6.dataBinding
            com.byd.music.databinding.FragmentLocalSdBinding r0 = (com.byd.music.databinding.FragmentLocalSdBinding) r0
            com.google.android.material.tabs.TabLayout r0 = r0.tabLayout
            V extends androidx.databinding.ViewDataBinding r2 = r6.dataBinding
            com.byd.music.databinding.FragmentLocalSdBinding r2 = (com.byd.music.databinding.FragmentLocalSdBinding) r2
            com.google.android.material.tabs.TabLayout r2 = r2.tabLayout
            com.google.android.material.tabs.TabLayout$Tab r2 = r2.getTabAt(r4)
            r0.selectTab(r2)
        L7e:
            com.byd.music.OnLineMusic.NAVI_TYPE = r1
            com.byd.music.OnLineMusic.NAVI_CHILD_SOURCE = r1
            r0 = 0
            com.byd.music.OnLineMusic.NAVI_DEST = r0
        L85:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.fragment.localMusic.ui.LocalSDFragment.checkNavi():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void highLightIndex(int i, int i2) {
        LinearLayoutManager linearLayoutManager;
        int iFindFirstCompletelyVisibleItemPosition;
        LinearLayoutManager linearLayoutManager2;
        int iFindFirstCompletelyVisibleItemPosition2;
        String itemPinyin = null;
        if (i == 1) {
            if (this.dataBinding != 0 && (linearLayoutManager2 = (LinearLayoutManager) ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicList.getLayoutManager()) != null && (iFindFirstCompletelyVisibleItemPosition2 = linearLayoutManager2.findFirstCompletelyVisibleItemPosition()) >= 0 && iFindFirstCompletelyVisibleItemPosition2 < ((LocalSDViewModel) this.viewModel).localSdUsbMusicBeansList.size()) {
                itemPinyin = ((LocalSDViewModel) this.viewModel).localSdUsbMusicBeansList.get(iFindFirstCompletelyVisibleItemPosition2).getItemPinyin();
            }
        } else if (i == 2) {
            if (this.dataBinding != 0 && (linearLayoutManager = (LinearLayoutManager) ((FragmentLocalSdBinding) this.dataBinding).rvLocalMusicListCollect.getLayoutManager()) != null && (iFindFirstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition()) >= 0 && iFindFirstCompletelyVisibleItemPosition < ((LocalSDViewModel) this.viewModel).collectLocalSdUsbMusicBeansList.size()) {
                itemPinyin = ((LocalSDViewModel) this.viewModel).collectLocalSdUsbMusicBeansList.get(iFindFirstCompletelyVisibleItemPosition).getItemPinyin();
            }
        } else if (i == 4) {
            if (i2 < ((LocalSDViewModel) this.viewModel).albumList.size()) {
                itemPinyin = ((LocalSDViewModel) this.viewModel).albumList.get(i2).getItemPinyin();
            }
        } else if (i2 < ((LocalSDViewModel) this.viewModel).artistList.size()) {
            itemPinyin = ((LocalSDViewModel) this.viewModel).artistList.get(i2).getItemPinyin();
        }
        LogUtil.d(this.TAG, "highLightIndex:" + i + " firstVisibleItem=" + i2 + " firstPinyin=" + itemPinyin);
        if (TextUtils.isEmpty(itemPinyin)) {
            return;
        }
        ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.setCurrentPosition(String.valueOf(itemPinyin.charAt(0)));
    }

    @Override // com.byd.music.base.BaseFragment
    protected void handleOnBackPressed() {
        if (((LocalSDViewModel) this.viewModel).isShowOperateUi.get()) {
            disableOperationUI();
        } else {
            super.handleOnBackPressed();
        }
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
        super.onWindowModeChanged(z, i, z2, i2);
        LogUtil.e(this.TAG, "onWindowModeChanged, isMultiWindowMode = " + z + ", deviceOrientation = " + i + " isRight=" + ((LocalSDViewModel) this.viewModel).isRight.get());
        applyViewByOrientation(z, i);
    }

    private void applyViewByOrientation(boolean z, int i) {
        if (z) {
            ((FragmentLocalSdBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f));
            BindingUtils.setConstraintCustom(((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.setLayoutParams(layoutParams);
            ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.setPadding(SizeUtils.dp2px(50.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(50.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).ivLastMusic.getLayoutParams();
            layoutParams2.setMarginEnd(SizeUtils.dp2px(70.0f));
            ((FragmentLocalSdBinding) this.dataBinding).ivLastMusic.setLayoutParams(layoutParams2);
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).ivNextMusic.getLayoutParams();
            layoutParams3.setMarginStart(SizeUtils.dp2px(70.0f));
            ((FragmentLocalSdBinding) this.dataBinding).ivNextMusic.setLayoutParams(layoutParams3);
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.getLayoutParams();
            layoutParams4.width = SizeUtils.dp2px(12.0f);
            layoutParams4.height = SizeUtils.dp2px(330.0f);
            layoutParams4.topMargin = SizeUtils.dp2px(66.0f);
            layoutParams4.rightMargin = SizeUtils.dp2px(24.0f);
            layoutParams4.bottomMargin = SizeUtils.dp2px(26.0f);
            ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.setLayoutParams(layoutParams4);
            BindingUtils.setConstraintCustom(((FragmentLocalSdBinding) this.dataBinding).clMusicMain, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.getLayoutParams();
            layoutParams5.width = -1;
            layoutParams5.height = SizeUtils.dp2px(450.0f);
            layoutParams5.topToTop = -1;
            layoutParams5.startToEnd = -1;
            layoutParams5.startToStart = 0;
            layoutParams5.bottomToBottom = 0;
            layoutParams5.topToBottom = ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getId();
            ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.setLayoutParams(layoutParams5);
            ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(40.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).noSdUsbIv.getLayoutParams();
            layoutParams6.topMargin = SizeUtils.dp2px(57.0f);
            ((FragmentLocalSdBinding) this.dataBinding).noSdUsbIv.setLayoutParams(layoutParams6);
        } else if (i == 2) {
            ((FragmentLocalSdBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(90.0f));
            BindingUtils.setConstraintCustom(((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer, !((LocalSDViewModel) this.viewModel).isRight.get() ? 1 : 0, 0, 0, ((LocalSDViewModel) this.viewModel).isRight.get() ? 1 : 0);
            ConstraintLayout.LayoutParams layoutParams7 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getLayoutParams();
            layoutParams7.width = SizeUtils.dp2px(514.0f);
            layoutParams7.height = -1;
            ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.setLayoutParams(layoutParams7);
            ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(30.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams8 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).ivLastMusic.getLayoutParams();
            layoutParams8.setMarginEnd(SizeUtils.dp2px(55.0f));
            ((FragmentLocalSdBinding) this.dataBinding).ivLastMusic.setLayoutParams(layoutParams8);
            ConstraintLayout.LayoutParams layoutParams9 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).ivNextMusic.getLayoutParams();
            layoutParams9.setMarginStart(SizeUtils.dp2px(55.0f));
            ((FragmentLocalSdBinding) this.dataBinding).ivNextMusic.setLayoutParams(layoutParams9);
            ConstraintLayout.LayoutParams layoutParams10 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.getLayoutParams();
            layoutParams10.width = SizeUtils.dp2px(12.0f);
            layoutParams10.height = SizeUtils.dp2px(330.0f);
            layoutParams10.topMargin = SizeUtils.dp2px(66.0f);
            layoutParams10.rightMargin = SizeUtils.dp2px(24.0f);
            layoutParams10.bottomMargin = SizeUtils.dp2px(18.0f);
            ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.setLayoutParams(layoutParams10);
            BindingUtils.setConstraintCustom(((FragmentLocalSdBinding) this.dataBinding).clMusicMain, 0, ((LocalSDViewModel) this.viewModel).isRight.get() ? 0 : ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getId(), ((LocalSDViewModel) this.viewModel).isRight.get() ? ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getId() : 0, 0);
            ConstraintLayout.LayoutParams layoutParams11 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.getLayoutParams();
            layoutParams11.width = SizeUtils.dp2px(540.0f);
            layoutParams11.height = SizeUtils.dp2px(415.0f);
            layoutParams11.bottomToBottom = -1;
            layoutParams11.topToBottom = -1;
            layoutParams11.topToTop = -1;
            layoutParams11.startToStart = -1;
            ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.setLayoutParams(layoutParams11);
            ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams12 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).noSdUsbIv.getLayoutParams();
            layoutParams12.topMargin = SizeUtils.dp2px(57.0f);
            ((FragmentLocalSdBinding) this.dataBinding).noSdUsbIv.setLayoutParams(layoutParams12);
        } else {
            ((FragmentLocalSdBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(90.0f));
            BindingUtils.setConstraintCustom(((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams13 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getLayoutParams();
            layoutParams13.width = -1;
            layoutParams13.height = -2;
            ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.setLayoutParams(layoutParams13);
            ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.setPadding(SizeUtils.dp2px(50.0f), SizeUtils.dp2px(27.0f), SizeUtils.dp2px(50.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams14 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).ivLastMusic.getLayoutParams();
            layoutParams14.setMarginEnd(SizeUtils.dp2px(70.0f));
            ((FragmentLocalSdBinding) this.dataBinding).ivLastMusic.setLayoutParams(layoutParams14);
            ConstraintLayout.LayoutParams layoutParams15 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).ivNextMusic.getLayoutParams();
            layoutParams15.setMarginStart(SizeUtils.dp2px(70.0f));
            ((FragmentLocalSdBinding) this.dataBinding).ivNextMusic.setLayoutParams(layoutParams15);
            ConstraintLayout.LayoutParams layoutParams16 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.getLayoutParams();
            layoutParams16.width = SizeUtils.dp2px(12.0f);
            layoutParams16.height = SizeUtils.dp2px(330.0f);
            layoutParams16.topMargin = SizeUtils.dp2px(66.0f);
            layoutParams16.rightMargin = SizeUtils.dp2px(24.0f);
            layoutParams16.bottomMargin = SizeUtils.dp2px(16.0f);
            ((FragmentLocalSdBinding) this.dataBinding).slideBarLetter.setLayoutParams(layoutParams16);
            BindingUtils.setConstraintCustom(((FragmentLocalSdBinding) this.dataBinding).clMusicMain, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams17 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.getLayoutParams();
            layoutParams17.width = -1;
            layoutParams17.height = SizeUtils.dp2px(450.0f);
            layoutParams17.topToTop = -1;
            layoutParams17.startToEnd = -1;
            layoutParams17.startToStart = 0;
            layoutParams17.bottomToBottom = 0;
            layoutParams17.topToBottom = ((FragmentLocalSdBinding) this.dataBinding).clMusicInfoContainer.getId();
            ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.setLayoutParams(layoutParams17);
            ((FragmentLocalSdBinding) this.dataBinding).clMusicMain.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(40.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams18 = (ConstraintLayout.LayoutParams) ((FragmentLocalSdBinding) this.dataBinding).noSdUsbIv.getLayoutParams();
            layoutParams18.topMargin = SizeUtils.dp2px(200.0f);
            ((FragmentLocalSdBinding) this.dataBinding).noSdUsbIv.setLayoutParams(layoutParams18);
        }
        switchExpandAdapterByTag();
    }
}
