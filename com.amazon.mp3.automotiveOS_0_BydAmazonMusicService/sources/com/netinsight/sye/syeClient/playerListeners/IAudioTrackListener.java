package com.netinsight.sye.syeClient.playerListeners;

import com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo;
import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\"\u0010\u0007\u001a\u00020\u00062\u0011\u0010\u0005\u001a\r\u0012\t\u0012\u00070\u0003¢\u0006\u0002\b\u00040\u0002H&¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/playerListeners/IAudioTrackListener;", "", "", "Lcom/netinsight/sye/syeClient/audio/ISyeAudioTrack;", "Lkotlin/jvm/JvmSuppressWildcards;", "audioTracks", "", "onAvailableAudioTracks", "(Ljava/util/List;)V", "Lcom/netinsight/sye/syeClient/audio/ISyeAudioStreamInfo;", "syeAudioStreamInfo", "onAudioStreamChange", "(Lcom/netinsight/sye/syeClient/audio/ISyeAudioStreamInfo;)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public interface IAudioTrackListener {
    void onAudioStreamChange(ISyeAudioStreamInfo syeAudioStreamInfo);

    void onAvailableAudioTracks(List<ISyeAudioTrack> audioTracks);
}
