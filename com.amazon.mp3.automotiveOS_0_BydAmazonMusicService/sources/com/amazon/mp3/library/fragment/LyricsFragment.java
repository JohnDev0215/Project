package com.amazon.mp3.library.fragment;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImageCondition;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImagePublisher;
import com.amazon.mp3.library.fragment.LyricsFeedbackFragment;
import com.amazon.mp3.lyrics.item.LyricsLine;
import com.amazon.mp3.playback.OnLyricsLineClickListener;
import com.amazon.mp3.playback.OnLyricsLoadedListener;
import com.amazon.mp3.playback.OnNoLyricsDisplayedListener;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.NPSOverlayMetricsReporter;
import com.amazon.mp3.playback.fragment.NPSViews;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.MiniPlayerView;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.mp3.playback.view.MiniPlayerViewWithProgressBar;
import com.amazon.mp3.playback.view.NowPlayingMiniPlayerViewController;
import com.amazon.mp3.playback.view.lyrics.LyricsListViewContainer;
import com.amazon.mp3.playback.view.lyrics.LyricsSelector;
import com.amazon.mp3.playback.view.lyrics.ViewState;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricSharingConfigProvider;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingMetricsSender;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: LyricsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u0090\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002\u0090\u0001B\u001a\u0012\u0010\b\u0002\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010E¢\u0006\u0005\b\u008f\u0001\u0010KJ\b\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\u000bH\u0002J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\u0007H\u0002J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\b\u0010\u0013\u001a\u00020\u0007H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001b\u001a\u00020\u0007H\u0002J$\u0010!\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0002J\b\u0010\"\u001a\u00020\u0007H\u0002J\b\u0010#\u001a\u00020\u0007H\u0002J\b\u0010$\u001a\u00020\u0007H\u0002J\b\u0010%\u001a\u00020\u0007H\u0002J\b\u0010&\u001a\u00020\u0007H\u0002J\b\u0010'\u001a\u00020\u0007H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002J\u0012\u0010+\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010)H\u0016J&\u00101\u001a\u0004\u0018\u0001002\u0006\u0010-\u001a\u00020,2\b\u0010/\u001a\u0004\u0018\u00010.2\b\u0010*\u001a\u0004\u0018\u00010)H\u0016J\u001a\u00103\u001a\u00020\u00072\u0006\u00102\u001a\u0002002\b\u0010*\u001a\u0004\u0018\u00010)H\u0016J\b\u00104\u001a\u00020\u0007H\u0016J\b\u00105\u001a\u00020\u0007H\u0016J\b\u00106\u001a\u00020\u0007H\u0016J\b\u00107\u001a\u00020\u0007H\u0016J\b\u00108\u001a\u00020\u0007H\u0016J\b\u00109\u001a\u00020\u0007H\u0016J\u0010\u0010;\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u0017H\u0016J$\u0010<\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u000b2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010=\u001a\u00020\u0007H\u0016J\u0010\u0010@\u001a\u00020\u00072\u0006\u0010?\u001a\u00020>H\u0016J\b\u0010A\u001a\u00020\u0007H\u0016J\u0010\u0010D\u001a\u00020\u00192\u0006\u0010C\u001a\u00020BH\u0016R*\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010E8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001b\u0010Q\u001a\u00020L8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR\u0018\u0010S\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010V\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0018\u0010Y\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010\\\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0018\u0010_\u001a\u0004\u0018\u00010^8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R\u0018\u0010b\u001a\u0004\u0018\u00010a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bb\u0010cR\u0018\u0010e\u001a\u0004\u0018\u00010d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010fR\u0018\u0010g\u001a\u0004\u0018\u00010^8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010`R\u0018\u0010h\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010j\u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010iR\u0018\u0010l\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010n\u001a\u0004\u0018\u00010^8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bn\u0010`R\u0016\u0010p\u001a\u00020o8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bp\u0010qR\u0016\u0010r\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010sR\"\u0010t\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bt\u0010s\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR\u0018\u0010z\u001a\u0004\u0018\u00010y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bz\u0010{R\u0014\u0010}\u001a\u00020|8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b}\u0010~R\u001f\u0010\u0083\u0001\u001a\u00020\u007f8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0080\u0001\u0010N\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001c\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0084\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R\u001a\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0087\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001a\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u0087\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u0089\u0001R\u001a\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u0087\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u0089\u0001¨\u0006\u0091\u0001"}, d2 = {"Lcom/amazon/mp3/library/fragment/LyricsFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/mp3/playback/playbackcontrol/OnLyricsLineUpdateListener;", "Lcom/amazon/mp3/playback/OnLyricsLineClickListener;", "Lcom/amazon/mp3/playback/OnNoLyricsDisplayedListener;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/mp3/playback/OnLyricsLoadedListener;", "", "initFeedbackButton", "initLyricsSharing", "initShareLyricsButton", "", "getShareLyricsButtonWidth", "count", "changeCounter", "initExitLyricsFullScreenButton", "Landroid/view/View$OnClickListener;", "onClickListener", "setLyricsFeedbackButtonOnClickListener", "openLyricsFeedbackFragment", "", "getTrackAsin", "getTrackTitle", "", "getCurrentPlaybackTimestamp", "", "isLyricsFeedbackSupported", "sendLyricsReportWidgetClickedMetric", "Lcom/amazon/mp3/lyrics/item/LyricsLine;", "lyricsLine", "position", "Landroid/widget/TextView;", "lyricsTextView", "updateSelectedLinesList", "sendUiPageViewMetric", "initLyricsViewContainer", "initStyleSheet", "hideActionBar", "initMiniTransport", "dismiss", "isStage", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onDestroyView", "onResume", "onPause", "onDestroy", "onStart", "onStop", "positionMillis", "onLyricsLineUpdated", "onLyricsLineClicked", "onNoLyricsDisplayed", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "onPlayStateChanged", "Landroid/view/MenuItem;", "item", "onContextItemSelected", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "openLyricsSelectionFragmentSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "getOpenLyricsSelectionFragmentSubject", "()Lio/reactivex/rxjava3/subjects/PublishSubject;", "setOpenLyricsSelectionFragmentSubject", "(Lio/reactivex/rxjava3/subjects/PublishSubject;)V", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "mPlaybackController$delegate", "Lkotlin/Lazy;", "getMPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "mPlaybackController", "Lcom/amazon/mp3/library/fragment/LyricsFeedbackFragment;", "lyricsFeedbackFragment", "Lcom/amazon/mp3/library/fragment/LyricsFeedbackFragment;", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", "mMiniPlayerViewController", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", "Lcom/amazon/music/media/playback/MediaItem;", "mCurrentMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsListViewContainer;", "mLyricsListViewContainer", "Lcom/amazon/mp3/playback/view/lyrics/LyricsListViewContainer;", "Lcom/amazon/ui/foundations/views/BaseButton;", "feedbackButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/mp3/playback/view/MiniPlayerViewWithProgressBar;", "mMiniPlayerViewWithProgressBar", "Lcom/amazon/mp3/playback/view/MiniPlayerViewWithProgressBar;", "Landroid/widget/ImageView;", "mBackgroundImageView", "Landroid/widget/ImageView;", "shareLyricsButton", "selectLinesHeader", "Landroid/widget/TextView;", "selectedLinesCounter", "Landroid/widget/Button;", "nextButton", "Landroid/widget/Button;", "exitLyricsFullScreenButton", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelector;", "lyricsSelector", "Lcom/amazon/mp3/playback/view/lyrics/LyricsSelector;", "mIsStopped", "Z", "transparent", "getTransparent", "()Z", "setTransparent", "(Z)V", "Lcom/amazon/mp3/library/fragment/LyricsDynamicBackgroundSubscriber;", "mLyricsBackgroundSubscriber", "Lcom/amazon/mp3/library/fragment/LyricsDynamicBackgroundSubscriber;", "Lcom/amazon/mp3/playback/fragment/NPSOverlayMetricsReporter;", "mNPSOverlayMetricsReporter", "Lcom/amazon/mp3/playback/fragment/NPSOverlayMetricsReporter;", "Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricSharingConfigProvider;", "lyricSharingConfigProvider$delegate", "getLyricSharingConfigProvider", "()Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricSharingConfigProvider;", "lyricSharingConfigProvider", "Lrx/Subscription;", "mStyleSheetSubsciption", "Lrx/Subscription;", "Landroid/widget/RelativeLayout$LayoutParams;", "getMiniplayerLayout", "()Landroid/widget/RelativeLayout$LayoutParams;", "miniplayerLayout", "getLyricsContainerLayout", "lyricsContainerLayout", "getFeedbackButtonLayout", "feedbackButtonLayout", "<init>", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsFragment extends Fragment implements OnLyricsLineUpdateListener, OnLyricsLineClickListener, OnNoLyricsDisplayedListener, OnPlayStateChangedListener, OnLyricsLoadedListener {
    private static final String TAG = LyricsFragment.class.getSimpleName();
    private BaseButton exitLyricsFullScreenButton;
    private BaseButton feedbackButton;

    /* JADX INFO: renamed from: lyricSharingConfigProvider$delegate, reason: from kotlin metadata */
    private final Lazy lyricSharingConfigProvider;
    private LyricsFeedbackFragment lyricsFeedbackFragment;
    private LyricsSelector lyricsSelector;
    private ImageView mBackgroundImageView;
    private MediaItem mCurrentMediaItem;
    private boolean mIsStopped;
    private LyricsDynamicBackgroundSubscriber mLyricsBackgroundSubscriber;
    private LyricsListViewContainer mLyricsListViewContainer;
    private MiniPlayerViewController mMiniPlayerViewController;
    private MiniPlayerViewWithProgressBar mMiniPlayerViewWithProgressBar;
    private final NPSOverlayMetricsReporter mNPSOverlayMetricsReporter;

    /* JADX INFO: renamed from: mPlaybackController$delegate, reason: from kotlin metadata */
    private final Lazy mPlaybackController;
    private Subscription mStyleSheetSubsciption;
    private Button nextButton;
    private PublishSubject<Boolean> openLyricsSelectionFragmentSubject;
    private TextView selectLinesHeader;
    private TextView selectedLinesCounter;
    private BaseButton shareLyricsButton;
    private boolean transparent;

    public LyricsFragment() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public LyricsFragment(PublishSubject<Boolean> publishSubject) {
        this.openLyricsSelectionFragmentSubject = publishSubject;
        this.mPlaybackController = LazyKt__LazyJVMKt.lazy(new Function0<ActionValidatedPlaybackController>() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$mPlaybackController$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ActionValidatedPlaybackController invoke() {
                return PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
            }
        });
        this.mNPSOverlayMetricsReporter = NPSOverlayMetricsReporter.INSTANCE.getNPSOverlayMetricsReporter();
        this.lyricSharingConfigProvider = LazyKt__LazyJVMKt.lazy(new Function0<LyricSharingConfigProvider>() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$lyricSharingConfigProvider$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricSharingConfigProvider invoke() {
                return new LyricSharingConfigProvider();
            }
        });
    }

    public /* synthetic */ LyricsFragment(PublishSubject publishSubject, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : publishSubject);
    }

    private final ActionValidatedPlaybackController getMPlaybackController() {
        return (ActionValidatedPlaybackController) this.mPlaybackController.getValue();
    }

    private final RelativeLayout.LayoutParams getMiniplayerLayout() {
        MiniPlayerViewWithProgressBar miniPlayerViewWithProgressBar = this.mMiniPlayerViewWithProgressBar;
        return (RelativeLayout.LayoutParams) (miniPlayerViewWithProgressBar == null ? null : miniPlayerViewWithProgressBar.getLayoutParams());
    }

    private final RelativeLayout.LayoutParams getLyricsContainerLayout() {
        LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
        return (RelativeLayout.LayoutParams) (lyricsListViewContainer == null ? null : lyricsListViewContainer.getLayoutParams());
    }

    private final RelativeLayout.LayoutParams getFeedbackButtonLayout() {
        BaseButton baseButton = this.feedbackButton;
        return (RelativeLayout.LayoutParams) (baseButton == null ? null : baseButton.getLayoutParams());
    }

    public final boolean getTransparent() {
        return this.transparent;
    }

    public final void setTransparent(boolean z) {
        this.transparent = z;
    }

    private final LyricSharingConfigProvider getLyricSharingConfigProvider() {
        return (LyricSharingConfigProvider) this.lyricSharingConfigProvider.getValue();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        FragmentActivity activity;
        super.onCreate(savedInstanceState);
        this.mCurrentMediaItem = getMPlaybackController().getCurrentMediaItem();
        getMPlaybackController().addOnPlayStateChangedListener(this);
        if (isStage()) {
            FragmentActivity activity2 = getActivity();
            boolean z = false;
            if (activity2 != null && activity2.getRequestedOrientation() == 5) {
                z = true;
            }
            if (z || (activity = getActivity()) == null) {
                return;
            }
            activity.setRequestedOrientation(5);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        sendUiPageViewMetric();
        return inflater.inflate(R.layout.fragment_lyrics, (ViewGroup) null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FragmentActivity activity = getActivity();
        this.mLyricsListViewContainer = activity == null ? null : (LyricsListViewContainer) activity.findViewById(R.id.LyricsListViewContainer);
        FragmentActivity activity2 = getActivity();
        this.mMiniPlayerViewWithProgressBar = activity2 == null ? null : (MiniPlayerViewWithProgressBar) activity2.findViewById(R.id.mini_player_with_progress_bar);
        FragmentActivity activity3 = getActivity();
        this.mBackgroundImageView = activity3 == null ? null : (ImageView) activity3.findViewById(R.id.BackgroundImageView);
        FragmentActivity activity4 = getActivity();
        this.feedbackButton = activity4 == null ? null : (BaseButton) activity4.findViewById(R.id.lyrics_feedback_button);
        if (AmazonApplication.getCapabilities().isLyricsSharingEnabled() && isStage()) {
            initLyricsSharing();
            RelativeLayout.LayoutParams miniplayerLayout = getMiniplayerLayout();
            if (miniplayerLayout != null) {
                miniplayerLayout.addRule(12);
            }
            RelativeLayout.LayoutParams miniplayerLayout2 = getMiniplayerLayout();
            if (miniplayerLayout2 != null) {
                miniplayerLayout2.bottomMargin = getResources().getDimensionPixelSize(R.dimen.spacer_8);
            }
            RelativeLayout.LayoutParams lyricsContainerLayout = getLyricsContainerLayout();
            if (lyricsContainerLayout != null) {
                lyricsContainerLayout.addRule(2, R.id.mini_player_with_progress_bar);
            }
            RelativeLayout.LayoutParams feedbackButtonLayout = getFeedbackButtonLayout();
            if (feedbackButtonLayout != null) {
                feedbackButtonLayout.addRule(11);
            }
            LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
            ViewGroup.LayoutParams layoutParams = lyricsListViewContainer != null ? lyricsListViewContainer.getLayoutParams() : null;
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(2, R.id.ShareLyrics);
            layoutParams2.addRule(3, R.id.ExitLyricsFullScreen);
        } else {
            RelativeLayout.LayoutParams lyricsContainerLayout2 = getLyricsContainerLayout();
            if (lyricsContainerLayout2 != null) {
                lyricsContainerLayout2.addRule(3, R.id.mini_player_with_progress_bar);
            }
            RelativeLayout.LayoutParams feedbackButtonLayout2 = getFeedbackButtonLayout();
            if (feedbackButtonLayout2 != null) {
                feedbackButtonLayout2.addRule(12, 9);
            }
        }
        initLyricsViewContainer();
        initStyleSheet();
        hideActionBar();
        initMiniTransport();
        initFeedbackButton();
    }

    private final void initFeedbackButton() {
        if (isLyricsFeedbackSupported()) {
            BaseButton baseButton = this.feedbackButton;
            if (baseButton != null) {
                baseButton.setVisibility(0);
            }
            this.mStyleSheetSubsciption = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    LyricsFragment.m1256initFeedbackButton$lambda2(this.f$0, (StyleSheet) obj);
                }
            });
            setLyricsFeedbackButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LyricsFragment.m1257initFeedbackButton$lambda3(this.f$0, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initFeedbackButton$lambda-2, reason: not valid java name */
    public static final void m1256initFeedbackButton$lambda2(LyricsFragment this$0, StyleSheet styleSheet) {
        BaseButton baseButton;
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Drawable drawable = ResourcesCompat.getDrawable(this$0.getResources(), R.drawable.ic_action_flag, null);
        if (drawable == null || (baseButton = this$0.feedbackButton) == null || (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS)) == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initFeedbackButton$lambda-3, reason: not valid java name */
    public static final void m1257initFeedbackButton$lambda3(LyricsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendLyricsReportWidgetClickedMetric();
        this$0.openLyricsFeedbackFragment();
    }

    private final void initLyricsSharing() {
        String mediaItemIdString = MediaItem.getMediaItemIdString(getMPlaybackController().getCurrentMediaItem(), MediaItemId.Type.ASIN, null);
        if (mediaItemIdString != null) {
            getLyricSharingConfigProvider().isLyricsSharable(mediaItemIdString).observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    LyricsFragment.m1258initLyricsSharing$lambda5$lambda4(this.f$0, (Boolean) obj);
                }
            });
        }
        initExitLyricsFullScreenButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLyricsSharing$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1258initLyricsSharing$lambda5$lambda4(LyricsFragment this$0, Boolean isLyricsSharable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(isLyricsSharable, "isLyricsSharable");
        if (isLyricsSharable.booleanValue()) {
            FragmentActivity activity = this$0.getActivity();
            this$0.selectLinesHeader = activity == null ? null : (TextView) activity.findViewById(R.id.SelectLines);
            this$0.lyricsSelector = new LyricsSelector();
            this$0.initShareLyricsButton();
        }
    }

    private final void initShareLyricsButton() {
        FragmentActivity activity = getActivity();
        this.shareLyricsButton = activity == null ? null : (BaseButton) activity.findViewById(R.id.ShareLyrics);
        FragmentActivity activity2 = getActivity();
        this.selectedLinesCounter = activity2 != null ? (TextView) activity2.findViewById(R.id.SelectCounter) : null;
        final BaseButton baseButton = this.shareLyricsButton;
        if (baseButton != null) {
            baseButton.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = baseButton.getLayoutParams();
            layoutParams.width = getShareLyricsButtonWidth();
            baseButton.setLayoutParams(layoutParams);
            StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda5
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    LyricsFragment.m1260initShareLyricsButton$lambda9$lambda8(this.f$0, baseButton, (StyleSheet) obj);
                }
            });
        }
        BaseButton baseButton2 = this.shareLyricsButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsFragment.m1259initShareLyricsButton$lambda11(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initShareLyricsButton$lambda-9$lambda-8, reason: not valid java name */
    public static final void m1260initShareLyricsButton$lambda9$lambda8(LyricsFragment this$0, BaseButton shareLyricsButton, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(shareLyricsButton, "$shareLyricsButton");
        BaseButton.StyleBuilder buttonBuilder = styleSheet == null ? null : styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.GLASS);
        ButtonStyle iconStyle = styleSheet != null ? styleSheet.getIconStyle(IconStyleKey.SECONDARY_GLASS) : null;
        if (buttonBuilder == null) {
            return;
        }
        String string = this$0.getResources().getString(R.string.lyrics_full_screen_share_button_text);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…screen_share_button_text)");
        buttonBuilder.withText(string);
        if (iconStyle != null) {
            buttonBuilder.withActiveStyle(iconStyle);
        }
        buttonBuilder.applyStyle(shareLyricsButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initShareLyricsButton$lambda-11, reason: not valid java name */
    public static final void m1259initShareLyricsButton$lambda11(LyricsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItem mediaItem = this$0.mCurrentMediaItem;
        if (mediaItem != null) {
            LyricsSharingMetricsSender lyricsSharingMetricsSender = LyricsSharingMetricsSender.INSTANCE;
            lyricsSharingMetricsSender.sendLyricsShareButtonClickMetrics(mediaItem);
            LyricsSharingMetricsSender.sendUIClickMetrics$default(lyricsSharingMetricsSender, mediaItem, ActionType.LYRICS_SHARE_BUTTON_CLICK, PageType.LYRICS, null, 8, null);
        }
        PublishSubject<Boolean> publishSubject = this$0.openLyricsSelectionFragmentSubject;
        if (publishSubject == null) {
            return;
        }
        publishSubject.onNext(Boolean.TRUE);
    }

    private final int getShareLyricsButtonWidth() {
        String string = getResources().getString(R.string.lyrics_full_screen_share_button_text);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…screen_share_button_text)");
        float f = getResources().getDisplayMetrics().scaledDensity * 15;
        TextPaint textPaint = new TextPaint(1);
        textPaint.setTextSize(f);
        textPaint.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        return ((int) textPaint.measureText(string)) + ((int) getResources().getDimension(R.dimen.spacer_48));
    }

    private final void changeCounter(int count) {
        if (count == 1) {
            TextView textView = this.selectedLinesCounter;
            if (textView == null) {
                return;
            }
            textView.setText(Intrinsics.stringPlus("1 ", getResources().getString(R.string.line_selected)));
            return;
        }
        if (count > 1 && count <= 3) {
            TextView textView2 = this.selectedLinesCounter;
            if (textView2 == null) {
                return;
            }
            textView2.setText(count + ' ' + getResources().getString(R.string.lines_selected));
            return;
        }
        TextView textView3 = this.selectedLinesCounter;
        if (textView3 == null) {
            return;
        }
        textView3.setText(getResources().getString(R.string.default_no_lines_selected_text));
    }

    private final void initExitLyricsFullScreenButton() {
        FragmentActivity activity = getActivity();
        BaseButton baseButton = activity == null ? null : (BaseButton) activity.findViewById(R.id.ExitLyricsFullScreen);
        this.exitLyricsFullScreenButton = baseButton;
        if (baseButton != null) {
            baseButton.setVisibility(0);
        }
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsFragment.m1254initExitLyricsFullScreenButton$lambda16(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initExitLyricsFullScreenButton$lambda-16, reason: not valid java name */
    public static final void m1254initExitLyricsFullScreenButton$lambda16(final LyricsFragment this$0, StyleSheet styleSheet) {
        BaseButton baseButton;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder iconBuilder = styleSheet == null ? null : styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        Context context = this$0.getContext();
        if (context == null || (baseButton = this$0.exitLyricsFullScreenButton) == null) {
            return;
        }
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_action_cancel);
        if (drawable != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(baseButton);
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsFragment.m1255x584b7d85(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initExitLyricsFullScreenButton$lambda-16$lambda-15$lambda-14$lambda-13, reason: not valid java name */
    public static final void m1255x584b7d85(LyricsFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    private final void setLyricsFeedbackButtonOnClickListener(View.OnClickListener onClickListener) {
        BaseButton baseButton = this.feedbackButton;
        if (baseButton == null) {
            return;
        }
        baseButton.setOnClickListener(onClickListener);
    }

    private final void openLyricsFeedbackFragment() {
        LyricsFeedbackFragment lyricsFeedbackFragment = new LyricsFeedbackFragment();
        this.lyricsFeedbackFragment = lyricsFeedbackFragment;
        lyricsFeedbackFragment.initialize(getTrackAsin(), getTrackTitle(), getCurrentPlaybackTimestamp(), new LyricsFeedbackFragment.CloseCallback() { // from class: com.amazon.mp3.library.fragment.LyricsFragment.openLyricsFeedbackFragment.1
            @Override // com.amazon.mp3.library.fragment.LyricsFeedbackFragment.CloseCallback
            public void onClose() {
                LyricsFeedbackFragment lyricsFeedbackFragment2 = LyricsFragment.this.lyricsFeedbackFragment;
                if (lyricsFeedbackFragment2 == null) {
                    return;
                }
                lyricsFeedbackFragment2.dismiss();
            }
        });
        LyricsFeedbackFragment lyricsFeedbackFragment2 = this.lyricsFeedbackFragment;
        if (lyricsFeedbackFragment2 == null) {
            return;
        }
        lyricsFeedbackFragment2.show(getChildFragmentManager(), LyricsFeedbackFragment.INSTANCE.getTAG());
    }

    private final String getTrackAsin() {
        String mediaItemIdString = MediaItem.getMediaItemIdString(this.mCurrentMediaItem, MediaItemId.Type.ASIN, null);
        Intrinsics.checkNotNullExpressionValue(mediaItemIdString, "getMediaItemIdString(mCu…iaItemId.Type.ASIN, null)");
        return mediaItemIdString;
    }

    private final String getTrackTitle() {
        String name = getMPlaybackController().getCurrentMediaItem().getName();
        Intrinsics.checkNotNullExpressionValue(name, "mPlaybackController.currentMediaItem.name");
        return name;
    }

    private final long getCurrentPlaybackTimestamp() {
        return getMPlaybackController().getPositionMillis();
    }

    private final boolean isLyricsFeedbackSupported() {
        return AmazonApplication.getCapabilities().isLyricsFeedbackSupported();
    }

    private final void sendLyricsReportWidgetClickedMetric() {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.LYRICS_REPORT_WIDGET_CLICK).withInteractionType(InteractionType.TAP).withPageType(PageType.LYRICS).withEntityIdType(EntityIdType.ASIN).withEntityId(getTrackAsin()).withEntityType(EntityType.TRACK).build());
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        FragmentActivity activity;
        super.onDestroyView();
        LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
        if (lyricsListViewContainer != null) {
            lyricsListViewContainer.onDestroy();
        }
        MiniPlayerViewController miniPlayerViewController = this.mMiniPlayerViewController;
        if (miniPlayerViewController != null) {
            miniPlayerViewController.clear();
        }
        this.mLyricsListViewContainer = null;
        this.mMiniPlayerViewWithProgressBar = null;
        this.mBackgroundImageView = null;
        LyricsDynamicBackgroundSubscriber lyricsDynamicBackgroundSubscriber = this.mLyricsBackgroundSubscriber;
        if (lyricsDynamicBackgroundSubscriber != null) {
            DynamicBackgroundImagePublisher.INSTANCE.getInstance().unSubscribeBackgroundImageUpdate(lyricsDynamicBackgroundSubscriber);
        }
        LyricsFeedbackFragment lyricsFeedbackFragment = this.lyricsFeedbackFragment;
        if (lyricsFeedbackFragment != null) {
            lyricsFeedbackFragment.dismissAllowingStateLoss();
        }
        this.lyricsFeedbackFragment = null;
        if (isStage() && (activity = getActivity()) != null) {
            activity.setRequestedOrientation(-1);
        }
        Subscription subscription = this.mStyleSheetSubsciption;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (isStage()) {
            return;
        }
        this.mNPSOverlayMetricsReporter.startTimer(NPSViews.LYRICS_FULL_SCREEN);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        if (isStage()) {
            return;
        }
        NPSOverlayMetricsReporter.stopTimer$default(this.mNPSOverlayMetricsReporter, NPSViews.LYRICS_FULL_SCREEN, false, 2, null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        getMPlaybackController().removeOnLyricsLineUpdateListener(this);
        getMPlaybackController().removeOnPlayStateChangedListener(this);
        getLyricSharingConfigProvider().close();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getMPlaybackController().addOnLyricsLineUpdateListener(this);
        this.mIsStopped = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getMPlaybackController().removeOnLyricsLineUpdateListener(this);
        this.mIsStopped = true;
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener
    public void onLyricsLineUpdated(long positionMillis) {
        LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
        if (lyricsListViewContainer == null) {
            return;
        }
        lyricsListViewContainer.setPlaybackTime(positionMillis);
    }

    @Override // com.amazon.mp3.playback.OnLyricsLineClickListener
    public void onLyricsLineClicked(LyricsLine lyricsLine, int position, TextView lyricsTextView) {
        if (lyricsLine == null) {
            return;
        }
        LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
        if (lyricsListViewContainer != null && lyricsListViewContainer.getLyricsSharingMode()) {
            updateSelectedLinesList(lyricsLine, position, lyricsTextView);
            return;
        }
        getMPlaybackController().seek(lyricsLine.getStartTime(), 0);
        if (getMPlaybackController().getPlayStatus() == PlayStatus.USER_PAUSED) {
            getMPlaybackController().play();
        }
    }

    private final void updateSelectedLinesList(LyricsLine lyricsLine, int position, TextView lyricsTextView) {
        LyricsSelector lyricsSelector = null;
        if (lyricsLine != null) {
            LyricsSelector lyricsSelector2 = this.lyricsSelector;
            if (lyricsSelector2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("lyricsSelector");
                lyricsSelector2 = null;
            }
            lyricsSelector2.eventHandler(lyricsLine, position, lyricsTextView, this.mLyricsListViewContainer);
        }
        LyricsSelector lyricsSelector3 = this.lyricsSelector;
        if (lyricsSelector3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lyricsSelector");
            lyricsSelector3 = null;
        }
        if (lyricsSelector3.getSelectedLinesList().isEmpty()) {
            Button button = this.nextButton;
            if (button != null) {
                button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark_gray)));
            }
        } else {
            Button button2 = this.nextButton;
            if (button2 != null) {
                button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
            }
        }
        LyricsSelector lyricsSelector4 = this.lyricsSelector;
        if (lyricsSelector4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("lyricsSelector");
        } else {
            lyricsSelector = lyricsSelector4;
        }
        changeCounter(lyricsSelector.getSelectedLinesList().size());
    }

    @Override // com.amazon.mp3.playback.OnNoLyricsDisplayedListener
    public void onNoLyricsDisplayed() {
        if (isStage()) {
            return;
        }
        dismiss();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        initStyleSheet();
    }

    private final void sendUiPageViewMetric() {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(PageType.LYRICS.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build());
    }

    private final void initLyricsViewContainer() {
        ImageView imageView;
        LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
        if (lyricsListViewContainer == null) {
            return;
        }
        if (getTransparent() && (imageView = this.mBackgroundImageView) != null) {
            imageView.setVisibility(8);
        }
        lyricsListViewContainer.setPlayStatus(getMPlaybackController().getPlayStatus());
        MediaItem currentMediaItem = getMPlaybackController().getCurrentMediaItem();
        if (currentMediaItem != null) {
            lyricsListViewContainer.showLyricsForMediaItem(currentMediaItem);
        }
        lyricsListViewContainer.changeToState(ViewState.FULL_SCREEN, false);
        lyricsListViewContainer.setOnLyricsLineClickListener(this);
        lyricsListViewContainer.setOnNoLyricsDisplayedListener(this);
        lyricsListViewContainer.setIsFullScreen(true);
    }

    private final void initStyleSheet() {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsFragment.m1261initStyleSheet$lambda25(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initStyleSheet$lambda-25, reason: not valid java name */
    public static final void m1261initStyleSheet$lambda25(LyricsFragment this$0, StyleSheet styleSheet) {
        GridSize gridSize;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsListViewContainer lyricsListViewContainer = this$0.mLyricsListViewContainer;
        if (lyricsListViewContainer != null) {
            lyricsListViewContainer.initStyleSheet(styleSheet);
        }
        Context context = this$0.getContext();
        if (context != null && (gridSize = styleSheet.getGridSize(context)) != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            LyricsListViewContainer lyricsListViewContainer2 = this$0.mLyricsListViewContainer;
            if (lyricsListViewContainer2 != null) {
                LayoutParamUtils.INSTANCE.setLayoutMargins(lyricsListViewContainer2, -1, -1, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : Integer.valueOf(marginSpace), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(marginSpace), (240 & 128) != 0 ? null : null);
            }
        }
        LyricsDynamicBackgroundSubscriber lyricsDynamicBackgroundSubscriber = new LyricsDynamicBackgroundSubscriber(new WeakReference(this$0.mBackgroundImageView), new WeakReference(this$0.getResources()));
        this$0.mLyricsBackgroundSubscriber = lyricsDynamicBackgroundSubscriber;
        DynamicBackgroundImagePublisher companion = DynamicBackgroundImagePublisher.INSTANCE.getInstance();
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        companion.subscribeBackgroundImageUpdate(lyricsDynamicBackgroundSubscriber, styleSheet, new DynamicBackgroundImageCondition(null, null, AlphaKey.GLASS_1, null, 11, null));
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        LyricsListViewContainer lyricsListViewContainer = this.mLyricsListViewContainer;
        if (lyricsListViewContainer != null) {
            lyricsListViewContainer.setPlayStatus(getMPlaybackController().getPlayStatus());
        }
        if (Intrinsics.areEqual(this.mCurrentMediaItem, getMPlaybackController().getCurrentMediaItem())) {
            return;
        }
        this.mCurrentMediaItem = getMPlaybackController().getCurrentMediaItem();
        LyricsListViewContainer lyricsListViewContainer2 = this.mLyricsListViewContainer;
        if (lyricsListViewContainer2 == null) {
            return;
        }
        lyricsListViewContainer2.post(new Runnable() { // from class: com.amazon.mp3.library.fragment.LyricsFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                LyricsFragment.m1262onPlayStateChanged$lambda27(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPlayStateChanged$lambda-27, reason: not valid java name */
    public static final void m1262onPlayStateChanged$lambda27(LyricsFragment this$0) {
        LyricsListViewContainer lyricsListViewContainer;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsListViewContainer lyricsListViewContainer2 = this$0.mLyricsListViewContainer;
        if (lyricsListViewContainer2 != null) {
            lyricsListViewContainer2.resetScrollPolicy();
        }
        LyricsListViewContainer lyricsListViewContainer3 = this$0.mLyricsListViewContainer;
        if (lyricsListViewContainer3 != null) {
            lyricsListViewContainer3.show(false);
        }
        MediaItem mediaItem = this$0.mCurrentMediaItem;
        if (mediaItem == null || (lyricsListViewContainer = this$0.mLyricsListViewContainer) == null) {
            return;
        }
        lyricsListViewContainer.showLyricsForMediaItem(mediaItem);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        MiniPlayerViewController miniPlayerViewController = this.mMiniPlayerViewController;
        if (miniPlayerViewController == null) {
            return false;
        }
        return miniPlayerViewController.onContextItemSelected(item);
    }

    private final void hideActionBar() {
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            FragmentActivity activity = getActivity();
            if (activity == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.playback.activity.NowPlayingFragmentActivity");
            }
            ((NowPlayingFragmentActivity) activity).hideActionBar();
        }
    }

    private final void initMiniTransport() {
        FragmentActivity activity = getActivity();
        MiniPlayerViewWithProgressBar miniPlayerViewWithProgressBar = this.mMiniPlayerViewWithProgressBar;
        MiniPlayerView miniPlayerView = miniPlayerViewWithProgressBar == null ? null : miniPlayerViewWithProgressBar.getMiniPlayerView();
        if (miniPlayerView != null) {
            miniPlayerView.setMinimizeViewVisibility(0);
        }
        if (activity != null) {
            NowPlayingMiniPlayerViewController nowPlayingMiniPlayerViewController = new NowPlayingMiniPlayerViewController(activity, miniPlayerView, this, MiniPlayerViewController.MiniPLayerSource.LYRICS);
            this.mMiniPlayerViewController = nowPlayingMiniPlayerViewController;
            nowPlayingMiniPlayerViewController.refreshPlayer();
        }
    }

    private final void dismiss() {
        FragmentActivity activity;
        FragmentManager supportFragmentManager;
        if (this.mIsStopped || (activity = getActivity()) == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
            return;
        }
        supportFragmentManager.popBackStack();
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }
}
