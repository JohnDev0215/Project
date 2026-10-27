package com.amazon.video.sdk.download;

import com.amazon.avod.media.MediaQuality;
import com.google.common.collect.ImmutableBiMap;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 com.amazon.video.sdk.download.DownloadQuality, still in use, count: 1, list:
  (r0v0 com.amazon.video.sdk.download.DownloadQuality) from 0x003e: INVOKE (r0v1 com.google.common.collect.ImmutableBiMap<com.amazon.video.sdk.download.DownloadQuality, com.amazon.avod.media.MediaQuality>) = 
  (r0v0 com.amazon.video.sdk.download.DownloadQuality)
  (wrap:com.amazon.avod.media.MediaQuality:0x0036: SGET  A[WRAPPED] (LINE:32) com.amazon.avod.media.MediaQuality.LOWEST com.amazon.avod.media.MediaQuality)
  (r2v1 com.amazon.video.sdk.download.DownloadQuality)
  (wrap:com.amazon.avod.media.MediaQuality:0x0038: SGET  A[WRAPPED] (LINE:33) com.amazon.avod.media.MediaQuality.LOW com.amazon.avod.media.MediaQuality)
  (r4v0 com.amazon.video.sdk.download.DownloadQuality)
  (wrap:com.amazon.avod.media.MediaQuality:0x003a: SGET  A[WRAPPED] (LINE:34) com.amazon.avod.media.MediaQuality.MEDIUM com.amazon.avod.media.MediaQuality)
  (r6v0 com.amazon.video.sdk.download.DownloadQuality)
  (wrap:com.amazon.avod.media.MediaQuality:0x003c: SGET  A[WRAPPED] (LINE:35) com.amazon.avod.media.MediaQuality.HIGH com.amazon.avod.media.MediaQuality)
 STATIC call: com.google.common.collect.ImmutableBiMap.of(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):com.google.common.collect.ImmutableBiMap A[MD:<K, V>:(K, V, K, V, K, V, K, V):com.google.common.collect.ImmutableBiMap<K, V> (m)] (LINE:31)
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: DownloadQuality.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadQuality;", "", "(Ljava/lang/String;I)V", "Lowest", "Low", "Medium", "High", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadQuality {
    Lowest,
    Low,
    Medium,
    High;


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ImmutableBiMap<DownloadQuality, MediaQuality> rothkoToLegacy;

    public static DownloadQuality valueOf(String str) {
        return (DownloadQuality) Enum.valueOf(DownloadQuality.class, str);
    }

    public static DownloadQuality[] values() {
        return (DownloadQuality[]) $VALUES.clone();
    }

    private DownloadQuality() {
    }

    static {
        ImmutableBiMap<DownloadQuality, MediaQuality> immutableBiMapOf = ImmutableBiMap.of(new DownloadQuality(), MediaQuality.LOWEST, new DownloadQuality(), MediaQuality.LOW, new DownloadQuality(), MediaQuality.MEDIUM, new DownloadQuality(), MediaQuality.HIGH);
        Intrinsics.checkNotNullExpressionValue(immutableBiMapOf, "of(\n            Download…diaQuality.HIGH\n        )");
        rothkoToLegacy = immutableBiMapOf;
    }

    /* JADX INFO: compiled from: DownloadQuality.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0006R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadQuality$Companion;", "", "()V", "rothkoToLegacy", "Lcom/google/common/collect/ImmutableBiMap;", "Lcom/amazon/video/sdk/download/DownloadQuality;", "Lcom/amazon/avod/media/MediaQuality;", "getRothkoToLegacy", "()Lcom/google/common/collect/ImmutableBiMap;", "downloadQualityToMediaQuality", "downloadQuality", "mediaQualityToDownloadQuality", "mediaQualityUnsanitized", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImmutableBiMap<DownloadQuality, MediaQuality> getRothkoToLegacy() {
            return DownloadQuality.rothkoToLegacy;
        }

        public final MediaQuality downloadQualityToMediaQuality(DownloadQuality downloadQuality) {
            Intrinsics.checkNotNullParameter(downloadQuality, "downloadQuality");
            MediaQuality mediaQuality = getRothkoToLegacy().get(downloadQuality);
            if (mediaQuality != null) {
                return mediaQuality;
            }
            String str = String.format("cannot convert type: %s", Arrays.copyOf(new Object[]{downloadQuality}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
            throw new IllegalArgumentException(str);
        }

        public final DownloadQuality mediaQualityToDownloadQuality(MediaQuality mediaQualityUnsanitized) {
            Intrinsics.checkNotNullParameter(mediaQualityUnsanitized, "mediaQualityUnsanitized");
            boolean z = mediaQualityUnsanitized == MediaQuality.HIGHEST;
            if (z) {
                mediaQualityUnsanitized = MediaQuality.HIGH;
            } else if (z) {
                throw new NoWhenBranchMatchedException();
            }
            DownloadQuality downloadQuality = getRothkoToLegacy().inverse().get(mediaQualityUnsanitized);
            if (downloadQuality != null) {
                return downloadQuality;
            }
            String str = String.format("cannot convert type: %s", Arrays.copyOf(new Object[]{mediaQualityUnsanitized}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
            throw new IllegalArgumentException(str);
        }
    }
}
