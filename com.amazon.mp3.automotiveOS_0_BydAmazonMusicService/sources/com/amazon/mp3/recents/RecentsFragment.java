package com.amazon.mp3.recents;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.activity.util.AccountJobController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.providers.DefaultContextMenuProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.recents.converters.RecentsConverterFactory;
import com.amazon.mp3.recents.repository.RecentsDBRepository;
import com.amazon.mp3.recents.usecase.RecentsModelUseCase;
import com.amazon.mp3.recents.view.RecentsViewFactory;
import com.amazon.mp3.recents.viewmodel.RecentsViewModel;
import com.amazon.mp3.recents.viewmodel.RecentsViewModelFactory;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class RecentsFragment extends LibraryBaseFragment implements ContextMenuSupportingFragment {
    private static final String TAG = RecentsFragment.class.getSimpleName();
    private AccountJobController mAccountJobs;
    private AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.recents.RecentsFragment.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(final Set<AccountStateField> set) {
            if (set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
                RecentsFragment.this.refreshView();
            }
        }
    };
    private ContextMenuHandler mContextMenuHandler;
    private View mLoadingView;
    private PageGridViewModel mPageGridViewModel;
    private RecentsViewModel mRecentsViewModel;
    private RecyclerView mRecyclerView;
    private RelativeLayout mRootView;
    private StyleSheet styleSheet;

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    protected boolean shouldSaveLastViewedSource() {
        return false;
    }

    public static RecentsFragment newInstance() {
        RecentsFragment recentsFragment = new RecentsFragment();
        recentsFragment.setArguments(new Bundle());
        return recentsFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        this.mAccountJobs = new AccountJobController(this, activity);
        if (activity instanceof NavigationProvider) {
            this.mNavigationProvider = (NavigationProvider) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mAccountJobs.destroy();
        this.mAccountJobs = null;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.recents.RecentsFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$0((StyleSheet) obj);
            }
        });
        AccountManagerSingleton.get().registerListener(this.mAccountStateChangeListener);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mFromTabHost = arguments.getBoolean("com.amazon.mp3.library.EXTRA_FRAGMENT_IN_TAB_HOST", false);
        }
        RecentsViewModel recentsViewModel = (RecentsViewModel) ViewModelProviders.of(this, new RecentsViewModelFactory(getActivity().getApplication(), new RecentsModelUseCase(new RecentsDBRepository(RecentlyPlayedManager.getInstance(getApplication()), new RecentsSmartPlaylistsProvider(ArtworkSizeUtil.getGridArtworkSize(getActivity()))), new RecentsConverterFactory(getApplication())))).get(RecentsViewModel.class);
        this.mRecentsViewModel = recentsViewModel;
        recentsViewModel.fetchData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$0(StyleSheet styleSheet) {
        this.styleSheet = styleSheet;
        if (this.mPageGridViewModel != null) {
            setupViews();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_recents, container, false);
        this.mRootView = relativeLayout;
        return relativeLayout;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View viewFindViewById = this.mRootView.findViewById(R.id.loading_screen);
        this.mLoadingView = viewFindViewById;
        viewFindViewById.setVisibility(0);
        this.mRecyclerView = (RecyclerView) this.mRootView.findViewById(R.id.recents_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        observeViewModel();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mContextMenuHandler = null;
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuSupportingFragment
    public void openContextMenuForView(ContextMenuHandler contextMenuHandler, View view) {
        this.mContextMenuHandler = contextMenuHandler;
        registerForContextMenu(view);
        getActivity().openContextMenu(view);
        unregisterForContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.mContextMenuHandler == null || !getUserVisibleHint()) {
            return false;
        }
        return this.mContextMenuHandler.onContextMenuItemSelected(menuItem, "cirrus");
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ContextMenuHandler contextMenuHandler = this.mContextMenuHandler;
        if (contextMenuHandler != null) {
            contextMenuHandler.onCreateContextMenu(menu, "cirrus");
        }
    }

    private void observeViewModel() {
        this.mRecentsViewModel.getPageData().observe(this, new Observer() { // from class: com.amazon.mp3.recents.RecentsFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$observeViewModel$1((PageGridViewModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeViewModel$1(PageGridViewModel pageGridViewModel) {
        this.mPageGridViewModel = pageGridViewModel;
        if (pageGridViewModel != null && this.styleSheet != null) {
            setupViews();
        }
        this.mLoadingView.setVisibility(8);
    }

    private void setupViews() {
        new RecentsViewFactory(this, this.styleSheet, this.mNavigationProvider, new DefaultContextMenuProvider(getActivity(), this, new LibraryStateProvider(getContext()), PageType.RECENT_ACTIVITY, new PageStateManager())).createRecentsView(this.mPageGridViewModel.getModels(), this.mRecyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView() {
        this.mRecentsViewModel.fetchData();
    }
}
