package com.amazon.video.sdk.player;

import com.amazon.video.sdk.player.timeline.TimeUnit;
import kotlin.Metadata;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'VOD' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:372)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:337)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:322)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:293)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:266)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: ContentConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/video/sdk/player/VideoType;", "", "timeUnit", "Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "(Ljava/lang/String;ILcom/amazon/video/sdk/player/timeline/TimeUnit;)V", "getTimeUnit$AmazonAndroidVideoPlayer_release", "()Lcom/amazon/video/sdk/player/timeline/TimeUnit;", "isLive", "", "isLive$AmazonAndroidVideoPlayer_release", "VOD", "Trailer", "Live", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoType {
    private static final /* synthetic */ VideoType[] $VALUES;
    public static final VideoType Live;
    public static final VideoType Trailer;
    public static final VideoType VOD;
    private final TimeUnit timeUnit;

    private static final /* synthetic */ VideoType[] $values() {
        return new VideoType[]{VOD, Trailer, Live};
    }

    public static VideoType valueOf(String str) {
        return (VideoType) Enum.valueOf(VideoType.class, str);
    }

    public static VideoType[] values() {
        return (VideoType[]) $VALUES.clone();
    }

    private VideoType(String str, int i, TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    /* JADX INFO: renamed from: getTimeUnit$AmazonAndroidVideoPlayer_release, reason: from getter */
    public final TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    static {
        TimeUnit timeUnit = TimeUnit.CONTENT_MILLIS;
        VOD = new VideoType("VOD", 0, timeUnit);
        Trailer = new VideoType("Trailer", 1, timeUnit);
        Live = new VideoType("Live", 2, TimeUnit.EPOCH_MILLIS);
        $VALUES = $values();
    }

    public final boolean isLive$AmazonAndroidVideoPlayer_release() {
        return this == Live;
    }
}
