package com.amazon.avod.content.config;

import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: QualityScoreCappingStrategy.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/content/config/QualityScoreCappingStrategy;", "", "(Ljava/lang/String;I)V", "NO_QUALITY_SCORE", "MAX_QUALITY_SCORE", "IDEAL_QUALITY_SCORE", "Companion", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum QualityScoreCappingStrategy {
    NO_QUALITY_SCORE,
    MAX_QUALITY_SCORE,
    IDEAL_QUALITY_SCORE;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: QualityScoreCappingStrategy.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/content/config/QualityScoreCappingStrategy$Companion;", "", "()V", "lookupQualityScoreCappingStrategy", "Lcom/amazon/avod/content/config/QualityScoreCappingStrategy;", "strategy", "", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final QualityScoreCappingStrategy lookupQualityScoreCappingStrategy(String strategy) {
            if (strategy != null) {
                try {
                    return QualityScoreCappingStrategy.valueOf(strategy);
                } catch (Throwable unused) {
                    DLog.warnf("Unable to map " + ((Object) strategy) + " string to QualityScoreCappingStrategy enum, returning default");
                }
            }
            return QualityScoreCappingStrategy.NO_QUALITY_SCORE;
        }
    }
}
