package com.amazon.video.sdk.stream;

import com.google.android.gms.cast.MediaTrack;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimedTextStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "", "(Ljava/lang/String;I)V", "Commentary", "Descriptive", "Dialog", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum TimedTextSubtype {
    Commentary,
    Descriptive,
    Dialog;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: TimedTextStream.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextSubtype$Companion;", "", "()V", "from", "Lcom/amazon/video/sdk/stream/TimedTextSubtype;", "value", "", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TimedTextSubtype from(String value) {
            String lowerCase;
            if (value == null) {
                lowerCase = null;
            } else {
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                lowerCase = value.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            if (lowerCase == null) {
                lowerCase = "";
            }
            int iHashCode = lowerCase.hashCode();
            if (iHashCode != -1724545844) {
                if (iHashCode != -1332085432) {
                    if (iHashCode == 899152809 && lowerCase.equals(MediaTrack.ROLE_COMMENTARY)) {
                        return TimedTextSubtype.Commentary;
                    }
                } else if (lowerCase.equals("dialog")) {
                    return TimedTextSubtype.Dialog;
                }
            } else if (lowerCase.equals("descriptive")) {
                return TimedTextSubtype.Descriptive;
            }
            return TimedTextSubtype.Dialog;
        }
    }
}
