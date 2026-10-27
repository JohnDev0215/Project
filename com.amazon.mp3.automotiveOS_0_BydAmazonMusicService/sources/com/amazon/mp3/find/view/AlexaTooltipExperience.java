package com.amazon.mp3.find.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OneTimeExperience;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ToolTipDialog;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u0000 &2\u00020\u00012\u00020\u0002:\u0001&B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\tH\u0002J\u0006\u0010\u001a\u001a\u00020\u0011J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\u0010\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\tH\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J\u001a\u0010!\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u001a\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010$\u001a\u00020\u0011H\u0002J\b\u0010%\u001a\u00020\u0011H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/mp3/find/view/AlexaTooltipExperience;", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "Lcom/amazon/mp3/util/OneTimeExperience;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "currentTimestamp", "", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "tooltipDisposable", "Lio/reactivex/disposables/Disposable;", "canPresentTooltip", "", "dismissTooltip", "", "dismissPermanently", "getNumTooltipViews", "", "getOffsetX", "actionBar", "Landroid/view/View;", "getOffsetY", "getTimestamp", "hideToolTip", "isATCSkipTooltipNotEligible", "isEligibleBySubscription", "isEligibleByTimestamp", "lastTimestamp", "onDestroy", "onTooltipDismissed", "show", "showTooltip", "view", "trackTooltipUIClickEvent", "updateSharedPreferences", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaTooltipExperience extends OneTimeExperience implements TooltipExperienceDecorator {
    private static final String TAG;
    private ToolTipDialog bauhausTooltip;
    private final long currentTimestamp;
    private StyleSheet styleSheet;
    private Disposable tooltipDisposable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaTooltipExperience(Context context) {
        super("sfb_alexa_tooltip_experience", context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.currentTimestamp = PlatformProviderUtilKt.getCurrentEpochTimeMillis();
    }

    static {
        String simpleName = AlexaTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "AlexaTooltipExperience::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // com.amazon.mp3.util.OneTimeExperience, com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void onDestroy() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        this.bauhausTooltip = null;
        Disposable disposable = this.tooltipDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.tooltipDisposable = null;
        super.onDestroy();
    }

    @Override // com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void dismissTooltip(boolean dismissPermanently) {
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "Not yet implemented"));
    }

    @Override // com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void showTooltip(final View view, final StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.styleSheet = styleSheet;
        this.tooltipDisposable = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.find.view.AlexaTooltipExperience$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                AlexaTooltipExperience.m1003showTooltip$lambda0(this.f$0, observableEmitter);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.AlexaTooltipExperience$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AlexaTooltipExperience.m1004showTooltip$lambda2(this.f$0, view, styleSheet, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.find.view.AlexaTooltipExperience$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AlexaTooltipExperience.m1005showTooltip$lambda3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-0, reason: not valid java name */
    public static final void m1003showTooltip$lambda0(AlexaTooltipExperience this$0, ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        emitter.onNext(Boolean.valueOf(this$0.canPresentTooltip()));
        emitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-2, reason: not valid java name */
    public static final void m1004showTooltip$lambda2(AlexaTooltipExperience this$0, View view, StyleSheet styleSheet, Boolean success) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        if (success == null) {
            return;
        }
        success.booleanValue();
        Intrinsics.checkNotNullExpressionValue(success, "success");
        if (success.booleanValue()) {
            this$0.updateSharedPreferences();
            this$0.show(view, styleSheet);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-3, reason: not valid java name */
    public static final void m1005showTooltip$lambda3(Throwable th) {
        Log.error(TAG, th.toString());
    }

    public final void hideToolTip() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog == null) {
            return;
        }
        toolTipDialog.dismiss();
    }

    private final void updateSharedPreferences() {
        getTimestamp();
        int numTooltipViews = getNumTooltipViews();
        TimeUnit.DAYS.toMillis(1L);
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putInt("sfb_alexa_tooltip_num_find_landing_page_views", numTooltipViews + 1);
        editorEdit.putLong("sfb_alexa_tooltip_timestamp", this.currentTimestamp);
        editorEdit.apply();
    }

    private final void show(View actionBar, StyleSheet styleSheet) {
        if (styleSheet == null) {
            return;
        }
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        Context context = actionBar.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "actionBar.context");
        ToolTipDialog text = new ToolTipDialog(context, styleSheet).setPosition(ToolTipDialog.Position.BOTTOM).setAnchorPoint(getOffsetX(actionBar), getOffsetY(actionBar)).setText(R.string.find_alexa_tooltip);
        this.bauhausTooltip = text;
        if (text != null) {
            text.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.find.view.AlexaTooltipExperience$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    AlexaTooltipExperience.m1001show$lambda6$lambda4(this.f$0, dialogInterface);
                }
            });
        }
        ToolTipDialog toolTipDialog2 = this.bauhausTooltip;
        if (toolTipDialog2 != null) {
            toolTipDialog2.setToolTipClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.find.view.AlexaTooltipExperience$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AlexaTooltipExperience.m1002show$lambda6$lambda5(this.f$0, view);
                }
            });
        }
        ToolTipDialog toolTipDialog3 = this.bauhausTooltip;
        if (toolTipDialog3 == null) {
            return;
        }
        toolTipDialog3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: show$lambda-6$lambda-4, reason: not valid java name */
    public static final void m1001show$lambda6$lambda4(AlexaTooltipExperience this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onTooltipDismissed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: show$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1002show$lambda6$lambda5(AlexaTooltipExperience this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ToolTipDialog toolTipDialog = this$0.bauhausTooltip;
        if (toolTipDialog == null) {
            return;
        }
        toolTipDialog.dismiss();
    }

    private final int getOffsetX(View actionBar) {
        BaseButton baseButton = (BaseButton) actionBar.findViewById(R.id.search_alexa_button);
        return actionBar.getLeft() + baseButton.getLeft() + (baseButton.getWidth() / 2);
    }

    private final int getOffsetY(View actionBar) {
        return actionBar.getTop() + ((BaseButton) actionBar.findViewById(R.id.search_alexa_button)).getBottom();
    }

    public final boolean canPresentTooltip() {
        return canPresentExperience() && isATCSkipTooltipNotEligible() && isEligibleBySubscription() && isEligibleByTimestamp(getTimestamp()) && AlexaUtil.isAlexaSearchFindBrowseEnabled();
    }

    private final boolean isEligibleBySubscription() {
        return UserSubscriptionUtil.getUserSubscription().hasSubscription();
    }

    private final boolean isATCSkipTooltipNotEligible() {
        return !FTUSettingsUtil.customerSkippedATC(getContext()) || FTUSettingsUtil.searchTooltipOnATCSkipShown(getContext());
    }

    private final boolean isEligibleByTimestamp(long lastTimestamp) {
        return lastTimestamp + TimeUnit.DAYS.toMillis(1L) <= this.currentTimestamp;
    }

    private final int getNumTooltipViews() {
        return getPrefs().getInt("sfb_alexa_tooltip_num_find_landing_page_views", 0);
    }

    private final long getTimestamp() {
        return getPrefs().getLong("sfb_alexa_tooltip_timestamp", 0L);
    }

    private final void onTooltipDismissed() {
        trackTooltipUIClickEvent();
        if (getNumTooltipViews() >= 5) {
            disableExperiencePresentation();
        }
    }

    private final void trackTooltipUIClickEvent() {
        Log.debug(TAG, "Sending UI click event for tooltip");
        MetricsLogger.sendEvent(UiClickEvent.builder(ActionType.DISMISS).withInteractionType(InteractionType.TAP).withContentInfo(MetricsLogger.getContentInfo("tooltip")).withEventTime(System.currentTimeMillis()).build());
    }
}
