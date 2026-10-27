package com.amazon.mp3.util;

import com.amazon.digitalmusicplayback.StreamingStatusResponse;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.concurrency.ConcurrentDeviceInfo;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConcurrencyUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/util/ConcurrencyUtil;", "", "()V", "onMaximumConcurrencyReached", "", "streamingStatusResponses", "", "Lcom/amazon/digitalmusicplayback/StreamingStatusResponse;", "player", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ConcurrencyUtil {
    public static final ConcurrencyUtil INSTANCE = new ConcurrencyUtil();

    private ConcurrencyUtil() {
    }

    public final void onMaximumConcurrencyReached(List<StreamingStatusResponse> streamingStatusResponses, PrimeVideoPlayer player) {
        ConcurrencyException concurrencyException;
        Intrinsics.checkNotNullParameter(streamingStatusResponses, "streamingStatusResponses");
        Intrinsics.checkNotNullParameter(player, "player");
        ArrayList<StreamingStatusResponse> arrayList = new ArrayList();
        Iterator<T> it = streamingStatusResponses.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            String deviceName = ((StreamingStatusResponse) next).getDeviceName();
            Intrinsics.checkNotNullExpressionValue(deviceName, "it.deviceName");
            if (deviceName.length() > 0) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        for (StreamingStatusResponse streamingStatusResponse : arrayList) {
            arrayList2.add(new ConcurrentDeviceInfo(streamingStatusResponse.getDeviceName(), streamingStatusResponse.getElapsedTime()));
        }
        if (arrayList2.isEmpty()) {
            concurrencyException = new ConcurrencyException(CollectionsKt__CollectionsKt.mutableListOf(new ConcurrentDeviceInfo(null, 0L)), false);
        } else {
            concurrencyException = new ConcurrencyException(arrayList2, false);
        }
        player.setError(concurrencyException, ChangeReason.RESOLVING_CONCURRENCY);
    }
}
