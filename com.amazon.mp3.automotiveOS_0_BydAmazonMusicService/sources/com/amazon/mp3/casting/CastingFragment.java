package com.amazon.mp3.casting;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.cloudqueue.CloudQueueFactory;
import com.amazon.mp3.cloudqueue.model.ConnectionInfoMetadata;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NPSOverlayMetricsReporter;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.Configuration;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionConfig;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.casting.session.iot.IotConnectionConfig;
import com.amazon.music.casting.session.iot.IotConnectionManager;
import com.amazon.music.castingviews.CastingDeviceListView;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.castingviews.Configuration;
import com.amazon.music.events.types.Orientation;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.picassoscrolllistener.PicassoScrollListener;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class CastingFragment extends Fragment implements ContextMenuSupportingFragment {
    private static final String TAG = CastingFragment.class.getSimpleName();
    private View mCastingButton;
    private CastingDeviceListView mCastingDeviceListView;
    private LinearLayout mCastingListLayout;
    private CastingSessionManager mCastingManager;
    private RecyclerView mCloudQueueRecyclerView;
    private ContextMenuHandler mContextMenuHandler;
    private FrameLayout mFrameLayout;
    private View mLoadingView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View mVolumeScrubberView;
    private SeekBar mVolumeSeekBar;
    public Boolean isStageCustomToolbar = Boolean.FALSE;
    private boolean backPressed = true;
    private final CastingConnectionCallback mCallback = new CastingConnectionCallback() { // from class: com.amazon.mp3.casting.CastingFragment.1
        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onError(CastingException e) {
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onConnected(final CastingDevice device) {
            CastingFragment.this.close();
            if (CastingFragment.this.mVolumeScrubberView != null) {
                CastingFragment.this.mVolumeScrubberView.setVisibility(0);
            }
        }

        @Override // com.amazon.music.casting.session.CastingConnectionCallback
        public void onDisconnected(TerminationReason terminationReason) {
            CastingFragment.this.close();
            if (CastingFragment.this.mVolumeScrubberView != null) {
                CastingFragment.this.mVolumeScrubberView.setVisibility(8);
            }
        }
    };
    private NPSOverlayMetricsReporter npsOverlayMetricsReporter = NPSOverlayMetricsReporter.INSTANCE.getNPSOverlayMetricsReporter();

    private void updateVolumeSeekBar() {
    }

    public static Intent getStartIntent(final Context context) {
        Intent intent = new Intent();
        intent.setClass(context, NowPlayingFragmentActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", CastingFragment.class.getSimpleName());
        intent.setFlags(536870912);
        return intent;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        this.mCastingManager = CastingSessionManager.getInstance();
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.casting_fragment_container, container, false);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) viewGroup.findViewById(R.id.swipeRefreshLayout);
        View viewFindViewById = viewGroup.findViewById(R.id.loading_spinner);
        this.mLoadingView = viewFindViewById;
        viewFindViewById.setVisibility(0);
        this.mFrameLayout = (FrameLayout) viewGroup.findViewById(R.id.frame);
        this.mCastingListLayout = (LinearLayout) viewGroup.findViewById(R.id.casting_list_layout);
        this.mCloudQueueRecyclerView = (RecyclerView) viewGroup.findViewById(R.id.cloudQueueDeviceList);
        this.mCloudQueueRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mVolumeScrubberView = viewGroup.findViewById(R.id.volumeScrubber);
        SeekBar seekBar = (SeekBar) viewGroup.findViewById(R.id.volumeSeekBar);
        this.mVolumeSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.mp3.casting.CastingFragment.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(final SeekBar seekBar2, final int progress, final boolean fromUser) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(final SeekBar seekBar2) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(final SeekBar seekBar2) {
                int progress = seekBar2.getProgress();
                CastingFragment.this.mCastingManager.setVolume(progress);
                Log.debug(CastingFragment.TAG, "changed volume progress to: " + progress + "%");
            }
        });
        updateVolumeSeekBar();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            StyleSheetProvider.getStyleSheet().observe(activity, new Observer() { // from class: com.amazon.mp3.casting.CastingFragment$$ExternalSyntheticLambda1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$onCreateView$0((StyleSheet) obj);
                }
            });
        }
        if (activity instanceof NowPlayingFragmentActivity) {
            NowPlayingFragmentActivity nowPlayingFragmentActivity = (NowPlayingFragmentActivity) activity;
            Toolbar toolbar = nowPlayingFragmentActivity.getToolbar();
            ActionBar supportActionBar = nowPlayingFragmentActivity.getSupportActionBar();
            supportActionBar.setDisplayShowCustomEnabled(false);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
            if (toolbar != null && !this.isStageCustomToolbar.booleanValue()) {
                toolbar.setNavigationContentDescription(R.string.casting_close_list_of_devices_content_description);
                View viewFindViewById2 = toolbar.findViewById(R.id.CastingButton);
                this.mCastingButton = viewFindViewById2;
                if (viewFindViewById2 != null) {
                    viewFindViewById2.setVisibility(8);
                }
                toolbar.setNavigationIcon(R.drawable.btn_close);
                setWindowInset((View) toolbar.getParent().getParent(), true);
            }
        }
        this.mCastingManager.registerListener(this.mCallback);
        this.mSwipeRefreshLayout.setOnRefreshListener(new AnonymousClass3(activity));
        return viewGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateView$0(StyleSheet styleSheet) {
        fetchView(styleSheet);
        initStyle(styleSheet);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.casting.CastingFragment$3, reason: invalid class name */
    class AnonymousClass3 implements SwipeRefreshLayout.OnRefreshListener {
        final /* synthetic */ FragmentActivity val$activity;

        AnonymousClass3(final FragmentActivity val$activity) {
            this.val$activity = val$activity;
        }

        @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
        public void onRefresh() {
            if (this.val$activity != null) {
                StyleSheetProvider.getStyleSheet().observe(this.val$activity, new Observer() { // from class: com.amazon.mp3.casting.CastingFragment$3$$ExternalSyntheticLambda0
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        this.f$0.lambda$onRefresh$0((StyleSheet) obj);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onRefresh$0(StyleSheet styleSheet) {
            CastingFragment.this.fetchView(styleSheet);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        CastingUIMetricsLogger.recordDeviceListPageView(getResources().getConfiguration().orientation == 1 ? Orientation.PORTRAIT : Orientation.LANDSCAPE);
        super.onViewCreated(view, savedInstanceState);
        showActionBar();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        Toolbar toolbar;
        this.mLoadingView = null;
        this.mFrameLayout = null;
        CastingDeviceListView castingDeviceListView = this.mCastingDeviceListView;
        if (castingDeviceListView != null) {
            castingDeviceListView.onDestroy();
            this.mCastingDeviceListView = null;
        }
        this.mCastingManager.unregisterListener(this.mCallback);
        FragmentActivity activity = getActivity();
        if (activity instanceof NowPlayingFragmentActivity) {
            NowPlayingFragmentActivity nowPlayingFragmentActivity = (NowPlayingFragmentActivity) activity;
            ActionBar supportActionBar = nowPlayingFragmentActivity.getSupportActionBar();
            supportActionBar.setDisplayShowCustomEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background_dark_blue)));
            if (!AmazonApplication.getCapabilities().isYAImmerseEnabled() && (toolbar = nowPlayingFragmentActivity.getToolbar()) != null) {
                toolbar.setNavigationContentDescription(R.string.close_button_content_description);
                View view = this.mCastingButton;
                if (view != null) {
                    view.setVisibility(0);
                }
                toolbar.setNavigationIcon(R.drawable.ic_chevron_caretdown);
                setWindowInset((View) toolbar.getParent().getParent(), false);
            }
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        }
        showActionBar();
    }

    private void showActionBar() {
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) getActivity()).showActionBar();
        }
    }

    private void setWindowInset(View targetView, final boolean useWindowInset) {
        if (Build.VERSION.SDK_INT >= 30) {
            ViewCompat.setOnApplyWindowInsetsListener(targetView, new OnApplyWindowInsetsListener() { // from class: com.amazon.mp3.casting.CastingFragment$$ExternalSyntheticLambda0
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    return CastingFragment.lambda$setWindowInset$1(useWindowInset, view, windowInsetsCompat);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat lambda$setWindowInset$1(boolean z, View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        if (z) {
            view.setPadding(0, insets.top, 0, 0);
        } else {
            view.setPadding(0, 0, 0, 0);
        }
        return WindowInsetsCompat.CONSUMED;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public boolean isBackPressed() {
        return this.backPressed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fetchView(StyleSheet styleSheet) {
        CastingDeviceListView castingDeviceListViewCreateCastingDeviceListView = CastingViewsFactory.createCastingDeviceListView(this);
        this.mCastingDeviceListView = castingDeviceListViewCreateCastingDeviceListView;
        if (castingDeviceListViewCreateCastingDeviceListView == null) {
            return;
        }
        castingDeviceListViewCreateCastingDeviceListView.getView(styleSheet).subscribe(new AnonymousClass4());
    }

    /* JADX INFO: renamed from: com.amazon.mp3.casting.CastingFragment$4, reason: invalid class name */
    class AnonymousClass4 implements rx.Observer<ViewGroup> {
        AnonymousClass4() {
        }

        @Override // rx.Observer
        public void onCompleted() {
            CastingFragment.this.mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override // rx.Observer
        public void onError(final Throwable throwable) {
            CastingFragment.this.mSwipeRefreshLayout.setRefreshing(false);
        }

        @Override // rx.Observer
        public void onNext(final ViewGroup view) {
            CastingFragment.this.mSwipeRefreshLayout.setRefreshing(false);
            CastingFragment.this.setupView(view);
            if (CastingFragment.this.mCastingDeviceListView.hasAtLeastOneAmazonDevice() && Feature.casting_v2.isEnabled() && CastingFragment.this.mCastingManager != null && !CastingFragment.this.mCastingManager.isConnected() && CloudQueueSequencer.isCloudQueuePlaying()) {
                AmazonApplication.getBackgroundHandler().post(new Runnable() { // from class: com.amazon.mp3.casting.CastingFragment$4$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onNext$0();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onNext$0() {
            CastingFragment.this.startIotConnectionForAmazonDevices();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupView(final ViewGroup viewGroup) {
        if (viewGroup != null && this.mFrameLayout != null) {
            ((RecyclerView) viewGroup.findViewById(R.id.recyclerView)).setOnScrollListener(new PicassoScrollListener(getContext()));
            this.mFrameLayout.removeAllViews();
            this.mFrameLayout.addView(viewGroup, -1, -1);
            this.mFrameLayout.setVisibility(0);
            if (CastingUtil.isCasting() && !CastingUtil.isCastingToAlexa()) {
                this.mVolumeScrubberView.setVisibility(0);
            } else {
                this.mVolumeScrubberView.setVisibility(8);
            }
        }
        View view = this.mLoadingView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void close() {
        this.backPressed = false;
        getActivity().onBackPressed();
    }

    private void initStyle(StyleSheet styleSheet) {
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        Objects.requireNonNull(spacerInPixels);
        int iIntValue = spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        Objects.requireNonNull(spacerInPixels2);
        int iIntValue2 = spacerInPixels2.intValue();
        GridStyle gridStyle = styleSheet.getGridStyle(GridStyleKey.HORIZONTAL_ITEM_GRID);
        int iIntValue3 = (gridStyle.getPeekSize().intValue() / 2) + gridStyle.getGridSize().getMarginSpace();
        LayoutParamUtils.INSTANCE.setLayoutMargins(this.mCastingListLayout, -1, -2, Boolean.FALSE, Integer.valueOf(iIntValue3), Integer.valueOf(iIntValue), Integer.valueOf(iIntValue3), Integer.valueOf(iIntValue2));
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
        ContextMenuHandler contextMenuHandler = this.mContextMenuHandler;
        if (contextMenuHandler != null) {
            return contextMenuHandler.onContextMenuItemSelected(menuItem, null);
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ContextMenuHandler contextMenuHandler = this.mContextMenuHandler;
        if (contextMenuHandler != null) {
            contextMenuHandler.onCreateContextMenu(menu, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startIotConnectionForAmazonDevices() {
        final Context context = getContext();
        if (context == null) {
            Log.error(TAG, "startIotConnectionForAmazonDevices: context is null");
        } else {
            CloudQueueFactory.getInstance(context).getCloudQueueService().getConnectionInfoMetadata().subscribe(new Consumer() { // from class: com.amazon.mp3.casting.CastingFragment$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) throws Throwable {
                    CastingFragment.lambda$startIotConnectionForAmazonDevices$2(context, (ConnectionInfoMetadata) obj);
                }
            }, new Consumer() { // from class: com.amazon.mp3.casting.CastingFragment$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) throws Throwable {
                    CastingFragment.lambda$startIotConnectionForAmazonDevices$3((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startIotConnectionForAmazonDevices$2(Context context, ConnectionInfoMetadata connectionInfoMetadata) throws Throwable {
        if (connectionInfoMetadata.getIotEndPoint() == null || connectionInfoMetadata.getIdentityPoolId() == null || connectionInfoMetadata.getClientId() == null || connectionInfoMetadata.getRegion() == null) {
            Log.error(TAG, "startIotConnectionForAmazonDevices: got invalid connection info " + connectionInfoMetadata);
            return;
        }
        Configuration configurationBuild = CastingViewsFactory.createCastingConfiguration(context).build();
        CastingSessionConfig castingSessionConfig = configurationBuild.getCastingSessionConfig();
        IotConnectionManager.getInstance(context, new IotConnectionConfig(new IotConnectionConfig.Builder(castingSessionConfig.getMaxTimeoutSeconds(), castingSessionConfig.getMaxTimeoutSeconds(), castingSessionConfig.getMinReconnectIntervalSeconds(), castingSessionConfig.getMaxReconnectIntervalSeconds(), castingSessionConfig.getReconnectAttemptsThreshold(), connectionInfoMetadata.getIotEndPoint(), connectionInfoMetadata.getIdentityPoolId(), connectionInfoMetadata.getClientId(), connectionInfoMetadata.getRegion())), new Configuration.Builder().withCastingSessionConfig(castingSessionConfig).withMarketplace(configurationBuild.getMarketplace()).withRequestInterceptor(configurationBuild.getRequestInterceptor()).build()).connect();
        Log.debug(TAG, "startIotConnectionForAmazonDevices is successful");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startIotConnectionForAmazonDevices$3(Throwable th) throws Throwable {
        Log.error(TAG, "startIotConnectionForAmazonDevices failed", th);
    }
}
