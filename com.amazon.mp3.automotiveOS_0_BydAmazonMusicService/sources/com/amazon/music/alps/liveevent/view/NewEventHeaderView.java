package com.amazon.music.alps.liveevent.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.alps.R$id;
import com.amazon.music.alps.R$layout;
import com.amazon.music.alps.util.CountDownTimerHelper;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.ui.foundations.ktx.ViewKt;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NewEventHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\b\u001d\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 U2\u00020\u0001:\u0003UVWB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020:J\b\u0010P\u001a\u00020NH\u0014J\b\u0010Q\u001a\u00020NH\u0014J\u0010\u0010R\u001a\u00020N2\u0006\u0010S\u001a\u00020TH\u0002R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0013\u0010\u0010R\u001b\u0010\u0015\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0016\u0010\u0010R\u001b\u0010\u0018\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0019\u0010\u0010R!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\f\u001a\u0004\b\u001d\u0010\u001eR$\u0010 \u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010%R$\u0010&\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b'\u0010\"\u001a\u0004\b(\u0010\u0010\"\u0004\b)\u0010%R\u001b\u0010*\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\f\u001a\u0004\b+\u0010\u0010R\u001b\u0010-\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\f\u001a\u0004\b.\u0010\u0010R\u001b\u00100\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b2\u0010\f\u001a\u0004\b1\u0010\u0010R\u001b\u00103\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b5\u0010\f\u001a\u0004\b4\u0010\u0010R!\u00106\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b8\u0010\f\u001a\u0004\b7\u0010\u001eR\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010;\u001a\u00020<8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\f\u001a\u0004\b=\u0010>R$\u0010@\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bA\u0010\"\u001a\u0004\bB\u0010\u0010\"\u0004\bC\u0010%R\u001b\u0010D\u001a\u00020<8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bF\u0010\f\u001a\u0004\bE\u0010>R$\u0010G\u001a\u00020\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bH\u0010\"\u001a\u0004\bI\u0010\u0010\"\u0004\bJ\u0010%R\u0010\u0010K\u001a\u0004\u0018\u00010LX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "uiEvents", "Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UIEvents;", "(Landroid/content/Context;Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UIEvents;)V", "buttonContainer", "Landroid/widget/LinearLayout;", "getButtonContainer", "()Landroid/widget/LinearLayout;", "buttonContainer$delegate", "Lkotlin/Lazy;", "counterDays", "Landroid/widget/TextView;", "getCounterDays", "()Landroid/widget/TextView;", "counterDays$delegate", "counterHours", "getCounterHours", "counterHours$delegate", "counterMinutes", "getCounterMinutes", "counterMinutes$delegate", "counterSeconds", "getCounterSeconds", "counterSeconds$delegate", "counters", "", "getCounters", "()[Landroid/widget/TextView;", "counters$delegate", "headline", "getHeadline$annotations", "()V", "getHeadline", "setHeadline", "(Landroid/widget/TextView;)V", ContextMappingConstants.LABEL, "getLabel$annotations", "getLabel", "setLabel", "labelDays", "getLabelDays", "labelDays$delegate", "labelHours", "getLabelHours", "labelHours$delegate", "labelMinutes", "getLabelMinutes", "labelMinutes$delegate", "labelSeconds", "getLabelSeconds", "labelSeconds$delegate", "labels", "getLabels", "labels$delegate", "lastStartTimeMillis", "", "primaryButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getPrimaryButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "primaryButton$delegate", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText$annotations", "getPrimaryText", "setPrimaryText", "secondaryButton", "getSecondaryButton", "secondaryButton$delegate", ContextMappingConstants.SECONDARY_TEXT, "getSecondaryText$annotations", "getSecondaryText", "setSecondaryText", "timer", "Lcom/amazon/music/alps/util/CountDownTimerHelper;", "configureCountdown", "", "startTimeMillis", "onAttachedToWindow", "onDetachedFromWindow", "updateTimeLeft", "timeLeftSeconds", "", "Companion", "UIEvents", "UserEvents", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NewEventHeaderView extends ConstraintLayout {
    private static final List<Integer> TIME_UNITS = CollectionsKt__CollectionsKt.listOf((Object[]) new Integer[]{60, 60, 24});

    /* JADX INFO: renamed from: buttonContainer$delegate, reason: from kotlin metadata */
    private final Lazy buttonContainer;

    /* JADX INFO: renamed from: counterDays$delegate, reason: from kotlin metadata */
    private final Lazy counterDays;

    /* JADX INFO: renamed from: counterHours$delegate, reason: from kotlin metadata */
    private final Lazy counterHours;

    /* JADX INFO: renamed from: counterMinutes$delegate, reason: from kotlin metadata */
    private final Lazy counterMinutes;

    /* JADX INFO: renamed from: counterSeconds$delegate, reason: from kotlin metadata */
    private final Lazy counterSeconds;

    /* JADX INFO: renamed from: counters$delegate, reason: from kotlin metadata */
    private final Lazy counters;
    private TextView headline;
    private TextView label;

    /* JADX INFO: renamed from: labelDays$delegate, reason: from kotlin metadata */
    private final Lazy labelDays;

    /* JADX INFO: renamed from: labelHours$delegate, reason: from kotlin metadata */
    private final Lazy labelHours;

    /* JADX INFO: renamed from: labelMinutes$delegate, reason: from kotlin metadata */
    private final Lazy labelMinutes;

    /* JADX INFO: renamed from: labelSeconds$delegate, reason: from kotlin metadata */
    private final Lazy labelSeconds;

    /* JADX INFO: renamed from: labels$delegate, reason: from kotlin metadata */
    private final Lazy labels;
    private long lastStartTimeMillis;

    /* JADX INFO: renamed from: primaryButton$delegate, reason: from kotlin metadata */
    private final Lazy primaryButton;
    private TextView primaryText;

    /* JADX INFO: renamed from: secondaryButton$delegate, reason: from kotlin metadata */
    private final Lazy secondaryButton;
    private TextView secondaryText;
    private CountDownTimerHelper timer;
    private final UIEvents uiEvents;

    @VisibleForTesting
    public static /* synthetic */ void getHeadline$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getLabel$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getPrimaryText$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void getSecondaryText$annotations() {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewEventHeaderView(Context context, UIEvents uIEvents) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.uiEvents = uIEvents;
        this.counterDays = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$counterDays$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.counter_days);
            }
        });
        this.counterHours = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$counterHours$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.counter_hours);
            }
        });
        this.counterMinutes = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$counterMinutes$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.counter_minutes);
            }
        });
        this.counterSeconds = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$counterSeconds$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.counter_seconds);
            }
        });
        this.labelDays = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$labelDays$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_days);
            }
        });
        this.labelHours = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$labelHours$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_hours);
            }
        });
        this.labelMinutes = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$labelMinutes$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_minutes);
            }
        });
        this.labelSeconds = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$labelSeconds$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_seconds);
            }
        });
        this.primaryButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$primaryButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.primary_button);
            }
        });
        this.secondaryButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$secondaryButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.secondary_button);
            }
        });
        this.buttonContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$buttonContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.button_container);
            }
        });
        this.labels = LazyKt__LazyJVMKt.lazy(new Function0<TextView[]>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$labels$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TextView[] invoke() {
                return new TextView[]{this.this$0.getLabelDays(), this.this$0.getLabelHours(), this.this$0.getLabelMinutes(), this.this$0.getLabelSeconds()};
            }
        });
        this.counters = LazyKt__LazyJVMKt.lazy(new Function0<TextView[]>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView$counters$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TextView[] invoke() {
                return new TextView[]{this.this$0.getCounterDays(), this.this$0.getCounterHours(), this.this$0.getCounterMinutes(), this.this$0.getCounterSeconds()};
            }
        });
        View.inflate(context, R$layout.new_event_header_view, this);
        View viewFindViewById = findViewById(R$id.label);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.label)");
        this.label = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.headline);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.headline)");
        this.headline = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R$id.primary_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.primary_text)");
        this.primaryText = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R$id.secondary_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.secondary_text)");
        this.secondaryText = (TextView) viewFindViewById4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getCounterDays() {
        Object value = this.counterDays.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-counterDays>(...)");
        return (TextView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getCounterHours() {
        Object value = this.counterHours.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-counterHours>(...)");
        return (TextView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getCounterMinutes() {
        Object value = this.counterMinutes.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-counterMinutes>(...)");
        return (TextView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getCounterSeconds() {
        Object value = this.counterSeconds.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-counterSeconds>(...)");
        return (TextView) value;
    }

    public final TextView getLabelDays() {
        Object value = this.labelDays.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelDays>(...)");
        return (TextView) value;
    }

    public final TextView getLabelHours() {
        Object value = this.labelHours.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelHours>(...)");
        return (TextView) value;
    }

    public final TextView getLabelMinutes() {
        Object value = this.labelMinutes.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelMinutes>(...)");
        return (TextView) value;
    }

    public final TextView getLabelSeconds() {
        Object value = this.labelSeconds.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelSeconds>(...)");
        return (TextView) value;
    }

    public final BaseButton getPrimaryButton() {
        Object value = this.primaryButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryButton>(...)");
        return (BaseButton) value;
    }

    public final BaseButton getSecondaryButton() {
        Object value = this.secondaryButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryButton>(...)");
        return (BaseButton) value;
    }

    public final LinearLayout getButtonContainer() {
        Object value = this.buttonContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-buttonContainer>(...)");
        return (LinearLayout) value;
    }

    public final TextView[] getLabels() {
        return (TextView[]) this.labels.getValue();
    }

    public final TextView[] getCounters() {
        return (TextView[]) this.counters.getValue();
    }

    public final TextView getLabel() {
        return this.label;
    }

    public final void setLabel(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.label = textView;
    }

    public final TextView getHeadline() {
        return this.headline;
    }

    public final void setHeadline(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.headline = textView;
    }

    public final TextView getPrimaryText() {
        return this.primaryText;
    }

    public final void setPrimaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.primaryText = textView;
    }

    public final TextView getSecondaryText() {
        return this.secondaryText;
    }

    public final void setSecondaryText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.secondaryText = textView;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        configureCountdown(this.lastStartTimeMillis);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        CountDownTimerHelper countDownTimerHelper = this.timer;
        if (countDownTimerHelper != null) {
            countDownTimerHelper.stop();
        }
        this.timer = null;
        super.onDetachedFromWindow();
    }

    public final void configureCountdown(long startTimeMillis) {
        UIEvents uIEvents;
        Function0<Unit> onRequestCountdownSync;
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
            CountDownTimerHelper countDownTimerHelper = this.timer;
            if (countDownTimerHelper != null) {
                countDownTimerHelper.stop();
            }
            this.timer = null;
            this.lastStartTimeMillis = 0L;
            if (startTimeMillis <= 0 || (uIEvents = this.uiEvents) == null || (onRequestCountdownSync = uIEvents.getOnRequestCountdownSync()) == null) {
                return;
            }
            onRequestCountdownSync.invoke();
            return;
        }
        updateTimeLeft((int) (time / ((long) 1000)));
        if (this.timer == null) {
            CountDownTimerHelper countDownTimerHelper2 = new CountDownTimerHelper(time, this) { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.configureCountdown.3
                final /* synthetic */ long $timeLeftMillis;
                final /* synthetic */ NewEventHeaderView this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(time, 1000L);
                    this.$timeLeftMillis = time;
                    this.this$0 = this;
                }

                @Override // com.amazon.music.alps.util.CountDownTimerHelper
                public void onFinished() {
                    Function0<Unit> onCountdownFinished;
                    UIEvents uIEvents2 = this.this$0.uiEvents;
                    if (uIEvents2 == null || (onCountdownFinished = uIEvents2.getOnCountdownFinished()) == null) {
                        return;
                    }
                    onCountdownFinished.invoke();
                }

                @Override // com.amazon.music.alps.util.CountDownTimerHelper
                public void onTicked(long msRemaining) {
                    this.this$0.updateTimeLeft((int) (msRemaining / ((long) 1000)));
                }
            };
            countDownTimerHelper2.start();
            this.timer = countDownTimerHelper2;
        }
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

    /* JADX INFO: compiled from: NewEventHeaderView.kt */
    @Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012#\b\u0002\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t\u0012#\b\u0002\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0014\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R2\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R2\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\t8\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\r0\u00148\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UserEvents;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "target", "", "onClickPrimaryButton", "Lkotlin/jvm/functions/Function1;", "getOnClickPrimaryButton", "()Lkotlin/jvm/functions/Function1;", "onClickSecondaryButton", "getOnClickSecondaryButton", "Lkotlin/Function0;", "onAddToCalendar", "Lkotlin/jvm/functions/Function0;", "getOnAddToCalendar", "()Lkotlin/jvm/functions/Function0;", "onShare", "getOnShare", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class UserEvents {
        private final Function0<Unit> onAddToCalendar;
        private final Function1<String, Unit> onClickPrimaryButton;
        private final Function1<String, Unit> onClickSecondaryButton;
        private final Function0<Unit> onShare;

        public UserEvents() {
            this(null, null, null, null, 15, null);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UserEvents)) {
                return false;
            }
            UserEvents userEvents = (UserEvents) other;
            return Intrinsics.areEqual(this.onClickPrimaryButton, userEvents.onClickPrimaryButton) && Intrinsics.areEqual(this.onClickSecondaryButton, userEvents.onClickSecondaryButton) && Intrinsics.areEqual(this.onAddToCalendar, userEvents.onAddToCalendar) && Intrinsics.areEqual(this.onShare, userEvents.onShare);
        }

        public int hashCode() {
            return (((((this.onClickPrimaryButton.hashCode() * 31) + this.onClickSecondaryButton.hashCode()) * 31) + this.onAddToCalendar.hashCode()) * 31) + this.onShare.hashCode();
        }

        public String toString() {
            return "UserEvents(onClickPrimaryButton=" + this.onClickPrimaryButton + ", onClickSecondaryButton=" + this.onClickSecondaryButton + ", onAddToCalendar=" + this.onAddToCalendar + ", onShare=" + this.onShare + ')';
        }

        /* JADX WARN: Multi-variable type inference failed */
        public UserEvents(Function1<? super String, Unit> onClickPrimaryButton, Function1<? super String, Unit> onClickSecondaryButton, Function0<Unit> onAddToCalendar, Function0<Unit> onShare) {
            Intrinsics.checkNotNullParameter(onClickPrimaryButton, "onClickPrimaryButton");
            Intrinsics.checkNotNullParameter(onClickSecondaryButton, "onClickSecondaryButton");
            Intrinsics.checkNotNullParameter(onAddToCalendar, "onAddToCalendar");
            Intrinsics.checkNotNullParameter(onShare, "onShare");
            this.onClickPrimaryButton = onClickPrimaryButton;
            this.onClickSecondaryButton = onClickSecondaryButton;
            this.onAddToCalendar = onAddToCalendar;
            this.onShare = onShare;
        }

        public /* synthetic */ UserEvents(Function1 function1, Function1 function12, Function0 function0, Function0 function02, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Function1<String, Unit>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.UserEvents.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            } : function1, (i & 2) != 0 ? new Function1<String, Unit>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.UserEvents.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            } : function12, (i & 4) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.UserEvents.3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function0, (i & 8) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.UserEvents.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function02);
        }

        public final Function1<String, Unit> getOnClickPrimaryButton() {
            return this.onClickPrimaryButton;
        }

        public final Function1<String, Unit> getOnClickSecondaryButton() {
            return this.onClickSecondaryButton;
        }

        public final Function0<Unit> getOnAddToCalendar() {
            return this.onAddToCalendar;
        }

        public final Function0<Unit> getOnShare() {
            return this.onShare;
        }
    }

    /* JADX INFO: compiled from: NewEventHeaderView.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/alps/liveevent/view/NewEventHeaderView$UIEvents;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lkotlin/Function0;", "", "onCountdownFinished", "Lkotlin/jvm/functions/Function0;", "getOnCountdownFinished", "()Lkotlin/jvm/functions/Function0;", "onRequestCountdownSync", "getOnRequestCountdownSync", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class UIEvents {
        private final Function0<Unit> onCountdownFinished;
        private final Function0<Unit> onRequestCountdownSync;

        public UIEvents() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UIEvents)) {
                return false;
            }
            UIEvents uIEvents = (UIEvents) other;
            return Intrinsics.areEqual(this.onCountdownFinished, uIEvents.onCountdownFinished) && Intrinsics.areEqual(this.onRequestCountdownSync, uIEvents.onRequestCountdownSync);
        }

        public int hashCode() {
            return (this.onCountdownFinished.hashCode() * 31) + this.onRequestCountdownSync.hashCode();
        }

        public String toString() {
            return "UIEvents(onCountdownFinished=" + this.onCountdownFinished + ", onRequestCountdownSync=" + this.onRequestCountdownSync + ')';
        }

        public UIEvents(Function0<Unit> onCountdownFinished, Function0<Unit> onRequestCountdownSync) {
            Intrinsics.checkNotNullParameter(onCountdownFinished, "onCountdownFinished");
            Intrinsics.checkNotNullParameter(onRequestCountdownSync, "onRequestCountdownSync");
            this.onCountdownFinished = onCountdownFinished;
            this.onRequestCountdownSync = onRequestCountdownSync;
        }

        public /* synthetic */ UIEvents(Function0 function0, Function0 function02, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.UIEvents.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function0, (i & 2) != 0 ? new Function0<Unit>() { // from class: com.amazon.music.alps.liveevent.view.NewEventHeaderView.UIEvents.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function02);
        }

        public final Function0<Unit> getOnCountdownFinished() {
            return this.onCountdownFinished;
        }

        public final Function0<Unit> getOnRequestCountdownSync() {
            return this.onRequestCountdownSync;
        }
    }
}
