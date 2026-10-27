package com.amazon.mp3.playback;

import android.content.Context;
import android.content.SharedPreferences;
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

/* JADX INFO: compiled from: SkipLimitEducationalTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 %2\u00020\u0001:\u0002&%B!\b\u0016\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0002H\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0016R\"\u0010\u0011\u001a\u00020\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006'"}, d2 = {"Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience;", "Lcom/amazon/mp3/playback/SkipTooltipExperience;", "", "getNumTooltipViews", "", "updateTooltipText", "", "hasRotatedOrientation", "fragmentIsInitializing", "incrementNumTooltipViews", "numTooltipViews", "isEligibleByTooltipViews", "canPresentTooltip", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", "containerType", "trackTooltipViewFlexEvent", "trackTooltipDismissFlexEvent", "maxNumTooltipViews", "I", "getMaxNumTooltipViews", "()I", "setMaxNumTooltipViews", "(I)V", "tooltipTextResId", "Ljava/lang/Integer;", "getTooltipTextResId", "()Ljava/lang/Integer;", "setTooltipTextResId", "(Ljava/lang/Integer;)V", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience$Builder;", "builder", "Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;", "tooltipExperienceBuilder", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience$Builder;Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;)V", "Companion", "Builder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SkipLimitEducationalTooltipExperience extends SkipTooltipExperience {
    private static final String NUM_TOOLTIP_VIEWS;
    private static final String SKIP_LIMIT_EDUCATIONAL_TOOLTIP_EXPERIENCE_NAME;
    private static final String TAG;
    private static final String dismissFlexEvent;
    private static final String viewFlexEventMiniPlayer;
    private static final String viewFlexEventNowPlaying;
    private int maxNumTooltipViews;
    private Integer tooltipTextResId;

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void updateTooltipText() {
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public int getMaxNumTooltipViews() {
        return this.maxNumTooltipViews;
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void setMaxNumTooltipViews(int i) {
        this.maxNumTooltipViews = i;
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public Integer getTooltipTextResId() {
        return this.tooltipTextResId;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipLimitEducationalTooltipExperience(Context context, Builder builder, SkipTooltipExperience.Builder tooltipExperienceBuilder) {
        super(context, tooltipExperienceBuilder, SKIP_LIMIT_EDUCATIONAL_TOOLTIP_EXPERIENCE_NAME);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(tooltipExperienceBuilder, "tooltipExperienceBuilder");
        this.maxNumTooltipViews = Builder.INSTANCE.getMAX_NUM_TOOLTIP_VIEWS();
        this.tooltipTextResId = Integer.valueOf(R.string.dmusic_now_playing_skip_limit_educational_tooltip);
        setMaxNumTooltipViews(builder.getMNumViews());
    }

    static {
        String simpleName = SkipLimitEducationalTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SkipLimitEducationalTool…ce::class.java.simpleName");
        TAG = simpleName;
        NUM_TOOLTIP_VIEWS = "num_skip_limit_educational_tooltip_views";
        SKIP_LIMIT_EDUCATIONAL_TOOLTIP_EXPERIENCE_NAME = "skip_limit_educational_tooltip_experience";
        viewFlexEventNowPlaying = "skipLimitEducationalTooltipNowPlayingViewEvent";
        viewFlexEventMiniPlayer = "skipLimitEducationalTooltipMiniPlayerViewEvent";
        dismissFlexEvent = "skipLimitEducationalTooltipDismissEvent";
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void incrementNumTooltipViews(boolean hasRotatedOrientation, boolean fragmentIsInitializing) {
        int numTooltipViews = getNumTooltipViews();
        if (hasRotatedOrientation || !fragmentIsInitializing || numTooltipViews > getMaxNumTooltipViews()) {
            return;
        }
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putInt(NUM_TOOLTIP_VIEWS, numTooltipViews + 1);
        editorEdit.apply();
    }

    private final int getNumTooltipViews() {
        return getPrefs().getInt(NUM_TOOLTIP_VIEWS, 0);
    }

    public boolean isEligibleByTooltipViews(int numTooltipViews) {
        return numTooltipViews <= getMaxNumTooltipViews();
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public boolean canPresentTooltip() {
        return canPresentExperience() && isEligibleByTooltipViews(getNumTooltipViews());
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void trackTooltipViewFlexEvent(ContainerType containerType) {
        Intrinsics.checkNotNullParameter(containerType, "containerType");
        if (containerType != ContainerType.NOW_PLAYING && containerType != ContainerType.MINI_TRANSPORT) {
            Log.info(TAG, "Invalid containerType for view event");
        } else {
            Log.info(TAG, "Sending flex event for tooltip viewed");
            MetricsLogger.sendEvent(FlexEvent.builder(containerType == ContainerType.MINI_TRANSPORT ? viewFlexEventMiniPlayer : viewFlexEventNowPlaying).withEmitterId("em_200").build());
        }
    }

    @Override // com.amazon.mp3.playback.SkipTooltipExperience
    public void trackTooltipDismissFlexEvent() {
        Log.info(TAG, "Sending flex event for tooltip clicked");
        MetricsLogger.sendEvent(FlexEvent.builder(dismissFlexEvent).withEmitterId("em_200").build());
    }

    /* JADX INFO: compiled from: SkipLimitEducationalTooltipExperience.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience$Builder;", "", "Landroid/view/View;", "rootView", "setRootView", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience;", "createTooltipExperience", "mRootView", "Landroid/view/View;", "", "mNumViews", "I", "getMNumViews", "()I", "setMNumViews", "(I)V", "Lcom/amazon/mp3/view/TooltipDisplayer;", "tooltip", "Lcom/amazon/mp3/view/TooltipDisplayer;", "getTooltip", "()Lcom/amazon/mp3/view/TooltipDisplayer;", "setTooltip", "(Lcom/amazon/mp3/view/TooltipDisplayer;)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int MAX_NUM_TOOLTIP_VIEWS = 1;
        private int mNumViews = MAX_NUM_TOOLTIP_VIEWS;
        private View mRootView;
        private TooltipDisplayer tooltip;

        public final int getMNumViews() {
            return this.mNumViews;
        }

        public final void setMNumViews(int i) {
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

        public final SkipLimitEducationalTooltipExperience createTooltipExperience(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new SkipLimitEducationalTooltipExperience(context, this, new SkipTooltipExperience.Builder());
        }

        /* JADX INFO: compiled from: SkipLimitEducationalTooltipExperience.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience$Builder$Companion;", "", "()V", "MAX_NUM_TOOLTIP_VIEWS", "", "getMAX_NUM_TOOLTIP_VIEWS", "()I", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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
