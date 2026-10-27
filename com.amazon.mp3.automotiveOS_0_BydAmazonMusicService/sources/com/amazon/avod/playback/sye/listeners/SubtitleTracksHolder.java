package com.amazon.avod.playback.sye.listeners;

import com.netinsight.sye.syeClient.generated.enums.cEA608.ChannelIndex;
import com.netinsight.sye.syeClient.playerListeners.IClosedCaptionListener;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleTracksHolder implements IClosedCaptionListener {
    private boolean mHasSubtitle = false;

    @Override // com.netinsight.sye.syeClient.playerListeners.IClosedCaptionListener
    public void onAvailableClosedCaptionChannels(List<ChannelIndex> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mHasSubtitle = true;
    }
}
