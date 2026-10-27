package com.amazon.mp3.playback;

import com.amazon.music.media.playback.PlaybackAudioQuality;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackAudioQualityToAudioQuality.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/playback/PlaybackAudioQualityToAudioQuality;", "", "Lcom/amazon/music/media/playback/PlaybackAudioQuality;", "playbackAudioQuality", "", "isStandard", "isHD", "isUHD", "isHigh", "isLow", "isKatana", "isImmersive", "isAtmos", "isRa360", "isAtmosOr360", "isUnknown", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaybackAudioQualityToAudioQuality {
    private static final HashSet<PlaybackAudioQuality> atmosQuality;
    private static final HashSet<PlaybackAudioQuality> hDQuality;
    private static final HashSet<PlaybackAudioQuality> hiResQuality;
    private static final HashSet<PlaybackAudioQuality> immersiveQuality;
    private static final HashSet<PlaybackAudioQuality> ra360Quality;
    private static final HashSet<PlaybackAudioQuality> standardQuality;

    static {
        HashSet<PlaybackAudioQuality> hashSet = new HashSet<>();
        standardQuality = hashSet;
        HashSet<PlaybackAudioQuality> hashSet2 = new HashSet<>();
        hiResQuality = hashSet2;
        HashSet<PlaybackAudioQuality> hashSet3 = new HashSet<>();
        hDQuality = hashSet3;
        HashSet<PlaybackAudioQuality> hashSet4 = new HashSet<>();
        immersiveQuality = hashSet4;
        HashSet<PlaybackAudioQuality> hashSet5 = new HashSet<>();
        atmosQuality = hashSet5;
        HashSet<PlaybackAudioQuality> hashSet6 = new HashSet<>();
        ra360Quality = hashSet6;
        hashSet.add(PlaybackAudioQuality.LOW);
        hashSet.add(PlaybackAudioQuality.MEDIUM);
        hashSet.add(PlaybackAudioQuality.HIGH);
        hashSet2.add(PlaybackAudioQuality.UHD44);
        hashSet2.add(PlaybackAudioQuality.UHD48);
        hashSet2.add(PlaybackAudioQuality.UHD96);
        hashSet2.add(PlaybackAudioQuality.UHD192);
        hashSet3.add(PlaybackAudioQuality.HD44);
        hashSet3.add(PlaybackAudioQuality.HD48);
        hashSet3.add(PlaybackAudioQuality.HD96);
        hashSet3.add(PlaybackAudioQuality.HD192);
        hashSet4.add(PlaybackAudioQuality.THREE_DIMENSIONAL);
        hashSet5.add(PlaybackAudioQuality.THREED_DATMOS_LOW);
        hashSet5.add(PlaybackAudioQuality.THREED_DATMOS_MED);
        hashSet5.add(PlaybackAudioQuality.THREED_DATMOS_HIGH);
        hashSet6.add(PlaybackAudioQuality.THREED_S360RA_L0);
        hashSet6.add(PlaybackAudioQuality.THREED_S360RA_L1);
        hashSet6.add(PlaybackAudioQuality.THREED_S360RA_L2);
        hashSet6.add(PlaybackAudioQuality.THREED_S360RA_L3);
    }

    public final boolean isStandard(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return standardQuality.contains(playbackAudioQuality);
    }

    public final boolean isHD(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return hDQuality.contains(playbackAudioQuality);
    }

    public final boolean isUHD(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return hiResQuality.contains(playbackAudioQuality);
    }

    public final boolean isHigh(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return playbackAudioQuality == PlaybackAudioQuality.HIGH || playbackAudioQuality == PlaybackAudioQuality.MEDIUM;
    }

    public final boolean isLow(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return playbackAudioQuality == PlaybackAudioQuality.LOW;
    }

    public final boolean isKatana(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return isHD(playbackAudioQuality) || isUHD(playbackAudioQuality);
    }

    public final boolean isImmersive(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return immersiveQuality.contains(playbackAudioQuality);
    }

    public final boolean isAtmos(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return atmosQuality.contains(playbackAudioQuality);
    }

    public final boolean isRa360(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return ra360Quality.contains(playbackAudioQuality);
    }

    public final boolean isAtmosOr360(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return isAtmos(playbackAudioQuality) || isRa360(playbackAudioQuality);
    }

    public final boolean isUnknown(PlaybackAudioQuality playbackAudioQuality) {
        Intrinsics.checkNotNullParameter(playbackAudioQuality, "playbackAudioQuality");
        return playbackAudioQuality == PlaybackAudioQuality.UNKNOWN;
    }
}
