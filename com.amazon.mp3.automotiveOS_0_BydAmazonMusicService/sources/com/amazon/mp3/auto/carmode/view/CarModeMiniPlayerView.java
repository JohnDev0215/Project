package com.amazon.mp3.auto.carmode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.OnBitmapChangeListener;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.PlayerVerticalSwipeListener;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.playback.view.OnPlayerClickListener;
import com.amazon.mp3.playback.view.PlayerContentView;
import com.amazon.mp3.playback.view.PlayerView;
import com.amazon.mp3.playback.view.PlayerViewConfig;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeMiniPlayerView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000à\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u009c\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002\u009c\u0001B\u001d\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0098\u0001¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002J\u001a\u0010\u0012\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u001a\u0010\u0015\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0007H\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0002J\b\u0010\u0018\u001a\u00020\u0007H\u0002J\b\u0010\u0019\u001a\u00020\u0007H\u0002J\b\u0010\u001a\u001a\u00020\u0007H\u0002J\u000e\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001bJ\u0010\u0010 \u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u001eH\u0014J\u0012\u0010#\u001a\u00020\u00072\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010&\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010$H\u0016J\u0012\u0010)\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010'H\u0016J\u000e\u0010,\u001a\u00020\u00072\u0006\u0010+\u001a\u00020*J\u0010\u0010/\u001a\u00020\u00072\u0006\u0010.\u001a\u00020-H\u0016J\u0010\u00101\u001a\u00020\u00072\u0006\u00100\u001a\u00020-H\u0016J\u0012\u00104\u001a\u00020\u00072\b\u00103\u001a\u0004\u0018\u000102H\u0016J\u0010\u00106\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u0013H\u0016J\u0010\u00108\u001a\u00020\u00072\u0006\u00107\u001a\u00020\u0013H\u0016J\u0010\u0010:\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u0013H\u0016J\u0010\u0010=\u001a\u00020\u00072\u0006\u0010<\u001a\u00020;H\u0016J\u0010\u0010@\u001a\u00020\u00072\u0006\u0010?\u001a\u00020>H\u0016J\u0010\u0010B\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u0013H\u0016J\u0010\u0010D\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u0013H\u0016J\b\u0010E\u001a\u00020\u0003H\u0016J\b\u0010F\u001a\u00020\u0003H\u0016J\b\u0010G\u001a\u00020\u0003H\u0016J\b\u0010H\u001a\u00020\u0007H\u0016J\b\u0010I\u001a\u00020\u0007H\u0016J\u0010\u0010J\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010K\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010L\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010O\u001a\u00020\u00072\u0006\u0010N\u001a\u00020MH\u0016J\u001a\u0010S\u001a\u00020\u00072\b\u0010Q\u001a\u0004\u0018\u00010P2\b\u0010R\u001a\u0004\u0018\u00010PJ\n\u0010T\u001a\u0004\u0018\u00010MH\u0016J\u0012\u0010U\u001a\u00020\u00072\b\u0010Q\u001a\u0004\u0018\u00010PH\u0016J\u0012\u0010V\u001a\u00020\u00072\b\u0010R\u001a\u0004\u0018\u00010PH\u0016J\u0012\u0010Y\u001a\u00020\u00072\b\u0010X\u001a\u0004\u0018\u00010WH\u0016J\u0012\u0010Z\u001a\u00020\u00072\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010[\u001a\u00020\u0007H\u0016J\u0010\u0010]\u001a\u00020\u00072\u0006\u0010\\\u001a\u00020\u0013H\u0016J\u0010\u0010_\u001a\u00020\u00072\u0006\u0010^\u001a\u00020>H\u0016J\u0010\u0010b\u001a\u00020\u00132\b\u0010a\u001a\u0004\u0018\u00010`J\u0010\u0010c\u001a\u00020\u00132\b\u0010a\u001a\u0004\u0018\u00010`J\u0010\u0010e\u001a\u00020\u00072\u0006\u0010d\u001a\u00020>H\u0016J\b\u0010f\u001a\u00020\u0007H\u0014J\b\u0010g\u001a\u00020\u0007H\u0014R\u0018\u0010N\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010hR\u0018\u0010\"\u001a\u0004\u0018\u00010!8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010iR\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010jR\u0018\u0010%\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010kR\u0016\u0010m\u001a\u00020l8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010nR\u0016\u0010o\u001a\u00020>8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010pR\u0016\u00105\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u0010qR\u0016\u00107\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u0010qR\u0016\u0010\\\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010qR\u001b\u0010w\u001a\u00020r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR\u001b\u0010{\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bx\u0010t\u001a\u0004\by\u0010zR\u001b\u0010~\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b|\u0010t\u001a\u0004\b}\u0010zR\u001d\u0010\u0081\u0001\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\r\n\u0004\b\u007f\u0010t\u001a\u0005\b\u0080\u0001\u0010zR \u0010\u0086\u0001\u001a\u00030\u0082\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0083\u0001\u0010t\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R \u0010\u0089\u0001\u001a\u00030\u0082\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0087\u0001\u0010t\u001a\u0006\b\u0088\u0001\u0010\u0085\u0001R \u0010\u008e\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u008b\u0001\u0010t\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R \u0010\u0093\u0001\u001a\u00030\u008f\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0090\u0001\u0010t\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u001c\u0010\u0094\u0001R\u0018\u0010\u0095\u0001\u001a\u00020\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0095\u0001\u0010qR\u001b\u0010\u0096\u0001\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0097\u0001¨\u0006\u009d\u0001"}, d2 = {"Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;", "Landroid/widget/FrameLayout;", "Lcom/amazon/mp3/playback/view/PlayerView;", "Lcom/amazon/mp3/playback/view/PlayerContentView;", "Lcom/amazon/podcast/media/playback/Playback$StateCallback;", "Landroid/content/Context;", "context", "", "initView", "stylizeViews", "Landroid/graphics/Bitmap;", "bitmap", "updatePlayerArtwork", "attachClickListeners", "Lcom/amazon/ui/foundations/views/BaseButton;", "button", "Landroid/view/View$OnClickListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setBaseButtonClickListener", "", "visible", "setBaseButtonVisibility", "setCompositeTouchEventListener", PageUriUtils.DO_PLAY_VALUE, "forward", "showPauseIcon", "showPlayIcon", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "setStyleSheet", "Landroid/view/ContextMenu;", "menu", "onCreateContextMenu", "Lcom/amazon/mp3/playback/view/OnPlayerClickListener;", "onPlayerClickListener", "addPlayerClickListener", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "playerNavigationListener", "addPlayerNavigationListener", "Lcom/amazon/mp3/playback/PlayerVerticalSwipeListener;", "playerVerticalSwipeListener", "addPlayerVerticalSwipeListener", "Lcom/amazon/mp3/auto/carmode/OnBitmapChangeListener;", "onBitmapChangeListener", "addOnBitmapChangeListener", "", NotificationCompat.CATEGORY_PROGRESS, "updateProgress", "duration", "updateDuration", "Lcom/amazon/mp3/playback/PlayerViewModel$PlayerState;", "playerState", "setPlayerState", "hasNext", "hasNextTrack", "hasPrev", "hasPrevTrack", "shuffled", "shuffleStateChanged", "Lcom/amazon/music/media/playback/RepeatMode;", "repeatMode", "repeatStateChanged", "", "rating", "ratingChanged", "added", "addedToLibrary", "connected", "castingStateChanged", "getPrevContentView", "getCurrentContentView", "getNextContentView", "animateToPrev", "animateToNext", "setCurrentBitmap", "setNextBitmap", "setPrevBitmap", "Lcom/amazon/mp3/playback/view/PlayerViewConfig;", "playerViewConfig", "updatePlaybackControl", "", "title", MediaTrack.ROLE_SUBTITLE, "updatePlayerView", "getPlayerViewConfig", "setTitle", "setSubtitle", "Landroid/graphics/drawable/Drawable;", "drawable", "setArtworkDrawable", "setArtworkBitmap", "setDefaultArtwork", "isLoading", "setLoading", "visibility", "setMinimizeViewVisibility", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "next", "prev", "state", "onPlaybackStateChange", "onAttachedToWindow", "onDetachedFromWindow", "Lcom/amazon/mp3/playback/view/PlayerViewConfig;", "Lcom/amazon/mp3/playback/view/OnPlayerClickListener;", "Lcom/amazon/mp3/auto/carmode/OnBitmapChangeListener;", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "Lcom/amazon/mp3/view/TouchEventHandler;", "compositeTouchListener", "Lcom/amazon/mp3/view/TouchEventHandler;", "minSwipeDistance", "I", "Z", "Landroid/widget/ProgressBar;", "loadingSpinner$delegate", "Lkotlin/Lazy;", "getLoadingSpinner", "()Landroid/widget/ProgressBar;", "loadingSpinner", "playButton$delegate", "getPlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "playButton", "nextButton$delegate", "getNextButton", "nextButton", "forwardButton$delegate", "getForwardButton", "forwardButton", "Landroid/widget/TextView;", "titleTextView$delegate", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "Lcom/amazon/ui/foundations/views/BaseImage;", "artwork$delegate", "getArtwork", "()Lcom/amazon/ui/foundations/views/BaseImage;", "artwork", "Landroidx/appcompat/widget/AppCompatImageView;", "gradientOverlay$delegate", "getGradientOverlay", "()Landroidx/appcompat/widget/AppCompatImageView;", "gradientOverlay", "Lcom/amazon/music/views/library/styles/StyleSheet;", "showPlayButton", "currentBitmap", "Landroid/graphics/Bitmap;", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeMiniPlayerView extends FrameLayout implements PlayerView, PlayerContentView, Playback.StateCallback {
    private static final String TAG = CarModeMiniPlayerView.class.getSimpleName();

    /* JADX INFO: renamed from: artwork$delegate, reason: from kotlin metadata */
    private final Lazy artwork;
    private TouchEventHandler compositeTouchListener;
    private Bitmap currentBitmap;

    /* JADX INFO: renamed from: forwardButton$delegate, reason: from kotlin metadata */
    private final Lazy forwardButton;

    /* JADX INFO: renamed from: gradientOverlay$delegate, reason: from kotlin metadata */
    private final Lazy gradientOverlay;
    private boolean hasNext;
    private boolean hasPrev;
    private boolean isLoading;

    /* JADX INFO: renamed from: loadingSpinner$delegate, reason: from kotlin metadata */
    private final Lazy loadingSpinner;
    private int minSwipeDistance;

    /* JADX INFO: renamed from: nextButton$delegate, reason: from kotlin metadata */
    private final Lazy nextButton;
    private OnBitmapChangeListener onBitmapChangeListener;
    private OnPlayerClickListener onPlayerClickListener;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;
    private PlayerNavigationListener playerNavigationListener;
    private PlayerViewConfig playerViewConfig;
    private boolean showPlayButton;
    private StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: compiled from: CarModeMiniPlayerView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PlayerViewModel.PlayerState.values().length];
            iArr[PlayerViewModel.PlayerState.LOADING.ordinal()] = 1;
            iArr[PlayerViewModel.PlayerState.PAUSED.ordinal()] = 2;
            iArr[PlayerViewModel.PlayerState.PLAYING.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerVerticalSwipeListener(PlayerVerticalSwipeListener playerVerticalSwipeListener) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addedToLibrary(boolean added) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void animateToNext() {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void animateToPrev() {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void castingStateChanged(boolean connected) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerContentView getCurrentContentView() {
        return this;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerContentView getNextContentView() {
        return this;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerContentView getPrevContentView() {
        return this;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void ratingChanged(int rating) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void repeatStateChanged(RepeatMode repeatMode) {
        Intrinsics.checkNotNullParameter(repeatMode, "repeatMode");
    }

    public void setArtworkDrawable(Drawable drawable) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setDefaultArtwork() {
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setMinimizeViewVisibility(int visibility) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setNextBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setPrevBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void shuffleStateChanged(boolean shuffled) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updateDuration(long duration) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updateProgress(long progress) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeMiniPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.compositeTouchListener = new TouchEventHandler();
        this.minSwipeDistance = ScreenUtil.convertDpToPx(25);
        this.loadingSpinner = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$loadingSpinner$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R.id.car_mode_mini_player_loading_spinner);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_mini_player_play_button);
            }
        });
        this.nextButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$nextButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_mini_player_next_button);
            }
        });
        this.forwardButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$forwardButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_mini_player_forward_button);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_mini_player_title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_mini_player_subtitle);
            }
        });
        this.artwork = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$artwork$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                return (BaseImage) this.this$0.findViewById(R.id.car_mode_mini_player_artwork);
            }
        });
        this.gradientOverlay = LazyKt__LazyJVMKt.lazy(new Function0<AppCompatImageView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$gradientOverlay$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AppCompatImageView invoke() {
                return (AppCompatImageView) this.this$0.findViewById(R.id.car_mode_mini_player_overlay);
            }
        });
        this.showPlayButton = true;
        initView(context);
    }

    private final ProgressBar getLoadingSpinner() {
        Object value = this.loadingSpinner.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-loadingSpinner>(...)");
        return (ProgressBar) value;
    }

    private final BaseButton getPlayButton() {
        Object value = this.playButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getNextButton() {
        Object value = this.nextButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-nextButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getForwardButton() {
        Object value = this.forwardButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-forwardButton>(...)");
        return (BaseButton) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final BaseImage getArtwork() {
        Object value = this.artwork.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artwork>(...)");
        return (BaseImage) value;
    }

    private final AppCompatImageView getGradientOverlay() {
        Object value = this.gradientOverlay.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-gradientOverlay>(...)");
        return (AppCompatImageView) value;
    }

    public final void setStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        stylizeViews();
    }

    private final void initView(Context context) {
        View.inflate(context, R.layout.car_mode_mini_player_view, this);
        stylizeViews();
        attachClickListeners();
        setCompositeTouchEventListener();
    }

    private final void stylizeViews() {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        FontStyle fontStyle;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton.StyleBuilder styleBuilderWithIcon3;
        BaseButton.StyleBuilder styleBuilderWithIcon4;
        Integer color;
        if (AmazonApplication.getCapabilities().isCarModeV2Enabled()) {
            StyleSheet styleSheet = this.styleSheet;
            if (styleSheet != null && (color = styleSheet.getColor(ColorKey.COLOR_BASE, AlphaKey.GLASS_5)) != null) {
                setBackgroundColor(color.intValue());
            }
        } else {
            setBackgroundColor(getContext().getColor(R.color.mini_player));
            getGradientOverlay().setVisibility(8);
        }
        StyleSheet styleSheet2 = this.styleSheet;
        BaseButton.StyleBuilder iconBuilder = styleSheet2 == null ? null : styleSheet2.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
        if (this.showPlayButton) {
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_play_tray);
            if (drawable != null && iconBuilder != null && (styleBuilderWithIcon4 = iconBuilder.withIcon(drawable)) != null) {
                styleBuilderWithIcon4.applyStyle(getPlayButton());
            }
        } else {
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_pause_tray);
            if (drawable2 != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable2)) != null) {
                styleBuilderWithIcon.applyStyle(getPlayButton());
            }
        }
        Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_next_tray);
        if (drawable3 != null && iconBuilder != null && (styleBuilderWithIcon3 = iconBuilder.withIcon(drawable3)) != null) {
            styleBuilderWithIcon3.applyStyle(getNextButton());
        }
        Drawable drawable4 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_fast_forward_mini_player);
        if (drawable4 != null && iconBuilder != null && (styleBuilderWithIcon2 = iconBuilder.withIcon(drawable4)) != null) {
            styleBuilderWithIcon2.applyStyle(getForwardButton());
        }
        StyleSheet styleSheet3 = this.styleSheet;
        if (styleSheet3 != null && (fontStyle = styleSheet3.getFontStyle(FontStyleKey.PRIMARY_T1_HUGE_SHARPGROTESK)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
        }
        getTitleTextView().setSelected(true);
    }

    @Override // android.view.View
    protected void onCreateContextMenu(ContextMenu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onCreateContextMenu(menu);
        PlayerNavigationListener playerNavigationListener = this.playerNavigationListener;
        if (playerNavigationListener == null) {
            return;
        }
        playerNavigationListener.onCreateContextMenu(menu);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerClickListener(OnPlayerClickListener onPlayerClickListener) {
        this.onPlayerClickListener = onPlayerClickListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerNavigationListener(PlayerNavigationListener playerNavigationListener) {
        this.playerNavigationListener = playerNavigationListener;
    }

    public final void addOnBitmapChangeListener(OnBitmapChangeListener onBitmapChangeListener) {
        Intrinsics.checkNotNullParameter(onBitmapChangeListener, "onBitmapChangeListener");
        this.onBitmapChangeListener = onBitmapChangeListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setPlayerState(PlayerViewModel.PlayerState playerState) {
        if (playerState == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[playerState.ordinal()];
        if (i == 1) {
            setLoading(true);
            showPauseIcon();
            this.showPlayButton = false;
        } else if (i == 2) {
            setLoading(false);
            showPlayIcon();
            this.showPlayButton = true;
        } else {
            if (i != 3) {
                return;
            }
            setLoading(false);
            showPauseIcon();
            this.showPlayButton = false;
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void hasNextTrack(boolean hasNext) {
        this.hasNext = hasNext;
        getNextButton().setEnabled(hasNext);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void hasPrevTrack(boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setCurrentBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        setArtworkBitmap(bitmap);
    }

    private final void updatePlayerArtwork(Bitmap bitmap) {
        Integer cornerSize;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (cornerSize = styleSheet.getCornerSize(CornerSizeKey.SMALL)) != null) {
            getArtwork().applyCornerRadius(cornerSize.intValue());
        }
        getArtwork().getImage().setScaleType(ImageView.ScaleType.FIT_CENTER);
        BaseImage artwork = getArtwork();
        Drawable drawableConvertBitmapToDrawable = BitmapUtil.convertBitmapToDrawable(getResources(), bitmap);
        Intrinsics.checkNotNullExpressionValue(drawableConvertBitmapToDrawable, "convertBitmapToDrawable(resources, bitmap)");
        artwork.setImage(drawableConvertBitmapToDrawable);
        getArtwork().setVisibility(0);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updatePlaybackControl(PlayerViewConfig playerViewConfig) {
        Intrinsics.checkNotNullParameter(playerViewConfig, "playerViewConfig");
        this.playerViewConfig = playerViewConfig;
        setBaseButtonVisibility(getPlayButton(), playerViewConfig.isShowPlayPause());
        if (playerViewConfig.isPodcastView()) {
            setBaseButtonVisibility(getForwardButton(), playerViewConfig.isShowForward());
            setBaseButtonVisibility(getNextButton(), false);
        } else {
            setBaseButtonVisibility(getForwardButton(), false);
            setBaseButtonVisibility(getNextButton(), playerViewConfig.isShowNext());
        }
    }

    public final void updatePlayerView(String title, String subtitle) {
        setVisibility((title == null && subtitle == null) ? 8 : 0);
        if (Intrinsics.areEqual(getTitleTextView().getText(), title) && Intrinsics.areEqual(getSubtitleTextView().getText(), subtitle)) {
            return;
        }
        setTitle(title);
        setSubtitle(subtitle);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerViewConfig getPlayerViewConfig() {
        return this.playerViewConfig;
    }

    private final void attachClickListeners() {
        setBaseButtonClickListener(getPlayButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeMiniPlayerView.m326attachClickListeners$lambda7(this.f$0, view);
            }
        });
        setBaseButtonClickListener(getNextButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeMiniPlayerView.m327attachClickListeners$lambda8(this.f$0, view);
            }
        });
        setBaseButtonClickListener(getForwardButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeMiniPlayerView.m328attachClickListeners$lambda9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-7, reason: not valid java name */
    public static final void m326attachClickListeners$lambda7(CarModeMiniPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-8, reason: not valid java name */
    public static final void m327attachClickListeners$lambda8(CarModeMiniPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.next(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-9, reason: not valid java name */
    public static final void m328attachClickListeners$lambda9(CarModeMiniPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.forward();
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setTitle(String title) {
        getTitleTextView().setText(title);
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setSubtitle(String subtitle) {
        getSubtitleTextView().setText(subtitle);
    }

    public void setArtworkBitmap(Bitmap bitmap) {
        if (Intrinsics.areEqual(this.currentBitmap, bitmap) || bitmap == null) {
            return;
        }
        this.currentBitmap = bitmap;
        updatePlayerArtwork(bitmap);
        OnBitmapChangeListener onBitmapChangeListener = this.onBitmapChangeListener;
        if (onBitmapChangeListener == null) {
            return;
        }
        onBitmapChangeListener.onChange(bitmap);
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        if (isLoading) {
            getPlayButton().setVisibility(8);
            getForwardButton().setEnabled(false);
            getLoadingSpinner().setVisibility(0);
            getLoadingSpinner().setIndeterminate(true);
            return;
        }
        getPlayButton().setVisibility(0);
        getLoadingSpinner().setVisibility(8);
        getLoadingSpinner().setIndeterminate(false);
        getForwardButton().setEnabled(true);
    }

    private final void setBaseButtonClickListener(BaseButton button, View.OnClickListener listener) {
        if (button == null) {
            return;
        }
        button.setOnClickListener(listener);
    }

    private final void setBaseButtonVisibility(BaseButton button, boolean visible) {
        if (button != null) {
            button.setVisibility(visible ? 0 : 8);
        }
    }

    private final void setCompositeTouchEventListener() {
        this.minSwipeDistance = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.compositeTouchListener.setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView.setCompositeTouchEventListener.1
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
                if (Math.abs(x) > CarModeMiniPlayerView.this.getWidth() / 3) {
                    CarModeMiniPlayerView.this.next(InteractionType.SWIPE);
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
                if (Math.abs(x) > CarModeMiniPlayerView.this.getWidth() / 3) {
                    CarModeMiniPlayerView.this.prev(InteractionType.SWIPE);
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
                PlayerNavigationListener playerNavigationListener;
                if (Math.abs(y) < CarModeMiniPlayerView.this.minSwipeDistance || (playerNavigationListener = CarModeMiniPlayerView.this.playerNavigationListener) == null) {
                    return;
                }
                playerNavigationListener.showNowPlaying(InteractionType.TAP);
            }
        });
        this.compositeTouchListener.setOnClickListener(getContext(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeMiniPlayerView.m329setCompositeTouchEventListener$lambda10(this.f$0, view);
            }
        });
        setOnTouchListener(this.compositeTouchListener.getTouchEventListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setCompositeTouchEventListener$lambda-10, reason: not valid java name */
    public static final void m329setCompositeTouchEventListener$lambda10(CarModeMiniPlayerView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlayerNavigationListener playerNavigationListener = this$0.playerNavigationListener;
        if (playerNavigationListener == null) {
            return;
        }
        playerNavigationListener.showNowPlaying(InteractionType.TAP);
    }

    private final void play() {
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        OnPlayerClickListener onPlayerClickListener = this.onPlayerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onPlayPauseClicked();
    }

    private final void forward() {
        OnPlayerClickListener onPlayerClickListener = this.onPlayerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onForwardClicked();
    }

    public final boolean next(InteractionType type) {
        OnPlayerClickListener onPlayerClickListener;
        if (!this.hasNext || (onPlayerClickListener = this.onPlayerClickListener) == null) {
            return false;
        }
        if (type == null) {
            type = InteractionType.TAP;
        }
        return onPlayerClickListener.onNextClicked(type);
    }

    public final boolean prev(InteractionType type) {
        OnPlayerClickListener onPlayerClickListener;
        if (!this.hasPrev || (onPlayerClickListener = this.onPlayerClickListener) == null) {
            return false;
        }
        if (type == null) {
            type = InteractionType.TAP;
        }
        return onPlayerClickListener.onPrevClicked(type);
    }

    private final void showPauseIcon() {
        getPlayButton().clearAnimation();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_pause_tray);
        if (drawable != null) {
            getPlayButton().setIcon(drawable);
        }
        getPlayButton().setContentDescription(getResources().getString(R.string.dmusic_player_btn_pause_description));
    }

    private final void showPlayIcon() {
        getPlayButton().clearAnimation();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_play_tray);
        if (drawable != null) {
            getPlayButton().setIcon(drawable);
        }
        getPlayButton().setContentDescription(getResources().getString(R.string.dmusic_player_btn_play_description));
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        setPlayerState(CarModePresetsUtility.INSTANCE.convertPlaybackState(state));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Podcast.getPlayback().addStateCallback(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Podcast.getPlayback().removeStateCallback(this);
        super.onDetachedFromWindow();
    }
}
