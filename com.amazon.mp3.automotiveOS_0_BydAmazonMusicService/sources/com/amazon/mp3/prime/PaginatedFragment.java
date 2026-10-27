package com.amazon.mp3.prime;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.fragment.NetworkErrorDialogHandler;
import com.amazon.mp3.library.adapter.TileAdapter;
import com.amazon.mp3.library.fragment.RetainedPrimeFragment;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.pager.PagerProvider;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.browse.pager.PaginatedRankType;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.EndOfPageNotifier;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.pager.Pager;
import com.amazon.music.pager.PagerObserver;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PaginatedFragment<D extends SearchItem & CatalogContent, T extends ArrayAdapter<D>, S extends StateProvider<D>> extends Fragment implements ContentAccessUtil.CatalogActionListener<D> {
    protected static final String TAG = PaginatedFragment.class.getSimpleName();
    private NavigationActivity activity;
    protected T mAdapter;
    protected String mAsin;
    protected RetainedPrimeFragment mCache;
    private int mClickedPosition;
    protected Collection<D> mData;
    protected PaginatedDataType mDataType;
    private View mEmptyLayout;
    protected EndOfPageNotifier.EndOfPageListener mEndOfPageListener;
    protected String mHeaderText;
    protected AbsListView mListView;
    protected View mLoadingBarView;
    protected View mLoadingView;
    protected NetworkErrorDialogHandler mNetworkErrorHandler;
    protected Pager mPager;
    protected PagerProvider mPagerProvider;
    protected PaginatedRankType mRankType;
    protected String mRefinementId;
    private boolean mSavedIsPlaying;
    private MediaItem mSavedMediaItem;
    protected S mStateProvider;
    protected String mWidgetId;
    protected EndOfPageNotifier mEndOfPageNotifier = new EndOfPageNotifier();
    private final PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private OnPlayStateChangedListener mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.prime.PaginatedFragment.1
        @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
        public void onPlayStateChanged() {
            if (NowPlayingUtil.isPlayingChanged(PaginatedFragment.this.mSavedMediaItem, PaginatedFragment.this.mSavedIsPlaying)) {
                PaginatedFragment paginatedFragment = PaginatedFragment.this;
                paginatedFragment.mSavedMediaItem = paginatedFragment.mPlaybackController.getCurrentMediaItem();
                PaginatedFragment.this.mSavedIsPlaying = PlayNotificationIcon.shouldAnimate();
                PaginatedFragment.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    protected final TileAdapter.ItemButtonListener mOverflowListener = new TileAdapter.ItemButtonListener() { // from class: com.amazon.mp3.prime.PaginatedFragment.2
        @Override // com.amazon.mp3.library.adapter.TileAdapter.ItemButtonListener
        public void onClick(View view, int position) {
            view.performLongClick();
        }
    };
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.prime.PaginatedFragment.3
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
                PaginatedFragment.this.refreshOnUiThread();
            }
        }
    };

    protected abstract T createAdapter();

    protected abstract void createContextMenuProvider();

    protected abstract PagerProvider createPagerProvider();

    protected abstract S createStateProvider();

    protected abstract int getLayoutResourceId();

    protected abstract View.OnCreateContextMenuListener getOnCreateContextMenuListener();

    protected abstract void handleOnClick(D item, final int position);

    protected abstract List<D> transformItemsFromResponse(final List items);

    protected abstract void updateAdapterOnUiThread(final List<D> items);

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mRankType = (PaginatedRankType) getArguments().getSerializable("RANK_TYPE_KEY");
        this.mRefinementId = getArguments().getString("REFINEMENT_KEY");
        this.mHeaderText = getArguments().getString("HEADER_TEXT_KEY");
        this.mAsin = getArguments().getString("PAGINATED_FRAGMENT_ASIN_KEY", null);
        this.mWidgetId = getArguments().getString("PAGINATED_FRAGMENT_WIDGET_ID_KEY", null);
        PagerProvider pagerProviderCreatePagerProvider = createPagerProvider();
        this.mPagerProvider = pagerProviderCreatePagerProvider;
        this.mDataType = pagerProviderCreatePagerProvider.getDataType();
        RetainedPrimeFragment retainedFragment = RetainedPrimeFragment.getRetainedFragment(getActivity().getSupportFragmentManager(), createFragmentKey());
        this.mCache = retainedFragment;
        this.mPager = (Pager) retainedFragment.read("PAGER");
        sendUiPageViewMetric();
        String str = (String) this.mCache.read("REFINEMENT_KEY", "empty_filter");
        if (this.mPager == null || !str.equals(this.mRefinementId)) {
            this.mPager = createPager();
        }
        subscribePager();
        S s = (S) this.mCache.read("STATE_PROVIDER");
        this.mStateProvider = s;
        if (s == null) {
            this.mStateProvider = (S) createStateProvider();
        }
        this.mData = null;
        if (str.equals(this.mRefinementId)) {
            this.mData = (Collection) this.mCache.read("ADAPTER_DATA");
        }
        T t = (T) createAdapter();
        this.mAdapter = t;
        Collection<D> collection = this.mData;
        if (collection != null) {
            t.addAll(collection);
        }
        this.mSavedMediaItem = this.mPlaybackController.getCurrentMediaItem();
        this.mSavedIsPlaying = this.mPlaybackController.getPlayStatus().shouldBePlaying();
        this.mPlaybackController.addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        this.activity = getActivity() instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) getActivity() : (MusicHomeActivity) getActivity();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(getLayoutResourceId(), container, false);
        AbsListView absListView = (AbsListView) viewInflate.findViewById(R.id.list_view);
        this.mListView = absListView;
        absListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(getOnItemClickListener());
        this.mListView.setVisibility(8);
        View viewFindViewById = viewInflate.findViewById(R.id.empty_layout);
        this.mEmptyLayout = viewFindViewById;
        viewFindViewById.setVisibility(8);
        View viewFindViewById2 = viewInflate.findViewById(R.id.loading_spinner);
        this.mLoadingView = viewFindViewById2;
        viewFindViewById2.setVisibility(0);
        this.activity.requestHomeButtonAsBack();
        createContextMenuProvider();
        this.mListView.setOnCreateContextMenuListener(getOnCreateContextMenuListener());
        subscribeToEndOfPage();
        setupLoadingView(viewInflate);
        if (this.mAdapter.getCount() <= 0) {
            this.mPager.next();
        } else {
            showLoadedListView();
        }
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.activity.setHeaderView(new ActionBarView(getActivity(), this.mHeaderText));
        this.activity.requestHomeButtonAsBack();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mNetworkErrorHandler = new NetworkErrorDialogHandler(this.activity);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mPager.unsubscribe();
        this.mEndOfPageNotifier.removeEndOfPageListener(this.mEndOfPageListener);
        this.mCache.save("PAGER", this.mPager);
        this.mCache.save("STATE_PROVIDER", this.mStateProvider);
        this.mCache.save("ADAPTER_DATA", getCurrentAdapterData());
        this.mCache.save("REFINEMENT_KEY", this.mRefinementId);
        this.mPlaybackController.removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.PaginatedFragment$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType;

        static {
            int[] iArr = new int[PaginatedDataType.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType = iArr;
            try {
                iArr[PaginatedDataType.ALBUM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.TRACK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[PaginatedDataType.STATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static String getHeaderStringForDataType(Context context, PaginatedDataType dataType) {
        int i = AnonymousClass10.$SwitchMap$com$amazon$mp3$prime$browse$pager$PaginatedDataType[dataType.ordinal()];
        if (i == 1) {
            return context.getResources().getString(R.string.dmusic_recommended_prime_albums_card);
        }
        if (i == 2) {
            return context.getResources().getString(R.string.dmusic_recommended_prime_songs_card);
        }
        if (i == 3) {
            return context.getResources().getString(R.string.dmusic_recommended_prime_playlists_card);
        }
        if (i == 4) {
            return context.getResources().getString(R.string.dmusic_recommended_prime_stations_card);
        }
        throw new IllegalArgumentException("Incorrect dataType passed in");
    }

    protected String createFragmentKey() {
        return TAG + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + this.mRankType.getValue() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + this.mDataType.getValue() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + this.mRefinementId;
    }

    private void subscribeToEndOfPage() {
        this.mListView.setOnScrollListener(this.mEndOfPageNotifier);
        EndOfPageNotifier.EndOfPageListener endOfPageListener = new EndOfPageNotifier.EndOfPageListener() { // from class: com.amazon.mp3.prime.PaginatedFragment.4
            @Override // com.amazon.mp3.view.EndOfPageNotifier.EndOfPageListener
            public void onEndOfPage() {
                PaginatedFragment.this.mLoadingBarView.setVisibility(0);
                PaginatedFragment.this.mPager.next();
            }
        };
        this.mEndOfPageListener = endOfPageListener;
        this.mEndOfPageNotifier.addEndOfPageListener(endOfPageListener);
    }

    protected Pager createPager() {
        return this.mPagerProvider.createPager(getContext());
    }

    protected Collection<D> getCurrentAdapterData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mAdapter.getCount(); i++) {
            arrayList.add((SearchItem) this.mAdapter.getItem(i));
        }
        return arrayList;
    }

    private void subscribePager() {
        this.mPager.subscribe(new PagerObserver<List>() { // from class: com.amazon.mp3.prime.PaginatedFragment.5
            @Override // com.amazon.music.pager.PagerObserver
            public void onNext(List items) {
                PaginatedFragment.this.showLoadedListView();
                PaginatedFragment.this.updateAdapterOnUiThread(PaginatedFragment.this.transformItemsFromResponse(items));
            }

            @Override // com.amazon.music.pager.PagerObserver
            public void onError(Throwable throwable) {
                PaginatedFragment.this.showEmptyView();
            }

            @Override // com.amazon.music.pager.PagerObserver
            public void onCompleted() {
                PaginatedFragment.this.mLoadingBarView.setVisibility(8);
            }
        });
    }

    private AdapterView.OnItemClickListener getOnItemClickListener() {
        return new AdapterView.OnItemClickListener() { // from class: com.amazon.mp3.prime.PaginatedFragment.6
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SearchItem searchItem = (SearchItem) parent.getItemAtPosition(position);
                PaginatedFragment.this.mClickedPosition = position;
                if (PaginatedFragment.this.canViewItemDetail(searchItem)) {
                    PaginatedFragment.this.handleOnClick(searchItem, position);
                } else {
                    ContentAccessUtil.checkCanContinueCatalogAction(PaginatedFragment.this.getActivity(), searchItem, ContentAccessUtil.ContentAccessOperation.STREAM, PaginatedFragment.this);
                }
            }
        };
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(final D item, final boolean canContinue) {
        if (canContinue) {
            handleOnClick(item, this.mClickedPosition);
        }
    }

    private void setupLoadingView(View parent) {
        View viewFindViewById = parent.findViewById(R.id.ListActivityLoading);
        this.mLoadingBarView = viewFindViewById;
        ((TextView) viewFindViewById.findViewById(R.id.ListActivityLoadingText)).setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLoadedListView() {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.PaginatedFragment.7
            @Override // java.lang.Runnable
            public void run() {
                PaginatedFragment.this.mLoadingView.setVisibility(8);
                PaginatedFragment.this.mLoadingBarView.setVisibility(8);
                PaginatedFragment.this.mListView.setVisibility(0);
                PaginatedFragment.this.mEmptyLayout.setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyView() {
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.PaginatedFragment.8
            @Override // java.lang.Runnable
            public void run() {
                PaginatedFragment.this.mLoadingView.setVisibility(8);
                PaginatedFragment.this.mLoadingBarView.setVisibility(8);
                PaginatedFragment.this.mListView.setVisibility(8);
                PaginatedFragment.this.mEmptyLayout.setVisibility(0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canViewItemDetail(D item) {
        return (item instanceof Artist) || (item instanceof Genre) || (item instanceof Playlist) || (item instanceof PrimePlaylist);
    }

    private void sendUiPageViewMetric() {
        NavigationAppEvent.builder("seeAll-" + this.mWidgetId).withOrientation(ScreenUtil.getScreenOrientation(getContext())).publish();
    }

    protected void sendUiClickMetric(final String identifier, final String entityId, final EntityIdType entityIdType, final String pageType, final int position) {
        UserInteractionAppEvent.builder(identifier).withEntityId(entityId).withEntityIdType(entityIdType).withPageType(pageType).withEntityPos(position).withInteractionType(InteractionType.TAP).publish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshOnUiThread() {
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.prime.PaginatedFragment.9
            @Override // java.lang.Runnable
            public void run() {
                T t = PaginatedFragment.this.mAdapter;
                if (t != null) {
                    t.notifyDataSetChanged();
                }
            }
        });
    }
}
