package com.amazon.music.alps.liveevent;

import com.amazon.identity.auth.device.utils.AccountConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LineupEndpointType.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/music/alps/liveevent/LineupEndpointType;", "", "(Ljava/lang/String;I)V", "PRIMEVIDEO", "TWITCH_URL", "TWITCH_ID", "UNKNOWN", "Companion", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum LineupEndpointType {
    PRIMEVIDEO,
    TWITCH_URL,
    TWITCH_ID,
    UNKNOWN;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: LineupEndpointType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/alps/liveevent/LineupEndpointType$Companion;", "", "()V", "from", "Lcom/amazon/music/alps/liveevent/LineupEndpointType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LineupEndpointType from(String type) {
            LineupEndpointType lineupEndpointType;
            LineupEndpointType[] lineupEndpointTypeArrValues = LineupEndpointType.values();
            int length = lineupEndpointTypeArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    lineupEndpointType = null;
                    break;
                }
                lineupEndpointType = lineupEndpointTypeArrValues[i];
                i++;
                if (Intrinsics.areEqual(lineupEndpointType.name(), type)) {
                    break;
                }
            }
            return lineupEndpointType == null ? LineupEndpointType.UNKNOWN : lineupEndpointType;
        }
    }
}
