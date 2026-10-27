package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.fragment.contextmenu.SeeAllPlaylistItem;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter;
import com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerSnapHelper;
import com.amazon.mp3.fragment.viewmodel.ContextMenuAddToPlaylistViewModel;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: ContextMenuAddToPlaylistShovelerFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 .2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001.B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0014J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J&\u0010 \u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u001bH\u0016J\u0010\u0010-\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistShovelerFragment;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter$OnPlaylistClickListener;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper$OnSnapPositionChangeListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "addToPlaylistViewModel", "Lcom/amazon/mp3/fragment/viewmodel/ContextMenuAddToPlaylistViewModel;", "getAddToPlaylistViewModel", "()Lcom/amazon/mp3/fragment/viewmodel/ContextMenuAddToPlaylistViewModel;", "addToPlaylistViewModel$delegate", "Lkotlin/Lazy;", "onScrollListener", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedOnScrollListener;", "parentView", "Landroid/view/View;", "playlistsAdapter", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerFocusedAdapter;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "snapHelper", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/DescriptiveShovelerSnapHelper;", "initRecyclerViewPosition", "", "position", "", "observeChangesToPlaylists", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "onSnapPositionChange", "oldPosition", "newPosition", "setupViews", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContextMenuAddToPlaylistShovelerFragment extends ContextMenuAddToPlaylistBaseFragment implements DescriptiveShovelerFocusedAdapter.OnPlaylistClickListener, DescriptiveShovelerSnapHelper.OnSnapPositionChangeListener {
    private final String TAG;

    /* JADX INFO: renamed from: addToPlaylistViewModel$delegate, reason: from kotlin metadata */
    private final Lazy addToPlaylistViewModel;
    private final DescriptiveShovelerFocusedOnScrollListener onScrollListener;
    private View parentView;
    private final DescriptiveShovelerFocusedAdapter playlistsAdapter;
    private RecyclerView recyclerView;
    private final DescriptiveShovelerSnapHelper snapHelper;

    public ContextMenuAddToPlaylistShovelerFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        this.addToPlaylistViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(ContextMenuAddToPlaylistViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, null);
        this.playlistsAdapter = new DescriptiveShovelerFocusedAdapter();
        DescriptiveShovelerSnapHelper descriptiveShovelerSnapHelper = new DescriptiveShovelerSnapHelper();
        this.snapHelper = descriptiveShovelerSnapHelper;
        this.onScrollListener = new DescriptiveShovelerFocusedOnScrollListener(descriptiveShovelerSnapHelper, this);
        this.TAG = ContextMenuAddToPlaylistShovelerFragment.class.getSimpleName();
    }

    private final ContextMenuAddToPlaylistViewModel getAddToPlaylistViewModel() {
        return (ContextMenuAddToPlaylistViewModel) this.addToPlaylistViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_overflow_shoveler, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…oveler, container, false)");
        this.parentView = viewInflate;
        setupArguments();
        View view = this.parentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentView");
            view = null;
        }
        setupViews(view);
        observeChangesToPlaylists();
        getPlaylistsViewModel().setAddToPlaylistManager(new AddToPlaylistManager(getContext(), getAddToPlaylistListener()));
        View view2 = this.parentView;
        if (view2 != null) {
            return view2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("parentView");
        return null;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment
    protected void observeChangesToPlaylists() {
        super.observeChangesToPlaylists();
        LiveData<List<SeeAllPlaylistItem>> playlists = getAddToPlaylistViewModel().getPlaylists();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        playlists.observe(viewLifecycleOwner, new Observer() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment$observeChangesToPlaylists$$inlined$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                List<SeeAllPlaylistItem> list = (List) t;
                if (list == null) {
                    return;
                }
                this.this$0.playlistsAdapter.updatePlaylists(list, this.this$0.getContext());
                this.this$0.playlistsAdapter.notifyDataSetChanged();
                if (!list.isEmpty()) {
                    this.this$0.initRecyclerViewPosition(1);
                } else {
                    this.this$0.initRecyclerViewPosition(0);
                }
            }
        });
        try {
            String mediaSourceId = getMediaSourceId();
            if (!(mediaSourceId == null || StringsKt__StringsJVMKt.isBlank(mediaSourceId))) {
                getAddToPlaylistViewModel().fetchPlaylists(getMediaSourceId(), true);
            } else {
                Log.debug(this.TAG, "Cannot fetch playlists. Invalid Media Source ID.");
            }
        } catch (IllegalArgumentException e) {
            Log.debug(this.TAG, e.getMessage());
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerSnapHelper.OnSnapPositionChangeListener
    public void onSnapPositionChange(int oldPosition, int newPosition) {
        if (oldPosition < 0 || newPosition < 0) {
            return;
        }
        this.playlistsAdapter.updateActiveItem(oldPosition, newPosition);
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        recyclerView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ContextMenuAddToPlaylistShovelerFragment.m1153onSnapPositionChange$lambda2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onSnapPositionChange$lambda-2, reason: not valid java name */
    public static final void m1153onSnapPositionChange$lambda2(ContextMenuAddToPlaylistShovelerFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RecyclerView recyclerView = this$0.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    private final void setupViews(View parentView) {
        this.playlistsAdapter.setOnPlaylistClickListener(this);
        View viewFindViewById = parentView.findViewById(R.id.overflow_shoveler_recycler_view);
        RecyclerView recyclerView = (RecyclerView) viewFindViewById;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
        recyclerView.setAdapter(this.playlistsAdapter);
        recyclerView.addItemDecoration(new DescriptiveShovelerFocusedOffsetDecoration());
        recyclerView.addOnScrollListener(this.onScrollListener);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "parentView.findViewById<…ScrollListener)\n        }");
        this.recyclerView = recyclerView;
        DescriptiveShovelerSnapHelper descriptiveShovelerSnapHelper = this.snapHelper;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        descriptiveShovelerSnapHelper.attachToRecyclerView(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initRecyclerViewPosition(final int position) {
        RecyclerView recyclerView = this.recyclerView;
        final RecyclerView recyclerView2 = null;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            layoutManager.scrollToPosition(position);
        }
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            recyclerView2 = recyclerView3;
        }
        Intrinsics.checkNotNullExpressionValue(OneShotPreDrawListener.add(recyclerView2, new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment$initRecyclerViewPosition$$inlined$doOnPreDraw$1
            @Override // java.lang.Runnable
            public final void run() {
                RecyclerView recyclerView4 = this.recyclerView;
                RecyclerView recyclerView5 = null;
                if (recyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                    recyclerView4 = null;
                }
                RecyclerView.LayoutManager layoutManager2 = recyclerView4.getLayoutManager();
                if (layoutManager2 != null) {
                    layoutManager2.scrollToPosition(position);
                }
                RecyclerView recyclerView6 = this.recyclerView;
                if (recyclerView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                    recyclerView6 = null;
                }
                recyclerView6.smoothScrollBy(1, 0);
                RecyclerView recyclerView7 = this.recyclerView;
                if (recyclerView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                } else {
                    recyclerView5 = recyclerView7;
                }
                RecyclerView.Adapter adapter = recyclerView5.getAdapter();
                if (adapter == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter");
                }
                DescriptiveShovelerFocusedAdapter descriptiveShovelerFocusedAdapter = (DescriptiveShovelerFocusedAdapter) adapter;
                int i = position;
                descriptiveShovelerFocusedAdapter.updateActiveItem(i, i);
                descriptiveShovelerFocusedAdapter.notifyDataSetChanged();
            }
        }), "View.doOnPreDraw(\n    cr…dd(this) { action(this) }");
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.DescriptiveShovelerFocusedAdapter.OnPlaylistClickListener
    public void onItemClicked(final SeeAllPlaylistItem playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        if (!playlist.getIsActive()) {
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView = null;
            }
            recyclerView.post(new Runnable() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    ContextMenuAddToPlaylistShovelerFragment.m1152onItemClicked$lambda6(this.f$0, playlist);
                }
            });
            return;
        }
        if (!ConnectivityUtil.hasAnyInternetConnection(getContext()) && Intrinsics.areEqual("cirrus", MediaProvider.getSource(getPlaylistsViewModel().getTracksUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getContext()));
            ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener = getCallbacksListener();
            if (callbacksListener == null) {
                return;
            }
            callbacksListener.onAddToPlaylistError(getString(R.string.dmusic_playlist_retry_network_error_title));
            return;
        }
        String id = playlist.getId();
        if (Intrinsics.areEqual(id, "-1")) {
            getPlaylistsViewModel().checkIfNumberOfPlaylistsLimitReached(getContext());
            ContextMenuAddToPlaylistBaseFragment.sendUiClickMetrics$default(this, ActionType.CREATE_PLAYLIST_ATTEMPT, 0, null, PageType.NOW_PLAYING_OVERFLOW, 6, null);
            return;
        }
        if (Intrinsics.areEqual(id, "-2")) {
            ContextMenuAddToPlaylistBaseFragment.sendUiClickMetrics$default(this, ActionType.GO_SEE_ALL_PLAYLIST_OVERFLOW, 0, null, null, 14, null);
            Bundle argumentsBundle$default = ContextMenuAddToPlaylistBaseFragment.Companion.getArgumentsBundle$default(ContextMenuAddToPlaylistBaseFragment.INSTANCE, getPlaylistsViewModel().getTracksUri(), getPlaylistsViewModel().getAlbumCollection(), getPlaylistsViewModel().getAlbumAsin(), getPageType(), false, 16, null);
            Bundle arguments = getArguments();
            argumentsBundle$default.putBoolean("POP_OUT_MENU", arguments != null ? arguments.getBoolean("POP_OUT_MENU", false) : false);
            ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener2 = getCallbacksListener();
            if (callbacksListener2 == null) {
                return;
            }
            callbacksListener2.navigateToSeeAllPage(argumentsBundle$default);
            return;
        }
        setSelectedPlaylistName(playlist.getName());
        Uri playlistUri = MediaProvider.UdoPlaylistTracks.getContentUriFromLuid(getContext(), getMediaSourceId(), playlist.getLuId());
        Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
        addToUserPlaylist(playlistUri);
        ContextMenuAddToPlaylistBaseFragment.sendUiClickMetrics$default(this, ActionType.ADD_TO_PLAYLIST_OVERFLOW, playlist.getIndex(), playlist.getId(), null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onItemClicked$lambda-6, reason: not valid java name */
    public static final void m1152onItemClicked$lambda6(ContextMenuAddToPlaylistShovelerFragment this$0, SeeAllPlaylistItem playlist) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlist, "$playlist");
        DescriptiveShovelerSnapHelper descriptiveShovelerSnapHelper = this$0.snapHelper;
        int index = playlist.getIndex();
        RecyclerView recyclerView = this$0.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        descriptiveShovelerSnapHelper.scrollToPositionWithSnap(index, recyclerView);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            recyclerView = null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        Integer numValueOf = linearLayoutManager != null ? Integer.valueOf(linearLayoutManager.findFirstCompletelyVisibleItemPosition()) : null;
        recyclerView.setAdapter(this.playlistsAdapter);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && adapter.getItemCount() > 0 && numValueOf != null && numValueOf.intValue() < adapter.getItemCount() && numValueOf.intValue() > -1) {
            initRecyclerViewPosition(numValueOf.intValue());
        }
    }
}
