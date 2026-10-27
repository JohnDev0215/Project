package com.amazon.video.sdk.player;

import com.amazon.video.sdk.player.HdcpLevelProvider;

/* JADX INFO: loaded from: classes5.dex */
public class UnsupportedPlatformHdcpLevelProvider implements HdcpLevelProvider {
    @Override // com.amazon.video.sdk.player.HdcpLevelProvider
    public HdcpLevelProvider.HdcpLevel getCurrentHdcpLevel() {
        return HdcpLevelProvider.HdcpLevel.NO_HDCP_SUPPORT;
    }
}
