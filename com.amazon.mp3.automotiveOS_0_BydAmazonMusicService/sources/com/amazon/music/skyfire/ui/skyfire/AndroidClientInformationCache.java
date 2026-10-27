package com.amazon.music.skyfire.ui.skyfire;

import CoreInterface.v1_0.ClientInformation;
import PlaybackInterface.v1_0.ImmutablePlaybackSpeedClientInformation;
import PlaybackInterface.v1_0.ImmutableVolumeClientInformation;
import PlaybackInterface.v1_0.PlaybackSpeedClientInformation;
import PlaybackInterface.v1_0.VolumeClientInformation;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.amazon.music.skyfire.core.network.ClientInformationCache;
import com.amazon.music.skyfire.core.network.Mappers;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidClientInformationCache.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0096\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000fR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/AndroidClientInformationCache;", "Lcom/amazon/music/skyfire/core/network/ClientInformationCache;", "()V", "playbackSpeed", "", "getPlaybackSpeed", "()D", "setPlaybackSpeed", "(D)V", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "getVolume", "setVolume", "get", "LCoreInterface/v1_0/ClientInformation;", "clientInformationName", "", "queryKey", TetheredMessageKey.clientInformation, "set", "", "clientInformationString", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AndroidClientInformationCache implements ClientInformationCache {
    public static final AndroidClientInformationCache INSTANCE = new AndroidClientInformationCache();
    private static double playbackSpeed;
    private static double volume;

    private AndroidClientInformationCache() {
    }

    @Override // com.amazon.music.skyfire.core.network.ClientInformationCache
    public String queryKey(ClientInformation clientInformation) {
        Intrinsics.checkNotNullParameter(clientInformation, "clientInformation");
        if (clientInformation instanceof ImmutableVolumeClientInformation) {
            return MediaRouteProviderProtocol.CLIENT_DATA_VOLUME;
        }
        if (clientInformation instanceof ImmutablePlaybackSpeedClientInformation) {
            return "playbackSpeed";
        }
        return null;
    }

    @Override // com.amazon.music.skyfire.core.network.ClientInformationCache
    public ClientInformation get(String clientInformationName) {
        Intrinsics.checkNotNullParameter(clientInformationName, "clientInformationName");
        if (Intrinsics.areEqual(clientInformationName, VolumeClientInformation.class.getCanonicalName())) {
            return ImmutableVolumeClientInformation.builder().volume(Double.valueOf(volume)).build();
        }
        if (Intrinsics.areEqual(clientInformationName, PlaybackSpeedClientInformation.class.getCanonicalName())) {
            return ImmutablePlaybackSpeedClientInformation.builder().playbackSpeed(Double.valueOf(playbackSpeed)).build();
        }
        return null;
    }

    public final void set(String clientInformationString) {
        Intrinsics.checkNotNullParameter(clientInformationString, "clientInformationString");
        ClientInformation clientInformation = (ClientInformation) Mappers.mapper().readValue(clientInformationString, ClientInformation.class);
        if (clientInformation instanceof VolumeClientInformation) {
            Double dVolume = ((VolumeClientInformation) clientInformation).volume();
            Intrinsics.checkNotNullExpressionValue(dVolume, "clientInformation.volume()");
            volume = dVolume.doubleValue();
        } else if (clientInformation instanceof PlaybackSpeedClientInformation) {
            Double dPlaybackSpeed = ((PlaybackSpeedClientInformation) clientInformation).playbackSpeed();
            Intrinsics.checkNotNullExpressionValue(dPlaybackSpeed, "clientInformation.playbackSpeed()");
            playbackSpeed = dPlaybackSpeed.doubleValue();
        }
    }

    public final double getVolume() {
        return volume;
    }

    public final void setVolume(double d) {
        volume = d;
    }

    public final double getPlaybackSpeed() {
        return playbackSpeed;
    }

    public final void setPlaybackSpeed(double d) {
        playbackSpeed = d;
    }
}
