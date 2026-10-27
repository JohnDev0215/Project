package com.amazon.music.alps.liveevent;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventStatus.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventStatus;", "", "(Ljava/lang/String;I)V", "UPCOMING", "LIVE", "BETWEEN", "AFTERGLOW", "EXPIRED", "UNKNOWN", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LiveEventStatus {
    UPCOMING,
    LIVE,
    BETWEEN,
    AFTERGLOW,
    EXPIRED,
    UNKNOWN;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: LiveEventStatus.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/alps/liveevent/LiveEventStatus$Companion;", "", "()V", "from", "Lcom/amazon/music/alps/liveevent/LiveEventStatus;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LiveEventStatus from(String type) {
            LiveEventStatus liveEventStatus;
            LiveEventStatus[] liveEventStatusArrValues = LiveEventStatus.values();
            int length = liveEventStatusArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    liveEventStatus = null;
                    break;
                }
                liveEventStatus = liveEventStatusArrValues[i];
                i++;
                if (Intrinsics.areEqual(liveEventStatus.name(), type)) {
                    break;
                }
            }
            return liveEventStatus == null ? LiveEventStatus.UNKNOWN : liveEventStatus;
        }
    }
}
