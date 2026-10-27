package com.amazon.avod.content.smoothstream.streamstate.retentionpolicy;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.experiments.MobileWeblab;
import com.amazon.avod.experiments.WeblabTreatment;
import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveRetentionPolicy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/content/smoothstream/streamstate/retentionpolicy/LiveRetentionPolicy;", "", "(Ljava/lang/String;I)V", "FIXED_DURATION_SLIDING_WINDOW", "DYNAMIC_DURATION_SLIDING_WINDOW", "ASCENDING_LIVE_EDGE_SLIDING_WINDOW", "Companion", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LiveRetentionPolicy {
    FIXED_DURATION_SLIDING_WINDOW,
    DYNAMIC_DURATION_SLIDING_WINDOW,
    ASCENDING_LIVE_EDGE_SLIDING_WINDOW;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: LiveRetentionPolicy.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lcom/amazon/avod/content/smoothstream/streamstate/retentionpolicy/LiveRetentionPolicy$Companion;", "", "()V", "getLiveRetentionPolicy", "Lcom/amazon/avod/content/smoothstream/streamstate/retentionpolicy/LiveRetentionPolicy;", "config", "Lcom/amazon/avod/content/config/SmoothStreamingPlaybackConfig;", "webLab", "Lcom/amazon/avod/experiments/MobileWeblab;", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: LiveRetentionPolicy.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[WeblabTreatment.values().length];
                iArr[WeblabTreatment.T1.ordinal()] = 1;
                iArr[WeblabTreatment.T2.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LiveRetentionPolicy getLiveRetentionPolicy(SmoothStreamingPlaybackConfig config, MobileWeblab webLab) {
            Intrinsics.checkNotNullParameter(config, "config");
            if (config.isLiveRetentionPolicyConfigEnabled()) {
                try {
                    String liveRetentionPolicy = config.getLiveRetentionPolicy();
                    Intrinsics.checkNotNullExpressionValue(liveRetentionPolicy, "config.liveRetentionPolicy");
                    return LiveRetentionPolicy.valueOf(liveRetentionPolicy);
                } catch (IllegalArgumentException unused) {
                    DLog.warnf("Unable to map " + config.getLiveRetentionPolicy() + " string to LiveRetentionPolicy enum");
                }
            } else if (webLab != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[webLab.getCurrentTreatment().ordinal()];
                if (i == 1) {
                    return LiveRetentionPolicy.DYNAMIC_DURATION_SLIDING_WINDOW;
                }
                if (i == 2) {
                    return LiveRetentionPolicy.ASCENDING_LIVE_EDGE_SLIDING_WINDOW;
                }
                return LiveRetentionPolicy.FIXED_DURATION_SLIDING_WINDOW;
            }
            return config.useDynamicFrontBufferForLiveStreaming() ? LiveRetentionPolicy.DYNAMIC_DURATION_SLIDING_WINDOW : LiveRetentionPolicy.FIXED_DURATION_SLIDING_WINDOW;
        }
    }
}
