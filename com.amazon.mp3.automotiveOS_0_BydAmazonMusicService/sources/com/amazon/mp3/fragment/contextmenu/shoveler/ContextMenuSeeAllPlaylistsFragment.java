package com.amazon.mp3.fragment.contextmenu.shoveler;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionInflater;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.fragment.contextmenu.SeeAllPlaylistItem;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter;
import com.amazon.mp3.fragment.viewmodel.ContextMenuAddToPlaylistViewModel;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.AddToPlaylistManager;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: ContextMenuSeeAllPlaylistsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0014J$\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%H\u0016J\u001a\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u00102\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010(\u001a\u00020\u0018H\u0002J\u0010\u0010)\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsFragment;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuAddToPlaylistBaseFragment;", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter$OnPlaylistClickListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "addToPlaylistViewModel", "Lcom/amazon/mp3/fragment/viewmodel/ContextMenuAddToPlaylistViewModel;", "getAddToPlaylistViewModel", "()Lcom/amazon/mp3/fragment/viewmodel/ContextMenuAddToPlaylistViewModel;", "addToPlaylistViewModel$delegate", "Lkotlin/Lazy;", "backButton", "Landroid/widget/ImageView;", "parentView", "Landroid/view/View;", "playlistsAdapter", "Lcom/amazon/mp3/fragment/contextmenu/shoveler/ContextMenuSeeAllPlaylistsAdapter;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "titleText", "Landroid/widget/TextView;", "addDialogMargins", "", "isPopOut", "", "observeChangesToPlaylists", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/fragment/contextmenu/SeeAllPlaylistItem;", "onViewCreated", "view", "setupPopOutView", "setupViews", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContextMenuSeeAllPlaylistsFragment extends ContextMenuAddToPlaylistBaseFragment implements ContextMenuSeeAllPlaylistsAdapter.OnPlaylistClickListener {
    private final String TAG;

    /* JADX INFO: renamed from: addToPlaylistViewModel$delegate, reason: from kotlin metadata */
    private final Lazy addToPlaylistViewModel;
    private ImageView backButton;
    private View parentView;
    private final ContextMenuSeeAllPlaylistsAdapter playlistsAdapter;
    private RecyclerView recyclerView;
    private TextView titleText;

    public ContextMenuSeeAllPlaylistsFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return this;
            }
        };
        this.addToPlaylistViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(ContextMenuAddToPlaylistViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsFragment$special$$inlined$viewModels$default$2
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
        this.playlistsAdapter = new ContextMenuSeeAllPlaylistsAdapter();
        this.TAG = ContextMenuSeeAllPlaylistsFragment.class.getSimpleName();
    }

    private final ContextMenuAddToPlaylistViewModel getAddToPlaylistViewModel() {
        return (ContextMenuAddToPlaylistViewModel) this.addToPlaylistViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_context_menu_see_all, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…ee_all, container, false)");
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
        sendUiContentViewMetrics();
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
        playlists.observe(viewLifecycleOwner, new Observer() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsFragment$observeChangesToPlaylists$$inlined$observe$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                List<SeeAllPlaylistItem> list = (List) t;
                if (list == null) {
                    return;
                }
                this.this$0.playlistsAdapter.updatePlaylists(list, this.this$0.getContext());
                this.this$0.playlistsAdapter.notifyDataSetChanged();
                this.this$0.startPostponedEnterTransition();
            }
        });
        try {
            String mediaSourceId = getMediaSourceId();
            if (!(mediaSourceId == null || StringsKt__StringsJVMKt.isBlank(mediaSourceId))) {
                ContextMenuAddToPlaylistViewModel.fetchPlaylists$default(getAddToPlaylistViewModel(), getMediaSourceId(), false, 2, null);
            } else {
                Log.debug(this.TAG, "Cannot fetch playlists. Invalid Media Source ID.");
            }
        } catch (IllegalArgumentException e) {
            Log.debug(this.TAG, e.getMessage());
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        postponeEnterTransition();
        TransitionInflater transitionInflaterFrom = TransitionInflater.from(requireContext());
        setEnterTransition(transitionInflaterFrom.inflateTransition(R.transition.slide_right));
        setExitTransition(transitionInflaterFrom.inflateTransition(R.transition.slide_right));
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean("POP_OUT_MENU", false) : false;
        addDialogMargins(z);
        if (z) {
            setupPopOutView();
        }
    }

    private final void setupPopOutView() {
        TextView textView = this.titleText;
        ImageView imageView = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleText");
            textView = null;
        }
        textView.setTextSize(0, getResources().getDimension(R.dimen.text_size_18));
        View view = this.parentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R.id.title_background);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(0);
            viewFindViewById.setOnClickListener(null);
        }
        View view2 = this.parentView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentView");
            view2 = null;
        }
        View viewFindViewById2 = view2.findViewById(R.id.recycler_background);
        if (viewFindViewById2 != null) {
            viewFindViewById2.setVisibility(0);
        }
        View view3 = this.parentView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parentView");
            view3 = null;
        }
        View viewFindViewById3 = view3.findViewById(R.id.root_container);
        if (viewFindViewById3 != null) {
            View view4 = this.parentView;
            if (view4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("parentView");
                view4 = null;
            }
            viewFindViewById3.setBackgroundColor(ContextCompat.getColor(view4.getContext(), R.color.black));
        }
        ImageView imageView2 = this.backButton;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
            imageView2 = null;
        }
        ViewGroup.LayoutParams layoutParams = imageView2.getLayoutParams();
        int dimension = (int) getResources().getDimension(R.dimen.bauhaus_spacer_base);
        layoutParams.width = dimension;
        layoutParams.height = dimension;
        ImageView imageView3 = this.backButton;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backButton");
        } else {
            imageView = imageView3;
        }
        imageView.requestLayout();
    }

    private final void addDialogMargins(boolean isPopOut) {
        float dimension;
        Rect rect = new Rect();
        FragmentActivity activity = getActivity();
        RecyclerView recyclerView = null;
        Window window = activity == null ? null : activity.getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                decorView.getWindowVisibleDisplayFrame(rect);
            }
            if (isPopOut) {
                dimension = getResources().getDimension(R.dimen.bauhaus_spacer_base);
            } else {
                dimension = getResources().getDimension(R.dimen.bauhaus_spacer_huge);
            }
            int i = (int) dimension;
            TextView textView = this.titleText;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleText");
                textView = null;
            }
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.topMargin = i;
            TextView textView2 = this.titleText;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("titleText");
                textView2 = null;
            }
            textView2.setLayoutParams(marginLayoutParams);
            RecyclerView recyclerView2 = this.recyclerView;
            if (recyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                recyclerView2 = null;
            }
            ViewGroup.LayoutParams layoutParams2 = recyclerView2.getLayoutParams();
            if (layoutParams2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            }
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
            marginLayoutParams2.topMargin = i;
            RecyclerView recyclerView3 = this.recyclerView;
            if (recyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            } else {
                recyclerView = recyclerView3;
            }
            recyclerView.setLayoutParams(marginLayoutParams2);
        }
    }

    private final void setupViews(View parentView) {
        View viewFindViewById = parentView.findViewById(R.id.action_button_back);
        ImageView imageView = (ImageView) viewFindViewById;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContextMenuSeeAllPlaylistsFragment.m1156setupViews$lambda5$lambda4(this.f$0, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "parentView.findViewById<…)\n            }\n        }");
        this.backButton = imageView;
        View viewFindViewById2 = parentView.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "parentView.findViewById(R.id.title)");
        this.titleText = (TextView) viewFindViewById2;
        this.playlistsAdapter.setOnPlaylistClickListener(this);
        View viewFindViewById3 = parentView.findViewById(R.id.playlists_container);
        RecyclerView recyclerView = (RecyclerView) viewFindViewById3;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.setAdapter(this.playlistsAdapter);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "parentView.findViewById<…laylistsAdapter\n        }");
        this.recyclerView = recyclerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1156setupViews$lambda5$lambda4(ContextMenuSeeAllPlaylistsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener = this$0.getCallbacksListener();
        if (callbacksListener == null) {
            return;
        }
        callbacksListener.navigateToShovelerMenu(this$0.getArguments());
    }

    @Override // com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuSeeAllPlaylistsAdapter.OnPlaylistClickListener
    public void onItemClicked(SeeAllPlaylistItem playlist) {
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        if (!ConnectivityUtil.hasAnyInternetConnection(getContext()) && Intrinsics.areEqual("cirrus", MediaProvider.getSource(getPlaylistsViewModel().getTracksUri()))) {
            startActivity(NetworkErrorDialogActivity.getStartIntent(getContext()));
            ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbacksListener = getCallbacksListener();
            if (callbacksListener == null) {
                return;
            }
            callbacksListener.onAddToPlaylistError(getString(R.string.dmusic_playlist_retry_network_error_title));
            return;
        }
        if (Intrinsics.areEqual(playlist.getId(), "-1")) {
            getPlaylistsViewModel().checkIfNumberOfPlaylistsLimitReached(getContext());
            ContextMenuAddToPlaylistBaseFragment.sendUiClickMetrics$default(this, ActionType.CREATE_PLAYLIST_ATTEMPT, 0, null, null, 14, null);
            return;
        }
        setSelectedPlaylistName(playlist.getName());
        Uri playlistUri = MediaProvider.UdoPlaylistTracks.getContentUriFromLuid(getContext(), getMediaSourceId(), playlist.getLuId());
        Intrinsics.checkNotNullExpressionValue(playlistUri, "playlistUri");
        addToUserPlaylist(playlistUri);
        ContextMenuAddToPlaylistBaseFragment.sendUiClickMetrics$default(this, ActionType.ADD_TO_PLAYLIST_OVERFLOW, playlist.getIndex(), playlist.getLuId(), null, 8, null);
    }
}
