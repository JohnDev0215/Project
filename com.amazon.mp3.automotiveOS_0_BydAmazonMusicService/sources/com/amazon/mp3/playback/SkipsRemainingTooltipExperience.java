package com.amazon.mp3.playback;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.SkipTooltipExperience;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.view.TooltipDisplayer;
import com.amazon.mp3.view.TooltipView;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipsRemainingTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0012H\u0016R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience;", "Lcom/amazon/mp3/playback/SkipTooltipExperience;", "context", "Landroid/content/Context;", "builder", "Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience$Builder;", "tooltipExperienceBuilder", "Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;", "(Landroid/content/Context;Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience$Builder;Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;)V", "maxNumTooltipViews", "", "getMaxNumTooltipViews", "()I", "setMaxNumTooltipViews", "(I)V", "canPresentTooltip", "", "trackTooltipDismissFlexEvent", "", "trackTooltipViewFlexEvent", "containerType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", "updateTooltipText", "Builder", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkipsRemainingTooltipExperience extends SkipTooltipExperience {
    private static final String SKIPS_REMAINING_TOOLTIP_EXPERIENCE_NAME;
    private static final String TAG;
    private static final String dismissFlexEvent;
    private static final String viewFlexEventOneSkipRemainingMiniPlayer;
    private static final String viewFlexEventOneSkipRemainingNowPlaying;
    private static final String viewFlexEventThreeSkipsRemainingMiniPlayer;
    private static final String viewFlexEventThreeSkipsRemainingNowPlaying;
    private int maxNumTooltipViews;

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public int getMaxNumTooltipViews() {
        return this.maxNumTooltipViews;
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void setMaxNumTooltipViews(int i) {
        this.maxNumTooltipViews = i;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipsRemainingTooltipExperience(Context context, Builder builder, SkipTooltipExperience.Builder tooltipExperienceBuilder) {
        super(context, tooltipExperienceBuilder, SKIPS_REMAINING_TOOLTIP_EXPERIENCE_NAME);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(tooltipExperienceBuilder, "tooltipExperienceBuilder");
        this.maxNumTooltipViews = Builder.INSTANCE.getMAX_NUM_TOOLTIP_VIEWS();
        setMaxNumTooltipViews(builder.getMNumViews());
    }

    static {
        String simpleName = SkipsRemainingTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SkipsRemainingTooltipExp…ce::class.java.simpleName");
        TAG = simpleName;
        SKIPS_REMAINING_TOOLTIP_EXPERIENCE_NAME = "skips_remaining_tooltip_experience";
        viewFlexEventThreeSkipsRemainingNowPlaying = "threeSkipsRemainingTooltipNowPlayingViewEvent";
        viewFlexEventThreeSkipsRemainingMiniPlayer = "threeSkipsRemainingTooltipMiniPlayerViewEvent";
        viewFlexEventOneSkipRemainingNowPlaying = "oneSkipRemainingTooltipNowPlayingViewEvent";
        viewFlexEventOneSkipRemainingMiniPlayer = "oneSkipRemainingTooltipMiniPlayerViewEvent";
        dismissFlexEvent = "skipsRemainingTooltipDismissEvent";
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public boolean canPresentTooltip() {
        Integer tooltipSkipsRemaining = getTooltipSkipsRemaining();
        if (tooltipSkipsRemaining != null && tooltipSkipsRemaining.intValue() == 1) {
            return true;
        }
        Integer tooltipSkipsRemaining2 = getTooltipSkipsRemaining();
        return tooltipSkipsRemaining2 != null && tooltipSkipsRemaining2.intValue() == 3;
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void updateTooltipText() {
        Integer tooltipSkipsRemaining = getTooltipSkipsRemaining();
        if (tooltipSkipsRemaining != null && tooltipSkipsRemaining.intValue() == 1) {
            setTooltipTextResId(Integer.valueOf(R.string.dmusic_now_playing_skip_limit_one_remaining_tooltip));
        } else if (tooltipSkipsRemaining != null && tooltipSkipsRemaining.intValue() == 3) {
            setTooltipTextResId(Integer.valueOf(R.string.dmusic_now_playing_skip_limit_three_remaining_tooltip));
        }
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void trackTooltipViewFlexEvent(ContainerType containerType) {
        String str;
        Intrinsics.checkNotNullParameter(containerType, "containerType");
        if (containerType != ContainerType.NOW_PLAYING && containerType != ContainerType.MINI_TRANSPORT) {
            Log.info(TAG, "Invalid containerType for view event");
            return;
        }
        String str2 = null;
        Integer tooltipSkipsRemaining = getTooltipSkipsRemaining();
        if (tooltipSkipsRemaining != null) {
            int iIntValue = tooltipSkipsRemaining.intValue();
            if (iIntValue == 1) {
                str = containerType == ContainerType.MINI_TRANSPORT ? viewFlexEventOneSkipRemainingMiniPlayer : viewFlexEventOneSkipRemainingNowPlaying;
            } else if (iIntValue == 3) {
                str = containerType == ContainerType.MINI_TRANSPORT ? viewFlexEventThreeSkipsRemainingMiniPlayer : viewFlexEventThreeSkipsRemainingNowPlaying;
            }
            str2 = str;
        }
        if (str2 != null) {
            FlexEvent flexEventBuild = FlexEvent.builder(str2).withEmitterId("em_200").build();
            Log.info(TAG, "Sending flex event for tooltip viewed");
            MetricsLogger.sendEvent(flexEventBuild);
            return;
        }
        Log.info(TAG, "Unable to send flex event for tooltip viewed");
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void trackTooltipDismissFlexEvent() {
        Log.info(TAG, "Sending flex event for tooltip clicked");
        MetricsLogger.sendEvent(FlexEvent.builder(dismissFlexEvent).withEmitterId("em_200").build());
    }

    /* JADX INFO: compiled from: SkipsRemainingTooltipExperience.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience$Builder;", "", "Landroid/view/View;", "rootView", "setRootView", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience;", "createTooltipExperience", "mRootView", "Landroid/view/View;", "", "mNumViews", "I", "getMNumViews", "()I", "setMNumViews", "(I)V", "Lcom/amazon/mp3/view/TooltipDisplayer;", "tooltip", "Lcom/amazon/mp3/view/TooltipDisplayer;", "getTooltip", "()Lcom/amazon/mp3/view/TooltipDisplayer;", "setTooltip", "(Lcom/amazon/mp3/view/TooltipDisplayer;)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static class Builder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int MAX_NUM_TOOLTIP_VIEWS = 1;
        private int mNumViews = MAX_NUM_TOOLTIP_VIEWS;
        private View mRootView;
        private TooltipDisplayer tooltip;

        public int getMNumViews() {
            return this.mNumViews;
        }

        public void setMNumViews(int i) {
            this.mNumViews = i;
        }

        public final Builder setRootView(View rootView) {
            Intrinsics.checkNotNullParameter(rootView, "rootView");
            this.mRootView = rootView;
            View viewFindViewById = rootView.findViewById(R.id.TooltipDisplayerContainer);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.…ooltipDisplayerContainer)");
            this.tooltip = new TooltipDisplayer((TooltipView) viewFindViewById);
            return this;
        }

        public final SkipsRemainingTooltipExperience createTooltipExperience(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new SkipsRemainingTooltipExperience(context, this, new SkipTooltipExperience.Builder());
        }

        /* JADX INFO: compiled from: SkipsRemainingTooltipExperience.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience$Builder$Companion;", "", "()V", "MAX_NUM_TOOLTIP_VIEWS", "", "getMAX_NUM_TOOLTIP_VIEWS", "()I", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final int getMAX_NUM_TOOLTIP_VIEWS() {
                return Builder.MAX_NUM_TOOLTIP_VIEWS;
            }
        }
    }
}
