package com.amazon.music.alps.liveevent;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LineupGroupNameType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/alps/liveevent/LineupGroupNameType;", "", "(Ljava/lang/String;I)V", "MONTH_DAY", "DAY_NUMBER", "UNKNOWN", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LineupGroupNameType {
    MONTH_DAY,
    DAY_NUMBER,
    UNKNOWN;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: LineupGroupNameType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/alps/liveevent/LineupGroupNameType$Companion;", "", "()V", "from", "Lcom/amazon/music/alps/liveevent/LineupGroupNameType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LineupGroupNameType from(String type) {
            LineupGroupNameType lineupGroupNameType;
            LineupGroupNameType[] lineupGroupNameTypeArrValues = LineupGroupNameType.values();
            int length = lineupGroupNameTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    lineupGroupNameType = null;
                    break;
                }
                lineupGroupNameType = lineupGroupNameTypeArrValues[i];
                i++;
                if (Intrinsics.areEqual(lineupGroupNameType.name(), type)) {
                    break;
                }
            }
            return lineupGroupNameType == null ? LineupGroupNameType.UNKNOWN : lineupGroupNameType;
        }
    }
}
