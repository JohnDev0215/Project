package com.amazon.avod.media;

import com.amazon.music.find.api.RemoteSearchApi;
import com.google.common.base.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: AudioStreamType.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/amazon/avod/media/AudioStreamType;", "", "fourCC", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getFourCC", "()Ljava/lang/String;", RemoteSearchApi.ATMOS_ASSET_QUALITY, "DDP", "AACL", "AACH", "AACHV2", "Companion", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum AudioStreamType {
    ATMOS("ATMO"),
    DDP("EC-3"),
    AACL("AACL"),
    AACH("AACH"),
    AACHV2("ACH2");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String fourCC;

    AudioStreamType(String str) {
        this.fourCC = str;
        Preconditions.checkArgument(str.length() == 4, "fourCC must be 4 characters long", new Object[0]);
    }

    public final String getFourCC() {
        return this.fourCC;
    }

    /* JADX INFO: compiled from: AudioStreamType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/avod/media/AudioStreamType$Companion;", "", "()V", "fromFourCC", "Lcom/amazon/avod/media/AudioStreamType;", "fourCC", "", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AudioStreamType fromFourCC(String fourCC) {
            Intrinsics.checkNotNullParameter(fourCC, "fourCC");
            AudioStreamType[] audioStreamTypeArrValues = AudioStreamType.values();
            int length = audioStreamTypeArrValues.length;
            int i = 0;
            while (i < length) {
                AudioStreamType audioStreamType = audioStreamTypeArrValues[i];
                i++;
                if (StringsKt__StringsJVMKt.equals(audioStreamType.getFourCC(), fourCC, true)) {
                    return audioStreamType;
                }
            }
            return null;
        }
    }
}
