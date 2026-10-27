package androidx.leanback.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.core.view.ViewCompat;
import androidx.leanback.R;
import androidx.leanback.app.HeadersFragment;
import androidx.leanback.transition.TransitionHelper;
import androidx.leanback.transition.TransitionListener;
import androidx.leanback.util.StateMachine;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.leanback.widget.InvisibleRowPresenter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.PageRow;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowHeaderPresenter;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.ScaleFrameLayout;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class BrowseFragment extends BaseFragment {
    private static final String CURRENT_SELECTED_POSITION = "currentSelectedPosition";
    static final boolean DEBUG = false;
    public static final int HEADERS_DISABLED = 3;
    public static final int HEADERS_ENABLED = 1;
    public static final int HEADERS_HIDDEN = 2;
    static final String HEADER_SHOW = "headerShow";
    static final String HEADER_STACK_INDEX = "headerStackIndex";
    private static final String IS_PAGE_ROW = "isPageRow";
    private static final String LB_HEADERS_BACKSTACK = "lbHeadersBackStack_";
    static final String TAG = "BrowseFragment";
    private ObjectAdapter mAdapter;
    private PresenterSelector mAdapterPresenter;
    BackStackListener mBackStackChangedListener;
    private boolean mBrandColorSet;
    BrowseFrameLayout mBrowseFrame;
    BrowseTransitionListener mBrowseTransitionListener;
    private int mContainerListAlignTop;
    private int mContainerListMarginStart;
    OnItemViewSelectedListener mExternalOnItemViewSelectedListener;
    private PresenterSelector mHeaderPresenterSelector;
    HeadersFragment mHeadersFragment;
    Object mHeadersTransition;
    boolean mIsPageRow;
    Fragment mMainFragment;
    MainFragmentAdapter mMainFragmentAdapter;
    ListRowDataAdapter mMainFragmentListRowDataAdapter;
    MainFragmentRowsAdapter mMainFragmentRowsAdapter;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    Object mPageRow;
    private float mScaleFactor;
    private ScaleFrameLayout mScaleFrameLayout;
    private Object mSceneAfterEntranceTransition;
    Object mSceneWithHeaders;
    Object mSceneWithoutHeaders;
    String mWithHeadersBackStackName;
    private static final String ARG_TITLE = BrowseFragment.class.getCanonicalName() + ".title";
    private static final String ARG_HEADERS_STATE = BrowseFragment.class.getCanonicalName() + ".headersState";
    final StateMachine.State STATE_SET_ENTRANCE_START_STATE = new StateMachine.State("SET_ENTRANCE_START_STATE") { // from class: androidx.leanback.app.BrowseFragment.1
        @Override // androidx.leanback.util.StateMachine.State
        public void run() {
            BrowseFragment.this.setEntranceTransitionStartState();
        }
    };
    final StateMachine.Event EVT_HEADER_VIEW_CREATED = new StateMachine.Event("headerFragmentViewCreated");
    final StateMachine.Event EVT_MAIN_FRAGMENT_VIEW_CREATED = new StateMachine.Event("mainFragmentViewCreated");
    final StateMachine.Event EVT_SCREEN_DATA_READY = new StateMachine.Event("screenDataReady");
    private MainFragmentAdapterRegistry mMainFragmentAdapterRegistry = new MainFragmentAdapterRegistry();
    private int mHeadersState = 1;
    private int mBrandColor = 0;
    boolean mHeadersBackStackEnabled = true;
    boolean mShowingHeaders = true;
    boolean mCanShowHeaders = true;
    private boolean mMainFragmentScaleEnabled = true;
    private int mSelectedPosition = -1;
    boolean mStopped = true;
    private final SetSelectionRunnable mSetSelectionRunnable = new SetSelectionRunnable();
    private final BrowseFrameLayout.OnFocusSearchListener mOnFocusSearchListener = new BrowseFrameLayout.OnFocusSearchListener() { // from class: androidx.leanback.app.BrowseFragment.4
        @Override // androidx.leanback.widget.BrowseFrameLayout.OnFocusSearchListener
        public View onFocusSearch(View focused, int direction) {
            if (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.isInHeadersTransition()) {
                return focused;
            }
            if (BrowseFragment.this.getTitleView() != null && focused != BrowseFragment.this.getTitleView() && direction == 33) {
                return BrowseFragment.this.getTitleView();
            }
            if (BrowseFragment.this.getTitleView() != null && BrowseFragment.this.getTitleView().hasFocus() && direction == 130) {
                return (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.mShowingHeaders) ? BrowseFragment.this.mHeadersFragment.getVerticalGridView() : BrowseFragment.this.mMainFragment.getView();
            }
            boolean isRtl = ViewCompat.getLayoutDirection(focused) == 1;
            int towardStart = isRtl ? 66 : 17;
            int towardEnd = isRtl ? 17 : 66;
            if (BrowseFragment.this.mCanShowHeaders && direction == towardStart) {
                if (BrowseFragment.this.isVerticalScrolling() || BrowseFragment.this.mShowingHeaders || !BrowseFragment.this.isHeadersDataReady()) {
                    return focused;
                }
                return BrowseFragment.this.mHeadersFragment.getVerticalGridView();
            }
            if (direction == towardEnd) {
                if (!BrowseFragment.this.isVerticalScrolling() && BrowseFragment.this.mMainFragment != null && BrowseFragment.this.mMainFragment.getView() != null) {
                    return BrowseFragment.this.mMainFragment.getView();
                }
                return focused;
            }
            if (direction == 130 && BrowseFragment.this.mShowingHeaders) {
                return focused;
            }
            return null;
        }
    };
    private final BrowseFrameLayout.OnChildFocusListener mOnChildFocusListener = new BrowseFrameLayout.OnChildFocusListener() { // from class: androidx.leanback.app.BrowseFragment.5
        @Override // androidx.leanback.widget.BrowseFrameLayout.OnChildFocusListener
        public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
            if (BrowseFragment.this.getChildFragmentManager().isDestroyed()) {
                return true;
            }
            if (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.mShowingHeaders && BrowseFragment.this.mHeadersFragment != null && BrowseFragment.this.mHeadersFragment.getView() != null && BrowseFragment.this.mHeadersFragment.getView().requestFocus(direction, previouslyFocusedRect)) {
                return true;
            }
            if (BrowseFragment.this.mMainFragment == null || BrowseFragment.this.mMainFragment.getView() == null || !BrowseFragment.this.mMainFragment.getView().requestFocus(direction, previouslyFocusedRect)) {
                return BrowseFragment.this.getTitleView() != null && BrowseFragment.this.getTitleView().requestFocus(direction, previouslyFocusedRect);
            }
            return true;
        }

        @Override // androidx.leanback.widget.BrowseFrameLayout.OnChildFocusListener
        public void onRequestChildFocus(View child, View focused) {
            if (BrowseFragment.this.getChildFragmentManager().isDestroyed() || !BrowseFragment.this.mCanShowHeaders || BrowseFragment.this.isInHeadersTransition()) {
                return;
            }
            int childId = child.getId();
            if (childId == R.id.browse_container_dock && BrowseFragment.this.mShowingHeaders) {
                BrowseFragment.this.startHeadersTransitionInternal(false);
            } else if (childId == R.id.browse_headers_dock && !BrowseFragment.this.mShowingHeaders) {
                BrowseFragment.this.startHeadersTransitionInternal(true);
            }
        }
    };
    private HeadersFragment.OnHeaderClickedListener mHeaderClickedListener = new HeadersFragment.OnHeaderClickedListener() { // from class: androidx.leanback.app.BrowseFragment.10
        @Override // androidx.leanback.app.HeadersFragment.OnHeaderClickedListener
        public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
            if (!BrowseFragment.this.mCanShowHeaders || !BrowseFragment.this.mShowingHeaders || BrowseFragment.this.isInHeadersTransition() || BrowseFragment.this.mMainFragment == null || BrowseFragment.this.mMainFragment.getView() == null) {
                return;
            }
            BrowseFragment.this.startHeadersTransitionInternal(false);
            BrowseFragment.this.mMainFragment.getView().requestFocus();
        }
    };
    private HeadersFragment.OnHeaderViewSelectedListener mHeaderViewSelectedListener = new HeadersFragment.OnHeaderViewSelectedListener() { // from class: androidx.leanback.app.BrowseFragment.11
        @Override // androidx.leanback.app.HeadersFragment.OnHeaderViewSelectedListener
        public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
            int position = BrowseFragment.this.mHeadersFragment.getSelectedPosition();
            if (BrowseFragment.this.mShowingHeaders) {
                BrowseFragment.this.onRowSelected(position);
            }
        }
    };
    private final RecyclerView.OnScrollListener mWaitScrollFinishAndCommitMainFragment = new RecyclerView.OnScrollListener() { // from class: androidx.leanback.app.BrowseFragment.12
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == 0) {
                recyclerView.removeOnScrollListener(this);
                if (!BrowseFragment.this.mStopped) {
                    BrowseFragment.this.commitMainFragment();
                }
            }
        }
    };

    @Deprecated
    public static abstract class FragmentFactory<T extends Fragment> {
        public abstract T createFragment(Object obj);
    }

    @Deprecated
    public interface FragmentHost {
        void notifyDataReady(MainFragmentAdapter mainFragmentAdapter);

        void notifyViewCreated(MainFragmentAdapter mainFragmentAdapter);

        void showTitleView(boolean z);
    }

    @Deprecated
    public interface MainFragmentAdapterProvider {
        MainFragmentAdapter getMainFragmentAdapter();
    }

    @Deprecated
    public interface MainFragmentRowsAdapterProvider {
        MainFragmentRowsAdapter getMainFragmentRowsAdapter();
    }

    @Override // androidx.leanback.app.BaseFragment
    void createStateMachineStates() {
        super.createStateMachineStates();
        this.mStateMachine.addState(this.STATE_SET_ENTRANCE_START_STATE);
    }

    @Override // androidx.leanback.app.BaseFragment
    void createStateMachineTransitions() {
        super.createStateMachineTransitions();
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_ON_PREPARED, this.STATE_SET_ENTRANCE_START_STATE, this.EVT_HEADER_VIEW_CREATED);
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_ON_PREPARED, this.STATE_ENTRANCE_ON_PREPARED_ON_CREATEVIEW, this.EVT_MAIN_FRAGMENT_VIEW_CREATED);
        this.mStateMachine.addTransition(this.STATE_ENTRANCE_ON_PREPARED, this.STATE_ENTRANCE_PERFORM, this.EVT_SCREEN_DATA_READY);
    }

    final class BackStackListener implements FragmentManager.OnBackStackChangedListener {
        int mIndexOfHeadersBackStack = -1;
        int mLastEntryCount;

        BackStackListener() {
            this.mLastEntryCount = BrowseFragment.this.getFragmentManager().getBackStackEntryCount();
        }

        void load(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                int i = savedInstanceState.getInt(BrowseFragment.HEADER_STACK_INDEX, -1);
                this.mIndexOfHeadersBackStack = i;
                BrowseFragment.this.mShowingHeaders = i == -1;
                return;
            }
            if (!BrowseFragment.this.mShowingHeaders) {
                BrowseFragment.this.getFragmentManager().beginTransaction().addToBackStack(BrowseFragment.this.mWithHeadersBackStackName).commit();
            }
        }

        void save(Bundle outState) {
            outState.putInt(BrowseFragment.HEADER_STACK_INDEX, this.mIndexOfHeadersBackStack);
        }

        @Override // android.app.FragmentManager.OnBackStackChangedListener
        public void onBackStackChanged() {
            if (BrowseFragment.this.getFragmentManager() == null) {
                Log.w(BrowseFragment.TAG, "getFragmentManager() is null, stack:", new Exception());
                return;
            }
            int count = BrowseFragment.this.getFragmentManager().getBackStackEntryCount();
            int i = this.mLastEntryCount;
            if (count > i) {
                FragmentManager.BackStackEntry entry = BrowseFragment.this.getFragmentManager().getBackStackEntryAt(count - 1);
                if (BrowseFragment.this.mWithHeadersBackStackName.equals(entry.getName())) {
                    this.mIndexOfHeadersBackStack = count - 1;
                }
            } else if (count < i && this.mIndexOfHeadersBackStack >= count) {
                if (!BrowseFragment.this.isHeadersDataReady()) {
                    BrowseFragment.this.getFragmentManager().beginTransaction().addToBackStack(BrowseFragment.this.mWithHeadersBackStackName).commit();
                    return;
                } else {
                    this.mIndexOfHeadersBackStack = -1;
                    if (!BrowseFragment.this.mShowingHeaders) {
                        BrowseFragment.this.startHeadersTransitionInternal(true);
                    }
                }
            }
            this.mLastEntryCount = count;
        }
    }

    @Deprecated
    public static class BrowseTransitionListener {
        public void onHeadersTransitionStart(boolean withHeaders) {
        }

        public void onHeadersTransitionStop(boolean withHeaders) {
        }
    }

    private final class SetSelectionRunnable implements Runnable {
        static final int TYPE_INTERNAL_SYNC = 0;
        static final int TYPE_INVALID = -1;
        static final int TYPE_USER_REQUEST = 1;
        private int mPosition;
        private boolean mSmooth;
        private int mType;

        SetSelectionRunnable() {
            reset();
        }

        void post(int position, int type, boolean smooth) {
            if (type >= this.mType) {
                this.mPosition = position;
                this.mType = type;
                this.mSmooth = smooth;
                BrowseFragment.this.mBrowseFrame.removeCallbacks(this);
                if (!BrowseFragment.this.mStopped) {
                    BrowseFragment.this.mBrowseFrame.post(this);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            BrowseFragment.this.setSelection(this.mPosition, this.mSmooth);
            reset();
        }

        public void stop() {
            BrowseFragment.this.mBrowseFrame.removeCallbacks(this);
        }

        public void start() {
            if (this.mType != -1) {
                BrowseFragment.this.mBrowseFrame.post(this);
            }
        }

        private void reset() {
            this.mPosition = -1;
            this.mType = -1;
            this.mSmooth = false;
        }
    }

    private final class FragmentHostImpl implements FragmentHost {
        boolean mShowTitleView = true;

        FragmentHostImpl() {
        }

        @Override // androidx.leanback.app.BrowseFragment.FragmentHost
        public void notifyViewCreated(MainFragmentAdapter fragmentAdapter) {
            BrowseFragment.this.mStateMachine.fireEvent(BrowseFragment.this.EVT_MAIN_FRAGMENT_VIEW_CREATED);
            if (!BrowseFragment.this.mIsPageRow) {
                BrowseFragment.this.mStateMachine.fireEvent(BrowseFragment.this.EVT_SCREEN_DATA_READY);
            }
        }

        @Override // androidx.leanback.app.BrowseFragment.FragmentHost
        public void notifyDataReady(MainFragmentAdapter fragmentAdapter) {
            if (BrowseFragment.this.mMainFragmentAdapter == null || BrowseFragment.this.mMainFragmentAdapter.getFragmentHost() != this || !BrowseFragment.this.mIsPageRow) {
                return;
            }
            BrowseFragment.this.mStateMachine.fireEvent(BrowseFragment.this.EVT_SCREEN_DATA_READY);
        }

        @Override // androidx.leanback.app.BrowseFragment.FragmentHost
        public void showTitleView(boolean show) {
            this.mShowTitleView = show;
            if (BrowseFragment.this.mMainFragmentAdapter == null || BrowseFragment.this.mMainFragmentAdapter.getFragmentHost() != this || !BrowseFragment.this.mIsPageRow) {
                return;
            }
            BrowseFragment.this.updateTitleViewVisibility();
        }
    }

    @Deprecated
    public static class MainFragmentAdapter<T extends Fragment> {
        private final T mFragment;
        FragmentHostImpl mFragmentHost;
        private boolean mScalingEnabled;

        public MainFragmentAdapter(T fragment) {
            this.mFragment = fragment;
        }

        public final T getFragment() {
            return this.mFragment;
        }

        public boolean isScrolling() {
            return false;
        }

        public void setExpand(boolean expand) {
        }

        public void setEntranceTransitionState(boolean state) {
        }

        public void setAlignment(int windowAlignOffsetFromTop) {
        }

        public boolean onTransitionPrepare() {
            return false;
        }

        public void onTransitionStart() {
        }

        public void onTransitionEnd() {
        }

        public boolean isScalingEnabled() {
            return this.mScalingEnabled;
        }

        public void setScalingEnabled(boolean scalingEnabled) {
            this.mScalingEnabled = scalingEnabled;
        }

        public final FragmentHost getFragmentHost() {
            return this.mFragmentHost;
        }

        void setFragmentHost(FragmentHostImpl fragmentHost) {
            this.mFragmentHost = fragmentHost;
        }
    }

    @Deprecated
    public static class MainFragmentRowsAdapter<T extends Fragment> {
        private final T mFragment;

        public MainFragmentRowsAdapter(T fragment) {
            if (fragment == null) {
                throw new IllegalArgumentException("Fragment can't be null");
            }
            this.mFragment = fragment;
        }

        public final T getFragment() {
            return this.mFragment;
        }

        public void setAdapter(ObjectAdapter adapter) {
        }

        public void setOnItemViewClickedListener(OnItemViewClickedListener listener) {
        }

        public void setOnItemViewSelectedListener(OnItemViewSelectedListener listener) {
        }

        public void setSelectedPosition(int rowPosition, boolean smooth, Presenter.ViewHolderTask rowHolderTask) {
        }

        public void setSelectedPosition(int rowPosition, boolean smooth) {
        }

        public int getSelectedPosition() {
            return 0;
        }

        public RowPresenter.ViewHolder findRowViewHolderByPosition(int position) {
            return null;
        }
    }

    private boolean createMainFragment(ObjectAdapter adapter, int position) {
        Object item = null;
        boolean swap = true;
        if (this.mCanShowHeaders) {
            if (adapter == null || adapter.size() == 0) {
                return false;
            }
            if (position < 0) {
                position = 0;
            } else if (position >= adapter.size()) {
                throw new IllegalArgumentException(String.format("Invalid position %d requested", Integer.valueOf(position)));
            }
            item = adapter.get(position);
        }
        boolean oldIsPageRow = this.mIsPageRow;
        Object oldPageRow = this.mPageRow;
        boolean z = this.mCanShowHeaders && (item instanceof PageRow);
        this.mIsPageRow = z;
        Object obj = z ? item : null;
        this.mPageRow = obj;
        if (this.mMainFragment == null) {
            swap = true;
        } else if (oldIsPageRow) {
            if (this.mIsPageRow) {
                if (oldPageRow == null) {
                    swap = false;
                } else if (oldPageRow == obj) {
                    swap = false;
                }
            } else {
                swap = true;
            }
        } else {
            swap = this.mIsPageRow;
        }
        if (swap) {
            Fragment fragmentCreateFragment = this.mMainFragmentAdapterRegistry.createFragment(item);
            this.mMainFragment = fragmentCreateFragment;
            if (!(fragmentCreateFragment instanceof MainFragmentAdapterProvider)) {
                throw new IllegalArgumentException("Fragment must implement MainFragmentAdapterProvider");
            }
            setMainFragmentAdapter();
        }
        return swap;
    }

    void setMainFragmentAdapter() {
        MainFragmentAdapter mainFragmentAdapter = ((MainFragmentAdapterProvider) this.mMainFragment).getMainFragmentAdapter();
        this.mMainFragmentAdapter = mainFragmentAdapter;
        mainFragmentAdapter.setFragmentHost(new FragmentHostImpl());
        if (!this.mIsPageRow) {
            ComponentCallbacks2 componentCallbacks2 = this.mMainFragment;
            if (componentCallbacks2 instanceof MainFragmentRowsAdapterProvider) {
                setMainFragmentRowsAdapter(((MainFragmentRowsAdapterProvider) componentCallbacks2).getMainFragmentRowsAdapter());
            } else {
                setMainFragmentRowsAdapter(null);
            }
            this.mIsPageRow = this.mMainFragmentRowsAdapter == null;
            return;
        }
        setMainFragmentRowsAdapter(null);
    }

    @Deprecated
    public static class ListRowFragmentFactory extends FragmentFactory<RowsFragment> {
        @Override // androidx.leanback.app.BrowseFragment.FragmentFactory
        public RowsFragment createFragment(Object row) {
            return new RowsFragment();
        }
    }

    @Deprecated
    public static final class MainFragmentAdapterRegistry {
        private static final FragmentFactory sDefaultFragmentFactory = new ListRowFragmentFactory();
        private final Map<Class<?>, FragmentFactory> mItemToFragmentFactoryMapping = new HashMap();

        public MainFragmentAdapterRegistry() {
            registerFragment(ListRow.class, sDefaultFragmentFactory);
        }

        public void registerFragment(Class<?> rowClass, FragmentFactory factory) {
            this.mItemToFragmentFactoryMapping.put(rowClass, factory);
        }

        public Fragment createFragment(Object item) {
            FragmentFactory fragmentFactory = item == null ? sDefaultFragmentFactory : this.mItemToFragmentFactoryMapping.get(item.getClass());
            if (fragmentFactory == null && !(item instanceof PageRow)) {
                fragmentFactory = sDefaultFragmentFactory;
            }
            return fragmentFactory.createFragment(item);
        }
    }

    public static Bundle createArgs(Bundle args, String title, int headersState) {
        if (args == null) {
            args = new Bundle();
        }
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_HEADERS_STATE, headersState);
        return args;
    }

    public void setBrandColor(int color) {
        this.mBrandColor = color;
        this.mBrandColorSet = true;
        HeadersFragment headersFragment = this.mHeadersFragment;
        if (headersFragment != null) {
            headersFragment.setBackgroundColor(color);
        }
    }

    public int getBrandColor() {
        return this.mBrandColor;
    }

    private void updateWrapperPresenter() {
        ObjectAdapter objectAdapter = this.mAdapter;
        if (objectAdapter == null) {
            this.mAdapterPresenter = null;
            return;
        }
        final PresenterSelector adapterPresenter = objectAdapter.getPresenterSelector();
        if (adapterPresenter == null) {
            throw new IllegalArgumentException("Adapter.getPresenterSelector() is null");
        }
        if (adapterPresenter == this.mAdapterPresenter) {
            return;
        }
        this.mAdapterPresenter = adapterPresenter;
        Presenter[] presenters = adapterPresenter.getPresenters();
        final Presenter invisibleRowPresenter = new InvisibleRowPresenter();
        final Presenter[] allPresenters = new Presenter[presenters.length + 1];
        System.arraycopy(allPresenters, 0, presenters, 0, presenters.length);
        allPresenters[allPresenters.length - 1] = invisibleRowPresenter;
        this.mAdapter.setPresenterSelector(new PresenterSelector() { // from class: androidx.leanback.app.BrowseFragment.2
            @Override // androidx.leanback.widget.PresenterSelector
            public Presenter getPresenter(Object item) {
                Row row = (Row) item;
                if (row.isRenderedAsRowView()) {
                    return adapterPresenter.getPresenter(item);
                }
                return invisibleRowPresenter;
            }

            @Override // androidx.leanback.widget.PresenterSelector
            public Presenter[] getPresenters() {
                return allPresenters;
            }
        });
    }

    public void setAdapter(ObjectAdapter adapter) {
        this.mAdapter = adapter;
        updateWrapperPresenter();
        if (getView() == null) {
            return;
        }
        updateMainFragmentRowsAdapter();
        this.mHeadersFragment.setAdapter(this.mAdapter);
    }

    void setMainFragmentRowsAdapter(MainFragmentRowsAdapter mainFragmentRowsAdapter) {
        MainFragmentRowsAdapter mainFragmentRowsAdapter2 = this.mMainFragmentRowsAdapter;
        if (mainFragmentRowsAdapter == mainFragmentRowsAdapter2) {
            return;
        }
        if (mainFragmentRowsAdapter2 != null) {
            mainFragmentRowsAdapter2.setAdapter(null);
        }
        this.mMainFragmentRowsAdapter = mainFragmentRowsAdapter;
        if (mainFragmentRowsAdapter != null) {
            mainFragmentRowsAdapter.setOnItemViewSelectedListener(new MainFragmentItemViewSelectedListener(mainFragmentRowsAdapter));
            this.mMainFragmentRowsAdapter.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        }
        updateMainFragmentRowsAdapter();
    }

    void updateMainFragmentRowsAdapter() {
        ListRowDataAdapter listRowDataAdapter = this.mMainFragmentListRowDataAdapter;
        if (listRowDataAdapter != null) {
            listRowDataAdapter.detach();
            this.mMainFragmentListRowDataAdapter = null;
        }
        if (this.mMainFragmentRowsAdapter != null) {
            ObjectAdapter objectAdapter = this.mAdapter;
            ListRowDataAdapter listRowDataAdapter2 = objectAdapter != null ? new ListRowDataAdapter(objectAdapter) : null;
            this.mMainFragmentListRowDataAdapter = listRowDataAdapter2;
            this.mMainFragmentRowsAdapter.setAdapter(listRowDataAdapter2);
        }
    }

    public final MainFragmentAdapterRegistry getMainFragmentRegistry() {
        return this.mMainFragmentAdapterRegistry;
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener listener) {
        this.mExternalOnItemViewSelectedListener = listener;
    }

    public OnItemViewSelectedListener getOnItemViewSelectedListener() {
        return this.mExternalOnItemViewSelectedListener;
    }

    public RowsFragment getRowsFragment() {
        Fragment fragment = this.mMainFragment;
        if (fragment instanceof RowsFragment) {
            return (RowsFragment) fragment;
        }
        return null;
    }

    public Fragment getMainFragment() {
        return this.mMainFragment;
    }

    public HeadersFragment getHeadersFragment() {
        return this.mHeadersFragment;
    }

    public void setOnItemViewClickedListener(OnItemViewClickedListener listener) {
        this.mOnItemViewClickedListener = listener;
        MainFragmentRowsAdapter mainFragmentRowsAdapter = this.mMainFragmentRowsAdapter;
        if (mainFragmentRowsAdapter != null) {
            mainFragmentRowsAdapter.setOnItemViewClickedListener(listener);
        }
    }

    public OnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    public void startHeadersTransition(boolean withHeaders) {
        if (!this.mCanShowHeaders) {
            throw new IllegalStateException("Cannot start headers transition");
        }
        if (isInHeadersTransition() || this.mShowingHeaders == withHeaders) {
            return;
        }
        startHeadersTransitionInternal(withHeaders);
    }

    public boolean isInHeadersTransition() {
        return this.mHeadersTransition != null;
    }

    public boolean isShowingHeaders() {
        return this.mShowingHeaders;
    }

    public void setBrowseTransitionListener(BrowseTransitionListener listener) {
        this.mBrowseTransitionListener = listener;
    }

    @Deprecated
    public void enableRowScaling(boolean enable) {
        enableMainFragmentScaling(enable);
    }

    public void enableMainFragmentScaling(boolean enable) {
        this.mMainFragmentScaleEnabled = enable;
    }

    void startHeadersTransitionInternal(final boolean withHeaders) {
        if (getFragmentManager().isDestroyed() || !isHeadersDataReady()) {
            return;
        }
        this.mShowingHeaders = withHeaders;
        this.mMainFragmentAdapter.onTransitionPrepare();
        this.mMainFragmentAdapter.onTransitionStart();
        onExpandTransitionStart(!withHeaders, new Runnable() { // from class: androidx.leanback.app.BrowseFragment.3
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.mHeadersFragment.onTransitionPrepare();
                BrowseFragment.this.mHeadersFragment.onTransitionStart();
                BrowseFragment.this.createHeadersTransition();
                if (BrowseFragment.this.mBrowseTransitionListener != null) {
                    BrowseFragment.this.mBrowseTransitionListener.onHeadersTransitionStart(withHeaders);
                }
                TransitionHelper.runTransition(withHeaders ? BrowseFragment.this.mSceneWithHeaders : BrowseFragment.this.mSceneWithoutHeaders, BrowseFragment.this.mHeadersTransition);
                if (BrowseFragment.this.mHeadersBackStackEnabled) {
                    if (!withHeaders) {
                        BrowseFragment.this.getFragmentManager().beginTransaction().addToBackStack(BrowseFragment.this.mWithHeadersBackStackName).commit();
                        return;
                    }
                    int index = BrowseFragment.this.mBackStackChangedListener.mIndexOfHeadersBackStack;
                    if (index >= 0) {
                        FragmentManager.BackStackEntry entry = BrowseFragment.this.getFragmentManager().getBackStackEntryAt(index);
                        BrowseFragment.this.getFragmentManager().popBackStackImmediate(entry.getId(), 1);
                    }
                }
            }
        });
    }

    boolean isVerticalScrolling() {
        return this.mHeadersFragment.isScrolling() || this.mMainFragmentAdapter.isScrolling();
    }

    final boolean isHeadersDataReady() {
        ObjectAdapter objectAdapter = this.mAdapter;
        return (objectAdapter == null || objectAdapter.size() == 0) ? false : true;
    }

    @Override // androidx.leanback.app.BrandedFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_SELECTED_POSITION, this.mSelectedPosition);
        outState.putBoolean(IS_PAGE_ROW, this.mIsPageRow);
        BackStackListener backStackListener = this.mBackStackChangedListener;
        if (backStackListener != null) {
            backStackListener.save(outState);
        } else {
            outState.putBoolean(HEADER_SHOW, this.mShowingHeaders);
        }
    }

    @Override // androidx.leanback.app.BaseFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = FragmentUtil.getContext(this);
        TypedArray ta = context.obtainStyledAttributes(R.styleable.LeanbackTheme);
        this.mContainerListMarginStart = (int) ta.getDimension(R.styleable.LeanbackTheme_browseRowsMarginStart, context.getResources().getDimensionPixelSize(R.dimen.lb_browse_rows_margin_start));
        this.mContainerListAlignTop = (int) ta.getDimension(R.styleable.LeanbackTheme_browseRowsMarginTop, context.getResources().getDimensionPixelSize(R.dimen.lb_browse_rows_margin_top));
        ta.recycle();
        readArguments(getArguments());
        if (this.mCanShowHeaders) {
            if (this.mHeadersBackStackEnabled) {
                this.mWithHeadersBackStackName = LB_HEADERS_BACKSTACK + this;
                this.mBackStackChangedListener = new BackStackListener();
                getFragmentManager().addOnBackStackChangedListener(this.mBackStackChangedListener);
                this.mBackStackChangedListener.load(savedInstanceState);
            } else if (savedInstanceState != null) {
                this.mShowingHeaders = savedInstanceState.getBoolean(HEADER_SHOW);
            }
        }
        this.mScaleFactor = getResources().getFraction(R.fraction.lb_browse_rows_scale, 1, 1);
    }

    @Override // androidx.leanback.app.BrandedFragment, android.app.Fragment
    public void onDestroyView() {
        setMainFragmentRowsAdapter(null);
        this.mPageRow = null;
        this.mMainFragmentAdapter = null;
        this.mMainFragment = null;
        this.mHeadersFragment = null;
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        if (this.mBackStackChangedListener != null) {
            getFragmentManager().removeOnBackStackChangedListener(this.mBackStackChangedListener);
        }
        super.onDestroy();
    }

    public HeadersFragment onCreateHeadersFragment() {
        return new HeadersFragment();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getChildFragmentManager().findFragmentById(R.id.scale_frame) == null) {
            this.mHeadersFragment = onCreateHeadersFragment();
            createMainFragment(this.mAdapter, this.mSelectedPosition);
            FragmentTransaction ft = getChildFragmentManager().beginTransaction().replace(R.id.browse_headers_dock, this.mHeadersFragment);
            if (this.mMainFragment != null) {
                ft.replace(R.id.scale_frame, this.mMainFragment);
            } else {
                MainFragmentAdapter mainFragmentAdapter = new MainFragmentAdapter(null);
                this.mMainFragmentAdapter = mainFragmentAdapter;
                mainFragmentAdapter.setFragmentHost(new FragmentHostImpl());
            }
            ft.commit();
        } else {
            this.mHeadersFragment = (HeadersFragment) getChildFragmentManager().findFragmentById(R.id.browse_headers_dock);
            this.mMainFragment = getChildFragmentManager().findFragmentById(R.id.scale_frame);
            this.mIsPageRow = savedInstanceState != null && savedInstanceState.getBoolean(IS_PAGE_ROW, false);
            this.mSelectedPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_SELECTED_POSITION, 0) : 0;
            setMainFragmentAdapter();
        }
        this.mHeadersFragment.setHeadersGone(true ^ this.mCanShowHeaders);
        PresenterSelector presenterSelector = this.mHeaderPresenterSelector;
        if (presenterSelector != null) {
            this.mHeadersFragment.setPresenterSelector(presenterSelector);
        }
        this.mHeadersFragment.setAdapter(this.mAdapter);
        this.mHeadersFragment.setOnHeaderViewSelectedListener(this.mHeaderViewSelectedListener);
        this.mHeadersFragment.setOnHeaderClickedListener(this.mHeaderClickedListener);
        View root = inflater.inflate(R.layout.lb_browse_fragment, container, false);
        getProgressBarManager().setRootView((ViewGroup) root);
        BrowseFrameLayout browseFrameLayout = (BrowseFrameLayout) root.findViewById(R.id.browse_frame);
        this.mBrowseFrame = browseFrameLayout;
        browseFrameLayout.setOnChildFocusListener(this.mOnChildFocusListener);
        this.mBrowseFrame.setOnFocusSearchListener(this.mOnFocusSearchListener);
        installTitleView(inflater, this.mBrowseFrame, savedInstanceState);
        ScaleFrameLayout scaleFrameLayout = (ScaleFrameLayout) root.findViewById(R.id.scale_frame);
        this.mScaleFrameLayout = scaleFrameLayout;
        scaleFrameLayout.setPivotX(0.0f);
        this.mScaleFrameLayout.setPivotY(this.mContainerListAlignTop);
        if (this.mBrandColorSet) {
            this.mHeadersFragment.setBackgroundColor(this.mBrandColor);
        }
        this.mSceneWithHeaders = TransitionHelper.createScene(this.mBrowseFrame, new Runnable() { // from class: androidx.leanback.app.BrowseFragment.6
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.showHeaders(true);
            }
        });
        this.mSceneWithoutHeaders = TransitionHelper.createScene(this.mBrowseFrame, new Runnable() { // from class: androidx.leanback.app.BrowseFragment.7
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.showHeaders(false);
            }
        });
        this.mSceneAfterEntranceTransition = TransitionHelper.createScene(this.mBrowseFrame, new Runnable() { // from class: androidx.leanback.app.BrowseFragment.8
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.setEntranceTransitionEndState();
            }
        });
        return root;
    }

    void createHeadersTransition() {
        Object objLoadTransition = TransitionHelper.loadTransition(FragmentUtil.getContext(this), this.mShowingHeaders ? R.transition.lb_browse_headers_in : R.transition.lb_browse_headers_out);
        this.mHeadersTransition = objLoadTransition;
        TransitionHelper.addTransitionListener(objLoadTransition, new TransitionListener() { // from class: androidx.leanback.app.BrowseFragment.9
            @Override // androidx.leanback.transition.TransitionListener
            public void onTransitionStart(Object transition) {
            }

            @Override // androidx.leanback.transition.TransitionListener
            public void onTransitionEnd(Object transition) {
                VerticalGridView headerGridView;
                View mainFragmentView;
                BrowseFragment.this.mHeadersTransition = null;
                if (BrowseFragment.this.mMainFragmentAdapter != null) {
                    BrowseFragment.this.mMainFragmentAdapter.onTransitionEnd();
                    if (!BrowseFragment.this.mShowingHeaders && BrowseFragment.this.mMainFragment != null && (mainFragmentView = BrowseFragment.this.mMainFragment.getView()) != null && !mainFragmentView.hasFocus()) {
                        mainFragmentView.requestFocus();
                    }
                }
                if (BrowseFragment.this.mHeadersFragment != null) {
                    BrowseFragment.this.mHeadersFragment.onTransitionEnd();
                    if (BrowseFragment.this.mShowingHeaders && (headerGridView = BrowseFragment.this.mHeadersFragment.getVerticalGridView()) != null && !headerGridView.hasFocus()) {
                        headerGridView.requestFocus();
                    }
                }
                BrowseFragment.this.updateTitleViewVisibility();
                if (BrowseFragment.this.mBrowseTransitionListener != null) {
                    BrowseFragment.this.mBrowseTransitionListener.onHeadersTransitionStop(BrowseFragment.this.mShowingHeaders);
                }
            }
        });
    }

    void updateTitleViewVisibility() {
        boolean showBranding;
        MainFragmentAdapter mainFragmentAdapter;
        boolean showTitleView;
        MainFragmentAdapter mainFragmentAdapter2;
        if (!this.mShowingHeaders) {
            if (this.mIsPageRow && (mainFragmentAdapter2 = this.mMainFragmentAdapter) != null) {
                showTitleView = mainFragmentAdapter2.mFragmentHost.mShowTitleView;
            } else {
                showTitleView = isFirstRowWithContent(this.mSelectedPosition);
            }
            if (showTitleView) {
                showTitle(6);
                return;
            } else {
                showTitle(false);
                return;
            }
        }
        if (this.mIsPageRow && (mainFragmentAdapter = this.mMainFragmentAdapter) != null) {
            showBranding = mainFragmentAdapter.mFragmentHost.mShowTitleView;
        } else {
            showBranding = isFirstRowWithContent(this.mSelectedPosition);
        }
        boolean showSearch = isFirstRowWithContentOrPageRow(this.mSelectedPosition);
        int flags = showBranding ? 0 | 2 : 0;
        if (showSearch) {
            flags |= 4;
        }
        if (flags != 0) {
            showTitle(flags);
        } else {
            showTitle(false);
        }
    }

    boolean isFirstRowWithContentOrPageRow(int rowPosition) {
        ObjectAdapter objectAdapter = this.mAdapter;
        if (objectAdapter == null || objectAdapter.size() == 0) {
            return true;
        }
        int i = 0;
        while (i < this.mAdapter.size()) {
            Row row = (Row) this.mAdapter.get(i);
            if (row.isRenderedAsRowView() || (row instanceof PageRow)) {
                return rowPosition == i;
            }
            i++;
        }
        return true;
    }

    boolean isFirstRowWithContent(int rowPosition) {
        ObjectAdapter objectAdapter = this.mAdapter;
        if (objectAdapter == null || objectAdapter.size() == 0) {
            return true;
        }
        int i = 0;
        while (i < this.mAdapter.size()) {
            Row row = (Row) this.mAdapter.get(i);
            if (row.isRenderedAsRowView()) {
                return rowPosition == i;
            }
            i++;
        }
        return true;
    }

    public void setHeaderPresenterSelector(PresenterSelector headerPresenterSelector) {
        this.mHeaderPresenterSelector = headerPresenterSelector;
        HeadersFragment headersFragment = this.mHeadersFragment;
        if (headersFragment != null) {
            headersFragment.setPresenterSelector(headerPresenterSelector);
        }
    }

    private void setHeadersOnScreen(boolean onScreen) {
        View containerList = this.mHeadersFragment.getView();
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) containerList.getLayoutParams();
        lp.setMarginStart(onScreen ? 0 : -this.mContainerListMarginStart);
        containerList.setLayoutParams(lp);
    }

    void showHeaders(boolean show) {
        this.mHeadersFragment.setHeadersEnabled(show);
        setHeadersOnScreen(show);
        expandMainFragment(!show);
    }

    private void expandMainFragment(boolean expand) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.mScaleFrameLayout.getLayoutParams();
        params.setMarginStart(!expand ? this.mContainerListMarginStart : 0);
        this.mScaleFrameLayout.setLayoutParams(params);
        this.mMainFragmentAdapter.setExpand(expand);
        setMainFragmentAlignment();
        float scaleFactor = (!expand && this.mMainFragmentScaleEnabled && this.mMainFragmentAdapter.isScalingEnabled()) ? this.mScaleFactor : 1.0f;
        this.mScaleFrameLayout.setLayoutScaleY(scaleFactor);
        this.mScaleFrameLayout.setChildScale(scaleFactor);
    }

    class MainFragmentItemViewSelectedListener implements OnItemViewSelectedListener {
        MainFragmentRowsAdapter mMainFragmentRowsAdapter;

        public MainFragmentItemViewSelectedListener(MainFragmentRowsAdapter fragmentRowsAdapter) {
            this.mMainFragmentRowsAdapter = fragmentRowsAdapter;
        }

        @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            int position = this.mMainFragmentRowsAdapter.getSelectedPosition();
            BrowseFragment.this.onRowSelected(position);
            if (BrowseFragment.this.mExternalOnItemViewSelectedListener != null) {
                BrowseFragment.this.mExternalOnItemViewSelectedListener.onItemSelected(itemViewHolder, item, rowViewHolder, row);
            }
        }
    }

    void onRowSelected(int position) {
        this.mSetSelectionRunnable.post(position, 0, true);
    }

    void setSelection(int position, boolean smooth) {
        if (position == -1) {
            return;
        }
        this.mSelectedPosition = position;
        HeadersFragment headersFragment = this.mHeadersFragment;
        if (headersFragment == null || this.mMainFragmentAdapter == null) {
            return;
        }
        headersFragment.setSelectedPosition(position, smooth);
        replaceMainFragment(position);
        MainFragmentRowsAdapter mainFragmentRowsAdapter = this.mMainFragmentRowsAdapter;
        if (mainFragmentRowsAdapter != null) {
            mainFragmentRowsAdapter.setSelectedPosition(position, smooth);
        }
        updateTitleViewVisibility();
    }

    private void replaceMainFragment(int position) {
        if (createMainFragment(this.mAdapter, position)) {
            swapToMainFragment();
            expandMainFragment((this.mCanShowHeaders && this.mShowingHeaders) ? false : true);
        }
    }

    final void commitMainFragment() {
        FragmentManager fm = getChildFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.scale_frame);
        if (currentFragment != this.mMainFragment) {
            fm.beginTransaction().replace(R.id.scale_frame, this.mMainFragment).commit();
        }
    }

    private void swapToMainFragment() {
        if (this.mStopped) {
            return;
        }
        VerticalGridView gridView = this.mHeadersFragment.getVerticalGridView();
        if (isShowingHeaders() && gridView != null && gridView.getScrollState() != 0) {
            getChildFragmentManager().beginTransaction().replace(R.id.scale_frame, new Fragment()).commit();
            gridView.removeOnScrollListener(this.mWaitScrollFinishAndCommitMainFragment);
            gridView.addOnScrollListener(this.mWaitScrollFinishAndCommitMainFragment);
            return;
        }
        commitMainFragment();
    }

    public void setSelectedPosition(int position) {
        setSelectedPosition(position, true);
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    public RowPresenter.ViewHolder getSelectedRowViewHolder() {
        MainFragmentRowsAdapter mainFragmentRowsAdapter = this.mMainFragmentRowsAdapter;
        if (mainFragmentRowsAdapter != null) {
            int rowPos = mainFragmentRowsAdapter.getSelectedPosition();
            return this.mMainFragmentRowsAdapter.findRowViewHolderByPosition(rowPos);
        }
        return null;
    }

    public void setSelectedPosition(int position, boolean smooth) {
        this.mSetSelectionRunnable.post(position, 1, smooth);
    }

    public void setSelectedPosition(int rowPosition, boolean smooth, Presenter.ViewHolderTask rowHolderTask) {
        if (this.mMainFragmentAdapterRegistry == null) {
            return;
        }
        if (rowHolderTask != null) {
            startHeadersTransition(false);
        }
        MainFragmentRowsAdapter mainFragmentRowsAdapter = this.mMainFragmentRowsAdapter;
        if (mainFragmentRowsAdapter != null) {
            mainFragmentRowsAdapter.setSelectedPosition(rowPosition, smooth, rowHolderTask);
        }
    }

    @Override // androidx.leanback.app.BrandedFragment, android.app.Fragment
    public void onStart() {
        Fragment fragment;
        HeadersFragment headersFragment;
        super.onStart();
        this.mHeadersFragment.setAlignment(this.mContainerListAlignTop);
        setMainFragmentAlignment();
        if (this.mCanShowHeaders && this.mShowingHeaders && (headersFragment = this.mHeadersFragment) != null && headersFragment.getView() != null) {
            this.mHeadersFragment.getView().requestFocus();
        } else if ((!this.mCanShowHeaders || !this.mShowingHeaders) && (fragment = this.mMainFragment) != null && fragment.getView() != null) {
            this.mMainFragment.getView().requestFocus();
        }
        if (this.mCanShowHeaders) {
            showHeaders(this.mShowingHeaders);
        }
        this.mStateMachine.fireEvent(this.EVT_HEADER_VIEW_CREATED);
        this.mStopped = false;
        commitMainFragment();
        this.mSetSelectionRunnable.start();
    }

    @Override // android.app.Fragment
    public void onStop() {
        this.mStopped = true;
        this.mSetSelectionRunnable.stop();
        super.onStop();
    }

    private void onExpandTransitionStart(boolean expand, Runnable callback) {
        if (expand) {
            callback.run();
        } else {
            new ExpandPreLayout(callback, this.mMainFragmentAdapter, getView()).execute();
        }
    }

    private void setMainFragmentAlignment() {
        int alignOffset = this.mContainerListAlignTop;
        if (this.mMainFragmentScaleEnabled && this.mMainFragmentAdapter.isScalingEnabled() && this.mShowingHeaders) {
            alignOffset = (int) ((alignOffset / this.mScaleFactor) + 0.5f);
        }
        this.mMainFragmentAdapter.setAlignment(alignOffset);
    }

    public final void setHeadersTransitionOnBackEnabled(boolean headersBackStackEnabled) {
        this.mHeadersBackStackEnabled = headersBackStackEnabled;
    }

    public final boolean isHeadersTransitionOnBackEnabled() {
        return this.mHeadersBackStackEnabled;
    }

    private void readArguments(Bundle args) {
        if (args == null) {
            return;
        }
        if (args.containsKey(ARG_TITLE)) {
            setTitle(args.getString(ARG_TITLE));
        }
        if (args.containsKey(ARG_HEADERS_STATE)) {
            setHeadersState(args.getInt(ARG_HEADERS_STATE));
        }
    }

    public void setHeadersState(int headersState) {
        if (headersState < 1 || headersState > 3) {
            throw new IllegalArgumentException("Invalid headers state: " + headersState);
        }
        if (headersState != this.mHeadersState) {
            this.mHeadersState = headersState;
            if (headersState == 1) {
                this.mCanShowHeaders = true;
                this.mShowingHeaders = true;
            } else if (headersState == 2) {
                this.mCanShowHeaders = true;
                this.mShowingHeaders = false;
            } else if (headersState == 3) {
                this.mCanShowHeaders = false;
                this.mShowingHeaders = false;
            } else {
                Log.w(TAG, "Unknown headers state: " + headersState);
            }
            HeadersFragment headersFragment = this.mHeadersFragment;
            if (headersFragment != null) {
                headersFragment.setHeadersGone(true ^ this.mCanShowHeaders);
            }
        }
    }

    public int getHeadersState() {
        return this.mHeadersState;
    }

    @Override // androidx.leanback.app.BaseFragment
    protected Object createEntranceTransition() {
        return TransitionHelper.loadTransition(FragmentUtil.getContext(this), R.transition.lb_browse_entrance_transition);
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void runEntranceTransition(Object entranceTransition) {
        TransitionHelper.runTransition(this.mSceneAfterEntranceTransition, entranceTransition);
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void onEntranceTransitionPrepare() {
        this.mHeadersFragment.onTransitionPrepare();
        this.mMainFragmentAdapter.setEntranceTransitionState(false);
        this.mMainFragmentAdapter.onTransitionPrepare();
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void onEntranceTransitionStart() {
        this.mHeadersFragment.onTransitionStart();
        this.mMainFragmentAdapter.onTransitionStart();
    }

    @Override // androidx.leanback.app.BaseFragment
    protected void onEntranceTransitionEnd() {
        MainFragmentAdapter mainFragmentAdapter = this.mMainFragmentAdapter;
        if (mainFragmentAdapter != null) {
            mainFragmentAdapter.onTransitionEnd();
        }
        HeadersFragment headersFragment = this.mHeadersFragment;
        if (headersFragment != null) {
            headersFragment.onTransitionEnd();
        }
    }

    void setSearchOrbViewOnScreen(boolean onScreen) {
        View searchOrbView = getTitleViewAdapter().getSearchAffordanceView();
        if (searchOrbView != null) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) searchOrbView.getLayoutParams();
            lp.setMarginStart(onScreen ? 0 : -this.mContainerListMarginStart);
            searchOrbView.setLayoutParams(lp);
        }
    }

    void setEntranceTransitionStartState() {
        setHeadersOnScreen(false);
        setSearchOrbViewOnScreen(false);
    }

    void setEntranceTransitionEndState() {
        setHeadersOnScreen(this.mShowingHeaders);
        setSearchOrbViewOnScreen(true);
        this.mMainFragmentAdapter.setEntranceTransitionState(true);
    }

    private class ExpandPreLayout implements ViewTreeObserver.OnPreDrawListener {
        static final int STATE_FIRST_DRAW = 1;
        static final int STATE_INIT = 0;
        static final int STATE_SECOND_DRAW = 2;
        private final Runnable mCallback;
        private int mState;
        private final View mView;
        private MainFragmentAdapter mainFragmentAdapter;

        ExpandPreLayout(Runnable callback, MainFragmentAdapter adapter, View view) {
            this.mView = view;
            this.mCallback = callback;
            this.mainFragmentAdapter = adapter;
        }

        void execute() {
            this.mView.getViewTreeObserver().addOnPreDrawListener(this);
            this.mainFragmentAdapter.setExpand(false);
            this.mView.invalidate();
            this.mState = 0;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (BrowseFragment.this.getView() == null || FragmentUtil.getContext(BrowseFragment.this) == null) {
                this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
            int i = this.mState;
            if (i == 0) {
                this.mainFragmentAdapter.setExpand(true);
                this.mView.invalidate();
                this.mState = 1;
                return false;
            }
            if (i == 1) {
                this.mCallback.run();
                this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
                this.mState = 2;
                return false;
            }
            return false;
        }
    }
}
