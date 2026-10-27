package com.amazon.mp3.view.stage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingBaseButton;
import com.amazon.mp3.casting.CastingButtonHelper;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playback.view.PlayQueueButton;
import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.MediaPlaybackUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.CompositeOverflowButton;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider;
import com.amazon.mp3.view.player.EnabledViewState;
import com.amazon.mp3.view.player.PlayerViewEnabledStateManager;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageBottomIconsView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u0095\u00012\u00020\u0001:\u0002\u0095\u0001B.\b\u0007\u0012\b\u0010\u008f\u0001\u001a\u00030\u008e\u0001\u0012\f\b\u0002\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0090\u0001\u0012\t\b\u0002\u0010\u0092\u0001\u001a\u00020\u000b¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0002H\u0002J]\u0010\u0012\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0015\u001a\u00020\u0014H\u0002J,\u0010\u001b\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\n\u0010\u001e\u001a\u0004\u0018\u00010\u0014H\u0002J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0014H\u0002J\n\u0010 \u001a\u0004\u0018\u00010\u0014H\u0002J\u0010\u0010#\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020!H\u0002J\u0012\u0010$\u001a\u0004\u0018\u00010\u00142\u0006\u0010\"\u001a\u00020!H\u0002J\u0018\u0010)\u001a\u00020\u00022\u0006\u0010&\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020'J8\u0010/\u001a\u00020\u00022\u0006\u0010*\u001a\u00020!2\u0006\u0010&\u001a\u00020%2\b\b\u0002\u0010+\u001a\u00020'2\b\b\u0002\u0010,\u001a\u00020'2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010-H\u0007J\u0016\u00101\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020!2\u0006\u00100\u001a\u00020'J\u0006\u00102\u001a\u00020\u0002J\u0006\u00103\u001a\u00020\u0002J\u0006\u00104\u001a\u00020\u0002J\u0014\u00107\u001a\u00020\u00022\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u000b05J\u0010\u0010:\u001a\u00020\u00022\b\u00109\u001a\u0004\u0018\u000108J\u000e\u0010=\u001a\u00020\u00022\u0006\u0010<\u001a\u00020;J\u0006\u0010>\u001a\u00020\u0002R\u001e\u00106\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b6\u0010?R\u0018\u0010A\u001a\u0004\u0018\u00010@8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010BR$\u0010D\u001a\u0004\u0018\u00010C8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0018\u0010J\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010KR\u0018\u0010M\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010KR\u0018\u0010N\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010KR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u001b\u0010Z\u001a\u00020U8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010YR\u001d\u0010^\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b[\u0010W\u001a\u0004\b\\\u0010]R\u001d\u0010a\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b_\u0010W\u001a\u0004\b`\u0010]R\u001d\u0010d\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bb\u0010W\u001a\u0004\bc\u0010]R\u001d\u0010g\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\be\u0010W\u001a\u0004\bf\u0010]R\u001d\u0010j\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bh\u0010W\u001a\u0004\bi\u0010]R\u001d\u0010m\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bk\u0010W\u001a\u0004\bl\u0010]R\u001d\u0010p\u001a\u0004\u0018\u00010\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bn\u0010W\u001a\u0004\bo\u0010]R\u001b\u0010u\u001a\u00020q8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\br\u0010W\u001a\u0004\bs\u0010tR\u0018\u0010<\u001a\u0004\u0018\u00010;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010vR\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010wR\u001a\u0010y\u001a\b\u0012\u0004\u0012\u00020!0x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010zR\u001a\u0010{\u001a\b\u0012\u0004\u0012\u00020!0x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010zR\u001a\u0010|\u001a\b\u0012\u0004\u0012\u00020!0x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010zR\u001f\u0010\u0081\u0001\u001a\u0004\u0018\u00010}8BX\u0082\u0084\u0002¢\u0006\r\n\u0004\b~\u0010W\u001a\u0005\b\u007f\u0010\u0080\u0001R,\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0082\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0017\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b(\u0010\u0089\u0001R\u0017\u0010&\u001a\u00020%8\u0002@\u0002X\u0082.¢\u0006\u0007\n\u0005\b&\u0010\u008a\u0001R\u0017\u0010\u008b\u0001\u001a\u00020'8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0017\u0010\u008d\u0001\u001a\u00020'8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u008c\u0001¨\u0006\u0096\u0001"}, d2 = {"Lcom/amazon/mp3/view/stage/StageBottomButtonsView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "addCompositeOverflowButton", "Lcom/amazon/mp3/view/player/EnabledViewState;", "getCastingButtonEnableState", "shareButtonClicked", "toggleCaptionsButton", "expandContractButtonClicked", "Landroid/view/View;", "view", "", "width", "height", "left", "top", "right", "bottom", "setMargins", "(Landroid/view/View;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCastingButton", "Landroid/graphics/drawable/Drawable;", "icon", "activeIcon", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "customBuilder", "getButtonWithDrawable", "Lcom/amazon/mp3/playback/view/PlayQueueButton;", "getPlayQueueButton", "getLandscapeClosedCaptionsButton", "getLandscapeExpandContractButton", "getShareButton", "Lcom/amazon/mp3/view/stage/StageBottomIconType;", "buttonType", "getResIdForButtonType", "getButtonForButtonType", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "forceLandscapeVideoButtons", "initViewStyling", "iconTypeStage", Constants.ENABLE_DISABLE, "canClickWhenDisabled", "Landroid/view/View$OnClickListener;", "onClickListener", "addButton", "show", "updateButtonVisibility", "validate", "updateOverflowMenuSecondaryIconTo", "unregisterCastingListeners", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "openLyricsCardSelectionFragmentSubject", "setOpenLyricsCardSelectionFragmentSubject", "Landroid/graphics/Bitmap;", "blurAlbumArt", "setBlurAlbumArt", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "setLifecycleOwner", "clearLifecycleOwner", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lcom/amazon/mp3/casting/CastingButtonHelper;", "castingButtonHelper", "Lcom/amazon/mp3/casting/CastingButtonHelper;", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "videoPlayerStateProvider", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "getVideoPlayerStateProvider", "()Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "setVideoPlayerStateProvider", "(Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;)V", "iconButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "tinyIconButtonBuilder", "smallIconButtonBuilder", "landscapeIconButtonBuilder", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "enabledStateManager", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "Landroid/widget/LinearLayout;", "actionIconContainer$delegate", "Lkotlin/Lazy;", "getActionIconContainer", "()Landroid/widget/LinearLayout;", "actionIconContainer", "playQueueDrawable$delegate", "getPlayQueueDrawable", "()Landroid/graphics/drawable/Drawable;", "playQueueDrawable", "shareDrawable$delegate", "getShareDrawable", "shareDrawable", "alexaDrawable$delegate", "getAlexaDrawable", "alexaDrawable", "closedCaptionsActiveDrawable$delegate", "getClosedCaptionsActiveDrawable", "closedCaptionsActiveDrawable", "closedCaptionsInactiveDrawable$delegate", "getClosedCaptionsInactiveDrawable", "closedCaptionsInactiveDrawable", "portraitExpandDrawable$delegate", "getPortraitExpandDrawable", "portraitExpandDrawable", "landscapeContractDrawable$delegate", "getLandscapeContractDrawable", "landscapeContractDrawable", "Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricSharingConfigProvider;", "lyricSharingConfigProvider$delegate", "getLyricSharingConfigProvider", "()Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricSharingConfigProvider;", "lyricSharingConfigProvider", "Landroidx/lifecycle/LifecycleOwner;", "Landroid/graphics/Bitmap;", "", "audioButtons", "Ljava/util/List;", "portraitVideoButtons", "landscapeVideoButtons", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "videoPlayer$delegate", "getVideoPlayer", "()Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "videoPlayer", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "stageOverflowMenuSecondaryIconStateProvider", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "getStageOverflowMenuSecondaryIconStateProvider", "()Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "setStageOverflowMenuSecondaryIconStateProvider", "(Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;)V", "Z", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isLyricsSharingEnabled", "()Z", "isVideoTrack", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageBottomButtonsView extends ConstraintLayout {
    private static final String TAG = StageBottomButtonsView.class.getSimpleName();

    /* JADX INFO: renamed from: actionIconContainer$delegate, reason: from kotlin metadata */
    private final Lazy actionIconContainer;

    /* JADX INFO: renamed from: alexaDrawable$delegate, reason: from kotlin metadata */
    private final Lazy alexaDrawable;
    private final List<StageBottomIconType> audioButtons;
    private Bitmap blurAlbumArt;
    private CastingButtonHelper castingButtonHelper;

    /* JADX INFO: renamed from: closedCaptionsActiveDrawable$delegate, reason: from kotlin metadata */
    private final Lazy closedCaptionsActiveDrawable;

    /* JADX INFO: renamed from: closedCaptionsInactiveDrawable$delegate, reason: from kotlin metadata */
    private final Lazy closedCaptionsInactiveDrawable;
    private final PlayerViewEnabledStateManager enabledStateManager;
    private boolean forceLandscapeVideoButtons;
    private BaseButton.StyleBuilder iconButtonBuilder;

    /* JADX INFO: renamed from: landscapeContractDrawable$delegate, reason: from kotlin metadata */
    private final Lazy landscapeContractDrawable;
    private BaseButton.StyleBuilder landscapeIconButtonBuilder;
    private final List<StageBottomIconType> landscapeVideoButtons;
    private LifecycleOwner lifecycleOwner;

    /* JADX INFO: renamed from: lyricSharingConfigProvider$delegate, reason: from kotlin metadata */
    private final Lazy lyricSharingConfigProvider;
    private PublishSubject<Integer> openLyricsCardSelectionFragmentSubject;

    /* JADX INFO: renamed from: playQueueDrawable$delegate, reason: from kotlin metadata */
    private final Lazy playQueueDrawable;
    private final ActionValidatedPlaybackController playbackController;

    /* JADX INFO: renamed from: portraitExpandDrawable$delegate, reason: from kotlin metadata */
    private final Lazy portraitExpandDrawable;
    private final List<StageBottomIconType> portraitVideoButtons;

    /* JADX INFO: renamed from: shareDrawable$delegate, reason: from kotlin metadata */
    private final Lazy shareDrawable;
    private BaseButton.StyleBuilder smallIconButtonBuilder;
    private StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider;
    private StyleSheet styleSheet;
    private BaseButton.StyleBuilder tinyIconButtonBuilder;

    /* JADX INFO: renamed from: videoPlayer$delegate, reason: from kotlin metadata */
    private final Lazy videoPlayer;
    private VideoPlayerStateProvider videoPlayerStateProvider;

    /* JADX INFO: compiled from: StageBottomIconsView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[StageBottomIconType.values().length];
            iArr[StageBottomIconType.PLAY_QUEUE.ordinal()] = 1;
            iArr[StageBottomIconType.CASTING.ordinal()] = 2;
            iArr[StageBottomIconType.SHARE.ordinal()] = 3;
            iArr[StageBottomIconType.ALEXA.ordinal()] = 4;
            iArr[StageBottomIconType.CLOSED_CAPTIONS.ordinal()] = 5;
            iArr[StageBottomIconType.EXPAND_CONTRACT.ordinal()] = 6;
            iArr[StageBottomIconType.OVERFLOW.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[StageOverflowMenuSecondaryIconState.values().length];
            iArr2[StageOverflowMenuSecondaryIconState.REPEAT_ONE.ordinal()] = 1;
            iArr2[StageOverflowMenuSecondaryIconState.REPEAT_ALL.ordinal()] = 2;
            iArr2[StageOverflowMenuSecondaryIconState.SHUFFLE.ordinal()] = 3;
            iArr2[StageOverflowMenuSecondaryIconState.NONE.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageBottomButtonsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ StageBottomButtonsView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StageBottomButtonsView(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        View.inflate(context, R.layout.stage_bottom_icons_layout, this);
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        this.enabledStateManager = new PlayerViewEnabledStateManager(controller);
        this.actionIconContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$actionIconContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R.id.action_icon_container);
            }
        });
        this.playQueueDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$playQueueDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_playback_playqueue);
            }
        });
        this.shareDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$shareDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_action_share);
            }
        });
        this.alexaDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$alexaDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_alexa_bauhaus);
            }
        });
        this.closedCaptionsActiveDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$closedCaptionsActiveDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_other_closedcaptionsus_active);
            }
        });
        this.closedCaptionsInactiveDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$closedCaptionsInactiveDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_other_closedcaptionsus_inactive);
            }
        });
        this.portraitExpandDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$portraitExpandDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_other_expand);
            }
        });
        this.landscapeContractDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$landscapeContractDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R.drawable.ic_other_contract);
            }
        });
        this.lyricSharingConfigProvider = LazyKt__LazyJVMKt.lazy(new Function0<LyricSharingConfigProvider>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$lyricSharingConfigProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricSharingConfigProvider invoke() {
                return new LyricSharingConfigProvider();
            }
        });
        StageBottomIconType stageBottomIconType = StageBottomIconType.SHARE;
        StageBottomIconType stageBottomIconType2 = StageBottomIconType.PLAY_QUEUE;
        StageBottomIconType stageBottomIconType3 = StageBottomIconType.OVERFLOW;
        this.audioButtons = CollectionsKt__CollectionsKt.listOf((Object[]) new StageBottomIconType[]{stageBottomIconType, StageBottomIconType.CASTING, stageBottomIconType2, stageBottomIconType3});
        this.portraitVideoButtons = CollectionsKt__CollectionsKt.listOf((Object[]) new StageBottomIconType[]{stageBottomIconType, stageBottomIconType2, stageBottomIconType3});
        this.landscapeVideoButtons = CollectionsKt__CollectionsKt.listOf((Object[]) new StageBottomIconType[]{StageBottomIconType.CLOSED_CAPTIONS, StageBottomIconType.EXPAND_CONTRACT});
        this.videoPlayer = LazyKt__LazyJVMKt.lazy(new Function0<PrimeVideoPlayer>() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$videoPlayer$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimeVideoPlayer invoke() {
                PlaybackConfig playbackConfig;
                Playback playback = Playback.getInstance();
                Player primeVideoPlayer = (playback == null || (playbackConfig = playback.getPlaybackConfig()) == null) ? null : playbackConfig.getPrimeVideoPlayer();
                if (primeVideoPlayer instanceof PrimeVideoPlayer) {
                    return (PrimeVideoPlayer) primeVideoPlayer;
                }
                return null;
            }
        });
    }

    public final VideoPlayerStateProvider getVideoPlayerStateProvider() {
        return this.videoPlayerStateProvider;
    }

    public final void setVideoPlayerStateProvider(VideoPlayerStateProvider videoPlayerStateProvider) {
        this.videoPlayerStateProvider = videoPlayerStateProvider;
    }

    private final LinearLayout getActionIconContainer() {
        Object value = this.actionIconContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-actionIconContainer>(...)");
        return (LinearLayout) value;
    }

    private final Drawable getPlayQueueDrawable() {
        return (Drawable) this.playQueueDrawable.getValue();
    }

    private final Drawable getShareDrawable() {
        return (Drawable) this.shareDrawable.getValue();
    }

    private final Drawable getAlexaDrawable() {
        return (Drawable) this.alexaDrawable.getValue();
    }

    private final Drawable getClosedCaptionsActiveDrawable() {
        return (Drawable) this.closedCaptionsActiveDrawable.getValue();
    }

    private final Drawable getClosedCaptionsInactiveDrawable() {
        return (Drawable) this.closedCaptionsInactiveDrawable.getValue();
    }

    private final Drawable getPortraitExpandDrawable() {
        return (Drawable) this.portraitExpandDrawable.getValue();
    }

    private final Drawable getLandscapeContractDrawable() {
        return (Drawable) this.landscapeContractDrawable.getValue();
    }

    private final boolean isLyricsSharingEnabled() {
        return AmazonApplication.getCapabilities().isLyricsSharingEnabled();
    }

    private final LyricSharingConfigProvider getLyricSharingConfigProvider() {
        return (LyricSharingConfigProvider) this.lyricSharingConfigProvider.getValue();
    }

    private final PrimeVideoPlayer getVideoPlayer() {
        return (PrimeVideoPlayer) this.videoPlayer.getValue();
    }

    private final boolean isVideoTrack() {
        return NowPlayingUtil.isVideoPlaying();
    }

    public final StageOverflowMenuSecondaryIconStateProvider getStageOverflowMenuSecondaryIconStateProvider() {
        return this.stageOverflowMenuSecondaryIconStateProvider;
    }

    public final void setStageOverflowMenuSecondaryIconStateProvider(StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider) {
        this.stageOverflowMenuSecondaryIconStateProvider = stageOverflowMenuSecondaryIconStateProvider;
    }

    public static /* synthetic */ void initViewStyling$default(StageBottomButtonsView stageBottomButtonsView, StyleSheet styleSheet, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        stageBottomButtonsView.initViewStyling(styleSheet, z);
    }

    public final void initViewStyling(StyleSheet styleSheet, boolean forceLandscapeVideoButtons) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilder2;
        List<StageBottomIconType> list;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.forceLandscapeVideoButtons = forceLandscapeVideoButtons;
        IconSizeKey iconSizeKey = IconSizeKey.TINY;
        IconStyleKey iconStyleKey = IconStyleKey.GLASS;
        this.tinyIconButtonBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        this.smallIconButtonBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, iconStyleKey);
        if (NowPlayingUtil.isSmallScreen(getContext())) {
            styleBuilder = this.tinyIconButtonBuilder;
        } else {
            styleBuilder = this.smallIconButtonBuilder;
        }
        this.iconButtonBuilder = styleBuilder;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (styleSheet.getLandscapeType(context) == LandscapeType.TABLET_LANDSCAPE) {
            styleBuilder2 = this.smallIconButtonBuilder;
        } else {
            styleBuilder2 = this.tinyIconButtonBuilder;
        }
        this.landscapeIconButtonBuilder = styleBuilder2;
        getActionIconContainer().removeAllViews();
        if (forceLandscapeVideoButtons) {
            list = this.landscapeVideoButtons;
        } else if (isVideoTrack()) {
            if (ScreenUtil.isPortrait()) {
                list = this.portraitVideoButtons;
            } else {
                list = this.landscapeVideoButtons;
            }
        } else {
            list = this.audioButtons;
        }
        this.enabledStateManager.setControlsAreEnabled(true);
        this.enabledStateManager.updateConfigForMediaItem(this.playbackController.getCurrentMediaItem());
        for (StageBottomIconType stageBottomIconType : list) {
            if (stageBottomIconType == StageBottomIconType.OVERFLOW) {
                addCompositeOverflowButton();
            } else {
                addButton$default(this, stageBottomIconType, styleSheet, false, false, null, 28, null);
            }
        }
    }

    private final void addCompositeOverflowButton() {
        StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        CompositeOverflowButton compositeOverflowButton = new CompositeOverflowButton(context);
        compositeOverflowButton.setId(getResIdForButtonType(StageBottomIconType.OVERFLOW));
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        compositeOverflowButton.initViewStyling(styleSheet);
        compositeOverflowButton.getPrimaryButton().setId(R.id.MoreOptions);
        compositeOverflowButton.getPrimaryButton().setEnabled(isEnabled());
        if (getActionIconContainer().getChildCount() != 0) {
            setMargins$default(this, compositeOverflowButton, 0, 0, Integer.valueOf((int) getResources().getDimension(R.dimen.spacer_32)), null, null, null, 118, null);
        }
        getActionIconContainer().addView(compositeOverflowButton);
        compositeOverflowButton.getPrimaryButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageBottomButtonsView.m1737addCompositeOverflowButton$lambda4(this.f$0, view);
            }
        });
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider = this.stageOverflowMenuSecondaryIconStateProvider;
        if (stageOverflowMenuSecondaryIconStateProvider == null || (stageOverflowMenuSecondaryIconState = stageOverflowMenuSecondaryIconStateProvider.getStageOverflowMenuSecondaryIconState()) == null) {
            return;
        }
        compositeOverflowButton.updateSecondaryIconTo(stageOverflowMenuSecondaryIconState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addCompositeOverflowButton$lambda-4, reason: not valid java name */
    public static final void m1737addCompositeOverflowButton$lambda4(StageBottomButtonsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        NowPlayingFragmentActivity nowPlayingFragmentActivity = context instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) context : null;
        if (nowPlayingFragmentActivity == null) {
            return;
        }
        Fragment currentFragment = nowPlayingFragmentActivity.getCurrentFragment();
        NowPlayingFragment nowPlayingFragment = currentFragment instanceof NowPlayingFragment ? (NowPlayingFragment) currentFragment : null;
        if (nowPlayingFragment == null) {
            return;
        }
        nowPlayingFragment.onStageOverflowMenuButtonClicked();
    }

    public static /* synthetic */ void addButton$default(StageBottomButtonsView stageBottomButtonsView, StageBottomIconType stageBottomIconType, StyleSheet styleSheet, boolean z, boolean z2, View.OnClickListener onClickListener, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        boolean z3 = z;
        if ((i & 8) != 0) {
            z2 = false;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            onClickListener = null;
        }
        stageBottomButtonsView.addButton(stageBottomIconType, styleSheet, z3, z4, onClickListener);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0167  */
    @kotlin.jvm.JvmOverloads
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addButton(com.amazon.mp3.view.stage.StageBottomIconType r17, com.amazon.music.views.library.styles.StyleSheet r18, boolean r19, boolean r20, android.view.View.OnClickListener r21) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.stage.StageBottomButtonsView.addButton(com.amazon.mp3.view.stage.StageBottomIconType, com.amazon.music.views.library.styles.StyleSheet, boolean, boolean, android.view.View$OnClickListener):void");
    }

    private final EnabledViewState getCastingButtonEnableState() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (CastingUtil.isCastingAvailable() && currentMediaItem != null && !NowPlayingUtil.isVideoPlaying()) {
            ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
            if (!childUserUtil.isChildUser(applicationContext)) {
                return EnabledViewState.ENABLED;
            }
        }
        return EnabledViewState.GONE;
    }

    public final void updateButtonVisibility(StageBottomIconType buttonType, boolean show) {
        Intrinsics.checkNotNullParameter(buttonType, "buttonType");
        BaseButton buttonForButtonType = getButtonForButtonType(buttonType);
        if (buttonForButtonType == null) {
            return;
        }
        buttonForButtonType.setVisibility(show ? 0 : 8);
    }

    private final void shareButtonClicked() {
        LifecycleOwner lifecycleOwner;
        final MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        String mediaItemIdString = MediaItem.getMediaItemIdString(currentMediaItem, MediaItemId.Type.ASIN, null);
        if (mediaItemIdString == null || !isLyricsSharingEnabled() || (lifecycleOwner = this.lifecycleOwner) == null) {
            MediaItemHelper.shareMediaItem(getContext(), currentMediaItem, false, this.openLyricsCardSelectionFragmentSubject, this.blurAlbumArt);
        } else {
            if (lifecycleOwner == null) {
                return;
            }
            getLyricSharingConfigProvider().isLyricsSharable(mediaItemIdString).observe(lifecycleOwner, new Observer() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$$ExternalSyntheticLambda4
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    StageBottomButtonsView.m1741shareButtonClicked$lambda10$lambda9(this.f$0, currentMediaItem, (Boolean) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: shareButtonClicked$lambda-10$lambda-9, reason: not valid java name */
    public static final void m1741shareButtonClicked$lambda10$lambda9(StageBottomButtonsView this$0, MediaItem mediaItem, Boolean isLyricsSharable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(isLyricsSharable, "isLyricsSharable");
        MediaItemHelper.shareMediaItem(context, mediaItem, isLyricsSharable.booleanValue(), this$0.openLyricsCardSelectionFragmentSubject, this$0.blurAlbumArt);
    }

    private final void toggleCaptionsButton() {
        String nowPlayingMediaItemAsin = MediaPlaybackUtil.getNowPlayingMediaItemAsin();
        PrimeVideoPlayer videoPlayer = getVideoPlayer();
        boolean z = videoPlayer != null && videoPlayer.isShowingCaptions();
        ActionType actionType = z ? ActionType.VIDEO_TOGGLE_CAPTION_OFF : ActionType.VIDEO_TOGGLE_CAPTION_ON;
        if (nowPlayingMediaItemAsin != null) {
            VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, nowPlayingMediaItemAsin, actionType, null, PageType.NOW_PLAYING_STAGE, null, null, 52, null);
        }
        VideoPlayerStateProvider videoPlayerStateProvider = this.videoPlayerStateProvider;
        if (videoPlayerStateProvider != null) {
            videoPlayerStateProvider.setCaptionsEnabled(!z);
        }
        PrimeVideoPlayer videoPlayer2 = getVideoPlayer();
        if (videoPlayer2 == null) {
            return;
        }
        videoPlayer2.showCaptions(!z);
    }

    private final void expandContractButtonClicked() {
        Activity activity;
        ActionType actionType;
        String nowPlayingMediaItemAsin = MediaPlaybackUtil.getNowPlayingMediaItemAsin();
        if (ScreenUtil.isLandscape()) {
            Context context = getContext();
            activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                activity.setRequestedOrientation(1);
            }
            actionType = ActionType.SELECT_PORTRAIT_MODE;
        } else {
            Context context2 = getContext();
            activity = context2 instanceof Activity ? (Activity) context2 : null;
            if (activity != null) {
                activity.setRequestedOrientation(0);
            }
            actionType = ActionType.SELECT_LANDSCAPE_MODE;
        }
        ActionType actionType2 = actionType;
        if (nowPlayingMediaItemAsin == null) {
            return;
        }
        VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, nowPlayingMediaItemAsin, actionType2, null, PageType.NOW_PLAYING_STAGE, null, PageSubType.ONDEMAND_VIDEO, 20, null);
    }

    static /* synthetic */ void setMargins$default(StageBottomButtonsView stageBottomButtonsView, View view, int i, int i2, Integer num, Integer num2, Integer num3, Integer num4, int i3, Object obj) {
        stageBottomButtonsView.setMargins(view, (i3 & 2) != 0 ? -2 : i, (i3 & 4) == 0 ? i2 : -2, (i3 & 8) != 0 ? null : num, (i3 & 16) != 0 ? null : num2, (i3 & 32) != 0 ? null : num3, (i3 & 64) == 0 ? num4 : null);
    }

    private final void setMargins(View view, int width, int height, Integer left, Integer top, Integer right, Integer bottom) {
        if (view == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final BaseButton getCastingButton() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        CastingBaseButton castingBaseButton = new CastingBaseButton(context);
        castingBaseButton.setId(getResIdForButtonType(StageBottomIconType.CASTING));
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        castingBaseButton.initStyleSheet(styleSheet);
        this.castingButtonHelper = new CastingButtonHelper(new WeakReference(castingBaseButton));
        return castingBaseButton;
    }

    static /* synthetic */ BaseButton getButtonWithDrawable$default(StageBottomButtonsView stageBottomButtonsView, Drawable drawable, Drawable drawable2, BaseButton.StyleBuilder styleBuilder, int i, Object obj) {
        if ((i & 2) != 0) {
            drawable2 = null;
        }
        if ((i & 4) != 0) {
            styleBuilder = stageBottomButtonsView.iconButtonBuilder;
        }
        return stageBottomButtonsView.getButtonWithDrawable(drawable, drawable2, styleBuilder);
    }

    private final BaseButton getButtonWithDrawable(Drawable icon, Drawable activeIcon, BaseButton.StyleBuilder customBuilder) {
        BaseButton.StyleBuilder styleBuilderWithIcon = (icon == null || customBuilder == null) ? null : customBuilder.withIcon(icon);
        if (activeIcon != null && styleBuilderWithIcon != null) {
            styleBuilderWithIcon.withActiveIcon(activeIcon);
        }
        if (styleBuilderWithIcon == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return styleBuilderWithIcon.build(context);
    }

    private final PlayQueueButton getPlayQueueButton(Drawable icon) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        PlayQueueButton playQueueButton = new PlayQueueButton(getContext());
        playQueueButton.setPageType(PageType.NOW_PLAYING);
        playQueueButton.setId(getResIdForButtonType(StageBottomIconType.PLAY_QUEUE));
        if (icon != null && (styleBuilder = this.iconButtonBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(icon)) != null) {
            styleBuilderWithIcon.applyStyle(playQueueButton);
        }
        return playQueueButton;
    }

    private final BaseButton getLandscapeClosedCaptionsButton() {
        Drawable closedCaptionsActiveDrawable;
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithActiveIcon;
        BaseButton.StyleBuilder styleBuilderWithActiveStyle;
        Drawable closedCaptionsInactiveDrawable = getClosedCaptionsInactiveDrawable();
        if (closedCaptionsInactiveDrawable == null || (closedCaptionsActiveDrawable = getClosedCaptionsActiveDrawable()) == null) {
            return null;
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.ACCENT_GLASS);
        if (iconStyle == null || (styleBuilder = this.landscapeIconButtonBuilder) == null || (styleBuilderWithIcon = styleBuilder.withIcon(closedCaptionsInactiveDrawable)) == null || (styleBuilderWithActiveIcon = styleBuilderWithIcon.withActiveIcon(closedCaptionsActiveDrawable)) == null || (styleBuilderWithActiveStyle = styleBuilderWithActiveIcon.withActiveStyle(iconStyle)) == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        final BaseButton baseButtonBuild = styleBuilderWithActiveStyle.build(context);
        if (baseButtonBuild == null) {
            return null;
        }
        baseButtonBuild.setId(getResIdForButtonType(StageBottomIconType.CLOSED_CAPTIONS));
        VideoPlayerStateProvider videoPlayerStateProvider = getVideoPlayerStateProvider();
        boolean z = false;
        if (videoPlayerStateProvider != null && videoPlayerStateProvider.getIsCaptionsEnabled()) {
            z = true;
        }
        baseButtonBuild.setActive(z);
        baseButtonBuild.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageBottomButtonsView.m1738x15289f6c(this.f$0, baseButtonBuild, view);
            }
        });
        return baseButtonBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getLandscapeClosedCaptionsButton$lambda-21$lambda-20$lambda-19$lambda-18$lambda-17, reason: not valid java name */
    public static final void m1738x15289f6c(StageBottomButtonsView this$0, BaseButton this_apply, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_apply, "$this_apply");
        this$0.toggleCaptionsButton();
        this_apply.setActive(!this_apply.getIsActive());
    }

    private final BaseButton getLandscapeExpandContractButton() {
        BaseButton buttonWithDrawable$default = getButtonWithDrawable$default(this, (ScreenUtil.isLandscape() || this.forceLandscapeVideoButtons) ? getLandscapeContractDrawable() : getPortraitExpandDrawable(), null, this.landscapeIconButtonBuilder, 2, null);
        if (buttonWithDrawable$default == null) {
            return null;
        }
        buttonWithDrawable$default.setId(getResIdForButtonType(StageBottomIconType.EXPAND_CONTRACT));
        buttonWithDrawable$default.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageBottomButtonsView.m1739getLandscapeExpandContractButton$lambda23$lambda22(this.f$0, view);
            }
        });
        return buttonWithDrawable$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getLandscapeExpandContractButton$lambda-23$lambda-22, reason: not valid java name */
    public static final void m1739getLandscapeExpandContractButton$lambda23$lambda22(StageBottomButtonsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.expandContractButtonClicked();
    }

    private final BaseButton getShareButton() {
        BaseButton buttonWithDrawable$default = getButtonWithDrawable$default(this, getShareDrawable(), null, null, 6, null);
        if (buttonWithDrawable$default == null) {
            return null;
        }
        buttonWithDrawable$default.setId(getResIdForButtonType(StageBottomIconType.SHARE));
        buttonWithDrawable$default.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageBottomButtonsView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageBottomButtonsView.m1740getShareButton$lambda25$lambda24(this.f$0, view);
            }
        });
        return buttonWithDrawable$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getShareButton$lambda-25$lambda-24, reason: not valid java name */
    public static final void m1740getShareButton$lambda25$lambda24(StageBottomButtonsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.shareButtonClicked();
    }

    private final int getResIdForButtonType(StageBottomIconType buttonType) {
        switch (WhenMappings.$EnumSwitchMapping$0[buttonType.ordinal()]) {
            case 1:
                return R.id.PersistentPlayerPlayQueueButton;
            case 2:
                return R.id.CastingButton;
            case 3:
                return R.id.PersistentPlayerShareButton;
            case 4:
                return R.id.StageLandscapeVideoAlexaButton;
            case 5:
                return R.id.StageVideoClosedCaptionsButton;
            case 6:
                return R.id.VideoPlayerExpandContract;
            case 7:
                return R.id.StageCompositeButton;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final BaseButton getButtonForButtonType(StageBottomIconType buttonType) {
        return (BaseButton) findViewById(getResIdForButtonType(buttonType));
    }

    public final void validate() {
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider;
        StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState;
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider2;
        StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState2;
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider3;
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider4;
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider5 = this.stageOverflowMenuSecondaryIconStateProvider;
        StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState3 = stageOverflowMenuSecondaryIconStateProvider5 == null ? null : stageOverflowMenuSecondaryIconStateProvider5.getStageOverflowMenuSecondaryIconState();
        int i = stageOverflowMenuSecondaryIconState3 == null ? -1 : WhenMappings.$EnumSwitchMapping$1[stageOverflowMenuSecondaryIconState3.ordinal()];
        if (i == 1) {
            if (this.playbackController.getRepeatMode() == RepeatMode.REPEAT_ONE || (stageOverflowMenuSecondaryIconStateProvider = this.stageOverflowMenuSecondaryIconStateProvider) == null) {
                return;
            }
            if (this.playbackController.isShuffled()) {
                stageOverflowMenuSecondaryIconState = StageOverflowMenuSecondaryIconState.SHUFFLE;
            } else {
                stageOverflowMenuSecondaryIconState = StageOverflowMenuSecondaryIconState.NONE;
            }
            stageOverflowMenuSecondaryIconStateProvider.setStageOverflowMenuSecondaryIconState(stageOverflowMenuSecondaryIconState);
            return;
        }
        if (i == 2) {
            if (this.playbackController.getRepeatMode() == RepeatMode.REPEAT_ALL || (stageOverflowMenuSecondaryIconStateProvider2 = this.stageOverflowMenuSecondaryIconStateProvider) == null) {
                return;
            }
            if (this.playbackController.isShuffled()) {
                stageOverflowMenuSecondaryIconState2 = StageOverflowMenuSecondaryIconState.SHUFFLE;
            } else {
                stageOverflowMenuSecondaryIconState2 = StageOverflowMenuSecondaryIconState.NONE;
            }
            stageOverflowMenuSecondaryIconStateProvider2.setStageOverflowMenuSecondaryIconState(stageOverflowMenuSecondaryIconState2);
            return;
        }
        if (i == 3) {
            if (this.playbackController.isShuffled() || (stageOverflowMenuSecondaryIconStateProvider3 = this.stageOverflowMenuSecondaryIconStateProvider) == null) {
                return;
            }
            stageOverflowMenuSecondaryIconStateProvider3.setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.NONE);
            return;
        }
        if (i == 4 && this.playbackController.isShuffled() && (stageOverflowMenuSecondaryIconStateProvider4 = this.stageOverflowMenuSecondaryIconStateProvider) != null) {
            stageOverflowMenuSecondaryIconStateProvider4.setStageOverflowMenuSecondaryIconState(StageOverflowMenuSecondaryIconState.SHUFFLE);
        }
    }

    public final void updateOverflowMenuSecondaryIconTo() {
        StageOverflowMenuSecondaryIconState stageOverflowMenuSecondaryIconState;
        CompositeOverflowButton compositeOverflowButton;
        validate();
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider = this.stageOverflowMenuSecondaryIconStateProvider;
        if (stageOverflowMenuSecondaryIconStateProvider == null || (stageOverflowMenuSecondaryIconState = stageOverflowMenuSecondaryIconStateProvider.getStageOverflowMenuSecondaryIconState()) == null || (compositeOverflowButton = (CompositeOverflowButton) findViewById(getResIdForButtonType(StageBottomIconType.OVERFLOW))) == null) {
            return;
        }
        compositeOverflowButton.updateSecondaryIconTo(stageOverflowMenuSecondaryIconState);
    }

    public final void unregisterCastingListeners() {
        CastingButtonHelper castingButtonHelper = this.castingButtonHelper;
        if (castingButtonHelper == null) {
            return;
        }
        castingButtonHelper.unregisterListeners();
    }

    public final void setOpenLyricsCardSelectionFragmentSubject(PublishSubject<Integer> openLyricsCardSelectionFragmentSubject) {
        Intrinsics.checkNotNullParameter(openLyricsCardSelectionFragmentSubject, "openLyricsCardSelectionFragmentSubject");
        this.openLyricsCardSelectionFragmentSubject = openLyricsCardSelectionFragmentSubject;
    }

    public final void setBlurAlbumArt(Bitmap blurAlbumArt) {
        this.blurAlbumArt = blurAlbumArt;
    }

    public final void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.lifecycleOwner = lifecycleOwner;
    }

    public final void clearLifecycleOwner() {
        this.lifecycleOwner = null;
    }
}
