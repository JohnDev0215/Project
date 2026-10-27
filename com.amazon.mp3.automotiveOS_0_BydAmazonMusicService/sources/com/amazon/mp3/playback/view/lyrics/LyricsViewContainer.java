package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.playback.FrugalLyricsListener;
import com.amazon.mp3.playback.OnNoLyricsDisplayedListener;
import com.amazon.mp3.playback.view.lyrics.LyricsProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsViewContainer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000 r2\u00020\u00012\u00020\u0002:\u0001rB\u0011\b\u0016\u0012\u0006\u0010l\u001a\u00020k¢\u0006\u0004\bm\u0010nB\u0019\b\u0016\u0012\u0006\u0010l\u001a\u00020k\u0012\u0006\u0010p\u001a\u00020o¢\u0006\u0004\bm\u0010qJ\b\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0002J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\f\u001a\u00020\u0003H\u0002J\u0006\u0010\r\u001a\u00020\u0003J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011J\u0010\u0010\u0015\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0014J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0003J\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0003J\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aJ\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001cH\u0016J\u0010\u0010!\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001fH\u0016J\u0006\u0010\"\u001a\u00020\u0003J\b\u0010#\u001a\u00020\u0005H\u0016J\u000e\u0010%\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0003J\u000e\u0010'\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0003J\b\u0010(\u001a\u00020\u0005H\u0016J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010*\u001a\u00020)H\u0016J\b\u0010,\u001a\u00020\u0005H\u0016J\b\u0010-\u001a\u00020\u0005H\u0014J\b\u0010.\u001a\u00020\u0005H\u0014J\u0018\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020\tH\u0014J\u000e\u00103\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001cJ\u0006\u00104\u001a\u00020\u0003J\u0006\u00105\u001a\u00020\u0003J\u000e\u00107\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u0003J\u0012\u0010:\u001a\u00020\u00052\b\u00109\u001a\u0004\u0018\u000108H&J\u0010\u0010<\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\tH&J\n\u0010=\u001a\u0004\u0018\u00010/H&R\u0016\u0010>\u001a\u00020\u00038\u0004@\u0004X\u0085\u000e¢\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010@\u001a\u0004\u0018\u00010\u00148\u0004@\u0004X\u0085\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010C\u001a\u00020B8\u0004X\u0085\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010F\u001a\u00020E8\u0004X\u0085\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010?R\u0018\u0010J\u001a\u0004\u0018\u00010I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010L\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010MR(\u0010O\u001a\u0004\u0018\u00010\u00032\b\u0010N\u001a\u0004\u0018\u00010\u00038\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010?R\u001b\u0010Y\u001a\u00020T8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bU\u0010V\u001a\u0004\bW\u0010XR\u001b\u0010]\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010V\u001a\u0004\b[\u0010\\R\u001b\u0010b\u001a\u00020^8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b_\u0010V\u001a\u0004\b`\u0010aR\u001b\u0010g\u001a\u00020c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bd\u0010V\u001a\u0004\be\u0010fR\u0014\u0010h\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u0016\u0010j\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010?¨\u0006s"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsViewContainer;", "Landroid/widget/LinearLayout;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsProvider$LoadLyricsCallback;", "", "isLyricsAvailable", "", "updateIsLyricsDisplayed", "showFrugalLyricsView", "hideFrugalLyricsView", "", "position", "shouldShowFrugalLyrics", "isFromJapanAndPlayInOffline", "getHasLyricsLoaded", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "showLyricsForMediaItem", "Lcom/amazon/mp3/playback/OnNoLyricsDisplayedListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnNoLyricsDisplayedListener", "Lcom/amazon/mp3/playback/FrugalLyricsListener;", "setFrugalLyricsListener", "enabled", "setFrugalLyricsEnabled", "hasUserInteracted", "setHasUserInteracted", "Lcom/amazon/mp3/playback/view/lyrics/ViewState;", "getCachedViewState", "", "time", "setPlaybackTime", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "isStage", "onDestroy", "isShown", "show", "isBlocked", "setIsBlocked", "onLoadDelay", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "lyrics", "onLyricsLoaded", "onNoLyrics", "onDetachedFromWindow", "onAttachedToWindow", "Landroid/view/View;", "changedView", "visibility", "onVisibilityChanged", "getLinePosition", "getHasUserInteracted", "isFrugalLyricsViewVisible", "showCompleteLyrics", "setShowFullLyricsOnOverlays", "Lcom/amazon/music/media/playback/PlayStatus;", "playStatus", "setPlayStatus", "linePosition", "setCurrentPlayingLyricsLine", "getLyricsView", "mHasLyricsLoaded", "Z", "mFrugalLyricsListener", "Lcom/amazon/mp3/playback/FrugalLyricsListener;", "Lcom/amazon/mp3/playback/view/lyrics/CachedViewState;", "mCachedViewState", "Lcom/amazon/mp3/playback/view/lyrics/CachedViewState;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsMetricsReporter;", "mLyricsMetricsReporter", "Lcom/amazon/mp3/playback/view/lyrics/LyricsMetricsReporter;", "mIsFrugalLyricsEnabled", "Lcom/amazon/mp3/playback/view/lyrics/LyricsTracker;", "mLyricsTracker", "Lcom/amazon/mp3/playback/view/lyrics/LyricsTracker;", "mOnNoLyricsDisplayedListener", "Lcom/amazon/mp3/playback/OnNoLyricsDisplayedListener;", "value", "mIsCurrentLyricsEmpty", "Ljava/lang/Boolean;", "setMIsCurrentLyricsEmpty", "(Ljava/lang/Boolean;)V", "mIsBlocked", "Lcom/amazon/mp3/playback/view/lyrics/LyricsProvider;", "mLyricsProvider$delegate", "Lkotlin/Lazy;", "getMLyricsProvider", "()Lcom/amazon/mp3/playback/view/lyrics/LyricsProvider;", "mLyricsProvider", "mFrugalLyricsView$delegate", "getMFrugalLyricsView", "()Landroid/widget/LinearLayout;", "mFrugalLyricsView", "Lcom/amazon/ui/foundations/views/BaseButton;", "mFrugalLyricsBadge$delegate", "getMFrugalLyricsBadge", "()Lcom/amazon/ui/foundations/views/BaseButton;", "mFrugalLyricsBadge", "Landroid/widget/TextView;", "mFrugalLyricsTextView$delegate", "getMFrugalLyricsTextView", "()Landroid/widget/TextView;", "mFrugalLyricsTextView", "viewId", "I", "showFullLyricsOnOverlays", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attributeSet", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class LyricsViewContainer extends LinearLayout implements LyricsProvider.LoadLyricsCallback {

    @JvmField
    protected final CachedViewState mCachedViewState;

    /* JADX INFO: renamed from: mFrugalLyricsBadge$delegate, reason: from kotlin metadata */
    private final Lazy mFrugalLyricsBadge;

    @JvmField
    protected FrugalLyricsListener mFrugalLyricsListener;

    /* JADX INFO: renamed from: mFrugalLyricsTextView$delegate, reason: from kotlin metadata */
    private final Lazy mFrugalLyricsTextView;

    /* JADX INFO: renamed from: mFrugalLyricsView$delegate, reason: from kotlin metadata */
    private final Lazy mFrugalLyricsView;

    @JvmField
    protected boolean mHasLyricsLoaded;
    private boolean mIsBlocked;
    private Boolean mIsCurrentLyricsEmpty;
    private boolean mIsFrugalLyricsEnabled;

    @JvmField
    protected final LyricsMetricsReporter mLyricsMetricsReporter;

    /* JADX INFO: renamed from: mLyricsProvider$delegate, reason: from kotlin metadata */
    private final Lazy mLyricsProvider;
    private LyricsTracker mLyricsTracker;
    private OnNoLyricsDisplayedListener mOnNoLyricsDisplayedListener;
    private boolean showFullLyricsOnOverlays;
    private final int viewId;

    public abstract View getLyricsView();

    public abstract void setCurrentPlayingLyricsLine(int linePosition);

    public abstract void setPlayStatus(PlayStatus playStatus);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsViewContainer(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        CachedViewState cachedViewState = CachedViewState.getInstance();
        Intrinsics.checkNotNullExpressionValue(cachedViewState, "getInstance()");
        this.mCachedViewState = cachedViewState;
        LyricsMetricsReporter lyricsMetricsReporter = LyricsMetricsReporter.getInstance(getContext());
        Intrinsics.checkNotNullExpressionValue(lyricsMetricsReporter, "getInstance(context)");
        this.mLyricsMetricsReporter = lyricsMetricsReporter;
        this.mLyricsProvider = LazyKt__LazyJVMKt.lazy(new Function0<LyricsProvider>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mLyricsProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricsProvider invoke() {
                return new LyricsProvider(this.this$0.getContext().getApplicationContext());
            }
        });
        this.mFrugalLyricsView = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mFrugalLyricsView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R.id.FrugalLyricsView);
            }
        });
        this.mFrugalLyricsBadge = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mFrugalLyricsBadge$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.FrugalLyricsBadge);
            }
        });
        this.mFrugalLyricsTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mFrugalLyricsTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.FrugalLyricsTextView);
            }
        });
        this.viewId = hashCode();
        LayoutInflater.from(getContext()).inflate(R.layout.view_frugal_lyrics, this);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        CachedViewState cachedViewState = CachedViewState.getInstance();
        Intrinsics.checkNotNullExpressionValue(cachedViewState, "getInstance()");
        this.mCachedViewState = cachedViewState;
        LyricsMetricsReporter lyricsMetricsReporter = LyricsMetricsReporter.getInstance(getContext());
        Intrinsics.checkNotNullExpressionValue(lyricsMetricsReporter, "getInstance(context)");
        this.mLyricsMetricsReporter = lyricsMetricsReporter;
        this.mLyricsProvider = LazyKt__LazyJVMKt.lazy(new Function0<LyricsProvider>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mLyricsProvider$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricsProvider invoke() {
                return new LyricsProvider(this.this$0.getContext().getApplicationContext());
            }
        });
        this.mFrugalLyricsView = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mFrugalLyricsView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R.id.FrugalLyricsView);
            }
        });
        this.mFrugalLyricsBadge = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mFrugalLyricsBadge$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.FrugalLyricsBadge);
            }
        });
        this.mFrugalLyricsTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$mFrugalLyricsTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.FrugalLyricsTextView);
            }
        });
        this.viewId = hashCode();
        LayoutInflater.from(getContext()).inflate(R.layout.view_frugal_lyrics, this);
    }

    private final void setMIsCurrentLyricsEmpty(Boolean bool) {
        this.mIsCurrentLyricsEmpty = bool;
        updateIsLyricsDisplayed();
    }

    private final LyricsProvider getMLyricsProvider() {
        return (LyricsProvider) this.mLyricsProvider.getValue();
    }

    private final LinearLayout getMFrugalLyricsView() {
        Object value = this.mFrugalLyricsView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mFrugalLyricsView>(...)");
        return (LinearLayout) value;
    }

    private final BaseButton getMFrugalLyricsBadge() {
        Object value = this.mFrugalLyricsBadge.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mFrugalLyricsBadge>(...)");
        return (BaseButton) value;
    }

    private final TextView getMFrugalLyricsTextView() {
        Object value = this.mFrugalLyricsTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mFrugalLyricsTextView>(...)");
        return (TextView) value;
    }

    /* JADX INFO: renamed from: getHasLyricsLoaded, reason: from getter */
    public final boolean getMHasLyricsLoaded() {
        return this.mHasLyricsLoaded;
    }

    public void showLyricsForMediaItem(MediaItem mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        this.mHasLyricsLoaded = false;
        setMIsCurrentLyricsEmpty(null);
        final String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null);
        if (mediaItemIdString != null) {
            getMLyricsProvider().fetchLyrics(new LyricsTrackInfo() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer.showLyricsForMediaItem.1
                @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
                /* JADX INFO: renamed from: getAsin */
                public String get$trackAsin() {
                    String asin = mediaItemIdString;
                    Intrinsics.checkNotNullExpressionValue(asin, "asin");
                    return asin;
                }

                @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
                public String getMarketplaceId() {
                    String homeMarketPlaceId = AccountDetailUtil.get(this.getContext().getApplicationContext()).getHomeMarketPlaceId();
                    Intrinsics.checkNotNullExpressionValue(homeMarketPlaceId, "get(context.applicationContext).homeMarketPlaceId");
                    return homeMarketPlaceId;
                }
            }, this);
        } else {
            post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LyricsViewContainer.m1537showLyricsForMediaItem$lambda0(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showLyricsForMediaItem$lambda-0, reason: not valid java name */
    public static final void m1537showLyricsForMediaItem$lambda0(LyricsViewContainer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setVisibility(8);
        OnNoLyricsDisplayedListener onNoLyricsDisplayedListener = this$0.mOnNoLyricsDisplayedListener;
        if (onNoLyricsDisplayedListener == null) {
            return;
        }
        onNoLyricsDisplayedListener.onNoLyricsDisplayed();
    }

    public final void setOnNoLyricsDisplayedListener(OnNoLyricsDisplayedListener listener) {
        this.mOnNoLyricsDisplayedListener = listener;
    }

    public final void setFrugalLyricsListener(FrugalLyricsListener listener) {
        this.mFrugalLyricsListener = listener;
    }

    public final void setFrugalLyricsEnabled(boolean enabled) {
        this.mIsFrugalLyricsEnabled = enabled;
    }

    public final void setHasUserInteracted(boolean hasUserInteracted) {
        FrugalLyricsChecker.INSTANCE.setHasUserInteracted(hasUserInteracted);
    }

    public final ViewState getCachedViewState() {
        return this.mCachedViewState.getViewState();
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setPlaybackTime(long r3) {
        /*
            r2 = this;
            boolean r0 = r2.isLyricsAvailable()
            if (r0 != 0) goto L7
            return
        L7:
            int r3 = r2.getLinePosition(r3)
            boolean r4 = r2.shouldShowFrugalLyrics(r3)
            r0 = 1
            r1 = 0
            if (r4 == 0) goto L31
            android.widget.LinearLayout r3 = r2.getMFrugalLyricsView()
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L2d
            android.view.View r3 = r2.getLyricsView()
            if (r3 != 0) goto L25
        L23:
            r0 = r1
            goto L2b
        L25:
            int r3 = r3.getVisibility()
            if (r3 != 0) goto L23
        L2b:
            if (r0 == 0) goto L51
        L2d:
            r2.showFrugalLyricsView()
            goto L51
        L31:
            android.widget.LinearLayout r4 = r2.getMFrugalLyricsView()
            int r4 = r4.getVisibility()
            if (r4 == 0) goto L4b
            android.view.View r4 = r2.getLyricsView()
            if (r4 != 0) goto L43
        L41:
            r0 = r1
            goto L49
        L43:
            int r4 = r4.getVisibility()
            if (r4 != 0) goto L41
        L49:
            if (r0 != 0) goto L4e
        L4b:
            r2.hideFrugalLyricsView()
        L4e:
            r2.setCurrentPlayingLyricsLine(r3)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer.setPlaybackTime(long):void");
    }

    public void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (isStage()) {
            getMFrugalLyricsBadge().setVisibility(8);
            FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
            if (fontStyle != null) {
                FontUtil.INSTANCE.applyFontStyle(getMFrugalLyricsTextView(), fontStyle);
            }
        } else {
            FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.TERTIARY);
            if (fontStyle2 != null) {
                FontUtil.INSTANCE.applyFontStyle(getMFrugalLyricsTextView(), fontStyle2);
            }
        }
        BaseButton.StyleBuilder tagBuilder = styleSheet.getTagBuilder(TagSizeKey.SMALL, ScreenUtil.isLandscape() ? TagStyleKey.OUTLINE_PRIMARY : TagStyleKey.SOLID_PRIMARY);
        if (tagBuilder != null) {
            String string = getResources().getString(R.string.dmusic_lyrics_listview_badge);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ic_lyrics_listview_badge)");
            BaseButton.StyleBuilder styleBuilderWithText = tagBuilder.withText(string);
            if (styleBuilderWithText != null) {
                styleBuilderWithText.applyStyle(getMFrugalLyricsBadge());
            }
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        if (spacerInPixels == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(getMFrugalLyricsTextView(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(spacerInPixels.intValue()), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    public final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    public void onDestroy() {
        this.mFrugalLyricsListener = null;
        setOnNoLyricsDisplayedListener(null);
    }

    public final void show(boolean isShown) {
        if (isShown) {
            if (isLyricsAvailable()) {
                setVisibility(0);
                return;
            }
            return;
        }
        setVisibility(8);
    }

    public final void setIsBlocked(boolean isBlocked) {
        this.mIsBlocked = isBlocked;
    }

    public void onLoadDelay() {
        this.mHasLyricsLoaded = false;
        setVisibility(4);
    }

    public void onLyricsLoaded(Lyrics lyrics) {
        Intrinsics.checkNotNullParameter(lyrics, "lyrics");
        this.mHasLyricsLoaded = true;
        setMIsCurrentLyricsEmpty(Boolean.valueOf(lyrics.getLines().isEmpty()));
        this.mLyricsTracker = new LyricsTracker(lyrics);
        this.mLyricsMetricsReporter.setLyrics(lyrics);
        FrugalLyricsChecker.INSTANCE.setLyrics(lyrics);
        setVisibility((isFromJapanAndPlayInOffline() || !isLyricsAvailable() || this.mIsBlocked) ? 4 : 0);
        if (shouldShowFrugalLyrics(0)) {
            showFrugalLyricsView();
        } else {
            hideFrugalLyricsView();
        }
    }

    public void onNoLyrics() {
        this.mHasLyricsLoaded = false;
        setVisibility(4);
        OnNoLyricsDisplayedListener onNoLyricsDisplayedListener = this.mOnNoLyricsDisplayedListener;
        if (onNoLyricsDisplayedListener == null) {
            return;
        }
        onNoLyricsDisplayedListener.onNoLyricsDisplayed();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLyricsMetricsReporter.hideLyrics(this.viewId);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateIsLyricsDisplayed();
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View changedView, int visibility) {
        Intrinsics.checkNotNullParameter(changedView, "changedView");
        super.onVisibilityChanged(changedView, visibility);
        updateIsLyricsDisplayed();
    }

    private final boolean isLyricsAvailable() {
        return this.mHasLyricsLoaded && Intrinsics.areEqual(this.mIsCurrentLyricsEmpty, Boolean.FALSE);
    }

    private final void updateIsLyricsDisplayed() {
        if (isLyricsAvailable()) {
            View lyricsView = getLyricsView();
            boolean z = false;
            if (lyricsView != null && lyricsView.isShown()) {
                z = true;
            }
            if (z) {
                LyricsMetricsReporter lyricsMetricsReporter = this.mLyricsMetricsReporter;
                if (lyricsMetricsReporter == null) {
                    return;
                }
                lyricsMetricsReporter.displayLyrics(this.viewId);
                return;
            }
        }
        LyricsMetricsReporter lyricsMetricsReporter2 = this.mLyricsMetricsReporter;
        if (lyricsMetricsReporter2 == null) {
            return;
        }
        lyricsMetricsReporter2.hideLyrics(this.viewId);
    }

    public final int getLinePosition(long time) {
        LyricsTracker lyricsTracker = this.mLyricsTracker;
        if (lyricsTracker == null) {
            return 0;
        }
        return lyricsTracker.findPositionFromTime(time + ((long) ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
    }

    private final void showFrugalLyricsView() {
        post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LyricsViewContainer.m1536showFrugalLyricsView$lambda4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showFrugalLyricsView$lambda-4, reason: not valid java name */
    public static final void m1536showFrugalLyricsView$lambda4(LyricsViewContainer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMFrugalLyricsView().setVisibility(0);
        View lyricsView = this$0.getLyricsView();
        if (lyricsView != null) {
            lyricsView.setVisibility(8);
        }
        this$0.updateIsLyricsDisplayed();
        this$0.mCachedViewState.setViewState(ViewState.FRUGAL_LYRICS);
        FrugalLyricsListener frugalLyricsListener = this$0.mFrugalLyricsListener;
        if (frugalLyricsListener == null) {
            return;
        }
        frugalLyricsListener.onFrugalLyricsDisplayed();
    }

    private final void hideFrugalLyricsView() {
        post(new Runnable() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsViewContainer$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LyricsViewContainer.m1535hideFrugalLyricsView$lambda5(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hideFrugalLyricsView$lambda-5, reason: not valid java name */
    public static final void m1535hideFrugalLyricsView$lambda5(LyricsViewContainer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getMFrugalLyricsView().setVisibility(8);
        View lyricsView = this$0.getLyricsView();
        if (lyricsView != null) {
            lyricsView.setVisibility(0);
        }
        this$0.updateIsLyricsDisplayed();
        this$0.mCachedViewState.setViewState(this$0.mIsFrugalLyricsEnabled ? ViewState.PREVIEW : ViewState.FULL_SCREEN);
        FrugalLyricsListener frugalLyricsListener = this$0.mFrugalLyricsListener;
        if (frugalLyricsListener == null) {
            return;
        }
        frugalLyricsListener.onFrugalLyricsHidden();
    }

    private final boolean shouldShowFrugalLyrics(int position) {
        return !this.showFullLyricsOnOverlays && this.mIsFrugalLyricsEnabled && FrugalLyricsChecker.INSTANCE.shouldShowFrugalLyrics(position);
    }

    public final boolean getHasUserInteracted() {
        return FrugalLyricsChecker.INSTANCE.getHasUserInteracted();
    }

    public final boolean isFrugalLyricsViewVisible() {
        return getMFrugalLyricsView().getVisibility() == 0;
    }

    public final void setShowFullLyricsOnOverlays(boolean showCompleteLyrics) {
        this.showFullLyricsOnOverlays = showCompleteLyrics;
    }

    private final boolean isFromJapanAndPlayInOffline() {
        return Intrinsics.areEqual(AccountDetailStorage.get(getContext()).getLibraryHomeMarketplace(""), Marketplace.JAPAN.getObfuscatedId()) && !ConnectivityUtil.isAvailable(getContext());
    }
}
