package com.amazon.mp3.auto;

import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.media.auto.util.SubDeviceConnectionUtil;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.metrics.mts.MTSMetricsSender;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.podcast.Podcast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubDeviceConnectionUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\u001a\u0010\u000b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u0002H\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016R(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/auto/SubDeviceConnectionUtils;", "Lcom/amazon/music/media/auto/util/SubDeviceConnectionUtil;", "", "shouldRefreshPlaybackInstanceId", "", "disconnectAllDevices", "connectAllDevices", "updatePlayBackInstanceIdForPodcast", "", "subDeviceType", "shouldStopCasting", "sendSubDeviceConnectedEvent", "sendSubDeviceDisconnectedEvent", "", "connectedDeviceTypes", "Ljava/util/List;", "getConnectedDeviceTypes", "()Ljava/util/List;", "setConnectedDeviceTypes", "(Ljava/util/List;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SubDeviceConnectionUtils implements SubDeviceConnectionUtil {
    public static final SubDeviceConnectionUtils INSTANCE = new SubDeviceConnectionUtils();
    private static List<String> connectedDeviceTypes;

    private SubDeviceConnectionUtils() {
    }

    static {
        List<String> listSynchronizedList = Collections.synchronizedList(new ArrayList());
        Intrinsics.checkNotNullExpressionValue(listSynchronizedList, "synchronizedList(ArrayList())");
        connectedDeviceTypes = listSynchronizedList;
    }

    public final List<String> getConnectedDeviceTypes() {
        return connectedDeviceTypes;
    }

    @Override // com.amazon.music.media.auto.util.SubDeviceConnectionUtil
    public void sendSubDeviceConnectedEvent(String subDeviceType, boolean shouldStopCasting) {
        synchronized (connectedDeviceTypes) {
            if (subDeviceType != null) {
                SubDeviceConnectionUtils subDeviceConnectionUtils = INSTANCE;
                if (!subDeviceConnectionUtils.getConnectedDeviceTypes().contains(subDeviceType)) {
                    if (shouldStopCasting) {
                        CastingSessionManager.getInstance().disconnect(TerminationReason.EXTERNAL_DEVICE_CONNECTED);
                    }
                    MTSMetricsSender mTSMetricsSender = Playback.getInstance().getPlaybackConfig().getMTSMetricsSender();
                    subDeviceConnectionUtils.disconnectAllDevices(false);
                    mTSMetricsSender.sendSubDeviceConnectedEvent(subDeviceType, true);
                    subDeviceConnectionUtils.updatePlayBackInstanceIdForPodcast();
                    subDeviceConnectionUtils.connectAllDevices(false);
                    subDeviceConnectionUtils.getConnectedDeviceTypes().add(subDeviceType);
                }
            }
        }
    }

    @Override // com.amazon.music.media.auto.util.SubDeviceConnectionUtil
    public void sendSubDeviceDisconnectedEvent(String subDeviceType) {
        synchronized (connectedDeviceTypes) {
            if (subDeviceType != null) {
                SubDeviceConnectionUtils subDeviceConnectionUtils = INSTANCE;
                if (subDeviceConnectionUtils.getConnectedDeviceTypes().contains(subDeviceType)) {
                    MTSMetricsSender mTSMetricsSender = Playback.getInstance().getPlaybackConfig().getMTSMetricsSender();
                    subDeviceConnectionUtils.getConnectedDeviceTypes().remove(subDeviceType);
                    subDeviceConnectionUtils.disconnectAllDevices(false);
                    mTSMetricsSender.sendSubDeviceDisconnectedEvent(subDeviceType, true);
                    subDeviceConnectionUtils.updatePlayBackInstanceIdForPodcast();
                    subDeviceConnectionUtils.connectAllDevices(false);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
    }

    private final void disconnectAllDevices(boolean shouldRefreshPlaybackInstanceId) {
        MTSMetricsSender mTSMetricsSender = Playback.getInstance().getPlaybackConfig().getMTSMetricsSender();
        Iterator<String> it = connectedDeviceTypes.iterator();
        while (it.hasNext()) {
            mTSMetricsSender.sendSubDeviceDisconnectedEvent(it.next(), shouldRefreshPlaybackInstanceId);
        }
    }

    private final void connectAllDevices(boolean shouldRefreshPlaybackInstanceId) {
        MTSMetricsSender mTSMetricsSender = Playback.getInstance().getPlaybackConfig().getMTSMetricsSender();
        Iterator<String> it = connectedDeviceTypes.iterator();
        while (it.hasNext()) {
            mTSMetricsSender.sendSubDeviceConnectedEvent(it.next(), shouldRefreshPlaybackInstanceId);
        }
    }

    private final void updatePlayBackInstanceIdForPodcast() {
        Podcast.setPlaybackInstanceId(Playback.getInstance().getPlaybackConfig().getPlaybackInstanceId());
    }
}
