package com.amazon.avod.dash.config.bola;

import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BolaAlgorithm.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/avod/dash/config/bola/BolaAlgorithm;", "", "(Ljava/lang/String;I)V", "STATIC_TRADE_OFF_FACTOR", "DYNAMIC_TRADE_OFF_FACTOR", "Companion", "ATVPlaybackDash_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum BolaAlgorithm {
    STATIC_TRADE_OFF_FACTOR,
    DYNAMIC_TRADE_OFF_FACTOR;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BolaAlgorithm.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/dash/config/bola/BolaAlgorithm$Companion;", "", "()V", "validateNameWithDefault", "Lcom/amazon/avod/dash/config/bola/BolaAlgorithm;", "enumName", "", "ATVPlaybackDash_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BolaAlgorithm validateNameWithDefault(String enumName) {
            Intrinsics.checkNotNullParameter(enumName, "enumName");
            BolaAlgorithm[] bolaAlgorithmArrValues = BolaAlgorithm.values();
            int length = bolaAlgorithmArrValues.length;
            int i = 0;
            while (i < length) {
                BolaAlgorithm bolaAlgorithm = bolaAlgorithmArrValues[i];
                i++;
                if (bolaAlgorithm.name().equals(enumName)) {
                    DLog.logf(Intrinsics.stringPlus("BolaAlgorithm lookup returning ", bolaAlgorithm.name()));
                    return bolaAlgorithm;
                }
            }
            DLog.logf("BolaAlgorithm lookup returning default");
            return BolaAlgorithm.DYNAMIC_TRADE_OFF_FACTOR;
        }
    }
}
