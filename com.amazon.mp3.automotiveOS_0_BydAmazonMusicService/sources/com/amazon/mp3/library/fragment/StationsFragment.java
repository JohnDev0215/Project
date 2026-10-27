package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.mp3.eux.EUXProvider;
import com.amazon.mp3.fragment.contextmenu.StationContextMenuProvider;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter;
import com.amazon.mp3.library.adapter.StationsAdapter;
import com.amazon.mp3.library.adapter.SubMenuSpinnerAdapter;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.PrimeMusicPagerFragment;
import com.amazon.mp3.prime.station.StationPresenterWrapper;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.prime.station.StationsFactory;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.ForegroundLogger;
import com.amazon.mp3.util.ListViewUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.EndOfPageNotifier;
import com.amazon.mp3.view.HeaderGridView;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.mp3.view.RecentStationsHorizontalListView;
import com.amazon.mp3.view.refinements.ViewLoadingMonitor;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.station.StationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationNotFoundException;
import com.amazon.music.station.model.RefinementItem;
import com.amazon.music.station.model.SectionItem;
import com.amazon.music.station.view.StationListView;
import com.amazon.music.widget.eux.adapters.EUXStationsRecommendationsAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class StationsFragment extends Fragment implements StationListView, PrimeMusicPagerFragment {
    public static final String ARGUMENT_REFINEMENT_SELECTION_KEY;
    private static final String SHARED_PREF_NAME;
    private static final String TAG;
    private StationContextMenuProvider mContextMenuProvider;
    private StationsAdapter mCurrentStationsAdapter;
    private final EndOfPageNotifier mEndOfPageNotifier;
    private boolean mHasBrowseStations;
    private boolean mIsFirstSpinnerCallback;
    private HeaderGridView mListView;
    private LibraryPagerAdapter.OnListViewCreatedListener mListViewCreatedListener;
    private View mLoadingBarView;
    private View mLoadingView;
    private OnPlayStateChangedListener mOnPlayStateChangedListener;
    private final TileAdapter.ItemButtonListener mOverflowButtonListener;
    private boolean mPausedSinceVisible;
    private final PlaybackController mPlaybackController;
    private PreferenceUpdateProvider.PreferenceUpdateReceiver mPreferenceUpdateReceiver;
    private boolean mPresenterStarted;
    private boolean mQueryRecentTaskRunning;
    private View mRefinementSpinnerView;
    private Map<SectionItem, List<RefinementItem>> mRefinements;
    private RetainedPrimeFragment mRetainedPrimeFragment;
    private boolean mSavedIsPlaying;
    private MediaItem mSavedMediaItem;
    private Spinner mSpinner;
    private Station mStation;
    private StationPresenterWrapper mStationPresenterWrapper;
    private StationsFactory mStationsFactory;
    private ViewLoadingMonitor mViewMonitor;
    private final RecentStationsRecyclerViewAdapter.ItemButtonListener recentClickListener;
    private final View.OnCreateContextMenuListener recentStationOnCreateContextMenuListener;
    private final RecentStationsRecyclerViewAdapter.ItemButtonListener recentStationOverflowButtonListener;
    private RecentStationsHorizontalListView stationsHorizontalListView;

    static {
        String simpleName = StationsFragment.class.getSimpleName();
        TAG = simpleName;
        SHARED_PREF_NAME = "com.amazon.mp3_" + StationsFragment.class.getSimpleName();
        ARGUMENT_REFINEMENT_SELECTION_KEY = simpleName + "_key_refinement_selection";
    }

    public static StationsFragment newInstance(final Bundle arguments) {
        StationsFragment stationsFragment = new StationsFragment();
        stationsFragment.setArguments(arguments);
        return stationsFragment;
    }

    public StationsFragment() {
        EndOfPageNotifier endOfPageNotifier = new EndOfPageNotifier() { // from class: com.amazon.mp3.library.fragment.StationsFragment.1
            @Override // com.amazon.mp3.view.EndOfPageNotifier, android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                super.onScrollStateChanged(view, scrollState);
            }
        };
        this.mEndOfPageNotifier = endOfPageNotifier;
        this.mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.2
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public void onPlayStateChanged() {
                if (NowPlayingUtil.isPlayingChanged(StationsFragment.this.mSavedMediaItem, StationsFragment.this.mSavedIsPlaying)) {
                    StationsFragment stationsFragment = StationsFragment.this;
                    stationsFragment.mSavedMediaItem = stationsFragment.mPlaybackController.getCurrentMediaItem();
                    StationsFragment.this.mSavedIsPlaying = PlayNotificationIcon.shouldAnimate();
                    if (StationsFragment.this.mCurrentStationsAdapter != null) {
                        StationsFragment.this.mCurrentStationsAdapter.notifyDataSetChanged();
                    }
                    if (StationsFragment.this.stationsHorizontalListView != null) {
                        StationsFragment.this.stationsHorizontalListView.notifyDataSetChanged();
                    }
                }
            }
        };
        this.mOverflowButtonListener = new TileAdapter.ItemButtonListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.3
            @Override // com.amazon.mp3.library.adapter.TileAdapter.ItemButtonListener
            public void onClick(final View v, final int position) {
                v.performLongClick();
            }
        };
        this.recentStationOverflowButtonListener = new RecentStationsRecyclerViewAdapter.ItemButtonListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.4
            @Override // com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter.ItemButtonListener
            public void onClick(final View v, final int position) {
                v.performLongClick();
            }
        };
        this.recentClickListener = new RecentStationsRecyclerViewAdapter.ItemButtonListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.5
            @Override // com.amazon.mp3.library.adapter.RecentStationsRecyclerViewAdapter.ItemButtonListener
            public void onClick(View view, int position) {
                StationsFragment.this.recentStationsClicked(StationsFragment.this.stationsHorizontalListView.getStation(position), position);
            }
        };
        this.recentStationOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.6
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                int iIntValue = ((Integer) view.getTag()).intValue();
                StationsFragment stationsFragment = StationsFragment.this;
                stationsFragment.mStation = stationsFragment.stationsHorizontalListView.getStation(iIntValue);
                StationsFragment.this.mContextMenuProvider.onCreateContextMenu(StationsFragment.this.getActivity(), menu, StationsFragment.this.mStation, iIntValue, "cirrus");
            }
        };
        endOfPageNotifier.addEndOfPageListener(new EndOfPageNotifier.EndOfPageListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.7
            @Override // com.amazon.mp3.view.EndOfPageNotifier.EndOfPageListener
            public void onEndOfPage() {
                Log.debug(StationsFragment.TAG, "Requesting next page");
                if (StationsFragment.this.mStationPresenterWrapper.requestNextPage()) {
                    StationsFragment.this.mLoadingBarView.setVisibility(0);
                }
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, PageLoadLatencyCode.STATIONS));
        createRetainedFragment();
        initializeStationsPresenter();
        this.mSavedMediaItem = this.mPlaybackController.getCurrentMediaItem();
        this.mSavedIsPlaying = this.mPlaybackController.getPlayStatus().shouldBePlaying();
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeStationsPresenter() {
        StationsFactory stationsFactory = StationsFactory.getInstance(getContext());
        this.mStationsFactory = stationsFactory;
        this.mStationPresenterWrapper = stationsFactory.createStationPresenterWrapper();
        subscribeToStationsFactory(this.mStationsFactory);
    }

    private void subscribeToStationsFactory(final StationsFactory stationsFactory) {
        stationsFactory.registerStationsObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.StationsFragment.8
            @Override // rx.functions.Action1
            public void call(final Object o) {
                StationsFragment.this.initializeStationsPresenter();
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.library.fragment.StationsFragment.9
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(StationsFragment.TAG, "StationPresenterWrapper failed to reinitialize after receiving eventthat StationsFactory should be updated. Continue using existing StationPresenterWrapper instance. " + throwable.getMessage());
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RENDERING, PageLoadLatencyCode.STATIONS));
        View viewInflate = inflater.inflate(R.layout.prime_grid_layout, container, false);
        HeaderGridView headerGridView = (HeaderGridView) viewInflate.findViewById(R.id.list_view);
        this.mListView = headerGridView;
        headerGridView.setVisibility(8);
        this.mListView.setOnScrollListener(this.mEndOfPageNotifier);
        this.stationsHorizontalListView = new RecentStationsHorizontalListView(getContext(), ArtworkSizeUtil.getGridArtworkSize(getActivity()), this.recentStationOverflowButtonListener, this.recentClickListener, this.recentStationOnCreateContextMenuListener);
        View viewFindViewById = viewInflate.findViewById(R.id.loading_spinner);
        this.mLoadingView = viewFindViewById;
        viewFindViewById.setVisibility(0);
        View viewFindViewById2 = viewInflate.findViewById(R.id.ListActivityLoading);
        this.mLoadingBarView = viewFindViewById2;
        ((TextView) viewFindViewById2.findViewById(R.id.ListActivityLoadingText)).setVisibility(8);
        this.mLoadingBarView.setVisibility(8);
        View viewInflate2 = inflater.inflate(R.layout.refinement_spinner, (ViewGroup) this.mListView, false);
        this.mRefinementSpinnerView = viewInflate2;
        viewInflate2.setVisibility(8);
        this.mIsFirstSpinnerCallback = true;
        Spinner spinner = (Spinner) this.mRefinementSpinnerView.findViewById(R.id.refinement_spinner);
        this.mSpinner = spinner;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.10
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(final AdapterView<?> parent) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
                if (!StationsFragment.this.mIsFirstSpinnerCallback) {
                    StationsFragment.this.setRefinement((RefinementItem) parent.getItemAtPosition(position));
                }
                StationsFragment.this.mIsFirstSpinnerCallback = false;
            }
        });
        this.mSpinner.setClickable(true);
        this.mRefinementSpinnerView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                StationsFragment.this.mSpinner.performClick();
            }
        });
        this.mListView.addHeaderView(this.stationsHorizontalListView, null, false, true);
        this.mListView.addHeaderView(this.mRefinementSpinnerView, null, true, true);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.12
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Station station = (Station) parent.getItemAtPosition(position);
                StationsFragment.this.sendUiClickMetric("playStation", station.getKey(), EntityIdType.STATION_KEY, position);
                if (StationsFragment.this.mCurrentStationsAdapter.isEmptyLayoutShowing() || !ConnectivityUtil.checkConnectivityAndShowDialog(StationsFragment.this.getActivity())) {
                    return;
                }
                StationsFragment.this.playStation(station, true, true);
            }
        });
        this.mContextMenuProvider = new StationContextMenuProvider((BaseActivity) getActivity());
        this.mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.13
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(final ContextMenu contextMenu, final View view, final ContextMenu.ContextMenuInfo contextMenuInfo) {
                Station station;
                if (contextMenuInfo instanceof AdapterView.AdapterContextMenuInfo) {
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
                    StationsAdapter.ViewHolder viewHolder = (StationsAdapter.ViewHolder) adapterContextMenuInfo.targetView.getTag();
                    if (viewHolder == null || (station = viewHolder.mStation) == null) {
                        return;
                    }
                    StationsFragment.this.mStation = station;
                    StationsFragment.this.mContextMenuProvider.onCreateContextMenu(StationsFragment.this.getActivity(), contextMenu, StationsFragment.this.mStation, adapterContextMenuInfo.position, "cirrus");
                }
            }
        });
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("com.amazon.mp3.STANDALONE_FRAGMENT_KEY")) {
            FragmentActivity activity = getActivity();
            int i = arguments.getInt("com.amazon.mp3.STANDALONE_FRAGMENT_TITLE_KEY", 0);
            if (i != 0 && (activity instanceof NavigationActivity)) {
                Resources resources = activity.getResources();
                NavigationActivity navigationActivity = (NavigationActivity) activity;
                navigationActivity.setHeaderView(new ActionBarView(getActivity(), resources.getString(i)));
                navigationActivity.requestHomeButtonAsBack();
            }
            this.mPausedSinceVisible = true;
        }
        createViewMonitor();
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.PRIME_MUSIC);
        }
        Map<SectionItem, List<RefinementItem>> map = this.mRefinements;
        if (map != null) {
            onRefinementItemLoaded(map);
        }
        if (this.mPresenterStarted) {
            this.mStationPresenterWrapper.resume(this, getContext());
        } else {
            this.mStationPresenterWrapper.start(this, getContext());
            this.mPresenterStarted = true;
        }
        if (this.mPausedSinceVisible) {
            sendUiPageViewEvent();
            this.mPausedSinceVisible = false;
        }
        queryRecentStations();
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.STATIONS));
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(final Context context) {
        super.onAttach(context);
        registerLanguagePreferenceUpdateReceiver();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        PreferenceUpdateProvider.deregisterReceiver(this.mPreferenceUpdateReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiClickMetric(final String identifier, final String entityId, final EntityIdType entityIdType, final int position) {
        UserInteractionAppEvent.builder(identifier).withEntityId(entityId).withEntityIdType(entityIdType).withInteractionType(InteractionType.TAP).withEntityPos(position).publish();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(final boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ForegroundLogger.log(this);
            sendUiPageViewEvent();
        } else {
            this.mPausedSinceVisible = false;
        }
    }

    private void sendUiPageViewEvent() {
        String string;
        Bundle arguments = getArguments();
        String string2 = null;
        if (!StringUtils.isEmpty(arguments.getString("com.amazon.mp3.StationsFragment.extra.STATION_KEY"))) {
            string = null;
        } else {
            DeeplinkIntentKey deeplinkIntentKey = DeeplinkIntentKey.KEY_REF_MARKER;
            string2 = arguments.getString(deeplinkIntentKey.toString());
            DeeplinkIntentKey deeplinkIntentKey2 = DeeplinkIntentKey.KEY_ASSOCIATE_TAG;
            string = arguments.getString(deeplinkIntentKey2.toString());
            arguments.remove(deeplinkIntentKey.toString());
            arguments.remove(deeplinkIntentKey2.toString());
        }
        NavigationAppEvent.builder(PageType.BROWSE_STATIONS.getMetricsValue()).withRefMarker(string2).withAssociateTag(string).withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        this.mStationPresenterWrapper.pause();
        Object selectedItem = this.mSpinner.getSelectedItem();
        if (selectedItem != null && (selectedItem instanceof RefinementItem)) {
            SharedPreferences.Editor editorEdit = getActivity().getSharedPreferences(SHARED_PREF_NAME, 0).edit();
            editorEdit.putString("key_refinement_selection", ((RefinementItem) selectedItem).getId());
            editorEdit.putInt("key_scroll_position", this.mListView.getFirstVisiblePosition());
            editorEdit.apply();
            Log.debug(TAG, "Scroll position saved as %d", Integer.valueOf(this.mListView.getFirstVisiblePosition()));
        }
        this.mPausedSinceVisible = getUserVisibleHint();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mPresenterStarted = false;
        this.mViewMonitor.deregisterAllViews();
        this.mViewMonitor.setOnViewsLoadingListener(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (this.mStationPresenterWrapper != null) {
            this.mRetainedPrimeFragment.getMap().put("Recommended Stations", this.mStationPresenterWrapper);
        }
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        if (!getUserVisibleHint()) {
            return false;
        }
        super.onContextItemSelected(item);
        if (this.mStation != null) {
            return this.mContextMenuProvider.onContextMenuItemSelected(getActivity(), item, this.mStation, "cirrus");
        }
        return false;
    }

    @Override // com.amazon.music.station.view.StationListView
    public void onRefinementItemLoaded(final Map<SectionItem, List<RefinementItem>> refinementMap) {
        RefinementItem spinner;
        if (getActivity() == null) {
            return;
        }
        this.mRefinements = refinementMap;
        if (refinementMap.isEmpty()) {
            Log.error(TAG, "No refinements were loaded");
            this.mHasBrowseStations = false;
            showEmptyView(getNoBrowseStationsView());
            this.mViewMonitor.notifyAllViewsLoaded();
            return;
        }
        this.mHasBrowseStations = true;
        RefinementItemAdapter refinementItemAdapterCreateRefinementAdapter = createRefinementAdapter(refinementMap);
        this.mSpinner.setAdapter((SpinnerAdapter) refinementItemAdapterCreateRefinementAdapter);
        this.mViewMonitor.notifyViewState(this.mRefinementSpinnerView, ViewLoadingMonitor.ViewState.LOADED);
        Bundle arguments = getArguments();
        if (arguments.getString("com.amazon.mp3.StationsFragment.extra.STATION_SEED_ID") != null) {
            arguments.remove("com.amazon.mp3.StationsFragment.extra.CATEGORY_ID");
            spinner = setSpinner(this.mSpinner, "allStations");
            if (spinner == null) {
                spinner = (RefinementItem) refinementItemAdapterCreateRefinementAdapter.getItem(0);
            }
        } else {
            spinner = null;
        }
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, 0);
        if (spinner == null) {
            String string = arguments.getString("com.amazon.mp3.StationsFragment.extra.CATEGORY_ID");
            if (string != null) {
                arguments.remove("com.amazon.mp3.StationsFragment.extra.CATEGORY_ID");
                spinner = setSpinner(this.mSpinner, string);
            } else {
                String str = ARGUMENT_REFINEMENT_SELECTION_KEY;
                String string2 = arguments.getString(str);
                if (string2 == null) {
                    string2 = sharedPreferences.getString("key_refinement_selection", null);
                } else {
                    arguments.putString(str, null);
                    sharedPreferences.edit().putString("key_refinement_selection", string2).apply();
                }
                if (string2 != null) {
                    spinner = setSpinner(this.mSpinner, string2);
                }
            }
        }
        if (spinner == null) {
            spinner = setSpinner(this.mSpinner, "allStations");
        }
        if (spinner != null) {
            setRefinement(spinner);
        }
    }

    private List<Station> toStations(List<StationItem> stationItems) {
        if (stationItems != null) {
            ArrayList arrayList = new ArrayList(stationItems.size());
            Iterator<StationItem> it = stationItems.iterator();
            while (it.hasNext()) {
                arrayList.add(PrimeItemsTransformationUtil.toPrimeStation(it.next()));
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    @Override // com.amazon.music.station.view.StationListView
    public void onStationItemsLoaded(final RefinementItem refinementItem, final List<StationItem> stationItems) {
        List<Station> stations = toStations(stationItems);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        boolean z = this.mCurrentStationsAdapter == null;
        if ("Recommended Stations".equals(refinementItem.getId()) && stations.isEmpty()) {
            showEmptyView(getNoRecommendedStationsView());
        } else {
            if (z) {
                this.mCurrentStationsAdapter = new StationsAdapter(activity, ArtworkSizeUtil.getGridArtworkSize(activity), false, this.mOverflowButtonListener);
            }
            this.mCurrentStationsAdapter.setNotifyOnChange(false);
            this.mCurrentStationsAdapter.clear();
            this.mCurrentStationsAdapter.addAll(stations);
            this.mCurrentStationsAdapter.notifyDataSetChanged();
        }
        this.mViewMonitor.notifyViewState(this.mListView, ViewLoadingMonitor.ViewState.LOADED);
        this.mLoadingView.setVisibility(8);
        this.mLoadingBarView.setVisibility(8);
        if (z) {
            ListViewUtil.stopScroll(this.mListView);
            this.mListView.setAdapter((ListAdapter) this.mCurrentStationsAdapter);
            SharedPreferences sharedPreferences = activity.getSharedPreferences(SHARED_PREF_NAME, 0);
            String string = sharedPreferences.getString("key_refinement_selection", null);
            int i = sharedPreferences.getInt("key_scroll_position", 0);
            if (refinementItem.getId().equals(string) && this.mListView.getCount() > i) {
                this.mListView.setSelection(i);
            }
            LibraryPagerAdapter.OnListViewCreatedListener onListViewCreatedListener = this.mListViewCreatedListener;
            if (onListViewCreatedListener != null) {
                onListViewCreatedListener.onListViewCreated(getListView());
            }
            processStationIntent(stations);
        }
    }

    private void showEmptyView(final View emptyView) {
        this.mCurrentStationsAdapter = new StationsAdapter(getActivity(), emptyView);
        ListViewUtil.stopScroll(this.mListView);
        this.mListView.setAdapter((ListAdapter) this.mCurrentStationsAdapter);
    }

    private View getNoBrowseStationsView() {
        View viewInflate = View.inflate(getContext(), R.layout.prime_search_nothing_found_layout, null);
        ((TextView) viewInflate.findViewById(R.id.search_no_results)).setText(getString(R.string.dmusic_prime_search_nothing_found, getString(R.string.dmusic_library_stations_tab)));
        viewInflate.setVisibility(0);
        return viewInflate;
    }

    private View getNoRecommendedStationsView() {
        return new EUXStationsRecommendationsAdapter(getContext()).getView(EUXProvider.buildEUXConfiguration(getContext()));
    }

    private void processStationIntent(List<Station> stations) {
        Bundle arguments = getArguments();
        String string = arguments.getString("com.amazon.mp3.StationsFragment.extra.STATION_SEED_ID");
        String string2 = arguments.getString("com.amazon.mp3.StationsFragment.extra.STATION_KEY");
        if (string != null) {
            try {
                string2 = this.mStationsFactory.getStationManager().getStationItemBySeedId(string).getKey();
            } catch (StationException | StationNotFoundException unused) {
            }
        }
        if (string2 != null) {
            for (int i = 0; i < stations.size(); i++) {
                Station station = stations.get(i);
                if (string2.equals(station.getKey())) {
                    navigateToAndPlayStation(station, i);
                    return;
                }
            }
        }
    }

    private void navigateToAndPlayStation(Station station, int stationNo) {
        this.mListView.setSelection(stationNo);
        Bundle arguments = getArguments();
        String string = arguments.getString("com.amazon.mp3.StationsFragment.extra.ACTION");
        arguments.remove("com.amazon.mp3.StationsFragment.extra.ACTION");
        boolean z = "playFullScreen".equals(string) || "playNowPlaying".equals(string);
        playStation(station, z || PageUriUtils.DO_PLAY_VALUE.equals(string), z);
    }

    @Override // com.amazon.music.station.view.StationListView
    public void onNetworkError(final StationException e) {
        if (getActivity() == null) {
            return;
        }
        ViewLoadingMonitor viewLoadingMonitor = this.mViewMonitor;
        HeaderGridView headerGridView = this.mListView;
        ViewLoadingMonitor.ViewState viewState = ViewLoadingMonitor.ViewState.ERROR;
        viewLoadingMonitor.notifyViewState(headerGridView, viewState);
        this.mViewMonitor.notifyViewState(this.mRefinementSpinnerView, viewState);
        Log.error(TAG, "Network error: " + e.getLocalizedMessage());
    }

    @Override // com.amazon.music.station.view.StationListView
    public void onProcessingError(final StationException e) {
        Log.error(TAG, "Processing error", e);
        if (getActivity() == null) {
            return;
        }
        ViewLoadingMonitor viewLoadingMonitor = this.mViewMonitor;
        HeaderGridView headerGridView = this.mListView;
        ViewLoadingMonitor.ViewState viewState = ViewLoadingMonitor.ViewState.ERROR;
        viewLoadingMonitor.notifyViewState(headerGridView, viewState);
        this.mViewMonitor.notifyViewState(this.mRefinementSpinnerView, viewState);
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setOnListViewCreatedListener(LibraryPagerAdapter.OnListViewCreatedListener listener) {
        this.mListViewCreatedListener = listener;
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
        HeaderGridView headerGridView = this.mListView;
        if (headerGridView != null) {
            headerGridView.setOnTouchListener(listener);
        }
    }

    @Override // com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void scrollToTop() {
        this.mListView.smoothScrollToPositionFromTop(0, 0);
    }

    public final AbsListView getListView() {
        return this.mListView;
    }

    private void createViewMonitor() {
        ViewLoadingMonitor viewLoadingMonitor = new ViewLoadingMonitor();
        this.mViewMonitor = viewLoadingMonitor;
        viewLoadingMonitor.setOnViewsLoadingListener(new ViewLoadingMonitor.OnViewsLoadingListener() { // from class: com.amazon.mp3.library.fragment.StationsFragment.14
            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onViewsLoading() {
                StationsFragment.this.mLoadingBarView.setVisibility(0);
                StationsFragment.this.mLoadingView.setVisibility(0);
                StationsFragment.this.mListView.setVisibility(8);
                StationsFragment.this.mRefinementSpinnerView.setVisibility(8);
                StationsFragment.this.stationsHorizontalListView.setRecentStationsVisibility(8);
            }

            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onAllViewsLoaded() {
                StationsFragment.this.mLoadingBarView.setVisibility(8);
                StationsFragment.this.mLoadingView.setVisibility(8);
                StationsFragment.this.mListView.setVisibility(0);
                if (StationsFragment.this.mHasBrowseStations) {
                    StationsFragment.this.mRefinementSpinnerView.setVisibility(0);
                    StationsFragment.this.stationsHorizontalListView.setRecentStationsVisibility(0);
                }
            }

            @Override // com.amazon.mp3.view.refinements.ViewLoadingMonitor.OnViewsLoadingListener
            public void onViewsError() {
                if (StationsFragment.this.getActivity() == null) {
                    return;
                }
                StationsFragment.this.mLoadingBarView.setVisibility(8);
                StationsFragment.this.mLoadingView.setVisibility(8);
                StationsFragment.this.mRefinementSpinnerView.setVisibility(8);
                StationsFragment.this.stationsHorizontalListView.setRecentStationsVisibility(8);
                StationsFragment.this.mListView.setVisibility(0);
                StationsFragment stationsFragment = StationsFragment.this;
                stationsFragment.showEmptyScreen(stationsFragment.getActivity(), R.layout.prime_service_error_layout, true);
            }
        });
        this.mViewMonitor.registerView(this.mRefinementSpinnerView);
        this.mViewMonitor.registerView(this.stationsHorizontalListView);
        this.mViewMonitor.registerView(this.mListView);
    }

    protected void onRecentStationsLoaded(List<Station> recentStations) {
        if (getActivity() == null) {
            return;
        }
        if (!recentStations.isEmpty()) {
            this.stationsHorizontalListView.setStations(recentStations);
        }
        this.mViewMonitor.notifyViewState(this.stationsHorizontalListView, ViewLoadingMonitor.ViewState.LOADED);
    }

    private void queryRecentStations() {
        this.mViewMonitor.notifyViewState(this.stationsHorizontalListView, ViewLoadingMonitor.ViewState.LOADING);
        if (this.mQueryRecentTaskRunning) {
            return;
        }
        this.mQueryRecentTaskRunning = true;
        new RecentStationsCursorLoadTask(getActivity()).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefinement(RefinementItem refinementItem) {
        ListViewUtil.stopScroll(this.mListView);
        this.mListView.setAdapter((ListAdapter) null);
        this.mLoadingView.setVisibility(0);
        this.mCurrentStationsAdapter = null;
        this.mStationPresenterWrapper.setRefinement(refinementItem);
    }

    private void createRetainedFragment() {
        String simpleName = getClass().getSimpleName();
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        RetainedPrimeFragment retainedPrimeFragment = (RetainedPrimeFragment) supportFragmentManager.findFragmentByTag(simpleName);
        this.mRetainedPrimeFragment = retainedPrimeFragment;
        if (retainedPrimeFragment == null) {
            this.mRetainedPrimeFragment = new RetainedPrimeFragment();
            supportFragmentManager.beginTransaction().add(this.mRetainedPrimeFragment, simpleName).commitAllowingStateLoss();
        }
    }

    private static RefinementItem setSpinner(Spinner spinner, String selectedRefinement) {
        for (int i = 0; i < spinner.getCount(); i++) {
            Object itemAtPosition = spinner.getItemAtPosition(i);
            if (itemAtPosition instanceof RefinementItem) {
                RefinementItem refinementItem = (RefinementItem) itemAtPosition;
                if (refinementItem.getId().equals(selectedRefinement)) {
                    spinner.setSelection(i);
                    return refinementItem;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playStation(Station station, boolean startPlayback, boolean startFullscreen) {
        String str;
        String str2;
        ContentUnavailableReason playCatalogContentUnavailableReason = ContentAccessUtil.getPlayCatalogContentUnavailableReason(station);
        if (playCatalogContentUnavailableReason == null) {
            Bundle arguments = getArguments();
            if (arguments != null) {
                DeeplinkIntentKey deeplinkIntentKey = DeeplinkIntentKey.KEY_REF_MARKER;
                String string = arguments.getString(deeplinkIntentKey.toString());
                DeeplinkIntentKey deeplinkIntentKey2 = DeeplinkIntentKey.KEY_ASSOCIATE_TAG;
                String string2 = arguments.getString(deeplinkIntentKey2.toString());
                arguments.remove(deeplinkIntentKey.toString());
                arguments.remove(deeplinkIntentKey2.toString());
                str2 = string2;
                str = string;
            } else {
                str = null;
                str2 = null;
            }
            StationUtils.playStation(getActivity(), station, startPlayback, startFullscreen, PlaybackPageType.PRIME_BROWSE_STATIONS_LIST, str, str2);
            return;
        }
        DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(getContext(), playCatalogContentUnavailableReason, null, station.getItemTypeName(), station, ContentAccessUtil.ContentAccessOperation.STREAM);
        if (dialogForDisabledReason != null) {
            dialogForDisabledReason.show(getFragmentManager(), (String) null);
        }
    }

    @VisibleForTesting(otherwise = 2)
    protected void removeRecommendationRefinement(final List<RefinementItem> entryValues) {
        ListIterator<RefinementItem> listIterator = entryValues.listIterator();
        while (listIterator.hasNext()) {
            if ("Recommended Stations".equals(listIterator.next().getId())) {
                listIterator.remove();
                return;
            }
        }
    }

    private RefinementItemAdapter createRefinementAdapter(final Map<SectionItem, List<RefinementItem>> refinementMap) {
        FragmentActivity activity = getActivity();
        RefinementItemAdapter refinementItemAdapter = new RefinementItemAdapter(activity, R.layout.refinement_spinner_item, R.layout.refinement_spinner_dropdown_item, R.id.refinement_item_textview, R.layout.refinement_spinner_submenu_item, R.id.refinement_submenu_text);
        Resources resources = activity.getResources();
        List<RefinementItem> value = null;
        for (Map.Entry<SectionItem, List<RefinementItem>> entry : refinementMap.entrySet()) {
            if ("genres".equals(entry.getKey().getId())) {
                value = entry.getValue();
            } else {
                if (!AmazonApplication.getCapabilities().shouldShowRecommendations()) {
                    removeRecommendationRefinement(entry.getValue());
                }
                Iterator<RefinementItem> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    refinementItemAdapter.add(it.next());
                }
            }
        }
        if (value != null) {
            refinementItemAdapter.addSection(resources.getString(R.string.dmusic_genres_refinement), value);
        }
        return refinementItemAdapter;
    }

    private static class RefinementItemAdapter extends SubMenuSpinnerAdapter<RefinementItem> {
        public RefinementItemAdapter(Context context, int itemLayoutId, int dropdownItemLayoutId, int itemViewId, int submenuHeaderLayoutId, int submenuHeaderViewId) {
            super(context, itemLayoutId, dropdownItemLayoutId, itemViewId, submenuHeaderLayoutId, submenuHeaderViewId);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.mp3.library.adapter.SubMenuSpinnerAdapter
        public String getTextForItem(final RefinementItem item) {
            return item.getTitle();
        }
    }

    private class RecentStationsCursorLoadTask extends AsyncTask<Void, Void, List<Station>> {
        private Context mContext;

        public RecentStationsCursorLoadTask(Context context) {
            this.mContext = context;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<Station> doInBackground(final Void... params) {
            List<RecentlyPlayedItem> listQueryRecentStations = RecentlyPlayedManager.getInstance(this.mContext).queryRecentStations();
            if (listQueryRecentStations != null && !listQueryRecentStations.isEmpty()) {
                return toStationItemList(listQueryRecentStations);
            }
            return Collections.emptyList();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final List<Station> recentStations) {
            StationsFragment.this.onRecentStationsLoaded(recentStations);
            StationsFragment.this.mQueryRecentTaskRunning = false;
        }

        private List<Station> toStationItemList(final List<RecentlyPlayedItem> recentlyPlayedItems) {
            ArrayList arrayList = new ArrayList(recentlyPlayedItems.size());
            Iterator<RecentlyPlayedItem> it = recentlyPlayedItems.iterator();
            while (it.hasNext()) {
                try {
                    Station stationItem = toStationItem(it.next());
                    if (stationItem != null) {
                        arrayList.add(stationItem);
                    }
                } catch (JSONException e) {
                    Log.warning(StationsFragment.TAG, "Failed to parse station from recents: %s", StringUtils.defaultString(e.getMessage()));
                }
            }
            return arrayList;
        }

        private Station toStationItem(final RecentlyPlayedItem recentlyPlayedItem) throws JSONException {
            StationItem stationItemFromRemoteRecent;
            if (recentlyPlayedItem.getTrackProviderState() != null) {
                stationItemFromRemoteRecent = StationStorageUtil.fromJson(RecentlyPlayedManager.getProviderInfo(new JSONObject(recentlyPlayedItem.getTrackProviderState())));
            } else if (!recentlyPlayedItem.isRemote()) {
                Log.error(StationsFragment.TAG, "TrackProvider json is null for item " + recentlyPlayedItem);
                stationItemFromRemoteRecent = null;
            } else {
                stationItemFromRemoteRecent = StationStorageUtil.fromRemoteRecent(recentlyPlayedItem);
            }
            if (stationItemFromRemoteRecent == null) {
                return null;
            }
            return PrimeItemsTransformationUtil.toPrimeStation(stationItemFromRemoteRecent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyScreen(final Context context, final int layoutId, final boolean hideRecentStations) {
        this.mCurrentStationsAdapter = new StationsAdapter(context, layoutId);
        ListViewUtil.stopScroll(this.mListView);
        this.mListView.setAdapter((ListAdapter) this.mCurrentStationsAdapter);
        if (hideRecentStations) {
            this.stationsHorizontalListView.setRecentStationsVisibility(8);
        }
    }

    private void registerLanguagePreferenceUpdateReceiver() {
        PreferenceUpdateProvider.PreferenceUpdateReceiver preferenceUpdateReceiver = new PreferenceUpdateProvider.PreferenceUpdateReceiver() { // from class: com.amazon.mp3.library.fragment.StationsFragment.15
            @Override // com.amazon.mp3.configuration.PreferenceUpdateProvider.PreferenceUpdateReceiver
            public void onReceive() {
                if (StationsFragment.this.mStationPresenterWrapper != null) {
                    StationsFragment.this.mStationPresenterWrapper.refreshData();
                }
            }
        };
        this.mPreferenceUpdateReceiver = preferenceUpdateReceiver;
        PreferenceUpdateProvider.registerReceiver(preferenceUpdateReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recentStationsClicked(@Nullable final Station station, final int position) {
        if (station == null) {
            return;
        }
        if (!TextUtils.isEmpty(station.getSFASeed())) {
            StationUtils.sendStartSFAUiClickMetrics(station.getSFASeed(), com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.SFA_STATION_SEED_ID);
        } else {
            sendUiClickMetric("playStation", station.getKey(), EntityIdType.STATION_KEY, position);
        }
        if (this.stationsHorizontalListView.getVisibility() == 0 && ConnectivityUtil.checkConnectivityAndShowDialog(getActivity())) {
            if (!TextUtils.isEmpty(station.getSFASeed())) {
                StationUtils.startSFA(getContext(), station.getSFASeed(), "STATION_SEED_ID", PlaybackPageType.RECENTLY_PLAYED, ControlSource.APP_UI);
            } else {
                playStation(station, true, true);
            }
        }
    }
}
