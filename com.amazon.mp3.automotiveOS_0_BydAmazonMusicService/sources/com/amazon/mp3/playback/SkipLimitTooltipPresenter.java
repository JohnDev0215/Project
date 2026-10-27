package com.amazon.mp3.playback;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.playback.SkipLimitEducationalTooltipExperience;
import com.amazon.mp3.playback.SkipTooltipExperience;
import com.amazon.mp3.playback.SkipsRemainingTooltipExperience;
import com.amazon.mp3.playback.view.MiniPlayerView;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.skiplimit.SkipLimitProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SkipLimitTooltipPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J.\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0011R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/playback/SkipLimitTooltipPresenter;", "", "()V", "mSkipLimitEducationalTooltipExperience", "Lcom/amazon/mp3/playback/SkipLimitEducationalTooltipExperience;", "mSkipsRemainingTooltipExperience", "Lcom/amazon/mp3/playback/SkipsRemainingTooltipExperience;", "destroyExperiences", "", "dismissSkipLimitEducationalTooltip", "dismissSkipsRemainingTooltip", "dismissTooltips", "getSkipsRemaining", "", "getTooltipType", "Lcom/amazon/mp3/playback/SkipTooltipExperience$TooltipType;", "isConfigured", "", "setSkipLimitEducationalTooltipExperience", "rootView", "Landroid/view/View;", "context", "Landroid/content/Context;", "setSkipLimitTooltipExperiences", "setSkipsRemainingTooltipExperience", "showSkipTooltip", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "inImmersiveMode", "hasRotatedOrientation", "fragmentIsInitializing", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SkipLimitTooltipPresenter {
    private SkipLimitEducationalTooltipExperience mSkipLimitEducationalTooltipExperience;
    private SkipsRemainingTooltipExperience mSkipsRemainingTooltipExperience;

    /* JADX INFO: compiled from: SkipLimitTooltipPresenter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SkipTooltipExperience.TooltipType.values().length];
            iArr[SkipTooltipExperience.TooltipType.EDUCATIONAL.ordinal()] = 1;
            iArr[SkipTooltipExperience.TooltipType.SKIPS_REMAINING.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final void setSkipLimitEducationalTooltipExperience(View rootView, Context context) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(context, "context");
        this.mSkipLimitEducationalTooltipExperience = new SkipLimitEducationalTooltipExperience.Builder().setRootView(rootView).createTooltipExperience(context);
    }

    public final void setSkipsRemainingTooltipExperience(View rootView, Context context) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(context, "context");
        this.mSkipsRemainingTooltipExperience = new SkipsRemainingTooltipExperience.Builder().setRootView(rootView).createTooltipExperience(context);
    }

    public final void setSkipLimitTooltipExperiences(View rootView, Context context) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(context, "context");
        setSkipLimitEducationalTooltipExperience(rootView, context);
        setSkipsRemainingTooltipExperience(rootView, context);
    }

    private final int getSkipsRemaining() {
        SkipLimitProvider skipLimitProvider;
        if (!(Playback.getInstance().getPlaybackProvider() instanceof SequencerPlaybackProvider) || (skipLimitProvider = Playback.getInstance().getPlaybackConfig().getSkipLimitProvider()) == null) {
            return 0;
        }
        return skipLimitProvider.getCount();
    }

    public final void showSkipTooltip(View rootView, StyleSheet styleSheet, boolean inImmersiveMode, boolean hasRotatedOrientation, boolean fragmentIsInitializing) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        SkipTooltipExperience.TooltipType tooltipType = getTooltipType();
        if (tooltipType == null) {
            return;
        }
        int skipsRemaining = getSkipsRemaining();
        int i = WhenMappings.$EnumSwitchMapping$0[tooltipType.ordinal()];
        if (i == 1) {
            SkipLimitEducationalTooltipExperience skipLimitEducationalTooltipExperience = this.mSkipLimitEducationalTooltipExperience;
            if (skipLimitEducationalTooltipExperience == null) {
                return;
            }
            skipLimitEducationalTooltipExperience.showTooltip(rootView, hasRotatedOrientation, fragmentIsInitializing, styleSheet, 0, rootView instanceof MiniPlayerView, inImmersiveMode);
            return;
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        SkipsRemainingTooltipExperience skipsRemainingTooltipExperience = this.mSkipsRemainingTooltipExperience;
        if (skipsRemainingTooltipExperience == null) {
            return;
        }
        skipsRemainingTooltipExperience.showTooltip(rootView, hasRotatedOrientation, fragmentIsInitializing, styleSheet, Integer.valueOf(skipsRemaining), rootView instanceof MiniPlayerView, inImmersiveMode);
    }

    public final void dismissSkipLimitEducationalTooltip() {
        SkipLimitEducationalTooltipExperience skipLimitEducationalTooltipExperience = this.mSkipLimitEducationalTooltipExperience;
        if (skipLimitEducationalTooltipExperience == null) {
            return;
        }
        skipLimitEducationalTooltipExperience.dismissTooltip(true);
    }

    public final void dismissSkipsRemainingTooltip() {
        SkipsRemainingTooltipExperience skipsRemainingTooltipExperience = this.mSkipsRemainingTooltipExperience;
        if (skipsRemainingTooltipExperience == null) {
            return;
        }
        skipsRemainingTooltipExperience.dismissTooltip(false);
    }

    public final void dismissTooltips() {
        dismissSkipLimitEducationalTooltip();
        dismissSkipsRemainingTooltip();
    }

    public final void destroyExperiences() {
        SkipsRemainingTooltipExperience skipsRemainingTooltipExperience = this.mSkipsRemainingTooltipExperience;
        if (skipsRemainingTooltipExperience != null) {
            skipsRemainingTooltipExperience.onDestroy();
        }
        SkipLimitEducationalTooltipExperience skipLimitEducationalTooltipExperience = this.mSkipLimitEducationalTooltipExperience;
        if (skipLimitEducationalTooltipExperience == null) {
            return;
        }
        skipLimitEducationalTooltipExperience.onDestroy();
    }

    public final SkipTooltipExperience.TooltipType getTooltipType() {
        SkipLimitEducationalTooltipExperience skipLimitEducationalTooltipExperience;
        if (AmazonApplication.getCapabilities().isFreeTierSkipLimitEducationalTooltipEnabled() && (skipLimitEducationalTooltipExperience = this.mSkipLimitEducationalTooltipExperience) != null && skipLimitEducationalTooltipExperience.canPresentTooltip()) {
            return SkipTooltipExperience.TooltipType.EDUCATIONAL;
        }
        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
        if ((cloudQueueSequencer == null || cloudQueueSequencer.enforceSkipLimitOrTooltip()) && AmazonApplication.getCapabilities().isFreeTierSkipsRemainingTooltipEnabled() && this.mSkipsRemainingTooltipExperience != null && getSkipsRemaining() == 1) {
            return SkipTooltipExperience.TooltipType.SKIPS_REMAINING;
        }
        return null;
    }

    public final boolean isConfigured() {
        return (this.mSkipLimitEducationalTooltipExperience == null || this.mSkipsRemainingTooltipExperience == null) ? false : true;
    }
}
