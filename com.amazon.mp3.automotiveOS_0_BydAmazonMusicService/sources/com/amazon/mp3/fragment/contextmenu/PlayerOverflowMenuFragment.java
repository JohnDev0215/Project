package com.amazon.mp3.fragment.contextmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.transition.TransitionInflater;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistShovelerFragment;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlayerOverflowMenuFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010 \u001a\u00020\u001dH\u0002J\b\u0010!\u001a\u00020\u001dH\u0002J\b\u0010\"\u001a\u00020\u001dH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuFragment;", "Landroidx/fragment/app/Fragment;", "overFlowMenuCloseObservable", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/fragment/contextmenu/StageOverflowMenuMoreButtons;", "(Lio/reactivex/rxjava3/subjects/PublishSubject;)V", "currentTrackNameSubscription", "Lio/reactivex/rxjava3/disposables/Disposable;", "enterAnimationDurationMs", "", "nowPlayingViewModel", "Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuNowPlayingViewModel;", "getNowPlayingViewModel", "()Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuNowPlayingViewModel;", "nowPlayingViewModel$delegate", "Lkotlin/Lazy;", "rootView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "isStage", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "", "onViewCreated", "view", "setupAddToPlaylistSection", "setupMoreSection", "setupNowPlayingSection", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayerOverflowMenuFragment extends Fragment {
    private Disposable currentTrackNameSubscription;
    private long enterAnimationDurationMs;

    /* JADX INFO: renamed from: nowPlayingViewModel$delegate, reason: from kotlin metadata */
    private final Lazy nowPlayingViewModel;
    private final PublishSubject<StageOverflowMenuMoreButtons> overFlowMenuCloseObservable;
    private ConstraintLayout rootView;

    public PlayerOverflowMenuFragment() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public PlayerOverflowMenuFragment(PublishSubject<StageOverflowMenuMoreButtons> publishSubject) {
        this.overFlowMenuCloseObservable = publishSubject;
        this.enterAnimationDurationMs = 500L;
        this.nowPlayingViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PlayerOverflowMenuNowPlayingViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuFragment$special$$inlined$activityViewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
                ViewModelStore viewModelStore = fragmentActivityRequireActivity.getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "requireActivity().viewModelStore");
                return viewModelStore;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuFragment$special$$inlined$activityViewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
                ViewModelProvider.Factory defaultViewModelProviderFactory = fragmentActivityRequireActivity.getDefaultViewModelProviderFactory();
                Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
                return defaultViewModelProviderFactory;
            }
        });
    }

    public /* synthetic */ PlayerOverflowMenuFragment(PublishSubject publishSubject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : publishSubject);
    }

    private final PlayerOverflowMenuNowPlayingViewModel getNowPlayingViewModel() {
        return (PlayerOverflowMenuNowPlayingViewModel) this.nowPlayingViewModel.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_overflow_menu, container, false);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) viewInflate;
        this.rootView = constraintLayout;
        return constraintLayout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null && (!r2.getBoolean("FIRST_TIME_LAUNCHED", false))) {
            postponeEnterTransition(this.enterAnimationDurationMs, TimeUnit.MILLISECONDS);
        }
        TransitionInflater transitionInflaterFrom = TransitionInflater.from(requireContext());
        setEnterTransition(transitionInflaterFrom.inflateTransition(R.transition.slide_left));
        setExitTransition(transitionInflaterFrom.inflateTransition(R.transition.slide_left));
        setupNowPlayingSection();
        if (!NowPlayingUtil.isVideoPlaying()) {
            setupAddToPlaylistSection();
        }
        setupMoreSection();
        startPostponedEnterTransition();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Disposable disposable = this.currentTrackNameSubscription;
        if (disposable == null) {
            return;
        }
        disposable.dispose();
    }

    private final void setupNowPlayingSection() {
        float dimension;
        if (isStage()) {
            ConstraintLayout constraintLayout = this.rootView;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
                constraintLayout = null;
            }
            View viewFindViewById = constraintLayout.findViewById(R.id.overflow_menu_now_playing);
            if (viewFindViewById == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
            final TextView textView = (TextView) viewFindViewById;
            textView.setVisibility(0);
            this.currentTrackNameSubscription = getNowPlayingViewModel().getCurrentTrackSourceNameObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuFragment$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    PlayerOverflowMenuFragment.m1117setupNowPlayingSection$lambda1(textView, this, (String) obj);
                }
            });
            if (isStage()) {
                dimension = getResources().getDimension(R.dimen.text_size_11);
            } else {
                dimension = getResources().getDimension(R.dimen.text_size_13);
            }
            textView.setTextSize(0, dimension);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupNowPlayingSection$lambda-1, reason: not valid java name */
    public static final void m1117setupNowPlayingSection$lambda1(TextView nowPlayingView, PlayerOverflowMenuFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(nowPlayingView, "$nowPlayingView");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        nowPlayingView.setText(this$0.getString(R.string.dmusic_stage_player_overflow_now_playing_collection, str));
    }

    private final void setupMoreSection() {
        ConstraintLayout constraintLayout = this.rootView;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            constraintLayout = null;
        }
        View viewFindViewById = constraintLayout.findViewById(R.id.more_section_container);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
        }
        viewFindViewById.setVisibility(0);
        PlayerOverflowMenuMoreSectionFragment playerOverflowMenuMoreSectionFragment = new PlayerOverflowMenuMoreSectionFragment();
        playerOverflowMenuMoreSectionFragment.setArguments(getArguments());
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getParentFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "parentFragmentManager.beginTransaction()");
        if (isStage()) {
            fragmentTransactionBeginTransaction.replace(R.id.more_buttons, new PlayerOverflowMenuMoreButtonsFragment(this.overFlowMenuCloseObservable));
        }
        fragmentTransactionBeginTransaction.replace(R.id.more_section, playerOverflowMenuMoreSectionFragment);
        fragmentTransactionBeginTransaction.commit();
    }

    private final void setupAddToPlaylistSection() {
        Bundle arguments = getArguments();
        if ((arguments == null || arguments.getBoolean("EXTRA_IS_SIDE_LOADED")) ? false : true) {
            LifecycleOwner parentFragment = getParentFragment();
            if (parentFragment == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener");
            }
            final ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener shovelerCallbacksListener = (ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener) parentFragment;
            ContextMenuAddToPlaylistShovelerFragment contextMenuAddToPlaylistShovelerFragment = new ContextMenuAddToPlaylistShovelerFragment();
            contextMenuAddToPlaylistShovelerFragment.setCallbacksListener(shovelerCallbacksListener);
            contextMenuAddToPlaylistShovelerFragment.setArguments(getArguments());
            final Bundle arguments2 = getArguments();
            ConstraintLayout constraintLayout = null;
            if (arguments2 != null && arguments2.getBoolean("POP_OUT_MENU", false)) {
                ConstraintLayout constraintLayout2 = this.rootView;
                if (constraintLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                    constraintLayout2 = null;
                }
                ImageView imageView = (ImageView) constraintLayout2.findViewById(R.id.see_all_button);
                if (imageView != null) {
                    imageView.setVisibility(0);
                    imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuFragment$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            PlayerOverflowMenuFragment.m1116setupAddToPlaylistSection$lambda6$lambda5$lambda4(shovelerCallbacksListener, arguments2, view);
                        }
                    });
                }
            }
            FragmentActivity activity = getActivity();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            ConstraintLayout constraintLayout3 = this.rootView;
            if (constraintLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            } else {
                constraintLayout = constraintLayout3;
            }
            View viewFindViewById = constraintLayout.findViewById(R.id.add_to_playlist_shoveler_container);
            if (viewFindViewById == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            viewFindViewById.setVisibility(0);
            FragmentTransaction fragmentTransactionBeginTransaction = getParentFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "parentFragmentManager.beginTransaction()");
            fragmentTransactionBeginTransaction.replace(R.id.add_to_playlist_shoveler, contextMenuAddToPlaylistShovelerFragment);
            fragmentTransactionBeginTransaction.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupAddToPlaylistSection$lambda-6$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1116setupAddToPlaylistSection$lambda6$lambda5$lambda4(ContextMenuAddToPlaylistBaseFragment.ShovelerCallbacksListener callbackListener, Bundle bundle, View view) {
        Intrinsics.checkNotNullParameter(callbackListener, "$callbackListener");
        Intrinsics.checkNotNullParameter(bundle, "$bundle");
        callbackListener.navigateToSeeAllPage(bundle);
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }
}
