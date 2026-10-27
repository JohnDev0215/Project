package com.amazon.music.explore.widgets.views;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.explore.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.managers.CountDownTimerManager;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.skyfire.ui.skyfire.Queues;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.ui.foundations.ktx.ViewKt;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 ^2\u00020\u0001:\u0001^B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020<J\b\u0010W\u001a\u00020UH\u0014J\b\u0010X\u001a\u00020UH\u0014J\u0012\u0010Y\u001a\u00020U2\b\b\u0002\u0010Z\u001a\u00020[H\u0002J\u0010\u0010\\\u001a\u00020U2\u0006\u0010]\u001a\u00020[H\u0002R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001e\u0010\u0014\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001e\u0010\u0017\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001e\u0010&\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R\u001e\u0010)\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u000e\"\u0004\b+\u0010\u0010R\u001e\u0010,\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u000e\"\u0004\b.\u0010\u0010R\u001e\u0010/\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000e\"\u0004\b1\u0010\u0010R\u001e\u00102\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u000e\"\u0004\b4\u0010\u0010R\u001e\u00105\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u000e\"\u0004\b7\u0010\u0010R!\u00108\u001a\b\u0012\u0004\u0012\u00020\f0\u001b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b:\u0010\u001f\u001a\u0004\b9\u0010\u001dR\u000e\u0010;\u001a\u00020<X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010=\u001a\u00020>8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001e\u0010C\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u000e\"\u0004\bE\u0010\u0010R\u001e\u0010F\u001a\u00020>8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010@\"\u0004\bH\u0010BR\u001e\u0010I\u001a\u00020\f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u000e\"\u0004\bK\u0010\u0010R\u001e\u0010L\u001a\u00020M8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u0010\u0010R\u001a\u0004\u0018\u00010SX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006_"}, d2 = {"Lcom/amazon/music/explore/widgets/views/EventHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buttonContainer", "Landroid/widget/LinearLayout;", "getButtonContainer", "()Landroid/widget/LinearLayout;", "setButtonContainer", "(Landroid/widget/LinearLayout;)V", "counterDays", "Landroid/widget/TextView;", "getCounterDays", "()Landroid/widget/TextView;", "setCounterDays", "(Landroid/widget/TextView;)V", "counterHours", "getCounterHours", "setCounterHours", "counterMinutes", "getCounterMinutes", "setCounterMinutes", "counterSeconds", "getCounterSeconds", "setCounterSeconds", "counters", "", "getCounters", "()[Landroid/widget/TextView;", "counters$delegate", "Lkotlin/Lazy;", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "getDispatcherContext", "()Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "setDispatcherContext", "(Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;)V", "headline", "getHeadline", "setHeadline", ContextMappingConstants.LABEL, "getLabel", "setLabel", "labelDays", "getLabelDays", "setLabelDays", "labelHours", "getLabelHours", "setLabelHours", "labelMinutes", "getLabelMinutes", "setLabelMinutes", "labelSeconds", "getLabelSeconds", "setLabelSeconds", "labels", "getLabels", "labels$delegate", "lastStartTimeMillis", "", "primaryButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getPrimaryButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "setPrimaryButton", "(Lcom/amazon/ui/foundations/views/BaseButton;)V", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "setPrimaryText", "secondaryButton", "getSecondaryButton", "setSecondaryButton", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "thumbnail", "Landroid/widget/ImageView;", "getThumbnail", "()Landroid/widget/ImageView;", "setThumbnail", "(Landroid/widget/ImageView;)V", "timer", "Lcom/amazon/music/skyfire/ui/managers/CountDownTimerManager;", "configureTimer", "", "startTimeMillis", "onAttachedToWindow", "onDetachedFromWindow", "requestRefresh", "maxWaitSeconds", "", "updateTimeLeft", "timeLeftSeconds", "Companion", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EventHeaderView extends ConstraintLayout {
    private static final int DEFAULT_MAX_WAIT_SECONDS = 3;

    @ViewFinder.AView
    public LinearLayout buttonContainer;

    @ViewFinder.AView
    public TextView counterDays;

    @ViewFinder.AView
    public TextView counterHours;

    @ViewFinder.AView
    public TextView counterMinutes;

    @ViewFinder.AView
    public TextView counterSeconds;

    /* JADX INFO: renamed from: counters$delegate, reason: from kotlin metadata */
    private final Lazy counters;
    private DispatcherContext dispatcherContext;

    @ViewFinder.AView
    public TextView headline;

    @ViewFinder.AView
    public TextView label;

    @ViewFinder.AView
    public TextView labelDays;

    @ViewFinder.AView
    public TextView labelHours;

    @ViewFinder.AView
    public TextView labelMinutes;

    @ViewFinder.AView
    public TextView labelSeconds;

    /* JADX INFO: renamed from: labels$delegate, reason: from kotlin metadata */
    private final Lazy labels;
    private long lastStartTimeMillis;

    @ViewFinder.AView
    public BaseButton primaryButton;

    @ViewFinder.AView
    public TextView primaryText;

    @ViewFinder.AView
    public BaseButton secondaryButton;

    @ViewFinder.AView
    public TextView secondaryText;

    @ViewFinder.AView
    public ImageView thumbnail;
    private CountDownTimerManager timer;
    private static final List<Integer> TIME_UNITS = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{60, 60, 24});

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventHeaderView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.labels = LazyKt__LazyJVMKt.lazy(new Function0<TextView[]>() { // from class: com.amazon.music.explore.widgets.views.EventHeaderView$labels$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TextView[] invoke() {
                return new TextView[]{this.this$0.getLabelDays(), this.this$0.getLabelHours(), this.this$0.getLabelMinutes(), this.this$0.getLabelSeconds()};
            }
        });
        this.counters = LazyKt__LazyJVMKt.lazy(new Function0<TextView[]>() { // from class: com.amazon.music.explore.widgets.views.EventHeaderView$counters$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TextView[] invoke() {
                return new TextView[]{this.this$0.getCounterDays(), this.this$0.getCounterHours(), this.this$0.getCounterMinutes(), this.this$0.getCounterSeconds()};
            }
        });
        ViewFinderKt.findViews$default(ViewGroupKt.inflateThis(this, R.layout.event_header_view), (Object) null, 1, (Object) null);
    }

    public final TextView getLabel() {
        TextView textView = this.label;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.LABEL);
        return null;
    }

    public final void setLabel(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.label = textView;
    }

    public final TextView getHeadline() {
        TextView textView = this.headline;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("headline");
        return null;
    }

    public final void setHeadline(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.headline = textView;
    }

    public final TextView getPrimaryText() {
        TextView textView = this.primaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.PRIMARY_TEXT);
        return null;
    }

    public final void setPrimaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.primaryText = textView;
    }

    public final TextView getSecondaryText() {
        TextView textView = this.secondaryText;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.SECONDARY_TEXT);
        return null;
    }

    public final void setSecondaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.secondaryText = textView;
    }

    public final ImageView getThumbnail() {
        ImageView imageView = this.thumbnail;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
        return null;
    }

    public final void setThumbnail(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.thumbnail = imageView;
    }

    public final TextView getCounterDays() {
        TextView textView = this.counterDays;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("counterDays");
        return null;
    }

    public final void setCounterDays(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.counterDays = textView;
    }

    public final TextView getCounterHours() {
        TextView textView = this.counterHours;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("counterHours");
        return null;
    }

    public final void setCounterHours(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.counterHours = textView;
    }

    public final TextView getCounterMinutes() {
        TextView textView = this.counterMinutes;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("counterMinutes");
        return null;
    }

    public final void setCounterMinutes(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.counterMinutes = textView;
    }

    public final TextView getCounterSeconds() {
        TextView textView = this.counterSeconds;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("counterSeconds");
        return null;
    }

    public final void setCounterSeconds(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.counterSeconds = textView;
    }

    public final TextView getLabelDays() {
        TextView textView = this.labelDays;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("labelDays");
        return null;
    }

    public final void setLabelDays(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.labelDays = textView;
    }

    public final TextView getLabelHours() {
        TextView textView = this.labelHours;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("labelHours");
        return null;
    }

    public final void setLabelHours(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.labelHours = textView;
    }

    public final TextView getLabelMinutes() {
        TextView textView = this.labelMinutes;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("labelMinutes");
        return null;
    }

    public final void setLabelMinutes(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.labelMinutes = textView;
    }

    public final TextView getLabelSeconds() {
        TextView textView = this.labelSeconds;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("labelSeconds");
        return null;
    }

    public final void setLabelSeconds(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.labelSeconds = textView;
    }

    public final BaseButton getPrimaryButton() {
        BaseButton baseButton = this.primaryButton;
        if (baseButton != null) {
            return baseButton;
        }
        Intrinsics.throwUninitializedPropertyAccessException("primaryButton");
        return null;
    }

    public final void setPrimaryButton(BaseButton baseButton) {
        Intrinsics.checkNotNullParameter(baseButton, "<set-?>");
        this.primaryButton = baseButton;
    }

    public final BaseButton getSecondaryButton() {
        BaseButton baseButton = this.secondaryButton;
        if (baseButton != null) {
            return baseButton;
        }
        Intrinsics.throwUninitializedPropertyAccessException("secondaryButton");
        return null;
    }

    public final void setSecondaryButton(BaseButton baseButton) {
        Intrinsics.checkNotNullParameter(baseButton, "<set-?>");
        this.secondaryButton = baseButton;
    }

    public final LinearLayout getButtonContainer() {
        LinearLayout linearLayout = this.buttonContainer;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("buttonContainer");
        return null;
    }

    public final void setButtonContainer(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.buttonContainer = linearLayout;
    }

    public final TextView[] getLabels() {
        return (TextView[]) this.labels.getValue();
    }

    public final TextView[] getCounters() {
        return (TextView[]) this.counters.getValue();
    }

    public final DispatcherContext getDispatcherContext() {
        return this.dispatcherContext;
    }

    public final void setDispatcherContext(DispatcherContext dispatcherContext) {
        this.dispatcherContext = dispatcherContext;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        configureTimer(this.lastStartTimeMillis);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        CountDownTimerManager countDownTimerManager = this.timer;
        if (countDownTimerManager != null) {
            countDownTimerManager.stop();
        }
        this.timer = null;
        super.onDetachedFromWindow();
    }

    public final void configureTimer(long startTimeMillis) {
        this.lastStartTimeMillis = startTimeMillis;
        long time = startTimeMillis - new Date().getTime();
        Object[] objArrPlus = ArraysKt___ArraysJvmKt.plus((Object[]) getLabels(), (Object[]) getCounters());
        int length = objArrPlus.length;
        int i = 0;
        while (i < length) {
            Object obj = objArrPlus[i];
            i++;
            ViewKt.collapseIf((TextView) obj, time <= 0);
        }
        if (time <= 0) {
            CountDownTimerManager countDownTimerManager = this.timer;
            if (countDownTimerManager != null) {
                countDownTimerManager.stop();
            }
            this.timer = null;
            this.lastStartTimeMillis = 0L;
            if (startTimeMillis > 0) {
                requestRefresh(0);
                return;
            }
            return;
        }
        updateTimeLeft((int) (time / ((long) 1000)));
        if (this.timer == null) {
            CountDownTimerManager countDownTimerManager2 = new CountDownTimerManager(time, this) { // from class: com.amazon.music.explore.widgets.views.EventHeaderView.configureTimer.2
                final /* synthetic */ long $timeLeftMillis;
                final /* synthetic */ EventHeaderView this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(time, 1000L);
                    this.$timeLeftMillis = time;
                    this.this$0 = this;
                }

                @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
                public void onFinished() {
                    EventHeaderView.requestRefresh$default(this.this$0, 0, 1, null);
                }

                @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
                public void onTicked(long msRemaining) {
                    this.this$0.updateTimeLeft((int) (msRemaining / ((long) 1000)));
                }
            };
            countDownTimerManager2.start();
            this.timer = countDownTimerManager2;
        }
    }

    static /* synthetic */ void requestRefresh$default(EventHeaderView eventHeaderView, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 3;
        }
        eventHeaderView.requestRefresh(i);
    }

    private final void requestRefresh(int maxWaitSeconds) {
        postDelayed(new Runnable() { // from class: com.amazon.music.explore.widgets.views.EventHeaderView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EventHeaderView.m2011requestRefresh$lambda2(this.f$0);
            }
        }, ((long) (maxWaitSeconds > 0 ? new Random().nextInt(maxWaitSeconds) : 0)) * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestRefresh$lambda-2, reason: not valid java name */
    public static final void m2011requestRefresh$lambda2(EventHeaderView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DispatcherContext dispatcherContext = this$0.dispatcherContext;
        if (dispatcherContext == null) {
            return;
        }
        ImmutableTriggerExternalActionMethod immutableTriggerExternalActionMethodBuild = ImmutableTriggerExternalActionMethod.builder().name(Methods.refreshSelf).queue(Queues.INSTANCE.getSingleTheaded()).build();
        Intrinsics.checkNotNullExpressionValue(immutableTriggerExternalActionMethodBuild, "builder()\n              …                 .build()");
        SkyFireKt.dispatch$default(dispatcherContext, new Method[]{immutableTriggerExternalActionMethodBuild}, (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTimeLeft(int timeLeftSeconds) {
        ArrayList arrayList = new ArrayList();
        List<Integer> list = TIME_UNITS;
        Integer numValueOf = Integer.valueOf(timeLeftSeconds);
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            int iIntValue2 = numValueOf.intValue();
            arrayList.add(Integer.valueOf(iIntValue2 % iIntValue));
            numValueOf = Integer.valueOf(iIntValue2 / iIntValue);
        }
        arrayList.add(numValueOf);
        int i = 0;
        for (Object obj : CollectionsKt___CollectionsKt.reversed(arrayList)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            int iIntValue3 = ((Number) obj).intValue();
            TextView textView = getCounters()[i];
            String str = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(iIntValue3)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
            textView.setText(str);
            i = i2;
        }
    }
}
