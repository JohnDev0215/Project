package com.amazon.mp3.playback;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OneTimeExperience;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ToolTipDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: SwipeControlTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\t\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\nJ\b\u0010\r\u001a\u00020\u000bH\u0002J \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nH\u0002J\"\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\nH\u0016J\u000e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u000bR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/playback/SwipeControlTooltipExperience;", "Lcom/amazon/mp3/util/OneTimeExperience;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "tooltipSubscription", "Lrx/Subscription;", "dismissTooltip", "", "", "dismissPermanently", "onTooltipDismissed", "showBauhausTooltip", "view", "Landroid/view/View;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "inImmersiveMode", "showTooltip", "trackTooltipContentView", "containerType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", "trackTooltipUIClickEvent", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SwipeControlTooltipExperience extends OneTimeExperience {
    private static final String TAG;
    private static final String swipeControlContentName;
    private static final String swipeControlExperienceName;
    private static final int tooltipTextResId;
    private ToolTipDialog bauhausTooltip;
    private Subscription tooltipSubscription;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeControlTooltipExperience(Context context) {
        super(swipeControlExperienceName, context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    static {
        String simpleName = SwipeControlTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SwipeControlTooltipExper…ce::class.java.simpleName");
        TAG = simpleName;
        swipeControlExperienceName = "swipe_control_tooltip_experience";
        swipeControlContentName = "swipeControlTooltip";
        tooltipTextResId = R.string.dmusic_swipe_control_tooltip_text;
    }

    public void showTooltip(final View view, final StyleSheet styleSheet, final boolean inImmersiveMode) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.tooltipSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.SwipeControlTooltipExperience$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SwipeControlTooltipExperience.m1398showTooltip$lambda0(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.SwipeControlTooltipExperience$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SwipeControlTooltipExperience.m1399showTooltip$lambda2(this.f$0, view, styleSheet, inImmersiveMode, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.SwipeControlTooltipExperience$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SwipeControlTooltipExperience.m1400showTooltip$lambda3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-0, reason: not valid java name */
    public static final void m1398showTooltip$lambda0(SwipeControlTooltipExperience this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        subscriber.onNext(Boolean.valueOf(this$0.canPresentExperience()));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-2, reason: not valid java name */
    public static final void m1399showTooltip$lambda2(SwipeControlTooltipExperience this$0, View view, StyleSheet styleSheet, boolean z, Boolean success) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        if (success == null) {
            return;
        }
        success.booleanValue();
        Intrinsics.checkNotNullExpressionValue(success, "success");
        if (success.booleanValue()) {
            this$0.showBauhausTooltip(view, styleSheet, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-3, reason: not valid java name */
    public static final void m1400showTooltip$lambda3(Throwable th) {
        Log.error(TAG, "SwipeToolTip failed to show", th);
    }

    private final void showBauhausTooltip(View view, StyleSheet styleSheet, boolean inImmersiveMode) {
        Window window;
        Window window2;
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        ToolTipDialog.Position position = ToolTipDialog.Position.TOP;
        int i = tooltipTextResId;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "miniPlayer.context");
        ToolTipDialog text = new ToolTipDialog(context, styleSheet).setPosition(position).setAnchorView(view).setText(i);
        this.bauhausTooltip = text;
        if (text != null) {
            text.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.playback.SwipeControlTooltipExperience$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    SwipeControlTooltipExperience.m1397showBauhausTooltip$lambda5$lambda4(this.f$0, dialogInterface);
                }
            });
        }
        ToolTipDialog toolTipDialog2 = this.bauhausTooltip;
        if (toolTipDialog2 != null) {
            toolTipDialog2.show();
        }
        if (inImmersiveMode) {
            ToolTipDialog toolTipDialog3 = this.bauhausTooltip;
            if (toolTipDialog3 != null && (window2 = toolTipDialog3.getWindow()) != null) {
                window2.addFlags(256);
            }
        } else {
            ToolTipDialog toolTipDialog4 = this.bauhausTooltip;
            if (toolTipDialog4 != null && (window = toolTipDialog4.getWindow()) != null) {
                window.clearFlags(256);
            }
        }
        trackTooltipContentView(ContainerType.MINI_TRANSPORT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showBauhausTooltip$lambda-5$lambda-4, reason: not valid java name */
    public static final void m1397showBauhausTooltip$lambda5$lambda4(SwipeControlTooltipExperience this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onTooltipDismissed();
    }

    public final void dismissTooltip(boolean dismissPermanently) {
        if (dismissTooltip() && dismissPermanently) {
            disableExperiencePresentation();
        }
    }

    private final boolean dismissTooltip() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog == null) {
            return false;
        }
        toolTipDialog.dismiss();
        Subscription subscription = this.tooltipSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.tooltipSubscription = null;
        return true;
    }

    private final void onTooltipDismissed() {
        trackTooltipUIClickEvent();
        disableExperiencePresentation();
    }

    public final void trackTooltipUIClickEvent() {
        Log.info(TAG, "Sending UI Click Event for Tooltip Clicked by User");
        MetricsLogger.sendEvent(UiClickEvent.builder(ActionType.DISMISS).withInteractionType(InteractionType.TAP).withContentInfo(MetricsLogger.getContentInfo(ContentName.SWIPE_TOOLTIP.getMetricsValue())).withEventTime(System.currentTimeMillis()).build());
    }

    public final void trackTooltipContentView(ContainerType containerType) {
        Intrinsics.checkNotNullParameter(containerType, "containerType");
        Log.info(TAG, "Sending Content View Event for Tooltip");
        MetricsLogger.sendEvent(UiContentViewEvent.clientEventBuilder().withContainerType(containerType).withContentName(ContentName.SWIPE_TOOLTIP).withContainerIndex(0L).withNumItemsAccessible(1L).withImpressionTimestamp(System.currentTimeMillis()).build());
    }
}
