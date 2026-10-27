package com.visioncritical.touchpointkit.commons;

import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointLogger.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/visioncritical/touchpointkit/commons/TouchPointLogger;", "", "()V", "Companion", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointLogger {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: TouchPointLogger.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/visioncritical/touchpointkit/commons/TouchPointLogger$Companion;", "", "()V", "debugLog", "", ParserUtil.TAG_QUERY_PARAM_NAME, "", "message", "errorLog", "infoLog", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void debugLog(String tag, String message) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(message, "message");
            if (TouchPointActivity.INSTANCE.getShared().getEnableDebugLogs()) {
                Log.d(tag, message);
            }
        }

        public final void infoLog(String tag, String message) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(message, "message");
            if (TouchPointActivity.INSTANCE.getShared().getDisableAllLogs()) {
                return;
            }
            Log.i(tag, message);
        }

        public final void errorLog(String tag, String message) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(message, "message");
            if (TouchPointActivity.INSTANCE.getShared().getDisableAllLogs()) {
                return;
            }
            Log.e(tag, message);
        }
    }
}
