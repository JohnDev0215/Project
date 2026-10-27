package com.amazon.video.sdk.stream;

import com.google.android.gms.cast.MediaTrack;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioStream.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/stream/AudioType;", "", "(Ljava/lang/String;I)V", "Commentary", "Descriptive", "Dialog", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AudioType {
    Commentary,
    Descriptive,
    Dialog;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: AudioStream.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/stream/AudioType$Companion;", "", "()V", "from", "Lcom/amazon/video/sdk/stream/AudioType;", "value", "", "to", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: AudioStream.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AudioType.values().length];
                iArr[AudioType.Commentary.ordinal()] = 1;
                iArr[AudioType.Descriptive.ordinal()] = 2;
                iArr[AudioType.Dialog.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioType from(String value) {
            String lowerCase;
            if (value == null) {
                lowerCase = "";
            } else {
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                lowerCase = value.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            int iHashCode = lowerCase.hashCode();
            if (iHashCode != -1724545844) {
                if (iHashCode != -1332085432) {
                    if (iHashCode == 899152809 && lowerCase.equals(MediaTrack.ROLE_COMMENTARY)) {
                        return AudioType.Commentary;
                    }
                } else if (lowerCase.equals("dialog")) {
                    return AudioType.Dialog;
                }
            } else if (lowerCase.equals("descriptive")) {
                return AudioType.Descriptive;
            }
            return AudioType.Dialog;
        }

        public final String to(AudioType value) {
            Intrinsics.checkNotNullParameter(value, "value");
            int i = WhenMappings.$EnumSwitchMapping$0[value.ordinal()];
            if (i == 1) {
                return MediaTrack.ROLE_COMMENTARY;
            }
            if (i == 2) {
                return "descriptive";
            }
            if (i == 3) {
                return "dialog";
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
