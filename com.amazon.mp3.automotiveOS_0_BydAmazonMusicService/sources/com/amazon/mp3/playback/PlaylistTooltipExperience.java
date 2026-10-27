package com.amazon.mp3.playback;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OneTimeExperience;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.TooltipDisplayer;
import com.amazon.mp3.view.TooltipView;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ToolTipDialog;
import com.amazon.ui.foundations.utils.ScreenUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 -2\u00020\u0001:\u0002-.B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\b\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002J\b\u0010\u001f\u001a\u00020\u0012H\u0002J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\nH\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002J\u0018\u0010#\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010$\u001a\u00020\u0012J\b\u0010%\u001a\u00020\u0014H\u0002J \u0010&\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u0012H\u0002J0\u0010'\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010$\u001a\u00020\u0012J\u0010\u0010(\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\"\u0010(\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010$\u001a\u00020\u0012H\u0002J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020+J\u0006\u0010,\u001a\u00020\u0014R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/amazon/mp3/playback/PlaylistTooltipExperience;", "Lcom/amazon/mp3/util/OneTimeExperience;", "context", "Landroid/content/Context;", "builder", "Lcom/amazon/mp3/playback/PlaylistTooltipExperience$PlaylistTooltipExperienceBuilder;", "(Landroid/content/Context;Lcom/amazon/mp3/playback/PlaylistTooltipExperience$PlaylistTooltipExperienceBuilder;)V", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "maxNumNowPlayingPageViews", "", "playlistTooltip", "Lcom/amazon/mp3/view/TooltipDisplayer;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "tooltipSubscription", "Lrx/Subscription;", "canPresentTooltip", "", "dismissPlaylistTooltip", "", "dismissPermanently", "getNumTooltipViews", "getOverflowIconOffset", "toolbar", "Landroid/view/View;", "getToolbarHeightOffset", "incrementNumTooltipViews", "hasRotatedOrientation", "fragmentIsInitializing", "isEligibleByNumPlaylists", "isEligibleBySubscription", "isEligibleByTooltipViews", "numPageViews", "isNightwingCustomer", "onConfigurationChanged", "inImmersiveMode", "onTooltipDismissed", "showBauhausTooltip", "showPlaylistTooltip", "showTooltip", "trackTooltipContentView", "containerType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", "trackTooltipUIClickEvent", "Companion", "PlaylistTooltipExperienceBuilder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class PlaylistTooltipExperience extends OneTimeExperience {
    private static final String NUM_NOW_PLAYING_PAGE_VIEWS;
    private static final String PLAYLIST_TOOLTIP_EXPERIENCE_NAME;
    private static final String TAG;
    private ToolTipDialog bauhausTooltip;
    private int maxNumNowPlayingPageViews;
    private TooltipDisplayer playlistTooltip;
    private StyleSheet styleSheet;
    private Subscription tooltipSubscription;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistTooltipExperience(Context context, PlaylistTooltipExperienceBuilder builder) {
        super(PLAYLIST_TOOLTIP_EXPERIENCE_NAME, context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.maxNumNowPlayingPageViews = PlaylistTooltipExperienceBuilder.INSTANCE.getMAX_NUM_NOW_PLAYING_PAGE_VIEWS();
        this.playlistTooltip = builder.getMplaylistTooltip();
        this.maxNumNowPlayingPageViews = builder.getMNumViews();
    }

    static {
        String simpleName = PlaylistTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "PlaylistTooltipExperience::class.java.simpleName");
        TAG = simpleName;
        NUM_NOW_PLAYING_PAGE_VIEWS = "num_now_playing_page_views";
        PLAYLIST_TOOLTIP_EXPERIENCE_NAME = "playlist_tooltip_experience_now_playing";
    }

    public final void showPlaylistTooltip(final View toolbar, final boolean hasRotatedOrientation, final boolean fragmentIsInitializing, final StyleSheet styleSheet, final boolean inImmersiveMode) {
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        this.styleSheet = styleSheet;
        this.tooltipSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.PlaylistTooltipExperience$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistTooltipExperience.m1387showPlaylistTooltip$lambda0(this.f$0, hasRotatedOrientation, fragmentIsInitializing, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.PlaylistTooltipExperience$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistTooltipExperience.m1388showPlaylistTooltip$lambda2(this.f$0, toolbar, styleSheet, inImmersiveMode, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.PlaylistTooltipExperience$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistTooltipExperience.m1389showPlaylistTooltip$lambda3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistTooltip$lambda-0, reason: not valid java name */
    public static final void m1387showPlaylistTooltip$lambda0(PlaylistTooltipExperience this$0, boolean z, boolean z2, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.incrementNumTooltipViews(z, z2);
        subscriber.onNext(Boolean.valueOf(this$0.canPresentTooltip()));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistTooltip$lambda-2, reason: not valid java name */
    public static final void m1388showPlaylistTooltip$lambda2(PlaylistTooltipExperience this$0, View toolbar, StyleSheet styleSheet, boolean z, Boolean success) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(toolbar, "$toolbar");
        if (success == null) {
            return;
        }
        success.booleanValue();
        Intrinsics.checkNotNullExpressionValue(success, "success");
        if (success.booleanValue()) {
            this$0.showTooltip(toolbar, styleSheet, z);
        } else {
            if (this$0.isNightwingCustomer()) {
                return;
            }
            this$0.disableExperiencePresentation();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showPlaylistTooltip$lambda-3, reason: not valid java name */
    public static final void m1389showPlaylistTooltip$lambda3(Throwable th) {
        Log.error(TAG, th.toString());
    }

    public final void dismissPlaylistTooltip(boolean dismissPermanently) {
        if (dismissPlaylistTooltip()) {
            Subscription subscription = this.tooltipSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this.tooltipSubscription = null;
            if (dismissPermanently) {
                disableExperiencePresentation();
            }
        }
    }

    private final boolean dismissPlaylistTooltip() {
        TooltipDisplayer tooltipDisplayer = this.playlistTooltip;
        if (tooltipDisplayer != null) {
            tooltipDisplayer.dismiss();
            return true;
        }
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog == null) {
            return false;
        }
        toolTipDialog.dismiss();
        return true;
    }

    public final boolean canPresentTooltip() {
        return canPresentExperience() && isEligibleBySubscription() && isEligibleByTooltipViews(getNumTooltipViews()) && isEligibleByNumPlaylists();
    }

    private final void onTooltipDismissed() {
        trackTooltipUIClickEvent();
        disableExperiencePresentation();
    }

    private final int getOverflowIconOffset(View toolbar) {
        Context context = getContext();
        if (context == null) {
            return 0;
        }
        return ((context.getResources().getDimensionPixelOffset(R.dimen.overflow_button_clickable_size) / 2) - context.getResources().getDimensionPixelOffset(R.dimen.overflow_spacing)) + toolbar.getPaddingRight();
    }

    private final void incrementNumTooltipViews(boolean hasRotatedOrientation, boolean fragmentIsInitializing) {
        int numTooltipViews = getNumTooltipViews();
        if (hasRotatedOrientation || !fragmentIsInitializing || numTooltipViews > this.maxNumNowPlayingPageViews) {
            return;
        }
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putInt(NUM_NOW_PLAYING_PAGE_VIEWS, numTooltipViews + 1);
        editorEdit.apply();
    }

    private final int getNumTooltipViews() {
        return getPrefs().getInt(NUM_NOW_PLAYING_PAGE_VIEWS, 0);
    }

    private final boolean isEligibleByTooltipViews(int numPageViews) {
        return numPageViews <= this.maxNumNowPlayingPageViews;
    }

    private final boolean isNightwingCustomer() {
        return UserSubscriptionUtil.getUserSubscription().isNightwingOnly();
    }

    private final boolean isEligibleBySubscription() {
        return UserSubscriptionUtil.getUserSubscription().hasSubscription();
    }

    private final boolean isEligibleByNumPlaylists() {
        return !PlaylistUtil.hasUserPlaylist(getContext());
    }

    private final void showTooltip(View toolbar, StyleSheet styleSheet, boolean inImmersiveMode) {
        showTooltip(toolbar);
    }

    private final void showBauhausTooltip(View toolbar, StyleSheet styleSheet, boolean inImmersiveMode) {
        Window window;
        Window window2;
        Context context = getContext();
        Object systemService = context == null ? null : context.getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return;
        }
        int screenWidthPixels = ScreenUtil.INSTANCE.getScreenWidthPixels(windowManager);
        toolbar.measure(0, 0);
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        Context context2 = toolbar.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "toolbar.context");
        ToolTipDialog text = new ToolTipDialog(context2, styleSheet).setPosition(ToolTipDialog.Position.BOTTOM).setAnchorPoint(screenWidthPixels - getOverflowIconOffset(toolbar), getToolbarHeightOffset(toolbar)).setText(R.string.dmusic_now_playing_playlist_tooltip);
        this.bauhausTooltip = text;
        if (text != null) {
            text.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.playback.PlaylistTooltipExperience$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    PlaylistTooltipExperience.m1386showBauhausTooltip$lambda7(this.f$0, dialogInterface);
                }
            });
        }
        if (inImmersiveMode) {
            ToolTipDialog toolTipDialog2 = this.bauhausTooltip;
            if (toolTipDialog2 != null && (window2 = toolTipDialog2.getWindow()) != null) {
                window2.addFlags(256);
            }
        } else {
            ToolTipDialog toolTipDialog3 = this.bauhausTooltip;
            if (toolTipDialog3 != null && (window = toolTipDialog3.getWindow()) != null) {
                window.clearFlags(256);
            }
        }
        ToolTipDialog toolTipDialog4 = this.bauhausTooltip;
        if (toolTipDialog4 != null) {
            toolTipDialog4.show();
        }
        trackTooltipContentView(ContainerType.NOW_PLAYING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showBauhausTooltip$lambda-7, reason: not valid java name */
    public static final void m1386showBauhausTooltip$lambda7(PlaylistTooltipExperience this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onTooltipDismissed();
    }

    private final int getToolbarHeightOffset(View toolbar) {
        Context context = getContext();
        if (context != null) {
            return (toolbar.getMeasuredHeight() / 2) + (context.getResources().getDimensionPixelOffset(R.dimen.overflow_button_clickable_size) / 2);
        }
        return toolbar.getMeasuredHeight();
    }

    private final void showTooltip(View toolbar) {
        final TooltipDisplayer tooltipDisplayer = this.playlistTooltip;
        if (tooltipDisplayer == null) {
            return;
        }
        tooltipDisplayer.setupPositioning(TooltipView.ArrowPosition.TOP_RIGHT, getOverflowIconOffset(toolbar));
        trackTooltipContentView(ContainerType.NOW_PLAYING);
        tooltipDisplayer.setClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.PlaylistTooltipExperience$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaylistTooltipExperience.m1390showTooltip$lambda10$lambda9(tooltipDisplayer, this, view);
            }
        });
        tooltipDisplayer.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-10$lambda-9, reason: not valid java name */
    public static final void m1390showTooltip$lambda10$lambda9(TooltipDisplayer tooltip, PlaylistTooltipExperience this$0, View view) {
        Intrinsics.checkNotNullParameter(tooltip, "$tooltip");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        tooltip.dismiss();
        this$0.onTooltipDismissed();
    }

    public final void trackTooltipContentView(ContainerType containerType) {
        Intrinsics.checkNotNullParameter(containerType, "containerType");
        Log.info(TAG, "Sending Content View Event for Tooltip");
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(containerType).withContentName(ContentName.PLAYLIST_TOOLTIP).withContainerIndex(0L).withNumItemsAccessible(1L).withImpressionTimestamp(System.currentTimeMillis()).build());
    }

    public final void trackTooltipUIClickEvent() {
        Log.info(TAG, "Sending UI Click Event for Tooltip Clicked by User");
        MetricsLogger.sendEvent(UiClickEvent.builder(ActionType.DISMISS).withInteractionType(InteractionType.TAP).withContentInfo(MetricsLogger.getContentInfo("tooltip")).withEventTime(System.currentTimeMillis()).build());
    }

    public final void onConfigurationChanged(View toolbar, boolean inImmersiveMode) {
        ToolTipDialog toolTipDialog;
        Unit unit;
        if (toolbar == null || (toolTipDialog = this.bauhausTooltip) == null || !toolTipDialog.isShowing()) {
            return;
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            unit = null;
        } else {
            showBauhausTooltip(toolbar, styleSheet, inImmersiveMode);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            toolTipDialog.dismiss();
        }
    }

    /* JADX INFO: compiled from: PlaylistTooltipExperience.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/playback/PlaylistTooltipExperience$PlaylistTooltipExperienceBuilder;", "", "Landroid/view/View;", "rootView", "setRootView", "", "numViews", "setNumViews", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/playback/PlaylistTooltipExperience;", "createPlaylistTooltipExperience", "mRootView", "Landroid/view/View;", "mNumViews", "I", "getMNumViews", "()I", "setMNumViews", "(I)V", "Lcom/amazon/mp3/view/TooltipDisplayer;", "mplaylistTooltip", "Lcom/amazon/mp3/view/TooltipDisplayer;", "getMplaylistTooltip", "()Lcom/amazon/mp3/view/TooltipDisplayer;", "setMplaylistTooltip", "(Lcom/amazon/mp3/view/TooltipDisplayer;)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class PlaylistTooltipExperienceBuilder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int MAX_NUM_NOW_PLAYING_PAGE_VIEWS = 3;
        private int mNumViews = MAX_NUM_NOW_PLAYING_PAGE_VIEWS;
        private View mRootView;
        private TooltipDisplayer mplaylistTooltip;

        public final int getMNumViews() {
            return this.mNumViews;
        }

        public final void setMNumViews(int i) {
            this.mNumViews = i;
        }

        public final TooltipDisplayer getMplaylistTooltip() {
            return this.mplaylistTooltip;
        }

        public final PlaylistTooltipExperienceBuilder setRootView(View rootView) {
            Intrinsics.checkNotNullParameter(rootView, "rootView");
            this.mRootView = rootView;
            View viewFindViewById = rootView.findViewById(R.id.TooltipDisplayerContainer);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.…ooltipDisplayerContainer)");
            this.mplaylistTooltip = new TooltipDisplayer((TooltipView) viewFindViewById);
            return this;
        }

        public final PlaylistTooltipExperienceBuilder setNumViews(int numViews) {
            this.mNumViews = numViews;
            return this;
        }

        public final PlaylistTooltipExperience createPlaylistTooltipExperience(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new PlaylistTooltipExperience(context, this);
        }

        /* JADX INFO: compiled from: PlaylistTooltipExperience.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/PlaylistTooltipExperience$PlaylistTooltipExperienceBuilder$Companion;", "", "()V", "MAX_NUM_NOW_PLAYING_PAGE_VIEWS", "", "getMAX_NUM_NOW_PLAYING_PAGE_VIEWS", "()I", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final int getMAX_NUM_NOW_PLAYING_PAGE_VIEWS() {
                return PlaylistTooltipExperienceBuilder.MAX_NUM_NOW_PLAYING_PAGE_VIEWS;
            }
        }
    }
}
