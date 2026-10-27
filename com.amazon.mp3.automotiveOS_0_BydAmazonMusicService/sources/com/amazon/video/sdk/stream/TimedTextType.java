package com.amazon.video.sdk.stream;

import com.google.android.gms.cast.MediaTrack;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimedTextStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextType;", "", "(Ljava/lang/String;I)V", "Caption", "SDH", "Subtitle", "Mandatory", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum TimedTextType {
    Caption,
    SDH,
    Subtitle,
    Mandatory;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: TimedTextStream.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextType$Companion;", "", "()V", "from", "Lcom/amazon/video/sdk/stream/TimedTextType;", "value", "", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        public final TimedTextType from(String value) {
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
            switch (lowerCase.hashCode()) {
                case -2060497896:
                    if (lowerCase.equals(MediaTrack.ROLE_SUBTITLE)) {
                        return TimedTextType.Subtitle;
                    }
                    break;
                case -392910375:
                    if (lowerCase.equals("mandatory")) {
                        return TimedTextType.Mandatory;
                    }
                    break;
                case 113719:
                    if (lowerCase.equals("sdh")) {
                        return TimedTextType.SDH;
                    }
                    break;
                case 552573414:
                    if (lowerCase.equals(MediaTrack.ROLE_CAPTION)) {
                        return TimedTextType.Caption;
                    }
                    break;
            }
            return TimedTextType.Caption;
        }
    }
}
