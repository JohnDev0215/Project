package com.amazon.mp3.fragment.contextmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlayerModelConfiguration;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.player.EnabledViewState;
import com.amazon.mp3.view.player.PlayerViewAction;
import com.amazon.mp3.view.player.PlayerViewEnabledStateManager;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: PlayerOverflowMenuMoreButtonsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0002\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010;¢\u0006\u0004\bk\u0010lJ\u001e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\bH\u0002J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\bH\u0002J\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u001c\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u001c\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\bH\u0002J\u001e\u0010$\u001a\u0004\u0018\u00010\u00062\u0006\u0010!\u001a\u00020\u00112\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"H\u0002J\u0012\u0010'\u001a\u00020\u001a2\b\u0010&\u001a\u0004\u0018\u00010%H\u0002J]\u00100\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010(2\b\b\u0002\u0010*\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020\u00112\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b0\u00101J$\u00108\u001a\u00020(2\u0006\u00103\u001a\u0002022\b\u00105\u001a\u0004\u0018\u0001042\b\u00107\u001a\u0004\u0018\u000106H\u0016J\u001a\u00109\u001a\u00020\b2\u0006\u0010)\u001a\u00020(2\b\u00107\u001a\u0004\u0018\u000106H\u0016J\b\u0010:\u001a\u00020\bH\u0016R\u001f\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010;8\u0006¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?R\u0016\u0010A\u001a\u00020@8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bA\u0010BR\u0018\u0010D\u001a\u0004\u0018\u00010C8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bD\u0010ER\u0018\u0010G\u001a\u0004\u0018\u00010F8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0018\u0010J\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010#\u001a\u0004\u0018\u00010\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b#\u0010LR\u001b\u0010R\u001a\u00020M8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010QR\u0014\u0010T\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010UR\u0018\u0010V\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0014\u0010Y\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010[\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010WR\u0014\u0010\\\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\\\u0010ZR\u0018\u0010]\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010WR\u0014\u0010^\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010ZR\u0018\u0010_\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010WR\u0014\u0010`\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010ZR\u0018\u0010a\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010WR\u0014\u0010b\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010ZR\u0017\u0010d\u001a\u00020c8\u0006¢\u0006\f\n\u0004\bd\u0010e\u001a\u0004\bf\u0010gR\u0014\u0010i\u001a\u00020h8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010j¨\u0006m"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreButtonsFragment;", "Landroidx/fragment/app/Fragment;", "", "Lcom/amazon/mp3/fragment/contextmenu/StageOverflowMenuMoreButtons;", "buttonList", "", "Lcom/amazon/ui/foundations/views/BaseButton;", "getAvailableButtons", "", "onRepeatButtonClicked", "onShuffleButtonClicked", "onDownloadButtonClicked", "onStationFromAnythingButtonClicked", "onDislikeButtonClicked", "Lcom/amazon/music/media/playback/RepeatMode;", "repeatMode", "Lkotlin/Pair;", "", "getRepeatModeButtonAttributes", "Lcom/amazon/music/downloads/DownloadState;", "downloadState", "getDownloadButtonAttributes", "Lcom/amazon/music/media/playback/ratings/LikeState;", "dislikeState", "getDislikeButtonAttributes", "initializeOrUpdateRepeatButton", "", "shuffleOn", "initializeOrUpdateShuffleButton", "initializeOrUpdateDownloadButton", "likeState", "initializeOrUpdateDislikeButton", "initializeStationFromAnythingButton", "iconId", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "activeButtonStyle", "initializeButton", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "isFreeTierNonPurchasedItem", "Landroid/view/View;", "view", "width", "height", "left", "top", "right", "bottom", "setMargins", "(Landroid/view/View;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "onViewCreated", "onDestroy", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "overFlowMenuCloseObservable", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "getOverFlowMenuCloseObservable", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "Landroid/widget/LinearLayout;", "buttonsContainer", "Landroid/widget/LinearLayout;", "Lrx/Subscription;", "styleSheetSubscription", "Lrx/Subscription;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "iconButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreButtonsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/amazon/mp3/fragment/contextmenu/PlayerOverflowMenuMoreButtonsViewModel;", "viewModel", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "disposables", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "repeatModeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Landroid/view/View$OnClickListener;", "repeatModeButtonClickListener", "Landroid/view/View$OnClickListener;", "shuffleButton", "shuffleButtonClickListener", "downloadButton", "downloadButtonClickListener", "stationFromAnythingButton", "stationFromAnythingButtonClickListener", "dislikeButton", "dislikeButtonClickListener", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "getPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "enabledStateManager", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "<init>", "(Lio/reactivex/rxjava3/subjects/PublishSubject;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlayerOverflowMenuMoreButtonsFragment extends Fragment {
    private ButtonStyle activeButtonStyle;
    private LinearLayout buttonsContainer;
    private BaseButton dislikeButton;
    private final View.OnClickListener dislikeButtonClickListener;
    private final CompositeDisposable disposables;
    private BaseButton downloadButton;
    private final View.OnClickListener downloadButtonClickListener;
    private final PlayerViewEnabledStateManager enabledStateManager;
    private BaseButton.StyleBuilder iconButtonBuilder;
    private final PublishSubject<StageOverflowMenuMoreButtons> overFlowMenuCloseObservable;
    private final ActionValidatedPlaybackController playbackController;
    private BaseButton repeatModeButton;
    private final View.OnClickListener repeatModeButtonClickListener;
    private BaseButton shuffleButton;
    private final View.OnClickListener shuffleButtonClickListener;
    private BaseButton stationFromAnythingButton;
    private final View.OnClickListener stationFromAnythingButtonClickListener;
    private StyleSheet styleSheet;
    private Subscription styleSheetSubscription;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX INFO: compiled from: PlayerOverflowMenuMoreButtonsFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[StageOverflowMenuMoreButtons.values().length];
            iArr[StageOverflowMenuMoreButtons.REPEAT.ordinal()] = 1;
            iArr[StageOverflowMenuMoreButtons.SHUFFLE.ordinal()] = 2;
            iArr[StageOverflowMenuMoreButtons.DOWNLOAD.ordinal()] = 3;
            iArr[StageOverflowMenuMoreButtons.STATION.ordinal()] = 4;
            iArr[StageOverflowMenuMoreButtons.DISLIKE.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[RepeatMode.values().length];
            iArr2[RepeatMode.REPEAT_NONE.ordinal()] = 1;
            iArr2[RepeatMode.REPEAT_ONE.ordinal()] = 2;
            iArr2[RepeatMode.REPEAT_ALL.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[DownloadState.values().length];
            iArr3[DownloadState.DOWNLOADED.ordinal()] = 1;
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[LikeState.values().length];
            iArr4[LikeState.DISLIKE.ordinal()] = 1;
            iArr4[LikeState.LIKE.ordinal()] = 2;
            iArr4[LikeState.NEUTRAL.ordinal()] = 3;
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    public PlayerOverflowMenuMoreButtonsFragment() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public PlayerOverflowMenuMoreButtonsFragment(PublishSubject<StageOverflowMenuMoreButtons> publishSubject) {
        this.overFlowMenuCloseObservable = publishSubject;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PlayerOverflowMenuMoreButtonsViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$special$$inlined$activityViewModels$default$1
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
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$special$$inlined$activityViewModels$default$2
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
        this.disposables = new CompositeDisposable();
        this.repeatModeButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerOverflowMenuMoreButtonsFragment.m1132repeatModeButtonClickListener$lambda0(this.f$0, view);
            }
        };
        this.shuffleButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerOverflowMenuMoreButtonsFragment.m1133shuffleButtonClickListener$lambda1(this.f$0, view);
            }
        };
        this.downloadButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerOverflowMenuMoreButtonsFragment.m1125downloadButtonClickListener$lambda2(this.f$0, view);
            }
        };
        this.stationFromAnythingButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerOverflowMenuMoreButtonsFragment.m1134stationFromAnythingButtonClickListener$lambda3(this.f$0, view);
            }
        };
        this.dislikeButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayerOverflowMenuMoreButtonsFragment.m1124dislikeButtonClickListener$lambda4(this.f$0, view);
            }
        };
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        this.enabledStateManager = new PlayerViewEnabledStateManager(controller);
    }

    public /* synthetic */ PlayerOverflowMenuMoreButtonsFragment(PublishSubject publishSubject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : publishSubject);
    }

    private final PlayerOverflowMenuMoreButtonsViewModel getViewModel() {
        return (PlayerOverflowMenuMoreButtonsViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: repeatModeButtonClickListener$lambda-0, reason: not valid java name */
    public static final void m1132repeatModeButtonClickListener$lambda0(PlayerOverflowMenuMoreButtonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onRepeatButtonClicked();
        PublishSubject<StageOverflowMenuMoreButtons> publishSubject = this$0.overFlowMenuCloseObservable;
        if (publishSubject == null) {
            return;
        }
        publishSubject.onNext(StageOverflowMenuMoreButtons.REPEAT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: shuffleButtonClickListener$lambda-1, reason: not valid java name */
    public static final void m1133shuffleButtonClickListener$lambda1(PlayerOverflowMenuMoreButtonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onShuffleButtonClicked();
        PublishSubject<StageOverflowMenuMoreButtons> publishSubject = this$0.overFlowMenuCloseObservable;
        if (publishSubject == null) {
            return;
        }
        publishSubject.onNext(StageOverflowMenuMoreButtons.SHUFFLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: downloadButtonClickListener$lambda-2, reason: not valid java name */
    public static final void m1125downloadButtonClickListener$lambda2(PlayerOverflowMenuMoreButtonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDownloadButtonClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stationFromAnythingButtonClickListener$lambda-3, reason: not valid java name */
    public static final void m1134stationFromAnythingButtonClickListener$lambda3(PlayerOverflowMenuMoreButtonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onStationFromAnythingButtonClicked();
        PublishSubject<StageOverflowMenuMoreButtons> publishSubject = this$0.overFlowMenuCloseObservable;
        if (publishSubject == null) {
            return;
        }
        publishSubject.onNext(StageOverflowMenuMoreButtons.STATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: dislikeButtonClickListener$lambda-4, reason: not valid java name */
    public static final void m1124dislikeButtonClickListener$lambda4(PlayerOverflowMenuMoreButtonsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onDislikeButtonClicked();
        PublishSubject<StageOverflowMenuMoreButtons> publishSubject = this$0.overFlowMenuCloseObservable;
        if (publishSubject == null) {
            return;
        }
        publishSubject.onNext(StageOverflowMenuMoreButtons.DISLIKE);
    }

    public final ActionValidatedPlaybackController getPlaybackController() {
        return this.playbackController;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.styleSheetSubscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayerOverflowMenuMoreButtonsFragment.m1127onCreateView$lambda5(this.f$0, (StyleSheet) obj);
            }
        });
        View rootView = inflater.inflate(R.layout.fragment_overflow_menu_more_buttons, container, false);
        View viewFindViewById = rootView.findViewById(R.id.overflow_menu_more_buttons_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.…u_more_buttons_container)");
        this.buttonsContainer = (LinearLayout) viewFindViewById;
        Intrinsics.checkNotNullExpressionValue(rootView, "rootView");
        return rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-5, reason: not valid java name */
    public static final void m1127onCreateView$lambda5(PlayerOverflowMenuMoreButtonsFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = styleSheet;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        List<? extends StageOverflowMenuMoreButtons> listListOf;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        getViewModel().initiaizeOverflowState();
        StyleSheet styleSheet = this.styleSheet;
        LinearLayout linearLayout = null;
        this.iconButtonBuilder = styleSheet == null ? null : styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        StyleSheet styleSheet2 = this.styleSheet;
        this.activeButtonStyle = styleSheet2 == null ? null : styleSheet2.getIconStyle(IconStyleKey.ACCENT_NORMAL);
        this.disposables.add(getViewModel().getRepeatModeObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerOverflowMenuMoreButtonsFragment.m1128onViewCreated$lambda6(this.f$0, (RepeatMode) obj);
            }
        }));
        this.disposables.add(getViewModel().getShuffleObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerOverflowMenuMoreButtonsFragment.m1129onViewCreated$lambda7(this.f$0, (Boolean) obj);
            }
        }));
        this.disposables.add(getViewModel().getDownloadStateObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerOverflowMenuMoreButtonsFragment.m1130onViewCreated$lambda8(this.f$0, (DownloadState) obj);
            }
        }));
        initializeStationFromAnythingButton();
        this.disposables.add(getViewModel().getDislikeStateObservable().subscribe(new Consumer() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                PlayerOverflowMenuMoreButtonsFragment.m1131onViewCreated$lambda9(this.f$0, (LikeState) obj);
            }
        }));
        MediaItem currentMediaItem = getViewModel().getPlaybackController().getCurrentMediaItem();
        if (currentMediaItem != null) {
            getViewModel().getDislikeStateObservable().onNext(currentMediaItem.getLikeState());
        }
        if (NowPlayingUtil.isVideoPlaying()) {
            listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new StageOverflowMenuMoreButtons[]{StageOverflowMenuMoreButtons.REPEAT, StageOverflowMenuMoreButtons.SHUFFLE});
        } else {
            listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new StageOverflowMenuMoreButtons[]{StageOverflowMenuMoreButtons.REPEAT, StageOverflowMenuMoreButtons.SHUFFLE, StageOverflowMenuMoreButtons.DOWNLOAD, StageOverflowMenuMoreButtons.STATION, StageOverflowMenuMoreButtons.DISLIKE});
        }
        this.enabledStateManager.setControlsAreEnabled(true);
        for (BaseButton baseButton : NowPlayingUtil.isVideoPlaying() ? CollectionsKt__CollectionsKt.listOf((Object[]) new BaseButton[]{this.repeatModeButton, this.shuffleButton}) : getAvailableButtons(listListOf)) {
            if (baseButton != null) {
                LinearLayout linearLayout2 = this.buttonsContainer;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("buttonsContainer");
                    linearLayout2 = null;
                }
                if (linearLayout2.getChildCount() != 0) {
                    StyleSheet styleSheet3 = this.styleSheet;
                    setMargins$default(this, baseButton, 0, 0, styleSheet3 == null ? null : styleSheet3.getSpacerInPixels(SpacerKey.BASE), null, null, null, 118, null);
                }
                LinearLayout linearLayout3 = this.buttonsContainer;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("buttonsContainer");
                    linearLayout3 = null;
                }
                linearLayout3.addView(baseButton);
            }
        }
        LinearLayout linearLayout4 = this.buttonsContainer;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonsContainer");
            linearLayout4 = null;
        }
        LinearLayout linearLayout5 = this.buttonsContainer;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonsContainer");
        } else {
            linearLayout = linearLayout5;
        }
        linearLayout4.setVisibility(linearLayout.getChildCount() == 0 ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-6, reason: not valid java name */
    public static final void m1128onViewCreated$lambda6(PlayerOverflowMenuMoreButtonsFragment this$0, RepeatMode it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initializeOrUpdateRepeatButton(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-7, reason: not valid java name */
    public static final void m1129onViewCreated$lambda7(PlayerOverflowMenuMoreButtonsFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initializeOrUpdateShuffleButton(it.booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-8, reason: not valid java name */
    public static final void m1130onViewCreated$lambda8(PlayerOverflowMenuMoreButtonsFragment this$0, DownloadState it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initializeOrUpdateDownloadButton(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-9, reason: not valid java name */
    public static final void m1131onViewCreated$lambda9(PlayerOverflowMenuMoreButtonsFragment this$0, LikeState it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initializeOrUpdateDislikeButton(it);
    }

    private final List<BaseButton> getAvailableButtons(List<? extends StageOverflowMenuMoreButtons> buttonList) {
        final BaseButton baseButton;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = buttonList.iterator();
        while (it.hasNext()) {
            int i = WhenMappings.$EnumSwitchMapping$0[((StageOverflowMenuMoreButtons) it.next()).ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        MediaItem currentMediaItem = getPlaybackController().getCurrentMediaItem();
                        if (currentMediaItem != null) {
                            AbstractItem abstractItem = MediaItemHelper.getAbstractItem(currentMediaItem, true, null);
                            if ((isFreeTierNonPurchasedItem(currentMediaItem) || (abstractItem != null && new PlayerModelConfiguration().shouldShowContextMenuDownloadUpsellButton(abstractItem))) && (baseButton = this.downloadButton) != null) {
                                StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.fragment.contextmenu.PlayerOverflowMenuMoreButtonsFragment$$ExternalSyntheticLambda10
                                    @Override // rx.functions.Action1
                                    public final void call(Object obj) {
                                        PlayerOverflowMenuMoreButtonsFragment.m1126getAvailableButtons$lambda16$lambda15$lambda14$lambda13(baseButton, (StyleSheet) obj);
                                    }
                                });
                            }
                        }
                        arrayList.add(this.downloadButton);
                    } else if (i == 4) {
                        arrayList.add(this.stationFromAnythingButton);
                    } else if (i == 5 && !CastingUtil.isCasting()) {
                        arrayList.add(this.dislikeButton);
                    }
                } else if (this.enabledStateManager.getEnabledViewState(PlayerViewAction.SHUFFLE) == EnabledViewState.ENABLED) {
                    arrayList.add(this.shuffleButton);
                }
            } else if (this.enabledStateManager.getEnabledViewState(PlayerViewAction.REPEAT) == EnabledViewState.ENABLED) {
                arrayList.add(this.repeatModeButton);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAvailableButtons$lambda-16$lambda-15$lambda-14$lambda-13, reason: not valid java name */
    public static final void m1126getAvailableButtons$lambda16$lambda15$lambda14$lambda13(BaseButton button, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(button, "$button");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Float alpha = styleSheet.getAlpha(AlphaKey.GLASS_3);
        if (alpha == null) {
            return;
        }
        button.setAlpha(alpha.floatValue());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        Subscription subscription = this.styleSheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.disposables.dispose();
    }

    private final void onRepeatButtonClicked() {
        getViewModel().toggleRepeatMode();
    }

    private final void onShuffleButtonClicked() {
        getViewModel().toggleShuffle();
    }

    private final void onDownloadButtonClicked() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        getViewModel().downloadTrack(activity);
    }

    private final void onStationFromAnythingButtonClicked() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        getViewModel().stationFromAnything(activity);
    }

    private final void onDislikeButtonClicked() {
        getViewModel().dislikeTrack();
    }

    private final Pair<Integer, Integer> getRepeatModeButtonAttributes(RepeatMode repeatMode) {
        int i;
        int i2;
        int i3 = WhenMappings.$EnumSwitchMapping$1[repeatMode.ordinal()];
        if (i3 == 1) {
            i = R.drawable.ic_playback_repeat;
            i2 = R.string.dmusic_player_repeat_all_songs_button_description;
        } else if (i3 == 2) {
            i = R.drawable.ic_playback_repeat_one;
            i2 = R.string.dmusic_player_repeat_off_button_description;
        } else {
            if (i3 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.drawable.ic_playback_repeat_all;
            i2 = R.string.dmusic_player_repeat_one_song_button_description;
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i));
    }

    private final Pair<Integer, Integer> getDownloadButtonAttributes(DownloadState downloadState) {
        int i;
        int i2;
        if (WhenMappings.$EnumSwitchMapping$2[downloadState.ordinal()] == 1) {
            i = R.drawable.ic_download_complete;
            i2 = R.string.dmusic_download_state_downloaded;
        } else {
            i = R.drawable.ic_action_download;
            i2 = R.string.dmusic_context_download;
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i));
    }

    private final Pair<Integer, Integer> getDislikeButtonAttributes(LikeState dislikeState) {
        int i;
        int i2;
        int i3 = WhenMappings.$EnumSwitchMapping$3[dislikeState.ordinal()];
        if (i3 == 1) {
            i = R.drawable.ic_action_dislike_active;
            i2 = R.string.thumbs_down_selected_content_description;
        } else {
            if (i3 != 2 && i3 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.drawable.ic_action_dislike;
            i2 = R.string.dmusic_like_button_neutral_state_desc;
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i));
    }

    private final void initializeOrUpdateRepeatButton(RepeatMode repeatMode) {
        Pair<Integer, Integer> repeatModeButtonAttributes = getRepeatModeButtonAttributes(repeatMode);
        int iIntValue = repeatModeButtonAttributes.component1().intValue();
        int iIntValue2 = repeatModeButtonAttributes.component2().intValue();
        if (this.repeatModeButton == null) {
            BaseButton baseButtonInitializeButton = initializeButton(iIntValue2, this.activeButtonStyle);
            this.repeatModeButton = baseButtonInitializeButton;
            if (baseButtonInitializeButton != null) {
                baseButtonInitializeButton.setOnClickListener(this.repeatModeButtonClickListener);
            }
        }
        boolean z = repeatMode != RepeatMode.REPEAT_NONE;
        Context context = getContext();
        if (context != null) {
            Drawable drawable = ContextCompat.getDrawable(context, iIntValue2);
            if (drawable != null) {
                BaseButton baseButton = this.repeatModeButton;
                if (z) {
                    if (baseButton != null) {
                        baseButton.setActiveIcon(drawable);
                    }
                } else if (baseButton != null) {
                    baseButton.setIcon(drawable);
                }
            }
            BaseButton baseButton2 = this.repeatModeButton;
            if (baseButton2 != null) {
                baseButton2.setContentDescription(context.getString(iIntValue));
            }
        }
        BaseButton baseButton3 = this.repeatModeButton;
        if (baseButton3 != null) {
            baseButton3.setId(R.id.PersistentPlayerRepeatButton);
        }
        BaseButton baseButton4 = this.repeatModeButton;
        if (baseButton4 == null) {
            return;
        }
        baseButton4.setActive(z);
    }

    private final void initializeOrUpdateShuffleButton(boolean shuffleOn) {
        int i = shuffleOn ? R.string.dmusic_player_turn_off_shuffle_button_description : R.string.dmusic_player_turn_on_shuffle_button_description;
        if (this.shuffleButton == null) {
            BaseButton baseButtonInitializeButton = initializeButton(R.drawable.ic_playback_shuffle, this.activeButtonStyle);
            this.shuffleButton = baseButtonInitializeButton;
            if (baseButtonInitializeButton != null) {
                baseButtonInitializeButton.setOnClickListener(this.shuffleButtonClickListener);
            }
        }
        BaseButton baseButton = this.shuffleButton;
        if (baseButton != null) {
            baseButton.setId(R.id.PersistentPlayerShuffleButton);
        }
        BaseButton baseButton2 = this.shuffleButton;
        if (baseButton2 != null) {
            baseButton2.setActive(shuffleOn);
        }
        BaseButton baseButton3 = this.shuffleButton;
        if (baseButton3 == null) {
            return;
        }
        Context context = getContext();
        baseButton3.setContentDescription(context == null ? null : context.getString(i));
    }

    private final void initializeOrUpdateDownloadButton(DownloadState downloadState) {
        BaseButton baseButton;
        Pair<Integer, Integer> downloadButtonAttributes = getDownloadButtonAttributes(downloadState);
        int iIntValue = downloadButtonAttributes.component1().intValue();
        int iIntValue2 = downloadButtonAttributes.component2().intValue();
        if (this.downloadButton == null) {
            BaseButton baseButtonInitializeButton$default = initializeButton$default(this, iIntValue2, null, 2, null);
            this.downloadButton = baseButtonInitializeButton$default;
            if (baseButtonInitializeButton$default != null) {
                baseButtonInitializeButton$default.setOnClickListener(this.downloadButtonClickListener);
            }
        }
        BaseButton baseButton2 = this.downloadButton;
        if (baseButton2 != null) {
            baseButton2.setId(R.id.section_name);
        }
        Context context = getContext();
        if (context == null) {
            return;
        }
        Drawable drawable = ContextCompat.getDrawable(context, iIntValue2);
        if (drawable != null && (baseButton = this.downloadButton) != null) {
            baseButton.setIcon(drawable);
        }
        BaseButton baseButton3 = this.downloadButton;
        if (baseButton3 == null) {
            return;
        }
        baseButton3.setContentDescription(context.getString(iIntValue));
    }

    private final void initializeOrUpdateDislikeButton(LikeState likeState) {
        Pair<Integer, Integer> dislikeButtonAttributes = getDislikeButtonAttributes(likeState);
        int iIntValue = dislikeButtonAttributes.component1().intValue();
        int iIntValue2 = dislikeButtonAttributes.component2().intValue();
        if (this.dislikeButton == null) {
            BaseButton baseButtonInitializeButton$default = initializeButton$default(this, iIntValue2, null, 2, null);
            this.dislikeButton = baseButtonInitializeButton$default;
            if (baseButtonInitializeButton$default != null) {
                baseButtonInitializeButton$default.setOnClickListener(this.dislikeButtonClickListener);
            }
        }
        BaseButton baseButton = this.dislikeButton;
        if (baseButton != null) {
            baseButton.setId(R.id.ThumbsDownButton);
        }
        boolean z = likeState == LikeState.DISLIKE;
        Context context = getContext();
        if (context != null) {
            Drawable drawable = ContextCompat.getDrawable(context, iIntValue2);
            if (drawable != null) {
                BaseButton baseButton2 = this.dislikeButton;
                if (z) {
                    if (baseButton2 != null) {
                        baseButton2.setActiveIcon(drawable);
                    }
                } else if (baseButton2 != null) {
                    baseButton2.setIcon(drawable);
                }
            }
            BaseButton baseButton3 = this.dislikeButton;
            if (baseButton3 != null) {
                baseButton3.setContentDescription(context.getString(iIntValue));
            }
        }
        BaseButton baseButton4 = this.dislikeButton;
        if (baseButton4 == null) {
            return;
        }
        baseButton4.setActive(z);
    }

    private final void initializeStationFromAnythingButton() {
        BaseButton baseButtonInitializeButton$default = initializeButton$default(this, R.drawable.ic_station_from_anything, null, 2, null);
        this.stationFromAnythingButton = baseButtonInitializeButton$default;
        if (baseButtonInitializeButton$default != null) {
            baseButtonInitializeButton$default.setId(R.id.section_name);
        }
        BaseButton baseButton = this.stationFromAnythingButton;
        if (baseButton != null) {
            Context context = getContext();
            baseButton.setContentDescription(context != null ? context.getString(R.string.start_station_from_anything) : null);
        }
        BaseButton baseButton2 = this.stationFromAnythingButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setOnClickListener(this.stationFromAnythingButtonClickListener);
    }

    static /* synthetic */ BaseButton initializeButton$default(PlayerOverflowMenuMoreButtonsFragment playerOverflowMenuMoreButtonsFragment, int i, ButtonStyle buttonStyle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            buttonStyle = null;
        }
        return playerOverflowMenuMoreButtonsFragment.initializeButton(i, buttonStyle);
    }

    private final BaseButton initializeButton(int iconId, ButtonStyle activeButtonStyle) {
        Drawable drawable;
        Context context = getContext();
        if (context != null && (drawable = ContextCompat.getDrawable(context, iconId)) != null) {
            BaseButton.StyleBuilder styleBuilder = this.iconButtonBuilder;
            BaseButton.StyleBuilder styleBuilderWithIcon = styleBuilder == null ? null : styleBuilder.withIcon(drawable);
            if (activeButtonStyle != null) {
                styleBuilderWithIcon = styleBuilderWithIcon == null ? null : styleBuilderWithIcon.withActiveStyle(activeButtonStyle);
            }
            baseButtonBuild = styleBuilderWithIcon != null ? styleBuilderWithIcon.build(context) : null;
            if (baseButtonBuild != null) {
                baseButtonBuild.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            }
        }
        return baseButtonBuild;
    }

    private final boolean isFreeTierNonPurchasedItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        return UserSubscriptionUtil.isNightwingOnly() && !(mediaAccessInfo != null && mediaAccessInfo.isOwned());
    }

    static /* synthetic */ void setMargins$default(PlayerOverflowMenuMoreButtonsFragment playerOverflowMenuMoreButtonsFragment, View view, int i, int i2, Integer num, Integer num2, Integer num3, Integer num4, int i3, Object obj) {
        playerOverflowMenuMoreButtonsFragment.setMargins(view, (i3 & 2) != 0 ? -2 : i, (i3 & 4) == 0 ? i2 : -2, (i3 & 8) != 0 ? null : num, (i3 & 16) != 0 ? null : num2, (i3 & 32) != 0 ? null : num3, (i3 & 64) == 0 ? num4 : null);
    }

    private final void setMargins(View view, int width, int height, Integer left, Integer top, Integer right, Integer bottom) {
        if (view == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
